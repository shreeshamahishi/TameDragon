package org.tamedragon.common.optimization;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

import org.tamedragon.common.llvmir.instructions.BranchInst;
import org.tamedragon.common.llvmir.instructions.CallInst;
import org.tamedragon.common.llvmir.instructions.Instruction;
import org.tamedragon.common.llvmir.instructions.LoadInst;
import org.tamedragon.common.llvmir.instructions.PhiNode;
import org.tamedragon.common.llvmir.instructions.ReturnInst;
import org.tamedragon.common.llvmir.instructions.TerminatorInst;
import org.tamedragon.common.llvmir.instructions.CmpInst.Predicate;
import org.tamedragon.common.llvmir.instructions.Instruction.InstructionID;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionDetailAccessException;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionUpdateException;
import org.tamedragon.common.llvmir.types.Argument;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.CompilationContext;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.Type;
import org.tamedragon.common.llvmir.types.Value;


public class TailRecursiveCallElimination {

	private CompilationContext compilationContext = null;
	private List<PhiNode> newPhiNodes = new ArrayList<PhiNode>();
	private Function function;

	private BasicBlock newEntry;

	public Transformations execute(Function function) throws InstructionUpdateException, InstructionDetailAccessException{
		this.function = function;
		compilationContext = function.getContext();
		
		Transformations transformations = new Transformations(Transformations.TAIL_RECURSIVE_CALL_ELIMINATION);

		List<CallInst> tailRecursiveCalls = getTailRecursiveCallInstructions();
		for(CallInst tailRecursiveCall : tailRecursiveCalls){
			BasicBlock basicBlockWithTailRecursiveCall = tailRecursiveCall.getParent();

			BasicBlock oldFirstBasicBlock = function.getStartNode();

			if(newEntry == null){
				newEntry = new BasicBlock(function);
				function.insertNewStartBasicBlock(newEntry);
				Value val = oldFirstBasicBlock;
				val.setName("tailrecurse");

				// Create a new phi node for each of the arguments that are passed to the function
				List<Argument> arguments = function.getArgumentList();
				for(Argument argument : arguments){
					PhiNode phiNode = PhiNode.create(argument.getType(), argument.getName() + ".tr", 2, 
							(Value)argument, oldFirstBasicBlock);
					argument.replaceAllUsesOfThisWith(phiNode);
					phiNode.addIncoming(argument, newEntry);
					oldFirstBasicBlock.addInstruction(phiNode);

					newPhiNodes.add(phiNode);
				}
			}

			// The other argument for the phi node will be the values passed in the call
			int numArgs = tailRecursiveCall.getNumArgOperands();
			for(int i = 0; i < numArgs; i++){
				Value argOperand = tailRecursiveCall.getArgOperand(i + 1);
				newPhiNodes.get(i).addIncoming(argOperand, basicBlockWithTailRecursiveCall);
			}

			// Remove the call instruction itself
			int numUses = tailRecursiveCall.getNumUses();
			if(numUses == 0){
				// There are no users of the return value of this call, can be removed right away
				basicBlockWithTailRecursiveCall.removeInstruction(tailRecursiveCall);
			}
			else{
				// The return value of this call has some uses; they must either be phi nodes in the successor
				// block or is being returned itself
				for(int i = 0; i < numUses; i++){
					Value user = tailRecursiveCall.getUserAt(i);
					Instruction userInstr = (Instruction) user;
					if(userInstr.getInstructionID() == InstructionID.PHI_NODE_INST){
						PhiNode phiNode = (PhiNode) userInstr;
						phiNode.removeIncomingValue(tailRecursiveCall.getParent());
						basicBlockWithTailRecursiveCall.removeInstruction(tailRecursiveCall);
					}
					else{
						ReturnInst returnInst = (ReturnInst) userInstr;
						if(returnInst.getParent() == tailRecursiveCall.getParent()){
							basicBlockWithTailRecursiveCall.removeInstruction(tailRecursiveCall);
						}
					}
				}
			}
			
			BasicBlock oldTarget = function.getCfg().getSuccessors(basicBlockWithTailRecursiveCall).get(0);
			function.replaceTargetOfBlockWith(basicBlockWithTailRecursiveCall, oldTarget, oldFirstBasicBlock );
		}
		
		return transformations;
	}

	private List<CallInst> getTailRecursiveCallInstructions() {
		Iterator<BasicBlock> iterator = function.basicBlocksIterator();

		List<CallInst> tailRecursiveCallInstructions = new ArrayList<CallInst>();

		while(iterator.hasNext()){
			BasicBlock basicBlock = iterator.next();
			if(!basicBlockCanBeScannedForTailRecursiveCalls(basicBlock)){
				continue;
			}

			
			int numInstrs = basicBlock.numInstructions();
			if(numInstrs == 2){
				Instruction instruction = basicBlock.getInstructionAt(1);
				if(instruction.getInstructionID() == InstructionID.CALL_INST &&
						((CallInst)instruction).getCalledFunction() == function){
					tailRecursiveCallInstructions.add((CallInst) instruction);
					continue;
				}
			}
			
			Stack<Instruction> interveningInstructions = new Stack<Instruction>();
			
			int currentIndex = basicBlock.numInstructions() -2;
			while(currentIndex >= 0){
				Instruction instruction = basicBlock.getInstructionAt(currentIndex);
				if(instruction.getInstructionID() != InstructionID.CALL_INST){
					interveningInstructions.add(instruction);
					currentIndex--;
					continue;
				}
				
				CallInst callInst = (CallInst) instruction;
				if(callInst.getCalledFunction() == function){
					// Maybe tail recursive
					if(allInstructionsCanMoveAboveCall(interveningInstructions, callInst)){
						tailRecursiveCallInstructions.add(callInst);
						break;
					}
				}
				
				currentIndex--;
			}
		}
		
		return tailRecursiveCallInstructions;
	}

	private boolean allInstructionsCanMoveAboveCall(Stack<Instruction> interveningInstructions, CallInst callInst) {
		
		while(!interveningInstructions.empty()){
			Instruction instruction = interveningInstructions.pop();
			if(!safeToMoveAboveCall(instruction, callInst)){
				return false;
			}
		}
		
		return true;
	}
	
	private boolean safeToMoveAboveCall(Instruction instruction, CallInst callInst){
		// FIXME: We can move load/store/call/free instructions above the call if the
		// call does not mod/ref the memory location being processed.
		if (instruction.mayHaveSideEffects()){  
			// This also handles volatile loads.
			return false;
		}

		if (instruction.getInstructionID() == InstructionID.LOAD_INST) {
			LoadInst loadInst = (LoadInst) instruction;
			// Loads may always be moved above calls without side effects.
			if (callInst.mayHaveSideEffects()) {
				// Non-volatile loads may be moved above a call with side effects if it
				// does not write to memory and the load provably won't trap.
				// FIXME: Writes to memory only matter if they may alias the pointer
				// being loaded from. 
				// TODO : We dont know yet if it is safe to load unconditionally
				if (callInst.mayWriteToMemory())
					return false;
			}
		}

		// Otherwise, if this is a side-effect free instruction, check to make sure
		// that it does not use the return value of the call.  If it doesn't use the
		// return value of the call, it must only use things that are defined before
		// the call, or movable instructions between the call and the instruction
		// itself.
		for(int i = 0; i < callInst.getNumUses(); i++){
			if(instruction == callInst.getUserAt(i)){
				return false;
			}
		}

		return true;
	}

	/**
	 * 
	 * @param basicBlock
	 * @return
	 */
	private boolean basicBlockCanBeScannedForTailRecursiveCalls(
			BasicBlock basicBlock) {
		TerminatorInst terminatorInst = basicBlock.getLastInstruction();
		if(terminatorInst.getInstructionID() == InstructionID.RETURN_INST){
			if(basicBlock.getFirstInstruction() == terminatorInst){
				return false;
			}
			
			return true;
		}

		if(!(terminatorInst.getInstructionID() == InstructionID.BRANCH_INST &&
				((BranchInst) terminatorInst).isUnconditional())){
			return false;
		}

		BasicBlock successor = function.getCfg().getSuccessors(basicBlock).get(0);
		Instruction lastInstrOfSuccessor = successor.getLastInstruction();
		if(lastInstrOfSuccessor.getInstructionID() != InstructionID.RETURN_INST){
			return false;
		}

		if(successor.getFirstNonPhiInstruction() != lastInstrOfSuccessor){
			return false;
		}

		return true;
	}
	
	private void removeThisInstrFromItsUsersList(Instruction instruction) throws InstructionDetailAccessException, InstructionUpdateException {
		int numOfUsers = instruction.getNumUses();
		for(int  i = 0; i < numOfUsers; i++){
			Value user = instruction.getUserAt(i);
			user.removeUser(instruction);

			if(user instanceof PhiNode){
				PhiNode phiNode = (PhiNode)user;
				int nosOfIncomingValues = phiNode.getNumIncomingValues();
				for(int j = 0; j < nosOfIncomingValues; j++){
					Value incomingVal = phiNode.getIncomingValue(j);
					if(incomingVal == instruction){
						// NOTE: Following statement disabled by Shreesha, to be reviewed later.
						//phiNode.removeIncomingValue(j);
					}
				}
				if(phiNode.getNumIncomingValues() == 1){
					Value incomingValue = phiNode.getIncomingValue(0);
					replaceUsersOfPhiNodeWithValue(phiNode,incomingValue);
					phiNode.getParent().removeInstructionFromBasicBlock(phiNode);
				}
			}
		}
		int nosOfOprnds = instruction.getNumOperands();
		for(int i = 0 ; i < nosOfOprnds; i++){
			Value oprndValue = instruction.getOperand(i);
			if(oprndValue.getNumUses() != 0)
				oprndValue.removeUser(instruction);
		}
	}

	private void replaceUsersOfPhiNodeWithValue(PhiNode phiNode, Value incomingValue) {
		int numOfUsers = phiNode.getNumUses();
		for(int i = 0; i < numOfUsers; i++){
			Value user = phiNode.getUserAt(i);
			if(user instanceof Instruction){
				Instruction instruction = (Instruction)user;
				int numOfOprnds = instruction.getNumOperands();
				for(int j = 0; j < numOfOprnds; j++){
					Value oiprnd = instruction.getOperand(j);
					if(oiprnd == phiNode){
						instruction.setOperand(j, incomingValue);
					}
				}
			}

		}
	}

	private void updateUsersOfIncomingPhiNodeValues(Function parentFunction) throws InstructionDetailAccessException {
		//		for(PhiNode phiNode : listOfCompletePhiNodes){
		//			for(int i = 0; i < 2; i++){
		//				Value incomingVal = phiNode.getIncomingValue(i);
		//				int nosOfUsers = incomingVal.getNumUses();
		//				for(int j = 0; j < nosOfUsers; j++){
		//					Value user = incomingVal.getUserAt(j);
		//					if(user instanceof Instruction && user != phiNode){
		//						Instruction instruction = (Instruction)user;
		//						int indexForBBOfUserInstr = parentFunction.getBasicBlocks().indexOf(instruction.getParent());
		//						int indexForBBOfPhiNode = parentFunction.getBasicBlocks().indexOf(phiNode.getParent());
		//						if(indexForBBOfUserInstr >= indexForBBOfPhiNode){
		//							int numOfOprndsOfUserInstr = instruction.getNumOperands();
		//							for(int k = 0; k < numOfOprndsOfUserInstr; k++){
		//								Value oprnd = instruction.getOperand(k);
		//								if(oprnd == incomingVal && (oprnd instanceof Argument))
		//									instruction.setOperand(k, phiNode);
		//							}
		//						}
		//					}
		//				}
		//			}
		//		}
	}

	private Predicate getOppositePredicate(Predicate predicate) {
		if(predicate == Predicate.ICMP_EQ)
			return Predicate.ICMP_NE;
		else if(predicate == Predicate.ICMP_SGE)
			return Predicate.ICMP_SLT;
		else if(predicate == Predicate.ICMP_SGT)
			return Predicate.ICMP_SLE;
		return null;
	}
}
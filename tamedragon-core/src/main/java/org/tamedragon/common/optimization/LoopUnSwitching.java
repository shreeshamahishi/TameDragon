package org.tamedragon.common.optimization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.jgraph.graph.DefaultEdge;
import org.tamedragon.common.DominatorTree;
import org.tamedragon.common.controlflowanalysis.LoopIdentifier;
import org.tamedragon.common.controlflowanalysis.LoopInfo;
import org.tamedragon.common.controlflowanalysis.LoopNestingTree;
import org.tamedragon.common.llvmir.instructions.BinaryOperator;
import org.tamedragon.common.llvmir.instructions.BranchInst;
import org.tamedragon.common.llvmir.instructions.CFG;
import org.tamedragon.common.llvmir.instructions.ICmpInst;
import org.tamedragon.common.llvmir.instructions.Instruction;
import org.tamedragon.common.llvmir.instructions.PhiNode;
import org.tamedragon.common.llvmir.instructions.TerminatorInst;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionCreationException;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionDetailAccessException;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionUpdateException;
import org.tamedragon.common.llvmir.math.APInt;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.CompilationContext;
import org.tamedragon.common.llvmir.types.Constant;
import org.tamedragon.common.llvmir.types.ConstantInt;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.Type;
import org.tamedragon.common.llvmir.types.Value;

/**
 * This class will move the loop invariant conditional expression outside of the loop and will create two loops.
 * E.g. 
 * for                             if(LICondExpr)
 *    A;								for
 *    if(LICondExpr)						A;B;C;
 *    	B;						   else
 *    C;								for
 *    										A;C;
 *
 */
public class LoopUnSwitching {

	// This map keeps track of loop variant values and list of new instructions that needs to be changed by new loop's variant variables.
	private Map<Value, Set<Instruction>> mapValueVsInCompleteInstr = new HashMap<Value, Set<Instruction>>();
	
	// This map keeps track of old vs new loop variant variables.
	private Map<Value,Value> oldVsNewValue = new HashMap<Value, Value>();
	
	// This map keeps track of Basic Block values vs Basic Blocks(Basic Blocks within the loop in process and the new loop which we create from this loop)
	private Map<Value, BasicBlock> valueVsBB = new HashMap<Value, BasicBlock>();
	
	// This stack keeps track of loop invariant conditional expressions, which we need to hoist.
	private Stack<ICmpInst> stackOfLoopInvariantCondExpr = new Stack<ICmpInst>();
	
	private int splitCounter = 1;
	
	private CFG cfg;

	public Transformations execute(Function function) throws InstructionDetailAccessException, InstructionUpdateException, InstructionCreationException, InstantiationException{
		Transformations transformations = new Transformations(Transformations.LOOP_UNSWITCHING);
		
		cfg = function.getCfg();

		// Identify any loops
		LoopIdentifier loopIdentifier = new LoopIdentifier(function);
		LoopNestingTree loopNestingTree = loopIdentifier.getLoopNestingTree();
		DominatorTree dominatorTree = loopIdentifier.getDominatorTree();

		// Iterate over the loop nesting tree, starting with the leaf loops 
		// first; this way, we start with the innermost loops and traverse
		// to outer loops
		List<LoopInfo> leafLoops = loopNestingTree.getLeafLoops();

		HashSet<LoopInfo> visitedLoops = new HashSet<LoopInfo>();

		for(LoopInfo leafLoop : leafLoops){
			LoopInfo runner = leafLoop;
			boolean isOutterLoop = false;
			while(runner != null){
				if(visitedLoops.contains(runner)){
					// Already visited, try next one
					runner = loopNestingTree.getImmediateOuterLoop(runner);
					isOutterLoop = true;
					continue;
				}
				visitedLoops.add(runner);
				LinkedHashSet<Value> hashSet = runner.getLoopInvariants();
				Iterator<Value> iteratorSet = hashSet.iterator();
				// Here we iterate over the set of loop invariant variables.
				int numOfLoopInvariantExprs = 0;
				while(iteratorSet.hasNext()){
					Value value = iteratorSet.next();
					if(value instanceof ICmpInst){
						ICmpInst cmpInstr = (ICmpInst)value;

						// the cmpInstr should be used by a conditional branch
						boolean isUsedByCondBr = chkIfUsedByACondBr(cmpInstr);

						if(!isUsedByCondBr)
							continue;

						// Not visited, visit it now
						if(numOfLoopInvariantExprs == 0)
							analyzeAndPerformLoopUnSwitching(dominatorTree, runner, cmpInstr, isOutterLoop);
						else{
							reset();
							execute(function);
						}

						numOfLoopInvariantExprs++;
					}
				}
				// Get the next outer loop to analyse
				runner = loopNestingTree.getImmediateOuterLoop(runner);
				isOutterLoop = true;
			}
		}
		return transformations;
	}

	private boolean chkIfUsedByACondBr(ICmpInst cmpInstr) {
		int numOfUsers = cmpInstr.getNumUses();
		for(int i = 0; i < numOfUsers; i++){
			Value user = cmpInstr.getUserAt(i);
			if(user instanceof BranchInst)
				return true;
		}
		return false;
	}

	private void reset() {
		// This map keeps track of loop variant values and list of new instructions that needs to be changed by new loop's variant variables.
		mapValueVsInCompleteInstr = new HashMap<Value, Set<Instruction>>();
		// This map keeps track of old vs new loop variant variables.
		oldVsNewValue = new HashMap<Value, Value>();
		// This map keeps track of Basic Block values vs Basic Blocks(Basic Blocks within the loop in process and the new loop which we create from this loop)
		valueVsBB = new HashMap<Value, BasicBlock>();
		// This stack keeps track of loop invariant conditional expressions, which we need to hoist.
		stackOfLoopInvariantCondExpr = new Stack<ICmpInst>();
	}

	/**
	 * This function hoist the loop invariant conditional expression, creates a conditional branch and creates two loops 
	 * one loop will execute the true part of the Loop Invariant Conditional Expr and other loop will execute the false 
	 * part of it.
	 * @param dominatorTree
	 * @param loopInfo
	 * @param isOutterLoop 
	 * @throws InstructionDetailAccessException
	 * @throws InstructionUpdateException
	 * @throws InstructionCreationException
	 * @throws InstantiationException
	 */
	private void analyzeAndPerformLoopUnSwitching(DominatorTree dominatorTree, LoopInfo loopInfo,
			ICmpInst cmpInstr, boolean isOutterLoop) throws InstructionDetailAccessException, InstructionUpdateException, InstructionCreationException, InstantiationException {
		
//		BasicBlock loopExit = loopInfo.getBackEdgeSource();
//		BasicBlock loopHeader = loopInfo.getBackEdgeTarget();
//		Function parentFunc = loopExit.getParent();
//		LinkedHashSet<Value> hashSet = loopInfo.getLoopInvariants();
//		stackOfLoopInvariantCondExpr.push(cmpInstr);
//		
//		Function function = loopHeader.getParent();
//
//		// Get the loop PreHeader and hoist the instruction
//		// TODO Here we assume only one preHeader is there for the loop
//		BasicBlock preHeader = (BasicBlock)loopInfo.getPreHeader().get(0);
//		TerminatorInst preHeaderTerminatorInst = preHeader.getLastInstruction();
//		
//		// Here we hoist the loop invariant expression
//		cmpInstr.moveInstructionBefore(preHeaderTerminatorInst);
//
//		// If this loop is not the innermost loop, then just hoist the invariant condition and do nothing and return back.
//		if(isOutterLoop)
//			return;
//
//		CompilationContext compilationContext = cmpInstr.getContext();
//		// Here we remove the Terminator Instruction from the loop preHeader
//		preHeader.removeInstruction(preHeaderTerminatorInst);
//
//		// Create two BasicBlocks. One will go to the loop executing the true part of the LICondExpr 
//		// and another will do the opposite.
//		Value trueBBVal = new Value(Type.getLabelType(compilationContext));
//		String splitName = getSplitName();
//
//		trueBBVal.setName(splitName);
//		BasicBlock ifTrue = new BasicBlock(trueBBVal, parentFunc);
//		CFG cfg = parentFunc.getCfg();
//
//		// Add the true basic block after the loop pre-header
//		List<BasicBlock> destinations = new ArrayList<BasicBlock>();
//		destinations.add(loopHeader);
//		function.insertNewNodeBetween(preHeader, destinations, ifTrue);
//
//		// Add a branch instruction from the true basic block to the loopHeader.
//		BranchInst split1BrInstr = BranchInst.create(loopHeader, ifTrue, compilationContext);
//		ifTrue.addInstruction(split1BrInstr);
//
//		// This is the false Basic Block and we put it after the true Basic Block
//		Value falseBBVal = new Value(Type.getLabelType(compilationContext));
//		splitName = getSplitName();
//		falseBBVal.setName(splitName);
//		BasicBlock ifFalse = new BasicBlock(falseBBVal, parentFunc);
//		cfg.insertNodeAt(ifFalse, index + 2);
//		bblist.add(indx+2 , ifFalse);
//
//		// At last we create a conditional branch in the loop preHeader
//		BranchInst branchInst2 = BranchInst.create(ifTrue, ifFalse, cmpInstr, preHeader, compilationContext);
//		preHeader.addInstruction(branchInst2);
//
//		// Create a duplicate of the loop
//		List<BasicBlock>  nodes = loopInfo.getNodesInLoop();
//		int count  = 0;
//		BasicBlock tempBB = null;
//		// Iterate through the nodes of the loop
//		for(BasicBlock node : nodes){
//			BasicBlock basicBlock = (BasicBlock)node;
//			// Update the valueVsBB map with old BB value vs old BB
//			valueVsBB.put(basicBlock.getValue(), basicBlock);
//			// Create a new BB as similar to the old one
//			Value value2 = new Value(Type.getLabelType(compilationContext));
//			BasicBlock newBB = new BasicBlock(value2, parentFunc);
//			// Update the valueVsBB with newBB value and the newBB
//			valueVsBB.put(value2, newBB);
//
//			// Here we put the new instructions into the new BB , 
//			// these instructions are similar to the instruction in the old BB, except that the loop variant variables are different
//			createNewListInstrAndAddItToNewBB(basicBlock, newBB, nodes, hashSet, cfg);
//
//			// inserting the newBB into the CFG, after the loop exit of the loop of which we want to make duplicate of.
//			int pos = 0;
//			if(count  == 0){
//				tempBB = newBB;
//				pos = cfg.getNodeIndex(loopExit);
//				indx = bblist.indexOf(loopExit);
//				cfg.insertNodeAt(newBB, pos+1);	
//
//				// insert the false BB between the loop preheader and the newBB
//				ifFalse.addBasicBlockInBetween(preHeader, newBB, cfg);
//
//				// create a branch instruction from false BB to newBB
//				BranchInst split2BrInstr = BranchInst.create(newBB, ifFalse, compilationContext);
//				ifFalse.addInstruction(split2BrInstr);
//			}
//			else{
//				// insert rest of the new BBs after the first new newBB
//				pos = cfg.getNodeIndex(tempBB);
//				indx = bblist.indexOf(tempBB);
//				tempBB = newBB;
//				cfg.insertNodeAt(newBB, pos+1);	
//			}
//
//			bblist.add(indx+1, newBB);
//			count++;
//		}
//		// now since all the new instructions are ready we can update them
//		updateInCompleteInstrs(hashSet,cfg);
	}

	/**
	 * This function creates new list of instructions based on the list of instructions of oldBB 
	 * @param oldBB
	 * @param newBB
	 * @param nodes
	 * @param setOfLoopInVariants
	 * @param cfg
	 * @throws InstructionDetailAccessException
	 * @throws InstructionUpdateException
	 * @throws InstructionCreationException
	 * @throws InstantiationException
	 */
	private void createNewListInstrAndAddItToNewBB(BasicBlock oldBB, BasicBlock newBB,	
			List<BasicBlock> nodes, LinkedHashSet<Value> setOfLoopInVariants, CFG cfg) throws InstructionDetailAccessException, InstructionUpdateException, InstructionCreationException, InstantiationException {
		oldVsNewValue.put(oldBB, newBB);
		Iterator<Instruction> instructions = oldBB.getInstructions();
		while(instructions.hasNext() ){
			Instruction newInstruction = null;

			Instruction instruction = instructions.next();
			
			if(instruction instanceof PhiNode){
				PhiNode phiNode = (PhiNode)instruction;
				int numOfIncomingValues = phiNode.getNumIncomingValues();
				newInstruction = PhiNode.create(phiNode.getType(), null, numOfIncomingValues, phiNode.getPrimaryValue(), newBB);
				for(int i = 0; i < numOfIncomingValues; i++){
					BasicBlock basicBlock = phiNode.getIncomingBlock(i);
					Value value = phiNode.getIncomingValue(i);
					((PhiNode)newInstruction).addIncoming(value, basicBlock);
					if(nodes.contains(basicBlock)){
						// for incoming BB value
						updateMapValueVsInCompleteInstr(basicBlock, newInstruction);
						// for incoming value from that BB
						updateMapValueVsInCompleteInstr(value, newInstruction);
					}
				}
			}
			else if(instruction instanceof BinaryOperator){
				BinaryOperator binaryOperator = (BinaryOperator)instruction;
				newInstruction = BinaryOperator.create(binaryOperator.getOpCode(), binaryOperator.getType(), null, binaryOperator.getOperand(0), binaryOperator.getOperand(1), binaryOperator.hasNoSignedWrap(), binaryOperator.hasNoUnsignedWrap(), false, newBB);
				updateMapOrSetOprnd(binaryOperator, newInstruction, setOfLoopInVariants);
			}
			else if(instruction instanceof ICmpInst){
				ICmpInst cmpInst = (ICmpInst)instruction;
				newInstruction = ICmpInst.create(cmpInst.getPredicate(), cmpInst.getOperand(0), cmpInst.getOperand(1), null, newBB);
				updateMapOrSetOprnd(cmpInst, newInstruction, setOfLoopInVariants);
			}
			else if(instruction instanceof BranchInst){
				BranchInst branchInst = (BranchInst)instruction;
				BasicBlock trueBB = branchInst.getNodeForTruePath();

				if(branchInst.isConditional()){
					Value condition = branchInst.getCondition();
					BasicBlock falseBB = branchInst.getNodeForFalsePath();
					newInstruction = BranchInst.create(trueBB, falseBB, condition, newBB, branchInst.getContext());
					cfg.addEdge(newBB, trueBB, new DefaultEdge());
					cfg.addEdge(newBB, falseBB , new DefaultEdge());
					if(!stackOfLoopInvariantCondExpr.isEmpty() && condition == stackOfLoopInvariantCondExpr.peek()){
						Value cmpCond = stackOfLoopInvariantCondExpr.pop();
						if(cmpCond instanceof ICmpInst){
							APInt apInt = new APInt(1, "1", false);
							ConstantInt constantInt = new ConstantInt(Type.getInt1Type(cmpCond.getContext(), false), apInt);
							branchInst.setCondition(constantInt);
							apInt = new APInt(1, "0", false);
							constantInt = new ConstantInt(Type.getInt1Type(cmpCond.getContext(), false), apInt);
							((BranchInst)newInstruction).setCondition(constantInt);
						}
					}
				}
				else{
					newInstruction = BranchInst.create(branchInst.getNodeForTruePath(), newBB, branchInst.getContext());
					cfg.addEdge(newBB, trueBB, new DefaultEdge());
				}

				updateMapOrSetOprnd(branchInst, newInstruction, setOfLoopInVariants);
			}

			if(newInstruction != null){
				oldVsNewValue.put(instruction, newInstruction);
				newBB.addInstruction(newInstruction);
			}
		}
	}

	/**
	 * This function sees if the operand of this new instruction is a loop invariant value then 
	 * we can directly set the operand or else we have to update the map of loop variant values and list of new instructions
	 * which is dependent on this value.
	 * 
	 * @param instruction
	 * @param newInstr
	 * @param setOfLoopInVariants
	 */
	private void updateMapOrSetOprnd(Instruction instruction, Instruction newInstr, LinkedHashSet<Value> setOfLoopInVariants) {
		int numOfOprnds = instruction.getNumOperands();
		for(int i = 0; i < numOfOprnds; i++){
			Value oprnd = instruction.getOperand(i);
			if(setOfLoopInVariants.contains(oprnd) && !oprnd.getType().isLabelType())
				newInstr.setOperand(i, oprnd);
			else
				updateMapValueVsInCompleteInstr(oprnd, newInstr);
		}
	}

	/**
	 * This function updates the incomplete instructions of the new loop
	 * @param setOfLoopInVariants
	 * @param cfg
	 * @throws InstructionUpdateException
	 * @throws InstructionDetailAccessException
	 */
	private void updateInCompleteInstrs(LinkedHashSet<Value> setOfLoopInVariants, CFG cfg) throws InstructionUpdateException, InstructionDetailAccessException {
		Set<Value> keySet = oldVsNewValue.keySet();
		Iterator<Value> iterator = keySet.iterator();
		while(iterator.hasNext()){
			Value value = iterator.next();
			if(mapValueVsInCompleteInstr.containsKey(value )){
				Set<Instruction> instructions = mapValueVsInCompleteInstr.get(value);
				for(Instruction instruction : instructions){

					int numOfOprnds = instruction.getNumOperands();

					if(instruction instanceof BranchInst){
						BranchInst branchInst = (BranchInst)instruction;

						for(int i = 0; i < numOfOprnds; i++){
							Value value2 = instruction.getOperand(i);
							Value newValue = oldVsNewValue.get(value2);

							// We check for isLabelType because even the BasicBlocks are considered as loop invariants and we don't want them to considered as such.
							// and we check for newValue != null because not every operand will be loop invariant so it won't be in the oldVsNewValue Map
							if((!setOfLoopInVariants.contains(value2) || (value2 instanceof ICmpInst) || value2.getType().isLabelType()) && newValue != null ){
								BasicBlock basicBlock = valueVsBB.get(newValue);

								// This case is possible for conditional branch instr where the first operand is a conditional expression instead of BasicBlock
								if(basicBlock == null){
									Value condition = branchInst.getCondition();
									if(!(condition instanceof Constant))
										branchInst.setOperand(i, newValue);
								}
								else{
									branchInst.setSuccessor(i, basicBlock,true);
									BasicBlock oldSuccessor = valueVsBB.get(value2);
									// While creating the new branch instr we give the sucessor of the old branch instruction so thaty we need to remove.
									DefaultEdge oldEdge = cfg.getEdge(branchInst.getParent(), oldSuccessor);
									if(oldEdge != null){
										cfg.removeEdge(oldEdge);
									}
									cfg.addEdge(branchInst.getParent(), basicBlock, new DefaultEdge());
								}
							}
						}
					}
					else{
						for(int i = 0; i < numOfOprnds; i++){
							Value value2 = instruction.getOperand(i);
							Value newValue = oldVsNewValue.get(value2);
							if((!setOfLoopInVariants.contains(value2) || value2.getType().isLabelType()) && newValue != null){

								// updating the PhiNode
								if(instruction instanceof PhiNode){
									PhiNode phiNode = (PhiNode)instruction;
									BasicBlock tempIncomingBB = phiNode.getIncomingBlock(i);
									Value tempIncomingBBValue = tempIncomingBB;
									Value actualIncomingBBValue = oldVsNewValue.get(tempIncomingBBValue);
									BasicBlock actualIncomingBB = valueVsBB.get(actualIncomingBBValue);
									phiNode.setIncomingValueAndBasicBlock(i, newValue, actualIncomingBB);
								}
								else
									instruction.setOperand(i, newValue);
							}
						}
					}
				}
			}
		}
	}

	/**
	 * This function add the new instruction to the list of instructions which is dependent on the given value
	 * @param value
	 * @param inCompleteInstr
	 */
	private void updateMapValueVsInCompleteInstr(Value value, Instruction inCompleteInstr) {
		if(mapValueVsInCompleteInstr.containsKey(value)){
			Set<Instruction> instructions = mapValueVsInCompleteInstr.get(value);
			instructions.add(inCompleteInstr);
		}
		else{
			Set<Instruction> instructions = new HashSet<Instruction>();
			instructions.add(inCompleteInstr);
			mapValueVsInCompleteInstr.put(value, instructions);
		}
	}

	private String getSplitName(){
		return "split" + splitCounter ++;
	}
}

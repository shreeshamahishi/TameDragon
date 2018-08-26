package org.tamedragon.common.controlflowanalysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.tamedragon.common.llvmir.instructions.BinaryOperator;
import org.tamedragon.common.llvmir.instructions.CFG;
import org.tamedragon.common.llvmir.instructions.Instruction;
import org.tamedragon.common.llvmir.instructions.PhiNode;
import org.tamedragon.common.llvmir.instructions.BinaryOperator.BinaryOperatorID;
import org.tamedragon.common.llvmir.instructions.Instruction.InstructionID;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.Constant;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.Value;
import org.tamedragon.common.llvmir.utils.LLVMIREmitter;

public class InductionVariableAnalysis {

	private Function function;

	private LLVMIREmitter emitter;
	
	private List<InductionVariable> basicIndVars;
	
	private HashMap<Value, InductionVariable> valueAndIndVars;
	private HashMap<InductionVariable, List<InductionVariable>> 
					basicIndVarAndDependentIndVars;

	public InductionVariableAnalysis(Function function){
		emitter = LLVMIREmitter.getInstance();
		this.function = function;
		
		valueAndIndVars = new HashMap<Value, InductionVariable>();
		basicIndVarAndDependentIndVars = new 
				HashMap<InductionVariable, List<InductionVariable>>();
		identifyIndVars();
	}

	public void identifyIndVars(){

		// Identify the loops
		LoopIdentifier loopIdentifier = new LoopIdentifier(function);
		LoopNestingTree loopNestingTree = loopIdentifier.getLoopNestingTree();

		// Iterate over the loop nesting tree, starting with the leaf loops 
		// first; this way, we start with the innermost loops and traverse
		// to outer loops
		List<LoopInfo> leafLoops = loopNestingTree.getLeafLoops();

		HashSet<LoopInfo> visitedLoops = new HashSet<LoopInfo>();

		for(LoopInfo leafLoop : leafLoops){
			HashMap<Value, InductionVariable> valueAndIndVarsInLoop = identifyIndVars(leafLoop);
			valueAndIndVars.putAll(valueAndIndVarsInLoop);
			
			visitedLoops.add(leafLoop);

			// Now that the leaf loop has been visited, work outwards
			if(loopNestingTree.isAnOutermostLoop(leafLoop))
				continue;

			LoopInfo runner = loopNestingTree.getImmediateOuterLoop(leafLoop);

			while(runner != null){
				if(visitedLoops.contains(runner)){
					// Already visited, try next one
					runner = loopNestingTree.getImmediateOuterLoop(runner);
					continue;
				}
				// Not visited, visit it now
				valueAndIndVars.putAll(identifyIndVars(runner));

				// Get the next outer loop to analyze
				runner = loopNestingTree.getImmediateOuterLoop(runner);
			}
		}
	}

	private HashMap<Value, InductionVariable> identifyIndVars(LoopInfo loopInfo) {

		HashMap<Value, InductionVariable> valueAndIndVars = 
			new HashMap<Value, InductionVariable>();
		
		basicIndVars = identifyBasicIndVars(loopInfo);
		for(InductionVariable basicIndVar : basicIndVars){
			valueAndIndVars.put(basicIndVar.getDefiningInstruction(), basicIndVar);
		}

		// Now we have found the basic induction variables, lets identify the
		// dependent induction variables.
		List<BasicBlock> nodesInLoop = loopInfo.getNodesInLoop();
		Set<Value> invariantsInLoop = loopInfo.getLoopInvariants();

		for(BasicBlock node : nodesInLoop){
			
			BasicBlock bb = (BasicBlock) node;
			Iterator<Instruction> instrs = bb.getInstructions();

			BinaryOperatorID indVarOp = null;
			while(instrs.hasNext()){
				Instruction instr = instrs.next();
				InstructionID instrID = instr.getInstructionID();
				boolean isPostOperator = true;
				
				if(instrID != InstructionID.BINARY_INST)
					continue;

				BinaryOperator binaryOperator = (BinaryOperator) instr;

				BinaryOperatorID binOpId = binaryOperator.getOpCode();
				if(!(binOpId.isAddition() || binOpId.isSubtraction()
						|| binOpId.isMultiplication()))
					// Look only for additions, subtractions and multiplications
					continue;

				Value firstOperand = binaryOperator.getOperand(0);
				Value secondOperand = binaryOperator.getOperand(1);

				Value tempValue = null;
				Value diff = null;

				
				if(isLoopConstant(invariantsInLoop, firstOperand) && 
						!isLoopConstant(invariantsInLoop, secondOperand)){
					tempValue = secondOperand;
					diff = firstOperand;
					indVarOp = binOpId;
					isPostOperator = false;
				}
				else if(isLoopConstant(invariantsInLoop, secondOperand) && 
						!isLoopConstant(invariantsInLoop, firstOperand)){
					tempValue = firstOperand;
					diff = secondOperand;
					indVarOp = binOpId;
				}
				else 
					continue;
				
				InductionVariable baseIndVar = valueAndIndVars.get(tempValue);
				if(baseIndVar == null)
					continue;
				
				// tempValue is an induction variable; therefore the current
				// instruction defines a new induction variable
				Value factor = null;
				if(binOpId.isMultiplication()){
					factor = diff;
					diff = null;
				}
				
				InductionVariable newIndVar = new InductionVariable(instr, baseIndVar, factor,
						diff, isPostOperator, indVarOp, loopInfo);
				updateIndVarMap(newIndVar);
				
				valueAndIndVars.put(instr, newIndVar);
			}
		}
		
		return valueAndIndVars;

	}

	private boolean isLoopConstant(Set<Value> loopInvariants, Value value) {
		
		if(value.isAConstant())
			return true;
		
		if(loopInvariants.contains(value))
			return true;
		
		return false;

	}

	/**
	 * Returns the basic or fundamental induction vars if any are found.
	 * Informally, a fundamental induction variable in SSA form has the
	 * following properties:
	 * 
	 * a. It is a function of loop invariants and its value on the current
	 * iteration.
	 * b. It will have a phi function with one of its inputs as a definition
	 * from inside the loop and one or more definitions from outside the
	 * loop.
	 * c. The definition inside the loop will be part of the loop and will
	 * have be defined as a binary add or multiplication operation with the
	 * phi function as one of its operand and the other a loop invariant.
	 * @param loopInfo
	 * @return
	 */
	private List<InductionVariable> identifyBasicIndVars(LoopInfo loopInfo){
		List<InductionVariable> basicIndVars = new ArrayList<InductionVariable>();

		List<BasicBlock> nodesInLoop = loopInfo.getNodesInLoop();
		for(BasicBlock node : nodesInLoop){
			BasicBlock basicBlock = (BasicBlock) node;

			Iterator<Instruction> instrs = basicBlock.getInstructions();
			instrLoop : while(instrs.hasNext()){
				Instruction instr = instrs.next();
				InstructionID instrID = instr.getInstructionID();

				if(instrID != InstructionID.PHI_NODE_INST)
					continue;

				PhiNode phiNode = (PhiNode) instr;
				Constant increment = null;
				Constant factor = null;
				BinaryOperatorID indVarOp = null;
				boolean isPostOperator = true;
				int numOperands = phiNode.getNumOperands();

				BinaryOperator binOperator = null;
				
				for(int i = 0; i < numOperands; i++){
					try{
						BasicBlock pred = phiNode.getIncomingBlock(i);
						Value incomingVal = phiNode.getIncomingValue(i);
						if(nodesInLoop.contains(pred)){
							// The predecessor is in the loop
							if(!(incomingVal instanceof BinaryOperator))
								continue instrLoop;

							binOperator = (BinaryOperator) incomingVal;
							BinaryOperatorID binOpID = binOperator.getOpCode();
							if(!(binOpID.isAddition() || binOpID.isMultiplication()
									|| binOpID.isSubtraction()))
								continue instrLoop;

							Value operand1 = binOperator.getOperand(0);
							Value operand2 = binOperator.getOperand(1);
							if(operand1.isAConstant()){
								if(operand2 != phiNode)
									continue instrLoop;
								else{
									// Identified the basic induction variable
									if(binOpID.isMultiplication())
										factor = (Constant) operand1;
									else
										increment = (Constant) operand1;
									indVarOp = binOpID;
									isPostOperator = false;
								}
							}
							else if(operand2.isAConstant()){
								if(operand1 != phiNode)
									continue instrLoop;
								else{
									// Identified the basic induction variable
									if(binOpID.isMultiplication())
										factor = (Constant) operand2;
									else
										increment = (Constant) operand2;
									indVarOp = binOpID;
								}
							}
							else
								continue instrLoop;
						}
						else{
							// The predecessor is outside the loop
							if(!incomingVal.isAConstant())
								continue instrLoop;
						}
					}
					catch(Exception e){
						e.printStackTrace();
						System.exit(-1);
					}
				}

				// This instruction defines a basic induction variable
				InductionVariable newBasicIndVar = new InductionVariable(phiNode, null, factor,
						increment, isPostOperator, indVarOp, loopInfo);
				newBasicIndVar.setIncrementor(binOperator);
				basicIndVars.add(newBasicIndVar);
			}
		}

		return basicIndVars;
	}


	private void updateIndVarMap(InductionVariable newIndVar) {
		List<InductionVariable> dependentIndVars = null;
		InductionVariable fundIndVarOfNewIndVar = newIndVar.getBaseIndVar();
		
		if(fundIndVarOfNewIndVar == null){
			// This is a fundamental ind var
			dependentIndVars = 
				new ArrayList<InductionVariable>();
			dependentIndVars.add(newIndVar);
			basicIndVarAndDependentIndVars.put(newIndVar, dependentIndVars);
		}
		else{
			InductionVariable fundamentalIndVar = 
					newIndVar.getFundamentalIndVar();
			dependentIndVars = basicIndVarAndDependentIndVars.get(fundamentalIndVar);
			if(dependentIndVars == null){
				dependentIndVars = new ArrayList<InductionVariable>();
			}
			dependentIndVars.add(newIndVar);
			basicIndVarAndDependentIndVars.put(fundamentalIndVar, dependentIndVars);
		}
	}

	public HashMap<Value, InductionVariable> getValueAndIndVars() {
		return valueAndIndVars;
	}
	
	public HashMap<InductionVariable, List<InductionVariable>> getInductionVarMap(){
		return basicIndVarAndDependentIndVars;
	}
}

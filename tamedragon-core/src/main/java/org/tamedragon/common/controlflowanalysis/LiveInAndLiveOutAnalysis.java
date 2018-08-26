package org.tamedragon.common.controlflowanalysis;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tamedragon.common.DominatorTree;
import org.tamedragon.common.llvmir.instructions.BinaryOperator;
import org.tamedragon.common.llvmir.instructions.GetElementPtrInst;
import org.tamedragon.common.llvmir.instructions.ICmpInst;
import org.tamedragon.common.llvmir.instructions.Instruction;
import org.tamedragon.common.llvmir.instructions.BinaryOperator.BinaryOperatorID;
import org.tamedragon.common.llvmir.instructions.CmpInst.Predicate;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.Value;

public class LiveInAndLiveOutAnalysis {
	private Map<BasicBlock, List<Instruction>> evalList = null;
	private Map<BasicBlock, List<Instruction>> killList = null;
	private Map<BasicBlock, Set<Instruction>> availExpIn = new LinkedHashMap<BasicBlock, Set<Instruction>>();
	private Map<BasicBlock, List<Instruction>> availExpOut = new LinkedHashMap<BasicBlock, List<Instruction>>();	
	DominatorTree dominatorTree = null;
	
	private static final Logger LOG = LoggerFactory.getLogger(LiveInAndLiveOutAnalysis.class);
	
	public LiveInAndLiveOutAnalysis(Function function){
		DominatorCalculationContext dominatorCalculationContext =
			new DominatorCalculationContext(function);
		//dominatorCalculationContext.setIterativeMethod();
		dominatorCalculationContext.setLengauerTarjan(null);
		dominatorTree = dominatorCalculationContext.getDominatorTree();
		
		EvalAndKillExprAnalysis evalAndKillExprAnalysis = new EvalAndKillExprAnalysis(function);
		evalList = evalAndKillExprAnalysis.getEvalList();
		killList = evalAndKillExprAnalysis.getKillList();
		populateUniversalSetOfExprsAndInitializeAvailExprs(function);
		populateAvailableExprOut(function);
		populateAvailableExpIn(function);
	}
	
	private void populateUniversalSetOfExprsAndInitializeAvailExprs(Function function){
		Set<Instruction> universalSetOfExprs = new LinkedHashSet<Instruction>();
		Iterator<BasicBlock> basicBlockIterator1 = function.basicBlocksIterator();
		while(basicBlockIterator1.hasNext()) {
			BasicBlock basicBlock = basicBlockIterator1.next();
			List<Instruction> instructions = evalList.get(basicBlock);

			for(Instruction instruction : instructions){

				if(instruction instanceof BinaryOperator || instruction instanceof ICmpInst || instruction instanceof GetElementPtrInst){
					int equivalentIndex = equivalentExpressionIndex(universalSetOfExprs.iterator(), instruction);
					if(equivalentIndex < 0){
						universalSetOfExprs.add(instruction);
					}
				}
			}
		}
		
		Iterator<BasicBlock> basicBlockIterator2 = function.basicBlocksIterator();
		while(basicBlockIterator2.hasNext()) {
			BasicBlock basicBlock = basicBlockIterator2.next();
			availExpIn.put(basicBlock, universalSetOfExprs);
		}
	}

	private void populateAvailableExprOut(Function function){
		Iterator<BasicBlock> basicBlockIterator = function.basicBlocksIterator();
		while(basicBlockIterator.hasNext()) {
			BasicBlock basicBlock = basicBlockIterator.next();
			// Removing KilledExprs from AvailableInExprs
			Set<Instruction> availInExprsMinusKillExprs = null;
			Set<Instruction> availExprs = availExpIn.get(basicBlock);
			List<Instruction> killedExprs = killList.get(basicBlock);
			availExprs.removeAll(killedExprs);
			availInExprsMinusKillExprs = availExprs;
			List<Instruction> evalInstructions = evalList.get(basicBlock);

			// Union of 'evalInstructions' and 'availInExprsMinusKillExprs'
			Set<Instruction> unionSet = new LinkedHashSet<Instruction>();

			for(int i = 0; i < evalInstructions.size(); i++){
				unionSet.add(evalInstructions.get(i));
			}
			Iterator<Instruction> iterator = availInExprsMinusKillExprs.iterator();
			while(iterator.hasNext()){
				Instruction instruction = iterator.next();
				int equivalentIndex = equivalentExpressionIndex(unionSet.iterator(), instruction);
				if(equivalentIndex < 0){
					unionSet.add(instruction);
				}
			}
			List<Instruction> availExprOut = new ArrayList<Instruction>(unionSet);
			availExpOut.put(basicBlock, availExprOut);
		}
	}
	
	/**
	 * This function checks if the expression passed in as a second argument is present or not 
	 * in the given collection of expressions, which is passed as a first argument, based on the operands and the operator. 
	 * It also sets the equivalentExprIndex to keep track of the equivalent Expression
	 * It return true if either both expressions objects are equal, 
	 * i.e based on the java == operator or if they have the same operands and operator.
	 * @param collectionOfExprs
	 * @param instruction
	 * @return
	 */
	public int equivalentExpressionIndex(Iterator<Instruction> iterator,
					Instruction instruction) {
		Value op1 = instruction.getOperand(0);
		Value op2 = instruction.getOperand(1);
		
		
			
		int indexOfEqivalentExpr = -1;
		int count  = 0;
		while(iterator.hasNext()){
			Instruction instruction2 = iterator.next();
			if(instruction == instruction2){
				indexOfEqivalentExpr = count;
			}
			else if(instruction2 instanceof BinaryOperator || instruction2 instanceof ICmpInst){
				Value oprnd1 = instruction2.getOperand(0);
				Value oprnd2 = instruction2.getOperand(1);
				
				if(instruction2 instanceof BinaryOperator && instruction instanceof BinaryOperator){
					BinaryOperator binaryOperator = (BinaryOperator)instruction;
					BinaryOperator binaryOperator2 = (BinaryOperator)instruction2;

					BinaryOperatorID binaryOperatorID = binaryOperator.getOpCode();
					BinaryOperatorID binaryOperatorID2 = binaryOperator2.getOpCode();

					if(((op1.equals(oprnd1) && op2.equals(oprnd2)) || (op1.equals(oprnd2) 
							&& op2.equals(oprnd1))) && binaryOperatorID == binaryOperatorID2){
						indexOfEqivalentExpr = count;
					}
					
				}
				else if(instruction2 instanceof ICmpInst && instruction instanceof ICmpInst){
					ICmpInst cmpInst = (ICmpInst)instruction;
					ICmpInst cmpInst2 = (ICmpInst)instruction2;

					Predicate predicate = cmpInst.getPredicate();
					Predicate predicate2 = cmpInst2.getPredicate();

					if(op1.equals(oprnd1) && op2.equals(oprnd2) && predicate == predicate2){
						indexOfEqivalentExpr = count;
					}
				}
			}
			else if(instruction2 instanceof GetElementPtrInst && instruction instanceof GetElementPtrInst){
				
				if(instruction2.toString().equals("%23 = getelementptr inbounds [100 x i32]* %array, i32 0, i32 %middle.0") &&
						"%17 = getelementptr inbounds [100 x i32]* %array, i32 0, i32 %middle.0".equals(instruction.toString())){
					LOG.debug("WAIT HERE");
				}
				
				int numOfOperands = instruction2.getNumOperands();
				int numOfOprnds = instruction.getNumOperands();
				boolean foundMatch = true;
				while(numOfOperands == numOfOprnds && numOfOperands != 0){
					Value oprnd1 = instruction.getOperand(numOfOprnds - 1);
					Value oprnd2 = instruction2.getOperand(numOfOperands - 1);
					
					if(!oprnd1.equals(oprnd2)){
						foundMatch = false;
						break;
					}
					
					numOfOperands--;
					numOfOprnds--;
				}	

				if(foundMatch){
					indexOfEqivalentExpr = count;
					return indexOfEqivalentExpr;
				}
			}
			count++;
		}
		
		return indexOfEqivalentExpr;
	}
	
	private void populateAvailableExpIn(Function function){
		Iterator<BasicBlock> basicBlockIterator = function.basicBlocksIterator();
		while(basicBlockIterator.hasNext()) {
			BasicBlock bb = basicBlockIterator.next();
			Set<Instruction> availInstrs = new LinkedHashSet<Instruction>();
			List<BasicBlock> predecessors = function.getCfg().getPredecessors(bb);
			int count = 0;
			BasicBlock currentNode = null;
			for(BasicBlock node : predecessors){
				BasicBlock basicBlock = (BasicBlock)node;
				List<Instruction>  availExpOutInstrs = null;
				
				if(count != 0 && !(dominatorTree.dominates(currentNode, basicBlock) || dominatorTree.dominates(basicBlock, currentNode))){
					continue;
				}
				else{
					availExpOutInstrs = availExpOut.get(basicBlock);
					availInstrs.addAll(availExpOutInstrs);
					currentNode = basicBlock;
				}
				count++;
			}
			availExpIn.put(bb, availInstrs);
		}
	}
	
	public Map<BasicBlock, List<Instruction>> getEvalList() {
		return evalList;
	}

	public Map<BasicBlock, List<Instruction>> getKillList() {
		return killList;
	}

	public Map<BasicBlock, Set<Instruction>> getAvailExpIn() {
		return availExpIn;
	}

	public Map<BasicBlock, List<Instruction>> getAvailExpOut() {
		return availExpOut;
	}
}

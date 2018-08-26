package org.tamedragon.common.optimization;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tamedragon.common.DominatorTree;
import org.tamedragon.common.controlflowanalysis.DominatorCalculationContext;
import org.tamedragon.common.controlflowanalysis.LiveInAndLiveOutAnalysis;
import org.tamedragon.common.llvmir.instructions.BinaryOperator;
import org.tamedragon.common.llvmir.instructions.GetElementPtrInst;
import org.tamedragon.common.llvmir.instructions.ICmpInst;
import org.tamedragon.common.llvmir.instructions.Instruction;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionCreationException;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.Value;
import org.tamedragon.common.llvmir.utils.LLVMIREmitter;

public class GlobalCSE {
	private DominatorTree dominatorTree = null;
	private boolean isChanged = false;
	
	private static final Logger LOG = LoggerFactory.getLogger(GlobalCSE.class);

	public void execute(Function function) throws InstructionCreationException{
		isChanged = false;
				
		DominatorCalculationContext dominatorCalculationContext =  new DominatorCalculationContext(function);
		
		dominatorCalculationContext.setIterativeMethod();
		//dominatorCalculationContext.setLengauerTarjan(null);
		
		dominatorTree = dominatorCalculationContext.getDominatorTree();
		
		if(LOG.isDebugEnabled()){
			dominatorTree.print();
		}
		
		LiveInAndLiveOutAnalysis liveInAndLiveOutAnalysis = new LiveInAndLiveOutAnalysis(function);
		Map<BasicBlock, List<Instruction>> evalList = liveInAndLiveOutAnalysis.getEvalList();
		Map<BasicBlock, Set<Instruction>> availExpIn = liveInAndLiveOutAnalysis.getAvailExpIn();
		
		Map<Instruction,Map<Integer,Integer>> availAndItsCoOrdinates = new LinkedHashMap<Instruction, Map<Integer,Integer>>();
		int indexOfBB = 0;
		Iterator<BasicBlock> basicBlockIterator = function.basicBlocksIterator();
		while(basicBlockIterator.hasNext()) {
			BasicBlock bb = basicBlockIterator.next();
			Set<Instruction> availInExprs = null;
			
			if(indexOfBB == 0)
				availInExprs = new LinkedHashSet<Instruction>(evalList.get(bb));
			else
				availInExprs = availExpIn.get(bb);
			
			loopinThroughAvailInExprs : for(Instruction availInExpr : availInExprs){
				
				BasicBlock parentOfAvailableExpr = availInExpr.getParent();
				boolean isDominates = dominatorTree.dominates(parentOfAvailableExpr, bb);
				if(!isDominates)
					continue;
				Map<Integer,Integer> coordinates = null;

				if(!availAndItsCoOrdinates.containsKey(availInExpr))
					coordinates = new LinkedHashMap<Integer, Integer>();
				else
					coordinates = availAndItsCoOrdinates.get(availInExpr);
				
				Iterator<Instruction> instrsIterator = bb.getInstructions();
				int index = liveInAndLiveOutAnalysis.equivalentExpressionIndex(instrsIterator, availInExpr);
				
				if(index >= 0){
					if(availInExpr instanceof BinaryOperator || availInExpr instanceof ICmpInst 
							|| availInExpr instanceof GetElementPtrInst){
						
						int numOfOprnds = availInExpr.getNumOperands();
						
						Iterator<Instruction> listOfInstrs = bb.getInstructionSubset(index +1);
						
						//for(int i = index  + 1; i < listOfInstrs.size(); i++){
						//	Instruction instruction = listOfInstrs.get(i);
						while(listOfInstrs.hasNext()){
							Instruction instruction = listOfInstrs.next();
							while(numOfOprnds != 0){
								Value oprnd = availInExpr.getOperand(numOfOprnds - 1);
								if(oprnd == instruction)
									continue loopinThroughAvailInExprs;
								numOfOprnds--;
							}
						}
						// So its a common global subexpression, now check for the occurrence of these expressions
						// i.e the basic block number and the position of the instruction inside that basic block
						coordinates.put(indexOfBB, index);
					}
				}
				availAndItsCoOrdinates.put(availInExpr, coordinates);
			}
			indexOfBB++;
		}
		
		// Last Step is to do the replacement
		Set<Entry<Instruction, Map<Integer, Integer>>> entries = availAndItsCoOrdinates.entrySet();
		Iterator<Entry<Instruction, Map<Integer, Integer>>> iterator = entries.iterator();
		Set<Instruction> setOfInstrToBeRemoved = new HashSet<Instruction>();		
		
		while(iterator.hasNext()){
			
			Entry<Instruction, Map<Integer, Integer>> entry = iterator.next();
			Map<Integer, Integer> mapOfBBVsInstrPos = entry.getValue();
			Instruction instruction = entry.getKey();
			Set<Entry<Integer, Integer>> entries2 = mapOfBBVsInstrPos.entrySet();
			Iterator<Entry<Integer, Integer>> iterator2 = entries2.iterator();
			int count = 0;
		
			while(entries2.size() > 1 && iterator2.hasNext()){
				Entry<Integer, Integer> entry2 = iterator2.next();
				Integer bbIndex = entry2.getKey();
				Integer instrIndex = entry2.getValue();
				if(count > 0){
					BasicBlock basicBlock = function.getBasicBlockAt(bbIndex);
					Instruction instructn = basicBlock.getInstructionAt(instrIndex);
					
					setOfInstrToBeRemoved.add(instructn);
					int noOfUsers = instructn.getNumUses();
					while(noOfUsers != 0){
						Value value = instructn.getUserAt(noOfUsers-1);
						if(value instanceof Instruction){
							Instruction instruction2 = (Instruction) value;
							int nosOfOprs = instruction2.getNumOperands();
							while(nosOfOprs != 0){
								Value value2 = instruction2.getOperand(nosOfOprs-1);
								if(value2 == instructn)
									instruction2.setOperand(nosOfOprs - 1, instruction);
								nosOfOprs--;
							}
						}
						noOfUsers--;
					}
				}				
				count++;
			}
		}
		
		// Now remove the instructions
		Iterator<Instruction> setiterator = setOfInstrToBeRemoved.iterator();
		while(setiterator.hasNext()){
			isChanged = true;
			Instruction instruction = setiterator.next();
			instruction.getParent().removeInstructionFromBasicBlock(instruction);
		}
		if(isChanged){
			LocalCSE localCSE = new LocalCSE();
			basicBlockIterator = function.basicBlocksIterator();
			while(basicBlockIterator.hasNext()) {
				localCSE.localCSE(basicBlockIterator.next());
			}
			execute(function);
		}
	}
}


package org.tamedragon.common.optimization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.jgraph.graph.DefaultEdge;
import org.tamedragon.common.llvmir.instructions.BranchInst;
import org.tamedragon.common.llvmir.instructions.CFG;
import org.tamedragon.common.llvmir.instructions.Instruction;
import org.tamedragon.common.llvmir.instructions.LLVMUtility;
import org.tamedragon.common.llvmir.instructions.TerminatorInst;
import org.tamedragon.common.llvmir.instructions.Instruction.InstructionID;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionDetailAccessException;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionUpdateException;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.Type;
import org.tamedragon.common.llvmir.types.Value;
import org.tamedragon.common.llvmir.types.Value.ValueTypeID;

public class TailMerging {

	private static final short MIN_INSTRUCTIONS_TO_MATCH = 1;

	private boolean isChanged = false;

	private CFG cfg;
	private Function function;

	private Map<Instruction, List<Instruction>> commonInstrVsListOfCommonInstr = 
		new HashMap<Instruction, List<Instruction>>(); 

	public Transformations execute(Function function) throws InstructionUpdateException, InstructionDetailAccessException{
		Transformations transformations = new Transformations(Transformations.TAIL_MERGING);

		CFG cfg = function.getCfg();
		this.cfg = cfg;
		this.function = function;

		do{
			isChanged = false;
			List<BasicBlock> tempBBList = new ArrayList<BasicBlock>();

			Iterator<BasicBlock> basicBlockIterator = function.basicBlocksIterator();
			while(basicBlockIterator.hasNext()) {
				tempBBList.add(basicBlockIterator.next());
			}

			for(BasicBlock basicBlock : tempBBList){
				List<BasicBlock> predecessors = cfg.getPredecessors(basicBlock);

				if(predecessors == null || predecessors.size() <= 1){
					continue;
				}

				if(!allPredecessorsAreSiblings(predecessors)){
					continue;
				}

				// This predecessor has more than predecessor; check if some or all predecessors have
				// similar last instructions
				List<BasicBlocksWithSimilarLastFewInstructions> basicBlocksWithSimilarLastFewInstructions
				= getbasicBlocksWithSimilarLastFewInstructions(predecessors);

				if(!basicBlocksWithSimilarLastFewInstructions.isEmpty()){
					modifyFlowGraph(basicBlocksWithSimilarLastFewInstructions, basicBlock);
					isChanged = true;
				}
			}
		}
		while(isChanged);


		//		List<BasicBlock> tempBBList = new ArrayList<BasicBlock>();
		//
		//		Iterator<BasicBlock> basicBlockIterator = function.basicBlocksIterator();
		//		while(basicBlockIterator.hasNext()) {
		//			tempBBList.add(basicBlockIterator.next());
		//		}
		//
		//		int nosOfBBs = tempBBList.size();
		//		int countBB = 0;
		//
		//		for(int indx = 0; indx < tempBBList.size() && countBB < nosOfBBs; indx++, countBB++){
		//
		//			BasicBlock basicBlock = tempBBList.get(countBB);
		//
		//			List<Instruction> globalInstrs = new ArrayList<Instruction>();
		//			Set<BasicBlock> bbset = new HashSet<BasicBlock>();
		//			List<BasicBlock> predecessors = cfg.getPredecessors(basicBlock);
		//			
		//			Vector<BasicBlock> temp1Predessors = new Vector<BasicBlock>(predecessors);
		//			int nosOfPredecessors = predecessors.size();
		//
		//			for(int predecessorIndex = 0; 
		//					temp1Predessors.size() > 1 && predecessorIndex < nosOfPredecessors; predecessorIndex++){
		//
		//				BasicBlock predecessor = predecessors.get(predecessorIndex);
		//				bbset.add(predecessor);
		//				int anotherPredecessorIndex = 0;
		//
		//				for(int count1 = 0; count1 < temp1Predessors.size() && anotherPredecessorIndex < nosOfPredecessors; count1++, 
		//						anotherPredecessorIndex++){
		//
		//					BasicBlock otherPredecessor = predecessors.get(anotherPredecessorIndex);
		//
		//					if(!bbset.add(otherPredecessor))
		//						continue;
		//
		//					List<Instruction> commonInstrs = compareBB(predecessor, otherPredecessor);
		//					if(commonInstrs.size() == otherPredecessor.getInstructions().size() 
		//							&& commonInstrs.size() == predecessor.getInstructions().size()){
		//
		//						// Now the predecessors of "otherPredecessorBB" should have "predecessorBB" as their successors
		//						List<BasicBlock> predecessorsOfOtherPredecessorBB = cfg.getPredecessors(otherPredecessor);
		//						for(BasicBlock predesor : predecessorsOfOtherPredecessorBB){
		//
		//							DefaultEdge edge = cfg.getEdge(predesor, predecessor);
		//							if(edge == null){
		//								cfg.addEdge(predesor, predecessor, new DefaultEdge());
		//							}
		//						}
		//
		//						// And the sucessors of "otherPredecessorBB" should have "predecessorBB" as their predecessor
		//						List<BasicBlock> sucessorsOfOtherPredecessorBB = cfg.getSuccessors(otherPredecessor);
		//						for(BasicBlock sucesor : sucessorsOfOtherPredecessorBB){
		//							DefaultEdge edge = cfg.getEdge(predecessor, sucesor);
		//							if(edge == null){
		//								cfg.addEdge(predecessor, sucesor, new DefaultEdge());
		//							}
		//						}
		//
		//						// Remove otherPredecessor
		//						predecessorsOfOtherPredecessorBB = cfg.getPredecessors(otherPredecessor);
		//						function.removeBasicBlockFromFunction(otherPredecessor);
		//
		//						nosOfPredecessors--;
		//						nosOfBBs--;
		//
		//						otherPredecessor.getValue().replaceAllUsesOfThisWith(predecessor.getValue());
		//
		//						isChanged = true;
		//					}
		//
		//					if(predecessorIndex == 0)
		//						globalInstrs.addAll(commonInstrs);
		//
		//					else{
		//						Iterator<Instruction> globalIterator = globalInstrs.iterator();
		//
		//						while (globalIterator.hasNext()) {
		//							Instruction instruction = globalIterator.next();
		//							boolean isSame = false;
		//							for(Instruction commonInstruction : commonInstrs){
		//								isSame = LLVMUtility.compareInstructions(instruction, commonInstruction);
		//
		//								if(isSame)
		//									break;
		//							}
		//
		//							if(!isSame)
		//								globalIterator.remove();
		//						}
		//					}
		//				}
		//			}
		//			
		//			// Now move all the instructions in globalInstrs to the current BasicBlock
		//			globalInstrIterator : for(Instruction instruction : globalInstrs){
		//				if(!(instruction instanceof BranchInst)){
		//
		//					int numOfUsers = instruction.getNumUses();
		//
		//					for(int j = 0; j < numOfUsers; j++){
		//						Value user = instruction.getUserAt(j);
		//						// If any user of this instruction is also a instruction within the same basic block then don't remove it.
		//						if(user instanceof Instruction){
		//							Instruction ins = (Instruction) user;
		//							if(ins.getParent() == instruction.getParent())
		//								continue globalInstrIterator;
		//						}
		//					}
		//
		//					List<Instruction> listOfInstrs = commonInstrVsListOfCommonInstr.get(instruction);
		//
		//					for(Instruction instruction2 : listOfInstrs)
		//						instruction2.getParent().getInstructions().remove(instruction2);
		//
		//					instruction.getParent().getInstructions().remove(instruction);
		//					basicBlock.getInstructions().add(0, instruction);
		//					instruction.setParent(basicBlock);
		//					isChanged = true;
		//				}
		//			}
		//			
		//			// reset the map
		//			commonInstrVsListOfCommonInstr = new HashMap<Instruction, List<Instruction>>(); 
		//		}
		//
		//		basicBlockIterator = function.basicBlocksIterator();
		//		while(basicBlockIterator.hasNext()) {
		//			BasicBlock basicBlock = basicBlockIterator.next();
		//			Vector<Instruction> instructions = basicBlock.getInstructions();
		//			for(Instruction instruction : instructions){
		//				if(instruction instanceof PhiNode){
		//					PhiNode phiNode = (PhiNode)instruction;
		//					LLVMUtility.removePhiNodeIfIncomingValuesAreSame(phiNode);
		//				}
		//			}
		//		}
		//
		//		if(isChanged){
		//			isChanged = false;
		//			commonInstrVsListOfCommonInstr = new HashMap<Instruction, List<Instruction>>(); 
		//			execute(function);
		//		}

		return transformations;
	}

	private boolean allPredecessorsAreSiblings(List<BasicBlock> predecessors) {

		for(int i = 0; i < predecessors.size() -1; i++){
			BasicBlock predecessor = predecessors.get(i);

			for(int j = i +1; j < predecessors.size(); j++){
				BasicBlock nextPredecessor = predecessors.get(j);
				if(pathExists(predecessor, nextPredecessor)){
					return false;
				}
			}
		}
		return true;
	}

	private boolean pathExists(BasicBlock basicBlock,
			BasicBlock otherBasicBlock) {
		if(cfg.getEdge(basicBlock, otherBasicBlock) != null){
			return true;
		}

		List<BasicBlock> successors = cfg.getSuccessors(basicBlock);
		for(BasicBlock successor : successors){
			if(pathExists(successor, otherBasicBlock)){
				return true;
			}
		}

		return false;
	}

	private void modifyFlowGraph(
			List<BasicBlocksWithSimilarLastFewInstructions> basicBlocksWithSimilarLastFewInstructions,
			BasicBlock target)
	throws InstructionUpdateException, InstructionDetailAccessException {

		for(BasicBlocksWithSimilarLastFewInstructions commonBBsWithSimilarLastInstrs : basicBlocksWithSimilarLastFewInstructions){
			List<BasicBlock> basicBlocks = commonBBsWithSimilarLastInstrs.getBasicBlocks();
			List<Instruction> subListOfCommonInstrs = commonBBsWithSimilarLastInstrs.getSubListOfSimilarInstructions();

			List<BasicBlock> bbsWithNonCommonInstrs = new ArrayList<BasicBlock>();
			List<BasicBlock> bbsWithCommonInstrs = new ArrayList<BasicBlock>();
			for(BasicBlock basicBlock : basicBlocks){
				if(basicBlock.numInstructions() -1 > subListOfCommonInstrs.size()){
					bbsWithNonCommonInstrs.add(basicBlock);
				}
				else{
					bbsWithCommonInstrs.add(basicBlock);
				}
			}

			if(bbsWithCommonInstrs.size() > 1){
				// There is at least one basic block that contains all instructions in other sibling
				// basic blocks with common instructions.
				int numSiblings = basicBlocks.size();
				BasicBlock newTarget = bbsWithCommonInstrs.get(0);
				for(int i = 1; i < numSiblings; i++){
					BasicBlock sibling = basicBlocks.get(i);
					if(bbsWithCommonInstrs.contains(sibling)){
						removeBasicBlockThatIsSimilarTo(sibling, newTarget);
					}
					else{
						replacePredecessorsOfTargetWith(newTarget, sibling, false);
						removeRedundantInstructionsInBasicBlock(sibling, subListOfCommonInstrs, target);
					}
				}
			}
			else{
				// There is no basic block that contains all instructions common to other siblings; create 
				// a new basic block and insert between the siblings and the target. Also remove additional
				// instructions from the siblings
				BasicBlock newBBWithCommonInstrs = new BasicBlock(function);
				newBBWithCommonInstrs.setName("CommonInstructions");
				for(Instruction instruction : subListOfCommonInstrs){
					newBBWithCommonInstrs.addInstruction(instruction);
				}

				for(BasicBlock basicBlock : basicBlocks){
					removeRedundantInstructionsInBasicBlock(basicBlock, subListOfCommonInstrs, target);
				}

				function.insertNewNodeBetween(basicBlocks, target, newBBWithCommonInstrs);
			}
		}
	}

	/**
	 * Removes the basic block that is passed in the first argument. Note that this slightly
	 * different from function.removeDeadBasicBlock() since the users are the instructions in 
	 * the basic block that is going to be removed are converted to users of the corresponding
	 * instructions in the reference basic block passed in the second argument.
	 * @throws InstructionUpdateException 
	 * @throws InstructionDetailAccessException 
	 */

	private boolean removeBasicBlockThatIsSimilarTo(BasicBlock basicBlockToBeRemoved,
			BasicBlock referenceBasicBlock) throws InstructionDetailAccessException, InstructionUpdateException {

		if(!function.removeBasicBlock(basicBlockToBeRemoved)){
			return false;
		}

		// For each instruction in the basic block, make the user of the instruction as the user
		// of the corresponding instruction in the reference basic block
		Iterator<Instruction> instructions = basicBlockToBeRemoved.getInstructions();
		int count = 0;
		while(instructions.hasNext()){
			Instruction instruction = instructions.next();
			instruction.replaceAllUsesOfThisWith(referenceBasicBlock.getInstructionAt(count));
			count++;
		}

		basicBlockToBeRemoved.removeAllInstructions();
		return true;
	}

	private void removeRedundantInstructionsInBasicBlock(BasicBlock basicBlock,
			List<Instruction> subListOfCommonInstrs, BasicBlock destination) {

		int indexIntoBasicBlock = basicBlock.numInstructions() -2;
		for(int i = subListOfCommonInstrs.size() -1; i >= 0 ; i--){
			Instruction instr = basicBlock.getInstructionAt(indexIntoBasicBlock);

			Instruction instrInCommonInstrList = subListOfCommonInstrs.get(i);
			int numUses = instrInCommonInstrList.getNumUses();
			for(int j = 0; j < numUses; j++){
				Value use = instrInCommonInstrList.getUserAt(j);
				if(use.getValueTypeID() == ValueTypeID.INSTRUCTION && 
						((Instruction)use).getInstructionID() == InstructionID.PHI_NODE_INST
						&& ((Instruction)use).getParent() == destination){
					Instruction useInstruction = (Instruction)use;
					destination.removeInstruction(useInstruction);
					useInstruction.replaceAllUsesOfThisWith(instrInCommonInstrList);
				}
			}

			if(instr != instrInCommonInstrList){
				instr.replaceAllUsesOfThisWith(instrInCommonInstrList);
			}

			basicBlock.removeInstruction(instr);

			indexIntoBasicBlock--;
		}
	}

	/**
	 * Replaces the target of the predecessors of the given basic block (target)
	 * with the new target. It is assumed that the new target is already part of the
	 * control flow graph.
	 * 
	 * If the removeTarget flag is true, this function will remove the target from the cfg.
	 */

	private boolean replacePredecessorsOfTargetWith(BasicBlock target, BasicBlock newTarget, boolean removeTarget) 
	throws InstructionUpdateException, InstructionDetailAccessException
	{
		List<BasicBlock> predecessors = cfg.getPredecessors(target);

		for(BasicBlock predecessor : predecessors){
			DefaultEdge edgeToBeRemoved = cfg.getEdge(predecessor, target);
			if(!cfg.removeEdge(edgeToBeRemoved)){
				// TODO Log warning here
				return false;
			}

			if(!cfg.addEdge(predecessor, newTarget, new DefaultEdge())){
				// TODO Log warning here
				return false;
			}

			TerminatorInst terminatorInstr = predecessor.getLastInstruction();
			terminatorInstr.replaceSuccessorWith(target, newTarget);

			if(removeTarget){
				if(!cfg.removeVertex(target)){
					// TODO Log warning here
					return false;
				}
			}
		}
		return true;
	}


	private List<BasicBlocksWithSimilarLastFewInstructions> getbasicBlocksWithSimilarLastFewInstructions(
			List<BasicBlock> predecessors) throws InstructionUpdateException, InstructionDetailAccessException {
		List<BasicBlocksWithSimilarLastFewInstructions> identifiedCommonInstrs = new ArrayList<BasicBlocksWithSimilarLastFewInstructions>();

		outer: for(int i = 0; i < predecessors.size() -1; i++){
			BasicBlock pred = predecessors.get(i);
			for(BasicBlocksWithSimilarLastFewInstructions commonInstrBBs : identifiedCommonInstrs){
				if(commonInstrBBs.canGroup(pred)){
					i++;
					continue outer;
				}
			}

			outer1: for(int j = i +1; j < predecessors.size(); j++){
				BasicBlock nextPred = predecessors.get(j);
				for(BasicBlocksWithSimilarLastFewInstructions commonInstrBBs : identifiedCommonInstrs){
					if(commonInstrBBs.canGroup(nextPred)){
						continue outer1;
					}
				}

				List<Instruction> lastCommonInstructions = lastInstructionsMatch( pred, nextPred);

				if(!lastCommonInstructions.isEmpty()){
					List<BasicBlock> newGroup = new ArrayList<BasicBlock>();
					newGroup.add(pred);
					newGroup.add(nextPred);
					BasicBlocksWithSimilarLastFewInstructions bbsWithSimilarLastFewInstrs 
					= new BasicBlocksWithSimilarLastFewInstructions(newGroup, lastCommonInstructions);
					identifiedCommonInstrs.add(bbsWithSimilarLastFewInstrs);
				}
			}
		}

		return identifiedCommonInstrs;
	}

	/**
	 * Returns the maximal sublist that match from the end of each list
	 * @param firstList
	 * @param otherList
	 * @return
	 * @throws InstructionDetailAccessException 
	 * @throws InstructionUpdateException 
	 */
	private List<Instruction> lastInstructionsMatch(BasicBlock basicBlock, BasicBlock otherBasicBlock) 
	throws InstructionUpdateException, InstructionDetailAccessException{

		List<Instruction> matchingSubList = new ArrayList<Instruction>();

		BasicBlock bbWithLesserInstructions = basicBlock;
		BasicBlock bbWithMoreInstructions = otherBasicBlock;
		if(bbWithLesserInstructions.numInstructions() > otherBasicBlock.numInstructions()){
			bbWithLesserInstructions = otherBasicBlock;
			bbWithMoreInstructions = basicBlock;
		}

		int numMatches = 0;
		int currentIndexInSmallBB = bbWithLesserInstructions.numInstructions() -2;
		int currentIndexInLargerBB = bbWithMoreInstructions.numInstructions() -2;

		boolean isSame = true;
		while(isSame && currentIndexInSmallBB >= 0){
			Instruction instr = bbWithLesserInstructions.getInstructionAt(currentIndexInSmallBB);
			Instruction instrInOtherBasicBlock = bbWithMoreInstructions.getInstructionAt(currentIndexInLargerBB);

			isSame = LLVMUtility.compareInstructions(instr, instrInOtherBasicBlock);
			if(isSame){
				numMatches++;
				matchingSubList.add(instr);
			}
			else{
				break;
			}

			currentIndexInSmallBB-- ; currentIndexInLargerBB--;
		}

		return matchingSubList;
	}

	private List<Instruction> lastInstructionsMatch(List<Instruction> instructions,  BasicBlock basicBlock) 
			throws InstructionUpdateException, InstructionDetailAccessException{
		
		List<Instruction> matchingSubList = new ArrayList<Instruction>();
		
		boolean isSame = true;
		int indexInSmallerList = 0;
		int indexInLargerList = 0;
		int numMatches = 0;
		boolean numInstrsInBBIsLarger = true;
		if(instructions.size() <= basicBlock.numInstructions()){			
			indexInSmallerList = instructions.size() -2;
			indexInLargerList = basicBlock.numInstructions() - 2;			
		}
		else{
			numInstrsInBBIsLarger = false;
			indexInSmallerList = basicBlock.numInstructions() - 2;
			indexInLargerList = instructions.size() -2;
		}
		
		while(isSame && indexInSmallerList >= 0){
			Instruction instr = null;
			Instruction otherInstr = null;
			
			if(numInstrsInBBIsLarger){
				instr = instructions.get(indexInSmallerList);
				otherInstr = basicBlock.getInstructionAt(indexInLargerList);
			}
			else{
				instr = basicBlock.getInstructionAt(indexInLargerList);  
				otherInstr = instructions.get(indexInSmallerList);
			}
			

			isSame = LLVMUtility.compareInstructions(instr, otherInstr);
			if(isSame){
				numMatches++;
				matchingSubList.add(instr);
			}
			else{
				break;
			}

			indexInSmallerList-- ; indexInLargerList--;
		}
		

		return matchingSubList;

	}
	
	private List<Instruction> compareBB(BasicBlock predecessor,	BasicBlock otherPredecessor) throws InstructionUpdateException, InstructionDetailAccessException {
		List<Instruction> commonInstrs = new ArrayList<Instruction>();
		Iterator<Instruction> predInstrs = predecessor.getInstructions();
		Iterator<Instruction> otherPredInstrs = otherPredecessor.getInstructions();

		if(predecessor.numInstructions() != otherPredecessor.numInstructions()){
			return commonInstrs;
		}

		while(predInstrs.hasNext()){
			Instruction predecessorInstr = predInstrs.next(); 
			List<Instruction> listOfInstr = new ArrayList<Instruction>();
			
			while(otherPredInstrs.hasNext()){
				Instruction otherPredecessorInstr = otherPredInstrs.next();
				boolean isSame = LLVMUtility.compareInstructions(otherPredecessorInstr, predecessorInstr);

				if(isSame && !commonInstrs.contains(predecessorInstr)){
					commonInstrs.add(predecessorInstr);
					listOfInstr.add(otherPredecessorInstr);
				}
			}
			commonInstrVsListOfCommonInstr.put(predecessorInstr, listOfInstr);
		}

		return commonInstrs;
	}

	private class BasicBlocksWithSimilarLastFewInstructions{

		private List<BasicBlock> basicBlocks;
		private List<Instruction> subListOfSimilarInstructions;

		public BasicBlocksWithSimilarLastFewInstructions( List<BasicBlock> basicBlocks, 
				List<Instruction> subListOfSimilarInstructions){
			this.basicBlocks = basicBlocks;
			this.subListOfSimilarInstructions = subListOfSimilarInstructions;
		}

		public boolean canGroup(BasicBlock basicBlock) throws InstructionUpdateException, InstructionDetailAccessException {

			List<Instruction> matchingSubList = lastInstructionsMatch(subListOfSimilarInstructions, basicBlock);

			if(matchingSubList.size() >= MIN_INSTRUCTIONS_TO_MATCH){
				basicBlocks.add(basicBlock);

				if(subListOfSimilarInstructions.size() > matchingSubList.size()){
					subListOfSimilarInstructions = matchingSubList;
				}

				return true;
			}
			return false;
		}

		public List<BasicBlock> getBasicBlocks() {
			return basicBlocks;
		}

		public void setBasicBlocks(List<BasicBlock> basicBlocks) {
			this.basicBlocks = basicBlocks;
		}

		public List<Instruction> getSubListOfSimilarInstructions() {
			return subListOfSimilarInstructions;
		}

		public void setSubListOfSimilarInstructions(
				List<Instruction> subListOfSimilarInstructions) {
			this.subListOfSimilarInstructions = subListOfSimilarInstructions;
		}

	}
}

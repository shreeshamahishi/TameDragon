package org.tamedragon.common.optimization;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.jgraph.graph.DefaultEdge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tamedragon.common.llvmir.instructions.BranchInst;
import org.tamedragon.common.llvmir.instructions.CFG;
import org.tamedragon.common.llvmir.instructions.Instruction;
import org.tamedragon.common.llvmir.instructions.PhiNode;
import org.tamedragon.common.llvmir.instructions.TerminatorInst;
import org.tamedragon.common.llvmir.instructions.Instruction.InstructionID;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionCreationException;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionDetailAccessException;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionUpdateException;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.Value;

public class BranchOptimization {

	private Function function;
	
	private static final Logger LOG = LoggerFactory.getLogger(BranchOptimization.class);

	public Transformations execute(Function function) throws 
					InstructionDetailAccessException, InstructionUpdateException, 
					InstructionCreationException, InstantiationException{

		this.function = function;

		Transformations transformations = new Transformations(Transformations.BRANCH_OPTIMIZATIONS);

		Iterator<BasicBlock> basicBlockIterator = function.modifiableBasicBlocksIterator();
		while(basicBlockIterator.hasNext()) {
			BasicBlock currentNode = basicBlockIterator.next();
			if(blockHasOnlyOneUnconditionalBranch(currentNode) && !(function.getStartNode() == currentNode)){
				LOG.info("Removing basic block = {}", currentNode);

				boolean removed = function.removeBasicBlockOnlyWithAnUnconditionalJump(currentNode);
				if(!removed){
					LOG.warn("Unable to remove basic block = {}", currentNode);
				}
			}
		}

		return transformations;
	}

	private boolean blockHasOnlyOneUnconditionalBranch(BasicBlock basicBlock) {
		if(basicBlock.numInstructions() != 1){
			LOG.warn("When checking if basic block has more only an unconditional branch, there are more than one instruction in basic block = {}", basicBlock);
			return false;
		}

		Instruction terminatorInstr = basicBlock.getLastInstruction();
		BranchInst branchInst = null;
		if(terminatorInstr.getInstructionID() == InstructionID.BRANCH_INST){
			branchInst = (BranchInst) terminatorInstr;
			if(branchInst.isConditional()){
				return false;
			}
		}

		// TODO include other terminator instructions here (eg.: switch with only one condition)
		return true;
	}
}
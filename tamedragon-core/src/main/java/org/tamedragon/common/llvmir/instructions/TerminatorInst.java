package org.tamedragon.common.llvmir.instructions;

import java.util.HashSet;
import java.util.List;
import java.util.Vector;

import org.tamedragon.common.llvmir.instructions.exceptions.InstructionDetailAccessException;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionUpdateException;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.Type;
import org.tamedragon.common.llvmir.types.Value;

/**
 * Subclasses of this class are all able to terminate a basic
 * block.  Thus, these are all the flow control type of operations.
 *
 */
public abstract class TerminatorInst extends Instruction {

	protected TerminatorInst(InstructionID instID, Type type, List<Value> operandList, BasicBlock parent) {
		super(instID, type, operandList, parent);
		setTerminatorInstruction(true);
	}

	/// Virtual methods - Terminators should overload these and provide inline
	/// overrides of non-V methods.
	public abstract BasicBlock getSuccessor(int index) throws InstructionDetailAccessException;
	public abstract void setSuccessor(int index, BasicBlock basicBlock, boolean setOprnd) throws InstructionUpdateException;
	public abstract boolean replaceSuccessorWith(BasicBlock successor, BasicBlock newSuccessor) throws InstructionUpdateException, 
					InstructionDetailAccessException; 
	public abstract String getInstructionName();
	public abstract int getNumSuccessors();
	public abstract boolean removeSuccessor(BasicBlock successor) throws InstructionDetailAccessException;

	protected void markAllSuccessorsExecutable(BasicBlock cfNode, HashSet<BasicBlock> unreachableNodes,
			Vector<BasicBlock> cfgWorkList){
		CFG cfg = cfNode.getParent().getCfg();
		List<BasicBlock> successors = cfg.getSuccessors(cfNode);

		// All branches can be executed

		for(BasicBlock successorNode : successors){
			// If the set of unreachable nodes does not already 
			// contain the successor, it means that the successor
			// is already known to be reachable; no need to add 
			// to the list of basic blocks.
			if(!unreachableNodes.contains(successorNode))
				continue;

			// If the basic blocks worklist already contains the
			// successor node, do not need add it again
			if(cfgWorkList.contains(successorNode))
				continue;

			cfgWorkList.addElement((BasicBlock) successorNode);

		}
	}
}

package org.tamedragon.common.llvmir.utils;

import java.util.Iterator;
import java.util.Vector;

import org.tamedragon.common.llvmir.instructions.CFG;
import org.tamedragon.common.llvmir.instructions.Instruction;
import org.tamedragon.common.llvmir.instructions.PhiNode;
import org.tamedragon.common.llvmir.instructions.TerminatorInst;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionDetailAccessException;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionUpdateException;
import org.tamedragon.common.llvmir.types.BasicBlock;

public class BasicBlockUtils {

	/**
	 * Split the edge connecting specified block. 
	 * @param nodeBB
	 * @param nodeSucc
	 * @return
	 * @throws InstructionDetailAccessException
	 * @throws InstructionUpdateException
	 */
	/*public static BasicBlock splitEdge(BasicBlock nodeBB, BasicBlock nodeSucc)
			throws InstructionDetailAccessException, InstructionUpdateException {
		BasicBlock BB = (BasicBlock) nodeBB;
		
		CFG cfg = BB.getParent().getCfg();
		
		BasicBlock successor = (BasicBlock)nodeSucc;
		int SuccNum = getSuccessorNumber(BB, successor);

		// If this is a critical edge, let SplitCriticalEdge do it.
		TerminatorInst LatchTerm = BB.getLastInstruction();
		if (CFG.splitCriticalEdge(LatchTerm, SuccNum, false, false) != null)
			return LatchTerm.getSuccessor(SuccNum);

		// If the edge isn't critical, then BB has a single successor or successor has a
		// single predecessor.  Split the block.
		BasicBlock SP = cfg.getPredecessors(successor).get(0);
		if (SP != null && SP == BB) {
			// If the successor only has a single pred, split the top of the successor
			// block.
			return splitBlock(successor, successor.getInstructions());
		}

		// Otherwise, if BB has a single successor, split it at the bottom of the block.
		if(cfg.getSuccessors(BB).size() == 1)
			return splitBlock(BB, BB.getInstructions());

		return null;
	}*/

	/**
	 * Search for the specified successor of basic block BB
	 * and return its position in the terminator instruction's list of
	 * successors.  It is an error to call this with a block that is not a
	 * successor.
	 * @param bb
	 * @param succ
	 * @return
	 */
	private static int getSuccessorNumber(BasicBlock bb, BasicBlock succ) {
		CFG cfg = bb.getParent().getCfg();
		return cfg.getSuccessors(bb).indexOf(succ);
	}

	/**
	 * Split the specified block at the specified instruction - every thing before 
	 * SplitPt stays in Old and everything starting with SplitPt moves to a new block.
	 * The two blocks are joined by an unconditional branch and the loop info is updated.
	 * @param old
	 * @param splitPt
	 * @return
	 * @throws InstructionDetailAccessException
	 * @throws InstructionUpdateException
	 */
	public static BasicBlock splitBlock(BasicBlock old, Vector<Instruction> splitPt) 
					throws InstructionDetailAccessException, InstructionUpdateException {
		Instruction instr = null;
		Instruction instruction = splitPt.get(0);
		
		if(instruction instanceof PhiNode){
			Iterator<Instruction> splitIt = splitPt.iterator();
			while(splitIt.hasNext()){
				instr = splitIt.next();
				if(instr instanceof PhiNode)
					continue;
				else
					break;
			}
		}
		else{
			instr = instruction;
		}
		
		BasicBlock newBB = old.splitBasicBlock(instr, old.getName()+".split");
		return newBB;
	}
}

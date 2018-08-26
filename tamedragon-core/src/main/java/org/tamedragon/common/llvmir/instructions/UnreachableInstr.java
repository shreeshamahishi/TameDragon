package org.tamedragon.common.llvmir.instructions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

import org.tamedragon.common.LatticeValue;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionCreationException;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.Constant;
import org.tamedragon.common.llvmir.types.Type;
import org.tamedragon.common.llvmir.types.Value;

public class UnreachableInstr extends Instruction{

	protected UnreachableInstr(BasicBlock parent) {
		super(InstructionID.UNREACHABLE_INST, Type.getVoidType(parent.getType().getCompilationContext()), null, parent);
	}
	
	
	public static UnreachableInstr create(BasicBlock parent) throws InstructionCreationException{
		if(parent == null)
			throw new InstructionCreationException(InstructionCreationException.PARENT_BASIC_BLOCK_CANNOT_BE_NULL);
		return new UnreachableInstr(parent);
	}

	@Override
	public boolean definesNewValue() {
		return false;
	}

	@Override
	public boolean isCastInstruction() {
		return false;
	}

	@Override
	public boolean isCritical() {
		return true;
	}

	@Override
	public boolean isTerminatorInstruction() {
		return true;
	}


	@Override
	public String emit() {
		return "unreachable";
	}


	@Override
	public LatticeValue visitForSCCP(LatticeValue latticeValueBeforeModelling,
			HashSet<BasicBlock> unreachableNodes,
			HashMap<Value, LatticeValue> tempVsLatticeValue,
			Vector<Value> ssaWorkList, Vector<BasicBlock> cfgWorkList) {
		// Unreachable instruction
		// Nothing to do - no-op
		return null;
	}


	@Override
	public boolean canBeHoistedOrSank() {
		return false;
	}
	
	@Override
	public Constant foldIfPossible() {
		// TODO Cannot be folded?
		return null;
	}
	
}

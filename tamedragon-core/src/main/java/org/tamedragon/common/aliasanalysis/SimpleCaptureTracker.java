package org.tamedragon.common.aliasanalysis;

import org.tamedragon.common.llvmir.instructions.Instruction;
import org.tamedragon.common.llvmir.instructions.Instruction.InstructionID;
import org.tamedragon.common.llvmir.types.User;
import org.tamedragon.common.llvmir.types.Value.ValueTypeID;

public class SimpleCaptureTracker extends CaptureTracker{

	private boolean returnCaptures;
	private boolean captured;

	public SimpleCaptureTracker(boolean returnCaptures) {
		this.returnCaptures = returnCaptures;
		captured = false;
	}

	@Override
	public void tooManyUses() {
		captured = true;
	}

	@Override
	public boolean shouldExplore(User user) {
		return true;
	}

	@Override
	public boolean captured(User user) {

		// user should always be an instruction
		ValueTypeID valueTypeID = user.getValueTypeID();
		if(valueTypeID != ValueTypeID.INSTRUCTION){
			return false;
		}

		Instruction instr = (Instruction) user;
		InstructionID instrID = instr.getInstructionID();
		if (instrID == InstructionID.RETURN_INST && !returnCaptures){
			return false;
		}

		captured = true;
		return true;
	}

	public boolean isReturnCaptures() {
		return returnCaptures;
	}

	public boolean isCaptured() {
		return captured;
	}
}

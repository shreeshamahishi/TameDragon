package org.tamedragon.common.llvmir.instructions;

import java.util.List;

import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.Type;
import org.tamedragon.common.llvmir.types.Value;

public abstract class UnaryInstruction extends Instruction{

	public UnaryInstruction(InstructionID instructionID, Type type,
			List<Value> operandList, BasicBlock parent) {
		super(instructionID, type, operandList, parent);
		// TODO Auto-generated constructor stub
	}
}

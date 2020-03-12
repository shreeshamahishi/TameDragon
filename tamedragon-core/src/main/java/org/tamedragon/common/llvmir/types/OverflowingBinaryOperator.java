package org.tamedragon.common.llvmir.types;

import org.tamedragon.common.llvmir.instructions.BinaryOperator;
import org.tamedragon.common.llvmir.instructions.BinaryOperator.BinaryOperatorID;
import org.tamedragon.common.llvmir.instructions.Instruction;
import org.tamedragon.common.llvmir.instructions.Instruction.InstructionID;

public class OverflowingBinaryOperator extends Value{
	
	private boolean noUnsignedWrap;
	private boolean noSignedWrap;
	private OpCodeID opCodeId;
	
	public OverflowingBinaryOperator(Type type, OpCodeID opCodeId,
			boolean noUnsignedWrap, boolean noSignedWrap) {
		super(type);
		this.opCodeId = opCodeId;
		this.noUnsignedWrap = noUnsignedWrap;
		this.noSignedWrap = noSignedWrap;
	}

	public void setHasNoUnsignedWrap(boolean b) {
		noUnsignedWrap = b;
	}
	public void setHasNoSignedWrap(boolean b) {
		noSignedWrap = b;
	}

	/// Test whether this operation is known to never
	/// undergo unsigned overflow, aka the nuw property.
	public boolean hasNoUnsignedWrap() {
		return noUnsignedWrap;
	}

	/// Test whether this operation is known to never
	/// undergo signed overflow, aka the nsw property.
	public boolean hasNoSignedWrap() {
		return noSignedWrap;
	}

	public static boolean classof(Instruction instr) {
		if(instr.getInstructionID() != InstructionID.BINARY_INST) {
			return false;
		}

		BinaryOperator binaryOp = (BinaryOperator)instr;
		return binaryOp.getOpCode() == BinaryOperatorID.ADD ||
				binaryOp.getOpCode() == BinaryOperatorID.SUB ||
				binaryOp.getOpCode() == BinaryOperatorID.MUL ||
				binaryOp.getOpCode() == BinaryOperatorID.SHL;

	}

	public static boolean classof(ConstantExpr constExpr) {
		
		if(constExpr.getOpCode() != InstructionID.BINARY_INST) {
			return false;
		}
		
		return constExpr.getOtherOpCodeId() == BinaryOperatorID.ADD ||
			   constExpr.getOtherOpCodeId() == BinaryOperatorID.SUB ||
			   constExpr.getOtherOpCodeId() == BinaryOperatorID.MUL ||
			   constExpr.getOtherOpCodeId() == BinaryOperatorID.SHL;
	}

	public static boolean classof(Value val) {
		return (val instanceof Instruction && classof((Instruction)val)) ||
				(val instanceof ConstantExpr && classof((ConstantExpr)val));
	}

	public OpCodeID getOpCodeId() {
		return opCodeId;
	}
}

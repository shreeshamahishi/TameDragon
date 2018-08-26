package org.tamedragon.common.controlflowanalysis;

import org.tamedragon.common.llvmir.instructions.BinaryOperator.BinaryOperatorID;
import org.tamedragon.common.llvmir.types.Value;
import org.tamedragon.common.llvmir.utils.LLVMIREmitter;

public class ExpressionElement{

	public enum ElementType {OPERAND, OPERATOR};

	private Value operand;
	private ElementType type;
	private BinaryOperatorID binOpId;

	private LLVMIREmitter emitter = LLVMIREmitter.getInstance();

	public ExpressionElement(Value value){
		operand = value;
		type = ElementType.OPERAND;
	}

	public ExpressionElement(BinaryOperatorID oprType){
		binOpId = oprType;
		type = ElementType.OPERATOR;
	}

	public Value getOperand() {
		return operand;
	}

	public ElementType getType() {
		return type;
	}

	public BinaryOperatorID getBinOpId() {
		return binOpId;
	}

	public void setBinOpID(BinaryOperatorID binOpId) throws Exception{
		if(type != ElementType.OPERATOR)
			throw new Exception("Trying to set operator sign on a non-operator.");
		this.binOpId = binOpId;
	}
	
	public String toString(){
		if(type == ElementType.OPERATOR){
			if(binOpId == BinaryOperatorID.MUL || binOpId == BinaryOperatorID.FMUL){
				return "*";
			}
			else if(binOpId == BinaryOperatorID.ADD || binOpId == BinaryOperatorID.FADD){
				return "+";
			}
			else if(binOpId == BinaryOperatorID.SUB || binOpId == BinaryOperatorID.FSUB){
				return "-";
			}
			else 
				return null;
		}
		else{
			return emitter.getValidName(operand);
		}
	}
	
	@Override
	public boolean equals(Object otherObj){
		if(!(otherObj instanceof ExpressionElement)){
			return false;
		}
		
		ExpressionElement otherElement  = (ExpressionElement) otherObj;
		if(type != otherElement.getType()){
			return false;
		}
		
		if(type == ElementType.OPERATOR){
			if(binOpId != otherElement.getBinOpId()){
				return false;
			}
		}
		else{
			Value otherOperand = otherElement.getOperand();
			if(operand.isAConstant()){
				if(!otherOperand.isAConstant()){
					return false;
				}
				return operand.equals(otherOperand);
			}
			else{
				if(otherOperand.isAConstant()){
					return false;
				}
				if(operand != otherOperand){
					return false;
				}
			}
		}
		
		return true;
	}
	
}

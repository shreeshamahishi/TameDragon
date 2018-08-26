package org.tamedragon.common.llvmir.irdata;
import org.tamedragon.common.llvmir.instructions.BinaryOperator.BinaryOperatorID;

/**
 * The BinaryOprData class denotes a binary operation instruction.
 **/

public class BinaryOprData extends ValueData{
	private String firstOperand;
	private String secondOperand;
	private BinaryOperatorID binOpratorID;
	private String flag; // nuw, nsw or exact

	public void setFirstOperand(String firstOpr) {
		this.firstOperand = firstOpr;
	}

	public String getFirstOperand() {
		return firstOperand;
	}

	public void setSecondOperand(String secondOpr) {
		this.secondOperand = secondOpr;
	}

	public String getSecondOperand() {
		return secondOperand;
	}

	public void setBinOpratorID(BinaryOperatorID binOprId) {
		this.binOpratorID = binOprId;
	}

	public BinaryOperatorID getBinOpratorID() {
		return binOpratorID;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
}
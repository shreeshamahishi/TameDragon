package org.tamedragon.common.llvmir.irdata;

/**
 * The StoreInstrData class denotes a store instruction.
 **/

public class StoreInstrData extends ValueData{
	private String firstOperand;
	private	String secondOperand;
	private String atomicOrdering;
	private String isVolatile;
	
	public void setFirstOperand(String firstOperand) {
		this.firstOperand = firstOperand;
	}
	
	public String getFirstOperand() {
		return firstOperand;
	}
	
	public void setSecondOperand(String secondOperand) {
		this.secondOperand = secondOperand;
	}
	
	public String getSecondOperand() {
		return secondOperand;
	}

	public void setAtomicOrdering(String atomicOrdering) {
		this.atomicOrdering = atomicOrdering;
	}

	public String getAtomicOrdering() {
		return atomicOrdering;
	}

	public void setIsVolatile(String isVolatile) {
		this.isVolatile = isVolatile;
	}

	public String getIsVolatile() {
		return isVolatile;
	}
}

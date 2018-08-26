package org.tamedragon.common.llvmir.irdata;

import java.util.List;

/**
 * The SelectInstrData class denotes a select instruction.
 **/

public class SelectInstrData extends ValueData {
	
	private String conditionalValue;
	private String firstType;
	private String firstValue;
	private String secondType;
	private String secondValue;
	
	
	public String getConditionalValue() {
		return conditionalValue;
	}
	
	public void setConditionalValue(String conditionalValue) {
		this.conditionalValue = conditionalValue;
	}

	public String getFirstType() {
		return firstType;
	}

	public void setFirstType(String firstType) {
		this.firstType = firstType;
	}

	public String getFirstValue() {
		return firstValue;
	}

	public void setFirstValue(String firstValue) {
		this.firstValue = firstValue;
	}

	public String getSecondType() {
		return secondType;
	}

	public void setSecondType(String secondType) {
		this.secondType = secondType;
	}

	public String getSecondValue() {
		return secondValue;
	}

	public void setSecondValue(String secondValue) {
		this.secondValue = secondValue;
	}
}

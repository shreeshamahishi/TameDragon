package org.tamedragon.common.llvmir.irdata;

/**
 * The AllocaInstrData class denotes an alloca instruction.
 **/

public class AllocaInstrData extends ValueData  {

	private String alignmentValue;
	private String arrayLength;
	
	public void setAlign(String align) {
		this.alignmentValue = align;
	}
	
	public String getAlign() {
		return alignmentValue;
	}
	
	public void setArrayLength(String arrayLength) {
		this.arrayLength = arrayLength;
	}
	
	public String getArrayLength() {
		return arrayLength;
	}
}
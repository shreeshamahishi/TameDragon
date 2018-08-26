package org.tamedragon.common.llvmir.irdata;

/**
 * The LoadInstrData class denotes a load instruction.
 **/

public class LoadInstrData extends ValueData {
	
	private String pointerName;

	public void setPointerName(String pointerName) {
		this.pointerName = pointerName;
	}

	public String getPointerName() {
		return pointerName;
	}
}

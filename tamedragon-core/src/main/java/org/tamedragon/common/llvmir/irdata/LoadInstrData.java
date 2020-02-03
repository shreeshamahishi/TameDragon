package org.tamedragon.common.llvmir.irdata;

/**
 * The LoadInstrData class denotes a load instruction.
 **/

public class LoadInstrData extends ValueData {
	
	private String pointeeTypeStr;
	private String pointerName;

	public String getPointeeTypeStr() {
		return pointeeTypeStr;
	}

	public void setPointeeTypeStr(String pointeeTypeStr) {
		this.pointeeTypeStr = pointeeTypeStr;
	}
	
	public void setPointerName(String pointerName) {
		this.pointerName = pointerName;
	}

	public String getPointerName() {
		return pointerName;
	}

}

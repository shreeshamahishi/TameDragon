package org.tamedragon.common.llvmir.irdata;

/**
 * The ReturnInstrData class denotes a return instruction.
 **/

public class ReturnInstrData extends ValueData {

	private String returnType;
	private String returnData;

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnData(String returnData) {
		this.returnData = returnData;
	}

	public String getReturnData() {
		return returnData;
	}
}
package org.tamedragon.common.llvmir.irdata;

/**
 * The CallInstrData class denotes a call instruction.
 **/


public class CallInstrData extends ValueData{
	private String isVarArguments;
	private String callingConv;
	private String parameterAttributes;
	private String functionName;
	private String functionParameters;
	private String functionAttributes;
	
	public void setIsVarArguments(String isTail) {
		this.isVarArguments = isTail;
	}
	
	public String getIsVarArguments() {
		return isVarArguments;
	}
	
	public void setCallingConv(String callingConv) {
		this.callingConv = callingConv;
	}
	
	public String getCallingConv() {
		return callingConv;
	}
	
	public void setParameterAttr(String parameterAttr) {
		this.parameterAttributes = parameterAttr;
	}
	
	public String getParameterAttr() {
		return parameterAttributes;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionParameters(String functionParameters) {
		this.functionParameters = functionParameters;
	}

	public String getFunctionParameters() {
		return functionParameters;
	}

	public void setFunctionAttributes(String functionAttributes) {
		this.functionAttributes = functionAttributes;
	}

	public String getFunctionAttributes() {
		return functionAttributes;
	}
}
package org.tamedragon.common.llvmir.irdata;

import java.util.List;

/**
 * The FunctionData class denotes a function definition.
 **/

public class FunctionData extends ValueData {
	
	private String linkageType, functionName;
	private List<String> paramTypes;
	private List<ArgumentData> argumentList;
	private String functionAttribute ;
	private boolean isDefine;
	private String returnAttribute;
	
	public void setLinkageType(String linkageType) {
		this.linkageType = linkageType;
	}
	
	public String getLinkageType() {
		return linkageType;
	}
	
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	
	public String getFunctionName() {
		return functionName;
	}
	
	public void setArgumentList(List<ArgumentData> argumentList) {
		this.argumentList = argumentList;
	}
	
	public List<ArgumentData> getArgumentList() {
		return argumentList;
	}
	
	public void setFunctionAttribute(String functionAttribute) {
		this.functionAttribute = functionAttribute;
	}
	
	public String getFunctionAttribute() {
		return functionAttribute;
	}
	
	public void setDefine(boolean isDefine) {
		this.isDefine = isDefine;
	}
	
	public boolean isDefine() {
		return isDefine;
	}
	
	public void setReturnAttribute(String returnAttribute) {
		this.returnAttribute = returnAttribute;
	}
	
	public String getReturnAttribute() {
		return returnAttribute;
	}

	public void setParamTypes(List<String> paramTypes) {
		this.paramTypes = paramTypes;
	}

	public List<String> getParamTypes() {
		return paramTypes;
	}
}

package org.tamedragon.common.llvmir.irdata;

import java.util.List;

/**
 * The GlobalVariableData class denotes a global variable definition.
 **/

public class GlobalVariableData extends ValueData {
	
	private String isConstant;
	private String isThreadLoacal;
	private String initialValue;
	private String linkageType;
	private String align;
	private String variableType;
	private boolean isArray;
	private boolean isStringConst;
	private boolean isStructObj;
	private boolean isUnnamed;
	private CastInstrData castData ;
	private GetElementPtrData ptrData;
	private List<ValueData> ptrDataList;
	
	public void setIsConstant(String isConstant) {
		this.isConstant = isConstant;
	}
	
	public String getIsConstant() {
		return isConstant;
	}
	
	public void setIsThreadLoacal(String isThreadLoacal) {
		this.isThreadLoacal = isThreadLoacal;
	}
	
	public String getIsThreadLoacal() {
		return isThreadLoacal;
	}
	
	public void setInitialValue(String initialValue) {
		this.initialValue = initialValue;
	}
	
	public String getInitialValue() {
		return initialValue;
	}
	
	public void setLinkageType(String linkagetype) {
		this.linkageType = linkagetype;
	}
	
	public String getLinkageType() {
		return linkageType;
	}
	
	public void setArray(boolean isArray) {
		this.isArray = isArray;
	}
	
	public boolean isArray() {
		return isArray;
	}
	
	public void setStringConst(boolean isStringConst) {
		this.isStringConst = isStringConst;
	}
	
	public boolean isStringConst() {
		return isStringConst;
	}
	
	public void setAlign(String alignStr) {
		this.align = alignStr;
	}
	
	public String getAlign() {
		return align;
	}
	
	public void setVariableType(String variableType) {
		this.variableType = variableType;
	}

	public String getVariableType() {
		return variableType;
	}

	public void setStructObj(boolean isStructObj) {
		this.isStructObj = isStructObj;
	}
	
	public boolean isStructObj() {
		return isStructObj;
	}

	public void setUnnamed(boolean isUnnamed) {
		this.isUnnamed = isUnnamed;
	}
	
	public boolean isUnnamed() {
		return isUnnamed;
	}
	
	public void setCastData(CastInstrData castData) {
		this.castData = castData;
	}
	
	public CastInstrData getCastData() {
		return castData;
	}
	
	public void setPtrData(GetElementPtrData ptrData) {
		this.ptrData = ptrData;
	}
	
	public GetElementPtrData getPtrData() {
		return ptrData;
	}
	
	public void setPtrDataList(List<ValueData> ptrDataList2) {
		this.ptrDataList = ptrDataList2;
	}
	
	public List<ValueData> getPtrDataList() {
		return ptrDataList;
	}
}
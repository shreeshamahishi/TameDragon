package org.tamedragon.common.llvmir.irdata;

/**
 * The GetElementPtrData class denotes the GetElementPtr instruction.
 **/

public class GetElementPtrData extends ValueData{

	private String pointeeTypeStr;
	private String ptrType;
	private String isInBound;
	private String elementName;
	private String listVsIndexStr;

	public void setPtrType(String ptrType) {
		this.ptrType = ptrType;
	}

	public String getPtrType() {
		return ptrType;
	}

	public void setListVsIndexStr(String listVsIndexStr) {
		this.listVsIndexStr = listVsIndexStr;
	}

	public String getListVsIndexStr() {
		return listVsIndexStr;
	}

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

	public String getElementName() {
		return elementName;
	}

	public void setIsInBound(String isInBound) {
		this.isInBound = isInBound;
	}

	public String getIsInBound() {
		return isInBound;
	}

	public void setPointeeTypeStr(String pointeeTypeStr) {
		this.pointeeTypeStr = pointeeTypeStr;
	}
	
	public String getPointeeTypeStr() {
		return pointeeTypeStr;
	}
}

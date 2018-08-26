package org.tamedragon.common.llvmir.irdata;

/**
 * The base class for all intermediate representations of instructions and other constructs like structures.. All
 * classes that extend this class and including this class are POJOs that simply denote the intermediate representations.
 */

public abstract class ValueData  {
	
	protected String typeStr;
	protected String result;
	protected int lineNum;
	protected int position;
	
	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}
	
	public String getTypeStr() {
		return typeStr;
	}

	public void setResult(String name) {
		this.result = name;
	}

	public String getResult() {
		return result;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getLineNum() {
		return lineNum;
	}

	public void setLineNum(int lineNum) {
		this.lineNum = lineNum;
	}
}

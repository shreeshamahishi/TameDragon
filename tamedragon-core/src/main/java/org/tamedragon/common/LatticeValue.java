package org.tamedragon.common;

import org.tamedragon.common.llvmir.types.Constant;

public class LatticeValue{
	public static short UNDEFINED = 0;
	public static short CONSTANT = 1;
	public static short OVERDEFINED = 2;

	private short type;
	private Constant constantValue;

	public LatticeValue(){

	}

	public LatticeValue(short type){
		this.type = type;
	}

	public short getType() {
		return type;
	}

	public void setType(short type) {
		this.type = type;
	}

	public Constant getConstantValue() {
		return constantValue;
	}

	public void setConstantValue(Constant constantValue) {
		this.type = LatticeValue.CONSTANT;
		this.constantValue = constantValue;
	}

	public String toString(){
		String ret = "UNKNOWN";
		if(type == UNDEFINED)
			ret = "UNDEFINED";
		else if(type == OVERDEFINED)
			ret = "OVERDEFINED";
		else if(type == CONSTANT)
			ret = "CONSTANT(" + constantValue.toString() + ")";
		return ret;
	}
}
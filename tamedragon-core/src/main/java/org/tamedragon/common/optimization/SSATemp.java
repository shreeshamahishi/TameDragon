package org.tamedragon.common.optimization;

import org.tamedragon.common.llvmir.types.Temp;

public class SSATemp extends Temp{
	
	Temp temp;      // Embedded temp
	int count;      // The variable count ; indicates the number of times defined
	
	public SSATemp(Temp temp, int count){
		this.temp = temp;
		this.count = count;
	}
	
	public String toString(){
		return temp.toString() + "_n" + count;
	}
	
	@Override
	public boolean isSigned() {
		return temp.isSigned();
	}

	@Override
	public void setSigned(boolean signed) {
		temp.setSigned(signed);
	}

	@Override
	public boolean isInteger() {
		return temp.isInteger();
	}

	@Override
	public void setInteger(boolean integer) {
		temp.setInteger(integer);
	}

	@Override
	public int getIntegerSize() {
		return temp.getIntegerSize();
	}

	@Override
	public void setIntegerSize(int integerSize) {
		temp.setIntegerSize(integerSize);
	}

	@Override
	public int getFloatPrecision() {
		return temp.getFloatPrecision();
	}

	@Override
	public void setFloatPrecision(int floatPrecision) {
		temp.setFloatPrecision(floatPrecision);
	}	
}

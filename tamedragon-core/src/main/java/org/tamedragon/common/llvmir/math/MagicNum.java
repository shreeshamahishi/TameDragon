package org.tamedragon.common.llvmir.math;

public class MagicNum {
	protected APInt magicNum;   // The magic number
	protected int s;            // Shift amount
	
	public MagicNum(APInt magicNum, int s) {
		this.magicNum = magicNum;
		this.s = s;
	}

	public APInt getMagicNum() {
		return magicNum;
	}

	public void setMagicNum(APInt magicNum) {
		this.magicNum = magicNum;
	}

	public int getS() {
		return s;
	}

	public void setS(int s) {
		this.s = s;
	}
}

package org.tamedragon.common.llvmir.math;

public class QuotientRemainderPair {
	
	private APInt quotient;
	private APInt remainderApInt;
	private ULong remainderULong;
	
	public QuotientRemainderPair(APInt quotient, APInt remainderApInt) {
		this.quotient = quotient;
		this.remainderApInt = remainderApInt;
	}
	
	public QuotientRemainderPair(APInt quotient, ULong remainderULong) {
		this.quotient = quotient;
		this.remainderULong = remainderULong;
		
	}
	
	public APInt getQuotient() {
		return quotient;
	}
	
	public void setQuotient(APInt quotient) {
		this.quotient = quotient;
	}
	
	public APInt getRemainderApInt() {
		return remainderApInt;
	}
	
	public void setRemainderApInt(APInt remainder) {
		this.remainderApInt = remainder;
	}
	
	public ULong getRemainderULong() {
		return remainderULong;
	}
	
	public void setRemainderULong(ULong remainderULong) {
		this.remainderULong = remainderULong;
	}
	
}

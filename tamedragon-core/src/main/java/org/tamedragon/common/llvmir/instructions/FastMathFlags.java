package org.tamedragon.common.llvmir.instructions;

public final class FastMathFlags {

	private boolean allowReassoc;
	private boolean noNaNs;
	private boolean noInfs;
	private boolean noSignedZeros;
	private boolean allowReciprocal;
	private boolean allowContract;
	private boolean approxFunc;
	
	public FastMathFlags() {
		super();
	}
	
	public FastMathFlags(boolean allowReassoc, boolean noNaNs, boolean noInfs, boolean noSignedZeros,
			boolean allowReciprocal, boolean allowContract, boolean approxFunc) {
		super();
		this.allowReassoc = allowReassoc;
		this.noNaNs = noNaNs;
		this.noInfs = noInfs;
		this.noSignedZeros = noSignedZeros;
		this.allowReciprocal = allowReciprocal;
		this.allowContract = allowContract;
		this.approxFunc = approxFunc;
	}
	
	public boolean isAllowReassoc() {
		return allowReassoc;
	}
	public void setAllowReassoc(boolean allowReassoc) {
		this.allowReassoc = allowReassoc;
	}
	public boolean isNoNaNs() {
		return noNaNs;
	}
	public void setNoNaNs(boolean noNaNs) {
		this.noNaNs = noNaNs;
	}
	public boolean isNoInfs() {
		return noInfs;
	}
	public void setNoInfs(boolean noInfs) {
		this.noInfs = noInfs;
	}
	public boolean isNoSignedZeros() {
		return noSignedZeros;
	}
	public void setNoSignedZeros(boolean noSignedZeros) {
		this.noSignedZeros = noSignedZeros;
	}
	public boolean isAllowReciprocal() {
		return allowReciprocal;
	}
	public void setAllowReciprocal(boolean allowReciprocal) {
		this.allowReciprocal = allowReciprocal;
	}
	public boolean isAllowContract() {
		return allowContract;
	}
	public void setAllowContract(boolean allowContract) {
		this.allowContract = allowContract;
	}
	public boolean isApproxFunc() {
		return approxFunc;
	}
	public void setApproxFunc(boolean approxFunc) {
		this.approxFunc = approxFunc;
	}
}

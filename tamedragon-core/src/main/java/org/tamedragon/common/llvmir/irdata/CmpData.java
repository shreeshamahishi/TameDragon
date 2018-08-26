package org.tamedragon.common.llvmir.irdata;

/**
 * The CmpData class denotes an integer or real number comparison instruction - icmp or fcmp.
 **/

public class CmpData extends ValueData{

	private String predicate;
	private String firstOprand;
	private String secondOprand;
	private String cmpType;

	public void setPredicate(String predicate) {
		this.predicate = predicate;
	}

	public String getPredicate() {
		return predicate;
	}

	public void setFirstOprand(String firstOprand) {
		this.firstOprand = firstOprand;
	}

	public String getFirstOprand() {
		return firstOprand;
	}

	public void setSecondOprand(String secondOprand) {
		this.secondOprand = secondOprand;
	}

	public String getSecondOprand() {
		return secondOprand;
	}

	public void setCmpType(String cmpType) {
		this.cmpType = cmpType;
	}

	public String getCmpType() {
		return cmpType;
	}
}
package org.tamedragon.common.llvmir.irdata;

/**
 * The ArgumentData class denotes an argument that is passed to a function.
 **/

public class ArgumentData extends ValueData{

	private String attributes;
	private boolean hasEllipses;

	public void setAttributs(String attributr_list) {
		this.attributes = attributr_list;
	}

	public String getAttributrs() {
		return attributes;
	}

	public void setHasEllipses(boolean hasEllipses) {
		this.hasEllipses = hasEllipses;
	}

	public boolean isHasEllipses() {
		return hasEllipses;
	}
}

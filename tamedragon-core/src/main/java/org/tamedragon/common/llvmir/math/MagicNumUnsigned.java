package org.tamedragon.common.llvmir.math;

public class MagicNumUnsigned extends MagicNum{
	private boolean addIndicator;     // Add indicator
	
	public MagicNumUnsigned(APInt magicNum, int s, boolean addIndicator) {
		super(magicNum, s);
		this.addIndicator = addIndicator;
	}

	public boolean isAddIndicator() {
		return addIndicator;
	}

	public void setAddIndicator(boolean addIndicator) {
		this.addIndicator = addIndicator;
	}
}

package org.tamedragon.common.llvmir.irdata;

/**
 * The DataLayout class denotes the data layout specification. It is a simple string that is
 * interpreted by the front-end.
 **/

public class DataLayout extends ValueData {

	private String targetDataLayout;

	public void setTargetDataLayout(String targetDataLayout) {
		this.targetDataLayout = targetDataLayout;
	}

	public String getTargetDataLayout() {
		return targetDataLayout;
	}
}

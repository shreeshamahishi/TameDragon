package org.tamedragon.common.llvmir.irdata;

/**
 * The PhiNodeData class denotes a phi node with all incoming values and corresponding basic block labels.
 **/

public class PhiNodeData extends ValueData {
	private String incomingValueAndBBPairs;

	public void setIncomingValueAndBBPairs(String incomingValueAndBBPairs) {
		this.incomingValueAndBBPairs = incomingValueAndBBPairs;
	}

	public String getIncomingValueAndBBPairs() {
		return incomingValueAndBBPairs;
	}
}
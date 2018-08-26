package org.tamedragon.common.llvmir.irdata;

/**
 * The SwitchInstrData class denotes the switch instruction.
 **/

public class SwitchInstrData extends ValueData{
	private String switchOn;
	private String defaultBB;
	
	public void setSwitchOn(String switchOn) {
		this.switchOn = switchOn;
	}

	public String getSwitchOn() {
		return switchOn;
	}

	public void setDefaultBB(String defaultBB) {
		this.defaultBB = defaultBB;
	}

	public String getDefaultBB() {

		return defaultBB;
	}
}
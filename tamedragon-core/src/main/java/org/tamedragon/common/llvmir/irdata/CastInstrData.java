package org.tamedragon.common.llvmir.irdata;

/**
 * The CastInstrData class denotes a cast instruction like uitofp, sitofp, etc..
 **/

public class CastInstrData extends ValueData {

	private String source;
	private String value;
	private String target;

	public void setSource(String source) {
		this.source = source;
	}

	public String getSource() {
		return source;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getTarget() {
		return target;
	}

}

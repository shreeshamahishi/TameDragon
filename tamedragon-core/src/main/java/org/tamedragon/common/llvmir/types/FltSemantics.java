package org.tamedragon.common.llvmir.types;

/**
 * Represents floating point arithmetic semantics
 * @author ipsg
 *
 */
public class FltSemantics {
	short maxExponent;
	short minExponent;
	int precision;
	boolean arithmaticOK;
	
	public FltSemantics(short maxExponent, short minExponent, int precision, boolean arithmaticOK) {
		this.maxExponent = maxExponent;
		this.minExponent = minExponent;
		this.precision = precision;
		this.arithmaticOK = arithmaticOK;
	}
}

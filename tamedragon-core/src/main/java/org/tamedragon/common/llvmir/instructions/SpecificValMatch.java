package org.tamedragon.common.llvmir.instructions;

public class SpecificValMatch<T> extends Pattern {

	private T Val;

	public SpecificValMatch(T V) {
		Val = V;
	}

	public <ITy> boolean match(ITy V) { return V == Val; }
}
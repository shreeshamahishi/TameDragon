package org.tamedragon.common.llvmir.instructions;

public class BindType<C> extends Pattern {
	C VR;

	public BindType(C V)  {
		VR = V;
	}

	public <T> boolean match(T value) {
		try {
			C CV = (C)value;
			VR = CV;
			return true;
		}
		catch (ClassCastException e) {
			return false;
		}
	}
}

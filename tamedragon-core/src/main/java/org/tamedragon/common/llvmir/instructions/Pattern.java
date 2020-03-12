package org.tamedragon.common.llvmir.instructions;

public abstract class Pattern {
	public abstract <T> boolean match(T Value);
}

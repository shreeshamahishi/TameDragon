package org.tamedragon.common.llvmir.types;

import java.util.Vector;

public class ConstantTraits {
	public static int uses(Vector v) {
		return v.size();
	}
	public static int uses(Constant v) {
		return 1;
	}
}

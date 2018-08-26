package org.tamedragon.compilers.clang.abssyntree;

public class BinaryOpExpr extends RootExpr{

	 public static final int OR = 0;
	 public static final int AND = 1;
	 public static final int EQUALS = 5;
	 public static final int NOT_EQUALS = 6;
	 public static final int LT = 7;
	 public static final int GT = 8;
	 public static final int GTE = 9;
	 public static final int LTE = 10;
	 public static final int LEFT_SHIFT = 11;
	 public static final int RIGHT_SHIFT = 12;
	 public static final int PLUS = 13;
	 public static final int MINUS = 14;
	 public static final int MULTIPLY = 15;
	 public static final int DIVIDE = 16;
	 public static final int MODULO = 17;
	 
	 
	 private int type;
	 
	 private RootExpr lo;
	 private RootExpr ro;
	 
	 public BinaryOpExpr(int type, RootExpr lo, RootExpr ro) {
		 this.lo = lo;
		 this.ro = ro;
	 }

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public RootExpr getLo() {
		return lo;
	}

	public void setLo(RootExpr lo) {
		this.lo = lo;
	}

	public RootExpr getRo() {
		return ro;
	}

	public void setRo(RootExpr ro) {
		this.ro = ro;
	}
	 
}

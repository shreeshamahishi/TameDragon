package org.tamedragon.compilers.clang.abssyntree;

public interface Expr {
	public static final int ROOT = 1;
	public static final int ASSIGNMENT = 2;
	public static final int CONDITIONAL = 3;
	public static final int CONSTANT = 4;
	public static final int LOGICAL_OR = 5;
	public static final int LOGICAL_AND = 6;
	public static final int INCLUSIVE_OR = 7;
	public static final int EXCLUSIVE_OR = 8;
	public static final int AND = 9;
	public static final int EQUALITY = 10;
	public static final int RELATIONAL = 11;
	public static final int SHIFT = 12;
	public static final int ADDITIVE = 13;
	public static final int MULTIPLICATIVE = 14;
	public static final int CAST = 15;
	public static final int UNARY = 16;
	public static final int POST_FIX = 17;
	public static final int PRIMARY = 18;
	
	public int getExprType();
	
}

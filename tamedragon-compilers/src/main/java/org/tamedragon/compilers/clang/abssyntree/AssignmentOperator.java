package org.tamedragon.compilers.clang.abssyntree;

public class AssignmentOperator extends Absyn {
	public static final int ASSIGN = 1;
	public static final int MULTIPLY_ASSIGN = 2; 
	public static final int DIVIDE_ASSIGN = 3;
	public static final int ADD_ASSIGN = 4;
	public static final int MINUS_ASSIGN  = 5;
	public static final int MODULO_ASSIGN  = 6;
	public static final int BITWISE_AND_ASSIGN  = 7;
	public static final int BITWISE_XOR_ASSIGN  = 8;
	public static final int BITWISE_OR_ASSIGN  = 9;
	public static final int LEFT_SHIFT_ASSIGN  = 10;
	public static final int RIGHT_SHIFT_ASSIGN  = 11;
	
	private int type;
	
	public AssignmentOperator(int type){
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	
}

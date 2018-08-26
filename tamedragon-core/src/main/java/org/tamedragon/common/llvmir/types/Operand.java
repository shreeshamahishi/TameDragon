package org.tamedragon.common.llvmir.types;
public interface Operand {
	public static int TEMP_TYPE = 0;
	public static int NUMERIC_TYPE = 1;

	public int getOperandType();
	public boolean isInteger();

}

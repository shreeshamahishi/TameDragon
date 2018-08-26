package org.tamedragon.assemblyabstractions.constructs;

/**
 * This interface is an abstraction of a construct of a mips assembly tree; it can either be
 * a statement or an expression.
 * @author shreesha123
 *
 */
public interface AssemType {

	public static final int VALUE_TYPE = 0;     // Represents a assem type that evaluates to a value
	public static final int COMPARE_TYPE = 1;
	public static final int VOID_TYPE = 2;  // Statements that have expressions returning no value
	public static final int STRING_TYPE = 3;  // String literal type
	public static final int FLOATING_POINT_TYPE = 4;
		
	// Int types
	public static final int SIZE_NIBBLE = 4;
	public static final int SIZE_BYTE = 1;
	public static final int SIZE_HALF_WORD = 2;
	public static final int SIZE_WORD = 4;
	public static final int SIZE_DOUBLE = 8;
	public static final int SIZE_QUAD_WORD = 16;
	
	// Floating point types
	public static final int FP_HALF = 2;
	public static final int FP_FLOAT = 4;
	public static final int FP_DOUBLE = 8;
	public static final int FP_128 = 24;
	public static final int FP_80_X86 = 16;
	public static final int FP_128_PPC = 40;
	public static final int FP_X86_MMX = 48;
	
	// Int qualifier
	public static final int INT_SIGNED = 0;
	public static final int INT_UNSIGNED = 1;
	
	public int getAssemTypeType();
	public String getDescription();	
}

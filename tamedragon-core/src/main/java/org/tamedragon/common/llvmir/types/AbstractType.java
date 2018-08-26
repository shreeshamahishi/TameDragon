package org.tamedragon.common.llvmir.types;

/**
 * Represents a type not associated with any name (equivalent to AssemValueProperties in the AST).
 * @author shreesha
 *
 */
public class AbstractType {

	public static final int SINGLE_PRECISION = 4;
	public static final int DOUBLE_PRECISION = 8;
	
	public static final int WORD_SIZE = 4;
	public static final int BYTE_SIZE = 1;
	public static final int HALF_WORD = 2;
	public static final int DOUBLE_WORD = 8;
	
	private boolean unSigned;
	private boolean integer;
	private int integerSize;
	private int floatPrecision;
	
	public AbstractType(){}
	
	public AbstractType(boolean isUnsigned, boolean integer, int integerSize, int floatPrecision){
		this.unSigned = isUnsigned;
		this.integer = integer;
		this.integerSize = integerSize;
		this.floatPrecision = floatPrecision;
	}
	
	public boolean isUnSigned() {
		return unSigned;
	}
	
	public void setSigned(boolean unSigned) {
		this.unSigned = unSigned;
	}
	
	public boolean isInteger() {
		return integer;
	}
	public void setInteger(boolean integer) {
		this.integer = integer;
	}
	
	public int getIntegerSize() {
		return integerSize;
	}
	
	public void setIntegerSize(int integerSize) {
		this.integerSize = integerSize;
	}
	
	public int getFloatPrecision() {
		return floatPrecision;
	}
	
	public void setFloatPrecision(int floatPrecision) {
		this.floatPrecision = floatPrecision;
	}
	
	public int getSize(){
		int size = 0;
		
		if(integer){
			size = integerSize;
		}
		else{
			size = floatPrecision;
		}
		
		return size;
	}
}

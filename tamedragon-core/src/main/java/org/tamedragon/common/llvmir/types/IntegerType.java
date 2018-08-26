package org.tamedragon.common.llvmir.types;


public class IntegerType extends Type{

	protected final static int MIN_NUM_BITS = 1;
	protected final static int MAX_NUM_BITS = 8388607;    // (1 << 23)
	
	private int numBits;
	private boolean isSigned;
	
	public IntegerType(CompilationContext compilationContext, int numBits, boolean isSigned){
		super(compilationContext, TypeID.INTEGER_ID);
		this.numBits = numBits;
		this.isSigned = isSigned;
	}

	public int getNumBits() {
		return numBits;
	}
	
	public boolean isSigned() {
		return isSigned;
	}

	public String toString(){
		return "i" + numBits;
	}
}

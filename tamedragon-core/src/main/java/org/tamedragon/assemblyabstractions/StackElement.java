package org.tamedragon.assemblyabstractions;

public interface StackElement {
	public static final short SCALAR_TYPE = 0;
	public static final short COLLECTION_TYPE = 1;
	
	public static final short ESCAPED = 0;
	public static final short SPILLED = 1;
	public static final short SAVED_REGISTER = 2;
	public static final short ARGUMENT_IN_STACK = 3;
	
	public short getType();  // Can be one of SCALAR_TYPE or COLLECTION_TYPE
	public short getMode();  // Can be one of ESCAPED or SPILLED or SAVED_REGISTER
	public int getSize();
}



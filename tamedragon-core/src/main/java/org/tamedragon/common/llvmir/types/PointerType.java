package org.tamedragon.common.llvmir.types;

/**
 * PointerType represents pointers.
 */

public class PointerType extends SequentialType {

	private int addressSpace;

	protected PointerType(CompilationContext compilationContext,
			Type containedType, int addressSpace) {
		super(compilationContext, TypeID.POINTER_ID, containedType);
		this.addressSpace = addressSpace;
	}

	public int getAddressSpace() {
		return addressSpace;
	}

	/**
	 * Utility function that returns true if the type that is passed can be a
	 * pointee to a pointer. Labels, metadata and void cannot be pointees.
	 */

	public static boolean isValidElementType(Type elementType) {
		return !elementType.isLabelType() && !elementType.isMetadataType() && !elementType.isVoidType();
	}

	public String toString() {
		if (addressSpace == 0) {
			if (containedType instanceof StructType) {
				StructType structType = (StructType) containedType;
				String name = "%" + structType.getName();
				return name + "*";
			}
			return containedType + "*";
		} else{
			if (containedType instanceof StructType) {
				StructType structType = (StructType) containedType;
				return "%" + structType.getName() + " addrspace(" + addressSpace + ")*";
			}
			return containedType + " addrspace(" + addressSpace + ")*";
		}
	}
	
	public int getPointerSize(){
		// TODO This will need to be reviewed after we consider target data and ABI alignments
		return 32;
	}
}

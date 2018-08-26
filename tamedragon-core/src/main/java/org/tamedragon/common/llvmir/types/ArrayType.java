package org.tamedragon.common.llvmir.types;

public class ArrayType extends SequentialType{

	private long numElements;

	public ArrayType(CompilationContext compilationContext, Type containedType, long numElements){
		super(compilationContext, TypeID.ARRAY_ID, containedType);
		this.numElements = numElements;
	}

	public long getNumElements(){ return numElements; }


	/**
	 * Utility function that returns true if the type that is passed
	 * can be the type of an element in an array. 
	 */
	public static boolean isValidElementType(Type elementType) {
		return !elementType.isVoidType() && !elementType.isLabelType() &&
		!elementType.isMetadataType() && !elementType.isFunctionType();
	}

	public String toString(){
		if(containedType.isStructType()){
			StructType structType = (StructType)containedType;
			return "[" + numElements + " x " + structType.toString() + "]";
		}
		return "[" + numElements + " x " + containedType.toString() + "]";

	}
	
	@Override
	public boolean isValidIndex(int index) {
		if(index < 0)
			return false;
		else if(index >= numElements)
			return false;
		else 
			return true;
	}
	
	public int getDimension(){
		int dimension = 1;
		ArrayType arrayType = this;
		while(arrayType.getContainedType().isArrayType()){
			dimension++;
			arrayType = (ArrayType) arrayType.getContainedType();
		}
		return dimension;
	}
	
	@Override
	public Type getTypeAtIndex(int index) {
		return containedType;
	}
	
	public Type getActualContainedType(){
		Type containedType = this.getContainedType();
		if(containedType.isArrayType()){
			ArrayType arrayType = (ArrayType)containedType;
			containedType = arrayType.getActualContainedType();
		}
		return containedType;
	}

	public long getSize() {
		long sizeOfContainedType = 0;
		for(Type containedType : containedTypes){
			if(containedType.isPrimitiveType()){
				sizeOfContainedType += containedType.getPrimitiveSizeInBits();
			}
			else if(containedType.isPointerType()){
				PointerType ptrType = (PointerType) containedType;
				sizeOfContainedType += ptrType.getPointerSize();
			}
			else if(containedType.isStructType()){
				StructType strType = (StructType) containedType;
				sizeOfContainedType += strType.getSize();
			}
			else if(containedType.isArrayType()){
				ArrayType arrType = (ArrayType) containedType;
				sizeOfContainedType += arrType.getSize();
			}
		}
		
		return sizeOfContainedType * numElements;
	}
}

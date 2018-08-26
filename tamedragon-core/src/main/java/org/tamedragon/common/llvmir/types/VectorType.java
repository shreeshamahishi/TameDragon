package org.tamedragon.common.llvmir.types;

/**
 * VectorType represents a vector of elements. Vector types are used when multiple primitive data are operated 
 * in parallel using a single instruction (SIMD). A vector type requires a size (number of elements) and an 
 * underlying primitive data type OR a pointer to those. 
 * @author ipsg
 *
 */
public class VectorType extends SequentialType{

	private int numElements;

	public VectorType(CompilationContext compilationContext, Type containedType, int numElements){
		super(compilationContext, TypeID.VECTOR_ID, containedType);
		this.numElements = numElements;
	}

	/**
	 * Utility function that returns true if the type that is passed
	 * can be the elements of a vector. Only integers and floating point
	 * values can be elements of a vector.
	 */
	public static boolean isValidElementType(Type elementType) {
		return elementType.isIntegerType() || elementType.isFloatingPointType();
	}

	public int getNumElements(){ 
		return numElements; 
	}

	public String toString(){
		return "<" + numElements + " x " + containedType.toString() + ">";

	}
}

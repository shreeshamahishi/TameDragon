package org.tamedragon.common.llvmir.types;

import java.util.ArrayList;
import java.util.Vector;

import org.tamedragon.common.llvmir.types.exceptions.TypeCreationException;

public class Type {

	/* Token bindings for various built-in types. The values are directly used
	   in the LLVM IR. */

	protected static final String HALF = "half";
	protected static final String FLOAT = "float";
	protected static final String DOUBLE = "double";
	protected static final String FP128 = "fp128";
	protected static final String X86_FP80 = "x86_fp80";
	protected static final String PPC_FP128 = "ppc_fp128";
	protected static final String VOID = "void";
	protected static final String LABEL = "label";
	protected static final String X86MMX = "x86mmx";
	protected static final String METADATA = "metadata";

	/**
	 * The compilation context in which this Type is under the purview of.
	 */
	protected CompilationContext compilationContext;

	public enum TypeID {
		VOID_ID(0), // 0: type with no size
		HALF_FLOAT_ID(1), // 1: 16-bit floating point type
		FLOAT_ID(2), // 1: 32-bit floating point type
		DOUBLE_ID(3), // 2: 64-bit floating point type
		X86_FP80_ID(4), // 3: 80-bit floating point type (X87)
		FP128_ID(5), // 4: 128-bit floating point type (112-bit mantissa)
		PPC_FP128_ID(6), // 5: 128-bit floating point type (two 64-bits, PowerPC)
		LABEL_ID(7), // 6: Labels
		METADATA_ID(8), // 7: Metadata
		X86_MMX_ID(9), // 8: MMX vectors (64 bits, X86 specific)
		INTEGER_ID(10), // 9: Arbitrary bit width integers
		FUNCTION_ID(11), // 10: Functions
		STRUCT_ID(12), // 11: Structures
		ARRAY_ID(13), // 12: Arrays
		POINTER_ID(14), // 13: Pointers
		VECTOR_ID(15); // 14: SIMD 'packed' format, or other vector type

		private int value;

		private TypeID(int value) {
			this.value = value;
		}

		public int getTypeIdValue() {
			return value;
		}
	}

	/**
	 * This constructor is protected to remind developers to refrain from
	 * creating instances of Type directly using this constructor. To create a
	 * particular type, use the static helper functions in defined in this
	 * class. Eg.: Type.getFloatType(compilationContext). This ensures that the
	 * type that is returned from this function is a singleton within the
	 * purview of the the compilation context.
	 * 
	 * @param compilationContext
	 * @param typeId
	 */
	protected Type(CompilationContext compilationContext, TypeID typeId) {
		this.compilationContext = compilationContext;
		this.typeID = typeId;
	}

	/**
	 * The type Id of this type
	 */
	protected TypeID typeID;

	/**
	 * containedTypes is an array of Types contained by this Type.
	 * For example, this could be the arguments of a function type, the elements
	 * of a structure, the type pointed to by a pointer, the element type of an array,
	 * etc. This pointer may be 0 for types that don't contain other types (eg. primitive types like
	 * IntegerType, DoubleType, FloatType). We use a ArrayList to hold this since this list need not
	 * thread-safe and in a thread-safe scenario, ArrayLists are faster than Vectors.
	 */
	protected ArrayList<Type> containedTypes;

	public ArrayList<Type> getContainedTypes() {
		return containedTypes;
	}

	public TypeID getTypeId() {
		return typeID;
	}

	public CompilationContext getCompilationContext() {
		return compilationContext;
	}

	public void setCompilationContext(CompilationContext compilationContext) {
		this.compilationContext = compilationContext;
	}

	// *************************************************************************************************
	// *************** Static functions to generate singleton primitive types
	// **************************
	// *************************************************************************************************

	public static Type getHalfType(CompilationContext compilationContext) {
		return compilationContext.getHalfType();
	}

	public static Type getFloatType(CompilationContext compilationContext) {
		return compilationContext.getFloatType();
	}

	public static Type getDoubleType(CompilationContext compilationContext) {
		return compilationContext.getDoubleType();
	}

	public static Type getVoidType(CompilationContext compilationContext) {
		return compilationContext.getVoidType();
	}

	public static Type getLabelType(CompilationContext compilationContext) {
		return compilationContext.getLabelType();
	}

	public static Type getMetaDataType(CompilationContext compilationContext) {
		return compilationContext.getMetaDataType();
	}

	public static Type getX86_FP80Type(CompilationContext compilationContext) {
		return compilationContext.getX86_FP80Type();
	}

	public static Type getFP128Type(CompilationContext compilationContext) {
		return compilationContext.getFP128Type();
	}

	public static Type getPPCFP128Type(CompilationContext compilationContext) {
		return compilationContext.getPPCFP128Type();
	}

	public static Type getX86MMXType(CompilationContext compilationContext) {
		return compilationContext.getX86MMXType();
	}

	public static IntegerType getInt1Type(CompilationContext compilationContext, boolean isSigned) {
		return createIntTypesWithStandardSize(compilationContext, 1, isSigned);
	}

	public static IntegerType getInt8Type(CompilationContext compilationContext, boolean isSigned) {
		return createIntTypesWithStandardSize(compilationContext, 8, isSigned);
	}

	public static IntegerType getInt16Type(CompilationContext compilationContext, boolean isSigned) {
		return createIntTypesWithStandardSize(compilationContext, 16, isSigned);
	}

	public static IntegerType getInt32Type(CompilationContext compilationContext, boolean isSigned) {
		return createIntTypesWithStandardSize(compilationContext, 32, isSigned);
	}

	public static IntegerType getInt64Type(CompilationContext compilationContext, boolean isSigned) {
		return createIntTypesWithStandardSize(compilationContext, 64, isSigned);
	}

	private static IntegerType createIntTypesWithStandardSize(
			CompilationContext compilationContext, int numBits, boolean isSigned) {
		IntegerType returnIntType = null;
		try {
			returnIntType = compilationContext.getIntegerType(numBits,isSigned);
		} catch (Exception e) {
		} // Should not happen

		return returnIntType;
	}

	public static IntegerType getIntegerType(
			CompilationContext compilationContext, int numBits, boolean isSigned)
					throws TypeCreationException {
		return compilationContext.getIntegerType(numBits, isSigned);
	}

	public static FunctionType getFunctionType(
			CompilationContext compilationContext, Type returnType,
			boolean isVarArgs, Vector<Type> paramTypes)
					throws TypeCreationException {
		if(returnType == null)
			throw new TypeCreationException("Return type cannot be null");
		return compilationContext.getFunctionType(returnType, isVarArgs,
				paramTypes);
	}

	// *************************************************************************************************
	// *************** Static functions to generate composite types
	// ************************************
	// *************************************************************************************************

	public static StructType getStructType(
			CompilationContext compilationContext, boolean isPacked,
			String name, boolean isUnion, Type... contained) throws TypeCreationException {

		return compilationContext.getStructType(isPacked, name, isUnion, contained);
	}

	public static ArrayType getArrayType(CompilationContext compilationContext,
			Type containedType, long numElements) throws TypeCreationException {

		return compilationContext.getArrayType(containedType, numElements);
	}

	public static VectorType getVectorType(
			CompilationContext compilationContext, Type containedType,
			int numElements) throws TypeCreationException {

		return compilationContext.getVectorType(containedType, numElements);
	}

	public static PointerType getPointerType(
			CompilationContext compilationContext, Type containedType,
			int addressSpace) throws TypeCreationException {

		return compilationContext.getPointerType(containedType, addressSpace);
	}

	// *************************************************************************************************
	// *************** Utility Functions to indicate the type of this Type
	// object***********************
	// *************************************************************************************************

	public boolean isVoidType() {
		return typeID == TypeID.VOID_ID;
	}

	public boolean isFloatType() {
		return typeID == TypeID.FLOAT_ID;
	}

	public boolean isHalfType() {
		return typeID == TypeID.HALF_FLOAT_ID;
	}

	public boolean isDoubleType() {
		return typeID == TypeID.DOUBLE_ID;
	}

	public boolean isX86_FP80Type() {
		return typeID == TypeID.X86_FP80_ID;
	}

	public boolean isFP128Type() {
		return typeID == TypeID.FP128_ID;
	}

	public boolean isPPC_FP128Type() {
		return typeID == TypeID.PPC_FP128_ID;
	}

	public boolean isX86_MMXType() {
		return typeID == TypeID.X86_MMX_ID;
	}

	public boolean isLabelType() {
		return typeID == TypeID.LABEL_ID;
	}

	public boolean isMetadataType() {
		return typeID == TypeID.METADATA_ID;
	}

	public boolean isIntegerType() {
		return typeID == TypeID.INTEGER_ID;
	}

	public boolean isFunctionType() {
		return typeID == TypeID.FUNCTION_ID;
	}

	public boolean isStructType() {
		return typeID == TypeID.STRUCT_ID;
	}

	public boolean isArrayType() {
		return typeID == TypeID.ARRAY_ID;
	}

	public boolean isPointerType() {
		return typeID == TypeID.POINTER_ID;
	}

	public boolean isVectorType() {
		return typeID == TypeID.VECTOR_ID;
	}

	public boolean isFloatingPointType() {
		return typeID == TypeID.FLOAT_ID || typeID == TypeID.DOUBLE_ID
				|| typeID == TypeID.HALF_FLOAT_ID
				|| typeID == TypeID.X86_FP80_ID || typeID == TypeID.FP128_ID
				|| typeID == TypeID.PPC_FP128_ID;
	}

	public boolean isIntOrIntVectorType() {
		if (isIntegerType())
			return true;
		if (typeID != TypeID.VECTOR_ID)
			return false;

		return ((VectorType) this).getContainedType().isIntegerType();
	}

	public boolean isFPOrFPVectorType() {
		if (isFloatingPointType())
			return true;

		if (typeID != TypeID.VECTOR_ID)
			return false;

		return ((VectorType) this).getContainedType().isFloatingPointType();
	}

	private boolean isIntegerOfSize(int size) {
		if (typeID != TypeID.INTEGER_ID)
			return false;

		IntegerType intType = (IntegerType) this;
		if (intType.getNumBits() == size)
			return true;

		return false;

	}

	public boolean isInt1Type() {
		return isIntegerOfSize(1);
	}

	public boolean isInt8Type() {
		return isIntegerOfSize(8);
	}

	public boolean isInt16Type() {
		return isIntegerOfSize(16);
	}

	public boolean isInt32Type() {
		return isIntegerOfSize(32);
	}

	public boolean isInt64Type() {
		return isIntegerOfSize(64);
	}

	public boolean isPrimitiveType() {
		return typeID == TypeID.INTEGER_ID || typeID == TypeID.VOID_ID || typeID == TypeID.FLOAT_ID
				|| typeID == TypeID.DOUBLE_ID || typeID == TypeID.X86_FP80_ID
				|| typeID == TypeID.FP128_ID || typeID == TypeID.PPC_FP128_ID
				|| typeID == TypeID.LABEL_ID || typeID == TypeID.METADATA_ID
				|| typeID == TypeID.X86_MMX_ID;
	}

	public boolean isDerivedType() {
		return typeID == TypeID.FUNCTION_ID
				|| typeID == TypeID.STRUCT_ID || typeID == TypeID.ARRAY_ID
				|| typeID == TypeID.POINTER_ID || typeID == TypeID.VECTOR_ID;
	}

	public boolean isFirstClassType() {
		return !(typeID == TypeID.FUNCTION_ID || typeID == TypeID.VOID_ID);
	}

	// Returns true if the type is a valid type to map to a register.
	// This includes all first-class types except struct and array types.
	public boolean isSingleValueType() {
		return (typeID != TypeID.VOID_ID && isPrimitiveType())
				|| typeID == TypeID.INTEGER_ID || typeID == TypeID.POINTER_ID
				|| typeID == TypeID.VECTOR_ID;
	}

	/**
	 * Returns true if the type is an aggregate type. This means it is valid as
	 * the first operand of an insertvalue or extractvalue instruction. This
	 * includes struct and array types, but does not include vector types.
	 * @return
	 */
	public boolean isAggregateType() {
		return typeID == TypeID.STRUCT_ID || typeID == TypeID.ARRAY_ID;
	}

	public int getPrimitiveSizeInBits() {
		switch (typeID) {
		case FLOAT_ID:
			return 32;
		case DOUBLE_ID:
			return 64;
		case X86_FP80_ID:
			return 80;
		case FP128_ID:
			return 128;
		case PPC_FP128_ID:
			return 128;
		case X86_MMX_ID:
			return 64;
		case INTEGER_ID:
			return ((IntegerType) this).getNumBits();
		case VECTOR_ID:
			return ((VectorType) this).getPrimitiveSizeInBits();

		default:
			return 0;
		}
	}

	public Type getScalarType() {
		if (typeID == TypeID.VECTOR_ID) {
			return ((VectorType) this).getContainedType();
		}
		return this;
	}

	public Type getVectorElementType() {
		if(getTypeId() != TypeID.VECTOR_ID) {
			throw new IllegalArgumentException("Can only invoke getVectorElementType() on Vector types!");
		}
		return ((VectorType) this).getContainedType();
	}

	public int getScalarSizeInBits() {
		return getScalarType().getPrimitiveSizeInBits();
	}

	public String toString() {
		String typeDesc = "";
		if (typeID == TypeID.HALF_FLOAT_ID)
			typeDesc = HALF;
		else if (typeID == TypeID.FLOAT_ID)
			typeDesc = FLOAT;
		else if (typeID == TypeID.DOUBLE_ID)
			typeDesc = DOUBLE;
		else if (typeID == TypeID.FP128_ID)
			typeDesc = FP128;
		else if (typeID == TypeID.X86_FP80_ID)
			typeDesc = X86_FP80;
		else if (typeID == TypeID.PPC_FP128_ID)
			typeDesc = PPC_FP128;
		else if (typeID == TypeID.VOID_ID)
			typeDesc = VOID;
		else if (typeID == TypeID.LABEL_ID)
			typeDesc = LABEL;
		else if (typeID == TypeID.X86_MMX_ID)
			typeDesc = X86MMX;
		else if (typeID == TypeID.METADATA_ID)
			typeDesc = METADATA;

		return typeDesc;
	}

	public boolean isSized() {
		// If it's a primitive, it is always sized.
		if (typeID == TypeID.INTEGER_ID || isFloatingPointType()
				|| typeID == TypeID.POINTER_ID || typeID == TypeID.X86_MMX_ID)
			return true;
		// If it is not something that can have a size (e.g. a function or
		// label),
		// it doesn't have a size.
		if (typeID != TypeID.STRUCT_ID && typeID != TypeID.ARRAY_ID
				&& typeID != TypeID.VECTOR_ID)
			return false;
		// Otherwise we have to try harder to decide.
		return isSizedDerivedType();
	}

	private boolean isSizedDerivedType() {
		if (this.isIntegerType())
			return true;

		if (this.isArrayType()) {
			ArrayType ATy = (ArrayType) this;
			return ATy.getContainedType().isSized();
		}

		if (this.isVectorType()) {
			VectorType VTy = (VectorType) this;
			return VTy.getContainedType().isSized();
		}

		if (!(this.isStructType()))
			return false;
		else {
			// Opaque structs have no size.
			StructType structType = (StructType) this;
			if (structType.isOpaque())
				return false;

			// Okay, our struct is sized if all of the elements are.
			int nosOfElements = structType.getElementSize();
			for (int i = 0; i < nosOfElements; i++) {
				Type t = structType.getTypeAtIndex(i);
				if (!t.isSized())
					return false;
			}
		}
		return true;
	}
}

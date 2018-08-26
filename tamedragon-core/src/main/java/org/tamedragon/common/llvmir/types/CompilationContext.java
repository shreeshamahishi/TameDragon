package org.tamedragon.common.llvmir.types;


import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.tamedragon.common.llvmir.types.Type.TypeID;
import org.tamedragon.common.llvmir.types.exceptions.TypeCreationException;


/**
 * This class is loosely modeled around the LLVMContext class that isolates modules, types
 * and other entities from other contexts in the same address space. We can create a new context
 * in a thread and keep the process compilation contexts isolated in that thread.
 * @author shreesha
 *
 */
public class CompilationContext {

	// Basic type instances provided as singletons. We do not need multiple instances of basic types, so we
	// expose them using singletons.
	private Type HALF_TYPE;
	private Type FLOAT_TYPE;
	private Type DOUBLE_TYPE;
	private Type VOID_TYPE;
	private Type LABEL_TYPE;
	private Type METADATA_TYPE;
	private Type X86_FP80_TYPE;
	private Type FP128_TYPE;
	private Type PPC_FP128_TYPE;
	private Type X86_MMX_TYPE;

	// "Static" pool of derived types
	private Vector<StructType> STRUCT_TYPES_POOL;
	private Vector<ArrayType> ARRAY_TYPES_POOL;
	private Vector<VectorType> VECTOR_TYPES_POOL;
	private Vector<PointerType> POINTER_TYPES_POOL;
	private Vector<PointerType> ASPOINTER_TYPES_POOL;
	private Vector<IntegerType> INTEGER_TYPES_POOL;
	private Vector<FunctionType> FUNCTION_TYPES_POOL;

	// Static pool of undefined values of various types
	private HashMap<Type, UndefValue> undefConstants;

	// Error messages used in exceptions if there an attempt to create a type using invalid data
	// For example, we can create integers with a maximum of 23 bits or we cannot used the void type 
	// as an element in a struct or array.
	public static final String INVALID_ELEMENT_FOR_STRUCT = " cannot be used as element of a struct.";
	public static final String INVALID_ELEMENT_FOR_ARRAY = " cannot be used as element of an array.";
	public static final String INVALID_ELEMENT_FOR_POINTER = " cannot be used as a valid type for a pointer to point to.";
	public static final String INVALID_ELEMENT_FOR_VECTOR = " cannot be used as element of a vector.";
	public static final String INVALID_SIZE_FOR_INTEGER = " is not a valid bit size for an integer type.";
	public static final String INVALID_RETURN_TYPE_FOR_FUNC = " is not a valid return type of a function.";
	public static final String INVALID_PARAM_FOR_FUNC = " is not a valid argument for a function.";

	public ConstantUniqueMap constantsTy = null;
	public List<Module> modules;

	// IIB
	{
		constantsTy = new ConstantUniqueMap();
		modules = new ArrayList<Module>();
	}

	public CompilationContext(){
		STRUCT_TYPES_POOL = new Vector<StructType>();
		ARRAY_TYPES_POOL = new Vector<ArrayType>();
		VECTOR_TYPES_POOL = new Vector<VectorType>();
		POINTER_TYPES_POOL = new Vector<PointerType>();
		ASPOINTER_TYPES_POOL = new Vector<PointerType>();
		INTEGER_TYPES_POOL = new Vector<IntegerType>();
		FUNCTION_TYPES_POOL = new Vector<FunctionType>();

		undefConstants = new HashMap<Type, UndefValue>();
	}


	// ************************************************************************************************
	// *************** Functions to access primitive types. Singletons are ensured. *******************
	// ************************************************************************************************

	public Type getHalfType(){
		if(HALF_TYPE == null)
			HALF_TYPE = new Type(this, TypeID.HALF_FLOAT_ID);

		return HALF_TYPE;
	}


	public Type getFloatType(){
		if(FLOAT_TYPE == null)
			FLOAT_TYPE = new Type(this, TypeID.FLOAT_ID);

		return FLOAT_TYPE;
	}

	public Type getDoubleType(){
		if(DOUBLE_TYPE == null)
			DOUBLE_TYPE = new Type(this, TypeID.DOUBLE_ID);

		return DOUBLE_TYPE;
	}

	public Type getVoidType(){
		if(VOID_TYPE == null)
			VOID_TYPE = new Type(this, TypeID.VOID_ID);

		return VOID_TYPE;
	}

	public Type getLabelType(){
		if(LABEL_TYPE == null)
			LABEL_TYPE = new Type(this, TypeID.LABEL_ID);

		return LABEL_TYPE;
	}

	public Type getMetaDataType(){
		if(METADATA_TYPE == null)
			METADATA_TYPE = new Type(this, TypeID.METADATA_ID);

		return METADATA_TYPE;
	}

	public Type getX86_FP80Type(){
		if(X86_FP80_TYPE == null)
			X86_FP80_TYPE = new Type(this, TypeID.X86_FP80_ID);

		return X86_FP80_TYPE;
	}

	public Type getFP128Type(){
		if(FP128_TYPE == null)
			FP128_TYPE = new Type(this, TypeID.FP128_ID);

		return FP128_TYPE;
	}

	public Type getPPCFP128Type(){
		if(PPC_FP128_TYPE == null)
			PPC_FP128_TYPE = new Type(this, TypeID.PPC_FP128_ID);

		return PPC_FP128_TYPE;
	}

	public Type getX86MMXType(){
		if(X86_MMX_TYPE == null)
			X86_MMX_TYPE = new Type(this, TypeID.X86_MMX_ID);

		return X86_MMX_TYPE;
	}

	/**
	 * This function is required when the client requests for a integer type with "non-standard"
	 * number of bits.
	 * @param numBits
	 * @return
	 */
	public IntegerType getIntegerType(int numBits, boolean isSigned) throws TypeCreationException {

		if(numBits < IntegerType.MIN_NUM_BITS || numBits > IntegerType.MAX_NUM_BITS){
			// Log here
			String errorMessage = numBits + INVALID_SIZE_FOR_INTEGER;
			throw new TypeCreationException(errorMessage);
		}

		for(int i = 0; i < INTEGER_TYPES_POOL.size(); i++){
			IntegerType intType = INTEGER_TYPES_POOL.elementAt(i);
			if(intType.getNumBits() == numBits)
				return intType;
		}

		// Create and return new integer type with required number of bits

		IntegerType newIntType = new IntegerType(this, numBits,isSigned);
		INTEGER_TYPES_POOL.add(newIntType);

		return newIntType;

	}

	// ************************************************************************************************
	// *************** Functions to access composite types. Singletons are ensured. *******************
	// ************************************************************************************************

	public StructType getStructType( boolean isPacked, String name, boolean isUnion, Type ... containedTypes)
	throws TypeCreationException  {

		// Validate the contained types to check if they are good for
		// element of a struct
		if(containedTypes != null){
			for(Type contType : containedTypes){
				if(!StructType.isValidElementType(contType)){
					// Invalid type for a struct element
					// Log here
					String errorMessage = contType + INVALID_ELEMENT_FOR_STRUCT;
					throw new TypeCreationException(errorMessage);
				}
			}
		}

		StructType requiredType = null;

		if(STRUCT_TYPES_POOL.size() == 0)
			requiredType = null;

		outer : for(int i = 0; i < STRUCT_TYPES_POOL.size(); i++){
			StructType structTypeInPool = STRUCT_TYPES_POOL.elementAt(i);

			if(name != null){
				// The name of the struct that is passed is not null; it is a named struct
				String nameOfStructTypeInPool = structTypeInPool.getName();
				if(nameOfStructTypeInPool == null)
					continue outer;
				
				boolean isOpaque = structTypeInPool.isOpaque();
				if(!name.equals(nameOfStructTypeInPool) || isOpaque)
					continue outer;

				requiredType = structTypeInPool;
			}

			if(structTypeInPool.getElementSize() != containedTypes.length)
				continue;

			boolean structurallySimilar = true;
			int count = 0;
			for(Type contType : containedTypes){
				Type typeInStructInPool = structTypeInPool.getTypeAtIndex(count);
				if(typeInStructInPool != contType)
					continue outer;

				count++;
			}

			if(structurallySimilar)
				requiredType = structTypeInPool;
		}

		if(requiredType != null)
			// Already in pool, return that type.
			return requiredType;

		// Create a new type
		requiredType = new StructType(this, isPacked, name, isUnion, containedTypes);

		// Add it to the pool
		STRUCT_TYPES_POOL.add(requiredType);

		return requiredType;
	}

	public ArrayType getArrayType(Type containedType, long numElements) 
	throws TypeCreationException{

		// Check if the contained type is compatible as an array element
		if(!ArrayType.isValidElementType(containedType)){
			// Invalid type for an array element
			// Log here
			String errorMessage = containedType + INVALID_ELEMENT_FOR_ARRAY;
			throw new TypeCreationException(errorMessage);
		}

		ArrayType requiredType = null;

		if(ARRAY_TYPES_POOL.size() == 0)
			requiredType = null;

		for(int i = 0; i < ARRAY_TYPES_POOL.size(); i++){
			ArrayType vectorTypeInPool = ARRAY_TYPES_POOL.elementAt(i);

			if(vectorTypeInPool.getNumElements() == numElements 
					&& vectorTypeInPool.getContainedType() == containedType){

				requiredType = vectorTypeInPool;
				break;
			}
		}

		if(requiredType != null)
			// Already in pool, return that type.
			return requiredType;

		// Create a new type
		requiredType = new ArrayType(this, containedType, numElements);

		// Add it to the pool
		ARRAY_TYPES_POOL.add(requiredType);

		return requiredType;

	}

	public VectorType getVectorType(Type containedType, int numElements) 
	throws TypeCreationException {

		// Check if the contained type is compatible as an array element
		if(!VectorType.isValidElementType(containedType)){
			// Invalid type for a vector element
			// Log here
			String errorMessage = containedType + INVALID_ELEMENT_FOR_VECTOR;
			throw new TypeCreationException(errorMessage);
		}

		VectorType requiredType = null;

		if(VECTOR_TYPES_POOL.size() == 0)
			requiredType = null;

		for(int i = 0; i < VECTOR_TYPES_POOL.size(); i++){
			VectorType vectorTypeInPool = VECTOR_TYPES_POOL.elementAt(i);

			if(vectorTypeInPool.getNumElements() == numElements 
					&& vectorTypeInPool.getContainedType() == containedType){

				requiredType = vectorTypeInPool;
				break;
			}
		}

		if(requiredType != null)
			// Already in pool, return that type.
			return requiredType;

		// Create a new type
		requiredType = new VectorType(this, containedType, numElements);

		// Add it to the pool
		VECTOR_TYPES_POOL.add(requiredType);

		return requiredType;

	}

	public PointerType getPointerType(Type containedType, int addressSpace) throws TypeCreationException {

		// If the contained type is Void type then convert it to i8 type
		if(containedType.isVoidType()){
			containedType = Type.getInt8Type(containedType.getCompilationContext(), true);
		}

		// Check if the contained type is compatible as an array element
		if(!PointerType.isValidElementType(containedType)){
			// Invalid type for a vector element
			// Log here
			String errorMessage = containedType + INVALID_ELEMENT_FOR_POINTER;
			throw new TypeCreationException(errorMessage);
		}

		PointerType requiredType = null;

		Vector<PointerType> REQUIRED_POOL = POINTER_TYPES_POOL;

		if(addressSpace != 0)
			REQUIRED_POOL = ASPOINTER_TYPES_POOL;

		if(REQUIRED_POOL.size() == 0)
			requiredType = null;

		for(int i = 0; i < REQUIRED_POOL.size(); i++){
			PointerType pointerTypeInPool = REQUIRED_POOL.elementAt(i);

			if(pointerTypeInPool.getContainedType() == containedType
					&& pointerTypeInPool.getAddressSpace() == addressSpace){
				requiredType = pointerTypeInPool;
				break;
			}
		}

		if(requiredType != null)
			// Already in pool, return that type.
			return requiredType;

		// Create a new type
		requiredType = new PointerType(this, containedType, addressSpace);

		// Add it to the pool
		REQUIRED_POOL.add(requiredType);

		return requiredType;

	}

	public FunctionType getFunctionType(Type returnType, boolean isVarArgs, Vector<Type> paramTypes)
	throws TypeCreationException {

		// Initialize to an empty string if a null value has been passed for 
		// the parameter types
		if(paramTypes == null)
			paramTypes = new Vector<Type>();

		// Check for a valid return type
		if(!FunctionType.isValidReturnType(returnType)){
			// Invalid return type for a function
			// Log here
			String errorMessage = returnType + INVALID_RETURN_TYPE_FOR_FUNC;
			throw new TypeCreationException(errorMessage);
		}

		// Validate the contained types to check if they are good for
		// a function parameter

		for(int i = 0; i < paramTypes.size(); i++){
			Type requiredParamType = paramTypes.elementAt(i);
			if(!FunctionType.isValidArgumentType(requiredParamType)){
				// Invalid argument type for a function
				// Log here
				String errorMessage = requiredParamType + INVALID_PARAM_FOR_FUNC;
				throw new TypeCreationException(errorMessage);
			}
		}

		FunctionType requiredType = null;

		if(FUNCTION_TYPES_POOL.size() == 0)
			requiredType = null;

		outer : for(int i = 0; i < FUNCTION_TYPES_POOL.size(); i++){
			FunctionType functionTypeInPool = FUNCTION_TYPES_POOL.elementAt(i);

			if(functionTypeInPool.getReturnType() != returnType)
				continue;

			int numParamsOfFunctionInPool = functionTypeInPool.getNumParams();
			if(numParamsOfFunctionInPool != paramTypes.size())
				continue;

			if(isVarArgs && !functionTypeInPool.isVarArg())
				continue;

			if(!isVarArgs && functionTypeInPool.isVarArg())
				continue;

			for(int j = 0; j < numParamsOfFunctionInPool; j++){
				Type paramTypeOfFuncInPool = functionTypeInPool.getParamType(j);
				Type requiredParamType = paramTypes.elementAt(j);

				if(paramTypeOfFuncInPool != requiredParamType)
					continue outer;
			}

			requiredType = functionTypeInPool;
			break;
		}

		if(requiredType != null)
			// Already in pool, return that type.
			return requiredType;

		// Create a new type
		requiredType = new FunctionType(this, returnType, paramTypes, isVarArgs);

		// Add it to the pool
		FUNCTION_TYPES_POOL.add(requiredType);

		return requiredType;
	}

	public UndefValue getUndefinedValueConstants(Type type) {
		if(type.getCompilationContext() != this){
			//Error! This instance must be the context of the type passed
			//  TODO Log here
			System.exit(-1);
		}

		UndefValue constUndefVal = undefConstants.get(type);
		if(constUndefVal == null){
			constUndefVal = new UndefValue(type, null);
			undefConstants.put(type, constUndefVal);
		}

		return constUndefVal;
	}

	public void addModule(Module module){
		modules.add(module);
	}

	public List<Module> getModules() {
		return modules;
	}
}

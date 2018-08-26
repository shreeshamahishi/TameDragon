package org.tamedragon.dagir.valuetypes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tamedragon.common.llvmir.types.CompilationContext;
import org.tamedragon.common.llvmir.types.IntegerType;
import org.tamedragon.common.llvmir.types.Type;
import org.tamedragon.common.llvmir.types.VectorType;
import org.tamedragon.common.llvmir.types.Type.TypeID;
import org.tamedragon.common.llvmir.types.exceptions.TypeCreationException;


/// compareRawBits - A meaningless but well-behaved order, useful for
/// constructing containers.
class compareRawBits {
	public boolean evtsAreEqual(ExtendedValueType L, ExtendedValueType R) {
		if (L.getMachineValueType().getSimpleType() == R.getMachineValueType().getSimpleType())
			//TODO review what we do here
			//return L.getLL < R.LLVMTy;
			return true;
		else
			return L.getMachineValueType().getSimpleType().isLesserThan(R.getMachineValueType().getSimpleType());
	}
}

public class ExtendedValueType {
	
	private static final Logger LOG = LoggerFactory.getLogger(ExtendedValueType.class);
	
	private static final String INVALID_TYPE_FOR_VECTOR = "Simple vector VT not representable by simple integer vector VT";
	private static final String SIMPLE_TYPE_EXPECTED = "Expected a SimpleValueType.";
	private static final String INVALID_VECTOR_TYPE = "Invalid vector type.";
	
	private MachineValueType V;
	private Type llvmType;

	public ExtendedValueType()  {
		V = new MachineValueType(SimpleValueType.INVALID_SIMPLE_VALUE_TYPE);
		llvmType = null;
	}

	public ExtendedValueType(SimpleValueType SVT) {
		V = new MachineValueType(SVT);
		llvmType = null;
	}

	public ExtendedValueType(MachineValueType MVT) {
		V = MVT;
		llvmType = null;
	}

	private void setLLVMType(Type type) {
		llvmType = type;
	}

	public MachineValueType getMachineValueType(){
		return V;
	}

	@Override
	public boolean equals(Object other){
		if(!(other instanceof ExtendedValueType)){
			return false;
		}

		ExtendedValueType otherEvt = (ExtendedValueType) other;

		if(V.getSimpleType() == SimpleValueType.INVALID_SIMPLE_VALUE_TYPE){
			if(llvmType != otherEvt.getLLVMType()){
				return false;
			}
			else{
				return true;
			}
		}
		else{
			if(V.getSimpleType().equals(otherEvt.getSimpleVT())){
				return true;
			}
			else{
				return false;
			}
		}
	}

	private Type getLLVMType() {
		return llvmType;
	}

	/// getFloatingPointVT - Returns the ExtendedValueType that represents a floating point
	/// type with the given number of bits.  There are two floating point types
	/// with 128 bits - this returns f128 rather than ppcf128.
	public static ExtendedValueType getFloatingPointVT(int BitWidth) {
		return new ExtendedValueType(MachineValueType.getFloatingPointVT(BitWidth));
	}

	/// getIntegerVT - Returns the ExtendedValueType that represents an integer with the given
	/// number of bits.
	public static ExtendedValueType getIntegerVT(CompilationContext Context, int BitWidth) {
		MachineValueType M = MachineValueType.getIntegerVT(BitWidth);
		if (M.getSimpleType() != SimpleValueType.INVALID_SIMPLE_VALUE_TYPE)
			return new ExtendedValueType(M);

		return getExtendedIntegerVT(Context, BitWidth);
	}

	public static ExtendedValueType getExtendedIntegerVT(CompilationContext compilationContext, int BitWidth){
		try{
			ExtendedValueType VT = new ExtendedValueType();
			VT.setLLVMType(Type.getIntegerType(compilationContext, BitWidth, true));
			return VT;
		}
		catch(TypeCreationException e){
			//TODO Log this
			e.printStackTrace();
			System.exit(-1);
		}
		return null;
	}

	/// getVectorVT - Returns the ExtendedValueType that represents a vector NumElements in
	/// length, where each element is of type VT.
	public static ExtendedValueType getVectorVT(CompilationContext compilationContext, ExtendedValueType VT, int NumElements) {
		MachineValueType M = MachineValueType.getVectorVT(VT.V, NumElements);
		if (M.getSimpleType() != SimpleValueType.INVALID_SIMPLE_VALUE_TYPE)
			return new ExtendedValueType(M);

		return getExtendedVectorVT(compilationContext, VT, NumElements);
	}

	public static ExtendedValueType getExtendedVectorVT(CompilationContext Context, ExtendedValueType VT,
			int NumElements){
		try{
			ExtendedValueType ResultVT = new ExtendedValueType();

			ResultVT.setLLVMType(Type.getVectorType(Context, VT.getTypeForEVT(Context), NumElements));
			//assert(ResultVT.isExtended() && "Type is not extended!");
			return ResultVT;
		}
		catch(TypeCreationException e){
			//TODO Log this
			e.printStackTrace();
			System.exit(-1);
		}

		return null;
	}

	/// changeVectorElementTypeToInteger - Return a vector with the same number
	/// of elements as this vector, but with the element type converted to an
	/// integer type with the same bitwidth.
	ExtendedValueType changeVectorElementTypeToInteger() {
		if (!isSimple())
			return changeExtendedVectorElementTypeToInteger();
		MachineValueType EltTy = getSimpleVT().getVectorElementType();
		int BitWidth = EltTy.getSizeInBits();
		MachineValueType IntTy = MachineValueType.getIntegerVT(BitWidth);
		MachineValueType VecTy = MachineValueType.getVectorVT(IntTy, getVectorNumElements());
		
		boolean isInvalidSimpleValueType = VecTy.getSimpleType() == SimpleValueType.INVALID_SIMPLE_VALUE_TYPE;
		if(isInvalidSimpleValueType){
			LOG.error(INVALID_TYPE_FOR_VECTOR);	
			assert false : INVALID_TYPE_FOR_VECTOR;
			return null;
		}
		
		return new ExtendedValueType(VecTy);
	}

	private ExtendedValueType changeExtendedVectorElementTypeToInteger(){
		CompilationContext context = llvmType.getCompilationContext();
		ExtendedValueType IntTy = getIntegerVT(context, getVectorElementType().getSizeInBits());
		return getVectorVT(context, IntTy, getVectorNumElements());
	}

	/// isSimple - Test if the given ExtendedValueType is simple (as opposed to being
	/// extended).
	public boolean isSimple() {
		if(V.getSimpleType().isLesserThanOrEqualTo(SimpleValueType.LastSimpleValueType))
			return true;

		return false ;
	}

	/// isExtended - Test if the given ExtendedValueType is extended (as opposed to
	/// being simple).
	public boolean isExtended() {
		return !isSimple();
	}

	/// isFloatingPoint - Return true if this is a FP, or a vector FP type.
	public boolean isFloatingPoint() {
		return isSimple() ? V.isFloatingPoint() : isExtendedFloatingPoint();
	}

	/// isInteger - Return true if this is an integer, or a vector integer type.
	public boolean isInteger() {
		return isSimple() ? V.isInteger() : isExtendedInteger();
	}

	/// isVector - Return true if this is a vector value type.
	public boolean isVector() {
		return isSimple() ? V.isVector() : isExtendedVector();
	}

	/// is64BitVector - Return true if this is a 64-bit vector type.
	public boolean is64BitVector() {
		if (!isSimple())
			return isExtended64BitVector();

		SimpleValueType svt = V.getSimpleType();

		return (svt == SimpleValueType.v8i8  || svt == SimpleValueType.v4i16 || svt == SimpleValueType.v2i32 ||
				svt == SimpleValueType.v1i64 || svt == SimpleValueType.v2f32);
	}

	/// is128BitVector - Return true if this is a 128-bit vector type.
	public boolean is128BitVector() {
		if (!isSimple())
			return isExtended128BitVector();

		SimpleValueType svt = V.getSimpleType();

		return (svt == SimpleValueType.v16i8 || svt == SimpleValueType.v8i16 || svt == SimpleValueType.v4i32 ||
				svt == SimpleValueType.v2i64 || svt == SimpleValueType.v4f32 || svt == SimpleValueType.v2f64);
	}

	/// is256BitVector - Return true if this is a 256-bit vector type.
	private boolean is256BitVector() {
		if (!isSimple())
			return isExtended256BitVector();

		SimpleValueType svt = V.getSimpleType();
		return (svt == SimpleValueType.v8f32 || svt == SimpleValueType.v4f64 || svt == SimpleValueType.v32i8 ||
				svt == SimpleValueType.v16i16 || svt == SimpleValueType.v8i32 || svt == SimpleValueType.v4i64);

	}

	/// is512BitVector - Return true if this is a 512-bit vector type.
	private boolean is512BitVector() {
		SimpleValueType svt = V.getSimpleType();
		return isSimple() ? svt == SimpleValueType.v8i64 : isExtended512BitVector();

	}

	/// isOverloaded - Return true if this is an overloaded type for TableGen.
	boolean isOverloaded() {

		SimpleValueType svt = V.getSimpleType();

		return (svt == SimpleValueType.iAny || svt == SimpleValueType.fAny || 
				svt == SimpleValueType.vAny ||svt == SimpleValueType.iPTRAny);
	}

	/// isByteSized - Return true if the bit size is a multiple of 8.
	public boolean isByteSized() {
		return (getSizeInBits() & 7) == 0;
	}

	/// isRound - Return true if the size is a power-of-two number of bytes.
	public boolean isRound() {
		int BitSize = getSizeInBits();
		boolean flag = false;
		if((BitSize & (BitSize - 1)) > 0){
			flag = true;
		}
		return BitSize >= 8 && !flag;
	}

	/// bitsEq - Return true if this has the same number of bits as VT.
	public boolean bitsEq(ExtendedValueType VT) {
		if (this.equals(VT)) 
			return true;

		return getSizeInBits() == VT.getSizeInBits();
	}

	/// bitsGT - Return true if this has more bits than VT.
	public boolean bitsGT(ExtendedValueType VT) {
		if (this.equals(VT)) 
			return false;

		return getSizeInBits() > VT.getSizeInBits();
	}

	/// bitsGE - Return true if this has no less bits than VT.
	public boolean bitsGE(ExtendedValueType VT) {
		if (this.equals(VT)) 
			return true;
		return getSizeInBits() >= VT.getSizeInBits();
	}

	/// bitsLT - Return true if this has less bits than VT.
	boolean bitsLT(ExtendedValueType VT) {
		if (this.equals(VT)) 
			return false;
		return getSizeInBits() < VT.getSizeInBits();
	}

	/// bitsLE - Return true if this has no more bits than VT.
	boolean bitsLE(ExtendedValueType VT) {
		if (this.equals(VT)) 
			return true;
		return getSizeInBits() <= VT.getSizeInBits();
	}


	/// getSimpleVT - Return the SimpleValueType held in the specified
	/// simple ExtendedValueType.
	public MachineValueType getSimpleVT() {
		
		if(!isSimple()){
			assert false : SIMPLE_TYPE_EXPECTED;
			LOG.warn(SIMPLE_TYPE_EXPECTED);
		}
		return V;
	}

	/// getScalarType - If this is a vector type, return the element type,
	/// otherwise return this.
	public ExtendedValueType getScalarType() {
		return isVector() ? getVectorElementType() : this;
	}

	/// getVectorElementType - Given a vector type, return the type of
	/// each element.
	public ExtendedValueType getVectorElementType() {
		if(!isVector()){
			assert false : INVALID_VECTOR_TYPE;
			LOG.error(INVALID_VECTOR_TYPE);
		}

		if (isSimple())
			return new ExtendedValueType(V.getVectorElementType());
		return getExtendedVectorElementType();
	}

	/// getVectorNumElements - Given a vector type, return the number of
	/// elements it contains.
	public int getVectorNumElements() {
		
		if(!isVector()){
			assert false :INVALID_VECTOR_TYPE; 
			LOG.error(INVALID_VECTOR_TYPE);
			return -1;
		}

		if (isSimple())
			return V.getVectorNumElements();
		return getExtendedVectorNumElements();
	}

	/// getSizeInBits - Return the size of the specified value type in bits.
	int getSizeInBits() {
		if (isSimple())
			return V.getSizeInBits();
		return getExtendedSizeInBits();
	}

	/// getStoreSize - Return the number of bytes overwritten by a store
	/// of the specified value type.
	public int getStoreSize() {
		return (getSizeInBits() + 7) / 8;
	}

	/// getStoreSizeInBits - Return the number of bits overwritten by a store
	/// of the specified value type.
	public int getStoreSizeInBits() {
		return getStoreSize() * 8;
	}

	/// getRoundIntegerType - Rounds the bit-width of the given integer ExtendedValueType up
	/// to the nearest power of two (and at least to eight), and returns the
	/// integer ExtendedValueType with that number of bits.
	public ExtendedValueType getRoundIntegerType(CompilationContext Context) {
		
		if(!(isInteger() && !isVector())){
			LOG.error(INVALID_VECTOR_TYPE);
			return null;
		}
		int BitWidth = getSizeInBits();
		if (BitWidth <= 8)
			return new ExtendedValueType(new MachineValueType(SimpleValueType.i8));
		return getIntegerVT(Context, 1 << (int) (Math.log(BitWidth)/Math.log(2)));
	}

	/// getHalfSizedIntegerVT - Finds the smallest simple value type that is
	/// greater than or equal to half the width of this ExtendedValueType. If no simple
	/// value type can be found, an extended integer value type of half the
	/// size (rounded up) is returned.
	public ExtendedValueType getHalfSizedIntegerVT(CompilationContext Context) {
		if(!(isInteger() && !isVector())){
			LOG.error(INVALID_VECTOR_TYPE);
			return null;
		}

		int ExtendedValueTypeSize = getSizeInBits();
		for (int IntVT = SimpleValueType.FIRST_INTEGER_VALUETYPE.getIndex();
		IntVT <= SimpleValueType.LAST_INTEGER_VALUETYPE.getIndex(); IntVT++) {
			ExtendedValueType HalfVT = new ExtendedValueType((new MachineValueType(SimpleValueType.values()[IntVT])));
			if (HalfVT.getSizeInBits() * 2 >= ExtendedValueTypeSize)
				return HalfVT;
		}
		return getIntegerVT(Context, (ExtendedValueTypeSize + 1) / 2);
	}

	/// isPow2VectorType - Returns true if the given vector is a power of 2.
	public boolean isPow2VectorType() {
		int NElts = getVectorNumElements();
		boolean flag = false;
		if((NElts & (NElts - 1)) > 0){
			flag = true; 
		}
		return !flag;
	}

	/// getPow2VectorType - Widens the length of the given vector ExtendedValueType up to
	/// the nearest power of 2 and returns that type.
	public ExtendedValueType getPow2VectorType(CompilationContext Context) {
		if (!isPow2VectorType()) {
			int NElts = getVectorNumElements();
			int Pow2NElts = 1 << (int) (Math.log(NElts)/Math.log(2));
			return ExtendedValueType.getVectorVT(Context, getVectorElementType(), Pow2NElts);
		}
		else {
			return this;
		}
	}

	/// getEVTString - This function returns value type as a string,
	/// e.g. "i32".
	public String getEVTString(){
		switch (V.getSimpleType()) {
		default:
			if (isVector())
				return "v" + getVectorNumElements() +
				getVectorElementType().getEVTString();
			if (isInteger())
				return "i" + getSizeInBits();
			//TODO : Not reachable, invalid EVT
			LOG.error("Invalid EVT!");
		case i1:      return "i1";
		case i8:      return "i8";
		case i16:     return "i16";
		case i32:     return "i32";
		case i64:     return "i64";
		case i128:    return "i128";
		case f16:     return "f16";
		case f32:     return "f32";
		case f64:     return "f64";
		case f80:     return "f80";
		case f128:    return "f128";
		case ppcf128: return "ppcf128";
		case isVoid:  return "isVoid";
		case Other:   return "ch";
		case Glue:    return "glue";
		case x86mmx:  return "x86mmx";
		case v2i8:    return "v2i8";
		case v4i8:    return "v4i8";
		case v8i8:    return "v8i8";
		case v16i8:   return "v16i8";
		case v32i8:   return "v32i8";
		case v2i16:   return "v2i16";
		case v4i16:   return "v4i16";
		case v8i16:   return "v8i16";
		case v16i16:  return "v16i16";
		case v2i32:   return "v2i32";
		case v4i32:   return "v4i32";
		case v8i32:   return "v8i32";
		case v1i64:   return "v1i64";
		case v2i64:   return "v2i64";
		case v4i64:   return "v4i64";
		case v8i64:   return "v8i64";
		case v2f32:   return "v2f32";
		case v2f16:   return "v2f16";
		case v4f32:   return "v4f32";
		case v8f32:   return "v8f32";
		case v2f64:   return "v2f64";
		case v4f64:   return "v4f64";
		case Metadata:return "Metadata";
		case Untyped: return "Untyped";
		}
	}


	/// getTypeForEVT - This method returns an LLVM type corresponding to the
	/// specified EVT.  For integer types, this returns an unsigned type.  Note
	/// that this will abort for types that cannot be represented.
	public Type getTypeForEVT(CompilationContext Context){
		try{
			switch (V.getSimpleType()) {
			default:
				if(!isExtended()){
					LOG.warn("Type is not extended!");
					return null;
				}
				
				return llvmType;

			case isVoid:  return Type.getVoidType(Context);
			case i1:      return Type.getInt1Type(Context, true);
			case i8:      return Type.getInt8Type(Context, true);
			case i16:     return Type.getInt16Type(Context, true);
			case i32:     return Type.getInt32Type(Context, true);
			case i64:     return Type.getInt64Type(Context, true);
			//case i128:    return IntegerType.get(Context, 128);
			case f16:     return Type.getHalfType(Context);
			case f32:     return Type.getFloatType(Context);
			case f64:     return Type.getDoubleType(Context);
			case f80:     return Type.getX86_FP80Type(Context);
			case f128:    return Type.getFP128Type(Context);
			case ppcf128: return Type.getPPCFP128Type(Context);
			case x86mmx:  return Type.getX86MMXType(Context);
			case v2i8:    return Type.getVectorType(Context, Type.getInt8Type(Context, true), 2);
			case v4i8:    return Type.getVectorType(Context, Type.getInt8Type(Context, true), 4);
			case v8i8:    return Type.getVectorType(Context, Type.getInt8Type(Context, true), 8);
			case v16i8:   return Type.getVectorType(Context, Type.getInt8Type(Context, true), 16);
			case v32i8:   return Type.getVectorType(Context, Type.getInt8Type(Context, true), 32);
			case v2i16:   return Type.getVectorType(Context, Type.getInt16Type(Context, true), 2);
			case v4i16:   return Type.getVectorType(Context, Type.getInt16Type(Context, true), 4);
			case v8i16:   return Type.getVectorType(Context, Type.getInt16Type(Context, true), 8);
			case v16i16:  return Type.getVectorType(Context, Type.getInt16Type(Context, true), 16);
			case v2i32:   return Type.getVectorType(Context, Type.getInt32Type(Context, true), 2);
			case v4i32:   return Type.getVectorType(Context, Type.getInt32Type(Context, true), 4);
			case v8i32:   return Type.getVectorType(Context, Type.getInt32Type(Context, true), 8);
			case v1i64:   return Type.getVectorType(Context, Type.getInt64Type(Context, true), 1);
			case v2i64:   return Type.getVectorType(Context, Type.getInt64Type(Context, true), 2);
			case v4i64:   return Type.getVectorType(Context, Type.getInt64Type(Context, true), 4);
			case v8i64:   return Type.getVectorType(Context, Type.getInt64Type(Context, true), 8);
			case v2f16:   return Type.getVectorType(Context, Type.getHalfType(Context), 2);
			case v2f32:   return Type.getVectorType(Context, Type.getFloatType(Context), 2);
			case v4f32:   return Type.getVectorType(Context, Type.getFloatType(Context), 4);
			case v8f32:   return Type.getVectorType(Context, Type.getFloatType(Context), 8);
			case v2f64:   return Type.getVectorType(Context, Type.getDoubleType(Context), 2);
			case v4f64:   return Type.getVectorType(Context, Type.getDoubleType(Context), 4); 
			case Metadata: return Type.getMetaDataType(Context);
			}
		}
		catch(TypeCreationException e){
			LOG.error("Unable to create a type.");
			e.printStackTrace();
			System.exit(-1);
		}
		return null;
	}

	public int getRawBits() {
		if (isSimple())
			return V.getSimpleType().getIndex();
		else
			return llvmType.getPrimitiveSizeInBits();
	}

	public boolean isExtendedFloatingPoint(){
		if(!(isExtended())){
			LOG.error("Type is not extended!");
		}

		return llvmType.isFPOrFPVectorType();
	}

	public boolean isExtendedInteger(){
		if(!(isExtended())){
			LOG.error("Type is not extended!");
		}
		return llvmType.isIntOrIntVectorType();
	}

	public boolean isExtendedVector(){
		if(!(isExtended())){
			LOG.error("Type is not extended!");
		}
		return llvmType.isVectorType();
	}

	public boolean isExtended64BitVector(){
		return isExtendedVector() && getSizeInBits() == 64;
	}

	public boolean isExtended128BitVector(){
		return isExtendedVector() && getSizeInBits() == 128;
	}

	public boolean isExtended256BitVector(){
		return isExtendedVector() && getSizeInBits() == 256;
	}

	public boolean isExtended512BitVector(){
		return isExtendedVector() && getSizeInBits() == 512;
	}

	public ExtendedValueType getExtendedVectorElementType(){
		//TODO Implement this later
		//		if(!(isExtended())){
		//			// TODO Log error here
		//			LOG.error("Type is not extended!");
		//		}
		//		  return getEx(cast<VectorType>(LLVMTy)->getElementType());
		return null;
	}

	public int getExtendedVectorNumElements(){
		//TODO Implement this later
		return 0;
	}

	public int getExtendedSizeInBits(){
		if(!isExtended()){
			//TODO  Log error
			LOG.error("Type is not extended!");
			return -1;
		}

		TypeID typeId = llvmType.getTypeId();
		if (typeId == TypeID.INTEGER_ID){
			return ((IntegerType)llvmType).getNumBits();
		}
		else if(typeId == TypeID.VECTOR_ID)
			return ((VectorType)llvmType).getPrimitiveSizeInBits();
		else{
			LOG.error("Unrecognized extended type.");
			return -1;
		}
	}
}

package org.tamedragon.dagir.valuetypes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MachineValueType {

	private SimpleValueType SimpleTy;
	
	private static final Logger LOG = LoggerFactory.getLogger(ExtendedValueType.class);

	public MachineValueType() {
		SimpleTy = SimpleValueType.INVALID_SIMPLE_VALUE_TYPE;
	}

	public MachineValueType(SimpleValueType svt){
		SimpleTy = svt;
	}

	public SimpleValueType getSimpleType(){
		return SimpleTy;
	}
	
	public boolean isGreaterThan(MachineValueType S) {
		return SimpleTy.isGreaterThan(S.SimpleTy); 
	}

	public boolean isGreaterThanOrEqualTo(MachineValueType S) {
		return SimpleTy.isGreaterThanOrEqualTo(S.SimpleTy); 
	}
	
	public boolean isLesserThan(MachineValueType S) {
		return SimpleTy.isLesserThan(S.SimpleTy); 
	}
	
	public boolean isLesserThanOrEqualTo(MachineValueType S) {
		return SimpleTy.isLesserThanOrEqualTo(S.SimpleTy); 
	}
	
	@Override
	public boolean equals(Object other){
		if(!(other instanceof MachineValueType)){
			return false;
		}
		
		MachineValueType otherMvt = (MachineValueType) other;
		if(SimpleTy == otherMvt.SimpleTy)
			return true;
		
		return false;
	}
	
	/// isFloatingPoint - Return true if this is a FP, or a vector FP type.
	public boolean isFloatingPoint() {
		return ((SimpleTy.isGreaterThanOrEqualTo(SimpleValueType.FIRST_FP_VALUETYPE) &&
		SimpleTy.isLesserThanOrEqualTo(SimpleValueType.LAST_FP_VALUETYPE)) ||
		(SimpleTy.isGreaterThanOrEqualTo(SimpleValueType.FIRST_FP_VECTOR_VALUETYPE) &&
		SimpleTy.isLesserThanOrEqualTo(SimpleValueType.LAST_FP_VECTOR_VALUETYPE)));
	}
	
	/// isFloatingPoint - Return true if this is a FP, or a vector FP type.
	public boolean isInteger() {
		return ((SimpleTy.isGreaterThanOrEqualTo(SimpleValueType.FIRST_INTEGER_VALUETYPE) &&
		SimpleTy.isLesserThanOrEqualTo(SimpleValueType.LAST_INTEGER_VALUETYPE)) ||
		(SimpleTy.isGreaterThanOrEqualTo(SimpleValueType.v2i8) &&
		SimpleTy.isLesserThanOrEqualTo(SimpleValueType.v8i64)));
	}

	public boolean isVector() {
		return ((SimpleTy.isGreaterThanOrEqualTo(SimpleValueType.FIRST_VECTOR_VALUETYPE) &&
		SimpleTy.isLesserThanOrEqualTo(SimpleValueType.LAST_VECTOR_VALUETYPE)));
	}

	/// isPow2VectorType - Returns true if the given vector is a power of 2.
	public boolean isPow2VectorType() {
		int numElements = getVectorNumElements();
		if((numElements & (numElements - 1)) > 0)
			return false;
		return true;
	}

	/// getPow2VectorType - Widens the length of the given vector MachineValueTypes up to
	/// the nearest power of 2 and returns that type.
	public MachineValueType getPow2VectorType() {
		if (isPow2VectorType())
			return this;

		int numElements = getVectorNumElements();
		int pow2NumElements = 1 << (int) (Math.log(numElements)/Math.log(2));
		return MachineValueType.getVectorVT(getVectorElementType(), pow2NumElements);
	}

	/// getScalarType - If this is a vector type, return the element type,
	/// otherwise return this.
	public MachineValueType getScalarType() {
		return isVector() ? getVectorElementType() : this;
	}

	public MachineValueType getVectorElementType(){
		switch (SimpleTy) {
		default:
			return new MachineValueType(SimpleValueType.INVALID_SIMPLE_VALUE_TYPE);
		case v2i8 :
		case v4i8 :
		case v8i8 :
		case v16i8:
		case v32i8: return new MachineValueType(SimpleValueType.i8);
		case v2i16:
		case v4i16:
		case v8i16:
		case v16i16: return new MachineValueType(SimpleValueType.i16);
		case v2i32:
		case v4i32:
		case v8i32: return new MachineValueType(SimpleValueType.i32);
		case v1i64:
		case v2i64:
		case v4i64:
		case v8i64: return new MachineValueType(SimpleValueType.i64);
		case v2f16: return new MachineValueType(SimpleValueType.f16);
		case v2f32:
		case v4f32:
		case v8f32: return new MachineValueType(SimpleValueType.f32);
		case v2f64:
		case v4f64: return new MachineValueType(SimpleValueType.f64);
		}
	}

	public int getVectorNumElements() {
		switch (SimpleTy) {
		default:
			return ~0;
		case v32i8: return 32;
		case v16i8:
		case v16i16: return 16;
		case v8i8 :
		case v8i16:
		case v8i32:
		case v8i64:
		case v8f32: return 8;
		case v4i8:
		case v4i16:
		case v4i32:
		case v4i64:
		case v4f32:
		case v4f64: return 4;
		case v2i8:
		case v2i16:
		case v2i32:
		case v2i64:
		case v2f16:
		case v2f32:
		case v2f64: return 2;
		case v1i64: return 1;
		}
	}

	public int getSizeInBits(){
		switch (SimpleTy) {
		case iPTR:{
			LOG.warn("Value type size is target-dependent. Ask TLI.");
			return -1;
		}
		case iPTRAny:
		case iAny:
		case fAny:
		{
			LOG.warn("Value type is overloaded.");
			return -1;
		}
		default:
		{
			LOG.warn("getSizeInBits called on extended MachineValueTypes.");
			return -1;
		}
		case i1  :  return 1;
		case i8  :  return 8;
		case i16 :
		case f16:
		case v2i8:  return 16;
		case f32 :
		case i32 :
		case v4i8:
		case v2i16:
		case v2f16: return 32;
		case x86mmx:
		case f64 :
		case i64 :
		case v8i8:
		case v4i16:
		case v2i32:
		case v1i64:
		case v2f32: return 64;
		case f80 :  return 80;
		case f128:
		case ppcf128:
		case i128:
		case v16i8:
		case v8i16:
		case v4i32:
		case v2i64:
		case v4f32:
		case v2f64: return 128;
		case v32i8:
		case v16i16:
		case v8i32:
		case v4i64:
		case v8f32:
		case v4f64: return 256;
		case v8i64: return 512;
		}
	}

	/// getStoreSize - Return the number of bytes overwritten by a store
	/// of the specified value type.
	public int getStoreSize(){
		return (getSizeInBits() + 7) / 8;
	}

	/// getStoreSizeInBits - Return the number of bits overwritten by a store
	/// of the specified value type.
	public int getStoreSizeInBits(){
		return getStoreSize() * 8;
	}

	public static MachineValueType getFloatingPointVT(int bitWidth) {
		switch (bitWidth) {
		default:
			LOG.error("Bad bit width!");
			return null;
		case 16:
			return new MachineValueType(SimpleValueType.f16);
		case 32:
			return new MachineValueType(SimpleValueType.f32);
		case 64:
			return new MachineValueType(SimpleValueType.f64);
		case 80:
			return new MachineValueType(SimpleValueType.f80);
		case 128:
			return new MachineValueType(SimpleValueType.f128);
		}
	}

	static MachineValueType getIntegerVT(int BitWidth) {
		switch (BitWidth) {
		default:
			return new MachineValueType(SimpleValueType.INVALID_SIMPLE_VALUE_TYPE);
		case 1:
			return new MachineValueType(SimpleValueType.i1);
		case 8:
			return new MachineValueType(SimpleValueType.i8);
		case 16:
			return new MachineValueType(SimpleValueType.i16);
		case 32:
			return new MachineValueType(SimpleValueType.i32);
		case 64:
			return new MachineValueType(SimpleValueType.i64);
		case 128:
			return new MachineValueType(SimpleValueType.i128);
		}
	}

	public static MachineValueType getVectorVT(MachineValueType VT, int NumElements) {
		switch (VT.SimpleTy) {
		
		default:
			break;
		case i8:
		if (NumElements == 2)  return new MachineValueType(SimpleValueType.v2i8);
		if (NumElements == 4)  return new MachineValueType(SimpleValueType.v4i8);
		if (NumElements == 8)  return new MachineValueType(SimpleValueType.v8i8);
		if (NumElements == 16) return new MachineValueType(SimpleValueType.v16i8);
		if (NumElements == 32) return new MachineValueType(SimpleValueType.v32i8);
		break;
		
		case i16:
		if (NumElements == 2)  return new MachineValueType(SimpleValueType.v2i16);
		if (NumElements == 4)  return new MachineValueType(SimpleValueType.v4i16);
		if (NumElements == 8)  return new MachineValueType(SimpleValueType.v8i16);
		if (NumElements == 16) return new MachineValueType(SimpleValueType.v16i16);
		break;
		
		case i32:
		if (NumElements == 2)  return new MachineValueType(SimpleValueType.v2i32);
		if (NumElements == 4)  return new MachineValueType(SimpleValueType.v4i32);
		if (NumElements == 8)  return new MachineValueType(SimpleValueType.v8i32);
		break;
		
		case i64:
		if (NumElements == 1)  new MachineValueType(SimpleValueType.v1i64);
		if (NumElements == 2)  return new MachineValueType(SimpleValueType.v2i64);
		if (NumElements == 4)  return new MachineValueType(SimpleValueType.v4i64);
		if (NumElements == 8)  return new MachineValueType(SimpleValueType.v8i64);
		break;
		
		case f16:
			if (NumElements == 2)  return new MachineValueType(SimpleValueType.v2f16);
		break;
		
		case f32:
		if (NumElements == 2)  return new MachineValueType(SimpleValueType.v2f32);
		if (NumElements == 4)  return new MachineValueType(SimpleValueType.v4f32);
		if (NumElements == 8)  return new MachineValueType(SimpleValueType.v8f32);
		break;
		
		case f64:
		if (NumElements == 2)  return new MachineValueType(SimpleValueType.v2f64);
		if (NumElements == 4)  return new MachineValueType(SimpleValueType.v4f64);
		break;
		}
		
		return new MachineValueType(SimpleValueType.INVALID_SIMPLE_VALUE_TYPE);
	}
}
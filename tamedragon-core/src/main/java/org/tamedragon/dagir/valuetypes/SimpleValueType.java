package org.tamedragon.dagir.valuetypes;

public enum SimpleValueType {
	Other(0),   // This is a non-standard value
	i1(1),   // This is a 1 bit integer value
	i8(2),   // This is an 8 bit integer value
	i16(3),   // This is a 16 bit integer value
	i32(4),   // This is a 32 bit integer value
	i64(5),   // This is a 64 bit integer value
	i128(6),   // This is a 128 bit integer value
	FIRST_INTEGER_VALUETYPE(i1),
	LAST_INTEGER_VALUETYPE(i128),

	f16(7),   // This is a 16 bit floating point value
	f32(8),   // This is a 32 bit floating point value
	f64(9),   // This is a 64 bit floating point value
	f80(10),   // This is a 80 bit floating point value
	f128(11),   // This is a 128 bit floating point value
	ppcf128(12),   // This is a PPC 128-bit floating point value

	FIRST_FP_VALUETYPE(f16),
	LAST_FP_VALUETYPE(ppcf128),

	v2i8(13),   //  2 x i8
	v4i8(14),   //  4 x i8
	v8i8(15),   //  8 x i8
	v16i8(16),   // 16 x i8
	v32i8(17),   // 32 x i8
	v2i16(18),   //  2 x i16
	v4i16(19),   //  4 x i16
	v8i16(20),   //  8 x i16
	v16i16(21),   // 16 x i16
	v2i32(22),   //  2 x i32
	v4i32(23),   //  4 x i32
	v8i32(24),   //  8 x i32
	v1i64(25),   //  1 x i64
	v2i64(26),   //  2 x i64
	v4i64(27),   //  4 x i64
	v8i64(28),   //  8 x i64

	v2f16(29),   //  2 x f16
	v2f32(30),   //  2 x f32
	v4f32(31),   //  4 x f32
	v8f32(32),   //  8 x f32
	v2f64(33),   //  2 x f64
	v4f64(34),   //  4 x f64

	FIRST_VECTOR_VALUETYPE(v2i8),
	LAST_VECTOR_VALUETYPE(v4f64),
	FIRST_FP_VECTOR_VALUETYPE(v2f16),
	LAST_FP_VECTOR_VALUETYPE(v4f64),

	x86mmx(35),   // This is an X86 MMX value
	Glue(36),   // This glues nodes together during pre-RA sched
	isVoid(37),   // This has no value
	Untyped(38),    // This value takes a register, but has
	// unspecified type.  The register class
	// will be determined by the opcode.

	LAST_VALUETYPE(39),   // This always remains at the end of the list.

	// This is the current maximum for LAST_VALUETYPE.
	// MVT::MAX_ALLOWED_VALUETYPE is used for asserts and to size bit vectors
	// This value must be a multiple of 32.
	MAX_ALLOWED_VALUETYPE(64),

	// Metadata - This is MDNode or MDString.
	Metadata(250),

	// iPTRAny - An int value the size of the pointer of the current
	// target to any address space. This must only be used internal to
	// tblgen. Other than for overloading, we treat iPTRAny the same as iPTR.
	iPTRAny(251),

	// vAny - A vector with any length and element size. This is used
	// for intrinsics that have overloadings based on vector types.
	// This is only for tblgen's consumption!
	vAny(252),

	// fAny - Any floating-point or vector floating-point value. This is used
	// for intrinsics that have overloadings based on floating-point types.
	// This is only for tblgen's consumption!
	fAny(253),

	// iAny - An integer or vector integer value of any bit width. This is
	// used for intrinsics that have overloadings based on integer bit widths.
	// This is only for tblgen's consumption!
	iAny(254),

	// iPTR - An int value the size of the pointer of the current
	// target.  This should only be used internal to tblgen!
	iPTR(255),

	// LastSimpleValueType - The greatest valid SimpleValueType value.
	LastSimpleValueType(255),

	// INVALID_SIMPLE_VALUE_TYPE - Simple value types greater than or equal
	// to this are considered extended value types.
	INVALID_SIMPLE_VALUE_TYPE(LastSimpleValueType.valueIndex + 1);
	
	private int valueIndex;
	
	private SimpleValueType(int valIndex){
		this.valueIndex = valIndex;
	}
	
	private SimpleValueType(SimpleValueType otherSvt){
		this.valueIndex = otherSvt.valueIndex;
	}

	public boolean isGreaterThan(SimpleValueType simpleTy) {
		if(valueIndex > simpleTy.valueIndex)
			return true;
		return false;
	}

	public boolean isLesserThan(SimpleValueType simpleTy) {
		if(valueIndex < simpleTy.valueIndex)
			return true;
		return false;
	}

	public boolean isGreaterThanOrEqualTo(SimpleValueType simpleTy) {
		if(valueIndex >= simpleTy.valueIndex)
			return true;
		return false;
	}

	public boolean isLesserThanOrEqualTo(SimpleValueType simpleTy) {
		if(valueIndex <= simpleTy.valueIndex)
			return true;
		return false;
	}
	
	public int getIndex(){
		return valueIndex;
	}
}
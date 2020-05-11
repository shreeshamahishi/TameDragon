package org.tamedragon.common.llvmir.types;

import org.apache.tools.ant.taskdefs.condition.IsSigned;
import org.tamedragon.common.llvmir.math.APInt;
import org.tamedragon.common.llvmir.math.APSInt;
import org.tamedragon.common.llvmir.math.ULong;
import org.tamedragon.common.llvmir.types.Type.TypeID;
import org.tamedragon.common.llvmir.types.exceptions.TypeCreationException;

public class ConstantInt extends Constant {

	private APInt apInt;

	private ConstantInt theTrueVal;
	private ConstantInt theFalseVal;

	public ConstantInt(IntegerType intType, APInt val) throws InstantiationException{
		super(intType, null);
		if(val.getNumBits() != intType.getNumBits())
			throw new InstantiationException("Number of bits in APInt object is not equal to the number of bits of Integer Type");
		this.apInt = val;
		setValueTypeID(ValueTypeID.CONST_INT);
	}

	public static ConstantInt create(IntegerType intType, long V, 
			boolean isSigned) throws InstantiationException {
		return new ConstantInt(intType, new APInt(intType.getNumBits(), V + "", isSigned));
	}

	public static Constant create(Type type, long V, boolean isSigned) throws InstantiationException {
		ConstantInt constInt = create((IntegerType)type.getScalarType(), V, isSigned);

		// For vectors, broadcast the value.
		if(type.getTypeId() == TypeID.VECTOR_ID){
			VectorType vectorType = (VectorType) type;
			return ConstantVector.getSplat(vectorType.getNumElements(), constInt);
		}

		return constInt;
	}
	
	public static ConstantInt create(CompilationContext context, APInt apInt) {
		//TODO If it already exists, return that
		/*// get an existing value or the insertion position
		  LLVMContextImpl *pImpl = Context.pImpl;
		  std::unique_ptr<ConstantInt> &Slot = pImpl->IntConstants[V];
		  if (!Slot) {
		    // Get the corresponding integer type for the bit width of the value.
		    IntegerType *ITy = IntegerType::get(Context, V.getBitWidth());
		    Slot.reset(new ConstantInt(ITy, V));
		  }
		  assert(Slot->getType() == IntegerType::get(Context, V.getBitWidth()));
		  return Slot.get();
		  */
		IntegerType intType;
		try {
			intType = IntegerType.getIntegerType(context, apInt.getNumBits(), ((APSInt)apInt).isSigned());
		} catch (TypeCreationException e) {
			e.printStackTrace();
			return null;
		}
		try {
			return new ConstantInt(intType, apInt);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	public APInt getApInt() {
		return apInt;
	}

	public void setApInt(APInt apInt) {
		this.apInt = apInt;
	}

	public String toString() {
		return type.toString() + " " + apInt.toString();
	}

	@Override
	public String getName() {
		return apInt.toString();
	}

	public boolean isZero() {
		return apInt.isNullValue();
	}

	public boolean isOne() {
		return apInt.isOneValue();
	}
	
	public boolean isPositiveUnity(){
		return apInt.isOneValue();
	}

	public boolean isNegativeUnity(){
		return apInt.getUnsignedVal().equals(ULong.valueOf("-1"));
	}

	public boolean isAllOnesValue() {
		return apInt.isAllOnesValue();

	}

	public boolean equalsInt(long i) {
		return apInt.getUnsignedVal().equals(ULong.valueOf(i));
	}

	/*
	public Constant add(ConstantInt other) throws Exception {
		apInt.add(other.getApInt());
		return new ConstantInt((IntegerType)getType(), apInt);
	}

	public Constant subtract(ConstantInt other)  throws Exception {
		apInt.subtract(other.getApInt());
		return new ConstantInt((IntegerType)getType(), apInt);

	}

	public  Constant multiply(ConstantInt other) throws Exception{
		apInt.mul(other.getApInt());
		return new ConstantInt((IntegerType)getType(), apInt);
	}

	
	public Constant udiv(ConstantInt other) throws Exception {
		
	        return ConstantInt::get(CI1->getContext(), C1V.udiv(C2V));
		
		int result = apInt.udiv(other.getApInt());
		return new ConstantInt((IntegerType)getType(),
				new APInt(apInt.getNumBits(), "" + result, false));

	}

	public Constant sdiv(ConstantInt other) throws Exception {
		int result = apInt.sdiv(other.getApInt());
		return new ConstantInt((IntegerType)getType(),
				new APInt(apInt.getNumBits(), "" + result, false));
	}

	public Constant urem(ConstantInt other) throws Exception {
		int result = apInt.urem(other.getApInt());
		return new ConstantInt((IntegerType)getType(),
				new APInt(apInt.getNumBits(), "" + result, false));
	}

	public Constant srem(ConstantInt other) throws Exception {
		int result = apInt.srem(other.getApInt());
		return new ConstantInt((IntegerType)getType(),
				new APInt(apInt.getNumBits(), "" + result, false));
	}

	public Constant and(ConstantInt other) throws Exception {
		int result = apInt.and(other.getApInt());
		return new ConstantInt((IntegerType)getType(),
				new APInt(apInt.getNumBits(), "" + result, false));
	}

	public Constant or(ConstantInt other) throws Exception {
		int result = apInt.or(other.getApInt());
		return new ConstantInt((IntegerType)getType(),
				new APInt(apInt.getNumBits(), "" + result, false));
	}

	public Constant xor(ConstantInt other) throws Exception {
		int result = apInt.xor(other.getApInt());
		return new ConstantInt((IntegerType)getType(),
				new APInt(apInt.getNumBits(), "" + result, false));
	}

	public Constant shl(ConstantInt other) throws Exception {
		int result = apInt.shl(other.getApInt());
		return new ConstantInt((IntegerType)getType(),
				new APInt(apInt.getNumBits(), "" + result, false));
	}

	public Constant lshr(ConstantInt other) throws Exception {
		int result = apInt.lshr(other.getApInt());
		return new ConstantInt((IntegerType)getType(),
				new APInt(apInt.getNumBits(), "" + result, false));
	}

	public Constant ashr(ConstantInt other) throws Exception {
		int result = apInt.ashr(other.getApInt());
		return new ConstantInt((IntegerType)getType(),
				new APInt(apInt.getNumBits(), "" + result, false));
	}
	*/
	
	public boolean equals(ConstantInt otherConst){
		APInt otherApInt = otherConst.getApInt();
		if(apInt.getUnsignedVal().equals(otherApInt.getUnsignedVal()))
			return true;

		return false;
	}

	@Override
	public boolean isNegative() {
		APInt apInt = this.getApInt();
		String strRep = apInt.toString();
		char ch = strRep.charAt(0);
		if(ch == '-')
			return true;
		return false;
	}

	public static ConstantInt getTrue(CompilationContext context) throws InstantiationException {
		ConstantInt trueVal = context.getTheFalseVal();
		if (trueVal == null) {
			trueVal = ConstantInt.create(Type.getInt1Type(context, false), 1, false);
			context.setTheFalseVal(trueVal);
		}
		return trueVal;
	}

	public static ConstantInt getFalse(CompilationContext context) throws InstantiationException {
		ConstantInt falseVal = context.getTheFalseVal();
		if (falseVal == null) {
			falseVal = ConstantInt.create(Type.getInt1Type(context, false), 0, false);
			context.setTheFalseVal(falseVal);
		}
		return falseVal;
	}

	public static ConstantInt getTrue(Type type) throws InstantiationException {
		// TODO Include the ability to pass in bit width
		//if(!(type.isIntOrIntVectorType(1))) {
		if(!(type.isIntOrIntVectorType())) {
			throw new IllegalArgumentException("Type not i1 or vector of i1.");
		}

		ConstantInt TrueC = ConstantInt.getTrue(type.getCompilationContext());
		if(type.getTypeId() == TypeID.VECTOR_ID) {
			VectorType vectorType = (VectorType) type;
			return (ConstantInt) ConstantVector.getSplat(vectorType.getNumElements(), TrueC);
		}

		return TrueC;
	}

	public static ConstantInt getFalse(Type type) throws InstantiationException {
		// TODO Include the ability to pass in bit width
		//if(!(type.isIntOrIntVectorType(1))) {
		if(!(type.isIntOrIntVectorType())) {
			throw new IllegalArgumentException("Type not i1 or vector of i1.");
		}
		ConstantInt falseC = ConstantInt.getFalse(type.getCompilationContext());
		if(type.getTypeId() == TypeID.VECTOR_ID) {
			VectorType vectorType = (VectorType) type;
			return (ConstantInt) ConstantVector.getSplat(vectorType.getNumElements(), falseC);
		}

		return falseC;
	}
}

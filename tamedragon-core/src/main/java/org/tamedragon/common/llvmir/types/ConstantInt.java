package org.tamedragon.common.llvmir.types;

import org.tamedragon.common.llvmir.math.APInt;

public class ConstantInt extends Constant {

	private APInt apInt;

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
		return apInt.getVal().equals("0");
	}

	public boolean isPositiveUnity(){
		return apInt.getVal().equals("1")
				|| apInt.getVal().equals("+1");
	}
	
	public boolean isNegativeUnity(){
		return apInt.getVal().equals("-1");
	}
	
	public boolean isAllOnesValue() {
		// TODO Auto-generated method stub
		return false;

	}

	public boolean equalsInt(long i) {
		return apInt.getVal().equals("" + i);

	}

	public Constant add(ConstantInt other) throws Exception {
		int result = apInt.addOrSubtract(other.getApInt(), true);
		return new ConstantInt((IntegerType)getType(),
				new APInt(apInt.getNumBits(),
						"" + result, apInt.isSigned()));
	}

	public Constant subtract(ConstantInt other)  throws Exception {
		int result = apInt.addOrSubtract(other.getApInt(), false);
		return new ConstantInt((IntegerType)getType(),
				new APInt(apInt.getNumBits(),
						"" + result, apInt.isSigned()));

	}

	public  Constant multiply(ConstantInt other) throws Exception{
		int result = apInt.multiply(other.getApInt());
		return new ConstantInt((IntegerType)getType(),
				new APInt(apInt.getNumBits(),
						"" + result, apInt.isSigned()));

	}

	public Constant udiv(ConstantInt other) throws Exception {
		int result = apInt.udiv(other.getApInt());
		return new ConstantInt((IntegerType)getType(),
				new APInt(apInt.getNumBits(),
						"" + result, apInt.isSigned()));

	}

	public Constant sdiv(ConstantInt other) throws Exception {
		int result = apInt.sdiv(other.getApInt());
		return new ConstantInt((IntegerType)getType(),
				new APInt(apInt.getNumBits(),
						"" + result, apInt.isSigned()));
	}

	public Constant urem(ConstantInt other) throws Exception {
		int result = apInt.urem(other.getApInt());
		return new ConstantInt((IntegerType)getType(),
				new APInt(apInt.getNumBits(),
						"" + result, apInt.isSigned()));
	}

	public Constant srem(ConstantInt other) throws Exception {
		int result = apInt.srem(other.getApInt());
		return new ConstantInt((IntegerType)getType(),
				new APInt(apInt.getNumBits(),
						"" + result, apInt.isSigned()));
	}

	public Constant and(ConstantInt other) throws Exception {
		int result = apInt.and(other.getApInt());
		return new ConstantInt((IntegerType)getType(),
				new APInt(apInt.getNumBits(),
						"" + result, apInt.isSigned()));
	}

	public Constant or(ConstantInt other) throws Exception {
		int result = apInt.or(other.getApInt());
		return new ConstantInt((IntegerType)getType(),
				new APInt(apInt.getNumBits(),
						"" + result, apInt.isSigned()));
	}

	public Constant xor(ConstantInt other) throws Exception {
		int result = apInt.xor(other.getApInt());
		return new ConstantInt((IntegerType)getType(),
				new APInt(apInt.getNumBits(),
						"" + result, apInt.isSigned()));
	}

	public Constant shl(ConstantInt other) throws Exception {
		int result = apInt.shl(other.getApInt());
		return new ConstantInt((IntegerType)getType(),
				new APInt(apInt.getNumBits(),
						"" + result, apInt.isSigned()));
	}

	public Constant lshr(ConstantInt other) throws Exception {
		int result = apInt.lshr(other.getApInt());
		return new ConstantInt((IntegerType)getType(),
				new APInt(apInt.getNumBits(),
						"" + result, apInt.isSigned()));
	}

	public Constant ashr(ConstantInt other) throws Exception {
		int result = apInt.ashr(other.getApInt());
		return new ConstantInt((IntegerType)getType(),
				new APInt(apInt.getNumBits(),
						"" + result, apInt.isSigned()));
	}

	public boolean equals(ConstantInt otherConst){
		APInt otherApInt = otherConst.getApInt();
		if(apInt.compare(otherApInt) == 0)
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
}

package org.tamedragon.common.llvmir.types;

public class ConstantFP extends Constant{

	private APFloat apFloat;

	public ConstantFP(Type type, APFloat apFloat) throws InstantiationException{
		super(type, null);
		this.apFloat = apFloat;
		this.valueTypeID = ValueTypeID.CONST_FP;
	}

	@Override
	public String getName() {
		return apFloat.toString();
	}

	public String toString(){
		return type.toString() + " " + apFloat.toString();
	}

	public APFloat getAPFloat(){
		return apFloat;
	}

	public boolean isZero() {
		APFloat apFloat = this.getAPFloat();
		String strRep = apFloat.toString();
		if(strRep.equals("0.0") || strRep.equals("0") || strRep.equals("0.0f") || strRep.equals("0.0F"))
			return true;

		return false;
	}

	public boolean isPositiveUnity(){
		APFloat apFloat = this.getAPFloat();
		String strRep = apFloat.toString();
		if(strRep.equals("1.0")  || strRep.equals("1.0f") || strRep.equals("1.0F")
				|| strRep.equals("+1.0")  || strRep.equals("+1.0f") || strRep.equals("+1.0F"))
			return true;

		return false;
	}

	public boolean isNegativeUnity(){
		APFloat apFloat = this.getAPFloat();
		String strRep = apFloat.toString();
		if(strRep.equals("-1.0")  || strRep.equals("-1.0f") || strRep.equals("-1.0F"))
			return true;

		return false;
	}

	@Override
	public boolean isNegative() {
		APFloat apFloat = this.getAPFloat();
		String strRep = apFloat.toString();
		char ch = strRep.charAt(0);
		if(ch == '-')
			return true;
		return false;
	}

	public Constant add(ConstantFP other) throws Exception {
		double result = apFloat.addOrSubtract(other.getAPFloat(), true);
		return new ConstantFP(getType(),
				new APFloat(APFloat.IEEEdouble, "" + result));
	}

	public Constant subtract(ConstantFP other) throws Exception {
		double result = apFloat.addOrSubtract(other.getAPFloat(), false);
		return new ConstantFP(getType(),
				new APFloat(APFloat.IEEEdouble, "" + result));
	}

	public Constant mul(ConstantFP other) throws Exception {
		double result = apFloat.mul(other.getAPFloat());
		return new ConstantFP(getType(),
				new APFloat(APFloat.IEEEdouble, "" + result));
	}

	public Constant divide(ConstantFP other) throws Exception {
		double result = apFloat.divide(other.getAPFloat());
		return new ConstantFP(getType(),
				new APFloat(APFloat.IEEEdouble, "" + result));
	}

	public Constant frem(ConstantFP other) throws Exception {
		double result = apFloat.frem(other.getAPFloat());
		return new ConstantFP(getType(),
				new APFloat(APFloat.IEEEdouble, "" + result));
	}

	public boolean equals(ConstantFP otherConst){
		APFloat otherApFloat = otherConst.getAPFloat();

		if(apFloat.compare(otherApFloat) == 0)
			return true;

		return false;
	}

	/*
	 * ConstantFP accessors.
	 */
	public static ConstantFP create(CompilationContext context, APFloat apFloatVal) throws InstantiationException {
		// TODO Get from existing pool if present
		//	LLVMContextImpl* pImpl = Context.pImpl;
		//std.unique_ptr<ConstantFP> &Slot = pImpl->FPConstants[V];
		ConstantFP slot = null;
		if(slot != null) {
			return slot;
		}

		Type type = null;
		if (apFloatVal.getSemantics() == APFloat.IEEEhalf) {
			type = Type.getHalfType(context);
		}
		else if (apFloatVal.getSemantics() == APFloat.IEEEsingle)
			type = Type.getFloatType(context);
		else if (apFloatVal.getSemantics() == APFloat.IEEEdouble)
			type = Type.getDoubleType(context);
		else if (apFloatVal.getSemantics() == APFloat.x87DoubleExtended)
			type = Type.getX86_FP80Type(context);
		else if (apFloatVal.getSemantics() == APFloat.IEEEquad)
			type = Type.getFP128Type(context);
		else {
			if(apFloatVal.getSemantics() != APFloat.PPCDoubleDouble) {
				throw new IllegalArgumentException("Unknown FP format");
			}
			type = Type.getPPCFP128Type(context);
		}
		
		slot = new ConstantFP(type, apFloatVal);

		return slot;
	}
}

package org.tamedragon.common.llvmir.types;

import java.util.List;

public class UndefValue extends Constant{

	protected UndefValue(Type type, List<Value> operandList)  {
		super(type, operandList);
		setValueTypeID(ValueTypeID.UNDEF_VALUE);
	}

	public static UndefValue createOrGet(Type type) {
		CompilationContext context = type.getCompilationContext();
		return context.getUndefinedValueConstants(type);
	}

	public String toString(){
		return "undef";
	}

	public String getValueName(){
		return "undef";
	}

}

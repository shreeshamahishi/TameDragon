package org.tamedragon.common.llvmir.types;

import java.util.List;

import org.tamedragon.common.llvmir.types.Value.ValueTypeID;

public class ConstantCreator {
	public static Constant create(Type ty, List<Constant> v) {
		if(ty.isArrayType()){
			ConstantArray constantArray = new ConstantArray((ArrayType)ty, v);
			constantArray.setValueTypeID(ValueTypeID.CONST_ARRAY);
			return constantArray;
		}
		else if(ty.isStructType()){
			ConstantStruct constantStruct = new ConstantStruct((StructType)ty, v); 
			constantStruct.setValueTypeID(ValueTypeID.CONST_STRUCT);
			return constantStruct;
		}
		else
			return new Constant(ty,v);
	}
}

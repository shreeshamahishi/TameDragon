package org.tamedragon.common.utils;

import org.tamedragon.common.llvmir.types.AbstractType;
import org.tamedragon.common.llvmir.types.Numeric;
import org.tamedragon.common.llvmir.types.Temp;

/**
 * Provides a list of conversion facilities like creating an abstractType out
 * of a temp or a numeric and vice versa,etc. 
 *
 */
public class ConversionUtility {

	/**
	 * Infers and creates an object of type AbstractType from a temporary
	 * 
	 * @param temp
	 * @return
	 */
	public static AbstractType getAbstractType(Temp temp){
		AbstractType abstractType = new AbstractType();
		
		if(temp.isInteger()){
			abstractType.setInteger(true);
			abstractType.setIntegerSize(temp.getIntegerSize());
			abstractType.setSigned(temp.isSigned());
		}
		else{
			abstractType.setInteger(false);
			abstractType.setFloatPrecision(temp.getFloatPrecision());
		}
		
		return abstractType;
	}
	
	/**
	 * Infers and creates an object of type AbstractType from a Numeric
	 * 
	 * @param temp
	 * @return
	 */
	public static AbstractType getAbstractType(Numeric numeric){
		AbstractType abstractType = new AbstractType();
		
		if(numeric.isInteger()){
			abstractType.setInteger(true);
			abstractType.setIntegerSize(numeric.getIntegerSize());
			abstractType.setSigned(true);   // Assume that a numeric is signed?
		}
		else{
			abstractType.setInteger(false);
			abstractType.setFloatPrecision(numeric.getFloatPrecision());
		}
		
		return abstractType;
	}
	
}

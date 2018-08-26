package org.tamedragon.compilers.clang.preprocessor;

import java.util.regex.Pattern;

public class Constant extends Absyn  {
	
	public static final int INT_CONST = 0;
	public static final int CHAR_CONST = 1;
	
	private String intConstant;
	private String charConstant;

	private int type;
	
	public static final int MAX_CHAR_CONST_LENGTH = 3;
	public static final int MAX_SHORT_CONST_LENGTH = 5;
	public static final int MAX_INT_CONST_LENGTH = 10;
	public static final int MAX_UNSIGNED_LONG_CONST_LENGTH = 20;	
	public static final int MAX_SIGNED_LONG_CONST_LENGTH = 19;
	
	public static final int MAX_UNSIGNED_CHAR_VALUE = 256;
	public static final int MIN_UNSIGNED_CHAR_VALUE = 0;
	public static final int MAX_UNSIGNED_SHORT_VALUE = 65535;
	public static final int MIN_UNSIGNED_SHORT_VALUE = 0;
	
	public static final String MAX_UNSIGNED_INT_VALUE = "4294967295";
	public static final String MAX_UNSIGNED_LONG_VALUE = "18446744073709551615";
	public static final String MAX_SIGNED_LONG_VALUE = "9223372036854775808";
	
	public static final int MAX_SIGNED_CHAR_VALUE = 127;
	public static final int MIN_SIGNED_CHAR_VALUE = -128;
	public static final int MAX_SIGNED_SHORT_VALUE = 32767;
	public static final int MIN_SIGNED_SHORT_VALUE = -32768;
	public static final int MAX_SIGNED_INT_VALUE = 2147483647;
	public static final int MIN_SIGNED_INT_VALUE = -2147483648;
	
	public static final int MAX_FLOAT_EXPONENT_LENGTH = 38;
	public static final int MAX_DOUBLE_EXPONENT_LENGTH = 308;
	
	public static final int MAX_FLOAT_SIGNIFICAND = 3;											  
	public static final long MAX_FLOAT_BASE = 402823466L;
	public static final long MAX_FLOAT_DIGITS = 1000000000L;
	public static final int MAX_FLOAT_NUM_DIGITS = 9;
		
	public static final int MAX_DOUBLE_SIGNIFICAND = 1;
	public static final long MAX_DOUBLE_BASE = 7976931348623158L;
	public static final long MAX_DOUBLE_DIGITS = 10000000000000000L;
	public static final int MAX_DOUBLE_NUM_DIGITS = 16;
	
	public String toString(){
		String str = "";
		if(type == INT_CONST)
			str = intConstant;
		else if(type == CHAR_CONST)
			str = charConstant;
		
		return str;
	}	
	
	public Constant(){}
	
	public Constant(int type, String str){
		this.type = type;
		if(type == INT_CONST)
			intConstant = str;
		else // character constant
			charConstant = str;
	}
	
	public String getCharConstant() {
		return charConstant;
	}
	public void setCharConstant(String charConstant) {
		type = CHAR_CONST;
		this.charConstant = charConstant;
	}
	public String getIntConstant() {
		return intConstant;
	}
	public void setIntConstant(String intConstant) {
		type = INT_CONST;
		this.intConstant = intConstant;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	// Static function to check limits of a integer constant

	public static boolean canBeSignedChar(String integerConst){
		return checkCanBeChar(integerConst, false);
	}
	
	public static boolean canBeUnSignedChar(String integerConst){
		return checkCanBeChar(integerConst, true);
	}
	
	public static boolean canBeUnSignedShort(String integerConst){
		return checkCanBeShort(integerConst, true);
	}
	
	public static boolean canBeSignedShort(String integerConst){
		return checkCanBeShort(integerConst, false);
	}
	
	public static boolean canBeSignedInt(String integerConst){
		return checkCanBeInt(integerConst, false);
	}
	
	public static boolean canBeUnSignedInt(String integerConst){
		return checkCanBeInt(integerConst, true);
	}
	
	public static boolean canBeUnSignedLong(String integerConst){
		return checkCanBeLongInt(integerConst, true);
	}
	
	public static boolean canBeSignedLong(String integerConst){
		return checkCanBeLongInt(integerConst, false);
	}

	private static boolean checkCanBeChar(String integerConst, boolean isUnSigned){
		int constLength = integerConst.length();
		if(constLength > Constant.MAX_CHAR_CONST_LENGTH)
			return false;
		
		try{
			int value = Integer.parseInt(integerConst);
			if(isUnSigned){
				if(value > Constant.MAX_UNSIGNED_CHAR_VALUE || value < Constant.MIN_UNSIGNED_CHAR_VALUE)
					return false;
			}
			else{
				if(value > Constant.MAX_SIGNED_CHAR_VALUE || value < Constant.MIN_SIGNED_CHAR_VALUE)
					return false;
			}
			
		}
		catch(NumberFormatException nfe){
			// Not an integer constant; check if it is a character.
			//TODO revisit this
			int value = getAsciiValue(integerConst);
			if(value == -1){
				return false;
			}
		}
		return true;
	}
	
	private static boolean checkCanBeShort(String integerConst, boolean isUnSigned){
		int constLength = integerConst.length();
		if(constLength > Constant.MAX_SHORT_CONST_LENGTH)
			return false;
		
		try{
			int value = Integer.parseInt(integerConst);
			if(isUnSigned){
				if(value > Constant.MAX_UNSIGNED_SHORT_VALUE || value < Constant.MIN_UNSIGNED_SHORT_VALUE)
					return false;
			}
			else{
				if(value > Constant.MAX_SIGNED_SHORT_VALUE || value < Constant.MIN_SIGNED_SHORT_VALUE)
					return false;
			}
			
		}
		catch(NumberFormatException nfe){
			return false;
		}
		return true;
	}
	
	public static boolean canBeFloat(String floatingConstant){
		int maxExponentLength = MAX_FLOAT_EXPONENT_LENGTH;
		int maxSignificand = MAX_FLOAT_SIGNIFICAND;
		long maxBase = MAX_FLOAT_BASE;
		long digits = MAX_FLOAT_DIGITS;
		int maxNumDigits = MAX_FLOAT_NUM_DIGITS;
		
		return canBeFloatOrDouble(floatingConstant, maxExponentLength, 
					maxSignificand, maxBase, digits, maxNumDigits);
	}
	
	public static boolean canBeDouble(String floatingConstant){
		int maxExponentLength = MAX_DOUBLE_EXPONENT_LENGTH;
		int maxSignificand = MAX_DOUBLE_SIGNIFICAND;
		long maxBase = MAX_DOUBLE_BASE;
		long digits = MAX_DOUBLE_DIGITS;
		int maxNumDigits = MAX_DOUBLE_NUM_DIGITS;
		
		return canBeFloatOrDouble(floatingConstant, maxExponentLength, 
					maxSignificand, maxBase, digits, maxNumDigits);
	}
	
	
	public static boolean canBeFloatOrDouble(String floatingConstant, int maxExponentLength,
			int maxSignificand, long maxBase, long digits, int maxNumDigits ){
		
		String rootFloat = floatingConstant.split("f|F")[0];
		String[] baseAndExponent = rootFloat.split("e|E");
		String base = null;
		String exponent = null;
		if(baseAndExponent.length == 2){
			base = baseAndExponent[0];
			exponent = baseAndExponent[1];
		}
		else
			base = baseAndExponent[0];   // No exponent specified
		
		// Extract the base and get only the significant digits
		if(base.startsWith("-") || base.startsWith("+")){
			base = base.substring(1, base.length());
		}
		
		String significantDigits = null;
		Pattern pattern = Pattern.compile(".", Pattern.LITERAL);
		
		String [] splitDecimalPoint = pattern.split(base);
		if(splitDecimalPoint.length == 0)
			significantDigits = base;
		else
			significantDigits = splitDecimalPoint[0];  // Only the value before the decimal point
		
		String decimalString = splitDecimalPoint[1];   // The number after the decimal point
		
		int baseSignificantLength = significantDigits.length();
		if(baseSignificantLength == 1) {
			int significand = Integer.parseInt(significantDigits);
			if(significand == 0)   // Of the form 0.xxx....
				baseSignificantLength = 0;
		}
			
		if(exponent == null){    // No exponent; check only base						
			if(baseSignificantLength < maxExponentLength + 1)
				return true;
			else if(baseSignificantLength == maxExponentLength + 1){ // Check the first 10 digits
				String firstConsideredDigits = significantDigits.substring(0, maxNumDigits + 1);
				long firstConsidered = new Long(firstConsideredDigits).longValue();
				
				long maxVal = (maxSignificand * digits) + maxBase;
				
				if(firstConsidered > maxVal){
					return false;
				}
				else
					return true;
			}
			else
				return false;
		}
		else{  // Exponent exists
			boolean negativeExponent = false;
			if(exponent.startsWith("-") || exponent.startsWith("+")){
				if(exponent.startsWith("-"))
					negativeExponent = true;
				exponent = exponent.substring(1, exponent.length());				
			}
			
			int exponentLength = Integer.parseInt(exponent);
			int totalLength = 0;
			if(negativeExponent)
				totalLength = baseSignificantLength;
			else
				totalLength = baseSignificantLength + exponentLength;
			
			if(totalLength > maxExponentLength + 1)
				return false;
			else if(totalLength == maxExponentLength + 1){
				if(baseSignificantLength == 0){
					if(decimalString.length() > 0){
						long significand = new Long(decimalString).longValue();
						if(significand > maxBase){
							return false;
						}
						else
							return true;
					}
					else 
						return true;
				}
				else{
					long significand = new Long(significantDigits).longValue();
					if(significand > maxSignificand)
						return false;
					else if(significand < maxSignificand)
						return true;
					else{
						if(significand > maxBase){
							return false;
						}
						else
							return true;
					}
				}
			}
			else
				return true;
		}
	}
	
	private static int getAsciiValue(String charConst){
		if(charConst.length() != 3){
			return -1;
		}

		char arr [] = charConst.toCharArray();
		if(!(arr[0] == '\'' && arr[2] == '\'')){
			return -1;				
		}
		
		int val = arr[1];
		
		//return Character.getNumericValue(arr[1]);
		return val;
		
	}

	/*
	 * Checks and returns true if the passed string can be an int in C, either signed or unsigned depending on
	 * on the flag passed in the second parameter
	 */
	
	private static boolean checkCanBeInt(String integerConst, boolean isUnSigned){
		int constLength = integerConst.length();
		if(constLength > Constant.MAX_INT_CONST_LENGTH)
			return false;   // Cannot be an int, too long
		
		if(constLength < Constant.MAX_INT_CONST_LENGTH){
			return true;     // Can be an int, its lesser than the max length of an int
		}
		
		// Same length as that of max length of an int, check 
		try{
			if(isUnSigned){    // Checking for signed int
				if(integerConst.equalsIgnoreCase(Constant.MAX_UNSIGNED_INT_VALUE))
					return true;   // Exactly equal to the max unsigned int value
				else               
					// Check each digit
					return isNotOutOfRange(integerConst, Constant.MAX_UNSIGNED_INT_VALUE);
			}
			else{
				int value = Integer.parseInt(integerConst);
				if(value == Constant.MAX_SIGNED_INT_VALUE) // Exactly equal to the max signed int value
					return true;
				else
					// Check each digit
					return isNotOutOfRange(integerConst, "" + Constant.MAX_SIGNED_INT_VALUE);
			
			}
		}
		catch(NumberFormatException nfe){
			return false;
		}		
	}
	
	/*
	 * Checks and returns true if the passed string can be an long int in C, either signed or unsigned depending on
	 * on the flag passed in the second parameter
	 */
	
	private static boolean checkCanBeLongInt(String integerConst, boolean isUnSigned){
		int constLength = integerConst.length();
		if(isUnSigned){  
			if(constLength > Constant.MAX_UNSIGNED_LONG_CONST_LENGTH)
				return false;   // Length exceeds the limit of an unsigned long int.
			else if(constLength < Constant.MAX_UNSIGNED_LONG_CONST_LENGTH)
				return true;    // Length is lesser than the limit of an unsigned long int.
		}
		else{
			if(constLength > Constant.MAX_SIGNED_LONG_CONST_LENGTH)
				return false;   // Length exceeds the limit of an unsigned long int.
			else if(constLength < Constant.MAX_SIGNED_LONG_CONST_LENGTH)
				return true;    // Length is lesser than the limit of an unsigned long int.
		}
			
		// Length is same as that of maximum allowed, check the digits
		try{
			if(isUnSigned){    // Checking for signed int
				return isNotOutOfRange(integerConst, Constant.MAX_UNSIGNED_LONG_VALUE);
			}
			else{
				return isNotOutOfRange(integerConst, Constant.MAX_SIGNED_LONG_VALUE);
			}
		}
		catch(NumberFormatException nfe){
			return false;
		}		
	}
	
	private static boolean isNotOutOfRange(String intConst, String maxValue){
		char[] intConstArr = intConst.toCharArray();
		char[] maxValArr = intConst.toCharArray();
		
		for(int i = 0; i < maxValArr.length; i++){
			if(maxValArr[i] < intConstArr[i])
				return false;
		}
		
		return true;
	}
	
}

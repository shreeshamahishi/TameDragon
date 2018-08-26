package org.tamedragon.common.llvmir.types;

public class Numeric implements Operand{
	public static int INTEGER_TYPE = 0;
	public static int LONG_TYPE = 1;
	public static int CHAR_TYPE = 2;
	public static int FLOAT_TYPE = 3;
	public static int DOUBLE_TYPE =4;

	private int type;
	private String valueStr;

	public final static int EQ = 0;
	public final static int NE = 1;
	public final static int LT = 2;
	public final static int GT = 3;
	public final static int LE = 4;
	public final static int GE = 5;

	public Numeric(int type, String valueStr){
		this.type = type;
		this.valueStr = valueStr; 	
	}

	public int getType(){
		return type;
	}

	public String toString(){

		if(type == CHAR_TYPE){
			char [] charArray = valueStr.toCharArray();

			char charVal = 0;
			for(char c : charArray){
				charVal += c;
			}

			return ""  + (int) charVal;


		}
		return valueStr;
	}

	public int getOperandType(){
		return Operand.NUMERIC_TYPE;
	}

	public boolean equals(Numeric num){
		return toString().equals(num.toString());
	}

	public boolean performCheck(Numeric num, int relOp){
		int typeOfOther = num.getType();
		String otherValueStr = num.toString();

		if(type == CHAR_TYPE || type == INTEGER_TYPE){
			int thisValue = -1;
			if(type == CHAR_TYPE)
				thisValue = (int)valueStr.charAt(0);			
			else
				thisValue = Integer.parseInt(valueStr);

			// Perform checks against the other ones now
			if(typeOfOther == CHAR_TYPE || typeOfOther == INTEGER_TYPE){    // Other value is char or int type
				int otherValue = -1;
				if(type == CHAR_TYPE)                                       // Other value is char type
					otherValue = (int)otherValueStr.charAt(0);			
				else														// Other value is int type
					otherValue = Integer.parseInt(otherValueStr);

				return performCheck(thisValue, otherValue, relOp);
			}
			else if(typeOfOther == LONG_TYPE){                              // Other value is long type
				long otherValue = Long.parseLong(otherValueStr);
				return performCheck(thisValue, otherValue, relOp);
			}
			else if(typeOfOther == FLOAT_TYPE){                             // Other value is float type
				float otherValue = Float.parseFloat(otherValueStr);
				return performCheck(thisValue, (double)otherValue, relOp);
			}
			else {															// Other value is double type
				double otherValue = Double.parseDouble(otherValueStr);
				return performCheck(thisValue, otherValue, relOp);
			}
		}
		else if(type == FLOAT_TYPE || type == DOUBLE_TYPE){
			double thisValue = 0.0;
			if(type == FLOAT_TYPE)
				thisValue = (double) Float.parseFloat(valueStr);
			else
				thisValue = Double.parseDouble(valueStr);

			// Perform checks against the other ones now
			if(typeOfOther == CHAR_TYPE || typeOfOther == INTEGER_TYPE){    // Other value is char or int type
				int otherValue = -1;
				if(type == CHAR_TYPE)                                       // Other value is char type
					otherValue = (int)otherValueStr.charAt(0);			
				else														// Other value is int type
					otherValue = Integer.parseInt(otherValueStr);

				return performCheck(thisValue, otherValue, relOp);
			}
			else if(typeOfOther == LONG_TYPE){                              // Other value is long type
				long otherValue = Long.parseLong(otherValueStr);
				return performCheck(thisValue, otherValue, relOp);
			}
			else if(typeOfOther == FLOAT_TYPE){                             // Other value is float type
				float otherValue = Float.parseFloat(otherValueStr);
				return performCheck(thisValue, (double)otherValue, relOp);
			}
			else {															// Other value is double type
				double otherValue = Double.parseDouble(otherValueStr);
				return performCheck(thisValue, otherValue, relOp);
			}			
		}	
		else{
			long thisValue = Long.parseLong(valueStr);

			// Perform checks against the other ones now
			if(typeOfOther == CHAR_TYPE || typeOfOther == INTEGER_TYPE){    // Other value is char or int type
				int otherValue = -1;
				if(type == CHAR_TYPE)                                       // Other value is char type
					otherValue = (int)otherValueStr.charAt(0);			
				else														// Other value is int type
					otherValue = Integer.parseInt(otherValueStr);

				return performCheck(thisValue, otherValue, relOp);
			}
			else if(typeOfOther == LONG_TYPE){                              // Other value is long type
				long otherValue = Long.parseLong(otherValueStr);
				return performCheck(thisValue, otherValue, relOp);
			}
			else if(typeOfOther == FLOAT_TYPE){                             // Other value is float type
				float otherValue = Float.parseFloat(otherValueStr);
				return performCheck(thisValue, (double)otherValue, relOp);
			}
			else {															// Other value is double type
				double otherValue = Double.parseDouble(otherValueStr);
				return performCheck(thisValue, otherValue, relOp);
			}			
		}
	}

	public boolean performCheck(int thisValue, int otherValue, int relOp){

		if(relOp == LT){
			if(thisValue < otherValue) return true;
			return false;
		}
		else if(relOp == GT){
			if(thisValue > otherValue) return true;
			return false;
		}
		else if(relOp == LE){
			if(thisValue <= otherValue) return true;
			return false;
		}
		else if(relOp == EQ){
			if(thisValue == otherValue) return true;
			return false;
		}
		else if(relOp == NE){
			if(thisValue != otherValue) return true;
			return false;
		}
		else{
			if(thisValue >= otherValue) return true;
			return false;
		}			
	}

	public boolean performCheck(int thisValue, long otherValue, int relOp){

		if(relOp == LT){
			if(thisValue < otherValue) return true;
			return false;
		}
		else if(relOp == GT){
			if(thisValue > otherValue) return true;
			return false;
		}
		else if(relOp == LE){
			if(thisValue <= otherValue) return true;
			return false;
		}
		else if(relOp == EQ){
			if(thisValue == otherValue) return true;
			return false;
		}
		else if(relOp == NE){
			if(thisValue != otherValue) return true;
			return false;
		}
		else{
			if(thisValue >= otherValue) return true;
			return false;
		}			
	}

	public boolean performCheck(int thisValue, double otherValue, int relOp){

		if(relOp == LT){
			if(thisValue < otherValue) return true;
			return false;
		}
		else if(relOp == GT){
			if(thisValue > otherValue) return true;
			return false;
		}
		else if(relOp == LE){
			if(thisValue <= otherValue) return true;
			return false;
		}
		else if(relOp == EQ){
			if(thisValue == otherValue) return true;
			return false;
		}
		else if(relOp == NE){
			if(thisValue != otherValue) return true;
			return false;
		}
		else{
			if(thisValue >= otherValue) return true;
			return false;
		}			
	}

	public boolean performCheck(double thisValue, int otherValue, int relOp){

		if(relOp == LT){
			if(thisValue < otherValue) return true;
			return false;
		}
		else if(relOp == GT){
			if(thisValue > otherValue) return true;
			return false;
		}
		else if(relOp == LE){
			if(thisValue <= otherValue) return true;
			return false;
		}
		else if(relOp == EQ){
			if(thisValue == otherValue) return true;
			return false;
		}
		else if(relOp == NE){
			if(thisValue != otherValue) return true;
			return false;
		}
		else{
			if(thisValue >= otherValue) return true;
			return false;
		}			
	}

	public boolean performCheck(double thisValue, long otherValue, int relOp){

		if(relOp == LT){
			if(thisValue < otherValue) return true;
			return false;
		}
		else if(relOp == GT){
			if(thisValue > otherValue) return true;
			return false;
		}
		else if(relOp == LE){
			if(thisValue <= otherValue) return true;
			return false;
		}
		else if(relOp == EQ){
			if(thisValue == otherValue) return true;
			return false;
		}
		else if(relOp == NE){
			if(thisValue != otherValue) return true;
			return false;
		}
		else{
			if(thisValue >= otherValue) return true;
			return false;
		}			
	}

	public boolean performCheck(double thisValue, double otherValue, int relOp){

		if(relOp == LT){
			if(thisValue < otherValue) return true;
			return false;
		}
		else if(relOp == GT){
			if(thisValue > otherValue) return true;
			return false;
		}
		else if(relOp == LE){
			if(thisValue <= otherValue) return true;
			return false;
		}
		else if(relOp == EQ){
			if(thisValue == otherValue) return true;
			return false;
		}
		else if(relOp == NE){
			if(thisValue != otherValue) return true;
			return false;
		}
		else{
			if(thisValue >= otherValue) return true;
			return false;
		}			
	}

	public boolean isInteger(){
		return (type == CHAR_TYPE || type == INTEGER_TYPE || type == LONG_TYPE);
	}

	public int getIntegerSize(){
		int size = -1; // Invalid

		if(type == CHAR_TYPE)
			size = AbstractType.BYTE_SIZE;
		else if(type == INTEGER_TYPE)
			size = AbstractType.WORD_SIZE;
		else if(type == LONG_TYPE)
			size = AbstractType.DOUBLE_WORD;

		return size;

	}

	public int getFloatPrecision(){
		int precision = -1; // Invalid

		if(type == DOUBLE_TYPE)
			precision = AbstractType.DOUBLE_PRECISION;
		else if(type == FLOAT_TYPE)
			precision = AbstractType.SINGLE_PRECISION;

		return precision;

	}

}

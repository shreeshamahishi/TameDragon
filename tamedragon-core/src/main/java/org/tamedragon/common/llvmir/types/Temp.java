package org.tamedragon.common.llvmir.types;

import java.util.Vector;

import org.tamedragon.assemblyabstractions.constructs.AssemValueProperties;


public class Temp implements Operand
{
	public static final int TEMP = 0;
	public static final int REGISTER = 1;
	public static final int USER_DEFINED = 2;

	public static final int MACHINE_REGISTER_RETURN_TYPE = 0;
	public static final int MACHINE_REGISTER_FRAME_POINTER = 1;
	public static final int MACHINE_REGISTER_STACK_POINTER = 2;
	public static final int MACHINE_REGISTER_ARGUMENT_TYPE = 3;

	public static Vector<Temp> physicalRegisters = new Vector<Temp>();

	private static int count;  
	private int num;
	private int tempType;
	private String registerType;

	private String userDefinedString;

	// Type properties (the attributes of the values in this temp
	private boolean signed;
	private boolean integer;
	private int integerSize;
	private int floatPrecision;

	public Temp() { 
		tempType = TEMP;
		num = count++;
	}

	public Temp(int num, String userDefinedString){
		tempType = USER_DEFINED;
		this.num = num;
		this.userDefinedString = userDefinedString;
	}

	public Temp(String type){
		tempType = REGISTER;
		registerType = type;
	}

	/**
	 * Resets the count of the temporary variables to zero; typically called when compilation is
	 * repeated afresh in the same running instance
	 *
	 */
	public static void reset(){
		count = 0;
	}

	public String toString() 
	{
		if(tempType == TEMP)			
			return "t" + num;
		else if(tempType == REGISTER)			
			return registerType;
		else
			return userDefinedString;
	}

	public String getDetailedDesc(){
		if(tempType == TEMP)
			return "t" + num + "(" + getTypeStr() + ")";

		else if(tempType == REGISTER)
			return registerType + "(" + getTypeStr() + ")";

		else
			return userDefinedString + "(" + getTypeStr() + ")";
	}

	public String getTypeStr(){
		String typeStr = "type = ";
		if(isInteger()){
			if(isSigned())
				typeStr += "i";
			else
				typeStr += "ui";

			typeStr += getIntegerSize();
		}
		else{
			typeStr += "f" + getFloatPrecision();
		}
		return typeStr;
	}

	public int getNum(){
		return num;
	}

	public int getTempType(){
		return tempType;
	}

	public boolean isPhysicalRegister(){
		return tempType == REGISTER;
	}

	public String getRegisterType(){
		return registerType;
	}

	public boolean equals(Temp temp){
		if(this.toString().equalsIgnoreCase(temp.toString())) return true;
		return false;
	}

	public int getOperandType(){
		return Operand.TEMP_TYPE;
	}

	public static Temp getTemp(String type){

		for(Temp pr : physicalRegisters){
			if(pr.toString().equalsIgnoreCase(type))
				return pr;				
		}

		Temp retTemp = new Temp(type);
		retTemp.setInteger(true);
		retTemp.setIntegerSize(4);
		retTemp.setSigned(false);

		physicalRegisters.add(retTemp);
		return retTemp;
	}

	public boolean isSigned() {
		return signed;
	}

	public void setSigned(boolean signed) {
		this.signed = signed;
	}

	public boolean isInteger() {
		return integer;
	}

	public void setInteger(boolean integer) {
		this.integer = integer;
	}

	public int getIntegerSize() {
		return integerSize;
	}

	public void setIntegerSize(int integerSize) {
		this.integerSize = integerSize;
	}

	public int getFloatPrecision() {
		return floatPrecision;
	}

	public void setFloatPrecision(int floatPrecision) {
		this.floatPrecision = floatPrecision;
	}	

	public boolean isWiderThan(Temp otherTemp){
		if(integer){
			if(otherTemp.isInteger()){
				if(integerSize > otherTemp.getIntegerSize())
					return true;
				else
					return false;
			}
			else{
				// The other props is a floating point
				return false;
			}
		}
		else{
			if(otherTemp.isInteger()){				
				return true;				
			}
			else{
				if(floatPrecision > otherTemp.floatPrecision)
					return true;
				else
					return false;
			}
		}
	}

}

package org.tamedragon.assemblyabstractions.constructs;

public class AssemValueProperties {
	
	private boolean unSigned;
	private boolean integer;
	private int integerSize;
	private int floatPrecision;
	
	public AssemValueProperties(){}
	
	public AssemValueProperties(boolean isUnsigned, boolean integer, int integerSize, int floatPrecision){
		this.unSigned = isUnsigned;
		this.integer = integer;
		this.integerSize = integerSize;
		this.floatPrecision = floatPrecision;
	}
	
	public boolean isUnSigned() {
		return unSigned;
	}
	public void setSigned(boolean unSigned) {
		this.unSigned = unSigned;
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
	
	/**
	 * Returns true if this object is "wider" than the other AVP object
	 * passed to this function, else false.
	 * @param otherAvp
	 * @return
	 */
	public boolean isWiderThan(AssemValueProperties otherAvp){
		if(integer){
			if(otherAvp.isInteger()){
				if(integerSize > otherAvp.getIntegerSize())
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
			if(otherAvp.isInteger()){				
					return true;				
			}
			else{
				if(floatPrecision > otherAvp.floatPrecision)
					return true;
				else
					return false;
			}
		}
	}
	
	public int getSizeInBytes(){
		if(integer)
			return integerSize;
		else
			return floatPrecision;
	}
}

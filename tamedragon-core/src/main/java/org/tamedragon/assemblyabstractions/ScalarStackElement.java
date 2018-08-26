package org.tamedragon.assemblyabstractions;

import org.tamedragon.common.llvmir.types.AbstractType;

/**
 * Represents a simple element on the stack like an integer, a char or a double floating point 
 * @author shreesha
 *
 */
public class ScalarStackElement implements StackElement {
	
	public short type;
	public short mode;
	
	private AbstractType  abstractType;
	
	public short getType() {
		return type;
	}
	public void setType(short type) {
		this.type = type;
	}
	public short getMode() {
		return mode;
	}
	public void setMode(short mode) {
		this.mode = mode;
	}
	public AbstractType getAbstractType() {
		return abstractType;
	}
	public void setAbstractType(AbstractType abstractType) {
		this.abstractType = abstractType;
	}
	
	public int getSize(){
		if(abstractType.isInteger()){
			return abstractType.getIntegerSize();
		}
		else{
			return abstractType.getFloatPrecision();
		}
	}
}

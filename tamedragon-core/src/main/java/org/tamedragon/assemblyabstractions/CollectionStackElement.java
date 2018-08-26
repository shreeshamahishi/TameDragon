package org.tamedragon.assemblyabstractions;

import java.util.Vector;

import org.tamedragon.common.llvmir.types.AbstractType;

/**
 * Represents a collection of elements on the stack that should be contiguous like 
 * an array, a struct, a union, etc.
 * @author shreesha
 *
 */
public class CollectionStackElement implements StackElement {

	public short type;
	public short mode;

	private Vector<AbstractType>  abstractTypes;

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

	public Vector<AbstractType> getAbstractTypes() {
		return abstractTypes;
	}

	public void setAbstractTypes(Vector<AbstractType> abstractTypes) {
		this.abstractTypes = abstractTypes;
	}


	public int getSize(){
		
		int size = 0;
		
		for(AbstractType abstractType: abstractTypes){
			if(abstractType.isInteger()){
				size +=  abstractType.getIntegerSize();
			}
			else{
				size +=  abstractType.getFloatPrecision();
			}
		}
		
		return size;
	}

}

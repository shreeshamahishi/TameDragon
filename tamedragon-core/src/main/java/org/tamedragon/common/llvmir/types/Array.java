package org.tamedragon.common.llvmir.types;

public class Array {
	
	private int numElements;    // Number of elements in the array
	private AbstractType type;          // The type of the elements
	
	public int getNumElements() {
		return numElements;
	}
	
	public void setNumElements(int numElements) {
		this.numElements = numElements;
	}
	
	public AbstractType getType() {
		return type;
	}
	
	public void setType(AbstractType type) {
		this.type = type;
	}

	public String toString(){
		return "[" + numElements + " x " + type.toString() + "]";
	}
}

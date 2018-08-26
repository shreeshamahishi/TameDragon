package org.tamedragon.compilers.clang.semantics;

/**
 * Represents the result of the function "populateShiftReduceTable". 
 * elementCount represents the total count
 * sizeCanBeDetermined represents the flag that indicates whether or not the size can be
 * determined accurately through the structure of the group (primarily if an array size is not specified)
 * @author shreesha
 *
 */
public class ShiftReducePopulationResult {
	
	private int elementCount;
	private boolean sizeCanBeDetermined;
	
	public int getElementCount() {
		return elementCount;
	}
	
	public void setElementCount(int elementCount) {
		this.elementCount = elementCount;
	}
	
	public boolean isSizeCanBeDetermined() {
		return sizeCanBeDetermined;
	}
	
	public void setSizeCanBeDetermined(boolean sizeCanBeDetermined) {
		this.sizeCanBeDetermined = sizeCanBeDetermined;
	}
}

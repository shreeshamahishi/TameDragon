package org.tamedragon.common.aliasanalysis;

import java.util.HashSet;
import java.util.Set;

import org.tamedragon.common.llvmir.instructions.Instruction;
import org.tamedragon.common.llvmir.types.Value;

/**
 * This class represents two sets: a may alias set and a must alias set. If two objects are
 * found to be in the same may-alias set, they are known to perhaps alias each other; if they
 * are found to be in the same must-alias set, they are known to alias each other.
 * 
 * If the two objects are found in separate tests or if the two objects are not found in either,
 * they do not alias each other.
 * 
 */

public class AliasSet {

	public enum AccessType{
		NoModRef(0),
		Refs(1),
		Mods(2),
		ModRef(3);

		private final int type;

		AccessType(int type){
			this.type = type;
		}
	}
	
	private AccessType accessType;
	
	private Set<Value> aliasSet;
	
	private boolean isVolatile;
	
	private long size;
	
	public AliasSet(){
		aliasSet = new HashSet<Value>();
	}

	public boolean isVolatile() {
		return isVolatile;
	}

	public void setVolatile(boolean isVolatile) {
		this.isVolatile = isVolatile;
	}

	public void addUnknownInst(Instruction instruction,
			AliasAnalysis aliasAnalysis) {}

	public AccessType getAccessType() {
		return accessType;
	}

	public void setAccessType(AccessType accessType) {
		this.accessType = accessType;
	}

	public boolean contains(Value pointer) {
		return aliasSet.contains(pointer);
	}

	public void updateSize(long newSize) {
		if(newSize > size){
			size = newSize;
		}
	}
}

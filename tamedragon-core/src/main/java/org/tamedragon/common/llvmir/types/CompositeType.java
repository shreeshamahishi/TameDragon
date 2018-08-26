package org.tamedragon.common.llvmir.types;


public class CompositeType extends Type{

	public CompositeType(CompilationContext compilationContext, TypeID typeID){
		super(compilationContext, typeID);
	}

	/**
	 * Return null if the index is invalid. If the index is valid, we pick and
	 * return the type at that index.
	 * @param index
	 * @return
	 */
	public Type getTypeAtIndex(int index){

		if(!isValidIndex(index)){
			// Log here
			return null;
		}

		return containedTypes.get(index);
	}

	/**
	 * The index is valid if the value that is passed in the parameter is greater than
	 * or equal and zero and is lesser than the size of containedTypes.
	 * @param index
	 * @return
	 */
	public boolean isValidIndex(int index){
		if(index < 0){
			// Log here
			return false;
		}
		if(index >= containedTypes.size()){
			// Log here
			return false;
		}

		return true;
	}
}

package org.tamedragon.common.llvmir.types;

import java.util.ArrayList;

public class SequentialType extends CompositeType {

	// Reference to the sing contained type.
	protected Type containedType;

	public SequentialType(CompilationContext compilationContext, TypeID parentTypeID,
			Type containedType){
		super(compilationContext, parentTypeID);
		this.containedType = containedType;
		if(containedTypes == null)
			containedTypes = new ArrayList<Type>();
		containedTypes.add(containedType);
	}

	public Type getContainedType(){
		return containedType;
	}

	public void setContainedType(Type containedType) {
		this.containedType = containedType;
	}
}

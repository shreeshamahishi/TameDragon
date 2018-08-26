package org.tamedragon.assemblyabstractions.constructs;

import org.tamedragon.common.llvmir.types.Value;

public class IRTreeType extends IRTreeStatement{

	private Value value;

	public IRTreeType(Value value){
		this.value = value;
		this.statementType = TreeStatementType.TYPE;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	public Value getValue() {
		return value;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof IRTreeType))
			return false;
		IRTreeType irTreeType = (IRTreeType)obj;
		Value value1 = getValue();
		Value value2 = irTreeType.getValue();
		if(value1.getType() != value2.getType())
			return false;
		if(value1.getName() != null && value2.getName() == null)
			return false;
		if(value1.getName() == null && value2.getName() != null)
			return false;
		if(!value1.getName().equals(value2.getName()))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		Value value = getValue();
		if(value.getName() != null)
			return value.getType().hashCode() + value.getName().hashCode();
		else
			return value.getType().hashCode();
	}

}

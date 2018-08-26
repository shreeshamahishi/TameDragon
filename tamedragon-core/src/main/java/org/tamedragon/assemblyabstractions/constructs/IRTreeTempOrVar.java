package org.tamedragon.assemblyabstractions.constructs;

import java.util.Random;

import org.tamedragon.common.llvmir.types.Value;

public class IRTreeTempOrVar extends IRTreeExp {

	private Value value;

	public IRTreeTempOrVar(Value value){
		this.value = value;
		expType = TreeNodeExpType.TEMP_OR_VAR_EXP;
	}

	public Value getValue() {
		return value;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof IRTreeTempOrVar))
			return false;

		IRTreeTempOrVar irTreeTempOrVar = (IRTreeTempOrVar)obj;
		//make sure both the value are equal
		Value v1 = this.getValue();
		Value v2 = irTreeTempOrVar.getValue();

		if(!(v1.getName().equals(v2.getName())))
			return false;
		else if(!(v1.getType().equals(v2.getType())))
			return false;

		int numOfUsers1 = v1.getNumUses();
		int numOfUsers2 = v1.getNumUses();



		if(numOfUsers1 != numOfUsers2)
			return false;

		for(int i = 0; i < numOfUsers1; i++){
			Value userOfv1 = v1.getUserAt(i);
			Value userOfv2 = v2.getUserAt(i);
			if(userOfv1 != userOfv2)
				return false;
		}


		return true;
	}

	@Override
	public int hashCode() {
		if(getValue().getName() != null)
			return this.getValue().getName().hashCode();
		else
			return new Random().nextInt();
	}

	public String getDescription() {
		if(value == null) return "Temp ??"; 

		return "Temp (" + value.toString() + ")";
	}


}

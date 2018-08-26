package org.tamedragon.assemblyabstractions.constructs;

import org.tamedragon.common.Label;
import org.tamedragon.common.llvmir.types.Temp;

public class AssemTemp extends AssemExp {
	private Temp temp;
	private AssemValueProperties assemValueProperties;   // The type of the temporary or variable
	
	public AssemTemp(Temp temp, AssemValueProperties assemValueProperties){
		this.temp = temp;
		this.assemValueProperties = assemValueProperties;
		
		this.temp.setFloatPrecision(this.assemValueProperties.getFloatPrecision());
		this.temp.setInteger(this.assemValueProperties.isInteger());
		this.temp.setIntegerSize(this.assemValueProperties.getIntegerSize());
		this.temp.setSigned(!this.assemValueProperties.isUnSigned());
	}
	
	public Temp getTemp() {
		return temp;
	}
	
	public int getAssemTypeType() {
		return AssemType.VALUE_TYPE;
	}
	
	public AssemType translateToCJump(Label testLabel, Label endLabel, boolean isNegation) {
		// Create a constant 0
		AssemConst assemConst0 = new AssemConst(0);
		
		// Create a conditional based on whether this temporary is 1 or 0
		return new AssemCJump(AssemCJump.GT, this, assemConst0,
				testLabel, endLabel);
	}
	
	public AssemType translateToIntType() {
		return this;
	}
	
	public String getDescription() {
		if(temp == null) return "Temp ??"; 
		if(temp.isPhysicalRegister()){
			// Get this from the value properties of this object, 
			// not from temp
			
		}
		return "Temp (" + temp.getDetailedDesc() + ")";
	}
	
	public AssemExpList children()  {
		return null;
	}
	
	public AssemExp build(AssemExpList list) {
		return this;
	}
	
	public AssemSeqExp canonize() {
		return new AssemSeqExp(null, this);
	}
	
	public int getExpType() {
		return AssemExp.TEMP;
	}

	public AssemValueProperties getAssemValueProperties() {
		return assemValueProperties;
	}

	public void setAssemValueProperties(AssemValueProperties assemValueProperties) {
		this.assemValueProperties = assemValueProperties;
	}	
	
	public AssemValueProperties getValueProperties(){
		return assemValueProperties;
	}
	
}

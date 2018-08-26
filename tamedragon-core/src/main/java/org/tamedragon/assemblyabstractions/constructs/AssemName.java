package org.tamedragon.assemblyabstractions.constructs;

import org.tamedragon.common.Label;
import org.tamedragon.common.llvmir.types.Temp;

public class AssemName extends AssemExp
{
	private Label label;
	  
	public AssemName(Label l)
	{
		label = l;
	}
	
	public AssemExpList children()
	{
		return null;
	}
	
	public Label getLabel()
	{
		return label;
	}
	
	public AssemType translateToCJump(Label labelTrue, Label labelFalse, boolean isNegation){
		
		return null;   // This should never happen
	}
	
	public AssemExp build(AssemExpList list) {
		return this;
	}
	
	/**
	* This function will coerce this binary operator expression into an int type,
	* by creating a temporary holding an int value
	*/
	public AssemType translateToIntType() {
		//return null; // This should never happen
		Temp destTemp = new Temp();
		AssemTemp assemTemp = new AssemTemp(destTemp, getValueProperties());
		AssemMove assemMove = new AssemMove(assemTemp, this);
		return new AssemSeqExp(assemMove, assemTemp);
	}
		
	public int getAssemTypeType() {
		return VALUE_TYPE; // It does not matter
	}
	
	public String getDescription() {
		return "Name (" + label.toString() + ")";
	}

	public AssemSeqExp canonize() {
		AssemSeqExp newSeqExp = new AssemSeqExp(null, this);
		return newSeqExp;	
	}
	
	public int getExpType() {
		return AssemExp.NAME;
	}
	
	public AssemValueProperties getValueProperties(){
		// Lets set a integer value to the the value (presumably the address of this name?)
		AssemValueProperties avp = new AssemValueProperties();
		avp.setInteger(true);
		avp.setIntegerSize(AssemType.SIZE_WORD);
		return avp;
	}
	
}

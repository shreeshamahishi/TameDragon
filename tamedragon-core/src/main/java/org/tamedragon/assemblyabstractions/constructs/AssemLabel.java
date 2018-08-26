package org.tamedragon.assemblyabstractions.constructs;

import org.tamedragon.common.Label;

public class AssemLabel extends AssemStm {
	private Label label;
	  
	public AssemLabel(Label l) {
		label = l;
	}
	  
	public AssemExpList children() {
		return null;
	}

	public Label getLabel(){
		return label;
	}
	
	public String getDescription() {
		return "Label(" + label.toString() + ")";
	}
	
	public AssemStm build(AssemExpList list){
		return this;
	}
	
	public AssemStm canonize(){
		return this;
	}
	
	public int getStmType() {
		return AssemStm.LABEL;
	}
}

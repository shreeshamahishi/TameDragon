package org.tamedragon.assemblyabstractions.constructs;

import org.tamedragon.common.Label;

/**
 * This class holds information used to create LLVM's Branch Instruction
 * @author ipsg
 *
 */
public class IRTreeLabel extends IRTreeStatement{
	
	private Label label;
	
	public IRTreeLabel(Label l){
		label = l;
		this.statementType = TreeStatementType.LABEL;
	}
	  
	public void setLabel(Label label) {
		this.label = label;
	}

	public Label getLabel(){
		return label;
	}
	
	public String getDescription() {
		return "Label(" + label.toString() + ")";
	}
	
	public TreeStatementType getStmType() {
		return TreeStatementType.LABEL;
	}
	
	public String toString(){
		return getLabel().toString();
	}

}

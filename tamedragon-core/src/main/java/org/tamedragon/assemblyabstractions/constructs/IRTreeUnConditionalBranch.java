package org.tamedragon.assemblyabstractions.constructs;

import org.tamedragon.common.Label;

/**
 * This class holds information needed to create LLVM's UnConditional Branch Instruction
 * @author ipsg
 *
 */
public class IRTreeUnConditionalBranch extends IRTreeStatement{
	
	private Label targetLabel;
	
	public IRTreeUnConditionalBranch(Label targetBlock){
		this.targetLabel = targetBlock;
		this.statementType = TreeStatementType.UNCONDITIONAL_JUMP;
	}
	
	public Label getTargetLabel() {
		return targetLabel;
	}
	public void setTargetLabel(Label targetLabel) {
		this.targetLabel = targetLabel;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}
}

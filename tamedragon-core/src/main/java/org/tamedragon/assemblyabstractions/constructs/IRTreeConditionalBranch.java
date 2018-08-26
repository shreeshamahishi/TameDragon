package org.tamedragon.assemblyabstractions.constructs;

import org.tamedragon.common.Label;

/**
 * This class holds information needed to create LLVM's Conditional Branch Instruction
 * @author ipsg
 *
 */
public class IRTreeConditionalBranch extends IRTreeStatement {
	
	private Label leftLabel, rightLabel;
	private IRTreeExp irTreeExp;
	
	public IRTreeConditionalBranch(Label leftLabel, Label rightLabel, IRTreeExp irTreeExp){
		this.leftLabel = leftLabel;
		this.rightLabel = rightLabel;
		this.irTreeExp = irTreeExp;
		this.statementType = TreeStatementType.CONDITIONAL_JUMP;
	}

	public void setLeftLabel(Label leftLabel) {
		this.leftLabel = leftLabel;
	}

	public Label getLeftLabel() {
		return leftLabel;
	}

	public void setRightLabel(Label rightLabel) {
		this.rightLabel = rightLabel;
	}

	public Label getRightLabel() {
		return rightLabel;
	}

	public IRTreeExp getIrTreeExp() {
		return irTreeExp;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}
}

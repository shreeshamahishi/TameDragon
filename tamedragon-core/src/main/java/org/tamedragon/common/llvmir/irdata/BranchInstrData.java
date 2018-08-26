package org.tamedragon.common.llvmir.irdata;

/**
 * The BranchInstrData class denotes a branch instruction that could be either a conditional branch or an 
 * unconditional jump.
 **/

public class BranchInstrData extends ValueData {

	private String forTrueTargetBasicBlock;
	private String forFalseTargetBasicBlock;
	private String condition;

	public void setIfTrue(String ifTrue) {
		this.forTrueTargetBasicBlock = ifTrue;
	}
	
	public String getIfTrue() {
		return forTrueTargetBasicBlock;
	}
	
	public void setIfFalse(String ifFalse) {
		this.forFalseTargetBasicBlock = ifFalse;
	}
	
	public String getIfFalse() {
		return forFalseTargetBasicBlock;
	}
	
	public void setCondition(String condition) {
		this.condition = condition;
	}
	
	public String getCondition() {
		return condition;
	}
}

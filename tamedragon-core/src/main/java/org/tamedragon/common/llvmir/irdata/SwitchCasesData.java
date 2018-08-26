package org.tamedragon.common.llvmir.irdata;

/**
 * The SwitchCasesData class denotes case data for a switch/case construct - the condition and the target basic
 * block if the condition is evaluated to true.
 **/

public class SwitchCasesData extends ValueData{
	
	private String condition;
	private String targetBasicBlock;
	
	public void setCondition(String condition) {
		this.condition = condition;
	}
	
	public String getCondition() {
		return condition;
	}
	
	public void setTarget(String target) {
		targetBasicBlock = target;
	}
	
	public String getTarget() {
		return targetBasicBlock;
	}
}
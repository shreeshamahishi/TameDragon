package org.tamedragon.assemblyabstractions.constructs;

import java.util.Map;

/**
 * This class holds in the information needed to create LLVM's Switch Instruction
 * @author ipsg
 *
 */
public class IRTreeSwitch extends IRTreeStatement {
	
	private IRTree switchExpr;
	private IRTreeLabel defaultLabel;
	private Map<IRTree, IRTreeLabel> caseValuesVsLabels;
	
	public IRTreeSwitch(IRTree switchExpr, IRTreeLabel defaultLabel, Map<IRTree, IRTreeLabel> caseValuesVsLabels){
		this.switchExpr = switchExpr;
		this.defaultLabel = defaultLabel;
		this.caseValuesVsLabels = caseValuesVsLabels;
		this.statementType = TreeStatementType.SWITCH;
	}
	
	public IRTree getSwitchExpr() {
		return switchExpr;
	}

	public IRTreeLabel getDefaultLabel() {
		return defaultLabel;
	}

	public Map<IRTree, IRTreeLabel> getCaseValuesVsLabels() {
		return caseValuesVsLabels;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

}

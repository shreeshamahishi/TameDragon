package org.tamedragon.assemblyabstractions.constructs;

import java.util.Map;

import org.tamedragon.common.Label;
import org.tamedragon.common.llvmir.types.Type;

/**
 * This class holds information needed to create LLVM's PhiNode Instruction
 * @author ipsg
 *
 */
public class IRTreePhiExpr extends IRTreeExp {
	
	private Type type;
	private Map<Label, IRTreeExp> labelVsExprMap;
	
	public IRTreePhiExpr(Type type, Map<Label, IRTreeExp> labelVsValueMap){
		this.type = type;
		this.labelVsExprMap = labelVsValueMap;
		this.expType = TreeNodeExpType.PHI_NODE;
	}
	
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Map<Label, IRTreeExp> getLabelVsExprMap() {
		return labelVsExprMap;
	}

	public void setLabelVsExprMap(Map<Label, IRTreeExp> labelVsExprMap) {
		this.labelVsExprMap = labelVsExprMap;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

}

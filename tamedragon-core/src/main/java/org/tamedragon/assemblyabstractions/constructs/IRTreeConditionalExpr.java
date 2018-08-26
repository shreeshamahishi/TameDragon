package org.tamedragon.assemblyabstractions.constructs;

import org.tamedragon.common.llvmir.instructions.CmpInst.Predicate;

/**
 * This class holds information needed to create LLVM's Conditional Expression Instructions
 * @author ipsg
 *
 */
public class IRTreeConditionalExpr extends IRTreeExp{
	
	private IRTreeExp leftExp;
	private IRTreeExp rightExp;
	private Predicate predicate;
	
	public IRTreeConditionalExpr(Predicate predicate, IRTreeExp leftExp, IRTreeExp rightExp){
		this.leftExp = leftExp;
		this.rightExp = rightExp;
		this.predicate = predicate;
		this.expType = TreeNodeExpType.CONDITIONAL_EXPR;
	}

	public IRTreeExp getLeftExp() {
		return leftExp;
	}

	public void setLeftExp(IRTreeExp leftExp) {
		this.leftExp = leftExp;
	}

	public IRTreeExp getRightExp() {
		return rightExp;
	}

	public void setRightExp(IRTreeExp rightExp) {
		this.rightExp = rightExp;
	}

	public Predicate getPredicate() {
		return predicate;
	}

	public void setPredicate(Predicate predicate) {
		this.predicate = predicate;
	}
}

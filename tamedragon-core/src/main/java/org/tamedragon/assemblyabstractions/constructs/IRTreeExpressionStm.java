package org.tamedragon.assemblyabstractions.constructs;


/**
 * Represents any expression coerced into a statement
 * @author ipsg
 *
 */

public class IRTreeExpressionStm extends IRTreeStatement {
	
	private IRTreeExp expressionTree;

	public IRTreeExp getExpressionTree() {
		return expressionTree;
	}

	public IRTreeExpressionStm(IRTreeExp expressionTree){
		this.expressionTree = expressionTree;
		this.statementType = TreeStatementType.EXPRESSION;
	}
	
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}
}

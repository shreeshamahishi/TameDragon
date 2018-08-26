package org.tamedragon.compilers.clang.preprocessor;

public class ConstExpr extends Absyn {
	
	private ConditionalExpr conditionalExpr;
	
	public ConstExpr(){}
	
	public ConditionalExpr getConditionalExpr() {
		return conditionalExpr;
	}
	
	public void setConditionalExpr(ConditionalExpr condExpr) {
		this.conditionalExpr = condExpr;
	}
	
	public int evaluate(){
		return conditionalExpr.evaluate();
	}
	
}

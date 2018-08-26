package org.tamedragon.compilers.clang.abssyntree;

public class ConditionalExpr extends Absyn implements Expr{
	
	private LogicalOrExpr logicalOrExpr;
	private RootExpr trueInConditional;
	private ConditionalExpr falseInConditional;
	
	public ConditionalExpr(){}
	
	public ConditionalExpr(LogicalOrExpr loe){
		this.logicalOrExpr = loe;
	}
	
	public ConditionalExpr(LogicalOrExpr loe, RootExpr re, ConditionalExpr ce){
		this.logicalOrExpr = loe;
		this.trueInConditional = re;
		this.falseInConditional = ce;
	}
	
	public ConditionalExpr getFalseInConditional() {
		return falseInConditional;
	}
	public void setFalseInConditional(ConditionalExpr falseInConditional) {
		this.falseInConditional = falseInConditional;
	}
	public LogicalOrExpr getLogicalOrExpr() {
		return logicalOrExpr;
	}
	public void setLogicalOrExpr(LogicalOrExpr logicalOrExpr) {
		this.logicalOrExpr = logicalOrExpr;
	}
	public RootExpr getTrueInConditional() {
		return trueInConditional;
	}
	public void setTrueInConditional(RootExpr trueInConditional) {
		this.trueInConditional = trueInConditional;
	}
	
	public int getExprType(){
		return Expr.CONDITIONAL;
	}
	
	public String toString(){
		String str = "";
		if(trueInConditional != null){
			str += logicalOrExpr.toString() + " ? " + trueInConditional.toString() + " : " + falseInConditional.toString();
		}
		else{
			return logicalOrExpr.toString();
		}
		return str;
	}

}

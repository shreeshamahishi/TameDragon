package org.tamedragon.compilers.clang.preprocessor;

public class ConditionalExpr extends Absyn {
	
	private LogicalOrExpr logicalOrExpr;
	private ConstExpr trueInConditional;
	private ConditionalExpr falseInConditional;
	
	public ConditionalExpr(){}
	
	public ConditionalExpr(LogicalOrExpr loe){
		this.logicalOrExpr = loe;
	}
	
	public ConditionalExpr(LogicalOrExpr loe, ConstExpr constExpr, ConditionalExpr ce){
		this.logicalOrExpr = loe;
		this.trueInConditional = constExpr;
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
	
	public ConstExpr getTrueInConditional() {
		return trueInConditional;
	}
	
	public void setTrueInConditional(ConstExpr trueInConditional) {
		this.trueInConditional = trueInConditional;
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

	public int evaluate(){
		int val = logicalOrExpr.evaluate();
		if(trueInConditional != null && falseInConditional != null){
			if(val == 0)			
				return falseInConditional.evaluate();
			else
				return trueInConditional.evaluate();			
		}
		
		return val;
	}
	
}

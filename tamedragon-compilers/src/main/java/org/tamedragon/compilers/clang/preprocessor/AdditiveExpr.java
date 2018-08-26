package org.tamedragon.compilers.clang.preprocessor;

public class AdditiveExpr extends Absyn {
	
	public static final int ADD = 0;
	public static final int SUBTRACT = 1;
	
	private int operator;
	private MultiplicativeExpr multiplicativeExpr;
	private AdditiveExpr additiveExpr;
	
	public AdditiveExpr(){}
	
	public AdditiveExpr(AdditiveExpr ae, MultiplicativeExpr me, int oprType){
		this.additiveExpr = ae;
		this.multiplicativeExpr = me;
		this.operator = oprType;
		if(ae != null){
			setLineNum(ae.getLineNum());
			setPos(ae.getPos());
		}
	}
	
	public int getOperator() {
		return operator;
	}

	public void setOperator(int operator) {
		this.operator = operator;
	}

	public MultiplicativeExpr getMultiplicativeExpr() {
		return multiplicativeExpr;
	}

	public void setMultiplicativeExpr(MultiplicativeExpr multiplicativeExpr) {
		this.multiplicativeExpr = multiplicativeExpr;
	}

	public AdditiveExpr getAdditiveExpr() {
		return additiveExpr;
	}

	public void setAdditiveExpr(AdditiveExpr additiveExpr) {
		this.additiveExpr = additiveExpr;
	}
	
	public String toString(){
		String str = "";
		
		if(additiveExpr != null){
			String oprStr = "+";
			if(operator == SUBTRACT)
				oprStr = "-";
			
			str += additiveExpr.toString() + oprStr + multiplicativeExpr.toString();
		}
		else{
			return multiplicativeExpr.toString();
		}
		return str;
	}	
	
	public int evaluate(){
		int val = multiplicativeExpr.evaluate();
		
		if(additiveExpr != null){
			int aeVal = additiveExpr.evaluate();
			
			if(operator == ADD)
				return val + aeVal;
			
			else 
				return val - aeVal;
		}		
		
		return val;
	}	
	
}

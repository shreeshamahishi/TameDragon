package org.tamedragon.compilers.clang.preprocessor;

public class MultiplicativeExpr extends Absyn {
	public static final int MULTIPLY = 0;
	public static final int DIVIDE = 1;
	public static final int MODULO = 2;

	private int operator;
	private UnaryExpr unaryExpr;
	private MultiplicativeExpr multiplicativeExprRight;
	
	public MultiplicativeExpr(){}

	public MultiplicativeExpr(UnaryExpr ueLeft, MultiplicativeExpr multiplicativeExprRight, int opr){
		operator = opr;
		this.unaryExpr = ueLeft;
		this.multiplicativeExprRight = multiplicativeExprRight;	
		if(ueLeft != null)
			setLineNum(ueLeft.getLineNum());

	}

	public int getOperator() {
		return operator;
	}

	public void setOperator(int operator) {
		this.operator = operator;
	}

	public UnaryExpr getUnaryExpr() {
		return unaryExpr;
	}

	public void setUnaryExpr(UnaryExpr unaryExpr) {
		this.unaryExpr = unaryExpr;
	}

	public MultiplicativeExpr getOtherExpr() {
		return multiplicativeExprRight;
	}

	public void setOtherExpr(MultiplicativeExpr multiplicativeExpr) {
		this.multiplicativeExprRight = multiplicativeExpr;
	}

	public String toString(){
		String str = "";

		if(multiplicativeExprRight != null){
			String oprStr = "*";
			if(operator == DIVIDE)
				oprStr = "/";
			else if(operator == MODULO)
				oprStr = "%";

			str += unaryExpr.toString() + oprStr + multiplicativeExprRight.toString();
		}
		else{
			return unaryExpr.toString();
		}
		
		return str;
	}	

	public int evaluate(){
		int val = unaryExpr.evaluate();
		
		if(multiplicativeExprRight != null){
			int meVal = multiplicativeExprRight.evaluate();
			
			if(operator == MULTIPLY)
				return val * meVal;
			
			else if(operator == DIVIDE)
				return val / meVal;
			else
				return val % meVal;
		}		
		
		return val;
	}	
	
}

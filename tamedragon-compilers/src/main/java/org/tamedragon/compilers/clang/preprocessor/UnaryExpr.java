package org.tamedragon.compilers.clang.preprocessor;

public class UnaryExpr extends Absyn {
	public static final int PLUS = 1;
	public static final int MINUS = 2;
	public static final int TILDE = 3;
	public static final int NOT = 4;
	
	private PrimaryExpr primaryExpr;
	private int operator;
	private MultiplicativeExpr multiplicativeExpr;	
	private int type;

	public UnaryExpr(){}
	
	public UnaryExpr(int type, MultiplicativeExpr me){
		this.type = type;
		multiplicativeExpr = me;
	}
	
	public UnaryExpr(PrimaryExpr pe){
		primaryExpr = pe;
	}
	
	public int getOperator() {
		return operator;
	}
	public void setOperator(int operator) {
		this.operator = operator;
	}
	
	public PrimaryExpr getPrimaryExpr() {
		return primaryExpr;
	}
	
	public void setPrimaryExpr(PrimaryExpr primaryExpr) {
		this.primaryExpr = primaryExpr;
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}	

	public MultiplicativeExpr getMultiplicativeExpr() {
		return multiplicativeExpr;
	}

	public void setMultiplicativeExpr(MultiplicativeExpr multiplicativeExpr) {
		this.multiplicativeExpr = multiplicativeExpr;
	}
	
	public String toString(){
		
		if(multiplicativeExpr != null){
			String pre = "+";
			if(type == MINUS)
				pre = "-";
			else if(type == TILDE)
				pre = "~";
			else if(type == NOT)
				pre = "!";
			return pre + multiplicativeExpr.toString();
		}
	
		return primaryExpr.toString();		
	}	
	
	public int evaluate(){
		
		if(primaryExpr != null)
			return primaryExpr.evaluate();
		
		// This must be one of the real unary operators applied to a multiplicative expr
		int meVal = multiplicativeExpr.evaluate();
		if(operator == PLUS)
			return meVal;
		else if(operator == MINUS)
			return -1 * meVal;
		else if(operator == TILDE)
			return  ~ meVal;
		else { // NOT (!)
			if(meVal == 0)
				return 1;
			else return 0;
		}		
	}	
}

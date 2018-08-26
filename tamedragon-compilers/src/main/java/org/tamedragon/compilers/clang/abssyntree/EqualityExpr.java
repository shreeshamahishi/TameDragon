package org.tamedragon.compilers.clang.abssyntree;

public class EqualityExpr  extends Absyn implements Expr{
	public static final int EQUALS = 1;
	public static final int NOT_EQUALS = 2;
	
	private int operator;
	private RelationalExpr relationalExpr;
	private EqualityExpr equalityExpr;
	
	public EqualityExpr(){
	}
	
	public EqualityExpr(EqualityExpr ee, RelationalExpr re, int oprType){
		this.equalityExpr = ee;
		this.relationalExpr = re;
		this.operator = oprType;
		if(ee != null){
			setLineNum(ee.getLineNum());
			setPos(ee.getPos());
		}
	}
	
	public int getExprType(){
		return Expr.EQUALITY;
	}

	public int getOperator() {
		return operator;
	}

	public void setOperator(int operator) {
		this.operator = operator;
	}

	public RelationalExpr getRelationalExpr() {
		return relationalExpr;
	}

	public void setRelationalExpr(RelationalExpr relationalExpr) {
		this.relationalExpr = relationalExpr;
	}

	public EqualityExpr getEqualityExpr() {
		return equalityExpr;
	}

	public void setEqualityExpr(EqualityExpr equalityExpr) {
		this.equalityExpr = equalityExpr;
	}
	
	public String toString(){
		String str = "";
		
		if(equalityExpr != null){
			String oprStr = "==";
			if(operator == NOT_EQUALS)
				oprStr = "!=";
			str += equalityExpr.toString() + oprStr + relationalExpr.toString();
		}
		else{
			return relationalExpr.toString();
		}
		return str;
	}	
}

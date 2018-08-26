package org.tamedragon.compilers.clang.abssyntree;

public class ConstExpr  extends Absyn  implements Expr{
	private ConditionalExpr condExpr;

	public ConstExpr(){}
	
	public ConstExpr(ConditionalExpr ce){
		this.condExpr = ce;
	}
	
	public ConditionalExpr getCondExpr() {
		return condExpr;
	}

	public void setCondExpr(ConditionalExpr condExpr) {
		this.condExpr = condExpr;
	}
	
	public int getExprType(){
		return Expr.CONSTANT;
	}
	
	public String toString(){
		return condExpr.toString();
	}
	
}
	
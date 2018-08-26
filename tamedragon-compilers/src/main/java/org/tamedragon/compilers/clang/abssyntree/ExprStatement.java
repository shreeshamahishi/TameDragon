package org.tamedragon.compilers.clang.abssyntree;

public class ExprStatement extends Statement{

	private RootExpr expr;
	
	public ExprStatement(RootExpr expr){
		this.expr = expr;
	}

	public RootExpr getExpr() {
		return expr;
	}

	public void setExpr(RootExpr expr) {
		this.expr = expr;
	}
}

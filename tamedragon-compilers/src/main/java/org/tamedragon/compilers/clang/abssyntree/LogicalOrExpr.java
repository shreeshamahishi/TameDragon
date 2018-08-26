package org.tamedragon.compilers.clang.abssyntree;

public class LogicalOrExpr extends Absyn implements Expr{
	private LogicalAndExpr logicalAndExpr;
	private LogicalOrExpr logicalOrExpr;

	public LogicalOrExpr(){}
	
	public LogicalOrExpr(LogicalOrExpr loe, LogicalAndExpr lae){
		this.logicalAndExpr = lae;
		this.logicalOrExpr = loe;
		if(loe != null){
			setLineNum(loe.getLineNum());
			setPos(loe.getPos());
		}
	}
	
	public int getExprType(){
		return Expr.LOGICAL_OR;
	}
	
	public LogicalAndExpr getLogicalAndExpr() {
		return logicalAndExpr;
	}

	public void setLogicalAndExpr(LogicalAndExpr logicalAndExpr) {
		this.logicalAndExpr = logicalAndExpr;
	}

	public LogicalOrExpr getLogicalOrExpr() {
		return logicalOrExpr;
	}

	public void setLogicalOrExpr(LogicalOrExpr logicalOrExpr) {
		this.logicalOrExpr = logicalOrExpr;
	}

	public String toString(){
		String str = "";
		if(logicalOrExpr != null){
			str += logicalOrExpr.toString() + " || " + logicalAndExpr.toString();
		}
		else{
			return logicalAndExpr.toString();
		}
		return str;
	}
}

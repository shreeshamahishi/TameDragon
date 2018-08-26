package org.tamedragon.compilers.clang.abssyntree;

public class LogicalAndExpr extends Absyn implements Expr{
	
	private InclusiveOrExpr inclusiveOrExpr;
	private LogicalAndExpr logicalAndExpr;
	
	public LogicalAndExpr(){}
	
	public LogicalAndExpr(LogicalAndExpr lae, InclusiveOrExpr ioe){
		this.inclusiveOrExpr = ioe;
		this.logicalAndExpr = lae;
		if(lae != null){
			setLineNum(lae.getLineNum());
			setPos(lae.getPos());
		}
	}
	
	public int getExprType(){
		return Expr.LOGICAL_AND;
	}

	public InclusiveOrExpr getInclusiveOrExpr() {
		return inclusiveOrExpr;
	}

	public void setInclusiveOrExpr(InclusiveOrExpr inclusiveOrExpr) {
		this.inclusiveOrExpr = inclusiveOrExpr;
	}

	public LogicalAndExpr getLogicalAndExpr() {
		return logicalAndExpr;
	}

	public void setLogicalAndExpr(LogicalAndExpr logicalAndExpr) {
		this.logicalAndExpr = logicalAndExpr;
	}

	public String toString(){
		String str = "";
		if(logicalAndExpr != null){
			str += logicalAndExpr.toString() + " && " + inclusiveOrExpr.toString();
		}
		else{
			return inclusiveOrExpr.toString();
		}
		return str;
	}
	
}

package org.tamedragon.compilers.clang.abssyntree;

public class InclusiveOrExpr extends Absyn implements Expr{
	
	private ExclusiveOrExpr exclusiveOrExpr;
	private InclusiveOrExpr inclusiveOrExpr;
	
	public InclusiveOrExpr(){}
	
	public InclusiveOrExpr(InclusiveOrExpr ioe, ExclusiveOrExpr eoe){
		this.exclusiveOrExpr = eoe;
		this.inclusiveOrExpr = ioe;
		if(ioe != null){
			setLineNum(ioe.getLineNum());
			setPos(ioe.getPos());
		}
	}
	
	public int getExprType(){
		return Expr.INCLUSIVE_OR;
	}

	public ExclusiveOrExpr getExclusiveOrExpr() {
		return exclusiveOrExpr;
	}

	public void setExclusiveOrExpr(ExclusiveOrExpr exclusiveOrExpr) {
		this.exclusiveOrExpr = exclusiveOrExpr;
	}

	public InclusiveOrExpr getInclusiveOrExpr() {
		return inclusiveOrExpr;
	}

	public void setInclusiveOrExpr(InclusiveOrExpr inclusiveOrExpr) {
		this.inclusiveOrExpr = inclusiveOrExpr;
	}
	
	public String toString(){
		String str = "";
		if(inclusiveOrExpr != null){
			str += inclusiveOrExpr.toString() + " | " + exclusiveOrExpr.toString();
		}
		else{
			return exclusiveOrExpr.toString();
		}
		return str;
	}
	
}

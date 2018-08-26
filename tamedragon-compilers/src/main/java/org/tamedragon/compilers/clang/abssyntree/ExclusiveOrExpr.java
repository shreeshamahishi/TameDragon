package org.tamedragon.compilers.clang.abssyntree;

public class ExclusiveOrExpr extends Absyn implements Expr {
	private AndExpr andExpr;
	private ExclusiveOrExpr exclusiveOrExpr;
	
	public ExclusiveOrExpr(){}
	
	public ExclusiveOrExpr(ExclusiveOrExpr eoe, AndExpr ae){
		this.exclusiveOrExpr = eoe;
		this.andExpr = ae;
		if(eoe != null){
			setLineNum(eoe.getLineNum());
			setPos(eoe.getPos());
		}
	}
	
	public int getExprType(){
		return Expr.EXCLUSIVE_OR;
	}

	public AndExpr getAndExpr() {
		return andExpr;
	}

	public void setAndExpr(AndExpr andExpr) {
		this.andExpr = andExpr;
	}

	public ExclusiveOrExpr getExclusiveOrExpr() {
		return exclusiveOrExpr;
	}

	public void setExclusiveOrExpr(ExclusiveOrExpr exclusiveOrExpr) {
		this.exclusiveOrExpr = exclusiveOrExpr;
	}

	public String toString(){
		String str = "";
		if(exclusiveOrExpr != null){
			str += exclusiveOrExpr.toString() + " ^ " + andExpr.toString();
		}
		else{
			return andExpr.toString();
		}
		return str;
	}
}

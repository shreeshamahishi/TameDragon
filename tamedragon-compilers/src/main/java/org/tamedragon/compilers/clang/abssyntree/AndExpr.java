package org.tamedragon.compilers.clang.abssyntree;

public class AndExpr extends Absyn implements Expr{
	private EqualityExpr equalityExpr;
	private AndExpr andExpr ;
	
	public AndExpr(){}
	
	public AndExpr(AndExpr ae, EqualityExpr ee){
		this.andExpr = ae;
		this.equalityExpr = ee;
		if(ae != null){
			setLineNum(ae.getLineNum());
			setPos(ae.getPos());
		}
	}
	
	public int getExprType(){
		return Expr.AND;
	}

	public EqualityExpr getEqualityExpr() {
		return equalityExpr;
	}

	public void setEqualityExpr(EqualityExpr equalityExpr) {
		this.equalityExpr = equalityExpr;
	}

	public AndExpr getAndExpr() {
		return andExpr;
	}

	public void setAndExpr(AndExpr andExpr) {
		this.andExpr = andExpr;
	}
	
	public String toString(){
		String str = "";
		if(andExpr != null){
			str += andExpr.toString() + " & " + equalityExpr.toString();
		}
		else{
			return equalityExpr.toString();
		}
		return str;
	}
	
}

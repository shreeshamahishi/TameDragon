package org.tamedragon.compilers.clang.preprocessor;

public class AndExpr extends Absyn {
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
	
	public int evaluate(){
		int val = equalityExpr.evaluate();
		
		if(andExpr != null){
			int eoeVal = andExpr.evaluate();
			
			return val & eoeVal; 
		}		
		
		return val;
	}
	
}

package org.tamedragon.compilers.clang.preprocessor;

public class ShiftExpr extends Absyn {
	
	public static final int LEFT_SHIFT = 0;
	public static final int RIGHT_SHIFT = 1;
	
	private int operator;
	private AdditiveExpr additiveExpr;
	private ShiftExpr shiftExpr;
	
	public ShiftExpr(){}
	
	public ShiftExpr(ShiftExpr se, AdditiveExpr ae, int opr){
		this.shiftExpr = se;
		this.additiveExpr = ae;
		this.operator = opr;
		
		if(se != null){
			setLineNum(se.getLineNum());
			setPos(se.getPos());
		}
	}
	
	public int getOperator() {
		return operator;
	}

	public void setOperator(int operator) {
		this.operator = operator;
	}

	public AdditiveExpr getAdditiveExpr() {
		return additiveExpr;
	}

	public void setAdditiveExpr(AdditiveExpr additiveExpr) {
		this.additiveExpr = additiveExpr;
	}

	public ShiftExpr getShiftExpr() {
		return shiftExpr;
	}

	public void setShiftExpr(ShiftExpr shiftExpr) {
		this.shiftExpr = shiftExpr;
	}
	
	public String toString(){
		String str = "";
		
		if(shiftExpr != null){
			String oprStr = "<<";
			if(operator == RIGHT_SHIFT)
				oprStr = ">>";
			
			str += shiftExpr.toString() + oprStr + additiveExpr.toString();
		}
		else{
			return additiveExpr.toString();
		}
		return str;
	}	
	
	public int evaluate(){
		int val = additiveExpr.evaluate();
		
		if(shiftExpr != null){
			int seVal = shiftExpr.evaluate();
			
			if(operator == LEFT_SHIFT)
				return val << seVal;
			
			else 
				return val >> seVal;
		}		
		
		return val;
	}	
}

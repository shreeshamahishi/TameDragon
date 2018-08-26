package org.tamedragon.compilers.clang.abssyntree;

public class ShiftExpr extends Absyn implements Expr{
	
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
	
	public int getExprType(){
		return Expr.SHIFT;
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
}

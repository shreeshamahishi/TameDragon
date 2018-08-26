package org.tamedragon.compilers.clang.abssyntree;

public class RelationalExpr extends Absyn implements Expr{
	
	public static final int LESSER_THAN = 0;
	public static final int GREATER_THAN = 1;
	public static final int LESSER_THAN_OR_EQUAL_TO = 2;
	public static final int GREATER_THAN_OR_EQUAL_TO = 3;
	
	private ShiftExpr shiftExpr;
	private int operator;
	private RelationalExpr relationalExpr;
	
	public RelationalExpr(){}
	
	public RelationalExpr(RelationalExpr re, ShiftExpr se, int oprType){
		this.relationalExpr = re;
		this.shiftExpr = se;
		this.operator = oprType;
		
		if(re != null){
			setLineNum(re.getLineNum());
			setPos(re.getPos());
		}
	}

	public int getExprType(){
		return Expr.RELATIONAL;
	}

	public ShiftExpr getShiftExpr() {
		return shiftExpr;
	}

	public void setShiftExpr(ShiftExpr shiftExpr) {
		this.shiftExpr = shiftExpr;
	}

	public int getOperator() {
		return operator;
	}

	public void setOperator(int operator) {
		this.operator = operator;
	}

	public RelationalExpr getRelationalExpr() {
		return relationalExpr;
	}

	public void setRelationalExpr(RelationalExpr relationalExpr) {
		this.relationalExpr = relationalExpr;
	}
	
	public String toString(){
		String str = "";
		
		if(relationalExpr != null){
			String oprStr = "<";
			if(operator == GREATER_THAN)
				oprStr = ">";
			else if(operator == GREATER_THAN_OR_EQUAL_TO)
				oprStr = ">=";
			else if(operator == LESSER_THAN_OR_EQUAL_TO)
				oprStr = "<=";
			
			str += relationalExpr.toString() + oprStr + shiftExpr.toString();
		}
		else{
			return shiftExpr.toString();
		}
		return str;
	}	
	
}

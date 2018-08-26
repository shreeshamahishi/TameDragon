package org.tamedragon.compilers.clang.abssyntree;

public class MultiplicativeExpr extends Absyn implements Expr{
	public static final int MULTIPLY = 0;
	public static final int DIVIDE = 1;
	public static final int MODULO = 2;
	
	private int operator;
	private CastExpr castExpr;
	private MultiplicativeExpr multiplicativeExpr;
	
	public MultiplicativeExpr(){}
	
	public MultiplicativeExpr(MultiplicativeExpr me, CastExpr castExpr, int opr){
		operator = opr;
		this.castExpr = castExpr;
		this.multiplicativeExpr = me;	
		
		if(me != null){
			setLineNum(me.getLineNum());
			setPos(me.getPos());
		}
	}
	
	public int getExprType(){
		return Expr.MULTIPLICATIVE;
	}

	public int getOperator() {
		return operator;
	}

	public void setOperator(int operator) {
		this.operator = operator;
	}

	public CastExpr getCastExpr() {
		return castExpr;
	}

	public void setCastExpr(CastExpr castExpr) {
		this.castExpr = castExpr;
	}

	public MultiplicativeExpr getOtherExpr() {
		return multiplicativeExpr;
	}

	public void setOtherExpr(MultiplicativeExpr multiplicativeExpr) {
		this.multiplicativeExpr = multiplicativeExpr;
	}
	
	public String toString(){
		String str = "";
		
		if(multiplicativeExpr != null){
			String oprStr = "*";
			if(operator == DIVIDE)
				oprStr = "/";
			
			str += multiplicativeExpr.toString() + oprStr + castExpr.toString();
		}
		else{
			return castExpr.toString();
		}
		return str;
	}	
	
}

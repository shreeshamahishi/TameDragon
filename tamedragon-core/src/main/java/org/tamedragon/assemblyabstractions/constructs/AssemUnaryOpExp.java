package org.tamedragon.assemblyabstractions.constructs;

import org.tamedragon.common.Label;
import org.tamedragon.common.llvmir.types.Temp;

public class AssemUnaryOpExp extends AssemExp {	
	private int op;
	private AssemExp expr;

	public final static int ONES_COMPLEMENT = 0;
	public final static int TWOS_COMPLEMENT = 1;

	public AssemUnaryOpExp(int b, AssemExp expr) {
		op = b;
		this.expr = expr; 
	}

	public int getOperator() {
		return op;
	}

	public AssemExpList children() {
		return new AssemExpList(expr, null);
	}

	public AssemType translateToCJump(Label labelTrue, Label labelFalse, boolean isNegation) {
		AssemType returnAssemType = null;

		return returnAssemType;
	}

	/**
	 * This function will coerce this binary operator expression into an int type,
	 * by creating a temporary holding an int value
	 */
	public AssemType translateToIntType() {
		AssemType retAssemType = null;

		return retAssemType;
	}

	public String getDescription() {
		String retStr = "UnaryOpExp(";
		String opStr = "";
		switch(op) {
		case ONES_COMPLEMENT:
			opStr = "~";
			break;
		case TWOS_COMPLEMENT:
			opStr = "<>";
			break;		
		}

		return retStr + opStr + ")";
	}

	public AssemExp getExpr() {
		return expr;
	}

	public void setExpr(AssemExp expr) {
		this.expr = expr;
	}
	
	public AssemExp build(AssemExpList list) {
		return new AssemBinOpExp(op, list.getHead(),list.getTail().getHead());
	}

	public AssemSeqExp canonize() {		
		AssemSeqExp canonizedExpr = expr.canonize();

		AssemStm canonizedExprStm = canonizedExpr.getStm();

		AssemExp canonizedExprExp = canonizedExpr.getExp();

		AssemSeqExp retSeqExp = null;
		if(commutes(canonizedExprStm, canonizedExprExp)) {
			// Order does not matter
			AssemStm stm = null;
			if(canonizedExprStm == null) {
				return new AssemSeqExp(null, this);
			}
			else {
				return new AssemSeqExp(stm, 
						new AssemUnaryOpExp(op, canonizedExprExp));

			}
		}
		else {
			// Order matters; the left expression must be evaluated before the right statement
			Temp temp = new Temp();
			AssemTemp assemTemp = new AssemTemp(temp, canonizedExprExp.getValueProperties());
			AssemStm stm = null;
			AssemMove moveStm = new AssemMove(assemTemp,canonizedExprExp );

			if(canonizedExprStm == null) {
				retSeqExp = new AssemSeqExp(moveStm, 
						new AssemUnaryOpExp(op, canonizedExprExp));
			}
			else  {

				stm = new AssemSeq(canonizedExprStm, moveStm);
				retSeqExp = new AssemSeqExp(stm, 
						new AssemUnaryOpExp(op, canonizedExprExp));
			}

		}
		return retSeqExp;
	}

	public boolean commutes(AssemStm stm, AssemExp exp) {
		return (stm == null) || (exp instanceof AssemName) || (exp instanceof AssemConst);
	}

	public int getExpType() {
		return AssemExp.UNARY_EXP;
	}

	public int getAssemTypeType() {
		return VALUE_TYPE;
	}

	public AssemValueProperties getValueProperties(){
		return expr.getValueProperties();
	}
	
}

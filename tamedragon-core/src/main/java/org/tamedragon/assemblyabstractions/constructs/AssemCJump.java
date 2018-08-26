package org.tamedragon.assemblyabstractions.constructs;

import org.tamedragon.common.Label;
import org.tamedragon.common.llvmir.types.Temp;

public class AssemCJump extends AssemStm
{
	public final static int EQ = 0;
	public final static int NE = 1;
	public final static int LT = 2;
	public final static int GT = 3;
	public final static int LE = 4;
	public final static int GE = 5;
	public final static int ULT = 6;
	public final static int ULE = 7;
	public final static int UGT = 8;
	public final static int UGE = 9;
	public final static int LTE = 10;

	public int relop;
	private AssemExp left, right;
	private Label iftrue, iffalse;

	public AssemCJump(int rel, AssemExp l, AssemExp r, 
			Label t, Label f) 
	{
		relop = rel; 
		left = l;
		right = r;
		iftrue = t;
		iffalse = f;
	}

	public AssemExpList children() 
	{
		return new AssemExpList(left, new AssemExpList(right,null));
	}

	public static int notRel(int relop) {
		switch (relop) {
		case EQ:  return NE;
		case NE:  return EQ;
		case LT:  return GE;
		case GE:  return LT;
		case GT:  return LE;
		case LE:  return GT;
		case ULT: return UGE;
		case UGE: return ULT;
		case UGT: return ULE;
		case ULE: return UGT;
		default: return -1;
		}
	}

	public Label getIfTrueLabel() {
		return iftrue;
	}

	public Label getIfFalseLabel() {
		return iffalse;
	}

	public String getDescription() {
		String retStr = "CJump(";
		String opStr = "";
		switch (relop) 
		{
		case EQ:  opStr = "<>";
		case NE:  opStr = "=";
		case LT:  opStr = ">";
		case GE:  opStr = "<";
		case GT:  opStr = "<=";
		case LE:  opStr = ">";
		case ULT: opStr = "UGE";
		case UGE: opStr = "ULT";
		case UGT: opStr = "ULe";;
		case ULE: opStr = "UGT";;
		}

		String lbls = "- True: " + iftrue + " False:" + iffalse;
		return retStr + opStr + ")" +lbls;
	}

	public AssemExp getLeft() {
		return left;
	}

	public AssemExp getRight() {
		return right;
	}

	public AssemStm build(AssemExpList list)
	{
		return new AssemCJump(relop, list.getHead(),list.getTail().getHead(),iftrue,iffalse);
	}

	public AssemStm canonize() {
		AssemStm retStm = null;

		AssemSeqExp seLeft = left.canonize();
		AssemSeqExp seRight = right.canonize();

		AssemStm seLeftStm = seLeft.getStm();
		AssemStm seRightStm = seRight.getStm();

		AssemExp seLeftExp = seLeft.getExp();
		AssemExp seRightExp = seRight.getExp();

		if(commutes(seRightStm, seLeftExp)) {
			// Order does not matter
			AssemStm stm = null;
			if(seLeftStm == null) {
				if(seRightStm != null) stm = seRightStm;
			}
			else {
				if(seRight == null) stm = seLeftStm;
				else
				{
					stm = new AssemSeq(seLeftStm, seRightStm);
				}
			}

			retStm = new AssemSeq(stm, 
					new AssemCJump(relop, seLeftExp, seRightExp, iftrue, iffalse));
		}
		else {
			// Order matters; the left expression must be evaluated before the right statement
			Temp temp = new Temp();
			AssemTemp assemTemp = new AssemTemp(temp, seLeftExp.getValueProperties());
			AssemStm stm = null;
			AssemMove moveStm = new AssemMove(assemTemp,seLeftExp );

			if(seLeftStm == null) {
				if(seRightStm != null) 
				{
					stm = new AssemSeq(moveStm, seRightStm);
				}
			}
			else {
				if(seRightStm == null)  {
					stm = new AssemSeq(seLeftStm, moveStm);
				}
				else {
					stm = new AssemSeq(seLeftStm, new AssemSeq(moveStm, seRightStm));
				}
			}
			retStm = new AssemSeq(stm, 
					new AssemCJump(relop, assemTemp, seRightExp,iftrue, iffalse));
		}
		return retStm;
	}

	public boolean commutes(AssemStm stm, AssemExp exp)
	{
		return (stm == null) || (exp instanceof AssemName) || (exp instanceof AssemConst);
	}

	public int getStmType()
	{
		return AssemStm.CJUMP;
	}

	public void setLeft(AssemExp left) {
		this.left = left;
	}

	public void setRight(AssemExp right) {
		this.right = right;
	}
}
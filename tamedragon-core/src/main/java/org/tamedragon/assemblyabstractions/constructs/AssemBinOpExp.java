package org.tamedragon.assemblyabstractions.constructs;

import java.util.Stack;

import org.tamedragon.common.Label;
import org.tamedragon.common.llvmir.types.Temp;

public class AssemBinOpExp extends AssemExp
{	
	private int op;
	private AssemExp left, right;

	public final static int PLUS =0;
	public final static int MINUS=1;
	public final static int MUL=2;
	public final static int DIV=3;	
	public final static int MODULO =4;

	// Bitwise operators
	public final static int BITWISE_AND=11;
	public final static int BITWISE_OR=12;	
	public final static int BITWISE_XOR=13;
	public final static int LSHIFT =14;
	public final static int RSHIFT= 15;
	public final static int ARSHIFT =16;

	public AssemBinOpExp(int b, AssemExp l, AssemExp r) {
		op = b;
		left = l; 
		right = r; 
	}

	public AssemExpList children() {
		return new AssemExpList(left, new AssemExpList(right,null));
	}

	public int getOperator() {
		return op;
	}

	public AssemType translateToCJump(Label labelTrue, Label labelFalse, boolean isNegation) {
		AssemType returnAssemType = null;

		// Move this value to a temporary
		Temp temp = new Temp();
		AssemValueProperties assemValueProperties = getValueProperties();
		AssemTemp assemTemp = new AssemTemp(temp, assemValueProperties);
		AssemMove assemMov = new AssemMove(assemTemp, this);

		// Create a constant 0
		AssemConst assemConst0 = new AssemConst(0);

		// Create a conditional based on whether the above temporary is greater than zero
		AssemCJump tempCJump = null;
		if(!isNegation)   // Check if temp is greater than zero
			tempCJump = new AssemCJump(AssemCJump.GT, assemTemp, assemConst0,
					labelTrue, labelFalse);
		else             // Check if temp is lesser than or equal to zero
			tempCJump = new AssemCJump(AssemCJump.LTE, assemTemp, assemConst0,
					labelTrue, labelFalse);

		Stack<AssemStm> seqStack = new Stack<AssemStm>();
		seqStack.push(assemMov);
		seqStack.push(tempCJump);

		returnAssemType = translateSeqStatement(seqStack);		


		return returnAssemType;
	}

	/**
	 * This function will coerce this binary operator expression into an int type,
	 * by creating a temporary holding an int value
	 */
	public AssemType translateToIntType() {
		AssemType retAssemType = null;

		Temp temp = new Temp();
		AssemTemp assemTemp = new AssemTemp(temp, getValueProperties());
		
		AssemMove assemMove = new AssemMove(assemTemp, this);

		retAssemType = new AssemSeqExp(assemMove, assemTemp); 

		return retAssemType;
	}

	public int getAssemTypeType() {		
		return VALUE_TYPE;
	}

	public String getDescription() {
		String retStr = "BinOpExp(";
		String opStr = "";
		switch(op) {
		/*case EQ:
			opStr = "=";
			break;
		case NE:
			opStr = "<>";
			break;
		case GT:
			opStr = ">";
			break;
		case LT:
			opStr = "<";
			break;
		case GTE:
			opStr = ">=";
			break;
		case LTE:
			opStr = "<=";
			break; */
		case PLUS:
			opStr = "+";
			break;
		case MINUS:
			opStr = "-";
			break;
		case MUL:
			opStr = "*";
			break;
		case DIV:
			opStr = "/";
			break;
		case BITWISE_AND:
			opStr = "&";
			break;
		case BITWISE_OR:
			opStr = "|";
			break;
		case LSHIFT:
			opStr = "LSHIFT";
			break;
		case RSHIFT:
			opStr = "RSHIFT";
			break;
		case ARSHIFT:
			opStr = "ARSHIFT";
			break;
		case BITWISE_XOR:
			opStr = "XOR";
			break;	
		}

		return retStr + opStr + ")";
	}

	public AssemExp getLeft() {
		return left;
	}

	public AssemExp getRight() {
		return right;
	}

	/*private int getCJumpOperator(int op) {
		if(op == EQ) return AssemCJump.EQ;
		else if(op == NE) return AssemCJump.NE;
		else if(op == GT) return AssemCJump.GT;
		else if(op == LT) return AssemCJump.LT;
		else if(op == LTE) return AssemCJump.LE;
		else if(op == GTE) return AssemCJump.GE;
		return -1;
	}*/

	public AssemSeq translateSeqStatement(Stack<AssemStm> seq) {
		AssemSeq retAssemType = null;

		while(!seq.isEmpty()) {
			AssemType temp = (AssemType)seq.pop();
			// If there is an expression in this sequence, coerce it to a statement,
			// since we are building a list of statements 
			if(temp instanceof AssemExp)
			{
				AssemExp exp = (AssemExp) temp;
				temp = exp.translateToStatement();
			}
			retAssemType  = new AssemSeq((AssemStm)temp, (AssemStm)retAssemType);
		}

		return retAssemType;
	}

	public AssemExp build(AssemExpList list) {
		return new AssemBinOpExp(op, list.getHead(),list.getTail().getHead());
	}

	public AssemSeqExp canonize() {
		AssemSeqExp seLeft = left.canonize();
		AssemSeqExp seRight = right.canonize();	

		AssemStm seLeftStm = seLeft.getStm();
		AssemStm seRightStm = seRight.getStm();

		AssemExp seLeftExp = seLeft.getExp();
		AssemExp seRightExp = seRight.getExp();

		AssemSeqExp retSeqExp = null;
		if(commutes(seRightStm, seLeftExp)) {
			// Order does not matter
			AssemStm stm = null;
			if(seLeftStm == null)
			{
				if(seRightStm != null) stm = seRightStm;
			}
			else
			{
				if(seRight == null) stm = seLeftStm;
				else
				{
					stm = new AssemSeq(seLeftStm, seRightStm);
				}
			}

			retSeqExp = new AssemSeqExp(stm, 
					new AssemBinOpExp(op, seLeftExp, seRightExp));
		}
		else {
			// Order matters; the left expression must be evaluated before the right statement
			Temp temp = new Temp();
			AssemTemp assemTemp = new AssemTemp(temp, getValueProperties());
			AssemStm stm = null;
			AssemMove moveStm = new AssemMove(assemTemp,seLeftExp );

			if(seLeftStm == null) {
				if(seRightStm != null) 
				{
					stm = new AssemSeq(moveStm, seRightStm);
					retSeqExp = new AssemSeqExp(stm, 
							new AssemBinOpExp(op, assemTemp, seRightExp));
				}
			}
			else  {
				if(seRightStm == null) {
					stm = new AssemSeq(seLeftStm, moveStm);
				}
				else {
					stm = new AssemSeq(seLeftStm, new AssemSeq(moveStm, seRightStm));
					retSeqExp = new AssemSeqExp(stm, 
							new AssemBinOpExp(op, assemTemp, seRightExp));
				}
			}
		}
		return retSeqExp;
	}

	public boolean commutes(AssemStm stm, AssemExp exp) {
		return (stm == null) || (exp instanceof AssemName) || (exp instanceof AssemConst);
	}

	public int getExpType() {
		return AssemExp.BIN_OP;
	}

	public void setLeft(AssemExp left) {
		this.left = left;
	}

	public void setRight(AssemExp right) {
		this.right = right;
	}
	
	public AssemValueProperties getValueProperties(){
		AssemValueProperties avpLeft = left.getValueProperties();
		AssemValueProperties avpRight = right.getValueProperties();
		
		// If the size is not the same, take the "wider" one
		if(avpLeft.isWiderThan(avpRight))
			return avpLeft;
		else
			return avpRight;
		
	}
}

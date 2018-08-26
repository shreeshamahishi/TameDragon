package org.tamedragon.assemblyabstractions.constructs;


import org.tamedragon.common.Label;
import org.tamedragon.common.llvmir.types.Temp;

public class AssemMemory extends AssemExp{

	public static final short FRAME_POINTER = 0;
	public static final short STACK_POINTER = 1;
	
	private AssemExp exp;	
	private short base;   // Can be one of frame or stack pointer.
	
	private AssemValueProperties typeOfContainedValue;
	
	public AssemMemory(short base, AssemExp e, AssemValueProperties avp) {
		this.base = base;
		exp = e;
		this.typeOfContainedValue = avp;
	}
	
	public AssemExpList children() {
		return new AssemExpList(exp,null);
	}
	
	public AssemExp getMemExp() {
		return exp;
	}
	
	public void setMemExp(AssemExp exp) {
		this.exp = exp;
		this.typeOfContainedValue = exp.getValueProperties();
	}
	
	public AssemType translateToCJump(Label labelTrue, Label labelFalse, boolean isNegation){
		
		return exp.translateToCJump(labelTrue, labelFalse, isNegation);
	}
	
	/**
	* This function will coerce this binary operator expression into an int type,
	* by creating a temporary holding an int value
	*/
	public AssemType translateToIntType() {
		AssemTemp temp = new AssemTemp(new Temp(), typeOfContainedValue);
		AssemMove move = new AssemMove(temp, this);
		AssemSeqExp seqExp = new AssemSeqExp(move, temp);
		
		return seqExp;
	}
		
	public int getAssemTypeType() {
		return VALUE_TYPE;
	}
	
	public String getDescription() {
		return "Memory";
	}
	
	public AssemExp build(AssemExpList list) {
		return new AssemMemory(base, list.getHead(), typeOfContainedValue);
	}
	
	public AssemSeqExp canonize() {
		AssemSeqExp seMemExp = exp.canonize();

		AssemStm seMemExpStm = seMemExp.getStm();
		AssemExp seMemExpExp = seMemExp.getExp();

		return new AssemSeqExp(seMemExpStm, new AssemMemory(base, seMemExpExp, typeOfContainedValue));	
	}
	
	public int getExpType() {
		return AssemExp.MEM;
	}

	public AssemValueProperties getValueProperties() {
		return typeOfContainedValue;
	}
	
	public short getBase() {
		return base;
	}

	public void setBase(short base) {
		this.base = base;
	}
}

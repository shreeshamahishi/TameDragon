package org.tamedragon.assemblyabstractions.constructs;

/*
 * Represents an argument passed to a function
 */

public class AssemArgPassStm extends AssemStm{

	private AssemExp exp;
	private int index;
	
	public AssemArgPassStm(AssemExp exp, int index){
		this.exp = exp;
		this.index = index;
	}
	
	@Override
	public String getDescription() {
		String desc = "ArgPass";
		if(exp == null)
			desc += "(void)";
		else
			desc += "(" + exp.getDescription() + ")";
		return desc;
	}

	@Override
	public AssemStm build(AssemExpList list) {
		return new AssemArgPassStm(list.getHead(), index);
	}

	@Override
	public AssemExpList children() {
		return new AssemExpList(exp,null);
	}

	@Override
	public AssemStm canonize() {
		AssemSeqExp seqExp = exp.canonize();
		return new AssemSeq(seqExp.getStm(), new AssemArgPassStm(seqExp.getExp(), index));
	}

	@Override
	public int getStmType() {
		return AssemStm.ARG_PASS_STM;
	}
	
	public AssemExp getExp() {
		return exp;
	}

	public void setExp(AssemExp exp) {
		this.exp = exp;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}

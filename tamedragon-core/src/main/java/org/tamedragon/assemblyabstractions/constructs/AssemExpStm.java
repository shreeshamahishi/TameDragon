package org.tamedragon.assemblyabstractions.constructs;

/**
 * This is an expression expressed as a statement
 * @author shreesha123
 *
 */
public class AssemExpStm extends AssemStm
{
	private AssemExp exp;    
	
	public AssemExpStm(AssemExp exp) {
		this.exp = exp;
	}
	
	public AssemExp getExp() {
		return exp;
	}
	
	public String getDescription() {
		return "ExpStm";
	}
	
	public AssemExpList children()  {
		return new AssemExpList(exp,null);
	}
	
	public AssemStm build(AssemExpList list) {
		return new AssemExpStm(list.getHead());
	}
	
	public AssemStm canonize() {
		AssemSeqExp seqExp = exp.canonize();
		return new AssemSeq(seqExp.getStm(), new AssemExpStm(seqExp.getExp()));
	}
	
	public int getStmType() {
		return AssemStm.EXP_STM;
	}

	public void setExp(AssemExp exp) {
		this.exp = exp;
	}
}

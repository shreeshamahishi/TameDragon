package org.tamedragon.assemblyabstractions.constructs;


public class AssemReturnStm extends AssemStm
{
	private AssemExp returnExpr;
	
	public AssemExp getReturnExpr() {
		return returnExpr;
	}

	public void setReturnExpr(AssemExp returnExpr) {
		this.returnExpr = returnExpr;
	}

	public AssemReturnStm(AssemExp retExp) {
		returnExpr = retExp;
	}

	@Override
	public String getDescription() {
		String retString  = "return ";
		if(returnExpr == null)
			retString += "void";
		else
			retString += returnExpr.getDescription();
		
		return retString;
	}

	//@Override
	public AssemStm build(AssemExpList list) {
		return new AssemReturnStm(list.getHead());
	}

	@Override
	public AssemExpList children() {
		if (returnExpr != null)
		   return new AssemExpList(returnExpr,null);
		
		return null;
	}

	@Override
	public AssemStm canonize() {	
		if(returnExpr == null)
			return this;
		
		AssemSeqExp seqExp = returnExpr.canonize();
		return new AssemSeq(seqExp.getStm(), new AssemReturnStm(seqExp.getExp()));
	}

	@Override
	public int getStmType() {
		return AssemStm.RETURN;
	}
	
}
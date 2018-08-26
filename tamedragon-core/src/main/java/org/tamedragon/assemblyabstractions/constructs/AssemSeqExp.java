package org.tamedragon.assemblyabstractions.constructs;

import org.tamedragon.common.Label;

/**
 * This class is an abstraction of a list of arbitrary statements followed by an expression that
 * evaluates to a value.
 *
 * @author shreesha123
 *
 */

public class AssemSeqExp extends AssemExp {
	private AssemStm stm;
	private AssemExp exp;
	
	public AssemSeqExp(AssemStm s, AssemExp e)  {
		stm = s;
		exp = e;
	}

	public AssemStm getStm() {
		return stm;
	}
	
	public AssemExp getExp() {
		return exp;
	}
	
	public AssemType translateToCJump(Label testLabel, Label endwhileLabel, boolean isNegation) {
		AssemStm cjmp =  (AssemStm) exp.translateToCJump(testLabel, endwhileLabel, isNegation);
		return new AssemSeq(stm, cjmp);
		
		// return new MipsAssemSeq(stm, (MipsAssemStm)exp.translateToCJump(testLabel, endwhileLabel));
	}
	
	public AssemType translateToIntType() {
		return new AssemSeqExp(stm, (AssemExp) exp.translateToIntType());
	}
	
	public int getAssemTypeType() {
		return exp.getAssemTypeType();
	}
	
	public String getDescription() {
		return "StmSeqAndExp";
	}
	
	public AssemExpList children() {
		return null;
	}
	
	public AssemExp build(AssemExpList list) {
		return null;
	}
	
	public AssemSeqExp canonize(){
		if(stm == null) return exp.canonize();
		
		AssemStm memStm = stm.canonize();
		AssemSeqExp memExpSe = exp.canonize();
		
		AssemStm stmFromExp = memExpSe.getStm();
		
		AssemStm stm = null;
		if(memStm == null){
			if(stmFromExp != null) stm = stmFromExp;
		}
		else {
			if(stmFromExp == null) stm = memStm;
			else
			{
				stm = new AssemSeq(memStm, stmFromExp);
			}
		}
		
		return new AssemSeqExp(stm, memExpSe.getExp());
	}
	
	public int getExpType() {
		return AssemExp.SEQ_EXP;
	}

	public void setStm(AssemStm stm) {
		this.stm = stm;
	}

	public void setExp(AssemExp exp) {
		this.exp = exp;
	}
	
	public AssemValueProperties getValueProperties(){
		return exp.getValueProperties();
	}
	
}

package org.tamedragon.assemblyabstractions.constructs;

import org.tamedragon.common.Label;

public class AssemJump extends AssemStm {
	private AssemExp exp;
	private Label targetLabel;
	
	public AssemJump(AssemExp e){
		exp = e; 
		if(e instanceof AssemName){
			AssemName nm = (AssemName)e;
			targetLabel = nm.getLabel();
		}
	}
	
	public AssemExpList children() {
		return new AssemExpList(exp,null);
	}
	
	public AssemExp getLabelExp(){
		return exp;
	}
	
	public void setLabelExp(AssemExp exp){
		this.exp = exp;
	}
	
	public String getDescription(){
		return "Jump";
	}
	
	public AssemStm build(AssemExpList list){
		return new AssemJump(list.getHead());
	}
	
	public AssemStm canonize(){
		AssemSeqExp seqExp = exp.canonize();
		return new AssemSeq(seqExp.getStm(), new AssemJump(seqExp.getExp()));
	}
	
	public Label getLabel(){
		return targetLabel;
	}
	
	public int getStmType(){
		return AssemStm.JUMP;
	}
}

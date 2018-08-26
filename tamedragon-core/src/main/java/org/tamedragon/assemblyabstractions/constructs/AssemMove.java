package org.tamedragon.assemblyabstractions.constructs;

import java.util.Vector;

import org.tamedragon.common.llvmir.types.Temp;

public class AssemMove extends AssemStm
{
	private AssemExp dst, src;
	
	public AssemMove(AssemExp d, AssemExp s) {
		dst = d;
		src = s;
	}
	  
	public AssemExpList children() {
		if (dst instanceof AssemMemory)
		   return new AssemExpList(((AssemMemory)dst).getMemExp(), new AssemExpList(src,null));
		else return new AssemExpList(src,null);
	}

	public String getDescription() {
		return "Move";
	}

	public AssemExp getDst() {
		return dst;
	}

	public AssemExp getSrc() {
		return src;
	}
	
	public AssemStm build(AssemExpList list) {
		if (dst instanceof AssemMemory){
			AssemMemory assemMem = (AssemMemory) dst;
			short base = assemMem.getBase();
			AssemValueProperties avpOfContainedValue = assemMem.getValueProperties();
			return new AssemMove(new AssemMemory(base, list.getHead(), avpOfContainedValue), 
					list.getTail().getHead());
		}
		else 
			return new AssemMove(dst, list.getHead());
	}
	
	public AssemStm canonize() {		
		AssemStm retStm = null;
		
		AssemSeqExp destSeqExp = dst.canonize();
		AssemSeqExp srcSeqExp = src.canonize();
		
		AssemStm destSeqExpStm = destSeqExp.getStm();
		AssemStm srcSeqExpStm = srcSeqExp.getStm();

		AssemExp destSeqExpExp = destSeqExp.getExp();
				
		AssemStm stm = null;
		if(destSeqExpStm == null) {    // The destination sub-tree does not have a statement			
			if(destSeqExpExp instanceof AssemMemory) {   // The destination sub-tree exp is a memory location				
				AssemMemory mem = (AssemMemory) destSeqExpExp;
				Temp temp = new Temp();
				AssemTemp assemTemp = new AssemTemp(temp, mem.getMemExp().getValueProperties());
				AssemMove newMoveStm = new AssemMove(assemTemp, mem.getMemExp());
	
				if(srcSeqExpStm == null)
					stm = newMoveStm;
				else
					stm = new AssemSeq(newMoveStm, srcSeqExpStm);
				
				short base = mem.getBase(); 
				AssemValueProperties avpOfContainedValue = mem.getValueProperties();
				retStm = new AssemSeq(stm,
						new AssemMove(new AssemMemory(base, assemTemp, avpOfContainedValue), srcSeqExp.getExp()));
			}
			else {    // The destination sub-tree is not a memory location
				if(srcSeqExpStm != null){
					stm = srcSeqExpStm;
					retStm = new AssemSeq(stm, new AssemMove(destSeqExpExp, srcSeqExp.getExp()));
				}
				else{
					retStm = new AssemMove(destSeqExpExp, srcSeqExp.getExp());
				}
			}
		}
		else {                      // The destination sub-tree has a statement
			if(destSeqExpExp instanceof AssemMemory) {
				
				AssemMemory mem = (AssemMemory) destSeqExpExp;
				Temp temp = new Temp();
				AssemTemp assemTemp = new AssemTemp(temp, mem.getMemExp().getValueProperties());
				AssemMove newMoveStm = new AssemMove(assemTemp, mem.getMemExp());
				AssemSeq tempSeq = new AssemSeq(destSeqExpStm, newMoveStm);
				stm = new AssemSeq(tempSeq, srcSeqExpStm);
				short base = mem.getBase();
				AssemValueProperties avpOfContainedValue = mem.getValueProperties();
				retStm = new AssemSeq(stm,
						new AssemMove(new AssemMemory(base, assemTemp, avpOfContainedValue), srcSeqExp.getExp()));
				
			}
			else {
				
				if(srcSeqExpStm == null) {//stm = new MipsAssemSeq(destSeqExpStm, newMoveStm);
					retStm = destSeqExpStm;
				}
				else {
					retStm = new AssemSeq(destSeqExpStm, srcSeqExpStm);
				}
			}
		}
		
		return retStm;
	}
	
	public int getStmType() {
		return AssemStm.MOVE;
	}
	
	public Vector getChildren() {
		Vector ret = new Vector();
		ret.addElement(dst);
		ret.add(src);
		return ret;
	}

	public void setDst(AssemExp dst) {
		this.dst = dst;
	}

	public void setSrc(AssemExp src) {
		this.src = src;
	}
}

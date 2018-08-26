package org.tamedragon.assemblyabstractions.constructs;

/**
 * This class is an abstraction of two statements, one followed by the other. Since it does 
 * not evaluate to any value, it inherits from MipsAssemStm.
 * 
 * @author shreesha123
 *
 */
public class AssemSeq extends AssemStm{
	
	private AssemStm left, right;
	
	public AssemSeq(AssemStm l, AssemStm r) {
		left = l;
		right = r;
	}
	
	public AssemStm getLeftStm() {
		return left;
	}
	
	public void setLeftStm(AssemStm stm) {
		left = stm;
	}
	
	public AssemStm getRightStm() {
		return right;
	}
	
	public void setRightStm(AssemStm stm) {
		right = stm;
	}
	
	
	public String getDescription() {
		return "StmSequence";
	}
	
	public AssemExpList children()  {
		return null;
	}
	
	public AssemStm build(AssemExpList list) {
		return null;
	}
	
	public AssemStm canonize() {
		AssemStm rightStm = null;
		AssemStm leftStm = left.canonize();
		if(right != null)
			rightStm = right.canonize();
		
		return new AssemSeq(leftStm, rightStm);
	}
	
	public int getStmType() {
		return AssemStm.SEQ;
	}
}

package org.tamedragon.common.llvmir.instructions;

import java.util.Vector;

import org.tamedragon.common.EnvironmentConstants;
import org.tamedragon.common.llvmir.types.Operand;
import org.tamedragon.common.llvmir.types.Temp;

public class ConditionalFlagSet implements AssemblyInstruction {

	private Temp flagRegister;       // The register that will get set to true when the condition is true
	private Vector<Operand> srcList; // The list containing the operands on which the operator is applied
	private int relop;               // The conditional operator (one of the operators defined in CJump)
	
	public ConditionalFlagSet(Vector<Operand> srcList, int relop,
			 Temp flagRegister){
	this.srcList = srcList;
	this.relop = relop;
	this.flagRegister = flagRegister;
}
	
	@Override
	public Vector<Temp> getDestList() {
		return null;
	}

	@Override
	public Vector<Operand> getSrcList() {
		return srcList;
	}

	@Override
	public Vector getJumps() {
		return null;
	}

	@Override
	public String getLabelStr() {
		return null;
	}

	@Override
	public boolean isMove() {
		return false;
	}

	@Override
	public int getType() {
		return CONDITIONAL_FLAG_SET; 
	}

	@Override
	public void updateSourceList(int index, Operand operand) {
		if(index < srcList.size()){
			srcList.set(index, operand);
		}
	}
	
	public int getRelOp(){ return relop;}
	
	public Temp getFlagRegister() {
		return flagRegister;
	}

	public void setFlagRegister(Temp flagRegister) {
		this.flagRegister = flagRegister;
	}

	public String toString(){
		String desc = "";
		
		String src1 = srcList.get(0).toString();
		String src2 = srcList.get(1).toString();
		
		String opDesc = "";
		
		if(relop == CJump.EQ) 
			opDesc = "c.eq";
		else if(relop == CJump.NE)
			opDesc = "c.ne";
		else if(relop == CJump.GT)
			opDesc = "c.gt";
		else if(relop == CJump.GE)
			opDesc = "c.ge";
		else if(relop == CJump.LT)
			opDesc = "c.lt";
		else 
			opDesc = "c.le";
		
		desc = EnvironmentConstants.TAB + opDesc + " " + src1.toString() + ", " 
				+ src2 + EnvironmentConstants.NEWLINE;
		
		return desc;
	}
}

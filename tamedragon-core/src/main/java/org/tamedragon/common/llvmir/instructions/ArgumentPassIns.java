package org.tamedragon.common.llvmir.instructions;

import java.util.Vector;

import org.tamedragon.common.EnvironmentConstants;
import org.tamedragon.common.llvmir.types.Operand;
import org.tamedragon.common.llvmir.types.Temp;

public class ArgumentPassIns  implements AssemblyInstruction{
	
	private Temp temp;
	
	private Vector<Temp> destList;
	private Vector<Operand> srcList;
	
	public ArgumentPassIns(Temp temp){
		this.temp = temp;
		srcList = new Vector<Operand>();
		srcList.add(temp);
	}

	public Temp getTemp() {
		return temp;
	}

	public void setTemp(Temp temp) {
		this.temp = temp;
	}

	@Override
	public Vector<Temp> getDestList() {
		 return destList;
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
		return AssemblyInstruction.ARG_PASS;
	}

	@Override
	public void updateSourceList(int operandIndex, Operand operand) {
		if(srcList == null){
			// Should not happen
			return;
		}
		if(operandIndex < srcList.size()){
			srcList.set(operandIndex, operand);
		}
	}
	
	public String toString(){
		String desc = "";
		if(srcList != null && srcList.size() > 0){
			for(Operand opr: srcList){
				desc += opr.toString();
			}
		}
		
		return EnvironmentConstants.TAB + "pass " + desc + EnvironmentConstants.NEWLINE;
	}
}

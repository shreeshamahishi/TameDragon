package org.tamedragon.common.llvmir.instructions;

import java.util.Vector;

import org.tamedragon.common.EnvironmentConstants;
import org.tamedragon.common.TargetMachine;
import org.tamedragon.common.llvmir.types.Operand;
import org.tamedragon.common.llvmir.types.Temp;

public class Return implements AssemblyInstruction
{
	private Vector<Operand> operands;
	
	public Vector<Operand> getOperands() {
		return operands;
	}

	public void setOperands(Vector<Operand> operands) {
		this.operands = operands;
	}

	public Return(Vector<Operand> oprs){
		this.operands = oprs;
	}
	
	public int getType(){
		return AssemblyInstruction.RETURN;
	}

	@Override
	public Vector<Temp> getDestList() {
		return null;
	}

	@Override
	public Vector<Operand> getSrcList(){
		return operands;
	}

	@Override
	public Vector getJumps() {
		return null;
	}

	@Override
	public String getLabelStr(){
		return null;
	}

	@Override
	public boolean isMove() {
		return false;
	}

	@Override
	public void updateSourceList(int operandIndex, Operand operand) {
		if(operands == null){
			// Should not happen
			return;
		}
		if(operandIndex < operands.size()){
			operands.set(operandIndex, operand);
		}
	}
	
	public String toString(){
		String desc = "void";
		if(operands != null && operands.size() > 0){
			desc = "";
			for(Operand opr: operands){
				desc += opr.toString() + ",";
			}
		}
		return EnvironmentConstants.TAB + "return " + desc + EnvironmentConstants.NEWLINE;
	}
}

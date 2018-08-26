package org.tamedragon.common.llvmir.instructions;

import java.util.Vector;

import org.tamedragon.common.EnvironmentConstants;
import org.tamedragon.common.TargetMachine;
import org.tamedragon.common.llvmir.types.Operand;
import org.tamedragon.common.llvmir.types.Temp;

public class LabelInstr implements AssemblyInstruction
{
	private String lblStr;
	
	public LabelInstr(String str){
		lblStr = str;
	}
	
	public Vector<Operand> getSrcList(){
		return null;
	}
	
	public Vector<Temp> getDestList(){
		return null;
	}
	
	public Vector getJumps(){
		return null;
	}
	
	public int getType(){
		return AssemblyInstruction.LABEL;
	}
	
	public String getLabelStr(){
		return lblStr;
	}
	
	public boolean isMove(){
		return false;
	}
	
	public String toString(){		
		return lblStr + ":" + EnvironmentConstants.NEWLINE;
	}
	
	public void updateSourceList(int index, Operand operand){
		return;   // Nothing to do
	}
}

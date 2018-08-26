package org.tamedragon.common.llvmir.instructions;

import java.util.Vector;

import org.tamedragon.common.EnvironmentConstants;
import org.tamedragon.common.TargetMachine;
import org.tamedragon.common.llvmir.types.Operand;
import org.tamedragon.common.llvmir.types.Temp;

public class Jump implements AssemblyInstruction
{
	private String label;
	
	public Jump(String lbl){
		this.label = lbl;
	}
	
	public String getLabel(){
		return label;
	}
	
	public int getType(){
		return AssemblyInstruction.JUMP;
	}
	
	public Vector<Operand> getSrcList(){
		return null;
	}
	
	public Vector<Temp> getJumps(){
		Vector jmpsVector = new Vector();
		jmpsVector.addElement(label);
		return jmpsVector;
	}
	
	public String getLabelStr(){
		return null;
	}
	
	public Vector getDestList(){
		return null;
	}
	
	public boolean isMove(){
		return false;
	}
	
	public String generateInstructionStringForJump(Jump jump){		
		return EnvironmentConstants.TAB + "j " + jump.getLabel() + EnvironmentConstants.NEWLINE;
	}
	
	public String toString(){
		return EnvironmentConstants.TAB + "j " + label + EnvironmentConstants.NEWLINE;
	}
	
	public void updateSourceList(int index, Operand operand){
		return;   // Nothing to do
	}
}

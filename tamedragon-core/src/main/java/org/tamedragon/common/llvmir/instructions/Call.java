package org.tamedragon.common.llvmir.instructions;

import java.util.Vector;

import org.tamedragon.common.EnvironmentConstants;
import org.tamedragon.common.llvmir.types.Operand;
import org.tamedragon.common.llvmir.types.Temp;

public class Call implements AssemblyInstruction
{
	private String functionName;
	private Vector<Temp> destList;
	private Vector<Operand> srcList;
	
	public Call(String name, Vector<Temp> destList, Vector<Operand> srcList){
		functionName = name;
		this.destList = destList;
		this.srcList = srcList;
	}

	public Vector<Operand> getSrcList(){
		return srcList;
	}
	
	public Vector<Temp> getDestList(){
		return destList;
	}
	
	public Vector getJumps(){
		return null;
	}
	
	public int getType(){
		return AssemblyInstruction.CALL;
	}
	
	public boolean isMove(){
		return false;
	}
	
	public String getLabelStr(){
		return null;
	}
	
	public String getFunctionName(){
		return functionName;
	}
	
	public String toString(){

		// Description for return value
		String destStr = "void: ";
		if(destList != null && destList.size() > 0){
			destStr = "";
			int count = 0;
			for(Temp destTemp : destList){
				destStr += destTemp.toString() + (count == destList.size() -1 ? "" : ", ");
				count++;
			}
			destStr += ": ";
		}
		
		// Description for arguments
		String argsStr = "void";
		if(srcList != null && srcList.size() > 0){
			argsStr = "";
			int count = 0;
			for(Operand arg : srcList){
				argsStr += arg.toString() + (count == srcList.size() -1 ? "" : ", ");
				count++;
			}
		}
		
		String returnAndArgs = "(" + destStr + argsStr + ")";
		String signature = functionName + returnAndArgs;
		
		return EnvironmentConstants.TAB + "call " + signature + EnvironmentConstants.NEWLINE;
	}
	
	public void updateSourceList(int index, Operand operand){
		if(index < srcList.size()){
			srcList.set(index, operand);
		}
	}
}

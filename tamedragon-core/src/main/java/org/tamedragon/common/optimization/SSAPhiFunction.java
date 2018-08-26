package org.tamedragon.common.optimization;

import java.util.Vector;
import java.util.HashMap;
import java.util.Stack;





import org.tamedragon.common.EnvironmentConstants;
import org.tamedragon.common.llvmir.instructions.AssemblyInstruction;
import org.tamedragon.common.llvmir.types.Numeric;
import org.tamedragon.common.llvmir.types.Operand;
import org.tamedragon.common.llvmir.types.Temp;

public class SSAPhiFunction implements AssemblyInstruction{
	private Vector destList;
	private Vector srcList;
	
	private String instructionDescription;
	
	private boolean isMoveStm;
	
	private int numParams;
	private Temp destTemp;
	
	//private int currentParamNumberToBeRenamed;   // Represents the curren parameter index that should be renamed
												 // by the renaming alogrithm
	
	public SSAPhiFunction(){
		srcList = new Vector();
		destList = new Vector();
	}
	
	/*
	 * This constructor is called when a phi function is first created; in this case,
	 * the destination (the left-hand side of the phi function) is the same as those
	 * on right side (the right-hand side variables). For example, this would result
	 * in phi functions that look like this:
	 * 
	 * $t0 <- phi($t0, $t0);
	 * 
	 */
	
	public SSAPhiFunction(Temp temp, int numPredecessors) {
		srcList = new Vector();
		destList = new Vector();
		numParams = numPredecessors;
		destTemp = temp;
		
		for(int i = 0; i < numPredecessors; i++){
			srcList.addElement(temp);
		}
		
		updateInstructionString();
	
		destList.addElement(temp);
		isMoveStm = true;
	}
	
	/*
	 * This constructor is called when a phi function already exists and we need to create
	 * a new phi function from that phi function. This will therefore need the new destination
	 * variable (the first parameter to the constructor) and the source variables from the old
	 * phi function (the second parameter to the construtor).
	 */
	
	//public SSAPhiFunction(Temp temp, Vector srcList, int currentParamNum) {
	public SSAPhiFunction(Temp temp, Vector srcList) {
		this.srcList = srcList;
		destList = new Vector();
		numParams = srcList.size();
		destTemp = temp;
		
		updateInstructionString();
	
		destList.addElement(temp);
		isMoveStm = true;
	}
	
	public int getNumParams(){
		return numParams;
	}
	
	public void updateInstructionString(){
		String paramStr = "";
		String destTempName = destTemp.toString();
		for(int i = 0; i < numParams; i++){
			Operand opr = (Operand) srcList.elementAt(i);
			if(opr.getOperandType() == Operand.TEMP_TYPE){
				Temp temp = (Temp) srcList.elementAt(i);
				if(i < numParams -1) paramStr += temp + ", ";
				else paramStr += temp;
			}
			else{
				Numeric nm = (Numeric) srcList.elementAt(i);
				if(i < numParams -1) paramStr += nm + ", ";
				else paramStr += nm;
			}
		}
		
		instructionDescription = EnvironmentConstants.TAB + destTempName 
		+ " <- phi(" + paramStr + ")" + EnvironmentConstants.NEWLINE; 
	}
	
	public Temp updateParamName(HashMap oldNameVsNewSSATemp, int paramNum) {
		Temp oldTemp = (Temp) srcList.elementAt(paramNum);
		Stack stack = (Stack) oldNameVsNewSSATemp.get(oldTemp);
		SSATemp newTemp = null;
		
		if(stack != null){
			Integer countInt = (Integer) stack.lastElement();
			int count = countInt.intValue();
			newTemp = new SSATemp(oldTemp, count);
		}
		if(newTemp != null) 
			srcList.set(paramNum, newTemp);
		
		updateInstructionString();
		
		return newTemp;
	}
	
	public void removeParameter(int index){
		srcList.remove(index);
		numParams = srcList.size();
		updateInstructionString();
		numParams = srcList.size();
	}
	
	/**
	 * Updates the source list with the operand at the given index
	 * @param index
	 * @param operand
	 */
	public void updateSourceList(int index, Operand operand){
		if(index < srcList.size()){
			srcList.set(index, operand);
		}
	}
	
	public String toString() {
		updateInstructionString();
		return instructionDescription;
	}
	
	public Vector getSrcList() {
		return srcList;
	}
	
	public Vector getDestList(){
		return destList;
	}
	
	public Vector getJumps(){
		return null;
	}
	
	public String getLabelStr(){
		return null;
	}
	
	public int getType()
	{
		return AssemblyInstruction.SSA_PHI_FUNCTION;
	}
	
	public boolean isMove(){
		return isMoveStm;
	}

	public Temp getDestTemp() {
		return destTemp;
	}

	/*public int getCurrentParamNumberToBeRenamed() {
		return currentParamNumberToBeRenamed;
	}
	*/
}

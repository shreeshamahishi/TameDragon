package org.tamedragon.common.llvmir.instructions;

import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tamedragon.common.EnvironmentConstants;
import org.tamedragon.common.llvmir.types.Numeric;
import org.tamedragon.common.llvmir.types.Operand;
import org.tamedragon.common.llvmir.types.Temp;

public class Operation implements AssemblyInstruction
{
	public static final int ADD = 0;
	public static final int MINUS = 1;
	public static final int MUL = 2;
	public static final int DIV = 3;
	public static final int LOAD_IMMEDIATE_VALUE = 4;
	public static final int STORE_WORD_INTO_ADDRESS_AT_TEMP = 5;
	public static final int STORE_WORD_INTO_CONST_ADDRESS = 6;
	public static final int MOVE_TEMP_TO_TEMP = 7;
	public static final int LOAD_ADDRESS = 8;
	public static final int LOAD_FROM_TEMP_OFFSET_EXP_MEM = 9;
	
	public static final int MODULO = 10;
	public static final int BITWISE_AND = 11;
	public static final int BITWISE_OR = 12;
	public static final int BITWISE_XOR = 13;
	public static final int LEFT_SHIFT = 14;
	public static final int RIGHT_SHIFT = 15;
	
	public static final int ONES_COMPLEMENT = 16;
	
	private Vector<Operand> srcList;
	private Vector<Temp> destList;
	
	private static final Logger LOG = LoggerFactory.getLogger(Operation.class);
	private static final String INVALID_OPERAND_TYPE = "Invalid type for operand";
	
	private Numeric immediateValue;
	private int operationCode;
	private String label;
	
	public Operation(){
		srcList = new Vector<Operand>();
		destList = new Vector<Temp>();
	}
	
	public Operation(Vector<Temp> dest, Vector<Operand> src, int operationCode, Numeric immediateValue,
					 String label) {
		destList = dest;
		srcList = src;
		this.operationCode = operationCode;
		this.immediateValue = immediateValue;
		this.label = label;				
	}
	
	
	public void setDefsAndSources(Vector<Temp> dest, Vector<Operand> src){
		this.destList = dest;
		this.srcList = src;
	}
	
	public void updateSourceList(int index, Operand operand){
		if(index < srcList.size()){
			srcList.set(index, operand);
		}
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
	
	public String getLabelStr(){
		return null;
	}
	
	public int getType(){
		return AssemblyInstruction.INSTRUCTION;
	}
	
	public boolean isMove(){
		return (operationCode == MOVE_TEMP_TO_TEMP);
	}
	
	public String getLabel(){
		return label;
	}
	
	public int getOperationCode(){
		return operationCode;
	}
	
	public Numeric getImmediateValue(){
		return immediateValue;
	}
	
	public String toString(){
		
		String instructionDescription = "";

		Temp destTemp = null; // Only one destination
		if(destList != null){
			destTemp = (Temp) destList.elementAt(0); 
		}
		
		int numSrcTemps = 0;
		if(srcList != null){
			numSrcTemps = srcList.size();
		}
		
		if(isArithmeticInstruction()){
			// A mathematical operation; should have one destination and either:
			// two source variables - either two temporaries or a temporary and an immediate value
			// OR 1 source variable that is an operand in a unary operation.

			String lhsOperand = "";
			if(srcList.elementAt(0).getOperandType() == Operand.TEMP_TYPE){
				lhsOperand = ((Temp)srcList.elementAt(0)).toString();
			}
			else{
				lhsOperand = ((Numeric)srcList.elementAt(0)).toString();
			}
			
			String rhsOperand = "";
			if(numSrcTemps == 2)
				if(srcList.elementAt(1).getOperandType() == Operand.TEMP_TYPE)
					rhsOperand = ((Temp)srcList.elementAt(1)).toString();
				else
					rhsOperand = ((Numeric)srcList.elementAt(1)).toString();
			else{
				if(immediateValue == null){
					// Must be unary operation
					if(srcList.elementAt(0).getOperandType() == Operand.TEMP_TYPE)
						rhsOperand = ((Temp)srcList.elementAt(0)).toString();
					else
						rhsOperand = ((Numeric)srcList.elementAt(0)).toString();
				}
				else{
					rhsOperand = immediateValue.toString();
				}
			}
						
			if(operationCode == Operation.ADD)
				instructionDescription = EnvironmentConstants.TAB + "add " + destTemp.toString() + ", " + lhsOperand + ", " 
				+ rhsOperand + EnvironmentConstants.NEWLINE;
			else if(operationCode == Operation.MINUS)
				instructionDescription = EnvironmentConstants.TAB + "sub " + destTemp.toString() + ", " + lhsOperand + ", " 
				+ rhsOperand + EnvironmentConstants.NEWLINE;
			else if(operationCode == Operation.MUL)
				instructionDescription = EnvironmentConstants.TAB + "mul " + destTemp.toString() + ", " + lhsOperand + ", " 
				+ rhsOperand + EnvironmentConstants.NEWLINE;
			else if(operationCode == Operation.DIV)
				instructionDescription = EnvironmentConstants.TAB + "div " + destTemp.toString() + ", " + lhsOperand + ", " 
				+ rhsOperand + EnvironmentConstants.NEWLINE;
			else if(operationCode == Operation.MODULO)
				instructionDescription = EnvironmentConstants.TAB + "rem " + destTemp.toString() + ", " + lhsOperand + ", " 
				+ rhsOperand + EnvironmentConstants.NEWLINE;
			else if(operationCode == Operation.BITWISE_AND)
				instructionDescription = EnvironmentConstants.TAB + "and " + destTemp.toString() + ", " + lhsOperand + ", " 
				+ rhsOperand + EnvironmentConstants.NEWLINE;
			else if(operationCode == Operation.BITWISE_OR)
				instructionDescription = EnvironmentConstants.TAB + "or " + destTemp.toString() + ", " + lhsOperand + ", " 
				+ rhsOperand + EnvironmentConstants.NEWLINE;
			else if(operationCode == Operation.BITWISE_XOR)
				instructionDescription = EnvironmentConstants.TAB + "xor " + destTemp.toString() + ", " + lhsOperand + ", " 
				+ rhsOperand + EnvironmentConstants.NEWLINE;
			else if(operationCode == Operation.LEFT_SHIFT)
				instructionDescription = EnvironmentConstants.TAB + "rol " + destTemp.toString() + ", " + lhsOperand + ", " 
				+ rhsOperand + EnvironmentConstants.NEWLINE;
			else if(operationCode == Operation.RIGHT_SHIFT)
				instructionDescription = EnvironmentConstants.TAB + "ror " + destTemp.toString() + ", " + lhsOperand + ", " 
				+ rhsOperand + EnvironmentConstants.NEWLINE;
			else if(operationCode == Operation.ONES_COMPLEMENT)
				instructionDescription = EnvironmentConstants.TAB + "not " + destTemp.toString() + ", " + lhsOperand + ", " 
				+ rhsOperand + EnvironmentConstants.NEWLINE;
		}
		else if(operationCode == Operation.LOAD_IMMEDIATE_VALUE){
			instructionDescription = EnvironmentConstants.TAB + "li " + destTemp.toString() + ", " + 
			                 immediateValue.toString() + EnvironmentConstants.NEWLINE; 
		}
		else if(operationCode == Operation.STORE_WORD_INTO_ADDRESS_AT_TEMP){
			String srcString = "";
			if(srcList.elementAt(0).getOperandType() == Operand.TEMP_TYPE)
				srcString= ((Temp) srcList.elementAt(0)).toString();
			else
				srcString= ((Numeric) srcList.elementAt(0)).toString();
			
			if(srcList.elementAt(1) instanceof Numeric){
				// The temp representing the address CANNOT be a Numeric
				assert false : INVALID_OPERAND_TYPE;
				LOG.error(INVALID_OPERAND_TYPE);
			}
			
			Temp addrTemp = (Temp) srcList.elementAt(1);
			
			String offSetStr = immediateValue.toString();
			int offSet = Integer.parseInt(offSetStr);
			String destStr = "";
			if(offSet == 0)
				destStr = "(" + addrTemp.toString() + ")" + EnvironmentConstants.NEWLINE;
			else 
				destStr = offSet + "(" + addrTemp.toString() + ")" + EnvironmentConstants.NEWLINE;
			instructionDescription = EnvironmentConstants.TAB + "sw " + srcString + ", " + destStr;
		}
		else if(operationCode == Operation.STORE_WORD_INTO_CONST_ADDRESS){
			String srcString = "";
			if(srcList.elementAt(0).getOperandType() == Operand.TEMP_TYPE)
				srcString = ((Temp) srcList.elementAt(0)).toString();
			else
				srcString = ((Numeric) srcList.elementAt(0)).toString();
			
			// TODO - Check if the following statement is correct instruction
			instructionDescription = EnvironmentConstants.TAB + "sw " + srcString + ", " + immediateValue.toString() + EnvironmentConstants.NEWLINE;
		}
		else if(operationCode == Operation.MOVE_TEMP_TO_TEMP){
			String srcString = "";
			if(srcList.elementAt(0).getOperandType() == Operand.TEMP_TYPE)
				srcString = ((Temp) srcList.elementAt(0)).toString();
			else
				srcString = ((Numeric) srcList.elementAt(0)).toString();
			// TODO - Check if the following statement is correct instruction
			instructionDescription = EnvironmentConstants.TAB + "move " + destTemp.toString() + ", " + srcString 
										+ EnvironmentConstants.NEWLINE;
		}
		else if(operationCode == Operation.LOAD_ADDRESS){
			if(label != null)                        // Load address from label
				instructionDescription = EnvironmentConstants.TAB + "la " + destTemp.toString() + ", " 
				+ label + EnvironmentConstants.NEWLINE;
			else if(immediateValue != null)          // Load address from constant value
				instructionDescription = EnvironmentConstants.TAB + "la " + destTemp.toString() + ", " + immediateValue.toString() + EnvironmentConstants.NEWLINE;
			else{
				// The temp representing the address CANNOT be a Numeric
				Temp srcTemp = (Temp) srcList.elementAt(0);
				instructionDescription = EnvironmentConstants.TAB + "la " + destTemp.toString() + ", " + "(" + srcTemp.toString() + ")" + EnvironmentConstants.NEWLINE;
			}
		}
		else if(operationCode == Operation.LOAD_FROM_TEMP_OFFSET_EXP_MEM){
			String offSetStr = immediateValue.toString();
			int offSet = Integer.parseInt(offSetStr);
			String srcString = "";
			if(srcList.elementAt(0).getOperandType() == Operand.TEMP_TYPE)
				srcString = ((Temp) srcList.elementAt(0)).toString();
			else
				srcString = ((Numeric) srcList.elementAt(0)).toString();
			String signStr = "";
			if(offSet < 0) {
				signStr = "-";
				int val = Math.abs(offSet);
				
				instructionDescription = EnvironmentConstants.TAB + "lw " + destTemp.toString() + ", " +  signStr + val + "("  +
				srcString+ ")" + EnvironmentConstants.NEWLINE;
			}
			else if(offSet > 0){
				instructionDescription = EnvironmentConstants.TAB + "lw " + destTemp.toString() + ", " +  offSet + "("  +
				srcString+ ")" + EnvironmentConstants.NEWLINE;
			}
			else
			{
				instructionDescription = EnvironmentConstants.TAB + "lw " + destTemp.toString() + ", " + "("  +
				srcString+ ")" + EnvironmentConstants.NEWLINE;
			}
		}
		
		return instructionDescription;
	}	
	
	public int getInstructionType(){
		return operationCode;
	}
	
	public boolean isArithmeticInstruction(){
		if(operationCode == Operation.ADD || operationCode == Operation.MINUS || 
				operationCode == Operation.MUL || operationCode == Operation.DIV
				|| operationCode == Operation.MODULO || operationCode == Operation.BITWISE_AND
				|| operationCode == Operation.BITWISE_OR || operationCode == Operation.BITWISE_XOR
				|| operationCode == Operation.LEFT_SHIFT || operationCode == Operation.RIGHT_SHIFT
				|| operationCode == Operation.ONES_COMPLEMENT)
			return true;
		
		return false;
	}
	
	/**
	 * Returns true if this operation has floating point registers or immediate values
	 * in it, false otherwise.
	 * @return
	 */
	public boolean instructionHasFloatingPointReferences(){
		if(destList != null){
			for(Temp destTemp : destList){
				if(!destTemp.isInteger())
					return true;
			}
		}
		
		if(srcList != null){
			for(Operand opr : srcList){
				if(opr.getOperandType()  == Operand.NUMERIC_TYPE){
					Numeric srcImmd = (Numeric) opr;
					if(!srcImmd.isInteger())
						return true;
				}
				else{
					Temp srcTemp = (Temp) opr;
					if(!srcTemp.isInteger())
						return true;
				}
			}
		}
		
		return false;
		
	}
	
	/**
	 * Replaces the immediate value passed in the first argument with the temp passed in the second argument
	 * @param numeric
	 * @param temp
	 */
	public void replaceImmediateValueWithTemp(Numeric numeric, Temp temp){
		if(srcList != null && srcList.size() > 0){
			int index = 0;
			int replacementIndex = -1;
			for(Operand operand : srcList){
				if(operand.getOperandType() == Operand.NUMERIC_TYPE){
					if(numeric == operand)
						replacementIndex = index;
				}
				index++;
			}
			
			if(replacementIndex > -1)
				srcList.set(replacementIndex, temp);
				
		}
		
		if(immediateValue != null){
			if(immediateValue == numeric){
				srcList.add(temp);
				immediateValue = null;
			}
		}
	}
	
	public boolean isCommutative(){
		if(operationCode == Operation.ADD || operationCode == Operation.MUL ||
				operationCode == Operation.BITWISE_XOR || operationCode == Operation.BITWISE_AND
				|| operationCode == Operation.BITWISE_OR)
			return true;
		
		return false;
	}
}

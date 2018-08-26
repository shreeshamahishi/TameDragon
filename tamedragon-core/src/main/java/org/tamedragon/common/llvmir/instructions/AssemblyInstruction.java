package org.tamedragon.common.llvmir.instructions;

import java.util.Vector;

import org.tamedragon.common.llvmir.types.Operand;
import org.tamedragon.common.llvmir.types.Temp;

public interface AssemblyInstruction 
{
	public static final int INSTRUCTION = 1	;
	public static final int JUMP = 2;
	public static final int CJUMP = 3;
	public static final int CALL = 4;
	public static final int LABEL = 5;
	public static final int SSA_PHI_FUNCTION = 6;
	public static final int RETURN = 7;
	public static final int ARG_PASS = 8;
	public static final int CONDITIONAL_FLAG_SET = 9;
	
	public Vector<Temp> getDestList();
	public Vector<Operand> getSrcList();
	public Vector getJumps();
	public String getLabelStr();
	public boolean isMove();
	public int getType();
	public void updateSourceList(int operandIndex, Operand operand);
	
}


package org.tamedragon.assemblyabstractions.constructs;

import org.tamedragon.common.Label;

public abstract class AssemExp implements AssemType 
{
	
	public static final int BIN_OP = 1;
	public static final int TEMP = 2;
	public static final int CONST = 3;
	public static final int CALL = 4;
	public static final int MEM = 5;
	public static final int NAME = 6;
	public static final int SEQ_EXP = 7;
	public static final int UNARY_EXP = 8;
	
	public abstract AssemType translateToCJump(Label testLabel, Label endwhileLabel, boolean isNegation);
	
	public abstract AssemType translateToIntType();
	
	public abstract AssemExpList children();
	
	public abstract AssemExp build(AssemExpList list);
	
	public abstract AssemSeqExp canonize();
	
	public AssemType translateToStatement(){
		return new AssemExpStm(this);
	}
	
	public abstract int getExpType();
	
	public abstract AssemValueProperties getValueProperties();
}

package org.tamedragon.assemblyabstractions.constructs;

public abstract class AssemStm implements AssemType
{
	
	public static final int CJUMP = 1;
	public static final int EXP_STM = 2;
	public static final int JUMP = 3;
	public static final int LABEL = 4;
	public static final int MOVE = 5;
	public static final int SEQ = 6;
	public static final int CALL = 7;
	public static final int MOVE_CALL = 8;
	public static final int RETURN = 9;
	public static final int ARG_PASS_STM = 10;

	public int getAssemTypeType() {
		return AssemType.VOID_TYPE;
	}
	
	public abstract String getDescription();
	
	public abstract AssemStm build(AssemExpList list);
	
	public abstract AssemExpList children();
	
	public abstract AssemStm canonize();
	
	public abstract int getStmType();
}

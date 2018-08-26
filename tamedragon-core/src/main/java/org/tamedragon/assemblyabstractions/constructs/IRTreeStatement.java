package org.tamedragon.assemblyabstractions.constructs;

/**
 * This abstract class encapsulates the common features a of LLVM Instruction
 * @author ipsg
 *
 */
public abstract class IRTreeStatement  implements IRTree{
	
	public enum TreeStatementType{
		ARG_PASS(0),
		DECLARATION (1),
		MOVE(2),
		RETURN(3),
		EXPRESSION(4),
		UNCONDITIONAL_JUMP(5),
		LOOP_BODY_STMT(6),
		LABEL(7),
		CONDITIONAL_JUMP(8),
		GLOBAL(9),
		TYPE(10),
		FUNC(11),
		RET_VAL(12),
		SWITCH(13),
		UNREACHABLE(14);
		
		private int value;
		
		private TreeStatementType(int i){
			value = i;
		}
	}
	
	protected TreeStatementType statementType;
	
	public TreeStatementType getStatementType(){
		return statementType;
	}

	@Override
	public abstract String getDescription();
}

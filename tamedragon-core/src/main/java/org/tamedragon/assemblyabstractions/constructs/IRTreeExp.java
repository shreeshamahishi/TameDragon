package org.tamedragon.assemblyabstractions.constructs;

/**
 * This is an abstract class which must be extended by other IRTree Expression classes like
 * Binary Expressions, Call Expressions, PhiNodes etc. 
 * @author ipsg
 *
 */
public abstract class IRTreeExp implements IRTree{
	
	public enum TreeNodeExpType{		
		CONST_EXP(0),
		TEMP_OR_VAR_EXP(1),
		CALL_EXP(2),
		BINARY_EXP(3),
		MEMORY(4),
		CONDITIONAL_EXPR(5),
		PHI_NODE(6),
		AGR_EXP(7),
		CAST_EXP(8),
		ALLOCA(9),
		EXPR_LIST(10);
		
		private int value;
		
		private TreeNodeExpType(int value){
			this.value = value;
		}
		
		public int getValue(){
			return value;
		}
	}
	
	protected TreeNodeExpType expType;
	
	public TreeNodeExpType getExpType() {
		return expType;
	}
	
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}
}

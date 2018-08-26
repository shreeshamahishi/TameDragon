package org.tamedragon.assemblyabstractions.constructs;

/**
 * This class holds information needed to create LLVM's Alloca Instruction for the Returned Value
 * @author ipsg
 *
 */
public class IRTreeReturnAlloca extends IRTreeStatement{
	
	private IRTreeMemory memory;
	
	public IRTreeReturnAlloca(IRTreeMemory memory){
		this.memory = memory;
		this.statementType = TreeStatementType.RET_VAL;
	}

	public IRTreeMemory getMemory() {
		return memory;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}
}

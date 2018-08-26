package org.tamedragon.assemblyabstractions.constructs;

import org.tamedragon.common.llvmir.types.Type;

/**
 * This class holds information needed to create LLVM's Return Instruction
 * @author ipsg
 *
 */
public class IRTreeReturn extends IRTreeStatement  {

	private IRTreeExp expTree;
	private Type expectedReturnType;
	private Type originalReturnType;
	
	public IRTreeExp getExpTree() {
		return expTree;
	}

	public Type getExpectedReturnType() {
		return expectedReturnType;
	}

	public Type getOriginalReturnType() {
		return originalReturnType;
	}

	public IRTreeReturn(IRTreeExp expTree, Type expectedReturnType, Type originalReturnType){
		this.expTree = expTree;
		this.expectedReturnType = expectedReturnType;
		this.originalReturnType = originalReturnType;
		this.statementType = TreeStatementType.RETURN;
	}

	@Override
	public String getDescription() {
		String retString  = "return ";
		if(expTree == null)
			retString += "void";
		else
			retString += expTree.getDescription();
		
		return retString;
	}
}

package org.tamedragon.assemblyabstractions.constructs;

/**
 * This class holds information needed to create LLVM's unreachable instruction
 * @author ipsg
 *
 */
public class IRTreeUnReachable extends IRTreeStatement {

	public IRTreeUnReachable(){
		this.statementType = TreeStatementType.UNREACHABLE;
	}
	
	@Override
	public String getDescription() {
		return "unreachable";
	}

}

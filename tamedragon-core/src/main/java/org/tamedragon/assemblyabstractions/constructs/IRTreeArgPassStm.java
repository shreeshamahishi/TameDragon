package org.tamedragon.assemblyabstractions.constructs;

/**
 * This class holds the information needed to create LLVM's Function Arguments
 * @author ipsg
 *
 */
public class IRTreeArgPassStm extends IRTreeStatement{
	
	private IRTreeTempOrVar originalValuePassed;
	private IRTreeMemory memory;
	
	public IRTreeArgPassStm(IRTreeTempOrVar originalValuePassed, IRTreeMemory memory){
		this.originalValuePassed = originalValuePassed;
		this.memory = memory;
		this.statementType = TreeStatementType.ARG_PASS;
	}
	
	public IRTreeTempOrVar getOriginalValuePassed() {
		return originalValuePassed;
	}

	public IRTreeMemory getMemory() {
		return memory;
	}

	@Override
	public String getDescription() {
		String desc = "ArgPass";
		if(originalValuePassed == null)
			desc += "(void)";
		else
			desc += "(" + originalValuePassed.getDescription() + ")";
		return desc;
	}
}

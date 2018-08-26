package org.tamedragon.assemblyabstractions.constructs;

import org.tamedragon.common.llvmir.types.Value;

/**
 * This class holds information used to create LLVM's Variables which are stored in memory
 * @author ipsg
 *
 */
public class IRTreeMemory extends IRTreeExp {
	
	private Value memory;
	private IRTreeExp irTreeExp;

	public IRTreeMemory(Value memory){
		this.memory = memory;
		this.expType = TreeNodeExpType.MEMORY;
	}
	
	public IRTreeMemory(Value memory, IRTreeExp irTreeExp){
		this.memory = memory;
		this.irTreeExp = irTreeExp;
		this.expType = TreeNodeExpType.MEMORY;
	}
	
	public Value getMemory() {
		return memory;
	}

	public IRTreeExp getIrTreeExp() {
		return irTreeExp;
	}

	public void setIrTreeExp(IRTreeExp irTreeExp) {
		this.irTreeExp = irTreeExp;
	}
}

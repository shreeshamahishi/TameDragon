package org.tamedragon.assemblyabstractions.constructs;

import org.tamedragon.common.llvmir.types.Type;

/**
 * This class holds the information needed to create LLVM's Alloca Instruction
 * @author ipsg
 *
 */
public class IRTreeAlloca extends IRTreeExp implements IRTree {

	private Type type;
	private IRTreeExp irTreeExp;
	
	public IRTreeAlloca(Type type, IRTreeExp irTreeExp){
		this.type = type;
		this.irTreeExp = irTreeExp;
		this.expType = TreeNodeExpType.ALLOCA;
	}
	
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public IRTreeExp getIrTreeExp() {
		return irTreeExp;
	}

	public void setIrTreeExp(IRTreeExp irTreeExp) {
		this.irTreeExp = irTreeExp;
	}

	@Override
	public String getDescription() {
		return "alloca " + type.toString() + " " + irTreeExp.toString();
	}
}

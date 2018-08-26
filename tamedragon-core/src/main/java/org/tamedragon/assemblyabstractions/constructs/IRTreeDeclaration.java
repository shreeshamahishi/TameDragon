package org.tamedragon.assemblyabstractions.constructs;

import org.tamedragon.common.llvmir.instructions.Instruction.AtomicOrdering;
import org.tamedragon.common.llvmir.instructions.Instruction.SynchronizationScope;
import org.tamedragon.common.llvmir.types.GlobalValue.LinkageTypes;

/**
 * This class holds the information needed to create LLVM's Alloca and Store Instruction for a given variable declaration
 * @author ipsg
 *
 */
public class IRTreeDeclaration extends IRTreeStatement {
	
	private IRTreeExp initializer;
	private IRTreeMemory memoryTree;
	private boolean volatileFlag;
	private boolean isGlobal;
	private boolean isConstant;
	private boolean isUnnamedAddr;
	private AtomicOrdering atomicOrdering;
	private SynchronizationScope syncScope;
	private LinkageTypes linkageTypes;
	
	public IRTreeDeclaration(IRTreeMemory memoryTree, IRTreeExp initializer, boolean isGlobal, boolean isConstant, boolean isUnnamedAddr, LinkageTypes linkageTypes){
		this.initializer = initializer;
		this.memoryTree = memoryTree;
		this.isGlobal = isGlobal;
		this.isConstant = isConstant;
		this.isUnnamedAddr = isUnnamedAddr;
		this.linkageTypes = linkageTypes;
		this.statementType = TreeStatementType.DECLARATION;
	}
	
	public boolean isVolatileFlag() {
		return volatileFlag;
	}

	public boolean isConstant() {
		return isConstant;
	}

	public void setVolatileFlag(boolean volatileFlag) {
		this.volatileFlag = volatileFlag;
	}

	public AtomicOrdering getAtomicOrdering() {
		return atomicOrdering;
	}

	public void setAtomicOrdering(AtomicOrdering atomicOrdering) {
		this.atomicOrdering = atomicOrdering;
	}

	public SynchronizationScope getSyncScope() {
		return syncScope;
	}

	public void setSyncScope(SynchronizationScope syncScope) {
		this.syncScope = syncScope;
	}

	public boolean isGlobal() {
		return isGlobal;
	}

	public boolean isUnnamedAddr() {
		return isUnnamedAddr;
	}

	public IRTreeExp getInitializerExpression() {
		return initializer;
	}

	public void setInitializer(IRTreeExp initializer) {
		this.initializer = initializer;
	}

	public IRTreeMemory getMemoryTree() {
		return memoryTree;
	}
	
	public LinkageTypes getLinkageTypes() {
		return linkageTypes;
	}

	public void setLinkageTypes(LinkageTypes linkageTypes) {
		this.linkageTypes = linkageTypes;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}
}

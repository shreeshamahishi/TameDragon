package org.tamedragon.assemblyabstractions.constructs;

/**
 * This class holds information needed to create the index, which is used by LLVM's GetElementPtr Instruction
 * @author ipsg
 *
 */
public class IRTreeIndex {
	
	private IRTree irTree;
	boolean isNegative;
	
	public IRTreeIndex(IRTree irTree, boolean isNegative) {
		this.irTree = irTree;
		this.isNegative = isNegative;
	}

	public IRTree getIrTree() {
		return irTree;
	}

	public void setIrTree(IRTree irTree) {
		this.irTree = irTree;
	}

	public boolean isNegative() {
		return isNegative;
	}

	public void setNegative(boolean isNegative) {
		this.isNegative = isNegative;
	}
}

package org.tamedragon.assemblyabstractions.constructs;

/**
 * This class holds the information needed to create LLVM's Cast Instruction
 * @author ipsg
 *
 */
public class IRTreeCast extends IRTreeExp {
	
	private IRTree destTree;
	private IRTree srcTree;
	private boolean isBitCastOpr;
	
	public IRTreeCast(IRTree srcTree, IRTree destTree){
		this.destTree = destTree;
		this.srcTree = srcTree;
		this.expType = TreeNodeExpType.CAST_EXP;
	}

	public IRTree getDestTree() {
		return destTree;
	}
	
	public IRTree getSrcTree() {
		return srcTree;
	}

	public boolean isBitCastOpr() {
		return isBitCastOpr;
	}

	public void setBitCastOpr(boolean isBitCastOpr) {
		this.isBitCastOpr = isBitCastOpr;
	}
}

package org.tamedragon.assemblyabstractions.constructs;

import org.tamedragon.common.llvmir.instructions.BinaryOperator.BinaryOperatorID;

/**
 * This class holds the information needed to create LLVM's Binary Expressions
 * @author ipsg
 *
 */
public class IRTreeBinaryExp extends IRTreeExp {
	
	private BinaryOperatorID binaryOperatorID;
	private IRTreeExp left, right;
	
	public IRTreeBinaryExp(BinaryOperatorID binOpID, IRTreeExp left, IRTreeExp right){
		this.binaryOperatorID = binOpID;
		this.left = left;
		this.right = right;
		expType = TreeNodeExpType.BINARY_EXP;
	}
	
	public BinaryOperatorID getBinaryOperatorID() {
		return binaryOperatorID;
	}
	
	public void setBinaryOperatorID(BinaryOperatorID bID) {
		this.binaryOperatorID = bID;
	}
	
	public IRTreeExp getLeft() {
		return left;
	}
	
	public void setLeft(IRTreeExp left) {
		this.left = left;
	}
	
	public IRTreeExp getRight() {
		return right;
	}
	
	public void setRight(IRTreeExp right) {
		this.right = right;
	}
}

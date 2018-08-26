package org.tamedragon.compilers.clang.abssyntree;

public class ArgumentExpressionList extends Absyn  implements NodeCollection{
	
	private AssignmentExpr assignmentExpr;
	private ArgumentExpressionList nextList;
	
	public ArgumentExpressionList(AssignmentExpr ae, ArgumentExpressionList ael){
		this.assignmentExpr = ae;
		this.nextList = ael;
	}
	
	public ArgumentExpressionList(){}
	
	public int getNodeCollectionType(){
		return NodeCollection.ARG_EXPR_LIST;
	}
	
	public NodeCollection getNextInCollection(){
		return nextList;
	}

	public ArgumentExpressionList getArgumentExpressionList() {
		return nextList;
	}

	public void setArgumentExpressionList(
			ArgumentExpressionList argumentExpressionList) {
		this.nextList = argumentExpressionList;
	}

	public AssignmentExpr getAssignmentExpr() {
		return assignmentExpr;
	}

	public void setAssignmentExpr(AssignmentExpr assignmentExpr) {
		this.assignmentExpr = assignmentExpr;
	}

	public void setDeclarationListNext(NodeCollection newList){
		this.nextList = (ArgumentExpressionList)newList;
	}
}

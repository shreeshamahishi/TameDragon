package org.tamedragon.compilers.clang.abssyntree;

public class RootExpr extends Absyn implements Expr, NodeCollection{
	private AssignmentExpr assignmentExpr;
	private RootExpr rootExprNext;

	public RootExpr(){}
	
	public RootExpr(AssignmentExpr ae){
		this.assignmentExpr = ae;
	}
	
	public RootExpr(RootExpr re, AssignmentExpr ae){
		this.rootExprNext = re;
		this.assignmentExpr = ae;
	}
	
	public int getExprType(){
		return Expr.ROOT;
	}

	public AssignmentExpr getAssignmentExpr() {
		return assignmentExpr;
	}

	public void setAssignmentExpr(AssignmentExpr assignmentExpr) {
		this.assignmentExpr = assignmentExpr;
	}

	public RootExpr getRootExprNext() {
		return rootExprNext;
	}

	public void setRootExprNext(RootExpr assignmentExprNext) {
		this.rootExprNext = assignmentExprNext;
	}
	
	public int getNodeCollectionType(){
		return NodeCollection.ROOT_EXPR_LIST;
	}
	public NodeCollection getNextInCollection(){
		return rootExprNext;
	}
	public void setDeclarationListNext(NodeCollection newList){
		this.rootExprNext = (RootExpr)newList;
	}
	
	public String toString(){
		String str = assignmentExpr.toString();
		if(rootExprNext != null){
			str += ", " + rootExprNext.toString();
		}
		return str;
	}	
}

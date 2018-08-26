package org.tamedragon.compilers.clang.abssyntree;

public class AssignmentExpr extends Absyn  implements Expr, NodeItem{
	private ConditionalExpr conditionalExpr;
	private UnaryExpr unaryExpr;
	private AssignmentExpr assignmentExpr;
	private ExplicitCasting explicitCasting;
	
	private AssignmentOperator assignmentOperator;
	
	public AssignmentExpr(){}
	
	public AssignmentExpr(ConditionalExpr ce){
		this.conditionalExpr = ce;
	}
	
	public AssignmentExpr(UnaryExpr unaryExpr, AssignmentExpr ae, int oprType){
		this.unaryExpr = unaryExpr;
		this.assignmentExpr = ae;
		this.assignmentOperator =  new AssignmentOperator(oprType);
	}
	
	public AssignmentExpr getAssignmentExpr() {
		return assignmentExpr;
	}
	public void setAssignmentExpr(AssignmentExpr assignmentExpr) {
		this.assignmentExpr = assignmentExpr;
	}
	public ConditionalExpr getConditionalExpr() {
		return conditionalExpr;
	}
	public void setConditionalExpr(ConditionalExpr conditionalExpr) {
		this.conditionalExpr = conditionalExpr;
	}
	public UnaryExpr getUnaryExpr() {
		return unaryExpr;
	}
	public void setUnaryExpr(UnaryExpr unaryExpr) {
		this.unaryExpr = unaryExpr;
	}
	public AssignmentOperator getAssignmentOperator() {
		return assignmentOperator;
	}
	public void setAssignmentOperator(AssignmentOperator assignmentOperator) {
		this.assignmentOperator = assignmentOperator;
	}
	
	public ExplicitCasting getExplicitCasting() {
		return explicitCasting;
	}

	public void setExplicitCasting(ExplicitCasting explicitCasting) {
		this.explicitCasting = explicitCasting;
	}

	public int getExprType(){
		return Expr.ASSIGNMENT;
	}
	
	public int getNodeItemType(){
		return NodeItem.ASSIGNMENT_EXPR;
	}
	
	public String toString(){
		String str = "";
		if(conditionalExpr != null)
			if(explicitCasting != null)
				str = "(" + explicitCasting.toString() + ")" + conditionalExpr.toString();
			else
				str = conditionalExpr.toString();
		else
			if(explicitCasting != null)
				str = unaryExpr.toString() + "=" + "(" + explicitCasting.toString() + ")" + assignmentExpr.toString();
			else
				str = unaryExpr.toString() + "=" + assignmentExpr.toString();
		return str;
	}
	
}

package org.tamedragon.compilers.clang.abssyntree;

public class Initializer extends Absyn implements NodeItem{
	private AssignmentExpr initExpr;
	private InitializerList initializerList;
	private CompoundStatement compoundStatement;
	
	public Initializer(){}
	
	public Initializer(AssignmentExpr initExpr, InitializerList initializerList)
	{
		this.initExpr = initExpr;
		this.initializerList = initializerList;
	}

	public AssignmentExpr getInitExpr() {
		return initExpr;
	}

	public void setInitExpr(AssignmentExpr initExpr) {
		this.initExpr = initExpr;
	}

	public InitializerList getInitializerList() {
		return initializerList;
	}

	public void setInitializerList(InitializerList initializerList) {
		this.initializerList = initializerList;
	}
	
	public int getNodeItemType(){
		return NodeItem.INITIALIZER;
	}

	public CompoundStatement getCompoundStatement() {
		return compoundStatement;
	}

	public void setCompoundStatement(CompoundStatement compoundStatement) {
		this.compoundStatement = compoundStatement;
	}
}

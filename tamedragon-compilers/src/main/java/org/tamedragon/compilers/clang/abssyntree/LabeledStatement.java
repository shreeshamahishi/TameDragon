package org.tamedragon.compilers.clang.abssyntree;

public class LabeledStatement extends Statement{

	private String label;
	private ConstExpr caseExpr; // should be constant expression
	private Statement stmt;
	
	public LabeledStatement(String label, ConstExpr caseExpr,Statement stmt)
	{
		this.label = label;
		this.caseExpr = caseExpr;
		this.stmt = stmt;
	}

	public ConstExpr getCaseExpr() {
		return caseExpr;
	}

	public void setCaseExpr(ConstExpr caseExpr) {
		this.caseExpr = caseExpr;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Statement getStmt() {
		return stmt;
	}

	public void setStmt(Statement stmt) {
		this.stmt = stmt;
	}
}

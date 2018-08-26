package org.tamedragon.compilers.clang.abssyntree;

public class SelectionStatement extends Statement{
	
	private RootExpr ifExpr;
	private Statement ifStmt;
	private Statement elseStmt;
	private RootExpr switchExpr;
	private Statement switchStmt;
	
	public SelectionStatement(){}
	
	public SelectionStatement(RootExpr ifExpr, Statement ifStmt,
			Statement elseStmt, RootExpr switchExpr, Statement switchStmt)
	{
		this.ifExpr = ifExpr;
		this.ifStmt = ifStmt;
		this.elseStmt = elseStmt;
		this.switchExpr = switchExpr;
		this.switchStmt = switchStmt;
	}

	public Statement getElseStmt() {
		return elseStmt;
	}

	public void setElseStmt(Statement elseStmt) {
		this.elseStmt = elseStmt;
	}

	public RootExpr getIfExpr() {
		return ifExpr;
	}

	public void setIfExpr(RootExpr ifExpr) {
		this.ifExpr = ifExpr;
	}

	public Statement getIfStmt() {
		return ifStmt;
	}

	public void setIfStmt(Statement ifStmt) {
		this.ifStmt = ifStmt;
	}

	public RootExpr getSwitchExpr() {
		return switchExpr;
	}

	public void setSwitchExpr(RootExpr switchExpr) {
		this.switchExpr = switchExpr;
	}

	public Statement getSwitchStmt() {
		return switchStmt;
	}

	public void setSwitchStmt(Statement switchStmt) {
		this.switchStmt = switchStmt;
	}
}

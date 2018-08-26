package org.tamedragon.compilers.clang.abssyntree;

public class IterationStatement extends Statement{
	
	public static final int WHILE = 1;
	public static final int DO_WHILE = 2;
	public static final int FOR = 3;
	
	private RootExpr whileExpr;
	private Statement whileStmt;
	
	private RootExpr forInitExpr;
	private Declaration declaration;
	private RootExpr forCondExpr;
	private RootExpr forIncrExpr;
	private Statement forStmt;
	
	private int iterationType;
	
	public IterationStatement() {}
	
	public IterationStatement(RootExpr whileExpr, Statement whileStmt, int iterationType)
	{
		this.iterationType = iterationType;
		this.whileExpr = whileExpr;
		this.whileStmt = whileStmt;
	}
	
	
	public IterationStatement(RootExpr forInitExpr, RootExpr forCondExpr, RootExpr forIncrExpr, Statement forStmt)
	{
		this.forInitExpr = forInitExpr;
		this.forCondExpr = forCondExpr;
		this.forIncrExpr = forIncrExpr;
		this.forStmt = forStmt;
	}

	public RootExpr getForCondExpr() {
		return forCondExpr;
	}

	public void setForCondExpr(RootExpr forCondExpr) {
		this.forCondExpr = forCondExpr;
	}

	public RootExpr getForIncrExpr() {
		return forIncrExpr;
	}

	public void setForIncrExpr(RootExpr forIncrExpr) {
		this.forIncrExpr = forIncrExpr;
	}
	
	public Declaration getForDeclr() {
		return declaration;
	}

	public void setForDeclr(Declaration declaration) {
		this.declaration = declaration;
	}

	public RootExpr getForInitExpr() {
		return forInitExpr;
	}

	public void setForInitExpr(RootExpr forInitExpr) {
		this.forInitExpr = forInitExpr;
	}

	public Statement getForStmt() {
		return forStmt;
	}

	public void setForStmt(Statement forStmt) {
		this.forStmt = forStmt;
	}

	public RootExpr getWhileExpr() {
		return whileExpr;
	}

	public void setWhileExpr(RootExpr whileExpr) {
		this.whileExpr = whileExpr;
	}

	public Statement getWhileStmt() {
		return whileStmt;
	}

	public void setWhileStmt(Statement whileStmt) {
		this.whileStmt = whileStmt;
	}

	public int getIterationType() {
		return iterationType;
	}

	public void setIterationType(int iterationType) {
		this.iterationType = iterationType;
	}
}

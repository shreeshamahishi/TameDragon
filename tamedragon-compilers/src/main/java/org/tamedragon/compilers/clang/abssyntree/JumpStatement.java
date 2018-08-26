package org.tamedragon.compilers.clang.abssyntree;

public class JumpStatement extends Statement{

	public static final int GOTO = 1;
	public static final int CONTINUE = 2;
	public static final int BREAK = 3;
	public static final int RETURN = 4;
	
	private String gotoIdentifier;
	private RootExpr returnExpr;
	
	private int type;
	
	public JumpStatement(){}
	
	public JumpStatement(int type, String gotoIdentifier, RootExpr returnExpr ) {
		this.type = type;
		this.gotoIdentifier = gotoIdentifier;
		this.returnExpr = returnExpr;
	}

	public String getGotoIdentifier() {
		return gotoIdentifier;
	}

	public void setGotoIdentifier(String gotoIdentifier) {
		this.gotoIdentifier = gotoIdentifier;
	}

	public RootExpr getReturnExpr() {
		return returnExpr;
	}

	public void setReturnExpr(RootExpr returnExpr) {
		this.returnExpr = returnExpr;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
}

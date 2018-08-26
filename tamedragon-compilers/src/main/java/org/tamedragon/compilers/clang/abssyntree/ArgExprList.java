package org.tamedragon.compilers.clang.abssyntree;

public class ArgExprList extends Absyn implements NodeItem{

	private RootExpr expr;
	private ArgExprList argExprList;
	
	public ArgExprList(RootExpr expr, ArgExprList argExprList)
	{
		this.expr = expr;
		this.argExprList = argExprList;
	}
	
	public int getNodeItemType(){
		return NodeItem.ARG_EXPR_LIST;
	}
}

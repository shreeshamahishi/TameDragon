package org.tamedragon.compilers.clang.abssyntree;

public class FunctionCallExpr extends RootExpr{
	
	private String name;
	private ArgExprList argExprList;
	
	public FunctionCallExpr(String name, ArgExprList argExprList)
	{
		this.name = name;
		this.argExprList = argExprList;
	}
}

package org.tamedragon.compilers.clang.abssyntree;

public class VarExpr extends RootExpr
{
	public static final int DOT = 1;
	public static final int POINTER_MEM_ACCESS = 2;
	public static final int ARRAY = 3;
	public static final int IDENTIFIER = 3;
	
	private int type;
	
	private RootExpr baseExpr;
	private RootExpr memExpr;
	
	private String varIdentifier;
	
	public VarExpr(int type, RootExpr baseExpr, RootExpr memExpr)
	{
		this.type = type;
		this.baseExpr = baseExpr;
		this.memExpr = memExpr;
	}
	
	public VarExpr(int type, String str)
	{
		this.type = type;
		this.varIdentifier = str;
	}
}

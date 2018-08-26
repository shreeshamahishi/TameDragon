package org.tamedragon.compilers.clang.abssyntree;

public class UnaryOpExpr extends RootExpr{

	public static final int PREFIX_INCREMENT = 1;
	public static final int POSTFIX_INCREMENT = 2;
	public static final int PREFIX_DECREMENT = 3;
	public static final int POSTFIX_DECREMENT = 4;
	public static final int AMPERSAND = 1;
	public static final int STAR = 1;
	public static final int PLUS = 1;
	public static final int MINUS = 1;
	public static final int ONES_COMPLEMENT = 1;
	public static final int NOT = 1;
	public static final int SIZEOF_EXPR = 1;
	public static final int SIZEOF_TYPENAME = 1;
	
	private int type;
	private RootExpr expr;
	private TypeName typeName;
	
	public UnaryOpExpr(int type, RootExpr expr, TypeName typeName)
	{
		this.type = type;
		this.expr = expr;
		this.typeName = typeName;
	}
}

package org.tamedragon.compilers.clang.abssyntree;

public class PostfixExpr extends Absyn implements Expr {
	
	public static final int DOT = 0;
	public static final int DEREFERENCE = 1;
	public static final int INCR = 2;
	public static final int DECR = 3;
	public static final int ARRAY_REF = 4;
	public static final int FUNC_CALL = 5;
	public static final int PRIMARY = 6;
	
	private PrimaryExpr primaryExpr;
	private PostfixExpr postfixExpr;
	
	private RootExpr arrayExpr;
	private ArgumentExpressionList argumentExpressionList;
	private String identifier;
	private int type;
	
	
	public String toString(){		
		if(postfixExpr != null){
			String pre = postfixExpr.toString();
			
			if(type == DOT)
				pre = pre + "." +  identifier;
			else if(type == DEREFERENCE)
				pre = pre + "->" + identifier;
			else if(type == ARRAY_REF)
				pre = pre + "[" + arrayExpr.toString() + "]";
			else if(type == INCR)
				pre = pre + "++";
			else if(type == DECR)
				pre = pre + "--";
			else if(type == FUNC_CALL)
				pre = pre + "(" + argumentExpressionList.toString() + ")";  // TODO implement toString for this class
			
			return pre ;
		}
		
		return primaryExpr.toString();		
	}

	
	public PostfixExpr(){
		type = -1;
	}
	
	public PostfixExpr(PrimaryExpr pe){
		type = PRIMARY;
		this.primaryExpr = pe;
	}
	
	public PostfixExpr(PostfixExpr poe, int type){
		this.postfixExpr = poe;
		this.type = type;
	}
	
	public PostfixExpr(PostfixExpr poe, String id, int type){
		this.postfixExpr = poe;
		this.type = type;
		this.identifier = id;
	}
	
	public PostfixExpr(PostfixExpr poe, ArgumentExpressionList ael){
		this.postfixExpr = poe;
		this.type = FUNC_CALL;
		this.argumentExpressionList = ael;
	}
	
	public PostfixExpr(PostfixExpr poe, Expr expr){
		this.postfixExpr = poe;
		this.type = ARRAY_REF;
		this.arrayExpr = (RootExpr)expr;
	}
	
	public ArgumentExpressionList getArgumentExpressionList() {
		return argumentExpressionList;
	}
	public void setArgumentExpressionList(
			ArgumentExpressionList argumentExpressionList) {
		this.argumentExpressionList = argumentExpressionList;
	}
	public RootExpr getArrayExpr() {
		return arrayExpr;
	}
	public void setArrayExpr(RootExpr arrayExpr) {
		this.arrayExpr = arrayExpr;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public PostfixExpr getPostfixExpr() {
		return postfixExpr;
	}
	public void setPostfixExpr(PostfixExpr postfixExpr) {
		this.postfixExpr = postfixExpr;
	}
	public PrimaryExpr getPrimaryExpr() {
		return primaryExpr;
	}
	public void setPrimaryExpr(PrimaryExpr primaryExpr) {
		type = PRIMARY;
		this.primaryExpr = primaryExpr;
	}
	
	public int getExprType(){
		return Expr.POST_FIX;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}

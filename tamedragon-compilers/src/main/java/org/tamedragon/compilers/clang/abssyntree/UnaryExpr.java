package org.tamedragon.compilers.clang.abssyntree;

public class UnaryExpr extends Absyn implements Expr{
	
	public static final int INCR = 0;
	public static final int DECR = 1;
	public static final int PLUS = 2;
	public static final int MINUS = 3;
	public static final int AMPERSAND = 4;
	public static final int STAR = 5;
	public static final int TILDE = 6;
	public static final int NOT = 7;
	public static final int SIZEOF = 8;
	public static final int TYPE_NAME = 9;
	
	
	private PostfixExpr postfixExpr;
	private int operator;
	private CastExpr castExpr;
	private TypeName typeName;
	private UnaryExpr unaryExpr;
	
	private int type;
	
	public UnaryExpr(){}
	
	public UnaryExpr(int type, CastExpr ce){
		this.type = type;
		castExpr = ce;
	}
	
	public UnaryExpr(PostfixExpr pe){
		postfixExpr = pe;
	}
	
	public UnaryExpr(int type, UnaryExpr ue){
		this.type = type;
		unaryExpr = ue;
	}
	
	public UnaryExpr(TypeName tn){
		this.type = TYPE_NAME;
		typeName = tn;
	}
	
	public int getOperator() {
		return operator;
	}
	public void setOperator(int operator) {
		this.operator = operator;
	}
	public PostfixExpr getPostfixExpr() {
		return postfixExpr;
	}
	public void setPostfixExpr(PostfixExpr postfixExpr) {
		this.postfixExpr = postfixExpr;
	}
	public TypeName getTypeName() {
		return typeName;
	}
	public void setTypeName(TypeName typeName) {
		this.typeName = typeName;
	}
	public CastExpr getCastExpr() {
		return castExpr;
	}
	public void setCastExpr(CastExpr castExpr) {
		this.castExpr = castExpr;
	}
	public UnaryExpr getUnaryExpr() {
		return unaryExpr;
	}
	public void setUnaryExpr(UnaryExpr unaryExpr) {
		this.unaryExpr = unaryExpr;
	}
	
	public int getExprType(){
		return Expr.UNARY;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}	

	public String toString(){
		
		if(unaryExpr != null){
			String pre = "++";
			if(type == INCR)
				pre = "--";
			else if(type == SIZEOF)
				pre = "sizeof ";
			return pre + unaryExpr.toString();
		}
		else if(castExpr != null){
			String pre = "+";
			if(type == MINUS)
				pre = "-";
			else if(type == AMPERSAND)
				pre = "&";
			else if(type == STAR)
				pre = "*";
			else if(type == TILDE)
				pre = "~";
			else if(type == NOT)
				pre = "!";
			return pre + castExpr.toString();
		}
		else if(typeName != null){
			return "sizeof " + typeName.toString();
		}
		
		return postfixExpr.toString();
		
	}

}

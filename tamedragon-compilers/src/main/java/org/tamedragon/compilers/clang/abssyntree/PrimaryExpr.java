package org.tamedragon.compilers.clang.abssyntree;

public class PrimaryExpr extends Absyn implements Expr {
	
	public static final int IDENTIFIER_TYPE = 0;
	public static final int CONSTANT_TYPE = 1;
	public static final int STRING_TYPE = 2;
	public static final int EXPR_TYPE = 3;
	
	private String identifier;
	private Constant constant;
	private String string;
	private RootExpr expr;
	
	private int type;
	
	public PrimaryExpr(){}
	
	public PrimaryExpr(int type){
		this.type = type;
	}
	
	public PrimaryExpr(Expr e){
		this.type = EXPR_TYPE;
		this.expr = (RootExpr)e;
	}
	
	public PrimaryExpr(Constant ct){
		this.type = CONSTANT_TYPE;
		this.constant = ct;
	}
	
	public PrimaryExpr(int type, String str){
		if(type == IDENTIFIER_TYPE)
			identifier = str;
		else
			string = str;
	}
	
	public Constant getConstant() {
		return constant;
	}
	public void setConstant(Constant constant) {
		this.constant = constant;
	}
	public RootExpr getExpr() {
		return expr;
	}
	public void setExpr(RootExpr expr) {
		this.expr = expr;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getString() {
		return string;
	}
	public void setString(String string) {
		// Remove the enclosing double quotes
		this.string = string.substring(1, string.length() -1);
	}
	
	public int getExprType(){
		return Expr.PRIMARY;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}	
	
	public String toString(){
		String str = "";
		if(type == IDENTIFIER_TYPE)
			str = identifier;
		else if(type == CONSTANT_TYPE)
			str = constant.toString();
		else if(type == STRING_TYPE)
			str = string;
		else if(type == EXPR_TYPE)
			str = expr.toString();
		
		return str;
	}	
	
}

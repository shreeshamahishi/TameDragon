package org.tamedragon.compilers.clang.preprocessor;

public class PrimaryExpr extends Absyn  {
	
	public static final int IDENTIFIER_TYPE = 0;
	public static final int CONSTANT_TYPE = 1;
	public static final int STRING_TYPE = 2;
	public static final int CONST_EXPR_TYPE = 3;
	public static final int DEFINED_EXPR_TYPE = 4;
	public static final int FUNC_CALL_EXPR_TYPE = 5;
	
	private String identifier;
	private Constant constant;
	private String string;
	private Defined defined;
	private FuncCallExpr funcCallExpr;
	
	private ConstExpr constExpr;
	
	private int type;
	
	public PrimaryExpr(){}
	
	public PrimaryExpr(int type){
		this.type = type;
	}
	
	public PrimaryExpr(ConstExpr e){
		this.type = CONST_EXPR_TYPE;
		this.constExpr = (ConstExpr)e;
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
	
	public PrimaryExpr(Defined defined){
		this.defined = defined;
		this.type = DEFINED_EXPR_TYPE;
	}
	
	public PrimaryExpr(FuncCallExpr funcCallExpr){
		this.funcCallExpr = funcCallExpr;
		this.type = FUNC_CALL_EXPR_TYPE;
	}
	
	public Constant getConstant() {
		return constant;
	}
	public void setConstant(Constant constant) {
		this.constant = constant;
	}
	public ConstExpr getExpr() {
		return constExpr;
	}
	public void setConstExpr(ConstExpr expr) {
		this.constExpr = expr;
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
		else if(type == CONST_EXPR_TYPE)
			str = constExpr.toString();
		else if(type == DEFINED_EXPR_TYPE)
			str = defined.toString();
		else if(type == FUNC_CALL_EXPR_TYPE)
			str = funcCallExpr.toString();
		
		return str;
	}	
	
	public int evaluate(){
		if(type == IDENTIFIER_TYPE){
			DefinitionsMap definitionsMap = DefinitionsMap.getInstance();
			String defValue = definitionsMap.getDefinitionFromInstance(identifier);
			if(defValue != null){
				try{
					return Integer.parseInt(defValue);
				}
				catch(NumberFormatException nfe){
					return 1;
				}
			}
			else
				return 0;
		}
		else if(type == CONSTANT_TYPE){  // Integer constant
			String constantStr = constant.toString();
			char ch = constantStr.charAt(constantStr.length()-1);
			if(ch == 'L')
				constantStr = constantStr.substring(0, constantStr.length()-1);
			return Integer.parseInt(constantStr);
		}
		else if(type == STRING_TYPE)  // TODO Set it as error in the ErrorHandler
			return 0;   
		else if(type == DEFINED_EXPR_TYPE)
			return defined.evaluate();
		else if(type == FUNC_CALL_EXPR_TYPE)
			return funcCallExpr.evaluate();
		else
			return constExpr.evaluate();
	}
}

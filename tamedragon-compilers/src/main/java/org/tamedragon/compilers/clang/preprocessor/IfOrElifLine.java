package org.tamedragon.compilers.clang.preprocessor;

public class IfOrElifLine extends Absyn{
	
	public static final int IFDEF = 1;
	public static final int IFNDEF = 2;
	public static final int CONST_EXPR = 3;
	
	private int ifLineType;
	private ConstExpr constExpr;
	private String identifier;
	
	public IfOrElifLine(){}
		
	public IfOrElifLine(int lineNum, int type, ConstExpr constExpr){
		setLineNum(lineNum);
		this.ifLineType = type;
		this.constExpr = constExpr;

	}
	
	public IfOrElifLine(int lineNum, int type, String id){
		setLineNum(lineNum);
		this.ifLineType = type;
		this.identifier = id;
	}
	
	public int getIfLineType() {
		return ifLineType;
	}

	public void setIfLineType(int ifLineType) {
		this.ifLineType = ifLineType;
	}

	public ConstExpr getConstExpr() {
		return constExpr;
	}

	public void setConstExpr(ConstExpr constExpr) {
		this.constExpr = constExpr;
	}
		
	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	public boolean isTrue(){
		
		boolean evaluatesToTrue = false;
		if(identifier != null){              
			// The check is for whether or not an ID has been defined
			DefinitionsMap defsMap = DefinitionsMap.getInstance();
			String defValue = defsMap.getDefinition(identifier);
			if(defValue != null && ifLineType == IfLine.IFDEF){   
				// The key has been defined and the condition is #ifdef
				evaluatesToTrue = true;
			}
			
			if(defValue == null && ifLineType == IfLine.IFNDEF){  
				// The key has been not been defined and the condition is #ifndef
				evaluatesToTrue = true;
			}
		}
		else{
			// The check is for a constant expression (whether or not it evaluates to a non-zero value
			int exprVal = constExpr.evaluate();
			if(exprVal != 0)
				evaluatesToTrue = true;
		}
		
		return evaluatesToTrue;
	}
	
	

}

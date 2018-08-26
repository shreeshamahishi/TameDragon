package org.tamedragon.compilers.clang.preprocessor;

public class Elif extends IfOrElifLine  {
	
	public Elif(int lineNum, int defType, String id){
		super(lineNum, defType, id);
	}
	
	public Elif(int lineNum, int defType, ConstExpr constExpr){
		super(lineNum, defType, constExpr);
	}
	
}

package org.tamedragon.compilers.clang.preprocessor;

public class IfLine extends IfOrElifLine {
	
	public IfLine(int lineNum, int defType, String id){
		super(lineNum, defType, id);
	}
	
	public IfLine(int lineNum, int defType, ConstExpr constExpr){
		super(lineNum, defType, constExpr);
	}
		
}

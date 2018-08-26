package org.tamedragon.compilers.clang.abssyntree;

public class StructDeclarator extends Absyn implements NodeItem{

	private Declarator declarator;
	private ConstExpr bitFieldValue;

	public StructDeclarator(){}
	
	public StructDeclarator(Declarator declarator,
			ConstExpr bitFieldValue)
	{
		this.declarator = declarator;
		this.bitFieldValue = bitFieldValue;
	}

	public ConstExpr getBitFieldValue() {
		return bitFieldValue;
	}

	public void setBitFieldValue(ConstExpr bitFieldValue) {
		this.bitFieldValue = bitFieldValue;
	}

	public Declarator getDeclarator() {
		return declarator;
	}

	public void setDeclarator(Declarator declarator) {
		this.declarator = declarator;
	}
	
	public int getNodeItemType(){
		return NodeItem.STRUCT_DECLARATOR;
	}
	
}

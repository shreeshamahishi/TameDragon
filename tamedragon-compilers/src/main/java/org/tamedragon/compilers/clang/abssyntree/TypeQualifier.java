package org.tamedragon.compilers.clang.abssyntree;

public class TypeQualifier extends Absyn implements DeclSpecUnit, NodeItem {
	public static final int CONST = 1;
	public static final int VOLATILE = 2;
	
	protected int type;
	
	public TypeQualifier(){}
	
	public TypeQualifier(int line, int pos, int type) {
		this.type = type;
		setLineNum(line);
		setPos(pos);
	}
	
	public TypeQualifier(int type) {
		this.type = type;
	}
	
	public int getDecSpecType(){
		return DeclSpecUnit.TYPE_QUALIFIER;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public int getNodeItemType(){
		return NodeItem.TYPE_QUALIFIER;
	}
	
	public String toString(){
		String str = "";
		if(type == CONST)
			str = " const";
		else
			str = "volatile";
		
		return str;
	}
}

package org.tamedragon.compilers.clang.abssyntree;

public class TypeSpecifier extends Absyn implements DeclSpecUnit{
	
	public static final int VOID = 1;
	public static final int CHAR = 2;
	public static final int SHORT = 3;
	public static final int INT = 4;
	public static final int LONG = 5;
	public static final int FLOAT = 6;
	public static final int DOUBLE = 7;
	public static final int SIGNED = 8;
	public static final int UNSIGNED = 9;
	public static final int STRUCT_OR_UNION_SPEC = 10;
	public static final int ENUM_SPEC = 11;
	public static final int TYPEDEF_NAME = 12;
	public static final int TYPEOF = 13;
	
	protected int type;
	
	public TypeSpecifier(){}
	
	public TypeSpecifier(int type) {
		this.type = type;
	}

	public TypeSpecifier(int lineNum, int pos, int type){
		setLineNum(lineNum);
		setPos(pos);
		this.type = type;
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public int getDecSpecType(){
		return DeclSpecUnit.TYPE_SPECIFIER;
	}

	public String toString(){
		String str = "";
		if(type == VOID)
			str += "void";
		else if(type == CHAR)
			str += "char";
		else if(type == SHORT)
			str += "short";
		else if(type == INT)
			str += "int";
		else if(type == LONG)
			str += "long";
		else if(type == FLOAT)
			str += "float";
		else if(type == DOUBLE)
			str += "double";
		else if(type == SIGNED)
			str += "signed";
		else if(type == UNSIGNED)
			str += "unsigned";
		else if(type == STRUCT_OR_UNION_SPEC)
			str += "struct_or_union";  // TODO change this
		else if(type == ENUM_SPEC)
			str += "enum_spec";   // TODO change this
		else if(type == TYPEDEF_NAME)
			str += "typedef_name";		 // TODO change this
		else if(type == TYPEOF)
			str += "typeof";  // TODO change this
		
		return str;
	}
}

package org.tamedragon.compilers.clang.abssyntree;

public class StorageClassSpecifiers extends Absyn implements DeclSpecUnit{
	public static final int AUTO = 1;
	public static final int REGISTER = 2;
	public static final int STATIC = 3;
	public static final int EXTERN = 4;
	public static final int TYPEDEF = 5;
	
	private int type;
	
	public StorageClassSpecifiers(int type) {
		this.type = type;
	}
	
	public StorageClassSpecifiers(int lineNum, int pos, int type) {
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
		return DeclSpecUnit.STORAGE_CLASS_SPECS;
	}
	
}

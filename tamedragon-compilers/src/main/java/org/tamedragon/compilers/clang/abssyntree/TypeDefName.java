package org.tamedragon.compilers.clang.abssyntree;

public class TypeDefName extends TypeSpecifier{

	private String name;
	
	public TypeDefName(){}
	
	public TypeDefName(int line, int pos, String name){
		super(TypeSpecifier.TYPEDEF_NAME);
		setLineNum(line);
		setPos(pos);
		this.name = name;
	}
	
	public TypeDefName(String name){
		super(TypeSpecifier.TYPEDEF_NAME);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

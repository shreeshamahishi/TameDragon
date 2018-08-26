package org.tamedragon.compilers.clang.abssyntree;

public class Enumerator  extends Absyn implements NodeItem{

	private String name;
	private ConstExpr initValue;   // should be a const value
	
	public Enumerator(){}
	
	public Enumerator(String name, ConstExpr initValue)
	{
		this.name = name;
		this.initValue = initValue;
	}

	public ConstExpr getInitValue() {
		return initValue;
	}

	public void setInitValue(ConstExpr initValue) {
		this.initValue = initValue;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getNodeItemType(){
		return NodeItem.ENUMERATOR;
	}
	
}

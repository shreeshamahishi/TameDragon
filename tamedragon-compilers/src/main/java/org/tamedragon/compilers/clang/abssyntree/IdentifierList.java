package org.tamedragon.compilers.clang.abssyntree;

public class IdentifierList extends Absyn {
	private String name;
	private IdentifierList identifierList;
	
	public IdentifierList(){}
	
	public IdentifierList(String name, IdentifierList identifierList)
	{
		this.name = name;
		this.identifierList = identifierList;
	}
	
	public void setIdentifierListNext(IdentifierList identifierListNext) {
		this.identifierList = identifierListNext;
	}
	
	public IdentifierList getNextInCollection(){
		return identifierList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

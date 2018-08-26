package org.tamedragon.compilers.clang.abssyntree;

public class StructDeclaration extends Absyn implements NodeItem {
	private SpecifierQualifierList specifierQualifierList;
	private StructDeclaratorList structDeclaratorList;
	
	public StructDeclaration(){}
	
	public StructDeclaration(SpecifierQualifierList specifierQualifierList,
			StructDeclaratorList structDeclaratorList)
	{
		this.specifierQualifierList = specifierQualifierList;
		this.structDeclaratorList = structDeclaratorList;
	}

	public SpecifierQualifierList getSpecifierQualifierList() {
		return specifierQualifierList;
	}

	public void setSpecifierQualifierList(
			SpecifierQualifierList specifierQualifierList) {
		this.specifierQualifierList = specifierQualifierList;
	}

	public StructDeclaratorList getStructDeclaratorList() {
		return structDeclaratorList;
	}

	public void setStructDeclaratorList(StructDeclaratorList structDeclaratorList) {
		this.structDeclaratorList = structDeclaratorList;
	}
	
	public int getNodeItemType(){
		return NodeItem.STRUCT_DECLARATION;
	}
	
}

package org.tamedragon.compilers.clang.abssyntree;

public class StructDeclaratorList extends Absyn implements NodeCollection{

	private StructDeclarator structDeclarator;
	private StructDeclaratorList structDeclaratorListNext;
	
	public StructDeclaratorList(){}
	
	public StructDeclaratorList(StructDeclarator structDeclarator,
			StructDeclaratorList structDeclaratorList)
	{
		this.structDeclarator = structDeclarator;
		this.structDeclaratorListNext = structDeclaratorList;
	}

	public StructDeclarator getStructDeclarator() {
		return structDeclarator;
	}

	public void setStructDeclarator(StructDeclarator structDeclarator) {
		this.structDeclarator = structDeclarator;
	}

	public StructDeclaratorList getStructDeclaratorListNext() {
		return structDeclaratorListNext;
	}

	public void setStructDeclaratorListNext(
			StructDeclaratorList structDeclaratorListNext) {
		this.structDeclaratorListNext = structDeclaratorListNext;
	}
	
	public int getNodeCollectionType(){
		return NodeCollection.STRUCT_DECLARATOR_LIST;
	}
	
	public NodeCollection getNextInCollection(){
		return structDeclaratorListNext;
	}
	
	public void setDeclarationListNext(NodeCollection newList){
		this.structDeclaratorListNext = (StructDeclaratorList)newList;
	}
}

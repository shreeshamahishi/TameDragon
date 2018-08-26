package org.tamedragon.compilers.clang.abssyntree;

public class StructDeclarationList extends Absyn implements NodeCollection{
	
	private StructDeclaration structDeclaration;
	private StructDeclarationList structDeclarationListNext;
	
	public StructDeclarationList(){}
	
	public StructDeclarationList(StructDeclaration structDeclaration,
						StructDeclarationList structDeclarationListNext)
	{
		this.structDeclaration = structDeclaration;
		this.structDeclarationListNext = structDeclarationListNext;
	}

	public StructDeclaration getStructDeclaration() {
		return structDeclaration;
	}

	public void setStructDeclaration(StructDeclaration structDeclaration) {
		this.structDeclaration = structDeclaration;
	}

	public StructDeclarationList getStructDeclarationListNext() {
		return structDeclarationListNext;
	}

	public void setStructDeclarationListNext(
			StructDeclarationList structDeclarationListNext) {
		this.structDeclarationListNext = structDeclarationListNext;
	}
	
	public int getNodeCollectionType(){
		return NodeCollection.STRUCT_DECLARATION_LIST;
	}
	
	public NodeCollection getNextInCollection(){
		return structDeclarationListNext;
	}
	
	public void setDeclarationListNext(NodeCollection newList){
		this.structDeclarationListNext = (StructDeclarationList)newList;
	}
	
}

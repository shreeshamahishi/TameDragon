package org.tamedragon.compilers.clang.abssyntree;

public class InitDeclaratorList extends Absyn implements NodeCollection{
	private InitDeclarator  initDeclarator;
	private InitDeclaratorList initDeclaratorListNext;
	
	public InitDeclaratorList(){}
	
	public InitDeclaratorList(InitDeclarator  initDeclarator,
					InitDeclaratorList initDeclaratorListNext)
	{
		this.initDeclarator = initDeclarator;
		this.initDeclaratorListNext = initDeclaratorListNext;
	}

	public InitDeclarator getInitDeclarator() {
		return initDeclarator;
	}

	public void setInitDeclarator(InitDeclarator initDeclarator) {
		this.initDeclarator = initDeclarator;
	}

	public InitDeclaratorList getInitDeclaratorListNext() {
		return initDeclaratorListNext;
	}

	public void setInitDeclaratorListNext(InitDeclaratorList initDeclaratorListNext) {
		this.initDeclaratorListNext = initDeclaratorListNext;
	}
	
	public int getNodeCollectionType(){
		return NodeCollection.INIT_DECLARATOR_LIST;
	}
	
	public NodeCollection getNextInCollection(){
		return initDeclaratorListNext;
	}
	
	public void setDeclarationListNext(NodeCollection newList){
		this.initDeclaratorListNext = (InitDeclaratorList)newList;;
	}
}

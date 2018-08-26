package org.tamedragon.compilers.clang.abssyntree;

public class InitializerList extends Absyn implements NodeCollection{
	private Initializer initializer;
	private InitializerList initializerList;
	
	public InitializerList(){}
	
	public InitializerList(Initializer initializer,
			InitializerList initializerList)
	{
		this.initializer = initializer;
		this.initializerList =initializerList;
	}
	
	public int getNodeCollectionType(){
		return NodeCollection.INITIALIZER_LIST;
	}
	
	public NodeCollection getNextInCollection(){
		return initializerList;
	}

	public Initializer getInitializer() {
		return initializer;
	}

	public void setInitializer(Initializer initializer) {
		this.initializer = initializer;
	}

	public InitializerList getInitializerList() {
		return initializerList;
	}

	public void setInitializerList(InitializerList initializerList) {
		this.initializerList = initializerList;
	}
	
	public void setDeclarationListNext(NodeCollection newList){
		this.initializerList = (InitializerList)newList;;
	}
}

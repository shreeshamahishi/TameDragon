package org.tamedragon.compilers.clang.abssyntree;

public class ParamList extends Absyn implements NodeCollection{

	private ParamDeclaration paramDeclaration;
	private ParamList paramList;
	
	public ParamList(){}
	
	public ParamList(ParamDeclaration paramDeclaration, ParamList paramList)
	{
		this.paramDeclaration = paramDeclaration;
		this.paramList = paramList;
	}
	
	public int getNodeCollectionType(){
		return NodeCollection.PARAMETER_LIST;
	}
	
	public NodeCollection getNextInCollection(){
		return paramList;
	}

	public ParamDeclaration getParamDeclaration() {
		return paramDeclaration;
	}

	public void setParamDeclaration(ParamDeclaration paramDeclaration) {
		this.paramDeclaration = paramDeclaration;
	}

	public ParamList getParamList() {
		return paramList;
	}

	public void setParamList(ParamList paramList) {
		this.paramList = paramList;
	}
	
	public void setDeclarationListNext(NodeCollection newList){
		this.paramList = (ParamList)newList;
	}
}

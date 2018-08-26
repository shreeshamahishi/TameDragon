package org.tamedragon.compilers.clang.abssyntree;

public class EnumList extends Absyn implements NodeCollection{

	private Enumerator enumarator;
	private EnumList enumList;
	
	public EnumList(){}
	
	public EnumList(Enumerator enumarator, EnumList enumList)
	{
		this.enumarator = enumarator;
		this.enumList = enumList;
	}

	public Enumerator getEnumarator() {
		return enumarator;
	}

	public void setEnumarator(Enumerator enumarator) {
		this.enumarator = enumarator;
	}

	public EnumList getEnumList() {
		return enumList;
	}

	public void setEnumList(EnumList enumList) {
		this.enumList = enumList;
	}
	
	public int getNodeCollectionType(){
		return NodeCollection.ENUMERATOR_LIST;
	}
	
	public NodeCollection getNextInCollection(){
		return enumList;
	}
	
	public void setDeclarationListNext(NodeCollection newList){
		this.enumList = (EnumList)newList;;
	}	
}

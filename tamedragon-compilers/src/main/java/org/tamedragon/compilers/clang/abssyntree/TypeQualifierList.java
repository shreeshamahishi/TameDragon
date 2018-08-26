package org.tamedragon.compilers.clang.abssyntree;

public class TypeQualifierList extends Absyn implements NodeCollection{

	private TypeQualifier typeQualifier;
	private TypeQualifierList typeQualifierList;
	
	public TypeQualifierList(){}
	
	public TypeQualifierList(TypeQualifier typeQualifier, TypeQualifierList typeQualifierList) {
		this.typeQualifier = typeQualifier;
		this.typeQualifierList = typeQualifierList;
	}
	
	public int getNodeCollectionType(){
		return NodeCollection.TYPE_QUALIFIER_LIST;
	}
	
	public NodeCollection getNextInCollection(){
		return typeQualifierList;
	}

	public TypeQualifier getTypeQualifier() {
		return typeQualifier;
	}

	public void setTypeQualifier(TypeQualifier typeQualifier) {
		this.typeQualifier = typeQualifier;
	}

	public TypeQualifierList getTypeQualifierList() {
		return typeQualifierList;
	}

	public void setTypeQualifierList(TypeQualifierList typeQualifierList) {
		this.typeQualifierList = typeQualifierList;
	}
	
	public void setDeclarationListNext(NodeCollection newList){
		this.typeQualifierList = (TypeQualifierList)newList;
	}
	
	public String toString(){
		String str = "";
		str += typeQualifier.toString();
		if(typeQualifierList != null)
			str += typeQualifierList.toString();
		
		return str;
	}
	
}

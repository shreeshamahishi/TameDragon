package org.tamedragon.compilers.clang.abssyntree;

public class SpecifierQualifierList  extends Absyn  implements SpecifierListType{

	private TypeSpecifier typeSpecifier;
	private TypeQualifier typeQualifier;
	private SpecifierQualifierList specifierQualifierListNext;
	
	public SpecifierQualifierList(){}
	
	public SpecifierQualifierList(TypeSpecifier typeSpecifier,
			TypeQualifier typeQualifier,
			SpecifierQualifierList specifierQualifierListNext)
	{
		this.typeSpecifier = typeSpecifier;
		this.typeQualifier = typeQualifier;
		this.specifierQualifierListNext = specifierQualifierListNext;
	}

	public SpecifierQualifierList getSpecifierQualifierListNext() {
		return specifierQualifierListNext;
	}

	public void setSpecifierQualifierListNext(
			SpecifierQualifierList specifierQualifierListNext) {
		this.specifierQualifierListNext = specifierQualifierListNext;
	}

	public TypeQualifier getTypeQualifier() {
		return typeQualifier;
	}

	public void setTypeQualifier(TypeQualifier typeQualifier) {
		this.typeQualifier = typeQualifier;
	}

	public TypeSpecifier getTypeSpecifier() {
		return typeSpecifier;
	}

	public void setTypeSpecifier(TypeSpecifier typeSpecifier) {
		this.typeSpecifier = typeSpecifier;
	}
	
	public StorageClassSpecifiers getStorageClassSpecifiers(){
		// Does not have any storage class specifiers
		return null;
	}
	
	public int getSpecifierListType(){
		return SpecifierListType.SPECIFIER_QUALIFIER_LIST;
	}
	
	public SpecifierListType getNext(){
		return getSpecifierQualifierListNext();
	}
	
	public String toString(){
		String str = "";
		if(typeSpecifier != null)
			str += typeSpecifier.toString();
		if(typeQualifier != null)
			str += typeQualifier.toString();

		if(specifierQualifierListNext != null)
			str += " " + specifierQualifierListNext.toString();
		
		return str;
	}
}

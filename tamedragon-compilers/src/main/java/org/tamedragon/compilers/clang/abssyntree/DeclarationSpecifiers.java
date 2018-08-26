package org.tamedragon.compilers.clang.abssyntree;

public class DeclarationSpecifiers extends Absyn implements SpecifierListType {

	private StorageClassSpecifiers storageClassSpecifiers;
	private DeclarationSpecifiers declarationSpecifiersNext;
	private TypeSpecifier typeSpecifier;
	private TypeQualifier typeQualifier;
	
	public DeclarationSpecifiers(){}
	
	public DeclarationSpecifiers(TypeSpecifier typeSpecifier, TypeQualifier typeQualifier, 
			StorageClassSpecifiers storageClassSpecifiers,
			DeclarationSpecifiers declarationSpecifiersNext)
	{
		this.typeSpecifier = typeSpecifier;
		this.typeQualifier = typeQualifier;
		this.storageClassSpecifiers = storageClassSpecifiers;
		
		this.declarationSpecifiersNext = declarationSpecifiersNext;
	}

	public DeclarationSpecifiers getDeclarationSpecifiersNext() {
		return declarationSpecifiersNext;
	}

	public void setDeclarationSpecifiersNext(
			DeclarationSpecifiers declarationSpecifiersNext) {
		this.declarationSpecifiersNext = declarationSpecifiersNext;
	}

	public StorageClassSpecifiers getStorageClassSpecifiers() {
		return storageClassSpecifiers;
	}

	public void setStorageClassSpecifiers(
			StorageClassSpecifiers storageClassSpecifiers) {
		this.storageClassSpecifiers = storageClassSpecifiers;
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
	
	public int getSpecifierListType(){
		return SpecifierListType.DECLARATION_SPECIFIERS;
	}
	
	public SpecifierListType getNext(){
		return getDeclarationSpecifiersNext();
	}
}

package org.tamedragon.compilers.clang.abssyntree;

public interface SpecifierListType {
	public static final int DECLARATION_SPECIFIERS = 0;
	public static final int SPECIFIER_QUALIFIER_LIST = 1;
	
	public int getSpecifierListType();
	public TypeSpecifier getTypeSpecifier();
	public TypeQualifier getTypeQualifier();
	public StorageClassSpecifiers getStorageClassSpecifiers();
	public SpecifierListType getNext();

}

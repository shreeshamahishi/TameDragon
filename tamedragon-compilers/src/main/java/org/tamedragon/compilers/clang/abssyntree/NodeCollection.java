package org.tamedragon.compilers.clang.abssyntree;

public interface NodeCollection {
	
	public static final int STRUCT_DECLARATION_LIST = 0;
	public static final int INIT_DECLARATOR_LIST = 1;
	public static final int STRUCT_DECLARATOR_LIST = 2;
	public static final int ENUMERATOR_LIST = 3;
	public static final int TYPE_QUALIFIER_LIST = 4;
	public static final int PARAMETER_LIST = 5;
	public static final int INITIALIZER_LIST = 6;
	public static final int ARG_EXPR_LIST = 7;
	public static final int ROOT_EXPR_LIST = 8;
	public static final int BLOCK_ITEM_LIST = 9;
	
	public int getNodeCollectionType();
	public NodeCollection getNextInCollection();
	public void setDeclarationListNext(NodeCollection newList);

}

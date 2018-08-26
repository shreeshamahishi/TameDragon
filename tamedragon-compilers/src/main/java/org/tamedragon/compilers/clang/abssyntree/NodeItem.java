package org.tamedragon.compilers.clang.abssyntree;

/**
 * Represents an item that represents a collection in a LL(*) production. Not relevant to LR parsing
 * @author shreesha123
 *
 */
public interface NodeItem {
	
	public static final int STRUCT_DECLARATION = 0;
	public static final int INIT_DECLARATOR = 1;
	public static final int STRUCT_DECLARATOR = 2;
	public static final int ENUMERATOR = 3;
	public static final int TYPE_QUALIFIER = 4;
	public static final int PARAM_DECLARATION = 5;
	public static final int INITIALIZER = 6;
	public static final int ARG_EXPR_LIST = 7;
	public static final int ASSIGNMENT_EXPR = 8;
	public static final int BLOCK_ITEM = 9;
	
	public int getNodeItemType();
}

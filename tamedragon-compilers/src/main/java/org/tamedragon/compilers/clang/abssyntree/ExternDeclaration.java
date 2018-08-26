package org.tamedragon.compilers.clang.abssyntree;

abstract public class ExternDeclaration extends Absyn {

	public static final short FUNCTION_DEF = 1;
	public static final short DECLARATION = 2;
	public static final short INCLUDE_DIRECTIVE = 3;
	
	abstract public short getType();
}

package org.tamedragon.compilers.clang.semantics;


public interface DeclaratorChainElement{
		
	public static final int DECL_CHAIN_FUNCTION = 0;
	public static final int ARRAY = 1;
	public static final int POINTER = 2;
	
	public String setNextElementInDeclaratorChain(DeclaratorChainElement dce);
	public int getDeclaratorChainElementType();
}

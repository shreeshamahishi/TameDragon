package org.tamedragon.assemblyabstractions.constructs;

/**
 * This is an Interface that holds the information needed to create LLVM's instructions, Function Arguments, Global Values etc.
 * This information is gathered during the semantic analysis of the "C code".
 * @author ipsg
 *
 */
public interface IRTree {
	
	public String getDescription();
}

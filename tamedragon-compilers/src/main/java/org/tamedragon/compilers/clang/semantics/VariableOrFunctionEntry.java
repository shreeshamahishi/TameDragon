package org.tamedragon.compilers.clang.semantics;

import org.tamedragon.compilers.clang.SourceLocation;

public interface VariableOrFunctionEntry{
	public static final int FUNCTION = 0;
	public static final int VARIABLE = 1;	
	
	public int getCategory();
	public SourceLocation getSourceLocation();
	public String getDescription();
}
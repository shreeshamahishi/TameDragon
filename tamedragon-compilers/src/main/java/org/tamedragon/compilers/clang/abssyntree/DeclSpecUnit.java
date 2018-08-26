package org.tamedragon.compilers.clang.abssyntree;

public interface DeclSpecUnit {
	
	public static final int STORAGE_CLASS_SPECS = 0;
	public static final int TYPE_QUALIFIER = 1;
	public static final int TYPE_SPECIFIER = 2;
	
	public int getDecSpecType();
}

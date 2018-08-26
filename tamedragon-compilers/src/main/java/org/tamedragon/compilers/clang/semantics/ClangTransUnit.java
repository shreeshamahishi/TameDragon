package org.tamedragon.compilers.clang.semantics;

import java.util.Vector;

import org.tamedragon.assemblyabstractions.constructs.IRTree;

public interface ClangTransUnit {
	public IRTree getExternalDecl();
	public Vector<String> dataDeclarations();
}
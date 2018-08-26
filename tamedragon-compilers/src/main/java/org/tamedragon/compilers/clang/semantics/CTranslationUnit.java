package org.tamedragon.compilers.clang.semantics;

import java.util.Vector;

import org.tamedragon.assemblyabstractions.constructs.AssemType;

public interface CTranslationUnit {
	public AssemType getExternalDeclTree();
	public Vector<String> dataDeclarations();
}

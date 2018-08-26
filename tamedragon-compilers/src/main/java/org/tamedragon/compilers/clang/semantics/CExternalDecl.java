package org.tamedragon.compilers.clang.semantics;

import java.util.Vector;

import org.tamedragon.assemblyabstractions.constructs.AssemType;

public class CExternalDecl implements CTranslationUnit{
	private AssemType assemType;
	private Vector<String> dataDeclarations;

	public AssemType getExternalDeclTree() {
		return assemType;
	}

	public void setExternalDeclTree(AssemType assemType) {
		this.assemType = assemType;
	}
	
	public Vector<String> dataDeclarations(){
		return dataDeclarations;
	}
}

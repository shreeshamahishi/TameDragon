package org.tamedragon.compilers.clang.semantics;

import java.util.Vector;

import org.tamedragon.assemblyabstractions.constructs.IRTree;


public class ClangExternalDecl implements ClangTransUnit{
	private IRTree irTree;
	public void setIrTree(IRTree irTree) {
		this.irTree = irTree;
	}

	private Vector<String> dataDeclarations;

	public Vector<String> dataDeclarations(){
		return dataDeclarations;
	}

	@Override
	public IRTree getExternalDecl() {
		return irTree;
	}
}

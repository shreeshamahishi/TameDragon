package org.tamedragon.compilers.clang.abssyntree;

public class FunctionDef extends ExternDeclaration{
	private DeclarationSpecifiers declSpecifiers;
	private Declarator declarator;
	private CompoundStatement compoundStmt;
	
	public FunctionDef(){}
	
	public FunctionDef(DeclarationSpecifiers declSpecifiers,Declarator declarator, CompoundStatement compoundStmt) {
		this.declSpecifiers = declSpecifiers;
		this.declarator = declarator;
		this.compoundStmt = compoundStmt;
	}
	
	public short getType() {
		return ExternDeclaration.FUNCTION_DEF;
	}

	public CompoundStatement getCompoundStmt() {
		return compoundStmt;
	}

	public void setCompoundStmt(CompoundStatement compoundStmt) {
		this.compoundStmt = compoundStmt;
	}

	public Declarator getDeclarator() {
		return declarator;
	}

	public void setDeclarator(Declarator declarator) {
		this.declarator = declarator;
	}

	public DeclarationSpecifiers getDeclSpecifiers() {
		return declSpecifiers;
	}

	public void setDeclSpecifiers(DeclarationSpecifiers declSpecifiers) {
		this.declSpecifiers = declSpecifiers;
	}
}

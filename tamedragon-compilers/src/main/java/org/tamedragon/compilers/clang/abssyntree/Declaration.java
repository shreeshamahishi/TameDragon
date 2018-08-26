package org.tamedragon.compilers.clang.abssyntree;

public class Declaration extends ExternDeclaration{
	
	private DeclarationSpecifiers declSpecifiers; 
	private InitDeclaratorList initDeclaratorList;
	private boolean isTypeDefDeclaration;
	
	public Declaration(){}
	
	public Declaration(DeclarationSpecifiers declSpecifiers,
			InitDeclaratorList initDeclaratorList, boolean typdefFlag)
	{
		this.declSpecifiers = declSpecifiers;
		this.initDeclaratorList = initDeclaratorList;
		this.isTypeDefDeclaration = typdefFlag;
	}
	
	public short getType()
	{
		return ExternDeclaration.DECLARATION;
	}

	public DeclarationSpecifiers getDeclSpecifiers() {
		return declSpecifiers;
	}

	public void setDeclSpecifiers(DeclarationSpecifiers declSpecifiers) {
		this.declSpecifiers = declSpecifiers;
	}

	public InitDeclaratorList getInitDeclaratorList() {
		return initDeclaratorList;
	}

	public void setInitDeclaratorList(InitDeclaratorList initDeclaratorList) {
		this.initDeclaratorList = initDeclaratorList;
	}

	public boolean isTypeDefDeclaration() {
		return isTypeDefDeclaration;
	}

	public void setTypeDefDeclaration(boolean isTypeDefDeclaration) {
		this.isTypeDefDeclaration = isTypeDefDeclaration;
	}
}

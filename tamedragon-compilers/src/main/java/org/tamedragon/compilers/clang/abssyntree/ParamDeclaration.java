package org.tamedragon.compilers.clang.abssyntree;

public class ParamDeclaration extends Absyn implements NodeItem {

	private DeclarationSpecifiers declarationSpecifiers;
	private Declarator declarator;
	private AbstractDeclarator abstractDeclarator;
	
	public ParamDeclaration(){}
	
	public ParamDeclaration(DeclarationSpecifiers declarationSpecifiers,
			Declarator declarator, AbstractDeclarator abstractDeclarator)
	{
		this.declarationSpecifiers = declarationSpecifiers;
		this.declarator =declarator;
		this.abstractDeclarator = abstractDeclarator;
	}

	public AbstractDeclarator getAbstractDeclarator() {
		return abstractDeclarator;
	}

	public void setAbstractDeclarator(AbstractDeclarator abstractDeclarator) {
		this.abstractDeclarator = abstractDeclarator;
	}

	public DeclarationSpecifiers getDeclarationSpecifiers() {
		return declarationSpecifiers;
	}

	public void setDeclarationSpecifiers(DeclarationSpecifiers declarationSpecifiers) {
		this.declarationSpecifiers = declarationSpecifiers;
	}

	public Declarator getDeclarator() {
		return declarator;
	}

	public void setDeclarator(Declarator declarator) {
		this.declarator = declarator;
	}
	
	public int getNodeItemType(){
		return NodeItem.PARAM_DECLARATION;
	}
}

package org.tamedragon.compilers.clang.abssyntree;

public class InitDeclarator extends Absyn implements NodeItem {

	private Declarator declarator;
	private Initializer initializer;
	
	public InitDeclarator(){}
	
	public InitDeclarator(Declarator declarator,Initializer initializer)
	{
		this.declarator = declarator;
		this.initializer = initializer;
	}

	public Declarator getDeclarator() {
		return declarator;
	}

	public void setDeclarator(Declarator declarator) {
		this.declarator = declarator;
	}

	public Initializer getInitializer() {
		return initializer;
	}

	public void setInitializer(Initializer initializer) {
		this.initializer = initializer;
	}
	
	public int getNodeItemType(){
		return NodeItem.INIT_DECLARATOR;
	}
}

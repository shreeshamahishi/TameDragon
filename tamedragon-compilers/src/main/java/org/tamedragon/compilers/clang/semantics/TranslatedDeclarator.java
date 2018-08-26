package org.tamedragon.compilers.clang.semantics;

import org.tamedragon.compilers.clang.abssyntree.ParamTypeList;

class TranslatedDeclarator{	
	private String name;
	private DeclaratorChainElement declaratorChainElement;
	private ParamTypeList paramTypeList;

	public TranslatedDeclarator(){}

	public TranslatedDeclarator(DeclaratorChainElement declaratorChainElement, String name){
		this.declaratorChainElement = declaratorChainElement; this.name = name;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public DeclaratorChainElement getDeclaratorChainElement() {
		return declaratorChainElement;
	}

	public void setDeclaratorChainElement( DeclaratorChainElement declaratorChainElement) {
		this.declaratorChainElement = declaratorChainElement;
	}

	public ParamTypeList getParamTypeList() {
		return paramTypeList;
	}

	public void setParamTypeList(ParamTypeList paramTypeList) {
		this.paramTypeList = paramTypeList;
	}			
}

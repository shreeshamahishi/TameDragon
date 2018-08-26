package org.tamedragon.compilers.clang.abssyntree;

import java.util.Stack;

public class TranslationUnit extends Absyn{

	private ExternDeclaration externDec;
	private TranslationUnit translationUnitNext;
	private Stack<String> errors;

	public TranslationUnit(){}
	
	public TranslationUnit(ExternDeclaration externDec, TranslationUnit translationUnit) {
		this.externDec = externDec;
		this.translationUnitNext = translationUnit;
	}

	public ExternDeclaration getExternDec() { return externDec; }

	public void setExternDec(ExternDeclaration externDec) { this.externDec = externDec; }

	public TranslationUnit getTranslationUnitNext() { return translationUnitNext; }

	public void setTranslationUnitNext(TranslationUnit translationUnitNext) { 
		this.translationUnitNext = translationUnitNext;
	}

	public Stack<String> getErrors() {
		return errors;
	}

	public void setErrors(Stack<String> errors) {
		this.errors = errors;
	}
}

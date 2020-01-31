package org.tamedragon.compilers.clang.preprocessor;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

public class Warning extends Absyn implements PreprocessorDirective {
	
	private TokenSequence tokenSequence;
	
	@Override
	public int getPreprocessorUnitType() {
		return PreprocessorUnit.WARNING;
	}

	@Override
	public StringBuffer process(String sourceFilePath, Graph<String, DefaultEdge> dependenciesDag) {
		return new StringBuffer(tokenSequence.toString());
	}

	public TokenSequence getTokenSequence() {
		return tokenSequence;
	}

	public void setTokenSequence(TokenSequence tokenSequence) {
		this.tokenSequence = tokenSequence;
	}
}

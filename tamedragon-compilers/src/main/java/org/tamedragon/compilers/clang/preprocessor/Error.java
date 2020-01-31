package org.tamedragon.compilers.clang.preprocessor;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

public class Error extends Absyn implements PreprocessorDirective {
	private TokenSequence tokenSequence;
	
	private String sourceFilePath;
	
	public String getSourceFilePath() {
		return sourceFilePath;
	}

	public void setSourceFilePath(String sourceFilePath) {
		this.sourceFilePath = sourceFilePath;
	}

	public TokenSequence getTokenSequence() {
		return tokenSequence;
	}

	public void setTokenSequence(TokenSequence tokenSequence) {
		this.tokenSequence = tokenSequence;
	}	
	
	public int getPreprocessorUnitType(){
		return PreprocessorUnit.ERROR;
	}
	
	@Override
	public StringBuffer process(String sourceFilePath, Graph<String, DefaultEdge> dependenciesDag){
		return new StringBuffer(tokenSequence.toString());
	}
}

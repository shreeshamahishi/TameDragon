package org.tamedragon.compilers.clang.preprocessor;

public class Pragma extends Absyn implements PreprocessorDirective {

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
		return PreprocessorUnit.PRAGMA;
	}
	
	public StringBuffer process(){
		return null;
	}
	
}

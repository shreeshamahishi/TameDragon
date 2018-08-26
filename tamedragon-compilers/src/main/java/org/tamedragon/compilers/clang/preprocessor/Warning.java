package org.tamedragon.compilers.clang.preprocessor;

public class Warning extends Absyn implements PreprocessorDirective {
	
	private TokenSequence tokenSequence;
	
	private String sourceFilePath;

	@Override
	public int getPreprocessorUnitType() {
		return PreprocessorUnit.WARNING;
	}

	@Override
	public StringBuffer process() {
		return new StringBuffer(tokenSequence.toString());
	}

	@Override
	public void setSourceFilePath(String path) {
		this.sourceFilePath = path;
	}

	public TokenSequence getTokenSequence() {
		return tokenSequence;
	}

	public void setTokenSequence(TokenSequence tokenSequence) {
		this.tokenSequence = tokenSequence;
	}

	public String getSourceFilePath() {
		return sourceFilePath;
	}

}

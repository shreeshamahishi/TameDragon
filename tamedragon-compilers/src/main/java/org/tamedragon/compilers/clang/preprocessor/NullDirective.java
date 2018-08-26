package org.tamedragon.compilers.clang.preprocessor;

public class NullDirective extends Absyn implements PreprocessorDirective {

	private String sourceFilePath;
	
	public String getSourceFilePath() {
		return sourceFilePath;
	}

	public void setSourceFilePath(String sourceFilePath) {
		this.sourceFilePath = sourceFilePath;
	}
	
	public int getPreprocessorUnitType(){
		return PreprocessorUnit.NULL_DIRECTIVE;
	}
	
	public StringBuffer process(){
		return null;
	}
}

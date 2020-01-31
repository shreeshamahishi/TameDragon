package org.tamedragon.compilers.clang.preprocessor;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

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
	
	@Override
	public StringBuffer process(String sourceFilePath, Graph<String, DefaultEdge> dependenciesDag){
		return null;
	}
}

package org.tamedragon.compilers.clang.preprocessor;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

public class Line extends Absyn implements PreprocessorDirective {
	
	private String lineNumStr;
	private String fileName;	
	
	public Line(){}
	
	private String sourceFilePath;
	
	public String getSourceFilePath() {
		return sourceFilePath;
	}

	public void setSourceFilePath(String sourceFilePath) {
		this.sourceFilePath = sourceFilePath;
	}
	
	public Line(int thisLineNum, String lineNumStr){
		setLineNum(thisLineNum);		
		this.lineNumStr = lineNumStr;
	}
	
	public String getLineNumStr() {
		return lineNumStr;
	}
	
	public void setLineNumStr(String lineNumStr) {
		this.lineNumStr = lineNumStr;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public int getPreprocessorUnitType(){
		return PreprocessorUnit.LINE;
	}
	
	@Override
	public StringBuffer process(String sourceFilePath, Graph<String, DefaultEdge> dependenciesDag){
		return null;
	}
}

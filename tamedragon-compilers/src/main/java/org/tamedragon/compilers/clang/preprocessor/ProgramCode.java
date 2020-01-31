package org.tamedragon.compilers.clang.preprocessor;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

public class ProgramCode extends Absyn implements PreprocessorUnit{

	private List<String> tokens;
	
	public ProgramCode(){
		tokens = new ArrayList<String>();
	}

	public void addToken(String token){
		tokens.add(token);		
	}

	public StringBuffer getCodeText() {
		StringBuffer codeTextStrBuf = new StringBuffer();
		for(String token: tokens){
			codeTextStrBuf.append(token);
		}

		return codeTextStrBuf;
	}

	public int getPreprocessorUnitType(){
		return PreprocessorUnit.PROGRAM_CODE;
	}

	@Override
	public StringBuffer process(String sourceFilePath, Graph<String, DefaultEdge> dependenciesDag){
		StringBuffer sb = Definition.process(tokens);
		 return sb;
	}
	
	public String toString(){
		return getCodeText().toString();
	}
}

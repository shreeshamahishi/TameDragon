package org.tamedragon.compilers.clang.preprocessor;

import java.util.ArrayList;
import java.util.List;

public class ProgramCode extends Absyn implements PreprocessorUnit{

	private List<String> tokens;
	private String sourceFilePath;

	public String getSourceFilePath() {
		return sourceFilePath;
	}

	public void setSourceFilePath(String sourceFilePath) {
		this.sourceFilePath = sourceFilePath;
	}

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

	public StringBuffer process(){
		StringBuffer sb = Definition.process(tokens);
		 return sb;
	}
	
	public String toString(){
		return getCodeText().toString();
	}
}

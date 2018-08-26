package org.tamedragon.compilers.clang.abssyntree;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.tamedragon.compilers.clang.CLangUtils;
import org.tamedragon.compilers.clang.CompilerSettings;
import org.tamedragon.compilers.clang.preprocessor.PreprocessorMain;
import org.tamedragon.compilers.clang.semantics.ClangTransUnit;
import org.tamedragon.compilers.clang.semantics.Semantic;

public class IncludeStatement extends Statement {
	
	public static final int LIB = 0;
	public static final int LOCAL = 1;

	public IncludeStatement(){}

	private int includeType;
	private String fileName;
	private FileNameLib fileNameLib;

	public IncludeStatement(int lineNum, String fileName, int includeType){
		setLineNum(lineNum);
		this.includeType = includeType;
		if(fileName != null && fileName.length() >= 2){
			fileName = fileName.substring(1, fileName.length() -1);
		}
		this.fileName = fileName;
	}

	public IncludeStatement(int lineNum, FileNameLib fnl, int includeType){
		setLineNum(lineNum);
		this.includeType = includeType;
		this.fileNameLib = fnl;
	}

	public int getIncludeType() {
		return includeType;
	}

	public void setIncludeType(int includeType) {
		this.includeType = includeType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public FileNameLib getFileNameLib() {
		return fileNameLib;
	}

	public void setFileNameLib(FileNameLib fileNameLib) {
		this.fileNameLib = fileNameLib;
	}

	public List<ClangTransUnit> process(Properties properties){
		
		// GET THE INCLUDE PATH FOR SYSTEM LIBRARIES AND THE PROJECT PATH
		String includePath = CompilerSettings.getInstance().getIncludePath();
		String projectPath = CompilerSettings.getInstance().getProjectPath();
		
		String includeFilePath = "";
		if(includeType == LIB)
			includeFilePath = includePath + File.separator + fileNameLib;		
		else
			includeFilePath = projectPath + File.separator + fileName;			

		// Run the pre-processor			
		PreprocessorMain ppMain = new PreprocessorMain(includeFilePath);			
		InputStream sourceFileInputStream = ppMain.process(false); 
		
		// Translate to abstract syntax tree
		TranslationUnit translationUnit = CLangUtils.getTranslationByLLParsing(sourceFileInputStream);

		// Pass through semantic analyzer and translate to assembly tree
		Semantic semantic = new Semantic(properties, includeFilePath, CompilerSettings.getInstance().getInstanceCompilationContext());
		List<ClangTransUnit> translationUnits =  semantic.translateAbstractTree(translationUnit);    	 
		
		return translationUnits;
	}

	public String toString(){
		if(includeType == LIB)
			return fileNameLib.toString();

		return fileName;
	}
}

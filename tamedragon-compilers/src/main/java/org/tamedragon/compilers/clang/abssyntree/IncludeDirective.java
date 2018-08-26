package org.tamedragon.compilers.clang.abssyntree;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tamedragon.common.llvmir.types.CompilationContext;
import org.tamedragon.compilers.clang.CLangUtils;
import org.tamedragon.compilers.clang.CompilerSettings;
import org.tamedragon.compilers.clang.semantics.ClangTransUnit;
import org.tamedragon.compilers.clang.semantics.Semantic;

public class IncludeDirective extends ExternDeclaration {

	public static final int LIB = 0;
	public static final int LOCAL = 1;

	public IncludeDirective(){}

	private int includeType;
	private String fileName;
	private FileNameLib fileNameLib;

	private HashMap<String, List<InputStream>> includesMapInIncludedFile;

	private static final String UNSUPPORTED_BYTE_FORMAT_IN_INCLUDE_FILE = "Fatal: Include file has unsupported encoding.";

	private static final Logger LOG = LoggerFactory.getLogger(IncludeDirective.class);

	public IncludeDirective(int lineNum, String fileName, int includeType){
		setLineNum(lineNum);
		this.includeType = includeType;
		if(fileName != null && fileName.length() >= 2){
			fileName = fileName.substring(1, fileName.length() -1);
		}
		this.fileName = fileName;
	}

	public IncludeDirective(int lineNum, FileNameLib fnl, int includeType){
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

	public void setIncludesMap(HashMap<String, List<InputStream>> includesMapInIncludedFile){
		this.includesMapInIncludedFile = includesMapInIncludedFile;
	}

	public List<ClangTransUnit>  process(Properties properties){

		// GET THE INCLUDE PATH FOR SYSTEM LIBRARIES AND THE PROJECT PATH
		String includePath = CompilerSettings.getInstance().getIncludePath();
		String projectPath = CompilerSettings.getInstance().getProjectPath();

		String includeFilePath = "";
		String includeStr = "";
		if(includeType == LIB){
			includeStr = "# " + fileNameLib + " #";
			includeFilePath = includePath + File.separator + fileNameLib;
		}
		else{
			// TODO Do we need to process this each time?
			includeStr = "# \"" + fileName + "\" #";
			includeFilePath = projectPath + File.separator + fileName;
		}

		InputStream preprocesedIncludedFileStream = null;		
		List<InputStream> includedFilesPreprocessedStream = null;
		if(includesMapInIncludedFile != null)
			includedFilesPreprocessedStream = includesMapInIncludedFile.get(includeStr);
		if(includedFilesPreprocessedStream == null || includedFilesPreprocessedStream.size() == 0){
			// TODO: Error: can this happen?
			return null;

		}
		else{
			preprocesedIncludedFileStream = includedFilesPreprocessedStream.remove(0);
		}

		StringBuffer sbIncludedFile = CLangUtils.getStringBufferFromInputStream(preprocesedIncludedFileStream);
		if(sbIncludedFile.toString().trim().length() == 0){
			// No need to process this included file, macro replacements have probably reduced it to zero size
			return null;
		}

		// Get back the input stream
		try{
			preprocesedIncludedFileStream = new ByteArrayInputStream(sbIncludedFile.toString().getBytes("utf-8")) ;
		}
		catch(Exception e){
			assert false : UNSUPPORTED_BYTE_FORMAT_IN_INCLUDE_FILE;
			LOG.error(UNSUPPORTED_BYTE_FORMAT_IN_INCLUDE_FILE);
			return null;
		}

		// Translate to abstract syntax tree
		TranslationUnit translationUnit = CLangUtils.getTranslationByLLParsing(preprocesedIncludedFileStream);

		// Pass through semantic analyzer and translate to assembly tree
		CompilationContext compilationContext = CompilerSettings.getInstance().getInstanceCompilationContext();
		Semantic semanticAnalyzer = new Semantic(properties, includeFilePath, compilationContext); 	    
		List<ClangTransUnit> translationUnitsOfIncludedFile =  semanticAnalyzer.translateAbstractTree(translationUnit);  

		return translationUnitsOfIncludedFile;
	}

	public String toString(){
		if(includeType == LIB)
			return fileNameLib.toString();

		return fileName;
	}

	@Override
	public short getType() {
		return ExternDeclaration.INCLUDE_DIRECTIVE;
	}
}

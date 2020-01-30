package org.tamedragon.compilers.clang.preprocessor;


import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.tamedragon.compilers.clang.CompilerSettings;

public class IncludeDirective extends Absyn implements PreprocessorDirective {

	public static final int LIB = 0;
	public static final int LOCAL = 1;

	public static final String DEFAULT_NEWLINE = "\r\n";
	
	public IncludeDirective(){}

	private int includeType;
	private String fileName;
	private FileNameLib fileNameLib;
	
	private String sourceFilePath;
	
	public String getSourceFilePath() {
		return sourceFilePath;
	}

	public void setSourceFilePath(String sourceFilePath) {
		this.sourceFilePath = sourceFilePath;
	}

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

	public int getPreprocessorUnitType(){
		return PreprocessorUnit.INCLUDE_DIRECTIVE;
	}

	public StringBuffer process(){
		StringBuffer sb = new StringBuffer();
		CompilerSettings compilerSettings = CompilerSettings.getInstance();
		String newLine = compilerSettings.getInstanceNewLine();
		
		String projectRootPath = CompilerSettings.getInstance().getProjectRoot();
		String projectPath = compilerSettings.getProjectPath();
		String includePath = compilerSettings.getIncludePath();
		
		String newIncludePath = compilerSettings.getNewIncludePath();
		
		if(newLine == null){
			newLine = DEFAULT_NEWLINE;
		}
		
		String modifiedIncludeLine = newLine;
		String includeFilePath = "";
		if(includeType == LIB){
			modifiedIncludeLine = "# " + fileNameLib.toString() + " #" + newLine;
			
			// If path is explicitly given then we don't need the modified include path
			String libFileName = fileNameLib.getLibraryFileName();
			if(libFileName.contains("/") || libFileName.contains("\\")
					|| newIncludePath == null)
				includeFilePath = includePath + fileNameLib;
			else
				includeFilePath = newIncludePath + fileNameLib;
			
			// Update the include path, based on current include path location
			if(libFileName.contains("/")){
				newIncludePath = includePath + fileNameLib.getLibraryFileName().substring(0, fileNameLib.getLibraryFileName().lastIndexOf('/'));
				//compilerSettings.setNewIncludePath(newIncludePath);
			}
		}
		else{
			modifiedIncludeLine = "# \"" + fileName + "\" #" + newLine;
			includeFilePath = projectRootPath + projectPath + fileName;
		}
		
		sb.append(modifiedIncludeLine);
		
		
		
		// Process the included header file too
		PreprocessorMain ppMain = new PreprocessorMain(includeFilePath);
		InputStream is = ppMain.process(false);
		
		HashMap<String, HashMap<String, List<InputStream>>> includesVsPreProcessed =  IncludesPreProcessed.getInstance();
		HashMap<String, List<InputStream>> preprocessedIncludesListMap =  includesVsPreProcessed.get(sourceFilePath);
		if(preprocessedIncludesListMap == null){
			preprocessedIncludesListMap = new HashMap<String, List<InputStream>>();
		}
		
		List<InputStream> preprocessedIncludes = preprocessedIncludesListMap.get(modifiedIncludeLine);
		if(preprocessedIncludes == null){
			preprocessedIncludes = new ArrayList<InputStream>();
		}
		preprocessedIncludes.add(is);
		
		preprocessedIncludesListMap.put(modifiedIncludeLine.trim(), preprocessedIncludes);
		
		includesVsPreProcessed.put(sourceFilePath, preprocessedIncludesListMap);
		
		//Reset to original include Path
		compilerSettings.setIncludePath(includePath);
		
		return sb;
	}
	
	public String toString(){
		if(includeType == LIB)
			return fileNameLib.toString();

		return fileName;
	}
}

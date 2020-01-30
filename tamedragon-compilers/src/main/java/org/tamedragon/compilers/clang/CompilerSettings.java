package org.tamedragon.compilers.clang;

import org.tamedragon.common.llvmir.types.CompilationContext;

public class CompilerSettings {

	private String target;
	private String includePath;	
	private String newIncludePath;
	private String projectRoot;
	private String projectPath;
	private String replaceTrigraphSequences;
	private String newLine;
	private String debugMode;
	private CompilationContext compilationContext;
	
	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public CompilationContext getCompilationContext() {
		return compilationContext;
	}

	public void setCompilationContext(CompilationContext compilationContext) {
		this.compilationContext = compilationContext;
	}

	public String getIncludePath() {
		return includePath;
	}

	public void setIncludePath(String includePath) {
		this.includePath = includePath;
	}

	public String getProjectPath() {
		return projectPath;
	}

	public void setProjectPath(String projectPath) {
		this.projectPath = projectPath;
	}

	public String getReplaceTrigraphSequences() {
		return replaceTrigraphSequences;
	}

	public void setReplaceTrigraphSequences(String replaceTrigraphSequences) {
		this.replaceTrigraphSequences = replaceTrigraphSequences;
	}

	public String getNewLine() {
		return newLine;
	}

	public void setNewLine(String newLine) {
		this.newLine = newLine;
	}

	private CompilerSettings(){}
	
	public static CompilerSettings SINGLETON_INSTANCE;
	
	public static CompilerSettings getInstance(){
		if(SINGLETON_INSTANCE == null){
			SINGLETON_INSTANCE = new CompilerSettings();			
		}
		
		return SINGLETON_INSTANCE;
	}
	
	public String getInstanceTarget() {
		SINGLETON_INSTANCE = getInstance();
		return SINGLETON_INSTANCE.getTarget();
	}

	public void setInstanceTarget(String target) {
		SINGLETON_INSTANCE = getInstance();
		SINGLETON_INSTANCE.setTarget(target);
	}

	public String getNewIncludePath() {
		return newIncludePath;
	}

	public void setNewIncludePath(String newIncludePath) {
		this.newIncludePath = newIncludePath;
	}

	public String getInstanceIncludePath() {
		SINGLETON_INSTANCE = getInstance();
		return SINGLETON_INSTANCE.getIncludePath();
	}

	public void setInstanceIncludePath(String includePath) {
		this.includePath = includePath;
	}

	public String getInstanceProjectPath() {
		SINGLETON_INSTANCE = getInstance();
		return SINGLETON_INSTANCE.getProjectPath();
	}

	public void setInstanceProjectPath(String projectPath) {
		SINGLETON_INSTANCE = getInstance();
		SINGLETON_INSTANCE.setProjectPath(projectPath);
	}

	public String getInstanceReplaceTrigraphSequences() {
		SINGLETON_INSTANCE = getInstance();
		return SINGLETON_INSTANCE.getReplaceTrigraphSequences();
	}

	public void setInstanceReplaceTrigraphSequences(String replaceTrigraphSequences) {
		SINGLETON_INSTANCE = getInstance();
		SINGLETON_INSTANCE.setReplaceTrigraphSequences(replaceTrigraphSequences);
	}

	public String getInstanceNewLine() {
		SINGLETON_INSTANCE = getInstance();
		return SINGLETON_INSTANCE.getNewLine();
	}

	public void setInstanceNewLine(String newLine) {
		SINGLETON_INSTANCE = getInstance();
		SINGLETON_INSTANCE.setNewLine(newLine);
	}
	
	public CompilationContext getInstanceCompilationContext() {
		SINGLETON_INSTANCE = getInstance();
		return SINGLETON_INSTANCE.getCompilationContext();
	}

	public void setInstanceCompilationContext(CompilationContext compilationContext) {
		SINGLETON_INSTANCE = getInstance();
		SINGLETON_INSTANCE.setCompilationContext(compilationContext);
	}
	
	public String getDebugMode() {
		return debugMode;
	}

	public void setDebugMode(String debugMode) {
		this.debugMode = debugMode;
	}

	public String getProjectRoot() {
		return projectRoot;
	}

	public void setProjectRoot(String projectRoot) {
		this.projectRoot = projectRoot;
	} 
}

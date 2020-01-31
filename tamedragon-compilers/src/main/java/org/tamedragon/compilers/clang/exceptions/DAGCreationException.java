package org.tamedragon.compilers.clang.exceptions;

public class DAGCreationException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private static final String CIRCULAR_DEPENDENCIES_PREFIX = "Circular dependency found between ";
	
	private String sourceFilePath;
	private String includeFilePath;
	
	public DAGCreationException(String sourceFilePath, String includeFilePath) {
		this.sourceFilePath = sourceFilePath;
		this.includeFilePath = includeFilePath;
	}
	
	@Override
	public String getMessage() {
		return CIRCULAR_DEPENDENCIES_PREFIX + sourceFilePath  + " and " + includeFilePath;
	}
	
	public String getSourceFilePath() {
		return sourceFilePath;
	}

	public String getIncludeFilePath() {
		return includeFilePath;
	}

	
}

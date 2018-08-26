package org.tamedragon.common.llvmir.types.exceptions;

public class NameCreationException extends Exception {

	private String errorMessage;
	
	public static final String CANNOT_HAVE_NULL_NAME = 
				"Cannot create value name with a null name";
	
	public static final String INVALID_VALUE_NAME = 
				"Invalid name for a value";
	
	public NameCreationException(String errorMessage){
		this.errorMessage = errorMessage;
	}
	
	public String getMessage(){
		return errorMessage;
	}
	
	
}

package org.tamedragon.common.llvmir.types.exceptions;


public class TypeCreationException extends Exception{

	private String errorMessage;
	
	public TypeCreationException(String errorMessage){
		this.errorMessage = errorMessage;
	}
	
	public String getMessage(){
		return errorMessage;
	}
	
}

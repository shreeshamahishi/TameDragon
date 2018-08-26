package org.tamedragon.common.exceptions;

public class MissingConfigurationException extends Exception{

	public static final String UNSPECIFIED_TARGET_MACHINE = "A target machine architecture has not been specified.";
	
	public MissingConfigurationException(String errorDesc){
		super(errorDesc);
	}
}

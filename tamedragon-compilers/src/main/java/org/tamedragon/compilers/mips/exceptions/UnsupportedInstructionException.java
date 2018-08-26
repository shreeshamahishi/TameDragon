package org.tamedragon.compilers.mips.exceptions;


public class UnsupportedInstructionException extends Exception{
	
	public static final String ERROR_MSGS_OUT_OF_BOUNDS = "An attempt was made to access an error message, but it was out bounds from the list of error messages";
	
	private String msg; 
	
	public UnsupportedInstructionException(String msg){
		super(msg);
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
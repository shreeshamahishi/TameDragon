package org.tamedragon.compilers.clang.exceptions;

public class TokenOutOfBoundsException extends Exception{
	
	public static final String ERROR_MSGS_OUT_OF_BOUNDS = "An attempt was made to access an error message, but it was out bounds from the list of error messages";
	
	private String msg; 
	
	public TokenOutOfBoundsException(String msg){
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

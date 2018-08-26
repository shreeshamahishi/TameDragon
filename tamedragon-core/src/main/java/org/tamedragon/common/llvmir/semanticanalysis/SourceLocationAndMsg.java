package org.tamedragon.common.llvmir.semanticanalysis;

public class SourceLocationAndMsg {
	
	public SourceLocationAndMsg(SourceLocation srcLocation, String msg){
		this.srcLocation = srcLocation;
		this.msg = msg;
	}
	
	private SourceLocation srcLocation;
	private String msg;
	
	public SourceLocation getSrcLocation() {
		return srcLocation;
	} 
	
	public void setSrcLocation(SourceLocation srcLocation) {
		this.srcLocation = srcLocation;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
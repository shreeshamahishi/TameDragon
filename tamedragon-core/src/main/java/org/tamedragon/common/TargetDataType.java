package org.tamedragon.common;

public class TargetDataType {

	public static final int DATA_TYPE_CHAR_SIZE = 1;
	public static final int DATA_TYPE_HALF_WORD_SIZE = 2;
	public static final int DATA_TYPE_WORD_SIZE = 4;
	public static final int DATA_TYPE_DOUBLE_WORD_SIZE = 8;
	
	public static final int DATA_TYPE_INTEGER = 1;
	public static final int DATA_TYPE_FLOATING_POINT = 2;
	public static final int DATA_TYPE_STRING = 3;
	
	private int size;
	private int type;
	private String value;
	
	public void setSize(int size){
		this.size = size;
	}
	
	public int getSize(){
		return size;
	}
	
	public void setType(int type){
		this.type = type;
	}
	
	public int getType(){
		return type;
	}
	
	public void setValue(String value){
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}
}

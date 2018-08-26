package org.tamedragon.compilers.mips.translate;

import org.tamedragon.common.EnvironmentConstants;
import org.tamedragon.common.Label;
import org.tamedragon.common.TargetDataType;

public class MipsDataType {
	
	public final String BYTE_TYPE = ".byte";
	public final String SHORT_INT_TYPE = ".half";
	public final String WORD_TYPE = ".word";
	public final String DOUBLE_WORD_TYPE = ".dword";
	public final String STRING_TYPE = ".asciiz";
	
	private int size;
	private int type;
	private String value;
	
	public void setSize(int size){
		this.size = size;
	}
	
	public int getSize(){
		return size;
	}
	
	public String getTypeDescription(){		
		String typeStr = "";
		
		if(size == TargetDataType.DATA_TYPE_CHAR_SIZE){
			typeStr = BYTE_TYPE;
		}
		if(size == TargetDataType.DATA_TYPE_HALF_WORD_SIZE){
			typeStr = SHORT_INT_TYPE;
		}
		if(size == TargetDataType.DATA_TYPE_WORD_SIZE){
			typeStr = WORD_TYPE;
		}
		if(size == TargetDataType.DATA_TYPE_DOUBLE_WORD_SIZE){
			typeStr = DOUBLE_WORD_TYPE;
		}
		
		if(type == TargetDataType.DATA_TYPE_STRING){
			typeStr = STRING_TYPE;
		}
				
		return typeStr;
	}
	
	public String getDataSectionDescription(Label label){
		String dataSectionDesc = "";
		String typeDesc = getTypeDescription();
		dataSectionDesc =  label + ": " + typeDesc + " \"" + value +  "\""  + EnvironmentConstants.NEWLINE;
		return dataSectionDesc;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}

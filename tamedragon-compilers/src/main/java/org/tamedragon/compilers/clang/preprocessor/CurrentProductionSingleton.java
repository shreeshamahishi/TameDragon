package org.tamedragon.compilers.clang.preprocessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CurrentProductionSingleton {
	
	public static CurrentProductionSingleton currentProductionInstance;
	
	private static final Logger LOG = LoggerFactory.getLogger(CurrentProductionSingleton.class);
	
	public static final int TRANSLATION_UNIT = 0;
	public static final int PREPROCESSOR_DIRECTIVE = 1;
	public static final int ARTIFACT_INCLUDE = 2;
	public static final int DEFINITION = 3;
	public static final int LINE = 4;
	public static final int PRAGMA = 5;
	public static final int ERROR = 6;
	public static final int NULL = 7;
	public static final int CONDITIONAL = 8;
	public static final int IFLINE = 9;
	public static final int ELIF = 10;
	public static final int ELIF_PART = 11;
	public static final int TOKEN_SEQUENCE = 12;
	public static final int CONST_EXPR = 13;
	public static final int PROGRAM_CODE = 14;
	public static final int FILENAME_LIB = 15;
	public static final int ELSE_PART = 16;
	
	private int currentProduction;

	private CurrentProductionSingleton(){}
	
	public int getCurrentProduction() {
		return currentProduction;
	}

	public void setCurrentProduction(int curentProduction) {
		this.currentProduction = curentProduction;
	}
	
	public static CurrentProductionSingleton getInstance(){
		if(currentProductionInstance == null){
			currentProductionInstance = new CurrentProductionSingleton();
		}
		return currentProductionInstance;
	}
	
	public void setCurrentProductionOfInstance(int type){
		currentProductionInstance.setCurrentProduction(type);
	}
	
	public int getCurrentProductionOfInstance(){
		return currentProductionInstance.getCurrentProduction();
	}
	
	public boolean wsRequired(){
		if(currentProductionInstance.getCurrentProduction() == PROGRAM_CODE 
				|| currentProductionInstance.getCurrentProduction() == TOKEN_SEQUENCE){
			LOG.info("WS REQUIRED SINCE  current prod = " + currentProductionInstance.getCurrentProduction());
			return true;
		}
		
		LOG.info("WS NOT REQUIRED SINCE  current prod = " + currentProductionInstance.getCurrentProduction());
	
		return false;
	}
}

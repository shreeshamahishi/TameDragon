package org.tamedragon.common.aliasanalysis;

public enum AliasResult {
	NO_ALIAS(0) ,              //  0: No dependencies
	MAY_ALIAS(1),   		   //  1: May or may not alias; can be anything.
	PARTIAL_ALIAS(2),          //  2: Pointers differ, but object pointed to overlap.
	MUST_ALIAS(3);             //  3: Pointers are equal.

	private int value;

	private AliasResult(int value){
		this.value = value;
	}

	public int getAliasResultValue(){
		return value;
	}
} 
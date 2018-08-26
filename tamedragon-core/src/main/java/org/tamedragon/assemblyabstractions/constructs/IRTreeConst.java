package org.tamedragon.assemblyabstractions.constructs;

import org.tamedragon.common.llvmir.math.APInt;
import org.tamedragon.common.llvmir.types.APFloat;
import org.tamedragon.common.llvmir.types.Constant;
import org.tamedragon.common.llvmir.types.ConstantFP;
import org.tamedragon.common.llvmir.types.ConstantInt;
import org.tamedragon.common.llvmir.types.Value;

/**
 * This class holds information needed to create LLVM's Constant Values
 * @author ipsg
 *
 */
public class IRTreeConst extends IRTreeExp {

	private boolean intFlag;
	private APInt apInt;
	private APFloat apFloat;
	private Value value;
	
	public IRTreeConst(ConstantInt constInt){
		value = constInt;
		intFlag = true;
		this.apInt = constInt.getApInt();
		expType = TreeNodeExpType.CONST_EXP;
	}
	
	public IRTreeConst(ConstantFP constFP){
		value = constFP;
		intFlag = false;
		this.apFloat = constFP.getAPFloat();
		expType = TreeNodeExpType.CONST_EXP;
	}
	
	public IRTreeConst(Constant constant){
		value = constant;
		intFlag = false;
		expType = TreeNodeExpType.CONST_EXP;
	}
	
	public boolean isIntFlag() {
		return intFlag;
	}

	public void setIntFlag(boolean intFlag) {
		this.intFlag = intFlag;
	}

	public APInt getApInt() {
		return apInt;
	}

	public void setApInt(APInt apInt) {
		this.apInt = apInt;
	}

	public APFloat getApFloat() {
		return apFloat;
	}

	public void setApFloat(APFloat apFloat) {
		this.apFloat = apFloat;
	}
	
	public Value getExpressionValue() {
		return value;
	}
	
	public String getDescription() {
		return "CONST(" + value.toString() + ")";
	}
	
	
}

package org.tamedragon.assemblyabstractions.constructs;

import java.util.Stack;

import org.tamedragon.common.Label;
import org.tamedragon.common.llvmir.types.Temp;

public class AssemConst extends AssemExp{
	
	public static final int CONSTANT_INTEGER = 0;
	public static final int CONSTANT_LONG = 1;
	public static final int CONSTANT_CHAR = 2;
	public static final int CONSTANT_FLOAT = 3;
	public static final int CONSTANT_DOUBLE = 4;
	
	private int intValue;
	private long longValue;
	private char charValue;
	private float floatValue;
	private double doubleValue;
	
	private String stringValue;
	private Label labelForStringValue;
	
	private int constantType;
	
	public AssemConst(int v)  {
		constantType = CONSTANT_INTEGER;
		intValue = v;
	}
	
	public AssemConst(long l)  { 
		constantType = CONSTANT_LONG;
		longValue = l;
	}
	
	public AssemConst(char c)  { 
		constantType = CONSTANT_CHAR;
		charValue = c;
	}
	
	public AssemConst(float f)  {
		constantType = CONSTANT_FLOAT;
		floatValue = f;
	}
	
	public AssemConst(double d)  {
		constantType = CONSTANT_DOUBLE;
		doubleValue = d;
	}
	
	public AssemConst(String strValue)  {
		constantType = AssemType.STRING_TYPE;
		stringValue = strValue;
		labelForStringValue = new Label(strValue);
	}
	
	public AssemExpList children() {
		return null;
	}
	
	public int getAssemTypeType() {
		return AssemType.VALUE_TYPE;
	}
	
	public AssemType translateToCJump(Label testLabel, Label endLabel, boolean isNegation){
		// Move this value to a temporary
		Temp temp = new Temp();
		AssemTemp assemTemp = new AssemTemp(temp, getValueProperties());
		AssemMove assemMov = new AssemMove(assemTemp, this);
		
		// Create a constant 0
		AssemConst assemConst0 = (AssemConst) new AssemConst(0);
		
		// Create a conditional based on whether the above temporary is 1 or 0
		AssemCJump tempCJump = null;
		if(!isNegation)
			tempCJump = new AssemCJump(AssemCJump.GT, assemTemp, assemConst0,
				testLabel, endLabel);
		else
			tempCJump = new AssemCJump(AssemCJump.LTE, assemTemp, assemConst0,
					testLabel, endLabel);
		
		Stack<AssemType> seqStack = new Stack<AssemType>();
		seqStack.push(assemMov);
		seqStack.push(tempCJump);
		
		return translateSeqStatement(seqStack);
	}
	
	public AssemType translateToIntType() {
		// Create a new temporary to hold this constant
		Temp temp = new Temp();
		AssemTemp assemTemp = new AssemTemp(temp, getValueProperties());
		
		// Move this value into the temporary and return the seq-exp
		AssemMove assemMove = new AssemMove(assemTemp, this);
		
		return new AssemSeqExp(assemMove, assemTemp);
	}
	
	public String getDescription() {
		String desc = "";
		if(constantType == CONSTANT_INTEGER){
			desc = "" + intValue;
		}
		else if(constantType == CONSTANT_CHAR){
			desc = "" +  charValue;
		}
		else if(constantType == CONSTANT_LONG){
			desc = "" +  longValue;
		}
		else if(constantType == CONSTANT_FLOAT){
			desc = "" +  floatValue;
		}
		else if(constantType == CONSTANT_DOUBLE){
			desc = "" +  doubleValue;
		}
		
		return "CONST(" + desc + ")";
	}
	
	public AssemSeq translateSeqStatement(Stack seq) {
		AssemSeq retAssemType = null;
		
		while(!seq.isEmpty())
		{
			AssemType temp = (AssemType)seq.pop();
			// If there is an expression in this sequence, coerce it to a statement,
			// since we are building a list of statements 
			if(temp instanceof AssemExp)
			{
				AssemExp exp = (AssemExp) temp;
				temp = exp.translateToStatement();
			}
			retAssemType  = new AssemSeq((AssemStm)temp, (AssemStm)retAssemType);
		}
		
		return retAssemType;
	}
	
	public AssemExp build(AssemExpList list) {
		return this;
	}
	
	public AssemSeqExp canonize() {
		return new AssemSeqExp(null, this);	
	}
	
	public int getExpType() {
		return AssemExp.CONST;
	}

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

	public int getConstantType() {
		return constantType;
	}

	public Label getLabelForStringValue() {
		return labelForStringValue;
	}

	public void setLabelForStringValue(Label labelForStringValue) {
		this.labelForStringValue = labelForStringValue;
	}

	public int getIntValue() {
		return intValue;
	}

	public void setIntValue(int intValue) {
		this.intValue = intValue;
	}

	public long getLongValue() {
		return longValue;
	}

	public void setLongValue(long longValue) {
		this.longValue = longValue;
	}

	public char getCharValue() {
		return charValue;
	}

	public void setCharValue(char charValue) {
		this.charValue = charValue;
	}

	public float getFloatValue() {
		return floatValue;
	}

	public void setFloatValue(float floatValue) {
		this.floatValue = floatValue;
	}

	public double getDoubleValue() {
		return doubleValue;
	}

	public void setDoubleValue(double doubleValue) {
		this.doubleValue = doubleValue;
	}

	public void setConstantType(int constantType) {
		this.constantType = constantType;
	}
	
	/**
	 * Infers and returns the value properties based on the constant type
	 */
	public AssemValueProperties getValueProperties(){
		AssemValueProperties assemValueProperties = new AssemValueProperties();

		if(constantType == CONSTANT_INTEGER){
			assemValueProperties.setInteger(true);
			assemValueProperties.setIntegerSize(AssemType.SIZE_WORD);
		}
		else if(constantType == CONSTANT_CHAR){
			assemValueProperties.setInteger(true);
			assemValueProperties.setIntegerSize(AssemType.SIZE_BYTE);
		}
		else if(constantType == CONSTANT_LONG){
			assemValueProperties.setInteger(true);
			assemValueProperties.setIntegerSize(AssemType.SIZE_DOUBLE);
		}
		else if(constantType == CONSTANT_FLOAT){
			assemValueProperties.setInteger(false);
			assemValueProperties.setFloatPrecision(AssemType.FP_FLOAT);
		}
		else if(constantType == CONSTANT_DOUBLE){
			assemValueProperties.setInteger(false);
			assemValueProperties.setFloatPrecision(AssemType.FP_DOUBLE);
		}
		
		return assemValueProperties;
		
	}
	
	public boolean isInteger(){
		return (constantType == CONSTANT_CHAR || constantType == CONSTANT_LONG || constantType == CONSTANT_INTEGER);
	}
}

package org.tamedragon.common.llvmir.types;

import java.util.ArrayList;
import java.util.List;

import org.tamedragon.common.llvmir.math.APInt;

/**
 * A self-contained host- and target-independent arbitrary-precision
 * floating-point software implementation.
 * @author ipsg
 *
 */
public class APFloat {
	public static final FltSemantics IEEEhalf = new FltSemantics((short)15, (short)-14, 11, true);
	public static final FltSemantics IEEEsingle = new FltSemantics((short)127, (short)-126, 24, true);
	public static final FltSemantics IEEEdouble = new FltSemantics((short)1023, (short)-1022, 53, true);
	public static final FltSemantics IEEEquad = new FltSemantics((short)16383, (short)-16382, 113, true);
	public static final FltSemantics x87DoubleExtended = new FltSemantics((short)16383, (short)-16382, 64, true);
	public static final FltSemantics Bogus = new FltSemantics((short)0, (short)0, 0, true);
	public static final FltSemantics PPCDoubleDouble = new FltSemantics((short)1023, (short)-1022, 106, false);

	FltSemantics semantics;
	String strRepresentation;
	Integer integerPart;

	static int semanticsPrecision(FltSemantics FltSem){
		return FltSem.precision;
	}

	public APFloat(FltSemantics F, String str){
		this.semantics = F;
		if(F == IEEEdouble){
			Double d = Double.parseDouble(str);

			if(str.contains(".")){
				String intPart = str.substring(0, str.indexOf('.'));
				if(intPart.length() > 0)
					integerPart = Integer.parseInt(intPart);
				else
					integerPart = 0;
			}
			else
				integerPart = Integer.parseInt(str);

			str = formatDoubleInScientificNotation(d);
		}
		this.strRepresentation = str;
	}

	/**
	 * This function format the Double in scientific notation. For e.g. 54.3
	 * will be formatted as 5.430000e+01
	 */
	private String formatDoubleInScientificNotation(Double d) {
		String formatedStr = null;
		boolean isNegative = (d < 0)? true : false;
		Double num = (d < 0)? d*(-1) : d;
		Integer exp = 0;
		String s = num.toString();
		if(!s.equals("Infinity")){
			while(num >= 10){
				char chArr[] = s.toCharArray();
				List<Character> chList = new ArrayList<Character>();
				for(char ch : chArr)
					chList.add(ch);
				int index = chList.indexOf('.');
				char temp = chArr[index-1];
				chArr[index-1] = '.';
				chArr[index] = temp;
				s = new String(chArr);
				if(s.contains("e"))
					s = s.substring(0, s.indexOf('e'));
				else if(s.contains("E"))
					s = s.substring(0, s.indexOf('E'));
				num = new Double(s);
				exp++;
			}
			if(num < 1 && num != 0){
				while(num < 1){
					s = num.toString();
					char chArr[] = s.toCharArray();
					List<Character> chList = new ArrayList<Character>();
					for(char ch : chArr)
						chList.add(ch);
					int index = chList.indexOf('.');
					char temp = chArr[index+1];
					chArr[index+1] = '.';
					chArr[index] = temp;
					String s1 = new String(chArr);
					num = new Double(s1);
					exp--;
				}
			}
			formatedStr = num.toString();
			String decimalPart = num.toString().split("\\.")[1];
			if(decimalPart.length() < 6){
				for(int i = decimalPart.length(); i < 6; i++){
					formatedStr += "0";
				}
			}
			formatedStr += (exp < 0)? "e-" : "e+";
			formatedStr += (exp.toString().length() > 2)? exp.toString() : (exp < 0)? "0" + new Integer(exp*(-1)).toString() : "0" + exp.toString();

			if(isNegative)
				formatedStr = "-" + formatedStr;

			return formatedStr;
		}
		return s;
	}

	/* Floating point numbers have a four-state comparison relation.  */
	enum cmpResult {
		cmpLessThan,
		cmpEqual,
		cmpGreaterThan,
		cmpUnordered
	}

	/* IEEE-754R gives five rounding modes.  */
	enum roundingMode {
		rmNearestTiesToEven,
		rmTowardPositive,
		rmTowardNegative,
		rmTowardZero,
		rmNearestTiesToAway
	}

	// Operation status.  opUnderflow or opOverflow are always returned
	// or-ed with opInexact.
	enum opStatus {
		opOK (0x00),
		opInvalidOp(0x01),
		opDivByZero(0x02),
		opOverflow(0x04),
		opUnderflow(0x08),
		opInexact(0x10);

		int opCode;

		opStatus(int opCode){
			this.opCode = opCode;
		}

		public int getOpCode(){
			return opCode;
		}
	}

	// Category of internally-represented number.
	enum fltCategory {
		fcInfinity,
		fcNaN,
		fcNormal,
		fcZero
	};

	enum uninitializedTag {
		uninitialized
	};

	public String getStrRepresentation() {
		return strRepresentation;
	}

	public void setStrRepresentation(String strRepresentation) {
		this.strRepresentation = strRepresentation;
	}

	public Integer getIntegerPart() {
		return integerPart;
	}

	public void setIntegerPart(Integer integerPart) {
		this.integerPart = integerPart;
	}

	public String toString(){
		String str = "";

		if(strRepresentation != null)
			str += strRepresentation;
		else
			str += integerPart.toString();

		return str;
	}

	public double addOrSubtract(APFloat otherAPFloat, boolean add){
		double thisVal = Double.parseDouble(strRepresentation);
		double thatVal = Double.parseDouble(otherAPFloat.getStrRepresentation());

		double result = thisVal + thatVal;
		if(!add)
			result = thisVal - thatVal;

		return result;

	}

	public int compare(APFloat otherAPFloat) {
		double thisVal = Double.parseDouble(strRepresentation);
		double thatVal = Double.parseDouble(otherAPFloat.getStrRepresentation());
		if(thisVal == thatVal)
			return 0;
		else if(thisVal > thatVal)
			return 1;

		return -1;

	}

	public double mul(APFloat otherAPFloat) {
		double thisVal = Double.parseDouble(strRepresentation);
		double thatVal = Double.parseDouble(otherAPFloat.getStrRepresentation());

		return thisVal * thatVal;
	}

	public double divide(APFloat otherAPFloat) {
		double thisVal = Double.parseDouble(strRepresentation);
		double thatVal = Double.parseDouble(otherAPFloat.getStrRepresentation());

		return thisVal / thatVal;
	}

	public double frem(APFloat otherAPFloat) {
		double thisVal = Double.parseDouble(strRepresentation);
		double thatVal = Double.parseDouble(otherAPFloat.getStrRepresentation());

		return thisVal % thatVal;
	}

	public FltSemantics getSemantics() {
		return semantics;
	}

	public static APFloat getZero(FltSemantics semantics) {
		// TODO Fix this
		return new APFloat(semantics, "0.0");
	}

	public static APInt getNullValue(int numBits) { 
		return new APInt(numBits, "0", 10); 
	}
	
	public boolean isZero() {
		// TODO Implement this
		return false;
		//return category == fcZero; 
	}
	
	public boolean isNegZero() {
		// TODO Implement this
		return false;
		//return isZero() && getIEEE().isNegative();
	}
	
	public boolean isNaN() {
		// TODO Implement this
		return false;
		//return  getCategory() == fcNaN; 
	}
	
	public boolean isInfinity() {
		// TODO Implement this
		return false;
		//return  getCategory() == fcInfinity; 
	}
	
	public boolean isNegative() {
		// TODO Implement this
		return false;
		//return getIEEE().isNegative();
	}
	
}
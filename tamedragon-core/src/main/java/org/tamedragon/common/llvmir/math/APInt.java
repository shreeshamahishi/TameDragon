package org.tamedragon.common.llvmir.math;


import static org.tamedragon.common.llvmir.math.MathUtils.Hi_32;
import static org.tamedragon.common.llvmir.math.MathUtils.Lo_32;
import static org.tamedragon.common.llvmir.math.MathUtils.Make_64;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * Class for arbitrary precision integers.
 * @author ipsg
 *
 */
public class APInt {


	private static final int AP_INT_BITS_PER_WORD = 64;
	private static BigInteger WORDTYPE_MAX = ULong.MAX_VALUE;

	protected int numBits;
	protected ULong[] unsignedVals;
	
	protected Boolean opOverflowFlag;

	public APInt() {

	}

	/*public APInt(int numBits, String val, boolean isSigned ){
		if(numBits <= 0) {
			throw new IllegalArgumentException("Bitwidth too small");
		}

		fromString(numBits, val, 10);
	}*/

	// public APInt(int numBits, ULong val, boolean isSigned = false)
	public APInt(int numBits, ULong val, boolean isSigned){
		this.numBits = numBits;
		if(numBits < 0) {
			throw new IllegalArgumentException("bitwidth too small");
		}
		if (isSingleWord()) {
			unsignedVals = new ULong[1];
			unsignedVals[0] = val;
			clearUnusedBits();
		} 
		else {
			ULong[] vals = new ULong[1];
			initSlowCase(vals, isSigned);
		}
	}

	public APInt(int numbits, String str, int radix) {
		if(numbits <= 0) {
			throw new IllegalArgumentException("Bitwidth too small");
		}

		fromString(numbits, str, radix);
	}


	/* Create a new APInt of numBits width, initialized as val.
	 *
	 * If isSigned is true then val is treated as if it were a signed value
	 * (i.e. as an int64_t) and the appropriate sign extension to the bit width
	 * will be done. Otherwise, no sign extension occurs (high order bits beyond
	 * the range of val are zero filled).
	 *
	 * param numBits the bit width of the constructed APInt
	 * param val the initial value of the APInt
	 * param isSigned how to treat signedness of val
	 */
	//public APInt(int numBits, ULong val, boolean isSigned = false)
	public APInt(int numBits, ULong[] vals, boolean isSigned) {

		this.numBits = numBits;
		if(numBits <= 0) {
			throw new IllegalArgumentException("bitwidth too small");
		}
		if (isSingleWord()) {
			this.unsignedVals = new ULong[1];
			this.unsignedVals[0] = vals[0];
			clearUnusedBits();
		} else {
			initSlowCase(vals, isSigned);
		}
	}

	public APInt clone() {

		ULong[] newVals = new ULong[unsignedVals.length];
		for(int i = 0; i < newVals.length; i++) {
			newVals[i] = unsignedVals[i].clone();
		}

		APInt newAPInt = new APInt(numBits, newVals.clone(), false);
		return new APSInt(newAPInt, false);
	}

	private void fromString(int numbits, String str, int radix) {

		this.numBits = numbits;

		// Check our assumptions here
		if(str == null || str.isEmpty()) {
			throw new IllegalArgumentException("Invalid string");
		}

		if(!(radix == 10 || radix == 8 || radix == 16 || radix == 2 || radix == 36)){
			throw new IllegalArgumentException("Radix should be 2, 8, 10, 16, or 36!");
		}

		boolean isNeg = false;
		char firstChar = str.charAt(0);
		String strNumOnly = str;
		if(firstChar == '-' || firstChar == '+') {
			strNumOnly = str.substring(1, str.length());
			if(strNumOnly.length() == 0) {
				throw new IllegalArgumentException("String is only a sign, needs a value.");
			}

			if(firstChar == '-') {
				isNeg = true;
			}
		}

		int slen = strNumOnly.length();

		if(!(slen <= numbits || radix != 2)) {
			throw new IllegalArgumentException("Insufficient bit width");
		}

		if(!((slen-1)*3 <= numbits || radix != 8)) {
			throw new IllegalArgumentException("Insufficient bit width");
		}

		if(!((slen-1)*4 <= numbits || radix != 16)) {
			throw new IllegalArgumentException("Insufficient bit width");
		}

		if(!(((slen-1)*64)/22 <= numbits || radix != 10)) {
			throw new IllegalArgumentException("Insufficient bit width");
		}

		// Allocate memory as needed
		if (isSingleWord()) {
			unsignedVals = new ULong[1];
			unsignedVals[0] = ULong.valueOf(0);
		}
		else {
			unsignedVals = getClearedMemory(getNumWords());
		}

		// Figure out if we can shift instead of multiply
		int shift = (radix == 16 ? 4 : radix == 8 ? 3 : radix == 2 ? 1 : 0);

		// Digit traversal loop
		char[] digits = strNumOnly.toCharArray();
		for (char c : digits) {
			int digit = getDigit(c, radix);
			if(digit > radix) {
				throw new IllegalArgumentException("Invalid character in digit string");
			}

			// Shift or multiply the value by the radix
			if (slen > 1) {
				if (shift == 1) {
					shl(shift);
				}
				else {
					//unsignedVal = unsignedVal.mul(ULong.valueOf(radix));
					mul(ULong.valueOf(radix));
				}
			}

			// Add in the digit we just interpreted
			this.add(ULong.valueOf(digit));
		}

		// If its negative, put it in two's complement form
		if (isNeg) {
			negate();
		}
	}

	private ULong[] getClearedMemory(int numWords) {
		ULong[] result = new ULong[numWords];
		for(int i = 0; i < result.length; i++) {
			result[i] = ULong.valueOf(0);
		}
		return result;
	}

	/**
	 * out-of-line slow case for inline constructor
	 * @param val
	 * @param isSigned
	 */
	protected void initSlowCase(ULong[] vals, boolean isSigned) {
		unsignedVals = getClearedMemory(getNumWords());
		unsignedVals[0] = vals[0];
		if(vals.length == 1) {
			if (isSigned && vals[0].longValue() < 0) {
				for (int i = 1; i < getNumWords(); ++i) {
					unsignedVals[i] = ULong.valueOf(WORDTYPE_MAX);
				}
			}
		}
		else {
			for (int i = 1; i < getNumWords(); ++i) {
				unsignedVals[i] = vals[i];
			}
		}
		clearUnusedBits();
	}

	/**
	 * out-of-line slow case for inline copy constructor
	 * @param that
	 */
	protected void initSlowCase(APInt that) {
		unsignedVals = new ULong[getNumWords()];
		for(int i = 0; i < unsignedVals.length; i++) {
			unsignedVals[i] = that.getUnsignedVals()[i].clone();
		}
	}

	//*************************************** TESTS *****************************************************
	/* Determine sign of this APInt.
	 * This tests the high bit of this APInt to determine if it is set.
	 * Returns true if this APInt is negative, false otherwise
	 */
	public boolean isNegative() {
		if(isBitSet(numBits -1)) {
			return true;
		}
		return false;
	}

	/*Determine if this APInt Value is non-negative (>= 0)
	 * This tests the high bit of the APInt to determine if it is unset.
	 */
	public boolean isNonNegative() { 
		return !isNegative(); 
	}

	/**
	 *  Determine if sign bit of this APInt is set.
	 * This tests the high bit of this APInt to determine if it is set.
	 * Returns true if this APInt has its sign bit set, false otherwise.
	 */
	public boolean isSignBitSet() { 
		return isBitSet(numBits-1); 
	}

	/**
	 * Determine if sign bit of this APInt is clear.
	 * This tests the high bit of this APInt to determine if it is clear.
	 * Returns true if this APInt has its sign bit clear, false otherwise.
	 */
	public boolean isSignBitClear() { 
		return !isSignBitSet(); 
	}

	/* Determine if this APInt Value is positive.
	 * This tests if the value of this APInt is positive (> 0). Note
	 * that 0 is not a positive value.
	 * Returns true if this APInt is positive.
	 */
	public boolean isStrictlyPositive() { 
		return isNonNegative() && !isNullValue(); 
	}

	/* Determine if this APInt Value is non-positive (<= 0).
	 * Returns true if this APInt is non-positive.
	 */
	public boolean isNonPositive() { 
		return !isStrictlyPositive(); 
	}

	/* Determine if all bits are set
	 * This checks to see if the value has all bits of the APInt are set or not.
	 */
	public boolean isAllOnesValue() {
		if (isSingleWord()) {
			return unsignedVals[0].equals(ULong.valueOf(WORDTYPE_MAX.shiftRight(AP_INT_BITS_PER_WORD - numBits)));
		}
		else {
			// TODO Implement this
			// return countTrailingOnesSlowCase() == BitWidth;
			return false;
		}
	}

	/* Determine if all bits are clear
	 * This checks to see if the value has all bits of the APInt are clear or
	 * not.
	 */
	public boolean isNullValue() {
		for(int i = 0; i < unsignedVals.length; i++) {
			if(unsignedVals[i].longValue() != 0) {
				return false;
			}
		}
		return true;
	}

	/*Determine if this is a value of 1.
	  This checks to see if the value of this APInt is one.
	 */
	public boolean isOneValue() {
		if (isSingleWord()) {
			return unsignedVals[0].equals(1);
		}
		else {
			return countLeadingZerosSlowCase() == numBits - 1;
		}
	}

	/* Determine if this is the largest int value.
	 * This checks to see if the value of this APInt is the maximum unsigned
	 * value for the APInt's bit width.
	 */
	public boolean isMaxValue() { return isAllOnesValue(); }

	/* Determine if this is the largest signed value.
	 * This checks to see if the value of this APInt is the maximum signed
	 * value for the APInt's bit width.
	 */
	public boolean isMaxSignedValue() {
		if (isSingleWord()) {
			return unsignedVals[0].equals(ULong.valueOf(1).leftShift(numBits -1).subtract(1));
		}

		return !isNegative() && countTrailingOnesSlowCase() == numBits - 1;
	}

	/* Determine if this is the smallest int value.
	 * This checks to see if the value of this APInt is the minimum unsigned
	 * value for the APInt's bit width.
	 */
	public boolean isMinValue() { return isNullValue(); }

	/* Determine if this is the smallest signed value.
	 * This checks to see if the value of this APInt is the minimum unsigned
	 * value for the APInt's bit width.
	 */
	public boolean isMinSignedValue() {
		if (isSingleWord()) {
			return unsignedVals[0].equals(ULong.valueOf(1).leftShift(numBits -1));
		}

		return isNegative() && countTrailingZerosSlowCase() == numBits - 1;
	}

	/* 
	 *Check if this APInt has an N-bits int integer value.
	 */
	public boolean isIntN(int N) {
		if(N <= 0) {
			throw new IllegalArgumentException("N must be a positive number");
		}
		return getActiveBits() <= N;
	}

	/* 
	 * Check if this APInt has an N-bits signed integer value.
	 */
	public boolean isSignedIntN(int N) {
		if(N <= 0) {
			throw new IllegalArgumentException("N must be a positive number");
		}

		return getMinSignedBits() <= N;
	}

	/* Check if this APInt's value is a power of two greater than zero.
	 * returns true if the argument APInt value is a power of two > 0.
	 */
	public boolean isPowerOf2() {
		if (isSingleWord()) {
			return MathUtils.isPowerOf2_64(unsignedVals[0]);
		}
		else {
			return countPopulationSlowCase() == 1;
		}
	}

	/* 
	 * Check if the APInt's value is returned by getSignMask.
	 * Returns true if this is the value returned by getSignMask.
	 */
	public boolean isSignMask() { 
		return isMinSignedValue(); 
	}

	/* Convert APInt to a boolean value.
	 * This converts the APInt to a boolean value as a test against zero.
	 */
	public boolean getBoolValue() { 
		return !isNot(); 
	}

	/* Check if the APInt consists of a repeated bit pattern.
	 * e.g. 0x01010101 satisfies isSplat(8).
	 * splatSizeInBits is the size of the pattern in bits. Must divide bit
	 * width without remainder.
	 */
	public boolean isSplat(int splatSizeInBits) {
		if(numBits % splatSizeInBits <= 0) {
			throw new IllegalArgumentException("SplatSizeInBits must divide width!");
		}

		// We can check that all parts of an integer are equal by making use of a
		// little trick: rotate and check if it's still the same value.
		return this.equals(rotl(splatSizeInBits));
	}

	/* Returns true if this APInt value is a sequence of numBits ones
	 * starting at the least significant bit with the remainder zero.
	 */
	public boolean isMask(int numBits) {
		if(numBits <= 0) {
			throw new IllegalArgumentException("numBits must be non-zero");
		}
		if(numBits > this.numBits) {
			throw new IllegalArgumentException("numBits out of range");
		}

		if (isSingleWord()) {
			return unsignedVals[0].equals((ULong.valueOf(WORDTYPE_MAX).rightShift(AP_INT_BITS_PER_WORD - numBits)));
		}

		int Ones = countTrailingOnesSlowCase();
		return (numBits == Ones) && ((Ones + countLeadingZerosSlowCase()) == numBits);
	}

	/* Returns true if this APInt is a non-empty sequence of ones starting at
	 *  the least significant bit with the remainder zero.
	 *  Ex. isMask(0x0000FFFFU) == true.
	 */
	public boolean isMask() {
		if (isSingleWord()) {
			return MathUtils.isMask_64(unsignedVals[0]);
		}

		int Ones = countTrailingOnesSlowCase();
		return (Ones > 0) && ((Ones + countLeadingZerosSlowCase()) == numBits);
	}

	/* Return true if this APInt value contains a sequence of ones with
	 * the remainder zero.
	 */
	public boolean isShiftedMask() {
		if (isSingleWord()) {
			return MathUtils.isShiftedMask_64(unsignedVals[0]);
		}

		int Ones = countPopulationSlowCase();
		int LeadZ = countLeadingZerosSlowCase();
		return (Ones + LeadZ + countTrailingZeros()) == numBits;
	}

	// ****************************************************************** GENERATORS **********************************************************************
	/* Gets maximum unsigned value of APInt for specific bit width.
	 */
	public static APInt getMaxValue(int numBits) {
		return getAllOnesValue(numBits);
	}

	/*Gets maximum signed value of APInt for a specific bit width.
	 */
	public static APInt getSignedMaxValue(int numBits) {
		APInt api = getAllOnesValue(numBits);
		api.clearBit(numBits - 1);
		return api;
	}

	/* 
	 * Gets minimum unsigned value of APInt for a specific bit width.
	 */
	public static APInt getMinValue(int numBits) { 
		return new APInt(numBits, new ULong[] {ULong.valueOf("0")}, false); 
	}

	/*
	 * Gets minimum signed value of APInt for a specific bit width.
	 */
	public static APInt getSignedMinValue(int numBits) {
		APInt api = new APInt(numBits, new ULong[] {ULong.valueOf("0")}, false);
		api.setBit(numBits - 1);
		return api;
	}

	/* 
	 * Get the SignMask for a specific bit width.
	 * This is just a wrapper function of getSignedMinValue(), and it helps code
	 * readability when we want to get a SignMask.
	 */
	public static APInt getSignMask(int numBits) {
		return getSignedMinValue(numBits);
	}

	/* Get the all-ones value.
	 * Returns the all-ones value for an APInt of the specified bit-width.
	 */
	public static APInt getAllOnesValue(int numBits) {
		return new APInt(numBits, new ULong[] { ULong.valueOf(WORDTYPE_MAX)}, true);
	}	

	/*
	 * Returns the '0' value for an APInt of the specified bit-width.
	 */
	public static APInt getNullValue(int numBits) {
		return new APInt(numBits, ULong.valueOf("0"), false);
	}


	/* Compute an APInt containing numBits highbits from this APInt.
	 *
	 * Get an APInt with the same BitWidth as this APInt, just zero mask
	 * the low bits and right shift to the least significant bit.
	 *
	 * Returns the high "numBits" bits of this APInt.
	 */
	public APInt getHiBits(int numBits) {
		return lshr(this.numBits - numBits);
	}

	/* Compute an APInt containing numBits lowbits from this APInt.
	 *
	 * Get an APInt with the same BitWidth as this APInt, just zero mask
	 * the high bits.
	 *
	 * Returns the low "numBits" bits of this APInt.
	 */
	public APInt getLoBits(int numBits) {
		APInt result = getLowBitsSet(this.numBits, numBits);
		result = result.andWith(this);
		return result;
	}

	/*
	 * Return an APInt with exactly one bit set in the result.
	 */
	public static APInt getOneBitSet(int numBits, int bitIndex) {
		APInt result = new APInt(numBits, ULong.valueOf(0), false);
		result.setBit(bitIndex);
		return result;
	}

	/* Get a value with a block of bits set.
	 *
	 * Constructs an APInt value that has a contiguous range of bits set. The
	 * bits from loBit (inclusive) to hiBit (exclusive) will be set. All other
	 * bits will be zero. For example, with parameters(32, 0, 16) you would get
	 * 0x0000FFFF. Please call getBitsSetWithWrap if loBit may be greater than
	 * hiBit.
	 *
	 * numBits the intended bit width of the result
	 * loBit the index of the lowest bit set.
	 * hiBit the index of the highest bit set.
	 *
	 * Returns An APInt value with the requested bits set.
	 */
	public static APInt getBitsSet(int numBits, int loBit, int hiBit) {
		if(loBit > hiBit) {
			throw new IllegalArgumentException("loBit greater than hiBit");
		}

		APInt Res = new APInt(numBits, ULong.valueOf(0), false);
		Res.setBits(loBit, hiBit);
		return Res;
	}

	/* Wrap version of getBitsSet.
	 * If \p hiBit is no less than \p loBit, this is same with getBitsSet.
	 * If \p hiBit is less than \p loBit, the set bits "wrap". For example, with
	 * parameters (32, 28, 4), you would get 0xF000000F.
	 */
	public static APInt getBitsSetWithWrap(int numBits, int loBit, int hiBit) {
		APInt Res = new APInt(numBits, ULong.valueOf(0), false);
		Res.setBitsWithWrap(loBit, hiBit);
		return Res;
	}

	/* Get a value with upper bits starting at loBit set.
	 *
	 * Constructs an APInt value that has a contiguous range of bits set. The
	 * bits from loBit (inclusive) to numBits (exclusive) will be set. All other
	 * bits will be zero. For example, with parameters(32, 12) you would get
	 * 0xFFFFF000.
	 *
	 * numBits the intended bit width of the result
	 * loBit the index of the lowest bit to set.
	 *
	 * Returns An APInt value with the requested bits set.
	 */
	public static APInt getBitsSetFrom(int numBits, int loBit) {
		APInt Res = new APInt(numBits, ULong.valueOf(0), false);
		Res.setBitsFrom(loBit);
		return Res;
	}

	/* Get a value with high bits set
	 *
	 * Constructs an APInt value that has the top hiBitsSet bits set.
	 *
	 * numBits the bitwidth of the result
	 * hiBitsSet the number of high-order bits set in the result.
	 */
	public static APInt getHighBitsSet(int numBits, int hiBitsSet) {
		APInt Res = new APInt(numBits, ULong.valueOf(0), false);
		Res.setHighBits(hiBitsSet);
		return Res;
	}

	/* Get a value with low bits set
	 * Constructs an APInt value that has the bottom loBitsSet bits set.
	 *
	 * numBits the bitwidth of the result
	 * loBitsSet the number of low-order bits set in the result.
	 */
	public static APInt getLowBitsSet(int numBits, int loBitsSet) {
		APInt Res = new APInt(numBits, ULong.valueOf(0), false);
		Res.setLowBits(loBitsSet);
		return Res;
	}

	/*
	 * Return a value containing V broadcasted over NewLen bits.
	 */
	public static APInt getSplat(int NewLen,  APInt V) {
		if(NewLen < V.getNumBits()) {
			throw new IllegalArgumentException("Can't splat to smaller bit width!");
		}

		APInt Val = V.zextOrSelf(NewLen);
		for (int I = V.getNumBits(); I < NewLen; I <<= 1)
			Val =  Val.or(Val.shiftLeft(I));

		return Val;
	}

	/* Determine if two APInts have the same value, after zero-extending
	 * one of them (if needed!) to ensure that the bit-widths match.
	 */
	public static boolean isSameValue(APInt I1,  APInt I2) {
		if (I1.getNumBits() == I2.getNumBits())
			return I1 == I2;

		if (I1.getNumBits() > I2.getNumBits())
			return I1 == I2.zext(I1.getNumBits());

		return I1.zext(I2.getNumBits()) == I2;
	}

	// ****************************************************************** UNARY OPERATORS **********************************************************************

	/* Postfix increment operator.
	 * Increments *this by 1.
	 * Returns a new APInt value representing the original value of *this.
	 */

	public APInt increment() {
		APInt incremented = clone();

		if (isSingleWord()) {
			incremented.unsignedVals[0] = incremented.unsignedVals[0].add(1);
		}
		else {
			tcIncrement(incremented.unsignedVals, getNumWords());
		}

		return incremented.clearUnusedBits();
	}

	/* Postfix decrement operator.
	 * Decrements *this by 1.
	 * Returns a new APInt value representing the original value of *this.
	 */
	public APInt decrement() {
		APInt decremented = clone();

		if (isSingleWord()) {
			decremented.unsignedVals[0] = decremented.unsignedVals[0].subtract(1);
		}
		else {
			tcDecrement(decremented.unsignedVals, getNumWords());
		}
		return clearUnusedBits();
	}

	public boolean not() {
		if (isSingleWord()) {
			return unsignedVals[0].equals(0);
		}

		return countLeadingZerosSlowCase() == numBits;
	}


	// ****************************************************************** ASSIGNMENT OPERATORS **********************************************************************

	/* Copy assignment operator.
	 * Returns *this after assignment of RHS.
	 */
	public APInt assign(APInt RHS) {
		// If the bitwidths are the same, we can avoid mucking with memory
		if (isSingleWord() && RHS.isSingleWord()) {
			unsignedVals[0] = RHS.unsignedVals[0];
			numBits = RHS.numBits;
			return clearUnusedBits();
		}

		AssignSlowCase(RHS);
		return this;
	}

	/* Assignment operator.
	 *
	 * The RHS value is assigned to *this. If the significant bits in RHS exceed
	 * the bit width, the excess bits are truncated. If the bit width is larger
	 * than 64, the value is zero filled in the unspecified high order bits.
	 *
	 * Returns this after assignment of RHS value.
	 */
	public APInt assign(ULong RHS) {
		if (isSingleWord()) {
			unsignedVals[0] = RHS;
			clearUnusedBits();
		} 
		else {
			unsignedVals[0] = RHS;
			for(int i = 1; i < getNumWords(); i++) {
				unsignedVals[1] = ULong.valueOf(0);
			}
		}
		return this;
	}

	/* 
	 * Bitwise AND assignment operator.
	 * Performs a bitwise AND operation on this APInt and RHS. The result is
	 * assigned to *this.
	 * Returns *this after ANDing with RHS.
	 */
	public APInt andAssign(APInt RHS) {
		if(numBits == RHS.numBits) {
			throw new IllegalArgumentException("Bit widths must be the same");
		}
		if (isSingleWord()) {
			unsignedVals[0] = unsignedVals[0].and(RHS.getUnsignedVals()[0]);
		}
		else {
			AndAssignSlowCase(RHS);
		}
		return this;
	}

	/* Bitwise AND assignment operator.
	 *
	 * Performs a bitwise AND operation on this APInt and RHS. RHS is
	 * logically zero-extended or truncated to match the bit-width of
	 * the LHS.
	 */
	public APInt andAssign(ULong RHS) {
		if (isSingleWord()) {
			unsignedVals[0] = unsignedVals[0].and(RHS);
			return this;
		}

		unsignedVals[0] = unsignedVals[0].and(RHS);
		for(int i = 1; i < getNumWords() -1; i++) {
			unsignedVals[i] = ULong.valueOf(0);
		}
		return this;
	}

	/* Bitwise OR assignment operator.
	 *
	 * Performs a bitwise OR operation on this APInt and RHS. The result is
	 * assigned *this;
	 *
	 * Returns *this after ORing with RHS.
	 */
	public APInt orAssign(APInt RHS) {
		if(numBits != RHS.numBits) {
			throw new IllegalArgumentException("Bit widths must be the same");
		}
		if (isSingleWord()) {
			unsignedVals[0] = unsignedVals[0].or(RHS.unsignedVals[0]);
		}
		else {
			OrAssignSlowCase(RHS);
		}

		return this;
	}

	/* Bitwise OR assignment operator.
	 * Performs a bitwise OR operation on this APInt and RHS. RHS is
	 * logically zero-extended or truncated to match the bit-width of
	 * the LHS.
	 */
	public APInt orAssign(ULong RHS) {
		if (isSingleWord()) {
			unsignedVals[0] = unsignedVals[0].or(RHS);
			clearUnusedBits();
		} else {
			unsignedVals[0] = unsignedVals[0].or(RHS);
		}
		return this;
	}

	/* Bitwise XOR assignment operator.
	 *
	 * Performs a bitwise XOR operation on this APInt and RHS. The result is
	 * assigned to *this.
	 *
	 * Returns *this after XORing with RHS.
	 */
	public APInt xorAssign(APInt RHS) {
		if(numBits != RHS.numBits) {
			throw new IllegalArgumentException("Bit widths must be the same");
		}

		if (isSingleWord()) {
			unsignedVals[0] = unsignedVals[0].xor(RHS.unsignedVals[0]);
		}
		else {
			XorAssignSlowCase(RHS);
		}
		return this;
	}

	/* Bitwise XOR assignment operator.
	 *
	 * Performs a bitwise XOR operation on this APInt and RHS. RHS is
	 * logically zero-extended or truncated to match the bit-width of
	 * the LHS.
	 */
	public APInt xorAssign(ULong RHS) {
		if (isSingleWord()) {
			unsignedVals[0] = unsignedVals[0].xor(RHS);
			clearUnusedBits();
		} else {
			unsignedVals[0] = unsignedVals[0].xor(RHS);
		}
		return this;
	}

	/* Multiplication assignment operator.
	 * Multiplies this APInt by RHS and assigns the result to *this.
	 * Returns this
	 */
	public APInt mulAssign(APInt RHS) {
		if(numBits != RHS.numBits) {
			throw new IllegalArgumentException("Bit widths must be the same");
		}
		this.assign(this.mul(RHS));
		return this;
	}

	public APInt mulAssign(ULong RHS) {
		if (isSingleWord()) {
			unsignedVals[0] = unsignedVals[0].mul(RHS);
		} 
		else {
			int NumWords = getNumWords();
			tcMultiplyPart(unsignedVals, unsignedVals, RHS, ULong.valueOf(0), NumWords, NumWords, false);
		}
		return clearUnusedBits();
	}

	/* Addition assignment operator.
	 * Adds RHS to *this and assigns the result to *this.
	 * Returns this
	 */
	public APInt addAssign(final APInt RHS) {
		if(numBits != RHS.getNumBits()) {
			throw new IllegalArgumentException("Bit widths must be the same");
		}

		if (isSingleWord()) {
			unsignedVals[0] = unsignedVals[0].add(RHS.getUnsignedVals()[0]);
		}
		else {
			tcAdd(unsignedVals, RHS.getUnsignedVals(), ULong.valueOf(0), getNumWords());
		}

		return clearUnusedBits();
	}

	public APInt addAssign(final ULong RHS) {
		if (isSingleWord()) {
			unsignedVals[0] = unsignedVals[0].add(RHS);
		}
		else {
			tcAddPart(unsignedVals, RHS, getNumWords());
		}

		return clearUnusedBits();
	}

	/* Subtraction assignment operator.
	 * Subtracts RHS from *this and assigns the result to *this.
	 * Returns this
	 */
	public APInt subtractAssign(APInt RHS) {
		if(numBits != RHS.numBits) {
			throw new IllegalArgumentException("Bit widths must be the same");
		}
		if (isSingleWord())
			unsignedVals[0] = unsignedVals[0].subtract(RHS.unsignedVals[0]);
		else
			tcSubtract(unsignedVals, RHS.unsignedVals, ULong.valueOf(0), getNumWords());
		return clearUnusedBits();
	}

	public APInt subtractAssign(ULong RHS) {
		if (isSingleWord()) {
			unsignedVals[0] = unsignedVals[0].subtract(RHS);
		}
		else {
			tcSubtractPart(unsignedVals, RHS, getNumWords());
		}

		return clearUnusedBits();
	}

	/* Left-shift assignment function.
	 *
	 * Shifts *this left by shiftAmt and assigns the result to *this.
	 *
	 * Returns *this after shifting left by ShiftAmt
	 */
	public APInt leftShiftAssign(int ShiftAmt) {
		if(ShiftAmt > numBits){
			throw new IllegalArgumentException("Invalid shift amount");
		}

		if (isSingleWord()) {
			if (ShiftAmt == numBits) {
				unsignedVals[0] = ULong.valueOf(0);
			}
			else {
				unsignedVals[0] = unsignedVals[0].leftShift(ShiftAmt);
			}
			return clearUnusedBits();
		}

		shlSlowCase(ShiftAmt);

		return this;
	}

	/* Left-shift assignment function.
	 * Shifts this left by shiftAmt and assigns the result to *this.
	 *
	 * Returns this after shifting left by ShiftAmt
	 */
	public APInt leftShiftAssign(final APInt ShiftAmt){
		// It's undefined behavior in C to shift by numBits or greater.
		this.assign(ShiftAmt.getLimitedValue(ULong.valueOf(numBits)));
		return this;
	}

	// ****************************************************************** BINARY OPERATORS ************************************************************

	/* Multiplication operator.
	 *
	 * Multiplies this APInt by RHS and returns the result.
	 */

	public APInt mul(APInt other) {
		return mul(other.unsignedVals[0]).clone();
	}

	public APInt mul(final ULong RHS) {
		if (isSingleWord()) {
			unsignedVals[0] = unsignedVals[0].mul(RHS);
		}
		else {
			int numWords = getNumWords();
			tcMultiplyPart(unsignedVals, unsignedVals, RHS, ULong.valueOf(0), numWords, numWords, false);
		}

		return clearUnusedBits();
	}

	/* Left logical shift operator.
	 * Shifts this APInt left by Bits and returns the result.
	 */
	APInt leftShift(int Bits) {
		return shl(Bits); 
	}

	/* Left logical shift operator.
	 * Shifts this APInt left by \p Bits and returns the result.
	 */
	APInt leftShift(APInt Bits) { 
		return shl(Bits); 
	}

	/* 
	 * Logical right-shift function.
	 * Logical right-shift this APInt by shiftAmt.
	 */
	APInt lshr(int shiftAmt) {
		APInt R = this.clone();
		R.lshrInPlace(shiftAmt);
		return R;
	}

	/*
	 *  Logical right-shift this APInt by ShiftAmt in place.
	 */
	public void lshrInPlace(int ShiftAmt) {
		if(ShiftAmt > numBits) {
			throw new IllegalArgumentException("Invalid shift amount");
		}

		if (isSingleWord()) {
			if (ShiftAmt == numBits) {
				unsignedVals[0] = ULong.valueOf(0);
			}
			else {
				unsignedVals[0] = unsignedVals[0].rightShift(ShiftAmt);
			}
			return;
		}
		lshrSlowCase(ShiftAmt);
	}

	/* 
	 * Left-shift function.
	 * Left-shift this APInt by shiftAmt.
	 */
	public APInt shl(int shiftAmt) {
		APInt R = clone();
		R.leftShiftAssign(shiftAmt);
		return R;
	}

	/*
	 *Rotate left by rotateAmt.
	 */
	public APInt rotl(int rotateAmt) {
		rotateAmt %= numBits;
		if (rotateAmt == 0) {
			return this;
		}

		return shl(rotateAmt).or(lshr(numBits - rotateAmt));
	}

	/*
	 * Rotate right by rotateAmt.
	 */
	public  APInt rotr(int rotateAmt) {
		rotateAmt %= numBits;
		if (rotateAmt == 0) {
			return this;
		}
		return lshr(rotateAmt).or(shl(numBits - rotateAmt));
	}

	/* Arithmetic right-shift function.
	 * Arithmetic right-shift this APInt by shiftAmt.
	 */
	public  APInt ashr(APInt ShiftAmt) {
		APInt R = clone();
		R.ashrInPlace(ShiftAmt);
		return R;
	}

	/* 
	 * Arithmetic right-shift this APInt by shiftAmt in place.
	 */
	public  void ashrInPlace(APInt shiftAmt) {
		ashrInPlace(shiftAmt.getLimitedValue(ULong.valueOf(numBits)).longValue());
	}

	/*
	 * Logical right-shift this APInt by ShiftAmt in place.
	 */
	public void lshrInPlace(APInt shiftAmt) {
		lshrInPlace((int)shiftAmt.getLimitedValue(ULong.valueOf(numBits)).longValue());
	}

	/* 
	 * Rotate right by rotateAmt.
	 */
	public APInt rotr(APInt rotateAmt) {
		return rotr(rotateModulo(numBits, rotateAmt));
	}

	/* Unsigned division operation.
	 *
	 * Perform an int divide operation on this APInt by RHS. Both this and
	 * RHS are treated as int quantities for purposes of this division.
	 *
	 * Returns a new APInt value containing the division result, rounded towards
	 * zero.
	 */
	public APInt udiv(APInt RHS) {
		if(numBits != RHS.numBits) {
			throw new IllegalArgumentException("Bit widths must be the same");
		}

		// First, deal with the easy case
		if (isSingleWord()) {
			if(RHS.unsignedVals[0].equals(0)) {
				throw new IllegalArgumentException("Divide by zero?");
			}
			return new APInt(numBits, unsignedVals[0].div(RHS.unsignedVals[0]), false);
		}

		// Get some facts about the LHS and RHS number of bits and words
		int lhsWords = getNumWords(getActiveBits());
		int rhsBits = RHS.getActiveBits();
		int rhsWords = getNumWords(rhsBits);
		if(rhsWords <= 0) {
			throw new IllegalArgumentException("Divided by zero???");
		}

		// Deal with some degenerate cases
		if (lhsWords <= 0) // 0 / X ===> 0
			return new APInt(numBits, ULong.valueOf(0), false);
		if (rhsBits == 1)  // X / 1 ===> X
			return this;
		if (lhsWords < rhsWords || this.ult(RHS)) // X / Y ===> 0, iff X < Y
			return new APInt(numBits, ULong.valueOf(0), false);
		if (this.equals(RHS)) // X / X ===> 1
			return new APInt(numBits, ULong.valueOf(1), false);
		if (lhsWords == 1) // rhsWords is 1 if lhsWords is 1.
			// All high words are zero, just use native divide
			return new APInt(numBits, unsignedVals[0].div(RHS.unsignedVals[0]), false);

		// We have to compute it the hard way. Invoke the Knuth divide algorithm.

		QuotientRemainderPair qrPair = divideWithBigInts(unsignedVals, getNumWords(), RHS.getUnsignedVals(), RHS.getNumWords());
		return qrPair.getQuotient();
	}

	public APInt udiv(ULong RHS) {
		if(RHS.equals(0)) {
			throw new IllegalArgumentException("Divide by zero?");
		}

		// First, deal with the easy case
		if (isSingleWord()) {
			return new APInt(numBits, unsignedVals[0].div(RHS), false);
		}

		// Get some facts about the LHS words.
		int lhsWords = getNumWords(getActiveBits());

		// Deal with some degenerate cases
		if (lhsWords <= 0) // 0 / X ===> 0
			return new APInt(numBits, ULong.valueOf(0), false);
		if (RHS.equals(1))  // X / 1 ===> X
			return this;
		if (ult(RHS)) // X / Y ===> 0, iff X < Y
			return new APInt(numBits, ULong.valueOf(0), false);
		if (this.equals(RHS))		    // X / X ===> 1
			return new APInt(numBits, ULong.valueOf(1), false);
		if (lhsWords == 1) // rhsWords is 1 if lhsWords is 1.
			// All high words are zero, just use native divide
			return new APInt(numBits, unsignedVals[0].div(RHS), false);

		// We have to compute it the hard way. Invoke the Knuth divide algorithm.
		QuotientRemainderPair qrPair = divideWithBigInts(unsignedVals, getNumWords(), new ULong[] {RHS}, 1);
		return qrPair.getQuotient();
	}

	/* Signed division function for APInt.
	 * Signed divide this APInt by APInt RHS.
	 * The result is rounded towards zero.
	 */
	public APInt sdiv(APInt RHS) {
		if (isNegative()) {
			if (RHS.isNegative()) {
				return (this.mul(ULong.valueOf(-1))).udiv(RHS.mul(ULong.valueOf(-1)));
			}
			return (this.mul(ULong.valueOf(-1)).udiv(RHS)).mul(ULong.valueOf(-1));
		}

		if (RHS.isNegative()) {
			return this.udiv(RHS.mul(ULong.valueOf(-1))).mul(ULong.valueOf(-1));
		}

		return this.udiv(RHS);
	}

	public APInt sdiv(long RHS) {
		if (isNegative()) {
			if (RHS < 0)
				return (this.mul(ULong.valueOf(-1))).udiv(ULong.valueOf(RHS * -1));
			return (this.mul(ULong.valueOf(-1)).udiv(ULong.valueOf(RHS))).mul(ULong.valueOf(-1));
		}
		if (RHS < 0)
			return (this.udiv(ULong.valueOf(RHS * -1))).mul(ULong.valueOf(-1));
		return this.udiv(ULong.valueOf(RHS));
	}

	/* Unsigned remainder operation.
	 *
	 * Perform an int remainder operation on this APInt with RHS being the
	 * divisor. Both this and RHS are treated as int quantities for purposes
	 * of this operation. Note that this is a true remainder operation and not a
	 * modulo operation because the sign follows the sign of the dividend which
	 * is *this.
	 *
	 * Returns a new APInt value containing the remainder result
	 */
	public APInt urem(APInt RHS) {
		if(numBits != RHS.numBits) {
			throw new IllegalArgumentException("Bit widths must be the same");
		}

		if (isSingleWord()) {
			if(RHS.unsignedVals[0].equals(0)) {
				throw new IllegalArgumentException("Remainder by zero?");
			}
			return new APInt(numBits, RHS.unsignedVals[0].modulo(RHS.unsignedVals[0]), false);
		}

		// Get some facts about the LHS
		int lhsWords = getNumWords(getActiveBits());

		// Get some facts about the RHS
		int rhsBits = RHS.getActiveBits();
		int rhsWords = getNumWords(rhsBits);
		if(rhsWords <= 0){
			throw new IllegalArgumentException("Performing remainder operation by zero ???");
		}

		// Check the degenerate cases
		if (lhsWords == 0) // 0 % Y ===> 0
			return new APInt(numBits, ULong.valueOf(0), false);
		if (rhsBits == 1) // X % 1 ===> 0
			return new APInt(numBits, ULong.valueOf(0), false);
		if (lhsWords < rhsWords || this.ult(RHS)) // X % Y ===> X, iff X < Y
			return this;
		if (this.equals(RHS)) // X % X == 0;
			return new APInt(numBits, ULong.valueOf(0), false);
		if (lhsWords == 1)  // All high words are zero, just use native remainder
			return new APInt(numBits, RHS.unsignedVals[0].modulo(RHS.unsignedVals[0]), false);

		// We have to compute it the hard way. Invoke the Knuth divide algorithm.
		return divideWithBigInts(unsignedVals, lhsWords, RHS.unsignedVals, rhsWords).getRemainderApInt();
	}

	public ULong urem(ULong RHS) {
		if(RHS.equals(0)){
			throw new IllegalArgumentException("Remainder by zero?");
		}

		if (isSingleWord())
			return unsignedVals[0].modulo(RHS);

		// Get some facts about the LHS
		int lhsWords = getNumWords(getActiveBits());

		// Check the degenerate cases
		if (lhsWords == 0) // 0 % Y ===> 0
			return ULong.valueOf(0);
		if (RHS.equals(1)) // X % 1 ===> 0
			return ULong.valueOf(0);
		if (this.ult(RHS)) // X % Y ===> X, iff X < Y
			return getZExtValueNew();
		if (this.equals(RHS)) // X % X == 0;
			return ULong.valueOf(0);
		if (lhsWords == 1) // All high words are zero, just use native remainder
			return unsignedVals[0].modulo(RHS);

		// We have to compute it the hard way. Invoke the Knuth divide algorithm.
		APInt remainderAPInt = divideWithBigInts(unsignedVals, lhsWords, new ULong[] {RHS}, 1).getRemainderApInt();
		return remainderAPInt.getUnsignedVals()[0];
	}

	/* Function for signed remainder operation.
	 *
	 * Signed remainder operation on APInt.
	 */
	public APInt srem(APInt RHS) {
		if (isNegative()) {
			if (RHS.isNegative())
				return (this.mul(ULong.valueOf(-1))).urem(RHS.mul(ULong.valueOf(-1))).mul(ULong.valueOf(-1));
			return (this.mul(ULong.valueOf(-1)).urem(RHS)).mul(ULong.valueOf(-1));
		}
		if (RHS.isNegative())
			return this.urem(RHS.mul(ULong.valueOf(-1)));
		
		return this.urem(RHS);
	}

	public long srem(long RHS) {
		if (isNegative()) {
			if (RHS < 0)
				return (this.mul(ULong.valueOf(-1))).urem(ULong.valueOf(RHS * -1)).mul(-1).longValue();
			return (this.mul(ULong.valueOf(-1)).urem(ULong.valueOf(RHS))).mul(ULong.valueOf(-1)).longValue();
		}
		if (RHS < 0)
			return this.udiv(ULong.valueOf(RHS * -1)).getUnsignedVals()[0].longValue();
			
		return this.udiv(ULong.valueOf(RHS)).getUnsignedVals()[0].longValue();
	}

	/* Dual division/remainder interface.
	 *
	 * Sometimes it is convenient to divide two APInt values and obtain both the
	 * quotient and remainder. This function does both operations in the same
	 * computation making it a little more efficient.
	 */
	public static QuotientRemainderPair udivrem(APInt LHS, APInt RHS) {
		if(LHS.numBits != RHS.numBits){
			throw new IllegalArgumentException("Bit widths must be the same");
		}
		
	  int numBits = LHS.numBits;
	  
	  APInt quotient, remainderApInt;
	  
	  // First, deal with the easy case
	  if (LHS.isSingleWord()) {
	    if(RHS.unsignedVals[0].equals(0)){
			throw new IllegalArgumentException("Divide by zero?");
		}
	    
	    ULong QuotVal = LHS.unsignedVals[0].div(RHS.unsignedVals[0]);
	    ULong RemVal = LHS.unsignedVals[0].modulo(RHS.unsignedVals[0]);
	    quotient = new APInt(numBits, QuotVal, false);
	    remainderApInt = new APInt(numBits, RemVal, false);
	    return new QuotientRemainderPair(quotient, remainderApInt);
	  }

	  // Get some size facts about the dividend and divisor
	  int lhsWords = LHS.getNumWords(LHS.getActiveBits());
	  int rhsBits = RHS.getActiveBits();
	  int rhsWords = RHS.getNumWords(rhsBits);
	  if(rhsWords <= 0) {
		  throw new IllegalArgumentException("Performing divrem operation by zero ???");
	  }

	  // Check the degenerate cases
	  if (lhsWords == 0) {
		  quotient = new APInt(numBits, ULong.valueOf(0), false);  // 0 / Y ===> 0
		  remainderApInt = new APInt(numBits, ULong.valueOf(0), false);  // 0 % Y ===> 0
		  return new QuotientRemainderPair(quotient, remainderApInt);
	  }

	  if (rhsBits == 1) {
		  quotient = LHS;                 // X / 1 ===> X
		  remainderApInt = new APInt(numBits, ULong.valueOf(0), false); // X % 1 ===> 0
	  }

	  if (lhsWords < rhsWords || LHS.ult(RHS)) {
		  remainderApInt = LHS;                                   // X % Y ===> X, iff X < Y
		  quotient = new APInt(numBits, ULong.valueOf(0), false); // X / Y ===> 0, iff X < Y
		  return new QuotientRemainderPair(quotient, remainderApInt);
	  }

	  if (LHS == RHS) {
		  quotient = new APInt(numBits, ULong.valueOf(1), false);  // X / X ===> 1
		  remainderApInt = new APInt(numBits, ULong.valueOf(0), false); // X % X ===> 0;
		  return new QuotientRemainderPair(quotient, remainderApInt);
	  }

	  if (lhsWords == 1) { // rhsWords is 1 if lhsWords is 1.
	    // There is only one word to consider so use the native versions.
	    ULong lhsValue = LHS.unsignedVals[0];
	    ULong rhsValue = RHS.unsignedVals[0];
	    quotient = new APInt(numBits, lhsValue.div(rhsValue), false);
	    remainderApInt = new APInt(numBits, lhsValue.modulo(rhsValue), false);
	    return new QuotientRemainderPair(quotient, remainderApInt);
	  }

	  // Okay, lets do it the long way
	  QuotientRemainderPair qrp =  divideWithBigInts(LHS.unsignedVals, lhsWords, RHS.unsignedVals, rhsWords);
	  
	  // Clear the rest of the Quotient and Remainder.
	  setWordValues(qrp.getQuotient().unsignedVals, 0, lhsWords);
	  setWordValues(qrp.getRemainderApInt().unsignedVals, 0, lhsWords);
	  
	  return qrp;
	}
	
	public static void setWordValues(ULong vals[], long value, int offSet) {
		for(int i = offSet; i < vals.length; i++) {
			vals[i] = ULong.valueOf(value);
		}
		
	}
	
	public static QuotientRemainderPair udivrem(APInt LHS, ULong RHS) {
		if(RHS.equals(ULong.valueOf(0))) {
			throw new IllegalArgumentException("Divide by zero?");
		}

		int BitWidth = LHS.getNumBits();

		QuotientRemainderPair qrp = null;

		// First, deal with the easy case
		if (LHS.isSingleWord()) {
			ULong QuotVal = LHS.getUnsignedVals()[0].div(RHS);
			ULong Remainder = LHS.getUnsignedVals()[0].modulo(RHS);
			APInt quotient = new APInt(BitWidth, new ULong[] {QuotVal}, false);

			qrp = new QuotientRemainderPair(quotient, Remainder);

			return qrp;
		}

		// Get some size facts about the dividend and divisor
		int lhsWords = LHS.getNumWords(LHS.getActiveBits());

		// Check the degenerate cases
		if (lhsWords == 0) {
			APInt quotient = new APInt(BitWidth, new ULong[] {ULong.valueOf(0)}, false);    // 0 / Y ===> 0
			ULong Remainder = ULong.valueOf(0);                                     // 0 % Y ===> 0
			qrp = new QuotientRemainderPair(quotient, Remainder);
			return qrp;
		}

		if (RHS.equals(ULong.valueOf(1))) {
			APInt quotient = LHS;                   // X / 1 ===> X
			ULong Remainder = ULong.valueOf(0);                    // X % 1 ===> 0
			qrp = new QuotientRemainderPair(quotient, Remainder);
			return qrp;
		}

		if (LHS.ult(RHS)) {
			ULong Remainder = LHS.getZExtValueNew();      // X % Y ===> X, iff X < Y
			APInt quotient = new APInt(BitWidth, new ULong[] {ULong.valueOf(0)}, false);             // X / Y ===> 0, iff X < Y
			qrp = new QuotientRemainderPair(quotient, Remainder);
			return qrp;
		}

		if (LHS.equals(RHS)) {
			APInt quotient  = new APInt(BitWidth, new ULong[] {ULong.valueOf(1)}, false);   // X / X ===> 1
			ULong Remainder = ULong.valueOf(0);                               // X % X ===> 0;
			qrp = new QuotientRemainderPair(quotient, Remainder);
			return qrp;
		}

		// Make sure there is enough space to hold the results.
		// NOTE: This assumes that reallocate won't affect any bits if it doesn't
		// change the size. This is necessary if Quotient is aliased with LHS.
		APInt quotient = LHS.clone();
		quotient.reallocate(BitWidth);

		if (lhsWords == 1) { // rhsWords is 1 if lhsWords is 1.
			// There is only one word to consider so use the native versions.
			ULong lhsValue = LHS.getUnsignedVals()[0];
			quotient = quotient.getAPInt(lhsValue.div(RHS));
			ULong Remainder = lhsValue.modulo(RHS);

			qrp = new QuotientRemainderPair(quotient, Remainder);

			return qrp;
		}

		// Okay, lets do it the long way
		ULong[] rhsVals = new ULong[1];
		rhsVals[0] = RHS;
		QuotientRemainderPair qrPair = divideWithBigInts(LHS.getUnsignedVals(), lhsWords, rhsVals, 1);

		// Clear the rest of the Quotient.
		setWordValues(qrPair.getQuotient().unsignedVals, 0, lhsWords);
		return qrPair;
	}
	
	public static QuotientRemainderPair sdivrem(APInt LHS, APInt RHS) {

		QuotientRemainderPair qr = null;

		if (LHS.isNegative()) {
			if (RHS.isNegative())
				qr = udivrem(LHS.mul(ULong.valueOf(-1)), RHS.mul(ULong.valueOf(-1)));
			else {
				qr = udivrem(LHS.mul(ULong.valueOf(-1)), RHS);
				qr.getQuotient().negate();
			}

			qr.getRemainderApInt().negate();
		} 
		else if (RHS.isNegative()) {
			qr = udivrem(LHS, RHS.mul(ULong.valueOf(-1)));
			qr.getQuotient().negate();
		} 
		else {
			qr = udivrem(LHS, RHS);
		}

		return qr;
	}

	public static QuotientRemainderPair sdivrem(APInt LHS, long RHS) {
		QuotientRemainderPair qr = null;
		ULong remainder;
		if (LHS.isNegative()) {
			if (RHS < 0)
				qr = udivrem(LHS.mul(ULong.valueOf(-1)), ULong.valueOf(RHS *-1));
			else {
				qr = udivrem(LHS.mul(ULong.valueOf(-1)), ULong.valueOf(RHS));
				qr.getQuotient().negate();
			}

			remainder = qr.getRemainderULong().mul(-1);
			qr.setRemainderULong(remainder);

		} else if (RHS < 0) {
			qr = udivrem(LHS, ULong.valueOf(RHS * -1));
			qr.getQuotient().negate();
		} else {
			qr = udivrem(LHS, ULong.valueOf(RHS));
		}

		return qr;
	}

	// Operations that return overflow indicators.
	public APInt sadd_ov(APInt RHS){
		APInt Res = this.add(RHS);
		boolean overflow = isNonNegative() == RHS.isNonNegative() &&
				Res.isNonNegative() != isNonNegative();
		Res.setOpOverflowFlag(overflow);
		return Res;
	}
	
	public APInt uadd_ov(APInt RHS){
		APInt Res = this.add(RHS);
		boolean overflow = Res.ult(RHS);
		Res.setOpOverflowFlag(overflow);
		return Res;
	}
	
	public APInt ssub_ov(APInt RHS){ 
		APInt Res = this.subtract(RHS);
		boolean overflow = isNonNegative() != RHS.isNonNegative() &&
				Res.isNonNegative() != isNonNegative();
		Res.setOpOverflowFlag(overflow);
		return Res;
	}
	
	public APInt usub_ov(APInt RHS){ 
		APInt Res = this.subtract(RHS);
		boolean overflow = Res.ugt(this);
		Res.setOpOverflowFlag(overflow);
		return Res;
	}
	
	public APInt sdiv_ov(APInt RHS){ 
		// MININT/-1  -->  overflow.
		boolean overflow = isMinSignedValue() && RHS.isAllOnesValue();
		APInt Res = this.sdiv(RHS);
		Res.setOpOverflowFlag(overflow);
		return Res;
	}
	
	public APInt smul_ov(APInt RHS){ 
		APInt Res = this.mul(RHS);
		boolean overflow;

		if (this.equals(0) && RHS.equals(0))
			overflow = Res.sdiv(RHS) != this || Res.sdiv(this) != RHS;
		else
			overflow = false;
		Res.setOpOverflowFlag(overflow);
		return Res;
	}
	
	public APInt umul_ov(APInt RHS){
		if (countLeadingZeros() + RHS.countLeadingZeros() + 2 <= numBits) {
			APInt Res = this.mul(RHS);
			Res.setOpOverflowFlag(true);
			return Res;
		}

		boolean overflow;
		APInt Res =  this.lshr(1).mul(RHS);
		overflow = Res.isNegative();
		Res.leftShiftAssign(1);
		if (unsignedVals[0].isGreaterThan(0)) {
			Res.addAssign(RHS);
			if (Res.ult(RHS))
				overflow = true;
		}
		
		Res.setOpOverflowFlag(overflow);
		
		return Res;
	}
	
	public APInt sshl_ov(APInt ShAmt){ 
		boolean overflow = ShAmt.uge(ULong.valueOf(getNumBits()));
		if (overflow) {
			APInt res = new APInt(numBits, ULong.valueOf(0), false);
			res.setOpOverflowFlag(true);
			return res;
		}

		if (isNonNegative()) // Don't allow sign change.
			overflow = ShAmt.uge(ULong.valueOf(countLeadingZeros()));
		else
			overflow = ShAmt.uge(ULong.valueOf(countLeadingOnes()));

		APInt res = this.leftShift(ShAmt);
		res.setOpOverflowFlag(overflow);

		return res;
	}
	
	public APInt ushl_ov(APInt ShAmt){ 
		boolean overflow = ShAmt.uge(ULong.valueOf(getNumBits()));
		if (overflow) {
			APInt res = new APInt(numBits, ULong.valueOf(0), false);
			res.setOpOverflowFlag(true);
			return res;
		}

		  overflow = ShAmt.ugt(ULong.valueOf(countLeadingZeros()));

		  APInt res = this.leftShift(ShAmt);
		  res.setOpOverflowFlag(overflow);
		  return res;
	}


	// Operations that saturate
	public APInt sadd_sat(APInt RHS) {
		APInt Res = sadd_ov(RHS);
		if (!Res.getOpOverflowFlag())
			return Res;

		return isNegative() ? getSignedMinValue(numBits) : getSignedMaxValue(numBits);
	}

	public APInt uadd_sat(APInt RHS) {
		APInt Res = uadd_ov(RHS);
		if (!Res.getOpOverflowFlag())
			return Res;

		return getMaxValue(numBits);
	}

	public APInt ssub_sat(APInt RHS) {
		APInt Res = ssub_ov(RHS);
		if (!Res.getOpOverflowFlag())
			return Res;

		return isNegative() ? getSignedMinValue(numBits) : getSignedMaxValue(numBits);
	}

	public APInt usub_sat(APInt RHS) {
		APInt Res = usub_ov(RHS);
		if (!Res.getOpOverflowFlag())
			return Res;
		
		return new APInt(numBits, ULong.valueOf(0), false);
	}

	public APInt smul_sat(APInt RHS) {
		APInt Res = smul_ov(RHS);
		if (!Res.getOpOverflowFlag())
			return Res;

		// The result is negative if one and only one of inputs is negative.
		boolean ResIsNegative = isNegative() ^ RHS.isNegative();

		return ResIsNegative ? getSignedMinValue(numBits) : getSignedMaxValue(numBits);
	}

	public APInt umul_sat(APInt RHS) {
		APInt Res = umul_ov(RHS);
		if (!Res.getOpOverflowFlag())
			return Res;

		return getMaxValue(numBits);
	}

	public APInt sshl_sat(APInt RHS) {
		APInt Res = sshl_ov(RHS);
		if (!Res.getOpOverflowFlag())
			return Res;

		return isNegative() ? getSignedMinValue(numBits) : getSignedMaxValue(numBits);
	}

	public APInt ushl_sat(APInt RHS) {
		APInt Res = ushl_ov(RHS);
		if (!Res.getOpOverflowFlag())
			return Res;

		return getMaxValue(numBits);
	}

	/* Array-indexing support.
	 *
	 * Returns the bit value at bitPosition
	 */
	public boolean atIndex(int bitPosition) {
		if(bitPosition >= getNumBits()) {
			throw new IllegalArgumentException("Bit position out of bounds!");
		}

		return !(maskBit(bitPosition).and(getWord(bitPosition)).equals(0));
	}


	// ****************************************************************** BIT MANIPULATION OPERATORS ************************************************************

	/// Set every bit to 1.
	public void setAllBits() {
		if (isSingleWord()) {
			unsignedVals[0] = ULong.valueOf(WORDTYPE_MAX);
		}
		else {
			// Set all the bits in all the words.
			for(int i = 0; i < getNumWords(); i++) {
				unsignedVals[i] = ULong.valueOf(WORDTYPE_MAX);
			}

		}
		// Clear the unused ones
		clearUnusedBits();
	}

	/// Set the top bits starting from loBit.
	public void setBitsFrom(int loBit) {
		setBits(loBit, numBits);
	}

	/// Set the bottom loBits bits.
	public void setLowBits(int loBits) {
		setBits(0, loBits);
	}

	/// Set the top hiBits bits.
	public void setHighBits(int hiBits) {
		setBits(numBits - hiBits, numBits);
	}

	/// Set every bit to 0.
	public void clearAllBits() {
		if (isSingleWord()) {
			unsignedVals[0] = ULong.valueOf(0);
		}
		else {
			// Clear all the bits in all the words.
			for(int i = 0; i < getNumWords(); i++) {
				unsignedVals[i] = ULong.valueOf(0);
			}
		}
	}

	/// Set bottom loBits bits to 0.
	public void clearLowBits(int loBits) {
		if(loBits > numBits) {
			throw new IllegalArgumentException("More bits than bitwidth");
		}

		APInt Keep = getHighBitsSet(numBits, numBits - loBits);
		this.andAssign(Keep);
	}

	/*
	 * Set the sign bit to 0.
	 */
	public void clearSignBit() {
		clearBit(numBits - 1);
	}

	/// Toggles a given bit to its opposite value.
	///
	/// Toggle a given bit to its opposite value whose position is given
	/// as "bitPosition".
	void flipBit(int bitPosition) {

	}

	/// Insert the bits from a smaller APInt starting at bitPosition.
	void insertBits(APInt SubBits, int bitPosition) {

	}

	void insertBits(ULong SubBits, int bitPosition, int numBits) {

	}

	/*
	 * Return an APInt with the extracted bits [bitPosition,bitPosition+numBits).
	 */
	public APInt extractBits(int numBits, int bitPosition) {
		if(numBits <= 0) {
			throw new IllegalArgumentException("Can't extract zero bits");
		}

		if(!(bitPosition < this.numBits && (numBits + bitPosition) <= this.numBits)){
			throw new IllegalArgumentException("Illegal bit extraction");
		}

		if (isSingleWord()) {
			return new APInt(numBits, unsignedVals[0].rightShift(bitPosition), false);
		}

		int loBit = whichBit(bitPosition);
		int loWord = whichWord(bitPosition);
		int hiWord = whichWord(bitPosition + numBits - 1);

		// Single word result extracting bits from a single word source.
		if (loWord == hiWord) {
			return new APInt(numBits, unsignedVals[loWord].rightShift(loBit), false);
		}

		// Extracting bits that start on a source word boundary can be done
		// as a fast memory copy.
		if (loBit == 0) {
			ULong[] vals = new ULong[1 + hiWord];
			for(int i = 0; i < vals.length; i++) {
				vals[i] = unsignedVals[i + loWord];
			}

			return new APInt(numBits, vals, false);
		}

		// General case - shift + copy source words directly into place.
		APInt Result = new APInt(numBits, ULong.valueOf(0), false);
		int NumSrcWords = getNumWords();
		int NumDstWords = Result.getNumWords();

		ULong[] DestPtr = Result.getUnsignedVals();
		for (int word = 0; word < NumDstWords; ++word) {
			ULong w0 = unsignedVals[loWord + word];
			ULong w1 = (loWord + word + 1) < NumSrcWords ? unsignedVals[loWord + word + 1] : ULong.valueOf(0);
			DestPtr[word] = w0.rightShift(loBit).or(w1.leftShift((AP_INT_BITS_PER_WORD - loBit)));
		}

		return Result.clearUnusedBits();
	}

	public ULong extractBitsAsZExtValue(int numBits, int bitPosition) {
		if(numBits <= 0) {
			throw new IllegalArgumentException("Can't extract zero bits");
		}
		if(!(bitPosition < this.numBits && (numBits + bitPosition) <= this.numBits)) {
			throw new IllegalArgumentException("Illegal bit extraction");
		}
		if(numBits > 64) {
			throw new IllegalArgumentException("Illegal bit extraction");
		}

		ULong maskBits = MathUtils.maskTrailingOnes(numBits);
		if (isSingleWord()) {
			return unsignedVals[0].rightShift(bitPosition).and(maskBits);
		}

		int loBit = whichBit(bitPosition);
		int loWord = whichWord(bitPosition);
		int hiWord = whichWord(bitPosition + numBits - 1);
		if (loWord == hiWord) {
			return unsignedVals[loWord].rightShift(loBit).and(maskBits);
		}

		int wordBits = 64;
		ULong retBits = unsignedVals[loWord].rightShift(loBit);
		retBits = retBits.or(unsignedVals[hiWord].leftShift(wordBits - loBit));
		retBits = retBits.and(maskBits);
		return retBits;

	}


	/* Compute the number of active bits in the value
	 *
	 * This function returns the number of active bits which is defined as the
	 * bit width minus the number of leading zeros. This is used in several
	 * computations to see how "wide" the value is.
	 */
	public int getActiveBits() { return numBits - countLeadingZeros(); }

	/* The APInt version of the countLeadingZeros functions in
	 *   MathExtras.h.
	 *
	 * It counts the number of zeros from the most significant bit to the first
	 * one bit.
	 *
	 * \returns BitWidth if the value is zero, otherwise returns the number of
	 *   zeros from the most significant bit to the first one bits.
	 */
	public int countLeadingZeros() {
		if (isSingleWord()) {
			int unusedBits = AP_INT_BITS_PER_WORD - numBits;
			return MathUtils.countLeadingZeros(unsignedVals[0]) - unusedBits;
		}

		// TODO for integers larger than 64 bits
		return -1;
	}

	/* Get the number of words.
	 * Here one word's bitwidth equals to that of uint64_t.
	 * Returns the number of words to hold the integer value of this APInt.
	 */
	public int getNumWords() { return getNumWords(numBits); }

	/*Get the number of words.
	 * *NOTE* Here one word's bitwidth equals to that of ULong
	 * Returns the number of words to hold the integer value with a given bit width.
	 */
	public int getNumWords(int BitWidth) {
		return (numBits + AP_INT_BITS_PER_WORD - 1) / AP_INT_BITS_PER_WORD;
	}


	/* Count the number of leading one bits.
	 *
	 * This function is an APInt version of the countLeadingOnes
	 * functions in MathExtras.h. It counts the number of ones from the most
	 * significant bit to the first zero bit.
	 *countLeadingOnes
	 * Returns 0 if the high order bit is not set, otherwise returns the number
	 * of 1 bits from the most significant to the least
	 */

	public int countLeadingOnes() {
		if(isSingleWord()) {
			return MathUtils.countLeadingOnes(unsignedVals[0].leftShift(AP_INT_BITS_PER_WORD - numBits));
		}
		else {
			// TODO for integers larger than 64 bits
			return -1;
		}
	}

	/* Get the minimum bit size for this signed APInt
	 *
	 * Computes the minimum bit width for this APInt while considering it to be a
	 * signed (and probably negative) value. If the value is not negative, this
	 * function returns the same value as getActiveBits()+1. Otherwise, it
	 * returns the smallest bit width that will retain the negative value. For
	 * example, -1 can be written as 0b1 or 0xFFFFFFFFFF. 0b1 is shorter and so
	 * for -1, this function will always return 1.
	 */
	public int getMinSignedBits() {
		if (isNegative()) {
			int leadingOnes = countLeadingOnes();
			return numBits - leadingOnes + 1;
		}
		return getActiveBits() + 1;
	}

	public int getNumBits() {
		return numBits;
	}

	public void setNumBits(int numBits) {
		this.numBits = numBits;
	}

	/* Clear unused high order bits
	 *
	 * This method is used internally to clear the top "N" bits in the high order
	 * word that are not used by the APInt. This is needed after the most
	 * significant word is assigned a value to ensure that those bits are
	 * zero'd out.
	 */
	public APInt clearUnusedBits() {
		// Compute how many bits are used in the final word
		int wordBits = ((numBits-1) % AP_INT_BITS_PER_WORD) + 1;

		// Mask out the high bits.
		ULong mask = ULong.MAX.rightShift(AP_INT_BITS_PER_WORD - wordBits);
		if (isSingleWord()){
			unsignedVals[0] = unsignedVals[0].and(mask);
		}
		else{
			unsignedVals[getNumWords() - 1] = unsignedVals[getNumWords() - 1].and(mask);
		}

		return this;
	}

	/*Set a given bit to 0.
	 *
	 * Set the given bit to 0 whose position is given as "bitPosition".
	 */
	public void clearBit(int bitPosition) {
		if(bitPosition > numBits){
			throw new IllegalArgumentException("BitPosition out of range");
		}

		ULong mask = maskBit(bitPosition).complement();
		if (isSingleWord()){
			unsignedVals[0] = unsignedVals[0].and(mask);
		}
		else{
			int wordIndex = whichWord(bitPosition);
			unsignedVals[wordIndex] = unsignedVals[wordIndex].and(mask);
		}
	}

	public String toString() {
		return toString(10, true, false);
	}

	public String toString(int Radix, boolean isSigned, boolean formatAsCLiteral){

		String result = "";
		if(!(Radix == 10 || Radix == 8 || Radix == 16 || Radix == 2 ||
				Radix == 36)) {
			throw new IllegalArgumentException("Radix should be 2, 8, 10, 16, or 36!");
		}

		String Prefix = "";
		if (formatAsCLiteral) {
			switch (Radix) {
			case 2:
				// Binary literals are a non-standard extension added in gcc 4.3:
				// http://gcc.gnu.org/onlinedocs/gcc-4.3.0/gcc/Binary-constants.html
				Prefix = "0b";
				break;
			case 8:
				Prefix = "0";
				break;
			case 10:
				break; // No prefix
			case 16:
				Prefix = "0x";
				break;
			default:
				throw new IllegalArgumentException("Invalid radix!");
			}
		}

		// First, check for a zero value and just short circuit the logic below.
		if (isSingleWord() && unsignedVals[0].longValue() == 0) {
			result += Prefix + "0";
			return result;
		}

		String Digits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		if (isSingleWord()) {

			ULong N = null;
			if (!isSigned) {
				N = getZExtValueNew();
				result += N.toString();
			} else {
				long I = getSExtValue();
				if (I >= 0) {
					result = "" + I;
				} else {
					result = "-";
					//N = -(uint64_t)I;*/
					N = ULong.valueOf(I * -1);
					result += N.toString();
				}
			}

			if(Radix == 10) {
				return result;
			}

			// TODO - Test for radix != 10
			result += Prefix;

			StringBuffer sb = new StringBuffer();
			while (N.longValue() != 0) {
				sb.append(Digits.charAt(N.modulo(Radix).intValue()));
				N  = N.div(Radix);
			}

			result += sb.toString();
		}

		result = toStringMultiWord(Radix, Prefix, isSigned);
		return result;
	}

	protected String toStringMultiWord(int Radix, String Prefix, boolean isSigned) {
		String Digits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String result = "";
		APInt Tmp = this.clone();

		if (isSigned && isNegative()) {
			// They want to print the signed version and it is a negative value
			// Flip the bits and add one to turn it into the equivalent positive
			// value and put a '-' in the result.
			Tmp.negate();
			result += "-";
		}

		result += Prefix;

		// We insert the digits backward, then reverse them to get the right order.
		int StartDig = result.length();

		// For the 2, 8 and 16 bit cases, we can just shift instead of divide
		// because the number of bits per digit (1, 3 and 4 respectively) divides
		// equally.  We just shift until the value is zero.
		if (Radix == 2 || Radix == 8 || Radix == 16) {
			// Just shift tmp right for each digit width until it becomes zero
			int ShiftAmt = (Radix == 16 ? 4 : (Radix == 8 ? 3 : 1));
			int MaskAmt = Radix - 1;

			while (Tmp.getBoolValue()) {
				// TODO Implement Radix != 10
				//unsigned Digit = unsigned(Tmp.getRawData()[0]) & MaskAmt;
				//Str.push_back(Digits[Digit]);
				//Tmp.lshrInPlace(ShiftAmt);
			}
		} else {
			while (Tmp.getBoolValue()) {
				ULong Digit;
				QuotientRemainderPair qrPair = udivrem(Tmp, ULong.valueOf(Radix));
				Tmp = qrPair.getQuotient();
				Digit = qrPair.getRemainderULong();
				if(Digit.isGreaterThan(Radix)) {
					throw new IllegalArgumentException("divide failed");
				}
				result += "" + (Digits.charAt(Digit.intValue()));
			}
		}

		// Reverse the digits before returning.
		result = new StringBuffer(result).reverse().toString();

		return result;
	}

	/* Addition assignment operator.
	 * In place addition.
	 * returns this
	 */
	public APInt add(final APInt RHS) {
		APInt newVal = this.clone();
		return newVal.addAssign(RHS);
	}

	public APInt add(final ULong RHS) {
		if (isSingleWord()) {
			unsignedVals[0] = unsignedVals[0].add(RHS);
		}
		else {
			tcAddPart(unsignedVals, RHS, getNumWords());
		}

		return clearUnusedBits();
	}

	public APInt subtract(final APInt other) {
		return subtract(other.unsignedVals[0]);
	}

	public APInt subtract(final ULong RHS) {
		if (isSingleWord()) {
			unsignedVals[0] = unsignedVals[0].subtract(RHS);
		}
		else {
			// TODO Implement this
			//tcAddPart(U.pVal, RHS, getNumWords());
		}
		return clearUnusedBits();
	}



	// TODO Replace above with the ones below:

	public APInt andWith(APInt other) {
		return null;
	}

	public APInt xorWith(APInt other) {
		return null;
	}

	/* Left-shift assignment function.
	 * Shifts *this left by shiftAmt and assigns the result to *this.
	 * Returns *this after shifting left by ShiftAmt
	 */
	public APInt shl(APInt ShiftAmt) {
		// TODO Implement this
		return null;
	}

	public APInt lshr(APInt other) {
		return null;
	}

	/* Logical negation operator.
	 * Performs logical negation operation on this APInt.
	 * Returns true if *this is zero, false otherwise.
	 */
	public boolean isNot() {
		if (isSingleWord()) {
			return unsignedVals[0].equals(ULong.valueOf(0));
		}
		return countLeadingZerosSlowCase() == numBits;
	}

	public APInt shiftLeft(int numBits) {
		return null;
	}

	/* Zero extend to a new width.
	 * 
	 *  This operation zero extends the APInt to a new width. The high order bits
	 *  are filled with 0 bits.  It is an error to specify a width that is less
	 * than or equal to the current width.
	 */
	public APInt zext(int width){
		if(width < numBits) {
			throw new IllegalArgumentException("Invalid APInt ZeroExtend request");
		}

		if (width <= AP_INT_BITS_PER_WORD) {
			return new APInt(width, unsignedVals, false);
		}

		// TODO Implement this
		/*
		  APInt Result(getMemory(getNumWords(width)), width);

		  // Copy words.
		  std::memcpy(Result.U.pVal, getRawData(), getNumWords() * APINT_WORD_SIZE);

		  // Zero remaining words.
		  std::memset(Result.U.pVal + getNumWords(), 0,
		              (Result.getNumWords() - getNumWords()) * APINT_WORD_SIZE);

		  return Result;
		 */
		return null;
	}

	/* Sign extend to a new width.
	 *
	 * This operation sign extends the APInt to a new width. If the high order
	 * bit is set, the fill on the left will be done with 1 bits, otherwise zero.
	 * It is an error to specify a width that is less than or equal to the
	 * current width.
	 */
	public APInt sext(int width){
		if(width < numBits) {
			throw new IllegalArgumentException("Invalid APInt SignExtend request");
		}

		if (width <= AP_INT_BITS_PER_WORD) {
			long signExtended = MathUtils.signExtend64(unsignedVals[0], numBits);
			return new APInt(width, new ULong[] {ULong.valueOf(signExtended)}, false);
		}

		// TODO Implement this
		/*
		  APInt Result(getMemory(getNumWords(Width)), Width);

		  // Copy words.
		  std::memcpy(Result.U.pVal, getRawData(), getNumWords() * APINT_WORD_SIZE);

		  // Sign extend the last word since there may be unused bits in the input.
		  Result.U.pVal[getNumWords() - 1] =
		      SignExtend64(Result.U.pVal[getNumWords() - 1],
		                   ((BitWidth - 1) % APINT_BITS_PER_WORD) + 1);

		  // Fill with sign bits.
		  std::memset(Result.U.pVal + getNumWords(), isNegative() ? -1 : 0,
		              (Result.getNumWords() - getNumWords()) * APINT_WORD_SIZE);
		  Result.clearUnusedBits();
		  return Result;
		 */
		return null;
	}

	/* Sign extend or truncate to width

	 * Make this APInt have the bit width given by width. The value is sign
	 * extended, truncated, or left alone to make it that width.
	 */
	public APInt sextOrTrunc(int width){
		if (numBits < width)
			return sext(width);
		if (numBits > width)
			return trunc(width);
		return this;
	}

	/* Zero extend or truncate to width
	 *
	 * Make this APInt have the bit width given by width. The value is zero
	 * extended, truncated, or left alone to make it that width.
	 */
	public APInt zextOrTrunc(int width){
		if (numBits < width)
			return zext(width);
		if (numBits > width)
			return trunc(width);
		return this;
	}

	/*Unsigned less than comparison
	  ///
	  /// Regards both *this and RHS as unsigned quantities and compares them for
	  /// the validity of the less-than relationship.
	  ///
	  /// \returns true if *this < RHS when both are considered unsigned.
	 */
	public boolean ult(APInt RHS) { return compare(RHS) < 0; }

	/* Unsigned less than comparison
	  ///
	  /// Regards both *this as an unsigned quantity and compares it with RHS for
	  /// the validity of the less-than relationship.
	  ///
	  /// \returns true if *this < RHS when considered unsigned.
	 */
	public boolean ult(ULong RHS) {
		// Only need to check active bits if not a single word.
		return (isSingleWord() || getActiveBits() <= 64) && getZExtValueNew().isLesserThan(RHS);
	}

	/* Determine if this APInt just has one word to store value.
	 * Returns true if the number of bits <= 64, false otherwise.
	 */
	protected boolean isSingleWord() {
		return numBits <= AP_INT_BITS_PER_WORD ? true : false;
	}

	/// Toggle every bit to its opposite value.
	public void flipAllBits() {
		if (isSingleWord()) {
			unsignedVals[0] = unsignedVals[0].xor(ULong.MAX);
			clearUnusedBits();
		} else {
			// TODO Implement this
			//flipAllBitsSlowCase();
		}
	}

	/*Negate this APInt in place.
	 */
	public void negate() {
		flipAllBits();
		this.add(ULong.valueOf(1));
	}

	/* Get a single bit mask.
	 *
	 * returns a ULong with only bit at "whichBit(bitPosition)" set
	 * This method generates and returns a uint64_t (word) mask for a single
	 * bit at a specific bit position. This is used to mask the bit in the
	 * corresponding word.
	 */
	public static ULong maskBit(int bitPosition) {
		return ULong.valueOf(1).leftShift(whichBit(bitPosition));
	}

	/* Determine which bit in a word a bit is in.
	 *
	 * Return the bit position in a word for the specified bit position
	 * in the APInt.
	 */
	protected static int whichBit(int bitPosition) {
		return bitPosition % AP_INT_BITS_PER_WORD;
	}

	/**
	 * Tests if the bit is set in this integer at the specified location
	 * @param numBits
	 * @return
	 */
	protected boolean isBitSet(int bitPosition) {
		if(unsignedVals[getNumWords() -1].and(maskBit(bitPosition)).longValue() == 0) {
			return false;
		}
		return true;
	}

	/* Set a given bit to 1.
	 * Set the given bit to 1 whose position is given as "bitPosition".
	 * 
	 */
	public void setBit(int bitPosition) {
		if(bitPosition > numBits) {
			throw new IllegalArgumentException("BitPosition out of range");
		}

		ULong mask = maskBit(bitPosition);
		if (isSingleWord()) {
			unsignedVals[0].or(mask);
		}
		else {
			int wordIndex = whichWord(bitPosition);
			unsignedVals[wordIndex] = unsignedVals[wordIndex].and(mask);
			//U.pVal[whichWord(BitPosition)] |= Mask;
		}
	}

	/* Set the sign bit to 1.
	 */
	public void setSignBit() {
		setBit(numBits - 1);
	}

	public ULong[] getUnsignedVals() {
		return unsignedVals;
	}

	public void setUnsignedVals(ULong[] unsignedVals) {
		this.unsignedVals = unsignedVals;
	}


	/* Equality check.
	 * Compares this APInt with RHS for the validity of the equality
	 * relationship.
	 */
	public boolean equals(APInt RHS) {
		if(numBits != RHS.getNumBits()) {
			throw new IllegalArgumentException("Comparison requires equal bit widths");
		}

		if (isSingleWord()) {
			return unsignedVals[0].equals(RHS.getUnsignedVals()[0]);
		}

		return equalSlowCase(RHS);
	}

	public boolean equalSlowCase(APInt RHS) {
		for(int i = 0; i < getNumWords(); i++) {
			ULong val = unsignedVals[i];
			ULong valOther = RHS.getUnsignedVals()[i];
			if(!val.equals(valOther)) {
				return false;
			}
		}

		return true;
	}

	/* Equality comparison.
	 *
	 * Compares this APInt with a ULong for the validity of the equality
	 * relationship.
	 * Returns true if this == val
	 */
	public boolean equals(ULong val) {
		return (isSingleWord() || getActiveBits() <= 64) && getZExtValueNew().equals(val);
	}

	/* Inequality comparison.
	 * Compares this APInt with RHS for the validity of the inequality
	 * relationship.
	 *
	 * Returns true if this != Val
	 */
	public boolean notEquals(APInt RHS) { return !equals(RHS); }

	/* Inequality comparison.
	 * Compares this APInt with a ULong for the validity of the inequality
	 * relationship.
	 *
	 * Returns true if this != Val
	 */
	public boolean notEquals(ULong Val) { return !equals(Val); }

	/* Signed comparison. Returns -1, 0, or 1 if this APInt is less than, equal
	 * to, or greater than RHS.
	 */
	public int compareSigned(APInt RHS) {
		if(numBits != RHS.getNumBits()) {
			throw new IllegalArgumentException("Bit widths must be same for comparison");
		}

		if (isSingleWord()) {
			long lhsSext = MathUtils.signExtend64(unsignedVals[0], numBits);
			long rhsSext = MathUtils.signExtend64(RHS.getUnsignedVals()[0], numBits);

			return lhsSext < rhsSext ? -1 : 1;
		}

		boolean lhsNeg = isNegative();
		boolean rhsNeg = RHS.isNegative();

		// If the sign bits don't match, then (LHS < RHS) if LHS is negative
		if (lhsNeg != rhsNeg)
			return lhsNeg ? -1 : 1;

		// Otherwise we can just use an unsigned comparison, because even negative
		// numbers compare correctly this way if both have the same signed-ness.

		// TODO Implement this
		//return tcCompare(U.pVal, RHS.U.pVal, getNumWords());
		return -1;
	}

	protected int compare(APInt RHS) {
		if(numBits != RHS.getNumBits()) {
			throw new IllegalArgumentException("Bit widths must be same for comparison");
		}
		if (isSingleWord()) {
			return unsignedVals[0].isLesserThan(RHS.unsignedVals[0]) ? -1 : 
				(unsignedVals[0].isGreaterThan(RHS.unsignedVals[0]) ? -1 : 1);
		}

		// TODO Implement this
		// return tcCompare(U.pVal, RHS.U.pVal, getNumWords());
		return -1;
	}

	/* Signed less than comparison
	 *
	 * Regards both *this and RHS as signed quantities and compares them for
	 * validity of the less-than relationship.
	 *
	 * \returns true if *this < RHS when both are considered signed.
	 */
	public boolean slt(APInt RHS){ return compareSigned(RHS) < 0; }

	/*  Signed less than comparison
	 *
	 * Regards both *this as a signed quantity and compares it with RHS for
	 * the validity of the less-than relationship.
	 *
	 * \returns true if *this < RHS when considered signed.
	 */
	boolean slt(long RHS){
		return (!isSingleWord() && getMinSignedBits() > 64) ? isNegative()
				: getSExtValue() < RHS;
	}

	/*  Unsigned less or equal comparison
	 *
	 * Regards both *this and RHS as unsigned quantities and compares them for
	 * validity of the less-or-equal relationship.
	 *
	 * \returns true if *this <= RHS when both are considered unsigned.
	 */
	public boolean ule(APInt RHS){ return compare(RHS) <= 0; }

	/*  Unsigned less or equal comparison
	 *
	 * Regards both *this as an unsigned quantity and compares it with RHS for
	 * the validity of the less-or-equal relationship.
	 *
	 * \returns true if *this <= RHS when considered unsigned.
	 */
	boolean ule(ULong RHS){ return !ugt(RHS); }

	/*  Signed less or equal comparison
	 *
	 * Regards both *this and RHS as signed quantities and compares them for
	 * validity of the less-or-equal relationship.
	 *
	 * \returns true if *this <= RHS when both are considered signed.
	 */
	public boolean sle(APInt RHS){ return compareSigned(RHS) <= 0; }

	/*  Signed less or equal comparison
	 *
	 * Regards both *this as a signed quantity and compares it with RHS for the
	 * validity of the less-or-equal relationship.
	 *
	 * \returns true if *this <= RHS when considered signed.
	 */
	public boolean sle(long RHS){ return !sgt(RHS); }

	/*  Unsigned greater than comparison
	 *
	 * Regards both *this and RHS as unsigned quantities and compares them for
	 * the validity of the greater-than relationship.
	 *
	 * \returns true if *this > RHS when both are considered unsigned.
	 */
	public boolean ugt(APInt RHS){ return !ule(RHS); }

	/*  Unsigned greater than comparison
	 *
	 * Regards both *this as an unsigned quantity and compares it with RHS for
	 * the validity of the greater-than relationship.
	 *
	 * \returns true if *this > RHS when considered unsigned.
	 */
	public boolean ugt(ULong RHS){
		// Only need to check active bits if not a single word.
		return (!isSingleWord() && getActiveBits() > 64) || getZExtValueNew().isGreaterThan(RHS);
	}

	/*  Signed greater than comparison
	 *
	 * Regards both *this and RHS as signed quantities and compares them for the
	 * validity of the greater-than relationship.
	 *
	 * \returns true if *this > RHS when both are considered signed.
	 */
	public boolean sgt(APInt RHS){ return !sle(RHS); }

	/*  Signed greater than comparison
	 *
	 * Regards both *this as a signed quantity and compares it with RHS for
	 * the validity of the greater-than relationship.
	 *
	 * \returns true if *this > RHS when considered signed.
	 */
	public boolean sgt(long RHS){
		return (!isSingleWord() && getMinSignedBits() > 64) ? !isNegative()
				: getSExtValue() > RHS;
	}

	/*  Unsigned greater or equal comparison
	 *
	 * Regards both *this and RHS as unsigned quantities and compares them for
	 * validity of the greater-or-equal relationship.
	 *
	 * \returns true if *this >= RHS when both are considered unsigned.
	 */
	public boolean uge(APInt RHS){ return !ult(RHS); }

	/*  Unsigned greater or equal comparison
	 *
	 * Regards both *this as an unsigned quantity and compares it with RHS for
	 * the validity of the greater-or-equal relationship.
	 *
	 * \returns true if *this >= RHS when considered unsigned.
	 */
	public boolean uge(ULong RHS){ return !ult(RHS); }

	/*  Signed greater or equal comparison
	 *
	 * Regards both *this and RHS as signed quantities and compares them for
	 * validity of the greater-or-equal relationship.
	 *
	 * \returns true if *this >= RHS when both are considered signed.
	 */
	public boolean sge(APInt RHS){ return !slt(RHS); }

	/*  Signed greater or equal comparison
	 *
	 * Regards both *this as a signed quantity and compares it with RHS for
	 * the validity of the greater-or-equal relationship.
	 *
	 * \returns true if *this >= RHS when considered signed.
	 */
	public boolean sge(long RHS){ return !slt(RHS); }


	/* Get zero extended value
	 *
	 * This method attempts to return the value of this APInt as a zero extended
	 * uint64_t. The bitwidth must be <= 64 or the value must fit within a
	 * uint64_t. Otherwise an assertion will result.
	 */
	public ULong getZExtValueNew() {
		if (isSingleWord()) {
			return unsignedVals[0];
		}
		if(getActiveBits() <= 64) {
			throw new IllegalArgumentException("Too many bits for uint64_t");
		}
		// TODO Implement more than 64 bits
		//return U.pVal[0];
		return null;
	}

	/* Get sign extended value
	 *
	 * This method attempts to return the value of this APInt as a sign extended
	 * int64_t. The bit width must be <= 64 or the value must fit within an
	 * int64_t. Otherwise an assertion will result.
	 */
	public long getSExtValue() {
		if (isSingleWord()) {
			return MathUtils.signExtend64(unsignedVals[0], numBits);
		}
		if(getMinSignedBits() > 64) {
			throw new IllegalArgumentException("Too many bits for int64_t");
		}

		return unsignedVals[0].longValue();
	}

	/* This function returns a pointer to the internal storage of the APInt.
	 * This is useful for writing out the APInt in binary form without any
	 * conversions.
	 */
	public ULong getRawData()  {
		if (isSingleWord()) {
			return unsignedVals[0];
		}

		// TODO Implement this
		// return &U.pVal[0];
		return null;
	}


	/* Truncate to new width.
	 *
	 * Truncate the APInt to a specified width. It is an error to specify a width
	 *  that is greater than or equal to the current width.
	 */
	public APInt trunc(int width) {
		if(width >= numBits) {
			throw new IllegalArgumentException("Invalid APInt Truncate request");
		}
		if(width <= 0) {
			throw new IllegalArgumentException("Can't truncate to 0 bits");
		}

		APInt result = null;
		if (width <= AP_INT_BITS_PER_WORD) {
			result = new APInt(width, unsignedVals, false);
			return result;
		}

		// TODO Implement this
		/*APInt Result(getMemory(getNumWords(width)), width);

		// Copy full words.
		unsigned i;
		for (i = 0; i != width / APINT_BITS_PER_WORD; i++)
			Result.U.pVal[i] = U.pVal[i];

		// Truncate and copy any partial word.
		unsigned bits = (0 - width) % APINT_BITS_PER_WORD;
		if (bits != 0)
			Result.U.pVal[i] = U.pVal[i] << bits >> bits;

		return Result;
		 */
		return result;
	}

	/* Arithmetic right-shift this APInt by ShiftAmt in place.
	 */
	public void ashrInPlace(long shiftAmt) {
		if(shiftAmt > numBits) {
			throw new IllegalArgumentException("Invalid shift amount");
		}
		if (isSingleWord()) {
			long SExtVAL = MathUtils.signExtend64(unsignedVals[0], numBits);
			if (shiftAmt == numBits) {
				unsignedVals[0] = ULong.valueOf(SExtVAL >> (AP_INT_BITS_PER_WORD - 1)); // Fill with sign bit.
			}
			else {
				unsignedVals[0] = ULong.valueOf(SExtVAL >> shiftAmt);
			}
			clearUnusedBits();
			return;
		}

		// TODO Implement this
		//ashrSlowCase(shiftAmt);
	}	

	/* A utility function that converts a character to a digit.
	 */
	public static int getDigit(char cdigit, int radix) {
		int r = -1;

		if (radix == 16 || radix == 36) {
			r = cdigit - '0';
			if (r <= 9)
				return r;

			r = cdigit - 'A';
			if (r <= radix - 11)
				return r + 10;

			r = cdigit - 'a';
			if (r <= radix - 11)
				return r + 10;

			radix = 10;
		}

		r = cdigit - '0';
		if (r < radix) {
			return r;
		}

		return -1;
	}

	/*
	 * DST += RHS + CARRY where CARRY is zero or one.  Returns the carry flag.
	 */

	protected static ULong tcAdd(ULong[] dst, ULong[] rhs, ULong carry, int numWords) {
		if(carry.isGreaterThan(1)) {
			throw new IllegalArgumentException("Carry can only be 0 or 1");
		}

		for (int i = 0; i < numWords; i++) {
			ULong l = dst[i];
			if (carry.isGreaterThan(0)) {
				dst[i] = dst[i].add(rhs[i]).add(1);
				carry = dst[i].isLesserThanOrEqualTo(1) ? ULong.valueOf(1) : ULong.valueOf(0);
			} else {
				dst[i] = dst[i].add(rhs[i]);
				carry = dst[i].isLesserThan(1) ? ULong.valueOf(1) : ULong.valueOf(0);
			}
		}

		return carry;
	}

	/// DST -= RHS + CARRY where CARRY is zero or one. Returns the carry flag.
	protected static ULong tcSubtract(ULong[] dst, ULong[] rhs, ULong carry, int parts) {
		if(carry.isGreaterThan(1)) {
			throw new IllegalArgumentException("Carry can only be 0 or 1");
		}

		for (int i = 0; i < parts; i++) {
			ULong l = dst[i];
			if (carry.equals(ULong.valueOf(1))) {
				dst[i] = dst[i].subtract(rhs[i].add(1));
				carry = dst[i].isGreaterThanOrEqualTo(1) ? ULong.valueOf(1) : ULong.valueOf(0);
			} else {
				dst[i] = dst[i].subtract(rhs[i]);
				carry = dst[i].isGreaterThan(1) ? ULong.valueOf(1) : ULong.valueOf(0);
			}
		}

		return carry;
	}

	/*  DST += RHS.  Returns the carry flag.
	 *  This function adds a single "word" integer, src, to the multiple
	 * "word" integer array, dst[]. dst[] is modified to reflect the addition and
	 * 1 is returned if there is a carry out, otherwise 0 is returned.
	 *  @returns the carry of the addition.
	 */
	public static ULong tcAddPart(ULong[] dst, ULong src, int parts) {
		for (int i = 0; i < parts; ++i) {
			dst[i] = dst[i].add(src);
			if (dst[i].isGreaterThanOrEqualTo(src))
				return ULong.valueOf(0); // No need to carry so exit early.
			src = ULong.valueOf(1); // Carry one to next digit.
		}

		return ULong.valueOf(1);
	}

	/* DST -= RHS.  Returns the carry flag.
	 * This function subtracts a single "word" (64-bit word), src, from
	 * the multi-word integer array, dst[], propagating the borrowed 1 value until
	 * no further borrowing is needed or it runs out of "words" in dst.  The result
	 * is 1 if "borrowing" exhausted the digits in dst, or 0 if dst was not
	 * exhausted. In other words, if src > dst then this function returns 1,
	 * otherwise 0.
	 * Returns the borrow out of the subtraction
	 */
	public static ULong tcSubtractPart(ULong[] dst, ULong src, int parts) {
		for (int i = 0; i < parts; ++i) {
			ULong Dst = dst[i];
			dst[i] = dst[i].subtract(src);
			if (src.isLesserThanOrEqualTo(Dst)) {
				return ULong.valueOf(0); // No need to borrow so exit early.
			}

			src = ULong.valueOf(1);    // We have to "borrow 1" from next "word"
		}

		return ULong.valueOf(1);
	}


	/* DST += SRC * MULTIPLIER + PART   if add is true
	 * DST  = SRC * MULTIPLIER + PART   if add is false
	 *
	 * Requires 0 <= DSTPARTS <= SRCPARTS + 1.  If DST overlaps SRC they must
	 * start at the same point, i.e. DST == SRC.
	 *
	 * If DSTPARTS == SRC_PARTS + 1 no overflow occurs and zero is returned.
	 * Otherwise DST is filled with the least significant DSTPARTS parts of the
	 * result, and if all of the omitted higher parts were zero return zero,
	 * otherwise overflow occurred and return one.
	 */
	public static int tcMultiplyPart(ULong[] dst, ULong[] src,
			ULong multiplier, ULong carry, int srcParts, int dstParts, boolean add) {
		/* Otherwise our writes of DST kill our later reads of SRC.  */
		/*if(dst.isGreaterThan(src) || dst < src + srcParts) {

		}
		assert(dstParts <= srcParts + 1);
		 */

		/* N loops; minimum of dstParts and srcParts.  */
		int n = dstParts < srcParts ? dstParts : srcParts;

		for (int i = 0; i < n; i++) {
			ULong low, mid, high, srcPart;

			/* [ LOW, HIGH ] = MULTIPLIER * SRC[i] + DST[i] + CARRY.

		         This cannot overflow, because

		         (n - 1) * (n - 1) + 2 (n - 1) = (n - 1) * (n + 1)

		         which is less than n^2.  */

			srcPart = src[i];

			if (multiplier.longValue() == 0 || srcPart.longValue() == 0) {
				low = carry;
				high = ULong.valueOf(0);
			} else {
				low = lowHalf(srcPart).mul(lowHalf(multiplier));
				high = highHalf(srcPart).mul(highHalf(multiplier));

				mid = lowHalf(srcPart).mul(highHalf(multiplier));
				high = high.add(highHalf(mid));
				mid = mid.leftShift(AP_INT_BITS_PER_WORD / 2);
				if (low.add(mid).isLesserThan(low)) {
					high = high.add(ULong.valueOf(1));
				}
				low = low.add(mid);

				mid = highHalf(srcPart).mul(lowHalf(multiplier));
				high = high.add(highHalf(mid));
				mid = mid.leftShift(AP_INT_BITS_PER_WORD / 2);
				if (low.add(mid).isLesserThan(low)) {
					high = high.add(ULong.valueOf(1));
				}
				low = low.add(mid);

				/* Now add carry.  */
				if (low.add(carry).isLesserThan(low)) {
					high = high.add(ULong.valueOf(1));
				}
				low = low.add(carry);
			}

			if (add) { /* And now DST[i], and store the new low part there.  */
				if (low.add(dst[i]).isLesserThan(low)) {
					high = high.add(ULong.valueOf(1));
				}
				dst[i] = dst[i].add(low);
			} else
				dst[i] = low;

			carry = high;
		}

		if (srcParts < dstParts) {
			/* Full multiplication, there is no overflow.  */
			assert(srcParts + 1 == dstParts);
			dst[srcParts] = carry;
			return 0;
		}

		/* We overflowed if there is carry.  */
		if (carry.isGreaterThan(ULong.valueOf(0))) {
			return 1;
		}

		/* We would overflow if any significant unwritten parts would be
		     non-zero.  This is true if any remaining src parts are non-zero
		     and the multiplier is non-zero.  */
		if (multiplier.isGreaterThan(ULong.valueOf(0))) {
			for (int i = dstParts; i < srcParts; i++) {
				if (src[i].isGreaterThan(ULong.valueOf(0))) {
					return 1;
				}
			}
		}

		/* We fitted in the narrow destination.  */
		return 0;
	}


	/* Determine which word a bit is in.
	 * Returns the word position for the specified bit position.
	 */
	public static int whichWord(int bitPosition) {
		return bitPosition / AP_INT_BITS_PER_WORD;
	}

	/* Slow case for countLeadingZeros */
	public int countLeadingZerosSlowCase() {
		int Count = 0;
		for (int i = getNumWords()-1; i >= 0; --i) {
			ULong V = unsignedVals[i];
			if (V.longValue() == 0) {
				Count += AP_INT_BITS_PER_WORD;
			}
			else {
				Count += MathUtils.countLeadingZeros(V.getUnsignedBigInt());
				break;
			}
		}

		// Adjust for unused bits in the most significant word (they are zero).
		int Mod = numBits % AP_INT_BITS_PER_WORD;
		Count -= Mod > 0 ? AP_INT_BITS_PER_WORD - Mod : 0;
		return Count;
	}

	/* Slow case for countLeadingZeros */
	public int countTrailingZerosSlowCase()  {
		int Count = 0;
		int i = 0;
		for (; i < getNumWords() && unsignedVals[i].equals(0); ++i) {
			Count += AP_INT_BITS_PER_WORD;
		}

		if (i < getNumWords()) {
			Count += MathUtils.countTrailingZeros(unsignedVals[i]);
		}

		return (Count < numBits) ? Count : numBits;
	}

	public static QuotientRemainderPair divideWithBigInts(ULong[] LHS, int lhsWords, ULong[] RHS, int rhsWords) {
		BigInteger biLHS = getBigInteger(LHS, lhsWords);
		BigInteger biRHS = getBigInteger(RHS, rhsWords);

		BigInteger quotientBI = biLHS.divide(biRHS);
		BigInteger remainderBI = biLHS.remainder(biRHS);

		if(quotientBI.toString().equals("1")) {
			System.out.println("WAIT HERE");
		}

		APInt resultAPI = new APInt(lhsWords * AP_INT_BITS_PER_WORD, quotientBI.toString(), 10);
		//resultAPI.toString();
		ULong remainder = ULong.valueOf(remainderBI);

		return new QuotientRemainderPair(resultAPI, remainder);
	}

	protected static BigInteger getBigInteger(ULong[] vals, int numWords) {
		BigInteger bigInt = new BigInteger("0");

		// Find the last significant index
		int lastSignificantIndex = numWords;
		for(int i = numWords -1; i >= 0; i--) {
			if(vals[i].longValue() != 0) {
				break;
			}
			lastSignificantIndex--;
		}

		for(int i = 0; i < lastSignificantIndex; i++) {
			if(i == lastSignificantIndex -1) {
				bigInt = bigInt.add(vals[i].getUnsignedBigInt());
			}
			else {
				BigInteger valBI = vals[i].getUnsignedBigInt().add(ULong.MAX_VALUE);
				bigInt = bigInt.add(valBI);
			}
		}
		return bigInt;
	}

	/* Assignment operator.
	 * The RHS value is assigned to *this. If the significant bits in RHS exceed
	 * the bit width, the excess bits are truncated. If the bit width is larger
	 * than 64, the value is zero filled in the unspecified high order bits.
	 * Returns *this after assignment of RHS value.
	 */
	public APInt getAPInt(ULong RHS) {
		if (isSingleWord()) {
			unsignedVals[0] = RHS;
			clearUnusedBits();
		} else {
			unsignedVals[0] = RHS;
			for(int i = 1; i < getNumWords(); i++) {
				unsignedVals[i] = ULong.valueOf(0);
			}
		}

		return this;
	}

	/* Returns the integer part with the least significant BITS set.
	   BITS cannot be zero.  */
	private static ULong lowBitMask(int bits) {
		if(bits == 0 || bits > AP_INT_BITS_PER_WORD) {
			throw new IllegalArgumentException("Invalid number of bits");
		}

		return ULong.valueOf(0).complement().rightShift(AP_INT_BITS_PER_WORD - bits);
	}

	/* Returns the value of the lower half of PART.  */
	private static ULong lowHalf(ULong part) {
		return part.and(lowBitMask(AP_INT_BITS_PER_WORD / 2));
	}

	/* Returns the value of the upper half of PART.  */
	private static ULong highHalf(ULong part) {
		return part.rightShift(AP_INT_BITS_PER_WORD / 2);
	}

	public int countTrailingOnesSlowCase() {
		int Count = 0;
		int i = 0;
		for (; i < getNumWords() && unsignedVals[i].equals(WORDTYPE_MAX); ++i) {
			Count += AP_INT_BITS_PER_WORD;
		}

		if (i < getNumWords()) {
			Count += MathUtils.countTrailingOnes(unsignedVals[i]);
		}
		assert(Count <= numBits);
		return Count;
	}

	/*
	 * Slow case for countPopulation
	 */
	public int countPopulationSlowCase() {
		int Count = 0;
		for (int i = 0; i < getNumWords(); ++i) {
			Count += MathUtils.countPopulation(unsignedVals[i]);
		}

		return Count;
	}

	/*
	 * Logical right-shift this APInt by shiftAmt.
	 * Logical right-shift function.
	 */
	public void lshrSlowCase(int ShiftAmt) {
		tcShiftRight(unsignedVals, getNumWords(), ShiftAmt);
	}

	/// Shift a bignum right Count bits in-place. Shifted in bits are zero. There
	/// are no restrictions on Count.
	public void tcShiftRight(ULong[] Dst, int Words, int Count) {
		// Don't bother performing a no-op shift.
		if (Count <= 0)
			return;

		// WordShift is the inter-part shift; BitShift is the intra-part shift.
		int WordShift = Count / AP_INT_BITS_PER_WORD < Words ? Count / AP_INT_BITS_PER_WORD : Count;
		int BitShift = Count % AP_INT_BITS_PER_WORD;

		int WordsToMove = Words - WordShift;
		// Fastpath for moving by whole words.
		if (BitShift == 0) {
			// TODO Implement this
			// std::memmove(Dst, Dst + WordShift, WordsToMove * APINT_WORD_SIZE);
		} else {
			for (int i = 0; i != WordsToMove; ++i) {
				Dst[i] = Dst[i + WordShift].rightShift(BitShift);
				if (i + 1 != WordsToMove)
					Dst[i] = Dst[i].or(Dst[i + WordShift + 1].leftShift(AP_INT_BITS_PER_WORD - BitShift));
			}
		}

		// Fill in the remainder with 0s.
		// TODO Implement this
		//std::memset(Dst + WordsToMove, 0, WordShift * APINT_WORD_SIZE);
	}

	/*
	 * Count the number of trailing zero bits.
	 *
	 * This function is an APInt version of the countTrailingZeros
	 * functions in MathExtras.h. It counts the number of zeros from the least
	 * significant bit to the first set bit.
	 *
	 * \returns BitWidth if the value is zero, otherwise returns the number of
	 * zeros from the least significant bit to the first one bit.
	 */
	public int countTrailingZeros() {
		if (isSingleWord()){
			int trailingZeros = (int) MathUtils.countTrailingZeros(unsignedVals[0]);
			return trailingZeros < numBits ? trailingZeros : numBits;
		}

		return countTrailingZerosSlowCase();
	}

	/*
	 * Set the bits from loBit (inclusive) to hiBit (exclusive) to 1.
	 * This function handles "wrap" case when \p loBit > \p hiBit, and calls
	 * setBits when \p loBit <= \p hiBit.
	 */
	public void setBitsWithWrap(int loBit, int hiBit) {
		if(hiBit > numBits) {
			throw new IllegalArgumentException("hiBit out of range");
		}
		if(loBit > numBits) {
			throw new IllegalArgumentException("loBit out of range");
		}

		if (loBit <= hiBit) {
			setBits(loBit, hiBit);
			return;
		}

		setLowBits(hiBit);
		setHighBits(numBits - loBit);
	}

	/*
	 * Set the bits from loBit (inclusive) to hiBit (exclusive) to 1.
	 * This function handles case when \p loBit <= \p hiBit.
	 */
	public void setBits(int loBit, int hiBit) {
		if(hiBit > numBits) {
			throw new IllegalArgumentException("hiBit out of range");
		}

		if(loBit > numBits) {
			throw new IllegalArgumentException("loBit out of range");
		}

		if(loBit > hiBit) {
			throw new IllegalArgumentException("loBit greater than hiBit");
		}
		if (loBit == hiBit) {
			return;
		}

		if (loBit < AP_INT_BITS_PER_WORD && hiBit <= AP_INT_BITS_PER_WORD) {
			ULong mask = ULong.valueOf(WORDTYPE_MAX).rightShift(AP_INT_BITS_PER_WORD - (hiBit - loBit));
			mask = mask.leftShift(loBit);
			unsignedVals[0] =  unsignedVals[0].or(mask);
		} else {
			setBitsSlowCase(loBit, hiBit);
		}
	}

	/* Zero extend or truncate to width
	 * Make this APInt have the bit width given by \p width. The value is zero
	 * extended, or left alone to make it that width.
	 */
	public APInt zextOrSelf(int width) {
		if (numBits < width) {
			return zext(width);
		}

		return this;
	}

	/* Utility method to change the bit width of this APInt to new bit width,
	 * allocating and/or deallocating as necessary. There is no guarantee on the
	 * value of any bits upon return. Caller should populate the bits after.
	 */
	public void reallocate(int newBitWidth) {
		// If the number of words is the same we can just change the width and stop.
		if (getNumWords() == getNumWords(newBitWidth)) {
			numBits = newBitWidth;
			return;
		}

		// Update BitWidth.
		numBits = newBitWidth;

		// If we are supposed to have an allocation, create it.
		if (!isSingleWord()) {
			unsignedVals = new ULong[getNumWords()];
		}
	}

	/*
	 *  Slow case for assign ( = )
	 */
	void AssignSlowCase(APInt RHS) {
		// Don't do anything for X = X
		if (this == RHS) {
			return;
		}

		// Adjust the bit width and handle allocations as necessary.
		reallocate(RHS.getNumBits());

		// Copy the data.
		if (isSingleWord()) {
			unsignedVals[0] = RHS.unsignedVals[0];
		}
		else {
			for(int i = 0; i < getNumWords(); i++) {
				unsignedVals[i] = RHS.unsignedVals[i];
			}
		}
	}

	/*
	 *  Slow case for operator &=.
	 */
	public void AndAssignSlowCase(APInt RHS) {
		tcAnd(unsignedVals, RHS.getUnsignedVals(), getNumWords());
	}

	/*
	 *  Slow case for operator |=.
	 */
	public void OrAssignSlowCase(APInt RHS) {
		tcOr(unsignedVals, RHS.unsignedVals, getNumWords());
	}

	/*
	 *  Slow case for operator ^=.
	 */
	public void XorAssignSlowCase(APInt RHS) {
		tcXOr(unsignedVals, RHS.unsignedVals, getNumWords());
	}

	/// The obvious AND, OR and XOR and complement operations.
	protected static void tcAnd(ULong[] dst, ULong[] rhs, int parts) {
		for (int i = 0; i < parts; i++) {
			dst[i] = dst[i].and(rhs[i]);
		}
	}

	protected static void tcOr(ULong[] dst, ULong[] rhs, int parts) {
		for (int i = 0; i < parts; i++) {
			dst[i] = dst[i].or(rhs[i]);
		}
	}

	protected static void tcXOr(ULong[] dst, ULong[] rhs, int parts) {
		for (int i = 0; i < parts; i++) {
			dst[i] = dst[i].xor(rhs[i]);
		}
	}

	protected static void tcComplement(ULong[] dst, int parts) {
		for (int i = 0; i < parts; i++) {
			dst[i] = dst[i].complement();
		}
	}


	/// out-of-line slow case for setBits.
	public void setBitsSlowCase(int loBit, int hiBit) {
		int loWord = whichWord(loBit);
		int hiWord = whichWord(hiBit);

		// Create an initial mask for the low word with zeros below loBit.
		ULong loMask = ULong.valueOf(WORDTYPE_MAX.shiftLeft(whichBit(loBit)));

		// If hiBit is not aligned, we need a high mask.
		int hiShiftAmt = whichBit(hiBit);
		if (hiShiftAmt != 0) {
			// Create a high mask with zeros above hiBit.
			ULong hiMask = ULong.valueOf(WORDTYPE_MAX.shiftRight(AP_INT_BITS_PER_WORD - hiShiftAmt));
			// If loWord and hiWord are equal, then we combine the masks. Otherwise,
			// set the bits in hiWord.
			if (hiWord == loWord) {
				loMask = loMask.and(hiMask);
			}
			else {
				unsignedVals[hiWord] = unsignedVals[hiWord].or(hiMask);
			}

		}
		// Apply the mask to the low word.
		unsignedVals[loWord] = unsignedVals[loWord].or(loMask);

		// Fill any words between loWord and hiWord with all ones.
		for (int word = loWord + 1; word < hiWord; ++word) {
			unsignedVals[word] = ULong.valueOf(WORDTYPE_MAX);
		}
	}

	/// Slow case for shl
	public void shlSlowCase(int ShiftAmt) {
		tcShiftLeft(unsignedVals, getNumWords(), ShiftAmt);
		clearUnusedBits();
	}

	/// Increment a bignum in-place.  Return the carry flag.
	public static ULong tcIncrement(ULong[] dst, int parts) {
		return tcAddPart(dst, ULong.valueOf(1), parts);
	}

	/// Decrement a bignum in-place.  Return the borrow flag.
	public static ULong tcDecrement(ULong[]  dst, int parts) {
		return tcSubtractPart(dst, ULong.valueOf(1), parts);
	}

	public static void tcShiftLeft(ULong[] Dst, int Words, int Count) {

		if (Count <= 0) { // Don't bother performing a no-op shift.
			return;
		}

		// WordShift is the inter-part shift; BitShift is the intra-part shift.
		int WordShift = Count / AP_INT_BITS_PER_WORD < Words ? Count / AP_INT_BITS_PER_WORD : Words;
		int BitShift = Count % AP_INT_BITS_PER_WORD;

		// Fastpath for moving by whole words.
		if (BitShift == 0) {
			for(int i = 0; i < (Words - WordShift); i++) {
				Dst[WordShift + i] = Dst[i];
			}
		} 
		else {
			while (Words-- > WordShift) {
				Dst[Words] = Dst[Words - WordShift].leftShift(BitShift);
				if (Words > WordShift)
					Dst[Words] = Dst[Words].or(Dst[Words - WordShift - 1].rightShift((AP_INT_BITS_PER_WORD - BitShift)));
			}
		}

		// Fill in the remainder with 0s.
		for(int i = 0; i < WordShift; i++) {
			Dst[i] = ULong.valueOf(0);
		}
	}

	/* 
	 * If this value is smaller than the specified limit, return it, otherwise
	 * return the limit value.  This causes the value to saturate to the limit.
	 */
	protected ULong getLimitedValue(ULong Limit) {
		if(Limit == null) {
			Limit = ULong.valueOf(ULong.MAX_VALUE);
		}
		return ugt(Limit) ? Limit : getZExtValueNew();
	}

	public APInt or(APInt lshr) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * Calculate the rotate amount modulo the bit width.
	 */
	public static int rotateModulo(int BitWidth, APInt rotateAmt) {
		int rotBitWidth = rotateAmt.getNumBits();
		APInt rot = rotateAmt;
		if (rotBitWidth < BitWidth) {
			// Extend the rotate APInt, so that the urem doesn't divide by 0.
			// e.g. APInt(1, 32) would give APInt(1, 0).
			rot = rotateAmt.zext(BitWidth);
		}
		rot = rot.urem(new APInt(rot.getNumBits(), ULong.valueOf(BitWidth), false));
		return (int)rot.getLimitedValue(ULong.valueOf(BitWidth)).longValue();
	}

	public Boolean getOpOverflowFlag() {
		return opOverflowFlag;
	}

	public void setOpOverflowFlag(Boolean opOverflowFlag) {
		this.opOverflowFlag = opOverflowFlag;
	}


	  /* Get the word corresponding to a bit position
	  /// \returns the corresponding word for the specified bit position.
	   */
	public ULong getWord(int bitPosition) {
	    return isSingleWord() ? unsignedVals[0] : unsignedVals[whichWord(bitPosition)];
	  }
	
	/*
	private static final short CHAR_BIT = 8;
	private long WordType;

	  /// This enum is used to hold the constants we needed for APInt.

	 private static final short APINT_WORD_SIZE = sizeof(long);
	    /// Bits in a word.
	 private static final short APINT_BITS_PER_WORD = APINT_WORD_SIZE * CHAR_BIT;

	  enum Rounding {
	    DOWN,
	    TOWARD_ZERO,
	    UP,
	  };

	private static final long WORDTYPE_MAX = ~0L;

	/// This union is used to store the integer value. When the
	  /// integer bit-width <= 64, it uses VAL, otherwise it uses pVal.
	private long UVAL;   ///< Used to store the <= 64 bits integer value.
	private long UVals; ///< Used to store the >64 bits integer value.


	private int BitWidth; /// The number of bits in this APInt.


	  /// Fast internal constructor
	  ///
	  /// This constructor is used only internally for speed of construction of
	  /// temporaries. It is unsafe for general use so it is not public.
	  public APInt(long val, unsigned bits) : BitWidth(bits) {
	    U.pVal = val;
	  }

	  /// out-of-line slow case for lshr.
	  void lshrSlowCase(unsigned ShiftAmt);

	  /// out-of-line slow case for ashr.
	  void ashrSlowCase(unsigned ShiftAmt);

	  /// out-of-line slow case for countLeadingOnes.
	  unsigned countLeadingOnesSlowCase() const LLVM_READONLY;

	  /// out-of-line slow case for intersects.
	  bool intersectsSlowCase(APInt &RHS) const LLVM_READONLY;

	  /// out-of-line slow case for isSubsetOf.
	  bool isSubsetOfSlowCase(APInt &RHS) const LLVM_READONLY;

	  /// out-of-line slow case for flipAllBits.
	  void flipAllBitsSlowCase();

	  /// Unsigned comparison. Returns -1, 0, or 1 if this APInt is less than, equal
	  /// to, or greater than RHS.
	  int compare(APInt &RHS) const LLVM_READONLY;

	  /// Construct an APInt of numBits width, initialized as bigVal[].
	  ///
	  /// Note that bigVal.size() can be smaller or larger than the corresponding
	  /// bit width but any extraneous bits will be dropped.
	  ///
	  /// \param numBits the bit width of the constructed APInt
	  /// \param bigVal a sequence of words to form the initial value of the APInt
	  APInt(unsigned numBits, ArrayRef<uint64_t> bigVal);

	  /// Equivalent to APInt(numBits, ArrayRef<uint64_t>(bigVal, numWords)), but
	  /// deprecated because this constructor is prone to ambiguity with the
	  /// APInt(unsigned, uint64_t, bool) constructor.
	  ///
	  /// If this overload is ever deleted, care should be taken to prevent calls
	  /// from being incorrectly captured by the APInt(unsigned, uint64_t, bool)
	  /// constructor.
	  APInt(unsigned numBits, unsigned numWords, const uint64_t bigVal[]);

	  /// Construct an APInt from a string representation.
	  ///
	  /// This constructor interprets the string \p str in the given radix. The
	  /// interpretation stops when the first character that is not suitable for the
	  /// radix is encountered, or the end of the string. Acceptable radix values
	  /// are 2, 8, 10, 16, and 36. It is an error for the value implied by the
	  /// string to require more bits than numBits.
	  ///
	  /// \param numBits the bit width of the constructed APInt
	  /// \param str the string to be interpreted
	  /// \param radix the radix to use for the conversion
	  APInt(unsigned numBits, StringRef str, uint8_t radix);

	  /// Simply makes *this a copy of that.
	  /// Copy Constructor.
	  APInt(APInt &that) : BitWidth(that.BitWidth) {
	    if (isSingleWord())
	      U.VAL = that.U.VAL;
	    else
	      initSlowCase(that);
	  }

	  /// Move Constructor.
	  APInt(APInt &&that) : BitWidth(that.BitWidth) {
	    memcpy(&U, &that.U, sizeof(U));
	    that.BitWidth = 0;
	  }

	  /// Default constructor that creates an uninteresting APInt
	  /// representing a 1-bit zero value.
	  ///
	  /// This is useful for object deserialization (pair this with the static
	  ///  method Read).
	  explicit APInt() : BitWidth(1) { U.VAL = 0; }

	  /// Used to insert APInt objects, or objects that contain APInt objects, into
	  ///  FoldingSets.
	  void Profile(FoldingSetNodeID &id) const;



	  /// This operation tests if there are any pairs of corresponding bits
	  /// between this APInt and RHS that are both set.
	  bool intersects(APInt &RHS) const {
	    assert(BitWidth == RHS.BitWidth && "Bit widths must be the same");
	    if (isSingleWord())
	      return (U.VAL & RHS.U.VAL) != 0;
	    return intersectsSlowCase(RHS);
	  }

	  /// This operation checks that all bits set in this APInt are also set in RHS.
	  bool isSubsetOf(APInt &RHS) const {
	    assert(BitWidth == RHS.BitWidth && "Bit widths must be the same");
	    if (isSingleWord())
	      return (U.VAL & ~RHS.U.VAL) == 0;
	    return isSubsetOfSlowCase(RHS);
	  }

	  /// Truncate to new width with unsigned saturation.
	  ///
	  /// If the APInt, treated as unsigned integer, can be losslessly truncated to
	  /// the new bitwidth, then return truncated APInt. Else, return max value.
	  APInt truncUSat(unsigned width) const;

	  /// Truncate to new width with signed saturation.
	  ///
	  /// If this APInt, treated as signed integer, can be losslessly truncated to
	  /// the new bitwidth, then return truncated APInt. Else, return either
	  /// signed min value if the APInt was negative, or signed max value.
	  APInt truncSSat(unsigned width) const;

	  /// Sign extend or truncate to width
	  ///
	  /// Make this APInt have the bit width given by \p width. The value is sign
	  /// extended, or left alone to make it that width.
	  APInt sextOrSelf(unsigned width) const;

	  /// @}
	  /// \name Value Characterization Functions
	  /// @{

	  /// Return the number of bits in the APInt.
	  unsigned getBitWidth() const { return BitWidth; }



	  /// Compute the number of active words in the value of this APInt.
	  ///
	  /// This is used in conjunction with getActiveData to extract the raw value of
	  /// the APInt.
	  unsigned getActiveWords() const {
	    unsigned numActiveBits = getActiveBits();
	    return numActiveBits ? whichWord(numActiveBits - 1) + 1 : 1;
	  }

	  /// Get bits required for string value.
	  ///
	  /// This method determines how many bits are required to hold the APInt
	  /// equivalent of the string given by \p str.
	  static unsigned getBitsNeeded(StringRef str, uint8_t radix);

	  /// Computes the number of leading bits of this APInt that are equal to its
	  /// sign bit.
	  unsigned getNumSignBits() const {
	    return isNegative() ? countLeadingOnes() : countLeadingZeros();
	  }



	  /// Count the number of trailing one bits.
	  ///
	  /// This function is an APInt version of the countTrailingOnes
	  /// functions in MathExtras.h. It counts the number of ones from the least
	  /// significant bit to the first zero bit.
	  ///
	  /// \returns BitWidth if the value is all ones, otherwise returns the number
	  /// of ones from the least significant bit to the first zero bit.
	  unsigned countTrailingOnes() const {
	    if (isSingleWord())
	      return llvm::countTrailingOnes(U.VAL);
	    return countTrailingOnesSlowCase();
	  }

	  /// Count the number of bits set.
	  ///
	  /// This function is an APInt version of the countPopulation functions
	  /// in MathExtras.h. It counts the number of 1 bits in the APInt value.
	  ///
	  /// \returns 0 if the value is zero, otherwise returns the number of set bits.
	  unsigned countPopulation() const {
	    if (isSingleWord())
	      return llvm::countPopulation(U.VAL);
	    return countPopulationSlowCase();
	  }

	  /// @}
	  /// \name Conversion Functions
	  /// @{
	  void print(raw_ostream &OS, bool isSigned) const;

	  /// Converts an APInt to a string and append it to Str.  Str is commonly a
	  /// SmallString.
	  void toString(SmallVectorImpl<char> &Str, unsigned Radix, bool Signed,
	                bool formatAsCLiteral = false) const;

	  /// Considers the APInt to be unsigned and converts it into a string in the
	  /// radix given. The radix can be 2, 8, 10 16, or 36.
	  void toStringUnsigned(SmallVectorImpl<char> &Str, unsigned Radix = 10) const {
	    toString(Str, Radix, false, false);
	  }

	  /// Considers the APInt to be signed and converts it into a string in the
	  /// radix given. The radix can be 2, 8, 10, 16, or 36.
	  void toStringSigned(SmallVectorImpl<char> &Str, unsigned Radix = 10) const {
	    toString(Str, Radix, true, false);
	  }

	  /// Return the APInt as a std::string.
	  ///
	  /// Note that this is an inefficient method.  It is better to pass in a
	  /// SmallVector/SmallString to the methods above to avoid thrashing the heap
	  /// for the string.
	  std::string toString(unsigned Radix, bool Signed) const;

	  /// \returns a byte-swapped representation of this APInt Value.
	  APInt byteSwap() const;

	  /// \returns the value with the bit representation reversed of this APInt
	  /// Value.
	  APInt reverseBits() const;

	  /// Converts this APInt to a double value.
	  double roundToDouble(bool isSigned) const;

	  /// Converts this unsigned APInt to a double value.
	  double roundToDouble() const { return roundToDouble(false); }

	  /// Converts this signed APInt to a double value.
	  double signedRoundToDouble() const { return roundToDouble(true); }

	  /// Converts APInt bits to a double
	  ///
	  /// The conversion does not do a translation from integer to double, it just
	  /// re-interprets the bits as a double. Note that it is valid to do this on
	  /// any bit width. Exactly 64 bits will be translated.
	  double bitsToDouble() const {
	    return BitsToDouble(getWord(0));
	  }

	  /// Converts APInt bits to a float
	  ///
	  /// The conversion does not do a translation from integer to float, it just
	  /// re-interprets the bits as a float. Note that it is valid to do this on
	  /// any bit width. Exactly 32 bits will be translated.
	  float bitsToFloat() const {
	    return BitsToFloat(static_cast<uint32_t>(getWord(0)));
	  }

	  /// Converts a double to APInt bits.
	  ///
	  /// The conversion does not do a translation from double to integer, it just
	  /// re-interprets the bits of the double.
	  static APInt doubleToBits(double V) {
	    return APInt(sizeof(double) * CHAR_BIT, DoubleToBits(V));
	  }

	  /// Converts a float to APInt bits.
	  ///
	  /// The conversion does not do a translation from float to integer, it just
	  /// re-interprets the bits of the float.
	  static APInt floatToBits(float V) {
	    return APInt(sizeof(float) * CHAR_BIT, FloatToBits(V));
	  }

	  /// @}
	  /// \name Mathematics Operations
	  /// @{

	  /// \returns the floor log base 2 of this APInt.
	  unsigned logBase2() const { return getActiveBits() -  1; }

	  /// \returns the ceil log base 2 of this APInt.
	  unsigned ceilLogBase2() const {
	    APInt temp(*this);
	    --temp;
	    return temp.getActiveBits();
	  }

	  /// \returns the nearest log base 2 of this APInt. Ties round up.
	  ///
	  /// NOTE: When we have a BitWidth of 1, we define:
	  ///
	  ///   log2(0) = UINT32_MAX
	  ///   log2(1) = 0
	  ///
	  /// to get around any mathematical concerns resulting from
	  /// referencing 2 in a space where 2 does no exist.
	  unsigned nearestLogBase2() const {
	    // Special case when we have a bitwidth of 1. If VAL is 1, then we
	    // get 0. If VAL is 0, we get WORDTYPE_MAX which gets truncated to
	    // UINT32_MAX.
	    if (BitWidth == 1)
	      return U.VAL - 1;

	    // Handle the zero case.
	    if (isNullValue())
	      return UINT32_MAX;

	    // The non-zero case is handled by computing:
	    //
	    //   nearestLogBase2(x) = logBase2(x) + x[logBase2(x)-1].
	    //
	    // where x[i] is referring to the value of the ith bit of x.
	    unsigned lg = logBase2();
	    return lg + unsigned((*this)[lg - 1]);
	  }

	  /// \returns the log base 2 of this APInt if its an exact power of two, -1
	  /// otherwise
	  int32_t exactLogBase2() const {
	    if (!isPowerOf2())
	      return -1;
	    return logBase2();
	  }

	  /// Compute the square root
	  APInt sqrt() const;

	  /// Get the absolute value;
	  ///
	  /// If *this is < 0 then return -(*this), otherwise *this;
	  APInt abs() const {
	    if (isNegative())
	      return -(*this);
	    return *this;
	  }

	  /// \returns the multiplicative inverse for a given modulo.
	  APInt multiplicativeInverse(APInt &modulo) const;

	  /// @}
	  /// \name Support for division by constant
	  /// @{

	  /// Calculate the magic number for signed division by a constant.
	  struct ms;
	  ms magic() const;

	  /// Calculate the magic number for unsigned division by a constant.
	  struct mu;
	  mu magicu(unsigned LeadingZeros = 0) const;

	  /// @}
	  /// \name Building-block Operations for APInt and APFloat
	  /// @{

	  // These building block operations operate on a representation of arbitrary
	  // precision, two's-complement, bignum integer values. They should be
	  // sufficient to implement APInt and APFloat bignum requirements. Inputs are
	  // generally a pointer to the base of an array of integer parts, representing
	  // an unsigned bignum, and a count of how many parts there are.

	  /// Sets the least significant part of a bignum to the input value, and zeroes
	  /// out higher parts.
	  static void tcSet(WordType *, WordType, unsigned);

	  /// Assign one bignum to another.
	  static void tcAssign(WordType *, const WordType *, unsigned);

	  /// Returns true if a bignum is zero, false otherwise.
	  static bool tcIsZero(WordType *, unsigned);

	  /// Extract the given bit of a bignum; returns 0 or 1.  Zero-based.
	  static int tcExtractBit(WordType *, unsigned bit);

	  /// Copy the bit vector of width srcBITS from SRC, starting at bit srcLSB, to
	  /// DST, of dstCOUNT parts, such that the bit srcLSB becomes the least
	  /// significant bit of DST.  All high bits above srcBITS in DST are
	  /// zero-filled.
	  static void tcExtract(WordType *, unsigned dstCount,
	                        const WordType *, unsigned srcBits,
	                        unsigned srcLSB);

	  /// Set the given bit of a bignum.  Zero-based.
	  static void tcSetBit(WordType *, unsigned bit);

	  /// Clear the given bit of a bignum.  Zero-based.
	  static void tcClearBit(WordType *, unsigned bit);

	  /// Returns the bit number of the least or most significant set bit of a
	  /// number.  If the input number has no bits set -1U is returned.
	  static unsigned tcLSB(WordType *, unsigned n);
	  static unsigned tcMSB(WordType *parts, unsigned n);

	  /// Negate a bignum in-place.
	  static void tcNegate(WordType *, unsigned);

	  /// DST = LHS * RHS, where DST has the same width as the operands and is
	  /// filled with the least significant parts of the result.  Returns one if
	  /// overflow occurred, otherwise zero.  DST must be disjoint from both
	  /// operands.
	  static int tcMultiply(WordType *, const WordType *, const WordType *,
	                        unsigned);

	  /// DST = LHS * RHS, where DST has width the sum of the widths of the
	  /// operands. No overflow occurs. DST must be disjoint from both operands.
	  static void tcFullMultiply(WordType *, const WordType *,
	                             const WordType *, unsigned, unsigned);

	  /// If RHS is zero LHS and REMAINDER are left unchanged, return one.
	  /// Otherwise set LHS to LHS / RHS with the fractional part discarded, set
	  /// REMAINDER to the remainder, return zero.  i.e.
	  ///
	  ///  OLD_LHS = RHS * LHS + REMAINDER
	  ///
	  /// SCRATCH is a bignum of the same size as the operands and result for use by
	  /// the routine; its contents need not be initialized and are destroyed.  LHS,
	  /// REMAINDER and SCRATCH must be distinct.
	  static int tcDivide(WordType *lhs, const WordType *rhs,
	                      WordType *remainder, WordType *scratch,
	                      unsigned parts);

	  /// Shift a bignum left Count bits. Shifted in bits are zero. There are no
	  /// restrictions on Count.
	  static void tcShiftLeft(WordType *, unsigned Words, unsigned Count);

	  /// Shift a bignum right Count bits.  Shifted in bits are zero.  There are no
	  /// restrictions on Count.
	  static void tcShiftRight(WordType *, unsigned Words, unsigned Count);

	  /// Comparison (unsigned) of two bignums.
	  static int tcCompare(WordType *, const WordType *, unsigned);

	  /// Set the least significant BITS and clear the rest.
	  static void tcSetLeastSignificantBits(WordType *, unsigned, unsigned bits);

	  /// debug method
	  void dump() const;

	  /// @}
	};

	/// Magic data for optimising signed division by a constant.
	struct APInt::ms {
	  APInt m;    ///< magic number
	  unsigned s; ///< shift amount
	};

	/// Magic data for optimising unsigned division by a constant.
	struct APInt::mu {
	  APInt m;    ///< magic number
	  bool a;     ///< add indicator
	  unsigned s; ///< shift amount
	};

	inline bool operator==(uint64_t V1, const APInt &V2) { return V2 == V1; }

	inline bool operator!=(uint64_t V1, const APInt &V2) { return V2 != V1; }

	/// Unary bitwise complement operator.
	///
	/// \returns an APInt that is the bitwise complement of \p v.
	inline APInt operator~(APInt v) {
	  v.flipAllBits();
	  return v;
	}

	inline APInt operator&(APInt a, const APInt &b) {
	  a &= b;
	  return a;
	}

	inline APInt operator&(APInt &a, APInt &&b) {
	  b &= a;
	  return std::move(b);
	}

	inline APInt operator&(APInt a, uint64_t RHS) {
	  a &= RHS;
	  return a;
	}

	inline APInt operator&(uint64_t LHS, APInt b) {
	  b &= LHS;
	  return b;
	}



	inline APInt operator|(APInt &a, APInt &&b) {
	  b |= a;
	  return std::move(b);
	}

	inline APInt operator|(APInt a, uint64_t RHS) {
	  a |= RHS;
	  return a;
	}

	inline APInt operator|(uint64_t LHS, APInt b) {
	  b |= LHS;
	  return b;
	}

	inline APInt operator^(APInt a, const APInt &b) {
	  a ^= b;
	  return a;
	}

	inline APInt operator^(APInt &a, APInt &&b) {
	  b ^= a;
	  return std::move(b);
	}

	inline APInt operator^(APInt a, uint64_t RHS) {
	  a ^= RHS;
	  return a;
	}

	inline APInt operator^(uint64_t LHS, APInt b) {
	  b ^= LHS;
	  return b;
	}

	inline raw_ostream &operator<<(raw_ostream &OS, const APInt &I) {
	  I.print(OS, true);
	  return OS;
	}

	inline APInt operator-(APInt v) {
	  v.negate();
	  return v;
	}

	inline APInt operator+(APInt a, const APInt &b) {
	  a += b;
	  return a;
	}

	inline APInt operator+(APInt &a, APInt &&b) {
	  b += a;
	  return std::move(b);
	}

	inline APInt operator+(APInt a, uint64_t RHS) {
	  a += RHS;
	  return a;
	}

	inline APInt operator+(uint64_t LHS, APInt b) {
	  b += LHS;
	  return b;
	}

	inline APInt operator-(APInt a, const APInt &b) {
	  a -= b;
	  return a;
	}

	inline APInt operator-(APInt &a, APInt &&b) {
	  b.negate();
	  b += a;
	  return std::move(b);
	}

	inline APInt operator-(APInt a, uint64_t RHS) {
	  a -= RHS;
	  return a;
	}

	inline APInt operator-(uint64_t LHS, APInt b) {
	  b.negate();
	  b += LHS;
	  return b;
	}

	inline APInt operator*(APInt a, uint64_t RHS) {
	  a *= RHS;
	  return a;
	}

	inline APInt operator*(uint64_t LHS, APInt b) {
	  b *= LHS;
	  return b;
	}


	namespace APIntOps {

	/// Determine the smaller of two APInts considered to be signed.
	inline const APInt &smin(APInt &A, const APInt &B) {
	  return A.slt(B) ? A : B;
	}

	/// Determine the larger of two APInts considered to be signed.
	inline const APInt &smax(APInt &A, const APInt &B) {
	  return A.sgt(B) ? A : B;
	}

	/// Determine the smaller of two APInts considered to be signed.
	inline const APInt &umin(APInt &A, const APInt &B) {
	  return A.ult(B) ? A : B;
	}

	/// Determine the larger of two APInts considered to be unsigned.
	inline const APInt &umax(APInt &A, const APInt &B) {
	  return A.ugt(B) ? A : B;
	}

	/// Compute GCD of two unsigned APInt values.
	///
	/// This function returns the greatest common divisor of the two APInt values
	/// using Stein's algorithm.
	///
	/// \returns the greatest common divisor of A and B.
	APInt GreatestCommonDivisor(APInt A, APInt B);

	/// Converts the given APInt to a double value.
	///
	/// Treats the APInt as an unsigned value for conversion purposes.
	inline double RoundAPIntToDouble(APInt &APIVal) {
	  return APIVal.roundToDouble();
	}

	/// Converts the given APInt to a double value.
	///
	/// Treats the APInt as a signed value for conversion purposes.
	inline double RoundSignedAPIntToDouble(APInt &APIVal) {
	  return APIVal.signedRoundToDouble();
	}

	/// Converts the given APInt to a float vlalue.
	inline float RoundAPIntToFloat(APInt &APIVal) {
	  return float(RoundAPIntToDouble(APIVal));
	}

	/// Converts the given APInt to a float value.
	///
	/// Treats the APInt as a signed value for conversion purposes.
	inline float RoundSignedAPIntToFloat(APInt &APIVal) {
	  return float(APIVal.signedRoundToDouble());
	}

	/// Converts the given double value into a APInt.
	///
	/// This function convert a double value to an APInt value.
	APInt RoundDoubleToAPInt(double Double, unsigned width);

	/// Converts a float value into a APInt.
	///
	/// Converts a float value into an APInt value.
	inline APInt RoundFloatToAPInt(float Float, unsigned width) {
	  return RoundDoubleToAPInt(double(Float), width);
	}

	/// Return A unsign-divided by B, rounded by the given rounding mode.
	APInt RoundingUDiv(APInt &A, const APInt &B, APInt::Rounding RM);

	/// Return A sign-divided by B, rounded by the given rounding mode.
	APInt RoundingSDiv(APInt &A, const APInt &B, APInt::Rounding RM);

	/// Let q(n) = An^2 + Bn + C, and BW = bit width of the value range
	/// (e.g. 32 for i32).
	/// This function finds the smallest number n, such that
	/// (a) n >= 0 and q(n) = 0, or
	/// (b) n >= 1 and q(n-1) and q(n), when evaluated in the set of all
	///     integers, belong to two different intervals [Rk, Rk+R),
	///     where R = 2^BW, and k is an integer.
	/// The idea here is to find when q(n) "overflows" 2^BW, while at the
	/// same time "allowing" subtraction. In unsigned modulo arithmetic a
	/// subtraction (treated as addition of negated numbers) would always
	/// count as an overflow, but here we want to allow values to decrease
	/// and increase as long as they are within the same interval.
	/// Specifically, adding of two negative numbers should not cause an
	/// overflow (as long as the magnitude does not exceed the bit width).
	/// On the other hand, given a positive number, adding a negative
	/// number to it can give a negative result, which would cause the
	/// value to go from [-2^BW, 0) to [0, 2^BW). In that sense, zero is
	/// treated as a special case of an overflow.
	///
	/// This function returns None if after finding k that minimizes the
	/// positive solution to q(n) = kR, both solutions are contained between
	/// two consecutive integers.
	///
	/// There are cases where q(n) > T, and q(n+1) < T (assuming evaluation
	/// in arithmetic modulo 2^BW, and treating the values as signed) by the
	/// virtue of *signed* overflow. This function will *not* find such an n,
	/// however it may find a value of n satisfying the inequalities due to
	/// an *unsigned* overflow (if the values are treated as unsigned).
	/// To find a solution for a signed overflow, treat it as a problem of
	/// finding an unsigned overflow with a range with of BW-1.
	///
	/// The returned value may have a different bit width from the input
	/// coefficients.
	Optional<APInt> SolveQuadraticEquationWrap(APInt A, APInt B, APInt C,
	                                           unsigned RangeWidth);

	/// Compare two values, and if they are different, return the position of the
	/// most significant bit that is different in the values.
	Optional<unsigned> GetMostSignificantDifferentBit(APInt &A,
	                                                  const APInt &B);

	} // End of APIntOps namespace

	// See friend declaration above. This additional declaration is required in
	// order to compile LLVM with IBM xlC compiler.
	hash_code hash_value(APInt &Arg);

	/// StoreIntToMemory - Fills the StoreBytes bytes of memory starting from Dst
	/// with the integer held in IntVal.
	void StoreIntToMemory(APInt &IntVal, uint8_t *Dst, unsigned StoreBytes);

	/// LoadIntFromMemory - Loads the integer stored in the LoadBytes bytes starting
	/// from Src into IntVal, which is assumed to be wide enough and to hold zero.
	void LoadIntFromMemory(APInt &IntVal, uint8_t *Src, unsigned LoadBytes);
	 */

}

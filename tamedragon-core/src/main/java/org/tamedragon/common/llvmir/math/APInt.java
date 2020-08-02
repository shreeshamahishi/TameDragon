package org.tamedragon.common.llvmir.math;

import java.math.BigInteger;

/**
 * Class for arbitrary precision integers.
 * @author ipsg
 *
 */
public class APInt {

	public static final int AP_INT_BITS_PER_WORD = 64;
	protected static BigInteger WORDTYPE_MAX = ULong.MAX_VALUE;

	protected int numBits;
	protected ULong[] unsignedVals;

	protected Boolean opOverflowFlag;

	public APInt() { }
	
	public enum Rounding {
		    DOWN,
		    TOWARD_ZERO,
		    UP,
		  };

	/* Fast internal constructor
	 * This constructor is used only internally for speed of construction of
	 *temporaries. It is unsafe for general use so it is not public.
	 */
	private APInt(ULong[] vals, int bits){
		numBits = bits;
		unsignedVals =  vals;
	}

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

	/* Construct an APInt from a string representation.
	 * This constructor interprets the string \p str in the given radix. The
	 * interpretation stops when the first character that is not suitable for the
	 * radix is encountered, or the end of the string. Acceptable radix values
	 * are 2, 8, 10, 16, and 36. It is an error for the value implied by the
	 * string to require more bits than numBits.
	 *
	 * numBits the bit width of the constructed APInt
	 * str the string to be interpreted
	 * radix the radix to use for the conversion
	 */
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
	 * 
	 * public APInt(int numBits, ULong val, boolean isSigned = false) - isSigned 
	 * is false by default
	 */
	//
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
	
	/* Simply makes this a copy of that.
	 * Copy Constructor.
	 */
	public APInt(APInt otherAPInt) {
		this.numBits = otherAPInt.numBits;
		if (isSingleWord())
			unsignedVals[0] = otherAPInt.unsignedVals[0].clone();
		else
			initSlowCase(otherAPInt);
	}

	public APInt clone() {

		ULong[] newVals = new ULong[unsignedVals.length];
		for(int i = 0; i < newVals.length; i++) {
			newVals[i] = unsignedVals[i].clone();
		}

		APInt newAPInt = new APInt(numBits, newVals, false);
		// return new APSInt(newAPInt, false);
		return newAPInt;
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
			int digit = APIntUtils.getDigit(c, radix);
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

	//*************************************** VALUE TESTS *****************************************************
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
			return countTrailingOnesSlowCase() == numBits;
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

	// ****************************************************************** VALUE GENERATORS *****************************************************************

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
		APInt result = APIntUtils.getLowBitsSet(this.numBits, numBits);
		result = result.and(this);
		return result;
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
			APIntUtils.tcIncrement(incremented.unsignedVals, getNumWords());
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
			APIntUtils.tcDecrement(decremented.unsignedVals, getNumWords());
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
		if(numBits != RHS.numBits) {
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
			APIntUtils.tcMultiplyPart(unsignedVals, unsignedVals, RHS, ULong.valueOf(0), NumWords, NumWords, false);
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
			APIntUtils.tcAdd(unsignedVals, RHS.getUnsignedVals(), ULong.valueOf(0), getNumWords());
		}
		return clearUnusedBits();
	}

	public APInt addAssign(final ULong RHS) {
		if (isSingleWord()) {
			unsignedVals[0] = unsignedVals[0].add(RHS);
		}
		else {
			APIntUtils.tcAddPart(unsignedVals, RHS, getNumWords());
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
		
		if (isSingleWord()) {
			unsignedVals[0] = unsignedVals[0].subtract(RHS.unsignedVals[0]);
		}
		else {
			APIntUtils.tcSubtract(unsignedVals, RHS.unsignedVals, ULong.valueOf(0), getNumWords());
		}
		return clearUnusedBits();
	}

	public APInt subtractAssign(ULong RHS) {
		if (isSingleWord()) {
			unsignedVals[0] = unsignedVals[0].subtract(RHS);
		}
		else {
			APIntUtils.tcSubtractPart(unsignedVals, RHS, getNumWords());
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
	public APInt leftShiftAssign(final APInt shiftAmount){
		// It's undefined behavior in C to shift by numBits or greater.
		this.leftShiftAssign(shiftAmount.getLimitedValue(ULong.valueOf(numBits)).intValue());
		return this;
	}

	// ****************************************************************** BINARY OPERATORS ************************************************************

	/* Multiplication operator.
	 *
	 * Multiplies this APInt by RHS and returns the result.
	 */

	public APInt mul(APInt other) {
		if(numBits != other.numBits) {
			throw new IllegalArgumentException("Bit widths must be the same");
		}

		if (isSingleWord()) {
			return new APInt(numBits, unsignedVals[0].mul(other.unsignedVals[0]), false);
		}
		
		ULong[] unsignedValsNew = new ULong[getNumWords()];
		
		APInt Result = new APInt(unsignedValsNew, getNumBits());

		APIntUtils.tcMultiply(Result.unsignedVals, unsignedVals, other.unsignedVals, getNumWords());

		Result.clearUnusedBits();
		return Result;
	}

	public APInt mul(final ULong RHS) {
		APInt newVal = this.clone();
		return newVal.mulAssign(RHS);
	}

	/* Left logical shift operator.
	 * Shifts this APInt left by Bits and returns the result.
	 */
	public APInt leftShift(int Bits) {
		return shl(Bits); 
	}

	/* Left logical shift operator.
	 * Shifts this APInt left by \p Bits and returns the result.
	 */
	public APInt leftShift(APInt Bits) { 
		return shl(Bits); 
	}

	/* Arithmetic right-shift function.
	 * Arithmetic right-shift this APInt by shiftAmt.
	 */
	public  APInt ashr(APInt ShiftAmt) {
		APInt R = clone();
		R.ashrInPlace(ShiftAmt);
		return R;
	}
	

	/* Arithmetic right-shift function.
	 * Arithmetic right-shift this APInt by shiftAmt.
	 */
	public  APInt ashr(int ShiftAmt) {
		APInt R = clone();
		R.ashrInPlace(ShiftAmt);
		return R;
	}

	/* 
	 * Arithmetic right-shift this APInt by shiftAmt in place.
	 */
	public  void ashrInPlace(APInt shiftAmt) {
		ashrInPlace(shiftAmt.getLimitedValue(ULong.valueOf(numBits)).intValue());
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
	 * Logical right-shift this APInt by shiftAmt.
	 * Logical right-shift function.
	 * Slow case for lshr.
	 */
	public void lshrSlowCase(int ShiftAmt) {
		APIntUtils.tcShiftRight(unsignedVals, getNumWords(), ShiftAmt);
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
		return rotr(APIntUtils.rotateModulo(numBits, rotateAmt));
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

		QuotientRemainderPair qrPair = APIntUtils.divideWithBigInts(unsignedVals, getNumWords(), RHS.getUnsignedVals(), RHS.getNumWords());
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
		QuotientRemainderPair qrPair = APIntUtils.divideWithBigInts(unsignedVals, getNumWords(), new ULong[] {RHS}, 1);
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
			return new APInt(numBits, unsignedVals[0].modulo(RHS.unsignedVals[0]), false);
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
		return APIntUtils.divideWithBigInts(unsignedVals, lhsWords, RHS.unsignedVals, rhsWords).getRemainderApInt();
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
			return getZExtValue();
		if (this.equals(RHS)) // X % X == 0;
			return ULong.valueOf(0);
		if (lhsWords == 1) // All high words are zero, just use native remainder
			return unsignedVals[0].modulo(RHS);

		// We have to compute it the hard way. Invoke the Knuth divide algorithm.
		APInt remainderAPInt = APIntUtils.divideWithBigInts(unsignedVals, lhsWords, new ULong[] {RHS}, 1).getRemainderApInt();
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
			return this.urem(ULong.valueOf(RHS * -1)).longValue();

		return this.urem(ULong.valueOf(RHS)).longValue();
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

		if (this.equals(ULong.valueOf(0)) && RHS.equals(ULong.valueOf(0)))
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

		return isNegative() ? APIntUtils.getSignedMinValue(numBits) : APIntUtils.getSignedMaxValue(numBits);
	}

	public APInt uadd_sat(APInt RHS) {
		APInt Res = uadd_ov(RHS);
		if (!Res.getOpOverflowFlag())
			return Res;

		return APIntUtils.getMaxValue(numBits);
	}

	public APInt ssub_sat(APInt RHS) {
		APInt Res = ssub_ov(RHS);
		if (!Res.getOpOverflowFlag())
			return Res;

		return isNegative() ? APIntUtils.getSignedMinValue(numBits) : APIntUtils.getSignedMaxValue(numBits);
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

		return ResIsNegative ? APIntUtils.getSignedMinValue(numBits) : APIntUtils.getSignedMaxValue(numBits);
	}

	public APInt umul_sat(APInt RHS) {
		APInt Res = umul_ov(RHS);
		if (!Res.getOpOverflowFlag())
			return Res;

		return APIntUtils.getMaxValue(numBits);
	}

	public APInt sshl_sat(APInt RHS) {
		APInt Res = sshl_ov(RHS);
		if (!Res.getOpOverflowFlag())
			return Res;

		return isNegative() ? APIntUtils.getSignedMinValue(numBits) : APIntUtils.getSignedMaxValue(numBits);
	}

	public APInt ushl_sat(APInt RHS) {
		APInt Res = ushl_ov(RHS);
		if (!Res.getOpOverflowFlag())
			return Res;

		return APIntUtils.getMaxValue(numBits);
	}

	/* Array-indexing support.
	 *
	 * Returns the bit value at bitPosition
	 */
	public boolean atIndex(int bitPosition) {
		if(bitPosition >= getNumBits()) {
			throw new IllegalArgumentException("Bit position out of bounds!");
		}

		return !(APIntUtils.maskBit(bitPosition).and(getWord(bitPosition)).equals(0));
	}

	// ****************************************************************** COMPARISON OPERATORS ************************************************************

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


	/* Equality comparison.
	 *
	 * Compares this APInt with a ULong for the validity of the equality
	 * relationship.
	 * Returns true if this == val
	 */
	public boolean equals(ULong val) {
		return (isSingleWord() || getActiveBits() <= 64) && getZExtValue().equals(val);
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

	/* Unsigned less than comparison
	 *
	 * Regards both *this and RHS as unsigned quantities and compares them for
	 * the validity of the less-than relationship.
	 * Returns true if *this < RHS when both are considered unsigned.
	 */
	public boolean ult(APInt RHS) { return compare(RHS) < 0; }

	/* Unsigned less than comparison
	 *
	 * Regards both *this as an unsigned quantity and compares it with RHS for
	 * the validity of the less-than relationship.
	 * Returns true if *this < RHS when considered unsigned.
	 */
	public boolean ult(ULong RHS) {
		// Only need to check active bits if not a single word.
		return (isSingleWord() || getActiveBits() <= 64) && getZExtValue().isLesserThan(RHS);
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
		return (!isSingleWord() && getActiveBits() > 64) || getZExtValue().isGreaterThan(RHS);
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
	 * Returns true if *this >= RHS when both are considered signed.
	 */
	public boolean sge(APInt RHS){ return !slt(RHS); }

	/*  Signed greater or equal comparison
	 *
	 * Regards both *this as a signed quantity and compares it with RHS for
	 * the validity of the greater-or-equal relationship.
	 *
	 * Returns true if *this >= RHS when considered signed.
	 */
	public boolean sge(long RHS){ return !slt(RHS); }

	/*
	 *  This operation tests if there are any pairs of corresponding bits
	 * between this APInt and RHS that are both set.
	 */
	public boolean intersects(APInt RHS) {
		if(numBits != RHS.numBits) {
			throw new IllegalArgumentException("Bit widths must be the same");
		}
		if (isSingleWord()) {
			return !(unsignedVals[0].and(RHS.unsignedVals[0]).equals(0));
		}

		return intersectsSlowCase(RHS);
	}

	/**
	 * Slow case for intersects
	 */
	public boolean intersectsSlowCase(APInt RHS) {
		for (int i = 0, e = getNumWords(); i != e; ++i)
			if (!(unsignedVals[i].and(RHS.unsignedVals[i])).equals(0))
				return true;

		return false;
	}

	/*
	 * This operation checks that all bits set in this APInt are also set in RHS.
	 */
	public boolean isSubsetOf(APInt RHS){
		if(numBits != RHS.numBits) {
			throw new IllegalArgumentException("Bit widths must be the same");
		}

		if (isSingleWord())
			return unsignedVals[0].and(RHS.unsignedVals[0].complement()).equals(0);
		return isSubsetOfSlowCase(RHS);
	}

	/**
	 * Slow case for isSubsetOf
	 */
	public boolean isSubsetOfSlowCase(APInt RHS) {
		for (int i = 0, e = getNumWords(); i != e; ++i)
			if (!(unsignedVals[i].and(RHS.unsignedVals[i].complement()).equals(0)))
				return false;

		return true;
	}

	// ****************************************************************** RESIZING OPERATORS *******************************************************************
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

		ULong[] vals = new ULong[width];
		result = new APInt(vals, width);

		// Copy full words.
		int i;
		for (i = 0; i != width / AP_INT_BITS_PER_WORD; i++)
			result.unsignedVals[i] = unsignedVals[i];

		// Truncate and copy any partial word.
		int bits = (0 - width) % AP_INT_BITS_PER_WORD;
		if (bits != 0)
			result.unsignedVals[i] = unsignedVals[i].leftShift(bits).rightShift(bits);

		return result;
	}

	/* Truncate to new width with unsigned saturation.
	 * If the APInt, treated as unsigned integer, can be losslessly truncated to
	 * the new bitwidth, then return truncated APInt. Else, return max value.
	 */
	public APInt truncUSat(int width) {
		if(width >= numBits) {
			throw new IllegalArgumentException("Invalid APInt Truncate request");
		}
		if(width <= 0) {
			throw new IllegalArgumentException("Can't truncate to 0 bits");
		}

		// Can we just losslessly truncate it?
		if (isIntN(width))
			return trunc(width);
		// If not, then just return the new limit.
		return APIntUtils.getMaxValue(width);
	}

	/* Truncate to new width with signed saturation.
	 * If this APInt, treated as signed integer, can be losslessly truncated to
	 * the new bitwidth, then return truncated APInt. Else, return either
	 * signed min value if the APInt was negative, or signed max value.
	 */
	public APInt truncSSat(int width) {
		if(width >= numBits) {
			throw new IllegalArgumentException("Invalid APInt Truncate request");
		}
		if(width <= 0) {
			throw new IllegalArgumentException("Can't truncate to 0 bits");
		}

		// Can we just losslessly truncate it?
		if (isSignedIntN(width))
			return trunc(width);
		// If not, then just return the new limits.
		return isNegative() ? APIntUtils.getSignedMinValue(width) : APIntUtils.getSignedMaxValue(width);
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

		int numWords = getNumWords(width);
		ULong[] vals = new ULong[numWords];

		APInt result = new APInt(vals, width);

		// Copy words.
		for(int i = 0; i < numWords; i++) {
			result.unsignedVals[i] = unsignedVals[i].clone();
		}

		// Sign extend the last word since there may be unused bits in the input.
		result.unsignedVals[getNumWords() - 1] =
				ULong.valueOf(MathUtils.signExtend64(unsignedVals[getNumWords() - 1], ((numBits - 1) % AP_INT_BITS_PER_WORD) + 1));

		// Fill with sign bits.
		APIntUtils.setWordValues(result.unsignedVals, isNegative() ? -1 : 0, getNumWords(), result.getNumWords() - getNumWords());
		result.clearUnusedBits();
		return result;
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

		ULong vals[] = new ULong[width];
		APInt Result = new APInt(vals, width);

		// Copy words.
		for(int i = 0; i < getNumWords(); i++) {
			Result.unsignedVals[i] = unsignedVals[i];
		}

		// Zero remaining words.
		APIntUtils.setWordValues(Result.unsignedVals, 0, getNumWords(), Result.unsignedVals.length);

		return Result;

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

	/* Sign extend or truncate to width
	 * Make this APInt have the bit width given by \p width. The value is sign
	 * extended, or left alone to make it that width.
	 */
	public APInt sextOrSelf(int width) {
		if (numBits < width) {
			return sext(width);
		}

		return this;
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

	// *********************************************************** BIT MANIPULATION OPERATORS ************************************************************

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

		APInt Keep = APIntUtils.getHighBitsSet(numBits, numBits - loBits);
		this.andAssign(Keep);
	}

	/*
	 * Set the sign bit to 0.
	 */
	public void clearSignBit() {
		clearBit(numBits - 1);
	}

	/* Toggles a given bit to its opposite value.
	 *
	 * Toggle a given bit to its opposite value whose position is given
	 * as "bitPosition".
	 */
	public void flipBit(int bitPosition) {
		if(bitPosition >= numBits) {
			throw new IllegalArgumentException("Out of the bit-width range!");
		}
		if (atIndex(bitPosition))
			clearBit(bitPosition);
		else
			setBit(bitPosition);
	}

	/// Insert the bits from a smaller APInt starting at bitPosition.
	public void insertBits(APInt subBits, int bitPosition) {
		int subnumBits = subBits.getNumBits();
		if(0 >= subnumBits && (subnumBits + bitPosition) > numBits) {
			throw new IllegalArgumentException("Illegal bit insertion");
		}

		// Insertion is a direct copy.
		if (subnumBits == numBits) {
			this.assign(subBits);
			return;
		}

		// Single word result can be done as a direct bitmask.
		if (isSingleWord()) {
			ULong mask = ULong.valueOf(WORDTYPE_MAX).rightShift((AP_INT_BITS_PER_WORD - subnumBits));
			unsignedVals[0] = unsignedVals[0].and(mask.leftShift(bitPosition).complement());
			unsignedVals[0] = unsignedVals[0].or(subBits.unsignedVals[0].leftShift(bitPosition));
			return;
		}

		int loBit = APIntUtils.whichBit(bitPosition);
		int loWord = APIntUtils.whichWord(bitPosition);
		int hi1Word = APIntUtils.whichWord(bitPosition + subnumBits - 1);

		// Insertion within a single word can be done as a direct bitmask.
		if (loWord == hi1Word) {
			ULong mask = ULong.valueOf(WORDTYPE_MAX).rightShift(AP_INT_BITS_PER_WORD - subnumBits);
			unsignedVals[loWord] = unsignedVals[0].and(mask.leftShift(loBit).complement());
			unsignedVals[0] = unsignedVals[0].or(subBits.unsignedVals[0].leftShift(loBit));
			return;
		}

		// Insert on word boundaries.
		if (loBit == 0) {
			// Direct copy whole words.
			int numWholeSubWords = subnumBits / AP_INT_BITS_PER_WORD;
			for(int i = 0; i < unsignedVals.length; i++) {
				unsignedVals[i + loWord] = subBits.unsignedVals[i];
			}

			// Mask+insert remaining bits.
			int remainingBits = subnumBits % AP_INT_BITS_PER_WORD;
			if (remainingBits != 0) {
				ULong mask = ULong.valueOf(WORDTYPE_MAX).rightShift(AP_INT_BITS_PER_WORD - remainingBits);
				unsignedVals[hi1Word] = unsignedVals[hi1Word].and(mask.complement());
				unsignedVals[hi1Word] = unsignedVals[hi1Word].or(subBits.getWord(subnumBits - 1));
			}
			return;
		}

		// General case - set/clear individual bits in dst based on src.
		// TODO - there is scope for optimization here, but at the moment this code
		// path is barely used so prefer readability over performance.
		for (int i = 0; i != subnumBits; ++i) {
			if (subBits.atIndex(i))
				setBit(bitPosition + i);
			else
				clearBit(bitPosition + i);
		}
	}

	public void insertBits(ULong subBits, int bitPosition, int numBits) {
		ULong maskBits = MathUtils.maskTrailingOnes(numBits);
		subBits = subBits.and(maskBits);
		if (isSingleWord()) {
			unsignedVals[0] = unsignedVals[0].and(maskBits.leftShift(bitPosition).complement());
			unsignedVals[0] = unsignedVals[0].or(subBits.leftShift(bitPosition));
			return;
		}

		int loBit = APIntUtils.whichBit(bitPosition);
		int loWord = APIntUtils.whichWord(bitPosition);
		int hiWord = APIntUtils.whichWord(bitPosition + numBits - 1);
		if (loWord == hiWord) {
			unsignedVals[loWord] = unsignedVals[loWord].and(maskBits.leftShift(loBit).complement());
			unsignedVals[loWord] = unsignedVals[loWord].or(subBits.leftShift(loBit));
			return;
		}

		//static_assert(8 * sizeof(WordType) <= 64,
		//              "This code assumes only two words affected");
		int wordBits = 8 * 64;
		unsignedVals[loWord] = unsignedVals[loWord].and(maskBits.leftShift(loBit).complement());
		unsignedVals[loWord] = unsignedVals[loWord].or(subBits.leftShift(loBit));

		unsignedVals[hiWord] = unsignedVals[hiWord].and(maskBits.rightShift(wordBits - loBit).complement());
		unsignedVals[hiWord] = unsignedVals[hiWord].or(subBits.rightShift(wordBits - loBit));
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

		int loBit = APIntUtils.whichBit(bitPosition);
		int loWord = APIntUtils.whichWord(bitPosition);
		int hiWord = APIntUtils.whichWord(bitPosition + numBits - 1);

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

		int loBit = APIntUtils.whichBit(bitPosition);
		int loWord = APIntUtils.whichWord(bitPosition);
		int hiWord = APIntUtils.whichWord(bitPosition + numBits - 1);
		if (loWord == hiWord) {
			return unsignedVals[loWord].rightShift(loBit).and(maskBits);
		}

		int wordBits = 64;
		ULong retBits = unsignedVals[loWord].rightShift(loBit);
		retBits = retBits.or(unsignedVals[hiWord].leftShift(wordBits - loBit));
		retBits = retBits.and(maskBits);
		return retBits;

	}

	// ****************************************************************** VALUE CHARACTERIZATION FUNCTIONS  ***********************************************

	public int getNumBits() {
		return numBits;
	}

	/* Get the number of words.
	 * Here one word's bitwidth equals to that of uint64_t.
	 * Returns the number of words to hold the integer value of this APInt.
	 */
	public int getNumWords() {
		return getNumWords(numBits); 
	}

	/* Compute the number of active bits in the value
	 *
	 * This function returns the number of active bits which is defined as the
	 * bit width minus the number of leading zeros. This is used in several
	 * computations to see how "wide" the value is.
	 */
	public int getActiveBits() { 
		return numBits - countLeadingZeros(); 
	}

	/* Compute the number of active words in the value of this APInt.
	 * This is used in conjunction with getActiveData to extract the raw value of
	 * the APInt.
	 */
	public int getActiveWords() {
		int numActiveBits = getActiveBits();
		return numActiveBits > 0 ? APIntUtils.whichWord(numActiveBits - 1) + 1 : 1;
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

	/* Get zero extended value
	 *
	 * This method attempts to return the value of this APInt as a zero extended
	 * uint64_t. The bitwidth must be <= 64 or the value must fit within a
	 * uint64_t. Otherwise an assertion will result.
	 */
	public ULong getZExtValue() {
		if (isSingleWord()) {
			return unsignedVals[0];
		}
		if(getActiveBits() <= 64) {
			throw new IllegalArgumentException("Too many bits for uint64_t");
		}
		return unsignedVals[0];
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

	/* The APInt version of the countLeadingZeros functions in MathExtras.h.
	 * It counts the number of zeros from the most significant bit to the first
	 * one bit.
	 * Returns BitWidth if the value is zero, otherwise returns the number of
	 * zeros from the most significant bit to the first one bits.
	 */
	public int countLeadingZeros() {
		if (isSingleWord()) {
			int unusedBits = AP_INT_BITS_PER_WORD - numBits;
			return MathUtils.countLeadingZeros(unsignedVals[0]) - unusedBits;
		}

		return countLeadingZerosSlowCase();
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
		return countLeadingOnesSlowCase();
	}

	public int countLeadingOnesSlowCase() {
		int highWordBits = numBits % AP_INT_BITS_PER_WORD;
		int shift;
		if (highWordBits <= 0) {
			highWordBits = AP_INT_BITS_PER_WORD;
			shift = 0;
		} 
		else {
			shift = AP_INT_BITS_PER_WORD - highWordBits;
		}

		int i = getNumWords() - 1;
		int Count = MathUtils.countLeadingOnes(unsignedVals[i].leftShift(shift));
		if (Count == highWordBits) {
			for (i--; i >= 0; --i) {
				if (unsignedVals[i].equals(ULong.valueOf(WORDTYPE_MAX)))
					Count += AP_INT_BITS_PER_WORD;
				else {
					Count += MathUtils.countLeadingOnes(unsignedVals[i]);
					break;
				}
			}
		}
		return Count;
	}

	/* 
	 * Computes the number of leading bits of this APInt that are equal to its
	 * sign bit.
	 */
	public int getNumSignBits() {
		return isNegative() ? countLeadingOnes() : countLeadingZeros();
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

	/* Count the number of trailing one bits.
	 *
	 * This function is an APInt version of the countTrailingOnes
	 * functions in MathExtras.h. It counts the number of ones from the least
	 * significant bit to the first zero bit.
	 *
	 * Returns BitWidth if the value is all ones, otherwise returns the number
	 * of ones from the least significant bit to the first zero bit.
	 */
	public int countTrailingOnes() {
		if (isSingleWord())
			return MathUtils.countTrailingOnes(unsignedVals[0]);
		return countTrailingOnesSlowCase();
	}

	public int countTrailingOnesSlowCase() {
		int Count = 0;
		int i = 0;
		for (; i < getNumWords() && unsignedVals[i].equals(ULong.valueOf(WORDTYPE_MAX)); ++i) {
			Count += AP_INT_BITS_PER_WORD;
		}

		if (i < getNumWords()) {
			Count += MathUtils.countTrailingOnes(unsignedVals[i]);
		}
		assert(Count <= numBits);
		return Count;
	}

	/* Count the number of bits set.
	 * This function is an APInt version of the countPopulation functions
	 * in MathExtras.h. It counts the number of 1 bits in the APInt value.
	 * Returns 0 if the value is zero, otherwise returns the number of set bits.
	 */
	public int countPopulation() {
		if (isSingleWord())
			return MathUtils.countPopulation(unsignedVals[0]);
		return countPopulationSlowCase();
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

	// ****************************************************************** CONVERSION FUNCTIONS ************************************************************

	@Override
	public String toString() {
		return toString(10, true, false);
	}

	/*
	 * Default value of formatAsCLiteral is false
	 */
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
				N = getZExtValue();
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
				QuotientRemainderPair qrPair = APIntUtils.udivrem(Tmp, ULong.valueOf(Radix));
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

	/*
	 *  Considers the APInt to be unsigned and converts it into a string in the
	 * radix given. The radix can be 2, 8, 10 16, or 36. The default value is 10
	 */
	public String toStringUnsigned(int Radix) {
		return toString(Radix, false, false);
	}

	/// Considers the APInt to be signed and converts it into a string in the
	/// radix given. The radix can be 2, 8, 10, 16, or 36.
	public String toStringSigned(int Radix) {
		return toString(Radix, true, false);
	}

	/*
	 *  Returns a byte-swapped representation of this APInt Value.
	 */
	public APInt byteSwap() {
		// TODO - Incomplete implementation in MathUtils
		if(numBits < 16 && numBits % 16 != 0) {
			throw new IllegalArgumentException("Cannot byteswap!");
		}

		if (numBits == 16)
			return new APInt(numBits, ULong.valueOf(MathUtils.ByteSwap_16(unsignedVals[0].shortValue())), false);
		if (numBits == 32)
			return new APInt(numBits, ULong.valueOf(MathUtils.ByteSwap_32((int)unsignedVals[0].longValue())), false);
		if (numBits == 48) {
			int Tmp1 = (int) unsignedVals[0].rightShift(16).longValue();
			Tmp1 = MathUtils.ByteSwap_32(Tmp1);
			short Tmp2 = unsignedVals[0].shortValue();
			Tmp2 = MathUtils.ByteSwap_16((short)Tmp2);
			return new APInt(numBits, ULong.valueOf(Tmp2).leftShift(32).or(Tmp1), false);
		}
		if (numBits == 64)
			return new APInt(numBits, MathUtils.ByteSwap_64(unsignedVals[0]), false);

		APInt Result = new APInt(getNumWords() * AP_INT_BITS_PER_WORD, ULong.valueOf(0), false);

		for (int I = 0, N = getNumWords(); I != N; ++I) {
			Result.unsignedVals[I] = MathUtils.ByteSwap_64(unsignedVals[N - I - 1]);
		}

		if (Result.numBits != numBits) {
			Result.lshrInPlace(Result.numBits - numBits);
			Result.numBits = numBits;
		}

		return Result;
	}

	/*
	 *  Returns the value with the bit representation reversed of this APInt Value.
	 */
	public APInt reverseBits() {
		// TODO - Incomplete implementation in MathUtils
		switch (numBits) {
		case 64:
			return new APInt(numBits, MathUtils.reverseBits(unsignedVals[0]).unsignedVals[0], false);
		case 32:
			return new APInt(numBits, MathUtils.reverseBits(unsignedVals[0].intValue()).unsignedVals[0], false);
		case 16:
			return  new APInt(numBits, MathUtils.reverseBits(unsignedVals[0].shortValue()).unsignedVals[0], false);
		case 8:
			return new APInt(numBits, MathUtils.reverseBits(unsignedVals[0].byteValue()).unsignedVals[0], false);
		default:
			break;
		}

		APInt Val = this.clone();
		APInt Reversed = new APInt(numBits, ULong.valueOf(0), false);
		int S = numBits;

		for (; !Val.equals(0); Val.lshrInPlace(1)) {
			Reversed.leftShiftAssign(1);
			Reversed.orAssign(Val.getUnsignedVals()[0]);
			--S;
		}

		Reversed.leftShiftAssign(S);
		return Reversed;
	}

	/*
	 *  Converts this APInt to a double value.
	 */
	public double roundToDouble(boolean isSigned) {

		// Handle the simple case where the value is contained in one ULong.
		// It is wrong to optimize getWord(0) to VAL; there might be more than one
		// word.
		if (isSingleWord() || getActiveBits() <= AP_INT_BITS_PER_WORD) {
			if (isSigned) {
				long sext = MathUtils.signExtend64(getWord(0), numBits);
				return (double) sext;
			} 
			else
				return (double) getWord(0).longValue();
		}

		// Determine if the value is negative.
		boolean isNeg = isNegative();

		// Construct the absolute value if we're negative.
		APInt Tmp = isNeg ? this.mul(new APInt(numBits, ULong.valueOf(-1), false)) : (this);

		// Figure out how many bits we're using.
		int n = Tmp.getActiveBits();

		// The exponent (without bias normalization) is just the number of bits
		// we are using. Note that the sign bit is gone since we constructed the
		// absolute value.
		ULong exp = ULong.valueOf(n);

		// Return infinity for exponent overflow
		if (exp.isGreaterThan(1023)) {
			if (!isSigned || !isNeg)
				return Double.POSITIVE_INFINITY;
			else
				return Double.NEGATIVE_INFINITY;
		}

		exp = exp.add(1023); // Increment for 1023 bias

		// Number of bits in mantissa is 52. To obtain the mantissa value, we must
		// extract the high 52 bits from the correct words in pVal.
		ULong mantissa;
		int hiWord = APIntUtils.whichWord(n - 1);
		if (hiWord == 0) {
			mantissa = Tmp.unsignedVals[0];
			if (n > 52)
				mantissa = mantissa.rightShift(n - 52); // shift down, we want the top 52 bits.
		} else {
			if(hiWord <= 0) {
				throw new IllegalArgumentException("huh?");
			}
			ULong hibits = Tmp.unsignedVals[hiWord].leftShift((52 - n % AP_INT_BITS_PER_WORD));
			ULong lobits = Tmp.unsignedVals[hiWord - 1].rightShift((11 + n % AP_INT_BITS_PER_WORD));
			mantissa = hibits.or(lobits);
		}

		// The leading bit of mantissa is implicit, so get rid of it.
		ULong sign = isNeg ? ULong.valueOf(1).leftShift(AP_INT_BITS_PER_WORD - 1) : ULong.valueOf(0);
		ULong I = sign.or(exp.leftShift(52)).or(mantissa);
		return (double)I.longValue();
	}

	/*
	 *  Converts this unsigned APInt to a double value.
	 */
	public double roundToDouble() { 
		return roundToDouble(false); 
	}

	/// Converts this signed APInt to a double value.
	public double signedRoundToDouble() { 
		return roundToDouble(true); 
	}

	/* Converts APInt bits to a double
	 * The conversion does not do a translation from integer to double, it just
	 * re-interprets the bits as a double. Note that it is valid to do this on
	 * any bit width. Exactly 64 bits will be translated.
	 */
	public double bitsToDouble() {
		// TODO - Incomplete implementation in MathUtils
		return MathUtils.BitsToDouble(getWord(0));
	}

	/* Converts APInt bits to a float
	 * The conversion does not do a translation from integer to float, it just
	 * re-interprets the bits as a float. Note that it is valid to do this on
	 * any bit width. Exactly 32 bits will be translated.
	 */
	public float bitsToFloat() {
		// TODO - Incomplete implementation in MathUtils
		//return BitsToFloat(static_cast<uint32_t>(getWord(0)));
		return 0.0f;
	}

	// ***************************************************************** MATHEMATICS OPERATIONS ****************************************************************

	/*
	 * Returns the floor log base 2 of this APInt.
	 */
	public int logBase2() { 
		return getActiveBits() -  1; 
	}

	/* 
	 * Returns the ceil log base 2 of this APInt.
	 */
	public int ceilLogBase2() {
		APInt temp = this.clone();
		temp = temp.decrement();
		return temp.getActiveBits();
	}

	/* Returns the nearest log base 2 of this APInt. Ties round up.
	 *
	 * NOTE: When we have a numBits of 1, we define:
	 *
	 *   log2(0) = UINT32_MAX
	 *   log2(1) = 0
	 *
	 * to get around any mathematical concerns resulting from
	 * referencing 2 in a space where 2 does no exist.
	 */
	public int nearestLogBase2() {
		// Special case when we have a bitwidth of 1. If VAL is 1, then we
		// get 0. If VAL is 0, we get WORDTYPE_MAX which gets truncated to
		// UINT32_MAX.
		if (numBits == 1)
			return unsignedVals[0].subtract(-1).intValue();

		// Handle the zero case.
		if (isNullValue())
			return 2147483647;

		// The non-zero case is handled by computing:
		//
		//   nearestLogBase2(x) = logBase2(x) + x[logBase2(x)-1].
		//
		// where x[i] is referring to the value of the ith bit of x.
		int lg = logBase2();
		return lg + (unsignedVals[lg - 1].intValue());
	}

	/*
	 * Returns the log base 2 of this APInt if its an exact power of two, -1
	 * otherwise
	 */
	public int exactLogBase2() {
		if (!isPowerOf2())
			return -1;
		return logBase2();
	}

	/*
	 * Compute the square root
	 */
	public APInt sqrt() {

		// Determine the magnitude of the value.
		int magnitude = getActiveBits();

		// Use a fast table for some small values. This also gets rid of some
		// rounding errors in libc sqrt for small values.
		if (magnitude <= 5) {
			byte results[] = {
					/*     0 */ 0,
					/*  1- 2 */ 1, 1,
					/*  3- 6 */ 2, 2, 2, 2,
					/*  7-12 */ 3, 3, 3, 3, 3, 3,
					/* 13-20 */ 4, 4, 4, 4, 4, 4, 4, 4,
					/* 21-30 */ 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
					/*    31 */ 6};
			return new APInt(numBits, ULong.valueOf(results[(isSingleWord() ? unsignedVals[0] : unsignedVals[0]).intValue()]), false);
		}

		// If the magnitude of the value fits in less than 52 bits (the precision of
		// an IEEE double precision floating point value), then we can use the
		// libc sqrt function which will probably use a hardware sqrt computation.
		// This should be faster than the algorithm below.
		if (magnitude < 52) {
			ULong value = ULong.valueOf(Math.round(Math.sqrt((double)unsignedVals[0].longValue())));
			return new APInt( numBits, value, false);
		}

		// Okay, all the short cuts are exhausted. We must compute it. The following
		// is a classical Babylonian method for computing the square root. This code
		// was adapted to APInt from a wikipedia article on such computations.
		// See http://www.wikipedia.org/ and go to the page named
		// Calculate_an_integer_square_root.
		int nbits = numBits, i = 4;
		APInt testy = new APInt(numBits, ULong.valueOf(16), false);
		APInt x_old = new APInt(numBits, ULong.valueOf(1), false);
		APInt x_new = new APInt(numBits, ULong.valueOf(0), false);
		APInt two = new APInt(numBits, ULong.valueOf(2), false);

		// Select a good starting value using binary logarithms.
		for (;; i += 2, testy = testy.shl(2))
			if (i >= nbits || this.ule(testy)) {
				x_old = x_old.shl(i / 2);
				break;
			}

		// Use the Babylonian method to arrive at the integer square root:
		for (;;) {
			x_new = (this.udiv(x_old).add(x_old)).udiv(two);
			if (x_old.ule(x_new))
				break;
			x_old = x_new;
		}

		// Make sure we return the closest approximation
		// NOTE: The rounding calculation below is correct. It will produce an
		// off-by-one discrepancy with results from pari/gp. That discrepancy has been
		// determined to be a rounding issue with pari/gp as it begins to use a
		// floating point representation after 192 bits. There are no discrepancies
		// between this algorithm and pari/gp for bit widths < 192 bits.
		APInt square = x_old.mul(x_old).clone();
		APInt nextSquare = x_old.add(ULong.valueOf(1)).mul(x_old.add(ULong.valueOf(1)));
		if (this.ult(square))
			return x_old;

		if(this.ule(nextSquare)) {
			throw new IllegalArgumentException("Error in APInt::sqrt computation");
		}

		APInt midpoint = nextSquare.subtract(square).udiv(two);
		APInt offset = this.subtract(square);
		if (offset.ult(midpoint)) {
			return x_old;
		}

		return x_old.add(ULong.valueOf(1));
	}

	/*
	 * Get the absolute value;
	 *
	 * If *this is < 0 then return -(*this), otherwise *this;
	 */
	public APInt abs() {
		if (isNegative())
			return this.mul(ULong.valueOf(-1));
		return this;
	}

	/* 
	 * Returns the multiplicative inverse for a given modulo.
	 */
	public APInt multiplicativeInverse(APInt modulo) {
		if(!ult(modulo)) {
			throw new IllegalArgumentException("This APInt must be smaller than the modulo");
		}

		// Using the properties listed at the following web page (accessed 06/21/08):
		//   http://www.numbertheory.org/php/euclid.html
		// (especially the properties numbered 3, 4 and 9) it can be proved that
		// BitWidth bits suffice for all the computations in the algorithm implemented
		// below. More precisely, this number of bits suffice if the multiplicative
		// inverse exists, but may not suffice for the general extended Euclidean
		// algorithm.

		APInt r[] = new APInt[]{modulo, this};
		APInt t[] = {new APInt(numBits, ULong.valueOf(0), false), new APInt(numBits, ULong.valueOf(1), false)};
		APInt q = new APInt(numBits, ULong.valueOf(0), false);

		int i;
		for (i = 0; !r[i ^ 1].equals(ULong.valueOf(0)); i ^= 1) {
			// An overview of the math without the confusing bit-flipping:
			// q = r[i-2] / r[i-1]
			// r[i] = r[i-2] % r[i-1]
			// t[i] = t[i-2] - t[i-1] * q
			QuotientRemainderPair qr = APIntUtils.udivrem(r[i], r[i ^ 1]);
			q = qr.getQuotient();
			r[i] = qr.getRemainderApInt();

			t[i].subtractAssign(t[i ^ 1].mul(q));
		}

		// If this APInt and the modulo are not coprime, there is no multiplicative
		// inverse, so return 0. We check this by looking at the next-to-last
		// remainder, which is the gcd(*this,modulo) as calculated by the Euclidean
		// algorithm.
		if (r[i].notEquals(ULong.valueOf(1)))
			return new APInt(numBits, ULong.valueOf(0), false);

		// The next-to-last t is the multiplicative inverse.  However, we are
		// interested in a positive inverse. Calculate a positive one from a negative
		// one if necessary. A simple addition of the modulo suffices because
		// abs(t[i]) is known to be less than *this/2 (see the link above).
		if (t[i].isNegative())
			t[i].addAssign(modulo);

		return t[i];
	}

	//************************************************************ SUPPORT FOR DIVISTION BY CONSTANT ************************************************************

	/*
	 *  Calculate the magic number for signed division by a constant.
	 */
	public MagicNum magic() {
		APInt d = this.clone();
		int p;
		APInt ad, anc, delta, q1, r1, q2, r2, t;
		APInt signedMin = APIntUtils.getSignedMinValue(d.getNumBits());

		ad = d.abs();
		t = signedMin.add(d.lshr(d.getNumBits() - 1));
		anc = t.subtract(ULong.valueOf(1)).subtract(t.urem(ad));  // absolute value of nc
		p = d.getNumBits() - 1;   // initialize p
		q1 = signedMin.udiv(anc);  // initialize q1 = 2p/abs(nc)
		r1 = signedMin.subtract(q1.mul(anc)); // initialize r1 = rem(2p,abs(nc))
		q2 = signedMin.udiv(ad);   // initialize q2 = 2p/abs(d)
		r2 = signedMin.subtract(q2.mul(ad));  // initialize r2 = rem(2p,abs(d))
		do {
			p = p + 1;
			q1 = q1.leftShift(1);      // update q1 = 2p/abs(nc)
			r1 = r1.leftShift(1);      // update r1 = rem(2p/abs(nc))
			if (r1.uge(anc)) { // must be unsigned comparison
				q1 = q1.add(ULong.valueOf(1));
				r1 = r1.subtract(anc);
			}
			q2 = q2.leftShift(1);     // update q2 = 2p/abs(d)
			r2 = r2.leftShift(1);     // update r2 = rem(2p/abs(d))
			if (r2.uge(ad)) { // must be unsigned comparison
				q2 = q2.add(ULong.valueOf(1));
				r2 = r2.subtract(ad);
			}
			delta = ad.subtract(r2);
		} while (q1.ult(delta) || (q1 == delta && r1.equals(ULong.valueOf(0))));

		APInt m = q2.add(ULong.valueOf(1));
		if (d.isNegative())
			m = m.mul(ULong.valueOf(-1));            // resulting magic number
		int s = p - d.getNumBits();                 //  resulting shift

		return new MagicNum(m, s);
	}

	/*
	 * Calculate the magic number for unsigned division by a constant. 
	 * Default value of LeadingZeros is 0.
	 */
	public MagicNumUnsigned magicu(int LeadingZeros) {
		APInt d = this.clone();
		int p;
		APInt nc, delta, q1, r1, q2, r2;
		int addIndicator = 0; // initialize "add" indicator
		APInt allOnes = APIntUtils.getAllOnesValue(d.getNumBits()).lshr(LeadingZeros);
		APInt signedMin = APIntUtils.getSignedMinValue(d.getNumBits());
		APInt signedMax = APIntUtils.getSignedMaxValue(d.getNumBits());

		nc = allOnes.subtract((allOnes.subtract(d)).urem(d));
		p = d.getNumBits() - 1;  // initialize p
		q1 = signedMin.udiv(nc);  // initialize q1 = 2p/nc
		r1 = signedMin.subtract(q1.mul(nc)); // initialize r1 = rem(2p,nc)
		q2 = signedMax.udiv(d);   // initialize q2 = (2p-1)/d
		r2 = signedMax.subtract(q2.mul(d));  // initialize r2 = rem((2p-1),d)
		do {
			p = p + 1;
			if (r1.uge(nc.subtract(r1))) {
				q1 = q1.add(q1).add(ULong.valueOf(1));  // update q1
				r1 = r1.add(r1).subtract(nc); // update r1
			} else {
				q1 = q1.add(q1); // update q1
				r1 = r1.add(r1); // update r1
			}
			if (r2.add(ULong.valueOf(1)).uge(d.subtract(r2))) {
				if (q2.uge(signedMax))
					addIndicator = 1;
				q2 = q2.add(q2).add(ULong.valueOf(1));                // update q2
				r2 = r2.add(r2).add(ULong.valueOf(1)).subtract(d);    // update r2
			} else {
				if (q2.uge(signedMin))
					addIndicator = 1;
				q2 = q2.add(q2);     // update q2
				r2 = r2.add(r2).add(ULong.valueOf(1)); // update r2
			}
			delta = d.subtract(ULong.valueOf(1)).subtract(r2);
		} while (p < d.getNumBits() * 2 &&
				(q1.ult(delta) || (q1 == delta && r1.equals(ULong.valueOf(0)))));

		APInt magicNum = q2.add(ULong.valueOf(1));              // resulting magic number
		int s = p - d.getNumBits(); // resulting shift

		boolean addIndicatorBool = addIndicator == 1? true: false;

		MagicNumUnsigned magu = new MagicNumUnsigned(magicNum, s, addIndicatorBool);

		return magu;
	}


	/*Get the number of words.
	 * *NOTE* Here one word's bitwidth equals to that of ULong
	 * Returns the number of words to hold the integer value with a given bit width.
	 */
	public int getNumWords(int BitWidth) {
		return (numBits + AP_INT_BITS_PER_WORD - 1) / AP_INT_BITS_PER_WORD;
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

	/*
	 * Set the given bit to 0 whose position is given as "bitPosition".
	 */
	public void clearBit(int bitPosition) {
		if(bitPosition > numBits){
			throw new IllegalArgumentException("BitPosition out of range");
		}

		ULong mask = APIntUtils.maskBit(bitPosition).complement();
		if (isSingleWord()){
			unsignedVals[0] = unsignedVals[0].and(mask);
		}
		else{
			int wordIndex = APIntUtils.whichWord(bitPosition);
			unsignedVals[wordIndex] = unsignedVals[wordIndex].and(mask);
		}
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
		APInt newVal = this.clone();
		return newVal.addAssign(RHS);
	}

	public APInt subtract(final APInt other) {
		APInt newVal = this.clone();
		return newVal.subtractAssign(other);
	}

	public APInt subtract(final ULong RHS) {
		APInt newVal = this.clone();
		return newVal.subtractAssign(RHS);
	}

	public APInt and(APInt other) {
		APInt newVal = this.clone();
		return newVal.andAssign(other);
	}
	
	public APInt and(ULong other) {
		APInt newVal = this.clone();
		return newVal.andAssign(other);
	}
	
	public APInt or(APInt other) {
		APInt newVal = this.clone();
		return newVal.orAssign(other);
	}
	
	public APInt or(ULong other) {
		APInt newVal = this.clone();
		return newVal.orAssign(other);
	}

	public APInt xor(APInt other) {
		APInt newVal = this.clone();
		return newVal.xorAssign(other);
	}
	
	public APInt xor(ULong other) {
		APInt newVal = this.clone();
		return newVal.xorAssign(other);
	}

	/* Left-shift assignment function.
	 * Shifts *this left by shiftAmt and assigns the result to *this.
	 * Returns *this after shifting left by ShiftAmt
	 */
	public APInt shl(APInt ShiftAmt) {
		APInt newVal = this.clone();
		newVal.leftShiftAssign(ShiftAmt);
	    return newVal;
	}

	public APInt lshr(APInt other) {
		APInt newVal = this.clone();
		newVal.lshrInPlace(other);
	    return newVal;
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
			flipAllBitsSlowCase();
		}
	}
	
	/* 
	 * Toggle every bit to its opposite value - slow case
	 */
	public void flipAllBitsSlowCase() {
	  APIntUtils.tcComplement(unsignedVals, getNumWords());
	  clearUnusedBits();
	}

	/*
	 * Negate this APInt in place.
	 */
	public void negate() {
		flipAllBits();
		this.add(ULong.valueOf(1));
	}

	/**
	 * Tests if the bit is set in this integer at the specified location
	 * @param numBits
	 * @return
	 */
	protected boolean isBitSet(int bitPosition) {
		if(unsignedVals[getNumWords() -1].and(APIntUtils.maskBit(bitPosition)).longValue() == 0) {
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

		ULong mask = APIntUtils.maskBit(bitPosition);
		if (isSingleWord()) {
			unsignedVals[0].or(mask);
		}
		else {
			int wordIndex = APIntUtils.whichWord(bitPosition);
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

		return APIntUtils.tcCompare(unsignedVals, RHS.unsignedVals, getNumWords());
	}

	protected int compare(APInt RHS) {
		if(numBits != RHS.getNumBits()) {
			throw new IllegalArgumentException("Bit widths must be same for comparison");
		}
		if (isSingleWord()) {
			return unsignedVals[0].isLesserThan(RHS.unsignedVals[0]) ? -1 : 
				(unsignedVals[0].isGreaterThan(RHS.unsignedVals[0]) ? -1 : 1);
		}

		return APIntUtils.tcCompare(unsignedVals, RHS.unsignedVals, getNumWords());
	}

	/* Arithmetic right-shift this APInt by ShiftAmt in place.
	 */
	public void ashrInPlace(int shiftAmt) {
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

		ashrSlowCase(shiftAmt);
	}	

	/*
	 * Arithmetic right-shift this APInt by shiftAmt.
	 * Arithmetic right-shift function.
	 */
	public void ashrSlowCase(int ShiftAmt) {
		// Don't bother performing a no-op shift.
		if (ShiftAmt <= 0)
			return;

		// Save the original sign bit for later.
		boolean Negative = isNegative();

		// WordShift is the inter-part shift; BitShift is intra-part shift.
		int WordShift = ShiftAmt / AP_INT_BITS_PER_WORD;
		int BitShift = ShiftAmt % AP_INT_BITS_PER_WORD;

		int WordsToMove = getNumWords() - WordShift;
		if (WordsToMove != 0) {
			// Sign extend the last word to fill in the unused bits.
			unsignedVals[getNumWords() - 1] = ULong.valueOf(MathUtils.signExtend64(
					unsignedVals[getNumWords() - 1], (numBits - 1) % AP_INT_BITS_PER_WORD) + 1);

			// Fastpath for moving by whole words.
			if (BitShift == 0) {
				// std::memmove(unsignedVals, unsignedVals + WordShift, WordsToMove * APINT_WORD_SIZE);
				for(int i = 0; i < WordShift + WordsToMove; i++) {
					unsignedVals[i] = unsignedVals[i + WordShift];
				}
			}
			else {
				// Move the words containing significant bits.
				for (int i = 0; i != WordsToMove - 1; ++i)
					unsignedVals[i] = (unsignedVals[i + WordShift].rightShift(BitShift)).or(
							unsignedVals[i + WordShift + 1].leftShift((AP_INT_BITS_PER_WORD - BitShift)));

				// Handle the last word which has no high bits to copy.
				unsignedVals[WordsToMove - 1] = unsignedVals[WordShift + WordsToMove - 1].rightShift(BitShift);
				// Sign extend one more time.
				unsignedVals[WordsToMove - 1] =
						ULong.valueOf(MathUtils.signExtend64(unsignedVals[WordsToMove - 1], AP_INT_BITS_PER_WORD - BitShift));
			}
		}


		// Fill in the remainder based on the original sign.
		APIntUtils.setWordValues(unsignedVals, Negative ? -1 : 0, WordsToMove, unsignedVals.length);
		clearUnusedBits();
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
		APIntUtils.tcAnd(unsignedVals, RHS.getUnsignedVals(), getNumWords());
	}

	/*
	 *  Slow case for operator |=.
	 */
	public void OrAssignSlowCase(APInt RHS) {
		APIntUtils.tcOr(unsignedVals, RHS.unsignedVals, getNumWords());
	}

	/*
	 *  Slow case for operator ^=.
	 */
	public void XorAssignSlowCase(APInt RHS) {
		APIntUtils.tcXOr(unsignedVals, RHS.unsignedVals, getNumWords());
	}

	/// out-of-line slow case for setBits.
	public void setBitsSlowCase(int loBit, int hiBit) {
		int loWord = APIntUtils.whichWord(loBit);
		int hiWord = APIntUtils.whichWord(hiBit);

		// Create an initial mask for the low word with zeros below loBit.
		ULong loMask = ULong.valueOf(WORDTYPE_MAX.shiftLeft(APIntUtils.whichBit(loBit)));

		// If hiBit is not aligned, we need a high mask.
		int hiShiftAmt = APIntUtils.whichBit(hiBit);
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
		APIntUtils.tcShiftLeft(unsignedVals, getNumWords(), ShiftAmt);
		clearUnusedBits();
	}

	/* 
	 * If this value is smaller than the specified limit, return it, otherwise
	 * return the limit value.  This causes the value to saturate to the limit.
	 */
	protected ULong getLimitedValue(ULong Limit) {
		if(Limit == null) {
			Limit = ULong.valueOf(ULong.MAX_VALUE);
		}
		return ugt(Limit) ? Limit : getZExtValue();
	}

	public Boolean getOpOverflowFlag() {
		return opOverflowFlag;
	}

	public void setOpOverflowFlag(Boolean opOverflowFlag) {
		this.opOverflowFlag = opOverflowFlag;
	}

	/* 
	 * Get the word corresponding to a bit position
	 * Returns the corresponding word for the specified bit position.
	 */
	public ULong getWord(int bitPosition) {
		return isSingleWord() ? unsignedVals[0] : unsignedVals[APIntUtils.whichWord(bitPosition)];
	}

	/*
	* Unary bitwise complement operator.
	* Returns an APInt that is the bitwise complement of \p v.
	*/
	public APInt complement(APInt v) {
	  APInt newVal = this.clone();
	  newVal.flipAllBits();
	  return newVal;
	}
}

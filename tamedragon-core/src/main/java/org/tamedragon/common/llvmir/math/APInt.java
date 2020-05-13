package org.tamedragon.common.llvmir.math;


//===-- llvm/ADT/APInt.h - For Arbitrary Precision Integer -----*- C++ -*--===//
//
// Part of the LLVM Project, under the Apache License v2.0 with LLVM Exceptions.
// See https://llvm.org/LICENSE.txt for license information.
// SPDX-License-Identifier: Apache-2.0 WITH LLVM-exception
//
//===----------------------------------------------------------------------===//
///
/// This file implements a class to represent arbitrary precision
/// integral constant values and operations on them.
///
//===----------------------------------------------------------------------===//



//===----------------------------------------------------------------------===//
//	                              APInt Class
//===----------------------------------------------------------------------===//

/// Class for arbitrary precision integers.
///
/// APInt is a functional replacement for common case unsigned integer type like
/// "unsigned", "unsigned long" or "uint64_t", but also allows non-byte-width
/// integer sizes and large integer value types such as 3-bits, 15-bits, or more
/// than 64-bits of precision. APInt provides a variety of arithmetic operators
/// and methods to manipulate integer values of any bit-width. It supports both
/// the typical integer arithmetic and comparison operations as well as bitwise
/// manipulation.
///
/// The class has several invariants worth noting:
///   * All bit, byte, and word positions are zero-based.
///   * Once the bit width is set, it doesn't change except by the Truncate,
///     SignExtend, or ZeroExtend operations.
///   * All binary operators must be on APInt instances of the same bit width.
///     Attempting to use these operators on instances with different bit
///     widths will yield an assertion.
///   * The value is stored canonically as an unsigned value. For operations
///     where it makes a difference, there are both signed and unsigned variants
///     of the operation. For example, sdiv and udiv. However, because the bit
///     widths must be the same, operations such as Mul and Add produce the same
///     results regardless of whether the values are interpreted as signed or
///     not.
///   * In general, the class tries to follow the style of computation that LLVM
///     uses in its IR. This simplifies its use for LLVM.
///

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Class for arbitrary precision integers.
 * @author ipsg
 *
 */
public class APInt {

	private static final int AP_INT_BITS_PER_WORD = 64;
	private static BigInteger WORDTYPE_MAX = ULong.MAX_VALUE;

	protected int numBits;
	//private String valStr;
	protected ULong unsignedVal;

	public APInt(int numBits, String val, boolean isSigned ){
		this.numBits = numBits;

		if("true".equals(val)) {
			unsignedVal = ULong.valueOf("1");
		}
		else if("false".equals(val)) {
			unsignedVal = ULong.valueOf("0");
		}
		else {
			unsignedVal = ULong.valueOf(val);
		}
	}

	public APInt() {

	}

	public APInt(int numbits, String str, int radix) {
		if(numbits <= 0) {
			throw new IllegalArgumentException("Bitwidth too small");
		}

		fromString(numbits, str, radix);
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

		// Allocate memory if needed
		if (isSingleWord()) {
			unsignedVal = ULong.valueOf(0);
		}
		else {
			// TODO Implement this
			//U.pVal = getClearedMemory(getNumWords());
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
					unsignedVal = unsignedVal.leftShift(shift);	
				}
				else {
					unsignedVal = unsignedVal.mul(ULong.valueOf(radix));
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

	/* Create a new APInt of numBits width, initialized as val.
	 *
	 * If isSigned is true then val is treated as if it were a signed value
	 * (i.e. as an int64_t) and the appropriate sign extension to the bit width
	 * will be done. Otherwise, no sign extension occurs (high order bits beyond
	 * the range of val are zero filled).
	 *
	 * \param numBits the bit width of the constructed APInt
	 * \param val the initial value of the APInt
	 * \param isSigned how to treat signedness of val
	 */
	//public APInt(int numBits, ULong val, boolean isSigned = false)
	public APInt(int numBits, ULong val, boolean isSigned) {

		this.numBits = numBits;
		if(numBits <= 0) {
			throw new IllegalArgumentException("bitwidth too small");
		}
		if (isSingleWord()) {
			this.unsignedVal = val;
			clearUnusedBits();
		} else {
			// TODO Implement this
			//initSlowCase(val, isSigned);
		}
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
			return MathUtils.countLeadingZeros(unsignedVal) - unusedBits;
		}

		// TODO for integers larger than 64 bits
		return -1;
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
			return MathUtils.countLeadingOnes(unsignedVal.leftShift(AP_INT_BITS_PER_WORD - numBits));
		}
		else {
			// TODO for integers larger than 64 bits
			return -1;
		}
	}

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

	/* Gets minimum unsigned value of APInt for a specific bit width.
	 */
	public static APInt getMinValue(int numBits) { return new APInt(numBits, ULong.valueOf("0"), false); }

	/*Gets minimum signed value of APInt for a specific bit width.
	 */
	public static APInt getSignedMinValue(int numBits) {
		APInt api = new APInt(numBits, ULong.valueOf("0"), false);
		api.setBit(numBits - 1);
		return api;
	}


	/* Get the all-ones value.
	 * \returns the all-ones value for an APInt of the specified bit-width.
	 */
	public static APInt getAllOnesValue(int numBits) {
		return new APInt(numBits, ULong.valueOf(WORDTYPE_MAX), true);
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
			unsignedVal.andInPlace(mask);
		}
		else{
			// TODO Implement this
			//U.pVal[getNumWords() - 1] &= mask;
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
			unsignedVal = unsignedVal.and(mask);
		}
		else{
			// TODO Implement this
			// U.pVal[whichWord(BitPosition)] &= Mask;
		}
	}

	public String toString() {
		return "" + unsignedVal;
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
		if (unsignedVal.longValue() == 0) {
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

		// TODO Implement the arbitrary precision int
		return result;
	}

	public boolean isNullValue() {
		if(unsignedVal.longValue() == 0) {
			return true;
		}
		return false;
	}


	/* Addition assignment operator.
	 * In place addition.
	 * returns this
	 */
	public APInt add(final APInt RHS) {
		return add(RHS.unsignedVal);
	}

	public APInt add(final ULong RHS) {
		if (isSingleWord()) {
			unsignedVal.addInPlace(RHS);
		}
		else {
			// TODO Implement this
			//tcAddPart(U.pVal, RHS, getNumWords());
		}
		return clearUnusedBits();
	}
	
	public APInt subtract(final APInt other) {
		return subtract(other.unsignedVal);
	}
	
	public APInt subtract(final ULong RHS) {
		if (isSingleWord()) {
			unsignedVal.subtractInPlace(RHS);
		}
		else {
			// TODO Implement this
			//tcAddPart(U.pVal, RHS, getNumWords());
		}
		return clearUnusedBits();
	}

	
	public APInt mul(APInt other) {
		return mul(other.unsignedVal);
	}
	
	public APInt mul(final ULong RHS) {
		if (isSingleWord()) {
			unsignedVal.mulInPlace(RHS);
		}
		else {
			// TODO Implement this
			//tcAddPart(U.pVal, RHS, getNumWords());
		}
		return clearUnusedBits();
	}

	// TODO Replace above with the ones below:
	public APInt udiv(APInt other) {
		return null;
	}

	public APInt sdiv(APInt other) {
		return null;
	}

	public APInt urem(APInt other) {
		return null;
	}
	
	public APInt srem(APInt other) {
		return null;
	}
	
	public APInt andWith(APInt other) {
		return null;
	}
	
	public APInt orWith(APInt other) {
		return null;
	}
	
	public APInt xorWith(APInt other) {
		return null;
	}
	
	public APInt shl(APInt other) {
		return null;
	}
	
	public APInt lshr(APInt other) {
		return null;
	}

	public APInt ashr(APInt other) {
		return null;
	}

	public APInt not() {
		return null;
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
			return new APInt(width, unsignedVal, false);
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
			long signExtended = MathUtils.signExtend64(unsignedVal, numBits);
			return new APInt(width, ULong.valueOf(signExtended), false);
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

	/*@returns the '0' value for an APInt of the specified bit-width.
	  /// @brief Get the '0' value.

	 */
	public static APInt getNullValue(int numBits) {
		return new APInt(numBits, "0", false);
	}

	public boolean isAllOnesValue() {
		if (isSingleWord()) {
			return unsignedVal.getUnsignedBigInt().equals(ULong.valueOf(WORDTYPE_MAX.shiftRight(AP_INT_BITS_PER_WORD - numBits)));
		}
		else {
			// TODO Implement this
			// return countTrailingOnesSlowCase() == BitWidth;
			return false;
		}
	}

	/// Toggle every bit to its opposite value.
	public void flipAllBits() {
		if (isSingleWord()) {
			unsignedVal.xorInPlace(ULong.MAX);
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
		unsignedVal.addInPlace(1);
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


	/*Determine if this is a value of 1.
	  This checks to see if the value of this APInt is one.
	 */
	public boolean isOneValue() {
		if (isSingleWord()) {
			return unsignedVal.equals(1);
		}
		else {
			// TODO Implement this
			//  return countLeadingZerosSlowCase() == BitWidth - 1;
			return false;
		}

	}

	public boolean isSignMask() { return isMinSignedValue(); }

	/// This checks to see if the value of this APInt is the minimum signed
	/// value for the APInt's bit width.
	/// @brief Determine if this is the smallest signed value.
	public boolean isMinSignedValue() {
		// return BitWidth == 1 ? VAL == 1 : isNegative() && isPowerOf2();
		//TODO Get this right
		return false;
	}

	/**
	 * Tests if the bit is set in this integer at the specified location
	 * @param numBits
	 * @return
	 */
	protected boolean isBitSet(int bitPosition) {
		if(this.getUnsignedVal().and(maskBit(bitPosition)).longValue() == 0) {
			return false;
		}
		return true;
	}


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
	public boolean isNonNegative() { return !isNegative(); }

	/*
	 * Determine if this APInt Value is positive.
	 * This tests if the value of this APInt is positive (> 0). Note
	 * that 0 is not a positive value.
	 * \returns true if this APInt is positive.
	 */
	public boolean isStrictlyPositive() {
		return isNonNegative() && !isNullValue(); 
	}

	/* Check if this APInt's value is a power of two greater than zero.
	 * returns true if the argument APInt value is a power of two > 0.
	 */
	public boolean isPowerOf2() {
		if (isSingleWord()) {
			return MathUtils.isPowerOf2_64(unsignedVal);
		}
		else {
			//  return countPopulationSlowCase() == 1;
			return false;
		}
	}

	public static APInt getLowBitsSet(int dstWidth, int srcWidth) {
		// TODO Auto-generated method stub
		return null;
	}

	/* Set a given bit to 1.
	 * Set the given bit to 1 whose position is given as "bitPosition".
	 * 
	 */
	public void setBit(int bitPosition) {
		if(bitPosition > numBits) {
			throw new IllegalArgumentException("BitPosition out of range");
		}

		ULong Mask = maskBit(bitPosition);
		if (isSingleWord()) {
			unsignedVal.or(Mask);
		}
		else {
			// TODO Implement this
			//U.pVal[whichWord(BitPosition)] |= Mask;
		}
	}

	/* Set the sign bit to 1.
	 */
	public void setSignBit() {
		setBit(numBits - 1);
	}

	public ULong getUnsignedVal() {
		return unsignedVal;
	}

	public void setUnsignedVal(ULong unsignedVal) {
		this.unsignedVal = unsignedVal;
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
			return unsignedVal.equals(RHS.getUnsignedVal());
		}
		// TODO Implement this
		//return EqualSlowCase(RHS);
		return false;
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
			long lhsSext = MathUtils.signExtend64(unsignedVal, numBits);
			long rhsSext = MathUtils.signExtend64(RHS.getUnsignedVal(), numBits);

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
			return unsignedVal.isLesserThan(RHS.unsignedVal) ? -1 : 
				(unsignedVal.isGreaterThan(RHS.unsignedVal) ? -1 : 1);
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
			return unsignedVal;
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
		if (isSingleWord())
			return MathUtils.signExtend64(unsignedVal, numBits);
		if(getMinSignedBits() > 64) {
			throw new IllegalArgumentException("Too many bits for int64_t");
		}

		return unsignedVal.longValue();
	}

	/* This function returns a pointer to the internal storage of the APInt.
	 * This is useful for writing out the APInt in binary form without any
	 * conversions.
	 */
	public ULong getRawData()  {
		if (isSingleWord()) {
			return unsignedVal;
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
			result = new APInt(width, unsignedVal, false);
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

	/* Arithmetic right-shift function.
	 * Arithmetic right-shift this APInt by shiftAmt.
	 */
	public APInt ashr(ULong shiftAmt) {
		APInt R = new APInt(this.numBits, this.unsignedVal, true);
		R.ashrInPlace(shiftAmt);
		return R;
	}

	/* Arithmetic right-shift this APInt by ShiftAmt in place.
	 */
	public void ashrInPlace(ULong shiftAmt) {
		if(shiftAmt.isGreaterThan(numBits)) {
			throw new IllegalArgumentException("Invalid shift amount");
		}
		if (isSingleWord()) {
			long SExtVAL = MathUtils.signExtend64(unsignedVal, numBits);
			if (shiftAmt.intValue() == numBits) {
				unsignedVal = ULong.valueOf(SExtVAL >> (AP_INT_BITS_PER_WORD - 1)); // Fill with sign bit.
			}
			else {
				unsignedVal = ULong.valueOf(SExtVAL >> shiftAmt.longValue());
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

	  /// Determine which word a bit is in.
	  ///
	  /// \returns the word position for the specified bit position.
	  static unsigned whichWord(unsigned bitPosition) {
	    return bitPosition / APINT_BITS_PER_WORD;
	  }

	  /// Get the word corresponding to a bit position
	  /// \returns the corresponding word for the specified bit position.
	  uint64_t getWord(unsigned bitPosition) const {
	    return isSingleWord() ? U.VAL : U.pVal[whichWord(bitPosition)];
	  }

	  /// Utility method to change the bit width of this APInt to new bit width,
	  /// allocating and/or deallocating as necessary. There is no guarantee on the
	  /// value of any bits upon return. Caller should populate the bits after.
	  void reallocate(unsigned NewBitWidth);

	  /// An internal division function for dividing APInts.
	  ///
	  /// This is used by the toString method to divide by the radix. It simply
	  /// provides a more convenient form of divide for internal use since KnuthDiv
	  /// has specific constraints on its inputs. If those constraints are not met
	  /// then it provides a simpler form of divide.
	  static void divide(WordType *LHS, unsigned lhsWords,
	                     const WordType *RHS, unsigned rhsWords, WordType *Quotient,
	                     WordType *Remainder);

	  /// out-of-line slow case for inline constructor
	  void initSlowCase(uint64_t val, bool isSigned);

	  /// shared code between two array constructors
	  void initFromArray(ArrayRef<uint64_t> array);

	  /// out-of-line slow case for inline copy constructor
	  void initSlowCase(APInt &that);

	  /// out-of-line slow case for shl
	  void shlSlowCase(unsigned ShiftAmt);

	  /// out-of-line slow case for lshr.
	  void lshrSlowCase(unsigned ShiftAmt);

	  /// out-of-line slow case for ashr.
	  void ashrSlowCase(unsigned ShiftAmt);

	  /// out-of-line slow case for operator=
	  void AssignSlowCase(APInt &RHS);

	  /// out-of-line slow case for operator==
	  bool EqualSlowCase(APInt &RHS) const LLVM_READONLY;

	  /// out-of-line slow case for countLeadingZeros
	  unsigned countLeadingZerosSlowCase() const LLVM_READONLY;

	  /// out-of-line slow case for countLeadingOnes.
	  unsigned countLeadingOnesSlowCase() const LLVM_READONLY;

	  /// out-of-line slow case for countTrailingZeros.
	  unsigned countTrailingZerosSlowCase() const LLVM_READONLY;

	  /// out-of-line slow case for countTrailingOnes
	  unsigned countTrailingOnesSlowCase() const LLVM_READONLY;

	  /// out-of-line slow case for countPopulation
	  unsigned countPopulationSlowCase() const LLVM_READONLY;

	  /// out-of-line slow case for intersects.
	  bool intersectsSlowCase(APInt &RHS) const LLVM_READONLY;

	  /// out-of-line slow case for isSubsetOf.
	  bool isSubsetOfSlowCase(APInt &RHS) const LLVM_READONLY;

	  /// out-of-line slow case for setBits.
	  void setBitsSlowCase(unsigned loBit, unsigned hiBit);

	  /// out-of-line slow case for flipAllBits.
	  void flipAllBitsSlowCase();

	  /// out-of-line slow case for operator&=.
	  void AndAssignSlowCase(APInt& RHS);

	  /// out-of-line slow case for operator|=.
	  void OrAssignSlowCase(APInt& RHS);

	  /// out-of-line slow case for operator^=.
	  void XorAssignSlowCase(APInt& RHS);

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

	  /// Destructor.
	  ~APInt() {
	    if (needsCleanup())
	      delete[] U.pVal;
	  }

	  /// Default constructor that creates an uninteresting APInt
	  /// representing a 1-bit zero value.
	  ///
	  /// This is useful for object deserialization (pair this with the static
	  ///  method Read).
	  explicit APInt() : BitWidth(1) { U.VAL = 0; }

	  /// Returns whether this instance allocated memory.
	  bool needsCleanup() const { return !isSingleWord(); }

	  /// Used to insert APInt objects, or objects that contain APInt objects, into
	  ///  FoldingSets.
	  void Profile(FoldingSetNodeID &id) const;

	  /// @}
	  /// \name Value Tests
	  /// @{

	  /// Determine if sign bit of this APInt is set.
	  ///
	  /// This tests the high bit of this APInt to determine if it is set.
	  ///
	  /// \returns true if this APInt has its sign bit set, false otherwise.
	  bool isSignBitSet() const { return (*this)[BitWidth-1]; }

	  /// Determine if sign bit of this APInt is clear.
	  ///
	  /// This tests the high bit of this APInt to determine if it is clear.
	  ///
	  /// \returns true if this APInt has its sign bit clear, false otherwise.
	  bool isSignBitClear() const { return !isSignBitSet(); }

	  /// Determine if this APInt Value is positive.
	  ///
	  /// This tests if the value of this APInt is positive (> 0). Note
	  /// that 0 is not a positive value.
	  ///
	  /// \returns true if this APInt is positive.
	  bool isStrictlyPositive() const { return isNonNegative() && !isNullValue(); }

	  /// Determine if this APInt Value is non-positive (<= 0).
	  ///
	  /// \returns true if this APInt is non-positive.
	  bool isNonPositive() const { return !isStrictlyPositive(); }

	  /// Determine if all bits are set
	  ///
	  /// This checks to see if the value has all bits of the APInt are set or not.
	  bool isAllOnesValue() const {
	    if (isSingleWord())
	      return U.VAL == WORDTYPE_MAX >> (APINT_BITS_PER_WORD - BitWidth);
	    return countTrailingOnesSlowCase() == BitWidth;
	  }

	  /// Determine if all bits are clear
	  ///
	  /// This checks to see if the value has all bits of the APInt are clear or
	  /// not.
	  bool isNullValue() const { return !*this; }

	  /// Determine if this is a value of 1.
	  ///
	  /// This checks to see if the value of this APInt is one.
	  bool isOneValue() const {
	    if (isSingleWord())
	      return U.VAL == 1;
	    return countLeadingZerosSlowCase() == BitWidth - 1;
	  }

	  /// Determine if this is the largest unsigned value.
	  ///
	  /// This checks to see if the value of this APInt is the maximum unsigned
	  /// value for the APInt's bit width.
	  bool isMaxValue() const { return isAllOnesValue(); }

	  /// Determine if this is the largest signed value.
	  ///
	  /// This checks to see if the value of this APInt is the maximum signed
	  /// value for the APInt's bit width.
	  bool isMaxSignedValue() const {
	    if (isSingleWord())
	      return U.VAL == ((WordType(1) << (BitWidth - 1)) - 1);
	    return !isNegative() && countTrailingOnesSlowCase() == BitWidth - 1;
	  }

	  /// Determine if this is the smallest unsigned value.
	  ///
	  /// This checks to see if the value of this APInt is the minimum unsigned
	  /// value for the APInt's bit width.
	  bool isMinValue() const { return isNullValue(); }

	  /// Determine if this is the smallest signed value.
	  ///
	  /// This checks to see if the value of this APInt is the minimum signed
	  /// value for the APInt's bit width.
	  bool isMinSignedValue() const {
	    if (isSingleWord())
	      return U.VAL == (WordType(1) << (BitWidth - 1));
	    return isNegative() && countTrailingZerosSlowCase() == BitWidth - 1;
	  }

	  /// Check if this APInt has an N-bits unsigned integer value.
	  bool isIntN(unsigned N) const {
	    assert(N && "N == 0 ???");
	    return getActiveBits() <= N;
	  }

	  /// Check if this APInt has an N-bits signed integer value.
	  bool isSignedIntN(unsigned N) const {
	    assert(N && "N == 0 ???");
	    return getMinSignedBits() <= N;
	  }

	  /// Check if the APInt's value is returned by getSignMask.
	  ///
	  /// \returns true if this is the value returned by getSignMask.
	  bool isSignMask() const { return isMinSignedValue(); }

	  /// Convert APInt to a boolean value.
	  ///
	  /// This converts the APInt to a boolean value as a test against zero.
	  bool getBoolValue() const { return !!*this; }

	  /// If this value is smaller than the specified limit, return it, otherwise
	  /// return the limit value.  This causes the value to saturate to the limit.
	  uint64_t getLimitedValue(uint64_t Limit = UINT64_MAX) const {
	    return ugt(Limit) ? Limit : getZExtValue();
	  }

	  /// Check if the APInt consists of a repeated bit pattern.
	  ///
	  /// e.g. 0x01010101 satisfies isSplat(8).
	  /// \param SplatSizeInBits The size of the pattern in bits. Must divide bit
	  /// width without remainder.
	  bool isSplat(unsigned SplatSizeInBits) const;

	  /// \returns true if this APInt value is a sequence of \param numBits ones
	  /// starting at the least significant bit with the remainder zero.
	  bool isMask(unsigned numBits) const {
	    assert(numBits != 0 && "numBits must be non-zero");
	    assert(numBits <= BitWidth && "numBits out of range");
	    if (isSingleWord())
	      return U.VAL == (WORDTYPE_MAX >> (APINT_BITS_PER_WORD - numBits));
	    unsigned Ones = countTrailingOnesSlowCase();
	    return (numBits == Ones) &&
	           ((Ones + countLeadingZerosSlowCase()) == BitWidth);
	  }

	  /// \returns true if this APInt is a non-empty sequence of ones starting at
	  /// the least significant bit with the remainder zero.
	  /// Ex. isMask(0x0000FFFFU) == true.
	  bool isMask() const {
	    if (isSingleWord())
	      return isMask_64(U.VAL);
	    unsigned Ones = countTrailingOnesSlowCase();
	    return (Ones > 0) && ((Ones + countLeadingZerosSlowCase()) == BitWidth);
	  }

	  /// Return true if this APInt value contains a sequence of ones with
	  /// the remainder zero.
	  bool isShiftedMask() const {
	    if (isSingleWord())
	      return isShiftedMask_64(U.VAL);
	    unsigned Ones = countPopulationSlowCase();
	    unsigned LeadZ = countLeadingZerosSlowCase();
	    return (Ones + LeadZ + countTrailingZeros()) == BitWidth;
	  }

	  /// Get the SignMask for a specific bit width.
	  ///
	  /// This is just a wrapper function of getSignedMinValue(), and it helps code
	  /// readability when we want to get a SignMask.
	  static APInt getSignMask(unsigned BitWidth) {
	    return getSignedMinValue(BitWidth);
	  }

	  /// Get the '0' value.
	  ///
	  /// \returns the '0' value for an APInt of the specified bit-width.
	  static APInt getNullValue(unsigned numBits) { return APInt(numBits, 0); }

	  /// Compute an APInt containing numBits highbits from this APInt.
	  ///
	  /// Get an APInt with the same BitWidth as this APInt, just zero mask
	  /// the low bits and right shift to the least significant bit.
	  ///
	  /// \returns the high "numBits" bits of this APInt.
	  APInt getHiBits(unsigned numBits) const;

	  /// Compute an APInt containing numBits lowbits from this APInt.
	  ///
	  /// Get an APInt with the same BitWidth as this APInt, just zero mask
	  /// the high bits.
	  ///
	  /// \returns the low "numBits" bits of this APInt.
	  APInt getLoBits(unsigned numBits) const;

	  /// Return an APInt with exactly one bit set in the result.
	  static APInt getOneBitSet(unsigned numBits, unsigned BitNo) {
	    APInt Res(numBits, 0);
	    Res.setBit(BitNo);
	    return Res;
	  }

	  /// Get a value with a block of bits set.
	  ///
	  /// Constructs an APInt value that has a contiguous range of bits set. The
	  /// bits from loBit (inclusive) to hiBit (exclusive) will be set. All other
	  /// bits will be zero. For example, with parameters(32, 0, 16) you would get
	  /// 0x0000FFFF. Please call getBitsSetWithWrap if \p loBit may be greater than
	  /// \p hiBit.
	  ///
	  /// \param numBits the intended bit width of the result
	  /// \param loBit the index of the lowest bit set.
	  /// \param hiBit the index of the highest bit set.
	  ///
	  /// \returns An APInt value with the requested bits set.
	  static APInt getBitsSet(unsigned numBits, unsigned loBit, unsigned hiBit) {
	    assert(loBit <= hiBit && "loBit greater than hiBit");
	    APInt Res(numBits, 0);
	    Res.setBits(loBit, hiBit);
	    return Res;
	  }

	  /// Wrap version of getBitsSet.
	  /// If \p hiBit is no less than \p loBit, this is same with getBitsSet.
	  /// If \p hiBit is less than \p loBit, the set bits "wrap". For example, with
	  /// parameters (32, 28, 4), you would get 0xF000000F.
	  static APInt getBitsSetWithWrap(unsigned numBits, unsigned loBit,
	                                  unsigned hiBit) {
	    APInt Res(numBits, 0);
	    Res.setBitsWithWrap(loBit, hiBit);
	    return Res;
	  }

	  /// Get a value with upper bits starting at loBit set.
	  ///
	  /// Constructs an APInt value that has a contiguous range of bits set. The
	  /// bits from loBit (inclusive) to numBits (exclusive) will be set. All other
	  /// bits will be zero. For example, with parameters(32, 12) you would get
	  /// 0xFFFFF000.
	  ///
	  /// \param numBits the intended bit width of the result
	  /// \param loBit the index of the lowest bit to set.
	  ///
	  /// \returns An APInt value with the requested bits set.
	  static APInt getBitsSetFrom(unsigned numBits, unsigned loBit) {
	    APInt Res(numBits, 0);
	    Res.setBitsFrom(loBit);
	    return Res;
	  }

	  /// Get a value with high bits set
	  ///
	  /// Constructs an APInt value that has the top hiBitsSet bits set.
	  ///
	  /// \param numBits the bitwidth of the result
	  /// \param hiBitsSet the number of high-order bits set in the result.
	  static APInt getHighBitsSet(unsigned numBits, unsigned hiBitsSet) {
	    APInt Res(numBits, 0);
	    Res.setHighBits(hiBitsSet);
	    return Res;
	  }

	  /// Get a value with low bits set
	  ///
	  /// Constructs an APInt value that has the bottom loBitsSet bits set.
	  ///
	  /// \param numBits the bitwidth of the result
	  /// \param loBitsSet the number of low-order bits set in the result.
	  static APInt getLowBitsSet(unsigned numBits, unsigned loBitsSet) {
	    APInt Res(numBits, 0);
	    Res.setLowBits(loBitsSet);
	    return Res;
	  }

	  /// Return a value containing V broadcasted over NewLen bits.
	  static APInt getSplat(unsigned NewLen, const APInt &V);

	  /// Determine if two APInts have the same value, after zero-extending
	  /// one of them (if needed!) to ensure that the bit-widths match.
	  static bool isSameValue(APInt &I1, const APInt &I2) {
	    if (I1.getBitWidth() == I2.getBitWidth())
	      return I1 == I2;

	    if (I1.getBitWidth() > I2.getBitWidth())
	      return I1 == I2.zext(I1.getBitWidth());

	    return I1.zext(I2.getBitWidth()) == I2;
	  }

	  /// Overload to compute a hash_code for an APInt value.
	  friend hash_code hash_value(APInt &Arg);

	  /// @}
	  /// \name Unary Operators
	  /// @{

	  /// Postfix increment operator.
	  ///
	  /// Increments *this by 1.
	  ///
	  /// \returns a new APInt value representing the original value of *this.
	  const APInt operator++(int) {
	    APInt API(*this);
	    ++(*this);
	    return API;
	  }

	  /// Prefix increment operator.
	  ///
	  /// \returns *this incremented by one
	  APInt &operator++();

	  /// Postfix decrement operator.
	  ///
	  /// Decrements *this by 1.
	  ///
	  /// \returns a new APInt value representing the original value of *this.
	  const APInt operator--(int) {
	    APInt API(*this);
	    --(*this);
	    return API;
	  }

	  /// Prefix decrement operator.
	  ///
	  /// \returns *this decremented by one.
	  APInt &operator--();

	  /// Logical negation operator.
	  ///
	  /// Performs logical negation operation on this APInt.
	  ///
	  /// \returns true if *this is zero, false otherwise.
	  bool operator!() const {
	    if (isSingleWord())
	      return U.VAL == 0;
	    return countLeadingZerosSlowCase() == BitWidth;
	  }

	  /// @}
	  /// \name Assignment Operators
	  /// @{

	  /// Copy assignment operator.
	  ///
	  /// \returns *this after assignment of RHS.
	  APInt &operator=(APInt &RHS) {
	    // If the bitwidths are the same, we can avoid mucking with memory
	    if (isSingleWord() && RHS.isSingleWord()) {
	      U.VAL = RHS.U.VAL;
	      BitWidth = RHS.BitWidth;
	      return clearUnusedBits();
	    }

	    AssignSlowCase(RHS);
	    return *this;
	  }

	  /// Move assignment operator.
	  APInt &operator=(APInt &&that) {
	#ifdef _MSC_VER
	    // The MSVC std::shuffle implementation still does self-assignment.
	    if (this == &that)
	      return *this;
	#endif
	    assert(this != &that && "Self-move not supported");
	    if (!isSingleWord())
	      delete[] U.pVal;

	    // Use memcpy so that type based alias analysis sees both VAL and pVal
	    // as modified.
	    memcpy(&U, &that.U, sizeof(U));

	    BitWidth = that.BitWidth;
	    that.BitWidth = 0;

	    return *this;
	  }

	  /// Assignment operator.
	  ///
	  /// The RHS value is assigned to *this. If the significant bits in RHS exceed
	  /// the bit width, the excess bits are truncated. If the bit width is larger
	  /// than 64, the value is zero filled in the unspecified high order bits.
	  ///
	  /// \returns *this after assignment of RHS value.
	  APInt &operator=(uint64_t RHS) {
	    if (isSingleWord()) {
	      U.VAL = RHS;
	      clearUnusedBits();
	    } else {
	      U.pVal[0] = RHS;
	      memset(U.pVal+1, 0, (getNumWords() - 1) * APINT_WORD_SIZE);
	    }
	    return *this;
	  }

	  /// Bitwise AND assignment operator.
	  ///
	  /// Performs a bitwise AND operation on this APInt and RHS. The result is
	  /// assigned to *this.
	  ///
	  /// \returns *this after ANDing with RHS.
	  APInt &operator&=(APInt &RHS) {
	    assert(BitWidth == RHS.BitWidth && "Bit widths must be the same");
	    if (isSingleWord())
	      U.VAL &= RHS.U.VAL;
	    else
	      AndAssignSlowCase(RHS);
	    return *this;
	  }

	  /// Bitwise AND assignment operator.
	  ///
	  /// Performs a bitwise AND operation on this APInt and RHS. RHS is
	  /// logically zero-extended or truncated to match the bit-width of
	  /// the LHS.
	  APInt &operator&=(uint64_t RHS) {
	    if (isSingleWord()) {
	      U.VAL &= RHS;
	      return *this;
	    }
	    U.pVal[0] &= RHS;
	    memset(U.pVal+1, 0, (getNumWords() - 1) * APINT_WORD_SIZE);
	    return *this;
	  }

	  /// Bitwise OR assignment operator.
	  ///
	  /// Performs a bitwise OR operation on this APInt and RHS. The result is
	  /// assigned *this;
	  ///
	  /// \returns *this after ORing with RHS.
	  APInt &operator|=(APInt &RHS) {
	    assert(BitWidth == RHS.BitWidth && "Bit widths must be the same");
	    if (isSingleWord())
	      U.VAL |= RHS.U.VAL;
	    else
	      OrAssignSlowCase(RHS);
	    return *this;
	  }

	  /// Bitwise OR assignment operator.
	  ///
	  /// Performs a bitwise OR operation on this APInt and RHS. RHS is
	  /// logically zero-extended or truncated to match the bit-width of
	  /// the LHS.
	  APInt &operator|=(uint64_t RHS) {
	    if (isSingleWord()) {
	      U.VAL |= RHS;
	      clearUnusedBits();
	    } else {
	      U.pVal[0] |= RHS;
	    }
	    return *this;
	  }

	  /// Bitwise XOR assignment operator.
	  ///
	  /// Performs a bitwise XOR operation on this APInt and RHS. The result is
	  /// assigned to *this.
	  ///
	  /// \returns *this after XORing with RHS.
	  APInt &operator^=(APInt &RHS) {
	    assert(BitWidth == RHS.BitWidth && "Bit widths must be the same");
	    if (isSingleWord())
	      U.VAL ^= RHS.U.VAL;
	    else
	      XorAssignSlowCase(RHS);
	    return *this;
	  }

	  /// Bitwise XOR assignment operator.
	  ///
	  /// Performs a bitwise XOR operation on this APInt and RHS. RHS is
	  /// logically zero-extended or truncated to match the bit-width of
	  /// the LHS.
	  APInt &operator^=(uint64_t RHS) {
	    if (isSingleWord()) {
	      U.VAL ^= RHS;
	      clearUnusedBits();
	    } else {
	      U.pVal[0] ^= RHS;
	    }
	    return *this;
	  }

	  /// Multiplication assignment operator.
	  ///
	  /// Multiplies this APInt by RHS and assigns the result to *this.
	  ///
	  /// \returns *this
	  APInt &operator*=(APInt &RHS);
	  APInt &operator*=(uint64_t RHS);



	  /// Subtraction assignment operator.
	  ///
	  /// Subtracts RHS from *this and assigns the result to *this.
	  ///
	  /// \returns *this
	  APInt &operator-=(APInt &RHS);
	  APInt &operator-=(uint64_t RHS);

	  /// Left-shift assignment function.
	  ///
	  /// Shifts *this left by shiftAmt and assigns the result to *this.
	  ///
	  /// \returns *this after shifting left by ShiftAmt
	  APInt &operator<<=(unsigned ShiftAmt) {
	    assert(ShiftAmt <= BitWidth && "Invalid shift amount");
	    if (isSingleWord()) {
	      if (ShiftAmt == BitWidth)
	        U.VAL = 0;
	      else
	        U.VAL <<= ShiftAmt;
	      return clearUnusedBits();
	    }
	    shlSlowCase(ShiftAmt);
	    return *this;
	  }

	  /// Left-shift assignment function.
	  ///
	  /// Shifts *this left by shiftAmt and assigns the result to *this.
	  ///
	  /// \returns *this after shifting left by ShiftAmt
	  APInt &operator<<=(APInt &ShiftAmt);

	  /// @}
	  /// \name Binary Operators
	  /// @{

	  /// Multiplication operator.
	  ///
	  /// Multiplies this APInt by RHS and returns the result.
	  APInt operator*(APInt &RHS) const;

	  /// Left logical shift operator.
	  ///
	  /// Shifts this APInt left by \p Bits and returns the result.
	  APInt operator<<(unsigned Bits) const { return shl(Bits); }

	  /// Left logical shift operator.
	  ///
	  /// Shifts this APInt left by \p Bits and returns the result.
	  APInt operator<<(APInt &Bits) const { return shl(Bits); }

	  /// Logical right-shift function.
	  ///
	  /// Logical right-shift this APInt by shiftAmt.
	  APInt lshr(unsigned shiftAmt) const {
	    APInt R(*this);
	    R.lshrInPlace(shiftAmt);
	    return R;
	  }

	  /// Logical right-shift this APInt by ShiftAmt in place.
	  void lshrInPlace(unsigned ShiftAmt) {
	    assert(ShiftAmt <= BitWidth && "Invalid shift amount");
	    if (isSingleWord()) {
	      if (ShiftAmt == BitWidth)
	        U.VAL = 0;
	      else
	        U.VAL >>= ShiftAmt;
	      return;
	    }
	    lshrSlowCase(ShiftAmt);
	  }

	  /// Left-shift function.
	  ///
	  /// Left-shift this APInt by shiftAmt.
	  APInt shl(unsigned shiftAmt) const {
	    APInt R(*this);
	    R <<= shiftAmt;
	    return R;
	  }

	  /// Rotate left by rotateAmt.
	  APInt rotl(unsigned rotateAmt) const;

	  /// Rotate right by rotateAmt.
	  APInt rotr(unsigned rotateAmt) const;

	  /// Arithmetic right-shift function.
	  ///
	  /// Arithmetic right-shift this APInt by shiftAmt.
	  APInt ashr(APInt &ShiftAmt) const {
	    APInt R(*this);
	    R.ashrInPlace(ShiftAmt);
	    return R;
	  }

	  /// Arithmetic right-shift this APInt by shiftAmt in place.
	  void ashrInPlace(APInt &shiftAmt);

	  /// Logical right-shift function.
	  ///
	  /// Logical right-shift this APInt by shiftAmt.
	  APInt lshr(APInt &ShiftAmt) const {
	    APInt R(*this);
	    R.lshrInPlace(ShiftAmt);
	    return R;
	  }

	  /// Logical right-shift this APInt by ShiftAmt in place.
	  void lshrInPlace(APInt &ShiftAmt);

	  /// Left-shift function.
	  ///
	  /// Left-shift this APInt by shiftAmt.
	  APInt shl(APInt &ShiftAmt) const {
	    APInt R(*this);
	    R <<= ShiftAmt;
	    return R;
	  }

	  /// Rotate left by rotateAmt.
	  APInt rotl(APInt &rotateAmt) const;

	  /// Rotate right by rotateAmt.
	  APInt rotr(APInt &rotateAmt) const;

	  /// Unsigned division operation.
	  ///
	  /// Perform an unsigned divide operation on this APInt by RHS. Both this and
	  /// RHS are treated as unsigned quantities for purposes of this division.
	  ///
	  /// \returns a new APInt value containing the division result, rounded towards
	  /// zero.
	  APInt udiv(APInt &RHS) const;
	  APInt udiv(uint64_t RHS) const;

	  /// Signed division function for APInt.
	  ///
	  /// Signed divide this APInt by APInt RHS.
	  ///
	  /// The result is rounded towards zero.
	  APInt sdiv(APInt &RHS) const;
	  APInt sdiv(int64_t RHS) const;

	  /// Unsigned remainder operation.
	  ///
	  /// Perform an unsigned remainder operation on this APInt with RHS being the
	  /// divisor. Both this and RHS are treated as unsigned quantities for purposes
	  /// of this operation. Note that this is a true remainder operation and not a
	  /// modulo operation because the sign follows the sign of the dividend which
	  /// is *this.
	  ///
	  /// \returns a new APInt value containing the remainder result
	  APInt urem(APInt &RHS) const;
	  uint64_t urem(uint64_t RHS) const;

	  /// Function for signed remainder operation.
	  ///
	  /// Signed remainder operation on APInt.
	  APInt srem(APInt &RHS) const;
	  int64_t srem(int64_t RHS) const;

	  /// Dual division/remainder interface.
	  ///
	  /// Sometimes it is convenient to divide two APInt values and obtain both the
	  /// quotient and remainder. This function does both operations in the same
	  /// computation making it a little more efficient. The pair of input arguments
	  /// may overlap with the pair of output arguments. It is safe to call
	  /// udivrem(X, Y, X, Y), for example.
	  static void udivrem(APInt &LHS, const APInt &RHS, APInt &Quotient,
	                      APInt &Remainder);
	  static void udivrem(APInt &LHS, uint64_t RHS, APInt &Quotient,
	                      uint64_t &Remainder);

	  static void sdivrem(APInt &LHS, const APInt &RHS, APInt &Quotient,
	                      APInt &Remainder);
	  static void sdivrem(APInt &LHS, int64_t RHS, APInt &Quotient,
	                      int64_t &Remainder);

	  // Operations that return overflow indicators.
	  APInt sadd_ov(APInt &RHS, bool &Overflow) const;
	  APInt uadd_ov(APInt &RHS, bool &Overflow) const;
	  APInt ssub_ov(APInt &RHS, bool &Overflow) const;
	  APInt usub_ov(APInt &RHS, bool &Overflow) const;
	  APInt sdiv_ov(APInt &RHS, bool &Overflow) const;
	  APInt smul_ov(APInt &RHS, bool &Overflow) const;
	  APInt umul_ov(APInt &RHS, bool &Overflow) const;
	  APInt sshl_ov(APInt &Amt, bool &Overflow) const;
	  APInt ushl_ov(APInt &Amt, bool &Overflow) const;

	  // Operations that saturate
	  APInt sadd_sat(APInt &RHS) const;
	  APInt uadd_sat(APInt &RHS) const;
	  APInt ssub_sat(APInt &RHS) const;
	  APInt usub_sat(APInt &RHS) const;
	  APInt smul_sat(APInt &RHS) const;
	  APInt umul_sat(APInt &RHS) const;
	  APInt sshl_sat(APInt &RHS) const;
	  APInt ushl_sat(APInt &RHS) const;

	  /// Array-indexing support.
	  ///
	  /// \returns the bit value at bitPosition
	  bool operator[](unsigned bitPosition) const {
	    assert(bitPosition < getBitWidth() && "Bit position out of bounds!");
	    return (maskBit(bitPosition) & getWord(bitPosition)) != 0;
	  }


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

	  /// Zero extend or truncate to width
	  ///
	  /// Make this APInt have the bit width given by \p width. The value is zero
	  /// extended, or left alone to make it that width.
	  APInt zextOrSelf(unsigned width) const;

	  /// @}
	  /// \name Bit Manipulation Operators
	  /// @{

	  /// Set every bit to 1.
	  void setAllBits() {
	    if (isSingleWord())
	      U.VAL = WORDTYPE_MAX;
	    else
	      // Set all the bits in all the words.
	      memset(U.pVal, -1, getNumWords() * APINT_WORD_SIZE);
	    // Clear the unused ones
	    clearUnusedBits();
	  }



	  /// Set the bits from loBit (inclusive) to hiBit (exclusive) to 1.
	  /// This function handles "wrap" case when \p loBit > \p hiBit, and calls
	  /// setBits when \p loBit <= \p hiBit.
	  void setBitsWithWrap(unsigned loBit, unsigned hiBit) {
	    assert(hiBit <= BitWidth && "hiBit out of range");
	    assert(loBit <= BitWidth && "loBit out of range");
	    if (loBit <= hiBit) {
	      setBits(loBit, hiBit);
	      return;
	    }
	    setLowBits(hiBit);
	    setHighBits(BitWidth - loBit);
	  }

	  /// Set the bits from loBit (inclusive) to hiBit (exclusive) to 1.
	  /// This function handles case when \p loBit <= \p hiBit.
	  void setBits(unsigned loBit, unsigned hiBit) {
	    assert(hiBit <= BitWidth && "hiBit out of range");
	    assert(loBit <= BitWidth && "loBit out of range");
	    assert(loBit <= hiBit && "loBit greater than hiBit");
	    if (loBit == hiBit)
	      return;
	    if (loBit < APINT_BITS_PER_WORD && hiBit <= APINT_BITS_PER_WORD) {
	      uint64_t mask = WORDTYPE_MAX >> (APINT_BITS_PER_WORD - (hiBit - loBit));
	      mask <<= loBit;
	      if (isSingleWord())
	        U.VAL |= mask;
	      else
	        U.pVal[0] |= mask;
	    } else {
	      setBitsSlowCase(loBit, hiBit);
	    }
	  }

	  /// Set the top bits starting from loBit.
	  void setBitsFrom(unsigned loBit) {
	    return setBits(loBit, BitWidth);
	  }

	  /// Set the bottom loBits bits.
	  void setLowBits(unsigned loBits) {
	    return setBits(0, loBits);
	  }

	  /// Set the top hiBits bits.
	  void setHighBits(unsigned hiBits) {
	    return setBits(BitWidth - hiBits, BitWidth);
	  }

	  /// Set every bit to 0.
	  void clearAllBits() {
	    if (isSingleWord())
	      U.VAL = 0;
	    else
	      memset(U.pVal, 0, getNumWords() * APINT_WORD_SIZE);
	  }

	  /// Set bottom loBits bits to 0.
	  void clearLowBits(unsigned loBits) {
	    assert(loBits <= BitWidth && "More bits than bitwidth");
	    APInt Keep = getHighBitsSet(BitWidth, BitWidth - loBits);
	 *this &= Keep;
	  }

	  /// Set the sign bit to 0.
	  void clearSignBit() {
	    clearBit(BitWidth - 1);
	  }

	  /// Toggles a given bit to its opposite value.
	  ///
	  /// Toggle a given bit to its opposite value whose position is given
	  /// as "bitPosition".
	  void flipBit(unsigned bitPosition);

	  /// Insert the bits from a smaller APInt starting at bitPosition.
	  void insertBits(APInt &SubBits, unsigned bitPosition);
	  void insertBits(uint64_t SubBits, unsigned bitPosition, unsigned numBits);

	  /// Return an APInt with the extracted bits [bitPosition,bitPosition+numBits).
	  APInt extractBits(unsigned numBits, unsigned bitPosition) const;
	  uint64_t extractBitsAsZExtValue(unsigned numBits, unsigned bitPosition) const;

	  /// @}
	  /// \name Value Characterization Functions
	  /// @{

	  /// Return the number of bits in the APInt.
	  unsigned getBitWidth() const { return BitWidth; }

	  /// Get the number of words.
	  ///
	  /// Here one word's bitwidth equals to that of uint64_t.
	  ///
	  /// \returns the number of words to hold the integer value of this APInt.
	  unsigned getNumWords() const { return getNumWords(BitWidth); }

	  /// Get the number of words.
	  ///
	  /// *NOTE* Here one word's bitwidth equals to that of uint64_t.
	  ///
	  /// \returns the number of words to hold the integer value with a given bit
	  /// width.
	  static unsigned getNumWords(unsigned BitWidth) {
	    return ((uint64_t)BitWidth + APINT_BITS_PER_WORD - 1) / APINT_BITS_PER_WORD;
	  }

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

	  /// Count the number of trailing zero bits.
	  ///
	  /// This function is an APInt version of the countTrailingZeros
	  /// functions in MathExtras.h. It counts the number of zeros from the least
	  /// significant bit to the first set bit.
	  ///
	  /// \returns BitWidth if the value is zero, otherwise returns the number of
	  /// zeros from the least significant bit to the first one bit.
	  unsigned countTrailingZeros() const {
	    if (isSingleWord())
	      return std::min(unsigned(llvm::countTrailingZeros(U.VAL)), BitWidth);
	    return countTrailingZerosSlowCase();
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

	  /// DST += RHS + CARRY where CARRY is zero or one.  Returns the carry flag.
	  static WordType tcAdd(WordType *, const WordType *,
	                        WordType carry, unsigned);
	  /// DST += RHS.  Returns the carry flag.
	  static WordType tcAddPart(WordType *, WordType, unsigned);

	  /// DST -= RHS + CARRY where CARRY is zero or one. Returns the carry flag.
	  static WordType tcSubtract(WordType *, const WordType *,
	                             WordType carry, unsigned);
	  /// DST -= RHS.  Returns the carry flag.
	  static WordType tcSubtractPart(WordType *, WordType, unsigned);

	  /// DST += SRC * MULTIPLIER + PART   if add is true
	  /// DST  = SRC * MULTIPLIER + PART   if add is false
	  ///
	  /// Requires 0 <= DSTPARTS <= SRCPARTS + 1.  If DST overlaps SRC they must
	  /// start at the same point, i.e. DST == SRC.
	  ///
	  /// If DSTPARTS == SRC_PARTS + 1 no overflow occurs and zero is returned.
	  /// Otherwise DST is filled with the least significant DSTPARTS parts of the
	  /// result, and if all of the omitted higher parts were zero return zero,
	  /// otherwise overflow occurred and return one.
	  static int tcMultiplyPart(WordType *dst, const WordType *src,
	                            WordType multiplier, WordType carry,
	                            unsigned srcParts, unsigned dstParts,
	                            bool add);

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

	  /// The obvious AND, OR and XOR and complement operations.
	  static void tcAnd(WordType *, const WordType *, unsigned);
	  static void tcOr(WordType *, const WordType *, unsigned);
	  static void tcXor(WordType *, const WordType *, unsigned);
	  static void tcComplement(WordType *, unsigned);

	  /// Comparison (unsigned) of two bignums.
	  static int tcCompare(WordType *, const WordType *, unsigned);

	  /// Increment a bignum in-place.  Return the carry flag.
	  static WordType tcIncrement(WordType *dst, unsigned parts) {
	    return tcAddPart(dst, 1, parts);
	  }

	  /// Decrement a bignum in-place.  Return the borrow flag.
	  static WordType tcDecrement(WordType *dst, unsigned parts) {
	    return tcSubtractPart(dst, 1, parts);
	  }

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

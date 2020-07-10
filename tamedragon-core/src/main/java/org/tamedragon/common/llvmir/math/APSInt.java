package org.tamedragon.common.llvmir.math;

import java.math.BigDecimal;

//===-- llvm/ADT/APSInt.h - Arbitrary Precision Signed Int -----*- C++ -*--===//
//
//Part of the LLVM Project, under the Apache License v2.0 with LLVM Exceptions.
//See https://llvm.org/LICENSE.txt for license information.
//SPDX-License-Identifier: Apache-2.0 WITH LLVM-exception
//
//===----------------------------------------------------------------------===//
//
//This file implements the APSInt class, which is a simple class that
//represents an arbitrary sized integer that knows its signedness.
//
//===----------------------------------------------------------------------===//



public class APSInt extends APInt {
	private boolean IsUnsigned;

	// Default constructor that creates an uninitialized APInt.
	public APSInt(){
		super();
		IsUnsigned = false;
	}

	//* APSInt ctor - Create an APSInt with the specified width, default to unsigned.
	//public APSInt(int BitWidth, boolean isUnsigned = true) {
	public APSInt(int numBits, boolean isUnsigned) {
		setNumBits(numBits);
		setUnsignedVals(new ULong[] {ULong.valueOf("0")});
		this.IsUnsigned = isUnsigned;

	}

	//public APSInt(APInt otherAPInt, boolean isUnsigned = true) {
	public APSInt(APInt otherAPInt, boolean isUnsigned) {
		setAPSInt(otherAPInt, isUnsigned);
	}

	private void setAPSInt(APInt apInt, boolean isUnsigned) {
		setNumBits(apInt.getNumBits());
		setUnsignedVals(apInt.getUnsignedVals());
		this.IsUnsigned = isUnsigned;
	}
	
	public APSInt clone() {
		APInt newAPInt = new APInt(numBits, unsignedVals, isSigned());
		return new APSInt(newAPInt, IsUnsigned);
	}

	/* Construct an APSInt from a string representation.
	 *
	 * This constructor interprets the string str using the radix of 10.
	 * The interpretation stops at the end of the string. The bit width of the
	 * constructed APSInt is determined automatically.
	 *
	 * @param str the string to be interpreted.
	 */
	public APSInt(String str) {
		if(str == null || str.length() == 0){
			throw new IllegalArgumentException("Invalid string length");
		}

		// (Over-)estimate the required number of bits.
		int numBits = ((str.length() * 64) / 19) + 2;

		APInt temp = new APInt(numBits, str, 10);
		if (str.charAt(0) == '-') {
			int minBits = temp.getMinSignedBits();
			if (minBits > 0 && minBits < numBits) {
				temp = temp.trunc(minBits);
			}
			setAPSInt(temp, /*isUnsigned=*/false);
			return;
		}

		int activeBits = temp.getActiveBits();
		if (activeBits > 0 && activeBits < numBits) {
			temp = temp.trunc(activeBits);
		}

		setAPSInt(temp, /*isUnsigned=*/true);
	}

	/* Determine sign of this APSInt.
	 *
	 * \returns true if this APSInt is negative, false otherwise
	 */
	public boolean isNegative(){ return isSigned() && isNegative(); }

	/* Determine if this APSInt Value is non-negative (>= 0)
	 *
	 * \returns true if this APSInt is non-negative, false otherwise
	 */
	public boolean isNonNegative(){ return !isNegative(); }

	/* Determine if this APSInt Value is positive.
	 *
	 * This tests if the value of this APSInt is positive (> 0). Note
	 * that 0 is not a positive value.
	 *
	 * \returns true if this APSInt is positive.
	 */
	public boolean isStrictlyPositive(){ return isNonNegative() && !isNullValue(); }

	// Query sign information.
	public boolean isSigned(){ return !IsUnsigned; }
	public boolean isUnsigned(){ return IsUnsigned; }
	public void setIsUnsigned(boolean Val) { IsUnsigned = Val; }
	public void setIsSigned(boolean Val) { IsUnsigned = !Val; }


	/* toString - Converts an APInt to a String */
	public String toString(int Radix){
		return toString(Radix, true, false);
	}

	/* Get the correctly-extended long value.
	 */
	public long getExtValue(){
		if(getMinSignedBits() <= 64) {
			throw new IllegalArgumentException("Too many bits for long");
		}
		return isSigned() ? getSExtValue() : getZExtValueNew().longValue();
	}

	public APSInt trunc(int width){
		return new APSInt(super.trunc(width), IsUnsigned);
	}

	public APSInt extend(int width){
		if (IsUnsigned) {
			return new APSInt(zext(width), IsUnsigned);
		}
		else {
			return new APSInt(sext(width), IsUnsigned);
		}
	}

	public APSInt extOrTrunc(int width){
		if (IsUnsigned) {
			return new APSInt(zextOrTrunc(width), IsUnsigned);
		}
		else {
			return new APSInt(sextOrTrunc(width), IsUnsigned);
		}
	}

	public APSInt modulo(APSInt RHS){
		if(IsUnsigned == RHS.IsUnsigned) {
			throw new IllegalArgumentException("Signedness mismatch!");
		}
		return IsUnsigned ? new APSInt(urem(RHS), true) : new APSInt(srem(RHS), false);
	}

	public APSInt div(APSInt RHS){
		if(IsUnsigned == RHS.IsUnsigned) {
			throw new IllegalArgumentException("Signedness mismatch!");
		}
		return IsUnsigned ? new APSInt(udiv(RHS), true) : new APSInt(sdiv(RHS), false);
	}

	public APSInt lshr(int Amt){
		return IsUnsigned ? new APSInt(lshr(Amt), true) : new APSInt(ashr(ULong.valueOf(Amt)), false);
	}

	public boolean ult(APSInt RHS){
		if(IsUnsigned == RHS.IsUnsigned) {
			throw new IllegalArgumentException("Signedness mismatch!");
		}
		return IsUnsigned ? ult(RHS) : slt(RHS);
	}

	public boolean ugt(APSInt RHS){
		if(IsUnsigned == RHS.IsUnsigned) {
			throw new IllegalArgumentException("Signedness mismatch!");
		}
		return IsUnsigned ? ugt(RHS) : sgt(RHS);
	}

	public boolean ule(APSInt RHS){
		if(IsUnsigned == RHS.IsUnsigned) {
			throw new IllegalArgumentException("Signedness mismatch!");
		}
		return IsUnsigned ? ule(RHS) : sle(RHS);
	}

	public boolean uge(APSInt RHS){
		if(IsUnsigned == RHS.IsUnsigned) {
			throw new IllegalArgumentException("Signedness mismatch!");
		}
		return IsUnsigned ? uge(RHS) : sge(RHS);
	}
	
	public boolean equals(APInt RHS){
		 if(numBits != RHS.getNumBits()) {
			 return false;
		 }
		 if (isSingleWord()) {
		      return unsignedVals[0].equals(RHS.getUnsignedVals()[0]);
		 }
		 else {
			 // TODO Implement this
			 //return EqualSlowCase(RHS);
			 return false;
		 }
	}

	public String toString() {
		return toString(10);
	}
	
	public boolean neq(APSInt RHS){
		return !equals(RHS);
	}

	public boolean equals(long RHS){
		return compareValues(this, get(RHS)) == 0;
	}

	public boolean neq(long RHS){
		return compareValues(this, get(RHS)) != 0;
	}

	public boolean lte(long RHS){
		return compareValues(this, get(RHS)) <= 0;
	}

	public boolean gte(long RHS){
		return compareValues(this, get(RHS)) >= 0;
	}

	public boolean lt(long RHS){
		return compareValues(this, get(RHS)) < 0;
	}

	public boolean gt(long RHS){
		return compareValues(this, get(RHS)) > 0;
	}

	// The remaining operators just wrap the logic of APInt, but retain the
	// signedness information.

	public APSInt lsh(int Bits){
		return new APSInt(((APInt)(this)).shiftLeft(Bits), IsUnsigned);
	}

	public APSInt incr() {
		APInt apInt = add(ULong.valueOf(1));
		return new APSInt(apInt, IsUnsigned);
	}

	public APSInt decr() {
		APInt apInt = subtract(ULong.valueOf(1));
		return new APSInt(apInt, IsUnsigned);
	}

	public APSInt add(APSInt RHS) {
		if(IsUnsigned != RHS.IsUnsigned) {
			throw new IllegalArgumentException("Signedness mismatch!");
		}
		
		APInt apInt = add(RHS);
		return new APSInt(apInt, IsUnsigned);
	}

	public APSInt subtract(APSInt RHS) {
		if(IsUnsigned != RHS.IsUnsigned) {
			throw new IllegalArgumentException("Signedness mismatch!");
		}
		
		APInt apInt = subtract(RHS);
		return new APSInt(apInt, IsUnsigned);
	}

	public APSInt mul(APSInt RHS) {
		if(IsUnsigned != RHS.IsUnsigned) {
			throw new IllegalArgumentException("Signedness mismatch!");
		}
		
		APInt apInt = mul(RHS);
		return new APSInt(apInt, IsUnsigned);
	}

	public APSInt xor(APSInt RHS) {
		/*if(IsUnsigned == RHS.IsUnsigned) {
			throw new IllegalArgumentException("Signedness mismatch!");
		}
		APInt temp = new APSInt((APInt)this).xorWith(RHS), false);

		return temp;
		*/
		return null;
	}

	public APSInt and(APSInt RHS){
		if(IsUnsigned != RHS.IsUnsigned) {
			throw new IllegalArgumentException("Signedness mismatch!");
		}
		return new APSInt(((APInt)(this)).andWith(RHS), IsUnsigned);
	}

	public APSInt or(APSInt RHS){
		if(IsUnsigned == RHS.IsUnsigned) {
			throw new IllegalArgumentException("Signedness mismatch!");
		}
		return new APSInt(((APInt)(this)).orWith(RHS), IsUnsigned);
	}

	/*public APSInt not(){
		return new APSInt(((APInt)(this)).not(), IsUnsigned);
	}*/

	/* getMaxValue - Return the APSInt representing the maximum integer value
	 *  with the given bit width and signedness.
	 */
	public static APSInt getMaxValue(int numBits, boolean Unsigned) {
		return new APSInt(Unsigned ? getMaxValue(numBits) : APInt.getSignedMaxValue(numBits), Unsigned);
	}

	/* getMinValue - Return the APSInt representing the minimum integer value
	 *  with the given bit width and signedness.
	 */
	public static APSInt getMinValue(int numBits, boolean Unsigned) {
		return new APSInt(Unsigned ? getMinValue(numBits) : getSignedMinValue(numBits), Unsigned);
	}

	/* Determine if two APSInts have the same value, zero- or
	 * sign-extending as needed.
	 */
	public static boolean isSameValue(APSInt I1,APSInt I2) {
		return compareValues(I1, I2) > 0;
	}

	/* Compare underlying values of two numbers.
	 */
	public static int compareValues(APSInt I1,APSInt I2) {
		if (I1.getNumBits() == I2.getNumBits() && I1.isSigned() == I2.isSigned())
			return I1.IsUnsigned ? I1.compare(I2) : I1.compareSigned(I2);

			// Check for a bit-width mismatch.
			if (I1.getNumBits() > I2.getNumBits())
				return compareValues(I1, I2.extend(I1.getNumBits()));
			if (I2.getNumBits() > I1.getNumBits())
				return compareValues(I1.extend(I2.getNumBits()), I2);

			// We have a signedness mismatch. Check for negative values and do an
			// int compare if both are positive.
			if (I1.isSigned()) {
				if(!I2.isSigned()) {
					throw new IllegalArgumentException("Expected signed mismatch");
				}
				if (I1.isNegative())
					return -1;
			} else {
				if(I2.isSigned()) {
					throw new IllegalArgumentException("Expected signed mismatch");
				}
				if (I2.isNegative())
					return 1;
			}

			return I1.compare(I2);
	}

	public static APSInt get(long X) { return new APSInt(new APInt(64, new ULong[] {ULong.valueOf(X)}, false), false); }
	public static APSInt getUnsigned(ULong X) { return new APSInt(new APInt(64, new ULong[] {X}, false), true); }
}


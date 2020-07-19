package org.tamedragon.common.llvmir.math;

import java.math.BigInteger;

public class APIntUtils {

	//********************************************************* Value Generators *****************************************
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
		return new APInt(numBits, new ULong[] { ULong.valueOf(APInt.WORDTYPE_MAX)}, true);
	}	

	/*
	 * Returns the '0' value for an APInt of the specified bit-width.
	 */
	public static APInt getNullValue(int numBits) {
		return new APInt(numBits, ULong.valueOf("0"), false);
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
	
	//********************************************************* Building-block Operations for APInt and APFloat *****************************************

	// These building block operations operate on a representation of arbitrary
	// precision, two's-complement, bignum integer values. They should be
	// sufficient to implement APInt and APFloat bignum requirements. Inputs are
	// generally a pointer to the base of an array of integer parts, representing
	// an int bignum, and a count of how many parts there are.

	/* Sets the least significant part of a bignum to the input value, and zeroes
	 * out higher parts.
	 */
	public static void tcSet(ULong[] dst, ULong part, int parts) {

	}

	/* 
	 *  Assign one bignum to another.
	 */
	public static void tcAssign(ULong[] dst, ULong[] src, int parts) {

	}

	/* 
	 * Returns true if a bignum is zero, false otherwise.
	 */
	public static boolean tcIsZero(ULong[] src, int parts) {
		// TODO Implement this
		return false;
	}

	/*
	 * Extract the given bit of a bignum; returns 0 or 1.  Zero-based.
	 */
	public static int tcExtractBit(ULong[] parts, int bit) {
		// TODO Implement this
		return -1;
	}

	/* Copy the bit vector of width srcBITS from SRC, starting at bit srcLSB, to
	 * DST, of dstCOUNT parts, such that the bit srcLSB becomes the least
	 * significant bit of DST.  All high bits above srcBITS in DST are
	 * zero-filled.
	 */
	public static void tcExtract(ULong[] dst, int dstCount, ULong[] src, int srcBits,
			int srcLSB) {

	}

	/* 
	 * Set the given bit of a bignum.  Zero-based.
	 */
	public static void tcSetBit(ULong[] parts, int bit) {

	}

	/*
	 * Clear the given bit of a bignum.  Zero-based.
	 */
	public static void tcClearBit(ULong[] parts, int bit) {

	}

	/* 
	 * Returns the bit number of the least or most significant set bit of a
	 * number.  If the input number has no bits set -1U is returned.
	 */
	public static int tcLSB(ULong[] parts, int n) {
		// TODO Implement this
		return -1;
	}

	public static int tcMSB(ULong[] parts, int n) {
		// TODO Implement this
		return -1;
	}

	/* 
	 * Negate a bignum in-place.
	 */
	public static void tcNegate(ULong[] dst, int parts) {

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
				mid = mid.leftShift(APInt.AP_INT_BITS_PER_WORD / 2);
				if (low.add(mid).isLesserThan(low)) {
					high = high.add(ULong.valueOf(1));
				}
				low = low.add(mid);

				mid = highHalf(srcPart).mul(lowHalf(multiplier));
				high = high.add(highHalf(mid));
				mid = mid.leftShift(APInt.AP_INT_BITS_PER_WORD / 2);
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




	/* DST = LHS * RHS, where DST has the same width as the operands and is
	 * filled with the least significant parts of the result.  Returns one if
	 * overflow occurred, otherwise zero.  DST must be disjoint from both
	 * operands.
	 */

	public static int tcMultiply(ULong[] dst, ULong[] lhs, ULong[] rhs, int parts) {
		// TODO Implement this
		return -1;
	}

	/*
	 * * DST = LHS * RHS, where DST has width the sum of the widths of the
	 *
		  operands. No overflow occurs. DST must be disjoint from both operands.
	 */
	public static void tcFullMultiply(ULong[] dst, ULong[] lhs,
			ULong[] rhs, int lhsParts, int rhsParts) {

	}

	/* If RHS is zero LHS and REMAINDER are left unchanged, return one.
	 * Otherwise set LHS to LHS / RHS with the fractional part discarded, set
	 * REMAINDER to the remainder, return zero.  i.e.
	 *
	 *  OLD_LHS = RHS * LHS + REMAINDER
	 *
	 * SCRATCH is a bignum of the same size as the operands and result for use by
	 * the routine; its contents need not be initialized and are destroyed.  LHS,
	 * REMAINDER and SCRATCH must be distinct.
	 */
	public static int tcDivide(ULong[]lhs, ULong[]rhs,
			ULong[]remainder, ULong[]scratch,
			int parts) {
		// TODO Implement this
		return -1;
	}

	/* Shift a bignum left Count bits. Shifted in bits are zero. There are no
	 * restrictions on Count.
	 */

	public static void tcShiftLeft(ULong[] Dst, int Words, int Count) {

		if (Count <= 0) { // Don't bother performing a no-op shift.
			return;
		}

		// WordShift is the inter-part shift; BitShift is the intra-part shift.
		int WordShift = Count / APInt.AP_INT_BITS_PER_WORD < Words ? Count / APInt.AP_INT_BITS_PER_WORD : Words;
		int BitShift = Count % APInt.AP_INT_BITS_PER_WORD;

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
					Dst[Words] = Dst[Words].or(Dst[Words - WordShift - 1].rightShift((APInt.AP_INT_BITS_PER_WORD - BitShift)));
			}
		}

		// Fill in the remainder with 0s.
		for(int i = 0; i < WordShift; i++) {
			Dst[i] = ULong.valueOf(0);
		}
	}

	/* Shift a bignum right Count bits.  Shifted in bits are zero.  There are no
	 * restrictions on Count.
	 */
	public static void tcShiftRight(ULong[] Dst, int Words, int Count) {
		// Don't bother performing a no-op shift.
		if (Count <= 0)
			return;

		// WordShift is the inter-part shift; BitShift is the intra-part shift.
		int WordShift = Count / APInt.AP_INT_BITS_PER_WORD < Words ? Count / APInt.AP_INT_BITS_PER_WORD : Count;
		int BitShift = Count % APInt.AP_INT_BITS_PER_WORD;

		int WordsToMove = Words - WordShift;
		// Fastpath for moving by whole words.
		if (BitShift == 0) {
			// TODO Implement this
			// std::memmove(Dst, Dst + WordShift, WordsToMove * APINT_WORD_SIZE);
		} else {
			for (int i = 0; i != WordsToMove; ++i) {
				Dst[i] = Dst[i + WordShift].rightShift(BitShift);
				if (i + 1 != WordsToMove)
					Dst[i] = Dst[i].or(Dst[i + WordShift + 1].leftShift(APInt.AP_INT_BITS_PER_WORD - BitShift));
			}
		}

		// Fill in the remainder with 0s.
		// TODO Implement this
		//std::memset(Dst + WordsToMove, 0, WordShift * APINT_WORD_SIZE);
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

	/* 
	 * Comparison (int) of two bignums.
	 */
	public static int tcCompare(ULong[] lhs, ULong[] rhs, int parts) {
		while (parts > 0) {
			parts--;
			if (!lhs[parts].equals(rhs[parts]))
				return (lhs[parts].isGreaterThan(rhs[parts])) ? 1 : -1;
		}

		return 0;
	}

	protected static void tcComplement(ULong[] dst, int parts) {
		for (int i = 0; i < parts; i++) {
			dst[i] = dst[i].complement();
		}
	}

	/*
	 *  Increment a bignum in-place.  Return the carry flag.
	 */
	public static ULong tcIncrement(ULong[] dst, int parts) {
		return tcAddPart(dst, ULong.valueOf(1), parts);
	}

	/*
	 *  Decrement a bignum in-place.  Return the borrow flag.
	 */
	public static ULong tcDecrement(ULong[]  dst, int parts) {
		return tcSubtractPart(dst, ULong.valueOf(1), parts);
	}

	/*
	 * Set the least significant BITS and clear the rest.
	 */
	public static void tcSetLeastSignificantBits(ULong[] dst, int part, int bits) {

	}

	/* Returns the value of the lower half of PART.  */
	private static ULong lowHalf(ULong part) {
		return part.and(lowBitMask(APInt.AP_INT_BITS_PER_WORD / 2));
	}

	/* Returns the value of the upper half of PART.  */
	private static ULong highHalf(ULong part) {
		return part.rightShift(APInt.AP_INT_BITS_PER_WORD / 2);
	}

	/* Returns the integer part with the least significant BITS set.
	   BITS cannot be zero.  */
	private static ULong lowBitMask(int bits) {
		if(bits == 0 || bits > APInt.AP_INT_BITS_PER_WORD) {
			throw new IllegalArgumentException("Invalid number of bits");
		}
		return ULong.valueOf(0).complement().rightShift(APInt.AP_INT_BITS_PER_WORD - bits);
	}
	
	/* Get the number of words.
	 * *NOTE* Here one word's bitwidth equals to that of ULong.
	 * Returns the number of words to hold the integer value with a given bit width.
	 */
	public static int getNumWords(int bitwidth) {
		ULong numerator = ULong.valueOf(bitwidth).add(APInt.AP_INT_BITS_PER_WORD -1);
		ULong ulongVal = numerator.div(APInt.AP_INT_BITS_PER_WORD);
		return ulongVal.intValue();
	}
	
	/* Get bits required for string value.
	 * This method determines how many bits are required to hold the APInt
	 * equivalent of the string given by str.
	 */
	public static int getBitsNeeded(String str, int radix) {
		// TODO Implement this.
		
		 /* assert(!str.empty() && "Invalid string length");
		  assert(
		      (radix == 10 || radix == 8 || radix == 16 || radix == 2 || radix == 36) &&
		      "Radix should be 2, 8, 10, 16, or 36!");

		  size_t slen = str.size();

		  // Each computation below needs to know if it's negative.
		  StringRef::iterator p = str.begin();
		  unsigned isNegative = *p == '-';
		  if (*p == '-' || *p == '+') {
		    p++;
		    slen--;
		    assert(slen && "String is only a sign, needs a value.");
		  }

		  // For radixes of power-of-two values, the bits required is accurately and
		  // easily computed
		  if (radix == 2)
		    return slen + isNegative;
		  if (radix == 8)
		    return slen * 3 + isNegative;
		  if (radix == 16)
		    return slen * 4 + isNegative;

		  // FIXME: base 36

		  // This is grossly inefficient but accurate. We could probably do something
		  // with a computation of roughly slen*64/20 and then adjust by the value of
		  // the first few digits. But, I'm not sure how accurate that could be.

		  // Compute a sufficient number of bits that is always large enough but might
		  // be too large. This avoids the assertion in the constructor. This
		  // calculation doesn't work appropriately for the numbers 0-9, so just use 4
		  // bits in that case.
		  unsigned sufficient = radix == 10 ? (slen == 1 ? 4 : slen * 64 / 18)
		                                    : (slen == 1 ? 7 : slen * 16 / 3);

		  // Convert to the actual binary value.
		  APInt tmp(sufficient, StringRef(p, slen), radix);

		  // Compute how many bits are required. If the log is infinite, assume we need
		  // just bit. If the log is exact and value is negative, then the value is
		  // MinSignedValue with (log + 1) bits.
		  unsigned log = tmp.logBase2();
		  if (log == (unsigned)-1) {
		    return isNegative + 1;
		  } else if (isNegative && tmp.isPowerOf2()) {
		    return isNegative + log;
		  } else {
		    return isNegative + log + 1;
		  }
		  */
		
		return -1;
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
		setWordValues(qrp.getQuotient().unsignedVals, 0, lhsWords, qrp.getQuotient().unsignedVals.length);
		setWordValues(qrp.getRemainderApInt().unsignedVals, 0, lhsWords, qrp.getQuotient().unsignedVals.length);

		return qrp;
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
			ULong Remainder = LHS.getZExtValue();      // X % Y ===> X, iff X < Y
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
		QuotientRemainderPair qrPair = APIntUtils.divideWithBigInts(LHS.getUnsignedVals(), lhsWords, rhsVals, 1);

		// Clear the rest of the Quotient.
		APIntUtils.setWordValues(qrPair.getQuotient().unsignedVals, 0, lhsWords, qrp.getQuotient().unsignedVals.length);
		return qrPair;
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

	public static QuotientRemainderPair sdivrem(APInt LHS, APInt RHS) {

		QuotientRemainderPair qr = null;

		if (LHS.isNegative()) {
			if (RHS.isNegative())
				qr = APIntUtils.udivrem(LHS.mul(ULong.valueOf(-1)), RHS.mul(ULong.valueOf(-1)));
			else {
				qr = APIntUtils.udivrem(LHS.mul(ULong.valueOf(-1)), RHS);
				qr.getQuotient().negate();
			}

			qr.getRemainderApInt().negate();
		} 
		else if (RHS.isNegative()) {
			qr = APIntUtils.udivrem(LHS, RHS.mul(ULong.valueOf(-1)));
			qr.getQuotient().negate();
		} 
		else {
			qr = APIntUtils.udivrem(LHS, RHS);
		}

		return qr;
	}

	protected static void setWordValues(ULong vals[], long value, int offSet, int length) {
		for(int i = offSet; i < vals.length; i++) {
			vals[i] = ULong.valueOf(value);
			if(i > length) {
				break;
			}
		}
	}
	
	public static QuotientRemainderPair divideWithBigInts(ULong[] LHS, int lhsWords, ULong[] RHS, int rhsWords) {
		BigInteger biLHS = getBigInteger(LHS, lhsWords);
		BigInteger biRHS = getBigInteger(RHS, rhsWords);

		BigInteger quotientBI = biLHS.divide(biRHS);
		BigInteger remainderBI = biLHS.remainder(biRHS);

		if(quotientBI.toString().equals("1")) {
			System.out.println("WAIT HERE");
		}

		APInt resultAPI = new APInt(lhsWords * APInt.AP_INT_BITS_PER_WORD, quotientBI.toString(), 10);
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
	
	/* Converts a double to APInt bits.
	 * The conversion does not do a translation from double to integer, it just
	 * re-interprets the bits of the double.
	 */
	public static APInt doubleToBits(double V) {
		return new APInt(64, MathUtils.DoubleToBits(V), false);
	}

	/* Converts a float to APInt bits.
	 *
	 * The conversion does not do a translation from float to integer, it just
	 * re-interprets the bits of the float.
	 */
	public static APInt floatToBits(float V) {
		return new APInt(32, MathUtils.FloatToBits(V), false);
	}

	/* Determine which word a bit is in.
	 * Returns the word position for the specified bit position.
	 */
	public static int whichWord(int bitPosition) {
		return bitPosition / APInt.AP_INT_BITS_PER_WORD;
	}
	
	/* Get a single bit mask.
	 *
	 * Returns a ULong with only bit at "whichBit(bitPosition)" set
	 * This method generates and returns a uint64_t (word) mask for a single
	 * bit at a specific bit position. This is used to mask the bit in the
	 * corresponding word.
	 */
	public static ULong maskBit(int bitPosition) {
		return ULong.valueOf(1).leftShift(whichBit(bitPosition));
	}

	/* Determine which bit in a word a bit is in.
	 * Return the bit position in a word for the specified bit position
	 * in the APInt.
	 */
	protected static int whichBit(int bitPosition) {
		return bitPosition % APInt.AP_INT_BITS_PER_WORD;
	}
	
	/* 
	 * A utility function that converts a character to a digit.
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
	
	// ****************************************************************** APINT OPERATIONS
	
	/*
	 *  Determine the smaller of two APInts considered to be signed.
	 */
	public static APInt smin(APInt A,  APInt B) {
	  return A.slt(B) ? A : B;
	}

	/* 
	 * Determine the larger of two APInts considered to be signed.
	 */
	public static APInt smax(APInt A,  APInt B) {
	  return A.sgt(B) ? A : B;
	}

	/* 
	 * Determine the smaller of two APInts considered to be signed.
	 */
	public static APInt umin(APInt A,  APInt B) {
	  return A.ult(B) ? A : B;
	}

	/* 
	 * Determine the larger of two APInts considered to be unsigned.
	 */
	public static APInt umax(APInt A,  APInt B) {
	  return A.ugt(B) ? A : B;
	}

	/* Compute GCD of two unsigned APInt values.
	*
	* This function returns the greatest common divisor of the two APInt values
	* using Stein's algorithm.
	*
	* Returns the greatest common divisor of A and B.
	*/
	public static APInt GreatestCommonDivisor(APInt A, APInt B) {
		// TODO Implement this
		return null;
	}

	/* 
	 * Converts the given APInt to a double value.
	* Treats the APInt as an unsigned value for conversion purposes.
	*/
	public static double RoundAPIntToDouble(APInt APIVal) {
	  return APIVal.roundToDouble();
	}

	/* 
	 * Converts the given APInt to a double value.
	*
	*
	* Treats the APInt as a signed value for conversion purposes.
	*/
	public static double RoundSignedAPIntToDouble(APInt APIVal) {
	  return APIVal.signedRoundToDouble();
	}

	/* 
	 * Converts the given APInt to a float vlalue.
	 */
	public static float RoundAPIntToFloat(APInt APIVal) {
	  return (float)RoundAPIntToDouble(APIVal);
	}

	/* Converts the given APInt to a float value.
	*
	* Treats the APInt as a signed value for conversion purposes.
	*/
	public static float RoundSignedAPIntToFloat(APInt APIVal) {
	  return (float)APIVal.signedRoundToDouble();
	}

	/* Converts the given double value into a APInt.
	*
	* This function convert a double value to an APInt value.
	*/
	public static APInt RoundDoubleToAPInt(double Double, int width) {
		// TODO Implement this
		return null;
	};

	/* Converts a float value into a APInt.
	*
	* Converts a float value into an APInt value.
	*/
	public static APInt RoundFloatToAPInt(float Float, int width) {
	  return RoundDoubleToAPInt((double)Float, width);
	}

	/* 
	 * Return A unsign-divided by B, rounded by the given rounding mode.
	 */
	public static APInt RoundingUDiv(APInt A,  APInt B, APInt.Rounding RM) {
		// TODO Implement this
		return null;
	}

	/* 
	 * Return A sign-divided by B, rounded by the given rounding mode.
	 */
	public static APInt RoundingSDiv(APInt A,  APInt B, APInt.Rounding RM) {
		// TODO Implement this
		return null;
	}

	/* Let q(n) = An^2 + Bn + C, and BW = bit width of the value range
	* (e.g. 32 for i32).
	* This function finds the smallest number n, such that
	* (a) n >= 0 and q(n) = 0, or
	* (b) n >= 1 and q(n-1) and q(n), when evaluated in the set of all
	*     integers, belong to two different intervals [Rk, Rk+R),
	*     where R = 2^BW, and k is an integer.
	* The idea here is to find when q(n) "overflows" 2^BW, while at the
	* same time "allowing" subtraction. In unsigned modulo arithmetic a
	* subtraction (treated as addition of negated numbers) would always
	* count as an overflow, but here we want to allow values to decrease
	* and increase as long as they are within the same interval.
	* Specifically, adding of two negative numbers should not cause an
	* overflow (as long as the magnitude does not exceed the bit width).
	* On the other hand, given a positive number, adding a negative
	* number to it can give a negative result, which would cause the
	* value to go from [-2^BW, 0) to [0, 2^BW). In that sense, zero is
	* treated as a special case of an overflow.
	*
	* This function returns None if after finding k that minimizes the
	* positive solution to q(n) = kR, both solutions are contained between
	* two consecutive integers.
	*
	* There are cases where q(n) > T, and q(n+1) < T (assuming evaluation
	* in arithmetic modulo 2^BW, and treating the values as signed) by the
	* virtue of *signed* overflow. This function will *not* find such an n,
	* however it may find a value of n satisfying the inequalities due to
	* an *unsigned* overflow (if the values are treated as unsigned).
	* To find a solution for a signed overflow, treat it as a problem of
	* finding an unsigned overflow with a range with of BW-1.
	*
	* The returned value may have a different bit width from the input
	* coefficients.
	*/
	public static APInt SolveQuadraticEquationWrap(APInt A, APInt B, APInt C, int RangeWidth){
		// TODO Implement this
		return null;
	}

	/* Compare two values, and if they are different, return the position of the
	* most significant bit that is different in the values.
	*/
	public static Integer GetMostSignificantDifferentBit(APInt A, APInt B){
		// TODO Implement this
		return null;
	}
	
	/* StoreIntToMemory - Fills the StoreBytes bytes of memory starting from Dst
	* with the integer held in IntVal.
	*/
	public static void StoreIntToMemory(APInt IntVal, byte Dst, int StoreBytes){
		// TODO Implement this
	}

	/* LoadIntFromMemory - Loads the integer stored in the LoadBytes bytes starting
	* from Src into IntVal, which is assumed to be wide enough and to hold zero.
	*/
	public static void LoadIntFromMemory(APInt IntVal, byte Src, int LoadBytes){
		// TODO Implement this
	}
}

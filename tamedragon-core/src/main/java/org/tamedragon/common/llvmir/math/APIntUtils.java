package org.tamedragon.common.llvmir.math;

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
}

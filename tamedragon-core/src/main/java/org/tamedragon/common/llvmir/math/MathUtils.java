package org.tamedragon.common.llvmir.math;

import java.math.BigInteger;

public class MathUtils {

	private static final int API_INT_BITS_PER_WORD = 64;

	public static int countLeadingOnes(ULong unsignedValue) {	
		ULong valComplement = unsignedValue.complement();
		return countLeadingZeros(valComplement);
	}

	public static int countTrailingOnes(ULong unsignedValue) {	
		int count = 0;
		ULong mask = ULong.valueOf(1);
		for(int i = 0; i < API_INT_BITS_PER_WORD; i++) {
			if(unsignedValue.and(mask).equals(0)) {
				break;
			}
			count++;
			mask = mask.leftShift(1);
		}
		return count;
	}

	public static int countTrailingZeros(ULong unsignedValue) {	
		ULong valComplement = unsignedValue.complement();
		return countTrailingOnes(valComplement);
	}

	public static int countLeadingZeros(ULong unsignedVal) {
		return countLeadingZeros(unsignedVal.toBigInteger());
	}

	public static int countLeadingZeros(BigInteger valBigInt) {
		int bitLength = valBigInt.bitLength();
		return API_INT_BITS_PER_WORD - bitLength;
	}

	public static int countPopulation(ULong unsignedValue) {
		int count = 0;
		ULong mask = ULong.valueOf(1);
		for(int i = 0; i < API_INT_BITS_PER_WORD; i++) {
			if(unsignedValue.and(mask).equals(1)) {
				count++;
			}
			mask = mask.leftShift(1);
		}

		return count;
	}

	/// Sign-extend the number in the bottom B bits of X to a 64-bit integer.
	/// Requires 0 < B < 64.
	public static long signExtend64(ULong X, int B) {
		if(B <= 0) {
			throw new IllegalArgumentException("Bit width can't be 0.");
		}

		if(B > 64) {
			throw new IllegalArgumentException("Bit width out of range.");
		} 

		return X.longValue() << (64- B) >> (64 - B);
	}

	/* Return true if the argument is a power of two > 0 (64 bit edition.)
	 */
	public static boolean isPowerOf2_64(ULong value) {
		if(value.isLesserThanOrEqualTo(0)) {
			return false;
		}

		if (value.and(value.subtract(1)).isLesserThanOrEqualTo(0)) {
			return true;
		}

		return false;
	}

	/* Return the high 32 bits of a 64 bit value. */
	public static int Hi_32(ULong Value) {
		return Value.rightShift(32).intValue();
	}

	/*Return the low 32 bits of a 64 bit value. */
	public static int Lo_32(ULong Value) {
		return Value.intValue();
	}

	/* Make a 64-bit integer from a high / low pair of 32-bit integers. */
	public static ULong Make_64(int High, int Low) {
		return (ULong.valueOf(High).leftShift(32)).or(ULong.valueOf(Low));
	}

	/*
	 * Return true if the argument is a non-empty sequence of ones starting at the
	 * least significant bit with the remainder zero (64 bit version).
	 */
	public static boolean isMask_64(ULong value) {
		return value.isGreaterThan(0) && ((value.add(1)).and(value)).equals(0);
	}

	/* 
	 * Return true if the argument contains a non-empty sequence of ones with the
	 * remainder zero (64 bit version.)
	 */
	public static boolean isShiftedMask_64(ULong Value) {
		return Value.isGreaterThan(0) && isMask_64((Value.subtract(1)).or(Value));
	}

	public static ULong maskTrailingOnes(int N) {
		if(N <= 64) {
			throw new IllegalArgumentException("Invalid bit index");
		}

		return N == 0 ? ULong.valueOf(0): (ULong.valueOf(-1).rightShift(64 - N));
	}
}

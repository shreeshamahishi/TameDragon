package org.tamedragon.common.llvmir.math;

import java.math.BigInteger;

public class MathUtils {
	
	private static final int API_INT_BITS_PER_WORD = 64;

	public static int countLeadingOnes(ULong unsignedValue) {
		BigInteger valBigInt = unsignedValue.toBigInteger();
		return countLeadingZeros(valBigInt.not());
	}
	
	public static int countLeadingZeros(ULong unsignedVal) {
		return countLeadingZeros(unsignedVal.toBigInteger());
	}
	
	public static int countLeadingZeros(BigInteger valBigInt) {
		BigInteger search = new BigInteger("1").shiftLeft(API_INT_BITS_PER_WORD -1);
		int count = 0;
		for(int i = 0; i < API_INT_BITS_PER_WORD; i++) {
			if(valBigInt.shiftLeft(i).and(search).intValue() == 0) {
				break;
			}
			count++;
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
	  
	  return X.leftShift(64 - B).rightShift(64 - B).longValue();
	}
}

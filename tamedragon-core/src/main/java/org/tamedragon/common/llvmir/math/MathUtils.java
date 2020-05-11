package org.tamedragon.common.llvmir.math;

import java.math.BigInteger;

public class MathUtils {
	
	private static final int API_INT_BITS_PER_WORD = 64;

	public static int countLeadingOnes(ULong unsignedValue) {
		/*BigInteger valBigInt = unsignedValue.toBigInteger();
		System.out.println("Number :  " + valBigInt + ": Bit count: " + valBigInt.bitCount() + ", Bit length : " +  valBigInt.bitLength()
		+ ", Lowest set bit : " + valBigInt.getLowestSetBit());
		BigInteger valComplement = valBigInt.not();
		System.out.println("Complement :  " + valComplement + ": Bit count: " + valComplement.bitCount() + ", Bit length : " +  valComplement.bitLength()
		+ ", Lowest set bit : " + valComplement.getLowestSetBit());
		*/
		
		ULong valComplement = unsignedValue.complement();
		return countLeadingZeros(valComplement);
	}
	
	public static int countLeadingZeros(ULong unsignedVal) {
		return countLeadingZeros(unsignedVal.toBigInteger());
	}
	
	public static int countLeadingZeros(BigInteger valBigInt) {
		/*System.out.println(valBigInt.bitLength());
		
		BigInteger search = new BigInteger("1").shiftLeft(API_INT_BITS_PER_WORD -1);
		int bitCount = valBigInt.bitCount();
		int count = 0;
		for(int i = 0; i < API_INT_BITS_PER_WORD; i++) {
			if(valBigInt.shiftLeft(i).and(search).intValue() == 0) {
				break;
			}
			count++;
		}
		 
		return count;
		*/
		int bitLength = valBigInt.bitLength();
		return API_INT_BITS_PER_WORD - bitLength;
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
}

package org.tamedragon.common.llvmir.math;

import java.math.BigInteger;

public class ULong extends UNumber implements Comparable<ULong> {

	private static final long serialVersionUID = -179884336682326300L;

	public static final String ERROR_INT_CONST_TOO_LARGE = "Integer constant too large";

	private static final int UNSIGNED_LONG_NUM_BITS = 64;

	/**
	 * A constant holding the minimum value an <code>unsigned long</code> can have, 0.
	 */
	public static final BigInteger MIN_VALUE = BigInteger.ZERO;

	/**
	 * A constant holding the maximum value an <code>unsigned long</code> can
	 * have, 2<sup>64</sup>-1.
	 */
	public static final BigInteger MAX_VALUE = new BigInteger("18446744073709551615");

	public static final BigInteger MAX_VALUE_LONG = new BigInteger("9223372036854775808");

	public static final BigInteger MAX_VALUE_INT = new BigInteger("2147483648");

	/**
	 * A constant holding the maximum value + 1 an <code>signed long</code> can
	 * have as ULong, 2<sup>63</sup>.
	 */
	public static final ULong MAX = valueOf(MAX_VALUE);

	private long value;
	private BigInteger unsignedBigInt;

	private ULong(long value) {
		setValues(value);
	}

	private ULong(BigInteger bigIntValue) throws NumberFormatException {
		setWithBigIntValue(bigIntValue);
	}

	private ULong(String valStr) throws NumberFormatException {
		BigInteger bigIntValue = new BigInteger(valStr);
		setWithBigIntValue(bigIntValue);
	}

	public static ULong valueOf(long value) {
		return new ULong(value);
	}

	public static ULong valueOf(BigInteger value) throws NumberFormatException {
		return new ULong(value);
	}

	public static ULong valueOf(String value) throws NumberFormatException {
		return new ULong(value);
	}

	private void setWithBigIntValue(BigInteger bigIntValue) {
		long val = bigIntValue.longValue();
		setValues(val);
	}

	private void setValues(long value) {
		this.value = value;
		if (value >= 0) {
			unsignedBigInt = BigInteger.valueOf(value);
		}
		else {   // Negative value
			long wrap = value & Long.MAX_VALUE;
			unsignedBigInt = BigInteger.valueOf(wrap).add(MAX_VALUE_LONG);
		}
	}

	public ULong clone() {
		return new ULong(value);
	}

	@Override
	public String toString() {
		return unsignedBigInt.toString();
	}

	public static int compare(long x, long y) {
		x += Long.MIN_VALUE;
		y += Long.MIN_VALUE;
		return (x < y) ? -1 : ((x == y) ? 0 : 1);
	}


	@Override
	public int intValue() {
		return (int) value;
	}

	@Override
	public long longValue() {
		return value;
	}

	public BigInteger getUnsignedBigInt() {
		return unsignedBigInt;
	}

	@Override
	public float floatValue() {
		if (value < 0)
			return ((float) (value & Long.MAX_VALUE)) + Long.MAX_VALUE;
		else
			return value;
	}

	@Override
	public double doubleValue() {
		if (value < 0)
			return ((double) (value & Long.MAX_VALUE)) + Long.MAX_VALUE;
		else
			return value;
	}

	@Override
	public int hashCode() {
		return unsignedBigInt.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ULong)
			return value == ((ULong) obj).value;

		return false;
	}

	public boolean equals(long other) {
		return value == other;
	}

	@Override
	public int compareTo(ULong other) {
		return compare(value, other.value);
	}

	// Operations - additions
	public ULong add(final ULong otherVal){
		BigInteger result = unsignedBigInt.add(otherVal.getUnsignedBigInt());
		return ULong.valueOf(result);
	}

	public ULong add(long otherVal) {
		BigInteger result = unsignedBigInt.add(BigInteger.valueOf(otherVal));
		return ULong.valueOf(result);
	}

	// Operations - subtractions
	public ULong subtract(final ULong otherVal){
		BigInteger result = unsignedBigInt.subtract(otherVal.getUnsignedBigInt());
		return ULong.valueOf(result);
	}

	public ULong subtract(long otherVal) {
		BigInteger result = unsignedBigInt.subtract(BigInteger.valueOf(otherVal));
		return ULong.valueOf(result);
	}

	// Operations - multiplications
	public ULong mul(final ULong otherVal){
		BigInteger result = unsignedBigInt.multiply(otherVal.getUnsignedBigInt());
		return ULong.valueOf(result);
	}

	public ULong mul(long otherVal) {
		return mul(ULong.valueOf(otherVal));
	}

	// Operations - divisions
	public ULong div(final ULong val) {
		BigInteger res = unsignedBigInt.divide(val.getUnsignedBigInt());
		return valueOf(res);
	}

	public ULong div(long val) {
		return div(ULong.valueOf(val));
	}

	// Operations - modulo
	public ULong modulo(final ULong val) {
		BigInteger res = unsignedBigInt.mod(val.getUnsignedBigInt());
		return valueOf(res);
	}

	public ULong modulo(long val) {
		return modulo(ULong.valueOf(val));
	}

	// Left shift
	public ULong leftShift(int bits) {
		bits = normalizeBitsForShifting(bits);
		BigInteger sl = unsignedBigInt.shiftLeft(bits);
		return ULong.valueOf(sl);
	}

	// Right shift
	public ULong rightShift(int bits) {
		bits = normalizeBitsForShifting(bits);
		BigInteger sr = unsignedBigInt.shiftRight(bits);
		return ULong.valueOf(sr);
	}

	/*// Right shift in place
	public ULong rightShiftInPlace(int bits) {
		bits = normalizeBitsForShifting(bits);
		setWithBigIntValue(unsignedBigInt.shiftRight(bits));
		return this;
	}*/

	// Operations - Xor
	public ULong xor(ULong other) {
		return ULong.valueOf(unsignedBigInt.xor(other.getUnsignedBigInt()));
	}
	
	public ULong xor(long other) {
		return xor(ULong.valueOf(other));
	}

	// Operations - And
	public ULong and(ULong other) {
		return ULong.valueOf(unsignedBigInt.and(other.getUnsignedBigInt()));
	}
	
	public ULong and(long other) {
		return and(ULong.valueOf(other));
	}

	// Operations - Or
	public ULong or(ULong other) {
		return ULong.valueOf(unsignedBigInt.or(other.getUnsignedBigInt()));
	}
	
	public ULong or(long other) {
		return or(ULong.valueOf(other));
	}

	// Complement
	public ULong complement() {
		return ULong.valueOf(unsignedBigInt.not());
	}

	// Comparison operators - lesser than
	public boolean isLesserThan(long rhs) {
		return isLesserThan(ULong.valueOf(rhs));
	}

	public boolean isLesserThan(ULong other) {
		if(unsignedBigInt.compareTo(other.getUnsignedBigInt()) < 0) {
			return true;
		}
		return false;
	}

	// Comparison operators - lesser than or equal to
	public boolean isLesserThanOrEqualTo(long rhs) {
		if(value == rhs || isLesserThan(ULong.valueOf(rhs))) {
			return true;
		}
		return false;
	}

	public boolean isLesserThanOrEqualTo(ULong other) {
		if(unsignedBigInt.compareTo(other.getUnsignedBigInt()) <= 0) {
			return true;
		}
		return false;
	}

	// Comparison operators - greater than
	public boolean isGreaterThan(long rhs) {
		return isGreaterThan(ULong.valueOf(rhs));
	}

	public boolean isGreaterThan(ULong other) {
		if(unsignedBigInt.compareTo(other.getUnsignedBigInt()) > 0) {
			return true;
		}
		return false;
	}

	// Comparison operators - greater than or equal to
	public boolean isGreaterThanOrEqualTo(int rhs) {
		if(value == rhs || isGreaterThan(rhs)) {
			return true;
		}
		return false;
	}

	public boolean isGreaterThanOrEqualTo(ULong other) {
		if(unsignedBigInt.compareTo(other.getUnsignedBigInt()) >= 0) {
			return true;
		}
		return false;
	}

	public ULong subtract(final int val) {
		return subtract((long) val);
	}

	private int normalizeBitsForShifting(int bits) {
		if(bits < 0) {
			long wrap = bits & Integer.MAX_VALUE;
			bits = BigInteger.valueOf(wrap).add(MAX_VALUE_LONG).intValue();
		}

		if(bits >= UNSIGNED_LONG_NUM_BITS) {
			bits = bits % UNSIGNED_LONG_NUM_BITS;
		}

		return bits;
	}


}
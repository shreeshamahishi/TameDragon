package org.tamedragon.common.llvmir.math;

import java.math.BigInteger;

import org.antlr.grammar.v3.CodeGenTreeWalker.setBlock_return;


public class ULong extends UNumber implements Comparable<ULong> {

	public static final String ERROR_INT_CONST_TOO_LARGE = "Integer constant too large";

	/**
	 * A constant holding the minimum negative value an <code>unsigned long</code> can
	 * have, 0.
	 */
	public static final BigInteger MIN_NEGATIVE_VALUE =  new BigInteger("-18446744073709551615");

	/**
	 * A constant holding the minimum value an <code>unsigned long</code> can
	 * have, 0.
	 */
	public static final BigInteger MIN_VALUE = BigInteger.ZERO;

	/**
	 * A constant holding the maximum value an <code>unsigned long</code> can
	 * have, 2<sup>64</sup>-1.
	 */
	public static final BigInteger MAX_VALUE = new BigInteger("18446744073709551615");

	/**
	 * A constant holding the maximum value + 1 an <code>signed long</code> can
	 * have, 2<sup>63</sup>.
	 */
	public static final BigInteger MAX_VALUE_LONG = new BigInteger("9223372036854775808");

	/**
	 * A constant holding the minimum value an <code>unsigned long</code> can
	 * have as ULong, 0.
	 */
	public static final ULong MIN = valueOf(MIN_VALUE.longValue());

	/**
	 * A constant holding the maximum value + 1 an <code>signed long</code> can
	 * have as ULong, 2<sup>63</sup>.
	 */
	public static final ULong MAX = valueOf(MAX_VALUE);

	/**
	 * The value modeling the content of this <code>unsigned long</code>
	 */
	private long value;

	/**
	 * The value modeling the content of this <code>unsigned long</code>
	 */
	private BigInteger unsignedBigInt;


	/**
	 * Create an <code>unsigned long</code> by masking it with
	 * <code>0xFFFFFFFFFFFFFFFF</code> i.e. <code>(long) -1</code> becomes
	 * <code>(uint) 18446744073709551615</code>
	 */
	private ULong(long value) {
		setValues(value);
	}

	/**
	 * Create an <code>unsigned long</code>
	 *
	 * @throws NumberFormatException If <code>value</code> is not in the range
	 *             of an <code>unsigned long</code>
	 */
	private ULong(BigInteger bigIntValue) throws NumberFormatException {
		setWithBigIntValue(bigIntValue);
	}

	/**
	 * Create an <code>unsigned long</code>
	 *
	 * @throws NumberFormatException If <code>value</code> does not contain a
	 *             parsable <code>unsigned long</code>.
	 */
	private ULong(String valStr) throws NumberFormatException {

		if(valStr == null || valStr.length() == 0){
			throw new IllegalArgumentException("Invalid value string for arbitrary precision integer");
		}


		BigInteger bigIntValue = new BigInteger(valStr);
		setWithBigIntValue(bigIntValue);

	}

	private void setWithBigIntValue(BigInteger bigIntValue) {
		//if(bigIntValue.compareTo(MIN_NEGATIVE_VALUE) < 0 || bigIntValue.compareTo(MAX_VALUE) > 0) {
		//	throw new NumberFormatException(ERROR_INT_CONST_TOO_LARGE);
		//}

		long val = bigIntValue.longValue();
		setValues(val);
	}

	private void setValues(long value) {
		this.value = value;
		if (value >= 0) {
			unsignedBigInt = BigInteger.valueOf(value);
		}
		else {
			long wrap = value & Long.MAX_VALUE;
			unsignedBigInt = BigInteger.valueOf(wrap).add(MAX_VALUE_LONG);
		}
	}
	
	public ULong clone() {
		return new ULong(value);
	}

	/**
	 * Create an <code>unsigned long</code>
	 *
	 * @throws NumberFormatException If <code>value</code> does not contain a
	 *             parsable <code>unsigned long</code>.
	 */
	public static ULong valueOf(String value) throws NumberFormatException {
		return new ULong(value);
	}

	/**
	 * Create an <code>unsigned long</code> by masking it with
	 * <code>0xFFFFFFFFFFFFFFFF</code> i.e. <code>(long) -1</code> becomes
	 * <code>(uint) 18446744073709551615</code>
	 */
	public static ULong valueOf(long value) {
		return new ULong(value);
	}

	/**
	 * Create an <code>unsigned long</code>
	 *
	 * @throws NumberFormatException If <code>value</code> is not in the range
	 *             of an <code>unsigned long</code>
	 */
	public static ULong valueOf(BigInteger value) throws NumberFormatException {
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
		/* [java-8] */
		if (true) return Long.hashCode(value);
		/* [/java-8] */
		return Long.valueOf(value).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ULong)
			return value == ((ULong) obj).value;

		return false;
	}

	@Override
	public int compareTo(ULong o) {
		return compare(value, o.value);
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

	// Operations - additions in place versions
	public ULong addInPlace(final ULong otherVal){
		BigInteger result = unsignedBigInt.add(otherVal.getUnsignedBigInt());
		setWithBigIntValue(result);
		return this;
	}

	public ULong addInPlace(long otherVal) {
		BigInteger result = unsignedBigInt.add(BigInteger.valueOf(otherVal));
		setWithBigIntValue(result);
		return this;
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

	// Operations - subtractions in place versions
	public ULong subtractInPlace(final ULong otherVal){
		BigInteger result = unsignedBigInt.subtract(otherVal.getUnsignedBigInt());
		setWithBigIntValue(result);
		return this;
	}

	public ULong subtractInPlace(long otherVal) {
		BigInteger result = unsignedBigInt.subtract(BigInteger.valueOf(otherVal));
		setWithBigIntValue(result);
		return this;
	}

	// Operations - multiplications
	public ULong mul(final ULong otherVal){
		BigInteger result = unsignedBigInt.multiply(otherVal.getUnsignedBigInt());
		return ULong.valueOf(result);
	}

	public ULong mul(long otherVal) {
		BigInteger result = unsignedBigInt.multiply(BigInteger.valueOf(otherVal));
		return ULong.valueOf(result);
	}

	// Operations - subtractions in place versions
	public ULong mulInPlace(final ULong otherVal){
		BigInteger result = unsignedBigInt.multiply(otherVal.getUnsignedBigInt());
		setWithBigIntValue(result);
		return this;
	}

	public ULong mulInPlace(long otherVal) {
		BigInteger result = unsignedBigInt.multiply(BigInteger.valueOf(otherVal));
		setWithBigIntValue(result);
		return this;
	}

	// Operations - divisions
	public ULong div(final ULong val) {
		BigInteger res = unsignedBigInt.divide(val.getUnsignedBigInt());
		return valueOf(res);
	}

	public ULong modulo(final ULong val) {
		BigInteger res = unsignedBigInt.mod(val.getUnsignedBigInt());
		return valueOf(res);
	}

	public ULong div(long val) {
		BigInteger res = unsignedBigInt.divide(BigInteger.valueOf(val));
		return valueOf(res);
	}

	public ULong modulo(long val) {
		BigInteger res = unsignedBigInt.mod(BigInteger.valueOf(val));
		return valueOf(res);
	}

	public ULong subtract(final int val) {
		return subtract((long) val);
	}

	public ULong leftShift(int bits) {
		BigInteger sl = unsignedBigInt.shiftLeft(bits);
		return ULong.valueOf(sl);
	}

	public ULong rightShift(int bits) {
		BigInteger sr = unsignedBigInt.shiftRight(bits);
		return ULong.valueOf(sr);
	}

	public ULong rightShiftInPlace(int bits) {
		setWithBigIntValue(unsignedBigInt.shiftRight(bits));
		return this;
	}

	public ULong xor(ULong other) {
		return ULong.valueOf(unsignedBigInt.xor(other.getUnsignedBigInt()));
	}

	public ULong xorInPlace(ULong other) {
		setWithBigIntValue(unsignedBigInt.xor(other.getUnsignedBigInt()));
		return this;
	}

	public ULong and(ULong other) {
		return ULong.valueOf(unsignedBigInt.and(other.getUnsignedBigInt()));
	}

	public ULong andInPlace(ULong other) {
		setWithBigIntValue(unsignedBigInt.and(other.getUnsignedBigInt()));
		return this;
	}

	public ULong or(ULong other) {
		return ULong.valueOf(unsignedBigInt.or(other.getUnsignedBigInt()));
	}

	public ULong orInPlace(ULong other) {
		setWithBigIntValue(unsignedBigInt.or(other.getUnsignedBigInt()));
		return this;
	}

	public boolean isLesserThan(int other) {
		if(value < other) {
			return true;
		}
		return false;
	}

	public boolean isLesserThan(ULong other) {
		if(unsignedBigInt.compareTo(other.getUnsignedBigInt()) < 0) {
			return true;
		}
		return false;
	}

	public boolean isLesserThanOrEqualTo(int other) {
		if(value == other || value < other) {
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

	public boolean isGreaterThan(int rHS) {
		if(value > rHS) {
			return true;
		}
		return false;
	}

	public boolean isGreaterThan(ULong other) {
		if(unsignedBigInt.compareTo(other.getUnsignedBigInt()) > 0) {
			return true;
		}
		return false;
	}

	public boolean isGreaterThanOrEqualTo(int rHS) {
		if(value == rHS || value > rHS) {
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

	public ULong complement() {
		ULong complement = xor(MAX);
		return complement;
	}
}
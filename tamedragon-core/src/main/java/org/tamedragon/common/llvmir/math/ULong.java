package org.tamedragon.common.llvmir.math;

import java.math.BigInteger;


public class ULong extends UNumber implements Comparable<ULong> {

	/**
	 * A constant holding the minimum value an <code>unsigned long</code> can
	 * have, 0.
	 */
	public static final BigInteger MIN_VALUE        = BigInteger.ZERO;

	/**
	 * A constant holding the maximum value an <code>unsigned long</code> can
	 * have, 2<sup>64</sup>-1.
	 */
	public static final BigInteger MAX_VALUE        = new BigInteger("18446744073709551615");

	/**
	 * A constant holding the maximum value + 1 an <code>signed long</code> can
	 * have, 2<sup>63</sup>.
	 */
	public static final BigInteger MAX_VALUE_LONG   = new BigInteger("9223372036854775808");

	/**
	 * A constant holding the minimum value an <code>unsigned long</code> can
	 * have as ULong, 0.
	 */
	public static final ULong      MIN              = valueOf(MIN_VALUE.longValue());

	/**
	 * A constant holding the maximum value + 1 an <code>signed long</code> can
	 * have as ULong, 2<sup>63</sup>.
	 */
	public static final ULong      MAX              = valueOf(MAX_VALUE);

	/**
	 * The value modelling the content of this <code>unsigned long</code>
	 */
	private final long             value;

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

	public static int compare(long x, long y) {
		x += Long.MIN_VALUE;
		y += Long.MIN_VALUE;
		return (x < y) ? -1 : ((x == y) ? 0 : 1);
	}

	/**
	 * Create an <code>unsigned long</code>
	 *
	 * @throws NumberFormatException If <code>value</code> is not in the range
	 *             of an <code>unsigned long</code>
	 */
	private ULong(BigInteger value) throws NumberFormatException {
		if (value.compareTo(MIN_VALUE) < 0 || value.compareTo(MAX_VALUE) > 0)
			throw new NumberFormatException();
		else
			this.value = value.longValue();
	}

	/**
	 * Create an <code>unsigned long</code> by masking it with
	 * <code>0xFFFFFFFFFFFFFFFF</code> i.e. <code>(long) -1</code> becomes
	 * <code>(uint) 18446744073709551615</code>
	 */
	private ULong(long value) {
		this.value = value;
	}

	/// Convert a char array into an APInt
	///
	/// \param radix 2, 8, 10, 16, or 36
	/// Converts a string into a number.  The string must be non-empty
	/// and well-formed as a number of the given base. The bit-width
	/// must be sufficient to hold the result.
	///
	/// This is used by the constructors that take string arguments.
	///
	/// StringRef::getAsInteger is superficially similar but (1) does
	/// not assume that the string is well-formed and (2) grows the
	/// result to hold the input.
	//public void fromString(int numBits, String str, short radix) {
	// (Over-)estimate the required number of bits.
	//  int numBits = ((str.length() * 64) / 19) + 2;
	//}

	/**
	 * Create an <code>unsigned long</code>
	 *
	 * @throws NumberFormatException If <code>value</code> does not contain a
	 *             parsable <code>unsigned long</code>.
	 */
	private ULong(String value) throws NumberFormatException {

		//long x = 9223372036854775808L;

		if(value == null || value.length() == 0){
			throw new IllegalArgumentException("Invalid value string for arbitrary precision integer");
		}

		int valLength = value.length();

		boolean isNegative = false;
		if (value.charAt(0) == '-') {
			isNegative = true;
			valLength--;
		}

		if (valLength <= 18) {
			this.value = Long.parseLong(value, 10);
			return;
		}

		final long first = Long.parseLong(value.substring(0, valLength - 1), 10);
		final int second = Character.digit(value.charAt(valLength - 1), 10);
		if (second < 0)
			throw new NumberFormatException("Bad digit at end of " + value);

		long result = first * 10 + second;
		if (compare(result, first) < 0)
			throw new NumberFormatException(
					String.format("String value %s exceeds range of unsigned long", value));

		this.value = result;
	}

	@Override
	public int intValue() {
		return (int) value;
	}

	@Override
	public long longValue() {
		return value;
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
	public String toString() {
		if (value >= 0)
			return Long.toString(value);
		else {
			long longWithMask = value & Long.MAX_VALUE;
			return BigInteger.valueOf(longWithMask).add(MAX_VALUE_LONG).toString();
		}
	}

	@Override
	public int compareTo(ULong o) {
		return compare(value, o.value);
	}

	public ULong add(ULong val) throws NumberFormatException {
		if (value < 0 && val.value < 0)
			throw new NumberFormatException();

		final long result = value + val.value;
		if ((value < 0 || val.value < 0) && result >= 0)
			throw new NumberFormatException();

		return valueOf(result);
	}

	public ULong add(int val) throws NumberFormatException {
		return add((long) val);
	}

	public ULong add(long val) throws NumberFormatException {
		if (val < 0)
			return subtract(Math.abs(val));

		final long result = value + val;
		if (value < 0 && result >= 0)
			throw new NumberFormatException();

		return valueOf(result);
	}

	public ULong subtract(final ULong val) {
		//if (this.compareTo(val) < 0)
		//	throw new NumberFormatException();

		final long result = value - val.value;
		if (value < 0 && result >= 0)
			throw new NumberFormatException();

		return valueOf(result);
	}

	public ULong mul(final ULong val) {
		// TODO Implement later
		return null;
	}

	public ULong subtract(final int val) {
		return subtract((long) val);
	}

	public ULong subtract(final long val) {
		if (val < 0)
			return add(-val);

		if (compare(value, val) < 0)
			throw new NumberFormatException();

		final long result = value - val;
		if (value < 0 && result >= 0)
			throw new NumberFormatException();

		return valueOf(result);
	}

	public ULong leftShift(int bits) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ULong rightShift(int bits) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ULong or(ULong other) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean isLesserThan(int other) {
		// TODO Implement this
		return false;
	}
	
	public boolean isLesserThan(ULong other) {
		// TODO Implement this
		return false;
	}

	public boolean isGreaterThan(int rHS) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean isGreaterThan(ULong rHS) {
		// TODO Auto-generated method stub
		return false;
	}

	public ULong complement() {
		// TODO Auto-generated method stub
		return null;
	}

	public ULong and(ULong mask) {
		// TODO Auto-generated method stub
		return null;
	}
}
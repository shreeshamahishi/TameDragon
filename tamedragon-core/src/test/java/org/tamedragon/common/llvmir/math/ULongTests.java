package org.tamedragon.common.llvmir.math;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Test;

public class ULongTests {

	@Test
	public void testInitializationsWithLongValues() {
		long val = 9223372036854775807L;
		ULong ulong = ULong.valueOf(val);
		assertEquals(ulong.toString(), "9223372036854775807");

		val = -9223372036854775808L;
		ulong = ULong.valueOf(val);
		assertEquals(ulong.toString(), "9223372036854775808");

		// Within limit negative value
		val = -9223372036854775807L;
		ulong = ULong.valueOf(val);
		assertEquals(ulong.toString(), "9223372036854775809");

		// Within limit positive value
		val = 9223372036854770L;
		ulong = ULong.valueOf(val);
		assertEquals(ulong.toString(), "9223372036854770");
	}

	@Test
	public void testInitializationsWithBigIntValues() {
		BigInteger bigInt = new BigInteger("18446744073709551615");
		ULong ulong = ULong.valueOf(bigInt);
		assertEquals(ulong.toString(), "18446744073709551615");

		bigInt = new BigInteger("-18446744073709551615");
		ulong = ULong.valueOf(bigInt);
		assertEquals(ulong.toString(), "1");

		// Outside maximum limit - wrap around
		bigInt = new BigInteger("184467440737095516153");
		ulong = ULong.valueOf(bigInt);
		assertTrue(ulong.toString().equals("18446744073709551609"));

		// Outside minimum limit  - wrap around
		bigInt = new BigInteger("-45");
		ulong = ULong.valueOf(bigInt);
		assertTrue(ulong.toString().equals("18446744073709551571"));
	}

	@Test
	public void testAssignmentWithStringWithinAndOutsideRanges() {
		ULong ulong = ULong.valueOf("18446744073709551615");
		assertEquals(ulong.toString(), "18446744073709551615");

		ulong = ULong.valueOf("-18446744073709551615");
		assertEquals(ulong.toString(), "1");

		// Outside maximum limit
		ulong = ULong.valueOf("184467440737095516153");
		assertTrue(ulong.toString().equals("18446744073709551609"));

		// Outside minimum limit
		ulong = ULong.valueOf("-18446744073709551616");
		assertTrue(ulong.toString().equals("0"));
	}

	@Test
	public void testAdditions() {
		// Within range
		ULong x = ULong.valueOf("18446744073709551613");
		ULong y = ULong.valueOf(1);
		ULong res = x.add(y);
		assertEquals(res.toString(), "18446744073709551614");
		res = x.add(1);
		assertEquals(res.toString(), "18446744073709551614");

		// At boundary
		y = ULong.valueOf(2);
		res = x.add(y);
		assertEquals(res.toString(), "18446744073709551615");
		res = x.add(2);
		assertEquals(res.toString(), "18446744073709551615");

		// Out of bounds
		y = ULong.valueOf(3);
		res = x.add(y);
		assertEquals(res.toString(), "0");
		res = x.add(3);
		assertEquals(res.toString(), "0");

		y = ULong.valueOf(4);
		res = x.add(y);
		assertEquals(res.toString(), "1");
		res = x.add(4);
		assertEquals(res.toString(), "1");

		// Add with a negative number
		y = ULong.valueOf(-13);
		res = x.add(y);
		assertEquals(res.toString(), "18446744073709551600");
		res = x.add(-13);
		assertEquals(res.toString(), "18446744073709551600");

		// Add to a negative number
		x = ULong.valueOf(-908);
		y = ULong.valueOf(87);
		res = x.add(y);
		assertEquals(res.toString(), "18446744073709550795");
		res = x.add(87);
		assertEquals(res.toString(), "18446744073709550795");

		assertTrue(res != x);
		assertTrue(res != y);
	}

	@Test
	public void testSubtractions() {
		// Within range
		ULong x = ULong.valueOf("18446744073709551613");
		ULong y = ULong.valueOf(10);
		ULong res = x.subtract(y);
		assertEquals(res.toString(), "18446744073709551603");
		res = x.subtract(10);
		assertEquals(res.toString(), "18446744073709551603");

		// At boundary
		y = ULong.valueOf("18446744073709551613");
		res = x.subtract(y);
		assertEquals(res.toString(), "0");

		// Result below lower bound
		y = ULong.valueOf("18446744073709551614");
		res = x.subtract(y);
		assertEquals(res.toString(), "18446744073709551615");

		y = ULong.valueOf("18446744073709551615");
		res = x.subtract(y);
		assertEquals(res.toString(), "18446744073709551614");

		x = ULong.valueOf(45);
		y = ULong.valueOf(87);
		res = x.subtract(y);
		assertEquals(res.toString(), "18446744073709551574");
		res = x.subtract(87);
		assertEquals(res.toString(), "18446744073709551574");

		// Subtracting with negative number
		x = ULong.valueOf(45);
		y = ULong.valueOf(-87);
		res = x.subtract(y);
		assertEquals(res.toString(), "132");
		res = x.subtract(-87);
		assertEquals(res.toString(), "132");

		x = ULong.valueOf(-45);
		y = ULong.valueOf(-87);
		res = x.subtract(y);
		assertEquals(res.toString(), "42");
		res = x.subtract(-87);
		assertEquals(res.toString(), "42");

		assertTrue(res != x);
		assertTrue(res != y);
	}

	@Test
	public void testMultiplications() {
		// Within range
		ULong x = ULong.valueOf("3689348814741910323");
		ULong y = ULong.valueOf(4);
		ULong res = x.mul(y);
		assertEquals(res.toString(), "14757395258967641292");
		res = x.mul(4);
		assertEquals(res.toString(), "14757395258967641292");

		// At boundary
		y = ULong.valueOf("5");
		res = x.mul(y);
		assertEquals(res.toString(), "18446744073709551615");
		res = x.mul(5);
		assertEquals(res.toString(), "18446744073709551615");

		// Out of bounds
		y = ULong.valueOf("6");
		res = x.mul(y);
		assertEquals(res.toString(), "3689348814741910322");
		res = x.mul(6);
		assertEquals(res.toString(), "3689348814741910322");

		// Multiply with a negative number
		x = ULong.valueOf("453");
		y = ULong.valueOf("-3");
		res = x.mul(y);
		assertEquals(res.toString(), "18446744073709550257");
		res = x.mul(-3);
		assertEquals(res.toString(), "18446744073709550257");

		// Multiply to a negative number
		x = ULong.valueOf("-453");
		y = ULong.valueOf("5");
		res = x.mul(y);
		assertEquals(res.toString(), "18446744073709549351");
		res = x.mul(5);
		assertEquals(res.toString(), "18446744073709549351");

		y = ULong.valueOf("-4");
		res = x.mul(y);
		assertEquals(res.toString(), "1812");
		res = x.mul(-4);
		assertEquals(res.toString(), "1812");

		assertTrue(res != x);
		assertTrue(res != y);
	}

	@Test
	public void testDivisions() {
		// Within range
		ULong x = ULong.valueOf("18446744073709551615");
		ULong y = ULong.valueOf(5);
		ULong res = x.div(y);
		assertEquals(res.toString(), "3689348814741910323");
		res = x.div(5);
		assertEquals(res.toString(), "3689348814741910323");

		// Numerator out of range
		x = ULong.valueOf("184467440737095516154");
		y = ULong.valueOf("2");
		res = x.div(y);
		assertEquals(res.toString(), "9223372036854775805");
		res = x.div(2);
		assertEquals(res.toString(), "9223372036854775805");

		// Denominator out of range
		x = ULong.valueOf("1844674407370955161");
		y = ULong.valueOf("184467440737095516157");
		res = x.div(y);
		assertEquals(res.toString(), "0");

		// Denom and numerator and denom > numerator
		x = ULong.valueOf("1");
		y = ULong.valueOf("2");
		res = x.div(y);
		assertEquals(res.toString(), "0");
		res = x.div(2);
		assertEquals(res.toString(), "0");

		// Divide with a negative number
		x = ULong.valueOf("1024");
		y = ULong.valueOf("-2");
		res = x.div(y);
		assertEquals(res.toString(), "0");
		res = x.div(-2);
		assertEquals(res.toString(), "0");

		// Divide a negative number
		x = ULong.valueOf("-1024");
		y = ULong.valueOf("3");
		res = x.div(y);
		assertEquals(res.toString(), "6148914691236516864");
		res = x.div(3);
		assertEquals(res.toString(), "6148914691236516864");

		x = ULong.valueOf("-1024");
		y = ULong.valueOf("-4");
		res = x.div(y);
		assertEquals(res.toString(), "0");
		res = x.div(-4);
		assertEquals(res.toString(), "0");

		assertTrue(res != x);
		assertTrue(res != y);
	}

	@Test
	public void testModulo() {
		// Within range
		ULong x = ULong.valueOf("6");
		ULong y = ULong.valueOf(3);
		ULong res = x.modulo(y);
		assertEquals(res.toString(), "0");
		res = x.modulo(3);
		assertEquals(res.toString(), "0");

		// Numerator out of range
		x = ULong.valueOf("184467440737095516154");
		y = ULong.valueOf("2");
		res = x.modulo(y);
		assertEquals(res.toString(), "0");
		res = x.modulo(2);
		assertEquals(res.toString(), "0");

		// Denominator out of range
		x = ULong.valueOf("1844674407370955161");
		y = ULong.valueOf("184467440737095516157");
		res = x.modulo(y);
		assertEquals(res.toString(), "1844674407370955161");

		// Denom and numerator and denom > numerator
		x = ULong.valueOf("1");
		y = ULong.valueOf("2");
		res = x.modulo(y);
		assertEquals(res.toString(), "1");
		res = x.modulo(2);
		assertEquals(res.toString(), "1");

		// Divide with a negative number
		x = ULong.valueOf("1024");
		y = ULong.valueOf("-3");
		res = x.modulo(y);
		assertEquals(res.toString(), "1024");
		res = x.modulo(-3);
		assertEquals(res.toString(), "1024");

		// Divide a negative number
		x = ULong.valueOf("-1024");
		y = ULong.valueOf("3");
		res = x.modulo(y);
		assertEquals(res.toString(), "0");
		res = x.modulo(3);
		assertEquals(res.toString(), "0");

		x = ULong.valueOf("-1024");
		y = ULong.valueOf("-4");
		res = x.modulo(y);
		assertEquals(res.toString(), "18446744073709550592");
		res = x.modulo(-4);
		assertEquals(res.toString(), "18446744073709550592");

		assertTrue(res != x);
		assertTrue(res != y);
	}

	@Test
	public void testLeftShift() {
		// Within range 
		ULong x = ULong.valueOf("9223372036854775802");
		ULong res = x.leftShift(1);
		assertEquals(res.toString(), "18446744073709551604");

		// Value at boundary, result out of range
		x = ULong.valueOf("18446744073709551615");
		res = x.leftShift(1);
		assertEquals(res.toString(), "18446744073709551614");

		// Value out of boundary
		x = ULong.valueOf("18446744073709551618");
		res = x.leftShift(2);
		assertEquals(res.toString(), "8");

		// Shift 63 bits
		x = ULong.valueOf("18446744073709551615");
		res = x.leftShift(63);
		assertEquals(res.toString(), "9223372036854775808");

		// Shift 64 bits
		x = ULong.valueOf("18446744073709551615");
		res = x.leftShift(64);
		assertEquals(res.toString(), "18446744073709551615");

		// Shift 65 bits
		x = ULong.valueOf("18446744073709551615");
		res = x.leftShift(65);
		assertEquals(res.toString(), "18446744073709551614");

		// Shift 131 bits
		x = ULong.valueOf("1024");
		res = x.leftShift(131);
		assertEquals(res.toString(), "8192");

		// Shift 0 bits
		x = ULong.valueOf("1024");
		res = x.leftShift(0);
		assertEquals(res.toString(), "1024");

		// Shift -1 bits
		x = ULong.valueOf("1024");
		res = x.leftShift(-1);
		assertEquals(res.toString(), "0");

		// Shift -345 bits
		x = ULong.valueOf("1024");
		res = x.leftShift(-345);
		assertEquals(res.toString(), "562949953421312");

		assertTrue(res != x);
	}

	@Test
	public void testRightShift() {
		// Within range 
		ULong x = ULong.valueOf("9223372036854775802");
		ULong res = x.rightShift(1);
		assertEquals(res.toString(), "4611686018427387901");

		// Value at boundary
		x = ULong.valueOf("18446744073709551615");
		res = x.rightShift(1);
		assertEquals(res.toString(), "9223372036854775807");

		// Value out of boundary
		x = ULong.valueOf("18446744073709551618");
		res = x.rightShift(2);
		assertEquals(res.toString(), "0");

		// Shift 63 bits
		x = ULong.valueOf("18446744073709551615");
		res = x.rightShift(63);
		assertEquals(res.toString(), "1");

		// Shift 64 bits
		x = ULong.valueOf("18446744073709551615");
		res = x.rightShift(64);
		assertEquals(res.toString(), "18446744073709551615");

		// Shift 65 bits
		x = ULong.valueOf("18446744073709551615");
		res = x.rightShift(65);
		assertEquals(res.toString(), "9223372036854775807");

		// Shift 131 bits
		x = ULong.valueOf("1024");
		res = x.rightShift(131);
		assertEquals(res.toString(), "128");

		// Shift 0 bits
		x = ULong.valueOf("1024");
		res = x.rightShift(0);
		assertEquals(res.toString(), "1024");

		// Shift -1 bits
		x = ULong.valueOf("1024");
		res = x.rightShift(-1);
		assertEquals(res.toString(), "0");

		// Shift -345 bits
		x = ULong.valueOf("1024");
		res = x.rightShift(-345);
		assertEquals(res.toString(), "0");

		assertTrue(res != x);
	}

	@Test
	public void testXor() {
		// Within range 
		ULong x = ULong.valueOf("1234");
		ULong res = x.xor(ULong.valueOf(321));
		assertEquals(res.toString(), "1427");
		res = x.xor(321);
		assertEquals(res.toString(), "1427");

		// Both values at upper boundary
		x = ULong.valueOf("18446744073709551615");
		res = x.xor(ULong.valueOf("18446744073709551615"));
		assertEquals(res.toString(), "0");

		// Both values at lower boundary
		x = ULong.valueOf(0);
		res = x.xor(ULong.valueOf(0));
		assertEquals(res.toString(), "0");
		res = x.xor(0);
		assertEquals(res.toString(), "0");

		// LHS beyond upper boundary
		x = ULong.valueOf("18446744073709551618");
		res = x.xor(ULong.valueOf(63));
		assertEquals(res.toString(), "61");
		res = x.xor(63);
		assertEquals(res.toString(), "61");

		// RHS beyond upper boundary
		x = ULong.valueOf("8769067");
		res = x.xor(ULong.valueOf("18446744073709551632"));
		assertEquals(res.toString(), "8769083");

		// Both beyond upper boundary
		x = ULong.valueOf("18446744073709551647");
		res = x.xor(ULong.valueOf("184467440737095516321"));
		assertEquals(res.toString(), "190");

		// LHS below lower boundary
		x = ULong.valueOf("-906");
		res = x.xor(ULong.valueOf("1024"));
		assertEquals(res.toString(), "18446744073709549686");
		res = x.xor(1024);
		assertEquals(res.toString(), "18446744073709549686");

		// RHS below lower boundary
		x = ULong.valueOf("1024");
		res = x.xor(ULong.valueOf("-9087"));
		assertEquals(res.toString(), "18446744073709541505");
		res = x.xor(-9087);
		assertEquals(res.toString(), "18446744073709541505");

		// Both below lower boundary
		x = ULong.valueOf("-2048");
		res = x.xor(ULong.valueOf("-9089"));
		assertEquals(res.toString(), "9343");
		res = x.xor(-9089);
		assertEquals(res.toString(), "9343");

		// With zero
		x = ULong.valueOf("2048");
		res = x.xor(ULong.valueOf(0));
		assertEquals(res.toString(), "2048");
		res = x.xor(0);
		assertEquals(res.toString(), "2048");
		
		x = ULong.valueOf(0);
		res = x.xor(ULong.valueOf("2048"));
		assertEquals(res.toString(), "2048");
		res = x.xor(2048);
		assertEquals(res.toString(), "2048");

		assertTrue(res != x);
	}

	@Test
	public void testAnd() {
		// Within range 
		ULong x = ULong.valueOf("1234");
		ULong res = x.and(ULong.valueOf(321));
		assertEquals(res.toString(), "64");
		res = x.and(321);
		assertEquals(res.toString(), "64");

		// Both values at upper boundary
		x = ULong.valueOf("18446744073709551615");
		res = x.and(ULong.valueOf("18446744073709551615"));
		assertEquals(res.toString(), "18446744073709551615");

		// Both values at lower boundary
		x = ULong.valueOf(0);
		res = x.and(ULong.valueOf(0));
		assertEquals(res.toString(), "0");
		res = x.and(0);
		assertEquals(res.toString(), "0");

		// LHS beyond upper boundary
		x = ULong.valueOf("18446744073709551618");
		res = x.and(ULong.valueOf(63));
		assertEquals(res.toString(), "2");
		res = x.and(63);
		assertEquals(res.toString(), "2");

		// RHS beyond upper boundary
		x = ULong.valueOf("8769067");
		res = x.and(ULong.valueOf("18446744073709551632"));
		assertEquals(res.toString(), "0");

		// Both beyond upper boundary
		x = ULong.valueOf("18446744073709551647");
		res = x.and(ULong.valueOf("184467440737095516321"));
		assertEquals(res.toString(), "1");

		// LHS below lower boundary
		x = ULong.valueOf("-906");
		res = x.and(ULong.valueOf("1024"));
		assertEquals(res.toString(), "1024");
		res = x.and(1024);
		assertEquals(res.toString(), "1024");

		// RHS below lower boundary
		x = ULong.valueOf("1024");
		res = x.and(ULong.valueOf("-9087"));
		assertEquals(res.toString(), "1024");
		res = x.and(-9087);
		assertEquals(res.toString(), "1024");

		// Both below lower boundary
		x = ULong.valueOf("-2048");
		res = x.and(ULong.valueOf("-9089"));
		assertEquals(res.toString(), "18446744073709541376");
		res = x.and(-9089);
		assertEquals(res.toString(), "18446744073709541376");

		// With zero
		x = ULong.valueOf("2048");
		res = x.and(ULong.valueOf(0));
		assertEquals(res.toString(), "0");
		res = x.and(0);
		assertEquals(res.toString(), "0");
		x = ULong.valueOf(0);
		res = x.and(ULong.valueOf("2048"));
		assertEquals(res.toString(), "0");
		res = x.and(2048);
		assertEquals(res.toString(), "0");

		assertTrue(res != x);
	}

	@Test
	public void testOr() {
		// Within range 
		ULong x = ULong.valueOf("1234");
		ULong res = x.or(ULong.valueOf(321));
		assertEquals(res.toString(), "1491");
		res = x.or(321);
		assertEquals(res.toString(), "1491");

		// Both values at upper boundary
		x = ULong.valueOf("18446744073709551615");
		res = x.or(ULong.valueOf("18446744073709551615"));
		assertEquals(res.toString(), "18446744073709551615");

		// Both values at lower boundary
		x = ULong.valueOf(0);
		res = x.or(ULong.valueOf(0));
		assertEquals(res.toString(), "0");
		res = x.or(0);
		assertEquals(res.toString(), "0");

		// LHS beyond upper boundary
		x = ULong.valueOf("18446744073709551618");
		res = x.or(ULong.valueOf(63));
		assertEquals(res.toString(), "63");
		res = x.or(63);
		assertEquals(res.toString(), "63");

		// RHS beyond upper boundary
		x = ULong.valueOf("8769067");
		res = x.or(ULong.valueOf("18446744073709551632"));
		assertEquals(res.toString(), "8769083");

		// Both beyond upper boundary
		x = ULong.valueOf("18446744073709551647");
		res = x.or(ULong.valueOf("184467440737095516321"));
		assertEquals(res.toString(), "191");

		// LHS below lower boundary
		x = ULong.valueOf("-906");
		res = x.or(ULong.valueOf("1024"));
		assertEquals(res.toString(), "18446744073709550710");
		res = x.or(1024);
		assertEquals(res.toString(), "18446744073709550710");

		// RHS below lower boundary
		x = ULong.valueOf("1024");
		res = x.or(ULong.valueOf("-9087"));
		assertEquals(res.toString(), "18446744073709542529");
		res = x.or(-9087);
		assertEquals(res.toString(), "18446744073709542529");

		// Both below lower boundary
		x = ULong.valueOf("-2048");
		res = x.or(ULong.valueOf("-9089"));
		assertEquals(res.toString(), "18446744073709550719");
		res = x.or(-9089);
		assertEquals(res.toString(), "18446744073709550719");

		// With zero
		x = ULong.valueOf("2048");
		res = x.or(ULong.valueOf(0));
		assertEquals(res.toString(), "2048");
		res = x.or(0);
		assertEquals(res.toString(), "2048");
		x = ULong.valueOf(0);
		res = x.or(ULong.valueOf("2048"));
		assertEquals(res.toString(), "2048");
		res = x.or(2048);
		assertEquals(res.toString(), "2048");

		assertTrue(res != x);
	}

	@Test
	public void testComplement() {
		// Within range 
		ULong x = ULong.valueOf("1234");
		ULong res = x.complement();
		assertEquals(res.toString(), "18446744073709550381");

		// With zero
		x = ULong.valueOf("0");
		res = x.complement();
		assertEquals(res.toString(), "18446744073709551615");

		// With lower than zero
		x = ULong.valueOf("-23");
		res = x.complement();
		assertEquals(res.toString(), "22");

		// At upper bound
		x = ULong.valueOf("18446744073709551615");
		res = x.complement();
		assertEquals(res.toString(), "0");

		// Beyond upper bound
		x = ULong.valueOf("18446744073709551622");
		res = x.complement();
		assertEquals(res.toString(), "18446744073709551609");
	}

	@Test
	public void testComparisonOperators() {
		// Within range, lhs > rhs
		ULong x = ULong.valueOf("1234");
		assertTrue(x.isGreaterThan(134));
		assertTrue(x.isGreaterThan(ULong.valueOf(134)));
		assertTrue(x.isGreaterThanOrEqualTo(134));
		assertTrue(x.isGreaterThanOrEqualTo(ULong.valueOf(134)));
		assertFalse(x.equals(134L));
		assertFalse(x.equals(ULong.valueOf(134)));
		assertFalse(x.isLesserThan(134));
		assertFalse(x.isLesserThan(ULong.valueOf(134)));
		assertFalse(x.isLesserThanOrEqualTo(134));
		assertFalse(x.isLesserThanOrEqualTo(ULong.valueOf(134)));

		// LHS at upper boundary, RHS in range
		x = ULong.valueOf("18446744073709551615");
		assertTrue(x.isGreaterThan(45678));
		assertTrue(x.isGreaterThan(ULong.valueOf(45678)));
		assertTrue(x.isGreaterThanOrEqualTo(45678));
		assertTrue(x.isGreaterThanOrEqualTo(ULong.valueOf(45678)));
		assertFalse(x.equals(45678));
		assertFalse(x.equals(ULong.valueOf(45678)));
		assertFalse(x.isLesserThan(45678));
		assertFalse(x.isLesserThan(ULong.valueOf(45678)));
		assertFalse(x.isLesserThanOrEqualTo(45678));
		assertFalse(x.isLesserThanOrEqualTo(ULong.valueOf(45678)));

		// LHS in range, RHS at upper boundary
		x = ULong.valueOf(45678);
		assertFalse(x.isGreaterThan(ULong.valueOf("18446744073709551615")));
		assertFalse(x.isGreaterThanOrEqualTo(ULong.valueOf("18446744073709551615")));
		assertFalse(x.equals(ULong.valueOf("18446744073709551615")));
		assertTrue(x.isLesserThan(ULong.valueOf("18446744073709551615")));
		assertTrue(x.isLesserThanOrEqualTo(ULong.valueOf("18446744073709551615")));

		// Both at upper boundary
		x = ULong.valueOf("18446744073709551615");
		assertTrue(x.equals(ULong.valueOf("18446744073709551615")));
		assertTrue(x.isGreaterThanOrEqualTo(ULong.valueOf("18446744073709551615")));
		assertTrue(x.isLesserThanOrEqualTo(ULong.valueOf("18446744073709551615")));
		assertFalse(x.isLesserThan(ULong.valueOf("18446744073709551615")));
		assertFalse(x.isGreaterThan(ULong.valueOf("18446744073709551615")));

		// Both at lower boundary
		x = ULong.valueOf(0);
		assertTrue(x.equals(0));
		assertTrue(x.equals(ULong.valueOf(0)));
		assertTrue(x.isLesserThanOrEqualTo(0));
		assertTrue(x.isLesserThanOrEqualTo(ULong.valueOf(0)));
		assertTrue(x.isGreaterThanOrEqualTo(0));
		assertTrue(x.isGreaterThanOrEqualTo(ULong.valueOf(0)));
		assertFalse(x.isLesserThan(0));
		assertFalse(x.isLesserThan(ULong.valueOf(0)));
		assertFalse(x.isGreaterThan(0));
		assertFalse(x.isGreaterThan(ULong.valueOf(0)));

		// LHS below lower boundary, RHS in range
		x = ULong.valueOf("-34");
		assertFalse(x.equals(34));
		assertFalse(x.equals(ULong.valueOf("34")));
		assertFalse(x.isLesserThanOrEqualTo(34));
		assertFalse(x.isLesserThanOrEqualTo(ULong.valueOf("34")));
		assertFalse(x.isLesserThan(34));
		assertFalse(x.isLesserThan(ULong.valueOf("34")));
		assertTrue(x.isGreaterThan(34));
		assertTrue(x.isGreaterThan(ULong.valueOf("34")));
		assertTrue(x.isGreaterThanOrEqualTo(34));
		assertTrue(x.isGreaterThanOrEqualTo(ULong.valueOf("34")));

		// LHS in range, RHS below lower boundary
		x = ULong.valueOf("56743");
		assertFalse(x.equals(-509));
		assertFalse(x.equals(ULong.valueOf("-509")));
		assertTrue(x.isLesserThanOrEqualTo(-509));
		assertTrue(x.isLesserThanOrEqualTo(ULong.valueOf("-509")));
		assertTrue(x.isLesserThan(-509));
		assertTrue(x.isLesserThan(ULong.valueOf("-509")));
		assertFalse(x.isGreaterThan(-509));
		assertFalse(x.isGreaterThan(ULong.valueOf("-509")));
		assertFalse(x.isGreaterThanOrEqualTo(-509));
		assertFalse(x.isGreaterThanOrEqualTo(ULong.valueOf("-509")));

		// Both below lower boundary, LHS lesser than RHS 
		x = ULong.valueOf("-56743");
		assertFalse(x.equals(-509));
		assertFalse(x.equals(ULong.valueOf("-509")));
		assertTrue(x.isLesserThanOrEqualTo(-509));
		assertTrue(x.isLesserThanOrEqualTo(ULong.valueOf("-509")));
		assertTrue(x.isLesserThan(-509));
		assertTrue(x.isLesserThan(ULong.valueOf("-509")));
		assertFalse(x.isGreaterThan(-509));
		assertFalse(x.isGreaterThan(ULong.valueOf("-509")));
		assertFalse(x.isGreaterThanOrEqualTo(-509));
		assertFalse(x.isGreaterThanOrEqualTo(ULong.valueOf("-509")));

		// Both below lower boundary, LHS greater than RHS 
		x = ULong.valueOf("-509");
		assertFalse(x.equals(-56743));
		assertFalse(x.equals(ULong.valueOf("-56743")));
		assertFalse(x.isLesserThanOrEqualTo(-56743));
		assertFalse(x.isLesserThanOrEqualTo(ULong.valueOf("-56743")));
		assertFalse(x.isLesserThan(-56743));
		assertFalse(x.isLesserThan(ULong.valueOf("-56743")));
		assertTrue(x.isGreaterThan(-56743));
		assertTrue(x.isGreaterThan(ULong.valueOf("-56743")));
		assertTrue(x.isGreaterThanOrEqualTo(-56743));
		assertTrue(x.isGreaterThanOrEqualTo(ULong.valueOf("-56743")));
	}	
}

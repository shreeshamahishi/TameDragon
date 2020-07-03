package org.tamedragon.common.llvmir.math;

import static org.junit.Assert.assertEquals;
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

		// At boundary
		y = ULong.valueOf(2);
		res = x.add(y);
		assertEquals(res.toString(), "18446744073709551615");

		// Out of bounds
		y = ULong.valueOf(3);
		res = x.add(y);
		assertEquals(res.toString(), "0");

		y = ULong.valueOf(4);
		res = x.add(y);
		assertEquals(res.toString(), "1");

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

		// At boundary
		y = ULong.valueOf("18446744073709551613");
		res = x.subtract(y);
		assertEquals(res.toString(), "0");

		// Out of bounds
		y = ULong.valueOf("18446744073709551614");
		res = x.subtract(y);
		assertEquals(res.toString(), "18446744073709551615");

		y = ULong.valueOf("18446744073709551615");
		res = x.subtract(y);
		assertEquals(res.toString(), "18446744073709551614");

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

		// At boundary
		y = ULong.valueOf("5");
		res = x.mul(y);
		assertEquals(res.toString(), "18446744073709551615");

		// Out of bounds
		y = ULong.valueOf("6");
		res = x.mul(y);
		assertEquals(res.toString(), "3689348814741910322");

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

		// Numerator out of range
		x = ULong.valueOf("184467440737095516154");
		y = ULong.valueOf("2");
		res = x.div(y);
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

		// Numerator out of range
		x = ULong.valueOf("184467440737095516154");
		y = ULong.valueOf("2");
		res = x.modulo(y);
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
	
	/*
	@Test
	public void testXor() {
		// Within range 
		ULong x = ULong.valueOf("1234");
		ULong res = x.xor(ULong.valueOf(321));
		assertEquals(res.toString(), "4611686018427387901");

		// Both values at upper boundary
		x = ULong.valueOf("18446744073709551615");
		res = x.xor(ULong.valueOf("18446744073709551615"));
		assertEquals(res.toString(), "9223372036854775807");

		// Both values at lower boundary
		x = ULong.valueOf(0);
		res = x.xor(ULong.valueOf(0));
		assertEquals(res.toString(), "0");

		// Shift 63 bits
		x = ULong.valueOf("18446744073709551615");
		res = x.xor(63);
		assertEquals(res.toString(), "1");

		// Shift 64 bits
		x = ULong.valueOf("18446744073709551615");
		res = x.xor(64);
		assertEquals(res.toString(), "18446744073709551615");

		// Shift 65 bits
		x = ULong.valueOf("18446744073709551615");
		res = x.xor(65);
		assertEquals(res.toString(), "9223372036854775807");

		// Shift 131 bits
		x = ULong.valueOf("1024");
		res = x.xor(131);
		assertEquals(res.toString(), "128");

		// Shift 0 bits
		x = ULong.valueOf("1024");
		res = x.xor(0);
		assertEquals(res.toString(), "1024");

		// Shift -1 bits
		x = ULong.valueOf("1024");
		res = x.xor(-1);
		assertEquals(res.toString(), "0");

		// Shift -345 bits
		x = ULong.valueOf("1024");
		res = x.xor(-345);
		assertEquals(res.toString(), "0");
		
		assertTrue(res != x);
	}
	*/
	
}

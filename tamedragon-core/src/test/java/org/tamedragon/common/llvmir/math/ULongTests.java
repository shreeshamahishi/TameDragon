package org.tamedragon.common.llvmir.math;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;

import org.junit.Test;

public class ULongTests {

	@Test
	public void testAssignmentWithLongWithinAndOutsideRanges() {
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
	public void testAssignmentWithBigIntWithinAndOutsideRanges() {
		BigInteger bigInt = new BigInteger("18446744073709551615");
		ULong ulong = ULong.valueOf(bigInt);
		assertEquals(ulong.toString(), "18446744073709551615");

		bigInt = new BigInteger("-18446744073709551615");
		ulong = ULong.valueOf(bigInt);
		assertEquals(ulong.toString(), "1");

		// Outside maximum limit
		try {
			bigInt = new BigInteger("184467440737095516153");
			ulong = ULong.valueOf(bigInt);
			assertTrue(false);
		}
		catch(NumberFormatException nfe) {
			assertTrue(nfe.getMessage().equals("Integer constant too large"));
		}

		// Outside minimum limit
		try {
			bigInt = new BigInteger("-18446744073709551616");
			ulong = ULong.valueOf(bigInt);
			assertTrue(false);
		}
		catch(NumberFormatException nfe) {
			assertTrue(nfe.getMessage().equals("Integer constant too large"));
		}
	}
	
	@Test
	public void testAssignmentWithStringWithinAndOutsideRanges() {
		ULong ulong = ULong.valueOf("18446744073709551615");
		assertEquals(ulong.toString(), "18446744073709551615");

		ulong = ULong.valueOf("-18446744073709551615");
		assertEquals(ulong.toString(), "1");

		// Outside maximum limit
		try {
			ulong = ULong.valueOf("184467440737095516153");
			assertTrue(false);
		}
		catch(NumberFormatException nfe) {
			assertTrue(nfe.getMessage().equals("Integer constant too large"));
		}

		// Outside minimum limit
		try {
			ulong = ULong.valueOf("-18446744073709551616");
			assertTrue(false);
		}
		catch(NumberFormatException nfe) {
			assertTrue(nfe.getMessage().equals("Integer constant too large"));
		}
	}
	
	@Test
	public void testWrapping() {
		ULong x = ULong.valueOf("18446744073709551615");
		ULong y = ULong.valueOf(1);
		ULong res = x.add(y);
		assertEquals(res.toString(), "0");
		
		y = ULong.valueOf(2);
		res = x.add(y);
		assertEquals(res.toString(), "1");
		
		y = ULong.valueOf(25);
		res = x.mul(y);
		assertEquals(res.toString(), "18446744073709551591");
	}
}

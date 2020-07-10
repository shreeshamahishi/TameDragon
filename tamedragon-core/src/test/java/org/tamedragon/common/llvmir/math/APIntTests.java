package org.tamedragon.common.llvmir.math;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;

public class APIntTests {

	@Test
	public void testAPIntConstructionWithVariousRadixes() {
		// TODO

	}

	@Test
	public void testAPIntSingleWordInitByteSizesAndULongAndSignFlag() {

		// 1-byte 0
		APInt temp = new APInt(1, ULong.valueOf("0"), false);
		assertTrue(temp.toString().equals("0"));
		assertTrue(temp.toString(10, false, false).equals("0"));
		assertTrue(temp.getUnsignedVals()[0].toString().equals("0"));

		// 1-byte 1
		temp = new APInt(1, ULong.valueOf("1"), false);
		assertTrue(temp.toString().equals("-1"));
		assertTrue(temp.toString(10, false, false).equals("1"));
		assertTrue(temp.getUnsignedVals()[0].toString().equals("1"));

		// 1-byte beyond upper bound
		temp = new APInt(1, ULong.valueOf("8"), false);
		assertTrue(temp.toString().equals("0"));
		assertTrue(temp.toString(10, false, false).equals("0"));
		assertTrue(temp.getUnsignedVals()[0].toString().equals("0"));

		// 1-byte negative
		temp = new APInt(1, ULong.valueOf("-13"), false);
		assertTrue(temp.toString().equals("-1"));
		assertTrue(temp.toString(10, false, false).equals("1"));
		assertTrue(temp.getUnsignedVals()[0].toString().equals("1"));

		// Within range positive number
		temp = new APInt(8, ULong.valueOf("100"), false);
		assertTrue(temp.toString().equals("100"));
		assertTrue(temp.toString(10, false, false).equals("100"));
		assertTrue(temp.getUnsignedVals()[0].toString().equals("100"));

		// Negative number 
		temp = new APInt(8, ULong.valueOf("-9"), false);
		assertTrue(temp.toString().equals("-9"));
		assertTrue(temp.toString(10, false, false).equals("247"));
		assertTrue(temp.getUnsignedVals()[0].toString().equals("247"));

		// Positive number outside range
		temp = new APInt(8, ULong.valueOf("325"), false);
		assertTrue(temp.toString().equals("69"));
		assertTrue(temp.toString(10, false, false).equals("69"));
		assertTrue(temp.getUnsignedVals()[0].toString().equals("69"));

		// Non-byte type bit width (5) in range
		temp = new APInt(5, ULong.valueOf("30"), false);
		assertTrue(temp.toString().equals("-2"));
		assertTrue(temp.toString(10, false, false).equals("30"));
		assertTrue(temp.getUnsignedVals()[0].toString().equals("30"));

		// Non-byte type bit width (5) at upper bound
		temp = new APInt(5, ULong.valueOf("31"), false);
		assertTrue(temp.toString().equals("-1"));
		assertTrue(temp.toString(10, false, false).equals("31"));
		assertTrue(temp.getUnsignedVals()[0].toString().equals("31"));

		// Arbitrary bit width (5) beyond upper bound
		temp = new APInt(5, ULong.valueOf("32"), false);
		assertTrue(temp.toString().equals("0"));
		assertTrue(temp.toString(10, false, false).equals("0"));
		assertTrue(temp.getUnsignedVals()[0].toString().equals("0"));

		// 64 bit long max value
		temp = new APInt(64, ULong.valueOf("9223372036854775807"), false);
		assertTrue(temp.toString().equals("9223372036854775807"));
		assertTrue(temp.toString(10, false, false).equals("9223372036854775807"));
		assertTrue(temp.getUnsignedVals()[0].toString().equals("9223372036854775807"));

		// 64 bit long beyond max value
		temp = new APInt(64, ULong.valueOf("9223372036854775812"), false);
		assertTrue(temp.toString().equals("-9223372036854775804"));
		assertTrue(temp.toString(10, false, false).equals("9223372036854775812"));
		assertTrue(temp.getUnsignedVals()[0].toString().equals("9223372036854775812"));

		// 64 bit long 0
		temp = new APInt(64, ULong.valueOf("0"), false);
		assertTrue(temp.toString().equals("0"));
		assertTrue(temp.toString(10, false, false).equals("0"));
		assertTrue(temp.getUnsignedVals()[0].toString().equals("0"));

		// 64 bit long negative value
		temp = new APInt(64, ULong.valueOf("-78"), false);
		assertTrue(temp.toString().equals("-78"));
		assertTrue(temp.toString(10, false, false).equals("18446744073709551538"));
		assertTrue(temp.getUnsignedVals()[0].toString().equals("18446744073709551538"));

		// 64 bit unsigned max value
		temp = new APInt(64, ULong.valueOf("18446744073709551615"), false);
		assertTrue(temp.toString().equals("-1"));
		assertTrue(temp.toString(10, false, false).equals("18446744073709551615"));
		assertTrue(temp.getUnsignedVals()[0].toString().equals("18446744073709551615"));

		// 64 bit unsigned beyond max value
		temp = new APInt(64, ULong.valueOf("18446744073709551623"), false);
		assertTrue(temp.toString().equals("7"));
		assertTrue(temp.toString(10, false, false).equals("7"));
		assertTrue(temp.getUnsignedVals()[0].toString().equals("7"));
	}
	
	@Test
	public void testAdditionOperationsOnSingleWordAPInt() {
		
		BigDecimal bd0 = new BigDecimal("24.00");
		BigDecimal bd1 = new BigDecimal("24.50");
		BigDecimal res1 = bd0.add(bd1);
		System.out.println(res1);
		
		// Something in range
		APInt temp1 = new APInt(8, ULong.valueOf(3), false);
		APInt temp2 = new APInt(8, ULong.valueOf(21), false);
		APInt res = temp1.add(temp2);
		assertTrue(res.toString().equals("24"));
		
	}
	
	@Test
	public void testAPIntConstructionWithStringAndRadix10AndByteSizes() {
		
		BigDecimal bd0 = new BigDecimal(24.00);
		bd0.setScale(2, RoundingMode.HALF_UP);
		BigDecimal bd1 = new BigDecimal(24.51);
		bd1.setScale(2, RoundingMode.HALF_UP);
		BigDecimal bd2 = new BigDecimal(24.5000002);
		BigDecimal bd3 = new BigDecimal(24.5000003);
		
		BigDecimal fbo = new BigDecimal(48.51);
		
		
		BigDecimal res = bd0.add(bd1);
		res.setScale(2, RoundingMode.HALF_UP);
		System.out.println(res);
		
		if(fbo.equals(res)) {
			System.out.println("Equals	 ");
		}
		else {
			System.out.println("NOt Equals	 ");
		}
		
		res.setScale(2, RoundingMode.HALF_UP);
		System.out.println(res);
		
		
		
		System.out.println("BD0 scale " + bd0.scale());
		System.out.println("BD1 scale " + bd1.scale());
		System.out.println("BD2 scale " + bd2.scale());
		System.out.println("BD3 scale " + bd3.scale());
		
		bd0.setScale(5);
		
		System.out.println("Equals returns: " + bd1.equals(bd2));
		System.out.println("compareTo returns: " + bd1.compareTo(bd2));
		

		// Within range positive number
		APInt temp = new APInt(8, "100", 10);
		assertTrue(temp.getUnsignedVals()[0].toString().equals("100"));

		// Negative number
		temp = new APInt(8, "-9", 10);
		assertTrue(temp.getUnsignedVals()[0].toString().equals("247"));

		// Positive number outside range
		temp = new APInt(8, "325", 10);
		System.out.println(temp.getUnsignedVals());
		assertTrue(temp.getUnsignedVals()[0].toString().equals("69"));
	}

	@Test
	public void testAPIntConstructionWithStringAndRadix10AndNonnByteSizes() {

		// Within range positive number
		APInt temp = new APInt(3, "5", 10);
		System.out.println(temp.toString());
		assertTrue(temp.toString().equals("-3"));
	}

	@Test
	public void testMultiParts() {

		// Value fits well in one word, 1 part
		APInt apInt = new APInt(64, "23", 10);
		assertTrue(apInt.unsignedVals.length == 1);
		assertTrue(apInt.unsignedVals[0].equals(ULong.valueOf(23)));

		// Value fits exactly in one word, 1 part
		apInt = new APInt(64, "18446744073709551615", 10);
		assertTrue(apInt.unsignedVals.length == 1);
		assertTrue(apInt.unsignedVals[0].equals(ULong.valueOf("18446744073709551615")));

		// Value should overflow with 1 part
		apInt = new APInt(64, "18446744073709551622", 10);
		assertTrue(apInt.unsignedVals.length == 1);
		assertTrue(apInt.unsignedVals[0].equals(ULong.valueOf("6")));

		// Exactly 1 word in 2 parts
		apInt = new APInt(128, "18446744073709551615", 10);
		String str = apInt.toString();
		System.out.println(str);
		assertTrue(apInt.unsignedVals.length == 2);
		assertTrue(apInt.unsignedVals[0].equals(ULong.valueOf("18446744073709551615")));
		assertTrue(apInt.unsignedVals[1].equals(ULong.valueOf(0)));
		assertTrue(apInt.toString().equals("18446744073709551615"));

		// Fits in two words, in two parts
		apInt = new APInt(128, "18446744073709551619", 10);
		assertTrue(apInt.unsignedVals.length == 2);
		assertTrue(apInt.unsignedVals[0].equals(ULong.valueOf(3)));
		assertTrue(apInt.unsignedVals[1].equals(ULong.valueOf(1)));
		assertTrue(apInt.toString().equals("18446744073709551619"));

		// Fits in two words, in three parts
		apInt = new APInt(192, "18446744073709551622", 10);
		assertTrue(apInt.unsignedVals.length == 3);
		assertTrue(apInt.unsignedVals[0].equals(ULong.valueOf(6)));
		assertTrue(apInt.unsignedVals[1].equals(ULong.valueOf(1)));
		assertTrue(apInt.unsignedVals[2].equals(ULong.valueOf(0)));
		assertTrue(apInt.toString().equals("18446744073709551622"));

		// Fits in two words, in four parts
		apInt = new APInt(256, "18446744073709551627", 10);
		assertTrue(apInt.unsignedVals.length == 4);
		assertTrue(apInt.unsignedVals[0].equals(ULong.valueOf(11)));
		assertTrue(apInt.unsignedVals[1].equals(ULong.valueOf(1)));
		assertTrue(apInt.unsignedVals[2].equals(ULong.valueOf(0)));
		assertTrue(apInt.unsignedVals[3].equals(ULong.valueOf(0)));
		assertTrue(apInt.toString().equals("18446744073709551627"));
	}

	@Test
	public void testMultiPartOperations() {
		APInt apInt = new APInt(128, "18446744073709551615", 10);
		assertTrue(apInt.unsignedVals[0].equals(ULong.valueOf("18446744073709551615")));
		assertTrue(apInt.unsignedVals[1].equals(ULong.valueOf(0)));

		// Initial value with more than one part
		apInt = new APInt(128, "18446744073709551619", 10);
		assertTrue(apInt.unsignedVals[0].equals(ULong.valueOf(3)));
		assertTrue(apInt.unsignedVals[1].equals(ULong.valueOf(1)));

		// Multiplications
		apInt = new APInt(128, "18446744073709551615", 10);
		ULong multiplier = ULong.valueOf(2);
		int overflow = APInt.tcMultiplyPart(apInt.unsignedVals, apInt.unsignedVals, multiplier, ULong.valueOf(0), 2, 2, false);
		assertTrue(overflow == 0);
		assertTrue(apInt.unsignedVals[0].equals(ULong.valueOf("18446744073709551614")));
		assertTrue(apInt.unsignedVals[1].equals(ULong.valueOf(1)));

		apInt = new APInt(128, "18446744073709551615", 10);
		multiplier = ULong.valueOf(3);
		overflow = APInt.tcMultiplyPart(apInt.unsignedVals, apInt.unsignedVals, multiplier, ULong.valueOf(0), 2, 2, false);
		assertTrue(overflow == 0);
		assertTrue(apInt.unsignedVals[0].equals(ULong.valueOf("18446744073709551613")));
		assertTrue(apInt.unsignedVals[1].equals(ULong.valueOf(2)));

		// Multiplications with overflow
		apInt = new APInt(128, "18446744073709551619", 10);
		multiplier = ULong.valueOf("18446744073709551615");
		overflow = APInt.tcMultiplyPart(apInt.unsignedVals, apInt.unsignedVals, multiplier, ULong.valueOf(0), 2, 2, false);
		assertTrue(overflow == 1);
		assertTrue(apInt.unsignedVals[0].equals(ULong.valueOf("18446744073709551613")));
		assertTrue(apInt.unsignedVals[1].equals(ULong.valueOf(1)));

		// Additions
		apInt = new APInt(128, "18446744073709551615", 10);
		ULong addVal = ULong.valueOf(56);
		ULong carry = APInt.tcAddPart(apInt.unsignedVals, addVal, 2);
		assertTrue(carry.equals(ULong.valueOf(0)));
		assertTrue(apInt.unsignedVals[0].equals(ULong.valueOf(55)));
		assertTrue(apInt.unsignedVals[1].equals(ULong.valueOf(1)));

		apInt = new APInt(128, "18446744073709551632", 10);
		addVal = ULong.valueOf(47);
		carry = APInt.tcAddPart(apInt.unsignedVals, addVal, 2);
		assertTrue(carry.equals(ULong.valueOf(0)));
		assertTrue(apInt.unsignedVals[0].equals(ULong.valueOf(63)));
		assertTrue(apInt.unsignedVals[1].equals(ULong.valueOf(1)));
	}
}

package org.tamedragon.common.llvmir.math;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class APIntTests {

	@Test
	public void testAPIntConstructionWithVariousRadixes() {
		// TODO

	}

	@Test
	public void testAPIntConstructionWithStringAndRadix10AndByteSizes() {

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

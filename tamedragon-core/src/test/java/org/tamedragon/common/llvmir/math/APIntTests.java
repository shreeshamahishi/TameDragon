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
	public void testSingleWordClearUnusedBits() {
		APInt apInt = new APInt(7, ULong.valueOf("126"), false);
		apInt.clearUnusedBits();
		assertTrue(apInt.toString(10, false, false).equals("126"));

		apInt = new APInt(7, ULong.valueOf("127"), false);
		apInt.clearUnusedBits();
		assertTrue(apInt.toString(10, false, false).equals("127"));

		// Test indirectly through an overflow
		apInt = new APInt(7, ULong.valueOf("126"), false);
		APInt apInt2 = new APInt(7, ULong.valueOf("5"), false);
		APInt result = apInt.add(apInt2);
		assertTrue(result.toString(10, false, false).equals("3"));
	}

	@Test
	public void testSingleWordAdditionOfAPInts() {
		// Both in range and result also in range
		APInt apInt1 = new APInt(32, ULong.valueOf("0"), false);
		APInt apInt2 = new APInt(32, ULong.valueOf("0"), false);
		APInt result = apInt1.add(apInt2);
		assertTrue(result != apInt1);
		assertTrue(result != apInt2);
		assertTrue(apInt1 != apInt2);
		assertTrue(result.toString().equals("0"));
		assertTrue(apInt1.toString().equals("0"));
		assertTrue(apInt2.toString().equals("0"));

		apInt1 = new APInt(32, ULong.valueOf("42"), false);
		apInt2 = new APInt(32, ULong.valueOf("54"), false);
		result = apInt1.add(apInt2);
		assertTrue(result.toString().equals("96"));

		// At upper bound
		apInt1 = new APInt(64, ULong.valueOf("18446744073709550001"), false);
		apInt2 = new APInt(64, ULong.valueOf("1614"), false);
		result = apInt1.add(apInt2);
		assertTrue(result.toString().equals("-1"));
		assertTrue(result.toString(10, false, false).equals("18446744073709551615"));

		// Both in range and result overflows
		apInt1 = new APInt(64, ULong.valueOf("18446744073709550001"), false);
		apInt2 = new APInt(64, ULong.valueOf("1618"), false);
		result = apInt1.add(apInt2);
		assertTrue(result.toString().equals("3"));
		assertTrue(result.toString(10, false, false).equals("3"));

		// One above upper range and the other in range
		apInt1 = new APInt(32, ULong.valueOf("4294967299"), false);
		apInt2 = new APInt(32, ULong.valueOf("109"), false);
		result = apInt1.add(apInt2);
		assertTrue(result.toString(10, false, false).equals("112"));	

		// Both above upper range
		apInt1 = new APInt(8, ULong.valueOf("257"), false);
		apInt2 = new APInt(8, ULong.valueOf("258"), false);
		result = apInt1.add(apInt2);
		assertTrue(result.toString().equals("3"));
		assertTrue(result.toString(10, false, false).equals("3"));

		// Arbitrary bit width 5, no overflow
		apInt1 = new APInt(5, ULong.valueOf("15"), false);
		apInt2 = new APInt(5, ULong.valueOf("14"), false);
		result = apInt1.add(apInt2);
		assertTrue(result.toString().equals("-3"));
		assertTrue(result.toString(10, false, false).equals("29"));

		// Arbitrary bit width 5, with overflow
		apInt1 = new APInt(5, ULong.valueOf("15"), false);
		apInt2 = new APInt(5, ULong.valueOf("18"), false);
		result = apInt1.add(apInt2);
		assertTrue(result.toString().equals("1"));
		assertTrue(result.toString(10, false, false).equals("1"));
	}

	@Test
	public void testSingleWordAdditionOfAPIntWithULong() {
		// In range and result also in range
		APInt apInt1 = new APInt(32, ULong.valueOf("0"), false);
		APInt result = apInt1.add(ULong.valueOf(0));
		assertTrue(result != apInt1);
		assertTrue(result.toString().equals("0"));
		assertTrue(result.toString(10, false, false).equals("0"));

		apInt1 = new APInt(32, ULong.valueOf("42"), false);
		result = apInt1.add(ULong.valueOf(54));
		assertTrue(result.toString().equals("96"));
		assertTrue(result.toString(10, false, false).equals("96"));

		// At upper bound
		apInt1 = new APInt(64, ULong.valueOf("18446744073709550001"), false);
		result = apInt1.add(ULong.valueOf(1614));
		assertTrue(result.toString().equals("-1"));
		assertTrue(result.toString(10, false, false).equals("18446744073709551615"));

		// Both in range and result overflows
		apInt1 = new APInt(64, ULong.valueOf("18446744073709550001"), false);
		result = apInt1.add(ULong.valueOf(1618));
		assertTrue(result.toString().equals("3"));
		assertTrue(result.toString(10, false, false).equals("3"));

		// One above upper range and the other in range
		apInt1 = new APInt(32, ULong.valueOf("4294967299"), false);
		result = apInt1.add(ULong.valueOf(109));
		assertTrue(result.toString().equals("112"));
		assertTrue(result.toString(10, false, false).equals("112"));	

		// Both above upper range and the other in range
		apInt1 = new APInt(8, ULong.valueOf("257"), false);
		result = apInt1.add(ULong.valueOf(258));
		assertTrue(result.toString().equals("3"));
		assertTrue(result.toString(10, false, false).equals("3"));

		// Arbitrary bit width 5, no overflow
		apInt1 = new APInt(5, ULong.valueOf("15"), false);
		result = apInt1.add(ULong.valueOf(14));
		assertTrue(result.toString().equals("-3"));
		assertTrue(result.toString(10, false, false).equals("29"));

		// Arbitrary bit width 5, with overflow
		apInt1 = new APInt(5, ULong.valueOf("15"), false);
		result = apInt1.add(ULong.valueOf(18));
		assertTrue(result.toString().equals("1"));
		assertTrue(result.toString(10, false, false).equals("1"));
	}

	@Test
	public void testSingleWordSubtraction() {
		// Both in range and result also in range
		APInt apInt1 = new APInt(32, ULong.valueOf("0"), false);
		APInt apInt2 = new APInt(32, ULong.valueOf("0"), false);
		APInt result = apInt1.subtract(apInt2);
		assertTrue(result != apInt1);
		assertTrue(result != apInt2);
		assertTrue(apInt1 != apInt2);
		assertTrue(result.toString(10, false, false).equals("0"));
		assertTrue(result.toString(10, true, false).equals("0"));

		apInt1 = new APInt(32, ULong.valueOf("54"), false);
		apInt2 = new APInt(32, ULong.valueOf("42"), false);
		result = apInt1.subtract(apInt2);
		assertTrue(result.toString(10, false, false).equals("12"));
		assertTrue(result.toString(10, true, false).equals("12"));

		// Both in range and result underflow
		apInt1 = new APInt(32, ULong.valueOf("43"), false);
		apInt2 = new APInt(32, ULong.valueOf("56"), false);
		result = apInt1.subtract(apInt2);
		assertTrue(result.toString(10, false, false).equals("4294967283"));
		assertTrue(result.toString(10, true, false).equals("-13"));

		// Both operands at upper bound
		apInt1 = new APInt(64, ULong.valueOf("18446744073709551615"), false);
		apInt2 = new APInt(64, ULong.valueOf("18446744073709551615"), false);
		result = apInt1.subtract(apInt2);
		assertTrue(result.toString(10, false, false).equals("0"));
		assertTrue(result.toString(10, true, false).equals("0"));

		// First operand at upper bound, second operand below upper bound
		apInt1 = new APInt(64, ULong.valueOf("18446744073709551615"), false);
		apInt2 = new APInt(64, ULong.valueOf("614"), false);
		result = apInt1.subtract(apInt2);
		assertTrue(result.toString(10, false, false).equals("18446744073709551001"));
		assertTrue(result.toString(10, true, false).equals("-615"));

		// First operand beyond upper bound, second operand below upper bound
		apInt1 = new APInt(64, ULong.valueOf("18446744073709551632"), false);
		apInt2 = new APInt(64, ULong.valueOf("1618"), false);
		result = apInt1.subtract(apInt2);
		assertTrue(result.toString(10, false, false).equals("18446744073709550014"));
		assertTrue(result.toString(10, true, false).equals("-1602"));

		// First operand at upper bound, second operand above upper bound
		apInt1 = new APInt(64, ULong.valueOf("18446744073709551615"), false);
		apInt2 = new APInt(64, ULong.valueOf("18446744073709551633"), false);
		result = apInt1.subtract(apInt2);
		assertTrue(result.toString(10, false, false).equals("18446744073709551598"));
		assertTrue(result.toString(10, true, false).equals("-18"));

		// Both operands beyond upper bound and first is greater than the second
		apInt1 = new APInt(64, ULong.valueOf("18446744073709551645"), false);
		apInt2 = new APInt(64, ULong.valueOf("18446744073709551633"), false);
		result = apInt1.subtract(apInt2);
		assertTrue(result.toString(10, false, false).equals("12"));
		assertTrue(result.toString(10, true, false).equals("12"));

		// Both operands beyond upper bound and first is greater than the second
		apInt1 = new APInt(64, ULong.valueOf("18446744073709551656"), false);
		apInt2 = new APInt(64, ULong.valueOf("18446744073709551693"), false);
		result = apInt1.subtract(apInt2);
		assertTrue(result.toString(10, false, false).equals("18446744073709551579"));
		assertTrue(result.toString(10, true, false).equals("-37"));
	}

	@Test
	public void testSingleWordSubtractionOfULongFromAPInt() {
		// Both in range and result also in range
		APInt apInt1 = new APInt(32, ULong.valueOf("0"), false);
		APInt result = apInt1.subtract(ULong.valueOf(0));
		assertTrue(result != apInt1);
		assertTrue(result.toString(10, false, false).equals("0"));
		assertTrue(result.toString(10, true, false).equals("0"));

		apInt1 = new APInt(32, ULong.valueOf("54"), false);
		result = apInt1.subtract(ULong.valueOf("42"));
		assertTrue(result.toString(10, false, false).equals("12"));
		assertTrue(result.toString(10, true, false).equals("12"));

		// Both in range and result underflow
		apInt1 = new APInt(32, ULong.valueOf("43"), false);
		result = apInt1.subtract(ULong.valueOf("56"));
		assertTrue(result.toString(10, false, false).equals("4294967283"));
		assertTrue(result.toString(10, true, false).equals("-13"));

		// Both operands at upper bound
		apInt1 = new APInt(64, ULong.valueOf("18446744073709551615"), false);
		result = apInt1.subtract(ULong.valueOf("18446744073709551615"));
		assertTrue(result.toString(10, false, false).equals("0"));
		assertTrue(result.toString(10, true, false).equals("0"));

		// First operand at upper bound, second operand below upper bound
		apInt1 = new APInt(64, ULong.valueOf("18446744073709551615"), false);
		result = apInt1.subtract(ULong.valueOf("614"));
		assertTrue(result.toString(10, false, false).equals("18446744073709551001"));
		assertTrue(result.toString(10, true, false).equals("-615"));

		// First operand beyond upper bound, second operand below upper bound
		apInt1 = new APInt(64, ULong.valueOf("18446744073709551632"), false);
		result = apInt1.subtract(ULong.valueOf("1618"));
		assertTrue(result.toString(10, false, false).equals("18446744073709550014"));
		assertTrue(result.toString(10, true, false).equals("-1602"));
	}

	@Test
	public void testSingleWordMultiplicationOfAPInts() {
		// Both in range and result also in range
		APInt apInt1 = new APInt(32, ULong.valueOf(0), false);
		APInt apInt2 = new APInt(32, ULong.valueOf(0), false);
		APInt result = apInt1.mul(apInt2);

		assertTrue(result != apInt1);
		assertTrue(result != apInt2);

		assertTrue(result.toString().equals("0"));
		assertTrue(result.toString(10, false, false).equals("0"));

		apInt1 = new APInt(32, ULong.valueOf(54), false);
		apInt2 = new APInt(32, ULong.valueOf(42), false);
		result = apInt1.mul(apInt2);
		assertTrue(result.toString().equals("2268"));
		assertTrue(result.toString(10, false, false).equals("2268"));

		// Result at upper bound
		apInt1 = new APInt(64, ULong.valueOf("3689348814741910323"), false);
		apInt2 = new APInt(64, ULong.valueOf(5), false);
		result = apInt1.mul(apInt2);
		assertTrue(result.toString().equals("-1"));
		assertTrue(result.toString(10, false, false).equals("18446744073709551615"));

		// Both in range and result overflows
		apInt1 = new APInt(64, ULong.valueOf("18446744073709551613"), false);
		apInt2 = new APInt(64, ULong.valueOf(12), false);
		result = apInt1.mul(apInt2);
		assertTrue(result.toString().equals("-36"));
		assertTrue(result.toString(10, false, false).equals("18446744073709551580"));

		// One above upper range and the other in range
		apInt1 = new APInt(32, ULong.valueOf("4294967299"), false);
		apInt2 =  new APInt(32, ULong.valueOf(2), false);
		result = apInt1.mul(apInt2);
		assertTrue(result.toString().equals("6"));
		assertTrue(result.toString(10, false, false).equals("6"));

		// Both above upper range
		apInt1 =  new APInt(8, ULong.valueOf(257), false);
		apInt2 =  new APInt(8, ULong.valueOf(258), false);
		result = apInt1.mul(apInt2);
		assertTrue(result.toString().equals("2"));
		assertTrue(result.toString(10, false, false).equals("2"));

		// Arbitrary bit width 5, no overflow
		apInt1 =  new APInt(5, ULong.valueOf(12), false);
		apInt2 =  new APInt(5, ULong.valueOf(2), false);
		result = apInt1.mul(apInt2);
		assertTrue(result.toString().equals("-8"));
		assertTrue(result.toString(10, false, false).equals("24"));

		// Arbitrary bit width 5, with overflow
		apInt1 =  new APInt(5, ULong.valueOf(3), false);
		apInt2 =  new APInt(5, ULong.valueOf(15), false);
		result = apInt1.mul(apInt2);
		assertTrue(result.toString().equals("13"));
		assertTrue(result.toString(10, false, false).equals("13"));
	}

	@Test
	public void testSingleWordMultiplicationOfAPIntWithULong() {
		// Both in range and result also in range
		APInt apInt = new APInt(32, ULong.valueOf(0), false);
		APInt result = apInt.mul(ULong.valueOf(0));
		assertTrue(result.toString(10, false, false).equals("0"));

		apInt = new APInt(32, ULong.valueOf(54), false);
		result = apInt.mul(ULong.valueOf(42));
		assertTrue(result != apInt);
		assertTrue(result.toString(10, false, false).equals("2268"));

		// Result at upper bound
		apInt = new APInt(64, ULong.valueOf("3689348814741910323"), false);
		result = apInt.mul(ULong.valueOf(5));
		assertTrue(result.toString(10, false, false).equals("18446744073709551615"));

		// Both in range and result overflows
		apInt = new APInt(64, ULong.valueOf("18446744073709551613"), false);
		result = apInt.mul(ULong.valueOf(12));
		assertTrue(result.toString(10, false, false).equals("18446744073709551580"));

		// One above upper range and the other in range
		apInt = new APInt(32, ULong.valueOf("4294967299"), false);
		result = apInt.mul(ULong.valueOf(2));
		assertTrue(result.toString(10, false, false).equals("6"));

		// Arbitrary bit width 5, no overflow
		apInt = new APInt(5, ULong.valueOf(12), false);
		result = apInt.mul(ULong.valueOf(2));
		assertTrue(result.toString(10, false, false).equals("24"));

		// Arbitrary bit width 5, with overflow
		apInt = new APInt(5, ULong.valueOf(3), false);
		result = apInt.mul(ULong.valueOf(15));
		assertTrue(result.toString(10, false, false).equals("13"));
	}

	@Test
	public void testSingleWordUnsignedDivisionOfAPInts() {
		// Division of zero by a non-zero number
		APInt apInt1 = new APInt(32, ULong.valueOf(0), false);
		APInt apInt2 = new APInt(32, ULong.valueOf(10), false);

		APInt result = apInt1.udiv(apInt2);
		assertTrue(result.toString(10, false, false) .equals("0"));

		// Division of a number by zero
		apInt1 = new APInt(32, ULong.valueOf(8), false);
		apInt2 = new APInt(32, ULong.valueOf(0), false);
		
		try {
			result = apInt1.udiv(apInt2);
			assertTrue(false);
		}
		catch(Exception e) {
			assertTrue(e.getMessage().equals("Divide by zero?"));
		}

		// Exact division with numerator below upper bound
		apInt1 = new APInt(64, ULong.valueOf(45), false);
		apInt2 = new APInt(64, ULong.valueOf(9), false);
		result = apInt1.udiv(apInt2);
		assertTrue(result.toString(10, false, false) .equals("5"));

		// Inexact division with numerator below upper bound
		apInt1 = new APInt(64, ULong.valueOf(19), false);
		apInt2 = new APInt(64, ULong.valueOf(4), false);
		result = apInt1.udiv(apInt2);
		assertTrue(result.toString(10, false, false) .equals("4"));

		// Both in range and denom > nume
		apInt1 = new APInt(64, ULong.valueOf(5), false);
		apInt2 = new APInt(64, ULong.valueOf(95), false);
		result = apInt1.udiv(apInt2);
		assertTrue(result.toString(10, false, false) .equals("0"));

		// Numerator at upper bound and denom in range
		apInt1 = new APInt(64, ULong.valueOf("18446744073709551615"), false);
		apInt2 = new APInt(64, ULong.valueOf(5), false);
		result = apInt1.udiv(apInt2);
		assertTrue(result.toString(10, false, false) .equals("3689348814741910323"));

		// Both at boundary
		apInt1 = new APInt(64, ULong.valueOf("18446744073709551615"), false);
		apInt2 = new APInt(64, ULong.valueOf("18446744073709551615"), false);
		result = apInt1.udiv(apInt2);
		assertTrue(result.toString(10, false, false) .equals("1"));

		// Numerator beyond upper bound, denom in range
		apInt1 = new APInt(64, ULong.valueOf("18446744073709551618"), false);
		apInt2 = new APInt(64, ULong.valueOf(2), false);
		result = apInt1.udiv(apInt2);
		assertTrue(result.toString(10, false, false) .equals("1"));

		// Denominator beyond upper bound, numerator in range
		apInt1 = new APInt(64, ULong.valueOf(2), false);
		apInt2 = new APInt(64, ULong.valueOf("18446744073709551618"), false);
		result = apInt1.udiv(apInt2);
		assertTrue(result.toString(10, false, false) .equals("1"));

		// Both beyond range, num > denom
		apInt1 = new APInt(64, ULong.valueOf("184467440737095516192"), false);
		apInt2 = new APInt(64, ULong.valueOf("18446744073709551618"), false);
		result = apInt1.udiv(apInt2);
		assertTrue(result.toString(10, false, false) .equals("16"));

		// Both beyond range, denom > num
		apInt1 = new APInt(64, ULong.valueOf("18446744073709551618"), false);
		apInt2 = new APInt(64, ULong.valueOf("184467440737095516193"), false);
		result = apInt1.udiv(apInt2);
		assertTrue(result.toString(10, false, false) .equals("0"));

		// Arbitrary bit width 5, exact division
		apInt1 =  new APInt(5, ULong.valueOf(12), false);
		apInt2 = new APInt(5, ULong.valueOf(3), false);
		result = apInt1.udiv(apInt2);
		assertTrue(result.toString(10, false, false) .equals("4"));

		// Arbitrary bit width 5, inexact division
		apInt1 = new APInt(5, ULong.valueOf(12), false);
		apInt2 = new APInt(5, ULong.valueOf(5), false);
		result = apInt1.udiv(apInt2);
		assertTrue(result.toString(10, false, false) .equals("2"));

		// Arbitrary bit width 5, numerator 0
		apInt1 =  new APInt(5, ULong.valueOf(0), false);
		apInt2 = new APInt(5, ULong.valueOf(3), false);
		result = apInt1.udiv(apInt2);
		assertTrue(result.toString(10, false, false) .equals("0"));
	}
	
	@Test
	public void testSingleWordUnsignedDivisionOfAPIntWithULong() {
		// Division of zero by a non-zero number
		APInt apInt1 = new APInt(32, ULong.valueOf(0), false);
		APInt result = apInt1.udiv(ULong.valueOf(10));
		assertTrue(result.toString(10, false, false) .equals("0"));

		// Division of a number by zero
		apInt1 = new APInt(32, ULong.valueOf(8), false);
		try {
			result = apInt1.udiv(ULong.valueOf(0));
			assertTrue(false);
		}
		catch(Exception e) {
			assertTrue(e.getMessage().equals("Divide by zero?"));
		}

		// Exact division with numerator below upper bound
		apInt1 = new APInt(64, ULong.valueOf(45), false);
		result = apInt1.udiv(ULong.valueOf(9));
		assertTrue(result.toString(10, false, false) .equals("5"));

		// Inexact division with numerator below upper bound
		apInt1 = new APInt(64, ULong.valueOf(19), false);
		result = apInt1.udiv(ULong.valueOf(4));
		assertTrue(result.toString(10, false, false) .equals("4"));

		// Both in range and denom > nume
		apInt1 = new APInt(64, ULong.valueOf(5), false);
		result = apInt1.udiv(ULong.valueOf(95));
		assertTrue(result.toString(10, false, false) .equals("0"));

		// Numerator at upper bound and denom in range
		apInt1 = new APInt(64, ULong.valueOf("18446744073709551615"), false);
		result = apInt1.udiv(ULong.valueOf(5));
		assertTrue(result.toString(10, false, false) .equals("3689348814741910323"));

		// Both at boundary
		apInt1 = new APInt(64, ULong.valueOf("18446744073709551615"), false);
		result = apInt1.udiv(ULong.valueOf("18446744073709551615"));
		assertTrue(result.toString(10, false, false) .equals("1"));

		// Numerator beyond upper bound, denom in range
		apInt1 = new APInt(64, ULong.valueOf("18446744073709551618"), false);
		result = apInt1.udiv(ULong.valueOf(2));
		assertTrue(result.toString(10, false, false) .equals("1"));

		// Arbitrary bit width 5, exact division
		apInt1 =  new APInt(5, ULong.valueOf(12), false);
		result = apInt1.udiv(ULong.valueOf(3));
		assertTrue(result.toString(10, false, false) .equals("4"));

		// Arbitrary bit width 5, inexact division
		apInt1 = new APInt(5, ULong.valueOf(12), false);
		result = apInt1.udiv(ULong.valueOf(5));
		assertTrue(result.toString(10, false, false) .equals("2"));

		// Arbitrary bit width 5, numerator 0
		apInt1 =  new APInt(5, ULong.valueOf(0), false);
		result = apInt1.udiv(ULong.valueOf(3));
		assertTrue(result.toString(10, false, false) .equals("0"));
	}
	
	@Test
	public void testSingleWordSignedDivisionOfAPInts() {
		// Division of zero by a non-zero number, both positive
		APInt apInt1 = new APInt(32, ULong.valueOf(0), false);
		APInt apInt2 = new APInt(32, ULong.valueOf(10), false);

		APInt result = apInt1.sdiv(apInt2);
		assertTrue(result.toString(10, false, false) .equals("0"));
		assertTrue(result.toString(10, true, false) .equals("0"));

		// Division of a number by zero
		apInt1 = new APInt(32, ULong.valueOf(8), false);
		apInt2 = new APInt(32, ULong.valueOf(0), false);
		try {
			result = apInt1.sdiv(apInt2);
			assertTrue(false);
		}
		catch(Exception e) {
			assertTrue(e.getMessage().equals("Divide by zero?"));
		}

		// Both negative
		apInt1 = new APInt(64, ULong.valueOf(-45), false);
		apInt2 = new APInt(64, ULong.valueOf(-9), false);
		result = apInt1.sdiv(apInt2);
		assertTrue(result.toString(10, false, false).equals("5"));
		assertTrue(result.toString(10, true, false).equals("5"));
		
		// Numerator negative, denominator positive
		apInt1 = new APInt(64, ULong.valueOf(-45), false);
		apInt2 = new APInt(64, ULong.valueOf(9), false);
		result = apInt1.sdiv(apInt2);
		assertTrue(result.toString(10, false, false) .equals("18446744073709551611"));
		assertTrue(result.toString(10, true, false) .equals("-5"));

		// Numerator negative, denominator positive
		apInt1 = new APInt(64, ULong.valueOf("18446744073709551615"), false);
		apInt2 = new APInt(64, ULong.valueOf(-5), false);
		result = apInt1.sdiv(apInt2);
		assertTrue(result.toString(10, false, false) .equals("0"));
		assertTrue(result.toString(10, true, false) .equals("0"));
		
		// Both positive
		apInt1 = new APInt(64, ULong.valueOf(121), false);
		apInt2 = new APInt(64, ULong.valueOf(11), false);
		result = apInt1.sdiv(apInt2);
		assertTrue(result.toString(10, false, false) .equals("11"));
		assertTrue(result.toString(10, true, false) .equals("11"));
	}
	
	@Test
	public void testSingleWordSignedDivisionOfAPIntsWithLong() {
		// Division of zero by a non-zero number, both positive
		APInt apInt1 = new APInt(32, ULong.valueOf(0), false);
		APInt result = apInt1.sdiv(10);
		assertTrue(result.toString(10, false, false).equals("0"));
		assertTrue(result.toString(10, true, false).equals("0"));

		// Division of a number by zero
		apInt1 = new APInt(32, ULong.valueOf(8), false);
		try {
			result = apInt1.sdiv(0);
			assertTrue(false);
		}
		catch(Exception e) {
			assertTrue(e.getMessage().equals("Divide by zero?"));
		}

		// Both negative
		apInt1 = new APInt(64, ULong.valueOf(-45), false);
		result = apInt1.sdiv(-9);
		assertTrue(result.toString(10, false, false) .equals("5"));
		assertTrue(result.toString(10, true, false) .equals("5"));
		
		// Numerator negative, denominator positive
		apInt1 = new APInt(64, ULong.valueOf(-45), false);
		result = apInt1.sdiv(9);
		assertTrue(result.toString(10, false, false) .equals("18446744073709551611"));
		assertTrue(result.toString(10, true, false) .equals("-5"));

		//  Denominator negative, numerator positive
		apInt1 = new APInt(64, ULong.valueOf("18446744073709551615"), false);
		result = apInt1.sdiv(-5);
		assertTrue(result.toString(10, false, false) .equals("0"));
		assertTrue(result.toString(10, true, false) .equals("0"));
		
		// Both positive
		apInt1 = new APInt(64, ULong.valueOf(121), false);
		result = apInt1.sdiv(11);
		assertTrue(result.toString(10, false, false) .equals("11"));
		assertTrue(result.toString(10, true, false) .equals("11"));
	}
	
	@Test
	public void testSingleWordUnsignedRemainderOfAPInts() {
		// Division of zero by a non-zero number
		APInt apInt1 = new APInt(32, ULong.valueOf(0), false);
		APInt apInt2 = new APInt(32, ULong.valueOf(10), false);

		APInt result = apInt1.urem(apInt2);
		assertTrue(result.toString(10, false, false) .equals("0"));

		// Division of a number by zero
		apInt1 = new APInt(32, ULong.valueOf(8), false);
		apInt2 = new APInt(32, ULong.valueOf(0), false);
		
		try {
			result = apInt1.urem(apInt2);
			assertTrue(false);
		}
		catch(Exception e) {
			assertTrue(e.getMessage().equals("Remainder by zero?"));
		}

		// Exact division with numerator below upper bound
		apInt1 = new APInt(64, ULong.valueOf(45), false);
		apInt2 = new APInt(64, ULong.valueOf(9), false);
		result = apInt1.urem(apInt2);
		assertTrue(result.toString(10, false, false).equals("0"));

		// Inexact division with numerator below upper bound
		apInt1 = new APInt(64, ULong.valueOf(45), false);
		apInt2 = new APInt(64, ULong.valueOf(4), false);
		result = apInt1.urem(apInt2);
		assertTrue(result.toString(10, false, false).equals("1"));

		// Both in range and denom > nume
		apInt1 = new APInt(64, ULong.valueOf(5), false);
		apInt2 = new APInt(64, ULong.valueOf(95), false);
		result = apInt1.urem(apInt2);
		assertTrue(result.toString(10, false, false) .equals("5"));

		// Numerator at upper bound and denom in range
		apInt1 = new APInt(64, ULong.valueOf("18446744073709551615"), false);
		apInt2 = new APInt(64, ULong.valueOf(5), false);
		result = apInt1.urem(apInt2);
		assertTrue(result.toString(10, false, false) .equals("0"));

		// Both at boundary
		apInt1 = new APInt(64, ULong.valueOf("18446744073709551615"), false);
		apInt2 = new APInt(64, ULong.valueOf("18446744073709551615"), false);
		result = apInt1.urem(apInt2);
		assertTrue(result.toString(10, false, false).equals("0"));

		// Numerator beyond upper bound, denom in range
		apInt1 = new APInt(64, ULong.valueOf("18446744073709551618"), false);
		apInt2 = new APInt(64, ULong.valueOf(2), false);
		result = apInt1.urem(apInt2);
		assertTrue(result.toString(10, false, false) .equals("0"));

		// Denominator beyond upper bound, numerator in range
		apInt1 = new APInt(64, ULong.valueOf(2), false);
		apInt2 = new APInt(64, ULong.valueOf("18446744073709551618"), false);
		result = apInt1.urem(apInt2);
		assertTrue(result.toString(10, false, false) .equals("0"));

		// Both beyond range, num > denom
		apInt1 = new APInt(64, ULong.valueOf("184467440737095516192"), false);
		apInt2 = new APInt(64, ULong.valueOf("18446744073709551618"), false);
		result = apInt1.urem(apInt2);
		assertTrue(result.toString(10, false, false) .equals("0"));

		// Both beyond range, denom > num
		apInt1 = new APInt(64, ULong.valueOf("18446744073709551618"), false);
		apInt2 = new APInt(64, ULong.valueOf("184467440737095516193"), false);
		result = apInt1.urem(apInt2);
		assertTrue(result.toString(10, false, false) .equals("2"));

		// Arbitrary bit width 5, exact division
		apInt1 =  new APInt(5, ULong.valueOf(12), false);
		apInt2 = new APInt(5, ULong.valueOf(3), false);
		result = apInt1.urem(apInt2);
		assertTrue(result.toString(10, false, false) .equals("0"));

		// Arbitrary bit width 5, inexact division
		apInt1 = new APInt(5, ULong.valueOf(12), false);
		apInt2 = new APInt(5, ULong.valueOf(5), false);
		result = apInt1.urem(apInt2);
		assertTrue(result.toString(10, false, false) .equals("2"));

		// Arbitrary bit width 5, numerator 0
		apInt1 =  new APInt(5, ULong.valueOf(0), false);
		apInt2 = new APInt(5, ULong.valueOf(3), false);
		result = apInt1.urem(apInt2);
		assertTrue(result.toString(10, false, false) .equals("0"));
	}
	
	@Test
	public void testSingleWordUnsignedRemainderOfAPIntWithULong() {
		// Division of zero by a non-zero number
		APInt apInt1 = new APInt(32, ULong.valueOf(0), false);

		ULong result = apInt1.urem(ULong.valueOf(10));
		assertTrue(result.toString().equals("0"));

		// Division of a number by zero
		apInt1 = new APInt(32, ULong.valueOf(8), false);
		try {
			result = apInt1.urem(ULong.valueOf(0));
			assertTrue(false);
		}
		catch(Exception e) {
			assertTrue(e.getMessage().equals("Remainder by zero?"));
		}

		// Exact division with numerator below upper bound
		apInt1 = new APInt(64, ULong.valueOf(45), false);
		result = apInt1.urem(ULong.valueOf(9));
		assertTrue(result.toString().equals("0"));

		// Inexact division with numerator below upper bound
		apInt1 = new APInt(64, ULong.valueOf(45), false);
		result = apInt1.urem(ULong.valueOf(4));
		assertTrue(result.toString().equals("1"));

		// Both in range and denom > nume
		apInt1 = new APInt(64, ULong.valueOf(5), false);
		result = apInt1.urem(ULong.valueOf(95));
		assertTrue(result.toString() .equals("5"));

		// Numerator at upper bound and denom in range
		apInt1 = new APInt(64, ULong.valueOf("18446744073709551615"), false);
		result = apInt1.urem(ULong.valueOf(5));
		assertTrue(result.toString() .equals("0"));

		// Both at boundary
		apInt1 = new APInt(64, ULong.valueOf("18446744073709551615"), false);
		result = apInt1.urem(ULong.valueOf("18446744073709551615"));
		assertTrue(result.toString().equals("0"));

		// Numerator beyond upper bound, denom in range
		apInt1 = new APInt(64, ULong.valueOf("18446744073709551618"), false);
		result = apInt1.urem(ULong.valueOf(2));
		assertTrue(result.toString() .equals("0"));

		// Arbitrary bit width 5, exact division
		apInt1 =  new APInt(5, ULong.valueOf(12), false);
		result = apInt1.urem(ULong.valueOf(3));
		assertTrue(result.toString().equals("0"));

		// Arbitrary bit width 5, inexact division
		apInt1 = new APInt(5, ULong.valueOf(12), false);
		result = apInt1.urem(ULong.valueOf(5));
		assertTrue(result.toString().equals("2"));

		// Arbitrary bit width 5, numerator 0
		apInt1 =  new APInt(5, ULong.valueOf(0), false);
		result = apInt1.urem(ULong.valueOf(3));
		assertTrue(result.toString().equals("0"));
	}
	
	@Test
	public void testSingleWordSignedRemainderOfAPInts() {
		// Division of zero by a non-zero number, both positive
		APInt apInt1 = new APInt(32, ULong.valueOf(0), false);
		APInt apInt2 = new APInt(32, ULong.valueOf(10), false);

		APInt result = apInt1.srem(apInt2);
		assertTrue(result.toString(10, false, false).equals("0"));
		assertTrue(result.toString(10, true, false).equals("0"));

		// Division of a number by zero
		apInt1 = new APInt(32, ULong.valueOf(8), false);
		apInt2 = new APInt(32, ULong.valueOf(0), false);
		try {
			result = apInt1.srem(apInt2);
			assertTrue(false);
		}
		catch(Exception e) {
			assertTrue(e.getMessage().equals("Remainder by zero?"));
		}

		// Both negative
		apInt1 = new APInt(64, ULong.valueOf(-45), false);
		apInt2 = new APInt(64, ULong.valueOf(-9), false);
		result = apInt1.srem(apInt2);
		assertTrue(result != apInt1);
		assertTrue(result != apInt2);
		assertTrue(result.toString(10, false, false).equals("0"));
		assertTrue(result.toString(10, true, false).equals("0"));
		
		// Inexact division, both negative
		apInt1 = new APInt(64, ULong.valueOf(-45), false);
		apInt2 = new APInt(64, ULong.valueOf(-4), false);
		result = apInt1.srem(apInt2);
		assertTrue(result.toString(10, false, false).equals("18446744073709551615"));
		assertTrue(result.toString(10, true, false).equals("-1"));
		
		// Numerator negative, denominator positive
		apInt1 = new APInt(64, ULong.valueOf(-45), false);
		apInt2 = new APInt(64, ULong.valueOf(9), false);
		result = apInt1.srem(apInt2);
		assertTrue(result.toString(10, false, false).equals("0"));
		assertTrue(result.toString(10, true, false).equals("0"));

		// Denominator  negative,  numerator positive
		apInt1 = new APInt(64, ULong.valueOf("18446744073709551615"), false);
		apInt2 = new APInt(64, ULong.valueOf(-5), false);
		result = apInt1.srem(apInt2);
		assertTrue(result.toString(10, false, false).equals("18446744073709551615"));
		assertTrue(result.toString(10, true, false).equals("-1"));
		
		// Both positive
		apInt1 = new APInt(64, ULong.valueOf(121), false);
		apInt2 = new APInt(64, ULong.valueOf(11), false);
		result = apInt1.srem(apInt2);
		assertTrue(result.toString(10, false, false).equals("0"));
		assertTrue(result.toString(10, true, false).equals("0"));
	}
	
	@Test
	public void testSingleWordSignedRemainderOfAPIntsWithLong() {
		// Division of zero by a non-zero number, both positive
		APInt apInt1 = new APInt(32, ULong.valueOf(0), false);

		long result = apInt1.srem(10);
		assertTrue(result == 0);

		// Division of a number by zero
		apInt1 = new APInt(32, ULong.valueOf(8), false);
		try {
			result = apInt1.srem(0);
			assertTrue(false);
		}
		catch(Exception e) {
			assertTrue(e.getMessage().equals("Remainder by zero?"));
		}

		// Both negative
		apInt1 = new APInt(64, ULong.valueOf(-45), false);
		result = apInt1.srem(-9);
		assertTrue(result == 0);
		
		// Numerator negative, denominator positive
		apInt1 = new APInt(64, ULong.valueOf(-45), false);
		result = apInt1.srem(9);
		assertTrue(result == 0);

		// Denominator negative, numerator positive
		apInt1 = new APInt(64, ULong.valueOf("18446744073709551615"), false);
		result = apInt1.srem(-5);
		assertTrue(result == -1);
		
		// Both positive
		apInt1 = new APInt(64, ULong.valueOf(121), false);
		result = apInt1.srem(11);
		assertTrue(result == 0);
	}
	
	
	/*
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
		int overflow = APIntUtils.tcMultiplyPart(apInt.unsignedVals, apInt.unsignedVals, multiplier, ULong.valueOf(0), 2, 2, false);
		assertTrue(overflow == 0);
		assertTrue(apInt.unsignedVals[0].equals(ULong.valueOf("18446744073709551614")));
		assertTrue(apInt.unsignedVals[1].equals(ULong.valueOf(1)));

		apInt = new APInt(128, "18446744073709551615", 10);
		multiplier = ULong.valueOf(3);
		overflow = APIntUtils.tcMultiplyPart(apInt.unsignedVals, apInt.unsignedVals, multiplier, ULong.valueOf(0), 2, 2, false);
		assertTrue(overflow == 0);
		assertTrue(apInt.unsignedVals[0].equals(ULong.valueOf("18446744073709551613")));
		assertTrue(apInt.unsignedVals[1].equals(ULong.valueOf(2)));

		// Multiplications with overflow
		apInt = new APInt(128, "18446744073709551619", 10);
		multiplier = ULong.valueOf("18446744073709551615");
		overflow = APIntUtils.tcMultiplyPart(apInt.unsignedVals, apInt.unsignedVals, multiplier, ULong.valueOf(0), 2, 2, false);
		assertTrue(overflow == 1);
		assertTrue(apInt.unsignedVals[0].equals(ULong.valueOf("18446744073709551613")));
		assertTrue(apInt.unsignedVals[1].equals(ULong.valueOf(1)));

		// Additions
		apInt = new APInt(128, "18446744073709551615", 10);
		ULong addVal = ULong.valueOf(56);
		ULong carry = APIntUtils.tcAddPart(apInt.unsignedVals, addVal, 2);
		assertTrue(carry.equals(ULong.valueOf(0)));
		assertTrue(apInt.unsignedVals[0].equals(ULong.valueOf(55)));
		assertTrue(apInt.unsignedVals[1].equals(ULong.valueOf(1)));

		apInt = new APInt(128, "18446744073709551632", 10);
		addVal = ULong.valueOf(47);
		carry = APIntUtils.tcAddPart(apInt.unsignedVals, addVal, 2);
		assertTrue(carry.equals(ULong.valueOf(0)));
		assertTrue(apInt.unsignedVals[0].equals(ULong.valueOf(63)));
		assertTrue(apInt.unsignedVals[1].equals(ULong.valueOf(1)));
	}
	 */
}

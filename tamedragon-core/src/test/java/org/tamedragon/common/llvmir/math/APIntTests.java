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
	public void testAPIntConstructionWithStringAndRadix10() {
		
		// Within range positive number
		APInt temp = new APInt(8, "100", 10);
		assertTrue(temp.getUnsignedVal().toString().equals("100"));
		
		// Negative number
		temp = new APInt(8, "-9", 10);
		assertTrue(temp.getUnsignedVal().toString().equals("247"));
		
		// Positive number outside range
		temp = new APInt(8, "325", 10);
		System.out.println(temp.getUnsignedVal());
		assertTrue(temp.getUnsignedVal().toString().equals("69"));
		
		
	}
}

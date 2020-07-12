package org.tamedragon.common.llvmir.math;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class MathUtilsTest {
	
	@Test
	public void testTrailingOnes() {
		ULong val = ULong.valueOf(0);  // .... 0000 0000
		int trailingOnes = MathUtils.countTrailingOnes(val);
		assertTrue(trailingOnes == 0);
		
		val = ULong.valueOf(1);       // .... 0000 0001
		trailingOnes = MathUtils.countTrailingOnes(val);
		assertTrue(trailingOnes == 1);
		
		val = ULong.valueOf(2);      // .... 0000 0010
		trailingOnes = MathUtils.countTrailingOnes(val);
		assertTrue(trailingOnes == 0);
		
		val = ULong.valueOf(3);      // .... 0000 0011
		trailingOnes = MathUtils.countTrailingOnes(val);
		assertTrue(trailingOnes == 2);
		
		val = ULong.valueOf(4);     // .... 0000 0100
		trailingOnes = MathUtils.countTrailingOnes(val);
		assertTrue(trailingOnes == 0);
		
		val = ULong.valueOf(5);     // .... 0000 0101
		trailingOnes = MathUtils.countTrailingOnes(val);
		assertTrue(trailingOnes == 1);
		
		val = ULong.valueOf(6);     // .... 0000 0110
		trailingOnes = MathUtils.countTrailingOnes(val);
		assertTrue(trailingOnes == 0);
		
		val = ULong.valueOf(7);     // .... 0000 0111
		trailingOnes = MathUtils.countTrailingOnes(val);
		assertTrue(trailingOnes == 3);
		
		val = ULong.valueOf(8);     // .... 0000 1000
		trailingOnes = MathUtils.countTrailingOnes(val);
		assertTrue(trailingOnes == 0);
		
		val = ULong.valueOf(191);     // .... 1011 1111
		trailingOnes = MathUtils.countTrailingOnes(val);
		assertTrue(trailingOnes == 6);
		
		
	}

}

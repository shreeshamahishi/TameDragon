package org.tamedragon.compilers.mips.translate;

import java.util.Vector;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.tamedragon.common.Label;
import org.tamedragon.common.llvmir.instructions.AssemblyInstruction;
import org.tamedragon.common.llvmir.instructions.CJump;
import org.tamedragon.common.llvmir.types.AbstractType;
import org.tamedragon.common.llvmir.types.Numeric;
import org.tamedragon.common.llvmir.types.Operand;
import org.tamedragon.common.llvmir.types.Temp;
import org.tamedragon.compilers.clang.tests.TestInitializer;
import org.tamedragon.compilers.mips.translate.MipsProperties;

public class MIPSPropertiesTest1 extends TestInitializer {
	
	private MipsProperties fixture;
	
	@Before
	public void setUp(){
		fixture = new MipsProperties();
	}
	
	@Test
	public void testCJumpReplacements() {      	
		Vector<Operand> srcList = new Vector<Operand>();
		Temp temp1 = Temp.getTemp("$f2");
		String temp1Name = temp1.toString();
		
		temp1.setInteger(false);
		temp1.setFloatPrecision(AbstractType.DOUBLE_PRECISION);
		
		Numeric numeric = new Numeric(Numeric.DOUBLE_TYPE, "60000.009");
		
		srcList.add(temp1);
		srcList.add(numeric);
		Label ifLabel = new Label("L24");
		Label elseLabel = new Label("L23");
		
		int relop = CJump.LE;
		
		CJump instr = new CJump(srcList, ifLabel.toString(), 
				elseLabel.toString(), relop, null);
		
		Vector<AssemblyInstruction> replacements = 
			fixture.getReplacementsForConditionalJumpInsHavingFloatingPointOperands(instr);
		
		assertNotNull(replacements);
		assertTrue(replacements.size() == 3);
		
	}
}

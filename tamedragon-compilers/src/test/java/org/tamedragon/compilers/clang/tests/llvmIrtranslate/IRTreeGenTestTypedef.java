package org.tamedragon.compilers.clang.tests.llvmIrtranslate;

import java.util.List;

import static org.junit.Assert.*;

import org.junit.Test;
import org.tamedragon.common.utils.ComparisionUtils;
import org.tamedragon.compilers.LLVMBaseTest;

public class IRTreeGenTestTypedef extends LLVMBaseTest{
	
	private String projectPath = "CSrc/TranslateToLLVMIR/Typedef";

	@Test
	public void testSimpleTypeDefExample(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "SimpleTypeDefExample.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "SimpleTypeDefExampleLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
}

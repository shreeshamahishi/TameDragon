package org.tamedragon.compilers.clang.tests.llvmIrtranslate;

import java.util.List;

import static org.junit.Assert.*;

import org.junit.Test;
import org.tamedragon.common.utils.ComparisionUtils;
import org.tamedragon.compilers.LLVMBaseTest;

public class IRTreeGenTestMixture extends LLVMBaseTest {
	
	private String projectPath = "CSrc/TranslateToLLVMIR/MixtureOfDeclrAndStmts";

	@Test
	public void testSimpleTest(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "Simple.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "SimpleLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
}

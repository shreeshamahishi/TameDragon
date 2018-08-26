package org.tamedragon.compilers.clang.tests.llvmIrtranslate;

import java.util.List;

import static org.junit.Assert.*;

import org.junit.Test;
import org.tamedragon.common.utils.ComparisionUtils;
import org.tamedragon.compilers.LLVMBaseTest;

public class IRTreeGenTestDynamicMemoryAllocation extends LLVMBaseTest{
	
	private String projectPath = "TranslateToLLVMIR/DynamicMemoryAllocation";
	
	@Test
	public void testSimpleMallocEg(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "SimpleMallocEg.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "SimpleMallocEgLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
}

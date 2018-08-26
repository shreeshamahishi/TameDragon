package org.tamedragon.compilers.clang.tests.llvmIrtranslate;

import java.util.List;

import static org.junit.Assert.*;

import org.junit.Test;
import org.tamedragon.common.utils.ComparisionUtils;
import org.tamedragon.compilers.LLVMBaseTest;

public class IRTreeGenTestFloatType extends LLVMBaseTest{
	
	private String projectPath = "CSrc/TranslateToLLVMIR/FloatTest";
	
	@Test
	public void testFloatTest1(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "FloatTest1.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "FloatTest1LLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testFloatTest2(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "FloatTest2.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "FloatTest2LLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
}

package org.tamedragon.compilers.clang.tests.llvmIrtranslate;

import java.util.List;

import static org.junit.Assert.*;

import org.junit.Test;
import org.tamedragon.common.utils.ComparisionUtils;
import org.tamedragon.compilers.LLVMBaseTest;

public class IRTreeGenTestIntrinsicFunctions extends LLVMBaseTest{
	
	private String projectPath = "CSrc/TranslateToLLVMIR/IntrinsicFunctions";
	
	@Test
	public void testMemCpy1(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "memcpyEg1.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "memcpyEg1LLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testMemCpy2(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "memcpyEg2.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "memcpyEg2LLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
}

package org.tamedragon.compilers.clang.tests.llvmIrtranslate;

import java.util.List;

import static org.junit.Assert.*;

import org.junit.Test;
import org.tamedragon.common.utils.LLVMIRComparisionUtils;
import org.tamedragon.compilers.LLVMBaseTest;


public class IRTreeGenTestSimple extends LLVMBaseTest{
	
	private String projectPath = "CSrc/TranslateToLLVMIR/simplePrograms";

	@Test
	public void testSample1Program(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "sample1.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "sample1LLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testSample2Program(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "sample2.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "sample2LLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
}

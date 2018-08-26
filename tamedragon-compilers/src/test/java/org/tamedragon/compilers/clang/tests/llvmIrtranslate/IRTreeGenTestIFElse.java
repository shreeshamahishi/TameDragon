package org.tamedragon.compilers.clang.tests.llvmIrtranslate;

import java.util.List;

import static org.junit.Assert.*;

import org.junit.Test;
import org.tamedragon.common.utils.ComparisionUtils;
import org.tamedragon.compilers.LLVMBaseTest;


public class IRTreeGenTestIFElse extends LLVMBaseTest{

	private String projectPath = "CSrc/TranslateToLLVMIR/IFElseTests";
	
	@Test
	public void testSimpleIfElse(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "simpleIfElse.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "simpleIfElseLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testMem2Reg3(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "Mem2Reg3.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "Mem2Reg3LLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testForWithIfElse2(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "ForWithIfElse2.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "ForWithIfElse2LLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void testOnlyIf(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "onlyIf.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "onlyIfLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}	
}

package org.tamedragon.compilers.clang.tests.llvmIrtranslate;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.tamedragon.common.utils.ComparisionUtils;
import org.tamedragon.compilers.LLVMBaseTest;

public class IRTreeGenTestEnums extends LLVMBaseTest {
	
	private String projectPath = "CSrc/TranslateToLLVMIR/Enums";

	@Test
	public void testEnumTest(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "enumTest.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "enumTestLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testEnumTest1(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "enumTest1.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "enumTest1LLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testEnumTest2(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "enumTest2.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "enumTest2LLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testEnumTest3(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "enumTest3.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "enumTest3LLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testEnumTest4(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "enumTest4.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "enumTest4LLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testEnumTest5(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "enumTest5.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "enumTest5LLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testEnumTest6(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "enumTest6.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "enumTest6LLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testEnumTest7(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "enumTest7.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "enumTest7LLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
}

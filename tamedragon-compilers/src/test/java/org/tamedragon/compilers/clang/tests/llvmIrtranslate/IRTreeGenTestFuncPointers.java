package org.tamedragon.compilers.clang.tests.llvmIrtranslate;

import java.util.List;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.tamedragon.common.utils.ComparisionUtils;
import org.tamedragon.compilers.LLVMBaseTest;

public class IRTreeGenTestFuncPointers extends LLVMBaseTest{
	
	private String projectPath = "CSrc/TranslateToLLVMIR/FunctionPointer";
	
	@Test
	public void testSimpleFunctionPointer(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "SimpleFuncPointer.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "SimpleFuncPointerLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testFunctionPointer(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "FunctionPointer.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "FunctionPointerLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Ignore
	@Test
	public void testFunctionPointerAsMethodArg(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "FunctionPointerAsMethodArg.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "FunctionPointerAsMethodArgLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
}

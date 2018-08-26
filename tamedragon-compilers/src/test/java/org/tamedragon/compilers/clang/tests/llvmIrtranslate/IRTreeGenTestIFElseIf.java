package org.tamedragon.compilers.clang.tests.llvmIrtranslate;

import java.util.List;

import static org.junit.Assert.*;

import org.junit.Test;
import org.tamedragon.common.utils.ComparisionUtils;
import org.tamedragon.compilers.LLVMBaseTest;


public class IRTreeGenTestIFElseIf extends LLVMBaseTest{
	
	private String projectPath = "CSrc/TranslateToLLVMIR/IfElseIfTest";

	@Test
	public void testSimpleIfElseIf(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "IfElseIfTest.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "IfElseIfTestLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testIfElseIfWithinForLoop(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "IfElseIfWithinForLoop.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "IfElseIfWithinForLoopLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testIfElseIfWithinLoopWithBreakContinueReturn(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "IfElseIfWithinLoopWithBreakContinueReturn.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "IfElseIfWithinLoopWithBreakContinueReturnLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
}

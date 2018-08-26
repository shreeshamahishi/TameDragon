package org.tamedragon.compilers.clang.tests.llvmIrtranslate;

import java.util.List;

import static org.junit.Assert.*;

import org.junit.Test;
import org.tamedragon.common.utils.ComparisionUtils;
import org.tamedragon.compilers.LLVMBaseTest;

public class IRTreeGenTestInterpretingIntoLLVMIRWithoutNames extends LLVMBaseTest{
	
	private String projectPath = "CSrc/TranslateToLLVMIR/LLVMIRWithoutNames";
	
	@Test
	public void testSimpleProgram(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "SimpleIfElse.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "SimpleIfElseLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}	
}

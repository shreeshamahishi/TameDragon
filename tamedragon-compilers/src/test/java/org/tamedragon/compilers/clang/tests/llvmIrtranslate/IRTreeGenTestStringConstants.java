package org.tamedragon.compilers.clang.tests.llvmIrtranslate;

import java.util.List;

import static org.junit.Assert.*;

import org.junit.Test;
import org.tamedragon.common.utils.ComparisionUtils;
import org.tamedragon.compilers.LLVMBaseTest;

public class IRTreeGenTestStringConstants extends LLVMBaseTest{
	
	private String projectPath = "CSrc/TranslateToLLVMIR/Strings";
	
	@Test
	public void testSimpleStringConstant(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "simpleStringAssignment.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "simpleStringAssignmentLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
}

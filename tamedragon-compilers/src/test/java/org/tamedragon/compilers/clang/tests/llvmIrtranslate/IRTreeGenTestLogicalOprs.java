package org.tamedragon.compilers.clang.tests.llvmIrtranslate;

import java.util.List;

import static org.junit.Assert.*;

import org.junit.Test;
import org.tamedragon.common.utils.ComparisionUtils;
import org.tamedragon.compilers.LLVMBaseTest;

public class IRTreeGenTestLogicalOprs extends LLVMBaseTest{
	
	private String projectPath = "CSrc/TranslateToLLVMIR/TestLogicalAND_OR";

	@Test
	public void testLogicalOR(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "logicalOR.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "logicalORLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testLogicalAND(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "logicalAND.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "logicalANDLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testLogicalANDInIf(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "logicalANDInIf.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "logicalANDInIfLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testAssigningLogicalOR(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "AssigningLogicalOR.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "AssigningLogicalORLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
}

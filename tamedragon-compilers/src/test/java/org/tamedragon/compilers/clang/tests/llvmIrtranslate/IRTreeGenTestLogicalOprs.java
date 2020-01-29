package org.tamedragon.compilers.clang.tests.llvmIrtranslate;

import java.util.List;

import static org.junit.Assert.*;

import org.junit.Test;
import org.tamedragon.common.utils.LLVMIRComparisionUtils;
import org.tamedragon.compilers.LLVMBaseTest;

public class IRTreeGenTestLogicalOprs extends LLVMBaseTest{
	
	private String projectPath = "CSrc/TranslateToLLVMIR/TestLogicalAND_OR";

	@Test
	public void testLogicalOR(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "logicalOR.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "logicalORLLVMIR.ll"));
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
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "logicalANDLLVMIR.ll"));
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
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "logicalANDInIfLLVMIR.ll"));
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
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "AssigningLogicalORLLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
}

package org.tamedragon.compilers.clang.tests.llvmIrtranslate;

import java.util.List;

import static org.junit.Assert.*;

import org.junit.Test;
import org.tamedragon.common.utils.ComparisionUtils;
import org.tamedragon.compilers.LLVMBaseTest;

public class IRTreeGenTestCasting extends LLVMBaseTest{
	
	private String projectPath = "CSrc/TranslateToLLVMIR/TestCastingOprs";
	
	@Test
	public void testIntToDouble(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "intToDouble.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "intToDoubleLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testIntToFloat(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "intToFloat.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "intToFloatLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testFloatToDouble(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "floatToDouble.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "floatToDoubleLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testDoubleToFloat(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "doubleToFloat.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "doubleToFloatLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testCharToI32(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "charToI32.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "charToI32LLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testShortToInt(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "shortToInt.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "shortToIntLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testIntToShort(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "intToShort.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "intToShortLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testIntToLong(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "intToLong.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "intToLongLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testLongToInt(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "longToInt.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "longToIntLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testPointerToInt(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "castingPointerToInt.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "castingPointerToIntLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testRandomCasting(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "randomCastingTest.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "randomCastingTestLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testExplicitCasting(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "LoopsWithMultipleBasicIndVars.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "LoopsWithMultipleBasicIndVarsLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testCastingOperation(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "CastOperation.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "CastOperationLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
}

package org.tamedragon.compilers.clang.tests.llvmIrtranslate;

import java.util.List;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.tamedragon.common.llvmir.instructions.LLVMUtility;
import org.tamedragon.common.utils.LLVMIRComparisionUtils;
import org.tamedragon.compilers.LLVMBaseTest;
import org.tamedragon.compilers.clang.CLangUtils;
import org.tamedragon.compilers.clang.CompilerSettings;

public class IRTreeTestCallInstr extends LLVMBaseTest{
	
	private CompilerSettings compilerSettings;
	private String projectPath = "CSrc/TranslateToLLVMIR/TestCallInstr/";
	private String projectRootPath;
	
	@Before
	public void setUp(){		
		super.setUp();
		properties = LLVMUtility.getDefaultProperties();
		
		CLangUtils.populateSettings();
		compilerSettings = CompilerSettings.getInstance();
		compilerSettings.setProjectPath(projectPath);

		projectRootPath = compilerSettings.getProjectRoot();
	}

	@Test
	public void testSimpleCallInstr(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectRootPath + projectPath, "TestCallInstr.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "TestCallInstrLLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testSimpleCallInstr2(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectRootPath + projectPath, "TestCallInstr2.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "TestCallInstr2LLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testFibonacciSeries(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectRootPath + projectPath, "fibonacci.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "fibonacciLLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testingMultipleReturn(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectRootPath + projectPath, "testingMultipleReturn.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "testingMultipleReturnLLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
}

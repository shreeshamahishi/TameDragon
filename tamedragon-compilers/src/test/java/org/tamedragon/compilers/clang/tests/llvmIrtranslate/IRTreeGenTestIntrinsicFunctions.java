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

public class IRTreeGenTestIntrinsicFunctions extends LLVMBaseTest{
	
	private CompilerSettings compilerSettings;
	private String projectPath = "CSrc/TranslateToLLVMIR/IntrinsicFunctions/";
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
	public void testMemCpy1(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectRootPath + projectPath, "memcpyEg1.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "memcpyEg1LLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testMemCpy2(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectRootPath + projectPath, "memcpyEg2.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "memcpyEg2LLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
}

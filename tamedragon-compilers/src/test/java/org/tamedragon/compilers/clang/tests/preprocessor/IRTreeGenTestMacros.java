package org.tamedragon.compilers.clang.tests.preprocessor;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.tamedragon.common.llvmir.instructions.LLVMUtility;
import org.tamedragon.compilers.LLVMBaseTest;
import org.tamedragon.compilers.clang.CLangUtils;
import org.tamedragon.compilers.clang.CompilerSettings;

public class IRTreeGenTestMacros extends LLVMBaseTest{
	
	private CompilerSettings compilerSettings;
	private String projectPath = "CSrc/Preprocessor/";
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
	public void testSimpleMacroDefinition(){
		try {
			getRawLLVRIRInstrs(projectRootPath + projectPath, "SimpleMacro.c");
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testFunctionLikeMacro(){
		try {
			getRawLLVRIRInstrs(projectRootPath + projectPath, "FunctionLikeMacro.c");
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testCondition(){
		try {
			getRawLLVRIRInstrs(projectRootPath + projectPath, "ConditionalTest11.c");
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testCondition2(){
		try {
			getRawLLVRIRInstrs(projectRootPath + projectPath, "ConditionalTest12.c");
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testDuplicateOfSideEffects(){
		try {
			getRawLLVRIRInstrs(projectRootPath + projectPath, "Duplication_Of_Side_Effects.c");
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testCocatenation(){
		try {
			getRawLLVRIRInstrs(projectRootPath + projectPath, "Concatenation.c");
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testCocatenation2(){
		try {
			getRawLLVRIRInstrs(projectRootPath + projectPath, "Concatenation2.c");
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testFuncLikeMacro2(){
		try {
			getRawLLVRIRInstrs(projectRootPath + projectPath, "FuncLikeMacro2.c");
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testErrorCheck(){
		try {
			getRawLLVRIRInstrs(projectRootPath + projectPath, "ErrorCheck.c");
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testFeaturesDotH(){
		try { 
			getRawLLVRIRInstrs(projectRootPath + projectPath, "TestFeaturesDotH.c");
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testNestedIf(){
		try {
			getRawLLVRIRInstrs(projectRootPath + projectPath, "ConditionalTest13.c");
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testMacroWithDoubleQuotes(){
		try {
			getRawLLVRIRInstrs(projectRootPath + projectPath, "MacroWithinDoubleQuotes.c");
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
}

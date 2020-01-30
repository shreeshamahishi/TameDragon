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


public class IRTreeGenTestLoops extends LLVMBaseTest{
	
	private CompilerSettings compilerSettings;
	private String projectPath = "CSrc/TranslateToLLVMIR/LoopTests/";
	
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
	public void testSimpleFor(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectRootPath + projectPath, "simpleFor.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "simpleForLLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testForLoopWithContinueBreakAndReturn(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectRootPath + projectPath, "ForLoopWithContinueBreakAndReturn.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "ForLoopWithContinueBreakAndReturnLLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}


	@Test
	public void testWhileloop(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectRootPath + projectPath, "while.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "whileLLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}


	@Test
	public void testDoWhileWithIfElse(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectRootPath + projectPath, "simpleDoWhileWithIfElse.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "simpleDoWhileWithIfElseLLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}


	@Test
	public void testWhileloopWithBreakInIfAndContInElse(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectRootPath + projectPath, "sample1.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "sample1LLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}


	@Test
	public void testWhileloopWithEmptyElseBlk(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectRootPath + projectPath, "sample2.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "sample2LLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void testFloatingPointOperations(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectRootPath + projectPath, "TestingFloatingPointBinaryOprs.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "TestingFloatingPointBinaryOprsLLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}


	@Test
	public void testAsignmentPlusOrMinus(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectRootPath + projectPath, "AsignmentPlusOrMinus.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "AsignmentPlusOrMinusLLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}


	@Test
	public void testSimpleDoWhile(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectRootPath + projectPath, "simpleDoWhile.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "simpleDoWhileLLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}


	@Test
	public void testWhileWithBreak(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectRootPath + projectPath, "whileWithBreak.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "whileWithBreakLLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}


	@Test
	public void testWhileWithBreakInElse(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectRootPath + projectPath, "whileWithBreakInElse.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "whileWithBreakInElseLLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void testDoWhileWithinWhile(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectRootPath + projectPath, "doWhileWithinWhile.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "doWhileWithinWhileLLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testTrivialForLoop(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectRootPath + projectPath, "TrivalForLoop.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "TrivalForLoopLLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testNestedLoopsWithSingleBasicIndVars(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectRootPath + projectPath, "NestedLoopsWithSingleBasicIndVars.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "NestedLoopsWithSingleBasicIndVarsLLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testDeclarationInsideForLoop(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectRootPath + projectPath, "DeclarationInsideForLoop.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "DeclarationInsideForLoopLLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testForLoopWithNoInitNoCondExpAndNoIncr(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectRootPath + projectPath, "ForLoopWithNoInitNoCondExpAndNoIncr.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "ForLoopWithNoInitNoCondExpAndNoIncrLLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
}

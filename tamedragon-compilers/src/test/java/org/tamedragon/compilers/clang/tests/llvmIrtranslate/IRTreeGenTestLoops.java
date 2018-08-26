package org.tamedragon.compilers.clang.tests.llvmIrtranslate;

import java.util.List;

import static org.junit.Assert.*;

import org.junit.Test;
import org.tamedragon.common.utils.ComparisionUtils;
import org.tamedragon.compilers.LLVMBaseTest;


public class IRTreeGenTestLoops extends LLVMBaseTest{
	
	private String projectPath = "CSrc/TranslateToLLVMIR/LoopTests";

	@Test
	public void testSimpleFor(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "simpleFor.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "simpleForLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testForLoopWithContinueBreakAndReturn(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "ForLoopWithContinueBreakAndReturn.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "ForLoopWithContinueBreakAndReturnLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}


	@Test
	public void testWhileloop(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "while.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "whileLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}


	@Test
	public void testDoWhileWithIfElse(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "simpleDoWhileWithIfElse.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "simpleDoWhileWithIfElseLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}


	@Test
	public void testWhileloopWithBreakInIfAndContInElse(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "sample1.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "sample1LLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}


	@Test
	public void testWhileloopWithEmptyElseBlk(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "sample2.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "sample2LLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void testFloatingPointOperations(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "TestingFloatingPointBinaryOprs.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "TestingFloatingPointBinaryOprsLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}


	@Test
	public void testAsignmentPlusOrMinus(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "AsignmentPlusOrMinus.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "AsignmentPlusOrMinusLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}


	@Test
	public void testSimpleDoWhile(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "simpleDoWhile.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "simpleDoWhileLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}


	@Test
	public void testWhileWithBreak(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "whileWithBreak.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "whileWithBreakLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}


	@Test
	public void testWhileWithBreakInElse(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "whileWithBreakInElse.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "whileWithBreakInElseLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void testDoWhileWithinWhile(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "doWhileWithinWhile.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "doWhileWithinWhileLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testTrivialForLoop(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "TrivalForLoop.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "TrivalForLoopLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testNestedLoopsWithSingleBasicIndVars(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "NestedLoopsWithSingleBasicIndVars.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "NestedLoopsWithSingleBasicIndVarsLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testDeclarationInsideForLoop(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "DeclarationInsideForLoop.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "DeclarationInsideForLoopLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testForLoopWithNoInitNoCondExpAndNoIncr(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "ForLoopWithNoInitNoCondExpAndNoIncr.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "ForLoopWithNoInitNoCondExpAndNoIncrLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
}

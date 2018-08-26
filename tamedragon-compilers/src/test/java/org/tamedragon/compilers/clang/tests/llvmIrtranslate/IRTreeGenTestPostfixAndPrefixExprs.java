package org.tamedragon.compilers.clang.tests.llvmIrtranslate;

import java.util.List;

import static org.junit.Assert.*;

import org.junit.Test;
import org.tamedragon.common.utils.ComparisionUtils;
import org.tamedragon.compilers.LLVMBaseTest;

public class IRTreeGenTestPostfixAndPrefixExprs extends LLVMBaseTest{
	
	private String projectPath = "CSrc/TranslateToLLVMIR/TestPostfixAndPrefixExprs";
	
	@Test
	public void testSimplePostfix(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "testPostfix.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "testPostfixLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testAssigningAPostfixExpr(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "assigningAPostfixExpr.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "assigningAPostfixExprLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testPostFixSub(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "testPostFixSubtraction.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "testPostFixSubtractionLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testAssigningADecrPostfixExpr(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "assigningADecrPostfixExpr.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "assigningADecrPostfixExprLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testPrefix(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "testPrefix.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "testPrefixLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testAssigningPrefixExpr(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "assigningAPrefixExpr.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "assigningAPrefixExprLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testPostfixReturn(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "postfixReturn.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "postfixReturnLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testPrefixReturn(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "prefixReturn.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "prefixReturnLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testDoublePostfix(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "doublepostfix.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "doublepostfixLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testPostfixFollowedByPrefix(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "postfixFollowedByPrefix.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "postfixFollowedByPrefixLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testRandomTest(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "randomTest.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "randomTestLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testPointerPrefixArithmatic(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "pointerPrefixArithmatic.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "pointerPrefixArithmaticLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testPointerPostfixArithmatic(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "pointerPostfixArithmatic.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "pointerPostfixArithmaticLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
}

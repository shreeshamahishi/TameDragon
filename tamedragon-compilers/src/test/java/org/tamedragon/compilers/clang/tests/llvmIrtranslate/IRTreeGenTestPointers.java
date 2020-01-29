package org.tamedragon.compilers.clang.tests.llvmIrtranslate;

import java.util.List;

import static org.junit.Assert.*;

import org.junit.Test;
import org.tamedragon.common.utils.LLVMIRComparisionUtils;
import org.tamedragon.compilers.LLVMBaseTest;

public class IRTreeGenTestPointers extends LLVMBaseTest{
	
	private String projectPath = "CSrc/TranslateToLLVMIR/Pointers";
	
	@Test
	public void testReturningPointer(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "returningPointer.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "returningPointerLLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testPointerTest(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "pointerTest.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "pointerTestLLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testPointerToPointerToPointer(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "pointerToPointerToPointer.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "pointerToPointerToPointerLLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testPointerRandomTest(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "pointerRandomTest.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "pointerRandomTestLLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testPointerRandomTest2(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "pointerRandomTest2.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "pointerRandomTest2LLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testPointerArithmatic(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "pointerArithmatic.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "pointerArithmaticLLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testPointerArithmatic2(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "pointerArithmatic2.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "pointerArithmatic2LLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	
	@Test
	public void testPointerArithmatic3(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "pointerArithmatic3.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "pointerArithmatic3LLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testMem2RegPtrOpr(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "Mem2RegPtrOpr.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "Mem2RegPtrOprLLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testMultipleNestedLoops(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "MultipleNestedLoops.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "MultipleNestedLoopsLLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testPointerDereference(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "PointerDereference.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "PointerDereferenceLLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testConstantPointer(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "ConstPtr.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "ConstPtrLLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testPointerOperation1(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "PointerOperation1.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "PointerOperation1LLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testPhiAndGlobalPtr(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "PhiAndGlobalPtr.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "PhiAndGlobalPtrLLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
}

package org.tamedragon.compilers.clang.tests.llvmIrtranslate;

import java.util.List;

import static org.junit.Assert.*;

import org.junit.Test;
import org.tamedragon.common.utils.ComparisionUtils;
import org.tamedragon.compilers.LLVMBaseTest;

public class IRTreeGenTestPointers extends LLVMBaseTest{
	
	private String projectPath = "CSrc/TranslateToLLVMIR/Pointers";
	
	@Test
	public void testReturningPointer(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "returningPointer.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "returningPointerLLVMIR.bc"));
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
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "pointerTestLLVMIR.bc"));
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
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "pointerToPointerToPointerLLVMIR.bc"));
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
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "pointerRandomTestLLVMIR.bc"));
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
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "pointerRandomTest2LLVMIR.bc"));
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
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "pointerArithmaticLLVMIR.bc"));
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
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "pointerArithmatic2LLVMIR.bc"));
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
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "pointerArithmatic3LLVMIR.bc"));
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
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "Mem2RegPtrOprLLVMIR.bc"));
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
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "MultipleNestedLoopsLLVMIR.bc"));
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
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "PointerDereferenceLLVMIR.bc"));
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
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "ConstPtrLLVMIR.bc"));
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
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "PointerOperation1LLVMIR.bc"));
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
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "PhiAndGlobalPtrLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
}

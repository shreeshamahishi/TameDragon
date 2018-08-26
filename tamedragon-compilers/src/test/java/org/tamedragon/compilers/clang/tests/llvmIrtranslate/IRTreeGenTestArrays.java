package org.tamedragon.compilers.clang.tests.llvmIrtranslate;

import java.util.List;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.tamedragon.common.utils.ComparisionUtils;
import org.tamedragon.compilers.LLVMBaseTest;

public class IRTreeGenTestArrays extends LLVMBaseTest {
	
	private String projectPath = "CSrc/TranslateToLLVMIR/Arrays";
	
	@Test
	public void testSimpleArrManipulation() {
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "simpleArrayManipulation.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "simpleArrayManipulationLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testReturningArrayValue(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "returningArrayValue.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "returningArrayValueLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testReturningArrayValue2(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "returningArrayValue2.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "returningArrayValue2LLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testMultiDimensionalArrayTest(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "MultiDimensionalArrayTest.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "MultiDimensionalArrayTestLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testReturningMultiDimensionalArray(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "returningMultiDimensionalArray.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "returningMultiDimensionalArrayLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testArrayOfFloatType(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "ArrayOfFloatType.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "ArrayOfFloatTypeLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testPopulatingArrayInLoop(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "populatingArrayInLoop.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "populatingArrayInLoopLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testReturningArrayPointer(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "ReturningArrayPointer.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "ReturningArrayPointerLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testReturningArrayPointer2(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "ReturningArrayPointer2.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "ReturningArrayPointer2LLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testReturningArrayPointer3(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "ReturningArrayPointer3.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "ReturningArrayPointer3LLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testReturningArrayPointer4(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "ReturningArrayPointer4.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "ReturningArrayPointer4LLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testReturningArrayPointer5(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "ReturningArrayPointer5.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "ReturningArrayPointer5LLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testReturningArrayPointer6(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "ReturningArrayPointer6.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "ReturningArrayPointer6LLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testReturningArrayPointer7(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "ReturningArrayPointer7.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "ReturningArrayPointer7LLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testReturningArrayPointer8(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "ReturningArrayPointer8.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "ReturningArrayPointer8LLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testReturningArrayPointer9(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "ReturningArrayPointer9.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "ReturningArrayPointer9LLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testReturningArrayPointer10(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "ReturningArrayPointer10.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "ReturningArrayPointer10LLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testReturningArrayPointer11(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "ReturningArrayPointer11.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "ReturningArrayPointer11LLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testReturningArrayPointer12(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "ReturningArrayPointer12.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "ReturningArrayPointer12LLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testAddressOfArray(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "addressOfArray.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "addressOfArrayLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testManipulatingArrElePtr(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "manipulatingArrElePtr.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "manipulatingArrElePtrLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testGlobalArray(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "GlobalArray.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "GlobalArrayLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testPassingSingleDimensionalArray(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "PassingSingleDimensionalArray.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "PassingSingleDimensionalArrayLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testPassingDoubleDimensionalArray(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "PassingDoubleDimensionalArray.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "PassingDoubleDimensionalArrayLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testVariableSizedArray(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "VariableLengthArray.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "VariableLengthArrayLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testMultiDimensionalArray(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "MultiDimensionalArray.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "MultiDimensionalArrayLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testArrayOfStrings(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "ArrayOfStrings.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "ArrayOfStringsLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Ignore
	@Test
	public void testArrayOfStructs(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "ArrayOfStructs.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "ArrayOfStructsLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testMultiDimensionalArray1(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "MultiDimensionalArray1.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "MultiDimensionalArray1LLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
}

package org.tamedragon.compilers.clang.tests.llvmIrtranslate;

import java.util.List;

import static org.junit.Assert.*;

import org.junit.Test;
import org.tamedragon.common.utils.LLVMIRComparisionUtils;
import org.tamedragon.compilers.LLVMBaseTest;

public class IRTreeGenTestUnion extends LLVMBaseTest{
	
	private String projectPath = "CSrc/TranslateToLLVMIR/Union";
	
	@Test
	public void testUnion(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "UnionTest.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "UnionTestLLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testUnion1(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "UnionTest1.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "UnionTest1LLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testUnion2(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "UnionTest2.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "UnionTest2LLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testStructureWithinUnion(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "StructureWithinUnion.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "StructureWithinUnionLLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testUnion3(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "UnionTest3.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "UnionTest3LLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testAnnonymousUnion(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "AnnonymousUnion.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "AnnonymousUnionLLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testInitializingUnion(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "InitializingUnion.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "InitializingUnionLLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testStrucureWithinUnion2(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "StructureWithinUnion2.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "StructureWithinUnion2LLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
}

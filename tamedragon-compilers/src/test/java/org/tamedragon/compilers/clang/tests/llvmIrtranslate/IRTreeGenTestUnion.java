package org.tamedragon.compilers.clang.tests.llvmIrtranslate;

import java.util.List;

import static org.junit.Assert.*;

import org.junit.Test;
import org.tamedragon.common.utils.ComparisionUtils;
import org.tamedragon.compilers.LLVMBaseTest;

public class IRTreeGenTestUnion extends LLVMBaseTest{
	
	private String projectPath = "CSrc/TranslateToLLVMIR/Union";
	
	@Test
	public void testUnion(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "UnionTest.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "UnionTestLLVMIR.bc"));
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
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "UnionTest1LLVMIR.bc"));
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
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "UnionTest2LLVMIR.bc"));
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
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "StructureWithinUnionLLVMIR.bc"));
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
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "UnionTest3LLVMIR.bc"));
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
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "AnnonymousUnionLLVMIR.bc"));
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
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "InitializingUnionLLVMIR.bc"));
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
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "StructureWithinUnion2LLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
}

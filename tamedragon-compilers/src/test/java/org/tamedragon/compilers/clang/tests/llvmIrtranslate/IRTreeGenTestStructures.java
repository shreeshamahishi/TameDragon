package org.tamedragon.compilers.clang.tests.llvmIrtranslate;

import java.util.List;

import static org.junit.Assert.*;

import org.junit.Test;
import org.tamedragon.common.utils.ComparisionUtils;
import org.tamedragon.compilers.LLVMBaseTest;

public class IRTreeGenTestStructures extends LLVMBaseTest{
	
	private String projectPath = "CSrc/TranslateToLLVMIR/Structures";

	@Test
	public void testLocalStructureEg(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "LocalStructureEg.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "LocalStructureEgLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void testGlobalStructureEg(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "GlobalStructureEg.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "GlobalStructureEgLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testAssigningAStructElement(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "AssigningAStructElement.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "AssigningAStructElementLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testStructureWithinStructure(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "StructureWithinStructure.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "StructureWithinStructureLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testDereferencingAStructure(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "DereferencingAStructureMember.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "DereferencingAStructureMemberLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testDereferencingAStructureMemberUsingArrow(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "DereferencingAStructureMemberUsingArrow.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "DereferencingAStructureMemberUsingArrowLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testArrayWithinAStructure(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "ArrayWithinAStructure.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "ArrayWithinAStructureLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testStructureContainingArrayOfStructures(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "StructureContainingArrayOfStructures.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "StructureContainingArrayOfStructuresLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testStructureRandomTest(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "StructureRandomTest.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "StructureRandomTestLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testArrayOfPointersWithinStructure(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "ArrayPointerWithinStructure.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "ArrayPointerWithinStructureLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testStructureTest(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "StructureTest.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "StructureTestLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testInitializingStructureMembers(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "StructureTest2.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "StructureTest2LLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testStructureTestInitialization(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "StructureTestInitialization.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "StructureTestInitializationLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testAssigningStructureVariable(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "AssigningStructureVariable.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "AssigningStructureVariableLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testLocalStructureAssignment(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "LocalStructureAssignment.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "LocalStructureAssignmentLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testLocalStructureTest2(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "LocalStructureTest2.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "LocalStructureTest2LLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testInnerStructAssignment(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "InnerStructAssignment.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "InnerStructAssignmentLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testStructAssignment(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "StructureAssignment.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "StructureAssignmentLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testComplexStruct(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectPath, "ComplexStruct.c");
			assertTrue(ComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "ComplexStructLLVMIR.bc"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
}

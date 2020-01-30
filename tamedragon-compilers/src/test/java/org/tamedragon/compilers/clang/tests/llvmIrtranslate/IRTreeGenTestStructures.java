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

public class IRTreeGenTestStructures extends LLVMBaseTest{
	
	private CompilerSettings compilerSettings;
	private String projectPath = "CSrc/TranslateToLLVMIR/Structures/";
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
	public void testLocalStructureEg(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectRootPath + projectPath, "LocalStructureEg.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "LocalStructureEgLLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void testGlobalStructureEg(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectRootPath + projectPath, "GlobalStructureEg.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "GlobalStructureEgLLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testAssigningAStructElement(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectRootPath + projectPath, "AssigningAStructElement.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "AssigningAStructElementLLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testStructureWithinStructure(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectRootPath + projectPath, "StructureWithinStructure.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "StructureWithinStructureLLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testDereferencingAStructure(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectRootPath + projectPath, "DereferencingAStructureMember.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "DereferencingAStructureMemberLLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testDereferencingAStructureMemberUsingArrow(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectRootPath + projectPath, "DereferencingAStructureMemberUsingArrow.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "DereferencingAStructureMemberUsingArrowLLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testArrayWithinAStructure(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectRootPath + projectPath, "ArrayWithinAStructure.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "ArrayWithinAStructureLLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testStructureContainingArrayOfStructures(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectRootPath + projectPath, "StructureContainingArrayOfStructures.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "StructureContainingArrayOfStructuresLLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testStructureRandomTest(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectRootPath + projectPath, "StructureRandomTest.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "StructureRandomTestLLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testArrayOfPointersWithinStructure(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectRootPath + projectPath, "ArrayPointerWithinStructure.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "ArrayPointerWithinStructureLLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testStructureTest(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectRootPath + projectPath, "StructureTest.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "StructureTestLLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testInitializingStructureMembers(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectRootPath + projectPath, "StructureTest2.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "StructureTest2LLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testStructureTestInitialization(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectRootPath + projectPath, "StructureTestInitialization.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "StructureTestInitializationLLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testAssigningStructureVariable(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectRootPath + projectPath, "AssigningStructureVariable.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "AssigningStructureVariableLLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testLocalStructureAssignment(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectRootPath + projectPath, "LocalStructureAssignment.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "LocalStructureAssignmentLLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testLocalStructureTest2(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectRootPath + projectPath, "LocalStructureTest2.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "LocalStructureTest2LLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testInnerStructAssignment(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectRootPath + projectPath, "InnerStructAssignment.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "InnerStructAssignmentLLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testStructAssignment(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectRootPath + projectPath, "StructureAssignment.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "StructureAssignmentLLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testComplexStruct(){
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectRootPath + projectPath, "ComplexStruct.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "ComplexStructLLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
}

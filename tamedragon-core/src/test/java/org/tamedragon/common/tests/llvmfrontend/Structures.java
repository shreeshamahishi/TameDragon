package org.tamedragon.common.tests.llvmfrontend;

import java.io.File;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.tamedragon.common.utils.LLVMIRUtils;
import org.tamedragon.common.utils.ComparisionUtils;

public class Structures  {

	String rootPath = "CToLLVMIRTranslate/Structures";

	@Test
	public void test1(){

		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(rootPath).getFile());
		File fileArr[] = file.listFiles();
		for(File f : fileArr){
			String llvmFileName = f.getName();
			
			if("StructureAssignmentLLVMIR.ll".equals(llvmFileName)
			   || "InnerStructAssignmentLLVMIR.ll".equals(llvmFileName))	{
				continue;
			}
			
			if(llvmFileName.matches("[[a-z][A-Z][0-9]]+\\.ll")){				
				LLVMIRUtils llvirUtils = new LLVMIRUtils();
				List<String> instrs = llvirUtils.getInstructionsList(rootPath, llvmFileName);
				assertTrue(ComparisionUtils.compare(instrs, rootPath, llvmFileName));
			}
		}
	}
	
	@Test
	public void testComplexStruct(){
		String llvmFileName = "ComplexStructLLVMIR.ll";
		LLVMIRUtils llvmirUtils = new LLVMIRUtils();
		List<String> instrs = llvmirUtils.getInstructionsList(rootPath, llvmFileName);
		assertTrue(ComparisionUtils.compare(instrs, rootPath, llvmFileName));
	}
	
	@Test
	public void testGlobalStructure(){
		String llvmFileName = "GlobalStructureEgLLVMIR.ll";
		LLVMIRUtils llvmirUtils = new LLVMIRUtils();
		List<String> instrs = llvmirUtils.getInstructionsList(rootPath, llvmFileName);
		assertTrue(ComparisionUtils.compare(instrs, rootPath, llvmFileName));
	}
	
	@Test
	public void testAssigningAStructElement(){
		String llvmFileName = "AssigningAStructElementLLVMIR.ll";
		LLVMIRUtils llvmirUtils = new LLVMIRUtils();
		List<String> instrs = llvmirUtils.getInstructionsList(rootPath, llvmFileName);
		assertTrue(ComparisionUtils.compare(instrs, rootPath, llvmFileName));
	}
	
	@Test
	public void testDereferencingAStructMember(){
		String llvmFileName = "DereferencingAStructureMemberLLVMIR.ll";
		LLVMIRUtils llvmirUtils = new LLVMIRUtils();
		List<String> instrs = llvmirUtils.getInstructionsList(rootPath, llvmFileName);
		assertTrue(ComparisionUtils.compare(instrs, rootPath, llvmFileName));
	}
	
	@Test
	public void testDereferencingAStructureMemberUsingArrow(){
		String llvmFileName = "DereferencingAStructureMemberUsingArrowLLVMIR.ll";
		LLVMIRUtils llvmirUtils = new LLVMIRUtils();
		List<String> instrs = llvmirUtils.getInstructionsList(rootPath, llvmFileName);
		assertTrue(ComparisionUtils.compare(instrs, rootPath, llvmFileName));
	}
	
	@Ignore
	@Test
	public void testInnerStructAssignment(){
		String llvmFileName = "InnerStructAssignmentLLVMIR.ll";
		LLVMIRUtils llvmirUtils = new LLVMIRUtils();
		List<String> instrs = llvmirUtils.getInstructionsList(rootPath, llvmFileName);
		assertTrue(ComparisionUtils.compare(instrs, rootPath, llvmFileName));
	}
	
	@Test
	public void testLocalStructure1(){
		String llvmFileName = "LocalStructureEgLLVMIR.ll";
		LLVMIRUtils llvmirUtils = new LLVMIRUtils();
		List<String> instrs = llvmirUtils.getInstructionsList(rootPath, llvmFileName);
		assertTrue(ComparisionUtils.compare(instrs, rootPath, llvmFileName));
	}
	
	@Test
	public void testLocalStructureTest2(){
		String llvmFileName = "LocalStructureTest2LLVMIR.ll";
		LLVMIRUtils llvmirUtils = new LLVMIRUtils();
		List<String> instrs = llvmirUtils.getInstructionsList(rootPath, llvmFileName);
		assertTrue(ComparisionUtils.compare(instrs, rootPath, llvmFileName));
	}
	
	@Ignore
	@Test
	public void testStructureAssignment(){
		String llvmFileName = "StructureAssignmentLLVMIR.ll";
		LLVMIRUtils llvmirUtils = new LLVMIRUtils();
		List<String> instrs = llvmirUtils.getInstructionsList(rootPath, llvmFileName);
		assertTrue(ComparisionUtils.compare(instrs, rootPath, llvmFileName));
	}
	
	@Test
	public void testStructureContainingArrayOfStructures(){
		String llvmFileName = "StructureContainingArrayOfStructuresLLVMIR.ll";
		LLVMIRUtils llvmirUtils = new LLVMIRUtils();
		List<String> instrs = llvmirUtils.getInstructionsList(rootPath, llvmFileName);
		assertTrue(ComparisionUtils.compare(instrs, rootPath, llvmFileName));
	}
	
	@Test
	public void testStructureWithinStructure(){
		String llvmFileName = "StructureWithinStructureLLVMIR.ll";
		LLVMIRUtils llvmirUtils = new LLVMIRUtils();
		List<String> instrs = llvmirUtils.getInstructionsList(rootPath, llvmFileName);
		assertTrue(ComparisionUtils.compare(instrs, rootPath, llvmFileName));
	}
}
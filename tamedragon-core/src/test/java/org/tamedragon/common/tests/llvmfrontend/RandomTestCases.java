package org.tamedragon.common.tests.llvmfrontend;

import java.io.File;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.tamedragon.common.utils.LLVMIRUtils;
import org.tamedragon.common.utils.ComparisionUtils;

public class RandomTestCases {
	String rootPath = "CToLLVMIRTranslate/RandomTestCases";

	@Ignore
	@Test
	public void test1(){

		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(rootPath).getFile());
		File fileArr[] = file.listFiles();
		for(File f : fileArr){
			String llvmFileName = f.getName();
			// TODO array initialization is pending DijskstraAlgorithmLLVMIR.ll
			if(llvmFileName.equals("FloydWarshallAlgForShortestPathLLVMIR.ll"))
				continue;

			// TODO Correct these too
			if(llvmFileName.equals("DESLLVMIR.ll" ) || llvmFileName.equals("PrintingCharactersLLVMIR.ll") 
					|| llvmFileName.equals("RSALLVMIR.ll")||llvmFileName.equals("ArrayOfStructuresLLVMIR.ll"))
				continue;

			// TODO Correct these too (maybe simple errors)
			if(llvmFileName.equals("LinkedListLLVMIR.ll") || llvmFileName.equals("UnionTest3LLVMIR.ll")){				
				continue;
			}

			if(llvmFileName.matches("[[a-z][A-Z][0-9]]+\\.ll")){	
				System.out.println("*************** FILE CONSIDERED = " + llvmFileName);
				LLVMIRUtils llvirUtils = new LLVMIRUtils();
				List<String> instrs = llvirUtils.getInstructionsList(rootPath, llvmFileName);
				assertTrue(ComparisionUtils.compare(instrs, rootPath, llvmFileName));
			}
		}
	}

	@Ignore
	@Test
	public void testFloydWarshallAlg(){
		String llvmFileName = "FloydWarshallAlgForShortestPathLLVMIR.ll";
		LLVMIRUtils llvmirUtils = new LLVMIRUtils();
		List<String> instrs = llvmirUtils.getInstructionsList(rootPath, llvmFileName);
		assertTrue(ComparisionUtils.compare(instrs, rootPath, llvmFileName));
	}
	
	@Ignore
	@Test
	public void testDES(){
		String llvmFileName = "DESLLVMIR.ll";
		LLVMIRUtils llvmirUtils = new LLVMIRUtils();
		List<String> instrs = llvmirUtils.getInstructionsList(rootPath, llvmFileName);
		assertTrue(ComparisionUtils.compare(instrs, rootPath, llvmFileName));
	}
	
	@Ignore
	@Test
	public void testPrintChars(){
		String llvmFileName = "PrintingCharactersLLVMIR.ll";
		LLVMIRUtils llvmirUtils = new LLVMIRUtils();
		List<String> instrs = llvmirUtils.getInstructionsList(rootPath, llvmFileName);
		assertTrue(ComparisionUtils.compare(instrs, rootPath, llvmFileName));
	}
	
	@Ignore
	@Test
	public void testRSA(){
		String llvmFileName = "RSALLVMIR.ll";
		LLVMIRUtils llvmirUtils = new LLVMIRUtils();
		List<String> instrs = llvmirUtils.getInstructionsList(rootPath, llvmFileName);
		assertTrue(ComparisionUtils.compare(instrs, rootPath, llvmFileName));
	}
	
	@Ignore
	@Test
	public void testArraysOfStructs(){
		String llvmFileName = "ArrayOfStructuresLLVMIR.ll";
		LLVMIRUtils llvmirUtils = new LLVMIRUtils();
		List<String> instrs = llvmirUtils.getInstructionsList(rootPath, llvmFileName);
		assertTrue(ComparisionUtils.compare(instrs, rootPath, llvmFileName));
	}
	
	@Ignore
	@Test
	public void testLinkedListLLVMIR(){
		String llvmFileName = "LinkedListLLVMIR.ll";
		LLVMIRUtils llvmirUtils = new LLVMIRUtils();
		List<String> instrs = llvmirUtils.getInstructionsList(rootPath, llvmFileName);
		assertTrue(ComparisionUtils.compare(instrs, rootPath, llvmFileName));
	}

	@Ignore
	@Test
	public void testUnion3(){
		String llvmFileName = "UnionTest3LLVMIR.ll";
		LLVMIRUtils llvmirUtils = new LLVMIRUtils();
		List<String> instrs = llvmirUtils.getInstructionsList(rootPath, llvmFileName);
		assertTrue(ComparisionUtils.compare(instrs, rootPath, llvmFileName));
	}
}
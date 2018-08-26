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

	@Test
	public void test1(){

		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(rootPath).getFile());
		File fileArr[] = file.listFiles();
		for(File f : fileArr){
			String llvmFileName = f.getName();
			// TODO array initialization is pending DijskstraAlgorithmLLVMIR.bc
			if(llvmFileName.equals("FloydWarshallAlgForShortestPathLLVMIR.bc"))
				continue;

			// TODO Correct these too
			if(llvmFileName.equals("DESLLVMIR.bc" ) || llvmFileName.equals("PrintingCharactersLLVMIR.bc") 
					|| llvmFileName.equals("RSALLVMIR.bc")||llvmFileName.equals("ArrayOfStructuresLLVMIR.bc"))
				continue;

			// TODO Correct these too (maybe simple errors)
			if(llvmFileName.equals("LinkedListLLVMIR.bc") || llvmFileName.equals("UnionTest3LLVMIR.bc")){				
				continue;
			}

			if(llvmFileName.matches("[[a-z][A-Z][0-9]]+\\.bc")){				
				LLVMIRUtils llvirUtils = new LLVMIRUtils();
				List<String> instrs = llvirUtils.getInstructionsList(rootPath, llvmFileName);
				assertTrue(ComparisionUtils.compare(instrs, rootPath, llvmFileName));
			}
		}
	}

	@Ignore
	@Test
	public void testFloydWarshallAlg(){
		String llvmFileName = "FloydWarshallAlgForShortestPathLLVMIR.bc";
		LLVMIRUtils llvmirUtils = new LLVMIRUtils();
		List<String> instrs = llvmirUtils.getInstructionsList(rootPath, llvmFileName);
		assertTrue(ComparisionUtils.compare(instrs, rootPath, llvmFileName));
	}
	
	@Ignore
	@Test
	public void testDES(){
		String llvmFileName = "DESLLVMIR.bc";
		LLVMIRUtils llvmirUtils = new LLVMIRUtils();
		List<String> instrs = llvmirUtils.getInstructionsList(rootPath, llvmFileName);
		assertTrue(ComparisionUtils.compare(instrs, rootPath, llvmFileName));
	}
	
	@Ignore
	@Test
	public void testPrintChars(){
		String llvmFileName = "PrintingCharactersLLVMIR.bc";
		LLVMIRUtils llvmirUtils = new LLVMIRUtils();
		List<String> instrs = llvmirUtils.getInstructionsList(rootPath, llvmFileName);
		assertTrue(ComparisionUtils.compare(instrs, rootPath, llvmFileName));
	}
	
	@Ignore
	@Test
	public void testRSA(){
		String llvmFileName = "RSALLVMIR.bc";
		LLVMIRUtils llvmirUtils = new LLVMIRUtils();
		List<String> instrs = llvmirUtils.getInstructionsList(rootPath, llvmFileName);
		assertTrue(ComparisionUtils.compare(instrs, rootPath, llvmFileName));
	}
	
	@Ignore
	@Test
	public void testArraysOfStructs(){
		String llvmFileName = "ArrayOfStructuresLLVMIR.bc";
		LLVMIRUtils llvmirUtils = new LLVMIRUtils();
		List<String> instrs = llvmirUtils.getInstructionsList(rootPath, llvmFileName);
		assertTrue(ComparisionUtils.compare(instrs, rootPath, llvmFileName));
	}
	
	@Ignore
	@Test
	public void testLinkedListLLVMIR(){
		String llvmFileName = "LinkedListLLVMIR.bc";
		LLVMIRUtils llvmirUtils = new LLVMIRUtils();
		List<String> instrs = llvmirUtils.getInstructionsList(rootPath, llvmFileName);
		assertTrue(ComparisionUtils.compare(instrs, rootPath, llvmFileName));
	}

	@Ignore
	@Test
	public void testUnion3(){
		String llvmFileName = "UnionTest3LLVMIR.bc";
		LLVMIRUtils llvmirUtils = new LLVMIRUtils();
		List<String> instrs = llvmirUtils.getInstructionsList(rootPath, llvmFileName);
		assertTrue(ComparisionUtils.compare(instrs, rootPath, llvmFileName));
	}
}
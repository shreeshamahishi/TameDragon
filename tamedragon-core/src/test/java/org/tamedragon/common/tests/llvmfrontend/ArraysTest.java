package org.tamedragon.common.tests.llvmfrontend;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.tamedragon.common.utils.LLVMIRUtils;
import org.tamedragon.common.utils.ComparisionUtils;


public class ArraysTest  {
	String rootPath = "CToLLVMIRTranslate/Arrays";

	private LLVMIRUtils llvmirUtils;
	
	@Before
	public void setUp(){
		llvmirUtils = new LLVMIRUtils();
	}
	
	@Test
	public void test1(){

		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(rootPath).getFile());
		File fileArr[] = file.listFiles();
		for(File f : fileArr){
			String llvmFileName = f.getName();
			if(llvmFileName.equals("VariableLengthArrayLLVMIR.ll") 
					|| llvmFileName.equals("PassingDoubleDimensionalArrayLLVMIR.ll")
					|| llvmFileName.equals("ArrayOfStringsLLVMIR.ll")
					|| llvmFileName.equals("ArrayOfStructsLLVMIR.ll"))
				continue;
			if(llvmFileName.matches("[[a-z][A-Z][0-9]]+\\.ll")){		
				System.out.println("Checking out Arrays "  + llvmFileName);
				List<String> instrs = llvmirUtils.getInstructionsList(rootPath, llvmFileName);
				assertTrue(ComparisionUtils.compare(instrs, rootPath, llvmFileName));
			}
		}
	}

	@Ignore
	@Test
	public void testVariableLengthArray(){
		String llvmFileName = "VariableLengthArrayLLVMIR.ll";
		List<String> instrs = llvmirUtils.getInstructionsList(rootPath, llvmFileName);
		assertTrue(ComparisionUtils.compare(instrs, rootPath, llvmFileName));
	}

	//@Ignore
	@Test
	public void testPassDoubleDimArray(){
		String llvmFileName = "PassingDoubleDimensionalArrayLLVMIR.ll";
		List<String> instrs = llvmirUtils.getInstructionsList(rootPath, llvmFileName);
		assertTrue(ComparisionUtils.compare(instrs, rootPath, llvmFileName));
	}

	@Ignore
	@Test
	public void testArrayOfStrings(){
		String llvmFileName = "ArrayOfStringsLLVMIR.ll";
		List<String> instrs = llvmirUtils.getInstructionsList(rootPath, llvmFileName);
		assertTrue(ComparisionUtils.compare(instrs, rootPath, llvmFileName));
	}

	@Ignore
	@Test
	public void testArrayOfStructs(){
		String llvmFileName = "ArrayOfStructsLLVMIR.ll";
		List<String> instrs = llvmirUtils.getInstructionsList(rootPath, llvmFileName);
		assertTrue(ComparisionUtils.compare(instrs, rootPath, llvmFileName));
	}

}

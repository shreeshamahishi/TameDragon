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
			if(llvmFileName.equals("VariableLengthArrayLLVMIR.bc") 
					|| llvmFileName.equals("PassingDoubleDimensionalArrayLLVMIR.bc")
					|| llvmFileName.equals("ArrayOfStringsLLVMIR.bc")
					|| llvmFileName.equals("ArrayOfStructsLLVMIR.bc"))
				continue;
			if(llvmFileName.matches("[[a-z][A-Z][0-9]]+\\.bc")){		
				System.out.println("Checking out Arrays "  + llvmFileName);
				List<String> instrs = llvmirUtils.getInstructionsList(rootPath, llvmFileName);
				assertTrue(ComparisionUtils.compare(instrs, rootPath, llvmFileName));
			}
		}
	}

	@Ignore
	@Test
	public void testVariableLengthArray(){
		String llvmFileName = "VariableLengthArrayLLVMIR.bc";
		List<String> instrs = llvmirUtils.getInstructionsList(rootPath, llvmFileName);
		assertTrue(ComparisionUtils.compare(instrs, rootPath, llvmFileName));
	}

	//@Ignore
	@Test
	public void testPassDoubleDimArray(){
		String llvmFileName = "PassingDoubleDimensionalArrayLLVMIR.bc";
		List<String> instrs = llvmirUtils.getInstructionsList(rootPath, llvmFileName);
		assertTrue(ComparisionUtils.compare(instrs, rootPath, llvmFileName));
	}

	@Ignore
	@Test
	public void testArrayOfStrings(){
		String llvmFileName = "ArrayOfStringsLLVMIR.bc";
		List<String> instrs = llvmirUtils.getInstructionsList(rootPath, llvmFileName);
		assertTrue(ComparisionUtils.compare(instrs, rootPath, llvmFileName));
	}

	@Ignore
	@Test
	public void testArrayOfStructs(){
		String llvmFileName = "ArrayOfStructsLLVMIR.bc";
		List<String> instrs = llvmirUtils.getInstructionsList(rootPath, llvmFileName);
		assertTrue(ComparisionUtils.compare(instrs, rootPath, llvmFileName));
	}

}

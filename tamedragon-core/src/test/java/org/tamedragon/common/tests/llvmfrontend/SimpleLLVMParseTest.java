package org.tamedragon.common.tests.llvmfrontend;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.tamedragon.common.utils.ComparisionUtils;
import org.tamedragon.common.utils.LLVMIRUtils;


public class SimpleLLVMParseTest {
	String rootPath = "CToLLVMIRTranslate" ;
	
	@Test
	public void test1(){
		String llvmFileName = "test.bc";
		LLVMIRUtils llvirUtils = new LLVMIRUtils();
		List<String> instrs = llvirUtils.getInstructionsList(rootPath, llvmFileName);
		assertTrue(ComparisionUtils.compare(instrs, rootPath, llvmFileName));
	}
}

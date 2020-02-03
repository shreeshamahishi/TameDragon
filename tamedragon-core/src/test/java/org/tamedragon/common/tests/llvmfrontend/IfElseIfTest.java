package org.tamedragon.common.tests.llvmfrontend;

import java.io.File;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.tamedragon.common.utils.LLVMIRUtils;
import org.tamedragon.common.utils.ComparisionUtils;

public class IfElseIfTest {
	String rootPath = "CToLLVMIRTranslate/IfElseIfTest";

	@Ignore
	@Test
	public void test1(){
		File file = new File(rootPath);
		File fileArr[] = file.listFiles();
		for(File f : fileArr){
			String llvmFileName = f.getName();
			if(llvmFileName.matches("[[a-z][A-Z][0-9]]+\\.ll")){
				LLVMIRUtils llvirUtils = new LLVMIRUtils();
				List<String> instrs = llvirUtils.getInstructionsList(rootPath, llvmFileName);						
				assertTrue(ComparisionUtils.compare(instrs, rootPath, llvmFileName));
			}
		}
	}
}
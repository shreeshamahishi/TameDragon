package org.tamedragon.common.tests.llvmfrontend;

import java.util.List;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.tamedragon.common.utils.ComparisionUtils;
import org.tamedragon.common.utils.LLVMIRUtils;

public class BinaryOperators  {
	String rootPath = "CToLLVMIRTranslate/BinaryOperators";

	private LLVMIRUtils llvmirUtils;
	
	@Before
	public void setUp(){
		llvmirUtils = new LLVMIRUtils();
	}
	
	
	@Test
	public void testSimpleBinOperation(){
		String llvmFileName = "SimpleBinOpLLVMIR.bc";
		List<String> instrs = llvmirUtils.getInstructionsList(rootPath, llvmFileName);
		assertTrue(ComparisionUtils.compare(instrs, rootPath, llvmFileName));
	}
	
	@Test
	public void testSimpleBinOperationWithGlobalVar(){
		String llvmFileName = "SimpleBinOpWithGlobalVarLLVMIR.bc";
		List<String> instrs = llvmirUtils.getInstructionsList(rootPath, llvmFileName);
		assertTrue(ComparisionUtils.compare(instrs, rootPath, llvmFileName));
	}
}

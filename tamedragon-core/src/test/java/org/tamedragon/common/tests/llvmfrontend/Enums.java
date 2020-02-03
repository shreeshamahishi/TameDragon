package org.tamedragon.common.tests.llvmfrontend;

import java.io.File;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.tamedragon.common.utils.LLVMIRUtils;
import org.tamedragon.common.utils.ComparisionUtils;

public class Enums  {
	String rootPath = "CToLLVMIRTranslate/Enums";

	@Test
	public void enumTest1(){
		String llvmFileName = "enumTest1LLVMIR.ll";
		LLVMIRUtils llvmirUtils = new LLVMIRUtils();
		List<String> instrs = llvmirUtils.getInstructionsList(rootPath, llvmFileName);
		assertTrue(ComparisionUtils.compare(instrs, rootPath, llvmFileName));
	}

	@Ignore
	@Test
	public void enumTest2(){
		String llvmFileName = "enumTest2LLVMIR.ll";
		LLVMIRUtils llvmirUtils = new LLVMIRUtils();
		List<String> instrs = llvmirUtils.getInstructionsList(rootPath, llvmFileName);
		assertTrue(ComparisionUtils.compare(instrs, rootPath, llvmFileName));
	}

	@Ignore
	@Test
	public void enumTest3(){
		String llvmFileName = "enumTest3LLVMIR.ll";
		LLVMIRUtils llvmirUtils = new LLVMIRUtils();
		List<String> instrs = llvmirUtils.getInstructionsList(rootPath, llvmFileName);
		assertTrue(ComparisionUtils.compare(instrs, rootPath, llvmFileName));
	}

	@Ignore
	@Test
	public void enumTest4(){
		String llvmFileName = "enumTest4LLVMIR.ll";
		LLVMIRUtils llvmirUtils = new LLVMIRUtils();
		List<String> instrs = llvmirUtils.getInstructionsList(rootPath, llvmFileName);
		assertTrue(ComparisionUtils.compare(instrs, rootPath, llvmFileName));
	}

	@Ignore
	@Test
	public void enumTest5(){
		String llvmFileName = "enumTest5LLVMIR.ll";
		LLVMIRUtils llvmirUtils = new LLVMIRUtils();
		List<String> instrs = llvmirUtils.getInstructionsList(rootPath, llvmFileName);
		assertTrue(ComparisionUtils.compare(instrs, rootPath, llvmFileName));
	}

	@Ignore
	@Test
	public void enumTest6(){
		String llvmFileName = "enumTest6LLVMIR.ll";
		LLVMIRUtils llvmirUtils = new LLVMIRUtils();
		List<String> instrs = llvmirUtils.getInstructionsList(rootPath, llvmFileName);
		assertTrue(ComparisionUtils.compare(instrs, rootPath, llvmFileName));
	}

	@Ignore
	@Test
	public void enumTest7(){
		String llvmFileName = "enumTest7LLVMIR.ll";
		LLVMIRUtils llvmirUtils = new LLVMIRUtils();
		List<String> instrs = llvmirUtils.getInstructionsList(rootPath, llvmFileName);
		assertTrue(ComparisionUtils.compare(instrs, rootPath, llvmFileName));
	}
}

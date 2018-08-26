package org.tamedragon.common.tests.optimizations.scalar;

import java.util.Iterator;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.Module;
import org.tamedragon.common.llvmir.utils.LLVMIREmitter;
import org.tamedragon.common.optimization.LocalCSE;
import org.tamedragon.common.utils.LLVMIRUtils;
import org.tamedragon.common.utils.ComparisionUtils;

public class LocalCSETest  {

	private final static String ROOT_PATH = "ScalarOpts/LocalCSE";

	@Test
	public void test1() throws Exception {
		String llvmSrcFileName =  "LocalCSETest1Src.bc";
		String llvmOutFile = "LocalCSETest1Out.bc";

		runLocalCSE(llvmSrcFileName, llvmOutFile);
	}
	
	@Test
	public void test2() throws Exception {
		String llvmSrcFileName =  "LocalCSETest2Src.bc";
		String llvmOutFile = "LocalCSETest2Out.bc";

		runLocalCSE(llvmSrcFileName, llvmOutFile);
	}
	
	@Test
	public void test3() throws Exception {
		String llvmSrcFileName =  "LocalCSETest3Src.bc";
		String llvmOutFile = "LocalCSETest3Out.bc";

		runLocalCSE(llvmSrcFileName, llvmOutFile);
	}
	
	@Test
	public void test4() throws Exception {
		String llvmSrcFileName =  "LocalCSETest4Src.bc";
		String llvmOutFile = "LocalCSETest4Out.bc";

		runLocalCSE(llvmSrcFileName, llvmOutFile);
	}
	
	@Test
	public void test5() throws Exception {
		String llvmSrcFileName =  "LocalCSETest5Src.bc";
		String llvmOutFile = "LocalCSETest5Out.bc";

		runLocalCSE(llvmSrcFileName, llvmOutFile);
	}
	
	@Test
	public void test6() throws Exception {
		String llvmSrcFileName =  "VariableLengthArraySrc.bc";
		String llvmOutFile = "VariableLengthArrayOut.bc";

		runLocalCSE(llvmSrcFileName, llvmOutFile);
	}
	
	@Test
	public void test7() throws Exception {
		String llvmSrcFileName =  "DereferencingAStructureMemberSrc.bc";
		String llvmOutFile = "DereferencingAStructureMemberOut.bc";

		runLocalCSE(llvmSrcFileName, llvmOutFile);
	}
	
	@Test
	public void test8() throws Exception {
		String llvmSrcFileName =  "addressOfArraySrc.bc";
		String llvmOutFile = "addressOfArrayOut.bc";

		runLocalCSE(llvmSrcFileName, llvmOutFile);
	}
	
	@Ignore
	@Test
	public void test10() throws Exception {
		String llvmSrcFileName =  "FunctionPointerSrc.bc";
		String llvmOutFile = "FunctionPointerOut.bc";

		runLocalCSE(llvmSrcFileName, llvmOutFile);
	}
	
	@Test
	public void test11() throws Exception {
		String llvmSrcFileName =  "GlobalSrc.bc";
		String llvmOutFile = "GlobalOut.bc";

		runLocalCSE(llvmSrcFileName, llvmOutFile);
	}
	
	@Test
	public void test12() throws Exception {
		String llvmSrcFileName =  "AnotherExampleOnScopeSrc.bc";
		String llvmOutFile = "AnotherExampleOnScopeOut.bc";

		runLocalCSE(llvmSrcFileName, llvmOutFile);
	}
	
	@Test
	public void test13() throws Exception {
		String llvmSrcFileName =  "LocalCSETest6Src.bc";
		String llvmOutFile = "LocalCSETest6Out.bc";

		runLocalCSE(llvmSrcFileName, llvmOutFile);
	}
	
	@Test
	public void test14() throws Exception {
		String llvmSrcFileName =  "LocalCSETest7Src.bc";
		String llvmOutFile = "LocalCSETest7Out.bc";

		runLocalCSE(llvmSrcFileName, llvmOutFile);
	}
	
	private void runLocalCSE(String srcFileName, String llvmOutFile)
			throws Exception {

		LLVMIRUtils llvmirUtils = new LLVMIRUtils();
		llvmirUtils.getInstructionsList(ROOT_PATH, srcFileName);

		Module module = llvmirUtils.getModule();
		List<Function> functions = module.getFunctions();

		// There can be only one function to test mem2reg in the module.
		Function function = functions.get(0);

		// Local Common Subexpression Elimination
		LocalCSE localCSE = new LocalCSE();
		Iterator<BasicBlock> basicBlockIterator = function.basicBlocksIterator();
		while(basicBlockIterator.hasNext()) {
			localCSE.localCSE(basicBlockIterator.next());
		}
		System.out.println("AFTER LOCAL CSE(Common SubExpression Elimination): ");
		LLVMIREmitter emitter = llvmirUtils.getEmitter();
		emitter.reset();
		List<String> instrsAfterOpt = emitter.emitInstructions(module);
		llvmirUtils.printAsm(instrsAfterOpt);
		assertTrue(ComparisionUtils.compare(instrsAfterOpt, ROOT_PATH, llvmOutFile));
	}
}

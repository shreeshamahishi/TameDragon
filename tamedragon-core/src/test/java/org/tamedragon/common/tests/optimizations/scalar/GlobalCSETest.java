package org.tamedragon.common.tests.optimizations.scalar;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.Module;
import org.tamedragon.common.llvmir.utils.LLVMIREmitter;
import org.tamedragon.common.optimization.GlobalCSE;
import org.tamedragon.common.optimization.LocalCSE;
import org.tamedragon.common.utils.LLVMIRUtils;
import org.tamedragon.common.utils.ComparisionUtils;

public class GlobalCSETest {

	private static final String ROOT_PATH = "ScalarOpts/GlobalCSE";

	@Test
	public void test1() throws Exception {
		String llvmSrcFile =  "GlobalCSETest1Src.ll";
		String llvmGlobalCSEOutFileName = "GlobalCSETest1Out.ll";

		runGlobalCSE(llvmSrcFile, llvmGlobalCSEOutFileName);
	}
	
	@Test
	public void test2() throws Exception {
		String llvmSrcFile =  "IfElseIfTestSrc.ll";
		String llvmGlobalCSEOutFileName = "IfElseIfTestOut.ll";

		runGlobalCSE(llvmSrcFile, llvmGlobalCSEOutFileName);
	}
	
	@Test
	public void test3() throws Exception {
		String llvmSrcFile =  "IfElseIfWithinForLoopSrc.ll";
		String llvmGlobalCSEOutFileName = "IfElseIfWithinForLoopOut.ll";

		runGlobalCSE(llvmSrcFile, llvmGlobalCSEOutFileName);
	}
	
	@Test
	public void test4() throws Exception {
		String llvmSrcFile =  "IfElseIfWithinLoopWithBreakContinueReturnSrc.ll";
		String llvmGlobalCSEOutFileName = "IfElseIfWithinLoopWithBreakContinueReturnOut.ll";

		runGlobalCSE(llvmSrcFile, llvmGlobalCSEOutFileName);
	}
	
	@Test
	public void test5() throws Exception {
		String llvmSrcFile =  "ForWithIfElse2Src.ll";
		String llvmGlobalCSEOutFileName = "ForWithIfElse2Out.ll";

		runGlobalCSE(llvmSrcFile, llvmGlobalCSEOutFileName);
	}
	
	@Test
	public void test6() throws Exception {
		String llvmSrcFile =  "BinarySearchSrc.ll";
		String llvmGlobalCSEOutFileName = "BinarySearchOut.ll";

		runGlobalCSE(llvmSrcFile, llvmGlobalCSEOutFileName);
	}
	
	@Test
	public void test7() throws Exception {
		String llvmSrcFile =  "helloSrc.ll";
		String llvmGlobalCSEOutFileName = "helloOut.ll";

		runGlobalCSE(llvmSrcFile, llvmGlobalCSEOutFileName);
	}
	
	@Test
	public void test8() throws Exception {
		String llvmSrcFile =  "BubbleSortSrc.ll";
		String llvmGlobalCSEOutFileName = "BubbleSortOut.ll";

		runGlobalCSE(llvmSrcFile, llvmGlobalCSEOutFileName);
	}
	
	@Test
	public void test9() throws Exception {
		String llvmSrcFile =  "insertSrc.ll";
		String llvmGlobalCSEOutFileName = "insertOut.ll";

		runGlobalCSE(llvmSrcFile, llvmGlobalCSEOutFileName);
	}
	
	@Test
	public void test10() throws Exception {
		String llvmSrcFile =  "SimpleAA1Src.ll";
		String llvmGlobalCSEOutFileName = "SimpleAA1Out.ll";

		runGlobalCSE(llvmSrcFile, llvmGlobalCSEOutFileName);
	}
	
	private void runGlobalCSE(String llvmSrcFile, String llvmOutFileName) throws Exception  {
		LLVMIRUtils llvmirUtils = new LLVMIRUtils();
		llvmirUtils.getInstructionsList(ROOT_PATH, llvmSrcFile);

		Module module = llvmirUtils.getModule();
		List<Function> functions = module.getFunctions();

		// There can be only one function to test SCCP in the module.

		Function function = functions.get(0);

		// Local Common Subexpression Elimination
		LocalCSE localCSE = new LocalCSE();
		Iterator<BasicBlock> basicBlockIterator = function.basicBlocksIterator();
		while(basicBlockIterator.hasNext()) {
			localCSE.localCSE(basicBlockIterator.next());
		}
		System.out.println("AFTER LOCAL CSE(Local Common SubExpression Elimination): ");
		LLVMIREmitter emitter = llvmirUtils.getEmitter();
		emitter.reset();
		List<String> instrsAfterOpt = emitter.emitInstructions(module);
		llvmirUtils.printAsm(instrsAfterOpt);

		GlobalCSE globalCSE = new GlobalCSE();
		globalCSE.execute(function);
		System.out.println("AFTER GLOBAL CSE(Global Common SubExpression Elimination): ");
		emitter.reset();
		instrsAfterOpt = emitter.emitInstructions(module);
		llvmirUtils.printAsm(instrsAfterOpt);

		assertTrue(ComparisionUtils.compare(instrsAfterOpt, ROOT_PATH, llvmOutFileName));
	}

}

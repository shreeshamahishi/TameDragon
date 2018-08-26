package org.tamedragon.compilers.opts;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.Module;
import org.tamedragon.common.optimization.GlobalCSE;
import org.tamedragon.common.optimization.LocalCSE;
import org.tamedragon.common.optimization.MemToRegPromoter;
import org.tamedragon.common.utils.ComparisionUtils;
import org.tamedragon.compilers.LLVMBaseTest;

public class GlobalCSETest extends LLVMBaseTest{

	private static final String ROOT_PATH = "CSrc/Optimizations/GlobalCSE";

	@Test
	public void test1() throws Exception {
		String cSrcfilename =  "GlobalCSETest1.c";
		String llvmGlobalCSEOutFileName = "GlobalCSETest1.bc";

		runGlobalCSE(cSrcfilename, llvmGlobalCSEOutFileName);
	}
	
	@Test
	public void test2() throws Exception {
		String cSrcfilename =  "IfElseIfTest.c";
		String llvmGlobalCSEOutFileName = "IfElseIfTest.bc";

		runGlobalCSE(cSrcfilename, llvmGlobalCSEOutFileName);
	}
	
	@Test
	public void test3() throws Exception {
		String cSrcfilename =  "IfElseIfWithinForLoop.c";
		String llvmGlobalCSEOutFileName = "IfElseIfWithinForLoop.bc";

		runGlobalCSE(cSrcfilename, llvmGlobalCSEOutFileName);
	}
	
	@Test
	public void test4() throws Exception {
		String cSrcfilename =  "IfElseIfWithinLoopWithBreakContinueReturn.c";
		String llvmGlobalCSEOutFileName = "IfElseIfWithinLoopWithBreakContinueReturn.bc";

		runGlobalCSE(cSrcfilename, llvmGlobalCSEOutFileName);
	}
	
	@Test
	public void test5() throws Exception {
		String cSrcfilename =  "ForWithIfElse2.c";
		String llvmGlobalCSEOutFileName = "ForWithIfElse2.bc";

		runGlobalCSE(cSrcfilename, llvmGlobalCSEOutFileName);
	}
	
	@Test
	public void test6() throws Exception {
		String cSrcfilename =  "BinarySearch.c";
		String llvmGlobalCSEOutFileName = "BinarySearch.bc";

		runGlobalCSE(cSrcfilename, llvmGlobalCSEOutFileName);
	}
	
	@Test
	public void test7() throws Exception {
		String cSrcfilename =  "hello.c";
		String llvmGlobalCSEOutFileName = "hello.bc";

		runGlobalCSE(cSrcfilename, llvmGlobalCSEOutFileName);
	}
	
	@Test
	public void test8() throws Exception {
		String cSrcfilename =  "BubbleSort.c";
		String llvmGlobalCSEOutFileName = "BubbleSort.bc";

		runGlobalCSE(cSrcfilename, llvmGlobalCSEOutFileName);
	}
	
	@Test
	public void test9() throws Exception {
		String cSrcfilename =  "insert.c";
		String llvmGlobalCSEOutFileName = "insert.bc";

		runGlobalCSE(cSrcfilename, llvmGlobalCSEOutFileName);
	}
	
	@Test
	public void test10() throws Exception {
		String cSrcfilename =  "SimpleAA1.c";
		String llvmGlobalCSEOutFileName = "SimpleAA1.bc";

		runGlobalCSE(cSrcfilename, llvmGlobalCSEOutFileName);
	}
	
	private void runGlobalCSE(String cSrcfilename, String llvmOutFileName) throws Exception  {

		getRawLLVRIRInstrs(ROOT_PATH, cSrcfilename);

		Module module = getModule();
		List<Function> functions = module.getFunctions();

		// There can be only one function to test SCCP in the module.

		Function function = functions.get(0);

		// Mem2reg
		MemToRegPromoter memToRegPromoter = new MemToRegPromoter();
		memToRegPromoter.execute(function);
		System.out.println("AFTER MEM2REG: ");
		emitter.reset();
		List<String> instrsAfterOpt = emitter.emitInstructions(function);
		printAsm(instrsAfterOpt);
		
		// Local Common Subexpression Elimination
		LocalCSE localCSE = new LocalCSE();
		Iterator<BasicBlock> basicBlockIterator = function.basicBlocksIterator();
		while(basicBlockIterator.hasNext()) {
			localCSE.localCSE(basicBlockIterator.next());
		}
		System.out.println("******* AFTER LOCAL CSE(Local Common SubExpression Elimination): ");
		emitter.reset();
		instrsAfterOpt = emitter.emitInstructions(function);
		printAsm(instrsAfterOpt);

		GlobalCSE globalCSE = new GlobalCSE();
		globalCSE.execute(function);
		System.out.println("******* AFTER GLOBAL CSE(Global Common SubExpression Elimination): ");
		emitter.reset();
		instrsAfterOpt = emitter.emitInstructions(function);
		printAsm(instrsAfterOpt);

		assertTrue(ComparisionUtils.compare(instrsAfterOpt, ROOT_PATH, llvmOutFileName));
	}

}

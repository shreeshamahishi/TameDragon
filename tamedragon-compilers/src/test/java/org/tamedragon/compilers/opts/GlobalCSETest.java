package org.tamedragon.compilers.opts;

import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

import org.tamedragon.common.llvmir.instructions.LLVMUtility;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.Module;
import org.tamedragon.common.optimization.GlobalCSE;
import org.tamedragon.common.optimization.LocalCSE;
import org.tamedragon.common.optimization.MemToRegPromoter;
import org.tamedragon.common.utils.LLVMIRComparisionUtils;
import org.tamedragon.compilers.LLVMBaseTest;
import org.tamedragon.compilers.clang.CLangUtils;
import org.tamedragon.compilers.clang.CompilerSettings;

public class GlobalCSETest extends LLVMBaseTest{

	private CompilerSettings compilerSettings;
	private String projectPath = "CSrc/Optimizations/GlobalCSE/";
	private String projectRootPath;
	
	@Before
	public void setUp(){		
		super.setUp();
		properties = LLVMUtility.getDefaultProperties();
		
		CLangUtils.populateSettings();
		compilerSettings = CompilerSettings.getInstance();
		compilerSettings.setProjectPath(projectPath);

		projectRootPath = compilerSettings.getProjectRoot();
	}

	@Test
	public void test1() throws Exception {
		String cSrcfilename =  "GlobalCSETest1.c";
		String llvmGlobalCSEOutFileName = "GlobalCSETest1.ll";

		runGlobalCSE(cSrcfilename, llvmGlobalCSEOutFileName);
	}
	
	@Test
	public void test2() throws Exception {
		String cSrcfilename =  "IfElseIfTest.c";
		String llvmGlobalCSEOutFileName = "IfElseIfTest.ll";

		runGlobalCSE(cSrcfilename, llvmGlobalCSEOutFileName);
	}
	
	@Test
	public void test3() throws Exception {
		String cSrcfilename =  "IfElseIfWithinForLoop.c";
		String llvmGlobalCSEOutFileName = "IfElseIfWithinForLoop.ll";

		runGlobalCSE(cSrcfilename, llvmGlobalCSEOutFileName);
	}
	
	@Test
	public void test4() throws Exception {
		String cSrcfilename =  "IfElseIfWithinLoopWithBreakContinueReturn.c";
		String llvmGlobalCSEOutFileName = "IfElseIfWithinLoopWithBreakContinueReturn.ll";

		runGlobalCSE(cSrcfilename, llvmGlobalCSEOutFileName);
	}
	
	@Test
	public void test5() throws Exception {
		String cSrcfilename =  "ForWithIfElse2.c";
		String llvmGlobalCSEOutFileName = "ForWithIfElse2.ll";

		runGlobalCSE(cSrcfilename, llvmGlobalCSEOutFileName);
	}
	
	@Test
	public void test6() throws Exception {
		String cSrcfilename =  "BinarySearch.c";
		String llvmGlobalCSEOutFileName = "BinarySearch.ll";

		runGlobalCSE(cSrcfilename, llvmGlobalCSEOutFileName);
	}
	
	@Test
	public void test7() throws Exception {
		String cSrcfilename =  "hello.c";
		String llvmGlobalCSEOutFileName = "hello.ll";

		runGlobalCSE(cSrcfilename, llvmGlobalCSEOutFileName);
	}
	
	@Test
	public void test8() throws Exception {
		String cSrcfilename =  "BubbleSort.c";
		String llvmGlobalCSEOutFileName = "BubbleSort.ll";

		runGlobalCSE(cSrcfilename, llvmGlobalCSEOutFileName);
	}
	
	@Test
	public void test9() throws Exception {
		String cSrcfilename =  "insert.c";
		String llvmGlobalCSEOutFileName = "insert.ll";

		runGlobalCSE(cSrcfilename, llvmGlobalCSEOutFileName);
	}
	
	@Test
	public void test10() throws Exception {
		String cSrcfilename =  "SimpleAA1.c";
		String llvmGlobalCSEOutFileName = "SimpleAA1.ll";

		runGlobalCSE(cSrcfilename, llvmGlobalCSEOutFileName);
	}
	
	private void runGlobalCSE(String cSrcfilename, String llvmOutFileName) throws Exception  {

		getRawLLVRIRInstrs(projectRootPath + projectPath, cSrcfilename);

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

		assertTrue(LLVMIRComparisionUtils.compare(instrsAfterOpt, projectPath, llvmOutFileName));
	}

}

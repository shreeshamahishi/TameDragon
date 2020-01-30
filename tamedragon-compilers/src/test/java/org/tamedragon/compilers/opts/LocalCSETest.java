package org.tamedragon.compilers.opts;

import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

import org.tamedragon.common.llvmir.instructions.LLVMUtility;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.Module;
import org.tamedragon.common.optimization.LocalCSE;
import org.tamedragon.common.optimization.MemToRegPromoter;
import org.tamedragon.common.utils.LLVMIRComparisionUtils;
import org.tamedragon.compilers.LLVMBaseTest;
import org.tamedragon.compilers.clang.CLangUtils;
import org.tamedragon.compilers.clang.CompilerSettings;

public class LocalCSETest extends LLVMBaseTest {

	private CompilerSettings compilerSettings;
	private String projectPath = "CSrc/Optimizations/LocalCSE/";
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
		String cSrcfilename =  "LocalCSETest1.c";
		String llvmLocalCSEOutFileName = "LocalCSETest1.ll";

		runLocalCSE(cSrcfilename, llvmLocalCSEOutFileName);
	}
	
	@Test
	public void test2() throws Exception {
		String cSrcfilename =  "LocalCSETest2.c";
		String llvmLocalCSEOutFileName = "LocalCSETest2.ll";

		runLocalCSE(cSrcfilename, llvmLocalCSEOutFileName);
	}
	
	@Test
	public void test3() throws Exception {
		String cSrcfilename =  "LocalCSETest3.c";
		String llvmLocalCSEOutFileName = "LocalCSETest3.ll";

		runLocalCSE(cSrcfilename, llvmLocalCSEOutFileName);
	}
	
	@Test
	public void test4() throws Exception {
		String cSrcfilename =  "LocalCSETest4.c";
		String llvmLocalCSEOutFileName = "LocalCSETest4.ll";

		runLocalCSE(cSrcfilename, llvmLocalCSEOutFileName);
	}
	
	@Test
	public void test5() throws Exception {
		String cSrcfilename =  "LocalCSETest5.c";
		String llvmLocalCSEOutFileName = "LocalCSETest5.ll";

		runLocalCSE(cSrcfilename, llvmLocalCSEOutFileName);
	}
	
	@Test
	public void test6() throws Exception {
		String cSrcfilename =  "VariableLengthArray.c";
		String llvmLocalCSEOutFileName = "VariableLengthArray.ll";

		runLocalCSE(cSrcfilename, llvmLocalCSEOutFileName);
	}
	
	@Test
	public void test7() throws Exception {
		String cSrcfilename =  "DereferencingAStructureMember.c";
		String llvmGlobalCSEOutFileName = "DereferencingAStructureMember.ll";

		runLocalCSE(cSrcfilename, llvmGlobalCSEOutFileName);
	}
	
	@Test
	public void test8() throws Exception {
		String cSrcfilename =  "addressOfArray.c";
		String llvmGlobalCSEOutFileName = "addressOfArray.ll";

		runLocalCSE(cSrcfilename, llvmGlobalCSEOutFileName);
	}
	
	@Ignore
	@Test
	public void test10() throws Exception {
		String cSrcfilename =  "FunctionPointer.c";
		String llvmGlobalCSEOutFileName = "FunctionPointer.ll";

		runLocalCSE(cSrcfilename, llvmGlobalCSEOutFileName);
	}
	
	@Test
	public void test11() throws Exception {
		String cSrcfilename =  "Global.c";
		String llvmGlobalCSEOutFileName = "Global.ll";

		runLocalCSE(cSrcfilename, llvmGlobalCSEOutFileName);
	}
	
	@Test
	public void test12() throws Exception {
		String cSrcfilename =  "AnotherExampleOnScope.c";
		String llvmGlobalCSEOutFileName = "AnotherExampleOnScope.ll";

		runLocalCSE(cSrcfilename, llvmGlobalCSEOutFileName);
	}
	
	private void runLocalCSE(String srcFileName, String llvmLocalCSEOutFileName)
			throws Exception {

		getRawLLVRIRInstrs(projectRootPath + projectPath, srcFileName);

		Module module = getModule();
		List<Function> functions = module.getFunctions();

		// There can be only one function to test mem2reg in the module.
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
		System.out.println("******* AFTER LOCAL CSE(Common SubExpression Elimination): ");
		emitter.reset();
		instrsAfterOpt = emitter.emitInstructions(function);
		printAsm(instrsAfterOpt);
		assertTrue(LLVMIRComparisionUtils.compare(instrsAfterOpt, projectPath, llvmLocalCSEOutFileName));
	}
}

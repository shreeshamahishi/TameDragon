package org.tamedragon.compilers.opts;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

import org.tamedragon.common.llvmir.instructions.LLVMUtility;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.Module;
import org.tamedragon.common.optimization.MemToRegPromoter;
import org.tamedragon.common.optimization.SparseConditionalConstantPropagation;
import org.tamedragon.common.utils.LLVMIRComparisionUtils;
import org.tamedragon.compilers.LLVMBaseTest;
import org.tamedragon.compilers.clang.CLangUtils;
import org.tamedragon.compilers.clang.CompilerSettings;

public class SccpTests extends LLVMBaseTest {
	
	private CompilerSettings compilerSettings;
	private String projectPath = "CSrc/Optimizations/SccpTests/";
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
	public void runSCCP1() throws Exception {
		String cSrcfilename =  "SCCP1.c";
		String llvmOutFileName = "SccpOut1.ll";

		runSccp(cSrcfilename, llvmOutFileName);
	}
	
	@Test
	public void runSCCP2() throws Exception {
		String cSrcfilename =  "SCCP2.c";
		String llvmOutFileName = "SccpOut2.ll";

		runSccp(cSrcfilename, llvmOutFileName);
	}
	
	@Test
	public void runSCCP3() throws Exception {
		String cSrcfilename =  "SCCP3.c";
		String llvmOutFileName = "SccpOut3.ll";

		runSccp(cSrcfilename, llvmOutFileName);
	}

	private void runSccp(String cSrcfilename, String llvmOutFileName) throws Exception  {

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
		
		// SCCP
		SparseConditionalConstantPropagation sccp = new SparseConditionalConstantPropagation();
		sccp.execute(function);
		System.out.println("AFTER SCCP: ");
		emitter.reset();
		instrsAfterOpt = emitter.emitInstructions(function);
		printAsm(instrsAfterOpt);
		
		assertTrue(LLVMIRComparisionUtils.compare(instrsAfterOpt, projectPath, llvmOutFileName));
	}
}

package org.tamedragon.common.tests.optimizations.scalar;

import java.util.List;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.Module;
import org.tamedragon.common.llvmir.utils.LLVMIREmitter;
import org.tamedragon.common.optimization.MemToRegPromoter;
import org.tamedragon.common.optimization.SparseConditionalConstantPropagation;
import org.tamedragon.common.utils.LLVMIRUtils;
import org.tamedragon.common.utils.ComparisionUtils;

public class SccpTests  {
	
	private static final String ROOT_PATH = "ScalarOpts/SCCP";
	
	@Test
	public void runSCCP1() throws Exception {
		String cSrcfilename =  "SccpSrc1.ll";
		String llvmOutFileName = "SccpOut1.ll";

		runSccp(cSrcfilename, llvmOutFileName);
	}
	
	@Test
	public void runSCCP2() throws Exception {
		String cSrcfilename =  "SccpSrc2.ll";
		String llvmOutFileName = "SccpOut2.ll";

		runSccp(cSrcfilename, llvmOutFileName);
	}
	
	@Test
	public void runSCCP3() throws Exception {
		String cSrcfilename =  "SccpSrc3.ll";
		String llvmOutFileName = "SccpOut3.ll";

		runSccp(cSrcfilename, llvmOutFileName);
	}

	private void runSccp(String cSrcfilename, String llvmOutFileName) throws Exception  {

		LLVMIRUtils llvmirUtils = new LLVMIRUtils();
		llvmirUtils.getInstructionsList(ROOT_PATH, cSrcfilename);

		Module module = llvmirUtils.getModule();
		List<Function> functions = module.getFunctions();

		// There can be only one function to test SCCP in the module.

		Function function = functions.get(0);

		// Mem2reg
		MemToRegPromoter memToRegPromoter = new MemToRegPromoter();
		memToRegPromoter.execute(function);
		System.out.println("AFTER MEM2REG: ");
		LLVMIREmitter emitter = llvmirUtils.getEmitter();
		emitter.reset();
		List<String> instrsAfterOpt = emitter.emitInstructions(function);
		llvmirUtils.printAsm(instrsAfterOpt);
		
		// SCCP
		SparseConditionalConstantPropagation sccp = new SparseConditionalConstantPropagation();
		sccp.execute(function);
		System.out.println("AFTER SCCP: ");
		emitter.reset();
		instrsAfterOpt = emitter.emitInstructions(function);
		llvmirUtils.printAsm(instrsAfterOpt);
		
		assertTrue(ComparisionUtils.compare(instrsAfterOpt, ROOT_PATH, llvmOutFileName));
	}
}

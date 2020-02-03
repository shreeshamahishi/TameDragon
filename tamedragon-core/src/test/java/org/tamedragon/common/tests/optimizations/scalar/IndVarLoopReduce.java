
package org.tamedragon.common.tests.optimizations.scalar;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.Module;
import org.tamedragon.common.llvmir.utils.LLVMIREmitter;
import org.tamedragon.common.optimization.LoopStrengthReduce;
import org.tamedragon.common.optimization.MemToRegPromoter;
import org.tamedragon.common.utils.LLVMIRUtils;
import org.tamedragon.common.utils.ComparisionUtils;

public class IndVarLoopReduce  {
	
	private static final String ROOT_PATH = "ScalarOpts/IndVarLoopReduce";
	
	@Ignore
	@Test
	public void runLoopNewVarsInsideLoop() throws Exception {
		String cSrcfilename =  "LoopNewVarsInsideLoopSrc.ll";
		String llvmOutFileName = "LoopNewVarsInsideLoopOut.ll";

		runIndVarLoopReduce(cSrcfilename, llvmOutFileName);
	}
	
	private void runIndVarLoopReduce(String cSrcfilename, String llvmOutFileName) throws Exception  {

		LLVMIRUtils llvmirUtils = new LLVMIRUtils();
		llvmirUtils.getInstructionsList(ROOT_PATH, cSrcfilename);

		Module module = llvmirUtils.getModule();
		List<Function> functions = module.getFunctions();

		// There can be only one function to test Loop reduce in the module.

		Function function = functions.get(0);

		// Mem2reg
		MemToRegPromoter memToRegPromoter = new MemToRegPromoter();
		memToRegPromoter.execute(function);
		System.out.println("AFTER MEM2REG: ");
		LLVMIREmitter emitter = llvmirUtils.getEmitter();
		emitter.reset();
		List<String> instrsAfterOpt = emitter.emitInstructions(function);
		llvmirUtils.printAsm(instrsAfterOpt);
		
		// Run loop reduce
		LoopStrengthReduce lsr = new LoopStrengthReduce();
		lsr.execute(function);
		System.out.println("AFTER LOOP STRENGTH REDUCE: ");
		emitter.reset();
		instrsAfterOpt = emitter.emitInstructions(function);
		llvmirUtils.printAsm(instrsAfterOpt);
		
		assertTrue(ComparisionUtils.compare(instrsAfterOpt, ROOT_PATH, llvmOutFileName));
		
	}
}


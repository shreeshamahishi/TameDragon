package org.tamedragon.compilers.opts;

import java.util.List;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.Module;
import org.tamedragon.common.optimization.ADCE;
import org.tamedragon.common.optimization.MemToRegPromoter;
import org.tamedragon.common.utils.ComparisionUtils;
import org.tamedragon.compilers.LLVMBaseTest;

public class ADCETests extends LLVMBaseTest {
	
	private static final String ROOT_PATH = "CSrc/Optimizations/ADCETests";
	
	@Test
	public void runADCE1() throws Exception {
		String cSrcfilename =  "ADCE1.c";
		String llvmOutFileName = "ADCEOut1.bc";

		runAdce(cSrcfilename, llvmOutFileName);
	}

	@Test
	public void runADCE2() throws Exception {
		String cSrcfilename =  "ADCE2.c";
		String llvmOutFileName = "ADCEOut2.bc";

		runAdce(cSrcfilename, llvmOutFileName);
	}
	
	private void runAdce(String cSrcfilename, String llvmOutFileName) throws Exception  {

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
		
		// ADCE
		ADCE adce = new ADCE();
		adce.execute(function);
		System.out.println("AFTER ADCE: ");
		emitter.reset();
		instrsAfterOpt = emitter.emitInstructions(function);
		printAsm(instrsAfterOpt);
		
		assertTrue(ComparisionUtils.compare(instrsAfterOpt, ROOT_PATH, llvmOutFileName));
	}
}
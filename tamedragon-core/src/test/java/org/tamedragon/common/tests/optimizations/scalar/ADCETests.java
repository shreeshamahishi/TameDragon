package org.tamedragon.common.tests.optimizations.scalar;

import java.util.List;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.Module;
import org.tamedragon.common.llvmir.utils.LLVMIREmitter;
import org.tamedragon.common.optimization.ADCE;
import org.tamedragon.common.optimization.MemToRegPromoter;
import org.tamedragon.common.utils.LLVMIRUtils;
import org.tamedragon.common.utils.ComparisionUtils;

public class ADCETests  {
	
	private static final String ROOT_PATH = "ScalarOpts/ADCE";
	
	@Test
	public void runADCE1() throws Exception {
		String cSrcfilename =  "ADCESrc1.bc";
		String llvmOutFileName = "ADCEOut1.bc";

		runAdce(cSrcfilename, llvmOutFileName);
	}

	@Test
	public void runADCE2() throws Exception {
		String cSrcfilename =  "ADCESrc2.bc";
		String llvmOutFileName = "ADCEOut2.bc";

		runAdce(cSrcfilename, llvmOutFileName);
	}
	
	private void runAdce(String cSrcfilename, String llvmOutFileName) throws Exception  {
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
		List<String> instrsAfterOpt = emitter.emitInstructions(function);
		llvmirUtils.printAsm(instrsAfterOpt);
		
		// ADCE
		ADCE adce = new ADCE();
		adce.execute(function);
		System.out.println("AFTER ADCE: ");
		emitter.reset();
		instrsAfterOpt = emitter.emitInstructions(function);
		llvmirUtils.printAsm(instrsAfterOpt);
		
		assertTrue(ComparisionUtils.compare(instrsAfterOpt, ROOT_PATH, llvmOutFileName));
	}
}
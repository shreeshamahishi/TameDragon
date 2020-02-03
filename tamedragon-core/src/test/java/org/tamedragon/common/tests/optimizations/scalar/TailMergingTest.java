package org.tamedragon.common.tests.optimizations.scalar;

import java.util.List;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionCreationException;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionDetailAccessException;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionUpdateException;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.Module;
import org.tamedragon.common.llvmir.utils.LLVMIREmitter;
import org.tamedragon.common.optimization.TailMerging;
import org.tamedragon.common.utils.ComparisionUtils;
import org.tamedragon.common.utils.LLVMIRUtils;

public class TailMergingTest {
	private final static String ROOT_PATH = "ScalarOpts/TailMerging";

	@Test
	public void test1() throws Exception {
		String llvmSrcFileName =  "TailMerging1Src.ll";
		String llvmOutFile = "TailMerging1Out.ll";

		runTailMerging(llvmSrcFileName, llvmOutFile);
	}
	
	@Test
	public void test2() throws Exception {
		String llvmSrcFileName =  "TailMerging2Src.ll";
		String llvmOutFile = "TailMerging2Out.ll";

		runTailMerging(llvmSrcFileName, llvmOutFile);
	}
	
	@Test
	public void test3() throws Exception {
		String llvmSrcFileName =  "TailMerging3Src.ll";
		String llvmOutFile = "TailMerging3Out.ll";

		runTailMerging(llvmSrcFileName, llvmOutFile);
	}
	
	private void runTailMerging(String llvmSrcFileName, String llvmOutFile) throws InstructionDetailAccessException, InstructionUpdateException, InstructionCreationException, InstantiationException {
		LLVMIRUtils llvmirUtils = new LLVMIRUtils();
		llvmirUtils.getInstructionsList(ROOT_PATH, llvmSrcFileName);

		Module module = llvmirUtils.getModule();
		List<Function> functions = module.getFunctions();

		// There can be only one function to test mem2reg in the module.
		Function function = functions.get(0);
		TailMerging tailMerging = new TailMerging();
		tailMerging.execute(function);
			
		System.out.println("AFTER TAIL MERGING ELIMINATION: ");
		LLVMIREmitter emitter = llvmirUtils.getEmitter();
		emitter.reset();
		List<String> instrsAfterOpt = emitter.emitInstructions(module);
		llvmirUtils.printAsm(instrsAfterOpt);
		assertTrue(ComparisionUtils.compare(instrsAfterOpt, ROOT_PATH, llvmOutFile));
	}
}

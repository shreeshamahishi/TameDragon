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
import org.tamedragon.common.optimization.TailRecursiveCallElimination;
import org.tamedragon.common.utils.ComparisionUtils;
import org.tamedragon.common.utils.LLVMIRUtils;

public class TailRecursiveCallEliminationTest {
	private final static String ROOT_PATH = "TailCallOptimizations";

	@Test
	public void test1() throws Exception {
		String llvmSrcFileName =  "TailRecursiveCallSrc.bc";
		String llvmOutFile = "TailRecursiveCallOut.bc";

		runTailRecursiveCallElmination(llvmSrcFileName, llvmOutFile);
	}
	
	@Test
	public void test2() throws Exception {
		String llvmSrcFileName =  "TailRecursiveCallElimination2Src.bc";
		String llvmOutFile = "TailRecursiveCallElimination2Out.bc";

		runTailRecursiveCallElmination(llvmSrcFileName, llvmOutFile);
	}
	
	@Test
	public void test3() throws Exception {
		String llvmSrcFileName =  "TailRecursiveCallElimination3Src.bc";
		String llvmOutFile = "TailRecursiveCallElimination3Out.bc";

		runTailRecursiveCallElmination(llvmSrcFileName, llvmOutFile);
	}
	
	@Test
	public void test4() throws Exception {
		String llvmSrcFileName =  "TailRecursiveCallElimination4Src.bc";
		String llvmOutFile = "TailRecursiveCallElimination4Out.bc";

		runTailRecursiveCallElmination(llvmSrcFileName, llvmOutFile);
	}

	private void runTailRecursiveCallElmination(String llvmSrcFileName, String llvmOutFile) throws InstructionDetailAccessException, InstructionUpdateException, InstructionCreationException, InstantiationException {
		LLVMIRUtils llvmirUtils = new LLVMIRUtils();
		llvmirUtils.getInstructionsList(ROOT_PATH, llvmSrcFileName);

		Module module = llvmirUtils.getModule();
		List<Function> functions = module.getFunctions();

		// There can be only one function to test mem2reg in the module.
		Function function = functions.get(0);
		TailRecursiveCallElimination tailRecursiveCallElimination = new TailRecursiveCallElimination();
		tailRecursiveCallElimination.execute(function);
			
		System.out.println("AFTER TAIL RECURSIVE CALL ELIMINATION: ");
		LLVMIREmitter emitter = llvmirUtils.getEmitter();
		emitter.reset();
		List<String> instrsAfterOpt = emitter.emitInstructions(module);
		llvmirUtils.printAsm(instrsAfterOpt);
		assertTrue(ComparisionUtils.compare(instrsAfterOpt, ROOT_PATH, llvmOutFile));
	}
}

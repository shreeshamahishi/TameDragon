package org.tamedragon.common.tests.optimizations.scalar;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.tamedragon.common.llvmir.instructions.CFG;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionCreationException;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionDetailAccessException;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionUpdateException;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.Module;
import org.tamedragon.common.llvmir.utils.LLVMIREmitter;
import org.tamedragon.common.optimization.LoopUnSwitching;
import org.tamedragon.common.utils.LLVMIRUtils;
import org.tamedragon.common.utils.ComparisionUtils;

public class LoopUnSwitchingTest {
	private final static String ROOT_PATH = "ScalarOpts/LoopUnSwitching";

	@Ignore
	@Test
	public void test1() throws Exception {
		String llvmSrcFileName =  "LocalUnSwitchTest1Src.bc";
		String llvmOutFile = "LocalUnSwitchTest1Out.bc";

		runLoopUnSwitching(llvmSrcFileName, llvmOutFile);
	}
	
	@Ignore
	@Test
	public void test2() throws Exception {
		String llvmSrcFileName =  "LocalUnSwitchTest2Src.bc";
		String llvmOutFile = "LocalUnSwitchTest2Out.bc";

		runLoopUnSwitching(llvmSrcFileName, llvmOutFile);
	}
	
	@Ignore
	@Test
	public void test3() throws Exception {
		String llvmSrcFileName =  "LocalUnSwitchTest3Src.bc";
		String llvmOutFile = "LocalUnSwitchTest3Out.bc";

		runLoopUnSwitching(llvmSrcFileName, llvmOutFile);
	}
	
	@Ignore
	@Test
	public void test4() throws Exception {
		String llvmSrcFileName =  "LocalUnSwitchTest4Src.bc";
		String llvmOutFile = "LocalUnSwitchTest4Out.bc";

		runLoopUnSwitching(llvmSrcFileName, llvmOutFile);
	}
	
	@Ignore
	@Test
	public void test5() throws Exception {
		String llvmSrcFileName =  "LocalUnSwitchTest5Src.bc";
		String llvmOutFile = "LocalUnSwitchTest5Out.bc";

		runLoopUnSwitching(llvmSrcFileName, llvmOutFile);
	}
	
	@Ignore
	@Test
	public void test6() throws Exception {
		String llvmSrcFileName =  "LocalUnSwitchTest6Src.bc";
		String llvmOutFile = "LocalUnSwitchTest6Out.bc";

		runLoopUnSwitching(llvmSrcFileName, llvmOutFile);
	}
	
	@Ignore
	@Test
	public void test7() throws Exception {
		String llvmSrcFileName =  "LocalUnSwitchTest7Src.bc";
		String llvmOutFile = "LocalUnSwitchTest7Out.bc";

		runLoopUnSwitching(llvmSrcFileName, llvmOutFile);
	}
	
	@Ignore
	@Test
	public void test8() throws Exception {
		String llvmSrcFileName =  "LocalUnSwitchTest8Src.bc";
		String llvmOutFile = "LocalUnSwitchTest8Out.bc";

		runLoopUnSwitching(llvmSrcFileName, llvmOutFile);
	}
	
	@Ignore
	@Test
	public void test9() throws Exception {
		String llvmSrcFileName =  "LocalUnSwitchTest9Src.bc";
		String llvmOutFile = "LocalUnSwitchTest9Out.bc";

		runLoopUnSwitching(llvmSrcFileName, llvmOutFile);
	}
	
	@Ignore
	@Test
	public void test10() throws Exception {
		String llvmSrcFileName =  "LocalUnSwitchTest10Src.bc";
		String llvmOutFile = "LocalUnSwitchTest10Out.bc";

		runLoopUnSwitching(llvmSrcFileName, llvmOutFile);
	}
	
	private void runLoopUnSwitching(String llvmSrcFileName, String llvmOutFile) throws InstructionDetailAccessException, InstructionUpdateException, InstructionCreationException, InstantiationException {
		LLVMIRUtils llvmirUtils = new LLVMIRUtils();
		llvmirUtils.getInstructionsList(ROOT_PATH, llvmSrcFileName);

		Module module = llvmirUtils.getModule();
		List<Function> functions = module.getFunctions();

		// There can be only one function to test mem2reg in the module.
		Function function = functions.get(0);
		LoopUnSwitching loopUnSwitching = new LoopUnSwitching();
		
		loopUnSwitching.execute(function);
			
		System.out.println(" AFTER LOOP UNSWITCHING: ");
		LLVMIREmitter emitter = llvmirUtils.getEmitter();
		emitter.reset();
		List<String> instrsAfterOpt = emitter.emitInstructions(module);
		llvmirUtils.printAsm(instrsAfterOpt);
		assertTrue(ComparisionUtils.compare(instrsAfterOpt, ROOT_PATH, llvmOutFile));
	}
}

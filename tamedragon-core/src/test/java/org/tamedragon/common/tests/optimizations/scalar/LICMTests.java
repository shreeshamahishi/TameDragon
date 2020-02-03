package org.tamedragon.common.tests.optimizations.scalar;

import java.util.List;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.Module;
import org.tamedragon.common.llvmir.utils.LLVMIREmitter;
import org.tamedragon.common.optimization.LICM;
import org.tamedragon.common.utils.LLVMIRUtils;
import org.tamedragon.common.utils.ComparisionUtils;

public class LICMTests  {

	private static final String ROOT_PATH = "ScalarOpts/LICM";

	@Test
	public void runSimpleLicm() throws Exception {
		String llvmsrcFile =  "SimpleLoopInvariantSrc.ll";
		String llvmOutFileName = "SimpleLoopInvariantOut.ll";

		runLicm(llvmsrcFile, llvmOutFileName, "foo");
	}

	@Test
	public void runHoistInsWithConstAndInvariantOperands() throws Exception {
		String llvmsrcFile =  "ConstAndInvariantOperandsSrc.ll";
		String llvmOutFileName = "ConstAndInvariantOperandsOut.ll";

		runLicm(llvmsrcFile, llvmOutFileName, "foobar");
	}

	@Test
	public void runHoistInsWithInvariantOperandsSomeNotDominating1() throws Exception {
		String llvmsrcFile =  "InvariantInstrsNotDominatingSrc1.ll";
		String llvmOutFileName = "InvariantInstrsNotDominatingOut1.ll";

		runLicm(llvmsrcFile, llvmOutFileName, "foobar");
	}

	@Test
	public void runHoistInsWithInvariantOperandsSomeNotDominating2() throws Exception {
		String llvmsrcFile =  "InvariantInstrsNotDominatingSrc2.ll";
		String llvmOutFileName = "InvariantInstrsNotDominatingOut2.ll";

		runLicm(llvmsrcFile, llvmOutFileName, "foobar");
	}

	@Test
	public void simpleInvarientBinaryOpr() throws Exception {
		String llvmsrcFile =  "simpleInvarientBinaryOprSrc.ll";
		String llvmOutFileName = "simpleInvarientBinaryOprOut.ll";

		runLicm(llvmsrcFile, llvmOutFileName, "foobar");
	}

	@Test
	public void HoistingTest1() throws Exception {
		String llvmsrcFile =  "HoistingTest1Src.ll";
		String llvmOutFileName = "HoistingTest1Out.ll";

		runLicm(llvmsrcFile, llvmOutFileName, "foobar");
	}

	@Test
	public void HoistingTest2() throws Exception {
		String llvmsrcFile =  "HoistingTest2Src.ll";
		String llvmOutFileName = "HoistingTest2Out.ll";

		runLicm(llvmsrcFile, llvmOutFileName, "foobar");
	}

	@Test
	public void HoistingTest3() throws Exception {
		String llvmsrcFile =  "HoistingTest3Src.ll";
		String llvmOutFileName = "HoistingTest3Out.ll";

		runLicm(llvmsrcFile, llvmOutFileName, "foobar");
	}

	@Test
	public void HoistingTest4() throws Exception {
		String llvmsrcFile =  "HoistingTest4Src.ll";
		String llvmOutFileName = "HoistingTest4Out.ll";

		runLicm(llvmsrcFile, llvmOutFileName, "foobar");
	}

	@Test
	public void nestedloopInvariantInInnerLoop() throws Exception {
		String llvmsrcFile =  "NestedloopInvariantInInnerLoopSrc.ll";
		String llvmOutFileName = "NestedloopInvariantInInnerLoopOut.ll";

		runLicm(llvmsrcFile, llvmOutFileName,"nestLoop");
	}

	@Test
	public void nestedloopInvariantInBothLoop() throws Exception {
		String llvmsrcFile =  "NestedloopInvariantInBoothLoopSrc.ll";
		String llvmOutFileName = "NestedloopInvariantInBoothLoopOut.ll";

		runLicm(llvmsrcFile, llvmOutFileName,"nestLoop");
	}

	@Test
	public void nestedloopInvariantInOuterLoop() throws Exception {
		String llvmsrcFile =  "NestedloopInvariantInOuterLoopSrc.ll";
		String llvmOutFileName = "NestedloopInvariantInOuterLoopOut.ll";

		runLicm(llvmsrcFile, llvmOutFileName,"nestLoopInvOuter");
	}

	@Test
	public void nested3loop() throws Exception {
		String llvmsrcFile =  "nested3loopSrc.ll";
		String llvmOutFileName = "nested3loopOut.ll";

		runLicm(llvmsrcFile, llvmOutFileName, "nestes3loop");
	}

	@Test
	public void twoPreheaders() throws Exception {
		String llvmsrcFile =  "twoPreheaderSrc.ll";
		String llvmOutFileName = "twoPreheaderOut.ll";

		runLicm(llvmsrcFile, llvmOutFileName, "twopreheader");
	}

	//	@Test
	//	public void gotoLabel() throws Exception {
	//		String cSrcfilename =  "gotoLabel.c";
	//		String llvmOutFileName = "gotoLabel.ll";
	//
	//		runLicm(cSrcfilename, llvmOutFileName);
	//	}

	@Test
	public void switchTest() throws Exception {
		String llvmsrcFile =  "switchTestSrc.ll";
		String llvmOutFileName = "switchTestOut.ll";

		runLicm(llvmsrcFile, llvmOutFileName, "switchTest");
	}

	private void runLicm(String cSrcfilename, String llvmOutFileName, String functionNameToOptimize) throws Exception  {

		LLVMIRUtils llvmirUtils = new LLVMIRUtils();
		llvmirUtils.getInstructionsList(ROOT_PATH, cSrcfilename);

		Module module = llvmirUtils.getModule();
		List<Function> functions = module.getFunctions();

		Function function = null;
		
		// There can be only one function to test SCCP in the module.		
		for(Function currentFunction : functions){
			String function_name = currentFunction.getName();

			if(function_name.equals(functionNameToOptimize)) {
				function = currentFunction;
				break;
			}
		}

		if(function == null){
			System.out.println("Function not found");
			return;
		}

		// LICM
		LICM licm = new LICM();
		licm.execute(function);
		System.out.println("AFTER LICM: ");
		LLVMIREmitter emitter = llvmirUtils.getEmitter();
		emitter.reset();
		List<String> instrsAfterOpt  = emitter.emitInstructions(function);
		llvmirUtils.printAsm(instrsAfterOpt);

		assertTrue(ComparisionUtils.compare(instrsAfterOpt, ROOT_PATH, llvmOutFileName));
	}
}


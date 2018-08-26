package org.tamedragon.common.tests.optimizations.scalar;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.Module;
import org.tamedragon.common.llvmir.utils.LLVMIREmitter;
import org.tamedragon.common.optimization.BranchOptimization;
import org.tamedragon.common.optimization.LICM;
import org.tamedragon.common.optimization.MemToRegPromoter;
import org.tamedragon.common.utils.LLVMIRUtils;
import org.tamedragon.common.utils.ComparisionUtils;

public class BranchOptimizationTests   {

	private static final String ROOT_PATH = "ScalarOpts/BranchOptimizations";
	//Test1
	@Test
	public void runSimpleBranchOptimization() throws Exception {
		String llvmsrcFile =  "UC2UC.bc";
		String llvmOutFileName = "UC2UCOut.bc";

		runBranchOptimization(llvmsrcFile, llvmOutFileName, "foo");
	}

	// Test2 If else Via Empty Basic Block
	@Ignore
	@Test
	public void runIfElseViaEmptyBasicBlock() throws Exception {
		String llvmsrcFile =  "Ifelse.bc";
		String llvmOutFileName = "IfelseOut.bc";

		runBranchOptimization(llvmsrcFile, llvmOutFileName, "foo");
	}

	//Test3
	@Test
	public void runIfElseIfTestLLVMIR() throws Exception {
		String llvmsrcFile =  "IfElseIfTestLLVMIR.bc";
		String llvmOutFileName = "IfElseIfTestLLVMIROut.bc";

		runBranchOptimization(llvmsrcFile, llvmOutFileName, "foo");
	}

	//Test4
	@Test
	public void IfElseIfWithinForLoopLLVMIR() throws Exception {
		String llvmsrcFile =  "IfElseIfWithinForLoopLLVMIR.bc";
		String llvmOutFileName = "IfElseIfWithinForLoopLLVMIROut.bc";

		runBranchOptimization(llvmsrcFile, llvmOutFileName, "foo");
	}
	//Test5
	@Test
	public void IfElseIfWithinLoopWithBreakContinueReturnLLVMIR() throws Exception {
		String llvmsrcFile =  "IfElseIfWithinLoopWithBreakContinueReturnLLVMIR.bc";
		String llvmOutFileName = "IfElseIfWithinLoopWithBreakContinueReturnLLVMIROut.bc";

		runBranchOptimization(llvmsrcFile, llvmOutFileName, "foo");
	}

	//Test6
	@Test
	public void onlyIfLLVMIR() throws Exception {
		String llvmsrcFile =  "onlyIfLLVMIR.bc";
		String llvmOutFileName = "onlyIfLLVMIROut.bc";

		runBranchOptimization(llvmsrcFile, llvmOutFileName, "foo");
	}

	//Test7 Related to the Switch Statement
	@Test
	public void FallthroughLLVMIR() throws Exception {
		String llvmsrcFile =  "FallthroughLLVMIR.bc";
		String llvmOutFileName = "FallthroughLLVMIROut.bc";

		runBranchOptimization(llvmsrcFile, llvmOutFileName, "foo");
	}

	//Test 8 simple Switch cases
	@Ignore
	@Test
	public void SwitchOnCharLLVMIR() throws Exception {
		String llvmsrcFile =  "SwitchOnCharLLVMIR.bc";
		String llvmOutFileName = "SwitchOnCharLLVMIROut.bc";

		runBranchOptimization(llvmsrcFile, llvmOutFileName, "foo");
	}
	//Test 9 simple Switch on Short Value
	@Test
	public void SwitchOnShortValueLLVMIR() throws Exception {
		String llvmsrcFile =  "SwitchOnShortValueLLVMIR.bc";
		String llvmOutFileName = "SwitchOnShortValueLLVMIROut.bc";

		runBranchOptimization(llvmsrcFile, llvmOutFileName, "foo");
	}

	//Test 9 simple Switch on Short Value
	@Test
	public void doWhileWithinWhileLLVMIR() throws Exception {
		String llvmsrcFile =  "doWhileWithinWhileLLVMIR.bc";
		String llvmOutFileName = "doWhileWithinWhileLLVMIROut.bc";

		runBranchOptimization(llvmsrcFile, llvmOutFileName, "foo");
	}

	//Test 10
	@Test
	public void MultipleNestedLoopsLLVMIR() throws Exception {
		String llvmsrcFile =  "MultipleNestedLoopsLLVMIR.bc";
		String llvmOutFileName = "MultipleNestedLoopsLLVMIROut.bc";

		runBranchOptimization(llvmsrcFile, llvmOutFileName, "foo");
	}

	@Ignore
	@Test
	public void ForLoopWithContinueBreakAndReturnLLVMIR() throws Exception {
		String llvmsrcFile =  "ForLoopWithContinueBreakAndReturnLLVMIR.bc";
		String llvmOutFileName = "ForLoopWithContinueBreakAndReturnLLVMIROut.bc";

		runBranchOptimization(llvmsrcFile, llvmOutFileName, "foo");
	}

	//Test 12
	@Test
	public void sample1LLVMIR() throws Exception {
		String llvmsrcFile =  "sample1LLVMIR.bc";
		String llvmOutFileName = "sample1LLVMIROut.bc";

		runBranchOptimization(llvmsrcFile, llvmOutFileName, "foo");
	}

	private void runBranchOptimization(String cSrcfilename, String llvmOutFileName, String functionNameToOptimize) throws Exception  {
		LLVMIRUtils llvmirUtils = new LLVMIRUtils();
		llvmirUtils.getInstructionsList(ROOT_PATH, cSrcfilename);

		Module module = llvmirUtils.getModule();
		List<Function> functions = module.getFunctions();

		Function function = null;
		// There can be only one function to test SCCP in the module.
		for(Function currentFunction : functions){
			String function_name = currentFunction.getName();

			if(function_name.equals(functionNameToOptimize)){
				function = currentFunction;
				break;

			}
		}

		if(function == null){
			System.out.println("Function not found");
			return;
		}
		////		 Mem2reg
		//		MemToRegPromoter memToRegPromoter = new MemToRegPromoter();
		//		memToRegPromoter.execute(function.getCfg());
		//		System.out.println("AFTER MEM2REG: ");
		//		emitter.reset();
		LLVMIREmitter emitter = llvmirUtils.getEmitter();
		List<String> instrsAfterOpt = emitter.emitInstructions(function);
		//		printAsm(instrsAfterOpt);

		// Branch Optimization
		BranchOptimization branchOptimizations = new BranchOptimization();
		branchOptimizations.execute(function);
		System.out.println("AFTER Branch Optimization: ");
		emitter.reset();
		instrsAfterOpt = emitter.emitInstructions(function);
		llvmirUtils.printAsm(instrsAfterOpt);
		assertTrue(ComparisionUtils.compare(instrsAfterOpt, ROOT_PATH, llvmOutFileName));
	}
}

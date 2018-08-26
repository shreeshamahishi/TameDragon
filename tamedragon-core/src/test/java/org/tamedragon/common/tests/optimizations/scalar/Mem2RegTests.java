package org.tamedragon.common.tests.optimizations.scalar;

import java.util.List;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.Module;
import org.tamedragon.common.llvmir.utils.LLVMIREmitter;
import org.tamedragon.common.optimization.MemToRegPromoter;
import org.tamedragon.common.utils.LLVMIRUtils;
import org.tamedragon.common.utils.ComparisionUtils;

public class Mem2RegTests  {

	private static final String ROOT_PATH = "ScalarOpts/Mem2Reg";

	@Test
	public void runSimpleStraightLineProg() throws Exception {
		String cSrcfilename =  "Mem2RegSrc1.bc";
		String llvmOutFileName = "Mem2RegOut1.bc";

		runMem2Reg(cSrcfilename, llvmOutFileName, "foo");
	}

	@Test
	public void runSimpleIfProg() throws Exception {
		String cSrcfilename =  "Mem2RegSrc2.bc";
		String llvmOutFileName = "Mem2RegOut2.bc";

		runMem2Reg(cSrcfilename, llvmOutFileName, "foo");
	}

	@Test
	public void runSimpleIfElseProg() throws Exception {
		String cSrcfilename =  "Mem2RegSrc3.bc";
		String llvmOutFileName = "Mem2RegOut3.bc";

		runMem2Reg(cSrcfilename, llvmOutFileName, "foo");
	}

	@Test
	public void runSimpleNestedIfElse1Prog() throws Exception {
		String cSrcfilename =  "Mem2RegSrc4.bc";
		String llvmOutFileName = "Mem2RegOut4.bc";

		runMem2Reg(cSrcfilename, llvmOutFileName, "foo");
	}

	@Test
	public void runForLoopWithIfElse1Prog() throws Exception {
		String cSrcfilename =  "Mem2RegSrc5.bc";
		String llvmOutFileName = "Mem2RegOut5.bc";

		runMem2Reg(cSrcfilename, llvmOutFileName, "foo");
	}

	@Test
	public void runForWhileWithIfElse1Prog() throws Exception {
		String cSrcfilename =  "Mem2RegSrc6.bc";
		String llvmOutFileName = "Mem2RegOut6.bc";

		runMem2Reg(cSrcfilename, llvmOutFileName, "foo");
	}

	@Test
	public void runForSimpleIfElseWithUndefProg() throws Exception {
		String cSrcfilename =  "Mem2RegSrc7.bc";
		String llvmOutFileName = "Mem2RegOut7.bc";

		runMem2Reg(cSrcfilename, llvmOutFileName, "foo");
	}

	@Test
	public void runIfElseWithNDefsInSameBBProg() throws Exception {
		String cSrcfilename =  "Mem2RegSrc8.bc";
		String llvmOutFileName = "Mem2RegOut8.bc";

		runMem2Reg(cSrcfilename, llvmOutFileName, "foo");
	}

	@Test
	public void runIfElseWithMultiplePhiForArgs() throws Exception {
		String cSrcfilename =  "Mem2RegSrc9.bc";
		String llvmOutFileName = "Mem2RegOut9.bc";

		runMem2Reg(cSrcfilename, llvmOutFileName, "foo");
	}

	@Test
	public void runIfElseWithPreExistingPhiNode() throws Exception {
		String cSrcfilename =  "Mem2RegSrc10.bc";
		String llvmOutFileName = "Mem2RegOut10.bc";

		runMem2Reg(cSrcfilename, llvmOutFileName, "foo");
	}

	@Test
	public void runWithSeveralPointers1() throws Exception {
		String cSrcfilename = "Mem2RegSrc12.bc";
		String llvmOutFileName = "Mem2RegOut12.bc";

		runMem2Reg(cSrcfilename, llvmOutFileName, "bar");
	}

	@Test
	public void runDoWhileWithinWhile() throws Exception {
		String cSrcfilename = "doWhileWithinWhileSrc.bc";
		String llvmOutFileName = "doWhileWithinWhileOut.bc";

		runMem2Reg(cSrcfilename, llvmOutFileName, "foo");
	}

	@Test
	public void runIfElseIfLadder() throws Exception {
		String cSrcfilename = "IfElseIfLadderSrc.bc";
		String llvmOutFileName = "IfElseIfLadderOut.bc";

		runMem2Reg(cSrcfilename, llvmOutFileName, "foo");
	}

	@Test
	public void runGlobalVar() throws Exception {
		String cSrcfilename = "GlobalVarSrc.bc";
		String llvmOutFileName = "GlobalVarOut.bc";

		runMem2Reg(cSrcfilename, llvmOutFileName, "foo");
	}

	@Test
	public void runDerefStructMember() throws Exception {
		String cSrcfilename = "DerefStructMemberSrc.bc";
		String llvmOutFileName = "DerefStructMemberOut.bc";

		runMem2Reg(cSrcfilename, llvmOutFileName,"foo");
	}

	@Test
	public void runBinarySearch() throws Exception {
		String cSrcfilename = "BinarySearchSrc.bc";
		String llvmOutFileName = "BinarySearchOut.bc";

		runMem2Reg(cSrcfilename, llvmOutFileName,"main");
	}

	@Test
	public void runArrayAssignmentInLoop() throws Exception {
		String cSrcfilename = "ArrayAssignmentInLoopSrc.bc";
		String llvmOutFileName = "ArrayAssignmentInLoopOut.bc";

		runMem2Reg(cSrcfilename, llvmOutFileName,"foo");
	}

	@Test
	public void runArrayPointerInStructure() throws Exception {
		String cSrcfilename = "ArrayPointerInStructureSrc.bc";
		String llvmOutFileName = "ArrayPointerInStructureOut.bc";

		runMem2Reg(cSrcfilename, llvmOutFileName,"foo");
	}

	@Test
	public void runCastInstrInStruct() throws Exception {
		String cSrcfilename = "CastInstrInStructureSrc.bc";
		String llvmOutFileName = "CastInstrInStructureOut.bc";

		runMem2Reg(cSrcfilename, llvmOutFileName,"fun");
	}

	@Test
	public void runAddingTwoNumbers() throws Exception {
		String cSrcfilename = "AddingTwoNumbersSrc.bc";
		String llvmOutFileName = "AddingTwoNumbersOut.bc";

		runMem2Reg(cSrcfilename, llvmOutFileName,"main");
	}

	@Test
	public void Test() throws Exception {
		String cSrcfilename = "TestIn.bc";
		String llvmOutFileName = "TestOut.bc";

		runMem2Reg(cSrcfilename, llvmOutFileName,"foo");
	}
	
	@Test
	public void ComplexStruct1Test() throws Exception {
		String cSrcfilename = "ComplexStruct1Src.bc";
		String llvmOutFileName = "ComplexStruct1Out.bc";

		runMem2Reg(cSrcfilename, llvmOutFileName,"foo");
	}

	/*
	 // Not sure what these are, check them out
	@Test
	public void runFunctionPointer() throws Exception {
		String cSrcfilename = "FunctionPointer.c";
		String llvmOutFileName = "FunctionPointerOut.bc";

		runMem2Reg(cSrcfilename, llvmOutFileName);
	}


	@Test
	public void runNonCriticalMemAccess() throws Exception {
		String cSrcfilename = "Mem2Reg11.c";
		String llvmOutFileName = "Mem2RegOut11.bc";

		runMem2Reg(cSrcfilename, llvmOutFileName);
	}*/

	private void runMem2Reg(String srcFileName, String llvmOutFileName, String functionNameToOptimize)
			throws Exception {

		LLVMIRUtils llvmirUtils = new LLVMIRUtils();
		llvmirUtils.getInstructionsList(ROOT_PATH, srcFileName);

		Module module = llvmirUtils.getModule();
		List<Function> functions = module.getFunctions();
		Function function = null;
		// There can be only one function to test mem2reg in the module.
		for(Function currentFunction : functions){
			String function_name = currentFunction.getName();

			if(function_name.equals(functionNameToOptimize))
			{
				function = currentFunction;
				break;
			}
		}

		if(function == null){
			System.out.println("Function not found");
			return;
		}
		
		// Mem2reg
		MemToRegPromoter memToRegPromoter = new MemToRegPromoter();
		memToRegPromoter.execute(function);
		System.out.println("AFTER MEM2REG: ");
		LLVMIREmitter emitter = llvmirUtils.getEmitter();
		emitter.reset();
		List<String> instrsAfterOpt = emitter.emitInstructions(function.getParent());
		llvmirUtils.printAsm(instrsAfterOpt);
		assertTrue(ComparisionUtils.compare(instrsAfterOpt, ROOT_PATH, llvmOutFileName));

	}
}

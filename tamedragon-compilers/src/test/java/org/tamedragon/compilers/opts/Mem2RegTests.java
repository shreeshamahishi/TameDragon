package org.tamedragon.compilers.opts;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.Module;
import org.tamedragon.common.optimization.MemToRegPromoter;
import org.tamedragon.common.utils.LLVMIRComparisionUtils;
import org.tamedragon.compilers.LLVMBaseTest;

public class Mem2RegTests extends LLVMBaseTest {

	private final static String ROOT_PATH = "CSrc/Optimizations/Mem2RegTests";
	
	@Test
	public void runSimpleStraightLineProg() throws Exception {
		String cSrcfilename =  "Mem2Reg1.c";
		String llvmOutFileName = "Mem2RegOut1.ll";

		runMem2Reg(cSrcfilename, llvmOutFileName);
	}

	@Test
	public void runSimpleIfProg() throws Exception {
		String cSrcfilename = "Mem2Reg2.c";
		String llvmOutFileName = "Mem2RegOut2.ll";

		runMem2Reg(cSrcfilename, llvmOutFileName);
	}

	@Test
	public void runSimpleIfElseProg() throws Exception {
		String cSrcfilename = "Mem2Reg3.c";
		String llvmOutFileName = "Mem2RegOut3.ll";

		runMem2Reg(cSrcfilename, llvmOutFileName);
	}

	@Test
	public void runSimpleNestedIfElse1Prog() throws Exception {
		String cSrcfilename = "Mem2Reg4.c";
		String llvmOutFileName = "Mem2RegOut4.ll";

		runMem2Reg(cSrcfilename, llvmOutFileName);
	}

	@Test
	public void runForLoopWithIfElse1Prog() throws Exception {
		String cSrcfilename = "Mem2Reg5.c";
		String llvmOutFileName = "Mem2RegOut5.ll";

		runMem2Reg(cSrcfilename, llvmOutFileName);
	}

	@Test
	public void runForWhileWithIfElse1Prog() throws Exception {
		String cSrcfilename = "Mem2Reg6.c";
		String llvmOutFileName = "Mem2RegOut6.ll";

		runMem2Reg(cSrcfilename, llvmOutFileName);
	}

	@Test
	public void runForSimpleIfElseWithUndefProg() throws Exception {
		String cSrcfilename = "Mem2Reg7.c";
		String llvmOutFileName = "Mem2RegOut7.ll";

		runMem2Reg(cSrcfilename, llvmOutFileName);
	}

	@Test
	public void runIfElseWithNDefsInSameBBProg() throws Exception {
		String cSrcfilename = "Mem2Reg8.c";
		String llvmOutFileName = "Mem2RegOut8.ll";

		runMem2Reg(cSrcfilename, llvmOutFileName);
	}

	@Test
	public void runIfElseWithMultiplePhiForArgs() throws Exception {
		String cSrcfilename = "Mem2Reg9.c";
		String llvmOutFileName = "Mem2RegOut9.ll";

		runMem2Reg(cSrcfilename, llvmOutFileName);
	}

	@Test
	public void runIfElseWithPreExistingPhiNode() throws Exception {
		String cSrcfilename = "Mem2Reg10.c";
		String llvmOutFileName = "Mem2RegOut10.ll";

		runMem2Reg(cSrcfilename, llvmOutFileName);
	}

	@Test
	public void runDoWhileWithinWhile() throws Exception {
		String cSrcfilename = "doWhileWithinWhile.c";
		String llvmOutFileName = "doWhileWithinWhileOut.ll";

		runMem2Reg(cSrcfilename, llvmOutFileName);
	}

	@Test
	public void runIfElseIfLadder() throws Exception {
		String cSrcfilename = "IfElseIfLadder.c";
		String llvmOutFileName = "IfElseIfLadderOut.ll";

		runMem2Reg(cSrcfilename, llvmOutFileName);
	}

	@Test
	public void runGlobalVar() throws Exception {
		String cSrcfilename = "GlobalVar.c";
		String llvmOutFileName = "GlobalVarOut.ll";

		runMem2Reg(cSrcfilename, llvmOutFileName);
	}

	@Test
	public void runDerefStructMember() throws Exception {
		String cSrcfilename = "DerefStructMember.c";
		String llvmOutFileName = "DerefStructMemberOut.ll";

		runMem2Reg(cSrcfilename, llvmOutFileName);
	}
	
	@Test
	public void runBinarySearch() throws Exception {
		String cSrcfilename = "BinarySearch.c";
		String llvmOutFileName = "BinarySearchOut.ll";

		runMem2Reg(cSrcfilename, llvmOutFileName);
	}
	
	@Test
	public void runArrayAssignmentInLoop() throws Exception {
		String cSrcfilename = "ArrayAssignmentInLoop.c";
		String llvmOutFileName = "ArrayAssignmentInLoopOut.ll";

		runMem2Reg(cSrcfilename, llvmOutFileName);
	}
	
	@Test
	public void runArrayPointerInStructure() throws Exception {
		String cSrcfilename = "ArrayPointerInStructure.c";
		String llvmOutFileName = "ArrayPointerInStructureOut.ll";

		runMem2Reg(cSrcfilename, llvmOutFileName);
	}
	
	@Test
	public void runCastInstrInStruct() throws Exception {
		String cSrcfilename = "CastInstrInStructure.c";
		String llvmOutFileName = "CastInstrInStructureOut.ll";

		runMem2Reg(cSrcfilename, llvmOutFileName);
	}
	
	@Ignore
	@Test
	public void runAddingTwoNumbers() throws Exception {
		String cSrcfilename = "AddingTwoNumbers.c";
		String llvmOutFileName = "AddingTwoNumbersOut.ll";

		runMem2Reg(cSrcfilename, llvmOutFileName);
	}
	
	/*
	 // Not sure what these are, check them out
	@Test
	public void runFunctionPointer() throws Exception {
		String cSrcfilename = "FunctionPointer.c";
		String llvmOutFileName = "FunctionPointerOut.ll";

		runMem2Reg(cSrcfilename, llvmOutFileName);
	}
	
	
	@Test
	public void runNonCriticalMemAccess() throws Exception {
		String cSrcfilename = "Mem2Reg11.c";
		String llvmOutFileName = "Mem2RegOut11.ll";

		runMem2Reg(cSrcfilename, llvmOutFileName);
	}*/

	@Test
	public void runWithSeveralPointers1() throws Exception {
		String cSrcfilename = "Mem2Reg12.c";
		String llvmOutFileName = "Mem2RegOut12.ll";

		runMem2Reg(cSrcfilename, llvmOutFileName);
	}

	private void runMem2Reg(String srcFileName, String llvmOutFileName)
	throws Exception {

		getRawLLVRIRInstrs(ROOT_PATH, srcFileName);

		Module module = getModule();
		List<Function> functions = module.getFunctions();

		// There can be only one function to test mem2reg in the module.

		Function function = functions.get(functions.size() -1);

		// Mem2reg
		MemToRegPromoter memToRegPromoter = new MemToRegPromoter();
		memToRegPromoter.execute(function);
		System.out.println("AFTER MEM2REG: ");
		emitter.reset();
		List<String> instrsAfterOpt = emitter.emitInstructions(function.getParent());
		printAsm(instrsAfterOpt);
		assertTrue(LLVMIRComparisionUtils.compare(instrsAfterOpt, ROOT_PATH, llvmOutFileName));

	}
}

package org.tamedragon.compilers.opts;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.Module;
import org.tamedragon.common.optimization.MemToRegPromoter;
import org.tamedragon.common.utils.ComparisionUtils;
import org.tamedragon.compilers.LLVMBaseTest;

public class Mem2RegTests extends LLVMBaseTest {

	private final static String ROOT_PATH = "CSrc/Optimizations/Mem2RegTests";

	@Test
	public void runSimpleStraightLineProg() throws Exception {
		String cSrcfilename =  "Mem2Reg1.c";
		String llvmOutFileName = "Mem2RegOut1.bc";

		runMem2Reg(cSrcfilename, llvmOutFileName);
	}

	@Test
	public void runSimpleIfProg() throws Exception {
		String cSrcfilename = "Mem2Reg2.c";
		String llvmOutFileName = "Mem2RegOut2.bc";

		runMem2Reg(cSrcfilename, llvmOutFileName);
	}

	@Test
	public void runSimpleIfElseProg() throws Exception {
		String cSrcfilename = "Mem2Reg3.c";
		String llvmOutFileName = "Mem2RegOut3.bc";

		runMem2Reg(cSrcfilename, llvmOutFileName);
	}

	@Test
	public void runSimpleNestedIfElse1Prog() throws Exception {
		String cSrcfilename = "Mem2Reg4.c";
		String llvmOutFileName = "Mem2RegOut4.bc";

		runMem2Reg(cSrcfilename, llvmOutFileName);
	}

	@Test
	public void runForLoopWithIfElse1Prog() throws Exception {
		String cSrcfilename = "Mem2Reg5.c";
		String llvmOutFileName = "Mem2RegOut5.bc";

		runMem2Reg(cSrcfilename, llvmOutFileName);
	}

	@Test
	public void runForWhileWithIfElse1Prog() throws Exception {
		String cSrcfilename = "Mem2Reg6.c";
		String llvmOutFileName = "Mem2RegOut6.bc";

		runMem2Reg(cSrcfilename, llvmOutFileName);
	}

	@Test
	public void runForSimpleIfElseWithUndefProg() throws Exception {
		String cSrcfilename = "Mem2Reg7.c";
		String llvmOutFileName = "Mem2RegOut7.bc";

		runMem2Reg(cSrcfilename, llvmOutFileName);
	}

	@Test
	public void runIfElseWithNDefsInSameBBProg() throws Exception {
		String cSrcfilename = "Mem2Reg8.c";
		String llvmOutFileName = "Mem2RegOut8.bc";

		runMem2Reg(cSrcfilename, llvmOutFileName);
	}

	@Test
	public void runIfElseWithMultiplePhiForArgs() throws Exception {
		String cSrcfilename = "Mem2Reg9.c";
		String llvmOutFileName = "Mem2RegOut9.bc";

		runMem2Reg(cSrcfilename, llvmOutFileName);
	}

	@Test
	public void runIfElseWithPreExistingPhiNode() throws Exception {
		String cSrcfilename = "Mem2Reg10.c";
		String llvmOutFileName = "Mem2RegOut10.bc";

		runMem2Reg(cSrcfilename, llvmOutFileName);
	}

	@Test
	public void runDoWhileWithinWhile() throws Exception {
		String cSrcfilename = "doWhileWithinWhile.c";
		String llvmOutFileName = "doWhileWithinWhileOut.bc";

		runMem2Reg(cSrcfilename, llvmOutFileName);
	}

	@Test
	public void runIfElseIfLadder() throws Exception {
		String cSrcfilename = "IfElseIfLadder.c";
		String llvmOutFileName = "IfElseIfLadderOut.bc";

		runMem2Reg(cSrcfilename, llvmOutFileName);
	}

	@Test
	public void runGlobalVar() throws Exception {
		String cSrcfilename = "GlobalVar.c";
		String llvmOutFileName = "GlobalVarOut.bc";

		runMem2Reg(cSrcfilename, llvmOutFileName);
	}

	@Test
	public void runDerefStructMember() throws Exception {
		String cSrcfilename = "DerefStructMember.c";
		String llvmOutFileName = "DerefStructMemberOut.bc";

		runMem2Reg(cSrcfilename, llvmOutFileName);
	}
	
	@Test
	public void runBinarySearch() throws Exception {
		String cSrcfilename = "BinarySearch.c";
		String llvmOutFileName = "BinarySearchOut.bc";

		runMem2Reg(cSrcfilename, llvmOutFileName);
	}
	
	@Test
	public void runArrayAssignmentInLoop() throws Exception {
		String cSrcfilename = "ArrayAssignmentInLoop.c";
		String llvmOutFileName = "ArrayAssignmentInLoopOut.bc";

		runMem2Reg(cSrcfilename, llvmOutFileName);
	}
	
	@Test
	public void runArrayPointerInStructure() throws Exception {
		String cSrcfilename = "ArrayPointerInStructure.c";
		String llvmOutFileName = "ArrayPointerInStructureOut.bc";

		runMem2Reg(cSrcfilename, llvmOutFileName);
	}
	
	@Ignore
	@Test
	public void runCastInstrInStruct() throws Exception {
		String cSrcfilename = "CastInstrInStructure.c";
		String llvmOutFileName = "CastInstrInStructureOut.bc";

		runMem2Reg(cSrcfilename, llvmOutFileName);
	}

	@Test
	public void runAddingTwoNumbers() throws Exception {
		String cSrcfilename = "AddingTwoNumbers.c";
		String llvmOutFileName = "AddingTwoNumbersOut.bc";

		runMem2Reg(cSrcfilename, llvmOutFileName);
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

	@Test
	public void runWithSeveralPointers1() throws Exception {
		String cSrcfilename = "Mem2Reg12.c";
		String llvmOutFileName = "Mem2RegOut12.bc";

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
		assertTrue(ComparisionUtils.compare(instrsAfterOpt, ROOT_PATH, llvmOutFileName));

	}
}

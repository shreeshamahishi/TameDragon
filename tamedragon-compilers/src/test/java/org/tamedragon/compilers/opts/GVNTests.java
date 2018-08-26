package org.tamedragon.compilers.opts;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.Module;
import org.tamedragon.common.optimization.GVN;
import org.tamedragon.common.optimization.MemToRegPromoter;
import org.tamedragon.common.utils.ComparisionUtils;
import org.tamedragon.compilers.LLVMBaseTest;

public class GVNTests extends LLVMBaseTest{
	private static final String ROOT_PATH = "CSrc/Optimizations/GVNTests";
	//test 1 for simple local  congruent value
	@Ignore
	@Test
	public void runGVN1() throws Exception {
		String cSrcfilename =  "GVN1.c";
		String llvmOutFileName = "GVN1.bc";

		runGVN(cSrcfilename, llvmOutFileName);
	}
	//test 2 for commutative Binary Operator like + *;
	@Test
	public void runGVNForcommutativeTest() throws Exception {
		String cSrcfilename =  "GVNForCommutativeTest.c";
		String llvmOutFileName = "GVNForCommutativeTest.bc";

		runGVN(cSrcfilename, llvmOutFileName);
	}
	//test 3 For Compare Instruction ICMP
	@Test
	public void cmpInstGVNTest() throws Exception {
		String cSrcfilename =  "IcmpData.c";
		String llvmOutFileName = "IcmpData.bc";

		runGVN(cSrcfilename, llvmOutFileName);
	}
	//test 4 for Casting Instruction
	@Test
	public void castInstGVNTest() throws Exception {
		String cSrcfilename =  "CastInstrTest.c";
		String llvmOutFileName = "CastInstrTest.bc";

		runGVN(cSrcfilename, llvmOutFileName);
	}
	//test 5 for FCMP Instruction
	@Test
	public void fcmpInstrGVNTest() throws Exception {
		String cSrcfilename =  "FCMPInstrTest.c";
		String llvmOutFileName = "FCMPInstrTest.bc";

		runGVN(cSrcfilename, llvmOutFileName);
	}

	//test 6 for Simple GVN in For loop.
	@Ignore
	@Test
	public void simpleGVNinForLoop() throws Exception {
		String cSrcfilename =  "SimpleGVNinForLoop.c";
		String llvmOutFileName = "SimpleGVNinForLoop.bc";

		runGVN(cSrcfilename, llvmOutFileName);
	}

	//test 7 for Simple GVN for global Variable Updating in between.
	@Test
	public void gvnForGlobalVariable() throws Exception {
		String cSrcfilename =  "GVNForGlobalVariable.c";
		String llvmOutFileName = "GVNForGlobalVariable.bc";

		runGVN(cSrcfilename, llvmOutFileName);
	}


	//test 8 for Simple GVN for global Variable not Updating in between.
	@Test
	public void gvnForGlobalVariable1() throws Exception {
		String cSrcfilename =  "GVNForGlobalVariable1.c";
		String llvmOutFileName = "GVNForGlobalVariable1.bc";

		runGVN(cSrcfilename, llvmOutFileName);
	}

	//test 9 for Simple GVN for Array.
	@Test
	public void gvnTestForArrays() throws Exception {
		String cSrcfilename =  "ArraysTests.c";
		String llvmOutFileName = "ArraysTests.bc";

		runGVN(cSrcfilename, llvmOutFileName);
	}

	//test 10 for structure.
	@Ignore
	@Test
	public void castInstrInStructure() throws Exception {
		String cSrcfilename =  "CastInstrInStructure.c";
		String llvmOutFileName = "CastInstrInStructure.bc";

		runGVN(cSrcfilename, llvmOutFileName);
	}

	// Random Test cases for the Different Algorithm	
	//test 11 Addition of two number. Fail
	@Test
	public void addingTwoNumbersTest() throws Exception {
		String cSrcfilename =  "AddingTwoNumbers.c";
		String llvmOutFileName = "AddingTwoNumbers.bc";

		runGVN(cSrcfilename, llvmOutFileName);
	}

	//test 12 Binary Search.
	@Ignore
	@Test
	public void binarySearchTest() throws Exception {
		String cSrcfilename =  "BinarySearch.c";
		String llvmOutFileName = "BinarySearch.bc";

		runGVN(cSrcfilename, llvmOutFileName);
	}

	//test 13 for Simple GVN in For loop.
	@Ignore
	@Test
	public void acessingStructureElement() throws Exception {
		String cSrcfilename =  "AcessingStructureElement.c";
		String llvmOutFileName = "AcessingStructureElement.bc";

		runGVN(cSrcfilename, llvmOutFileName);
	}
	// Test 14 for Accessing Array Element
	@Test
	public void acessingArrayElement() throws Exception {
		String cSrcfilename =  "AcessingArrayElement.c";
		String llvmOutFileName = "AcessingArrayElement.bc";

		runGVN(cSrcfilename, llvmOutFileName);
	}


	// Test 15 for Accessing  Element through Pointer
	@Test
	public void pointerTest() throws Exception {
		String cSrcfilename =  "pointerTest.c";
		String llvmOutFileName = "pointerTest.bc";

		runGVN(cSrcfilename, llvmOutFileName);
	}

	// Test 16 Adding Zero to the variable (dead code Elimination)
	@Test
	public void addingZeroTest() throws Exception {
		String cSrcfilename =  "addingZeroTest.c";
		String llvmOutFileName = "addingZeroTest.bc";

		runGVN(cSrcfilename, llvmOutFileName);
	}
	// Test 16 Adding Zero to the variable (dead code Elimination)
	@Test
	public void subZeroTest() throws Exception {
		String cSrcfilename =  "SubZeroTest.c";
		String llvmOutFileName = "SubZeroTest.bc";

		runGVN(cSrcfilename, llvmOutFileName);
	}

	private void runGVN(String cSrcfilename, String llvmOutFileName) throws Exception  {

		getRawLLVRIRInstrs(ROOT_PATH, cSrcfilename);

		Module module = getModule();
		List<Function> functions = module.getFunctions();

		// There can be only one function to test SCCP in the module.

		//List<String> instrsAfterOpt = new ArrayList<String>();

		for(Function function : functions){
			// Mem2reg
			MemToRegPromoter memToRegPromoter = new MemToRegPromoter();
			memToRegPromoter.execute(function);
			List<String> instrsAfterMem2Reg = emitter.emitInstructions(function);
			System.out.println("AFTER MEM2REG FOR FUNCTION: " + function.getName());
			printAsm(instrsAfterMem2Reg);
			emitter.reset();
		}

		// GVN
		for(Function function : functions){
			GVN gvn = new GVN();
			gvn.execute(function);
			emitter.reset();
		}

		List<String> instrsAfterOpt = emitter.emitInstructions(module);
		System.out.println("AFTER GVN: ");
		printAsm(instrsAfterOpt);

		assertTrue(ComparisionUtils.compare(instrsAfterOpt, ROOT_PATH, llvmOutFileName));
	}
}

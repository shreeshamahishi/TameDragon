package org.tamedragon.common.tests.optimizations.scalar;

import java.util.List;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.Module;
import org.tamedragon.common.llvmir.utils.LLVMIREmitter;
import org.tamedragon.common.optimization.GVN;
import org.tamedragon.common.optimization.MemToRegPromoter;
import org.tamedragon.common.utils.LLVMIRUtils;
import org.tamedragon.common.utils.ComparisionUtils;

public class GVNTests {
	private static final String ROOT_PATH = "ScalarOpts/GVNTests";
	//test 1 for simple local  congruent value
	@Test
	public void runGVN1() throws Exception {
		String cSrcfilename =  "GVN1Src.bc";
		String llvmOutFileName = "GVN1.bc";

		runGVN(cSrcfilename, llvmOutFileName, "foo");
	}
	//test 2 for commutative Binary Operator like + *;
	@Test
	public void runGVNForcommutativeTest() throws Exception {
		String cSrcfilename =  "GVNForCommutativeTestSrc.bc";
		String llvmOutFileName = "GVNForCommutativeTest.bc";

		runGVN(cSrcfilename, llvmOutFileName, "foo");
	}
	//test 3 For Compare Instruction ICMP
	@Test
	public void cmpInstGVNTest() throws Exception {
		String cSrcfilename =  "IcmpDataSrc.bc";
		String llvmOutFileName = "IcmpData.bc";

		runGVN(cSrcfilename, llvmOutFileName, "foo");
	}
	//test 4 for Casting Instruction
	@Test
	public void castInstGVNTest() throws Exception {
		String cSrcfilename =  "CastInstrTestSrc.bc";
		String llvmOutFileName = "CastInstrTest.bc";

		runGVN(cSrcfilename, llvmOutFileName, "foo");
	}
	//test 5 for FCMP Instruction
	@Test
	public void fcmpInstrGVNTest() throws Exception {
		String cSrcfilename =  "FCMPInstrTestSrc.bc";
		String llvmOutFileName = "FCMPInstrTest.bc";

		runGVN(cSrcfilename, llvmOutFileName, "foo");
	}
	
	//test 6 for Simple GVN in For loop.
	@Test
	public void simpleGVNinForLoop() throws Exception {
		String cSrcfilename =  "SimpleGVNinForLoopSrc.bc";
		String llvmOutFileName = "SimpleGVNinForLoop.bc";

		runGVN(cSrcfilename, llvmOutFileName, "foo");
	}
	
	//test 7 for Simple GVN for global Variable Updating in between.
	@Test
	public void gvnForGlobalVariable() throws Exception {
		String cSrcfilename =  "GVNForGlobalVariableSrc.bc";
		String llvmOutFileName = "GVNForGlobalVariable.bc";

		runGVN(cSrcfilename, llvmOutFileName, "foo");
	}
	
	
	//test 8 for Simple GVN for global Variable not Updating in between.
	@Test
	public void gvnForGlobalVariable1() throws Exception {
		String cSrcfilename =  "GVNForGlobalVariable1Src.bc";
		String llvmOutFileName = "GVNForGlobalVariable1.bc";

		runGVN(cSrcfilename, llvmOutFileName, "foo");
	}
	
	//test 9 for Simple GVN for Array.
	@Test
	public void gvnTestForArrays() throws Exception {
		String cSrcfilename =  "ArraysTestsSrc.bc";
		String llvmOutFileName = "ArraysTests.bc";

		runGVN(cSrcfilename, llvmOutFileName, "fun");
	}
	
	//test 10 for structure.
	@Test
	public void castInstrInStructure() throws Exception {
		String cSrcfilename =  "CastInstrInStructureSrc.bc";
		String llvmOutFileName = "CastInstrInStructure.bc";

		runGVN(cSrcfilename, llvmOutFileName, "fun");
	}
	
// Random Test cases for the Different Algorithm	
	//test 11 Addition of two number. Fail
	@Test
	public void addingTwoNumbersTest() throws Exception {
		String cSrcfilename =  "AddingTwoNumbersSrc.bc";
		String llvmOutFileName = "AddingTwoNumbers.bc";

		runGVN(cSrcfilename, llvmOutFileName, "main");
	}
	
	//test 12 Binary Search.
	//Fail
	@Test
	public void binarySearchTest() throws Exception {
		String cSrcfilename =  "BinarySearchSrc.bc";
		String llvmOutFileName = "BinarySearch.bc";

		runGVN(cSrcfilename, llvmOutFileName, "main");
	}
	
	//test 13 for Simple GVN in For loop.
	@Test
	public void acessingStructureElement() throws Exception {
		String cSrcfilename =  "AcessingStructureElementSrc.bc";
		String llvmOutFileName = "AcessingStructureElement.bc";

		runGVN(cSrcfilename, llvmOutFileName, "fun");
	}
	// Test 14 for Accessing Array Element
	@Test
	public void acessingArrayElement() throws Exception {
		String cSrcfilename =  "AcessingArrayElementSrc.bc";
		String llvmOutFileName = "AcessingArrayElement.bc";

		runGVN(cSrcfilename, llvmOutFileName, "foo");
	}
	
	
	// Test 15 for Accessing  Element through Pointer
	@Test
	public void pointerTest() throws Exception {
		String cSrcfilename =  "pointerTestSrc.bc";
		String llvmOutFileName = "pointerTest.bc";

		runGVN(cSrcfilename, llvmOutFileName, "foo");
	}
	
	// Test 16 Adding Zero to the variable (dead code Elimination)
	@Test
	public void addingZeroTest() throws Exception {
		String cSrcfilename =  "addingZeroTestSrc.bc";
		String llvmOutFileName = "addingZeroTest.bc";

		runGVN(cSrcfilename, llvmOutFileName, "addingZero");
	}
	// Test 17 Adding Zero to the variable (dead code Elimination)
	@Test
	public void subZeroTest() throws Exception {
		String cSrcfilename =  "SubZeroTestSrc.bc";
		String llvmOutFileName = "SubZeroTest.bc";

		runGVN(cSrcfilename, llvmOutFileName, "subsZero");
	}
	
	// Test 18
	@Test
	public void insideIfElseTest() throws Exception {
		String cSrcfilename =  "ifElseTest.bc";
		String llvmOutFileName = "IfElseOut.bc";

		runGVN(cSrcfilename, llvmOutFileName, "foo");
	}
	
	
	private void runGVN(String cSrcfilename, String llvmOutFileName, String functionNameToOptimize) throws Exception  {
		LLVMIRUtils llvmirUtils = new LLVMIRUtils();
		llvmirUtils.getInstructionsList(ROOT_PATH, cSrcfilename);

		Module module = llvmirUtils.getModule();
		List<Function> functions = module.getFunctions();
		Function function = null;
		// There can be only one function to test SCCP in the module.
		for(Function currentFunction : functions){
			String function_name = currentFunction.getName();

			if(function_name.equals(functionNameToOptimize))
			{
				function = currentFunction;
				break;

			}}
		//		Function function = functions.get(functions.size() -1);
		
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
		List<String> instrsAfterOpt = emitter.emitInstructions(module);
		llvmirUtils.printAsm(instrsAfterOpt);
		
		// GVN
		GVN gvn = new GVN();
		gvn.execute(function);
		System.out.println("AFTER GVN: ");
		emitter.reset();
		instrsAfterOpt = emitter.emitInstructions(module);
		llvmirUtils.printAsm(instrsAfterOpt);
		
		assertTrue(ComparisionUtils.compare(instrsAfterOpt, ROOT_PATH, llvmOutFileName));
	}
}

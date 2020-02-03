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
		String cSrcfilename =  "GVN1Src.ll";
		String llvmOutFileName = "GVN1.ll";

		runGVN(cSrcfilename, llvmOutFileName, "foo");
	}
	//test 2 for commutative Binary Operator like + *;
	@Test
	public void runGVNForcommutativeTest() throws Exception {
		String cSrcfilename =  "GVNForCommutativeTestSrc.ll";
		String llvmOutFileName = "GVNForCommutativeTest.ll";

		runGVN(cSrcfilename, llvmOutFileName, "foo");
	}
	//test 3 For Compare Instruction ICMP
	@Test
	public void cmpInstGVNTest() throws Exception {
		String cSrcfilename =  "IcmpDataSrc.ll";
		String llvmOutFileName = "IcmpData.ll";

		runGVN(cSrcfilename, llvmOutFileName, "foo");
	}
	//test 4 for Casting Instruction
	@Test
	public void castInstGVNTest() throws Exception {
		String cSrcfilename =  "CastInstrTestSrc.ll";
		String llvmOutFileName = "CastInstrTest.ll";

		runGVN(cSrcfilename, llvmOutFileName, "foo");
	}
	//test 5 for FCMP Instruction
	@Test
	public void fcmpInstrGVNTest() throws Exception {
		String cSrcfilename =  "FCMPInstrTestSrc.ll";
		String llvmOutFileName = "FCMPInstrTest.ll";

		runGVN(cSrcfilename, llvmOutFileName, "foo");
	}
	
	//test 6 for Simple GVN in For loop.
	@Test
	public void simpleGVNinForLoop() throws Exception {
		String cSrcfilename =  "SimpleGVNinForLoopSrc.ll";
		String llvmOutFileName = "SimpleGVNinForLoop.ll";

		runGVN(cSrcfilename, llvmOutFileName, "foo");
	}
	
	//test 7 for Simple GVN for global Variable Updating in between.
	@Test
	public void gvnForGlobalVariable() throws Exception {
		String cSrcfilename =  "GVNForGlobalVariableSrc.ll";
		String llvmOutFileName = "GVNForGlobalVariable.ll";

		runGVN(cSrcfilename, llvmOutFileName, "foo");
	}
	
	
	//test 8 for Simple GVN for global Variable not Updating in between.
	@Test
	public void gvnForGlobalVariable1() throws Exception {
		String cSrcfilename =  "GVNForGlobalVariable1Src.ll";
		String llvmOutFileName = "GVNForGlobalVariable1.ll";

		runGVN(cSrcfilename, llvmOutFileName, "foo");
	}
	
	//test 9 for Simple GVN for Array.
	@Test
	public void gvnTestForArrays() throws Exception {
		String cSrcfilename =  "ArraysTestsSrc.ll";
		String llvmOutFileName = "ArraysTests.ll";

		runGVN(cSrcfilename, llvmOutFileName, "fun");
	}
	
	//test 10 for structure.
	@Test
	public void castInstrInStructure() throws Exception {
		String cSrcfilename =  "CastInstrInStructureSrc.ll";
		String llvmOutFileName = "CastInstrInStructure.ll";

		runGVN(cSrcfilename, llvmOutFileName, "fun");
	}
	
// Random Test cases for the Different Algorithm	
	//test 11 Addition of two number. Fail
	@Test
	public void addingTwoNumbersTest() throws Exception {
		String cSrcfilename =  "AddingTwoNumbersSrc.ll";
		String llvmOutFileName = "AddingTwoNumbers.ll";

		runGVN(cSrcfilename, llvmOutFileName, "main");
	}
	
	//test 12 Binary Search.
	//Fail
	@Test
	public void binarySearchTest() throws Exception {
		String cSrcfilename =  "BinarySearchSrc.ll";
		String llvmOutFileName = "BinarySearch.ll";

		runGVN(cSrcfilename, llvmOutFileName, "main");
	}
	
	//test 13 for Simple GVN in For loop.
	@Test
	public void acessingStructureElement() throws Exception {
		String cSrcfilename =  "AcessingStructureElementSrc.ll";
		String llvmOutFileName = "AcessingStructureElement.ll";

		runGVN(cSrcfilename, llvmOutFileName, "fun");
	}
	// Test 14 for Accessing Array Element
	@Test
	public void acessingArrayElement() throws Exception {
		String cSrcfilename =  "AcessingArrayElementSrc.ll";
		String llvmOutFileName = "AcessingArrayElement.ll";

		runGVN(cSrcfilename, llvmOutFileName, "foo");
	}
	
	
	// Test 15 for Accessing  Element through Pointer
	@Test
	public void pointerTest() throws Exception {
		String cSrcfilename =  "pointerTestSrc.ll";
		String llvmOutFileName = "pointerTest.ll";

		runGVN(cSrcfilename, llvmOutFileName, "foo");
	}
	
	// Test 16 Adding Zero to the variable (dead code Elimination)
	@Test
	public void addingZeroTest() throws Exception {
		String cSrcfilename =  "addingZeroTestSrc.ll";
		String llvmOutFileName = "addingZeroTest.ll";

		runGVN(cSrcfilename, llvmOutFileName, "addingZero");
	}
	// Test 17 Adding Zero to the variable (dead code Elimination)
	@Test
	public void subZeroTest() throws Exception {
		String cSrcfilename =  "SubZeroTestSrc.ll";
		String llvmOutFileName = "SubZeroTest.ll";

		runGVN(cSrcfilename, llvmOutFileName, "subsZero");
	}
	
	// Test 18
	@Test
	public void insideIfElseTest() throws Exception {
		String cSrcfilename =  "ifElseTest.ll";
		String llvmOutFileName = "IfElseOut.ll";

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

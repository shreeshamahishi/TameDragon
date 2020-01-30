package org.tamedragon.compilers.opts;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

import org.tamedragon.common.llvmir.instructions.LLVMUtility;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.Module;
import org.tamedragon.common.optimization.GVN;
import org.tamedragon.common.optimization.MemToRegPromoter;
import org.tamedragon.common.utils.LLVMIRComparisionUtils;
import org.tamedragon.compilers.LLVMBaseTest;
import org.tamedragon.compilers.clang.CLangUtils;
import org.tamedragon.compilers.clang.CompilerSettings;

public class GVNTests extends LLVMBaseTest{

	private CompilerSettings compilerSettings;
	private String projectPath = "CSrc/Optimizations/GVNTests/";
	private String projectRootPath;
	
	@Before
	public void setUp(){		
		super.setUp();
		properties = LLVMUtility.getDefaultProperties();
		
		CLangUtils.populateSettings();
		compilerSettings = CompilerSettings.getInstance();
		compilerSettings.setProjectPath(projectPath);

		projectRootPath = compilerSettings.getProjectRoot();
	}
	
	@Ignore
	@Test
	public void runGVN1() throws Exception {
		String cSrcfilename =  "GVN1.c";
		String llvmOutFileName = "GVN1.ll";

		runGVN(cSrcfilename, llvmOutFileName);
	}

	@Test
	public void runGVNForcommutativeTest() throws Exception {
		String cSrcfilename =  "GVNForCommutativeTest.c";
		String llvmOutFileName = "GVNForCommutativeTest.ll";

		runGVN(cSrcfilename, llvmOutFileName);
	}

	@Test
	public void cmpInstGVNTest() throws Exception {
		String cSrcfilename =  "IcmpData.c";
		String llvmOutFileName = "IcmpData.ll";

		runGVN(cSrcfilename, llvmOutFileName);
	}

	@Test
	public void castInstGVNTest() throws Exception {
		String cSrcfilename =  "CastInstrTest.c";
		String llvmOutFileName = "CastInstrTest.ll";

		runGVN(cSrcfilename, llvmOutFileName);
	}

	@Test
	public void fcmpInstrGVNTest() throws Exception {
		String cSrcfilename =  "FCMPInstrTest.c";
		String llvmOutFileName = "FCMPInstrTest.ll";

		runGVN(cSrcfilename, llvmOutFileName);
	}

	@Ignore
	@Test
	public void simpleGVNinForLoop() throws Exception {
		String cSrcfilename =  "SimpleGVNinForLoop.c";
		String llvmOutFileName = "SimpleGVNinForLoop.ll";

		runGVN(cSrcfilename, llvmOutFileName);
	}

	@Test
	public void gvnForGlobalVariable() throws Exception {
		String cSrcfilename =  "GVNForGlobalVariable.c";
		String llvmOutFileName = "GVNForGlobalVariable.ll";

		runGVN(cSrcfilename, llvmOutFileName);
	}

	@Test
	public void gvnForGlobalVariable1() throws Exception {
		String cSrcfilename =  "GVNForGlobalVariable1.c";
		String llvmOutFileName = "GVNForGlobalVariable1.ll";

		runGVN(cSrcfilename, llvmOutFileName);
	}

	@Test
	public void gvnTestForArrays() throws Exception {
		String cSrcfilename =  "ArraysTests.c";
		String llvmOutFileName = "ArraysTests.ll";

		runGVN(cSrcfilename, llvmOutFileName);
	}

	@Ignore
	@Test
	public void castInstrInStructure() throws Exception {
		String cSrcfilename =  "CastInstrInStructure.c";
		String llvmOutFileName = "CastInstrInStructure.ll";

		runGVN(cSrcfilename, llvmOutFileName);
	}

	@Ignore
	@Test
	public void addingTwoNumbersTest() throws Exception {
		String cSrcfilename =  "AddingTwoNumbers.c";
		String llvmOutFileName = "AddingTwoNumbers.ll";

		runGVN(cSrcfilename, llvmOutFileName);
	}

	@Ignore
	@Test
	public void binarySearchTest() throws Exception {
		String cSrcfilename =  "BinarySearch.c";
		String llvmOutFileName = "BinarySearch.ll";

		runGVN(cSrcfilename, llvmOutFileName);
	}

	@Ignore
	@Test
	public void acessingStructureElement() throws Exception {
		String cSrcfilename =  "AcessingStructureElement.c";
		String llvmOutFileName = "AcessingStructureElement.ll";

		runGVN(cSrcfilename, llvmOutFileName);
	}

	@Test
	public void acessingArrayElement() throws Exception {
		String cSrcfilename =  "AcessingArrayElement.c";
		String llvmOutFileName = "AcessingArrayElement.ll";

		runGVN(cSrcfilename, llvmOutFileName);
	}

	@Test
	public void pointerTest() throws Exception {
		String cSrcfilename =  "pointerTest.c";
		String llvmOutFileName = "pointerTest.ll";

		runGVN(cSrcfilename, llvmOutFileName);
	}

	@Test
	public void addingZeroTest() throws Exception {
		String cSrcfilename =  "addingZeroTest.c";
		String llvmOutFileName = "addingZeroTest.ll";

		runGVN(cSrcfilename, llvmOutFileName);
	}

	@Test
	public void subZeroTest() throws Exception {
		String cSrcfilename =  "SubZeroTest.c";
		String llvmOutFileName = "SubZeroTest.ll";

		runGVN(cSrcfilename, llvmOutFileName);
	}

	private void runGVN(String cSrcfilename, String llvmOutFileName) throws Exception  {

		getRawLLVRIRInstrs(projectRootPath + projectPath, cSrcfilename);

		Module module = getModule();
		List<Function> functions = module.getFunctions();

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

		assertTrue(LLVMIRComparisionUtils.compare(instrsAfterOpt, projectPath, llvmOutFileName));
	}
}

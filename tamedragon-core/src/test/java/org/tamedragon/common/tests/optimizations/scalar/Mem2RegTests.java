package org.tamedragon.common.tests.optimizations.scalar;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.junit.Test;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.Module;
import org.tamedragon.common.llvmir.utils.LLVMIREmitter;
import org.tamedragon.common.optimization.MemToRegPromoter;
import org.tamedragon.common.utils.ComparisionUtils;
import org.tamedragon.common.utils.LLVMIRUtils;
	import org.xml.sax.SAXException;

public class Mem2RegTests  {

	private static final String ROOT_PATH = "ScalarOpts/Mem2Reg";

	@Test
	public void tempTest() {
		Source xmlFile = new StreamSource(new File(this.getClass().getClassLoader().getResource("EFilingBatchXMLMini.xml").getFile()));
		
		SchemaFactory schemaFactory = SchemaFactory
				.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		try {
			String schemaFileName = "EFL_SARXBatchSchema.xsd";
			// String schemaFileName = "BSA_XML_2_0.xsd";
			URL schemaFileUrl = this.getClass().getClassLoader().getResource(schemaFileName);
			Schema schema = schemaFactory.newSchema(schemaFileUrl);
			Validator validator = schema.newValidator();
			validator.validate(xmlFile);
			System.out.println(xmlFile.getSystemId() + " is valid");
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/*
	@Test
	public void runSample2() throws Exception {
		String cSrcfilename =  "TDProg2Raw.ll";
		String llvmOutFileName = "TDProg2RawOutTD.ll";

		runMem2Reg(cSrcfilename, llvmOutFileName, "Prog2");
	}

	@Test
	public void runSimpleStraightLineProg() throws Exception {
		String cSrcfilename =  "Mem2RegSrc1.ll";
		String llvmOutFileName = "Mem2RegOut1.ll";

		runMem2Reg(cSrcfilename, llvmOutFileName, "foo");
	}

	@Test
	public void runSimpleIfProg() throws Exception {
		String cSrcfilename =  "Mem2RegSrc2.ll";
		String llvmOutFileName = "Mem2RegOut2.ll";

		runMem2Reg(cSrcfilename, llvmOutFileName, "foo");
	}

	@Test
	public void runSimpleIfElseProg() throws Exception {
		String cSrcfilename =  "Mem2RegSrc3.ll";
		String llvmOutFileName = "Mem2RegOut3.ll";

		runMem2Reg(cSrcfilename, llvmOutFileName, "foo");
	}

	@Test
	public void runSimpleNestedIfElse1Prog() throws Exception {
		String cSrcfilename =  "Mem2RegSrc4.ll";
		String llvmOutFileName = "Mem2RegOut4.ll";

		runMem2Reg(cSrcfilename, llvmOutFileName, "foo");
	}

	@Test
	public void runForLoopWithIfElse1Prog() throws Exception {
		String cSrcfilename =  "Mem2RegSrc5.ll";
		String llvmOutFileName = "Mem2RegOut5.ll";

		runMem2Reg(cSrcfilename, llvmOutFileName, "foo");
	}

	@Test
	public void runForWhileWithIfElse1Prog() throws Exception {
		String cSrcfilename =  "Mem2RegSrc6.ll";
		String llvmOutFileName = "Mem2RegOut6.ll";

		runMem2Reg(cSrcfilename, llvmOutFileName, "foo");
	}

	@Test
	public void runForSimpleIfElseWithUndefProg() throws Exception {
		String cSrcfilename =  "Mem2RegSrc7.ll";
		String llvmOutFileName = "Mem2RegOut7.ll";

		runMem2Reg(cSrcfilename, llvmOutFileName, "foo");
	}

	@Test
	public void runIfElseWithNDefsInSameBBProg() throws Exception {
		String cSrcfilename =  "Mem2RegSrc8.ll";
		String llvmOutFileName = "Mem2RegOut8.ll";

		runMem2Reg(cSrcfilename, llvmOutFileName, "foo");
	}

	@Test
	public void runIfElseWithMultiplePhiForArgs() throws Exception {
		String cSrcfilename =  "Mem2RegSrc9.ll";
		String llvmOutFileName = "Mem2RegOut9.ll";

		runMem2Reg(cSrcfilename, llvmOutFileName, "foo");
	}

	@Test
	public void runIfElseWithPreExistingPhiNode() throws Exception {
		String cSrcfilename =  "Mem2RegSrc10.ll";
		String llvmOutFileName = "Mem2RegOut10.ll";

		runMem2Reg(cSrcfilename, llvmOutFileName, "foo");
	}

	@Test
	public void runWithSeveralPointers1() throws Exception {
		String cSrcfilename = "Mem2RegSrc12.ll";
		String llvmOutFileName = "Mem2RegOut12.ll";

		runMem2Reg(cSrcfilename, llvmOutFileName, "bar");
	}

	@Test
	public void runDoWhileWithinWhile() throws Exception {
		String cSrcfilename = "doWhileWithinWhileSrc.ll";
		String llvmOutFileName = "doWhileWithinWhileOut.ll";

		runMem2Reg(cSrcfilename, llvmOutFileName, "foo");
	}

	@Test
	public void runIfElseIfLadder() throws Exception {
		String cSrcfilename = "IfElseIfLadderSrc.ll";
		String llvmOutFileName = "IfElseIfLadderOut.ll";

		runMem2Reg(cSrcfilename, llvmOutFileName, "foo");
	}

	@Test
	public void runGlobalVar() throws Exception {
		String cSrcfilename = "GlobalVarSrc.ll";
		String llvmOutFileName = "GlobalVarOut.ll";

		runMem2Reg(cSrcfilename, llvmOutFileName, "foo");
	}

	@Test
	public void runDerefStructMember() throws Exception {
		String cSrcfilename = "DerefStructMemberSrc.ll";
		String llvmOutFileName = "DerefStructMemberOut.ll";

		runMem2Reg(cSrcfilename, llvmOutFileName,"foo");
	}

	@Test
	public void runBinarySearch() throws Exception {
		String cSrcfilename = "BinarySearchSrc.ll";
		String llvmOutFileName = "BinarySearchOut.ll";

		runMem2Reg(cSrcfilename, llvmOutFileName,"main");
	}

	@Test
	public void runArrayAssignmentInLoop() throws Exception {
		String cSrcfilename = "ArrayAssignmentInLoopSrc.ll";
		String llvmOutFileName = "ArrayAssignmentInLoopOut.ll";

		runMem2Reg(cSrcfilename, llvmOutFileName,"foo");
	}

	@Test
	public void runArrayPointerInStructure() throws Exception {
		String cSrcfilename = "ArrayPointerInStructureSrc.ll";
		String llvmOutFileName = "ArrayPointerInStructureOut.ll";

		runMem2Reg(cSrcfilename, llvmOutFileName,"foo");
	}

	@Test
	public void runCastInstrInStruct() throws Exception {
		String cSrcfilename = "CastInstrInStructureSrc.ll";
		String llvmOutFileName = "CastInstrInStructureOut.ll";

		runMem2Reg(cSrcfilename, llvmOutFileName,"fun");
	}

	@Test
	public void runAddingTwoNumbers() throws Exception {
		String cSrcfilename = "AddingTwoNumbersSrc.ll";
		String llvmOutFileName = "AddingTwoNumbersOut.ll";

		runMem2Reg(cSrcfilename, llvmOutFileName,"main");
	}

	@Test
	public void Test() throws Exception {
		String cSrcfilename = "TestIn.ll";
		String llvmOutFileName = "TestOut.ll";

		runMem2Reg(cSrcfilename, llvmOutFileName,"foo");
	}

	@Test
	public void ComplexStruct1Test() throws Exception {
		String cSrcfilename = "ComplexStruct1Src.ll";
		String llvmOutFileName = "ComplexStruct1Out.ll";

		runMem2Reg(cSrcfilename, llvmOutFileName,"foo");
	}
*/
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

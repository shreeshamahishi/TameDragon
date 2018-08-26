package org.tamedragon.common.loadstoreanalysis;

import static org.junit.Assert.assertTrue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.tamedragon.common.dataflowanalysis.LoadStoreAnalysis;
import org.tamedragon.common.llvmir.instructions.LoadInst;
import org.tamedragon.common.llvmir.instructions.StoreInst;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.Module;
import org.tamedragon.common.llvmir.utils.LLVMIREmitter;
import org.tamedragon.common.utils.LLVMIRUtils;

public class LoadStoreAnalysisTests {

	private static final String ROOT_PATH = "CSrc/LoadStoreAnalysis/";

	private static final String TARGET_PATH = "D:\\shreesha\\work\\CompilerVision\\trunk\\Core\\TestData\\LoadStoreAnalysis";

	LLVMIREmitter emitter = LLVMIREmitter.getInstance();

	private LoadStoreAnalysis loadStoreAnalysis;

	@Test
	public void simpleLoadStore() throws Exception {
		String cSrcfilename =  "SimpleLoadStore.c";

		Function function = getFunction(cSrcfilename);

		loadStoreAnalysis = new LoadStoreAnalysis(function);
		loadStoreAnalysis.analyze();

		BasicBlock bb1 = function.getBasicBlockAt(0);
		LoadInst loadIns = (LoadInst)bb1.getInstructionAt(8);

		List<StoreInst> storeInstrs = loadStoreAnalysis.getStoresForLoad(loadIns);
		assertTrue(storeInstrs.size() == 1);

		StoreInst si = storeInstrs.get(0);
		StoreInst storeInst = (StoreInst)bb1.getInstructionAt(6);
		assertEquals(si, storeInst);
	}

	@Test
	public void simpleStoreOverwrite() throws Exception {
		String cSrcfilename =  "SimpleStoreOverwrite.c";

		Function function = getFunction(cSrcfilename);

		loadStoreAnalysis = new LoadStoreAnalysis(function);
		loadStoreAnalysis.analyze();

		BasicBlock bb1 = function.getBasicBlockAt(0);
		LoadInst loadIns = (LoadInst)bb1.getInstructionAt(7);

		List<StoreInst> storeInstrs = loadStoreAnalysis.getStoresForLoad(loadIns);
		assertTrue(storeInstrs.size() == 1);

		StoreInst si = storeInstrs.get(0);
		StoreInst storeInst = (StoreInst)bb1.getInstructionAt(6);
		assertEquals(si, storeInst);

	}

	@Test
	public void ifCondition() throws Exception {
		String cSrcfilename =  "IfCondition.c";

		Function function = getFunction(cSrcfilename);

		loadStoreAnalysis = new LoadStoreAnalysis(function);
		loadStoreAnalysis.analyze();

		BasicBlock bb0 = function.getBasicBlockAt(0);
		BasicBlock bb1 = function.getBasicBlockAt(1);
		BasicBlock bb2 = function.getBasicBlockAt(2);
		LoadInst loadIns = (LoadInst)bb2.getInstructionAt(1);

		List<StoreInst> storeInstrs = loadStoreAnalysis.getStoresForLoad(loadIns);
		assertTrue(storeInstrs.size() == 2);

		StoreInst si1 = storeInstrs.get(0);
		StoreInst si2 = storeInstrs.get(1);
		StoreInst str1 = (StoreInst)bb0.getInstructionAt(5);
		StoreInst str2 = (StoreInst)bb1.getInstructionAt(0);
		assertTrue((si1 == str1 && si2 == str2) || (si1 == str2 && si2 == str1));

	}

	@Test
	public void ifElseConditionWithInitInBothBranches() throws Exception {
		String cSrcfilename =  "IfElseConditionWithInitInBothBranches.c";

		Function function = getFunction(cSrcfilename);

		loadStoreAnalysis = new LoadStoreAnalysis(function);
		loadStoreAnalysis.analyze();

		BasicBlock bb1 = function.getBasicBlockAt(1);
		BasicBlock bb2 = function.getBasicBlockAt(2);
		BasicBlock bb3 = function.getBasicBlockAt(3);
		LoadInst loadIns = (LoadInst)bb3.getInstructionAt(1);

		List<StoreInst> storeInstrs = loadStoreAnalysis.getStoresForLoad(loadIns);
		assertTrue(storeInstrs.size() == 2);

		StoreInst si1 = storeInstrs.get(0);
		StoreInst si2 = storeInstrs.get(1);
		StoreInst str1 = (StoreInst)bb1.getInstructionAt(0);
		StoreInst str2 = (StoreInst)bb2.getInstructionAt(0);
		assertTrue((si1 == str1 && si2 == str2) || (si1 == str2 && si2 == str1));
	}

	@Test
	public void ifElseConditionWithInitInOneBranch() throws Exception {
		String cSrcfilename =  "IfElseConditionWithInitInOneBranch.c";

		Function function = getFunction(cSrcfilename);

		loadStoreAnalysis = new LoadStoreAnalysis(function);
		loadStoreAnalysis.analyze();

		BasicBlock bb0 = function.getBasicBlockAt(0);
		BasicBlock bb1 = function.getBasicBlockAt(1);
		BasicBlock bb2 = function.getBasicBlockAt(2);
		BasicBlock bb3 = function.getBasicBlockAt(3);
		LoadInst loadIns1 = (LoadInst)bb3.getInstructionAt(1);

		List<StoreInst> storeInstrs = loadStoreAnalysis.getStoresForLoad(loadIns1);
		assertTrue(storeInstrs.size() == 2);

		StoreInst si1 = storeInstrs.get(0);
		StoreInst si2 = storeInstrs.get(1);
		StoreInst str1 = (StoreInst)bb0.getInstructionAt(5);
		StoreInst str2 = (StoreInst)bb1.getInstructionAt(0);
		assertTrue((si1 == str1 && si2 == str2) || (si1 == str2 && si2 == str1));

		LoadInst loadIns2 = (LoadInst)bb2.getInstructionAt(0);
		storeInstrs = loadStoreAnalysis.getStoresForLoad(loadIns2);
		assertTrue(storeInstrs.size() == 1);
		si1 = storeInstrs.get(0);
		assertTrue(si1 == str1);

	}

	@Test
	public void simpleLoopIncrement() throws Exception {
		String cSrcfilename =  "SimpleLoopIncrement.c";

		Function function = getFunction(cSrcfilename);

		loadStoreAnalysis = new LoadStoreAnalysis(function);
		loadStoreAnalysis.analyze();

		BasicBlock bb0 = function.getBasicBlockAt(0);
		BasicBlock bb2 = function.getBasicBlockAt(2);
		BasicBlock bb3 = function.getBasicBlockAt(3);
		LoadInst loadIns1 = (LoadInst)bb3.getInstructionAt(0);

		List<StoreInst> storeInstrs = loadStoreAnalysis.getStoresForLoad(loadIns1);
		assertTrue(storeInstrs.size() == 2);

		StoreInst si1 = storeInstrs.get(0);
		StoreInst si2 = storeInstrs.get(1);
		StoreInst str1 = (StoreInst)bb0.getInstructionAt(4);
		StoreInst str2 = (StoreInst)bb2.getInstructionAt(2);
		assertTrue((si1 == str1 && si2 == str2) || (si1 == str2 && si2 == str1));

		LoadInst loadIns2 = (LoadInst)bb2.getInstructionAt(0);
		storeInstrs = loadStoreAnalysis.getStoresForLoad(loadIns2);
		assertTrue(storeInstrs.size() == 2);
		si1 = storeInstrs.get(0);
		si2 = storeInstrs.get(1);

		assertTrue((si1 == str1 && si2 == str2) || (si1 == str2 && si2 == str1));
	}

	@Test
	public void ifInsideLoop() throws Exception {
		String cSrcfilename =  "IfInsideLoop.c";

		Function function = getFunction(cSrcfilename);

		loadStoreAnalysis = new LoadStoreAnalysis(function);
		loadStoreAnalysis.analyze();

		BasicBlock bb0 = function.getBasicBlockAt(0);
		BasicBlock bb2 = function.getBasicBlockAt(2);
		BasicBlock bb3 = function.getBasicBlockAt(3);
		BasicBlock bb4 = function.getBasicBlockAt(4);
		BasicBlock bb5 = function.getBasicBlockAt(5);

		LoadInst loadIns1 = (LoadInst)bb2.getInstructionAt(0);

		List<StoreInst> storeInstrs = loadStoreAnalysis.getStoresForLoad(loadIns1);
		assertTrue(storeInstrs.size() == 3);

		StoreInst str1 = (StoreInst)bb0.getInstructionAt(10);
		StoreInst str2 = (StoreInst)bb3.getInstructionAt(2);
		StoreInst str4 = (StoreInst)bb2.getInstructionAt(2);
		assertTrue(storeInstrs.contains(str1) && storeInstrs.contains(str2) && storeInstrs.contains(str4));

		LoadInst loadIns2 = (LoadInst)bb3.getInstructionAt(0);
		storeInstrs = loadStoreAnalysis.getStoresForLoad(loadIns2);
		assertTrue(storeInstrs.size() == 1);
		assertTrue(storeInstrs.contains(str4));

		LoadInst loadIns3 = (LoadInst)bb3.getInstructionAt(3);
		storeInstrs = loadStoreAnalysis.getStoresForLoad(loadIns3);
		assertTrue(storeInstrs.size() == 1);
		assertTrue(storeInstrs.contains(str2));

		LoadInst loadIns4 = (LoadInst)bb4.getInstructionAt(4);
		storeInstrs = loadStoreAnalysis.getStoresForLoad(loadIns4);
		assertTrue(storeInstrs.size() == 2);
		assertTrue(storeInstrs.contains(str2) && storeInstrs.contains(str4));

		LoadInst loadIns5 = (LoadInst)bb5.getInstructionAt(0);
		storeInstrs = loadStoreAnalysis.getStoresForLoad(loadIns5);
		assertTrue(storeInstrs.size() == 3);
		assertTrue(storeInstrs.contains(str1) && storeInstrs.contains(str2) && storeInstrs.contains(str4));
	}

	@Test
	public void ifElseInsideLoop() throws Exception {
		String cSrcfilename =  "IfElseInsideLoop.c";

		Function function = getFunction(cSrcfilename);

		loadStoreAnalysis = new LoadStoreAnalysis(function);
		loadStoreAnalysis.analyze();

		BasicBlock bb0 = function.getBasicBlockAt(0);
		BasicBlock bb2 = function.getBasicBlockAt(2);
		BasicBlock bb3 = function.getBasicBlockAt(3);
		BasicBlock bb4 = function.getBasicBlockAt(4);
		BasicBlock bb5 = function.getBasicBlockAt(5);
		BasicBlock bb6 = function.getBasicBlockAt(6);

		StoreInst str1 = (StoreInst)bb0.getInstructionAt(8);
		StoreInst str2 = (StoreInst)bb3.getInstructionAt(2);
		StoreInst str3 = (StoreInst)bb4.getInstructionAt(2);
		StoreInst str4 = (StoreInst)bb2.getInstructionAt(2);

		LoadInst loadIns1 = (LoadInst)bb2.getInstructionAt(0);

		List<StoreInst> storeInstrs = loadStoreAnalysis.getStoresForLoad(loadIns1);
		assertTrue(storeInstrs.size() == 3);
		assertTrue(storeInstrs.contains(str1) && storeInstrs.contains(str2) && storeInstrs.contains(str3));

		LoadInst loadIns2 = (LoadInst)bb3.getInstructionAt(0);
		storeInstrs = loadStoreAnalysis.getStoresForLoad(loadIns2);
		assertTrue(storeInstrs.size() == 1);
		assertTrue(storeInstrs.contains(str4));

		LoadInst loadIns3 = (LoadInst)bb4.getInstructionAt(0);
		storeInstrs = loadStoreAnalysis.getStoresForLoad(loadIns3);
		assertTrue(storeInstrs.size() == 1);
		assertTrue(storeInstrs.contains(str4));

		LoadInst loadIns4 = (LoadInst)bb5.getInstructionAt(4);
		storeInstrs = loadStoreAnalysis.getStoresForLoad(loadIns4);
		assertTrue(storeInstrs.size() == 2);
		assertTrue(storeInstrs.contains(str2) && storeInstrs.contains(str3));

		LoadInst loadIns5 = (LoadInst)bb6.getInstructionAt(0);
		storeInstrs = loadStoreAnalysis.getStoresForLoad(loadIns5);
		assertTrue(storeInstrs.size() == 3);
		assertTrue(storeInstrs.contains(str1) && storeInstrs.contains(str2) && storeInstrs.contains(str3));

	}

	@Test
	public void loopInsideIf() throws Exception {
		String cSrcfilename =  "LoopInsideIf.c";

		Function function = getFunction(cSrcfilename);

		loadStoreAnalysis = new LoadStoreAnalysis(function);
		loadStoreAnalysis.analyze();

		BasicBlock bb0 = function.getBasicBlockAt(0);
		BasicBlock bb1 = function.getBasicBlockAt(1);
		BasicBlock bb3 = function.getBasicBlockAt(3);
		BasicBlock bb6 = function.getBasicBlockAt(6);
		BasicBlock bb7 = function.getBasicBlockAt(7);

		StoreInst str1 = (StoreInst)bb0.getInstructionAt(7);
		StoreInst str2 = (StoreInst)bb1.getInstructionAt(2);
		StoreInst str3 = (StoreInst)bb3.getInstructionAt(2);
		StoreInst str4 = (StoreInst)bb6.getInstructionAt(1);

		LoadInst loadIns1 = (LoadInst)bb1.getInstructionAt(0);
		List<StoreInst> storeInstrs = loadStoreAnalysis.getStoresForLoad(loadIns1);
		assertTrue(storeInstrs.size() == 1);
		assertTrue(storeInstrs.contains(str1));

		LoadInst loadIns2 = (LoadInst)bb3.getInstructionAt(0);
		storeInstrs = loadStoreAnalysis.getStoresForLoad(loadIns2);
		assertTrue(storeInstrs.size() == 2);
		assertTrue(storeInstrs.contains(str2) && storeInstrs.contains(str3));

		LoadInst loadIns3 = (LoadInst)bb7.getInstructionAt(1);
		storeInstrs = loadStoreAnalysis.getStoresForLoad(loadIns3);
		assertTrue(storeInstrs.size() == 3);
		assertTrue(storeInstrs.contains(str2) && storeInstrs.contains(str3) && storeInstrs.contains(str4));
	}

	@Test
	public void loopsInsideIfElse() throws Exception {
		String cSrcfilename =  "LoopsInsideIfElse.c";

		Function function = getFunction(cSrcfilename);



		loadStoreAnalysis = new LoadStoreAnalysis(function);
		loadStoreAnalysis.analyze();

		BasicBlock bb0 = function.getBasicBlockAt(0);
		BasicBlock bb1 = function.getBasicBlockAt(1);
		BasicBlock bb3 = function.getBasicBlockAt(3);
		BasicBlock bb5 = function.getBasicBlockAt(5);
		BasicBlock bb7 = function.getBasicBlockAt(7);
		BasicBlock bb9 = function.getBasicBlockAt(9);

		StoreInst str1 = (StoreInst)bb0.getInstructionAt(7);
		StoreInst str2 = (StoreInst)bb1.getInstructionAt(2);
		StoreInst str3 = (StoreInst)bb3.getInstructionAt(2);
		StoreInst str4 = (StoreInst)bb5.getInstructionAt(2);
		StoreInst str5 = (StoreInst)bb7.getInstructionAt(2);
		StoreInst str6 = (StoreInst)bb9.getInstructionAt(2);

		LoadInst loadIns1 = (LoadInst)bb1.getInstructionAt(0);
		List<StoreInst> storeInstrs = loadStoreAnalysis.getStoresForLoad(loadIns1);
		assertTrue(storeInstrs.size() == 1);
		assertTrue(storeInstrs.contains(str1));

		LoadInst loadIns2 = (LoadInst)bb3.getInstructionAt(0);
		storeInstrs = loadStoreAnalysis.getStoresForLoad(loadIns2);
		assertTrue(storeInstrs.size() == 2);
		assertTrue(storeInstrs.contains(str2) && storeInstrs.contains(str3));

		LoadInst loadIns3 = (LoadInst)bb5.getInstructionAt(0);
		storeInstrs = loadStoreAnalysis.getStoresForLoad(loadIns3);
		assertTrue(storeInstrs.size() == 1);
		assertTrue(storeInstrs.contains(str1));

		LoadInst loadIns4 = (LoadInst)bb7.getInstructionAt(0);
		storeInstrs = loadStoreAnalysis.getStoresForLoad(loadIns4);
		assertTrue(storeInstrs.size() == 2);
		assertTrue(storeInstrs.contains(str4) && storeInstrs.contains(str5));

		LoadInst loadIns5 = (LoadInst)bb9.getInstructionAt(0);
		storeInstrs = loadStoreAnalysis.getStoresForLoad(loadIns5);
		assertTrue(storeInstrs.size() == 4);
		assertTrue(storeInstrs.contains(str2) && storeInstrs.contains(str3) &&
				storeInstrs.contains(str4) && storeInstrs.contains(str5));

		LoadInst loadIns6 = (LoadInst)bb9.getInstructionAt(3);
		storeInstrs = loadStoreAnalysis.getStoresForLoad(loadIns6);
		assertTrue(storeInstrs.size() == 1);
		assertTrue(storeInstrs.contains(str6));
	}

	@Test
	public void nestedLoops() throws Exception {
		String cSrcfilename =  "NestedLoops.c";

		Function function = getFunction(cSrcfilename);

		loadStoreAnalysis = new LoadStoreAnalysis(function);
		loadStoreAnalysis.analyze();

		BasicBlock bb0 = function.getBasicBlockAt(0);
		BasicBlock bb2 = function.getBasicBlockAt(2);
		BasicBlock bb4 = function.getBasicBlockAt(4);
		BasicBlock bb6 = function.getBasicBlockAt(6);
		BasicBlock bb8 = function.getBasicBlockAt(8);

		StoreInst str1 = (StoreInst)bb0.getInstructionAt(12);
		StoreInst str2 = (StoreInst)bb2.getInstructionAt(2);
		StoreInst str3 = (StoreInst)bb4.getInstructionAt(2);
		StoreInst str4 = (StoreInst)bb6.getInstructionAt(3);

		LoadInst loadIns1 = (LoadInst)bb2.getInstructionAt(0);
		List<StoreInst> storeInstrs = loadStoreAnalysis.getStoresForLoad(loadIns1);
		assertTrue(storeInstrs.size() == 2);
		assertTrue(storeInstrs.contains(str1) && storeInstrs.contains(str4));

		LoadInst loadIns2 = (LoadInst)bb4.getInstructionAt(0);
		storeInstrs = loadStoreAnalysis.getStoresForLoad(loadIns2);
		assertTrue(storeInstrs.size() == 2);
		assertTrue(storeInstrs.contains(str2) && storeInstrs.contains(str3));

		LoadInst loadIns3 = (LoadInst)bb6.getInstructionAt(0);
		storeInstrs = loadStoreAnalysis.getStoresForLoad(loadIns3);
		assertTrue(storeInstrs.size() == 2);
		assertTrue(storeInstrs.contains(str2) && storeInstrs.contains(str3));

		LoadInst loadIns4 = (LoadInst)bb8.getInstructionAt(0);
		storeInstrs = loadStoreAnalysis.getStoresForLoad(loadIns4);
		assertTrue(storeInstrs.size() == 2);
		assertTrue(storeInstrs.contains(str1) && storeInstrs.contains(str4));
	}

	@Test
	public void complex() throws Exception {
		String cSrcfilename =  "Complex.c";

		Function function = getFunction(cSrcfilename);

		loadStoreAnalysis = new LoadStoreAnalysis(function);
		loadStoreAnalysis.analyze();

		BasicBlock bb1 = function.getBasicBlockAt(1);
		BasicBlock bb3 = function.getBasicBlockAt(3);
		BasicBlock bb4 = function.getBasicBlockAt(4);
		BasicBlock bb6 = function.getBasicBlockAt(6);
		BasicBlock bb8 = function.getBasicBlockAt(8);
		BasicBlock bb12 = function.getBasicBlockAt(12);
		BasicBlock bb13 = function.getBasicBlockAt(13);

		StoreInst str2 = (StoreInst)bb1.getInstructionAt(1);
		StoreInst str3 = (StoreInst)bb3.getInstructionAt(2);
		StoreInst str4 = (StoreInst)bb4.getInstructionAt(4);
		StoreInst str5 = (StoreInst)bb6.getInstructionAt(2);
		StoreInst str6 = (StoreInst)bb8.getInstructionAt(2);
		StoreInst str8 = (StoreInst)bb12.getInstructionAt(0);
		StoreInst str9 = (StoreInst)bb13.getInstructionAt(2);

		LoadInst loadIns1 = (LoadInst)bb3.getInstructionAt(0);
		List<StoreInst> storeInstrs = loadStoreAnalysis.getStoresForLoad(loadIns1);
		assertTrue(storeInstrs.size() == 4);
		assertTrue(storeInstrs.contains(str2) && storeInstrs.contains(str4) && storeInstrs.contains(str5)
				&& storeInstrs.contains(str6));

		LoadInst loadIns2 = (LoadInst)bb3.getInstructionAt(3);
		storeInstrs = loadStoreAnalysis.getStoresForLoad(loadIns2);
		assertTrue(storeInstrs.size() == 1);
		assertTrue(storeInstrs.contains(str3));

		LoadInst loadIns3 = (LoadInst)bb4.getInstructionAt(2);
		storeInstrs = loadStoreAnalysis.getStoresForLoad(loadIns3);
		assertTrue(storeInstrs.size() == 1);
		assertTrue(storeInstrs.contains(str3));

		LoadInst loadIns4 = (LoadInst)bb6.getInstructionAt(0);
		storeInstrs = loadStoreAnalysis.getStoresForLoad(loadIns4);
		assertTrue(storeInstrs.size() == 2);
		assertTrue(storeInstrs.contains(str4) && storeInstrs.contains(str5));

		LoadInst loadIns5 = (LoadInst)bb8.getInstructionAt(0);
		storeInstrs = loadStoreAnalysis.getStoresForLoad(loadIns5);
		assertTrue(storeInstrs.size() == 1);
		assertTrue(storeInstrs.contains(str3));

		LoadInst loadIns6 = (LoadInst)bb13.getInstructionAt(0);
		storeInstrs = loadStoreAnalysis.getStoresForLoad(loadIns6);
		assertTrue(storeInstrs.size() == 5);
		assertTrue(storeInstrs.contains(str2) && storeInstrs.contains(str4) && 
				storeInstrs.contains(str5) && storeInstrs.contains(str6) 
				&& storeInstrs.contains(str8));

		LoadInst loadIns7 = (LoadInst)bb13.getInstructionAt(3);
		storeInstrs = loadStoreAnalysis.getStoresForLoad(loadIns7);
		assertTrue(storeInstrs.size() == 1);
		assertTrue(storeInstrs.contains(str9));

	}

	private Function getFunction(String cSrcfilename)  throws Exception{
		LLVMIRUtils llvmirUtils = new LLVMIRUtils();
		llvmirUtils.getInstructionsList(ROOT_PATH, cSrcfilename);

		Module module = llvmirUtils.getModule();
		List<Function> functions = module.getFunctions();

		saveRawOutput(module, cSrcfilename);

		Function function = functions.get(functions.size() -1);
		return function;
	}

	private void saveRawOutput(Module module, String cSrcfilename) {
		List<String> instrs = emitter.emitInstructions(module);

		String targetFileName = cSrcfilename.replace(".c", ".bc");

		File targetFile = new File(TARGET_PATH, targetFileName);

		BufferedWriter writer = null;
		try
		{
			writer = new BufferedWriter( new FileWriter(targetFile));
			int count =0;
			for(String ins : instrs){
				writer.write( ins);
				if(count < instrs.size() -1){
					writer.write("\n");
				}
				count++;
			}
		}
		catch ( IOException e)
		{
		}
		finally
		{
			try
			{
				if ( writer != null)
					writer.close( );
			}
			catch ( IOException e)
			{
			}
		}
	}
}

package org.tamedragon.common.controlflowanalysis;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import org.tamedragon.common.aliasanalysis.AliasResult;
import org.tamedragon.common.aliasanalysis.FSAliasAnalysis;
import org.tamedragon.common.llvmir.instructions.Instruction;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.Module;
import org.tamedragon.common.llvmir.types.Value;
import org.tamedragon.common.optimization.MemToRegPromoter;
import org.tamedragon.compilers.LLVMBaseTest;

public class LoopIdiomRecogizeTests extends LLVMBaseTest{

	private static final String ROOT_PATH = "CSrc/ControlFlowAnalysis/PointerIncrementingTests";
	
	@Test
	public void simplePointerIncrement() throws Exception {
		String cSrcfilename =  "SimplePointerIncrement.c";
		
		Function function = getFunction(cSrcfilename);

		LoopIdiomRecognize loopIdiomRecognize = new LoopIdiomRecognize(function);
		loopIdiomRecognize.identifyingIncrementingPointers();

	}
	
	private Function getFunction(String cSrcfilename)  throws Exception{
		getRawLLVRIRInstrs(ROOT_PATH, cSrcfilename);

		Module module = getModule();
		List<Function> functions = module.getFunctions();

		Function function = functions.get(functions.size() -1);
		
		// Mem2reg
		MemToRegPromoter memToRegPromoter = new MemToRegPromoter();
		memToRegPromoter.execute(function);
		System.out.println("AFTER MEM2REG: ");
		emitter.reset();
		List<String> instrsAfterOpt = emitter.emitInstructions(module);
		printAsm(instrsAfterOpt);
		
		return function;
	}
	
}

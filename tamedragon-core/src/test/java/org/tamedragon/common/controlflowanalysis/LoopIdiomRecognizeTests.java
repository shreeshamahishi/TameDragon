package org.tamedragon.common.controlflowanalysis;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.tamedragon.common.llvmir.instructions.GetElementPtrInst;
import org.tamedragon.common.llvmir.instructions.LoadInst;
import org.tamedragon.common.llvmir.instructions.PhiNode;
import org.tamedragon.common.llvmir.instructions.StoreInst;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.Module;
import org.tamedragon.common.utils.LLVMIRUtils;

public class LoopIdiomRecognizeTests {

	private static final String ROOT_PATH = "ControlFlowAnalysis/LoopIdiomRecognize";
	
	private LLVMIRUtils llvmirUtils;
	
	@Before
	public void setUp(){
		llvmirUtils = new LLVMIRUtils();
	}
	
	@Test
	public void simplePointerIncrementRaw() throws Exception {
		String cSrcfilename =  "SimplePointerIncrementRaw.bc";
		
		Function function = getFunction(cSrcfilename, "foo");
		assertNotNull(function);
		
		LoopIdiomRecognize loopIdiomRecognize = new LoopIdiomRecognize(function);
		loopIdiomRecognize.identifyingIncrementingPointers();
		
		// Initializing store
		StoreInst initStore = (StoreInst) function.getBasicBlockAt(0).getInstructionAt(10);
		IncrementingPointerTuple tuple1 = loopIdiomRecognize.getIncrementingPointerTupleForInstruction(initStore);
		assertNotNull(tuple1);
		
		// Other instructions that participate in the increment
		LoadInst preLoad = (LoadInst) function.getBasicBlockAt(2).getInstructionAt(5);
		IncrementingPointerTuple tuple2 = loopIdiomRecognize.getIncrementingPointerTupleForInstruction(preLoad);
		assertNotNull(tuple2);
		GetElementPtrInst gep = (GetElementPtrInst) function.getBasicBlockAt(2).getInstructionAt(6);
		IncrementingPointerTuple tuple3 = loopIdiomRecognize.getIncrementingPointerTupleForInstruction(gep);
		assertNotNull(tuple3);
		StoreInst postStore = (StoreInst) function.getBasicBlockAt(2).getInstructionAt(7);
		IncrementingPointerTuple tuple4 = loopIdiomRecognize.getIncrementingPointerTupleForInstruction(postStore);
		assertNotNull(tuple4);
		
		// Check tuple
		assertTrue(tuple1 == tuple2 && tuple1 == tuple3 && tuple1 == tuple4);
	}
	
	@Test
	public void simplePointerIncrement() throws Exception {
		String cSrcfilename =  "SimplePointerIncrement.bc";
		
		Function function = getFunction(cSrcfilename, "foo");
		assertNotNull(function);
		
		LoopIdiomRecognize loopIdiomRecognize = new LoopIdiomRecognize(function);
		loopIdiomRecognize.identifyingIncrementingPointers();
		
		// Initializing phi node
		PhiNode phiNode = (PhiNode) function.getBasicBlockAt(1).getInstructionAt(2);
		IncrementingPointerTuple tuple1 = loopIdiomRecognize.getIncrementingPointerTupleForInstruction(phiNode);
		assertNotNull(tuple1);
		
		// Other instructions that participate in the increment
		GetElementPtrInst gep = (GetElementPtrInst) function.getBasicBlockAt(2).getInstructionAt(2);
		IncrementingPointerTuple tuple2 = loopIdiomRecognize.getIncrementingPointerTupleForInstruction(gep);
		assertNotNull(tuple2);
		
		// Check tuple
		assertTrue(tuple1 == tuple2);
	}
	
	private Function getFunction(String srcFileName, String functionNameToOptimize){
		llvmirUtils.getInstructionsList(ROOT_PATH, srcFileName);
		Module module = llvmirUtils.getModule();
		List<Function> functions = module.getFunctions();
		Function function = null;
		for(Function currentFunction : functions){
			String function_name = currentFunction.getName();

			if(function_name.equals(functionNameToOptimize))
			{
				function = currentFunction;
				break;

			}
		}
		return function;
	}
}

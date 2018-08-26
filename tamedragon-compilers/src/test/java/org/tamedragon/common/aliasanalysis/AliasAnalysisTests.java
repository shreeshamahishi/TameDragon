package org.tamedragon.common.aliasanalysis;

import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import org.tamedragon.common.llvmir.instructions.Instruction;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.GlobalVariable;
import org.tamedragon.common.llvmir.types.Module;
import org.tamedragon.common.llvmir.types.PointerType;
import org.tamedragon.common.llvmir.types.Value;
import org.tamedragon.common.optimization.MemToRegPromoter;
import org.tamedragon.compilers.LLVMBaseTest;

public class AliasAnalysisTests extends LLVMBaseTest{

//	private static final String ROOT_PATH = "CSrc/AliasAnalysis/";
//
//	private BasicAliasAnalysis basicAliasAnalysis;
//
//	@Before
//	public void setUp(){
//		super.setUp();
//		basicAliasAnalysis = new BasicAliasAnalysis(new NoAliasAnalysis(null));
//	}
//
//	@Test
//	public void takeAddresses1() throws Exception {
//		String cSrcfilename =  "TakeAddresses1.c";
//		
//		Function function = getFunction(cSrcfilename);
//		Module module = getModule();
//
//		FSAliasAnalysis fsAliasAnalysis = new FSAliasAnalysis(null);
//		fsAliasAnalysis.analyze(module, function);
//		
//		List<Value> pointers = identifyPointers(module, function);
//		Value a = getPtr(pointers, "a"); Value b = getPtr(pointers, "b");
//		Value p3 = getPtr(pointers, "p3.0");
//		
//		// Two different local variables 
//		BasicBlock bb1 = function.getBasicBlocks().get(0);
//		Instruction ins = bb1.getInstructions().elementAt(2);
//		AliasResult result = fsAliasAnalysis.alias(ins, a, b);
//		assertTrue(result == AliasResult.NO_ALIAS);
//
//		// Compare p3 with a and b before p3 is assigned
//		result = fsAliasAnalysis.alias(ins, a, p3);
//		assertTrue(result == AliasResult.NO_ALIAS);
//		result = fsAliasAnalysis.alias(ins, b, p3);
//		assertTrue(result == AliasResult.NO_ALIAS);
//		
//		// After a phi instruction assignment to a pointer, the pointer could have any value
//		// that matches one of the operands
//		BasicBlock bb3 = function.getBasicBlocks().get(3);
//		ins = bb3.getInstructions().elementAt(1);
//		result = fsAliasAnalysis.alias(ins, p3, a);
//		assertTrue(result == AliasResult.MAY_ALIAS);
//		result = fsAliasAnalysis.alias(ins, p3, b);
//		assertTrue(result == AliasResult.MAY_ALIAS);
//
//	}
//	
//	@Test
//	public void takeAddresses2() throws Exception {
//		String cSrcfilename =  "TakeAddresses2.c";
//		
//		Function function = getFunction(cSrcfilename);
//		Module module = getModule();
//
//		FSAliasAnalysis fsAliasAnalysis = new FSAliasAnalysis(null);
//		fsAliasAnalysis.analyze(module, function);
//		
//		List<Value> pointers = identifyPointers(module, function);
//		Value a = getPtr(pointers, "a"); Value b = getPtr(pointers, "b");
//		Value glb = getPtr(pointers, "glb"); Value p1 = getPtr(pointers, "p1.0");
//		
//		// Two different local variables 
//		BasicBlock bb1 = function.getBasicBlocks().get(0);
//		Instruction ins = bb1.getInstructions().elementAt(2);
//		AliasResult result = fsAliasAnalysis.alias(ins, a, b);
//		assertTrue(result == AliasResult.NO_ALIAS);
//		
//		// A local variable and a pointer before the pointer gets assigned an address
//		result = fsAliasAnalysis.alias(ins, a, p1);
//		assertTrue(result == AliasResult.NO_ALIAS);
//		
//		// A global and a local variable
//		result = fsAliasAnalysis.alias(ins, glb, a);
//		assertTrue(result == AliasResult.NO_ALIAS);
//		result = fsAliasAnalysis.alias(ins, glb, b);
//		assertTrue(result == AliasResult.NO_ALIAS);
//		
//		// A local variable with unknown storage and a local variable or a global variable
//		result = fsAliasAnalysis.alias(ins, p1, a);
//		assertTrue(result == AliasResult.NO_ALIAS);
//		result = fsAliasAnalysis.alias(ins, p1, b);
//		assertTrue(result == AliasResult.NO_ALIAS);
//		result = fsAliasAnalysis.alias(ins, p1, glb);
//		assertTrue(result == AliasResult.NO_ALIAS);
//		
//		// After a phi instruction assignment to a pointer, the pointer could have any value
//		// that matches one of the operands
//		BasicBlock bb3 = function.getBasicBlocks().get(2);
//		ins = bb3.getInstructions().elementAt(1);
//		result = fsAliasAnalysis.alias(ins, p1, a);
//		assertTrue(result == AliasResult.MAY_ALIAS);
//		result = fsAliasAnalysis.alias(ins, p1, b);
//		assertTrue(result == AliasResult.MAY_ALIAS);
//		
//		result = fsAliasAnalysis.alias(ins, p1, glb);
//		assertTrue(result == AliasResult.NO_ALIAS);
//	}
//	
//	@Test
//	public void pointerAssignmentsInLoop1() throws Exception {
//		String cSrcfilename =  "PointerAssignmentsInLoop1.c";
//		
//		Function function = getFunction(cSrcfilename);
//		Module module = getModule();
//
//		FSAliasAnalysis fsAliasAnalysis = new FSAliasAnalysis(null);
//		fsAliasAnalysis.analyze(module, function);
//		
//		List<Value> pointers = identifyPointers(module, function);
//		Value a = getPtr(pointers, "a"); Value b = getPtr(pointers, "b");
//		Value c = getPtr(pointers, "c"); Value p1_0 = getPtr(pointers, "p1.0");
//		Value p2_0 = getPtr(pointers, "p2.0"); Value p1_1 = getPtr(pointers, "p1.1");
//		
//		// Two different local variables 
//		BasicBlock bb1 = function.getBasicBlocks().get(0);
//		Instruction ins = bb1.getInstructions().elementAt(2);
//		AliasResult result = fsAliasAnalysis.alias(ins, a, b);
//		assertTrue(result == AliasResult.NO_ALIAS);
//		
//		// At basic block 2, instruction number 4, p1 and p2 may alias
//		bb1 = function.getBasicBlocks().get(1);
//		ins = bb1.getInstructions().elementAt(4);
//		result = fsAliasAnalysis.alias(ins, p1_0, p2_0);
//		assertTrue(result == AliasResult.MAY_ALIAS);
//		
//		// At basic block %26, instruction number 0, %p2.0 and %a do not alias,
//		// but %p2.0 may alias with %b or %c
//		bb1 = function.getBasicBlocks().get(5);
//		ins = bb1.getInstructions().elementAt(0);
//		result = fsAliasAnalysis.alias(ins, p2_0, a);
//		assertTrue(result == AliasResult.NO_ALIAS);
//		
//		result = fsAliasAnalysis.alias(ins, p2_0, b);
//		assertTrue(result == AliasResult.MAY_ALIAS);
//		
//		result = fsAliasAnalysis.alias(ins, p2_0, c);
//		assertTrue(result == AliasResult.MAY_ALIAS);
//		
//		// Same at %33 or at %35
//		bb1 = function.getBasicBlocks().get(6);
//		ins = bb1.getInstructions().elementAt(1);
//		
//		result = fsAliasAnalysis.alias(ins, p2_0, a);
//		assertTrue(result == AliasResult.NO_ALIAS);
//		result = fsAliasAnalysis.alias(ins, p2_0, b);
//		assertTrue(result == AliasResult.MAY_ALIAS);
//		result = fsAliasAnalysis.alias(ins, p2_0, c);
//		assertTrue(result == AliasResult.MAY_ALIAS);
//		
//		result = fsAliasAnalysis.alias(ins, p1_0, a);
//		assertTrue(result == AliasResult.MAY_ALIAS);
//		result = fsAliasAnalysis.alias(ins, p1_0, b);
//		assertTrue(result == AliasResult.MAY_ALIAS);
//		result = fsAliasAnalysis.alias(ins, p1_0, c);
//		assertTrue(result == AliasResult.MAY_ALIAS);
//		
//		result = fsAliasAnalysis.alias(ins, p1_1, a);
//		assertTrue(result == AliasResult.NO_ALIAS);
//		result = fsAliasAnalysis.alias(ins, p1_1, b);
//		assertTrue(result == AliasResult.MAY_ALIAS);
//		result = fsAliasAnalysis.alias(ins, p1_1, c);
//		assertTrue(result == AliasResult.MAY_ALIAS);
//		
//		bb1 = function.getBasicBlocks().get(7);
//		ins = bb1.getInstructions().elementAt(1);
//		
//		result = fsAliasAnalysis.alias(ins, p2_0, a);
//		assertTrue(result == AliasResult.NO_ALIAS);
//		result = fsAliasAnalysis.alias(ins, p2_0, b);
//		assertTrue(result == AliasResult.MAY_ALIAS);
//		result = fsAliasAnalysis.alias(ins, p2_0, c);
//		assertTrue(result == AliasResult.MAY_ALIAS);
//		
//		result = fsAliasAnalysis.alias(ins, p1_0, a);
//		assertTrue(result == AliasResult.MAY_ALIAS);
//		result = fsAliasAnalysis.alias(ins, p1_0, b);
//		assertTrue(result == AliasResult.MAY_ALIAS);
//		result = fsAliasAnalysis.alias(ins, p1_0, c);
//		assertTrue(result == AliasResult.MAY_ALIAS);
//		
//		result = fsAliasAnalysis.alias(ins, p1_1, a);
//		assertTrue(result == AliasResult.NO_ALIAS);
//		result = fsAliasAnalysis.alias(ins, p1_1, b);
//		assertTrue(result == AliasResult.MAY_ALIAS);
//		result = fsAliasAnalysis.alias(ins, p1_1, c);
//		assertTrue(result == AliasResult.MAY_ALIAS);
//	}
//	
//	@Test
//	public void arrayIteration1() throws Exception {
//		String cSrcfilename =  "ArrayIteration1.c";
//		
//		Function function = getFunction(cSrcfilename);
//		Module module = getModule();
//
//		FSAliasAnalysis fsAliasAnalysis = new FSAliasAnalysis(null);
//		fsAliasAnalysis.analyze(module, function);
//		
//		List<Value> pointers = identifyPointers(module, function);
//		Value a = getPtr(pointers, "a"); Value b = getPtr(pointers, "b");
//		Value c = getPtr(pointers, "c"); Value p1_0 = getPtr(pointers, "p1.0");
//		Value p2_0 = getPtr(pointers, "p2.0"); Value p1_1 = getPtr(pointers, "p1.1");
//	}
//	
//	@Test
//	public void complexStruct1() throws Exception {
//		String cSrcfilename =  "ComplexStruct1.c";
//		
//		Function function = getFunction(cSrcfilename);
//		Module module = getModule();
//
//		FSAliasAnalysis fsAliasAnalysis = new FSAliasAnalysis(null);
//		fsAliasAnalysis.analyze(module, function);
//		
//		List<Value> pointers = identifyPointers(module, function);
//	}
//	
//	@Test
//	public void complextStruct2() throws Exception {
//		String cSrcfilename =  "ComplexStruct2.c";
//		
//		Function function = getFunction(cSrcfilename);
//		Module module = getModule();
//
//		FSAliasAnalysis fsAliasAnalysis = new FSAliasAnalysis(null);
//		fsAliasAnalysis.analyze(module, function);
//		
//		List<Value> pointers = identifyPointers(module, function);
//		
//	}
//	
//	@Ignore
//	@Test
//	public void aliasForCSE1() throws Exception {
//		String cSrcfilename =  "AliasForCSE1.c";
//		
//		Function function = getFunction(cSrcfilename);
//		Module module = getModule();
//		
//		FSAliasAnalysis fsAliasAnalysis = new FSAliasAnalysis(null);
//		fsAliasAnalysis.analyze(module, function);
//		
//		List<Value> pointers = identifyPointers(module, function);
//		Value a = getPtr(pointers, "a");
//		
//		// Two different local variables 
//		BasicBlock bb1 = function.getBasicBlocks().get(0);
//		Instruction ins1 = bb1.getInstructions().elementAt(2);
//		Instruction ins2 = bb1.getInstructions().elementAt(5);
//		AliasResult result = fsAliasAnalysis.alias(ins1, ins2, a);
//		assertTrue(result == AliasResult.NO_ALIAS);
//		
//	}
//	
//	@Test
//	public void complextStruct3() throws Exception {
//		String cSrcfilename =  "ComplexStruct3.c";
//		
//		Function function = getFunction(cSrcfilename);
//		Module module = getModule();
//
//		//FSAliasAnalysis fsAliasAnalysis = new FSAliasAnalysis(null);
//		//fsAliasAnalysis.analyze(function);
//		
//		List<Value> pointers = identifyPointers(module, function);
//		
//	}
//	
//	@Test
//	public void ComplexStructVariableGEP1() throws Exception {
//		String cSrcfilename =  "ComplexStructVariableGEP1.c";
//		
//		Function function = getFunction(cSrcfilename);
//		Module module = getModule();
//
//		FSAliasAnalysis fsAliasAnalysis = new FSAliasAnalysis(null);
//		//fsAliasAnalysis.analyze(function);
//		
//		List<Value> pointers = identifyPointers(module, function);
//		
//	}
//	
//	@Test
//	public void CastOperation() throws Exception {
//		String cSrcfilename =  "CastOperation.c";
//		
//		Function function = getFunction(cSrcfilename);
//		Module module = getModule();
//
//		FSAliasAnalysis fsAliasAnalysis = new FSAliasAnalysis(null);
//		//fsAliasAnalysis.analyze(function);
//		
//		List<Value> pointers = identifyPointers(module, function);
//		
//	}
//	
//	@Test
//	public void AliasesInLoadInstrs1() throws Exception {
//		String cSrcfilename =  "AliasesWithLoadStores1.c";
//		
//		Function function = getFunction(cSrcfilename);
//		Module module = getModule();
//
//		//FSAliasAnalysis fsAliasAnalysis = new FSAliasAnalysis(null);
//		//fsAliasAnalysis.analyze(module, function);
//		
////		List<Value> pointers = identifyPointers(module, function);
////		
////		BasicBlock bb1 = function.getBasicBlocks().get(0);
////		Instruction ins1 = bb1.getInstructions().elementAt(80);
////		
////		Value v61 = getPtr(pointers, "%61");
////		Value v68 = getPtr(pointers, "%68");
////		
////		assertTrue(fsAliasAnalysis.alias(ins1, v61, v68) == AliasResult.NO_ALIAS);
//		
//	}
//	
//	@Test
//	public void AliasesInLoadInstrs2() throws Exception {
//		String cSrcfilename =  "AliasWithLoadStores2.c";
//		
//		Function function = getFunction(cSrcfilename);
//		Module module = getModule();
//
//		FSAliasAnalysis fsAliasAnalysis = new FSAliasAnalysis(null);
//		fsAliasAnalysis.analyze(module, function);
//		
//		List<Value> pointers = identifyPointers(module, function);
//	}
//	
//	@Test
//	public void aliasesInArrayIterationRaw2() throws Exception{
//		String cSrcfilename =  "ArrayIteration2.c";
//		
//		Function function = getFunction(cSrcfilename);
//		Module module = getModule();
//
//		FSAliasAnalysis fsAliasAnalysis = new FSAliasAnalysis(null);
//		fsAliasAnalysis.analyze(module, function);
//		
//		List<Value> pointers = identifyPointers(module, function);
//	}
//	
//	@Test
//	public void aliasesInAMultipleIncrementsOfAPointerInLoop() throws Exception{
//		String cSrcfilename =  "MultipleIncrementsOfAPointerInLoop.c";
//		
//		Function function = getFunction(cSrcfilename);
//		Module module = getModule();
//
//		FSAliasAnalysis fsAliasAnalysis = new FSAliasAnalysis(null);
//		fsAliasAnalysis.analyze(module, function);
//		
//		List<Value> pointers = identifyPointers(module, function);
//	}
//	
//	@Test
//	public void aliasesInPtrIncrmInLoopWithCondition() throws Exception{
//		String cSrcfilename =  "PointerIncrWithConditionInsideLoop1.c";
//		
//		Function function = getFunction(cSrcfilename);
//		Module module = getModule();
//
//		FSAliasAnalysis fsAliasAnalysis = new FSAliasAnalysis(null);
//		fsAliasAnalysis.analyze(module, function);
//		
//		List<Value> pointers = identifyPointers(module, function);
//	}
//	
//	@Test
//	public void aliasesInNestedLoopWithConditionInInnerLoop() throws Exception{
//		String cSrcfilename =  "NestedLoopWithConditionInInnerLoop.c";
//		
//		Function function = getFunction(cSrcfilename);
//		Module module = getModule();
//
//		FSAliasAnalysis fsAliasAnalysis = new FSAliasAnalysis(null);
//		fsAliasAnalysis.analyze(module, function);
//		
//		List<Value> pointers = identifyPointers(module, function);
//	}
//	
//	
//	private Function getFunction(String cSrcfilename)  throws Exception{
//		getRawLLVRIRInstrs(ROOT_PATH, cSrcfilename);
//
//		Module module = getModule();
//		List<Function> functions = module.getFunctions();
//
//		Function function = functions.get(functions.size() -1);
//		
//		// Mem2reg
//		MemToRegPromoter memToRegPromoter = new MemToRegPromoter();
//		memToRegPromoter.execute(function.getCfg());
//		System.out.println("AFTER MEM2REG: ");
//		emitter.reset();
//		List<String> instrsAfterOpt = emitter.emitInstructions(module);
//		printAsm(instrsAfterOpt);
//		
//		return function;
//	}
//	
//	private List<Value> identifyPointers(Module module, Function funcOfInterest) {
//		Set<Value> pointers = new HashSet<Value>();
//
//		List<GlobalVariable> globalVars = module.getGlobalVariables();
//		for(GlobalVariable gv : globalVars){
//			pointers.add(gv);
//		}
//
//		List<BasicBlock> basicBlocks = funcOfInterest.getBasicBlocks();
//		for(BasicBlock bb: basicBlocks){
//			Vector<Instruction> instrs = bb.getInstructions();
//			for(Instruction ins : instrs){
//				if(ins.getType() instanceof PointerType){
//					pointers.add(ins);
//				}
//
//				int numOprs = ins.getNumOperands();
//				for(int i = 0; i < numOprs; i++){
//					Value opr = ins.getOperand(i);
//					if(opr.getType() instanceof PointerType){
//						pointers.add(opr);
//					}
//				}
//
//			}
//		}
//
//		List<Value> ptrList = new ArrayList<Value>(pointers);
//
//		return ptrList;
//
//	}
//	
//	private Value getPtr(List<Value> ptrs, String name) {
//		for(Value ptr : ptrs){
//			if(ptr.getName() != null){
//				if(ptr.getName().equals(name))
//					return ptr;
//			}
//			else{
//				String nameFromEmitter = emitter.getValidName(ptr);
//				if(name.equals(nameFromEmitter)){
//					return ptr;
//				}
//				
//			}
//		}
//
//		return null;
//	}
}
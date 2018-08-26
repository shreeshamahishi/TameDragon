package org.tamedragon.common.aliasanalysis;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.junit.Ignore;
import org.junit.Test;
import org.tamedragon.common.Pair;
import org.tamedragon.common.llvmir.instructions.Instruction;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.ConstantInt;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.GlobalVariable;
import org.tamedragon.common.llvmir.types.Module;
import org.tamedragon.common.llvmir.types.PointerType;
import org.tamedragon.common.llvmir.types.Value;
import org.tamedragon.common.utils.LLVMIRUtils;

public class FSAnalysisTests {
//	private static final String ROOT_PATH = "TestData/AliasAnalysis/FSAnalysisTests";
//
//	@Test
//	public void runCombinations1(){
//		String srcFileName =  "CombinationsSrc1.bc";
//
//		Function function = getFunction(srcFileName, "foo");
//
//		assertNotNull(function);
//		Module module = getModule();
//
//		FSAliasAnalysis fsAliasAnalysis = new FSAliasAnalysis(null);
//		fsAliasAnalysis.analyze(module, function);
//
//		BasicBlock bb1 = function.getBasicBlocks().get(0);
//		Instruction ins = bb1.getInstructions().elementAt(54);
//		
//		List<Value> pointers = identifyPointers(module, function);
//		
//		Value auto1 = getPtr(pointers, "%1"); 
//		Value auto2 = getPtr(pointers, "%2");
//		Value arg1 = getPtr(pointers, "a"); 
//		Value arg2 = getPtr(pointers, "b"); 
//		Value glb1 = getPtr(pointers, "glb_var1");
//		Value glb2 = getPtr(pointers, "glb_var2");
//		
//		assertTrue(fsAliasAnalysis.alias(ins, auto1, auto2) == AliasResult.NO_ALIAS); // Two local variables do not alias
//		assertTrue(fsAliasAnalysis.alias(ins, glb1, glb2) == AliasResult.NO_ALIAS);   // Two global variables do not alias
//		assertTrue(fsAliasAnalysis.alias(ins, arg1, arg2) == AliasResult.MAY_ALIAS);  // Two arguments may alias
//		assertTrue(fsAliasAnalysis.alias(ins, auto1, glb1) == AliasResult.NO_ALIAS
//				&& fsAliasAnalysis.alias(ins, glb1, auto1) == AliasResult.NO_ALIAS);  // An auto and a global variable do not alias
//		assertTrue(fsAliasAnalysis.alias(ins, auto1, arg1) == AliasResult.NO_ALIAS
//				&& fsAliasAnalysis.alias(ins, arg1, auto1) == AliasResult.NO_ALIAS);  // An auto and an argument do not alias
//		assertTrue(fsAliasAnalysis.alias(ins, glb1, arg1) == AliasResult.MAY_ALIAS
//				&& fsAliasAnalysis.alias(ins, arg1, glb1) == AliasResult.MAY_ALIAS);  // A global variable and an arg may alias
//	}
//	
//	@Test
//	public void runComplexStructCombinedGEPSrc1() throws Exception {
//		String srcFileName =  "ComplexStructCombinedGEPSrc1.bc";
//
//		Function function = getFunction(srcFileName, "foo");
//
//		assertNotNull(function);
//		Module module = getModule();
//
//		FSAliasAnalysis fsAliasAnalysis = new FSAliasAnalysis(null);
//		fsAliasAnalysis.analyze(module, function);
//
//		// Two different local variables 
//		BasicBlock bb1 = function.getBasicBlocks().get(0);
//		Instruction ins = bb1.getInstructions().elementAt(3);
//		
//		List<Value> pointers = identifyPointers(module, function);
//		Value gep1 = getPtr(pointers, "%1");
//		Value gep3 = getPtr(pointers, "%3");
//		Value gep5 = getPtr(pointers, "%5");
//		Value gep9 = getPtr(pointers, "%9");
//		Value gep14 = getPtr(pointers, "%14");
//		Value gep19 = getPtr(pointers, "%19");
//		assertTrue(fsAliasAnalysis.alias(ins, gep1, gep3) == AliasResult.MUST_ALIAS);
//		
//		ins = bb1.getInstructions().elementAt(5);
//		assertTrue(fsAliasAnalysis.alias(ins, gep1, gep5) == AliasResult.NO_ALIAS);
//		assertTrue(fsAliasAnalysis.alias(ins, gep3, gep5) == AliasResult.NO_ALIAS);
//		
//		BasicBlock bb2 = function.getBasicBlocks().get(1);
//		ins = bb2.getInstructions().elementAt(1);
//		assertTrue(fsAliasAnalysis.alias(ins, gep1, gep9) == AliasResult.NO_ALIAS);
//		assertTrue(fsAliasAnalysis.alias(ins, gep3, gep9) == AliasResult.NO_ALIAS);
//		assertTrue(fsAliasAnalysis.alias(ins, gep5, gep9) == AliasResult.NO_ALIAS);
//		
//		BasicBlock bb3 = function.getBasicBlocks().get(3);
//		ins = bb3.getInstructions().elementAt(1);
//		assertTrue(fsAliasAnalysis.alias(ins, gep9, gep14) == AliasResult.MUST_ALIAS);
//		assertTrue(fsAliasAnalysis.alias(ins, gep1, gep14) == AliasResult.NO_ALIAS);
//		assertTrue(fsAliasAnalysis.alias(ins, gep3, gep14) == AliasResult.NO_ALIAS);
//		assertTrue(fsAliasAnalysis.alias(ins, gep5, gep14) == AliasResult.NO_ALIAS);
//		
//		BasicBlock bb4 = function.getBasicBlocks().get(4);
//		ins = bb4.getInstructions().elementAt(2);
//		assertTrue(fsAliasAnalysis.alias(ins, gep1, gep19) == AliasResult.MAY_ALIAS);
//	}
//
//	@Test
//	public void runComplexStruct2() throws Exception {
//		String srcFileName =  "ComplexStruct1Mem2Reg.bc";
//
//		Function function = getFunction(srcFileName, "foo");
//
//		assertNotNull(function);
//		Module module = getModule();
//
//		FSAliasAnalysis fsAliasAnalysis = new FSAliasAnalysis(null);
//		fsAliasAnalysis.analyze(module, function);
//
//		List<Value> pointers = identifyPointers(module, function);
//		//		Value a = getPtr(pointers, "a"); Value b = getPtr(pointers, "b");
//		//		Value c = getPtr(pointers, "c"); Value p1_0 = getPtr(pointers, "p1.0");
//		//		Value p2_0 = getPtr(pointers, "p2.0"); Value p1_1 = getPtr(pointers, "p1.1");
//
//	}
//
//	@Test
//	public void runComplexStructVariableGEPSrc1() throws Exception {
//		String srcFileName =  "ComplexStructVariableGEPSrc1.bc";
//
//		Function function = getFunction(srcFileName, "foo");
//
//		assertNotNull(function);
//		Module module = getModule();
//		FSAliasAnalysis fsAliasAnalysis = new FSAliasAnalysis(null);
//		fsAliasAnalysis.analyze(module, function);
//		
//		List<Value> pointers = identifyPointers(module, function);
//		Value v1 = getPtr(pointers, "%1"); Value v3 = getPtr(pointers, "%3");
//		Value v5 = getPtr(pointers, "%5"); Value v8 = getPtr(pointers, "%8");
//		Value v11 = getPtr(pointers, "%11");
//		
//		BasicBlock bb = function.getBasicBlocks().get(0);
//		Instruction ins = bb.getInstructions().elementAt(10);
//		assertTrue(fsAliasAnalysis.alias(ins, v1, v3) == AliasResult.PARTIAL_ALIAS);
//		assertTrue(fsAliasAnalysis.alias(ins, v1, v5) == AliasResult.PARTIAL_ALIAS);
//		assertTrue(fsAliasAnalysis.alias(ins, v1, v11) == AliasResult.PARTIAL_ALIAS);
//		assertTrue(fsAliasAnalysis.alias(ins, v3, v11) == AliasResult.MUST_ALIAS);
//		assertTrue(fsAliasAnalysis.alias(ins, v5, v11) == AliasResult.PARTIAL_ALIAS);
//		assertTrue(fsAliasAnalysis.alias(ins, v1, v8) == AliasResult.MAY_ALIAS);
//		
//	}
//	
//	@Test
//	public void runCastOperation() throws Exception {
//		String srcFileName =  "CastOperationSrc.bc";
//
//		Function function = getFunction(srcFileName, "foo");
//
//		assertNotNull(function);
//		Module module = getModule();
//		FSAliasAnalysis fsAliasAnalysis = new FSAliasAnalysis(null);
//		fsAliasAnalysis.analyze(module, function);
//		
//		List<Value> pointers = identifyPointers(module, function);
//		Value v5 = getPtr(pointers, "%5"); Value v9 = getPtr(pointers, "%9");
//		Value v20 = getPtr(pointers, "%20"); Value v25 = getPtr(pointers, "%25");
//		
//		BasicBlock bb = function.getBasicBlocks().get(2);
//		Instruction ins = bb.getInstructions().elementAt(9);
//		assertTrue(fsAliasAnalysis.alias(ins, v5, v9) == AliasResult.NO_ALIAS);
//		
//		bb = function.getBasicBlocks().get(6);	
//		ins = bb.getInstructions().elementAt(12);
//		assertTrue(fsAliasAnalysis.alias(ins, v20, v25) == AliasResult.PARTIAL_ALIAS);
//	}
//	
//	@Test
//	public void runSelectOperation() throws Exception {
//		String srcFileName =  "SelectOperationSrc.bc";
//
//		Function function = getFunction(srcFileName, "foo");
//
//		assertNotNull(function);
//		Module module = getModule();
//		FSAliasAnalysis fsAliasAnalysis = new FSAliasAnalysis(null);
//		fsAliasAnalysis.analyze(module, function);
//		
//		List<Value> pointers = identifyPointers(module, function);
//		Value X = getPtr(pointers, "%1"); Value Y = getPtr(pointers, "%2");
//		Value Z = getPtr(pointers, "%3"); 
//		
//		BasicBlock bb = function.getBasicBlocks().get(0);
//		Instruction ins = bb.getInstructions().elementAt(12);
//		assertTrue(fsAliasAnalysis.alias(ins, X, Y) == AliasResult.MAY_ALIAS);
//		assertTrue(fsAliasAnalysis.alias(ins, Y, Z) == AliasResult.MAY_ALIAS);
//		assertTrue(fsAliasAnalysis.alias(ins, X, Z) == AliasResult.NO_ALIAS);
//	}
//	
//	@Test
//	public void runAliasWithLoadStores1() throws Exception {
//		String srcFileName =  "AliasWithLoadStoresSrc1.bc";
//
//		Function function = getFunction(srcFileName, "foo");
//
//		assertNotNull(function);
//		Module module = getModule();
//		FSAliasAnalysis fsAliasAnalysis = new FSAliasAnalysis(null);
//		fsAliasAnalysis.analyze(module, function);
//		
//		List<Value> pointers = identifyPointers(module, function);
//		
//		BasicBlock bb = function.getBasicBlocks().get(26);
//		Instruction ins = bb.getInstructions().elementAt(3);
//		Value localTest1Opt1 = getPtr(pointers, "%89");
//		Value localTest1Opt2 = getPtr(pointers, "%96");
//		Value localTest2Opt1 = getPtr(pointers, "%103");
//		Value localTest2Opt2 = getPtr(pointers, "%110");
//		Value arg1Opt1 = getPtr(pointers, "%125");
//		Value arg1Opt2 = getPtr(pointers, "%133");
//		Value arg2Opt1 = getPtr(pointers, "%141");
//		Value arg2Opt2 = getPtr(pointers, "%149");
//		Value glblTest1Opt1 = getPtr(pointers, "%156");
//		Value glblTest1Opt2 = getPtr(pointers, "%163");
//		Value glblTest2Opt1 = getPtr(pointers, "%170");
//		Value glblTest2Opt2 = getPtr(pointers, "%177");
//		Value localTest3Opt1 = getPtr(pointers, "%117");
//		
//		assertTrue(fsAliasAnalysis.alias(ins, localTest1Opt1, localTest1Opt2) == AliasResult.NO_ALIAS);
//		assertTrue(fsAliasAnalysis.alias(ins, localTest2Opt1, localTest2Opt2) == AliasResult.NO_ALIAS);
//		assertTrue(fsAliasAnalysis.alias(ins, arg1Opt1, arg1Opt2) == AliasResult.MAY_ALIAS);
//		assertTrue(fsAliasAnalysis.alias(ins, arg2Opt1, arg2Opt2) == AliasResult.MAY_ALIAS);
//		assertTrue(fsAliasAnalysis.alias(ins, arg1Opt1, arg2Opt1) == AliasResult.MAY_ALIAS);
//		assertTrue(fsAliasAnalysis.alias(ins, glblTest1Opt1, glblTest1Opt2) == AliasResult.MAY_ALIAS);
//		assertTrue(fsAliasAnalysis.alias(ins, glblTest2Opt1, glblTest2Opt2) == AliasResult.MAY_ALIAS);
//		assertTrue(fsAliasAnalysis.alias(ins, glblTest1Opt1, glblTest2Opt1) == AliasResult.MAY_ALIAS);
//		assertTrue(fsAliasAnalysis.alias(ins, localTest3Opt1, localTest1Opt2) == AliasResult.MUST_ALIAS);
//		
//	}
//	
//	@Ignore
//	@Test
//	public void runAliasWithLoadStores2() throws Exception {
//		String srcFileName =  "AliasWithLoadStoresRaw2.bc";
//
//		Function function = getFunction(srcFileName, "foo");
//
//		assertNotNull(function);
//		Module module = getModule();
//		FSAliasAnalysis fsAliasAnalysis = new FSAliasAnalysis(null);
//		fsAliasAnalysis.analyze(module, function);
//		
//		List<Value> pointers = identifyPointers(module, function);
//		
//		Value v11 = getPtr(pointers, "%7"); Value v14 = getPtr(pointers, "%10");
//		BasicBlock bb = function.getBasicBlocks().get(0);
//		Instruction ins = bb.getInstructions().elementAt(46);
//		assertTrue(fsAliasAnalysis.alias(ins, v11, v14) == AliasResult.NO_ALIAS);
//		
//		Value v27 = getPtr(pointers, "%23"); Value v30 = getPtr(pointers, "%26");
//		bb = function.getBasicBlocks().get(2);
//		ins = bb.getInstructions().elementAt(20);
//		assertTrue(fsAliasAnalysis.alias(ins, v27, v30) == AliasResult.NO_ALIAS);
//		
//		Value v51 = getPtr(pointers, "%47"); Value v54 = getPtr(pointers, "%50");
//		bb = function.getBasicBlocks().get(4);
//		ins = bb.getInstructions().elementAt(20);
//		assertTrue(fsAliasAnalysis.alias(ins, v51, v54) == AliasResult.MUST_ALIAS);
//		
//		Value v63 = getPtr(pointers, "%59"); Value v66 = getPtr(pointers, "%62");
//		bb = function.getBasicBlocks().get(5);
//		ins = bb.getInstructions().elementAt(10);
//		assertTrue(fsAliasAnalysis.alias(ins, v63, v66) == AliasResult.MAY_ALIAS);
//		
//		Value v76 = getPtr(pointers, "%72"); Value v79 = getPtr(pointers, "%75");
//		bb = function.getBasicBlocks().get(5);
//		ins = bb.getInstructions().elementAt(31);
//		assertTrue(fsAliasAnalysis.alias(ins, v76, v79) == AliasResult.NO_ALIAS);
//		
//	}
//	
//	@Test
//	public void multipleIncrementsOfAPointerInLoopRaw() throws Exception {
//		String srcFileName =  "MulitpleIncrementsOfAPointerInLoopRaw.bc";
//
//		Function function = getFunction(srcFileName, "foo");
//
//		assertNotNull(function);
//		Module module = getModule();
//		FSAliasAnalysis fsAliasAnalysis = new FSAliasAnalysis(null);
//		fsAliasAnalysis.analyze(module, function);
//		
//		List<Value> pointers = identifyPointers(module, function);
//		
//		// %36 at the beginning of basic block %6
//		BasicBlock bb = function.getBasicBlocks().get(1);
//		Instruction ins1 = bb.getInstructions().elementAt(0);
//		Value v36 = getPtr(pointers, "%36"); 
//		Set<StorageLocation> locations = fsAliasAnalysis.getStorageLocation(ins1, v36);
//		assertTrue(locations.size() == 1);
//		StorageLocation location = locations.iterator().next();
//		assertTrue(location.getType() == LocationType.LOOP_INCREMENT);
//		LoopIncrementedStorageLocation loopIncrementedStorageLocation = (LoopIncrementedStorageLocation) location;
//		ScaledStorageLocation baseAddress = (ScaledStorageLocation)loopIncrementedStorageLocation.getBaseAddressLocation();
//		assertTrue(baseAddress.toString().equals("SCALED:{ARGUMENT:0[32+(32*%20, 32*%30)]}"));
//		assertTrue(loopIncrementedStorageLocation.getIncrementedBy().getConstValue() == 32);
//		List<Pair<ConstantInt, Value>> constExpr = loopIncrementedStorageLocation.getIncrementedBy().getExpression();
//		assertTrue(constExpr.size() == 2);
//		assertTrue(constExpr.get(0).getFirst().equalsInt(32));
//		assertTrue(emitter.getValidName(constExpr.get(0).getSecond()).equals("%20"));
//		assertTrue(constExpr.get(1).getFirst().equalsInt(32));
//		assertTrue(emitter.getValidName(constExpr.get(1).getSecond()).equals("%30"));
//		
//		// %11 at the beginning of basic block %6
//		bb = function.getBasicBlocks().get(2);
//		ins1 = bb.getInstructions().elementAt(1);
//		Value v11 = getPtr(pointers, "%11"); 
//		locations = fsAliasAnalysis.getStorageLocation(ins1, v11);
//		assertTrue(locations.size() == 1);
//		location = locations.iterator().next();
//		assertTrue(location.getType() == LocationType.LOOP_INCREMENT);
//		loopIncrementedStorageLocation = (LoopIncrementedStorageLocation) location;
//		StorageLocation baseAddress2 = loopIncrementedStorageLocation.getBaseAddressLocation();
//		assertTrue(baseAddress2.toString().equals("ARGUMENT:0"));
//		assertTrue(loopIncrementedStorageLocation.getIncrementedBy().getConstValue() == 32);
//		constExpr = loopIncrementedStorageLocation.getIncrementedBy().getExpression();
//		assertTrue(constExpr.size() == 2);
//		assertTrue(constExpr.get(0).getFirst().equalsInt(32));
//		assertTrue(emitter.getValidName(constExpr.get(0).getSecond()).equals("%20"));
//		assertTrue(constExpr.get(1).getFirst().equalsInt(32));
//		assertTrue(emitter.getValidName(constExpr.get(1).getSecond()).equals("%30"));
//		
//		// %13 after assignment in basic block %6
//		bb = function.getBasicBlocks().get(2);
//		ins1 = bb.getInstructions().elementAt(4);
//		Value v13 = getPtr(pointers, "%13"); 
//		locations = fsAliasAnalysis.getStorageLocation(ins1, v13);
//		assertTrue(locations.size() == 1);
//		location = locations.iterator().next();
//		assertTrue(location.getType() == LocationType.LOOP_INCREMENT);
//		loopIncrementedStorageLocation = (LoopIncrementedStorageLocation) location;
//		StorageLocation baseAddress3 = loopIncrementedStorageLocation.getBaseAddressLocation();
//		assertTrue(baseAddress3.toString().equals("SCALED:{ARGUMENT:0[32+()]}"));
//		
//		assertTrue(loopIncrementedStorageLocation.getIncrementedBy().getConstValue() == 32);
//		constExpr = loopIncrementedStorageLocation.getIncrementedBy().getExpression();
//		assertTrue(constExpr.size() == 2);
//		assertTrue(constExpr.get(0).getFirst().equalsInt(32));
//		assertTrue(emitter.getValidName(constExpr.get(0).getSecond()).equals("%20"));
//		assertTrue(constExpr.get(1).getFirst().equalsInt(32));
//		assertTrue(emitter.getValidName(constExpr.get(1).getSecond()).equals("%30"));
//		
//		// %24 after assignment in basic block %6
//		bb = function.getBasicBlocks().get(2);
//		ins1 = bb.getInstructions().elementAt(17);
//		Value v24 = getPtr(pointers, "%24"); 
//		locations = fsAliasAnalysis.getStorageLocation(ins1, v24);
//		assertTrue(locations.size() == 1);
//		location = locations.iterator().next();
//		assertTrue(location.getType() == LocationType.LOOP_INCREMENT);
//		loopIncrementedStorageLocation = (LoopIncrementedStorageLocation) location;
//		StorageLocation baseAddress4 = loopIncrementedStorageLocation.getBaseAddressLocation();
//		assertTrue(baseAddress4.toString().equals("SCALED:{ARGUMENT:0[32+(32*%20)]}"));
//		assertTrue(loopIncrementedStorageLocation.getIncrementedBy().getConstValue() == 32);
//		constExpr = loopIncrementedStorageLocation.getIncrementedBy().getExpression();
//		assertTrue(constExpr.size() == 2);
//		assertTrue(constExpr.get(0).getFirst().equalsInt(32));
//		assertTrue(emitter.getValidName(constExpr.get(0).getSecond()).equals("%20"));
//		assertTrue(constExpr.get(1).getFirst().equalsInt(32));
//		assertTrue(emitter.getValidName(constExpr.get(1).getSecond()).equals("%30"));
//		
//		// %36 after assignment in basic block %6
//		bb = function.getBasicBlocks().get(2);
//		ins1 = bb.getInstructions().elementAt(32);
//		locations = fsAliasAnalysis.getStorageLocation(ins1, v36);
//		assertTrue(locations.size() == 1);
//		location = locations.iterator().next();
//		assertTrue(location.getType() == LocationType.LOOP_INCREMENT);
//		loopIncrementedStorageLocation = (LoopIncrementedStorageLocation) location;
//		StorageLocation baseAddress5 = loopIncrementedStorageLocation.getBaseAddressLocation();
//		assertTrue(baseAddress5.toString().equals("SCALED:{ARGUMENT:0[32+(32*%20, 32*%30)]}"));
//		assertTrue(loopIncrementedStorageLocation.getIncrementedBy().getConstValue() == 32);
//		constExpr = loopIncrementedStorageLocation.getIncrementedBy().getExpression();
//		assertTrue(constExpr.size() == 2);
//		assertTrue(constExpr.get(0).getFirst().equalsInt(32));
//		assertTrue(emitter.getValidName(constExpr.get(0).getSecond()).equals("%20"));
//		assertTrue(constExpr.get(1).getFirst().equalsInt(32));
//		assertTrue(emitter.getValidName(constExpr.get(1).getSecond()).equals("%30"));
//	}
//
//	@Test
//	public void runPointerIncrementInLoopWithConditionInsideLoopRaw1(){
//		String srcFileName =  "PointerIncrWithConditionInsideLoopRaw1.bc";
//
//		Function function = getFunction(srcFileName, "foo");
//
//		assertNotNull(function);
//		Module module = getModule();
//		FSAliasAnalysis fsAliasAnalysis = new FSAliasAnalysis(null);
//		fsAliasAnalysis.analyze(module, function);
//		
//		List<Value> pointers = identifyPointers(module, function);
//	}
//	
//	@Test
//	public void runNestedLoopWithConditionInInnerLoopRaw1(){
//		String srcFileName =  "NestedLoopWithConditionInInnerLoopRaw1.bc";
//
//		Function function = getFunction(srcFileName, "foo");
//
//		assertNotNull(function);
//		Module module = getModule();
//		FSAliasAnalysis fsAliasAnalysis = new FSAliasAnalysis(null);
//		fsAliasAnalysis.analyze(module, function);
//		
//		List<Value> pointers = identifyPointers(module, function);
//	}
//	
//	@Ignore
//	@Test
//	public void runAliasInArrayIterationRaw1() throws Exception {
//		String srcFileName =  "ArrayIterationRaw1.bc";
//
//		Function function = getFunction(srcFileName, "foo");
//
//		assertNotNull(function);
//		Module module = getModule();
//		FSAliasAnalysis fsAliasAnalysis = new FSAliasAnalysis(null);
//		fsAliasAnalysis.analyze(module, function);
//		
//		List<Value> pointers = identifyPointers(module, function);
//		
//		// Initial point
//		BasicBlock bb = function.getBasicBlocks().get(0);
//		Instruction ins = bb.getInstructions().elementAt(19);
//		Value v4 = getPtr(pointers, "%4"); Value v5 = getPtr(pointers, "%5");
//		Value v6 = getPtr(pointers, "%6");
//		assertTrue(fsAliasAnalysis.alias(ins, v4, v5) == AliasResult.MUST_ALIAS);
//		assertTrue(fsAliasAnalysis.alias(ins, v4, v6) == AliasResult.MUST_ALIAS);
//		assertTrue(fsAliasAnalysis.alias(ins, v5, v6) == AliasResult.MUST_ALIAS);
//		
//		// Inside loop
//		bb = function.getBasicBlocks().get(2);
//		ins = bb.getInstructions().elementAt(12);
//		Value v12 = getPtr(pointers, "%12"); Value v14 = getPtr(pointers, "%14");
//		Value v16 = getPtr(pointers, "%16"); 
//		assertTrue(fsAliasAnalysis.alias(ins, v12, v14) == AliasResult.NO_ALIAS);
//		assertTrue(fsAliasAnalysis.alias(ins, v12, v16) == AliasResult.NO_ALIAS);
//		assertTrue(fsAliasAnalysis.alias(ins, v14, v16) == AliasResult.MUST_ALIAS);
//		
//		// Inside loop, another location
//		bb = function.getBasicBlocks().get(9);
//		ins = bb.getInstructions().elementAt(11);
//		Value v38 = getPtr(pointers, "%38"); Value v39 = getPtr(pointers, "%39");
//		Value v41 = getPtr(pointers, "%41"); 
//		
//		assertTrue(fsAliasAnalysis.alias(ins, v41, v38) == AliasResult.NO_ALIAS);
//		assertTrue(fsAliasAnalysis.alias(ins, v41, v39) == AliasResult.NO_ALIAS);
//		assertTrue(fsAliasAnalysis.alias(ins, v38, v39) == AliasResult.MUST_ALIAS);
//	}
//	
////	@Test
////	public void runAliasInArrayIteration1() throws Exception {
////		String srcFileName =  "ArrayIterationSrc1.bc";
////
////		Function function = getFunction(srcFileName, "foo");
////
////		assertNotNull(function);
////		Module module = getModule();
////		FSAliasAnalysis fsAliasAnalysis = new FSAliasAnalysis(null);
////		fsAliasAnalysis.analyze(module, function);
////		
////		List<Value> pointers = identifyPointers(module, function);
////		BasicBlock bb = function.getBasicBlocks().get(9);
////		Instruction ins = bb.getInstructions().elementAt(10);
////		Value v5 = getPtr(pointers, "%5"); Value v6 = getPtr(pointers, "%6");
////		Value v4 = getPtr(pointers, "%4");
////		assertTrue(fsAliasAnalysis.alias(ins, v5, v6) == AliasResult.MUST_ALIAS);
////		assertTrue(fsAliasAnalysis.alias(ins, v5, v4) == AliasResult.NO_ALIAS);
////		assertTrue(fsAliasAnalysis.alias(ins, v6, v4) == AliasResult.NO_ALIAS);
////		
////	}
////	
////	@Test
////	public void runAliasInArrayIterationRaw2() throws Exception {
////		String srcFileName =  "ArrayIterationRaw2.bc";
////
////		Function function = getFunction(srcFileName, "foo");
////
////		assertNotNull(function);
////		Module module = getModule();
////		FSAliasAnalysis fsAliasAnalysis = new FSAliasAnalysis(null);
////		fsAliasAnalysis.analyze(module, function);
////		
////		List<Value> pointers = identifyPointers(module, function);
////		
////		// Initial point
////		BasicBlock bb = function.getBasicBlocks().get(0);
////		Instruction ins = bb.getInstructions().elementAt(18);
////		Value v3 = getPtr(pointers, "%3"); Value v4 = getPtr(pointers, "%4");
////		Value v5 = getPtr(pointers, "%5");
////		assertTrue(fsAliasAnalysis.alias(ins, v3, v4) == AliasResult.MUST_ALIAS);
////		assertTrue(fsAliasAnalysis.alias(ins, v3, v5) == AliasResult.MUST_ALIAS);
////		assertTrue(fsAliasAnalysis.alias(ins, v4, v5) == AliasResult.MUST_ALIAS);
////		
////		// Inside loop
////		bb = function.getBasicBlocks().get(2);
////		ins = bb.getInstructions().elementAt(17);
////		Value v21 = getPtr(pointers, "%21"); Value v22 = getPtr(pointers, "%22");
////		Value v23 = getPtr(pointers, "%23"); 
////		assertTrue(fsAliasAnalysis.alias(ins, v21, v22) == AliasResult.NO_ALIAS);
////		assertTrue(fsAliasAnalysis.alias(ins, v21, v23) == AliasResult.NO_ALIAS);
////		assertTrue(fsAliasAnalysis.alias(ins, v22, v23) == AliasResult.MUST_ALIAS);
////		
////		// Outside the loop
////		bb = function.getBasicBlocks().get(5);
////		ins = bb.getInstructions().elementAt(0);
////		assertTrue(fsAliasAnalysis.alias(ins, v22, v23) == AliasResult.MUST_ALIAS);
////		
////		bb = function.getBasicBlocks().get(7);
////		ins = bb.getInstructions().elementAt(0);
////		assertTrue(fsAliasAnalysis.alias(ins, v22, v23) == AliasResult.MUST_ALIAS);
////		assertTrue(fsAliasAnalysis.alias(ins, v21, v23) == AliasResult.NO_ALIAS);
////	}
////	
////	@Test
////	public void runAliasInArrayIteration2() throws Exception {
////		String srcFileName =  "ArrayIterationSrc2.bc";
////
////		Function function = getFunction(srcFileName, "foo");
////
////		assertNotNull(function);
////		Module module = getModule();
////		FSAliasAnalysis fsAliasAnalysis = new FSAliasAnalysis(null);
////		fsAliasAnalysis.analyze(module, function);
////		
////		List<Value> pointers = identifyPointers(module, function);
////		
////		// Initial point
////		BasicBlock bb = function.getBasicBlocks().get(0);
////		Instruction ins = bb.getInstructions().elementAt(18);
////		Value v3 = getPtr(pointers, "%3"); Value v4 = getPtr(pointers, "%4");
////		Value v5 = getPtr(pointers, "%5");
////		assertTrue(fsAliasAnalysis.alias(ins, v3, v4) == AliasResult.MUST_ALIAS);
////		assertTrue(fsAliasAnalysis.alias(ins, v3, v5) == AliasResult.MUST_ALIAS);
////		assertTrue(fsAliasAnalysis.alias(ins, v4, v5) == AliasResult.MUST_ALIAS);
////		
////		// Inside loop
////		bb = function.getBasicBlocks().get(2);
////		ins = bb.getInstructions().elementAt(17);
////		Value v21 = getPtr(pointers, "%21"); Value v22 = getPtr(pointers, "%22");
////		Value v23 = getPtr(pointers, "%23"); 
////		assertTrue(fsAliasAnalysis.alias(ins, v21, v22) == AliasResult.NO_ALIAS);
////		assertTrue(fsAliasAnalysis.alias(ins, v21, v23) == AliasResult.NO_ALIAS);
////		assertTrue(fsAliasAnalysis.alias(ins, v22, v23) == AliasResult.MUST_ALIAS);
////		
////		// Outside the loop
////		bb = function.getBasicBlocks().get(5);
////		ins = bb.getInstructions().elementAt(0);
////		assertTrue(fsAliasAnalysis.alias(ins, v22, v23) == AliasResult.MUST_ALIAS);
////		
////		bb = function.getBasicBlocks().get(7);
////		ins = bb.getInstructions().elementAt(0);
////		assertTrue(fsAliasAnalysis.alias(ins, v22, v23) == AliasResult.MUST_ALIAS);
////		assertTrue(fsAliasAnalysis.alias(ins, v21, v23) == AliasResult.NO_ALIAS);
////	}
//	
//	private Function getFunction(String srcFileName, String functionNameToOptimize){
//		getListOfInstrs(ROOT_PATH, srcFileName);
//
//		Module module = getModule();
//		List<Function> functions = module.getFunctions();
//		Function function = null;
//		for(Function currentFunction : functions){
//			String function_name = currentFunction.getName();
//
//			if(function_name.equals(functionNameToOptimize))
//			{
//				function = currentFunction;
//				break;
//
//			}
//		}
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
//				
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

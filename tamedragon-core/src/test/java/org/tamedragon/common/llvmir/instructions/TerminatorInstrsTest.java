package org.tamedragon.common.llvmir.instructions;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.tamedragon.common.llvmir.instructions.BranchInst;
import org.tamedragon.common.llvmir.instructions.CFG;
import org.tamedragon.common.llvmir.instructions.CallingConv;
import org.tamedragon.common.llvmir.instructions.ReturnInst;
import org.tamedragon.common.llvmir.instructions.SwitchInst;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionCreationException;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionDetailAccessException;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionUpdateException;
import org.tamedragon.common.llvmir.math.APInt;
import org.tamedragon.common.llvmir.types.APFloat;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.CompilationContext;
import org.tamedragon.common.llvmir.types.ConstantFP;
import org.tamedragon.common.llvmir.types.ConstantInt;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.GlobalValue;
import org.tamedragon.common.llvmir.types.Module;
import org.tamedragon.common.llvmir.types.PointerType;
import org.tamedragon.common.llvmir.types.Type;
import org.tamedragon.common.llvmir.types.Value;
import org.tamedragon.common.llvmir.types.GlobalValue.LinkageTypes;
import org.tamedragon.common.llvmir.types.exceptions.TypeCreationException;

public class TerminatorInstrsTest {

	private CompilationContext compilationContext;
	private Function function;

	@Before
	public void setUp(){
		compilationContext = new CompilationContext();
		
		PointerType ptrToFunctype = null;
		Vector<Type> paramTypes = new Vector<Type>();
		Type funcType = null;
		try {
			funcType = Type.getFunctionType(compilationContext, Type.getInt32Type(compilationContext, true),
						false, paramTypes );
			ptrToFunctype = Type.getPointerType(compilationContext, funcType, 0);
		} catch (TypeCreationException e) {fail("Function type could not be created");}
		
		Module module = new Module("sampleModule", compilationContext, null);
		CFG cfg = new CFG();
		function = Function.create(module, ptrToFunctype, null, CallingConv.C, "foo", cfg);
		
	}

	//*************************************************************************************************************
	//*********************** TESTS FOR THE RETURN INSTRUCTION  ***************************************************
	//*************************************************************************************************************

	// Try creating valid values of the return instruction and check the 
	// string description
	@Test
	public void testValidReturnInstructionCreation(){
		try{
			Type int32 = Type.getInt32Type(compilationContext, true);
			Value retVal = new Value(int32);
			retVal.setName("2");
			ReturnInst retInst1 = null;
			retInst1 = ReturnInst.create(compilationContext, retVal, null);
			assertNotNull(retInst1);
			assertTrue(retInst1.emit().equals("ret i32 %2"));

			ReturnInst retInst2 = null;
			APFloat apFloat = new APFloat(APFloat.IEEEdouble, "34");
			try {
				retVal = new ConstantFP(Type.getDoubleType(compilationContext), apFloat );
			} catch (InstantiationException e) {
				e.printStackTrace();
				assertTrue(false);
			}
			retInst2 = ReturnInst.create(compilationContext, retVal, null);
			assertNotNull(retInst2);
			assertTrue(retInst2.emit().equals("ret double 3.400000e+01"));

			ReturnInst retInst3 = null;
			retVal = new Value(Type.getVoidType(compilationContext));
			retVal.setName("");
			retInst3 = ReturnInst.create(compilationContext, retVal, null);
			assertNotNull(retInst3);
			assertTrue(retInst3.emit().equals("ret void"));

			ReturnInst retInst4 = null;
			retVal = new Value(Type.getFloatType(compilationContext));
			retVal.setName("7");
			retInst4 = ReturnInst.create(compilationContext, retVal, null);
			assertNotNull(retInst4);
			assertTrue(retInst4.emit().equals("ret float %7"));
		}
		catch(InstructionCreationException ice){
			ice.printStackTrace();
			assertTrue(false);
		}
	}

	// Try creating return instructions with wrong parameters (function return type)
	@Test
	public void testInValidReturnInstructionCreation(){
		ReturnInst retInst1 = null;
		String errMessage = "";

		try{
			Type funcType = Type.getFunctionType(compilationContext, Type.getDoubleType(compilationContext), false, null);
			Value retVal = new Value(funcType);
			retVal.setName("2");
			retInst1 = ReturnInst.create(compilationContext, retVal, null);

		}
		catch(InstructionCreationException ice){ 
			errMessage = ice.getMessage();
		}
		catch(TypeCreationException tce){ }
		assertNull(retInst1);
		assertTrue(errMessage.equals(InstructionCreationException.RETURN_INST_MUST_RETURN_FIRST_CLASS_TYPE_OR_VOID));
	}

	// Try creating return instructions with wrong setters and getters (the return instruction does not have 
	// successors).
	@Test
	public void testInValidReturnInstructionSemantics(){
		ReturnInst retInst = null;

		String invalidRetInstUpdateMsg = "";
		String invalidRetInstDetailsAccessMsg = "";
		BasicBlock successor1 = null;
		int numSuccessors  = 0;

		// Create a valid return instruction that returns void
		try{
			Type voidType = Type.getVoidType(compilationContext);
			Value retVal = new Value(voidType);
			retVal.setName("2");
			retInst = ReturnInst.create(compilationContext, retVal, null);
		}
		catch(InstructionCreationException ice){ }
		assertNotNull(retInst);

		// Try updating the number of successors of the return instruction
		try{
			BasicBlock bb2 = new BasicBlock(compilationContext);
			bb2.setName("2");
			retInst.setSuccessor(0, bb2, true);
			numSuccessors = retInst.getNumSuccessors();
		}
		catch(InstructionUpdateException iue){
			invalidRetInstUpdateMsg = iue.getMessage();
		}
		assertTrue(invalidRetInstUpdateMsg.equals(InstructionUpdateException.RETURN_INST_CANNOT_SET_SUCCESSOR));
		assertTrue(numSuccessors == 0);

		// Try accessing the number of successors of a return instruction
		try{
			successor1 = retInst.getSuccessor(0);
		}
		catch(InstructionDetailAccessException idae){
			invalidRetInstDetailsAccessMsg = idae.getMessage();
		}
		assertTrue(invalidRetInstDetailsAccessMsg.equals(InstructionDetailAccessException.RETURN_INST_CANNOT_HAVE_SUCCESSORS));
		assertNull(successor1);
	}

	//*************************************************************************************************************
	//*********************** TESTS FOR BRANCH INSTRUCTIONS *******************************************************
	//*************************************************************************************************************

	@Test
	public void testUnconditionalBranchCreation(){
		BasicBlock basicBlock = new BasicBlock(compilationContext);
		basicBlock.setName("onlyLabel");
		BranchInst branchInst = BranchInst.create(basicBlock, null, compilationContext);

		assertTrue(branchInst.emit().equals("br label %onlyLabel"));
	}

	@Test
	public void testConditionalBranchCreation(){
		BasicBlock ifBB = new BasicBlock(compilationContext);
		ifBB.setName("ifLbl");
		
		BasicBlock elseBB = new BasicBlock(compilationContext);
		elseBB.setName("elseLbl");

		Value condition = null;
		try {
			condition = ConstantInt.create(Type.getInt1Type(compilationContext, false), 23, false);
		} catch (InstantiationException e1) {
			assertTrue(false);
			e1.printStackTrace();
		}

		BranchInst branchInst = null;

		try{
			branchInst = BranchInst.create(ifBB, elseBB, condition, null, compilationContext);
		}
		catch(Exception e){};

		assertNotNull(branchInst);
		assertTrue(branchInst.getType() == Type.getVoidType(compilationContext));
		assertTrue(branchInst.emit().equals("br i1 true, label %ifLbl, label %elseLbl"));
	}

	@Test
	public void testInvalidConditionalBranchCreationWithIncorrectConditionType(){
		BasicBlock ifBB = new BasicBlock(compilationContext);
		ifBB.setName("ifLbl");
		
		BasicBlock elseBB = new BasicBlock(compilationContext);
		elseBB.setName("elseLbl");

		Value condition = new Value(Type.getInt8Type(compilationContext, true));
		condition.setName("23");
		String errMsg = "";

		BranchInst branchInst = null;

		try{
			branchInst = BranchInst.create(ifBB, elseBB, condition, null, compilationContext);
		}
		catch(Exception e){
			errMsg = e.getMessage(); 
		}

		assertNull(branchInst);
		assertTrue(errMsg.equals(
				InstructionCreationException.CONDITION_FOR_CONDITIONAL_BRANCH_MUST_BE_OF_TYPE_I1));
	}

	@Test
	public void testInvalidUnconditionalBranchUpdateWithCondition(){
		BasicBlock onlyBB = new BasicBlock(compilationContext);
		onlyBB.setName("onlyBr");
		BranchInst unconditionalBr = BranchInst.create(onlyBB, null, compilationContext);

		Value condition = new Value(Type.getInt1Type(compilationContext, false));
		String errMsg = "";
		try{
			unconditionalBr.setCondition(condition);
		}
		catch(InstructionUpdateException iue){
			errMsg = iue.getMessage();
		}

		assertTrue(errMsg.equals(
				InstructionUpdateException.BRANCH_INST_CANNOT_SET_CONDITION_FOR_UNCONDITIONAL_BRANCH));

	}

	@Test
	public void testInvalidUnconditionalBranchUpdateWithNonZeroIndexForSuccessor(){
		BasicBlock onlyBB = new BasicBlock(compilationContext);
		onlyBB.setName("onlyBr");
		BranchInst unconditionalBr = BranchInst.create(onlyBB, null, compilationContext);

		BasicBlock newSuccessor = new BasicBlock(compilationContext);
		newSuccessor.setName("newBr");
		String errMsg = "";
		try{
			unconditionalBr.setSuccessor(1, newSuccessor, true);
		}
		catch(InstructionUpdateException iue){
			errMsg = iue.getMessage();
		}

		assertTrue(errMsg.equals(
				InstructionUpdateException.INVALID_INDEX_FOR_SUCCESSOR + "unconditional branch instruction: " + 1));
	}

	@Test
	public void testInvalidConditionalBranchUpdateWithIncorrectIndexForSuccessor(){
		BasicBlock ifBB = new BasicBlock(compilationContext);
		ifBB.setName("ifLbl");
		
		BasicBlock elseBB = new BasicBlock(compilationContext);
		elseBB.setName("elseLbl");
		Value condition = new Value(Type.getInt1Type(compilationContext, false));
		BranchInst branchInst = null;
		try{
			branchInst = BranchInst.create(ifBB, elseBB, condition, null, compilationContext);
		}
		catch(Exception e){ }
		assertNotNull(branchInst);

		BasicBlock newElseBB = new BasicBlock(compilationContext);
		newElseBB.setName("newElseLbl");

		String errMsg1 = "";

		try{
			branchInst.setSuccessor(3, newElseBB, true);
		}
		catch(InstructionUpdateException iue){
			errMsg1 = iue.getMessage();
		}

		assertTrue(errMsg1.equals(
				InstructionUpdateException.INVALID_INDEX_FOR_SUCCESSOR + "conditional branch instruction: " + 3));
	}

	@Test
	public void testInvalidUnconditionalBranchUpdateWithSwapOperation(){
		
		BasicBlock onlyBB = new BasicBlock(compilationContext);
		onlyBB.setName("onlyBr");
		BranchInst unconditionalBr = BranchInst.create(onlyBB, null, compilationContext);

		String errMsg = "";
		try{
			unconditionalBr.swapSuccessors();
		}
		catch(InstructionUpdateException iue){
			errMsg = iue.getMessage();
		}

		assertTrue(errMsg.equals(
				InstructionUpdateException.BRANCH_INST_CANNOT_SWAP_SUCCESSORS_FOR_UNCONDITIONAL_BRANCH));
	}

	@Test
	public void testInvalidUnconditionalBranchToGetCondition(){
		BasicBlock onlyBB = new BasicBlock(compilationContext);
		onlyBB.setName("onlyBr");
		BranchInst unconditionalBr = BranchInst.create(onlyBB, null, compilationContext);

		String errMsg = "";
		Value cond = null;
		try{
			cond = unconditionalBr.getCondition();
		}
		catch(InstructionDetailAccessException idae){
			errMsg = idae.getMessage();
		}
		assertNull(cond);
		assertTrue(errMsg.equals(
				InstructionDetailAccessException.BRANCH_INST_CANNOT_GET_CONDITION_FOR_UNCONDITIONAL_BRANCH));
	}
	
	@Test
	public void testConditionalBranchGetCondition(){
		BasicBlock ifBB = new BasicBlock(compilationContext);
		ifBB.setName("ifLbl");
		
		BasicBlock elseBB = new BasicBlock(compilationContext);
		elseBB.setName("elseLbl");
		Value condition = new Value(Type.getInt1Type(compilationContext, false));
		condition.setName("abc");
		BranchInst branchInst = null;
		try{
			branchInst = BranchInst.create(ifBB, elseBB, condition, null, compilationContext);
		}
		catch(Exception e){ }
		assertNotNull(branchInst);

		String errMsg = "";
		Value cond = null;
		try{
			cond = branchInst.getCondition();
		}
		catch(InstructionDetailAccessException idae){
			errMsg = idae.getMessage();
		}
		assertNotNull(cond);
		assertTrue(cond.toString().equals("i1 abc"));
		assertTrue(errMsg.equals(""));
	}
	
	
	@Test
	public void testUnconditionalBranchCreationAndUpdateSemantics(){

		// Create a valid unconditional branch instruction
		
		BasicBlock onlyBB = new BasicBlock(compilationContext);
		onlyBB.setName("onlyBr");
		BranchInst unconditionalBr = BranchInst.create(onlyBB, null, compilationContext);
		assertNotNull(unconditionalBr);
		assertTrue(unconditionalBr.isUnconditional());
		assertFalse(unconditionalBr.isConditional());
		assertTrue(unconditionalBr.getNumOperands() == 1);
		assertTrue(unconditionalBr.getType().isVoidType());
		assertTrue(unconditionalBr.emit().equals("br label %onlyBr"));
		BasicBlock onlySuccessor = null;
		try{
			onlySuccessor = unconditionalBr.getSuccessor(0);
		}
		catch(InstructionDetailAccessException idae){}
		assertNotNull(onlySuccessor);
		assertTrue(onlySuccessor.getName().equals("onlyBr"));

		// Update it by changing the successor
		String errMessage1 = "";
		BasicBlock newBB = new BasicBlock(compilationContext);
		newBB.setName("newBr");
		try{
			unconditionalBr.setSuccessor(0, newBB, true);
		}
		catch(InstructionUpdateException iue){ errMessage1 = iue.getMessage();}
		assertTrue(errMessage1.equals(""));
		BasicBlock newSuccessor = null;
		try{
			newSuccessor = unconditionalBr.getSuccessor(0);
		}
		catch(InstructionDetailAccessException idae){}
		assertNotNull(newSuccessor);
		assertTrue(newSuccessor.getName().equals("newBr"));
		assertTrue(unconditionalBr.emit().equals("br label %newBr"));
	}

	@Test
	public void testConditionalBranchCreationAndUpdateSemantics(){

		// Create a valid conditional branch instruction
		BasicBlock ifBB = new BasicBlock(compilationContext);
		ifBB.setName("ifBB");
		
		BasicBlock elseBB = new BasicBlock(compilationContext);
		elseBB.setName("elseBB");
		Value condition = new Value(Type.getInt1Type(compilationContext, false));
		try {
			condition = ConstantInt.create(Type.getInt1Type(compilationContext, false), 32, false);
		} catch (InstantiationException e) {
			e.printStackTrace();
			assertTrue(false);
		}
		BranchInst conditionalBr = null;

		try{
			conditionalBr = BranchInst.create(ifBB, elseBB, condition, null, compilationContext);
		}
		catch(InstructionCreationException ice){}
		assertNotNull(conditionalBr);
		assertTrue(conditionalBr.isConditional());
		assertFalse(conditionalBr.isUnconditional());
		assertTrue(conditionalBr.getNumOperands() == 3);
		assertTrue(conditionalBr.getType().isVoidType());
		BasicBlock succ1 = null;
		BasicBlock succ2 = null;
		try{
			succ1 = conditionalBr.getSuccessor(1);
			succ2 = conditionalBr.getSuccessor(2);
		}
		catch(InstructionDetailAccessException idae){}
		assertNotNull(succ1);
		assertNotNull(succ2);
		assertTrue(succ1.getName().equals("ifBB"));
		assertTrue(succ2.getName().equals("elseBB"));
		assertTrue(conditionalBr.emit().equals("br i1 true, label %ifBB, label %elseBB"));

		// Update the conditional branch instruction with a new condition and check again
		PointerType pointerType = null;
		try {
			pointerType = Type.getPointerType(compilationContext, Type.getInt1Type(compilationContext, false), 0);
		} catch (TypeCreationException e) {
			e.printStackTrace();
			assertTrue(false);
		}
		Value value = new Value(pointerType);
		List<Value> opernads = new ArrayList<Value>();
		opernads.add(value);
		Value newCondition = new GlobalValue(pointerType, opernads, LinkageTypes.CommonLinkage, "abc");
		try{
			conditionalBr.setCondition(newCondition);
		}
		catch(InstructionUpdateException iue){ }
		assertTrue(conditionalBr.emit().equals("br i1 true, label %ifBB, label %elseBB"));
	}

	//*************************************************************************************************************
	//*********************** TESTS FOR SWITCH INSTRUCTIONS *******************************************************
	//*************************************************************************************************************

	// Confirm that all "normal" switch cases are created correctly.
	@Test
	public void testSwitchCreation(){

		// Basic one without cases
		Value switchOn1 = new Value(Type.getInt32Type(compilationContext, true));
		switchOn1.setName("val");
		
		BasicBlock defaultBB1 = new BasicBlock(compilationContext);
		defaultBB1.setName("default");
		SwitchInst switchInst1 = null;

		try{
			switchInst1 = SwitchInst.create(switchOn1, defaultBB1, null);
		}
		catch(InstructionCreationException ice){}
		assertNotNull(switchInst1);
		assertTrue(switchInst1.toString().equals("switch i32 %val, label %default []"));
	}

	@Test
	public void testSwitchUpdate(){

		// Another one with some cases
		Value switchOn2 = new Value(Type.getInt32Type(compilationContext, true));
		switchOn2.setName("result");
		BasicBlock defaultBB2 = new BasicBlock(compilationContext);
		defaultBB2.setName("default");
		SwitchInst switchInst2 = null;
		try{
			switchInst2 = SwitchInst.create(switchOn2, defaultBB2, null);
		}
		catch(InstructionCreationException ice){}
		assertNotNull(switchInst2);
		ConstantInt caseVal1 = null;
		try {
			caseVal1 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "0", true));
		} catch (InstantiationException e) {}
		assertNotNull(caseVal1);
		
		BasicBlock succBlock1 = new BasicBlock(compilationContext);
		succBlock1.setName("onzero");
		ConstantInt caseVal2 = null;
		try {
			caseVal2 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "1", true));
		} catch (InstantiationException e) {}
		assertNotNull(caseVal2);
		
		BasicBlock succBlock2 = new BasicBlock(compilationContext);
		succBlock2.setName("onone");
		ConstantInt caseVal3 = null;
		try {
			caseVal3 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "2", true));
		} catch (InstantiationException e) {}
		assertNotNull(caseVal3);
		BasicBlock succBlock3 = new BasicBlock(compilationContext);
		succBlock3.setName("ontwo");
		
		try{
			switchInst2.addCase(caseVal1, succBlock1);
			switchInst2.addCase(caseVal2, succBlock2);
			switchInst2.addCase(caseVal3, succBlock3);
		}
		catch(InstructionUpdateException ice){ }

		String scrubbedOutput = switchInst2.toString().replaceAll("\n", "");
		assertTrue(scrubbedOutput.equals(
		"switch i32 %result, label %default [    i32 0, label %onzero    i32 1, label %onone    i32 2, label %ontwo   ]"));
	}

	@Test
	public void testInvalidSwitchWithNullInputs(){
		// Try with null switch on
		Value switchOn1 = null;
		BasicBlock defaultBB1 = new BasicBlock(compilationContext);
		defaultBB1.setName("default1");

		SwitchInst switchInst1 = null;

		String errMessage1 = "";
		try{
			switchInst1 = SwitchInst.create(switchOn1, defaultBB1, null);
		}
		catch(InstructionCreationException ice){
			errMessage1 = ice.getMessage();
		}
		assertNull(switchInst1);
		assertTrue(errMessage1.equals(InstructionCreationException.SWITCH_ON_CANNOT_BE_NULL));

		// Try with null default basic block
		Value switchOn2 = new Value(Type.getInt32Type(compilationContext, true));
		BasicBlock defaultBB2 = null;
		SwitchInst switchInst2 = null;

		String errMessage2 = "";
		try{
			switchInst2 = SwitchInst.create(switchOn2, defaultBB2, null);
		}
		catch(InstructionCreationException ice){
			errMessage2 = ice.getMessage();
		}
		assertNotNull(switchInst2);
		assertTrue(errMessage2.equals(""));
	}

	@Test
	public void testInvalidSwitchWithNonI32SwitchOn(){
		// Try with null switch on
		Value switchOn1 = new Value(Type.getInt1Type(compilationContext, false));
		BasicBlock defaultBB1 = new BasicBlock(compilationContext);
		defaultBB1.setName("default1");
		SwitchInst switchInst1 = null;

		String errMessage1 = "";
		try{
			switchInst1 = SwitchInst.create(switchOn1, defaultBB1, null);
		}
		catch(InstructionCreationException ice){
			errMessage1 = ice.getMessage();
		}
		assertNull(switchInst1);
		assertTrue(errMessage1.equals(InstructionCreationException.SWITCH_ON_TYPE_MUST_BE_I32_OR_I64));
	}

	@Test
	public void testInvalidSwitchUpdateDuplicateCaseValues(){

		Value switchOn2 = new Value(Type.getInt32Type(compilationContext, true));
		switchOn2.setName("result");
		BasicBlock defaultBB2 = new BasicBlock(compilationContext);
		defaultBB2.setName("default");
		SwitchInst switchInst2 = null;
		try{
			switchInst2 = SwitchInst.create(switchOn2, defaultBB2, null);
		}
		catch(InstructionCreationException ice){}
		assertNotNull(switchInst2);
		String errMsg = null;
		ConstantInt caseVal1 = null;
		try {
			caseVal1 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "0", true));
		} catch (InstantiationException e) {}
		assertNotNull(caseVal1);
		
		BasicBlock succBlock1 = new BasicBlock(compilationContext);
		succBlock1.setName("onzero");
		ConstantInt caseVal2 = null;
		try {
			caseVal2 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "0", true));
		} catch (InstantiationException e) {}
		assertNotNull(caseVal2);
		
		BasicBlock succBlock2 = new BasicBlock(compilationContext);
		succBlock2.setName("onzero");
		ConstantInt caseVal3 = null;
		try {
			caseVal3 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "2", true));
		} catch (InstantiationException e) {}
		assertNotNull(caseVal3);
		BasicBlock succBlock3 = new BasicBlock(compilationContext);
		succBlock3.setName("ontwo");
		try{
			switchInst2.addCase(caseVal1, succBlock1);
			switchInst2.addCase(caseVal2, succBlock2);
			switchInst2.addCase(caseVal3, succBlock3);
		}
		catch(InstructionUpdateException ice){ 
			errMsg = ice.getMessage();
		}
		assertTrue(errMsg.equals(InstructionUpdateException.CASE_VALUES_CANNOT_BE_DUPLICATE));
	}

	@Test
	public void testSwitchUpdateWithCorrectInputsForSuccessorUpdate(){

		Value switchOn1 = new Value(Type.getInt32Type(compilationContext, true));
		switchOn1.setName("res");
		
		BasicBlock defaultBB2 = new BasicBlock(compilationContext);
		defaultBB2.setName("default");
		SwitchInst switchInst1 = null;
		try{
			switchInst1 = SwitchInst.create(switchOn1, defaultBB2, null);
		}
		catch(InstructionCreationException ice){}
		assertNotNull(switchInst1);
		ConstantInt caseVal1 = null;
		try {
			caseVal1 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "1", true));
		} catch (InstantiationException e) {}
		assertNotNull(caseVal1);
		
		BasicBlock succBlock1 = new BasicBlock(compilationContext);
		succBlock1.setName("onone");
		ConstantInt caseVal2 = null;
		try {
			caseVal2 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "2", true));
		} catch (InstantiationException e) {}
		assertNotNull(caseVal2);
		BasicBlock succBlock2 = new BasicBlock(compilationContext);
		succBlock2.setName("ontwo");
		ConstantInt caseVal3 = null;
		try {
			caseVal3 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "3", true));
		} catch (InstantiationException e) {}
		assertNotNull(caseVal3);
		
		BasicBlock succBlock3 = new BasicBlock(compilationContext);
		succBlock3.setName("onthree");
		try{
			switchInst1.addCase(caseVal1, succBlock1);
			switchInst1.addCase(caseVal2, succBlock2);
			switchInst1.addCase(caseVal3, succBlock3);
		}
		catch(InstructionUpdateException ice){  }
		assertTrue(switchInst1.getNumCases() == 4);
		String scrubbedOutput = switchInst1.toString().replaceAll("\n", "");
		assertTrue(scrubbedOutput.equals(
		"switch i32 %res, label %default [    i32 1, label %onone    i32 2, label %ontwo    i32 3, label %onthree   ]"));

		// Try updating with index = 0
		String errMsg1 = "";
		BasicBlock newDefaultSuccessor = new BasicBlock(compilationContext);
		newDefaultSuccessor.setName("newdefault");
		try{
			switchInst1.setSuccessor(0, newDefaultSuccessor, true);
		}
		catch(InstructionUpdateException iue){
			errMsg1 = iue.getMessage();
		}
		assertTrue(errMsg1.equals(""));

		scrubbedOutput = switchInst1.toString().replaceAll("\n", "");
		assertTrue(scrubbedOutput.equals(
		"switch i32 %res, label %newdefault [    i32 1, label %onone    i32 2, label %ontwo    i32 3, label %onthree   ]"));

		// Try updating with index = 1
		errMsg1 = "";
		
		BasicBlock newSuccessor = new BasicBlock(compilationContext);
		newSuccessor.setName("newBB");
		try{
			switchInst1.setSuccessor(1, newSuccessor, true);
		}
		catch(InstructionUpdateException iue){
			errMsg1 = iue.getMessage();
		}
		assertTrue(errMsg1.equals(""));

		scrubbedOutput = switchInst1.toString().replaceAll("\n", "");
		assertTrue(scrubbedOutput.equals(
		"switch i32 %res, label %newdefault [    i32 1, label %newBB    i32 2, label %ontwo    i32 3, label %onthree   ]"));
	}

	@Test
	public void testInvalidSwitchUpdateWrongInputsForSuccessorUpdate(){

		Value switchOn1 = new Value(Type.getInt32Type(compilationContext, true));
		switchOn1.setName("res");
		
		BasicBlock defaultBB2 = new BasicBlock(compilationContext);
		defaultBB2.setName("default");
		SwitchInst switchInst1 = null;
		try{
			switchInst1 = SwitchInst.create(switchOn1, defaultBB2, null);
		}
		catch(InstructionCreationException ice){}
		assertNotNull(switchInst1);
		ConstantInt caseVal1 = null;
		try {
			caseVal1 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "1", true));
		} catch (InstantiationException e) {}
		assertNotNull(caseVal1);
		
		BasicBlock succBlock1 = new BasicBlock(compilationContext);
		succBlock1.setName("onzero");
		ConstantInt caseVal2 = null;
		try {
			caseVal2 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "2", true));
		} catch (InstantiationException e) {}
		assertNotNull(caseVal2);
		
		BasicBlock succBlock2 = new BasicBlock(compilationContext);
		succBlock2.setName("onone");
		ConstantInt caseVal3 = null;
		try {
			caseVal3 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "3", true));
		} catch (InstantiationException e) {}
		assertNotNull(caseVal3);
		
		BasicBlock succBlock3 = new BasicBlock(compilationContext);
		succBlock3.setName("ontwo");
		try{
			switchInst1.addCase(caseVal1, succBlock1);
			switchInst1.addCase(caseVal2, succBlock2);
			switchInst1.addCase(caseVal3, succBlock3);
		}
		catch(InstructionUpdateException ice){  }
		assertTrue(switchInst1.getNumCases() == 4);

		// Try updating with index = -1
		String errMsg1 = "";
		
		BasicBlock newSuccessor = new BasicBlock(compilationContext);
		newSuccessor.setName("newBB");
		try{
			switchInst1.setSuccessor(-1, newSuccessor, true);
		}
		catch(InstructionUpdateException iue){
			errMsg1 = iue.getMessage();
		}

		assertTrue(errMsg1.equals(InstructionUpdateException.INVALID_INDEX_FOR_SUCCESSOR 
				+ switchInst1.getInstructionName() + ": -1"));

		// Try updating with invalid successor index, but with valid successor
		errMsg1 = "";
		newSuccessor = new BasicBlock(compilationContext);
		newSuccessor.setName("newBB");
		try{
			switchInst1.setSuccessor(4, newSuccessor, true);
		}
		catch(InstructionUpdateException iue){
			errMsg1 = iue.getMessage();
		}
		assertTrue(errMsg1.equals(InstructionUpdateException.INVALID_INDEX_FOR_SUCCESSOR 
				+ switchInst1.getInstructionName() + ": 4"));

		// Try updating with valid index, but null successor
		newSuccessor = null;
		errMsg1 = "";
		try{
			switchInst1.setSuccessor(1, newSuccessor, true);
		}
		catch(InstructionUpdateException iue){
			errMsg1 = iue.getMessage();
		}
		assertTrue(errMsg1.equals(InstructionUpdateException.BASIC_BLOCK_CANNOT_BE_NULL));
	}

	@Test
	public void testSwitchUpdateWithCorrectInputsForAddingCase(){

		Value switchOn1 = new Value(Type.getInt32Type(compilationContext, true));
		switchOn1.setName("23");
		
		BasicBlock defaultBB2 = new BasicBlock(compilationContext);
		defaultBB2.setName("ifallelsefails");
		SwitchInst switchInst1 = null;
		try{
			switchInst1 = SwitchInst.create(switchOn1, defaultBB2, null);
		}
		catch(InstructionCreationException ice){}
		assertNotNull(switchInst1);
		ConstantInt caseVal1 = null;
		try {
			caseVal1 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "12", true));
		} catch (InstantiationException e) {}
		assertNotNull(caseVal1);
		
		BasicBlock succBlock1 = new BasicBlock(compilationContext);
		succBlock1.setName("12");
		ConstantInt caseVal2 = null;
		try {
			caseVal2 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "13", true));
		} catch (InstantiationException e) {}
		assertNotNull(caseVal2);
		
		BasicBlock succBlock2 = new BasicBlock(compilationContext);
		succBlock2.setName("13");
		ConstantInt caseVal3 = null;
		try {
			caseVal3 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "14", true));
		} catch (InstantiationException e) {}
		assertNotNull(caseVal3);
		
		BasicBlock succBlock3 = new BasicBlock(compilationContext);
		succBlock3.setName("14");
		try{
			switchInst1.addCase(caseVal1, succBlock1);
			switchInst1.addCase(caseVal2, succBlock2);
			switchInst1.addCase(caseVal3, succBlock3);
		}
		catch(InstructionUpdateException ice){  }
		assertTrue(switchInst1.getNumCases() == 4);
		
		String scrubbedOutput = switchInst1.toString().replaceAll("\n", "");
		assertTrue(scrubbedOutput.equals(
		"switch i32 %23, label %ifallelsefails [    i32 12, label %12    i32 13, label %13    i32 14, label %14   ]"));

		// Add a new case
		ConstantInt newCaseVal = null;
		try {
			newCaseVal = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "15", true));
		} catch (InstantiationException e) {}
		assertNotNull(newCaseVal);
		
		BasicBlock newSuccBlock = new BasicBlock(compilationContext);
		newSuccBlock.setName("15");
		String errMsg = "";
		try{
			switchInst1.addCase(newCaseVal, newSuccBlock);
		}
		catch(InstructionUpdateException iue){ errMsg = iue.getMessage(); }
		assertTrue(errMsg.equals(""));
		
		scrubbedOutput = switchInst1.toString().replaceAll("\n", "");
		assertTrue(scrubbedOutput.equals(
		"switch i32 %23, label %ifallelsefails [    i32 12, label %12    i32 13, label %13    i32 14, label %14    i32 15, label %15   ]"));
	}

	@Test
	public void testInvalidSwitchUpdateWrongInputsForAddingCase(){
		Value switchOn1 = new Value(Type.getInt32Type(compilationContext, true));
		switchOn1.setName("23");
		
		BasicBlock defaultBB2 = new BasicBlock(compilationContext);
		defaultBB2.setName("default");
		SwitchInst switchInst1 = null;
		try{
			switchInst1 = SwitchInst.create(switchOn1, defaultBB2, null);
		}
		catch(InstructionCreationException ice){}
		assertNotNull(switchInst1);
		ConstantInt caseVal1 = null;
		try {
			caseVal1 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "12", true));
		} catch (InstantiationException e) {}
		assertNotNull(caseVal1);
		
		BasicBlock succBlock1 = new BasicBlock(compilationContext);
		succBlock1.setName("12");
		ConstantInt caseVal2 = null;
		try {
			caseVal2 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "13", true));
		} catch (InstantiationException e) {}
		assertNotNull(caseVal2);
		
		BasicBlock succBlock2 = new BasicBlock(compilationContext);
		succBlock2.setName("13");
		ConstantInt caseVal3 = null;
		try {
			caseVal3 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "14", true));
		} catch (InstantiationException e) {}
		assertNotNull(caseVal3);
		BasicBlock succBlock3 = new BasicBlock(compilationContext);
		succBlock3.setName("14");
		try{
			switchInst1.addCase(caseVal1, succBlock1);
			switchInst1.addCase(caseVal2, succBlock2);
			switchInst1.addCase(caseVal3, succBlock3);
		}
		catch(InstructionUpdateException ice){  }
		assertTrue(switchInst1.getNumCases() == 4);
		
		String scrubbedOutput = switchInst1.toString().replaceAll("\n", "");
		assertTrue(scrubbedOutput.equals(
		"switch i32 %23, label %default [    i32 12, label %12    i32 13, label %13    i32 14, label %14   ]"));
		
		// Try adding a new case with null for case value
		ConstantInt newCaseVal = null;
		BasicBlock newSuccBlock = new BasicBlock(compilationContext);
		newSuccBlock.setName("15");
		String errMsg = "";
		try{
			switchInst1.addCase(newCaseVal, newSuccBlock);
		}
		catch(InstructionUpdateException iue){ errMsg = iue.getMessage(); }
		assertTrue(errMsg.equals(InstructionUpdateException.CONSTANT_INT_CANNOT_BE_NULL));
		
		scrubbedOutput = switchInst1.toString().replaceAll("\n", "");
		assertTrue(scrubbedOutput.equals(
		"switch i32 %23, label %default [    i32 12, label %12    i32 13, label %13    i32 14, label %14   ]"));
		
		// Try adding a new case with null for the successor block
		errMsg = "";
		newCaseVal = null;
		try {
			newCaseVal = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "15", true));
		} catch (InstantiationException e) {}
		assertNotNull(newCaseVal);
		newSuccBlock = null;
		try{
			switchInst1.addCase(newCaseVal, newSuccBlock);
		}
		catch(InstructionUpdateException iue){ errMsg = iue.getMessage(); }
		assertTrue(errMsg.equals(InstructionUpdateException.BASIC_BLOCK_CANNOT_BE_NULL));
		scrubbedOutput = switchInst1.toString().replaceAll("\n", "");
		assertTrue(scrubbedOutput.equals(
		"switch i32 %23, label %default [    i32 12, label %12    i32 13, label %13    i32 14, label %14   ]"));
		
		errMsg = "";
		newCaseVal = null;
		try {
			newCaseVal = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "14", true));
		} catch (InstantiationException e) {}
		assertNotNull(newCaseVal);
		newSuccBlock = new BasicBlock(compilationContext);
		try{
			switchInst1.addCase(newCaseVal, newSuccBlock);
		}
		catch(InstructionUpdateException iue){ errMsg = iue.getMessage(); }
		assertTrue(errMsg.equals(InstructionUpdateException.CASE_VALUES_CANNOT_BE_DUPLICATE));
		scrubbedOutput = switchInst1.toString().replaceAll("\n", "");
		assertTrue(scrubbedOutput.equals(
		"switch i32 %23, label %default [    i32 12, label %12    i32 13, label %13    i32 14, label %14   ]"));
	}
	
	@Test
	public void testSwitchUpdateWithCorrectInputsForSettingSwitchOn(){

		Value switchOn1 = new Value(Type.getInt32Type(compilationContext, true));
		switchOn1.setName("1");
		
		BasicBlock defaultBB2 = new BasicBlock(compilationContext);
		defaultBB2.setName("anyother");
		SwitchInst switchInst1 = null;
		try{
			switchInst1 = SwitchInst.create(switchOn1, defaultBB2, null);
		}
		catch(InstructionCreationException ice){}
		assertNotNull(switchInst1);
		ConstantInt caseVal1 = null;
		try {
			caseVal1 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "12", true));
		} catch (InstantiationException e) {}
		assertNotNull(caseVal1);
		
		BasicBlock succBlock1 = new BasicBlock(compilationContext);
		succBlock1.setName("12");
		ConstantInt caseVal2 = null;
		try {
			caseVal2 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "13", true));
		} catch (InstantiationException e) {}
		assertNotNull(caseVal2);
		
		BasicBlock succBlock2 = new BasicBlock(compilationContext);
		succBlock2.setName("13");
		try{
			switchInst1.addCase(caseVal1, succBlock1);
			switchInst1.addCase(caseVal2, succBlock2);
		}
		catch(InstructionUpdateException ice){  }
		assertTrue(switchInst1.getNumCases() == 3);
		
		String scrubbedOutput = switchInst1.toString().replaceAll("\n", "");
		assertTrue(scrubbedOutput.equals(
		"switch i32 %1, label %anyother [    i32 12, label %12    i32 13, label %13   ]"));
		
		// Update the condition
		Value newSwitchOn = new Value(Type.getInt32Type(compilationContext, true));
		newSwitchOn.setName("3");
		String errMsg = "";
		try{
			switchInst1.setCondition(newSwitchOn, true);
		}
		catch(InstructionUpdateException iue){ errMsg = iue.getMessage(); }
		assertTrue(errMsg.equals(""));
		
		scrubbedOutput = switchInst1.toString().replaceAll("\n", "");		
		assertTrue(scrubbedOutput.equals(
		"switch i32 %3, label %anyother [    i32 12, label %12    i32 13, label %13   ]"));
	}
	
	@Test
	public void testSwitchUpdateWithWrongInputsForSettingSwitchOn(){

		Value switchOn1 = new Value(Type.getInt32Type(compilationContext, true));
		switchOn1.setName("1");
		BasicBlock defaultBB2 = new BasicBlock(compilationContext);
		defaultBB2.setName("anyother");
		SwitchInst switchInst1 = null;
		try{
			switchInst1 = SwitchInst.create(switchOn1, defaultBB2, null);
		}
		catch(InstructionCreationException ice){}
		assertNotNull(switchInst1);
		ConstantInt caseVal1 = null;
		try {
			caseVal1 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "12", true));
		} catch (InstantiationException e) {}
		assertNotNull(caseVal1);
		BasicBlock succBlock1 = new BasicBlock(compilationContext);
		succBlock1.setName("12");
		ConstantInt caseVal2 = null;
		try {
			caseVal2 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "13", true));
		} catch (InstantiationException e) {}
		assertNotNull(caseVal2);
		BasicBlock succBlock2 = new BasicBlock(compilationContext);
		succBlock2.setName("13");
		try{
			switchInst1.addCase(caseVal1, succBlock1);
			switchInst1.addCase(caseVal2, succBlock2);
		}
		catch(InstructionUpdateException ice){  }
		assertTrue(switchInst1.getNumCases() == 3);
		
		String scrubbedOutput = switchInst1.toString().replaceAll("\n", "");
		assertTrue(scrubbedOutput.equals(
		"switch i32 %1, label %anyother [    i32 12, label %12    i32 13, label %13   ]"));

		// Update the condition
		Value newSwitchOn = new Value(Type.getInt1Type(compilationContext, false));
		newSwitchOn.setName("3");
		String errMsg = "";
		try{
			switchInst1.setCondition(newSwitchOn, false);
		}
		catch(InstructionUpdateException iue){ errMsg = iue.getMessage(); }
		assertTrue(errMsg.equals(InstructionUpdateException.SWITCH_ON_TYPE_MUST_BE_I32_OR_I64));
		scrubbedOutput = switchInst1.toString().replaceAll("\n", "");
		assertTrue(scrubbedOutput.equals(
		"switch i32 %1, label %anyother [    i32 12, label %12    i32 13, label %13   ]"));
	}
	
	@Test
	public void testSwitchUpdateWithCorrectInputsForRemovingCase(){

		Value switchOn1 = new Value(Type.getInt32Type(compilationContext, true));
		switchOn1.setName("1");
		BasicBlock defaultBB2 = new BasicBlock(compilationContext);
		defaultBB2.setName("anyother");
		SwitchInst switchInst1 = null;
		try{
			switchInst1 = SwitchInst.create(switchOn1, defaultBB2, null);
		}
		catch(InstructionCreationException ice){}
		assertNotNull(switchInst1);
		ConstantInt caseVal1 = null;
		try {
			caseVal1 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "12", true));
		} catch (InstantiationException e) {}
		assertNotNull(caseVal1);
		
		BasicBlock succBlock1 = new BasicBlock(compilationContext);
		succBlock1.setName("12");
		ConstantInt caseVal2 = null;
		try {
			caseVal2 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "13", true));
		} catch (InstantiationException e) {}
		assertNotNull(caseVal2);
		
		BasicBlock succBlock2 = new BasicBlock(compilationContext);
		succBlock2.setName("13");
		ConstantInt caseVal3 = null;
		try {
			caseVal3 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "14", true));
		} catch (InstantiationException e) {}
		assertNotNull(caseVal3);
		BasicBlock succBlock3 = new BasicBlock(compilationContext);
		succBlock3.setName("14");
		ConstantInt caseVal4 = null;
		try {
			caseVal4 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "15", true));
		} catch (InstantiationException e) {}
		assertNotNull(caseVal4);
		BasicBlock succBlock4 = new BasicBlock(compilationContext);
		succBlock4.setName("15");
		ConstantInt caseVal5 = null;
		try {
			caseVal5 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "16", true));
		} catch (InstantiationException e) {}
		assertNotNull(caseVal5);
		
		BasicBlock succBlock5 = new BasicBlock(compilationContext);
		succBlock5.setName("16");
		try{
			switchInst1.addCase(caseVal1, succBlock1);
			switchInst1.addCase(caseVal2, succBlock2);
			switchInst1.addCase(caseVal3, succBlock3);
			switchInst1.addCase(caseVal4, succBlock4);
			switchInst1.addCase(caseVal5, succBlock5);
		}
		catch(InstructionUpdateException ice){  }
		assertTrue(switchInst1.getNumCases() == 6);
		String scrubbedOutput = switchInst1.toString().replaceAll("\n", "");
		assertTrue(scrubbedOutput.equals(
		"switch i32 %1, label %anyother [    i32 12, label %12    i32 13, label %13    i32 14, label %14    i32 15, label %15    i32 16, label %16   ]"));

		// Remove the first case
		String errMsg = "";
		try{
			switchInst1.removeCase(1);
		}
		catch(InstructionUpdateException iue){ errMsg = iue.getMessage(); }
		catch(InstructionDetailAccessException idae){ errMsg = idae.getMessage(); }
		assertTrue(errMsg.equals(""));
		assertTrue(switchInst1.getNumCases() == 5);
		
		scrubbedOutput = switchInst1.toString().replaceAll("\n", "");
		assertTrue(scrubbedOutput.equals(
		"switch i32 %1, label %anyother [    i32 13, label %13    i32 14, label %14    i32 15, label %15    i32 16, label %16   ]"));
		
		// Remove the last case
		errMsg = "";
		try{
			switchInst1.removeCase(4);
		}
		catch(InstructionUpdateException iue){ errMsg = iue.getMessage(); }
		catch(InstructionDetailAccessException idae){ errMsg = idae.getMessage(); }
		assertTrue(errMsg.equals(""));
		assertTrue(switchInst1.getNumCases() == 4);
		
		scrubbedOutput = switchInst1.toString().replaceAll("\n", "");
		assertTrue(scrubbedOutput.equals(
		"switch i32 %1, label %anyother [    i32 13, label %13    i32 14, label %14    i32 15, label %15   ]"));
		
		// Remove the case in the middle
		errMsg = "";
		try{
			switchInst1.removeCase(2);
		}
		catch(InstructionUpdateException iue){ errMsg = iue.getMessage(); }
		catch(InstructionDetailAccessException idae){ errMsg = idae.getMessage(); }
		assertTrue(errMsg.equals(""));
		assertTrue(switchInst1.getNumCases() == 3);
		
		scrubbedOutput = switchInst1.toString().replaceAll("\n", "");
		assertTrue(scrubbedOutput.equals(
		"switch i32 %1, label %anyother [    i32 13, label %13    i32 15, label %15   ]"));
	}
	
	@Test
	public void testInvalidSwitchUpdateWithWrongInputsForRemovingCase(){

		Value switchOn1 = new Value(Type.getInt32Type(compilationContext, true));
		switchOn1.setName("1");
		
		BasicBlock defaultBB2 = new BasicBlock(compilationContext);
		defaultBB2.setName("anyother");
		SwitchInst switchInst1 = null;
		try{
			switchInst1 = SwitchInst.create(switchOn1, defaultBB2, null);
		}
		catch(InstructionCreationException ice){}
		assertNotNull(switchInst1);
		ConstantInt caseVal1 = null;
		try {
			caseVal1 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "12", true));
		} catch (InstantiationException e) {}
		assertNotNull(caseVal1);
		
		BasicBlock succBlock1 = new BasicBlock(compilationContext);
		succBlock1.setName("12");
		ConstantInt caseVal2 = null;
		try {
			caseVal2 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "13", true));
		} catch (InstantiationException e) {}
		assertNotNull(caseVal2);
		
		BasicBlock succBlock2 = new BasicBlock(compilationContext);
		succBlock2.setName("13");
		try{
			switchInst1.addCase(caseVal1, succBlock1);
			switchInst1.addCase(caseVal2, succBlock2);
		}
		catch(InstructionUpdateException ice){  }
		assertTrue(switchInst1.getNumCases() == 3);
		
		String scrubbedOutput = switchInst1.toString().replaceAll("\n", "");
		assertTrue(scrubbedOutput.equals(
		"switch i32 %1, label %anyother [    i32 12, label %12    i32 13, label %13   ]"));

		// Try removing the first case at 0
		String errMsg = "";
		try{
			switchInst1.removeCase(0);
		}
		catch(InstructionUpdateException iue){ errMsg = iue.getMessage(); }
		catch(InstructionDetailAccessException idae){ errMsg = idae.getMessage(); }
		assertTrue(errMsg.equals(InstructionUpdateException.CANNOT_REMOVE_DEFAULT_CASE));
		assertTrue(switchInst1.getNumCases() == 3);
		
		scrubbedOutput = switchInst1.toString().replaceAll("\n", "");
		assertTrue(scrubbedOutput.equals(
		"switch i32 %1, label %anyother [    i32 12, label %12    i32 13, label %13   ]"));
		
		// Try removing with wrong index (-1) 
		errMsg = "";
		try{
			switchInst1.removeCase(-1);
		}
		catch(InstructionUpdateException iue){  }
		catch(InstructionDetailAccessException idae){ errMsg = idae.getMessage(); }
		assertTrue(errMsg.equals(InstructionDetailAccessException.INVALID_INDEX_FOR_SUCCESSOR 
				+ switchInst1.getInstructionName() + ": -1"));
		assertTrue(switchInst1.getNumCases() == 3);
		scrubbedOutput = switchInst1.toString().replaceAll("\n", "");
		assertTrue(scrubbedOutput.equals(
		"switch i32 %1, label %anyother [    i32 12, label %12    i32 13, label %13   ]"));
		
		// Try removing with wrong index (>= number of successors) 
		errMsg = "";
		try{
			switchInst1.removeCase(3);
		}
		catch(InstructionUpdateException iue){ errMsg = iue.getMessage(); }
		catch(InstructionDetailAccessException idae){ errMsg = idae.getMessage(); }
		assertTrue(errMsg.equals(InstructionDetailAccessException.INVALID_INDEX_FOR_SUCCESSOR 
				+ switchInst1.getInstructionName() + ": 3"));
		assertTrue(switchInst1.getNumCases() == 3);
		scrubbedOutput = switchInst1.toString().replaceAll("\n", "");
		assertTrue(scrubbedOutput.equals(
		"switch i32 %1, label %anyother [    i32 12, label %12    i32 13, label %13   ]"));
	}
	
	@Test
	public void testSwitchUpdateWithCorrectInputsForSettingSuccessorValue(){

		Value switchOn1 = new Value(Type.getInt32Type(compilationContext, true));
		switchOn1.setName("result");
		
		BasicBlock defaultBB2 = new BasicBlock(compilationContext);
		defaultBB2.setName("anyother");
		SwitchInst switchInst1 = null;
		try{
			switchInst1 = SwitchInst.create(switchOn1, defaultBB2, null);
		}
		catch(InstructionCreationException ice){}
		assertNotNull(switchInst1);
		ConstantInt caseVal1 = null;
		try {
			caseVal1 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "1", true));
		} catch (InstantiationException e) {}
		assertNotNull(caseVal1);
		
		BasicBlock succBlock1 = new BasicBlock(compilationContext);
		succBlock1.setName("dothis");
		ConstantInt caseVal2 = null;
		try {
			caseVal2 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "2", true));
		} catch (InstantiationException e) {}
		assertNotNull(caseVal2);
		
		BasicBlock succBlock2 = new BasicBlock(compilationContext);
		succBlock2.setName("dothat");
		try{
			switchInst1.addCase(caseVal1, succBlock1);
			switchInst1.addCase(caseVal2, succBlock2);
		}
		catch(InstructionUpdateException ice){  }
		assertTrue(switchInst1.getNumCases() == 3);
		String scrubbedOutput = switchInst1.toString().replaceAll("\n", "");
		assertTrue(scrubbedOutput.equals(
		"switch i32 %result, label %anyother [    i32 1, label %dothis    i32 2, label %dothat   ]"));

		// Set successor value
		ConstantInt newCaseForSucc1 = null;
		try {
			newCaseForSucc1 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "67", true));
		} catch (InstantiationException e1) {}
		assertNotNull(newCaseForSucc1);
		String errMsg = "";
		try{
			switchInst1.setSuccessorValue(1,newCaseForSucc1); 
		}
		catch(InstructionUpdateException iue){ errMsg = iue.getMessage(); }
		assertTrue(errMsg.equals(""));
		assertTrue(switchInst1.getNumCases() == 3);
		scrubbedOutput = switchInst1.toString().replaceAll("\n", "");
		assertTrue(scrubbedOutput.equals(
		"switch i32 %result, label %anyother [    i32 67, label %dothis    i32 2, label %dothat   ]"));
		
		// Set successor value
		ConstantInt newCaseForSucc2 = null;
		try {
			newCaseForSucc2 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "52", true));
		} catch (InstantiationException e) {}
		assertNotNull(newCaseForSucc2);
		errMsg = "";
		try{
			switchInst1.setSuccessorValue(2,newCaseForSucc2); 
		}
		catch(InstructionUpdateException iue){ errMsg = iue.getMessage(); }
		assertTrue(errMsg.equals(""));
		assertTrue(switchInst1.getNumCases() == 3);
		scrubbedOutput = switchInst1.toString().replaceAll("\n", "");
		assertTrue(scrubbedOutput.equals(
		"switch i32 %result, label %anyother [    i32 67, label %dothis    i32 52, label %dothat   ]"));
	}
	
	@Test
	public void testInvalidSwitchUpdateWithWrongInputsForSettingSuccessorValue(){
		Value switchOn1 = new Value(Type.getInt32Type(compilationContext, true));
		switchOn1.setName("result");
		
		BasicBlock defaultBB2 = new BasicBlock(compilationContext);
		defaultBB2.setName("anyother");
		SwitchInst switchInst1 = null;
		try{
			switchInst1 = SwitchInst.create(switchOn1, defaultBB2, null);
		}
		catch(InstructionCreationException ice){}
		assertNotNull(switchInst1);
		ConstantInt caseVal1 = null;
		try {
			caseVal1 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "1", true));
		} catch (InstantiationException e) {}
		assertNotNull(caseVal1);
		
		BasicBlock succBlock1 = new BasicBlock(compilationContext);
		succBlock1.setName("dothis");
		ConstantInt caseVal2 = null;
		try {
			caseVal2 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "2", true));
		} catch (InstantiationException e) {}
		assertNotNull(caseVal2);
		
		BasicBlock succBlock2 = new BasicBlock(compilationContext);
		succBlock2.setName("dothat");
		try{
			switchInst1.addCase(caseVal1, succBlock1);
			switchInst1.addCase(caseVal2, succBlock2);
		}
		catch(InstructionUpdateException ice){  }
		assertTrue(switchInst1.getNumCases() == 3);
		String scrubbedOutput = switchInst1.toString().replaceAll("\n", "");
		assertTrue(scrubbedOutput.equals(
		"switch i32 %result, label %anyother [    i32 1, label %dothis    i32 2, label %dothat   ]"));

		// Try setting successor value with invalid successor index (-1)
		ConstantInt newCaseForSucc1 = null;
		try {
			newCaseForSucc1 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "88", true));
		} catch (InstantiationException e) {}
		assertNotNull(newCaseForSucc1);
		String errMsg = "";
		try{
			switchInst1.setSuccessorValue(-1,newCaseForSucc1); 
		}
		catch(InstructionUpdateException iue){ errMsg = iue.getMessage(); }
		assertTrue(errMsg.equals(InstructionUpdateException.INVALID_INDEX_FOR_SUCCESSOR +
				switchInst1.getInstructionName() + ": -1"));
		assertTrue(switchInst1.getNumCases() == 3);
		scrubbedOutput = switchInst1.toString().replaceAll("\n", "");
		assertTrue(scrubbedOutput.equals(
		"switch i32 %result, label %anyother [    i32 1, label %dothis    i32 2, label %dothat   ]"));
		
		// Try setting successor value with invalid successor index (> number of successors)
		ConstantInt newCaseForSucc2 = null;
		try {
			newCaseForSucc2 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "77", true));
		} catch (InstantiationException e) {}
		assertNotNull(newCaseForSucc2);
		errMsg = "";
		try{
			switchInst1.setSuccessorValue(5, newCaseForSucc2); 
		}
		catch(InstructionUpdateException iue){ errMsg = iue.getMessage(); }
		assertTrue(errMsg.equals(InstructionUpdateException.INVALID_INDEX_FOR_SUCCESSOR +
				switchInst1.getInstructionName() + ": 5"));
		assertTrue(switchInst1.getNumCases() == 3);
		scrubbedOutput = switchInst1.toString().replaceAll("\n", "");
		assertTrue(scrubbedOutput.equals(
		"switch i32 %result, label %anyother [    i32 1, label %dothis    i32 2, label %dothat   ]"));
	}
	
	@Test
	public void testSwitchUpdateWithCorrectInputsForGettingSuccessor(){
		Value switchOn1 = new Value(Type.getInt32Type(compilationContext, true));
		switchOn1.setName("result");
		
		BasicBlock defaultBB2 = new BasicBlock(compilationContext);
		defaultBB2.setName("anyother");
		SwitchInst switchInst1 = null;
		try{
			switchInst1 = SwitchInst.create(switchOn1, defaultBB2, null);
		}
		catch(InstructionCreationException ice){}
		assertNotNull(switchInst1);
		ConstantInt caseVal1 = null;
		try {
			caseVal1 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "1", true));
		} catch (InstantiationException e) {}
		assertNotNull(caseVal1);
		
		BasicBlock succBlock1 = new BasicBlock(compilationContext);
		succBlock1.setName("dothis");
		ConstantInt caseVal2 = null;
		try {
			caseVal2 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "2", true));
		} catch (InstantiationException e) {}
		assertNotNull(caseVal2);
		
		BasicBlock succBlock2 = new BasicBlock(compilationContext);
		succBlock2.setName("dothat");
		try{
			switchInst1.addCase(caseVal1, succBlock1);
			switchInst1.addCase(caseVal2, succBlock2);
		}
		catch(InstructionUpdateException ice){  }
		assertTrue(switchInst1.getNumCases() == 3);
		String scrubbedOutput = switchInst1.toString().replaceAll("\n", "");
		assertTrue(scrubbedOutput.equals(
		"switch i32 %result, label %anyother [    i32 1, label %dothis    i32 2, label %dothat   ]"));

		// Get successor value at index 0
		BasicBlock bbAt0 = null;
		try{
			bbAt0 = switchInst1.getSuccessor(0);
		}
		catch(InstructionDetailAccessException idae){ }
		assertNotNull(bbAt0);
		assertTrue(bbAt0.getName().equals("anyother"));
		
		// Get successor value at index 0
		BasicBlock bbAt1 = null;
		try{
			bbAt1 = switchInst1.getSuccessor(1);
		}
		catch(InstructionDetailAccessException idae){ }
		assertNotNull(bbAt1);
		assertTrue(bbAt1.getName().equals("dothis"));
		
		// Get successor value at index 1
		BasicBlock bbAt2 = null;
		try{
			bbAt2 = switchInst1.getSuccessor(2);
		}
		catch(InstructionDetailAccessException idae){ }
		assertNotNull(bbAt2);
		assertTrue(bbAt2.getName().equals("dothat"));
	}
	
	@Test
	public void testInvalidSwitchUpdateWithWrongInputsForGettingSuccessor(){
		Value switchOn1 = new Value(Type.getInt32Type(compilationContext, true));
		switchOn1.setName("rain");
		
		BasicBlock defaultBB2 = new BasicBlock(compilationContext);
		defaultBB2.setName("sunny");
		SwitchInst switchInst1 = null;
		try{
			switchInst1 = SwitchInst.create(switchOn1, defaultBB2, null);
		}
		catch(InstructionCreationException ice){}
		assertNotNull(switchInst1);
		ConstantInt caseVal1 = null;
		try {
			caseVal1 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "10", true));
		} catch (InstantiationException e) {}
		assertNotNull(caseVal1);
		
		BasicBlock succBlock1 = new BasicBlock(compilationContext);
		succBlock1.setName("thunder");
		ConstantInt caseVal2 = null;
		try {
			caseVal2 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "20", true));
		} catch (InstantiationException e) {}
		assertNotNull(caseVal2);
		
		BasicBlock succBlock2 = new BasicBlock(compilationContext);
		succBlock2.setName("lightning");
		try{
			switchInst1.addCase(caseVal1, succBlock1);
			switchInst1.addCase(caseVal2, succBlock2);
		}
		catch(InstructionUpdateException ice){  }
		assertTrue(switchInst1.getNumCases() == 3);

		String scrubbedOutput = switchInst1.toString().replaceAll("\n", "");
		assertTrue(scrubbedOutput.equals(
		"switch i32 %rain, label %sunny [    i32 10, label %thunder    i32 20, label %lightning   ]"));

		// Try getting successor value at index -2
		String errMsg = "";
		BasicBlock bbAt0 = null;
		try{
			bbAt0 = switchInst1.getSuccessor(-2);
		}
		catch(InstructionDetailAccessException idae){errMsg = idae.getMessage();}
		assertNull(bbAt0);
		assertTrue(errMsg.equals(InstructionDetailAccessException.INVALID_INDEX_FOR_SUCCESSOR 
				 + switchInst1.getInstructionName() + ": -2"));
		
		// Try getting successor value at a wrong index ( > numSuccessors)
		errMsg = "";
		BasicBlock bbAt1 = null;
		try{
			bbAt1 = switchInst1.getSuccessor(3);
		}
		catch(InstructionDetailAccessException idae){errMsg = idae.getMessage(); }
		assertNull(bbAt1);
		assertTrue(errMsg.equals(InstructionDetailAccessException.INVALID_INDEX_FOR_SUCCESSOR 
				 + switchInst1.getInstructionName() + ": 3"));
	}
	
	@Test
	public void testSwitchUpdateWithCorrectInputsForGettingCaseValue(){
		Value switchOn1 = new Value(Type.getInt32Type(compilationContext, true));
		switchOn1.setName("color");
		BasicBlock defaultBB2 = new BasicBlock(compilationContext);
		defaultBB2.setName("white");
		SwitchInst switchInst1 = null;
		try{
			switchInst1 = SwitchInst.create(switchOn1, defaultBB2, null);
		}
		catch(InstructionCreationException ice){}
		assertNotNull(switchInst1);
		ConstantInt caseVal1 = null;
		try {
			caseVal1 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "10", true));
		} catch (InstantiationException e) {}
		assertNotNull(caseVal1);
		
		BasicBlock succBlock1 = new BasicBlock(compilationContext);
		succBlock1.setName("red");
		ConstantInt caseVal2 = null;
		try {
			caseVal2 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "20", true));
		} catch (InstantiationException e) {}
		assertNotNull(caseVal2);
		
		BasicBlock succBlock2 = new BasicBlock(compilationContext);
		succBlock2.setName("blue");
		try{
			switchInst1.addCase(caseVal1, succBlock1);
			switchInst1.addCase(caseVal2, succBlock2);
		}
		catch(InstructionUpdateException ice){  }
		assertTrue(switchInst1.getNumCases() == 3);
		String scrubbedOutput = switchInst1.toString().replaceAll("\n", "");
		assertTrue(scrubbedOutput.equals(
		"switch i32 %color, label %white [    i32 10, label %red    i32 20, label %blue   ]"));
		
		// Get the case value at 1
		String errMsg = "";
		ConstantInt ciAt1 = null;
		try{
			ciAt1 = switchInst1.getCaseValue(1);
		}
		catch(InstructionDetailAccessException idae){errMsg = idae.getMessage();}
		assertNotNull(ciAt1);
		assertTrue(ciAt1.toString().equals("i32 10"));
		assertTrue(errMsg.equals(""));
		
		// Get the case value at 1
		errMsg = "";
		ConstantInt ciAt2 = null;
		try{
			ciAt2 = switchInst1.getCaseValue(2);
		}
		catch(InstructionDetailAccessException idae){errMsg = idae.getMessage();}
		assertNotNull(ciAt2);
		assertTrue(ciAt2.toString().equals("i32 20"));
		assertTrue(errMsg.equals(""));
	}
	
	@Test
	public void testInvalidSwitchUpdateWithWrongInputsForGettingCaseValue(){
		Value switchOn1 = new Value(Type.getInt32Type(compilationContext, true));
		switchOn1.setName("vehicle");
		
		BasicBlock defaultBB2 = new BasicBlock(compilationContext);
		defaultBB2.setName("walk");
		SwitchInst switchInst1 = null;
		try{
			switchInst1 = SwitchInst.create(switchOn1, defaultBB2, null);
		}
		catch(InstructionCreationException ice){}
		assertNotNull(switchInst1);
		ConstantInt caseVal1 = null;
		try {
			caseVal1 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "90", true));
		} catch (InstantiationException e) {}
		assertNotNull(caseVal1);
		
		BasicBlock succBlock1 = new BasicBlock(compilationContext);
		succBlock1.setName("bus");
		ConstantInt caseVal2 = null;
		try {
			caseVal2 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "100", true));
		} catch (InstantiationException e) {}
		assertNotNull(caseVal2);
		BasicBlock succBlock2 = new BasicBlock(compilationContext);
		succBlock2.setName("train");
		try{
			switchInst1.addCase(caseVal1, succBlock1);
			switchInst1.addCase(caseVal2, succBlock2);
		}
		catch(InstructionUpdateException ice){  }
		assertTrue(switchInst1.getNumCases() == 3);
		String scrubbedOutput = switchInst1.toString().replaceAll("\n", "");
		assertTrue(scrubbedOutput.equals(
		"switch i32 %vehicle, label %walk [    i32 90, label %bus    i32 100, label %train   ]"));

		// Try getting the case value at 0
		String errMsg = "";
		ConstantInt ciAt1 = null;
		try{
			ciAt1 = switchInst1.getCaseValue(0);
		}
		catch(InstructionDetailAccessException idae){errMsg = idae.getMessage();}
		assertNull(ciAt1);
		assertTrue(errMsg.equals(InstructionDetailAccessException.INVALID_INDEX_FOR_CASE));
		
		// Try getting the case value at 3
		errMsg = "";
		ConstantInt ciAt3 = null;
		try{
			ciAt3 = switchInst1.getCaseValue(0);
		}
		catch(InstructionDetailAccessException idae){errMsg = idae.getMessage();}
		assertNull(ciAt3);
		assertTrue(errMsg.equals(InstructionDetailAccessException.INVALID_INDEX_FOR_CASE));
	}
	
	@Test
	public void testSwitchUpdateWithCorrectInputsForGettingSuccessorValue(){
		Value switchOn1 = new Value(Type.getInt32Type(compilationContext, true));
		switchOn1.setName("color");
		
		BasicBlock defaultBB2 = new BasicBlock(compilationContext);
		defaultBB2.setName("white");
		SwitchInst switchInst1 = null;
		try{
			switchInst1 = SwitchInst.create(switchOn1, defaultBB2, null);
		}
		catch(InstructionCreationException ice){}
		assertNotNull(switchInst1);
		ConstantInt caseVal1 = null;
		try {
			caseVal1 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "10", true));
		} catch (InstantiationException e) {}
		assertNotNull(caseVal1);
		BasicBlock succBlock1 = new BasicBlock(compilationContext);
		succBlock1.setName("red");
		ConstantInt caseVal2 = null;
		try {
			caseVal2 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "20", true));
		} catch (InstantiationException e) {}
		assertNotNull(caseVal2);
		
		BasicBlock succBlock2 = new BasicBlock(compilationContext);
		succBlock2.setName("blue");
		try{
			switchInst1.addCase(caseVal1, succBlock1);
			switchInst1.addCase(caseVal2, succBlock2);
		}
		catch(InstructionUpdateException ice){  }
		assertTrue(switchInst1.getNumCases() == 3);
		
		String scrubbedOutput = switchInst1.toString().replaceAll("\n", "");
		assertTrue(scrubbedOutput.equals(
		"switch i32 %color, label %white [    i32 10, label %red    i32 20, label %blue   ]"));
		
		// Get the successor value at 1
		String errMsg = "";
		ConstantInt ciAt1 = null;
		try{
			ciAt1 = switchInst1.getSuccessorValue(1);
		}
		catch(InstructionDetailAccessException idae){errMsg = idae.getMessage();}
		assertNotNull(ciAt1);
		assertTrue(ciAt1.toString().equals("i32 10"));
		assertTrue(errMsg.equals(""));

		// Get the successor value at 1
		errMsg = "";
		ConstantInt ciAt2 = null;
		try{
			ciAt2 = switchInst1.getSuccessorValue(2);
		}
		catch(InstructionDetailAccessException idae){errMsg = idae.getMessage();}
		assertNotNull(ciAt2);
		assertTrue(ciAt2.toString().equals("i32 20"));
		assertTrue(errMsg.equals(""));
	}
	
	@Test
	public void testInvalidSwitchUpdateWithWrongInputsForGettingSuccessorValue(){
		Value switchOn1 = new Value(Type.getInt32Type(compilationContext, true));
		switchOn1.setName("vehicle");
		
		BasicBlock defaultBB2 = new BasicBlock(compilationContext);
		defaultBB2.setName("walk");
		SwitchInst switchInst1 = null;
		try{
			switchInst1 = SwitchInst.create(switchOn1, defaultBB2, null);
		}
		catch(InstructionCreationException ice){}
		assertNotNull(switchInst1);
		ConstantInt caseVal1 = null;
		try {
			caseVal1 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "90", true));
		} catch (InstantiationException e) {}
		assertNotNull(caseVal1);
		
		BasicBlock succBlock1 = new BasicBlock(compilationContext);
		succBlock1.setName("bus");
		ConstantInt caseVal2 = null;
		try {
			caseVal2 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "100", true));
		} catch (InstantiationException e) {}
		assertNotNull(caseVal2);
		
		BasicBlock succBlock2 = new BasicBlock(compilationContext);
		succBlock2.setName("train");
		try{
			switchInst1.addCase(caseVal1, succBlock1);
			switchInst1.addCase(caseVal2, succBlock2);
		}
		catch(InstructionUpdateException ice){  }
		assertTrue(switchInst1.getNumCases() == 3);
		
		String scrubbedOutput = switchInst1.toString().replaceAll("\n", "");
		assertTrue(scrubbedOutput.equals(
		"switch i32 %vehicle, label %walk [    i32 90, label %bus    i32 100, label %train   ]"));
		
		// Try getting the successor value at 0
		String errMsg = "";
		ConstantInt ciAt0 = null;
		try{
			ciAt0 = switchInst1.getSuccessorValue(0);
		}
		catch(InstructionDetailAccessException idae){errMsg = idae.getMessage();}
		assertNull(ciAt0);
		assertTrue(errMsg.equals(InstructionDetailAccessException.INVALID_INDEX_FOR_SUCCESSOR +
								switchInst1.getInstructionName() + ": 0"));
		
		// Try getting the successor value at 3
		errMsg = "";
		ConstantInt ciAt3 = null;
		try{
			ciAt3 = switchInst1.getSuccessorValue(3);
		}
		catch(InstructionDetailAccessException idae){errMsg = idae.getMessage();}
		assertNull(ciAt3);
		assertTrue(errMsg.equals(InstructionDetailAccessException.INVALID_INDEX_FOR_SUCCESSOR +
								switchInst1.getInstructionName() + ": 3"));
	}
	
	@Test
	public void testSwitchUpdateForFindingCaseValue(){
		Value switchOn1 = new Value(Type.getInt32Type(compilationContext, true));
		switchOn1.setName("shape");
		
		BasicBlock defaultBB2 = new BasicBlock(compilationContext);
		defaultBB2.setName("sphere");
		SwitchInst switchInst1 = null;
		try{
			switchInst1 = SwitchInst.create(switchOn1, defaultBB2, null);
		}
		catch(InstructionCreationException ice){}
		assertNotNull(switchInst1);
		ConstantInt caseVal1 = null;
		try {
			caseVal1 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "5", true));
		} catch (InstantiationException e) {}
		assertNotNull(caseVal1);
		
		BasicBlock succBlock1 = new BasicBlock(compilationContext);
		succBlock1.setName("box");
		ConstantInt caseVal2 = null;
		try {
			caseVal2 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "6", true));
		} catch (InstantiationException e) {}
		assertNotNull(caseVal2);
		
		BasicBlock succBlock2 = new BasicBlock(compilationContext);
		succBlock2.setName("cube");
		ConstantInt caseVal3 = null;
		try {
			caseVal3 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "7", true));
		} catch (InstantiationException e) {}
		assertNotNull(caseVal3);
		
		BasicBlock succBlock3 = new BasicBlock(compilationContext);
		succBlock3.setName("rhomboid");
		ConstantInt caseVal4 = null;
		try {
			caseVal4 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "8", true));
		} catch (InstantiationException e) {}
		assertNotNull(caseVal4);
		BasicBlock succBlock4 = new BasicBlock(compilationContext);
		succBlock4.setName("pyramid");
		
		try{
			switchInst1.addCase(caseVal1, succBlock1);
			switchInst1.addCase(caseVal2, succBlock2);
			switchInst1.addCase(caseVal3, succBlock3);
			switchInst1.addCase(caseVal4, succBlock4);
		}
		catch(InstructionUpdateException ice){  }
		assertTrue(switchInst1.getNumCases() == 5);
		
		String scrubbedOutput = switchInst1.toString().replaceAll("\n", "");
		assertTrue(scrubbedOutput.equals(
		"switch i32 %shape, label %sphere [    i32 5, label %box    i32 6, label %cube    i32 7, label %rhomboid    i32 8, label %pyramid   ]"));
		
		// Look for a null value
		int foundIndex = -1; 
		try{
			foundIndex = switchInst1.findCaseValue(null);
		}
		catch(InstructionDetailAccessException idae){ }
		assertTrue(foundIndex == 0);
		
		// Look for a valid value with the first index
		foundIndex = -1; 
		try{
			foundIndex = switchInst1.findCaseValue(caseVal1);
		}
		catch(InstructionDetailAccessException idae){ }
		assertTrue(foundIndex == 1);
		
		// Look for a valid value with the last index
		foundIndex = -1; 
		try{
			foundIndex = switchInst1.findCaseValue(caseVal4);
		}
		catch(InstructionDetailAccessException idae){ }
		assertTrue(foundIndex == 4);
		
		// Look for a valid value with some constint in the middle
		foundIndex = -1; 
		try{
			foundIndex = switchInst1.findCaseValue(caseVal3);
		}
		catch(InstructionDetailAccessException idae){ }
		assertTrue(foundIndex == 3);
		
		// Look for a valid value with some constint that is not in the switch instruction
		ConstantInt caseVal5 = null;
		try {
			caseVal5 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "9", true));
		} catch (InstantiationException e) {}
		assertNotNull(caseVal5);
		foundIndex = -1; 
		try{
			foundIndex = switchInst1.findCaseValue(caseVal5);
		}
		catch(InstructionDetailAccessException idae){ }
		assertTrue(foundIndex == 0);
	}
	
	@Test
	public void testSwitchUpdateForFindingCaseDest(){
		Value switchOn1 = new Value(Type.getInt32Type(compilationContext, true));
		switchOn1.setName("shape");
		
		BasicBlock defaultBB2 = new BasicBlock(compilationContext);
		defaultBB2.setName("sphere");
		SwitchInst switchInst1 = null;
		try{
			switchInst1 = SwitchInst.create(switchOn1, defaultBB2, null);
		}
		catch(InstructionCreationException ice){}
		assertNotNull(switchInst1);
		ConstantInt caseVal1 = null;
		try {
			caseVal1 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "5", true));
		} catch (InstantiationException e) {}
		assertNotNull(caseVal1);
		
		BasicBlock succBlock1 = new BasicBlock(compilationContext);
		succBlock1.setName("box");
		ConstantInt caseVal2 = null;
		try {
			caseVal2 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "6", true));
		} catch (InstantiationException e) {}
		assertNotNull(caseVal2);
		
		BasicBlock succBlock2 = new BasicBlock(compilationContext);
		succBlock2.setName("cube");
		ConstantInt caseVal3 = null;
		try {
			caseVal3 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "7", true));
		} catch (InstantiationException e) {}
		assertNotNull(caseVal3);
		BasicBlock succBlock3 = new BasicBlock(compilationContext);
		succBlock3.setName("rhomboid");
		ConstantInt caseVal4 = null;
		try {
			caseVal4 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "8", true));
		} catch (InstantiationException e) {}
		assertNotNull(caseVal4);
		
		BasicBlock succBlock4 = new BasicBlock(compilationContext);
		succBlock4.setName("pyramid");
		
		// Create a constInt that leads to basic block 2
		ConstantInt caseVal5 = null;
		try {
			caseVal5 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "9", true));
		} catch (InstantiationException e) {}
		assertNotNull(caseVal5);
		
		try{
			switchInst1.addCase(caseVal1, succBlock1);
			switchInst1.addCase(caseVal2, succBlock2);
			switchInst1.addCase(caseVal3, succBlock3);
			switchInst1.addCase(caseVal4, succBlock4);
			switchInst1.addCase(caseVal5, succBlock2);
		}
		catch(InstructionUpdateException ice){  }
		assertTrue(switchInst1.getNumCases() == 6);
		

		String scrubbedOutput = switchInst1.toString().replaceAll("\n", "");
		assertTrue(scrubbedOutput.equals(
		"switch i32 %shape, label %sphere [    i32 5, label %box    i32 6, label %cube    i32 7, label %rhomboid    i32 8, label %pyramid    i32 9, label %cube   ]"));
		
		// Look for a basic block that is unique
		ConstantInt uniqueConstInt = null; 
		try{
			uniqueConstInt = switchInst1.findCaseDest(succBlock1);
		}
		catch(InstructionDetailAccessException idae){ }
		assertNotNull(uniqueConstInt);
		assertTrue(uniqueConstInt.toString().equals("i32 5"));
		
		// ... and another 
		uniqueConstInt = null; 
		try{
			uniqueConstInt = switchInst1.findCaseDest(succBlock3);
		}
		catch(InstructionDetailAccessException idae){ }
		assertNotNull(uniqueConstInt);
		assertTrue(uniqueConstInt.toString().equals("i32 7"));
		
		// ... and yet another 
		uniqueConstInt = null; 
		try{
			uniqueConstInt = switchInst1.findCaseDest(succBlock4);
		}
		catch(InstructionDetailAccessException idae){ }
		assertNotNull(uniqueConstInt);
		assertTrue(uniqueConstInt.toString().equals("i32 8"));
		
		// ... and the default basic block
		uniqueConstInt = null; 
		try{
			uniqueConstInt = switchInst1.findCaseDest(defaultBB2);
		}
		catch(InstructionDetailAccessException idae){ }
		assertNull(uniqueConstInt);
		
		// ... and with a basic block not part of the switch instruction
		BasicBlock succBlock5 = new BasicBlock(compilationContext);
		succBlock5.setName("ovoid");
		uniqueConstInt = null; 
		try{
			uniqueConstInt = switchInst1.findCaseDest(succBlock5);
			
		}
		catch(InstructionDetailAccessException idae){ }
		assertNull(uniqueConstInt);
		
		// ... and with a basic block that is not unique for cases
		uniqueConstInt = null; 
		try{
			uniqueConstInt = switchInst1.findCaseDest(succBlock2);
			
		}
		catch(InstructionDetailAccessException idae){ }
		assertNull(uniqueConstInt);
	}
}

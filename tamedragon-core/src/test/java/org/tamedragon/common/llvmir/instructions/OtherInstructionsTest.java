package org.tamedragon.common.llvmir.instructions;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.tamedragon.common.llvmir.instructions.CFG;
import org.tamedragon.common.llvmir.instructions.CallInst;
import org.tamedragon.common.llvmir.instructions.CallingConv;
import org.tamedragon.common.llvmir.instructions.FCmpInst;
import org.tamedragon.common.llvmir.instructions.ICmpInst;
import org.tamedragon.common.llvmir.instructions.PhiNode;
import org.tamedragon.common.llvmir.instructions.SelectInst;
import org.tamedragon.common.llvmir.instructions.CmpInst.Predicate;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionCreationException;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionUpdateException;
import org.tamedragon.common.llvmir.math.APInt;
import org.tamedragon.common.llvmir.types.APFloat;
import org.tamedragon.common.llvmir.types.ArrayType;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.CompilationContext;
import org.tamedragon.common.llvmir.types.ConstantFP;
import org.tamedragon.common.llvmir.types.ConstantInt;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.Module;
import org.tamedragon.common.llvmir.types.PointerType;
import org.tamedragon.common.llvmir.types.Type;
import org.tamedragon.common.llvmir.types.Value;
import org.tamedragon.common.llvmir.types.VectorType;
import org.tamedragon.common.llvmir.types.exceptions.TypeCreationException;

public class OtherInstructionsTest {

	private CompilationContext compilationContext;

	@Before
	public void setUp(){
		compilationContext = new CompilationContext();
	}

	public Function createSampleFunction(){
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
		return Function.create(module, ptrToFunctype, null, CallingConv.C, "foo", cfg);
		
	}
	
	//*********************************************************************************************************************
	//***************************************************** PHI NODE INSTRUCTION TESTS *************************************
	//*********************************************************************************************************************
	@Test
	public void testValidPhiInstrsCreation(){

		PhiNode phiNode1 = null;

		Type type = Type.getInt32Type(compilationContext, true);
		phiNode1 = PhiNode.create(type, "x", 3, null, null);

		assertNotNull(phiNode1);
		String desc = phiNode1.emit();
		assertTrue(desc.equals("%x = phi i32 []"));

		// Add one operand
		String errMsg = "";
		try{
			ConstantInt ci0 = null;
			try {
				ci0 = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "0", false));
			} catch (InstantiationException e) {}
			assertNotNull(ci0);
			BasicBlock bb0 = new BasicBlock(createSampleFunction());
			bb0.setName("LoopHeader");

			phiNode1.addIncoming(ci0, bb0);
		}
		catch(InstructionUpdateException iue) {errMsg = iue.getMessage(); }

		assertTrue(errMsg.equals(""));
		desc = phiNode1.emit();
		assertTrue(desc.equals("%x = phi i32 [ 0, %LoopHeader ]"));

		// Add two operands
		Type typeI8 = Type.getInt8Type(compilationContext, true);
		PhiNode phiNode2 = PhiNode.create(typeI8, "12", 3, null, null);
		try{
			ConstantInt ci0 = null;
			try {
				ci0 = new ConstantInt(Type.getInt8Type(compilationContext, true), new APInt(8, "3", true));
			} catch (InstantiationException e) {}
			assertNotNull(ci0);
			
			BasicBlock bb0 = new BasicBlock(createSampleFunction());
			bb0.setName("LoopHeader");

			Value v1 = new Value(typeI8);
			v1.setName("nextindvar");
			BasicBlock bb1 = new BasicBlock(createSampleFunction());
			bb1.setName("Loop");

			phiNode2.addIncoming(ci0, bb0);
			phiNode2.addIncoming(v1, bb1);
		}
		catch(InstructionUpdateException iue) {errMsg = iue.getMessage(); }

		assertTrue(errMsg.equals(""));
		desc = phiNode2.emit();
		assertTrue(desc.equals("%12 = phi i8 [ 3, %LoopHeader ], [ %nextindvar, %Loop ]"));
	}

	@Test
	public void testInValidPhiInstrsUpdateWrongIndex(){

		PhiNode phiNode1 = null;

		Type type = Type.getInt32Type(compilationContext, true);
		phiNode1 = PhiNode.create(type, "x", 3, null, null);

		assertNotNull(phiNode1);
		String desc = phiNode1.emit();
		assertTrue(desc.equals("%x = phi i32 []"));

		// Add one operand
		String errMsg = "";
		try{
			Value v0 = new Value(Type.getInt32Type(compilationContext, true));
			v0.setName("23");
			
			BasicBlock bb0 = new BasicBlock(createSampleFunction());
			bb0.setName("LoopHeader");

			phiNode1.addIncoming(v0, bb0);
		}
		catch(InstructionUpdateException iue) {errMsg = iue.getMessage(); }

		assertTrue(errMsg.equals(""));
		desc = phiNode1.emit();
		assertTrue(desc.equals("%x = phi i32 [ %23, %LoopHeader ]"));
	}

	// *************************************************************************************************************
	// *********************** TESTS FOR ICMP INSTRUCTIONS *********************************************************
	// *************************************************************************************************************

	// Confirm that all "normal" icmp instructions are created correctly.
	@Test
	public void testICmpCreation() {
		// lhs variable , rhs constant
		Predicate predicate = Predicate.ICMP_EQ;
		Value lhs = new Value(Type.getInt32Type(compilationContext, true));
		lhs.setName("x");
		APInt apInt = new APInt(32, "4", true);
		ConstantInt constRhs = null;
		try {
			constRhs = new ConstantInt(Type.getInt32Type(compilationContext, true), apInt);
		} catch (InstantiationException e2) {}
		String name = "result";
		ICmpInst iCmpInst = null;
		try{
			iCmpInst = ICmpInst.create(predicate, lhs, constRhs, name, null);
		}
		catch(InstructionCreationException ice){}
		assertNotNull(iCmpInst);
		assertTrue(iCmpInst.emit().equals("%result = icmp eq i32 %x, 4"));

		// lhs constant , rhs variable
		ConstantInt constLhs = null;
		try {
			constLhs = new ConstantInt(Type.getInt32Type(compilationContext, true), apInt);
		} catch (InstantiationException e1) {}
		assertNotNull(constLhs);
		Value rhs = new Value(Type.getInt32Type(compilationContext, true));
		rhs.setName("y");
		iCmpInst = null;
		try{
			iCmpInst = ICmpInst.create(predicate, constLhs, rhs, name, null);
		}
		catch(InstructionCreationException ice){}
		assertNotNull(iCmpInst);
		assertTrue(iCmpInst.emit().equals("%result = icmp eq i32 4, %y"));

		// lhs constant , rhs constant
		iCmpInst = null;
		try{
			iCmpInst = ICmpInst.create(predicate, constLhs, constRhs, name, null);
		}
		catch(InstructionCreationException ice){}
		assertNotNull(iCmpInst);
		assertTrue(iCmpInst.emit().equals("%result = icmp eq i32 4, 4"));

		// lhs variable , rhs variable
		iCmpInst = null;
		try{
			iCmpInst = ICmpInst.create(predicate, lhs, rhs, name, null);
		}
		catch(InstructionCreationException ice){}
		assertNotNull(iCmpInst);
		assertTrue(iCmpInst.emit().equals("%result = icmp eq i32 %x, %y"));

		PointerType pointerType = null;
		try {
			pointerType = Type.getPointerType(compilationContext, Type.getInt32Type(compilationContext, true), 0);
		} catch (TypeCreationException e1) {}
		assertNotNull(pointerType);
		lhs = new Value(pointerType);
		lhs.setName("x");
		pointerType = null;
		try {
			pointerType = Type.getPointerType(compilationContext, Type.getInt32Type(compilationContext, true), 0);
		} catch (TypeCreationException e1) {}
		assertNotNull(pointerType);
		rhs = new Value(pointerType);
		rhs.setName("y");
		iCmpInst = null;
		try{
			iCmpInst = ICmpInst.create(predicate, lhs, rhs, name, null);
		}
		catch(InstructionCreationException ice){}
		assertNotNull(iCmpInst);
		assertTrue(iCmpInst.emit().equals("%result = icmp eq i32* %x, %y"));

		VectorType vectorType = null;
		try {
			vectorType = Type.getVectorType(compilationContext, Type.getInt32Type(compilationContext, true), 4);
		} catch (TypeCreationException e1) {}
		assertNotNull(vectorType);
		lhs = new Value(vectorType);
		lhs.setName("x");
		vectorType = null;
		try {
			vectorType = Type.getVectorType(compilationContext, Type.getInt32Type(compilationContext, true), 4);
		} catch (TypeCreationException e1) {}
		assertNotNull(vectorType);
		rhs = new Value(vectorType);
		rhs.setName("y");
		iCmpInst = null;
		try{
			iCmpInst = ICmpInst.create(predicate, lhs, rhs, name, null);
		}
		catch(InstructionCreationException ice){}
		assertNotNull(iCmpInst);
		assertTrue(iCmpInst.emit().equals("%result = icmp eq <4 x i32> %x, %y"));
	}
	// Confirm that all "invalid" icmp instructions throw expected exceptions.
	@Test
	public void testInvalidICmpCreation() {
		Predicate predicate = Predicate.FCMP_FALSE;
		Value lhs = new Value(Type.getInt32Type(compilationContext, true));
		lhs.setName("x");
		APInt apInt = new APInt(32, "4", true);
		String name = "%result";
		String errMsg = "";
		ConstantInt constRhs = null;
		try {
			constRhs = new ConstantInt(Type.getInt32Type(compilationContext, true), apInt);
		} catch (InstantiationException e2) {}
		assertNotNull(constRhs);
		ICmpInst iCmpInst = null;
		try{
			iCmpInst = ICmpInst.create(predicate, lhs, constRhs, name, null);
		}
		catch(InstructionCreationException ice){
			errMsg = ice.getMessage();
		}
		assertTrue(errMsg.equals(InstructionCreationException.INVALID_PREDICATE_VALUE_FOR__ICMP_INSTR));

		predicate = Predicate.ICMP_EQ;
		lhs = new Value(Type.getDoubleType(compilationContext));
		lhs.setName("x");
		errMsg = "";
		iCmpInst = null;
		try{
			iCmpInst = ICmpInst.create(predicate, lhs, constRhs, name, null);
		}
		catch(InstructionCreationException ice){
			errMsg = ice.getMessage();
		}
		assertTrue(errMsg.equals(InstructionCreationException.INVALID_TYPE_OF_VALUES_FOR_ICMP_INSTR));
	}

	// *************************************************************************************************************
	// *********************** TESTS FOR FCMP INSTRUCTIONS *********************************************************
	// *************************************************************************************************************

	// Confirm that all "normal" fcmp instructions are created correctly.
	@Test
	public void testFCmpCreation() {
		// lhs variable , rhs constant
		Predicate predicate = Predicate.FCMP_OEQ;
		Value lhs = new Value(Type.getDoubleType(compilationContext));
		lhs.setName("x");
		APFloat apFloat = new APFloat(APFloat.IEEEdouble, "10.56");
		ConstantFP constRhs = null;
		try {
			constRhs = new ConstantFP(Type.getDoubleType(compilationContext), apFloat);
		} catch (InstantiationException e2) {}
		String name = "result";
		FCmpInst fCmpInst = null;
		try{
			fCmpInst = FCmpInst.create(predicate, lhs, constRhs, name, null);
		}
		catch(InstructionCreationException ice){ice.printStackTrace();}
		assertNotNull(fCmpInst);
		String fCmpInstStr = fCmpInst.emit();
		assertTrue(fCmpInstStr.equals("%result = fcmp oeq double %x, 1.056000e+01"));

		// lhs constant , rhs variable
		ConstantFP constLhs = null;
		try {
			constLhs = new ConstantFP(Type.getDoubleType(compilationContext), apFloat);
		} catch (InstantiationException e1) {}
		assertNotNull(constLhs);
		Value rhs = new Value(Type.getDoubleType(compilationContext));
		rhs.setName("y");
		fCmpInst = null;
		try{
			fCmpInst = FCmpInst.create(predicate, constLhs, rhs, name, null);
		}
		catch(InstructionCreationException ice){}
		assertNotNull(fCmpInst);
		fCmpInstStr = fCmpInst.emit();
		assertTrue(fCmpInstStr.equals("%result = fcmp oeq double 1.056000e+01, %y"));

		// lhs constant , rhs constant
		fCmpInst = null;
		try{
			fCmpInst = FCmpInst.create(predicate, constLhs, constRhs, name, null);
		}
		catch(InstructionCreationException ice){}
		assertNotNull(fCmpInst);
		assertTrue(fCmpInst.emit().equals("%result = fcmp oeq double 1.056000e+01, 1.056000e+01"));

		// lhs variable , rhs variable
		fCmpInst = null;
		try{
			fCmpInst = FCmpInst.create(predicate, lhs, rhs, name, null);
		}
		catch(InstructionCreationException ice){}
		assertNotNull(fCmpInst);
		fCmpInstStr = fCmpInst.emit();
		assertTrue(fCmpInstStr.equals("%result = fcmp oeq double %x, %y"));

		VectorType vectorType = null;
		try {
			vectorType = Type.getVectorType(compilationContext, Type.getDoubleType(compilationContext), 4);
		} catch (TypeCreationException e1) {}
		assertNotNull(vectorType);
		lhs = new Value(vectorType);
		lhs.setName("x");
		vectorType = null;
		try {
			vectorType = Type.getVectorType(compilationContext, Type.getDoubleType(compilationContext), 4);
		} catch (TypeCreationException e1) {}
		assertNotNull(vectorType);
		rhs = new Value(vectorType);
		rhs.setName("y");
		fCmpInst = null;
		try{
			fCmpInst = FCmpInst.create(predicate, lhs, rhs, name, null);
		}
		catch(InstructionCreationException ice){}
		assertNotNull(fCmpInst);
		fCmpInstStr = fCmpInst.emit();
		assertTrue(fCmpInstStr.equals("%result = fcmp oeq <4 x double> %x, %y"));
	}
	// Confirm that all "invalid" fcmp instructions throw expected exceptions.
	@Test
	public void testInvalidFCmpCreation() {
		Predicate predicate = Predicate.ICMP_EQ;
		Value lhs = new Value(Type.getInt32Type(compilationContext, true));
		lhs.setName("x");
		APFloat apFloat = new APFloat(APFloat.IEEEdouble, "10.56");
		String name = "result";
		String errMsg = "";
		ConstantFP constRhs = null;
		try {
			constRhs = new ConstantFP(Type.getInt32Type(compilationContext, true), apFloat);
		} catch (InstantiationException e2) {}
		assertNotNull(constRhs);
		FCmpInst fCmpInst = null;
		try{
			fCmpInst = FCmpInst.create(predicate, lhs, constRhs, name, null);
		}
		catch(InstructionCreationException ice){
			errMsg = ice.getMessage();
		}
		assertTrue(errMsg.equals(InstructionCreationException.INVALID_PREDICATE_VALUE_FOR__FCMP_INSTR));

		predicate = Predicate.FCMP_OEQ;
		lhs = new Value(Type.getInt32Type(compilationContext, true));
		lhs.setName("x");
		errMsg = "";
		fCmpInst = null;
		try{
			fCmpInst = FCmpInst.create(predicate, lhs, constRhs, name, null);
		}
		catch(InstructionCreationException ice){
			errMsg = ice.getMessage();
		}
		assertTrue(errMsg.equals(InstructionCreationException.INVALID_TYPE_OF_VALUES_FOR_FCMP_INSTR));
	}

	// *************************************************************************************************************
	// *********************** TESTS FOR SELECT INSTRUCTION ********************************************************
	// *************************************************************************************************************


	@Test
	public void testSelectInstCreation() {

		// Confirm that all "normal" select instructions are created correctly.
		Type conditionType = Type.getInt1Type(compilationContext, false);
		Type trueSelectionType = Type.getInt32Type(compilationContext, true);
		Type falseSelectionType = Type.getInt32Type(compilationContext, true);

		Value conditionVal = new Value(conditionType); conditionVal.setName("78");
		Value trueVal = new Value(trueSelectionType); trueVal.setName("3");
		Value falseVal = new Value(falseSelectionType); falseVal.setName("4");

		SelectInst selectInst = null;

		try {
			selectInst = SelectInst.create(conditionVal, trueVal, falseVal, "2", null);
		} 
		catch (InstructionCreationException e) {
			fail("Function type could not be created");
		}

		assertNotNull(selectInst);
		String selectInstStr = selectInst.emit();
		assertTrue(selectInstStr.equals("%2 = select i1 %78, i32 %3, i32 %4"));

		// Create an invalid select instruction with condition type that is not i1.
		selectInst = null;
		conditionVal = new Value(Type.getFloatType(compilationContext));
		String errMessage = "";
		trueVal = new Value(Type.getDoubleType(compilationContext)); trueVal.setName("m");
		falseVal = new Value(Type.getDoubleType(compilationContext)); falseVal.setName("n");
		try{
			selectInst = SelectInst.create(conditionVal, trueVal, falseVal, "2", null);
		}
		catch (InstructionCreationException e) {
			errMessage = e.getMessage();
		}

		assertTrue(errMessage.equals(InstructionCreationException.INVALID_CONDITION_TYPE_FOR_SELECT_INSTR));

		// Create an invalid select instruction with a null condition value.
		selectInst = null;
		conditionVal = null;

		try{
			selectInst = SelectInst.create(conditionVal, trueVal, falseVal, "2", null);
		}
		catch (InstructionCreationException e) {
			errMessage = e.getMessage();
		}

		assertTrue(errMessage.equals(InstructionCreationException.CONDITION_CODE_CANNOT_BE_NULL));

		// Create an invalid select instruction with null selection value for the true part.
		selectInst = null;
		conditionVal = new Value(Type.getInt1Type(compilationContext, false));
		trueVal = null;
		try{
			selectInst = SelectInst.create(conditionVal, trueVal, falseVal, "2", null);
		}
		catch (InstructionCreationException e) {
			errMessage = e.getMessage();
		}

		assertTrue(errMessage.equals(InstructionCreationException.SELECTION_VALUES_CANNOT_BE_NULL));

		// Create an invalid select instruction with null selection value for the false part.
		selectInst = null;
		conditionVal = new Value(Type.getInt1Type(compilationContext, false));
		trueVal = new Value(Type.getDoubleType(compilationContext)); trueVal.setName("m");
		falseVal = null;
		try{
			selectInst = SelectInst.create(conditionVal, trueVal, falseVal, "2", null);
		}
		catch (InstructionCreationException e) {
			errMessage = e.getMessage();
		}

		assertTrue(errMessage.equals(InstructionCreationException.SELECTION_VALUES_CANNOT_BE_NULL));

		// Create an invalid select instruction with different types for the selection values.
		selectInst = null;
		conditionVal = new Value(Type.getInt1Type(compilationContext, false));
		trueVal = new Value(Type.getDoubleType(compilationContext)); trueVal.setName("m");
		falseVal = new Value(Type.getFloatType(compilationContext)); falseVal.setName("n");
		try{
			selectInst = SelectInst.create(conditionVal, trueVal, falseVal, "2", null);
		}
		catch (InstructionCreationException e) {
			errMessage = e.getMessage();
		}

		assertTrue(errMessage.equals(InstructionCreationException.TYPE_MISMATCH_IN_SELECTION_INSTR));

	}

	@Test
	public void testSelectInstUpdate() {

		// Confirm that all "normal" select instructions are updated correctly.
		Type conditionType = Type.getInt1Type(compilationContext, false);
		Type trueSelectionType = Type.getInt32Type(compilationContext, true);
		Type falseSelectionType = Type.getInt32Type(compilationContext, true);

		Value conditionVal = new Value(conditionType); conditionVal.setName("78");
		Value trueVal = new Value(trueSelectionType); trueVal.setName("3");
		Value falseVal = new Value(falseSelectionType); falseVal.setName("4");

		SelectInst selectInst = null;

		try {
			selectInst = SelectInst.create(conditionVal, trueVal, falseVal, "2", null);
		} 
		catch (InstructionCreationException e) {
			fail("Function type could not be created");
		}

		assertNotNull(selectInst);
		String selectInstStr = selectInst.emit();
		assertTrue(selectInstStr.equals("%2 = select i1 %78, i32 %3, i32 %4"));

		// Update with valid condition semantics
		Value newConditionVal = new Value(Type.getInt1Type(compilationContext, false));
		newConditionVal.setName("90");
		boolean isUpdateError = true;
		try{
			selectInst.setCondition(newConditionVal);
			isUpdateError = false;
		}
		catch(InstructionUpdateException iue){}
		assertTrue(!isUpdateError);

		assertTrue(selectInst.emit().equals("%2 = select i1 %90, i32 %3, i32 %4"));

		// Update with valid true selection semantics
		Value newTrueValue = new Value(Type.getInt32Type(compilationContext, false));
		newTrueValue.setName("56");
		isUpdateError = true;
		try{
			selectInst.setSelectionValue(newTrueValue, true);
			isUpdateError = false;
		}
		catch(InstructionUpdateException iue){}
		assertTrue(!isUpdateError);
		selectInstStr = selectInst.emit();
		assertTrue(selectInstStr.equals("%2 = select i1 %90, i32 %56, i32 %4"));

		// Update with valid false selection semantics
		Value newFalseValue = new Value(Type.getInt32Type(compilationContext, false));
		newFalseValue.setName("jk");
		isUpdateError = true;
		try{
			selectInst.setSelectionValue(newFalseValue, false);
			isUpdateError = false;
		}
		catch(InstructionUpdateException iue){}
		assertTrue(!isUpdateError);
		assertTrue(selectInst.emit().equals("%2 = select i1 %90, i32 %56, i32 %jk"));

		// Update with null condition value
		Value newConditionValue = null;
		String errMessage = "";
		try{
			selectInst.setCondition(newConditionValue);
		}
		catch(InstructionUpdateException iue){
			errMessage = iue.getMessage();
		}
		assertTrue(errMessage.equals(InstructionUpdateException.CONDITION_TYPE_CANNOT_BE_NULL_FOR_SELECT));

		// Update with invalid condition value
		newConditionValue = new Value(Type.getFloatType(compilationContext));
		errMessage = "";
		try{
			selectInst.setCondition(newConditionValue);
		}
		catch(InstructionUpdateException iue){
			errMessage = iue.getMessage();
		}
		assertTrue(errMessage.equals(InstructionUpdateException.CONDITION_TYPE_NOT_I1_FOR_SELECT));

		// Update with null selection value for "true" 
		newConditionValue = new Value(Type.getInt1Type(compilationContext, false));
		errMessage = "";
		Value newTrueVal = null;
		try{
			selectInst.setSelectionValue(newTrueVal, true);
		}
		catch(InstructionUpdateException iue){
			errMessage = iue.getMessage();
		}
		assertTrue(errMessage.equals(InstructionUpdateException.SELECT_VALUE_CANNOT_BE_NULL_FOR_SELECT));

		// Update with null selection value for "false" 
		errMessage = "";
		Value newFalseVal = null;
		try{
			selectInst.setSelectionValue(newFalseVal, false);
		}
		catch(InstructionUpdateException iue){
			errMessage = iue.getMessage();
		}
		assertTrue(errMessage.equals(InstructionUpdateException.SELECT_VALUE_CANNOT_BE_NULL_FOR_SELECT));

		// Update with incompatible selection value for "true" 
		errMessage = "";
		newTrueVal = new Value(Type.getDoubleType(compilationContext));
		try{
			selectInst.setSelectionValue(newTrueVal, true);
		}
		catch(InstructionUpdateException iue){
			errMessage = iue.getMessage();
		}
		assertTrue(errMessage.equals(InstructionUpdateException.INCOMPATIBLE_SELECT_TYPE_FOR_SELECT));

		// Update with incompatible selection value for "false" 
		errMessage = "";
		newFalseVal = new Value(Type.getInt16Type(compilationContext, false));
		try{
			selectInst.setSelectionValue(newFalseVal, false);
		}
		catch(InstructionUpdateException iue){
			errMessage = iue.getMessage();
		}
		assertTrue(errMessage.equals(InstructionUpdateException.INCOMPATIBLE_SELECT_TYPE_FOR_SELECT));
	}

	// *************************************************************************************************************
	// *********************** TESTS FOR CALL INSTRUCTIONS *********************************************************
	// *************************************************************************************************************


	// Confirm that all "normal" call instructions are created correctly.
	@Test
	public void testCallCreation() {
		//creating a normal call instruction, with arguments
		Vector<Type> paramTypes = new Vector<Type>();
		paramTypes.add(Type.getDoubleType(compilationContext));
		paramTypes.add(Type.getDoubleType(compilationContext));
		Type funcType = null;
		PointerType ptrToFunctype = null;
		try {
			funcType = Type.getFunctionType(compilationContext,
					Type.getInt32Type(compilationContext, true), false, paramTypes );
			ptrToFunctype = Type.getPointerType(compilationContext, funcType, 0);
		} catch (TypeCreationException e) {fail("Function type could not be created");}
		
		Module module = new Module("testCallCreation", compilationContext, null);
		CFG cfg = new CFG();
		Function funcValue = Function.create(module, ptrToFunctype, null, CallingConv.C, "foo", cfg);
		List<Value> args = new ArrayList<Value>();
		Value v1 = new Value(Type.getDoubleType(compilationContext));
		v1.setName("arg1");
		args.add(v1);
		APFloat apFloat = new APFloat(APFloat.IEEEdouble, "10.50");
		ConstantFP constantFP = null;
		try {
			constantFP = new ConstantFP(Type.getDoubleType(compilationContext), apFloat);
		} catch (InstantiationException e) {}
		assertNotNull(constantFP);
		args.add(constantFP);
		String name = "result";
		boolean isTailCall = false;
		CallInst callInst = null;
		try{
			callInst = CallInst.create(funcValue, args, name, isTailCall, false, null);
		}
		catch(InstructionCreationException ice){}
		assertNotNull(callInst);
		assertTrue(callInst.emit().equals("%result = call i32 @foo(double %arg1, double 1.050000e+01)"));

		//creating a normal call instruction, without arguments
		funcType = null;
		try {
			funcType = Type.getFunctionType(compilationContext, Type.getInt32Type(compilationContext, true), false, null );
			ptrToFunctype = Type.getPointerType(compilationContext, funcType, 0);
		} 
		catch (TypeCreationException e) {
			fail("Function type could not be created");
		}

		funcValue = Function.create(module, ptrToFunctype, null, CallingConv.C, "foo", cfg);
		callInst = null;
		try{
			callInst = CallInst.create(funcValue, name, isTailCall, false, null);
		}
		catch(InstructionCreationException ice){}
		assertNotNull(callInst);
		assertTrue(callInst.emit().equals("%result = call i32 @foo()"));

		//creating a normal call instruction, with function pointer
		funcType = null;
		try {
			funcType = Type.getFunctionType(compilationContext, Type.getInt32Type(compilationContext, true), false, paramTypes );
			ptrToFunctype = Type.getPointerType(compilationContext, funcType, 0);
		} 
		catch (TypeCreationException e) {
			fail("Function type could not be created");
		}
		funcValue = Function.create(module, ptrToFunctype, null, CallingConv.C, "foo", cfg);
		callInst = null;
		try{
			callInst = CallInst.create(funcValue, args, name, isTailCall, false,  null);
		}
		catch(InstructionCreationException ice){}
		assertNotNull(callInst);
		String callInstStr = callInst.emit();
		assertTrue(callInstStr.equals("%result = call i32 @foo(double %arg1, double 1.050000e+01)"));

		//creating a normal call instruction, with var args in argument list
		funcType = null;
		try {
			funcType = Type.getFunctionType(compilationContext, Type.getInt32Type(compilationContext, true), true, paramTypes );
			ptrToFunctype = Type.getPointerType(compilationContext, funcType, 0);
		} 
		catch (TypeCreationException e) {
			fail("Function type could not be created");
		}

		funcValue = Function.create(module, ptrToFunctype, null, CallingConv.C, "foo", cfg);
		args = new ArrayList<Value>();
		v1 = new Value(Type.getDoubleType(compilationContext));
		v1.setName("arg1");
		args.add(v1);
		apFloat = new APFloat(APFloat.IEEEdouble, "10.50");
		constantFP = null;
		try {
			constantFP = new ConstantFP(Type.getDoubleType(compilationContext), apFloat);
		} 
		catch (InstantiationException e) {}

		assertNotNull(constantFP);
		args.add(constantFP);
		v1 = new Value(Type.getInt32Type(compilationContext, true));
		v1.setName("arg2");
		args.add(v1);

		callInst = null;
		try{
			callInst = CallInst.create(funcValue, args, name, isTailCall, false, null);
		}
		catch(InstructionCreationException ice){}
		assertNotNull(callInst);
		assertTrue(callInst.emit().equals("%result = call i32 (double, double, ...)* @foo(double %arg1, double 1.050000e+01, i32 %arg2)"));
	}

	// Confirm that all "invalid" call instructions throws proper messages
	@Test
	public void testInvalidCallCreation() {
		//creating a call instruction, where nos of params is not equal to nos of arguments
		Vector<Type> paramTypes = new Vector<Type>();
		paramTypes.add(Type.getDoubleType(compilationContext));
		Type funcType = null;
		PointerType ptrToFunctype = null;
		try {
			funcType = Type.getFunctionType(compilationContext, Type.getInt32Type(compilationContext, true), false, paramTypes );
			ptrToFunctype = Type.getPointerType(compilationContext, funcType, 0);
		} catch (TypeCreationException e) {
			fail("Function type could not be created");
		}

		Module module = new Module("testInvalidCallCreation", compilationContext, null);
		CFG cfg = new CFG();
		Function funcValue = Function.create(module, ptrToFunctype, null, CallingConv.C, "foo", cfg);
		List<Value> args = new ArrayList<Value>();
		Value v1 = new Value(Type.getDoubleType(compilationContext));
		v1.setName("arg1");
		args.add(v1);
		APFloat apFloat = new APFloat(APFloat.IEEEdouble, "10.50");
		ConstantFP constantFP = null;
		try {
			constantFP = new ConstantFP(Type.getDoubleType(compilationContext), apFloat);
		} catch (InstantiationException e) {}
		assertNotNull(constantFP);
		args.add(constantFP);
		String name = "result";
		boolean isTailCall = false;
		CallInst callInst = null;
		String errMsg = "";
		try{
			callInst = CallInst.create(funcValue, args, name, isTailCall, false, null);
		}
		catch(InstructionCreationException ice){
			errMsg = ice.getMessage();
		}
		assertTrue(errMsg.equals(InstructionCreationException.NOS_OF_ARGS_SHOULD_BE_EQUAL_TO_NOS_OF_PARAMS));

		//creating a call instruction, where param type is not equal to argument type
		paramTypes = new Vector<Type>();
		paramTypes.add(Type.getDoubleType(compilationContext));
		paramTypes.add(Type.getInt32Type(compilationContext, true));
		funcType = null;
		try {
			funcType = Type.getFunctionType(compilationContext, Type.getInt32Type(compilationContext, true), false, paramTypes );
			ptrToFunctype = Type.getPointerType(compilationContext, funcType, 0);
		} catch (TypeCreationException e) {
			fail("Function type could not be created");
		}
		funcValue = Function.create(module, ptrToFunctype, null, CallingConv.C, "foo", cfg);
		callInst = null;
		errMsg = "";
		try{
			callInst = CallInst.create(funcValue, args, name, isTailCall, false, null);
		}
		catch(InstructionCreationException ice){
			errMsg = ice.getMessage();
		}
		assertTrue(errMsg.equals(InstructionCreationException.FUNC_PARAM_TYPE_CANNOT_BE_DIFFERENT_THAN_ARG_TYPE));

		//creating a call instruction, with var args in argument list, but nos of aruments is less than parameters
		paramTypes = new Vector<Type>();
		paramTypes.add(Type.getDoubleType(compilationContext));
		paramTypes.add(Type.getDoubleType(compilationContext));
		funcType = null;
		try {
			funcType = Type.getFunctionType(compilationContext, Type.getInt32Type(compilationContext, true), true, paramTypes );
			ptrToFunctype = Type.getPointerType(compilationContext, funcType, 0);
		} catch (TypeCreationException e) {
			fail("Function type could not be created");
		}
		PointerType funcPointerType = null;
		try {
			funcPointerType = Type.getPointerType(compilationContext, funcType, 0);
		} catch (TypeCreationException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		funcValue = Function.create(module, ptrToFunctype, null, CallingConv.C, "foo", cfg);
		args = new ArrayList<Value>();
		v1 = new Value(Type.getDoubleType(compilationContext));
		v1.setName("arg1");
		args.add(v1);

		errMsg = "";

		callInst = null;
		try{
			callInst = CallInst.create(funcValue, args, name, isTailCall, false, null);
		}
		catch(InstructionCreationException ice){
			errMsg = ice.getMessage();
		}
		assertTrue(errMsg.equals(InstructionCreationException.NOS_OF_ARGS_CANNOT_BE_LESS_THAN_NOS_OF_PARAMS));
	}

	// Confirm that all "valid updates" for call instructions works correctly
	@Ignore
	@Test
	public void testCallInstrUpdates() {
		//creating a normal call instruction, with arguments, and functional attributes
		Vector<Type> paramTypes = new Vector<Type>();
		paramTypes.add(Type.getDoubleType(compilationContext));
		paramTypes.add(Type.getDoubleType(compilationContext));
		Type funcType = null;
		PointerType ptrToFunctype = null;
		try {
			funcType = Type.getFunctionType(compilationContext, Type.getInt32Type(compilationContext, true), false, paramTypes );
			ptrToFunctype = Type.getPointerType(compilationContext, funcType, 0);
		} catch (TypeCreationException e) {
			fail("Function type could not be created");
		}
		Module module = new Module("testCallInstrUpdates", compilationContext, null);
		CFG cfg = new CFG();
		Function funcValue = Function.create(module, ptrToFunctype, null, CallingConv.C, "foo", cfg);
		List<Value> args = new ArrayList<Value>();
		Value v1 = new Value(Type.getDoubleType(compilationContext));
		v1.setName("arg1");
		args.add(v1);
		APFloat apFloat = new APFloat(APFloat.IEEEdouble, "10.50");
		ConstantFP constantFP = null;
		try {
			constantFP = new ConstantFP(Type.getDoubleType(compilationContext), apFloat);
		} catch (InstantiationException e) {}
		assertNotNull(constantFP);
		args.add(constantFP);
		String name = "result";
		boolean isTailCall = false;
		CallInst callInst = null;
		try{
			callInst = CallInst.create(funcValue, args, name, isTailCall, false, null);
		}
		catch(InstructionCreationException ice){}
		assertNotNull(callInst);
		try {
			callInst.addFuncAttribute(4);
			callInst.addFuncAttribute(32);
		} catch (InstructionUpdateException e) {fail(e.getMessage());}
		assertTrue(callInst.emit().equals("%result = call i32 @foo(double %arg1, double 1.050000e+01) nounwind, noreturn"));

		//removing function attributes
		try {
			callInst.removeFuncAttribute(4);
		} catch (InstructionUpdateException e) {fail(e.getMessage());}
		assertTrue(callInst.emit().equals("%result = call i32 @foo(double %arg1, double 1.050000e+01) nounwind"));

		//creating a normal call instruction, with return attributes
		callInst = null;
		try{
			callInst = CallInst.create(funcValue, args, name, isTailCall, false, null);
		}
		catch(InstructionCreationException ice){}
		assertNotNull(callInst);
		try {
			callInst.addReturnAttributes(8);
			callInst.addReturnAttributes(2);
		} catch (InstructionUpdateException e) {fail(e.getMessage());}
		String callInstStr = callInst.emit();
		assertTrue(callInstStr.equals("%result = call signext,inreg i32 @foo(double %arg1, double 1.050000e+01)"));
	}

	// Confirm that all "valid updates" for call instructions works correctly
	@Ignore
	@Test
	public void testInvalidCallInstrUpdates() {
		//Invalid functional attribute for call instruction
		Vector<Type> paramTypes = new Vector<Type>();
		paramTypes.add(Type.getDoubleType(compilationContext));
		paramTypes.add(Type.getDoubleType(compilationContext));
		Type funcType = null;
		PointerType ptrToFunctype = null;
		try {
			funcType = Type.getFunctionType(compilationContext, Type.getInt32Type(compilationContext, true), false, paramTypes );
			ptrToFunctype = Type.getPointerType(compilationContext, funcType, 0);
		} 
		catch (TypeCreationException e) {
			fail("Function type could not be created");
		}
		Module module = new Module("testInvalidCallInstrUpdates", compilationContext, null);
		CFG cfg = new CFG();
		Function funcValue = Function.create(module, ptrToFunctype, null, CallingConv.C, "foo", cfg);
		List<Value> args = new ArrayList<Value>();
		Value v1 = new Value(Type.getDoubleType(compilationContext));
		v1.setName("arg1");
		args.add(v1);
		APFloat apFloat = new APFloat(APFloat.IEEEdouble, "10.50");
		ConstantFP constantFP = null;
		try {
			constantFP = new ConstantFP(Type.getDoubleType(compilationContext), apFloat);
		} catch (InstantiationException e) {}
		assertNotNull(constantFP);
		args.add(constantFP);
		String name = "result";
		boolean isTailCall = false;
		CallInst callInst = null;

		String errMsg = "";
		try{
			callInst = CallInst.create(funcValue, args, name, isTailCall, false, null);
		}
		catch(InstructionCreationException ice){}
		assertNotNull(callInst);
		try {
			callInst.addFuncAttribute(16);
		} catch (InstructionUpdateException e) {
			errMsg = e.getMessage();
		}
		assertTrue(errMsg.equals(InstructionUpdateException.INVALID_ATTR_FOR_FUNCTION_IN_CALL_INSTR));

		//updating function attribute, which is not a power of two
		callInst = null;

		errMsg = "";
		try{
			callInst = CallInst.create(funcValue, args, name, isTailCall, false, null);
		}
		catch(InstructionCreationException ice){}
		assertNotNull(callInst);
		try {
			callInst.addFuncAttribute(18);
		} catch (InstructionUpdateException e) {
			errMsg = e.getMessage();
		}
		assertTrue(errMsg.equals(InstructionUpdateException.ATTRIBUTE_IS_NOT_A_POWER_OF_TWO));

		//updating return attribute, which is not a power of two
		callInst = null;

		errMsg = "";
		try{
			callInst = CallInst.create(funcValue, args, name, isTailCall, false, null);
		}
		catch(InstructionCreationException ice){}
		assertNotNull(callInst);
		try {
			callInst.addReturnAttributes(18);
		} catch (InstructionUpdateException e) {
			errMsg = e.getMessage();
		}
		assertTrue(errMsg.equals(InstructionUpdateException.ATTRIBUTE_IS_NOT_A_POWER_OF_TWO));

		//updating return attribute, which is not a valid return attribute
		callInst = null;

		errMsg = "";
		try{
			callInst = CallInst.create(funcValue, args, name, isTailCall, false, null);
		}
		catch(InstructionCreationException ice){}
		assertNotNull(callInst);
		try {
			callInst.addReturnAttributes(4);
		} catch (InstructionUpdateException e) {
			errMsg = e.getMessage();
		}
		assertTrue(errMsg.equals(InstructionUpdateException.INVALID_ATTR_FOR_FUNCTION_RET_TYPE));

		//updating return attribute, for a function which returns void type
		funcType = null;
		try {
			funcType = Type.getFunctionType(compilationContext, Type.getVoidType(compilationContext), false, paramTypes );
			ptrToFunctype = Type.getPointerType(compilationContext, funcType, 0);
		} catch (TypeCreationException e) {
			fail("Function type could not be created");
		}

		funcValue = Function.create(module, ptrToFunctype, null, CallingConv.C, "foo", cfg);
		callInst = null;
		errMsg = "";

		try{
			callInst = CallInst.create(funcValue, args, name, isTailCall, false, null);
		}
		catch(InstructionCreationException ice){}
		assertNotNull(callInst);
		try {
			callInst.addReturnAttributes(4);
		} catch (InstructionUpdateException e) {
			errMsg = e.getMessage();
		}
		assertTrue(errMsg.equals(InstructionUpdateException.CANNOT_SET_A_RET_ATTR_TO_FUNC_RET_VOID));
	}
}
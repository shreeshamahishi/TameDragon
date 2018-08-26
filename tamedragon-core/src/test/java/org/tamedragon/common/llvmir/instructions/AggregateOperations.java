package org.tamedragon.common.llvmir.instructions;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionCreationException;
import org.tamedragon.common.llvmir.types.APFloat;
import org.tamedragon.common.llvmir.types.CompilationContext;
import org.tamedragon.common.llvmir.types.ConstantFP;
import org.tamedragon.common.llvmir.types.Type;
import org.tamedragon.common.llvmir.types.Value;
import org.tamedragon.common.llvmir.types.exceptions.TypeCreationException;

public class AggregateOperations {
	private CompilationContext compilationContext;

	@Before
	public void setUp(){
		compilationContext = new CompilationContext();
	}

	// *************************************************************************************************************
	// *********************** TESTS FOR EXTRACT VALUE INSTRUCTIONS
	// *******************************************************
	// *************************************************************************************************************
	
	// Confirm that all "normal" extractvalue instructions are created correctly.
	@Test
	public void createExtractValueInst(){
		// creating normal extractValue instructions, with structure type as first operand
		String name = "result";
		Type type = null;
		try {
			type = Type.getStructType(compilationContext, false, "pqr", false, Type.getDoubleType(compilationContext), Type.getInt32Type(compilationContext, true));
		} catch (TypeCreationException e) {}
		assertNotNull(type);
		Value firstOp = new Value(type);
		firstOp.setName("struct.pqr");

		ExtractValueInst extractValueInst = null;
		
		try{
			extractValueInst = ExtractValueInst.create(name, firstOp, null, 0);
		}catch (InstructionCreationException ice) {}
		assertNotNull(extractValueInst);
		String createdInsStr = extractValueInst.toString();
		assertTrue(createdInsStr.equals("%result = extractvalue %pqr %struct.pqr, 0"));
		
		// creating normal extractValue instructions, with Array type as first operand
		type = null;
		try {
			type = Type.getArrayType(compilationContext, Type.getInt32Type(compilationContext, true), 4);
		} catch (TypeCreationException e) {}
		assertNotNull(type);
		firstOp = new Value(type);
		firstOp.setName("intArr");
		
		extractValueInst = null;
		
		try{
			extractValueInst = ExtractValueInst.create(name, firstOp, null, 0);
		}catch (InstructionCreationException ice) {}
		assertNotNull(extractValueInst);
		assertTrue(extractValueInst.toString().equals("%result = extractvalue [4 x i32] %intArr, 0"));
		
		//creating a complex extractValue instruction, i.e trying to access a member of an array within a structure
		Type arrayType = null;
		type = null;
		try {
			arrayType = Type.getArrayType(compilationContext, Type.getInt32Type(compilationContext, true), 4);
			type = Type.getStructType(compilationContext, false, "pqr1", false, Type.getInt32Type(compilationContext, true), arrayType);
		} catch (TypeCreationException e) {}
		assertNotNull(arrayType);
		assertNotNull(type);
		
		firstOp = new Value(type);
		firstOp.setName("struct.pqr1");
		
		extractValueInst = null;
		
		try{
			extractValueInst = ExtractValueInst.create(name, firstOp, null, 1, 3);
		}catch(InstructionCreationException ice){
			ice.printStackTrace();
		}
		
		assertNotNull(extractValueInst);
		String extractValueInstStr = extractValueInst.toString();
		assertTrue(extractValueInstStr.equals("%result = extractvalue %pqr1 %struct.pqr1, 1, 3"));
	}
	
	// Confirm that all "invalid" extractvalue instructions throws expected exceptions
	@Test
	public void createInvalidExtractValueInst(){
		// creating invalid extractValue instructions, with structure type as first operand, and without a value name
		String name = "%result";
		Type type = null;
		try {
			type = Type.getStructType(compilationContext, false, "pqr", false, Type.getDoubleType(compilationContext), Type.getInt32Type(compilationContext, true));
		} catch (TypeCreationException e) {}
		assertNotNull(type);
		Value firstOp = new Value(type);
		firstOp.setName("");
		
		ExtractValueInst extractValueInst = null;
		
		String errMsg = "";
		
		try{
			extractValueInst = ExtractValueInst.create(name, firstOp, null, 0);
		}catch (InstructionCreationException ice) {
			errMsg = ice.getMessage();
		}
		
		assertTrue(errMsg.equals(InstructionCreationException.AGG_VALUE_NAME_CANNOT_BE_NULL_OR_EMPTY));
		
		//creating a extractvalue instruction, where index is out of bounds
		firstOp.setName("%struct.pqr");
		
		errMsg = "";
		
		extractValueInst = null;
		
		try{
			extractValueInst = ExtractValueInst.create(name, firstOp, null, 2);
		}catch (InstructionCreationException ice) {
			errMsg = ice.getMessage();
		}
		
		assertTrue(errMsg.equals(InstructionCreationException.INVALID_INDEX_FOR_EXTRACT_VAL_INSTR));
		
		//creating a extractvalue instruction where the first operand is not of aggregate type
		type = null;
		try {
			type = Type.getVectorType(compilationContext, Type.getInt32Type(compilationContext, true), 4);
		} catch (TypeCreationException e) {}
		assertNotNull(type);
		firstOp = new Value(type);
		firstOp.setName("struct.pqr");
		
		extractValueInst = null;
		
		try{
			extractValueInst = ExtractValueInst.create(name, firstOp, null, 0);
		}catch (InstructionCreationException ice) {
			errMsg = ice.getMessage();
		}
		
		assertTrue(errMsg.equals(InstructionCreationException.VALUE_SHOULD_BE_OF_AGGREGATE_TYPE));
		
		//check for at least one index
		type = null;
		try {
			type = Type.getStructType(compilationContext, false, "pqr", false, Type.getDoubleType(compilationContext), Type.getInt32Type(compilationContext, true));
		} catch (TypeCreationException e) {}
		assertNotNull(type);
		firstOp = new Value(type);
		firstOp.setName("struct.pqr");
		
		extractValueInst = null;
		
		try{
			extractValueInst = ExtractValueInst.create(name, firstOp, null);
		}catch (InstructionCreationException ice) {
			errMsg = ice.getMessage();
		}
		
		assertTrue(errMsg.equals(InstructionCreationException.ATLEAST_ONE_INDEX_SHOULD_BE_PROVIDED));
	}
	
	// *************************************************************************************************************
	// *********************** TESTS FOR INSERT VALUE INSTRUCTIONS
	// *******************************************************
	// *************************************************************************************************************
	
	// Confirm that all "normal" insertvalue instructions are created correctly.
	@Test
	public void createInsertValueInst(){
		//creating normal insert element instruction, with structure as aggregate type
		Type aggrType = null;
		try{
			aggrType = Type.getStructType(compilationContext, false, "struct.abc", false, Type.getDoubleType(compilationContext), Type.getInt32Type(compilationContext, true));
		   }
		catch(TypeCreationException tce){}
		assertNotNull(aggrType);
		Value firstOp = new Value(aggrType);
		firstOp.setName("struct.abc");
		
		Type eleType = Type.getDoubleType(compilationContext);
		Value secondOp = new Value(eleType);
		secondOp.setName("val");
		
		String name = "result";
		
		InsertValueInst insertValueInst = null;
		try{
			insertValueInst = InsertValueInst.create(firstOp, secondOp, name, null, 0);
		}
		catch(InstructionCreationException ice){
			ice.printStackTrace();
		}
		assertNotNull(insertValueInst);
		String insertValueInstStr = insertValueInst.toString();
		
		assertTrue(insertValueInstStr.equals("%result = insertvalue %struct.abc %struct.abc, double %val, 0"));
	
		//creating insertvalue instruction with Array as aggregate type
		aggrType = null;
		try{
			aggrType = Type.getArrayType(compilationContext, Type.getDoubleType(compilationContext), 4);
		   }
		catch(TypeCreationException tce){}
		assertNotNull(aggrType);
		firstOp = new Value(aggrType);
		firstOp.setName("intArr");
		
		insertValueInst = null;
		try{
			insertValueInst = InsertValueInst.create(firstOp, secondOp, name, null, 0);
		}
		catch(InstructionCreationException ice){}
		assertNotNull(insertValueInst);
		assertTrue(insertValueInst.toString().equals("%result = insertvalue [4 x double] %intArr, double %val, 0"));
		
		//creating insertvalue instruction with element as a constant value
		APFloat apFloat = new APFloat(APFloat.IEEEdouble, "10.0");
		ConstantFP constantFP = null;
		
		try{
			constantFP = new ConstantFP(Type.getDoubleType(compilationContext), apFloat);
		}
		catch(InstantiationException ice){}
		assertNotNull(constantFP);
		
		insertValueInst = null;
		try{
			insertValueInst = InsertValueInst.create(firstOp, constantFP, name, null, 0);
		}
		catch(InstructionCreationException ice){}
		assertNotNull(insertValueInst);
		assertTrue(insertValueInst.toString().equals("%result = insertvalue [4 x double] %intArr, double 1.000000e+01, 0"));
	}
	
	// Confirm that all "invalid" extractvalue instructions throws expected exceptions
	@Test
	public void createInvalidInsertValueInst(){
		//creating insertvalue instructions with non aggregate type value
		Type aggrType = null;
		try {
			aggrType = Type.getVectorType(compilationContext, Type.getDoubleType(compilationContext), 4);
		} catch (TypeCreationException e) {}
		assertNotNull(aggrType);
		Value firstOp = new Value(aggrType);
		firstOp.setName("%vector_pqr");
		
		Type eleType = Type.getDoubleType(compilationContext);
		Value secondOp = new Value(eleType);
		secondOp.setName("%val");
		
		String name = "%result";
		
		String errMsg = "";
		
		InsertValueInst insertValueInst = null;
		try{
			insertValueInst = InsertValueInst.create(firstOp, secondOp, name, null, 0);
		}
		catch(InstructionCreationException ice){
			errMsg = ice.getMessage();
		}
		assertTrue(errMsg.equals(InstructionCreationException.FIRST_OPERAND_TO_A_INSERTVALUE_INSTR_MUST_BE_A_AGGR_TYPE));
		
		//aggregate value must have a name
		aggrType = null;
		try {
			aggrType = Type.getStructType(compilationContext, false, "pqr", false, Type.getDoubleType(compilationContext), Type.getInt32Type(compilationContext, true));
		} catch (TypeCreationException e) {}
		assertNotNull(aggrType);
		firstOp = new Value(aggrType);
		firstOp.setName("");
		
		insertValueInst = null;
		try{
			insertValueInst = InsertValueInst.create(firstOp, secondOp, name, null, 0);
		}
		catch(InstructionCreationException ice){
			errMsg = ice.getMessage();
		}
		assertTrue(errMsg.equals(InstructionCreationException.AGG_VALUE_MUST_HAVE_A_NAME));
		
		//creating insertvalue instruction where inserted element type does not matches with the type of the value indexed
		firstOp = new Value(aggrType);
		firstOp.setName("pqr");
		
		eleType = Type.getInt16Type(compilationContext, true);
		secondOp = new Value(eleType);
		secondOp.setName("%val");
		
		errMsg = "";
		
		insertValueInst = null;
		try{
			insertValueInst = InsertValueInst.create(firstOp, secondOp, name, null, 0);
		}
		catch(InstructionCreationException ice){
			errMsg = ice.getMessage();
		}
		assertTrue(errMsg.equals(InstructionCreationException.INSERTED_VALUE_TYPE_MUST_MATCH_WITH_THE_TYPE_OF_VALUE_BEING_ASSIGNED));
		
		//creating insertvalue instruction without name of the aggregate type
		aggrType = null;
		try{
			aggrType = Type.getStructType(compilationContext, false, "pqr", false, Type.getDoubleType(compilationContext), Type.getInt32Type(compilationContext, true));
		   }
		catch(TypeCreationException tce){}
		assertNotNull(aggrType);
		firstOp = new Value(aggrType);
		firstOp.setName("pqr");
		
		eleType = Type.getInt32Type(compilationContext, true);
		secondOp = new Value(eleType);
		secondOp.setName("%val");
		
		errMsg = "";
		
		insertValueInst = null;
		try{
			insertValueInst = InsertValueInst.create(firstOp, secondOp, name, null, 0);
		}
		catch(InstructionCreationException ice){
			errMsg = ice.getMessage();
		}
		assertTrue(errMsg.equals(InstructionCreationException.INSERTED_VALUE_TYPE_MUST_MATCH_WITH_THE_TYPE_OF_VALUE_BEING_ASSIGNED));
		
		//creating insertvalue instruction where second operand doesn't have a value name in case it is a variable not a constant
		aggrType = null;
		try{
			aggrType = Type.getStructType(compilationContext, false, "pqr", false, Type.getDoubleType(compilationContext), Type.getInt32Type(compilationContext, true));
		   }
		catch(TypeCreationException tce){}
		assertNotNull(aggrType);
		firstOp = new Value(aggrType);
		firstOp.setName("%struct.pqr");
		
		eleType = Type.getInt32Type(compilationContext, true);
		secondOp = new Value(eleType);
		secondOp.setName("");
		
		errMsg = "";
		
		insertValueInst = null;
		try{
			insertValueInst = InsertValueInst.create(firstOp, secondOp, name, null, 1);
		}
		catch(InstructionCreationException ice){
			errMsg = ice.getMessage();
		}
		assertTrue(errMsg.equals(InstructionCreationException.INSERTED_VALUE_TYPE_MUST_HAVE_A_VALUE_NAME));
		
		//creating insertvalue instruction in which index is out of bounds
		aggrType = null;
		try{
			aggrType = Type.getStructType(compilationContext, false, "pqr", false, Type.getDoubleType(compilationContext), Type.getInt32Type(compilationContext, true));
		   }
		catch(TypeCreationException tce){}
		assertNotNull(aggrType);
		firstOp = new Value(aggrType);
		firstOp.setName("pqr");
		
		eleType = Type.getInt32Type(compilationContext, true);
		secondOp = new Value(eleType);
		secondOp.setName("%val");
		
		errMsg = "";
		
		insertValueInst = null;
		try{
			insertValueInst = InsertValueInst.create(firstOp, secondOp, name, null, 2);
		}
		catch(InstructionCreationException ice){
			errMsg = ice.getMessage();
		}
		assertTrue(errMsg.equals(InstructionCreationException.INDEX_IS_OUT_OF_BOUNDS));
		
		//creating insertvalue instruction in which aggregate type name and value name are not same
		aggrType = null;
		try{
			aggrType = Type.getStructType(compilationContext, false, "pqr", false, Type.getDoubleType(compilationContext), Type.getInt32Type(compilationContext, true));
		   }
		catch(TypeCreationException tce){}
		assertNotNull(aggrType);
		firstOp = new Value(aggrType);
		firstOp.setName("%struct.pqrst");
		
		eleType = Type.getInt32Type(compilationContext, true);
		secondOp = new Value(eleType);
		secondOp.setName("%val");
		
		errMsg = "";
		
		insertValueInst = null;
		try{
			insertValueInst = InsertValueInst.create(firstOp, secondOp, name, null, 1);
		}
		catch(InstructionCreationException ice){
			errMsg = ice.getMessage();
		}
		assertTrue(errMsg.equals(InstructionCreationException.TYPE_NAME_AND_VALUE_NAME_MUST_BE_EQUAL));
		
		//atleast one index should be provided
		insertValueInst = null;
		try{
			insertValueInst = InsertValueInst.create(firstOp, secondOp, name, null);
		}
		catch(InstructionCreationException ice){
			errMsg = ice.getMessage();
		}
		assertTrue(errMsg.equals(InstructionCreationException.ATLEAST_ONE_INDEX_SHOULD_BE_PROVIDED));
		
		//second operand must be of first class type
		aggrType = null;
		try{
			aggrType = Type.getStructType(compilationContext, false, "pqr", false, Type.getDoubleType(compilationContext), Type.getInt32Type(compilationContext, true));
		   }
		catch(TypeCreationException tce){}
		assertNotNull(aggrType);
		firstOp = new Value(aggrType);
		firstOp.setName("pqr");
		
		Type functionType = null;
		try{
			functionType = Type.getFunctionType(compilationContext, Type.getDoubleType(compilationContext), false, null);
		}
		catch(Exception e){}
		assertNotNull(functionType);
		
		secondOp = new Value(functionType);
		secondOp.setName("foo");
		
		errMsg = "";
		
		insertValueInst = null;
		try{
			insertValueInst = InsertValueInst.create(firstOp, secondOp, name, null, 1);
		}
		catch(InstructionCreationException ice){
			errMsg = ice.getMessage();
		}
		assertTrue(errMsg.equals(InstructionCreationException.SECOND_OPERAND_MUST_BE_OF_FIRST_CLASS_TYPE));
	}
}

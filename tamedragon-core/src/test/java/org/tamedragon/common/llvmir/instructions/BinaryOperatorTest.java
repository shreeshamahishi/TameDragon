package org.tamedragon.common.llvmir.instructions;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.tamedragon.common.llvmir.instructions.BinaryOperator;
import org.tamedragon.common.llvmir.instructions.BinaryOperator.BinaryOperatorID;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionCreationException;
import org.tamedragon.common.llvmir.types.CompilationContext;
import org.tamedragon.common.llvmir.types.ConstantInt;
import org.tamedragon.common.llvmir.types.GlobalVariable;
import org.tamedragon.common.llvmir.types.IntegerType;
import org.tamedragon.common.llvmir.types.Type;
import org.tamedragon.common.llvmir.types.Value;
import org.tamedragon.common.llvmir.types.VectorType;

public class BinaryOperatorTest {

	private CompilationContext compilationContext;

	@Before
	public void setUp(){
		compilationContext = new CompilationContext();
	}

	// Try with ADD and the noSignedWrap set to true
	@Test
	public void testValidInt32AddBinOpTypeCreation(){
		try{
			Type int32 = Type.getInt32Type(compilationContext, true);
			BinaryOperatorID binOpIdAdd = BinaryOperatorID.ADD;
			List<Value> operandList = new ArrayList<Value>();
			Value result = new Value(int32); result.setName("22");
			Value value1 = new Value(int32); value1.setName("1");
			Value value2 = new Value(int32); value2.setName("3");
			operandList.add(value1);
			operandList.add(value2);

			boolean noSignedWrap = true;
			boolean noUnsignedWrap = false;
			boolean exact = false;

			BinaryOperator binaryOpr =  BinaryOperator.create(binOpIdAdd, int32, "22", value1, 
					value2, noSignedWrap, noUnsignedWrap, exact, null);
			assertNotNull(binaryOpr);
			assertTrue(binaryOpr.toString().equals("%22 = add nsw i32 %1, %3"));
		}
		catch(InstructionCreationException boce){
			boce.printStackTrace();
			assertTrue(false);
		}
	}

	// Try with SUB and the noSignedWrap, signedWrap set to true
	@Test
	public void testValidInt16SubBinOpTypeCreation(){
		try{

			Type int16 = Type.getInt16Type(compilationContext, true);
			BinaryOperatorID binOpIdSub = BinaryOperatorID.SUB;
			List<Value> operandList = new ArrayList<Value>();
			Value value1 = new Value(int16); value1.setName("4");
			Value value2 = new Value(int16); value2.setName("6");
			operandList.add(value1);
			operandList.add(value2);
			boolean noSignedWrap = true;
			boolean noUnsignedWrap = true;
			boolean exact = false;
			BinaryOperator binaryOpr =  BinaryOperator.create(binOpIdSub, int16, "a", value1, 
					value2, noSignedWrap, noUnsignedWrap, exact, null);
			assertNotNull(binaryOpr);
			assertTrue(binaryOpr.toString().equals("%a = sub nuw nsw i16 %4, %6"));
		}
		catch(InstructionCreationException boce){
			boce.printStackTrace();
			assertTrue(false);
		}
	}

	// Try with MUL and the noSignedWrap, noUnsignedWrap, exact all set to false
	@Test
	public void testValidInt64MulBinOpTypeCreation(){
		try{

			Type int64 = Type.getInt64Type(compilationContext, true);
			BinaryOperatorID binOpIdMul = BinaryOperatorID.MUL;
			List<Value> operandList = new ArrayList<Value>();
			Value value1 = new Value(int64); value1.setName("8");
			Value value2 = new Value(int64); value2.setName("10");
			operandList.add(value1);
			operandList.add(value2);
			boolean noSignedWrap = false;
			boolean noUnsignedWrap = false;
			boolean exact = false;
			BinaryOperator binaryOpr =  BinaryOperator.create(binOpIdMul, int64, 
					"1", value1, value2, noSignedWrap, noUnsignedWrap, exact, null);
			assertNotNull(binaryOpr);
			assertTrue(binaryOpr.toString().equals("%1 = mul i64 %8, %10"));
		}
		catch(InstructionCreationException boce){
			boce.printStackTrace();
			assertTrue(false);
		}
	}

	// Try with SHL and the noUnsignedWrap flag set to true
	@Test
	public void testValidInt1ShlBinOpTypeCreation(){
		try{

			Type int1 = Type.getInt1Type(compilationContext, true);
			BinaryOperatorID binOpIdShl = BinaryOperatorID.SHL;
			List<Value> operandList = new ArrayList<Value>();
			Value value1 = new Value(int1); value1.setName("121");
			Value value2 = new Value(int1); value2.setName("122");
			operandList.add(value1);
			operandList.add(value2);
			boolean noSignedWrap = false;
			boolean noUnsignedWrap = true;
			boolean exact = false;
			BinaryOperator binaryOpr =  BinaryOperator.create(binOpIdShl, int1, 
					"1", value1, value2, noSignedWrap, noUnsignedWrap, exact, null);
			assertNotNull(binaryOpr);
			assertTrue(binaryOpr.toString().equals("%1 = shl nuw i1 %121, %122"));
		}
		catch(InstructionCreationException boce){
			boce.printStackTrace();
			assertTrue(false);
		}
	}

	// Test attempts to create Binary operators with incorrect result value
	@Test
	public void testInvalidBinOpTypeCreationWithInvalidResultValue(){

		// Try with null result value
		BinaryOperator binaryOpr1 = null, binaryOpr2 = null;
		Type int32 = Type.getInt32Type(compilationContext, true);
		try{
			BinaryOperatorID binOpIdShl = BinaryOperatorID.SHL;
			List<Value> operandList = new ArrayList<Value>();
			Value value1 = new Value(int32); value1.setName("%22");
			Value value2 = new Value(int32); value2.setName("%23");
			operandList.add(value1);
			operandList.add(value2);
			boolean noSignedWrap = false;
			boolean noUnsignedWrap = true;
			boolean exact = false;
			binaryOpr1 =  BinaryOperator.create(binOpIdShl, 
					null, "%1", value1, value2, noSignedWrap,
					noUnsignedWrap, exact, null);
		}
		catch(InstructionCreationException boce){
			String errMsg = boce.getMessage();

			assertTrue(errMsg.equals(InstructionCreationException.RESULT_TYPE_CANNOT_BE_NULL));
		}
		assertNull(binaryOpr1);

		// Try with a result value that has a type different from those of the operands
		try{
			Type int1 = Type.getInt1Type(compilationContext, false);
			BinaryOperatorID binOpIdSub = BinaryOperatorID.SUB;
			List<Value> operandList = new ArrayList<Value>();
			Value value1 = new Value(int1); value1.setName("%1");
			Value value2 = new Value(int1); value1.setName("%3");
			operandList.add(value1);
			operandList.add(value2);
			binaryOpr2 =  BinaryOperator.create(binOpIdSub, int32, "%4", value1, value2, false,
					false, false, null);
		}
		catch(InstructionCreationException boce){
			String errMsg = boce.getMessage();
			assertTrue(errMsg.equals(InstructionCreationException.RESULT_VALUE_TYPE_DIFFERS_FROM_OPERANDS_TYPE));
		}
		assertNull(binaryOpr2);
		
	}
	
	// Test attempts to create Binary operators with null binary operator ID.
	@Test
	public void testInvalidBinOpTypeCreationWithNullBinOpID(){

		// Try with more than two operands
		BinaryOperator binaryOpr = null;
		try{

			Type floatType = Type.getFloatType(compilationContext);
			Value value1 = new Value(floatType); value1.setName("%5");
			Value value2 = new Value(floatType); value2.setName("%6");
			boolean noSignedWrap = false;
			boolean noUnsignedWrap = true;
			boolean exact = false;
			binaryOpr =  BinaryOperator.create(null, floatType, "%7", value1, value2, noSignedWrap,
					noUnsignedWrap, exact, null);
		}
		catch(InstructionCreationException boce){
			String errMsg = boce.getMessage();
			assertTrue(errMsg.equals(InstructionCreationException.BINARY_OPERATOR_ID_IS_NULL));
		}
		assertNull(binaryOpr);
	}
	
	// Try with FADD
	@Test
	public void testValidFloatAddBinOpTypeCreation(){
		try{
			Type floatType = Type.getFloatType(compilationContext);
			BinaryOperatorID binOpIdAdd = BinaryOperatorID.FADD;
			
			Value result = new Value(floatType); result.setName("1");
			Value value1 = new Value(floatType); value1.setName("10");
			Value value2 = new Value(floatType); value2.setName("11");
			
			BinaryOperator binaryOpr =  BinaryOperator.create(binOpIdAdd, floatType, "1", 
					value1, value2, false,
					false, false, null);
			assertNotNull(binaryOpr);
			assertTrue(binaryOpr.toString().equals("%1 = fadd float %10, %11"));
		}
		catch(InstructionCreationException boce){
			boce.printStackTrace();
			assertTrue(false);
		}
	}

	// Try with FSUB
	@Test
	public void testValidDoubleSubBinOpTypeCreation(){
		try{
			Type dblType = Type.getDoubleType(compilationContext);
			BinaryOperatorID binOpIdSub = BinaryOperatorID.FSUB;
			Value result = new Value(dblType);  result.setName("abc");
			Value value1 = new Value(dblType); value1.setName("13");
			Value value2 = new Value(dblType); value2.setName("14");
		
			BinaryOperator binaryOpr =  BinaryOperator.create(binOpIdSub, dblType, 
					"abc", value1, value2, false,
					false, false, null);
			assertNotNull(binaryOpr);
			assertTrue(binaryOpr.toString().equals("%abc = fsub double %13, %14"));
		}
		catch(InstructionCreationException boce){
			boce.printStackTrace();
			assertTrue(false);
		}
	}

	// Try with FMUL
	@Test
	public void testValidHalfMulBinOpTypeCreation(){
		try{
			Type halfType = Type.getHalfType(compilationContext);
			BinaryOperatorID binOpIdMul = BinaryOperatorID.FMUL;
			Value value1 = new Value(halfType); value1.setName("18");
			Value value2 = new Value(halfType); value2.setName("231");
		
			BinaryOperator binaryOpr =  BinaryOperator.create(binOpIdMul, halfType, 
					"abc", value1, value2, false,
					false, false, null);
			assertNotNull(binaryOpr);
			assertTrue(binaryOpr.toString().equals("%abc = fmul half %18, %231"));
		}
		catch(InstructionCreationException boce){
			boce.printStackTrace();
			assertTrue(false);
		}
	}
	
	// Try with FREM
	@Test
	public void testValidFp128RemBinOpTypeCreation(){
		try{
			Type fp128Type = Type.getFP128Type(compilationContext);
			BinaryOperatorID binOpIdFp128 = BinaryOperatorID.FREM;
			Value value1 = new Value(fp128Type); value1.setName("1");
			Value value2 = new Value(fp128Type); value2.setName("9");
			
			BinaryOperator binaryOpr =  BinaryOperator.create(binOpIdFp128, 
					fp128Type, "abc", value1, value2, false,
					false, false, null);
			assertNotNull(binaryOpr);
			assertTrue(binaryOpr.toString().equals("%abc = frem fp128 %1, %9"));
		}
		catch(InstructionCreationException boce){
			boce.printStackTrace();
			assertTrue(false);
		}
	}

	// Try with other valid floating point types
	@Test
	public void testValidOtherFloatingPointBinOpTypeCreation(){
		try{
			Type ppcfp128Type = Type.getPPCFP128Type(compilationContext);
			Type x86Fp80Type = Type.getX86_FP80Type(compilationContext);
			BinaryOperatorID binOpId1 = BinaryOperatorID.FSUB;
			BinaryOperatorID binOpId2 = BinaryOperatorID.FMUL;
			Value value1 = new Value(ppcfp128Type); value1.setName("10");
			Value value2 = new Value(ppcfp128Type); value2.setName("8");
			
			Value value3 = new Value(x86Fp80Type); value3.setName("2");
			Value value4 = new Value(x86Fp80Type); value4.setName("76");

			BinaryOperator binaryOpr1 =  BinaryOperator.create(binOpId1, 
					ppcfp128Type, "2", value1, value2, false,
					false, false, null);
			assertNotNull(binaryOpr1);
			assertTrue(binaryOpr1.toString().equals("%2 = fsub ppc_fp128 %10, %8"));

			BinaryOperator binaryOpr2 =  BinaryOperator.create(binOpId2, 
					x86Fp80Type, "3", value3, value4, false,
					false, false, null);
			assertNotNull(binaryOpr2);
			assertTrue(binaryOpr2.toString().equals("%3 = fmul x86_fp80 %2, %76"));
		}
		catch(InstructionCreationException boce){
			boce.printStackTrace();
			assertTrue(false);
		}
	}

	// Test attempts to create Binary operators with integer operands and invalid combination
	// of the "exact" flag with the wrong operator. For example, the "exact" flag cannot be 
	// used with sub or mul.
	@Test
	public void testInvalidBinOpTypeCreationWithIntegerOperandsAndWrongUseOfExactFlag(){

		// Try with ADD and exact = true
		BinaryOperator binaryOpr1 = null;
		BinaryOperatorID binOpId1 = BinaryOperatorID.ADD;
		Type int8Type = Type.getInt8Type(compilationContext, true);
		Value value1 = new Value(int8Type); value1.setName("%1");
		Value value2 = new Value(int8Type); value2.setName("%2");
		try{
			binaryOpr1 =  BinaryOperator.create(binOpId1, 
					int8Type, "@abc", value1, value2, false,
					true, true, null);
		}
		catch(InstructionCreationException boce){
			String errMsg = boce.getMessage();
			assertTrue(errMsg.equalsIgnoreCase(InstructionCreationException.EXACT_CANNOT_BE_USED_HERE + binOpId1));
		}
		assertNull(binaryOpr1);

		// Try with SUB and exact = true
		BinaryOperator binaryOpr2 = null;
		BinaryOperatorID binOpId2 = BinaryOperatorID.SUB;
		Type int16Type = Type.getInt16Type(compilationContext, true);
		Value value3 = new Value(int16Type); value3.setName("%4");
		Value value4 = new Value(int16Type); value4.setName("%5");
		try{
			binaryOpr2 =  BinaryOperator.create(binOpId2, 
					int16Type, "@abc", value3, value4, true,
					false, true, null);
		}
		catch(InstructionCreationException boce){
			String errMsg = boce.getMessage();
			assertTrue(errMsg.equalsIgnoreCase(InstructionCreationException.EXACT_CANNOT_BE_USED_HERE + binOpId2));
		}
		assertNull(binaryOpr2);

		// Try with MUL and exact = true
		BinaryOperator binaryOpr3 = null;
		BinaryOperatorID binOpId3 = BinaryOperatorID.MUL;
		Type int32Type = Type.getInt32Type(compilationContext, true);
		Value value5 = new Value(int32Type);
		Value value6 = new Value(int32Type); 
		
		try{
			binaryOpr3 =  BinaryOperator.create(binOpId3, 
					int32Type, "@abc", value5, value6, true,
					false, true, null);
		}
		catch(InstructionCreationException boce){
			String errMsg = boce.getMessage();
			assertTrue(errMsg.equalsIgnoreCase(InstructionCreationException.EXACT_CANNOT_BE_USED_HERE + binOpId3));
		}
		assertNull(binaryOpr3);

		// Try with UREM and exact = true
		BinaryOperator binaryOpr4 = null;
		BinaryOperatorID binOpId4 = BinaryOperatorID.UREM;
		Type int64Type = Type.getInt64Type(compilationContext, true);
		Value value7 = new Value(int64Type);
		Value value8 = new Value(int64Type); 
		try{
			binaryOpr4 =  BinaryOperator.create(binOpId4, 
					int64Type,"@abc", value7, value8, false,
					false, true, null);
		}
		catch(InstructionCreationException boce){
			String errMsg = boce.getMessage();
			assertTrue(errMsg.equalsIgnoreCase(InstructionCreationException.EXACT_CANNOT_BE_USED_HERE + binOpId4));
		}
		assertNull(binaryOpr4);

		// Try with SREM and exact = true
		BinaryOperator binaryOpr5 = null;
		BinaryOperatorID binOpId5 = BinaryOperatorID.SREM;
		Type int3Type = null;
		try{
			int3Type = Type.getIntegerType(compilationContext, 3, true);
		}
		catch(Exception e){}
		assertNotNull(int3Type);
		
		Value value9 = new Value(int3Type);
		Value value10 = new Value(int3Type); 
		
		try{
			binaryOpr5 =  BinaryOperator.create(binOpId5, 
					int3Type, "@abc", value9, value10, true,
					false, true, null);
		}
		catch(InstructionCreationException boce){
			String errMsg = boce.getMessage();
			assertTrue(errMsg.equalsIgnoreCase(InstructionCreationException.EXACT_CANNOT_BE_USED_HERE + binOpId5));
		}
		assertNull(binaryOpr5);

		// Try with SHL and exact = true
		BinaryOperator binaryOpr6 = null;
		BinaryOperatorID binOpId6 = BinaryOperatorID.SHL;
		Type int6Type = null;
		try{
			int6Type = Type.getIntegerType(compilationContext, 6, true);
		}
		catch(Exception e){}
		assertNotNull(int6Type);
		Value value11 = new Value(int6Type);
		Value value12 = new Value(int6Type);
		try{
			binaryOpr6 =  BinaryOperator.create(binOpId6, 
					int6Type, "@abc", value11, value12, false,
					false, true, null);
		}
		catch(InstructionCreationException boce){
			String errMsg = boce.getMessage();
			assertTrue(errMsg.equalsIgnoreCase(InstructionCreationException.EXACT_CANNOT_BE_USED_HERE + binOpId6));
		}
		assertNull(binaryOpr6);

		// Try with AND and exact = true
		BinaryOperator binaryOpr7 = null;
		BinaryOperatorID binOpId7 = BinaryOperatorID.AND;
		Type int9Type = null;
		try{
			int9Type = Type.getIntegerType(compilationContext, 9, true);
		}
		catch(Exception e){}
		assertNotNull(int8Type);
		Value value13 = new Value(int9Type);
		Value value14 = new Value(int9Type);
		
		try{
			binaryOpr7 =  BinaryOperator.create(binOpId7, 
					int9Type, "@abc", value13, value14, false,
					false, true, null);
		}
		catch(InstructionCreationException boce){
			String errMsg = boce.getMessage();
			assertTrue(errMsg.equalsIgnoreCase(InstructionCreationException.EXACT_CANNOT_BE_USED_HERE + binOpId7));
		}
		assertNull(binaryOpr7);

		// Try with OR and exact = true
		BinaryOperator binaryOpr8 = null;
		BinaryOperatorID binOpId8 = BinaryOperatorID.OR;
		Type int11Type = null;
		try{
			int11Type = Type.getIntegerType(compilationContext, 11, true);
		}
		catch(Exception e){}
		assertNotNull(int8Type);
		Value value15 = new Value(int11Type);
		Value value16 = new Value(int11Type);
		
		try{
			binaryOpr8 =  BinaryOperator.create(binOpId8, 
					int11Type, "@abc", value15, value16, false,
					false, true, null);
		}
		catch(InstructionCreationException boce){
			String errMsg = boce.getMessage();
			assertTrue(errMsg.equalsIgnoreCase(InstructionCreationException.EXACT_CANNOT_BE_USED_HERE + binOpId8));
		}
		assertNull(binaryOpr8);

		// Try with XOR and exact = true
		BinaryOperator binaryOpr9 = null;
		BinaryOperatorID binOpId9 = BinaryOperatorID.XOR;
		Type int14Type = null;
		try{
			int14Type = Type.getIntegerType(compilationContext, 14, true);
		}
		catch(Exception e){}
		assertNotNull(int14Type);
		Value value17 = new Value(int14Type);
		Value value18 = new Value(int14Type);
		try{
			binaryOpr9 =  BinaryOperator.create(binOpId9, 
					int14Type, "@abc", value17, value18, false,
					false, true, null);
		}
		catch(InstructionCreationException boce){
			String errMsg = boce.getMessage();
			assertTrue(errMsg.equalsIgnoreCase(InstructionCreationException.EXACT_CANNOT_BE_USED_HERE + binOpId9));
		}
		assertNull(binaryOpr9);
	}
	
	// Test attempts to create Binary operators with integer operands and invalid combination
	// of the "nsw" and/or "nuw" flag with the wrong operator. For example, the "nsw / nuw" 
	// flag cannot be used with sub or mul.
	@Test
	public void testInvalidBinOpTypeCreationWithIntegerOperandsAndWrongUseOfWrapFlags(){

		// Try with UDIV and nsw = true
		BinaryOperator binaryOpr1 = null;
		BinaryOperatorID binOpId1 = BinaryOperatorID.UDIV;
		Type int1Type = Type.getInt1Type(compilationContext, false);
		Value value1 = new Value(int1Type); 
		Value value2 = new Value(int1Type);
		try{
			binaryOpr1 =  BinaryOperator.create(binOpId1, 
					int1Type, "@abc", value1, value2, true,
					false, false, null);
		}
		catch(InstructionCreationException boce){
			String errMsg = boce.getMessage();
			assertTrue(errMsg.equalsIgnoreCase(InstructionCreationException.WRAP_FLAG_CANNOT_BE_USED_HERE + binOpId1));
		}
		assertNull(binaryOpr1);

		// Try with SDIV and nuw = true
		BinaryOperator binaryOpr2 = null;
		BinaryOperatorID binOpId2 = BinaryOperatorID.SDIV;
		Type int8Type = Type.getInt8Type(compilationContext, true);
		Value value3 = new Value(int8Type); 
		Value value4 = new Value(int8Type);
		try{
			binaryOpr2 =  BinaryOperator.create(binOpId2, 
					int8Type, "@abc", value3, value4, false,
					true, false, null);
		}
		catch(InstructionCreationException boce){
			String errMsg = boce.getMessage();
			assertTrue(errMsg.equalsIgnoreCase(InstructionCreationException.WRAP_FLAG_CANNOT_BE_USED_HERE + binOpId2));
		}
		assertNull(binaryOpr2);

		// Try with UREM and nuw, nsw = true
		BinaryOperator binaryOpr3 = null;
		BinaryOperatorID binOpId3 = BinaryOperatorID.UREM;
		Type int16Type = Type.getInt16Type(compilationContext, true);
		Value value5 = new Value(int16Type); 
		Value value6 = new Value(int16Type);
		try{
			Value result = new Value(int16Type);  result.setName("@abc");

			binaryOpr3 =  BinaryOperator.create(binOpId3, 
					int16Type, "@abc", value5, value6, true,
					true, false, null);
		}
		catch(InstructionCreationException boce){
			String errMsg = boce.getMessage();
			assertTrue(errMsg.equalsIgnoreCase(
					InstructionCreationException.WRAP_FLAG_CANNOT_BE_USED_HERE 
					+ binOpId3));
		}
		assertNull(binaryOpr3);

		// Try with SREM and nuw = true
		BinaryOperator binaryOpr4 = null;
		BinaryOperatorID binOpId4 = BinaryOperatorID.SREM;
		Type int32Type = Type.getInt32Type(compilationContext, true);
		Value value7 = new Value(int32Type); 
		Value value8 = new Value(int32Type);
		try{
			binaryOpr4 =  BinaryOperator.create(binOpId4, 
					int32Type, "@abc", value7, value8, false,
					true, false, null);
		}
		catch(InstructionCreationException boce){
			String errMsg = boce.getMessage();
			assertTrue(errMsg.equalsIgnoreCase(
					InstructionCreationException.WRAP_FLAG_CANNOT_BE_USED_HERE 
					+ binOpId4));
		}
		assertNull(binaryOpr4);

		// Try with LSHR and nsw = true
		BinaryOperator binaryOpr5 = null;
		BinaryOperatorID binOpId5 = BinaryOperatorID.LSHR;
		Type int64Type = Type.getInt64Type(compilationContext, true);
		Value value9 = new Value(int64Type); 
		Value value10 = new Value(int64Type);
		try{
			binaryOpr5 =  BinaryOperator.create(binOpId5, 
					int64Type, "@abc", value9, value10, true,
					false, false, null);
		}
		catch(InstructionCreationException boce){
			String errMsg = boce.getMessage();
			assertTrue(errMsg.equalsIgnoreCase(
					InstructionCreationException.WRAP_FLAG_CANNOT_BE_USED_HERE 
					+ binOpId5));
		}
		assertNull(binaryOpr5);

		// Try with ASHR and nuw = true
		BinaryOperator binaryOpr6 = null;
		BinaryOperatorID binOpId6 = BinaryOperatorID.ASHR;
		Type int3Type = null;
		try{
			int3Type = Type.getIntegerType(compilationContext, 3, true);
		}
		catch(Exception e){}
		assertNotNull(int3Type);
		Value value11 = new Value(int3Type); 
		Value value12 = new Value(int3Type);
		try{
			binaryOpr6 =  BinaryOperator.create(binOpId6, 
					int3Type, "@abc", value11, value12, false,
					true, false, null);
		}
		catch(InstructionCreationException boce){
			String errMsg = boce.getMessage();
			assertTrue(errMsg.equalsIgnoreCase(InstructionCreationException.WRAP_FLAG_CANNOT_BE_USED_HERE + binOpId6));
		}
		assertNull(binaryOpr6);

		// Try with OR and nuw, nsw = true
		BinaryOperator binaryOpr8 = null;
		BinaryOperatorID binOpId8 = BinaryOperatorID.OR;
		Type int9Type = null;
		try{
			int9Type = Type.getIntegerType(compilationContext, 9, true);
		}
		catch(Exception e){}
		assertNotNull(int9Type);
		Value value15 = new Value(int9Type); 
		Value value16 = new Value(int9Type);
		try{
			binaryOpr8 =  BinaryOperator.create(binOpId8, 
					int9Type, "@abc", value15, value16, true,
					true, false, null);
		}
		catch(InstructionCreationException boce){
			String errMsg = boce.getMessage();
			assertTrue(errMsg.equalsIgnoreCase(
					InstructionCreationException.WRAP_FLAG_CANNOT_BE_USED_HERE 
					+ binOpId8));
		}
		assertNull(binaryOpr8);
		
		// Try with XOR and nsw = true
		BinaryOperator binaryOpr9 = null;
		BinaryOperatorID binOpId9 = BinaryOperatorID.XOR;
		Type int11Type = null;
		try{
			int11Type = Type.getIntegerType(compilationContext, 11, true);
		}
		catch(Exception e){}
		assertNotNull(int9Type);
		Value value17 = new Value(int11Type); 
		Value value18 = new Value(int11Type);
		try{
			binaryOpr9 =  BinaryOperator.create(binOpId9, 
					int11Type, "@abc", value17, value18, true,
					false, false, null);
		}
		catch(InstructionCreationException boce){
			String errMsg = boce.getMessage();
			assertTrue(errMsg.equalsIgnoreCase(InstructionCreationException.WRAP_FLAG_CANNOT_BE_USED_HERE + binOpId9));
		}
		assertNull(binaryOpr9);
	}
	
	// Test attempts to create Binary operators with non-integer operands for operations
	// that require integer arguments. For example, the "add", "sub" or "mul" operations 
	// need only integer operands, not floating point types.
	@Test
	public void testInvalidBinOpTypeCreationWithNonIntegerOperandsForIntegerOps(){

		// Try ADD with an integer and a floating point operand
		BinaryOperator binaryOpr1 = null;
		BinaryOperatorID binOpId1 = BinaryOperatorID.AND;
		Type int32Type = Type.getInt32Type(compilationContext, true);
		Type floatType = Type.getFloatType(compilationContext);
		Value value1 = new Value(int32Type); 
		Value value2 = new Value(floatType);
		try{
			binaryOpr1 =  BinaryOperator.create(binOpId1, 
					int32Type, "@abc", value1, value2, true, true, false, null);
		}
		catch(InstructionCreationException boce){
			String errMsg = boce.getMessage();
			assertTrue(errMsg.equalsIgnoreCase(
					InstructionCreationException.OPERANDS_MUST_BE_INTEGERS));
		}
		assertNull(binaryOpr1);
		
		// Try SUB with a floating point operand and an integer 
		BinaryOperator binaryOpr2 = null;
		BinaryOperatorID binOpId2 = BinaryOperatorID.SUB;
		Type doubleType = Type.getDoubleType(compilationContext);
		Value value3 = new Value(doubleType); 
		Value value4 = new Value(int32Type);
		try{
			binaryOpr2 =  BinaryOperator.create(binOpId2, 
					doubleType, "@abc", value3, value4, true, true, false, null);
		}
		catch(InstructionCreationException boce){
			String errMsg = boce.getMessage();
			assertTrue(errMsg.equalsIgnoreCase(
					InstructionCreationException.OPERANDS_MUST_BE_INTEGERS));
		}
		assertNull(binaryOpr2);
		
		// Try MUL with both floating point operands 
		BinaryOperator binaryOpr3 = null;
		BinaryOperatorID binOpId3 = BinaryOperatorID.MUL;
		Type fp128Type = Type.getFP128Type(compilationContext);
		Type ppcfp128Type = Type.getPPCFP128Type(compilationContext);
		Value value5 = new Value(fp128Type); 
		Value value6 = new Value(ppcfp128Type);
		try{
			binaryOpr3 =  BinaryOperator.create(binOpId3, 
					fp128Type, "@abc", value5, value6, true, true, false, null);
		}
		catch(InstructionCreationException boce){
			String errMsg = boce.getMessage();
			assertTrue(errMsg.equalsIgnoreCase(
					InstructionCreationException.OPERANDS_MUST_BE_INTEGERS));
		}
		assertNull(binaryOpr3);
		
		// Try UREM with a floating point and integer
		BinaryOperator binaryOpr4 = null;
		BinaryOperatorID binOpId4 = BinaryOperatorID.UREM;
		Type x86fp80Type = Type.getX86_FP80Type(compilationContext);
		Type int16Type = Type.getInt16Type(compilationContext, true);
		Value value7 = new Value(x86fp80Type); 
		Value value8 = new Value(int16Type);
		try{
			binaryOpr4 =  BinaryOperator.create(binOpId4,
					x86fp80Type, "@abc", value7, value8, true, true, false, null);
		}
		catch(InstructionCreationException boce){
			String errMsg = boce.getMessage();
			assertTrue(errMsg.equalsIgnoreCase(
					InstructionCreationException.OPERANDS_MUST_BE_INTEGERS));
		}
		assertNull(binaryOpr4);
		
		// Try SREM with a integer and a floating point
		BinaryOperator binaryOpr5 = null;
		BinaryOperatorID binOpId5 = BinaryOperatorID.SREM;
		Value value9 = new Value(int16Type); 
		Value value10 = new Value(floatType);
		try{
			binaryOpr5 =  BinaryOperator.create(binOpId5, 
					int16Type, "@abc", value9, value10, true, true, false, null);
		}
		catch(InstructionCreationException boce){
			String errMsg = boce.getMessage();
			assertTrue(errMsg.equalsIgnoreCase(
					InstructionCreationException.OPERANDS_MUST_BE_INTEGERS));
		}
		assertNull(binaryOpr5);
		
		// Try UDIV with a floating point and an integer
		BinaryOperator binaryOpr6 = null;
		BinaryOperatorID binOpId6 = BinaryOperatorID.UDIV;
		Value value11 = new Value(floatType); 
		Value value12 = new Value(int32Type);
		try{
			binaryOpr6 =  BinaryOperator.create(binOpId6, 
					floatType, "@abc", value11, value12, true, true, false, null);
		}
		catch(InstructionCreationException boce){
			String errMsg = boce.getMessage();
			assertTrue(errMsg.equalsIgnoreCase(InstructionCreationException.OPERANDS_MUST_BE_INTEGERS));
		}
		assertNull(binaryOpr6);
		
		// Try SDIV with an integer and a floating point
		BinaryOperator binaryOpr7 = null;
		BinaryOperatorID binOpId7 = BinaryOperatorID.SDIV;
		Value value15 = new Value(int16Type); 
		Value value16 = new Value(ppcfp128Type);
		try{
			binaryOpr7 =  BinaryOperator.create(binOpId7, 
					int16Type, "@abc", value15, value16, true, true, false, null);
		}
		catch(InstructionCreationException boce){
			String errMsg = boce.getMessage();
			assertTrue(errMsg.equalsIgnoreCase(
					InstructionCreationException.OPERANDS_MUST_BE_INTEGERS));
		}
		assertNull(binaryOpr7);
		
		// Try SHL with a floating point value and an integer
		BinaryOperator binaryOpr8 = null;
		BinaryOperatorID binOpId8 = BinaryOperatorID.SHL;
		Value value17 = new Value(fp128Type); 
		Value value18 = new Value(int16Type);
		try{
			binaryOpr8 =  BinaryOperator.create(binOpId8, 
					fp128Type, "@abc", value17, value18, true, true, false, null);
		}
		catch(InstructionCreationException boce){
			String errMsg = boce.getMessage();
			assertTrue(errMsg.equalsIgnoreCase(
					InstructionCreationException.OPERANDS_MUST_BE_INTEGERS));
		}
		assertNull(binaryOpr8);
		
		// Try LSHR with an integer and a floating point
		BinaryOperator binaryOpr9 = null;
		BinaryOperatorID binOpId9 = BinaryOperatorID.LSHR;
		Value value19 = new Value(int16Type); 
		Value value20 = new Value(doubleType);
		try{
			binaryOpr9 =  BinaryOperator.create(binOpId9, 
					int16Type, "@abc", value19, value20, true, true, false, null);
		}
		catch(InstructionCreationException boce){
			String errMsg = boce.getMessage();
			assertTrue(errMsg.equalsIgnoreCase(
					InstructionCreationException.OPERANDS_MUST_BE_INTEGERS));
		}
		assertNull(binaryOpr9);
		
		// Try ASHR a floating point value and an integer
		BinaryOperator binaryOpr10 = null;
		BinaryOperatorID binOpId10 = BinaryOperatorID.ASHR;
		Value value21 = new Value(floatType); 
		Value value22 = new Value(int32Type);
		try{
			binaryOpr10 =  BinaryOperator.create(binOpId10, 
					floatType, "@abc", value21, value22, true, true, false, null);
		}
		catch(InstructionCreationException boce){
			String errMsg = boce.getMessage();
			assertTrue(errMsg.equalsIgnoreCase(
					InstructionCreationException.OPERANDS_MUST_BE_INTEGERS));
		}
		assertNull(binaryOpr10);
		
		// Try AND with an integer and a floating point
		BinaryOperator binaryOpr11 = null;
		BinaryOperatorID binOpId11 = BinaryOperatorID.AND;
		Value value23 = new Value(int16Type); 
		Value value24 = new Value(ppcfp128Type);
		try{
			binaryOpr11 =  BinaryOperator.create(binOpId11, 
					int16Type, "@abc", value23, value24, true, true, false, null);
		}
		catch(InstructionCreationException boce){
			String errMsg = boce.getMessage();
			assertTrue(errMsg.equalsIgnoreCase(
					InstructionCreationException.OPERANDS_MUST_BE_INTEGERS));
		}
		assertNull(binaryOpr11);
		
		// Try OR with a floating point and an integer
		BinaryOperator binaryOpr12 = null;
		BinaryOperatorID binOpId12 = BinaryOperatorID.OR;
		Value value25 = new Value(x86fp80Type); 
		Value value26 = new Value(int32Type);
		try{
			binaryOpr12 =  BinaryOperator.create(binOpId12, 
					x86fp80Type, "@abc", value25, value26, true, true, false, null);
		}
		catch(InstructionCreationException boce){
			String errMsg = boce.getMessage();
			assertTrue(errMsg.equalsIgnoreCase(
					InstructionCreationException.OPERANDS_MUST_BE_INTEGERS));
		}
		assertNull(binaryOpr12);
		
		// Try XOR with an integer and a floating point
		BinaryOperator binaryOpr13 = null;
		BinaryOperatorID binOpId13 = BinaryOperatorID.XOR;
		Value value27 = new Value(int16Type); 
		Value value28 = new Value(fp128Type);
		try{
			binaryOpr13 =  BinaryOperator.create(binOpId13, 
					int16Type, "@abc", value27, value28, true, true, false, null);
		}
		catch(InstructionCreationException boce){
			String errMsg = boce.getMessage();
			assertTrue(errMsg.equalsIgnoreCase(InstructionCreationException.OPERANDS_MUST_BE_INTEGERS));
		}
		assertNull(binaryOpr13);
	}
	
	// Test attempts to create Binary operators with non floating pont operands for operations
	// that require floating point arguments. For example, the "fadd", "fsub" or "fmul" operations 
	// need only floating point operands, not integer types.
	@Test
	public void testInvalidBinOpTypeCreationWithNonFloatingPointOperandsForFloatingPointOps(){

		Type int1Type = Type.getInt1Type(compilationContext, false);
		Type int8Type = Type.getInt8Type(compilationContext, true);
		Type int16Type = Type.getInt16Type(compilationContext, true);
		Type int32Type = Type.getInt32Type(compilationContext, true);
		Type int64Type = Type.getInt64Type(compilationContext, true);
		
		Type halfType = Type.getHalfType(compilationContext);
		Type floatType = Type.getFloatType(compilationContext);
		Type doubleType = Type.getDoubleType(compilationContext);
		
		// Try FADD with an integer and a floating point operand
		BinaryOperator binaryOpr1 = null;
		BinaryOperatorID binOpId1 = BinaryOperatorID.FADD;
		Value value1 = new Value(int1Type); 
		Value value2 = new Value(halfType);
		try{
			binaryOpr1 =  BinaryOperator.create(binOpId1, 
					int1Type, "@abc", value1, value2, true, true, false, null);
		}
		catch(InstructionCreationException boce){
			String errMsg = boce.getMessage();
			assertTrue(errMsg.equalsIgnoreCase(
					InstructionCreationException.OPERANDS_MUST_BE_FLOATING_POINT_TYPES));
		}
		assertNull(binaryOpr1);
		
		// Try FSUB with both integers
		BinaryOperator binaryOpr2 = null;
		BinaryOperatorID binOpId2 = BinaryOperatorID.FSUB;
		Value value3 = new Value(int16Type); 
		Value value4 = new Value(int32Type);
		try{
			binaryOpr2 =  BinaryOperator.create(binOpId2, 
					int16Type, "@abc", value3, value4, true, true, false, null);
		}
		catch(InstructionCreationException boce){
			String errMsg = boce.getMessage();
			assertTrue(errMsg.equalsIgnoreCase(
					InstructionCreationException.OPERANDS_MUST_BE_FLOATING_POINT_TYPES));
		}
		assertNull(binaryOpr2);
		
		// Try FMUL with a floating point operand and an integer
		BinaryOperator binaryOpr3 = null;
		BinaryOperatorID binOpId3 = BinaryOperatorID.FMUL;
		Value value5 = new Value(floatType); 
		Value value6 = new Value(int8Type);
		try{
			binaryOpr3 =  BinaryOperator.create(binOpId3, 
					floatType, "@abc", value5, value6, true, true, false, null);
		}
		catch(InstructionCreationException boce){
			String errMsg = boce.getMessage();
			assertTrue(errMsg.equalsIgnoreCase(
					InstructionCreationException.OPERANDS_MUST_BE_FLOATING_POINT_TYPES));
		}
		assertNull(binaryOpr3);
		
		// Try FDIV with a floating point operand and an integer
		BinaryOperator binaryOpr4 = null;
		BinaryOperatorID binOpId4 = BinaryOperatorID.FDIV;
		Value value7 = new Value(doubleType); 
		Value value8 = new Value(int64Type);
		try{
			binaryOpr4 =  BinaryOperator.create(binOpId4, 
					doubleType, "@abc", value7, value8, true, true, false, null);
		}
		catch(InstructionCreationException boce){
			String errMsg = boce.getMessage();
			assertTrue(errMsg.equalsIgnoreCase(
					InstructionCreationException.OPERANDS_MUST_BE_FLOATING_POINT_TYPES));
		}
		assertNull(binaryOpr4);
		
		// Try FREM with a floating point operand and an integer
		BinaryOperator binaryOpr5 = null;
		BinaryOperatorID binOpId5 = BinaryOperatorID.FREM;
		Value value9 = new Value(doubleType); 
		Value value10 = new Value(int64Type);
		try{
			binaryOpr5 =  BinaryOperator.create(binOpId5, 
					doubleType, "@abc", value9, value10, true, true, false, null);
		}
		catch(InstructionCreationException boce){
			String errMsg = boce.getMessage();
			assertTrue(errMsg.equalsIgnoreCase(
					InstructionCreationException.OPERANDS_MUST_BE_FLOATING_POINT_TYPES));
		}
		assertNull(binaryOpr5);
	}
	
	// Test attempts to create Binary operators with integer operands of unequal bit size. 
	// For example, the "add", "sub" or "mul" operations need only integer operands of
	// equal bit size.
	@Test
	public void testInvalidBinOpTypeCreationWithIntegerOperandsOfUnequalBitSize(){

		Type int1Type = Type.getInt1Type(compilationContext, false);
		Type int8Type = Type.getInt8Type(compilationContext, true);
		Type int16Type = Type.getInt16Type(compilationContext, true);
		Type int5Type = null, int6Type = null;
		try{
			int5Type = Type.getIntegerType(compilationContext, 5, true);
			int6Type = Type.getIntegerType(compilationContext, 6, true);
		}
		catch(Exception e){}
		
		assertNotNull(int5Type);
		assertNotNull(int6Type);

		BinaryOperator binaryOpr1 = null;
		BinaryOperatorID binOpId1 = BinaryOperatorID.ADD;
		Value value1 = new Value(int1Type); 
		Value value2 = new Value(int8Type);
		try{
			binaryOpr1 =  BinaryOperator.create(binOpId1, 
					int1Type, "@abc", value1, value2, true, true, false, null);
		}
		catch(InstructionCreationException boce){
			String errMsg = boce.getMessage();
			assertTrue(errMsg.equalsIgnoreCase(
					InstructionCreationException.INTEGER_OPERANDS_MUST_BE_SAME_WIDTH));
		}
		assertNull(binaryOpr1);
		
		BinaryOperator binaryOpr2 = null;
		BinaryOperatorID binOpId2 = BinaryOperatorID.SUB;
		Value value3 = new Value(int16Type); 
		Value value4 = new Value(int5Type);
		try{
			binaryOpr2 =  BinaryOperator.create(binOpId2, 
					int16Type, "@abc", value3, value4, true, true, false, null);
		}
		catch(InstructionCreationException boce){
			String errMsg = boce.getMessage();
			assertTrue(errMsg.equalsIgnoreCase(InstructionCreationException.INTEGER_OPERANDS_MUST_BE_SAME_WIDTH));
		}
		assertNull(binaryOpr2);
		
		BinaryOperator binaryOpr3 = null;
		BinaryOperatorID binOpId3 = BinaryOperatorID.MUL;
		Value value5 = new Value(int5Type); 
		Value value6 = new Value(int6Type);
		try{
			binaryOpr3 =  BinaryOperator.create(binOpId3, 
					int5Type, "@abc", value5, value6, true, true, false, null);
		}
		catch(InstructionCreationException boce){
			String errMsg = boce.getMessage();
			assertTrue(errMsg.equalsIgnoreCase(InstructionCreationException.INTEGER_OPERANDS_MUST_BE_SAME_WIDTH));
		}
		assertNull(binaryOpr3);
		
	}
	
	// Test attempts to create Binary operators with vector operands that are not similar. 
	// For example, the "add", "sub" or "mul" operations need only integer operands of
	// equal bit size.
	@Test
	public void testInvalidBinOpTypeCreationWithInvalidVectorOperands(){
		Type int1Type = Type.getInt1Type(compilationContext, false);
		Type int8Type = Type.getInt8Type(compilationContext, true);
		Type floatType = Type.getFloatType(compilationContext);
		Type doubleType = Type.getDoubleType(compilationContext);
		Type int5Type = null, int6Type = null;
		
		Type vecType1 = null, vecType2 = null, vecType3 = null, vecType4 = null, 
					vecType5 = null, vecType6 = null, vecType7 = null, vecType8 = null;
		
		try{
			int5Type = Type.getIntegerType(compilationContext, 5, true);
			int6Type = Type.getIntegerType(compilationContext, 6, true);
			
			// Two vectors, int type, but different size
			vecType1 = Type.getVectorType(compilationContext, int1Type, 4);
			vecType2 = Type.getVectorType(compilationContext, int1Type, 5);
			
			// Two vectors, int type, same size, but different type of elements
			vecType3 = Type.getVectorType(compilationContext, int8Type, 5);
			vecType4 = Type.getVectorType(compilationContext, int1Type, 5);
			
			// Two vectors, float type, but different size
			vecType5 = Type.getVectorType(compilationContext, floatType, 6);
			vecType6 = Type.getVectorType(compilationContext, floatType, 3);
			
			// Two vectors, floating point type, same size, but different type of elements
			vecType7 = Type.getVectorType(compilationContext, floatType, 5);
			vecType8 = Type.getVectorType(compilationContext, doubleType, 5);
			
		}
		catch(Exception e){}
		assertNotNull(int5Type);
		assertNotNull(int6Type);
		assertNotNull(vecType1);
		assertNotNull(vecType2);
		assertNotNull(vecType3);
		assertNotNull(vecType4);
		assertNotNull(vecType5);
		assertNotNull(vecType6);
		assertNotNull(vecType7);
		assertNotNull(vecType8);

		BinaryOperator binaryOpr1 = null;
		BinaryOperatorID binOpId1 = BinaryOperatorID.ADD;
		Value value1 = new Value(vecType1); 
		Value value2 = new Value(vecType2);
		try{
			binaryOpr1 =  BinaryOperator.create(binOpId1, 
					vecType1, "@abc", value1, value2, true, true, false, null);
		}
		catch(InstructionCreationException boce){
			String errMsg = boce.getMessage();
			assertTrue(errMsg.equalsIgnoreCase(
					InstructionCreationException.VECTOR_OPERANDS_MUST_BE_SIMILAR));
		}
		assertNull(binaryOpr1);
		
		BinaryOperator binaryOpr2 = null;
		BinaryOperatorID binOpId2 = BinaryOperatorID.SUB;
		Value value3 = new Value(vecType3); 
		Value value4 = new Value(vecType4);
		try{
			binaryOpr2 =  BinaryOperator.create(binOpId2, 
					vecType3, "@abc", value3, value4, true, true, false, null);
		}
		catch(InstructionCreationException boce){
			String errMsg = boce.getMessage();
			assertTrue(errMsg.equalsIgnoreCase(
					InstructionCreationException.VECTOR_OPERANDS_MUST_BE_SIMILAR));
		}
		assertNull(binaryOpr2);
		
		BinaryOperator binaryOpr3 = null;
		BinaryOperatorID binOpId3 = BinaryOperatorID.FMUL;
		Value value5 = new Value(vecType5); 
		Value value6 = new Value(vecType6);
		try{
			binaryOpr3 =  BinaryOperator.create(binOpId3, 
					vecType5, "@abc", value5, value6, true, true, false, null);
		}
		catch(InstructionCreationException boce){
			String errMsg = boce.getMessage();
			assertTrue(errMsg.equalsIgnoreCase(InstructionCreationException.VECTOR_OPERANDS_MUST_BE_SIMILAR));
		}
		assertNull(binaryOpr3);
		
		BinaryOperator binaryOpr4 = null;
		BinaryOperatorID binOpId4 = BinaryOperatorID.FDIV;
		Value value7 = new Value(vecType7); 
		Value value8 = new Value(vecType8);
		try{
			binaryOpr4 =  BinaryOperator.create(binOpId4, 
					vecType7, "@abc", value7, value8, true, true, false, null);
		}
		catch(InstructionCreationException boce){
			String errMsg = boce.getMessage();
			assertTrue(errMsg.equalsIgnoreCase(
					InstructionCreationException.VECTOR_OPERANDS_MUST_BE_SIMILAR));
		}
		assertNull(binaryOpr4);
		
		BinaryOperator binaryOpr5 = null;
		BinaryOperatorID binOpId5 = BinaryOperatorID.SDIV;
		Value value9 = new Value(vecType3); 
		Value value10 = new Value(vecType7);
		try{
			binaryOpr5 =  BinaryOperator.create(binOpId5, 
					vecType3, "@abc",value9, value10, true, true, false, null);
		}
		catch(InstructionCreationException boce){
			String errMsg = boce.getMessage();
			assertTrue(errMsg.equalsIgnoreCase(InstructionCreationException.VECTOR_OPERANDS_MUST_BE_SIMILAR));
		}
		assertNull(binaryOpr5);
		
		BinaryOperator binaryOpr6 = null;
		BinaryOperatorID binOpId6 = BinaryOperatorID.UREM;
		Value value11 = new Value(vecType1); 
		Value value12 = new Value(vecType8);
		try{
			binaryOpr6 =  BinaryOperator.create(binOpId6, 
					vecType1, "@abc", value11, value12, true, true, false, null);
		}
		catch(InstructionCreationException boce){
			String errMsg = boce.getMessage();
			assertTrue(errMsg.equalsIgnoreCase(InstructionCreationException.VECTOR_OPERANDS_MUST_BE_SIMILAR));
		}
		assertNull(binaryOpr6);
	}
	
	// Test attempts to create Binary operators with the exact flag and operators
	// that require floating point operands. For example, the "fadd", "fsub" or 
	// "fmul" operations have no meaning for the "exact", "nsw" or "nuw" flags.
	@Test
	public void testInvalidBinOpTypeCreationWithWrongFlagsAndFloatingPointOperands(){

		Type halfType = Type.getHalfType(compilationContext);
		Type floatType = Type.getFloatType(compilationContext);
		Type doubleType = Type.getDoubleType(compilationContext);
		Type fp128Type = Type.getFP128Type(compilationContext);
		Type ppcfp128Type = Type.getPPCFP128Type(compilationContext);
		Type x86fp80Type = Type.getX86_FP80Type(compilationContext);
		
		BinaryOperator binaryOpr1 = null;
		BinaryOperatorID binOpId1 = BinaryOperatorID.FADD;
		Value value1 = new Value(halfType); 
		Value value2 = new Value(halfType);
		try{
			binaryOpr1 =  BinaryOperator.create(binOpId1, 
					halfType, "@abc", value1, value2, false, false, true, null);
		}
		catch(InstructionCreationException boce){
			String errMsg = boce.getMessage();
			assertTrue(errMsg.equalsIgnoreCase(
					InstructionCreationException.EXACT_CANNOT_BE_USED_FOR_FLOATING_POINT_OPERANDS));
		}
		assertNull(binaryOpr1);
		
		BinaryOperator binaryOpr2 = null;
		BinaryOperatorID binOpId2 = BinaryOperatorID.FSUB;
		Value value3 = new Value(floatType); 
		Value value4 = new Value(floatType);
		try{
			binaryOpr2 =  BinaryOperator.create(binOpId2, 
					floatType, "@abc", value3, value4, false, false, true, null);
		}
		catch(InstructionCreationException boce){
			String errMsg = boce.getMessage();
			assertTrue(errMsg.equalsIgnoreCase(
					InstructionCreationException.EXACT_CANNOT_BE_USED_FOR_FLOATING_POINT_OPERANDS));
		}
		assertNull(binaryOpr2);
		
		BinaryOperator binaryOpr3 = null;
		BinaryOperatorID binOpId3 = BinaryOperatorID.FMUL;
		Value value5 = new Value(doubleType); 
		Value value6 = new Value(doubleType);
		try{
			binaryOpr3 =  BinaryOperator.create(binOpId3, 
					doubleType, "@abc", value5, value6, false, false, true, null);
		}
		catch(InstructionCreationException boce){
			String errMsg = boce.getMessage();
			assertTrue(errMsg.equalsIgnoreCase(InstructionCreationException.EXACT_CANNOT_BE_USED_FOR_FLOATING_POINT_OPERANDS));
		}
		assertNull(binaryOpr3);
		
		BinaryOperator binaryOpr4 = null;
		BinaryOperatorID binOpId4 = BinaryOperatorID.FDIV;
		Value value7 = new Value(fp128Type); 
		Value value8 = new Value(fp128Type);
		try{
			binaryOpr4 =  BinaryOperator.create(binOpId4, 
					fp128Type, "@abc", value7, value8, false, false, true, null);
		}
		catch(InstructionCreationException boce){
			String errMsg = boce.getMessage();
			assertTrue(errMsg.equalsIgnoreCase(
					InstructionCreationException.EXACT_CANNOT_BE_USED_FOR_FLOATING_POINT_OPERANDS));
		}
		assertNull(binaryOpr4);
		
		BinaryOperator binaryOpr5 = null;
		BinaryOperatorID binOpId5 = BinaryOperatorID.FREM;
		Value value9 = new Value(ppcfp128Type); 
		Value value10 = new Value(ppcfp128Type);
		try{
			binaryOpr5 =  BinaryOperator.create(binOpId5, 
					ppcfp128Type, "@abc", value9, value10, false, false, true, null);
		}
		catch(InstructionCreationException boce){
			String errMsg = boce.getMessage();
			assertTrue(errMsg.equalsIgnoreCase(
					InstructionCreationException.EXACT_CANNOT_BE_USED_FOR_FLOATING_POINT_OPERANDS));
		}
		assertNull(binaryOpr5);
		
		BinaryOperator binaryOpr6 = null;
		BinaryOperatorID binOpId6 = BinaryOperatorID.FSUB;
		Value value11 = new Value(x86fp80Type); 
		Value value12 = new Value(x86fp80Type);
		try{
			binaryOpr6 =  BinaryOperator.create(binOpId6, 
					x86fp80Type, "@abc", value11, value12, false, false, true, null);
		}
		catch(InstructionCreationException boce){
			String errMsg = boce.getMessage();
			assertTrue(errMsg.equalsIgnoreCase(
					InstructionCreationException.EXACT_CANNOT_BE_USED_FOR_FLOATING_POINT_OPERANDS));
		}
		assertNull(binaryOpr6);
		
		// Try with "nsw" or "nuw" flags
		BinaryOperator binaryOpr7 = null;
		BinaryOperatorID binOpId7 = BinaryOperatorID.FADD;
		Value value13 = new Value(halfType); 
		Value value14 = new Value(halfType);
		try{
			binaryOpr7 =  BinaryOperator.create(binOpId7, 
					halfType, "@abc", value13, value14, true, false, false, null);
		}
		catch(InstructionCreationException boce){
			String errMsg = boce.getMessage();
			assertTrue(errMsg.equalsIgnoreCase(
					InstructionCreationException.WRAP_FLAG_CANNOT_BE_USED_FOR_FLOATING_POINT_OPERANDS));
		}
		assertNull(binaryOpr7);
		
		BinaryOperator binaryOpr8 = null;
		BinaryOperatorID binOpId8 = BinaryOperatorID.FSUB;
		Value value15 = new Value(floatType); 
		Value value16 = new Value(floatType);
		try{
			binaryOpr8 =  BinaryOperator.create(binOpId8, 
					floatType, "@abc", value15, value16, false, true, false, null);
		}
		catch(InstructionCreationException boce){
			String errMsg = boce.getMessage();
			assertTrue(errMsg.equalsIgnoreCase(InstructionCreationException.WRAP_FLAG_CANNOT_BE_USED_FOR_FLOATING_POINT_OPERANDS));
		}
		assertNull(binaryOpr8);
		
		BinaryOperator binaryOpr9 = null;
		BinaryOperatorID binOpId9 = BinaryOperatorID.FMUL;
		Value value17 = new Value(doubleType); 
		Value value18 = new Value(doubleType);
		try{
			binaryOpr9 =  BinaryOperator.create(binOpId9, 
					doubleType, "@abc", value17, value18, true, false, false, null);
		}
		catch(InstructionCreationException boce){
			String errMsg = boce.getMessage();
			assertTrue(errMsg.equalsIgnoreCase(
					InstructionCreationException.WRAP_FLAG_CANNOT_BE_USED_FOR_FLOATING_POINT_OPERANDS));
		}
		assertNull(binaryOpr9);
		
		BinaryOperator binaryOpr10 = null;
		BinaryOperatorID binOpId10 = BinaryOperatorID.FDIV;
		VectorType vecType1 = null;
		VectorType vecType2 = null;
		try{
			vecType1 = Type.getVectorType(compilationContext, fp128Type, 10);
			vecType2 = Type.getVectorType(compilationContext, fp128Type, 10);
		}
		catch(Exception e) {}
		
		assertNotNull(vecType1);
		assertNotNull(vecType2);
		
		Value value19 = new Value(doubleType); 
		Value value20 = new Value(doubleType);
		try{
			binaryOpr10 =  BinaryOperator.create(binOpId10, 
					doubleType, "@abc", value19, value20, false, true, false, null);
		}
		catch(InstructionCreationException boce){
			String errMsg = boce.getMessage();
			assertTrue(errMsg.equalsIgnoreCase(
					InstructionCreationException.WRAP_FLAG_CANNOT_BE_USED_FOR_FLOATING_POINT_OPERANDS));
		}
		assertNull(binaryOpr10);
		
		BinaryOperator binaryOpr11 = null;
		BinaryOperatorID binOpId11 = BinaryOperatorID.FREM;
		Value value21 = new Value(x86fp80Type); 
		Value value22 = new Value(x86fp80Type);
		try{
			binaryOpr11 =  BinaryOperator.create(binOpId11, 
					x86fp80Type, "@abc", value21, value22, true, false, false, null);
		}
		catch(InstructionCreationException boce){
			String errMsg = boce.getMessage();
			assertTrue(errMsg.equalsIgnoreCase(
					InstructionCreationException.WRAP_FLAG_CANNOT_BE_USED_FOR_FLOATING_POINT_OPERANDS));
		}
		assertNull(binaryOpr11);
	}

	// Test the description (String) values of the instructions
	@Test
	public void testInstructionStrings() throws InstantiationException{

		Type halfType = Type.getHalfType(compilationContext);
		Type floatType = Type.getFloatType(compilationContext);
		Type doubleType = Type.getDoubleType(compilationContext);
		Type fp128Type = Type.getFP128Type(compilationContext);
		Type ppcfp128Type = Type.getPPCFP128Type(compilationContext);
		Type x86fp80Type = Type.getX86_FP80Type(compilationContext);
		
		BinaryOperator binaryOpr1 = null;
		BinaryOperatorID binOpId1 = BinaryOperatorID.FADD;
		Value value1 = new Value(halfType); value1.setName("1");
		Value value2 = new Value(halfType); value2.setName("2");
		try{
			binaryOpr1 =  BinaryOperator.create(binOpId1,
					halfType, "abc", value1, value2, false, false, false, null);
		}
		catch(InstructionCreationException boce){ }
		
		assertNotNull(binaryOpr1);
		assertTrue(binaryOpr1.toString().equals("%abc = fadd half %1, %2"));
		
		BinaryOperator binaryOpr2 = null;
		BinaryOperatorID binOpId2 = BinaryOperatorID.FSUB;
		
		Value value3 = new Value(floatType); value3.setName("3");
		Value value4 = new Value(floatType); value4.setName("4");
		try{
			binaryOpr2 =  BinaryOperator.create(binOpId2,
					floatType, "abc", value3, value4, false, false, false, null);
		}
		catch(InstructionCreationException boce){ }
		
		assertNotNull(binaryOpr2);
		assertTrue(binaryOpr2.toString().equals("%abc = fsub float %3, %4"));
		
		BinaryOperator binaryOpr3 = null;
		BinaryOperatorID binOpId3 = BinaryOperatorID.FMUL;
		Value value5 = new Value(doubleType); value5.setName("5");
		
		Value value6 = new Value(doubleType); value6.setName("6");
		try{
			binaryOpr3 =  BinaryOperator.create(binOpId3, 
					doubleType, "abc", value5, value6, false, false, false, null);
		}
		catch(InstructionCreationException boce){ }
		
		assertNotNull(binaryOpr3);
		assertTrue(binaryOpr3.toString().equals("%abc = fmul double %5, %6"));
		
		BinaryOperator binaryOpr4 = null;
		BinaryOperatorID binOpId4 = BinaryOperatorID.FDIV;
		Value value7 = new Value(fp128Type); value7.setName("7");
		Value value8 = new Value(fp128Type); value8.setName("8");
		try{
			binaryOpr4 =  BinaryOperator.create(binOpId4, 
					fp128Type, "abc", value7, value8, false, false, false, null);
		}
		catch(InstructionCreationException boce){ }
		
		assertNotNull(binaryOpr4);
		assertTrue(binaryOpr4.toString().equals("%abc = fdiv fp128 %7, %8"));
		
		BinaryOperator binaryOpr5 = null;
		BinaryOperatorID binOpId5 = BinaryOperatorID.FREM;
		Value value9 = new Value(x86fp80Type); value9.setName("4");
		Value value10 = new Value(x86fp80Type); value10.setName("82");
		try{
			binaryOpr5 =  BinaryOperator.create(binOpId5, 
					x86fp80Type, "abc", value9, value10, false, false, false, null);
		}
		catch(InstructionCreationException boce){ }
		
		assertNotNull(binaryOpr5);
		assertTrue(binaryOpr5.toString().equals("%abc = frem x86_fp80 %4, %82"));
		
		BinaryOperator binaryOpr6 = null;
		BinaryOperatorID binOpId6 = BinaryOperatorID.FADD;
		
		VectorType vec1 = null, vec2 = null;
		try{
			vec1 = Type.getVectorType(compilationContext, ppcfp128Type, 12);
			vec2 = Type.getVectorType(compilationContext, ppcfp128Type, 12);
		}
		catch(Exception e) {}
		
		Value value11 = new Value(vec1); value11.setName("12");
		Value value12 = new Value(vec2); value12.setName("34");
		try{
			binaryOpr6 =  BinaryOperator.create(binOpId6, 
					vec1, "abc", value11, value12, false, false, false, null);
		}
		catch(InstructionCreationException boce){ 
			boce.printStackTrace();
		}
		
		assertNotNull(binaryOpr6);
		assertTrue(binaryOpr6.toString().equals("%abc = fadd <12 x ppc_fp128> %12, %34"));
		
		// Now any remaining operations that need to be tested for integer operands
		Type int32Type = Type.getInt32Type(compilationContext, true);
		Type int64Type = Type.getInt64Type(compilationContext, true);
		Type int1Type = Type.getInt1Type(compilationContext, false);
		Type int8Type = Type.getInt8Type(compilationContext, true);
		
		BinaryOperator binaryOpr7 = null;
		BinaryOperatorID binOpId7 = BinaryOperatorID.UDIV;
		
		VectorType vec3 = null, vec4 = null;
		try{
			vec3 = Type.getVectorType(compilationContext, int32Type, 3);
			vec4 = Type.getVectorType(compilationContext, int32Type, 3);
		}
		catch(Exception e) {}
		
		Value value13 = new Value(vec3); value13.setName("5");
		Value value14 = new Value(vec4); value14.setName("43");
		try{
			binaryOpr7 =  BinaryOperator.create(binOpId7, 
					vec3, "abc", value13, value14, false, false, true, null);
		}
		catch(InstructionCreationException boce){ 
			boce.printStackTrace();
		}
		
		assertNotNull(binaryOpr7);
		assertTrue(binaryOpr7.toString().equals("%abc = udiv exact <3 x i32> %5, %43"));
		
		BinaryOperator binaryOpr8 = null;
		BinaryOperatorID binOpId8 = BinaryOperatorID.SDIV;
		
		Value value15 = new Value(int1Type); value15.setName("3");
		Value value16 = new Value(int1Type); value16.setName("56");
		try{
			binaryOpr8 =  BinaryOperator.create(binOpId8, 
					int1Type, "abc", value15, value16, false, false, true, null);
		}
		catch(InstructionCreationException boce){ 
			boce.printStackTrace();
		}
		
		assertNotNull(binaryOpr8);
		assertTrue(binaryOpr8.toString().equals("%abc = sdiv exact i1 %3, %56"));
		
		BinaryOperator binaryOpr9 = null;
		BinaryOperatorID binOpId9 = BinaryOperatorID.UREM;
		
		Value value17 = new Value(int8Type); value17.setName("98");
		Value value18 = new Value(int8Type); value18.setName("2");
		try{
			binaryOpr9 =  BinaryOperator.create(binOpId9, 
					int8Type, "abc", value17, value18, false, false, false, null);
		}
		catch(InstructionCreationException boce){ 
			boce.printStackTrace();
		}
		
		assertNotNull(binaryOpr9);
		assertTrue(binaryOpr9.toString().equals("%abc = urem i8 %98, %2"));
		
		BinaryOperator binaryOpr10 = null;
		BinaryOperatorID binOpId10 = BinaryOperatorID.SREM;
		
		Value value19 = new Value(int64Type); value19.setName("1");
		Value value20 = new Value(int64Type); value20.setName("24");
		try{
			binaryOpr10 =  BinaryOperator.create(binOpId10, 
					int64Type, "abc", value19, value20, false, false, false, null);
		}
		catch(InstructionCreationException boce){ 
			boce.printStackTrace();
		}
		
		assertNotNull(binaryOpr10);
		assertTrue(binaryOpr10.toString().equals("%abc = srem i64 %1, %24"));
		
		BinaryOperator binaryOpr11 = null;
		BinaryOperatorID binOpId11 = BinaryOperatorID.AND;
		
		Value value21 = new Value(int8Type); value21.setName("9");
		Value value22 = new Value(int8Type); value22.setName("2");
		try{
			binaryOpr11 =  BinaryOperator.create(binOpId11, 
					int8Type, "abc", value21, value22, true, true, false, null);
		}
		catch(InstructionCreationException boce){ 
			boce.printStackTrace();
		}
		assertNotNull(binaryOpr11);
		assertTrue(binaryOpr11.toString().equals("%abc = and nuw nsw i8 %9, %2"));
		
		BinaryOperator binaryOpr12 = null;
		BinaryOperatorID binOpId12 = BinaryOperatorID.OR;
		
		Value value23 = new Value(int1Type); value23.setName("98");
		ConstantInt constInt = ConstantInt.create((IntegerType)int1Type, 98, false);
		Value value24 = new Value(int1Type); value24.setName("21");
		try{
			binaryOpr12 =  BinaryOperator.create(binOpId12, 
					int1Type, "abc", constInt, value24, false, false, false, null);
		}
		catch(InstructionCreationException boce){ 
			boce.printStackTrace();
		}
		assertNotNull(binaryOpr12);
		assertTrue(binaryOpr12.toString().equals("%abc = or i1 98, %21"));
		
		BinaryOperator binaryOpr13 = null;
		BinaryOperatorID binOpId13 = BinaryOperatorID.XOR;
		
		Value value25 = new Value(int32Type); value25.setName("3");
		Value value26 = new Value(int32Type); value26.setName("4");
		try{
			binaryOpr13 =  BinaryOperator.create(binOpId13, 
					int32Type, "abc", value25, value26, false, false, false, null);
		}
		catch(InstructionCreationException boce){ 
			boce.printStackTrace();
		}
		assertNotNull(binaryOpr13);
		assertTrue(binaryOpr13.toString().equals("%abc = xor i32 %3, %4"));

	}
}

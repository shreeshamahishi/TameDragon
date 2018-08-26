package org.tamedragon.common.controlflowanalysis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.tamedragon.common.controlflowanalysis.ExpressionElement;
import org.tamedragon.common.controlflowanalysis.IndVarExpression;
import org.tamedragon.common.controlflowanalysis.InductionVariable;
import org.tamedragon.common.llvmir.instructions.BinaryOperator.BinaryOperatorID;
import org.tamedragon.common.llvmir.math.APInt;
import org.tamedragon.common.llvmir.types.APFloat;
import org.tamedragon.common.llvmir.types.CompilationContext;
import org.tamedragon.common.llvmir.types.ConstantFP;
import org.tamedragon.common.llvmir.types.ConstantInt;
import org.tamedragon.common.llvmir.types.IntegerType;
import org.tamedragon.common.llvmir.types.Type;
import org.tamedragon.common.llvmir.types.Value;

public class IndVarExpressionsTest {

	private CompilationContext compilationContext;

	@Before
	public void setUp(){
		compilationContext = new CompilationContext();
	}

	@Test
	public void testPostfixIntegerOperationWithConstant(){
		try{
			Type type = Type.getInt32Type(compilationContext, true);
			IntegerType intType = (IntegerType) type;

			// Create the basic ind var
			Value basicVal = new Value(type);
			basicVal.setName("i");
			IndVarExpression basicIndVarExpr = new IndVarExpression(basicVal, null, null, true);

			// Create the dependent ind var
			APInt apInt = new APInt(intType.getNumBits(), "12", intType.isSigned());
			ConstantInt incrVal = new ConstantInt(intType, apInt);

			IndVarExpression dependentIndVar1 = new IndVarExpression(
					incrVal, basicIndVarExpr, BinaryOperatorID.ADD, true);
			assertEquals("%i+12", dependentIndVar1.toString());

			IndVarExpression dependentIndVar2 = new IndVarExpression(
					incrVal, basicIndVarExpr, BinaryOperatorID.SUB, true);
			assertEquals("%i-12", dependentIndVar2.toString());

			IndVarExpression dependentIndVar3 = new IndVarExpression(
					incrVal, basicIndVarExpr, BinaryOperatorID.MUL, true);
			assertEquals("%i*12", dependentIndVar3.toString());

		}
		catch(Exception e){
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void testPostfixFPOperationWithConstant(){
		try{
			Type type = Type.getDoubleType(compilationContext);

			// Create the basic ind var
			Value basicVal = new Value(type);
			basicVal.setName("i");
			IndVarExpression basicIndVarExpr = new IndVarExpression(basicVal, null, null, true);

			// Create the dependent ind var
			APFloat apFloat = new APFloat(APFloat.IEEEdouble,"1.23");
			ConstantFP constFP = new ConstantFP(type, apFloat);

			IndVarExpression dependentIndVar1 = new IndVarExpression(
					constFP, basicIndVarExpr, BinaryOperatorID.FADD, true);
			assertEquals("%i+1.230000e+00", dependentIndVar1.toString());

			IndVarExpression dependentIndVar2 = new IndVarExpression(
					constFP, basicIndVarExpr, BinaryOperatorID.FSUB, true);
			assertEquals("%i-1.230000e+00", dependentIndVar2.toString());

			IndVarExpression dependentIndVar3 = new IndVarExpression(
					constFP, basicIndVarExpr, BinaryOperatorID.FMUL, true);
			assertEquals("%i*1.230000e+00", dependentIndVar3.toString());

		}
		catch(Exception e){
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void testPostfixIntegerOperationWithNonConstant(){
		try{
			Type type = Type.getInt32Type(compilationContext, true);

			// Create the basic ind var
			Value basicVal = new Value(type);
			basicVal.setName("x");
			IndVarExpression basicIndVarExpr = new IndVarExpression(basicVal, null, null, true);

			// Create the dependent ind var
			Value operandVal = new Value(type);
			operandVal.setName("y");

			IndVarExpression dependentIndVar1 = new IndVarExpression(
					operandVal, basicIndVarExpr, BinaryOperatorID.ADD, true);
			assertEquals("%x+%y", dependentIndVar1.toString());

			IndVarExpression dependentIndVar2 = new IndVarExpression(
					operandVal, basicIndVarExpr, BinaryOperatorID.SUB, true);
			assertEquals("%x-%y", dependentIndVar2.toString());

			IndVarExpression dependentIndVar3 = new IndVarExpression(
					operandVal, basicIndVarExpr, BinaryOperatorID.MUL, true);
			assertEquals("%x*%y", dependentIndVar3.toString());

		}
		catch(Exception e){
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void testPostfixFPOperationWithNonConstant(){
		try{
			Type type = Type.getDoubleType(compilationContext);

			// Create the basic ind var
			Value basicVal = new Value(type);
			basicVal.setName("i");
			IndVarExpression basicIndVarExpr = new IndVarExpression(basicVal, null, null, true);

			// Create the dependent ind var
			Value operandVal = new Value(type);
			operandVal.setName("j");

			IndVarExpression dependentIndVar1 = new IndVarExpression(
					operandVal, basicIndVarExpr, BinaryOperatorID.FADD, true);
			assertEquals("%i+%j", dependentIndVar1.toString());

			IndVarExpression dependentIndVar2 = new IndVarExpression(
					operandVal, basicIndVarExpr, BinaryOperatorID.FSUB, true);
			assertEquals("%i-%j", dependentIndVar2.toString());

			IndVarExpression dependentIndVar3 = new IndVarExpression(
					operandVal, basicIndVarExpr, BinaryOperatorID.FMUL, true);
			assertEquals("%i*%j", dependentIndVar3.toString());

		}
		catch(Exception e){
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void testIntegerPostfixOperationOfExprWithConstantNoFolding(){
		try{
			Type type = Type.getInt32Type(compilationContext, true);
			IntegerType intType = (IntegerType) type;

			// Create the basic ind var
			Value basicVal = new Value(type);
			basicVal.setName("i");
			IndVarExpression basicIndVarExpr = new IndVarExpression(basicVal, null, null, true);

			// Create the dependent ind var
			Value operandVal = new Value(type);
			operandVal.setName("j");

			IndVarExpression dependentIndVar1 = new IndVarExpression(
					operandVal, basicIndVarExpr, BinaryOperatorID.ADD, true);
			assertEquals("%i+%j", dependentIndVar1.toString());

			Value operandVal2 = new Value(type);
			operandVal2.setName("k");
			IndVarExpression dependentIndVar2 = new IndVarExpression(
					operandVal2, dependentIndVar1, BinaryOperatorID.SUB, true);
			assertEquals("%i+%j-%k", dependentIndVar2.toString());

			Value operandVal3 = new Value(type);
			operandVal3.setName("m");
			IndVarExpression dependentIndVar3 = new IndVarExpression(
					operandVal3, dependentIndVar2, BinaryOperatorID.MUL, true);
			assertEquals("%i*%m+%j*%m-%k*%m", dependentIndVar3.toString());

			APInt apInt = new APInt(intType.getNumBits(), "12", intType.isSigned());
			ConstantInt operandVal4 = new ConstantInt( intType, apInt);
			IndVarExpression dependentIndVar4 = new IndVarExpression(
					operandVal4, dependentIndVar3, BinaryOperatorID.MUL, true);
			assertEquals("%i*%m*12+%j*%m*12-%k*%m*12", dependentIndVar4.toString());

			APInt apInt1 = new APInt(intType.getNumBits(), "4", intType.isSigned());
			ConstantInt operandVal5 = new ConstantInt( intType, apInt1);
			IndVarExpression dependentIndVar5 = new IndVarExpression(
					operandVal5, dependentIndVar4, BinaryOperatorID.ADD, true);
			assertEquals("%i*%m*12+%j*%m*12-%k*%m*12+4", dependentIndVar5.toString());

			Value operandVal6 = new Value(type);
			operandVal6.setName("x");
			IndVarExpression dependentIndVar6 = new IndVarExpression(
					operandVal6, dependentIndVar5, BinaryOperatorID.MUL, true);
			assertEquals("%i*%m*12*%x+%j*%m*12*%x-%k*%m*12*%x+4*%x", dependentIndVar6.toString());

			APInt apInt2 = new APInt(intType.getNumBits(), "56", intType.isSigned());
			ConstantInt operandVal7 = new ConstantInt( intType, apInt2);
			IndVarExpression dependentIndVar7 = new IndVarExpression(
					operandVal7, dependentIndVar6, BinaryOperatorID.SUB, true);
			assertEquals("%i*%m*12*%x+%j*%m*12*%x-%k*%m*12*%x+4*%x-56", dependentIndVar7.toString());

		}
		catch(Exception e){
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void testFPPostfixOperationOfExprWithConstantNoFolding(){
		try{
			Type type = Type.getDoubleType(compilationContext);

			// Create the basic ind var
			Value basicVal = new Value(type);
			basicVal.setName("i");
			IndVarExpression basicIndVarExpr = new IndVarExpression(basicVal, null, null, true);

			// Create the dependent ind var
			Value operandVal = new Value(type);
			operandVal.setName("j");

			IndVarExpression dependentIndVar1 = new IndVarExpression(
					operandVal, basicIndVarExpr, BinaryOperatorID.FADD, true);
			assertEquals("%i+%j", dependentIndVar1.toString());

			Value operandVal2 = new Value(type);
			operandVal2.setName("k");
			IndVarExpression dependentIndVar2 = new IndVarExpression(
					operandVal2, dependentIndVar1, BinaryOperatorID.FSUB, true);
			assertEquals("%i+%j-%k", dependentIndVar2.toString());

			Value operandVal3 = new Value(type);
			operandVal3.setName("m");
			IndVarExpression dependentIndVar3 = new IndVarExpression(
					operandVal3, dependentIndVar2, BinaryOperatorID.FMUL, true);
			assertEquals("%i*%m+%j*%m-%k*%m", dependentIndVar3.toString());

			APFloat apFloat = new APFloat(APFloat.IEEEdouble,"1.23");
			ConstantFP operandVal4 = new ConstantFP( type, apFloat);
			IndVarExpression dependentIndVar4 = new IndVarExpression(
					operandVal4, dependentIndVar3, BinaryOperatorID.FMUL, true);
			assertEquals("%i*%m*1.230000e+00+%j*%m*1.230000e+00-%k*%m*1.230000e+00", dependentIndVar4.toString());

			APFloat apFloat1 = new APFloat(APFloat.IEEEdouble, "4.0");
			ConstantFP operandVal5 = new ConstantFP( type, apFloat1);
			IndVarExpression dependentIndVar5 = new IndVarExpression(
					operandVal5, dependentIndVar4, BinaryOperatorID.FADD, true);
			assertEquals("%i*%m*1.230000e+00+%j*%m*1.230000e+00-%k*%m*1.230000e+00+4.000000e+00", dependentIndVar5.toString());

			Value operandVal6 = new Value(type);
			operandVal6.setName("x");
			IndVarExpression dependentIndVar6 = new IndVarExpression(
					operandVal6, dependentIndVar5, BinaryOperatorID.FMUL, true);
			assertEquals("%i*%m*1.230000e+00*%x+%j*%m*1.230000e+00*%x-%k*%m*1.230000e+00*%x+4.000000e+00*%x", dependentIndVar6.toString());

			APFloat apFloat2 = new APFloat(APFloat.IEEEdouble, "5.6");
			ConstantFP operandVal7 = new ConstantFP(type, apFloat2);
			IndVarExpression dependentIndVar7 = new IndVarExpression(
					operandVal7, dependentIndVar6, BinaryOperatorID.FSUB, true);
			assertEquals("%i*%m*1.230000e+00*%x+%j*%m*1.230000e+00*%x-%k*%m*1.230000e+00*%x+4.000000e+00*%x-5.600000e+00", dependentIndVar7.toString());

		}
		catch(Exception e){
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void testIntegerPrefixOperationOfExprWithConstantNoFolding(){
		try{
			Type type = Type.getInt32Type(compilationContext, true);
			IntegerType intType = (IntegerType) type;

			// Create the basic ind var
			Value basicVal = new Value(type);
			basicVal.setName("i");
			IndVarExpression basicIndVarExpr = new IndVarExpression(basicVal, null, null, true);

			// Create the dependent ind var
			Value operandVal = new Value(type);
			operandVal.setName("j");

			IndVarExpression dependentIndVar1 = new IndVarExpression(
					operandVal, basicIndVarExpr, BinaryOperatorID.ADD, false);
			assertEquals("%j+%i", dependentIndVar1.toString());

			Value operandVal2 = new Value(type);
			operandVal2.setName("k");
			IndVarExpression dependentIndVar2 = new IndVarExpression(
					operandVal2, dependentIndVar1, BinaryOperatorID.SUB, false);
			assertEquals("%k-%j-%i", dependentIndVar2.toString());

			Value operandVal3 = new Value(type);
			operandVal3.setName("m");
			IndVarExpression dependentIndVar3 = new IndVarExpression(
					operandVal3, dependentIndVar2, BinaryOperatorID.MUL, false);
			assertEquals("%m*%k-%m*%j-%m*%i", dependentIndVar3.toString());

			Value operandVal4 = new Value(type);
			operandVal4.setName("x");
			IndVarExpression dependentIndVar4 = new IndVarExpression(
					operandVal4, dependentIndVar3, BinaryOperatorID.SUB, false);
			assertEquals("%x-%m*%k+%m*%j+%m*%i", dependentIndVar4.toString());

			APInt apInt1 = new APInt(intType.getNumBits(), "98", intType.isSigned());
			ConstantInt operandVal5 = new ConstantInt( intType, apInt1);
			IndVarExpression dependentIndVar5 = new IndVarExpression(
					operandVal5, dependentIndVar4, BinaryOperatorID.MUL, false);
			assertEquals("98*%x-98*%m*%k+98*%m*%j+98*%m*%i", dependentIndVar5.toString());

			APInt apInt2 = new APInt(intType.getNumBits(), "19", intType.isSigned());
			ConstantInt operandVal6 = new ConstantInt( intType, apInt2);
			IndVarExpression dependentIndVar6 = new IndVarExpression(
					operandVal6, dependentIndVar5, BinaryOperatorID.SUB, false);
			assertEquals("19-98*%x+98*%m*%k-98*%m*%j-98*%m*%i", dependentIndVar6.toString());

		}
		catch(Exception e){
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void testFPPrefixOperationOfExprWithConstantNoFolding(){
		try{
			Type type = Type.getDoubleType(compilationContext);

			// Create the basic ind var
			Value basicVal = new Value(type);
			basicVal.setName("i");
			IndVarExpression basicIndVarExpr = new IndVarExpression(basicVal, null, null, true);

			// Create the dependent ind var
			Value operandVal = new Value(type);
			operandVal.setName("j");

			IndVarExpression dependentIndVar1 = new IndVarExpression(
					operandVal, basicIndVarExpr, BinaryOperatorID.FADD, false);
			assertEquals("%j+%i", dependentIndVar1.toString());

			Value operandVal2 = new Value(type);
			operandVal2.setName("k");
			IndVarExpression dependentIndVar2 = new IndVarExpression(
					operandVal2, dependentIndVar1, BinaryOperatorID.FSUB, false);
			assertEquals("%k-%j-%i", dependentIndVar2.toString());

			Value operandVal3 = new Value(type);
			operandVal3.setName("m");
			IndVarExpression dependentIndVar3 = new IndVarExpression(
					operandVal3, dependentIndVar2, BinaryOperatorID.FMUL, false);
			assertEquals("%m*%k-%m*%j-%m*%i", dependentIndVar3.toString());

			Value operandVal4 = new Value(type);
			operandVal4.setName("x");
			IndVarExpression dependentIndVar4 = new IndVarExpression(
					operandVal4, dependentIndVar3, BinaryOperatorID.FSUB, false);
			assertEquals("%x-%m*%k+%m*%j+%m*%i", dependentIndVar4.toString());

			APFloat apFloat1 = new APFloat(APFloat.IEEEdouble, "98");
			ConstantFP operandVal5 = new ConstantFP(type, apFloat1);
			IndVarExpression dependentIndVar5 = new IndVarExpression(
					operandVal5, dependentIndVar4, BinaryOperatorID.FMUL, false);
			assertEquals("9.800000e+01*%x-9.800000e+01*%m*%k+9.800000e+01*%m*%j+9.800000e+01*%m*%i", dependentIndVar5.toString());

			APFloat apFloat2 = new APFloat(APFloat.IEEEdouble, "19");
			ConstantFP operandVal6 = new ConstantFP(type, apFloat2);
			IndVarExpression dependentIndVar6 = new IndVarExpression(
					operandVal6, dependentIndVar5, BinaryOperatorID.FSUB, false);
			assertEquals("1.900000e+01-9.800000e+01*%x+9.800000e+01*%m*%k-9.800000e+01*%m*%j-9.800000e+01*%m*%i", dependentIndVar6.toString());

		}
		catch(Exception e){
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void testIntegerPostfixOperationOfExprWithConstantFolding(){
		try{
			Type type = Type.getInt32Type(compilationContext, true);
			IntegerType intType = (IntegerType) type;

			// Create the basic ind var
			Value basicVal = new Value(type);
			basicVal.setName("i");
			IndVarExpression basicIndVarExpr = new IndVarExpression(basicVal, null, null, true);

			APInt apInt = new APInt(intType.getNumBits(), "43", intType.isSigned());
			ConstantInt operandVal1 = new ConstantInt( intType, apInt);
			IndVarExpression dependentIndVar1 = new IndVarExpression(
					operandVal1, basicIndVarExpr, BinaryOperatorID.ADD, true);
			assertEquals("%i+43", dependentIndVar1.toString());

			Value operandVal2 = new Value(type);
			operandVal2.setName("k");
			IndVarExpression dependentIndVar2 = new IndVarExpression(
					operandVal2, dependentIndVar1, BinaryOperatorID.SUB, true);
			assertEquals("%i+43-%k", dependentIndVar2.toString());

			// a - b (b is positive)
			APInt apInt1 = new APInt(intType.getNumBits(), "20", intType.isSigned());
			ConstantInt operandVal3 = new ConstantInt( intType, apInt1);
			IndVarExpression dependentIndVar3 = new IndVarExpression(
					operandVal3, dependentIndVar2, BinaryOperatorID.SUB, true);
			assertEquals("%i+23-%k", dependentIndVar3.toString());

			// a + b (b is positive)
			APInt apInt2 = new APInt(intType.getNumBits(), "101", intType.isSigned());
			ConstantInt operandVal4 = new ConstantInt( intType, apInt2);
			IndVarExpression dependentIndVar4 = new IndVarExpression(
					operandVal4, dependentIndVar3, BinaryOperatorID.ADD, true);
			assertEquals("%i+124-%k", dependentIndVar4.toString());

			// a - b (b is positive and larger)
			APInt apInt3 = new APInt(intType.getNumBits(), "200", intType.isSigned());
			ConstantInt operandVal5 = new ConstantInt( intType, apInt3);
			IndVarExpression dependentIndVar5 = new IndVarExpression(
					operandVal5, dependentIndVar4, BinaryOperatorID.SUB, true);
			assertEquals("%i+-76-%k", dependentIndVar5.toString());

			// -a + (-b) = -1 * (a + b)
			APInt apInt4 = new APInt(intType.getNumBits(), "-21", intType.isSigned());
			ConstantInt operandVal6 = new ConstantInt( intType, apInt4);
			IndVarExpression dependentIndVar6 = new IndVarExpression(
					operandVal6, dependentIndVar5, BinaryOperatorID.ADD, true);
			assertEquals("%i+-97-%k", dependentIndVar6.toString());

			// -a + b = b -a
			APInt apInt5 = new APInt(intType.getNumBits(), "100", intType.isSigned());
			ConstantInt operandVal7 = new ConstantInt( intType, apInt5);
			IndVarExpression dependentIndVar7 = new IndVarExpression(
					operandVal7, dependentIndVar6, BinaryOperatorID.ADD, true);
			assertEquals("%i+3-%k", dependentIndVar7.toString());

			// a + (-b) = a - b
			APInt apInt6 = new APInt(intType.getNumBits(), "-200", intType.isSigned());
			ConstantInt operandVal8 = new ConstantInt( intType, apInt6);
			IndVarExpression dependentIndVar8 = new IndVarExpression(
					operandVal8, dependentIndVar7, BinaryOperatorID.ADD, true);
			assertEquals("%i+-197-%k", dependentIndVar8.toString());

			// -a - b = -(a+b)
			APInt apInt7 = new APInt(intType.getNumBits(), "23", intType.isSigned());
			ConstantInt operandVal9 = new ConstantInt( intType, apInt7);
			IndVarExpression dependentIndVar9 = new IndVarExpression(
					operandVal9, dependentIndVar8, BinaryOperatorID.SUB, true);
			assertEquals("%i+-220-%k", dependentIndVar9.toString());

			// -a - (-b) = b -a
			APInt apInt8 = new APInt(intType.getNumBits(), "-400", intType.isSigned());
			ConstantInt operandVal10 = new ConstantInt( intType, apInt8);
			IndVarExpression dependentIndVar10 = new IndVarExpression(
					operandVal10, dependentIndVar9, BinaryOperatorID.SUB, true);
			assertEquals("%i+180-%k", dependentIndVar10.toString());

			// a * b
			APInt apInt9 = new APInt(intType.getNumBits(), "2", intType.isSigned());
			ConstantInt operandVal11 = new ConstantInt( intType, apInt9);
			IndVarExpression dependentIndVar11 = new IndVarExpression(
					operandVal11, dependentIndVar10, BinaryOperatorID.MUL, true);
			assertEquals("%i*2+360-%k*2", dependentIndVar11.toString());

			// a - b (b > a)
			APInt apInt10 = new APInt(intType.getNumBits(), "400", intType.isSigned());
			ConstantInt operandVal12 = new ConstantInt( intType, apInt10);
			IndVarExpression dependentIndVar12 = new IndVarExpression(
					operandVal12, dependentIndVar11, BinaryOperatorID.SUB, true);
			assertEquals("%i*2+-40-%k*2", dependentIndVar12.toString());

			// -a * b 
			APInt apInt11 = new APInt(intType.getNumBits(), "2", intType.isSigned());
			ConstantInt operandVal13 = new ConstantInt( intType, apInt11);
			IndVarExpression dependentIndVar13 = new IndVarExpression(
					operandVal13, dependentIndVar12, BinaryOperatorID.MUL, true);
			assertEquals("%i*4+-80-%k*4", dependentIndVar13.toString());

			// -a * (-b) 
			APInt apInt12 = new APInt(intType.getNumBits(), "-3", intType.isSigned());
			ConstantInt operandVal14 = new ConstantInt( intType, apInt12);
			IndVarExpression dependentIndVar14 = new IndVarExpression(
					operandVal14, dependentIndVar13, BinaryOperatorID.MUL, true);
			assertEquals("%i*-12+240-%k*-12", dependentIndVar14.toString());

		}
		catch(Exception e){
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void testFPPostfixOperationOfExprWithConstantFolding(){
		try{
			Type type = Type.getDoubleType(compilationContext);

			// Create the basic ind var
			Value basicVal = new Value(type);
			basicVal.setName("i");
			IndVarExpression basicIndVarExpr = new IndVarExpression(basicVal, null, null, true);

			APFloat apFloat = new APFloat(APFloat.IEEEdouble, "43.0");
			ConstantFP operandVal1 = new ConstantFP( type, apFloat);
			IndVarExpression dependentIndVar1 = new IndVarExpression(
					operandVal1, basicIndVarExpr, BinaryOperatorID.FADD, true);
			assertEquals("%i+4.300000e+01", dependentIndVar1.toString());

			Value operandVal2 = new Value(type);
			operandVal2.setName("k");
			IndVarExpression dependentIndVar2 = new IndVarExpression(
					operandVal2, dependentIndVar1, BinaryOperatorID.FSUB, true);
			assertEquals("%i+4.300000e+01-%k", dependentIndVar2.toString());

			// a - b (b is positive)
			APFloat apFloat1 = new APFloat(APFloat.IEEEdouble, "20.0");
			ConstantFP operandVal3 = new ConstantFP( type,  apFloat1);
			IndVarExpression dependentIndVar3 = new IndVarExpression(
					operandVal3, dependentIndVar2, BinaryOperatorID.FSUB, true);
			assertEquals("%i+2.300000e+01-%k", dependentIndVar3.toString());

			// a + b (b is positive)
			APFloat apFloat2 = new APFloat(APFloat.IEEEdouble,  "101.0");
			ConstantFP operandVal4 = new ConstantFP( type,   apFloat2);
			IndVarExpression dependentIndVar4 = new IndVarExpression(
					operandVal4, dependentIndVar3, BinaryOperatorID.FADD, true);
			assertEquals("%i+1.240000e+02-%k", dependentIndVar4.toString());

			// a - b (b is positive and larger)
			APFloat apFloat3 = new APFloat(APFloat.IEEEdouble,  "200.0");
			ConstantFP operandVal5 = new ConstantFP( type,   apFloat3);
			IndVarExpression dependentIndVar5 = new IndVarExpression(
					operandVal5, dependentIndVar4, BinaryOperatorID.FSUB, true);
			assertEquals("%i+-7.600000e+01-%k", dependentIndVar5.toString());

			// -a + (-b) = -1 * (a + b)
			APFloat apFloat4 = new APFloat(APFloat.IEEEdouble,  "-21.0");
			ConstantFP operandVal6 = new ConstantFP( type,   apFloat4);
			IndVarExpression dependentIndVar6 = new IndVarExpression(
					operandVal6, dependentIndVar5, BinaryOperatorID.FADD, true);
			assertEquals("%i+-9.700000e+01-%k", dependentIndVar6.toString());

			// -a + b = b -a
			APFloat apFloat5 = new APFloat(APFloat.IEEEdouble,  "100.0");
			ConstantFP operandVal7 = new ConstantFP( type,   apFloat5);
			IndVarExpression dependentIndVar7 = new IndVarExpression(
					operandVal7, dependentIndVar6, BinaryOperatorID.FADD, true);
			assertEquals("%i+3.000000e+00-%k", dependentIndVar7.toString());

			// a + (-b) = a - b
			APFloat apFloat6 = new APFloat(APFloat.IEEEdouble,  "-200.0");
			ConstantFP operandVal8 = new ConstantFP( type,   apFloat6);
			IndVarExpression dependentIndVar8 = new IndVarExpression(
					operandVal8, dependentIndVar7, BinaryOperatorID.FADD, true);
			assertEquals("%i+-1.970000e+02-%k", dependentIndVar8.toString());

			// -a - b = -(a+b)
			APFloat apFloat7 = new APFloat(APFloat.IEEEdouble,  "23.0");
			ConstantFP operandVal9 = new ConstantFP( type,   apFloat7);
			IndVarExpression dependentIndVar9 = new IndVarExpression(
					operandVal9, dependentIndVar8, BinaryOperatorID.FSUB, true);
			assertEquals("%i+-2.200000e+02-%k", dependentIndVar9.toString());

			// -a - (-b) = b -a
			APFloat apFloat8 = new APFloat(APFloat.IEEEdouble,  "-400.0");
			ConstantFP operandVal10 = new ConstantFP( type,   apFloat8);
			IndVarExpression dependentIndVar10 = new IndVarExpression(
					operandVal10, dependentIndVar9, BinaryOperatorID.FSUB, true);
			assertEquals("%i+1.800000e+02-%k", dependentIndVar10.toString());

			// a * b
			APFloat apFloat9 = new APFloat(APFloat.IEEEdouble,  "2.0");
			ConstantFP operandVal11 = new ConstantFP( type,   apFloat9);
			IndVarExpression dependentIndVar11 = new IndVarExpression(
					operandVal11, dependentIndVar10, BinaryOperatorID.FMUL, true);
			assertEquals("%i*2.000000e+00+3.600000e+02-%k*2.000000e+00", dependentIndVar11.toString());

			// a - b (b > a)
			APFloat apFloat10 = new APFloat(APFloat.IEEEdouble,  "400.0");
			ConstantFP operandVal12 = new ConstantFP( type,   apFloat10);
			IndVarExpression dependentIndVar12 = new IndVarExpression(
					operandVal12, dependentIndVar11, BinaryOperatorID.FSUB, true);
			assertEquals("%i*2.000000e+00+-4.000000e+01-%k*2.000000e+00", dependentIndVar12.toString());

			// -a * b 
			APFloat apFloat11 = new APFloat(APFloat.IEEEdouble,  "2.0");
			ConstantFP operandVal13 = new ConstantFP( type,   apFloat11);
			IndVarExpression dependentIndVar13 = new IndVarExpression(
					operandVal13, dependentIndVar12, BinaryOperatorID.FMUL, true);
			assertEquals("%i*4.000000e+00+-8.000000e+01-%k*4.000000e+00", dependentIndVar13.toString());

			// -a * (-b) 
			APFloat apFloat12 = new APFloat(APFloat.IEEEdouble,  "-3.0");
			ConstantFP operandVal14 = new ConstantFP( type,   apFloat12);
			IndVarExpression dependentIndVar14 = new IndVarExpression(
					operandVal14, dependentIndVar13, BinaryOperatorID.FMUL, true);
			assertEquals("%i*-1.200000e+01+2.400000e+02-%k*-1.200000e+01", dependentIndVar14.toString());
		}
		catch(Exception e){
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void testIntegerPrefixOperationOfExprWithConstantFolding(){
		try{
			Type type = Type.getInt32Type(compilationContext, true);
			IntegerType intType = (IntegerType) type;

			// Create the basic ind var
			Value basicVal = new Value(type);
			basicVal.setName("i");
			IndVarExpression basicIndVarExpr = new IndVarExpression(basicVal, null, null, true);

			APInt apInt = new APInt(intType.getNumBits(), "43", intType.isSigned());
			ConstantInt operandVal1 = new ConstantInt( intType, apInt);
			IndVarExpression dependentIndVar1 = new IndVarExpression(
					operandVal1, basicIndVarExpr, BinaryOperatorID.ADD, true);
			assertEquals("%i+43", dependentIndVar1.toString());

			Value operandVal2 = new Value(type);
			operandVal2.setName("k");
			IndVarExpression dependentIndVar2 = new IndVarExpression(
					operandVal2, dependentIndVar1, BinaryOperatorID.SUB, true);
			assertEquals("%i+43-%k", dependentIndVar2.toString());

			APInt apInt1 = new APInt(intType.getNumBits(), "12", intType.isSigned());
			ConstantInt operandVal3 = new ConstantInt( intType, apInt1);
			IndVarExpression dependentIndVar3 = new IndVarExpression(
					operandVal3, dependentIndVar2, BinaryOperatorID.ADD, false);
			assertEquals("%i+55-%k", dependentIndVar3.toString());

			APInt apInt2 = new APInt(intType.getNumBits(), "3", intType.isSigned());
			ConstantInt operandVal4 = new ConstantInt( intType, apInt2);
			IndVarExpression dependentIndVar4 = new IndVarExpression(
					operandVal4, dependentIndVar3, BinaryOperatorID.MUL, false);
			assertEquals("3*%i+165-3*%k", dependentIndVar4.toString());

			APInt apInt3 = new APInt(intType.getNumBits(), "-2", intType.isSigned());
			ConstantInt operandVal5 = new ConstantInt( intType, apInt3);
			IndVarExpression dependentIndVar5 = new IndVarExpression(
					operandVal5, dependentIndVar4, BinaryOperatorID.MUL, false);
			assertEquals("-6*%i+-330--6*%k", dependentIndVar5.toString());

			APInt apInt4 = new APInt(intType.getNumBits(), "10", intType.isSigned());
			ConstantInt operandVal6 = new ConstantInt( intType, apInt4);
			IndVarExpression dependentIndVar6 = new IndVarExpression(
					operandVal6, dependentIndVar5, BinaryOperatorID.SUB, false);
			assertEquals("6*%i+340+-6*%k", dependentIndVar6.toString());

			Value operandVal7 = new Value(type);
			operandVal7.setName("m");
			IndVarExpression dependentIndVar7 = new IndVarExpression(
					operandVal7, dependentIndVar6, BinaryOperatorID.MUL, false);
			assertEquals("%m*6*%i+%m*340+%m*-6*%k", dependentIndVar7.toString());

			APInt apInt5 = new APInt(intType.getNumBits(), "7", intType.isSigned());
			ConstantInt operandVal8 = new ConstantInt( intType, apInt5);
			IndVarExpression dependentIndVar8 = new IndVarExpression(
					operandVal8, dependentIndVar7, BinaryOperatorID.SUB, false);
			assertEquals("7-%m*6*%i-%m*340-%m*-6*%k", dependentIndVar8.toString());

			APInt apInt6 = new APInt(intType.getNumBits(), "3", intType.isSigned());
			ConstantInt operandVal9 = new ConstantInt( intType, apInt6);
			IndVarExpression dependentIndVar9 = new IndVarExpression(
					operandVal9, dependentIndVar8, BinaryOperatorID.SUB, false);
			assertEquals("-4+%m*6*%i+%m*340+%m*-6*%k", dependentIndVar9.toString());

			Value operandVal10 = new Value(type);
			operandVal10.setName("x");
			IndVarExpression dependentIndVar10 = new IndVarExpression(
					operandVal10, dependentIndVar9, BinaryOperatorID.MUL, true);
			assertEquals("-4*%x+%m*6*%i*%x+%m*340*%x+%m*-6*%k*%x", dependentIndVar10.toString());

			APInt apInt7 = new APInt(intType.getNumBits(), "15", intType.isSigned());
			ConstantInt operandVal11 = new ConstantInt( intType, apInt7);
			IndVarExpression dependentIndVar11 = new IndVarExpression(
					operandVal11, dependentIndVar10, BinaryOperatorID.SUB, true);
			assertEquals("-4*%x+%m*6*%i*%x+%m*340*%x+%m*-6*%k*%x-15", dependentIndVar11.toString());

			APInt apInt8 = new APInt(intType.getNumBits(), "23", intType.isSigned());
			ConstantInt operandVal12 = new ConstantInt( intType, apInt8);
			IndVarExpression dependentIndVar12 = new IndVarExpression(
					operandVal12, dependentIndVar11, BinaryOperatorID.SUB, false);
			assertEquals("4*%x-%m*6*%i*%x-%m*340*%x-%m*-6*%k*%x+38", dependentIndVar12.toString());

		}
		catch(Exception e){
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void testFPPrefixOperationOfExprWithConstantFolding(){
		try{
			Type type = Type.getDoubleType(compilationContext);

			// Create the basic ind var
			Value basicVal = new Value(type);
			basicVal.setName("i");
			IndVarExpression basicIndVarExpr = new IndVarExpression(basicVal, null, null, true);

			APFloat apFloat = new APFloat(APFloat.IEEEdouble, "43.0");
			ConstantFP operandVal1 = new ConstantFP( type, apFloat);
			IndVarExpression dependentIndVar1 = new IndVarExpression(
					operandVal1, basicIndVarExpr, BinaryOperatorID.FADD, true);
			assertEquals("%i+4.300000e+01", dependentIndVar1.toString());

			Value operandVal2 = new Value(type);
			operandVal2.setName("k");
			IndVarExpression dependentIndVar2 = new IndVarExpression(
					operandVal2, dependentIndVar1, BinaryOperatorID.FSUB, true);
			assertEquals("%i+4.300000e+01-%k", dependentIndVar2.toString());

			APFloat apFloat1 = new APFloat(APFloat.IEEEdouble, "12.0");
			ConstantFP operandVal3 = new ConstantFP( type, apFloat1);
			IndVarExpression dependentIndVar3 = new IndVarExpression(
					operandVal3, dependentIndVar2, BinaryOperatorID.FADD, false);
			assertEquals("%i+5.500000e+01-%k", dependentIndVar3.toString());

			APFloat apFloat2 = new APFloat(APFloat.IEEEdouble, "3.0");
			ConstantFP operandVal4 = new ConstantFP( type, apFloat2);
			IndVarExpression dependentIndVar4 = new IndVarExpression(
					operandVal4, dependentIndVar3, BinaryOperatorID.FMUL, false);
			assertEquals("3.000000e+00*%i+1.650000e+02-3.000000e+00*%k", dependentIndVar4.toString());

			APFloat apFloat3 = new APFloat(APFloat.IEEEdouble, "-2.0");
			ConstantFP operandVal5 = new ConstantFP( type, apFloat3);
			IndVarExpression dependentIndVar5 = new IndVarExpression(
					operandVal5, dependentIndVar4, BinaryOperatorID.FMUL, false);
			assertEquals("-6.000000e+00*%i+-3.300000e+02--6.000000e+00*%k", dependentIndVar5.toString());

			APFloat apFloat4 = new APFloat(APFloat.IEEEdouble, "10.0");
			ConstantFP operandVal6 = new ConstantFP( type, apFloat4);
			IndVarExpression dependentIndVar6 = new IndVarExpression(
					operandVal6, dependentIndVar5, BinaryOperatorID.FSUB, false);
			assertEquals("6.000000e+00*%i+3.400000e+02+-6.000000e+00*%k", dependentIndVar6.toString());

			Value operandVal7 = new Value(type);
			operandVal7.setName("m");
			IndVarExpression dependentIndVar7 = new IndVarExpression(
					operandVal7, dependentIndVar6, BinaryOperatorID.FMUL, false);
			assertEquals("%m*6.000000e+00*%i+%m*3.400000e+02+%m*-6.000000e+00*%k", dependentIndVar7.toString());

			APFloat apFloat5 = new APFloat(APFloat.IEEEdouble, "7.0");
			ConstantFP operandVal8 = new ConstantFP( type, apFloat5);
			IndVarExpression dependentIndVar8 = new IndVarExpression(
					operandVal8, dependentIndVar7, BinaryOperatorID.FSUB, false);
			assertEquals("7.000000e+00-%m*6.000000e+00*%i-%m*3.400000e+02-%m*-6.000000e+00*%k", dependentIndVar8.toString());

			APFloat apFloat6 = new APFloat(APFloat.IEEEdouble, "3.0");
			ConstantFP operandVal9 = new ConstantFP( type, apFloat6);
			IndVarExpression dependentIndVar9 = new IndVarExpression(
					operandVal9, dependentIndVar8, BinaryOperatorID.FSUB, false);
			assertEquals("-4.000000e+00+%m*6.000000e+00*%i+%m*3.400000e+02+%m*-6.000000e+00*%k", dependentIndVar9.toString());

			Value operandVal10 = new Value(type);
			operandVal10.setName("x");
			IndVarExpression dependentIndVar10 = new IndVarExpression(
					operandVal10, dependentIndVar9, BinaryOperatorID.FMUL, true);
			assertEquals("-4.000000e+00*%x+%m*6.000000e+00*%i*%x+%m*3.400000e+02*%x+%m*-6.000000e+00*%k*%x", dependentIndVar10.toString());

			APFloat apFloat7 = new APFloat(APFloat.IEEEdouble, "15.0");
			ConstantFP operandVal11 = new ConstantFP( type, apFloat7);
			IndVarExpression dependentIndVar11 = new IndVarExpression(
					operandVal11, dependentIndVar10, BinaryOperatorID.FSUB, true);
			assertEquals("-4.000000e+00*%x+%m*6.000000e+00*%i*%x+%m*3.400000e+02*%x+%m*-6.000000e+00*%k*%x-1.500000e+01", dependentIndVar11.toString());

			APFloat apFloat8 = new APFloat(APFloat.IEEEdouble, "23.0");
			ConstantFP operandVal12 = new ConstantFP( type, apFloat8);
			IndVarExpression dependentIndVar12 = new IndVarExpression(
					operandVal12, dependentIndVar11, BinaryOperatorID.FSUB, false);
			assertEquals("4.000000e+00*%x-%m*6.000000e+00*%i*%x-%m*3.400000e+02*%x-%m*-6.000000e+00*%k*%x+3.800000e+01", dependentIndVar12.toString());
		}
		catch(Exception e){
			e.printStackTrace();
			assertTrue(false);
		}
	}

	

	@Test
	public void testIntMultiplicativeExpressionAreSimilar(){
		try{
			Type type = Type.getInt32Type(compilationContext, true);
			IntegerType intType = (IntegerType) type;
			
			Value basicVal = new Value(type);
			basicVal.setName("i");
			
			APInt apInt = new APInt(intType.getNumBits(), "43", intType.isSigned());
			ConstantInt const1 = new ConstantInt( intType, apInt);

			// i * 43 compared with j
			List<ExpressionElement> expr = new ArrayList<ExpressionElement>();
			expr.add(new ExpressionElement(basicVal)); expr.add(new ExpressionElement(BinaryOperatorID.MUL));
			expr.add(new ExpressionElement(const1));
			
			List<ExpressionElement> otherExpr = new ArrayList<ExpressionElement>();
			Value operandVal1 = new Value(type);
			operandVal1.setName("k");
			Value operandVal2 = new Value(type);
			operandVal1.setName("j");
			
			otherExpr.add(new ExpressionElement(operandVal1)); 
			
			assertFalse(InductionVariable.multiplicativeExpressionsAreSimilar(expr, otherExpr));
			
			// i * 43 compared with i
			otherExpr = new ArrayList<ExpressionElement>();
			otherExpr.add(new ExpressionElement(basicVal)); 
			assertFalse(InductionVariable.multiplicativeExpressionsAreSimilar(expr, otherExpr));
			
			// i * 43 compared with 43 (same constant object)
			otherExpr = new ArrayList<ExpressionElement>();
			otherExpr.add(new ExpressionElement(const1)); 
			assertFalse(InductionVariable.multiplicativeExpressionsAreSimilar(expr, otherExpr));
			
			// i * 43 compared to k * j
			otherExpr = new ArrayList<ExpressionElement>();
			otherExpr.add(new ExpressionElement(operandVal1)); otherExpr.add(new ExpressionElement(BinaryOperatorID.MUL));
			otherExpr.add(new ExpressionElement(operandVal2));
			assertFalse(InductionVariable.multiplicativeExpressionsAreSimilar(expr, otherExpr));
			
			// i * 43 compared to i * 43
			otherExpr = new ArrayList<ExpressionElement>();
			otherExpr.add(new ExpressionElement(basicVal)); otherExpr.add(new ExpressionElement(BinaryOperatorID.MUL));
			otherExpr.add(new ExpressionElement(const1));
			assertTrue(InductionVariable.multiplicativeExpressionsAreSimilar(expr, otherExpr));
			
			// i * 43 compared to i * 43 (different constant)
			APInt apInt1 = new APInt(intType.getNumBits(), "43", intType.isSigned());
			ConstantInt const2 = new ConstantInt( intType, apInt1);
			otherExpr = new ArrayList<ExpressionElement>();
			otherExpr.add(new ExpressionElement(basicVal)); otherExpr.add(new ExpressionElement(BinaryOperatorID.MUL));
			otherExpr.add(new ExpressionElement(const2));
			assertTrue(InductionVariable.multiplicativeExpressionsAreSimilar(expr, otherExpr));
			
		}
		catch(Exception e){
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testFpMultiplicativeExpressionAreSimilar(){
		try{
			Type type = Type.getDoubleType(compilationContext);

			// Create the basic ind var
			Value basicVal = new Value(type);
			basicVal.setName("i");

			// Create the dependent ind var
			APFloat apFloat = new APFloat(APFloat.IEEEdouble,"1.23");
			ConstantFP constFP1 = new ConstantFP(type, apFloat);
			
			// i * 1.23 compared with j
			List<ExpressionElement> expr = new ArrayList<ExpressionElement>();
			expr.add(new ExpressionElement(basicVal)); expr.add(new ExpressionElement(BinaryOperatorID.FMUL));
			expr.add(new ExpressionElement(constFP1));
			
			List<ExpressionElement> otherExpr = new ArrayList<ExpressionElement>();
			Value operandVal1 = new Value(type);
			operandVal1.setName("k");
			Value operandVal2 = new Value(type);
			operandVal1.setName("j");
			
			otherExpr.add(new ExpressionElement(operandVal1)); 
			
			assertFalse(InductionVariable.multiplicativeExpressionsAreSimilar(expr, otherExpr));
			
			// i * 1.23 compared with i
			otherExpr = new ArrayList<ExpressionElement>();
			otherExpr.add(new ExpressionElement(basicVal)); 
			assertFalse(InductionVariable.multiplicativeExpressionsAreSimilar(expr, otherExpr));
			
			// i * 1.23 compared with 1.23 (same constant object)
			otherExpr = new ArrayList<ExpressionElement>();
			otherExpr.add(new ExpressionElement(constFP1)); 
			assertFalse(InductionVariable.multiplicativeExpressionsAreSimilar(expr, otherExpr));
			
			// i * 1.23 compared to k * j
			otherExpr = new ArrayList<ExpressionElement>();
			otherExpr.add(new ExpressionElement(operandVal1)); otherExpr.add(new ExpressionElement(BinaryOperatorID.FMUL));
			otherExpr.add(new ExpressionElement(operandVal2));
			assertFalse(InductionVariable.multiplicativeExpressionsAreSimilar(expr, otherExpr));
			
			// i * 1.23 compared to i * 1.23
			otherExpr = new ArrayList<ExpressionElement>();
			otherExpr.add(new ExpressionElement(basicVal)); otherExpr.add(new ExpressionElement(BinaryOperatorID.FMUL));
			otherExpr.add(new ExpressionElement(constFP1));
			assertTrue(InductionVariable.multiplicativeExpressionsAreSimilar(expr, otherExpr));
			
			// i * 1.23 compared to i * 1.23 (different constant)
			APFloat apFloat1 = new APFloat(APFloat.IEEEdouble,"1.23");
			ConstantFP constFP2 = new ConstantFP(type, apFloat1);
			otherExpr = new ArrayList<ExpressionElement>();
			otherExpr.add(new ExpressionElement(basicVal)); otherExpr.add(new ExpressionElement(BinaryOperatorID.FMUL));
			otherExpr.add(new ExpressionElement(constFP2));
			assertTrue(InductionVariable.multiplicativeExpressionsAreSimilar(expr, otherExpr));
		}
		catch(Exception e){
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void testIntExpressionEvaluation(){
		try{
			Type type = Type.getInt32Type(compilationContext, true);
			IntegerType intType = (IntegerType) type;

			// Create the basic ind var
			Value basicVal = new Value(type);
			basicVal.setName("i");
			IndVarExpression basicIndVarExpr = new IndVarExpression(basicVal, null, null, true);

			APInt apInt = new APInt(intType.getNumBits(), "43", intType.isSigned());
			ConstantInt operandVal1 = new ConstantInt( intType, apInt);
			IndVarExpression dependentIndVar1 = new IndVarExpression(
					operandVal1, basicIndVarExpr, BinaryOperatorID.ADD, true);
			assertEquals("%i+43", dependentIndVar1.toString());

			Value operandVal2 = new Value(type);
			operandVal2.setName("k");
			IndVarExpression dependentIndVar2 = new IndVarExpression(
					operandVal2, dependentIndVar1, BinaryOperatorID.SUB, true);
			assertEquals("%i+43-%k", dependentIndVar2.toString());

			Value operandVal3 = new Value(type);
			operandVal3.setName("j");
			IndVarExpression dependentIndVar3 = new IndVarExpression(
					operandVal3, dependentIndVar2, BinaryOperatorID.ADD, true);
			assertEquals("%i+43-%k+%j", dependentIndVar3.toString());

			List<ExpressionElement> result1 = InductionVariable.evaluate(BinaryOperatorID.SUB, 
					dependentIndVar3.getExpression(), dependentIndVar2.getExpression());
			assertEquals("+%j", getStringRepresentationOfExpr(result1));
			
			List<ExpressionElement> result2 = InductionVariable.evaluate(BinaryOperatorID.SUB, 
					dependentIndVar2.getExpression(), dependentIndVar3.getExpression());
			assertEquals("-%j", getStringRepresentationOfExpr(result2));
			
			List<ExpressionElement> result3 = InductionVariable.evaluate(BinaryOperatorID.ADD, 
					dependentIndVar3.getExpression(), dependentIndVar2.getExpression());

			assertEquals("+2*%i+86-2*%k+%j", getStringRepresentationOfExpr(result3));
			
			// Another combination....
			Value operandVal4 = new Value(type);
			operandVal4.setName("m");
			IndVarExpression dependentIndVar4 = new IndVarExpression(
					operandVal4, dependentIndVar3, BinaryOperatorID.SUB, true);
			assertEquals("%i+43-%k+%j-%m", dependentIndVar4.toString());

			List<ExpressionElement> result4 = InductionVariable.evaluate(BinaryOperatorID.ADD, 
					dependentIndVar4.getExpression(), dependentIndVar2.getExpression());
			assertEquals("+2*%i+86-2*%k+%j-%m", getStringRepresentationOfExpr(result4));
			
			List<ExpressionElement> result5 = InductionVariable.evaluate(BinaryOperatorID.SUB, 
					dependentIndVar4.getExpression(), dependentIndVar2.getExpression());
			assertEquals("+%j-%m", getStringRepresentationOfExpr(result5));
			
			List<ExpressionElement> result6 = InductionVariable.evaluate(BinaryOperatorID.SUB, 
					dependentIndVar2.getExpression(), dependentIndVar4.getExpression());
			assertEquals("-%j+%m", getStringRepresentationOfExpr(result6));
			
			// With more than 1 multiplication elements
			Value operandVal5 = new Value(type);
			operandVal5.setName("x");
			IndVarExpression dependentIndVar5 = new IndVarExpression(
					operandVal5, dependentIndVar4, BinaryOperatorID.MUL, true);
			assertEquals("%i*%x+43*%x-%k*%x+%j*%x-%m*%x", dependentIndVar5.toString());
			
			// Try (%i*%x+43*%x-%k*%x+%j*%x-%m*%x) + (%m*%x-%j*%x) = %i*%x+43*%x-%k*%x
			IndVarExpression basicIndVarExpr1 = new IndVarExpression(operandVal4, null, null, true);
			IndVarExpression dependentIndVar6 = new IndVarExpression(
					operandVal3, basicIndVarExpr1, BinaryOperatorID.SUB, true);
			assertEquals("%m-%j", dependentIndVar6.toString());
			
			IndVarExpression dependentIndVar7 = new IndVarExpression(
					operandVal5, dependentIndVar6, BinaryOperatorID.MUL, true);
			assertEquals("%m*%x-%j*%x", dependentIndVar7.toString());
			
			List<ExpressionElement> result7 = InductionVariable.evaluate(BinaryOperatorID.ADD, 
					dependentIndVar5.getExpression(), dependentIndVar7.getExpression());
			assertEquals("+%i*%x+43*%x-%k*%x", getStringRepresentationOfExpr(result7));
			
			// Try (%i*%x+43*%x-%k*%x+%j*%x-%m*%x) -(%m*%x-%j*%x) = %i*%x+43*%x-%k*%x+2*%j*%x-2*%m*%x
			List<ExpressionElement> result8 = InductionVariable.evaluate(BinaryOperatorID.SUB, 
					dependentIndVar5.getExpression(), dependentIndVar7.getExpression());
			assertEquals("+%i*%x+43*%x-%k*%x+2*%j*%x-2*%m*%x", getStringRepresentationOfExpr(result8));
			
			// Try (%m*%x-%j*%x) + (%i*%x+43*%x-%k*%x+%j*%x-%m*%x) = %i*%x+43*%x-%k*%x
			List<ExpressionElement> result9 = InductionVariable.evaluate(BinaryOperatorID.ADD, 
					dependentIndVar7.getExpression(), dependentIndVar5.getExpression());
			assertEquals("+%i*%x+43*%x-%k*%x", getStringRepresentationOfExpr(result9));
			
			// Try (%m*%x-%j*%x) - (%i*%x+43*%x-%k*%x+%j*%x-%m*%x) = -%i*%x-43*%x+%k*%x-2*%m*%x+2*%j*%x
			List<ExpressionElement> result10 = InductionVariable.evaluate(BinaryOperatorID.SUB, 
					dependentIndVar7.getExpression(), dependentIndVar5.getExpression());
			assertEquals("+2*%m*%x-2*%j*%x-%i*%x-43*%x+%k*%x", getStringRepresentationOfExpr(result10));
			
		}
		catch(Exception e){
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testFpExpressionEvaluation(){
		try{
			Type type = Type.getDoubleType(compilationContext);

			// Create the basic ind var
			Value basicVal = new Value(type);
			basicVal.setName("f");
			IndVarExpression basicIndVarExpr = new IndVarExpression(basicVal, null, null, true);

			APFloat apFloat = new APFloat(APFloat.IEEEdouble,"2.56");
			ConstantFP operandVal1 = new ConstantFP(type, apFloat);
			IndVarExpression dependentIndVar1 = new IndVarExpression(
					operandVal1, basicIndVarExpr, BinaryOperatorID.FADD, true);
			assertEquals("%f+2.560000e+00", dependentIndVar1.toString());

			Value operandVal2 = new Value(type);
			operandVal2.setName("k");
			IndVarExpression dependentIndVar2 = new IndVarExpression(
					operandVal2, dependentIndVar1, BinaryOperatorID.FSUB, true);
			assertEquals("%f+2.560000e+00-%k", dependentIndVar2.toString());

			Value operandVal3 = new Value(type);
			operandVal3.setName("j");
			IndVarExpression dependentIndVar3 = new IndVarExpression(
					operandVal3, dependentIndVar2, BinaryOperatorID.FADD, true);
			assertEquals("%f+2.560000e+00-%k+%j", dependentIndVar3.toString());

			List<ExpressionElement> result1 = InductionVariable.evaluate(BinaryOperatorID.FSUB, 
					dependentIndVar3.getExpression(), dependentIndVar2.getExpression());
			assertEquals("+%j", getStringRepresentationOfExpr(result1));
			
			List<ExpressionElement> result2 = InductionVariable.evaluate(BinaryOperatorID.FSUB, 
					dependentIndVar2.getExpression(), dependentIndVar3.getExpression());
			assertEquals("-%j", getStringRepresentationOfExpr(result2));
			
			List<ExpressionElement> result3 = InductionVariable.evaluate(BinaryOperatorID.FADD, 
					dependentIndVar3.getExpression(), dependentIndVar2.getExpression());

			assertEquals("+2.000000e+00*%f+5.120000e+00-2.000000e+00*%k+%j", getStringRepresentationOfExpr(result3));
			
			// Another combination....
			Value operandVal4 = new Value(type);
			operandVal4.setName("m");
			IndVarExpression dependentIndVar4 = new IndVarExpression(
					operandVal4, dependentIndVar3, BinaryOperatorID.FSUB, true);
			assertEquals("%f+2.560000e+00-%k+%j-%m", dependentIndVar4.toString());

			List<ExpressionElement> result4 = InductionVariable.evaluate(BinaryOperatorID.FADD, 
					dependentIndVar4.getExpression(), dependentIndVar2.getExpression());
			assertEquals("+2.000000e+00*%f+5.120000e+00-2.000000e+00*%k+%j-%m", getStringRepresentationOfExpr(result4));
			
			List<ExpressionElement> result5 = InductionVariable.evaluate(BinaryOperatorID.FSUB, 
					dependentIndVar4.getExpression(), dependentIndVar2.getExpression());
			assertEquals("+%j-%m", getStringRepresentationOfExpr(result5));
			
			List<ExpressionElement> result6 = InductionVariable.evaluate(BinaryOperatorID.FSUB, 
					dependentIndVar2.getExpression(), dependentIndVar4.getExpression());
			assertEquals("-%j+%m", getStringRepresentationOfExpr(result6));
			
			// With more than 1 multiplication elements
			Value operandVal5 = new Value(type);
			operandVal5.setName("x");
			IndVarExpression dependentIndVar5 = new IndVarExpression(
					operandVal5, dependentIndVar4, BinaryOperatorID.FMUL, true);
			assertEquals("%f*%x+2.560000e+00*%x-%k*%x+%j*%x-%m*%x", dependentIndVar5.toString());
			
			// Try (%i*%x+43*%x-%k*%x+%j*%x-%m*%x) - (%m*%x-%j*%x) = %i*%x+43*%x-%k*%x
			IndVarExpression basicIndVarExpr1 = new IndVarExpression(operandVal4, null, null, true);
			IndVarExpression dependentIndVar6 = new IndVarExpression(
					operandVal3, basicIndVarExpr1, BinaryOperatorID.FSUB, true);
			assertEquals("%m-%j", dependentIndVar6.toString());
			
			IndVarExpression dependentIndVar7 = new IndVarExpression(
					operandVal5, dependentIndVar6, BinaryOperatorID.FMUL, true);
			assertEquals("%m*%x-%j*%x", dependentIndVar7.toString());
			
			List<ExpressionElement> result7 = InductionVariable.evaluate(BinaryOperatorID.FADD, 
					dependentIndVar5.getExpression(), dependentIndVar7.getExpression());
			assertEquals("+%f*%x+2.560000e+00*%x-%k*%x", getStringRepresentationOfExpr(result7));
		}
		catch(Exception e){
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	public String getStringRepresentationOfExpr(List<ExpressionElement> expression){
		String expr = "";

		for(ExpressionElement element : expression){
			expr += element.toString();
		}

		return expr;
	}
}

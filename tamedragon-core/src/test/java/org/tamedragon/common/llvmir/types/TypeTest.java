package org.tamedragon.common.llvmir.types;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.junit.Ignore;
import org.junit.Test;
import org.tamedragon.common.llvmir.types.ArrayType;
import org.tamedragon.common.llvmir.types.CompilationContext;
import org.tamedragon.common.llvmir.types.FunctionType;
import org.tamedragon.common.llvmir.types.IntegerType;
import org.tamedragon.common.llvmir.types.PointerType;
import org.tamedragon.common.llvmir.types.StructType;
import org.tamedragon.common.llvmir.types.Type;
import org.tamedragon.common.llvmir.types.exceptions.TypeCreationException;

public class TypeTest {

	private CompilationContext compilationContext1;
	private CompilationContext compilationContext2;

	@Test
	public void testHalfTypesAreSingletonsInSameContext(){

		compilationContext1 = new CompilationContext();
		compilationContext2 = new CompilationContext();

		Type halfType1CC1 = Type.getHalfType(compilationContext1);
		Type halfType2CC1 = Type.getHalfType(compilationContext1);
		assertTrue(halfType1CC1 == halfType2CC1);

		Type halfType1CC2 = Type.getHalfType(compilationContext2);
		Type halfType2CC2 = Type.getHalfType(compilationContext2);
		assertTrue(halfType1CC2 == halfType2CC2);

		assertTrue(halfType1CC1 != halfType1CC2);
		assertTrue(halfType2CC1 != halfType2CC2);
	}

	@Test
	public void testFloatTypesAreSingletonsInSameContext(){

		compilationContext1 = new CompilationContext();
		compilationContext2 = new CompilationContext();

		Type floatType1CC1 = Type.getFloatType(compilationContext1);
		Type floatType2CC1 = Type.getFloatType(compilationContext1);
		assertTrue(floatType1CC1 == floatType2CC1);

		Type floatType1CC2 = Type.getFloatType(compilationContext2);
		Type floatType2CC2 = Type.getFloatType(compilationContext2);
		assertTrue(floatType1CC2 == floatType2CC2);

		assertTrue(floatType1CC1 != floatType1CC2);
		assertTrue(floatType2CC1 != floatType2CC2);
	}

	@Test
	public void testDoubleTypesAreSingletonsInSameContext(){

		compilationContext1 = new CompilationContext();
		compilationContext2 = new CompilationContext();

		Type doubleType1CC1 = Type.getDoubleType(compilationContext1);
		Type doubleType2CC1 = Type.getDoubleType(compilationContext1);
		assertTrue(doubleType1CC1 == doubleType2CC1);

		Type doubleType1CC2 = Type.getDoubleType(compilationContext2);
		Type doubleType2CC2 = Type.getDoubleType(compilationContext2);
		assertTrue(doubleType1CC2 == doubleType2CC2);

		assertTrue(doubleType1CC1 != doubleType1CC2);
		assertTrue(doubleType2CC1 != doubleType2CC2);
	}

	@Test
	public void testVoidTypesAreSingletonsInSameContext(){

		compilationContext1 = new CompilationContext();
		compilationContext2 = new CompilationContext();

		Type voidType1CC1 = Type.getVoidType(compilationContext1);
		Type voidType2CC1 = Type.getVoidType(compilationContext1);
		assertTrue(voidType1CC1 == voidType2CC1);

		Type voidType1CC2 = Type.getVoidType(compilationContext2);
		Type voidType2CC2 = Type.getVoidType(compilationContext2);
		assertTrue(voidType1CC2 == voidType2CC2);

		assertTrue(voidType1CC1 != voidType1CC2);
		assertTrue(voidType2CC1 != voidType2CC2);
	}

	@Test
	public void testLabelTypesAreSingletonsInSameContext(){

		compilationContext1 = new CompilationContext();
		compilationContext2 = new CompilationContext();

		Type labelType1CC1 = Type.getLabelType(compilationContext1);
		Type labelType2CC1 = Type.getLabelType(compilationContext1);
		assertTrue(labelType1CC1 == labelType2CC1);

		Type labelType1CC2 = Type.getLabelType(compilationContext2);
		Type labelType2CC2 = Type.getLabelType(compilationContext2);
		assertTrue(labelType1CC2 == labelType2CC2);

		assertTrue(labelType1CC1 != labelType1CC2);
		assertTrue(labelType2CC1 != labelType2CC2);
	}

	@Test
	public void testMetadataTypesAreSingletonsInSameContext(){

		compilationContext1 = new CompilationContext();
		compilationContext2 = new CompilationContext();

		Type metadataType1CC1 = Type.getMetaDataType(compilationContext1);
		Type metadataType2CC1 = Type.getMetaDataType(compilationContext1);
		assertTrue(metadataType1CC1 == metadataType2CC1);

		Type metadataType1CC2 = Type.getMetaDataType(compilationContext2);
		Type metadataType2CC2 = Type.getMetaDataType(compilationContext2);
		assertTrue(metadataType1CC2 == metadataType2CC2);

		assertTrue(metadataType1CC1 != metadataType1CC2);
		assertTrue(metadataType2CC1 != metadataType2CC2);
	}

	@Test
	public void testX86FP80TypesAreSingletonsInSameContext(){

		compilationContext1 = new CompilationContext();
		compilationContext2 = new CompilationContext();

		Type X86FP80Type1CC1 = Type.getX86_FP80Type(compilationContext1);
		Type X86FP80Type2CC1 = Type.getX86_FP80Type(compilationContext1);
		assertTrue(X86FP80Type1CC1 == X86FP80Type2CC1);

		Type X86FP80Type1CC2 = Type.getX86_FP80Type(compilationContext2);
		Type X86FP80Type2CC2 = Type.getX86_FP80Type(compilationContext2);
		assertTrue(X86FP80Type1CC2 == X86FP80Type2CC2);

		assertTrue(X86FP80Type1CC1 != X86FP80Type1CC2);
		assertTrue(X86FP80Type2CC1 != X86FP80Type2CC2);
	}

	@Test
	public void testFP128TypesAreSingletonsInSameContext(){

		compilationContext1 = new CompilationContext();
		compilationContext2 = new CompilationContext();

		Type FP128Type1CC1 = Type.getFP128Type(compilationContext1);
		Type FP128Type2CC1 = Type.getFP128Type(compilationContext1);
		assertTrue(FP128Type1CC1 == FP128Type2CC1);

		Type FP128Type1CC2 = Type.getFP128Type(compilationContext2);
		Type FP128Type2CC2 = Type.getFP128Type(compilationContext2);
		assertTrue(FP128Type1CC2 == FP128Type2CC2);

		assertTrue(FP128Type1CC1 != FP128Type1CC2);
		assertTrue(FP128Type2CC1 != FP128Type2CC2);
	}

	@Test
	public void testPPCFP128TypesAreSingletonsInSameContext(){

		compilationContext1 = new CompilationContext();
		compilationContext2 = new CompilationContext();

		Type PPCFP128Type1CC1 = Type.getPPCFP128Type(compilationContext1);
		Type PPCFP128Type2CC1 = Type.getPPCFP128Type(compilationContext1);
		assertTrue(PPCFP128Type1CC1 == PPCFP128Type2CC1);

		Type PPCFP128Type1CC2 = Type.getPPCFP128Type(compilationContext2);
		Type PPCFP128Type2CC2 = Type.getPPCFP128Type(compilationContext2);
		assertTrue(PPCFP128Type1CC2 == PPCFP128Type2CC2);

		assertTrue(PPCFP128Type1CC1 != PPCFP128Type1CC2);
		assertTrue(PPCFP128Type2CC1 != PPCFP128Type2CC2);
	}

	@Test
	public void testX86MMXTypesAreSingletonsInSameContext(){

		compilationContext1 = new CompilationContext();
		compilationContext2 = new CompilationContext();

		Type X86MMXType1CC1 = Type.getX86MMXType(compilationContext1);
		Type X86MMXType2CC1 = Type.getX86MMXType(compilationContext1);
		assertTrue(X86MMXType1CC1 == X86MMXType2CC1);

		Type X86MMXType1CC2 = Type.getX86MMXType(compilationContext2);
		Type X86MMXType2CC2 = Type.getX86MMXType(compilationContext2);
		assertTrue(X86MMXType1CC2 == X86MMXType2CC2);

		assertTrue(X86MMXType1CC1 != X86MMXType1CC2);
		assertTrue(X86MMXType2CC1 != X86MMXType2CC2);
	}

	@Test
	public void testSimilarStructTypesAreSingletonsInSameContext(){

		try{
			compilationContext1 = new CompilationContext();
			compilationContext2 = new CompilationContext();

			Type StructType1CC1 = Type.getStructType(compilationContext1, false, null, false,
					Type.getFloatType(compilationContext1), 
					Type.getDoubleType(compilationContext1));
			Type StructType2CC1 = Type.getStructType(compilationContext1, false, null, false,
					compilationContext1.getFloatType(),
					compilationContext1.getDoubleType());

			Type StructType3CC1 = Type.getStructType(compilationContext1, false, null, false,
					compilationContext1.getFloatType(),
					compilationContext1.getFloatType(),
					compilationContext1.getDoubleType());

			assertTrue(StructType1CC1 == StructType2CC1);
			assertTrue(StructType3CC1 != StructType2CC1);

			Type StructType1CC2 = Type.getStructType(compilationContext2, false, null, false,
					Type.getFloatType(compilationContext2), 
					Type.getDoubleType(compilationContext2));

			assertTrue(StructType1CC1 != StructType1CC2);
		}
		catch(TypeCreationException tce){
			assertTrue(false);
		}
	}

	@Ignore
	@Test
	public void testOpaqueTypesAreSingletonsInSameContext(){

		try {
			compilationContext1 = new CompilationContext();
			compilationContext2 = new CompilationContext();

			Type StructType1CC1 = Type.getStructType(compilationContext1, false, "S1",false);
			Type StructType2CC1 = Type.getStructType(compilationContext1, false, "S1",false);

			Type StructType3CC1 = Type.getStructType(compilationContext1, false, "S2",false);

			assertTrue(StructType1CC1 == StructType2CC1);
			assertTrue(StructType3CC1 != StructType2CC1);
		}
		catch(TypeCreationException tce){
			assertTrue(false);
		}
	}

	@Test
	public void testSimilarArrayTypesAreSingletonsInSameContext(){

		try {
			compilationContext1 = new CompilationContext();
			compilationContext2 = new CompilationContext();

			Type ArrayType1CC1 = Type.getArrayType(compilationContext1, Type.getFloatType(compilationContext1), 20L);
			Type ArrayType2CC1 = Type.getArrayType(compilationContext1, Type.getFloatType(compilationContext1), 20L);
			Type ArrayType3CC1 = Type.getArrayType(compilationContext1, Type.getDoubleType(compilationContext1), 20L);
			Type ArrayType4CC1 = Type.getArrayType(compilationContext1, Type.getFloatType(compilationContext1), 30L);
			assertTrue(ArrayType1CC1 == ArrayType2CC1);
			assertTrue(ArrayType1CC1 != ArrayType3CC1);
			assertTrue(ArrayType1CC1 != ArrayType4CC1);

			Type ArrayType1CC2 = Type.getArrayType(compilationContext2, Type.getFloatType(compilationContext2), 20L);
			assertTrue(ArrayType1CC1 != ArrayType1CC2);
		}
		catch(TypeCreationException tce){
			assertTrue(false);
		}
	}

	@Test
	public void testVectorTypesAreSingletonsInSameContext(){

		try{
			compilationContext1 = new CompilationContext();
			compilationContext2 = new CompilationContext();

			Type VectorType1CC1 = Type.getVectorType(compilationContext1, Type.getFloatType(compilationContext1), 2);
			Type VectorType2CC1 = Type.getVectorType(compilationContext1, Type.getFloatType(compilationContext1), 2);
			Type VectorType3CC1 = Type.getVectorType(compilationContext1, Type.getDoubleType(compilationContext1), 2);
			Type VectorType4CC1 = Type.getVectorType(compilationContext1, Type.getFloatType(compilationContext1), 3);
			assertTrue(VectorType1CC1 == VectorType2CC1);
			assertTrue(VectorType1CC1 != VectorType3CC1);
			assertTrue(VectorType1CC1 != VectorType4CC1);

			Type VectorType1CC2 = Type.getVectorType(compilationContext2, Type.getFloatType(compilationContext2), 2);
			assertTrue(VectorType1CC1 != VectorType1CC2);
		}
		catch(TypeCreationException tce){
			assertTrue(false);
		}
	}

	@Test
	public void testPointerTypesAreSingletonsInSameContext(){

		try {
			compilationContext1 = new CompilationContext();
			compilationContext2 = new CompilationContext();

			Type PointerType1CC1 = Type.getPointerType(compilationContext1, Type.getFloatType(compilationContext1), 2);
			Type PointerType2CC1 = Type.getPointerType(compilationContext1, Type.getFloatType(compilationContext1), 2);
			Type PointerType3CC1 = Type.getPointerType(compilationContext1, Type.getDoubleType(compilationContext1), 2);
			Type PointerType4CC1 = Type.getPointerType(compilationContext1, Type.getFloatType(compilationContext1), 3);
			assertTrue(PointerType1CC1 == PointerType2CC1);  // Same context, same address space, same pointee
			assertTrue(PointerType1CC1 != PointerType3CC1);  // Same context, same address space, different pointee
			assertTrue(PointerType1CC1 != PointerType4CC1);  // Same context, same pointee, different address spaces

			Type PointerType1CC2 = Type.getPointerType(compilationContext2, Type.getFloatType(compilationContext2), 2);
			assertTrue(PointerType1CC1 != PointerType1CC2);  // Same address space, same pointee, different context
		}
		catch(TypeCreationException tce){
			assertTrue(false);
		}
	}

	@Test
	public void testSameSizedIntegerTypesAreSingletonsInSameContext(){

		compilationContext1 = new CompilationContext();
		compilationContext2 = new CompilationContext();

		Type IntegerType1CC1 = Type.getInt1Type(compilationContext1, false);
		Type IntegerType2CC1 = Type.getInt1Type(compilationContext1, false);		
		assertTrue(IntegerType1CC1 == IntegerType2CC1);

		Type IntegerType1CC2 = Type.getInt1Type(compilationContext2, false);
		assertTrue(IntegerType1CC1 != IntegerType1CC2);

		Type IntegerType3CC1 = Type.getInt32Type(compilationContext1, true);
		assertTrue(IntegerType1CC1 != IntegerType3CC1);
	}

	@Test
	public void testFunctionTypesWithSameSignatureAreSingletonsInSameContext(){

		try { 
			compilationContext1 = new CompilationContext();
			compilationContext2 = new CompilationContext();

			Vector<Type> argsList1 = new Vector<Type>();
			argsList1.add(Type.getDoubleType(compilationContext1));
			argsList1.add(Type.getInt32Type(compilationContext1, true));

			Vector<Type> argsList2 = new Vector<Type>();
			argsList2.add(Type.getInt16Type(compilationContext1, true));

			Type FunctionType1CC1 = Type.getFunctionType(compilationContext1, 
					Type.getFloatType(compilationContext1), false, argsList1);
			Type FunctionType2CC1 = Type.getFunctionType(compilationContext1, 
					Type.getFloatType(compilationContext1), false, argsList1);
			Type FunctionType3CC1 = Type.getFunctionType(compilationContext1, 
					Type.getDoubleType(compilationContext1), false, argsList1);
			Type FunctionType4CC1 = Type.getFunctionType(compilationContext1, 
					Type.getFloatType(compilationContext1), false, argsList2);
			Type FunctionType5CC1 = Type.getFunctionType(compilationContext1, 
					Type.getFloatType(compilationContext1), true, argsList1);

			assertTrue(FunctionType1CC1 == FunctionType2CC1);
			assertTrue(FunctionType1CC1 != FunctionType3CC1);
			assertTrue(FunctionType1CC1 != FunctionType4CC1);
			assertTrue(FunctionType1CC1 != FunctionType5CC1);

			Type FunctionType1CC2 = Type.getFunctionType(compilationContext2, 
					Type.getFloatType(compilationContext2), false, argsList1);

			assertTrue(FunctionType1CC1 != FunctionType1CC2);
		}
		catch(TypeCreationException tce){
			assertTrue(false);
		}

	}

	@Test
	public void testTypesAreDifferentInSameContext(){

		try {
			CompilationContext sampleCompilationContext = new CompilationContext();

			List<Type> sampleTypes = new ArrayList<Type>();

			sampleTypes.add(Type.getFloatType(sampleCompilationContext));
			sampleTypes.add(Type.getDoubleType(sampleCompilationContext));
			sampleTypes.add(Type.getVoidType(sampleCompilationContext));
			sampleTypes.add(Type.getLabelType(sampleCompilationContext));
			sampleTypes.add(Type.getMetaDataType(sampleCompilationContext));
			sampleTypes.add(Type.getX86_FP80Type(sampleCompilationContext));
			sampleTypes.add(Type.getX86MMXType(sampleCompilationContext));
			sampleTypes.add(Type.getFP128Type(sampleCompilationContext));
			sampleTypes.add(Type.getPPCFP128Type(sampleCompilationContext));
			sampleTypes.add(Type.getStructType(sampleCompilationContext, false, null, false,
					Type.getFloatType(sampleCompilationContext), Type.getDoubleType(sampleCompilationContext)));
			sampleTypes.add(Type.getArrayType(sampleCompilationContext, Type.getFloatType(sampleCompilationContext), 20L));
			sampleTypes.add(Type.getVectorType(sampleCompilationContext, Type.getFloatType(sampleCompilationContext), 6));
			sampleTypes.add(Type.getPointerType(sampleCompilationContext, Type.getDoubleType(sampleCompilationContext), 0));
			sampleTypes.add(Type.getPointerType(sampleCompilationContext, Type.getFloatType(sampleCompilationContext), 0));
			sampleTypes.add(Type.getIntegerType(sampleCompilationContext, 3, true));
			sampleTypes.add(Type.getInt16Type(sampleCompilationContext, true));
			sampleTypes.add(Type.getFunctionType(sampleCompilationContext, Type.getDoubleType(sampleCompilationContext), false, new Vector<Type>()));

			for(int i = 0; i < sampleTypes.size(); i++){
				Type typeRef1 = sampleTypes.get(i);
				for(int j = 0; j < sampleTypes.size(); j++){
					Type typeRef2 = sampleTypes.get(j);
					if(i == j){
						assertTrue(typeRef1 == typeRef2);
					}
					else{
						assertTrue(typeRef1 != typeRef2);
					}
				}
			}
		}
		catch(TypeCreationException tce){
			assertTrue(false);
		}
	}

	@Test
	public void testTypeNames(){
		CompilationContext sampleCompilationContext = new CompilationContext();

		try {

			Type halfType = Type.getHalfType(sampleCompilationContext);
			assertTrue(halfType.toString().equals(Type.HALF));
			Type floatType = Type.getFloatType(sampleCompilationContext);
			assertTrue(floatType.toString().equals(Type.FLOAT));
			Type doubleType = Type.getDoubleType(sampleCompilationContext);
			assertTrue(doubleType.toString().equals(Type.DOUBLE));
			Type voidType = Type.getVoidType(sampleCompilationContext);
			assertTrue(voidType.toString().equals(Type.VOID));
			Type labelType = Type.getLabelType(sampleCompilationContext);
			assertTrue(labelType.toString().equals(Type.LABEL));
			Type metadataType = Type.getMetaDataType(sampleCompilationContext);
			assertTrue(metadataType.toString().equals(Type.METADATA));
			Type x86fp80Type = Type.getX86_FP80Type(sampleCompilationContext);
			assertTrue(x86fp80Type.toString().equals(Type.X86_FP80));
			Type x86mmxType = Type.getX86MMXType(sampleCompilationContext);
			assertTrue(x86mmxType.toString().equals(Type.X86MMX));
			Type fp128Type = Type.getFP128Type(sampleCompilationContext);
			assertTrue(fp128Type.toString().equals(Type.FP128));
			Type ppcfp128Type = Type.getPPCFP128Type(sampleCompilationContext);
			assertTrue(ppcfp128Type.toString().equals(Type.PPC_FP128));
			Type structType = Type.getStructType(sampleCompilationContext, false, null, false,
					Type.getFloatType(sampleCompilationContext), Type.getDoubleType(sampleCompilationContext));
			Type arrayType = Type.getArrayType(sampleCompilationContext, 
					Type.getStructType(sampleCompilationContext, false, null, false, Type.getDoubleType(sampleCompilationContext),
							Type.getDoubleType(sampleCompilationContext), Type.getFloatType(sampleCompilationContext)), 5);
			Type vectorType = Type.getVectorType(sampleCompilationContext, 
					Type.getFloatType(sampleCompilationContext), 28);
			Type pointerType1 = Type.getPointerType(sampleCompilationContext, 
					Type.getDoubleType(sampleCompilationContext), 0);
			Type pointerType2 = Type.getPointerType(sampleCompilationContext, 
					Type.getFloatType(sampleCompilationContext), 5);
			Type integerType16 = Type.getInt1Type(sampleCompilationContext, false);
			Type integerType32 = Type.getInt32Type(sampleCompilationContext, true);
			Type nonStandardIntType = Type.getIntegerType(sampleCompilationContext, 14, true);

			assertTrue(structType.toString().equals("{float, double}"));
			String arrStr = arrayType.toString();
			assertTrue(arrStr.equals("[5 x {double, double, float}]"));
			assertTrue(vectorType.toString().equals("<28 x float>"));
			assertTrue(pointerType1.toString().equals("double*"));
			assertTrue(pointerType2.toString().equals("float addrspace(5)*"));
			assertTrue(integerType16.toString().equals("i1"));
			assertTrue(integerType32.toString().equals("i32"));
			assertTrue(nonStandardIntType.toString().equals("i14"));
		
			Type retType1 = Type.getDoubleType(sampleCompilationContext);		
			Vector<Type> argsList1 = new Vector<Type>();
			argsList1.add(Type.getDoubleType(sampleCompilationContext));
			argsList1.add(Type.getInt16Type(sampleCompilationContext, true));
			argsList1.add(Type.getInt64Type(sampleCompilationContext, true));		
			Type funcType1 = Type.getFunctionType(sampleCompilationContext, retType1, false, argsList1);
			assertTrue(funcType1.toString().equals("double (double, i16, i64)"));

			Type retType2 = Type.getInt32Type(sampleCompilationContext, true);
			Vector<Type> argsList2 = new Vector<Type>();
			argsList2.add(Type.getDoubleType(sampleCompilationContext));
			argsList2.add(Type.getInt16Type(sampleCompilationContext, true));
			Type funcType2 = Type.getFunctionType(sampleCompilationContext, retType2, true, argsList2);
			assertTrue(funcType2.toString().equals("i32 (double, i16, ...)"));
		}
		catch(TypeCreationException tce){
			assertTrue(false);
		}
	}
	
	@Test
	public void testInvalidStructTypeCreation(){
		compilationContext1 = new CompilationContext();

		Type structType = null;
		
		Type integerType = Type.getInt16Type(compilationContext1, true);
		Type doubleType = Type.getDoubleType(compilationContext1);
		
		// Create some invalid types for struct
		Type voidType = Type.getVoidType(compilationContext1);
		Type labelType = Type.getLabelType(compilationContext1);
		Type metadataType = Type.getMetaDataType(compilationContext1);		
		Vector<Type> argList = new Vector<Type>();
		argList.add(Type.getDoubleType(compilationContext1));
		argList.add(Type.getDoubleType(compilationContext1));
		argList.add(Type.getInt64Type(compilationContext1, true));
		Type funcType = null;		
		try{
		 funcType = Type.getFunctionType(compilationContext1, voidType, false, argList);
		}
		catch(Exception e) {}
		assertNotNull (funcType);

		// Try creating a struct with random selection of elements
		try {
			structType = Type.getStructType(compilationContext1, false, null, false, voidType);
		}
		catch(TypeCreationException tce){
			assertTrue(tce.getMessage().equals(new String(voidType + CompilationContext.INVALID_ELEMENT_FOR_STRUCT)));
		}
		assertNull(structType);
		
		try {
			structType = Type.getStructType(compilationContext1, false, null, false, labelType, integerType);
		}
		catch(TypeCreationException tce){
			assertTrue(tce.getMessage().equals(new String(labelType + CompilationContext.INVALID_ELEMENT_FOR_STRUCT)));
		}
		assertNull(structType);
		
		try {
			structType = Type.getStructType(compilationContext1, false, null, false, integerType, metadataType, integerType);
		}
		catch(TypeCreationException tce){
			assertTrue(tce.getMessage().equals(new String(metadataType + CompilationContext.INVALID_ELEMENT_FOR_STRUCT)));
		}
		assertNull(structType);
		
		try {
			structType = Type.getStructType(compilationContext1, false, null, false, doubleType, funcType);
			assertTrue(false);
		}
		catch(TypeCreationException tce){
			assertTrue(tce.getMessage().equals(new String(funcType + CompilationContext.INVALID_ELEMENT_FOR_STRUCT)));
		}
		assertNull(structType);
		
		// ... and finally a valid one		
		try {
			structType = Type.getStructType(compilationContext1, false, null, false, doubleType, integerType);
			assertTrue(true);
		}
		catch(TypeCreationException tce){ }
		
		assertNotNull(structType);   // Valid type  
		
	}
	
	@Test
	public void testInvalidArrayTypeCreation(){
		compilationContext1 = new CompilationContext();

		Type arrayType  = null;
		
		Type integerType = Type.getInt32Type(compilationContext1, true);
		
		// Create some invalid types for array
		Type voidType = Type.getVoidType(compilationContext1);
		Type labelType = Type.getLabelType(compilationContext1);
		Type metadataType = Type.getMetaDataType(compilationContext1);		
		Vector<Type> argList = new Vector<Type>();
		argList.add(Type.getDoubleType(compilationContext1));
		argList.add(Type.getDoubleType(compilationContext1));
		argList.add(Type.getInt64Type(compilationContext1, true));
		Type funcType = null;
		try{
		 funcType = Type.getFunctionType(compilationContext1, voidType, false, argList);
		}
		catch(Exception e) {}
		assertNotNull(funcType);
		
		// Try creating an array all invalid elements
		try {
			arrayType = Type.getArrayType(compilationContext1, voidType, 300L);
		}
		catch(TypeCreationException tce){
			assertTrue(tce.getMessage().equals(new String(voidType + CompilationContext.INVALID_ELEMENT_FOR_ARRAY)));
		}
		assertNull(arrayType);
		
		try {
			arrayType = Type.getArrayType(compilationContext1, labelType, 2L);
		}
		catch(TypeCreationException tce){
			assertTrue(tce.getMessage().equals(new String(labelType + CompilationContext.INVALID_ELEMENT_FOR_ARRAY)));
		}
		assertNull(arrayType);
		
		try {
			arrayType = Type.getArrayType(compilationContext1, metadataType, 254L);
		}
		catch(TypeCreationException tce){
			assertTrue(tce.getMessage().equals(new String(metadataType + CompilationContext.INVALID_ELEMENT_FOR_ARRAY)));
		}
		assertNull(arrayType);
		
		try {
			arrayType = Type.getArrayType(compilationContext1, funcType, 254L);
		}
		catch(TypeCreationException tce){
			assertTrue(tce.getMessage().equals(new String(funcType + CompilationContext.INVALID_ELEMENT_FOR_ARRAY)));
		}
		assertNull(arrayType);
		
		// and finally a valid one
		try {
			arrayType = Type.getArrayType(compilationContext1, integerType, 254L);
		}
		catch(TypeCreationException tce){}		
		assertNotNull(arrayType);
	}
	
	@Ignore
	@Test
	public void testInvalidPointerTypeCreation(){
		compilationContext1 = new CompilationContext();

		Type pointerType  = null;
		
		Type integerType = Type.getInt32Type(compilationContext1, true);
		
		// Create some invalid types for array
		Type voidType = Type.getVoidType(compilationContext1);
		Type labelType = Type.getLabelType(compilationContext1);
		Type metadataType = Type.getMetaDataType(compilationContext1);		
		
		// Create a function 
		Vector<Type> argList = new Vector<Type>();
		argList.add(Type.getDoubleType(compilationContext1));
		argList.add(Type.getInt64Type(compilationContext1, true));
		argList.add(Type.getInt32Type(compilationContext1, true));
		Type funcType = null;
		try{
		 funcType = Type.getFunctionType(compilationContext1, voidType, false, argList);
		}
		catch(Exception e) {}
		assertNotNull (funcType);
		
		// Try creating a pointer that points to elements that a pointer cannot point to.
		try {
			pointerType = Type.getPointerType(compilationContext1, voidType, 23);
		}
		catch(TypeCreationException tce){
			String errMsg = tce.getMessage();
			assertTrue(errMsg.equals(new String(voidType + CompilationContext.INVALID_ELEMENT_FOR_POINTER)));
		}
		assertNull(pointerType);
		
		try {
			pointerType = Type.getPointerType(compilationContext1, labelType, 50);
		}
		catch(TypeCreationException tce){
			assertTrue(tce.getMessage().equals(new String(labelType + CompilationContext.INVALID_ELEMENT_FOR_POINTER)));
		}
		assertNull(pointerType);
		
		try {
			pointerType = Type.getPointerType(compilationContext1, metadataType, 100);
		}
		catch(TypeCreationException tce){
			assertTrue(tce.getMessage().equals(new String(metadataType + CompilationContext.INVALID_ELEMENT_FOR_POINTER)));
		}
		assertNull(pointerType);
		
		// and finally a couple of valid ones
		try {
			pointerType = Type.getPointerType(compilationContext1, funcType, 25);
		}
		catch(TypeCreationException tce){ }
		assertNotNull(pointerType);
		
		try {
			pointerType = Type.getPointerType(compilationContext1, integerType, 40);
		}
		catch(TypeCreationException tce){}		
		assertNotNull(pointerType);
	}
	
	@Test
	public void testInvalidVectorTypeCreation(){
		compilationContext1 = new CompilationContext();

		Type vectorType  = null;
		
		Type integerType = Type.getInt32Type(compilationContext1, true);
		
		// Create some invalid types for array
		Type voidType = Type.getVoidType(compilationContext1);
		Type labelType = Type.getLabelType(compilationContext1);
		Type metadataType = Type.getMetaDataType(compilationContext1);		
		Vector<Type> argList = new Vector<Type>();
		argList.add(Type.getFloatType(compilationContext1));
		argList.add(Type.getDoubleType(compilationContext1));
		argList.add(Type.getInt1Type(compilationContext1, false));
		Type funcType = null;
		try{
		 funcType = Type.getFunctionType(compilationContext1, integerType, false, argList);
		}
		catch(Exception e) {}
		assertNotNull (funcType);
		
		// Try creating an array all invalid elements
		try {
			vectorType = Type.getVectorType(compilationContext1, voidType, 10);
		}
		catch(TypeCreationException tce){
			assertTrue(tce.getMessage().equals(new String(voidType + CompilationContext.INVALID_ELEMENT_FOR_VECTOR)));
		}
		assertNull(vectorType);
		
		try {
			vectorType = Type.getVectorType(compilationContext1, labelType, 10);
		}
		catch(TypeCreationException tce){
			assertTrue(tce.getMessage().equals(new String(labelType + CompilationContext.INVALID_ELEMENT_FOR_VECTOR)));
		}
		assertNull(vectorType);
		
		try {
			vectorType = Type.getVectorType(compilationContext1, metadataType, 10);
		}
		catch(TypeCreationException tce){
			assertTrue(tce.getMessage().equals(new String(metadataType + CompilationContext.INVALID_ELEMENT_FOR_VECTOR)));
		}
		assertNull(vectorType);
		
		try {
			vectorType = Type.getVectorType(compilationContext1, funcType, 10);
		}
		catch(TypeCreationException tce){
			assertTrue(tce.getMessage().equals(new String(funcType + CompilationContext.INVALID_ELEMENT_FOR_VECTOR)));
		}
		assertNull(vectorType);
		
		// and finally a few valid ones
		try {
			vectorType = Type.getVectorType(compilationContext1, integerType, 30);
		}
		catch(TypeCreationException tce){}	
		assertNotNull(vectorType);
		
		Type flType = Type.getFloatType(compilationContext1);		
		try {
			vectorType = Type.getVectorType(compilationContext1, flType, 23);
		}
		catch(TypeCreationException tce){}		
		assertNotNull(vectorType);
		
		Type dblType = Type.getDoubleType(compilationContext1);
		try {
			vectorType = Type.getVectorType(compilationContext1, dblType, 46);
		}
		catch(TypeCreationException tce){}		
		assertNotNull(vectorType);
	}
	
	@Test
	public void testInvalidIntTypeCreation(){
		compilationContext1 = new CompilationContext();

		Type intType  = null;
		
		try {
			intType = Type.getIntegerType(compilationContext1, -1, true);
		}
		catch(TypeCreationException tce){
			assertTrue(tce.getMessage().equals(new String(-1 + CompilationContext.INVALID_SIZE_FOR_INTEGER)));
		}
		assertNull(intType);
		
		try {
			intType = Type.getIntegerType(compilationContext1, 8388609, true);
		}
		catch(TypeCreationException tce){
			assertTrue(tce.getMessage().equals(new String(8388609 + CompilationContext.INVALID_SIZE_FOR_INTEGER)));
		}
		assertNull(intType);
		
		try {
			intType = Type.getIntegerType(compilationContext1, 3, true);
		}
		catch(TypeCreationException tce){}		
		assertNotNull(intType);
		
		try {
			intType = Type.getIntegerType(compilationContext1, 89, true);
		}
		catch(TypeCreationException tce){}		
		assertNotNull(intType);
	}
	
	@Test
	public void testInvalidReturnTypeWithFunctionTypeCreation(){
		compilationContext1 = new CompilationContext();

		Type functType  = null;
		
		// Create some invalid types for return type of a function
		Type labelType = Type.getLabelType(compilationContext1);
		Type metadataType = Type.getMetaDataType(compilationContext1);		
		Vector<Type> argList = new Vector<Type>();
		Type refReturnType = Type.getVoidType(compilationContext1);
		argList.add(Type.getFloatType(compilationContext1));
		argList.add(Type.getInt8Type(compilationContext1, true));
		argList.add(Type.getInt64Type(compilationContext1, true));
		Type refFuncType = null;
		try{
			refFuncType = Type.getFunctionType(compilationContext1, refReturnType, false, argList);
		}
		catch(TypeCreationException tce){}
		assertNotNull(refFuncType);
		
		try {
			functType = Type.getFunctionType(compilationContext1, labelType, false, argList);
		}
		catch(TypeCreationException tce){
			assertTrue(tce.getMessage().equals(new String(labelType + CompilationContext.INVALID_RETURN_TYPE_FOR_FUNC)));
		}
		assertNull(functType);
		
		try {
			functType = Type.getFunctionType(compilationContext1, metadataType, false, argList);
		}
		catch(TypeCreationException tce){
			assertTrue(tce.getMessage().equals(new String(metadataType + CompilationContext.INVALID_RETURN_TYPE_FOR_FUNC)));
		}
		assertNull(functType);
		
		try {
			functType = Type.getFunctionType(compilationContext1, refFuncType, false, argList);
		}
		catch(TypeCreationException tce){
			assertTrue(tce.getMessage().equals(new String(refFuncType + CompilationContext.INVALID_RETURN_TYPE_FOR_FUNC)));
		}
		assertNull(functType);
		
		// and finally a few valid return types
		Type voidType = Type.getVoidType(compilationContext1);
		try {
			functType = Type.getFunctionType(compilationContext1, voidType, false, argList);
		}
		catch(TypeCreationException tce){}		
		assertNotNull(functType);
	}
	
	@Test
	public void testInvalidParamTypeWithFunctionTypeCreation(){
		compilationContext1 = new CompilationContext();

		Type functType  = null;
		
		// Create some invalid types for parameter types of a function
		Vector<Type> argList = new Vector<Type>();
		Type refReturnType = Type.getVoidType(compilationContext1);
		argList.add(Type.getFloatType(compilationContext1));
		argList.add(Type.getInt8Type(compilationContext1, true));
		argList.add(Type.getInt64Type(compilationContext1, true));
		Type refFuncType = null;
		try{
			refFuncType = Type.getFunctionType(compilationContext1, refReturnType, false, argList);
		}
		catch(TypeCreationException tce){}
		assertNotNull(refFuncType);
		
		try {
			Vector<Type> mainArgList = new Vector<Type>();
			mainArgList.add(refFuncType);
			functType = Type.getFunctionType(compilationContext1, Type.getVoidType(compilationContext1), false, mainArgList);
		}
		catch(TypeCreationException tce){
			assertTrue(tce.getMessage().equals(new String(refFuncType + CompilationContext.INVALID_PARAM_FOR_FUNC)));
		}
		assertNull(functType);
		
		try {
			Vector<Type> mainArgList = new Vector<Type>();
			mainArgList.add(Type.getVoidType(compilationContext1));
			mainArgList.add(Type.getInt32Type(compilationContext1, true));
			functType = Type.getFunctionType(compilationContext1, Type.getVoidType(compilationContext1), false, mainArgList);
		}
		catch(TypeCreationException tce){
			assertTrue(tce.getMessage().equals(new String(Type.getVoidType(compilationContext1) + CompilationContext.INVALID_PARAM_FOR_FUNC)));
		}
		assertNull(functType);
		
		try {
			Vector<Type> mainArgList = new Vector<Type>();
			mainArgList.add(Type.getInt16Type(compilationContext1, true));
			mainArgList.add(refFuncType);
			mainArgList.add(Type.getDoubleType(compilationContext1));
			functType = Type.getFunctionType(compilationContext1, Type.getVoidType(compilationContext1), false, mainArgList);
		}
		catch(TypeCreationException tce){
			assertTrue(tce.getMessage().equals(new String(refFuncType + CompilationContext.INVALID_PARAM_FOR_FUNC)));
		}
		assertNull(functType);
		
		// and finally a few valid parameter types
		Type metadataType = Type.getMetaDataType(compilationContext1);	
		Type labelType = Type.getLabelType(compilationContext1);
		try {
			functType = Type.getFunctionType(compilationContext1, Type.getInt32Type(compilationContext1, true), false, argList);
		}
		catch(TypeCreationException tce){}		
		assertNotNull(functType);
		
		try {
			functType = Type.getFunctionType(compilationContext1, metadataType, false, argList);
		}
		catch(TypeCreationException tce){}		
		assertNotNull(functType);
		
		try {
			functType = Type.getFunctionType(compilationContext1, labelType, false, argList);
		}
		catch(TypeCreationException tce){}		
		assertNotNull(functType);
	}
	
	@Test
	public void testTypeOfVariousTypes(){
		compilationContext1 = new CompilationContext();
		
		Type voidType = Type.getVoidType(compilationContext1);
		assertTrue(voidType.isVoidType());
		
		Type labelType = Type.getLabelType(compilationContext1);
		assertTrue(labelType.isLabelType());
		
		Type metadataType = Type.getMetaDataType(compilationContext1);
		assertTrue(metadataType.isMetadataType());
		
		Type floatType = Type.getFloatType(compilationContext1);
		assertTrue(floatType.isFloatingPointType());
		
		Type doubleType = Type.getDoubleType(compilationContext1);
		assertTrue(doubleType.isDoubleType());
		
		Type fp128Type = Type.getFP128Type(compilationContext1);
		assertTrue(fp128Type.isFP128Type());
		
		Type x86fp80Type = Type.getX86_FP80Type(compilationContext1);
		assertTrue(x86fp80Type.isX86_FP80Type());
		
		Type x86mmxType = Type.getX86MMXType(compilationContext1);
		assertTrue(x86mmxType.isX86_MMXType());
		
		Type ppcFp128Type = Type.getPPCFP128Type(compilationContext1);
		assertTrue(ppcFp128Type.isPPC_FP128Type());
		
		Type i1Type = Type.getInt1Type(compilationContext1, false);
		assertTrue(i1Type.isIntegerType());
		IntegerType iti1 = (IntegerType) i1Type;
		assertTrue(iti1.getNumBits() == 1);
		
		Type i8Type = Type.getInt8Type(compilationContext1, true);
		assertTrue(i8Type.isIntegerType());
		IntegerType iti8 = (IntegerType) i8Type;
		assertTrue(iti8.getNumBits() == 8);
		
		Type i16Type = Type.getInt16Type(compilationContext1, true);
		assertTrue(i16Type.isIntegerType());
		IntegerType iti16 = (IntegerType) i16Type;
		assertTrue(iti16.getNumBits() == 16);
		
		Type i32Type = Type.getInt32Type(compilationContext1, true);
		assertTrue(i32Type.isIntegerType());
		IntegerType iti32 = (IntegerType) i32Type;
		assertTrue(iti32.getNumBits() == 32);
		
		Type i64Type = Type.getInt64Type(compilationContext1, true);
		assertTrue(i64Type.isIntegerType());
		IntegerType iti64 = (IntegerType) i64Type;
		assertTrue(iti64.getNumBits() == 64);
		
		try{
			Type intTypeRandomBitSize = Type.getIntegerType(compilationContext1, 45, true);
			assertTrue(intTypeRandomBitSize.isIntegerType());
			IntegerType iRand = (IntegerType) intTypeRandomBitSize;
			assertTrue(iRand.getNumBits() == 45);
		}
		catch(TypeCreationException tce) {}
		
		try{
			Type ptrType = Type.getPointerType(compilationContext1, i64Type, 0);
			assertTrue(ptrType.isPointerType());
			PointerType ptr = (PointerType) ptrType;
			assertTrue(ptr.getAddressSpace() == 0);
			assertTrue(ptr.getContainedType() == Type.getInt64Type(compilationContext1, true));
			assertTrue(ptr.getTypeAtIndex(0) == Type.getInt64Type(compilationContext1, true));
		}
		catch(TypeCreationException tce) {}
		
		try{
			Type arrayType = Type.getArrayType(compilationContext1, i32Type, 20);
			assertTrue(arrayType.isArrayType());
			ArrayType arr = (ArrayType) arrayType;
			assertTrue(arr.getContainedType() == Type.getInt32Type(compilationContext1, true));
			assertTrue(arr.getNumElements() == 20);
		}
		catch(TypeCreationException tce) {}
		
		try{
			Type structType = Type.getStructType(compilationContext1, false, null, false,
					Type.getInt32Type(compilationContext1, true),
					Type.getDoubleType(compilationContext1),
					Type.getPointerType(compilationContext1, i64Type, 0));
			assertTrue(structType.isStructType());
			StructType str = (StructType) structType;
			assertTrue(!str.getIsPacked());
			assertTrue(str.getTypeAtIndex(0) == Type.getInt32Type(compilationContext1, true));
			assertTrue(str.getTypeAtIndex(1) == Type.getDoubleType(compilationContext1));
			assertTrue(str.getTypeAtIndex(2) == Type.getPointerType(compilationContext1, i64Type, 0));
			assertTrue(str.getElementSize() == 3);
			assertNull(str.getName());
			assertTrue(!str.getIsPacked());
		}
		catch(TypeCreationException tce) {}
		
		try{
			Vector<Type> paramTypes = new Vector<Type>();
			paramTypes.add(i8Type);
			paramTypes.add(Type.getPointerType(compilationContext1, doubleType, 0));
			
			Type funcType = Type.getFunctionType(compilationContext1, i32Type, false, paramTypes);
			assertTrue(funcType.isFunctionType());
			FunctionType ftp = (FunctionType) funcType;
			assertTrue(ftp.getReturnType() == Type.getInt32Type(compilationContext1, true));
			assertTrue(ftp.getNumParams() == 2);
			assertTrue(ftp.getParamType(0) == Type.getInt8Type(compilationContext1, true));
			assertTrue(ftp.getParamType(1) == Type.getPointerType(compilationContext1, doubleType, 0));
		}
		catch(TypeCreationException tce) {}
	}
}

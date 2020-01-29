package org.tamedragon.compilers.clang.tests.semanticanalysis;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Properties;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.tamedragon.common.llvmir.instructions.LLVMUtility;
import org.tamedragon.common.llvmir.types.CompilationContext;
import org.tamedragon.compilers.clang.CLangUtils;
import org.tamedragon.compilers.clang.ErrorHandler;
import org.tamedragon.compilers.clang.ErrorIterator;
import org.tamedragon.compilers.clang.SourceLocation;
import org.tamedragon.compilers.clang.SourceLocationAndMsg;
import org.tamedragon.compilers.clang.abssyntree.TranslationUnit;
import org.tamedragon.compilers.clang.semantics.Environments;
import org.tamedragon.compilers.clang.semantics.Semantic;
import org.tamedragon.compilers.clang.tests.TestInitializer;

public class AssignmentsTest3 extends TestInitializer {
	
	private Semantic semanticAnalyzer;
	private String sourceFilePath;
	private ErrorHandler errorHandler;
	private TranslationUnit translationUnit;
	private Properties properties;
	
	@Before
	public void setUp(){
		properties = LLVMUtility.getDefaultProperties();
		Environments environments = Environments.getInstance();
		environments.reset();
		
		sourceFilePath ="CSrc/Semantic/AssignmentsTest3.c"; 
		ClassLoader classLoader = getClass().getClassLoader();
		sourceFilePath = new File(classLoader.getResource(sourceFilePath).getFile()).getAbsolutePath();
		errorHandler = ErrorHandler.getInstance();
		errorHandler.reset();
		assertTrue(errorHandler.getNumErrors() == 0);
		assertTrue(errorHandler.getNumWarnings() == 0);
		
		CompilationContext compilationContext = new CompilationContext();

		semanticAnalyzer = new Semantic(properties, sourceFilePath, compilationContext);
		translationUnit = CLangUtils.getTranslationByLLParsing(sourceFilePath);
		assertNotNull(translationUnit);		
	}
	
	@Test
	public void test1() {      		
		semanticAnalyzer.translateAbstractTree(translationUnit);
		errorHandler.displayResult();
		
		assertTrue(errorHandler.getNumErrors() == 57);
		assertTrue(errorHandler.getNumWarnings() == 80);
		
		int count = 1;			
		
		ErrorIterator iter = new ErrorIterator(sourceFilePath);
		try{
			while(iter.hasNext()){
				SourceLocationAndMsg srcLcAndMsg =  iter.next();
				SourceLocation srcLc = srcLcAndMsg.getSrcLocation();
				String msg = srcLcAndMsg.getMsg();
				
				int line = srcLc.getLineNum();
				
				if(count == 1){
					assertTrue(line == 42); 
					assertTrue(msg.equals(ErrorHandler.W_CONST_LITERAL_OVERFLOW));
				}
				else if(count == 2){
					assertTrue(line == 43); 
					assertTrue(msg.equals(ErrorHandler.W_CONST_LITERAL_OVERFLOW));			
				}
				else if(count == 3){
					assertTrue(line == 45); 
					assertTrue(msg.equals(ErrorHandler.W_CONST_LITERAL_OVERFLOW));			
				}
				else if(count == 4){
					assertTrue(line == 46); 
					assertTrue(msg.equals(ErrorHandler.W_CONST_LITERAL_OVERFLOW));			
				}
				else if(count == 5){
					assertTrue(line == 48); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNING_A_STRING_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 6){
					assertTrue(line == 49); 
					assertTrue(msg.equals(ErrorHandler.W_DOUBLE_TO_CHAR_NARROWING));			
				}
				else if(count == 7){
					assertTrue(line == 50); 
					assertTrue(msg.equals(ErrorHandler.W_FLOAT_TO_CHAR_NARROWING));			
				}
				else if(count == 8){
					assertTrue(line == 51); 
					assertTrue(msg.equals(ErrorHandler.W_LONG_TO_CHAR_NARROWING));			
				}
				else if(count == 9){
					assertTrue(line == 53); 
					assertTrue(msg.equals(ErrorHandler.W_SHORT_TO_CHAR_NARROWING));			
				}
				else if(count == 10){
					assertTrue(line == 54); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 11){
					assertTrue(line == 55); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 12){
					assertTrue(line == 56); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 13){
					assertTrue(line == 57); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));			
				}
				else if(count == 14){
					assertTrue(line == 58); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));			
				}
				else if(count == 15){
					assertTrue(line == 59); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 16){
					assertTrue(line == 61); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 17){
					assertTrue(line == 62); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 18){
					assertTrue(line == 64); 
					assertTrue(msg.equals(ErrorHandler.W_LONG_TO_CHAR_NARROWING));			
				}
				else if(count == 19){
					assertTrue(line == 65); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 20){
					assertTrue(line == 70); 
					assertTrue(msg.equals(ErrorHandler.W_CONST_LITERAL_OVERFLOW));			
				}
				else if(count == 21){
					assertTrue(line == 71); 
					assertTrue(msg.equals(ErrorHandler.W_DOUBLE_TO_SHORT_NARROWING));			
				}
				else if(count == 22){
					assertTrue(line == 72); 
					assertTrue(msg.equals(ErrorHandler.W_FLOAT_TO_SHORT_NARROWING));			
				}
				else if(count == 23){
					assertTrue(line == 73); 
					assertTrue(msg.equals(ErrorHandler.W_LONG_TO_SHORT_NARROWING));			
				}
				else if(count == 24){
					assertTrue(line == 76); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 25){
					assertTrue(line == 77); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 26){
					assertTrue(line == 78); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 27){
					assertTrue(line == 79); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));			
				}
				else if(count == 28){
					assertTrue(line == 80); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));			
				}
				else if(count == 29){
					assertTrue(line == 81); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 30){
					assertTrue(line == 83); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 31){
					assertTrue(line == 84); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 32){
					assertTrue(line == 86); 
					assertTrue(msg.equals(ErrorHandler.W_LONG_TO_SHORT_NARROWING));			
				}
				else if(count == 33){
					assertTrue(line == 87); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 34){
					assertTrue(line == 92); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 35){
					assertTrue(line == 93); 
					assertTrue(msg.equals(ErrorHandler.W_DOUBLE_TO_INT_NARROWING));			
				}
				else if(count == 36){
					assertTrue(line == 94); 
					assertTrue(msg.equals(ErrorHandler.W_FLOAT_TO_INT_NARROWING));			
				}
				else if(count == 37){
					assertTrue(line == 98); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 38){
					assertTrue(line == 99); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 39){
					assertTrue(line == 100); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 40){
					assertTrue(line == 101); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_STRUCT_OR_UNION_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 41){
					assertTrue(line == 102); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_STRUCT_OR_UNION_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 42){
					assertTrue(line == 103); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 43){
					assertTrue(line == 105); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 44){
					assertTrue(line == 106); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 45){
					assertTrue(line == 109); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 46){
					assertTrue(line == 114); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 47){
					assertTrue(line == 115); 
					assertTrue(msg.equals(ErrorHandler.W_DOUBLE_TO_INT_NARROWING));			
				}
				else if(count == 48){
					assertTrue(line == 116); 
					assertTrue(msg.equals(ErrorHandler.W_FLOAT_TO_INT_NARROWING));			
				}
				else if(count == 49){
					assertTrue(line == 120); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 50){
					assertTrue(line == 121); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 51){
					assertTrue(line == 122); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 52){
					assertTrue(line == 123); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_STRUCT_OR_UNION_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 53){
					assertTrue(line == 124); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_STRUCT_OR_UNION_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 54){
					assertTrue(line == 125); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 55){
					assertTrue(line == 127); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 56){
					assertTrue(line == 128); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 57){
					assertTrue(line == 131); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 58){
					assertTrue(line == 135); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 59){
					assertTrue(line == 136); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 60){
					assertTrue(line == 136); 
					assertTrue(msg.equals(ErrorHandler.W_DOUBLE_TO_INT_NARROWING));			
				}
				else if(count == 61){
					assertTrue(line == 139); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 62){
					assertTrue(line == 140); 
					assertTrue(msg.equals(ErrorHandler.W_DOUBLE_TO_LONG_NARROWING));			
				}
				else if(count == 63){
					assertTrue(line == 141); 
					assertTrue(msg.equals(ErrorHandler.W_FLOAT_TO_LONG_NARROWING));			
				}
				else if(count == 64){
					assertTrue(line == 145); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 65){
					assertTrue(line == 146); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 66){
					assertTrue(line == 147); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 67){
					assertTrue(line == 148); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_STRUCT_OR_UNION_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 68){
					assertTrue(line == 149); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_STRUCT_OR_UNION_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 69){
					assertTrue(line == 150); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 70){
					assertTrue(line == 152); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 71){
					assertTrue(line == 153); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 72){
					assertTrue(line == 156); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 73){
					assertTrue(line == 161); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));			
				}
				else if(count == 74){
					assertTrue(line == 162); 
					assertTrue(msg.equals(ErrorHandler.W_DOUBLE_TO_FLOAT_NARROWING));			
				}
				else if(count == 75){
					assertTrue(line == 165); 
					assertTrue(msg.equals(ErrorHandler.W_INT_TO_FLOAT_NARROWING));			
				}
				else if(count == 76){
					assertTrue(line == 167); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_A_FLOATING_POINT_TYPE));			
				}
				else if(count == 77){
					assertTrue(line == 168); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_A_FLOATING_POINT_TYPE));			
				}
				else if(count == 78){
					assertTrue(line == 169); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_A_FLOATING_POINT_TYPE));			
				}
				else if(count == 79){
					assertTrue(line == 170); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_STRUCT_OR_UNION_TO_A_FLOATING_POINT_TYPE));			
				}
				else if(count == 80){
					assertTrue(line == 171); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_STRUCT_OR_UNION_TO_A_FLOATING_POINT_TYPE));			
				}
				else if(count == 81){
					assertTrue(line == 172); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_A_FLOATING_POINT_TYPE));			
				}
				else if(count == 82){
					assertTrue(line == 173); 
					assertTrue(msg.equals(ErrorHandler.W_INT_TO_FLOAT_NARROWING));			
				}
				else if(count == 83){
					assertTrue(line == 174); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_A_FLOATING_POINT_TYPE));			
				}
				else if(count == 84){
					assertTrue(line == 175); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_A_FLOATING_POINT_TYPE));			
				}
				else if(count == 85){
					assertTrue(line == 176); 
					assertTrue(msg.equals(ErrorHandler.W_INT_TO_FLOAT_NARROWING));			
				}
				else if(count == 86){
					assertTrue(line == 177); 
					assertTrue(msg.equals(ErrorHandler.W_LONG_TO_FLOAT_NARROWING));			
				}
				else if(count == 87){
					assertTrue(line == 178); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_A_FLOATING_POINT_TYPE));			
				}
				else if(count == 88){
					assertTrue(line == 179); 
					assertTrue(msg.equals(ErrorHandler.W_INT_TO_FLOAT_NARROWING));			
				}
				else if(count == 89){
					assertTrue(line == 180); 
					assertTrue(msg.equals(ErrorHandler.W_INT_TO_FLOAT_NARROWING));			
				}
				else if(count == 90){
					assertTrue(line == 183); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_A_FLOATING_POINT_TYPE));			
				}
				else if(count == 91){
					assertTrue(line == 189); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_A_FLOATING_POINT_TYPE));			
				}
				else if(count == 92){
					assertTrue(line == 190); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_A_FLOATING_POINT_TYPE));			
				}
				else if(count == 93){
					assertTrue(line == 191); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_A_FLOATING_POINT_TYPE));			
				}
				else if(count == 94){
					assertTrue(line == 192); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_STRUCT_OR_UNION_TO_A_DOUBLE_TYPE));			
				}
				else if(count == 95){
					assertTrue(line == 193); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_STRUCT_OR_UNION_TO_A_DOUBLE_TYPE));			
				}
				else if(count == 96){
					assertTrue(line == 194); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_A_FLOATING_POINT_TYPE));			
				}
				else if(count == 97){
					assertTrue(line == 196); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_A_FLOATING_POINT_TYPE));			
				}
				else if(count == 98){
					assertTrue(line == 197); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_A_FLOATING_POINT_TYPE));			
				}
				else if(count == 99){
					assertTrue(line == 200); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_A_FLOATING_POINT_TYPE));			
				}
				else if(count == 100){
					assertTrue(line == 202); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "d2" + ErrorHandler.E_NOT_DEFINED));		
				}
				else if(count == 101){
					assertTrue(line == 209); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNING_STRING_TO_POINTER_TO_INT));			
				}
				else if(count == 102){
					assertTrue(line == 210); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_A_FLOATING_POINT_TYPE_TO_A_POINTER));			
				}
				else if(count == 103){
					assertTrue(line == 211); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_A_FLOATING_POINT_TYPE_TO_A_POINTER));			
				}
				else if(count == 104){
					assertTrue(line == 212); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNING_AN_INTEGER_TYPE_TO_A_POINTER));	
				}
				else if(count == 105){
					assertTrue(line == 213); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNING_AN_INTEGER_TYPE_TO_A_POINTER));	
				}
				else if(count == 106){
					assertTrue(line == 214); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNING_AN_INTEGER_TYPE_TO_A_POINTER));			
				}
				else if(count == 107){
					assertTrue(line == 215); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNMENT_INCOMPATIBLE_POINTER_TYPES));			
				}
				else if(count == 108){
					assertTrue(line == 217); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNMENT_INCOMPATIBLE_POINTER_TYPES));	
				}
				else if(count == 109){
					assertTrue(line == 218); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));	
				}
				else if(count == 110){
					assertTrue(line == 219); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));			
				}
				else if(count == 111){
					assertTrue(line == 221); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNING_AN_INTEGER_TYPE_TO_A_POINTER));			
				}
				else if(count == 112){
					assertTrue(line == 223); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNMENT_INCOMPATIBLE_POINTER_TYPES));	
				}
				else if(count == 113){
					assertTrue(line == 224); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNING_AN_INTEGER_TYPE_TO_A_POINTER));	
				}
				else if(count == 114){
					assertTrue(line == 225); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNING_AN_INTEGER_TYPE_TO_A_POINTER));			
				}
				else if(count == 115){
					assertTrue(line == 226); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNMENT_INCOMPATIBLE_POINTER_TYPES));			
				}
				else if(count == 116){
					assertTrue(line == 227); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNING_AN_INTEGER_TYPE_TO_A_POINTER));	
				}
				else if(count == 117){
					assertTrue(line == 228); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNING_AN_INTEGER_TYPE_TO_A_POINTER));	
				}
				else if(count == 118){
					assertTrue(line == 231); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));			
				}
				else if(count == 119){
					assertTrue(line == 232); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));			
				}
				else if(count == 120){
					assertTrue(line == 233); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));	
				}
				else if(count == 121){
					assertTrue(line == 234); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));	
				}
				else if(count == 122){
					assertTrue(line == 235); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));			
				}
				else if(count == 123){
					assertTrue(line == 236); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));			
				}
				else if(count == 124){
					assertTrue(line == 237); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));	
				}
				else if(count == 125){
					assertTrue(line == 238); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));	
				}
				else if(count == 126){
					assertTrue(line == 239); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));			
				}
				else if(count == 127){
					assertTrue(line == 240); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));			
				}
				else if(count == 128){
					assertTrue(line == 242); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));	
				}
				else if(count == 129){
					assertTrue(line == 243); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));	
				}
				else if(count == 130){
					assertTrue(line == 244); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));			
				}
				else if(count == 131){
					assertTrue(line == 245); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));			
				}
				else if(count == 132){
					assertTrue(line == 246); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));	
				}
				else if(count == 133){
					assertTrue(line == 247); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));	
				}
				else if(count == 134){
					assertTrue(line == 248); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));			
				}
				else if(count == 135){
					assertTrue(line == 249); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));			
				}
				else if(count == 136){
					assertTrue(line == 250); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));	
				}
				else if(count == 137){
					assertTrue(line == 251); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));	
				}
				
				count++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		assertTrue(count == 138);
	}
	
	@Test
	public void checkIfAllAreErrors(){
		errorHandler.setAllWarningsAreErrors(true);
		
		System.out.println("AFTER SETTING ALL WARNINGS TO ERRORS");
		semanticAnalyzer.translateAbstractTree(translationUnit);
		errorHandler.displayResult();
		
		assertTrue(errorHandler.getNumErrors() == 137);
		assertTrue(errorHandler.getNumWarnings() == 0);
		
		int count = 1;			
		
		ErrorIterator iter = new ErrorIterator(sourceFilePath);
		try{
			while(iter.hasNext()){
				SourceLocationAndMsg srcLcAndMsg =  iter.next();
				SourceLocation srcLc = srcLcAndMsg.getSrcLocation();
				String msg = srcLcAndMsg.getMsg();
				
				int line = srcLc.getLineNum();
				
				if(count == 1){
					assertTrue(line == 42); 
					assertTrue(msg.equals(ErrorHandler.E_CONST_LITERAL_OVERFLOW));
				}
				else if(count == 2){
					assertTrue(line == 43); 
					assertTrue(msg.equals(ErrorHandler.E_CONST_LITERAL_OVERFLOW));			
				}
				else if(count == 3){
					assertTrue(line == 45); 
					assertTrue(msg.equals(ErrorHandler.E_CONST_LITERAL_OVERFLOW));			
				}
				else if(count == 4){
					assertTrue(line == 46); 
					assertTrue(msg.equals(ErrorHandler.E_CONST_LITERAL_OVERFLOW));			
				}
				else if(count == 5){
					assertTrue(line == 48); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_A_STRING_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 6){
					assertTrue(line == 49); 
					assertTrue(msg.equals(ErrorHandler.E_DOUBLE_TO_CHAR_NARROWING));			
				}
				else if(count == 7){
					assertTrue(line == 50); 
					assertTrue(msg.equals(ErrorHandler.E_FLOAT_TO_CHAR_NARROWING));			
				}
				else if(count == 8){
					assertTrue(line == 51); 
					assertTrue(msg.equals(ErrorHandler.E_LONG_TO_CHAR_NARROWING));			
				}
				else if(count == 9){
					assertTrue(line == 53); 
					assertTrue(msg.equals(ErrorHandler.E_SHORT_TO_CHAR_NARROWING));			
				}
				else if(count == 10){
					assertTrue(line == 54); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 11){
					assertTrue(line == 55); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 12){
					assertTrue(line == 56); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 13){
					assertTrue(line == 57); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));			
				}
				else if(count == 14){
					assertTrue(line == 58); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));			
				}
				else if(count == 15){
					assertTrue(line == 59); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 16){
					assertTrue(line == 61); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 17){
					assertTrue(line == 62); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 18){
					assertTrue(line == 64); 
					assertTrue(msg.equals(ErrorHandler.E_LONG_TO_CHAR_NARROWING));			
				}
				else if(count == 19){
					assertTrue(line == 65); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 20){
					assertTrue(line == 70); 
					assertTrue(msg.equals(ErrorHandler.E_CONST_LITERAL_OVERFLOW));			
				}
				else if(count == 21){
					assertTrue(line == 71); 
					assertTrue(msg.equals(ErrorHandler.E_DOUBLE_TO_SHORT_NARROWING));			
				}
				else if(count == 22){
					assertTrue(line == 72); 
					assertTrue(msg.equals(ErrorHandler.E_FLOAT_TO_SHORT_NARROWING));			
				}
				else if(count == 23){
					assertTrue(line == 73); 
					assertTrue(msg.equals(ErrorHandler.E_LONG_TO_SHORT_NARROWING));			
				}
				else if(count == 24){
					assertTrue(line == 76); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 25){
					assertTrue(line == 77); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 26){
					assertTrue(line == 78); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 27){
					assertTrue(line == 79); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));			
				}
				else if(count == 28){
					assertTrue(line == 80); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));			
				}
				else if(count == 29){
					assertTrue(line == 81); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 30){
					assertTrue(line == 83); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 31){
					assertTrue(line == 84); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 32){
					assertTrue(line == 86); 
					assertTrue(msg.equals(ErrorHandler.E_LONG_TO_SHORT_NARROWING));			
				}
				else if(count == 33){
					assertTrue(line == 87); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 34){
					assertTrue(line == 92); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 35){
					assertTrue(line == 93); 
					assertTrue(msg.equals(ErrorHandler.E_DOUBLE_TO_INT_NARROWING));			
				}
				else if(count == 36){
					assertTrue(line == 94); 
					assertTrue(msg.equals(ErrorHandler.E_FLOAT_TO_INT_NARROWING));			
				}
				else if(count == 37){
					assertTrue(line == 98); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 38){
					assertTrue(line == 99); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 39){
					assertTrue(line == 100); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 40){
					assertTrue(line == 101); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_STRUCT_OR_UNION_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 41){
					assertTrue(line == 102); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_STRUCT_OR_UNION_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 42){
					assertTrue(line == 103); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 43){
					assertTrue(line == 105); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 44){
					assertTrue(line == 106); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 45){
					assertTrue(line == 109); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 46){
					assertTrue(line == 114); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 47){
					assertTrue(line == 115); 
					assertTrue(msg.equals(ErrorHandler.E_DOUBLE_TO_INT_NARROWING));			
				}
				else if(count == 48){
					assertTrue(line == 116); 
					assertTrue(msg.equals(ErrorHandler.E_FLOAT_TO_INT_NARROWING));			
				}
				else if(count == 49){
					assertTrue(line == 120); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 50){
					assertTrue(line == 121); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 51){
					assertTrue(line == 122); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 52){
					assertTrue(line == 123); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_STRUCT_OR_UNION_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 53){
					assertTrue(line == 124); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_STRUCT_OR_UNION_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 54){
					assertTrue(line == 125); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 55){
					assertTrue(line == 127); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 56){
					assertTrue(line == 128); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 57){
					assertTrue(line == 131); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 58){
					assertTrue(line == 135); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 59){
					assertTrue(line == 136); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 60){
					assertTrue(line == 136); 
					assertTrue(msg.equals(ErrorHandler.E_DOUBLE_TO_INT_NARROWING));			
				}
				else if(count == 61){
					assertTrue(line == 139); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 62){
					assertTrue(line == 140); 
					assertTrue(msg.equals(ErrorHandler.E_DOUBLE_TO_LONG_NARROWING));			
				}
				else if(count == 63){
					assertTrue(line == 141); 
					assertTrue(msg.equals(ErrorHandler.E_FLOAT_TO_LONG_NARROWING));			
				}
				else if(count == 64){
					assertTrue(line == 145); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 65){
					assertTrue(line == 146); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 66){
					assertTrue(line == 147); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 67){
					assertTrue(line == 148); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_STRUCT_OR_UNION_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 68){
					assertTrue(line == 149); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_STRUCT_OR_UNION_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 69){
					assertTrue(line == 150); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 70){
					assertTrue(line == 152); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 71){
					assertTrue(line == 153); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 72){
					assertTrue(line == 156); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 73){
					assertTrue(line == 161); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));			
				}
				else if(count == 74){
					assertTrue(line == 162); 
					assertTrue(msg.equals(ErrorHandler.E_DOUBLE_TO_FLOAT_NARROWING));			
				}
				else if(count == 75){
					assertTrue(line == 165); 
					assertTrue(msg.equals(ErrorHandler.E_INT_TO_FLOAT_NARROWING));			
				}
				else if(count == 76){
					assertTrue(line == 167); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_A_FLOATING_POINT_TYPE));			
				}
				else if(count == 77){
					assertTrue(line == 168); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_A_FLOATING_POINT_TYPE));			
				}
				else if(count == 78){
					assertTrue(line == 169); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_A_FLOATING_POINT_TYPE));			
				}
				else if(count == 79){
					assertTrue(line == 170); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_STRUCT_OR_UNION_TO_A_FLOATING_POINT_TYPE));			
				}
				else if(count == 80){
					assertTrue(line == 171); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_STRUCT_OR_UNION_TO_A_FLOATING_POINT_TYPE));			
				}
				else if(count == 81){
					assertTrue(line == 172); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_A_FLOATING_POINT_TYPE));			
				}
				else if(count == 82){
					assertTrue(line == 173); 
					assertTrue(msg.equals(ErrorHandler.E_INT_TO_FLOAT_NARROWING));			
				}
				else if(count == 83){
					assertTrue(line == 174); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_A_FLOATING_POINT_TYPE));			
				}
				else if(count == 84){
					assertTrue(line == 175); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_A_FLOATING_POINT_TYPE));			
				}
				else if(count == 85){
					assertTrue(line == 176); 
					assertTrue(msg.equals(ErrorHandler.E_INT_TO_FLOAT_NARROWING));			
				}
				else if(count == 86){
					assertTrue(line == 177); 
					assertTrue(msg.equals(ErrorHandler.E_LONG_TO_FLOAT_NARROWING));			
				}
				else if(count == 87){
					assertTrue(line == 178); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_A_FLOATING_POINT_TYPE));			
				}
				else if(count == 88){
					assertTrue(line == 179); 
					assertTrue(msg.equals(ErrorHandler.E_INT_TO_FLOAT_NARROWING));			
				}
				else if(count == 89){
					assertTrue(line == 180); 
					assertTrue(msg.equals(ErrorHandler.E_INT_TO_FLOAT_NARROWING));			
				}
				else if(count == 90){
					assertTrue(line == 183); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_A_FLOATING_POINT_TYPE));			
				}
				else if(count == 91){
					assertTrue(line == 189); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_A_FLOATING_POINT_TYPE));			
				}
				else if(count == 92){
					assertTrue(line == 190); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_A_FLOATING_POINT_TYPE));			
				}
				else if(count == 93){
					assertTrue(line == 191); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_A_FLOATING_POINT_TYPE));			
				}
				else if(count == 94){
					assertTrue(line == 192); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_STRUCT_OR_UNION_TO_A_DOUBLE_TYPE));			
				}
				else if(count == 95){
					assertTrue(line == 193); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_STRUCT_OR_UNION_TO_A_DOUBLE_TYPE));			
				}
				else if(count == 96){
					assertTrue(line == 194); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_A_FLOATING_POINT_TYPE));			
				}
				else if(count == 97){
					assertTrue(line == 196); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_A_FLOATING_POINT_TYPE));			
				}
				else if(count == 98){
					assertTrue(line == 197); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_A_FLOATING_POINT_TYPE));			
				}
				else if(count == 99){
					assertTrue(line == 200); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_A_FLOATING_POINT_TYPE));			
				}
				else if(count == 100){
					assertTrue(line == 202); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "d2" + ErrorHandler.E_NOT_DEFINED));		
				}
				else if(count == 101){
					assertTrue(line == 209); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_STRING_TO_POINTER_TO_INT));			
				}
				else if(count == 102){
					assertTrue(line == 210); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_A_FLOATING_POINT_TYPE_TO_A_POINTER));			
				}
				else if(count == 103){
					assertTrue(line == 211); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_A_FLOATING_POINT_TYPE_TO_A_POINTER));			
				}
				else if(count == 104){
					assertTrue(line == 212); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_AN_INTEGER_TYPE_TO_A_POINTER));	
				}
				else if(count == 105){
					assertTrue(line == 213); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_AN_INTEGER_TYPE_TO_A_POINTER));	
				}
				else if(count == 106){
					assertTrue(line == 214); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_AN_INTEGER_TYPE_TO_A_POINTER));			
				}
				else if(count == 107){
					assertTrue(line == 215); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_INCOMPATIBLE_POINTER_TYPES));			
				}
				else if(count == 108){
					assertTrue(line == 217); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_INCOMPATIBLE_POINTER_TYPES));	
				}
				else if(count == 109){
					assertTrue(line == 218); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));	
				}
				else if(count == 110){
					assertTrue(line == 219); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));			
				}
				else if(count == 111){
					assertTrue(line == 221); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_AN_INTEGER_TYPE_TO_A_POINTER));			
				}
				else if(count == 112){
					assertTrue(line == 223); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_INCOMPATIBLE_POINTER_TYPES));	
				}
				else if(count == 113){
					assertTrue(line == 224); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_AN_INTEGER_TYPE_TO_A_POINTER));	
				}
				else if(count == 114){
					assertTrue(line == 225); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_AN_INTEGER_TYPE_TO_A_POINTER));			
				}
				else if(count == 115){
					assertTrue(line == 226); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_INCOMPATIBLE_POINTER_TYPES));			
				}
				else if(count == 116){
					assertTrue(line == 227); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_AN_INTEGER_TYPE_TO_A_POINTER));	
				}
				else if(count == 117){
					assertTrue(line == 228); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_AN_INTEGER_TYPE_TO_A_POINTER));	
				}
				else if(count == 118){
					assertTrue(line == 231); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));			
				}
				else if(count == 119){
					assertTrue(line == 232); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));			
				}
				else if(count == 120){
					assertTrue(line == 233); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));	
				}
				else if(count == 121){
					assertTrue(line == 234); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));	
				}
				else if(count == 122){
					assertTrue(line == 235); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));			
				}
				else if(count == 123){
					assertTrue(line == 236); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));			
				}
				else if(count == 124){
					assertTrue(line == 237); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));	
				}
				else if(count == 125){
					assertTrue(line == 238); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));	
				}
				else if(count == 126){
					assertTrue(line == 239); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));			
				}
				else if(count == 127){
					assertTrue(line == 240); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));			
				}
				else if(count == 128){
					assertTrue(line == 242); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));	
				}
				else if(count == 129){
					assertTrue(line == 243); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));	
				}
				else if(count == 130){
					assertTrue(line == 244); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));			
				}
				else if(count == 131){
					assertTrue(line == 245); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));			
				}
				else if(count == 132){
					assertTrue(line == 246); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));	
				}
				else if(count == 133){
					assertTrue(line == 247); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));	
				}
				else if(count == 134){
					assertTrue(line == 248); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));			
				}
				else if(count == 135){
					assertTrue(line == 249); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));			
				}
				else if(count == 136){
					assertTrue(line == 250); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));	
				}
				else if(count == 137){
					assertTrue(line == 251); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));	
				}
				
				count++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		assertTrue(count == 138);
	}
}

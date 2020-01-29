package org.tamedragon.compilers.clang.tests.semanticanalysis;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Properties;

import org.junit.Before;
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

public class PointersTest3  extends TestInitializer {
	
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
		sourceFilePath ="CSrc/Semantic/PointersTest3.c"; 
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
		
		assertTrue(errorHandler.getNumErrors() == 15);
		assertTrue(errorHandler.getNumWarnings() == 7);
		
		int count = 1;			
		
		ErrorIterator iter = new ErrorIterator(sourceFilePath);
		try{
			while(iter.hasNext()){
				SourceLocationAndMsg srcLcAndMsg =  iter.next();
				SourceLocation srcLc = srcLcAndMsg.getSrcLocation();
				String msg = srcLcAndMsg.getMsg();
				
				int line = srcLc.getLineNum();
				
				if(count == 1){
					assertTrue(line == 60); 
					assertTrue(msg.equals(ErrorHandler.E_CANNOT_ACCESS_ELEMENTS_OF_A_NON_ARRAY));
				}
				else if(count == 2){
					assertTrue(line == 61); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNING_AN_INTEGER_TYPE_TO_A_POINTER));
				}
				else if(count == 3){
					assertTrue(line == 68); 
					assertTrue(msg.equals(ErrorHandler.E_MULTIPLY_ASSIGN_ON_POINTER));
				}
				else if(count == 4){
					assertTrue(line == 70); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "*" + ErrorHandler.E_INVALID_OPERATOR_ON_POINTERS));
				}
				else if(count == 5){
					assertTrue(line == 71); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "/" + ErrorHandler.E_INVALID_OPERATOR_ON_POINTERS));
				}
				else if(count == 6){
					assertTrue(line == 72); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "*" + ErrorHandler.E_INVALID_OPERATOR_ON_POINTER_AND_INTEGER));
				}
				else if(count == 7){
					assertTrue(line == 73); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "/" + ErrorHandler.E_INVALID_OPERATOR_ON_POINTER_AND_INTEGER));
				}
				else if(count == 8){
					assertTrue(line == 74); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_A_FLOATING_POINT_TYPE_TO_A_POINTER));
				}
				else if(count == 9){
					assertTrue(line == 75); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "*" + ErrorHandler.E_INVALID_OPERATOR_ON_POINTER_AND_INTEGER));
				}
				else if(count == 10){
					assertTrue(line == 79); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "%" + ErrorHandler.E_INVALID_OPERATOR_ON_POINTER_AND_INTEGER));
				}
				else if(count == 11){
					assertTrue(line == 80); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "%" + ErrorHandler.E_INVALID_OPERATOR_ON_POINTERS));
				}
				else if(count == 12){
					assertTrue(line == 81); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_A_FLOATING_POINT_TYPE_TO_A_POINTER));
				}
				else if(count == 13){
					assertTrue(line == 82); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_A_FLOATING_POINT_TYPE_TO_A_POINTER));
				}
				else if(count == 14){
					assertTrue(line == 88); 
					assertTrue(msg.equals(ErrorHandler.E_SUBTRACTING_INCOMPATIBLE_POINTERS));
				}
				else if(count == 15){
					assertTrue(line == 90); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNMENT_INCOMPATIBLE_POINTER_TYPES));
				}
				else if(count == 16){
					assertTrue(line == 93); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNMENT_INCOMPATIBLE_POINTER_TYPES));
				}
				else if(count == 17){
					assertTrue(line == 96); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNING_AN_INTEGER_TYPE_TO_A_POINTER));
				}
				else if(count == 18){
					assertTrue(line == 97); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_A_FLOATING_POINT_TYPE_TO_A_POINTER));
				}
				else if(count == 19){
					assertTrue(line == 98); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_A_FLOATING_POINT_TYPE_TO_A_POINTER));
				}
				else if(count == 20){
					assertTrue(line == 115); 
					assertTrue(msg.equals(ErrorHandler.WARNING + "==" + ErrorHandler.W_INVALID_COMPARISON_OF_POINTER_AND_INTEGER));
				}
				else if(count == 21){
					assertTrue(line == 118); 
					assertTrue(msg.equals(ErrorHandler.WARNING + "==" + ErrorHandler.W_COMPARING_INCOMPATIBLE_POINTER_TYPES));
				}
				else if(count == 22){
					assertTrue(line == 121); 
					assertTrue(msg.equals(ErrorHandler.WARNING + "!=" + ErrorHandler.W_INVALID_COMPARISON_OF_POINTER_AND_INTEGER));
				}
							
				count++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		assertTrue(count == 23);
	}
	
	@Test
	public void checkIfAllAreErrors(){
		errorHandler.setAllWarningsAreErrors(true);
		
		System.out.println("AFTER SETTING ALL WARNINGS TO ERRORS");
		semanticAnalyzer.translateAbstractTree(translationUnit);
		errorHandler.displayResult();
		
		assertTrue(errorHandler.getNumErrors() == 22);
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
					assertTrue(line == 60); 
					assertTrue(msg.equals(ErrorHandler.E_CANNOT_ACCESS_ELEMENTS_OF_A_NON_ARRAY));
				}
				else if(count == 2){
					assertTrue(line == 61); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_AN_INTEGER_TYPE_TO_A_POINTER));
				}
				else if(count == 3){
					assertTrue(line == 68); 
					assertTrue(msg.equals(ErrorHandler.E_MULTIPLY_ASSIGN_ON_POINTER));
				}
				else if(count == 4){
					assertTrue(line == 70); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "*" + ErrorHandler.E_INVALID_OPERATOR_ON_POINTERS));
				}
				else if(count == 5){
					assertTrue(line == 71); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "/" + ErrorHandler.E_INVALID_OPERATOR_ON_POINTERS));
				}
				else if(count == 6){
					assertTrue(line == 72); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "*" + ErrorHandler.E_INVALID_OPERATOR_ON_POINTER_AND_INTEGER));
				}
				else if(count == 7){
					assertTrue(line == 73); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "/" + ErrorHandler.E_INVALID_OPERATOR_ON_POINTER_AND_INTEGER));
				}
				else if(count == 8){
					assertTrue(line == 74); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_A_FLOATING_POINT_TYPE_TO_A_POINTER));
				}
				else if(count == 9){
					assertTrue(line == 75); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "*" + ErrorHandler.E_INVALID_OPERATOR_ON_POINTER_AND_INTEGER));
				}
				else if(count == 10){
					assertTrue(line == 79); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "%" + ErrorHandler.E_INVALID_OPERATOR_ON_POINTER_AND_INTEGER));
				}
				else if(count == 11){
					assertTrue(line == 80); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "%" + ErrorHandler.E_INVALID_OPERATOR_ON_POINTERS));
				}
				else if(count == 12){
					assertTrue(line == 81); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_A_FLOATING_POINT_TYPE_TO_A_POINTER));
				}
				else if(count == 13){
					assertTrue(line == 82); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_A_FLOATING_POINT_TYPE_TO_A_POINTER));
				}
				else if(count == 14){
					assertTrue(line == 88); 
					assertTrue(msg.equals(ErrorHandler.E_SUBTRACTING_INCOMPATIBLE_POINTERS));
				}
				else if(count == 15){
					assertTrue(line == 90); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_INCOMPATIBLE_POINTER_TYPES));
				}
				else if(count == 16){
					assertTrue(line == 93); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_INCOMPATIBLE_POINTER_TYPES));
				}
				else if(count == 17){
					assertTrue(line == 96); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_AN_INTEGER_TYPE_TO_A_POINTER));
				}
				else if(count == 18){
					assertTrue(line == 97); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_A_FLOATING_POINT_TYPE_TO_A_POINTER));
				}
				else if(count == 19){
					assertTrue(line == 98); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_A_FLOATING_POINT_TYPE_TO_A_POINTER));
				}
				else if(count == 20){
					assertTrue(line == 115); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "==" + ErrorHandler.E_INVALID_COMPARISON_OF_POINTER_AND_INTEGER));
				}
				else if(count == 21){
					assertTrue(line == 118); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "==" + ErrorHandler.E_COMPARING_INCOMPATIBLE_POINTER_TYPES));
				}
				else if(count == 22){
					assertTrue(line == 121); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "!=" + ErrorHandler.E_INVALID_COMPARISON_OF_POINTER_AND_INTEGER));
				}
				
				count++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		assertTrue(count == 23);
	}
}

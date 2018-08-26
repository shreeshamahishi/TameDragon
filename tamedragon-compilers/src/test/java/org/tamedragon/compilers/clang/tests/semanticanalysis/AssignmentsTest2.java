package org.tamedragon.compilers.clang.tests.semanticanalysis;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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

public class AssignmentsTest2 extends TestInitializer {
	
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
		sourceFilePath ="CSrc/Semantic/AssignmentsTest2.c"; 
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
		
		System.out.println("***************** WAIT HERE: " + errorHandler.getNumErrors());
		
		assertTrue(errorHandler.getNumErrors() == 37);
		assertTrue(errorHandler.getNumWarnings() == 2);
		
		int count = 1;			
		
		ErrorIterator iter = new ErrorIterator(sourceFilePath);
		try{
			while(iter.hasNext()){
				SourceLocationAndMsg srcLcAndMsg =  iter.next();
				SourceLocation srcLc = srcLcAndMsg.getSrcLocation();
				String msg = srcLcAndMsg.getMsg();
				
				int line = srcLc.getLineNum();
				
				if(count == 1){
					assertTrue(line == 18); 
					assertTrue(msg.equals(ErrorHandler.W_RETURN_VALUE_IN_FUNC_RETURNING_VOID));
				}
				else if(count == 2){
					assertTrue(line == 29); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 3){
					assertTrue(line == 29); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_VOID));			
				}
				else if(count == 4){
					assertTrue(line == 34); 
					assertTrue(msg.equals(ErrorHandler.E_RIGHT_SIDE_IS_VOID));			
				}
				else if(count == 5){
					assertTrue(line == 39); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 6){
					assertTrue(line == 40); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 7){
					assertTrue(line == 41); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 8){
					assertTrue(line == 42); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 9){
					assertTrue(line == 43); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 10){
					assertTrue(line == 44); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 11){
					assertTrue(line == 45); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 12){
					assertTrue(line == 46); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 13){
					assertTrue(line == 47); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 14){
					assertTrue(line == 48); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 15){
					assertTrue(line == 49); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 16){
					assertTrue(line == 50); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 17){
					assertTrue(line == 51); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 18){
					assertTrue(line == 52); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 19){
					assertTrue(line == 53); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 20){
					assertTrue(line == 54); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 21){
					assertTrue(line == 55); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 22){
					assertTrue(line == 56); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 23){
					assertTrue(line == 57); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 24){
					assertTrue(line == 58); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 25){
					assertTrue(line == 59); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 26){
					assertTrue(line == 61); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 27){
					assertTrue(line == 62); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 28){
					assertTrue(line == 63); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 29){
					assertTrue(line == 64); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 30){
					assertTrue(line == 65); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 31){
					assertTrue(line == 66); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 32){
					assertTrue(line == 67); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 33){
					assertTrue(line == 68); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 34){
					assertTrue(line == 69); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 35){
					assertTrue(line == 71); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 36){
					assertTrue(line == 71); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_CONSTANT));			
				}
				else if(count == 37){
					assertTrue(line == 72); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 38){
					assertTrue(line == 74); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				
				count++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		assertTrue(count == 40);
	}
	

	@Test
	public void checkIfAllAreErrors(){
		errorHandler.setAllWarningsAreErrors(true);
		
		System.out.println("AFTER SETTING ALL WARNINGS TO ERRORS");
		semanticAnalyzer.translateAbstractTree(translationUnit);
		errorHandler.displayResult();
		
		assertTrue(errorHandler.getNumErrors() == 39);
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
					assertTrue(line == 18); 
					assertTrue(msg.equals(ErrorHandler.E_RETURN_VALUE_IN_FUNC_RETURNING_VOID));
				}
				else if(count == 2){
					assertTrue(line == 29); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 3){
					assertTrue(line == 29); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_VOID));			
				}
				else if(count == 4){
					assertTrue(line == 34); 
					assertTrue(msg.equals(ErrorHandler.E_RIGHT_SIDE_IS_VOID));			
				}
				else if(count == 5){
					assertTrue(line == 39); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 6){
					assertTrue(line == 40); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 7){
					assertTrue(line == 41); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 8){
					assertTrue(line == 42); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 9){
					assertTrue(line == 43); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 10){
					assertTrue(line == 44); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 11){
					assertTrue(line == 45); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 12){
					assertTrue(line == 46); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 13){
					assertTrue(line == 47); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 14){
					assertTrue(line == 48); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 15){
					assertTrue(line == 49); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 16){
					assertTrue(line == 50); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 17){
					assertTrue(line == 51); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 18){
					assertTrue(line == 52); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 19){
					assertTrue(line == 53); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 20){
					assertTrue(line == 54); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 21){
					assertTrue(line == 55); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 22){
					assertTrue(line == 56); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 23){
					assertTrue(line == 57); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 24){
					assertTrue(line == 58); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 25){
					assertTrue(line == 59); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 26){
					assertTrue(line == 61); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 27){
					assertTrue(line == 62); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 28){
					assertTrue(line == 63); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 29){
					assertTrue(line == 64); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 30){
					assertTrue(line == 65); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 31){
					assertTrue(line == 66); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 32){
					assertTrue(line == 67); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 33){
					assertTrue(line == 68); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 34){
					assertTrue(line == 69); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 35){
					assertTrue(line == 71); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 36){
					assertTrue(line == 71); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_CONSTANT));			
				}
				else if(count == 37){
					assertTrue(line == 72); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 38){
					assertTrue(line == 74); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));			
				}
				else if(count == 39){
					assertTrue(line == 93); 
					assertTrue(msg.equals(ErrorHandler.E_LEFTSHIFT_COUNT_GREATER_THAN_SIZE_OF_TYPE));			
				}
				count++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		assertTrue(count == 40);
	}
}

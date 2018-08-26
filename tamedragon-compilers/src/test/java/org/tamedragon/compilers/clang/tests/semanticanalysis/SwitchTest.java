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

public class SwitchTest extends TestInitializer {
	
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
		sourceFilePath ="CSrc/Semantic/SwitchTest.c"; 
		errorHandler = ErrorHandler.getInstance();
		errorHandler.reset();
		assertTrue(errorHandler.getNumErrors() == 0);
		assertTrue(errorHandler.getNumWarnings() == 0);
		
		CompilationContext compilationContext = new CompilationContext();
		semanticAnalyzer = new Semantic(properties, sourceFilePath, compilationContext);
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(sourceFilePath).getFile());
		translationUnit = CLangUtils.getTranslationByLLParsing(file.getAbsolutePath());
		assertNotNull(translationUnit);		
	}
	
	@Test
	public void test1() {      		
		semanticAnalyzer.translateAbstractTree(translationUnit);
		errorHandler.displayResult();
		
		assertTrue(errorHandler.getNumErrors() == 13);
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
					assertTrue(line == 19); 
					assertTrue(msg.equals(ErrorHandler.E_SWITCH_EXPR_MUST_BE_CONST));
				}
				else if(count == 2){
					assertTrue(line == 54); 
					assertTrue(msg.equals(ErrorHandler.E_SWITCH_EXPR_MUST_BE_CONST));			
				}
				else if(count == 3){
					assertTrue(line == 61); 
					assertTrue(msg.equals(ErrorHandler.E_SWITCH_EXPR_MUST_BE_CONST));				
				}
				else if(count == 4){
					assertTrue(line == 96); 
					assertTrue(msg.equals(ErrorHandler.E_CASE_USED_OUTSIDE_SWITCH));					
				}
				else if(count == 5){
					assertTrue(line == 99); 
					assertTrue(msg.equals(ErrorHandler.E_BREAK_IN_WRONG_LOCATION));	
				}
				else if(count == 6){
					assertTrue(line == 100); 
					assertTrue(msg.equals(ErrorHandler.E_CASE_USED_OUTSIDE_SWITCH));					
				}
				else if(count == 7){
					assertTrue(line == 103); 
					assertTrue(msg.equals(ErrorHandler.E_BREAK_IN_WRONG_LOCATION));					
				}
				else if(count == 8){
					assertTrue(line == 105); 
					assertTrue(msg.equals(ErrorHandler.E_DEFAULT_USED_OUTSIDE_SWITCH));					
				}
				else if(count == 9){
					assertTrue(line == 142); 
					assertTrue(msg.equals(ErrorHandler.E_CASE_EXPR_MUST_BE_CONST));					
				}
				else if(count == 10){
					assertTrue(line == 145); 
					assertTrue(msg.equals(ErrorHandler.E_CASE_EXPR_MUST_BE_CONST));							
				}
				else if(count == 11){
					assertTrue(line == 148); 
					assertTrue(msg.equals(ErrorHandler.E_CASE_EXPR_MUST_BE_CONST));						
				}
				else if(count == 12){
					assertTrue(line == 151); 
					assertTrue(msg.equals(ErrorHandler.E_CASE_EXPR_MUST_BE_CONST));					
				}
				else if(count == 13){
					assertTrue(line == 154); 
					assertTrue(msg.equals(ErrorHandler.E_CASE_EXPR_MUST_BE_CONST));				
				}
				
				count++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		assertTrue(count == 14);
	}
	
	@Test
	public void checkIfAllAreErrors(){
		errorHandler.setAllWarningsAreErrors(true);
		
		System.out.println("AFTER SETTING ALL WARNINGS TO ERRORS");
		semanticAnalyzer.translateAbstractTree(translationUnit);
		errorHandler.displayResult();
		
		assertTrue(errorHandler.getNumErrors() == 13);
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
					assertTrue(line == 19); 
					assertTrue(msg.equals(ErrorHandler.E_SWITCH_EXPR_MUST_BE_CONST));
				}
				else if(count == 2){
					assertTrue(line == 54); 
					assertTrue(msg.equals(ErrorHandler.E_SWITCH_EXPR_MUST_BE_CONST));			
				}
				else if(count == 3){
					assertTrue(line == 61); 
					assertTrue(msg.equals(ErrorHandler.E_SWITCH_EXPR_MUST_BE_CONST));				
				}
				else if(count == 4){
					assertTrue(line == 96); 
					assertTrue(msg.equals(ErrorHandler.E_CASE_USED_OUTSIDE_SWITCH));					
				}
				else if(count == 5){
					assertTrue(line == 99); 
					assertTrue(msg.equals(ErrorHandler.E_BREAK_IN_WRONG_LOCATION));	
				}
				else if(count == 6){
					assertTrue(line == 100); 
					assertTrue(msg.equals(ErrorHandler.E_CASE_USED_OUTSIDE_SWITCH));					
				}
				else if(count == 7){
					assertTrue(line == 103); 
					assertTrue(msg.equals(ErrorHandler.E_BREAK_IN_WRONG_LOCATION));					
				}
				else if(count == 8){
					assertTrue(line == 105); 
					assertTrue(msg.equals(ErrorHandler.E_DEFAULT_USED_OUTSIDE_SWITCH));					
				}
				else if(count == 9){
					assertTrue(line == 142); 
					assertTrue(msg.equals(ErrorHandler.E_CASE_EXPR_MUST_BE_CONST));					
				}
				else if(count == 10){
					assertTrue(line == 145); 
					assertTrue(msg.equals(ErrorHandler.E_CASE_EXPR_MUST_BE_CONST));							
				}
				else if(count == 11){
					assertTrue(line == 148); 
					assertTrue(msg.equals(ErrorHandler.E_CASE_EXPR_MUST_BE_CONST));						
				}
				else if(count == 12){
					assertTrue(line == 151); 
					assertTrue(msg.equals(ErrorHandler.E_CASE_EXPR_MUST_BE_CONST));					
				}
				else if(count == 13){
					assertTrue(line == 154); 
					assertTrue(msg.equals(ErrorHandler.E_CASE_EXPR_MUST_BE_CONST));				
				}
				
				count++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		assertTrue(count == 14);
	}	
}

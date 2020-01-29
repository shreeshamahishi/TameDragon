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

public class ExpressionsTest1 extends TestInitializer {
	
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
		sourceFilePath ="CSrc/Semantic/ExpressionsTest1.c"; 
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
		
		assertTrue(errorHandler.getNumErrors() == 7);
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
					assertTrue(line == 38); 
					assertTrue(msg.equals(ErrorHandler.E_NEGATIVE_OPERATOR_CANNOT_BE_USED));
				}
				else if(count == 2){
					assertTrue(line == 39); 
					assertTrue(msg.equals(ErrorHandler.E_NEGATIVE_OPERATOR_CANNOT_BE_USED));
				}
				else if(count == 3){
					assertTrue(line == 40); 
					assertTrue(msg.equals(ErrorHandler.E_NEGATIVE_OPERATOR_CANNOT_BE_USED));	
				}
				else if(count == 4){
					assertTrue(line == 41); 
					assertTrue(msg.equals(ErrorHandler.E_NEGATIVE_OPERATOR_CANNOT_BE_USED));
				}
				else if(count == 5){
					assertTrue(line == 42); 
					assertTrue(msg.equals(ErrorHandler.E_NEGATIVE_OPERATOR_CANNOT_BE_USED));
				}
				else if(count == 6){
					assertTrue(line == 43); 
					assertTrue(msg.equals(ErrorHandler.E_NEGATIVE_OPERATOR_CANNOT_BE_USED));				
				}
				else if(count == 7){
					assertTrue(line == 43); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));				
				}
				count++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		assertTrue(count == 8);
	}
	
	@Test
	public void checkIfAllAreErrors(){
		errorHandler.setAllWarningsAreErrors(true);
		
		System.out.println("AFTER SETTING ALL WARNINGS TO ERRORS");
		semanticAnalyzer.translateAbstractTree(translationUnit);
		errorHandler.displayResult();
		
		assertTrue(errorHandler.getNumErrors() == 7);
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
					assertTrue(line == 38); 
					assertTrue(msg.equals(ErrorHandler.E_NEGATIVE_OPERATOR_CANNOT_BE_USED));
				}
				else if(count == 2){
					assertTrue(line == 39); 
					assertTrue(msg.equals(ErrorHandler.E_NEGATIVE_OPERATOR_CANNOT_BE_USED));
				}
				else if(count == 3){
					assertTrue(line == 40); 
					assertTrue(msg.equals(ErrorHandler.E_NEGATIVE_OPERATOR_CANNOT_BE_USED));	
				}
				else if(count == 4){
					assertTrue(line == 41); 
					assertTrue(msg.equals(ErrorHandler.E_NEGATIVE_OPERATOR_CANNOT_BE_USED));
				}
				else if(count == 5){
					assertTrue(line == 42); 
					assertTrue(msg.equals(ErrorHandler.E_NEGATIVE_OPERATOR_CANNOT_BE_USED));
				}
				else if(count == 6){
					assertTrue(line == 43); 
					assertTrue(msg.equals(ErrorHandler.E_NEGATIVE_OPERATOR_CANNOT_BE_USED));				
				}	
				else if(count == 7){
					assertTrue(line == 43); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));				
				}
				count++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		assertTrue(count == 8);
	}	
}

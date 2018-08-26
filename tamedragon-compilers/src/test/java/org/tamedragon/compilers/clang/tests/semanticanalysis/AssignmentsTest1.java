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

public class AssignmentsTest1 extends TestInitializer {
	
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
		sourceFilePath ="CSrc/Semantic/AssignmentsTest1.c"; 
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
		
		assertTrue(errorHandler.getNumErrors() == 0);
		assertTrue(errorHandler.getNumWarnings() == 18);
		
		int count = 1;			
		
		ErrorIterator iter = new ErrorIterator(sourceFilePath);
		try{
			while(iter.hasNext()){
				SourceLocationAndMsg srcLcAndMsg =  iter.next();
				SourceLocation srcLc = srcLcAndMsg.getSrcLocation();
				String msg = srcLcAndMsg.getMsg();
				
				int line = srcLc.getLineNum();
				
				if(count == 1){
					assertTrue(line == 15); 
					assertTrue(msg.equals(ErrorHandler.W_CONST_LITERAL_OVERFLOW));
				}
				else if(count == 2){
					assertTrue(line == 17); 
					assertTrue(msg.equals(ErrorHandler.W_CONST_LITERAL_OVERFLOW));					
				}
				else if(count == 3){
					assertTrue(line == 18); 
					assertTrue(msg.equals(ErrorHandler.W_CONST_LITERAL_OVERFLOW));					
				}
				else if(count == 4){
					assertTrue(line == 21); 
					assertTrue(msg.equals(ErrorHandler.W_DOUBLE_TO_FLOAT_NARROWING));					
				}
				else if(count == 5){
					assertTrue(line == 24); 
					assertTrue(msg.equals(ErrorHandler.W_DOUBLE_TO_CHAR_NARROWING));	
				}
				else if(count == 6){
					assertTrue(line == 25); 
					assertTrue(msg.equals(ErrorHandler.W_DOUBLE_TO_SHORT_NARROWING));					
				}
				else if(count == 7){
					assertTrue(line == 26); 
					assertTrue(msg.equals(ErrorHandler.W_DOUBLE_TO_INT_NARROWING));					
				}
				else if(count == 8){
					assertTrue(line == 27); 
					assertTrue(msg.equals(ErrorHandler.W_DOUBLE_TO_LONG_NARROWING));				
				}
				else if(count == 9){
					assertTrue(line == 28); 
					assertTrue(msg.equals(ErrorHandler.W_DOUBLE_TO_FLOAT_NARROWING));
				}
				else if(count == 10){
					assertTrue(line == 31); 
					assertTrue(msg.equals(ErrorHandler.W_FLOAT_TO_CHAR_NARROWING));					
				}				
				else if(count == 11){
					assertTrue(line == 32); 
					assertTrue(msg.equals(ErrorHandler.W_FLOAT_TO_SHORT_NARROWING));										
				}
				else if(count == 12){
					assertTrue(line == 33); 
					assertTrue(msg.equals(ErrorHandler.W_FLOAT_TO_INT_NARROWING));				
				}
				else if(count == 13){
					assertTrue(line == 34); 
					assertTrue(msg.equals(ErrorHandler.W_FLOAT_TO_LONG_NARROWING));	
				}
				else if(count == 14){
					assertTrue(line == 42); 
					assertTrue(msg.equals(ErrorHandler.W_INT_TO_FLOAT_NARROWING));
				}
				else if(count == 15){
					assertTrue(line == 45); 
					assertTrue(msg.equals(ErrorHandler.W_SHORT_TO_CHAR_NARROWING));		
				}
				else if(count == 16){
					assertTrue(line == 59); 
					assertTrue(msg.equals(ErrorHandler.W_LONG_TO_CHAR_NARROWING));			
				}
				else if(count == 17){
					assertTrue(line == 60); 
					assertTrue(msg.equals(ErrorHandler.W_LONG_TO_SHORT_NARROWING));
				}
				else if(count == 18){
					assertTrue(line == 63); 
					assertTrue(msg.equals(ErrorHandler.W_LONG_TO_FLOAT_NARROWING));		
				}
			
				count++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		assertTrue(count == 19);
	}
	
	@Test
	public void checkIfAllAreErrors(){
		errorHandler.setAllWarningsAreErrors(true);
		
		System.out.println("AFTER SETTING ALL WARNINGS TO ERRORS");
		semanticAnalyzer.translateAbstractTree(translationUnit);
		errorHandler.displayResult();
		
		assertTrue(errorHandler.getNumErrors() == 18);
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
					assertTrue(line == 15); 
					assertTrue(msg.equals(ErrorHandler.E_CONST_LITERAL_OVERFLOW));
				}
				else if(count == 2){
					assertTrue(line == 17); 
					assertTrue(msg.equals(ErrorHandler.E_CONST_LITERAL_OVERFLOW));					
				}
				else if(count == 3){
					assertTrue(line == 18); 
					assertTrue(msg.equals(ErrorHandler.E_CONST_LITERAL_OVERFLOW));					
				}
				else if(count == 4){
					assertTrue(line == 21); 
					assertTrue(msg.equals(ErrorHandler.E_DOUBLE_TO_FLOAT_NARROWING));					
				}
				else if(count == 5){
					assertTrue(line == 24); 
					assertTrue(msg.equals(ErrorHandler.E_DOUBLE_TO_CHAR_NARROWING));	
				}
				else if(count == 6){
					assertTrue(line == 25); 
					assertTrue(msg.equals(ErrorHandler.E_DOUBLE_TO_SHORT_NARROWING));					
				}
				else if(count == 7){
					assertTrue(line == 26); 
					assertTrue(msg.equals(ErrorHandler.E_DOUBLE_TO_INT_NARROWING));					
				}
				else if(count == 8){
					assertTrue(line == 27); 
					assertTrue(msg.equals(ErrorHandler.E_DOUBLE_TO_LONG_NARROWING));				
				}
				else if(count == 9){
					assertTrue(line == 28); 
					assertTrue(msg.equals(ErrorHandler.E_DOUBLE_TO_FLOAT_NARROWING));
				}
				else if(count == 10){
					assertTrue(line == 31); 
					assertTrue(msg.equals(ErrorHandler.E_FLOAT_TO_CHAR_NARROWING));					
				}				
				else if(count == 11){
					assertTrue(line == 32); 
					assertTrue(msg.equals(ErrorHandler.E_FLOAT_TO_SHORT_NARROWING));										
				}
				else if(count == 12){
					assertTrue(line == 33); 
					assertTrue(msg.equals(ErrorHandler.E_FLOAT_TO_INT_NARROWING));				
				}
				else if(count == 13){
					assertTrue(line == 34); 
					assertTrue(msg.equals(ErrorHandler.E_FLOAT_TO_LONG_NARROWING));	
				}
				else if(count == 14){
					assertTrue(line == 42); 
					assertTrue(msg.equals(ErrorHandler.E_INT_TO_FLOAT_NARROWING));
				}
				else if(count == 15){
					assertTrue(line == 45); 
					assertTrue(msg.equals(ErrorHandler.E_SHORT_TO_CHAR_NARROWING));		
				}
				else if(count == 16){
					assertTrue(line == 59); 
					assertTrue(msg.equals(ErrorHandler.E_LONG_TO_CHAR_NARROWING));			
				}
				else if(count == 17){
					assertTrue(line == 60); 
					assertTrue(msg.equals(ErrorHandler.E_LONG_TO_SHORT_NARROWING));
				}
				else if(count == 18){
					assertTrue(line == 63); 
					assertTrue(msg.equals(ErrorHandler.E_LONG_TO_FLOAT_NARROWING));		
				}
			
				count++;
			}
			
			assertTrue(count == 19);
		}
		catch(Exception e){
			e.printStackTrace();
		} 
	}	
}

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

public class ForLoopsTest  extends TestInitializer {
	
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
		sourceFilePath ="CSrc/Semantic/ForLoopsTest.c"; 
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
		
		assertTrue(errorHandler.getNumErrors() == 2);
		assertTrue(errorHandler.getNumWarnings() == 4);
		
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
					assertTrue(msg.equals(ErrorHandler.E_CONTINUE_IN_WRONG_LOCATION));
				}
				else if(count == 2){
					assertTrue(line == 62); 
					assertTrue(msg.equals(ErrorHandler.W_RETURN_VALUE_IN_FUNC_RETURNING_VOID));					
				}
				else if(count == 3){
					assertTrue(line == 64); 
					assertTrue(msg.equals(ErrorHandler.W_RETURN_VALUE_IN_FUNC_RETURNING_VOID));					
				}
				else if(count == 4){
					assertTrue(line == 66); 
					assertTrue(msg.equals(ErrorHandler.E_BREAK_IN_WRONG_LOCATION));
				}
				else if(count == 5){
					assertTrue(line == 69); 
					assertTrue(msg.equals(ErrorHandler.W_RETURN_VALUE_IN_FUNC_RETURNING_VOID));
				}
				else if(count == 6){
					assertTrue(line == 71); 
					assertTrue(msg.equals(ErrorHandler.W_RETURN_VALUE_IN_FUNC_RETURNING_VOID));
				}
				
				count++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		assertTrue(count == 7);
		
	}
	
	//	Now set all warnings are errors
	@Test
	public void checkIfAllAreErrors(){
		System.out.println("AFTER SETTING ALL WARNINGS TO ERRORS");
		errorHandler.setAllWarningsAreErrors(true);
		
		semanticAnalyzer.translateAbstractTree(translationUnit);
		errorHandler.displayResult();
		assertTrue(errorHandler.getNumErrors() == 6);
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
					assertTrue(msg.equals(ErrorHandler.E_CONTINUE_IN_WRONG_LOCATION));
				}
				else if(count == 2){
					assertTrue(line == 62); 
					assertTrue(msg.equals(ErrorHandler.E_RETURN_VALUE_IN_FUNC_RETURNING_VOID));					
				}
				else if(count == 3){
					assertTrue(line == 64); 
					assertTrue(msg.equals(ErrorHandler.E_RETURN_VALUE_IN_FUNC_RETURNING_VOID));					
				}
				else if(count == 4){
					assertTrue(line == 66); 
					assertTrue(msg.equals(ErrorHandler.E_BREAK_IN_WRONG_LOCATION));
				}
				else if(count == 5){
					assertTrue(line == 69); 
					assertTrue(msg.equals(ErrorHandler.E_RETURN_VALUE_IN_FUNC_RETURNING_VOID));
				}
				else if(count == 6){
					assertTrue(line == 71); 
					assertTrue(msg.equals(ErrorHandler.E_RETURN_VALUE_IN_FUNC_RETURNING_VOID));
				}
				
				count++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		assertTrue(count == 7);
	}
}

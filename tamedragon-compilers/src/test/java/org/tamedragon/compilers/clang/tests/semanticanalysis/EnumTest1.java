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

public class EnumTest1 extends TestInitializer {
	
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
		sourceFilePath ="CSrc/Semantic/EnumTest1.c"; 
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
		
		assertTrue(errorHandler.getNumErrors() == 10);
		assertTrue(errorHandler.getNumWarnings() == 1);
		
		int count = 1;			
		
		ErrorIterator iter = new ErrorIterator(sourceFilePath);
		try{
			while(iter.hasNext()){
				SourceLocationAndMsg srcLcAndMsg =  iter.next();
				SourceLocation srcLc = srcLcAndMsg.getSrcLocation();
				String msg = srcLcAndMsg.getMsg();
				
				int line = srcLc.getLineNum();
				
				if(count == 1){
					assertTrue(line == 8); 
					assertTrue(msg.equals(ErrorHandler.E_ENUM_INIT_VAL_NOT_INTEGER));
				}
				else if(count == 2){
					assertTrue(line == 8); 
					assertTrue(msg.equals(ErrorHandler.E_ENUM_INIT_VAL_NOT_INTEGER));					
				}
				else if(count == 3){
					assertTrue(line == 9); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "z:" + ErrorHandler.E_ENUMERATOR_ALREADY_DEFINED 
							+ ErrorHandler.AI_SEE_DECLARATION + sourceFilePath + ":(7)"));							
				}
				else if(count == 4){
					assertTrue(line == 11); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "R:" + ErrorHandler.E_ENUMERATOR_ALREADY_DEFINED 
							+ ErrorHandler.AI_SEE_DECLARATION + sourceFilePath + ":(11)"));					
				}
				else if(count == 5){
					assertTrue(line == 23); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "x" + ErrorHandler.E_VARIABLE_ALREADY_DEFINED 
							+ ErrorHandler.AI_SEE_DECLARATION + sourceFilePath + ":(7)"));	
				}
				else if(count == 6){
					assertTrue(line == 25); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "boolean:" + ErrorHandler.E_ENUM_TYPE_ALREADY_DEFINED 
							+ ErrorHandler.AI_SEE_DECLARATION + sourceFilePath + ":(5)"));					
				}
				else if(count == 7){
					assertTrue(line == 29); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "boolean" + ErrorHandler.E_NOT_DEFINED));				
				}
				else if(count == 8){
					assertTrue(line == 30); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "boolean" + ErrorHandler.E_NOT_DEFINED));				
				}
				else if(count == 9){
					assertTrue(line == 30); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "YES: " + ErrorHandler.E_LEFT_SIDE_NOT_STRUCT_OR_UNION));				
				}
				else if(count == 10){
					assertTrue(line == 33); 					
					assertTrue(msg.equals(ErrorHandler.W_CONST_LITERAL_OVERFLOW));		
				}
				else if(count == 11){
					assertTrue(line == 34); 					
					assertTrue(msg.equals(ErrorHandler.ERROR + "YES1" + ErrorHandler.E_NOT_DEFINED));		
				}
				
				count++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		assertTrue(count == 12);
	}
	
	@Test
	public void checkIfAllAreErrors(){
		errorHandler.setAllWarningsAreErrors(true);
		
		System.out.println("AFTER SETTING ALL WARNINGS TO ERRORS");
		semanticAnalyzer.translateAbstractTree(translationUnit);
		errorHandler.displayResult();
		
		assertTrue(errorHandler.getNumErrors() == 11);
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
					assertTrue(line == 8); 
					assertTrue(msg.equals(ErrorHandler.E_ENUM_INIT_VAL_NOT_INTEGER));
				}
				else if(count == 2){
					assertTrue(line == 8); 
					assertTrue(msg.equals(ErrorHandler.E_ENUM_INIT_VAL_NOT_INTEGER));					
				}
				else if(count == 3){
					assertTrue(line == 9); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "z:" + ErrorHandler.E_ENUMERATOR_ALREADY_DEFINED 
							+ ErrorHandler.AI_SEE_DECLARATION + sourceFilePath + ":(7)"));							
				}
				else if(count == 4){
					assertTrue(line == 11); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "R:" + ErrorHandler.E_ENUMERATOR_ALREADY_DEFINED 
							+ ErrorHandler.AI_SEE_DECLARATION + sourceFilePath + ":(11)"));					
				}
				else if(count == 5){
					assertTrue(line == 23); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "x" + ErrorHandler.E_VARIABLE_ALREADY_DEFINED 
							+ ErrorHandler.AI_SEE_DECLARATION + sourceFilePath + ":(7)"));	
				}
				else if(count == 6){
					assertTrue(line == 25); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "boolean:" + ErrorHandler.E_ENUM_TYPE_ALREADY_DEFINED 
							+ ErrorHandler.AI_SEE_DECLARATION + sourceFilePath + ":(5)"));					
				}
				else if(count == 7){
					assertTrue(line == 29); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "boolean" + ErrorHandler.E_NOT_DEFINED));				
				}
				else if(count == 8){
					assertTrue(line == 30); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "boolean" + ErrorHandler.E_NOT_DEFINED));				
				}
				else if(count == 9){
					assertTrue(line == 30); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "YES: " + ErrorHandler.E_LEFT_SIDE_NOT_STRUCT_OR_UNION));				
				}
				else if(count == 10){
					assertTrue(line == 33); 					
					assertTrue(msg.equals(ErrorHandler.E_CONST_LITERAL_OVERFLOW));		
				}
				else if(count == 11){
					assertTrue(line == 34); 					
					assertTrue(msg.equals(ErrorHandler.ERROR + "YES1" + ErrorHandler.E_NOT_DEFINED));		
				}
				
				count++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		assertTrue(count == 12);
	}	
}

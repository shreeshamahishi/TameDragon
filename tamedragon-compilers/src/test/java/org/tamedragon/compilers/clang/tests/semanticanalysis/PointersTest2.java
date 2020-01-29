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

public class PointersTest2  extends TestInitializer {
	
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
		sourceFilePath ="CSrc/Semantic/PointersTest2.c"; 
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
		
		assertTrue(errorHandler.getNumErrors() == 11);
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
					assertTrue(line == 31); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "pa: " +
							ErrorHandler.E_ARRAY_SIZE_NOT_SPECIFIED.substring(ErrorHandler.ERROR.length(), 
							ErrorHandler.E_ARRAY_SIZE_NOT_SPECIFIED.length())
							));
				}
				else if(count == 2){
					assertTrue(line == 35); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "pz " +
							ErrorHandler.E_ARRAY_SIZE_SPECIFIER_IS_INVALID.substring(ErrorHandler.ERROR.length(), 
							ErrorHandler.E_ARRAY_SIZE_SPECIFIER_IS_INVALID.length())
							));
				}
				else if(count == 3){
					assertTrue(line == 36); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "pn " +
							ErrorHandler.E_ARRAY_SIZE_SPECIFIER_IS_INVALID.substring(ErrorHandler.ERROR.length(), 
							ErrorHandler.E_ARRAY_SIZE_SPECIFIER_IS_INVALID.length())
							));
				}
				else if(count == 4){
					assertTrue(line == 42); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "pe " +
							ErrorHandler.E_ARRAY_SIZE_SPECIFIER_IS_INVALID.substring(ErrorHandler.ERROR.length(), 
							ErrorHandler.E_ARRAY_SIZE_SPECIFIER_IS_INVALID.length())
							));
				}
				else if(count == 5){
					assertTrue(line == 43); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "pf " +
							ErrorHandler.E_ARRAY_SIZE_SPECIFIER_IS_INVALID.substring(ErrorHandler.ERROR.length(), 
							ErrorHandler.E_ARRAY_SIZE_SPECIFIER_IS_INVALID.length())
							));
				}
				if(count == 6){
					assertTrue(line == 45); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "varr1 " +
							ErrorHandler.E_ARRAY_SIZE_SPECIFIER_IS_INVALID.substring(ErrorHandler.ERROR.length(), 
							ErrorHandler.E_ARRAY_SIZE_SPECIFIER_IS_INVALID.length())
							));
				}
				else if(count == 7){
					assertTrue(line == 46); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "varr2 " +
							ErrorHandler.E_ARRAY_SIZE_SPECIFIER_IS_INVALID.substring(ErrorHandler.ERROR.length(), 
							ErrorHandler.E_ARRAY_SIZE_SPECIFIER_IS_INVALID.length())
							));
				}
				else if(count == 8){
					assertTrue(line == 49); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));
				}
				else if(count == 9){
					assertTrue(line == 50); 
					assertTrue(msg.equals(ErrorHandler.E_CANNOT_INCREMENT_A_CONSTANT));
				}
				else if(count == 10){
					assertTrue(line == 51); 
					assertTrue(msg.equals(ErrorHandler.E_CANNOT_DECREMENT_A_CONSTANT));
				}
				else if(count == 11){
					assertTrue(line == 55); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));
				}
				else if(count == 12){
					assertTrue(line == 55); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNING_AN_INTEGER_TYPE_TO_A_POINTER));
				}
				
				count++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		assertTrue(count == 13);
	}
	
	@Test
	public void checkIfAllAreErrors(){
		errorHandler.setAllWarningsAreErrors(true);
		
		System.out.println("AFTER SETTING ALL WARNINGS TO ERRORS");
		semanticAnalyzer.translateAbstractTree(translationUnit);
		errorHandler.displayResult();
		
		assertTrue(errorHandler.getNumErrors() == 12);
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
					assertTrue(line == 31); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "pa: " +
							ErrorHandler.E_ARRAY_SIZE_NOT_SPECIFIED.substring(ErrorHandler.ERROR.length(), 
							ErrorHandler.E_ARRAY_SIZE_NOT_SPECIFIED.length())
							));
				}
				else if(count == 2){
					assertTrue(line == 35); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "pz " +
							ErrorHandler.E_ARRAY_SIZE_SPECIFIER_IS_INVALID.substring(ErrorHandler.ERROR.length(), 
							ErrorHandler.E_ARRAY_SIZE_SPECIFIER_IS_INVALID.length())
							));
				}
				else if(count == 3){
					assertTrue(line == 36); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "pn " +
							ErrorHandler.E_ARRAY_SIZE_SPECIFIER_IS_INVALID.substring(ErrorHandler.ERROR.length(), 
							ErrorHandler.E_ARRAY_SIZE_SPECIFIER_IS_INVALID.length())
							));
				}
				else if(count == 4){
					assertTrue(line == 42); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "pe " +
							ErrorHandler.E_ARRAY_SIZE_SPECIFIER_IS_INVALID.substring(ErrorHandler.ERROR.length(), 
							ErrorHandler.E_ARRAY_SIZE_SPECIFIER_IS_INVALID.length())
							));
				}
				else if(count == 5){
					assertTrue(line == 43); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "pf " +
							ErrorHandler.E_ARRAY_SIZE_SPECIFIER_IS_INVALID.substring(ErrorHandler.ERROR.length(), 
							ErrorHandler.E_ARRAY_SIZE_SPECIFIER_IS_INVALID.length())
							));
				}
				if(count == 6){
					assertTrue(line == 45); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "varr1 " +
							ErrorHandler.E_ARRAY_SIZE_SPECIFIER_IS_INVALID.substring(ErrorHandler.ERROR.length(), 
							ErrorHandler.E_ARRAY_SIZE_SPECIFIER_IS_INVALID.length())
							));
				}
				else if(count == 7){
					assertTrue(line == 46); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "varr2 " +
							ErrorHandler.E_ARRAY_SIZE_SPECIFIER_IS_INVALID.substring(ErrorHandler.ERROR.length(), 
							ErrorHandler.E_ARRAY_SIZE_SPECIFIER_IS_INVALID.length())
							));
				}
				else if(count == 8){
					assertTrue(line == 49); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));
				}
				else if(count == 9){
					assertTrue(line == 50); 
					assertTrue(msg.equals(ErrorHandler.E_CANNOT_INCREMENT_A_CONSTANT));
				}
				else if(count == 10){
					assertTrue(line == 51); 
					assertTrue(msg.equals(ErrorHandler.E_CANNOT_DECREMENT_A_CONSTANT));
				}
				else if(count == 11){
					assertTrue(line == 55); 
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE));
				}
				else if(count == 12){
					assertTrue(line == 55); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_AN_INTEGER_TYPE_TO_A_POINTER));
				}
				

				count++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		assertTrue(count == 13);
	}
}

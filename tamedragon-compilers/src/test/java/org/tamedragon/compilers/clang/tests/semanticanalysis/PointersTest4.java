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

public class PointersTest4  extends TestInitializer {
	
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
		sourceFilePath ="CSrc/Semantic/PointersTest4.c"; 
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
		
		assertTrue(errorHandler.getNumErrors() == 3);
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
					assertTrue(line == 4); 
					String msgShouldBe = ErrorHandler.WARNING + ErrorHandler.AI_INCOMPATIBLE_TYPE_IN_RETURN + 
					ErrorHandler.W_ASSIGNING_AN_INTEGER_TYPE_TO_A_POINTER.substring(ErrorHandler.WARNING.length(), ErrorHandler.W_ASSIGNING_AN_INTEGER_TYPE_TO_A_POINTER.length());
					assertTrue(msg.equals(msgShouldBe));			
				}
				else if(count == 2){
					assertTrue(line == 30); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "arr: " +
							ErrorHandler.E_ARRAY_SIZE_NOT_SPECIFIED.substring(ErrorHandler.ERROR.length(), 
							ErrorHandler.E_ARRAY_SIZE_NOT_SPECIFIED.length())
							));
				}
				else if(count == 2){
					assertTrue(line == 31); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "carr: " +
							ErrorHandler.E_ARRAY_SIZE_NOT_SPECIFIED.substring(ErrorHandler.ERROR.length(), 
							ErrorHandler.E_ARRAY_SIZE_NOT_SPECIFIED.length())
							));
				}
				else if(count == 2){
					assertTrue(line == 35); 
					String shouldBe = ErrorHandler.WARNING + " to come together " + ErrorHandler.W_STRING_ARRAY_BOUNDS_OVERFLOW;
					assertTrue(msg.equals(shouldBe));
				}
				else if(count == 2){
					assertTrue(line == 47); 
					assertTrue(msg.equals(ErrorHandler.E_CANNOT_INCREMENT_A_CONSTANT));
				}
				
				count++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		assertTrue(count == 6);
	}
	
	@Test
	public void checkIfAllAreErrors(){
		errorHandler.setAllWarningsAreErrors(true);
		
		System.out.println("AFTER SETTING ALL WARNINGS TO ERRORS");
		semanticAnalyzer.translateAbstractTree(translationUnit);
		errorHandler.displayResult();
		
		assertTrue(errorHandler.getNumErrors() == 5);
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
					assertTrue(line == 4); 
					String msgShouldBe = ErrorHandler.ERROR + ErrorHandler.AI_INCOMPATIBLE_TYPE_IN_RETURN + 
					ErrorHandler.E_ASSIGNING_AN_INTEGER_TYPE_TO_A_POINTER.substring(ErrorHandler.ERROR.length(), ErrorHandler.E_ASSIGNING_AN_INTEGER_TYPE_TO_A_POINTER.length());
					assertTrue(msg.equals(msgShouldBe));			
				}
				else if(count == 2){
					assertTrue(line == 30); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "arr: " +
							ErrorHandler.E_ARRAY_SIZE_NOT_SPECIFIED.substring(ErrorHandler.ERROR.length(), 
							ErrorHandler.E_ARRAY_SIZE_NOT_SPECIFIED.length())
							));
				}
				else if(count == 3){
					assertTrue(line == 31); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "carr: " +
							ErrorHandler.E_ARRAY_SIZE_NOT_SPECIFIED.substring(ErrorHandler.ERROR.length(), 
							ErrorHandler.E_ARRAY_SIZE_NOT_SPECIFIED.length())
							));
				}
				else if(count == 4){
					assertTrue(line == 35); 
					assertTrue(msg.contains(" to come together "));
					assertTrue(msg.contains(ErrorHandler.E_STRING_ARRAY_BOUNDS_OVERFLOW));
				}
				else if(count == 5){
					assertTrue(line == 47); 
					assertTrue(msg.equals(ErrorHandler.E_CANNOT_INCREMENT_A_CONSTANT));
				}
				count++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		assertTrue(count == 6);
	}
}

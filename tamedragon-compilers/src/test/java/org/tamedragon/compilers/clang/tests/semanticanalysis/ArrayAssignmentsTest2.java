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

public class ArrayAssignmentsTest2 extends TestInitializer {

	private Semantic semanticAnalyzer;
	private String sourceFilePath;
	private ErrorHandler errorHandler;
	private TranslationUnit translationUnit;
	private Properties properties;

	@Before
	public void setUp(){
		Environments environments = Environments.getInstance();
		properties = LLVMUtility.getDefaultProperties();
		environments.reset();
		sourceFilePath ="CSrc/Semantic/ArrayAssignmentsTest2.c"; 
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
					assertTrue(line == 18); 
					assertTrue(msg.equals(ErrorHandler.E_ARRAY_SIZE_NOT_SPECIFIED));
				}
				else if(count == 2){
					assertTrue(line == 24); 
					assertTrue(msg.equals(ErrorHandler.E_ARRAY_SIZE_NOT_SPECIFIED));					
				}
				else if(count == 3){
					assertTrue(line == 48); 
					String msgShouldBe = ErrorHandler.ERROR + "darray: " + ErrorHandler.E_INVALID_INITIALIZATION_OF_MEMBER.substring(ErrorHandler.ERROR.length(), ErrorHandler.E_INVALID_INITIALIZATION_OF_MEMBER.length());
					assertTrue(msg.equals(msgShouldBe));	
				}
				else if(count == 4){
					assertTrue(line == 49); 
					String msgShouldBe = ErrorHandler.ERROR + "darray: " + ErrorHandler.E_INVALID_INITIALIZATION_OF_MEMBER.substring(ErrorHandler.ERROR.length(), ErrorHandler.E_INVALID_INITIALIZATION_OF_MEMBER.length());
					assertTrue(msg.equals(msgShouldBe));					
				}
				else if(count == 5){
					assertTrue(line == 50); 
					String msgShouldBe = ErrorHandler.ERROR + "darray: " + ErrorHandler.E_INVALID_INITIALIZATION_OF_MEMBER.substring(ErrorHandler.ERROR.length(), ErrorHandler.E_INVALID_INITIALIZATION_OF_MEMBER.length());
					assertTrue(msg.equals(msgShouldBe));						
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
					assertTrue(line == 18); 
					assertTrue(msg.equals(ErrorHandler.E_ARRAY_SIZE_NOT_SPECIFIED));
				}
				else if(count == 2){
					assertTrue(line == 24); 
					assertTrue(msg.equals(ErrorHandler.E_ARRAY_SIZE_NOT_SPECIFIED));					
				}
				else if(count == 3){
					assertTrue(line == 48); 
					String msgShouldBe = ErrorHandler.ERROR + "darray: " + ErrorHandler.E_INVALID_INITIALIZATION_OF_MEMBER.substring(ErrorHandler.ERROR.length(), ErrorHandler.E_INVALID_INITIALIZATION_OF_MEMBER.length());
					assertTrue(msg.equals(msgShouldBe));						
				}
				else if(count == 4){
					assertTrue(line == 49); 
					String msgShouldBe = ErrorHandler.ERROR + "darray: " + ErrorHandler.E_INVALID_INITIALIZATION_OF_MEMBER.substring(ErrorHandler.ERROR.length(), ErrorHandler.E_INVALID_INITIALIZATION_OF_MEMBER.length());
					assertTrue(msg.equals(msgShouldBe));						
				}
				else if(count == 5){
					assertTrue(line == 50); 
					String msgShouldBe = ErrorHandler.ERROR + "darray: " + ErrorHandler.E_INVALID_INITIALIZATION_OF_MEMBER.substring(ErrorHandler.ERROR.length(), ErrorHandler.E_INVALID_INITIALIZATION_OF_MEMBER.length());
					assertTrue(msg.equals(msgShouldBe));					
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

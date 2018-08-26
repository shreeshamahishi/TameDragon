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

public class FuncRedefinitionTest1 extends TestInitializer{
	
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
		sourceFilePath = "CSrc/Semantic/FuncRedefinitionTest1.c"; 
		errorHandler = ErrorHandler.getInstance();
		errorHandler.reset();
		assertTrue(errorHandler.getNumErrors() == 0);
		assertTrue(errorHandler.getNumWarnings() == 0);
		
		CompilationContext compilationContext = new CompilationContext();
		semanticAnalyzer = new Semantic(properties, sourceFilePath, compilationContext);
		try{
		translationUnit = CLangUtils.getTranslationByLLParsing(sourceFilePath);
		}
		catch(Throwable e){
			e.printStackTrace();
		}
		assertNotNull(translationUnit);		
	}
	
	@Test
	public void test1() {      
		
		semanticAnalyzer.translateAbstractTree(translationUnit);
		errorHandler.displayResult();
		
		System.out.println("Num errs = " + errorHandler.getNumErrors());
		System.out.println("Num warnings = " + errorHandler.getNumWarnings());
		
		assertTrue(errorHandler.getNumErrors() == 1);
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
					assertTrue(line == 5); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "bar" + ErrorHandler.E_FUNCTION_ALREADY_DEFINED 
							+ ErrorHandler.AI_SEE_DECLARATION + sourceFilePath + ":(3)"));	
				}
				
			
				count++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		assertTrue(count == 2);
				
	}

	@Test
	public void test2(){
		errorHandler.setAllWarningsAreErrors(false);
		errorHandler.setDeclarationErrorsAreWarnings(false);
		semanticAnalyzer.translateAbstractTree(translationUnit);

		System.out.println("AFTER SETTING THAT ALL WARNINGS ARE NOT ERRORS, BUT DECLARATIONS ERRORS ARE NOT WARNINGS, THEY ARE ERRORS");
		errorHandler.displayResult();
		assertTrue(errorHandler.getNumErrors() == 1);
		assertTrue(errorHandler.getNumWarnings() == 0);
	
	}
}

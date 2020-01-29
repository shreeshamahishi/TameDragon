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

public class DeclaratorTest1 extends TestInitializer{
	
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
		sourceFilePath = "CSrc/Semantic/DeclaratorTest1.c"; 
		ClassLoader classLoader = getClass().getClassLoader();
		sourceFilePath = new File(classLoader.getResource(sourceFilePath).getFile()).getAbsolutePath();
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
		
		assertTrue(errorHandler.getNumErrors() == 13);
		assertTrue(errorHandler.getNumWarnings() == 8);
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
					assertTrue(msg.equals(ErrorHandler.W_INVALID_STORAGE_SPEC_FOR_EXTERNAL_DEC));
				}
				else if(count == 2){
					assertTrue(line == 5); 
					assertTrue(msg.equals(ErrorHandler.W_EXTERN_DEC_CANNOT_HAVE_INIT));					
				}
				else if(count == 3){
					assertTrue(line == 5); 
					assertTrue(msg.equals(ErrorHandler.W_EXTERN_DEC_CANNOT_HAVE_INIT));					
				}
				else if(count == 4){
					assertTrue(line == 5); 
					assertTrue(msg.equals(ErrorHandler.W_EXTERN_DEC_CANNOT_HAVE_INIT));	
				}
				else if(count == 5){
					assertTrue(line == 7); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "lo" + ErrorHandler.E_VARIABLE_ALREADY_DEFINED 
												+ ErrorHandler.AI_SEE_DECLARATION + sourceFilePath + ":(7)"));					
				}
				else if(count == 6){
					assertTrue(line == 8); 
					assertTrue(msg.equals(ErrorHandler.E_MORE_THAN_ONE_STORAGE_SPEC));					
				}
				else if(count == 7){
					assertTrue(line == 8); 
					assertTrue(msg.equals(ErrorHandler.W_EXTERN_DEC_CANNOT_HAVE_INIT));				
				}
				else if(count == 8){
					assertTrue(line == 8); 
					assertTrue(msg.equals(ErrorHandler.W_EXTERN_DEC_CANNOT_HAVE_INIT));	
				}
				else if(count == 9){
					assertTrue(line == 8); 
					assertTrue(msg.equals(ErrorHandler.W_EXTERN_DEC_CANNOT_HAVE_INIT));						
				}				
				else if(count == 10){
					assertTrue(line == 10); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "lo" + ErrorHandler.E_VARIABLE_ALREADY_DEFINED 
							+ ErrorHandler.AI_SEE_DECLARATION + sourceFilePath + ":(7)"));										
				}
				else if(count == 11){
					assertTrue(line == 19); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "i" + ErrorHandler.E_VARIABLE_ALREADY_DEFINED 
							+ ErrorHandler.AI_SEE_DECLARATION + sourceFilePath + ":(5)"));					
				}
				else if(count == 12){
					assertTrue(line == 23); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "d" + ErrorHandler.E_VARIABLE_ALREADY_DEFINED 
							+ ErrorHandler.AI_SEE_DECLARATION + sourceFilePath + ":(4)"));					
				}
				else if(count == 13){
					assertTrue(line == 25); 
					assertTrue(msg.equals(ErrorHandler.E_MORE_THAN_ONE_STORAGE_SPEC));					
				}
				else if(count == 14){
					assertTrue(line == 25); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "mm" + ErrorHandler.E_VARIABLE_ALREADY_DEFINED 
							+ ErrorHandler.AI_SEE_DECLARATION + sourceFilePath + ":(16)"));					
				}
				else if(count == 15){
					assertTrue(line == 26); 
					assertTrue(msg.equals(ErrorHandler.E_MORE_THAN_ONE_TYPE));					
				}
				else if(count == 16){
					assertTrue(line == 29); 
					assertTrue(msg.equals(ErrorHandler.W_EXTERN_DEC_CANNOT_HAVE_INIT));					
				}
				else if(count == 17){
					assertTrue(line == 31); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "mm" + ErrorHandler.E_VARIABLE_ALREADY_DEFINED 
							+ ErrorHandler.AI_SEE_DECLARATION + sourceFilePath + ":(16)"));					
				}
				else if(count == 18){
					assertTrue(line == 36); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "foo" + ErrorHandler.E_FUNCTION_SIGNATURE_MISMATCH 
							+ ErrorHandler.AI_SEE_DECLARATION + sourceFilePath + ":(12)" ));					
				}
				else if(count == 19){
					assertTrue(line == 40); 
					assertTrue(msg.equals(ErrorHandler.E_MORE_THAN_ONE_TYPE));					
				}
				else if(count == 20){
					assertTrue(line == 41); 
					assertTrue(msg.equals(ErrorHandler.E_MORE_THAN_ONE_STORAGE_SPEC));					
				}
				else if(count == 21){
					assertTrue(line == 49); 
					String msgShouldBe = ErrorHandler.ERROR + ErrorHandler.AI_INCOMPATIBLE_TYPE_IN_RETURN 
					+ ErrorHandler.E_ASSIGNING_A_FLOATING_POINT_TYPE_TO_A_POINTER.substring(
							ErrorHandler.ERROR.length(), ErrorHandler.E_ASSIGNING_A_FLOATING_POINT_TYPE_TO_A_POINTER.length());
					assertTrue(msg.equals(msgShouldBe));					
				}
			
				count++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		assertTrue(count == 22);
				
	}

	@Test
	public void test2(){
		errorHandler.setAllWarningsAreErrors(false);
		errorHandler.setDeclarationErrorsAreWarnings(false);
		semanticAnalyzer.translateAbstractTree(translationUnit);

		System.out.println("AFTER SETTING THAT ALL WARNINGS ARE NOT ERRORS, BUT DECLARATIONS ERRORS ARE NOT WARNING, THEY ARE ERRORS");
		errorHandler.displayResult();
		assertTrue(errorHandler.getNumErrors() == 21);
		assertTrue(errorHandler.getNumWarnings() == 0);
	
	}
}

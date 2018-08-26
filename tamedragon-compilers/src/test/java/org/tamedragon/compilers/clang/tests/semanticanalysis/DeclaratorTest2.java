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

public class DeclaratorTest2 extends TestInitializer{
	
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
		sourceFilePath = "CSrc/Semantic/DeclaratorTest2.c"; 
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
		
		assertTrue(errorHandler.getNumErrors() == 6);
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
					assertTrue(line == 17); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "bar" + ErrorHandler.E_FUNCTION_ALREADY_DEFINED 
							+ ErrorHandler.AI_SEE_DECLARATION + sourceFilePath + ":(11)"));	
				}
				else if(count == 2){
					assertTrue(line == 27); 
					assertTrue(msg.equals(ErrorHandler.W_DOUBLE_CANNOT_BE_USED_WITH_UNSIGNED_SPEC));					
				}
				else if(count == 3){
					assertTrue(line == 27); 
					assertTrue(msg.equals(ErrorHandler.W_CONST_LITERAL_OVERFLOW));					
				}
				else if(count == 4){
					assertTrue(line == 28); 
					assertTrue(msg.equals(ErrorHandler.W_FLOAT_CANNOT_BE_USED_WITH_UNSIGNED_SPEC));					
				}
				else if(count == 5){
					assertTrue(line == 28); 
					assertTrue(msg.equals(ErrorHandler.W_CONST_LITERAL_OVERFLOW));						
				}
				else if(count == 6){
					assertTrue(line == 29); 
					assertTrue(msg.equals(ErrorHandler.W_DOUBLE_CANNOT_BE_USED_WITH_SIGNED_SPEC));
				}
				else if(count == 7){
					assertTrue(line == 29); 
					assertTrue(msg.equals(ErrorHandler.W_CONST_LITERAL_OVERFLOW));					
				}
				else if(count == 8){
					assertTrue(line == 30); 
					assertTrue(msg.equals(ErrorHandler.W_FLOAT_CANNOT_BE_USED_WITH_SIGNED_SPEC));
				}
				else if(count == 9){
					assertTrue(line == 30); 
					assertTrue(msg.equals(ErrorHandler.W_CONST_LITERAL_OVERFLOW));					
				}	
				else if(count == 10){
					assertTrue(line == 37); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "foobar" + ErrorHandler.E_FUNCTION_ALREADY_DEFINED 
							+ ErrorHandler.AI_SEE_DECLARATION + sourceFilePath + ":(23)"));		
				}	
				else if(count == 11){
					assertTrue(line == 44); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "otr3" + ErrorHandler.E_VARIABLE_ALREADY_DEFINED 
							+ ErrorHandler.AI_SEE_DECLARATION + sourceFilePath + ":(1)"));		
				}	
				else if(count == 12){
					assertTrue(line == 45); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "ddd" + ErrorHandler.E_VARIABLE_ALREADY_DEFINED 
							+ ErrorHandler.AI_SEE_DECLARATION + sourceFilePath + ":(2)"));		
				}	
				else if(count == 13){
					assertTrue(line == 46); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "largedbl" + ErrorHandler.E_VARIABLE_ALREADY_DEFINED 
							+ ErrorHandler.AI_SEE_DECLARATION + sourceFilePath + ":(2)"));		
				}	
				else if(count == 14){
					assertTrue(line == 55); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "anotherbar" + ErrorHandler.E_FUNCTION_SIGNATURE_MISMATCH 
							+ ErrorHandler.AI_SEE_DECLARATION + sourceFilePath + ":(43)"));	
				}	
			
				count++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		assertTrue(count == 15);
	}
	
	@Test
	public void checkIfAllAreErrors(){
		errorHandler.setAllWarningsAreErrors(true);
		
		System.out.println("AFTER SETTING ALL WARNINGS TO ERRORS");
		semanticAnalyzer.translateAbstractTree(translationUnit);
		errorHandler.displayResult();
		
		assertTrue(errorHandler.getNumErrors() == 14);
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
					assertTrue(line == 17); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "bar" + ErrorHandler.E_FUNCTION_ALREADY_DEFINED 
							+ ErrorHandler.AI_SEE_DECLARATION + sourceFilePath + ":(11)"));	
				}
				else if(count == 2){
					assertTrue(line == 27); 
					assertTrue(msg.equals(ErrorHandler.E_DOUBLE_CANNOT_BE_USED_WITH_UNSIGNED_SPEC));					
				}
				else if(count == 3){
					assertTrue(line == 27); 
					assertTrue(msg.equals(ErrorHandler.E_CONST_LITERAL_OVERFLOW));					
				}
				else if(count == 4){
					assertTrue(line == 28); 
					assertTrue(msg.equals(ErrorHandler.E_FLOAT_CANNOT_BE_USED_WITH_UNSIGNED_SPEC));					
				}
				else if(count == 5){
					assertTrue(line == 28); 
					assertTrue(msg.equals(ErrorHandler.E_CONST_LITERAL_OVERFLOW));						
				}
				else if(count == 6){
					assertTrue(line == 29); 
					assertTrue(msg.equals(ErrorHandler.E_DOUBLE_CANNOT_BE_USED_WITH_SIGNED_SPEC));
				}
				else if(count == 7){
					assertTrue(line == 29); 
					assertTrue(msg.equals(ErrorHandler.E_CONST_LITERAL_OVERFLOW));					
				}
				else if(count == 8){
					assertTrue(line == 30); 
					assertTrue(msg.equals(ErrorHandler.E_FLOAT_CANNOT_BE_USED_WITH_SIGNED_SPEC));
				}
				else if(count == 9){
					assertTrue(line == 30); 
					assertTrue(msg.equals(ErrorHandler.E_CONST_LITERAL_OVERFLOW));					
				}	
				else if(count == 10){
					assertTrue(line == 37); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "foobar" + ErrorHandler.E_FUNCTION_ALREADY_DEFINED 
							+ ErrorHandler.AI_SEE_DECLARATION + sourceFilePath + ":(23)"));		
				}	
				else if(count == 11){
					assertTrue(line == 44); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "otr3" + ErrorHandler.E_VARIABLE_ALREADY_DEFINED 
							+ ErrorHandler.AI_SEE_DECLARATION + sourceFilePath + ":(1)"));		
				}	
				else if(count == 12){
					assertTrue(line == 45); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "ddd" + ErrorHandler.E_VARIABLE_ALREADY_DEFINED 
							+ ErrorHandler.AI_SEE_DECLARATION + sourceFilePath + ":(2)"));		
				}	
				else if(count == 13){
					assertTrue(line == 46); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "largedbl" + ErrorHandler.E_VARIABLE_ALREADY_DEFINED 
							+ ErrorHandler.AI_SEE_DECLARATION + sourceFilePath + ":(2)"));		
				}	
				else if(count == 14){
					assertTrue(line == 55); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "anotherbar" + ErrorHandler.E_FUNCTION_SIGNATURE_MISMATCH 
							+ ErrorHandler.AI_SEE_DECLARATION + sourceFilePath + ":(43)"));	
				}	
			
				count++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		assertTrue(count == 15);
	}
}

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

public class StructsTest1 extends TestInitializer {
	
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
		sourceFilePath ="CSrc/Semantic/StructsTest1.c"; 
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
		
		assertTrue(errorHandler.getNumErrors() == 19);
		assertTrue(errorHandler.getNumWarnings() == 5);
		
		int count = 1;			
		
		ErrorIterator iter = new ErrorIterator(sourceFilePath);
		try{
			while(iter.hasNext()){
				SourceLocationAndMsg srcLcAndMsg =  iter.next();
				SourceLocation srcLc = srcLcAndMsg.getSrcLocation();
				String msg = srcLcAndMsg.getMsg();
				
				int line = srcLc.getLineNum();
				
				if(count == 1){
					assertTrue(line == 6); 					
					assertTrue(msg.equals(ErrorHandler.ERROR + "lo:" + ErrorHandler.E_DUPLICATE_MEMBER_NAME ));		
				}
				else if(count == 2){
					assertTrue(line == 9); 
					assertTrue(msg.equals(ErrorHandler.E_MORE_THAN_ONE_TYPE));					
				}
				else if(count == 3){
					assertTrue(line == 10); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "i:" + ErrorHandler.E_DUPLICATE_MEMBER_NAME ));					
				}
				else if(count == 4){
					assertTrue(line == 11); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "d:" + ErrorHandler.E_DUPLICATE_MEMBER_NAME ));					
				}
				else if(count == 5){
					assertTrue(line == 12); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "lo:" + ErrorHandler.E_DUPLICATE_MEMBER_NAME ));
				}
				else if(count == 6){
					assertTrue(line == 17); 					
					assertTrue(msg.equals(ErrorHandler.ERROR + "comp:" + ErrorHandler.E_FUNCTION_AS_STRUCT_MEMBER ));				
				}
				else if(count == 7){
					assertTrue(line == 19); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "x:" + ErrorHandler.E_FUNCTION_AS_STRUCT_MEMBER ));					
				}
				else if(count == 8){
					assertTrue(line == 22); 					
					assertTrue(msg.equals(ErrorHandler.ERROR + "c" + ErrorHandler.E_ARRAY_OF_FUNCTIONS ));				
				}
				else if(count == 9){
					assertTrue(line == 23); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "mm" + ErrorHandler.E_ARRAY_OF_FUNCTIONS ));	
				}
				else if(count == 10){
					assertTrue(line == 24); 					
					assertTrue(msg.equals(ErrorHandler.ERROR + "qn" + ErrorHandler.E_DECL_FUNC_RETURNING_FUNC ));			
				}				
				else if(count == 11){
					assertTrue(line == 25); 					
					assertTrue(msg.equals(ErrorHandler.ERROR + "dd" + ErrorHandler.E_DECL_FUNC_RETURNING_ARRAY ));													
				}
				
				else if(count == 12){
					assertTrue(line == 43); 					
					assertTrue(msg.equals(ErrorHandler.ERROR + "q:" + ErrorHandler.E_STRUCT_MEMBER_HAS_UNKNOWN_TYPE ));
				}
				else if(count == 13){
					assertTrue(line == 64); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "test3" + ErrorHandler.E_STRUCT_ALREADY_DEFINED 
							+ ErrorHandler.AI_SEE_DECLARATION + sourceFilePath + ":(54)"));	
				}
				else if(count == 14){
					assertTrue(line == 72); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "inner" + ErrorHandler.E_STRUCT_ALREADY_DEFINED 
							+ ErrorHandler.AI_SEE_DECLARATION + sourceFilePath + ":(57)"));	
				}
				else if(count == 15){
					assertTrue(line == 88); 
					assertTrue(msg.equals(ErrorHandler.W_CONST_LITERAL_OVERFLOW));			
				}
				else if(count == 16){
					assertTrue(line == 89); 					
					assertTrue(msg.equals(ErrorHandler.W_EXCESS_INITIALIZERS_IN_STRUCT_INIT));			
				}				
				else if(count == 17){
					assertTrue(line == 90); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 18){
					assertTrue(line == 90); 
					assertTrue(msg.equals(ErrorHandler.W_EXCESS_INITIALIZERS_IN_STRUCT_INIT));			
				}
				else if(count == 19){
					assertTrue(line == 95); 					
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_A_FLOATING_POINT_TYPE));	
				}
				else if(count == 20){
					assertTrue(line == 98); 					
					assertTrue(msg.equals(ErrorHandler.ERROR + "another_test:" + ErrorHandler.E_STRUCT_NOT_DEFINED ));	
				}
				else if(count == 21){
					assertTrue(line == 98); 
					assertTrue(msg.equals(ErrorHandler.W_EXCESS_INITIALIZERS_IN_STRUCT_INIT));			
				}
				else if(count == 22){
					assertTrue(line == 106); 					
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_CONSTANT));
				}
				else if(count == 23){
					assertTrue(line == 108); 					
					assertTrue(msg.equals(ErrorHandler.ERROR + "heh:" + ErrorHandler.E_NOT_MEMBER_OF_STRUCT_OR_UNION ));			
				}
				else if(count == 24){
					assertTrue(line == 108); 					
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));			
				}
			
				count++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		assertTrue(count == 25);
	}
	
	@Test
	public void checkIfAllAreErrors(){
		errorHandler.setAllWarningsAreErrors(true);
		
		System.out.println("AFTER SETTING ALL WARNINGS TO ERRORS");
		semanticAnalyzer.translateAbstractTree(translationUnit);
		errorHandler.displayResult();
		
		assertTrue(errorHandler.getNumErrors() == 24);
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
					assertTrue(line == 6); 					
					assertTrue(msg.equals(ErrorHandler.ERROR + "lo:" + ErrorHandler.E_DUPLICATE_MEMBER_NAME ));		
				}
				else if(count == 2){
					assertTrue(line == 9); 
					assertTrue(msg.equals(ErrorHandler.E_MORE_THAN_ONE_TYPE));					
				}
				else if(count == 3){
					assertTrue(line == 10); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "i:" + ErrorHandler.E_DUPLICATE_MEMBER_NAME ));					
				}
				else if(count == 4){
					assertTrue(line == 11); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "d:" + ErrorHandler.E_DUPLICATE_MEMBER_NAME ));					
				}
				else if(count == 5){
					assertTrue(line == 12); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "lo:" + ErrorHandler.E_DUPLICATE_MEMBER_NAME ));
				}
				else if(count == 6){
					assertTrue(line == 17); 					
					assertTrue(msg.equals(ErrorHandler.ERROR + "comp:" + ErrorHandler.E_FUNCTION_AS_STRUCT_MEMBER ));				
				}
				else if(count == 7){
					assertTrue(line == 19); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "x:" + ErrorHandler.E_FUNCTION_AS_STRUCT_MEMBER ));					
				}
				else if(count == 8){
					assertTrue(line == 22); 					
					assertTrue(msg.equals(ErrorHandler.ERROR + "c" + ErrorHandler.E_ARRAY_OF_FUNCTIONS ));				
				}
				else if(count == 9){
					assertTrue(line == 23); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "mm" + ErrorHandler.E_ARRAY_OF_FUNCTIONS ));	
				}
				else if(count == 10){
					assertTrue(line == 24); 					
					assertTrue(msg.equals(ErrorHandler.ERROR + "qn" + ErrorHandler.E_DECL_FUNC_RETURNING_FUNC ));			
				}				
				else if(count == 11){
					assertTrue(line == 25); 					
					assertTrue(msg.equals(ErrorHandler.ERROR + "dd" + ErrorHandler.E_DECL_FUNC_RETURNING_ARRAY ));													
				}
				else if(count == 12){
					assertTrue(line == 43); 					
					assertTrue(msg.equals(ErrorHandler.ERROR + "q:" + ErrorHandler.E_STRUCT_MEMBER_HAS_UNKNOWN_TYPE ));
				}
				else if(count == 13){
					assertTrue(line == 64); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "test3" + ErrorHandler.E_STRUCT_ALREADY_DEFINED 
							+ ErrorHandler.AI_SEE_DECLARATION + sourceFilePath + ":(54)"));	
				}
				else if(count == 14){
					assertTrue(line == 72); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "inner" + ErrorHandler.E_STRUCT_ALREADY_DEFINED 
							+ ErrorHandler.AI_SEE_DECLARATION + sourceFilePath + ":(57)"));	
				}
				else if(count == 15){
					assertTrue(line == 88); 
					assertTrue(msg.equals(ErrorHandler.E_CONST_LITERAL_OVERFLOW));			
				}
				else if(count == 16){
					assertTrue(line == 89); 					
					assertTrue(msg.equals(ErrorHandler.E_EXCESS_INITIALIZERS_IN_STRUCT_INIT));			
				}	
				else if(count == 17){
					assertTrue(line == 90); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE));			
				}
				else if(count == 18){
					assertTrue(line == 90); 
					assertTrue(msg.equals(ErrorHandler.E_EXCESS_INITIALIZERS_IN_STRUCT_INIT));			
				}
				else if(count == 19){
					assertTrue(line == 95); 					
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_A_FLOATING_POINT_TYPE));	
				}
				else if(count == 20){
					assertTrue(line == 98); 					
					assertTrue(msg.equals(ErrorHandler.ERROR + "another_test:" + ErrorHandler.E_STRUCT_NOT_DEFINED ));	
				}
				else if(count == 21){
					assertTrue(line == 98); 
					assertTrue(msg.equals(ErrorHandler.E_EXCESS_INITIALIZERS_IN_STRUCT_INIT));			
				}
				else if(count == 22){
					assertTrue(line == 106); 					
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_CONSTANT));
				}
				else if(count == 23){
					assertTrue(line == 108); 					
					assertTrue(msg.equals(ErrorHandler.ERROR + "heh:" + ErrorHandler.E_NOT_MEMBER_OF_STRUCT_OR_UNION ));			
				}
				else if(count == 24){
					assertTrue(line == 108); 					
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));			
				}
			
				count++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		assertTrue(count == 25);
	}	
}

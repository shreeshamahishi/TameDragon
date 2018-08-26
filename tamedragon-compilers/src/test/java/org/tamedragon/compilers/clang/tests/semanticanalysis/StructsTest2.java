package org.tamedragon.compilers.clang.tests.semanticanalysis;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Properties;

import org.junit.Before;
import org.junit.Ignore;
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

public class StructsTest2 extends TestInitializer {
	
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
		sourceFilePath ="CSrc/Semantic/StructsTest2.c"; 
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
		
		assertTrue(errorHandler.getNumErrors() == 26);
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
					assertTrue(line == 103); 					
					assertTrue(msg.equals(ErrorHandler.ERROR + "==:" + ErrorHandler.E_INVALID_OPERATOR_ON_STRUCT_UNION ));		
				}
				else if(count == 2){
					assertTrue(line == 104); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "a" + ErrorHandler.E_NOT_DEFINED ));				
				}
				else if(count == 3){
					assertTrue(line == 104); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "c1: " + ErrorHandler.E_LEFT_SIDE_NOT_STRUCT_OR_UNION ));					
				}
				else if(count == 4){
					assertTrue(line == 106); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "a" + ErrorHandler.E_NOT_DEFINED ));			
				}
				else if(count == 5){
					assertTrue(line == 106); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "==:" + ErrorHandler.E_INVALID_OPERATOR_ON_STRUCT_UNION ));	
				}
				else if(count == 6){
					assertTrue(line == 107); 					
					assertTrue(msg.equals(ErrorHandler.ERROR + "a" + ErrorHandler.E_NOT_DEFINED ));		
				}
				else if(count == 7){
					assertTrue(line == 107); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "c1: " + ErrorHandler.E_LEFT_SIDE_NOT_STRUCT_OR_UNION ));					
				}
				else if(count == 8){
					assertTrue(line == 111); 					
					assertTrue(msg.equals(ErrorHandler.ERROR + "==:" + ErrorHandler.E_INVALID_OPERATOR_ON_STRUCT_UNION ));	
				}
				else if(count == 9){
					assertTrue(line == 115); 
					assertTrue(msg.equals(ErrorHandler.ERROR + ">=:" + ErrorHandler.E_INVALID_OPERATOR_ON_STRUCT_UNION ));	
				}
				else if(count == 10){
					assertTrue(line == 116); 					
					assertTrue(msg.equals(ErrorHandler.W_CONST_LITERAL_OVERFLOW ));			
				}				
				else if(count == 11){
					assertTrue(line == 119); 					
					assertTrue(msg.equals(ErrorHandler.ERROR + "<=:" + ErrorHandler.E_INVALID_OPERATOR_ON_STRUCT_UNION ));													
				}
				else if(count == 12){
					assertTrue(line == 123); 					
					assertTrue(msg.equals(ErrorHandler.ERROR + "!=:" + ErrorHandler.E_INVALID_OPERATOR_ON_STRUCT_UNION ));			
				}
				else if(count == 13){
					assertTrue(line == 124); 					
					assertTrue(msg.equals(ErrorHandler.W_CONST_LITERAL_OVERFLOW ));
				}
				else if(count == 14){
					assertTrue(line == 129); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "+:" + ErrorHandler.E_INVALID_OPERATOR_ON_STRUCT_UNION ));			
				}
				else if(count == 15){
					assertTrue(line == 133); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "-:" + ErrorHandler.E_INVALID_OPERATOR_ON_STRUCT_UNION ));			
				}
				else if(count == 16){
					assertTrue(line == 134); 
					assertTrue(msg.equals(ErrorHandler.W_CONST_LITERAL_OVERFLOW));			
				}
				else if(count == 17){
					assertTrue(line == 137); 					
					assertTrue(msg.equals(ErrorHandler.ERROR + "*:" + ErrorHandler.E_INVALID_OPERATOR_ON_STRUCT_UNION ));						
				}				
				else if(count == 18){
					assertTrue(line == 138); 
					assertTrue(msg.equals(ErrorHandler.W_CONST_LITERAL_OVERFLOW));			
				}
				else if(count == 19){
					assertTrue(line == 142); 					
					assertTrue(msg.equals(ErrorHandler.ERROR + "+:" + ErrorHandler.E_INVALID_OPERATOR_ON_STRUCT_UNION ));	
				}
				else if(count == 20){
					assertTrue(line == 142); 					
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));	
				}
				else if(count == 21){
					assertTrue(line == 144); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "a" + ErrorHandler.E_NOT_DEFINED ));			
				}
				else if(count == 22){
					assertTrue(line == 145); 					
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));
				}
				else if(count == 23){
					assertTrue(line == 147); 					
					assertTrue(msg.equals(ErrorHandler.ERROR + "a" + ErrorHandler.E_NOT_DEFINED ));			
				}
				else if(count == 24){
					assertTrue(line == 148); 					
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNMENT_INCOMPATIBLE_POINTER_TYPES));			
				}				
				else if(count == 25){
					assertTrue(line == 151); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));			
				}
				else if(count == 26){
					assertTrue(line == 152); 					
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));
				}
				else if(count == 27){
					assertTrue(line == 155); 					
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));	
				}
				else if(count == 28){
					assertTrue(line == 158); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "a" + ErrorHandler.E_NOT_DEFINED ));
				}
				else if(count == 29){
					assertTrue(line == 158); 					
					assertTrue(msg.equals("Error: Argument 3 of foo: Assignment of incompatible types."));
				}
				else if(count == 30){
					assertTrue(line == 162); 					
					assertTrue(msg.equals(ErrorHandler.ERROR + "x: " + ErrorHandler.E_LEFT_SIDE_NOT_STRUCT_OR_UNION ));			
				}
				else if(count == 31){
					assertTrue(line == 162); 					
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_STRUCT_OR_UNION_TO_A_DOUBLE_TYPE));			
				}
			
				count++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		assertTrue(count == 32);
	}
	
	@Test
	public void checkIfAllAreErrors(){
		errorHandler.setAllWarningsAreErrors(true);
		
		System.out.println("AFTER SETTING ALL WARNINGS TO ERRORS");
		semanticAnalyzer.translateAbstractTree(translationUnit);
		errorHandler.displayResult();
		
		assertTrue(errorHandler.getNumErrors() == 31);
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
					assertTrue(line == 103); 					
					assertTrue(msg.equals(ErrorHandler.ERROR + "==:" + ErrorHandler.E_INVALID_OPERATOR_ON_STRUCT_UNION ));		
				}
				else if(count == 2){
					assertTrue(line == 104); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "a" + ErrorHandler.E_NOT_DEFINED ));				
				}
				else if(count == 3){
					assertTrue(line == 104); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "c1: " + ErrorHandler.E_LEFT_SIDE_NOT_STRUCT_OR_UNION ));					
				}
				else if(count == 4){
					assertTrue(line == 106); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "a" + ErrorHandler.E_NOT_DEFINED ));			
				}
				else if(count == 5){
					assertTrue(line == 106); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "==:" + ErrorHandler.E_INVALID_OPERATOR_ON_STRUCT_UNION ));	
				}
				else if(count == 6){
					assertTrue(line == 107); 					
					assertTrue(msg.equals(ErrorHandler.ERROR + "a" + ErrorHandler.E_NOT_DEFINED ));		
				}
				else if(count == 7){
					assertTrue(line == 107); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "c1: " + ErrorHandler.E_LEFT_SIDE_NOT_STRUCT_OR_UNION ));					
				}
				else if(count == 8){
					assertTrue(line == 111); 					
					assertTrue(msg.equals(ErrorHandler.ERROR + "==:" + ErrorHandler.E_INVALID_OPERATOR_ON_STRUCT_UNION ));	
				}
				else if(count == 9){
					assertTrue(line == 115); 
					assertTrue(msg.equals(ErrorHandler.ERROR + ">=:" + ErrorHandler.E_INVALID_OPERATOR_ON_STRUCT_UNION ));	
				}
				else if(count == 10){
					assertTrue(line == 116); 					
					assertTrue(msg.equals(ErrorHandler.E_CONST_LITERAL_OVERFLOW ));			
				}				
				else if(count == 11){
					assertTrue(line == 119); 					
					assertTrue(msg.equals(ErrorHandler.ERROR + "<=:" + ErrorHandler.E_INVALID_OPERATOR_ON_STRUCT_UNION ));													
				}
				else if(count == 12){
					assertTrue(line == 123); 					
					assertTrue(msg.equals(ErrorHandler.ERROR + "!=:" + ErrorHandler.E_INVALID_OPERATOR_ON_STRUCT_UNION ));			
				}
				else if(count == 13){
					assertTrue(line == 124); 					
					assertTrue(msg.equals(ErrorHandler.E_CONST_LITERAL_OVERFLOW ));
				}
				else if(count == 14){
					assertTrue(line == 129); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "+:" + ErrorHandler.E_INVALID_OPERATOR_ON_STRUCT_UNION ));			
				}
				else if(count == 15){
					assertTrue(line == 133); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "-:" + ErrorHandler.E_INVALID_OPERATOR_ON_STRUCT_UNION ));			
				}
				else if(count == 16){
					assertTrue(line == 134); 
					assertTrue(msg.equals(ErrorHandler.E_CONST_LITERAL_OVERFLOW));			
				}
				else if(count == 17){
					assertTrue(line == 137); 					
					assertTrue(msg.equals(ErrorHandler.ERROR + "*:" + ErrorHandler.E_INVALID_OPERATOR_ON_STRUCT_UNION ));						
				}				
				else if(count == 18){
					assertTrue(line == 138); 
					assertTrue(msg.equals(ErrorHandler.E_CONST_LITERAL_OVERFLOW));			
				}
				else if(count == 19){
					assertTrue(line == 142); 					
					assertTrue(msg.equals(ErrorHandler.ERROR + "+:" + ErrorHandler.E_INVALID_OPERATOR_ON_STRUCT_UNION ));	
				}
				else if(count == 20){
					assertTrue(line == 142); 					
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));	
				}
				else if(count == 21){
					assertTrue(line == 144); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "a" + ErrorHandler.E_NOT_DEFINED ));			
				}
				else if(count == 22){
					assertTrue(line == 145); 					
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));
				}
				else if(count == 23){
					assertTrue(line == 147); 					
					assertTrue(msg.equals(ErrorHandler.ERROR + "a" + ErrorHandler.E_NOT_DEFINED ));			
				}
				else if(count == 24){
					assertTrue(line == 148); 					
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_INCOMPATIBLE_POINTER_TYPES));			
				}				
				else if(count == 25){
					assertTrue(line == 151); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));			
				}
				else if(count == 26){
					assertTrue(line == 152); 					
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));
				}
				else if(count == 27){
					assertTrue(line == 155); 					
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES));	
				}
				else if(count == 28){
					assertTrue(line == 158); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "a" + ErrorHandler.E_NOT_DEFINED ));
				}
				else if(count == 29){
					assertTrue(line == 158); 					
					assertTrue(msg.equals("Error: Argument 3 of foo: Assignment of incompatible types."));
				}
				else if(count == 30){
					assertTrue(line == 162); 					
					assertTrue(msg.equals(ErrorHandler.ERROR + "x: " + ErrorHandler.E_LEFT_SIDE_NOT_STRUCT_OR_UNION ));			
				}
				else if(count == 31){
					assertTrue(line == 162); 					
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_STRUCT_OR_UNION_TO_A_DOUBLE_TYPE));			
				}
			
				count++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		assertTrue(count == 32);
	}	
	
}

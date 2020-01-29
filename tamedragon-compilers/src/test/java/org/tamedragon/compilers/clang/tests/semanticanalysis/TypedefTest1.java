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

public class TypedefTest1 extends TestInitializer {
	
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
		sourceFilePath ="CSrc/Semantic/TypedefTest1.c"; 
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
		assertTrue(errorHandler.getNumWarnings() == 9);
		
		int count = 1;			
		
		ErrorIterator iter = new ErrorIterator(sourceFilePath);
		try{
			while(iter.hasNext()){
				SourceLocationAndMsg srcLcAndMsg =  iter.next();
				SourceLocation srcLc = srcLcAndMsg.getSrcLocation();
				String msg = srcLcAndMsg.getMsg();
				
				int line = srcLc.getLineNum();
				
				if(count == 1){
					assertTrue(line == 25); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "Height" + ErrorHandler.E_TYPE_ALREADY_DEFINED
							+ ErrorHandler.AI_SEE_DECLARATION + sourceFilePath + ":(4)"));		
				}
				else if(count == 2){
					assertTrue(line == 26); 					
					assertTrue(msg.equals(ErrorHandler.ERROR + "Height:" + 
							ErrorHandler.E_TYPE_ALREADY_DEFINED_WITH_CONFLICTING_TYPE
							+ ErrorHandler.AI_SEE_DECLARATION + sourceFilePath + ":(4)"));					
				}
				else if(count == 3){
					assertTrue(line == 28); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "XX:" + ErrorHandler.E_TYPE_CANNOT_HAVE_INITIALIZER ));					
				}
				else if(count == 4){
					assertTrue(line == 47); 
					assertTrue(msg.equals(ErrorHandler.E_INVALID_TYPEDEF_DECLARATION));					
				}
				else if(count == 5){
					assertTrue(line == 50); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "here:" + ErrorHandler.E_TYPE_CANNOT_HAVE_INITIALIZER ));				
				}
				else if(count == 6){
					assertTrue(line == 52); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "noel:" + ErrorHandler.E_TYPE_NOT_DEFINED ));
				}
				else if(count == 7){
					assertTrue(line == 52); 					
					assertTrue(msg.equals(ErrorHandler.E_INVALID_TYPE_DECL));				
				}
				else if(count == 8){
					assertTrue(line == 53); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "almeida:" + ErrorHandler.E_TYPE_NOT_DEFINED ));
				}
				else if(count == 9){
					assertTrue(line == 53); 					
					assertTrue(msg.equals(ErrorHandler.E_INVALID_TYPE_DECL));				
				}
				else if(count == 10){
					assertTrue(line == 69); 					
					assertTrue(msg.equals(ErrorHandler.ERROR + "XYZ" + ErrorHandler.E_NOT_DEFINED ));			
				}				
				else if(count == 11){
					assertTrue(line == 80); 					
					assertTrue(msg.equals(ErrorHandler.W_CONST_LITERAL_OVERFLOW));													
				}
				else if(count == 12){
					assertTrue(line == 112); 					
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNMENT_INCOMPATIBLE_POINTER_TYPES));			
				}
				else if(count == 13){
					assertTrue(line == 115); 					
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNMENT_INCOMPATIBLE_POINTER_TYPES));	
				}
				else if(count == 14){
					assertTrue(line == 121); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNMENT_INCOMPATIBLE_POINTER_TYPES));	
				}
				else if(count == 15){
					assertTrue(line == 126); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNMENT_INCOMPATIBLE_POINTER_TYPES));	
				}
				else if(count == 16){
					assertTrue(line == 131); 
					assertTrue(msg.equals(ErrorHandler.W_DOUBLE_TO_INT_NARROWING));			
				}
				else if(count == 17){
					assertTrue(line == 135); 					
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_CONSTANT));			
				}	
				else if(count == 18){
					assertTrue(line == 142); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNMENT_INCOMPATIBLE_POINTER_TYPES));				
				}
				else if(count == 19){
					assertTrue(line == 143); 					
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNMENT_INCOMPATIBLE_POINTER_TYPES));		
				}	
				else if(count == 20){
					assertTrue(line == 145); 					
					assertTrue(msg.equals(ErrorHandler.W_CONST_LITERAL_OVERFLOW));		
				}
				
				count++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		assertTrue(count == 21);
	}

	@Test
	public void checkIfAllAreErrors(){
		errorHandler.setAllWarningsAreErrors(true);
		
		System.out.println("AFTER SETTING ALL WARNINGS TO ERRORS");
		semanticAnalyzer.translateAbstractTree(translationUnit);
		errorHandler.displayResult();
		
		assertTrue(errorHandler.getNumErrors() == 20);
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
					assertTrue(line == 25); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "Height" + ErrorHandler.E_TYPE_ALREADY_DEFINED
							+ ErrorHandler.AI_SEE_DECLARATION + sourceFilePath + ":(4)"));		
				}
				else if(count == 2){
					assertTrue(line == 26); 					
					assertTrue(msg.equals(ErrorHandler.ERROR + "Height:" + 
							ErrorHandler.E_TYPE_ALREADY_DEFINED_WITH_CONFLICTING_TYPE
							+ ErrorHandler.AI_SEE_DECLARATION + sourceFilePath + ":(4)"));					
				}
				else if(count == 3){
					assertTrue(line == 28); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "XX:" + ErrorHandler.E_TYPE_CANNOT_HAVE_INITIALIZER ));					
				}
				else if(count == 4){
					assertTrue(line == 47); 
					assertTrue(msg.equals(ErrorHandler.E_INVALID_TYPEDEF_DECLARATION));					
				}
				else if(count == 5){
					assertTrue(line == 50); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "here:" + ErrorHandler.E_TYPE_CANNOT_HAVE_INITIALIZER ));				
				}
				else if(count == 6){
					assertTrue(line == 52); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "noel:" + ErrorHandler.E_TYPE_NOT_DEFINED ));
				}
				else if(count == 7){
					assertTrue(line == 52); 					
					assertTrue(msg.equals(ErrorHandler.E_INVALID_TYPE_DECL));				
				}
				else if(count == 8){
					assertTrue(line == 53); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "almeida:" + ErrorHandler.E_TYPE_NOT_DEFINED ));
				}
				else if(count == 9){
					assertTrue(line == 53); 					
					assertTrue(msg.equals(ErrorHandler.E_INVALID_TYPE_DECL));				
				}
				else if(count == 10){
					assertTrue(line == 69); 					
					assertTrue(msg.equals(ErrorHandler.ERROR + "XYZ" + ErrorHandler.E_NOT_DEFINED ));			
				}				
				else if(count == 11){
					assertTrue(line == 80); 					
					assertTrue(msg.equals(ErrorHandler.E_CONST_LITERAL_OVERFLOW));													
				}
				else if(count == 12){
					assertTrue(line == 112); 					
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_INCOMPATIBLE_POINTER_TYPES));			
				}
				else if(count == 13){
					assertTrue(line == 115); 					
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_INCOMPATIBLE_POINTER_TYPES));	
				}
				else if(count == 14){
					assertTrue(line == 121); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_INCOMPATIBLE_POINTER_TYPES));	
				}
				else if(count == 15){
					assertTrue(line == 126); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_INCOMPATIBLE_POINTER_TYPES));	
				}
				else if(count == 16){
					assertTrue(line == 131); 
					assertTrue(msg.equals(ErrorHandler.E_DOUBLE_TO_INT_NARROWING));			
				}
				else if(count == 17){
					assertTrue(line == 135); 					
					assertTrue(msg.equals(ErrorHandler.E_LEFT_SIDE_IS_CONSTANT));			
				}	
				else if(count == 18){
					assertTrue(line == 142); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_INCOMPATIBLE_POINTER_TYPES));				
				}
				else if(count == 19){
					assertTrue(line == 143); 					
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_INCOMPATIBLE_POINTER_TYPES));		
				}	
				else if(count == 20){
					assertTrue(line == 145); 					
					assertTrue(msg.equals(ErrorHandler.E_CONST_LITERAL_OVERFLOW));		
				}
				
				count++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		assertTrue(count == 21);
	}
}

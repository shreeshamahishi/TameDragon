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

public class PointersTest5  extends TestInitializer {
	
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
		sourceFilePath ="CSrc/Semantic/PointersTest5.c"; 
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
		
		assertTrue(errorHandler.getNumErrors() == 13);
		assertTrue(errorHandler.getNumWarnings() == 11);
		
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
					assertTrue(msg.equals(ErrorHandler.E_CANNOT_INCREMENT_A_CONSTANT));
				}
				else if(count == 2){
					assertTrue(line == 18); 
					
					assertTrue(msg.equals(ErrorHandler.ERROR + "dt: " +
							ErrorHandler.E_MISSING_SUBSCRIPT_IN_ARRAY_DECL.substring(ErrorHandler.ERROR.length(), 
							ErrorHandler.E_MISSING_SUBSCRIPT_IN_ARRAY_DECL.length())
							));
				}
				else if(count == 3){
					assertTrue(line == 40); 
					assertTrue(msg.equals(ErrorHandler.W_CONST_LITERAL_OVERFLOW));
				}
				else if(count == 4){
					assertTrue(line == 42); 
					assertTrue(msg.equals(ErrorHandler.W_CONST_LITERAL_OVERFLOW));
				}
				else if(count == 5){
					assertTrue(line == 42); 
					assertTrue(msg.equals(ErrorHandler.W_CONST_LITERAL_OVERFLOW));
				}
				else if(count == 6){
					assertTrue(line == 43); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_A_FLOATING_POINT_TYPE));
				}
				else if(count == 7){
					assertTrue(line == 47); 
					assertTrue(msg.equals(ErrorHandler.E_EXCESS_INITIALIZERS_IN_ARRAY_INIT));
				}
				else if(count == 8){
					assertTrue(line == 48); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "c5: " +
							ErrorHandler.E_INVALID_INITIALIZATION.substring(ErrorHandler.ERROR.length(), 
							ErrorHandler.E_INVALID_INITIALIZATION.length())
							));					
				}
				else if(count == 9){
					assertTrue(line == 50); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "iarr: " +
							ErrorHandler.E_INVALID_INITIALIZATION.substring(ErrorHandler.ERROR.length(), 
							ErrorHandler.E_INVALID_INITIALIZATION.length())
							));
				}
				else if(count == 10){
					assertTrue(line == 62); 
					String shouldBe = ErrorHandler.WARNING + "Jan" + ErrorHandler.W_STRING_ARRAY_BOUNDS_OVERFLOW;
					assertTrue(msg.equals(shouldBe));
				}
				else if(count == 11){
					assertTrue(line == 62); 
					String shouldBe = ErrorHandler.WARNING + "February" + ErrorHandler.W_STRING_ARRAY_BOUNDS_OVERFLOW;
					assertTrue(msg.equals(shouldBe));
				}
				else if(count == 12){
					assertTrue(line == 66); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNING_AN_INTEGER_TYPE_TO_A_POINTER));
				}
				else if(count == 13){
					assertTrue(line == 66); 
					assertTrue(msg.equals(ErrorHandler.E_EXCESS_INITIALIZERS_IN_ARRAY_INIT));
				}
				else if(count == 14){
					assertTrue(line == 68); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNING_AN_INTEGER_TYPE_TO_A_POINTER));
				}
				else if(count == 15){
					assertTrue(line == 68); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNING_AN_INTEGER_TYPE_TO_A_POINTER));
				}				
				else if(count == 16){
					assertTrue(line == 68); 
					assertTrue(msg.equals(ErrorHandler.E_EXCESS_INITIALIZERS_IN_ARRAY_INIT));
				}
				else if(count == 17){
					assertTrue(line == 69); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNING_AN_INTEGER_TYPE_TO_A_POINTER));
				}
				else if(count == 18){
					assertTrue(line == 69); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNING_AN_INTEGER_TYPE_TO_A_POINTER));
				}				
				else if(count == 19){
					assertTrue(line == 69); 
					assertTrue(msg.equals(ErrorHandler.E_EXCESS_INITIALIZERS_IN_ARRAY_INIT));
				}
				else if(count == 20){
					assertTrue(line == 72); 
					assertTrue(msg.equals(ErrorHandler.E_EXCESS_INITIALIZERS_IN_ARRAY_INIT));
				}
				else if(count == 21){
					assertTrue(line == 75); 
					assertTrue(msg.equals(ErrorHandler.E_EXCESS_INITIALIZERS_IN_ARRAY_INIT));
				}
				else if(count == 22){
					assertTrue(line == 79); 
					assertTrue(msg.equals(ErrorHandler.E_EXCESS_INITIALIZERS_IN_ARRAY_INIT));
				}
				else if(count == 23){
					assertTrue(line == 85); 
					assertTrue(msg.equals(ErrorHandler.E_INCORRECT_FORMAL_PARAMETER));
				}
				else if(count == 24){
					assertTrue(line == 92); 
					assertTrue(msg.equals(ErrorHandler.W_ASSIGNING_AN_INTEGER_TYPE_TO_A_POINTER));
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
				
				//if(count == 1){
				//	assertTrue(line == 3); 
				//	assertTrue(msg.equals(ErrorHandler.E_ASSIGNMENT_TARGET_IS_ARRAY_ADDRESS));
				//}
				//else 
					if(count == 1){
					assertTrue(line == 4); 
					assertTrue(msg.equals(ErrorHandler.E_CANNOT_INCREMENT_A_CONSTANT));
				}
				else if(count == 2){
					assertTrue(line == 18); 
					
					assertTrue(msg.equals(ErrorHandler.ERROR + "dt: " +
							ErrorHandler.E_MISSING_SUBSCRIPT_IN_ARRAY_DECL.substring(ErrorHandler.ERROR.length(), 
							ErrorHandler.E_MISSING_SUBSCRIPT_IN_ARRAY_DECL.length())
							));
					
					
				}
				else if(count == 3){
					assertTrue(line == 40); 
					assertTrue(msg.equals(ErrorHandler.E_CONST_LITERAL_OVERFLOW));
				}
				else if(count == 4){
					assertTrue(line == 42); 
					assertTrue(msg.equals(ErrorHandler.E_CONST_LITERAL_OVERFLOW));
				}
				else if(count == 5){
					assertTrue(line == 42); 
					assertTrue(msg.equals(ErrorHandler.E_CONST_LITERAL_OVERFLOW));
				}
				else if(count == 6){
					assertTrue(line == 43); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_POINTER_TO_A_FLOATING_POINT_TYPE));
				}
				else if(count == 7){
					assertTrue(line == 47); 
					assertTrue(msg.equals(ErrorHandler.E_EXCESS_INITIALIZERS_IN_ARRAY_INIT));
				}
				else if(count == 8){
					assertTrue(line == 48); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "c5: " +
							ErrorHandler.E_INVALID_INITIALIZATION.substring(ErrorHandler.ERROR.length(), 
							ErrorHandler.E_INVALID_INITIALIZATION.length())
							));					
				}
				else if(count == 9){
					assertTrue(line == 50); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "iarr: " +
							ErrorHandler.E_INVALID_INITIALIZATION.substring(ErrorHandler.ERROR.length(), 
							ErrorHandler.E_INVALID_INITIALIZATION.length())
							));
				}
				else if(count == 10){
					assertTrue(line == 62); 
					String shouldBe = ErrorHandler.ERROR + "Jan" + ErrorHandler.E_STRING_ARRAY_BOUNDS_OVERFLOW;
					assertTrue(msg.equals(shouldBe));
				}
				else if(count == 11){
					assertTrue(line == 62); 
					String shouldBe = ErrorHandler.ERROR + "February" + ErrorHandler.E_STRING_ARRAY_BOUNDS_OVERFLOW;
					assertTrue(msg.equals(shouldBe));
				}
				else if(count == 12){
					assertTrue(line == 66); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_AN_INTEGER_TYPE_TO_A_POINTER));
				}
				else if(count == 13){
					assertTrue(line == 66); 
					assertTrue(msg.equals(ErrorHandler.E_EXCESS_INITIALIZERS_IN_ARRAY_INIT));
				}
				else if(count == 14){
					assertTrue(line == 68); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_AN_INTEGER_TYPE_TO_A_POINTER));
				}
				else if(count == 15){
					assertTrue(line == 68); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_AN_INTEGER_TYPE_TO_A_POINTER));
				}				
				else if(count == 16){
					assertTrue(line == 68); 
					assertTrue(msg.equals(ErrorHandler.E_EXCESS_INITIALIZERS_IN_ARRAY_INIT));
				}
				else if(count == 17){
					assertTrue(line == 69); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_AN_INTEGER_TYPE_TO_A_POINTER));
				}
				else if(count == 18){
					assertTrue(line == 69); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_AN_INTEGER_TYPE_TO_A_POINTER));
				}				
				else if(count == 19){
					assertTrue(line == 69); 
					assertTrue(msg.equals(ErrorHandler.E_EXCESS_INITIALIZERS_IN_ARRAY_INIT));
				}
				else if(count == 20){
					assertTrue(line == 72); 
					assertTrue(msg.equals(ErrorHandler.E_EXCESS_INITIALIZERS_IN_ARRAY_INIT));
				}
				else if(count == 21){
					assertTrue(line == 75); 
					assertTrue(msg.equals(ErrorHandler.E_EXCESS_INITIALIZERS_IN_ARRAY_INIT));
				}
				else if(count == 22){
					assertTrue(line == 79); 
					assertTrue(msg.equals(ErrorHandler.E_EXCESS_INITIALIZERS_IN_ARRAY_INIT));
				}
				else if(count == 23){
					assertTrue(line == 85); 
					assertTrue(msg.equals(ErrorHandler.E_INCORRECT_FORMAL_PARAMETER));
				}
				else if(count == 24){
					assertTrue(line == 92); 
					assertTrue(msg.equals(ErrorHandler.E_ASSIGNING_AN_INTEGER_TYPE_TO_A_POINTER));
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

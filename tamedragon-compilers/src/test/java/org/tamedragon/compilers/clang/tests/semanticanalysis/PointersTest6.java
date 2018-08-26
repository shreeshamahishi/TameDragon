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

public class PointersTest6  extends TestInitializer {
	
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
		sourceFilePath ="CSrc/Semantic/PointersTest6.c"; 
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
		
		assertTrue(errorHandler.getNumErrors() == 15);
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
					assertTrue(line == 16); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "d: " +
							ErrorHandler.E_MISSING_SUBSCRIPT_IN_ARRAY_DECL.substring(ErrorHandler.ERROR.length(), 
							ErrorHandler.E_MISSING_SUBSCRIPT_IN_ARRAY_DECL.length())
							));
				}
				else if(count == 2){
					assertTrue(line == 21); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "d: " +
							ErrorHandler.E_MISSING_SUBSCRIPT_IN_ARRAY_DECL.substring(ErrorHandler.ERROR.length(), 
							ErrorHandler.E_MISSING_SUBSCRIPT_IN_ARRAY_DECL.length())
							));
				}
				else if(count == 3){
					assertTrue(line == 21); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "d: " +
							ErrorHandler.E_MISSING_SUBSCRIPT_IN_ARRAY_DECL.substring(ErrorHandler.ERROR.length(), 
							ErrorHandler.E_MISSING_SUBSCRIPT_IN_ARRAY_DECL.length())
							));
				}
				else if(count == 4){
					assertTrue(line == 35); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "c: " +
							ErrorHandler.E_ARRAY_SIZE_NOT_SPECIFIED.substring(ErrorHandler.ERROR.length(), 
							ErrorHandler.E_ARRAY_SIZE_NOT_SPECIFIED.length())
							));
				}
				else if(count == 5){
					assertTrue(line == 41); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "mm " +
							ErrorHandler.E_ARRAY_SIZE_SPECIFIER_IS_INVALID.substring(ErrorHandler.ERROR.length(), 
							ErrorHandler.E_ARRAY_SIZE_SPECIFIER_IS_INVALID.length())
							));
				}
				else if(count == 6){
					assertTrue(line == 41); 
					assertTrue(msg.equals(ErrorHandler.E_EXCESS_INITIALIZERS_IN_ARRAY_INIT));
				}
				else if(count == 7){
					assertTrue(line == 42); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "mmq " +
							ErrorHandler.E_ARRAY_SIZE_SPECIFIER_IS_INVALID.substring(ErrorHandler.ERROR.length(), 
							ErrorHandler.E_ARRAY_SIZE_SPECIFIER_IS_INVALID.length())
							));
				}
				else if(count == 8){
					assertTrue(line == 42); 
					assertTrue(msg.equals(ErrorHandler.E_EXCESS_INITIALIZERS_IN_ARRAY_INIT));			
				}
				else if(count == 9){
					assertTrue(line == 45); 
					assertTrue(msg.equals(ErrorHandler.E_EXCESS_INITIALIZERS_IN_ARRAY_INIT));
				}
				else if(count == 10){
					assertTrue(line == 58); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "yb: " +
							ErrorHandler.E_MISSING_SUBSCRIPT_IN_ARRAY_DECL.substring(ErrorHandler.ERROR.length(), 
									ErrorHandler.E_MISSING_SUBSCRIPT_IN_ARRAY_DECL.length())
									));
				}				
				else if(count == 11){
					assertTrue(line == 59); 
					assertTrue(msg.equals(ErrorHandler.E_EXCESS_INITIALIZERS_IN_ARRAY_INIT));
				}
				else if(count == 12){
					assertTrue(line == 62); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "zc: " +
							ErrorHandler.E_MISSING_SUBSCRIPT_IN_ARRAY_DECL.substring(ErrorHandler.ERROR.length(), 
									ErrorHandler.E_MISSING_SUBSCRIPT_IN_ARRAY_DECL.length())
									));
				}	
				else if(count == 13){
					assertTrue(line == 62); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "zc: " +
							ErrorHandler.E_MISSING_SUBSCRIPT_IN_ARRAY_DECL.substring(ErrorHandler.ERROR.length(), 
									ErrorHandler.E_MISSING_SUBSCRIPT_IN_ARRAY_DECL.length())
									));
				}
				else if(count == 14){
					assertTrue(line == 63); 
					assertTrue(msg.equals(ErrorHandler.E_EXCESS_INITIALIZERS_IN_ARRAY_INIT));
				}	
				else if(count == 15){
					assertTrue(line == 69); 
					assertTrue(msg.equals(ErrorHandler.E_ARRAY_SUBSCRIPT_NOT_INTEGER));	
				}
	
				count++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		assertTrue(count == 16);
	}
	
	@Test
	public void checkIfAllAreErrors(){
		errorHandler.setAllWarningsAreErrors(true);
		
		System.out.println("AFTER SETTING ALL WARNINGS TO ERRORS");
		semanticAnalyzer.translateAbstractTree(translationUnit);
		errorHandler.displayResult();
		
		assertTrue(errorHandler.getNumErrors() == 15);
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
					assertTrue(line == 16); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "d: " +
							ErrorHandler.E_MISSING_SUBSCRIPT_IN_ARRAY_DECL.substring(ErrorHandler.ERROR.length(), 
							ErrorHandler.E_MISSING_SUBSCRIPT_IN_ARRAY_DECL.length())
							));
				}
				else if(count == 2){
					assertTrue(line == 21); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "d: " +
							ErrorHandler.E_MISSING_SUBSCRIPT_IN_ARRAY_DECL.substring(ErrorHandler.ERROR.length(), 
							ErrorHandler.E_MISSING_SUBSCRIPT_IN_ARRAY_DECL.length())
							));
				}
				else if(count == 3){
					assertTrue(line == 21); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "d: " +
							ErrorHandler.E_MISSING_SUBSCRIPT_IN_ARRAY_DECL.substring(ErrorHandler.ERROR.length(), 
							ErrorHandler.E_MISSING_SUBSCRIPT_IN_ARRAY_DECL.length())
							));
				}
				else if(count == 4){
					assertTrue(line == 35); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "c: " +
							ErrorHandler.E_ARRAY_SIZE_NOT_SPECIFIED.substring(ErrorHandler.ERROR.length(), 
							ErrorHandler.E_ARRAY_SIZE_NOT_SPECIFIED.length())
							));
				}
				else if(count == 5){
					assertTrue(line == 41); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "mm " +
							ErrorHandler.E_ARRAY_SIZE_SPECIFIER_IS_INVALID.substring(ErrorHandler.ERROR.length(), 
							ErrorHandler.E_ARRAY_SIZE_SPECIFIER_IS_INVALID.length())
							));
				}
				else if(count == 6){
					assertTrue(line == 41); 
					assertTrue(msg.equals(ErrorHandler.E_EXCESS_INITIALIZERS_IN_ARRAY_INIT));
				}
				else if(count == 7){
					assertTrue(line == 42); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "mmq " +
							ErrorHandler.E_ARRAY_SIZE_SPECIFIER_IS_INVALID.substring(ErrorHandler.ERROR.length(), 
							ErrorHandler.E_ARRAY_SIZE_SPECIFIER_IS_INVALID.length())
							));
				}
				else if(count == 8){
					assertTrue(line == 42); 
					assertTrue(msg.equals(ErrorHandler.E_EXCESS_INITIALIZERS_IN_ARRAY_INIT));			
				}
				else if(count == 9){
					assertTrue(line == 45); 
					assertTrue(msg.equals(ErrorHandler.E_EXCESS_INITIALIZERS_IN_ARRAY_INIT));
				}
				else if(count == 10){
					assertTrue(line == 58); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "yb: " +
							ErrorHandler.E_MISSING_SUBSCRIPT_IN_ARRAY_DECL.substring(ErrorHandler.ERROR.length(), 
									ErrorHandler.E_MISSING_SUBSCRIPT_IN_ARRAY_DECL.length())
									));
				}				
				else if(count == 11){
					assertTrue(line == 59); 
					assertTrue(msg.equals(ErrorHandler.E_EXCESS_INITIALIZERS_IN_ARRAY_INIT));
				}
				else if(count == 12){
					assertTrue(line == 62); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "zc: " +
							ErrorHandler.E_MISSING_SUBSCRIPT_IN_ARRAY_DECL.substring(ErrorHandler.ERROR.length(), 
									ErrorHandler.E_MISSING_SUBSCRIPT_IN_ARRAY_DECL.length())
									));
				}	
				else if(count == 13){
					assertTrue(line == 62); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "zc: " +
							ErrorHandler.E_MISSING_SUBSCRIPT_IN_ARRAY_DECL.substring(ErrorHandler.ERROR.length(), 
									ErrorHandler.E_MISSING_SUBSCRIPT_IN_ARRAY_DECL.length())
									));
				}
				else if(count == 14){
					assertTrue(line == 63); 
					assertTrue(msg.equals(ErrorHandler.E_EXCESS_INITIALIZERS_IN_ARRAY_INIT));
				}	
				else if(count == 15){
					assertTrue(line == 69); 
					assertTrue(msg.equals(ErrorHandler.E_ARRAY_SUBSCRIPT_NOT_INTEGER));	
				}				
				count++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		assertTrue(count == 16);
	}
}

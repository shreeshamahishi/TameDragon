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
import org.tamedragon.compilers.clang.semantics.Symbol;
import org.tamedragon.compilers.clang.semantics.VariableOrFunctionEntry;
import org.tamedragon.compilers.clang.tests.TestInitializer;

public class ComplicatedDeclarationsTest1  extends TestInitializer {

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
		sourceFilePath ="CSrc/Semantic/ComplicatedDeclarations.c"; 
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

		Environments environments = Environments.getInstance();
		VariableOrFunctionEntry varOrFuncEntry = 
			(VariableOrFunctionEntry) environments.getInstanceVariableTable().get(Symbol.symbol("m"));
		assertNotNull(varOrFuncEntry);
		String desc = varOrFuncEntry.getDescription();
		assertTrue(desc.equals("m : int"));

		varOrFuncEntry = (VariableOrFunctionEntry) environments.getInstanceVariableTable().get(Symbol.symbol("argv"));
		assertNotNull(varOrFuncEntry);
		desc = varOrFuncEntry.getDescription();
		assertTrue(desc.equals("argv : pointer to pointer to char"));

		varOrFuncEntry = (VariableOrFunctionEntry) environments.getInstanceVariableTable().get(Symbol.symbol("daytab"));
		assertNotNull(varOrFuncEntry);
		desc = varOrFuncEntry.getDescription();
		assertTrue(desc.equals("daytab : pointer to array[13] of int"));

		varOrFuncEntry = (VariableOrFunctionEntry) environments.getInstanceVariableTable().get(Symbol.symbol("daytab1"));
		assertNotNull(varOrFuncEntry);
		desc = varOrFuncEntry.getDescription();
		assertTrue(desc.equals("daytab1 : array[13] of pointer to int"));

		varOrFuncEntry = (VariableOrFunctionEntry) environments.getInstanceVariableTable().get(Symbol.symbol("comp"));
		assertNotNull(varOrFuncEntry);
		desc = varOrFuncEntry.getDescription();
		assertTrue(desc.equals("comp : function returning pointer to void"));

		varOrFuncEntry = (VariableOrFunctionEntry) environments.getInstanceVariableTable().get(Symbol.symbol("comp1"));
		assertNotNull(varOrFuncEntry);
		desc = varOrFuncEntry.getDescription();
		assertTrue(desc.equals("comp1 : pointer to function returning void"));

		varOrFuncEntry = (VariableOrFunctionEntry) environments.getInstanceVariableTable().get(Symbol.symbol("x"));
		assertNotNull(varOrFuncEntry);
		desc = varOrFuncEntry.getDescription();
		assertTrue(desc.equals("x : function returning pointer to array[] of pointer to function returning char"));

		varOrFuncEntry = (VariableOrFunctionEntry) environments.getInstanceVariableTable().get(Symbol.symbol("a"));
		assertNotNull(varOrFuncEntry);
		desc = varOrFuncEntry.getDescription();
		assertTrue(desc.equals("a : array[3] of pointer to function returning pointer to array[5] of char"));

		assertTrue(errorHandler.getNumErrors() == 4);
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
					assertTrue(line == 10); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "c" + ErrorHandler.E_ARRAY_OF_FUNCTIONS));
				}
				else if(count == 2){
					assertTrue(line == 11); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "mm" + ErrorHandler.E_ARRAY_OF_FUNCTIONS));				
				}
				else if(count == 3){
					assertTrue(line == 12); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "qn" + ErrorHandler.E_DECL_FUNC_RETURNING_FUNC));				
				}
				else if(count == 4){
					assertTrue(line == 13); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "dd" + ErrorHandler.E_DECL_FUNC_RETURNING_ARRAY));					
				}
				count++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}

		assertTrue(count == 5);

	}
}
package org.tamedragon.compilers.clang.tests.preprocessor;
//package com.compilervision.compilers.clang.tests.preprocessor;
//
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertTrue;
//
//import java.io.InputStream;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import org.tamedragon.compilers.clang.CLangUtils;
//import org.tamedragon.compilers.clang.CompilerSettings;
//import org.tamedragon.compilers.clang.ErrorHandler;
//import org.tamedragon.compilers.clang.ErrorIterator;
//import org.tamedragon.compilers.clang.SourceLocation;
//import org.tamedragon.compilers.clang.SourceLocationAndMsg;
//import org.tamedragon.compilers.clang.abssyntree.TranslationUnit;
//import org.tamedragon.compilers.clang.preprocessor.DefinitionsMap;
//import org.tamedragon.compilers.clang.preprocessor.PreprocessorMain;
//import org.tamedragon.compilers.clang.preprocessor.PreprocessorSegments;
//import org.tamedragon.compilers.clang.semantics.CTranslationUnit;
//import org.tamedragon.compilers.clang.semantics.Environments;
//import org.tamedragon.compilers.clang.semantics.SemanticAnalyzer;
//import org.tamedragon.compilers.clang.tests.TestInitializer;
//
//public class DefineTest2 extends TestInitializer {
//
//	private String sourceFilePath;
//	private CompilerSettings compilerSettings;
//
//	@Before
//	public void setUp(){
//
//		CLangUtils.populateSettings();
//		compilerSettings = CompilerSettings.getInstance();
//		compilerSettings.setInstanceProjectPath("CSrc/Preprocessor");
//
//		sourceFilePath ="CSrc/Preprocessor/DefineTest2.c"; 
//	}
//
//	@Test
//	public void test1(){
//		// Start with a clean slate
//		ErrorHandler errorHandler = ErrorHandler.getInstance();
//		errorHandler.reset();
//		DefinitionsMap defsMap = DefinitionsMap.getInstance();
//		defsMap.clearDefinitions();
//		Environments environments = Environments.getInstance();
//		environments.reset();
//
//		String targetDesc = compilerSettings.getInstanceTarget();
//
//		// Run the preprocessor on the sourceFile			
//		PreprocessorMain ppMain = new PreprocessorMain(sourceFilePath);			
//		InputStream sourceFileInputStream = ppMain.process(true); 
//
//		// Verify that the defintions map is correctly populated
//		Set<String> keys = defsMap.getKeys();
//		assertTrue(keys.size() == 4);
//		assertTrue(defsMap.getDefinition("v1").equals("v5"));
//		assertTrue(defsMap.getDefinition("v2").equals("v5"));
//		assertTrue(defsMap.getDefinition("v3").equals("v5"));
//		assertTrue(defsMap.getDefinition("v4").equals("v5"));
//
//		// Translate to abstract syntax tree
//		TranslationUnit translationUnit = CLangUtils.getTranslationByLLParsing(sourceFileInputStream);
//
//		// Pass through semantic analyzer and translate to assembly tree
//		SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer(sourceFilePath, targetDesc); 	    
//		List<CTranslationUnit> translationUnits =  semanticAnalyzer.translateAbstractTree(translationUnit); 
//		errorHandler.displayResult();
//
//		int count = 1;			
//
//		ErrorIterator iter = new ErrorIterator(sourceFilePath);
//		try{
//			while(iter.hasNext()){
//				SourceLocationAndMsg srcLcAndMsg =  iter.next();
//				SourceLocation srcLc = srcLcAndMsg.getSrcLocation();
//				String msg = srcLcAndMsg.getMsg();
//
//				int line = srcLc.getLineNum();
//
//				if(count == 1){
//					assertTrue(line == 14); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "v5" + ErrorHandler.E_NOT_DEFINED));
//				}
//				else if(count == 2){
//					assertTrue(line == 15); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "v5" + ErrorHandler.E_NOT_DEFINED));
//				}
//				else if(count == 3){
//					assertTrue(line == 16); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "v5" + ErrorHandler.E_NOT_DEFINED));	
//				}
//				else if(count == 17){
//					assertTrue(line == 41); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "v5" + ErrorHandler.E_NOT_DEFINED));
//				}
//				else if(count == 5){
//					assertTrue(line == 18); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "v5" + ErrorHandler.E_NOT_DEFINED));
//				}
//				
//				count++;
//			}
//		}
//		catch(Exception e){
//			e.printStackTrace();
//		}
//
//		assertTrue(count == 6);
//	}
//}
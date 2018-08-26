package org.tamedragon.compilers.clang.tests.preprocessor;
//package com.compilervision.compilers.clang.tests.preprocessor;
//
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertTrue;
//
//import java.io.InputStream;
//import java.util.List;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import org.tamedragon.compilers.clang.CLangUtils;
//import org.tamedragon.compilers.clang.CMain;
//import org.tamedragon.compilers.clang.ErrorHandler;
//import org.tamedragon.compilers.clang.ErrorIterator;
//import org.tamedragon.compilers.clang.SourceLocation;
//import org.tamedragon.compilers.clang.SourceLocationAndMsg;
//import org.tamedragon.compilers.clang.abssyntree.TranslationUnit;
//import org.tamedragon.compilers.clang.preprocessor.PreprocessorMain;
//import org.tamedragon.compilers.clang.preprocessor.PreprocessorSegments;
//import org.tamedragon.compilers.clang.preprocessor.PreprocessorUnit;
//import org.tamedragon.compilers.clang.semantics.Environments;
//import org.tamedragon.compilers.clang.semantics.SemanticAnalyzer;
//import org.tamedragon.compilers.clang.tests.TestInitializer;
//
//public class ConditionalTest8 extends TestInitializer {
//
//	private String sourceFilePath;
//	private PreprocessorSegments preprocessorSegments;	
//	
//	@Before
//	public void setUp(){	
//		Environments environments = Environments.getInstance();
//		environments.reset();
//		
//		sourceFilePath ="CSrc/Preprocessor/ConditionalTest8.c"; 
//		
//		PreprocessorMain ppMain = new PreprocessorMain(sourceFilePath);
//		InputStream is = ppMain.replaceTrigraphSequencesAndSpliceLines(sourceFilePath);
//		
//		preprocessorSegments = ppMain.getPreprocessorTranslationByLLParsing(is);
//		assertNotNull(preprocessorSegments);			
//	}
//	
//	@Test
//	public void test1() {      		
//		
//		Environments environments = Environments.getInstance();
//		environments.reset();	
//		
//		List<PreprocessorUnit> units = preprocessorSegments.getUnits();
//		assertTrue(units.size() == 5);
//		assertTrue(units.get(0).getPreprocessorUnitType() == PreprocessorUnit.PROGRAM_CODE);
//		assertTrue(units.get(1).getPreprocessorUnitType() == PreprocessorUnit.DEFINITION);
//		assertTrue(units.get(2).getPreprocessorUnitType() == PreprocessorUnit.PROGRAM_CODE);
//		assertTrue(units.get(3).getPreprocessorUnitType() == PreprocessorUnit.CONDITIONAL);
//		assertTrue(units.get(4).getPreprocessorUnitType() == PreprocessorUnit.PROGRAM_CODE);
//		
//		StringBuffer sb = preprocessorSegments.process(sourceFilePath);		
//		String codeText = sb.toString();
//		
//		/*System.out.println("************ FINAL PROCESSED CODE: ");
//		System.out.println(codeText);
//		System.out.println("************ END FINAL PROCESSED CODE: ");
//		*/
//		assertTrue(!codeText.contains("#define")); // should not have any more directives
//		assertTrue(!codeText.contains("#ifdef"));  // should not have any more directives
//		assertTrue(!codeText.contains("#endif"));  // should not have any more directives
//		assertTrue(!codeText.contains("#else"));   // should not have any more directives
//		assertTrue(!codeText.contains("#elif"));   // should not have any more directives
//		
//		int numLinesInCode = getNumLinesInFile(sourceFilePath);
//		int numLinesInProcessedCode = getNumLinesInString(codeText);
//		
//		System.out.println(codeText);
//		
//		assertTrue(numLinesInCode == numLinesInProcessedCode);
//		
//	}	
//	
//	@Test
//	public void checkFullTranslation(){
//		// This code should not compile
//		String targetDesc = settings.getProperty("target");
//		ErrorHandler errorHandler = ErrorHandler.getInstance();
//		errorHandler.reset();
//
//		PreprocessorMain ppMain = new PreprocessorMain(sourceFilePath);			
//		InputStream sourceFileInputStream = ppMain.process(true); 
//		TranslationUnit translationUnit = CLangUtils.getTranslationByLLParsing(sourceFileInputStream);
//		SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer(sourceFilePath, targetDesc); 	    
//		semanticAnalyzer.translateAbstractTree(translationUnit);    	  
//		
//		assertTrue(errorHandler.getNumErrors() == 3);
//				
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
//					assertTrue(line == 18); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "i" + ErrorHandler.E_NOT_DEFINED));
//				}
//				if(count == 2){
//					assertTrue(line == 20); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m" + ErrorHandler.E_NOT_DEFINED));
//				}
//				if(count == 3){
//					assertTrue(line == 20); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "k" + ErrorHandler.E_NOT_DEFINED));
//				}
//				
//				
//				count++;
//			}
//		}
//		catch(Exception e){
//			e.printStackTrace();
//		}
//		
//		assertTrue(count == 4);		
//	}
//}

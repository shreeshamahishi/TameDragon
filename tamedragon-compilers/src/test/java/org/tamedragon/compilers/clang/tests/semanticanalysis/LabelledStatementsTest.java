package org.tamedragon.compilers.clang.tests.semanticanalysis;
//package com.compilervision.compilers.clang.tests.semanticanalysis;
//
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertTrue;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import org.tamedragon.common.llvmir.types.CompilationContext;
//import org.tamedragon.compilers.clang.CLangUtils;
//import org.tamedragon.compilers.clang.ErrorHandler;
//import org.tamedragon.compilers.clang.ErrorIterator;
//import org.tamedragon.compilers.clang.SourceLocation;
//import org.tamedragon.compilers.clang.SourceLocationAndMsg;
//import org.tamedragon.compilers.clang.abssyntree.TranslationUnit;
//import org.tamedragon.compilers.clang.semantics.Environments;
//import org.tamedragon.compilers.clang.semantics.Semantic;
//import org.tamedragon.compilers.clang.tests.TestInitializer;
//
//public class LabelledStatementsTest  extends TestInitializer {
//	
//	private Semantic semanticAnalyzer;
//	private String sourceFilePath;
//	private ErrorHandler errorHandler;
//	private TranslationUnit translationUnit;
//	
//	@Before
//	public void setUp(){
//		Environments environments = Environments.getInstance();
//		environments.reset();
//		sourceFilePath ="CSrc/Semantic/LabelledStatementsTest.c"; 
//		errorHandler = ErrorHandler.getInstance();
//		errorHandler.reset();
//		assertTrue(errorHandler.getNumErrors() == 0);
//		assertTrue(errorHandler.getNumWarnings() == 0);
//		
//		CompilationContext compilationContext = new CompilationContext();
//		semanticAnalyzer = new Semantic(sourceFilePath, compilationContext);
//		translationUnit = CLangUtils.getTranslationByLLParsing(sourceFilePath);
//		assertNotNull(translationUnit);
//	}
//	
//	@Test
//	public void test1() {      
//		semanticAnalyzer.translateAbstractTree(translationUnit);
//		errorHandler.displayResult();
//		
//		assertTrue(errorHandler.getNumErrors() == 6);
//		assertTrue(errorHandler.getNumWarnings() == 0);
//		
//		int count = 1;		
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
//					assertTrue(line == 12); 
//					assertTrue(msg.equals(ErrorHandler.E_CONTINUE_IN_WRONG_LOCATION));
//				}
//				else if(count == 2){
//					assertTrue(line == 14); 					
//					assertTrue(msg.equals(ErrorHandler.ERROR + "xyz:" + ErrorHandler.E_LABEL_REDEFINED));					
//				}
//				else if(count == 3){
//					assertTrue(line == 27); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "xyz1:" + ErrorHandler.E_NO_LABEL));					
//				}
//				else if(count == 4){
//					assertTrue(line == 29); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "xyz:" + ErrorHandler.E_LABEL_REDEFINED));
//				}
//				else if(count == 5){
//					assertTrue(line == 32); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "unknown:" + ErrorHandler.E_NO_LABEL));
//				}
//				else if(count == 6){
//					assertTrue(line == 37); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m1" + ErrorHandler.E_NOT_DEFINED));
//				}
//				
//				count++;
//			}
//		}
//		catch(Exception e){
//			e.printStackTrace();
//		}
//		
//		assertTrue(count == 7);
//		
//	}
//	
//	//	Now set all warnings are errors
//	@Test
//	public void checkIfAllAreErrors(){
//		System.out.println("AFTER SETTING ALL WARNINGS TO ERRORS");
//		errorHandler.setAllWarningsAreErrors(true);
//		
//		semanticAnalyzer.translateAbstractTree(translationUnit);
//		errorHandler.displayResult();
//		assertTrue(errorHandler.getNumErrors() == 6);
//		assertTrue(errorHandler.getNumWarnings() == 0);
//		
//		int count = 1;			
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
//					assertTrue(line == 12); 
//					assertTrue(msg.equals(ErrorHandler.E_CONTINUE_IN_WRONG_LOCATION));
//				}
//				else if(count == 2){
//					assertTrue(line == 14); 					
//					assertTrue(msg.equals(ErrorHandler.ERROR + "xyz:" + ErrorHandler.E_LABEL_REDEFINED));					
//				}
//				else if(count == 3){
//					assertTrue(line == 27); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "xyz1:" + ErrorHandler.E_NO_LABEL));					
//				}
//				else if(count == 4){
//					assertTrue(line == 29); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "xyz:" + ErrorHandler.E_LABEL_REDEFINED));
//				}
//				else if(count == 5){
//					assertTrue(line == 32); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "unknown:" + ErrorHandler.E_NO_LABEL));
//				}
//				else if(count == 6){
//					assertTrue(line == 37); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m1" + ErrorHandler.E_NOT_DEFINED));
//				}
//				
//				count++;
//			}
//		}
//		catch(Exception e){
//			e.printStackTrace();
//		}
//		
//		assertTrue(count == 7);
//	}
//}

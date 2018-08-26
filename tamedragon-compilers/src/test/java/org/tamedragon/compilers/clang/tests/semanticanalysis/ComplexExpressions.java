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
////@Ignore
//public class ComplexExpressions extends TestInitializer {
//	
//	private Semantic semanticAnalyzer;
//	private String sourceFilePath;
//	private ErrorHandler errorHandler;
//	private TranslationUnit translationUnit;
//	
//	
//	@Before
//	public void setUp(){
//		Environments environments = Environments.getInstance();
//		environments.reset();
//		sourceFilePath ="CSrc/Semantic/ComplexExpressions.c"; 
//		errorHandler = ErrorHandler.getInstance();
//		errorHandler.reset();
//		assertTrue(errorHandler.getNumErrors() == 0);
//		assertTrue(errorHandler.getNumWarnings() == 0);
//		
//		CompilationContext compilationContext = new CompilationContext();
//
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
//		assertTrue(errorHandler.getNumErrors() == 45);
//		assertTrue(errorHandler.getNumWarnings() == 0);
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
//					assertTrue(line == 7); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m1" + ErrorHandler.E_NOT_DEFINED));	
//				}
//				else if(count == 2){
//					assertTrue(line == 7); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m2" + ErrorHandler.E_NOT_DEFINED));					
//				}
//				else if(count == 3){
//					assertTrue(line == 7); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m3" + ErrorHandler.E_NOT_DEFINED));					
//				}
//				else if(count == 4){
//					assertTrue(line == 7); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m4" + ErrorHandler.E_NOT_DEFINED));				
//				}
//				else if(count == 5){
//					assertTrue(line == 8); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m1" + ErrorHandler.E_NOT_DEFINED));		
//				}
//				else if(count == 6){
//					assertTrue(line == 8); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m2" + ErrorHandler.E_NOT_DEFINED));					
//				}
//				else if(count == 7){
//					assertTrue(line == 8); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m3" + ErrorHandler.E_NOT_DEFINED));					
//				}
//				
//				else if(count == 8){
//					assertTrue(line == 9); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m1" + ErrorHandler.E_NOT_DEFINED));	
//				}
//				else if(count == 9){
//					assertTrue(line == 9); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m2" + ErrorHandler.E_NOT_DEFINED));				
//				}
//				else if(count == 10){
//					assertTrue(line == 10); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m1" + ErrorHandler.E_NOT_DEFINED));						
//				}
//				else if(count == 11){
//					assertTrue(line == 10); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m2" + ErrorHandler.E_NOT_DEFINED));				
//				}
//				else if(count == 12){
//					assertTrue(line == 10); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m3" + ErrorHandler.E_NOT_DEFINED));		
//				}
//				else if(count == 13){
//					assertTrue(line == 12); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m1" + ErrorHandler.E_NOT_DEFINED));							
//				}
//				else if(count == 14){
//					assertTrue(line == 12); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m2" + ErrorHandler.E_NOT_DEFINED));							
//				}				
//				else if(count == 15){
//					assertTrue(line == 12); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m3" + ErrorHandler.E_NOT_DEFINED));		
//				}
//				else if(count == 16){
//					assertTrue(line == 13); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m1" + ErrorHandler.E_NOT_DEFINED));			
//				}
//				else if(count == 17){
//					assertTrue(line == 13); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m2" + ErrorHandler.E_NOT_DEFINED));							
//				}
//				else if(count == 18){
//					assertTrue(line == 13); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m3" + ErrorHandler.E_NOT_DEFINED));						
//				}
//				else if(count == 19){
//					assertTrue(line == 14); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m1" + ErrorHandler.E_NOT_DEFINED));	
//				}
//				else if(count == 20){
//					assertTrue(line == 14); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m2" + ErrorHandler.E_NOT_DEFINED));					
//				}
//				else if(count == 21){
//					assertTrue(line == 14); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m3" + ErrorHandler.E_NOT_DEFINED));					
//				}				
//				else if(count == 22){
//					assertTrue(line == 15); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m1" + ErrorHandler.E_NOT_DEFINED));	
//				}
//				else if(count == 23){
//					assertTrue(line == 15); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m2" + ErrorHandler.E_NOT_DEFINED));						
//				}
//				else if(count == 24){
//					assertTrue(line == 15); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m3" + ErrorHandler.E_NOT_DEFINED));					
//				}
//				else if(count == 25){
//					assertTrue(line == 16); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m1" + ErrorHandler.E_NOT_DEFINED));						
//				}
//				else if(count == 26){
//					assertTrue(line == 16); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m2" + ErrorHandler.E_NOT_DEFINED));	
//				}
//				else if(count == 27){
//					assertTrue(line == 16); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m3" + ErrorHandler.E_NOT_DEFINED));					
//				}
//				else if(count == 28){
//					assertTrue(line == 17); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m1" + ErrorHandler.E_NOT_DEFINED));			
//				}
//				else if(count == 29){
//					assertTrue(line == 17); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m2" + ErrorHandler.E_NOT_DEFINED));							
//				}
//				else if(count == 30){
//					assertTrue(line == 17); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m3" + ErrorHandler.E_NOT_DEFINED));							
//				}
//				else if(count == 31){ 
//					assertTrue(line == 17); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m4" + ErrorHandler.E_NOT_DEFINED));						
//				}
//				else if(count == 32){
//					assertTrue(line == 17); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m5" + ErrorHandler.E_NOT_DEFINED));							
//				}
//				else if(count == 33){
//					assertTrue(line == 18); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m1" + ErrorHandler.E_NOT_DEFINED));							
//				}
//				else if(count == 34){
//					assertTrue(line == 18); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m2" + ErrorHandler.E_NOT_DEFINED));				
//				}
//				else if(count == 35){
//					assertTrue(line == 18); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m3" + ErrorHandler.E_NOT_DEFINED));				
//				}
//				else if(count == 36){
//					assertTrue(line == 18); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m4" + ErrorHandler.E_NOT_DEFINED));						
//				}
//				else if(count == 37){
//					assertTrue(line == 18); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m5" + ErrorHandler.E_NOT_DEFINED));				
//				}
//				else if(count == 38){
//					assertTrue(line == 19); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m1" + ErrorHandler.E_NOT_DEFINED));							
//				}
//				else if(count == 39){
//					assertTrue(line == 19); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m2" + ErrorHandler.E_NOT_DEFINED));					
//				}
//				else if(count == 40){
//					assertTrue(line == 19); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m3" + ErrorHandler.E_NOT_DEFINED));						
//				}
//				else if(count == 41){
//					assertTrue(line == 20); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m1" + ErrorHandler.E_NOT_DEFINED));					
//				}
//				else if(count == 42){
//					assertTrue(line == 20); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m2" + ErrorHandler.E_NOT_DEFINED));							
//				}
//				else if(count == 43){
//					assertTrue(line == 20); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m3" + ErrorHandler.E_NOT_DEFINED));							
//				}
//				else if(count == 44){
//					assertTrue(line == 20); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m4" + ErrorHandler.E_NOT_DEFINED));						
//				}
//				else if(count == 45){
//					assertTrue(line == 21); 
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
//		assertTrue(count == 46);
//	}
//	
//	@Test
//	public void checkIfAllAreErrors(){
//		errorHandler.setAllWarningsAreErrors(true);
//		
//		System.out.println("AFTER SETTING ALL WARNINGS TO ERRORS");
//		semanticAnalyzer.translateAbstractTree(translationUnit);
//		errorHandler.displayResult();
//		
//		assertTrue(errorHandler.getNumErrors() == 45);
//		assertTrue(errorHandler.getNumWarnings() == 0);
//		
//	int count = 1;			
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
//					assertTrue(line == 7); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m1" + ErrorHandler.E_NOT_DEFINED));	
//				}
//				else if(count == 2){
//					assertTrue(line == 7); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m2" + ErrorHandler.E_NOT_DEFINED));					
//				}
//				else if(count == 3){
//					assertTrue(line == 7); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m3" + ErrorHandler.E_NOT_DEFINED));					
//				}
//				else if(count == 4){
//					assertTrue(line == 7); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m4" + ErrorHandler.E_NOT_DEFINED));				
//				}
//				else if(count == 5){
//					assertTrue(line == 8); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m1" + ErrorHandler.E_NOT_DEFINED));		
//				}
//				else if(count == 6){
//					assertTrue(line == 8); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m2" + ErrorHandler.E_NOT_DEFINED));					
//				}
//				else if(count == 7){
//					assertTrue(line == 8); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m3" + ErrorHandler.E_NOT_DEFINED));					
//				}
//				
//				else if(count == 8){
//					assertTrue(line == 9); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m1" + ErrorHandler.E_NOT_DEFINED));	
//				}
//				else if(count == 9){
//					assertTrue(line == 9); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m2" + ErrorHandler.E_NOT_DEFINED));				
//				}
//				else if(count == 10){
//					assertTrue(line == 10); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m1" + ErrorHandler.E_NOT_DEFINED));						
//				}
//				else if(count == 11){
//					assertTrue(line == 10); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m2" + ErrorHandler.E_NOT_DEFINED));				
//				}
//				else if(count == 12){
//					assertTrue(line == 10); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m3" + ErrorHandler.E_NOT_DEFINED));		
//				}
//				else if(count == 13){
//					assertTrue(line == 12); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m1" + ErrorHandler.E_NOT_DEFINED));							
//				}
//				else if(count == 14){
//					assertTrue(line == 12); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m2" + ErrorHandler.E_NOT_DEFINED));							
//				}				
//				else if(count == 15){
//					assertTrue(line == 12); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m3" + ErrorHandler.E_NOT_DEFINED));		
//				}
//				else if(count == 16){
//					assertTrue(line == 13); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m1" + ErrorHandler.E_NOT_DEFINED));			
//				}
//				else if(count == 17){
//					assertTrue(line == 13); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m2" + ErrorHandler.E_NOT_DEFINED));							
//				}
//				else if(count == 18){
//					assertTrue(line == 13); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m3" + ErrorHandler.E_NOT_DEFINED));						
//				}
//				else if(count == 19){
//					assertTrue(line == 14); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m1" + ErrorHandler.E_NOT_DEFINED));	
//				}
//				else if(count == 20){
//					assertTrue(line == 14); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m2" + ErrorHandler.E_NOT_DEFINED));					
//				}
//				else if(count == 21){
//					assertTrue(line == 14); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m3" + ErrorHandler.E_NOT_DEFINED));					
//				}				
//				else if(count == 22){
//					assertTrue(line == 15); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m1" + ErrorHandler.E_NOT_DEFINED));	
//				}
//				else if(count == 23){
//					assertTrue(line == 15); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m2" + ErrorHandler.E_NOT_DEFINED));						
//				}
//				else if(count == 24){
//					assertTrue(line == 15); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m3" + ErrorHandler.E_NOT_DEFINED));					
//				}
//				else if(count == 25){
//					assertTrue(line == 16); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m1" + ErrorHandler.E_NOT_DEFINED));						
//				}
//				else if(count == 26){
//					assertTrue(line == 16); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m2" + ErrorHandler.E_NOT_DEFINED));	
//				}
//				else if(count == 27){
//					assertTrue(line == 16); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m3" + ErrorHandler.E_NOT_DEFINED));					
//				}
//				else if(count == 28){
//					assertTrue(line == 17); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m1" + ErrorHandler.E_NOT_DEFINED));			
//				}
//				else if(count == 29){
//					assertTrue(line == 17); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m2" + ErrorHandler.E_NOT_DEFINED));							
//				}
//				else if(count == 30){
//					assertTrue(line == 17); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m3" + ErrorHandler.E_NOT_DEFINED));							
//				}
//				else if(count == 31){ 
//					assertTrue(line == 17); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m4" + ErrorHandler.E_NOT_DEFINED));						
//				}
//				else if(count == 32){
//					assertTrue(line == 17); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m5" + ErrorHandler.E_NOT_DEFINED));							
//				}
//				else if(count == 33){
//					assertTrue(line == 18); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m1" + ErrorHandler.E_NOT_DEFINED));							
//				}
//				else if(count == 34){
//					assertTrue(line == 18); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m2" + ErrorHandler.E_NOT_DEFINED));				
//				}
//				else if(count == 35){
//					assertTrue(line == 18); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m3" + ErrorHandler.E_NOT_DEFINED));				
//				}
//				else if(count == 36){
//					assertTrue(line == 18); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m4" + ErrorHandler.E_NOT_DEFINED));						
//				}
//				else if(count == 37){
//					assertTrue(line == 18); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m5" + ErrorHandler.E_NOT_DEFINED));				
//				}
//				else if(count == 38){
//					assertTrue(line == 19); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m1" + ErrorHandler.E_NOT_DEFINED));							
//				}
//				else if(count == 39){
//					assertTrue(line == 19); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m2" + ErrorHandler.E_NOT_DEFINED));					
//				}
//				else if(count == 40){
//					assertTrue(line == 19); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m3" + ErrorHandler.E_NOT_DEFINED));						
//				}
//				else if(count == 41){
//					assertTrue(line == 20); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m1" + ErrorHandler.E_NOT_DEFINED));					
//				}
//				else if(count == 42){
//					assertTrue(line == 20); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m2" + ErrorHandler.E_NOT_DEFINED));							
//				}
//				else if(count == 43){
//					assertTrue(line == 20); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m3" + ErrorHandler.E_NOT_DEFINED));							
//				}
//				else if(count == 44){
//					assertTrue(line == 20); 
//					assertTrue(msg.equals(ErrorHandler.ERROR + "m4" + ErrorHandler.E_NOT_DEFINED));						
//				}
//				else if(count == 45){
//					assertTrue(line == 21); 
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
//		assertTrue(count == 46);
//	}	
//}

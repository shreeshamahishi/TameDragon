package org.tamedragon.compilers.clang.tests.irtranslate;
//package com.compilervision.compilers.clang.tests.irtranslate;
//
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertTrue;
//
//import java.util.List;
//import org.junit.Before;
//import org.junit.Test;
//
//import org.tamedragon.assemblyabstractions.constructs.AssemSeq;
//import org.tamedragon.assemblyabstractions.constructs.AssemType;
//import org.tamedragon.compilers.clang.CLangUtils;
//import org.tamedragon.compilers.clang.ErrorHandler;
//
//import org.tamedragon.compilers.clang.abssyntree.TranslationUnit;
//import org.tamedragon.compilers.clang.semantics.CFunctionDef;
//import org.tamedragon.compilers.clang.semantics.CTranslationUnit;
//import org.tamedragon.compilers.clang.semantics.SemanticAnalyzer;
//import org.tamedragon.compilers.clang.tests.TestInitializer;
//import org.tamedragon.compilers.clang.Debug;
//
//public class SimpleFunctionTest0 extends TestInitializer {
//	
//	private SemanticAnalyzer semanticAnalyzer;
//	private String sourceFilePath;
//	private ErrorHandler errorHandler;
//	private TranslationUnit translationUnit;
//	
//	@Before
//	public void setUp(){
//		sourceFilePath ="CSrc/TranslationToTreeIR/SimpleFunctionTest0.c"; 
//		errorHandler = ErrorHandler.getInstance();
//		errorHandler.reset();
//		assertTrue(errorHandler.getNumErrors() == 0);
//		assertTrue(errorHandler.getNumWarnings() == 0);
//	
//		semanticAnalyzer = new SemanticAnalyzer(sourceFilePath, "mips");
//		translationUnit = CLangUtils.getTranslationByLLParsing(sourceFilePath);
//		assertNotNull(translationUnit);
//	}
//	
//	@Test
//	public void test1() {      
//		List<CTranslationUnit> translationUnits = 
//			semanticAnalyzer.translateAbstractTree(translationUnit);
//		
//		assertTrue(errorHandler.getNumErrors() == 0);
//		assertTrue(translationUnits != null);
//		assertTrue(translationUnits.size() == 1);
//		
//		CTranslationUnit ctransUnit = translationUnits.get(0);
//		assertTrue(ctransUnit != null);
//		assertTrue(ctransUnit instanceof CFunctionDef);
//		
//		CFunctionDef cFuncDef = (CFunctionDef) ctransUnit;
//		AssemType funcTree = cFuncDef.getExternalDeclTree();
//	
//		assertTrue(funcTree != null);
//		
//		Debug debug = new Debug();
//		debug.printAssem(funcTree);
//		
//	}	
//}

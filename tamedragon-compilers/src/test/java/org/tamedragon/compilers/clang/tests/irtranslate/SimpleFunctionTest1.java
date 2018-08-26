package org.tamedragon.compilers.clang.tests.irtranslate;
//package com.compilervision.compilers.clang.tests.irtranslate;
//
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertTrue;
//
//import java.io.InputStream;
//import java.util.HashMap;
//import java.util.List;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import org.tamedragon.assemblyabstractions.constructs.AssemType;
//import org.tamedragon.compilers.canon.Linearizer;
//import org.tamedragon.compilers.clang.CLangUtils;
//import org.tamedragon.compilers.clang.CompilerSettings;
//import org.tamedragon.compilers.clang.Debug;
//import org.tamedragon.compilers.clang.ErrorHandler;
//import org.tamedragon.compilers.clang.abssyntree.TranslationUnit;
//import org.tamedragon.compilers.clang.preprocessor.DefinitionsMap;
//import org.tamedragon.compilers.clang.preprocessor.IncludesPreProcessed;
//import org.tamedragon.compilers.clang.preprocessor.PreprocessorMain;
//import org.tamedragon.compilers.clang.semantics.CExternalDecl;
//import org.tamedragon.compilers.clang.semantics.CFunctionDef;
//import org.tamedragon.compilers.clang.semantics.CTranslationUnit;
//import org.tamedragon.compilers.clang.semantics.Environments;
//import org.tamedragon.compilers.clang.semantics.SemanticAnalyzer;
//import org.tamedragon.compilers.clang.tests.TestInitializer;
//
//public class SimpleFunctionTest1 extends TestInitializer {
//
//	private SemanticAnalyzer semanticAnalyzer;
//	private String sourceFilePath;
//	private ErrorHandler errorHandler;
//	DefinitionsMap defsMap;
//	Environments environments;
//
//	private CompilerSettings compilerSettings;
//
//	List<CTranslationUnit> translationUnits;
//
//	@Before
//	public void setUp(){
//
//		CLangUtils.populateSettings();
//		compilerSettings = CompilerSettings.getInstance();
//		compilerSettings.setInstanceIncludePath("resources/include");
//
//		sourceFilePath ="CSrc/TranslationToTreeIR/SimpleFunctionTest1.c"; 
//
//		// Start with a clean slate
//		errorHandler = ErrorHandler.getInstance();
//		errorHandler.reset();
//		defsMap = DefinitionsMap.getInstance();
//		defsMap.clearDefinitions();
//		environments = Environments.getInstance();
//		environments.reset();		
//		HashMap<String, HashMap<String, List<InputStream>>> includesPreProcessed = IncludesPreProcessed.getInstance();
//		includesPreProcessed.clear();
//
//		// Pre-process
//		PreprocessorMain ppMain = new PreprocessorMain(sourceFilePath);
//		InputStream is = ppMain.process(true);
//
//		// Parse
//		TranslationUnit ast = CLangUtils.getTranslationByLLParsing(is);
//		assertNotNull(ast);
//
//		// Semantic analysis
//		semanticAnalyzer = new SemanticAnalyzer(sourceFilePath, "mips");
//
//		translationUnits = 
//			semanticAnalyzer.translateAbstractTree(ast);
//		errorHandler.displayResult();
//		assertTrue(errorHandler.getNumErrors() == 0);				
//	}
//
//	@Test
//	public void test1() { 
//		assertTrue(translationUnits != null);
//		assertTrue(translationUnits.size() == 3);
//
//		CTranslationUnit ctransUnit = translationUnits.get(0);
//		assertTrue(ctransUnit != null);		
//		assertTrue(ctransUnit instanceof CExternalDecl);
//		printExternalDeclTree(ctransUnit);
//
//		ctransUnit = translationUnits.get(1);
//		assertTrue(ctransUnit != null);		
//		assertTrue(ctransUnit instanceof CFunctionDef);
//		printFunctionDefTree(ctransUnit);	
//		
//		ctransUnit = translationUnits.get(2);
//		assertTrue(ctransUnit != null);		
//		assertTrue(ctransUnit instanceof CFunctionDef);
//		printFunctionDefTree(ctransUnit);		
//	}	
//
//	private void printFunctionDefTree(CTranslationUnit ctransUnit){
//		System.out.println("FUNCTION:");
//		CFunctionDef cFuncDef = (CFunctionDef) ctransUnit;
//		AssemType funcTree = cFuncDef.getExternalDeclTree();
//		assertTrue(funcTree != null);
//		Debug debug = new Debug();
//
//		System.out.println("ASSEMBLY TREE:");
//		debug.printAssem(funcTree);
//
//		System.out.println("CANONICAL TREE:");
//		Linearizer linearizer = new Linearizer(cFuncDef.getName());
//		linearizer.linearize(funcTree);
//		debug.print(linearizer.getCanonized());
//
//		System.out.println("BASIC BLOCKS FROM TREE:");
//		linearizer.generateBasicBlocks();
//		debug.print(linearizer.getBasicBlocks());
//
//		System.out.println("TRACE SCHEDULE FOR TREE:");
//		linearizer.generateTrace();	
//		debug.print(linearizer.getTrace());
//
//	}
//	
//	private void printExternalDeclTree(CTranslationUnit ctransUnit){
//		System.out.println("EXTERNAL DECLARATION:");
//		CExternalDecl cExternalDecl = (CExternalDecl) ctransUnit;
//		AssemType declTree = cExternalDecl.getExternalDeclTree();
//		assertTrue(declTree != null);
//		Debug debug = new Debug();
//
//		System.out.println("ASSEMBLY TREE:");
//		debug.printAssem(declTree);
//
//		System.out.println("CANONICAL TREE:");
//		Linearizer linearizer = new Linearizer(declTree.getDescription());
//		linearizer.linearize(declTree);
//		debug.print(linearizer.getCanonized());
//
//		System.out.println("BASIC BLOCKS FROM TREE:");
//		linearizer.generateBasicBlocks();
//		debug.print(linearizer.getBasicBlocks());
//
//		System.out.println("TRACE SCHEDULE FOR TREE:");
//		linearizer.generateTrace();	
//		debug.print(linearizer.getTrace());
//
//	}
//}

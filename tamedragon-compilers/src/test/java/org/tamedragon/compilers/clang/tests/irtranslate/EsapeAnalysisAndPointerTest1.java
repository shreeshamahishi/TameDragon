package org.tamedragon.compilers.clang.tests.irtranslate;
//package com.compilervision.compilers.clang.tests.irtranslate;
//
//import static org.junit.Assert.assertTrue;
//
//import java.io.InputStream;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Vector;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import org.tamedragon.assemblyabstractions.constructs.AssemBinOpExp;
//import org.tamedragon.assemblyabstractions.constructs.AssemConst;
//import org.tamedragon.assemblyabstractions.constructs.AssemExp;
//import org.tamedragon.assemblyabstractions.constructs.AssemMove;
//import org.tamedragon.assemblyabstractions.constructs.AssemSeq;
//import org.tamedragon.assemblyabstractions.constructs.AssemStm;
//import org.tamedragon.assemblyabstractions.constructs.AssemStmList;
//import org.tamedragon.assemblyabstractions.constructs.AssemType;
//import org.tamedragon.assemblyabstractions.constructs.AssemUnaryOpExp;
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
//public class EsapeAnalysisAndPointerTest1 extends TestInitializer {
//	
//	private SemanticAnalyzer semanticAnalyzer;
//	private String sourceFilePath;
//	private ErrorHandler errorHandler ;
//	DefinitionsMap defsMap;
//	Environments environments;
//	private CompilerSettings compilerSettings;
//	
//	@Before
//	public void setUp(){
//				
//		CLangUtils.populateSettings();
//		compilerSettings = CompilerSettings.getInstance();
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
//		sourceFilePath ="CSrc/TranslationToTreeIR/EscapeAnalysisAndPointerTest1.c"; 
//		errorHandler = ErrorHandler.getInstance();
//		errorHandler.reset();
//		assertTrue(errorHandler.getNumErrors() == 0);
//		assertTrue(errorHandler.getNumWarnings() == 0);		
//	}
//	
//	@Test
//	public void test1() {   
//		
//		Debug debug = new Debug();
//		
//		String targetDesc = compilerSettings.getInstanceTarget();
//		
//		PreprocessorMain ppMain = new PreprocessorMain(sourceFilePath);			
//		InputStream sourceFileInputStream = ppMain.process(true); 
//		
//		// Translate to abstract syntax tree
//		TranslationUnit translationUnit = CLangUtils.getTranslationByLLParsing(sourceFileInputStream);
//
//		// Pass through semantic analyzer and translate to assembly tree
//		semanticAnalyzer = new SemanticAnalyzer(sourceFilePath, targetDesc); 	 
//		errorHandler.displayResult();
//		
//		List<CTranslationUnit> translationUnits = 
//			semanticAnalyzer.translateAbstractTree(translationUnit);
//		assertTrue(translationUnits != null);		
//		int numErrors = errorHandler.getNumErrors();		
//		assertTrue(numErrors == 0);
//		int numTus = translationUnits.size();
//		assertTrue(numTus == 2);
//						
//		// Function main	
//		CTranslationUnit ctransUnit = translationUnits.get(1);
//		assertTrue(ctransUnit != null);
//		assertTrue(ctransUnit instanceof CFunctionDef);
//		
//		CFunctionDef cFunctionDef = (CFunctionDef) ctransUnit;
//		AssemType funcTree1 = cFunctionDef.getExternalDeclTree();	
//		assertTrue(funcTree1 != null);
//		assertTrue(funcTree1 instanceof AssemSeq);		
//		System.out.println("RAW TREE for main:");
//		debug.printAssem(funcTree1);
//		
//		Linearizer linearizer = new Linearizer(cFunctionDef.getName());
//		linearizer.linearize(funcTree1);
//		AssemStmList assemStmList = linearizer.getCanonized();
//		assertTrue(assemStmList != null);
//		//System.out.println("AFTER CANONIZING WITH LINEARIZER (swap):");
//		//debug.print(assemStmList);
//		
//		// FUNCTION MAIN	
//		ctransUnit = translationUnits.get(1);
//		assertTrue(ctransUnit != null);
//		assertTrue(ctransUnit instanceof CFunctionDef);
//		
//		cFunctionDef = (CFunctionDef) ctransUnit;
//		AssemType funcTree2 = cFunctionDef.getExternalDeclTree();	
//		assertTrue(funcTree2 != null);
//		assertTrue(funcTree1 instanceof AssemSeq);		
//		System.out.println("RAW TREE for main:");
//		debug.printAssem(funcTree2);
//		
//		linearizer = new Linearizer(cFunctionDef.getName());
//		linearizer.linearize(funcTree2);
//		assemStmList = linearizer.getCanonized();
//		assertTrue(assemStmList != null);
//		System.out.println("AFTER CANONIZING WITH LINEARIZER (main):");
//		debug.print(assemStmList);
//	
//		//Vector<AssemStm> allStms = getFlatListOfStatements(assemStmList);			
//	}	
//}

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
//public class BinaryOpsTest1 extends TestInitializer {
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
//		sourceFilePath ="CSrc/TranslationToTreeIR/BinaryOpsTest1.c"; 
//		errorHandler = ErrorHandler.getInstance();
//		errorHandler.reset();
//		assertTrue(errorHandler.getNumErrors() == 0);
//		assertTrue(errorHandler.getNumWarnings() == 0);		
//	}
//	
//	@Test
//	public void test1() {      		
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
//		assertTrue(numTus == 3);
//				
//		CTranslationUnit ctransUnit = translationUnits.get(0);
//		assertTrue(ctransUnit != null);
//		assertTrue(ctransUnit instanceof CExternalDecl);
//		
//		CExternalDecl cExternalDecl = (CExternalDecl) ctransUnit;
//		AssemType declTree = cExternalDecl.getExternalDeclTree();	
//		assertTrue(declTree != null);
//		
//		// Function do_bin_ops		
//		ctransUnit = translationUnits.get(1);
//		assertTrue(ctransUnit != null);
//		assertTrue(ctransUnit instanceof CFunctionDef);
//		
//		CFunctionDef cFunctionDef = (CFunctionDef) ctransUnit;
//		AssemType funcTree1 = cFunctionDef.getExternalDeclTree();	
//		assertTrue(funcTree1 != null);
//		assertTrue(funcTree1 instanceof AssemSeq);		
//				
//		Linearizer linearizer = new Linearizer(cFunctionDef.getName());
//		linearizer.linearize(funcTree1);
//		AssemStmList assemStmList = linearizer.getCanonized();
//		assertTrue(assemStmList != null);
//	
//		Vector<AssemStm> allStms = getFlatListOfStatements(assemStmList);
//		
//		checkForMoveBinOpStatementsInList(allStms, AssemBinOpExp.PLUS, null, null, 5);
//		checkForMoveBinOpStatementsInList(allStms, AssemBinOpExp.MINUS, null, null, 1);		
//		//checkForMoveBinOpStatementsInList(allStms, AssemBinOpExp.MUL, null, null, 1);
//		//checkForMoveBinOpStatementsInList(allStms, AssemBinOpExp.DIV, null, null, 1);
//		//checkForMoveBinOpStatementsInList(allStms, AssemBinOpExp.MODULO, null, null, 1);
//		checkForMoveBinOpStatementsInList(allStms, AssemBinOpExp.BITWISE_AND, null, null, 1);
//		checkForMoveBinOpStatementsInList(allStms, AssemBinOpExp.BITWISE_OR, null, null, 1);
//		checkForMoveBinOpStatementsInList(allStms, AssemBinOpExp.BITWISE_XOR, null, null, 2);
//		checkForMoveBinOpStatementsInList(allStms, AssemBinOpExp.LSHIFT, null, null, 1);
//		checkForMoveBinOpStatementsInList(allStms, AssemBinOpExp.RSHIFT, null, null, 1);
//		checkForMoveUnaryOpStatementsInList(allStms, AssemUnaryOpExp.ONES_COMPLEMENT, 1);		
//	}	
//	
//	private void checkForMoveUnaryOpStatementsInList(Vector<AssemStm> allStms, int unaryOp, int numOccurencesExpected){
//		int numOccurencesActual = 0;
//		
//		for(AssemStm stm: allStms){
//			if(!(stm instanceof AssemMove))
//				continue;
//			
//			// Move statement; check the left operand to see if it is a bin op
//			AssemMove moveStm = (AssemMove) stm;
//			AssemExp leftExp = moveStm.getSrc();
//			if(!(leftExp instanceof AssemUnaryOpExp))
//				continue;
//			
//			// RHS of the move is a unary op, lets check the operator first
//			AssemUnaryOpExp unaryOpExp = (AssemUnaryOpExp) leftExp;
//			int operatorOfUnaryOp = unaryOpExp.getOperator();
//			if(unaryOp != operatorOfUnaryOp)
//				continue;			
//				numOccurencesActual++;
//			}
//			
//		
//		// Num occurences actual and num occurences passed must be the same
//		assertTrue(numOccurencesActual == numOccurencesExpected);
//	}
//	
//	private void checkForMoveBinOpStatementsInList(Vector<AssemStm> allStms, int binaryOp, Integer left, Integer right, 
//			int numOccurencesExpected){
//		
//		int numOccurencesActual = 0;
//		
//		for(AssemStm stm: allStms){
//			if(!(stm instanceof AssemMove))
//				continue;
//			
//			// Move statement; check the left operand to see if it is a bin op
//			AssemMove moveStm = (AssemMove) stm;
//			AssemExp leftExp = moveStm.getSrc();
//			if(!(leftExp instanceof AssemBinOpExp))
//				continue;
//			
//			// RHS of the move is a binary op, lets check the operator first
//			AssemBinOpExp binOpExp = (AssemBinOpExp) leftExp;
//			int operatorOfBinOp = binOpExp.getOperator();
//			if(binaryOp != operatorOfBinOp)
//				continue;
//			
//			// operator is same, lets check the operands now
//			AssemExp leftOperand = binOpExp.getLeft();
//			AssemExp rightOperand = binOpExp.getRight();
//			if(left != null){ 
//				
//				if(!(leftOperand instanceof AssemConst))  // left operand is not a constant, continue
//					continue;
//				
//				int leftOperandVal = ((AssemConst)leftOperand).getIntValue();
//				if(left.intValue() != leftOperandVal)  // Not expected value, continue
//					continue;				
//				
//				if(right != null){
//					if(!(rightOperand instanceof AssemConst))
//						continue;
//				}	
//				
//				int rightOperandVal = ((AssemConst)rightOperand).getIntValue();
//				if(right.intValue() != rightOperandVal)  // Not expected value, continue
//					continue;	
//				
//				// Both the operator and the operators match
//				numOccurencesActual++;
//			}
//			else{
//				// No left operand to check for, lets check the right one
//				if(right != null){
//					if(!(rightOperand instanceof AssemConst))
//						continue;
//					
//					int rightOperandVal = ((AssemConst)rightOperand).getIntValue();
//					if(right.intValue() != rightOperandVal)  // Not expected value, continue
//						continue;	
//					
//					// Both the operator and the operators match
//					numOccurencesActual++;
//					
//				}	
//				else{
//					// Both left and right are null, operands must be temps
//					numOccurencesActual++;
//				}
//			}
//		}
//		
//		// Num occurences actual and num occurences passed must be the same
//		assertTrue(numOccurencesActual == numOccurencesExpected);
//	}	
//}

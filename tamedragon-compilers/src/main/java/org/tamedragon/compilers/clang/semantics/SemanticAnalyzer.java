package org.tamedragon.compilers.clang.semantics;
//package com.compilervision.compilers.clang.semantics;
//
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Set;
//import java.util.Stack;
//import java.util.Vector;
//
//import org.tamedragon.assemblyabstractions.ActivationFrame;
//import org.tamedragon.assemblyabstractions.ScalarStackElement;
//import org.tamedragon.assemblyabstractions.StackElement;
//import org.tamedragon.assemblyabstractions.constructs.AssemCJump;
//import org.tamedragon.assemblyabstractions.constructs.AssemCallExp;
//import org.tamedragon.assemblyabstractions.constructs.AssemConst;
//import org.tamedragon.assemblyabstractions.constructs.AssemExp;
//import org.tamedragon.assemblyabstractions.constructs.AssemExpList;
//import org.tamedragon.assemblyabstractions.constructs.AssemName;
//import org.tamedragon.assemblyabstractions.constructs.AssemTemp;
//import org.tamedragon.assemblyabstractions.constructs.AssemType;
//import org.tamedragon.assemblyabstractions.constructs.AssemValueProperties;
//import org.tamedragon.common.Label;
//import org.tamedragon.common.TargetDataType;
//import org.tamedragon.common.llvmir.types.AbstractType;
//import org.tamedragon.common.llvmir.types.Temp;
//import org.tamedragon.compilers.clang.ErrorHandler;
//import org.tamedragon.compilers.clang.SourceLocation;
//import org.tamedragon.compilers.clang.abssyntree.AbstractDeclarator;
//import org.tamedragon.compilers.clang.abssyntree.Absyn;
//import org.tamedragon.compilers.clang.abssyntree.AdditiveExpr;
//import org.tamedragon.compilers.clang.abssyntree.AndExpr;
//import org.tamedragon.compilers.clang.abssyntree.ArgumentExpressionList;
//import org.tamedragon.compilers.clang.abssyntree.AssignmentExpr;
//import org.tamedragon.compilers.clang.abssyntree.AssignmentOperator;
//import org.tamedragon.compilers.clang.abssyntree.BinaryOpExpr;
//import org.tamedragon.compilers.clang.abssyntree.CastExpr;
//import org.tamedragon.compilers.clang.abssyntree.CompoundStatement;
//import org.tamedragon.compilers.clang.abssyntree.ConditionalExpr;
//import org.tamedragon.compilers.clang.abssyntree.ConstExpr;
//import org.tamedragon.compilers.clang.abssyntree.Constant;
//import org.tamedragon.compilers.clang.abssyntree.Declaration;
//import org.tamedragon.compilers.clang.abssyntree.DeclarationList;
//import org.tamedragon.compilers.clang.abssyntree.DeclarationSpecifiers;
//import org.tamedragon.compilers.clang.abssyntree.Declarator;
//import org.tamedragon.compilers.clang.abssyntree.DirectDeclarator;
//import org.tamedragon.compilers.clang.abssyntree.EnumList;
//import org.tamedragon.compilers.clang.abssyntree.EnumSpecifier;
//import org.tamedragon.compilers.clang.abssyntree.Enumerator;
//import org.tamedragon.compilers.clang.abssyntree.EqualityExpr;
//import org.tamedragon.compilers.clang.abssyntree.ExclusiveOrExpr;
//import org.tamedragon.compilers.clang.abssyntree.ExprStatement;
//import org.tamedragon.compilers.clang.abssyntree.ExternDeclaration;
//import org.tamedragon.compilers.clang.abssyntree.FunctionDef;
//import org.tamedragon.compilers.clang.abssyntree.IncludeDirective;
//import org.tamedragon.compilers.clang.abssyntree.IncludeStatement;
//import org.tamedragon.compilers.clang.abssyntree.InclusiveOrExpr;
//import org.tamedragon.compilers.clang.abssyntree.InitDeclarator;
//import org.tamedragon.compilers.clang.abssyntree.InitDeclaratorList;
//import org.tamedragon.compilers.clang.abssyntree.Initializer;
//import org.tamedragon.compilers.clang.abssyntree.InitializerList;
//import org.tamedragon.compilers.clang.abssyntree.IterationStatement;
//import org.tamedragon.compilers.clang.abssyntree.JumpStatement;
//import org.tamedragon.compilers.clang.abssyntree.LabeledStatement;
//import org.tamedragon.compilers.clang.abssyntree.LogicalAndExpr;
//import org.tamedragon.compilers.clang.abssyntree.LogicalOrExpr;
//import org.tamedragon.compilers.clang.abssyntree.MultiplicativeExpr;
//import org.tamedragon.compilers.clang.abssyntree.ParamDeclaration;
//import org.tamedragon.compilers.clang.abssyntree.ParamList;
//import org.tamedragon.compilers.clang.abssyntree.ParamTypeList;
//import org.tamedragon.compilers.clang.abssyntree.Pointer;
//import org.tamedragon.compilers.clang.abssyntree.PostfixExpr;
//import org.tamedragon.compilers.clang.abssyntree.PrimaryExpr;
//import org.tamedragon.compilers.clang.abssyntree.RelationalExpr;
//import org.tamedragon.compilers.clang.abssyntree.RootExpr;
//import org.tamedragon.compilers.clang.abssyntree.SelectionStatement;
//import org.tamedragon.compilers.clang.abssyntree.ShiftExpr;
//import org.tamedragon.compilers.clang.abssyntree.SpecifierListType;
//import org.tamedragon.compilers.clang.abssyntree.SpecifierQualifierList;
//import org.tamedragon.compilers.clang.abssyntree.Statement;
//import org.tamedragon.compilers.clang.abssyntree.StatementList;
//import org.tamedragon.compilers.clang.abssyntree.StorageClassSpecifiers;
//import org.tamedragon.compilers.clang.abssyntree.StructDeclaration;
//import org.tamedragon.compilers.clang.abssyntree.StructDeclarationList;
//import org.tamedragon.compilers.clang.abssyntree.StructDeclarator;
//import org.tamedragon.compilers.clang.abssyntree.StructDeclaratorList;
//import org.tamedragon.compilers.clang.abssyntree.StructOrUnion;
//import org.tamedragon.compilers.clang.abssyntree.StructOrUnionSpecifier;
//import org.tamedragon.compilers.clang.abssyntree.TranslationUnit;
//import org.tamedragon.compilers.clang.abssyntree.TypeDefName;
//import org.tamedragon.compilers.clang.abssyntree.TypeName;
//import org.tamedragon.compilers.clang.abssyntree.TypeQualifier;
//import org.tamedragon.compilers.clang.abssyntree.TypeQualifierList;
//import org.tamedragon.compilers.clang.abssyntree.TypeSpecifier;
//import org.tamedragon.compilers.clang.abssyntree.UnaryExpr;
//import org.tamedragon.compilers.clang.preprocessor.IncludesPreProcessed;
//
///**
// * Represents a declarator that has been analyzed; it has a translated type and name
// * @author shreesha
// *
// */
//
//public class SemanticAnalyzer {
//
//	private Environments environments;
//	private ErrorHandler errorHandler;
//
//	TranslatingMediator translatingMediator;
//
//	// Stack<Label> loopStartLabelStack;  // To keep track of labels for continue statements
//	// Stack<Label> loopEndLabelStack;    // To keep track of labels for break statements
//
//	private Stack<IterationOrSelectionLabels> iterOrSwitchStack;
//
//	HashSet<String> labels;
//
//	//private Stack<IterationStatement> iterationStatementsStack; // This stack will be used while analyzing the iteration expression
//	//private Stack<SelectionStatement> switchStatementsStack; // This stack will be used while analyzing the iteration expression
//
//	private String sourceFileName;   // The name of the source file being analyzed.	
//
//	private HashSet<String> definedFunctions;   // List of defined functions
//	private String currentFunctionName;         // Name of the function currently being analyzed
//
//	private CFunctionDef currentFunctionDef; // FunctionProperties of the function currently being analyzed.
//	private HashMap<AssemExp, Integer> postfixOperativeExprs;   // List of assembly expressions that need post 
//	// operations (increment or decrement) after analyzing current assignment expression
//
//	private Stack<AssignmentExpr> assignmentExprs;   // A stack of assignment expressions curently being analyzed
//
//	private HashMap<Temp, List<AssemType>> tempVsListOfUseTree; // A map of temporaries versus the tree in which they are used
//	private String targetMachineName;
//
//	public static short ASSIGNMENT_IN_FUNC_RETURN_CONTEXT = 0;
//	public static short ASSIGNMENT_IN_ASSIGNMENT_EXPR = 1;
//	public static short ASSIGNMENT_IN_PARAMETER_PASSING = 2;
//	public static short ASSIGNMENT_IN_DECLR_INIT_EXPR = 3;
//	public static short ASSIGNMENT_TO_SUB_ELEMENT = 4;
//	public static short ASSIGNMENT_IN_STRUCT_MEMBER_INIT = 5;
//	public static short ARRAY_PARAM_ANALYSIS = 6;
//
//	public static short STRUCT_INITIALIZER_TRANSLATION_CONTEXT = 0;
//	public static short ARRAY_INITIALIZER_TRANSLATION_CONTEXT = 1;
//
//	// Binary and other operators
//	public static final String LOGICAL_OR = "||";
//	public static final String LOGICAL_AND = "&&";
//	public static final String INCLUSIVE_OR = "|";
//	public static final String EXCLUSIVE_OR = "^";
//	public static final String AND = "&";
//	public static final String EQUALS = "==";
//	public static final String NOT_EQUALS = "!=";
//	public static final String LESSER_THAN = "<";
//	public static final String GREATER_THAN = ">";
//	public static final String GREATER_THAN_OR_EQUAL_TO = ">=";
//	public static final String LESSER_THAN_OR_EQUAL_TO = "<=";
//	public static final String LEFT_SHIFT = "<<";
//	public static final String RIGHT_SHIFT = ">>"; 
//	public static final String ADD = "+";
//	public static final String SUBTRACT = "-";
//	public static final String MULTIPLY = "*";
//	public static final String DIVIDE = "/";
//	public static final String MODULO = "%";
//
//	public static final short EXTERNAL_DECLARATION_CONTEXT = 0;
//	public static final short EXTERN_DECLARATION_CONTEXT = 1;
//	public static final short LOCAL_VARIABLE_CONTEXT = 2;
//
//	public SemanticAnalyzer(String sourceFileName, String targetMachineName) {
//
//		this.targetMachineName = targetMachineName;
//
//		environments = Environments.getInstance();		
//		errorHandler = ErrorHandler.getInstance();
//
//		//loopStartLabelStack = new Stack<Label>();
//		//loopEndLabelStack = new Stack<Label>();
//
//		labels = new HashSet<String>();
//
//		this.sourceFileName = sourceFileName;
//
//		definedFunctions = new HashSet<String>();
//
//		//iterationStatementsStack = new Stack<IterationStatement>();
//		//switchStatementsStack = new Stack<SelectionStatement>();
//
//		iterOrSwitchStack = new Stack<IterationOrSelectionLabels>();
//
//		translatingMediator = new TranslatingMediator();		
//
//		assignmentExprs = new Stack<AssignmentExpr>();
//
//		tempVsListOfUseTree = new HashMap<Temp, List<AssemType>>();
//	}
//
//	public List<CTranslationUnit> translateAbstractTree(Absyn abstractSyntaxTree) {
//		TranslationUnit tu = (TranslationUnit) abstractSyntaxTree;
//
//		//identifyEscapingVariables(abstractSyntaxTree);
//
//		List<CTranslationUnit> translatedUnits = new ArrayList<CTranslationUnit>();
//
//		while(true){
//			if(tu == null) 
//				break;
//
//			ExternDeclaration externalDec = tu.getExternDec();			
//
//			int externalDecType = externalDec.getType();
//			if(externalDecType == ExternDeclaration.DECLARATION){
//
//				AssemType assemTypeFromDec = translateDeclaration((Declaration) externalDec, true);  // Check and translate declaration
//				if(assemTypeFromDec != null){
//					CExternalDecl cExternalDecl = new CExternalDecl();
//					cExternalDecl.setExternalDeclTree(assemTypeFromDec);
//					translatedUnits.add(cExternalDecl);				
//				}
//			}
//			else if(externalDecType == ExternDeclaration.FUNCTION_DEF ){
//				FunctionDef funcDef = (FunctionDef) externalDec;
//				currentFunctionDef = new CFunctionDef();
//				translateFunctionDef((FunctionDef)funcDef);  // This will update the currentFunctionDef
//
//				if(currentFunctionDef != null){
//					translatedUnits.add(currentFunctionDef);				
//				}
//
//				currentFunctionDef = null;   // Reset
//			}
//			else{   // Include directive
//				IncludeDirective includeDirective = (IncludeDirective) externalDec;	
//
//				HashMap<String, HashMap<String, List<InputStream>>> includesPreProcessed = IncludesPreProcessed.getInstance();
//				HashMap<String, List<InputStream>> includesMapForCurrentFile = includesPreProcessed.get(sourceFileName);
//
//				includeDirective.setIncludesMap(includesMapForCurrentFile);
//
//				List<CTranslationUnit> translationUnitsOfIncludedFile = includeDirective.process();
//				if(translationUnitsOfIncludedFile != null)
//					translatedUnits.addAll(translationUnitsOfIncludedFile);
//			}
//
//			tu = tu.getTranslationUnitNext();
//		}
//
//		return translatedUnits;
//	}
//
//	public AssemType translateDeclaration(Declaration declaration, boolean isExternalDecl){
//
//		boolean isTypedef = declaration.isTypeDefDeclaration();
//
//		DeclarationSpecifiers declarationSpecifiers = declaration.getDeclSpecifiers();
//
//		TypeEntryWithAttributes baseEntry = getBaseEntryFromSpecsList(declarationSpecifiers, 
//				isExternalDecl, false, isTypedef);	
//
//		// Now check the declarators
//		InitDeclaratorList initDeclaratorList = declaration.getInitDeclaratorList();
//
//		TranslatedExpAndType teRet = null;
//
//		AssemType ret = null;
//		if(initDeclaratorList != null){		
//			ret = translateDeclaratorList(initDeclaratorList, baseEntry, isExternalDecl, isTypedef);
//		}
//
//		return ret;
//	}
//
//	private AssemType translateDeclaratorList(InitDeclaratorList initDeclaratorList, TypeEntryWithAttributes baseEntry,
//			boolean isExternalDecl, boolean isTypeDef){
//
//		Vector<String> translatedDeclaratorNames = new Vector<String>();
//
//		Stack<AssemType> seqStmt = new Stack<AssemType>();
//
//		int count = 0;
//		while(true){
//			if(initDeclaratorList == null)
//				break;
//
//			InitDeclarator initDeclarator = initDeclaratorList.getInitDeclarator();
//			Declarator declarator = initDeclarator.getDeclarator();
//
//			SourceLocation location = new SourceLocation(declarator.getLineNum(), declarator.getPos());
//
//			TranslatedDeclarator translatedDeclarator = translateDeclarator(declarator, false, false, false);
//
//			if(translatedDeclarator == null){ // Error, move on to the next declarator
//				initDeclaratorList = initDeclaratorList.getInitDeclaratorListNext(); 
//				continue;
//			}
//
//			translatedDeclaratorNames.add(translatedDeclarator.getName());
//
//			String name = translatedDeclarator.getName();	
//
//			if(isTypeDef){  // This is a typedef, make an entry if such a type has already not been defined
//				Table table = environments.getInstanceTypeTable();				
//				TypeDefNameTypeEntry existingTypeEntry = (TypeDefNameTypeEntry)table.get(Symbol.symbol(name));
//				if(table.keyInCurrentScope(Symbol.symbol(name))){ // Already defined
//					TypeEntryWithAttributes existingTe = existingTypeEntry.getActualBasicType();
//					if(existingTe.equals(baseEntry)) { // Re-definition, continue
//						SourceLocation existingLocation = existingTypeEntry.getSourceLocation();
//						errorHandler.addError(sourceFileName, location, name,
//								ErrorHandler.AI_SEE_DECLARATION + sourceFileName + ":" + existingLocation,
//								ErrorHandler.E_TYPE_ALREADY_DEFINED);
//						initDeclaratorList = initDeclaratorList.getInitDeclaratorListNext(); 
//						continue; 
//					}
//
//					else {// Add to error and continue 
//						SourceLocation existingLocation = existingTypeEntry.getSourceLocation();
//						errorHandler.addError(sourceFileName, location, name + ":",
//								ErrorHandler.AI_SEE_DECLARATION + sourceFileName + ":" + existingLocation,
//								ErrorHandler.E_TYPE_ALREADY_DEFINED_WITH_CONFLICTING_TYPE);
//						initDeclaratorList = initDeclaratorList.getInitDeclaratorListNext(); 
//						continue;
//					}
//				}
//
//				TypeDefNameTypeEntry newTypeEntry = 
//					makeTypeEntry(baseEntry, translatedDeclarator, location);
//
//				if(newTypeEntry != null){
//					environments.getInstanceTypeTable().put(Symbol.symbol(name), newTypeEntry);
//				}
//
//				// If there is a initializer, flag an error
//				Initializer initializer = initDeclarator.getInitializer();		
//				if(initializer != null){
//					location = new SourceLocation(initializer.getLineNum(), initializer.getPos());
//					errorHandler.addError(sourceFileName, location, name + ":", "", ErrorHandler.E_TYPE_CANNOT_HAVE_INITIALIZER);
//				}
//
//				initDeclaratorList = initDeclaratorList.getInitDeclaratorListNext(); 
//				continue;				
//			}
//
//			VariableOrFunctionEntry newEntry = makeVariableOrFunctionEntry(translatedDeclarator, 
//					baseEntry, location);
//
//			short declarationContext = LOCAL_VARIABLE_CONTEXT;
//			if(isExternalDecl)
//				declarationContext = EXTERNAL_DECLARATION_CONTEXT;
//			if(baseEntry.isExtern())
//				declarationContext = EXTERN_DECLARATION_CONTEXT;
//
//			boolean hasInitializer = false;
//			if( initDeclarator.getInitializer() != null)
//				hasInitializer = true;
//
//			if(errorByRepeating(name, location, newEntry, declarationContext, hasInitializer)){								
//				initDeclaratorList = initDeclaratorList.getInitDeclaratorListNext(); 
//				continue;
//			}
//			else		
//				addToEnvironment(newEntry);  // No error, make entry
//
//			if(newEntry.getCategory() == VariableOrFunctionEntry.VARIABLE){				
//				VariableEntry varTypeEntry = (VariableEntry) newEntry;
//				BasicType btOfVar = varTypeEntry.getType().getBasicType();
//
//				// Get and analyze the initializer
//				Initializer initializer = initDeclarator.getInitializer();		
//				TypeEntryWithAttributes initlzrTp = null;
//				TranslatedExpAndType treExpAndType = null;
//				if(initializer != null){												
//					if(baseEntry.isExtern()){  
//						// Extern variables should not get initialized since the definition will do it for them
//						errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_EXTERN_DEC_CANNOT_HAVE_INIT);
//					}
//
//					if(btOfVar instanceof ArrayTypeEntry){				
//						treExpAndType = translateInitializer(varTypeEntry.getType(), 
//								initializer, ARRAY_INITIALIZER_TRANSLATION_CONTEXT);
//					}
//					else if(btOfVar instanceof StructOrUnionTypeEntry)
//						treExpAndType = translateInitializer(varTypeEntry.getType(), initializer, 
//								STRUCT_INITIALIZER_TRANSLATION_CONTEXT);
//					else   
//						treExpAndType = translateInitializer(varTypeEntry.getType(), initializer, ARRAY_INITIALIZER_TRANSLATION_CONTEXT);
//
//					if(treExpAndType != null)
//						initlzrTp = treExpAndType.getTypeEntry();					
//				}
//
//				if(btOfVar instanceof ArrayTypeEntry){				
//					// Check if the initializer is also an array
//					if(initlzrTp != null && !(initlzrTp.getBasicType() instanceof ArrayTypeEntry)){
//						// The declaration is an array, but the initializer is not; check if the declaration
//						// is of base type char and the initializer a string
//						BasicType baseTypeOfArray = ((ArrayTypeEntry) btOfVar).getBaseTypeEntry().getBasicType();
//						BasicType baseTypeOfInit = initlzrTp.getBasicType();
//
//						if(!(baseTypeOfArray instanceof CharTypeEntry 
//								&& baseTypeOfInit instanceof StringTypeEntry)){
//
//							errorHandler.addError(sourceFileName, location, name + ": ", "", ErrorHandler.E_INVALID_INITIALIZATION);
//							return null;
//						}
//						else{
//							checkAssignmentCompatibility(varTypeEntry.getType(), initlzrTp, location, 
//									SemanticAnalyzer.ASSIGNMENT_IN_DECLR_INIT_EXPR, AssignmentOperator.ASSIGN, null, -1);	
//						}
//					}
//
//					// Check if the array declarator is ok
//					arrayDeclaratorIsOk((ArrayTypeEntry) btOfVar, initlzrTp,name,  
//							SemanticAnalyzer.ASSIGNMENT_IN_DECLR_INIT_EXPR, location);	
//
//					//	If the excess initializer error message already exists, don't add it again
//
//					if(treExpAndType != null && treExpAndType.isExcessInitializersInArrayInit() &&  !errorHandler.errorOrWarningAlreadyExists(sourceFileName, location, ErrorHandler.ERROR_MSGS_ONLY, 
//							ErrorHandler.E_EXCESS_INITIALIZERS_IN_ARRAY_INIT)) {
//						// Add this error only if required; this might come up several times
//						errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_EXCESS_INITIALIZERS_IN_ARRAY_INIT);					
//					}
//
//				}
//				else{
//					if(initlzrTp != null)   // Able to determine the initializer
//						checkAssignmentCompatibility(varTypeEntry.getType(), initlzrTp, location, 
//								SemanticAnalyzer.ASSIGNMENT_IN_DECLR_INIT_EXPR, AssignmentOperator.ASSIGN, null, -1);
//
//					// If the initializer is of group type and this is not a struct type either,
//					// add excess elements error. If it is already there, don't add it again
//
//					if(initializer != null && initializer.getInitializerList() != null) {
//						BasicType bt = varTypeEntry.getType().getBasicType();
//
//						if(!(bt instanceof StructOrUnionTypeEntry) && initlzrTp != null){
//							// Neither array nor a struct, but initializer is not null
//
//							boolean excessArrayElemsErrorAlreadyExists = errorHandler.errorOrWarningAlreadyExists(sourceFileName, location, ErrorHandler.ERROR_MSGS_ONLY, 
//									ErrorHandler.E_EXCESS_INITIALIZERS_IN_ARRAY_INIT);
//							if(!excessArrayElemsErrorAlreadyExists)
//								// Add this error only if required; this might come up several times
//								errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_EXCESS_INITIALIZERS_IN_ARRAY_INIT);													
//						}						
//					}
//				}	
//
//				// The variable declaration has been analyzed, now translate it to an intermediate tree IR
//				// if there is no strict error
//				if(!errorHandler.isFoundStrictError() ){
//
//					// TODO Assume this variable lives in registers for now 
//					// TODO After escape analysis, we will know if it lives in memory or in registers
//
//					Temp newVarTemp = new Temp();
//					AssemType assemInitExpr = null;
//					if(treExpAndType != null)
//						assemInitExpr = treExpAndType.getAssemType();										
//					else
//						assemInitExpr = createDefautInitializerTree(varTypeEntry.getType().getBasicType());
//
//					AssemType declTree = null;
//					if(!(btOfVar instanceof ArrayTypeEntry || btOfVar instanceof StructOrUnionTypeEntry)){
//						// Neither array nor struct; translate simple declaration
//
//						AssemValueProperties assemValueProperties = getAssemValueProperties(varTypeEntry.getType());
//						declTree = translatingMediator.translateVarDec(newVarTemp, assemInitExpr, 
//								assemValueProperties, tempVsListOfUseTree);
//						translatingMediator.updateTempAndUses(declTree, declTree, tempVsListOfUseTree);
//					}
//					else{
//
//						/*// Either an array or a struct
//						int totalSize = 0;
//						if(btOfVar instanceof ArrayTypeEntry){  // Array							
//							BasicType baseType = btOfVar;
//							while(baseType instanceof ArrayTypeEntry){
//								ArrayTypeEntry arrayTypeEntry = (ArrayTypeEntry) btOfVar;							
//								TypeEntryWithAttributes teDim = arrayTypeEntry.getDimension();
//
//								if(teDim != null){
//									try{
//										totalSize += Integer.parseInt(teDim.getLiteralValue());
//									}
//									catch(NumberFormatException nfe){
//										totalSize = -1;
//										break;
//									}
//								}
//								else{
//									totalSize = -1;
//									break;
//								}																
//							}
//
//						}
//						 */
//					}
//
//					seqStmt.push(declTree);
//
//					count++;
//
//					varTypeEntry.setRegisterTemp(newVarTemp);  // Make note of the corresponding temporary
//				}				
//			}
//			else{   // This is a function declaration
//				Initializer initializer = initDeclarator.getInitializer();
//				if(initializer != null){   // Cannot have an initializer for a function
//					location = new SourceLocation(initializer.getLineNum(), initializer.getPos());
//					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_FUNCTION_CANNOT_HAVE_INITIALIZER);
//				}
//			}
//
//			initDeclaratorList = initDeclaratorList.getInitDeclaratorListNext();  // Get next
//		}
//
//		if(!seqStmt.isEmpty()){
//			if(count == 1){  // Only one declaration, no need for sequential statement
//				return seqStmt.pop();				
//			}
//			else{
//				return translatingMediator.translateSeqStatement(seqStmt);
//			}
//		}
//		else
//			return null;
//	}
//
//	private AssemType createDefautInitializerTree(BasicType basicType) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public AssemValueProperties getAssemValueProperties(TypeEntryWithAttributes teWithAttrsNewVar){		
//		boolean isUnsigned = teWithAttrsNewVar.isUnsigned();
//		BasicType basicType = teWithAttrsNewVar.getBasicType();
//
//		boolean isInteger = true;
//		int integerSize = -1;
//		int floatPrecision = -1;
//		if(basicType instanceof CharTypeEntry){          // 8 bits
//			integerSize = AssemType.SIZE_BYTE;
//		}
//		else if(basicType instanceof ShortTypeEntry){   // 16 bits
//			integerSize = AssemType.SIZE_HALF_WORD;
//		}
//		if(basicType instanceof IntTypeEntry){         // 32 bits
//			integerSize = AssemType.SIZE_WORD;
//		}
//		if(basicType instanceof PointerTypeEntry){         // 32 bits
//			integerSize = AssemType.SIZE_WORD;
//			isUnsigned = true;
//		}
//		if(basicType instanceof LongTypeEntry){        // 64 bits
//			integerSize = AssemType.SIZE_DOUBLE;
//		}
//		if(basicType instanceof HalfTypeEntry){        // 32 bits
//			isInteger = false;
//			floatPrecision = AssemType.FP_HALF;
//		}
//		if(basicType instanceof FloatTypeEntry){        // 32 bits
//			isInteger = false;
//			floatPrecision = AssemType.FP_FLOAT;
//		}
//		if(basicType instanceof DoubleTypeEntry){        // 64 bits
//			isInteger = false;
//			floatPrecision = AssemType.FP_DOUBLE;
//		}
//
//		AssemValueProperties assemValueProperties = new AssemValueProperties(isUnsigned, isInteger, 
//				integerSize, floatPrecision);
//
//		return assemValueProperties;
//	}
//
//	public AssemValueProperties inferAssemValueProperties(Constant cst){				
//		boolean isSigned = true;   // By default, a integer constant is regarded as signed		
//		boolean isInteger = true;
//		int integerSize = -1;
//		int floatPrecision = -1;
//		if(cst.getType() == Constant.FLOAT_CONST){
//			isInteger = false;
//			String fpConst = cst.getFloatingConstant();
//			if(Constant.canBeFloat(fpConst)){
//				floatPrecision = AssemType.FP_FLOAT;
//			}
//
//			if(Constant.canBeDouble(fpConst)){
//				floatPrecision = AssemType.FP_DOUBLE;
//			}
//		}
//		else if(cst.getType() == Constant.INT_CONST){
//			if(cst.isDeclaredAsLong())
//				integerSize =  AssemType.SIZE_QUAD_WORD;
//			else
//				integerSize =  AssemType.SIZE_WORD;
//		}
//		else{   // Constant char
//			integerSize = AssemType.SIZE_BYTE;
//		}
//
//		AssemValueProperties assemValueProperties = new AssemValueProperties(isSigned, isInteger, 
//				integerSize, floatPrecision);
//
//		return assemValueProperties;
//	}
//
//
//	/**
//	 * Checks if the initializer passed has only scalar elements (i.e., there are no arrays as elements. If so,
//	 * checks against the type of array and assigns elements. E.g a declaration like this: 
//	 * 
//	 * 	double array[2][2][3] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
//	 * 
//	 * Return the input array if elements of the array cannot be translated for scalar elements
//	 * 
//	 * @param baseEntry
//	 * @param translatedDeclarator
//	 * @param location
//	 * @return
//	 */
//
//	private TranslatedExpAndType translateCompoundInitializerWithAllScalarElements(TypeEntryWithAttributes teWithAttrs,
//			Vector<TranslatedExpAndType> translatedElements, SourceLocation location){
//
//
//		TranslatedExpAndType retValue = new TranslatedExpAndType();
//
//		HashMap<String, List<TypeEntryWithAttributes>> shiftReduceTable = new HashMap<String, List<TypeEntryWithAttributes>>();
//
//		int numElemsInScalarArray = translatedElements.size();
//		int correctNumOfElements = 0;
//
//		BasicType btTeWithAttrs = teWithAttrs.getBasicType();
//		if(!(btTeWithAttrs instanceof ArrayTypeEntry || btTeWithAttrs instanceof StructOrUnionTypeEntry)) {
//			return translatedElements.elementAt(0);
//		}
//
//		// Determine if all scalar elements in the initializer are all strings
//		boolean allElementsOfStringType = false;
//		if(allElementsOfSameType(translatedElements)){
//			TypeEntryWithAttributes teElem = translatedElements.get(0).getTypeEntry();
//			if(teElem.getBasicType() instanceof StringTypeEntry)
//				allElementsOfStringType = true;
//		}
//
//		boolean shiftRedTableCannotBeDetermined = false;
//
//		if(btTeWithAttrs instanceof StructOrUnionTypeEntry){
//			// This is of type struct or union
//			ShiftReducePopulationResult res = populateShiftReduceTable(shiftReduceTable, 0, teWithAttrs,
//					allElementsOfStringType, location);
//
//			correctNumOfElements = res.getElementCount();
//			if(!res.isSizeCanBeDetermined()){   
//				// Error, maybe no size specified for child arrays; return
//				shiftRedTableCannotBeDetermined = true;
//				retValue.setEntry(teWithAttrs);
//				return retValue;
//			}
//		}
//		else{
//			// This is of type array
//			ArrayTypeEntry arrTypeMain = (ArrayTypeEntry)btTeWithAttrs;
//			TypeEntryWithAttributes teChild = arrTypeMain.getBaseTypeEntry();
//			BasicType btChild = teChild.getBasicType();
//			if(btChild instanceof ArrayTypeEntry || btChild instanceof StructOrUnionTypeEntry){ 
//				// Multidimensional array or an array of struct type
//				if(arrTypeMain.getDimension() == null){
//					ShiftReducePopulationResult res = populateShiftReduceTable(shiftReduceTable, 0, teChild, 
//							allElementsOfStringType, location);
//					int numElemsOfBase = res.getElementCount();
//
//					if(numElemsOfBase == 0){   
//						// Error, maybe no size specified for child arrays; return
//						shiftRedTableCannotBeDetermined = true;
//						retValue.setEntry(teWithAttrs);
//						return retValue;
//					}
//					else{
//						processArrayInitWithUnknowSize(teWithAttrs,  shiftReduceTable, numElemsInScalarArray,
//								numElemsOfBase);
//						correctNumOfElements = numElemsInScalarArray;
//					}				
//				}
//				else{
//					ShiftReducePopulationResult res = populateShiftReduceTable(shiftReduceTable, 0, teWithAttrs, 
//							allElementsOfStringType, location);
//					correctNumOfElements = res.getElementCount();
//					if(!res.isSizeCanBeDetermined())
//						shiftRedTableCannotBeDetermined = true;
//				}
//			}
//			else {   // Single dimensional array with a non-complex base type
//				correctNumOfElements = translatedElements.size();
//				return getTransExpAndTypeForSingleDimArrayInit(teChild, correctNumOfElements, 
//						translatedElements, location);
//
//			}
//		}
//
//		int count = 1;
//
//		Stack<TypeEntryWithAttributes> shiftStack = new Stack<TypeEntryWithAttributes>();
//
//		for(TranslatedExpAndType transExpAndType: translatedElements){
//			TypeEntryWithAttributes teExp = transExpAndType.getTypeEntry();
//			location = transExpAndType.getSourceLocation();
//			List<TypeEntryWithAttributes> list = shiftReduceTable.get("" + count);
//
//			shiftStack.push(teExp);			
//			if(list != null){  
//				// There are reduce instruction(s) here
//				for(TypeEntryWithAttributes interimTe : list){
//
//					BasicType interimBasicType = interimTe.getBasicType();
//					if(interimBasicType instanceof ArrayTypeEntry){
//						// Array type
//						ArrayTypeEntry arrType = (ArrayTypeEntry) interimBasicType;
//						TypeEntryWithAttributes btOfArray = arrType.getBaseTypeEntry();
//						TypeEntryWithAttributes teDim = arrType.getDimension();
//						int arrSize = 0;
//						if(teDim != null){
//							//TODO is null value possible?
//							try{
//								arrSize = Integer.parseInt(teDim.getLiteralValue());
//							}
//							catch(NumberFormatException nfe){
//								// TODO  is this possible?
//							}						
//						}
//
//						Stack<TypeEntryWithAttributes> reversedEntryStack = new Stack<TypeEntryWithAttributes>();
//
//						if(btOfArray.getBasicType() instanceof CharTypeEntry && allElementsOfStringType){
//							// Array of chars; Check each element against the type
//
//							while(!shiftStack.isEmpty()){
//								TypeEntryWithAttributes teOnStack = shiftStack.pop();								
//								reversedEntryStack.push(teOnStack);
//							}
//
//							for(int i = 0; i < count; i++){														
//								checkAssignmentCompatibility(interimTe, reversedEntryStack.pop(), location,
//										SemanticAnalyzer.ASSIGNMENT_IN_DECLR_INIT_EXPR, AssignmentOperator.ASSIGN, null, -1);
//							}
//						}
//						else{
//							for(int i = 0; i < arrSize; i++){
//								if(!shiftStack.isEmpty()){
//									TypeEntryWithAttributes teOnStack = shiftStack.pop();								
//									reversedEntryStack.push(teOnStack);
//								}
//								else{
//									// TODO Error?
//								}
//							}
//
//							// Check each element against the type
//							for(int i = 0; i < arrSize; i++){														
//								checkAssignmentCompatibility(btOfArray, reversedEntryStack.pop(), location,
//										SemanticAnalyzer.ASSIGNMENT_IN_DECLR_INIT_EXPR, AssignmentOperator.ASSIGN, null, -1);
//							}		
//						}
//					}
//					else{
//						// Structure type
//						StructOrUnionTypeEntry structOrUnionTypeEntry = (StructOrUnionTypeEntry) interimBasicType;
//						List<String> memberNames = structOrUnionTypeEntry.getMemberNames();
//						Stack<TypeEntryWithAttributes> reversedEntryStack = new Stack<TypeEntryWithAttributes>();
//
//						if(!structOrUnionTypeEntry.isStruct()){  
//							// Is union, consider only the first element
//							if(!shiftStack.isEmpty()){
//								TypeEntryWithAttributes teOnStack = shiftStack.pop();								
//								reversedEntryStack.push(teOnStack);
//							}
//							else{
//								// TODO Error?
//							}
//
//							if(memberNames != null && memberNames.size() > 0){
//								TypeEntryWithAttributes unionMem = 
//									structOrUnionTypeEntry.getMemberType(memberNames.get(0));
//								checkAssignmentCompatibility(unionMem, reversedEntryStack.pop(), location,
//										SemanticAnalyzer.ASSIGNMENT_IN_DECLR_INIT_EXPR, AssignmentOperator.ASSIGN, null, -1);
//
//							}
//						}
//						else{
//							// This is a struct,consider all the members
//							for(int i = 0; i < memberNames.size(); i++){
//								if(!shiftStack.isEmpty()){
//									TypeEntryWithAttributes teOnStack = shiftStack.pop();								
//									reversedEntryStack.push(teOnStack);
//								}
//								else{
//									// TODO Error?
//								}
//							}						
//							// Check all the members
//
//							int memCount = 0;
//							for(String memName: memberNames){									
//								TypeEntryWithAttributes teStructMem = structOrUnionTypeEntry.getMemberType(memName);
//								checkAssignmentCompatibility(teStructMem, reversedEntryStack.pop(), location,
//										SemanticAnalyzer.ASSIGNMENT_IN_DECLR_INIT_EXPR, AssignmentOperator.ASSIGN, null, -1);
//								//if(!structOrUnionTypeEntry.isStruct()){
//								// Is union, exit loop for first member
//								//	if(memCount == 0)
//								//		break;
//							}
//							memCount++;
//						}
//					}
//
//					// Push this into the queue					
//					shiftStack.push(interimTe);					
//				}	
//			}
//			count++;
//		}
//
//		// Get the pending item on the stack
//		TypeEntryWithAttributes teRet = teWithAttrs;
//		if(shiftStack.size() > 0){
//			TypeEntryWithAttributes teLast = shiftStack.pop();
//			if(!teLast.equals(teWithAttrs)){
//				teRet = teWithAttrs;
//			}
//			else{
//				teRet = teLast;
//			}
//		}
//
//		if((!shiftRedTableCannotBeDetermined) && translatedElements.size() > correctNumOfElements){
//			if(btTeWithAttrs instanceof StructOrUnionTypeEntry)
//				retValue.setExcessInitializersInStructnit(true);
//			else
//				retValue.setExcessInitializersInArrayInit(true);
//		}
//
//		retValue.setEntry(teRet);
//		return retValue;
//
//	}
//
//	private void processArrayInitWithUnknowSize(TypeEntryWithAttributes teWithAttrs, 
//			HashMap<String, List<TypeEntryWithAttributes>> shiftReduceTable, int numElemsInScalarArray,
//			int numElemsOfBase){
//		int rem = numElemsInScalarArray % numElemsOfBase;
//		int dimOfMainArray = numElemsInScalarArray / numElemsOfBase;
//		if(rem > 0){
//			dimOfMainArray++;
//		}
//
//		// Update the shift reduce table with the dimension of the main array obtained
//		Set<String> reduceIndicesInTable = shiftReduceTable.keySet();
//		List<String> indicesList = new ArrayList<String>(reduceIndicesInTable);
//
//		int reduceIndex = numElemsOfBase;
//		int currIndex = 0;
//		for(int m = 1; m < dimOfMainArray; m++){	
//
//			int max = 0;
//			for(String key: indicesList){
//				List<TypeEntryWithAttributes> teWithAttrsList = shiftReduceTable.get(key);
//				int keyInt = Integer.parseInt(key);
//				currIndex = reduceIndex + keyInt;
//
//				List<TypeEntryWithAttributes> newList = new ArrayList<TypeEntryWithAttributes>();
//				newList.addAll(teWithAttrsList);
//
//				shiftReduceTable.put("" + currIndex, newList);		
//
//				if(currIndex > max)
//					max = currIndex;
//			}
//
//			reduceIndex = max;
//		}
//
//		// Update the dimension of the lhs, now that it is known
//		TypeEntryWithAttributes newDimTeWithAttrs = new TypeEntryWithAttributes();
//		newDimTeWithAttrs.setBasicType(IntTypeEntry.getInstance());
//		newDimTeWithAttrs.setLiteralValue("" + dimOfMainArray);
//
//		ArrayTypeEntry arrTypeMain = (ArrayTypeEntry) teWithAttrs.getBasicType();
//		arrTypeMain.setDimension(newDimTeWithAttrs);
//		updateReduceList(shiftReduceTable, reduceIndex, teWithAttrs);
//	}
//
//	/**
//	 * Creates a "shift-reduce" map that helps determine at which point in a initializer list with all
//	 * scalar elements a type (or types ) can be deduced. The cannotBeDetermined flag that is passed is an
//	 * output field that indicates that a correct mapping could not be found - primarily due to presence of
//	 * arrays with unspecified size as struct members OR as multidimensional arrays with unspecified sizes.
//	 * 
//	 * @param retMap
//	 * @param count
//	 * @param teWithAttributes
//	 * @param allValuesAreStrings
//	 * @param cannotBeDetermined
//	 * @param location
//	 * @return
//	 */
//
//	private ShiftReducePopulationResult populateShiftReduceTable(HashMap<String, List<TypeEntryWithAttributes>> retMap, int count, 
//			TypeEntryWithAttributes teWithAttributes, 
//			boolean allValuesAreStrings, SourceLocation location){
//
//		ShiftReducePopulationResult result = new ShiftReducePopulationResult();
//
//		BasicType basicType = teWithAttributes.getBasicType();
//
//		boolean sizeCanBeDetermined = true;
//		if(basicType instanceof ArrayTypeEntry){
//			// This is of type array, check the element type
//			ArrayTypeEntry arrTypeEntry = (ArrayTypeEntry) basicType;
//			TypeEntryWithAttributes teElements = arrTypeEntry.getBaseTypeEntry();
//
//			// Check the base is base type is an array of characters
//			boolean baseTypeIsCharArray = false;
//			BasicType btOfBase = teElements.getBasicType();
//			if(btOfBase instanceof ArrayTypeEntry){
//				ArrayTypeEntry baseArray = (ArrayTypeEntry) btOfBase;
//				TypeEntryWithAttributes baseArrayType = baseArray.getBaseTypeEntry();
//				if(baseArrayType.getBasicType() instanceof CharTypeEntry)
//					baseTypeIsCharArray = true;
//			}
//
//			int arrSize = 0;
//			TypeEntryWithAttributes teDim = arrTypeEntry.getDimension();
//			if(teDim != null){
//				try{
//					arrSize = Integer.parseInt(teDim.getLiteralValue());
//				}
//				catch(NumberFormatException nfe){
//					sizeCanBeDetermined = false;
//				}
//			}
//			else{
//				sizeCanBeDetermined = false;
//			}
//
//			if(allValuesAreStrings && baseTypeIsCharArray){
//				// Treat char array like strings
//				count = arrSize;
//				updateReduceList(retMap, count, teElements);				
//			}
//			else{
//				for(int i = 0; i < arrSize; i++){
//					ShiftReducePopulationResult res = populateShiftReduceTable(retMap, count, teElements, 
//							allValuesAreStrings,  location); 
//					if(!res.isSizeCanBeDetermined())
//						sizeCanBeDetermined = false;
//					else
//						count = res.getElementCount();
//				}
//
//				// Reduce typeEntryWithAttrs passed
//				updateReduceList(retMap, count, teWithAttributes);	
//			}
//
//		}
//		else if(basicType instanceof StructOrUnionTypeEntry){
//			// This is of type struct or union, iterate through the members
//			StructOrUnionTypeEntry structOrUnionTypeEntry = (StructOrUnionTypeEntry) basicType;
//			List<String> memberNames = structOrUnionTypeEntry.getMemberNames();
//
//			for(String memName: memberNames){
//				TypeEntryWithAttributes teMem = structOrUnionTypeEntry.getMemberType(memName);
//				BasicType memBt = teMem.getBasicType();
//				if(memBt instanceof ArrayTypeEntry || memBt instanceof StructOrUnionTypeEntry){		
//					ShiftReducePopulationResult res = populateShiftReduceTable(retMap, count, teMem, 
//							allValuesAreStrings, location);
//					if(!res.isSizeCanBeDetermined())
//						sizeCanBeDetermined = false;
//					else 
//						count = res.getElementCount();
//				}
//				else
//					count++;
//
//				if(!structOrUnionTypeEntry.isStruct()){
//					// Is a union
//					break;
//				}						
//			}
//
//			// Reduce this also
//			updateReduceList(retMap, count, teWithAttributes);			
//		}
//		else{
//			count++;
//		}
//
//		result.setElementCount(count);
//		result.setSizeCanBeDetermined(sizeCanBeDetermined);
//		return result;		
//	}
//
//	private void updateReduceList(HashMap<String, List<TypeEntryWithAttributes>> retMap, int count,
//			TypeEntryWithAttributes teWithAttributes){
//		List<TypeEntryWithAttributes> reduceTe = retMap.get("" + count);
//		if(reduceTe == null)
//			reduceTe = new ArrayList<TypeEntryWithAttributes>();
//		reduceTe.add(teWithAttributes);
//
//		retMap.put(""+count, reduceTe);	
//	}
//
//	private TypeDefNameTypeEntry makeTypeEntry(TypeEntryWithAttributes baseEntry, TranslatedDeclarator translatedDeclarator,
//			SourceLocation location){
//		String name = translatedDeclarator.getName();
//
//		TypeEntryWithAttributes actualBasicType = null;
//		String referenceTypeName = null;
//
//		BasicType btOfBase = baseEntry.getBasicType();
//		if(btOfBase instanceof TypeDefNameTypeEntry){
//			TypeDefNameTypeEntry tdEntry = (TypeDefNameTypeEntry)btOfBase;
//			referenceTypeName = tdEntry.getTypeName();
//			actualBasicType = getActualBasicType(tdEntry, location); 
//			if(actualBasicType != null){
//				actualBasicType.copy(baseEntry, true);
//			}
//		}
//		else{
//			actualBasicType = baseEntry;
//		}
//
//		TypeDefNameTypeEntry typeDefNameTypeEntry = null;
//
//		DeclaratorChainElement declaratorChainElement = translatedDeclarator.getDeclaratorChainElement();
//
//		if(declaratorChainElement == null){ // Must be a simple type declaration
//			typeDefNameTypeEntry = 
//				new TypeDefNameTypeEntry(name, actualBasicType, referenceTypeName, location);
//		}
//		else{
//			int declaratorChainElementType = declaratorChainElement.getDeclaratorChainElementType();
//			if(declaratorChainElementType == DeclaratorChainElement.DECL_CHAIN_FUNCTION){  // Error
//				errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_INVALID_TYPE_DECL);
//				return null;
//			}
//			else{
//				TypeEntryWithAttributes teWithAttrs = new TypeEntryWithAttributes();
//				teWithAttrs.setBasicType((BasicType) declaratorChainElement);
//				TypeEntryWithAttributes.updateBasicType(teWithAttrs, actualBasicType);
//				typeDefNameTypeEntry = 
//					new TypeDefNameTypeEntry(name, teWithAttrs, referenceTypeName, location);
//			}
//		}
//
//		return typeDefNameTypeEntry;
//
//	}
//
//	private TypeEntryWithAttributes getActualBasicType(TypeDefNameTypeEntry tdEntry,
//			SourceLocation location){
//
//		TypeEntryWithAttributes actualBasicType = null;
//		Table table = environments.getInstanceTypeTable();
//
//		String refName = tdEntry.getTypeName();
//
//		while(true){
//			Object entry = table.get(Symbol.symbol(refName));
//			if(entry instanceof TypeDefNameTypeEntry){
//				TypeDefNameTypeEntry parent = (TypeDefNameTypeEntry) entry;
//				refName = parent.getReferenceTypeName();
//				if(refName != null)
//					continue;
//				else{
//					actualBasicType = parent.getActualBasicType();
//					break;
//				}
//			}
//			else{  // Error				
//				errorHandler.addError(sourceFileName, location, refName + ":", "", ErrorHandler.E_TYPE_NOT_DEFINED);				
//				actualBasicType = null;
//				break;
//			}
//		}
//		return actualBasicType;
//	}
//
//	private void arrayDeclaratorIsOk(ArrayTypeEntry arrayType, TypeEntryWithAttributes init, String name,
//			int context, SourceLocation location){
//
//		// If this declaration is related to a declaration (either auto or extern), the size must be specified.
//		if(context == SemanticAnalyzer.ASSIGNMENT_IN_DECLR_INIT_EXPR){
//			if(init == null){
//				if(arrayType.getDimension() == null){
//					errorHandler.addError(sourceFileName, location, name + ": ", "", ErrorHandler.E_ARRAY_SIZE_NOT_SPECIFIED);		
//				}
//			}
//		}
//
//		// If this is a multidimensional array, make sure except the first row, the other dimensions must be specified
//		while(true){
//			TypeEntryWithAttributes baseTeWithAttributes = arrayType.getBaseTypeEntry();
//			if(baseTeWithAttributes.getBasicType() instanceof ArrayTypeEntry){
//				arrayType =  (ArrayTypeEntry)baseTeWithAttributes.getBasicType();
//
//				if(arrayType.getDimension() == null ){
//					if(context == SemanticAnalyzer.ASSIGNMENT_IN_PARAMETER_PASSING)
//						errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_INCORRECT_FORMAL_PARAMETER);
//					else{		
//						errorHandler.addError(sourceFileName, location, name + ": ", "", ErrorHandler.E_MISSING_SUBSCRIPT_IN_ARRAY_DECL);
//					}
//				}				
//			}
//			else
//				break;
//		}
//	}
//
//	private void addToEnvironment(VariableOrFunctionEntry newEntry){
//		//TODO - implement the following and un-comment to remove hard-coding of escapes
//		//Boolean escBool = (Boolean) environments.getEscapeTable().get(varName);
//		//boolean escapes = escBool.booleanValue();
//		boolean escapes = false;
//		if(!escapes){
//			Temp newVarTemp = new Temp();
//
//			if(newEntry.getCategory() == VariableOrFunctionEntry.VARIABLE){
//				VariableEntry newVariableEntry = (VariableEntry) newEntry;
//
//				environments.getInstanceVariableTable().put(Symbol.symbol(newVariableEntry.getName()), newVariableEntry);	
//				newVariableEntry.setRegisterTemp(newVarTemp);
//			}
//			else{
//				FunctionEntry newFuncEntry = (FunctionEntry) newEntry;
//				environments.getInstanceVariableTable().put(Symbol.symbol(newFuncEntry.getName()), newFuncEntry);	
//			}
//		}
//	}
//
//	private VariableOrFunctionEntry getVarOrFunctionEntryFromTranslatedDeclarator(TranslatedDeclarator translatedDeclarator, 
//			SourceLocation location){
//		VariableOrFunctionEntry varOrFunctionEntry = null;
//		DeclaratorChainElement declaratorChainElement = translatedDeclarator.getDeclaratorChainElement();
//
//		if(declaratorChainElement == null){ // Must be a simple variable declaration
//			VariableEntry varEntry = new VariableEntry();
//			TypeEntryWithAttributes teWithAttrs = new TypeEntryWithAttributes();
//			varEntry.setType(teWithAttrs);
//			varEntry.setName(translatedDeclarator.getName());
//			varEntry.setSourceLocation(location);
//			varOrFunctionEntry = varEntry;
//
//			return varOrFunctionEntry;
//		}
//
//		int declaratorChainElementType = declaratorChainElement.getDeclaratorChainElementType();
//		if(declaratorChainElementType == DeclaratorChainElement.DECL_CHAIN_FUNCTION){
//			FunctionEntry funcEntry = (FunctionEntry) declaratorChainElement;
//			funcEntry.setSourceLocation(location);
//			varOrFunctionEntry = (FunctionEntry) funcEntry;
//		}
//		else{
//			// Anything other than a function must be a variable
//			VariableEntry varEntry = new VariableEntry();
//			TypeEntryWithAttributes teWithAttrs = new TypeEntryWithAttributes();
//			teWithAttrs.setBasicType((BasicType) declaratorChainElement);
//			varEntry.setType(teWithAttrs);
//			varEntry.setName(translatedDeclarator.getName());
//			varEntry.setSourceLocation(location);
//			varOrFunctionEntry = varEntry;
//		}
//
//		return varOrFunctionEntry;
//	}
//
//	private VariableOrFunctionEntry makeVariableOrFunctionEntry(TranslatedDeclarator translatedDeclarator, 
//			TypeEntryWithAttributes teWithAttrs, SourceLocation location){
//
//		// Get the variable or function entry from the translated declarator first
//		VariableOrFunctionEntry variableOrFunEntry = getVarOrFunctionEntryFromTranslatedDeclarator(translatedDeclarator, location);
//
//		if(variableOrFunEntry.getCategory() == VariableOrFunctionEntry.FUNCTION){ // Make new function entry
//			FunctionEntry newFunctionEntry = (FunctionEntry) variableOrFunEntry;
//
//			// Update the basic type of the variable entry from the type entry with attributes object passed
//			TypeEntryWithAttributes teResult = newFunctionEntry.getReturnType();
//			if(teResult == null)
//				newFunctionEntry.setReturnType(teWithAttrs);
//			else
//				TypeEntryWithAttributes.updateBasicType(teResult, teWithAttrs);
//
//			if(newFunctionEntry.getReturnType().getBasicType() instanceof PointerTypeEntry)	{
//				// This is a function declaration/ definition that returns a pointer; set TypeEntryWithAttributes
//				// so it is not returning void (might have noted as void in get getBaseEntryFromDeclSpecs();
//				teWithAttrs.setVoid(false);
//			}
//			String name = translatedDeclarator.getName();
//			newFunctionEntry.setName(name);
//
//		}
//		else{  // Make new variable entry
//			VariableEntry newVariableEntry = (VariableEntry) variableOrFunEntry;
//
//			teWithAttrs.setIsLValue(true);
//			// Update the basic type of the variable entry from the type entry with attributes object passed
//			TypeEntryWithAttributes teType = newVariableEntry.getType();
//			if(teType == null)
//				newVariableEntry.setType(teWithAttrs);
//			else
//				TypeEntryWithAttributes.updateBasicType(teType, teWithAttrs);
//
//		}
//
//		return variableOrFunEntry;
//	}
//
//	/**
//	 * Analyzes and translates the declarator that is passed. isParamDeclContext is the parameter that indicates whether 
//	 * the declarator is located in the declaration of a parameter declaration of a function declaration 
//	 * or a function definition; if it isn't it could be
//	 * either be in an external declaration or a local (auto) declaration or a parameter declaration in a function
//	 * defintion - isFunctionDefContext indicates whether this is a function definition
//	 * @param declarator
//	 * @param isParamDeclContext
//	 * @param isFunctionDefContext
//	 * @return
//	 */
//
//	private TranslatedDeclarator translateDeclarator(Declarator declarator, 
//			boolean isParamDeclContext, boolean isFunctionDefContext, boolean isDeclaratorInStructMember){
//
//		SourceLocation location = new SourceLocation(declarator.getLineNum(), declarator.getPos());
//
//		DirectDeclarator directDeclarator = declarator.getDirectDeclarator();
//
//		Stack<DeclaratorChainElement> declaratorStack = new Stack<DeclaratorChainElement>();
//
//		ParamTypeList paramTypeList = null;
//
//		String name = null;
//
//		while(true){
//			if(directDeclarator == null)
//				break;
//
//			int directDeclaratorType = directDeclarator.getType();
//			if(directDeclaratorType == DirectDeclarator.DECLR){
//				Declarator subDeclarator = directDeclarator.getDeclarator();
//				TranslatedDeclarator translatedSubDeclarator = translateDeclarator(subDeclarator, 
//						isParamDeclContext, isFunctionDefContext,
//						isDeclaratorInStructMember);
//
//				if(translatedSubDeclarator == null){    // Error, move on to the next declarator
//					directDeclarator = directDeclarator.getDirectDeclarator();		
//					break;
//				}
//
//				name = translatedSubDeclarator.getName();
//				paramTypeList = translatedSubDeclarator.getParamTypeList();
//
//				declaratorStack.push(translatedSubDeclarator.getDeclaratorChainElement());				
//			}
//			else if(directDeclaratorType == DirectDeclarator.CONST_ARRAY){
//				ConditionalExpr conditionalExpr = directDeclarator.getArraySizeExpr().getCondExpr();
//				TypeEntryWithAttributes val =  translateConditionalExpr(conditionalExpr).getTypeEntry();
//				analyzeArraySizeExpr(val, location);
//
//				ArrayTypeEntry arrayTypeEntry = new ArrayTypeEntry();
//				arrayTypeEntry.setDimension(val);
//				declaratorStack.push(arrayTypeEntry);
//			}
//			else if(directDeclaratorType == DirectDeclarator.EMPTY_ARRAY){
//				ArrayTypeEntry arrayTypeEntry = new ArrayTypeEntry();
//				arrayTypeEntry.setDimension(null);
//				declaratorStack.push(arrayTypeEntry);
//			}
//			else if(directDeclaratorType == DirectDeclarator.FUNC){
//				FunctionEntry funcEntry = new FunctionEntry();
//				if(!isFunctionDefContext){
//					// This is a function declaration only, not a definition, so translate 
//					// the params right away
//
//					Vector<VariableEntry> formals = translateParamTypeList(directDeclarator.getParamTypeList(),
//							isFunctionDefContext);
//
//					if(directDeclarator.getParamTypeList().isHasEllipses()){
//						funcEntry.setEndsWithEllipses(true);
//					}
//
//					funcEntry.setFormals(formals);
//				}
//				else{
//					// This is a function definition, store the params to be translated later
//					// when the entire function definition is to being analyzed.
//					if(paramTypeList == null)
//						paramTypeList = directDeclarator.getParamTypeList();
//				}
//
//				funcEntry.setSourceLocation(location);
//				declaratorStack.push(funcEntry);
//			}
//			else if(directDeclaratorType == DirectDeclarator.ID){
//				name = directDeclarator.getId();
//			}
//			else if(directDeclaratorType == DirectDeclarator.NO_ARG_FUNC){
//				FunctionEntry funcEntry = new FunctionEntry();
//				funcEntry.setFormals(null);
//				funcEntry.setSourceLocation(location);
//				declaratorStack.push(funcEntry);
//			}
//			else if(directDeclaratorType == DirectDeclarator.ID_LIST_FUNC){
//				// TODO Implement this
//			}
//
//			directDeclarator = directDeclarator.getDirectDeclarator();			
//		}
//
//		// Analyze pointers, if any
//		populateDeclaratorStackWithPointerEntries(declarator, declaratorStack);
//
//		TranslatedDeclarator translatedDeclarator = new TranslatedDeclarator();
//		if(declaratorStack.isEmpty()){
//			// No stack, must be a simple declarator			
//			translatedDeclarator.setName(name);
//		}
//		else{
//			// Stack has been populated, now iterate through the stack, creating the VariableEntry or FunctionEntry 
//			// for the name		
//			translatedDeclarator =  getTranslatedDecalaratorFromStack(declaratorStack, name, location);
//		}
//
//		if(translatedDeclarator != null)
//			translatedDeclarator.setParamTypeList(paramTypeList);
//
//		return translatedDeclarator;
//	}
//
//	private TranslatedDeclarator getTranslatedDecalaratorFromStack(Stack<DeclaratorChainElement> 
//	declaratorStack, String name, SourceLocation location){
//
//		while(!declaratorStack.empty()){
//			DeclaratorChainElement top = declaratorStack.pop();
//			if(declaratorStack.empty()){ // Last object has already been popped, 				
//				return new TranslatedDeclarator(top, name);				
//			}
//			else{
//				DeclaratorChainElement nextChainElement = declaratorStack.pop();
//				String status = nextChainElement.setNextElementInDeclaratorChain(top);
//				if(status.equals(ErrorHandler.OK))
//					declaratorStack.push(nextChainElement);
//				else{
//					ErrorHandler errorHandler = ErrorHandler.getInstance();
//					errorHandler.addError(sourceFileName, location, name, "", status);
//					break;
//				}
//			}
//		}
//
//		return null; // Must be an error
//	}
//
//	private void populateDeclaratorStackWithPointerEntries(Declarator declarator, 
//			Stack<DeclaratorChainElement> stack){
//		// Analyze pointer (if any)
//
//		Pointer pointer = declarator.getPointer();
//
//		int qualifierCount = 0;
//		boolean isConstPtr = false;
//		boolean isVolatilePtr = false;
//		while(pointer != null){
//			TypeQualifierList tql = pointer.getTypeQualifierList();
//			SourceLocation location = new SourceLocation(pointer.getLineNum(), pointer.getPos());
//
//			if(tql != null){
//				qualifierCount++;
//				TypeQualifier tq = tql.getTypeQualifier();
//				if(tq.getType() == TypeQualifier.CONST)
//					isConstPtr = true;
//				else // Must be volatile
//					isVolatilePtr = true;
//			}
//			if(qualifierCount > 2){  // Can be const and volatile, not more
//				errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_TOO_MANY_QUALIFIERS);
//			}
//			else{
//				if(qualifierCount == 2){
//					if(!(isConstPtr && isVolatilePtr)){    // Either const or volatile is repeated
//						errorHandler.addError(sourceFileName, location, "", "",  ErrorHandler.E_REPEATED_QUALIFIER);
//					}
//				}
//			}
//
//			PointerTypeEntry pte = new PointerTypeEntry();
//
//			pte.setConstPtr(isConstPtr);
//			pte.setVolatilePtr(isVolatilePtr);
//			stack.push(pte);
//
//			pointer = pointer.getPointer();   // Get next pointer, if available
//		}		
//	}
//
//	/*
//	 * This function returns the translated expr. and type it sets the excessInitializers flat
//	 * to true if it finds excess initializers; the calling function can then
//	 * use this information to add to the error list.
//	 */
//	private TranslatedExpAndType translateInitializer(TypeEntryWithAttributes lhsType, Initializer initializer,
//			short initializerContext){
//
//		AssignmentExpr assgnExpr = initializer.getInitExpr();
//		if(assgnExpr != null){
//			return translateAssignmentExpr(assgnExpr);
//		}
//		else{
//
//			InitializerList initlzrList = initializer.getInitializerList();
//			SourceLocation location = new SourceLocation(initializer.getLineNum(), initializer.getPos());	
//
//			if(initializerContext == ARRAY_INITIALIZER_TRANSLATION_CONTEXT){
//				// This is an array initializer
//				BasicType btLhs = lhsType.getBasicType();
//				if(btLhs instanceof ArrayTypeEntry ){
//
//					ArrayTypeEntry btLhsArr = (ArrayTypeEntry) btLhs;
//
//					int maxSize = -1;  // No size specified in declaration
//					TypeEntryWithAttributes teDim = btLhsArr.getDimension();
//					if(teDim != null){
//						try{
//							maxSize = Integer.parseInt(teDim.getLiteralValue());
//						}
//						catch(NumberFormatException nfe){
//							errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_ARRAY_SUBSCRIPT_NOT_INTEGER);
//						}
//					}
//
//					return translateArrayInitializerList(lhsType, maxSize, initlzrList, location, 0);
//				}
//				else{			
//					TranslatedExpAndType transExpAndType = translateArrayInitializerList(lhsType, 
//							-1, initlzrList, location, 0);
//					transExpAndType.setExcessInitializersInArrayInit(true);
//					return transExpAndType;
//				}
//			}
//			else{			
//				// This is a struct initializer 
//				BasicType btLhs = lhsType.getBasicType();
//				if(btLhs instanceof StructOrUnionTypeEntry ){
//
//					StructOrUnionTypeEntry btLhsStruct = (StructOrUnionTypeEntry) btLhs;
//
//					return translateStructInitializerList(lhsType, initlzrList, location);
//				}
//				else{			
//					TranslatedExpAndType transExpAndType = translateStructInitializerList(lhsType, initlzrList, location);
//					transExpAndType.setExcessInitializersInStructnit(true);
//
//					return null;
//				}
//			}				
//		}		
//	}
//
//	private TranslatedExpAndType translateArrayInitializerList(TypeEntryWithAttributes tpBaseType, 
//			int size, InitializerList initializerList, SourceLocation location, int arrayBaseNum){
//
//		int count = 0;
//		Vector<TranslatedExpAndType> values = new Vector<TranslatedExpAndType>();
//
//		boolean excessInitializers = false;
//		boolean initializerHasAllScalarElements = true;
//		while(initializerList != null){
//			Initializer initializer = initializerList.getInitializer();
//			AssignmentExpr assignmentExpr = initializer.getInitExpr();
//			if(assignmentExpr != null){
//				TranslatedExpAndType translatedExpAndType = translateAssignmentExpr(assignmentExpr);
//				translatedExpAndType.setSourceLocation(new SourceLocation(assignmentExpr.getLineNum(),
//						assignmentExpr.getPos()));
//				values.addElement(translatedExpAndType);
//			}
//			else{
//				initializerHasAllScalarElements = false;
//				InitializerList list = initializer.getInitializerList();
//
//				// To translate this list, get the "sub-array" of the declaration
//				BasicType bt = tpBaseType.getBasicType();
//				if(bt instanceof ArrayTypeEntry){
//					ArrayTypeEntry btArr = (ArrayTypeEntry) bt;
//					TypeEntryWithAttributes teBtArr = btArr.getBaseTypeEntry();
//					BasicType btTeBtArr = teBtArr.getBasicType();
//					if(btTeBtArr instanceof ArrayTypeEntry){
//						ArrayTypeEntry btArrChild = (ArrayTypeEntry) btTeBtArr;
//
//						TypeEntryWithAttributes teDim = btArrChild.getDimension();
//						int maxSize = 0;
//						if(teDim != null){
//							try{
//								maxSize = Integer.parseInt(teDim.getLiteralValue());
//							}
//							catch(NumberFormatException nfe){
//								errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_ARRAY_SUBSCRIPT_NOT_INTEGER);
//							}
//						}
//
//						TranslatedExpAndType translatedExpAndType = translateArrayInitializerList(teBtArr,
//								maxSize, list, location, ++arrayBaseNum);						
//						values.addElement(translatedExpAndType);
//					}
//					else if(btTeBtArr instanceof StructOrUnionTypeEntry){
//						StructOrUnionTypeEntry structOrUnionTypeEntry = (StructOrUnionTypeEntry) btTeBtArr;
//						TranslatedExpAndType translatedExpAndType = translateStructInitializerList(teBtArr, list,
//								location);
//						values.addElement(translatedExpAndType);
//					}
//					else{
//						TranslatedExpAndType translatedExpAndType = translateArrayInitializerList(
//								teBtArr, -1, list, location, ++arrayBaseNum);
//						values.addElement(translatedExpAndType);
//
//						excessInitializers = true;
//					}			
//				}
//				else if(bt instanceof StructOrUnionTypeEntry){
//					StructOrUnionTypeEntry structOrUnionTypeEntry = (StructOrUnionTypeEntry) bt;
//					TranslatedExpAndType translatedExpAndType = translateStructInitializerList(
//							tpBaseType, list, location);
//					values.addElement(translatedExpAndType);
//				}
//				else{
//					TranslatedExpAndType translatedExpAndType = translateArrayInitializerList(
//							tpBaseType, -1, list, location, ++arrayBaseNum);
//					values.addElement(translatedExpAndType);
//					excessInitializers = true;
//				}				
//			}
//			count++;
//
//			initializerList = initializerList.getInitializerList();
//		}
//
//		if(initializerHasAllScalarElements){
//			TranslatedExpAndType translatedExpAndType = 
//				translateCompoundInitializerWithAllScalarElements(tpBaseType, values, location);
//			if(excessInitializers)
//				translatedExpAndType.setExcessInitializersInArrayInit(true);
//			return translatedExpAndType;
//		}
//
//		// Identify the base type on the lhs
//		TypeEntryWithAttributes baseType = null;
//		if(tpBaseType.getBasicType() instanceof ArrayTypeEntry){
//			ArrayTypeEntry baseArrType = (ArrayTypeEntry) tpBaseType.getBasicType();
//			baseType = baseArrType.getBaseTypeEntry();			
//		}
//		else{
//			baseType = tpBaseType;
//		}
//
//		TranslatedExpAndType translatedExpAndType = 
//			getTransExpAndTypeForSingleDimArrayInit(baseType, size, values, location);
//		if(excessInitializers)
//			translatedExpAndType.setExcessInitializersInArrayInit(true);
//		return translatedExpAndType;		
//	}
//
//	private TranslatedExpAndType getTransExpAndTypeForSingleDimArrayInit(TypeEntryWithAttributes baseType, 
//			int size, Vector<TranslatedExpAndType> values, SourceLocation location){
//
//		// Check if all the elements are the same type
//		boolean allElementsSameType = true;
//
//		int count = 0;
//		TypeEntryWithAttributes tpFirstElement = null;
//		if(values.size() > 0){			
//			for(TranslatedExpAndType tp : values){
//				if(count == 0)
//					tpFirstElement = tp.getTypeEntry();
//				else {
//					if(!(tpFirstElement.equals(tp.getTypeEntry()))){
//						allElementsSameType = false;
//					}
//				}
//
//				checkAssignmentCompatibility(baseType, tp.getTypeEntry(), location, 
//						SemanticAnalyzer.ASSIGNMENT_IN_DECLR_INIT_EXPR, AssignmentOperator.ASSIGN, null, -1);
//				count++;
//			}
//		}
//
//		if(size > -1  && count > size){			
//			ErrorHandler errorHandler = ErrorHandler.getInstance();
//
//			// If the excess initializer error message already exists, don't add it again
//			if(!errorHandler.errorOrWarningAlreadyExists(sourceFileName, location, ErrorHandler.ERROR_MSGS_ONLY, 
//					ErrorHandler.E_EXCESS_INITIALIZERS_IN_ARRAY_INIT)) 	{
//				// Add this error only if required; this might come up several times
//				errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_EXCESS_INITIALIZERS_IN_ARRAY_INIT);
//			}
//		}
//
//		// Create and return an array type		
//		ArrayTypeEntry arrayInit = new ArrayTypeEntry();
//		if(!allElementsSameType)
//			arrayInit.setBaseTypeEntry(baseType);
//		else
//			arrayInit.setBaseTypeEntry(tpFirstElement);
//
//		TypeEntryWithAttributes teDim = new TypeEntryWithAttributes();
//		teDim.setConst(true); teDim.setLiteralValue("" + count);
//		arrayInit.setDimension(teDim);
//
//		TypeEntryWithAttributes teRet = new TypeEntryWithAttributes();		
//		teRet.setBasicType(arrayInit);
//		teRet.setConst(true);
//
//		TranslatedExpAndType retValue = new TranslatedExpAndType();
//		retValue.setEntry(teRet);
//
//		return retValue;
//	}
//
//	private boolean allElementsOfSameType(Vector<TranslatedExpAndType> values){
//		// Check if all the elements are the same type
//		boolean allElementsSameType = true;
//		int count = 0;
//
//		TypeEntryWithAttributes tpFirstElement = null;
//
//		for(TranslatedExpAndType tp : values){
//			if(count == 0)
//				tpFirstElement = tp.getTypeEntry();
//			else {
//				if(!(tpFirstElement.equals(tp.getTypeEntry()))){
//					allElementsSameType = false;
//				}
//			}
//			count++;
//		}
//
//		return allElementsSameType;
//	}
//
//
//	/**
//	 * Checks if a symbol that should not be repeated in any context has actually been repeated.
//	 */
//	private boolean errorByRepeating(String name, SourceLocation location, VariableOrFunctionEntry newEntry, 
//			short declarationContext, boolean hasInitializer){
//
//		int newEntryCategory = newEntry.getCategory();
//
//		// Check if there is already an old entry in this environment with the same name
//		VariableOrFunctionEntry oldEntry = 
//			(VariableOrFunctionEntry)environments.getInstanceVariableTable().get(Symbol.symbol(name));
//		if(!environments.getInstanceVariableTable().keyInCurrentScope(Symbol.symbol(name)))
//			return false;
//
//
//		// The variable has been repeated
//		if(declarationContext == LOCAL_VARIABLE_CONTEXT){
//			// Its not ok to repeat -> probably a declaration inside a function or a parameter
//			// in a function defintion
//			if(oldEntry.getCategory() == VariableOrFunctionEntry.VARIABLE){
//				// Already declared as a variable, flag an error
//				errorHandler.addError(sourceFileName, location, name,
//						ErrorHandler.AI_SEE_DECLARATION + sourceFileName + ":" + oldEntry.getSourceLocation(),
//						ErrorHandler.E_VARIABLE_ALREADY_DEFINED);
//			}
//			else{
//				errorHandler.addError(sourceFileName, location, name, 
//						ErrorHandler.AI_SEE_DECLARATION + sourceFileName + ":" + oldEntry.getSourceLocation(),
//						ErrorHandler.E_FUNCTION_ALREADY_DEFINED);
//			}
//			return true;
//		}
//		else{
//			// Its ok to repeat -> probably an extern declaration or a parameter in a function declaration,
//			// but make sure the types are the same
//
//			int oldEntryCategory = oldEntry.getCategory();
//			if(newEntryCategory == VariableOrFunctionEntry.VARIABLE &&  
//					oldEntryCategory == VariableOrFunctionEntry.VARIABLE){
//				VariableEntry oldVar = (VariableEntry) oldEntry;
//				VariableEntry newVar = (VariableEntry) newEntry;
//				if(!(oldVar.getType().equals(newVar.getType()))){						
//					errorHandler.addError(sourceFileName, location, name,
//							ErrorHandler.AI_SEE_DECLARATION + sourceFileName + ":" + oldEntry.getSourceLocation(),
//							ErrorHandler.E_VARIABLE_ALREADY_DEFINED);						
//				}
//				else{
//					// ....also external declarations cannot be initialized more than once
//					if(!hasInitializer)
//						return true;
//
//					VariableOrFunctionEntry earlierEntry = 
//						(VariableOrFunctionEntry)environments.getInstanceVariableTable().get(Symbol.symbol(name));
//
//					VariableEntry earlierVarEntry = (VariableEntry) earlierEntry;
//					if(earlierVarEntry.getRegisterTemp() != null){
//						errorHandler.addError(sourceFileName, location, name,
//								null, ErrorHandler.E_VARIABLE_ALREADY_INITIALIZED);
//					}
//				}
//
//				return true;
//			}	
//			else if(newEntryCategory == VariableOrFunctionEntry.FUNCTION &&  
//					oldEntryCategory == VariableOrFunctionEntry.FUNCTION){
//
//				FunctionEntry oldVar = (FunctionEntry) oldEntry;
//				FunctionEntry newVar = (FunctionEntry) newEntry;
//				if(!(oldVar.getReturnType().equals(newVar.getReturnType()))){						
//					errorHandler.addError(sourceFileName, location, name,
//							ErrorHandler.AI_SEE_DECLARATION + sourceFileName + ":" + oldEntry.getSourceLocation(),
//							ErrorHandler.E_FUNCTION_ALREADY_DEFINED);						
//				}
//
//				return true;
//			}	
//			else
//				return false;
//		}
//
//	}
//
//	/**
//	 * Returns a list of variable type entry objects, each object obtained from the parameter information. Each
//	 * type entry is obtained the same way it is obtained in the translateDeclaration() function, but here, the methods
//	 * are a stripped down version of the translateDeclarator() function.
//	 * 
//	 */
//
//	private Vector<VariableEntry> getVarEntriesFromParamTypeList(ParamTypeList paramTypeList){
//
//		Vector<VariableEntry> paramEntries = new Vector<VariableEntry>();
//		if(paramTypeList == null){   // No parameters
//			return paramEntries; 
//		}
//
//		ParamList paramList = paramTypeList.getParamList();
//
//		while(true){    // Iterate through the param list
//			if(paramList == null) break;
//
//			VariableEntry newVariableEntry = null;
//			ParamDeclaration paramDecl = paramList.getParamDeclaration();
//			DeclarationSpecifiers declSpecs = paramDecl.getDeclarationSpecifiers();
//			Declarator declarator = paramDecl.getDeclarator();
//			AbstractDeclarator abstractDeclarator = paramDecl.getAbstractDeclarator();
//
//			TypeEntryWithAttributes  baseEntry = getBaseEntryFromSpecsList(declSpecs, false, false, false);
//			if(declarator != null){			
//				TranslatedDeclarator translatedDeclarator = translateDeclarator(declarator, true, true, false);
//				if(translatedDeclarator == null){ // Error, move on to the next declarator
//					paramList = paramList.getParamList();  // Get the next param list
//					continue;
//				}
//
//				SourceLocation location = new SourceLocation(declarator.getLineNum(), declarator.getPos());
//
//				VariableOrFunctionEntry newEntry = makeVariableOrFunctionEntry(translatedDeclarator, 
//						baseEntry, location);
//				newVariableEntry = (VariableEntry) newEntry;
//
//
//			}
//			else{   // Get from abstract declarator
//				if((abstractDeclarator != null)){
//					// TODO - Implement this later
//				}
//			}
//			paramEntries.addElement(newVariableEntry);
//
//			paramList = paramList.getParamList();  // Get the next param list
//		}
//
//		return paramEntries;
//	}
//
//
//	/**
//	 * Returns true if the function entry's signature matches that of the param type list passed in the second parameter
//	 * @param funcEntry
//	 * @param paramTypeListToTranslate
//	 * @return
//	 */
//	private boolean functionSignatureMatches(FunctionEntry funcEntry, ParamTypeList paramTypeListToTranslate){
//
//		Vector<VariableEntry> varEntriesFromParamList = getVarEntriesFromParamTypeList(paramTypeListToTranslate);
//		if(varEntriesFromParamList == null) // Error in declaring param list in function definition
//			return false;
//
//		Vector<VariableEntry> varEntries = funcEntry.getFormals();  // Get the formal parameters
//		if(varEntries == null) varEntries = new Vector<VariableEntry>();
//
//		int numEntries = varEntries.size();
//		int numVarsInParams = varEntriesFromParamList.size();
//		if(!funcEntry.isEndsWithEllipses()){
//			if(numEntries != numVarsInParams)
//				return false;
//		}
//		else{
//			if(numVarsInParams < numEntries)
//				return false;
//		}
//
//		// Check the signatures now
//		for(int i = 0; i < numEntries; i++){
//			VariableEntry varEntry = varEntries.elementAt(i);
//			TypeEntryWithAttributes tpWithAttrs =  varEntry.getType();
//
//			VariableEntry varEntryFromParam = varEntriesFromParamList.elementAt(i);
//			TypeEntryWithAttributes tpFromParamWithAttrs =  varEntryFromParam.getType();
//
//			if(!tpWithAttrs.equals(tpFromParamWithAttrs))
//				return false;
//		}
//
//		return true;
//	}
//
//	/**
//	 * @param specifierList
//	 * @return
//	 */
//	public TypeEntryWithAttributes getBaseEntryFromSpecsList(SpecifierListType specifierList,
//			boolean forExternalDecl, boolean isForSpecifierListInStructDecl, boolean isForTypedef){
//
//		TypeEntryWithAttributes typeEntryWithAttributes = new TypeEntryWithAttributes();
//
//		boolean isConst = false;
//		boolean isVolatile = false;
//		boolean isSigned = false;    // By default
//		boolean isUnsigned = false;
//		boolean isAuto = false;
//		boolean isExtern = false;
//		boolean isRegister = false;
//		boolean isStatic = false;
//		boolean isTypedef = false;
//		boolean isStructOrUnionSpec = false;
//		boolean isEnumSpec = false;
//		boolean isTypedefName = false;
//		boolean isInt  = false;
//		boolean isDouble  = false;
//		boolean isShort  = false;
//		boolean isLong  = false;
//		boolean isFloat  = false;
//		boolean isChar  = false;
//		boolean isVoid  = false;
//
//		BasicType newEntry = null;
//
//		StructOrUnionTypeEntry structOrUnionTypeEntry = null;  // 
//
//		int typeCount = 0;
//		Object lastSpecifier = null;   // The last specifier
//
//		int lineNum = -1; // Initialize line num to zero
//		int pos = -1;
//		int numStorageSpecs = 0;
//		SourceLocation location = null;
//		Vector<BasicType> typeQueue = new Vector<BasicType>();
//
//		// Iterate through the specifiers
//		while(true){
//			if(specifierList == null)
//				break;
//
//			TypeSpecifier ts = specifierList.getTypeSpecifier();
//			if(ts != null){				
//				lineNum = ts.getLineNum();
//
//				pos = ts.getPos();
//				location = new SourceLocation(lineNum, pos);
//
//				int tsType = ts.getType();
//				if(tsType == TypeSpecifier.CHAR){
//					typeCount++;
//					typeQueue.add(CharTypeEntry.getInstance());
//					isChar = true;
//					isSigned = true; // By default
//				}
//				else if(tsType == TypeSpecifier.DOUBLE){
//					typeCount++;
//					typeQueue.add(DoubleTypeEntry.getInstance());
//					isDouble = true;					
//
//					if(isSigned){  // This is signed; treat as int 
//						isInt = true;
//						isDouble = false;
//						errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_DOUBLE_CANNOT_BE_USED_WITH_SIGNED_SPEC);						
//					}
//					if(isUnsigned){ // This is unsigned; treat as int 
//						isInt = true;
//						isDouble = false;
//						errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_DOUBLE_CANNOT_BE_USED_WITH_UNSIGNED_SPEC);
//					}
//				}
//				else if(tsType == TypeSpecifier.ENUM_SPEC){
//					typeCount++;
//
//					EnumSpecifier enumSpecifier = (EnumSpecifier) ts;
//					EnumSpecTypeEntry enumSpecTypeEntry = translateEnumSpecifier(enumSpecifier);
//					isEnumSpec = true;	
//					typeQueue.add(enumSpecTypeEntry);
//
//				}
//				else if(tsType == TypeSpecifier.FLOAT){
//					typeCount++;
//					isFloat = true;
//					typeQueue.add(FloatTypeEntry.getInstance());
//					if(isSigned){
//						isFloat = false;
//						isInt = true;
//						errorHandler.addError(sourceFileName, location, "", "",  ErrorHandler.E_FLOAT_CANNOT_BE_USED_WITH_SIGNED_SPEC);
//					}
//					if(isUnsigned){
//						isFloat = false;
//						isInt = true;
//						errorHandler.addError(sourceFileName, location, "", "",  ErrorHandler.E_FLOAT_CANNOT_BE_USED_WITH_UNSIGNED_SPEC);	
//					}
//				}
//				else if(tsType == TypeSpecifier.INT){
//					typeCount++;
//					isInt = true;
//					typeQueue.add(IntTypeEntry.getInstance());
//				}
//				else if(tsType == TypeSpecifier.LONG){
//					typeCount++;
//					isLong = true;
//					typeQueue.add(LongTypeEntry.getInstance());
//				}
//				else if(tsType == TypeSpecifier.SHORT){
//					typeCount++;
//					isShort = true;
//					typeQueue.add(ShortTypeEntry.getInstance());
//				}
//				else if(tsType == TypeSpecifier.SIGNED){
//					isSigned = true;
//					typeQueue.add(IntTypeEntry.getInstance());
//					if(isFloat){
//						isSigned = false;
//						errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_FLOAT_CANNOT_BE_USED_WITH_SIGNED_SPEC);
//					}
//					if(isDouble){
//						isSigned = false;
//						errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_DOUBLE_CANNOT_BE_USED_WITH_SIGNED_SPEC);
//					}
//				}
//				else if(tsType == TypeSpecifier.STRUCT_OR_UNION_SPEC){
//					typeCount++;
//					StructOrUnionSpecifier structOrUnionSpecifier = (StructOrUnionSpecifier) ts;
//					structOrUnionTypeEntry = translateStructOrUnionSpecifier(structOrUnionSpecifier, forExternalDecl,
//							isForSpecifierListInStructDecl);
//					isStructOrUnionSpec = true;
//					typeQueue.add(structOrUnionTypeEntry);
//				}
//				else if(tsType == TypeSpecifier.TYPEDEF_NAME){
//					isTypedefName = true;
//					TypeDefName tdName = (TypeDefName) ts;
//					String name = tdName.getName();
//					Object entryObject =  environments.getInstanceTypeTable().get(Symbol.symbol(name));
//
//					if(entryObject instanceof TypeDefNameTypeEntry){
//						typeCount++;
//						TypeDefNameTypeEntry tdEntry = (TypeDefNameTypeEntry) entryObject;
//						typeQueue.add(tdEntry);
//					}
//					else{
//						errorHandler.addError(sourceFileName, location, name + ":", "", ErrorHandler.E_TYPE_NOT_DEFINED);
//					}
//
//					// If this is a typedef and more than one type specifier has been specified along with another
//					// typedef, regard it as error: eg: 'typedef unsigned ABCD EFGH;' 
//					if(isForTypedef  && typeQueue.size() > 1){  
//						errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_INVALID_TYPEDEF_DECLARATION);
//					} 					
//				}
//				else if(tsType == TypeSpecifier.UNSIGNED){
//					isUnsigned = true;
//					typeQueue.add(IntTypeEntry.getInstance());
//					if(isFloat){
//						isUnsigned = false;  // TODO confirm this
//						errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_FLOAT_CANNOT_BE_USED_WITH_UNSIGNED_SPEC);
//					}
//					if(isDouble){
//						isUnsigned = false;  // TODO confirm this
//						errorHandler.addError(sourceFileName, location,"", "", ErrorHandler.E_DOUBLE_CANNOT_BE_USED_WITH_UNSIGNED_SPEC);
//					}
//				}
//				else {// Must be void
//					typeCount++;
//					isVoid = true;	
//					typeQueue.add(VoidTypeEntry.getInstance());
//				}
//
//				lastSpecifier = ts;
//			}
//
//			TypeQualifier tq = specifierList.getTypeQualifier();
//			if(tq != null){
//				lineNum = tq.getLineNum();
//
//				pos = tq.getPos();
//				location = new SourceLocation(lineNum, pos);
//
//				int tqtype = tq.getType();
//				if(tqtype == TypeQualifier.CONST)
//					isConst = true;				
//				else  // Must be volatile
//					isVolatile = true;		
//
//				lastSpecifier = tq;				
//			}
//
//			StorageClassSpecifiers scs = specifierList.getStorageClassSpecifiers();
//			if(scs != null){
//				lineNum = scs.getLineNum();
//				pos = scs.getPos();
//				location = new SourceLocation(lineNum, pos);
//
//				int scstype = scs.getType();
//				if(scstype == StorageClassSpecifiers.AUTO)
//					isAuto = true;
//				else if(scstype == StorageClassSpecifiers.REGISTER)
//					isRegister = true;
//				else if(scstype == StorageClassSpecifiers.STATIC)
//					isStatic = true;
//				else if(scstype == StorageClassSpecifiers.EXTERN)
//					isExtern = true;
//				else 
//					isTypedef = true;
//
//				numStorageSpecs++;
//
//				lastSpecifier = scs;	
//			}
//
//			specifierList = specifierList.getNext();
//		}
//
//		// Create the base type based on information gathered above
//		boolean errorInIdentifyingType = false;
//		pos = 0;
//		location = new SourceLocation(lineNum, pos);
//
//		if(numStorageSpecs > 1){  // Declarations cannot have more than one storage specifier
//			errorHandler.addError(sourceFileName, location, "", "",   ErrorHandler.E_MORE_THAN_ONE_STORAGE_SPEC);
//		}
//
//		if(forExternalDecl){   // An external declaration should not be declared as auto or register
//			if(isAuto || isRegister){
//				errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_INVALID_STORAGE_SPEC_FOR_EXTERNAL_DEC);
//
//			}
//		}
//
//		if(typeCount != 1){  // Check number of types specified
//			if(typeCount > 1){  // Only one type should be specified	
//				if(!(typeCount == 2 && ((isInt && isShort) || (isInt && isLong))))
//					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_MORE_THAN_ONE_TYPE);
//			}
//			else{               // NO TYPE, If this is NOT an extern, at least one type should be specified 
//				if(!forExternalDecl){
//					if(!isForSpecifierListInStructDecl){
//						errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_NO_TYPE_SPECIFIED);
//						errorInIdentifyingType = true;
//					}
//				}
//			}
//		}
//
//		// TODO: Are there other checks to see if two specifiers cannot come together? Eg.: const, volatile?
//
//		if(!errorInIdentifyingType){
//
//			// Looks good so far, create the base type
//			if(typeQueue.size() > 0){
//				newEntry = typeQueue.elementAt(0);
//			}
//			else  // Must be extern declaration with no type specified, so default to IntType
//				newEntry = IntTypeEntry.getInstance();	
//
//			typeEntryWithAttributes.setBasicType(newEntry);
//
//			// Assign other adornments
//			typeEntryWithAttributes.setConst(isConst);
//			typeEntryWithAttributes.setVolatile(isVolatile);
//			typeEntryWithAttributes.setExtern(isExtern);
//			typeEntryWithAttributes.setRegister(isRegister);
//			typeEntryWithAttributes.setUnsigned(isUnsigned);
//			typeEntryWithAttributes.setStatic(isStatic);
//			typeEntryWithAttributes.setTypedef(isTypedef);
//			typeEntryWithAttributes.setAuto(isAuto);
//			typeEntryWithAttributes.setVoid(isVoid);
//			typeEntryWithAttributes.setTypedefName(isTypedefName);
//		}
//
//		return typeEntryWithAttributes;
//	}
//
//	public EnumSpecTypeEntry translateEnumSpecifier(EnumSpecifier enumSpecifier){		
//		String tag = enumSpecifier.getName();
//
//		SourceLocation srcLocation = new SourceLocation(enumSpecifier.getLineNum(), enumSpecifier.getPos());
//		EnumList enumList = enumSpecifier.getEnumList();
//
//		if(tag != null){			
//			if(environments.getInstanceTypeTable().keyInCurrentScope(Symbol.symbol(tag))){  // Tag already defined
//				EnumSpecTypeEntry oldEntry = (EnumSpecTypeEntry)environments.getInstanceTypeTable().get(Symbol.symbol(tag));
//				if(enumList != null){  // Error				
//					errorHandler.addError(sourceFileName, srcLocation, tag + ":",
//							ErrorHandler.AI_SEE_DECLARATION + sourceFileName + ":" + oldEntry.getSourceLocation(),
//							ErrorHandler.E_ENUM_TYPE_ALREADY_DEFINED);
//
//					return null;
//				}	
//				else {  // Return existing
//					return oldEntry;
//				}
//			}					
//		}
//
//		List<String>  enumNames = new ArrayList<String>();
//
//		while(enumList != null){
//			Enumerator enumerator = enumList.getEnumarator();
//			String name = enumerator.getName();
//			enumNames.add(name);
//
//			// Check if the name has been repeated
//			if(environments.getInstanceVariableTable().keyInCurrentScope(Symbol.symbol(name))){
//				// This name has been repeated
//
//				VariableOrFunctionEntry oldEntry = 
//					(VariableOrFunctionEntry)environments.getInstanceVariableTable().get(Symbol.symbol(name));
//
//				errorHandler.addError(sourceFileName, srcLocation, name + ":",
//						ErrorHandler.AI_SEE_DECLARATION + sourceFileName + ":" + oldEntry.getSourceLocation(),
//						ErrorHandler.E_ENUMERATOR_ALREADY_DEFINED);
//
//			}
//			else{				
//				VariableEntry varEntry = new VariableEntry();
//				TypeEntryWithAttributes teWithAttrs = new TypeEntryWithAttributes();
//				teWithAttrs.setBasicType(IntTypeEntry.getInstance());
//				teWithAttrs.setEnumConstant(true);
//				varEntry.setType(teWithAttrs);
//				varEntry.setName(name);
//				varEntry.setSourceLocation(srcLocation);
//				environments.getInstanceVariableTable().put(Symbol.symbol(name), varEntry);
//			}
//
//			// If the enumerator has a initialization value, check if it is an integer
//			ConstExpr constExpr = enumerator.getInitValue();
//			if(constExpr != null){
//				TranslatedExpAndType teInitValue = translateConditionalExpr(constExpr.getCondExpr());
//				TypeEntryWithAttributes teWithAttrs = teInitValue.getTypeEntry();
//				BasicType tp = teWithAttrs.getBasicType();
//
//				if(!(tp == IntTypeEntry.getInstance() || tp == CharTypeEntry.getInstance() ||
//						tp == ShortTypeEntry.getInstance() || tp == LongTypeEntry.getInstance())){
//					SourceLocation location = new SourceLocation(enumerator.getLineNum(), enumerator.getPos());
//					errorHandler.addError(sourceFileName, location, "", "",  ErrorHandler.E_ENUM_INIT_VAL_NOT_INTEGER);				
//				}
//			}
//
//			enumList = enumList.getEnumList();
//
//		}		
//
//		// Add to types
//		EnumSpecTypeEntry enumSpecTypeEntry = new EnumSpecTypeEntry(tag, enumNames, srcLocation);
//		if(tag != null && !environments.getInstanceVariableTable().keyInCurrentScope(Symbol.symbol(tag)))
//			environments.getInstanceTypeTable().put(Symbol.symbol(tag), enumSpecTypeEntry);	
//
//		return enumSpecTypeEntry;
//	}
//
//	public void translateFunctionDef(FunctionDef functionDef){
//
//		DeclarationSpecifiers declarationSpecifiers = functionDef.getDeclSpecifiers();
//		TypeEntryWithAttributes  teWithAttributes = 
//			getBaseEntryFromSpecsList(declarationSpecifiers, true, false, false);		
//
//		// Now check the declarator
//		Declarator declarator = functionDef.getDeclarator();
//		TranslatedDeclarator translatedDeclarator = translateDeclarator(declarator, false, true, false);
//		String name = null;
//
//		FunctionEntry funcTypeEntry = null;
//		ParamTypeList paramTypeListToTranslate = null;
//		if(translatedDeclarator != null){
//			// No error in declarator, continue
//			SourceLocation location = new SourceLocation(functionDef.getLineNum(), functionDef.getPos());
//			paramTypeListToTranslate = translatedDeclarator.getParamTypeList();
//			name = translatedDeclarator.getName();
//
//			// Check the validity of the function name
//			VariableOrFunctionEntry varOrFuncEntry = 
//				(VariableOrFunctionEntry)environments.getInstanceVariableTable().get(Symbol.symbol(name));
//
//			boolean funcNameError = false;
//			if(definedFunctions.contains(name)){ 
//				// This function has already been defined
//				errorHandler.addError(sourceFileName, location,  name,
//						ErrorHandler.AI_SEE_DECLARATION + sourceFileName + ":" + varOrFuncEntry.getSourceLocation(),
//						ErrorHandler.E_FUNCTION_ALREADY_DEFINED);
//				funcNameError = true;
//			}
//			else{				
//
//				// If this name has already been declared, check if it is a variable or function 
//				if(environments.getInstanceVariableTable().keyInCurrentScope(Symbol.symbol(name))) {
//					// name has already been declared, check if it is a variable or a function
//					if(varOrFuncEntry.getCategory() == VariableOrFunctionEntry.VARIABLE){
//						// Already declared as a variable, flag an error
//						errorHandler.addError(sourceFileName, location,  name, 
//								ErrorHandler.AI_SEE_DECLARATION + sourceFileName + ":" + varOrFuncEntry.getSourceLocation(), 
//								ErrorHandler.E_VARIABLE_ALREADY_DEFINED);
//						funcNameError = true;
//					}
//					else{
//						// Declared as a function already; check the signature
//						FunctionEntry funcEntry = (FunctionEntry) varOrFuncEntry;
//						if(!functionSignatureMatches(funcEntry, paramTypeListToTranslate)){
//							errorHandler.addError(sourceFileName, location,name,
//									ErrorHandler.AI_SEE_DECLARATION + sourceFileName + ":" + varOrFuncEntry.getSourceLocation(),
//									ErrorHandler.E_FUNCTION_SIGNATURE_MISMATCH);
//							funcNameError = true;
//						}
//					}
//				}
//			}
//
//			if(!funcNameError && !environments.getInstanceVariableTable().keyInCurrentScope(Symbol.symbol(name))){
//				funcTypeEntry =  (FunctionEntry)makeVariableOrFunctionEntry(translatedDeclarator, teWithAttributes,  location);
//				addToEnvironment(funcTypeEntry);  // No error, make entry
//			}
//
//			currentFunctionName = name;
//
//			ActivationFrameFactory activationFrameFactory = new ActivationFrameFactory();
//			ActivationFrame currentActivationFrame = activationFrameFactory.getActivationFrame(targetMachineName);
//			currentActivationFrame.setSourceName(name);
//			currentActivationFrame.setLabel(new Label());	
//			currentActivationFrame.setAlignData(true);
//
//			currentFunctionDef.setActivationFrame(currentActivationFrame);
//
//		}
//
//		// Now check and translate the function body
//		environments.getInstanceVariableTable().beginScope();
//		environments.getInstanceTypeTable().beginScope();
//
//		Vector<VariableEntry > paramEntries = null;
//		if(paramTypeListToTranslate != null){
//			// No error in declarator and there are parameters to analyze too
//
//			paramEntries =  translateParamTypeList(paramTypeListToTranslate, true);			
//		}
//
//		if(funcTypeEntry != null)
//			funcTypeEntry.setFormals(paramEntries);  // Set the list of variable entries from the param list		
//
//		// Now the compound statement
//		CompoundStatement cmpStmt = functionDef.getCompoundStmt();
//		AssemType paramsAsmt = translateParameterPassing(paramEntries);
//		AssemType bodyAsmt = translateCompoundStatement(cmpStmt, true);
//
//		// Create the assembly tree for the function
//		Stack<AssemType> functionAssemTreeStack = new Stack<AssemType>();
//
//		/*if(paramEntries != null){
//			AssemType paramsAssemTree = translateParamVariablesToAssemblyTree(paramEntries);
//			functionAssemTreeStack.push(paramsAssemTree);
//		}*/
//
//		if(paramsAsmt != null)
//			functionAssemTreeStack.push(paramsAsmt);
//		functionAssemTreeStack.push(bodyAsmt);
//
//		AssemType functionAssemTree = translatingMediator.translateSeqStatement(functionAssemTreeStack);
//
//		environments.getInstanceVariableTable().endScope();   // Close the scopes
//		environments.getInstanceTypeTable().endScope();
//
//		paramTypeListToTranslate = null; // Reset the param type list to translate
//
//		if(funcTypeEntry != null)  // No error, add to the list of defined functions
//			definedFunctions.add(funcTypeEntry.getName());  
//
//		currentFunctionDef.setExternalDeclTree(functionAssemTree);
//		currentFunctionDef.setName(name);	
//		currentFunctionDef.setMainFunction(currentFuncIsMain(teWithAttributes, paramEntries));
//	}
//
//	/**
//	 * Returns true if the function being analyzed in the main function; false otherwise.
//	 * @return
//	 */
//	private boolean currentFuncIsMain(TypeEntryWithAttributes teWithAttrs, 
//			Vector<VariableEntry> paramEntries){
//		if(currentFunctionName == null)  // Must not happen
//			return false;
//
//		if(!currentFunctionName.equals("main"))   // function name must be main
//			return false;
//
//		// The return must return an int or a void
//		if(!(teWithAttrs.getBasicType() == IntTypeEntry.getInstance() || 
//				teWithAttrs.getBasicType() == VoidTypeEntry.getInstance()))
//			return false;
//
//		if(paramEntries == null)
//			paramEntries = new Vector<VariableEntry>();
//
//		// The first parameter must be int and the second must be char * []
//		if(paramEntries == null || (!(paramEntries.size() == 2 || paramEntries.size() == 0)))
//			return false;
//
//		if(paramEntries.size() == 0)
//			return true;
//
//		VariableEntry varEntry1 = paramEntries.elementAt(0);
//		VariableEntry varEntry2 = paramEntries.elementAt(1);
//		if(varEntry1.getType().getBasicType() != IntTypeEntry.getInstance()) // First param must be an integer			
//			return false;
//		// Second param must be char *[]
//		BasicType secondParamType = varEntry2.getType().getBasicType();
//		if(!(secondParamType instanceof ArrayTypeEntry))
//			return false;
//		ArrayTypeEntry secondParamArray = (ArrayTypeEntry) secondParamType;
//		BasicType arrayElemType = secondParamArray.getBaseTypeEntry().getBasicType();
//		if(!(arrayElemType instanceof PointerTypeEntry ))
//			return false;
//
//		BasicType pointerBaseType = ((PointerTypeEntry) arrayElemType).getBaseTypeEntry().getBasicType();
//		if(pointerBaseType != CharTypeEntry.getInstance())
//			return false;
//
//		return true;
//
//	}
//
//	/*
//	private AssemType translateParamVariablesToAssemblyTree(Vector<VariableEntry > paramEntries){
//		Vector<Temp> tempParamList = new Vector<Temp>();
//
//		for(VariableEntry entry: paramEntries){
//			Temp varTemp = entry.getRegisterTemp();
//			tempParamList.add(varTemp);			
//		}
//
//		return translatingMediator.translateParamVariables(tempParamList);
//	}
//	 */
//
//	private Vector<VariableEntry> translateParamTypeList(ParamTypeList paramTypeList, 
//			boolean isFunctionDefContext ){
//
//		Vector<VariableEntry> paramEntries = new Vector<VariableEntry>();
//		ParamList paramList = paramTypeList.getParamList();
//
//		Stack<VariableEntry> paramEntriesStack = new Stack<VariableEntry>();
//
//		while(true){    // Iterate through the param list
//			if(paramList == null) break;
//
//			ParamDeclaration paramDecl = paramList.getParamDeclaration();
//			DeclarationSpecifiers declSpecs = paramDecl.getDeclarationSpecifiers();
//			Declarator declarator = paramDecl.getDeclarator();
//			AbstractDeclarator abstractDeclarator = paramDecl.getAbstractDeclarator();
//
//			TypeEntryWithAttributes  teWithAttributes = getBaseEntryFromSpecsList(declSpecs, false, false, false);
//			if(declarator != null){
//				TranslatedDeclarator translatedDeclarator = 
//					translateDeclarator(declarator, true, isFunctionDefContext, false);
//				if(translatedDeclarator == null){  // Error in declarator, move on to the next param
//					paramList = paramList.getParamList(); 
//					continue;
//				}
//
//				String name = translatedDeclarator.getName();
//
//				VariableEntry newEntry = null;				
//				SourceLocation location = new SourceLocation(paramTypeList.getLineNum(), paramTypeList.getPos());
//
//				if(isFunctionDefContext){					
//					newEntry = (VariableEntry) makeVariableOrFunctionEntry(translatedDeclarator, teWithAttributes, location);					
//					if(errorByRepeating(name, location, newEntry, LOCAL_VARIABLE_CONTEXT, false)){
//						paramList = paramList.getParamList();
//						continue;
//					}
//					else 
//						addToEnvironment(newEntry);  // No error, make entry
//
//				}
//				else{
//					newEntry = (VariableEntry) makeVariableOrFunctionEntry(translatedDeclarator, teWithAttributes, location);
//				}
//
//				// If this is an array, check the declaration of the array 
//				TypeEntryWithAttributes paramTe = newEntry.getType();
//				if(paramTe.getBasicType() instanceof ArrayTypeEntry){
//					arrayDeclaratorIsOk((ArrayTypeEntry) paramTe.getBasicType(), null, name, 
//							SemanticAnalyzer.ARRAY_PARAM_ANALYSIS, location);
//				}
//
//				//paramEntriesStack.push(newEntry);	
//				paramEntries.addElement(newEntry);
//			}
//			else{
//				if((abstractDeclarator != null)){
//					// TODO - Implement this later
//				}
//			}
//
//			paramList = paramList.getParamList();  // Get the next param list
//		}
//
//		// Place in correct order
//		while(!paramEntriesStack.isEmpty()){
//			VariableEntry newEntry = paramEntriesStack.pop();
//			paramEntries.addElement(newEntry);
//		}
//
//		return paramEntries;
//	}
//
//	// STRUCTS TRANSLATION
//
//	private StructOrUnionTypeEntry translateStructOrUnionSpecifier(StructOrUnionSpecifier structOrUnionSpecifier,
//			boolean structDeclIsExternal, boolean isForSpecifierListInStructDecl){
//		StructOrUnion structOrUnion = structOrUnionSpecifier.getStructOrUnion();
//		boolean isStruct = true;
//		if(structOrUnion.getType() == StructOrUnion.UNION)
//			isStruct = false;
//
//		StructDeclarationList structDeclarationList = structOrUnionSpecifier.getStructDecList();
//
//		String tag = structOrUnionSpecifier.getName();
//
//		LinkedHashMap<String, TypeEntryWithAttributes> namesAndTypes 
//		= new LinkedHashMap<String, TypeEntryWithAttributes>();
//
//		SourceLocation srcLocation = new SourceLocation(structOrUnion.getLineNum(), structOrUnion.getPos());
//
//		if(structDeclarationList == null){
//			// Check if the tag already exists
//			StructOrUnionTypeEntry existingType = (StructOrUnionTypeEntry)environments.getInstanceTypeTable().get(Symbol.symbol(tag));
//			if(existingType == null){
//				if(tag != null){
//					if(!isForSpecifierListInStructDecl)
//						errorHandler.addError(sourceFileName, srcLocation, tag + ":", "", ErrorHandler.E_STRUCT_NOT_DEFINED);
//				}
//				return new StructOrUnionTypeEntry(tag, isStruct, null, srcLocation);
//			}
//			else{
//				return existingType;
//			}
//		}
//
//		while(structDeclarationList != null){
//			StructDeclaration structDeclaration = structDeclarationList.getStructDeclaration();
//
//			SpecifierQualifierList specifierQualifierList = structDeclaration.getSpecifierQualifierList();
//			TypeEntryWithAttributes teStructDeclBase =
//				getBaseEntryFromSpecsList(specifierQualifierList, false, true, false);
//
//			StructDeclaratorList structDeclaratorList = structDeclaration.getStructDeclaratorList();
//
//			translateStructDeclaratorList(structDeclaratorList, teStructDeclBase, namesAndTypes,
//					structDeclarationList.getStructDeclarationListNext() == null);
//
//			structDeclarationList = structDeclarationList.getStructDeclarationListNext();	
//		}		
//
//		// If the struct does not have a tag name, create one
//		if(tag == null){
//			tag = getNameForTaglessStruct(10);
//		}
//
//		// Got all the members, create new struct type and create a type if necessary
//
//		StructOrUnionTypeEntry structOrUnionTypeEntry = new StructOrUnionTypeEntry(tag,isStruct, 
//				namesAndTypes, srcLocation);
//
//		if(environments.getInstanceTypeTable().keyInCurrentScope(Symbol.symbol(tag))){
//			// This name has been repeated
//			TypeEntry oldEntry =  (TypeEntry)environments.getInstanceTypeTable().get(Symbol.symbol(tag));
//			if(oldEntry instanceof StructOrUnionTypeEntry){
//				errorHandler.addError(sourceFileName, srcLocation, tag,
//						ErrorHandler.AI_SEE_DECLARATION + sourceFileName + ":" + oldEntry.getSourceLocation(),
//						ErrorHandler.E_STRUCT_ALREADY_DEFINED);
//			}
//			else{
//				return null;
//			}
//		}
//		else
//			environments.getInstanceTypeTable().put(Symbol.symbol(tag), structOrUnionTypeEntry);
//
//		return structOrUnionTypeEntry;
//
//	}
//
//	/**
//	 * Generates a random name for a struct declared without a tag. The name is a unique random number.
//	 * @param factor
//	 * @return
//	 */
//	private String getNameForTaglessStruct(int factor){
//		HashSet<String> existingKeys = new HashSet<String>();
//		int attempt = (int) (factor * Math.random());
//		String seed = "" + attempt;
//		while(existingKeys.size() < factor){
//			if(!environments.getInstanceTypeTable().keyInCurrentScope(Symbol.symbol(seed))){
//				return seed;
//			}
//			else{
//				attempt = (int) (factor * Math.random());
//				seed = "" + attempt;
//				existingKeys.add(seed);
//			}
//		}
//
//		if(factor < 1000000){
//			// TODO log a warning here
//			return getNameForTaglessStruct(factor * 10);
//		}
//		else{
//			return seed;
//		}
//	}
//
//	private void translateStructDeclaratorList(StructDeclaratorList structDeclaratorList,
//			TypeEntryWithAttributes baseEntry, LinkedHashMap<String, TypeEntryWithAttributes> namesAndTypes, 
//			boolean isLastMemberList){
//
//		List<TypeEntryWithAttributes> membersInDeclaratorList = new ArrayList<TypeEntryWithAttributes>();
//
//
//		SourceLocation location = new SourceLocation(structDeclaratorList.getLineNum(), 
//				structDeclaratorList.getPos());		
//
//		while(true){
//			if(structDeclaratorList == null)
//				break;
//
//			StructDeclarator structDeclarator = structDeclaratorList.getStructDeclarator();
//			Declarator declarator = structDeclarator.getDeclarator();
//			ConstExpr constExpr = structDeclarator.getBitFieldValue();
//
//			if(constExpr != null && declarator == null){
//				/*	// This is a bitfield; check the type of the declarators found so far (available in 
//				// membersInDeclaratorList) against the bit-field
//				TypeEntryWithAttributes teConstExpr = translateConditionalExpr(constExpr.getCondExpr()).getTypeEntry();
//
//				BasicType valBasicType = teConstExpr.getBasicType();
//				if((valBasicType == IntTypeEntry.getInstance() || valBasicType == ShortTypeEntry.getInstance()
//					|| valBasicType == CharTypeEntry.getInstance() || valBasicType == LongTypeEntry.getInstance())){
//					if(teConstExpr.getLiteralValue() == null){ // Is an expression
//						errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_BITFIELD_NOT_CONSTANT);
//					}
//				}
//				else{
//					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_BITFIELD_TYPE_IS_INVALID);
//				}		
//
//				structDeclaratorList = structDeclaratorList.getStructDeclaratorListNext(); 
//				continue;
//				 */
//			}
//
//			TranslatedDeclarator translatedDeclarator = translateDeclarator(declarator, false, false, true);
//			if(translatedDeclarator == null){ // Error, move on to the next declarator
//				structDeclaratorList = structDeclaratorList.getStructDeclaratorListNext(); 
//				continue;
//			}
//
//			VariableOrFunctionEntry varOrFuncEntry = getVarOrFunctionEntryFromTranslatedDeclarator(translatedDeclarator, location);
//			String name = translatedDeclarator.getName();
//
//			boolean memberError = false;
//			if(namesAndTypes.containsKey(name)){
//				errorHandler.addError(sourceFileName, location, name + ":", "", ErrorHandler.E_DUPLICATE_MEMBER_NAME);				
//				memberError = true;
//			}
//
//			if(varOrFuncEntry instanceof FunctionEntry){
//				errorHandler.addError(sourceFileName, location, name + ":", "", ErrorHandler.E_FUNCTION_AS_STRUCT_MEMBER);				
//				memberError = true;
//				return;
//			}
//
//			VariableEntry memberEntry = (VariableEntry) varOrFuncEntry;
//			baseEntry.setIsLValue(true);			
//			TypeEntryWithAttributes teType = memberEntry.getType();
//			// Update the basic type of the variable entry from the type entry with attributes object passed			
//			if(teType == null)
//				memberEntry.setType(baseEntry);
//			else
//				TypeEntryWithAttributes.updateBasicType(teType, baseEntry);
//
//
//			BasicType btOfMember = teType.getBasicType(); 
//			if(btOfMember instanceof StructOrUnionTypeEntry){
//				StructOrUnionTypeEntry btStructOrUnionType = (StructOrUnionTypeEntry)btOfMember;
//				if(environments.getInstanceTypeTable().get(Symbol.symbol(btStructOrUnionType.getTag())) == null){
//					errorHandler.addError(sourceFileName, location, name + ":", "", ErrorHandler.E_STRUCT_MEMBER_HAS_UNKNOWN_TYPE);				
//					memberError = true;
//				}
//			}
//
//			if(memberError){
//				structDeclaratorList = structDeclaratorList.getStructDeclaratorListNext(); 
//				continue;
//			}
//
//			if(btOfMember instanceof ArrayTypeEntry){  // Check if the array declarator is ok
//				ArrayTypeEntry arrayType = (ArrayTypeEntry) btOfMember;
//				int numDim = 0;
//				TypeEntryWithAttributes teFirstDim = arrayType.getDimension();
//
//				// If this is a multidimensional array, make sure except the first row, the other 
//				// dimensions must be specified
//				boolean baseTypeDimtSpecified = true;
//				while(true){
//					numDim++;
//					TypeEntryWithAttributes baseTeWithAttributes = arrayType.getBaseTypeEntry();
//					if(baseTeWithAttributes.getBasicType() instanceof ArrayTypeEntry){
//						arrayType =  (ArrayTypeEntry)baseTeWithAttributes.getBasicType();
//
//						if(arrayType.getDimension() == null){  // No 
//							baseTypeDimtSpecified = false;
//							break;							
//						}				
//					}
//					else
//						break;
//				}
//
//				// If the size of the first dimension is not specified, and this is NOT the last
//				// member, flag an error
//				if(teFirstDim == null){
//					if(!(isLastMemberList && structDeclaratorList.getStructDeclaratorListNext() == null)) // Not the last member
//						errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_ARRAY_SIZE_NOT_SPECIFIED);
//				}
//
//				// If this is a multidimensional array and the size of ANY of the inner dimensions are not specified,
//				// flag an error
//				if(numDim == 1){					
//					if(!baseTypeDimtSpecified) // Not the last member
//						errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_ARRAY_SIZE_NOT_SPECIFIED);
//				}
//
//			}	
//
//			namesAndTypes.put(name, teType);
//
//			membersInDeclaratorList.add(teType);
//
//			structDeclaratorList = structDeclaratorList.getStructDeclaratorListNext();  // Get next
//		}
//	}
//
//	private TranslatedExpAndType translateStructInitializerList(TypeEntryWithAttributes tpBaseType,
//			InitializerList initializerList, SourceLocation location){
//
//		int count = 0;
//		LinkedHashMap<String, TypeEntryWithAttributes> membersAndTypes 
//		= new LinkedHashMap<String, TypeEntryWithAttributes>();
//
//		BasicType bt = tpBaseType.getBasicType();
//		if(!(bt instanceof StructOrUnionTypeEntry)){
//			errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_EXCESS_INITIALIZERS_IN_STRUCT_INIT);
//			return null;
//		}
//
//		StructOrUnionTypeEntry structOrUnionTypeEntry = (StructOrUnionTypeEntry) bt;
//		int numMembers = structOrUnionTypeEntry.getNumMembers();
//		if(numMembers <= 0){
//			if(structOrUnionTypeEntry.isStruct())
//				errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_EXCESS_INITIALIZERS_IN_STRUCT_INIT);
//			else
//				errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_EXCESS_INITIALIZERS_IN_UNION_INIT);
//			return null;
//		}
//
//		Vector<Initializer> allInitializers = new Vector<Initializer>();
//		boolean initializerHasAllScalarElements = true;
//		Vector<TranslatedExpAndType> values = new Vector<TranslatedExpAndType>();
//
//		while(initializerList != null){
//			Initializer initializer = initializerList.getInitializer();
//			if(initializer != null){
//				allInitializers.add(initializer);
//				initializerList = initializerList.getInitializerList();
//			}
//			AssignmentExpr assignmentExpr = initializer.getInitExpr();
//			if(assignmentExpr != null){
//				TranslatedExpAndType translatedExpAndType = translateAssignmentExpr(assignmentExpr);
//				translatedExpAndType.setSourceLocation(new SourceLocation(assignmentExpr.getLineNum(),
//						assignmentExpr.getPos()));
//				values.addElement(translatedExpAndType);
//			}
//			else{
//				initializerHasAllScalarElements = false;
//			}
//		}
//
//		if(initializerHasAllScalarElements){
//			TranslatedExpAndType teExpr = 
//				translateCompoundInitializerWithAllScalarElements(tpBaseType, values, location);
//
//			if(teExpr.isExcessInitializersInStructnit()){
//				if(structOrUnionTypeEntry.isStruct())
//					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_EXCESS_INITIALIZERS_IN_STRUCT_INIT);
//				else
//					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_EXCESS_INITIALIZERS_IN_UNION_INIT);
//			}
//
//			return teExpr;
//		}
//
//		if(allInitializers.size() == 0){
//			if(structOrUnionTypeEntry.isStruct())
//				errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_EXCESS_INITIALIZERS_IN_STRUCT_INIT);
//			else
//				errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_EXCESS_INITIALIZERS_IN_UNION_INIT);
//			return null;
//		}
//
//		// Iterate through the member list and for each member, check against the type of the
//		// corresponding initializer
//		Vector<String> memberNames = structOrUnionTypeEntry.getMemberNames();
//
//		boolean isStruct = structOrUnionTypeEntry.isStruct();
//		// If this is a union, make sure there is no more than one initializer
//		if(!isStruct){  // This is a union
//			if(memberNames.size() > 0 && allInitializers.size() > 1){					
//				errorHandler.addError(sourceFileName, location, 
//						"" , "", ErrorHandler.E_EXCESS_INITIALIZERS_IN_UNION_INIT);	
//			}
//		}
//
//		for(String name: memberNames){
//			TypeEntryWithAttributes memberType = structOrUnionTypeEntry.getMemberType(name);
//			if(count < allInitializers.size()){
//				BasicType btMember = memberType.getBasicType();
//				Initializer initializer = allInitializers.elementAt(count);
//
//				if(btMember instanceof ArrayTypeEntry){
//					// Member is an array, check the initializer
//					AssignmentExpr assignmentExpr = initializer.getInitExpr();
//					if(assignmentExpr != null){
//						// Initializer is not an array initializer; flag an error
//						errorHandler.addError(sourceFileName, location, 
//								name + ": " , "", ErrorHandler.E_INVALID_INITIALIZATION_OF_MEMBER);						
//					}
//					else{
//						// Initializer is an array initializer; translate it
//
//						ArrayTypeEntry btLhsArr = (ArrayTypeEntry) btMember;
//
//						int maxSize = -1;  // No size specified in declaration
//						TypeEntryWithAttributes teDim = btLhsArr.getDimension();
//						if(teDim != null){
//							try{
//								maxSize = Integer.parseInt(teDim.getLiteralValue());
//							}
//							catch(NumberFormatException nfe){}
//						}
//
//						InitializerList list = initializer.getInitializerList();
//						TranslatedExpAndType translatedExpAndType = 
//							translateArrayInitializerList(memberType, maxSize, list, location, 0);
//						if(translatedExpAndType != null){
//							checkAssignmentCompatibility(memberType, translatedExpAndType.getTypeEntry(), location,
//									ASSIGNMENT_IN_STRUCT_MEMBER_INIT, AssignmentOperator.ASSIGN, null, -1);
//
//							membersAndTypes.put(name, translatedExpAndType.getTypeEntry());
//						}						
//					}
//				}
//				else if(btMember instanceof StructOrUnionTypeEntry){
//					// Member is a structure, check the initializer		
//					StructOrUnionTypeEntry strOrUnionTypeEntry = (StructOrUnionTypeEntry) btMember;
//					AssignmentExpr assignmentExpr = initializer.getInitExpr();
//					if(assignmentExpr != null){
//						TranslatedExpAndType translatedExpAndType = translateAssignmentExpr(assignmentExpr);
//						errorHandler.addError(sourceFileName, location, 
//								name + ": " , "", ErrorHandler.E_INVALID_INITIALIZATION_OF_MEMBER);	
//					}
//					else{
//						InitializerList list = initializer.getInitializerList();
//						TranslatedExpAndType translatedExpAndType = 
//							translateStructInitializerList(memberType, list,location);
//						if(translatedExpAndType != null){
//							checkAssignmentCompatibility(memberType, translatedExpAndType.getTypeEntry(), location,
//									ASSIGNMENT_IN_STRUCT_MEMBER_INIT, AssignmentOperator.ASSIGN, null, -1);
//
//							membersAndTypes.put(name, translatedExpAndType.getTypeEntry());
//						}	
//					}
//				}
//				else{					
//					// Member is neither an array nor a structure, check the initializer
//					AssignmentExpr assignmentExpr = initializer.getInitExpr();
//					if(assignmentExpr != null){
//						TranslatedExpAndType translatedExpAndType = translateAssignmentExpr(assignmentExpr);						
//						if(translatedExpAndType != null){
//							checkAssignmentCompatibility(memberType, translatedExpAndType.getTypeEntry(), location,
//									ASSIGNMENT_IN_STRUCT_MEMBER_INIT, AssignmentOperator.ASSIGN, null, -1);
//
//							membersAndTypes.put(name, translatedExpAndType.getTypeEntry());
//						}
//					}
//					else{
//						// Assume that this is an array and flag an error						
//						errorHandler.addError(sourceFileName, location, 
//								name + ": " , "", ErrorHandler.E_EXCESS_INITIALIZERS_IN_ARRAY_INIT);	
//					}
//				}
//			}			
//			count++;
//		}
//
//		// If any initializers are left out and the structure is a struct (not a union), they are extra.
//		if(isStruct && count < allInitializers.size()){
//			errorHandler.addError(sourceFileName, location, "" , "", 
//					ErrorHandler.E_EXCESS_INITIALIZERS_IN_STRUCT_INIT);	
//		}
//
//		// Create and return a struct type		
//		StructOrUnionTypeEntry structOrUnitTeFromInit = 
//			new StructOrUnionTypeEntry(structOrUnionTypeEntry.getTag(), structOrUnionTypeEntry.isStruct(),
//					membersAndTypes, location);
//
//		TypeEntryWithAttributes teRet = new TypeEntryWithAttributes();		
//		teRet.setBasicType(structOrUnitTeFromInit);
//		teRet.setConst(true);
//
//		TranslatedExpAndType retValue = new TranslatedExpAndType();
//		retValue.setEntry(teRet);
//
//		return retValue;
//
//	}	
//
//	// THE STATEMENTS
//	private AssemType translateStatement(Statement statement){	
//
//		if(statement instanceof CompoundStatement) {
//			CompoundStatement compoundStatement = (CompoundStatement) statement;
//
//			return translateCompoundStatement(compoundStatement, false);
//		}
//		else if(statement instanceof ExprStatement){
//			Stack<AssemType> exprStmtStack = new Stack<AssemType>();
//			ExprStatement exprStatement = (ExprStatement) statement;
//			RootExpr expr = exprStatement.getExpr();	
//			if(expr != null){
//				TranslatedExpAndType teExpr = translateExpr(expr);
//				AssemType exprTree = teExpr.getAssemType();
//				if(exprTree != null){
//					exprStmtStack.push(exprTree);
//				}				
//			}
//
//			if(!exprStmtStack.isEmpty()){
//				AssemType stmtListTree = translatingMediator.translateSeqStatement(exprStmtStack);
//				return stmtListTree;
//			}
//
//			return null;
//		}
//		else if(statement instanceof IterationStatement){
//			IterationStatement iterationStatement = (IterationStatement) statement;
//			return translateIterationStatement(iterationStatement);				
//		}
//		else if(statement instanceof SelectionStatement){
//			SelectionStatement selectionStatement = (SelectionStatement) statement;
//			return translateSelectionStatement(selectionStatement);		
//		} 
//		else if(statement instanceof JumpStatement){
//			JumpStatement jumpStatement = (JumpStatement) statement;
//			//TODO Implement below statement and un-comment and comment "return null";
//			return translateJumpStatement(jumpStatement);
//		}
//		else if(statement instanceof IncludeStatement){
//			IncludeStatement includeStatement = (IncludeStatement) statement;
//			//TODO Implement below statement and un-comment and comment "return null";
//			return translateIncludeStatement(includeStatement);
//		}
//		else {   // Must be labeled statement
//			LabeledStatement labeledStatement = (LabeledStatement) statement;
//			return translateLabeledStatement(labeledStatement);
//		}
//	}
//
//	private AssemType translateIncludeStatement(IncludeStatement includeStatement){
//		// TODO : Implement this: return null for now
//		return null;
//	}
//
//	private AssemType translateCompoundStatement(CompoundStatement compoundStatement, boolean isFunctionDefContext){
//		if(!isFunctionDefContext)
//			environments.getInstanceVariableTable().beginScope();
//		environments.getInstanceTypeTable().beginScope();
//
//		DeclarationList  declarationList = compoundStatement.getDeclarationList();
//		StatementList stmtList = compoundStatement.getStatementList();
//
//		Stack<AssemType> treeElementStack = new Stack<AssemType>();
//
//		if(declarationList != null){
//			while(declarationList != null){
//				Declaration decl = declarationList.getDeclaration();
//
//				AssemType declTree = translateDeclaration(decl, false);
//
//				if(declTree != null)
//					treeElementStack.push(declTree);
//
//				declarationList = declarationList.getDeclarationListNext();
//			}
//		}
//		if(stmtList != null){
//			while(stmtList != null){
//				Statement statement = stmtList.getStatement();				
//				AssemType declStmtList = translateStatement(statement);
//
//				if(declStmtList != null)
//					treeElementStack.push(declStmtList);
//
//				stmtList = stmtList.getStatementList();
//			}
//		}
//
//		// Create the tree for the compound statement.
//		AssemType seqStmtTree = translatingMediator.translateSeqStatement(treeElementStack);
//
//		if(!isFunctionDefContext){
//			environments.getInstanceVariableTable().endScope();
//			environments.getInstanceTypeTable().endScope();
//		}
//
//		return seqStmtTree;
//	}
//
//	private AssemType translateParameterPassing(Vector<VariableEntry> paramEntries){
//
//		if(paramEntries == null || paramEntries.size() == 0)
//			return null;
//
//		Stack<AssemType> treeElementStack = new Stack<AssemType>();
//
//		int count = 0;
//		for(VariableEntry paramEntry: paramEntries){
//			Temp registerTemp = paramEntry.getRegisterTemp();
//			AssemValueProperties avp = getAssemValueProperties(paramEntry.getType());
//			AssemType argTree = translatingMediator.translateFunctionArgument(registerTemp, avp, count++);
//			if(argTree != null)
//				treeElementStack.push(argTree);
//		}
//
//		// Create the tree for the compound statement.
//		AssemType seqStmtTree = translatingMediator.translateSeqStatement(treeElementStack);
//
//		return seqStmtTree;
//	}
//
//	private AssemType translateIterationStatement(IterationStatement iterationStatement){
//
//		AssemType finalStmtTree = null;
//
//		//iterationStatementsStack.push(iterationStatement);
//		Label iterStartLabel = new Label();
//		Label iterEndLabel = new Label();
//
//		IterationOrSelectionLabels iterOrSelectionLabels =
//			new IterationOrSelectionLabels(IterationOrSelectionLabels.ITERATION, iterStartLabel, iterEndLabel,null);
//
//		iterOrSwitchStack.push(iterOrSelectionLabels);
//
//		int iterType = iterationStatement.getIterationType();
//		SourceLocation location = new SourceLocation(iterationStatement.getLineNum(), iterationStatement.getPos());
//		if(iterType == IterationStatement.FOR){
//			RootExpr forCondExpr = iterationStatement.getForCondExpr();
//			RootExpr forInitExpr = iterationStatement.getForInitExpr();
//			RootExpr forIncrExpr = iterationStatement.getForIncrExpr();
//
//			AssemType condExprTree = null;
//			AssemType initExprTree = null;
//			AssemType incrExprTree = null;
//			AssemType forStmtTree = null;
//
//			if(forCondExpr != null){
//				TranslatedExpAndType teForCondExpr = translateExpr(forCondExpr);
//				if(teForCondExpr.getTypeEntry().getBasicType() == VoidTypeEntry.getInstance()){
//					errorHandler.addError(sourceFileName, location, "", "",  ErrorHandler.E_VOID_TYPE_IN_LOOP_CONDITION);
//				}
//				else{
//					condExprTree = teForCondExpr.getAssemType();
//				}
//			}
//
//			if(forInitExpr != null){
//				TranslatedExpAndType teForInitExpr = translateExpr(forInitExpr);
//				initExprTree = teForInitExpr.getAssemType();
//			}
//
//			if(forIncrExpr != null){
//				TranslatedExpAndType teForIncrExpr = translateExpr(forIncrExpr);
//				incrExprTree = teForIncrExpr.getAssemType();
//			}
//
//			Statement forStmt = iterationStatement.getForStmt();
//			if(forStmt != null){
//				forStmtTree = translateStatement(forStmt);
//			}
//
//			// Create the assembly tree and return it
//
//
//			if(!errorHandler.isFoundStrictError())
//				finalStmtTree = translatingMediator.translateForStmt(condExprTree, initExprTree,incrExprTree, forStmtTree,
//						iterStartLabel, iterEndLabel);			
//		}
//		else if(iterType == IterationStatement.WHILE || iterType == IterationStatement.DO_WHILE){
//
//			RootExpr whileExpr = iterationStatement.getWhileExpr();
//			Statement whileStmt = iterationStatement.getWhileStmt();
//
//			TranslatedExpAndType teWhileCondExpr = translateExpr(whileExpr);
//			AssemType whileBodyTree = null;
//			if(teWhileCondExpr.getTypeEntry().getBasicType() == VoidTypeEntry.getInstance()){
//				errorHandler.addError(sourceFileName, location, "", "",  ErrorHandler.E_VOID_TYPE_IN_LOOP_CONDITION);
//				return null;  // Error
//			}
//			if(whileStmt != null){
//				whileBodyTree = translateStatement(whileStmt);
//			}		
//
//			// No error, translate to intermediate tree representation			
//			if(!errorHandler.isFoundStrictError()){
//				AssemType whileTestTree = teWhileCondExpr.getAssemType();
//
//				if(iterType == IterationStatement.WHILE)   // This is a while statement
//					finalStmtTree = translatingMediator.translateWhileExp(whileTestTree, 
//							whileBodyTree, iterStartLabel, iterEndLabel, false);
//				else                                       // This is a do-while statement
//					finalStmtTree = translatingMediator.translateWhileExp(whileTestTree, 
//							whileBodyTree, iterStartLabel, iterEndLabel, true);
//			}						
//		}
//
//		// Pop the iteration stack
//		//iterationStatementsStack.pop();
//		if(!iterOrSwitchStack.isEmpty())
//			iterOrSwitchStack.pop();
//
//		return finalStmtTree;		
//	}
//
//	private AssemType translateSelectionStatement(SelectionStatement selectionStatement){
//
//		AssemType selectionTree = null;
//
//		Statement ifStmt = selectionStatement.getIfStmt();
//
//		if(ifStmt != null){   // Must be a if-else statement
//			RootExpr ifExpr = selectionStatement.getIfExpr();					
//			TranslatedExpAndType teRootExpr = translateExpr(ifExpr);
//
//			AssemType ifExprTree = teRootExpr.getAssemType();
//
//			AssemType ifStmtTree = translateStatement(ifStmt);
//
//			AssemType elseStmtTree = null;
//			Statement elseStmt = selectionStatement.getElseStmt();
//			if(elseStmt != null)
//				elseStmtTree = translateStatement(elseStmt);
//
//			if(!errorHandler.isFoundStrictError()){						
//				selectionTree = translatingMediator.translateIfStm(ifExprTree, ifStmtTree, elseStmtTree);
//			}			
//		}
//		else {    // Must be a switch statement			
//			//switchStatementsStack.push(selectionStatement);
//			Label switchEndLabel = new Label();			
//			iterOrSwitchStack.push(new IterationOrSelectionLabels(IterationOrSelectionLabels.SWITCH,
//					null, switchEndLabel, null));
//
//			RootExpr switchExpr = selectionStatement.getSwitchExpr();
//			Statement switchStmt = selectionStatement.getSwitchStmt();
//
//			// The switch expr must evaluate to a constant expression
//			TranslatedExpAndType exprExpAndType = translateExpr(switchExpr);
//			TypeEntryWithAttributes tpWithAttrs = exprExpAndType.getTypeEntry();
//			AssemType switchExprTree = exprExpAndType.getAssemType();
//
//			BasicType tp = tpWithAttrs.getBasicType();
//			if(!(tp == IntTypeEntry.getInstance() || tp == CharTypeEntry.getInstance() ||
//					tp == ShortTypeEntry.getInstance() || tp == LongTypeEntry.getInstance())){
//				SourceLocation location = new SourceLocation(switchExpr.getLineNum(), switchExpr.getPos());
//				errorHandler.addError(sourceFileName, location, "", "",  ErrorHandler.E_SWITCH_EXPR_MUST_BE_CONST);				
//			}
//
//			AssemType switchBodyTree = translateStatement(switchStmt);
//
//			if(!errorHandler.isFoundStrictError())
//				selectionTree = translatingMediator.translateSwitchStm(switchExprTree,
//						switchBodyTree, switchEndLabel);
//
//			//switchStatementsStack.pop();   // Pop out
//			if(!iterOrSwitchStack.isEmpty())
//				iterOrSwitchStack.pop();
//		}
//
//		return selectionTree;
//	}
//
//	private AssemType translateJumpStatement(JumpStatement jumpStatement){
//		int jmpStmtType = jumpStatement.getType();
//		SourceLocation location = new SourceLocation(jumpStatement.getLineNum(), jumpStatement.getPos());
//
//		if(jmpStmtType == JumpStatement.GOTO){
//			String gotoIdentifier = jumpStatement.getGotoIdentifier();
//
//			if(!labels.contains(gotoIdentifier)){  // No such label
//				errorHandler.addError(sourceFileName, location, gotoIdentifier + ":", "",  ErrorHandler.E_NO_LABEL);
//			}
//
//			return null; // TODO Implement this later
//		}
//		else if(jmpStmtType == JumpStatement.BREAK){
//			// Make sure this is inside a loop or in a switch/case
//			if(iterOrSwitchStack.isEmpty()){
//				errorHandler.addError(sourceFileName, location, "", "",  ErrorHandler.E_BREAK_IN_WRONG_LOCATION);
//			}
//			else{
//				IterationOrSelectionLabels iterOrSelectLabels = iterOrSwitchStack.pop();
//				AssemType jumpTree = translatingMediator.translateJump(iterOrSelectLabels.getEndLabel());
//				iterOrSwitchStack.push(iterOrSelectLabels);
//				return jumpTree;
//			}
//		}
//		else if(jmpStmtType == JumpStatement.CONTINUE){
//			if(iterOrSwitchStack.isEmpty()){
//				errorHandler.addError(sourceFileName, location, "", "",  ErrorHandler.E_CONTINUE_IN_WRONG_LOCATION);
//			}
//			else{
//				IterationOrSelectionLabels iterOrSelectLabels = iterOrSwitchStack.pop();
//				AssemType jumpTree = translatingMediator.translateJump(iterOrSelectLabels.getStartLabel());
//				iterOrSwitchStack.push(iterOrSelectLabels);
//				return jumpTree;
//			}
//		}
//		else { // Must be a return statement
//			RootExpr retExpr = jumpStatement.getReturnExpr();
//			TypeEntryWithAttributes expectedTeWithAttrs = null;
//
//			// Get the expected return type of the function
//			AssemType returnExprTree = null;
//			if(currentFunctionName != null){  
//				// Current function name has been determined; it could be null if the declarator in the 
//				// function definition has an error
//				FunctionEntry funcEntry = 
//					(FunctionEntry)environments.getInstanceVariableTable().get(Symbol.symbol(currentFunctionName));
//
//				expectedTeWithAttrs = funcEntry.getReturnType();
//				TranslatedExpAndType teRetExpr = translateExpr(retExpr);
//				returnExprTree = teRetExpr.getAssemType();
//
//				if(retExpr != null){
//					location = new SourceLocation(retExpr.getLineNum(), retExpr.getPos());
//
//					if(expectedTeWithAttrs.getBasicType() == VoidTypeEntry.getInstance()){
//						// A void function type is returning a value, flag a warning/error
//						errorHandler.addError(sourceFileName, location, "", "",  ErrorHandler.E_RETURN_VALUE_IN_FUNC_RETURNING_VOID);
//					}
//					else{						
//						checkAssignmentCompatibility(expectedTeWithAttrs, teRetExpr.getTypeEntry(), location, 
//								SemanticAnalyzer.ASSIGNMENT_IN_FUNC_RETURN_CONTEXT, AssignmentOperator.ASSIGN,
//								null, -1);
//					}
//				}
//				else{
//					if(expectedTeWithAttrs.getBasicType() != VoidTypeEntry.getInstance()){
//						// A function expected to return a non-void type is returning void
//						errorHandler.addError(sourceFileName, location, "", "",  ErrorHandler.E_RETURN_VOID_IN_FUNC_RETURNING_NON_VOID);						
//					}
//				}
//			}
//
//			// If there is no error, send back the tree for the return statement
//
//			if(!errorHandler.isFoundStrictError()){
//				AssemValueProperties assemValueProperties = getAssemValueProperties(expectedTeWithAttrs);
//				return translatingMediator.translateReturnExpr(returnExprTree, assemValueProperties);
//			}			
//		}
//
//		return null;   // Error
//
//	}
//
//	private AssemType translateLabeledStatement(LabeledStatement labeledStatement){
//		SourceLocation location = new SourceLocation(labeledStatement.getLineNum(), labeledStatement.getPos());
//
//		String label = labeledStatement.getLabel();
//		Statement stmt = labeledStatement.getStmt();
//		ConstExpr constExpr = labeledStatement.getCaseExpr();
//
//		// Check the type of the labelled statement 
//		// (ID: statement or case const expr: statement or default: statement)
//		if(label != null){
//			if(!labels.add(label)){   // Label already exists
//				errorHandler.addError(sourceFileName, location, label + ":", "",  ErrorHandler.E_LABEL_REDEFINED);
//			}			
//		}
//		else{ // Must be case or default
//			if(constExpr != null) {  // case const_expr : statement
//
//				// Case label should reduce to a integer constant.
//				TranslatedExpAndType exprExpAndType = translateConditionalExpr(constExpr.getCondExpr());
//				TypeEntryWithAttributes tpWithAttrs = exprExpAndType.getTypeEntry();
//				BasicType tp = tpWithAttrs.getBasicType();
//				String constVal = tpWithAttrs.getLiteralValue();
//
//				if(iterOrSwitchStack.isEmpty() || iterOrSwitchStack.peek().getType() != IterationOrSelectionLabels.SWITCH){
//					if(constVal != null)
//						errorHandler.addError(sourceFileName, location, "", "",  ErrorHandler.E_CASE_USED_OUTSIDE_SWITCH);
//				}	
//
//				if(constVal == null){
//					errorHandler.addError(sourceFileName, location, "", "",  ErrorHandler.E_CASE_EXPR_MUST_BE_CONST);
//				}
//				else {
//					if(!(tp == IntTypeEntry.getInstance() || tp == CharTypeEntry.getInstance() ||
//							tp == ShortTypeEntry.getInstance() || tp == LongTypeEntry.getInstance())){
//						errorHandler.addError(sourceFileName, location, "", "",  ErrorHandler.E_CASE_EXPR_MUST_BE_CONST);						
//					}	
//				}
//			}
//			else{    // default : statement
//				if(iterOrSwitchStack.isEmpty() || iterOrSwitchStack.peek().getType() != IterationOrSelectionLabels.SWITCH){
//					errorHandler.addError(sourceFileName, location, "", "",  ErrorHandler.E_DEFAULT_USED_OUTSIDE_SWITCH);
//				}				
//			}
//		}
//
//		// translate the statement(s) under this label now
//		if(!errorHandler.isFoundStrictError())
//			return translateStatement(stmt);
//		else
//			return null;
//	}
//
//	// THE EXPRESSIONS
//	private TranslatedExpAndType translateExpr(RootExpr expr){
//		TranslatedExpAndType translatedExprAndType = new TranslatedExpAndType();
//		if(expr != null) {  // Can be null because it can just be a semicolon
//			while(expr != null) {
//				AssignmentExpr assgnExpr = expr.getAssignmentExpr();
//				translatedExprAndType = translateAssignmentExpr(assgnExpr);
//
//				expr = expr.getRootExprNext();
//			}
//		}
//		return translatedExprAndType;
//	}
//
//	private TranslatedExpAndType translateAssignmentExpr(AssignmentExpr assgnExpr){		
//		assignmentExprs.push(assgnExpr);
//
//		ConditionalExpr conditionalExpr = assgnExpr.getConditionalExpr();
//
//		TranslatedExpAndType retValue = null;
//
//		if(conditionalExpr != null){     // This is a conditional expression
//			retValue =  translateConditionalExpr(conditionalExpr);
//		}
//		else{
//			UnaryExpr unaryExpr = assgnExpr.getUnaryExpr();
//
//			TranslatedExpAndType unaryExprTransExpAndType = translateUnaryExpr(unaryExpr);
//			AssignmentOperator asgnOpr = assgnExpr.getAssignmentOperator();
//			AssignmentExpr rhsExpr = assgnExpr.getAssignmentExpr();
//			TranslatedExpAndType assgnExprTransExpAndType = translateAssignmentExpr(rhsExpr);
//
//			// Check if the types are compatible
//			int lineNum = unaryExpr.getLineNum();
//			int pos = unaryExpr.getPos();
//
//			TypeEntryWithAttributes ueTeWithAttrs = unaryExprTransExpAndType.getTypeEntry();
//			TypeEntryWithAttributes rhsTeWithAttrs = assgnExprTransExpAndType.getTypeEntry();
//
//			if(ueTeWithAttrs != null && rhsTeWithAttrs != null){
//				checkAssignmentCompatibility(ueTeWithAttrs, rhsTeWithAttrs, new SourceLocation(lineNum, pos), 
//						SemanticAnalyzer.ASSIGNMENT_IN_ASSIGNMENT_EXPR, asgnOpr.getType(), null, -1);
//			}
//
//			if(!errorHandler.isFoundStrictError()){  
//				// No strict error, can be translated to tree IR
//
//				AssemType rhsType = assgnExprTransExpAndType.getAssemType();	
//				AssemType lhsType = unaryExprTransExpAndType.getAssemType();
//				AssemType assemAssign = null;
//
//				int binaryOprInAssignment = getBinaryOpFromAssignmentOpr(asgnOpr.getType());					
//				assemAssign = translatingMediator.translateAssignStm(lhsType, rhsType, binaryOprInAssignment);
//
//				// Update the usage of temps (if any) 
//				translatingMediator.updateTempAndUses(lhsType, assemAssign, tempVsListOfUseTree);
//				translatingMediator.updateTempAndUses(rhsType, assemAssign, tempVsListOfUseTree);
//
//				assgnExprTransExpAndType.setAssemType(assemAssign);	
//			}
//
//			retValue =  assgnExprTransExpAndType;			
//		}
//
//		if(!assignmentExprs.isEmpty()){
//			assignmentExprs.pop();
//		}
//
//		if(assignmentExprs.empty()){			
//			// Check if any postfix expressions must be evaluated
//			if(postfixOperativeExprs != null && postfixOperativeExprs.size() > 0 && !errorHandler.isFoundStrictError()){
//				AssemType assemType = retValue.getAssemType();				
//				AssemType finalTree = translatingMediator.addPostfixTree(assemType, postfixOperativeExprs);
//				translatingMediator.updateTempAndUses(assemType, finalTree, tempVsListOfUseTree);
//				retValue.setAssemType(finalTree);							
//			}
//
//			postfixOperativeExprs = null;  // Reset			
//		}		
//
//		return retValue;
//
//	}
//
//	private TranslatedExpAndType translateConditionalExpr(ConditionalExpr conditionalExpr){
//		LogicalOrExpr logicalOrExpr = conditionalExpr.getLogicalOrExpr();
//		RootExpr trueExpr = conditionalExpr.getTrueInConditional();
//		TranslatedExpAndType teTrue = null;
//		if(trueExpr != null){
//			ConditionalExpr falseExpr = conditionalExpr.getFalseInConditional();
//			TranslatedExpAndType teFalse = translateConditionalExpr(falseExpr);
//
//			// TODO - Check compatibility of teTrue and teFalse here? Write a test case for this
//
//			teTrue.getTypeEntry().setIsLValue(false); // This is an expression
//
//			// TODO -> Which one to return?
//			return teTrue;
//		}
//		else{
//			return translateLogicalOrExpr(logicalOrExpr);
//		}		
//	}
//
//	private TranslatedExpAndType translateLogicalOrExpr(LogicalOrExpr logicalOrExpr){
//
//		TranslatedExpAndType retValue = new TranslatedExpAndType();	
//
//		LogicalAndExpr logicalAndExpr = logicalOrExpr.getLogicalAndExpr();
//		LogicalOrExpr nextLOExpr = logicalOrExpr.getLogicalOrExpr();
//		SourceLocation location = new SourceLocation(logicalOrExpr.getLineNum(), logicalOrExpr.getPos());
//
//		if(nextLOExpr == null)
//			return translateLogicalAndExpr(logicalAndExpr);
//
//		// This is ACTUALLY a logical or expression
//
//		TranslatedExpAndType leftExprAndType = translateLogicalOrExpr(nextLOExpr);
//		TranslatedExpAndType rightExprAndType = translateLogicalAndExpr(logicalAndExpr);
//
//		BasicType result = getTranslatedExprTypePostOperation(leftExprAndType.getTypeEntry(), 
//				rightExprAndType.getTypeEntry(), location, LOGICAL_OR);
//
//		TypeEntryWithAttributes teWithAttrs = new TypeEntryWithAttributes();
//		teWithAttrs.setBasicType(result);
//		teWithAttrs.setIsLValue(false);  // This is an expression
//		retValue.setEntry(teWithAttrs); 
//
//		if(!errorHandler.isFoundStrictError()){ // No error in semantic checking yet, create and return the tree	
//			AssemType lhs = leftExprAndType.getAssemType();
//			AssemType rhs = rightExprAndType.getAssemType();
//			AssemType logicalOrExprTree =  translatingMediator.translateLogicalOperator(BinaryOpExpr.OR, lhs, rhs);
//
//			translatingMediator.updateTempAndUses(lhs, logicalOrExprTree, tempVsListOfUseTree);
//			translatingMediator.updateTempAndUses(rhs, logicalOrExprTree, tempVsListOfUseTree);
//
//			retValue.setAssemType(logicalOrExprTree);			
//		}			
//
//		return retValue;
//	}
//
//	private TranslatedExpAndType translateLogicalAndExpr(LogicalAndExpr logicalAndExpr){
//
//		TranslatedExpAndType retValue = new TranslatedExpAndType();
//
//		InclusiveOrExpr inclusiveOrExpr = logicalAndExpr.getInclusiveOrExpr();
//		LogicalAndExpr nextLAExpr = logicalAndExpr.getLogicalAndExpr();
//		SourceLocation location = new SourceLocation(logicalAndExpr.getLineNum(), logicalAndExpr.getPos());
//
//		if(nextLAExpr == null)
//			return translateInclusiveOrExpr(inclusiveOrExpr);
//
//		// This is ACTUALLY a logical and expression
//		TranslatedExpAndType leftExprAndType = translateLogicalAndExpr(nextLAExpr);
//		TranslatedExpAndType rightExprAndType = translateInclusiveOrExpr(inclusiveOrExpr);
//		BasicType result = getTranslatedExprTypePostOperation(leftExprAndType.getTypeEntry(), 
//				rightExprAndType.getTypeEntry(), location, LOGICAL_AND);
//
//		TypeEntryWithAttributes teWithAttrs = new TypeEntryWithAttributes();
//		teWithAttrs.setBasicType(result);
//		teWithAttrs.setIsLValue(false);  // This is an expression
//		retValue.setEntry(teWithAttrs);  
//
//		if(!errorHandler.isFoundStrictError()){ // No error in semantic checking yet, create and return the tree	
//			AssemType lhs = leftExprAndType.getAssemType();
//			AssemType rhs = rightExprAndType.getAssemType();
//
//			AssemType logicalAndExprTree =  translatingMediator.translateLogicalOperator(
//					BinaryOpExpr.AND, lhs, rhs);
//
//			translatingMediator.updateTempAndUses(lhs, logicalAndExprTree, tempVsListOfUseTree);
//			translatingMediator.updateTempAndUses(rhs, logicalAndExprTree, tempVsListOfUseTree);
//
//			retValue.setAssemType(logicalAndExprTree);			
//		}		
//
//		return retValue;		
//	}
//
//	private TranslatedExpAndType translateInclusiveOrExpr(InclusiveOrExpr inclusiveOrExpr){
//
//		TranslatedExpAndType retValue = new TranslatedExpAndType();
//
//		ExclusiveOrExpr exclusiveOrExpr = inclusiveOrExpr.getExclusiveOrExpr();
//		InclusiveOrExpr nextIEExpr = inclusiveOrExpr.getInclusiveOrExpr();
//		SourceLocation location = new SourceLocation(inclusiveOrExpr.getLineNum(), inclusiveOrExpr.getPos());
//
//		if(nextIEExpr == null)
//			return translateExclusiveOrExpr(exclusiveOrExpr);
//
//		// This is ACTUALLY a inclusive or expression
//		TranslatedExpAndType leftExprAndType = translateInclusiveOrExpr(nextIEExpr);
//		TranslatedExpAndType rightExprAndType = translateExclusiveOrExpr(exclusiveOrExpr);
//		BasicType result = getTranslatedExprTypePostOperation(leftExprAndType.getTypeEntry(), 
//				rightExprAndType.getTypeEntry(), location, INCLUSIVE_OR);
//
//		TypeEntryWithAttributes teWithAttrs = new TypeEntryWithAttributes();
//		teWithAttrs.setBasicType(result);
//		teWithAttrs.setIsLValue(false);  // This is an expression
//		retValue.setEntry(teWithAttrs);  
//
//		if(!errorHandler.isFoundStrictError()){ // No error in semantic checking yet, create and return the tree	
//
//			AssemType lhs = leftExprAndType.getAssemType();
//			AssemType rhs = rightExprAndType.getAssemType();
//
//			AssemType inclOrExprTree =  translatingMediator.translateBinOp(
//					BinaryOpExpr.INCLUSIVE_OR, lhs, rhs);
//
//			translatingMediator.updateTempAndUses(lhs, inclOrExprTree, tempVsListOfUseTree);
//			translatingMediator.updateTempAndUses(rhs, inclOrExprTree, tempVsListOfUseTree);
//
//			retValue.setAssemType(inclOrExprTree);			
//		}		
//
//		return retValue;
//	}
//
//	private TranslatedExpAndType translateExclusiveOrExpr(ExclusiveOrExpr exclusiveOrExpr){
//
//		TranslatedExpAndType retValue = new TranslatedExpAndType();
//
//		AndExpr andExpr = exclusiveOrExpr.getAndExpr();
//		ExclusiveOrExpr nextEOExpr = exclusiveOrExpr.getExclusiveOrExpr();
//		SourceLocation location = new SourceLocation(exclusiveOrExpr.getLineNum(), exclusiveOrExpr.getPos());
//
//		if(nextEOExpr == null)
//			return translateAndExpr(andExpr);
//
//		// This is ACTUALLY a exclusive or expression
//		TranslatedExpAndType leftExprAndType = translateExclusiveOrExpr(nextEOExpr);
//		TranslatedExpAndType rightExprAndType = translateAndExpr(andExpr);
//		BasicType result = getTranslatedExprTypePostOperation(leftExprAndType.getTypeEntry(), 
//				rightExprAndType.getTypeEntry(), location, EXCLUSIVE_OR);
//
//		TypeEntryWithAttributes teWithAttrs = new TypeEntryWithAttributes();
//		teWithAttrs.setBasicType(result);
//		teWithAttrs.setIsLValue(false);  // This is an expression
//		retValue.setEntry(teWithAttrs);  
//
//		if(!errorHandler.isFoundStrictError()){ // No error in semantic checking yet, create and return the tree				
//			AssemType lhs = leftExprAndType.getAssemType();
//			AssemType rhs = rightExprAndType.getAssemType();			
//
//			AssemType exclOrExprTree =  translatingMediator.translateBinOp(
//					BinaryOpExpr.EXCLUSIVE_OR, lhs, rhs);
//
//			translatingMediator.updateTempAndUses(lhs, exclOrExprTree, tempVsListOfUseTree);
//			translatingMediator.updateTempAndUses(rhs, exclOrExprTree, tempVsListOfUseTree);			
//
//			retValue.setAssemType(exclOrExprTree);			
//		}	
//
//		return retValue;
//
//	}
//
//	private TranslatedExpAndType translateAndExpr(AndExpr andExpr){
//
//		TranslatedExpAndType retValue = new TranslatedExpAndType();
//
//		EqualityExpr equalityExpr = andExpr.getEqualityExpr();
//		AndExpr nextAndExpr = andExpr.getAndExpr();
//		SourceLocation location = new SourceLocation(andExpr.getLineNum(), andExpr.getPos());
//
//		if(nextAndExpr == null)
//			return translateEqualityExpr(equalityExpr);
//
//		// This is ACTUALLY a AND expression
//		TranslatedExpAndType leftExprAndType = translateAndExpr(nextAndExpr);
//		TranslatedExpAndType rightExprAndType = translateEqualityExpr(equalityExpr);
//		BasicType result = getTranslatedExprTypePostOperation(leftExprAndType.getTypeEntry(), 
//				rightExprAndType.getTypeEntry(), location, AND);
//
//		TypeEntryWithAttributes teWithAttrs = new TypeEntryWithAttributes();
//		teWithAttrs.setBasicType(result);
//		teWithAttrs.setIsLValue(false);  // This is an expression
//		retValue.setEntry(teWithAttrs);  
//
//		if(!errorHandler.isFoundStrictError()){ // No error in semantic checking yet, create and return the tree	
//			AssemType lhs = leftExprAndType.getAssemType();
//			AssemType rhs = rightExprAndType.getAssemType();						
//
//			AssemType andExprTree =  translatingMediator.translateBinOp(
//					BinaryOpExpr.AND, leftExprAndType.getAssemType(), rightExprAndType.getAssemType());
//
//			translatingMediator.updateTempAndUses(lhs, andExprTree, tempVsListOfUseTree);
//			translatingMediator.updateTempAndUses(rhs, andExprTree, tempVsListOfUseTree);						
//
//			retValue.setAssemType(andExprTree);			
//		}	
//
//		return retValue;
//
//	}
//
//	private TranslatedExpAndType translateEqualityExpr(EqualityExpr equalityExpr){
//
//		TranslatedExpAndType retValue = new TranslatedExpAndType();
//
//		RelationalExpr relationalExpr = equalityExpr.getRelationalExpr();
//		SourceLocation location = new SourceLocation(equalityExpr.getLineNum(), equalityExpr.getPos());
//
//		int opr = equalityExpr.getOperator();
//		String type = EQUALS;
//		if(opr == EqualityExpr.NOT_EQUALS){
//			type = NOT_EQUALS;
//		}
//		EqualityExpr nextEqExpr = equalityExpr.getEqualityExpr();
//
//		if(nextEqExpr == null)
//			return translateRelationalExpr(relationalExpr);
//
//		// This is ACTUALLY an equality (equal or not equal) expression
//
//		TranslatedExpAndType leftExprAndType = translateEqualityExpr(nextEqExpr);
//
//		TranslatedExpAndType rightExprAndType = translateRelationalExpr(relationalExpr);
//		BasicType result = getTranslatedExprTypePostOperation(leftExprAndType.getTypeEntry(), 
//				rightExprAndType.getTypeEntry(), location, type);
//
//		TypeEntryWithAttributes teWithAttrs = new TypeEntryWithAttributes();
//		teWithAttrs.setBasicType(result);
//		teWithAttrs.setIsLValue(false);  // This is an expression
//		retValue.setEntry(teWithAttrs);  
//
//		if(!errorHandler.isFoundStrictError()){ // No error in semantic checking yet, create and return the tree
//			int comparisonOpExprType = type.equals(EQUALS)? AssemCJump.EQ: AssemCJump.NE;
//
//			AssemType lhs = leftExprAndType.getAssemType();
//			AssemType rhs = rightExprAndType.getAssemType();									
//
//			AssemType equalityExprTree =  translatingMediator.translateComparisonOperator(
//					comparisonOpExprType, lhs, rhs);
//
//			translatingMediator.updateTempAndUses(lhs, equalityExprTree, tempVsListOfUseTree);
//			translatingMediator.updateTempAndUses(rhs, equalityExprTree, tempVsListOfUseTree);						
//
//			retValue.setAssemType(equalityExprTree);			
//		}	
//
//		return retValue;		
//	}
//
//	private TranslatedExpAndType translateRelationalExpr(RelationalExpr relationalExpr){
//
//		TranslatedExpAndType retValue = new TranslatedExpAndType();
//
//		String type = LESSER_THAN;
//		int binaryOpType = AssemCJump.LT;
//		int rel = relationalExpr.getOperator();
//		if(rel == RelationalExpr.GREATER_THAN){
//			type = GREATER_THAN;
//			binaryOpType = AssemCJump.GT;
//		}
//		else if(rel == RelationalExpr.GREATER_THAN_OR_EQUAL_TO){
//			type = GREATER_THAN_OR_EQUAL_TO; 
//			binaryOpType = AssemCJump.GE;
//		}
//		else if(rel == RelationalExpr.LESSER_THAN_OR_EQUAL_TO){
//			type = LESSER_THAN_OR_EQUAL_TO; 
//			binaryOpType = AssemCJump.LE;
//		}
//
//		ShiftExpr shiftExpr = relationalExpr.getShiftExpr();
//		RelationalExpr nextRlExpr = relationalExpr.getRelationalExpr();
//		SourceLocation location = new SourceLocation(relationalExpr.getLineNum(), relationalExpr.getPos());
//
//		if(nextRlExpr == null)
//			return translateShiftExpr(shiftExpr);
//
//		// This is ACTUALLY a relational expression
//		TranslatedExpAndType leftExprAndType = translateRelationalExpr(nextRlExpr);
//		TranslatedExpAndType rightExprAndType = translateShiftExpr(shiftExpr);
//		BasicType result = getTranslatedExprTypePostOperation(leftExprAndType.getTypeEntry(), 
//				rightExprAndType.getTypeEntry(), location, type);
//
//		TypeEntryWithAttributes teWithAttrs = new TypeEntryWithAttributes();
//		teWithAttrs.setBasicType(result);
//		teWithAttrs.setIsLValue(false);  // This is an expression
//		retValue.setEntry(teWithAttrs);  
//
//		if(!errorHandler.isFoundStrictError()){ // No error in semantic checking yet, create and return the tree	
//			AssemType lhs = leftExprAndType.getAssemType();
//			AssemType rhs = rightExprAndType.getAssemType();												
//
//			AssemType relnExprTree =  translatingMediator.translateComparisonOperator(
//					binaryOpType, lhs, rhs);			
//
//			translatingMediator.updateTempAndUses(lhs, relnExprTree, tempVsListOfUseTree);
//			translatingMediator.updateTempAndUses(rhs, relnExprTree, tempVsListOfUseTree);	
//
//			retValue.setAssemType(relnExprTree);			
//		}	
//
//		return retValue;		
//	}
//
//	private TranslatedExpAndType translateShiftExpr(ShiftExpr shiftExpr){
//
//		TranslatedExpAndType retValue = new TranslatedExpAndType();
//
//		AdditiveExpr additiveExpr = shiftExpr.getAdditiveExpr();
//		ShiftExpr nextShiftExpr = shiftExpr.getShiftExpr();
//		SourceLocation location = new SourceLocation(shiftExpr.getLineNum(), shiftExpr.getPos());
//
//		int opr = shiftExpr.getOperator();
//		String type = LEFT_SHIFT;
//		int binaryOpExprType = BinaryOpExpr.LEFT_SHIFT;
//		if(opr == ShiftExpr.RIGHT_SHIFT){
//			type = RIGHT_SHIFT;
//			binaryOpExprType = BinaryOpExpr.RIGHT_SHIFT;
//		}
//
//		if(nextShiftExpr == null)
//			return translateAdditiveExpr(additiveExpr);
//
//		// This is ACTUALLY a shift expression
//		TranslatedExpAndType leftExprAndType = translateShiftExpr(nextShiftExpr);
//		TranslatedExpAndType rightExprAndType = translateAdditiveExpr(additiveExpr);
//		BasicType result = getTranslatedExprTypePostOperation(leftExprAndType.getTypeEntry(), 
//				rightExprAndType.getTypeEntry(), location, type);
//
//		TypeEntryWithAttributes teWithAttrs = new TypeEntryWithAttributes();
//		teWithAttrs.setBasicType(result);
//		teWithAttrs.setIsLValue(false);  // This is an expression
//		retValue.setEntry(teWithAttrs);  
//
//		if(!errorHandler.isFoundStrictError()){ // No error in semantic checking yet, create and return the tree
//			AssemType lhs = leftExprAndType.getAssemType();
//			AssemType rhs = rightExprAndType.getAssemType();												
//
//			AssemType shiftExprTree =  translatingMediator.translateBinOp(
//					binaryOpExprType, lhs, rhs);
//
//			translatingMediator.updateTempAndUses(lhs, shiftExprTree, tempVsListOfUseTree);
//			translatingMediator.updateTempAndUses(rhs, shiftExprTree, tempVsListOfUseTree);				
//
//			retValue.setAssemType(shiftExprTree);			
//		}	
//
//		return retValue;		
//	}
//
//	private TranslatedExpAndType translateAdditiveExpr(AdditiveExpr additiveExpr){
//
//		TranslatedExpAndType retValue = new TranslatedExpAndType();
//
//		MultiplicativeExpr multiplicativeExpr = additiveExpr.getMultiplicativeExpr();
//		AdditiveExpr nextAdditiveExpr = additiveExpr.getAdditiveExpr();
//		SourceLocation location = new SourceLocation(additiveExpr.getLineNum(), additiveExpr.getPos());
//
//		int opr = additiveExpr.getOperator();
//
//
//		if(nextAdditiveExpr == null)
//			return translateMultiplicativeExpr(multiplicativeExpr);
//
//		// This is ACTUALLY a add/subtract expression
//		String type = ADD;
//		int binaryOpExprType = BinaryOpExpr.ADDITIVE;
//		if(opr == AdditiveExpr.SUBTRACT){
//			binaryOpExprType = BinaryOpExpr.MINUS;
//			type = SUBTRACT;
//		}
//
//		TranslatedExpAndType addTransAndExpType = translateAdditiveExpr(nextAdditiveExpr);		
//		TranslatedExpAndType meTransAndExpType = translateMultiplicativeExpr(multiplicativeExpr);
//
//		BasicType result = getTranslatedExprTypePostOperation(meTransAndExpType.getTypeEntry(), 
//				addTransAndExpType.getTypeEntry(),location, type);
//
//		TypeEntryWithAttributes teWithAttrs = new TypeEntryWithAttributes();
//		teWithAttrs.setBasicType(result);
//		teWithAttrs.setIsLValue(false);  // This is an expression
//		retValue.setEntry(teWithAttrs);
//
//		if(!errorHandler.isFoundStrictError()){ // No error in semantic checking yet, create and return the tree	
//			AssemType addType = addTransAndExpType.getAssemType();
//			AssemType multiplyType = meTransAndExpType.getAssemType();												
//
//			AssemType additiveExprTree =  translatingMediator.translateBinOp(
//					binaryOpExprType, addType, multiplyType);
//
//			translatingMediator.updateTempAndUses(addType, additiveExprTree, tempVsListOfUseTree);
//			translatingMediator.updateTempAndUses(multiplyType, additiveExprTree, tempVsListOfUseTree);	
//
//			retValue.setAssemType(additiveExprTree);			
//		}	
//
//		return retValue;
//	}
//
//	private TranslatedExpAndType translateMultiplicativeExpr(MultiplicativeExpr multiplicativeExpr){
//
//		TranslatedExpAndType retValue = new TranslatedExpAndType();
//
//		CastExpr castExpr = multiplicativeExpr.getCastExpr();
//		MultiplicativeExpr nextMulExpr = multiplicativeExpr.getOtherExpr();
//		SourceLocation location = new SourceLocation(multiplicativeExpr.getLineNum(), multiplicativeExpr.getPos());
//
//		int opr = multiplicativeExpr.getOperator();
//		String type = MULTIPLY;
//		int binOpExprType = BinaryOpExpr.MULTIPLY;
//		if(opr == MultiplicativeExpr.DIVIDE){
//			type = DIVIDE;
//			binOpExprType = BinaryOpExpr.DIVIDE;
//		}
//		else if(opr == MultiplicativeExpr.MODULO){
//			type = MODULO;
//			binOpExprType = BinaryOpExpr.MODULO;
//		}
//
//		if(nextMulExpr == null)
//			return translateCastExpr(castExpr);
//
//		// This is ACTUALLY a multiplicative expression
//
//		TranslatedExpAndType leftExprAndType = translateMultiplicativeExpr(nextMulExpr);
//		TranslatedExpAndType rightExprAndType = translateCastExpr(castExpr);
//		BasicType result = getTranslatedExprTypePostOperation(leftExprAndType.getTypeEntry(), 
//				rightExprAndType.getTypeEntry(), location, type);
//
//		TypeEntryWithAttributes teWithAttrs = new TypeEntryWithAttributes();
//		teWithAttrs.setBasicType(result);
//		teWithAttrs.setIsLValue(false);  // This is an expression
//		retValue.setEntry(teWithAttrs);  
//
//		if(!errorHandler.isFoundStrictError()){ // No error in semantic checking yet, create and return the tree	
//
//			AssemType lhs = leftExprAndType.getAssemType();
//			AssemType rhs = rightExprAndType.getAssemType();												
//
//			AssemType mulExprTree =  translatingMediator.translateBinOp( binOpExprType, 
//					lhs, rhs);			
//
//			translatingMediator.updateTempAndUses(lhs, mulExprTree, tempVsListOfUseTree);
//			translatingMediator.updateTempAndUses(rhs, mulExprTree, tempVsListOfUseTree);	
//
//			retValue.setAssemType(mulExprTree);			
//		}	
//
//		return retValue;		
//	}
//
//	private TranslatedExpAndType translateCastExpr(CastExpr castExpr){
//
//		TranslatedExpAndType retValue = new TranslatedExpAndType();
//
//		UnaryExpr unaryExpr = castExpr.getUnaryExpr();
//		TypeName typeName = castExpr.getTypeName();
//		if(typeName == null){			
//			return translateUnaryExpr(unaryExpr);
//		}
//
//		// This is ACTUALLY a cast expression
//		SpecifierQualifierList specifierQualifierList = typeName.getSpecifierQualifierList();
//		AbstractDeclarator abstractDeclarator = typeName.getAbstractDeclarator();
//
//		if(specifierQualifierList != null){
//			TypeEntryWithAttributes teWithAttrs = 
//				getBaseEntryFromSpecsList(specifierQualifierList, false, false, false);
//			retValue.setEntry(teWithAttrs);
//		}
//		if(abstractDeclarator != null){
//			// TODO Implement this
//		}
//
//		// TODO Correct this and complete the rest of the implementation here, 
//		// including translating to the intermediate representation tree.
//		CastExpr otherCastExpr = castExpr.getCastExpr();
//		TranslatedExpAndType otherCastExprAndType = translateCastExpr(otherCastExpr);
//		if(otherCastExprAndType.getTypeEntry().isLValue())
//			retValue.getTypeEntry().setIsLValue(true);
//		else{
//			retValue.getTypeEntry().setIsLValue(false);
//		}
//
//		return retValue;
//	}
//
//	private TranslatedExpAndType translateUnaryExpr(UnaryExpr unaryExpr){
//
//		TranslatedExpAndType retValue = new TranslatedExpAndType();
//
//		PostfixExpr pfExpr = unaryExpr.getPostfixExpr();
//		if(pfExpr != null)  // Is a postfix expression
//			return translatePostfixExpr(pfExpr);			
//
//		// Must have some operator followed by another unary or cast expression
//		SourceLocation location = new SourceLocation(unaryExpr.getLineNum(), unaryExpr.getPos());
//
//		int unaryExrType = unaryExpr.getOperator();
//		if(unaryExrType == UnaryExpr.STAR){  
//			// Indirection operator, ensure the cast expression is a pointer
//			CastExpr castExpr = unaryExpr.getCastExpr();
//			TranslatedExpAndType teCastExpr = translateCastExpr(castExpr);
//			if(teCastExpr.getTypeEntry() == null)  // This error would already have been handled, continue
//				return retValue;
//
//			BasicType castExprBasicType = teCastExpr.getTypeEntry().getBasicType();
//
//			if(!(castExprBasicType instanceof PointerTypeEntry || castExprBasicType instanceof ArrayTypeEntry)){
//				// Neither a pointer nor an array element, flag an error
//				errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_WRONG_INDIRECTION);
//			}
//			else{		
//				TypeEntryWithAttributes teNew = null;
//				if(castExprBasicType instanceof PointerTypeEntry){
//					PointerTypeEntry ptrTypeEntry = (PointerTypeEntry)castExprBasicType;
//					teNew =  ptrTypeEntry.getBaseTypeEntry();
//					retValue.setEntry(teNew);
//					teNew.setIsLValue(true);					
//				}
//				else {   // Array type
//					ArrayTypeEntry arrayTypeEntry = (ArrayTypeEntry)castExprBasicType;
//					teNew = arrayTypeEntry.getBaseTypeEntry();
//					retValue.setEntry(teNew);
//					teNew.setIsLValue(true);
//				}
//
//				AssemType exprTree = teCastExpr.getAssemType();
//
//				if(!errorHandler.isFoundStrictError()){
//					
//					AssemValueProperties assemValueProperties = getAssemValueProperties(teNew);
//					
//					AssemType indirectionTree = translatingMediator.translateIndirection(exprTree, assemValueProperties);
//
//					translatingMediator.updateTempAndUses(exprTree, indirectionTree, tempVsListOfUseTree);	
//
//					retValue.setAssemType(indirectionTree);
//				}
//
//			}
//		}				
//		else if(unaryExrType == UnaryExpr.AMPERSAND){
//
//			CastExpr castExpr = unaryExpr.getCastExpr();
//			TranslatedExpAndType teChildExpr = translateCastExpr(castExpr);
//			TypeEntryWithAttributes teCastExprWithAttrs = teChildExpr.getTypeEntry();
//			if(teCastExprWithAttrs == null){
//				// Error, but This would already have been handled, continue
//				return retValue;
//			}
//
//			if(teCastExprWithAttrs.getLiteralValue() != null ){
//				errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_ADDRESS_OF_A_CONSTANT);
//			}
//			else if(teCastExprWithAttrs.isRegister()){
//				errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_ADDRESS_OF_A_VAR_IN_REGISTER);
//			}
//			else if(teCastExprWithAttrs.getBasicType() instanceof ArrayTypeEntry){ 
//				errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_ADDRESS_OF_A_CONSTANT);					
//			}
//			else if(!teCastExprWithAttrs.isLValue()){
//				errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_ADDRESS_OF_AN_EXPRESSION);					
//			}				
//			else{
//				TypeEntryWithAttributes newTeAttrs = new TypeEntryWithAttributes();
//				PointerTypeEntry ptrEntry = new PointerTypeEntry(teCastExprWithAttrs);
//				newTeAttrs.setBasicType(ptrEntry);
//				newTeAttrs.setIsLValue(false);
//				retValue.setEntry(newTeAttrs);
//
//				AssemType indirectionTree = null;
//
//				AssemType exprTree = teChildExpr.getAssemType();				
//				if(exprTree instanceof AssemTemp){
//					// This variable was considered to be in a register earlier but now we are taking its
//					// address; therefore back-track and reassign this to  memory 	
//					AssemTemp assemTemp = (AssemTemp) exprTree;
//
//					AssemValueProperties assemValueProperties = getAssemValueProperties(teChildExpr.getTypeEntry());
//					int sizeOfNextElement =  assemValueProperties.getSizeInBytes();
//					//currentOffset++;
//					
//					int offsetForNextElement = getOffsetForNextElement(sizeOfNextElement);
//					
//					translatingMediator.backTrack(assemTemp.getTemp(), tempVsListOfUseTree, offsetForNextElement, assemValueProperties);
//
//					// The resulting tree is the address
//					//indirectionTree = translatingMediator.createMemoryRefWithOffsetFromFramePointer(currentOffset);
//					indirectionTree = translatingMediator.createAddressWithOffsetFromFP(offsetForNextElement, assemValueProperties);
//
//					// Mark the corresponding variable entry with the updated information (replace the temp
//					// with the memory offset)
//					String identifier = teChildExpr.getVariableName();
//					Symbol idSym = Symbol.symbol(identifier);
//					VariableEntry oldVarEntry = (VariableEntry) environments.getInstanceVariableTable().get(idSym);						
//					oldVarEntry.setName(identifier);
//					oldVarEntry.setRegisterTemp(null);
//					oldVarEntry.setOffSetFromFP(offsetForNextElement);
//
//					// Add to the escaping variables in the current activation frame
//					AbstractType type = createAbstractType(oldVarEntry.getType());
//					if(currentFunctionDef != null){
//						ScalarStackElement scalarStackElement = new ScalarStackElement();
//						scalarStackElement.setType(StackElement.SCALAR_TYPE);
//						scalarStackElement.setMode(StackElement.ESCAPED);
//						scalarStackElement.setAbstractType(type);
//						ActivationFrame af = currentFunctionDef.getActivationFrame();
//						af.addStackElement(scalarStackElement);
//					}
//				}
//				else{		
//					// This is already in memory, get the address tree
//					indirectionTree = exprTree;
//				}
//
//				retValue.setAssemType(indirectionTree);				
//			}				
//		}
//		else if(unaryExrType == UnaryExpr.PLUS){
//			CastExpr castExpr = unaryExpr.getCastExpr();
//			retValue = translateCastExpr(castExpr); // No change from cast expression
//		}
//		else if(unaryExrType == UnaryExpr.MINUS){
//			CastExpr castExpr = unaryExpr.getCastExpr();
//			retValue = translateCastExpr(castExpr);
//			TypeEntryWithAttributes teWithAttrs = retValue.getTypeEntry();
//			retValue.getTypeEntry().setIsLValue(false);
//
//			// Check that the operands are correct for a "minus" value
//			BasicType bt = teWithAttrs.getBasicType();
//			if(!(bt == IntTypeEntry.getInstance() || bt == ShortTypeEntry.getInstance()
//					|| bt == CharTypeEntry.getInstance() || bt == LongTypeEntry.getInstance()
//					|| bt == DoubleTypeEntry.getInstance() || bt == FloatTypeEntry.getInstance())){
//
//				errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_NEGATIVE_OPERATOR_CANNOT_BE_USED);
//			}				
//			else{
//				AssemType assemType = retValue.getAssemType();
//				AssemType negationTree = translatingMediator.translateNegativeValOfExpr(retValue.getAssemType());
//				translatingMediator.updateTempAndUses(assemType, negationTree, tempVsListOfUseTree);	
//				retValue.setAssemType(negationTree);
//			}			
//		}
//		else if(unaryExrType == UnaryExpr.SIZEOF){
//			UnaryExpr nextUnaryExpr = unaryExpr.getUnaryExpr();
//			if(nextUnaryExpr != null){
//				TranslatedExpAndType teNextUnaryExpr = translateUnaryExpr(nextUnaryExpr);
//
//				TypeEntryWithAttributes newTeAttrs = new TypeEntryWithAttributes();
//				newTeAttrs.setUnsigned(true);
//				newTeAttrs.setBasicType(IntTypeEntry.getInstance());
//				newTeAttrs.setIsLValue(false);
//				retValue.setEntry(newTeAttrs);
//
//				int size = getSizeOfType(teNextUnaryExpr.getTypeEntry());
//
//				AssemType constTree = translatingMediator.translateConstant(size);
//				retValue.setAssemType(constTree);
//			}
//			else{
//				// TODO modify this in the next phase
//				TypeName typeName = unaryExpr.getTypeName();
//			}
//		}
//		else if(unaryExrType == UnaryExpr.NOT){
//			CastExpr castExpr = unaryExpr.getCastExpr();				
//			TranslatedExpAndType teCastExpr = translateCastExpr(castExpr);
//			TypeEntryWithAttributes teCastExprWithAttrs = teCastExpr.getTypeEntry();
//			if(teCastExprWithAttrs == null){
//				// This would already have been handled, continue
//				return retValue;
//			}
//			BasicType bt = teCastExprWithAttrs.getBasicType();
//
//			AssemType notTree = null;
//			if(bt instanceof StructOrUnionTypeEntry){
//				errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_UNARY_NEGATION_OP_ON_STRUCT);
//				retValue = teCastExpr;
//				retValue.getTypeEntry().setIsLValue(false);
//				return retValue;
//			}
//			else{ // No error, build the tree
//				AssemExp assemExp = (AssemExp) teCastExpr.getAssemType();
//				notTree = translatingMediator.translateNegationOfExpr((AssemExp)teCastExpr.getAssemType());
//				translatingMediator.updateTempAndUses(assemExp, notTree, tempVsListOfUseTree);	
//			}
//
//			TypeEntryWithAttributes newTeAttrs = new TypeEntryWithAttributes();
//			newTeAttrs.setBasicType(IntTypeEntry.getInstance());
//			newTeAttrs.setIsLValue(false);
//			retValue.setEntry(newTeAttrs);
//			retValue.setAssemType(notTree);
//		}
//		else if(unaryExrType == UnaryExpr.TILDE){
//			AssemType onesComplementTree = null;
//			CastExpr castExpr = unaryExpr.getCastExpr();
//			TranslatedExpAndType teCastExpr = translateCastExpr(castExpr);
//			TypeEntryWithAttributes teCastExprWithAttrs = teCastExpr.getTypeEntry();
//			if(teCastExprWithAttrs == null){
//				// This would already have been handled, continue
//				return retValue;
//			}
//			BasicType bt = teCastExprWithAttrs.getBasicType();
//			if(bt instanceof StructOrUnionTypeEntry){
//				errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_ONES_COMPLEMENT_ON_STRUCT);
//				TypeEntryWithAttributes newTeAttrs = new TypeEntryWithAttributes();
//				newTeAttrs.setBasicType(IntTypeEntry.getInstance());
//				newTeAttrs.setIsLValue(false);
//				retValue.setEntry(newTeAttrs);		
//				return retValue;
//			}
//			else{
//				// Can only be applied to integers
//				if(!(bt instanceof IntTypeEntry || bt instanceof LongTypeEntry || bt instanceof CharTypeEntry
//						|| bt instanceof ShortTypeEntry )){
//					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_ONES_COMPLEMENT_ON_INTEGERS_ONLY);
//					retValue = teCastExpr;
//					retValue.getTypeEntry().setIsLValue(false);
//					return retValue;
//				}
//				else{
//					// Translate to assembly tree
//					AssemType assemType = teCastExpr.getAssemType();
//					onesComplementTree = translatingMediator.translateOnesComplement(teCastExpr.getAssemType());
//					translatingMediator.updateTempAndUses(assemType, onesComplementTree, tempVsListOfUseTree);	
//					retValue.setAssemType(onesComplementTree);
//				}
//
//				TypeEntryWithAttributes newTeAttrs = new TypeEntryWithAttributes();
//				newTeAttrs.setBasicType(IntTypeEntry.getInstance());
//				newTeAttrs.setIsLValue(false);
//				retValue.setEntry(newTeAttrs);		
//				return retValue;
//			}
//		}
//		else if(unaryExrType == UnaryExpr.INCR || unaryExrType == UnaryExpr.DECR){
//			UnaryExpr uexpr = unaryExpr.getUnaryExpr();
//
//			TranslatedExpAndType teUExpr = translateUnaryExpr(uexpr);
//			TypeEntryWithAttributes teWithAttrs = teUExpr.getTypeEntry();								
//			BasicType bt = teWithAttrs.getBasicType();
//			retValue.setEntry(teWithAttrs);
//
//			String invalidOperandErrMessage = ErrorHandler.E_INVALID_OPERAND_FOR_INCREMENT;
//			String operatorNeedsLValueErrMessage = ErrorHandler.E_INCREMENT_OPR_NEEDS_LVALUE;
//			boolean isIncrement = true;
//			if(unaryExrType == UnaryExpr.DECR){
//				invalidOperandErrMessage = ErrorHandler.E_INVALID_OPERAND_FOR_DECREMENT;
//				operatorNeedsLValueErrMessage =  ErrorHandler.E_DECREMENT_OPR_NEEDS_LVALUE;
//				isIncrement = false;
//			}
//
//			AssemType unaryTree = null;
//
//			if(bt instanceof StructOrUnionTypeEntry){
//				errorHandler.addError(sourceFileName, location, "", "", invalidOperandErrMessage);
//				return retValue;
//			}
//			else{
//				if(!teWithAttrs.isLValue()){
//					errorHandler.addError(sourceFileName, location, "", "", operatorNeedsLValueErrMessage);
//					return retValue;
//				}				
//			}
//
//			teWithAttrs.setIsLValue(false);  // This is a lvalue now
//			// No error, create assembly tree and set it in return value
//
//			AssemType assemType = teUExpr.getAssemType();
//			unaryTree = translatingMediator.translateIncrementOrDecrementOp(assemType, isIncrement);
//			translatingMediator.updateTempAndUses(assemType, unaryTree, tempVsListOfUseTree);	
//			retValue.setAssemType(unaryTree);
//			return retValue;
//		}
//
//		return retValue; 
//	}
//
//	/**
//	 * Gets the current offset in the stack and also for global memory. For the stack, it is the offset based on 
//	 * previous value on the activation frame - what about global variables?
//	 * @return
//	 */
//	private int getOffsetForNextElement(int sizeOfNextElement){
//		ActivationFrame af = null;
//		if(currentFunctionDef != null){
//			af = currentFunctionDef.getActivationFrame();
//			int offsetForNextElement = af.getOffsetForNextElementFromFramePointer(sizeOfNextElement);
//			return offsetForNextElement;
//		}
//		else{
//			// TODO : implement for global memory
//			return -1;
//		}
//
//
//	}
//
//	private TranslatedExpAndType translatePostfixExpr(PostfixExpr pfExpr){			
//		TranslatedExpAndType tePrevExpr = null;
//		boolean prevTypeWasFuncCall= false;
//
//		PrimaryExpr primaryExpr = null;
//
//		SourceLocation location = new SourceLocation(pfExpr.getLineNum(), pfExpr.getPos());
//
//		while(pfExpr != null){
//
//			if(pfExpr.getType() == PostfixExpr.PRIMARY){
//				// First primary expression, the result might or might not follow
//				primaryExpr = pfExpr.getPrimaryExpr();
//				tePrevExpr = translatePrimaryExpr(primaryExpr);
//			}
//			else if(pfExpr.getType() == PostfixExpr.FUNC_CALL){
//				if(prevTypeWasFuncCall){
//					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_CANNOT_HAVE_FUNCTION_CALL_HERE);
//				}
//				else{
//					prevTypeWasFuncCall = true;
//					ArgumentExpressionList ael = pfExpr.getArgumentExpressionList();
//
//					if(tePrevExpr == null){
//
//					}
//					else{						
//						String identifier = primaryExpr.getIdentifier();						
//						FunctionEntry funcEntry = (FunctionEntry)
//						environments.getInstanceVariableTable().get(Symbol.symbol(identifier));	
//						if(funcEntry != null)							
//							tePrevExpr = translateFunctionCall(funcEntry, ael, location);
//					}
//
//					// TODO Function call tree to built and set here
//
//				}				
//			}
//			else if(pfExpr.getType() == PostfixExpr.DOT){				
//				prevTypeWasFuncCall = false;
//
//				// Check if previous type is either structure or a union
//				String postfixId = pfExpr.getIdentifier();
//
//				// The previous expression must be a struct or union type
//				TypeEntryWithAttributes tePrev = tePrevExpr.getTypeEntry();
//
//				if(tePrev == null || !(tePrev.getBasicType() instanceof StructOrUnionTypeEntry)){
//					errorHandler.addError(sourceFileName, location, postfixId + ": " , "", ErrorHandler.E_LEFT_SIDE_NOT_STRUCT_OR_UNION);
//					return tePrevExpr;
//				}							
//
//				// Analyze the struct or union 
//				StructOrUnionTypeEntry prevStructOrUnion = (StructOrUnionTypeEntry) tePrev.getBasicType();
//				TypeEntryWithAttributes memberType = prevStructOrUnion.getMemberType(postfixId);
//				if(memberType == null || memberType.getBasicType() == null){
//					errorHandler.addError(sourceFileName, location, postfixId + ":" , "", ErrorHandler.E_NOT_MEMBER_OF_STRUCT_OR_UNION);
//					return tePrevExpr;
//				}
//
//				tePrevExpr = new TranslatedExpAndType();
//				memberType.setIsLValue(true);
//				tePrevExpr.setEntry(memberType);
//
//				// TODO Deferencing tree to built and set here
//			}
//			else if(pfExpr.getType() == PostfixExpr.DEREFERENCE){
//
//				prevTypeWasFuncCall = false;
//
//				// Check if previous type is either structure or a union
//				String postfixId = pfExpr.getIdentifier();
//
//				// The previous expression must be a POINTER to a struct or union type
//				TypeEntryWithAttributes tePrev = tePrevExpr.getTypeEntry();
//				if(tePrev == null){
//					errorHandler.addError(sourceFileName, location, postfixId + ": " , "", ErrorHandler.E_LEFT_SIDE_NOT_POINTER_TO_STRUCT_OR_UNION);
//					return tePrevExpr;
//				}				
//				BasicType btLeft = tePrev.getBasicType();
//				if(!(btLeft instanceof PointerTypeEntry)){
//					errorHandler.addError(sourceFileName, location, postfixId + ": " , "", ErrorHandler.E_LEFT_SIDE_NOT_POINTER_TO_STRUCT_OR_UNION);
//					return tePrevExpr;
//				}
//
//				PointerTypeEntry ptrEntryLeft = (PointerTypeEntry) btLeft;
//				TypeEntryWithAttributes tePtr = ptrEntryLeft.getBaseTypeEntry();
//
//				if(tePtr == null || !(tePtr.getBasicType() instanceof StructOrUnionTypeEntry)){
//					errorHandler.addError(sourceFileName, location, postfixId + ": " , "", ErrorHandler.E_LEFT_SIDE_NOT_POINTER_TO_STRUCT_OR_UNION);
//					return tePrevExpr;
//				}							
//
//				// Analyze the struct or union 
//				StructOrUnionTypeEntry prevStructOrUnion = (StructOrUnionTypeEntry) tePtr.getBasicType();
//				TypeEntryWithAttributes memberType = prevStructOrUnion.getMemberType(postfixId);
//				if(memberType == null || memberType.getBasicType() == null){
//					errorHandler.addError(sourceFileName, location, postfixId + ":" , "", ErrorHandler.E_NOT_MEMBER_OF_STRUCT_OR_UNION);
//					return tePrevExpr;
//				}
//
//				tePrevExpr = new TranslatedExpAndType();
//				memberType.setIsLValue(true);
//				tePrevExpr.setEntry(memberType);	
//
//				// TODO Deferencing tree to built and set here
//
//			}
//			else if(pfExpr.getType() == PostfixExpr.DECR || pfExpr.getType() == PostfixExpr.INCR){
//				prevTypeWasFuncCall = false;
//
//				String errMessage = ErrorHandler.E_CANNOT_DECREMENT_A_CONSTANT;
//
//				int operatorType = pfExpr.getType();
//				if(operatorType == PostfixExpr.INCR){
//					errMessage = ErrorHandler.E_CANNOT_INCREMENT_A_CONSTANT;
//				}
//
//				if(tePrevExpr.getTypeEntry().getBasicType() instanceof ArrayTypeEntry){
//					// Cannot increment a constant
//					errorHandler.addError(sourceFileName, location, "", "", errMessage);
//				}
//				else{  // No error, create the tree
//					tePrevExpr.getTypeEntry().setIsLValue(false);
//					AssemType assemTypeChild = tePrevExpr.getAssemType();
//
//					// This will addressed at the end (after the entire assignment expression is analyzed)
//					if(postfixOperativeExprs == null){
//						postfixOperativeExprs = new HashMap<AssemExp, Integer>();
//					}
//					postfixOperativeExprs.put((AssemExp)assemTypeChild, operatorType );
//
//					tePrevExpr.setAssemType(assemTypeChild);
//				}				
//			}			
//			else if(pfExpr.getType() == PostfixExpr.ARRAY_REF){
//				prevTypeWasFuncCall = false;
//
//				RootExpr arrayExpr = pfExpr.getArrayExpr();
//				TranslatedExpAndType teArrayExpr = translateExpr(arrayExpr);
//				analyzeArrayIndexExpr(teArrayExpr.getTypeEntry(), location);
//
//				TypeEntryWithAttributes teWithAttrsPrev = tePrevExpr.getTypeEntry();
//				BasicType btPrev = teWithAttrsPrev.getBasicType();
//
//				if(btPrev instanceof TypeDefNameTypeEntry){
//					TypeDefNameTypeEntry tdPrev = (TypeDefNameTypeEntry) btPrev;
//					btPrev = tdPrev.getActualBasicType().getBasicType();
//				}
//
//				if(!(btPrev instanceof ArrayTypeEntry ||btPrev instanceof PointerTypeEntry)){
//					// Trying to refer to an element when prev expression is neither nor a pointer nor an array
//					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_CANNOT_ACCESS_ELEMENTS_OF_A_NON_ARRAY);
//				}	
//				else{
//					TypeEntryWithAttributes teWithAttrsNew = new TypeEntryWithAttributes();
//					if(btPrev instanceof ArrayTypeEntry){
//						ArrayTypeEntry ateEntry = (ArrayTypeEntry) btPrev;
//						teWithAttrsNew = ateEntry.getBaseTypeEntry();
//
//						// TODO Create tree for a array element reference
//
//					}
//					else{
//						// Must be PointerTypeEntry
//						PointerTypeEntry pteEntry = (PointerTypeEntry) btPrev;
//						teWithAttrsNew = pteEntry.getBaseTypeEntry();
//						// TODO Create tree for a reference at a location
//					}
//					teWithAttrsNew.setIsLValue(true);   // Is addressable, present in memory
//
//					tePrevExpr.setEntry(teWithAttrsNew);
//				}				
//			}
//
//			pfExpr = pfExpr.getPostfixExpr();
//		}
//
//		if(tePrevExpr == null)
//			tePrevExpr = new TranslatedExpAndType();  // Return empty translated expression and type
//
//		return tePrevExpr;
//
//	}
//
//	private TranslatedExpAndType translateFunctionCall(FunctionEntry funcEntry, ArgumentExpressionList ael, 
//			SourceLocation location){
//
//		Vector<VariableEntry> formals = funcEntry.getFormals();
//
//		Stack<AssemType> argsStack = new Stack<AssemType>();
//		Vector<AssemType> translatedParamAssemTypes = new Vector<AssemType>();
//
//		int paramCount = 0;
//
//		// Verify formals against argument expression list
//		if(formals == null || formals.size() == 0){
//			if(ael != null){
//				while(ael != null){
//					AssignmentExpr assgnExpr = ael.getAssignmentExpr();
//					translateAssignmentExpr(assgnExpr);
//
//					ael = ael.getArgumentExpressionList();
//				}
//				errorHandler.addError(sourceFileName, location, funcEntry.getName(), "", 
//						ErrorHandler.E_PARAMETERS_PASSED_TO_FUNC_ACCEPTING_VOID);
//			}
//		}
//		else{
//			if(ael == null){
//				errorHandler.addError(sourceFileName, location, funcEntry.getName(), "", 
//						ErrorHandler.E_NO_PARAMETERS_PASSED_TO_FUNC_ACCEPTING_NON_VOID_ARGS);
//			}
//			else{
//				int count = 0;
//				while(ael != null){
//					AssignmentExpr assgnExpr = ael.getAssignmentExpr();
//					TranslatedExpAndType teParam = translateAssignmentExpr(assgnExpr);
//
//					if(count < formals.size()){
//						VariableEntry formalEntry = formals.elementAt(count);
//
//						checkAssignmentCompatibility(formalEntry.getType(), teParam.getTypeEntry(), location, 
//								SemanticAnalyzer.ASSIGNMENT_IN_PARAMETER_PASSING, AssignmentOperator.ASSIGN,
//								funcEntry.getName(), count+1);									
//
//					}
//					else{
//						if(!funcEntry.isEndsWithEllipses())
//							errorHandler.addError(sourceFileName, location, funcEntry.getName(), "", ErrorHandler.E_WRONG_NUMBER_OF_PARAMETERS_PASSED_TO_FUNC);
//					}
//
//					if(!errorHandler.isFoundStrictError()){
//						argsStack.push(teParam.getAssemType());
//						translatedParamAssemTypes.add(teParam.getAssemType());
//					}
//
//					ael = ael.getArgumentExpressionList();
//					count++;
//				}
//
//				// Check if lesser parameters have been passed than expected
//				if(count < formals.size()){
//					errorHandler.addError(sourceFileName, location, funcEntry.getName(), "", 
//							ErrorHandler.E_WRONG_NUMBER_OF_PARAMETERS_PASSED_TO_FUNC);
//				}							
//			}
//		}
//
//		AssemType callTree = null;
//		boolean hasReturnValue = true;
//		TypeEntryWithAttributes teRet = funcEntry.getReturnType();
//		if(teRet == null)
//			hasReturnValue = false;
//		else {
//			if(teRet.getBasicType() instanceof VoidTypeEntry)
//				hasReturnValue = false;
//		}
//
//		if(!errorHandler.isFoundStrictError()){   // No error, translate to tree
//			Label fLabel = new Label(funcEntry.getName());
//			AssemValueProperties retVp = null;
//			if(hasReturnValue){
//				retVp = getAssemValueProperties(teRet);
//			}
//			callTree = translatingMediator.translateCallExp(fLabel, argsStack, 
//					paramCount, retVp);
//
//			// All the arguments above are used in the function call; update the temporaries (if any)
//			AssemCallExp assemCallExp = (AssemCallExp) callTree;
//			if(assemCallExp != null){
//				AssemExpList assemParamTypes = assemCallExp.getArgs();
//				if(assemParamTypes != null){
//					for(AssemType assemType : translatedParamAssemTypes){
//						translatingMediator.updateTempAndUses(assemType, assemCallExp, tempVsListOfUseTree);
//					}
//				}
//			}
//		}
//
//		TranslatedExpAndType retValue = new TranslatedExpAndType();
//		funcEntry.getReturnType().setIsLValue(false);
//		retValue.setEntry(funcEntry.getReturnType());
//		retValue.setAssemType(callTree);
//
//		return retValue;
//
//	}
//
//	private TranslatedExpAndType translatePrimaryExpr(PrimaryExpr primaryExpr){
//
//		TranslatedExpAndType retValue = new TranslatedExpAndType();
//
//		TypeEntryWithAttributes teWithAttrs = new TypeEntryWithAttributes();
//
//		int type = primaryExpr.getType();
//		if(type == PrimaryExpr.CONSTANT_TYPE){
//			AssemType constTree = null;
//			Constant cst = primaryExpr.getConstant();
//
//			String val = null;
//			int cstType = cst.getType();
//
//			if(cstType == Constant.INT_CONST) { // Int constant
//				val = cst.getIntConstant();
//				teWithAttrs.setBasicType(IntTypeEntry.getInstance());				
//				constTree = translatingMediator.translateConstant(val, TranslatingMediator.CONSTANT_INT);
//			}
//			else if(cstType == Constant.CHAR_CONST) {  // Char constant								
//				val = cst.getCharConstant();
//				teWithAttrs.setBasicType(CharTypeEntry.getInstance());
//				constTree = translatingMediator.translateConstant(val, TranslatingMediator.CONSTANT_CHAR);
//			}
//			else{  // Floating point constant - float or double				
//				val = cst.getFloatingConstant();
//				if(val.endsWith("f") || val.endsWith("F")){ // Must be a float					
//					teWithAttrs.setBasicType(FloatTypeEntry.getInstance());
//					constTree = translatingMediator.translateConstant(val, TranslatingMediator.CONSTANT_FLOAT);
//				}
//				else{
//					teWithAttrs.setBasicType(DoubleTypeEntry.getInstance());
//					constTree = translatingMediator.translateConstant(val, TranslatingMediator.CONSTANT_DOUBLE);
//				}
//			}
//			teWithAttrs.setLiteralValue(val);
//			teWithAttrs.setIsLValue(false);
//			retValue.setEntry(teWithAttrs);
//			retValue.setAssemType(constTree);				
//		}
//		else if(type == PrimaryExpr.IDENTIFIER_TYPE){
//			String identifier = primaryExpr.getIdentifier();
//			Symbol idSym = Symbol.symbol(identifier);
//			VariableOrFunctionEntry identifierTypeEntry = (VariableOrFunctionEntry)
//			environments.getInstanceVariableTable().get(idSym);			
//
//			if(identifierTypeEntry == null){
//				errorHandler.addError(sourceFileName, new SourceLocation(primaryExpr.getLineNum(),
//						primaryExpr.getPos()), idSym.toString(), "", ErrorHandler.E_NOT_DEFINED);
//			}
//			else{ // Identifier has been declared
//				AssemType varTree = null;
//				if(identifierTypeEntry.getCategory() == VariableOrFunctionEntry.VARIABLE){
//					VariableEntry varEntry = (VariableEntry) identifierTypeEntry;
//					teWithAttrs = varEntry.getType();
//					BasicType bt = teWithAttrs.getBasicType();
//
//					if(bt instanceof ArrayTypeEntry || teWithAttrs.isEnumConstant())
//						teWithAttrs.setIsLValue(false);					
//					else
//						teWithAttrs.setIsLValue(true);
//
//					retValue.setEntry(teWithAttrs);
//					retValue.setVariableName(identifier);
//
//					Temp regTemp = varEntry.getRegisterTemp();
//					AssemValueProperties assemValueProperties = getAssemValueProperties(varEntry.getType());
//					if(regTemp != null)   // This variable is in a register
//						varTree = translatingMediator.translateTemp(varEntry.getRegisterTemp(), assemValueProperties);
//					else{
//						// This variable is in memory
//						int offset = varEntry.getOffSetFromFP();
//						varTree = translatingMediator.createValueAtAddressWithOffsetFromFP(offset, assemValueProperties);
//					}
//				}
//				else{  // This is a function reference, return a pointer to it
//					FunctionEntry funcEntry = (FunctionEntry) identifierTypeEntry;
//					teWithAttrs = new TypeEntryWithAttributes();
//					retValue.setEntry(teWithAttrs);
//
//					PointerTypeEntry ptrEntry = new PointerTypeEntry();					
//					TypeEntryWithAttributes ptrBase = new TypeEntryWithAttributes();
//					ptrBase.setBasicType(funcEntry);		
//					ptrEntry.setBaseTypeEntry(ptrBase);
//
//					teWithAttrs.setBasicType(ptrEntry);  
//
//					// TODO varTree to get location of function label here
//				}				
//				retValue.setAssemType(varTree);				
//			}			
//		}
//		else if(type == PrimaryExpr.STRING_TYPE){
//			String str = primaryExpr.getString();			
//			teWithAttrs.setBasicType(StringTypeEntry.getInstance());
//			teWithAttrs.setIsLValue(false);
//			teWithAttrs.setLiteralValue(str);
//			retValue.setEntry(teWithAttrs);
//
//			AssemName stringLiteralTree = (AssemName)translatingMediator.translateConstant(
//					str, TranslatingMediator.CONSTANT_STRING);
//			retValue.setAssemType(stringLiteralTree);
//
//			TargetDataType targetDataType = new TargetDataType();
//			targetDataType.setType(TargetDataType.DATA_TYPE_STRING);
//			targetDataType.setValue(str);
//
//			Label label = stringLiteralTree.getLabel();
//
//			if(currentFunctionDef != null)
//				currentFunctionDef.setStringLabel(targetDataType, label); 
//			else{
//				// TODO where do we record the string when the function def is null?
//				// This is the case when we have a string literal in the an external declaration.
//			}
//		}
//		else {   // this primary expression is of the form (expression)
//			RootExpr rootExpr = primaryExpr.getExpr();
//			return translateExpr(rootExpr);
//		}
//
//		return retValue;
//	}
//
//	// UTILITY FUNCTIONS
//
//	private void analyzeArraySizeExpr(TypeEntryWithAttributes val, SourceLocation location){
//
//		BasicType valBasicType = val.getBasicType();
//		if((valBasicType == IntTypeEntry.getInstance() || valBasicType == ShortTypeEntry.getInstance()
//				|| valBasicType == CharTypeEntry.getInstance() || valBasicType == LongTypeEntry.getInstance())){
//			if(val.getLiteralValue() == null){ // Is an expression
//				errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_ARRAY_SIZE_NOT_CONSTANT);
//			}
//		}
//		else{
//			errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_ARRAY_SIZE_SPECIFIER_IS_INVALID);
//		}		
//	}
//
//	private void analyzeArrayIndexExpr(TypeEntryWithAttributes val, SourceLocation location){
//
//		BasicType valBasicType = val.getBasicType();
//		if(!(valBasicType instanceof IntTypeEntry)){
//			errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_ARRAY_SUBSCRIPT_NOT_INTEGER);
//		}
//	}
//
//
//	/**
//	 * 
//	 */
//
//	// TODO Complete this function
//	private BasicType getTranslatedExprTypePostOperation(TypeEntryWithAttributes teFirstType, 
//			TypeEntryWithAttributes teSecondType, SourceLocation location, String operatorType){		
//
//		BasicType firstType = null;
//		BasicType secondType = null;
//
//		// If either type is not defined, the error message would have been already set, so ignore and continue
//		if(teFirstType != null) {
//			firstType = teFirstType.getBasicType();
//		}
//		if(teSecondType != null) {
//			secondType = teSecondType.getBasicType();			
//		}
//
//		// If both are null values, return an Integer type
//		if(firstType == null && secondType == null){
//			return IntTypeEntry.getInstance();
//		}
//
//		// If any one of them is a struct or union, flag an error
//		if(firstType instanceof StructOrUnionTypeEntry || secondType instanceof StructOrUnionTypeEntry){
//			errorHandler.addError(sourceFileName, location, operatorType.toString() + ":","",
//					ErrorHandler.E_INVALID_OPERATOR_ON_STRUCT_UNION);
//			return IntTypeEntry.getInstance();
//		}
//
//		if(firstType instanceof PointerTypeEntry){  // First operand is pointer instance
//			if(secondType instanceof PointerTypeEntry){ // Second operand is also pointer instance
//				if(!operatorType.equals(SUBTRACT)){ // Not a subtraction
//					if(!(operatorType.equals(LESSER_THAN) || operatorType.equals(LESSER_THAN_OR_EQUAL_TO)
//							|| operatorType.equals(GREATER_THAN) || operatorType.equals(GREATER_THAN_OR_EQUAL_TO)
//							|| operatorType.equals(EQUALS) || operatorType.equals(NOT_EQUALS)  )){
//						// Not a relational operator either, flag an error
//						errorHandler.addError(sourceFileName, location, operatorType, "", ErrorHandler.E_INVALID_OPERATOR_ON_POINTERS);
//						return firstType;
//					}	
//					else{
//						// Is a relational operator, check if they are compatible
//						BasicType btPteFirst = ((PointerTypeEntry) firstType).getBaseTypeEntry().getBasicType();
//						BasicType btPteSecond = ((PointerTypeEntry) secondType).getBaseTypeEntry().getBasicType();
//						if(!btPteFirst.equals(btPteSecond)){
//							// Not same type of pointers, flag an error or warning if the rhs is not a void type							
//							errorHandler.addError(sourceFileName, location, operatorType,"",
//									ErrorHandler.E_COMPARING_INCOMPATIBLE_POINTER_TYPES);
//						}	
//						return IntTypeEntry.getInstance();
//					}
//				}
//				else{
//					// Subtracting two pointers, check if the types pointed to are compatible
//					PointerTypeEntry pteFirst = (PointerTypeEntry) firstType;
//					PointerTypeEntry pteSecond = (PointerTypeEntry) secondType;
//
//					BasicType btPteFirst = pteFirst.getBaseTypeEntry().getBasicType();
//					BasicType btPteSecond = pteSecond.getBaseTypeEntry().getBasicType();
//
//					if(!btPteFirst.equals(btPteSecond)){
//						errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_SUBTRACTING_INCOMPATIBLE_POINTERS);
//					}
//
//					return IntTypeEntry.getInstance();
//				}				
//			}
//			else if(secondType == IntTypeEntry.getInstance() || secondType == CharTypeEntry.getInstance() 
//					|| secondType == ShortTypeEntry.getInstance()  || secondType == LongTypeEntry.getInstance() ){
//				// Second type is an integer type, check compatibilities based on the operator
//				if(!(operatorType.equals(ADD) || operatorType.equals(SUBTRACT))){					
//					// Not an addition or a subtraction, check for relational operator
//					if(!(operatorType.equals(LESSER_THAN) || operatorType.equals(LESSER_THAN_OR_EQUAL_TO)
//							|| operatorType.equals(GREATER_THAN) || operatorType.equals(GREATER_THAN_OR_EQUAL_TO)
//							|| operatorType.equals(EQUALS) || operatorType.equals(NOT_EQUALS)  )){
//						// Not a relational operator either, flag an error
//						errorHandler.addError(sourceFileName, location, operatorType, "", ErrorHandler.E_INVALID_OPERATOR_ON_POINTER_AND_INTEGER);
//					}
//					else{
//						// Only zero can be compared to a pointer
//						String litValue = teSecondType.getLiteralValue();
//						if(litValue == null){
//							errorHandler.addError(sourceFileName, location, operatorType,"",
//									ErrorHandler.E_INVALID_COMPARISON_OF_POINTER_AND_INTEGER);
//						}
//						else{
//							if(!(litValue.equals("0") || litValue.equals("00") || litValue.equals("0x0"))){
//								errorHandler.addError(sourceFileName, location, operatorType,"",
//										ErrorHandler.E_INVALID_COMPARISON_OF_POINTER_AND_INTEGER);
//							}
//						}
//					}					
//					return firstType;
//				}
//			}
//		}
//		else if(firstType instanceof ArrayTypeEntry){
//			if(secondType == IntTypeEntry.getInstance()){
//				return firstType;
//			}
//		}
//		if(firstType == IntTypeEntry.getInstance()){
//			if(secondType instanceof ArrayTypeEntry){
//				return secondType;
//			}
//			else if(secondType instanceof PointerTypeEntry){
//				return secondType;
//			}
//		}
//		// Check which is "wider"
//		if(secondType == DoubleTypeEntry.getInstance()){
//			return secondType;
//		}
//		return firstType;
//	}
//
//	/**
//	 *  Checks if the two types are compatible, i.e., if the rhs can be assigned to the lhs correctly.
//	 *  If the two types are incompatible, a warning or an error is noted, depending on the severity of 
//	 *  the incompatibility.
//	 */
//
//	private void checkAssignmentCompatibility(TypeEntryWithAttributes lhsTeWithAttrs,  
//			TypeEntryWithAttributes rhsTeWithAttrs, SourceLocation location, short context, int operatorType,
//			String functionName, int paramNum){		
//
//		if(lhsTeWithAttrs == null || rhsTeWithAttrs == null)  // Error that has been handled already, just return
//			return;
//
//		ErrorHandler errorHandler = ErrorHandler.getInstance();
//
//		BasicType lhsTypeEntry = lhsTeWithAttrs.getBasicType();
//		BasicType rhsTypeEntry = rhsTeWithAttrs.getBasicType();		
//
//		// If lhs or rhs is a typedef, get the actual basic type
//		if(lhsTypeEntry instanceof TypeDefNameTypeEntry){
//			TypeDefNameTypeEntry tdEntry = (TypeDefNameTypeEntry) lhsTypeEntry;
//			TypeEntryWithAttributes basicTypeOfTypeDef = tdEntry.getActualBasicType();
//			basicTypeOfTypeDef.copy(lhsTeWithAttrs, true);
//			lhsTeWithAttrs = basicTypeOfTypeDef;
//			lhsTypeEntry = lhsTeWithAttrs.getBasicType();
//			lhsTeWithAttrs.setIsLValue(true);
//		}
//		if(rhsTypeEntry instanceof TypeDefNameTypeEntry){
//			TypeDefNameTypeEntry tdEntry = (TypeDefNameTypeEntry) rhsTypeEntry;
//			rhsTeWithAttrs = tdEntry.getActualBasicType();
//			rhsTypeEntry = rhsTeWithAttrs.getBasicType();
//		}
//
//		if(context == SemanticAnalyzer.ASSIGNMENT_IN_ASSIGNMENT_EXPR){
//			// Left side of assignment must be a modifiable l-value
//			if(!lhsTeWithAttrs.isLValue()){
//				errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE);
//			}
//
//			if(lhsTeWithAttrs.isConst()){
//				errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_LEFT_SIDE_IS_CONSTANT);
//			}
//
//			// Neither lhs nor rhs can be void
//			if(lhsTeWithAttrs.isVoid()){
//				errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_LEFT_SIDE_IS_VOID);
//			}
//			if(rhsTeWithAttrs.isVoid()){
//				errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_RIGHT_SIDE_IS_VOID);
//			}			
//		}
//		else if(context == SemanticAnalyzer.ASSIGNMENT_IN_DECLR_INIT_EXPR){
//			// Left hand side of assignment can be an  array type while right-side can be an array 
//			// initializer or a string if it is a char array type
//			if(lhsTypeEntry instanceof ArrayTypeEntry){
//				ArrayTypeEntry arrTeLhs = (ArrayTypeEntry) lhsTypeEntry;
//
//				if(rhsTypeEntry instanceof ArrayTypeEntry){
//					ArrayTypeEntry arrTeRhs = (ArrayTypeEntry) rhsTypeEntry;
//					// Rhs is also an array, check the compatibility
//					checkArrayTypesInDeclInit(arrTeLhs, arrTeRhs, location);										
//				}
//				else if(rhsTypeEntry == StringTypeEntry.getInstance()){
//					// Rhs is a string, check if the base type of the left side is a char
//					if(arrTeLhs.getBaseTypeEntry().getBasicType() != CharTypeEntry.getInstance()){
//						errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_INVALID_INITIALIZATION);
//					}
//					else{
//						// Check possible overflow (array size would have already been analyzed, so don't do any checks here
//						// for validity of array size)
//						TypeEntryWithAttributes teDim = arrTeLhs.getDimension();
//						if(teDim != null){
//							String arraySizeLit = teDim.getLiteralValue();
//							if(arraySizeLit != null){
//								try{
//									int arrSize = Integer.parseInt(arraySizeLit);
//									if(arrSize < rhsTeWithAttrs.getLiteralValue().length()){
//										errorHandler.addError(sourceFileName, location, rhsTeWithAttrs.getLiteralValue(), 
//												"", ErrorHandler.E_STRING_ARRAY_BOUNDS_OVERFLOW);
//									}
//								}
//								catch(NumberFormatException nfe){}
//							}
//						}
//					}
//				}					
//			}
//		}
//
//		String literalValue = rhsTeWithAttrs.getLiteralValue();  // Get the literal value
//
//		if(lhsTypeEntry == rhsTypeEntry) {
//			if(literalValue == null){
//				//TODO check other attributes also (like auto, const, etc)
//				return;
//			}
//
//			// The rhs is a literal, check if there is any overflow of values
//			checkTypeAgainstLiteralValue(lhsTeWithAttrs, literalValue, location);
//			return;
//		}
//
//		// The lhs and rhs are two different types; check compatibility if narrowing conversion is possible
//		String additionalInfoMsg = "";
//		if(context == SemanticAnalyzer.ASSIGNMENT_IN_FUNC_RETURN_CONTEXT)
//			additionalInfoMsg = ErrorHandler.AI_INCOMPATIBLE_TYPE_IN_RETURN;
//
//		if(lhsTypeEntry == CharTypeEntry.getInstance()){  // lhs is of type char, check rhs
//
//			if(literalValue != null ){  // rhs is a const value, check if it ok to assign to a char
//				if(rhsTypeEntry == DoubleTypeEntry.getInstance() || rhsTypeEntry == FloatTypeEntry.getInstance()){					
//					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_CONST_LITERAL_OVERFLOW);
//					return;
//				}
//				else if(rhsTypeEntry instanceof StringTypeEntry){
//					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_ASSIGNING_A_STRING_TO_AN_INTEGER_TYPE);
//					return;
//				}
//
//				if(!lhsTeWithAttrs.isUnsigned() && !Constant.canBeSignedChar(literalValue))
//					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_CONST_LITERAL_OVERFLOW);
//				if(lhsTeWithAttrs.isUnsigned() && !Constant.canBeUnSignedChar(literalValue))
//					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_CONST_LITERAL_OVERFLOW);				
//			}
//			else{
//				if(rhsTypeEntry == ShortTypeEntry.getInstance()){
//					errorHandler.addError(sourceFileName, location, additionalInfoMsg,  "", ErrorHandler.E_SHORT_TO_CHAR_NARROWING);
//				}
//				else if(rhsTypeEntry == FloatTypeEntry.getInstance()){  
//					errorHandler.addError(sourceFileName, location, additionalInfoMsg,  "", ErrorHandler.E_FLOAT_TO_CHAR_NARROWING);
//				}
//				else if(rhsTypeEntry == DoubleTypeEntry.getInstance()){  
//					errorHandler.addError(sourceFileName, location, additionalInfoMsg,  "", ErrorHandler.E_DOUBLE_TO_CHAR_NARROWING);
//				}
//				else if(rhsTypeEntry == LongTypeEntry.getInstance()){  
//					errorHandler.addError(sourceFileName, location, additionalInfoMsg,  "", ErrorHandler.E_LONG_TO_CHAR_NARROWING);
//				}
//				else if(rhsTypeEntry instanceof StructOrUnionTypeEntry){
//					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES);
//				}				
//				else if(rhsTypeEntry instanceof PointerTypeEntry || rhsTypeEntry instanceof ArrayTypeEntry){ // Rhs type is a pointer type
//					errorHandler.addError(sourceFileName, location, additionalInfoMsg,  "", ErrorHandler.E_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE);
//				}
//			}			
//		}
//		else if(lhsTypeEntry == ShortTypeEntry.getInstance()){  // lhs is of type short, check rhs
//			if(literalValue != null ){  // rhs is a const value, check if it ok to assign to a short				
//				if(rhsTypeEntry == DoubleTypeEntry.getInstance() || rhsTypeEntry == FloatTypeEntry.getInstance()){
//					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_CONST_LITERAL_OVERFLOW);
//					return ;
//				}
//
//				if(!lhsTeWithAttrs.isUnsigned() && !Constant.canBeSignedShort(literalValue))
//					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_CONST_LITERAL_OVERFLOW);
//				if(lhsTeWithAttrs.isUnsigned() && !Constant.canBeUnSignedShort(literalValue))
//					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_CONST_LITERAL_OVERFLOW);				
//			}
//			else{
//				if(rhsTypeEntry == FloatTypeEntry.getInstance()){  
//					errorHandler.addError(sourceFileName, location, additionalInfoMsg,  "", ErrorHandler.E_FLOAT_TO_SHORT_NARROWING);
//				}
//				if(rhsTypeEntry == DoubleTypeEntry.getInstance()){  
//					errorHandler.addError(sourceFileName, location, additionalInfoMsg,  "", ErrorHandler.E_DOUBLE_TO_SHORT_NARROWING);
//				}
//				if(rhsTypeEntry == LongTypeEntry.getInstance()){  
//					errorHandler.addError(sourceFileName, location, additionalInfoMsg,  "", ErrorHandler.E_LONG_TO_SHORT_NARROWING);
//				}
//				else if(rhsTypeEntry instanceof StructOrUnionTypeEntry){
//					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES);
//				}				
//				else if(rhsTypeEntry instanceof PointerTypeEntry || rhsTypeEntry instanceof ArrayTypeEntry){ // Rhs type is a pointer type
//					errorHandler.addError(sourceFileName, location, additionalInfoMsg,  "", ErrorHandler.E_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE);
//				}
//			}						
//		}
//		else if(lhsTypeEntry == IntTypeEntry.getInstance() || lhsTypeEntry instanceof EnumSpecTypeEntry){ // lhs is of type int, check rhs	
//			if(literalValue != null ){  // rhs is a const value, check if it ok to assign to a int
//				if(rhsTypeEntry == DoubleTypeEntry.getInstance() || rhsTypeEntry == FloatTypeEntry.getInstance()){					
//					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_CONST_LITERAL_OVERFLOW);
//					return ;
//				}
//				else if(rhsTypeEntry == StringTypeEntry.getInstance() || rhsTypeEntry instanceof PointerTypeEntry){
//					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE);
//					return ;
//				}
//
//				if(!lhsTeWithAttrs.isUnsigned() && !Constant.canBeSignedInt(literalValue))
//					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_CONST_LITERAL_OVERFLOW);
//				if(lhsTeWithAttrs.isUnsigned() && !Constant.canBeUnSignedInt(literalValue))
//					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_CONST_LITERAL_OVERFLOW);				
//			}
//			else{
//				if(rhsTypeEntry == DoubleTypeEntry.getInstance()){  
//					errorHandler.addError(sourceFileName, location, additionalInfoMsg,  "", ErrorHandler.E_DOUBLE_TO_INT_NARROWING);
//				}
//				if(rhsTypeEntry == FloatTypeEntry.getInstance()){  
//					errorHandler.addError(sourceFileName, location, additionalInfoMsg, "", ErrorHandler.E_FLOAT_TO_INT_NARROWING);
//				}
//				else if(rhsTypeEntry instanceof PointerTypeEntry){ // Rhs type is a pointer type
//					errorHandler.addError(sourceFileName, location, additionalInfoMsg,  "", ErrorHandler.E_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE);
//				}
//				else if(rhsTypeEntry instanceof ArrayTypeEntry){ // Rhs type is an array address
//					errorHandler.addError(sourceFileName, location, additionalInfoMsg,  "", ErrorHandler.E_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE);
//				}
//				else if(rhsTypeEntry instanceof StructOrUnionTypeEntry){ // Rhs type is a struct or union
//					errorHandler.addError(sourceFileName, location, additionalInfoMsg,  "", ErrorHandler.E_ASSIGNING_STRUCT_OR_UNION_TO_AN_INTEGER_TYPE);
//				}
//			}			
//		}
//		else if(lhsTypeEntry == LongTypeEntry.getInstance()){ // lhs is of type int, check rhs	
//			if(literalValue != null ){  // rhs is a const value, check if it ok to assign to a long
//
//				if(rhsTypeEntry == DoubleTypeEntry.getInstance() || rhsTypeEntry == FloatTypeEntry.getInstance()){
//					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_CONST_LITERAL_OVERFLOW);
//					return ;
//				}
//				else if(rhsTypeEntry == StringTypeEntry.getInstance()){
//					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE);
//					return ;					
//				}
//
//				if(!lhsTeWithAttrs.isUnsigned() && !Constant.canBeSignedLong(literalValue))
//					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_CONST_LITERAL_OVERFLOW);
//				if(lhsTeWithAttrs.isUnsigned() && !Constant.canBeUnSignedLong(literalValue))
//					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_CONST_LITERAL_OVERFLOW);				
//			}
//			else{
//				if(rhsTypeEntry == DoubleTypeEntry.getInstance()){  
//					errorHandler.addError(sourceFileName, location, additionalInfoMsg,  "", ErrorHandler.E_DOUBLE_TO_LONG_NARROWING);
//				}
//				if(rhsTypeEntry == FloatTypeEntry.getInstance()){  
//					errorHandler.addError(sourceFileName, location, additionalInfoMsg,  "", ErrorHandler.E_FLOAT_TO_LONG_NARROWING);
//				}
//				else if(rhsTypeEntry instanceof PointerTypeEntry){ // Rhs type is a pointer type
//					errorHandler.addError(sourceFileName, location, additionalInfoMsg,  "", ErrorHandler.E_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE);
//				}
//				else if(rhsTypeEntry instanceof ArrayTypeEntry){ // Rhs type is an array address
//					errorHandler.addError(sourceFileName, location, additionalInfoMsg,  "", ErrorHandler.E_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE);
//				}
//				else if(rhsTypeEntry instanceof StructOrUnionTypeEntry){ // Rhs type is a struct or union
//					errorHandler.addError(sourceFileName, location, additionalInfoMsg,  "", ErrorHandler.E_ASSIGNING_STRUCT_OR_UNION_TO_AN_INTEGER_TYPE);
//				}
//			}		
//		}
//		else if(lhsTypeEntry == FloatTypeEntry.getInstance()){// lhs is of type float, check rhs
//
//			if(rhsTypeEntry == DoubleTypeEntry.getInstance()){  
//				errorHandler.addError(sourceFileName, location, additionalInfoMsg,  "", ErrorHandler.E_DOUBLE_TO_FLOAT_NARROWING);
//			}
//			if(rhsTypeEntry == LongTypeEntry.getInstance()){  
//				errorHandler.addError(sourceFileName, location, additionalInfoMsg,  "", ErrorHandler.E_LONG_TO_FLOAT_NARROWING);
//			}
//			if(rhsTypeEntry == IntTypeEntry.getInstance() || rhsTypeEntry instanceof EnumSpecTypeEntry){  
//				errorHandler.addError(sourceFileName, location, additionalInfoMsg,  "", ErrorHandler.E_INT_TO_FLOAT_NARROWING);
//			}
//			else if(rhsTypeEntry instanceof PointerTypeEntry){ // Rhs type is a pointer type
//				errorHandler.addError(sourceFileName, location, additionalInfoMsg,  "", ErrorHandler.E_ASSIGNING_POINTER_TO_A_FLOATING_POINT_TYPE);
//			}
//			else if(rhsTypeEntry instanceof ArrayTypeEntry){ // Rhs type is an array address
//				errorHandler.addError(sourceFileName, location, additionalInfoMsg,  "", ErrorHandler.E_ASSIGNING_POINTER_TO_A_FLOATING_POINT_TYPE);
//			}
//			else if(rhsTypeEntry instanceof StructOrUnionTypeEntry){ // Rhs type is a struct or union
//				errorHandler.addError(sourceFileName, location, additionalInfoMsg,  "", ErrorHandler.E_ASSIGNING_STRUCT_OR_UNION_TO_A_FLOATING_POINT_TYPE);
//			}
//			else if(rhsTypeEntry == StringTypeEntry.getInstance()){
//				errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES);
//				return ;					
//			}
//		}
//		else if(lhsTypeEntry == DoubleTypeEntry.getInstance()){ // lhs is of type double, check rhs
//			if(rhsTypeEntry instanceof PointerTypeEntry || 
//					rhsTypeEntry instanceof ArrayTypeEntry || rhsTypeEntry instanceof StringTypeEntry){ // Rhs type is a pointer type
//				errorHandler.addError(sourceFileName, location, additionalInfoMsg, "" , ErrorHandler.E_ASSIGNING_POINTER_TO_A_FLOATING_POINT_TYPE);
//			}
//			else if(rhsTypeEntry instanceof StructOrUnionTypeEntry){ // Rhs type is a struct or union
//				errorHandler.addError(sourceFileName, location, additionalInfoMsg,  "", ErrorHandler.E_ASSIGNING_STRUCT_OR_UNION_TO_A_DOUBLE_TYPE);
//			}
//		}
//		else if(lhsTypeEntry instanceof PointerTypeEntry){ // lhs is of pointer type, check rhs			
//			if(rhsTypeEntry == CharTypeEntry.getInstance() || rhsTypeEntry == ShortTypeEntry.getInstance() ||
//					rhsTypeEntry == IntTypeEntry.getInstance() || rhsTypeEntry == LongTypeEntry.getInstance()
//					|| rhsTypeEntry instanceof EnumSpecTypeEntry){ 
//				// RHS is an integer type
//				if(operatorType == AssignmentOperator.ASSIGN){
//					// Only zero can be assigned to a pointer
//					String litValue = rhsTeWithAttrs.getLiteralValue();					
//					if(litValue == null){
//						errorHandler.addError(sourceFileName, location, additionalInfoMsg,"",
//								ErrorHandler.E_ASSIGNING_AN_INTEGER_TYPE_TO_A_POINTER);
//					}
//					else{						
//						if(!(litValue.equals("0") || litValue.equals("00") || litValue.equals("0x0"))){
//							errorHandler.addError(sourceFileName, location, additionalInfoMsg,"",
//									ErrorHandler.E_ASSIGNING_AN_INTEGER_TYPE_TO_A_POINTER);
//						}
//					}
//				}
//				else if(operatorType == AssignmentOperator.DIVIDE_ASSIGN){
//					errorHandler.addError(sourceFileName, location, additionalInfoMsg,"",
//							ErrorHandler.E_DIVIDE_ASSIGN_ON_POINTER);
//				}
//				else if(operatorType == AssignmentOperator.MODULO_ASSIGN){
//					errorHandler.addError(sourceFileName, location, additionalInfoMsg,"",
//							ErrorHandler.E_MODULO_ASSIGN_ON_POINTER);
//				}
//				else if(operatorType == AssignmentOperator.MULTIPLY_ASSIGN){
//					errorHandler.addError(sourceFileName, location, additionalInfoMsg,"",
//							ErrorHandler.E_MULTIPLY_ASSIGN_ON_POINTER);
//				}
//			}
//			else if(rhsTypeEntry == DoubleTypeEntry.getInstance() || rhsTypeEntry == FloatTypeEntry.getInstance()){
//				errorHandler.addError(sourceFileName, location, additionalInfoMsg, "" , ErrorHandler.E_ASSIGNING_A_FLOATING_POINT_TYPE_TO_A_POINTER);
//			}
//			else if(rhsTypeEntry instanceof StructOrUnionTypeEntry){
//				errorHandler.addError(sourceFileName, location, additionalInfoMsg, "" , ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES);
//			}
//			else if(rhsTypeEntry instanceof PointerTypeEntry){
//				// Check if the two pointer types are compatible
//				PointerTypeEntry ptLhs = (PointerTypeEntry) lhsTypeEntry;
//				PointerTypeEntry ptRhs = (PointerTypeEntry) rhsTypeEntry;
//
//				TypeEntryWithAttributes tePtrLhs = ptLhs.getBaseTypeEntry();
//				TypeEntryWithAttributes tePtrRhs = ptRhs.getBaseTypeEntry();
//
//				if(!tePtrLhs.equals(tePtrRhs)){
//					// Not same type of pointers, flag an error or warning if the rhs is not a void type
//					if(!(ptRhs.getBaseTypeEntry().getBasicType() == VoidTypeEntry.getInstance() || 
//							ptLhs.getBaseTypeEntry().getBasicType() == VoidTypeEntry.getInstance()))
//						errorHandler.addError(sourceFileName, location, additionalInfoMsg,"",
//								ErrorHandler.E_ASSIGNMENT_INCOMPATIBLE_POINTER_TYPES);
//				}				
//			}	
//			else if(rhsTypeEntry instanceof ArrayTypeEntry){
//				PointerTypeEntry ptLhs = (PointerTypeEntry) lhsTypeEntry;
//				if(context == SemanticAnalyzer.ASSIGNMENT_IN_ASSIGNMENT_EXPR){
//					// Check if the array type is compatible
//					ArrayTypeEntry rhsArr = (ArrayTypeEntry) rhsTypeEntry;			
//					TypeEntryWithAttributes tePtrLhs = ptLhs.getBaseTypeEntry();
//					TypeEntryWithAttributes teArrRhs = rhsArr.getBaseTypeEntry();
//					if(!tePtrLhs.equals(teArrRhs)){   // TODO Check if this is enough under all situations
//						errorHandler.addError(sourceFileName, location, additionalInfoMsg,"",
//								ErrorHandler.E_ASSIGNMENT_INCOMPATIBLE_POINTER_TYPES);
//					}
//				}
//				else if(context == SemanticAnalyzer.ASSIGNMENT_TO_SUB_ELEMENT){
//					// This check relevant only when verifying sub-types. 
//
//					ArrayTypeEntry arrRhs = (ArrayTypeEntry) rhsTypeEntry;
//					checkAssignmentCompatibility(ptLhs.getBaseTypeEntry(), arrRhs.getBaseTypeEntry(),location,
//							SemanticAnalyzer.ASSIGNMENT_TO_SUB_ELEMENT,AssignmentOperator.ASSIGN, null, -1);
//				}				
//			}
//			if(rhsTypeEntry instanceof StringTypeEntry){
//				PointerTypeEntry ptLhs = (PointerTypeEntry) lhsTypeEntry;
//				BasicType btOfLhsPtr = ptLhs.getBaseTypeEntry().getBasicType();
//				if(!(btOfLhsPtr instanceof CharTypeEntry)){  // Not a pointer to a char
//					if(btOfLhsPtr instanceof IntTypeEntry)
//						errorHandler.addError(sourceFileName, location, additionalInfoMsg,"",
//								ErrorHandler.E_ASSIGNING_STRING_TO_POINTER_TO_INT);
//					else if(btOfLhsPtr instanceof EnumSpecTypeEntry )
//						errorHandler.addError(sourceFileName, location, additionalInfoMsg,"",
//								ErrorHandler.E_ASSIGNING_STRING_TO_POINTER_TO_INT);
//					else if(btOfLhsPtr instanceof LongTypeEntry)
//						errorHandler.addError(sourceFileName, location, additionalInfoMsg,"",
//								ErrorHandler.E_ASSIGNING_STRING_TO_POINTER_TO_LONG);
//					else if(btOfLhsPtr instanceof ShortTypeEntry)
//						errorHandler.addError(sourceFileName, location, additionalInfoMsg,"",
//								ErrorHandler.E_ASSIGNING_STRING_TO_POINTER_TO_SHORT);
//					else if(btOfLhsPtr instanceof FloatTypeEntry)
//						errorHandler.addError(sourceFileName, location, additionalInfoMsg,"",
//								ErrorHandler.E_ASSIGNING_STRING_TO_POINTER_TO_FLOAT);
//					else if(btOfLhsPtr instanceof DoubleTypeEntry)
//						errorHandler.addError(sourceFileName, location, additionalInfoMsg,"",
//								ErrorHandler.E_ASSIGNING_STRING_TO_POINTER_TO_DOUBLE);
//					else if(btOfLhsPtr instanceof StructOrUnionTypeEntry)
//						errorHandler.addError(sourceFileName, location, additionalInfoMsg,"",
//								ErrorHandler.E_ASSIGNING_STRING_TO_POINTER_TO_STRUCT);
//					else 
//						errorHandler.addError(sourceFileName, location, additionalInfoMsg,"",
//								ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES);
//				}
//			}
//		}
//		else if(lhsTypeEntry instanceof ArrayTypeEntry){ // lhs is of array type, check each context
//			if(context == SemanticAnalyzer.ASSIGNMENT_IN_PARAMETER_PASSING)
//				arrayDeclaratorIsOk((ArrayTypeEntry)lhsTypeEntry, null, null, SemanticAnalyzer.ASSIGNMENT_IN_PARAMETER_PASSING,
//						location);			
//			else if(context == SemanticAnalyzer.ASSIGNMENT_TO_SUB_ELEMENT){
//				ArrayTypeEntry arrTypeEntryLhs = (ArrayTypeEntry) lhsTypeEntry;
//				ArrayTypeEntry arrTypeEntryRhs = null;
//				if(rhsTypeEntry instanceof ArrayTypeEntry){  // both lhs and rhs are arrays, 
//					// check the dimensions and the subtypes
//					arrTypeEntryRhs = (ArrayTypeEntry) rhsTypeEntry;
//
//					// Check the array dimensions first
//					TypeEntryWithAttributes lhsDimTeAttr = arrTypeEntryLhs.getDimension();
//					TypeEntryWithAttributes rhsDimTeAttr = arrTypeEntryRhs.getDimension();
//					if(lhsDimTeAttr != null && rhsDimTeAttr != null){
//						try{
//							int lhsDim = Integer.parseInt(lhsDimTeAttr.getLiteralValue());
//							int rhsDim = Integer.parseInt(rhsDimTeAttr.getLiteralValue());
//
//							if(lhsDim < rhsDim){
//								errorHandler.addError(sourceFileName, location, additionalInfoMsg, "",
//										ErrorHandler.E_DIFFERENT_ARRAY_SUBSCRIPTS);
//							}
//						}
//						catch(NumberFormatException nfe){ }
//					}
//					// Check the subtypes
//					checkAssignmentCompatibility(arrTypeEntryLhs.getBaseTypeEntry(), arrTypeEntryRhs.getBaseTypeEntry(),
//							location, SemanticAnalyzer.ASSIGNMENT_TO_SUB_ELEMENT,AssignmentOperator.ASSIGN, null, -1);
//				}
//				else{ // Incompatible types
//					errorHandler.addError(sourceFileName, location, additionalInfoMsg,"",
//							ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES);
//				}
//			}
//		}
//		else if(lhsTypeEntry instanceof StructOrUnionTypeEntry){ // lhs is of array type, check each context
//			if(!(rhsTypeEntry instanceof StructOrUnionTypeEntry)){
//				setIncompatibleAssignmentError(context, location, "", functionName, paramNum);
//			}
//			else{
//				// Both are structs (or unions), check the types
//				StructOrUnionTypeEntry stOrUnLhs = (StructOrUnionTypeEntry) lhsTypeEntry;
//				StructOrUnionTypeEntry stOrUnRhs = (StructOrUnionTypeEntry) rhsTypeEntry;
//				if(!stOrUnLhs.equals(stOrUnRhs)){
//					setIncompatibleAssignmentError(context, location, "", functionName, paramNum);
//				}			
//			}
//		}
//	}
//
//	private void setIncompatibleAssignmentError(int context, SourceLocation location, 
//			String additionalInfoMsg, String functionName, int paramNum){
//		if(context == SemanticAnalyzer.ASSIGNMENT_IN_ASSIGNMENT_EXPR)
//			errorHandler.addError(sourceFileName, location, additionalInfoMsg, "",
//					ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES);
//		else if(context == SemanticAnalyzer.ASSIGNMENT_IN_DECLR_INIT_EXPR){
//			errorHandler.addError(sourceFileName, location, additionalInfoMsg, "",
//					ErrorHandler.E_INVALID_INITIALIZATION);
//		}
//		else if(context == SemanticAnalyzer.ASSIGNMENT_IN_PARAMETER_PASSING){
//			String errMsgPreamble = "Argument " + paramNum + " of " + functionName + ": ";
//			errorHandler.addError(sourceFileName, location, errMsgPreamble, "",
//					ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES);
//		}
//	}
//
//	/**
//	 * The lhs and the right side literal value are already confirmed to be of the same type. Applies to the
//	 * basic types: int, short, char, long, double and float).
//	 * @param lhsTeWithAttrs
//	 * @param literalValue
//	 * @param location
//	 */
//	private void checkTypeAgainstLiteralValue(TypeEntryWithAttributes lhsTeWithAttrs, String literalValue,
//			SourceLocation location){
//
//		BasicType lhsTypeEntry = lhsTeWithAttrs.getBasicType();
//
//		if(lhsTypeEntry == CharTypeEntry.getInstance()){
//			if(!lhsTeWithAttrs.isUnsigned() && !Constant.canBeSignedChar(literalValue))
//				errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_CONST_LITERAL_OVERFLOW);
//			if(lhsTeWithAttrs.isUnsigned() && !Constant.canBeUnSignedChar(literalValue))
//				errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_CONST_LITERAL_OVERFLOW);
//		}
//		else if(lhsTypeEntry == ShortTypeEntry.getInstance()){  // lhs is of type short, check rhs
//			if(literalValue != null ){  // rhs is a const value, check if it ok to assign to a char
//				if(!lhsTeWithAttrs.isUnsigned() && !Constant.canBeSignedShort(literalValue))
//					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_CONST_LITERAL_OVERFLOW);
//				if(lhsTeWithAttrs.isUnsigned() && !Constant.canBeUnSignedShort(literalValue))
//					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_CONST_LITERAL_OVERFLOW);				
//			}
//		}
//		else if(lhsTypeEntry == IntTypeEntry.getInstance()){ // lhs is of type int, check rhs	
//			if(literalValue != null ){  // rhs is a const value, check if it ok to assign to a char
//				if(!lhsTeWithAttrs.isUnsigned() && !Constant.canBeSignedInt(literalValue))
//					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_CONST_LITERAL_OVERFLOW);
//				if(lhsTeWithAttrs.isUnsigned() && !Constant.canBeUnSignedInt(literalValue))
//					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_CONST_LITERAL_OVERFLOW);				
//			}
//		}
//		else if(lhsTypeEntry == LongTypeEntry.getInstance()){ // lhs is of type long, check rhs	
//			if(literalValue != null ){  // rhs is a const value, check if it ok to assign to a char
//				if(!lhsTeWithAttrs.isUnsigned() && !Constant.canBeSignedLong(literalValue))
//					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_CONST_LITERAL_OVERFLOW);
//				if(lhsTeWithAttrs.isUnsigned() && !Constant.canBeUnSignedLong(literalValue))
//					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_CONST_LITERAL_OVERFLOW);				
//			}
//		}
//		else if(lhsTypeEntry == FloatTypeEntry.getInstance()){ // lhs is of type float, check rhs	
//			if(literalValue != null ){  // rhs is a const value, check if it ok to assign to a float
//
//				if(!Constant.canBeFloat(literalValue)){
//					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_CONST_TOO_LARGE_FOR_FLOAT);
//				}
//			}
//		}
//		else if(lhsTypeEntry == DoubleTypeEntry.getInstance()){ // lhs is of type double, check rhs	
//			if(literalValue != null ){  // rhs is a const value, check if it ok to assign to a float
//				if(!Constant.canBeDouble(literalValue)){
//					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_CONST_TOO_LARGE_FOR_DOUBLE);
//				}
//			}	
//		}
//		return;
//	}
//
//	private void checkArrayTypesInDeclInit(ArrayTypeEntry arrayDecl, ArrayTypeEntry initArray,
//			SourceLocation location){
//
//		TypeEntryWithAttributes teAttrsLhs = arrayDecl.getBaseTypeEntry();
//		BasicType btArrTeLhs = teAttrsLhs.getBasicType();
//
//		TypeEntryWithAttributes teAttrsRhs = initArray.getBaseTypeEntry();
//		BasicType btArrTeRhs = teAttrsRhs.getBasicType();
//
//		// If the lhs is a pointer to char and the rhs is a string, we are ok
//		if(btArrTeLhs instanceof PointerTypeEntry){
//			PointerTypeEntry pteLhs = (PointerTypeEntry) btArrTeLhs;
//			TypeEntryWithAttributes baseOfPteLhs = pteLhs.getBaseTypeEntry();
//			if(baseOfPteLhs.getBasicType() instanceof CharTypeEntry && btArrTeRhs instanceof StringTypeEntry)
//				return;
//		}
//
//		checkAssignmentCompatibility(teAttrsLhs, teAttrsRhs, location, 
//				SemanticAnalyzer.ASSIGNMENT_IN_DECLR_INIT_EXPR, AssignmentOperator.ASSIGN, null, -1);
//
//		// Check the sizes
//		TypeEntryWithAttributes teDimArray = arrayDecl.getDimension();
//		TypeEntryWithAttributes teDimInit = initArray.getDimension();
//
//		if(teDimArray != null && teDimInit != null){
//			int sizeOfArray = 0;
//			try{
//				sizeOfArray = Integer.parseInt(teDimArray.getLiteralValue());
//			}
//			catch(NumberFormatException nfe){}
//
//			int sizeOfInit = Integer.parseInt(teDimInit.getLiteralValue());
//			if(sizeOfInit > sizeOfArray){
//				ErrorHandler errorHandler = ErrorHandler.getInstance();
//
//				// If the excess initializer error message already exists, don't add it again
//				if(!errorHandler.errorOrWarningAlreadyExists(sourceFileName, location, ErrorHandler.ERROR_MSGS_ONLY, 
//						ErrorHandler.E_EXCESS_INITIALIZERS_IN_ARRAY_INIT)) {
//					// Add this error only if required; this might come up several times
//					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_EXCESS_INITIALIZERS_IN_ARRAY_INIT);					
//				}
//			}	
//		}
//
//		// If the base type are both arrays, check the types again
//		if(btArrTeLhs instanceof ArrayTypeEntry && btArrTeRhs instanceof ArrayTypeEntry){
//			checkArrayTypesInDeclInit((ArrayTypeEntry) btArrTeLhs, (ArrayTypeEntry)btArrTeRhs, location );
//		}
//	}
//
//	private int getSizeOfType(TypeEntryWithAttributes type){
//		// TODO Abstract this out, since type depends on the target machine. Comment below and implement later
//		return 4;
//	}
//
//	/**
//	 * Returns the BinaryOpExpr type for the given Assignment operator type
//	 * 
//	 */
//
//	private int getBinaryOpFromAssignmentOpr(int assgnOprType){
//		int retValue = 0;
//		if(assgnOprType == AssignmentOperator.ASSIGN)   // A simple assignment
//			return -1;
//
//
//		if(assgnOprType == AssignmentOperator.ADD_ASSIGN){
//			retValue = BinaryOpExpr.PLUS;			
//		}
//		else if(assgnOprType == AssignmentOperator.MINUS_ASSIGN){
//			retValue = BinaryOpExpr.MINUS;			
//		}
//		else if(assgnOprType == AssignmentOperator.MULTIPLY_ASSIGN){
//			retValue = BinaryOpExpr.MULTIPLY;		
//		}
//		else if(assgnOprType == AssignmentOperator.DIVIDE_ASSIGN){
//			retValue = BinaryOpExpr.DIVIDE;			
//		}
//		else if(assgnOprType == AssignmentOperator.MODULO_ASSIGN){
//			retValue = BinaryOpExpr.MODULO;			
//		}
//		else if(assgnOprType == AssignmentOperator.BITWISE_AND_ASSIGN){
//			retValue = BinaryOpExpr.AND;		
//		}
//		else if(assgnOprType == AssignmentOperator.BITWISE_XOR_ASSIGN){
//			retValue = BinaryOpExpr.EXCLUSIVE_OR;			
//		}
//		else if(assgnOprType == AssignmentOperator.BITWISE_OR_ASSIGN){
//			retValue = BinaryOpExpr.OR;			
//		}
//		else if(assgnOprType == AssignmentOperator.LEFT_SHIFT_ASSIGN){
//			retValue = BinaryOpExpr.LEFT_SHIFT;		
//		}
//		else{
//			retValue = BinaryOpExpr.RIGHT_SHIFT;
//		}
//
//		return retValue;
//	}
//
//	/**
//	 * Returns an AbstractType object that is created based on the information in TypeEntryWithAttributes
//	 * passed in the argument. 
//	 */
//
//	private AbstractType createAbstractType(TypeEntryWithAttributes teWithAttr){
//		AbstractType abstractType = new AbstractType();
//
//		BasicType basicType = teWithAttr.getBasicType();
//
//		if(basicType == CharTypeEntry.getInstance() || basicType == ShortTypeEntry.getInstance()
//				|| basicType == IntTypeEntry.getInstance() || basicType == LongTypeEntry.getInstance()){
//
//			// Type is integer
//			abstractType.setInteger(true);
//
//			if(basicType == CharTypeEntry.getInstance()){                // Char type, size is 1 byte
//				abstractType.setIntegerSize(AbstractType.BYTE_SIZE);
//			}
//			else if(basicType == ShortTypeEntry.getInstance()){          // Short int type, size is 2 bytes
//				abstractType.setIntegerSize(AbstractType.HALF_WORD);
//			}
//			else if(basicType == IntTypeEntry.getInstance()){            // Int type, size is 4 bytes
//				abstractType.setIntegerSize(AbstractType.WORD_SIZE);
//			}
//			else {                                                       // Long type, size is 8 bytes
//				abstractType.setIntegerSize(AbstractType.DOUBLE_WORD);
//			}
//		}
//		else{
//			// Type is floating point
//			abstractType.setInteger(false);
//
//			if(basicType == FloatTypeEntry.getInstance()){               // Float type, single precision
//				abstractType.setFloatPrecision(AbstractType.SINGLE_PRECISION);
//			}
//			else {                                                       // Double type, double precision
//				abstractType.setFloatPrecision(AbstractType.DOUBLE_PRECISION);
//			}
//		}
//		return abstractType;
//	}
//}

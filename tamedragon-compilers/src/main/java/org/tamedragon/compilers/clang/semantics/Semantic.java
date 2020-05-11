package org.tamedragon.compilers.clang.semantics;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.Stack;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tamedragon.assemblyabstractions.constructs.IRTree;
import org.tamedragon.assemblyabstractions.constructs.IRTreeAggregateData;
import org.tamedragon.assemblyabstractions.constructs.IRTreeAlloca;
import org.tamedragon.assemblyabstractions.constructs.IRTreeBinaryExp;
import org.tamedragon.assemblyabstractions.constructs.IRTreeCallExp;
import org.tamedragon.assemblyabstractions.constructs.IRTreeCast;
import org.tamedragon.assemblyabstractions.constructs.IRTreeConst;
import org.tamedragon.assemblyabstractions.constructs.IRTreeDeclaration;
import org.tamedragon.assemblyabstractions.constructs.IRTreeExp;
import org.tamedragon.assemblyabstractions.constructs.IRTreeExpressionStm;
import org.tamedragon.assemblyabstractions.constructs.IRTreeIndex;
import org.tamedragon.assemblyabstractions.constructs.IRTreeLabel;
import org.tamedragon.assemblyabstractions.constructs.IRTreeMemory;
import org.tamedragon.assemblyabstractions.constructs.IRTreeMove;
import org.tamedragon.assemblyabstractions.constructs.IRTreeStatement;
import org.tamedragon.assemblyabstractions.constructs.IRTreeStatementList;
import org.tamedragon.assemblyabstractions.constructs.IRTreeTempOrVar;
import org.tamedragon.assemblyabstractions.constructs.IRTreeType;
import org.tamedragon.common.Label;
import org.tamedragon.common.llvmir.instructions.AllocaInst;
import org.tamedragon.common.llvmir.instructions.BinaryOperator.BinaryOperatorID;
import org.tamedragon.common.llvmir.instructions.CmpInst.Predicate;
import org.tamedragon.common.llvmir.instructions.LLVMUtility;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionCreationException;
import org.tamedragon.common.llvmir.math.APInt;
import org.tamedragon.common.llvmir.types.APFloat;
import org.tamedragon.common.llvmir.types.ArrayType;
import org.tamedragon.common.llvmir.types.CompilationContext;
import org.tamedragon.common.llvmir.types.ConstantAggregateZero;
import org.tamedragon.common.llvmir.types.ConstantArray;
import org.tamedragon.common.llvmir.types.ConstantExpr;
import org.tamedragon.common.llvmir.types.ConstantFP;
import org.tamedragon.common.llvmir.types.ConstantInt;
import org.tamedragon.common.llvmir.types.ConstantStruct;
import org.tamedragon.common.llvmir.types.FunctionType;
import org.tamedragon.common.llvmir.types.GlobalValue.LinkageTypes;
import org.tamedragon.common.llvmir.types.PointerType;
import org.tamedragon.common.llvmir.types.StructType;
import org.tamedragon.common.llvmir.types.Temp;
import org.tamedragon.common.llvmir.types.Type;
import org.tamedragon.common.llvmir.types.Value;
import org.tamedragon.common.llvmir.types.Value.ValueTypeID;
import org.tamedragon.common.llvmir.types.exceptions.TypeCreationException;
import org.tamedragon.compilers.clang.ErrorHandler;
import org.tamedragon.compilers.clang.SourceLocation;
import org.tamedragon.compilers.clang.abssyntree.AbstractDeclarator;
import org.tamedragon.compilers.clang.abssyntree.Absyn;
import org.tamedragon.compilers.clang.abssyntree.AdditiveExpr;
import org.tamedragon.compilers.clang.abssyntree.AndExpr;
import org.tamedragon.compilers.clang.abssyntree.ArgumentExpressionList;
import org.tamedragon.compilers.clang.abssyntree.AssignmentExpr;
import org.tamedragon.compilers.clang.abssyntree.AssignmentOperator;
import org.tamedragon.compilers.clang.abssyntree.BinaryOpExpr;
import org.tamedragon.compilers.clang.abssyntree.BlockItem;
import org.tamedragon.compilers.clang.abssyntree.BlockItemList;
import org.tamedragon.compilers.clang.abssyntree.CastExpr;
import org.tamedragon.compilers.clang.abssyntree.CompoundStatement;
import org.tamedragon.compilers.clang.abssyntree.ConditionalExpr;
import org.tamedragon.compilers.clang.abssyntree.ConstExpr;
import org.tamedragon.compilers.clang.abssyntree.Constant;
import org.tamedragon.compilers.clang.abssyntree.Declaration;
import org.tamedragon.compilers.clang.abssyntree.DeclarationSpecifiers;
import org.tamedragon.compilers.clang.abssyntree.Declarator;
import org.tamedragon.compilers.clang.abssyntree.DirectDeclarator;
import org.tamedragon.compilers.clang.abssyntree.EnumList;
import org.tamedragon.compilers.clang.abssyntree.EnumSpecifier;
import org.tamedragon.compilers.clang.abssyntree.Enumerator;
import org.tamedragon.compilers.clang.abssyntree.EqualityExpr;
import org.tamedragon.compilers.clang.abssyntree.ExclusiveOrExpr;
import org.tamedragon.compilers.clang.abssyntree.ExprStatement;
import org.tamedragon.compilers.clang.abssyntree.ExternDeclaration;
import org.tamedragon.compilers.clang.abssyntree.FunctionDef;
import org.tamedragon.compilers.clang.abssyntree.IncludeDirective;
import org.tamedragon.compilers.clang.abssyntree.IncludeStatement;
import org.tamedragon.compilers.clang.abssyntree.InclusiveOrExpr;
import org.tamedragon.compilers.clang.abssyntree.InitDeclarator;
import org.tamedragon.compilers.clang.abssyntree.InitDeclaratorList;
import org.tamedragon.compilers.clang.abssyntree.Initializer;
import org.tamedragon.compilers.clang.abssyntree.InitializerList;
import org.tamedragon.compilers.clang.abssyntree.IterationStatement;
import org.tamedragon.compilers.clang.abssyntree.JumpStatement;
import org.tamedragon.compilers.clang.abssyntree.LabeledStatement;
import org.tamedragon.compilers.clang.abssyntree.LogicalAndExpr;
import org.tamedragon.compilers.clang.abssyntree.LogicalOrExpr;
import org.tamedragon.compilers.clang.abssyntree.MultiplicativeExpr;
import org.tamedragon.compilers.clang.abssyntree.ParamDeclaration;
import org.tamedragon.compilers.clang.abssyntree.ParamList;
import org.tamedragon.compilers.clang.abssyntree.ParamTypeList;
import org.tamedragon.compilers.clang.abssyntree.Pointer;
import org.tamedragon.compilers.clang.abssyntree.PostfixExpr;
import org.tamedragon.compilers.clang.abssyntree.PrimaryExpr;
import org.tamedragon.compilers.clang.abssyntree.RelationalExpr;
import org.tamedragon.compilers.clang.abssyntree.RootExpr;
import org.tamedragon.compilers.clang.abssyntree.SelectionStatement;
import org.tamedragon.compilers.clang.abssyntree.ShiftExpr;
import org.tamedragon.compilers.clang.abssyntree.SpecifierListType;
import org.tamedragon.compilers.clang.abssyntree.SpecifierQualifierList;
import org.tamedragon.compilers.clang.abssyntree.Statement;
import org.tamedragon.compilers.clang.abssyntree.StorageClassSpecifiers;
import org.tamedragon.compilers.clang.abssyntree.StructDeclaration;
import org.tamedragon.compilers.clang.abssyntree.StructDeclarationList;
import org.tamedragon.compilers.clang.abssyntree.StructDeclarator;
import org.tamedragon.compilers.clang.abssyntree.StructDeclaratorList;
import org.tamedragon.compilers.clang.abssyntree.StructOrUnion;
import org.tamedragon.compilers.clang.abssyntree.StructOrUnionSpecifier;
import org.tamedragon.compilers.clang.abssyntree.TranslationUnit;
import org.tamedragon.compilers.clang.abssyntree.TypeDefName;
import org.tamedragon.compilers.clang.abssyntree.TypeName;
import org.tamedragon.compilers.clang.abssyntree.TypeOf;
import org.tamedragon.compilers.clang.abssyntree.TypeQualifier;
import org.tamedragon.compilers.clang.abssyntree.TypeQualifierList;
import org.tamedragon.compilers.clang.abssyntree.TypeSpecifier;
import org.tamedragon.compilers.clang.abssyntree.UnaryExpr;
import org.tamedragon.compilers.clang.preprocessor.IncludesPreProcessed;

/**
 * Represents a declarator that has been analyzed; it has a translated type and name
 * @author shreesha
 *
 */

public class Semantic {

	private Environments environments;
	private ErrorHandler errorHandler;
	private Translator translatingMediator;
	private Stack<IterationOrSelectionLabels> iterOrSwitchStack;
	private static int count_of_structures = 0;
	HashSet<String> labels;
	private String sourceFileName;   // The name of the source file being analyzed.	
	private HashSet<String> definedFunctions;   // List of defined functions
	private ClangFunctionDef currentFunctionDef; // FunctionProperties of the function currently being analyzed.
	private HashMap<IRTreeExp, Integer> postfixOperativeExprs;   // List of assembly expressions that need post 
	// operations (increment or decrement) after analyzing current assignment expression
	private Stack<AssignmentExpr> assignmentExprs;   // A stack of assignment expressions curently being analyzed
	private HashMap<String, StructOrUnionTypeEntry> tagVsStructOrUnionTE;
	private Set<BasicType> setOfBasicTypes;
	private Set<StructType> setOfStructType;
	Stack<Label> phiNodeTrueBBLabels;
	public static short ASSIGNMENT_IN_FUNC_RETURN_CONTEXT = 0;
	public static short ASSIGNMENT_IN_ASSIGNMENT_EXPR = 1;
	public static short ASSIGNMENT_IN_PARAMETER_PASSING = 2;
	public static short ASSIGNMENT_IN_DECLR_INIT_EXPR = 3;
	public static short ASSIGNMENT_TO_SUB_ELEMENT = 4;
	public static short ASSIGNMENT_IN_STRUCT_MEMBER_INIT = 5;
	public static short ARRAY_PARAM_ANALYSIS = 6;
	public static short STRUCT_INITIALIZER_TRANSLATION_CONTEXT = 0;
	public static short ARRAY_INITIALIZER_TRANSLATION_CONTEXT = 1;
	public static short COMPOUND_INITIALIZER_TRANSLATION_CONTEXT = 2;
	// Binary and other operators
	public static final String LOGICAL_OR = "||";
	public static final String LOGICAL_AND = "&&";
	public static final String INCLUSIVE_OR = "|";
	public static final String EXCLUSIVE_OR = "^";
	public static final String AND = "&";
	public static final String EQUALS = "==";
	public static final String NOT_EQUALS = "!=";
	public static final String LESSER_THAN = "<";
	public static final String GREATER_THAN = ">";
	public static final String GREATER_THAN_OR_EQUAL_TO = ">=";
	public static final String LESSER_THAN_OR_EQUAL_TO = "<=";
	public static final String LEFT_SHIFT = "<<";
	public static final String RIGHT_SHIFT = ">>"; 
	public static final String ADD = "+";
	public static final String SUBTRACT = "-";
	public static final String MULTIPLY = "*";
	public static final String DIVIDE = "/";
	public static final String MODULO = "%";
	public static final short EXTERNAL_DECLARATION_CONTEXT = 0;
	public static final short EXTERN_DECLARATION_CONTEXT = 1;
	public static final short LOCAL_VARIABLE_CONTEXT = 2;
	private CompilationContext compilationContext;
	private List<IRTree> functionDeclr;
	private Stack<IRTreeAlloca> variableLengthArrayAllocas;
	private Map<CompoundStatement, IRTreeAlloca> compoundStmtVsVariableLengthArrAlloca;
	private Set<IRTreeType> setOfNamedTypes;
	IRTree returnValueTree = null;
	String currentFuncName = "";
	Stack<CompoundStatement> stackOfBlocks;
	boolean isSameBlock = false;
	private Properties properties;

	private static final Logger LOG = LoggerFactory.getLogger(Semantic.class);
	
	public Semantic(Properties properties, String sourceFileName, CompilationContext compilationContext) {

		environments = Environments.getInstance();		
		errorHandler = ErrorHandler.getInstance();
		phiNodeTrueBBLabels = new Stack<Label>();
		labels = new HashSet<String>();
		this.sourceFileName = sourceFileName;
		definedFunctions = new HashSet<String>();
		iterOrSwitchStack = new Stack<IterationOrSelectionLabels>();
		translatingMediator = new Translator(properties);	
		Translator.count_for_string_constants = 0;
		count_of_structures = 0;
		assignmentExprs = new Stack<AssignmentExpr>();
		setOfBasicTypes = new HashSet<BasicType>();
		functionDeclr = new Stack<IRTree>();
		variableLengthArrayAllocas = new Stack<IRTreeAlloca>();
		setOfStructType = new LinkedHashSet<StructType>();
		tagVsStructOrUnionTE = new HashMap<String, StructOrUnionTypeEntry>();
		setOfNamedTypes = new HashSet<IRTreeType>();
		this.compilationContext = compilationContext;
		this.properties = properties;
	}

	public List<ClangTransUnit> translateAbstractTree(Absyn abstractSyntaxTree) {
		TranslationUnit tu = (TranslationUnit) abstractSyntaxTree;

		// Reset Label Count to one
		Label.resetCount();
		List<ClangTransUnit> translatedUnits = new ArrayList<ClangTransUnit>();

		while(true){
			if(tu == null) 
				break;

			ExternDeclaration externalDec = tu.getExternDec();	

			if(externalDec == null && tu.getErrors() != null){
				Stack<String> errorMsgs = tu.getErrors();
				while(!errorMsgs.isEmpty()){
					String mainMsg = errorMsgs.pop();
					int index = mainMsg.indexOf("  ");
					String lineNo = mainMsg.substring(0, index);
					mainMsg = mainMsg.substring(index).trim();
					index = lineNo.indexOf(' ');
					lineNo = lineNo.substring(index).trim();
					String[] strArr = lineNo.split(":");
					int lineNum = Integer.parseInt(strArr[0]);
					int pos = Integer.parseInt(strArr[1]);
					SourceLocation location = new SourceLocation(lineNum, pos);
					errorHandler.addError(sourceFileName, location, null, null, mainMsg);
				}
				break;
			}

			int externalDecType = externalDec.getType();
			if(externalDecType == ExternDeclaration.DECLARATION){

				IRTree irTreeFromDec = translateDeclaration((Declaration) externalDec, true);  // Check and translate declaration
				if(irTreeFromDec != null){
					ClangExternalDecl externalDecl = new ClangExternalDecl();
					externalDecl.setIrTree(irTreeFromDec);
					translatedUnits.add(externalDecl);			
				}
			}
			else if(externalDecType == ExternDeclaration.FUNCTION_DEF){

				FunctionDef funcDef = (FunctionDef) externalDec;
				currentFunctionDef = new ClangFunctionDef();
				translateFunctionDef((FunctionDef)funcDef);  // This will update the currentFunctionDef

				if(currentFunctionDef != null){
					translatedUnits.add(currentFunctionDef);				
				}

				currentFunctionDef = null;   // Reset
			}
			else{   // Include directive
				IncludeDirective includeDirective = (IncludeDirective) externalDec;	

				HashMap<String, HashMap<String, List<InputStream>>> includesPreProcessed = IncludesPreProcessed.getInstance();
				HashMap<String, List<InputStream>> includesMapForCurrentFile = includesPreProcessed.get(sourceFileName);

				includeDirective.setIncludesMap(includesMapForCurrentFile);

				List<ClangTransUnit> translationUnitsOfIncludedFile = includeDirective.process(properties);
				if(translationUnitsOfIncludedFile != null)
					translatedUnits.addAll(translationUnitsOfIncludedFile);

			}
			tu = tu.getTranslationUnitNext();
		}

		return translatedUnits;
	}

	public IRTree translateDeclaration(Declaration declaration, boolean isExternalDecl){

		boolean isTypedef = declaration.isTypeDefDeclaration();

		Stack<IRTree> irTreeStack = new Stack<IRTree>();

		DeclarationSpecifiers declarationSpecifiers = declaration.getDeclSpecifiers();

		TypeEntryWithAttributes baseEntry = getBaseEntryFromSpecsList(declarationSpecifiers, 
				isExternalDecl, false, isTypedef);	

		// If it is a Structure Type then we need to create an IRTreeType
		BasicType basicType = baseEntry.getBasicType();

		if(basicType instanceof StructOrUnionTypeEntry && setOfBasicTypes.add(basicType)){
			StructOrUnionTypeEntry structOrUnionTypeEntry = (StructOrUnionTypeEntry)basicType;
			String name = structOrUnionTypeEntry.getTag();

			// If the name is empty, then only getTag() will return a Random number, so set the tag as 'anon'
			boolean isNumber = name.matches("[0-9]");
			if(isNumber){
				if(count_of_structures == 0)
					name = "anon";
				else
					name = "anon" + count_of_structures;

				synchronized (this) {
					count_of_structures++;
				}
				structOrUnionTypeEntry.setTag(name);
			}

			boolean isUnSigned = baseEntry.isUnsigned();
			StructType structType = (StructType)translatingMediator.getLLVMType(basicType, isUnSigned, compilationContext);
			setOfStructType.add(structType);

			// If the structure contains nested Structures or Unions. then we have to create a type for that also.
			List<IRTreeType> listOfIrStructTypes = translatingMediator.getListOfNestedStructTypeIfPossible(structType, setOfStructType);
			Value value = new Value(structType);
			value.setName(name);
			IRTreeType irTreeType = new IRTreeType(value);
			irTreeStack.push(irTreeType);

			for(IRTreeType structIRTreeType : listOfIrStructTypes){
				irTreeStack.push(structIRTreeType);
			}
		}
		else if(basicType instanceof EnumSpecTypeEntry){
			EnumSpecTypeEntry enumSpecTypeEntry = (EnumSpecTypeEntry)basicType;
			Map<String, ConstantInt>  namesVsConstantValue = enumSpecTypeEntry.getNames();
			Set<Entry<String, ConstantInt>> entries = namesVsConstantValue.entrySet();
			Iterator<Entry<String, ConstantInt>> iterator = entries.iterator();
			while(iterator.hasNext()){
				Entry<String, ConstantInt> entry = iterator.next();
				String identifier = entry.getKey();
				Symbol idSym = Symbol.symbol(identifier);
				VariableOrFunctionEntry identifierTypeEntry = (VariableOrFunctionEntry)
						environments.getInstanceVariableTable().get(idSym);
				if(identifierTypeEntry.getCategory() == VariableOrFunctionEntry.VARIABLE){
					VariableEntry varEntry = (VariableEntry) identifierTypeEntry;
					varEntry.setValue(entry.getValue());
				}
			}
		}

		// Now check the declarators
		InitDeclaratorList initDeclaratorList = declaration.getInitDeclaratorList();
		IRTree ret = null;
		if(initDeclaratorList != null){		
			ret = translateDeclaratorList(initDeclaratorList, baseEntry, isExternalDecl, isTypedef);
			irTreeStack.push(ret);
		}

		return translatingMediator.translateSeqStatement(irTreeStack);
	}

	private IRTree translateDeclaratorList(InitDeclaratorList initDeclaratorList, TypeEntryWithAttributes baseEntry,
			boolean isExternalDecl, boolean isTypeDef){

		Vector<String> translatedDeclaratorNames = new Vector<String>();

		Stack<IRTree> seqStmt = new Stack<IRTree>();

		while(true){
			if(initDeclaratorList == null)
				break;

			InitDeclarator initDeclarator = initDeclaratorList.getInitDeclarator();
			Declarator declarator = initDeclarator.getDeclarator();

			SourceLocation location = new SourceLocation(declarator.getLineNum(), declarator.getPos());

			TranslatedDeclarator translatedDeclarator = translateDeclarator(declarator, false, false, false);

			if(translatedDeclarator == null){ // Error, move on to the next declarator
				initDeclaratorList = initDeclaratorList.getInitDeclaratorListNext(); 
				continue;
			}

			translatedDeclaratorNames.add(translatedDeclarator.getName());

			String name = translatedDeclarator.getName();

			if(isTypeDef){  // This is a typedef, make an entry if such a type has already not been defined
				Table table = environments.getInstanceTypeTable();				
				TypeDefNameTypeEntry existingTypeEntry = (TypeDefNameTypeEntry)table.get(Symbol.symbol(name));
				if(table.keyInCurrentScope(Symbol.symbol(name))){ // Already defined
					TypeEntryWithAttributes existingTe = existingTypeEntry.getActualBasicType();
					if(existingTe.equals(baseEntry)) { // Re-definition, continue
						SourceLocation existingLocation = existingTypeEntry.getSourceLocation();
						errorHandler.addError(sourceFileName, location, name,
								ErrorHandler.AI_SEE_DECLARATION + sourceFileName + ":" + existingLocation,
								ErrorHandler.E_TYPE_ALREADY_DEFINED);
						initDeclaratorList = initDeclaratorList.getInitDeclaratorListNext(); 
						continue; 
					}

					else {// Add to error and continue 
						SourceLocation existingLocation = existingTypeEntry.getSourceLocation();
						errorHandler.addError(sourceFileName, location, name + ":",
								ErrorHandler.AI_SEE_DECLARATION + sourceFileName + ":" + existingLocation,
								ErrorHandler.E_TYPE_ALREADY_DEFINED_WITH_CONFLICTING_TYPE);
						initDeclaratorList = initDeclaratorList.getInitDeclaratorListNext(); 
						continue;
					}
				}

				TypeDefNameTypeEntry newTypeEntry = 
						makeTypeEntry(baseEntry, translatedDeclarator, location);

				if(newTypeEntry != null){
					environments.getInstanceTypeTable().put(Symbol.symbol(name), newTypeEntry);
				}

				// If there is a initializer, flag an error
				Initializer initializer = initDeclarator.getInitializer();		
				if(initializer != null){
					location = new SourceLocation(initializer.getLineNum(), initializer.getPos());
					errorHandler.addError(sourceFileName, location, name + ":", "", ErrorHandler.E_TYPE_CANNOT_HAVE_INITIALIZER);
				}

				initDeclaratorList = initDeclaratorList.getInitDeclaratorListNext(); 
				continue;				
			}

			VariableOrFunctionEntry newEntry = makeVariableOrFunctionEntry(translatedDeclarator, 
					baseEntry, location);

			short declarationContext = LOCAL_VARIABLE_CONTEXT;
			if(isExternalDecl)
				declarationContext = EXTERNAL_DECLARATION_CONTEXT;
			if(baseEntry.isExtern())
				declarationContext = EXTERN_DECLARATION_CONTEXT;

			boolean hasInitializer = false;
			if( initDeclarator.getInitializer() != null)
				hasInitializer = true;

			if(errorByRepeating(name, location, newEntry, declarationContext, hasInitializer)){								
				initDeclaratorList = initDeclaratorList.getInitDeclaratorListNext(); 
				continue;
			}
			else		
				addToEnvironment(newEntry);  // No error, make entry

			if(newEntry.getCategory() == VariableOrFunctionEntry.VARIABLE){

				VariableEntry varTypeEntry = (VariableEntry) newEntry;
				BasicType btOfVar = varTypeEntry.getType().getBasicType();

				TypeEntryWithAttributes typeEntryWithAttributes = varTypeEntry.getType();
				BasicType basicType = typeEntryWithAttributes.getBasicType();
				boolean isUnSigned = typeEntryWithAttributes.isUnsigned();
				Type type = translatingMediator.getLLVMType(basicType, isUnSigned, compilationContext);
				Value newValue = createValueForLocalVariableDeclaration(type , name, isExternalDecl, baseEntry);
				varTypeEntry.setValue(newValue);

				// If it is enum type then create a NamedType for it
				if(basicType instanceof EnumSpecTypeEntry || basicType instanceof TypeDefNameTypeEntry){
					IRTreeType irTreeType = translatingMediator.getNamedTypeForEnum(basicType, type);
					if(irTreeType != null && setOfNamedTypes.add(irTreeType))
						seqStmt.push(irTreeType);
				}

				// Get and analyze the initializer
				Initializer initializer = initDeclarator.getInitializer();		
				TypeEntryWithAttributes initlzrTp = null;
				Translated treExpAndType = null;
				if(initializer != null){												
					if(baseEntry.isExtern()){  
						// Extern variables should not get initialized since the definition will do it for them
						errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_EXTERN_DEC_CANNOT_HAVE_INIT);
					}

					if(btOfVar instanceof ArrayTypeEntry){				
						treExpAndType = translateInitializer(varTypeEntry.getType(), 
								initializer, ARRAY_INITIALIZER_TRANSLATION_CONTEXT, name, isExternalDecl);

						if(!isExternalDecl && (treExpAndType.getIRTree() instanceof IRTreeStatementList)){
							IRTreeStatementList irTreeStatementList = (IRTreeStatementList)treExpAndType.getIRTree();
							IRTreeStatement irTreeStatement = translatingMediator.getLeafStatementList(irTreeStatementList).getStatement();
							if(irTreeStatement instanceof IRTreeExpressionStm){
								IRTreeExpressionStm expressionStm = (IRTreeExpressionStm)irTreeStatement;
								IRTreeExp irTreeExp = expressionStm.getExpressionTree();
								if(irTreeExp instanceof IRTreeConst){
									IRTreeConst irTreeConst = (IRTreeConst)irTreeExp;
									if(irTreeConst.getExpressionValue() instanceof ConstantArray){
										ConstantArray constantArray = (ConstantArray)irTreeConst.getExpressionValue();
										LinkageTypes linkageTypes = LinkageTypes.InternalLinkage;
										PointerType pointerType = null;
										try {
											pointerType = Type.getPointerType(compilationContext, constantArray.getType(), 0);
										} catch (TypeCreationException e) {
											e.printStackTrace();
											System.exit(-1);
										}
										Value value = new Value(pointerType);
										value.setName(currentFuncName + "." + name);
										IRTreeMemory irTreeMemory = new IRTreeMemory(value);
										IRTreeDeclaration irTreeDeclaration = new IRTreeDeclaration(irTreeMemory, irTreeConst, true, true, false, linkageTypes);
										treExpAndType.setIRTree(irTreeDeclaration);

										// We now know it is a constant Array, we also know its size, so check if the containedType is PointerType or Array Type. If it is pointer type then make it ArrayType
										if(newValue.getType().isPointerType()){
											PointerType pointerType2 = (PointerType)newValue.getType();
											Type containedType = pointerType2.getContainedType();
											if(containedType.isPointerType()){
												pointerType2.setContainedType(constantArray.getType());
											}
										}
									}
								}
							}
						}
					}
					else if(btOfVar instanceof StructOrUnionTypeEntry){
						StructOrUnionTypeEntry structOrUnionTypeEntry = (StructOrUnionTypeEntry)btOfVar;
						boolean isStruct = structOrUnionTypeEntry.isStruct();
						if(isStruct)
							treExpAndType = translateInitializer(varTypeEntry.getType(), initializer, 
									STRUCT_INITIALIZER_TRANSLATION_CONTEXT, name, isExternalDecl);
						else{// For Union
							TypeEntryWithAttributes firstMemberTypeEntryWithAttributes = structOrUnionTypeEntry.getMemberType(structOrUnionTypeEntry.getMemberNames().get(0));
							BasicType basicType2 = firstMemberTypeEntryWithAttributes.getBasicType();
							if(basicType2 instanceof ArrayTypeEntry){
								InitializerList initializerList = initializer.getInitializerList();
								if(initializerList != null){
									Initializer initializer2 = initializerList.getInitializer();
									if(initializer2 != null){
										AssignmentExpr assignmentExpr = initializer2.getInitExpr();
										if(assignmentExpr != null)
											treExpAndType = translateInitializer(varTypeEntry.getType(), initializer, 
													ARRAY_INITIALIZER_TRANSLATION_CONTEXT, name, isExternalDecl);
										else
											treExpAndType = translateInitializer(varTypeEntry.getType(), initializer, 
													STRUCT_INITIALIZER_TRANSLATION_CONTEXT, name, isExternalDecl);
									}
									else
										treExpAndType = translateInitializer(varTypeEntry.getType(), initializer, 
												STRUCT_INITIALIZER_TRANSLATION_CONTEXT, name, isExternalDecl);
								}
								else
									treExpAndType = translateInitializer(varTypeEntry.getType(), initializer, 
											STRUCT_INITIALIZER_TRANSLATION_CONTEXT, name, isExternalDecl);
							}
							else{
								treExpAndType = translateInitializer(varTypeEntry.getType(), initializer, 
										STRUCT_INITIALIZER_TRANSLATION_CONTEXT, name, isExternalDecl);
							}
						}
					}
					else   
						treExpAndType = translateInitializer(varTypeEntry.getType(), initializer, COMPOUND_INITIALIZER_TRANSLATION_CONTEXT, name, isExternalDecl);

					if(treExpAndType != null)
						initlzrTp = treExpAndType.getTypeEntry();					
				}

				if(btOfVar instanceof ArrayTypeEntry){	
					ArrayTypeEntry arrayTypeEntry = (ArrayTypeEntry)btOfVar;
					// Check if the initializer is also an array
					if(initlzrTp != null && !(initlzrTp.getBasicType() instanceof ArrayTypeEntry)){
						// The declaration is an array, but the initializer is not; check if the declaration
						// is of base type char and the initializer a string
						BasicType baseTypeOfArray = ((ArrayTypeEntry) btOfVar).getBaseTypeEntry().getBasicType();
						BasicType baseTypeOfInit = initlzrTp.getBasicType();

						if(!(baseTypeOfArray instanceof CharTypeEntry 
								&& baseTypeOfInit instanceof StringTypeEntry)){

							errorHandler.addError(sourceFileName, location, name + ": ", "", ErrorHandler.E_INVALID_INITIALIZATION);
							return null;
						}
						else{
							checkAssignmentCompatibility(varTypeEntry.getType(), initlzrTp, location, 
									Semantic.ASSIGNMENT_IN_DECLR_INIT_EXPR, AssignmentOperator.ASSIGN, null, -1);	
						}
					}

					// Check if the array declarator is ok
					arrayDeclaratorIsOk((ArrayTypeEntry) btOfVar, initlzrTp,name,  
							Semantic.ASSIGNMENT_IN_DECLR_INIT_EXPR, location);	

					//	If the excess initializer error message already exists, don't add it again
					if(treExpAndType != null && treExpAndType.isExcessInitializersInArrayInit() &&  !errorHandler.errorOrWarningAlreadyExists(sourceFileName, location, ErrorHandler.ERROR_MSGS_ONLY, 
							ErrorHandler.E_EXCESS_INITIALIZERS_IN_ARRAY_INIT)) {
						// Add this error only if required; this might come up several times
						errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_EXCESS_INITIALIZERS_IN_ARRAY_INIT);					
					}

					if(arrayTypeEntry.isVariableLengthArray()){
						PointerType pointerType = null;
						try {
							pointerType = Type.getPointerType(compilationContext, Type.getInt8Type(compilationContext, true), 0);
						} catch (TypeCreationException e) {
							e.printStackTrace();
							System.exit(-1);
						}

						if(!isSameBlock){
							isSameBlock = true;
							IRTreeAlloca treeAlloca = new IRTreeAlloca(pointerType, null);
							variableLengthArrayAllocas.push(treeAlloca);
							compoundStmtVsVariableLengthArrAlloca.put(stackOfBlocks.peek(),treeAlloca);
							String funcName = "llvm.stacksave";
							IRTreeCallExp callExp = translatingMediator.callIntrinsicLLVMStackRestoreOrStackSave(funcName, variableLengthArrayAllocas, seqStmt, compilationContext);
							IRTreeMove irTreeMove = new IRTreeMove(treeAlloca, callExp);
							seqStmt.push(irTreeMove);
						}

						TypeEntryWithAttributes attributes = arrayTypeEntry.getBaseTypeEntry();
						BasicType basicType2 = attributes.getBasicType();
						Type type2 = translatingMediator.getLLVMType(basicType2, attributes.isUnsigned(), compilationContext);
						Integer alignment = AllocaInst.getAlignmentForType(properties, type2);
						ConstantInt constantInt = null;
						APInt apInt = new APInt(32, alignment.toString(), true);
						try{
							constantInt = new ConstantInt(Type.getInt32Type(compilationContext, true), apInt);
						}
						catch(InstantiationException e){
							e.printStackTrace();
							System.exit(-1);
						}
						IRTreeConst irTreeConst = new IRTreeConst(constantInt);
						IRTreeBinaryExp binaryExp = new IRTreeBinaryExp(BinaryOperatorID.MUL,irTreeConst, (IRTreeExp)arrayTypeEntry.getIrTree());
						seqStmt.push(binaryExp);
						IRTreeAlloca irTreeAlloca = new IRTreeAlloca(Type.getInt8Type(compilationContext, true), binaryExp);
						seqStmt.push(irTreeAlloca);

						if(!type2.isInt8Type()){
							try {
								pointerType = Type.getPointerType(compilationContext, type2, 0);
							} 
							catch (TypeCreationException e) {
								e.printStackTrace();
								System.exit(-1);
							}
							IRTreeTempOrVar irTreeTempOrVar = new IRTreeTempOrVar(new Value(pointerType));
							IRTreeCast irTreeCast = new IRTreeCast(irTreeAlloca, irTreeTempOrVar); 
							irTreeCast.setBitCastOpr(true);
							seqStmt.push(irTreeCast);
						}

						initDeclaratorList = initDeclaratorList.getInitDeclaratorListNext();  // Get next
						continue;
					}
				}
				else{
					if(initlzrTp != null)   // Able to determine the initializer
						checkAssignmentCompatibility(varTypeEntry.getType(), initlzrTp, location, 
								Semantic.ASSIGNMENT_IN_DECLR_INIT_EXPR, AssignmentOperator.ASSIGN, null, -1);

					// If the initializer is of group type and this is not a struct type either,
					// add excess elements error. If it is already there, don't add it again

					if(initializer != null && initializer.getInitializerList() != null) {
						BasicType bt = varTypeEntry.getType().getBasicType();

						if(!(bt instanceof StructOrUnionTypeEntry) && initlzrTp != null){
							// Neither array nor a struct, but initializer is not null

							boolean excessArrayElemsErrorAlreadyExists = errorHandler.errorOrWarningAlreadyExists(sourceFileName, location, ErrorHandler.ERROR_MSGS_ONLY, 
									ErrorHandler.E_EXCESS_INITIALIZERS_IN_ARRAY_INIT);
							if(!excessArrayElemsErrorAlreadyExists)
								// Add this error only if required; this might come up several times
								errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_EXCESS_INITIALIZERS_IN_ARRAY_INIT);													
						}						
					}
				}	

				// The variable declaration has been analyzed, now translate it to an intermediate tree IR
				// if there is no strict error
				if(!errorHandler.isFoundStrictError()){

					boolean isGlobal = (declarationContext == EXTERN_DECLARATION_CONTEXT || 
							declarationContext == EXTERNAL_DECLARATION_CONTEXT) ? true : false;



					IRTree irInitExpr = null;
					if(treExpAndType != null){
						irInitExpr = treExpAndType.getIRTree();
					}

					boolean isConstant = typeEntryWithAttributes.isConst();
					Map<IRTreeExp, Boolean> isCompileTimeConstant = new HashMap<IRTreeExp, Boolean>();

					IRTreeExp irTreeExp = translatingMediator.getExprFromIRTree(irInitExpr, seqStmt, compilationContext, newValue, isGlobal, isConstant, isCompileTimeConstant);

					if(irTreeExp != null && isGlobal &&  !isCompileTimeConstant.get(irTreeExp)){
						errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_INIT_NOT_CONST_EXPR);
					}
					if(!errorHandler.isFoundStrictError()){
						IRTree declTree = translatingMediator.translateVarDec(newValue, irTreeExp, isGlobal, isConstant, compilationContext, baseEntry);

						if(declTree != null)
							seqStmt.push(declTree);
					}
				}				
			}
			else{   // This is a function declaration
				Initializer initializer = initDeclarator.getInitializer();
				if(initializer != null){   // Cannot have an initializer for a function
					location = new SourceLocation(initializer.getLineNum(), initializer.getPos());
					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_FUNCTION_CANNOT_HAVE_INITIALIZER);
				}
				DeclaratorChainElement  declaratorChainElement = translatedDeclarator.getDeclaratorChainElement();
				FunctionEntry functionEntry = (FunctionEntry)declaratorChainElement;

				String funcName  = functionEntry.getName();

				TypeEntryWithAttributes returnTypeEntryWithAttributes = functionEntry.getReturnType();
				BasicType returnBasicType = returnTypeEntryWithAttributes.getBasicType();
				Vector<VariableEntry> varEntryForFormalParameters = functionEntry.getFormals();
				boolean endsWithEllipses = functionEntry.isEndsWithEllipses();

				List<Value> paramValues = new ArrayList<Value>();
				List<List<Integer>> paramAttrs = new ArrayList<List<Integer>>();

				for(int i = 0; varEntryForFormalParameters != null && i < varEntryForFormalParameters.size(); i++){
					VariableEntry variableEntry = varEntryForFormalParameters.elementAt(i);
					TypeEntryWithAttributes typeEntryWithAttributes = variableEntry.getType();
					BasicType basicType = typeEntryWithAttributes.getBasicType();
					boolean isUnSigned = typeEntryWithAttributes.isUnsigned();
					Type type = null;
					if(funcName.equals("malloc"))
						type = Type.getInt64Type(compilationContext, true);
					else
						type = translatingMediator.getLLVMType(basicType, isUnSigned, compilationContext);
					Value value = new Value(type);
					paramValues.add(value);
					List<Integer> paramAttributes = new ArrayList<Integer>();
					paramAttrs.add(paramAttributes);
				}

				boolean isUnSigned = returnTypeEntryWithAttributes.isUnsigned();
				Type returnType = translatingMediator.getLLVMType(returnBasicType, isUnSigned, compilationContext);

				IRTree functionDefination = translatingMediator.createFuncDeclaration(funcName, returnType, paramValues, paramAttrs, endsWithEllipses, baseEntry.isStatic());
				boolean isFunctionAlreadyAddedToDeclrStack = translatingMediator.isFunctionAlreadyAddedToDeclrStack(functionDefination, functionDeclr);
				functionDeclr.add(functionDefination);

				if(!isFunctionAlreadyAddedToDeclrStack)
					seqStmt.push(functionDefination);
			}

			initDeclaratorList = initDeclaratorList.getInitDeclaratorListNext();  // Get next
		}

		if(!seqStmt.isEmpty())
			return translatingMediator.translateSeqStatement(seqStmt);
		else
			return null;
	}

	public Value createValueForLocalVariableDeclaration(Type type, String name, boolean isGlobal, TypeEntryWithAttributes baseEntry) {

		PointerType ptrType = null;

		if(baseEntry.isStatic()){
			isGlobal = true;
			if(currentFuncName != null && currentFuncName.length() != 0)
				name = currentFuncName + "." + name;
		}

		try {
			ptrType = Type.getPointerType(compilationContext, type, 0);
		} catch (TypeCreationException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		Value memoryValue = new Value(ptrType);
		memoryValue.setName(name);

		return memoryValue;
	}



	private Value createValue(String name, TypeEntryWithAttributes teWithAttrs, boolean isGlobal) {
		BasicType basicType = teWithAttrs.getBasicType();
		boolean isUnSigned = teWithAttrs.isUnsigned();
		if(basicType instanceof PointerTypeEntry){
			PointerTypeEntry pointerTypeEntry = (PointerTypeEntry)basicType;
			if(pointerTypeEntry.getBaseTypeEntry().getBasicType() == null)
				return null;
		}
		Type type = translatingMediator.getLLVMType(basicType, isUnSigned, compilationContext);
		Value newValue = new Value(type);
		newValue.setName(name);
		return newValue;

	}

	/**
	 * Checks if the initializer passed has only scalar elements (i.e., there are no arrays as elements. If so,
	 * checks against the type of array and assigns elements. E.g a declaration like this: 
	 * 
	 * 	double array[2][2][3] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
	 * 
	 * Return the input array if elements of the array cannot be translated for scalar elements
	 * 
	 * @param baseEntry
	 * @param translatedDeclarator
	 * @param location
	 * @return
	 */

	private Translated translateCompoundInitializerWithAllScalarElements(TypeEntryWithAttributes teWithAttrs,
			Vector<Translated> translatedElements, SourceLocation location, String lhsName, boolean isExternalDecl){


		Translated retValue = new Translated();

		HashMap<String, List<TypeEntryWithAttributes>> shiftReduceTable = new HashMap<String, List<TypeEntryWithAttributes>>();

		int numElemsInScalarArray = translatedElements.size();
		int correctNumOfElements = 0;

		BasicType btTeWithAttrs = teWithAttrs.getBasicType();
		if(!(btTeWithAttrs instanceof ArrayTypeEntry || btTeWithAttrs instanceof StructOrUnionTypeEntry)) {
			return translatedElements.elementAt(0);
		}

		// Determine if all scalar elements in the initializer are all strings
		boolean allElementsOfStringType = false;
		if(allElementsOfSameType(translatedElements)){
			TypeEntryWithAttributes teElem = translatedElements.get(0).getTypeEntry();
			if(teElem.getBasicType() instanceof StringTypeEntry)
				allElementsOfStringType = true;
		}

		boolean shiftRedTableCannotBeDetermined = false;

		if(btTeWithAttrs instanceof StructOrUnionTypeEntry){
			// This is of type struct or union
			ShiftReducePopulationResult res = populateShiftReduceTable(shiftReduceTable, 0, teWithAttrs,
					allElementsOfStringType, location);

			correctNumOfElements = res.getElementCount();
			if(!res.isSizeCanBeDetermined()){   
				// Error, maybe no size specified for child arrays; return
				shiftRedTableCannotBeDetermined = true;
				retValue.setEntry(teWithAttrs);
				return retValue;
			}
		}
		else{
			// This is of type array
			ArrayTypeEntry arrTypeMain = (ArrayTypeEntry)btTeWithAttrs;
			int dimension = 0;
			if(arrTypeMain.getDimension() != null && arrTypeMain.getDimension().getLiteralValue().matches("\\d+"))
				dimension = Integer.parseInt(arrTypeMain.getDimension().getLiteralValue());
			TypeEntryWithAttributes teChild = arrTypeMain.getBaseTypeEntry();
			BasicType btChild = teChild.getBasicType();
			if(btChild instanceof ArrayTypeEntry || btChild instanceof StructOrUnionTypeEntry){ 
				// Multidimensional array or an array of struct type
				if(arrTypeMain.getDimension() == null){
					ShiftReducePopulationResult res = populateShiftReduceTable(shiftReduceTable, 0, teChild, 
							allElementsOfStringType, location);
					int numElemsOfBase = res.getElementCount();

					if(numElemsOfBase == 0){   
						// Error, maybe no size specified for child arrays; return
						shiftRedTableCannotBeDetermined = true;
						retValue.setEntry(teWithAttrs);
						return retValue;
					}
					else{
						processArrayInitWithUnknowSize(teWithAttrs,  shiftReduceTable, numElemsInScalarArray,
								numElemsOfBase);
						correctNumOfElements = numElemsInScalarArray;
					}				
				}
				else{
					ShiftReducePopulationResult res = populateShiftReduceTable(shiftReduceTable, 0, teWithAttrs, 
							allElementsOfStringType, location);
					correctNumOfElements = res.getElementCount();
					if(!res.isSizeCanBeDetermined())
						shiftRedTableCannotBeDetermined = true;
				}
			}
			else if(btChild instanceof PointerTypeEntry){
				PointerTypeEntry pointerTypeEntry = (PointerTypeEntry)btChild;
				TypeEntryWithAttributes typeEntryWithAttributes = pointerTypeEntry.getBaseTypeEntry();
				if(typeEntryWithAttributes.getBasicType() instanceof CharTypeEntry){
					return getTransExpAndTypeForSingleDimPtrToCharArray(translatedElements, teWithAttrs);
				}
			}
			else {   // Single dimensional array with a non-complex base type
				correctNumOfElements = translatedElements.size();
				return getTransExpAndTypeForSingleDimArrayInit(teChild, correctNumOfElements, 
						translatedElements, location, lhsName, dimension, isExternalDecl);
			}
		}

		int count = 1;

		Stack<TypeEntryWithAttributes> shiftStack = new Stack<TypeEntryWithAttributes>();

		List<IRTree> listOfMemberIrTrees = new ArrayList<IRTree>();
		Vector<Translated> listOfTranslated = new Vector<Translated>();
		Stack<Vector<Translated>> stackOflistOfTranslated = new Stack<Vector<Translated>>();
		stackOflistOfTranslated.push(listOfTranslated);

		for(Translated transExpAndType: translatedElements){
			TypeEntryWithAttributes teExp = transExpAndType.getTypeEntry();
			location = transExpAndType.getSourceLocation();
			List<TypeEntryWithAttributes> list = shiftReduceTable.get("" + count);
			stackOflistOfTranslated.peek().add(transExpAndType);
			listOfMemberIrTrees.add(transExpAndType.getIRTree());

			shiftStack.push(teExp);			
			if(list != null){  
				int nosOfDimensions = 1;
				// There are reduce instruction(s) here
				for(TypeEntryWithAttributes interimTe : list){

					BasicType interimBasicType = interimTe.getBasicType();
					if(btTeWithAttrs instanceof ArrayTypeEntry){
						if(interimBasicType instanceof ArrayTypeEntry){
							// Array type
							ArrayTypeEntry arrType = (ArrayTypeEntry) interimBasicType;
							TypeEntryWithAttributes btOfArray = arrType.getBaseTypeEntry();
							TypeEntryWithAttributes teDim = arrType.getDimension();
							int arrSize = 0;
							TypeEntryWithAttributes dimension  = arrType.getDimension();
							int dimensionVal = Integer.parseInt(dimension.getLiteralValue());
							if(teDim != null){
								//TODO is null value possible?
								try{
									arrSize = Integer.parseInt(teDim.getLiteralValue());
								}
								catch(NumberFormatException nfe){
									// TODO  is this possible?
									nfe.printStackTrace();
								}						
							}

							Stack<TypeEntryWithAttributes> reversedEntryStack = new Stack<TypeEntryWithAttributes>();

							if(btOfArray.getBasicType() instanceof CharTypeEntry && allElementsOfStringType){
								// Array of chars; Check each element against the type

								while(!shiftStack.isEmpty()){
									TypeEntryWithAttributes teOnStack = shiftStack.pop();								
									reversedEntryStack.push(teOnStack);
								}

								for(int i = 0; i < count; i++){														
									checkAssignmentCompatibility(interimTe, reversedEntryStack.pop(), location,
											Semantic.ASSIGNMENT_IN_DECLR_INIT_EXPR, AssignmentOperator.ASSIGN, null, -1);
								}
							}
							else{
								for(int i = 0; i < arrSize; i++){
									if(!shiftStack.isEmpty()){
										TypeEntryWithAttributes teOnStack = shiftStack.pop();								
										reversedEntryStack.push(teOnStack);
									}
									else{
										// TODO Error?
									}
								}

								// Check each element against the type
								for(int i = 0; i < arrSize; i++){														
									checkAssignmentCompatibility(btOfArray, reversedEntryStack.pop(), location,
											Semantic.ASSIGNMENT_IN_DECLR_INIT_EXPR, AssignmentOperator.ASSIGN, null, -1);
								}
								if(!errorHandler.isFoundStrictError()){
									Translated translated = null;

									if(nosOfDimensions > 1){
										translated = getTransExpAndTypeForSingleDimArrayInit(btOfArray, arrSize, stackOflistOfTranslated.pop(), location, lhsName, dimensionVal , isExternalDecl);
										retValue = translated ;
									}
									else
										translated = translateCompoundInitializerWithAllScalarElements(interimTe, stackOflistOfTranslated.pop(), location, lhsName, isExternalDecl);

									if(stackOflistOfTranslated.empty()){
										listOfTranslated = new Vector<Translated>();
										listOfTranslated.add(translated);
										stackOflistOfTranslated.push(listOfTranslated);
									}
									else
										updatingListOfTranslatedBasedOnArrayDimension(translated,stackOflistOfTranslated,listOfTranslated);
								}
							}
						}
						else{
							// Structure typestackOflistOfTranslated
							StructOrUnionTypeEntry structOrUnionTypeEntry = (StructOrUnionTypeEntry) interimBasicType;
							List<String> memberNames = structOrUnionTypeEntry.getMemberNames();
							Stack<TypeEntryWithAttributes> reversedEntryStack = new Stack<TypeEntryWithAttributes>();

							if(!structOrUnionTypeEntry.isStruct()){  
								// Is union, consider only the first element
								if(!shiftStack.isEmpty()){
									TypeEntryWithAttributes teOnStack = shiftStack.pop();								
									reversedEntryStack.push(teOnStack);
								}
								else{
									// TODO Error?
								}

								if(memberNames != null && memberNames.size() > 0){
									TypeEntryWithAttributes unionMem = 
											structOrUnionTypeEntry.getMemberType(memberNames.get(0));
									checkAssignmentCompatibility(unionMem, reversedEntryStack.pop(), location,
											Semantic.ASSIGNMENT_IN_DECLR_INIT_EXPR, AssignmentOperator.ASSIGN, null, -1);

								}
							}
							else{
								// This is a struct,consider all the members
								for(int i = 0; i < memberNames.size(); i++){
									if(!shiftStack.isEmpty()){
										TypeEntryWithAttributes teOnStack = shiftStack.pop();								
										reversedEntryStack.push(teOnStack);
									}
									else{
										// TODO Error?
									}
								}

								// Check all the members
								for(String memName: memberNames){									
									TypeEntryWithAttributes teStructMem = structOrUnionTypeEntry.getMemberType(memName);
									checkAssignmentCompatibility(teStructMem, reversedEntryStack.pop(), location,
											Semantic.ASSIGNMENT_IN_DECLR_INIT_EXPR, AssignmentOperator.ASSIGN, null, -1);
								}

								if(!errorHandler.isFoundStrictError()){
									Translated translated = new Translated();
									setConstantStructureInTheTranslated(structOrUnionTypeEntry, listOfMemberIrTrees, true, translated, lhsName);
									// make this empty, otherwise it will keep on adding on previous data
									listOfMemberIrTrees = new ArrayList<IRTree>();
									translated.setEntry(interimTe);
									stackOflistOfTranslated.pop();

									if(stackOflistOfTranslated.empty()){
										listOfTranslated = new Vector<Translated>();
										listOfTranslated.add(translated);
										stackOflistOfTranslated.push(listOfTranslated);
									}
									else
										updatingListOfTranslatedBasedOnArrayDimension(translated,stackOflistOfTranslated,listOfTranslated);
								}
							}
						}
					}

					// Push this into the queue					
					shiftStack.push(interimTe);	
					nosOfDimensions++;
				}
				listOfTranslated = new Vector<Translated>();
				stackOflistOfTranslated.push(listOfTranslated);
			}
			count++;
		}

		// Create ConstantStruct
		if(btTeWithAttrs instanceof StructOrUnionTypeEntry){
			StructOrUnionTypeEntry structOrUnionTypeEntry = (StructOrUnionTypeEntry)btTeWithAttrs;
			setConstantStructureInTheTranslated(structOrUnionTypeEntry,listOfMemberIrTrees, isExternalDecl, retValue, lhsName);
		}

		// Get the pending item on the stack
		TypeEntryWithAttributes teRet = teWithAttrs;
		if(shiftStack.size() > 0){
			TypeEntryWithAttributes teLast = shiftStack.pop();
			if(!teLast.equals(teWithAttrs)){
				teRet = teWithAttrs;
			}
			else{
				teRet = teLast;
			}
		}

		if((!shiftRedTableCannotBeDetermined) && translatedElements.size() > correctNumOfElements){
			if(btTeWithAttrs instanceof StructOrUnionTypeEntry)
				retValue.setExcessInitializersInStructnit(true);
			else
				retValue.setExcessInitializersInArrayInit(true);
		}

		retValue.setEntry(teRet);
		return retValue;

	}

	private void setConstantStructureInTheTranslated(StructOrUnionTypeEntry structOrUnionTypeEntry, List<IRTree> listOfMemberIrTrees, boolean isExternalDecl, Translated retValue, String lhsName) {
		IRTreeStatementList irTreeStatementList = translatingMediator.createConstantStruct(listOfMemberIrTrees, structOrUnionTypeEntry, compilationContext);

		if(isExternalDecl)
			retValue.setIRTree(irTreeStatementList);
		else{
			IRTreeExpressionStm expressionStm = (IRTreeExpressionStm)translatingMediator.getLeafStatementList(irTreeStatementList).getStatement();
			IRTreeConst irTreeConst = (IRTreeConst)expressionStm.getExpressionTree();
			PointerType pointerType = null;
			try {
				pointerType = Type.getPointerType(compilationContext, irTreeConst.getExpressionValue().getType(), 0);
			} catch (TypeCreationException e) {
				e.printStackTrace();
				System.exit(-1);
			}
			Value memory = new Value(pointerType);
			memory.setName(currentFuncName + "." + lhsName);
			IRTreeMemory memoryTree = new IRTreeMemory(memory);
			IRTreeDeclaration irTreeDeclaration = new IRTreeDeclaration(memoryTree , irTreeConst, true, true, false, LinkageTypes.InternalLinkage);
			translatingMediator.getLeafStatementList(irTreeStatementList).setStatementList(new IRTreeStatementList(irTreeDeclaration, null));
			retValue.setIRTree(irTreeStatementList);
		}
	}

	private void updatingListOfTranslatedBasedOnArrayDimension(Translated translated, Stack<Vector<Translated>> stackOflistOfTranslated, Vector<Translated> listOfTranslated) {

		IRTree irTree = translated.getIRTree();
		while(true){
			if(irTree instanceof IRTreeExpressionStm){
				IRTreeExpressionStm expressionStm = (IRTreeExpressionStm)irTree;
				IRTreeConst irTreeConst1 = (IRTreeConst)expressionStm.getExpressionTree();
				Value exprValue1 = irTreeConst1.getExpressionValue();
				if(exprValue1 instanceof ConstantArray){
					ConstantArray constantArray1 = (ConstantArray)exprValue1;
					List<Translated> listOfTranslted = stackOflistOfTranslated.peek();
					int size = listOfTranslted.size();
					IRTree irTree2 = listOfTranslted.get(size-1).getIRTree();
					while(true){
						if(irTree2 instanceof IRTreeExpressionStm){
							IRTreeExpressionStm expressionStm2 = (IRTreeExpressionStm)irTree2;
							IRTreeConst irTreeConst2 = (IRTreeConst) expressionStm2.getExpressionTree();
							Value exprValue2 = irTreeConst2.getExpressionValue();
							if(exprValue2 instanceof ConstantArray){
								ConstantArray constantArray2 = (ConstantArray)exprValue2;
								int dimension1 = constantArray1.getArrayType().getDimension();
								int dimension2 = constantArray2.getArrayType().getDimension();

								if(dimension1 == dimension2){
									stackOflistOfTranslated.peek().add(translated);
								}
								else{
									listOfTranslated = new Vector<Translated>();
									listOfTranslated.add(translated);
									stackOflistOfTranslated.push(listOfTranslated);
								}
							}
							break;
						}
						else if(irTree2 instanceof IRTreeStatementList){
							IRTreeStatementList irTreeStatementList = (IRTreeStatementList)irTree2;
							irTree2 = translatingMediator.getLeafStatementList(irTreeStatementList).getStatement();
						}
					}
				}
				else if(exprValue1 instanceof ConstantStruct){
					List<Translated> listOfTranslted = stackOflistOfTranslated.peek();
					int size = listOfTranslted.size();
					IRTree irTree2 = listOfTranslted.get(size-1).getIRTree();
					while(true){
						if(irTree2 instanceof IRTreeExpressionStm){
							IRTreeExpressionStm expressionStm2 = (IRTreeExpressionStm)irTree2;
							IRTreeConst irTreeConst2 = (IRTreeConst) expressionStm2.getExpressionTree();
							Value exprValue2 = irTreeConst2.getExpressionValue();
							if(exprValue2 instanceof ConstantStruct){
								stackOflistOfTranslated.peek().add(translated);
							}
							else{
								Vector<Translated> translateds = new Vector<Translated>();
								translateds.add(translated);
								stackOflistOfTranslated.push(translateds);
							}
							break;
						}
						else if(irTree2 instanceof IRTreeStatementList){
							IRTreeStatementList irTreeStatementList = (IRTreeStatementList)irTree2;
							irTree2 = translatingMediator.getLeafStatementList(irTreeStatementList).getStatement();
						}
					}
				}
				break;
			}
			// Might be for String Array
			else if(irTree instanceof IRTreeStatementList){
				IRTreeStatementList irTreeStatementList = (IRTreeStatementList)irTree;
				irTree = translatingMediator.getLeafStatementList(irTreeStatementList).getStatement();
			}
		}
	}

	private Translated getTransExpAndTypeForSingleDimPtrToCharArray(Vector<Translated> translatedElements, TypeEntryWithAttributes arrTypeMain) {
		Stack<IRTree> irTreeStack = new Stack<IRTree>();
		List<org.tamedragon.common.llvmir.types.Constant> listOfConstants = new ArrayList<org.tamedragon.common.llvmir.types.Constant>();

		for(Translated translated : translatedElements){
			IRTreeDeclaration irTreeDeclaration = (IRTreeDeclaration) translated.getIRTree();
			irTreeStack.push(irTreeDeclaration);
			if(irTreeDeclaration.getInitializerExpression() instanceof IRTreeConst){
				IRTreeConst irTreeConst = (IRTreeConst)irTreeDeclaration.getInitializerExpression();
				if(irTreeConst.getExpressionValue() instanceof ConstantArray){
					ConstantArray constantArray = (ConstantArray)irTreeConst.getExpressionValue();
					ConstantExpr constantExpr = translatingMediator.createConstantExpressionForString(irTreeDeclaration, constantArray, compilationContext);
					listOfConstants.add(constantExpr);
				}
			}
		}
		ConstantArray constantArray = null;
		try {
			constantArray = (ConstantArray) ConstantArray.get(Type.getArrayType(compilationContext, Type.getPointerType(compilationContext, Type.getInt8Type(compilationContext, false), 0), listOfConstants.size()), listOfConstants);
		} catch (TypeCreationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		IRTreeConst irTreeConst = new IRTreeConst(constantArray);
		irTreeStack.push(irTreeConst);
		IRTree irTree = translatingMediator.translateSeqStatement(irTreeStack);

		Translated translated = new Translated();
		translated.setIRTree(irTree);
		translated.setEntry(arrTypeMain);
		return translated;
	}

	private void processArrayInitWithUnknowSize(TypeEntryWithAttributes teWithAttrs, 
			HashMap<String, List<TypeEntryWithAttributes>> shiftReduceTable, int numElemsInScalarArray,
			int numElemsOfBase){
		int rem = numElemsInScalarArray % numElemsOfBase;
		int dimOfMainArray = numElemsInScalarArray / numElemsOfBase;
		if(rem > 0){
			dimOfMainArray++;
		}

		// Update the shift reduce table with the dimension of the main array obtained
		Set<String> reduceIndicesInTable = shiftReduceTable.keySet();
		List<String> indicesList = new ArrayList<String>(reduceIndicesInTable);

		int reduceIndex = numElemsOfBase;
		int currIndex = 0;
		for(int m = 1; m < dimOfMainArray; m++){	

			int max = 0;
			for(String key: indicesList){
				List<TypeEntryWithAttributes> teWithAttrsList = shiftReduceTable.get(key);
				int keyInt = Integer.parseInt(key);
				currIndex = reduceIndex + keyInt;

				List<TypeEntryWithAttributes> newList = new ArrayList<TypeEntryWithAttributes>();
				newList.addAll(teWithAttrsList);

				shiftReduceTable.put("" + currIndex, newList);		

				if(currIndex > max)
					max = currIndex;
			}

			reduceIndex = max;
		}

		// Update the dimension of the lhs, now that it is known
		TypeEntryWithAttributes newDimTeWithAttrs = new TypeEntryWithAttributes();
		newDimTeWithAttrs.setBasicType(IntTypeEntry.getInstance());
		newDimTeWithAttrs.setLiteralValue("" + dimOfMainArray);

		ArrayTypeEntry arrTypeMain = (ArrayTypeEntry) teWithAttrs.getBasicType();
		arrTypeMain.setDimension(newDimTeWithAttrs);
		updateReduceList(shiftReduceTable, reduceIndex, teWithAttrs);
	}

	/**
	 * Creates a "shift-reduce" map that helps determine at which point in a initializer list with all
	 * scalar elements a type (or types ) can be deduced. The cannotBeDetermined flag that is passed is an
	 * output field that indicates that a correct mapping could not be found - primarily due to presence of
	 * arrays with unspecified size as struct members OR as multidimensional arrays with unspecified sizes.
	 * 
	 * @param retMap
	 * @param count
	 * @param teWithAttributes
	 * @param allValuesAreStrings
	 * @param cannotBeDetermined
	 * @param location
	 * @return 
	 */

	private ShiftReducePopulationResult populateShiftReduceTable(HashMap<String, List<TypeEntryWithAttributes>> retMap, int count, 
			TypeEntryWithAttributes teWithAttributes, 
			boolean allValuesAreStrings, SourceLocation location){

		ShiftReducePopulationResult result = new ShiftReducePopulationResult();

		BasicType basicType = teWithAttributes.getBasicType();

		boolean sizeCanBeDetermined = true;
		if(basicType instanceof ArrayTypeEntry){
			// This is of type array, check the element type
			ArrayTypeEntry arrTypeEntry = (ArrayTypeEntry) basicType;
			TypeEntryWithAttributes teElements = arrTypeEntry.getBaseTypeEntry();

			// Check the base is base type is an array of characters
			boolean baseTypeIsCharArray = false;
			BasicType btOfBase = teElements.getBasicType();
			if(btOfBase instanceof ArrayTypeEntry){
				ArrayTypeEntry baseArray = (ArrayTypeEntry) btOfBase;
				TypeEntryWithAttributes baseArrayType = baseArray.getBaseTypeEntry();
				if(baseArrayType.getBasicType() instanceof CharTypeEntry)
					baseTypeIsCharArray = true;
			}

			int arrSize = 0;
			TypeEntryWithAttributes teDim = arrTypeEntry.getDimension();
			if(teDim != null){
				try{
					arrSize = Integer.parseInt(teDim.getLiteralValue());
				}
				catch(NumberFormatException nfe){
					sizeCanBeDetermined = false;
				}
			}
			else{
				sizeCanBeDetermined = false;
			}

			if(allValuesAreStrings && baseTypeIsCharArray){
				// Treat char array like strings
				count = arrSize;
				updateReduceList(retMap, count, teElements);				
			}
			else{
				for(int i = 0; i < arrSize; i++){
					ShiftReducePopulationResult res = populateShiftReduceTable(retMap, count, teElements, 
							allValuesAreStrings,  location); 
					if(!res.isSizeCanBeDetermined())
						sizeCanBeDetermined = false;
					else
						count = res.getElementCount();
				}

				// Reduce typeEntryWithAttrs passed
				updateReduceList(retMap, count, teWithAttributes);	
			}

		}
		else if(basicType instanceof StructOrUnionTypeEntry){
			// This is of type struct or union, iterate through the members
			StructOrUnionTypeEntry structOrUnionTypeEntry = (StructOrUnionTypeEntry) basicType;
			List<String> memberNames = structOrUnionTypeEntry.getMemberNames();

			for(String memName: memberNames){
				TypeEntryWithAttributes teMem = structOrUnionTypeEntry.getMemberType(memName);
				BasicType memBt = teMem.getBasicType();
				if(memBt instanceof ArrayTypeEntry || memBt instanceof StructOrUnionTypeEntry){		
					ShiftReducePopulationResult res = populateShiftReduceTable(retMap, count, teMem, 
							allValuesAreStrings, location);
					if(!res.isSizeCanBeDetermined())
						sizeCanBeDetermined = false;
					else 
						count = res.getElementCount();
				}
				else
					count++;

				if(!structOrUnionTypeEntry.isStruct()){
					// Is a union
					break;
				}						
			}

			// Reduce this also
			updateReduceList(retMap, count, teWithAttributes);			
		}
		else{
			count++;
		}

		result.setElementCount(count);
		result.setSizeCanBeDetermined(sizeCanBeDetermined);
		return result;		
	}

	private void updateReduceList(HashMap<String, List<TypeEntryWithAttributes>> retMap, int count,
			TypeEntryWithAttributes teWithAttributes){
		List<TypeEntryWithAttributes> reduceTe = retMap.get("" + count);
		if(reduceTe == null)
			reduceTe = new ArrayList<TypeEntryWithAttributes>();
		reduceTe.add(teWithAttributes);

		retMap.put(""+count, reduceTe);	
	}

	private TypeDefNameTypeEntry makeTypeEntry(TypeEntryWithAttributes baseEntry, TranslatedDeclarator translatedDeclarator,
			SourceLocation location){
		String name = translatedDeclarator.getName();

		TypeEntryWithAttributes actualBasicType = null;
		String referenceTypeName = null;

		BasicType btOfBase = baseEntry.getBasicType();
		if(btOfBase instanceof TypeDefNameTypeEntry){
			TypeDefNameTypeEntry tdEntry = (TypeDefNameTypeEntry)btOfBase;
			referenceTypeName = tdEntry.getTypeName();
			actualBasicType = getActualBasicType(tdEntry, location); 
			if(actualBasicType != null){
				actualBasicType.copy(baseEntry, true);
			}
		}
		else{
			actualBasicType = baseEntry;
		}

		TypeDefNameTypeEntry typeDefNameTypeEntry = null;

		DeclaratorChainElement declaratorChainElement = translatedDeclarator.getDeclaratorChainElement();

		if(declaratorChainElement == null){ // Must be a simple type declaration
			typeDefNameTypeEntry = 
					new TypeDefNameTypeEntry(name, actualBasicType, referenceTypeName, location);
		}
		else{
			int declaratorChainElementType = declaratorChainElement.getDeclaratorChainElementType();
			if(declaratorChainElementType == DeclaratorChainElement.DECL_CHAIN_FUNCTION){  // Error
				errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_INVALID_TYPE_DECL);
				return null;
			}
			else{
				TypeEntryWithAttributes teWithAttrs = new TypeEntryWithAttributes();
				teWithAttrs.setBasicType((BasicType) declaratorChainElement);
				TypeEntryWithAttributes.updateBasicType(teWithAttrs, actualBasicType);
				typeDefNameTypeEntry = 
						new TypeDefNameTypeEntry(name, teWithAttrs, referenceTypeName, location);
			}
		}

		return typeDefNameTypeEntry;

	}

	private TypeEntryWithAttributes getActualBasicType(TypeDefNameTypeEntry tdEntry,
			SourceLocation location){

		TypeEntryWithAttributes actualBasicType = null;
		Table table = environments.getInstanceTypeTable();

		String refName = tdEntry.getTypeName();

		while(true){
			Object entry = table.get(Symbol.symbol(refName));
			if(entry instanceof TypeDefNameTypeEntry){
				TypeDefNameTypeEntry parent = (TypeDefNameTypeEntry) entry;
				refName = parent.getReferenceTypeName();
				if(refName != null)
					continue;
				else{
					actualBasicType = parent.getActualBasicType();
					break;
				}
			}
			else{  // Error				
				errorHandler.addError(sourceFileName, location, refName + ":", "", ErrorHandler.E_TYPE_NOT_DEFINED);				
				actualBasicType = null;
				break;
			}
		}
		return actualBasicType;
	}

	private void arrayDeclaratorIsOk(ArrayTypeEntry arrayType, TypeEntryWithAttributes init, String name,
			int context, SourceLocation location){

		// If this declaration is related to a declaration (either auto or extern), the size must be specified.
		if(context == Semantic.ASSIGNMENT_IN_DECLR_INIT_EXPR){
			if(init == null){
				if(arrayType.getDimension() == null){
					errorHandler.addError(sourceFileName, location, name + ": ", "", ErrorHandler.E_ARRAY_SIZE_NOT_SPECIFIED);		
				}
			}
		}

		// If this is a multidimensional array, make sure except the first row, the other dimensions must be specified
		while(true){
			TypeEntryWithAttributes baseTeWithAttributes = arrayType.getBaseTypeEntry();
			if(baseTeWithAttributes.getBasicType() instanceof ArrayTypeEntry){
				arrayType =  (ArrayTypeEntry)baseTeWithAttributes.getBasicType();

				if(arrayType.getDimension() == null ){
					if(context == Semantic.ASSIGNMENT_IN_PARAMETER_PASSING)
						errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_INCORRECT_FORMAL_PARAMETER);
					else{		
						errorHandler.addError(sourceFileName, location, name + ": ", "", ErrorHandler.E_MISSING_SUBSCRIPT_IN_ARRAY_DECL);
					}
				}				
			}
			else
				break;
		}
	}

	private void addToEnvironment(VariableOrFunctionEntry newEntry){
		//TODO - implement the following and un-comment to remove hard-coding of escapes
		//Boolean escBool = (Boolean) environments.getEscapeTable().get(varName);
		//boolean escapes = escBool.booleanValue();
		boolean escapes = false;
		if(!escapes){
			Temp newVarTemp = new Temp();

			if(newEntry.getCategory() == VariableOrFunctionEntry.VARIABLE){
				VariableEntry newVariableEntry = (VariableEntry) newEntry;

				environments.getInstanceVariableTable().put(Symbol.symbol(newVariableEntry.getName()), newVariableEntry);	
				newVariableEntry.setRegisterTemp(newVarTemp);
			}
			else{
				FunctionEntry newFuncEntry = (FunctionEntry) newEntry;
				environments.getInstanceVariableTable().put(Symbol.symbol(newFuncEntry.getName()), newFuncEntry);	
			}
		}
	}

	private VariableOrFunctionEntry getVarOrFunctionEntryFromTranslatedDeclarator(TranslatedDeclarator translatedDeclarator, 
			SourceLocation location){
		VariableOrFunctionEntry varOrFunctionEntry = null;
		DeclaratorChainElement declaratorChainElement = translatedDeclarator.getDeclaratorChainElement();

		if(declaratorChainElement == null){ // Must be a simple variable declaration
			VariableEntry varEntry = new VariableEntry();
			TypeEntryWithAttributes teWithAttrs = new TypeEntryWithAttributes();
			varEntry.setType(teWithAttrs);
			varEntry.setName(translatedDeclarator.getName());
			varEntry.setSourceLocation(location);
			varOrFunctionEntry = varEntry;

			return varOrFunctionEntry;
		}

		int declaratorChainElementType = declaratorChainElement.getDeclaratorChainElementType();
		if(declaratorChainElementType == DeclaratorChainElement.DECL_CHAIN_FUNCTION){
			FunctionEntry funcEntry = (FunctionEntry) declaratorChainElement;
			funcEntry.setSourceLocation(location);
			varOrFunctionEntry = (FunctionEntry) funcEntry;
		}
		else{
			// Anything other than a function must be a variable
			VariableEntry varEntry = new VariableEntry();
			TypeEntryWithAttributes teWithAttrs = new TypeEntryWithAttributes();
			teWithAttrs.setBasicType((BasicType) declaratorChainElement);
			varEntry.setType(teWithAttrs);
			varEntry.setName(translatedDeclarator.getName());
			varEntry.setSourceLocation(location);
			varOrFunctionEntry = varEntry;
		}

		return varOrFunctionEntry;
	}

	private VariableOrFunctionEntry makeVariableOrFunctionEntry(TranslatedDeclarator translatedDeclarator, 
			TypeEntryWithAttributes teWithAttrs, SourceLocation location){

		// Get the variable or function entry from the translated declarator first
		VariableOrFunctionEntry variableOrFunEntry = getVarOrFunctionEntryFromTranslatedDeclarator(translatedDeclarator, location);

		if(variableOrFunEntry.getCategory() == VariableOrFunctionEntry.FUNCTION){ // Make new function entry
			FunctionEntry newFunctionEntry = (FunctionEntry) variableOrFunEntry;

			// Update the basic type of the variable entry from the type entry with attributes object passed
			TypeEntryWithAttributes teResult = newFunctionEntry.getReturnType();
			if(teResult == null)
				newFunctionEntry.setReturnType(teWithAttrs);
			else
				TypeEntryWithAttributes.updateBasicType(teResult, teWithAttrs);

			if(newFunctionEntry.getReturnType().getBasicType() instanceof PointerTypeEntry)	{
				// This is a function declaration/ definition that returns a pointer; set TypeEntryWithAttributes
				// so it is not returning void (might have noted as void in get getBaseEntryFromDeclSpecs();
				teWithAttrs.setVoid(false);
			}
			String name = translatedDeclarator.getName();
			newFunctionEntry.setName(name);

		}
		else{  // Make new variable entry
			VariableEntry newVariableEntry = (VariableEntry) variableOrFunEntry;

			teWithAttrs.setIsLValue(true);
			// Update the basic type of the variable entry from the type entry with attributes object passed
			TypeEntryWithAttributes teType = newVariableEntry.getType();
			if(teType == null)
				newVariableEntry.setType(teWithAttrs);
			else
				TypeEntryWithAttributes.updateBasicType(teType, teWithAttrs);

		}

		return variableOrFunEntry;
	}

	/**
	 * Analyzes and translates the declarator that is passed. isParamDeclContext is the parameter that indicates whether 
	 * the declarator is located in the declaration of a parameter declaration of a function declaration 
	 * or a function definition; if it isn't it could be
	 * either be in an external declaration or a local (auto) declaration or a parameter declaration in a function
	 * defintion - isFunctionDefContext indicates whether this is a function definition
	 * @param declarator
	 * @param isParamDeclContext
	 * @param isFunctionDefContext
	 * @return
	 */

	private TranslatedDeclarator translateDeclarator(Declarator declarator, 
			boolean isParamDeclContext, boolean isFunctionDefContext, boolean isDeclaratorInStructMember){

		SourceLocation location = new SourceLocation(declarator.getLineNum(), declarator.getPos());

		DirectDeclarator directDeclarator = declarator.getDirectDeclarator();

		Stack<DeclaratorChainElement> declaratorStack = new Stack<DeclaratorChainElement>();

		ParamTypeList paramTypeList = null;

		String name = null;

		while(true){
			if(directDeclarator == null)
				break;

			int directDeclaratorType = directDeclarator.getType();
			if(directDeclaratorType == DirectDeclarator.DECLR){
				Declarator subDeclarator = directDeclarator.getDeclarator();
				TranslatedDeclarator translatedSubDeclarator = translateDeclarator(subDeclarator, 
						isParamDeclContext, isFunctionDefContext,
						isDeclaratorInStructMember);

				if(translatedSubDeclarator == null){    // Error, move on to the next declarator
					directDeclarator = directDeclarator.getDirectDeclarator();		
					break;
				}

				name = translatedSubDeclarator.getName();
				paramTypeList = translatedSubDeclarator.getParamTypeList();

				declaratorStack.push(translatedSubDeclarator.getDeclaratorChainElement());				
			}
			else if(directDeclaratorType == DirectDeclarator.ARRAY){
				boolean isVariableLengthArray = false;
				RootExpr rootExpr = directDeclarator.getArraySizeExpr();
				Translated translated = translateExpr(rootExpr);
				TypeEntryWithAttributes val =  translated.getTypeEntry();
				if(val != null && val.getBasicType() != IntTypeEntry.getInstance() && val.getBasicType() != ShortTypeEntry.getInstance() && val.getBasicType() != CharTypeEntry.getInstance() && val.getBasicType() != LongTypeEntry.getInstance()){
					ErrorHandler errorHandler = ErrorHandler.getInstance();
					errorHandler.addError(sourceFileName, location, name+" ", "", ErrorHandler.E_ARRAY_SIZE_SPECIFIER_IS_INVALID);
					break;
				}
				IRTree irTree = translated.getIRTree();
				if(val != null && irTree != null && irTree instanceof IRTreeConst && val.getLiteralValue() != null){
					IRTreeConst irTreeConst = (IRTreeConst)irTree;
					// should of integer type only
					val.setLiteralValue(irTreeConst.getApInt().toString());
					analyzeArraySizeExpr(val, location);
				}
				else if(irTree != null) // Variable length Array (irTree can be null in case we pass a variable length array as a parameter)
					isVariableLengthArray = true;

				ArrayTypeEntry arrayTypeEntry = new ArrayTypeEntry();
				arrayTypeEntry.setDimension(val);
				arrayTypeEntry.setIrTree(irTree);
				arrayTypeEntry.setVariableLengthArray(isVariableLengthArray);
				declaratorStack.push(arrayTypeEntry);
			}
			else if(directDeclaratorType == DirectDeclarator.EMPTY_ARRAY){
				ArrayTypeEntry arrayTypeEntry = new ArrayTypeEntry();
				arrayTypeEntry.setDimension(null);
				declaratorStack.push(arrayTypeEntry);
			}
			else if(directDeclaratorType == DirectDeclarator.FUNC){
				FunctionEntry funcEntry = new FunctionEntry();
				if(!isFunctionDefContext){
					// This is a function declaration only, not a definition, so translate 
					// the params right away

					Vector<VariableEntry> formals = translateParamTypeList(directDeclarator.getParamTypeList(),
							isFunctionDefContext);

					if(directDeclarator.getParamTypeList().isHasEllipses()){
						funcEntry.setEndsWithEllipses(true);
					}

					funcEntry.setFormals(formals);
				}
				else{
					// This is a function definition, store the params to be translated later
					// when the entire function definition is to being analyzed.
					if(paramTypeList == null)
						paramTypeList = directDeclarator.getParamTypeList();
				}

				funcEntry.setSourceLocation(location);
				declaratorStack.push(funcEntry);
			}
			else if(directDeclaratorType == DirectDeclarator.ID){
				name = directDeclarator.getId();
			}
			else if(directDeclaratorType == DirectDeclarator.NO_ARG_FUNC){
				FunctionEntry funcEntry = new FunctionEntry();
				funcEntry.setFormals(null);
				funcEntry.setSourceLocation(location);
				declaratorStack.push(funcEntry);
			}
			else if(directDeclaratorType == DirectDeclarator.ID_LIST_FUNC){
				// TODO Implement this
			}

			directDeclarator = directDeclarator.getDirectDeclarator();			
		}

		// Analyze pointers, if any
		populateDeclaratorStackWithPointerEntries(declarator, declaratorStack);

		TranslatedDeclarator translatedDeclarator = new TranslatedDeclarator();
		if(declaratorStack.isEmpty()){
			// No stack, must be a simple declarator			
			translatedDeclarator.setName(name);
		}
		else{
			// Stack has been populated, now iterate through the stack, creating the VariableEntry or FunctionEntry 
			// for the name		
			translatedDeclarator =  getTranslatedDecalaratorFromStack(declaratorStack, name, location);
		}

		if(translatedDeclarator != null)
			translatedDeclarator.setParamTypeList(paramTypeList);

		return translatedDeclarator;
	}

	private TranslatedDeclarator getTranslatedDecalaratorFromStack(Stack<DeclaratorChainElement> 
	declaratorStack, String name, SourceLocation location){

		while(!declaratorStack.empty()){
			DeclaratorChainElement top = declaratorStack.pop();
			if(declaratorStack.empty()){ // Last object has already been popped, 				
				return new TranslatedDeclarator(top, name);				
			}
			else{
				DeclaratorChainElement nextChainElement = declaratorStack.pop();
				String status = nextChainElement.setNextElementInDeclaratorChain(top);
				if(status.equals(ErrorHandler.OK))
					declaratorStack.push(nextChainElement);
				else{
					ErrorHandler errorHandler = ErrorHandler.getInstance();
					errorHandler.addError(sourceFileName, location, name, "", status);
					break;
				}
			}
		}

		return null; // Must be an error
	}

	private void populateDeclaratorStackWithPointerEntries(Declarator declarator, 
			Stack<DeclaratorChainElement> stack){
		// Analyze pointer (if any)

		Pointer pointer = declarator.getPointer();

		int qualifierCount = 0;
		boolean isConstPtr = false;
		boolean isVolatilePtr = false;
		while(pointer != null){
			TypeQualifierList tql = pointer.getTypeQualifierList();
			SourceLocation location = new SourceLocation(pointer.getLineNum(), pointer.getPos());

			if(tql != null){
				qualifierCount++;
				TypeQualifier tq = tql.getTypeQualifier();
				if(tq.getType() == TypeQualifier.CONST)
					isConstPtr = true;
				else // Must be volatile
					isVolatilePtr = true;
			}
			if(qualifierCount > 2){  // Can be const and volatile, not more
				errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_TOO_MANY_QUALIFIERS);
			}
			else{
				if(qualifierCount == 2){
					if(!(isConstPtr && isVolatilePtr)){    // Either const or volatile is repeated
						errorHandler.addError(sourceFileName, location, "", "",  ErrorHandler.E_REPEATED_QUALIFIER);
					}
				}
			}

			PointerTypeEntry pte = new PointerTypeEntry();

			pte.setConstPtr(isConstPtr);
			pte.setVolatilePtr(isVolatilePtr);
			stack.push(pte);

			pointer = pointer.getPointer();   // Get next pointer, if available
		}		
	}

	/*
	 * This function returns the translated expr. and type it sets the excessInitializers flat
	 * to true if it finds excess initializers; the calling function can then
	 * use this information to add to the error list.
	 */
	private Translated translateInitializer(TypeEntryWithAttributes lhsType, Initializer initializer,
			short initializerContext, String lhsName, boolean isExternalDecl){

		AssignmentExpr assgnExpr = initializer.getInitExpr();
		if(assgnExpr != null){
			return translateAssignmentExpr(assgnExpr);
		}
		else{

			InitializerList initlzrList = initializer.getInitializerList();
			SourceLocation location = new SourceLocation(initializer.getLineNum(), initializer.getPos());	

			if(initializerContext == ARRAY_INITIALIZER_TRANSLATION_CONTEXT){
				// This is an array initializer
				BasicType btLhs = lhsType.getBasicType();
				if(btLhs instanceof ArrayTypeEntry ){

					ArrayTypeEntry btLhsArr = (ArrayTypeEntry) btLhs;

					int maxSize = -1;  // No size specified in declaration
					TypeEntryWithAttributes teDim = btLhsArr.getDimension();
					if(teDim != null){
						try{
							maxSize = Integer.parseInt(teDim.getLiteralValue());
						}
						catch(NumberFormatException nfe){
							errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_ARRAY_SUBSCRIPT_NOT_INTEGER);
						}
					}

					return translateArrayInitializerList(lhsType, maxSize, initlzrList, location, 0, lhsName, isExternalDecl);
				}
				else if(btLhs instanceof StructOrUnionTypeEntry){

					StructOrUnionTypeEntry btLhsStruct = (StructOrUnionTypeEntry) btLhs;
					LinkedHashMap<String, TypeEntryWithAttributes> map = btLhsStruct.getMembers();
					Set<Entry<String, TypeEntryWithAttributes>> entries = map.entrySet();
					Iterator<Entry<String, TypeEntryWithAttributes>> iterator = entries.iterator();
					TypeEntryWithAttributes memberType = null;
					int maxSize = -1;
					while(iterator.hasNext()){
						Entry<String, TypeEntryWithAttributes> entry = iterator.next();
						TypeEntryWithAttributes tewa = entry.getValue();
						BasicType basicType = tewa.getBasicType();
						if(basicType instanceof ArrayTypeEntry){
							memberType = tewa;
							ArrayTypeEntry arrayTypeEntry = (ArrayTypeEntry)basicType;
							TypeEntryWithAttributes teDim = arrayTypeEntry.getDimension();
							if(teDim != null){
								try{
									maxSize = Integer.parseInt(teDim.getLiteralValue());
								}
								catch(NumberFormatException nfe){}
							}
							Translated translated = null;
							translated = translateArrayInitializerList(memberType, maxSize, initlzrList, location, 0, lhsName, isExternalDecl);
							translated.setEntry(lhsType);
							if(translated.isExcessInitializersInArrayInit()){
								translated.setExcessInitializersInArrayInit(false);
								translated.setExcessInitializersInStructnit(true);
								if(!btLhsStruct.isStruct())
									errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_EXCESS_INITIALIZERS_IN_UNION_INIT);
							}
							return translated;
						}
					}

				}
			}
			else if(initializerContext == STRUCT_INITIALIZER_TRANSLATION_CONTEXT){			
				// This is a struct initializer 
				BasicType btLhs = lhsType.getBasicType();
				if(btLhs instanceof StructOrUnionTypeEntry ){
					return translateStructInitializerList(lhsType, initlzrList, location, lhsName, isExternalDecl);
				}
				else{			
					Translated transExpAndType = translateStructInitializerList(lhsType, initlzrList, location, lhsName, isExternalDecl);
					transExpAndType.setExcessInitializersInStructnit(true);

					return null;
				}
			}
			else if(initializerContext == COMPOUND_INITIALIZER_TRANSLATION_CONTEXT && initlzrList == null){
				CompoundStatement compoundStatement = initializer.getCompoundStatement();
				BlockItemList blockItemList = compoundStatement.getBlockItemList();
				if(blockItemList == null)
					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_VOID_VALUE_NOT_IGNORED);
				Statement statement = null;
				while(blockItemList != null){
					BlockItem blockItem = blockItemList.getBlockItem();
					if(blockItem != null){
						statement = blockItem.getStatement();
						blockItemList = blockItemList.getBlockItemList();
					}
					else
						errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_VOID_VALUE_NOT_IGNORED);
				}
				if(statement != null && !(statement instanceof ExprStatement)){
					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_VOID_VALUE_NOT_IGNORED);
				}
				if(!errorHandler.isFoundStrictError()){
					Translated translated = new Translated();
					IRTree irTree = translateCompoundStatement(compoundStatement, false);
					translated.setIRTree(irTree);
					return translated;
				}
				return null;
			}
			// This is a special case where its neither an array type nor 
			// struct type nor compound expression but it has an initializer list
			// For e.g. char *ptr = {"abc", "pqr"}; which doesn't give an error
			// but warning i.e. "excess elements in scalar initializer"
			else if(initlzrList != null){
				Translated transExpAndType = translateArrayInitializerList(lhsType, 
						-1, initlzrList, location, 0, lhsName, isExternalDecl);
				transExpAndType.setExcessInitializersInArrayInit(true);
				return transExpAndType;
			}
			return null;
		}		
	}

	private Translated translateArrayInitializerList(TypeEntryWithAttributes tpBaseType, 
			int size, InitializerList initializerList, SourceLocation location, int arrayBaseNum, String lhsName, boolean isExternalDecl){

		int dimension = 0;
		Vector<Translated> values = new Vector<Translated>();
		boolean excessInitializers = false;
		boolean initializerHasAllScalarElements = true;

		while(initializerList != null){
			Initializer initializer = initializerList.getInitializer();
			AssignmentExpr assignmentExpr = initializer.getInitExpr();
			if(assignmentExpr != null){
				Translated translated = translateAssignmentExpr(assignmentExpr);
				translated.setSourceLocation(new SourceLocation(assignmentExpr.getLineNum(),
						assignmentExpr.getPos()));

				values.addElement(translated);
			}
			else{
				initializerHasAllScalarElements = false;
				InitializerList list = initializer.getInitializerList();

				// To translate this list, get the "sub-array" of the declaration
				BasicType bt = tpBaseType.getBasicType();
				if(bt instanceof ArrayTypeEntry){
					ArrayTypeEntry btArr = (ArrayTypeEntry) bt;
					if(btArr.getDimension() != null)
						dimension = Integer.parseInt(btArr.getDimension().getLiteralValue());
					TypeEntryWithAttributes teBtArr = btArr.getBaseTypeEntry();
					BasicType btTeBtArr = teBtArr.getBasicType();
					if(btTeBtArr instanceof ArrayTypeEntry){
						ArrayTypeEntry btArrChild = (ArrayTypeEntry) btTeBtArr;

						TypeEntryWithAttributes teDim = btArrChild.getDimension();
						int maxSize = 0;
						if(teDim != null){
							try{
								maxSize = Integer.parseInt(teDim.getLiteralValue());
							}
							catch(NumberFormatException nfe){
								errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_ARRAY_SUBSCRIPT_NOT_INTEGER);
							}
						}

						Translated translated = translateArrayInitializerList(teBtArr,
								maxSize, list, location, ++arrayBaseNum, lhsName, isExternalDecl);						
						values.addElement(translated);
					}
					else if(btTeBtArr instanceof StructOrUnionTypeEntry){
						Translated translated = translateStructInitializerList(teBtArr, list,
								location, lhsName, isExternalDecl);
						values.addElement(translated);
					}
					else{
						Translated translated = translateArrayInitializerList(
								teBtArr, -1, list, location, ++arrayBaseNum, lhsName, isExternalDecl);
						values.addElement(translated);

						excessInitializers = true;
					}			
				}
				else if(bt instanceof StructOrUnionTypeEntry){
					Translated translated = translateStructInitializerList(
							tpBaseType, list, location, lhsName, isExternalDecl);
					values.addElement(translated);
				}
				else{
					Translated translated = translateArrayInitializerList(
							tpBaseType, -1, list, location, ++arrayBaseNum, lhsName, isExternalDecl);
					values.addElement(translated);
					excessInitializers = true;
				}				
			}
			count_of_structures++;

			initializerList = initializerList.getInitializerList();
		}

		if(initializerHasAllScalarElements){
			Translated translated = 
					translateCompoundInitializerWithAllScalarElements(tpBaseType, values, location, lhsName, isExternalDecl);
			if(excessInitializers)
				translated.setExcessInitializersInArrayInit(true);
			return translated;
		}

		// Identify the base type on the lhs
		TypeEntryWithAttributes baseType = null;
		if(tpBaseType.getBasicType() instanceof ArrayTypeEntry){
			ArrayTypeEntry baseArrType = (ArrayTypeEntry) tpBaseType.getBasicType();
			baseType = baseArrType.getBaseTypeEntry();			
		}
		else{
			baseType = tpBaseType;
		}

		Translated translated = 
				getTransExpAndTypeForSingleDimArrayInit(baseType, size, values, location, lhsName, dimension, isExternalDecl);
		if(excessInitializers)
			translated.setExcessInitializersInArrayInit(true);
		return translated;		
	}

	private Translated getTransExpAndTypeForSingleDimArrayInit(TypeEntryWithAttributes baseType, 
			int size, Vector<Translated> values, SourceLocation location, String lhsName, int dimension, boolean isExternalDecl){
		IRTree irTree = null;
		List<IRTreeConst> listOfIrTreeConst = new ArrayList<IRTreeConst>();
		// Check if all the elements are the same type
		boolean allElementsSameType = true;
		// Check if all the elements are constants
		boolean allElementsConstants = true;

		int count = 0;
		TypeEntryWithAttributes tpFirstElement = null;

		Stack<IRTree> irTreeStack = new Stack<IRTree>();

		if(values.size() > 0){	
			for(Translated tp : values){
				if(count == 0)
					tpFirstElement = tp.getTypeEntry();
				else {
					if(!(tpFirstElement.equals(tp.getTypeEntry()))){
						allElementsSameType = false;
					}
				}

				checkAssignmentCompatibility(baseType, tp.getTypeEntry(), location, 
						Semantic.ASSIGNMENT_IN_DECLR_INIT_EXPR, AssignmentOperator.ASSIGN, null, -1);
				IRTree irTree2 = tp.getIRTree();
				if(irTree2 instanceof IRTreeDeclaration){
					IRTreeDeclaration irTreeDeclaration = (IRTreeDeclaration)irTree2;
					IRTreeExp irTreeExp = irTreeDeclaration.getInitializerExpression();
					irTree2 = irTreeExp;
				}
				else if(irTree2 instanceof IRTreeStatementList){
					irTreeStack.push(irTree2);
					IRTreeStatement irTreeStatement = translatingMediator.getLeafStatementList((IRTreeStatementList)irTree2).getStatement();
					if(irTreeStatement instanceof IRTreeExpressionStm){
						IRTreeExpressionStm expressionStm = (IRTreeExpressionStm)irTreeStatement;
						irTree2 = expressionStm.getExpressionTree();
					}
				}
				if(!(irTree2 instanceof IRTreeConst)){
					allElementsConstants = false;
				}
				else{
					IRTreeConst irTreeConst = (IRTreeConst)irTree2;
					listOfIrTreeConst.add(irTreeConst);
				}
				count++;
			}
		}

		if(size > -1  && (dimension > 0 ? count > dimension : count > size)){			
			ErrorHandler errorHandler = ErrorHandler.getInstance();

			// If the excess initializer error message already exists, don't add it again
			if(!errorHandler.errorOrWarningAlreadyExists(sourceFileName, location, ErrorHandler.ERROR_MSGS_ONLY, 
					ErrorHandler.E_EXCESS_INITIALIZERS_IN_ARRAY_INIT)) 	{
				// Add this error only if required; this might come up several times
				errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_EXCESS_INITIALIZERS_IN_ARRAY_INIT);
			}
		}
		ArrayTypeEntry arrayInit = new ArrayTypeEntry();

		if(!allElementsSameType)
			arrayInit.setBaseTypeEntry(baseType);
		else
			arrayInit.setBaseTypeEntry(tpFirstElement);

		TypeEntryWithAttributes teDim = new TypeEntryWithAttributes();
		teDim.setConst(true); teDim.setLiteralValue("" + dimension);
		arrayInit.setDimension(teDim);

		// Initialise excess elements by zero initialisers
		if(allElementsSameType){
			if(count+2 < dimension){
				// call llvm.memset
				translatingMediator.initializeAllElementsToDefaultValuesAndAssignRestOfTheValues(compilationContext, irTreeStack, lhsName, arrayInit, listOfIrTreeConst);
				// assign each element one by one to the array and return
			}
			else{
				// Create and return an array type		
				arrayInit.setBaseTypeEntry(tpFirstElement);
				initializeExtraElementsWithDefaultValues(listOfIrTreeConst,tpFirstElement,dimension);
				if(allElementsConstants){
					irTree = translatingMediator.translateConstantArray(listOfIrTreeConst, arrayInit, compilationContext, currentFuncName, lhsName, isExternalDecl);
					irTreeStack.push(irTree);
				}
			}
		}

		if(!irTreeStack.empty())
			irTree = translatingMediator.translateSeqStatement(irTreeStack);

		TypeEntryWithAttributes teRet = new TypeEntryWithAttributes();		
		teRet.setBasicType(arrayInit);
		if(count+2 >= dimension)
			teRet.setConst(true);

		Translated retValue = new Translated();
		retValue.setEntry(teRet);
		retValue.setIRTree(irTree);

		return retValue;
	}

	private void initializeExtraElementsWithDefaultValues(
			List<IRTreeConst> listOfIrTreeConst,
			TypeEntryWithAttributes tpFirstElement, int dimension) {
		BasicType basicType = tpFirstElement.getBasicType();
		boolean isUnSigned = tpFirstElement.isUnsigned();
		Type type = translatingMediator.getLLVMType(basicType, tpFirstElement.isUnsigned(), compilationContext);
		int size = listOfIrTreeConst.size();
		APInt val = null;
		APFloat apFloat = null;
		org.tamedragon.common.llvmir.types.Constant constant = null;
		IRTreeConst irTreeConst = null;
		while(size < dimension && (!type.isPointerType())){
			if(basicType instanceof CharTypeEntry){
				val = new APInt(8, "000", false);
				try {
					constant = new ConstantInt(Type.getInt8Type(compilationContext, false), val);
				} catch (InstantiationException e) {
					e.printStackTrace();
					System.exit(-1);
				}
				irTreeConst = new IRTreeConst(constant);
			}
			else if(basicType instanceof IntTypeEntry){
				val = new APInt(32, "0", true);
				try {
					constant = new ConstantInt(Type.getInt32Type(compilationContext, true), val);
				} catch (InstantiationException e) {
					e.printStackTrace();
					System.exit(-1);
				}
				irTreeConst = new IRTreeConst(constant);
			}
			else if(basicType instanceof FloatTypeEntry){
				apFloat = new APFloat(APFloat.IEEEdouble, "0.0");
				try {
					constant = new ConstantFP(Type.getFloatType(compilationContext), apFloat);
				} catch (InstantiationException e) {
					e.printStackTrace();
					System.exit(-1);
				}
				irTreeConst = new IRTreeConst(constant);
			}
			else if(basicType instanceof DoubleTypeEntry){
				apFloat = new APFloat(APFloat.IEEEdouble, "0.0");
				try {
					constant = new ConstantFP(Type.getDoubleType(compilationContext), apFloat);
				} catch (InstantiationException e) {
					e.printStackTrace();
					System.exit(-1);
				}
				irTreeConst = new IRTreeConst(constant);
			}
			else if(basicType instanceof ArrayTypeEntry){
				Type arrayType = translatingMediator.getLLVMType(basicType, isUnSigned, compilationContext);
				constant = ConstantAggregateZero.get(arrayType);
				irTreeConst = new IRTreeConst(constant);
			}
			listOfIrTreeConst.add(irTreeConst);
			size = listOfIrTreeConst.size();
		}
	}

	private boolean allElementsOfSameType(Vector<Translated> values){
		// Check if all the elements are the same type
		boolean allElementsSameType = true;
		int count = 0;

		TypeEntryWithAttributes tpFirstElement = null;

		for(Translated tp : values){
			if(count == 0)
				tpFirstElement = tp.getTypeEntry();
			else {
				if(!(tpFirstElement.equals(tp.getTypeEntry()))){
					allElementsSameType = false;
				}
			}
			count++;
		}

		return allElementsSameType;
	}


	/**
	 * Checks if a symbol that should not be repeated in any context has actually been repeated.
	 */
	private boolean errorByRepeating(String name, SourceLocation location, VariableOrFunctionEntry newEntry, 
			short declarationContext, boolean hasInitializer){

		int newEntryCategory = newEntry.getCategory();

		// Check if there is already an old entry in this environment with the same name
		VariableOrFunctionEntry oldEntry = 
				(VariableOrFunctionEntry)environments.getInstanceVariableTable().get(Symbol.symbol(name));
		if(!environments.getInstanceVariableTable().keyInCurrentScope(Symbol.symbol(name)))
			return false;


		// The variable has been repeated
		if(declarationContext == LOCAL_VARIABLE_CONTEXT){
			// Its not ok to repeat -> probably a declaration inside a function or a parameter
			// in a function defintion
			if(oldEntry.getCategory() == VariableOrFunctionEntry.VARIABLE){
				// Already declared as a variable, flag an error
				errorHandler.addError(sourceFileName, location, name,
						ErrorHandler.AI_SEE_DECLARATION + sourceFileName + ":" + oldEntry.getSourceLocation(),
						ErrorHandler.E_VARIABLE_ALREADY_DEFINED);
			}
			else{
				errorHandler.addError(sourceFileName, location, name, 
						ErrorHandler.AI_SEE_DECLARATION + sourceFileName + ":" + oldEntry.getSourceLocation(),
						ErrorHandler.E_FUNCTION_ALREADY_DEFINED);
			}
			return true;
		}
		else{
			// Its ok to repeat -> probably an extern declaration or a parameter in a function declaration,
			// but make sure the types are the same

			int oldEntryCategory = oldEntry.getCategory();
			if(newEntryCategory == VariableOrFunctionEntry.VARIABLE &&  
					oldEntryCategory == VariableOrFunctionEntry.VARIABLE){
				VariableEntry oldVar = (VariableEntry) oldEntry;
				VariableEntry newVar = (VariableEntry) newEntry;
				if(!(oldVar.getType().equals(newVar.getType()))){						
					errorHandler.addError(sourceFileName, location, name,
							ErrorHandler.AI_SEE_DECLARATION + sourceFileName + ":" + oldEntry.getSourceLocation(),
							ErrorHandler.E_VARIABLE_ALREADY_DEFINED);						
				}
				else{
					// ....also external declarations cannot be initialized more than once
					if(!hasInitializer)
						return true;

					VariableOrFunctionEntry earlierEntry = 
							(VariableOrFunctionEntry)environments.getInstanceVariableTable().get(Symbol.symbol(name));

					VariableEntry earlierVarEntry = (VariableEntry) earlierEntry;
					if(earlierVarEntry.getRegisterTemp() != null){
						errorHandler.addError(sourceFileName, location, name,
								null, ErrorHandler.E_VARIABLE_ALREADY_INITIALIZED);
					}
				}

				return true;
			}	
			else if(newEntryCategory == VariableOrFunctionEntry.FUNCTION &&  
					oldEntryCategory == VariableOrFunctionEntry.FUNCTION){

				FunctionEntry oldVar = (FunctionEntry) oldEntry;
				FunctionEntry newVar = (FunctionEntry) newEntry;
				if(!(oldVar.getReturnType().equals(newVar.getReturnType()))){						
					errorHandler.addError(sourceFileName, location, name,
							ErrorHandler.AI_SEE_DECLARATION + sourceFileName + ":" + oldEntry.getSourceLocation(),
							ErrorHandler.E_FUNCTION_ALREADY_DEFINED);						
				}

				return true;
			}	
			else
				return false;
		}

	}

	/**
	 * Returns a list of variable type entry objects, each object obtained from the parameter information. Each
	 * type entry is obtained the same way it is obtained in the translateDeclaration() function, but here, the methods
	 * are a stripped down version of the translateDeclarator() function.
	 * 
	 */

	private Vector<VariableEntry> getVarEntriesFromParamTypeList(ParamTypeList paramTypeList){

		Vector<VariableEntry> paramEntries = new Vector<VariableEntry>();
		if(paramTypeList == null){   // No parameters
			return paramEntries; 
		}

		ParamList paramList = paramTypeList.getParamList();

		while(true){    // Iterate through the param list
			if(paramList == null) break;

			VariableEntry newVariableEntry = null;
			ParamDeclaration paramDecl = paramList.getParamDeclaration();
			DeclarationSpecifiers declSpecs = paramDecl.getDeclarationSpecifiers();
			Declarator declarator = paramDecl.getDeclarator();
			AbstractDeclarator abstractDeclarator = paramDecl.getAbstractDeclarator();

			TypeEntryWithAttributes  baseEntry = getBaseEntryFromSpecsList(declSpecs, false, false, false);
			if(declarator != null){			
				TranslatedDeclarator translatedDeclarator = translateDeclarator(declarator, true, true, false);
				if(translatedDeclarator == null){ // Error, move on to the next declarator
					paramList = paramList.getParamList();  // Get the next param list
					continue;
				}

				SourceLocation location = new SourceLocation(declarator.getLineNum(), declarator.getPos());

				VariableOrFunctionEntry newEntry = makeVariableOrFunctionEntry(translatedDeclarator, 
						baseEntry, location);
				newVariableEntry = (VariableEntry) newEntry;


			}
			else{   // Get from abstract declarator
				if((abstractDeclarator != null)){
					// TODO - Implement this later
				}
			}
			if(newVariableEntry != null)
				paramEntries.addElement(newVariableEntry);

			paramList = paramList.getParamList();  // Get the next param list
		}

		return paramEntries;
	}


	/**
	 * Returns true if the function entry's signature matches that of the param type list passed in the second parameter
	 * @param funcEntry
	 * @param paramTypeListToTranslate
	 * @return
	 */
	private boolean functionSignatureMatches(FunctionEntry funcEntry, ParamTypeList paramTypeListToTranslate){

		Vector<VariableEntry> varEntriesFromParamList = getVarEntriesFromParamTypeList(paramTypeListToTranslate);
		if(varEntriesFromParamList == null) // Error in declaring param list in function definition
			return false;

		Vector<VariableEntry> varEntries = funcEntry.getFormals();  // Get the formal parameters
		if(varEntries == null) varEntries = new Vector<VariableEntry>();

		int numEntries = varEntries.size();
		int numVarsInParams = varEntriesFromParamList.size();
		if(!funcEntry.isEndsWithEllipses()){
			if(numEntries != numVarsInParams)
				return false;
		}
		else{
			if(numVarsInParams < numEntries)
				return false;
		}

		// Check the signatures now
		for(int i = 0; i < numEntries; i++){
			VariableEntry varEntry = varEntries.elementAt(i);
			TypeEntryWithAttributes tpWithAttrs =  varEntry.getType();

			VariableEntry varEntryFromParam = varEntriesFromParamList.elementAt(i);
			TypeEntryWithAttributes tpFromParamWithAttrs =  varEntryFromParam.getType();

			if(!tpWithAttrs.equals(tpFromParamWithAttrs))
				return false;
		}

		return true;
	}

	/**
	 * @param specifierList
	 * @return
	 */
	public TypeEntryWithAttributes getBaseEntryFromSpecsList(SpecifierListType specifierList,
			boolean forExternalDecl, boolean isForSpecifierListInStructDecl, boolean isForTypedef){

		TypeEntryWithAttributes typeEntryWithAttributes = new TypeEntryWithAttributes();

		boolean isConst = false;
		boolean isVolatile = false;
		boolean isSigned = false;    // By default
		boolean isUnsigned = false;
		boolean isAuto = false;
		boolean isExtern = false;
		boolean isRegister = false;
		boolean isStatic = false;
		boolean isTypedef = false;
		boolean isStructOrUnionSpec = false;
		boolean isEnumSpec = false;
		boolean isTypedefName = false;
		boolean isInt  = false;
		boolean isDouble  = false;
		boolean isShort  = false;
		boolean isLong  = false;
		boolean isFloat  = false;
		boolean isChar  = false;
		boolean isVoid  = false;
		BasicType newEntry = null;
		StructOrUnionTypeEntry structOrUnionTypeEntry = null;  // 
		int typeCount = 0;
		Object lastSpecifier = null;   // The last specifier
		int lineNum = -1; // Initialize line num to zero
		int pos = -1;
		int numStorageSpecs = 0;
		SourceLocation location = null;
		Vector<BasicType> typeQueue = new Vector<BasicType>();

		// Iterate through the specifiers
		while(true){
			if(specifierList == null)
				break;

			TypeSpecifier ts = specifierList.getTypeSpecifier();
			if(ts != null){				
				lineNum = ts.getLineNum();

				pos = ts.getPos();
				location = new SourceLocation(lineNum, pos);

				int tsType = ts.getType();
				if(tsType == TypeSpecifier.CHAR){
					typeCount++;
					typeQueue.add(CharTypeEntry.getInstance());
					isChar = true;
					isSigned = true; // By default
				}
				else if(tsType == TypeSpecifier.DOUBLE){
					typeCount++;
					typeQueue.add(DoubleTypeEntry.getInstance());
					isDouble = true;					

					if(isSigned){  // This is signed; treat as int 
						isInt = true;
						isDouble = false;
						errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_DOUBLE_CANNOT_BE_USED_WITH_SIGNED_SPEC);						
					}
					if(isUnsigned){ // This is unsigned; treat as int 
						isInt = true;
						isDouble = false;
						errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_DOUBLE_CANNOT_BE_USED_WITH_UNSIGNED_SPEC);
					}
				}
				else if(tsType == TypeSpecifier.ENUM_SPEC){
					typeCount++;

					EnumSpecifier enumSpecifier = (EnumSpecifier) ts;
					EnumSpecTypeEntry enumSpecTypeEntry = translateEnumSpecifier(enumSpecifier);
					isEnumSpec = true;	
					typeQueue.add(enumSpecTypeEntry);

				}
				else if(tsType == TypeSpecifier.FLOAT){
					typeCount++;
					isFloat = true;
					typeQueue.add(FloatTypeEntry.getInstance());
					if(isSigned){
						isFloat = false;
						isInt = true;
						errorHandler.addError(sourceFileName, location, "", "",  ErrorHandler.E_FLOAT_CANNOT_BE_USED_WITH_SIGNED_SPEC);
					}
					if(isUnsigned){
						isFloat = false;
						isInt = true;
						errorHandler.addError(sourceFileName, location, "", "",  ErrorHandler.E_FLOAT_CANNOT_BE_USED_WITH_UNSIGNED_SPEC);	
					}
				}
				else if(tsType == TypeSpecifier.INT){
					typeCount++;
					isInt = true;
					typeQueue.add(IntTypeEntry.getInstance());
				}
				else if(tsType == TypeSpecifier.LONG){
					typeCount++;
					isLong = true;
					typeQueue.add(LongTypeEntry.getInstance());
				}
				else if(tsType == TypeSpecifier.SHORT){
					typeCount++;
					isShort = true;
					typeQueue.add(ShortTypeEntry.getInstance());
				}
				else if(tsType == TypeSpecifier.SIGNED){
					isSigned = true;
					typeQueue.add(IntTypeEntry.getInstance());
					if(isFloat){
						isSigned = false;
						errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_FLOAT_CANNOT_BE_USED_WITH_SIGNED_SPEC);
					}
					if(isDouble){
						isSigned = false;
						errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_DOUBLE_CANNOT_BE_USED_WITH_SIGNED_SPEC);
					}
				}
				else if(tsType == TypeSpecifier.STRUCT_OR_UNION_SPEC){
					typeCount++;
					StructOrUnionSpecifier structOrUnionSpecifier = (StructOrUnionSpecifier) ts;
					structOrUnionTypeEntry = translateStructOrUnionSpecifier(structOrUnionSpecifier, forExternalDecl,
							isForSpecifierListInStructDecl);
					isStructOrUnionSpec = true;
					typeQueue.add(structOrUnionTypeEntry);
				}
				else if(tsType == TypeSpecifier.TYPEDEF_NAME){
					isTypedefName = true;
					TypeDefName tdName = (TypeDefName) ts;
					String name = tdName.getName();
					Object entryObject =  environments.getInstanceTypeTable().get(Symbol.symbol(name));

					if(entryObject instanceof TypeDefNameTypeEntry){
						typeCount++;
						TypeDefNameTypeEntry tdEntry = (TypeDefNameTypeEntry) entryObject;
						typeQueue.add(tdEntry);
					}
					else{
						errorHandler.addError(sourceFileName, location, name + ":", "", ErrorHandler.E_TYPE_NOT_DEFINED);
					}

					// If this is a typedef and more than one type specifier has been specified along with another
					// typedef, regard it as error: eg: 'typedef unsigned ABCD EFGH;' 
					if(isForTypedef  && typeQueue.size() > 1){  
						errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_INVALID_TYPEDEF_DECLARATION);
					} 					
				}
				else if(tsType == TypeSpecifier.UNSIGNED){
					isUnsigned = true;
					typeQueue.add(IntTypeEntry.getInstance());
					if(isFloat){
						isUnsigned = false;  // TODO confirm this
						errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_FLOAT_CANNOT_BE_USED_WITH_UNSIGNED_SPEC);
					}
					if(isDouble){
						isUnsigned = false;  // TODO confirm this
						errorHandler.addError(sourceFileName, location,"", "", ErrorHandler.E_DOUBLE_CANNOT_BE_USED_WITH_UNSIGNED_SPEC);
					}
				}
				else if(tsType == TypeSpecifier.TYPEOF){
					TypeOf typeOf = (TypeOf)ts;
					Translated translated = null;
					RootExpr expression = typeOf.getExpression();
					if(expression != null){
						translated = translateExpr(expression);
					}
					else{
						// Must be a TypeName
						TypeName typeName = typeOf.getTypeName();
						translated = getTranslatedFromTypeName(typeName);
					}
					typeQueue.add(translated.getTypeEntry().getBasicType());
					typeCount++;
				}
				else {// Must be void
					typeCount++;
					isVoid = true;	
					typeQueue.add(VoidTypeEntry.getInstance());
				}

				lastSpecifier = ts;
			}

			TypeQualifier tq = specifierList.getTypeQualifier();
			if(tq != null){
				lineNum = tq.getLineNum();

				pos = tq.getPos();
				location = new SourceLocation(lineNum, pos);

				int tqtype = tq.getType();
				if(tqtype == TypeQualifier.CONST)
					isConst = true;				
				else  // Must be volatile
					isVolatile = true;		

				lastSpecifier = tq;				
			}

			StorageClassSpecifiers scs = specifierList.getStorageClassSpecifiers();
			if(scs != null){
				lineNum = scs.getLineNum();
				pos = scs.getPos();
				location = new SourceLocation(lineNum, pos);

				int scstype = scs.getType();
				if(scstype == StorageClassSpecifiers.AUTO)
					isAuto = true;
				else if(scstype == StorageClassSpecifiers.REGISTER)
					isRegister = true;
				else if(scstype == StorageClassSpecifiers.STATIC)
					isStatic = true;
				else if(scstype == StorageClassSpecifiers.EXTERN)
					isExtern = true;
				else 
					isTypedef = true;

				numStorageSpecs++;

				lastSpecifier = scs;	
			}

			specifierList = specifierList.getNext();
		}

		// Create the base type based on information gathered above
		boolean errorInIdentifyingType = false;
		pos = 0;
		location = new SourceLocation(lineNum, pos);

		if(numStorageSpecs > 1){  // Declarations cannot have more than one storage specifier
			errorHandler.addError(sourceFileName, location, "", "",   ErrorHandler.E_MORE_THAN_ONE_STORAGE_SPEC);
		}

		if(forExternalDecl){   // An external declaration should not be declared as auto or register
			if(isAuto || isRegister){
				errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_INVALID_STORAGE_SPEC_FOR_EXTERNAL_DEC);

			}
		}

		if(typeCount != 1){  // Check number of types specified
			if(typeCount > 1){  // Only one type should be specified	
				if(!(typeCount == 2 && ((isInt && isShort) || (isInt && isLong))))
					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_MORE_THAN_ONE_TYPE);
			}
			else{               // NO TYPE, If this is NOT an extern or unsigned, at least one type should be specified 
				if(!forExternalDecl && !isUnsigned){
					if(!isForSpecifierListInStructDecl){
						errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_NO_TYPE_SPECIFIED);
						errorInIdentifyingType = true;
					}
				}
			}
		}

		// TODO: Are there other checks to see if two specifiers cannot come together? Eg.: const, volatile?

		if(!errorInIdentifyingType){

			// Looks good so far, create the base type
			if(typeQueue.size() > 0){
				newEntry = typeQueue.elementAt(0);
			}
			else  // Must be extern declaration with no type specified, so default to IntType
				newEntry = IntTypeEntry.getInstance();	

			typeEntryWithAttributes.setBasicType(newEntry);

			// Assign other adornments
			typeEntryWithAttributes.setConst(isConst);
			typeEntryWithAttributes.setVolatile(isVolatile);
			typeEntryWithAttributes.setExtern(isExtern);
			typeEntryWithAttributes.setRegister(isRegister);
			typeEntryWithAttributes.setUnsigned(isUnsigned);
			typeEntryWithAttributes.setStatic(isStatic);
			typeEntryWithAttributes.setTypedef(isTypedef);
			typeEntryWithAttributes.setAuto(isAuto);
			typeEntryWithAttributes.setVoid(isVoid);
			typeEntryWithAttributes.setTypedefName(isTypedefName);
		}

		return typeEntryWithAttributes;
	}

	public EnumSpecTypeEntry translateEnumSpecifier(EnumSpecifier enumSpecifier){		
		String tag = enumSpecifier.getName();

		SourceLocation srcLocation = new SourceLocation(enumSpecifier.getLineNum(), enumSpecifier.getPos());
		EnumList enumList = enumSpecifier.getEnumList();

		if(tag != null){			
			if(environments.getInstanceTypeTable().keyInCurrentScope(Symbol.symbol(tag))){  // Tag already defined
				EnumSpecTypeEntry oldEntry = (EnumSpecTypeEntry)environments.getInstanceTypeTable().get(Symbol.symbol(tag));
				if(enumList != null){  // Error				
					errorHandler.addError(sourceFileName, srcLocation, tag + ":",
							ErrorHandler.AI_SEE_DECLARATION + sourceFileName + ":" + oldEntry.getSourceLocation(),
							ErrorHandler.E_ENUM_TYPE_ALREADY_DEFINED);

					return null;
				}	
				else {  // Return existing
					return oldEntry;
				}
			}					
		}

		Map<String, ConstantInt>  enumNames = new LinkedHashMap<String, ConstantInt>();
		int initialValue = 0;
		while(enumList != null){
			Enumerator enumerator = enumList.getEnumarator();
			String name = enumerator.getName();


			// Check if the name has been repeated
			if(environments.getInstanceVariableTable().keyInCurrentScope(Symbol.symbol(name))){
				// This name has been repeated

				VariableOrFunctionEntry oldEntry = 
						(VariableOrFunctionEntry)environments.getInstanceVariableTable().get(Symbol.symbol(name));

				errorHandler.addError(sourceFileName, srcLocation, name + ":",
						ErrorHandler.AI_SEE_DECLARATION + sourceFileName + ":" + oldEntry.getSourceLocation(),
						ErrorHandler.E_ENUMERATOR_ALREADY_DEFINED);

			}
			else{				
				VariableEntry varEntry = new VariableEntry();
				TypeEntryWithAttributes teWithAttrs = new TypeEntryWithAttributes();
				teWithAttrs.setBasicType(IntTypeEntry.getInstance());
				teWithAttrs.setEnumConstant(true);
				varEntry.setType(teWithAttrs);
				varEntry.setName(name);
				varEntry.setSourceLocation(srcLocation);
				environments.getInstanceVariableTable().put(Symbol.symbol(name), varEntry);
			}

			// If the enumerator has a initialization value, check if it is an integer
			ConstExpr constExpr = enumerator.getInitValue();
			if(constExpr != null){
				Translated teInitValue = translateConditionalExpr(constExpr.getCondExpr());
				TypeEntryWithAttributes teWithAttrs = teInitValue.getTypeEntry();
				BasicType tp = teWithAttrs.getBasicType();

				if(!(tp == IntTypeEntry.getInstance() || tp == CharTypeEntry.getInstance() ||
						tp == ShortTypeEntry.getInstance() || tp == LongTypeEntry.getInstance())){
					SourceLocation location = new SourceLocation(enumerator.getLineNum(), enumerator.getPos());
					errorHandler.addError(sourceFileName, location, "", "",  ErrorHandler.E_ENUM_INIT_VAL_NOT_INTEGER);				
				}
				else if(!errorHandler.isFoundStrictError()){
					IRTreeConst irTreeConst = (IRTreeConst) teInitValue.getIRTree();
					ConstantInt constantInt = (ConstantInt)irTreeConst.getExpressionValue();
					enumNames.put(name, constantInt);
					initialValue = Integer.parseInt(constantInt.getApInt().toString());
				}
			}
			else{
				try {
					ConstantInt constantInt = ConstantInt.create(Type.getInt32Type(compilationContext, true), initialValue, true);
					enumNames.put(name, constantInt);
				} catch (InstantiationException e) {
					e.printStackTrace();
				}

			}

			enumList = enumList.getEnumList();
			initialValue++;
		}		

		// Add to types
		EnumSpecTypeEntry enumSpecTypeEntry = new EnumSpecTypeEntry(tag, enumNames, srcLocation);
		if(tag != null && !environments.getInstanceVariableTable().keyInCurrentScope(Symbol.symbol(tag)))
			environments.getInstanceTypeTable().put(Symbol.symbol(tag), enumSpecTypeEntry);	

		return enumSpecTypeEntry;
	}

	public void translateFunctionDef(FunctionDef functionDef){

		DeclarationSpecifiers declarationSpecifiers = functionDef.getDeclSpecifiers();
		TypeEntryWithAttributes  teWithAttributes = 
				getBaseEntryFromSpecsList(declarationSpecifiers, true, false, false);		

		// Now check the declarator
		Declarator declarator = functionDef.getDeclarator();
		TranslatedDeclarator translatedDeclarator = translateDeclarator(declarator, false, true, false);
		translatingMediator.resetTranslatorPropertiesForNewFunction();

		FunctionEntry funcTypeEntry = null;
		ParamTypeList paramTypeListToTranslate = null;
		Type returnType = null;
		boolean funcNameError = false;

		if(translatedDeclarator != null){
			// No error in declarator, continue
			SourceLocation location = new SourceLocation(functionDef.getLineNum(), functionDef.getPos());
			paramTypeListToTranslate = translatedDeclarator.getParamTypeList();
			currentFuncName = translatedDeclarator.getName();

			// Check the validity of the function name
			VariableOrFunctionEntry varOrFuncEntry = 
					(VariableOrFunctionEntry)environments.getInstanceVariableTable().get(Symbol.symbol(currentFuncName));

			if(definedFunctions.contains(currentFuncName)){ 
				// This function has already been defined
				errorHandler.addError(sourceFileName, location,  currentFuncName,
						ErrorHandler.AI_SEE_DECLARATION + sourceFileName + ":" + varOrFuncEntry.getSourceLocation(),
						ErrorHandler.E_FUNCTION_ALREADY_DEFINED);
				funcNameError = true;
			}
			else{				
				// If this name has already been declared, check if it is a variable or function 
				if(environments.getInstanceVariableTable().keyInCurrentScope(Symbol.symbol(currentFuncName))) {
					// name has already been declared, check if it is a variable or a function
					if(varOrFuncEntry.getCategory() == VariableOrFunctionEntry.VARIABLE){
						// Already declared as a variable, flag an error
						errorHandler.addError(sourceFileName, location,  currentFuncName, 
								ErrorHandler.AI_SEE_DECLARATION + sourceFileName + ":" + varOrFuncEntry.getSourceLocation(), 
								ErrorHandler.E_VARIABLE_ALREADY_DEFINED);
						funcNameError = true;
					}
					else{
						// Declared as a function already; check the signature
						FunctionEntry funcEntry = (FunctionEntry) varOrFuncEntry;
						if(!functionSignatureMatches(funcEntry, paramTypeListToTranslate)){
							errorHandler.addError(sourceFileName, location,currentFuncName,
									ErrorHandler.AI_SEE_DECLARATION + sourceFileName + ":" + varOrFuncEntry.getSourceLocation(),
									ErrorHandler.E_FUNCTION_SIGNATURE_MISMATCH);
							funcNameError = true;
						}
					}
				}
			}

			if(!funcNameError){
				if(!environments.getInstanceVariableTable().keyInCurrentScope(Symbol.symbol(currentFuncName))){
					funcTypeEntry =  (FunctionEntry)makeVariableOrFunctionEntry(translatedDeclarator, teWithAttributes,  location);
					addToEnvironment(funcTypeEntry);  // No error, make entry
				}
				// If the function is already been declared
				else{
					funcTypeEntry = (FunctionEntry) environments.getInstanceVariableTable().get(Symbol.symbol(currentFuncName));
				}
			}

			// if function is returning some thing
			if(funcTypeEntry != null){
				TypeEntryWithAttributes typeEntryWithAttributes = funcTypeEntry.getReturnType();
				boolean isUnSigned = typeEntryWithAttributes.isUnsigned();
				BasicType basicType = typeEntryWithAttributes.getBasicType();
				returnType = translatingMediator.getLLVMType(basicType, isUnSigned, compilationContext);
				returnValueTree = translatingMediator.createReturnValueTree(returnType);
			}

		}

		// Now check and translate the function body
		environments.getInstanceVariableTable().beginScope();
		environments.getInstanceTypeTable().beginScope();

		Vector<VariableEntry > paramEntries = null;
		if(paramTypeListToTranslate != null){
			// No error in declarator and there are parameters to analyze too
			paramEntries =  translateParamTypeList(paramTypeListToTranslate, true);			
		}

		if(funcTypeEntry != null)
			funcTypeEntry.setFormals(paramEntries);  // Set the list of variable entries from the param list

		boolean endsWithEllipses = false;

		if(funcTypeEntry != null)
			endsWithEllipses = funcTypeEntry.isEndsWithEllipses();


		CompoundStatement cmpStmt = functionDef.getCompoundStmt();
		IRTree paramsAsmt = translateParameterPassing(paramEntries);

		// Reset stack of Blocks and flag
		stackOfBlocks = new Stack<CompoundStatement>(); 
		isSameBlock = false;
		compoundStmtVsVariableLengthArrAlloca = new HashMap<CompoundStatement, IRTreeAlloca>();

		// Now the compound statement
		IRTree bodyAsmt = translateCompoundStatement(cmpStmt, true);

		if(!funcNameError)
			bodyAsmt = translatingMediator.createReturnStmt(returnValueTree, bodyAsmt, compilationContext);

		// Here we will create the Function Signature
		IRTree functionDefination = translatingMediator.createFunctionSignature(currentFuncName, paramsAsmt, returnType, endsWithEllipses, teWithAttributes.isStatic());

		// Create the assembly tree for the function
		Stack<IRTree> functionAssemTreeStack = new Stack<IRTree>();
		functionAssemTreeStack.push(functionDefination);

		translatingMediator.addReturnValueTreeToFunctionAssemStack(functionAssemTreeStack, returnValueTree, currentFuncName);

		if(paramsAsmt != null)
			functionAssemTreeStack.push(paramsAsmt);
		functionAssemTreeStack.push(bodyAsmt);

		IRTree functionAssemTree = translatingMediator.translateSeqStatement(functionAssemTreeStack);

		environments.getInstanceVariableTable().endScope();   // Close the scopes
		environments.getInstanceTypeTable().endScope();

		paramTypeListToTranslate = null; // Reset the param type list to translate

		if(funcTypeEntry != null)  // No error, add to the list of defined functions
			definedFunctions.add(funcTypeEntry.getName());  

		currentFunctionDef.setExternalDeclTree(functionAssemTree);
		currentFunctionDef.setName(currentFuncName);	
		currentFunctionDef.setMainFunction(currentFuncIsMain(teWithAttributes, paramEntries));
	}

	/**
	 * Returns true if the function being analyzed in the main function; false otherwise.
	 * @return
	 */
	private boolean currentFuncIsMain(TypeEntryWithAttributes teWithAttrs, 
			Vector<VariableEntry> paramEntries){
		if(currentFuncName == null)  // Must not happen
			return false;

		if(!currentFuncName.equals("main"))   // function name must be main
			return false;

		// The return must return an int or a void
		if(!(teWithAttrs.getBasicType() == IntTypeEntry.getInstance() || 
				teWithAttrs.getBasicType() == VoidTypeEntry.getInstance()))
			return false;

		if(paramEntries == null)
			paramEntries = new Vector<VariableEntry>();

		// The first parameter must be int and the second must be char * []
		if(paramEntries == null || (!(paramEntries.size() == 2 || paramEntries.size() == 0)))
			return false;

		if(paramEntries.size() == 0)
			return true;

		VariableEntry varEntry1 = paramEntries.elementAt(0);
		VariableEntry varEntry2 = paramEntries.elementAt(1);
		if(varEntry1.getType().getBasicType() != IntTypeEntry.getInstance()) // First param must be an integer			
			return false;
		// Second param must be char *[]
		BasicType secondParamType = varEntry2.getType().getBasicType();
		if(!(secondParamType instanceof ArrayTypeEntry))
			return false;
		ArrayTypeEntry secondParamArray = (ArrayTypeEntry) secondParamType;
		BasicType arrayElemType = secondParamArray.getBaseTypeEntry().getBasicType();
		if(!(arrayElemType instanceof PointerTypeEntry ))
			return false;

		BasicType pointerBaseType = ((PointerTypeEntry) arrayElemType).getBaseTypeEntry().getBasicType();
		if(pointerBaseType != CharTypeEntry.getInstance())
			return false;

		return true;

	}

	private Vector<VariableEntry> translateParamTypeList(ParamTypeList paramTypeList, 
			boolean isFunctionDefContext ){

		int paramNum = 0;

		Vector<VariableEntry> paramEntries = new Vector<VariableEntry>();
		ParamList paramList = paramTypeList.getParamList();

		Stack<VariableEntry> paramEntriesStack = new Stack<VariableEntry>();

		while(true){    // Iterate through the param list
			if(paramList == null) break;

			ParamDeclaration paramDecl = paramList.getParamDeclaration();
			DeclarationSpecifiers declSpecs = paramDecl.getDeclarationSpecifiers();
			
			if(declSpecs == null){
				errorHandler.addError(sourceFileName, new SourceLocation(paramDecl.getLineNum(), paramDecl.getPos()), paramDecl.getDeclarator().getDirectDeclarator().getId() + ":", "", ErrorHandler.DECLARATION_SPECIFIER_IS_MANDATORY);
				paramList = paramList.getParamList();
				continue;
			}
			
			Declarator declarator = paramDecl.getDeclarator();
			AbstractDeclarator abstractDeclarator = paramDecl.getAbstractDeclarator();

			TypeEntryWithAttributes  teWithAttributes = getBaseEntryFromSpecsList(declSpecs, false, false, false);
			if(declarator != null){
				TranslatedDeclarator translatedDeclarator = 
						translateDeclarator(declarator, true, false, false);
				if(translatedDeclarator == null){  // Error in declarator, move on to the next param
					paramList = paramList.getParamList(); 
					continue;
				}

				String name = translatedDeclarator.getName();

				VariableEntry newEntry = null;				
				SourceLocation location = new SourceLocation(paramTypeList.getLineNum(), paramTypeList.getPos());

				if(isFunctionDefContext){					
					newEntry = (VariableEntry) makeVariableOrFunctionEntry(translatedDeclarator, teWithAttributes, location);					
					if(errorByRepeating(name, location, newEntry, LOCAL_VARIABLE_CONTEXT, false)){
						paramList = paramList.getParamList();
						continue;
					}
					else 
						addToEnvironment(newEntry);  // No error, make entry

				}
				else{
					newEntry = (VariableEntry) makeVariableOrFunctionEntry(translatedDeclarator, teWithAttributes, location);
				}

				// If this is an array, check the declaration of the array 
				TypeEntryWithAttributes paramTe = newEntry.getType();
				if(paramTe.getBasicType() instanceof ArrayTypeEntry){
					arrayDeclaratorIsOk((ArrayTypeEntry) paramTe.getBasicType(), null, name, 
							Semantic.ARRAY_PARAM_ANALYSIS, location);
				}

				//enable the isParam flag and update the parameter number
				newEntry.setParam(true);
				newEntry.setParamNum(paramNum);

				Value value = createValue(newEntry.getName(), newEntry.getType(), false);
				newEntry.setValue(value);
				paramEntries.addElement(newEntry);
			}
			else{
				if((abstractDeclarator != null)){
					// TODO - Implement this later
				}
				if(teWithAttributes.getBasicType() != VoidTypeEntry.getInstance()){
					// It means only type has been specified and there is no identifier for it
					SourceLocation location = new SourceLocation(paramTypeList.getLineNum(), paramTypeList.getPos());
					VariableEntry newEntry = new VariableEntry(teWithAttributes, location, true, paramNum, null);	
					paramEntries.addElement(newEntry);
				}
			}

			paramNum++;

			paramList = paramList.getParamList();  // Get the next param list
		}

		// Place in correct order
		while(!paramEntriesStack.isEmpty()){
			VariableEntry newEntry = paramEntriesStack.pop();
			paramEntries.addElement(newEntry);
		}

		return paramEntries;
	}

	// STRUCTS TRANSLATION

	private StructOrUnionTypeEntry translateStructOrUnionSpecifier(StructOrUnionSpecifier structOrUnionSpecifier,
			boolean structDeclIsExternal, boolean isForSpecifierListInStructDecl){
		StructOrUnion structOrUnion = structOrUnionSpecifier.getStructOrUnion();
		boolean isStruct = true;
		if(structOrUnion.getType() == StructOrUnion.UNION)
			isStruct = false;

		StructDeclarationList structDeclarationList = structOrUnionSpecifier.getStructDecList();

		String tag = structOrUnionSpecifier.getName();

		LinkedHashMap<String, TypeEntryWithAttributes> namesAndTypes 
		= new LinkedHashMap<String, TypeEntryWithAttributes>();

		SourceLocation srcLocation = new SourceLocation(structOrUnion.getLineNum(), structOrUnion.getPos());

		if(structDeclarationList == null){
			// Check if the tag already exists
			StructOrUnionTypeEntry existingType = (StructOrUnionTypeEntry)environments.getInstanceTypeTable().get(Symbol.symbol(tag));
			if(existingType == null){
				if(tag != null){
					if(!isForSpecifierListInStructDecl)
						errorHandler.addError(sourceFileName, srcLocation, tag + ":", "", ErrorHandler.E_STRUCT_NOT_DEFINED);
				}
				StructOrUnionTypeEntry structOrUnionTypeEntry = new StructOrUnionTypeEntry(tag, isStruct, null, srcLocation);
				structOrUnionTypeEntry = updateTagNameVsStructOrUnionMap(structOrUnionTypeEntry, structDeclIsExternal);
				return structOrUnionTypeEntry;
			}
			else{
				return existingType;
			}
		}

		while(structDeclarationList != null){
			StructDeclaration structDeclaration = structDeclarationList.getStructDeclaration();

			SpecifierQualifierList specifierQualifierList = structDeclaration.getSpecifierQualifierList();
			TypeEntryWithAttributes teStructDeclBase =
					getBaseEntryFromSpecsList(specifierQualifierList, structDeclIsExternal, true, false);

			StructDeclaratorList structDeclaratorList = structDeclaration.getStructDeclaratorList();

			translateStructDeclaratorList(structDeclaratorList, teStructDeclBase, namesAndTypes,
					structDeclarationList.getStructDeclarationListNext() == null);

			structDeclarationList = structDeclarationList.getStructDeclarationListNext();	
		}		

		// If the struct does not have a tag name, create one
		if(tag == null){
			tag = getNameForTaglessStruct(10);
		}

		// Got all the members, create new struct type and create a type if necessary

		StructOrUnionTypeEntry structOrUnionTypeEntry = new StructOrUnionTypeEntry(tag,isStruct, 
				namesAndTypes, srcLocation);
		structOrUnionTypeEntry = updateTagNameVsStructOrUnionMap(structOrUnionTypeEntry, structDeclIsExternal);

		if(environments.getInstanceTypeTable().keyInCurrentScope(Symbol.symbol(tag))){
			// This name has been repeated
			Object object = environments.getInstanceTypeTable().get(Symbol.symbol(tag));
			if(object instanceof TypeEntry){
				TypeEntry oldEntry =  (TypeEntry) object;
				if(oldEntry instanceof StructOrUnionTypeEntry){
					errorHandler.addError(sourceFileName, srcLocation, tag,
							ErrorHandler.AI_SEE_DECLARATION + sourceFileName + ":" + oldEntry.getSourceLocation(),
							ErrorHandler.E_STRUCT_ALREADY_DEFINED);
				}
				else{
					return null;
				}
			}
			else if(object instanceof EnumSpecTypeEntry){
				EnumSpecTypeEntry enumSpecTypeEntry = (EnumSpecTypeEntry)object;
				errorHandler.addError(sourceFileName, srcLocation, enumSpecTypeEntry.getTag(),
						ErrorHandler.AI_SEE_DECLARATION + sourceFileName + ":" + enumSpecTypeEntry.getSourceLocation(),
						ErrorHandler.E_ENUM_ALREADY_DEFINED);
			}
		}
		else
			environments.getInstanceTypeTable().put(Symbol.symbol(tag), structOrUnionTypeEntry);

		return structOrUnionTypeEntry;

	}

	private StructOrUnionTypeEntry updateTagNameVsStructOrUnionMap(StructOrUnionTypeEntry structOrUnionTypeEntry, 
			boolean structDeclIsExternal) {
		String tag = structOrUnionTypeEntry.getTag();
		String key = null;

		if(structDeclIsExternal)
			key = "external_" + tag;
		else
			key = currentFuncName + "_" + tag;
		boolean flag;
		flag = key.equals("external_" + tag) && tagVsStructOrUnionTE.containsKey(currentFuncName + "_" + tag) ? true : false;
		flag = key.equals(currentFuncName + "_" + tag) && tagVsStructOrUnionTE.containsKey("external_" + tag) ? true : false;
		if(flag){
			int lastIndex = tag.lastIndexOf('.');
			if(lastIndex == -1)
				structOrUnionTypeEntry.setTag(tag + ".0");
			else{
				String count = tag.substring(lastIndex + 1);
				Integer i = Integer.parseInt(count) + 1;
				tag = tag.substring(0, lastIndex);
				structOrUnionTypeEntry.setTag(tag + "." + i);
			}
		}
		tagVsStructOrUnionTE.put(key, structOrUnionTypeEntry);
		return structOrUnionTypeEntry;
	}

	/**
	 * Generates a random name for a struct declared without a tag. The name is a unique random number.
	 * @param factor
	 * @return
	 */
	private String getNameForTaglessStruct(int factor){
		HashSet<String> existingKeys = new HashSet<String>();
		int attempt = (int) (factor * Math.random());
		String seed = "" + attempt;
		while(existingKeys.size() < factor){
			if(!environments.getInstanceTypeTable().keyInCurrentScope(Symbol.symbol(seed))){
				return seed;
			}
			else{
				attempt = (int) (factor * Math.random());
				seed = "" + attempt;
				existingKeys.add(seed);
			}
		}

		if(factor < 1000000){
			// TODO log a warning here
			return getNameForTaglessStruct(factor * 10);
		}
		else{
			return seed;
		}
	}

	private void translateStructDeclaratorList(StructDeclaratorList structDeclaratorList,
			TypeEntryWithAttributes baseEntry, LinkedHashMap<String, TypeEntryWithAttributes> namesAndTypes, 
			boolean isLastMemberList){

		List<TypeEntryWithAttributes> membersInDeclaratorList = new ArrayList<TypeEntryWithAttributes>();


		SourceLocation location = new SourceLocation(structDeclaratorList.getLineNum(), 
				structDeclaratorList.getPos());		

		while(true){
			if(structDeclaratorList == null)
				break;

			StructDeclarator structDeclarator = structDeclaratorList.getStructDeclarator();
			Declarator declarator = structDeclarator.getDeclarator();
			ConstExpr constExpr = structDeclarator.getBitFieldValue();

			if(constExpr != null && declarator == null){ // TODO Handle Bit Field for Structure
				/*	// This is a bitfield; check the type of the declarators found so far (available in 
				// membersInDeclaratorList) against the bit-field
				TypeEntryWithAttributes teConstExpr = translateConditionalExpr(constExpr.getCondExpr()).getTypeEntry();

				BasicType valBasicType = teConstExpr.getBasicType();
				if((valBasicType == IntTypeEntry.getInstance() || valBasicType == ShortTypeEntry.getInstance()
					|| valBasicType == CharTypeEntry.getInstance() || valBasicType == LongTypeEntry.getInstance())){
					if(teConstExpr.getLiteralValue() == null){ // Is an expression
						errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_BITFIELD_NOT_CONSTANT);
					}
				}
				else{
					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_BITFIELD_TYPE_IS_INVALID);
				}		

				structDeclaratorList = structDeclaratorList.getStructDeclaratorListNext(); 
				continue;
				 */
			}

			TranslatedDeclarator translatedDeclarator = translateDeclarator(declarator, false, false, true);
			if(translatedDeclarator == null){ // Error, move on to the next declarator
				structDeclaratorList = structDeclaratorList.getStructDeclaratorListNext(); 
				continue;
			}

			VariableOrFunctionEntry varOrFuncEntry = getVarOrFunctionEntryFromTranslatedDeclarator(translatedDeclarator, location);
			String name = translatedDeclarator.getName();

			boolean memberError = false;
			if(namesAndTypes.containsKey(name)){
				errorHandler.addError(sourceFileName, location, name + ":", "", ErrorHandler.E_DUPLICATE_MEMBER_NAME);				
				memberError = true;
			}

			if(varOrFuncEntry instanceof FunctionEntry){
				errorHandler.addError(sourceFileName, location, name + ":", "", ErrorHandler.E_FUNCTION_AS_STRUCT_MEMBER);				
				memberError = true;
				return;
			}

			VariableEntry memberEntry = (VariableEntry) varOrFuncEntry;
			baseEntry.setIsLValue(true);			
			TypeEntryWithAttributes teType = memberEntry.getType();
			// Update the basic type of the variable entry from the type entry with attributes object passed			
			if(teType == null)
				memberEntry.setType(baseEntry);
			else
				TypeEntryWithAttributes.updateBasicType(teType, baseEntry);


			BasicType btOfMember = teType.getBasicType(); 
			if(btOfMember instanceof StructOrUnionTypeEntry){
				StructOrUnionTypeEntry btStructOrUnionType = (StructOrUnionTypeEntry)btOfMember;
				if(environments.getInstanceTypeTable().get(Symbol.symbol(btStructOrUnionType.getTag())) == null){
					errorHandler.addError(sourceFileName, location, name + ":", "", ErrorHandler.E_STRUCT_MEMBER_HAS_UNKNOWN_TYPE);				
					memberError = true;
				}
			}

			if(memberError){
				structDeclaratorList = structDeclaratorList.getStructDeclaratorListNext(); 
				continue;
			}

			if(btOfMember instanceof ArrayTypeEntry){  // Check if the array declarator is ok
				ArrayTypeEntry arrayType = (ArrayTypeEntry) btOfMember;
				int numDim = 0;
				TypeEntryWithAttributes teFirstDim = arrayType.getDimension();

				// If this is a multidimensional array, make sure except the first row, the other 
				// dimensions must be specified
				boolean baseTypeDimtSpecified = true;
				while(true){
					numDim++;
					TypeEntryWithAttributes baseTeWithAttributes = arrayType.getBaseTypeEntry();
					if(baseTeWithAttributes.getBasicType() instanceof ArrayTypeEntry){
						arrayType =  (ArrayTypeEntry)baseTeWithAttributes.getBasicType();

						if(arrayType.getDimension() == null){  // No 
							baseTypeDimtSpecified = false;
							break;							
						}				
					}
					else
						break;
				}

				// If the size of the first dimension is not specified, and this is NOT the last
				// member, flag an error
				if(teFirstDim == null){
					if(!(isLastMemberList && structDeclaratorList.getStructDeclaratorListNext() == null)) // Not the last member
						errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_ARRAY_SIZE_NOT_SPECIFIED);
				}

				// If this is a multidimensional array and the size of ANY of the inner dimensions are not specified,
				// flag an error
				if(numDim == 1){					
					if(!baseTypeDimtSpecified) // Not the last member
						errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_ARRAY_SIZE_NOT_SPECIFIED);
				}

			}	

			namesAndTypes.put(name, teType);

			membersInDeclaratorList.add(teType);

			structDeclaratorList = structDeclaratorList.getStructDeclaratorListNext();  // Get next
		}
	}

	private Translated translateStructInitializerList(TypeEntryWithAttributes tpBaseType,
			InitializerList initializerList, SourceLocation location, String lhsName, boolean isExternalDecl){

		Translated retValue = new Translated();

		int count = 0;
		LinkedHashMap<String, TypeEntryWithAttributes> membersAndTypes 
		= new LinkedHashMap<String, TypeEntryWithAttributes>();

		BasicType bt = tpBaseType.getBasicType();
		if(!(bt instanceof StructOrUnionTypeEntry)){
			errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_EXCESS_INITIALIZERS_IN_STRUCT_INIT);
			return null;
		}

		StructOrUnionTypeEntry structOrUnionTypeEntry = (StructOrUnionTypeEntry) bt;
		int numMembers = structOrUnionTypeEntry.getNumMembers();
		if(numMembers <= 0){
			if(structOrUnionTypeEntry.isStruct())
				errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_EXCESS_INITIALIZERS_IN_STRUCT_INIT);
			else
				errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_EXCESS_INITIALIZERS_IN_UNION_INIT);
			return null;
		}

		Vector<Initializer> allInitializers = new Vector<Initializer>();
		boolean initializerHasAllScalarElements = true;
		Vector<Translated> values = new Vector<Translated>();

		while(initializerList != null){
			Initializer initializer = initializerList.getInitializer();
			if(initializer != null){
				allInitializers.add(initializer);
				initializerList = initializerList.getInitializerList();
			}
			AssignmentExpr assignmentExpr = initializer.getInitExpr();
			if(assignmentExpr != null){
				Translated translated = translateAssignmentExpr(assignmentExpr);

				if(translated.getTypeEntry().getBasicType() != null && (translated.getTypeEntry().getBasicType() instanceof StructOrUnionTypeEntry))
					initializerHasAllScalarElements = false;

				translated.setSourceLocation(new SourceLocation(assignmentExpr.getLineNum(),
						assignmentExpr.getPos()));
				values.addElement(translated);
			}
			else{
				initializerHasAllScalarElements = false;
			}
		}

		if(allInitializers.size() == 0){
			if(structOrUnionTypeEntry.isStruct())
				errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_EXCESS_INITIALIZERS_IN_STRUCT_INIT);
			else
				errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_EXCESS_INITIALIZERS_IN_UNION_INIT);
			return null;
		}

		// Iterate through the member list and for each member, check against the type of the
		// corresponding initializer
		Vector<String> memberNames = structOrUnionTypeEntry.getMemberNames();

		boolean isStruct = structOrUnionTypeEntry.isStruct();
		// If this is a union, make sure there is no more than one initializer
		if(!isStruct){  // This is a union
			if(memberNames.size() > 0 && allInitializers.size() > 1){					
				errorHandler.addError(sourceFileName, location, 
						"" , "", ErrorHandler.E_EXCESS_INITIALIZERS_IN_UNION_INIT);	
			}
		}

		List<IRTree> listOfMemberIRTree = new ArrayList<IRTree>();
		List<Type> listOfTypes = new ArrayList<Type>();
		boolean areAllMembersConstant = true;
		TypeEntryWithAttributes firstMemberType = structOrUnionTypeEntry.getMemberType(memberNames.elementAt(0));

		for(String name: memberNames){
			TypeEntryWithAttributes memberType = structOrUnionTypeEntry.getMemberType(name);

			if(count < allInitializers.size() && (!structOrUnionTypeEntry.isStruct() ? (count == 0 ? true : false) : true)){
				BasicType btMember = memberType.getBasicType();
				Initializer initializer = allInitializers.elementAt(count);
				IRTree irTree = null;
				if(btMember instanceof ArrayTypeEntry){
					// Member is an array, check the initializer
					AssignmentExpr assignmentExpr = initializer.getInitExpr();
					if(assignmentExpr != null){
						// Initializer is not an array initializer; flag an error
						errorHandler.addError(sourceFileName, location, 
								name + ": " , "", ErrorHandler.E_INVALID_INITIALIZATION_OF_MEMBER);						
					}
					else{
						// Initializer is an array initializer; translate it
						ArrayTypeEntry btLhsArr = (ArrayTypeEntry) btMember;

						int maxSize = -1;  // No size specified in declaration
						TypeEntryWithAttributes teDim = btLhsArr.getDimension();
						if(teDim != null){
							try{
								maxSize = Integer.parseInt(teDim.getLiteralValue());
							}
							catch(NumberFormatException nfe){}
						}

						InitializerList list = initializer.getInitializerList();
						Translated translated = 
								translateArrayInitializerList(memberType, maxSize, list, location, 0, lhsName, isExternalDecl);
						if(translated != null){

							// For Union check if the initializer type is compatible with the union type
							if(!structOrUnionTypeEntry.isStruct()){
								checkAssignmentCompatibility(firstMemberType, translated.getTypeEntry(), location,
										ASSIGNMENT_IN_STRUCT_MEMBER_INIT, AssignmentOperator.ASSIGN, null, -1);
							}
							else{
								checkAssignmentCompatibility(memberType, translated.getTypeEntry(), location,
										ASSIGNMENT_IN_STRUCT_MEMBER_INIT, AssignmentOperator.ASSIGN, null, -1);
							}

							irTree = translated.getIRTree();

							areAllMembersConstant = chkIfIRTreeIsAConstant(irTree);

							Type type = translatingMediator.getLLVMType(translated.getTypeEntry().getBasicType(), false, compilationContext);
							listOfTypes.add(type);
							listOfMemberIRTree.add(irTree);
							listOfMemberIRTree.removeAll(Collections.singleton(null));
							membersAndTypes.put(name, translated.getTypeEntry());
						}						
					}
				}
				else if(btMember instanceof StructOrUnionTypeEntry){
					// Member is a structure, check the initializer		
					StructOrUnionTypeEntry strOrUnionTypeEntry = (StructOrUnionTypeEntry) btMember;
					AssignmentExpr assignmentExpr = initializer.getInitExpr();
					if(assignmentExpr != null){
						Translated translated = translateAssignmentExpr(assignmentExpr);
						values.addElement(translated);

						// For Union check if the initializer type is compatible with the union type
						if(!structOrUnionTypeEntry.isStruct()){
							checkAssignmentCompatibility(firstMemberType, translated.getTypeEntry(), location,
									ASSIGNMENT_IN_STRUCT_MEMBER_INIT, AssignmentOperator.ASSIGN, null, -1);
						}

						if(translated.getTypeEntry().getBasicType() != strOrUnionTypeEntry)
							errorHandler.addError(sourceFileName, location, 
									name + ": " , "", ErrorHandler.E_INVALID_INITIALIZATION_OF_MEMBER);	

						if(translated != null){
							irTree = translated.getIRTree();

							areAllMembersConstant = chkIfIRTreeIsAConstant(irTree);

							Type type = translatingMediator.getLLVMType(translated.getTypeEntry().getBasicType(), false, compilationContext);
							listOfTypes.add(type);
							listOfMemberIRTree.add(irTree);
						}
					}
					else{
						InitializerList list = initializer.getInitializerList();
						Translated translated = 
								translateStructInitializerList(memberType, list,location, lhsName, isExternalDecl);
						if(translated != null){
							// For Union check if the initializer type is compatible with the union type
							if(!structOrUnionTypeEntry.isStruct()){
								checkAssignmentCompatibility(firstMemberType, translated.getTypeEntry(), location,
										ASSIGNMENT_IN_STRUCT_MEMBER_INIT, AssignmentOperator.ASSIGN, null, -1);
							}
							else{
								checkAssignmentCompatibility(memberType, translated.getTypeEntry(), location,
										ASSIGNMENT_IN_STRUCT_MEMBER_INIT, AssignmentOperator.ASSIGN, null, -1);
							}

							irTree = translated.getIRTree();

							areAllMembersConstant = chkIfIRTreeIsAConstant(irTree);

							Type type = translatingMediator.getLLVMType(translated.getTypeEntry().getBasicType(), false, compilationContext);
							listOfTypes.add(type);
							listOfMemberIRTree.add(irTree);
							membersAndTypes.put(name, translated.getTypeEntry());
						}	
					}
				}
				else{					
					// Member is neither an array nor a structure, check the initializer
					AssignmentExpr assignmentExpr = initializer.getInitExpr();
					if(assignmentExpr != null){
						Translated translated = translateAssignmentExpr(assignmentExpr);						
						if(translated != null){

							// For Union check if the initializer type is compatible with the union type
							if(!structOrUnionTypeEntry.isStruct()){
								checkAssignmentCompatibility(firstMemberType, translated.getTypeEntry(), location,
										ASSIGNMENT_IN_STRUCT_MEMBER_INIT, AssignmentOperator.ASSIGN, null, -1);
							}
							else{
								checkAssignmentCompatibility(memberType, translated.getTypeEntry(), location,
										ASSIGNMENT_IN_STRUCT_MEMBER_INIT, AssignmentOperator.ASSIGN, null, -1);
							}

							irTree = translated.getIRTree();

							areAllMembersConstant = chkIfIRTreeIsAConstant(irTree);

							Type type = translatingMediator.getLLVMType(memberType.getBasicType(), false, compilationContext);
							listOfTypes.add(type);
							listOfMemberIRTree.add(irTree);
							membersAndTypes.put(name, translated.getTypeEntry());
						}
					}
					else{
						// Assume that this is an array and flag an error
						if(!isStruct)
							errorHandler.addError(sourceFileName, location, 
									name + ": " , "", ErrorHandler.E_EXCESS_INITIALIZERS_IN_UNION_INIT);	
						else
							errorHandler.addError(sourceFileName, location, 
									name + ": " , "", ErrorHandler.E_EXCESS_INITIALIZERS_IN_STRUCT_INIT);	

					}
				}
			}			
			count++;
		}

		if(!areAllMembersConstant){
			Stack<IRTree> irTreeStack = new Stack<IRTree>();
			Type type = translatingMediator.getLLVMType(structOrUnionTypeEntry, false, compilationContext);
			PointerType pointerType = null;
			try {
				pointerType = Type.getPointerType(compilationContext, type, 0);
			} catch (TypeCreationException e) {
				e.printStackTrace();
				System.exit(-1);
			}
			Value memory = new Value(pointerType);
			memory.setName(lhsName); 
			IRTreeMemory memoryTree = new IRTreeMemory(memory);
			IRTreeDeclaration irTreeDeclaration = new IRTreeDeclaration(memoryTree, null, false, true, false, null);
			irTreeStack.push(irTreeDeclaration);
			translatingMediator.translateInitializationOfComplexTypes(listOfMemberIRTree, compilationContext, irTreeStack, listOfTypes, pointerType, memoryTree);

			retValue.setIRTree(translatingMediator.translateSeqStatement(irTreeStack));
			TypeEntryWithAttributes teRet = new TypeEntryWithAttributes();		
			teRet.setBasicType(structOrUnionTypeEntry);
			teRet.setConst(false);
			retValue.setEntry(teRet);
			return retValue;
		}

		if(initializerHasAllScalarElements){
			Translated teExpr = 
					translateCompoundInitializerWithAllScalarElements(tpBaseType, values, location, lhsName, isExternalDecl);

			if(teExpr.isExcessInitializersInStructnit()){
				if(structOrUnionTypeEntry.isStruct())
					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_EXCESS_INITIALIZERS_IN_STRUCT_INIT);
			}

			return teExpr;
		}

		IRTreeStatementList irTreeStatementList = translatingMediator.createConstantStruct(listOfMemberIRTree, structOrUnionTypeEntry, compilationContext);


		if(!isExternalDecl){
			IRTreeExpressionStm expressionStm = (IRTreeExpressionStm) translatingMediator.getLeafStatementList(irTreeStatementList).getStatement();
			IRTreeConst irTreeConst = (IRTreeConst)expressionStm.getExpressionTree();
			ConstantStruct constantStruct = (ConstantStruct) irTreeConst.getExpressionValue();
			PointerType pointerType = null;
			try {
				pointerType = Type.getPointerType(compilationContext, constantStruct.getType(), 0);
			} catch (TypeCreationException e) {
				e.printStackTrace();
				System.exit(-1);
			}
			Value memory = new Value(pointerType);
			memory.setName(currentFuncName + "." + lhsName); 
			IRTreeMemory memoryTree = new IRTreeMemory(memory);
			IRTreeDeclaration irTreeDeclaration = new IRTreeDeclaration(memoryTree, irTreeConst, true, true, false, LinkageTypes.InternalLinkage);
			if(irTreeStatementList == null)
				irTreeStatementList = new IRTreeStatementList(irTreeDeclaration, null);
			else
				translatingMediator.getLeafStatementList(irTreeStatementList).setStatementList(new IRTreeStatementList(irTreeDeclaration, null));
		}

		retValue.setIRTree(irTreeStatementList);
		// If any initializers are left out and the structure is a struct (not a union), they are extra.
		if(isStruct && count < allInitializers.size()){
			errorHandler.addError(sourceFileName, location, "" , "", 
					ErrorHandler.E_EXCESS_INITIALIZERS_IN_STRUCT_INIT);	
		}

		// Create and return a struct type		
		StructOrUnionTypeEntry structOrUnitTeFromInit = 
				new StructOrUnionTypeEntry(structOrUnionTypeEntry.getTag(), structOrUnionTypeEntry.isStruct(),
						membersAndTypes, location);
		structOrUnitTeFromInit = updateTagNameVsStructOrUnionMap(structOrUnitTeFromInit, isExternalDecl);

		TypeEntryWithAttributes teRet = new TypeEntryWithAttributes();		
		teRet.setBasicType(structOrUnitTeFromInit);
		teRet.setConst(true);
		retValue.setEntry(teRet);


		return retValue;

	}	

	private boolean chkIfIRTreeIsAConstant(IRTree irTree) {
		boolean areAllMembersConstant = true;
		if(irTree instanceof IRTreeStatementList){
			IRTreeStatementList irTreeStatementList = (IRTreeStatementList)irTree;
			IRTreeStatement irTreeStatement = translatingMediator.getLeafStatementList(irTreeStatementList).getStatement();
			if(irTreeStatement instanceof IRTreeExpressionStm){
				IRTreeExpressionStm expressionStm = (IRTreeExpressionStm)irTreeStatement;
				IRTreeExp irTreeExp = expressionStm.getExpressionTree();
				if(!(irTreeExp instanceof IRTreeConst))
					areAllMembersConstant = false;
			}
			else if(irTreeStatement instanceof IRTreeDeclaration){
				IRTreeDeclaration irTreeDeclaration = (IRTreeDeclaration)irTreeStatement;
				IRTreeExp irTreeExp = irTreeDeclaration.getInitializerExpression();
				if(!(irTreeExp instanceof IRTreeConst))
					areAllMembersConstant = false;
			}
			else
				areAllMembersConstant = false;
		}
		else if(irTree instanceof IRTreeDeclaration){
			IRTreeDeclaration irTreeDeclaration = (IRTreeDeclaration)irTree;
			IRTreeExp irTreeExp = irTreeDeclaration.getInitializerExpression();
			if(irTreeExp == null)
				areAllMembersConstant = false;
			else if(!(irTreeExp instanceof IRTreeConst))
				areAllMembersConstant = false;
		}
		else if(!(irTree instanceof IRTreeConst))
			areAllMembersConstant = false;

		return areAllMembersConstant;
	}

	// THE STATEMENTS
	private IRTree translateStatement(Statement statement){	

		if(statement instanceof CompoundStatement) {
			CompoundStatement compoundStatement = (CompoundStatement) statement;

			return translateCompoundStatement(compoundStatement, false);
		}
		else if(statement instanceof ExprStatement){
			Stack<IRTree> exprStmtStack = new Stack<IRTree>();
			ExprStatement exprStatement = (ExprStatement) statement;
			RootExpr expr = exprStatement.getExpr();	
			if(expr != null){
				Translated teExpr = translateExpr(expr);
				IRTree exprTree = teExpr.getIRTree();
				if(exprTree != null){
					exprStmtStack.push(exprTree);
				}				
			}

			if(!exprStmtStack.isEmpty()){
				IRTree stmtListTree = translatingMediator.translateSeqStatement(exprStmtStack);
				return stmtListTree;
			}

			return null;
		}
		else if(statement instanceof IterationStatement){
			IterationStatement iterationStatement = (IterationStatement) statement;
			return translateIterationStatement(iterationStatement);				
		}
		else if(statement instanceof SelectionStatement){
			SelectionStatement selectionStatement = (SelectionStatement) statement;
			return translateSelectionStatement(selectionStatement);		
		} 
		else if(statement instanceof JumpStatement){
			JumpStatement jumpStatement = (JumpStatement) statement;
			//TODO Implement below statement and un-comment and comment "return null";
			return translateJumpStatement(jumpStatement);
		}
		else if(statement instanceof IncludeStatement){
			IncludeStatement includeStatement = (IncludeStatement) statement;
			//TODO Implement below statement and un-comment and comment "return null";
			return translateIncludeStatement(includeStatement);
		}
		else {   // Must be labeled statement
			LabeledStatement labeledStatement = (LabeledStatement) statement;
			return translateLabeledStatement(labeledStatement);
		}
	}

	private IRTree translateIncludeStatement(IncludeStatement includeStatement){
		// TODO : Implement this: return null for now
		return null;
	}

	private IRTree translateCompoundStatement(CompoundStatement compoundStatement, boolean isFunctionDefContext){
		if(!isFunctionDefContext)
			environments.getInstanceVariableTable().beginScope();
		environments.getInstanceTypeTable().beginScope();



		stackOfBlocks.push(compoundStatement);
		isSameBlock = false;

		BlockItemList blockItemList = compoundStatement.getBlockItemList();

		Stack<IRTree> treeElementStack = new Stack<IRTree>();

		if(blockItemList != null){
			while(blockItemList != null){
				BlockItem blockItem = blockItemList.getBlockItem();
				if(blockItem != null){
					Declaration declaration = blockItem.getDeclaration();
					if(declaration != null){
						IRTree declTree = translateDeclaration(declaration, false);
						if(!translatingMediator.postfixExprStack.empty())
							translatingMediator.postfixExprStack.pop();

						if(declTree != null)
							treeElementStack.push(declTree);
					}
					Statement statement = blockItem.getStatement();
					if(statement != null){
						IRTree irTreeStmt = translateStatement(statement);
						if(!translatingMediator.postfixExprStack.empty())
							translatingMediator.postfixExprStack.pop();

						if(irTreeStmt != null)
							treeElementStack.push(irTreeStmt);
					}
				}
				blockItemList = blockItemList.getBlockItemList();
			}
		}

		CompoundStatement compoundStatement2 = stackOfBlocks.pop();

		if(!stackOfBlocks.empty())
			isSameBlock = true;

		// Call llvm.stackrestore() if required
		if(!variableLengthArrayAllocas.empty() && variableLengthArrayAllocas.peek() == compoundStmtVsVariableLengthArrAlloca.get(compoundStatement2)){
			String funcName = "llvm.stackrestore";
			translatingMediator.callIntrinsicLLVMStackRestoreOrStackSave(funcName, variableLengthArrayAllocas, treeElementStack, compilationContext);
		}

		// Create the tree for the compound statement.
		IRTree seqStmtTree = translatingMediator.translateSeqStatement(treeElementStack);

		if(!isFunctionDefContext){
			environments.getInstanceVariableTable().endScope();
			environments.getInstanceTypeTable().endScope();
		}

		return seqStmtTree;
	}

	private IRTree translateParameterPassing(Vector<VariableEntry> paramEntries){

		if(paramEntries == null || paramEntries.size() == 0)
			return null;

		Stack<IRTree> treeElementStack = new Stack<IRTree>();

		for(VariableEntry paramEntry: paramEntries){
			Value argValue = null;
			argValue = createValue(paramEntry.getName(), paramEntry.getType(), false);
			if(argValue != null){
				argValue.setValueTypeID(ValueTypeID.ARGUMENT);
				IRTree argTree = translatingMediator.translateFunctionArgument(argValue, compilationContext);
				if(argTree != null)
					treeElementStack.push(argTree);
			}
			paramEntry.setValue(argValue);
		}

		// Create the tree for the compound statement.
		IRTree seqStmtTree = translatingMediator.translateSeqStatement(treeElementStack);

		return seqStmtTree;
	}

	private IRTree translateIterationStatement(IterationStatement iterationStatement){

		IRTree finalStmtTree = null;

		Label iterStartLabel = new Label();
		Label iterEndLabel = new Label();


		IterationOrSelectionLabels iterOrSelectionLabels = null;

		int iterType = iterationStatement.getIterationType();
		SourceLocation location = new SourceLocation(iterationStatement.getLineNum(), iterationStatement.getPos());
		if(iterType == IterationStatement.FOR){

			Label incrLabel = new Label();

			RootExpr forCondExpr = iterationStatement.getForCondExpr();
			RootExpr forInitExpr = iterationStatement.getForInitExpr();
			RootExpr forIncrExpr = iterationStatement.getForIncrExpr();

			Declaration forDeclr = iterationStatement.getForDeclr();

			if(forIncrExpr != null){
				iterOrSelectionLabels = new IterationOrSelectionLabels(IterationOrSelectionLabels.FOR_ITERATION, iterStartLabel, iterEndLabel, incrLabel);
			}
			else{
				iterOrSelectionLabels = new IterationOrSelectionLabels(IterationOrSelectionLabels.FOR_ITERATION, iterStartLabel, iterEndLabel, null);
			}
			iterOrSwitchStack.push(iterOrSelectionLabels);

			IRTree condExprTree = null;
			IRTree initExprTree = null;
			IRTree incrExprTree = null;
			IRTree forStmtTree = null;
			IRTree forDeclrTree = null;

			if(forInitExpr != null){
				Translated teForInitExpr = translateExpr(forInitExpr);
				initExprTree = teForInitExpr.getIRTree();
			}

			if(forDeclr != null){
				forDeclrTree = translateDeclaration(forDeclr, false);
			}

			if(forCondExpr != null){
				Translated teForCondExpr = translateExpr(forCondExpr);
				if(teForCondExpr.getTypeEntry().getBasicType() == VoidTypeEntry.getInstance()){
					errorHandler.addError(sourceFileName, location, "", "",  ErrorHandler.E_VOID_TYPE_IN_LOOP_CONDITION);
				}
				else{
					condExprTree = teForCondExpr.getIRTree();
				}
			}

			if(forIncrExpr != null){
				Translated teForIncrExpr = translateExpr(forIncrExpr);
				incrExprTree = teForIncrExpr.getIRTree();
			}

			Statement forStmt = iterationStatement.getForStmt();
			if(forStmt != null){
				forStmtTree = translateStatement(forStmt);
			}

			// Create the assembly tree and return it
			if(!errorHandler.isFoundStrictError())
				finalStmtTree = translatingMediator.translateForStmt(condExprTree, initExprTree,incrExprTree, forStmtTree, compilationContext, iterStartLabel, iterEndLabel, incrLabel, returnValueTree, forDeclrTree);			
		}
		else if(iterType == IterationStatement.WHILE || iterType == IterationStatement.DO_WHILE){

			LOG.debug(currentFuncName);

			iterOrSelectionLabels = new IterationOrSelectionLabels(IterationOrSelectionLabels.ITERATION, iterStartLabel, iterEndLabel, null);
			iterOrSwitchStack.push(iterOrSelectionLabels);

			RootExpr whileExpr = iterationStatement.getWhileExpr();
			Statement whileStmt = iterationStatement.getWhileStmt();

			Translated teWhileCondExpr = translateExpr(whileExpr);
			IRTree whileBodyTree = null;
			if(teWhileCondExpr.getTypeEntry().getBasicType() == VoidTypeEntry.getInstance()){
				errorHandler.addError(sourceFileName, location, "", "",  ErrorHandler.E_VOID_TYPE_IN_LOOP_CONDITION);
				return null;  // Error
			}
			if(whileStmt != null){
				whileBodyTree = translateStatement(whileStmt);
			}		

			// No error, translate to intermediate tree representation			
			if(!errorHandler.isFoundStrictError()){
				IRTree whileTestTree = teWhileCondExpr.getIRTree();
				if(iterType == IterationStatement.WHILE)   // This is a while statement
					finalStmtTree = translatingMediator.translateWhileExp(whileTestTree, 
							whileBodyTree, false, compilationContext, iterStartLabel, iterEndLabel);
				else                                       // This is a do-while statement
					finalStmtTree = translatingMediator.translateWhileExp(whileTestTree, 
							whileBodyTree, true, compilationContext, iterStartLabel, iterEndLabel);
			}						
		}

		// Pop the iteration stack
		if(!iterOrSwitchStack.isEmpty())
			iterOrSwitchStack.pop();

		return finalStmtTree;		
	}


	private IRTree translateSelectionStatement(SelectionStatement selectionStatement){

		IRTree selectionTree = null;

		Statement ifStmt = selectionStatement.getIfStmt();

		if(ifStmt != null){   // Must be a if-else statement
			RootExpr ifExpr = selectionStatement.getIfExpr();	
			Translated teRootExpr = translateExpr(ifExpr);

			IRTree ifExprTree = teRootExpr.getIRTree();

			IRTree ifStmtTree = translateStatement(ifStmt);

			IRTree elseStmtTree = null;
			Statement elseStmt = selectionStatement.getElseStmt();
			if(elseStmt != null)
				elseStmtTree = translateStatement(elseStmt);

			if(!errorHandler.isFoundStrictError()){		
				//TODO: Vikash
				selectionTree = translatingMediator.translateIfStm(ifExprTree, ifStmtTree, elseStmtTree, compilationContext, returnValueTree);
			}			
		}
		else {    // Must be a switch statement			
			Label switchEndLabel = new Label();	
			iterOrSwitchStack.push(new IterationOrSelectionLabels(IterationOrSelectionLabels.SWITCH,
					null, switchEndLabel, null));

			RootExpr switchExpr = selectionStatement.getSwitchExpr();
			Statement switchStmt = selectionStatement.getSwitchStmt();

			// The switch expr must evaluate to a constant expression
			Translated exprExpAndType = translateExpr(switchExpr);
			TypeEntryWithAttributes tpWithAttrs = exprExpAndType.getTypeEntry();
			IRTree switchExprTree = exprExpAndType.getIRTree();

			BasicType tp = tpWithAttrs.getBasicType();
			if(!(tp == IntTypeEntry.getInstance() || tp == CharTypeEntry.getInstance() ||
					tp == ShortTypeEntry.getInstance() || tp == LongTypeEntry.getInstance())){
				SourceLocation location = new SourceLocation(switchExpr.getLineNum(), switchExpr.getPos());
				errorHandler.addError(sourceFileName, location, "", "",  ErrorHandler.E_SWITCH_EXPR_MUST_BE_CONST);				
			}

			IRTree switchBodyTree = translateStatement(switchStmt);

			if(!errorHandler.isFoundStrictError()){
				selectionTree = translatingMediator.translateSwitchStm(switchExprTree,
						switchBodyTree, switchEndLabel);
			}

			if(!iterOrSwitchStack.isEmpty())
				iterOrSwitchStack.pop();
		}

		return selectionTree;
	}

	private IRTree translateJumpStatement(JumpStatement jumpStatement){
		int jmpStmtType = jumpStatement.getType();
		SourceLocation location = new SourceLocation(jumpStatement.getLineNum(), jumpStatement.getPos());

		if(jmpStmtType == JumpStatement.BREAK){
			// Make sure this is inside a loop or in a switch/case
			if(iterOrSwitchStack.isEmpty()){
				errorHandler.addError(sourceFileName, location, "", "",  ErrorHandler.E_BREAK_IN_WRONG_LOCATION);
			}
			else{
				IterationOrSelectionLabels iterationOrSelectionLabels = iterOrSwitchStack.pop();
				Label endLabel = iterationOrSelectionLabels.getEndLabel();
				IRTree jumpTree = translatingMediator.translateJump(jmpStmtType, endLabel);
				iterOrSwitchStack.push(iterationOrSelectionLabels);
				return jumpTree;
			}
		}
		else if(jmpStmtType == JumpStatement.CONTINUE){
			if(iterOrSwitchStack.isEmpty()){
				errorHandler.addError(sourceFileName, location, "", "",  ErrorHandler.E_CONTINUE_IN_WRONG_LOCATION);
			}
			else{
				IterationOrSelectionLabels iterationOrSelectionLabels = iterOrSwitchStack.pop();
				Label continueLabel = null;

				if(iterationOrSelectionLabels.getType() == IterationOrSelectionLabels.FOR_ITERATION && iterationOrSelectionLabels.getIncrLabel() != null)
					continueLabel = iterationOrSelectionLabels.getIncrLabel();
				else
					continueLabel = iterationOrSelectionLabels.getStartLabel();

				IRTree jumpTree = translatingMediator.translateJump(jmpStmtType, continueLabel);
				iterOrSwitchStack.push(iterationOrSelectionLabels);
				return jumpTree;
			}
		}
		else { // Must be a return statement  
			RootExpr retExpr = jumpStatement.getReturnExpr();
			TypeEntryWithAttributes expectedTeWithAttrs = null;

			// Get the expected return type of the function
			IRTree returnExprTree = null;
			TypeEntryWithAttributes teWithAttrsRetExpr = null;
			if(currentFuncName != null){  
				// Current function name has been determined; it could be null if the declarator in the 
				// function definition has an error
				FunctionEntry funcEntry = 
						(FunctionEntry)environments.getInstanceVariableTable().get(Symbol.symbol(currentFuncName));

				expectedTeWithAttrs = funcEntry.getReturnType();
				Translated teRetExpr = translateExpr(retExpr);
				teWithAttrsRetExpr = teRetExpr.getTypeEntry();
				returnExprTree = teRetExpr.getIRTree();

				// If the return statement doesn't return anything
				if(returnExprTree == null){
					returnExprTree = new IRTreeMemory(null);

					teWithAttrsRetExpr = new TypeEntryWithAttributes();
					teWithAttrsRetExpr.setBasicType(VoidTypeEntry.getInstance());
				}

				if(retExpr != null){
					location = new SourceLocation(retExpr.getLineNum(), retExpr.getPos());

					if(expectedTeWithAttrs.getBasicType() == VoidTypeEntry.getInstance()){
						// A void function type is returning a value, flag a warning/error
						errorHandler.addError(sourceFileName, location, "", "",  ErrorHandler.E_RETURN_VALUE_IN_FUNC_RETURNING_VOID);
					}
					else{						
						checkAssignmentCompatibility(expectedTeWithAttrs, teRetExpr.getTypeEntry(), location, 
								Semantic.ASSIGNMENT_IN_FUNC_RETURN_CONTEXT, AssignmentOperator.ASSIGN,
								null, -1);
					}
				}
				else{
					if(expectedTeWithAttrs.getBasicType() != VoidTypeEntry.getInstance()){
						// A function expected to return a non-void type is returning void
						errorHandler.addError(sourceFileName, location, "", "",  ErrorHandler.E_RETURN_VOID_IN_FUNC_RETURNING_NON_VOID);						
					}
				}
			}

			// If there is no error, send back the tree for the return statement

			if(!errorHandler.isFoundStrictError()){
				return translatingMediator.translateReturnExpr(expectedTeWithAttrs, teWithAttrsRetExpr, returnExprTree, compilationContext, returnValueTree);
			}			
		}

		return null;   // Error

	}

	private IRTree translateLabeledStatement(LabeledStatement labeledStatement){
		SourceLocation location = new SourceLocation(labeledStatement.getLineNum(), labeledStatement.getPos());

		String strLabel = labeledStatement.getLabel();
		Statement stmt = labeledStatement.getStmt();
		ConstExpr constExpr = labeledStatement.getCaseExpr();
		Label label = new Label();
		IRTreeLabel irTreeLabel = new IRTreeLabel(label);

		// Check the type of the labelled statement 
		// (ID: statement or case const expr: statement or default: statement)
		if(strLabel != null){
			if(!labels.add(strLabel)){   // Label already exists
				errorHandler.addError(sourceFileName, location, strLabel + ":", "",  ErrorHandler.E_LABEL_REDEFINED);
			}			
		}
		else{ // Must be case or default

			if(constExpr != null) {  // case const_expr : statement

				// Case label should reduce to a integer constant.
				Translated exprExpAndType = translateConditionalExpr(constExpr.getCondExpr());
				IRTree constExprTree = exprExpAndType.getIRTree();



				translatingMediator.setCaseValueVsLabelMap(constExprTree, irTreeLabel);

				TypeEntryWithAttributes tpWithAttrs = exprExpAndType.getTypeEntry();
				BasicType tp = tpWithAttrs.getBasicType();
				String constVal = tpWithAttrs.getLiteralValue();

				if(iterOrSwitchStack.isEmpty() || iterOrSwitchStack.peek().getType() != IterationOrSelectionLabels.SWITCH){
					if(constVal != null)
						errorHandler.addError(sourceFileName, location, "", "",  ErrorHandler.E_CASE_USED_OUTSIDE_SWITCH);
				}	

				if(constVal == null){
					errorHandler.addError(sourceFileName, location, "", "",  ErrorHandler.E_CASE_EXPR_MUST_BE_CONST);
				}
				else {
					if(!(tp == IntTypeEntry.getInstance() || tp == CharTypeEntry.getInstance() ||
							tp == ShortTypeEntry.getInstance() || tp == LongTypeEntry.getInstance())){
						errorHandler.addError(sourceFileName, location, "", "",  ErrorHandler.E_CASE_EXPR_MUST_BE_CONST);						
					}	
				}
			}
			else{    // default : statement
				if(iterOrSwitchStack.isEmpty() || iterOrSwitchStack.peek().getType() != IterationOrSelectionLabels.SWITCH){
					errorHandler.addError(sourceFileName, location, "", "",  ErrorHandler.E_DEFAULT_USED_OUTSIDE_SWITCH);
				}

				translatingMediator.setCaseValueVsLabelMap(null, irTreeLabel);
			}
		}

		// translate the statement(s) under this label now
		if(!errorHandler.isFoundStrictError()){
			Stack<IRTree> irTreeStack = new Stack<IRTree>();
			IRTree irTree = translateStatement(stmt);

			if(irTreeLabel != null)
				irTreeStack.push(irTreeLabel);
			irTreeStack.push(irTree);

			return translatingMediator.translateSeqStatement(irTreeStack);
		}
		else
			return null;
	}

	// THE EXPRESSIONS
	private Translated translateExpr(RootExpr expr){
		Translated translatedExprAndType = new Translated();
		if(expr != null) {  // Can be null because it can just be a semicolon
			IRTree previousIRTree = null;
			while(expr != null) {
				AssignmentExpr assgnExpr = expr.getAssignmentExpr();
				translatedExprAndType = translateAssignmentExpr(assgnExpr);
				// can be something like this int i = 0, j = 10; etc..., so we have to store the previous irTree and
				// add it to successive irTrees.
				previousIRTree = translatingMediator.addPreviousIRTree(previousIRTree, translatedExprAndType.getIRTree());
				translatedExprAndType.setIRTree(previousIRTree);
				expr = expr.getRootExprNext();
			}
		}
		return translatedExprAndType;
	}

	private Translated translateAssignmentExpr(AssignmentExpr assgnExpr){		
		assignmentExprs.push(assgnExpr);

		ConditionalExpr conditionalExpr = assgnExpr.getConditionalExpr();
		Translated retValue = null;

		if(conditionalExpr != null){     // This is a conditional expression
			retValue =  translateConditionalExpr(conditionalExpr);
		}
		else{

			UnaryExpr unaryExpr = assgnExpr.getUnaryExpr();

			Translated unaryExprTransExpAndType = translateUnaryExpr(unaryExpr);
			TypeEntryWithAttributes ueTeWithAttrs = unaryExprTransExpAndType.getTypeEntry();
			boolean isLValue = false;

			if(ueTeWithAttrs != null)
				isLValue = ueTeWithAttrs.isLValue();

			AssignmentOperator asgnOpr = assgnExpr.getAssignmentOperator();
			AssignmentExpr rhsExpr = assgnExpr.getAssignmentExpr();
			Translated assgnExprTransExpAndType = translateAssignmentExpr(rhsExpr);
			TypeEntryWithAttributes rhsTeWithAttrs = assgnExprTransExpAndType.getTypeEntry();

			// This possible for statements like p = p++ where p can be of any type
			if(ueTeWithAttrs != null && !ueTeWithAttrs.isLValue() && isLValue)
				ueTeWithAttrs.setIsLValue(true);

			// Check if the types are compatible
			int lineNum = unaryExpr.getLineNum();
			int pos = unaryExpr.getPos();

			if(ueTeWithAttrs != null && rhsTeWithAttrs != null){
				BasicType actualLhsBasicType = ueTeWithAttrs.getBasicType();
				boolean isUnSigned = ueTeWithAttrs.isUnsigned();
				BasicType actualRhsBasicType = rhsTeWithAttrs.getBasicType();
				boolean isUnSigned2 = rhsTeWithAttrs.isUnsigned();

				Type lhsIrType = translatingMediator.getLLVMType(actualLhsBasicType, isUnSigned, compilationContext);
				Type rhsIrType = translatingMediator.getLLVMType(actualRhsBasicType, isUnSigned2, compilationContext);

				IRTree rhsType = assgnExprTransExpAndType.getIRTree();	
				IRTree lhsType = unaryExprTransExpAndType.getIRTree();

				if(lhsIrType != rhsIrType && (rhsType instanceof IRTreeConst)){
					IRTreeConst irTreeConst = (IRTreeConst)rhsType;
					Value value = irTreeConst.getExpressionValue();
					if(lhsIrType.isFloatingPointType() && (value instanceof ConstantInt)){
						ConstantInt constantInt = (ConstantInt)value;
						APInt apInt = constantInt.getApInt();
						String strVal = apInt.toString();
						ConstantFP constantFP = null;
						APFloat apFloat = new APFloat(APFloat.IEEEdouble, strVal+".0");
						try {
							constantFP = new ConstantFP(Type.getDoubleType(compilationContext), apFloat);
						} catch (InstantiationException e) {
							e.printStackTrace();
							System.exit(-1);
						}
						irTreeConst = new IRTreeConst(constantFP);
						rhsType = irTreeConst;
					}
				}

				checkAssignmentCompatibility(ueTeWithAttrs, rhsTeWithAttrs, new SourceLocation(lineNum, pos), 
						Semantic.ASSIGNMENT_IN_ASSIGNMENT_EXPR, asgnOpr.getType(), null, -1);

				if(!errorHandler.isFoundStrictError()){  
					// No strict error, can be translated to tree IR

					IRTree assemAssign = null;

					int binaryOprInAssignment = getBinaryOpFromAssignmentOpr(asgnOpr.getType());

					DoubleTypeEntry doubleTypeEntry = DoubleTypeEntry.getInstance();
					FloatTypeEntry floatTypeEntry = FloatTypeEntry.getInstance();
					boolean isFloatingPointAssignment = (actualLhsBasicType.equals(doubleTypeEntry) || actualLhsBasicType.equals(floatTypeEntry)) ? true : (actualRhsBasicType.equals(doubleTypeEntry) || actualRhsBasicType.equals(floatTypeEntry))? true : false;

					assemAssign = translatingMediator.translateAssignStm(lhsType, rhsType, 
							binaryOprInAssignment, isFloatingPointAssignment, compilationContext);

					assgnExprTransExpAndType.setIRTree(assemAssign);
				}
			}

			retValue =  assgnExprTransExpAndType;			
		}

		if(!assignmentExprs.isEmpty()){
			assignmentExprs.pop();
		}

		if(assignmentExprs.empty()){			
			// Check if any postfix expressions must be evaluated
			if(postfixOperativeExprs != null && postfixOperativeExprs.size() > 0 && !errorHandler.isFoundStrictError()){
				IRTree irTree = retValue.getIRTree();				
				IRTree finalTree = translatingMediator.addPostfixTree(irTree, postfixOperativeExprs);
				retValue.setIRTree(finalTree);							
			}

			postfixOperativeExprs = null;  // Reset			
		}		

		return retValue;

	}




	private Type createPointerType(Type irType, Pointer pointer) {
		if(pointer != null){
			try {
				irType = Type.getPointerType(compilationContext, irType, 0);
				while(pointer.getPointer() != null){
					irType = Type.getPointerType(compilationContext, irType, 0);
					pointer = pointer.getPointer();
				}
			} catch (TypeCreationException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
		return irType;
	}

	private Translated translateConditionalExpr(ConditionalExpr conditionalExpr){
		LogicalOrExpr logicalOrExpr = conditionalExpr.getLogicalOrExpr();
		RootExpr trueExpr = conditionalExpr.getTrueInConditional();
		SourceLocation location = new SourceLocation(conditionalExpr.getLineNum(), conditionalExpr.getPos());
		Translated teTrue = null;
		if(trueExpr != null){
			Translated retValue = new Translated();
			ConditionalExpr falseExpr = conditionalExpr.getFalseInConditional();
			Translated teFalse = translateConditionalExpr(falseExpr);
			teTrue = translateExpr(trueExpr);
			Translated teLogicalOrExpr = translateLogicalOrExpr(logicalOrExpr);

			BasicType result = getTranslatedExprTypePostOperation(teTrue.getTypeEntry(), 
					teFalse.getTypeEntry(), location, LOGICAL_OR);

			TypeEntryWithAttributes teWithAttrs = new TypeEntryWithAttributes();
			teWithAttrs.setBasicType(result);
			teWithAttrs.setIsLValue(false);  // This is an expression
			retValue.setEntry(teWithAttrs); 
			Type type = translatingMediator.getLLVMType(result, false, compilationContext);

			if(!errorHandler.isFoundStrictError()){ // No error in semantic checking yet, create and return the tree	
				IRTree lhs = teTrue.getIRTree();
				IRTree rhs = teFalse.getIRTree();
				IRTree condIRTree = teLogicalOrExpr.getIRTree();
				IRTree irTree =  translatingMediator.translateTernaryOp(lhs,rhs, condIRTree, compilationContext, type);

				retValue.setIRTree(irTree);			
			}

			return retValue;
		}
		else{
			return translateLogicalOrExpr(logicalOrExpr);
		}		
	}

	private Translated translateLogicalOrExpr(LogicalOrExpr logicalOrExpr){

		Translated retValue = new Translated();	

		LogicalAndExpr logicalAndExpr = logicalOrExpr.getLogicalAndExpr();
		LogicalOrExpr nextLOExpr = logicalOrExpr.getLogicalOrExpr();
		SourceLocation location = new SourceLocation(logicalOrExpr.getLineNum(), logicalOrExpr.getPos());

		if(nextLOExpr == null)
			return translateLogicalAndExpr(logicalAndExpr);

		// This is ACTUALLY a logical or expression
		//Label currentLabel = Label.getCurrentLabel();
		Translated leftExprAndType = translateLogicalOrExpr(nextLOExpr);
		Translated rightExprAndType = translateLogicalAndExpr(logicalAndExpr);

		BasicType result = getTranslatedExprTypePostOperation(leftExprAndType.getTypeEntry(), 
				rightExprAndType.getTypeEntry(), location, LOGICAL_OR);

		TypeEntryWithAttributes teWithAttrs = new TypeEntryWithAttributes();
		teWithAttrs.setBasicType(result);
		teWithAttrs.setIsLValue(false);  // This is an expression
		retValue.setEntry(teWithAttrs); 

		if(!errorHandler.isFoundStrictError()){ // No error in semantic checking yet, create and return the tree	
			IRTree lhs = leftExprAndType.getIRTree();
			IRTree rhs = rightExprAndType.getIRTree();
			IRTree logicalOrExprTree =  translatingMediator.translateLogicalOperator(BinaryOpExpr.OR, lhs, rhs, compilationContext);

			retValue.setIRTree(logicalOrExprTree);			
		}			

		return retValue;
	}

	private Translated translateLogicalAndExpr(LogicalAndExpr logicalAndExpr){

		Translated retValue = new Translated();

		InclusiveOrExpr inclusiveOrExpr = logicalAndExpr.getInclusiveOrExpr();
		LogicalAndExpr nextLAExpr = logicalAndExpr.getLogicalAndExpr();
		SourceLocation location = new SourceLocation(logicalAndExpr.getLineNum(), logicalAndExpr.getPos());

		if(nextLAExpr == null)
			return translateInclusiveOrExpr(inclusiveOrExpr);

		// This is ACTUALLY a logical and expression
		//Label currentLabel = Label.getCurrentLabel();
		Translated leftExprAndType = translateLogicalAndExpr(nextLAExpr);
		Translated rightExprAndType = translateInclusiveOrExpr(inclusiveOrExpr);
		BasicType result = getTranslatedExprTypePostOperation(leftExprAndType.getTypeEntry(), 
				rightExprAndType.getTypeEntry(), location, LOGICAL_AND);

		TypeEntryWithAttributes teWithAttrs = new TypeEntryWithAttributes();
		teWithAttrs.setBasicType(result);
		teWithAttrs.setIsLValue(false);  // This is an expression
		retValue.setEntry(teWithAttrs);  

		if(!errorHandler.isFoundStrictError()){ // No error in semantic checking yet, create and return the tree	
			IRTree lhs = leftExprAndType.getIRTree();
			IRTree rhs = rightExprAndType.getIRTree();
			IRTree logicalAndExprTree =  translatingMediator.translateLogicalOperator(BinaryOpExpr.AND, lhs, rhs, compilationContext);

			retValue.setIRTree(logicalAndExprTree);			
		}		

		return retValue;		
	}

	private Translated translateInclusiveOrExpr(InclusiveOrExpr inclusiveOrExpr){

		Translated retValue = new Translated();

		ExclusiveOrExpr exclusiveOrExpr = inclusiveOrExpr.getExclusiveOrExpr();
		InclusiveOrExpr nextIEExpr = inclusiveOrExpr.getInclusiveOrExpr();
		SourceLocation location = new SourceLocation(inclusiveOrExpr.getLineNum(), inclusiveOrExpr.getPos());

		if(nextIEExpr == null)
			return translateExclusiveOrExpr(exclusiveOrExpr);

		// This is ACTUALLY a inclusive or expression
		Translated leftExprAndType = translateInclusiveOrExpr(nextIEExpr);
		Translated rightExprAndType = translateExclusiveOrExpr(exclusiveOrExpr);
		BasicType result = getTranslatedExprTypePostOperation(leftExprAndType.getTypeEntry(), 
				rightExprAndType.getTypeEntry(), location, INCLUSIVE_OR);

		TypeEntryWithAttributes teWithAttrs = new TypeEntryWithAttributes();
		teWithAttrs.setBasicType(result);
		teWithAttrs.setIsLValue(false);  // This is an expression
		retValue.setEntry(teWithAttrs);  

		if(!errorHandler.isFoundStrictError()){ // No error in semantic checking yet, create and return the tree	

			IRTree lhs = leftExprAndType.getIRTree();
			IRTree rhs = rightExprAndType.getIRTree();

			IRTree inclOrExprTree =  translatingMediator.translateBinOp(BinaryOpExpr.INCLUSIVE_OR, lhs, rhs, false);
			retValue.setIRTree(inclOrExprTree);			
		}		

		return retValue;
	}

	private Translated translateExclusiveOrExpr(ExclusiveOrExpr exclusiveOrExpr){

		Translated retValue = new Translated();

		AndExpr andExpr = exclusiveOrExpr.getAndExpr();
		ExclusiveOrExpr nextEOExpr = exclusiveOrExpr.getExclusiveOrExpr();
		SourceLocation location = new SourceLocation(exclusiveOrExpr.getLineNum(), exclusiveOrExpr.getPos());

		if(nextEOExpr == null)
			return translateAndExpr(andExpr);

		// This is ACTUALLY a exclusive or expression
		Translated leftExprAndType = translateExclusiveOrExpr(nextEOExpr);
		Translated rightExprAndType = translateAndExpr(andExpr);
		BasicType result = getTranslatedExprTypePostOperation(leftExprAndType.getTypeEntry(), 
				rightExprAndType.getTypeEntry(), location, EXCLUSIVE_OR);

		TypeEntryWithAttributes teWithAttrs = new TypeEntryWithAttributes();
		teWithAttrs.setBasicType(result);
		teWithAttrs.setIsLValue(false);  // This is an expression
		retValue.setEntry(teWithAttrs);  

		if(!errorHandler.isFoundStrictError()){ // No error in semantic checking yet, create and return the tree				
			IRTree lhs = leftExprAndType.getIRTree();
			IRTree rhs = rightExprAndType.getIRTree();			

			IRTree exclOrExprTree =  translatingMediator.translateBinOp(
					BinaryOpExpr.EXCLUSIVE_OR, lhs, rhs, false);

			retValue.setIRTree(exclOrExprTree);			
		}	

		return retValue;

	}

	private Translated translateAndExpr(AndExpr andExpr){

		Translated retValue = new Translated();

		EqualityExpr equalityExpr = andExpr.getEqualityExpr();
		AndExpr nextAndExpr = andExpr.getAndExpr();
		SourceLocation location = new SourceLocation(andExpr.getLineNum(), andExpr.getPos());

		if(nextAndExpr == null)
			return translateEqualityExpr(equalityExpr);

		// This is ACTUALLY a AND expression
		Translated leftExprAndType = translateAndExpr(nextAndExpr);
		Translated rightExprAndType = translateEqualityExpr(equalityExpr);
		BasicType result = getTranslatedExprTypePostOperation(leftExprAndType.getTypeEntry(), 
				rightExprAndType.getTypeEntry(), location, AND);

		TypeEntryWithAttributes teWithAttrs = new TypeEntryWithAttributes();
		teWithAttrs.setBasicType(result);
		teWithAttrs.setIsLValue(false);  // This is an expression
		retValue.setEntry(teWithAttrs);  

		if(!errorHandler.isFoundStrictError()){ // No error in semantic checking yet, create and return the tree	
			IRTree lhs = leftExprAndType.getIRTree();
			IRTree rhs = rightExprAndType.getIRTree();						

			IRTree andExprTree =  translatingMediator.translateBinOp(
					BinaryOpExpr.AND, lhs, rhs, false);

			retValue.setIRTree(andExprTree);			
		}	

		return retValue;

	}

	private Translated translateEqualityExpr(EqualityExpr equalityExpr){

		Translated retValue = new Translated();

		RelationalExpr relationalExpr = equalityExpr.getRelationalExpr();
		SourceLocation location = new SourceLocation(equalityExpr.getLineNum(), equalityExpr.getPos());

		int opr = equalityExpr.getOperator();
		String type = EQUALS;
		if(opr == EqualityExpr.NOT_EQUALS){
			type = NOT_EQUALS;
		}
		EqualityExpr nextEqExpr = equalityExpr.getEqualityExpr();

		if(nextEqExpr == null)
			return translateRelationalExpr(relationalExpr);

		// This is ACTUALLY an equality (equal or not equal) expression
		Translated leftExprAndType = translateEqualityExpr(nextEqExpr);

		Translated rightExprAndType = translateRelationalExpr(relationalExpr);
		BasicType result = getTranslatedExprTypePostOperation(leftExprAndType.getTypeEntry(), 
				rightExprAndType.getTypeEntry(), location, type);

		TypeEntryWithAttributes teWithAttrs = new TypeEntryWithAttributes();
		teWithAttrs.setBasicType(result);
		teWithAttrs.setIsLValue(false);  // This is an expression
		retValue.setEntry(teWithAttrs); 

		Predicate predicate = null;
		if(result != null)
			if(result.equals(DoubleTypeEntry.getInstance()) || result.equals(FloatTypeEntry.getInstance()))
				if(type.equals(EQUALS))
					predicate = Predicate.FCMP_OEQ;
				else
					predicate = Predicate.FCMP_ONE;
			else if(result.equals(IntTypeEntry.getInstance()) || result instanceof PointerTypeEntry || result instanceof CharTypeEntry)
				if(type.equals(EQUALS))
					predicate = Predicate.ICMP_EQ;
				else
					predicate = Predicate.ICMP_NE;

		IRTree irTree = translatingMediator.translateComparisonOperator(predicate, leftExprAndType.getIRTree(), rightExprAndType.getIRTree());
		retValue.setIRTree(irTree);

		return retValue;		
	}

	private Translated translateRelationalExpr(RelationalExpr relationalExpr){

		Translated retValue = new Translated();

		String type = LESSER_THAN;

		int rel = relationalExpr.getOperator();
		if(rel == RelationalExpr.GREATER_THAN){
			type = GREATER_THAN;
		}
		else if(rel == RelationalExpr.GREATER_THAN_OR_EQUAL_TO){
			type = GREATER_THAN_OR_EQUAL_TO; 
		}
		else if(rel == RelationalExpr.LESSER_THAN_OR_EQUAL_TO){
			type = LESSER_THAN_OR_EQUAL_TO; 
		}

		ShiftExpr shiftExpr = relationalExpr.getShiftExpr();
		RelationalExpr nextRlExpr = relationalExpr.getRelationalExpr();
		SourceLocation location = new SourceLocation(relationalExpr.getLineNum(), relationalExpr.getPos());

		if(nextRlExpr == null)
			return translateShiftExpr(shiftExpr);

		// This is ACTUALLY a relational expression
		Translated leftExprAndType = translateRelationalExpr(nextRlExpr);
		Translated rightExprAndType = translateShiftExpr(shiftExpr);
		BasicType result = getTranslatedExprTypePostOperation(leftExprAndType.getTypeEntry(), 
				rightExprAndType.getTypeEntry(), location, type);

		TypeEntryWithAttributes teWithAttrs = new TypeEntryWithAttributes();
		teWithAttrs.setBasicType(result);
		teWithAttrs.setIsLValue(false);  // This is an expression
		retValue.setEntry(teWithAttrs);  

		if(!errorHandler.isFoundStrictError()){ // No error in semantic checking yet, create and return the tree
			TypeEntryWithAttributes teLeft = leftExprAndType.getTypeEntry();
			BasicType basicType = teLeft.getBasicType();
			boolean isUnSigned = teLeft.isUnsigned();
			Type operandType = translatingMediator.getLLVMType(basicType, isUnSigned, compilationContext);
			// TODO Vikash - handle other attributes when we know how to include them (signed, unsigned)
			Predicate predicate = null;
			if(type.equals(LESSER_THAN)){
				if(operandType.isIntegerType())
					predicate = Predicate.ICMP_SLT;
				else
					predicate = Predicate.FCMP_OLT;
			}
			else if(type.equals(GREATER_THAN)){
				if(operandType.isIntegerType())
					predicate = Predicate.ICMP_SGT;
				else
					predicate = Predicate.FCMP_OGT;
			}
			else if(type.equals(GREATER_THAN_OR_EQUAL_TO)){
				if(operandType.isIntegerType())
					predicate = Predicate.ICMP_SGE;
				else
					predicate = Predicate.FCMP_OGE;
			}
			else{
				if(operandType.isIntegerType())
					predicate = Predicate.ICMP_SLE;
				else
					predicate = Predicate.FCMP_OLE;
			}

			IRTree lhs = leftExprAndType.getIRTree();
			IRTree rhs = rightExprAndType.getIRTree();												

			IRTree relnExprTree =  translatingMediator.translateComparisonOperator(
					predicate, lhs, rhs);			


			retValue.setIRTree(relnExprTree);			
		}	

		return retValue;		
	}

	private Translated translateShiftExpr(ShiftExpr shiftExpr){

		Translated retValue = new Translated();

		AdditiveExpr additiveExpr = shiftExpr.getAdditiveExpr();
		ShiftExpr nextShiftExpr = shiftExpr.getShiftExpr();
		SourceLocation location = new SourceLocation(shiftExpr.getLineNum(), shiftExpr.getPos());

		int opr = shiftExpr.getOperator();
		String type = LEFT_SHIFT;
		int binaryOpExprType = BinaryOpExpr.LEFT_SHIFT;
		if(opr == ShiftExpr.RIGHT_SHIFT){
			type = RIGHT_SHIFT;
			binaryOpExprType = BinaryOpExpr.RIGHT_SHIFT;
		}

		if(nextShiftExpr == null)
			return translateAdditiveExpr(additiveExpr);

		// This is ACTUALLY a shift expression
		Translated leftExprAndType = translateShiftExpr(nextShiftExpr);
		TypeEntryWithAttributes  leftTypeEntryWithAttributes = leftExprAndType.getTypeEntry();
		Translated rightExprAndType = translateAdditiveExpr(additiveExpr);
		TypeEntryWithAttributes  rightTypeEntryWithAttributes = rightExprAndType.getTypeEntry();
		BasicType result = getTranslatedExprTypePostOperation(leftExprAndType.getTypeEntry(), 
				rightExprAndType.getTypeEntry(), location, type);

		BasicType leftBasicType = leftTypeEntryWithAttributes.getBasicType();
		BasicType rightBasicType = rightTypeEntryWithAttributes.getBasicType();

		if(((leftBasicType instanceof FloatTypeEntry) || (leftBasicType instanceof DoubleTypeEntry)) || ((rightBasicType instanceof FloatTypeEntry) || (rightBasicType instanceof DoubleTypeEntry))){
			errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_INVALID_OPERANDS);
		}

		// If the shift count is more than the size of the type, then issue a warning
		if(rightBasicType instanceof IntTypeEntry && rightTypeEntryWithAttributes.isConst()){
			boolean isExcess = translatingMediator.chkIfShiftCountIsMoreThanSizeOfType(leftBasicType, rightExprAndType);
			if(isExcess)
				errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_LEFTSHIFT_COUNT_GREATER_THAN_SIZE_OF_TYPE);
		}

		TypeEntryWithAttributes teWithAttrs = new TypeEntryWithAttributes();
		teWithAttrs.setBasicType(result);
		teWithAttrs.setIsLValue(false);  // This is an expression
		retValue.setEntry(teWithAttrs);  

		if(!errorHandler.isFoundStrictError()){ // No error in semantic checking yet, create and return the tree
			IRTree lhs = leftExprAndType.getIRTree();
			IRTree rhs = rightExprAndType.getIRTree();												

			IRTree shiftExprTree =  translatingMediator.translateBinOp(
					binaryOpExprType, lhs, rhs, false);


			retValue.setIRTree(shiftExprTree);

		}	

		return retValue;		
	}

	private Translated translateAdditiveExpr(AdditiveExpr additiveExpr){

		Translated retValue = new Translated();

		MultiplicativeExpr multiplicativeExpr = additiveExpr.getMultiplicativeExpr();
		AdditiveExpr nextAdditiveExpr = additiveExpr.getAdditiveExpr();
		SourceLocation location = new SourceLocation(additiveExpr.getLineNum(), additiveExpr.getPos());

		int opr = additiveExpr.getOperator();


		if(nextAdditiveExpr == null)
			return translateMultiplicativeExpr(multiplicativeExpr);

		// This is ACTUALLY a add/subtract expression
		String type = ADD;
		int binaryOpExprType = BinaryOpExpr.ADDITIVE;
		if(opr == AdditiveExpr.SUBTRACT){
			binaryOpExprType = BinaryOpExpr.MINUS;
			type = SUBTRACT;
		}

		Translated addTransAndExpType = translateAdditiveExpr(nextAdditiveExpr);		
		Translated meTransAndExpType = translateMultiplicativeExpr(multiplicativeExpr);

		BasicType result = getTranslatedExprTypePostOperation(meTransAndExpType.getTypeEntry(), 
				addTransAndExpType.getTypeEntry(),location, type);

		TypeEntryWithAttributes teWithAttrs = new TypeEntryWithAttributes();
		teWithAttrs.setBasicType(result);
		teWithAttrs.setIsLValue(false);  // This is an expression
		retValue.setEntry(teWithAttrs);

		if(!errorHandler.isFoundStrictError()){ // No error in semantic checking yet, create and return the tree

			IRTree addType = addTransAndExpType.getIRTree();
			IRTree multiplyType = meTransAndExpType.getIRTree();

			BasicType addBasicType = addTransAndExpType.getTypeEntry().getBasicType();

			// This is something like *(ptr + 3),  or *(ptr + var), or *(3 + ptr), or *(var + ptr)
			if((result instanceof PointerTypeEntry) || (result instanceof ArrayTypeEntry)){
				IRTree pointerType = null;
				IRTree non_pointerType = null;
				if((addBasicType instanceof PointerTypeEntry) || (addBasicType instanceof ArrayTypeEntry)){
					pointerType = addType;
					non_pointerType = multiplyType;
				}
				else{ // Must be multiplicative part is the PointerTypeEntry
					pointerType = multiplyType;
					non_pointerType = addType;
				}

				// IF non-pointer type is Constant int type then it should be of 64 bits
				if(non_pointerType instanceof IRTreeConst){
					IRTreeConst irTreeConst = (IRTreeConst)non_pointerType;
					APInt apInt = irTreeConst.getApInt();
					Value value = irTreeConst.getExpressionValue();
					if(apInt != null){
						if(binaryOpExprType == BinaryOpExpr.MINUS){
							String val = apInt.toString();
							val = "-"+val;
							apInt = new APInt(64, val, 10);
							irTreeConst.setApInt(apInt);
						}
						
						value.setType(Type.getInt64Type(compilationContext, true));
					}
				}

				IRTree additiveExprTree =  translatingMediator.translateBinPointerExpr(binaryOpExprType, pointerType,non_pointerType);
				retValue.setIRTree(additiveExprTree);
			}
			else{
				TypeEntryWithAttributes lhsTeWithAttrs = addTransAndExpType.getTypeEntry();

				BasicType actualLhsBasicType = lhsTeWithAttrs.getBasicType();
				TypeEntryWithAttributes rhsTeWithAttrs = meTransAndExpType.getTypeEntry();
				BasicType actualRhsBasicType = rhsTeWithAttrs.getBasicType();

				DoubleTypeEntry doubleTypeEntry = DoubleTypeEntry.getInstance();
				FloatTypeEntry floatTypeEntry = FloatTypeEntry.getInstance();
				boolean isFloatingPointAssignment = (actualLhsBasicType.equals(doubleTypeEntry) || actualLhsBasicType.equals(floatTypeEntry)) ? true : (actualRhsBasicType.equals(doubleTypeEntry) || actualRhsBasicType.equals(floatTypeEntry))? true : false;

				IRTree additiveExprTree =  translatingMediator.translateBinOp(
						binaryOpExprType, addType, multiplyType, isFloatingPointAssignment);	

				retValue.setIRTree(additiveExprTree);
			}

		}	

		return retValue;
	}

	private Translated translateMultiplicativeExpr(MultiplicativeExpr multiplicativeExpr){

		Translated retValue = new Translated();

		CastExpr castExpr = multiplicativeExpr.getCastExpr();
		MultiplicativeExpr nextMulExpr = multiplicativeExpr.getOtherExpr();
		SourceLocation location = new SourceLocation(multiplicativeExpr.getLineNum(), multiplicativeExpr.getPos());

		int opr = multiplicativeExpr.getOperator();
		String type = MULTIPLY;
		int binOpExprType = BinaryOpExpr.MULTIPLY;
		if(opr == MultiplicativeExpr.DIVIDE){
			type = DIVIDE;
			binOpExprType = BinaryOpExpr.DIVIDE;
		}
		else if(opr == MultiplicativeExpr.MODULO){
			type = MODULO;
			binOpExprType = BinaryOpExpr.MODULO;
		}

		if(nextMulExpr == null)
			return translateCastExpr(castExpr);

		// This is ACTUALLY a multiplicative expression

		Translated leftExprAndType = translateMultiplicativeExpr(nextMulExpr);
		Translated rightExprAndType = translateCastExpr(castExpr);
		BasicType result = getTranslatedExprTypePostOperation(leftExprAndType.getTypeEntry(), 
				rightExprAndType.getTypeEntry(), location, type);

		TypeEntryWithAttributes teWithAttrs = new TypeEntryWithAttributes();
		teWithAttrs.setBasicType(result);
		teWithAttrs.setIsLValue(false);  // This is an expression
		retValue.setEntry(teWithAttrs);  

		if(!errorHandler.isFoundStrictError()){ // No error in semantic checking yet, create and return the tree	

			IRTree lhs = leftExprAndType.getIRTree();
			IRTree rhs = rightExprAndType.getIRTree();

			TypeEntryWithAttributes lhsTeWithAttrs = leftExprAndType.getTypeEntry();
			BasicType actualLhsBasicType = lhsTeWithAttrs.getBasicType();
			TypeEntryWithAttributes rhsTeWithAttrs = rightExprAndType.getTypeEntry();
			BasicType actualRhsBasicType = rhsTeWithAttrs.getBasicType();

			DoubleTypeEntry doubleTypeEntry = DoubleTypeEntry.getInstance();
			FloatTypeEntry floatTypeEntry = FloatTypeEntry.getInstance();
			boolean isFloatingPointAssignment = (actualLhsBasicType.equals(doubleTypeEntry) || actualLhsBasicType.equals(floatTypeEntry)) ? true : (actualRhsBasicType.equals(doubleTypeEntry) || actualRhsBasicType.equals(floatTypeEntry))? true : false;

			IRTree mulExprTree =  translatingMediator.translateBinOp( binOpExprType, 
					lhs, rhs, isFloatingPointAssignment);

			retValue.setIRTree(mulExprTree);		
		}	

		return retValue;		
	}

	private Translated translateCastExpr(CastExpr castExpr){

		Translated retValue = new Translated();
		UnaryExpr unaryExpr = castExpr.getUnaryExpr();
		TypeName typeName = castExpr.getTypeName();

		if(typeName == null){			
			return translateUnaryExpr(unaryExpr);
		}

		// This is ACTUALLY a cast expression
		SpecifierQualifierList specifierQualifierList = typeName.getSpecifierQualifierList();
		AbstractDeclarator abstractDeclarator = typeName.getAbstractDeclarator();
		Type irType = null;

		TypeEntryWithAttributes teWithAttrs = null;

		if(specifierQualifierList != null){
			teWithAttrs = getBaseEntryFromSpecsList(specifierQualifierList, false, false, false);
			retValue.setEntry(teWithAttrs);
			irType = translatingMediator.getLLVMType(teWithAttrs.getBasicType(), teWithAttrs.isUnsigned(), compilationContext);
		}
		if(abstractDeclarator != null){
			Pointer pointer = abstractDeclarator.getPointer();
			if(teWithAttrs != null){
				PointerTypeEntry pointerTypeEntry = new PointerTypeEntry(teWithAttrs);
				TypeEntryWithAttributes attributes = new TypeEntryWithAttributes();
				attributes.copy(teWithAttrs, true);
				attributes.setBasicType(pointerTypeEntry);
				attributes.setVoid(false);

				retValue.setEntry(attributes);
			}
			irType = createPointerType(irType, pointer);
		}

		// TODO Correct this and complete the rest of the implementation here, 
		// including translating to the intermediate representation tree.
		CastExpr otherCastExpr = castExpr.getCastExpr();
		Translated otherCastExprAndType = translateCastExpr(otherCastExpr);
		IRTree irTree = otherCastExprAndType.getIRTree();
		if(irTree != null && (irTree instanceof IRTreeMemory)){
			IRTreeMemory irTreeMemory = (IRTreeMemory)irTree;
			Value memory = irTreeMemory.getMemory();
			if(memory.getType().isPointerType()){
				PointerType pointerType = (PointerType)memory.getType();
				Type containedType = pointerType.getContainedType();
				if(containedType.isArrayType()){
					IRTree aggregateDataStruct = new IRTreeMemory(memory);
					List<Entry<IRTreeIndex, Type>> indexVsType = new ArrayList<Entry<IRTreeIndex,Type>>();
					CompilationContext compilationContext = memory.getContext();
					translatingMediator.createTwoZeroIndexes(compilationContext , pointerType, containedType , indexVsType);
					IRTreeAggregateData aggregateData = new IRTreeAggregateData(aggregateDataStruct , indexVsType );
					otherCastExprAndType.setIRTree(aggregateData);
				}
			}
		}
		translatingMediator.addIRTreeCastToIRTree(otherCastExprAndType, irType);
		retValue.setIRTree(otherCastExprAndType.getIRTree());

		return retValue;
	}

	private Translated translateUnaryExpr(UnaryExpr unaryExpr){

		Translated retValue = new Translated();
		PostfixExpr pfExpr = unaryExpr.getPostfixExpr();

		if(pfExpr != null)  // Is a postfix expression
			return translatePostfixExpr(pfExpr);			

		// Must have some operator followed by another unary or cast expression
		SourceLocation location = new SourceLocation(unaryExpr.getLineNum(), unaryExpr.getPos());
		int unaryExrType = unaryExpr.getOperator();

		if(unaryExrType == UnaryExpr.STAR){  
			// Indirection operator, ensure the cast expression is a pointer
			CastExpr castExpr = unaryExpr.getCastExpr();
			Translated teCastExpr = translateCastExpr(castExpr);
			if(teCastExpr.getTypeEntry() == null)  // This error would already have been handled, continue
				return retValue;

			BasicType castExprBasicType = teCastExpr.getTypeEntry().getBasicType();

			if(!(castExprBasicType instanceof PointerTypeEntry || castExprBasicType instanceof ArrayTypeEntry)){
				// Neither a pointer nor an array element, flag an error
				errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_WRONG_INDIRECTION);
			}
			else{		
				//				TypeEntryWithAttributes teNew = null;
				if(castExprBasicType instanceof PointerTypeEntry){
					PointerTypeEntry ptrTypeEntry = (PointerTypeEntry)castExprBasicType;
					TypeEntryWithAttributes teNew =  ptrTypeEntry.getBaseTypeEntry();

					// If the contained type is function type then we don't need to derefernce it.
					if(teNew.getBasicType() instanceof FunctionEntry)
						return teCastExpr;

					retValue.setEntry(teNew);
					teNew.setIsLValue(true);					
				}
				else {   // Array type
					ArrayTypeEntry arrayTypeEntry = (ArrayTypeEntry)castExprBasicType;
					TypeEntryWithAttributes bt = arrayTypeEntry.getBaseTypeEntry();
					bt.setIsLValue(true);
					retValue.setEntry(bt);
					//					teNew.setIsLValue(true);
				}

				IRTree exprTree = teCastExpr.getIRTree();

				if(!errorHandler.isFoundStrictError()){
					IRTree indirectionTree = translatingMediator.translateIndirection(exprTree, compilationContext);
					retValue.setIRTree(indirectionTree);
				}
			}
		}				
		else if(unaryExrType == UnaryExpr.AMPERSAND){

			CastExpr castExpr = unaryExpr.getCastExpr();
			Translated teChildExpr = translateCastExpr(castExpr);
			TypeEntryWithAttributes teCastExprWithAttrs = teChildExpr.getTypeEntry();
			if(teCastExprWithAttrs == null){
				// Error, but This would already have been handled, continue
				return retValue;
			}

			if(teCastExprWithAttrs.getLiteralValue() != null ){
				errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_ADDRESS_OF_A_CONSTANT);
			}
			else if(teCastExprWithAttrs.isRegister()){
				errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_ADDRESS_OF_A_VAR_IN_REGISTER);
			}
			else if(teCastExprWithAttrs.getBasicType() instanceof ArrayTypeEntry){ 
				errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_ADDRESS_OF_A_CONSTANT);					
			}
			else if(!teCastExprWithAttrs.isLValue()){
				errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_ADDRESS_OF_AN_EXPRESSION);					
			}				
			else{
				TypeEntryWithAttributes newTeAttrs = new TypeEntryWithAttributes();
				PointerTypeEntry ptrEntry = new PointerTypeEntry(teCastExprWithAttrs);
				newTeAttrs.setBasicType(ptrEntry);
				newTeAttrs.setIsLValue(false);
				retValue.setEntry(newTeAttrs);

				IRTree indirectionTree = null;

				IRTree exprTree = teChildExpr.getIRTree();				
				if(exprTree instanceof IRTreeTempOrVar){
					// This variable was considered to be in a register earlier but now we are taking its
					// address; therefore back-track and reassign this to  memory 	
					IRTreeTempOrVar assemTemp = (IRTreeTempOrVar) exprTree;
					indirectionTree = assemTemp;
				}
				else{		
					// This is already in memory, get the address tree
					indirectionTree = exprTree;
				}

				retValue.setIRTree(indirectionTree);				
			}	

		}
		else if(unaryExrType == UnaryExpr.PLUS){
			CastExpr castExpr = unaryExpr.getCastExpr();
			retValue = translateCastExpr(castExpr); // No change from cast expression
			retValue.getTypeEntry().setIsLValue(false);
		}
		else if(unaryExrType == UnaryExpr.MINUS){
			CastExpr castExpr = unaryExpr.getCastExpr();
			retValue = translateCastExpr(castExpr);
			TypeEntryWithAttributes teWithAttrs = retValue.getTypeEntry();
			retValue.getTypeEntry().setIsLValue(false);

			// Check that the operands are correct for a "minus" value
			BasicType bt = teWithAttrs.getBasicType();
			if(!(bt == IntTypeEntry.getInstance() || bt == ShortTypeEntry.getInstance()
					|| bt == CharTypeEntry.getInstance() || bt == LongTypeEntry.getInstance()
					|| bt == DoubleTypeEntry.getInstance() || bt == FloatTypeEntry.getInstance())){

				errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_NEGATIVE_OPERATOR_CANNOT_BE_USED);
			}				
			else{
				IRTree irTree = retValue.getIRTree();
				if(!(irTree instanceof IRTreeConst)){
					IRTree negationTree = translatingMediator.translateNegativeValOfExpr(irTree, compilationContext);
					retValue.setIRTree(negationTree);
				}
				else{
					IRTreeConst irTreeConst = (IRTreeConst)irTree;
					APInt apInt = irTreeConst.getApInt();
					APFloat apFloat = irTreeConst.getApFloat();

					if(apInt != null) {
						apInt = new APInt(apInt.getNumBits(), "-" + apInt.toString(), 10);
						irTreeConst.setApInt(apInt);
					}
					else {
						apFloat.setStrRepresentation("-" + apFloat.getStrRepresentation());
					}

				}
			}			
		}
		else if(unaryExrType == UnaryExpr.SIZEOF){
			UnaryExpr nextUnaryExpr = unaryExpr.getUnaryExpr();
			if(nextUnaryExpr != null){
				Translated teNextUnaryExpr = translateUnaryExpr(nextUnaryExpr);
				TypeEntryWithAttributes newTeAttrs = new TypeEntryWithAttributes();
				newTeAttrs.setUnsigned(true);
				newTeAttrs.setBasicType(IntTypeEntry.getInstance());
				newTeAttrs.setIsLValue(false);
				retValue.setEntry(newTeAttrs);
				int size = getSizeOfType(teNextUnaryExpr.getTypeEntry());
				IRTree constTree = translatingMediator.translateConstant(size, compilationContext);
				retValue.setIRTree(constTree);
			}
			else{
				// TODO modify this in the next phase
				TypeName typeName = unaryExpr.getTypeName();
				retValue = getTranslatedFromTypeName(typeName);
			}
		}
		else if(unaryExrType == UnaryExpr.NOT){
			CastExpr castExpr = unaryExpr.getCastExpr();				
			Translated teCastExpr = translateCastExpr(castExpr);
			TypeEntryWithAttributes teCastExprWithAttrs = teCastExpr.getTypeEntry();

			if(teCastExprWithAttrs == null){
				return retValue;
			}

			BasicType bt = teCastExprWithAttrs.getBasicType();
			IRTree notTree = null;

			if(bt instanceof StructOrUnionTypeEntry){
				errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_UNARY_NEGATION_OP_ON_STRUCT);
				retValue = teCastExpr;
				retValue.getTypeEntry().setIsLValue(false);
				return retValue;
			}
			else{ // No error, build the tree
				IRTree irTree = teCastExpr.getIRTree();
				notTree = translatingMediator.translateNegationOfExpr(irTree,teCastExprWithAttrs, compilationContext);
			}

			TypeEntryWithAttributes newTeAttrs = new TypeEntryWithAttributes();
			newTeAttrs.setBasicType(IntTypeEntry.getInstance());
			newTeAttrs.setIsLValue(false);
			retValue.setEntry(newTeAttrs);
			retValue.setIRTree(notTree);
		}
		else if(unaryExrType == UnaryExpr.TILDE){
			IRTree onesComplementTree = null;
			CastExpr castExpr = unaryExpr.getCastExpr();
			Translated teCastExpr = translateCastExpr(castExpr);
			TypeEntryWithAttributes teCastExprWithAttrs = teCastExpr.getTypeEntry();
			if(teCastExprWithAttrs == null){
				// This would already have been handled, continue
				return retValue;
			}
			BasicType bt = teCastExprWithAttrs.getBasicType();
			if(bt instanceof StructOrUnionTypeEntry){
				errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_ONES_COMPLEMENT_ON_STRUCT);
				TypeEntryWithAttributes newTeAttrs = new TypeEntryWithAttributes();
				newTeAttrs.setBasicType(IntTypeEntry.getInstance());
				newTeAttrs.setIsLValue(false);
				retValue.setEntry(newTeAttrs);		
				return retValue;
			}
			else{
				// Can only be applied to integers
				if(!(bt instanceof IntTypeEntry || bt instanceof LongTypeEntry || bt instanceof CharTypeEntry
						|| bt instanceof ShortTypeEntry )){
					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_ONES_COMPLEMENT_ON_INTEGERS_ONLY);
					retValue = teCastExpr;
					retValue.getTypeEntry().setIsLValue(false);
					return retValue;
				}
				else{
					// Translate to assembly tree
					IRTree irTree = teCastExpr.getIRTree();
					onesComplementTree = translatingMediator.translateOnesComplement(irTree, compilationContext, teCastExprWithAttrs);
					retValue.setIRTree(onesComplementTree);
				}

				TypeEntryWithAttributes newTeAttrs = new TypeEntryWithAttributes();
				newTeAttrs.setBasicType(IntTypeEntry.getInstance());
				newTeAttrs.setIsLValue(false);
				retValue.setEntry(newTeAttrs);		
				return retValue;
			}
		}
		else  if(unaryExrType == UnaryExpr.INCR || unaryExrType == UnaryExpr.DECR){

			UnaryExpr uexpr = unaryExpr.getUnaryExpr();
			Translated teUExpr = translateUnaryExpr(uexpr);
			TypeEntryWithAttributes teWithAttrs = teUExpr.getTypeEntry();								
			BasicType bt = teWithAttrs.getBasicType();
			retValue.setEntry(teWithAttrs);

			String invalidOperandErrMessage = ErrorHandler.E_INVALID_OPERAND_FOR_INCREMENT;
			String operatorNeedsLValueErrMessage = ErrorHandler.E_INCREMENT_OPR_NEEDS_LVALUE;
			boolean isIncrement = true;

			if(unaryExrType == UnaryExpr.DECR){
				invalidOperandErrMessage = ErrorHandler.E_INVALID_OPERAND_FOR_DECREMENT;
				operatorNeedsLValueErrMessage =  ErrorHandler.E_DECREMENT_OPR_NEEDS_LVALUE;
				isIncrement = false;
			}

			IRTree unaryTree = null;

			if(bt instanceof StructOrUnionTypeEntry){
				errorHandler.addError(sourceFileName, location, "", "", invalidOperandErrMessage);
				return retValue;
			}
			else{
				if(!teWithAttrs.isLValue()){
					errorHandler.addError(sourceFileName, location, "", "", operatorNeedsLValueErrMessage);
					return retValue;
				}				
			}

			teWithAttrs.setIsLValue(false);  // This is a lvalue now
			// No error, create assembly tree and set it in return value
			IRTreeExp irTree = (IRTreeExp)teUExpr.getIRTree();

			try {
				IRTreeExp irTreeExp = translatingMediator.translateIncrementOrDecrementOp(irTree, isIncrement);
				IRTreeMove irTreeMove = new IRTreeMove(irTree, irTreeExp);
				Stack<IRTree> irTreeStack = new Stack<IRTree>();
				irTreeStack.push(irTreeExp);
				irTreeStack.push(irTreeMove);
				unaryTree = translatingMediator.translateSeqStatement(irTreeStack);
			} catch (InstructionCreationException e) {
				e.printStackTrace();
				System.exit(-1);
			} catch (InstantiationException e) {
				e.printStackTrace();
				System.exit(-1);
			}
			retValue.setIRTree(unaryTree);
			return retValue;
		}


		return retValue; 
	}

	private Translated getTranslatedFromTypeName(TypeName typeName) {
		Translated retValue = new Translated();
		SpecifierQualifierList specifierQualifierList = typeName.getSpecifierQualifierList();
		while(specifierQualifierList != null){
			TypeSpecifier typeSpecifier = specifierQualifierList.getTypeSpecifier();
			Type type2 = getIRTypeFromTypeSpecifier(typeSpecifier);
			int size = LLVMUtility.getSizeForType(properties, type2);
			IRTree constTree = translatingMediator.translateConstant(size, compilationContext);
			retValue.setIRTree(constTree);
			TypeEntryWithAttributes typeEntry = new TypeEntryWithAttributes();
			typeEntry.setBasicType(IntTypeEntry.getInstance());
			retValue.setTypeEntry(typeEntry);
			specifierQualifierList = specifierQualifierList.getSpecifierQualifierListNext();
		}
		return retValue;
	}

	private Type getIRTypeFromTypeSpecifier(TypeSpecifier typeSpecifier) {
		int type = typeSpecifier.getType();
		BasicType basicType = null;
		boolean isUnSigned = false;
		switch(type){
		case 1 : basicType = VoidTypeEntry.getInstance();break;
		case 2 : basicType = CharTypeEntry.getInstance();break;
		case 3 : basicType = ShortTypeEntry.getInstance();break;
		case 4 : basicType = IntTypeEntry.getInstance();break;
		case 5 : basicType = LongTypeEntry.getInstance();break;
		case 6 : basicType = FloatTypeEntry.getInstance();break;
		case 7 : basicType = DoubleTypeEntry.getInstance();break;
		case 8 : isUnSigned = false;break;
		case 9 : isUnSigned = true; break;
		case 10 : StructOrUnionSpecifier structOrUnionSpecifier = (StructOrUnionSpecifier)typeSpecifier;
		String tagName = structOrUnionSpecifier.getName();
		String key = null;
		if(currentFuncName != null && currentFuncName.length() != 0)
			key = currentFuncName + "_" + tagName;
		else
			key = "external_" + tagName;
		StructOrUnionTypeEntry structOrUnionTypeEntry = tagVsStructOrUnionTE.get(key);
		if(structOrUnionTypeEntry == null)
			structOrUnionTypeEntry = tagVsStructOrUnionTE.get("external_" + tagName);
		basicType = structOrUnionTypeEntry;
		break;
		case 11 : basicType = new EnumSpecTypeEntry();break;
		case 12 : basicType = new TypeDefNameTypeEntry();break;
		}
		Type type2 = translatingMediator.getLLVMType(basicType, isUnSigned, compilationContext);
		return type2;
	}

	private Translated translatePostfixExpr(PostfixExpr pfExpr){			
		Translated tePrevExpr = null;
		boolean prevTypeWasFuncCall= false;

		PrimaryExpr primaryExpr = null;

		SourceLocation location = new SourceLocation(pfExpr.getLineNum(), pfExpr.getPos());

		while(pfExpr != null){

			if(pfExpr.getType() == PostfixExpr.PRIMARY){
				// First primary expression, the result might or might not follow
				primaryExpr = pfExpr.getPrimaryExpr();
				tePrevExpr = translatePrimaryExpr(primaryExpr);
			}
			else if(pfExpr.getType() == PostfixExpr.FUNC_CALL){
				if(prevTypeWasFuncCall){
					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_CANNOT_HAVE_FUNCTION_CALL_HERE);
				}
				else{
					prevTypeWasFuncCall = true;
					ArgumentExpressionList ael = pfExpr.getArgumentExpressionList();

					if(tePrevExpr.getIRTree() != null){
						FunctionEntry funcEntry = (FunctionEntry)((PointerTypeEntry)tePrevExpr.getTypeEntry().getBasicType()).getBaseTypeEntry().getBasicType();
						IRTreeMemory funcMemory = (IRTreeMemory) tePrevExpr.getIRTree();
						tePrevExpr = translateFunctionCall(funcEntry, ael, location, funcMemory);
						// Function call can never be a LValue
						tePrevExpr.getTypeEntry().setIsLValue(false);
					}
				}				
			} 
			else if(pfExpr.getType() == PostfixExpr.DOT){	

				Stack<IRTree> irTreeStack = new Stack<IRTree>();

				prevTypeWasFuncCall = false;

				// Check if previous type is either structure or a union
				String postfixId = pfExpr.getIdentifier();

				// The previous expression must be a struct or union type
				TypeEntryWithAttributes tePrev = tePrevExpr.getTypeEntry();

				if(tePrev == null){
					errorHandler.addError(sourceFileName, location, postfixId + ": " , "", ErrorHandler.E_LEFT_SIDE_NOT_STRUCT_OR_UNION);
					return tePrevExpr;
				}	

				BasicType basicType = tePrev.getBasicType();

				if(basicType instanceof TypeDefNameTypeEntry){
					TypeDefNameTypeEntry typeDefNameTypeEntry = (TypeDefNameTypeEntry)basicType;
					tePrev = typeDefNameTypeEntry.getActualBasicType();
					basicType = tePrev.getBasicType();
				}

				if(!(basicType instanceof StructOrUnionTypeEntry)){
					errorHandler.addError(sourceFileName, location, postfixId + ": " , "", ErrorHandler.E_LEFT_SIDE_NOT_STRUCT_OR_UNION);
					return tePrevExpr;
				}

				// Analyze the struct or union 
				StructOrUnionTypeEntry prevStructOrUnion = (StructOrUnionTypeEntry) tePrev.getBasicType();
				StructType structTyp = (StructType) translatingMediator.getLLVMType(prevStructOrUnion, tePrev.isUnsigned(), compilationContext);
				if(setOfStructType.add(structTyp)){
					IRTreeType irTreeType = new IRTreeType(new Value(structTyp));
					irTreeStack.push(irTreeType);
				}
				TypeEntryWithAttributes memberType = prevStructOrUnion.getMemberType(postfixId);
				if(memberType == null || memberType.getBasicType() == null){
					errorHandler.addError(sourceFileName, location, postfixId + ":" , "", ErrorHandler.E_NOT_MEMBER_OF_STRUCT_OR_UNION);
					return tePrevExpr;
				}

				Vector<String> memberNames = prevStructOrUnion.getMemberNames();
				int index = memberNames.indexOf(postfixId);
				boolean isUnSigned = memberType.isUnsigned();

				Type memberIRType = translatingMediator.getLLVMType(memberType.getBasicType(), isUnSigned, compilationContext);


				// Create the index Tree
				IRTree indexTree = translatingMediator.createIndexTreeForStructure(index, compilationContext);
				IRTree structureRefTree = tePrevExpr.getIRTree();

				// Deferencing tree to built and set here
				if(prevStructOrUnion.isStruct()){
					// Create tree for a structure element reference
					IRTree structTree = translatingMediator.translateStructureReference(structureRefTree, indexTree, memberIRType, compilationContext);
					irTreeStack.push(structTree);
					IRTree resultantIrTree = translatingMediator.translateSeqStatement(irTreeStack);
					tePrevExpr.setIRTree(resultantIrTree);
				}
				else{
					// Union Type
					isUnSigned = tePrev.isUnsigned();
					StructType structType = (StructType)translatingMediator.getLLVMType(basicType, isUnSigned, compilationContext);
					Type type = structType.getTypeAtIndex(0);
					boolean isMatchedWithUnionType = (type == memberIRType) ? true : false;
					if(isMatchedWithUnionType){
						indexTree = translatingMediator.createIndexTreeForStructure(0, compilationContext);
						IRTree structTree = translatingMediator.translateStructureReference(structureRefTree, indexTree, memberIRType, compilationContext);
						tePrevExpr.setIRTree(structTree);
					}
					else{
						// Create a move instruction that will act as a bit cast operation
						PointerType pointerType1 = null;
						try {
							pointerType1 = Type.getPointerType(compilationContext, memberIRType, 0);
						} catch (TypeCreationException e) {
							e.printStackTrace();
							System.exit(-1);
						}
						Value value = new Value(pointerType1);
						IRTreeTempOrVar destExp = new IRTreeTempOrVar(value);

						IRTree src = null;

						if(structureRefTree instanceof IRTreeMemory){
							PointerType pointerType2 = null;
							try {
								pointerType2 = Type.getPointerType(compilationContext, structType, 0);
							} catch (TypeCreationException e) {
								e.printStackTrace();
								System.exit(-1);
							}
							Value value2 = new Value(pointerType2);
							String name = ((IRTreeMemory)structureRefTree).getMemory().getName();
							value2.setName(name);
							src = new IRTreeTempOrVar(value2);
						}
						else
							src = structureRefTree;

						IRTreeCast irTreeCast = new IRTreeCast(src, destExp);
						tePrevExpr.setIRTree(irTreeCast);
					}
				}

				memberType.setIsLValue(true);
				tePrevExpr.setEntry(memberType);
			}
			else if(pfExpr.getType() == PostfixExpr.DEREFERENCE){

				prevTypeWasFuncCall = false;

				// Check if previous type is either structure or a union
				String postfixId = pfExpr.getIdentifier();

				// The previous expression must be a POINTER to a struct or union type
				TypeEntryWithAttributes tePrev = tePrevExpr.getTypeEntry();
				if(tePrev == null){
					errorHandler.addError(sourceFileName, location, postfixId + ": " , "", ErrorHandler.E_LEFT_SIDE_NOT_POINTER_TO_STRUCT_OR_UNION);
					return tePrevExpr;
				}				
				BasicType btLeft = tePrev.getBasicType();
				if(!(btLeft instanceof PointerTypeEntry)){
					errorHandler.addError(sourceFileName, location, postfixId + ": " , "", ErrorHandler.E_LEFT_SIDE_NOT_POINTER_TO_STRUCT_OR_UNION);
					return tePrevExpr;
				}

				PointerTypeEntry ptrEntryLeft = (PointerTypeEntry) btLeft;
				TypeEntryWithAttributes tePtr = ptrEntryLeft.getBaseTypeEntry();
				BasicType basicType = tePtr.getBasicType();

				if(tePtr == null || !(basicType instanceof StructOrUnionTypeEntry)){
					errorHandler.addError(sourceFileName, location, postfixId + ": " , "", ErrorHandler.E_LEFT_SIDE_NOT_POINTER_TO_STRUCT_OR_UNION);
					return tePrevExpr;
				}							

				// Analyze the struct or union 
				StructOrUnionTypeEntry prevStructOrUnion = (StructOrUnionTypeEntry) tePtr.getBasicType();

				// This structure may Structure within a Structure in that case get the original structure
				String tag = prevStructOrUnion.getTag();
				StructOrUnionTypeEntry structOrUnionTypeEntry = tagVsStructOrUnionTE.get("external_" + tag);

				if(structOrUnionTypeEntry == null){// Then it might be a local Structure
					structOrUnionTypeEntry = tagVsStructOrUnionTE.get(currentFuncName + "_" + tag);
				}

				if(structOrUnionTypeEntry != null){
					prevStructOrUnion = structOrUnionTypeEntry;
				}

				TypeEntryWithAttributes memberType = prevStructOrUnion.getMemberType(postfixId);
				if(memberType == null || memberType.getBasicType() == null){
					errorHandler.addError(sourceFileName, location, postfixId + ":" , "", ErrorHandler.E_NOT_MEMBER_OF_STRUCT_OR_UNION);
					return tePrevExpr;
				}

				Vector<String> memberNames = prevStructOrUnion.getMemberNames();
				int index = memberNames.indexOf(postfixId);
				boolean isUnSigned = memberType.isUnsigned();

				Type memberIRType = translatingMediator.getLLVMType(memberType.getBasicType(), isUnSigned, compilationContext);

				// Create the index Tree
				IRTree indexTree = translatingMediator.createIndexTreeForStructure(index, compilationContext);

				// Deferencing tree to built and set here
				if(basicType instanceof StructOrUnionTypeEntry){
					// Create tree for a structure element reference
					IRTree structureRefTree = tePrevExpr.getIRTree();
					IRTree structTree = translatingMediator.translateStructureReference(structureRefTree, indexTree, memberIRType, compilationContext);
					tePrevExpr.setIRTree(structTree);
				}

				memberType.setIsLValue(true);
				tePrevExpr.setEntry(memberType);

			}
			else if(pfExpr.getType() == PostfixExpr.DECR || pfExpr.getType() == PostfixExpr.INCR){
				prevTypeWasFuncCall = false;

				String errMessage = ErrorHandler.E_CANNOT_DECREMENT_A_CONSTANT;

				int operatorType = pfExpr.getType();
				if(operatorType == PostfixExpr.INCR){
					errMessage = ErrorHandler.E_CANNOT_INCREMENT_A_CONSTANT;
				}

				if(tePrevExpr.getTypeEntry() != null){
					if(tePrevExpr.getTypeEntry().getBasicType() instanceof ArrayTypeEntry){
						// Cannot increment a constant
						errorHandler.addError(sourceFileName, location, "", "", errMessage);
					}
					else{  // No error, create the tree
						tePrevExpr.getTypeEntry().setIsLValue(false);
						IRTree assemTypeChild = tePrevExpr.getIRTree();

						// This will addressed at the end (after the entire assignment expression is analyzed)
						if(postfixOperativeExprs == null){
							postfixOperativeExprs = new HashMap<IRTreeExp, Integer>();
						}
						if(assemTypeChild instanceof IRTreeStatementList){
							IRTreeStatementList irTreeStatementList = (IRTreeStatementList)assemTypeChild;
							IRTreeStatementList leafIrTreeStatementList = translatingMediator.getLeafStatementList(irTreeStatementList);
							IRTreeStatement leafIrTreeStatement = leafIrTreeStatementList.getStatement();
							if(leafIrTreeStatement instanceof IRTreeExpressionStm){
								IRTreeExpressionStm expressionStm = (IRTreeExpressionStm)leafIrTreeStatement;
								postfixOperativeExprs.put(expressionStm.getExpressionTree(), operatorType);
							}
						}
						else
							postfixOperativeExprs.put((IRTreeExp)assemTypeChild, operatorType);

						tePrevExpr.setIRTree(assemTypeChild);
					}
				}
			}			
			else if(pfExpr.getType() == PostfixExpr.ARRAY_REF){
				
				if(tePrevExpr.getIRTree() == null)
					return tePrevExpr;

				prevTypeWasFuncCall = false;

				RootExpr arrayExpr = pfExpr.getArrayExpr();
				Translated teArrayExpr = translateExpr(arrayExpr);

				if(teArrayExpr.getTypeEntry() != null)
					analyzeArrayIndexExpr(teArrayExpr.getTypeEntry(), location);

				
				TypeEntryWithAttributes teWithAttrsPrev = tePrevExpr.getTypeEntry();
				BasicType btPrev = teWithAttrsPrev.getBasicType();

				if(btPrev instanceof TypeDefNameTypeEntry){
					TypeDefNameTypeEntry tdPrev = (TypeDefNameTypeEntry) btPrev;
					btPrev = tdPrev.getActualBasicType().getBasicType();
				}

				if(!(btPrev instanceof ArrayTypeEntry ||btPrev instanceof PointerTypeEntry)){
					// Trying to refer to an element when prev expression is neither nor a pointer nor an array
					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_CANNOT_ACCESS_ELEMENTS_OF_A_NON_ARRAY);
				}	
				else{
					TypeEntryWithAttributes teWithAttrsNew = new TypeEntryWithAttributes();
					if(btPrev instanceof ArrayTypeEntry){
						ArrayTypeEntry ateEntry = (ArrayTypeEntry) btPrev;
						teWithAttrsNew = ateEntry.getBaseTypeEntry();

						// TODO Create tree for a array element reference
						IRTree arrayRefTree = tePrevExpr.getIRTree();
						IRTree indexTree = teArrayExpr.getIRTree();
						IRTree arrTree = translatingMediator.translateArrayReference(arrayRefTree, indexTree, compilationContext);
						tePrevExpr.setIRTree(arrTree);
					}
					else{
						// Must be PointerTypeEntry
						PointerTypeEntry pteEntry = (PointerTypeEntry) btPrev;
						teWithAttrsNew = pteEntry.getBaseTypeEntry();
						// TODO Create tree for a reference at a location
						IRTree arrayRefTree = tePrevExpr.getIRTree();
						IRTree indexTree = teArrayExpr.getIRTree();
						IRTree arrTree = translatingMediator.translateArrayReference(arrayRefTree, indexTree, compilationContext);
						tePrevExpr.setIRTree(arrTree);
					}
					teWithAttrsNew.setIsLValue(true);   // Is addressable, present in memory

					tePrevExpr.setEntry(teWithAttrsNew);
				}				
			}

			pfExpr = pfExpr.getPostfixExpr();
		}

		if(tePrevExpr == null)
			tePrevExpr = new Translated();  // Return empty translated expression and type

		return tePrevExpr;
	}

	private Translated translateFunctionCall(FunctionEntry funcEntry, ArgumentExpressionList ael, 
			SourceLocation location, IRTreeMemory funcMemory){

		String funcName = funcEntry.getName();
		Vector<VariableEntry> formals = funcEntry.getFormals();
		Stack<IRTree> argsStack = new Stack<IRTree>();
		Vector<IRTree> translatedParamAssemTypes = new Vector<IRTree>();
		List<Type> listOfFormalTypes = new ArrayList<Type>();
		// Verify formals against argument expression list
		if(formals == null || formals.size() == 0){
			if(ael != null){
				while(ael != null){
					AssignmentExpr assgnExpr = ael.getAssignmentExpr();
					translateAssignmentExpr(assgnExpr);

					ael = ael.getArgumentExpressionList();
				}
				errorHandler.addError(sourceFileName, location, funcEntry.getName(), "", 
						ErrorHandler.E_PARAMETERS_PASSED_TO_FUNC_ACCEPTING_VOID);
			}
		}
		else{
			listOfFormalTypes = new ArrayList<Type>();
			if(ael == null){
				errorHandler.addError(sourceFileName, location, funcEntry.getName(), "", 
						ErrorHandler.E_NO_PARAMETERS_PASSED_TO_FUNC_ACCEPTING_NON_VOID_ARGS);
			}
			else{
				int count = 0;
				while(ael != null){
					AssignmentExpr assgnExpr = ael.getAssignmentExpr();
					Translated teParam = translateAssignmentExpr(assgnExpr);

					if(count < formals.size()){
						VariableEntry formalEntry = formals.elementAt(count);
						TypeEntryWithAttributes typeEntryWithAttributes = formalEntry.getType();
						BasicType basicType = typeEntryWithAttributes.getBasicType();
						boolean isUnSigned = typeEntryWithAttributes.isUnsigned();
						Type type = null;
						if(funcName != null && funcName.equals("malloc"))
							type = Type.getInt64Type(compilationContext, !isUnSigned);
						else
							type = translatingMediator.getLLVMType(basicType, isUnSigned, compilationContext);

						if(type.isArrayType()){
							ArrayType arrayType = (ArrayType)type;
							Type containedType = arrayType.getContainedType();
							PointerType pointerType = null;
							try {
								pointerType = Type.getPointerType(compilationContext, containedType, 0);
							} catch (TypeCreationException e) {
								e.printStackTrace();
								System.exit(-1);
							}
							type = pointerType;
						}

						listOfFormalTypes.add(type);
						checkAssignmentCompatibility(formalEntry.getType(), teParam.getTypeEntry(), location, 
								Semantic.ASSIGNMENT_IN_PARAMETER_PASSING, AssignmentOperator.ASSIGN,
								funcEntry.getName(), count+1);									

					}
					else{
						// this means this function takes variable arguments
						if(!funcEntry.isEndsWithEllipses())
							errorHandler.addError(sourceFileName, location, funcEntry.getName(), "", ErrorHandler.E_WRONG_NUMBER_OF_PARAMETERS_PASSED_TO_FUNC);
					}

					if(!errorHandler.isFoundStrictError()){
						
						IRTree irTree = teParam.getIRTree();
						TypeEntryWithAttributes typeEntryWithAttributes = teParam.getTypeEntry();
						Type llvmType = translatingMediator.getLLVMType(typeEntryWithAttributes.getBasicType(), typeEntryWithAttributes.isUnsigned(), compilationContext);
						if(llvmType.isFloatType() && funcName != null && funcName.equals("printf")){
							Value value = new Value(Type.getDoubleType(compilationContext));
							IRTreeTempOrVar destTree = new IRTreeTempOrVar(value );
							irTree = new IRTreeCast(irTree, destTree);
						}
						
						argsStack.push(irTree);
						translatedParamAssemTypes.add(teParam.getIRTree());
					}

					ael = ael.getArgumentExpressionList();
					count++;
				}

				// Check if lesser parameters have been passed than expected
				if(count < formals.size()){
					errorHandler.addError(sourceFileName, location, funcEntry.getName(), "", 
							ErrorHandler.E_WRONG_NUMBER_OF_PARAMETERS_PASSED_TO_FUNC);
				}							
			}
		}

		IRTree callTree = null;
		TypeEntryWithAttributes teRet = funcEntry.getReturnType();
		boolean isUnSigned = teRet.isUnsigned();
		Type returnType = null;
		if(teRet.getBasicType() instanceof VoidTypeEntry){
			returnType = Type.getVoidType(compilationContext);
		}
		else{
			BasicType basicTypeOfRetType = teRet.getBasicType();
			returnType = translatingMediator.getLLVMType(basicTypeOfRetType, isUnSigned, compilationContext);
		}

		if(!errorHandler.isFoundStrictError()){   // No error, translate to tree
			funcName = funcEntry.getName();
			boolean hasEllipses = funcEntry.isEndsWithEllipses();
			callTree = translatingMediator.translateCallExp(funcName, argsStack, returnType, listOfFormalTypes, hasEllipses, compilationContext, funcMemory);
		}

		Translated retValue = new Translated();
		funcEntry.getReturnType().setIsLValue(false);
		retValue.setEntry(funcEntry.getReturnType());
		retValue.setIRTree(callTree);

		return retValue;

	}


	private Translated translatePrimaryExpr(PrimaryExpr primaryExpr){

		Translated retValue = new Translated();

		TypeEntryWithAttributes teWithAttrs = new TypeEntryWithAttributes();

		int type = primaryExpr.getType();
		if(type == PrimaryExpr.CONSTANT_TYPE){
			IRTree constTree = null;
			Constant cst = primaryExpr.getConstant();

			String val = null;
			int cstType = cst.getType();
			boolean isUnSigned = teWithAttrs.isUnsigned();

			if(cstType == Constant.INT_CONST) { // Int constant
				val = cst.getIntConstant();
				teWithAttrs.setBasicType(IntTypeEntry.getInstance());	
				constTree = translatingMediator.translateConstant(val, TranslatingMediator.CONSTANT_INT,
						compilationContext, !isUnSigned);
			}
			else if(cstType == Constant.CHAR_CONST){
				val = cst.getCharConstant();
				teWithAttrs.setBasicType(CharTypeEntry.getInstance());
				constTree = translatingMediator.translateConstant(val, TranslatingMediator.CONSTANT_CHAR, compilationContext, !isUnSigned);
			}
			else  { // Floating point constant - float or double				
				val = cst.getFloatingConstant();
				if(val.endsWith("f") || val.endsWith("F")){ // Must be a float	
					String floatVal = val;
					teWithAttrs.setBasicType(FloatTypeEntry.getInstance());
					int endIndex = val.length() - 1;
					val = val.substring(0, endIndex);
					
					if(!val.contains(".")){
						errorHandler.addError(sourceFileName, new SourceLocation(primaryExpr.getLineNum(), primaryExpr.getPos()), floatVal, "", ErrorHandler.E_INVALID_DIGIT_IN_DECIMAL_CONSTANT); 
						return retValue;
					}
					
					val = LLVMUtility.convertIntoLLVMFloatingPointValue(val);
					constTree = translatingMediator.translateConstant(val, TranslatingMediator.CONSTANT_FLOAT,
							compilationContext, !isUnSigned);
				}
				else{ 
					teWithAttrs.setBasicType(DoubleTypeEntry.getInstance());
					constTree = translatingMediator.translateConstant(val, 
							TranslatingMediator.CONSTANT_DOUBLE, compilationContext, !isUnSigned);
				}
			}

			teWithAttrs.setLiteralValue(val);
			teWithAttrs.setIsLValue(false);
			teWithAttrs.setConst(true);
			retValue.setEntry(teWithAttrs);
			retValue.setIRTree(constTree);				
		}
		else if(type == PrimaryExpr.IDENTIFIER_TYPE){
			String identifier = primaryExpr.getIdentifier();
			Symbol idSym = Symbol.symbol(identifier);
			VariableOrFunctionEntry identifierTypeEntry = (VariableOrFunctionEntry)
					environments.getInstanceVariableTable().get(idSym);			

			if(identifierTypeEntry == null){
				errorHandler.addError(sourceFileName, new SourceLocation(primaryExpr.getLineNum(),
						primaryExpr.getPos()), idSym.toString(), "", ErrorHandler.E_NOT_DEFINED);
			}
			else{ // Identifier has been declared
				IRTree varTree = null;
				if(identifierTypeEntry.getCategory() == VariableOrFunctionEntry.VARIABLE){
					VariableEntry varEntry = (VariableEntry) identifierTypeEntry;
					teWithAttrs = varEntry.getType();
					BasicType bt = teWithAttrs.getBasicType();

					if(bt instanceof ArrayTypeEntry || teWithAttrs.isEnumConstant())
						teWithAttrs.setIsLValue(false);					
					else
						teWithAttrs.setIsLValue(true);

					retValue.setEntry(teWithAttrs);
					retValue.setVariableName(identifier);


					Value val = varEntry.getValue();
					boolean isParam = varEntry.isParamVar();

					if(val != null)
						varTree = translatingMediator.translateValue(val, isParam);
				}
				else{  // This is a function reference, return a pointer to it
					FunctionEntry funcEntry = (FunctionEntry) identifierTypeEntry;
					teWithAttrs = new TypeEntryWithAttributes();
					teWithAttrs.setIsLValue(true);
					retValue.setEntry(teWithAttrs);

					PointerTypeEntry ptrEntry = new PointerTypeEntry();					
					TypeEntryWithAttributes ptrBase = new TypeEntryWithAttributes();
					ptrBase.setBasicType(funcEntry);		
					ptrEntry.setBaseTypeEntry(ptrBase);

					teWithAttrs.setBasicType(ptrEntry);  

					Vector<VariableEntry> variableEntries = funcEntry.getFormals();
					TypeEntryWithAttributes returnAttributes = funcEntry.getReturnType();
					boolean isVarArgs = funcEntry.isEndsWithEllipses();
					Type returnType = translatingMediator.getLLVMType(returnAttributes.getBasicType(), returnAttributes.isUnsigned(), compilationContext);
					Vector<Type> paramTypes = new Vector<Type>();

					if(variableEntries != null){
						for(VariableEntry variableEntry : variableEntries){
							TypeEntryWithAttributes typeEntryWithAttributes = variableEntry.getType();
							Type type2 = translatingMediator.getLLVMType(typeEntryWithAttributes.getBasicType(), typeEntryWithAttributes.isUnsigned(), compilationContext);
							paramTypes.add(type2);
						}
					}

					FunctionType functionType = null;
					PointerType pointerType = null;
					try {
						functionType = Type.getFunctionType(compilationContext, returnType, isVarArgs, paramTypes );
						pointerType = Type.getPointerType(compilationContext, functionType, 0);
					} catch (TypeCreationException e) {
						e.printStackTrace();
						System.exit(-1);
					}

					Value funcPtr = new Value(pointerType);
					funcPtr.setName(funcEntry.getName());
					varTree = new IRTreeMemory(funcPtr);
					// TODO varTree to get location of function label here
				}
				retValue.setIRTree(varTree);				
			}			
		}
		else if(type == PrimaryExpr.STRING_TYPE){
			String str = primaryExpr.getString();			
			teWithAttrs.setBasicType(StringTypeEntry.getInstance());
			teWithAttrs.setIsLValue(false);
			teWithAttrs.setLiteralValue(str);
			retValue.setEntry(teWithAttrs);

			IRTree stringLiteralTree = translatingMediator.translateStringConstant(str, compilationContext, translatingMediator);
			retValue.setIRTree(stringLiteralTree);
		}
		else {   // this primary expression is of the form (expression)
			RootExpr rootExpr = primaryExpr.getExpr();
			return translateExpr(rootExpr);
		}

		return retValue;
	}

	// UTILITY FUNCTIONS

	private void analyzeArraySizeExpr(TypeEntryWithAttributes val, SourceLocation location){
		if(val != null){
			BasicType valBasicType = val.getBasicType();
			if((valBasicType == IntTypeEntry.getInstance() || valBasicType == ShortTypeEntry.getInstance()
					|| valBasicType == CharTypeEntry.getInstance() || valBasicType == LongTypeEntry.getInstance())){
				if(val.getLiteralValue() == null){ // Is an expression
					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_ARRAY_SIZE_NOT_CONSTANT);
				}
			}
			else{
				errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_ARRAY_SIZE_SPECIFIER_IS_INVALID);
			}
		}
	}

	private void analyzeArrayIndexExpr(TypeEntryWithAttributes val, SourceLocation location){

		BasicType valBasicType = val.getBasicType();
		if(!(valBasicType instanceof IntTypeEntry) && !(valBasicType instanceof ShortTypeEntry)){
			errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_ARRAY_SUBSCRIPT_NOT_INTEGER);
		}
	}

	// TODO Complete this function
	private BasicType getTranslatedExprTypePostOperation(TypeEntryWithAttributes teFirstType, 
			TypeEntryWithAttributes teSecondType, SourceLocation location, String operatorType){		

		BasicType firstType = null;
		BasicType secondType = null;

		// If either type is not defined, the error message would have been already set, so ignore and continue
		if(teFirstType != null) {
			firstType = teFirstType.getBasicType();
		}
		if(teSecondType != null) {
			secondType = teSecondType.getBasicType();			
		}

		// If both are null values, return an Integer type
		if(firstType == null && secondType == null){
			return IntTypeEntry.getInstance();
		}

		// If any one of them is a struct or union, flag an error
		if(firstType instanceof StructOrUnionTypeEntry || secondType instanceof StructOrUnionTypeEntry){
			errorHandler.addError(sourceFileName, location, operatorType.toString() + ":","",
					ErrorHandler.E_INVALID_OPERATOR_ON_STRUCT_UNION);
			return IntTypeEntry.getInstance();
		}

		if(firstType instanceof PointerTypeEntry){  // First operand is pointer instance
			if(secondType instanceof PointerTypeEntry){ // Second operand is also pointer instance
				if(!operatorType.equals(SUBTRACT)){ // Not a subtraction
					if(!(operatorType.equals(LESSER_THAN) || operatorType.equals(LESSER_THAN_OR_EQUAL_TO)
							|| operatorType.equals(GREATER_THAN) || operatorType.equals(GREATER_THAN_OR_EQUAL_TO)
							|| operatorType.equals(EQUALS) || operatorType.equals(NOT_EQUALS)  )){
						// Not a relational operator either, flag an error
						errorHandler.addError(sourceFileName, location, operatorType, "", ErrorHandler.E_INVALID_OPERATOR_ON_POINTERS);
						return firstType;
					}	
					else{
						// Is a relational operator, check if they are compatible
						BasicType btPteFirst = ((PointerTypeEntry) firstType).getBaseTypeEntry().getBasicType();
						BasicType btPteSecond = ((PointerTypeEntry) secondType).getBaseTypeEntry().getBasicType();
						if(!btPteFirst.equals(btPteSecond)){
							// Not same type of pointers, flag an error or warning if the rhs is not a void type							
							errorHandler.addError(sourceFileName, location, operatorType,"",
									ErrorHandler.E_COMPARING_INCOMPATIBLE_POINTER_TYPES);
						}	
						return IntTypeEntry.getInstance();
					}
				}
				else{
					// Subtracting two pointers, check if the types pointed to are compatible
					PointerTypeEntry pteFirst = (PointerTypeEntry) firstType;
					PointerTypeEntry pteSecond = (PointerTypeEntry) secondType;

					BasicType btPteFirst = pteFirst.getBaseTypeEntry().getBasicType();
					BasicType btPteSecond = pteSecond.getBaseTypeEntry().getBasicType();

					if(!btPteFirst.equals(btPteSecond)){
						errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_SUBTRACTING_INCOMPATIBLE_POINTERS);
					}

					return IntTypeEntry.getInstance();
				}				
			}
			else if(secondType == IntTypeEntry.getInstance() || secondType == CharTypeEntry.getInstance() 
					|| secondType == ShortTypeEntry.getInstance()  || secondType == LongTypeEntry.getInstance() ){
				// Second type is an integer type, check compatibilities based on the operator
				if(!(operatorType.equals(ADD) || operatorType.equals(SUBTRACT))){					
					// Not an addition or a subtraction, check for relational operator
					if(!(operatorType.equals(LESSER_THAN) || operatorType.equals(LESSER_THAN_OR_EQUAL_TO)
							|| operatorType.equals(GREATER_THAN) || operatorType.equals(GREATER_THAN_OR_EQUAL_TO)
							|| operatorType.equals(EQUALS) || operatorType.equals(NOT_EQUALS)  )){
						// Not a relational operator either, flag an error
						errorHandler.addError(sourceFileName, location, operatorType, "", ErrorHandler.E_INVALID_OPERATOR_ON_POINTER_AND_INTEGER);
					}
					else{
						// Only zero can be compared to a pointer
						String litValue = teSecondType.getLiteralValue();
						if(litValue == null){
							errorHandler.addError(sourceFileName, location, operatorType,"",
									ErrorHandler.E_INVALID_COMPARISON_OF_POINTER_AND_INTEGER);
						}
						else{
							if(!(litValue.equals("0") || litValue.equals("00") || litValue.equals("0x0"))){
								errorHandler.addError(sourceFileName, location, operatorType,"",
										ErrorHandler.E_INVALID_COMPARISON_OF_POINTER_AND_INTEGER);
							}
						}
					}					
					return firstType;
				}
			}
		}
		else if(firstType instanceof ArrayTypeEntry){
			if(secondType == IntTypeEntry.getInstance()){
				return firstType;
			}
		}
		if(firstType == IntTypeEntry.getInstance() || (firstType instanceof EnumSpecTypeEntry)){
			if(secondType instanceof ArrayTypeEntry){
				return secondType;
			}
			else if(secondType instanceof PointerTypeEntry){
				return secondType;
			}
		}
		if(firstType == ShortTypeEntry.getInstance()){
			if(secondType instanceof ArrayTypeEntry){
				return secondType;
			}
			else if(secondType instanceof PointerTypeEntry){
				return secondType;
			}
		}
		// Check which is "wider"
		if(secondType == DoubleTypeEntry.getInstance()){
			return secondType;
		}
		else if(secondType instanceof EnumSpecTypeEntry)
			return IntTypeEntry.getInstance();
		
		if(firstType instanceof EnumSpecTypeEntry)
			return IntTypeEntry.getInstance();
		
		return firstType;
	}

	/**
	 *  Checks if the two types are compatible, i.e., if the rhs can be assigned to the lhs correctly.
	 *  If the two types are incompatible, a warning or an error is noted, depending on the severity of 
	 *  the incompatibility.
	 */

	private void checkAssignmentCompatibility(TypeEntryWithAttributes lhsTeWithAttrs,  
			TypeEntryWithAttributes rhsTeWithAttrs, SourceLocation location, short context, int operatorType,
			String functionName, int paramNum){		

		if(lhsTeWithAttrs == null || rhsTeWithAttrs == null)  // Error that has been handled already, just return
			return;

		ErrorHandler errorHandler = ErrorHandler.getInstance();

		BasicType lhsTypeEntry = lhsTeWithAttrs.getBasicType();
		BasicType rhsTypeEntry = rhsTeWithAttrs.getBasicType();		

		// If lhs or rhs is a typedef, get the actual basic type
		if(lhsTypeEntry instanceof TypeDefNameTypeEntry){
			TypeDefNameTypeEntry tdEntry = (TypeDefNameTypeEntry) lhsTypeEntry;
			TypeEntryWithAttributes basicTypeOfTypeDef = tdEntry.getActualBasicType();
			basicTypeOfTypeDef.copy(lhsTeWithAttrs, true);
			lhsTeWithAttrs = basicTypeOfTypeDef;
			lhsTypeEntry = lhsTeWithAttrs.getBasicType();
			lhsTeWithAttrs.setIsLValue(true);
		}
		if(rhsTypeEntry instanceof TypeDefNameTypeEntry){
			TypeDefNameTypeEntry tdEntry = (TypeDefNameTypeEntry) rhsTypeEntry;
			rhsTeWithAttrs = tdEntry.getActualBasicType();
			rhsTypeEntry = rhsTeWithAttrs.getBasicType();
		}

		if(context == Semantic.ASSIGNMENT_IN_ASSIGNMENT_EXPR){
			// Left side of assignment must be a modifiable l-value
			if(!lhsTeWithAttrs.isLValue()){
				errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_LEFT_SIDE_IS_NOT_LVALUE);
			}

			if(lhsTeWithAttrs.isConst()){
				errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_LEFT_SIDE_IS_CONSTANT);
			}

			// Neither lhs nor rhs can be void
			if(lhsTeWithAttrs.isVoid()){
				errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_LEFT_SIDE_IS_VOID);
			}
			if(rhsTeWithAttrs.isVoid()){
				errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_RIGHT_SIDE_IS_VOID);
			}			
		}
		else if(context == Semantic.ASSIGNMENT_IN_DECLR_INIT_EXPR){
			// Left hand side of assignment can be an  array type while right-side can be an array 
			// initializer or a string if it is a char array type
			if(lhsTypeEntry instanceof ArrayTypeEntry){
				ArrayTypeEntry arrTeLhs = (ArrayTypeEntry) lhsTypeEntry;

				if(rhsTypeEntry instanceof ArrayTypeEntry){
					ArrayTypeEntry arrTeRhs = (ArrayTypeEntry) rhsTypeEntry;
					// Rhs is also an array, check the compatibility
					checkArrayTypesInDeclInit(arrTeLhs, arrTeRhs, location);										
				}
				else if(rhsTypeEntry == StringTypeEntry.getInstance()){
					// Rhs is a string, check if the base type of the left side is a char
					if(arrTeLhs.getBaseTypeEntry().getBasicType() != CharTypeEntry.getInstance()){
						errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_INVALID_INITIALIZATION);
					}
					else{
						// Check possible overflow (array size would have already been analyzed, so don't do any checks here
						// for validity of array size)
						TypeEntryWithAttributes teDim = arrTeLhs.getDimension();
						if(teDim != null){
							String arraySizeLit = teDim.getLiteralValue();
							if(arraySizeLit != null){
								try{
									int arrSize = Integer.parseInt(arraySizeLit);
									if(arrSize < rhsTeWithAttrs.getLiteralValue().length()){
										errorHandler.addError(sourceFileName, location, rhsTeWithAttrs.getLiteralValue(), 
												"", ErrorHandler.E_STRING_ARRAY_BOUNDS_OVERFLOW);
									}
								}
								catch(NumberFormatException nfe){}
							}
						}
					}
				}					
			}
		}

		String literalValue = rhsTeWithAttrs.getLiteralValue();  // Get the literal value

		if(lhsTypeEntry == rhsTypeEntry) {
			if(literalValue == null){
				//TODO check other attributes also (like auto, const, etc)
				return;
			}

			// The rhs is a literal, check if there is any overflow of values
			checkTypeAgainstLiteralValue(lhsTeWithAttrs, literalValue, location);
			return;
		}

		// The lhs and rhs are two different types; check compatibility if narrowing conversion is possible
		String additionalInfoMsg = "";
		if(context == Semantic.ASSIGNMENT_IN_FUNC_RETURN_CONTEXT)
			additionalInfoMsg = ErrorHandler.AI_INCOMPATIBLE_TYPE_IN_RETURN;

		if(lhsTypeEntry == CharTypeEntry.getInstance()){  // lhs is of type char, check rhs

			if(literalValue != null ){  // rhs is a const value, check if it ok to assign to a char
				if(rhsTypeEntry == DoubleTypeEntry.getInstance() || rhsTypeEntry == FloatTypeEntry.getInstance()){					
					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_CONST_LITERAL_OVERFLOW);
					return;
				}
				else if(rhsTypeEntry instanceof StringTypeEntry){
					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_ASSIGNING_A_STRING_TO_AN_INTEGER_TYPE);
					return;
				}

				if(!lhsTeWithAttrs.isUnsigned() && !Constant.canBeSignedChar(literalValue))
					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_CONST_LITERAL_OVERFLOW);
				if(lhsTeWithAttrs.isUnsigned() && !Constant.canBeUnSignedChar(literalValue))
					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_CONST_LITERAL_OVERFLOW);				
			}
			else{
				if(rhsTypeEntry == ShortTypeEntry.getInstance()){
					errorHandler.addError(sourceFileName, location, additionalInfoMsg,  "", ErrorHandler.E_SHORT_TO_CHAR_NARROWING);
				}
				else if(rhsTypeEntry == FloatTypeEntry.getInstance()){  
					errorHandler.addError(sourceFileName, location, additionalInfoMsg,  "", ErrorHandler.E_FLOAT_TO_CHAR_NARROWING);
				}
				else if(rhsTypeEntry == DoubleTypeEntry.getInstance()){  
					errorHandler.addError(sourceFileName, location, additionalInfoMsg,  "", ErrorHandler.E_DOUBLE_TO_CHAR_NARROWING);
				}
				else if(rhsTypeEntry == LongTypeEntry.getInstance()){  
					errorHandler.addError(sourceFileName, location, additionalInfoMsg,  "", ErrorHandler.E_LONG_TO_CHAR_NARROWING);
				}
				else if(rhsTypeEntry instanceof StructOrUnionTypeEntry){
					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES);
				}				
				else if(rhsTypeEntry instanceof PointerTypeEntry || rhsTypeEntry instanceof ArrayTypeEntry){ // Rhs type is a pointer type
					errorHandler.addError(sourceFileName, location, additionalInfoMsg,  "", ErrorHandler.E_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE);
				}
			}			
		}
		else if(lhsTypeEntry == ShortTypeEntry.getInstance()){  // lhs is of type short, check rhs
			if(literalValue != null ){  // rhs is a const value, check if it ok to assign to a short				
				if(rhsTypeEntry == DoubleTypeEntry.getInstance() || rhsTypeEntry == FloatTypeEntry.getInstance()){
					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_CONST_LITERAL_OVERFLOW);
					return ;
				}

				if(!lhsTeWithAttrs.isUnsigned() && !Constant.canBeSignedShort(literalValue))
					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_CONST_LITERAL_OVERFLOW);
				if(lhsTeWithAttrs.isUnsigned() && !Constant.canBeUnSignedShort(literalValue))
					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_CONST_LITERAL_OVERFLOW);				
			}
			else{
				if(rhsTypeEntry == FloatTypeEntry.getInstance()){  
					errorHandler.addError(sourceFileName, location, additionalInfoMsg,  "", ErrorHandler.E_FLOAT_TO_SHORT_NARROWING);
				}
				if(rhsTypeEntry == DoubleTypeEntry.getInstance()){  
					errorHandler.addError(sourceFileName, location, additionalInfoMsg,  "", ErrorHandler.E_DOUBLE_TO_SHORT_NARROWING);
				}
				if(rhsTypeEntry == LongTypeEntry.getInstance()){  
					errorHandler.addError(sourceFileName, location, additionalInfoMsg,  "", ErrorHandler.E_LONG_TO_SHORT_NARROWING);
				}
				else if(rhsTypeEntry instanceof StructOrUnionTypeEntry){
					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES);
				}				
				else if(rhsTypeEntry instanceof PointerTypeEntry || rhsTypeEntry instanceof ArrayTypeEntry){ // Rhs type is a pointer type
					errorHandler.addError(sourceFileName, location, additionalInfoMsg,  "", ErrorHandler.E_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE);
				}
			}						
		}
		else if(lhsTypeEntry == IntTypeEntry.getInstance() || lhsTypeEntry instanceof EnumSpecTypeEntry){ // lhs is of type int, check rhs	
			if(literalValue != null ){  // rhs is a const value, check if it ok to assign to a int
				if(rhsTypeEntry == DoubleTypeEntry.getInstance() || rhsTypeEntry == FloatTypeEntry.getInstance()){					
					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_CONST_LITERAL_OVERFLOW);
					return ;
				}
				else if(rhsTypeEntry == StringTypeEntry.getInstance() || rhsTypeEntry instanceof PointerTypeEntry){
					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE);
					return ;
				}

				if(!lhsTeWithAttrs.isUnsigned() && !Constant.canBeSignedInt(literalValue))
					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_CONST_LITERAL_OVERFLOW);
				if(lhsTeWithAttrs.isUnsigned() && !Constant.canBeUnSignedInt(literalValue))
					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_CONST_LITERAL_OVERFLOW);				
			}
			else{
				if(rhsTypeEntry == DoubleTypeEntry.getInstance()){  
					errorHandler.addError(sourceFileName, location, additionalInfoMsg,  "", ErrorHandler.E_DOUBLE_TO_INT_NARROWING);
				}
				if(rhsTypeEntry == FloatTypeEntry.getInstance()){  
					errorHandler.addError(sourceFileName, location, additionalInfoMsg, "", ErrorHandler.E_FLOAT_TO_INT_NARROWING);
				}
				else if(rhsTypeEntry instanceof PointerTypeEntry){ // Rhs type is a pointer type
					errorHandler.addError(sourceFileName, location, additionalInfoMsg,  "", ErrorHandler.E_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE);
				}
				else if(rhsTypeEntry instanceof ArrayTypeEntry){ // Rhs type is an array address
					errorHandler.addError(sourceFileName, location, additionalInfoMsg,  "", ErrorHandler.E_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE);
				}
				else if(rhsTypeEntry instanceof StructOrUnionTypeEntry){ // Rhs type is a struct or union
					errorHandler.addError(sourceFileName, location, additionalInfoMsg,  "", ErrorHandler.E_ASSIGNING_STRUCT_OR_UNION_TO_AN_INTEGER_TYPE);
				}
			}			
		}
		else if(lhsTypeEntry == LongTypeEntry.getInstance()){ // lhs is of type int, check rhs	
			if(literalValue != null ){  // rhs is a const value, check if it ok to assign to a long

				if(rhsTypeEntry == DoubleTypeEntry.getInstance() || rhsTypeEntry == FloatTypeEntry.getInstance()){
					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_CONST_LITERAL_OVERFLOW);
					return ;
				}
				else if(rhsTypeEntry == StringTypeEntry.getInstance()){
					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE);
					return ;					
				}

				if(!lhsTeWithAttrs.isUnsigned() && !Constant.canBeSignedLong(literalValue))
					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_CONST_LITERAL_OVERFLOW);
				if(lhsTeWithAttrs.isUnsigned() && !Constant.canBeUnSignedLong(literalValue))
					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_CONST_LITERAL_OVERFLOW);				
			}
			else{
				if(rhsTypeEntry == DoubleTypeEntry.getInstance()){  
					errorHandler.addError(sourceFileName, location, additionalInfoMsg,  "", ErrorHandler.E_DOUBLE_TO_LONG_NARROWING);
				}
				if(rhsTypeEntry == FloatTypeEntry.getInstance()){  
					errorHandler.addError(sourceFileName, location, additionalInfoMsg,  "", ErrorHandler.E_FLOAT_TO_LONG_NARROWING);
				}
				else if(rhsTypeEntry instanceof PointerTypeEntry){ // Rhs type is a pointer type
					errorHandler.addError(sourceFileName, location, additionalInfoMsg,  "", ErrorHandler.E_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE);
				}
				else if(rhsTypeEntry instanceof ArrayTypeEntry){ // Rhs type is an array address
					errorHandler.addError(sourceFileName, location, additionalInfoMsg,  "", ErrorHandler.E_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE);
				}
				else if(rhsTypeEntry instanceof StructOrUnionTypeEntry){ // Rhs type is a struct or union
					errorHandler.addError(sourceFileName, location, additionalInfoMsg,  "", ErrorHandler.E_ASSIGNING_STRUCT_OR_UNION_TO_AN_INTEGER_TYPE);
				}
			}		
		}
		else if(lhsTypeEntry == FloatTypeEntry.getInstance()){// lhs is of type float, check rhs

			if(rhsTypeEntry == DoubleTypeEntry.getInstance()){  
				errorHandler.addError(sourceFileName, location, additionalInfoMsg,  "", ErrorHandler.E_DOUBLE_TO_FLOAT_NARROWING);
			}
			if(rhsTypeEntry == LongTypeEntry.getInstance()){  
				errorHandler.addError(sourceFileName, location, additionalInfoMsg,  "", ErrorHandler.E_LONG_TO_FLOAT_NARROWING);
			}
			if(rhsTypeEntry == IntTypeEntry.getInstance() || rhsTypeEntry instanceof EnumSpecTypeEntry){  
				errorHandler.addError(sourceFileName, location, additionalInfoMsg,  "", ErrorHandler.E_INT_TO_FLOAT_NARROWING);
			}
			else if(rhsTypeEntry instanceof PointerTypeEntry){ // Rhs type is a pointer type
				errorHandler.addError(sourceFileName, location, additionalInfoMsg,  "", ErrorHandler.E_ASSIGNING_POINTER_TO_A_FLOATING_POINT_TYPE);
			}
			else if(rhsTypeEntry instanceof ArrayTypeEntry){ // Rhs type is an array address
				errorHandler.addError(sourceFileName, location, additionalInfoMsg,  "", ErrorHandler.E_ASSIGNING_POINTER_TO_A_FLOATING_POINT_TYPE);
			}
			else if(rhsTypeEntry instanceof StructOrUnionTypeEntry){ // Rhs type is a struct or union
				errorHandler.addError(sourceFileName, location, additionalInfoMsg,  "", ErrorHandler.E_ASSIGNING_STRUCT_OR_UNION_TO_A_FLOATING_POINT_TYPE);
			}
			else if(rhsTypeEntry == StringTypeEntry.getInstance()){
				errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES);
				return ;					
			}
		}
		else if(lhsTypeEntry == DoubleTypeEntry.getInstance()){ // lhs is of type double, check rhs
			if(rhsTypeEntry instanceof PointerTypeEntry || 
					rhsTypeEntry instanceof ArrayTypeEntry || rhsTypeEntry instanceof StringTypeEntry){ // Rhs type is a pointer type
				errorHandler.addError(sourceFileName, location, additionalInfoMsg, "" , ErrorHandler.E_ASSIGNING_POINTER_TO_A_FLOATING_POINT_TYPE);
			}
			else if(rhsTypeEntry instanceof StructOrUnionTypeEntry){ // Rhs type is a struct or union
				errorHandler.addError(sourceFileName, location, additionalInfoMsg,  "", ErrorHandler.E_ASSIGNING_STRUCT_OR_UNION_TO_A_DOUBLE_TYPE);
			}
		}
		else if(lhsTypeEntry instanceof PointerTypeEntry){ // lhs is of pointer type, check rhs			
			if(rhsTypeEntry == CharTypeEntry.getInstance() || rhsTypeEntry == ShortTypeEntry.getInstance() ||
					rhsTypeEntry == IntTypeEntry.getInstance() || rhsTypeEntry == LongTypeEntry.getInstance()
					|| rhsTypeEntry instanceof EnumSpecTypeEntry){ 
				// RHS is an integer type
				if(operatorType == AssignmentOperator.ASSIGN){
					// Only zero can be assigned to a pointer
					String litValue = rhsTeWithAttrs.getLiteralValue();					
					if(litValue == null){
						errorHandler.addError(sourceFileName, location, additionalInfoMsg,"",
								ErrorHandler.E_ASSIGNING_AN_INTEGER_TYPE_TO_A_POINTER);
					}
					else{						
						if(!(litValue.equals("0") || litValue.equals("00") || litValue.equals("0x0"))){
							errorHandler.addError(sourceFileName, location, additionalInfoMsg,"",
									ErrorHandler.E_ASSIGNING_AN_INTEGER_TYPE_TO_A_POINTER);
						}
					}
				}
				else if(operatorType == AssignmentOperator.DIVIDE_ASSIGN){
					errorHandler.addError(sourceFileName, location, additionalInfoMsg,"",
							ErrorHandler.E_DIVIDE_ASSIGN_ON_POINTER);
				}
				else if(operatorType == AssignmentOperator.MODULO_ASSIGN){
					errorHandler.addError(sourceFileName, location, additionalInfoMsg,"",
							ErrorHandler.E_MODULO_ASSIGN_ON_POINTER);
				}
				else if(operatorType == AssignmentOperator.MULTIPLY_ASSIGN){
					errorHandler.addError(sourceFileName, location, additionalInfoMsg,"",
							ErrorHandler.E_MULTIPLY_ASSIGN_ON_POINTER);
				}
			}
			else if(rhsTypeEntry == DoubleTypeEntry.getInstance() || rhsTypeEntry == FloatTypeEntry.getInstance()){
				errorHandler.addError(sourceFileName, location, additionalInfoMsg, "" , ErrorHandler.E_ASSIGNING_A_FLOATING_POINT_TYPE_TO_A_POINTER);
			}
			else if(rhsTypeEntry instanceof StructOrUnionTypeEntry){
				errorHandler.addError(sourceFileName, location, additionalInfoMsg, "" , ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES);
			}
			else if(rhsTypeEntry instanceof PointerTypeEntry){
				// Check if the two pointer types are compatible
				PointerTypeEntry ptLhs = (PointerTypeEntry) lhsTypeEntry;
				PointerTypeEntry ptRhs = (PointerTypeEntry) rhsTypeEntry;

				TypeEntryWithAttributes tePtrLhs = ptLhs.getBaseTypeEntry();
				TypeEntryWithAttributes tePtrRhs = ptRhs.getBaseTypeEntry();

				if(!tePtrLhs.equals(tePtrRhs)){
					// Not same type of pointers, flag an error or warning if the rhs is not a void type
					if(!(ptRhs.getBaseTypeEntry().getBasicType() == VoidTypeEntry.getInstance() || 
							ptLhs.getBaseTypeEntry().getBasicType() == VoidTypeEntry.getInstance()))
						errorHandler.addError(sourceFileName, location, additionalInfoMsg,"",
								ErrorHandler.E_ASSIGNMENT_INCOMPATIBLE_POINTER_TYPES);
				}				
			}	
			else if(rhsTypeEntry instanceof ArrayTypeEntry){
				PointerTypeEntry ptLhs = (PointerTypeEntry) lhsTypeEntry;
				if(context == Semantic.ASSIGNMENT_IN_ASSIGNMENT_EXPR){
					// Check if the array type is compatible
					ArrayTypeEntry rhsArr = (ArrayTypeEntry) rhsTypeEntry;			
					TypeEntryWithAttributes tePtrLhs = ptLhs.getBaseTypeEntry();
					TypeEntryWithAttributes teArrRhs = rhsArr.getBaseTypeEntry();
					if(!tePtrLhs.equals(teArrRhs)){   // TODO Check if this is enough under all situations
						errorHandler.addError(sourceFileName, location, additionalInfoMsg,"",
								ErrorHandler.E_ASSIGNMENT_INCOMPATIBLE_POINTER_TYPES);
					}
				}
				else if(context == Semantic.ASSIGNMENT_TO_SUB_ELEMENT){
					// This check relevant only when verifying sub-types. 

					ArrayTypeEntry arrRhs = (ArrayTypeEntry) rhsTypeEntry;
					checkAssignmentCompatibility(ptLhs.getBaseTypeEntry(), arrRhs.getBaseTypeEntry(),location,
							Semantic.ASSIGNMENT_TO_SUB_ELEMENT,AssignmentOperator.ASSIGN, null, -1);
				}				
			}
			if(rhsTypeEntry instanceof StringTypeEntry){
				PointerTypeEntry ptLhs = (PointerTypeEntry) lhsTypeEntry;
				BasicType btOfLhsPtr = ptLhs.getBaseTypeEntry().getBasicType();
				if(!(btOfLhsPtr instanceof CharTypeEntry)){  // Not a pointer to a char
					if(btOfLhsPtr instanceof IntTypeEntry)
						errorHandler.addError(sourceFileName, location, additionalInfoMsg,"",
								ErrorHandler.E_ASSIGNING_STRING_TO_POINTER_TO_INT);
					else if(btOfLhsPtr instanceof EnumSpecTypeEntry )
						errorHandler.addError(sourceFileName, location, additionalInfoMsg,"",
								ErrorHandler.E_ASSIGNING_STRING_TO_POINTER_TO_INT);
					else if(btOfLhsPtr instanceof LongTypeEntry)
						errorHandler.addError(sourceFileName, location, additionalInfoMsg,"",
								ErrorHandler.E_ASSIGNING_STRING_TO_POINTER_TO_LONG);
					else if(btOfLhsPtr instanceof ShortTypeEntry)
						errorHandler.addError(sourceFileName, location, additionalInfoMsg,"",
								ErrorHandler.E_ASSIGNING_STRING_TO_POINTER_TO_SHORT);
					else if(btOfLhsPtr instanceof FloatTypeEntry)
						errorHandler.addError(sourceFileName, location, additionalInfoMsg,"",
								ErrorHandler.E_ASSIGNING_STRING_TO_POINTER_TO_FLOAT);
					else if(btOfLhsPtr instanceof DoubleTypeEntry)
						errorHandler.addError(sourceFileName, location, additionalInfoMsg,"",
								ErrorHandler.E_ASSIGNING_STRING_TO_POINTER_TO_DOUBLE);
					else if(btOfLhsPtr instanceof StructOrUnionTypeEntry)
						errorHandler.addError(sourceFileName, location, additionalInfoMsg,"",
								ErrorHandler.E_ASSIGNING_STRING_TO_POINTER_TO_STRUCT);
					else 
						errorHandler.addError(sourceFileName, location, additionalInfoMsg,"",
								ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES);
				}
			}
		}
		else if(lhsTypeEntry instanceof ArrayTypeEntry){ // lhs is of array type, check each context
			if(context == Semantic.ASSIGNMENT_IN_PARAMETER_PASSING)
				arrayDeclaratorIsOk((ArrayTypeEntry)lhsTypeEntry, null, null, Semantic.ASSIGNMENT_IN_PARAMETER_PASSING,
						location);			
			else if(context == Semantic.ASSIGNMENT_TO_SUB_ELEMENT){
				ArrayTypeEntry arrTypeEntryLhs = (ArrayTypeEntry) lhsTypeEntry;
				ArrayTypeEntry arrTypeEntryRhs = null;
				if(rhsTypeEntry instanceof ArrayTypeEntry){  // both lhs and rhs are arrays, 
					// check the dimensions and the subtypes
					arrTypeEntryRhs = (ArrayTypeEntry) rhsTypeEntry;

					// Check the array dimensions first
					TypeEntryWithAttributes lhsDimTeAttr = arrTypeEntryLhs.getDimension();
					TypeEntryWithAttributes rhsDimTeAttr = arrTypeEntryRhs.getDimension();
					if(lhsDimTeAttr != null && rhsDimTeAttr != null){
						try{
							int lhsDim = Integer.parseInt(lhsDimTeAttr.getLiteralValue());
							int rhsDim = Integer.parseInt(rhsDimTeAttr.getLiteralValue());

							if(lhsDim < rhsDim){
								errorHandler.addError(sourceFileName, location, additionalInfoMsg, "",
										ErrorHandler.E_DIFFERENT_ARRAY_SUBSCRIPTS);
							}
						}
						catch(NumberFormatException nfe){ }
					}
					// Check the subtypes
					checkAssignmentCompatibility(arrTypeEntryLhs.getBaseTypeEntry(), arrTypeEntryRhs.getBaseTypeEntry(),
							location, Semantic.ASSIGNMENT_TO_SUB_ELEMENT,AssignmentOperator.ASSIGN, null, -1);
				}
				else{ // Incompatible types
					errorHandler.addError(sourceFileName, location, additionalInfoMsg,"",
							ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES);
				}
			}
		}
		else if(lhsTypeEntry instanceof StructOrUnionTypeEntry){ // lhs is of array type, check each context
			if(!(rhsTypeEntry instanceof StructOrUnionTypeEntry)){
				setIncompatibleAssignmentError(context, location, "", functionName, paramNum);
			}
			else{
				// Both are structs (or unions), check the types
				StructOrUnionTypeEntry stOrUnLhs = (StructOrUnionTypeEntry) lhsTypeEntry;
				StructOrUnionTypeEntry stOrUnRhs = (StructOrUnionTypeEntry) rhsTypeEntry;

				// If it is a union then check whether 'stOrUnLhs' contains 'stOrUnRhs'
				if(!stOrUnLhs.isStruct()){
					boolean isContains = false;
					LinkedHashMap<String, TypeEntryWithAttributes> membersMap = stOrUnLhs.getMembers();
					Set<Entry<String, TypeEntryWithAttributes>> entries = membersMap.entrySet();
					Iterator<Entry<String, TypeEntryWithAttributes>> iterator = entries.iterator();

					while(iterator.hasNext()){
						Entry<String, TypeEntryWithAttributes> entry = iterator.next();
						TypeEntryWithAttributes memberType = entry.getValue();
						// Since the initializer in case of a union would also be a member of the union within the curly braces so in order to compare we have to get the member type of 'stOrUnRhs'
						TypeEntryWithAttributes rhsMemberType = stOrUnRhs.getMembers().entrySet().iterator().next().getValue();
						if(memberType.getBasicType().equals(rhsMemberType.getBasicType()))
							isContains = true;
					}

					if(!isContains)
						setIncompatibleAssignmentError(context, location, "", functionName, paramNum);
				}
				else if(!stOrUnLhs.equals(stOrUnRhs)){
					setIncompatibleAssignmentError(context, location, "", functionName, paramNum);
				}			
			}
		}
	}

	private void setIncompatibleAssignmentError(int context, SourceLocation location, 
			String additionalInfoMsg, String functionName, int paramNum){
		if(context == Semantic.ASSIGNMENT_IN_ASSIGNMENT_EXPR)
			errorHandler.addError(sourceFileName, location, additionalInfoMsg, "",
					ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES);
		else if(context == Semantic.ASSIGNMENT_IN_DECLR_INIT_EXPR){
			errorHandler.addError(sourceFileName, location, additionalInfoMsg, "",
					ErrorHandler.E_INVALID_INITIALIZATION);
		}
		else if(context == Semantic.ASSIGNMENT_IN_PARAMETER_PASSING){
			String errMsgPreamble = "Argument " + paramNum + " of " + functionName + ": ";
			errorHandler.addError(sourceFileName, location, errMsgPreamble, "",
					ErrorHandler.E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES);
		}
	}

	/**
	 * The lhs and the right side literal value are already confirmed to be of the same type. Applies to the
	 * basic types: int, short, char, long, double and float).
	 * @param lhsTeWithAttrs
	 * @param literalValue
	 * @param location
	 */
	private void checkTypeAgainstLiteralValue(TypeEntryWithAttributes lhsTeWithAttrs, String literalValue,
			SourceLocation location){

		BasicType lhsTypeEntry = lhsTeWithAttrs.getBasicType();

		if(lhsTypeEntry == CharTypeEntry.getInstance()){
			if(!lhsTeWithAttrs.isUnsigned() && !Constant.canBeSignedChar(literalValue))
				errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_CONST_LITERAL_OVERFLOW);
			if(lhsTeWithAttrs.isUnsigned() && !Constant.canBeUnSignedChar(literalValue))
				errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_CONST_LITERAL_OVERFLOW);
		}
		else if(lhsTypeEntry == ShortTypeEntry.getInstance()){  // lhs is of type short, check rhs
			if(literalValue != null ){  // rhs is a const value, check if it ok to assign to a char
				if(!lhsTeWithAttrs.isUnsigned() && !Constant.canBeSignedShort(literalValue))
					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_CONST_LITERAL_OVERFLOW);
				if(lhsTeWithAttrs.isUnsigned() && !Constant.canBeUnSignedShort(literalValue))
					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_CONST_LITERAL_OVERFLOW);				
			}
		}
		else if(lhsTypeEntry == IntTypeEntry.getInstance()){ // lhs is of type int, check rhs	
			if(literalValue != null ){  // rhs is a const value, check if it ok to assign to a char
				if(!lhsTeWithAttrs.isUnsigned() && !Constant.canBeSignedInt(literalValue))
					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_CONST_LITERAL_OVERFLOW);
				if(lhsTeWithAttrs.isUnsigned() && !Constant.canBeUnSignedInt(literalValue))
					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_CONST_LITERAL_OVERFLOW);				
			}
		}
		else if(lhsTypeEntry == LongTypeEntry.getInstance()){ // lhs is of type long, check rhs	
			if(literalValue != null ){  // rhs is a const value, check if it ok to assign to a char
				if(!lhsTeWithAttrs.isUnsigned() && !Constant.canBeSignedLong(literalValue))
					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_CONST_LITERAL_OVERFLOW);
				if(lhsTeWithAttrs.isUnsigned() && !Constant.canBeUnSignedLong(literalValue))
					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_CONST_LITERAL_OVERFLOW);				
			}
		}
		else if(lhsTypeEntry == FloatTypeEntry.getInstance()){ // lhs is of type float, check rhs	
			if(literalValue != null ){  // rhs is a const value, check if it ok to assign to a float

				if(!Constant.canBeFloat(literalValue)){
					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_CONST_TOO_LARGE_FOR_FLOAT);
				}
			}
		}
		else if(lhsTypeEntry == DoubleTypeEntry.getInstance()){ // lhs is of type double, check rhs	
			if(literalValue != null ){  // rhs is a const value, check if it ok to assign to a float
				if(!Constant.canBeDouble(literalValue)){
					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_CONST_TOO_LARGE_FOR_DOUBLE);
				}
			}	
		}
		return;
	}

	private void checkArrayTypesInDeclInit(ArrayTypeEntry arrayDecl, ArrayTypeEntry initArray,
			SourceLocation location){

		TypeEntryWithAttributes teAttrsLhs = arrayDecl.getBaseTypeEntry();
		BasicType btArrTeLhs = teAttrsLhs.getBasicType();

		TypeEntryWithAttributes teAttrsRhs = initArray.getBaseTypeEntry();
		BasicType btArrTeRhs = teAttrsRhs.getBasicType();

		// If the lhs is a pointer to char and the rhs is a string, we are ok
		if(btArrTeLhs instanceof PointerTypeEntry){
			PointerTypeEntry pteLhs = (PointerTypeEntry) btArrTeLhs;
			TypeEntryWithAttributes baseOfPteLhs = pteLhs.getBaseTypeEntry();
			if(baseOfPteLhs.getBasicType() instanceof CharTypeEntry && btArrTeRhs instanceof StringTypeEntry)
				return;
		}

		checkAssignmentCompatibility(teAttrsLhs, teAttrsRhs, location, 
				Semantic.ASSIGNMENT_IN_DECLR_INIT_EXPR, AssignmentOperator.ASSIGN, null, -1);

		// Check the sizes
		TypeEntryWithAttributes teDimArray = arrayDecl.getDimension();
		TypeEntryWithAttributes teDimInit = initArray.getDimension();

		if(teDimArray != null && teDimInit != null){
			int sizeOfArray = 0;
			try{
				sizeOfArray = Integer.parseInt(teDimArray.getLiteralValue());
			}
			catch(NumberFormatException nfe){}

			int sizeOfInit = Integer.parseInt(teDimInit.getLiteralValue());
			if(sizeOfInit > sizeOfArray){
				ErrorHandler errorHandler = ErrorHandler.getInstance();

				// If the excess initializer error message already exists, don't add it again
				if(!errorHandler.errorOrWarningAlreadyExists(sourceFileName, location, ErrorHandler.ERROR_MSGS_ONLY, 
						ErrorHandler.E_EXCESS_INITIALIZERS_IN_ARRAY_INIT)) {
					// Add this error only if required; this might come up several times
					errorHandler.addError(sourceFileName, location, "", "", ErrorHandler.E_EXCESS_INITIALIZERS_IN_ARRAY_INIT);					
				}
			}	
		}

		// If the base type are both arrays, check the types again
		if(btArrTeLhs instanceof ArrayTypeEntry && btArrTeRhs instanceof ArrayTypeEntry){
			checkArrayTypesInDeclInit((ArrayTypeEntry) btArrTeLhs, (ArrayTypeEntry)btArrTeRhs, location );
		}
	}

	private int getSizeOfType(TypeEntryWithAttributes type){
		// TODO Abstract this out, since type depends on the target machine. Comment below and implement later
		return 4;
	}

	/**
	 * Returns the BinaryOpExpr type for the given Assignment operator type
	 * 
	 */

	private int getBinaryOpFromAssignmentOpr(int assgnOprType){
		int retValue = 0;
		if(assgnOprType == AssignmentOperator.ASSIGN)   // A simple assignment
			return -1;


		if(assgnOprType == AssignmentOperator.ADD_ASSIGN){
			retValue = BinaryOpExpr.PLUS;			
		}
		else if(assgnOprType == AssignmentOperator.MINUS_ASSIGN){
			retValue = BinaryOpExpr.MINUS;			
		}
		else if(assgnOprType == AssignmentOperator.MULTIPLY_ASSIGN){
			retValue = BinaryOpExpr.MULTIPLY;		
		}
		else if(assgnOprType == AssignmentOperator.DIVIDE_ASSIGN){
			retValue = BinaryOpExpr.DIVIDE;			
		}
		else if(assgnOprType == AssignmentOperator.MODULO_ASSIGN){
			retValue = BinaryOpExpr.MODULO;			
		}
		else if(assgnOprType == AssignmentOperator.BITWISE_AND_ASSIGN){
			retValue = BinaryOpExpr.AND;		
		}
		else if(assgnOprType == AssignmentOperator.BITWISE_XOR_ASSIGN){
			retValue = BinaryOpExpr.EXCLUSIVE_OR;			
		}
		else if(assgnOprType == AssignmentOperator.BITWISE_OR_ASSIGN){
			retValue = BinaryOpExpr.OR;			
		}
		else if(assgnOprType == AssignmentOperator.LEFT_SHIFT_ASSIGN){
			retValue = BinaryOpExpr.LEFT_SHIFT;		
		}
		else{
			retValue = BinaryOpExpr.RIGHT_SHIFT;
		}

		return retValue;
	}
}

class ComparingBasedOnAlignmentOfType implements Comparator<Type>{
	private Map<Type, Integer> typeVsWidth;

	public ComparingBasedOnAlignmentOfType(Map<Type, Integer> typeVsWidth){
		this.typeVsWidth = typeVsWidth;
	}

	@Override
	public int compare(Type o1, Type o2) {
		Integer width1 = typeVsWidth.get(o1);
		Integer width2 = typeVsWidth.get(o2);
		return width1.compareTo(width2);
	}

}

class ComparingBasedOnSizeOfType implements Comparator<Type>{
	private Map<Type, Integer> typeVsSize;

	public ComparingBasedOnSizeOfType(Map<Type, Integer> typeVsSize){
		this.typeVsSize = typeVsSize;
	}

	@Override
	public int compare(Type o1, Type o2) {
		Integer size1 = typeVsSize.get(o1);
		Integer size2 = typeVsSize.get(o2);
		return size1.compareTo(size2);
	}

}
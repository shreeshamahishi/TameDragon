
package org.tamedragon.common.llvmir.semanticanalysis;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;

import org.jgraph.graph.DefaultEdge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tamedragon.common.Label;
import org.tamedragon.common.Pair;
import org.tamedragon.common.llvmir.instructions.AllocaInst;
import org.tamedragon.common.llvmir.instructions.Attributes;
import org.tamedragon.common.llvmir.instructions.BinaryOperator;
import org.tamedragon.common.llvmir.instructions.BranchInst;
import org.tamedragon.common.llvmir.instructions.CFG;
import org.tamedragon.common.llvmir.instructions.CallInst;
import org.tamedragon.common.llvmir.instructions.CallingConv;
import org.tamedragon.common.llvmir.instructions.CastInst;
import org.tamedragon.common.llvmir.instructions.CmpInst;
import org.tamedragon.common.llvmir.instructions.FCmpInst;
import org.tamedragon.common.llvmir.instructions.GetElementPtrInst;
import org.tamedragon.common.llvmir.instructions.ICmpInst;
import org.tamedragon.common.llvmir.instructions.Instruction;
import org.tamedragon.common.llvmir.instructions.LLVMUtility;
import org.tamedragon.common.llvmir.instructions.LoadInst;
import org.tamedragon.common.llvmir.instructions.PhiNode;
import org.tamedragon.common.llvmir.instructions.ReturnInst;
import org.tamedragon.common.llvmir.instructions.SelectInst;
import org.tamedragon.common.llvmir.instructions.StoreInst;
import org.tamedragon.common.llvmir.instructions.SwitchInst;
import org.tamedragon.common.llvmir.instructions.UnreachableInstr;
import org.tamedragon.common.llvmir.instructions.BinaryOperator.BinaryOperatorID;
import org.tamedragon.common.llvmir.instructions.Instruction.AtomicOrdering;
import org.tamedragon.common.llvmir.instructions.Instruction.InstructionID;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionCreationException;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionDetailAccessException;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionUpdateException;
import org.tamedragon.common.llvmir.irdata.AllocaInstrData;
import org.tamedragon.common.llvmir.irdata.ArgumentData;
import org.tamedragon.common.llvmir.irdata.BinaryOprData;
import org.tamedragon.common.llvmir.irdata.BranchInstrData;
import org.tamedragon.common.llvmir.irdata.CallInstrData;
import org.tamedragon.common.llvmir.irdata.CastInstrData;
import org.tamedragon.common.llvmir.irdata.CmpData;
import org.tamedragon.common.llvmir.irdata.DataLayout;
import org.tamedragon.common.llvmir.irdata.FunctionData;
import org.tamedragon.common.llvmir.irdata.GetElementPtrData;
import org.tamedragon.common.llvmir.irdata.GlobalVariableData;
import org.tamedragon.common.llvmir.irdata.LoadInstrData;
import org.tamedragon.common.llvmir.irdata.PhiNodeData;
import org.tamedragon.common.llvmir.irdata.ReturnInstrData;
import org.tamedragon.common.llvmir.irdata.SelectInstrData;
import org.tamedragon.common.llvmir.irdata.StoreInstrData;
import org.tamedragon.common.llvmir.irdata.StructureData;
import org.tamedragon.common.llvmir.irdata.SwitchCasesData;
import org.tamedragon.common.llvmir.irdata.SwitchInstrData;
import org.tamedragon.common.llvmir.irdata.UnreachableInstrData;
import org.tamedragon.common.llvmir.irdata.ValueData;
import org.tamedragon.common.llvmir.math.APInt;
import org.tamedragon.common.llvmir.math.APSInt;
import org.tamedragon.common.llvmir.math.ULong;
import org.tamedragon.common.llvmir.types.APFloat;
import org.tamedragon.common.llvmir.types.Argument;
import org.tamedragon.common.llvmir.types.ArrayType;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.CompilationContext;
import org.tamedragon.common.llvmir.types.Constant;
import org.tamedragon.common.llvmir.types.ConstantArray;
import org.tamedragon.common.llvmir.types.ConstantExpr;
import org.tamedragon.common.llvmir.types.ConstantFP;
import org.tamedragon.common.llvmir.types.ConstantInt;
import org.tamedragon.common.llvmir.types.ConstantStruct;
import org.tamedragon.common.llvmir.types.FltSemantics;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.FunctionType;
import org.tamedragon.common.llvmir.types.GlobalValue;
import org.tamedragon.common.llvmir.types.GlobalVariable;
import org.tamedragon.common.llvmir.types.IntegerType;
import org.tamedragon.common.llvmir.types.Module;
import org.tamedragon.common.llvmir.types.NamedType;
import org.tamedragon.common.llvmir.types.PointerType;
import org.tamedragon.common.llvmir.types.StructType;
import org.tamedragon.common.llvmir.types.Type;
import org.tamedragon.common.llvmir.types.UndefValue;
import org.tamedragon.common.llvmir.types.Value;
import org.tamedragon.common.llvmir.types.exceptions.TypeCreationException;
import org.tamedragon.target.TargetData;

/** This is class represents the process of semantic analysis of an LLVM intermediate
 * representation. The analysis is performed on a list of ValueData objects that is
 * generated in the parsing layer. 
 */

public class LLVMSemantic {
	private String fileName;
	private int currentInstrID;
	private CompilationContext compilationContext;
	private Function currentFunction;
	private BasicBlock currentBasicBlock;
	private CFG cfg;
	private boolean isFirstInstruction ;
	private boolean isFirstBB;
	private List<NamedType> namedTypes;
	private Module module;
	private SwitchInst switchInstr;
	private Map<String, Value> localVariableNameValueMap;
	private Map<String, Value> globalVariableNameValueMap;
	private Map<Label, BasicBlock> labelAndBasicBlock;
	private Map<String, StructType> structAndType;
	private List<ValueData> irDataList;
	private List<BasicBlock> basicBlocksList;
	private CastInst castInstForGlobalVariable;
	private List<PhiNode> listOfIncompletePhiNodes;

	private LLVMErrorHandler errorHandler;
	private Properties properties;

	private static final Logger LOG = LoggerFactory.getLogger(LLVMSemantic.class);
	private static final String NO_LLVM_SOURCE = "No source code was found by the semantic analyzer. Check the parser.";
	private static final String UNKNOWN_IR_DATA = "Fatal error: Unknown intermediate representation.";
	private static final String UNKNOWN_TYPE = "Fatal error: Unknown type: ";
	private static final String CANNOT_CREATE_TYPE = "Fatal error: Unable to create type: ";
	private static final String NAME_IS_NULL = "No value with a null name exists. ";
	private static final String NO_VAUE_WITH_NAME = "No value exists with name: ";
	private static final String INCORRECT_ORDERING = "Incorrect ordering of local variable: ";
	private static final String UNKNOWN_CAST_INSTRUCTION = "Unknown cast instruction: ";
	private static final String CREATING_NEW_FUNCTION = "Creating new function: ";
	private static final String UNABLE_TO_UPDATE_PHI_NODE = "Fatal error: Not able to update phi node";
	private static final String UNABLE_TO_CREATE_TYPE = "Fatal error: Not able to create type: ";
	private static final String UNKNOWN_LINKAGE_TYPE = "Unknown linkage type: ";
	private static final String POINTEE_TYPE_DOES_NOT_MATCH_POINTER_CONTAINED_TYPE = "error: explicit pointee type doesn't match operand's pointee type";
	private static final String INVALID_TYPE_FOR_BOOLEAN_CONSTANT = "Fatal error: Invalid type for boolean constant";

	private static final String UNKNOWN_ERROR = "Unknown error: ";
	private static final String COMPILER_SETTINGS_PATH = "CompilerSettings.properties";

	public LLVMSemantic(String fileName, List<ValueData> irData){
		this.fileName = fileName;
		irDataList = irData;
		compilationContext = new CompilationContext();
		namedTypes = new ArrayList<NamedType>();
		module = new Module("", compilationContext, namedTypes);		
		isFirstInstruction = true;
		isFirstBB = true;
		switchInstr = null;
		localVariableNameValueMap = new LinkedHashMap<String, Value>();
		globalVariableNameValueMap = new LinkedHashMap<String, Value>();
		labelAndBasicBlock = new LinkedHashMap<Label, BasicBlock>();
		structAndType = new LinkedHashMap<String, StructType>();
		errorHandler = LLVMErrorHandler.getInstance();

		// Set up compiler settings and set default values if the properties file is not found
		properties = getProperties();

	}

	private Properties getProperties() {
		Properties properties = new Properties();
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(COMPILER_SETTINGS_PATH).getFile());

		FileReader reader = null;
		try {
			reader = new FileReader(file.getAbsoluteFile());
			properties = new Properties();
			properties.load(reader);

		} catch (FileNotFoundException e) {
			LOG.warn("Unable to locate compiler settings. Using default values.");
			return LLVMUtility.getDefaultProperties();
		} catch (IOException e) {
			LOG.warn("Unable to locate compiler settings. Using default values.");
			return LLVMUtility.getDefaultProperties();
		}

		// Close the Reader object
		try{
			if(reader != null){
				reader.close();
				reader = null;
			}
		}
		catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}

		return properties;
	}

	public Module semantic() {
		if(irDataList.isEmpty()){
			// No LLVM instructions in source?
			LOG.error(NO_LLVM_SOURCE);
			assert false : NO_LLVM_SOURCE;
			return null;
		}

		Iterator<ValueData> iterator = irDataList.iterator();
		int count = 0;
		while(iterator.hasNext()){
			ValueData valueData = iterator.next();

			if (valueData instanceof DataLayout) {
				DataLayout dataLayout = (DataLayout) valueData;
				createAndSetTargetData(dataLayout);
			}
			else if (valueData instanceof FunctionData){
				FunctionData functionData = (FunctionData)valueData;
				createFunction(functionData);
			}
			else if(valueData instanceof AllocaInstrData){
				AllocaInstrData allocaInstrData = (AllocaInstrData)valueData;
				createAllocaInstr(allocaInstrData);
			}
			else if(valueData instanceof LoadInstrData){
				LoadInstrData loadInstrData = (LoadInstrData)valueData;
				createLoadInstr(loadInstrData);
			}
			else if (valueData instanceof StoreInstrData){
				StoreInstrData storeInstrData = (StoreInstrData)valueData;
				createStoreInstr(storeInstrData);
			} 
			else if (valueData instanceof BinaryOprData){
				BinaryOprData binaryOprData = (BinaryOprData)valueData;
				createBinaryOprInstr(binaryOprData);
			}
			else if(valueData instanceof CmpData){
				CmpData icmpData =(CmpData)valueData;
				createIcmpInstr(icmpData);
			}
			else if(valueData instanceof BranchInstrData){
				BranchInstrData branch=(BranchInstrData)valueData;
				createBranchInstr(branch);
			}
			else if (valueData instanceof ReturnInstrData){
				ReturnInstrData returnInstrData = (ReturnInstrData)valueData;
				createReturnInstr(returnInstrData);
			}
			else if (valueData instanceof GlobalVariableData){
				GlobalVariableData data = (GlobalVariableData)valueData;
				createGlobalVariable(data);
			}
			else if (valueData instanceof SwitchInstrData){
				SwitchInstrData data = (SwitchInstrData) valueData;
				createSwitchInstr(data);
			}
			else if (valueData instanceof SwitchCasesData) {
				SwitchCasesData casesData = (SwitchCasesData)valueData;
				createCaseForCurrentSwitchInstruction(casesData);
			}
			else if (valueData instanceof UnreachableInstrData){
				UnreachableInstrData data = (UnreachableInstrData)valueData;
				createUnreachableInstr(data);
			}
			else if (valueData instanceof GetElementPtrData) {
				GetElementPtrData data = (GetElementPtrData)valueData;
				createGetElementPtr(data);
			}
			else if (valueData instanceof StructureData) {
				StructureData data = (StructureData)valueData;
				createStructureOrUnion(data);
			}
			else if (valueData instanceof PhiNodeData) {
				PhiNodeData data = (PhiNodeData)valueData;
				createPhiNode(data);
			}
			else if (valueData instanceof CallInstrData) {
				CallInstrData data = (CallInstrData)valueData;
				createCallInstr(data);
			}
			else if (valueData instanceof CastInstrData) {
				CastInstrData data = (CastInstrData)valueData;
				createCastInstr(data);
			}
			else if (valueData instanceof SelectInstrData) {
				SelectInstrData data = (SelectInstrData)valueData;
				createSelectInstr(data);
			}
			else{
				assert false : UNKNOWN_IR_DATA;
			return null;
			}
			count++;
		}

		errorHandler.displayResult();
		return module;
	}

	/** Initialize new basic block and insert it into the labelAndBasicBlock map
	 **/
	private BasicBlock initNewBasicBlock(Label label, Function parent, boolean isDummy) {
		BasicBlock newBB = null;
		BasicBlock tempBB = labelAndBasicBlock.get(label);
		if (tempBB != null) {
			// Basic block is already created
			newBB = tempBB;
		}
		else {
			// Create a new basic block.
			newBB = new BasicBlock(parent);
			cfg.addVertex(newBB);
			labelAndBasicBlock.put(label, newBB); // add to the lableVs basicBlock list
		}

		if (isFirstBB == true)  {
			currentBasicBlock = newBB;
			isFirstBB = false;
		}
		else {
			if(!isDummy) {
				// Incoming value from phi node
				cfg.addEdge(currentBasicBlock, newBB, new DefaultEdge());
			}
		}
		return newBB;
	}

	/**
	 * Update the current basic block after terminator instruction using currentInstrID
	 */
	private void updateCurrentBB() {
		currentInstrID++;
		String currentInsrIdStr = Integer.toString(currentInstrID);
		BasicBlock tempBB = null;
		Label tempLabel = new Label(currentInsrIdStr);
		tempBB = labelAndBasicBlock.get(tempLabel);
		if (tempBB != null){
			// Basic Block is already created
			currentBasicBlock = tempBB;
		}
		else {
			currentBasicBlock = initNewBasicBlock(tempLabel, currentFunction,false);
		}
	}

	/**
	 * Given a type description, this method returns the corresponding type. Aggregate types and other non-simple
	 * will need recursive calls.
	 */
	private Type getLLVMType(String strType, CompilationContext compilationContext, boolean isSigned) {
		Type type = null;

		try {
			if (strType.equals("i1")){
				type = Type.getInt1Type(compilationContext, isSigned);
			}
			else if (strType.equals("i8")){
				type = Type.getInt8Type(compilationContext, isSigned);
			}
			else if (strType.equals("i16")){
				type = Type.getInt16Type(compilationContext, isSigned);
			}
			else if (strType.equals("i32")){
				type = Type.getInt32Type(compilationContext, isSigned);
			}
			else if (strType.equals("i64")){
				type = Type.getInt64Type(compilationContext, isSigned);
			}
			else if (strType.equals("void")){
				type = Type.getVoidType(compilationContext);
			}
			else if (strType.equals("double")){
				type = Type.getDoubleType(compilationContext);
			}
			else if (strType.equals("float")){
				type = Type.getFloatType(compilationContext);
			}
			else if (strType.charAt(strType.length() - 1) == '*') {
				// Pointer type
				String containedTypeStr = strType.substring(0, strType.length() - 1);
				Type containedType = getLLVMType(containedTypeStr, compilationContext, isSigned);
				type = Type.getPointerType(compilationContext, containedType, 0);
			} else if ((strType.charAt(0) == '[') && (strType.charAt(strType.length() - 1) == ']')) {
				// Array type
				int numElements;
				String strTypewithoutbracket = strType.substring(1, strType.length() - 1);
				String numElementsAndType[] = strTypewithoutbracket.split(" x ", 2);
				String constantN0Str = numElementsAndType[0];
				numElements = Integer.parseInt(constantN0Str);
				String containedTypeStr = numElementsAndType[1];
				Type containedType = getLLVMType(containedTypeStr, compilationContext, false);
				if(containedType != null) {
					type = Type.getArrayType(compilationContext, containedType, numElements);				
				}
			}
			else if (strType.matches("\\w*\\s\\((\\w*\\**)?(,\\s\\w+\\**)*(,\\s\\.\\.\\.)?\\)")) {
				// Pointer to function type
				Type returnType = null;
				Vector<Type> paramTypes = new Vector<Type>();
				boolean isVarArgs = false;
				String returnTypeandArgu[] = strType.split("\\(");
				String returnTypeStr = returnTypeandArgu[0].trim();

				returnType = getLLVMType(returnTypeStr, compilationContext, false);
				String argulistStr[] = returnTypeandArgu[1].split(",");
				for (String argumentString : argulistStr) {
					argumentString = argumentString.trim();
					if (argumentString.contains(")")) {
						argumentString = argumentString.substring(0, argumentString.length() - 1);
					}
					if (argumentString.equals("...")) {
						isVarArgs = true;
						break;
					}
					Type argumentType = getLLVMType(argumentString, compilationContext,
							false);
					paramTypes.add(argumentType);
				}

				type = Type.getFunctionType(compilationContext, returnType, isVarArgs, paramTypes);
			} 
			else if (strType.contains("%struct") || strType.contains("%union")) {
				type = structAndType.get(strType);

				if (type == null) { 
					// Self-referential structure
					boolean isUnion = (strType.contains("%struct")) ? false : true;
					String name = strType.substring(1);
					List<Type> containedTypes = new ArrayList<Type>();
					type = Type.getStructType(compilationContext, false, name,
							isUnion, containedTypes.toArray(new Type[containedTypes.size()]));
				}
			}
			else{
				// Bad or unknown type. Lets assert and return null
				String message = UNKNOWN_TYPE + ": " + strType;
				LOG.error(message);
				//assert false : message;
			}
		}
		catch(TypeCreationException tce){
			// Cannot recover from this exception. Lets assert and return null
			LOG.error(tce.getMessage());
			assert false : tce.getMessage();

			return null;
		}

		if(type == null){
			// Cannot create this type
			LOG.error(CANNOT_CREATE_TYPE);
			//assert false : CANNOT_CREATE_TYPE;
		}

		return type;
	}

	/**
	 * This method returns the value from local or global map symbol table.
	 */
	private Value getValueFromMap(String key) {
		if(key == null){
			LOG.warn(NAME_IS_NULL);
			//assert false : NAME_IS_NULL;
			return null;
		}

		// First look for values set in the map for local temporaries.
		Value value = null;
		value = localVariableNameValueMap.get(key);
		if(value != null){
			return value;
		}

		// Not in local variable; look for values set in the map for global variables.
		value = globalVariableNameValueMap.get(key);
		if(value == null){
			String message = NO_VAUE_WITH_NAME + key;
			LOG.warn(message);
			//assert false : message;
			return null;
		}

		return value;
	}

	/**
	 * Adds a new value to the map for local variables. 
	 */
	private void addToMap(SourceLocation location, String name, Value value) {

		if(localVariableNameValueMap.containsKey(name)){
			errorHandler.addError(fileName, location , name, null, LLVMErrorHandler.E_ALREADY_DEFINED);
			return;
		}

		String number = name.substring(1);
		if (isVariableNameInteger(name)){
			currentInstrID++;
			int resultNumber = Integer.parseInt(number);
			if(currentInstrID != resultNumber){
				LOG.error(INCORRECT_ORDERING);
				assert false : INCORRECT_ORDERING;
				return;
			}
		}

		localVariableNameValueMap.put(name, value);
	}

	private boolean isVariableNameInteger(String name){
		if(name == null){
			return false;
		}

		String number = name.substring(1);
		if (number.matches("\\d+")){
			return true;
		}

		return false;

	}

	/**
	 * Returns the instruction ID given the name of the cast instruction. 
	 */
	private InstructionID getCastInstructionID(String typeStr) {
		InstructionID instructionID = null;
		if (typeStr.equals("trunc")){
			instructionID = InstructionID.TRUNC_INST;
		}
		else if (typeStr.equals("zext")){
			instructionID = InstructionID.ZEXT_INST;
		}
		else if (typeStr.equals("sext")){
			instructionID = InstructionID.SEXT_INST;
		}
		else if (typeStr.equals("fptrunc")){
			instructionID = InstructionID.FP_TRUNC_INST;
		}
		else if (typeStr.equals("fpext")){
			instructionID = InstructionID.FP_EXT_INST;
		}
		else if (typeStr.equals("fptoui")){
			instructionID = InstructionID.FP_TO_UI_INST;
		}
		else if (typeStr.equals("fptosi")){
			instructionID = InstructionID.FP_TO_SI_INST;
		}
		else if (typeStr.equals("uitofp")){
			instructionID = InstructionID.UI_TO_FP_INST;
		}
		else if (typeStr.equals("sitofp")){
			instructionID = InstructionID.SI_TO_FP_INST;
		}
		else if (typeStr.equals("ptrtoint")){
			instructionID = InstructionID.PTR_TO_INT_INST;
		}
		else if (typeStr.equals("inttoptr")){
			instructionID = InstructionID.INT_TO_PTR_INST;
		}
		else if (typeStr.equals("bitcast")){
			instructionID = InstructionID.BIT_CAST_INST;
		}
		else{
			LOG.error(UNKNOWN_CAST_INSTRUCTION);
			assert false : UNKNOWN_CAST_INSTRUCTION;
		}

		return instructionID;
	}

	/**
	 * Returns a constant value based on the value and type descriptions  
	 */
	private Value createConstantValue(SourceLocation location, String value, String typeStr) throws Exception {
		Type type = null;
		Constant constant = null;
		APInt apInt = null;
		boolean isSigned = false;
		char firstChar = value.charAt(0);

		if (firstChar == '+' || firstChar == '-'){
			isSigned = true;
		}
		if (typeStr != null){
			type = getLLVMType(typeStr, compilationContext, isSigned);
		}

		if(type == null){
			// Cannot create type; this error is already handled, return null
			return null;
		}
		
		if(value.equals("false")) {
			System.out.println("WAIT HERE");
		}

		if("undef".equals(value)) {
			return UndefValue.createOrGet(type);
		}

		if("true".equals(value) || "false".equals(value)) {
			if(!typeStr.equals("i1")) {
				errorHandler.addError(fileName, location, null, typeStr, POINTEE_TYPE_DOES_NOT_MATCH_POINTER_CONTAINED_TYPE);
				return ConstantInt.getFalse(compilationContext); 
			}

			if("true".equals(value)){
				return ConstantInt.getTrue(compilationContext); 
			}
			else {
				return ConstantInt.getFalse(compilationContext); 
			}
		}

		if(type instanceof PointerType){
			typeStr = typeStr.substring(0,typeStr.length()-1);
		}

		if (typeStr.equals("i64") || typeStr.equals("i32")
				|| typeStr.equals("i16") || typeStr.equals("i8") || typeStr.equals("i1")) {

			APSInt apsInt = new APSInt(value);
			apInt = apsInt.extOrTrunc(type.getPrimitiveSizeInBits());

			constant = new ConstantInt((IntegerType) type, apInt);
		} 
		else if (typeStr.equals("float") || typeStr.equals("double")) {
			FltSemantics fltSemantics = null;
			if (typeStr.equals("float")){
				fltSemantics = APFloat.IEEEsingle;
			}
			else{
				fltSemantics = APFloat.IEEEdouble;
			}

			APFloat apFloat = new APFloat(fltSemantics, value);
			constant = new ConstantFP(type, apFloat);
		}

		return constant;
	}

	/**
	 * Creates a constant array based on the initial value, type and pointer data.
	 */

	private Value createConstantArray(SourceLocation location, String initialValueStr, ArrayType type, boolean isArray, 
			boolean isString, List<ValueData> ptrDataList) throws Exception {
		Value value = null;

		boolean addNull = initialValueStr.contains("\\00");
		initialValueStr = initialValueStr.replaceAll("\\\\00", "");
		initialValueStr = initialValueStr.replaceAll("\\\\0A", "\n");
		initialValueStr = initialValueStr.replaceAll("\\\\09", "\t");

		if (isArray && !isString) {
			if (initialValueStr.charAt(0) == 'c') {
				// Create value for character array initialization
				initialValueStr = initialValueStr.substring(2, initialValueStr.length() - 1);
				value = ConstantArray.get(initialValueStr, addNull,compilationContext);
			} 
			else{ 
				value = initializeArray(location, initialValueStr,type, ptrDataList);
			}
		} 
		else {
			initialValueStr = initialValueStr.substring(1, initialValueStr.length() - 1);
			value = ConstantArray.get(initialValueStr, addNull,compilationContext);
		}

		return value;
	}

	/**
	 *  Initializes an array from the array type,  the initial value string and a list of pointer data.
	 */
	private Value initializeArray(SourceLocation location, String initialValueStr, ArrayType type, 
			List<ValueData> ptrDataList ) throws Exception {
		List<Constant> values = new ArrayList<Constant>();
		Value array = null;
		int dimension =  type.getDimension();
		Type containedType = type.getContainedType();

		if(dimension > 1 && !initialValueStr.contains("getelementptr")){
			// Multidimensional array
			Value elementValue = null;
			String initialiazer = initialValueStr;
			initialiazer = initialiazer.substring(1, initialiazer.length() - 1);
			String regex = containedType.toString();
			regex = regex.replaceAll("\\[", "\\\\[");
			regex = regex.replaceAll("\\]", "\\\\]");
			initialiazer = initialiazer.replaceFirst(regex, "");
			String strArr[] = initialiazer.split(regex);
			for(String str : strArr){
				str = str.trim();
				if(str.endsWith(",")){
					str = str.substring(0, str.length()-1);
				}

				if(containedType instanceof ArrayType){
					elementValue = initializeArray(location, str, (ArrayType) containedType, null);
				}

				values.add((Constant) elementValue);
			}
		}
		else if(initialValueStr.contains("getelementptr") && ptrDataList != null) {
			// Initial value contains a GEP instruction  
			for(ValueData data : ptrDataList){
				GetElementPtrData ptrData = (GetElementPtrData) data;
				GetElementPtrInst ptrInstr = createGetElementPtr(ptrData);

				List<Value> operandList = new ArrayList<Value>();
				operandList.add(ptrInstr);
				ConstantExpr constantExpr = new ConstantExpr(ptrInstr.getType(), operandList, 
						InstructionID.GET_ELEMENT_PTR, null);
				values.add(constantExpr);
			}
		}
		else {
			// Single dimensional array
			String initialStr = initialValueStr.substring(1,initialValueStr.length() - 1);
			String argList[] = initialStr.split(", ");
			for (String argStr : argList) {
				String typeAndValue[] = argStr.split(" ");
				String typeStr = typeAndValue[0];
				String valueStr = typeAndValue[1];
				Constant constant = (Constant) createConstantValue(location, valueStr, typeStr);
				values.add(constant);
			}
		}

		array = ConstantArray.get(type, values);
		return array;
	}

	/**
	 * Creates a Function object based on function data.
	 */
	private void createFunction(FunctionData function) {
		// Since we are creating a new function, reset all variables (except the global ones)
		localVariableNameValueMap = new LinkedHashMap<String, Value>();
		isFirstInstruction = true;
		basicBlocksList = new ArrayList<BasicBlock>();
		labelAndBasicBlock = new LinkedHashMap<Label,BasicBlock>();
		listOfIncompletePhiNodes = new ArrayList<PhiNode>();
		currentInstrID = 0;
		isFirstBB = true;

		List<Integer> attributeList = new ArrayList<Integer>();
		if(isFirstInstruction){
			cfg = new CFG();
		}

		int lineNum = function.getLineNum();
		SourceLocation location = new SourceLocation(lineNum);
		String functionName = function.getFunctionName();   
		String dataTypeStr = function.getTypeStr();
		Type returnType = null;
		FunctionType funcType = null;
		PointerType ptrToFunc = null;
		if(dataTypeStr == null){
			dataTypeStr = "void";
		}

		// Determine the linkage type
		GlobalValue.LinkageTypes linkageTypes = null;
		String linkageTypeStr = function.getLinkageType();
		if(linkageTypeStr != null && linkageTypeStr.equals("internal")){
			linkageTypes = GlobalValue.LinkageTypes.InternalLinkage;
		}
		else{
			linkageTypes = GlobalValue.LinkageTypes.ExternalLinkage;
		}

		List<ArgumentData> argData = function.getArgumentList();
		boolean hasEllipses = argData.size() > 0 && argData.get(argData.size() -1).isHasEllipses();

		Vector<Type> paramTypes = createParameterList(argData, hasEllipses);
		try{
			returnType = getLLVMType(dataTypeStr, compilationContext, true);
			funcType = Type.getFunctionType(compilationContext, returnType, hasEllipses, paramTypes);
			ptrToFunc = Type.getPointerType(compilationContext, funcType, 0);
		} 
		catch (Exception e) {
			errorHandler.addError( fileName, location, functionName, null, e.getMessage());
		}		

		currentFunction = (Function) getValueFromMap(functionName);
		if(currentFunction == null){
			//This function has not been created earlier, create it now
			String message = CREATING_NEW_FUNCTION + functionName;
			LOG.info(message);
			currentFunction = Function.create(module, ptrToFunc, linkageTypes, CallingConv.C,
					functionName, cfg);
		}
		else{
			if(currentFunction.getParent() == null){
				module.getFunctions().add(currentFunction);
			}
		}

		List<Argument> args = createArgumentsFromArgData(currentFunction, argData);
		for(Argument arg : args){
			currentFunction.getArgumentList().add(arg);

			if(arg.getName() != null){
				String argumentName = "%" + arg.getName();
				addToMap(null, argumentName, arg);
			}
		}

		// Set the return attribute to function
		String returnAttrStr = function.getReturnAttribute();
		int retAttrs = 0 ;
		try {
			if(returnAttrStr != null) {
				if(returnAttrStr.equals("noalias")){
					retAttrs =  Attributes.NoAlias;
				}
				currentFunction.addReturnAttributes(retAttrs);
			}
		} 
		catch (InstructionUpdateException e) {
			errorHandler.addError(fileName, location, returnAttrStr, null, e.getMessage());
		}

		// Set the function attribute
		String functionAttribute = function.getFunctionAttribute().trim();		
		if(functionAttribute != null && functionAttribute.length() != 0) {
			String singleFunctionAttr [] = functionAttribute.split(" ");
			for(String attrStr : singleFunctionAttr){
				Integer attribute = LLVMUtility.getParamAttributeAsIntegerValue(properties, attrStr);
				attributeList.add(attribute);
				try{
					currentFunction.addFnAttr(attribute);
				}
				catch (Exception e) {
					errorHandler.addError(fileName, location, attrStr, null, e.getMessage());
				}
			}
		}

		currentFunction.setBasicBlocks(basicBlocksList);

		// Create the first basic block
		Label label = new Label("0");
		currentBasicBlock = initNewBasicBlock(label,currentFunction,false);
		cfg.setStartNode(currentBasicBlock);

		// Add to the global map 
		globalVariableNameValueMap.put("@"+functionName, currentFunction);

		paramTypes = new Vector<Type>();
		hasEllipses = false;
	}

	private Vector<Type> createParameterList(List<ArgumentData> argDataList, boolean hasEllipses){
		Vector<Type> paramTypes = new Vector<Type>();
		for(ArgumentData argumentData : argDataList){
			SourceLocation location = new SourceLocation(argumentData.getLineNum());
			hasEllipses = argumentData.isHasEllipses();

			if(hasEllipses){
				// TODO Support variable arguments
				break;
			}

			String dataTyprStr = argumentData.getTypeStr();
			Type type = null;
			try{
				type = getLLVMType(dataTyprStr, compilationContext, true);
			}
			catch(Exception e){
				errorHandler.addError(fileName, location, dataTyprStr, null, e.getMessage());
			}

			paramTypes.add(type);

		}

		return paramTypes;

	}

	/**
	 * Creates an argument object and adds it into the current argument list. This is used while creating the
	 * corresponding function object. 
	 */
	private List<Argument> createArgumentsFromArgData(Function parent, List<ArgumentData> argDataList){

		List<Argument> arguments = new ArrayList<Argument>();

		int index = 0;
		for(ArgumentData argumentData : argDataList){
			SourceLocation location = new SourceLocation(argumentData.getLineNum());
			if(argumentData.isHasEllipses()){
				// TODO Support variable arguments
				break;
			}

			String varName = argumentData.getResult();
			String dataTyprStr = argumentData.getTypeStr();
			boolean isVariable = false;
			if(varName != null){
				isVariable = isVariable(varName);
			}

			if(isVariable){
				varName = varName.substring(1);
			}			

			// Determine the type
			Type type = null;
			try{
				type = getLLVMType(dataTyprStr, compilationContext, true);
			}
			catch(Exception e){
				errorHandler.addError(fileName, location, varName, null, e.getMessage());
			}

			Argument argument = new Argument(type, varName, null);
			argument.setParent(parent);

			// Set attributes for this argument if any			
			String argumentAttribute = argumentData.getAttributrs();
			if(argumentAttribute != null){
				String singleArgAttribute []= argumentAttribute.split(" ");
				for(String attrStr : singleArgAttribute){
					attrStr.trim();
					int attribute = LLVMUtility.getParamAttributeAsIntegerValue(properties, attrStr);
					try {
						argument.addAttr(index, attribute);
					} 
					catch (InstructionUpdateException e) {
						errorHandler.addError(fileName, location, attribute + "", null, e.getMessage());
					}
				}
			}

			arguments.add(argument);

			index++;
		}

		return arguments;
	}

	/**
	 * Creates a store instruction and adds it to the current basic block.
	 */
	private void createStoreInstr(StoreInstrData store) {
		String firstOperandStr = store.getFirstOperand();
		String secondOperandStr = store.getSecondOperand();
		String typeStr = store.getTypeStr();
		String isVolatileStr = store.getIsVolatile();
		String atomicOrderingString =  store.getAtomicOrdering();
		int lineNo = store.getLineNum();

		boolean isVolatile = isVolatileStr != null? true : false;

		// Determine atomic ordering
		AtomicOrdering atomicOrdering = null;

		if(atomicOrderingString != null){
			if(atomicOrderingString.equals("acquire")){
				atomicOrdering = AtomicOrdering.Acquire;
			}
			if(atomicOrderingString.equals("unordered")){
				atomicOrdering = AtomicOrdering.Unordered;
			}
			if(atomicOrderingString.equals("monotonic")){
				atomicOrdering = AtomicOrdering.Monotonic;
			}
			if(atomicOrderingString.equals("release")){
				atomicOrdering = AtomicOrdering.Release;
			}
			if(atomicOrderingString.equals("acq_rel")){
				atomicOrdering = AtomicOrdering.AcquireRelease;
			}
			if(atomicOrderingString.equals("seq_cst")){
				atomicOrdering = AtomicOrdering.SequentiallyConsistent;
			}
		}

		SourceLocation location = new SourceLocation(lineNo);
		Value firstOperand = null;
		Value secondOperand = null;
		Type type = null;

		boolean isVariable = isVariable(firstOperandStr); 
		Function function = null;

		if(!isVariable && isFloatingPointType(firstOperandStr)){
			// Is a constant value
			try {
				firstOperand = createConstantValue(location, firstOperandStr, typeStr);
			} 
			catch (Exception e) {
				errorHandler.addError(fileName, location, firstOperandStr, null, LLVMErrorHandler.E_CANNOT_BE_NULL);
			}
		}

		StoreInst storeInst = null;
		if(firstOperand == null){
			firstOperand = getValueFromMap(firstOperandStr);
		}


		type = getLLVMType(typeStr, compilationContext, false);
		if(type == null){
			// Error creating type, return now
			return;
		}

		PointerType ptrToFunctype = null;

		if(firstOperand == null && type instanceof PointerType) {
			// Pointer to a function
			ptrToFunctype = (PointerType) type;
			String functionName = firstOperandStr.substring(1);
			function = Function.create(null, ptrToFunctype, null, null, functionName, cfg);
			globalVariableNameValueMap.put(firstOperandStr, function);
			firstOperand = function;
		}

		secondOperand = getValueFromMap(secondOperandStr);		
		if(secondOperand == null){
			errorHandler.addError(fileName, location, secondOperandStr, null, LLVMErrorHandler.E_NO_DECLARATION);
		}
		try{
			storeInst = StoreInst.create(properties, firstOperand,secondOperand,isVolatile, atomicOrdering,null,currentBasicBlock);
			currentBasicBlock.addInstruction(storeInst);	 
		}
		catch(Exception ex){
			errorHandler.addError(fileName, location, null, null, ex.getMessage());
		}
	}

	private boolean isFloatingPointType(String operand) {
		if(operand == null){
			return false;
		}

		if(operand.matches("((\\+|-)?\\d+(\\.\\d+)?)|( \\d\\.\\d+e\\+\\d\\d)")
				|| operand.matches("\\d\\.\\d+e\\+\\d\\d") 
				|| operand.matches("\\d\\.\\d+e[\\-|\\+]\\d\\d")
				|| operand.matches("0x[0-9|a-f|A-F]+")){
			return true;
		}

		return false;
	}

	/**
	 * Creates an alloca Instruction and adds it to the current basic block.
	 */

	private void createAllocaInstr(AllocaInstrData alloca){
		AllocaInst allocaInst = null;
		String align = alloca.getAlign();
		String typeStr = alloca.getTypeStr();
		String result = alloca.getResult(); 
		SourceLocation location = new SourceLocation();
		Integer alignValue = null;
		String arrayLengthString = alloca.getArrayLength();

		Type llvmType = null;
		try{
			llvmType = getLLVMType(typeStr, compilationContext, true);
		}
		catch(Exception e){
			errorHandler.addError(fileName, location, result, null, e.getMessage());
		}

		Value arrayLengthValue = getValueFromMap(arrayLengthString);
		try{
			String name = handleName(result);
			if(align != null) {
				alignValue  = Integer.parseInt(align);
			}

			allocaInst = AllocaInst.create(properties, llvmType, name, alignValue, arrayLengthValue, currentBasicBlock);
			currentBasicBlock.addInstruction(allocaInst);
			addToMap(new SourceLocation(alloca.getLineNum()), result,allocaInst);
			updateIncompletePhiNode(allocaInst,result);
		}
		catch(Exception e){
			errorHandler.addError(fileName, location, result, null, e.getMessage());
		}
	}

	/** This function will be called after the creation of every instruction. This enables
	 *  correct update of a phi node that has not been fully initialized since there could
	 *  be incoming values for a phi node as a consequence of loops.
	 */
	private void updateIncompletePhiNode(Instruction instr, String name) {

		if(name == null || listOfIncompletePhiNodes.isEmpty()){
			return;
		}

		for(PhiNode phiNode : listOfIncompletePhiNodes){
			int numIncomingValues = phiNode.getNumIncomingValues();

			for(int i = 0; i < numIncomingValues; i++){
				try{
					Value value = phiNode.getIncomingValue(i);
					String valueName = value.getName();
					if(valueName != null && valueName.equals(name)){
						phiNode.setPhiOperandAt(i, instr);
					}
				}
				catch(InstructionDetailAccessException ide){
					assert false : UNABLE_TO_UPDATE_PHI_NODE;
				LOG.error(UNABLE_TO_UPDATE_PHI_NODE);
				}
			}
		}
	}

	/**
	 * Creates a load instruction and adds it to the current basic block.
	 */
	private void createLoadInstr(LoadInstrData load){
		String name = load.getPointerName();
		String result = load.getResult();

		SourceLocation location = new SourceLocation(load.getLineNum());
		LoadInst loadInst = null;
		Value value = getValueFromMap(name);
		if(value == null){
			//Undefined value?
			errorHandler.addError(fileName, location, name, null, LLVMErrorHandler.E_NO_DECLARATION);
			return;
		}

		// Confirm that the pointer type refers to the pointee type
		String pointeeTypeStr = load.getPointeeTypeStr();
		String pointerTypeStr = load.getTypeStr();

		Type pointerType = getLLVMType(pointerTypeStr, compilationContext, false); 

		Type pointeeType = getLLVMType(pointeeTypeStr, compilationContext, false); 

		try {
			Type containterType = Type.getPointerType(compilationContext, pointeeType, 0);
			if(containterType != pointerType) {
				errorHandler.addError(fileName, location, result, null, POINTEE_TYPE_DOES_NOT_MATCH_POINTER_CONTAINED_TYPE);
				return;
			}
		} catch (TypeCreationException ex) {
			errorHandler.addError(fileName, location, result, null, ex.getMessage());
		}

		try{
			String lresult = handleName(result);
			loadInst = LoadInst.create(properties, value,lresult, false, null, null, load.getPointeeTypeStr(), currentBasicBlock);
			currentBasicBlock.addInstruction(loadInst);
			addToMap(new SourceLocation(load.getLineNum()), result, loadInst);
			updateIncompletePhiNode(loadInst,result);
		}
		catch(Exception ex){
			errorHandler.addError(fileName, location, result, null, ex.getMessage());
		} 
	}

	/**
	 * Creates a GEP instruction and adds it to the current basic block.
	 */
	private GetElementPtrInst createGetElementPtr(GetElementPtrData data) {
		String result = data.getResult();
		String listVsIndexStr = data.getListVsIndexStr();
		String elementName = data.getElementName();
		String ptrTypeString = data.getPtrType();
		SourceLocation location = new SourceLocation(data.getLineNum());

		GetElementPtrInst instr = null;
		Value pointerOperand = getValueFromMap(elementName);	
		if(elementName.equals("undef") && pointerOperand == null){
			String typeName = ptrTypeString.substring(0, ptrTypeString.length()-1);
			StructType structureType = structAndType.get(typeName);
			PointerType type = null;
			try {
				type = Type.getPointerType(compilationContext, structureType, 0);
			} 
			catch (TypeCreationException e) {
				String message = UNABLE_TO_CREATE_TYPE + typeName;
				assert false : message;
				LOG.error(message);
				return null;
			}

			pointerOperand = UndefValue.createOrGet(type);
		}

		Type type = null; Value value = null;
		HashMap<Value, Type> indexAndTypeMap = new LinkedHashMap<Value, Type>();
		List<Pair<Value, Type>> valueAndTypePairs = new ArrayList<Pair<Value,Type>>();
		boolean isInBounds = false;
		String isInBoundsStr = data.getIsInBound();
		if(isInBoundsStr != null){
			isInBounds = true;
		}

		String valueAndTypeArray[] = listVsIndexStr.split(", ");
		int i = 0,index = 0;
		boolean isBaseElement = true;
		ArrayType arrayType = null;
		PointerType pointerType = null;
		StructType structType = null;
		Type type1 = null, elementType = null;

		for(String valueVsTypeStr : valueAndTypeArray) {
			String valueVsType[] = valueVsTypeStr.split(" ");
			String typeStr = valueVsType[0];
			String valueStr = valueVsType[1];

			if (isVariable(valueStr)) {
				value = getValueFromMap(valueStr);
				Type valueType = value.getType();
				Type usedType = null;
				try {
					usedType = getLLVMType(typeStr, compilationContext, false);
				} 
				catch (Exception e) {
					errorHandler.addError(fileName, location, typeStr, null, e.getMessage());
				}

				if(valueType != usedType){
					errorHandler.addError(fileName, location, valueStr, typeStr, LLVMErrorHandler.E_DEFINED_WITH_TYPE);
				}

				valueStr = valueStr.substring(1);
			} 
			else{
				try {
					value = createConstantValue(location, valueStr, typeStr);
				} 
				catch (Exception e) {
					errorHandler.addError(fileName, location, valueStr, null, e.getMessage());
				}
			}

			if(isBaseElement){
				type1 = pointerOperand.getType();
				pointerType = (PointerType) type1;
				elementType = pointerType.getContainedType();
				if(elementType.isArrayType() || elementType.isStructType()) {
					elementType = pointerType.getContainedType();
					type = elementType;
				}
				else {
					type = pointerOperand.getType();
					elementType = type;
				}
				isBaseElement = false;
			}
			else {
				if(valueStr.matches("\\d+")){
					index = Integer.parseInt(valueStr);
				}

				if(elementType.isStructType()) {
					structType = (StructType) elementType;
					type = structType.getTypeAtIndex(index);
					elementType = type;
				}
				else  {					
					arrayType = (ArrayType) elementType ;
					type = arrayType.getContainedType();
					elementType = type;
				}	
			}

			indexAndTypeMap.put(value, type); 
			i++;
		}

		Set<Map.Entry<Value, Type>> entries = indexAndTypeMap.entrySet();
		Iterator<Entry<Value, Type>> iterator = entries.iterator();
		while (iterator.hasNext()) { 
			Map.Entry<Value, Type> entry = iterator.next(); 
			Pair<Value, Type> pairedClass = new Pair<Value, Type>(entry.getKey(), entry.getValue());
			valueAndTypePairs.add(pairedClass);
		}

		try {
			String	lname = handleName(result);
			instr = GetElementPtrInst.create(lname , pointerOperand, valueAndTypePairs, data.getPointeeTypeStr(), currentBasicBlock);

			instr.setIsInBounds(isInBounds);
			if(result != null){
				addToMap(new SourceLocation(data.getLineNum()), result, instr);
				currentBasicBlock.addInstruction(instr);
			}

			updateIncompletePhiNode(instr,result);

		} 
		catch (InstructionCreationException e) {
			errorHandler.addError(fileName, location,result , null, e.getMessage());		
		}

		return instr;
	}

	/**
	 * Creates a select instruction and adds it to the current basic block.
	 */
	private void createSelectInstr(SelectInstrData data) {
		String conditionalValueStr = data.getConditionalValue();
		int lineNo = data.getLineNum();
		String result = data.getResult();
		String typeStr = data.getTypeStr();

		Value condition = null;
		Value first = null;
		Value second = null;
		SourceLocation location = new SourceLocation();
		location.setLineNum(lineNo);

		String dataTypeOfValues = data.getFirstType();

		try{
			String firstOprstr = data.getFirstValue();
			String secondOprStr = data.getSecondValue();
			if(isVariable(firstOprstr)){
				first = getValueFromMap(firstOprstr );
				if(first == null){
					errorHandler.addError(fileName, location, firstOprstr, result, LLVMErrorHandler.E_NO_DECLARATION);
				}
			}
			else{
				first = createConstantValue(location, firstOprstr, dataTypeOfValues);
			}

			if(isVariable(secondOprStr)){			
				second = getValueFromMap(secondOprStr);
				if(second == null){
					errorHandler.addError(fileName, location, secondOprStr, result, LLVMErrorHandler.E_NO_DECLARATION);
				}
			}
			else{
				second = createConstantValue(location, secondOprStr, dataTypeOfValues);
			}

			if(isVariable(conditionalValueStr)){			
				condition = getValueFromMap(conditionalValueStr);
				if(condition == null){
					errorHandler.addError(fileName, location, conditionalValueStr, result, LLVMErrorHandler.E_NO_DECLARATION);
				}
			}
			else{
				condition = createConstantValue(location, conditionalValueStr, typeStr);
			}
		}
		catch(Exception e) {
			errorHandler.addError(fileName, location, result, null, e.getMessage());
		}

		SelectInst selectInst = null;
		try{
			selectInst = SelectInst.create(condition, first, second, null, currentBasicBlock);
			currentBasicBlock.addInstruction(selectInst);
			addToMap(new SourceLocation(data.getLineNum()), result, selectInst);
		}
		catch(InstructionCreationException ice){
			errorHandler.addError(fileName, location, null, null, ice.getMessage());
		}
	}

	/**
	 * Creates a binaryOperator instruction and adds it to the current basic block.
	 */
	private void createBinaryOprInstr(BinaryOprData binaryOpr){
		String typeStr = binaryOpr.getTypeStr();
		String result = binaryOpr.getResult();

		String firstOprstr = binaryOpr.getFirstOperand();
		String secondOprStr = binaryOpr.getSecondOperand();
		BinaryOperatorID binOprId = binaryOpr.getBinOpratorID();
		SourceLocation location = new SourceLocation(binaryOpr.getLineNum());
		Type type = null;
		Value firstOprValue = null, secondOprValue = null;
		try{
			if(isVariable(firstOprstr)) {
				firstOprValue = getValueFromMap(firstOprstr);
				if(firstOprValue == null){
					errorHandler.addError(fileName, location, firstOprstr, result, LLVMErrorHandler.E_NO_DECLARATION);
				}
			}
			else{
				firstOprValue = createConstantValue(location, firstOprstr, typeStr);
			}

			if(isVariable(secondOprStr)){			
				secondOprValue = getValueFromMap(secondOprStr);
				if(secondOprValue == null){
					errorHandler.addError(fileName, location, secondOprStr, result, LLVMErrorHandler.E_NO_DECLARATION);
				}
			}
			else{
				secondOprValue = createConstantValue(location, secondOprStr, typeStr);
			}

			type = getLLVMType(typeStr,compilationContext,true);  	
		}
		catch(Exception e) {
			errorHandler.addError(fileName, location, result, null, e.getMessage());
		}

		BinaryOperator binaryOperator = null;

		String flag = binaryOpr.getFlag();
		boolean noSignedWrap = false;
		boolean noUnsignedWrap = false;
		boolean exact = false;
		if(flag != null){
			if("nsw".equals(flag)){
				noSignedWrap = true;
			}
			else if("nuw".equals(flag)){
				noSignedWrap = true;
			}
			else{
				exact = true;
			}
		}

		try {
			String lresult = handleName(result);
			binaryOperator = BinaryOperator.create(binOprId, type, lresult, firstOprValue,
					secondOprValue, noSignedWrap, noUnsignedWrap, exact,currentBasicBlock);
			currentBasicBlock.addInstruction(binaryOperator);
			addToMap(new SourceLocation(binaryOpr.getLineNum()), result, binaryOperator);
			updateIncompletePhiNode(binaryOperator,result);
		} 
		catch (Exception e) {
			String mainErrorMsg = UNKNOWN_ERROR + e.getMessage();
			errorHandler.addError(fileName, location, result, null, mainErrorMsg);
		}
	}

	/**
	 * Creates icmp / fcmp instruction and adds it to the current basic block.
	 */
	private void createIcmpInstr(CmpData icmpData){
		String result = icmpData.getResult();
		String predicateStr = icmpData.getPredicate();
		String firstOprstr = icmpData.getFirstOprand();
		String secondOprStr = icmpData.getSecondOprand();
		String typeStr = icmpData.getTypeStr();
		String cmp = icmpData.getCmpType();
		SourceLocation location = new SourceLocation(icmpData.getLineNum());

		boolean isIcmp = cmp.equals("icmp")? true : false;
		Value lhsValue = null, rhsValue = null;
		ICmpInst icmp = null;
		FCmpInst fcmp = null;
		CmpInst.Predicate predicate = null;
		if(predicateStr.equals("slt")){
			predicate = CmpInst.Predicate.ICMP_SLT;
		}
		else if(predicateStr.equals("ne")){
			predicate = CmpInst.Predicate.ICMP_NE;
		}
		else if(predicateStr.equals("eq")){
			predicate = CmpInst.Predicate.ICMP_EQ;
		}
		else if(predicateStr.equals("sgt")){
			predicate = CmpInst.Predicate.ICMP_SGT;
		}
		else if(predicateStr.equals("sle")){
			predicate = CmpInst.Predicate.ICMP_SLE;
		}
		else if(predicateStr.equals("sge")){
			predicate = CmpInst.Predicate.ICMP_SGE;
		}
		else if(predicateStr.equals("olt")){
			predicate = CmpInst.Predicate.FCMP_OLT;
		}
		else if(predicateStr.equals("ogt")){
			predicate = CmpInst.Predicate.FCMP_OGT;
		}
		else if(predicateStr.equals("one")){
			predicate = CmpInst.Predicate.FCMP_ONE;
		}
		else if(predicateStr.equals("oeq")){
			predicate = CmpInst.Predicate.FCMP_OEQ;
		}
		else if(predicateStr.equals("oge")){
			predicate = CmpInst.Predicate.FCMP_OGE;
		}

		try{
			if(isVariable(firstOprstr)) {
				lhsValue = getValueFromMap(firstOprstr);
				if(lhsValue == null){
					errorHandler.addError(fileName, location, firstOprstr, null, LLVMErrorHandler.E_NO_DECLARATION);
				}
			}
			else{
				lhsValue = createConstantValue(location, firstOprstr, typeStr);
			}

			if(isVariable(secondOprStr) || secondOprStr.equals("null")){
				rhsValue = getValueFromMap(secondOprStr);
				if(rhsValue == null){
					errorHandler.addError(fileName, location, secondOprStr, null, LLVMErrorHandler.E_NO_DECLARATION);
				}
			}
			else{
				rhsValue = createConstantValue(location, secondOprStr, typeStr);
			}

			String lresult = handleName(result);
			if(isIcmp){
				icmp =ICmpInst.create(predicate, lhsValue, rhsValue, lresult , currentBasicBlock);
			}
			else{
				fcmp = FCmpInst.create(predicate, lhsValue, rhsValue, lresult, currentBasicBlock);
			}
		}
		catch(Exception e) {
			errorHandler.addError(fileName, location, result, null, e.getMessage());
		}

		Instruction inst = fcmp;
		if(isIcmp){
			inst = icmp;
		}

		if(inst != null){
			currentBasicBlock.addInstruction(inst);
			addToMap(new SourceLocation(icmpData.getLineNum()), result, inst);
			updateIncompletePhiNode(inst, result);
		}
	}

	/**
	 * Creates a branch instruction and adds it to the current basic block.
	 * Also updates the current basic block based on the branch instruction.
	 */
	private void createBranchInstr(BranchInstrData branchInstrData){
		String ifTrueStr = branchInstrData.getIfTrue();
		String ifFalseStr = branchInstrData.getIfFalse();
		String conditionStr= branchInstrData.getCondition();

		SourceLocation location =  new SourceLocation(branchInstrData.getLineNum());
		Value condition = null;
		if(conditionStr != null) {
			condition = getValueFromMap(conditionStr);
			if(condition == null){
				try {
					condition = createConstantValue(location, conditionStr, "i1");
				} 
				catch (Exception e) {
					errorHandler.addError(fileName, location, conditionStr, null, e.getMessage());
				}
			}
		}

		BasicBlock ifTrue = null;
		BasicBlock ifFalse = null;
		BranchInst branchInst = null;
		Label labelTrue = new Label(ifTrueStr);
		ifTrue = initNewBasicBlock(labelTrue, currentFunction,false);
		if(ifFalseStr == null) {
			branchInst = BranchInst.create(ifTrue, currentBasicBlock, compilationContext);
		}
		else{
			Label labelFalse = new Label(ifFalseStr);
			ifFalse = initNewBasicBlock(labelFalse, currentFunction,false);
			try {
				branchInst = BranchInst.create(ifTrue, ifFalse, condition, currentBasicBlock, compilationContext);
			} 
			catch (InstructionCreationException e) {
				errorHandler.addError(fileName, location, null, null, e.getMessage());
			}
		}

		currentBasicBlock.addInstruction(branchInst);
		basicBlocksList.add(currentBasicBlock);  
		updateCurrentBB();
	}

	/**
	 * Creates a return instruction and adds it to the current basic block.
	 */

	private void createReturnInstr(ReturnInstrData returnInstrData) {
		String returnDataStr = returnInstrData.getReturnData();
		String retTypeStr =  returnInstrData.getReturnType();
		int lineNum = returnInstrData.getLineNum();	
		SourceLocation location = new SourceLocation(lineNum);
		boolean isVariable = false;

		if(returnDataStr != null) {
			// Check if the value being returned is a variable only if the function
			// is returning something.
			isVariable = isVariable(returnDataStr);
		}

		Value retValue = null;
		if(returnDataStr != null) {
			if (isVariable){
				retValue = getValueFromMap(returnDataStr);	
				returnDataStr =  returnDataStr.substring(1);
			}
			else{
				try {
					retValue = createConstantValue(location, returnDataStr, retTypeStr);
				} 
				catch (Exception e) {
					errorHandler.addError(fileName, location, returnDataStr, retTypeStr, e.getMessage());
				}
			}
		}

		ReturnInst returnInst = null;
		try	{
			returnInst = ReturnInst.create(compilationContext, retValue, currentBasicBlock);
		}
		catch(Exception e) {
			errorHandler.addError(fileName, location, returnDataStr, null, e.getMessage());
		}

		currentBasicBlock.addInstruction(returnInst);
		basicBlocksList.add(currentBasicBlock);
	}

	/**
	 * Creates an unreachable instruction and adds it to the current basic block.
	 */
	private void createUnreachableInstr(UnreachableInstrData data) {
		SourceLocation location =  new SourceLocation(data.getLineNum());
		UnreachableInstr instr = null;
		try {
			instr = UnreachableInstr.create(currentBasicBlock);
		}
		catch (InstructionCreationException e) {
			errorHandler.addError(fileName, location, "Unreachable", null, e.getMessage());
		}

		currentBasicBlock.addInstruction(instr);
		basicBlocksList.add(currentBasicBlock);
		updateCurrentBB();	
	}

	/**
	 * Creates a switch instruction and adds it to the current basic block.
	 */
	private void createSwitchInstr(SwitchInstrData data) {
		String switchOnStr = data.getSwitchOn();
		String defaultBBStr = data.getDefaultBB();
		SourceLocation location =  new SourceLocation(data.getLineNum());
		Value switchOn = getValueFromMap(switchOnStr);
		Label defaultLabel = new Label(defaultBBStr.substring(1));
		BasicBlock defaultBB = null;
		defaultBB = initNewBasicBlock(defaultLabel, currentFunction, false);
		SwitchInst inst = null;

		try{
			inst = SwitchInst.create(switchOn, defaultBB, currentBasicBlock);
		}
		catch(Exception e){
			errorHandler.addError(fileName, location, switchOnStr, defaultBBStr, e.getMessage());
		}

		switchInstr = inst;
		currentBasicBlock.addInstruction(inst);
		basicBlocksList.add(currentBasicBlock);	
		updateCurrentBB();
	}

	/**
	 * Creates switch cases for the current switch instruction.	
	 */
	private void createCaseForCurrentSwitchInstruction(SwitchCasesData casesData) {
		String typStr = casesData.getTypeStr();
		String conditionStr = casesData.getCondition();
		String targetStr = casesData.getTarget().substring(1);
		Label targetLabel = new Label(targetStr);
		SourceLocation location = new SourceLocation(casesData.getLineNum());
		BasicBlock targetBB = initNewBasicBlock(targetLabel, currentFunction,false);

		Value caseConst = null;
		try {
			caseConst = createConstantValue(location, conditionStr, typStr);
			switchInstr.addCase((ConstantInt) caseConst, targetBB);
		} 
		catch (Exception e1) {
			errorHandler.addError(fileName, location, conditionStr, null, e1.getMessage());
		}
	}

	/**
	 * Creates a global variable for the current module.
	 */
	private void createGlobalVariable(GlobalVariableData data) {
		String name = data.getResult(); 
		String typStr = data.getTypeStr();
		String initialValueStr = data.getInitialValue();
		String linkageTypeStr = data.getLinkageType();
		String alignStr = data.getAlign();
		String varTypeStr = data.getVariableType();
		CastInstrData castInstrData = data.getCastData();
		List<ValueData> ptrDataList = data.getPtrDataList();

		SourceLocation location =  new SourceLocation(data.getLineNum());
		boolean isArray = false;
		boolean isString = false;
		boolean isStructObj = false;
		boolean isUnnamed = data.isUnnamed();
		int align = 0;
		if(alignStr != null){
			align = Integer.parseInt(alignStr);
		}

		if(varTypeStr.equals("array")) {
			isArray = true;
		}
		else if(varTypeStr.equals("string_constant")) {
			isString = true;
		}
		else if (varTypeStr.equals("object")) {
			isStructObj = true;
		}		

		boolean isConstant = false;  
		String isConstantStr = data.getIsConstant();
		if(isConstantStr != null){
			isConstant = true;
		}

		GlobalValue.LinkageTypes linkageTypes = getLinkageType(linkageTypeStr, location);

		Type containedType = null;
		if(isStructObj){
			containedType = structAndType.get('%' + typStr);
		}
		else{
			try {
				containedType = getLLVMType(typStr, compilationContext, false);
			} 
			catch (Exception e1) {
				errorHandler.addError(fileName, location, typStr, null, e1.getMessage());
			}
		}

		PointerType ptrType = null;
		try{
			ptrType = Type.getPointerType( compilationContext, containedType,0);
		}
		catch(Exception e) {
			errorHandler.addError(fileName, location, typStr, null, e.getMessage());
		}

		List<Value> valueList = new ArrayList<Value>();

		if(initialValueStr != null) {	
			if(initialValueStr.equals("zeroinitializer")){
				valueList.add(null);
			}
			else{
				valueList = createValueListFromInitializer(initialValueStr, 
						typStr, isArray, isString,  containedType, ptrDataList, location);
			}
		}
		else if (castInstrData != null) {
			createCastInstr(castInstrData);
			valueList.add(castInstForGlobalVariable);
			castInstForGlobalVariable = null;			
		}
		else if (!ptrDataList.isEmpty()) {
			GetElementPtrData ptrData = (GetElementPtrData) ptrDataList.get(0);
			GetElementPtrInst getElmPtrInstr = createGetElementPtr(ptrData);
			valueList.add(getElmPtrInstr);
		}
		else{ 
			// Invalid state for global variable definition
			valueList.add(null);
		}

		GlobalVariable globalVariable = null;
		try{
			String lname = handleName(name);
			globalVariable = new GlobalVariable(module, ptrType, isConstant, linkageTypes, valueList, lname, null, false);
			globalVariable.setAlignment(align);

			if(isUnnamed==true){
				globalVariable.setUnnamedAddr(true);
			}
		}
		catch(Exception e) {
			errorHandler.addError(fileName, location, typStr, null, e.getMessage());
		}

		globalVariableNameValueMap.put(name, globalVariable);
	}

	private GlobalValue.LinkageTypes getLinkageType(String linkageTypeStr, SourceLocation location){

		GlobalValue.LinkageTypes linkageTypes = null;

		if(linkageTypeStr != null){
			if(linkageTypeStr.equals("common")){
				linkageTypes = GlobalValue.LinkageTypes.CommonLinkage;
			}
			else if(linkageTypeStr.equals("external")){
				linkageTypes = GlobalValue.LinkageTypes.ExternalLinkage;
			}
			else if(linkageTypeStr.equals("internal")){
				linkageTypes = GlobalValue.LinkageTypes.InternalLinkage;
			}
			else if(linkageTypeStr.equals("private")){
				linkageTypes = GlobalValue.LinkageTypes.PrivateLinkage;
			}
			else{
				// Unknown linkage type
				String message = UNKNOWN_LINKAGE_TYPE + linkageTypeStr;
				assert false : message;
				LOG.error(message);
				return null;
			}
		}

		return linkageTypes;
	}

	private List<Value> createValueListFromInitializer(String initialValueStr, 
			String typStr, boolean isArray, boolean isString, Type containedType,
			List<ValueData> ptrDataList, SourceLocation location){

		Value value = null;
		List<Value> valueList = new ArrayList<Value>();

		if(!(isArray || isString)){
			// Is a constant value
			try {
				value = createConstantValue(location, initialValueStr, typStr);
			} 
			catch (Exception e) {
				errorHandler.addError(fileName, location, '%'+typStr, null, e.getMessage());
			}
		}

		if(containedType instanceof StructType && ((StructType)containedType).isUnion()) {
			String initialValStrWithoutbraces = initialValueStr.substring(1, initialValueStr.length()-1);
			String[] typeAndValue = initialValStrWithoutbraces.trim().split("\\] \\[", 2);

			if(typeAndValue.length > 1) {	
				// Initialization of an union using multidimensional array
				isArray = true;
				String typeString = typeAndValue[0];
				typeString = typeString + "]";
				initialValueStr = "[" + typeAndValue[1]; 
				try {
					Type initializeArrayType = getLLVMType(typeString, compilationContext, false);
					value = createConstantArray(location, initialValueStr, (ArrayType) initializeArrayType,
							isArray, isString, ptrDataList);
				} 
				catch (Exception e) {
					errorHandler.addError(fileName, location, typeString, null, e.getMessage());
				}
			}
		}

		if(containedType instanceof ArrayType){
			try {
				value = createConstantArray(location, initialValueStr, (ArrayType)containedType,
						isArray, isString, ptrDataList);
			} catch (Exception e) {
				errorHandler.addError(fileName, location, initialValueStr, null, e.getMessage());
			}
		}

		if(initialValueStr.charAt(0)=='{' || containedType instanceof StructType) {
			List<Constant> listOfConstants = new ArrayList<Constant>();

			if(value != null){
				listOfConstants.add((Constant) value);
			}				
			else {
				listOfConstants = getConstantsListForInitializer(initialValueStr, typStr, ptrDataList, location);
			}

			ConstantStruct constantStruct = (ConstantStruct) ConstantStruct.get((StructType)containedType, listOfConstants);
			valueList.add(constantStruct);
		}
		else{
			valueList.add(value);
		}

		return valueList;
	}

	private List<Constant> getConstantsListForInitializer(String initialValueStr,
			String typStr, List<ValueData> ptrDataList, SourceLocation location){
		List<Constant> listOfConstants = new ArrayList<Constant>();

		String initialValStrWithoutbraces = initialValueStr.substring(1, initialValueStr.length()-1);
		String[] valuesStr = initialValStrWithoutbraces.split(", ");

		for(String typeandValueStr: valuesStr) {
			StringBuffer typeAndValueBuffer = new StringBuffer(typeandValueStr);
			typeAndValueBuffer = typeAndValueBuffer.reverse();
			String typeAndValueReverse = typeAndValueBuffer.toString();
			String[] typeAndValueArray = typeAndValueReverse.trim().split(" ", 2);

			String paraTypeStrReverse = typeAndValueArray[1];
			String nameStrRevers = typeAndValueArray[0];
			StringBuffer parameterBuff = new StringBuffer(paraTypeStrReverse);
			StringBuffer nameBuff = new StringBuffer(nameStrRevers);
			parameterBuff = parameterBuff.reverse();
			nameBuff = nameBuff.reverse();
			String typeStr = parameterBuff.toString().trim();
			String valueStr = nameBuff.toString().trim();
			Value value = null;
			if(!valueStr.equals("undef")){
				try {
					if(typeStr.charAt(0)=='[') {
						ArrayType dummyArrayType = null;
						value = createConstantArray(location, valueStr, dummyArrayType,true,false, ptrDataList);
					} 
					else{
						value = createConstantValue(location, valueStr, typeStr);
					}
				}
				catch (Exception e) {
					errorHandler.addError(fileName, location, typStr, null, e.getMessage());
				}

				listOfConstants.add((Constant)value);
			}
		}

		return listOfConstants;
	}

	/**
	 * Creates a phi node instruction and adds it to the current basic block.
	 */
	private void createPhiNode(PhiNodeData data) {
		String name = data.getResult();
		String typeStr = data.getTypeStr();
		String incomingStr = data.getIncomingValueAndBBPairs();
		SourceLocation location = new SourceLocation(data.getLineNum());
		PhiNode node = null;
		Type type = null;
		boolean isIncompletePhiNode = false;
		int numPredecessors = 0;

		try {
			type = getLLVMType(typeStr, compilationContext, false);
		} 
		catch (Exception e) {
			errorHandler.addError(fileName, location, typeStr, null, e.getMessage());
		}

		String predecessors[] = incomingStr.split("\\], \\[");
		numPredecessors = predecessors.length;
		for(int i = 0;i < numPredecessors; i++) {
			String valueStr = null;
			String basicBlockString = null;
			Value value = null;
			BasicBlock basicBlock = null;
			Label label = null;
			String valueAndBBStr = predecessors[i].trim();
			String [] valueAndBB = valueAndBBStr.split(",");
			valueStr = valueAndBB[0].trim();
			if(i == 0){
				valueStr = valueStr.substring(1).trim();
			}

			basicBlockString = valueAndBB[1].trim();
			basicBlockString = basicBlockString.substring(1);
			if(i == numPredecessors - 1){
				basicBlockString = basicBlockString.substring(0, basicBlockString.length()-1).trim();
			}
			try{
				if(isVariable(valueStr)) {
					value = getValueFromMap(valueStr);
					if(value == null){
						value = new Value(type);
						value.setName(valueStr);
						isIncompletePhiNode = true;
					}
				}
				else{
					value = createConstantValue(location, valueStr, typeStr);
				}
			}
			catch (Exception e) {
				errorHandler.addError(fileName, location, valueStr, null, e.getMessage());
			}

			label = new Label(basicBlockString);
			basicBlock = labelAndBasicBlock.get(label);
			if(basicBlock == null){
				basicBlock = initNewBasicBlock(label, currentFunction, true);
			}

			String lname = handleName(name);
			if (i == 0){
				node = PhiNode.create(type, lname , numPredecessors, value, currentBasicBlock);
			}
			try {
				node.addIncoming(value, basicBlock);
			} 
			catch (InstructionUpdateException e) {
				errorHandler.addError(fileName, location, valueStr, null, e.getMessage());
			}
		}

		currentBasicBlock.addInstruction(node);
		addToMap(new SourceLocation(data.getLineNum()), name, node);
		updateIncompletePhiNode(node,name);
		if(isIncompletePhiNode){
			listOfIncompletePhiNodes.add(node);
		}
	}

	/**
	 * Creates a structure or a union.
	 */
	private void createStructureOrUnion(StructureData data) {
		String result = data.getResult();
		String elementListStr = data.getStructElements();
		int lineNum = data.getLineNum();
		SourceLocation location = new SourceLocation(lineNum);
		boolean isUnion = false;
		String[] nameArray = result.split("\\.");
		if(nameArray[0].equals("union")){
			isUnion = true;
		}

		Type type = null;
		String elementStrArray[] = elementListStr.split(", ");
		int noOfElement = elementStrArray.length;
		Type[] typeArray = new Type[noOfElement];
		int i = 0;
		for(String typeStr : elementStrArray) {
			try {
				type = getLLVMType(typeStr, compilationContext, false);
			} 
			catch (Exception e) {
				errorHandler.addError(fileName, location, typeStr, null, e.getMessage());
			}

			if(type == null) {
				try {
					Type[] emptyTypeArray = new Type[0];
					type = Type.getStructType(compilationContext, false, typeStr, false,emptyTypeArray);
				} 
				catch (TypeCreationException e) {
					errorHandler.addError(fileName, location, typeStr, null, e.getMessage());
				}
			}

			typeArray[i] = type;
			i++;
		}

		StructType struct = null;
		NamedType namedType = null;
		try {
			struct = Type.getStructType(compilationContext, false, result, isUnion, typeArray);
			handleSelfReferentialStructure(struct, null);
			namedType = new NamedType(struct, struct.getName());
		} 
		catch (TypeCreationException e) {
			errorHandler.addError(fileName, location, result, null, e.getMessage());
		}

		namedTypes.add(namedType);
		String mapname = '%' + result;
		structAndType.put(mapname, struct);
		Set<Map.Entry<String, StructType>> entries = structAndType.entrySet();
		Iterator<Map.Entry<String, StructType>> iterator = entries.iterator();

		while (iterator.hasNext())  { 
			Map.Entry<String, StructType> entry = iterator.next();  
			StructType struct1 = entry.getValue();
			List<Type> list = struct1.getContainedTypes();

			ListIterator<Type> iterator1 = list.listIterator();
			int index=0;
			while(iterator1.hasNext()) {
				Type type2 = iterator1.next();
				if(type2.isStructType()) {
					StructType structType =(StructType) type2;
					String nameInStruct = structType.getName();
					if(result.equals(nameInStruct)) {
						list.remove(index);
						list.add(i, struct);
					}
				}
				index++;
			}
		}
	}

	/**
	 * Handles self referential structures.
	 */
	private void handleSelfReferentialStructure(StructType structType, StructType structType3) {
		ArrayList<Type> containedTypes = null;

		if(structType3 == null){
			containedTypes = structType.getContainedTypes();
		}
		else{
			containedTypes = structType3.getContainedTypes();
		}

		int index = 0;
		for(Type type : containedTypes){
			if(type.isStructType()){
				StructType structType2 = (StructType)type;
				if(structType2.getName().equals(structType.getName())){
					structType.addContainedTypeAtIndex(structType, index);
					structType.setSelfReferencial(true);
				}
				else{
					handleSelfReferentialStructure(structType, structType2);
				}
			}
			else if(type.isPointerType()){
				PointerType pointerType = (PointerType)type;
				handleSelfRefStructForPtrType(structType,pointerType, index);
			}
			else if(type.isArrayType()){
				ArrayType arrayType = (ArrayType)type;
				Type containedType = arrayType.getActualContainedType();
				if(containedType.isStructType()){
					StructType structType2 = (StructType)containedType;
					if(structType2.getName().equals(structType.getName())){
						structType.addContainedTypeAtIndex(structType, index);
						structType.setSelfReferencial(true);
					}
				}
			}
			index++;
		}
	}

	private void handleSelfRefStructForPtrType(StructType structType, PointerType pointerType, int index) {
		Type containedType = pointerType.getContainedType();
		if(containedType.isStructType()){
			StructType structType2 = (StructType)containedType;
			if(structType2.getName().equals(structType.getName())){
				pointerType.setContainedType(structType);
				structType.setSelfReferencial(true);
			}
			else{
				handleSelfReferentialStructure(structType, structType2);
			}
		}
		else if(containedType.isPointerType()){
			PointerType pointerType2 = (PointerType)containedType;
			handleSelfRefStructForPtrType(structType,pointerType2, index);
		}
	}

	/**
	 * Creates a call instruction and adds it the to current basic block.
	 */
	private void createCallInstr(CallInstrData data)  {

		String name = data.getResult();
		String typeStr = data.getTypeStr();
		String functionName = data.getFunctionName();
		String parameter = data.getFunctionParameters();
		String functionAttribute = data.getFunctionAttributes();
		String isTailStr = data.getIsVarArguments();
		SourceLocation location = new SourceLocation(data.getLineNum());
		boolean isTail = isTailStr == null ? false : true ;
		CallInst inst = null;
		FunctionType functionType = null;
		Vector<Type> args = new Vector<Type>();
		List<Value> valueList = new ArrayList<Value>();
		Type type = null;
		try {
			type = getLLVMType(typeStr, compilationContext, true);
		} 
		catch (Exception e1) {
			errorHandler.addError(fileName, location, typeStr, null, e1.getMessage());
		}
		Value functionValue = null;
		boolean isPointerToFunc = false;

		if(!parameter.equals("")) {
			String[] parameterArray = parameter.split(",");

			for(String typeAndName : parameterArray) {
				Type paramType = null;
				Value paramValue = null;
				Function function = null;
				StringBuffer typeAndNameBuffer = new StringBuffer(typeAndName);
				typeAndNameBuffer = typeAndNameBuffer.reverse();
				String typeAndNameReverse = typeAndNameBuffer.toString();
				String[] typeAndNameArray = typeAndNameReverse.split(" ",2);

				String paraTypeStrReverse = typeAndNameArray[1];
				String nameStrRevers = typeAndNameArray[0];
				StringBuffer parameterBuff = new StringBuffer(paraTypeStrReverse);
				StringBuffer nameBuff = new StringBuffer(nameStrRevers);
				parameterBuff = parameterBuff.reverse();
				nameBuff = nameBuff.reverse();
				String paraTypeStr = parameterBuff.toString().trim();
				String nameStr = nameBuff.toString().trim();
				try {
					paramType = getLLVMType(paraTypeStr, compilationContext, true);
				} 
				catch (Exception e) {
					errorHandler.addError(fileName, location, paraTypeStr, name, e.getMessage());
				}

				args.add(paramType);
				String lnameStr = nameStr;	
				if(isVariable(nameStr)) {
					paramValue = getValueFromMap(nameStr);
					lnameStr = nameStr.substring(1);
				}

				if(paramValue == null && isGlobalVariable(nameStr)) {
					PointerType ptrToFunctype = (PointerType) paramType;
					function = Function.create(null, ptrToFunctype, null, null, lnameStr, cfg);
					globalVariableNameValueMap.put(nameStr, function);
					paramValue = function;
				}

				if(paramValue == null){
					try {
						paramValue = createConstantValue(location, lnameStr , paraTypeStr);
					} 
					catch (Exception e) {
						errorHandler.addError(fileName, location, name, null, e.getMessage());
					}
				}
				valueList.add(paramValue);
			}
		}

		functionValue = getValueFromMap(functionName);

		if(functionValue == null){
			functionType = new FunctionType(compilationContext, type, args, isTail);
			PointerType ptrToFunctype = null;
			try {
				ptrToFunctype = Type.getPointerType(compilationContext, functionType, 0);
			} 
			catch (TypeCreationException e) {
				errorHandler.addError(fileName, location, null, null, e.getMessage());
			}

			String fname = functionName.substring(1);
			Function function = Function.create(null, ptrToFunctype, null, null, fname, null);
			functionValue = function;
		}
		else{
			isPointerToFunc = true;
		}

		try {
			String fname = name;
			fname = handleName(name);
			inst = CallInst.create(functionValue, valueList, fname , isTail, isPointerToFunc, currentBasicBlock);
			updateIncompletePhiNode(inst,name);
		} 
		catch (InstructionCreationException e) {
			errorHandler.addError(fileName, location, null, null, e.getMessage());
		}

		if(functionAttribute != null) {
			String functionAttributes[] = functionAttribute.split(" ");
			for(String attrStr : functionAttributes){
				int attribute = LLVMUtility.getParamAttributeAsIntegerValue(properties, attrStr);
				try {
					inst.addFuncAttribute(attribute);
				} 
				catch (InstructionUpdateException e) {
					errorHandler.addError(fileName, location, null, null, e.getMessage());
				}
			}
		}

		currentBasicBlock.addInstruction(inst);
		if(name != null){
			addToMap(new SourceLocation(data.getLineNum()), name, inst);
		}
	}

	/**
	 * Creates a cast instruction and adds it to the current basic block.
	 */
	private void createCastInstr(CastInstrData data) {
		String name = data.getResult();
		String typeStr = data.getTypeStr();
		String sourceStr = data.getSource();
		String valueStr = data.getValue();
		String targetStr = data.getTarget();
		int lineNum = data.getLineNum();
		SourceLocation location = new SourceLocation(lineNum);
		boolean isValueFromMap = isVariable(valueStr);
		CastInst inst = null;
		Type targetType = null;
		Value sourceValue = null;
		InstructionID instID = null;
		try {
			targetType = getLLVMType(targetStr, compilationContext, true);
		} 
		catch (Exception e) {
			errorHandler.addError(fileName, location, fileName, targetStr, e.getMessage());
		}

		if(isValueFromMap) {
			sourceValue = getValueFromMap(valueStr);
			valueStr =  valueStr.substring(1);
		} 
		else{
			try {
				sourceValue = createConstantValue(location, valueStr, sourceStr);
			} 
			catch (Exception e) {
				errorHandler.addError(fileName, location, fileName, valueStr, e.getMessage());
			}
		}

		instID = getCastInstructionID(typeStr);
		try {
			if(name != null) {
				inst = CastInst.create(instID, null, targetType, sourceValue, currentBasicBlock);
				updateIncompletePhiNode(inst,name);
			}
			else{
				castInstForGlobalVariable = CastInst.create(instID, null , targetType, sourceValue, null);
			}
		} 
		catch (InstructionCreationException e) {
			errorHandler.addError(fileName, location, fileName, valueStr, e.getMessage());
		}

		if(inst != null) {
			currentBasicBlock.addInstruction(inst);
			addToMap(new SourceLocation(data.getLineNum()), name, inst);
		}
	}

	/**
	 * Check if name is a user defined variable name or needs to go into the symbol table (numeric).
	 */
	private String handleName(String name){
		if(name  ==  null){
			return null;
		}

		String lname = name.substring(1);
		if(lname.matches("\\d+")){
			return null;
		}
		else{
			return lname;
		}
	}

	private void createAndSetTargetData(DataLayout dataLayout) {
		String targetDescription = dataLayout.getTargetDataLayout();
		TargetData targetData  = new TargetData(targetDescription);
		module.setTargetData(targetData);
	}

	private boolean isVariable(String name){
		return isLocalVariable(name) || isGlobalVariable(name);
	}

	private boolean isLocalVariable(String name) {
		if(name.startsWith("%")){
			return true;
		}
		return false;
	}

	private boolean isGlobalVariable(String name) {
		if(name.startsWith("@")){
			return true;
		}
		return false;
	}
}

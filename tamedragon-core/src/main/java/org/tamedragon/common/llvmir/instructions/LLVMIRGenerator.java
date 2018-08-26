package org.tamedragon.common.llvmir.instructions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.Stack;
import java.util.Vector;

import org.jgraph.graph.DefaultEdge;
import org.tamedragon.assemblyabstractions.constructs.IRTree;
import org.tamedragon.assemblyabstractions.constructs.IRTreeAggregateData;
import org.tamedragon.assemblyabstractions.constructs.IRTreeAlloca;
import org.tamedragon.assemblyabstractions.constructs.IRTreeBinaryExp;
import org.tamedragon.assemblyabstractions.constructs.IRTreeCallExp;
import org.tamedragon.assemblyabstractions.constructs.IRTreeCast;
import org.tamedragon.assemblyabstractions.constructs.IRTreeConditionalBranch;
import org.tamedragon.assemblyabstractions.constructs.IRTreeConditionalExpr;
import org.tamedragon.assemblyabstractions.constructs.IRTreeConst;
import org.tamedragon.assemblyabstractions.constructs.IRTreeDeclaration;
import org.tamedragon.assemblyabstractions.constructs.IRTreeExp;
import org.tamedragon.assemblyabstractions.constructs.IRTreeExpList;
import org.tamedragon.assemblyabstractions.constructs.IRTreeExpressionStm;
import org.tamedragon.assemblyabstractions.constructs.IRTreeFunction;
import org.tamedragon.assemblyabstractions.constructs.IRTreeIndex;
import org.tamedragon.assemblyabstractions.constructs.IRTreeLabel;
import org.tamedragon.assemblyabstractions.constructs.IRTreeMemory;
import org.tamedragon.assemblyabstractions.constructs.IRTreeMove;
import org.tamedragon.assemblyabstractions.constructs.IRTreePhiExpr;
import org.tamedragon.assemblyabstractions.constructs.IRTreeReturn;
import org.tamedragon.assemblyabstractions.constructs.IRTreeReturnAlloca;
import org.tamedragon.assemblyabstractions.constructs.IRTreeStatement;
import org.tamedragon.assemblyabstractions.constructs.IRTreeStatementList;
import org.tamedragon.assemblyabstractions.constructs.IRTreeSwitch;
import org.tamedragon.assemblyabstractions.constructs.IRTreeTempOrVar;
import org.tamedragon.assemblyabstractions.constructs.IRTreeType;
import org.tamedragon.assemblyabstractions.constructs.IRTreeUnConditionalBranch;
import org.tamedragon.assemblyabstractions.constructs.IRTreeExp.TreeNodeExpType;
import org.tamedragon.assemblyabstractions.constructs.IRTreeStatement.TreeStatementType;
import org.tamedragon.common.Label;
import org.tamedragon.common.Pair;
import org.tamedragon.common.llvmir.cfrontend.utils.CFrontendUtils;
import org.tamedragon.common.llvmir.instructions.BinaryOperator.BinaryOperatorID;
import org.tamedragon.common.llvmir.instructions.CmpInst.Predicate;
import org.tamedragon.common.llvmir.instructions.Instruction.AtomicOrdering;
import org.tamedragon.common.llvmir.instructions.Instruction.InstructionID;
import org.tamedragon.common.llvmir.instructions.Instruction.SynchronizationScope;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionCreationException;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionDetailAccessException;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionUpdateException;
import org.tamedragon.common.llvmir.math.APInt;
import org.tamedragon.common.llvmir.types.Argument;
import org.tamedragon.common.llvmir.types.ArrayType;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.CompilationContext;
import org.tamedragon.common.llvmir.types.Constant;
import org.tamedragon.common.llvmir.types.ConstantArray;
import org.tamedragon.common.llvmir.types.ConstantExpr;
import org.tamedragon.common.llvmir.types.ConstantInt;
import org.tamedragon.common.llvmir.types.ConstantStruct;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.FunctionType;
import org.tamedragon.common.llvmir.types.GlobalVariable;
import org.tamedragon.common.llvmir.types.IntegerType;
import org.tamedragon.common.llvmir.types.Module;
import org.tamedragon.common.llvmir.types.NamedType;
import org.tamedragon.common.llvmir.types.PointerType;
import org.tamedragon.common.llvmir.types.StructType;
import org.tamedragon.common.llvmir.types.Type;
import org.tamedragon.common.llvmir.types.Value;
import org.tamedragon.common.llvmir.types.GlobalValue.LinkageTypes;
import org.tamedragon.common.llvmir.types.Value.ValueTypeID;
import org.tamedragon.common.llvmir.types.exceptions.TypeCreationException;

/**
 * This class is responsible for generating LLVM IR when the input is a 
 * tree
 *
 */
public class LLVMIRGenerator {
	private CFG cfg;
	private BasicBlock currentBasicBlock;
	private BasicBlock startBlock;
	private CompilationContext compilationContext;
	private Map<Label,BasicBlock> labelVsBBMap;
	private List<PhiNode> listOfPhiNodes;
	private Map<IRTreeExp,Value> exprVsValue;
	private Map<Value,LoadInst> valueVsLoadInst;
	private Map<IRTreeExp,LoadInst> exprVsLoadInst;
	private Stack<LoadInst> loadInsts;

	private HashMap<Value, AllocaInst> memoryValAllocInstBinding;
	private HashMap<Value, GlobalVariable> memoryValGlobalVariableBinding;
	private HashMap<Value, AllocaInst> originalValValAllocInstBinding;
	Value repeatingLoadValue = null;
	Stack<Value> stackOfSwitchOn = null;
	Stack<IRTreeSwitch> stackOfIRTreeSwitch = null;
	Stack<BasicBlock> stackOfBBContainingSwitchInstrs;
	Stack<IRTreeExp> stackOfVariableLengthArrExprs;
	Module module;
	List<BasicBlock> listOfBBs = null;
	List<NamedType> namedTypes ;
	Function currentFunction;
	List<String> listOfNamesOfFuncsUsed;
	Set<String> setOfUsedValueNames;
	Map<Function, String> functionVsUsedValueNames;
	List<Function> listOfRemovedFunctions;
	List<GlobalVariable> listOfRemovedGlobalVariables;
	Map<String, GlobalVariable> nameVsGlobalVariable;
	Map<String, AllocaInst> nameVsLocalVariable;
	Map<Value, IRTreeExp> variableLengthArrVsExpr;
	boolean terminatorInstrEncountered ;
	int count1 = 0;
	int count2 = 0;
	
	private Properties properties;

	public LLVMIRGenerator(Properties properties, CompilationContext compilationContext, String filename){
		this.compilationContext = compilationContext;
		module = new Module(filename, compilationContext, null);
		nameVsGlobalVariable = new HashMap<String, GlobalVariable>();
		memoryValGlobalVariableBinding = new HashMap<Value, GlobalVariable>();
		exprVsValue = new HashMap<IRTreeExp, Value>();
		stackOfSwitchOn = new Stack<Value>();
		namedTypes = new ArrayList<NamedType>();
		module.setNamedTypes(namedTypes);
		listOfRemovedFunctions = new ArrayList<Function>();
		listOfRemovedGlobalVariables = new ArrayList<GlobalVariable>();
		listOfNamesOfFuncsUsed = new ArrayList<String>();
		setOfUsedValueNames = new HashSet<String>();
		functionVsUsedValueNames = new HashMap<Function, String>();
		originalValValAllocInstBinding = new HashMap<Value, AllocaInst>();
		memoryValAllocInstBinding = new HashMap<Value, AllocaInst>();
		this.properties = properties;
	}

	public void generateCode(IRTreeStatementList list) throws InstructionCreationException{
		currentBasicBlock = null;
		for(IRTreeStatementList tempList = list; tempList != null; 
				tempList = tempList.getStatementList()) {

			IRTreeStatement stm = tempList.getStatement();
			if(stm != null){

				if(stm instanceof IRTreeFunction){
					terminatorInstrEncountered = false;
					IRTreeFunction irTreeFunction = (IRTreeFunction)stm;
					if(!irTreeFunction.isDeclaration()){
						// Must be the start 
						cfg = new CFG();
						BasicBlock basicBlock = initNewBasicBlock();
						cfg.setStartNode(basicBlock);
						labelVsBBMap = new HashMap<Label, BasicBlock>();
						labelVsBBMap.put(new Label("L0"), basicBlock);
						startBlock = basicBlock;
						nameVsLocalVariable = new HashMap<String, AllocaInst>();
						originalValValAllocInstBinding = new HashMap<Value, AllocaInst>();
						memoryValAllocInstBinding = new HashMap<Value, AllocaInst>();
						exprVsLoadInst = new HashMap<IRTreeExp, LoadInst>();
						valueVsLoadInst = new HashMap<Value, LoadInst>();
						loadInsts = new Stack<LoadInst>();
						stackOfVariableLengthArrExprs = new Stack<IRTreeExp>();
						listOfPhiNodes = new ArrayList<PhiNode>();
						stackOfIRTreeSwitch = new Stack<IRTreeSwitch>();
						stackOfBBContainingSwitchInstrs = new Stack<BasicBlock>();
						variableLengthArrVsExpr = new HashMap<Value, IRTreeExp>();
						currentBasicBlock = basicBlock;
					}
				}

				if(terminatorInstrEncountered && (stm.getStatementType() != TreeStatementType.LABEL))
					continue;

				//generate code for statement
				generateCode(stm,tempList);
			}
		}
	}

	public void removeAnyUnUsedStaticFunctionOrFunctionDeclrs() {
		List<Function> functions = module.getFunctions();
		List<Function> tempfunctionList = new ArrayList<Function>(functions);
		for(Function function : tempfunctionList){
			LinkageTypes linkageTypes = function.getLinkage();
			// Check Whether it s static Function
			if(linkageTypes== LinkageTypes.InternalLinkage){
				String name = function.getName();
				// If the static function is not being called or in other sense not being 
				// used then don't emit it
				if(!listOfNamesOfFuncsUsed.contains(name)){
					functions.remove(function);
					listOfRemovedFunctions.add(function);
				}
			}
			else{
				int numBasicBlocks = function.getNumBasicBlocks();
				if(numBasicBlocks == 0){
					// Its a function declaration
					String funcName = function.getName();
					if(!listOfNamesOfFuncsUsed.contains(funcName)){
						functions.remove(function);
						listOfRemovedFunctions.add(function);
					}
				}
			}
		}
	}

	public void removeUnUsedStaticOrExternGlobalVariables() {
		List<GlobalVariable> globalVariables = module.getGlobalVariables();
		// To remove duplicate entries
		Set<GlobalVariable> setOfGlobalVariables = new LinkedHashSet<GlobalVariable>(globalVariables );
		for(GlobalVariable globalVariable : setOfGlobalVariables){
			LinkageTypes linkageTypes = globalVariable.getLinkage();
			if(linkageTypes == LinkageTypes.InternalLinkage || linkageTypes == LinkageTypes.ExternalLinkage){
				if(setOfUsedValueNames.contains(globalVariable.getName())){
					if(linkageTypes == LinkageTypes.InternalLinkage){
						int numOfUsers = globalVariable.getNumUses();
						boolean isUse = false;
						for(int i = 0; i < numOfUsers; i++){
							Value user = globalVariable.getUserAt(i);
							if(user instanceof Instruction){
								Instruction instruction = (Instruction)user;
								Function function = instruction.getParent().getParent();
								String functionName = function.getName();
								if(listOfNamesOfFuncsUsed.contains(functionName) || function.getLinkage() != LinkageTypes.InternalLinkage){
									isUse = true;
									break;
								}
							}
						}
						if(!isUse){
							globalVariables.remove(globalVariable);
							listOfRemovedGlobalVariables.add(globalVariable);
						}
					}
				}
				else{
					String staticVariableName = globalVariable.getName();
					// If it is a local static variable an then keep it and don't discard it, but if it is an external variable and it is unused then remove it.
					if(!staticVariableName.matches("[_|a-z|A-Z]+.+") || linkageTypes == LinkageTypes.ExternalLinkage){
						globalVariables.remove(globalVariable);
						listOfRemovedGlobalVariables.add(globalVariable);
					}
				}
			}
		}
	}

	private void generateCode(IRTreeStatement stm, IRTreeStatementList stmtList) throws InstructionCreationException {

		TreeStatementType stmType = stm.getStatementType();
		if(stmType == TreeStatementType.EXPRESSION){
			IRTreeExpressionStm irTreeExpStm = (IRTreeExpressionStm) stm;
			IRTreeExp irTreeExp = irTreeExpStm.getExpressionTree();
			valueFromExpressionNode(irTreeExp, stmtList);
		}
		else if(stmType == TreeStatementType.UNCONDITIONAL_JUMP){ // Node is a declaration 
			terminatorInstrEncountered = true;
			IRTreeUnConditionalBranch irTreeUnconditionalBranch = (IRTreeUnConditionalBranch) stm;
			Label label = irTreeUnconditionalBranch.getTargetLabel();
			BasicBlock targetBB = null;

			if(labelVsBBMap.containsKey(label)){
				targetBB = labelVsBBMap.get(label);
				cfg.addEdge(currentBasicBlock, targetBB, new DefaultEdge());
			}
			else{
				targetBB = initNewBasicBlock();
				cfg.addEdge( currentBasicBlock, targetBB, new DefaultEdge());
				labelVsBBMap.put(label, targetBB);
			}

			BranchInst newBranchInst = BranchInst.create(targetBB, currentBasicBlock, compilationContext);
			currentBasicBlock.addInstruction(newBranchInst);
		}
		/* This type statement will create a Allocation instruction for the return value of the Function
		 * and create a store instruction  for main function. */
		else if(stmType == TreeStatementType.RET_VAL){ 
			IRTreeReturnAlloca irTreeReturnAlloca = (IRTreeReturnAlloca) stm;
			AllocaInst allocaInst = null;
			Value addressVal = irTreeReturnAlloca.getMemory().getMemory();

			if(addressVal != null){
				Type contType = ((PointerType) addressVal.getType()).getContainedType();

				try{
					allocaInst = AllocaInst.create(properties, contType, addressVal.getName(), null, currentBasicBlock);
					if(allocaInst.hasName())
						nameVsLocalVariable.put(addressVal.getName(), allocaInst);
				}
				catch(InstructionCreationException ice){
					ice.printStackTrace();
					// TODO log here
					System.exit(-1);
				}

				memoryValAllocInstBinding.put(addressVal, allocaInst);
				insertAllocationInstruction(allocaInst);
				// If the function is main the store 0 in the memory allocated for the returned value
				String funcName = currentFunction.getName();

				if(funcName.equals("main")){
					ConstantInt firstArg = null;
					StoreInst storeInst = null;
					APInt val = new APInt(32, "0", true);

					try{
						firstArg = new ConstantInt(Type.getInt32Type(compilationContext, true), val);
						storeInst = StoreInst.create(properties, firstArg, allocaInst, false, null, null, currentBasicBlock);
					}
					catch (InstructionCreationException e) {
						e.printStackTrace();
						System.exit(-1);
					}
					catch(InstantiationException e){
						e.printStackTrace();
						System.exit(-1);
					}

					currentBasicBlock.addInstruction(storeInst);
				}
			}
		}
		else if(stmType == TreeStatementType.DECLARATION){ // Node is a declaration 
			IRTreeDeclaration llvmDeclaration = (IRTreeDeclaration) stm;
			IRTreeMemory memTree = llvmDeclaration.getMemoryTree();
			Value memoryVal = memTree.getMemory();
			boolean isGlobal = llvmDeclaration.isGlobal();

			if(!isGlobal){
				AllocaInst allocaInst = null;
				Type contType = null;
				try{
					String name = memoryVal.getName();
					contType = ((PointerType) memoryVal.getType()).getContainedType();

					if(contType.isPointerType()){
						PointerType pointerType = (PointerType)contType;
						if(pointerType.getContainedType().isStructType()){
							StructType structType = (StructType)pointerType.getContainedType();
							getListOfNestedStructType(structType, false);
						}
					}

					allocaInst = AllocaInst.create(properties, contType , name, null, currentBasicBlock);
					if(allocaInst.hasName())
						nameVsLocalVariable.put(name, allocaInst);
				}
				catch(InstructionCreationException ice){
					ice.printStackTrace();
					System.exit(-1);
				}

				memoryValAllocInstBinding.put(memoryVal, allocaInst);
				insertAllocationInstruction(allocaInst);
				IRTreeExp initializerTreeExp = llvmDeclaration.getInitializerExpression();

				if(initializerTreeExp != null){
					TreeNodeExpType expType = initializerTreeExp.getExpType();

					Value valueToBeStored = null;
					StoreInst storeInst = null;

					if(expType == TreeNodeExpType.CONST_EXP){
						IRTreeConst constNode = (IRTreeConst) initializerTreeExp;
						valueToBeStored = constNode.getExpressionValue();
					}
					else if(expType == TreeNodeExpType.MEMORY){
						IRTreeMemory llvmTreeNodeMemExp = (IRTreeMemory) initializerTreeExp;
						//						valueToBeStored = getValueToBeStored(llvmTreeNodeMemExp);
						valueToBeStored = llvmTreeNodeMemExp.getMemory();
						valueToBeStored = getCorrespondingAllocInstIfAvailable(valueToBeStored);

						if(llvmTreeNodeMemExp.getIrTreeExp() != null){
							IRTreeMemory irTreeMemory = (IRTreeMemory)llvmTreeNodeMemExp.getIrTreeExp();
							Value value = irTreeMemory.getMemory();
							value = getCorrespondingAllocInstIfAvailable(value);
							Value inst = valueVsLoadInst.get(value);
							if(inst == null){
								inst = getLoadInstrFromExpr(irTreeMemory);
								if(inst == null)
									inst = getCorrespondingAllocInstIfAvailable(value);
							}
							LoadInst loadInst = (LoadInst)createLoadIns(inst, null);
							valueToBeStored = loadInst;
							//							valueToBeStored = getValueToBeStored(llvmTreeNodeMemExp);
						}
						else if(valueToBeStored.getName() != null && nameVsGlobalVariable.containsKey(valueToBeStored.getName())){
							// Global Variable
							valueToBeStored = nameVsGlobalVariable.get(valueToBeStored.getName());
							valueToBeStored = valueFromExpressionNode(llvmTreeNodeMemExp, null);
						}
						else if(valueToBeStored instanceof AllocaInst){//if source if of pointer type then we have to load it first
							PointerType pointerType = (PointerType)allocaInst.getType();
							Type containedType = pointerType.getContainedType();
							if(!containedType.isPointerType()){
								LoadInst loadInst = (LoadInst)createLoadIns(valueToBeStored, null);
								valueToBeStored = loadInst;
							}
						}
					}
					else if(expType == TreeNodeExpType.TEMP_OR_VAR_EXP){
						valueToBeStored = getLatestLoadInstruction((IRTreeTempOrVar)initializerTreeExp);
					}
					else if(expType == TreeNodeExpType.BINARY_EXP || expType == TreeNodeExpType.CAST_EXP || expType == TreeNodeExpType.CALL_EXP || expType == TreeNodeExpType.PHI_NODE){
						valueToBeStored = valueFromExpressionNode(initializerTreeExp, stmtList);
					}
					else if(expType == TreeNodeExpType.AGR_EXP){
						IRTreeAggregateData aggregateData = (IRTreeAggregateData)initializerTreeExp;
						Value value = valueFromExpressionNode(aggregateData, stmtList);

						if(value instanceof GetElementPtrInst){
							GetElementPtrInst elementPtrInst = (GetElementPtrInst)value;

							boolean isAtTheSameLevel = chkIfBothValuesHaveSameLevelOfPointer(elementPtrInst, allocaInst);
							if(isAtTheSameLevel && (elementPtrInst.getType() == allocaInst.getType()))
								value = createLoadIns(elementPtrInst, null);

							valueToBeStored = value;
						}
						else{
							valueToBeStored = createLoadIns(value, null);
						}
					}

					Value castInst = null;

					try {
						castInst = castSourceToDestinationType(valueToBeStored, allocaInst, false, false);
						if(castInst != null)
							valueToBeStored = castInst;
					} 
					catch (InstructionCreationException e) {
						e.printStackTrace();
						System.exit(-1);
					}

					// Create the store instruction
					try{
						valueToBeStored = getGlobalVariableIfItExists(valueToBeStored);
						storeInst = StoreInst.create(properties, valueToBeStored, allocaInst, llvmDeclaration.isVolatileFlag(),
								llvmDeclaration.getAtomicOrdering(), llvmDeclaration.getSyncScope(), currentBasicBlock);
					}
					catch(InstructionCreationException ice){
						ice.printStackTrace();
						System.exit(-1);
					}
					currentBasicBlock.addInstruction(storeInst);
				}
			}
			/**
			 * Global variable Declaration
			 */
			else{
				IRTreeExp initializerTreeExp = llvmDeclaration.getInitializerExpression();
				boolean isUnnamedAddr = llvmDeclaration.isUnnamedAddr();
				LinkageTypes linkage = llvmDeclaration.getLinkageTypes();
				boolean isConstant = llvmDeclaration.isConstant();
				String section = "";
				Value initializer = null;

				if(initializerTreeExp != null){
					initializer = valueFromExpressionNode(initializerTreeExp, null);

					Value castInst = null;
					try {
						castInst = castSourceToDestinationType(initializer, memoryVal, false, false);
						if(castInst != null)
							initializer = castInst;
					} 
					catch (InstructionCreationException e) {
						e.printStackTrace();
						System.exit(-1);
					}
				}

				List<Value> listOfConsts = new ArrayList<Value>();
				listOfConsts.add(initializer);
				PointerType pointerType = (PointerType)memoryVal.getType();

				if(pointerType.getContainedType().isStructType()){
					StructType structType = (StructType)pointerType.getContainedType();
					getListOfNestedStructType(structType, false);
				}

				GlobalVariable globalVariable = new GlobalVariable(module, pointerType, isConstant, linkage, listOfConsts, memoryVal.getName(), null, false);

				if(!(initializer instanceof GetElementPtrInst))
					globalVariable.setUnnamedAddr(isUnnamedAddr);

				if(linkage != LinkageTypes.ExternalLinkage || linkage == null)
					globalVariable.setAlignment(AllocaInst.getAlignmentForType(properties, pointerType.getContainedType()));

				globalVariable.setSection(section);
				memoryValGlobalVariableBinding.put(memoryVal, globalVariable);
				nameVsGlobalVariable.put(memoryVal.getName(), globalVariable);
			}
		}
		else if(stmType == TreeStatementType.MOVE){  // Node is a assignment operation

			IRTreeMove llvmTreeNodeMove = (IRTreeMove) stm;
			IRTreeExp destExpNode = llvmTreeNodeMove.getDest();
			IRTreeExp src = llvmTreeNodeMove.getSrc();

			Value srcValue = null;

			if(src.getExpType() == TreeNodeExpType.TEMP_OR_VAR_EXP){
				srcValue = getLatestLoadInstruction((IRTreeTempOrVar)src);
			}
			else if(src.getExpType() == TreeNodeExpType.MEMORY){
				IRTreeMemory irTreeMemory = (IRTreeMemory)src;
				srcValue = getCorrespondingAllocInstIfAvailable(irTreeMemory.getMemory());
				if(!(srcValue instanceof AllocaInst) && irTreeMemory.getIrTreeExp() != null){
					srcValue = valueFromExpressionNode(src, stmtList);
				}
			}
			else{
				srcValue = valueFromExpressionNode(src, stmtList);
			}

			TreeNodeExpType destExpType = destExpNode.getExpType();
			Value destValue = null;

			if(destExpType == TreeNodeExpType.MEMORY){
				IRTreeMemory memTree = (IRTreeMemory) destExpNode;
				destValue = memTree.getMemory();
				// If the destination is pointer e.g. *y
				IRTreeExp irTreeExp = memTree.getIrTreeExp();

				if(irTreeExp != null && (irTreeExp instanceof IRTreeMemory)){
					destValue = valueFromExpressionNode(irTreeExp, stmtList);
				}

				//TODO get volatile flag, atomic ordering, sync scope correctly
				destValue = getCorrespondingAllocInstIfAvailable(destValue);
				boolean isBothSrcAndDestAreAtSameLevel = chkIfBothValuesHaveSameLevelOfPointer(srcValue, destValue);
				if(isBothSrcAndDestAreAtSameLevel)
					srcValue = createLoadIns(srcValue, null);
				else{
					while(count1 > count2){
						srcValue = createLoadIns(srcValue, null);
						count1--;
					}
					if(srcValue.getType() == destValue.getType()){
						srcValue  = castSourceToDestinationType(srcValue, destValue, false, false);
					}
				}
			}
			else if(destExpType == TreeNodeExpType.AGR_EXP){

				// For something like arr[0] = a
				IRTreeAggregateData aggregateData = (IRTreeAggregateData)destExpNode;
				destValue = valueFromExpressionNode(aggregateData, stmtList);

				boolean isBothSrcAndDestAreAtSameLevel = chkIfBothValuesHaveSameLevelOfPointer(srcValue, destValue);

				if(isBothSrcAndDestAreAtSameLevel)
					srcValue = createLoadIns(srcValue, null);

			}
			else if(destExpType == TreeNodeExpType.TEMP_OR_VAR_EXP){
				IRTreeTempOrVar irTreeTempOrVar = (IRTreeTempOrVar)destExpNode;
				destValue = getLatestLoadInstruction(irTreeTempOrVar);
			}
			else{
				destValue = valueFromExpressionNode(destExpNode, null);
			}

			//create Store Instruction
			try{
				if(!srcValue.getType().toString().equals(destValue.getType().toString())){
					Value castInst = castSourceToDestinationType(srcValue, destValue, false, false);
					if(castInst != null)
						srcValue = castInst;
				}
				srcValue = getGlobalVariableIfItExists(srcValue);
				srcValue = getFunctionForValue(srcValue);
				destValue = getGlobalVariableIfItExists(destValue);
				StoreInst storeInst = StoreInst.create(properties, srcValue, destValue, false, null, null, currentBasicBlock);
				currentBasicBlock.addInstruction(storeInst);
			}
			catch(InstructionCreationException ice){
				ice.printStackTrace();
				System.exit(-1);
			}

		}
		else if(stmType == TreeStatementType.RETURN){  // Node is a
			terminatorInstrEncountered = true;
			IRTreeReturn returnTree = (IRTreeReturn) stm;
			IRTreeExp expTree = returnTree.getExpTree();
			Value value = null;

			Type originalReturnType = returnTree.getOriginalReturnType();
			Type expectedReturnType = returnTree.getExpectedReturnType();

			if(expTree.getExpType() == TreeNodeExpType.MEMORY){
				// Create a load instruction
				IRTreeMemory memTree = (IRTreeMemory) expTree;
				Value memValue = memTree.getMemory();
				if(memValue != null){
					Value retInsValue = valueFromExpressionNode(memTree, null);
					value = retInsValue;
				}
				else
					value = memValue;
			}
			else if(expTree.getExpType() == TreeNodeExpType.TEMP_OR_VAR_EXP){
				value = getLatestLoadInstruction((IRTreeTempOrVar)expTree);
			}
			else if(expTree.getExpType() == TreeNodeExpType.AGR_EXP){
				value = valueFromExpressionNode(expTree, stmtList);

				if(originalReturnType.isPointerType()){
					PointerType pointerType = (PointerType)originalReturnType;
					Type containedType = pointerType.getContainedType();
					if((containedType.toString().equals(expectedReturnType.toString())) || (containedType.isPrimitiveType() && expectedReturnType.isPrimitiveType()))
						value = createLoadIns(value, null);
				}
				else if(originalReturnType.isPrimitiveType() && expectedReturnType.isPrimitiveType()){
					value = createLoadIns(value, null);
				}
			}
			else {
				value = valueFromExpressionNode(expTree, stmtList);
			}


			// If actual return type and original return type do not match then we have to create a cast instruction
			// value can be null for function returning void
			if(value != null && value.getType() != expectedReturnType){
				Value actualValue = new Value(expectedReturnType);
				try {
					value = castSourceToDestinationType(value, actualValue, false, false);
				} catch (InstructionCreationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			try{
				if(value != null)
					value = getGlobalVariableIfItExists(value);
				ReturnInst retInst = ReturnInst.create(compilationContext, value, currentBasicBlock);
				currentBasicBlock.addInstruction(retInst);
			}
			catch(InstructionCreationException ice){
				ice.printStackTrace();
				// TODO log here
				System.exit(-1);
			}

		}
		else if(stmType == TreeStatementType.LABEL){
			// Reset flag 'terminatorInstrEncountered'
			terminatorInstrEncountered = false;
			// A new label has been found, start a basic block here
			IRTreeLabel label = (IRTreeLabel)stm;
			BasicBlock newBB = null;
			if(labelVsBBMap.containsKey(label.getLabel())){
				newBB = labelVsBBMap.get(label.getLabel());
			}
			else{
				newBB = initNewBasicBlock();
				labelVsBBMap.put(label.getLabel(), newBB);
			}
			currentBasicBlock = newBB;
			listOfBBs.add(currentBasicBlock);
		}
		else if(stmType == TreeStatementType.CONDITIONAL_JUMP){
			terminatorInstrEncountered = true;
			IRTreeConditionalBranch conditionalBr = (IRTreeConditionalBranch)stm;
			Label leftLabel = conditionalBr.getLeftLabel();
			Label rightLabel = conditionalBr.getRightLabel();
			BasicBlock tempTrueBB = null;

			if(labelVsBBMap.containsKey(leftLabel)){
				tempTrueBB = labelVsBBMap.get(leftLabel);
				cfg.addEdge( currentBasicBlock, tempTrueBB, new DefaultEdge());
			}
			else{
				tempTrueBB = initNewBasicBlock();
				labelVsBBMap.put(leftLabel, tempTrueBB);
				cfg.addEdge(currentBasicBlock, tempTrueBB, new DefaultEdge());
			}

			BasicBlock tempFalseBB = null;

			if(labelVsBBMap.containsKey(rightLabel)){
				tempFalseBB = labelVsBBMap.get(rightLabel);
				cfg.addEdge(currentBasicBlock, tempFalseBB, new DefaultEdge());
			}
			else{
				tempFalseBB = initNewBasicBlock();
				labelVsBBMap.put(rightLabel, tempFalseBB);
				cfg.addEdge(currentBasicBlock, tempFalseBB, new DefaultEdge());
			}

			IRTreeExp conditionalExpr = conditionalBr.getIrTreeExp();
			Value compareVal = valueFromExpressionNode(conditionalExpr, stmtList);

			BranchInst branchInst;

			try {
				branchInst = BranchInst.create(tempTrueBB, tempFalseBB, compareVal, currentBasicBlock, compilationContext);
				branchInst.setParent(currentBasicBlock);
				currentBasicBlock.addInstruction(branchInst);
			} 
			catch (InstructionCreationException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
		else if(stmType == TreeStatementType.FUNC){
			IRTreeFunction irTreeFunctiondeclr = (IRTreeFunction)stm;
			String funcName = irTreeFunctiondeclr.getFuncName();
			List<Value> paramValues = irTreeFunctiondeclr.getParameters();
			Vector<Type> paramTypes = getParamTypesFromParamValues(paramValues);
			Type returnType = irTreeFunctiondeclr.getReturnType();
			List<List<Integer>> paramAttrs = irTreeFunctiondeclr.getParamAttrs();
			List<Integer> funcAttributeList = irTreeFunctiondeclr.getFuncAttributeList();
			List<Integer> retAttributeList = irTreeFunctiondeclr.getRetAttributeList();
			boolean hasEllipses = irTreeFunctiondeclr.isEndsWithEllipses();
			boolean isStatic = irTreeFunctiondeclr.isStatic();
			FunctionType functionType= null;
			PointerType ptrToFunctype = null;
			Function function = null;

			if(funcName != null)
				function = chkIfFuncAlreadyThereInModule(funcName);

			if(function == null){
				try {
					functionType = Type.getFunctionType(compilationContext, returnType, hasEllipses, paramTypes);
					ptrToFunctype = Type.getPointerType(compilationContext, functionType, 0);
				} 
				catch (TypeCreationException e) {
					e.printStackTrace();
					System.exit(-1);
				}
			}

			LinkageTypes linkageTypes = null;

			if(isStatic){
				linkageTypes = LinkageTypes.InternalLinkage;
			}
			else{
				linkageTypes = LinkageTypes.ExternalLinkage;
			}

			if(function == null)
				function = Function.create(module, ptrToFunctype, linkageTypes, CallingConv.C, funcName, cfg);
			else{
				function.setLinkage(linkageTypes);
				function.setCfg(cfg);
			}

			for(Integer funcAttr : funcAttributeList){
				try {
					function.addFnAttr(funcAttr);
				} 
				catch (InstructionUpdateException e) {
					e.printStackTrace();
					System.exit(-1);
				}
			}

			for(Integer retAttr : retAttributeList){
				try {
					function.addReturnAttributes(retAttr);
				} 
				catch (InstructionUpdateException e) {
					e.printStackTrace();
					System.exit(-1);
				}
			}

			// Set the Argument List
			int index = 0;


			List<Argument> arguments = function.getArgumentList();
			if(arguments.size() != 0)
				arguments.removeAll(arguments);

			for(Value paramValue : paramValues){
				Argument argument = new Argument(paramValue.getType(), paramValue.getName(), function);
				if(!irTreeFunctiondeclr.isDeclaration()){
					try {
						PointerType pointerType = Type.getPointerType(compilationContext, paramValue.getType(), 0);
						Value addressVal = new Value(pointerType);
						addressVal.setName(paramValue.getName());
						AllocaInst allocaInst = AllocaInst.create(properties,paramValue.getType(), null, null, currentBasicBlock);
						//TODO get volatile flag, atomic ordering, sync scope correctly
						StoreInst storeInst = StoreInst.create(properties, argument, allocaInst, false, null, null, currentBasicBlock);
						memoryValAllocInstBinding.put(paramValue, allocaInst);
						originalValValAllocInstBinding.put(argument, allocaInst);
						insertAllocationInstruction(allocaInst);
						currentBasicBlock.addInstruction(storeInst);
					} catch (InstructionCreationException e1) {
						e1.printStackTrace();
						System.exit(-1);
					}
					catch (TypeCreationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				if(paramAttrs.size()-1 >= index){
					List<Integer> argumentAttrs = paramAttrs.get(index);
					for(Integer argumentAttr : argumentAttrs){
						try {
							argument.addAttr(index, argumentAttr);
						} 
						catch (InstructionUpdateException e) {
							e.printStackTrace();
							System.exit(-1);
						}
					}
				}
				index++;
			}

			// initialize the list of basic blocks for this function if it is not a declaration
			if(!irTreeFunctiondeclr.isDeclaration()){
				listOfBBs = new ArrayList<BasicBlock>();
				listOfBBs.add(currentBasicBlock);
				function.setBasicBlocks(listOfBBs);
				currentFunction = function;
				currentBasicBlock.setParent(function);
			}
		}
		else if(stmType == TreeStatementType.SWITCH){
			terminatorInstrEncountered = true;
			IRTreeSwitch irTreeSwitch = (IRTreeSwitch)stm;
			IRTreeExp irTreeExp = (IRTreeExp)irTreeSwitch.getSwitchExpr();
			Value switchOn = valueFromExpressionNode(irTreeExp, null);
			IntegerType intType = (IntegerType)switchOn.getType();

			if(intType.getNumBits() < 32){
				try {
					switchOn = castSourceToDestinationType(switchOn, new Value(Type.getInt32Type(compilationContext, true)), false, false);
				} 
				catch (InstructionCreationException e) {
					e.printStackTrace();
					System.exit(-1);
				}
			}

			SwitchInst switchInst = null;
			BasicBlock defaultBB = null;

			Label defaultLabel = irTreeSwitch.getDefaultLabel().getLabel();

			if(labelVsBBMap.containsKey(defaultLabel)){
				defaultBB = labelVsBBMap.get(defaultLabel);
				cfg.addEdge(currentBasicBlock, defaultBB, new DefaultEdge());
			}
			else{
				defaultBB = initNewBasicBlock();
				labelVsBBMap.put(defaultLabel, defaultBB);
				cfg.addEdge(currentBasicBlock, defaultBB);
			}

			try {
				switchOn = getGlobalVariableIfItExists(switchOn);
				switchInst = SwitchInst.create(switchOn, defaultBB, currentBasicBlock);
			} 
			catch (InstructionCreationException e) {
				e.printStackTrace();
				System.exit(-1);
			}

			currentBasicBlock.addInstruction(switchInst);

			Map<IRTree, IRTreeLabel> caseValueVsLabel = irTreeSwitch.getCaseValuesVsLabels();
			Set<Entry<IRTree, IRTreeLabel>> entries_caseValVsLab = caseValueVsLabel.entrySet();
			Iterator<Entry<IRTree, IRTreeLabel>> iterator = entries_caseValVsLab.iterator();

			while(iterator.hasNext()){
				Entry<IRTree, IRTreeLabel> entry_caseValVsLab = iterator.next();
				IRTreeExp caseExpr = (IRTreeExp)entry_caseValVsLab.getKey();

				if(caseExpr != null){// This is default case
					ConstantInt caseValue = (ConstantInt)valueFromExpressionNode(caseExpr, null);
					IntegerType integerType = (IntegerType)caseValue.getType();

					if(integerType.getNumBits() < 32){
						caseValue.setApInt(new APInt(32, caseValue.getApInt().toString(), integerType.isSigned()));
						caseValue.setType(Type.getInt32Type(compilationContext, integerType.isSigned()));
					}

					IntegerType switchOnType = (IntegerType)switchOn.getType();
					if(switchOnType.getNumBits() > 32){
						caseValue.setApInt(new APInt(switchOnType.getNumBits(), caseValue.getApInt().toString(), integerType.isSigned()));
						caseValue.setType(switchOnType);
					}

					BasicBlock caseBB =  null;

					IRTreeLabel irTreeLabel = entry_caseValVsLab.getValue();
					Label caseLabel = irTreeLabel.getLabel();
					if(labelVsBBMap.containsKey(caseLabel)){
						caseBB = labelVsBBMap.get(caseLabel);
						cfg.addEdge(currentBasicBlock, caseBB, new DefaultEdge());
					}
					else{
						caseBB = initNewBasicBlock();
						labelVsBBMap.put(caseLabel, caseBB);
						cfg.addEdge(currentBasicBlock, caseBB, new DefaultEdge());
					}

					try {
						switchInst.addCase(caseValue, caseBB);
					} 
					catch (InstructionUpdateException e) {
						e.printStackTrace();
						System.exit(-1);
					}
				}
			}

		}
		else if(stmType == TreeStatementType.UNREACHABLE){
			terminatorInstrEncountered = true;
			UnreachableInstr unreachableInstr = null;

			try {
				unreachableInstr = UnreachableInstr.create(currentBasicBlock);
				currentBasicBlock.addInstruction(unreachableInstr);
			} 
			catch (InstructionCreationException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
		else if(stmType == TreeStatementType.TYPE){
			IRTreeType irTreeType = (IRTreeType)stm;
			Type type = irTreeType.getValue().getType();
			String name = null;
			if(type.isStructType()){
				StructType structType = (StructType) irTreeType.getValue().getType();
				name = structType.getName();
			}
			else if(type.isIntegerType()){// For Enum Types
				name = irTreeType.getValue().getName();
			}
			NamedType namedType = new NamedType(type, name);
			if(!namedTypes.contains(namedType))
				namedTypes.add(namedType);
		}
	}

	/**
	 * This method will return true if both values are of PointerType and are at same level,
	 * i.e if one value is i32** and other is float**, then this method will return true.
	 * @param srcValue
	 * @param destValue
	 * @return 
	 */
	private boolean chkIfBothValuesHaveSameLevelOfPointer(Value srcValue, Value destValue) {
		boolean isBothSrcAndDestAreAtSameLevel = false;

		if(srcValue.getType().isPointerType() && destValue.getType().isPointerType()){
			PointerType srcPointerType = (PointerType)srcValue.getType();
			PointerType destPointerType = (PointerType)destValue.getType();
			count1 = 0;
			count2 = 0;
			Type containedTypeSrc = srcPointerType.getContainedType();
			while(containedTypeSrc.isPointerType()){
				count1++;
				PointerType pointerType = (PointerType)containedTypeSrc;
				containedTypeSrc = pointerType.getContainedType();
			}
			Type containedTypeDest = destPointerType.getContainedType();
			while(containedTypeDest.isPointerType()){
				count2++;
				PointerType pointerType = (PointerType)containedTypeDest;
				containedTypeDest = pointerType.getContainedType();
			}

			if(count1 == count2)
				isBothSrcAndDestAreAtSameLevel = true;
		}
		return isBothSrcAndDestAreAtSameLevel;
	}

	private Value getLoadInstrFromExpr(IRTreeExp irTreeExp) {
		if(irTreeExp == null)
			return null;

		Value value = null;
		value = exprVsLoadInst.get(irTreeExp);

		if(value != null)
			return value;

		if(irTreeExp.getExpType() == TreeNodeExpType.MEMORY){
			IRTreeMemory irTreeMemory = (IRTreeMemory)irTreeExp;
			irTreeExp = irTreeMemory.getIrTreeExp();
			value = getLoadInstrFromExpr(irTreeExp);
		}

		return value;
	}

	/**
	 * This function cast the source type to the destination type, if required
	 * @param srcValue
	 * @param destValue
	 * @return CastInst
	 * @throws InstructionCreationException 
	 */
	private Value castSourceToDestinationType(Value srcValue, Value destValue, boolean isCallInstr, boolean isBitCastOpr) throws InstructionCreationException {

		srcValue = getGlobalVariableIfItExists(srcValue);
		destValue = getGlobalVariableIfItExists(destValue);

		CastInst castInst = null;
		Type srcType = srcValue.getType();
		Type destType = destValue.getType();

		if(destType.isPointerType() && !isCallInstr && !isBitCastOpr){
			PointerType pointerType = (PointerType)destType;
			destType = pointerType.getContainedType();
		}

		if(srcType.toString().equals(destType.toString()))
			return srcValue;

		Type type = null;

		if(srcType.isIntegerType() && destType.isIntegerType()){
			IntegerType srcIntType = (IntegerType)srcType;
			IntegerType destIntType = (IntegerType)destType;
			boolean isDestTypeSigned = destIntType.isSigned();
			int noOfSrcBits = srcIntType.getNumBits();
			int noOfDestBits = destIntType.getNumBits();

			if(noOfSrcBits < noOfDestBits){
				if(srcValue instanceof ConstantInt){
					ConstantInt constantInt = (ConstantInt)srcValue;
					boolean isSigned = ((IntegerType)destType).isSigned();
					String strVal = constantInt.getApInt().toString();
					APInt apInt = new APInt(noOfDestBits, strVal, isSigned);
					((ConstantInt) srcValue).setApInt(apInt);
					((ConstantInt) srcValue).setType(destIntType);
					return null;
				}
				if(noOfDestBits == 8){
					type = Type.getInt8Type(compilationContext, isDestTypeSigned);
				}
				else if(noOfDestBits == 16){
					type = Type.getInt16Type(compilationContext, isDestTypeSigned);
				}
				else if(noOfDestBits == 32){
					type = Type.getInt32Type(compilationContext, isDestTypeSigned);
				}
				else if(noOfDestBits == 64){
					type = Type.getInt64Type(compilationContext, isDestTypeSigned);
				}
				if(srcIntType.isSigned())
					castInst = CastInst.createSExtOrBitCast(srcValue, type, null, currentBasicBlock);
				else
					castInst = CastInst.createZExtOrBitCast(srcValue, type, null, currentBasicBlock);

			}
			else if(noOfSrcBits > noOfDestBits){

				Integer intValue = null;

				if(srcValue instanceof ConstantInt){
					ConstantInt constantInt = (ConstantInt)srcValue;
					boolean isSigned = ((IntegerType)destType).isSigned();
					String strVal = constantInt.getApInt().toString();
					intValue = Integer.valueOf(strVal);
					Integer wrappedValue = wrapUp(intValue,isSigned,noOfDestBits);
					strVal = wrappedValue.toString();
					APInt apInt = new APInt(noOfDestBits, strVal, isSigned);
					((ConstantInt) srcValue).setApInt(apInt);
					((ConstantInt) srcValue).setType(destIntType);
					return constantInt;
				}

				if(noOfDestBits == 1){
					type = Type.getInt1Type(compilationContext, isDestTypeSigned);
				}
				else if(noOfDestBits == 8){
					type = Type.getInt8Type(compilationContext, isDestTypeSigned);
				}
				else if(noOfDestBits == 16){
					type = Type.getInt16Type(compilationContext, isDestTypeSigned);
				}
				else if(noOfDestBits == 32){
					type = Type.getInt32Type(compilationContext, isDestTypeSigned);
				}
				castInst = CastInst.createTruncOrBitCast(srcValue, type, null, currentBasicBlock);
			}
		}

		else if(srcType.isIntOrIntVectorType() && destType.isFPOrFPVectorType()){
			if(srcType.isIntegerType() && destType.isFloatingPointType()){
				IntegerType srcIntegerType = (IntegerType)srcType;
				int nosOfSrcBits = srcType.getPrimitiveSizeInBits();
				int nosOfDestBits = destType.getPrimitiveSizeInBits();
				if(srcIntegerType.isSigned() && (nosOfSrcBits <= nosOfDestBits)){
					if(destType.equals(Type.getDoubleType(compilationContext))){
						type = Type.getDoubleType(compilationContext);
					}
					else if(destType.equals(Type.getFloatType(compilationContext))){
						type = Type.getFloatType(compilationContext);
					}
				}
			}
			else{
				//TODO is a vector of int and float
			}
			castInst = CastInst.createSitofpOrBitCast(srcValue, type, null, currentBasicBlock);
		}

		else if(srcType.isFloatingPointType() && destType.isFloatingPointType()){
			castInst = CastInst.createFPCast(srcValue, destType, null, currentBasicBlock);
		}

		else if(srcType.isFloatingPointType() && destType.isIntegerType()){
			castInst = CastInst.createFpToSi(srcValue, destType, null, currentBasicBlock);
		}

		else if(srcType.isPointerType() && destType.isIntegerType()){
			// Check whether the destination value i.e. LHS value is 0. If LHS is 0 and RHS is a pointer type 
			// i.e. we are trying to compare 'null pointer' with the RHS. So no casting is required
			if(destValue instanceof ConstantInt){
				ConstantInt constantInt = (ConstantInt)destValue;
				APInt apInt = constantInt.getApInt();
				if(apInt.getVal().equals("0")){
					return null;
				}
			}

			castInst = CastInst.createPointerCast(srcValue, destType, null, currentBasicBlock);
		}

		else if(srcType.isIntegerType() && destType.isPointerType()){
			// Check whether the source value i.e. RHS value is 0. If RHS is 0 and LHS is a pointer type 
			// i.e. we are trying to assign 'null pointer' to the LHS. So no casting is required
			if(srcValue instanceof ConstantInt){
				ConstantInt constantInt = (ConstantInt)srcValue;
				APInt apInt = constantInt.getApInt();
				if(apInt.getVal().equals("0"))
					return constantInt;
			}
			castInst = CastInst.createIntToPointerCast(srcValue, destType, null, currentBasicBlock);
		}

		else if(srcType.isPointerType() && destType.isPointerType()){
			castInst = CastInst.createPointerCast(srcValue, destType, null, currentBasicBlock);
		}

		if(currentBasicBlock != null && castInst != null)
			currentBasicBlock.addInstruction(castInst);

		return castInst;
	}

	private Integer wrapUp(Integer intValue, boolean isSigned, int noOfDestBits) {
		Integer wrappedValue = 0;

		if(!isSigned){
			Integer maxValue = new Double(Math.pow(2, noOfDestBits)).intValue();
			for(int i = 0; i < intValue; i++){
				if(wrappedValue <= maxValue)
					wrappedValue++;
				else
					wrappedValue = 0;
			}
		}
		else{
			Integer maxValue = new Double(Math.pow(2, noOfDestBits)).intValue();
			Integer maxPositiveValue = (maxValue/2) - 1;
			Integer maxNegativeValue = -(maxValue/2);

			while(intValue > 0){
				for(int i = 0; i < intValue; i++){
					if(wrappedValue <= maxPositiveValue)
						wrappedValue++;
					else{
						wrappedValue = 0;
						break;
					}
				}
				intValue -= (maxPositiveValue + 1);

				if(intValue < 0)
					break;

				wrappedValue = maxNegativeValue;

				for(int i = 0; i < intValue; i++){
					if(wrappedValue < 0)
						wrappedValue++;
					else{
						wrappedValue = 0;
						break;
					}
				}
				intValue += maxNegativeValue;
			}
		}

		return wrappedValue;
	}

	private BasicBlock initNewBasicBlock(){
		BasicBlock basicBlock = null;
		if(currentFunction == null){
			basicBlock = new BasicBlock(compilationContext);
		}
		else{
			basicBlock = new BasicBlock(currentFunction);
		}
		
		cfg.addVertex(basicBlock);
		return basicBlock;
	}

	/**
	 * All allocation instructions go to the top of the instructions list. They become
	 * part of the first basic block. This function inserts the allocation instruction
	 * to the end of the list of allocation instructions.
	 * @param allocaInst
	 */

	private void insertAllocationInstruction(AllocaInst allocaInst) {
		startBlock.addInstruction(allocaInst);
	}

	private Value createLoadIns(Value value, IRTreeExp irTreeExp){

		LoadInst loadInst = null;
		String loadInsTargetName = null;

		try{
			//TODO : get actual values for volatileFlag, atomic ordering and synchronization scope
			boolean isVolatile = false;
			AtomicOrdering atomicOrdering = null;
			SynchronizationScope syncScope = null;
			Value tempValue = value;

			// If this value is bound to an allocation instruction, use that to create the
			// load instruction
			value = getCorrespondingAllocInstIfAvailable(value);
			Value globalVariable = getGlobalVariableIfItExists(value);

			// For Variable length array
			if(!(tempValue instanceof AllocaInst) && !(globalVariable instanceof GlobalVariable) && tempValue == value && value.getName() != null){
				IRTreeExp exp = variableLengthArrVsExpr.get(value);
				if(exp == null && !stackOfVariableLengthArrExprs.empty()){
					exp = stackOfVariableLengthArrExprs.pop();
					variableLengthArrVsExpr.put(value, exp);
				}

				return valueFromExpressionNode(exp, null);
			}

			if(irTreeExp != null){
				if(irTreeExp instanceof IRTreeMemory){
					IRTreeMemory irTreeMemory = (IRTreeMemory)irTreeExp;
					Value value1 = irTreeMemory.getMemory();
					value1 = getCorrespondingAllocInstIfAvailable(value1);
					Value inst = valueVsLoadInst.get(value1);
					if(inst == null){
						inst = getLoadInstrFromExpr(irTreeExp);
						if(inst == null)
							inst = getCorrespondingAllocInstIfAvailable(value1);
					}
					value = inst;
				}
				else if(irTreeExp instanceof IRTreeAggregateData){
					Value value2 = valueFromExpressionNode(irTreeExp, null);
					value = value2;
				}
				// In case of Global return statement value name won't be there so here we have to return null
				// so that value would be null this is for return instruction returing void
				else{
					return null;
				}
			}
			loadInsTargetName = null;
			value = getGlobalVariableIfItExists(value);
			loadInst = LoadInst.create(properties, value, loadInsTargetName, 
					isVolatile, atomicOrdering, syncScope, currentBasicBlock);

			valueVsLoadInst.put(value, loadInst);
			if(irTreeExp != null)
				exprVsLoadInst.put(irTreeExp, loadInst);
			loadInsts.push(loadInst);

			// This required to keep track of static variables so it can be omitted while emitting the instructions if it is not being used
			setOfUsedValueNames.add(value.getName());
			functionVsUsedValueNames.put(currentFunction, value.getName());

			currentBasicBlock.addInstruction(loadInst);
		}
		catch(InstructionCreationException ice){
			ice.printStackTrace();
			// TODO log here
			System.exit(-1);
		}

		loadInst.setName(loadInsTargetName);
		return loadInst;
	}

	private Value getCorrespondingAllocInstIfAvailable(Value value){
		Type typ = value.getType();
		PointerType pointerType = null;
		try {
			pointerType = Type.getPointerType(compilationContext, typ, 0);
		} catch (TypeCreationException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		Value val = new Value(pointerType);
		val.setName(value.getName());
		AllocaInst allocInst = getAllocaInstrFromValueVsAllocaInstrMap(value, memoryValAllocInstBinding);
		if(allocInst != null)
			return allocInst;
		allocInst = getAllocaInstrFromValueVsAllocaInstrMap(value, originalValValAllocInstBinding);
		if(allocInst != null)
			value = allocInst;
		return value;
	}

	private AllocaInst getAllocaInstrFromValueVsAllocaInstrMap(Value val, Map<Value,AllocaInst> map) {
		AllocaInst allocaInstr = null;
		Set<Entry<Value, AllocaInst>> entries1 = map.entrySet();
		Iterator<Entry<Value, AllocaInst>> iterator1 = entries1.iterator();
		while(iterator1.hasNext()){
			Entry<Value, AllocaInst> entry = iterator1.next();
			Value v = entry.getKey();
			String vName = v.getName();
			String valueName = val.getName();
			Type valType = null;
			if(map == originalValValAllocInstBinding && val.getType().isPointerType())
				valType = ((PointerType)val.getType()).getContainedType();
			else
				valType = val.getType();
			if(vName != null && valueName != null && vName.equals(valueName) && v.getType() == valType){
				allocaInstr =  map.get(v);
				break;
			}
			else if(vName != null && valueName != null && vName.equals(valueName) && v.getType() != valType){
				if(valType.isPointerType()){
					PointerType pointerType = (PointerType)valType;
					Type containedType = pointerType.getContainedType();
					if(v.getType() == containedType){
						allocaInstr =  map.get(v);
						break;
					}
				}
			}
			else if(vName == null && valueName == null && v.getType() == valType && !(val instanceof Instruction)){
				allocaInstr =  map.get(v);
				if(allocaInstr != null)
					break;
			}
		}
		return allocaInstr;
	}

	private Value valueFromExpressionNode(IRTreeExp expNode, IRTreeStatementList stmtList){

		TreeNodeExpType expType = expNode.getExpType();

		switch(expType){
		case TEMP_OR_VAR_EXP:
			IRTreeTempOrVar valueNode = (IRTreeTempOrVar) expNode;
			Value value = valueNode.getValue();
			value = getGlobalVariableIfItExists(value);
			return value;
		case CONST_EXP:
			IRTreeConst constNode = (IRTreeConst) expNode;
			return translateConstantExpr(constNode);
		case BINARY_EXP:
			IRTreeBinaryExp binExpNode = (IRTreeBinaryExp) expNode;
			return translateBinaryExpr(binExpNode, stmtList);
		case MEMORY:
			IRTreeMemory memExpNode = (IRTreeMemory) expNode;
			value = memExpNode.getMemory();
			if(value.getType().isPointerType())
				return createLoadIns(value, memExpNode.getIrTreeExp());
			else 
				return value;
		case CALL_EXP:
			IRTreeCallExp irTreeCallExp = (IRTreeCallExp)expNode;
			return translateCallExpr(irTreeCallExp, stmtList);
		case CONDITIONAL_EXPR:
			IRTreeConditionalExpr irTreeConExpr = (IRTreeConditionalExpr) expNode;
			return translateConditionalExpr(irTreeConExpr, stmtList);
		case PHI_NODE:
			IRTreePhiExpr irTreePhiExpr = (IRTreePhiExpr) expNode;
			return translatePhiNode(irTreePhiExpr, stmtList);
		case AGR_EXP:
			IRTreeAggregateData irTreeAgrExpr = (IRTreeAggregateData) expNode;
			return translateAgrExpr(irTreeAgrExpr);
		case CAST_EXP:
			IRTreeCast irTreeCastExpr = (IRTreeCast) expNode;
			return translateCastExpr(irTreeCastExpr);
		case ALLOCA:
			IRTreeAlloca irTreeAlloca = (IRTreeAlloca) expNode;
			return createAllocaInstr(irTreeAlloca);
		case EXPR_LIST:
			IRTreeExpList irTreeExpList = (IRTreeExpList)expNode;
			return translateIRTreeExprList(irTreeExpList);
		default:
			return null;
		}
	}


	private Value translateConstantExpr(IRTreeConst constNode) {

		if(exprVsValue.containsKey(constNode)){
			return exprVsValue.get(constNode);
		}

		Value value = constNode.getExpressionValue();

		chkForGlobalVariableInSideConstantValue(value);

		Type type = value.getType();

		if(value.getType().isAggregateType()){
			StructType structType = returnStructTypeIfPresent(type);
			if(structType != null){
				NamedType namedType = new NamedType(structType, structType.getName());
				if(!namedTypes.contains(namedType)){
					namedTypes.add(namedType);
				}
			}
		}

		exprVsValue.put(constNode, value);

		return value;
	}

	private void chkForGlobalVariableInSideConstantValue(Value value) {
		if((value instanceof ConstantArray) || (value instanceof ConstantStruct)){
			
			List<Constant> constants = null;
			
			if(value instanceof ConstantArray){
				ConstantArray constantArray = (ConstantArray)value;
				constants = constantArray.getVal();
			}
			else if(value instanceof ConstantStruct){
				ConstantStruct constantStruct = (ConstantStruct)value;
				constants = constantStruct.getVal();
			}
			
			for(Constant constant : constants){
				if((constant instanceof ConstantArray) || (constant instanceof ConstantStruct)){
					chkForGlobalVariableInSideConstantValue(constant);
				}
				else if(constant instanceof ConstantExpr){
					ConstantExpr constantExpr = (ConstantExpr)constant;
					if(constantExpr.getOpCode() == InstructionID.GET_ELEMENT_PTR){
						GetElementPtrInst elementPtrInst = (GetElementPtrInst) constantExpr.getOperand(0);
						Value pointerOprnd = elementPtrInst.getPointerOperand();
						pointerOprnd = getGlobalVariableIfItExists(pointerOprnd);
						elementPtrInst.setOperand(0, pointerOprnd);
					}
				}
			}
		}
	}

	private StructType returnStructTypeIfPresent(Type type) {
		if(type.isArrayType()){
			ArrayType arrayType = (ArrayType)type;
			Type containedType = arrayType.getContainedType();
			if(containedType.isStructType())
				return (StructType) containedType;
			else if(containedType.isAggregateType())
				return returnStructTypeIfPresent(containedType);
		}
		return null;
	}

	private Value translateIRTreeExprList(IRTreeExpList irTreeExpList) {

		if(exprVsValue.containsKey(irTreeExpList)){
			return exprVsValue.get(irTreeExpList);
		}

		List<IRTreeExp> listOfIRTreeExprs = irTreeExpList.getListOfExprs();
		List<Constant> listOfConstants = new ArrayList<Constant>();
		Type containedType = null;
		ArrayType arrayType = null;
		ConstantArray constantArray = null;

		for(IRTreeExp irTreeExp : listOfIRTreeExprs){
			Value value = valueFromExpressionNode(irTreeExp, null);
			containedType = value.getType();
			List<Value> operandList = new ArrayList<Value>();
			operandList.add(value);
			InstructionID opcode = null;
			if(value.getValueTypeID() == ValueTypeID.INSTRUCTION){
				opcode = ((Instruction)value).getInstructionID();
			}
			ConstantExpr constantExpr = new ConstantExpr(value.getType(), operandList , opcode);
			listOfConstants.add(constantExpr);
		}

		try{
			arrayType = Type.getArrayType(compilationContext, containedType, listOfIRTreeExprs.size());
			constantArray = (ConstantArray) ConstantArray.get(arrayType, listOfConstants);
		}
		catch(TypeCreationException e){
			e.printStackTrace();
			System.exit(-1);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}

		exprVsValue.put(irTreeExpList, constantArray);

		return constantArray;
	}

	private Value createAllocaInstr(IRTreeAlloca irTreeAlloca) {
		if(exprVsValue.containsKey(irTreeAlloca)){
			return exprVsValue.get(irTreeAlloca);
		}

		Type type = irTreeAlloca.getType();
		IRTreeExp irTreeExp = irTreeAlloca.getIrTreeExp();
		AllocaInst allocaInst = null;
		Value arraySize = null;

		if(irTreeExp != null){ // Allocate memory for a variable length array
			arraySize = valueFromExpressionNode(irTreeExp, null);
			stackOfVariableLengthArrExprs.push(irTreeAlloca);
		}

		try {
			allocaInst = AllocaInst.create(properties, type, null, arraySize, currentBasicBlock);
			currentBasicBlock.addInstruction(allocaInst);

		} catch (InstructionCreationException e) {
			e.printStackTrace();
			System.exit(-1);
		}

		exprVsValue.put(irTreeAlloca, allocaInst);

		return allocaInst;
	}

	private Value getGlobalVariableIfItExists(Value value) {
		if(value.hasName())
			if(nameVsLocalVariable != null){
				if(!nameVsLocalVariable.containsKey(value.getName()))
					if(nameVsGlobalVariable.containsKey(value.getName())){
						setOfUsedValueNames.add(value.getName());
						functionVsUsedValueNames.put(currentFunction, value.getName());
						return nameVsGlobalVariable.get(value.getName());
					}
			}
			else if(nameVsGlobalVariable.containsKey(value.getName())){
				functionVsUsedValueNames.put(currentFunction, value.getName());
				setOfUsedValueNames.add(value.getName());
				return nameVsGlobalVariable.get(value.getName());
			}

		return value;
	}

	private Value translateCastExpr(IRTreeCast irTreeCastExpr) {

		if(exprVsValue.containsKey(irTreeCastExpr))
			return exprVsValue.get(irTreeCastExpr);

		IRTree destIrTree = irTreeCastExpr.getDestTree();
		IRTree srcIrTree = irTreeCastExpr.getSrcTree();

		Value destValue = null;
		Value srcValue = null;

		if(destIrTree instanceof IRTreeExp){
			IRTreeExp destExp = (IRTreeExp)destIrTree;
			destValue = valueFromExpressionNode(destExp, null);
		}
		if(srcIrTree instanceof IRTreeExp){
			IRTreeExp srcExp = (IRTreeExp)srcIrTree;
			srcValue = valueFromExpressionNode(srcExp, null);
		}

		Value castInst = null;
		try {
			castInst = castSourceToDestinationType(srcValue, destValue, false, true);
		} catch (InstructionCreationException e) {
			e.printStackTrace();
			System.exit(-1);
		}

		exprVsValue.put(irTreeCastExpr, castInst);

		if(irTreeCastExpr.isBitCastOpr()){ // for variable sized array
			stackOfVariableLengthArrExprs.push(irTreeCastExpr);
		}

		return castInst;
	}

	private Value translateCallExpr(IRTreeCallExp irTreeCallExp, IRTreeStatementList stmtList) {

		if(exprVsValue.containsKey(irTreeCallExp))
			return exprVsValue.get(irTreeCallExp);

		IRTreeMemory funcMemory = irTreeCallExp.getFunctionMemory();
		Value funcValue = funcMemory.getMemory();
		funcValue = getFunctionForValue(funcValue);
		funcValue = getCorrespondingAllocInstIfAvailable(funcValue);

		if(funcValue instanceof Function)
			listOfNamesOfFuncsUsed.add(funcValue.getName());
		else if(funcValue instanceof AllocaInst)
			funcValue = createLoadIns(funcValue, null);
		else { // May be Intrinsic Function so we should create it
			Function function = chkIfFuncAlreadyThereInModule(funcValue.getName());

			if(function == null){ // We need to create Intrinsic Function Declaration
				funcValue = Function.create(module, (PointerType) funcValue.getType(), null, CallingConv.C, funcValue.getName(), null);
				listOfNamesOfFuncsUsed.add(funcValue.getName());
			}
			else
				funcValue = function;
		}

		List<String> formatSpecifiers = null;

		boolean tailCall = irTreeCallExp.isTailCall();
		Type returnType = irTreeCallExp.getReturnType();
		Vector<Type> argsTypes = new Vector<Type>();
		List<Value> args = new ArrayList<Value>();

		List<IRTree> paramList = irTreeCallExp.getParamList();
		List<Type> formalTypes = irTreeCallExp.getFormalParamTypes();
		boolean isOnlyReadsMemory = irTreeCallExp.isOnlyReadsMemory();
		int index = 0;
		for(; index < formalTypes.size(); index++){
			IRTree irTree = paramList.get(index);
			Type formalType = formalTypes.get(index);
			Value value = new Value(formalType);
			Value paramValue = null;

			// Note here order of 'if-else' is important, because IRTreeMemory is also a type of IRTreeExp
			if(irTree instanceof IRTreeMemory){
				IRTreeMemory irTreeMemory = (IRTreeMemory)irTree;

				Value value2 = irTreeMemory.getMemory();
				value2 = getCorrespondingAllocInstIfAvailable(value2);
				value2 = getFunctionForValue(value2);
				Type typeOfArgument = value2.getType();
				// If the type of argument passed and type of formal is same then we don't 
				// need to load it from memory
				if(typeOfArgument != formalType)
					// Load it from memory
					paramValue = valueFromExpressionNode((IRTreeExp)irTree, stmtList);
				else
					paramValue = value2;
			}
			else if(irTree instanceof IRTreeTempOrVar){
				IRTreeTempOrVar irTreeTempOrVar = (IRTreeTempOrVar)irTree;
				paramValue = getLatestLoadInstruction(irTreeTempOrVar);
			}
			else if(irTree instanceof IRTreeExp){
				paramValue = valueFromExpressionNode((IRTreeExp)irTree, stmtList);
				Type type = paramValue.getType();
				if((paramValue instanceof GetElementPtrInst) && CFrontendUtils.isStdFunc(funcValue.getName())){
					GetElementPtrInst getElementPtrInst = (GetElementPtrInst)paramValue;
					Value value2 = getElementPtrInst.getPointerOperand();
					if(value2 instanceof GlobalVariable){
						GlobalVariable globalVariable = (GlobalVariable)value2;
						Value initializer = globalVariable.getInitializer();
						if(initializer instanceof ConstantArray){
							ConstantArray constantArray = (ConstantArray)initializer;
							String str = constantArray.getAsString();
							formatSpecifiers = CFrontendUtils.getListOfFormatSpecifiersFromString(str);
						}
					}
				}
				if((paramValue instanceof AllocaInst) && type != formalType){
					paramValue = createLoadIns(paramValue, null);
				}
			}
			else if(irTree instanceof IRTreeDeclaration){
				IRTreeDeclaration irTreeDeclaration = (IRTreeDeclaration)irTree;
				paramValue = getCorrespondingAllocInstIfAvailable(irTreeDeclaration.getMemoryTree().getMemory());
			}
			else if(irTree instanceof IRTreeStatementList){
				IRTreeStatementList irTreeStatementList = (IRTreeStatementList)irTree;
				while(irTreeStatementList != null){
					IRTreeStatement irTreeStatement = irTreeStatementList.getStatement();
					if(irTreeStatement instanceof IRTreeExpressionStm){
						IRTreeExpressionStm expressionStm = (IRTreeExpressionStm)irTreeStatement;
						IRTreeExp irTreeExp = expressionStm.getExpressionTree();
						paramValue = valueFromExpressionNode(irTreeExp, stmtList);
					}
					irTreeStatementList = irTreeStatementList.getStatementList();
				}
			}

			if((paramValue instanceof GetElementPtrInst) && !isOnlyReadsMemory && (paramValue.getType() != formalType))
				paramValue = createLoadIns(paramValue, null);

			Value castInst = null;
			try {
				castInst = castSourceToDestinationType(paramValue, value, true, false);
			} catch (InstructionCreationException e) {
				e.printStackTrace();
				System.exit(-1);
			}

			if(castInst != null)
				paramValue = castInst;

			argsTypes.add(formalType);
			paramValue = getGlobalVariableIfItExists(paramValue);
			args.add(paramValue);
		}

		Iterator<String> iterator = null;

		if(formatSpecifiers != null && formatSpecifiers.size() > 0){
			iterator = formatSpecifiers.iterator();
		}

		// Now add var args
		for(int i = index; i < paramList.size(); i++){
			IRTree irTree = paramList.get(i);
			Value paramValue = null;

			// Note here order of if else is important. because IRTreeMemory is also a type of IRTreeExp
			if(irTree instanceof IRTreeMemory){
				IRTreeMemory irTreeMemory = (IRTreeMemory)irTree;
				if(isOnlyReadsMemory){
					paramValue = irTreeMemory.getMemory();
				}
				else{
					// Load it from memory
					IRTreeExp exp = irTreeMemory.getIrTreeExp();
					paramValue = exprVsLoadInst.get(exp);
					if(paramValue == null)
						paramValue = valueFromExpressionNode((IRTreeExp)irTree, stmtList);
				}
			}
			else if(irTree instanceof IRTreeExp){
				paramValue = valueFromExpressionNode((IRTreeExp)irTree, stmtList);
				// TODO FIX THIS. Loading of GetElementPtr instruction should not depend upon the type of GetElementPtr instruction
				String formatSpecifier = null;
				if(iterator != null)
					formatSpecifier = iterator.next();
				if((paramValue instanceof GetElementPtrInst) && !isOnlyReadsMemory && formatSpecifier != null && ((!formatSpecifier.equals("%s")) || (formatSpecifier.equals("%s") && !paramValue.getType().toString().equals("i8*")))){
					paramValue = createLoadIns(paramValue, null);
				}
			}
			else if(irTree instanceof IRTreeDeclaration){
				IRTreeDeclaration irTreeDeclaration = (IRTreeDeclaration)irTree;
				paramValue = getCorrespondingAllocInstIfAvailable(irTreeDeclaration.getMemoryTree().getMemory());
			}
			else if(irTree instanceof IRTreeStatementList){
				IRTreeStatementList irTreeStatementList = (IRTreeStatementList)irTree;
				while(irTreeStatementList != null){
					IRTreeStatement irTreeStatement = irTreeStatementList.getStatement();
					if(irTreeStatement instanceof IRTreeExpressionStm){
						IRTreeExpressionStm expressionStm = (IRTreeExpressionStm)irTreeStatement;
						IRTreeExp irTreeExp = expressionStm.getExpressionTree();
						paramValue = valueFromExpressionNode(irTreeExp, stmtList);
					}
					irTreeStatementList = irTreeStatementList.getStatementList();
				}
			}

			paramValue = getGlobalVariableIfItExists(paramValue);
			paramValue = getCorrespondingAllocInstIfAvailable(paramValue);
			args.add(paramValue);
		}

		String name = "";

		if(returnType != Type.getVoidType(compilationContext)){
			name = null;
		}

		CallInst callInst = null;
		try {
			callInst = CallInst.create(funcValue, args, name, tailCall, false,currentBasicBlock);
		} catch (InstructionCreationException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		currentBasicBlock.addInstruction(callInst);

		try {
			int funcAttrs = irTreeCallExp.getFunc_attrs();
			List<Integer> listOfFuncAttrs = Attributes.getAttributesAsList(funcAttrs);
			for(Integer funcAttr : listOfFuncAttrs)
				callInst.addFuncAttribute(funcAttr);
			int returnAttrs = irTreeCallExp.getReturn_attrs();
			List<Integer> listOfReturnAttrs = Attributes.getAttributesAsList(returnAttrs);
			for(Integer returnAttr : listOfReturnAttrs){
				if(returnAttr == 1 || returnAttr == 2 || returnAttr == 8)
					callInst.addReturnAttributes(returnAttr);
			}
		} catch (InstructionUpdateException e) {
			e.printStackTrace();
			System.exit(-1);
		}


		exprVsValue.put(irTreeCallExp, callInst);
		return callInst;
	}

	private Value getFunctionForValue(Value value2) {
		if(value2.getName() != null && value2.getName().length() != 0){
			List<Function> functions = module.getFunctions();
			for(Function function : functions){
				String name = function.getName();
				if(value2.getName().equals(name) && value2.getType() == function.getType()){
					listOfNamesOfFuncsUsed.add(function.getName());
					return function;
				}
			}
		}
		return value2;
	}

	private Function chkIfFuncAlreadyThereInModule(String funcName) {
		List<Function> listOfFuncs = module.getFunctions();
		for(Function f : listOfFuncs){
			String functionName = f.getName();
			if(funcName != null && funcName.equals(functionName))
				return f;
		}
		return null;
	}

	private Value getLatestLoadInstruction(IRTreeTempOrVar irTreeTempOrVar) {

		Value srcValue = valueFromExpressionNode(irTreeTempOrVar, null);
		Value value = getAllocaInstrFromValueVsAllocaInstrMap(srcValue, memoryValAllocInstBinding);

		if(value == null && memoryValGlobalVariableBinding.containsKey(srcValue)){ // If its a global Variable
			value = srcValue;
		}
		if(nameVsGlobalVariable.containsKey(srcValue.getName()))
			value = srcValue;

		if(value != null){
			Value v = valueVsLoadInst.get(value);
			if(v == null)
				srcValue = valueVsLoadInst.get(srcValue);
			else
				srcValue = v;
		}
		return srcValue;
	}

	private Value translateAgrExpr(IRTreeAggregateData irTreeAgrExpr) {

		if(exprVsValue.containsKey(irTreeAgrExpr)){
			return exprVsValue.get(irTreeAgrExpr);
		}

		IRTree aggrData = irTreeAgrExpr.getAggregateDataStruct();
		boolean isStructure = false;
		Value value = null; 

		if(aggrData instanceof IRTreeMemory){
			IRTreeMemory irTreeMemory = (IRTreeMemory)aggrData;
			Value memory = irTreeMemory.getMemory();
			Type typ = memory.getType();

			if(typ.isPointerType()){
				PointerType pointerType = (PointerType)typ;
				Type containedType = pointerType.getContainedType();
				if(!containedType.isAggregateType() && irTreeMemory.getIrTreeExp() == null){
					value = valueFromExpressionNode(irTreeMemory, null);

					Type type = value.getType();
					if(type.isPointerType()){
						PointerType pointerType2 = (PointerType)type;
						Type containedType2 = pointerType2.getContainedType();

						if(containedType2.isStructType()){
							isStructure = true;
						}
					}
				}
				else{
					if(memory.getName() != null)
						memory = getCorrespondingAllocInstIfAvailable(memory);
					if(memory instanceof AllocaInst){
						AllocaInst allocaInst = (AllocaInst)memory;
						boolean chkIfItIsPointerToPointer = chkIfTheGivenValueIsPointerToPointer(allocaInst);
						if(chkIfItIsPointerToPointer){
							value = valueFromExpressionNode(irTreeMemory, null);
						}
					}
					if(value == null)
						value = memory;

					if(containedType.isStructType()){
						isStructure = true;
						StructType structType = (StructType)containedType;
						getListOfNestedStructType(structType, false);
					}
				}
			}

			if(irTreeMemory.getIrTreeExp() != null){
				IRTreeExp irTreeExp = irTreeMemory.getIrTreeExp();
				value = valueFromExpressionNode(irTreeExp, null);
			}
		}
		else if(aggrData instanceof IRTreeCast){
			IRTreeCast irTreeCast = (IRTreeCast)aggrData;
			value = valueFromExpressionNode(irTreeCast, null);
		}
		else{
			// Must be another AggregateExpr
			IRTreeAggregateData aggregateData = (IRTreeAggregateData)aggrData;
			value = translateAgrExpr(aggregateData);
		}

		Type typee = value.getType();
		if(typee.isPointerType() && (!(value instanceof LoadInst))){
			value = createMultipleLoadInstr(value);
		}
		typee = value.getType();
		if(typee.isPointerType()){
			PointerType pointerType = (PointerType)typee;
			if(pointerType.getContainedType().isStructType())
				isStructure = true;
		}

		List<Map.Entry<IRTreeIndex,Type>> indexVsType = irTreeAgrExpr.getIndexVsType();
		List<Pair<Value, Type>> indxVsType = new ArrayList<Pair<Value, Type>>();

		for(int i = 0; i < indexVsType.size(); i++){
			IRTreeIndex irTreeIndex = indexVsType.get(i).getKey();
			IRTree irTree = irTreeIndex.getIrTree();
			boolean isNegative = irTreeIndex.isNegative();
			Type type = indexVsType.get(i).getValue();
			if(indexVsType.size() == 1){
				type = typee;
			}
			if(irTree instanceof IRTreeConst){
				IRTreeConst irTreeConst = (IRTreeConst)irTree;
				Value constValue = irTreeConst.getExpressionValue();
				if(constValue instanceof ConstantInt){
					ConstantInt constantInt = (ConstantInt)constValue;
					APInt apInt = constantInt.getApInt();
					String val = apInt.getVal();
					int indexVal = Integer.parseInt(val);
					if(indexVal >= 0 && isNegative){
						val = "-" + val;
						apInt.setVal(val);
					}
					Value value2 = irTreeConst.getExpressionValue();
					int wordSize = LLVMUtility.getWordSize(properties);
					if(wordSize == 64 && apInt != null && indexVal != 0 && apInt.getNumBits() == 32 && i != 0 && !isStructure){
						apInt.setNumBits(64);
						value2.setType(Type.getInt64Type(compilationContext, true));
					}
					else if(apInt != null && apInt.getNumBits() == 64 && wordSize == 32){
						apInt.setNumBits(32);
						value2.setType(Type.getInt32Type(compilationContext, true));
					}
				}
				Pair<Value, Type> entry_IndexVsType = new Pair<Value, Type>(constValue, type);
				indxVsType.add(entry_IndexVsType);
			}
			else if(irTree instanceof IRTreeMemory){
				IRTreeMemory irTreeMemory = (IRTreeMemory)irTree;
				Value value1 = valueFromExpressionNode(irTreeMemory, null);
				if(value1.getType().isPointerType())
					value1 = createLoadIns(value1, null);
				Type typ = value1.getType();
				castIndexTo64BitsAndIntroduceABinSubIfReq(typ, value1, indxVsType, isNegative, type);
			}
			else if(irTree instanceof IRTreeBinaryExp){
				IRTreeBinaryExp binaryExp = (IRTreeBinaryExp)irTree;
				Value value2 = valueFromExpressionNode(binaryExp, null);
				Type typ = value2.getType();
				castIndexTo64BitsAndIntroduceABinSubIfReq(typ, value2, indxVsType, isNegative, type);
			}
			else if(irTree instanceof IRTreeAggregateData){
				IRTreeAggregateData aggregateData = (IRTreeAggregateData)irTree;
				Value value2 = valueFromExpressionNode(aggregateData, null);
				value2 = createLoadIns(value2, null);
				Type typ = value2.getType();
				castIndexTo64BitsAndIntroduceABinSubIfReq(typ, value2, indxVsType, isNegative, type);
			}
			else if(irTree instanceof IRTreeTempOrVar){
				Value value2 = getLatestLoadInstruction((IRTreeTempOrVar)irTree);
				Type typ = value2.getType();
				castIndexTo64BitsAndIntroduceABinSubIfReq(typ, value2, indxVsType, isNegative, type);
			}
		}

		String name = null;

		if(currentBasicBlock != null)
			name = null;

		try {
			value = getGlobalVariableIfItExists(value);
			GetElementPtrInst elementPtrInst = GetElementPtrInst.create(name, value, indxVsType, currentBasicBlock);
			elementPtrInst.setIsInBounds(true);
			exprVsValue.put(irTreeAgrExpr, elementPtrInst);
			// Because sometimes this instruction might be calculating address of a global pointer, 
			// which doesn't come under any function
			if(currentBasicBlock != null)
				currentBasicBlock.addInstruction(elementPtrInst);
			return elementPtrInst;
		} 
		catch (InstructionCreationException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		return null;
	}
	private Value createMultipleLoadInstr(Value value) {
		PointerType pointerType = (PointerType)value.getType();
		Type containedType = pointerType.getContainedType();
		while(containedType.isPointerType()){
			value = createLoadIns(value, null);
			Type type = value.getType();
			if(type.isPointerType()){
				pointerType = (PointerType)type;
				containedType = pointerType.getContainedType();
			}
		}
		return value;
	}

	private boolean chkIfTheGivenValueIsPointerToPointer(Value value) {
		Type type = value.getType();
		if(type.isPointerType()){
			PointerType pointerType = (PointerType)type;
			Type containedType = pointerType.getContainedType();
			if(containedType.isPointerType())
				return true;
		}
		return false;
	}

	/**
	 * Cast the Index to i64 Type and if the index is Negative then add a Binary Subtraction instruction also.
	 * @param typ
	 * @param value1
	 * @param indxVsType
	 * @param isNegative
	 * @param indexType
	 */
	private void castIndexTo64BitsAndIntroduceABinSubIfReq(Type typ, Value value1, List<Pair<Value, Type>> indxVsType, boolean isNegative, Type indexType) {

		Pair<Value, Type> entry_IndexVsType = null;
		int wordSize = LLVMUtility.getWordSize(properties);
		// Index should always be of 64 bits, if word size is also 64 bits
		if(!typ.isInt64Type() && wordSize == 64){
			try {
				value1 = castSourceToDestinationType(value1, new Value(Type.getInt64Type(compilationContext, true)), false, false);
				entry_IndexVsType = new Pair<Value, Type>(value1, indexType);
			} catch (InstructionCreationException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
		else if(!typ.isInt32Type() && wordSize == 32){
			try {
				value1 = castSourceToDestinationType(value1, new Value(Type.getInt32Type(compilationContext, true)), false, false);
				entry_IndexVsType = new Pair<Value, Type>(value1, indexType);
			} catch (InstructionCreationException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
		else{ // for 32 bit index
			entry_IndexVsType = new Pair<Value, Type>(value1, indexType);
		}

		if(isNegative){
			Value rightVal = value1;
			APInt val = null;
			Value leftVal = null;
			try {
				if(rightVal.getType().isInt32Type()){
					val = new APInt(32, "0", false);
					leftVal = new ConstantInt(Type.getInt32Type(compilationContext, false), val);
				}
				else if(rightVal.getType().isInt64Type()){
					val = new APInt(64, "0", false);
					leftVal = new ConstantInt(Type.getInt64Type(compilationContext, false), val);
				}
			} 
			catch (InstantiationException e) {
				e.printStackTrace();
				System.exit(-1);
			}
			// Create the subtraction binary operation instruction
			//TODO get actual values of noSignedWrap, noUnsignedWrap and exact flags later
			boolean noSignedWrap = false;
			boolean noUnsignedWrap = false;
			boolean exact = false;
			BinaryOperator binOpr = null;
			try {
				binOpr = BinaryOperator.create(
						BinaryOperatorID.SUB, 
						rightVal.getType(), null,
						leftVal, rightVal, noSignedWrap, noUnsignedWrap, exact, currentBasicBlock);
				entry_IndexVsType = new Pair<Value, Type>(binOpr, indexType);
			} catch (InstructionCreationException e) {
				e.printStackTrace();
				System.exit(-1);
			}
			currentBasicBlock.addInstruction(binOpr);
		}

		indxVsType.add(entry_IndexVsType);
	}

	private Value translatePhiNode(IRTreePhiExpr irTreePhiExpr, IRTreeStatementList stmtList) {

		if(exprVsValue.containsKey(irTreePhiExpr)){
			return exprVsValue.get(irTreePhiExpr);
		}

		Map<Label, IRTreeExp> labelVsExprTree = irTreePhiExpr.getLabelVsExprMap();
		PhiNode phiNode = null;
		Type type = irTreePhiExpr.getType();
		phiNode = PhiNode.create(type, null, labelVsExprTree.size(), new Value(type), currentBasicBlock);
		Set<Map.Entry<Label, IRTreeExp>> entrySet = labelVsExprTree.entrySet();
		List<Map.Entry<Label, IRTreeExp>> list = new ArrayList<Map.Entry<Label, IRTreeExp>>(entrySet);
		Iterator<Map.Entry<Label, IRTreeExp>> iterator = list.iterator();

		while(iterator.hasNext()){
			Map.Entry<Label, IRTreeExp> entry = iterator.next();
			IRTreeExp irTreeExpr = entry.getValue();
			Value value = null;

			if(irTreeExpr.getExpType() == TreeNodeExpType.MEMORY){
				IRTreeMemory irTreeMemory = (IRTreeMemory)irTreeExpr;
				Value value2 = irTreeMemory.getMemory();
				value2 = getCorrespondingAllocInstIfAvailable(value2);
				value = valueVsLoadInst.get(value2);
			}
			else
				value = valueFromExpressionNode(irTreeExpr, stmtList);

			Label label = entry.getKey();
			BasicBlock tempBB = null;

			if(labelVsBBMap.containsKey(label)){
				tempBB = labelVsBBMap.get(label);
			}
			try {
				value = getGlobalVariableIfItExists(value);
				phiNode.addIncoming(value, tempBB);
			} 
			catch (InstructionUpdateException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}

		int nosOfOperands = phiNode.getNumOperands();

		for(int i = 0; i < nosOfOperands; i++){
			try{
				phiNode.setIncomingValueAndBasicBlock(i, phiNode.getIncomingValue(i), 
						cfg.getPredecessors(phiNode.getParent()).get(i));
			}
			catch(InstructionDetailAccessException e){
				e.printStackTrace();
				System.exit(-1);
			}
			catch (InstructionUpdateException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}

		listOfPhiNodes.add(phiNode);
		currentBasicBlock.addInstruction(phiNode);
		phiNode.setParent(currentBasicBlock);
		exprVsValue.put(irTreePhiExpr, phiNode);
		return phiNode;
	}

	private Value translateConditionalExpr(IRTreeConditionalExpr conditionalExpr, IRTreeStatementList stmtList){

		if(exprVsValue.containsKey(conditionalExpr)){
			return exprVsValue.get(conditionalExpr);
		}

		IRTreeExp leftExp = conditionalExpr.getLeftExp();
		IRTreeExp rightExp = conditionalExpr.getRightExp();
		Value leftExpValue = valueFromExpressionNode(leftExp, stmtList);
		Value rightExpValue = valueFromExpressionNode(rightExp, stmtList);
		Type leftExpType = leftExpValue.getType();
		Type rightExpType = rightExpValue.getType();

		//for pointer type create a load instruction, and add it to the current BB
		if(leftExpType.isPointerType()){
			LoadInst leftLoadInst = (LoadInst) createLoadIns(leftExpValue, null);
			leftExpType = leftLoadInst.getType();
			leftExpValue = leftLoadInst;
		}

		if(rightExpType.isPointerType()){
			LoadInst rightLoadInst = (LoadInst) createLoadIns(rightExpValue, null);
			rightExpType = rightLoadInst.getType();
			rightExpValue = rightLoadInst;
		}

		boolean intComparison = false;

		if(leftExpType.isIntegerType() || leftExpType.isPointerType()){
			if(rightExpType.isIntegerType()){
				intComparison = true;
				if(leftExpType != rightExpType){
					// TODO : extension for different width integers
					Value castInst = null;
					try {
						castInst = castSourceToDestinationType(leftExpValue, rightExpValue, false, false);
					} catch (InstructionCreationException e) {
						e.printStackTrace();
						System.exit(-1);
					}
					if(castInst != null)
						leftExpValue = castInst;
				}
			}
			else{
				// TODO : extension for floating point types
			}
		}

		Predicate predicate = conditionalExpr.getPredicate();
		String compareInsName = null;
		Value compareVal = null;

		leftExpValue = getGlobalVariableIfItExists(leftExpValue);
		rightExpValue = getGlobalVariableIfItExists(rightExpValue);

		try{
			if(intComparison){
				ICmpInst iCmpInst = ICmpInst.create(predicate, leftExpValue, rightExpValue, compareInsName, currentBasicBlock);
				currentBasicBlock.addInstruction(iCmpInst);
				compareVal =iCmpInst;
			}
			else{
				if(!leftExpValue.getType().isFloatingPointType())
					leftExpValue = castSourceToDestinationType(leftExpValue, new Value(Type.getDoubleType(compilationContext)), false, false);
				else if(!rightExpValue.getType().isFloatingPointType())
					rightExpValue = castSourceToDestinationType(rightExpValue, new Value(Type.getDoubleType(compilationContext)), false, false);
				FCmpInst fCmpInst = FCmpInst.create(predicate, leftExpValue, rightExpValue, compareInsName, currentBasicBlock);
				currentBasicBlock.addInstruction(fCmpInst);
				compareVal = fCmpInst;
			}
		}
		catch(InstructionCreationException ice){
			ice.printStackTrace();
			System.exit(-1);
		}

		exprVsValue.put(conditionalExpr, compareVal);
		return compareVal;
	}


	private Value translateBinaryExpr(IRTreeBinaryExp llvmTreeNodeBinExp, IRTreeStatementList stmtList){
		if(exprVsValue.containsKey(llvmTreeNodeBinExp)){
			return exprVsValue.get(llvmTreeNodeBinExp);
		}

		IRTreeExp nodeExpLeft = llvmTreeNodeBinExp.getLeft();
		IRTreeExp nodeExpRight = llvmTreeNodeBinExp.getRight();
		Value leftVal = null;
		Value rightVal = null;

		if(nodeExpLeft instanceof IRTreeMemory){
			IRTreeMemory irTreeMemory = (IRTreeMemory)nodeExpLeft;
			IRTreeExp irTreeExp = irTreeMemory.getIrTreeExp();
			if(irTreeExp instanceof IRTreeMemory){
				IRTreeMemory irTreeMemory2 = (IRTreeMemory)irTreeExp;
				leftVal = valueFromExpressionNode(irTreeMemory2, stmtList);
			}
		}

		if(nodeExpRight instanceof IRTreeMemory){
			IRTreeMemory irTreeMemory = (IRTreeMemory)nodeExpRight;
			IRTreeExp irTreeExp = irTreeMemory.getIrTreeExp();
			if(irTreeExp instanceof IRTreeMemory){
				IRTreeMemory irTreeMemory2 = (IRTreeMemory)irTreeExp;
				rightVal = valueFromExpressionNode(irTreeMemory2, stmtList);
			}
		}
		else if(nodeExpRight instanceof IRTreeTempOrVar){
			IRTreeTempOrVar irTreeTempOrVar = (IRTreeTempOrVar)nodeExpRight;
			rightVal = getLatestLoadInstruction(irTreeTempOrVar);
		}

		if(leftVal == null)
			leftVal = valueFromExpressionNode(nodeExpLeft, stmtList);
		if(rightVal == null)
			rightVal = valueFromExpressionNode(nodeExpRight, stmtList);

		if(leftVal.getType().isPointerType()){
			Value templeftVal = valueVsLoadInst.get(leftVal);
			if(templeftVal == null)
				leftVal = createLoadIns(leftVal, null);
			else
				leftVal = templeftVal;
		}
		if(rightVal.getType().isPointerType()){
			Value tempRightVal = valueVsLoadInst.get(rightVal);
			if(tempRightVal == null)
				rightVal = createLoadIns(rightVal, null);
			else
				rightVal = tempRightVal;
		}

		Value srcValue = leftVal;
		Value destValue = rightVal;
		Type leftType = leftVal.getType();
		Type rightType = rightVal.getType();

		if(leftType != rightType){
			if(leftType.isFloatingPointType() && rightType.isIntegerType()){
				srcValue = rightVal;
				destValue = leftVal;
			}
			else if(rightType.isFloatingPointType() && leftType.isIntegerType()){
				srcValue = leftVal;
				destValue = rightVal;
			}
			else if(rightType.isDoubleType() && leftType.isFloatType()){
				srcValue = leftVal;
				destValue = rightVal;
			}
			else if(leftType.isDoubleType() && rightType.isFloatType()){
				srcValue = rightVal;
				destValue = leftVal;
			}
		}

		Value castInst = null;
		try {
			castInst = castSourceToDestinationType(srcValue, destValue, true, false);
		} catch (InstructionCreationException e) {
			e.printStackTrace();
			System.exit(-1);
		}

		if(castInst != null)
			srcValue = castInst;

		if(destValue == rightVal){
			leftVal = srcValue;
			rightVal = destValue;
		}
		else{
			leftVal = destValue;
			rightVal = srcValue;
		}

		// Create the binary operation instruction
		BinaryOperator binOpr =  null;
		try{
			// Create the actual binary operation instruction
			//TODO get actual values of noSignedWrap, noUnsignedWrap and exact flags later
			boolean noSignedWrap = false;
			boolean noUnsignedWrap = false;
			boolean exact = false;

			leftVal = getGlobalVariableIfItExists(leftVal);
			rightVal = getGlobalVariableIfItExists(rightVal);

			BinaryOperatorID binaryOperatorID = llvmTreeNodeBinExp.getBinaryOperatorID();

			if(leftVal.getType().isInt8Type() && rightVal.getType().isInt8Type() && (binaryOperatorID == BinaryOperatorID.ADD || binaryOperatorID == BinaryOperatorID.MUL || binaryOperatorID == BinaryOperatorID.SUB || binaryOperatorID == BinaryOperatorID.SDIV || binaryOperatorID == BinaryOperatorID.UDIV || binaryOperatorID == BinaryOperatorID.UREM || binaryOperatorID == BinaryOperatorID.SREM || binaryOperatorID == BinaryOperatorID.SHL || binaryOperatorID == BinaryOperatorID.LSHR || binaryOperatorID == BinaryOperatorID.ASHR)){
				castInst = castSourceToDestinationType(leftVal, new Value(Type.getInt32Type(compilationContext, true)), true, false);

				if(castInst != null)
					leftVal = castInst;

				castInst = castSourceToDestinationType(rightVal, new Value(Type.getInt32Type(compilationContext, true)), true, false);

				if(castInst != null)
					rightVal = castInst;
			}

			binOpr = BinaryOperator.create(
					binaryOperatorID, 
					leftVal.getType(), null, 
					leftVal, rightVal, noSignedWrap, noUnsignedWrap, exact, currentBasicBlock);
			currentBasicBlock.addInstruction(binOpr);
		}
		catch(InstructionCreationException ice){
			ice.printStackTrace();
			System.exit(-1);
		}

		exprVsValue.put(llvmTreeNodeBinExp, binOpr);
		return binOpr;
	}

//	public List<Instruction> getInstructions() {
//		CFGLinearizerBFS linearizer = new CFGLinearizerBFS();
//		linearizer.execute(cfg, startBlock);
//		List<Instruction> instrs = linearizer.getInstructions();
//		return instrs;
//	}

	public CFG getCFG(){
		return cfg;
	}

	public Module getModule() {
		return module;
	}

	private Vector<Type> getParamTypesFromParamValues(
			List<Value> formalParamValues) {
		Vector<Type> paramTypes = new Vector<Type>(formalParamValues.size());
		for(int i = 0; i < formalParamValues.size(); i++)
			paramTypes.addElement(formalParamValues.get(i).getType());
		return paramTypes;
	}

	public void getListOfNestedStructType(StructType structType, boolean isRecursive) {
		Integer memberSize = structType.getElementSize();
		for(int i = 0; i < memberSize; i++){
			Type type = structType.getTypeAtIndex(i);

			if(type.isArrayType()){
				ArrayType arrayType = (ArrayType)type;
				type = arrayType.getActualContainedType();
			}
			if(type.isStructType()){
				StructType structType2 = (StructType)type;
				NamedType namedType = new NamedType(structType2, structType2.getName());
				if(!namedTypes.contains(namedType)){

					if(isRecursive){
						namedTypes.add(namedTypes.size() -1, namedType);
					}
					else
						namedTypes.add(namedType);

					StructType structType3 = null;
					Iterator<NamedType> iterator = namedTypes.iterator();
					while(iterator.hasNext()){
						NamedType namedType2 = iterator.next();
						if(namedType2.getType().isStructType()){
							structType3 = (StructType) namedType2.getType();
							if(structType3 == structType2)
								break;
						}
					}

					if(structType3 != null)
						structType2.setName(structType3.getName());

					continue;
				}
				getListOfNestedStructType(structType2, true);
			}
		}
		NamedType namedType = new NamedType(structType, structType.getName());
		
		if(!namedTypes.contains(namedType)){
			namedTypes.add(namedType);
			StructType structType2 = null;
			Iterator<NamedType> iterator = namedTypes.iterator();
			while(iterator.hasNext()){
				NamedType namedType2 = iterator.next();
				if(namedType2.getType().isStructType()){
					structType2 = (StructType)namedType2.getType();
					if(structType2 == structType)
						break;
				}
			}

			if(structType2 != null)
				structType.setName(structType2.getName());
		}
	}
}
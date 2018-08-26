package org.tamedragon.common.llvmir.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.tamedragon.common.DominatorTreeNode;
import org.tamedragon.common.TreeNode;
import org.tamedragon.common.llvmir.instructions.CallInst;
import org.tamedragon.common.llvmir.instructions.Instruction;
import org.tamedragon.common.llvmir.instructions.Instruction.InstructionID;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.GlobalVariable;
import org.tamedragon.common.llvmir.types.Module;
import org.tamedragon.common.llvmir.types.NamedType;
import org.tamedragon.common.llvmir.types.StructType;
import org.tamedragon.common.llvmir.types.Type;
import org.tamedragon.common.llvmir.types.Value;
import org.tamedragon.common.llvmir.types.Value.ValueTypeID;
import org.tamedragon.target.TargetData;

/**
 * Helper class to emit the LLVM IR for a module.
 */

public class LLVMIREmitter {

	private LLVMIREmitter(){
		processedFunctions = new HashMap<Function, Boolean>();
		reset();
	}

	private static LLVMIREmitter singleInstance;

	private HashMap<Function, Boolean> processedFunctions;

	public static LLVMIREmitter getInstance(){
		if(singleInstance == null)
			singleInstance = new LLVMIREmitter();

		return singleInstance;
	}

	public void reset(){
		processedFunctions = new HashMap<Function, Boolean>();
	}

	private List<Value> listOfValues;

	public void processFunction(Function function){
		if(function == null){
			return;
		}
		
		int numBasicBlocks = function.getNumBasicBlocks();

		if(numBasicBlocks == 0){
			processedFunctions.put(function, true);
			return;
		}

		listOfValues = new ArrayList<Value>();
		Iterator<BasicBlock> basicBlockIterator = function.basicBlocksIterator();
		while(basicBlockIterator.hasNext()) {
			BasicBlock basicBlock = basicBlockIterator.next();
			if(!basicBlock.hasName()){
				listOfValues.add(basicBlock);
			}
			
			Iterator<Instruction> instructions = basicBlock.getInstructions();
			if(instructions == null){
				continue;
			}
			while(instructions.hasNext()){
				Instruction instruction = instructions.next();
				if(instruction.getName() != null || instruction.getType().isVoidType())
					continue;
				else if(instruction.getInstructionID() == InstructionID.BINARY_INST )
					listOfValues.add(instruction);
				else if(instruction.getInstructionID() == InstructionID.SELECT_INST )
					listOfValues.add(instruction);
				else if(instruction.getInstructionID() == InstructionID.ALLOCA_INST)
					listOfValues.add(instruction);
				else if(instruction.getInstructionID() == InstructionID.LOAD_INST )
					listOfValues.add(instruction);
				else if(instruction.getInstructionID() == InstructionID.GET_ELEMENT_PTR)
					listOfValues.add(instruction);
				else if(instruction.isCastInstruction())
					listOfValues.add(instruction);
				else if(instruction.getInstructionID() == InstructionID.ICMP_INST)
					listOfValues.add(instruction);
				else if(instruction.getInstructionID() == InstructionID.FCMP_INST)
					listOfValues.add(instruction);
				else if(instruction.getInstructionID() == InstructionID.PHI_NODE_INST)
					listOfValues.add(instruction);
				else if(instruction.getInstructionID() == InstructionID.CALL_INST){
					CallInst callInst = (CallInst)instruction;
					if(!callInst.getType().isVoidType())
						listOfValues.add(instruction);
				}
			}
		}
		processedFunctions.put(function, true);
	}

	public List<String> emitInstructions(Module module){
		List<String> listOfStrings = new ArrayList<String>() ;
		
		// emit TargetData is available
		TargetData targetData = module.getTargetData();
		if (targetData != null){
			String target = "target datalayout = " + targetData.getStringRepresentation();
			listOfStrings.add(target);
			listOfStrings.add("\n");
		}
		
		List<NamedType> namedTypes = module.getNamedTypes();
		Iterator<NamedType> iterator = namedTypes.iterator();
		
		// Construct named types
		while(iterator.hasNext()){
			NamedType namedType = iterator.next();
			if(namedType.getType().isStructType()){
				StructType structType = (StructType) namedType.getType();
				String namedTypeStr =  "%" + namedType.getName() + " = type { ";
				int nosOfElements = structType.getElementSize();
				for(int i = 0; i < nosOfElements; i++){
					Type typ = structType.getTypeAtIndex(i);
					String s = null;

					if(typ.isStructType())
						s = "%" + ((StructType)typ).getName();
					else
						s = typ.toString();

					namedTypeStr += (i < (nosOfElements - 1))? s + ", " : s + " }";
				}
				listOfStrings.add(namedTypeStr);
			}
			else if(namedType.getType().isIntegerType()){ // Enum Types
				String namedTypeStr =  "%" + namedType.getName() + " = type " + namedType.getType().toString();
				listOfStrings.add(namedTypeStr);
			}
		}
		if(namedTypes.size() > 0){
			listOfStrings.add("\n");
		}
		
		
		// Get the global variables of the module and add it to the list of instructions strings. 
		// Add them to a set to remove any duplicate entries
		List<GlobalVariable> globalVariables = module.getGlobalVariables();
		Set<GlobalVariable> setOfGlobalVariables = new LinkedHashSet<GlobalVariable>(globalVariables);
		for(GlobalVariable globalVariable : setOfGlobalVariables)
			listOfStrings.add(globalVariable.emit());
		if(setOfGlobalVariables.size() > 0){
			listOfStrings.add("\n");
		}

		// Get each Function from the module and add them to the 
		// list of instructions 
		List<Function> functions = module.getFunctions();
		Iterator<Function> funcIterator = functions.iterator();
		
		while(funcIterator.hasNext()){
			Function function = funcIterator.next();
			listOfStrings.addAll(emitInstructions(function));
			listOfStrings.add("\n");
		}
		
		return listOfStrings;
	}

	public List<String> emitInstructions(Function function){
		List<String> listOfStrings = new ArrayList<String>();
		processFunction(function);
		int numBasicBlocks = function.getNumBasicBlocks();

		if(numBasicBlocks == 0){
			// This is a function declaration
			String functionDeclr = "declare " + function.toString();
			listOfStrings.add(functionDeclr);
		}
		else{
			// This is a function definition
			// Start function definition
			String s = "define " + function.toString() + " {";
			listOfStrings.add(s);
			Iterator<BasicBlock> basicBlockIterator = function.basicBlocksIterator();
			while(basicBlockIterator.hasNext()) {
				BasicBlock basicBlock = basicBlockIterator.next();
				List<String> instructionsInBasicBlock = 
					basicBlock.emitInstructions(listOfValues);
				listOfStrings.addAll(instructionsInBasicBlock);
			}
			// End function definition
			listOfStrings.add("}");
		}
		return listOfStrings;
	}

	public List<String> emitDominatorTree(DominatorTreeNode root){

		List<String> treeDescriptions = new ArrayList<String>();

		emitDomTreeNode(root, treeDescriptions);

		return treeDescriptions;

	}

	private void emitDomTreeNode(DominatorTreeNode dominatorTreeNode,
			List<String> treeDescriptions) {
		String str = "Node: " + getDomTreeNodeName(dominatorTreeNode) + " Children = ";
		Vector<TreeNode> children = dominatorTreeNode.getChildren();

		int numNodes = dominatorTreeNode.getChildren().size();
		for(int i = 0; i < numNodes; i++){
			DominatorTreeNode dtNode = (DominatorTreeNode) children.elementAt(i);
			str += getDomTreeNodeName(dtNode) + ", ";
		}
		treeDescriptions.add(str);

		for(int j = 0; j < numNodes; j++){
			DominatorTreeNode dtNode = (DominatorTreeNode) children.elementAt(j);
			emitDomTreeNode(dtNode, treeDescriptions);
		}
	}

	private String getDomTreeNodeName(DominatorTreeNode treeNode){
		BasicBlock graphNode = treeNode.getGraphNode();
		return getValidName(((BasicBlock)graphNode));
	}

	public String getValidName(Value value){

		String globalPrefix = "@";
		String localPrefix = "%";
		
		String prefix = "";
		
		if(value instanceof GlobalVariable)
			prefix = globalPrefix;
		else if(value instanceof Function){
			return globalPrefix + value.getName();
		}
		else
			prefix = localPrefix;
		
		// If the value already has a name, return it 
		// right away
		if(value == null)
			return "null";

		if(value.hasName()){
			String name = value.getName();
			if(name.equals("null"))
				return name;
			else
				return prefix + name;
		}
		
		if(value.getType().isVoidType())
			return "";

		// The value has a name; construct it
		ValueTypeID valueTypeID = value.getValueTypeID();
		switch(valueTypeID){
		case GLOBAL_ALIAS:         
			// TODO This is an instance of GlobalAlias
			break;
		case ARGUMENT:
			// TODO This is an instance of Argument
			break;
		case BLOCK_ADDRESS:
			// TODO This is an instance of BlockAddress
			break;
		case MD_NODE:
			// TODO This is an instance of MDNode
			break;
		case MD_STRING:              
			// This is an instance of MDString
			break;
		case INLINE_ASM:             
			// This is an instance of InlineAsm
			break;
		case PSEUDO_SRC_VALUE:     
			// This is an instance of PseudoSourceValue
			break;
		case FIXED_STACK_PSEUDO_SRC: 
			// This is an instance of FixedStackPseudoSourceValue
			break;
		case UNDEF_VALUE:
			return "undef";

		case CONST_EXPR:
			return value.toString();
		case CONST_AGGREGATE_ZERO:
		case CONST_INT:
		case CONST_FP:
		case CONST_ARRAY:
		case CONST_STRUCT:
		case CONST_VECTOR:
		case CONST_POINTER_NULL:
			return value.getName();

		case INSTRUCTION:
			Instruction instr = (Instruction) value;
			// If the instruction is used in Global Variable
			if(instr.getParent() == null){
				if(instr.hasName())
					return prefix + instr.getName();
				else
					return "";
			}
			Function func = instr.getParent().getParent();
			Boolean funcProcessed = processedFunctions.get(func);
			
			if(funcProcessed == null)
				processFunction(func);

			if(listOfValues.indexOf(value) != -1)
				return prefix + listOfValues.indexOf(value);
			else
				return "?";  // Invalid?
		case BASIC_BLOCK:
			BasicBlock basicBlock = (BasicBlock)value;
			func = basicBlock.getParent();
			funcProcessed = processedFunctions.get(func);
			
			//if(funcProcessed == null)
				processFunction(func);
			
			if(listOfValues.indexOf(value) != -1)
				return prefix + listOfValues.indexOf(value);
			else
				return "?";  // Invalid?
			
		default:
			if(listOfValues.indexOf(value) != -1)
				return prefix + listOfValues.indexOf(value);
			else
				return "?";  // Invalid?
		}
		return "?";  // Invalid?
	}
}

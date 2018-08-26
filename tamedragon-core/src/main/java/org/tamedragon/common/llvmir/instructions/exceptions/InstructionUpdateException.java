package org.tamedragon.common.llvmir.instructions.exceptions;

import java.util.ArrayList;
import java.util.List;

public class InstructionUpdateException extends BaseCoreException{
	
	// Error messages related to the return instruction
	public static final String RETURN_INST_CANNOT_SET_SUCCESSOR = "Cannot set a successor for a return instruction.";
	
	// Error messages related to the branch instructions
	public static final String BRANCH_INST_CANNOT_SET_CONDITION_FOR_UNCONDITIONAL_BRANCH = 
					"Cannot set the condition of an unconditional branch.";
	public static final String BRANCH_INST_CANNOT_SWAP_SUCCESSORS_FOR_UNCONDITIONAL_BRANCH = 
					"The successors of an unconditional branch cannot be swapped since there is only successor.";
	
	// Error messages related to the switch instructions
	public static final String CONSTANT_INT_CANNOT_BE_NULL = "Case value cannot be null.";
	public static final String BASIC_BLOCK_CANNOT_BE_NULL = "Basic block cannot be null.";
	public static final String CASE_VALUES_CANNOT_BE_DUPLICATE = "Case values cannot be duplicate.";
	public static final String CANNOT_REMOVE_DEFAULT_CASE = "The default case cannot be removed.";
	
	// Error messages related to the alloca instructions
	public static final String ALIGNMENT_CANNOT_BE_MORE_THAN_MAX_ALIGNMENT = "Alignment cannot greater than the maximum alignment.";
	public static final String ALIGNMENT_CANNOT_BE_LESS_THAN_ONE = "Alignment cannot be less than one";
	
	// Error messages related to the alloca and load instructions
	public static final String ALIGNMENT_IS_NOT_A_POWER_OF_TWO = "Alignment is not a power of two";
	
	// Error messages related to the load and store instructions
	public static final String ONLY_ATOMIC_LOAD_OR_STORE_INSTR_CAN_BE_SINGLE_THREADED = "Only Atomic load/store instructions can be single threaded";
	public static final String INVALID_ATOMIC_ORDER_FOR_LOAD_OR_STORE_INSTR = "Invalid Atomic Ordering for Load/Store Instruction";
	public static final String ATOMIC_ORDERING_CAN_BE_SET_ONLY_FOR_ATOMIC_LOAD_OR_STORE_INSTR = "Atomic ordering can only be set for Atomic Load/Store Instructions";

	// Error messages related to the phi node instructions
	public static final String PHI_VALUE_CANNOT_BE_NULL = "Phi value cannot be null";
	public static final String INCOMPATIBLE_OPERAND_FOR_PHI_NODE = 
		"All operands to PHI node must be the same type as the PHI node";
	
	// Error messages related to the function instructions
	public static final String ATTRIBUTE_IS_NOT_A_POWER_OF_TWO = "Attribute is not a power of 2";
	public static final String INVALID_ATTR_FOR_FUNCTION = "Invalid attribute for function";
	
	// Error messages related to call instruction updates
	public static final String CANNOT_SET_A_RET_ATTR_TO_FUNC_RET_VOID = "Cannot set a return attribute for a function returning void";
	public static final String INVALID_ATTR_FOR_FUNCTION_IN_CALL_INSTR = "Invalid attribute for function in the call instruction";
	public static final String INVALID_ATTR_FOR_FUNCTION_RET_TYPE = "Invalid attribute type for the return value of a function in call instruction";
	
	// Error messages related to select instruction updates
	public static final String CONDITION_TYPE_NOT_I1_FOR_SELECT = "The condition for a select instruction must be of type i1.";
	public static final String CONDITION_TYPE_CANNOT_BE_NULL_FOR_SELECT = "The condition type in the selection instruction cannot be null.";
	public static final String INCOMPATIBLE_SELECT_TYPE_FOR_SELECT = "The selection type must be compatible with the type of the other selection.";
	public static final String SELECT_VALUE_CANNOT_BE_NULL_FOR_SELECT = "The selection value cannot be null.";
	
	private static List<String> ERROR_MESSAGES = new ArrayList<String>(); 
	
    static {
		// Add error messages related to update of return instruction
		ERROR_MESSAGES.add(RETURN_INST_CANNOT_SET_SUCCESSOR);
		
		// Add error messages related to update of branch instructions
		ERROR_MESSAGES.add(BRANCH_INST_CANNOT_SET_CONDITION_FOR_UNCONDITIONAL_BRANCH);
		ERROR_MESSAGES.add(BRANCH_INST_CANNOT_SWAP_SUCCESSORS_FOR_UNCONDITIONAL_BRANCH);
		ERROR_MESSAGES.add(CASE_VALUES_CANNOT_BE_DUPLICATE);
		
		// Add error messages related to update of alloca instructions
		ERROR_MESSAGES.add(ALIGNMENT_IS_NOT_A_POWER_OF_TWO);
		ERROR_MESSAGES.add(ALIGNMENT_CANNOT_BE_MORE_THAN_MAX_ALIGNMENT);
		ERROR_MESSAGES.add(ALIGNMENT_CANNOT_BE_LESS_THAN_ONE);
		
		// Add error messages related to update of load instructions
		ERROR_MESSAGES.add(INVALID_ATOMIC_ORDER_FOR_LOAD_OR_STORE_INSTR);
		ERROR_MESSAGES.add(ATOMIC_ORDERING_CAN_BE_SET_ONLY_FOR_ATOMIC_LOAD_OR_STORE_INSTR);
		ERROR_MESSAGES.add(ONLY_ATOMIC_LOAD_OR_STORE_INSTR_CAN_BE_SINGLE_THREADED);
		
		// Add error messages related to update of switch instructions
		ERROR_MESSAGES.add(CONSTANT_INT_CANNOT_BE_NULL);
		ERROR_MESSAGES.add(BASIC_BLOCK_CANNOT_BE_NULL);
		ERROR_MESSAGES.add(CASE_VALUES_CANNOT_BE_DUPLICATE);
		ERROR_MESSAGES.add(CANNOT_REMOVE_DEFAULT_CASE);
		
		// Add error messages related to update of phi node instruction
		ERROR_MESSAGES.add(PHI_VALUE_CANNOT_BE_NULL);
		ERROR_MESSAGES.add(INCOMPATIBLE_OPERAND_FOR_PHI_NODE);
		
		// Add error messages related to update of function instruction
		ERROR_MESSAGES.add(ATTRIBUTE_IS_NOT_A_POWER_OF_TWO);
		ERROR_MESSAGES.add(INVALID_ATTR_FOR_FUNCTION);
		
		// Add error messages related to update of call instruction
		ERROR_MESSAGES.add(CANNOT_SET_A_RET_ATTR_TO_FUNC_RET_VOID);
		ERROR_MESSAGES.add(INVALID_ATTR_FOR_FUNCTION_RET_TYPE);
		ERROR_MESSAGES.add(INVALID_ATTR_FOR_FUNCTION_IN_CALL_INSTR);
		
		// Add error messages related to update of select instruction
		ERROR_MESSAGES.add(CONDITION_TYPE_NOT_I1_FOR_SELECT);
		ERROR_MESSAGES.add(CONDITION_TYPE_CANNOT_BE_NULL_FOR_SELECT);
		ERROR_MESSAGES.add(INCOMPATIBLE_SELECT_TYPE_FOR_SELECT);
		ERROR_MESSAGES.add(SELECT_VALUE_CANNOT_BE_NULL_FOR_SELECT);
	}
    
    public InstructionUpdateException(String ... messageParts){
    	super(ERROR_MESSAGES, messageParts);
    }
    
    public InstructionUpdateException(Exception cause, String ... messageParts){
    	super(ERROR_MESSAGES, cause, messageParts);
    }
}

package org.tamedragon.common.llvmir.instructions.exceptions;

import java.util.ArrayList;
import java.util.List;

public class InstructionCreationException extends BaseCoreException{
	
	// Binary operator creation exceptions
	public static final String BINARY_OPERATOR_ID_IS_NULL = "A binary operator ID must have a non-null value.";
	public static final String INVALID_NUMBER_OF_ARGS_FOR_BINARY_OPERATOR = "A binary operator needs two operands.";
	public static final String OPERANDS_MUST_BE_INTEGERS = "Both operands must be integers.";
	public static final String OPERANDS_MUST_BE_FLOATING_POINT_TYPES = "Both operands must be floating point types.";
	public static final String INTEGER_OPERANDS_MUST_BE_SAME_WIDTH = "Both integer operands must have the same width.";
	public static final String VECTOR_OPERANDS_MUST_BE_SIMILAR = "Both vector operands must have the same width and contain similar elements.";
	public static final String EXACT_CANNOT_BE_USED_HERE = "Exact cannot be used with ";
	public static final String WRAP_FLAG_CANNOT_BE_USED_HERE = "Signed or unsigned wrap cannot be used with ";
	public static final String EXACT_CANNOT_BE_USED_FOR_FLOATING_POINT_OPERANDS = 
		"The exact flag cannot be used with floating point operands";
	public static final String WRAP_FLAG_CANNOT_BE_USED_FOR_FLOATING_POINT_OPERANDS = 
		"Signed or unsigned wrap cannot be used with floating point operands.";
	public static final String RESULT_VALUE_CANNOT_BE_NULL =
		"The result value cannot be null.";
	public static final String RESULT_VALUE_TYPE_DIFFERS_FROM_OPERANDS_TYPE =
		"The type of the result value cannot be different from that of the operands.";
	
	// Return instruction creation exceptions
	public static final String RETURN_INST_MUST_RETURN_FIRST_CLASS_TYPE_OR_VOID = "A return instruction must return either void or first class types.";
	
	// Branch instruction creation exceptions
	public static final String CONDITION_FOR_CONDITIONAL_BRANCH_MUST_BE_OF_TYPE_I1 =
					"The condition of a conditional branch must be of type i1.";
	
	// Switch instruction creation exceptions
	public static final String SWITCH_ON_CANNOT_BE_NULL = "The switch on for a switch instruction cannot be null";
	public static final String DEFAULT_BASIC_BLOCK_CANNOT_BE_NULL = "The default target label for a switch instruction cannot be null";
	
	//Allocation instruction creation exceptions
	public static final String RESULT_TYPE_CANNOT_BE_NULL = "Result type cannot be null";
	public static final String ALIGNMENT_IS_NOT_A_POWER_OF_TWO = "Alignment is not a power of two";
	public static final String ALIGNMENT_CANNOT_BE_MORE_THAN_MAX_ALIGNMENT = "Alignment cannot be more than maximun alignment.";
	public static final String ALIGNMENT_CANNOT_BE_LESS_THAN_ONE = "Alignment cannot be less than one.";
	public static final String INVALID_TYPE_FOR_ALLOCATION = "Invalid type for allocation instruction";
	public static final String VALUE_CANNOT_BE_NULL_FOR_ALLOC_INSTR = "Value cannot be null for alloc instruction";
	
	//Allocation and Load instruction creation exceptions
	public static final String INVALID_NUMBER_OF_OPERANDS = "A unary instruction needs only one operand.";

	//Load instruction creation exceptions
	public static final String LOAD_INSTR_SHOULD_HAVE_POINTER_TO_FIRSTCLASS_TYPE_ONLY = "The only Operand of a load Instruction instruction should be a pointer to a first class type only.";
	public static final String LOAD_INSTR_CAN_ONLY_HAVE_POINTER_AS_AN_OPERAND = "Load Instruction can have only Pointer type as an argument.";
	
	//Load and Store instruction creation Exception
	public static final String EXPECTED_VALUE_TOKEN_FOR_LOAD_OR_STORE_INSTR = "Expected Value token in load/store instruction";
	public static final String FOR_ATOMIC_LOAD_OR_STORE_INSTR_POINTEE_SHOULD_BE_OF_INTEGER_TYPE = "Atomic load or store instructions should have a pointer of Interger Type only";
	public static final String IF_FOR_LOAD_OR_STORE_INSTR_POINTEE_IS_OF_INTEGER_TYPE_THEN_BITS_SHOULD_BE_POW_OF_2 = "If load or store instruction have pointee of type Integer type, its size should be a power of 2";
	public static final String NON_ATOMIC_STORE_OR_LOAD_CANNOT_BE_SINGLE_THREADED = "Non Atomic Load or Store cannot be Single Threaded";
	public static final String INVALID_ATOMIC_ORDER_FOR_ATOMIC_LOAD_OR_STORE_INSTR = "Invalid Atomic Ordering for atomic Load or Store Instruction";
	public static final String IF_FOR_LOAD_OR_STORE_INSTR_POINTEE_IS_OF_INTEGER_TYPE_THEN_BITS_SHOULD_BE_BETWEEN_8_AND_TARGET_SPECIFIC_SIZE_LIMIT = "If a load or store instruction have pointee of type Integer type, its size should be minimum 8 and maximun would be target-specific size limit";
	
	//Store instruction creation exceptions
	public static final String FIRST_ARG_OF_STORE_INSTR_CANNOT_BE_NULL = "First argument of Store Instruction cannot be null";
	public static final String SECOND_ARG_OF_STORE_INSTR_CANNOT_BE_NULL = "Second argument of Store Instruction cannot be null";
	public static final String SHOULD_BE_A_VECTOR_OF_POINTER_TYPE_ONLY = "The value should be a vector of Pointer Type only";
	public static final String STORED_VALUE_AND_POINTER_TYPE_DONOT_MATCH = "Stored value and pointer type donot match";
	public static final String THE_2ND_OPERAND_TO_A_STORE_INSTR_SHOULD_BE_POINTER_TYPE = "The second operand to a store instruction must be a pointer type";
	public static final String STORE_INSTR_MUST_STORE_VAL_OF_FIRST_CLASS_TYPE = "Store instruction must store a value of First Class Type only.";
	public static final String OPERANDS_MUST_NOT_BE_NULL_AND_NOS_OF_OPERANDS_MUST_BE_TWO = "Operand list cannot be null, and the number of operands must be 2.";
	public static final String CURRENTLY_WE_HAVE_SUPPORT_FOR_ONLY_DOUBLE_TYPE = "Currently we support only double type only";
	
	//GetElementPointer instruction creation exceptions
	public static final String FIRST_VALUE_SHOULD_BE_EITHER_A_POINTER_TYPE_OR_VECTOR_TYPE = "First value of the getElementPtr should be either a vector of Pointers or Pointer type.";
	public static final String TYPE_OF_POINTER_VALUE_RET_CANNOT_BE_NULL = "The contained type of the pointer value, which is returned by getElementPtr cannot be null.";
	public static final String INDEX_LIST_CANNOT_BE_NULL = "index list cannot be null";
	public static final String OPERANDS_LIST_CANNOT_BE_EMPTY = "Operand list cannot be empty";
	public static final String VALUE_CANNOT_BE_NULL = "Value cannot be null";
	public static final String VALUE_SHOULD_BE_OF_POINTER_TYPE = "Value should be of Pointer Type";
	public static final String TYPE_CANNOT_BE_NULL_WHILE_INSTANTIATING_GEP_INSTR = "Type cannot be null while creating/instantiating a GetElementPointer Instruction.";
	public static final String TYPE_CANNOT_BE_NULL_WHILE_INSTANTIATING_FNEG = "Type cannot be null while creating/instantiating a FNeg Instruction.";
	public static final String INVALID_INDEX_FOR_GEP_INSTR = "Invalid index for GetElementPtr Instruction";
	
	//Allocation ,Load and GetElementPtr instruction creation exceptions
	public static final String INDEX_SHOULD_BE_A_CONST_INT = "Index should be a constant Integer";
	public static final String NAME_SHOULD_BE_PROVIDED = "Name should be provided.";
	public static final String OPERANDS_CANNOT_BE_NULL = "Operands list must not be null";	

	// Cast instruction creation exceptions
	public static final String INVALID_INST_ID_FOR_CAST = "The instruction ID passed is not valid for a cast instruction";
	public static final String CANNOT_CAST = "Cannot cast ";
	public static final String INVALID_INTEGER_CAST = "Invalid integer cast";
	
	//ICmpInst and FCmpInst instruction creation exception
	public static final String INVALID_TYPE_OF_VALUES_FOR_FCMP_INSTR = "Inavalid type of value for fcmp instruction";
	public static final String INVALID_PREDICATE_VALUE_FOR__FCMP_INSTR = "Invalid Predicate Value for 'fcmp' instruction";
	public static final String INVALID_TYPE_OF_VALUES_FOR_ICMP_INSTR = "Inavalid type of value for icmp instruction";
	public static final String INVALID_PREDICATE_VALUE_FOR__ICMP_INSTR = "Invalid Predicate Value for 'icmp' instruction";
	public static final String BOTH_LHS_RHS_TYPE_SHOULD_BE_EQUAL_CMP_INSTR = "LHS and RHS types cannot be different from each other in compare Instruction";
	public static final String CONDITION_CODE_CANNOT_BE_NULL = "Conditional Code cannot be null";
	public static final String LHS_AND_RHS_VALUE_CANNOT_BE_NULL = "LHS and RHS value cannot be null for Comparision instruction";
	
	//call instruction creation exception
	public static final String FUNC_VALUE_SHOULD_HAVE_A_NAME = "Function should have a name";
	public static final String ARGS_SHOULD_NOT_BE_NULL = "Arguments should be provided";
	public static final String FUNC_VALUE_CANNOT_BE_NULL = "Function Value cannot be null";
	public static final String IF_TYPE_IS_PTR_TO_FUNC_CONTAINED_TYPE_SHOULD_BE_FUNC_TYPE = "If type is pointer type then the contained type should be function type";
	public static final String INVALID_TYPE_FOR_CALL_INSTR_SHOULD_BE_EITHER_PTR_TO_FUNC_OR_FUNC_TYPE = "Invalid type for call instruction, should be either pointer to function or function type";
	public static final String NOS_OF_ARGS_CANNOT_BE_LESS_THAN_NOS_OF_PARAMS = "Number of Arguments cannot be less than number of parameters";
	public static final String NOS_OF_ARGS_SHOULD_BE_EQUAL_TO_NOS_OF_PARAMS = "Number of arguments should be same as number of parameters";
	public static final String FUNC_PARAM_TYPE_CANNOT_BE_DIFFERENT_THAN_ARG_TYPE = "Function parameter type cannot be different from Argument type";
	
	//extract element instruction exceptions
	public static final String SECOND_ARG_OF_EXTRACT_ELEMENT_INST_INSTR_CANNOT_BE_NULL = "Second argument of extract element instruction cannot be null";
	public static final String FIRST_ARG_OF_EXTRACT_ELEMENT_INST_INSTR_CANNOT_BE_NULL = "First argument of extract element instruction cannot be null";
	
	//extract value instruction exceptions
	public static final String AGG_VALUE_NAME_CANNOT_BE_NULL_OR_EMPTY = "Aggregate value should have a name";
	public static final String INVALID_INDEX_FOR_EXTRACT_VAL_INSTR = "Invalid index for extractValue Instruction";
	public static final String AGG_VALUE_CANNOT_BE_NULL = "Aggregate value cannot be null";
	public static final String VALUE_SHOULD_BE_OF_AGGREGATE_TYPE = "Value should be of aggregate type";
	public static final String EXPECTED_AGG_TYPE = "Expected Aggregate Type";
	public static final String ATLEAST_ONE_INDEX_SHOULD_BE_PROVIDED = "Atleast one index should be provided";
	
	//insert value instruction exceptions
	public static final String AGG_VALUE_MUST_HAVE_A_NAME = "Aggregate value must have a name";
	public static final String SECOND_OPERAND_MUST_BE_OF_FIRST_CLASS_TYPE = "Second operand must be of first class type";
	public static final String VALUE_TO_BE_INSERTED_CANNOT_BE_NULL = "Value to be inserted cannot be null";
	public static final String AGGREGATE_VALUE_CANNOT_BE_NULL = "Aggregate value cannot be null";
	public static final String TYPE_NAME_AND_VALUE_NAME_MUST_BE_EQUAL = "Type name and value name must be equal for aggregate operand";
	public static final String INDEX_IS_OUT_OF_BOUNDS = "Index out of bounds";
	public static final String INSERTED_VALUE_TYPE_MUST_HAVE_A_VALUE_NAME = "The value to be inserted must have a value name";
	public static final String INSERTED_VALUE_TYPE_MUST_MATCH_WITH_THE_TYPE_OF_VALUE_BEING_ASSIGNED = "Inserted value type must match the type of value being assigned";
	public static final String FIRST_OPERAND_TO_A_INSERTVALUE_INSTR_MUST_BE_A_AGGR_TYPE = "First operand of insert value instruction must be of aggregate type";
	
	// Function Definition instruction exceptions
	public static final String LIST_OF_PARAMETERS_CANNOT_BE_NULL = "Parameter list cannot be null.";
	public static final String RETURN_TYPE_MUST_BE_PROVIDED = "Return type must be provided";
	
	// Selection instruction related exceptions
	public static final String SELECTION_VALUES_CANNOT_BE_NULL = "Selection values cannot be null.";
	public static final String CONDITION_CANNOT_BE_NULL_FOR_SELECT_INSTR = "The condition for the select statement cannot be null.";
	public static final String INVALID_CONDITION_TYPE_FOR_SELECT_INSTR = "The condition for the select statement must be of type i1";
	public static final String TYPE_MISMATCH_IN_SELECTION_INSTR = "The arguments for the select instruction must be of the same type.";
	
	// UnreachableInstr related exception
	public static final String PARENT_BASIC_BLOCK_CANNOT_BE_NULL = "Each Instruction must have a parent Basic Block";
	
	private static List<String> ERROR_MESSAGES = new ArrayList<String>(); 
	
    static {
    	// Add error messages related to creation of binary operator instructions
		ERROR_MESSAGES.add(BINARY_OPERATOR_ID_IS_NULL);
		ERROR_MESSAGES.add(INVALID_NUMBER_OF_ARGS_FOR_BINARY_OPERATOR);
		ERROR_MESSAGES.add(OPERANDS_MUST_BE_INTEGERS);
		ERROR_MESSAGES.add(OPERANDS_MUST_BE_FLOATING_POINT_TYPES);
		ERROR_MESSAGES.add(INTEGER_OPERANDS_MUST_BE_SAME_WIDTH);
		ERROR_MESSAGES.add(VECTOR_OPERANDS_MUST_BE_SIMILAR);
		ERROR_MESSAGES.add(EXACT_CANNOT_BE_USED_HERE);
		ERROR_MESSAGES.add(WRAP_FLAG_CANNOT_BE_USED_HERE);
		ERROR_MESSAGES.add(EXACT_CANNOT_BE_USED_FOR_FLOATING_POINT_OPERANDS);
		ERROR_MESSAGES.add(WRAP_FLAG_CANNOT_BE_USED_FOR_FLOATING_POINT_OPERANDS);
		ERROR_MESSAGES.add(RESULT_VALUE_CANNOT_BE_NULL);
		ERROR_MESSAGES.add(RESULT_VALUE_TYPE_DIFFERS_FROM_OPERANDS_TYPE);
		
		// Add error messages related to creation of return instruction
		ERROR_MESSAGES.add(RETURN_INST_MUST_RETURN_FIRST_CLASS_TYPE_OR_VOID);
		
		// Add error messages related to creation of branch instructions
		ERROR_MESSAGES.add(CONDITION_FOR_CONDITIONAL_BRANCH_MUST_BE_OF_TYPE_I1);
		
		// Add error messages related to creation of switch instructions
		ERROR_MESSAGES.add(SWITCH_ON_CANNOT_BE_NULL);
		ERROR_MESSAGES.add(DEFAULT_BASIC_BLOCK_CANNOT_BE_NULL);
		
		// Add error messages related to creation of cast instructions
		ERROR_MESSAGES.add(INVALID_INST_ID_FOR_CAST);
		ERROR_MESSAGES.add(CANNOT_CAST);
		
		
		// Add error messages related to creation of alloca instructions
		ERROR_MESSAGES.add(INVALID_NUMBER_OF_OPERANDS);
		ERROR_MESSAGES.add(NAME_SHOULD_BE_PROVIDED);
		ERROR_MESSAGES.add(ALIGNMENT_IS_NOT_A_POWER_OF_TWO);
		ERROR_MESSAGES.add(ALIGNMENT_CANNOT_BE_MORE_THAN_MAX_ALIGNMENT);
		ERROR_MESSAGES.add(ALIGNMENT_CANNOT_BE_LESS_THAN_ONE);
		ERROR_MESSAGES.add(INVALID_TYPE_FOR_ALLOCATION);
		ERROR_MESSAGES.add(VALUE_CANNOT_BE_NULL_FOR_ALLOC_INSTR);
		ERROR_MESSAGES.add(OPERANDS_CANNOT_BE_NULL);
		ERROR_MESSAGES.add(RESULT_TYPE_CANNOT_BE_NULL);
		
		// Add error messages related to creation of load instructions
		ERROR_MESSAGES.add(LOAD_INSTR_CAN_ONLY_HAVE_POINTER_AS_AN_OPERAND);
		ERROR_MESSAGES.add(LOAD_INSTR_SHOULD_HAVE_POINTER_TO_FIRSTCLASS_TYPE_ONLY);
		ERROR_MESSAGES.add(INVALID_ATOMIC_ORDER_FOR_ATOMIC_LOAD_OR_STORE_INSTR);
		ERROR_MESSAGES.add(NON_ATOMIC_STORE_OR_LOAD_CANNOT_BE_SINGLE_THREADED);
		ERROR_MESSAGES.add(IF_FOR_LOAD_OR_STORE_INSTR_POINTEE_IS_OF_INTEGER_TYPE_THEN_BITS_SHOULD_BE_POW_OF_2);
		ERROR_MESSAGES.add(IF_FOR_LOAD_OR_STORE_INSTR_POINTEE_IS_OF_INTEGER_TYPE_THEN_BITS_SHOULD_BE_BETWEEN_8_AND_TARGET_SPECIFIC_SIZE_LIMIT);
		ERROR_MESSAGES.add(EXPECTED_VALUE_TOKEN_FOR_LOAD_OR_STORE_INSTR);
		ERROR_MESSAGES.add(FOR_ATOMIC_LOAD_OR_STORE_INSTR_POINTEE_SHOULD_BE_OF_INTEGER_TYPE);
		
		// Add error messages related to creation of store instructions
		ERROR_MESSAGES.add(OPERANDS_MUST_NOT_BE_NULL_AND_NOS_OF_OPERANDS_MUST_BE_TWO);
		ERROR_MESSAGES.add(STORE_INSTR_MUST_STORE_VAL_OF_FIRST_CLASS_TYPE);
		ERROR_MESSAGES.add(THE_2ND_OPERAND_TO_A_STORE_INSTR_SHOULD_BE_POINTER_TYPE);
		ERROR_MESSAGES.add(STORED_VALUE_AND_POINTER_TYPE_DONOT_MATCH);
		ERROR_MESSAGES.add(CURRENTLY_WE_HAVE_SUPPORT_FOR_ONLY_DOUBLE_TYPE);
		ERROR_MESSAGES.add(FIRST_ARG_OF_STORE_INSTR_CANNOT_BE_NULL);
		ERROR_MESSAGES.add(SECOND_ARG_OF_STORE_INSTR_CANNOT_BE_NULL);
		
		// Add error messages related to creation of GetElementPointer instructions
		ERROR_MESSAGES.add(TYPE_CANNOT_BE_NULL_WHILE_INSTANTIATING_GEP_INSTR);
		ERROR_MESSAGES.add(VALUE_CANNOT_BE_NULL);
		ERROR_MESSAGES.add(VALUE_SHOULD_BE_OF_POINTER_TYPE);
		ERROR_MESSAGES.add(OPERANDS_LIST_CANNOT_BE_EMPTY);
		ERROR_MESSAGES.add(INDEX_LIST_CANNOT_BE_NULL);
		ERROR_MESSAGES.add(INDEX_SHOULD_BE_A_CONST_INT);
		ERROR_MESSAGES.add(SHOULD_BE_A_VECTOR_OF_POINTER_TYPE_ONLY);
		ERROR_MESSAGES.add(INVALID_INDEX_FOR_GEP_INSTR);
		ERROR_MESSAGES.add(TYPE_OF_POINTER_VALUE_RET_CANNOT_BE_NULL);
		ERROR_MESSAGES.add(FIRST_VALUE_SHOULD_BE_EITHER_A_POINTER_TYPE_OR_VECTOR_TYPE);
		
		// Add error messages related to creation of ICmpInst and FCmpInst instructions
		ERROR_MESSAGES.add(INVALID_PREDICATE_VALUE_FOR__ICMP_INSTR);
		ERROR_MESSAGES.add(LHS_AND_RHS_VALUE_CANNOT_BE_NULL);
		ERROR_MESSAGES.add(CONDITION_CODE_CANNOT_BE_NULL);
		ERROR_MESSAGES.add(BOTH_LHS_RHS_TYPE_SHOULD_BE_EQUAL_CMP_INSTR);
		ERROR_MESSAGES.add(INVALID_TYPE_OF_VALUES_FOR_ICMP_INSTR);
		ERROR_MESSAGES.add(INVALID_TYPE_OF_VALUES_FOR_FCMP_INSTR);
		ERROR_MESSAGES.add(INVALID_PREDICATE_VALUE_FOR__FCMP_INSTR);
		
		// Add error messages related to creation of call instructions
		ERROR_MESSAGES.add(ARGS_SHOULD_NOT_BE_NULL);
		ERROR_MESSAGES.add(INVALID_TYPE_FOR_CALL_INSTR_SHOULD_BE_EITHER_PTR_TO_FUNC_OR_FUNC_TYPE);
		ERROR_MESSAGES.add(IF_TYPE_IS_PTR_TO_FUNC_CONTAINED_TYPE_SHOULD_BE_FUNC_TYPE);
		ERROR_MESSAGES.add(NOS_OF_ARGS_CANNOT_BE_LESS_THAN_NOS_OF_PARAMS);
		ERROR_MESSAGES.add(NOS_OF_ARGS_SHOULD_BE_EQUAL_TO_NOS_OF_PARAMS);
		ERROR_MESSAGES.add(FUNC_PARAM_TYPE_CANNOT_BE_DIFFERENT_THAN_ARG_TYPE);
		ERROR_MESSAGES.add(FUNC_VALUE_CANNOT_BE_NULL);
		ERROR_MESSAGES.add(FUNC_VALUE_SHOULD_HAVE_A_NAME);
		
		// Add error messages related to creation of extract element instructions
		ERROR_MESSAGES.add(SECOND_ARG_OF_EXTRACT_ELEMENT_INST_INSTR_CANNOT_BE_NULL);
		ERROR_MESSAGES.add(FIRST_ARG_OF_EXTRACT_ELEMENT_INST_INSTR_CANNOT_BE_NULL);
		
		// Add error messages related to creation of extractValue instructions
		ERROR_MESSAGES.add(EXPECTED_AGG_TYPE);
		ERROR_MESSAGES.add(AGG_VALUE_CANNOT_BE_NULL);
		ERROR_MESSAGES.add(VALUE_SHOULD_BE_OF_AGGREGATE_TYPE);
		ERROR_MESSAGES.add(INVALID_INDEX_FOR_EXTRACT_VAL_INSTR);
		ERROR_MESSAGES.add(AGG_VALUE_NAME_CANNOT_BE_NULL_OR_EMPTY);
		ERROR_MESSAGES.add(ATLEAST_ONE_INDEX_SHOULD_BE_PROVIDED);
		
		// Add error messages related to creation of inserttValue instructions
		ERROR_MESSAGES.add(AGGREGATE_VALUE_CANNOT_BE_NULL);
		ERROR_MESSAGES.add(TYPE_NAME_AND_VALUE_NAME_MUST_BE_EQUAL);
		ERROR_MESSAGES.add(INDEX_IS_OUT_OF_BOUNDS);
		ERROR_MESSAGES.add(FIRST_OPERAND_TO_A_INSERTVALUE_INSTR_MUST_BE_A_AGGR_TYPE);
		ERROR_MESSAGES.add(INSERTED_VALUE_TYPE_MUST_MATCH_WITH_THE_TYPE_OF_VALUE_BEING_ASSIGNED);
		ERROR_MESSAGES.add(INSERTED_VALUE_TYPE_MUST_HAVE_A_VALUE_NAME);
		ERROR_MESSAGES.add(SECOND_OPERAND_MUST_BE_OF_FIRST_CLASS_TYPE);
		ERROR_MESSAGES.add(VALUE_TO_BE_INSERTED_CANNOT_BE_NULL);
		ERROR_MESSAGES.add(AGG_VALUE_MUST_HAVE_A_NAME);
		
		// Add error messages related to creation of function definition
		ERROR_MESSAGES.add(LIST_OF_PARAMETERS_CANNOT_BE_NULL);
		ERROR_MESSAGES.add(RETURN_TYPE_MUST_BE_PROVIDED);
		
		// Add error messages related to creation of selection instructions
		ERROR_MESSAGES.add(SELECTION_VALUES_CANNOT_BE_NULL);
		ERROR_MESSAGES.add(CONDITION_CANNOT_BE_NULL_FOR_SELECT_INSTR);
		ERROR_MESSAGES.add(INVALID_CONDITION_TYPE_FOR_SELECT_INSTR);
		ERROR_MESSAGES.add(TYPE_MISMATCH_IN_SELECTION_INSTR);
		
		// Add common Error Messages
		ERROR_MESSAGES.add(PARENT_BASIC_BLOCK_CANNOT_BE_NULL);
	}
    
    public InstructionCreationException(String ... messageParts){
    	super(ERROR_MESSAGES, messageParts);
    }
}

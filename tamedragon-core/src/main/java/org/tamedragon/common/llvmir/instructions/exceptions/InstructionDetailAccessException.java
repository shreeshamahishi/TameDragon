package org.tamedragon.common.llvmir.instructions.exceptions;

import java.util.ArrayList;
import java.util.List;

public class InstructionDetailAccessException extends BaseCoreException{

	// Error messages related to the return instruction
	public static final String RETURN_INST_CANNOT_HAVE_SUCCESSORS = "A return instruction cannot have successors.";
	
	// Error messages related to the branch instructions
	public static final String BRANCH_INST_CANNOT_GET_CONDITION_FOR_UNCONDITIONAL_BRANCH =
				"Cannot get condition of an unconditional branch.";
	
	// Error messages related to the switch instruction
	public static final String INVALID_INDEX_FOR_CASE = "Invalid index to access a case";
	
	// Generic error messages for all cast instructions
	public static final String  ONLY_FIRST_CLASS_TYPES_CASTABLE = "Only first class types are castable.";
	public static final String  CANNOT_CAST_VEC_TO_INT_OF_DIFF_WIDTH = 
						"Cannot cast a vector to an integer of different width";
	public static final String  CANNOT_CAST_VEC_TO_FP_OF_DIFF_WIDTH = 
						"Cannot cast a vector to a floating point of different width";
	public static final String  CANNOT_CAST_PTR_OR_NON_FIRST_CLASS_TO_FP =
						"Cannot cast pointer or non-first class type to floating point type.";
	public static final String  CANNOT_CAST_PTR_TO_OTHER_THAN_PTR_OR_INT =
						"Cannot pointer to other than pointer or int.";
	public static final String  CANNOT_CAST_WRONG_WIDTH_VEC_X86_MMX = 
						"Casting vector of wrong width to X86_MMX";
	public static final String  INVALID_CAST_TO_X866_MMX = "Illegal cast to X86_MMX";
	
	// Error messages related to phi node instruction
	public static final String  PHI_NODE_DOES_NOT_HAVE_OPERAND = 
						"The value is not part of the phi node instruction.";
	
	// Error messages related to getElementPtr instruction
	public static final String INVALID_INDEX_FOR_GEP_INSTR = "Invalid index for GetElementPtr Instruction";
	
	// Error messages related to function instruction
	public static final String PARAM_DOESNT_HAS_THIS_ATTR = "Parameter doesn't has this attribute";
	
	private static List<String> ERROR_MESSAGES = new ArrayList<String>(); 
	
    static {
    	
		// Add error messages related to return instruction accesses.
		ERROR_MESSAGES.add(RETURN_INST_CANNOT_HAVE_SUCCESSORS);
		
		// Add error messages related to branch instruction accesses.
		ERROR_MESSAGES.add(BRANCH_INST_CANNOT_GET_CONDITION_FOR_UNCONDITIONAL_BRANCH);
		
		// Add error messages related to switch instruction accesses.
		ERROR_MESSAGES.add(INVALID_INDEX_FOR_CASE);
		
		// Add error messages related to cast instruction accesses.
		ERROR_MESSAGES.add(ONLY_FIRST_CLASS_TYPES_CASTABLE);
		ERROR_MESSAGES.add(CANNOT_CAST_VEC_TO_INT_OF_DIFF_WIDTH);
		ERROR_MESSAGES.add(CANNOT_CAST_VEC_TO_FP_OF_DIFF_WIDTH);
		ERROR_MESSAGES.add(CANNOT_CAST_PTR_OR_NON_FIRST_CLASS_TO_FP);
		ERROR_MESSAGES.add(CANNOT_CAST_PTR_TO_OTHER_THAN_PTR_OR_INT);
		ERROR_MESSAGES.add(CANNOT_CAST_WRONG_WIDTH_VEC_X86_MMX);
		ERROR_MESSAGES.add(INVALID_CAST_TO_X866_MMX);
		
		// Add error messages related to phi node instruction.
		ERROR_MESSAGES.add(PHI_NODE_DOES_NOT_HAVE_OPERAND);
		
		// Add error messages related to getElementPtr instruction.
		ERROR_MESSAGES.add(INVALID_INDEX_FOR_GEP_INSTR);
		
		// Add error messages related to function instruction.
		ERROR_MESSAGES.add(PARAM_DOESNT_HAS_THIS_ATTR);
	}
    
    public InstructionDetailAccessException(String ... messageParts){
    	super(ERROR_MESSAGES, messageParts);
    }
    
    public InstructionDetailAccessException(Exception cause, String ... messageParts){
    	super(ERROR_MESSAGES, cause, messageParts);
    }
}

package org.tamedragon.compilers.clang;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

public class ErrorHandler {
	
	public static final String W_SETTINGS_NO_PROPERTIES_FOUND = "Warning: No compiler properties found, using default.";
	
	// ERRORS RELATED TO PREPROCESSOR
	public static final String E_PREPROCESSOR_REDIFINITION_NOT_IDENTICAL = " already defined with a non-identical value.";
	public static final String E_DOUBLE_HASH_APPEARING_AT_THE_BEGINNING_OF_REPLACEMENT_LIST = "'##' cannot appear at start of macro expansion";
	public static final String E_DOUBLE_HASH_APPEARING_AT_THE_END_OF_REPLACEMENT_LIST = "'##' cannot appear at end of macro expansion";
	
	// WARNINGS RELATED TO PREPROCESSOR
	public static final String W_PREPROCESSOR_REDIFINITION_NOT_IDENTICAL = " already defined with a non-identical value.";
	
	// ERRORS RELATED TO SEMANTIC ANALYSIS
	public static final String OK = "OK";
	public static final String ERROR = "Error: ";
	public static final String WARNING = "Warning: ";
	public static final String E_MORE_THAN_ONE_TYPE = "Error: Variable declaration has more than one type.";
	public static final String E_NO_TYPE_SPECIFIED = "Error: Variable declaration does not have any type specified.";
	public static final String E_TOO_MANY_QUALIFIERS = "Error: Too many qualifiers specified.";
	public static final String E_REPEATED_QUALIFIER = "Error: Qualifier specified more than once.";
	public static final String E_DECL_FUNC_RETURNING_FUNC = " is defined as a function returning a function.";
	public static final String E_DECL_FUNC_RETURNING_ARRAY  = " is defined as a function returning an array.";
	public static final String E_ARRAY_OF_FUNCTIONS  = " is defined as an array of functions.";
	public static final String E_LAST_SPECIFIER_MUST_BE_TYPE = "Error: The last specifier for a variable is not a type.";	
	public static final String E_VARIABLE_DEFINED_LIKE_FUNCTION = "Error: A variable cannot be declared like a function.";
	public static final String E_FUNCTION_IN_PARAMETER_DECL = "Error: A function cannot be declared in a parameter list.";
	public static final String E_FUNCTION_CANNOT_HAVE_INITIALIZER = "Error: A function cannot have initialization.";
	public static final String E_VARIABLE_ALREADY_DEFINED = " is already defined as a variable.";
	public static final String E_VARIABLE_ALREADY_INITIALIZED = " is already initialized.";	
	public static final String E_FUNCTION_ALREADY_DEFINED = " is already defined as a function.";
	public static final String E_NOT_DEFINED = " is not defined.";
	public static final String E_TYPE_NOT_DEFINED = " type is not defined.";
	public static final String E_SWITCH_EXPR_MUST_BE_CONST = "Error: The expression in the switch statement must evaluate to a constant integer value.";
	public static final String E_MORE_THAN_ONE_STORAGE_SPEC = "Error: A declaration cannot have more than one storage class specified.";
	public static final String E_FUNCTION_SIGNATURE_MISMATCH = ": Function signature does not match that of the declaration.";
	public static final String E_CONTINUE_IN_WRONG_LOCATION = "Error: The \"continue\" statement cannot be used here.";
	public static final String E_BREAK_IN_WRONG_LOCATION = "Error: The \"break\" statement cannot be used here.";
	public static final String E_VOID_TYPE_IN_LOOP_CONDITION = "Error: Loop condition cannot be of void type."; 
	public static final String E_PARAMETERS_PASSED_TO_FUNC_ACCEPTING_VOID = " parameters passed to function that does not expect any.";
	public static final String E_NO_PARAMETERS_PASSED_TO_FUNC_ACCEPTING_NON_VOID_ARGS = " no parameters passed to function.";
	public static final String E_WRONG_NUMBER_OF_PARAMETERS_PASSED_TO_FUNC = " wrong number of parameters passed to function.";
	public static final String E_CANNOT_HAVE_FUNCTION_CALL_HERE = "Error: Another function call cannot be made here";
	public static final String E_WRONG_INDIRECTION = "Error: The indirection operator cannot be used on a non-pointer";
	public static final String E_INVALID_OPERANDS = "Error: Invalid Operands to Binary Expression";
	public static final String E_ASSIGNING_POINTER_TO_A_FLOATING_POINT_TYPE = "Error: Cannot convert a pointer type to a floating point type";
	public static final String E_ASSIGNING_A_FLOATING_POINT_TYPE_TO_A_POINTER = "Error: Cannot convert a floating point type to a pointer type.";
	public static final String E_ADDRESS_OF_A_CONSTANT = "Error: Cannot take the address of a constant.";
	public static final String E_ADDRESS_OF_A_VAR_IN_REGISTER = "Error: Cannot take the address of a variable marked as register.";
	public static final String E_ADDRESS_OF_AN_EXPRESSION = "Error: Cannot take the address of an expression.";
	public static final String E_LEFT_SIDE_IS_NOT_LVALUE = "Error: The left side of the assignment must be a lvalue.";
	public static final String E_LEFT_SIDE_IS_CONSTANT = "Error: The left side of the assignment is a constant read-only value.";	
	public static final String E_LEFT_SIDE_IS_VOID = "Error: Void value in left side of assignment.";
	public static final String E_RIGHT_SIDE_IS_VOID = "Error: Void value in right side of assignment.";
	public static final String E_EXCESS_INITIALIZERS_IN_STRUCT_INIT = "Error: Excess initializers specified in struct initialization values.";
	public static final String E_ARRAY_SIZE_SPECIFIER_IS_INVALID = "Error: The array size must be a constant integer or a constant expression that evaluates to an integer.";
	public static final String E_INVALID_DIGIT_IN_DECIMAL_CONSTANT = "Error:  Invalid digit in decimal constant";
	public static final String E_ARRAY_SIZE_NOT_SPECIFIED = "Error: The size of the array must be specified here.";
	public static final String E_CANNOT_DECREMENT_A_CONSTANT = "Error: A constant cannot be decremented.";
	public static final String E_CANNOT_INCREMENT_A_CONSTANT = "Error: A constant cannot be incremented.";
	public static final String E_CANNOT_ACCESS_ELEMENTS_OF_A_NON_ARRAY = "Error: Cannot use subscripts on objects that are neither arrays nor pointers.";
	public static final String E_DIVIDE_ASSIGN_ON_POINTER = "Error: Invalid binay operator \"/=\" for a pointer";
	public static final String E_MULTIPLY_ASSIGN_ON_POINTER= "Error: Invalid binay operator \"*=\" for a pointer";
	public static final String E_MODULO_ASSIGN_ON_POINTER = "Error: Invalid binay operator \"%=\" for a pointer";
	public static final String E_INVALID_OPERATOR_ON_POINTERS = " is an invalid operator on pointers.";
	public static final String E_SUBTRACTING_INCOMPATIBLE_POINTERS = "Error: Attempt to subtract incompatible pointer types.";
	public static final String E_INVALID_OPERATOR_ON_POINTER_AND_INTEGER = " is an invalid operator on a pointer and an integer.";
	public static final String E_INVALID_INITIALIZATION = "Error: Invalid initialization.";
	public static final String E_MISSING_SUBSCRIPT_IN_ARRAY_DECL = "Error: Missing subscript in array declaration.";
	public static final String E_EXCESS_INITIALIZERS_IN_ARRAY_INIT = "Error: Excess initializers specified in array initialization values.";
	public static final String E_EXCESS_INITIALIZERS_IN_UNION_INIT = "Error: Excess initializers specified in union initialization values.";
	public static final String E_ARRAY_SUBSCRIPT_NOT_INTEGER = "Error: Array subscript is not an integer.";
	public static final String E_INCORRECT_FORMAL_PARAMETER = "Error: Incorrect formal parameter.";
	public static final String E_ASSIGNMENT_OF_INCOMPATIBLE_TYPES = "Error: Assignment of incompatible types.";
	public static final String E_DUPLICATE_MEMBER_NAME = " Duplicate member variable";
	public static final String E_FUNCTION_AS_STRUCT_MEMBER = " Cannot have a function declaration as the member of a structure or union";
	public static final String  E_STRUCT_MEMBER_HAS_UNKNOWN_TYPE = " Struct member has unknown type.";
	public static final String E_STRUCT_ALREADY_DEFINED = " is already defined as a struct type.";
	public static final String E_ENUM_ALREADY_DEFINED = " is already defined as a enum type.";
	public static final String E_EMPTY_INITIALIZER_IN_STRUCT_INIT = "Error: No initializers specified in struct initialization";
	public static final String E_INVALID_INITIALIZATION_OF_MEMBER = "Error: Invalid initialization of structure member";
	public static final String E_STRUCT_NOT_DEFINED = " Structure or union not defined.";
	public static final String E_LEFT_SIDE_NOT_STRUCT_OR_UNION = " Left side is neither a structure nor a union.";
	public static final String E_LEFT_SIDE_NOT_POINTER_TO_STRUCT_OR_UNION = "Error: Left side is neither a pointer to a structure nor pointer to a union.";
	public static final String E_NOT_MEMBER_OF_STRUCT_OR_UNION = " is not a member of the structure or union.";
	public static final String E_INVALID_OPERATOR_ON_STRUCT_UNION = " not a valid operator on a structure or union.";
	public static final String E_TYPE_ALREADY_DEFINED = " is already defined as a type.";
	public static final String E_INVALID_TYPE_DECL = "Invalid type declaration";
	public static final String E_TYPE_ALREADY_DEFINED_WITH_CONFLICTING_TYPE = " conflict with type defined earlier.";
	public static final String E_TYPE_CANNOT_HAVE_INITIALIZER = " type cannot be initialized.";
	public static final String E_INVALID_TYPEDEF_DECLARATION = "Invalid typedef declaration.";
	public static final String E_LABEL_REDEFINED = " Label is already defined";
	public static final String E_NO_LABEL = " No such label found";	
	public static final String E_CASE_USED_OUTSIDE_SWITCH = "Error: Case label is used outside a switch block.";	
	public static final String E_DEFAULT_USED_OUTSIDE_SWITCH = "Error: Default label is used outside a switch block.";	
	public static final String E_CASE_EXPR_MUST_BE_CONST = "Error: Case expression does not evaluate to a constant";	
	public static final String E_UNARY_NEGATION_OP_ON_STRUCT = "Error: The unary negation operation '!' cannot be used on a struct";
	public static final String E_ONES_COMPLEMENT_ON_STRUCT = "Error: The unary one's complement operation '~' cannot be used on a struct.";
	public static final String E_ONES_COMPLEMENT_ON_INTEGERS_ONLY = "Error: The unary one's complement operation '~' can only be used on a integer types.";
	public static final String E_NEGATIVE_OPERATOR_CANNOT_BE_USED = "Error: The '-' operator cannot be used on this operand.";
	public static final String E_INCREMENT_OPR_NEEDS_LVALUE = "Error: The increment operator can only be applied on an lvalue.";
	public static final String E_INVALID_OPERAND_FOR_INCREMENT = "Error: Invalid operand for the increment operator.";
	public static final String E_DECREMENT_OPR_NEEDS_LVALUE = "Error: The decrement operator can only be applied on an lvalue.";
	public static final String E_INVALID_OPERAND_FOR_DECREMENT = "Error: Invalid operand for the decrement operator.";
	public static final String E_ENUM_INIT_VAL_NOT_INTEGER = "Error: Enum initialization is not an integral value.";	
	public static final String E_ENUMERATOR_ALREADY_DEFINED = " enumerator already defined";
	public static final String E_ENUM_TYPE_ALREADY_DEFINED = " enum type already defined";	
	public static final String E_ASSIGNING_STRUCT_OR_UNION_TO_AN_INTEGER_TYPE = "Error: Cannot convert a struct or union type to an integer type";
	public static final String E_ASSIGNING_STRUCT_OR_UNION_TO_A_FLOATING_POINT_TYPE = "Error: Cannot convert a struct or union type to a floating point type";
	public static final String E_ASSIGNING_STRUCT_OR_UNION_TO_A_DOUBLE_TYPE = "Error: Cannot convert a struct or union type to a double type";
	public static final String E_CONST_TOO_LARGE_FOR_FLOAT = "Error: The constant is too large to be represented as a float.";
	public static final String E_CONST_TOO_LARGE_FOR_DOUBLE = "Error: The constant is too large to be represented as a double.";
	public static final String E_VOID_VALUE_NOT_IGNORED = "Error: void value not ignored as it ought to be";
	public static final String E_INIT_NOT_CONST_EXPR = "Error: initializer element is not a compile-time constant";
	public static final String E_LEFTSHIFT_COUNT_GREATER_THAN_SIZE_OF_TYPE = "Left Shift count >= the width of the type";
	public static final String DECLARATION_SPECIFIER_IS_MANDATORY = "Declaration Specifier is necessary.";
	
	
	// Warnings about narrowing conversions (NOT strict compilation)
	public static final String W_FLOAT_TO_CHAR_NARROWING = "Warning: Narrowing conversion from a float to a char.";
	public static final String W_SHORT_TO_CHAR_NARROWING = "Warning: Narrowing conversion from a short to a char.";
	public static final String W_DOUBLE_TO_CHAR_NARROWING = "Warning: Narrowing conversion from a double to a char.";
	public static final String W_LONG_TO_CHAR_NARROWING = "Warning: Narrowing conversion from a long int to a char.";
	public static final String W_FLOAT_TO_SHORT_NARROWING = "Warning: Narrowing conversion from a float to a short.";
	public static final String W_DOUBLE_TO_SHORT_NARROWING = "Warning: Narrowing conversion from a double to a short.";
	public static final String W_LONG_TO_SHORT_NARROWING = "Warning: Narrowing conversion from a long int to a short.";
	public static final String W_DOUBLE_TO_INT_NARROWING = "Warning: Narrowing conversion from a double to an int.";
	public static final String W_DOUBLE_TO_LONG_NARROWING = "Warning: Narrowing conversion from a double to a long.";
	public static final String W_DOUBLE_TO_FLOAT_NARROWING = "Warning: Narrowing conversion from a double to a float.";
	public static final String W_FLOAT_TO_INT_NARROWING = "Warning: Narrowing conversion from a float to an int.";
	public static final String W_FLOAT_TO_LONG_NARROWING = "Warning: Narrowing conversion from a float to a long int.";
	public static final String W_INT_TO_FLOAT_NARROWING = "Warning: Conversion from an int to a float.";
	public static final String W_LONG_TO_FLOAT_NARROWING = "Warning: Conversion from a long int to a float.";
	public static final String W_CONST_LITERAL_OVERFLOW = "Warning: Overflow converting from constant literal";
	public static final String W_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE = "Warning: Attempt to convert a pointer type to an integer type";
	public static final String W_ASSIGNING_AN_INTEGER_TYPE_TO_A_POINTER = "Warning: Attempt to convert an integer type to a pointer type.";
	public static final String W_ASSIGNING_A_STRING_TO_AN_INTEGER_TYPE = "Warning: Attempt to convert a string to an integer type.";
	public static final String W_ASSIGNING_STRING_TO_POINTER_TO_INT = "Warning: Attempt to convert a string to a pointer to integer.";
	public static final String W_ASSIGNING_STRING_TO_POINTER_TO_SHORT = "Warning: Attempt to convert a string to a pointer to a short int.";
	public static final String W_ASSIGNING_STRING_TO_POINTER_TO_LONG = "Warning: Attempt to convert a string to a pointer to a long int.";
	public static final String W_ASSIGNING_STRING_TO_POINTER_TO_FLOAT = "Warning: Attempt to convert a string to a pointer to a float.";
	public static final String W_ASSIGNING_STRING_TO_POINTER_TO_DOUBLE = "Warning: Attempt to convert a string to a pointer to a double.";
	public static final String W_ASSIGNING_STRING_TO_POINTER_TO_STRUCT = "Warning: Attempt to convert a string to a pointer to a struct or union.";
		
	// Errors about narrowing conversions (strict compilation)
	public static final String E_FLOAT_TO_CHAR_NARROWING = "Error: Cannot convert from a float to a char.";
	public static final String E_SHORT_TO_CHAR_NARROWING = "Error: Cannot convert from a short to a char.";
	public static final String E_DOUBLE_TO_CHAR_NARROWING = "Error: Cannot convert from a double to a char.";
	public static final String E_LONG_TO_CHAR_NARROWING = "Error: Cannot convert from a long int to a char.";
	public static final String E_FLOAT_TO_SHORT_NARROWING = "Error: Cannot convert from a float to a short.";
	public static final String E_DOUBLE_TO_SHORT_NARROWING = "Error: Cannot convert from a double to a short.";
	public static final String E_LONG_TO_SHORT_NARROWING = "Error: Cannot convert from a long int to a short.";
	public static final String E_DOUBLE_TO_INT_NARROWING = "Error: Cannot convert from a double to an int.";
	public static final String E_FLOAT_TO_INT_NARROWING = "Error: Cannot convert from a float to an int.";
	public static final String E_DOUBLE_TO_LONG_NARROWING = "Error: Cannot convert from a double to a long.";
	public static final String E_FLOAT_TO_LONG_NARROWING = "Error: Cannot convert from a float to a long int.";
	public static final String E_DOUBLE_TO_FLOAT_NARROWING = "Error: Cannot convert from a double to a float.";		
	public static final String E_INT_TO_FLOAT_NARROWING = "Error: Cannot convert from an int to a float.";
	public static final String E_LONG_TO_FLOAT_NARROWING = "Error: Cannot convert from a long int to a float.";
	public static final String E_CONST_LITERAL_OVERFLOW = "Error: Overflow error converting from constant literal";
	public static final String E_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE = "Error: Cannot convert a pointer type to an integer type";	
	public static final String E_ASSIGNING_AN_INTEGER_TYPE_TO_A_POINTER = "Error: Cannot convert an integer type to a pointer.";
	public static final String E_ASSIGNING_A_STRING_TO_AN_INTEGER_TYPE = "Error: Cannot convert a string to an integer type.";
	public static final String E_ASSIGNING_STRING_TO_POINTER_TO_INT = "Error: Cannot convert a string to a pointer to integer.";
	public static final String E_ASSIGNING_STRING_TO_POINTER_TO_SHORT = "Error: Cannot convert a string to a pointer to a short int.";
	public static final String E_ASSIGNING_STRING_TO_POINTER_TO_LONG = "Error: Cannot convert a string to a pointer to a long int.";
	public static final String E_ASSIGNING_STRING_TO_POINTER_TO_FLOAT = "Error: Cannot convert a string to a pointer to a float.";
	public static final String E_ASSIGNING_STRING_TO_POINTER_TO_DOUBLE = "Error: Cannot convert a string to a pointer to a double.";
	public static final String E_ASSIGNING_STRING_TO_POINTER_TO_STRUCT = "Error: Cannot convert a string to a pointer to a struct or union.";
		
	// Warnings about declarations
	public static final String W_DOUBLE_CANNOT_BE_USED_WITH_SIGNED_SPEC = "Warning: double should not be used with a signed specifier.";
	public static final String W_FLOAT_CANNOT_BE_USED_WITH_SIGNED_SPEC = "Warning: float should not be used with a signed specifier.";
	public static final String W_FLOAT_CANNOT_BE_USED_WITH_UNSIGNED_SPEC ="Warning: float should not be used with an unsigned specifier.";	
	public static final String W_DOUBLE_CANNOT_BE_USED_WITH_UNSIGNED_SPEC = "Warning: double should not be used with a unsigned specifier.";
	public static final String W_INVALID_STORAGE_SPEC_FOR_EXTERNAL_DEC = "Warning: An external declaration should not have auto or register specified as storage class.";
	public static final String W_EXTERN_DEC_CANNOT_HAVE_INIT = "Warning: An extern variable should not be initialized.";
	
	// Errors about declarations
	public static final String E_DOUBLE_CANNOT_BE_USED_WITH_SIGNED_SPEC = "Error: double cannot not be used with a signed specifier.";
	public static final String E_FLOAT_CANNOT_BE_USED_WITH_SIGNED_SPEC = "Error: float cannot be used with a signed specifier.";
	public static final String E_FLOAT_CANNOT_BE_USED_WITH_UNSIGNED_SPEC ="Error: float cannot be used with an unsigned specifier.";	
	public static final String E_DOUBLE_CANNOT_BE_USED_WITH_UNSIGNED_SPEC = "Error: double cannot be used with a unsigned specifier.";
	public static final String E_INVALID_STORAGE_SPEC_FOR_EXTERNAL_DEC = "Error: An external declaration cannot have auto or register specified as storage class.";
	public static final String E_EXTERN_DEC_CANNOT_HAVE_INIT = "Error: An extern variable cannot be initialized.";

	// Errors about wrong control flow
	public static final String E_RETURN_VALUE_IN_FUNC_RETURNING_VOID = "Error: A function returning void cannot return any value.";
	public static final String E_RETURN_VOID_IN_FUNC_RETURNING_NON_VOID = "Error: The function must return a value.";
	
	// Warnings about wrong control flow
	public static final String W_RETURN_VALUE_IN_FUNC_RETURNING_VOID = "Warning: A function returning void cannot return any value.";
	public static final String W_RETURN_VOID_IN_FUNC_RETURNING_NON_VOID = "Warning: The function must return a value.";
	
	// Warnings about array size specifications
	public static final String W_ARRAY_SIZE_NOT_CONSTANT = "Warning: The array size specification should be a constant.";
	
	// Errors about array size specifications
	public static final String E_ARRAY_SIZE_NOT_CONSTANT = "Error: The array size specification must be a constant.";
	
	// Warnings about pointer and array assignments
	public static final String W_ASSIGNMENT_INCOMPATIBLE_POINTER_TYPES = "Warning: Assignment of incompatible pointer types.";
	public static final String W_INVALID_COMPARISON_OF_POINTER_AND_INTEGER = " is an invalid comparison on a pointer and an integer.";
	public static final String W_COMPARING_INCOMPATIBLE_POINTER_TYPES = " should not be used to compare incompatible pointer types.";
	public static final String W_STRING_ARRAY_BOUNDS_OVERFLOW = " is too long for the given array size and can cause overflow.";
	public static final String W_DIFFERENT_ARRAY_SUBSCRIPTS = "Warning: Assignment of arrays with different subscripts";
	
	// Errors about pointer and array assignments
	public static final String E_ASSIGNMENT_INCOMPATIBLE_POINTER_TYPES = "Error: Cannot assign incompatible pointer types.";
	public static final String E_INVALID_COMPARISON_OF_POINTER_AND_INTEGER = " is an invalid comparison on a pointer and an integer.";
	public static final String E_COMPARING_INCOMPATIBLE_POINTER_TYPES = " cannot be used to compare incompatible pointer types.";
	public static final String E_STRING_ARRAY_BOUNDS_OVERFLOW = " is too long for the given array size and will cause overflow.";
	public static final String E_DIFFERENT_ARRAY_SUBSCRIPTS = "Error: Assignment of arrays with different subscripts";
	
	// Other additional messages
	public static final String AI_SEE_DECLARATION = "See declaration in ";
	public static final String AI_INCOMPATIBLE_TYPE_IN_RETURN = "Incompatible type in \"return\": ";
	
	// Warning regarding shift operations
	public static final String W_LEFTSHIFT_COUNT_GREATER_THAN_SIZE_OF_TYPE = "Warning : Left Shift count >= the width of the type";
	
	// Warning regarding excess initialisers in Structures
	public static final String W_EXCESS_INITIALIZERS_IN_STRUCT_INIT = "Warning: Excess initializers specified in struct initialization values.";
	public static final String  W_EXCESS_INITIALIZERS_IN_UNION_INIT = "Warning: Excess initializers specified in union initialization values.";
	
	private HashMap<String, LinkedHashMap<SourceLocation, String>> fileNameVsErrors;
	private HashMap<String, LinkedHashMap<SourceLocation, String>> fileNameVsWarnings;
	
	private HashMap<String, String> conversionErrorsVsWarnings;
	private HashMap<String, String> declarationErrorsVsWarnings;
	private HashMap<String, String> controlFlowErrorsVsWarnings;
	private HashMap<String, String> arraySizeSpecsErrorsVsWarnings;
	private HashMap<String, String> incompatibleArrAndPtrAssgnErrorsVsWarnings;
	private HashMap<String, String> preprocessorErrorsVsWarnings;
	private HashMap<String, String> excessInitialiserInStructErrorVsWarning;
	
	// Settings for warnings/errors
	private boolean allWarningsAreErrors;
	private boolean conversionErrorsAreWarnings;
	private boolean declarationErrorsAreWarnings;
	private boolean controlFlowErrorsAreWarnings;
	private boolean arraySizeNonConstantsAreWarnings;
	private boolean incompatibleArrayAndPointerAssngsAreWarnings;
	private boolean allPreprocessorErrorsAreWarnings;
	private boolean excessInitialiserInStruct;
	
	
	// Flag that indicates if any strict error has been found (so compilation cannot proceed)
	private boolean foundStrictError;
	
	// Message types
	public static final short ERROR_MSGS_ONLY = 0;
	public static final short WARNING_MSGS_ONLY = 1;
	public static final short BOTH_ERROR_AND_WARNING_MSGS = 2;

	private static ErrorHandler singletonInstance;
	
	private ErrorHandler(){
		fileNameVsErrors = new HashMap<String, LinkedHashMap<SourceLocation, String>>();
		fileNameVsWarnings = new HashMap<String, LinkedHashMap<SourceLocation, String>>();
		
		// TODO hard-coded for now, read from some settings somewhere later
		allWarningsAreErrors = false;
		conversionErrorsAreWarnings = true;
		declarationErrorsAreWarnings = true;
		controlFlowErrorsAreWarnings = true;
		arraySizeNonConstantsAreWarnings = true;
		incompatibleArrayAndPointerAssngsAreWarnings = true;
		allPreprocessorErrorsAreWarnings = true;
		excessInitialiserInStruct = true;
		
		setUpErrorsAndWarnings();
	}
	
	private void setUpErrorsAndWarnings(){
		
		// Conversion errors and warnings
		conversionErrorsVsWarnings = new HashMap<String, String>();
		conversionErrorsVsWarnings.put(E_FLOAT_TO_CHAR_NARROWING, W_FLOAT_TO_CHAR_NARROWING);
		conversionErrorsVsWarnings.put(E_SHORT_TO_CHAR_NARROWING ,W_SHORT_TO_CHAR_NARROWING);
		conversionErrorsVsWarnings.put(E_DOUBLE_TO_CHAR_NARROWING ,W_DOUBLE_TO_CHAR_NARROWING);
		conversionErrorsVsWarnings.put(E_LONG_TO_CHAR_NARROWING ,W_LONG_TO_CHAR_NARROWING);
		conversionErrorsVsWarnings.put(E_FLOAT_TO_SHORT_NARROWING ,W_FLOAT_TO_SHORT_NARROWING);
		conversionErrorsVsWarnings.put(E_DOUBLE_TO_SHORT_NARROWING ,W_DOUBLE_TO_SHORT_NARROWING);
		conversionErrorsVsWarnings.put(E_LONG_TO_SHORT_NARROWING ,W_LONG_TO_SHORT_NARROWING);
		conversionErrorsVsWarnings.put(E_DOUBLE_TO_INT_NARROWING ,W_DOUBLE_TO_INT_NARROWING);
		conversionErrorsVsWarnings.put(E_DOUBLE_TO_LONG_NARROWING ,W_DOUBLE_TO_LONG_NARROWING);
		conversionErrorsVsWarnings.put(E_DOUBLE_TO_FLOAT_NARROWING ,W_DOUBLE_TO_FLOAT_NARROWING);
		conversionErrorsVsWarnings.put(E_FLOAT_TO_INT_NARROWING ,W_FLOAT_TO_INT_NARROWING);
		conversionErrorsVsWarnings.put(E_FLOAT_TO_LONG_NARROWING ,W_FLOAT_TO_LONG_NARROWING);
		conversionErrorsVsWarnings.put(E_INT_TO_FLOAT_NARROWING ,W_INT_TO_FLOAT_NARROWING);
		conversionErrorsVsWarnings.put(E_LONG_TO_FLOAT_NARROWING ,W_LONG_TO_FLOAT_NARROWING);
		conversionErrorsVsWarnings.put(E_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE ,W_ASSIGNING_POINTER_TO_AN_INTEGER_TYPE);
		conversionErrorsVsWarnings.put(E_ASSIGNING_AN_INTEGER_TYPE_TO_A_POINTER ,W_ASSIGNING_AN_INTEGER_TYPE_TO_A_POINTER);		
		conversionErrorsVsWarnings.put(E_ASSIGNING_A_STRING_TO_AN_INTEGER_TYPE ,W_ASSIGNING_A_STRING_TO_AN_INTEGER_TYPE);
		conversionErrorsVsWarnings.put(E_ASSIGNING_STRING_TO_POINTER_TO_INT, W_ASSIGNING_STRING_TO_POINTER_TO_INT);
		conversionErrorsVsWarnings.put(E_ASSIGNING_STRING_TO_POINTER_TO_SHORT, W_ASSIGNING_STRING_TO_POINTER_TO_SHORT);
		conversionErrorsVsWarnings.put(E_ASSIGNING_STRING_TO_POINTER_TO_LONG, W_ASSIGNING_STRING_TO_POINTER_TO_LONG);
		conversionErrorsVsWarnings.put(E_ASSIGNING_STRING_TO_POINTER_TO_FLOAT, W_ASSIGNING_STRING_TO_POINTER_TO_FLOAT);
		conversionErrorsVsWarnings.put(E_ASSIGNING_STRING_TO_POINTER_TO_DOUBLE, W_ASSIGNING_STRING_TO_POINTER_TO_DOUBLE);
		conversionErrorsVsWarnings.put(E_ASSIGNING_STRING_TO_POINTER_TO_STRUCT, W_ASSIGNING_STRING_TO_POINTER_TO_STRUCT);
		conversionErrorsVsWarnings.put(E_LEFTSHIFT_COUNT_GREATER_THAN_SIZE_OF_TYPE, W_LEFTSHIFT_COUNT_GREATER_THAN_SIZE_OF_TYPE);
		
		// Declaration errors and warnings
		declarationErrorsVsWarnings = new HashMap<String, String>();
		declarationErrorsVsWarnings.put(E_DOUBLE_CANNOT_BE_USED_WITH_SIGNED_SPEC, W_DOUBLE_CANNOT_BE_USED_WITH_SIGNED_SPEC);
		declarationErrorsVsWarnings.put(E_FLOAT_CANNOT_BE_USED_WITH_SIGNED_SPEC, W_FLOAT_CANNOT_BE_USED_WITH_SIGNED_SPEC);
		declarationErrorsVsWarnings.put(E_FLOAT_CANNOT_BE_USED_WITH_UNSIGNED_SPEC, W_FLOAT_CANNOT_BE_USED_WITH_UNSIGNED_SPEC);	
		declarationErrorsVsWarnings.put(E_DOUBLE_CANNOT_BE_USED_WITH_UNSIGNED_SPEC, W_DOUBLE_CANNOT_BE_USED_WITH_UNSIGNED_SPEC);
		declarationErrorsVsWarnings.put(E_INVALID_STORAGE_SPEC_FOR_EXTERNAL_DEC, W_INVALID_STORAGE_SPEC_FOR_EXTERNAL_DEC);
		declarationErrorsVsWarnings.put(E_EXTERN_DEC_CANNOT_HAVE_INIT, W_EXTERN_DEC_CANNOT_HAVE_INIT);
		declarationErrorsVsWarnings.put(E_CONST_LITERAL_OVERFLOW, W_CONST_LITERAL_OVERFLOW);
		
		// Control flow errors and warnings
		controlFlowErrorsVsWarnings =  new HashMap<String, String>();
		controlFlowErrorsVsWarnings.put(E_RETURN_VALUE_IN_FUNC_RETURNING_VOID, W_RETURN_VALUE_IN_FUNC_RETURNING_VOID);
		controlFlowErrorsVsWarnings.put(E_RETURN_VOID_IN_FUNC_RETURNING_NON_VOID, W_RETURN_VOID_IN_FUNC_RETURNING_NON_VOID);
		
		// Array size specifications and warnings
		arraySizeSpecsErrorsVsWarnings  =  new HashMap<String, String>();
		arraySizeSpecsErrorsVsWarnings.put(E_ARRAY_SIZE_NOT_CONSTANT, W_ARRAY_SIZE_NOT_CONSTANT);
		
		// Preprocessor errors and warning
		preprocessorErrorsVsWarnings = new HashMap<String, String>();
		preprocessorErrorsVsWarnings.put(E_PREPROCESSOR_REDIFINITION_NOT_IDENTICAL, W_PREPROCESSOR_REDIFINITION_NOT_IDENTICAL);
		
		incompatibleArrAndPtrAssgnErrorsVsWarnings = new HashMap<String, String>();
		incompatibleArrAndPtrAssgnErrorsVsWarnings.put(E_ASSIGNMENT_INCOMPATIBLE_POINTER_TYPES, W_ASSIGNMENT_INCOMPATIBLE_POINTER_TYPES);
		incompatibleArrAndPtrAssgnErrorsVsWarnings.put(E_INVALID_COMPARISON_OF_POINTER_AND_INTEGER, E_INVALID_COMPARISON_OF_POINTER_AND_INTEGER);
		incompatibleArrAndPtrAssgnErrorsVsWarnings.put(E_COMPARING_INCOMPATIBLE_POINTER_TYPES, W_COMPARING_INCOMPATIBLE_POINTER_TYPES);
		incompatibleArrAndPtrAssgnErrorsVsWarnings.put(E_STRING_ARRAY_BOUNDS_OVERFLOW, W_STRING_ARRAY_BOUNDS_OVERFLOW);		
		incompatibleArrAndPtrAssgnErrorsVsWarnings.put(E_DIFFERENT_ARRAY_SUBSCRIPTS, W_DIFFERENT_ARRAY_SUBSCRIPTS);
	
		// Excess Initialiser in Structure
		excessInitialiserInStructErrorVsWarning = new HashMap<String, String>();
		excessInitialiserInStructErrorVsWarning.put(E_EXCESS_INITIALIZERS_IN_STRUCT_INIT, W_EXCESS_INITIALIZERS_IN_STRUCT_INIT);
		excessInitialiserInStructErrorVsWarning.put(E_EXCESS_INITIALIZERS_IN_UNION_INIT, W_EXCESS_INITIALIZERS_IN_UNION_INIT);
	}
	
	public static ErrorHandler getInstance() {
		if(singletonInstance == null)
			singletonInstance = new ErrorHandler();
		return singletonInstance;
	}
	
	public void reset(){
		fileNameVsErrors.clear();
		fileNameVsWarnings.clear();
		
		// Reset to default values
		allWarningsAreErrors = false;
		conversionErrorsAreWarnings = true;
		declarationErrorsAreWarnings = true;
		controlFlowErrorsAreWarnings = true;
		arraySizeNonConstantsAreWarnings = true;
		incompatibleArrayAndPointerAssngsAreWarnings = true;
		allPreprocessorErrorsAreWarnings = true;
		
		foundStrictError = false;
			
	}
	
	/*
	 * 
	 */
	public void addError(String fileName, SourceLocation location, String preMsg, String additionalInfo, 
					String mainMsg) {
		 
		String finalMsg = ""; // The final message that will be ultimately displayed
		
		if(allWarningsAreErrors){   // ALL warnings are errors (strict) - no need to check for warnings
									// ALL are errors			
			finalMsg = getFinalMsg(preMsg, mainMsg, additionalInfo, true);
			setErrorOrWarning(fileName, location, finalMsg, true);	
			foundStrictError = true;
		}
		else{    // All warnings are NOT errors
			String warningMainMsg = getWarningBasedOnTypeAndSetting(mainMsg);
			if(warningMainMsg != null && !warningMainMsg.equals("")){ // Found a warning
				finalMsg = getFinalMsg(preMsg, warningMainMsg, additionalInfo, false);
				setErrorOrWarning(fileName, location, finalMsg, false);
			}
			else{    // Did not find a warning; treat is an error
				finalMsg = getFinalMsg(preMsg, mainMsg, additionalInfo, true);
				setErrorOrWarning(fileName, location, finalMsg, true);
				foundStrictError = true;
			}	
		}
	}
	
	private String getFinalMsg(String preMsg, String mainMsg, String additionalInfo, boolean isError){
		String finalMsg = "";
		String startFlag = ErrorHandler.ERROR;
		if(!isError) 
			startFlag = ErrorHandler.WARNING;
		
		if(preMsg != null && !preMsg.equals("")) { // There is a string to attach before the main message		
			if(mainMsg.startsWith(ErrorHandler.WARNING)){
				mainMsg = mainMsg.substring(ErrorHandler.WARNING.length(), mainMsg.length());
			}
			else if(mainMsg.startsWith(ErrorHandler.ERROR)){
				mainMsg = mainMsg.substring(ErrorHandler.ERROR.length(), mainMsg.length());
			}
			finalMsg = startFlag + preMsg + mainMsg;
		}
		else
			finalMsg = mainMsg;
		
		if(additionalInfo != null && !additionalInfo.equals(""))   // There is an additional info message too
			finalMsg += additionalInfo;
		
		return finalMsg;
	}
	
	private String getWarningBasedOnTypeAndSetting(String mainMsg){
						
		if(conversionErrorsAreWarnings && conversionErrorsVsWarnings.containsKey(mainMsg)){
			// Conversion errors are warnings
			return conversionErrorsVsWarnings.get(mainMsg);			
		}
		else if(declarationErrorsAreWarnings && declarationErrorsVsWarnings.containsKey(mainMsg)){
			// Declaration errors are warnings
			return declarationErrorsVsWarnings.get(mainMsg);
		}
		else if(controlFlowErrorsAreWarnings && controlFlowErrorsVsWarnings.containsKey(mainMsg)){
			// Declaration errors are warnings
			return controlFlowErrorsVsWarnings.get(mainMsg);
		}
		else if(arraySizeNonConstantsAreWarnings && arraySizeSpecsErrorsVsWarnings.containsKey(mainMsg)){
			// Array size specified with non-integers/ expressions are warnings
			return arraySizeSpecsErrorsVsWarnings.get(mainMsg);
		}
		else if(incompatibleArrayAndPointerAssngsAreWarnings && incompatibleArrAndPtrAssgnErrorsVsWarnings.containsKey(mainMsg)){
			// Array size specified with non-integers/ expressions are warnings
			return incompatibleArrAndPtrAssgnErrorsVsWarnings.get(mainMsg);
		}
		else if(allPreprocessorErrorsAreWarnings && preprocessorErrorsVsWarnings.containsKey(mainMsg)){
			// Declaration errors are warnings
			return preprocessorErrorsVsWarnings.get(mainMsg);
		}
		else if(excessInitialiserInStruct && excessInitialiserInStructErrorVsWarning.containsKey(mainMsg)){
			// Excess Initialiser in Structure is a Warning
			return excessInitialiserInStructErrorVsWarning.get(mainMsg);
		}
		else 
			return "";
		
	}
	
	private void setErrorOrWarning(String fileName, SourceLocation location, String msg, boolean isError){						
		LinkedHashMap<SourceLocation, String> entriesInErrorColl = singletonInstance.fileNameVsErrors.get(fileName);
	
		if(entriesInErrorColl == null)
			entriesInErrorColl = new LinkedHashMap<SourceLocation, String>();
		
		SourceLocation newLocation = location;
		if(entriesInErrorColl.containsKey(location)){			
			newLocation = location.getClone();
			
		}

		entriesInErrorColl.put(newLocation, msg);
		singletonInstance.fileNameVsErrors.put(fileName, entriesInErrorColl);

		
		// If this is a warning, add it to the warnings collection too
		if(!isError){
			LinkedHashMap<SourceLocation, String> entriesInWarningColl = singletonInstance.fileNameVsWarnings.get(fileName);
			
			if(entriesInWarningColl == null)
				entriesInWarningColl = new LinkedHashMap<SourceLocation, String>();
			
			entriesInWarningColl.put(newLocation, msg);
			singletonInstance.fileNameVsWarnings.put(fileName, entriesInWarningColl);
			
		}
		
	}
	
	public LinkedHashMap<SourceLocation, String> getErrorsInFile(String fileName){
		sortErrors(fileName);		
		return singletonInstance.fileNameVsErrors.get(fileName);

	}
	
	public void sortErrors(String fileName){
		
		LinkedHashMap<SourceLocation, String> errsInFile = singletonInstance.fileNameVsErrors.get(fileName);
		if(errsInFile == null)
			return;
		
		Set<SourceLocation> keys = errsInFile.keySet();
		ArrayList<SourceLocation> arrayListSrcLocks = new ArrayList<SourceLocation>();
		Iterator<SourceLocation> iter = keys.iterator();
		while(iter.hasNext()){
			arrayListSrcLocks.add(iter.next());
		}
		
		Collections.sort(arrayListSrcLocks);
		LinkedHashMap<SourceLocation, String> temp = new LinkedHashMap<SourceLocation, String>();
		
		for(SourceLocation srcLocation: arrayListSrcLocks ){
			String msg = errsInFile.get(srcLocation);
			temp.put(srcLocation, msg);
		}
		
		singletonInstance.fileNameVsErrors.put(fileName, temp);  // Update
	}
	
	public int getNumErrors() {
		int numErrors = 0;
		if(singletonInstance.fileNameVsErrors.size() == 0)
			return numErrors;
	
		// Determine number of errors
		Set entries = singletonInstance.fileNameVsErrors.entrySet();
		Iterator iter = entries.iterator();
		while(iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			LinkedHashMap errorsInFile = (LinkedHashMap)entry.getValue();
			
			numErrors += errorsInFile.size();
		}
			
		// Remove number of warnings
		if(singletonInstance.fileNameVsWarnings.size() == 0){
			return numErrors;
		}
		else{
			return numErrors - getNumWarnings();
		}
	}
	
	public int getNumWarnings() {
		int numWarnings = 0;
		if(singletonInstance.fileNameVsWarnings.size() == 0)
			return numWarnings;
	
		// Determine number of errors
		Set entries = singletonInstance.fileNameVsWarnings.entrySet();
		Iterator iter = entries.iterator();
		while(iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			LinkedHashMap warningInFile = (LinkedHashMap)entry.getValue();
			
			numWarnings += warningInFile.size();
		}
		
		return numWarnings;
	}
	
	public Vector<String> getErrorOrWarningMessages(String fileName, SourceLocation location, short errOrWarning){
		
		Vector<String> errMsgs = new Vector<String>();
		
		LinkedHashMap<SourceLocation, String> errorsInfile = singletonInstance.fileNameVsErrors.get(fileName);
		if(errorsInfile == null)
			return errMsgs;
		
		Set errEntries = errorsInfile.entrySet();
		Iterator errorsIter = errEntries.iterator();
		while(errorsIter.hasNext()){
			Map.Entry errEntry = (Map.Entry) errorsIter.next();
			SourceLocation srcLocation = (SourceLocation)errEntry.getKey();
			if(srcLocation.getLineNum() == location.getLineNum() &&
					srcLocation.getPos() == location.getPos()){
				String existingMsg = (String)errEntry.getValue();
				if(errOrWarning == ErrorHandler.ERROR_MSGS_ONLY){
					if(existingMsg.startsWith(ERROR))
						errMsgs.addElement(existingMsg);
				}
				else if(errOrWarning == ErrorHandler.WARNING_MSGS_ONLY){
					if(existingMsg.startsWith(WARNING))
						errMsgs.addElement(existingMsg);
				}
				else {  // ALL messages
					errMsgs.addElement(existingMsg);
				}
			}
		}
		
		return errMsgs;
	}
	
	public boolean errorOrWarningAlreadyExists(String fileName, SourceLocation location, 
			short errOrWarning, String msg){
		Vector<String> existingMsgs = getErrorOrWarningMessages(fileName, location, errOrWarning);
		return existingMsgs.contains(msg);
	}
	
	public void displayResult() {
		Set entries = singletonInstance.fileNameVsErrors.entrySet();
		Iterator iter = entries.iterator();
		while(iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			String fileName = (String)entry.getKey();
			System.out.println(fileName + ": ");
			
			sortErrors(fileName);
			
			LinkedHashMap<SourceLocation, String> errorsInFile= (LinkedHashMap<SourceLocation, String>) entry.getValue();
			Set errEntries = errorsInFile.entrySet();
			Iterator errorsIter = errEntries.iterator();
			while(errorsIter.hasNext()){
				Map.Entry errEntry = (Map.Entry) errorsIter.next();
				SourceLocation srcLocation = (SourceLocation)errEntry.getKey();
				String msg = (String)errEntry.getValue();
				
				msg = srcLocation.getLineNum() + ", " + srcLocation.getPos() + ": " + msg;
				System.out.println(msg);
				
			}				
		}
			
		// Print result summary
		displayResultSummary();			
	}

	private void displayResultSummary(){
		int numErrs = getNumErrors();
		int numWarnings = getNumWarnings();
		
		if(numErrs == 0 && numWarnings == 0) {
			System.out.println("Compiled successfully.");
		}
		else{
			if(numErrs > 0){
				System.out.println("Compilation failed: " + numErrs + " error(s) and " + numWarnings + " warning(s).");
			}
			else{  // Warnings are greater than zero
				System.out.println("Compiled successfully, but with " + numWarnings + " warning(s).");
			}
		}
	}

	public boolean isAllWarningsAreErrors() {
		return allWarningsAreErrors;
	}

	public void setAllWarningsAreErrors(boolean allWarningsAreErrors) {
		this.allWarningsAreErrors = allWarningsAreErrors;
	}

	public boolean isConversionErrorsAreWarnings() {
		return conversionErrorsAreWarnings;
	}

	public void setConversionErrorsAreWarnings(boolean conversionErrorsAreWarnings) {
		this.conversionErrorsAreWarnings = conversionErrorsAreWarnings;
	}

	public boolean isDeclarationErrorsAreWarnings() {
		return declarationErrorsAreWarnings;
	}

	public void setDeclarationErrorsAreWarnings(boolean declarationErrorsAreWarnings) {
		this.declarationErrorsAreWarnings = declarationErrorsAreWarnings;
	}

	public boolean isFoundStrictError() {
		return foundStrictError;
	}
}
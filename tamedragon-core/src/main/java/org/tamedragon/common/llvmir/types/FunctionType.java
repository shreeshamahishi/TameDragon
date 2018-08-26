package org.tamedragon.common.llvmir.types;

import java.util.ArrayList;
import java.util.Vector;

public class FunctionType extends Type {

	private boolean isVarArgs;

	public FunctionType(CompilationContext compilationContext, Type resultType,
			Vector<Type> args, boolean isVarArgs){
		super(compilationContext, TypeID.FUNCTION_ID);

		if(containedTypes == null)
			containedTypes = new ArrayList<Type>();

		// Add the result type to the contained types
		containedTypes.add(resultType);   

		// Add the parameters to the contained types if any
		if(args != null){
			for(int i = 0; i < args.size(); i++){
				containedTypes.add(args.elementAt(i));
			}
		}

		// Set the variable arguments flag
		this.isVarArgs = isVarArgs;
	}

	public boolean isVarArg() { return isVarArgs; }

	public Type getReturnType() { return containedTypes.get(0); }

	public Type getParamType(int index) { return containedTypes.get(index +1); }

	/**
	 * getNumParams returns the number of fixed parameters this function type
	 * requires.  This does not consider varargs.
	 */
	public int getNumParams() { 
		return containedTypes.size() - 1; 
	}

	/**
	 * Utility function that returns true if the specified type is valid as an argument type.
	 */
	public static boolean isValidArgumentType(Type argType){
		return argType.isFirstClassType();
	}
	
	/**
	 * Utility function that returns true if the type this is passed as parameter
	 * can be used as a return type from a function
	 */
	public static boolean isValidReturnType(Type returnType){
		return !returnType.isFunctionType() && !returnType.isLabelType() &&
		  !returnType.isMetadataType();
	}
	
	
	public String toString(){
		
		// Initialize with return type description
		String desc = containedTypes.get(0).toString();
		
		// Now append the parameter type descriptions
		desc += " (";
		for(int i = 1; i < containedTypes.size(); i++){
			String delimiter = i == containedTypes.size() -1 ? "" : ", ";
			desc += containedTypes.get(i).toString() + delimiter;
		}
		
		if(isVarArgs)
			desc += ", ...";
		
		desc += ")";
		
		return desc;
	}
}

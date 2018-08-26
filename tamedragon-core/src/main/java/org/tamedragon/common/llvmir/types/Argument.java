package org.tamedragon.common.llvmir.types;

import java.util.List;

import org.tamedragon.common.llvmir.instructions.Attributes;
import org.tamedragon.common.llvmir.instructions.LLVMUtility;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionDetailAccessException;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionUpdateException;
import org.tamedragon.common.llvmir.types.exceptions.TypeCreationException;


/**
 * A class to represent an incoming formal argument to a Function. An argument
 *  is a very simple Value. It is essentially a named (optional) type. When used 
 *  in the body of a function, it represents the value of the actual argument 
 *  the function was called with.
 */

public class Argument extends Value {

	private Function parentFunction;

	public void setParent(Function parent){
		this.parentFunction = parent;
	}

	/**
	 * If Function argument is specified, this argument is 
	 * inserted at the end of the argument list for the function.
	 * @param Ty
	 */
	public Argument(Type Ty, String name, Function parent)  {
		super(Ty);
		setValueTypeID(ValueTypeID.ARGUMENT);

		if (parent != null){
			this.parentFunction = parent;
			parent.getArgumentList().add(this);
			//parent.getSymbolTable().createValueName(name, this);
		}
		setName(name);
		setValueTypeID(ValueTypeID.ARGUMENT);
	}

	public Function getParent()  { return parentFunction; }

	/**
	 * Returns the index of this formal argument in its containing 
	 * function.  For example in "void foo(int a, float b)" a is 0 and b is 1. 
	 * @return
	 */
	public int getArgNo() {
		Function func = getParent();
		if(func == null){
			//TODO Throw exception and log here
			//throw new Exception("Argument is not in a function");
		}

		List<Argument> argList = func.getArgumentList();
		int count = 0;
		for(Argument arg : argList){
			if(arg == this)
				return count;
			count++;
		}

		return -1;   // Invalid argument?

	}

	/// hasByValAttr - Return true if this argument has the byval attribute on it
	/// in its containing function.
	public boolean hasByValAttr(){
		if (getType().isPointerType()) 
			return false;
		return getParent().paramHasAttr(getArgNo() + 1,Attributes.ByVal);
	}

	/// getParamAlignment - If this is a byval argument, return its alignment.
	public int getParamAlignment() throws TypeCreationException, InstructionDetailAccessException{
		//&& "Only pointers have alignments");
		return getParent().getParamAlignment(getArgNo()+1);
	}

	/// hasNestAttr - Return true if this argument has the nest attribute on
	/// it in its containing function.
	public boolean hasNestAttr(int index) {
		if (!getType().isPointerType()) 
			return false;
		return getParent().paramHasAttr(index + 2, Attributes.Nest);
	}

	/// hasNoAliasAttr - Return true if this argument has the noalias attribute on
	/// it in its containing function.
	public boolean hasNoAliasAttr(){
		if (!getType().isPointerType()) return false;
		return getParent().paramHasAttr(getArgNo() + 2, Attributes.NoAlias);
	}

	/// hasNoCaptureAttr - Return true if this argument has the nocapture
	/// attribute on it in its containing function.
	public boolean hasNoCaptureAttr(int index){
		if (!getType().isPointerType()) return false;
		return getParent().paramHasAttr(index + 2, Attributes.NoCapture);
	}

	/// hasSRetAttr - Return true if this argument has the sret attribute on it in
	/// its containing function.
	public boolean hasStructRetAttr(int index){
		if (!getType().isPointerType()) 
			return false;
		//		  if (this != getParent().arg_begin())
		//		    return false; // StructRet param must be first param

		return getParent().paramHasAttr(index + 2, Attributes.StructRet);
	}

	/// addAttr - Add a Attribute to an argument
	public void addAttr(int index, int attr ) throws InstructionUpdateException{
		getParent().addAttribute(index + 2, attr);
	}

	/// removeAttr - Remove a Attribute from an argument
	public void removeAttr(int index, int attr) throws InstructionUpdateException{
		getParent().removeAttribute(index + 2, attr);
	}

	public String toString(){

		String localPrefix = "%";

		String desc = "";
		Type type = getType();

		if(type.isStructType())
			desc += ((StructType)type).getName();
		else
			desc += type.toString();

		String name = getName();

		if(name != null && name.length() != 0)
			desc += " " + localPrefix + name; // arguments are always local

		List<Integer> attributeList = getParent().getAttributeList();
		List<Argument> argumentList = getParent().getArgumentList();
		int indexOfArgument = argumentList.indexOf(this);

		if(attributeList != null && attributeList.size() > (indexOfArgument + 2)){
			int attributes = attributeList.get(indexOfArgument + 2);
			String getAttrsAsString = LLVMUtility.getAttributeAsString(attributes);
			desc += " " + getAttrsAsString;
		}

		return desc;
	}
}

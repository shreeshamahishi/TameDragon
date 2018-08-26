package org.tamedragon.common.llvmir.types;

import java.util.ArrayList;
import java.util.List;

import org.tamedragon.common.llvmir.instructions.BranchInst;
import org.tamedragon.common.llvmir.instructions.Instruction;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionDetailAccessException;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionUpdateException;
import org.tamedragon.common.llvmir.symboltable.ValueSymbolTable;
import org.tamedragon.common.llvmir.types.Type.TypeID;

public class Value implements Container {

	public enum ValueTypeID {
		ARGUMENT,              // This is an instance of Argument
		BASIC_BLOCK,            // This is an instance of BasicBlock
		FUNCTION,              // This is an instance of Function
		GLOBAL_ALIAS,           // This is an instance of GlobalAlias
		GLOBAL_VALUE,        // This is an instance of GlobalVariable
		UNDEF_VALUE,            // This is an instance of UndefValue
		BLOCK_ADDRESS,          // This is an instance of BlockAddress
		CONST_EXPR,          // This is an instance of ConstantExpr
		CONST_AGGREGATE_ZERO, // This is an instance of ConstantAggregateZero
		CONST_INT,           // This is an instance of ConstantInt
		CONST_FP,            // This is an instance of ConstantFP
		CONST_ARRAY,         // This is an instance of ConstantArray
		CONST_STRUCT,        // This is an instance of ConstantStruct
		CONST_VECTOR,        // This is an instance of ConstantVector
		CONST_POINTER_NULL,   // This is an instance of ConstantPointerNull
		MD_NODE,                // This is an instance of MDNode
		MD_STRING,              // This is an instance of MDString
		INLINE_ASM,             // This is an instance of InlineAsm
		PSEUDO_SRC_VALUE,     // This is an instance of PseudoSourceValue
		FIXED_STACK_PSEUDO_SRC, // This is an instance of 
		// FixedStackPseudoSourceValue
		INSTRUCTION,           // This is an instance of Instruction
	} 

	protected ValueTypeID valueTypeID;
	protected Type type;
	protected List<User> userList;
	private String name;
	
	/**
	 * This is the greatest alignment value supported by load, store, and alloca instructions, and global values.
	 */
	public static final long MAX_ALIGN = 1l << 29;
	
	public Value(Type type){
		this.type = type;
	}

	public Type getType() {
		return type; 
	}

	public void setType(Type type) {
		this.type = type; 
	}

	public void setValueTypeID(ValueTypeID valueTypeID) {
		this.valueTypeID = valueTypeID;
	}

	public ValueTypeID getValueTypeID() {
		return valueTypeID; 
	}

	/**
	 * All values hold a context through their type.
	 * @return
	 */
	public CompilationContext getContext(){
		return type.getCompilationContext(); 
	}

	/**
	 * All values can potentially be named...
	 * @return
	 */
	public boolean hasName() { 
		return name != null; 
	}

	public void setName(String newName){
		// Fast path for common IRBuilder case of setName("") when there is no name.
		if (newName == null && !hasName())
			return;

		if(newName.length() == 0)
			return;

		// Name isn't changing?
		if (name != null && name.equals(newName))
			return;

		if(isAConstant())
			return;  // Cannot set name for a constant

		if(getType().isVoidType())
			return;   // Cannot assign a name to void values!

		if(valueTypeID == ValueTypeID.BASIC_BLOCK){
			name = newName;
			return;
		}
		
		// Get the symbol table to update for this object.
		ValueSymbolTable valueSymbolTable = getSymbolTable();
		if (valueSymbolTable == null){
			// If the symbol table is null, just set the new name
			name = newName;
			return;
		}

		// Name is changing to something new.
		String updatedNewName = valueSymbolTable.createValueName(newName, this);
		name = updatedNewName;
	}

	/**
	 * Return true if there is exactly one user of this value. 
	 * @return
	 */
	public boolean  hasOneUse() {
		return userList.size() == 0;
	}

	/**
	 * Return true if this Value has exactly N users.
	 * @param n
	 * @return
	 */
	public boolean hasNUsers(int n) {
		return userList.size() == n;}

	/**
	 * Return true if this value has N users or more.  This is logically equivalent to getNumUses() >= N.
	 * @param n
	 * @return
	 */
	public boolean hasNUsesOrMore(int n) {
		return userList.size()>= n;
	}

	/**
	 * This method computes the number of uses of this Value. This is a linear time operation. 
	 * @return
	 */
	public int getNumUses(){
		if(userList == null)
			return 0;
		return userList.size();
	}

	/**
	 * This method should only be used by the Use class.
	 * @param user
	 */
	public void addUser(User user) {
		if(userList == null || userList.size() == 0)
			userList = new ArrayList<User>();

		userList.add(user);
	}

	public boolean isUser(Value value){
		if(userList.contains(value))
			return true;

		return false;
	}

	public Value getUserAt(int index){
		if(index >= userList.size())
			return null;

		return userList.get(index);
	}

	public boolean removeUser(Value val) {
		return userList.remove(val);
	}


	public void replaceAllUsesOfThisWith(Value newValue){
		if(this == newValue){
			// TODO Log warning here: No effect of replacing uses of this value with itself.
			return;
		}
		if(userList == null)
			return;
		for(User user : userList){
			int numOperands = user.getNumOperands();
			int indexToBeReplacedAt = -1;
			for(int x = 0; x < numOperands; x++){
				Value operand = user.getOperand(x);
				if(operand == this){
					indexToBeReplacedAt = x;
					break;
				}
			}

			user.setOperand(indexToBeReplacedAt, newValue);

			BasicBlock basicBlock = null;
			if(newValue.getType().getTypeId() == TypeID.LABEL_ID){
				basicBlock = (BasicBlock)newValue;
			}

			if(basicBlock != null && user instanceof BranchInst){
				BranchInst branchInst = (BranchInst)user;
				try {
					branchInst.setSuccessor(indexToBeReplacedAt, basicBlock, false);
					branchInst.ifHasSameOperandsMakeConditionalToUnconditional();
				} catch (InstructionUpdateException e) {
					e.printStackTrace();
					System.exit(-1);
				} catch (InstructionDetailAccessException e) {
					e.printStackTrace();
					System.exit(-1);
				}
			}
		}
	}

	/**
	 * An enumeration for keeping track of the concrete subclass of Value that
	 * is actually instantiated. Values of this enumeration are kept in the 
	 * Value classes SubclassID field. They are used for concrete type
	 * identification.
	 * @author ipsg
	 *
	 */
	enum ValueTy {

	};

	public String toString(){
		String nameStr = "";
		if(name != null)
			nameStr = name;
		return (type.toString() + " " + nameStr).trim();
	}

	public String getName() {
		return name;
	}

	public ValueSymbolTable getSymbolTable(){
		if(this instanceof Instruction){
			Instruction instruction = (Instruction)this;
			BasicBlock basicBlock = instruction.getParent();
			if(basicBlock != null)
				if(basicBlock.getParent() != null)
					return basicBlock.getParent().getSymbolTable();
		}
		else if(this.valueTypeID == ValueTypeID.BASIC_BLOCK){
			BasicBlock basicBlock = (BasicBlock)this;
			return basicBlock.getParent().getSymbolTable();
		}
		else if(this instanceof Argument){
			Argument argument = (Argument)this;
			if(argument.getParent() != null)
				return argument.getParent().getSymbolTable();
		}
		return null;
	}

	public boolean isAConstant(){
		if(valueTypeID == ValueTypeID.CONST_AGGREGATE_ZERO
				|| valueTypeID == ValueTypeID.CONST_ARRAY
				|| valueTypeID == ValueTypeID.CONST_EXPR
				|| valueTypeID == ValueTypeID.CONST_FP
				|| valueTypeID == ValueTypeID.CONST_INT
				|| valueTypeID == ValueTypeID.CONST_POINTER_NULL
				|| valueTypeID == ValueTypeID.CONST_STRUCT
				|| valueTypeID == ValueTypeID.CONST_VECTOR)
			return true;

		return false;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Value))
			return false;

		if(this instanceof Constant && obj instanceof Constant){
			return ((Constant)this).equals((Constant)obj);
		}
		else{
			return this == obj;
		}
	}

	@Override
	public ContainerTypeID getContainerType() {
		return Container.ContainerTypeID.VALUE;
	}
}

package org.tamedragon.common.llvmir.types;

import java.util.List;

public class User extends Value {

	/**
	 * This is a pointer to the array of Uses for this User. 
	 * For nodes of fixed arity (e.g. a binary operator) this array will live
	 * prefixed to some derived class instance.  For nodes of resizable variable
	 * arity (e.g. PHINodes, SwitchInst etc.), this memory will be dynamically
	 * allocated and should be destroyed by the classes' virtual dtor.
	 */
	private List<? extends Value> operandList;

	public User(Type type, List<? extends Value> operandList)  {
		super(type);
		this.operandList = operandList;

		// To each operand, add this as the user
		if(operandList == null)
			return;

		for(Object operand : operandList){
			if(operand != null)
				((Value) operand).addUser(this);
		}
	}

	public Value getOperand(int index) {
		return (Value) operandList.get(index);
	}

	public void setOperand(int i, Value newOperand) {
		((List<Value>)operandList).set(i, newOperand);

		if(newOperand != null)
			newOperand.addUser(this);
	}

	protected Value removeOperandAt(int i) {
		return (Value) operandList.remove(i);
	}
	
	protected boolean removeOperand(Value value){
		return operandList.remove(value);
	}

	/**
	 * Add the new operand to the end of the operand list.
	 * @param newOperand
	 */
	protected void addOperand(Value newOperand) {
		((List<Value>)operandList).add(newOperand);

		if(newOperand != null)
			newOperand.addUser(this);
	}

	public int getNumOperands() { 
		if(operandList == null)
			return 0;
		return operandList.size(); 
	}

	public int getIndexOf(Value value){
		return operandList.indexOf(value);
	}
}

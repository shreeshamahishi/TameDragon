package org.tamedragon.common.llvmir.instructions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;

import org.tamedragon.common.LatticeValue;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionCreationException;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionDetailAccessException;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionUpdateException;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.CompilationContext;
import org.tamedragon.common.llvmir.types.Constant;
import org.tamedragon.common.llvmir.types.ConstantInt;
import org.tamedragon.common.llvmir.types.Type;
import org.tamedragon.common.llvmir.types.Value;
import org.tamedragon.common.llvmir.utils.LLVMIREmitter;

/**
 * This class represents LLVM's IndirectBranch Instruction
 * @author ipsg
 *
 */
public class IndirectBranchInst extends TerminatorInst {

	private static final String INDIRECT_BRANCH_INST = "indirect branch instruction";

	protected  IndirectBranchInst(CompilationContext compilationContext, List<Value> operandList, BasicBlock parent){
		super(InstructionID.INDIRECT_BR_INST, Type.getVoidType(compilationContext), operandList, parent);
	}

	@Override
	public String getInstructionName(){
		return INDIRECT_BRANCH_INST;
	}

	@Override
	public BasicBlock getSuccessor(int index) throws InstructionDetailAccessException {
		if(index < 0 || index >= getNumSuccessors()){
			throw new InstructionDetailAccessException(InstructionDetailAccessException.INVALID_INDEX_FOR_SUCCESSOR, 
												getInstructionName(), ": " + index);
		}
		
		return (BasicBlock)getOperand(index + 1);
	}

	@Override
	public void setSuccessor(int index, BasicBlock basicBlock, boolean setOprnd) throws InstructionUpdateException {
		if(index < 0 || index >= getNumSuccessors()){
			throw new InstructionUpdateException(InstructionUpdateException.INVALID_INDEX_FOR_SUCCESSOR, 
												getInstructionName(), ": " + index);
		}
		
		if(basicBlock == null){
			throw new InstructionUpdateException(InstructionUpdateException.BASIC_BLOCK_CANNOT_BE_NULL);
		}
		
		setOperand(index  +1, basicBlock);
	}

	/**
	 * Adds a case to this switch instruction. Must have the constant integer value and the basic
	 * block that it targets.
	 * @param constantInt
	 * @param basicBlock
	 * @throws InstructionUpdateException
	 */
	public void addPossibleDestinations( BasicBlock basicBlock) throws InstructionUpdateException {

		// ConstantInt passed is valid, check the basic block
		if(basicBlock == null){
			throw new InstructionUpdateException(InstructionUpdateException.BASIC_BLOCK_CANNOT_BE_NULL);
		}
		
		// Argument is OK, add it to the operands
		addOperand(basicBlock);
	}

	/**
	 * Returns the condition to switch on. Note that this is stored in the first operand.
	 * @return
	 */
	public Value getCondition() { return getOperand(0); }

	/**
	 * Sets the condition to switch on. Note that this is stored as the first operand.
	 * @return
	 */
	public void setCondition(Value value, boolean isSigned) throws InstructionUpdateException{
		if(value.getType() != Type.getInt32Type(value.getContext(), isSigned))
			throw new InstructionUpdateException(InstructionUpdateException.SWITCH_ON_TYPE_MUST_BE_I32_OR_I64);
		
		setOperand(0, value); 
	}

	/**
	 * Returns the default basic block. Note that this is stored as the second operand.
	 * @return
	 */
	public BasicBlock getDefaultDest() {
		return (BasicBlock)getOperand(1);
	}

	/**
	 * Returns the number of cases. This includes the default case (by notation this
	 * is 0) and the additional cases that may have been added.
	 * @return
	 */
	public int getNumCases()  {
		return getNumOperands()/2;
	}

	@Override
	public int getNumSuccessors() { return getNumOperands()/2; }

	/**
	 * Returns the specified case value.  Note that case #0, the
	 * default destination, does not have a case value.
	 */
	public ConstantInt getCaseValue(int i) throws InstructionDetailAccessException {
		if(i <= 0 || i >= getNumCases()){
			throw new InstructionDetailAccessException(InstructionDetailAccessException.INVALID_INDEX_FOR_CASE);
		}
		
		return getSuccessorValue(i);
	}

	/**
	 * Returns the value associated with the specified successor.
	 * 
	 * @param index
	 * @return
	 */
	
	public ConstantInt getSuccessorValue(int index)  throws InstructionDetailAccessException {
		if(index <= 0 || index >= getNumSuccessors())
			throw new InstructionDetailAccessException(InstructionDetailAccessException.INVALID_INDEX_FOR_SUCCESSOR, 
					getInstructionName(), ": " + index);
		
		return (ConstantInt) getOperand(index *2);
	}

	/**
	 * Searches all of the case values for the specified constant. If it is explicitly handled, 
	 * return the case number of it, otherwise return 0 to indicate that it is handled by the 
	 * default handler.
	 */
	public int findCaseValue( ConstantInt constantInt) throws InstructionDetailAccessException {
		int numCases = getNumCases();
		for (int i = 1; i != numCases; ++i)
			if (getCaseValue(i) == constantInt)
				return i;
		return 0;
	}

	/**
	 * 
	 * Finds the unique case value for a given successor. Returns null if the successor is
	 * not found, not unique, or is the default case.
	 * @param basicBlock
	 * @return
	 * @throws InstructionDetailAccessException
	 */
	public ConstantInt findCaseDest(BasicBlock basicBlock) throws InstructionDetailAccessException {
		if (basicBlock == getDefaultDest()) 
			return null;   // The default case 0

		ConstantInt constantInt = null;
		int numCases = getNumCases();
		for (int i = 1; i != numCases; ++i) {
			BasicBlock bb = getSuccessor(i);
			if(bb.equals(basicBlock)) {
				if (constantInt != null)
					return null;   // Multiple cases lead to the basic block.				
				else 
					constantInt = getCaseValue(i);
			}
		}
		return constantInt;
	}

	/**
	 * This method removes the specified successor from the switch instruction. Note 
	 * that this cannot be used to remove the default destination (successor #0). 
	 * Also note that this operation may reorder the remaining cases at index idx and above.
	 * @param index
	 */
	public void removeCase(int index) throws InstructionUpdateException, InstructionDetailAccessException{
		
		if(index == 0){
			throw new InstructionUpdateException(InstructionUpdateException.CANNOT_REMOVE_DEFAULT_CASE);
		}
	
		if(index < 0 || index *2 >= getNumOperands()){
			throw new InstructionDetailAccessException(InstructionDetailAccessException.INVALID_INDEX_FOR_SUCCESSOR, 
					getInstructionName(), ": " + index);
		}
		
		// The index is OK
		removeOperandAt(index *2);      // Remove the case value
		removeOperandAt(index * 2);  // Remove the basic block for the case
	}

	/**
	 * Updates the value associated with the specified successor.
	 * @param index
	 */
	public void setSuccessorValue(int index, ConstantInt successorValue) 
				throws InstructionUpdateException{
		if(index < 0 || index >= getNumSuccessors())
			throw new InstructionUpdateException(InstructionUpdateException.INVALID_INDEX_FOR_SUCCESSOR, 
								getInstructionName(), ": " + index);
		
		if(successorValue == null)
			throw new InstructionUpdateException(InstructionUpdateException.CONSTANT_INT_CANNOT_BE_NULL);
		
		setOperand(index *2, successorValue);
	}

	public static SwitchInst create(Value switchOn, BasicBlock defaultBB, BasicBlock parent) throws InstructionCreationException{

		if(switchOn == null){
			throw new InstructionCreationException(InstructionCreationException.SWITCH_ON_CANNOT_BE_NULL);
		}

		Type typeOfSwitchOn = switchOn.getType();
		if(!typeOfSwitchOn.isInt32Type())
			throw new InstructionCreationException(InstructionCreationException.SWITCH_ON_TYPE_MUST_BE_I32_OR_I64);

		// Switch on is valid, check the default basic block
		if(defaultBB == null){
			throw new InstructionCreationException(InstructionCreationException.DEFAULT_BASIC_BLOCK_CANNOT_BE_NULL);
		}

		// Arguments are OK, lets create and return the Switch instruction
		List<Value> operandList = new ArrayList<Value>();
		operandList.add(switchOn);
		operandList.add(defaultBB);
		CompilationContext compilationContext = typeOfSwitchOn.getCompilationContext();
		return new SwitchInst(compilationContext, operandList, parent);
	}

	@Override
	public String emit(){
		String description = "switch ";
		description += getOperand(0).toString() + ", ";
		description += getOperand(1).toString() + " ";

		if(getNumOperands() == 2){
			// No extra cases
			description += "[]";
			return description;
		}

		// Has at least one non-default case
		int numOps = getNumOperands();
		description += "[";
		for(int i = 2; i < numOps; i++){
			Value op = getOperand(i);
			if(i % 2 == 0)
				description += op.getType().toString() + " " + LLVMIREmitter.getInstance().getValidName(op) + ", ";
			else{
				if(i == numOps -1)    // last case
					description += op.getType().toString() + " " + LLVMIREmitter.getInstance().getValidName(op) + " ";
				else
					description += op.getType().toString() + " " + LLVMIREmitter.getInstance().getValidName(op) + "\n";
			}
		}
		description += "]";

		return description;
	}

	@Override
	public boolean isCastInstruction() {
		return false;
	}

	@Override
	public boolean definesNewValue() {
		return false;
	}

	@Override
	public boolean isTerminatorInstruction() {
		return true;
	}

	@Override
	public boolean isCritical() {
		return true;
	}

	@Override
	public LatticeValue visitForSCCP(LatticeValue latticeValueBeforeModelling,
			HashSet<BasicBlock> unreachableNodes,
			HashMap<Value, LatticeValue> tempVsLatticeValue,
			Vector<Value> ssaWorkList, Vector<BasicBlock> cfgWorkList) {
		// Indirect branch instructions successors are always executable.
		
		BasicBlock parentBB = getParent();
		
		markAllSuccessorsExecutable(parentBB, unreachableNodes, cfgWorkList);
		return null;
	}

	@Override
	public boolean canBeHoistedOrSank() {
		return false;
	}
	
	@Override
	public Constant foldIfPossible() {
		//TODO : Implement later
		return null;
	}

	// TODO Implement this
	@Override
	public boolean replaceSuccessorWith(BasicBlock successor,
			BasicBlock newSuccessor) throws InstructionUpdateException,
			InstructionDetailAccessException {
		
		return false;
	}

	// TODO Implement this
	@Override
	public boolean removeSuccessor(BasicBlock successor)
			throws InstructionDetailAccessException {
		return false;
	}
}

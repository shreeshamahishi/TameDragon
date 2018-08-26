package org.tamedragon.common.llvmir.instructions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tamedragon.common.LatticeValue;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionCreationException;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionDetailAccessException;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionUpdateException;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.CompilationContext;
import org.tamedragon.common.llvmir.types.Constant;
import org.tamedragon.common.llvmir.types.Type;
import org.tamedragon.common.llvmir.types.Value;
import org.tamedragon.common.llvmir.utils.LLVMIREmitter;

/**
 * This class represents LLVM's branch instruction. It could be either conditional or unconditional.
 * The operand list will look like {[Condition, FalseDest]?, TrueDest} - this is consistent with
 * the representation in LLVM.
 * 
 */
public class BranchInst extends TerminatorInst {

	private static final String UNCONDITIONAL_BRANCH = "unconditional branch instruction";
	private static final String CONDITIONAL_BRANCH = "conditional branch instruction";
	private BasicBlock trueBB;
	private BasicBlock falseBB;

	private static final Logger LOG = LoggerFactory.getLogger(BranchInst.class);
	
	private static final String SUCCESSOR_MISMATCH_IN_UNCONDITIONAL_BRANCH = "The successor does not match the branch for the \"true\" path for this unconditional branch.";
	private static final String SUCCESSOR_MISMATCH_IN_CONDITIONAL_BRANCH = "The successor matches neither the \"true\" path nor the \"false\" path for this conditional branch.";
	private static final String CANNOT_REMOVE_SUCCESSOR = "Unable to remove successor due to previous errors.";
	private static final String CANNOT_EMIT = "Cannot emit this branch instruction due to previous errors. ";
	private static final String CANNOT_CREATE_BRANCH_INSTR = "Cannot create new branch instruction due to previous errors.";
	private static final String CANNOT_MAKE_UNCONDITIONAL_TO_UNCONDITIONAL = "The branch instruction is already unconditional.";
	private static final String CANNOT_MAKE_UNCONDITIONAL_FOR_DISSIMILAR_OPERANDS = "This conditional branch cannot be simplified to an unconditional branch since the operands are not identical.";
	private static final String CANNOT_MAKE_UNCONDITIONAL_FOR_INVALID_SUCCESSOR = "This conditional branch cannot be made unconditional since the provided successor is not a child of this branch.";
	
	protected  BranchInst(CompilationContext compilationContext, List<Value> operandList , BasicBlock parent){
		super(InstructionID.BRANCH_INST, Type.getVoidType(compilationContext), operandList, parent);
	}

	public boolean isUnconditional() {
		return getNumOperands() == 1; 
	}

	public boolean isConditional() {
		return getNumOperands() == 3; 
	}

	public Value getCondition() throws InstructionDetailAccessException {
		if(isUnconditional())
			throw new InstructionDetailAccessException(InstructionDetailAccessException.BRANCH_INST_CANNOT_GET_CONDITION_FOR_UNCONDITIONAL_BRANCH);

		return getOperand(0);   // The last value is the condition of type i1
	}

	public void setCondition(Value condition) throws InstructionUpdateException {
		// If this is an unconditional branch, we cannot set the condition
		if(isUnconditional())
			throw new InstructionUpdateException(InstructionUpdateException.BRANCH_INST_CANNOT_SET_CONDITION_FOR_UNCONDITIONAL_BRANCH);

		// The type of the condition must be of type i1
		Type typeOfCondition = condition.getType();
		if(!typeOfCondition.isInt1Type())
			throw new InstructionUpdateException("The type of the condition for the conditional branch must be i1.");

		setOperand(0, condition);
	}

	@Override
	public int getNumSuccessors() { 
		if(isConditional())
			return 2;        // Conditional branch will have at the most 2 successors

		return 1;            // Unconditional branch will have at the most 1 successor
	}

	@Override
	public BasicBlock getSuccessor(int index) throws InstructionDetailAccessException {
		if(isUnconditional()){
			if(index != 0)
				throw new InstructionDetailAccessException(
						InstructionDetailAccessException.INVALID_INDEX_FOR_SUCCESSOR, getInstructionName(), 
						": " + index);
			return trueBB;  // The only successor of an unconditional branch.
		}

		// This is a conditional branch
		if(!(index == 1 || index == 2))
			throw new InstructionDetailAccessException(
					InstructionDetailAccessException.INVALID_INDEX_FOR_SUCCESSOR, getInstructionName(), 
					": " + index);
		if(index == 1)
			return trueBB; 
		else
			return falseBB;
	}

	@Override
	public boolean replaceSuccessorWith(BasicBlock successor,
			BasicBlock newSuccessor) throws InstructionUpdateException, InstructionDetailAccessException {

		if(isUnconditional()){
			if(successor == trueBB){
				setSuccessor(0, newSuccessor, false);
			}
			else{
				LOG.warn(SUCCESSOR_MISMATCH_IN_UNCONDITIONAL_BRANCH);
				return false;
			}
		}
		else{
			if(successor == trueBB){
				setSuccessor(1, newSuccessor, false);
			}
			else if(successor == falseBB){
				setSuccessor(2, newSuccessor, false);
			}
			else{
				LOG.warn(SUCCESSOR_MISMATCH_IN_CONDITIONAL_BRANCH);
				return false;
			}
		}

		return true;
	}

	@Override
	public void setSuccessor(int index, BasicBlock newSuccessor, boolean setOprnd)  throws InstructionUpdateException  {
		Value value = null;
		if(newSuccessor != null)
			value = newSuccessor;
		
		if(isUnconditional()){
			if(index != 0){
				LOG.error(InstructionUpdateException.INVALID_INDEX_FOR_SUCCESSOR +  getInstructionName() +  
						": " + index);
				
				throw new InstructionUpdateException(
						InstructionUpdateException.INVALID_INDEX_FOR_SUCCESSOR, getInstructionName(), 
						": " + index);
			}

			trueBB = newSuccessor;

			if(setOprnd)
				setOperand(0, value);  // Set the only successor of an unconditional branch.

			return;
		}

		// This is a conditional branch
		if(!(index == 1 || index == 2)){			
			LOG.error(InstructionUpdateException.INVALID_INDEX_FOR_SUCCESSOR +  getInstructionName() +  
					": " + index);
			
			throw new InstructionUpdateException(
					InstructionUpdateException.INVALID_INDEX_FOR_SUCCESSOR, getInstructionName(), 
					": " + index);
		}

		if(index == 1){
			trueBB = newSuccessor;
		}
		else{ 
			falseBB = newSuccessor;
		}

		if(setOprnd){
			setOperand(index, value);  // Set the only successor of an unconditional branch.
		}
	}

	@Override
	public boolean removeSuccessor(BasicBlock successor) {
		try{
			if(isUnconditional()){
				// Since this is unconditional branch, replace this entire instruction with 
				// an unreachable instruction
				if(getSuccessor(0) != successor){
					LOG.warn(SUCCESSOR_MISMATCH_IN_UNCONDITIONAL_BRANCH);
					return false;
				}

				BasicBlock parentBasicBlock = getParent();
				parentBasicBlock.removeInstruction(this);

				UnreachableInstr unreachableInstr = UnreachableInstr.create(parentBasicBlock);
				parentBasicBlock.addInstruction(unreachableInstr);
				return true;
			}
			else{
				// This conditional block should be made unconditional
				return makeConditionalToUnconditional(successor);
			}
		}
		catch(InstructionDetailAccessException e){
			LOG.error(CANNOT_REMOVE_SUCCESSOR + ":" + e.getMessage());
		} 
		catch (InstructionCreationException e) {
			LOG.error(CANNOT_REMOVE_SUCCESSOR + ":" + e.getMessage());
		} catch (InstructionUpdateException e) {
			LOG.error(CANNOT_REMOVE_SUCCESSOR + ":" + e.getMessage());
		}
		
		return false;
	}

	@Override
	public String getInstructionName(){
		if(isUnconditional()){
			return UNCONDITIONAL_BRANCH;
		}

		return CONDITIONAL_BRANCH;
	}

	/* Swaps the successors of the branch instruction. This also swaps any
	   branch weight metadata associated with the instruction so that it
	   continues to map correctly to each operand.
	 */
	
	public void swapSuccessors() throws InstructionUpdateException {
		if(isUnconditional()){
			LOG.error(InstructionUpdateException.BRANCH_INST_CANNOT_SWAP_SUCCESSORS_FOR_UNCONDITIONAL_BRANCH);
			
			throw new InstructionUpdateException(
					InstructionUpdateException.BRANCH_INST_CANNOT_SWAP_SUCCESSORS_FOR_UNCONDITIONAL_BRANCH);
		}

		BasicBlock falseSuccessor = (BasicBlock)getOperand(1);   // False successor
		BasicBlock trueSuccessor = (BasicBlock)getOperand(2);    // True successor

		setOperand(1, trueSuccessor);
		setOperand(1, falseSuccessor);
	}

	@Override
	public String emit(){
		String description = "";
		try {
			if(isUnconditional()){
				BasicBlock onlySuccessor;

				onlySuccessor = getSuccessor(0);
				Value val = onlySuccessor;
				String name = LLVMIREmitter.getInstance().getValidName(val);
				if(name == null){
					name = "undef";
				}
				description = "br label " + name;
			}
			else{
				BasicBlock trueSuccessor = getSuccessor(1);
				BasicBlock falseSuccessor = getSuccessor(2);

				String trueName = LLVMIREmitter.getInstance().getValidName(trueSuccessor);
				String falseName = LLVMIREmitter.getInstance().getValidName(falseSuccessor);

				Value condition = getOperand(0);

				String conditionName = null;

				if(condition instanceof Constant){
					Constant con = (Constant) condition;
					if(con.isTrue()){
						conditionName = "true";
					}
					else if(con.isFalse()){
						conditionName = "false";
					}
				}
				else{
					conditionName = LLVMIREmitter.getInstance().getValidName(condition);
				}

				if(conditionName == null)
					conditionName = "undef";

				description = "br " + condition.getType() + " " + conditionName +  ", label " 
							 + trueName +  ", label " + falseName;
			}
		} 
		catch (InstructionDetailAccessException e) {
			LOG.error(CANNOT_EMIT + ":" + e.getMessage());
		}

		return description;
	}

	/**
	 * Creates an unconditional branch instruction.
	 */
	public static BranchInst create(BasicBlock ifTrue, BasicBlock parent, CompilationContext compilationContext) {
		
		List<Value> operandList = new ArrayList<Value>();
		if(ifTrue != null)
			operandList.add(ifTrue);
		else
			operandList.add(null);

		BranchInst brInstr = null;
		try {
			brInstr = new BranchInst(compilationContext, operandList, parent);
			brInstr.setSuccessor(0, ifTrue, false);
		} catch (InstructionUpdateException e) {
			LOG.error(CANNOT_CREATE_BRANCH_INSTR + e.getMessage());
		}
		return brInstr;

	}
	/**
	 * Creates an conditional branch instruction.
	 */
	
	public static BranchInst create(BasicBlock ifTrue, BasicBlock ifFalse,
			Value condition, BasicBlock parent, CompilationContext compilationContext) throws InstructionCreationException {
		// This is a conditional branch

		if(!(condition.getType().isInt1Type())){
			LOG.error(InstructionCreationException.CONDITION_FOR_CONDITIONAL_BRANCH_MUST_BE_OF_TYPE_I1);
			throw new InstructionCreationException(
					InstructionCreationException.CONDITION_FOR_CONDITIONAL_BRANCH_MUST_BE_OF_TYPE_I1);
		}

		List<Value> operandList = new ArrayList<Value>(3);
		operandList.add(condition);
		if(ifTrue != null)
			operandList.add(ifTrue);
		else
			operandList.add(null);
		if(ifFalse != null)
			operandList.add(ifFalse);
		else
			operandList.add(null);

		BranchInst brInstr = new BranchInst(compilationContext, operandList, parent);
		try {
			brInstr.setSuccessor(1, ifTrue, false);
			brInstr.setSuccessor(2, ifFalse, false);
		} 
		catch (InstructionUpdateException e) {
			LOG.error(CANNOT_CREATE_BRANCH_INSTR + e.getMessage());
		}

		return brInstr;
	}

	public BasicBlock getNodeForTruePath(){
		return trueBB;
	}

	public BasicBlock getNodeForFalsePath(){
		return falseBB;
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
			Vector<Value> ssaWorkList,
			Vector<BasicBlock> cfgWorkList) {

		BasicBlock parentBB = getParent();

		if(isUnconditional()){
			// The only successor is reachable
			markAllSuccessorsExecutable(parentBB, unreachableNodes, cfgWorkList);
		}
		else{
			try{
				Value branchInstValue = getCondition();
				LatticeValue latVal = 
					tempVsLatticeValue.get(branchInstValue);
				if(latVal.getType() != LatticeValue.CONSTANT){
					markAllSuccessorsExecutable(parentBB, unreachableNodes, cfgWorkList);
					return null;
				}

				Constant constVal = latVal.getConstantValue();
				BasicBlock executableNode = null;
				if(!constVal.isZero()){
					// True path is executable
					executableNode = getNodeForTruePath();
				}
				else {
					// False path is executable
					executableNode = getNodeForFalsePath();
				}

				if(unreachableNodes.contains(executableNode)) {
					if(!cfgWorkList.contains(executableNode))
						cfgWorkList.addElement((BasicBlock)executableNode);
				}

				unreachableNodes.remove(executableNode);
			}
			catch(InstructionDetailAccessException idae){
				LOG.error("Visit for SCCP failed for this branch instruction: " + idae.getMessage());
			}
		}

		return null;
	}

	@Override
	public boolean canBeHoistedOrSank() {
		// Cannot be moved out of a loop
		return false;
	}

	@Override
	public Constant foldIfPossible() {
		return null;
	}

	public boolean ifHasSameOperandsMakeConditionalToUnconditional() throws InstructionUpdateException, InstructionDetailAccessException {
		if(!isConditional()){
			LOG.warn(CANNOT_MAKE_UNCONDITIONAL_TO_UNCONDITIONAL);
			return false;
		}
		
		if(getOperand(1) == getOperand(2)) {
			BasicBlock sucessorBB = getSuccessor(1);
			this.setOperand(0, getOperand(1));	
			this.removeOperandAt(1);
			setSuccessor(0,sucessorBB, false);
			return true;
		}
		
		LOG.warn(CANNOT_MAKE_UNCONDITIONAL_FOR_DISSIMILAR_OPERANDS);
		return false;
	}

	private boolean makeConditionalToUnconditional(BasicBlock successorToBeRemoved) 
			throws InstructionDetailAccessException,
			InstructionUpdateException {
		
		int index = -1;
		BasicBlock remainingLiveBlock = null;
		
		if(getSuccessor(1) == successorToBeRemoved){
			index = 1;
			remainingLiveBlock = getSuccessor(2);
		}
		else if(getSuccessor(2) == successorToBeRemoved){
			index = 2;
			remainingLiveBlock = getSuccessor(1);
		}
		
		if(index == -1){
			LOG.warn(CANNOT_MAKE_UNCONDITIONAL_FOR_INVALID_SUCCESSOR);
			return false;
		}
		
		this.setOperand(0, remainingLiveBlock);
		this.removeOperandAt(1);
		this.removeOperandAt(1);
		setSuccessor(0, remainingLiveBlock, false);
		return true;
		
	}
}

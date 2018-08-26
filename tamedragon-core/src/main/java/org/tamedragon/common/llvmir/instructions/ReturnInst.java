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
import org.tamedragon.common.llvmir.types.Type;
import org.tamedragon.common.llvmir.types.Value;
import org.tamedragon.common.llvmir.utils.LLVMIREmitter;

/**
 * This class represents LLVM's PhiNode instruction
 * @author ipsg
 *
 */
public class ReturnInst extends TerminatorInst {

	private static final String RETURN_INST = "return instruction";

	protected  ReturnInst(CompilationContext compilationContext, List<Value> operandList, BasicBlock parent){
		super(InstructionID.RETURN_INST, Type.getVoidType(compilationContext), operandList, parent);
	}

	// Convenience accessor. Returns null if there is no return value.
	public Value getReturnValue() {
		return getNumOperands() != 0 ? getOperand(0) : null;
	}

	@Override
	public int getNumSuccessors() {
		return 0; 
	}

	@Override
	public void setSuccessor(int index, BasicBlock newSuccessor, boolean setOprnd) throws InstructionUpdateException{
		throw new InstructionUpdateException(InstructionUpdateException.RETURN_INST_CANNOT_SET_SUCCESSOR);
	}

	@Override
	public BasicBlock getSuccessor(int index) throws InstructionDetailAccessException {
		throw new InstructionDetailAccessException(InstructionDetailAccessException.RETURN_INST_CANNOT_HAVE_SUCCESSORS);
	}

	@Override
	public String getInstructionName(){
		return RETURN_INST;
	}

	@Override
	public String emit(){
		Value operandVal = getOperand(0);
		String operandValName = LLVMIREmitter.getInstance().getValidName(operandVal);
		if(operandValName != null && operandValName.length() != 0)
			return "ret " + operandVal.getType() + " " + operandValName;
		else
			return "ret " + operandVal.getType();
	}

	public static ReturnInst create(CompilationContext compilationContext, Value retValue, BasicBlock parent)
	throws InstructionCreationException{
		// Ensure that the return value is either a null or a first class type
		if(retValue != null){
			if(!(retValue.getType().isFirstClassType() || retValue.getType().isVoidType()))
				throw new InstructionCreationException(
						InstructionCreationException.RETURN_INST_MUST_RETURN_FIRST_CLASS_TYPE_OR_VOID);
		}
		else{
			// Return value is null; lets regards it as a void value
			retValue = new Value(Type.getVoidType(compilationContext));
		}

		List<Value> operandList = new ArrayList<Value>();
		operandList.add(retValue);
		return new ReturnInst(compilationContext, operandList, parent);
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
		// TODO : Implement this later
		return null;
	}

	@Override
	public boolean canBeHoistedOrSank() {
		return false;
	}

	@Override
	public Constant foldIfPossible() {
		//TODO Cannot be folded?
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

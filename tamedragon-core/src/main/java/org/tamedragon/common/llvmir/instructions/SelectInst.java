package org.tamedragon.common.llvmir.instructions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;

import org.tamedragon.common.LatticeValue;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionCreationException;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionUpdateException;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.CompilationContext;
import org.tamedragon.common.llvmir.types.Constant;
import org.tamedragon.common.llvmir.types.Type;
import org.tamedragon.common.llvmir.types.Value;
import org.tamedragon.common.llvmir.utils.LLVMIREmitter;

/**
 * This instruction compares its operands according to the predicate given
   to the constructor. It only operates on integers or pointers. The operands
   must be identical types.
   @brief Represent an integer comparison operator.
 *
 */
public class SelectInst extends Instruction{

	protected SelectInst(Type type, List<Value> operandList,  String name, BasicBlock parent) {
		super(InstructionID.SELECT_INST, type, operandList, parent);
		setName(name);
	}

	public Value getCondition()  { return getOperand(0); }
	public Value getTrueValue()  { return getOperand(1); }
	public Value getFalseValue()  { return getOperand(2); }
	
	public void setCondition(Value value) throws InstructionUpdateException {
		if(value == null){
			throw new InstructionUpdateException(InstructionUpdateException.CONDITION_TYPE_CANNOT_BE_NULL_FOR_SELECT);
		}
		if(value.getType() != Type.getInt1Type(value.getContext(), false)){
			throw new InstructionUpdateException(InstructionUpdateException.CONDITION_TYPE_NOT_I1_FOR_SELECT);
		}
		
		setOperand(0, value);
	}
	
	public void setSelectionValue(Value value, boolean isForTrueValue) throws InstructionUpdateException {
		if(value == null){
			throw new InstructionUpdateException(InstructionUpdateException.SELECT_VALUE_CANNOT_BE_NULL_FOR_SELECT);
		}
		
		Value otherValue = null;
		if(isForTrueValue)
			otherValue = getOperand(2);
		else
			otherValue = getOperand(1);
		
		if(value.getType() != otherValue.getType()){
			throw new InstructionUpdateException(InstructionUpdateException.INCOMPATIBLE_SELECT_TYPE_FOR_SELECT);
		}
		
		if(isForTrueValue)
			setOperand(1, value);
		else
			setOperand(2, value);
	}
	

	/**
	 * Construct a select instruction, given the condition, and the two values 
	 * to be selected.  
	 */
	public static SelectInst create(Value condition, Value first, Value second, String name, BasicBlock parent)
	throws InstructionCreationException{
		
		if(first == null || second == null)
			throw new InstructionCreationException(InstructionCreationException.SELECTION_VALUES_CANNOT_BE_NULL);
		else if(condition == null)
			throw new InstructionCreationException(InstructionCreationException.CONDITION_CODE_CANNOT_BE_NULL);

		CompilationContext compilationContext = first.getContext();
		
		if(condition.getType()  != Type.getInt1Type(compilationContext, false))
			throw new InstructionCreationException(InstructionCreationException.INVALID_CONDITION_TYPE_FOR_SELECT_INSTR);

		Type firstSelectType = first.getType();
		Type secondSelectType = second.getType();

		if(firstSelectType != secondSelectType){
			throw new InstructionCreationException(InstructionCreationException.TYPE_MISMATCH_IN_SELECTION_INSTR);
		}

		List<Value> operandList = new ArrayList<Value>();
		operandList.add(condition);
		operandList.add(first);
		operandList.add(second);
		SelectInst selectInst = new SelectInst(firstSelectType, 
				operandList, name, parent);

		return selectInst; 
	}

	@Override
	public String emit(){
		Value condition = getOperand(0);
		String conditionName = LLVMIREmitter.getInstance().getValidName(condition);
		String first = LLVMIREmitter.getInstance().getValidName(getOperand(1));
		String second = LLVMIREmitter.getInstance().getValidName(getOperand(2));
		String name = LLVMIREmitter.getInstance().getValidName(this); 
		Type selectedType = getOperand(1).getType();
		String description  = name + " = select " + 
				condition.getType() + " " + conditionName + ", " 
				+ selectedType + " " + first + ", " 
				+ selectedType + " " + second ; 

		return description;
	}

	@Override
	public boolean isCastInstruction() {
		return false;
	}

	@Override
	public boolean definesNewValue() {
		return true;
	}

	@Override
	public boolean isTerminatorInstruction() {
		return false;
	}

	@Override
	public boolean isCritical() {
		return false;
	}

	@Override
	public LatticeValue visitForSCCP(LatticeValue latticeValueBeforeModelling,
			HashSet<BasicBlock> unreachableNodes,
			HashMap<Value, LatticeValue> tempVsLatticeValue,
	        Vector<Value> ssaWorkList,
			Vector<BasicBlock> cfgWorkList) {
		// If this select returns a struct, just mark the result overdefined.
		if (getType().isStructType()){
			LatticeValue newLatticeValue = new LatticeValue();
			newLatticeValue.setType(LatticeValue.OVERDEFINED);
		}

		LatticeValue conditionValue = tempVsLatticeValue.get(getCondition());
		if (conditionValue.getType() == LatticeValue.UNDEFINED){
			return latticeValueBeforeModelling;
		}

		if(conditionValue.getType() == LatticeValue.CONSTANT){
			Constant constVal = conditionValue.getConstantValue();
			Value result = constVal.isZero() ? getFalseValue() : getTrueValue();
			LatticeValue newLatticeValue = tempVsLatticeValue.get(result);
			return newLatticeValue;
		}

		// Otherwise, the condition is overdefined or a constant we can't evaluate.
		// See if we can produce something better than overdefined based on the T/F
		// value.
		LatticeValue trueVal = tempVsLatticeValue.get(getTrueValue());
		LatticeValue falseVal =  tempVsLatticeValue.get(getFalseValue());

		// select ?, C, C -> C.
		if (trueVal.getType() == LatticeValue.CONSTANT && 
				falseVal.getType() == LatticeValue.CONSTANT){
			if(trueVal.getConstantValue() == falseVal.getConstantValue()){
				LatticeValue newLatticeValue = new LatticeValue();
				newLatticeValue.setConstantValue(trueVal.getConstantValue());
				return newLatticeValue;
			}
		}

		if (trueVal.getType() == LatticeValue.UNDEFINED){
			// select ?, undef, X -> X.
			return falseVal;
		}

		if (falseVal.getType() == LatticeValue.UNDEFINED){
			// select ?, X, undef -> X.
			return trueVal;
		}

		// Give up, mark as overdefined.
		LatticeValue newLatticeValue = new LatticeValue();
		newLatticeValue.setType(LatticeValue.OVERDEFINED);
		return newLatticeValue;
	}

	@Override
	public boolean canBeHoistedOrSank() {
		return true;
	}

	@Override
	public Constant foldIfPossible() {
		Value operand1 = getOperand(0); Value operand2 = getOperand(1); Value operand3 = getOperand(2);
		if(!(operand1.isAConstant() && operand2.isAConstant() && operand3.isAConstant())){
			return null;
		}

		Constant conditionConst = (Constant) operand1;
		if(conditionConst.isPositiveUnity()){
			return (Constant) operand2;
		}
		else{
			return (Constant) operand3;
		}
	}
}

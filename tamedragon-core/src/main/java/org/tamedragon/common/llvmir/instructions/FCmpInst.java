package org.tamedragon.common.llvmir.instructions;

import java.util.ArrayList;
import java.util.List;

import org.tamedragon.common.llvmir.instructions.exceptions.InstructionCreationException;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.CompilationContext;
import org.tamedragon.common.llvmir.types.Constant;
import org.tamedragon.common.llvmir.types.Type;
import org.tamedragon.common.llvmir.types.Value;
import org.tamedragon.common.llvmir.types.VectorType;
import org.tamedragon.common.llvmir.utils.LLVMIREmitter;

/**
 * This class represents LLVM's FCmp Instruction. Represents a floating point comparison operator.
 * @author ipsg
 *
 */
public class FCmpInst extends CmpInst{

	public FCmpInst(Type type, List<Value> operandList, Predicate predicate, String name, BasicBlock parent) {
		super(InstructionID.FCMP_INST, type, operandList, predicate, name, parent);
	}

	@Override
	/**
	 * @returns true if the predicate of this instruction is commutative.
	 * @brief Determine if this is a commutative predicate.
	 */
	public boolean isCommutative() {
		return isEquality() ||
		getPredicate() == Predicate.FCMP_FALSE ||
		getPredicate() == Predicate.FCMP_TRUE ||
		getPredicate() == Predicate.FCMP_ORD ||
		getPredicate() == Predicate.FCMP_UNO;
	}

	@Override
	/**
	 * @returns true if the predicate of this instruction is EQ or NE.
	 * @brief Determine if this is an equality predicate.
	 */
	public boolean isEquality() {
		return getPredicate() == Predicate.FCMP_OEQ || getPredicate() == Predicate.FCMP_ONE ||
		getPredicate() == Predicate.FCMP_UEQ || getPredicate() == Predicate.FCMP_UNE;
	}


	/**
	 * @returns true if the predicate is relational (not EQ or NE).
	 * @brief Determine if this a relational predicate.
	 */
	public boolean isRelational() { return !isEquality(); }

	//	/**
	//	 * TODO : Exchange the two operands to this instruction in such a way that it does
	//	   not modify the semantics of the instruction. The predicate value may be
	//	   changed to retain the same result if the predicate is order dependent
	//	   (e.g. ult).
	//	   @brief Swap operands and adjust predicate.
	//	 */
	//	@Override
	//	public void swapOperands() {
	//		// TODO Auto-generated method stub
	//	}

	/**
	 * Construct a compare instruction, given the opcode, the predicate and
	   the two operands.  
	 * @brief Create a FCmpInst
	 * @param predicate
	 * @param lhs
	 * @param rhs
	 * @param name
	 * @return FCmpInst
	 */
	public static FCmpInst create(Predicate predicate, Value lhs, Value rhs, String name, BasicBlock parent) throws InstructionCreationException{
		if(lhs == null || rhs == null)
			throw new InstructionCreationException(InstructionCreationException.LHS_AND_RHS_VALUE_CANNOT_BE_NULL);
		else if(predicate == null)
			throw new InstructionCreationException(InstructionCreationException.CONDITION_CODE_CANNOT_BE_NULL);

		if(predicate.getPredicateValAsInt() < 0 || predicate.getPredicateValAsInt() > 15)
			throw new InstructionCreationException(InstructionCreationException.INVALID_PREDICATE_VALUE_FOR__FCMP_INSTR);

		Type lhsType = lhs.getType();
		Type rhsType = rhs.getType();

		if(!lhsType.isFloatingPointType() && !lhsType.isVectorType()){
			throw new InstructionCreationException(InstructionCreationException.INVALID_TYPE_OF_VALUES_FOR_FCMP_INSTR);
		}
		if(!rhsType.isFloatingPointType() && !rhsType.isVectorType()){
			throw new InstructionCreationException(InstructionCreationException.INVALID_TYPE_OF_VALUES_FOR_FCMP_INSTR);
		}

		if(lhsType.isVectorType()){
			VectorType vectorType = (VectorType)lhsType;
			Type containedType = vectorType.getContainedType();
			if(!containedType.isFloatingPointType())
				throw new InstructionCreationException(InstructionCreationException.INVALID_TYPE_OF_VALUES_FOR_FCMP_INSTR);
		}

		if(rhsType.isVectorType()){
			VectorType vectorType = (VectorType)rhsType;
			Type containedType = vectorType.getContainedType();
			if(!containedType.isFloatingPointType())
				throw new InstructionCreationException(InstructionCreationException.INVALID_TYPE_OF_VALUES_FOR_ICMP_INSTR);
		}

		if(lhsType != rhsType)
			throw new InstructionCreationException(InstructionCreationException.BOTH_LHS_RHS_TYPE_SHOULD_BE_EQUAL_CMP_INSTR);

		CompilationContext compilationContext = lhsType.getCompilationContext();

		List<Value> operandList = new ArrayList<Value>();
		operandList.add(lhs);
		operandList.add(rhs);
		FCmpInst fCmpInst = new FCmpInst(Type.getInt1Type(compilationContext, false), operandList, predicate, name, parent);

		return fCmpInst; 
	}

	@Override
	public String emit(){
		Value lhsVal = getOperand(0);
		Value rhsVal = getOperand(1);
		String lhs = LLVMIREmitter.getInstance().getValidName(lhsVal);
		String rhs = LLVMIREmitter.getInstance().getValidName(rhsVal);
		String name = LLVMIREmitter.getInstance().getValidName(this);
		String description  = name + " = fcmp " + predicate.getOpCode() + " " + lhsVal.getType().toString(); 
		description += " " + lhs;

		description += ", " + rhs;
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
	public Constant foldIfPossible() {
		Value operand1 = getOperand(0); Value operand2 = getOperand(1);
		if(!(operand1.isAConstant() && operand2.isAConstant())){
			return null;
		}
		return Constant.getCompare(predicate, (Constant) operand1, (Constant) operand2);
	}
	
	/**
	 * Exchange the two operands to this instruction in such a way that it does
	   not modify the semantics of the instruction. The predicate value may be
	   changed to retain the same result if the predicate is order dependent
	   (e.g. ult).
	*/
	@Override
	public void swapOperands() {
		// TODO Auto-generated method stub
		
	}
}

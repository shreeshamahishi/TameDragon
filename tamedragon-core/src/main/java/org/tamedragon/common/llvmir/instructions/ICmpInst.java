package org.tamedragon.common.llvmir.instructions;

import java.util.ArrayList;
import java.util.List;

import org.tamedragon.common.llvmir.instructions.exceptions.InstructionCreationException;
import org.tamedragon.common.llvmir.math.APInt;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.CompilationContext;
import org.tamedragon.common.llvmir.types.Constant;
import org.tamedragon.common.llvmir.types.ConstantInt;
import org.tamedragon.common.llvmir.types.Type;
import org.tamedragon.common.llvmir.types.Value;
import org.tamedragon.common.llvmir.types.VectorType;
import org.tamedragon.common.llvmir.utils.LLVMIREmitter;

/**
 * This instruction compares its operands according to the predicate given
   to the constructor. It only operates on integers or pointers. The operands
   must be identical types.
   @brief Represent an integer comparison operator.
 *
 */
public class ICmpInst extends CmpInst{

	protected ICmpInst(Type type, List<Value> operandList,  Predicate predicate, String name, BasicBlock parent) {
		super(InstructionID.ICMP_INST, type, operandList, predicate, name, parent);
	}

	/**
	 * For example, EQ->EQ, SLE->SLE, UGT->SGT, etc.
	   @returns the predicate that would be the result if the operand were
	   regarded as signed.
	   @brief Return the signed version of the predicate
	 * @return Predicate
	 */
	Predicate getSignedPredicate() {
		return getSignedPredicate(getPredicate());
	}

	/**
	 * This is a static version that you can use without an instruction.
	   @brief Return the signed version of the predicate.
	 * @param pred
	 * @return
	 */
	public static  Predicate getSignedPredicate(Predicate pred) {
		switch (pred) {
		case ICMP_EQ: case ICMP_NE: 
		case ICMP_SGT: case ICMP_SLT: case ICMP_SGE: case ICMP_SLE: 
			return pred;
		case ICMP_UGT: return Predicate.ICMP_SGT;
		case ICMP_ULT: return Predicate.ICMP_SLT;
		case ICMP_UGE: return Predicate.ICMP_SGE;
		case ICMP_ULE: return Predicate.ICMP_SLE;
		default: return null;
		}
	}

	/**
	 * For example, EQ->EQ, SLE->ULE, UGT->UGT, etc.
	   @returns the predicate that would be the result if the operand were
	   regarded as unsigned.
	   @brief Return the unsigned version of the predicate
	 * @return
	 */
	Predicate getUnsignedPredicate() {
		return getUnsignedPredicate(getPredicate());
	}

	/**
	 * This is a static version that you can use without an instruction.
	   @brief Return the unsigned version of the predicate.
	 * @param pred
	 * @return
	 */
	public static Predicate getUnsignedPredicate(Predicate pred) {
		switch (pred) {
		case ICMP_EQ: case ICMP_NE: 
		case ICMP_UGT: case ICMP_ULT: case ICMP_UGE: case ICMP_ULE: 
			return pred;
		case ICMP_SGT: return Predicate.ICMP_UGT;
		case ICMP_SLT: return Predicate.ICMP_ULT;
		case ICMP_SGE: return Predicate.ICMP_UGE;
		case ICMP_SLE: return Predicate.ICMP_ULE;
		default: return null;
		}
	}

	/**
	 * isEquality - Return true if this predicate is either EQ or NE.  This also
	   tests for commutativity.
	 * @param P
	 * @return
	 */
	public static boolean isEquality(Predicate P) {
		return P == Predicate.ICMP_EQ || P == Predicate.ICMP_NE;
	}

	/**
	 * isEquality - Return true if this predicate is either EQ or NE.  This also
	   tests for commutativity.
	 */
	@Override
	public boolean isEquality(){
		return isEquality(getPredicate());
	}

	/**
	 * @returns true if the predicate of this ICmpInst is commutative
	   @brief Determine if this relation is commutative.
	 */
	public boolean isCommutative() { return isEquality(); }

	/**
	 * isRelational - Return true if the predicate is relational (not EQ or NE).
	 * @return boolean
	 */
	public boolean isRelational(){ return !isEquality(); }

	/**
	 * 	isRelational - Return true if the predicate is relational (not EQ or NE).
	 * @param P
	 * @return
	 */
	public static boolean isRelational(Predicate P) { return !isEquality(P); }



	/**
	 * Construct a compare instruction, given the opcode, the predicate and
	   the two operands.  
	 * @brief Create a ICmpInst
	 * @param predicate
	 * @param lhs
	 * @param rhs
	 * @param name
	 * @return ICmpInst
	 */
	public static ICmpInst create(Predicate predicate, Value lhs, Value rhs, String name, BasicBlock parent) throws InstructionCreationException{
		if(lhs == null || rhs == null)
			throw new InstructionCreationException(InstructionCreationException.LHS_AND_RHS_VALUE_CANNOT_BE_NULL);
		else if(predicate == null)
			throw new InstructionCreationException(InstructionCreationException.CONDITION_CODE_CANNOT_BE_NULL);

		CompilationContext compilationContext = lhs.getContext();
		List<Value> operandList = new ArrayList<Value>();
		
		if(predicate.getPredicateValAsInt() < 32 || predicate.getPredicateValAsInt() > 41)
			throw new InstructionCreationException(InstructionCreationException.INVALID_PREDICATE_VALUE_FOR__ICMP_INSTR);
		
		if(lhs != null && rhs != null){
			Type lhsType = lhs.getType();
			Type rhsType = rhs.getType();
			
			if(!lhsType.isIntegerType() && !lhsType.isPointerType() && !lhsType.isVectorType()){
				throw new InstructionCreationException(InstructionCreationException.INVALID_TYPE_OF_VALUES_FOR_ICMP_INSTR);
			}
			if(!rhsType.isIntegerType() && !rhsType.isPointerType() && !rhsType.isVectorType()){
				throw new InstructionCreationException(InstructionCreationException.INVALID_TYPE_OF_VALUES_FOR_ICMP_INSTR);
			}
			
			if(lhsType.isVectorType()){
				VectorType vectorType = (VectorType)lhsType;
				Type containedType = vectorType.getContainedType();
				if(!containedType.isIntegerType() && !containedType.isPointerType())
					throw new InstructionCreationException(InstructionCreationException.INVALID_TYPE_OF_VALUES_FOR_ICMP_INSTR);
			}
			
			if(rhsType.isVectorType()){
				VectorType vectorType = (VectorType)rhsType;
				Type containedType = vectorType.getContainedType();
				if(!containedType.isIntegerType() && !containedType.isPointerType())
					throw new InstructionCreationException(InstructionCreationException.INVALID_TYPE_OF_VALUES_FOR_ICMP_INSTR);
			}
			
			if((rhs instanceof ConstantInt) && lhsType.isPointerType()){
				ConstantInt constantInt = (ConstantInt)rhs;
				APInt apInt = constantInt.getApInt();
				if(apInt.isNullValue()){
					// In this case, we are checking for a null value;
					// for example, in LLVM this will be expressed as:
					// %2 = icmp ne *i32 %3, null
					
					rhsType = lhsType;
					rhs = new Value(rhsType);
					rhs.setName("null");
				}
			}
			
			if(lhsType != rhsType)
				throw new InstructionCreationException(InstructionCreationException.BOTH_LHS_RHS_TYPE_SHOULD_BE_EQUAL_CMP_INSTR);
			
			operandList.add(lhs);
			operandList.add(rhs);
		}
		
		ICmpInst iCmpInst = new ICmpInst(Type.getInt1Type(compilationContext, false), operandList, predicate, name, parent);
		
		return iCmpInst; 
	}

	@Override
	public String emit(){
		Value lhsVal = getOperand(0);
		Value rhsVal = getOperand(1);
		String lhs = LLVMIREmitter.getInstance().getValidName(lhsVal);
		String rhs = LLVMIREmitter.getInstance().getValidName(rhsVal);
		String name = LLVMIREmitter.getInstance().getValidName(this);
		String description  = name + " = icmp " + predicate.getOpCode() + " " + lhsVal.getType().toString(); 
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

	@Override
	public void swapOperands() {
		// TODO Auto-generated method stub
		
	}
}

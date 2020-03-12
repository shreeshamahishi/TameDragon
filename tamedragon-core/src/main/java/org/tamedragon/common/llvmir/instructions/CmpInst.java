package org.tamedragon.common.llvmir.instructions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;

import org.tamedragon.common.LatticeValue;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.Constant;
import org.tamedragon.common.llvmir.types.Type;
import org.tamedragon.common.llvmir.types.Value;
import org.tamedragon.common.llvmir.types.VectorType;
import org.tamedragon.common.llvmir.types.exceptions.TypeCreationException;
import org.tamedragon.common.llvmir.types.Type.TypeID;

/**
 * This abstract class is used to encapsulate all the common features of a LLVM's Compare Instructions.
 * @author ipsg
 *
 */
public abstract class CmpInst extends Instruction{
	protected Predicate predicate;

	public CmpInst(InstructionID instrID, Type type, List<Value> operandList, Predicate predicate, String name, BasicBlock parent) {
		super(instrID, type, operandList, parent);

		this.predicate = predicate;
		setName(name);
	}

	public Predicate getPredicate() {
		return predicate;
	}

	public void setPredicate(Predicate predicate) {
		this.predicate = predicate;
	}

	/**
	 * This enumeration lists the possible predicates for CmpInst subclasses.
	   Values in the range 0-31 are reserved for FCmpInst, while values in the
	   range 32-64 are reserved for ICmpInst. This is necessary to ensure the
	   predicate values are not overlapping between the classes.
	 */
	public enum Predicate {
		// Opcode              U L G E    Intuitive operation
		FCMP_FALSE(0, "false"),  ///< 0 0 0 0    Always false (always folded)
		FCMP_OEQ(1, "oeq"),  ///< 0 0 0 1    True if ordered and equal
		FCMP_OGT(2, "ogt"),  ///< 0 0 1 0    True if ordered and greater than
		FCMP_OGE(3, "oge"),  ///< 0 0 1 1    True if ordered and greater than or equal
		FCMP_OLT(4, "olt"),  ///< 0 1 0 0    True if ordered and less than
		FCMP_OLE(5, "ole"),  ///< 0 1 0 1    True if ordered and less than or equal
		FCMP_ONE(6, "one"),  ///< 0 1 1 0    True if ordered and operands are unequal
		FCMP_ORD(7, "ord"),  ///< 0 1 1 1    True if ordered (no nans)
		FCMP_UNO(8, "uno"),  ///< 1 0 0 0    True if unordered: isnan(X) | isnan(Y)
		FCMP_UEQ(9, "ueq"),  ///< 1 0 0 1    True if unordered or equal
		FCMP_UGT(10, "ugt"),  ///< 1 0 1 0    True if unordered or greater than
		FCMP_UGE(11, "uge"),  ///< 1 0 1 1    True if unordered, greater than, or equal
		FCMP_ULT(12, "ult"),  ///< 1 1 0 0    True if unordered or less than
		FCMP_ULE(13, "ule"),  ///< 1 1 0 1    True if unordered, less than, or equal
		FCMP_UNE(14, "une"),  ///< 1 1 1 0    True if unordered or not equal
		FCMP_TRUE(15, "true"),  ///< 1 1 1 1    Always true (always folded)
		FIRST_FCMP_PREDICATE(FCMP_FALSE.getPredicateValAsInt(), ""),
		LAST_FCMP_PREDICATE(FCMP_TRUE.getPredicateValAsInt(), ""),
		BAD_FCMP_PREDICATE(FCMP_TRUE.getPredicateValAsInt() + 1, ""),
		ICMP_EQ(32, "eq"),  ///< equal
		ICMP_NE(33, "ne"),  ///< not equal
		ICMP_UGT(34, "ugt"),  ///< unsigned greater than
		ICMP_UGE(35, "uge"),  ///< unsigned greater or equal
		ICMP_ULT(36, "ult"),  ///< unsigned less than
		ICMP_ULE(37, "ule"),  ///< unsigned less or equal
		ICMP_SGT(38, "sgt"),  ///< signed greater than
		ICMP_SGE(39, "sge"),  ///< signed greater or equal
		ICMP_SLT(40, "slt"),  ///< signed less than
		ICMP_SLE(41, "sle"),  ///< signed less or equal
		FIRST_ICMP_PREDICATE(ICMP_EQ.getPredicateValAsInt(), ""),
		LAST_ICMP_PREDICATE(ICMP_SLE.getPredicateValAsInt(), ""),
		BAD_ICMP_PREDICATE(ICMP_SLE.getPredicateValAsInt() + 1, "");

		private int predicateValue;
		private String opCode;

		private Predicate(int predicateValue, String opCode){
			this.predicateValue = predicateValue;
			this.opCode = opCode;
		}

		public int getPredicateValAsInt(){
			return predicateValue;
		}

		public String getOpCode() {
			return opCode;
		}
	}

	public static boolean isFPPredicate(Predicate P) {
		return P.getPredicateValAsInt() >= Predicate.FIRST_FCMP_PREDICATE.getPredicateValAsInt() && P.getPredicateValAsInt() <= Predicate.LAST_FCMP_PREDICATE.getPredicateValAsInt();
	}


	public static boolean isIntPredicate(Predicate P) {
		return P.getPredicateValAsInt() >= Predicate.FIRST_ICMP_PREDICATE.getPredicateValAsInt() && P.getPredicateValAsInt() <= Predicate.LAST_ICMP_PREDICATE.getPredicateValAsInt();
	}

	public boolean isFPPredicate() { return isFPPredicate((getPredicate())); }
	public boolean isIntPredicate() { return isIntPredicate(getPredicate()); }

	/**
	 * 	 For example, EQ -> NE, UGT -> ULE, SLT -> SGE,
	     OEQ -> UNE, UGT -> OLE, OLT -> UGE, etc.
	 	 @returns the inverse predicate for the instruction's current predicate.
	 	 @brief Return the inverse of the instruction's predicate.
	 * 	 @return Predicate
	 */
	public Predicate getInversePredicate(){
		return getInversePredicate(getPredicate());
	}

	/*
	 * 	   For example, EQ -> NE, UGT -> ULE, SLT -> SGE,
	                OEQ -> UNE, UGT -> OLE, OLT -> UGE, etc.
	   @returns the inverse predicate for predicate provided in \p pred.
	   @brief Return the inverse of a given predicate
	 */
	public static Predicate getInversePredicate(Predicate pred) {
		switch (pred) {
		case ICMP_EQ: return Predicate.ICMP_NE;
		case ICMP_NE: return Predicate.ICMP_EQ;
		case ICMP_UGT: return Predicate.ICMP_ULE;
		case ICMP_ULT: return Predicate.ICMP_UGE;
		case ICMP_UGE: return Predicate.ICMP_ULT;
		case ICMP_ULE: return Predicate.ICMP_UGT;
		case ICMP_SGT: return Predicate.ICMP_SLE;
		case ICMP_SLT: return Predicate.ICMP_SGE;
		case ICMP_SGE: return Predicate.ICMP_SLT;
		case ICMP_SLE: return Predicate.ICMP_SGT;

		case FCMP_OEQ: return Predicate.FCMP_UNE;
		case FCMP_ONE: return Predicate.FCMP_UEQ;
		case FCMP_OGT: return Predicate.FCMP_ULE;
		case FCMP_OLT: return Predicate.FCMP_UGE;
		case FCMP_OGE: return Predicate.FCMP_ULT;
		case FCMP_OLE: return Predicate.FCMP_UGT;
		case FCMP_UEQ: return Predicate.FCMP_ONE;
		case FCMP_UNE: return Predicate.FCMP_OEQ;
		case FCMP_UGT: return Predicate.FCMP_OLE;
		case FCMP_ULT: return Predicate.FCMP_OGE;
		case FCMP_UGE: return Predicate.FCMP_OLT;
		case FCMP_ULE: return Predicate.FCMP_OGT;
		case FCMP_ORD: return Predicate.FCMP_UNO;
		case FCMP_UNO: return Predicate.FCMP_ORD;
		case FCMP_TRUE: return Predicate.FCMP_FALSE;
		case FCMP_FALSE: return Predicate.FCMP_TRUE;

		default: return null;
		}
	}

	/**
	 * For example, EQ->EQ, SLE->SGE, ULT->UGT,
	                OEQ->OEQ, ULE->UGE, OLT->OGT, etc.
	   @returns the predicate that would be the result of exchanging the two
	   operands of the CmpInst instruction without changing the result
	   produced.
	   @brief Return the predicate as if the operands were swapped
	 * @return
	 */
	public Predicate getSwappedPredicate() {
		return getSwappedPredicate(getPredicate());
	}

	/**
	 * This is a static version that you can use without an instruction
	 * available.
	 * @brief Return the predicate as if the operands were swapped.
	 * @param predicate2
	 * @return
	 */
	public static Predicate getSwappedPredicate(Predicate pred) {
		switch (pred) {
		case ICMP_EQ: case ICMP_NE:
			return pred;
		case ICMP_SGT: return Predicate.ICMP_SLT;
		case ICMP_SLT: return Predicate.ICMP_SGT;
		case ICMP_SGE: return Predicate.ICMP_SLE;
		case ICMP_SLE: return Predicate.ICMP_SGE;
		case ICMP_UGT: return Predicate.ICMP_ULT;
		case ICMP_ULT: return Predicate.ICMP_UGT;
		case ICMP_UGE: return Predicate.ICMP_ULE;
		case ICMP_ULE: return Predicate.ICMP_UGE;

		case FCMP_FALSE: case FCMP_TRUE:
		case FCMP_OEQ: case FCMP_ONE:
		case FCMP_UEQ: case FCMP_UNE:
		case FCMP_ORD: case FCMP_UNO:
			return pred;
		case FCMP_OGT: return Predicate.FCMP_OLT;
		case FCMP_OLT: return Predicate.FCMP_OGT;
		case FCMP_OGE: return Predicate.FCMP_OLE;
		case FCMP_OLE: return Predicate.FCMP_OGE;
		case FCMP_UGT: return Predicate.FCMP_ULT;
		case FCMP_ULT: return Predicate.FCMP_UGT;
		case FCMP_UGE: return Predicate.FCMP_ULE;
		case FCMP_ULE: return Predicate.FCMP_UGE;
		default : return null;
		}
	}

	/**
	 * This is just a convenience that dispatches to the subclasses.
	   @brief Swap the operands and adjust predicate accordingly to retain
	   the same comparison.
	 */
	public abstract void swapOperands();

	/**
	 * This is just a convenience that dispatches to the subclasses.
	   @brief Determine if this CmpInst is commutative.
	 * @return
	 */
	public abstract boolean isCommutative();

	/**
	 * This is just a convenience that dispatches to the subclasses.
	   @brief Determine if this is an equals/not equals predicate.
	 * @return
	 */
	public abstract boolean isEquality() ;


	/**
	 * @returns true if the comparison is signed, false otherwise.
	   @brief Determine if this instruction is using a signed comparison.
	 * @return
	 */
	public boolean isSigned() {
		return isSigned(getPredicate());
	}

	/**
	 * @returns true if the predicate is signed, false otherwise.
	   @brief Determine if the predicate is an signed operation.
	 * @param predicate2
	 * @return
	 */
	public static boolean isSigned(Predicate predicate){
		switch (predicate) {
		case ICMP_SLT: case ICMP_SLE: case ICMP_SGT: 
		case ICMP_SGE: return true;
		default: return false;
		}
	}

	/**
	 * @returns true if the comparison is unsigned, false otherwise.
	   @brief Determine if this instruction is using an unsigned comparison.
	 * @return
	 */
	public boolean isUnsigned() {
		return isUnsigned(getPredicate());
	}

	public static boolean isUnsigned(Predicate predicate){
		switch (predicate) {
		case ICMP_ULT: case ICMP_ULE: case ICMP_UGT: 
		case ICMP_UGE: return true;
		default: return false;
		}
	}

	/**
	 * This is just a convenience.
	   @brief Determine if this is true when both operands are the same.
	 * @return
	 */
	public boolean isTrueWhenEqual() {
		return isTrueWhenEqual(getPredicate());
	}

	public static boolean isTrueWhenEqual(Predicate predicate) {
		switch(predicate) {
		case ICMP_EQ:   case ICMP_UGE: case ICMP_ULE: case ICMP_SGE: case ICMP_SLE:
		case FCMP_TRUE: case FCMP_UEQ: case FCMP_UGE: case FCMP_ULE: return true;
		default: return false;
		}
	}
	
	public static boolean isFalseWhenEqual(Predicate predicate) {
		switch(predicate) {
		case ICMP_NE:    case ICMP_UGT: case ICMP_ULT: case ICMP_SGT: case ICMP_SLT:
		case FCMP_FALSE: case FCMP_ONE: case FCMP_OGT: case FCMP_OLT: return true;
		default: return false;
		}
	}

	/**
	 * This is just a convenience.
	   @brief Determine if this is false when both operands are the same.
	 * @return
	 */
	public boolean isFalseWhenEqual() {
		return isFalseWhenEqual(getPredicate());
	}

	public static boolean isOrdered(Predicate predicate) {
		switch (predicate) {
		default: return false;
		case FCMP_OEQ: case FCMP_ONE: case FCMP_OGT:
		case FCMP_OLT: case FCMP_OGE: case FCMP_OLE:
		case FCMP_ORD: return true;
		}
	}

	public static boolean isUnordered(Predicate predicate) {
		switch (predicate) {
		default: return false;
		case FCMP_UEQ: case FCMP_UNE: case FCMP_UGT:
		case FCMP_ULT: case FCMP_UGE: case FCMP_ULE:
		case FCMP_UNO: return true;
		}
	}


	@Override
	public LatticeValue visitForSCCP(LatticeValue latticeValueBeforeModelling,
			HashSet<BasicBlock> unreachableNodes,
			HashMap<Value, LatticeValue> tempVsLatticeValue,
			Vector<Value> ssaWorkList,
			Vector<BasicBlock> cfgWorkList){


		if(latticeValueBeforeModelling.getType() == LatticeValue.OVERDEFINED)
			return latticeValueBeforeModelling;

		LatticeValue value1State = tempVsLatticeValue.get(getOperand(0));
		LatticeValue value2State = tempVsLatticeValue.get(getOperand(1));

		if (value1State.getType() == LatticeValue.CONSTANT && 
				value2State.getType() == LatticeValue.CONSTANT){
			Constant resultConstant = Constant.getCompare(getPredicate(),
					value1State.getConstantValue(),
					value2State.getConstantValue());
			LatticeValue newLatticeValue = new LatticeValue();
			newLatticeValue.setConstantValue(resultConstant);
			return newLatticeValue;

		}

		// If operands are still undefined, wait for it to resolve.
		if (value1State.getType() != LatticeValue.OVERDEFINED && 
				value1State.getType() != LatticeValue.OVERDEFINED)
			return latticeValueBeforeModelling;

		LatticeValue newLatticeValue = new LatticeValue();
		newLatticeValue.setType(LatticeValue.OVERDEFINED);
		return newLatticeValue;

	}

	@Override
	public boolean canBeHoistedOrSank() {
		return true;
	}

	public static Type makeCmpResultType(Type type) throws TypeCreationException {
		if(type.getTypeId() == TypeID.VECTOR_ID) {
			VectorType vt = (VectorType)type;
			return VectorType.getVectorType(type.getCompilationContext(), Type.getInt1Type(type.getCompilationContext(), false), 
					vt.getNumElements());
		}

		return Type.getInt1Type(type.getCompilationContext(), false);
	}

}

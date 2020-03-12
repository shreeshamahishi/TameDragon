package org.tamedragon.common.common.analysis;

import static org.tamedragon.common.llvmir.instructions.PatternMatch.*;
import static org.tamedragon.common.common.analysis.ValueTracking.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.tamedragon.common.DominatorTree;
import org.tamedragon.common.llvmir.instructions.BinaryOperator;
import org.tamedragon.common.llvmir.instructions.BinaryOperator.BinaryOperatorID;
import org.tamedragon.common.llvmir.instructions.CallInst;
import org.tamedragon.common.llvmir.instructions.CastInst;
import org.tamedragon.common.llvmir.instructions.CmpInst;
import org.tamedragon.common.llvmir.instructions.CmpInst.Predicate;
import org.tamedragon.common.llvmir.instructions.ExtractValueInst;
import org.tamedragon.common.llvmir.instructions.FCmpInst;
import org.tamedragon.common.llvmir.instructions.FastMathFlags;
import org.tamedragon.common.llvmir.instructions.GetElementPtrInst;
import org.tamedragon.common.llvmir.instructions.ICmpInst;
import org.tamedragon.common.llvmir.instructions.InsertValueInst;
import org.tamedragon.common.llvmir.instructions.Instruction;
import org.tamedragon.common.llvmir.instructions.Instruction.InstructionID;
import org.tamedragon.common.llvmir.instructions.PhiNode;
import org.tamedragon.common.llvmir.instructions.SelectInst;
import org.tamedragon.common.llvmir.instructions.SpecificValMatch;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionDetailAccessException;
import org.tamedragon.common.llvmir.irdata.DataLayout;
import org.tamedragon.common.llvmir.math.APInt;
import org.tamedragon.common.llvmir.types.APFloat;
import org.tamedragon.common.llvmir.types.Constant;
import org.tamedragon.common.llvmir.types.ConstantExpr;
import org.tamedragon.common.llvmir.types.ConstantInt;
import org.tamedragon.common.llvmir.types.Type;
import org.tamedragon.common.llvmir.types.Type.TypeID;
import org.tamedragon.common.llvmir.types.UndefValue;
import org.tamedragon.common.llvmir.types.Value;
import org.tamedragon.common.llvmir.types.VectorType;
import org.tamedragon.common.llvmir.types.exceptions.TypeCreationException;
import org.tamedragon.common.llvmir.utils.llvmGrammarParser.ptr_type_return;

public class SimplifyInstruction {

	class OrderedPair<F extends Value, S extends Value> {

		private F first;
		private S second;

		public OrderedPair(F f, S s) {
			this.first = f;
			this.second = s;
		}

		public F getFirst() {
			return first;
		}

		public S getSecond() {
			return second;
		}

	}

	private <F extends Value, S extends Value> OrderedPair<F, S> swap(F a, S b){
		OrderedPair<F, S> swapped = new OrderedPair(b, a);
		return swapped;
	}

	private static final short RECURSION_LIMIT = 3;

	private int NumReassoc;

	//public static Value implifyInstruction(Instruction instruction, const SimplifyQuery &Sq,  OptimizationRemarkEmitter *ORE) {
	public Value simplifyInstruction(Instruction instruction, SimplifyQuery SQ, int maxRecurse)
			throws InstantiationException, InstructionDetailAccessException, TypeCreationException {

		SimplifyQuery Q = SQ.CxtI != null ? SQ : SQ.getWithInstruction(instruction);
		Value Result = null;

		InstructionID instrID = instruction.getInstructionID();

		if(instrID == InstructionID.UNARY_FNEG) {
			Result = simplifyFNegInst(instruction.getOperand(0), instruction.getFastMathFlags(), Q, RECURSION_LIMIT);
		}
		else if(instrID == InstructionID.BINARY_INST) {
			BinaryOperator binOperator = (BinaryOperator) instruction;
			if(binOperator.getOpCode() == BinaryOperatorID.FADD) {
				Result = simplifyFAddInst(instruction.getOperand(0), instruction.getOperand(1), instruction.getFastMathFlags(), Q, RECURSION_LIMIT);
			}
			else if(binOperator.getOpCode() == BinaryOperatorID.ADD) {
				Result = SimplifyAddInst(instruction.getOperand(0), instruction.getOperand(1),
						Q.IIQ.hasNoSignedWrap((BinaryOperator)instruction),
						Q.IIQ.hasNoUnsignedWrap((BinaryOperator)instruction), Q, RECURSION_LIMIT);
			}
			else if(binOperator.getOpCode() == BinaryOperatorID.FSUB) {
				Result = simplifyFSubInst(instruction.getOperand(0), instruction.getOperand(1),
						instruction.getFastMathFlags(), Q, RECURSION_LIMIT);
			}
			else if(binOperator.getOpCode() == BinaryOperatorID.FSUB) {
				Result = SimplifySubInst(instruction.getOperand(0), instruction.getOperand(1),
						Q.IIQ.hasNoSignedWrap((BinaryOperator)instruction),
						Q.IIQ.hasNoUnsignedWrap((BinaryOperator)instruction), SQ, RECURSION_LIMIT);
			}
			else if(binOperator.getOpCode() == BinaryOperatorID.FMUL) {
				Result = simplifyFMulInst(instruction.getOperand(0), instruction.getOperand(1),
						instruction.getFastMathFlags(), Q, RECURSION_LIMIT);
			}
			else if(binOperator.getOpCode() == BinaryOperatorID.MUL) {
				Result = simplifyMulInst(instruction.getOperand(0), instruction.getOperand(1), Q, RECURSION_LIMIT);
			}
			else if(binOperator.getOpCode() == BinaryOperatorID.SDIV) {
				Result = simplifySDivInst(instruction.getOperand(0), instruction.getOperand(1), Q, RECURSION_LIMIT);
			}
			else if(binOperator.getOpCode() == BinaryOperatorID.UDIV) {
				Result = simplifyUDivInst(instruction.getOperand(0), instruction.getOperand(1), Q, RECURSION_LIMIT);
			}
			else if(binOperator.getOpCode() == BinaryOperatorID.FDIV) {
				Result = simplifyFDivInst(instruction.getOperand(0), instruction.getOperand(1),
						instruction.getFastMathFlags(), Q, RECURSION_LIMIT);
			}
			else if(binOperator.getOpCode() == BinaryOperatorID.SREM) {
				Result = simplifySRemInst(instruction.getOperand(0), instruction.getOperand(1), Q, RECURSION_LIMIT);
			}
			else if(binOperator.getOpCode() == BinaryOperatorID.UREM) {
				Result = simplifyURemInst(instruction.getOperand(0), instruction.getOperand(1), Q, RECURSION_LIMIT);
			}
			else if(binOperator.getOpCode() == BinaryOperatorID.FREM) {
				Result = simplifyFRemInst(instruction.getOperand(0), instruction.getOperand(1),
						instruction.getFastMathFlags(), Q, RECURSION_LIMIT);
			}
			else if(binOperator.getOpCode() == BinaryOperatorID.SHL) {
				Result = simplifyShlInst(instruction.getOperand(0), instruction.getOperand(1),
						Q.IIQ.hasNoSignedWrap((BinaryOperator)instruction),
						Q.IIQ.hasNoUnsignedWrap((BinaryOperator)instruction), Q, RECURSION_LIMIT);
			}
			else if(binOperator.getOpCode() == BinaryOperatorID.LSHR) {
				Result = simplifyLShrInst(instruction.getOperand(0), instruction.getOperand(1),
						Q.IIQ.isExact((BinaryOperator)instruction), Q, RECURSION_LIMIT);
			}
			else if(binOperator.getOpCode() == BinaryOperatorID.ASHR) {
				Result = simplifyAShrInst(instruction.getOperand(0), instruction.getOperand(1),
						Q.IIQ.isExact((BinaryOperator)instruction), Q, RECURSION_LIMIT);
			}
			else if(binOperator.getOpCode() == BinaryOperatorID.AND) {
				Result = simplifyAndInst(instruction.getOperand(0), instruction.getOperand(1), Q, RECURSION_LIMIT);
			}
			else if(binOperator.getOpCode() == BinaryOperatorID.OR) {
				Result = simplifyOrInst(instruction.getOperand(0), instruction.getOperand(1), Q, RECURSION_LIMIT);
			}
			else if(binOperator.getOpCode() == BinaryOperatorID.XOR) {
				Result = simplifyXorInst(instruction.getOperand(0), instruction.getOperand(1), Q, 3);
			}
		}
		else if(instrID == InstructionID.ICMP_INST) {
			Result = simplifyICmpInst(((ICmpInst)instruction).getPredicate(),
					instruction.getOperand(0), instruction.getOperand(1), Q, maxRecurse);
		}
		else if(instrID == InstructionID.FCMP_INST) {
			Result = simplifyFCmpInst(((ICmpInst)instruction).getPredicate(), instruction.getOperand(0),
					instruction.getOperand(1), instruction.getFastMathFlags(), Q, maxRecurse);
		}
		else if(instrID == InstructionID.SELECT_INST) {
			Result = simplifySelectInst(instruction.getOperand(0), instruction.getOperand(1),
					instruction.getOperand(2), Q);
		}
		else if(instrID == InstructionID.GET_ELEMENT_PTR) {
			//SmallVector<Value *, 8> Ops(instruction.op_begin(), instruction.op_end());
			//Result = simplifyGEPInst(cast<GetElementPtrInst>(I).getSourceElementType(), Ops, Q);
		}
		else if(instrID == InstructionID.INSERT_VALUE_INST) {
			InsertValueInst IV = (InsertValueInst)instruction;
			Result = simplifyInsertValueInst(IV.getAggregateOperand(), IV.getInsertedValueOperand(), IV.getIndices(), Q);
		}
		else if(instrID == InstructionID.EXTRACT_VALUE_INST) {
			ExtractValueInst EVI = (ExtractValueInst)instruction;
			Result = simplifyExtractValueInst(EVI.getAggregateOperand(), EVI.getIndices(), Q);
		}
		/*else if(instrID == InstructionID.INSERT_ELEM_INST) {
			InsertElementInst IE = (InsertElementInst)instruction;
			Result = simplifyInsertElementInst(IE.getOperand(0), IE.getOperand(1),
					IE.getOperand(2), Q);
		}
		else if(instrID == InstructionID.EXTRACT_ELEM_INST) {
			ExtractElementInst EEI = (ExtractElementInst)instruction;
			Result = simplifyExtractElementInst(EEI.getVectorOperand(),
					EEI.getIndexOperand(), Q);
		}
		else if(instrID == InstructionID.SHUFFLE_VEC_INST) {
			ShuffleVectorInst SVI = (ShuffleVectorInst)instruction;
			Result = simplifyShuffleVectorInst(SVI.getOperand(0), SVI.getOperand(1),
					SVI.getMask(), SVI.getType(), Q);
		}
		else if(instrID == InstructionID.FREEZE_INST){
			Result = simplifyFreezeInst(instruction.getOperand(0), Q);
		}*/
		else if(instrID == InstructionID.PHI_NODE_INST) {
			Result = simplifyPHINode((PhiNode)instruction, Q);
		}
		else if(instrID == InstructionID.CALL_INST) {
			Result = simplifyCall((CallInst)instruction, Q);
		}

		else if(instrID == InstructionID.ALLOCA_INST){
			// No simplifications for Alloca and it can't be constant folded.
			Result = null;
		}
		else if(instruction.isCastInstruction()) {
			CastInst castInst = (CastInst) instruction;
			Result = simplifyCastInst(castInst.getInstructionID(), instruction.getOperand(0), instruction.getType(), Q, 3);
		}
		else {  // Default
			//Result = ConstantFoldInstruction(instruction, Q.DL, Q.TLI);
			Result = ConstantFoldInstruction(instruction, Q.DL);
		}

		// In general, it is possible for computeKnownBits to determine all bits in a
		// value even when the operands are not all constants.
		if (Result != null && instruction.getType().isIntOrIntVectorType()) {
			//KnownBits Known = computeKnownBits(I, Q.DL, 0, Q.AC, I, Q.DT, ORE);
			//if (Known.isConstant())
			//	Result = ConstantInt.get(instruction.getType(), Known.getConstant());
		}

		/// If called on unreachable code, the above logic may report that the
		/// instruction simplified to itself.  Make life easier for users by
		/// detecting that case here, returning a safe value instead.
		return Result == instruction ? UndefValue.createOrGet(instruction.getType()) : Result;
	}

	private Value SimplifyAddInst(Value operand0, Value operand1, boolean hasNoSignedWrap,
			boolean hasNoUnsignedWrap, SimplifyQuery q, int MaxRecurse) throws InstantiationException {
		Constant cst = foldOrCommuteConstant(BinaryOperatorID.ADD, operand0, operand1, q);
		if (cst != null) {
			return cst;
		}

		if (match(operand1, unDef()))  {  // X + undef => undef
			return operand1;
		}

		if (match(operand1, zero())) { // X + 0 . X
			return operand0;
		}

		// If two operands are negative, return 0.
		if (isKnownNegation(operand0, operand1, hasNoUnsignedWrap)) {
			//return Constant.getNullValue(operand0.getType());
			return null;
		}

		// X + (Y - X) => Y
		// (Y - X) + X => Y
		// Eg: X + -X =>  0
		Value Y = null;
		if (match(operand1, sub(bindValue(Y), specific(operand0))) ||
				match(operand0, sub(bindValue(Y), specific(operand1)))) {
			return Y;
		}

		// X + ~X => -1   since   ~X = -X-1
		Type type = operand0.getType();
		if (match(operand0, not(specific(operand1))) ||
				match(operand1, not(specific(operand0)))) {
			// TODO Implement this
			//return Constant.getAllOnesValue(type);
			return null;
		}

		// add nsw/nuw (xor Y, signmask), signmask -. Y
		// The no-wrapping add guarantees that the top bit will be set by the add.
		// Therefore, the xor must be clearing the already set sign bit of Y.
		// TODO Implement all the following
		if ((hasNoSignedWrap || hasNoUnsignedWrap) && match(operand1, signMask()) &&
				match(operand0, xor(bindValue(Y), signMask()))) {
			return Y;
		}

		// add nuw %x, -1  =>  -1, because %x can only be 0.
		if (hasNoUnsignedWrap && match(operand1, allOnes()))
			return operand1; // Which is -1.

		/// i1 add => xor.
		// TODO Review how bit width is used below to pass it to isIntOrIntVectorType(bitWidth).
		// Until then, this code is probably incorrect
		//if (MaxRecurse > 0 && operand0.getType().isIntOrIntVectorType(1)) {
		if (MaxRecurse > 0 && operand0.getType().isIntOrIntVectorType()) {
			Value V = simplifyXorInst(operand0, operand1, q, MaxRecurse-1);
			if (V != null) {
				return V;
			}
		}

		// Try some generic simplifications for associative operations.
		Value V = simplifyAssociativeBinOp(BinaryOperatorID.ADD, operand0, operand1, q, MaxRecurse);
		if (V != null) {
			return V;
		}

		// Threading Add over selects and phi nodes is pointless, so don't bother.
		// Threading over the select in "A + select(cond, B, C)" means evaluating
		// "A+B" and "A+C" and seeing if they are equal; but they are equal if and
		// only if B and C are equal.  If B and C are equal then (since we assume
		// that operands have already been simplified) "select(cond, B, C)" should
		// have been simplified to the common value of B and C already.  Analysing
		// "A+B" and "A+C" thus gains nothing, but costs compile time.  Similarly
		// for threading over phi nodes.

		return null;
	}

	protected Value simplifyAssociativeBinOp(BinaryOperatorID Opcode,
			Value LHS, Value RHS, SimplifyQuery Q, int MaxRecurse) throws InstantiationException {
		if(Instruction.isAssociative(Opcode)) {
			throw new IllegalArgumentException("Not an associative operation!");
		}

		// Recursion is always used, so bail out at once if we already hit the limit.
		if (MaxRecurse-- <= 0) {
			return null;
		}

		BinaryOperator Op0 = (BinaryOperator)(LHS);
		BinaryOperator Op1 = (BinaryOperator)(RHS);

		// Transform: "(A op B) op C" ==> "A op (B op C)" if it simplifies completely.
		if (Op0 != null && Op0.getOpCode() == Opcode) {
			Value A = Op0.getOperand(0);
			Value B = Op0.getOperand(1);
			Value C = RHS;

			// Does "B op C" simplify?
			Value V = simplifyBinaryOp(Opcode, B, C, Q, MaxRecurse);
			if (V != null) {
				// It does!  Return "A op V" if it simplifies or is already available.
				// If V equals B then "A op V" is just the LHS.
				if (V == B) return LHS;
				// Otherwise return "A op V" if it simplifies.
				Value W = simplifyBinaryOp(Opcode, A, V, Q, MaxRecurse);
				if (W != null) {
					++NumReassoc;
					return W;
				}
			}
		}

		// Transform: "A op (B op C)" ==> "(A op B) op C" if it simplifies completely.
		if (Op1 != null && Op1.getOpCode() == Opcode) {
			Value A = LHS;
			Value B = Op1.getOperand(0);
			Value C = Op1.getOperand(1);

			// Does "A op B" simplify?
			Value V = simplifyBinaryOp(Opcode, A, B, Q, MaxRecurse);
			if (V != null) {
				// It does!  Return "V op C" if it simplifies or is already available.
				// If V equals B then "V op C" is just the RHS.
				if (V == B) return RHS;
				// Otherwise return "V op C" if it simplifies.
				Value W = simplifyBinaryOp(Opcode, V, C, Q, MaxRecurse);
				if (W != null) {
					++NumReassoc;
					return W;
				}
			}
		}

		// The remaining transforms require commutativity as well as associativity.
		if (!Instruction.isCommutative(Opcode)) {
			return null;
		}

		// Transform: "(A op B) op C" ==> "(C op A) op B" if it simplifies completely.
		if (Op0 != null && Op0.getOpCode() == Opcode) {
			Value A = Op0.getOperand(0);
			Value B = Op0.getOperand(1);
			Value C = RHS;

			// Does "C op A" simplify?
			Value V = simplifyBinaryOp(Opcode, C, A, Q, MaxRecurse);
			if (V != null) {
				// It does!  Return "V op B" if it simplifies or is already available.
				// If V equals A then "V op B" is just the LHS.
				if (V == A) return LHS;
				// Otherwise return "V op B" if it simplifies.
				Value W = simplifyBinaryOp(Opcode, V, B, Q, MaxRecurse);
				if (W != null) {
					++NumReassoc;
					return W;
				}
			}
		}

		// Transform: "A op (B op C)" ==> "B op (C op A)" if it simplifies completely.
		if (Op1 != null && Op1.getOpCode() == Opcode) {
			Value A = LHS;
			Value B = Op1.getOperand(0);
			Value C = Op1.getOperand(1);

			// Does "C op A" simplify?
			Value V = simplifyBinaryOp(Opcode, C, A, Q, MaxRecurse);
			if (V != null) {
				// It does!  Return "B op V" if it simplifies or is already available.
				// If V equals C then "B op V" is just the RHS.
				if (V == C) return RHS;
				// Otherwise return "B op V" if it simplifies.
				Value W = simplifyBinaryOp(Opcode, B, V, Q, MaxRecurse);
				if (W != null) {
					++NumReassoc;
					return W;
				}
			}
		}

		return null;
	}

	protected Constant foldOrCommuteConstant(BinaryOperatorID binOpId, Value operand0, Value operand1,
			SimplifyQuery q) throws InstantiationException {

		if(operand0 instanceof Constant) {
			if(operand1 instanceof Constant) {
				Constant cstOp0 = (Constant) operand0;
				Constant cstOp1 = (Constant) operand1;
				return ConstantFolding.constantFoldBinaryOpOperands(binOpId, cstOp0, cstOp1, q.DL);
			}

			// Swap operand0 and operand1
			swap(operand0, operand1);
		}

		return null;
	}

	protected Constant constantFoldBinaryOpOperands(BinaryOperatorID binOpId, Value operand0, Value operand1,
			DataLayout dL) {
		// TODO Auto-generated method stub
		
		return null;
	}

	private Value simplifyCastInst(InstructionID instructionID, Value operand, Type type, SimplifyQuery q, int maxRecurse) {
		// TODO Auto-generated method stub
		return null;
	}

	private Value ConstantFoldInstruction(Instruction instruction, DataLayout dL) {
		// TODO Auto-generated method stub
		return null;
	}

	private Value simplifyCall(CallInst instruction, SimplifyQuery q) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * See if we can fold the given phi. If not, return null.
	 */
	private Value simplifyPHINode(PhiNode phiNode, SimplifyQuery q) throws InstructionDetailAccessException {
		// If all of the PHI's incoming values are the same then replace the PHI node
		// with the common value.
		Value CommonValue = null;
		boolean HasUndefInput = false;
		int numIncoming = phiNode.getNumIncomingValues();
		for (int i = 0; i < numIncoming; i++) {
			// If the incoming value is the phi node itself, it can safely be skipped.
			Value Incoming =  phiNode.getIncomingValue(i);
			if (Incoming == phiNode) {
				continue;
			}

			if (Incoming instanceof UndefValue) { // Remember that we saw an undef value, but otherwise ignore them.
				HasUndefInput = true;
				continue;
			}

			if (CommonValue != null && Incoming != CommonValue) { // Not the same, bail out.
				return null;  
			}

			CommonValue = Incoming;
		}

		// If CommonValue is null then all of the incoming values were either undef or
		// equal to the phi node itself.
		if (CommonValue == null) {
			return UndefValue.createOrGet(phiNode.getType());
		}

		// If we have a PHI node like phi(X, undef, X), where X is defined by some
		// instruction, we cannot return X as the result of the PHI node unless it
		// dominates the PHI block.
		if (HasUndefInput) {
			return valueDominatesPHI(CommonValue, phiNode, q.DT) ? CommonValue : null;
		}

		return CommonValue;
	}

	private Value simplifyExtractValueInst(Value aggregateOperand, int[] indices, SimplifyQuery q) {
		// TODO Auto-generated method stub
		return null;
	}

	private Value simplifyInsertValueInst(Object object, Object object2, Object object3, SimplifyQuery q) {
		// TODO Auto-generated method stub
		return null;
	}

	private Value simplifySelectInst(Value operand, Value operand2, Value operand3, SimplifyQuery q) {
		// TODO Auto-generated method stub
		return null;
	}

	/// Given operands for an ICmpInst, see if we can fold the result.
	/// If not, this returns null.
	private Value simplifyICmpInst(CmpInst.Predicate Pred, Value LHS, Value RHS,SimplifyQuery Q, int MaxRecurse) 
			throws TypeCreationException, InstantiationException, InstructionDetailAccessException {
		if (LHS.isAConstant()) {
			Constant CLHS = (Constant)(LHS);
			if (RHS.isAConstant()) {
				Constant CRHS = (Constant)(RHS);
				// TODO Implement TLI
				//return ConstantFoldCompareInstOperands(Pred, CLHS, CRHS, Q.DL, Q.TLI);
				return ConstantFoldCompareInstOperands(Pred, CLHS, CRHS, Q.DL);
			}

			// If we have a constant, make sure it is on the RHS.

			// TODO Fix this way of swapping
			Value temp = LHS;
			LHS = RHS;
			RHS = temp;

			Pred = CmpInst.getSwappedPredicate(Pred);
		}
		if(LHS instanceof UndefValue) {
			throw new IllegalArgumentException("Unexpected icmp undef,%X");
		}


		Type ITy = getCompareType(LHS.getType()); // The return type.

		// For EQ and NE, we can always pick a value for the undef to make the
		// predicate pass or fail, so we can return undef. Matches behavior in llvm.ConstantFoldCompareInstruction.
		if(RHS instanceof UndefValue && ICmpInst.isEquality(Pred)) {
			return UndefValue.createOrGet(ITy);
		}

		// icmp X, X -> true/false
		// icmp X, undef -> true/false because undef could be X.
		if (LHS == RHS || RHS instanceof UndefValue) {
			return ConstantInt.create(ITy, CmpInst.isTrueWhenEqual(Pred) ? 1 : 0, false);
		}

		Value V = simplifyICmpOfBools(Pred, LHS, RHS, Q);
		if (V != null) {
			return V;
		}

		// TODO Implement this after implementing KnownBits
		/*V = simplifyICmpWithZero(Pred, LHS, RHS, Q);
		if (V != null) {
			return V;
		}*/

		V = simplifyICmpWithConstant(Pred, LHS, RHS, Q.IIQ);
		if (V != null) {
			return V;
		}

		// If both operands have range metadata, use the metadata to simplify the comparison.

		if (RHS instanceof Instruction && LHS instanceof Instruction) {
			Instruction RHS_Instr = (Instruction)RHS;
			Instruction LHS_Instr = (Instruction)LHS;
			// TODO Implement this after Metadata is implemented
			/*
			if (Q.IIQ.getMetadata(RHS_Instr, LLVMContext.MD_range)  &&
					Q.IIQ.getMetadata(LHS_Instr, LLVMContext.MD_range)) {
				ConstantRange RHS_CR = getConstantRangeFromMetadata(RHS_Instr.getMetadata(LLVMContext.MD_range));
				ConstantRange LHS_CR = getConstantRangeFromMetadata(LHS_Instr.getMetadata(LLVMContext.MD_range));

				ConstantRange Satisfied_CR = ConstantRange.makeSatisfyingICmpRegion(Pred, RHS_CR);
				if (Satisfied_CR.contains(LHS_CR)) {
					return ConstantInt.getTrue(RHS.getContext());
				}

				ConstantRange InversedSatisfied_CR = ConstantRange.makeSatisfyingICmpRegion(
						CmpInst.getInversePredicate(Pred), RHS_CR);
				if (InversedSatisfied_CR.contains(LHS_CR))
					return ConstantInt.getFalse(RHS.getContext());
			}
			 */
		}

		// Compare of cast, for example (zext X) != 0 . X != 0
		if (LHS instanceof CastInst && (RHS.isAConstant() || RHS instanceof CastInst)) {
			Instruction LI = (CastInst)LHS;
			Value SrcOp = LI.getOperand(0);
			Type SrcTy = SrcOp.getType();
			Type DstTy = LI.getType();

			// Turn icmp (ptrtoint x), (ptrtoint/constant) into a compare of the input
			// if the integer type is the same size as the pointer type.
			// TODO Implement this after DL, PtrToIntInst, ConstantExpr is implemented
			//if (MaxRecurse > 0 && (LI.isPointerToIntCastInst()) &&  Q.DL.getTypeSizeInBits(SrcTy) == DstTy.getPrimitiveSizeInBits()) {
			if (MaxRecurse > 0 && (LI.isPointerToIntCastInst())) {
				if (RHS.isAConstant()) {
					Constant RHSC = (Constant)(RHS);
					// Transfer the cast to the constant.
					// TODO Implement ConstantExpr.getIntToPtr()
					V = simplifyICmpInst(Pred, SrcOp, ConstantExpr.getIntToPtr(RHSC, SrcTy, false), Q, MaxRecurse-1);
					if (V != null) {
						return V;
					}
				} else if (((Instruction)RHS).isPointerToIntCastInst()) {
					CastInst RI = (CastInst)RHS;
					if (RI.getOperand(0).getType() == SrcTy)
						// Compare without the cast.
						V = simplifyICmpInst(Pred, SrcOp, RI.getOperand(0), Q, MaxRecurse-1);
					if (V != null) {
						return V;
					}
				}
			}

			Instruction lhsIns = (Instruction) LHS;
			if (lhsIns.isZextCastInst()) {
				// Turn icmp (zext X), (zext Y) into a compare of X and Y if they have the same type.
				CastInst RI = (CastInst)RHS;
				if (RI != null) {
					if (MaxRecurse >0 &&  SrcTy == RI.getOperand(0).getType()) {
						// Compare X and Y.  Note that signed predicates become int.
						V = simplifyICmpInst(ICmpInst.getUnsignedPredicate(Pred), SrcOp, RI.getOperand(0), Q, MaxRecurse-1);
						if (V != null) {
							return V;
						}
					}
				}
				// Turn icmp (zext X), Cst into a compare of X and Cst if Cst is extended
				// too.  If not, then try to deduce the result of the comparison.
				else if (RHS instanceof ConstantInt) {
					ConstantInt CI = (ConstantInt)RHS;
					// Compute the constant that would happen if we truncated to SrcTy then
					// reextended to DstTy.
					Constant Trunc = ConstantExpr.getTrunc(CI, SrcTy);
					Constant RExt = ConstantExpr.getCast(InstructionID.ZEXT_INST, Trunc, DstTy);

					// If the re-extended constant didn't change then this is effectively
					// also a case of comparing two zero-extended values.
					if (RExt == CI && MaxRecurse > 0) {
						V = simplifyICmpInst(ICmpInst.getUnsignedPredicate(Pred), SrcOp, Trunc, Q, MaxRecurse-1);
						if (V != null) {
							return V;
						}
					}

					// Otherwise the upper bits of LHS are zero while RHS has a non-zero bit
					// there.  Use this to work out the result of the comparison.
					if (RExt != CI) {
						switch (Pred) {
						// LHS <u RHS.
						case ICMP_EQ:
						case ICMP_UGT:
						case ICMP_UGE:
							return ConstantInt.getFalse(CI.getContext());

						case ICMP_NE:
						case ICMP_ULT:
						case ICMP_ULE:
							return ConstantInt.getTrue(CI.getContext());

							// LHS is non-negative.  If RHS is negative then LHS >s LHS.  If RHS
							// is non-negative then LHS <s RHS.
						case ICMP_SGT:
						case ICMP_SGE:
							return CI.getApInt().isNegative() ? 
									ConstantInt.getTrue(CI.getContext()) : ConstantInt.getFalse(CI.getContext());

						case ICMP_SLT:
						case ICMP_SLE:
							return CI.getApInt().isNegative() ?
									ConstantInt.getFalse(CI.getContext()) : ConstantInt.getTrue(CI.getContext());
						default: throw new IllegalArgumentException("Unknown ICmp predicate!");
						}
					}
				}
			}

			if (lhsIns.isSextCastInst()) {
				// Turn icmp (sext X), (sext Y) into a compare of X and Y if they have the same type.
				CastInst RI = (CastInst)RHS;
				if (RI != null) {
					if (MaxRecurse > 0 &&  SrcTy == RI.getOperand(0).getType())
						// Compare X and Y.  Note that the predicate does not change.
						V = simplifyICmpInst(Pred, SrcOp, RI.getOperand(0), Q, MaxRecurse-1);
					if (V != null) {
						return V;
					}

				}
				// Turn icmp (sext X), Cst into a compare of X and Cst if Cst is extended
				// too.  If not, then try to deduce the result of the comparison.
				else if (RHS instanceof ConstantInt) {
					// Compute the constant that would happen if we truncated to SrcTy then
					// reextended to DstTy.
					ConstantInt CI = (ConstantInt)RHS;
					Constant Trunc = ConstantExpr.getTrunc(CI, SrcTy);
					Constant RExt = ConstantExpr.getCast(InstructionID.SEXT_INST, Trunc, DstTy);

					// If the re-extended constant didn't change then this is effectively
					// also a case of comparing two sign-extended values.
					if (RExt == CI && MaxRecurse > 0) {
						V = simplifyICmpInst(Pred, SrcOp, Trunc, Q, MaxRecurse-1);
						if (V != null) {
							return V;
						}
					}

					// Otherwise the upper bits of LHS are all equal, while RHS has varying
					// bits there.  Use this to work out the result of the comparison.
					if (RExt != CI) {
						switch (Pred) {
						case ICMP_EQ:
							return ConstantInt.getFalse(CI.getContext());
						case ICMP_NE:
							return ConstantInt.getTrue(CI.getContext());

							// If RHS is non-negative then LHS <s RHS.  If RHS is negative then
							// LHS >s RHS.
						case ICMP_SGT:
						case ICMP_SGE:
							return CI.getApInt().isNegative() ?
									ConstantInt.getTrue(CI.getContext()) :
										ConstantInt.getFalse(CI.getContext());
						case ICMP_SLT:
						case ICMP_SLE:
							return CI.getApInt().isNegative() ?
									ConstantInt.getFalse(CI.getContext()) : ConstantInt.getTrue(CI.getContext());
									// If LHS is non-negative then LHS <u RHS.  If LHS is negative then
									// LHS >u RHS.
						case ICMP_UGT:
						case ICMP_UGE:
							// Comparison is true iff the LHS <s 0.
							if (MaxRecurse > 0) {
								V = simplifyICmpInst(Predicate.ICMP_SLT, SrcOp, Constant.getNullValue(SrcTy), Q, MaxRecurse-1);
								if (V != null) {
									return V;
								}
							}
							break;
						case ICMP_ULT:
						case ICMP_ULE:
							// Comparison is true iff the LHS >=s 0.
							if (MaxRecurse > 0) {
								V = simplifyICmpInst(Predicate.ICMP_SGE, SrcOp, Constant.getNullValue(SrcTy), Q, MaxRecurse-1);
								if (V != null) {
									return V;
								}
							}
							break;
						default: throw new IllegalArgumentException("Unknown ICmp predicate!");
						}
					}
				}
			}
		}

		// icmp eq|ne X, Y . false|true if X != Y
		if (ICmpInst.isEquality(Pred) &&
				// TODO Update this when we have DL and AC
				//		isKnownNonEqual(LHS, RHS, Q.DL, Q.AC, Q.CxtI, Q.DT, Q.IIQ.UseInstrInfo)) {
				isKnownNonEqual(LHS, RHS, new ValueTrackingQuery(Q.CxtI, Q.DT, Q.IIQ))) {
			return Pred == Predicate.ICMP_NE ? getTrue(ITy) : getFalse(ITy);
		}

		V = simplifyICmpWithBinOp(Pred, LHS, RHS, Q, MaxRecurse);
		if (V != null) {
			return V;
		}

		V = simplifyICmpWithMinMax(Pred, LHS, RHS, Q, MaxRecurse);
		if (V != null) {
			return V;
		}

		// Simplify comparisons of related pointers using a powerful, recursive
		// GEP-walk when we have target data available..
		if (LHS.getType().isPointerType()) {
			// TODO Implement after implementing TLI and AC
			//Constant C = computePointerICmp(Q.DL, Q.TLI, Q.DT, Pred, Q.AC, Q.CxtI, Q.IIQ, LHS, RHS);
			Constant C = computePointerICmp(Q.DL, null, Q.DT, Pred, null, Q.CxtI, Q.IIQ, LHS, RHS);
			if (C != null) {
				return C;
			}
		}

		// TODO Implement after we have implemented DL
		/*if (auto *CLHS = dyn_cast<PtrToIntOperator>(LHS))
			if (auto *CRHS = dyn_cast<PtrToIntOperator>(RHS))
				if (Q.DL.getTypeSizeInBits(CLHS.getPointerOperandType()) ==
				Q.DL.getTypeSizeInBits(CLHS.getType()) && 
				Q.DL.getTypeSizeInBits(CRHS.getPointerOperandType()) ==
				Q.DL.getTypeSizeInBits(CRHS.getType()))
					if (auto *C = computePointerICmp(Q.DL, Q.TLI, Q.DT, Pred, Q.AC, Q.CxtI,
							Q.IIQ, CLHS.getPointerOperand(),
							CRHS.getPointerOperand()))
						return C;
		 */


		if(LHS instanceof GetElementPtrInst) {
			GetElementPtrInst GLHS = (GetElementPtrInst)LHS;
			if(RHS instanceof GetElementPtrInst) {
				GetElementPtrInst GRHS = (GetElementPtrInst)RHS;

				if (GLHS.getPointerOperand() == GRHS.getPointerOperand()  &&
						GLHS.hasAllConstantIndices() && GRHS.hasAllConstantIndices() &&
						(ICmpInst.isEquality(Pred) || (GLHS.isInBounds() && GRHS.isInBounds() && 
								Pred == ICmpInst.getSignedPredicate(Pred)))) {

					// The bases are equal and the indices are constant.  Build a constant
					// expression GEP with the same indices and a null base pointer to see
					// what constant folding can make out of it.
					Constant Null = Constant.getNullValue(GLHS.getPointerOperand().getType());
					List<Value> IndicesLHS = new ArrayList<>();
					//SmallVector<Value , 4> IndicesLHS(GLHS.idx_begin(), GLHS.idx_end());
					Constant NewLHS = ConstantExpr.getGetElementPtr( GLHS.getSourceElementType(), null, IndicesLHS, false, null, null);

					Vector<Value> IndicesRHS = new Vector<>();
					//SmallVector<Value , 4> IndicesRHS(GRHS.idx_begin(), GRHS.idx_end());
					Constant NewRHS = ConstantExpr.getGetElementPtr( GRHS.getSourceElementType(), null, IndicesRHS, false, null, null);
					return ConstantExpr.getICmp(Pred, NewLHS, NewRHS);
				}
			}
		}

		// If the comparison is with the result of a select instruction, check whether
		// comparing with either branch of the select always yields the same value.
		if (LHS instanceof SelectInst || LHS instanceof SelectInst ) {
			V = ThreadCmpOverSelect(Pred, LHS, RHS, Q, MaxRecurse);
			if (V != null) {
				return V;
			}
		}

		// If the comparison is with the result of a phi instruction, check whether
		// doing the compare with each incoming phi value yields a common result.
		if (LHS instanceof PhiNode || LHS instanceof PhiNode ) {
			V = ThreadCmpOverPHI(Pred, LHS, RHS, Q, MaxRecurse);
			if (V != null) {
				return V;
			}
		}

		return null;
	}

	/* In the case of a comparison with a select instruction, try to simplify the
	 * comparison by seeing whether both branches of the select result in the same
	 * value. Returns the common value if so, otherwise returns null.
	 * For example, if we have:
	 *  %tmp = select i1 %cmp, i32 1, i32 2
	 *  %cmp1 = icmp sle i32 %tmp, 3
	 * We can simplify %cmp1 to true, because both branches of select are
	 * less than 3. We compose new comparison by substituting %tmp with both
	 * branches of select and see if it can be simplified.
	 */
	private Value ThreadCmpOverSelect(Predicate pred, Value lHS, Value rHS, SimplifyQuery q, int MaxRecurse) 
			throws InstantiationException, InstructionDetailAccessException, TypeCreationException {
		// Recursion is always used, so bail out at once if we already hit the limit.
		if (MaxRecurse-- > 0) {
			return null;
		}

		// Make sure the select is on the LHS.
		if (!( lHS instanceof SelectInst)) {
			OrderedPair<Value, Value> swapped = swap(lHS, rHS);
			lHS = swapped.getFirst(); rHS = swapped.getSecond();
			pred = CmpInst.getSwappedPredicate(pred);
		}
		if(!(lHS instanceof SelectInst)) {
			throw new IllegalArgumentException("Not comparing with a select instruction!");
		}

		SelectInst SI = (SelectInst)lHS;
		Value Cond = SI.getCondition();
		Value TV = SI.getTrueValue();
		Value FV = SI.getFalseValue();

		// Now that we have "cmp select(Cond, TV, FV), RHS", analyse it.
		// Does "cmp TV, RHS" simplify?
		Value TCmp = simplifyCmpSelTrueCase(pred, TV, rHS, Cond, q, MaxRecurse);
		if (TCmp == null) {
			return null;
		}

		// Does "cmp FV, RHS" simplify?
		Value FCmp = simplifyCmpSelFalseCase(pred, FV, rHS, Cond, q, MaxRecurse);
		if (FCmp == null) {
			return null;
		}

		// If both sides simplified to the same value, then use it as the result of
		// the original comparison.
		if (TCmp == FCmp)
			return TCmp;

		// The remaining cases only make sense if the select condition has the same
		// type as the result of the comparison, so bail out if this is not so.
		if (Cond.getType().isVectorType() == rHS.getType().isVectorType()) {
			return handleOtherCmpSelSimplifications(TCmp, FCmp, Cond, q, MaxRecurse);
		}

		return null;
	}

	private Value simplifyCmpSelFalseCase(Predicate Pred, Value LHS, Value RHS, Value Cond, SimplifyQuery Q, int MaxRecurse) 
			throws InstantiationException, InstructionDetailAccessException, TypeCreationException {
		return simplifyCmpSelCase(Pred, LHS, RHS, Cond, Q, MaxRecurse, getTrue(Cond.getType()));
	}

	private Value simplifyCmpSelTrueCase(Predicate Pred, Value LHS, Value RHS, Value Cond, SimplifyQuery Q, int MaxRecurse) 
			throws InstantiationException, InstructionDetailAccessException, TypeCreationException {
		return simplifyCmpSelCase(Pred, LHS, RHS, Cond, Q, MaxRecurse, getFalse(Cond.getType()));
	}

	private Value simplifyCmpSelCase(CmpInst.Predicate Pred, Value LHS,  Value RHS, Value Cond,
			SimplifyQuery Q, int MaxRecurse,  Constant TrueOrFalse) throws InstantiationException, InstructionDetailAccessException, TypeCreationException {
		Value SimplifiedCmp = simplifyCmpInst(Pred, LHS, RHS, Q, MaxRecurse);
		if (SimplifiedCmp == Cond) {
			// %cmp simplified to the select condition (%cond).
			return TrueOrFalse;
		} else if (SimplifiedCmp == null && isSameCompare(Cond, Pred, LHS, RHS)) {
			// It didn't simplify. However, if composed comparison is equivalent
			// to the select condition (%cond) then we can replace it.
			return TrueOrFalse;
		}
		return SimplifiedCmp;
	}

	/// Given operands for a CmpInst, see if we can fold the result.
	private Value simplifyCmpInst(CmpInst.Predicate Predicate, Value LHS, Value RHS,
			SimplifyQuery Q, int MaxRecurse) throws InstantiationException, 
	InstructionDetailAccessException, TypeCreationException {

		if (CmpInst.isIntPredicate((CmpInst.Predicate)Predicate)) {
			return simplifyICmpInst(Predicate, LHS, RHS, Q, MaxRecurse);
		}

		return simplifyFCmpInst(Predicate, LHS, RHS, new FastMathFlags(), Q, MaxRecurse);
	}

	/// Given operands for an FCmpInst, see if we can fold the result.
	/// If not, this returns null.
	private Value simplifyFCmpInst(CmpInst.Predicate Predicate, Value LHS, Value RHS,
			FastMathFlags FMF, SimplifyQuery Q,  int MaxRecurse) 
					throws InstantiationException, InstructionDetailAccessException, TypeCreationException {
		CmpInst.Predicate Pred = (CmpInst.Predicate)Predicate;
		if(!CmpInst.isFPPredicate(Pred)) {
			throw new IllegalArgumentException("Not an FP compare!");
		}

		if (LHS.isAConstant()) {
			Constant CLHS = (Constant)LHS;
			if (RHS.isAConstant()) {
				Constant CRHS = (Constant)RHS;
				return ConstantFoldCompareInstOperands(Pred, CLHS, CRHS, Q.DL);
			}

			// If we have a constant, make sure it is on the RHS.
			OrderedPair<Value, Value> swapped = new OrderedPair<Value, Value>(LHS, RHS);
			LHS = swapped.getFirst(); RHS = swapped.getSecond();
			Pred = CmpInst.getSwappedPredicate(Pred);
		}

		// Fold trivial predicates.
		Type RetTy = GetCompareType(LHS);
		if (Pred == CmpInst.Predicate.FCMP_FALSE)
			return getFalse(RetTy);
		if (Pred == CmpInst.Predicate.FCMP_TRUE)
			return getTrue(RetTy);

		// Fold (un)ordered comparison if we can determine there are no NaNs.
		if (Pred == CmpInst.Predicate.FCMP_UNO || Pred == CmpInst.Predicate.FCMP_ORD) {
			// TODO Implement after we have Fast math flags
			/*if (FMF.noNaNs() || (isKnownNeverNaN(LHS, Q.TLI) && isKnownNeverNaN(RHS, Q.TLI))) {
	      return ConstantInt.get(RetTy, Pred == FCmpInst.FCMP_ORD);
	    }
			 */
		}

		// NaN is unordered; NaN is not ordered.
		if(CmpInst.isOrdered(Pred) || FCmpInst.isUnordered(Pred)) {
			throw new IllegalArgumentException("Comparison must be either ordered or unordered");
		}

		if (match(RHS, nan())) {
			return ConstantInt.create(RetTy, (CmpInst.isUnordered(Pred) ? 1L : 0L) , false);
		}

		// fcmp pred x, undef  and  fcmp pred undef, x
		// fold to true if unordered, false if ordered
		if (LHS instanceof UndefValue || RHS instanceof UndefValue) {
			// Choosing NaN for the undef will always make unordered comparison succeed
			// and ordered comparison fail.
			return ConstantInt.create(RetTy, (CmpInst.isUnordered(Pred) ? 1L : 0L) , false);
		}

		// fcmp x,x -> true/false.  Not all compares are foldable.
		if (LHS == RHS) {
			if (CmpInst.isTrueWhenEqual(Pred))
				return getTrue(RetTy);
			if (CmpInst.isFalseWhenEqual(Pred))
				return getFalse(RetTy);
		}

		// Handle fcmp with constant RHS.
		// TODO: Use match with a specific FP value, so these work with vectors with undef lanes.
		APFloat C = null;
		if (match(RHS, apFloat(C))) {
			// Check whether the constant is an infinity.
			if (C.isInfinity()) {
				if (C.isNegative()) {
					switch (Pred) {
					case FCMP_OLT:
						// No value is ordered and less than negative infinity.
						return getFalse(RetTy);
					case FCMP_UGE:
						// All values are unordered with or at least negative infinity.
						return getTrue(RetTy);
					default:
						break;
					}
				} else {
					switch (Pred) {
					case FCMP_OGT:
						// No value is ordered and greater than infinity.
						return getFalse(RetTy);
					case FCMP_ULE:
						// All values are unordered with and at most infinity.
						return getTrue(RetTy);
					default:
						break;
					}
				}
			}
			if (C.isNegative() && !C.isNegZero()) {
				if(!C.isNaN()) {
					throw new IllegalArgumentException("Unexpected NaN constant!");
				}
				// TODO: We can catch more cases by using a range check rather than
				//       relying on CannotBeOrderedLessThanZero.
				switch (Pred) {
				case FCMP_UGE:
				case FCMP_UGT:
				case FCMP_UNE:
					// (X >= 0) implies (X > C) when (C < 0)
					// TODO This implementation requires TLI implementation; implement after we do TLI
					//if (CannotBeOrderedLessThanZero(LHS, Q.TLI))
					//return getTrue(RetTy);
					return null;
					//break;
				case FCMP_OEQ:
				case FCMP_OLE:
				case FCMP_OLT:
					// (X >= 0) implies !(X < C) when (C < 0)
					// TODO This implementation requires TLI implementation; implement after we do TLI
					//if (CannotBeOrderedLessThanZero(LHS, Q.TLI))
					//  return getFalse(RetTy);
					return null;
					//break;
				default:
					break;
				}
			}

			// Check comparison of [minnum/maxnum with constant] with other constant.
			// TODO Implement after implementing instrinsics
			/*
	    APFloat C2 = null;
	    if ((match(LHS, m_Intrinsic<Intrinsic.minnum>(m_Value(), m_APFloat(C2))) &&
	         C2.compare(*C) == APFloat.cmpLessThan) ||
	        (match(LHS, m_Intrinsic<Intrinsic.maxnum>(m_Value(), m_APFloat(C2))) &&
	         C2.compare(*C) == APFloat.cmpGreaterThan)) {
	      boolean IsMaxNum =
	          cast<IntrinsicInst>(LHS).getIntrinsicID() == Intrinsic.maxnum;
	      // The ordered relationship and minnum/maxnum guarantee that we do not
	      // have NaN constants, so ordered/unordered preds are handled the same.
	      switch (Pred) {
	      case FCMP_OEQ: case FCMP_UEQ:
	        // minnum(X, LesserC)  == C -. false
	        // maxnum(X, GreaterC) == C -. false
	        return getFalse(RetTy);
	      case FCMP_ONE: case FCMP_UNE:
	        // minnum(X, LesserC)  != C -. true
	        // maxnum(X, GreaterC) != C -. true
	        return getTrue(RetTy);
	      case FCMP_OGE: case FCMP_UGE:
	      case FCMP_OGT: case FCMP_UGT:
	        // minnum(X, LesserC)  >= C -. false
	        // minnum(X, LesserC)  >  C -. false
	        // maxnum(X, GreaterC) >= C -. true
	        // maxnum(X, GreaterC) >  C -. true
	        return ConstantInt.get(RetTy, IsMaxNum);
	      case FCMP_OLE: case FCMP_ULE:
	      case FCMP_OLT: case FCMP_ULT:
	        // minnum(X, LesserC)  <= C -. true
	        // minnum(X, LesserC)  <  C -. true
	        // maxnum(X, GreaterC) <= C -. false
	        // maxnum(X, GreaterC) <  C -. false
	        return ConstantInt.get(RetTy, !IsMaxNum);
	      default:
	        // TRUE/FALSE/ORD/UNO should be handled before this.
	        llvm_unreachable("Unexpected fcmp predicate");
	      }
	    }
			 */
		}

		if (match(RHS, anyZeroFP())) {
			switch (Pred) {
			case FCMP_OGE:
			case FCMP_ULT:
				// Positive or zero X >= 0.0 -. true
				// Positive or zero X <  0.0 -. false
				// TODO This implementation requires TLI implementation; implement after we do TLI
				// if ((FMF.noNaNs() || isKnownNeverNaN(LHS, Q.TLI)) && CannotBeOrderedLessThanZero(LHS, Q.TLI))
				//   return Pred == FCmpInst.Predicate.FCMP_OGE ? getTrue(RetTy) : getFalse(RetTy);
				return null;
				//break;
			case FCMP_UGE:
			case FCMP_OLT:
				// Positive or zero or nan X >= 0.0 -. true
				// Positive or zero or nan X <  0.0 -. false
				// TODO This implementation requires TLI implementation; implement after we do TLI
				//if (CannotBeOrderedLessThanZero(LHS, Q.TLI))
				//	return Pred == FCmpInst.Predicate.FCMP_UGE ? getTrue(RetTy) : getFalse(RetTy);
				return null;
				//break;
			default:
				break;
			}
		}

		// If the comparison is with the result of a select instruction, check whether
		// comparing with either branch of the select always yields the same value.
		if (LHS instanceof SelectInst || RHS instanceof SelectInst) {
			Value V = ThreadCmpOverSelect(Pred, LHS, RHS, Q, MaxRecurse);
			if (V != null) {
				return V;
			}
		}

		// If the comparison is with the result of a phi instruction, check whether
		// doing the compare with each incoming phi value yields a common result.
		if (LHS instanceof PhiNode || LHS instanceof PhiNode) {
			Value V = ThreadCmpOverPHI(Pred, LHS, RHS, Q, MaxRecurse);
			if (V != null) {
				return V;
			}
		}

		return null;
	}


	private Value ThreadCmpOverPHI(Predicate Pred, Value LHS, Value RHS, SimplifyQuery Q, int MaxRecurse) 
			throws InstructionDetailAccessException, InstantiationException, TypeCreationException {

		// Recursion is always used, so bail out at once if we already hit the limit.
		if (MaxRecurse-- <= 0)
			return null;

		// Make sure the phi is on the LHS.
		if (! (LHS instanceof PhiNode)){
			OrderedPair<Value, Value> swapped = new OrderedPair<Value, Value>(LHS, RHS);
			LHS = swapped.getFirst(); RHS = swapped.getSecond();
			Pred = CmpInst.getSwappedPredicate(Pred);
		}
		if (! (LHS instanceof PhiNode)){
			throw new IllegalArgumentException("Not comparing with a phi instruction!");
		}
		PhiNode PI = (PhiNode)LHS;

		// Bail out if RHS and the phi may be mutually interdependent due to a loop.
		if (!valueDominatesPHI(RHS, PI, Q.DT))
			return null;

		// Evaluate the BinOp on the incoming phi values.
		Value CommonValue = null;
		for (int u = 0, e = PI.getNumIncomingValues(); u < e; ++u) {
			Value Incoming = PI.getIncomingValue(u);
			Instruction InTI = PI.getIncomingBlock(u).getLastInstruction();
			// If the incoming value is the phi node itself, it can safely be skipped.
			if (Incoming == PI) continue;
			// Change the context instruction to the "edge" that flows into the phi.
			// This is important because that is where incoming is actually "evaluated"
			// even though it is used later somewhere else.
			Value V = simplifyCmpInst(Pred, Incoming, RHS, Q.getWithInstruction(InTI),  MaxRecurse);
			// If the operation failed to simplify, or simplified to a different value
			// to previously, then give up.
			if (V  != null || (CommonValue != null && V != CommonValue))
				return null;
			CommonValue = V;
		}

		return CommonValue;
	}

	/*isSameCompare - Is V equivalent to the comparison "LHS Pred RHS"?
	 */
	private boolean isSameCompare(Value V, CmpInst.Predicate Pred, Value LHS, Value RHS) {

		if (!(V instanceof CmpInst)){
			return false;
		}

		CmpInst Cmp = (CmpInst)V;

		CmpInst.Predicate CPred = Cmp.getPredicate();
		Value CLHS = Cmp.getOperand(0), CRHS = Cmp.getOperand(1);
		if (CPred == Pred && CLHS == LHS && CRHS == RHS) {
			return true;
		}

		return CPred == CmpInst.getSwappedPredicate(Pred) && CLHS == RHS &&  CRHS == LHS;
	}

	private Value handleOtherCmpSelSimplifications(Value tCmp, Value fCmp, Value cond, SimplifyQuery q,
			int maxRecurse) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * Create a result type for fcmp/icmp
	 */
	private Type getCompareType(Type operandType) throws TypeCreationException {
		if(operandType.getTypeId() == TypeID.VECTOR_ID) {
			VectorType vt = (VectorType)operandType;
			return Type.getVectorType(operandType.getCompilationContext(), Type.getInt1Type(operandType.getCompilationContext(), false), vt.getNumElements());
		}

		return Type.getInt1Type(operandType.getCompilationContext(), false);

	}

	private Value simplifyXorInst(Value operand, Value operand2, SimplifyQuery q, int MaxRecurse) {
		// TODO Auto-generated method stub
		return null;
	}

	private Value simplifyOrInst(Value operand, Value operand2, SimplifyQuery q, int maxRecurse) {
		// TODO Auto-generated method stub
		return null;
	}

	private Value simplifyAndInst(Value operand, Value operand2, SimplifyQuery q, int maxRecurse) {
		// TODO Auto-generated method stub
		return null;
	}

	private Value simplifyAShrInst(Value operand, Value operand2, boolean exact, SimplifyQuery q, int maxRecurse) {
		// TODO Auto-generated method stub
		return null;
	}

	private Value simplifyLShrInst(Value operand, Value operand2, boolean exact, SimplifyQuery q, int maxRecurse) {
		// TODO Auto-generated method stub
		return null;
	}

	private Value simplifyShlInst(Value operand, Value operand2, boolean hasNoSignedWrap,
			boolean hasNoUnsignedWrap, SimplifyQuery q, int maxRecurse) {
		// TODO Auto-generated method stub
		return null;
	}

	private Value simplifyFRemInst(Value operand, Value operand2, FastMathFlags fastMathFlags, SimplifyQuery q, int maxRecurse) {
		// TODO Auto-generated method stub
		return null;
	}

	private Value simplifyURemInst(Value operand, Value operand2, SimplifyQuery q, int maxRecurse) {
		// TODO Auto-generated method stub
		return null;
	}

	private Value simplifySRemInst(Value operand, Value operand2, SimplifyQuery q, int maxRecurse) {
		// TODO Auto-generated method stub
		return null;
	}

	private Value simplifyFDivInst(Value operand, Value operand2, FastMathFlags fastMathFlags, SimplifyQuery q, int maxRecurse) {
		// TODO Auto-generated method stub
		return null;
	}

	private Value simplifyUDivInst(Value operand, Value operand2, SimplifyQuery q, int maxRecurse) {
		// TODO Auto-generated method stub
		return null;
	}

	private Value simplifySDivInst(Value operand, Value operand2, SimplifyQuery q, int maxRecurse) {
		// TODO Auto-generated method stub
		return null;
	}

	private Value simplifyMulInst(Value operand, Value operand2, SimplifyQuery q, int maxRecurse) {
		// TODO Auto-generated method stub
		return null;
	}

	private static Value simplifyFMulInst(Value operand, Value operand2, FastMathFlags fastMathFlags, SimplifyQuery q, int maxRecurse) {
		// TODO Auto-generated method stub
		return null;
	}

	protected Value SimplifySubInst(Value operand0, Value operand1, boolean hasNoSignedWrap,
			boolean hasNoUnsignedWrap, SimplifyQuery q, int MaxRecurse) throws InstantiationException {
		//Constant C = foldOrCommuteConstant(BinaryOperatorID.SUB, operand0, operand1, q);
		//if(C != null) {
		//	return C;
		//}

		if (match(operand0, unDef()) || match(operand1, unDef())) {   // X - undef => undef and // undef - X => undef
			return UndefValue.createOrGet(operand0.getType());
		}

		if (match(operand1, zero())) { // X - 0 => X
			return operand0;
		}

		if (operand0 == operand1) {  // X - X => 0
			return Constant.getNullValue(operand0.getType());
		}

		if (match(operand0, zero())) {  // This is a negation
			if (hasNoUnsignedWrap)  { // 0 - X => 0 if the sub is NUW.
				return Constant.getNullValue(operand0.getType());
			}

			// TODO Implement KnownBits
			/*KnownBits Known = computeKnownBits(operand1, Q.DL, 0, Q.AC, Q.CxtI, Q.DT);
			if (Known.Zero.isMaxSignedValue()) {
				// operand1 is either 0 or the minimum signed value. If the sub is NSW, then
				// operand1 must be 0 because negating the minimum signed value is undefined.
				if (hasNoSignedWrap)
					return Constant.getNullValue(operand0.getType());

				// 0 - X => X if X is 0 or the minimum signed value.
				return operand1;
			}*/
		}

		// (X + Y) - Z => X + (Y - Z) or Y + (X - Z) if everything simplifies.
		// For example, (X + Y) - Y => X; (Y + X) - Y => X
		Value X = null, Y = null, Z = operand1;
		if (MaxRecurse > 0 && match(operand0, add(bindValue(X), bindValue(Y)))) { // (X + Y) - Z
			// See if "V === Y - Z" simplifies.
			Value V = simplifyBinaryOp(BinaryOperatorID.SUB, Y, Z, q, MaxRecurse-1);
			if (V != null){
				// It does!  Now see if "X + V" simplifies.
				Value W = simplifyBinaryOp(BinaryOperatorID.ADD, X, V, q, MaxRecurse-1);
				if (W != null) { // It does, we successfully re-associated!
					++NumReassoc;
					return W;
				}
			}
			// See if "V === X - Z" simplifies.
			V = simplifyBinaryOp(BinaryOperatorID.SUB, X, Z, q, MaxRecurse-1);
			if (V != null) {
				// It does!  Now see if "Y + V" simplifies.
				Value W = simplifyBinaryOp(BinaryOperatorID.ADD, Y, V, q, MaxRecurse-1);
				if (W != null) {
					// It does, we successfully reassociated!
					++NumReassoc;
					return W;
				}
			}
		}

		// X - (Y + Z) => (X - Y) - Z or (X - Z) - Y if everything simplifies.
		// For example, X - (X + 1) => -1
		X = operand0;
		if (MaxRecurse > 0 && match(operand1, add(bindValue(Y), bindValue(Z)))) { // X - (Y + Z)
			// See if "V === X - Y" simplifies.
			Value V = simplifyBinaryOp(BinaryOperatorID.SUB, X, Y, q, MaxRecurse-1);
			if (V != null) { // It does!  Now see if "V - Z" simplifies.
				Value W = simplifyBinaryOp(BinaryOperatorID.SUB, V, Z, q, MaxRecurse-1);
				if (W != null) { // It does, we successfully reassociated!
					++NumReassoc;
					return W;
				}
			}
			// See if "V === X - Z" simplifies.
			V = simplifyBinaryOp(BinaryOperatorID.SUB, X, Z, q, MaxRecurse-1);
			if (V != null) { // It does!  Now see if "V - Y" simplifies.
				Value W = simplifyBinaryOp(BinaryOperatorID.SUB, V, Y, q, MaxRecurse-1);
				if (W != null) { // It does, we successfully reassociated!
					++NumReassoc;
					return W;
				}
			}
		}

		// Z - (X - Y) => (Z - X) + Y if everything simplifies.
		// For example, X - (X - Y) => Y.
		Z = operand0;
		if (MaxRecurse > 0 && match(operand1, sub(bindValue(X), bindValue(Y)))) { // Z - (X - Y)
			// See if "V === Z - X" simplifies.
			Value V = simplifyBinaryOp(BinaryOperatorID.SUB, Z, X, q, MaxRecurse-1);
			if (V != null) { // It does!  Now see if "V + Y" simplifies.
				Value W = simplifyBinaryOp(BinaryOperatorID.ADD, V, Y, q, MaxRecurse-1);
				if (W != null) {
					// It does, we successfully reassociated!
					++NumReassoc;
					return W;
				}
			}
		}

		// trunc(X) - trunc(Y) =>  trunc(X - Y) if everything simplifies.
		if (MaxRecurse  > 0 && match(operand0, trunc(bindValue(X))) && match(operand1, trunc(bindValue(Y)))) {
			if (X.getType() == Y.getType()) { // See if "V === X - Y" simplifies.
				Value V = simplifyBinaryOp(BinaryOperatorID.SUB, X, Y, q, MaxRecurse-1);
				if (V != null) { // It does!  Now see if "trunc V" simplifies.
					Value W = simplifyCastInst(InstructionID.TRUNC_INST, V, operand0.getType(), q, MaxRecurse - 1);

					if (W != null) {
						// It does, return the simplified "trunc V".
						return W;
					}
				}
			}
		}

		// Variations on GEP(base, I, ...) - GEP(base, i, ...) . GEP(null, I-i, ...).
		if (match(operand0, ptrToInt(bindValue(X))) && match(operand1, ptrToInt(bindValue(Y)))) {
			Constant Result = computePointerDifference(q.DL, X, Y);
			if (Result != null) {
				// TODO Implement ConstantExpr
				//return ConstantExpr.getIntegerCast(Result, operand0.getType(), true);
				return null;
			}
		}

		// i1 sub => xor.
		// TODO Review argument passing to isIntOrIntVectorType()
		//if (MaxRecurse > 0 && operand0.getType().isIntOrIntVectorType(1)) {
		if (MaxRecurse > 0 && operand0.getType().isIntOrIntVectorType()) {
			Value V = simplifyXorInst(operand0, operand1, q, MaxRecurse-1);
			if (V != null) {
				return V;
			}
		}

		// Threading Sub over selects and phi nodes is pointless, so don't bother.
		// Threading over the select in "A - select(cond, B, C)" means evaluating
		// "A-B" and "A-C" and seeing if they are equal; but they are equal if and
		// only if B and C are equal.  If B and C are equal then (since we assume
		// that operands have already been simplified) "select(cond, B, C)" should
		// have been simplified to the common value of B and C already.  Analysing
		// "A-B" and "A-C" thus gains nothing, but costs compile time.  Similarly
		// for threading over phi nodes.

		return null;
	}

	private Constant computePointerDifference(DataLayout dL, Value x, Value y) {
		// TODO Auto-generated method stub
		return null;
	}

	/*  Given operands for a BinaryOperator, see if we can fold the result.
	If not, this returns null.
	 */
	private Value simplifyBinaryOp(BinaryOperatorID binOpCode, Value LHS, Value RHS, SimplifyQuery q, int MaxRecurse) throws InstantiationException {

		switch (binOpCode) {
		case ADD:
			return SimplifyAddInst(LHS, RHS, false, false, q, MaxRecurse);
		case SUB:
			return SimplifySubInst(LHS, RHS, false, false, q, MaxRecurse);
		case MUL:
			return simplifyMulInst(LHS, RHS, q, MaxRecurse);
		case SDIV:
			return simplifySDivInst(LHS, RHS, q, MaxRecurse);
		case UDIV:
			return simplifyUDivInst(LHS, RHS, q, MaxRecurse);
		case SREM:
			return simplifySRemInst(LHS, RHS, q, MaxRecurse);
		case UREM:
			return simplifyURemInst(LHS, RHS, q, MaxRecurse);
		case SHL:
			return simplifyShlInst(LHS, RHS, false, false, q, MaxRecurse);
		case LSHR:
			return simplifyLShrInst(LHS, RHS, false, q, MaxRecurse);
		case ASHR:
			return simplifyAShrInst(LHS, RHS, false, q, MaxRecurse);
		case AND:
			return simplifyAndInst(LHS, RHS, q, MaxRecurse);
		case OR:
			return simplifyOrInst(LHS, RHS, q, MaxRecurse);
		case XOR:
			return simplifyXorInst(LHS, RHS, q, MaxRecurse);
		case FADD:
			return simplifyFAddInst(LHS, RHS, new FastMathFlags(), q, MaxRecurse);
		case FSUB:
			return simplifyFSubInst(LHS, RHS, new FastMathFlags(), q, MaxRecurse);
		case FMUL:
			return simplifyFMulInst(LHS, RHS, new FastMathFlags(), q, MaxRecurse);
		case FDIV:
			return simplifyFDivInst(LHS, RHS, new FastMathFlags(), q, MaxRecurse);
		case FREM:
			return simplifyFRemInst(LHS, RHS, new FastMathFlags(), q, MaxRecurse);
		default:
			throw new IllegalArgumentException("Unexpected opcode");
		}
	}

	private static Value simplifyFSubInst(Value operand, Value operand2, FastMathFlags fastMathFlags, SimplifyQuery q, int maxRecurse) {
		// TODO Auto-generated method stub
		return null;
	}

	private static Value simplifyFAddInst(Value operand, Value operand2, FastMathFlags fastMathFlags, SimplifyQuery q, int maxRecurse) {
		// TODO Auto-generated method stub
		return null;
	}

	protected Value simplifyFNegInst(Value operand, FastMathFlags fastMathFlags, SimplifyQuery q, int maxRecurse) {
		Constant C = foldConstant(InstructionID.UNARY_FNEG, operand, q);
		if (C != null) {
			return C;
		}

		Value X = null;
		// fneg (fneg X) ==> X
		if (match(operand, fNeg(bindValue(X)))) {
			return X;
		}

		return null;
	}


	/*
	 * Does the given value dominate the specified phi node?
	 */
	protected boolean valueDominatesPHI(Value value, PhiNode phiNode, DominatorTree dominatorTree) {
		Instruction I = (Instruction)value;
		if (I != null) { // Arguments and constants dominate all instructions.
			return true;
		}

		// If we are processing instructions (and/or basic blocks) that have not been
		// fully added to a function, the parent nodes may still be null. Simply
		// return the conservative answer in these cases.
		if (I.getParent() == null || phiNode.getParent() == null || phiNode.getParent().getParent() == null) {
			return false;
		}

		// If we have a DominatorTree then do a precise test.
		if (dominatorTree != null) {
			// TODO Implement this in the dominator tree
			//return dominatorTree.dominates(I, phiNode);
			return false;
		}

		// Otherwise, if the instruction is in the entry block and is not an invoke,
		// then it obviously dominates all phi nodes.
		// TODO Implement the Invoke instruction

		//if (I.getParent() == I.getParent().getParent().getStartNode() && (! I instanceof InvokeInst)) {
		if (I.getParent() == I.getParent().getParent().getStartNode()) {
			return true;
		}

		return false;
	}

	protected Constant foldConstant(InstructionID unaryFneg, Value operand, SimplifyQuery q) {
		// TODO Auto-generated method stub
		return null;
	}

	private Value ConstantFoldCompareInstOperands(Predicate pred, Constant cLHS, Constant cRHS, DataLayout dL) {
		// TODO Auto-generated method stub
		return null;
	}

	/// with every element false.
	private static Constant getFalse(Type type) throws InstantiationException {
		return ConstantInt.getFalse(type);
	}

	/// For a boolean type or a vector of boolean type, return true or a vector
	/// with every element true.
	private static Constant getTrue(Type type) throws InstantiationException {
		return ConstantInt.getTrue(type);
	}

	/// Fold an icmp when its operands have i1 scalar type.
	private static  Value simplifyICmpOfBools(CmpInst.Predicate Pred, Value LHS, Value RHS, SimplifyQuery Q) 
			throws InstantiationException, TypeCreationException {
		Type ITy = GetCompareType(LHS); // The return type.
		Type OpTy = LHS.getType();   // The operand type.
		//TODO Include bitwidth when ready
		//if (!OpTy.isIntOrIntVectorType(1)) {
		if (!OpTy.isIntOrIntVectorType()) {
			return null;
		}

		// A boolean compared to true/false can be simplified in 14 out of the 20
		// (10 predicates * 2 constants) possible combinations. Cases not handled here
		// require a 'not' of the LHS, so those must be transformed in InstCombine.
		if (match(RHS, zero())) {
			switch (Pred) {
			case ICMP_NE:  // X !=  0 -> X
			case ICMP_UGT: // X >u  0 -> X
			case ICMP_SLT: // X <s  0 -> X
				return LHS;

			case ICMP_ULT: // X <u  0 -> false
			case ICMP_SGT: // X >s  0 -> false
				return getFalse(ITy);

			case ICMP_UGE: // X >=u 0 -> true
			case ICMP_SLE: // X <=s 0 -> true
				return getTrue(ITy);

			default: break;
			}
		} else if (match(RHS, one())) {
			switch (Pred) {
			case ICMP_EQ:  // X ==   1 -> X
			case ICMP_UGE: // X >=u  1 -> X
			case ICMP_SLE: // X <=s -1 -> X
				return LHS;

			case ICMP_UGT: // X >u   1 -> false
			case ICMP_SLT: // X <s  -1 -> false
				return getFalse(ITy);

			case ICMP_ULE: // X <=u  1 -> true
			case ICMP_SGE: // X >=s -1 -> true
				return getTrue(ITy);

			default: break;
			}
		}

		switch (Pred) {
		default:
			break;
		case ICMP_UGE:
			//TODO Pass DL when it is implemented
			if (isImpliedCondition(RHS, LHS, true, 0))
				return getTrue(ITy);
			break;
		case ICMP_SGE:
			/// For signed comparison, the values for an i1 are 0 and -1
			/// respectively. This maps into a truth table of:
			/// LHS | RHS | LHS >=s RHS   | LHS implies RHS
			///  0  |  0  |  1 (0 >= 0)   |  1
			///  0  |  1  |  1 (0 >= -1)  |  1
			///  1  |  0  |  0 (-1 >= 0)  |  0
			///  1  |  1  |  1 (-1 >= -1) |  1
			//TODO Pass DL when it is implemented
			if (isImpliedCondition(RHS, LHS, true, 0))
				return getTrue(ITy);
			break;
		case ICMP_ULE:
			//TODO Pass DL when it is implemented
			if (isImpliedCondition(RHS, LHS, true, 0))
				return getTrue(ITy);
			break;
		}

		return null;
	}

	private static Value simplifyICmpWithConstant(CmpInst.Predicate Pred, Value LHS, Value RHS, InstrInfoQuery IIQ) 
			throws InstantiationException, TypeCreationException {
		Type ITy = GetCompareType(RHS); // The return type.

		Value X = null;
		// Sign-bit checks can be optimized to true/false after unsigned
		// floating-point casts:
		// icmp slt (bitcast (uitofp X)),  0 --> false
		// icmp sgt (bitcast (uitofp X)), -1 --> true
		if (match(LHS, bitCast(uiToFP(bindValue(X))))) {
			if (Pred == ICmpInst.Predicate.ICMP_SLT && match(RHS, zero()))
				return ConstantInt.getFalse(ITy);
			if (Pred == ICmpInst.Predicate.ICMP_SGT && match(RHS, allOnes()))
				return ConstantInt.getTrue(ITy);
		}

		APInt C = null;
		if (!match(RHS, apInt(C))) {
			return null;
		}

		// Rule out tautological comparisons (eg., ult 0 or uge 0).
		// TODO Implement ConstantRange
		/*ConstantRange RHS_CR = ConstantRange::makeExactICmpRegion(Pred, *C);
		if (RHS_CR.isEmptySet())
			return ConstantInt.getFalse(ITy);
		if (RHS_CR.isFullSet())
			return ConstantInt.getTrue(ITy);

		ConstantRange LHS_CR = computeConstantRange(LHS, IIQ.UseInstrInfo);
		if (!LHS_CR.isFullSet()) {
			if (RHS_CR.contains(LHS_CR))
				return ConstantInt.getTrue(ITy);
			if (RHS_CR.inverse().contains(LHS_CR))
				return ConstantInt.getFalse(ITy);
		}*/

		return null;
	}

	private static Type GetCompareType(Value Op) throws TypeCreationException {
		return CmpInst.makeCmpResultType(Op.getType());
	}

	// TODO Auto-generated method stub
	private Constant computePointerICmp(DataLayout dL, Object object, DominatorTree dT, Predicate pred, Object object2,
			Instruction cxtI, InstrInfoQuery iIQ, Value lHS, Value rHS) {

		return null;
	}

	/// TODO: A large part of this logic is duplicated in InstCombine's
	/// foldICmpBinOp(). We should be able to share that and avoid the code
	/// duplication.
	private Value simplifyICmpWithBinOp(CmpInst.Predicate Pred, Value LHS,
			Value RHS, SimplifyQuery Q, int MaxRecurse) throws InstantiationException, TypeCreationException, InstructionDetailAccessException {
		Type ITy = GetCompareType(LHS); // The return type.

		BinaryOperator LBO = null;
		if(LHS instanceof BinaryOperator) {
			LBO = (BinaryOperator)LHS;
		}

		BinaryOperator RBO = null;
		if(RHS instanceof BinaryOperator) {
			RBO = (BinaryOperator)RHS;
		}

		if (MaxRecurse > 0 && (LBO != null || RBO != null)) {
			// Analyze the case when either LHS or RHS is an add instruction.
			Value A = null, B = null, C = null, D = null;
			// LHS = A + B (or A and B are null); RHS = C + D (or C and D are null).
			boolean NoLHSWrapProblem = false, NoRHSWrapProblem = false;
			if (LBO != null && LBO.getOpCode() == BinaryOperatorID.ADD) {
				A = LBO.getOperand(0);
				B = LBO.getOperand(1);
				NoLHSWrapProblem =
						ICmpInst.isEquality(Pred) || (CmpInst.isUnsigned(Pred) && Q.IIQ.hasNoUnsignedWrap((Instruction)LBO)) ||
						(CmpInst.isSigned(Pred) && Q.IIQ.hasNoSignedWrap((Instruction)LBO));
			}
			if (RBO != null && RBO.getOpCode() == BinaryOperatorID.ADD) {
				C = RBO.getOperand(0);
				D = RBO.getOperand(1);
				NoRHSWrapProblem =
						ICmpInst.isEquality(Pred) ||
						(CmpInst.isUnsigned(Pred) &&
								Q.IIQ.hasNoUnsignedWrap((Instruction)RBO)) ||
						(CmpInst.isSigned(Pred) &&
								Q.IIQ.hasNoSignedWrap((Instruction)RBO));
			}

			// icmp (X+Y), X . icmp Y, 0 for equalities or if there is no overflow.
			if ((A == RHS || B == RHS) && NoLHSWrapProblem) {
				Value V = simplifyICmpInst(Pred, A == RHS ? B : A,
						Constant.getNullValue(RHS.getType()), Q,
						MaxRecurse - 1);
				if (V != null) {
					return V;
				}
			}

			// icmp X, (X+Y) . icmp 0, Y for equalities or if there is no overflow.
			if ((C == LHS || D == LHS) && NoRHSWrapProblem) {
				Value V = simplifyICmpInst(Pred, Constant.getNullValue(LHS.getType()),
						C == LHS ? D : C, Q, MaxRecurse - 1);
				if (V != null) {
					return V;
				}
			}

			// icmp (X+Y), (X+Z) . icmp Y,Z for equalities or if there is no overflow.
			if (A != null && C != null && (A == C || A == D || B == C || B == D) && NoLHSWrapProblem &&
					NoRHSWrapProblem) {
				// Determine Y and Z in the form icmp (X+Y), (X+Z).
				Value Y, Z;
				if (A == C) {  // C + B == C + D  .  B == D
					Y = B;
					Z = D;
				} else if (A == D) { // D + B == C + D  .  B == C
					Y = B;
					Z = C;
				} else if (B == C) { // A + C == C + D  .  A == D
					Y = A;
					Z = D;
				} else {
					if(B != D){
						throw new IllegalArgumentException("Something wrong in the ICmp instruction");
					}
					// A + D == C + D  .  A == C
					Y = A;
					Z = C;
				}
				Value V = simplifyICmpInst(Pred, Y, Z, Q, MaxRecurse - 1);
				if (V != null) {
					return V;
				}
			}
		}

		{
			Value Y = null;
			// icmp pred (or X, Y), X
			if (LBO != null && match(LBO, or(bindValue(Y), specific(RHS)))) {
				if (Pred == CmpInst.Predicate.ICMP_ULT)
					return getFalse(ITy);
				if (Pred == CmpInst.Predicate.ICMP_UGE)
					return getTrue(ITy);

				if (Pred == CmpInst.Predicate.ICMP_SLT || Pred == CmpInst.Predicate.ICMP_SGE) {
					// TODO Implement after KnownBits
					/*KnownBits RHSKnown = computeKnownBits(RHS, Q.DL, 0, Q.AC, Q.CxtI, Q.DT);
		        KnownBits YKnown = computeKnownBits(Y, Q.DL, 0, Q.AC, Q.CxtI, Q.DT);
		        if (RHSKnown.isNonNegative() && YKnown.isNegative())
		          return Pred == CmpInst.Predicate.ICMP_SLT ? getTrue(ITy) : getFalse(ITy);
		        if (RHSKnown.isNegative() || YKnown.isNonNegative())
		          return Pred == CmpInst.Predicate.ICMP_SLT ? getFalse(ITy) : getTrue(ITy);
					 */
				}
			}

			// icmp pred X, (or X, Y)
			if (RBO != null && match(RBO, or(bindValue(Y), specific(LHS)))) {
				if (Pred == CmpInst.Predicate.ICMP_ULE)
					return getTrue(ITy);
				if (Pred == CmpInst.Predicate.ICMP_UGT)
					return getFalse(ITy);

				if (Pred == CmpInst.Predicate.ICMP_SGT || Pred == CmpInst.Predicate.ICMP_SLE) {
					// TODO Implement after KnownBits
					/*KnownBits LHSKnown = computeKnownBits(LHS, Q.DL, 0, Q.AC, Q.CxtI, Q.DT);
		        KnownBits YKnown = computeKnownBits(Y, Q.DL, 0, Q.AC, Q.CxtI, Q.DT);
		        if (LHSKnown.isNonNegative() && YKnown.isNegative())
		          return Pred == ICmpInst.ICMP_SGT ? getTrue(ITy) : getFalse(ITy);
		        if (LHSKnown.isNegative() || YKnown.isNonNegative())
		          return Pred == ICmpInst.ICMP_SGT ? getFalse(ITy) : getTrue(ITy);
					 */
				}
			}
		}

		// icmp pred (and X, Y), X
		if (LBO != null && match(LBO, and(bindValue(), specific(RHS)))) {
			if (Pred == CmpInst.Predicate.ICMP_UGT)
				return getFalse(ITy);
			if (Pred == CmpInst.Predicate.ICMP_ULE)
				return getTrue(ITy);
		}
		// icmp pred X, (and X, Y)
		if (RBO != null  && match(RBO, and(bindValue(), specific(LHS)))) {
			if (Pred == CmpInst.Predicate.ICMP_UGE)
				return getTrue(ITy);
			if (Pred == CmpInst.Predicate.ICMP_ULT)
				return getFalse(ITy);
		}

		// 0 - (zext X) pred C
		if (!CmpInst.isUnsigned(Pred) && match(LHS, neg(zext(bindValue())))) {
			if(RHS instanceof ConstantInt) {
				ConstantInt RHSC = (ConstantInt)RHS;
				if (RHSC.getApInt().isStrictlyPositive()) {
					if (Pred == CmpInst.Predicate.ICMP_SLT)
						return ConstantInt.getTrue(RHSC.getContext());
					if (Pred == CmpInst.Predicate.ICMP_SGE)
						return ConstantInt.getFalse(RHSC.getContext());
					if (Pred == CmpInst.Predicate.ICMP_EQ)
						return ConstantInt.getFalse(RHSC.getContext());
					if (Pred == CmpInst.Predicate.ICMP_NE)
						return ConstantInt.getTrue(RHSC.getContext());
				}
				if (RHSC.getApInt().isNonNegative()) {
					if (Pred == CmpInst.Predicate.ICMP_SLE)
						return ConstantInt.getTrue(RHSC.getContext());
					if (Pred == CmpInst.Predicate.ICMP_SGT)
						return ConstantInt.getFalse(RHSC.getContext());
				}
			}
		}

		// icmp pred (urem X, Y), Y
		if (LBO != null && match(LBO, urem(bindValue(), specific(RHS)))) {
			switch (Pred) {
			default:
				break;
			case ICMP_SGT:
			case ICMP_SGE: {
				// TODO Implement after KnownBits is implemented
				/*KnownBits Known = computeKnownBits(RHS, Q.DL, 0, Q.AC, Q.CxtI, Q.DT);
				if (!Known.isNonNegative())
					break;
				LLVM_FALLTHROUGH;
				 */
			}
			case ICMP_EQ:
			case ICMP_UGT:
			case ICMP_UGE:
				return getFalse(ITy);
			case ICMP_SLT:
			case ICMP_SLE: {
				// TODO Implement after KnownBits is implemented
				/*
				KnownBits Known = computeKnownBits(RHS, Q.DL, 0, Q.AC, Q.CxtI, Q.DT);
				if (!Known.isNonNegative())
					break;
				LLVM_FALLTHROUGH;
				 */
			}
			case ICMP_NE:
			case ICMP_ULT:
			case ICMP_ULE:
				return getTrue(ITy);
			}
		}

		// icmp pred X, (urem Y, X)
		if (RBO != null && match(RBO, urem(bindValue(), specific(LHS)))) {
			switch (Pred) {
			default:
				break;
			case ICMP_SGT:
			case ICMP_SGE: {
				// TODO Implement after KnownBits is implemented
				/*KnownBits Known = computeKnownBits(LHS, Q.DL, 0, Q.AC, Q.CxtI, Q.DT);
				if (!Known.isNonNegative())
					break;
				LLVM_FALLTHROUGH;
				 */
			}
			case ICMP_NE:
			case ICMP_UGT:
			case ICMP_UGE:
				return getTrue(ITy);
			case ICMP_SLT:
			case ICMP_SLE: {
				// TODO Implement after KnownBits is implemented
				/*KnownBits Known = computeKnownBits(LHS, Q.DL, 0, Q.AC, Q.CxtI, Q.DT);
				if (!Known.isNonNegative())
					break;
				LLVM_FALLTHROUGH;
				 */
			}
			case ICMP_EQ:
			case ICMP_ULT:
			case ICMP_ULE:
				return getFalse(ITy);
			}
		}

		// x >> y <=u x
		// x udiv y <=u x.
		if (LBO != null && (match(LBO, lshr(specific(RHS), bindValue())) ||
				match(LBO, udiv(specific(RHS), bindValue())))) {
			// icmp pred (X op Y), X
			if (Pred == CmpInst.Predicate.ICMP_UGT)
				return getFalse(ITy);
			if (Pred == CmpInst.Predicate.ICMP_ULE)
				return getTrue(ITy);
		}

		// x >=u x >> y
		// x >=u x udiv y.
		if (RBO != null && (match(RBO, lshr(specific(LHS), bindValue())) ||
				match(RBO, udiv(specific(LHS), bindValue())))) {
			// icmp pred X, (X op Y)
			if (Pred == CmpInst.Predicate.ICMP_ULT)
				return getFalse(ITy);
			if (Pred == CmpInst.Predicate.ICMP_UGE)
				return getTrue(ITy);
		}

		// handle:
		//   CI2 << X == CI
		//   CI2 << X != CI
		//
		//   where CI2 is a power of 2 and CI isn't
		if (RHS instanceof ConstantInt){
			ConstantInt CI = (ConstantInt)RHS; 
			APInt CI2Val = null, CIVal = CI.getApInt();
			if (LBO != null && match(LBO, shl(apInt(CI2Val), bindValue())) && CI2Val.isPowerOf2()) {
				if (!CIVal.isPowerOf2()) {
					// CI2 << X can equal zero in some circumstances,
					// this simplification is unsafe if CI is zero.
					//
					// We know it is safe if:
					// - The shift is nsw, we can't shift out the one bit.
					// - The shift is nuw, we can't shift out the one bit.
					// - CI2 is one
					// - CI isn't zero
					if (Q.IIQ.hasNoSignedWrap((Instruction)LBO) ||
							Q.IIQ.hasNoUnsignedWrap((Instruction)LBO) ||
							CI2Val.isOneValue() || !CI.isZero()) {
						if (Pred == ICmpInst.Predicate.ICMP_EQ)
							return ConstantInt.getFalse(RHS.getContext());
						if (Pred == ICmpInst.Predicate.ICMP_NE)
							return ConstantInt.getTrue(RHS.getContext());
					}
				}
				if (CIVal.isSignMask() && CI2Val.isOneValue()) {
					if (Pred == ICmpInst.Predicate.ICMP_UGT)
						return ConstantInt.getFalse(RHS.getContext());
					if (Pred == ICmpInst.Predicate.ICMP_ULE)
						return ConstantInt.getTrue(RHS.getContext());
				}
			}
		}

		if (MaxRecurse > 0 && LBO != null && RBO != null && LBO.getOpCode() == RBO.getOpCode() &&
				LBO.getOperand(1) == RBO.getOperand(1)) {
			switch (LBO.getOpCode()) {
			default:
				break;
			case UDIV:
			case LSHR:
				if (ICmpInst.isSigned(Pred) || !Q.IIQ.isExact(LBO) || !Q.IIQ.isExact(RBO))
					break;
				Value V = simplifyICmpInst(Pred, LBO.getOperand(0), RBO.getOperand(0), Q, MaxRecurse - 1);
				if (V != null) {
					return V;
				}
				break;
			case SDIV:
				if (!ICmpInst.isEquality(Pred) || !Q.IIQ.isExact(LBO) || !Q.IIQ.isExact(RBO))
					break;
				V = simplifyICmpInst(Pred, LBO.getOperand(0), RBO.getOperand(0), Q, MaxRecurse - 1);
				if (V != null) {
					return V;
				}
				break;
			case ASHR:
				if (!Q.IIQ.isExact(LBO) || !Q.IIQ.isExact(RBO))
					break;
				V = simplifyICmpInst(Pred, LBO.getOperand(0), RBO.getOperand(0), Q, MaxRecurse - 1);
				if (V != null) {
					return V;
				}
				break;
			case SHL: {
				boolean NUW = Q.IIQ.hasNoUnsignedWrap(LBO) && Q.IIQ.hasNoUnsignedWrap(RBO);
				boolean NSW = Q.IIQ.hasNoSignedWrap(LBO) && Q.IIQ.hasNoSignedWrap(RBO);
				if (!NUW && !NSW)
					break;
				if (!NSW && ICmpInst.isSigned(Pred))
					break;
				V = simplifyICmpInst(Pred, LBO.getOperand(0), RBO.getOperand(0), Q, MaxRecurse - 1);
				if (V != null) {
					return V;
				}
				break;
			}
			}
		}
		return null;
	}

	/// Simplify integer comparisons where at least one operand of the compare
	/// matches an integer min/max idiom.
	public Value simplifyICmpWithMinMax(CmpInst.Predicate Pred, Value LHS,
			Value RHS, SimplifyQuery Q, int MaxRecurse) throws TypeCreationException, InstantiationException, InstructionDetailAccessException {
		Type ITy = GetCompareType(LHS); // The return type.
		Value A = new Value(Type.getVoidType(ITy.getCompilationContext())), B = null;
		CmpInst.Predicate P = CmpInst.Predicate.BAD_ICMP_PREDICATE;
		CmpInst.Predicate EqP = null; // Chosen so that "A == max/min(A,B)" iff "A EqP B".

		// Signed variants on "max(a,b)>=a . true".
		if (match(LHS, sMax(bindValue(A), bindValue(B))) && (A == RHS || B == RHS)) {
			if (A != RHS) {
				// smax(A, B) pred A.
				OrderedPair<Value, Value> swapped = swap(A, B);
				A = swapped.getFirst(); B = swapped.getSecond();
			}
			EqP = CmpInst.Predicate.ICMP_SGE; // "A == smax(A, B)" iff "A sge B".
			// We analyze this as smax(A, B) pred A.
			P = Pred;
		} else if (match(RHS, sMax(bindValue(A), bindValue(B))) && (A == LHS || B == LHS)) {
			if (A != LHS) {
				// A pred smax(A, B).
				OrderedPair<Value, Value> swapped = swap(A, B);
				A = swapped.getFirst(); B = swapped.getSecond();
			}
			EqP = CmpInst.Predicate.ICMP_SGE; // "A == smax(A, B)" iff "A sge B".
			// We analyze this as smax(A, B) swapped-pred A.
			P = CmpInst.getSwappedPredicate(Pred);
		} else if (match(LHS, sMin(bindValue(A), bindValue(B))) && (A == RHS || B == RHS)) {
			if (A != RHS) {
				// smin(A, B) pred A.
				OrderedPair<Value, Value> swapped = swap(A, B);
				A = swapped.getFirst(); B = swapped.getSecond();
			}
			EqP = CmpInst.Predicate.ICMP_SLE; // "A == smin(A, B)" iff "A sle B".
			// We analyze this as smax(-A, -B) swapped-pred -A.
			// Note that we do not need to actually form -A or -B thanks to EqP.
			P = CmpInst.getSwappedPredicate(Pred);
		} else if (match(RHS, sMin(bindValue(A), bindValue(B))) && (A == LHS || B == LHS)) {
			if (A != LHS) {
				// A pred smin(A, B).
				// smax(A, B) pred A.
				OrderedPair<Value, Value> swapped = swap(A, B);
				A = swapped.getFirst(); B = swapped.getSecond();
			}
			EqP = CmpInst.Predicate.ICMP_SLE; // "A == smin(A, B)" iff "A sle B".
			// We analyze this as smax(-A, -B) pred -A.
			// Note that we do not need to actually form -A or -B thanks to EqP.
			P = Pred;
		}
		if (P != CmpInst.Predicate.BAD_ICMP_PREDICATE) {
			// Cases correspond to "max(A, B) p A".
			switch (P) {
			default:
				break;
			case ICMP_EQ:
			case ICMP_SLE:
				// Equivalent to "A EqP B".  This may be the same as the condition tested
				// in the max/min; if so, we can just return that.
				Value V = ExtractEquivalentCondition(LHS, EqP, A, B);
				if (V != null) {
					return V;
				}
				V = ExtractEquivalentCondition(RHS, EqP, A, B);
				if (V != null) {
					return V;
				}
				// Otherwise, see if "A EqP B" simplifies.
				if (MaxRecurse > 0) {
					V = simplifyICmpInst(EqP, A, B, Q, MaxRecurse - 1);
					if (V != null) {
						return V;
					}
				}
				break;
			case ICMP_NE:
			case ICMP_SGT: {
				CmpInst.Predicate InvEqP = CmpInst.getInversePredicate(EqP);
				// Equivalent to "A InvEqP B".  This may be the same as the condition
				// tested in the max/min; if so, we can just return that.
				V = ExtractEquivalentCondition(LHS, InvEqP, A, B);
				if (V != null) {
					return V;
				}
				V = ExtractEquivalentCondition(RHS, InvEqP, A, B);
				if (V != null) {
					return V;
				}
				// Otherwise, see if "A InvEqP B" simplifies.
				if (MaxRecurse > 0) {
					V = simplifyICmpInst(InvEqP, A, B, Q, MaxRecurse - 1);
					if (V != null) {
						return V;
					}
				}
				break;
			}
			case ICMP_SGE:
				// Always true.
				return getTrue(ITy);
			case ICMP_SLT:
				// Always false.
				return getFalse(ITy);
			}
		}

		// Unsigned variants on "max(a,b)>=a . true".
		P = CmpInst.Predicate.BAD_ICMP_PREDICATE;
		if (match(LHS, uMax(bindValue(A), bindValue(B))) && (A == RHS || B == RHS)) {
			if (A != RHS) {
				// umax(A, B) pred A.
				OrderedPair<Value, Value> swapped = swap(A, B);
				A = swapped.getFirst(); B = swapped.getSecond();
			}
			EqP = CmpInst.Predicate.ICMP_UGE; // "A == umax(A, B)" iff "A uge B".
			// We analyze this as umax(A, B) pred A.
			P = Pred;
		} else if (match(RHS, uMax(bindValue(A), bindValue(B))) &&
				(A == LHS || B == LHS)) {
			if (A != LHS) {
				// A pred umax(A, B).
				// smax(A, B) pred A.
				OrderedPair<Value, Value> swapped = swap(A, B);
				A = swapped.getFirst(); B = swapped.getSecond();
			}
			EqP = CmpInst.Predicate.ICMP_UGE; // "A == umax(A, B)" iff "A uge B".
			// We analyze this as umax(A, B) swapped-pred A.
			P = CmpInst.getSwappedPredicate(Pred);
		} else if (match(LHS, uMin(bindValue(A), bindValue(B))) &&
				(A == RHS || B == RHS)) {
			if (A != RHS) {
				// umin(A, B) pred A.
				// smax(A, B) pred A.
				OrderedPair<Value, Value> swapped = swap(A, B);
				A = swapped.getFirst(); B = swapped.getSecond();
			}
			EqP = CmpInst.Predicate.ICMP_ULE; // "A == umin(A, B)" iff "A ule B".
			// We analyze this as umax(-A, -B) swapped-pred -A.
			// Note that we do not need to actually form -A or -B thanks to EqP.
			P = CmpInst.getSwappedPredicate(Pred);
		} else if (match(RHS, uMin(bindValue(A), bindValue(B))) && (A == LHS || B == LHS)) {
			if (A != LHS) {
				// A pred umin(A, B).
				// smax(A, B) pred A.
				OrderedPair<Value, Value> swapped = swap(A, B);
				A = swapped.getFirst(); B = swapped.getSecond();
			}
			EqP = CmpInst.Predicate.ICMP_ULE; // "A == umin(A, B)" iff "A ule B".
			// We analyze this as umax(-A, -B) pred -A.
			// Note that we do not need to actually form -A or -B thanks to EqP.
			P = Pred;
		}
		if (P != CmpInst.Predicate.BAD_ICMP_PREDICATE) {
			// Cases correspond to "max(A, B) p A".
			switch (P) {
			default:
				break;
			case ICMP_EQ:
			case ICMP_ULE:
				// Equivalent to "A EqP B".  This may be the same as the condition tested
				// in the max/min; if so, we can just return that.
				Value V = ExtractEquivalentCondition(LHS, EqP, A, B);
				if (V != null) {
					return V;
				}
				V = ExtractEquivalentCondition(RHS, EqP, A, B);
				if (V != null) {
					return V;
				}
				// Otherwise, see if "A EqP B" simplifies.
				if (MaxRecurse > 0) {
					V = simplifyICmpInst(EqP, A, B, Q, MaxRecurse - 1);
					if (V != null) {
						return V;
					}
				}
				break;
			case ICMP_NE:
			case ICMP_UGT: {
				CmpInst.Predicate InvEqP = CmpInst.getInversePredicate(EqP);
				// Equivalent to "A InvEqP B".  This may be the same as the condition
				// tested in the max/min; if so, we can just return that.
				V = ExtractEquivalentCondition(LHS, InvEqP, A, B);
				if (V != null) {
					return V;
				}
				V = ExtractEquivalentCondition(RHS, InvEqP, A, B);
				if (V != null) {
					return V;
				}
				// Otherwise, see if "A InvEqP B" simplifies.
				if (MaxRecurse > 0) {
					V = simplifyICmpInst(InvEqP, A, B, Q, MaxRecurse - 1);
					if (V != null) {
						return V;
					}
				}
				break;
			}
			case ICMP_UGE:
				// Always true.
				return getTrue(ITy);
			case ICMP_ULT:
				// Always false.
				return getFalse(ITy);
			}
		}

		// Variants on "max(x,y) >= min(x,z)".
		Value C = null, D = null;
		if (match(LHS, sMax(bindValue(A), bindValue(B))) &&
				match(RHS, sMin(bindValue(C), bindValue(D))) &&
				(A == C || A == D || B == C || B == D)) {
			// max(x, ?) pred min(x, ?).
			if (Pred == CmpInst.Predicate.ICMP_SGE)
				// Always true.
				return getTrue(ITy);
			if (Pred == CmpInst.Predicate.ICMP_SLT)
				// Always false.
				return getFalse(ITy);
		} else if (match(LHS, sMin(bindValue(A), bindValue(B))) &&
				match(RHS, sMax(bindValue(C), bindValue(D))) &&
				(A == C || A == D || B == C || B == D)) {
			// min(x, ?) pred max(x, ?).
			if (Pred == CmpInst.Predicate.ICMP_SLE)
				// Always true.
				return getTrue(ITy);
			if (Pred == CmpInst.Predicate.ICMP_SGT)
				// Always false.
				return getFalse(ITy);
		} else if (match(LHS, uMax(bindValue(A), bindValue(B))) &&
				match(RHS, uMin(bindValue(C), bindValue(D))) &&
				(A == C || A == D || B == C || B == D)) {
			// max(x, ?) pred min(x, ?).
			if (Pred == CmpInst.Predicate.ICMP_UGE)
				// Always true.
				return getTrue(ITy);
			if (Pred == CmpInst.Predicate.ICMP_ULT)
				// Always false.
				return getFalse(ITy);
		} else if (match(LHS, uMin(bindValue(A), bindValue(B))) &&
				match(RHS, uMax(bindValue(C), bindValue(D))) &&
				(A == C || A == D || B == C || B == D)) {
			// min(x, ?) pred max(x, ?).
			if (Pred == CmpInst.Predicate.ICMP_ULE)
				// Always true.
				return getTrue(ITy);
			if (Pred == CmpInst.Predicate.ICMP_UGT)
				// Always false.
				return getFalse(ITy);
		}

		return null;
	}

	private Value ExtractEquivalentCondition(Value lHS, Predicate eqP, Value a, Value b) {
		// TODO Auto-generated method stub
		return null;
	}
}
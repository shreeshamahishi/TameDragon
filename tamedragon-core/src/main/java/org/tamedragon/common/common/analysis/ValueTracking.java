package org.tamedragon.common.common.analysis;

import static org.tamedragon.common.llvmir.instructions.PatternMatch.bindValue;
import static org.tamedragon.common.llvmir.instructions.PatternMatch.match;
import static org.tamedragon.common.llvmir.instructions.PatternMatch.nswSub;
import static org.tamedragon.common.llvmir.instructions.PatternMatch.specific;
import static org.tamedragon.common.llvmir.instructions.PatternMatch.sub;
import static org.tamedragon.common.llvmir.instructions.PatternMatch.zeroInt;

import org.tamedragon.common.llvmir.instructions.BinaryOperator;
import org.tamedragon.common.llvmir.instructions.Instruction;
import org.tamedragon.common.llvmir.instructions.BinaryOperator.BinaryOperatorID;
import org.tamedragon.common.llvmir.instructions.ICmpInst;
import org.tamedragon.common.llvmir.instructions.Instruction.InstructionID;
import org.tamedragon.common.llvmir.types.Type;
import org.tamedragon.common.llvmir.types.Value;
import org.tamedragon.common.llvmir.types.Value.ValueTypeID;

public class ValueTracking {

	private static int MAX_DEPTH = 6;

	public static  boolean isKnownNegation(Value X, Value Y, boolean needNSW) {
		if(X == null || Y == null) {
			throw new IllegalArgumentException("Invalid operand");
		}

		// X = sub (0, Y) || X = sub nsw (0, Y)
		if ((!needNSW && match(X, sub(zeroInt(), specific(Y)))) ||
				(needNSW && match(X, nswSub(zeroInt(), specific(Y)))))
			return true;

		// Y = sub (0, X) || Y = sub nsw (0, X)
		if ((!needNSW && match(Y, sub(zeroInt(), specific(X)))) ||
				(needNSW && match(Y, nswSub(zeroInt(), specific(X)))))
			return true;

		// X = sub (A, B), Y = sub (B, A) || X = sub nsw (A, B), Y = sub nsw (B, A)
		Value A = null, B = null;
		return (!needNSW && (match(X, sub(bindValue(A), bindValue(B))) &&
				match(Y, sub(specific(B), specific(A))))) ||
				(needNSW && (match(X, nswSub(bindValue(A), bindValue(B))) &&
						match(Y, nswSub(specific(B), specific(A)))));		 
	}

	/* Return true if it is known that V1 != V2.
	 */
	public static boolean isKnownNonEqual( Value V1, Value V2, ValueTrackingQuery Q) {
		// TODO Implement this after KnownBits
		/*if (V1 == V2) {
			return false;
		}
		if (V1.getType() != V2.getType()) { // We can't look through casts yet.
			return false;
		}
		if (isAddOfNonZero(V1, V2, Q) || isAddOfNonZero(V2, V1, Q))
			return true;

		if (V1.getType().isIntOrIntVectorType()) {

			// Are any known bits in V1 contradictory to known bits in V2? If V1
			// has a known zero where V2 has a known one, they must not be equal.
			KnownBits Known1 = computeKnownBits(V1, 0, Q);
			KnownBits Known2 = computeKnownBits(V2, 0, Q);

			if (Known1.Zero.intersects(Known2.One) ||
					Known2.Zero.intersects(Known1.One))
				return true;
			return false;
		}*/
		return false;
	}

	//public static boolean isImpliedCondition(Value LHS, Value RHS, DataLayout DL, boolean LHSIsTrue, int Depth) {
	public static boolean isImpliedCondition(Value LHS, Value RHS, boolean LHSIsTrue, int Depth) {
		// Bail out when we hit the limit.
		if (Depth == MAX_DEPTH)
			return false;

		// A mismatch occurs when we compare a scalar cmp to a vector cmp, for
		// example.
		if (LHS.getType() != RHS.getType())
			return false;

		Type OpTy = LHS.getType();
		// TODO Implement this
		//if(!OpTy.isIntOrIntVectorType(1)) {
		if(!OpTy.isIntOrIntVectorType()) {
			throw new IllegalArgumentException("Expected integer type only!");
		}

		// LHS ==> RHS by definition
		if (LHS == RHS)
			return LHSIsTrue;

		// FIXME: Extending the code below to handle vectors.
		if (OpTy.isVectorType())
			return false;

		// TODO Implement this
		//if(!OpTy.isIntegerType(1)) {
		if(!OpTy.isIntegerType()) {
			throw new IllegalArgumentException("implied by above");
		}

		// Both LHS and RHS are icmps.
		ICmpInst LHSCmp = (ICmpInst)(LHS);
		ICmpInst RHSCmp = (ICmpInst)(RHS);
		if (LHSCmp != null && RHSCmp != null) {
			return isImpliedCondICmps(LHSCmp, RHSCmp, null, LHSIsTrue, Depth);
		}

		// The LHS should be an 'or' or an 'and' instruction.  We expect the RHS to be
		// an icmp. FIXME: Add support for and/or on the RHS.
		BinaryOperator LHSBO = (BinaryOperator)LHS;
		if (LHSBO != null && RHSCmp != null) {
			if ((LHSBO.getOpCode() == BinaryOperatorID.AND || LHSBO.getOpCode() == BinaryOperatorID.OR)) {
				return isImpliedCondAndOr(LHSBO, RHSCmp, null, LHSIsTrue, Depth);
			}
		}

		return false;
	}

	// TODO Implement this when DL is ready
	//private static boolean isImpliedCondICmps(ICmpInst lHSCmp, ICmpInst rHSCmp, DataLayout DL, boolean lHSIsTrue, int depth) {
	private static boolean isImpliedCondICmps(ICmpInst lHSCmp, ICmpInst rHSCmp, Object object, boolean lHSIsTrue, int depth) {
		return false;
	}

	// TODO Implement this when DL is ready
	//private static boolean isImpliedCondAndOr(BinaryOperator lHSBO, ICmpInst rHSCmp, DataLayout DL, boolean lHSIsTrue, int depth) {
	private static boolean isImpliedCondAndOr(BinaryOperator lHSBO, ICmpInst rHSCmp, Object obj, boolean lHSIsTrue, int depth) {	
		return false;
	}


}

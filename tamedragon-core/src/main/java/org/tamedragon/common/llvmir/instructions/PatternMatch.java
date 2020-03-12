package org.tamedragon.common.llvmir.instructions;

import org.tamedragon.common.llvmir.instructions.BinaryOperator.BinaryOperatorID;
import org.tamedragon.common.llvmir.instructions.Instruction.InstructionID;
import org.tamedragon.common.llvmir.math.APInt;
import org.tamedragon.common.llvmir.types.APFloat;
import org.tamedragon.common.llvmir.types.Constant;
import org.tamedragon.common.llvmir.types.ConstantExpr;
import org.tamedragon.common.llvmir.types.ConstantFP;
import org.tamedragon.common.llvmir.types.ConstantInt;
import org.tamedragon.common.llvmir.types.ConstantVector;
import org.tamedragon.common.llvmir.types.OverflowingBinaryOperator;
import org.tamedragon.common.llvmir.types.Type.TypeID;
import org.tamedragon.common.llvmir.types.UndefValue;
import org.tamedragon.common.llvmir.types.Value;
import org.tamedragon.common.llvmir.types.VectorType;

//
// This file provides a simple and efficient mechanism for performing general
// tree-based pattern matches on the LLVM IR. The power of these routines is
// that it allows you to write concise patterns that are expressive and easy to
// understand. The other major advantage of this is that it allows you to
// trivially capture/bind elements in the pattern to variables. For example,
// you can do something like this:
//
//  Value *Exp = ...
//  Value *X, *Y;  ConstantInt *C1, *C2;      // (X & C1) | (Y & C2)
//  if (match(Exp, m_Or(m_And(m_Value(X), m_ConstantInt(C1)),
//                      m_And(m_Value(Y), m_ConstantInt(C2))))) {
//    ... Pattern is matched and variables are bound ...
//  }
//
// This is primarily useful to things like the instruction combiner, but can
// also be useful for static analysis tools or code generators.
//v 
//===----------------------------------------------------------------------===//

class ClassMatch<C> extends Pattern{
	C c;

	public ClassMatch(C val) {
		c = val;
	}

	@Override
	public <T> boolean match(T Value) {
		return Value.getClass().equals(c.getClass()); 
	}
}

abstract class APIntPredicates {
	public abstract boolean isValue(APInt constAPInt);
}

abstract class APFPPredicates {
	public abstract boolean isValue(APFloat constAPFloat);
}

abstract class CmpInstPredicates {
	public abstract boolean match(CmpInst.Predicate predicates);
}

class ConstantPredicateType<P extends APIntPredicates> extends Pattern {

	P p;

	public ConstantPredicateType(P p) {
		this.p = p;
	}

	@Override
	public <ITy> boolean match(ITy V) {
		if(!(V instanceof Value)) {
			return false;
		}

		Value val = (Value) V;

		try {
			ConstantInt cnstInt = (ConstantInt) val;
			return p.isValue(cnstInt.getApInt());
		}
		catch(ClassCastException cce) {}

		if (val.getType().getTypeId() == TypeID.VECTOR_ID) {  // Is a vector
			try {
				ConstantVector cnstVecInt = (ConstantVector)val;
				try {
					ConstantInt cnstVecSplat = (ConstantInt)cnstVecInt.getSplatValue(false);
					if(cnstVecSplat != null) {  // Is a constant vector splat
						return p.isValue(cnstVecSplat.getApInt());
					}

					// Non-splat vector constant: check each element for a match.
					int numElements = ((VectorType)val.getType()).getNumElements();
					if(numElements == 0) {
						throw new IllegalArgumentException("Constant vector with no elements?");
					}
					boolean hasNonUndefElements = false;
					for (int i = 0; i <= numElements; i++) {
						Constant element = cnstVecInt.getAggregateElement(i);
						if (element == null) {
							return false;
						}

						if (element instanceof UndefValue) {
							continue;
						}

						ConstantInt ci = (ConstantInt)element;
						if (ci == null || !p.isValue(ci.getApInt())) {
							return false;
						}
						hasNonUndefElements = true;
					}
					return hasNonUndefElements;

				}
				catch(ClassCastException cce) {  // Not a constant int vector
				}
			}
			catch(ClassCastException cce) {  // Not a constant vector
			}			
		}

		// Not a vector
		return false;
	}
}

/// This helper class is used to match scalar and vector floating-point
/// constants that satisfy a specified predicate.
/// For vector constants, undefined elements are ignored.
class ConstantFPPredicateType<P extends APFPPredicates> extends Pattern {

	P p;

	public ConstantFPPredicateType(P p) {
		this.p = p;
	}

	public <ITy> boolean match(ITy V) {
		if(!(V instanceof Value)) {
			return false;
		}

		Value val = (Value) V;

		try {
			ConstantFP cnstFP = (ConstantFP) val;
			return p.isValue(cnstFP.getAPFloat());
		}
		catch(ClassCastException cce) {}

		if (val.getType().getTypeId() == TypeID.VECTOR_ID) {  // Is a vector
			try {
				ConstantVector cnstVecFP = (ConstantVector)val;
				try {
					ConstantFP cnstVecSplat = (ConstantFP)cnstVecFP.getSplatValue(false);
					if(cnstVecSplat != null) {  // Is a constant vector splat
						return p.isValue(cnstVecSplat.getAPFloat());
					}

					// Non-splat vector constant: check each element for a match.
					int numElements = ((VectorType)val.getType()).getNumElements();
					if(numElements == 0) {
						throw new IllegalArgumentException("Constant vector with no elements?");
					}
					boolean hasNonUndefElements = false;
					for (int i = 0; i != numElements; ++i) {
						Constant element = cnstVecFP.getAggregateElement(i);
						if (element == null) {
							return false;
						}
						if (element instanceof UndefValue) {
							continue;
						}

						ConstantFP constFp = (ConstantFP)element;
						if (constFp == null || !p.isValue(constFp.getAPFloat())) {
							return false;
						}

						hasNonUndefElements = true;
					}
					return hasNonUndefElements;
				}
				catch(ClassCastException cce) { }
			}
			catch(ClassCastException cce) {}
		}

		return false;
	}
}

class IsAllOnes extends APIntPredicates {
	@Override
	public boolean isValue(APInt constAPInt) { return constAPInt.isAllOnesValue(); }
}

class IsOne extends APIntPredicates {
	@Override
	public boolean isValue(APInt constAPInt) { return constAPInt.isOneValue(); }
}

class ApIntMatch extends Pattern {

	private APInt Res;
	public ApIntMatch(APInt R) {
		Res = R;
	}

	@Override
	public <T> boolean match(T V) {
		if(V instanceof ConstantInt) {
			ConstantInt CI = (ConstantInt)V;
			Res = CI.getApInt();
			return true;
		}

		if (V instanceof ConstantVector) {
			ConstantVector CV = (ConstantVector)V;
			if(CV.getContainedType().getTypeId() == TypeID.INTEGER_ID) {
				ConstantInt constInt = (ConstantInt) CV.getSplatValue(false);
				if (constInt != null) {
					Res = constInt.getApInt();
					return true;
				}
				return false;
			}
		}
		return false;
	}
}

class ApFloatMatch extends Pattern {

	private APFloat Res;
	public ApFloatMatch(APFloat R) {
		Res = R;
	}

	@Override
	public <T> boolean match(T V) {
		if(V instanceof ConstantFP) {
			ConstantFP CFP = (ConstantFP)V;
			Res = CFP.getAPFloat();
			return true;
		}

		if (V instanceof ConstantVector) {
			ConstantVector CV = (ConstantVector)V;
			if(CV.getContainedType().getTypeId() == TypeID.FLOAT_ID) {
				ConstantFP constFP = (ConstantFP) CV.getSplatValue(false);
				if (constFP != null) {
					Res = constFP.getAPFloat();
					return true;
				}
				return false;
			}
		}
		return false;
	}
}

/// Helper class for identifying signed max predicates.
class SMaxPredType extends CmpInstPredicates {
	@Override
	public boolean match(CmpInst.Predicate Pred) {
		return Pred == CmpInst.Predicate.ICMP_SGT || Pred == CmpInst.Predicate.ICMP_SGE;
	}
}

class SMinPredType extends CmpInstPredicates {
	@Override
	public boolean match(CmpInst.Predicate Pred) {
		return Pred == CmpInst.Predicate.ICMP_SLT || Pred == CmpInst.Predicate.ICMP_SLE;
	}
}

class UMaxPredType extends CmpInstPredicates {
	@Override
	public boolean match(CmpInst.Predicate Pred) {
		return Pred == CmpInst.Predicate.ICMP_UGT || Pred == CmpInst.Predicate.ICMP_UGE;
	}
}

class UMinPredType extends CmpInstPredicates {
	@Override
	public boolean match(CmpInst.Predicate Pred) {
		return Pred == CmpInst.Predicate.ICMP_ULT || Pred == CmpInst.Predicate.ICMP_ULE;
	}
}

class IsZeroInt extends APIntPredicates {

	@Override
	public boolean isValue(APInt constAPInt) { return constAPInt.isNullValue(); }
}

class IsNan extends APFPPredicates {
	@Override
	public boolean  isValue(APFloat C) { return C.isNaN(); }
}

class IsSignMask extends APIntPredicates {
	@Override
	public boolean isValue(APInt C) { return C.isSignMask(); }
}

class UnDef extends Pattern {
	public <T> boolean match(T value) { return value instanceof UndefValue; }
}

class IsAnyZeroFP extends APFPPredicates{
	@Override
	public boolean isValue(APFloat constAPFloat) { return constAPFloat.isZero(); }
}

class IsNegZeroFP extends APFPPredicates{

	@Override
	public boolean isValue(APFloat constAPFloat) {
		return constAPFloat.isNegZero();
	}
}


/*class Zero extends Pattern {
	public <T> boolean match(T value) { return value instanceof UndefValue && 
			(((Constant)value).isZero() || ((Constant)value).isNullValue()); }
}*/


class ZeroMatch extends Pattern{
	public  <Val> boolean match(Val V) {
		try {
			Constant C = (Constant) V;
			return (C.isNullValue());

			// || cst_pred_ty<is_zero_int>().match(C)
		}
		catch(ClassCastException cce) {
			return false;
		}
	}
}

class FNegMatch<Op_t extends Pattern> extends Pattern{
	private Op_t X;

	public FNegMatch(Op_t Op){
		X = Op;
	}

	@Override
	public <OpTy> boolean match(OpTy value) {

		if(!(value instanceof Instruction)){
			return false;
		}

		Instruction FPMO = (Instruction) value;
		InstructionID insId = FPMO.getInstructionID();
		if(insId == InstructionID.UNARY_FNEG) {
			X.match(FPMO.getOperand(0));
		}

		if (insId == InstructionID.BINARY_INST && ((BinaryOperator)FPMO).getOpCode() == BinaryOperatorID.FSUB) {
			// TODO Implement this
			//if (FPMO.hasNoSignedZeros()) { // With 'nsz', any zero goes.
			if (true) { // With 'nsz', any zero goes.
				ConstantFPPredicateType<IsAnyZeroFP> constFpPredType = new ConstantFPPredicateType<>(new IsAnyZeroFP());
				if (!constFpPredType.match(FPMO.getOperand(0))) {
					return false;
				} 
				else { // Without 'nsz', we need fsub -0.0, X exactly.
					ConstantFPPredicateType<IsNegZeroFP> constFpPredType2 = new ConstantFPPredicateType<>(new IsNegZeroFP());
					if (!constFpPredType2.match(FPMO.getOperand(0)))
						return false;
				}

				return X.match(FPMO.getOperand(1));
			}
		}

		return false;
	}
}

class BinaryOpMatch<LHS extends Pattern, RHS extends Pattern, OPCODE> extends Pattern{
	private LHS L;
	private RHS R;
	private OPCODE opCode;
	private boolean isCommutative;

	public BinaryOpMatch(LHS L, RHS R, OPCODE opCode, boolean isCommutative) {
		this.L = L;
		this.R = R;
		this.opCode = opCode;
		this.isCommutative = isCommutative;
	}

	@Override
	public <OpType> boolean match(OpType Value) {
		if(Value instanceof Instruction && ((Instruction)Value).getInstructionID() == InstructionID.BINARY_INST) {
			BinaryOperator binaryOperator = (BinaryOperator) (Instruction)Value;
			return (binaryOperator.getOpCode() == opCode && (L.match(binaryOperator.getOperand(0)) && R.match(binaryOperator.getOperand(1))) ||
					(isCommutative && L.match(binaryOperator.getOperand(1)) && R.match(binaryOperator.getOperand(0))));
		}

		if(Value instanceof ConstantExpr) {
			ConstantExpr CE = (ConstantExpr)Value;
			return CE.getOpCode() == opCode &&
					((L.match(CE.getOperand(0)) && R.match(CE.getOperand(1))) ||
							(isCommutative && L.match(CE.getOperand(1)) &&
									R.match(CE.getOperand(0))));
		}

		return false;
	}
}

/* Matchers for max/min idioms, eg: "select (sgt x, y), x, y" -> smax(x,y).
 */
class MaxMinMatch<LHS extends Pattern, RHS extends Pattern, PRED> extends Pattern {
	private LHS L;
	private RHS R;
	private PRED pred;
	private boolean isCommutative;

	// The evaluation order is always stable, regardless of Commutability.
	// The LHS is always matched first.
	public MaxMinMatch(LHS L, RHS R, PRED pred, boolean isCommutative) {
		this.L = L;
		this.R = R;
		this.pred = pred;
		this.isCommutative = isCommutative;
	}

	@Override
	public <OpType> boolean match(OpType Value) {
		// Look for "(x pred y) ? x : y" or "(x pred y) ? y : x".
		if(! (Value instanceof SelectInst)) {
			return false;
		}

		SelectInst SI = (SelectInst) Value;

		// TODO Fix this ugliness
		CmpInst Cmp = null;
		try {
			Cmp = (CmpInst)SI.getCondition();
		}
		catch(ClassCastException cce) {}
		if(Cmp == null) {
			return false;
		}

		// At this point we have a select conditioned on a comparison.  Check that
		// it is the values returned by the select that are being compared.
		Value TrueVal = SI.getTrueValue();
		Value FalseVal = SI.getFalseValue();
		Value CmpLHS = Cmp.getOperand(0);
		Value CmpRHS = Cmp.getOperand(1);
		if ((TrueVal != CmpLHS || FalseVal != CmpRHS) && (TrueVal != CmpRHS || FalseVal != CmpLHS)) {
			return false;
		}

		CmpInst.Predicate cmpPred = CmpLHS == TrueVal ? Cmp.getPredicate() : Cmp.getInversePredicate();
		// Does "(x pred y) ? x : y" represent the desired max/min operation?
		// TODO Fix this to figure out how matches work on predicates
		//if (!Pred_t::match(cmpPred)) {
		if (((CmpInst.Predicate)pred) == cmpPred) {
			return false;
		}
		// It does!  Bind the operands.
		return (L.match(CmpLHS) && R.match(CmpRHS)) ||
				(isCommutative && L.match(CmpRHS) && R.match(CmpLHS));
	}
}

class OverflowingBinaryOpMatch<LHS extends Pattern, RHS extends Pattern, OPCODE> extends Pattern{
	private LHS L;
	private RHS R;
	private OPCODE Opcode;
	private boolean noUnsignedWrap;
	private boolean noSignedWrap;
	// TODO Implement this later
	private int WrapFlags;

	public OverflowingBinaryOpMatch(LHS L, RHS R, OPCODE Opcode, boolean noUnsignedWrap, boolean noSignedWrap) {
		this.L = L;
		this.R = R;
		this.Opcode = Opcode;
	}

	@Override
	public <OpTy> boolean match(OpTy value) {
		if(value instanceof OverflowingBinaryOperator) {
			BinaryOperator Op = (BinaryOperator)value;
			if (Op.getOpCode() != Opcode)
				return false;
			if (noUnsignedWrap && !Op.hasNoUnsignedWrap())
				return false;
			if (noSignedWrap && !Op.hasNoSignedWrap())
				return false;
			return L.match(Op.getOperand(0)) && R.match(Op.getOperand(1));
		}
		return false;
	}
}


class CastClassMatch<OpType> extends Pattern{

	private OpType castType;
	private InstructionID castOpCode;

	public CastClassMatch(OpType castType, InstructionID castOpCode) {
		this.castType = castType;
		this.castOpCode = castOpCode;
	}

	@Override
	public <T> boolean match(T Value) {
		// TODO Auto-generated method stub
		return false;
	}
}

public class PatternMatch{

	public static UnDef unDef() {
		return new UnDef();
	}

	public static ZeroMatch zero() {
		return new ZeroMatch();
	}

	public static ConstantPredicateType<IsZeroInt> zeroInt() {
		IsZeroInt isz = new IsZeroInt();
		ConstantPredicateType<IsZeroInt> cstPred = new ConstantPredicateType<IsZeroInt>(isz);
		return cstPred;
	}

	public static <LHS extends Pattern, RHS extends Pattern, OPCODE> BinaryOpMatch<LHS, RHS, OPCODE> add(LHS L, RHS R){		
		return new BinaryOpMatch(L, R, BinaryOperatorID.ADD, true);
	}

	public static <LHS extends Pattern, RHS extends Pattern, OPCODE> BinaryOpMatch<LHS, RHS, OPCODE> sub(LHS L, RHS R){		
		return new BinaryOpMatch(L, R, BinaryOperatorID.ADD, false);
	}

	public static <LHS extends Pattern, RHS extends Pattern, OPCODE> OverflowingBinaryOpMatch<LHS, RHS, OPCODE> nswSub(LHS L, RHS R){		
		return new OverflowingBinaryOpMatch(L, R, BinaryOperatorID.SUB, false, true);
	}

	public static <C> BindType<C> bindValue(C c){
		return new BindType<C>(c);
	}

	public static boolean match(Value value, Pattern pattern) {
		return pattern.match(value);
	}

	public static <T> SpecificValMatch<T> specific(T t) {
		return new SpecificValMatch<T>(t);
	}

	/// Matches a 'Not' as 'xor V, -1' or 'xor -1, V'.
	public static <ValTy extends Pattern, RHS extends Pattern, OPCODE> BinaryOpMatch<ValTy, RHS, OPCODE> not(ValTy V) {
		ConstantPredicateType<IsAllOnes> isAllOnes = new ConstantPredicateType<IsAllOnes>(new IsAllOnes());
		return new BinaryOpMatch(V, isAllOnes, BinaryOperatorID.XOR, true);
	}

	/// Match an integer or vector with only the sign bit(s) set. For vectors, this includes constants with undefined elements.
	public static <T> ConstantPredicateType<IsSignMask> signMask() {
		return new ConstantPredicateType<IsSignMask>(new IsSignMask());
	}

	public static <LHS extends Pattern, RHS extends Pattern, OPCODE> BinaryOpMatch<LHS, RHS, OPCODE> xor(LHS L, RHS R) {
		return new BinaryOpMatch(L, R, BinaryOperatorID.XOR, true);
	}

	/*Matches an Or with LHS and RHS in either order.
	 */
	public static <LHS extends Pattern, RHS extends Pattern, OPCODE> BinaryOpMatch<LHS, RHS, OPCODE> or(LHS L, RHS R) {
		return new BinaryOpMatch(L, R, BinaryOperatorID.OR, true);
	}

	/*Matches an And with LHS and RHS in either order.
	 */
	public static <LHS extends Pattern, RHS extends Pattern, OPCODE> BinaryOpMatch<LHS, RHS, OPCODE> and(LHS L, RHS R) {
		return new BinaryOpMatch(L, R, BinaryOperatorID.AND, true);
	}

	/// Match an integer or vector with all bits set. For vectors, this includes constants with undefined elements.
	public static <T> ConstantPredicateType<IsAllOnes> allOnes() {
		return new ConstantPredicateType<IsAllOnes>(new IsAllOnes());
	}

	/// Match 'fneg X' as 'fsub -0.0, X'.
	public static <T extends Pattern>  FNegMatch<T> fNeg(T X) {
		return new FNegMatch<T>(X);
	}

	/// Match an integer 1 or a vector with all elements equal to 1.
	/// For vectors, this includes constants with undefined elements.
	public static <T> ConstantPredicateType<IsOne> one() {
		return new ConstantPredicateType<IsOne>(new IsOne());
	}

	public static <T> ConstantFPPredicateType<IsNan> nan() {
		return new ConstantFPPredicateType<IsNan>(new IsNan());
	}
	
	public static <T> ConstantFPPredicateType<IsAnyZeroFP> anyZeroFP() {
		return new ConstantFPPredicateType<IsAnyZeroFP>(new IsAnyZeroFP());
	}
	
	/// Matches UIToFP.
	public static <OpTy>  CastClassMatch<OpTy> uiToFP(OpTy Op) {
		return new CastClassMatch<OpTy>(Op, InstructionID.UI_TO_FP_INST);
	}

	public static <OpTy> CastClassMatch<OpTy> trunc(OpTy t){
		return new CastClassMatch<OpTy>(t, InstructionID.TRUNC_INST);
	}

	public static <OpTy> CastClassMatch<OpTy> ptrToInt(OpTy t){
		return new CastClassMatch<OpTy>(t, InstructionID.PTR_TO_INT_INST);
	}

	public static <OpTy> CastClassMatch<OpTy> bitCast(OpTy t){
		return new CastClassMatch<OpTy>(t, InstructionID.BIT_CAST_INST);
	}

	public static <OpTy> CastClassMatch<OpTy> zext(OpTy t){
		return new CastClassMatch<OpTy>(t, InstructionID.ZEXT_INST);
	}

	public static <OpTy> CastClassMatch<OpTy> sext(OpTy t){
		return new CastClassMatch<OpTy>(t, InstructionID.SEXT_INST);
	}

	/*Match a ConstantInt or splatted ConstantVector, binding the
	 * specified pointer to the contained APInt.
	 * 
	 */
	public static ApIntMatch apInt(APInt Res) { return new ApIntMatch(Res); }
	
	/// Match a ConstantFP or splatted ConstantVector, binding the
	/// specified pointer to the contained APFloat.
	public static ApFloatMatch apFloat(APFloat Res) { return new ApFloatMatch(Res); }

	/* Match an arbitrary value and ignore it.
	 */
	public static ClassMatch<Value> bindValue() { return new ClassMatch(Value.class); }

	/* Matches a 'Neg' as 'sub 0, V'.
	 */
	public static <ValTy extends Pattern, RHS extends Pattern, OPCODE> BinaryOpMatch<ConstantPredicateType<IsZeroInt>, ValTy, Object> neg(ValTy V) {
		return sub(zeroInt(), V);
	}

	public static <LHS extends Pattern, RHS extends Pattern, OPCODE> BinaryOpMatch<LHS, RHS, OPCODE> lshr(LHS L, RHS R) {
		return new BinaryOpMatch(L, R, BinaryOperatorID.LSHR, false);
	}

	public static <LHS extends Pattern, RHS extends Pattern, OPCODE> BinaryOpMatch<LHS, RHS, OPCODE> udiv(LHS L, RHS R) {
		return new BinaryOpMatch(L, R, BinaryOperatorID.UDIV, false);
	}

	public static <LHS extends Pattern, RHS extends Pattern, OPCODE> BinaryOpMatch<LHS, RHS, OPCODE> shl(LHS L, RHS R) {
		return new BinaryOpMatch(L, R, BinaryOperatorID.SHL, false);
	}

	public static <LHS extends Pattern, RHS extends Pattern, OPCODE> BinaryOpMatch<LHS, RHS, OPCODE> urem(LHS L, RHS R) {
		return new BinaryOpMatch(L, R, BinaryOperatorID.UREM, false);
	}

	public static <LHS extends Pattern, RHS extends Pattern, PRED> MaxMinMatch<LHS, RHS, PRED> sMax(LHS L, RHS R) {
		SMaxPredType smaxPredType = new SMaxPredType();
		return new MaxMinMatch(L, R, smaxPredType, false);
	}

	public static <LHS extends Pattern, RHS extends Pattern, PRED> MaxMinMatch<LHS, RHS, PRED> sMin(LHS L, RHS R) {
		SMinPredType sminPredType = new SMinPredType();
		return new MaxMinMatch(L, R, sminPredType, false);
	}

	public static <LHS extends Pattern, RHS extends Pattern, PRED> MaxMinMatch<LHS, RHS, PRED> uMax(LHS L, RHS R) {
		UMaxPredType umaxPredType = new UMaxPredType();
		return new MaxMinMatch(L, R, umaxPredType, false);
	}

	public static <LHS extends Pattern, RHS extends Pattern, PRED> MaxMinMatch<LHS, RHS, PRED> uMin(LHS L, RHS R) {
		// public MaxMinMatch(CmpInstCls cmpInstCls, LHS L, RHS R, PRED pred, boolean isCommutative) {
		UMinPredType uminPredType = new UMinPredType();
		return new MaxMinMatch(L, R, uminPredType, false);
	}
}

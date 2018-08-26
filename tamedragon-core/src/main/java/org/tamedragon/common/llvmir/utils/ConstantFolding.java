package org.tamedragon.common.llvmir.utils;

import org.tamedragon.common.llvmir.instructions.Instruction;
import org.tamedragon.common.llvmir.instructions.BinaryOperator.BinaryOperatorID;
import org.tamedragon.common.llvmir.math.APInt;
import org.tamedragon.common.llvmir.types.Constant;
import org.tamedragon.common.llvmir.types.ConstantFP;
import org.tamedragon.common.llvmir.types.ConstantInt;
import org.tamedragon.common.llvmir.types.UndefValue;
import org.tamedragon.common.llvmir.types.VectorType;

public class ConstantFolding {

	public static Constant constantFoldBinaryInstruction(
			BinaryOperatorID operatorID, Constant C1,
			Constant C2) throws Exception{

		// No compile-time operations on this type yet.
		if (C1.getType().isPPC_FP128Type())
			return null;

		// Handle UndefValue up front.
		if (C1 instanceof UndefValue ||C1 instanceof UndefValue) {
			/*switch (operatorID) {
			case XOR:
				if (C1 instanceof UndefValue ||C1 instanceof UndefValue)
					// Handle undef ^ undef . 0 special case. This is a common
					// idiom (misuse).
					return Constant.getNullValue(C1.getType());

			// Fallthrough
			case ADD:
			case SUB:
				return UndefValue.createOrGet(C1.getType());

			case AND:
				if (isa<UndefValue>(C1) && isa<UndefValue>(C2)) // undef & undef . undef
					return C1;
			return Constant.getNullValue(C1.getType());   // undef & X . 0
			case Instruction.Mul: {
				ConstantInt *CI;
				// X * undef . undef   if X is odd or undef
				if (((CI = dyn_cast<ConstantInt>(C1)) && CI.getValue()[0]) ||
						((CI = dyn_cast<ConstantInt>(C2)) && CI.getValue()[0]) ||
						(isa<UndefValue>(C1) && isa<UndefValue>(C2)))
					return UndefValue.get(C1.getType());

				// X * undef . 0       otherwise
				return Constant.getNullValue(C1.getType());
			}
			case Instruction.UDiv:
			case Instruction.SDiv:
				// undef / 1 . undef
				if (Opcode == Instruction.UDiv || Opcode == Instruction.SDiv)
					if (ConstantInt *CI2 = dyn_cast<ConstantInt>(C2))
						if (CI2.isOne())
							return C1;
			// FALL THROUGH
			case Instruction.URem:
			case Instruction.SRem:
				if (!isa<UndefValue>(C2))                    // undef / X . 0
					return Constant.getNullValue(C1.getType());
			return C2;                                   // X / undef . undef
			case Instruction.Or:                          // X | undef . -1
				if (isa<UndefValue>(C1) && isa<UndefValue>(C2)) // undef | undef . undef
					return C1;
			return Constant.getAllOnesValue(C1.getType()); // undef | X . ~0
			case Instruction.LShr:
				if (isa<UndefValue>(C2) && isa<UndefValue>(C1))
					return C1;                                  // undef lshr undef . undef
			return Constant.getNullValue(C1.getType()); // X lshr undef . 0
			// undef lshr X . 0
			case Instruction.AShr:
				if (!isa<UndefValue>(C2))                     // undef ashr X -. all ones
					return Constant.getAllOnesValue(C1.getType());
				else if (isa<UndefValue>(C1)) 
					return C1;                                  // undef ashr undef . undef
				else
					return C1;                                  // X ashr undef -. X
			case Instruction.Shl:
				if (isa<UndefValue>(C2) && isa<UndefValue>(C1))
					return C1;                                  // undef shl undef . undef
			// undef << X . 0   or   X << undef . 0
			return Constant.getNullValue(C1.getType());
			} */
		}

		// Handle simplifications when the RHS is a constant int.

		if (C2 instanceof ConstantInt) {
			ConstantInt CI2 = (ConstantInt) C2;
			switch (operatorID) {
			case ADD:
				if (CI2.isZero()) 
					return C1;                         // X + 0 == X
				break;

			case SUB:
				if (CI2.isZero())
					return C1;                          // X - 0 == X
				break;

			case MUL:
				if (CI2.isZero()) 
					return C2;                         // X * 0 == 0
				if (CI2.equalsInt(1))
					return C1;                             // X * 1 == X
				break;

			case UDIV:
			case SDIV:
				if (CI2.equalsInt(1))
					return C1;                         // X / 1 == X

				if (CI2.equalsInt(0))
					return UndefValue.createOrGet(C1.getType());               // X / 0 == undef
				break;

			case UREM:
			case SREM:
				if (CI2.equalsInt(1))
					return Constant.getNullValue(CI2.getType());        // X % 1 == 0

				if (CI2.equalsInt(0))
					return UndefValue.createOrGet(CI2.getType());       // X % 0 == undef
				break;

			case AND:
				if (CI2.isZero())
					return C2;                                        // X & 0 == 0

				if (CI2.isAllOnesValue())
					return C1;                                            // X & -1 == X

				/*if (ConstantExpr *CE1 = dyn_cast<ConstantExpr>(C1)) {
				// (zext i32 to i64) & 4294967295 . (zext i32 to i64)
				if (CE1.getOpcode() == Instruction.ZExt) {
					unsigned DstWidth = CI2.getType().getBitWidth();
					unsigned SrcWidth =
						CE1.getOperand(0).getType().getPrimitiveSizeInBits();
						APInt PossiblySetBits(APInt.getLowBitsSet(DstWidth, SrcWidth));
						if ((PossiblySetBits & CI2.getValue()) == PossiblySetBits)
							return C1;
				}

				// If and'ing the address of a global with a constant, fold it.
				if (CE1.getOpcode() == Instruction.PtrToInt && 
						isa<GlobalValue>(CE1.getOperand(0))) {
					GlobalValue *GV = cast<GlobalValue>(CE1.getOperand(0));

					// Functions are at least 4-byte aligned.
					unsigned GVAlign = GV.getAlignment();
					if (isa<Function>(GV))
						GVAlign = std.max(GVAlign, 4U);

					if (GVAlign > 1) {
						unsigned DstWidth = CI2.getType().getBitWidth();
						unsigned SrcWidth = std.min(DstWidth, Log2_32(GVAlign));
						APInt BitsNotSet(APInt.getLowBitsSet(DstWidth, SrcWidth));

						// If checking bits we know are clear, return zero.
						if ((CI2.getValue() & BitsNotSet) == CI2.getValue())
							return Constant.getNullValue(CI2.getType());
					}
				}
			}*/
				break;

			case OR:
				if (CI2.equalsInt(0)) 
					return C1;                     // X | 0 == X

				if (CI2.isAllOnesValue())
					return C2;                         // X | -1 == -1
				break;

			case XOR:
				if (CI2.equalsInt(0)) 
					return C1;                     // X ^ 0 == X

				/*if (ConstantExpr *CE1 = dyn_cast<ConstantExpr>(C1)) {
				switch (CE1.getOpcode()) {
				default: break;
				case Instruction.ICmp:
				case Instruction.FCmp:
					// cmp pred ^ true . cmp !pred
					assert(CI2.equalsInt(1));
				CmpInst.Predicate pred = (CmpInst.Predicate)CE1.getPredicate();
				pred = CmpInst.getInversePredicate(pred);
				return ConstantExpr.getCompare(pred, CE1.getOperand(0),
						CE1.getOperand(1));
				}
			}*/
				break;

			case ASHR:
				// ashr (zext C to Ty), C2 . lshr (zext C, CSA), C2
				/*if (ConstantExpr *CE1 = dyn_cast<ConstantExpr>(C1))
					if (CE1.getOpcode() == Instruction.ZExt)  // Top bits known zero.
						return ConstantExpr.getLShr(C1, C2);
				 */
				break;
			}
		} 
		else {
			if (C1 instanceof ConstantInt) {
				// If C1 is a ConstantInt and C2 is not, swap the operands.
				if (Instruction.isCommutative(operatorID))
					return Constant.getConstant(operatorID, C2, C1);
			}
		}

		// At this point we know neither constant is an UndefValue.
		if (C1 instanceof ConstantInt) {
			ConstantInt CI1 = (ConstantInt) C1;
			if (C2 instanceof ConstantInt) {
				ConstantInt CI2 = (ConstantInt) C2;
				APInt C1V = CI1.getApInt();
				APInt C2V = CI2.getApInt();
				switch (operatorID) {
				default:
					break;
					
				case ADD:     
					return CI1.add(CI2);
					
				case SUB:
					return CI1.subtract(CI2);
				
				case MUL:     
					return CI1.multiply(CI2);
				case UDIV:
					return CI1.udiv(CI2);
				case SDIV:
					//if (C2V.isAllOnesValue() && C1V.isMinSignedValue())
					//	return UndefValue.get(CI1.getType());   // MIN_INT / -1 . undef
					return CI1.sdiv(CI2);
				
				case UREM:
					return CI1.urem(CI2);
					
				case SREM:
				//if (C2V.isAllOnesValue() && C1V.isMinSignedValue())
				//	return UndefValue.get(CI1.getType());   // MIN_INT % -1 . undef
					return CI1.srem(CI2);
				
				case AND:
					return CI1.and(CI2);
					
				case OR:
					return CI1.or(CI2);
					
				case XOR:
					return CI1.xor(CI2);
					
				case SHL: 
					int shiftAmt = C2V.getZExtValue();
					if (shiftAmt < C1V.getNumBits())
						return CI1.shl(CI2);
					else
						// too big shift is undef
						return UndefValue.createOrGet(C1.getType());  
					
				case LSHR :
					shiftAmt = C2V.getZExtValue();
					if (shiftAmt < C1V.getNumBits())
						return CI1.lshr(CI2);
					else
						// too big shift is undef
						return UndefValue.createOrGet(C1.getType());   
					
				case ASHR : 
					shiftAmt = C2V.getZExtValue();
					if (shiftAmt < C1V.getNumBits())
						return CI1.ashr(CI2);
					else
						// too big shift is undef
						return UndefValue.createOrGet(C1.getType());
				}
			}

			// C1 is a ConstantInt, but C2 is not
			switch (operatorID) {
			case SDIV:
			case UDIV:
			case UREM:
			case SREM:
			case LSHR:
			case ASHR:
			case SHL:
				if (CI1.equalsInt(0)) return C1;
			break;
			default:
				break;
			}
			
		} 
		else if (C1 instanceof ConstantFP ) {
			ConstantFP CFP1 = (ConstantFP) C1;
			if (C2 instanceof ConstantFP ) {
				ConstantFP CFP2 = (ConstantFP) C2;
				switch (operatorID) {
					default:                   
						break;
					
					case FADD :
						return CFP1.add(CFP2);
					
					case FSUB:
						return CFP1.subtract(CFP2);
				
					case FMUL:
						return CFP1.mul(CFP2);
				
					case FDIV:
						return CFP1.divide(CFP2);
				
					case FREM:
						return CFP1.frem(CFP2);
				}
			}
		} 
		else if (C1.getType() instanceof VectorType ) {
			// Perform elementwise folding.
			// TODO Implement this
			/*SmallVector<Constant*, 16> Result;
			for (unsigned i = 0, e = VTy.getNumElements(); i != e; ++i) {
				Constant *LHS = C1.getAggregateElement(i);
				Constant *RHS = C2.getAggregateElement(i);
				if (LHS == 0 || RHS == 0) break;

				Result.push_back(ConstantExpr.get(Opcode, LHS, RHS));
			}

			if (Result.size() == VTy.getNumElements())
				return ConstantVector.get(Result);
				*/
			return C1;
		}

		// TODO: Look for constant expressions?
		/*if (ConstantExpr *CE1 = dyn_cast<ConstantExpr>(C1)) {
			// There are many possible foldings we could do here.  We should probably
			// at least fold add of a pointer with an integer into the appropriate
			// getelementptr.  This will improve alias analysis a bit.

			// Given ((a + b) + c), if (b + c) folds to something interesting, return
			// (a + (b + c)).
			if (Instruction.isAssociative(Opcode) && CE1.getOpcode() == Opcode) {
				Constant *T = ConstantExpr.get(Opcode, CE1.getOperand(1), C2);
				if (!isa<ConstantExpr>(T) || cast<ConstantExpr>(T).getOpcode() != Opcode)
					return ConstantExpr.get(Opcode, CE1.getOperand(0), T);
			}
		} 
		else if (isa<ConstantExpr>(C2)) {
			// If C2 is a constant expr and C1 isn't, flop them around and fold the
			// other way if possible.
			if (Instruction.isCommutative(Opcode))
				return ConstantFoldBinaryInstruction(Opcode, C2, C1);
		}
		*/

		// i1 can be simplified in many cases.
		if (C1.getType().isInt1Type()) {
			ConstantInt CI1 = (ConstantInt) C1;
			switch(operatorID) {
			case ADD:
			case SUB:
				return CI1.xor((ConstantInt)C2);
				
			case MUL:
				return CI1.and((ConstantInt)C2);
				
			case SHL:
			case LSHR:
			case ASHR:
				// We can assume that C2 == 0.  If it were one the result would be
				// undefined because the shift value is as large as the bitwidth.
				return C1;
				
			case SDIV:
			case UDIV:
				// We can assume that C2 == 1.  If it were zero the result would be
				// undefined through division by zero.
				return C1;
				
			case UREM:
			case SREM:
				// We can assume that C2 == 1.  If it were zero the result would be
				// undefined through division by zero.
				return ConstantInt.getTrueOrFalse(C1.getContext(), false);
				
			default:
				break;
			}
		}

		// We don't know how to fold this.
		return null;

	}

	/*public static Constant constantFoldCompareInstruction(CmpInst.Predicate pred, 
			Constant C1, Constant C2) {

	Type resultType;

		Type oprType = C1.getType(); 
		TypeID operatorTypeId = oprType.getTypeId();

		if (operatorTypeId == TypeID.VECTOR_ID){
			//resultType = VectorType.get(Type.getInt1Type(C1.getContext()),
			//		VT.getNumElements());
		}
		else
			resultType = Type.getInt1Type(C1.getContext(), false);

		// Fold FCMP_FALSE/FCMP_TRUE unconditionally.
		if (pred == FCmpInst.FCMP_FALSE)
			return Constant.getNullValue(ResultTy);

		if (pred == FCmpInst.FCMP_TRUE)
			return Constant.getAllOnesValue(ResultTy);

		// Handle some degenerate cases first
		if (isa<UndefValue>(C1) || isa<UndefValue>(C2)) {
			// For EQ and NE, we can always pick a value for the undef to make the
			// predicate pass or fail, so we can return undef.
			// Also, if both operands are undef, we can return undef.
			if (ICmpInst.isEquality(ICmpInst.Predicate(pred)) ||
					(isa<UndefValue>(C1) && isa<UndefValue>(C2)))
				return UndefValue.get(ResultTy);
			// Otherwise, pick the same value as the non-undef operand, and fold
			// it to true or false.
			return ConstantInt.get(ResultTy, CmpInst.isTrueWhenEqual(pred));
		}

		// No compile-time operations on this type yet.
		if (C1.getType().isPPC_FP128Type())
			return 0;

		// icmp eq/ne(null,GV) . false/true
		if (C1.isNullValue()) {
			if (const GlobalValue *GV = dyn_cast<GlobalValue>(C2))
				// Don't try to evaluate aliases.  External weak GV can be null.
				if (!isa<GlobalAlias>(GV) && !GV.hasExternalWeakLinkage()) {
					if (pred == ICmpInst.ICMP_EQ)
						return ConstantInt.getFalse(C1.getContext());
					else if (pred == ICmpInst.ICMP_NE)
						return ConstantInt.getTrue(C1.getContext());
				}
			// icmp eq/ne(GV,null) . false/true
		} else if (C2.isNullValue()) {
			if (const GlobalValue *GV = dyn_cast<GlobalValue>(C1))
				// Don't try to evaluate aliases.  External weak GV can be null.
				if (!isa<GlobalAlias>(GV) && !GV.hasExternalWeakLinkage()) {
					if (pred == ICmpInst.ICMP_EQ)
						return ConstantInt.getFalse(C1.getContext());
					else if (pred == ICmpInst.ICMP_NE)
						return ConstantInt.getTrue(C1.getContext());
				}
		}

		// If the comparison is a comparison between two i1's, simplify it.
		if (C1.getType().isIntegerTy(1)) {
			switch(pred) {
			case ICmpInst.ICMP_EQ:
				if (isa<ConstantInt>(C2))
					return ConstantExpr.getXor(C1, ConstantExpr.getNot(C2));
			return ConstantExpr.getXor(ConstantExpr.getNot(C1), C2);
			case ICmpInst.ICMP_NE:
				return ConstantExpr.getXor(C1, C2);
			default:
				break;
			}
		}

		if (isa<ConstantInt>(C1) && isa<ConstantInt>(C2)) {
			APInt V1 = cast<ConstantInt>(C1).getValue();
			APInt V2 = cast<ConstantInt>(C2).getValue();
			switch (pred) {
			default: llvm_unreachable("Invalid ICmp Predicate");
			case ICmpInst.ICMP_EQ:  return ConstantInt.get(ResultTy, V1 == V2);
			case ICmpInst.ICMP_NE:  return ConstantInt.get(ResultTy, V1 != V2);
			case ICmpInst.ICMP_SLT: return ConstantInt.get(ResultTy, V1.slt(V2));
			case ICmpInst.ICMP_SGT: return ConstantInt.get(ResultTy, V1.sgt(V2));
			case ICmpInst.ICMP_SLE: return ConstantInt.get(ResultTy, V1.sle(V2));
			case ICmpInst.ICMP_SGE: return ConstantInt.get(ResultTy, V1.sge(V2));
			case ICmpInst.ICMP_ULT: return ConstantInt.get(ResultTy, V1.ult(V2));
			case ICmpInst.ICMP_UGT: return ConstantInt.get(ResultTy, V1.ugt(V2));
			case ICmpInst.ICMP_ULE: return ConstantInt.get(ResultTy, V1.ule(V2));
			case ICmpInst.ICMP_UGE: return ConstantInt.get(ResultTy, V1.uge(V2));
			}
		} else if (isa<ConstantFP>(C1) && isa<ConstantFP>(C2)) {
			APFloat C1V = cast<ConstantFP>(C1).getValueAPF();
			APFloat C2V = cast<ConstantFP>(C2).getValueAPF();
			APFloat.cmpResult R = C1V.compare(C2V);
			switch (pred) {
			default: llvm_unreachable("Invalid FCmp Predicate");
			case FCmpInst.FCMP_FALSE: return Constant.getNullValue(ResultTy);
			case FCmpInst.FCMP_TRUE:  return Constant.getAllOnesValue(ResultTy);
			case FCmpInst.FCMP_UNO:
				return ConstantInt.get(ResultTy, R==APFloat.cmpUnordered);
			case FCmpInst.FCMP_ORD:
				return ConstantInt.get(ResultTy, R!=APFloat.cmpUnordered);
			case FCmpInst.FCMP_UEQ:
				return ConstantInt.get(ResultTy, R==APFloat.cmpUnordered ||
						R==APFloat.cmpEqual);
			case FCmpInst.FCMP_OEQ:   
				return ConstantInt.get(ResultTy, R==APFloat.cmpEqual);
			case FCmpInst.FCMP_UNE:
				return ConstantInt.get(ResultTy, R!=APFloat.cmpEqual);
			case FCmpInst.FCMP_ONE:   
				return ConstantInt.get(ResultTy, R==APFloat.cmpLessThan ||
						R==APFloat.cmpGreaterThan);
			case FCmpInst.FCMP_ULT: 
				return ConstantInt.get(ResultTy, R==APFloat.cmpUnordered ||
						R==APFloat.cmpLessThan);
			case FCmpInst.FCMP_OLT:   
				return ConstantInt.get(ResultTy, R==APFloat.cmpLessThan);
			case FCmpInst.FCMP_UGT:
				return ConstantInt.get(ResultTy, R==APFloat.cmpUnordered ||
						R==APFloat.cmpGreaterThan);
			case FCmpInst.FCMP_OGT:
				return ConstantInt.get(ResultTy, R==APFloat.cmpGreaterThan);
			case FCmpInst.FCMP_ULE:
				return ConstantInt.get(ResultTy, R!=APFloat.cmpGreaterThan);
			case FCmpInst.FCMP_OLE: 
				return ConstantInt.get(ResultTy, R==APFloat.cmpLessThan ||
						R==APFloat.cmpEqual);
			case FCmpInst.FCMP_UGE:
				return ConstantInt.get(ResultTy, R!=APFloat.cmpLessThan);
			case FCmpInst.FCMP_OGE: 
				return ConstantInt.get(ResultTy, R==APFloat.cmpGreaterThan ||
						R==APFloat.cmpEqual);
			}
		} else if (C1.getType().isVectorTy()) {
			// If we can constant fold the comparison of each element, constant fold
			// the whole vector comparison.
			SmallVector<Constant*, 4> ResElts;
			// Compare the elements, producing an i1 result or constant expr.
			for (unsigned i = 0, e = C1.getType().getVectorNumElements(); i != e;++i){
				Constant *C1E = C1.getAggregateElement(i);
				Constant *C2E = C2.getAggregateElement(i);
				if (C1E == 0 || C2E == 0) break;

				ResElts.push_back(ConstantExpr.getCompare(pred, C1E, C2E));
			}

			if (ResElts.size() == C1.getType().getVectorNumElements())
				return ConstantVector.get(ResElts);
		}

		if (C1.getType().isFloatingPointTy()) {
			int Result = -1;  // -1 = unknown, 0 = known false, 1 = known true.
			switch (evaluateFCmpRelation(C1, C2)) {
			default: llvm_unreachable("Unknown relation!");
			case FCmpInst.FCMP_UNO:
			case FCmpInst.FCMP_ORD:
			case FCmpInst.FCMP_UEQ:
			case FCmpInst.FCMP_UNE:
			case FCmpInst.FCMP_ULT:
			case FCmpInst.FCMP_UGT:
			case FCmpInst.FCMP_ULE:
			case FCmpInst.FCMP_UGE:
			case FCmpInst.FCMP_TRUE:
			case FCmpInst.FCMP_FALSE:
			case FCmpInst.BAD_FCMP_PREDICATE:
				break; // Couldn't determine anything about these constants.
			case FCmpInst.FCMP_OEQ: // We know that C1 == C2
				Result = (pred == FCmpInst.FCMP_UEQ || pred == FCmpInst.FCMP_OEQ ||
						pred == FCmpInst.FCMP_ULE || pred == FCmpInst.FCMP_OLE ||
						pred == FCmpInst.FCMP_UGE || pred == FCmpInst.FCMP_OGE);
			break;
			case FCmpInst.FCMP_OLT: // We know that C1 < C2
				Result = (pred == FCmpInst.FCMP_UNE || pred == FCmpInst.FCMP_ONE ||
						pred == FCmpInst.FCMP_ULT || pred == FCmpInst.FCMP_OLT ||
						pred == FCmpInst.FCMP_ULE || pred == FCmpInst.FCMP_OLE);
			break;
			case FCmpInst.FCMP_OGT: // We know that C1 > C2
				Result = (pred == FCmpInst.FCMP_UNE || pred == FCmpInst.FCMP_ONE ||
						pred == FCmpInst.FCMP_UGT || pred == FCmpInst.FCMP_OGT ||
						pred == FCmpInst.FCMP_UGE || pred == FCmpInst.FCMP_OGE);
			break;
			case FCmpInst.FCMP_OLE: // We know that C1 <= C2
				// We can only partially decide this relation.
				if (pred == FCmpInst.FCMP_UGT || pred == FCmpInst.FCMP_OGT) 
					Result = 0;
				else if (pred == FCmpInst.FCMP_ULT || pred == FCmpInst.FCMP_OLT) 
					Result = 1;
			break;
			case FCmpInst.FCMP_OGE: // We known that C1 >= C2
				// We can only partially decide this relation.
				if (pred == FCmpInst.FCMP_ULT || pred == FCmpInst.FCMP_OLT) 
					Result = 0;
				else if (pred == FCmpInst.FCMP_UGT || pred == FCmpInst.FCMP_OGT) 
					Result = 1;
			break;
			case FCmpInst.FCMP_ONE: // We know that C1 != C2
				// We can only partially decide this relation.
				if (pred == FCmpInst.FCMP_OEQ || pred == FCmpInst.FCMP_UEQ) 
					Result = 0;
				else if (pred == FCmpInst.FCMP_ONE || pred == FCmpInst.FCMP_UNE) 
					Result = 1;
			break;
			}

			// If we evaluated the result, return it now.
			if (Result != -1)
				return ConstantInt.get(ResultTy, Result);

		} else {
			// Evaluate the relation between the two constants, per the predicate.
			int Result = -1;  // -1 = unknown, 0 = known false, 1 = known true.
			switch (evaluateICmpRelation(C1, C2, CmpInst.isSigned(pred))) {
			default: llvm_unreachable("Unknown relational!");
			case ICmpInst.BAD_ICMP_PREDICATE:
				break;  // Couldn't determine anything about these constants.
			case ICmpInst.ICMP_EQ:   // We know the constants are equal!
				// If we know the constants are equal, we can decide the result of this
				// computation precisely.
				Result = ICmpInst.isTrueWhenEqual((ICmpInst.Predicate)pred);
			break;
			case ICmpInst.ICMP_ULT:
				switch (pred) {
				case ICmpInst.ICMP_ULT: case ICmpInst.ICMP_NE: case ICmpInst.ICMP_ULE:
					Result = 1; break;
				case ICmpInst.ICMP_UGT: case ICmpInst.ICMP_EQ: case ICmpInst.ICMP_UGE:
					Result = 0; break;
				}
			break;
			case ICmpInst.ICMP_SLT:
				switch (pred) {
				case ICmpInst.ICMP_SLT: case ICmpInst.ICMP_NE: case ICmpInst.ICMP_SLE:
					Result = 1; break;
				case ICmpInst.ICMP_SGT: case ICmpInst.ICMP_EQ: case ICmpInst.ICMP_SGE:
					Result = 0; break;
				}
			break;
			case ICmpInst.ICMP_UGT:
				switch (pred) {
				case ICmpInst.ICMP_UGT: case ICmpInst.ICMP_NE: case ICmpInst.ICMP_UGE:
					Result = 1; break;
				case ICmpInst.ICMP_ULT: case ICmpInst.ICMP_EQ: case ICmpInst.ICMP_ULE:
					Result = 0; break;
				}
			break;
			case ICmpInst.ICMP_SGT:
				switch (pred) {
				case ICmpInst.ICMP_SGT: case ICmpInst.ICMP_NE: case ICmpInst.ICMP_SGE:
					Result = 1; break;
				case ICmpInst.ICMP_SLT: case ICmpInst.ICMP_EQ: case ICmpInst.ICMP_SLE:
					Result = 0; break;
				}
			break;
			case ICmpInst.ICMP_ULE:
				if (pred == ICmpInst.ICMP_UGT) Result = 0;
			if (pred == ICmpInst.ICMP_ULT || pred == ICmpInst.ICMP_ULE) Result = 1;
			break;
			case ICmpInst.ICMP_SLE:
				if (pred == ICmpInst.ICMP_SGT) Result = 0;
			if (pred == ICmpInst.ICMP_SLT || pred == ICmpInst.ICMP_SLE) Result = 1;
			break;
			case ICmpInst.ICMP_UGE:
				if (pred == ICmpInst.ICMP_ULT) Result = 0;
			if (pred == ICmpInst.ICMP_UGT || pred == ICmpInst.ICMP_UGE) Result = 1;
			break;
			case ICmpInst.ICMP_SGE:
				if (pred == ICmpInst.ICMP_SLT) Result = 0;
			if (pred == ICmpInst.ICMP_SGT || pred == ICmpInst.ICMP_SGE) Result = 1;
			break;
			case ICmpInst.ICMP_NE:
				if (pred == ICmpInst.ICMP_EQ) Result = 0;
			if (pred == ICmpInst.ICMP_NE) Result = 1;
			break;
			}

			// If we evaluated the result, return it now.
			if (Result != -1)
				return ConstantInt.get(ResultTy, Result);

			// If the right hand side is a bitcast, try using its inverse to simplify
			// it by moving it to the left hand side.  We can't do this if it would turn
			// a vector compare into a scalar compare or visa versa.
			if (ConstantExpr *CE2 = dyn_cast<ConstantExpr>(C2)) {
				Constant *CE2Op0 = CE2.getOperand(0);
				if (CE2.getOpcode() == Instruction.BitCast &&
						CE2.getType().isVectorTy() == CE2Op0.getType().isVectorTy()) {
					Constant *Inverse = ConstantExpr.getBitCast(C1, CE2Op0.getType());
					return ConstantExpr.getICmp(pred, Inverse, CE2Op0);
				}
			}

			// If the left hand side is an extension, try eliminating it.
			if (ConstantExpr *CE1 = dyn_cast<ConstantExpr>(C1)) {
				if ((CE1.getOpcode() == Instruction.SExt && ICmpInst.isSigned(pred)) ||
						(CE1.getOpcode() == Instruction.ZExt && !ICmpInst.isSigned(pred))){
					Constant *CE1Op0 = CE1.getOperand(0);
					Constant *CE1Inverse = ConstantExpr.getTrunc(CE1, CE1Op0.getType());
					if (CE1Inverse == CE1Op0) {
						// Check whether we can safely truncate the right hand side.
						Constant *C2Inverse = ConstantExpr.getTrunc(C2, CE1Op0.getType());
						if (ConstantExpr.getZExt(C2Inverse, C2.getType()) == C2) {
							return ConstantExpr.getICmp(pred, CE1Inverse, C2Inverse);
						}
					}
				}
			}

			if ((!isa<ConstantExpr>(C1) && isa<ConstantExpr>(C2)) ||
					(C1.isNullValue() && !C2.isNullValue())) {
				// If C2 is a constant expr and C1 isn't, flip them around and fold the
				// other way if possible.
				// Also, if C1 is null and C2 isn't, flip them around.
				pred = ICmpInst.getSwappedPredicate((ICmpInst.Predicate)pred);
				return ConstantExpr.getICmp(pred, C2, C1);
			}
		}
		return 0;
	}
	 */
}

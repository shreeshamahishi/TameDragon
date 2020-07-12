package org.tamedragon.common.common.analysis;

import org.tamedragon.common.llvmir.instructions.BinaryOperator;
import org.tamedragon.common.llvmir.instructions.BinaryOperator.BinaryOperatorID;
import org.tamedragon.common.llvmir.instructions.CmpInst;
import org.tamedragon.common.llvmir.instructions.Instruction.InstructionID;
import org.tamedragon.common.llvmir.irdata.DataLayout;
import org.tamedragon.common.llvmir.math.APInt;
import org.tamedragon.common.llvmir.math.APIntUtils;
import org.tamedragon.common.llvmir.math.ULong;
import org.tamedragon.common.llvmir.types.Constant;
import org.tamedragon.common.llvmir.types.ConstantExpr;
import org.tamedragon.common.llvmir.types.ConstantInt;
import org.tamedragon.common.llvmir.types.GlobalValue;
import org.tamedragon.common.llvmir.types.UndefValue;

public class ConstantFolding {

	public static Constant constantFoldBinaryOpOperands(BinaryOperatorID binOpId, Constant lhs, Constant rhs,
			DataLayout dataLayout) throws InstantiationException {

		if (lhs instanceof ConstantExpr || rhs instanceof ConstantExpr ) {
			Constant cst = symbolicallyEvaluateBinop(binOpId, lhs, rhs, dataLayout);
			if(cst != null){
				return cst;
			}
		}

		return ConstantExpr.get(binOpId, lhs, rhs);
	}

	private static Constant symbolicallyEvaluateBinop(BinaryOperatorID binOpId, Constant lhs, Constant rhs,
			DataLayout dataLayout) {
		// TODO Implement this
		return null;
	}

	public static Constant constantFoldBinaryInstruction(BinaryOperatorID binOpCode, Constant C1, Constant C2)
			throws InstantiationException {

		// Simplify BinOps with their identity values first. They are no-ops and we
		// can always return the other value, including undef or poison values.
		// FIXME: remove unnecessary duplicated identity patterns below.
		// FIXME: Use AllowRHSConstant with getBinOpIdentity to handle additional ops,
		//        like X << 0 = X.
		Constant Identity = ConstantExpr.getBinOpIdentity(binOpCode, C1.getType());
		if (Identity != null) {
			if (C1 == Identity)
				return C2;
			if (C2 == Identity)
				return C1;
		}

		// Handle scalar UndefValue. Vectors are always evaluated per element.
		boolean hasScalarUndef = !C1.getType().isVectorType() && (C1 instanceof UndefValue || C2 instanceof UndefValue);

		Constant result = constantFoldBinOpsWithUndefOperands(binOpCode, C1, C2);
		if(result != null) {
			return result;
		}

		// Neither constant should be UndefValue, unless these are vector constants.
		if(hasScalarUndef) {
			throw new IllegalArgumentException("Unexpected UndefValue");
		};

		// Handle simplifications when the RHS is a constant int.
		if(C2 instanceof ConstantInt) {
			ConstantInt CI2 = (ConstantInt)(C2);
			switch (binOpCode) {
			case ADD:
				if (CI2.isZero()) return C1;                             // X + 0 == X
				break;
			case SUB:
				if (CI2.isZero()) return C1;                             // X - 0 == X
				break;
			case MUL:
				if (CI2.isZero()) return C2;                             // X * 0 == 0
			if (CI2.isOne())
				return C1;                                              // X * 1 == X
			break;
			case UDIV:
			case SDIV:
				if (CI2.isOne())
					return C1;                                            // X / 1 == X
				if (CI2.isZero())
					return UndefValue.createOrGet(CI2.getType());               // X / 0 == undef
				break;
			case UREM:
			case SREM:
				if (CI2.isOne()) {
					return Constant.getNullValue(CI2.getType());        // X % 1 == 0
				}
				if (CI2.isZero())
					return UndefValue.createOrGet(CI2.getType());               // X % 0 == undef
				break;
			case AND:
				if (CI2.isZero()) return C2;                           // X & 0 == 0
				if (CI2.isNegativeUnity())
					return C1;                                            // X & -1 == X

				if(C1 instanceof ConstantExpr) {
					ConstantExpr CE1 = (ConstantExpr)C1;
					// (zext i32 to i64) & 4294967295 -> (zext i32 to i64)
					if (CE1.getOpCode() == InstructionID.ZEXT_INST) {
						// TODO Implement getBitWidth() on Type
						//int DstWidth = CI2.getType().getBitWidth();
						int dstWidth = 24;
						int srcWidth = CE1.getOperand(0).getType().getPrimitiveSizeInBits();
						APInt possiblySetBits = APIntUtils.getLowBitsSet(dstWidth, srcWidth);
						// TODO Fix this
						//if ((possiblySetBits  & CI2.getApInt().getVal()) == PossiblySetBits) {
						//	return C1;
						//}
					}

					// If and'ing the address of a global with a constant, fold it.
					if (CE1.getOpCode() == InstructionID.PTR_TO_INT_INST && CE1.getOperand(0) instanceof GlobalValue) {
						GlobalValue GV = (GlobalValue) CE1.getOperand(0);
						Constant cst = constantFoldPtrToIntWithGlobaValue(GV);
						if(cst != null) {
							return cst;
						}
					}
				}
				break;
			case OR:
				if (CI2.isZero()) return C1;        // X | 0 == X
				if (CI2.isNegativeUnity())
					return C2;                         // X | -1 == -1
				break;
			case XOR:
				if (CI2.isZero()) return C1;        // X ^ 0 == X

				if (C1 instanceof ConstantExpr) {
					ConstantExpr CE1 = (ConstantExpr) C1;
					switch (CE1.getOpCode()) {
					default: break;
					case ICMP_INST:
					case FCMP_INST:
						// cmp pred ^ true -> cmp !pred
						if(!CI2.isOne()) {
							throw new IllegalArgumentException("Comparison can only have a value of one"); 
						}
						CmpInst.Predicate pred = (CmpInst.Predicate)CE1.getPredicate();
						pred = CmpInst.getInversePredicate(pred);
						return ConstantExpr.getCompare(pred, (Constant)CE1.getOperand(0), (Constant)CE1.getOperand(1));
					}
				}
				break;
			case ASHR:
				// ashr (zext C to Ty), C2 -> lshr (zext C, CSA), C2
				if(C1 instanceof ConstantExpr) {
					ConstantExpr CE1 = (ConstantExpr)(C1);
					if (CE1.getOpCode() == InstructionID.ZEXT_INST) { // Top bits known zero.
						return ConstantExpr.getLShr(C1, C2, false);
					}
				}
				break;
			}
		} else if (C1 instanceof ConstantInt) {
			// If C1 is a ConstantInt and C2 is not, swap the operands.
			if (BinaryOperator.isCommutative(binOpCode))
				return ConstantExpr.get(binOpCode, C2, C1);
		}

		if(C1 instanceof ConstantInt) {
			ConstantInt CI1 = (ConstantInt) C1;
			if(C2 instanceof ConstantInt) {
				ConstantInt CI2 = (ConstantInt) C2;

				APInt C1V = CI1.getApInt();
				APInt C2V = CI2.getApInt();
				switch (binOpCode) {
				default:
					break;
				case ADD:
					return ConstantInt.create(CI1.getContext(), C1V.add(C2V));
				case SUB:
					return ConstantInt.create(CI1.getContext(), C1V.subtract(C2V));
				case MUL:
					return ConstantInt.create(CI1.getContext(), C1V.mul(C2V));
				case UDIV:
					if(CI2.isZero()) {
						throw new IllegalArgumentException("Div by zero should have been handled above");
					}
					return ConstantInt.create(CI1.getContext(), C1V.udiv(C2V));
				case SDIV:
					if(CI2.isZero()) {
						throw new IllegalArgumentException("Div by zero should have been handled above");
					}
					if (C2V.isAllOnesValue() && C1V.isMinSignedValue()) {
						return UndefValue.createOrGet(CI1.getType());   // MIN_INT / -1 -> undef
					}
					return ConstantInt.create(CI1.getContext(), C1V.sdiv(C2V));
				case UREM:
					if(CI2.isZero()) {
						throw new IllegalArgumentException("Div by zero should have been handled above");
					}
					return ConstantInt.create(CI1.getContext(), C1V.urem(C2V));
				case SREM:
					if(CI2.isZero()) {
						throw new IllegalArgumentException("Div by zero should have been handled above");
					}
					if (C2V.isAllOnesValue() && C1V.isMinSignedValue()) {
						return UndefValue.createOrGet(CI1.getType());   // MIN_INT % -1 -> undef
					}
					return ConstantInt.create(CI1.getContext(), C1V.srem(C2V));
				case AND:
					return ConstantInt.create(CI1.getContext(), C1V.andWith(C2V));
				case OR:
					return ConstantInt.create(CI1.getContext(), C1V.or(C2V));
				case XOR:
					return ConstantInt.create(CI1.getContext(), C1V.xorWith(C2V));
				case SHL:
					if (C2V.ult(ULong.valueOf(C1V.getNumBits()))) {
						return ConstantInt.create(CI1.getContext(), C1V.shl(C2V));
					}
					return UndefValue.createOrGet(C1.getType()); // too big shift is undef
				case LSHR:
					if (C2V.ult(ULong.valueOf(C1V.getNumBits()))) {
						return ConstantInt.create(CI1.getContext(), C1V.lshr(C2V));
					}
					return UndefValue.createOrGet(C1.getType()); // too big shift is undef
				case ASHR:
					if (C2V.ult(ULong.valueOf(C1V.getNumBits()))) {
						return ConstantInt.create(CI1.getContext(), C1V.ashr(C2V));
					}
					return UndefValue.createOrGet(C1.getType()); // too big shift is undef
				}
			}
		}

			// TODO Implement the rest of it
			
			/*switch (Opcode) {
			case Instruction::SDiv:
			case Instruction::UDiv:
			case Instruction::URem:
			case Instruction::SRem:
			case Instruction::LShr:
			case Instruction::AShr:
			case Instruction::Shl:
				if (CI1->isZero()) return C1;
			break;
			default:
				break;
			}
		} else if (ConstantFP *CFP1 = dyn_cast<ConstantFP>(C1)) {
			if (ConstantFP *CFP2 = dyn_cast<ConstantFP>(C2)) {
				const APFloat &C1V = CFP1->getValueAPF();
				const APFloat &C2V = CFP2->getValueAPF();
				APFloat C3V = C1V;  // copy for modification
				switch (Opcode) {
				default:
					break;
				case Instruction::FAdd:
					(void)C3V.add(C2V, APFloat::rmNearestTiesToEven);
				return ConstantFP::get(C1->getContext(), C3V);
				case Instruction::FSub:
					(void)C3V.subtract(C2V, APFloat::rmNearestTiesToEven);
				return ConstantFP::get(C1->getContext(), C3V);
				case Instruction::FMul:
					(void)C3V.multiply(C2V, APFloat::rmNearestTiesToEven);
				return ConstantFP::get(C1->getContext(), C3V);
				case Instruction::FDiv:
					(void)C3V.divide(C2V, APFloat::rmNearestTiesToEven);
				return ConstantFP::get(C1->getContext(), C3V);
				case Instruction::FRem:
					(void)C3V.mod(C2V);
				return ConstantFP::get(C1->getContext(), C3V);
				}
			}
		} else if (VectorType *VTy = dyn_cast<VectorType>(C1->getType())) {
			// Fold each element and create a vector constant from those constants.
			SmallVector<Constant*, 16> Result;
			Type *Ty = IntegerType::get(VTy->getContext(), 32);
			for (unsigned i = 0, e = VTy->getNumElements(); i != e; ++i) {
				Constant *ExtractIdx = ConstantInt::get(Ty, i);
				Constant *LHS = ConstantExpr::getExtractElement(C1, ExtractIdx);
				Constant *RHS = ConstantExpr::getExtractElement(C2, ExtractIdx);

				// If any element of a divisor vector is zero, the whole op is undef.
				if (Instruction::isIntDivRem(Opcode) && RHS->isNullValue())
					return UndefValue::get(VTy);

				Result.push_back(ConstantExpr::get(Opcode, LHS, RHS));
			}

			return ConstantVector::get(Result);
		}

		if (ConstantExpr *CE1 = dyn_cast<ConstantExpr>(C1)) {
			// There are many possible foldings we could do here.  We should probably
			// at least fold add of a pointer with an integer into the appropriate
			// getelementptr.  This will improve alias analysis a bit.

			// Given ((a + b) + c), if (b + c) folds to something interesting, return
			// (a + (b + c)).
			if (Instruction::isAssociative(Opcode) && CE1->getOpcode() == Opcode) {
				Constant *T = ConstantExpr::get(Opcode, CE1->getOperand(1), C2);
				if (!isa<ConstantExpr>(T) || cast<ConstantExpr>(T)->getOpcode() != Opcode)
					return ConstantExpr::get(Opcode, CE1->getOperand(0), T);
			}
		} else if (isa<ConstantExpr>(C2)) {
			// If C2 is a constant expr and C1 isn't, flop them around and fold the
			// other way if possible.
			if (Instruction::isCommutative(Opcode))
				return ConstantFoldBinaryInstruction(Opcode, C2, C1);
		}

		// i1 can be simplified in many cases.
		if (C1->getType()->isIntegerTy(1)) {
			switch (Opcode) {
			case Instruction::Add:
			case Instruction::Sub:
				return ConstantExpr::getXor(C1, C2);
			case Instruction::Mul:
				return ConstantExpr::getAnd(C1, C2);
			case Instruction::Shl:
			case Instruction::LShr:
			case Instruction::AShr:
				// We can assume that C2 == 0.  If it were one the result would be
				// undefined because the shift value is as large as the bitwidth.
				return C1;
			case Instruction::SDiv:
			case Instruction::UDiv:
				// We can assume that C2 == 1.  If it were zero the result would be
				// undefined through division by zero.
				return C1;
			case Instruction::URem:
			case Instruction::SRem:
				// We can assume that C2 == 1.  If it were zero the result would be
				// undefined through division by zero.
				return ConstantInt::getFalse(C1->getContext());
			default:
				break;
			}
		}

		// We don't know how to fold this.
		return null;
		*/
			
		return null;
	}

	// TODO Implement this
	protected static Constant constantFoldPtrToIntWithGlobaValue(GlobalValue globalVal) {
		/*MaybeAlign GVAlign;

		if (Module *TheModule = GV->getParent()) {
			GVAlign = GV->getPointerAlignment(TheModule->getDataLayout());

			// If the function alignment is not specified then assume that it
			// is 4.
			// This is dangerous; on x86, the alignment of the pointer
			// corresponds to the alignment of the function, but might be less
			// than 4 if it isn't explicitly specified.
			// However, a fix for this behaviour was reverted because it
			// increased code size (see https://reviews.llvm.org/D55115)
			// FIXME: This code should be deleted once existing targets have
			// appropriate defaults
			if (!GVAlign && isa<Function>(GV))
				GVAlign = Align(4);
		} else if (isa<Function>(GV)) {
			// Without a datalayout we have to assume the worst case: that the
			// function pointer isn't aligned at all.
			GVAlign = llvm::None;
		} else {
			GVAlign = MaybeAlign(GV->getAlignment());
		}

		if (GVAlign && *GVAlign > 1) {
			unsigned DstWidth = CI2->getType()->getBitWidth();
			unsigned SrcWidth = std::min(DstWidth, Log2(*GVAlign));
			APInt BitsNotSet(APInt::getLowBitsSet(DstWidth, SrcWidth));

			// If checking bits we know are clear, return zero.
			if ((CI2->getValue() & BitsNotSet) == CI2->getValue())
				return Constant::getNullValue(CI2->getType());
		}*/

		return null;
	}

	protected static Constant constantFoldBinOpsWithUndefOperands(BinaryOperatorID binOpCode, Constant C1, Constant C2) {
		// Handle scalar UndefValue. Vectors are always evaluated per element.
		boolean hasScalarUndef = !C1.getType().isVectorType() && (C1 instanceof UndefValue || C2 instanceof UndefValue);

		/*
		if (hasScalarUndef) {
			switch (binOpCode) {
			case XOR:
				if (C1 instanceof UndefValue && C2 instanceof UndefValue)
					// Handle undef ^ undef -> 0 special case. This is a common idiom (misuse).
					return Constant.getNullValue(C1.getType());

			case ADD:
			case SUB:
				return UndefValue.get(C1.getType());
			case AND:
				if (C1 instanceof UndefValue && C2 instanceof UndefValue) { // undef & undef -> undef
					return C1;
				}
				return Constant.getNullValue(C1.getType());   // undef & X -> 0
			case MUL: {

				if (C1 instanceof UndefValue && C2 instanceof UndefValue) { // undef * undef -> undef
					return C1;
				}

				APInt CV;

				// X * undef -> undef   if X is odd
				if (match(C1, apInt(CV)) || match(C2, apInt(CV))) {
					// TODO Fix this after we have APInt correctly implemented

					//if ((*CV)[0])
					//	return UndefValue::get(C1->getType());

					// X * undef -> 0       otherwise
					return Constant.getNullValue(C1.getType());
				}
			}

			case SDIV:
			case UDIV:
				// X / undef -> undef
				if (isa<UndefValue>(C2))
					return C2;
				// undef / 0 -> undef
				// undef / 1 -> undef
				if (match(C2, m_Zero()) || match(C2, m_One()))
					return C1;
				// undef / X -> 0       otherwise
				return Constant::getNullValue(C1->getType());
			case Instruction::URem:
			case Instruction::SRem:
				// X % undef -> undef
				if (match(C2, m_Undef()))
					return C2;
			// undef % 0 -> undef
			if (match(C2, m_Zero()))
				return C1;
			// undef % X -> 0       otherwise
			return Constant::getNullValue(C1->getType());
			case Instruction::Or:                          // X | undef -> -1
				if (isa<UndefValue>(C1) && isa<UndefValue>(C2)) // undef | undef -> undef
					return C1;
			return Constant::getAllOnesValue(C1->getType()); // undef | X -> ~0
			case Instruction::LShr:
				// X >>l undef -> undef
				if (isa<UndefValue>(C2))
					return C2;
			// undef >>l 0 -> undef
			if (match(C2, m_Zero()))
				return C1;
			// undef >>l X -> 0
			return Constant::getNullValue(C1->getType());
			case Instruction::AShr:
				// X >>a undef -> undef
				if (isa<UndefValue>(C2))
					return C2;
			// undef >>a 0 -> undef
			if (match(C2, m_Zero()))
				return C1;
			// TODO: undef >>a X -> undef if the shift is exact
			// undef >>a X -> 0
			return Constant::getNullValue(C1->getType());
			case Instruction::Shl:
				// X << undef -> undef
				if (isa<UndefValue>(C2))
					return C2;
			// undef << 0 -> undef
			if (match(C2, m_Zero()))
				return C1;
			// undef << X -> 0
			return Constant::getNullValue(C1->getType());
			case Instruction::FAdd:
			case Instruction::FSub:
			case Instruction::FMul:
			case Instruction::FDiv:
			case Instruction::FRem:
				// [any flop] undef, undef -> undef
				if (isa<UndefValue>(C1) && isa<UndefValue>(C2))
					return C1;
			// [any flop] C, undef -> NaN
			// [any flop] undef, C -> NaN
			// We could potentially specialize NaN/Inf constants vs. 'normal'
			// constants (possibly differently depending on opcode and operand). This
			// would allow returning undef sometimes. But it is always safe to fold to
			// NaN because we can choose the undef operand as NaN, and any FP opcode
			// with a NaN operand will propagate NaN.
			return ConstantFP::getNaN(C1->getType());
			case Instruction::BinaryOpsEnd:
				llvm_unreachable("Invalid BinaryOp");
			}
		}
		
		*/
		
		return null;
	}

}

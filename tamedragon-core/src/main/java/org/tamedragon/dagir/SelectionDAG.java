package org.tamedragon.dagir;

//import com.compilervision.common.DiGraph;
import org.tamedragon.dagir.valuetypes.ExtendedValueType;

public class SelectionDAG // extends DiGraph{
{
/*
	public Object getNode(int opCode, ExtendedValueType valueType,
			SelectionDagValue N1, SelectionDagValue N2) {
		
		ConstantSDNode *N1C = dyn_cast<ConstantSDNode>(N1.getNode());
		  ConstantSDNode *N2C = dyn_cast<ConstantSDNode>(N2.getNode());
		  switch (Opcode) {
		  default: break;
		  case ISD::TokenFactor:
		    assert(VT == MVT::Other && N1.getValueType() == MVT::Other &&
		           N2.getValueType() == MVT::Other && "Invalid token factor!");
		    // Fold trivial token factors.
		    if (N1.getOpcode() == ISD::EntryToken) return N2;
		    if (N2.getOpcode() == ISD::EntryToken) return N1;
		    if (N1 == N2) return N1;
		    break;
		  case ISD::CONCAT_VECTORS:
		    // A CONCAT_VECTOR with all operands BUILD_VECTOR can be simplified to
		    // one big BUILD_VECTOR.
		    if (N1.getOpcode() == ISD::BUILD_VECTOR &&
		        N2.getOpcode() == ISD::BUILD_VECTOR) {
		      SmallVector<SDValue, 16> Elts(N1.getNode()->op_begin(),
		                                    N1.getNode()->op_end());
		      Elts.append(N2.getNode()->op_begin(), N2.getNode()->op_end());
		      return getNode(ISD::BUILD_VECTOR, DL, VT, &Elts[0], Elts.size());
		    }
		    break;
		  case ISD::AND:
		    assert(VT.isInteger() && "This operator does not apply to FP types!");
		    assert(N1.getValueType() == N2.getValueType() &&
		           N1.getValueType() == VT && "Binary operator types must match!");
		    // (X & 0) -> 0.  This commonly occurs when legalizing i64 values, so it's
		    // worth handling here.
		    if (N2C && N2C->isNullValue())
		      return N2;
		    if (N2C && N2C->isAllOnesValue())  // X & -1 -> X
		      return N1;
		    break;
		  case ISD::OR:
		  case ISD::XOR:
		  case ISD::ADD:
		  case ISD::SUB:
		    assert(VT.isInteger() && "This operator does not apply to FP types!");
		    assert(N1.getValueType() == N2.getValueType() &&
		           N1.getValueType() == VT && "Binary operator types must match!");
		    // (X ^|+- 0) -> X.  This commonly occurs when legalizing i64 values, so
		    // it's worth handling here.
		    if (N2C && N2C->isNullValue())
		      return N1;
		    break;
		  case ISD::UDIV:
		  case ISD::UREM:
		  case ISD::MULHU:
		  case ISD::MULHS:
		  case ISD::MUL:
		  case ISD::SDIV:
		  case ISD::SREM:
		    assert(VT.isInteger() && "This operator does not apply to FP types!");
		    assert(N1.getValueType() == N2.getValueType() &&
		           N1.getValueType() == VT && "Binary operator types must match!");
		    break;
		  case ISD::FADD:
		  case ISD::FSUB:
		  case ISD::FMUL:
		  case ISD::FDIV:
		  case ISD::FREM:
		    if (getTarget().Options.UnsafeFPMath) {
		      if (Opcode == ISD::FADD) {
		        // 0+x --> x
		        if (ConstantFPSDNode *CFP = dyn_cast<ConstantFPSDNode>(N1))
		          if (CFP->getValueAPF().isZero())
		            return N2;
		        // x+0 --> x
		        if (ConstantFPSDNode *CFP = dyn_cast<ConstantFPSDNode>(N2))
		          if (CFP->getValueAPF().isZero())
		            return N1;
		      } else if (Opcode == ISD::FSUB) {
		        // x-0 --> x
		        if (ConstantFPSDNode *CFP = dyn_cast<ConstantFPSDNode>(N2))
		          if (CFP->getValueAPF().isZero())
		            return N1;
		      }
		    }
		    assert(VT.isFloatingPoint() && "This operator only applies to FP types!");
		    assert(N1.getValueType() == N2.getValueType() &&
		           N1.getValueType() == VT && "Binary operator types must match!");
		    break;
		  case ISD::FCOPYSIGN:   // N1 and result must match.  N1/N2 need not match.
		    assert(N1.getValueType() == VT &&
		           N1.getValueType().isFloatingPoint() &&
		           N2.getValueType().isFloatingPoint() &&
		           "Invalid FCOPYSIGN!");
		    break;
		  case ISD::SHL:
		  case ISD::SRA:
		  case ISD::SRL:
		  case ISD::ROTL:
		  case ISD::ROTR:
		    assert(VT == N1.getValueType() &&
		           "Shift operators return type must be the same as their first arg");
		    assert(VT.isInteger() && N2.getValueType().isInteger() &&
		           "Shifts only work on integers");
		    // Verify that the shift amount VT is bit enough to hold valid shift
		    // amounts.  This catches things like trying to shift an i1024 value by an
		    // i8, which is easy to fall into in generic code that uses
		    // TLI.getShiftAmount().
		    assert(N2.getValueType().getSizeInBits() >=
		                   Log2_32_Ceil(N1.getValueType().getSizeInBits()) &&
		           "Invalid use of small shift amount with oversized value!");

		    // Always fold shifts of i1 values so the code generator doesn't need to
		    // handle them.  Since we know the size of the shift has to be less than the
		    // size of the value, the shift/rotate count is guaranteed to be zero.
		    if (VT == MVT::i1)
		      return N1;
		    if (N2C && N2C->isNullValue())
		      return N1;
		    break;
		  case ISD::FP_ROUND_INREG: {
		    EVT EVT = cast<VTSDNode>(N2)->getVT();
		    assert(VT == N1.getValueType() && "Not an inreg round!");
		    assert(VT.isFloatingPoint() && EVT.isFloatingPoint() &&
		           "Cannot FP_ROUND_INREG integer types");
		    assert(EVT.isVector() == VT.isVector() &&
		           "FP_ROUND_INREG type should be vector iff the operand "
		           "type is vector!");
		    assert((!EVT.isVector() ||
		            EVT.getVectorNumElements() == VT.getVectorNumElements()) &&
		           "Vector element counts must match in FP_ROUND_INREG");
		    assert(EVT.bitsLE(VT) && "Not rounding down!");
		    (void)EVT;
		    if (cast<VTSDNode>(N2)->getVT() == VT) return N1;  // Not actually rounding.
		    break;
		  }
		  case ISD::FP_ROUND:
		    assert(VT.isFloatingPoint() &&
		           N1.getValueType().isFloatingPoint() &&
		           VT.bitsLE(N1.getValueType()) &&
		           isa<ConstantSDNode>(N2) && "Invalid FP_ROUND!");
		    if (N1.getValueType() == VT) return N1;  // noop conversion.
		    break;
		  case ISD::AssertSext:
		  case ISD::AssertZext: {
		    EVT EVT = cast<VTSDNode>(N2)->getVT();
		    assert(VT == N1.getValueType() && "Not an inreg extend!");
		    assert(VT.isInteger() && EVT.isInteger() &&
		           "Cannot *_EXTEND_INREG FP types");
		    assert(!EVT.isVector() &&
		           "AssertSExt/AssertZExt type should be the vector element type "
		           "rather than the vector type!");
		    assert(EVT.bitsLE(VT) && "Not extending!");
		    if (VT == EVT) return N1; // noop assertion.
		    break;
		  }
		  case ISD::SIGN_EXTEND_INREG: {
		    EVT EVT = cast<VTSDNode>(N2)->getVT();
		    assert(VT == N1.getValueType() && "Not an inreg extend!");
		    assert(VT.isInteger() && EVT.isInteger() &&
		           "Cannot *_EXTEND_INREG FP types");
		    assert(EVT.isVector() == VT.isVector() &&
		           "SIGN_EXTEND_INREG type should be vector iff the operand "
		           "type is vector!");
		    assert((!EVT.isVector() ||
		            EVT.getVectorNumElements() == VT.getVectorNumElements()) &&
		           "Vector element counts must match in SIGN_EXTEND_INREG");
		    assert(EVT.bitsLE(VT) && "Not extending!");
		    if (EVT == VT) return N1;  // Not actually extending

		    if (N1C) {
		      APInt Val = N1C->getAPIntValue();
		      unsigned FromBits = EVT.getScalarType().getSizeInBits();
		      Val <<= Val.getBitWidth()-FromBits;
		      Val = Val.ashr(Val.getBitWidth()-FromBits);
		      return getConstant(Val, VT);
		    }
		    break;
		  }
		  case ISD::EXTRACT_VECTOR_ELT:
		    // EXTRACT_VECTOR_ELT of an UNDEF is an UNDEF.
		    if (N1.getOpcode() == ISD::UNDEF)
		      return getUNDEF(VT);

		    // EXTRACT_VECTOR_ELT of CONCAT_VECTORS is often formed while lowering is
		    // expanding copies of large vectors from registers.
		    if (N2C &&
		        N1.getOpcode() == ISD::CONCAT_VECTORS &&
		        N1.getNumOperands() > 0) {
		      unsigned Factor =
		        N1.getOperand(0).getValueType().getVectorNumElements();
		      return getNode(ISD::EXTRACT_VECTOR_ELT, DL, VT,
		                     N1.getOperand(N2C->getZExtValue() / Factor),
		                     getConstant(N2C->getZExtValue() % Factor,
		                                 N2.getValueType()));
		    }

		    // EXTRACT_VECTOR_ELT of BUILD_VECTOR is often formed while lowering is
		    // expanding large vector constants.
		    if (N2C && N1.getOpcode() == ISD::BUILD_VECTOR) {
		      SDValue Elt = N1.getOperand(N2C->getZExtValue());
		      EVT VEltTy = N1.getValueType().getVectorElementType();
		      if (Elt.getValueType() != VEltTy) {
		        // If the vector element type is not legal, the BUILD_VECTOR operands
		        // are promoted and implicitly truncated.  Make that explicit here.
		        Elt = getNode(ISD::TRUNCATE, DL, VEltTy, Elt);
		      }
		      if (VT != VEltTy) {
		        // If the vector element type is not legal, the EXTRACT_VECTOR_ELT
		        // result is implicitly extended.
		        Elt = getNode(ISD::ANY_EXTEND, DL, VT, Elt);
		      }
		      return Elt;
		    }

		    // EXTRACT_VECTOR_ELT of INSERT_VECTOR_ELT is often formed when vector
		    // operations are lowered to scalars.
		    if (N1.getOpcode() == ISD::INSERT_VECTOR_ELT) {
		      // If the indices are the same, return the inserted element else
		      // if the indices are known different, extract the element from
		      // the original vector.
		      SDValue N1Op2 = N1.getOperand(2);
		      ConstantSDNode *N1Op2C = dyn_cast<ConstantSDNode>(N1Op2.getNode());

		      if (N1Op2C && N2C) {
		        if (N1Op2C->getZExtValue() == N2C->getZExtValue()) {
		          if (VT == N1.getOperand(1).getValueType())
		            return N1.getOperand(1);
		          else
		            return getSExtOrTrunc(N1.getOperand(1), DL, VT);
		        }

		        return getNode(ISD::EXTRACT_VECTOR_ELT, DL, VT, N1.getOperand(0), N2);
		      }
		    }
		    break;
		  case ISD::EXTRACT_ELEMENT:
		    assert(N2C && (unsigned)N2C->getZExtValue() < 2 && "Bad EXTRACT_ELEMENT!");
		    assert(!N1.getValueType().isVector() && !VT.isVector() &&
		           (N1.getValueType().isInteger() == VT.isInteger()) &&
		           N1.getValueType() != VT &&
		           "Wrong types for EXTRACT_ELEMENT!");

		    // EXTRACT_ELEMENT of BUILD_PAIR is often formed while legalize is expanding
		    // 64-bit integers into 32-bit parts.  Instead of building the extract of
		    // the BUILD_PAIR, only to have legalize rip it apart, just do it now.
		    if (N1.getOpcode() == ISD::BUILD_PAIR)
		      return N1.getOperand(N2C->getZExtValue());

		    // EXTRACT_ELEMENT of a constant int is also very common.
		    if (ConstantSDNode *C = dyn_cast<ConstantSDNode>(N1)) {
		      unsigned ElementSize = VT.getSizeInBits();
		      unsigned Shift = ElementSize * N2C->getZExtValue();
		      APInt ShiftedVal = C->getAPIntValue().lshr(Shift);
		      return getConstant(ShiftedVal.trunc(ElementSize), VT);
		    }
		    break;
		  case ISD::EXTRACT_SUBVECTOR: {
		    SDValue Index = N2;
		    if (VT.isSimple() && N1.getValueType().isSimple()) {
		      assert(VT.isVector() && N1.getValueType().isVector() &&
		             "Extract subvector VTs must be a vectors!");
		      assert(VT.getVectorElementType() == N1.getValueType().getVectorElementType() &&
		             "Extract subvector VTs must have the same element type!");
		      assert(VT.getSimpleVT() <= N1.getValueType().getSimpleVT() &&
		             "Extract subvector must be from larger vector to smaller vector!");

		      if (isa<ConstantSDNode>(Index.getNode())) {
		        assert((VT.getVectorNumElements() +
		                cast<ConstantSDNode>(Index.getNode())->getZExtValue()
		                <= N1.getValueType().getVectorNumElements())
		               && "Extract subvector overflow!");
		      }

		      // Trivial extraction.
		      if (VT.getSimpleVT() == N1.getValueType().getSimpleVT())
		        return N1;
		    }
		    break;
		  }
		  }

		  if (N1C) {
		    if (N2C) {
		      SDValue SV = FoldConstantArithmetic(Opcode, VT, N1C, N2C);
		      if (SV.getNode()) return SV;
		    } else {      // Cannonicalize constant to RHS if commutative
		      if (isCommutativeBinOp(Opcode)) {
		        std::swap(N1C, N2C);
		        std::swap(N1, N2);
		      }
		    }
		  }

		  // Constant fold FP operations.
		  ConstantFPSDNode *N1CFP = dyn_cast<ConstantFPSDNode>(N1.getNode());
		  ConstantFPSDNode *N2CFP = dyn_cast<ConstantFPSDNode>(N2.getNode());
		  if (N1CFP) {
		    if (!N2CFP && isCommutativeBinOp(Opcode)) {
		      // Cannonicalize constant to RHS if commutative
		      std::swap(N1CFP, N2CFP);
		      std::swap(N1, N2);
		    } else if (N2CFP && VT != MVT::ppcf128) {
		      APFloat V1 = N1CFP->getValueAPF(), V2 = N2CFP->getValueAPF();
		      APFloat::opStatus s;
		      switch (Opcode) {
		      case ISD::FADD:
		        s = V1.add(V2, APFloat::rmNearestTiesToEven);
		        if (s != APFloat::opInvalidOp)
		          return getConstantFP(V1, VT);
		        break;
		      case ISD::FSUB:
		        s = V1.subtract(V2, APFloat::rmNearestTiesToEven);
		        if (s!=APFloat::opInvalidOp)
		          return getConstantFP(V1, VT);
		        break;
		      case ISD::FMUL:
		        s = V1.multiply(V2, APFloat::rmNearestTiesToEven);
		        if (s!=APFloat::opInvalidOp)
		          return getConstantFP(V1, VT);
		        break;
		      case ISD::FDIV:
		        s = V1.divide(V2, APFloat::rmNearestTiesToEven);
		        if (s!=APFloat::opInvalidOp && s!=APFloat::opDivByZero)
		          return getConstantFP(V1, VT);
		        break;
		      case ISD::FREM :
		        s = V1.mod(V2, APFloat::rmNearestTiesToEven);
		        if (s!=APFloat::opInvalidOp && s!=APFloat::opDivByZero)
		          return getConstantFP(V1, VT);
		        break;
		      case ISD::FCOPYSIGN:
		        V1.copySign(V2);
		        return getConstantFP(V1, VT);
		      default: break;
		      }
		    }

		    if (Opcode == ISD::FP_ROUND) {
		      APFloat V = N1CFP->getValueAPF();    // make copy
		      bool ignored;
		      // This can return overflow, underflow, or inexact; we don't care.
		      // FIXME need to be more flexible about rounding mode.
		      (void)V.convert(*EVTToAPFloatSemantics(VT),
		                      APFloat::rmNearestTiesToEven, &ignored);
		      return getConstantFP(V, VT);
		    }
		  }

		  // Canonicalize an UNDEF to the RHS, even over a constant.
		  if (N1.getOpcode() == ISD::UNDEF) {
		    if (isCommutativeBinOp(Opcode)) {
		      std::swap(N1, N2);
		    } else {
		      switch (Opcode) {
		      case ISD::FP_ROUND_INREG:
		      case ISD::SIGN_EXTEND_INREG:
		      case ISD::SUB:
		      case ISD::FSUB:
		      case ISD::FDIV:
		      case ISD::FREM:
		      case ISD::SRA:
		        return N1;     // fold op(undef, arg2) -> undef
		      case ISD::UDIV:
		      case ISD::SDIV:
		      case ISD::UREM:
		      case ISD::SREM:
		      case ISD::SRL:
		      case ISD::SHL:
		        if (!VT.isVector())
		          return getConstant(0, VT);    // fold op(undef, arg2) -> 0
		        // For vectors, we can't easily build an all zero vector, just return
		        // the LHS.
		        return N2;
		      }
		    }
		  }

		  // Fold a bunch of operators when the RHS is undef.
		  if (N2.getOpcode() == ISD::UNDEF) {
		    switch (Opcode) {
		    case ISD::XOR:
		      if (N1.getOpcode() == ISD::UNDEF)
		        // Handle undef ^ undef -> 0 special case. This is a common
		        // idiom (misuse).
		        return getConstant(0, VT);
		      // fallthrough
		    case ISD::ADD:
		    case ISD::ADDC:
		    case ISD::ADDE:
		    case ISD::SUB:
		    case ISD::UDIV:
		    case ISD::SDIV:
		    case ISD::UREM:
		    case ISD::SREM:
		      return N2;       // fold op(arg1, undef) -> undef
		    case ISD::FADD:
		    case ISD::FSUB:
		    case ISD::FMUL:
		    case ISD::FDIV:
		    case ISD::FREM:
		      if (getTarget().Options.UnsafeFPMath)
		        return N2;
		      break;
		    case ISD::MUL:
		    case ISD::AND:
		    case ISD::SRL:
		    case ISD::SHL:
		      if (!VT.isVector())
		        return getConstant(0, VT);  // fold op(arg1, undef) -> 0
		      // For vectors, we can't easily build an all zero vector, just return
		      // the LHS.
		      return N1;
		    case ISD::OR:
		      if (!VT.isVector())
		        return getConstant(APInt::getAllOnesValue(VT.getSizeInBits()), VT);
		      // For vectors, we can't easily build an all one vector, just return
		      // the LHS.
		      return N1;
		    case ISD::SRA:
		      return N1;
		    }
		  }

		  // Memoize this node if possible.
		  SDNode *N;
		  SDVTList VTs = getVTList(VT);
		  if (VT != MVT::Glue) {
		    SDValue Ops[] = { N1, N2 };
		    FoldingSetNodeID ID;
		    AddNodeIDNode(ID, Opcode, VTs, Ops, 2);
		    void *IP = 0;
		    if (SDNode *E = CSEMap.FindNodeOrInsertPos(ID, IP))
		      return SDValue(E, 0);

		    N = new (NodeAllocator) BinarySDNode(Opcode, DL, VTs, N1, N2);
		    CSEMap.InsertNode(N, IP);
		  } else {
		    N = new (NodeAllocator) BinarySDNode(Opcode, DL, VTs, N1, N2);
		  }

		  AllNodes.push_back(N);
		#ifndef NDEBUG
		  VerifySDNode(N);
		#endif
		  return SDValue(N, 0);

		}
		*/
}

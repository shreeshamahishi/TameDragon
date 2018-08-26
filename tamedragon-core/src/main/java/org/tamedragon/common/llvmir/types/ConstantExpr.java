package org.tamedragon.common.llvmir.types;

import java.util.ArrayList;
import java.util.List;

import org.tamedragon.common.llvmir.instructions.Instruction;
import org.tamedragon.common.llvmir.instructions.BinaryOperator.BinaryOperatorID;
import org.tamedragon.common.llvmir.instructions.Instruction.InstructionID;

/**
 * ConstantExpr - a constant value that is initialized with an expression using 
 * other constant values. This class uses the standard Instruction opcodes to define the various
 * constant expressions.
 * @author ipsg
 *
 */
public class ConstantExpr extends Constant {

	private InstructionID opCode;

	public ConstantExpr(Type ty, List<Value> operands, InstructionID opCode){
		super(ty, operands);
		this.opCode = opCode;
		setValueTypeID(ValueTypeID.CONST_EXPR);
	}

	/**
	 * Static methods to construct a ConstantExpr of different kinds.  Note that
	 * these methods may return a object that is not an instance of the
	 * ConstantExpr class, because they will attempt to fold the constant
	 * expression into something simpler if possible.
	 * getAlignOf constant expr - computes the alignment of a type in a target
	 * independent way (Note: the return type is an i64).
	 * alignof is implemented as: (i64) gep ({i1,Ty}*)null, 0, 1
	 * Note that a non-inbounds gep is used, as null isn't within any object.
	 * @param type
	 * @return
	 */
	public static Constant getAlignOf(Type type){
		try{
			CompilationContext compilationContext = type.getCompilationContext();
			Type aligningType =  StructType.getInt1Type(compilationContext, false);
			Constant nullPtr = Constant.getNullValue(Type.getPointerType(compilationContext, aligningType, 0));
			Constant Zero = ConstantInt.create(Type.getInt64Type(compilationContext, false), 0, false);
			Constant One = ConstantInt.create(Type.getInt32Type(compilationContext, false), 1, false);
			List<Constant> indices = new ArrayList<Constant>(); 
			indices.add(Zero);
			indices.add(One);
			Constant gepConst = null;
			gepConst = Constant.getElementPtr(nullPtr, indices);
			return getPtrToInt(gepConst, Type.getInt64Type(compilationContext, false));
		}
		catch(Exception e){
			//TODO log here
			e.printStackTrace();
			System.exit(-1);
		}

		return null;
	}

	private static Constant getPtrToInt(Constant gepConst, IntegerType int64Type) {
		// TODO Implement this
		return null;

	}

	/**
	 * getSizeOf constant expr - computes the (alloc) size of a type (in
	 * address-units, not bits) in a target independent way (Note: the return
	 * type is an i64).
	 * sizeof is implemented as: (i64) gep (Ty*)null, 1
	 * Note that a non-inbounds gep is used, as null isn't within any object.
	 * @param ty
	 * @return
	 */
	public static Constant getSizeOf(Type ty){
		try{
			CompilationContext compilationContext = ty.getCompilationContext();
			Constant gepIdx = ConstantInt.create(Type.getInt32Type(compilationContext, false), 1, false);

			Constant nullPtr = Constant.getNullValue(Type.getPointerType(compilationContext, ty, 0));

			Constant gepConst = Constant.getElementPtr(nullPtr, gepIdx);
			return getPtrToInt(gepConst, Type.getInt64Type(compilationContext, false));
		}
		catch(Exception e){
			//TODO Log here
			System.exit(-1);

		}
		return null;
	}

	public InstructionID getOpCode() {
		return opCode;
	}

	/**
	 * getOffsetOf constant expr - computes the offset of a struct field in a 
	 * target independent way (Note: the return type is an i64).
	 * @param sty
	 * @param fieldNo
	 * @return
	 * @throws Exception
	 */
	public static Constant getOffsetOf(StructType sty, int fieldNo) throws Exception {
		IntegerType type = Type.getInt32Type(sty.getCompilationContext(), false);
		return getOffsetOf(sty, ConstantInt.create(type, fieldNo, false));
	}

	/**
	 * getOffsetOf constant expr - This is a generalized form of getOffsetOf,
	 * which supports any aggregate type, and any Constant index.
	 * offsetof is implemented as: (i64) gep (Ty*)null, 0, FieldNo
	 * Note that a non-inbounds gep is used, as null isn't within any object.
	 * @param ty
	 * @param fieldNo
	 * @return
	 */
	public static Constant getOffsetOf(Type ty, Constant fieldNo){
		try{
			CompilationContext compilationContext = ty.getCompilationContext();

			IntegerType type = Type.getInt64Type(compilationContext, false);
			Constant zero = ConstantInt.create(type, 0, false);

			List<Constant> gepIndices = new ArrayList<Constant>();
			gepIndices.add(zero);
			gepIndices.add(fieldNo);

			Constant nullPtr = Constant.getNullValue(Type.getPointerType(compilationContext, ty, 0));

			Constant gepConst = getElementPtr(nullPtr, gepIndices);

			return getPtrToInt(gepConst, Type.getInt64Type(compilationContext, false));
		}
		catch(Exception e){
			// TODO Log here
			e.printStackTrace();
			System.exit(-1);
		}
		return null;
	}


	public static Constant getNeg(Constant constant, boolean hasNUW , boolean hasNSW ) throws Exception{
		return getConstWithOppositeSign(constant);
	}

	public static Constant getFNeg(Constant constant, boolean hasNUW , boolean hasNSW ) throws Exception{
		return getConstWithOppositeSign(constant);
	}

	public static Constant getNot(Constant c) throws Exception{
		if(!c.getType().isIntOrIntVectorType()){
			throw new Exception("Cannot NOT a nonintegral value!");
		}

		// TODO Fix this by implementing getAllOnesValue
		//return Constant.getConstant(BinaryOperatorID.XOR, C, Constant.getAllOnesValue(C.getType()));
		return Constant.getConstant(BinaryOperatorID.XOR, c, c);
	}

	@Override
	public String toString() {
		if(this.getOperand(0) instanceof Instruction)
			return ((Instruction)getOperand(0)).emit();
		return super.toString();
	}

	/*

	  static Constant *getAdd(Constant *C1, Constant *C2,
	                          bool HasNUW = false, bool HasNSW = false);
	  static Constant *getFAdd(Constant *C1, Constant *C2);
	  static Constant *getSub(Constant *C1, Constant *C2,
	                          bool HasNUW = false, bool HasNSW = false);
	  static Constant *getFSub(Constant *C1, Constant *C2);
	  static Constant *getMul(Constant *C1, Constant *C2,
	                          bool HasNUW = false, bool HasNSW = false);
	  static Constant *getFMul(Constant *C1, Constant *C2);
	  static Constant *getUDiv(Constant *C1, Constant *C2, bool isExact = false);
	  static Constant *getSDiv(Constant *C1, Constant *C2, bool isExact = false);
	  static Constant *getFDiv(Constant *C1, Constant *C2);
	  static Constant *getURem(Constant *C1, Constant *C2);
	  static Constant *getSRem(Constant *C1, Constant *C2);
	  static Constant *getFRem(Constant *C1, Constant *C2);
	  static Constant *getAnd(Constant *C1, Constant *C2);
	  static Constant *getOr(Constant *C1, Constant *C2);
	  static Constant *getXor(Constant *C1, Constant *C2);
	  static Constant *getShl(Constant *C1, Constant *C2,
	                          bool HasNUW = false, bool HasNSW = false);
	  static Constant *getLShr(Constant *C1, Constant *C2, bool isExact = false);
	  static Constant *getAShr(Constant *C1, Constant *C2, bool isExact = false);
	  static Constant *getTrunc   (Constant *C, Type *Ty);
	  static Constant *getSExt    (Constant *C, Type *Ty);
	  static Constant *getZExt    (Constant *C, Type *Ty);
	  static Constant *getFPTrunc (Constant *C, Type *Ty);
	  static Constant *getFPExtend(Constant *C, Type *Ty);
	  static Constant *getUIToFP  (Constant *C, Type *Ty);
	  static Constant *getSIToFP  (Constant *C, Type *Ty);
	  static Constant *getFPToUI  (Constant *C, Type *Ty);
	  static Constant *getFPToSI  (Constant *C, Type *Ty);
	  static Constant *getPtrToInt(Constant *C, Type *Ty);
	  static Constant *getIntToPtr(Constant *C, Type *Ty);
	  static Constant *getBitCast (Constant *C, Type *Ty);

	  static Constant *getNSWNeg(Constant *C) { return getNeg(C, false, true); }
	  static Constant *getNUWNeg(Constant *C) { return getNeg(C, true, false); }
	  static Constant *getNSWAdd(Constant *C1, Constant *C2) {
	    return getAdd(C1, C2, false, true);
	  }
	  static Constant *getNUWAdd(Constant *C1, Constant *C2) {
	    return getAdd(C1, C2, true, false);
	  }
	  static Constant *getNSWSub(Constant *C1, Constant *C2) {
	    return getSub(C1, C2, false, true);
	  }
	  static Constant *getNUWSub(Constant *C1, Constant *C2) {
	    return getSub(C1, C2, true, false);
	  }
	  static Constant *getNSWMul(Constant *C1, Constant *C2) {
	    return getMul(C1, C2, false, true);
	  }
	  static Constant *getNUWMul(Constant *C1, Constant *C2) {
	    return getMul(C1, C2, true, false);
	  }
	  static Constant *getNSWShl(Constant *C1, Constant *C2) {
	    return getShl(C1, C2, false, true);
	  }
	  static Constant *getNUWShl(Constant *C1, Constant *C2) {
	    return getShl(C1, C2, true, false);
	  }
	  static Constant *getExactSDiv(Constant *C1, Constant *C2) {
	    return getSDiv(C1, C2, true);
	  }
	  static Constant *getExactUDiv(Constant *C1, Constant *C2) {
	    return getUDiv(C1, C2, true);
	  }
	  static Constant *getExactAShr(Constant *C1, Constant *C2) {
	    return getAShr(C1, C2, true);
	  }
	  static Constant *getExactLShr(Constant *C1, Constant *C2) {
	    return getLShr(C1, C2, true);
	  }

	  /// Transparently provide more efficient getOperand methods.
	  DECLARE_TRANSPARENT_OPERAND_ACCESSORS(Constant);

	  // @brief Convenience function for getting one of the casting operations
	  // using a CastOps opcode.
	  static Constant *getCast(
	    unsigned ops,  ///< The opcode for the conversion
	    Constant *C,   ///< The constant to be converted
	    Type *Ty ///< The type to which the constant is converted
	  );

	  // @brief Create a ZExt or BitCast cast constant expression
	  static Constant *getZExtOrBitCast(
	    Constant *C,   ///< The constant to zext or bitcast
	    Type *Ty ///< The type to zext or bitcast C to
	  );

	  // @brief Create a SExt or BitCast cast constant expression 
	  static Constant *getSExtOrBitCast(
	    Constant *C,   ///< The constant to sext or bitcast
	    Type *Ty ///< The type to sext or bitcast C to
	  );

	  // @brief Create a Trunc or BitCast cast constant expression
	  static Constant *getTruncOrBitCast(
	    Constant *C,   ///< The constant to trunc or bitcast
	    Type *Ty ///< The type to trunc or bitcast C to
	  );

	  /// @brief Create a BitCast or a PtrToInt cast constant expression
	  static Constant *getPointerCast(
	    Constant *C,   ///< The pointer value to be casted (operand 0)
	    Type *Ty ///< The type to which cast should be made
	  );

	  /// @brief Create a ZExt, Bitcast or Trunc for integer -> integer casts
	  static Constant *getIntegerCast(
	    Constant *C,    ///< The integer constant to be casted 
	    Type *Ty, ///< The integer type to cast to
	    bool isSigned   ///< Whether C should be treated as signed or not
	  );

	  /// @brief Create a FPExt, Bitcast or FPTrunc for fp -> fp casts
	  static Constant *getFPCast(
	    Constant *C,    ///< The integer constant to be casted 
	    Type *Ty ///< The integer type to cast to
	  );

	  /// @brief Return true if this is a convert constant expression
	  bool isCast() const;

	  /// @brief Return true if this is a compare constant expression
	  bool isCompare() const;

	  /// @brief Return true if this is an insertvalue or extractvalue expression,
	  /// and the getIndices() method may be used.
	  bool hasIndices() const;

	  /// @brief Return true if this is a getelementptr expression and all
	  /// the index operands are compile-time known integers within the
	  /// corresponding notional static array extents. Note that this is
	  /// not equivalant to, a subset of, or a superset of the "inbounds"
	  /// property.
	  bool isGEPWithNoNotionalOverIndexing() const;

	  /// Select constant expr
	  ///
	  static Constant *getSelect(Constant *C, Constant *V1, Constant *V2);

	  /// get - Return a binary or shift operator constant expression,
	  /// folding if possible.
	  ///
	  static Constant *get(unsigned Opcode, Constant *C1, Constant *C2,
	                       unsigned Flags = 0);

	  /// @brief Return an ICmp or FCmp comparison operator constant expression.
	  static Constant *getCompare(unsigned short pred, Constant *C1, Constant *C2);

	  /// get* - Return some common constants without having to
	  /// specify the full Instruction::OPCODE identifier.
	  ///
	  static Constant *getICmp(unsigned short pred, Constant *LHS, Constant *RHS);
	  static Constant *getFCmp(unsigned short pred, Constant *LHS, Constant *RHS);

	  /// Getelementptr form.  Value* is only accepted for convenience;
	  /// all elements must be Constant's.
	  ///
	  static Constant *getGetElementPtr(Constant *C,
	                                    ArrayRef<Constant *> IdxList,
	                                    bool InBounds = false) {
	    return getGetElementPtr(C, makeArrayRef((Value * const *)IdxList.data(),
	                                            IdxList.size()),
	                            InBounds);
	  }
	  static Constant *getGetElementPtr(Constant *C,
	                                    Constant *Idx,
	                                    bool InBounds = false) {
	    // This form of the function only exists to avoid ambiguous overload
	    // warnings about whether to convert Idx to ArrayRef<Constant *> or
	    // ArrayRef<Value *>.
	    return getGetElementPtr(C, cast<Value>(Idx), InBounds);
	  }
	  static Constant *getGetElementPtr(Constant *C,
	                                    ArrayRef<Value *> IdxList,
	                                    bool InBounds = false);

	  /// Create an "inbounds" getelementptr. See the documentation for the
	  /// "inbounds" flag in LangRef.html for details.
	  static Constant *getInBoundsGetElementPtr(Constant *C,
	                                            ArrayRef<Constant *> IdxList) {
	    return getGetElementPtr(C, IdxList, true);
	  }
	  static Constant *getInBoundsGetElementPtr(Constant *C,
	                                            Constant *Idx) {
	    // This form of the function only exists to avoid ambiguous overload
	    // warnings about whether to convert Idx to ArrayRef<Constant *> or
	    // ArrayRef<Value *>.
	    return getGetElementPtr(C, Idx, true);
	  }
	  static Constant *getInBoundsGetElementPtr(Constant *C,
	                                            ArrayRef<Value *> IdxList) {
	    return getGetElementPtr(C, IdxList, true);
	  }

	  static Constant *getExtractElement(Constant *Vec, Constant *Idx);
	  static Constant *getInsertElement(Constant *Vec, Constant *Elt,Constant *Idx);
	  static Constant *getShuffleVector(Constant *V1, Constant *V2, Constant *Mask);
	  static Constant *getExtractValue(Constant *Agg, ArrayRef<unsigned> Idxs);
	  static Constant *getInsertValue(Constant *Agg, Constant *Val,
	                                  ArrayRef<unsigned> Idxs);

	  /// getOpcode - Return the opcode at the root of this constant expression
	  unsigned getOpcode() const { return getSubclassDataFromValue(); }

	  /// getPredicate - Return the ICMP or FCMP predicate value. Assert if this is
	  /// not an ICMP or FCMP constant expression.
	  unsigned getPredicate() const;

	  /// getIndices - Assert that this is an insertvalue or exactvalue
	  /// expression and return the list of indices.
	  ArrayRef<unsigned> getIndices() const;

	  /// getOpcodeName - Return a string representation for an opcode.
	  const char *getOpcodeName() const;

	  /// getWithOperandReplaced - Return a constant expression identical to this
	  /// one, but with the specified operand set to the specified value.
	  Constant *getWithOperandReplaced(unsigned OpNo, Constant *Op) const;

	  /// getWithOperands - This returns the current constant expression with the
	  /// operands replaced with the specified values.  The specified array must
	  /// have the same number of operands as our current one.
	  Constant *getWithOperands(ArrayRef<Constant*> Ops) const {
	    return getWithOperands(Ops, getType());
	  }

	  /// getWithOperands - This returns the current constant expression with the
	  /// operands replaced with the specified values and with the specified result
	  /// type.  The specified array must have the same number of operands as our
	  /// current one.
	  Constant *getWithOperands(ArrayRef<Constant*> Ops, Type *Ty) const;

	  virtual void destroyConstant();
	  virtual void replaceUsesOfWithOnConstant(Value *From, Value *To, Use *U);

	  /// Methods for support type inquiry through isa, cast, and dyn_cast:
	  static inline bool classof(const ConstantExpr *) { return true; }
	  static inline bool classof(const Value *V) {
	    return V->getValueID() == ConstantExprVal;
	  }

	private:
	  // Shadow Value::setValueSubclassData with a private forwarding method so that
	  // subclasses cannot accidentally use it.
	  void setValueSubclassData(unsigned short D) {
	    Value::setValueSubclassData(D);
	  }*/
}
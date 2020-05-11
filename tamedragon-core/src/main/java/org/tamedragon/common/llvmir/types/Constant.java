package org.tamedragon.common.llvmir.types;

import java.util.ArrayList;
import java.util.List;

import org.tamedragon.common.llvmir.instructions.CmpInst;
import org.tamedragon.common.llvmir.instructions.BinaryOperator.BinaryOperatorID;
import org.tamedragon.common.llvmir.instructions.CmpInst.Predicate;
import org.tamedragon.common.llvmir.instructions.Instruction.InstructionID;
import org.tamedragon.common.llvmir.math.APInt;
import org.tamedragon.common.llvmir.math.APSInt;
import org.tamedragon.common.llvmir.utils.ConstantFolding;

/**
 * It provides the common facilities of all constant values in an LLVM program.
 * A constant is a value that is immutable at runtime. Functions are constants because their address is
 * immutable. Same with global variables. 
 * @author ipsg
 *
 */
public class Constant extends User {

	public Constant(Type type, List<? extends Value> operandList)  {
		super(type, operandList);
	}

	public boolean isNullValue() {
		// 0 is null.
		if (this instanceof ConstantInt){
			ConstantInt ci = (ConstantInt)this;
			return ci.isZero();
		}

		// +0.0 is null.
		if (this instanceof ConstantFP){
			ConstantFP cfp = (ConstantFP)this;
			return cfp.isZero() && !cfp.isNegative();
		}

		// constant zero is zero for aggregates and cpnull is null for pointers.
		//return isa<ConstantAggregateZero>(this) || isa<ConstantPointerNull>(this);
		return false;
	}

	public boolean isZero(){
		if (this instanceof ConstantInt){
			ConstantInt ci = (ConstantInt)this;
			return ci.isZero();
		}

		// +0.0 is null.
		if (this instanceof ConstantFP){
			ConstantFP cfp = (ConstantFP)this;
			return cfp.isZero() && !cfp.isNegative();
		}

		// TODO include others
		return false;
	}

	public boolean isPositiveUnity(){
		if (this instanceof ConstantInt){
			ConstantInt ci = (ConstantInt)this;
			return ci.isPositiveUnity();
		}

		// +0.0 is null.
		if (this instanceof ConstantFP){
			ConstantFP cfp = (ConstantFP)this;
			return cfp.isPositiveUnity();
		}

		// TODO include others
		return false;
	}

	public boolean isNegativeUnity(){
		if (this instanceof ConstantInt){
			ConstantInt ci = (ConstantInt)this;
			return ci.isNegativeUnity();
		}

		// +0.0 is null.
		if (this instanceof ConstantFP){
			ConstantFP cfp = (ConstantFP)this;
			return cfp.isNegativeUnity();
		}

		// TODO include others
		return false;
	}


	public boolean isTrue(){
		if (!(this instanceof ConstantInt)){
			return false;
		}

		ConstantInt ci = (ConstantInt)this;
		if(!(ci.getApInt().getNumBits() == 1))
			return false;

		if(ci.isZero())
			return false;

		return true;
	}

	public boolean isFalse(){
		if (!(this instanceof ConstantInt)){
			return false;
		}

		ConstantInt ci = (ConstantInt)this;
		if(!(ci.getApInt().getNumBits() == 1))
			return false;

		if(!ci.isZero())
			return false;

		return true;
	}

	public static Constant getCompare(CmpInst.Predicate predicate,  Constant C1, Constant C2)  {
		// assert(C1->getType() == C2->getType() && "Op types should be identical!");

		switch (predicate) {


		//default: llvm_unreachable("Invalid CmpInst predicate");

		case FCMP_FALSE: case FCMP_OEQ: case FCMP_OGT:
		case FCMP_OGE:   case FCMP_OLT: case FCMP_OLE:
		case FCMP_ONE:   case FCMP_ORD: case FCMP_UNO:
		case FCMP_UEQ:   case FCMP_UGT: case FCMP_UGE:
		case FCMP_ULT:   case FCMP_ULE: case FCMP_UNE:
		case FCMP_TRUE:
			return getFCmp(predicate, C1, C2);

		case ICMP_EQ:  case ICMP_NE:  case ICMP_UGT:
		case ICMP_UGE: case ICMP_ULT: case ICMP_ULE:
		case ICMP_SGT: case ICMP_SGE: case ICMP_SLT:
		case ICMP_SLE:
			return getICmp(predicate, C1, C2);

		default:
			// Return false for now
			CompilationContext compilationContext = C1.getContext();
			return getTrueOrFalse(compilationContext, false);

		}
	} 

	public static Constant getICmp(Predicate predicate, Constant c1,
			Constant c2) {

		ConstantInt constInt1 = (ConstantInt) c1;
		ConstantInt constInt2 = (ConstantInt) c2;
		APInt apInt1 = constInt1.getApInt();
		APInt apInt2 = constInt2.getApInt();

		CompilationContext compilationContext = c1.getContext();

		switch(predicate){
		case ICMP_EQ:
			return getTrueOrFalse(compilationContext, apInt1.equals(apInt2));
		case ICMP_NE: 
			return getTrueOrFalse(compilationContext, apInt1.notEquals(apInt2));
		case ICMP_UGT:
			return getTrueOrFalse(compilationContext, apInt1.ugt(apInt2));
		case ICMP_SGT:
			return getTrueOrFalse(compilationContext, apInt1.sgt(apInt2));
		case ICMP_UGE: 
			return getTrueOrFalse(compilationContext, apInt1.uge(apInt2));
		case ICMP_SGE:
			return getTrueOrFalse(compilationContext, apInt1.sge(apInt2));
		case ICMP_ULT: 
			return getTrueOrFalse(compilationContext, apInt1.ult(apInt2));
		case ICMP_SLT:
			return getTrueOrFalse(compilationContext, apInt1.slt(apInt2));
		case ICMP_ULE:
			return getTrueOrFalse(compilationContext, apInt1.ule(apInt2));
		case ICMP_SLE:
			return getTrueOrFalse(compilationContext, apInt1.sle(apInt2));

		default:
			// Return false for now
			return getTrueOrFalse(compilationContext, false);
		}
	}

	private static Constant getFCmp(CmpInst.Predicate predicate, 
			Constant C1, Constant C2)  {

		ConstantFP constFP1 = (ConstantFP) C1;
		ConstantFP constFP2 = (ConstantFP) C2;
		APFloat apFloat1 = constFP1.getAPFloat();
		APFloat apFloat2 = constFP2.getAPFloat();

		CompilationContext compilationContext = C1.getContext();

		switch (predicate) {
		//default: llvm_unreachable("Invalid CmpInst predicate");

		case FCMP_FALSE:
			return getTrueOrFalse(compilationContext, false);

		case FCMP_TRUE:
			return getTrueOrFalse(compilationContext, true);

		case FCMP_OEQ:
		case FCMP_UEQ: 
			if(apFloat1.compare(apFloat2) == 0)
				return getTrueOrFalse(compilationContext, true);
			return getTrueOrFalse(compilationContext, false);

		case FCMP_OGT:
		case FCMP_UGT:
			if(apFloat1.compare(apFloat2) > 0)
				return getTrueOrFalse(compilationContext, true);
			return getTrueOrFalse(compilationContext, false);

		case FCMP_OGE: 
		case FCMP_UGE:
			int compareValue = apFloat1.compare(apFloat2);
			if(compareValue >= 0)
				return getTrueOrFalse(compilationContext, true);
			return getTrueOrFalse(compilationContext, false);

		case FCMP_OLE:
		case FCMP_ULE:
			compareValue = apFloat1.compare(apFloat2);
			if(compareValue <= 0)
				return getTrueOrFalse(compilationContext, true);
			return getTrueOrFalse(compilationContext, false);

		case FCMP_OLT: 
		case FCMP_ULT: 
			compareValue = apFloat1.compare(apFloat2);
			if(compareValue < 0)
				return getTrueOrFalse(compilationContext, true);
			return getTrueOrFalse(compilationContext, false);

		case FCMP_ONE:
		case FCMP_UNE:
			compareValue = apFloat1.compare(apFloat2);
			if(compareValue != 0)
				return getTrueOrFalse(compilationContext, true);
			return getTrueOrFalse(compilationContext, false);

		case FCMP_ORD:
		case FCMP_UNO:
			// Return false for now
			return getTrueOrFalse(compilationContext, false);

		default:
			// Return false for now
			return getTrueOrFalse(compilationContext, false);
		}
	}

	/*
	public static Constant getNullValue(Type type){
		TypeID typeID = type.getTypeId();

		 switch (typeID) {

		  case INTEGER_ID:
		    return ConstantInt

		  case Type::HalfTyID:
		    return ConstantFP::get(Ty->getContext(),
		                           APFloat::getZero(APFloat::IEEEhalf));
		  case Type::FloatTyID:
		    return ConstantFP::get(Ty->getContext(),
		                           APFloat::getZero(APFloat::IEEEsingle));
		  case Type::DoubleTyID:
		    return ConstantFP::get(Ty->getContext(),
		                           APFloat::getZero(APFloat::IEEEdouble));
		  case Type::X86_FP80TyID:
		    return ConstantFP::get(Ty->getContext(),
		                           APFloat::getZero(APFloat::x87DoubleExtended));
		  case Type::FP128TyID:
		    return ConstantFP::get(Ty->getContext(),
		                           APFloat::getZero(APFloat::IEEEquad));
		  case Type::PPC_FP128TyID:
		    return ConstantFP::get(Ty->getContext(),
		                           APFloat(APInt::getNullValue(128)));
		  case Type::PointerTyID:
		    return ConstantPointerNull::get(cast<PointerType>(Ty));
		  case Type::StructTyID:
		  case Type::ArrayTyID:
		  case Type::VectorTyID:
		    return ConstantAggregateZero::get(Ty);
		  default:
		    // Function, Label, or Opaque type?
		    llvm_unreachable("Cannot create a null constant of that type!");
		  }
	}
	 */

	public static Constant getElementPtr(Constant constPtr,
			List<Constant> constIndices) {
		// TODO Auto-generated method stub
		return null;

	}


	public static Constant getElementPtr(Constant constPtr, Constant gEPIdx) {
		List<Constant> indices = new ArrayList<Constant>();
		indices.add(gEPIdx);
		return getElementPtr(constPtr, indices);
	}


	public static Constant getCast(InstructionID castInsOp, Constant trunc, Type dstTy) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Constant getCast(Constant constantValue, Type type) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Constant getTrunc(ConstantInt cI, Type srcTy) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Constant getConstant(BinaryOperatorID operatorID,
			Constant constantValue, Constant constantValue2) throws Exception {

		Type constType1 = constantValue.getType();
		Type constType2 = constantValue2.getType();

		if(constType1 != constType2)
		{
			//TODO throw an exception here?
			return null;
		}

		switch (operatorID) {
		case ADD:
		case SUB:
		case MUL:
		case UDIV: 
		case SDIV: 
		case UREM:
		case SREM: 
		case AND:
		case OR:
		case XOR:
		case SHL:
		case LSHR:
		case ASHR:
			if(!constType1.isIntOrIntVectorType()){
				//TODO throw an exception here?
				return null;
			}
			break;

		case FADD:
		case FSUB:
		case FMUL:
		case FDIV:
		case FREM:
			if(!constType1.isFPOrFPVectorType()){
				//TODO throw an exception here?
				return null;
			}
			break;

		default:
			//TODO throw an exception here?
			return null;
		}

		return ConstantFolding.constantFoldBinaryInstruction(operatorID,
				constantValue, constantValue2);

	}

	public static Constant getZero(Value refValue)
			throws InstantiationException{

		if(refValue instanceof ConstantInt){
			ConstantInt constInt = (ConstantInt)refValue;
			APInt apInt = new APInt(constInt.getApInt().getNumBits(), "0", 
					((APSInt)constInt.getApInt()).isSigned());
			return new ConstantInt((IntegerType)constInt.getType(), apInt);
		}
		else if(refValue instanceof ConstantFP){
			ConstantFP constFp = (ConstantFP)refValue;
			APFloat apFloat = new APFloat(APFloat.IEEEdouble,"0.0");
			return new ConstantFP(constFp.getType(), apFloat);
		}
		else 
			return null;
	}

	public static Constant getTrueOrFalse(CompilationContext compilationContext,
			boolean getTrue){
		try {
			APInt val = new APInt(1, "1", false);
			if(!getTrue)
				val = new APInt(1, "0", false);

			return new ConstantInt(Type.getInt1Type(compilationContext, false),
					val);
		} 
		catch (InstantiationException e) {
			// TODO Log here
			return null;
		}
	}

	/*Constructor to create a '0' constant of arbitrary type.
	 */
	public static Constant getNullValue(Type type) throws InstantiationException {

		switch (type.getTypeId()) {
		case INTEGER_ID:
			return ConstantInt.create(type, 0, false);
		case HALF_FLOAT_ID:
			return ConstantFP.create(type.getCompilationContext(), APFloat.getZero(APFloat.IEEEhalf));
		case FLOAT_ID:
			return ConstantFP.create(type.getCompilationContext(), APFloat.getZero(APFloat.IEEEsingle));
		case DOUBLE_ID:
			return ConstantFP.create(type.getCompilationContext(), APFloat.getZero(APFloat.IEEEdouble));
		case X86_FP80_ID:
			return ConstantFP.create(type.getCompilationContext(), APFloat.getZero(APFloat.x87DoubleExtended));
		case FP128_ID:
			return ConstantFP.create(type.getCompilationContext(), APFloat.getZero(APFloat.IEEEquad));
		case PPC_FP128_ID:
			// TODO Fix this
			//return ConstantFP.create(type.getCompilationContext(), new APFloat(APFloat.PPCDoubleDouble, APInt.getNullValue(128)));
			return ConstantFP.create(type.getCompilationContext(), APFloat.getZero(APFloat.IEEEquad));
		case POINTER_ID:
			return new ConstantPointerNull((PointerType)type);
		case STRUCT_ID:
		case ARRAY_ID:
		case VECTOR_ID:
			return ConstantAggregateZero.get(type);
			// TODO Implement Token type
			//case Type::TokenTyID:
			//	return ConstantTokenNone::get(Ty->getContext());
		default:
			throw new IllegalArgumentException("Cannot create a null constant of that type!");
		}
	}

	public boolean equals(Constant otherConst){
		if((this instanceof ConstantInt) && (otherConst instanceof ConstantInt)){
			ConstantInt constInt = (ConstantInt)this;
			return constInt.equals((ConstantInt) otherConst);
		}
		else if((this instanceof UndefValue) && (otherConst instanceof UndefValue)){
			return true;
		}
		else if(this instanceof Function && (otherConst instanceof Function)){
			Function function = (Function)this;
			return function == (Function)otherConst;
		}
		else if(this instanceof ConstantFP && (otherConst instanceof ConstantFP)){
			ConstantFP constFp = (ConstantFP)this;
			return constFp.equals((ConstantFP) otherConst);
		}
		else 
			return false;
	}

	public boolean isNegative() {
		if(this instanceof ConstantInt){
			ConstantInt constInt = (ConstantInt)this;
			return constInt.isNegative();
		}
		else if(this instanceof ConstantFP){
			ConstantFP constFp = (ConstantFP)this;
			return constFp.isNegative();
		}
		else 
			return false;
	}

	/*If all elements of the vector constant have the same value, return that
	  value. Otherwise, return null. Ignore undefined elements by setting
	  the allowUndefs flag to true.
	 */
	public Constant getSplatValue(boolean allowUndefs) throws InstantiationException {
		if(!getType().isVectorType()) {
			throw new IllegalArgumentException("Only valid for vectors!");
		}

		if(getValueTypeID() == ValueTypeID.CONST_AGGREGATE_ZERO) {
			return getNullValue(getType().getVectorElementType());
		}

		try {
			// TODO Implement  ConstantDataVector
			// ConstantDataVector CV = (ConstantDataVector)(this);
			// return CV->getSplatValue();
			return null;
		}
		catch(ClassCastException cce) {}

		try {
			ConstantVector CV = (ConstantVector)(this);
			return CV.getSplatValue(false);
		}
		catch(ClassCastException cce) {}

		return null;
	}

	public static Constant getNegativeUnityConstant(Value refValue) throws InstantiationException{

		if(refValue instanceof ConstantInt){
			ConstantInt constInt = (ConstantInt)refValue;
			APInt apInt = new APInt(constInt.getApInt().getNumBits(), "-1", false);
			return new ConstantInt((IntegerType)constInt.getType(), apInt);
		}
		else if(refValue instanceof ConstantFP){
			ConstantFP constFp = (ConstantFP)refValue;
			APFloat apFloat = new APFloat(APFloat.IEEEdouble,"-1.0");
			return new ConstantFP(constFp.getType(), apFloat);
		}
		else 
			return null;
	}

	/*
	 * Multiply the constant that is passed with -1
	 */
	public static Constant getConstWithOppositeSign(Constant constValue) throws Exception {
		Constant negUnityConst = getNegativeUnityConstant(constValue);

		return Constant.getConstant(constValue.getType().isIntegerType()?
				BinaryOperatorID.MUL: BinaryOperatorID.FMUL, constValue, negUnityConst);
	}
}

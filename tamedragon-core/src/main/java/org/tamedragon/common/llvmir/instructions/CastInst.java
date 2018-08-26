package org.tamedragon.common.llvmir.instructions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;

import org.tamedragon.common.LatticeValue;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionCreationException;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionDetailAccessException;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.Constant;
import org.tamedragon.common.llvmir.types.ConstantExpr;
import org.tamedragon.common.llvmir.types.Type;
import org.tamedragon.common.llvmir.types.Value;
import org.tamedragon.common.llvmir.types.VectorType;
import org.tamedragon.common.llvmir.utils.LLVMIREmitter;

/**
 * This is the class for all instructions that perform data casts. 
 * @author shreesha
 *
 */

public class CastInst extends Instruction {

	private static final String TRUNC = "trunc";
	private static final String ZEXT = "zext";
	private static final String SEXT  = "sext";
	private static final String FPTRUNC  = "fptrunc ";
	private static final String FPEXT = "fpext";
	private static final String FPTOUI = "fptoui";
	private static final String FPTOSI = "fptosi";
	private static final String UITOFP = "uitofp";
	private static final String SITOFP = "sitofp";
	private static final String PTRTOINT = "ptrtoint";
	private static final String INTTOPTR = "inttoptr";
	private static final String BITCAST = "bitcast";

	protected CastInst(InstructionID instID, String name, Type type, List<Value> operandList, BasicBlock parent){
		super(instID, type, operandList, parent);
		setName(name);
		setCastInstruction(true);
	}

	/**
	 * There are several places where we need to know if a cast instruction 
	 * only deals with integer source and destination types. To simplify that 
	 * logic, this method is provided.
	 * Returns true if the cast has only integral typed operand and dest type.
	 * Determines if this is an integer-only cast.
	 * @return
	 */
	public boolean isIntegerCast(){
		InstructionID id = getInstructionID();
		switch (id) {
		case ZEXT_INST :
		case SEXT_INST :
		case TRUNC_INST :
			return true;
		case BIT_CAST_INST :
			return getOperand(0).getType().isIntegerType() &&
					getType().isIntegerType();

		default: return false;
		}
	}

	public Type getSrcType()  { return getOperand(0).getType(); }

	public Type getDestType()  { return getType(); }


	/**
	 * A lossless cast is one that does not alter the basic value. It implies 
	 * a no-op cast but is more stringent, preventing things like int.float, long.double, or int.ptr.
	 * Returns true iff the cast is lossless.
	 * Determines if this is a lossless cast.
	 */
	public boolean isLosslessCast(){
		// Only BitCast can be lossless, exit right away if we're not BitCast
		InstructionID opCode = getInstructionID();

		if (opCode != InstructionID.BIT_CAST_INST)
			return false;

		// Identity cast is always lossless
		Type srcType = getOperand(0).getType();
		Type destType = getType();
		if (srcType == destType)
			return true;

		// Pointer to pointer is always lossless.
		if (srcType.isPointerType())
			return destType.isPointerType();

		return false;  // Other types have no identity values
	}

	public static CastInst create(InstructionID instID, String name, Type targetType, 
			Value sourceValue, BasicBlock parent) throws InstructionCreationException {

		// Check if the instruction ID passed is valid
		if(instID.ordinal() < InstructionID.TRUNC_INST.ordinal() || 
				instID.ordinal() > InstructionID.BIT_CAST_INST.ordinal()){
			throw new InstructionCreationException(InstructionCreationException.INVALID_INST_ID_FOR_CAST);
		}

		// Check if the cast is valid
		if(!castIsValid(instID, sourceValue, targetType)){
			throw new InstructionCreationException(InstructionCreationException.CANNOT_CAST, 
					sourceValue.getType().toString(), InstructionCreationException.TO, targetType.toString());
		}

		// Valid input parameters, lets create a new instance of the CastInst and return it
		List<Value> operandList = new ArrayList<Value>();
		operandList.add(sourceValue); 

		return new CastInst(instID, name, targetType, operandList , parent);

	}

	/**
	 *  This method can be used to determine if a cast from S to DstTy using
	 *  Opcode op is valid or not. Returns true if the proposed cast is valid.
	 * @param op
	 * @param sourceValue
	 * @param destType
	 * @return
	 */

	public static boolean castIsValid(InstructionID op, Value sourceValue, Type destType){
		// Check for type sanity on the arguments
		Type sourceType = sourceValue.getType();
		if (!sourceType.isFirstClassType() || !destType.isFirstClassType() ||
				sourceType.isAggregateType() || destType.isAggregateType())
			return false;

		// Get the size of the types in bits, we'll need this later
		int SrcBitSize = sourceType.getScalarSizeInBits();
		int DstBitSize = destType.getScalarSizeInBits();

		// If these are vector types, get the lengths of the vectors (using zero for
		// scalar types means that checking that vector lengths match also checks that
		// scalars are not being converted to vectors or vectors to scalars).
		int SrcLength = sourceType.isVectorType() ? ((VectorType)sourceType).getNumElements() : 0;
		int DstLength = destType.isVectorType() ? ((VectorType)destType).getNumElements() : 0;

		// Switch on the opcode provided
		switch (op) {
		default: 
			return false; // This is an input error
		case TRUNC_INST:
			return sourceType.isIntOrIntVectorType() && destType.isIntOrIntVectorType() &&
					SrcLength == DstLength && SrcBitSize > DstBitSize;
		case ZEXT_INST:
			return sourceType.isIntOrIntVectorType() && destType.isIntOrIntVectorType() &&
					SrcLength == DstLength && SrcBitSize < DstBitSize;
		case SEXT_INST: 
			return sourceType.isIntOrIntVectorType() && destType.isIntOrIntVectorType() &&
					SrcLength == DstLength && SrcBitSize < DstBitSize;
		case FP_TRUNC_INST:
			return sourceType.isFPOrFPVectorType() && destType.isFPOrFPVectorType() &&
					SrcLength == DstLength && SrcBitSize > DstBitSize;
		case FP_EXT_INST:
			return sourceType.isFPOrFPVectorType() && destType.isFPOrFPVectorType() &&
					SrcLength == DstLength && SrcBitSize < DstBitSize;
		case UI_TO_FP_INST:
		case SI_TO_FP_INST:
			return sourceType.isIntOrIntVectorType() && destType.isFPOrFPVectorType() &&
					SrcLength == DstLength;
		case FP_TO_UI_INST:
		case FP_TO_SI_INST:
			return sourceType.isFPOrFPVectorType() && destType.isIntOrIntVectorType() &&
					SrcLength == DstLength;
		case PTR_TO_INT_INST:
			return sourceType.isPointerType() && destType.isIntegerType();
		case INT_TO_PTR_INST:
			return sourceType.isIntegerType() && destType.isPointerType();
		case BIT_CAST_INST:
			// BitCast implies a no-op cast of type only. No bits change.
			// However, you can't cast pointers to anything but pointers.
			if (sourceType.isPointerType() != destType.isPointerType())
				return false;

			// Now we know we're not dealing with a pointer/non-pointer mismatch. In all
			// these cases, the cast is okay if the source and destination bit widths
			// are identical.
			return sourceType.getPrimitiveSizeInBits() == destType.getPrimitiveSizeInBits();
		}
	}

	/**
	 * Create a ZExt or BitCast cast instruction
	 */
	public static CastInst createZExtOrBitCast(Value sourceValue, Type targetType, 
			String name , BasicBlock parent) throws InstructionCreationException {
		if (sourceValue.getType().getScalarSizeInBits() == targetType.getScalarSizeInBits())
			return CastInst.create(InstructionID.BIT_CAST_INST, name, targetType, sourceValue, parent);

		return CastInst.create(InstructionID.ZEXT_INST, name, targetType, sourceValue, parent);
	}

	/**
	 * Create a sitofp or BitCast cast instruction
	 */
	public static CastInst createSitofpOrBitCast(Value sourceValue, Type targetType, 
			String name, BasicBlock parent) throws InstructionCreationException {
		return CastInst.create(InstructionID.SI_TO_FP_INST, name, targetType, sourceValue, parent);
	}

	/**
	 * Create a fptosi cast instruction
	 */
	public static CastInst createFpToSi(Value sourceValue, Type targetType, 
			String name, BasicBlock parent) throws InstructionCreationException {
		return CastInst.create(InstructionID.FP_TO_SI_INST, name, targetType, sourceValue, parent);
	}

	/**
	 * Create a SExt or BitCast cast instruction
	 */
	public static CastInst createSExtOrBitCast(Value sourceValue, Type targetType, 
			String name, BasicBlock parent) throws InstructionCreationException {
		if (sourceValue.getType().getScalarSizeInBits() == targetType.getScalarSizeInBits())
			if (sourceValue.getType().getScalarSizeInBits() == targetType.getScalarSizeInBits())
				return CastInst.create(InstructionID.BIT_CAST_INST, name, targetType, sourceValue, parent);

		return CastInst.create(InstructionID.SEXT_INST, name, targetType, sourceValue, parent);
	}

	/**
	 * Create a Trunc or BitCast cast instruction
	 */
	public static CastInst createTruncOrBitCast(Value sourceValue, Type targetType, 
			String name, BasicBlock parent) throws InstructionCreationException {
		if (sourceValue.getType().getScalarSizeInBits() == targetType.getScalarSizeInBits())
			return CastInst.create(InstructionID.BIT_CAST_INST, name, targetType, sourceValue, parent);

		return CastInst.create(InstructionID.TRUNC_INST, name, targetType, sourceValue, parent);
	}

	/**
	 * Create a BitCast or a PtrToInt cast instruction
	 */
	public static CastInst createPointerCast(Value sourceValue, Type targetType, 
			String name, BasicBlock parent) throws InstructionCreationException {
		if(!sourceValue.getType().isPointerType()){
			throw new InstructionCreationException(InstructionCreationException.CANNOT_CAST, 
					sourceValue.getType().toString(), InstructionCreationException.TO, targetType.toString());
		}  

		if(!(targetType.isIntegerType() || targetType.isPointerType())){
			throw new InstructionCreationException(InstructionCreationException.CANNOT_CAST, 
					sourceValue.getType().toString(), InstructionCreationException.TO, targetType.toString());
		}

		if (targetType.isIntegerType())
			return CastInst.create(InstructionID.PTR_TO_INT_INST, name, targetType, sourceValue, parent);

		return CastInst.create(InstructionID.BIT_CAST_INST, name, targetType, sourceValue, parent);
	}

	/**
	 * Create a IntToPtr cast instruction
	 */
	public static CastInst createIntToPointerCast(Value sourceValue, Type targetType, 
			String name, BasicBlock parent) throws InstructionCreationException {
		if(!sourceValue.getType().isIntegerType()){
			throw new InstructionCreationException(InstructionCreationException.CANNOT_CAST, 
					sourceValue.getType().toString(), InstructionCreationException.TO, targetType.toString());
		}  

		if(!(targetType.isPointerType())){
			throw new InstructionCreationException(InstructionCreationException.CANNOT_CAST, 
					sourceValue.getType().toString(), InstructionCreationException.TO, targetType.toString());
		}

		return CastInst.create(InstructionID.INT_TO_PTR_INST, name, targetType, sourceValue, parent);
	}

	/**
	 * Create a ZExt, BitCast, or Trunc for int to int casts.
	 */
	public static CastInst createIntegerCast(Value sourceValue, Type targetType, 
			String name, boolean isSigned, BasicBlock parent) throws InstructionCreationException {
		if(!(sourceValue.getType().isIntOrIntVectorType() && targetType.isIntOrIntVectorType())){
			throw new InstructionCreationException(InstructionCreationException.INVALID_INTEGER_CAST);
		}

		int sourceBits = sourceValue.getType().getScalarSizeInBits();
		int destBits = targetType.getScalarSizeInBits();
		InstructionID opcode =
				(sourceBits == destBits ? InstructionID.BIT_CAST_INST :
					(sourceBits > destBits ? InstructionID.TRUNC_INST :
						(isSigned ? InstructionID.SEXT_INST : InstructionID.ZEXT_INST)));

		return CastInst.create(opcode, name, targetType, sourceValue, parent);
	}

	/**
	 * Create an FPExt, BitCast, or FPTrunc for fp to fp casts
	 */
	public static CastInst createFPCast(Value sourceValue, Type targetType, 
			String name, BasicBlock parent) throws InstructionCreationException {
		if(!(sourceValue.getType().isFPOrFPVectorType() && targetType.isFPOrFPVectorType())){
			throw new InstructionCreationException(InstructionCreationException.CANNOT_CAST, 
					sourceValue.getType().toString(), InstructionCreationException.TO, targetType.toString());
		}

		int srcBits = sourceValue.getType().getScalarSizeInBits();
		int destBits = targetType.getScalarSizeInBits();
		InstructionID opcode =
				(srcBits == destBits ? InstructionID.BIT_CAST_INST :
					(srcBits > destBits ? InstructionID.FP_TRUNC_INST : InstructionID.FP_EXT_INST));
		return CastInst.create(opcode, name, targetType, sourceValue, parent);
	}


	/**
	 * Check whether it is valid to call getCastOpcode for these types.
	 * This function must be kept in sync with getCastOpcode().
	 */

	public static boolean isCastable(
			Type srcType, ///< The Type from which the value should be cast.
			Type destType ///< The Type to which the value should be cast.
			){
		if (!srcType.isFirstClassType() || !destType.isFirstClassType())
			return false;

		if (srcType == destType)
			return true;

		VectorType srcVecType = null;
		VectorType destVecType = null;
		if(srcType.isVectorType() && destType.isVectorType()){
			srcVecType = (VectorType)srcType;
			destVecType = (VectorType)destType;

			if(srcVecType.getNumElements() == destVecType.getNumElements()){
				// An element by element cast.  Valid if casting the elements is valid.
				srcType = srcVecType.getContainedType();
				destType = destVecType.getContainedType();
			}

		}

		// Get the bit sizes, we'll need these
		int SrcBits = srcType.getPrimitiveSizeInBits();   // 0 for ptr
		int DestBits = destType.getPrimitiveSizeInBits(); // 0 for ptr

		// Run through the possibilities ...
		if (destType.isIntegerType()) {               // Casting to integral
			if (srcType.isIntegerType()) {                // Casting from integral
				return true;
			} 
			else if (srcType.isFloatingPointType()) {   // Casting from floating pt
				return true;
			} 
			else if (srcType.isVectorType()) {          // Casting from vector
				return DestBits == SrcBits;
			} 
			else {                                   // Casting from something else
				return srcType.isPointerType();
			}
		} 
		else if (destType.isFloatingPointType()) {  // Casting to floating pt
			if (srcType.isIntegerType()) {                // Casting from integral
				return true;
			} 
			else if (srcType.isFloatingPointType()) {   // Casting from floating pt
				return true;
			} 
			else if (srcType.isVectorType()) {          // Casting from vector
				return DestBits == SrcBits;
			} 
			else {                                   // Casting from something else
				return false;
			}
		}
		else if (destType.isVectorType()) {         // Casting to vector
			return DestBits == SrcBits;
		} 
		else if (destType.isPointerType()) {        // Casting to pointer
			if (srcType.isPointerType()) {                // Casting from pointer
				return true;
			} 
			else if (srcType.isIntegerType()) {         // Casting from integral
				return true;
			} 
			else {                                   // Casting from something else
				return false;
			}
		} 
		else if (destType.isX86_MMXType()) {
			if (srcType.isVectorType()) {
				return DestBits == SrcBits;       // 64-bit vector to MMX
			} 
			else {
				return false;
			}
		}
		else {                                   // Casting to something else
			return false;
		}
	}


	/**
	 * Returns the opcode necessary to cast Val into Ty using usual casting rules.
	 * @param srcValue
	 * @param srcIsSigned
	 * @param destType
	 * @param destIsSigned
	 * @return
	 * @throws InstructionDetailAccessException
	 */
	public static InstructionID getCastOpcode(
			Value srcValue, ///< The value to cast
			boolean srcIsSigned, ///< Whether to treat the source as signed
			Type destType,   ///< The Type to which the value should be casted
			boolean destIsSigned  ///< Whether to treate the dest. as signed
			)  throws InstructionDetailAccessException {
		Type srcType = srcValue.getType();

		if(!(srcType.isFirstClassType() && destType.isFirstClassType())){
			throw new InstructionDetailAccessException(InstructionDetailAccessException.ONLY_FIRST_CLASS_TYPES_CASTABLE);
		}

		if (srcType == destType)
			return InstructionID.BIT_CAST_INST;

		VectorType srcVecType = null;
		VectorType destVecType = null;
		if(srcType.isVectorType() && destType.isVectorType()){
			srcVecType = (VectorType)srcType;
			destVecType = (VectorType)destType;

			if(srcVecType.getNumElements() == destVecType.getNumElements()){
				// An element by element cast.  Valid if casting the elements is valid.
				srcType = srcVecType.getContainedType();
				destType = destVecType.getContainedType();
			}
		}

		// Get the bit sizes
		int srcBits = srcType.getPrimitiveSizeInBits();   // 0 for ptr
		int destBits = destType.getPrimitiveSizeInBits(); // 0 for ptr

		// Run through the possibilities ...
		if (destType.isIntegerType()) {                      // Casting to integral
			if (srcType.isIntegerType()) {                   // Casting from integral
				if (destBits < srcBits)
					return InstructionID.TRUNC_INST;         // int . smaller int
				else if (destBits > srcBits) {               // its an extension
					if (srcIsSigned)
						return InstructionID.SEXT_INST;      // signed . SEXT
					else
						return InstructionID.ZEXT_INST;      // int . ZEXT
				}
				else {
					return InstructionID.BIT_CAST_INST;      // Same size, No-op cast
				}
			}
			else if (srcType.isFloatingPointType()) {      // Casting from floating pt
				if (destIsSigned) 
					return InstructionID.FP_TO_SI_INST;      // FP . sint
				else
					return InstructionID.FP_TO_UI_INST;      // FP . uint 
			}
			else if (srcType.isVectorType()) {
				if(destBits != srcBits){
					throw new 
					InstructionDetailAccessException(
							InstructionDetailAccessException.CANNOT_CAST_VEC_TO_INT_OF_DIFF_WIDTH);
				}

				return InstructionID.BIT_CAST_INST;          // Same size, no-op cast

			}
			else {
				// Must be a source must be a pointer type
				return InstructionID.PTR_TO_INT_INST;        // ptr to int
			}
		}
		else if (destType.isFloatingPointType()) {         // Casting to floating pt
			if (srcType.isIntegerType()) {                     // Casting from integral
				if (srcIsSigned)
					return InstructionID.SI_TO_FP_INST;                          // sint . FP
				else
					return InstructionID.UI_TO_FP_INST;                           // uint . FP
			} 
			else if (srcType.isFloatingPointType()) {        // Casting from floating pt
				if (destBits < srcBits) {
					return InstructionID.FP_TRUNC_INST;                         // FP . smaller FP
				} 
				else if (destBits > srcBits) {
					return InstructionID.FP_EXT_INST;                            // FP . larger FP
				}
				else  {
					return InstructionID.BIT_CAST_INST;                             // same size, no-op cast
				}
			} else if (srcType.isVectorType()) {
				if(destBits != srcBits){
					throw new 
					InstructionDetailAccessException(
							InstructionDetailAccessException.CANNOT_CAST_VEC_TO_FP_OF_DIFF_WIDTH);
				}

				return InstructionID.BIT_CAST_INST;                             // same size, no-op cast
			} 
			else {
				throw new 
				InstructionDetailAccessException(
						InstructionDetailAccessException.CANNOT_CAST_PTR_OR_NON_FIRST_CLASS_TO_FP);
			}
		}
		else if (destType.isVectorType()) {
			if(destBits != srcBits){
				throw new 
				InstructionDetailAccessException(
						InstructionDetailAccessException.CANNOT_CAST_VEC_TO_FP_OF_DIFF_WIDTH);
			}
			return InstructionID.BIT_CAST_INST;
		}
		else if (destType.isPointerType()) {
			if (srcType.isPointerType()) {
				return InstructionID.BIT_CAST_INST;                               // ptr . ptr
			}
			else if (srcType.isIntegerType()) {
				return InstructionID.INT_TO_PTR_INST;                             // int . ptr
			}
			else {
				throw new 
				InstructionDetailAccessException(
						InstructionDetailAccessException.CANNOT_CAST_PTR_TO_OTHER_THAN_PTR_OR_INT);
			}
		} 
		else if (destType.isX86_MMXType()) {
			if (srcType.isVectorType()) {
				if(destBits != srcBits){

					throw new 
					InstructionDetailAccessException(
							InstructionDetailAccessException.CANNOT_CAST_WRONG_WIDTH_VEC_X86_MMX);
				}

				return InstructionID.BIT_CAST_INST;                               // 64-bit vector to MMX
			} 
			else {
				throw new 
				InstructionDetailAccessException(
						InstructionDetailAccessException.INVALID_CAST_TO_X866_MMX);
			}
		}
		else {
			// Should not happen: Casting to type that is not first-class");
		}

		// If we fall through to here we probably hit an assertion cast above
		// and assertions are not turned on. Anything we return is an error, so
		// BitCast is as good a choice as any.
		return InstructionID.BIT_CAST_INST;
	}

	@Override
	public String emit(){
		InstructionID id = getInstructionID();
		String opDesc = "";
		switch(id){
		case TRUNC_INST:      opDesc = TRUNC; break;
		case ZEXT_INST:       opDesc = ZEXT; break;
		case SEXT_INST:       opDesc = SEXT; break;
		case FP_TRUNC_INST:   opDesc = FPTRUNC; break;
		case FP_EXT_INST :    opDesc = FPEXT; break;
		case UI_TO_FP_INST:   opDesc = UITOFP; break;
		case SI_TO_FP_INST:   opDesc = SITOFP; break;
		case FP_TO_UI_INST:   opDesc = FPTOUI; break;
		case FP_TO_SI_INST:   opDesc = FPTOSI; break;
		case INT_TO_PTR_INST: opDesc = INTTOPTR; break;
		case PTR_TO_INT_INST: opDesc = PTRTOINT; break;
		case  BIT_CAST_INST:  opDesc = BITCAST; break;
		}

		String description = "";
		Value srcValue = getOperand(0);
		String name = LLVMIREmitter.getInstance().getValidName(this);
		String srcName = LLVMIREmitter.getInstance().getValidName(srcValue);

		description += (name == null || name.length() == 0)? getType().toString() : name +  " =";
		description += " " + opDesc;
		description += (name == null || name.length() == 0) ? " (" : " ";
		
		if(srcValue instanceof ConstantExpr)
			description += srcName + " to " + getType().toString();
		else
			description += srcValue.getType() + " " + srcName + " to " + getType().toString();
		
		description += (name == null || name.length() == 0) ? ")" : "";

		return description;
	}

	@Override
	public boolean isCastInstruction() {
		return true;
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
	public LatticeValue visitForSCCP(LatticeValue latticeValueBeforeModelling,
			HashSet<BasicBlock> unreachableNodes,
			HashMap<Value, LatticeValue> tempVsLatticeValue,
			Vector<Value> ssaWorkList,
			Vector<BasicBlock> cfgWorkList) {

		LatticeValue newLatticeValue = null;
		Value srcValue = getOperand(0);
		LatticeValue latticeValueOfOperand = tempVsLatticeValue.get(srcValue);
		// Inherit overdefinedness of operand
		if (latticeValueOfOperand.getType() == LatticeValue.OVERDEFINED){
			newLatticeValue = new LatticeValue();
			newLatticeValue.setType(LatticeValue.OVERDEFINED);
		}
		else if (latticeValueOfOperand.getType()
				== LatticeValue.CONSTANT)   {    
			// Propagate constant value
			Constant castConst = Constant.getCast(
					latticeValueOfOperand.getConstantValue(), getType());

			newLatticeValue = new LatticeValue();
			newLatticeValue.setConstantValue(castConst);

		}
		return newLatticeValue;
	}

	@Override
	public boolean canBeHoistedOrSank() {
		return true;
	}

	@Override
	public Constant foldIfPossible() {
		Value operand = getOperand(0);
		if(!operand.isAConstant()){
			return null;
		}

		return Constant.getCast((Constant) operand, getType());
	}
}

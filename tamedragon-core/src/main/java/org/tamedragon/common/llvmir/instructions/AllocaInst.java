package org.tamedragon.common.llvmir.instructions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tamedragon.common.LatticeValue;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionCreationException;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionUpdateException;
import org.tamedragon.common.llvmir.types.ArrayType;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.Constant;
import org.tamedragon.common.llvmir.types.PointerType;
import org.tamedragon.common.llvmir.types.StructType;
import org.tamedragon.common.llvmir.types.Type;
import org.tamedragon.common.llvmir.types.Value;
import org.tamedragon.common.llvmir.types.exceptions.TypeCreationException;
import org.tamedragon.common.llvmir.utils.LLVMIREmitter;

/**
 * This class represents the LLVM alloca instruction
 */

public class AllocaInst extends UnaryInstruction {

	private Properties properties;
	
	private Integer align;

	private static final Logger LOG = LoggerFactory.getLogger(AllocaInst.class);

	private static final String INVALID_POINTER_TYPE_FOR_ALLOCA = "Cannot create alloca due to invalid pointer type.";
	protected AllocaInst(Properties properties, Type type, String name,
			Integer align, List<Value> operandList, BasicBlock parent) {
		super(InstructionID.ALLOCA_INST, type, operandList, parent);
		this.align = align;
		this.properties = properties;
		setName(name);
	}

	public AllocaInst(Properties properties, Type type, String name, Value arraySize, BasicBlock parent) {
		super(InstructionID.ALLOCA_INST, type, null, parent);
		this.properties = properties;
		setName(name);
	}

	public boolean isVariableLengthArrayAllocation() {
		if (getNumOperands() == 0 || getOperand(0) == null){
			return false;
		}
		else {
			return true;
		}
	}

	public Value getArraySize() {
		return getOperand(0);
	}

	public Type getType() {
		return type;
	}

	public long getAlignment() {
		return align;
	}

	public void setAlignment(Integer align, Type type) throws InstructionUpdateException {
		Integer tempAlign = align;

		align = getAlignmentForType(properties, type);

		if (tempAlign > align){
			align = tempAlign;
		}

		if (align == 1) {
			this.align = align;
			return;
		}

		if (align > MAX_ALIGN){
			throw new InstructionUpdateException(InstructionUpdateException.ALIGNMENT_CANNOT_BE_MORE_THAN_MAX_ALIGNMENT);
		}

		boolean isPowerOfTwo = LLVMUtility.checkIfNumberIsPowerOfTwo(align);
		if (!isPowerOfTwo){
			throw new InstructionUpdateException(InstructionUpdateException.ALIGNMENT_IS_NOT_A_POWER_OF_TWO);
		}

		if (align < 1){
			throw new InstructionUpdateException(
					InstructionUpdateException.ALIGNMENT_CANNOT_BE_LESS_THAN_ONE);
		}

		this.align = align;
	}

	public static AllocaInst create(Properties properties, Type resultType, String name,
			Integer align, Value arraySize, BasicBlock parent) throws InstructionCreationException {
		Integer tempAlign = align;

		if(resultType == null){
			throw new InstructionCreationException(InstructionCreationException.RESULT_TYPE_CANNOT_BE_NULL);
		}

		if (resultType.isLabelType() || resultType.isFunctionType() || resultType.isMetadataType()
				|| resultType.isVoidType()){
			throw new InstructionCreationException(
					InstructionCreationException.INVALID_TYPE_FOR_ALLOCATION);
		}

		if(!(tempAlign == null)) {
			align = getAlignmentForType(properties, resultType);

			if (tempAlign > align) {
				boolean isPowerOf2 = LLVMUtility.checkIfNumberIsPowerOfTwo(tempAlign);
				if (!isPowerOf2){
					throw new InstructionCreationException(
							InstructionCreationException.ALIGNMENT_IS_NOT_A_POWER_OF_TWO);
				}

				align = tempAlign;
			}

			if (align < 1){
				throw new InstructionCreationException(
						InstructionCreationException.ALIGNMENT_CANNOT_BE_LESS_THAN_ONE);
			}
		}

		PointerType ptrType = null;
		try {
			ptrType = Type.getPointerType(resultType.getCompilationContext(), resultType, 0);
		}
		catch (TypeCreationException e) {
			LOG.error(INVALID_POINTER_TYPE_FOR_ALLOCA);
			return null;
		}

		List<Value> operandList = new ArrayList<Value>();
		if(arraySize != null){
			operandList.add(arraySize);
		}

		AllocaInst allocaInst = new AllocaInst(properties, ptrType, name, align,
				operandList, parent);

		return allocaInst;
	}

	public static AllocaInst create(Properties properties, Type resultType, String name, Value arraySize, 
			BasicBlock parent) throws InstructionCreationException {

		List<Value> operandList = new ArrayList<Value>();

		if (resultType == null) {
			throw new InstructionCreationException(InstructionCreationException.RESULT_TYPE_CANNOT_BE_NULL);
		}

		if (resultType.isLabelType() || resultType.isFunctionType()
				|| resultType.isMetadataType() || resultType.isVoidType()) {
			throw new InstructionCreationException(
					InstructionCreationException.INVALID_TYPE_FOR_ALLOCATION);
		}

		PointerType ptrType = null;
		try {
			ptrType = Type.getPointerType(resultType.getCompilationContext(), resultType, 0);
		} catch (TypeCreationException e) {
			LOG.error(INVALID_POINTER_TYPE_FOR_ALLOCA);
			return null;
		}

		if (arraySize != null) {
			operandList.add(arraySize);
		}

		int align = getAlignmentForType(properties, resultType);

		AllocaInst allocaInst = new AllocaInst(properties, ptrType, name, align,
				operandList, parent);

		return allocaInst;
	}

	public static int getAlignmentForType(Properties properties, Type t) {

		int align = 0;
		String alignStr = null;

		if (t.isInt1Type()) {
			alignStr = properties.getProperty("i1Alignment");
		}

		else if (t.isInt8Type()) {
			alignStr = properties.getProperty("i8Alignment");
		}

		else if (t.isInt16Type()) {
			alignStr = properties.getProperty("i16Alignment");
		}

		else if (t.isHalfType()) {
			alignStr = properties.getProperty("HalfTypeAlignment");
		}

		else if (t.isInt32Type()) {
			alignStr = properties.getProperty("i32Alignment");
		}

		else if (t.isFloatType()) {
			alignStr = properties.getProperty("floatAlignment");
		}

		else if (t.isDerivedType()) {

			if (t.isPointerType()) {
				alignStr = properties.getProperty("pointerAlignment");
			}
			else if (t.isStructType()) {
				StructType structType = (StructType) t;

				if (structType.isSelfReferencial()) {
					return 4;
				}

				// For Union Type
				int nosOfMembers = structType.getElementSize();
				int maxAlignment = 0;
				for (int i = 0; i < nosOfMembers; i++) {
					Type type = structType.getTypeAtIndex(i);
					int alignment = getAlignmentForType(properties, type);
					maxAlignment = (maxAlignment > alignment) ? maxAlignment
							: alignment;
					// for Structure
					if (!structType.isUnion() && maxAlignment >= 8)
						return 8;
				}
				return maxAlignment;
			}
			else if (t.isArrayType()) {
				ArrayType arrayType = (ArrayType) t;
				Type containedType = arrayType.getContainedType();
				long alignment = getAlignmentForType(properties, containedType);
				long nosOfElements = arrayType.getNumElements();
				long alignment2 = alignment * nosOfElements;

				if (containedType.isInt8Type()
						|| containedType.isInt1Type()) {
					if (alignment2 < 16) {
						alignment = 1;
					}
					else {
						alignment = 16;
					}
				} 
				else if (containedType.isPointerType()) {
					if (alignment2 < 16){
						alignment = 8;
					}
					else{
						alignment = 16;
					}
				} 
				else if (containedType.isInt32Type()
						|| containedType.isFloatType()
						|| containedType.isInt16Type()
						|| containedType.isHalfType()) {
					if (alignment2 < 16){
						alignment = 4;
					}
					else{
						alignment = 16;
					}
				} 
				else if (containedType.isInt64Type()
						|| containedType.isDoubleType()){
					alignment = 16;
				}
				else if (containedType.isStructType()) {
					if (alignment == 1 && alignment2 < 16)
						alignment = 1;
					else if (alignment == 4 && alignment2 < 16)
						alignment = 4;
					else if (alignment == 8 && alignment2 < 16)
						alignment = 8;
					else if (alignment2 >= 16)
						alignment = 16;
				}

				return (int) alignment;
			}

			else if (t.isVectorType()) {
				// TODO
			}

			else if (t.isFunctionType()) {
				// TODO
			}
		}

		// any double, or i64 type should have an alignment of 8
		else if (t.isDoubleType())
			alignStr = properties.getProperty("doubleAlignment");

		else if (t.isInt64Type())
			alignStr = properties.getProperty("i64Alignment");

		// any double, or i64 type should have an alignment of 8
		else if (t.isFP128Type())
			alignStr = properties.getProperty("FP128Alignment");

		else if (t.isPPC_FP128Type())
			alignStr = properties.getProperty("PPC_FP128Alignment");

		else if (t.isX86_FP80Type())
			alignStr = properties.getProperty("X86_MMXAlignment");

		else if (t.isX86_MMXType())
			alignStr = properties.getProperty("X86_MMXAlignment");

		if (alignStr != null)
			align = Integer.parseInt(alignStr);


		return align;
	}

	@Override
	public String emit() {
		String description = "";
		PointerType pointerType = (PointerType)getType();
		Type containedType = pointerType.getContainedType();
		String name = LLVMIREmitter.getInstance().getValidName(this);
		if (containedType.isStructType()) {
			StructType structType = (StructType) containedType;
			description = name + " = alloca " + "%" + structType.getName();
		} 
		else{
			description = name + " = alloca " + containedType.toString();
		}

		if(isVariableLengthArrayAllocation()){
			description += ", " + getOperand(0).getType().toString() + " " + LLVMIREmitter.getInstance().getValidName(getOperand(0));
		}

		if(align != null)
			description += ", align " + getAlignment();
		return description;
	}

	@Override
	public boolean isCastInstruction() {
		return false;
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

		// This will be overdefined.
		LatticeValue latticeValueAfterModelling = new LatticeValue();
		latticeValueAfterModelling.setType(LatticeValue.OVERDEFINED);

		return latticeValueAfterModelling;
	}

	@Override
	public boolean canBeHoistedOrSank() {
		// Cannot be moved out of a loop
		return false;
	}

	@Override
	public Constant foldIfPossible() {
		// Cannot be folded
		return null;
	}
}

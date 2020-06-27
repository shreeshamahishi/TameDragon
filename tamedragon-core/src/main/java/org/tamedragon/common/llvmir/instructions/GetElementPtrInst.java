package org.tamedragon.common.llvmir.instructions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Vector;

import org.tamedragon.common.LatticeValue;
import org.tamedragon.common.Pair;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionCreationException;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionDetailAccessException;
import org.tamedragon.common.llvmir.types.ArrayType;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.CompilationContext;
import org.tamedragon.common.llvmir.types.Constant;
import org.tamedragon.common.llvmir.types.ConstantInt;
import org.tamedragon.common.llvmir.types.PointerType;
import org.tamedragon.common.llvmir.types.StructType;
import org.tamedragon.common.llvmir.types.Type;
import org.tamedragon.common.llvmir.types.Value;
import org.tamedragon.common.llvmir.types.VectorType;
import org.tamedragon.common.llvmir.types.exceptions.TypeCreationException;
import org.tamedragon.common.llvmir.utils.LLVMIREmitter;

/**
 * This class represents LLVM's GetElementPtr Instruction. An instruction for type-safe pointer arithmetic to
 * access elements of arrays and structs.
 * @author ipsg
 *
 */
public class GetElementPtrInst extends Instruction {
	private List<Pair<Value, Type>> indexVsType;
	private Boolean isInBounds = false;
	private Boolean isUnnamedAddr = false;
	private String pointeeType;
	
	private Type sourceElementType;
	private Type resultElementType;

	public GetElementPtrInst(Type type, List<Value> operandList, 
			List<Pair<Value, Type>> indexVsType, String name, String pointeeType, BasicBlock parent) {
		super(InstructionID.GET_ELEMENT_PTR, type, operandList, parent);
		this.indexVsType = indexVsType;
		this.pointeeType = pointeeType;
		setName(name);
	}

	static Type checkGEPType(Type Ty) throws InstructionCreationException {
		if (Ty == null)
			throw new InstructionCreationException(
					InstructionCreationException.TYPE_CANNOT_BE_NULL_WHILE_INSTANTIATING_GEP_INSTR);
		return Ty;
	}

	void init(Value ptr, List<ConstantInt> idxList, String name) { }

	/**
	 * Returns the type of the element that would be loaded
	 * with a load instruction with the specified parameters. Null
	 * is returned if the indices are invalid for the specified pointer
	 * type.
	 * @param Ptr
	 * @param list
	 * @return
	 * @throws InstructionDetailAccessException
	 */
	public static Type getIndexedType(Type Ptr, List<Entry<Value, Type>> list) throws InstructionDetailAccessException{
		PointerType pointerType = (PointerType) Ptr;
		if (pointerType == null)
			return null; // Type isn't a pointer type!
		Type Agg = pointerType.getContainedType();

		// Handle the special case of the empty set index set, which is always
		// valid.
		if (list.isEmpty())
			return Agg;

		// If there is at least one index, the top level type must be sized,
		// otherwise
		// it cannot be 'stepped over'.
		if (!Agg.isSized())
			return null;

		if (Agg.isPointerType())
			return null;

		if (Agg.isPrimitiveType())
			return Agg;

		int i = 0;
		if (Agg.isStructType()) {
			StructType structType = (StructType) Agg;
			for (; i < list.size(); i++) {
				Value value = list.get(i).getKey();
				if(value instanceof ConstantInt){
					ConstantInt  constantInt = (ConstantInt)value;
					int index = constantInt.getApInt().getUnsignedVals()[0].getUnsignedBigInt().intValue();
					if (!structType.isValidIndex(index))
						throw new InstructionDetailAccessException(InstructionDetailAccessException.INVALID_INDEX_FOR_GEP_INSTR);
					else
						Agg = structType.getTypeAtIndex(index);
				}
			}
		}
		else if(Agg.isArrayType()){
			ArrayType arrayType = (ArrayType)Agg;
			return arrayType.getContainedType();
		}

		if (i == list.size())
			return Agg;
		else
			return null;
	}

	public Value getPointerOperand() {
		return getOperand(0);
	}

	static Integer getPointerOperandIndex() {
		return 0; // get index for modifying correct operand
	}

	Integer getPointerAddressSpace() {
		PointerType pointerType = (PointerType) getType();
		return pointerType.getAddressSpace();
	}

	Integer getNumIndexes() {
		// Note: always non-negative return
		return getNumOperands() - 1;
	}

	boolean hasIndices() {
		return getNumOperands() > 1;
	}

	/**
	 * Return true if all of the indices of this GEP are
	 * zeros. If so, the result pointer and the first operand have the same
	 * value, just potentially different types.
	 * @return
	 */
	public boolean hasAllZeroIndices() {
		for (int i = 1; i < getNumOperands(); ++i) {
			Value value = getOperand(i);
			if (value instanceof ConstantInt) {
				ConstantInt CI = (ConstantInt) value;
				if (!CI.isZero())
					return false;
			} else {
				return false;
			}
		}
		return true;
	}

	/**
	 * Return true if all of the indices of this GEP
	 * are constant integers. If so, the result pointer and the first
	 * operand have a constant offset between them.
	 * @return
	 */
	public boolean hasAllConstantIndices() {
		for (int i = 1; i < getNumOperands(); ++i) {
			if (!(getOperand(i) instanceof ConstantInt))
				return false;
		}
		return true;
	}

	public void setIsInBounds(boolean isInBounds) {
		this.isInBounds = isInBounds;
	}

	public boolean isInBounds() {
		return isInBounds;
	}

	public Boolean getIsUnnamedAddr() {
		return isUnnamedAddr;
	}

	public void setIsUnnamedAddr(Boolean isUnnamedAddr) {
		this.isUnnamedAddr = isUnnamedAddr;
	}

	public List<Pair<Value, Type>> getIndexVsType() {
		return indexVsType;
	}

	public static GetElementPtrInst create(String name, Value pointerOperand, List<Pair<Value, Type>> indexVsType, String pointeeType, BasicBlock parent)
			throws InstructionCreationException {
		if (pointerOperand == null)
			throw new InstructionCreationException(
					InstructionCreationException.VALUE_CANNOT_BE_NULL);

		if (!pointerOperand.getType().isVectorType() && !pointerOperand.getType().isPointerType()) 
			throw new InstructionCreationException(InstructionCreationException.FIRST_VALUE_SHOULD_BE_EITHER_A_POINTER_TYPE_OR_VECTOR_TYPE);
		else if (pointerOperand.getType().isVectorType()) {
			VectorType vectorType = (VectorType) pointerOperand.getType();
			if (!vectorType.getContainedType().isPointerType())
				throw new InstructionCreationException(
						InstructionCreationException.SHOULD_BE_A_VECTOR_OF_POINTER_TYPE_ONLY);
		} 

		Type type = null;

		List<Value> operandList = new ArrayList<Value>();
		operandList.add(pointerOperand);

		for(int i = 0; i < indexVsType.size(); i++){
			Value value = indexVsType.get(i).getFirst();
			operandList.add(value);
		}
		int lastIndex = indexVsType.size()-1;
		type = indexVsType.get(lastIndex).getSecond();

		if(type == null){
			throw new InstructionCreationException(InstructionCreationException.TYPE_OF_POINTER_VALUE_RET_CANNOT_BE_NULL);
		}

		CompilationContext compilationContext = type.getCompilationContext();

		PointerType pointerType = null;
		if(lastIndex == 0 && type.isPointerType())
			pointerType = (PointerType)type;
		else{
			try {
				pointerType = Type.getPointerType(compilationContext, type, 0);
			} catch (TypeCreationException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}

		GetElementPtrInst getElementPtrInst = new GetElementPtrInst(pointerType, operandList, indexVsType, name, pointeeType, parent);

		return getElementPtrInst;
	}

	@Override
	public String emit() {
		String description = "";
		String name = LLVMIREmitter.getInstance().getValidName(this);
		if(name != null && name.length() != 0)
			description = name + " = getelementptr ";
		else
			description = getType().toString() + " getelementptr ";
		if (isInBounds())
			description += "inbounds ";
		
		description += pointeeType + ",";

		PointerType type = (PointerType)getPointerOperand().getType();

		description += (name == null || name.length() == 0) ? " (" : " ";

		description += type.toString() + " "	+ LLVMIREmitter.getInstance().getValidName(getPointerOperand());

		List<Pair<Value, Type>> list = getIndexVsType();

		for (int i = 0; i < list.size(); i++) {
			Value value = list.get(i).getFirst();
			if(value instanceof ConstantInt){
				ConstantInt constantInt = (ConstantInt)value;
				description += ", " + constantInt.toString();
			}
			else{
				description += ", " + value.getType().toString() + " " + LLVMIREmitter.getInstance().getValidName(value);
			}
		}

		description += (name == null || name.length() == 0) ? ")" : "";
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
		if (latticeValueBeforeModelling.getType() == LatticeValue.OVERDEFINED) 
			return latticeValueBeforeModelling;

		List<Constant> constOperandsList = new ArrayList<Constant>();

		int numOperands = getNumOperands();

		for (int i = 0; i <= numOperands; ++i) {
			LatticeValue operandLatticeValue =
					tempVsLatticeValue.get(getOperand(i)); 

			if (operandLatticeValue.getType() == LatticeValue.UNDEFINED)
				// Operands are not resolved yet.
				return latticeValueBeforeModelling; 

			if (operandLatticeValue.getType() == LatticeValue.UNDEFINED){
				LatticeValue newLatticeValue = new LatticeValue();
				newLatticeValue.setType(LatticeValue.OVERDEFINED);
				return newLatticeValue; 
			}

			// Must be a constant
			constOperandsList.add(operandLatticeValue.getConstantValue());
		}

		Constant constPtr = constOperandsList.get(0);
		List<Constant> constIndices = constOperandsList.subList(1, constOperandsList.size());
		Constant getElemPtrConst = Constant.getElementPtr(constPtr, constIndices);

		LatticeValue newLatticeVal = new LatticeValue();
		newLatticeVal.setConstantValue(getElemPtrConst);
		return newLatticeVal;
	}

	@Override
	public boolean canBeHoistedOrSank() {
		return true;
	}

	@Override
	public void setOperand(int i, Value newOperand) {
		super.setOperand(i, newOperand);
		if(i != 0)
			indexVsType.get(i-1).setFirst(newOperand);
	}

	@Override
	public Constant foldIfPossible() {
		// TODO Implement later
		return null;
	}

	public String getPointeeType() {
		return pointeeType;
	}

	public Type getSourceElementType() {
		return sourceElementType;
	}

	public Type getResultElementType() {
		return resultElementType;
	}
}

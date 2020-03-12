package org.tamedragon.common.llvmir.instructions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;
import java.util.Map.Entry;

import org.tamedragon.common.LatticeValue;
import org.tamedragon.common.Pair;
import org.tamedragon.common.llvmir.instructions.Instruction.InstructionID;
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

public class FNeg extends Instruction{
	
	public FNeg(Type type, List<Value> operandList, String name, BasicBlock parent) {
		super(InstructionID.UNARY_FNEG, type, operandList, parent);
		setName(name);
	}

	static Type checkNegType(Type Ty) throws InstructionCreationException {
		if (Ty == null)
			throw new InstructionCreationException(
					InstructionCreationException.TYPE_CANNOT_BE_NULL_WHILE_INSTANTIATING_FNEG);
		return Ty;
	}

	Integer getNumIndexes() {
		// Note: always non-negative return
		return getNumOperands() - 1;
	}

	boolean hasIndices() {
		return getNumOperands() > 1;
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
		/*String name = LLVMIREmitter.getInstance().getValidName(this);
		if(name != null && name.length() != 0)
			description = name + " = fneg ";
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
		*/
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
	}

	@Override
	public Constant foldIfPossible() {
		// TODO Implement later
		return null;
	}
}

package org.tamedragon.common.llvmir.instructions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;

import org.tamedragon.common.LatticeValue;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionCreationException;
import org.tamedragon.common.llvmir.types.ArrayType;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.CompositeType;
import org.tamedragon.common.llvmir.types.Constant;
import org.tamedragon.common.llvmir.types.StructType;
import org.tamedragon.common.llvmir.types.Type;
import org.tamedragon.common.llvmir.types.Value;
import org.tamedragon.common.llvmir.utils.LLVMIREmitter;

/**
 * This class represents LLVM's ExtractValue Instruction. Create a extractvalue instruction with a base aggregate
 * value and a list of indices.
 * @author ipsg
 *
 */
public class ExtractValueInst extends UnaryInstruction{
	private int idxs[];
	private Value aggr;

	protected ExtractValueInst(Type type, List<Value> operandList, String name, int idxs[], Value aggr, BasicBlock parent) {
		super(InstructionID.EXTRACT_VALUE_INST, type, operandList, parent);
		this.idxs = idxs;
		this.aggr = aggr;
		setName(name);
	}

	/**
	 * @param agg
	 * @param idxs
	 * @return Returns the type of the element that would be extracted with an extractvalue instruction with the specified parameters.
	   		   Null is returned if the indices are invalid for the specified type.
	 */
	public static Type getIndexedType(Type agg, int idxs[])throws InstructionCreationException{
		for (int curIdx = 0; curIdx < idxs.length; ++curIdx) {
			int index = idxs[curIdx];
			// We can't use CompositeType::indexValid(Index) here.
			// indexValid() always returns true for arrays because getelementptr allows
			// out-of-bounds indices. Since we don't allow those for extractvalue and
			// insertvalue we need to check array indexing manually.
			// Since the only other types we can index into are struct types it's just
			// as easy to check those manually as well.
			if (agg instanceof ArrayType) {
				ArrayType arrType = (ArrayType)agg;
				if (index >= arrType.getNumElements())
					throw new InstructionCreationException(InstructionCreationException.INVALID_INDEX_FOR_EXTRACT_VAL_INSTR);
			} else if (agg instanceof StructType) {
				StructType structType = (StructType)agg;
				if (index >= structType.getElementSize())
					throw new InstructionCreationException(InstructionCreationException.INVALID_INDEX_FOR_EXTRACT_VAL_INSTR);
			} else {
				// Not a valid type to index into.
				throw new InstructionCreationException(InstructionCreationException.EXPECTED_AGG_TYPE);
			}
			CompositeType compositeType = (CompositeType)agg;
			agg = compositeType.getTypeAtIndex(index);
		}
		return agg;
	}

	/**
	 * creating a extractValue Instruction
	 * @param agg
	 * @param idxs
	 * @param name
	 * @return ExtractValueInst
	 */
	public static ExtractValueInst create(String name, Value value,BasicBlock parent,  int...idxs) throws InstructionCreationException{
		if(value == null)
			throw new InstructionCreationException(InstructionCreationException.VALUE_CANNOT_BE_NULL);

		Type type = value.getType();
		if(!type.isAggregateType())
			throw new InstructionCreationException(InstructionCreationException.VALUE_SHOULD_BE_OF_AGGREGATE_TYPE);
		
		if(value.getName() == null || value.getName().length() == 0)
			throw new InstructionCreationException(InstructionCreationException.AGG_VALUE_NAME_CANNOT_BE_NULL_OR_EMPTY);

		if(idxs.length == 0)
			throw new InstructionCreationException(InstructionCreationException.ATLEAST_ONE_INDEX_SHOULD_BE_PROVIDED);

		Type extractedValueType = getIndexedType(type, idxs);
		List<Value> operandList = new ArrayList<Value>();
		operandList.add(value);
		return new ExtractValueInst(extractedValueType, operandList, name, idxs, value, parent);
	}

	@Override
	public String emit(){
		String resultName = LLVMIREmitter.getInstance().getValidName(this);
		String description = resultName + " = extractvalue";
		String name = LLVMIREmitter.getInstance().getValidName(aggr);
		description += " " + aggr.getType().toString() + " " + name + ",";
		for(int i = 0; i < idxs.length; i++){
			if(i < idxs.length - 1)
				description += " " + idxs[i] + ",";
			else
				description += " " + idxs[i];
		}
		return description;
	}
	
	public Value getAggregateOperand() {
		return getOperand(0);
	}

	public static int getAggregateOperandIndex() {
		return 0;                      
	}

	public int[] getIndices()  {
		return idxs;
	}

	public Integer getNumIndices() {
		return idxs.length;
	}

	public boolean hasIndices() {
		return idxs.length > 0;
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
		return null;
	}

	@Override
	public boolean canBeHoistedOrSank() {
		return false;
	}
	
	@Override
	public Constant foldIfPossible() {
		// TODO: Cannot be folded?
		return null;
	}
}

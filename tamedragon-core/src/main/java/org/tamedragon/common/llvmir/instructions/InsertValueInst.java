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

public class InsertValueInst extends Instruction{
	int idxs[];
	Value aggr;
	Value valToInsert;
	protected InsertValueInst(Type type, List<Value> operandList, String name, int idxs[], Value aggr, Value valToInsert, BasicBlock parent) {
		super(InstructionID.INSERT_VALUE_INST, type, operandList, parent);
		this.idxs = idxs;
		this.aggr = aggr;
		this.valToInsert = valToInsert;
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
					throw new InstructionCreationException(InstructionCreationException.INDEX_IS_OUT_OF_BOUNDS);
			} else if (agg instanceof StructType) {
				StructType structType = (StructType)agg;
				if (index >= structType.getElementSize())
					throw new InstructionCreationException(InstructionCreationException.INDEX_IS_OUT_OF_BOUNDS);
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
	 * public static function to create InsertValue Instructions
	 * @param aggr
	 * @param valToInsert
	 * @param idxs
	 * @param name
	 * @return InsertValueInst
	 */
	public static InsertValueInst create(Value aggr, Value valToInsert, String name, BasicBlock parent,  int...idxs) throws InstructionCreationException{
		if(aggr == null)
			throw new InstructionCreationException(InstructionCreationException.AGGREGATE_VALUE_CANNOT_BE_NULL);
		
		if(aggr.getName() == null || aggr.getName().length() == 0)
			throw new InstructionCreationException(InstructionCreationException.AGG_VALUE_MUST_HAVE_A_NAME);
		
		if(valToInsert == null)
			throw new InstructionCreationException(InstructionCreationException.VALUE_TO_BE_INSERTED_CANNOT_BE_NULL);
		
		String nameOfValToInsert = valToInsert.getName();
		if(nameOfValToInsert == null || nameOfValToInsert.length() == 0)
			throw new InstructionCreationException(InstructionCreationException.INSERTED_VALUE_TYPE_MUST_HAVE_A_VALUE_NAME);
		
		if(idxs.length == 0)
			throw new InstructionCreationException(InstructionCreationException.ATLEAST_ONE_INDEX_SHOULD_BE_PROVIDED);
		
		Type type = aggr.getType();
		
		if(!type.isAggregateType())
			throw new InstructionCreationException(InstructionCreationException.FIRST_OPERAND_TO_A_INSERTVALUE_INSTR_MUST_BE_A_AGGR_TYPE);
		
		if(type instanceof StructType){
			StructType structType = (StructType)type;
			String structTypeName = structType.getName();
			String aggregateName = aggr.getName();
			if(!structTypeName.equals(aggregateName))
				throw new InstructionCreationException(InstructionCreationException.TYPE_NAME_AND_VALUE_NAME_MUST_BE_EQUAL);
		}
		
		Type valToInsrtType = valToInsert.getType();
		
		if(!valToInsrtType.isFirstClassType())
			throw new InstructionCreationException(InstructionCreationException.SECOND_OPERAND_MUST_BE_OF_FIRST_CLASS_TYPE);
		
		Type indexedType = getIndexedType(type, idxs);
		
		if(!indexedType.equals(valToInsrtType))
			throw new InstructionCreationException(InstructionCreationException.INSERTED_VALUE_TYPE_MUST_MATCH_WITH_THE_TYPE_OF_VALUE_BEING_ASSIGNED);
		
		List<Value> operandList = new ArrayList<Value>();
		operandList.add(aggr);
		operandList.add(valToInsert);
		InsertValueInst insertValueInst = new InsertValueInst(type, operandList, name, idxs, aggr, valToInsert, parent);
		return insertValueInst;
	}
	
	@Override
	public String emit(){
		String name = LLVMIREmitter.getInstance().getValidName(this);
		String aggrName = LLVMIREmitter.getInstance().getValidName(aggr);
		String valToInsName = LLVMIREmitter.getInstance().getValidName(valToInsert);
		String description = name + " = insertvalue " + getType().toString() + " " + aggrName + ", " 
		+ valToInsert.getType().toString();
		description += (valToInsert instanceof Constant)? " " + valToInsName + ", " : " " + valToInsName + ", ";
		for(int i = 0; i < idxs.length; i++)
			description += (i < idxs.length -1)?  idxs[i] + ", " : idxs[i];
		return description;
	}

	/**
	 * 
	 * @return Aggregate Value
	 */
	public Value getAggregateOperand() {
		return getOperand(0);
	}

	/**
	 * get index for modifying correct operand
	 * @return index of aggregate value in the list of operands
	 */
	public static int getAggregateOperandIndex() {
		return 0;                     
	}

	/**
	 * 
	 * @return Inserted Value
	 */
	public Value getInsertedValueOperand() {
		return getOperand(1);
	}

	/**
	 * get index for modifying correct operand
	 * @return index of Inserted Value in the list of operands
	 */
	public static int getInsertedValueOperandIndex() {
		return 1;                      
	}

	/**
	 * 
	 * @return Array of Indexes
	 */
	public int[] getIndices() {
		return idxs;
	}

	/**
	 * 
	 * @return number of indexes
	 */
	public int getNumIndices() {
		return idxs.length;
	}

	/**
	 * 
	 * @return true if number of indexes is more than one, else false
	 */
	public boolean hasIndices() {
		return (idxs.length > 0);
	}

	@Override
	public boolean isCastInstruction() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean definesNewValue() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTerminatorInstruction() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCritical() {
		return true;
	}

	@Override
	public LatticeValue visitForSCCP(LatticeValue latticeValueBeforeModelling,
			HashSet<BasicBlock> unreachableNodes,
			HashMap<Value, LatticeValue> tempVsLatticeValue,
	        Vector<Value> ssaWorkList,
			Vector<BasicBlock> cfgWorkList) {
		//TODO : Implement this later
		return null;
	}

	@Override
	public boolean canBeHoistedOrSank() {
		return false;
	}
	
	@Override
	public Constant foldIfPossible() {
		//TODO Implement later
		return null;
	}
}
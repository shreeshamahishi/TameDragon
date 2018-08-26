package org.tamedragon.assemblyabstractions.constructs;

import java.util.List;
import java.util.Map.Entry;

import org.tamedragon.common.llvmir.types.Type;

/**
 * This class holds the information needed to create LLVM's GetElementPtr Instruction.
 * @author ipsg
 *
 */
public class IRTreeAggregateData extends IRTreeExp{
	
	private IRTree aggregateDataStruct;
	private List<Entry<IRTreeIndex, Type>> indexVsType;
	
	public IRTreeAggregateData(IRTree aggregateDataStruct, List<Entry<IRTreeIndex, Type>> indexVsType){
		this.aggregateDataStruct = aggregateDataStruct;
		this.indexVsType = indexVsType;
		this.expType = TreeNodeExpType.AGR_EXP;
	}

	public IRTree getAggregateDataStruct() {
		return aggregateDataStruct;
	}

	public void setAggregateDataStruct(IRTree aggregateDataStruct) {
		this.aggregateDataStruct = aggregateDataStruct;
	}

	public List<Entry<IRTreeIndex, Type>> getIndexVsType() {
		return indexVsType;
	}

	public void setIndexVsType(List<Entry<IRTreeIndex, Type>> indexVsType) {
		this.indexVsType = indexVsType;
	}
}

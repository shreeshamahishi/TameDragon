package org.tamedragon.common.aliasanalysis;

import java.util.List;

import org.tamedragon.common.Pair;
import org.tamedragon.common.llvmir.types.ConstantInt;
import org.tamedragon.common.llvmir.types.Value;

public class LoopIncrementedStorageLocation  extends StorageLocation{

	private StorageLocation baseAddressLocation;
	
	// For scaled location types whose base address continuously gets incremented
	private StorageLocCombinedExpression incrementedBy;
	
	private Long baseOffset; // TODO: Required?
	
	public LoopIncrementedStorageLocation(StorageLocation baseLocation, StorageLocCombinedExpression loopIncrement){
		this.type = LocationType.LOOP_INCREMENT;
		this.baseAddressLocation = baseLocation;
		this.incrementedBy = loopIncrement;
	}

	public StorageLocCombinedExpression getIncrementedBy() {
		return incrementedBy;
	}
	
	
	@Override
	public String toString(){
		String desc = "LOOP_INCR:";
		desc += baseAddressLocation.toString() + "[" + baseOffset + "+(";
		desc += loopIncrementDesc();
		desc += ")]";
		
		return desc;
	}
	
	private String loopIncrementDesc(){
		String desc = "const_incr:";
		Long constIncrement = incrementedBy.getConstValue();
		if(constIncrement == null){
			desc += "0";
		}
		else{
			desc += constIncrement;
		}
		List<Pair<ConstantInt, Value>> linearExpression = incrementedBy.getExpression();

		desc += ",linear_expr=[";
		int count = 0;
		for(Pair<ConstantInt, Value> expr : linearExpression){
			desc += expr.getFirst().getApInt().getVal() + "*" + emitter.getValidName(expr.getSecond())
			+ (count++ < linearExpression.size() -1? ", " : "");
		}

		desc += "]";

		return desc;
	}
	
	public StorageLocation getBaseAddressLocation() {
		return baseAddressLocation;
	}

	public void setBaseAddressLocation(StorageLocation baseAddressLocation) {
		this.baseAddressLocation = baseAddressLocation;
	}
}

package org.tamedragon.common.aliasanalysis;

import java.util.ArrayList;
import java.util.List;

import org.tamedragon.common.Pair;
import org.tamedragon.common.llvmir.instructions.BinaryOperator.BinaryOperatorID;
import org.tamedragon.common.llvmir.types.Constant;
import org.tamedragon.common.llvmir.types.ConstantInt;
import org.tamedragon.common.llvmir.types.Value;

public class ScaledStorageLocation extends StorageLocation {

	// For scaled location types
	private StorageLocation baseAddressLocation;
	private List<Pair<ConstantInt, Value>> linearExpression;
	private long baseOffset;
	
	public ScaledStorageLocation(StorageLocation baseLocation, List<Pair<ConstantInt, Value>> linearExpression, long baseOffset){
		type = LocationType.SCALED;

		// If the base location is itself a scaled storage location, merge it
		if(baseLocation.isScaledLocation()){
			
			ScaledStorageLocation scaledStorageLocation = (ScaledStorageLocation) baseLocation;
			
			this.baseAddressLocation = scaledStorageLocation.getBaseAddressLocation();
			this.baseOffset = baseOffset + scaledStorageLocation.getBaseOffset();

			List<Pair<ConstantInt, Value>> combinedExpressions = 
				mergeLinearExpressions(scaledStorageLocation.getLinearExpression(), linearExpression);
			this.linearExpression = combinedExpressions;
		}
		else{
			this.baseAddressLocation = baseLocation;
			this.linearExpression = linearExpression;
			this.baseOffset = baseOffset;
		}
	}
	
	private List<Pair<ConstantInt, Value>> mergeLinearExpressions(
			List<Pair<ConstantInt, Value>> linearExpression,
			List<Pair<ConstantInt, Value>> newLinearExpression) {

		List<Pair<ConstantInt, Value>> mergedLinearExpr = new ArrayList<Pair<ConstantInt,Value>>();

		List<Pair<ConstantInt, Value>> matchingExprsInNewLinearExpression = new ArrayList<Pair<ConstantInt,Value>>(); 

		for(Pair<ConstantInt, Value> expr : linearExpression){
			Value value = expr.getSecond();
			Pair<ConstantInt, Value> matchingValue = null;
			for(Pair<ConstantInt, Value> newExpr : newLinearExpression){
				if(value == newExpr.getSecond()){
					matchingValue = newExpr;
					matchingExprsInNewLinearExpression.add(newExpr);
					break;
				}
			}
			if(matchingValue != null){
				try{
					ConstantInt mergedConst = (ConstantInt)
					Constant.getConstant(BinaryOperatorID.ADD, expr.getFirst(), matchingValue.getFirst());
					Pair<ConstantInt, Value> mergedPair = new Pair<ConstantInt, Value>(mergedConst, matchingValue.getSecond());
					mergedLinearExpr.add(mergedPair);
				}
				catch(Exception e){
					// TODO Log here
					e.printStackTrace();
					System.exit(-1);
				}
			}
			else{
				mergedLinearExpr.add(expr);
			}
		}

		// Add all pairs in the new linear expression except for those for which a match was found
		for(Pair<ConstantInt, Value> newExpr : newLinearExpression){
			if(!matchingExprsInNewLinearExpression.contains(newExpr)){
				mergedLinearExpr.add(newExpr);
			}
		}

		return mergedLinearExpr;

	}
	
	public AliasResult aliasWithScaledStorageLocation(StorageLocation otherSL) {
		
		LocationType otherType = otherSL.getType();
		if(otherType != LocationType.SCALED){
			if(otherType != LocationType.LOOP_INCREMENT){
				if(!(baseOffset == 0 && linearExpression.size() == 0)){
					return AliasResult.NO_ALIAS;
				}
				else{
					return baseAddressLocation.alias(otherSL);
				}
			}
			else{
				return AliasResult.MAY_ALIAS;
			}
		}
		else{
			// The other storage location is also scaled
			ScaledStorageLocation otherScaledStorageLocation = (ScaledStorageLocation) otherSL;
			StorageLocation otherBaseAddressLocation = otherScaledStorageLocation.getBaseAddressLocation();
			List<Pair<ConstantInt, Value>> otherLinearExpression = otherScaledStorageLocation.getLinearExpression();
			long otherBaseOffset = otherScaledStorageLocation.getBaseOffset();

			// If the base addresses are not the same, we cant say anything about the addresses
			if(baseAddressLocation != otherBaseAddressLocation){
				AliasResult baseAddressAliasResult = baseAddressLocation.alias(otherBaseAddressLocation);
				if(baseAddressAliasResult != AliasResult.MUST_ALIAS){
					return baseAddressAliasResult;
				}
			}

			// Get the difference from the linear expression (if any)

			List<Pair<ConstantInt, Value>> differenceInLinearExpression = new ArrayList<Pair<ConstantInt,Value>>();
			try{
				differenceInLinearExpression = getDifference(linearExpression, otherLinearExpression);
			}
			catch(Exception e){
				// TODO Log this later
				e.printStackTrace();
				System.exit(-1);
			}

			if(baseOffset == otherBaseOffset){
				if(differenceInLinearExpression.size() == 0){
					// Lexically similar GEPs
					return AliasResult.MUST_ALIAS;
				}
				else{
					// TODO Can we handle this better?
					return AliasResult.PARTIAL_ALIAS;
				}
			}
			else{
				if(differenceInLinearExpression.size() == 0){
					// Differs with a constant offset
					return AliasResult.PARTIAL_ALIAS;
				}
				else{
					// TODO Can we handle this better?
					return AliasResult.PARTIAL_ALIAS;
				}
			}
		}
	}
	
	public StorageLocation getBaseAddressLocation() {
		return baseAddressLocation;
	}

	public void setBaseAddressLocation(StorageLocation baseAddressLocation) {
		this.baseAddressLocation = baseAddressLocation;
	}
	
	public List<Pair<ConstantInt, Value>> getLinearExpression() {
		return linearExpression;
	}

	public void setLinearExpression(List<Pair<ConstantInt, Value>> linearExpression) {
		this.linearExpression = linearExpression;
	}

	public long getBaseOffset() {
		return baseOffset;
	}

	public void setBaseOffset(long baseOffset) {
		this.baseOffset = baseOffset;
	}
	
	@Override
	public String toString(){
		String desc = "SCALED:{";
		desc += baseAddressLocation.toString() + "[" + baseOffset + "+(";
		int count = 0;
		for(Pair<ConstantInt, Value> expr: linearExpression){
			desc += expr.getFirst().getApInt() + "*" + emitter.getValidName(expr.getSecond())
			+ (count++ < linearExpression.size() -1? ", " : "");
		}
		desc += ")]}";
		
		return desc;
	}
}

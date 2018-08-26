package org.tamedragon.common.aliasanalysis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tamedragon.common.Pair;
import org.tamedragon.common.llvmir.instructions.BinaryOperator.BinaryOperatorID;
import org.tamedragon.common.llvmir.math.APInt;
import org.tamedragon.common.llvmir.semanticanalysis.LLVMErrorHandler;
import org.tamedragon.common.llvmir.types.Array;
import org.tamedragon.common.llvmir.types.Constant;
import org.tamedragon.common.llvmir.types.ConstantInt;
import org.tamedragon.common.llvmir.types.Container;
import org.tamedragon.common.llvmir.types.PointerType;
import org.tamedragon.common.llvmir.types.Value;
import org.tamedragon.common.llvmir.types.Container.ContainerTypeID;
import org.tamedragon.common.llvmir.utils.LLVMIREmitter;

/** StorageLocation represents of a memory location. It is used to denote an abstract
 * location in memory for alias analysis
 * 
 */

enum LocationType {
	AUTO ,  
	DYNAMIC,
	GLOBAL,
	ARGUMENT,
	SCALED,
	LOOP_INCREMENT,
	ANY;
} 

public class StorageLocation implements Container, Comparable<StorageLocation>{

	private long objectIndex;
	protected LocationType type;

	protected LLVMIREmitter emitter = LLVMIREmitter.getInstance();
	
	private static final Logger LOG = LoggerFactory.getLogger(StorageLocation.class);

	public StorageLocation(){

	}

	public StorageLocation(LocationType locType, long objectIndex){
		type = locType;
		this.objectIndex = objectIndex;
	}

	private Value startOfLocationPtr; // The address of the start of the location.

	/** Size - The maximum size of the location, in address-units, or
	 * UnknownSize if the size is not known.  Note that an unknown size does
	 * not mean the pointer aliases the entire virtual address space, because
	 * there are restrictions on stepping out of one object and into another.
	 * See http://llvm.org/docs/LangRef.html#pointeraliasing
	 */
	private long size;

	/// TBAATag - The metadata node which describes the TBAA type of
	/// the location, or null if there is no known unique tag.
	//const MDNode *TBAATag; // TODO TBAA?

	public StorageLocation(Value start, long size
			//const MDNode *N = 0)   // TODO : TBAA?
	) {
		this.startOfLocationPtr = start;
		this.size = size;
		// this.TBAATag = N;  //TODO : TBAA?
	}

	public StorageLocation getWithNewPtr(final Value newPtrValue) {
		StorageLocation newSL = new StorageLocation(this.getStartOfLocationPtr(), this.getSize());
		newSL.setStartOfLocationPtr(newPtrValue);
		return newSL;
	}

	public StorageLocation getWithNewSize(long newSize) {
		StorageLocation newSL = new StorageLocation(this.getStartOfLocationPtr(), this.getSize());
		newSL.setSize(newSize);
		return newSL;
	}

	public StorageLocation getWithoutTBAATag() {
		StorageLocation newSL = new StorageLocation(this.getStartOfLocationPtr(), this.getSize());

		//TODO : TBAA?
		//newSL.setTBAATag(null);
		return newSL;
	}

	public Value getStartOfLocationPtr() {
		return startOfLocationPtr;
	}

	public void setStartOfLocationPtr(Value startOfLocationPtr) {
		this.startOfLocationPtr = startOfLocationPtr;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public long getObjectIndex() {
		return objectIndex;
	}

	public void setObjectIndex(long objectIndex) {
		this.objectIndex = objectIndex;
	}

	public LocationType getType() {
		return type;
	}

	public void setType(LocationType type) {
		this.type = type;
	}

	@Override
	public String toString(){
		String desc = "";
		if(type == LocationType.AUTO){
			desc = "AUTO:"+ objectIndex;
		}
		else if(type == LocationType.ARGUMENT){
			desc = "ARGUMENT:"+ objectIndex;
		}
		else if(type == LocationType.GLOBAL){
			desc = "GLOBAL:"+ objectIndex;
		}
		else if(type == LocationType.DYNAMIC){
			desc = "DYNAMIC:"+ objectIndex;
		}
		else if(type == LocationType.SCALED){
			ScaledStorageLocation scaledStorageLocation = (ScaledStorageLocation) this;
			return scaledStorageLocation.toString();
		}
		else if(type == LocationType.LOOP_INCREMENT){
			LoopIncrementedStorageLocation loopIncrementedStorageLocation = (LoopIncrementedStorageLocation) this;
			return loopIncrementedStorageLocation.toString();
		}
		else {
			desc = "ANY";
		}
		return desc;
	}

	public boolean isScaledLocation(){
		if(type == LocationType.SCALED){
			return true;
		}
		return false;
	}

	public AliasResult alias(StorageLocation otherSL) {

		if(this == otherSL){
			return AliasResult.MUST_ALIAS;
		}

		AliasResult result = AliasResult.MAY_ALIAS;
		if(type == LocationType.AUTO){
			switch(otherSL.getType()){
			case AUTO:
			{
				if(otherSL.getObjectIndex() == objectIndex){
					result = AliasResult.MUST_ALIAS;
				}else{
					return result = AliasResult.NO_ALIAS;
				}
				break;
			}
			case DYNAMIC:
				result = AliasResult.NO_ALIAS;
				break;
			case GLOBAL:
				result = AliasResult.NO_ALIAS;
				break;
			case ARGUMENT:
				result = AliasResult.NO_ALIAS;
				break;
			case SCALED:
			{
				ScaledStorageLocation otherScaledStorageLocation = (ScaledStorageLocation) otherSL;
				result =  otherScaledStorageLocation.aliasWithScaledStorageLocation(this);
				break;
			}
			case LOOP_INCREMENT:
			{

				LoopIncrementedStorageLocation  loopIncrementedStorageLocation = (LoopIncrementedStorageLocation) otherSL;

				StorageLocation otherBaseAddress = loopIncrementedStorageLocation.getBaseAddressLocation();
				if(otherBaseAddress.getType() == LocationType.AUTO && otherBaseAddress.getObjectIndex() == objectIndex){
					result = AliasResult.MAY_ALIAS;
				}
				else{
					result = AliasResult.NO_ALIAS;
				}
			}
			case ANY:
				result = AliasResult.NO_ALIAS;
				break;
			}
		}
		else if(type == LocationType.GLOBAL){
			switch(otherSL.getType()){
			case AUTO:
			{
				result = AliasResult.NO_ALIAS;
				break;
			}
			case DYNAMIC:
				// TODO : Check this
				result = AliasResult.MAY_ALIAS;
				break;
			case GLOBAL:
				if(otherSL.getObjectIndex() == objectIndex){
					result = AliasResult.MUST_ALIAS;
				}else{
					return result = AliasResult.NO_ALIAS;
				}
				break;
			case ARGUMENT:
				result = AliasResult.MAY_ALIAS;
				break;
			case SCALED:
				ScaledStorageLocation otherScaledStorageLocation = (ScaledStorageLocation) otherSL;
				result =  otherScaledStorageLocation.aliasWithScaledStorageLocation(this);
				break;
			case LOOP_INCREMENT:
			{
				LoopIncrementedStorageLocation loopIncrementedStorageLocation = (LoopIncrementedStorageLocation) otherSL;
				StorageLocation otherBaseAddress = loopIncrementedStorageLocation.getBaseAddressLocation();
				if(otherBaseAddress.getType() == LocationType.GLOBAL && otherBaseAddress.getObjectIndex() == objectIndex){
					result = AliasResult.MAY_ALIAS;
				}
				else{
					result = AliasResult.NO_ALIAS;
				}
			}	
			case ANY:
				result = AliasResult.MAY_ALIAS;
				break;
			}
		}
		else if(type == LocationType.ARGUMENT){
			switch(otherSL.getType()){
			case AUTO:
			{
				result = AliasResult.NO_ALIAS;
				break;
			}
			case DYNAMIC:
				// TODO : Check this
				result = AliasResult.MAY_ALIAS;
				break;
			case GLOBAL:
				result = AliasResult.MAY_ALIAS;
				break;
			case ARGUMENT:
				result = AliasResult.MAY_ALIAS;
				break;
			case SCALED:
			{
				ScaledStorageLocation otherScaledStorageLocation = (ScaledStorageLocation) otherSL;
				result =  otherScaledStorageLocation.aliasWithScaledStorageLocation(this);
				break;
			}
			case LOOP_INCREMENT:
				result = AliasResult.MAY_ALIAS;
				break;
			case ANY:
				result = AliasResult.MAY_ALIAS;
				break;
			}
		}
		else if(type == LocationType.SCALED){
			switch(otherSL.getType()){
			case ANY:
				result = AliasResult.MAY_ALIAS;
				break;

			default:
			{
				ScaledStorageLocation scaledStorageLoc = (ScaledStorageLocation) this;
				result =  scaledStorageLoc.aliasWithScaledStorageLocation(otherSL);
				break;
			}
			}
		}
		else if(type == LocationType.DYNAMIC){
			// TODO Check this later
			result = AliasResult.MAY_ALIAS;
		}
		else if(type == LocationType.LOOP_INCREMENT){
			LoopIncrementedStorageLocation loopIncrementedStorageLocation = (LoopIncrementedStorageLocation) this;
			StorageLocation baseAddressLocation = loopIncrementedStorageLocation.getBaseAddressLocation();
			StorageLocCombinedExpression incrementedBy = loopIncrementedStorageLocation.getIncrementedBy();

			switch(otherSL.getType()){
			case AUTO:
			{
				if(baseAddressLocation.getType() == LocationType.AUTO && baseAddressLocation.getObjectIndex() == 
					otherSL.getObjectIndex()){
					result = AliasResult.MAY_ALIAS;
				}
				else{
					result = AliasResult.NO_ALIAS;
				}
				break;
			}
			case DYNAMIC:
				// TODO Confirm this
				result = AliasResult.MAY_ALIAS;
				break;
			case GLOBAL:
				if(baseAddressLocation.getType() == LocationType.GLOBAL && baseAddressLocation.getObjectIndex() == 
					otherSL.getObjectIndex()){
					result = AliasResult.MAY_ALIAS;
				}
				else{
					result = AliasResult.NO_ALIAS;
				}
				break;
			case ARGUMENT:
				result = AliasResult.MAY_ALIAS;
				break;
			case SCALED:
			{
				ScaledStorageLocation otherScaledStorageLocation = (ScaledStorageLocation) otherSL;
				result =  otherScaledStorageLocation.aliasWithScaledStorageLocation(this);
				break;
			}
			case LOOP_INCREMENT:
			{
				LoopIncrementedStorageLocation otherLoopIncrementedStorageLocation = (LoopIncrementedStorageLocation) this;

				StorageLocation otherBaseAddress = otherLoopIncrementedStorageLocation.getBaseAddressLocation();
				if(baseAddressLocation.alias(otherBaseAddress) != AliasResult.MUST_ALIAS){
					return AliasResult.MAY_ALIAS;
				}
				else{

					StorageLocCombinedExpression otherIncrementedBy = otherLoopIncrementedStorageLocation.getIncrementedBy();

					if(incrementedBy.equals(otherIncrementedBy)){
						return AliasResult.MUST_ALIAS;
					}
					else{
						return AliasResult.NO_ALIAS;
					}
				}
			}
			case ANY:
				result = AliasResult.MAY_ALIAS;
				break;
			}
		}
		else{  // type = LocationType.ANY
			// TODO Check this later : 
			switch(otherSL.getType()){
			case ANY:
				result = AliasResult.MAY_ALIAS;
				break;
			case AUTO:
				result = AliasResult.NO_ALIAS;
				break;
			case DYNAMIC:
				result = AliasResult.NO_ALIAS;
				break;
			case ARGUMENT:
				result = AliasResult.MAY_ALIAS;
				break;
			case LOOP_INCREMENT:
				result = AliasResult.MAY_ALIAS;
				break;

			default:
				ScaledStorageLocation otherScaledStorageLocation = (ScaledStorageLocation) otherSL;
				result =  otherScaledStorageLocation.aliasWithScaledStorageLocation(this);
			}
		}
		return result;
	}

	public static StorageLocCombinedExpression getConstantDiffBetweenAddresses(StorageLocation storageLoc,
			StorageLocation otherStorageLoc) throws Exception {

		StorageLocation baseAddress = null;
		StorageLocation otherBaseAddressLocation = null;

		List<Pair<ConstantInt, Value>> linearExpression = null;
		List<Pair<ConstantInt, Value>> otherLinearExpression = null;
		long baseOffset = 0L;
		long otherBaseOffset = 0L;


		if(storageLoc.isScaledLocation()){
			ScaledStorageLocation scaledStorageLocation = (ScaledStorageLocation) storageLoc;
			baseAddress = scaledStorageLocation.getBaseAddressLocation();
			linearExpression = scaledStorageLocation.getLinearExpression();
			baseOffset = scaledStorageLocation.getBaseOffset();
		}
		else{
			// Not a scaled address
			baseAddress = storageLoc;
		}


		if(otherStorageLoc.isScaledLocation()){
			ScaledStorageLocation otherScaledStorageLocation = (ScaledStorageLocation) otherStorageLoc;
			otherBaseAddressLocation = otherScaledStorageLocation.getBaseAddressLocation();
			otherLinearExpression = otherScaledStorageLocation.getLinearExpression();
			otherBaseOffset = otherScaledStorageLocation.getBaseOffset();
		}
		else{
			// Not a scaled address
			otherBaseAddressLocation = otherStorageLoc;
		}

		// If the base addresses are not the same, we cant say anything about the addresses
		if(baseAddress != otherBaseAddressLocation){
			AliasResult baseAddressAliasResult = baseAddress.alias(otherBaseAddressLocation);
			if(baseAddressAliasResult != AliasResult.MUST_ALIAS){
				return null;
			}
		}

		List<Pair<ConstantInt, Value>> differenceInLinearExpression = null;
		try{
			differenceInLinearExpression = getDifference(linearExpression, otherLinearExpression);
		}
		catch(Exception e){
			// TODO Log this later
			e.printStackTrace();
			System.exit(-1);
		}

		if(differenceInLinearExpression.size() != 0){
			StorageLocCombinedExpression diff = new StorageLocCombinedExpression(baseOffset -otherBaseOffset, 
					differenceInLinearExpression);

			return diff;
		}
		return null;
	}

	public static List<Pair<ConstantInt, Value>> getDifference(
			List<Pair<ConstantInt, Value>> linearExpression,
			List<Pair<ConstantInt, Value>> otherLinearExpression) throws InstantiationException, Exception {

		List<Pair<ConstantInt, Value>> difference = new ArrayList<Pair<ConstantInt,Value>>();
		if(otherLinearExpression == null || otherLinearExpression.size() == 0){
			if(linearExpression == null){
				return difference;
			}
			return linearExpression;
		}

		if(linearExpression == null || linearExpression.size() == 0){
			for(Pair<ConstantInt, Value> pair : otherLinearExpression){
				ConstantInt constInt = pair.getFirst();
				ConstantInt negValue = (ConstantInt)Constant.getConstant(BinaryOperatorID.MUL, 
						Constant.getNegativeUnityConstant(constInt), constInt);

				Pair<ConstantInt, Value> negated = new Pair<ConstantInt, Value>(negValue, pair.getSecond());
				difference.add(negated);

			}

			return difference;
		}

		// Neither is empty
		int count = 0;
		List<Integer> discardIndexInLinearExpr = new ArrayList<Integer>();
		List<Integer> discardIndexInOtherLinearExpr = new ArrayList<Integer>();

		for(Pair<ConstantInt, Value> pair : linearExpression){
			int otherCount = 0;
			for(Pair<ConstantInt, Value> otherPair : otherLinearExpression){
				if(pair.getSecond() == otherPair.getSecond()){
					ConstantInt subtractionResult = (ConstantInt)Constant.getConstant(BinaryOperatorID.SUB, 
							pair.getFirst(), otherPair.getFirst());

					if(!subtractionResult.isZero()){
						Pair<ConstantInt, Value> resultPair = new Pair<ConstantInt, Value>(subtractionResult, pair.getSecond());
						difference.add(resultPair);
					}

					discardIndexInLinearExpr.add(count);
					discardIndexInOtherLinearExpr.add(otherCount);

					break;
				}
				otherCount++;
			}
			count++;
		}

		count = 0;
		for(Pair<ConstantInt, Value> pair : linearExpression){
			if(!discardIndexInLinearExpr.contains(count)){
				difference.add(pair);
			}
			count++;
		}

		int otherCount = 0;
		for(Pair<ConstantInt, Value> otherPair : otherLinearExpression){
			if(!discardIndexInOtherLinearExpr.contains(otherCount)){
				ConstantInt constInt = otherPair.getFirst();
				ConstantInt negValue = (ConstantInt)Constant.getConstant(BinaryOperatorID.MUL, 
						Constant.getNegativeUnityConstant(constInt), constInt);
				Pair<ConstantInt, Value> newPair = new Pair<ConstantInt, Value>(negValue, otherPair.getSecond());
				difference.add(newPair);
			}
			count++;
		}

		return difference;
	}

	public static boolean addressIncrementsAreIdentical(Pair<Long, List<Pair<ConstantInt, Value>>> firstIncrement,
			Pair<Long, List<Pair<ConstantInt, Value>>> secondIncrement){
		Long baseAddressFirst = firstIncrement.getFirst();
		Long baseAddressSecond = secondIncrement.getFirst();
		if(baseAddressFirst != baseAddressSecond){
			return false;
		}

		List<Pair<ConstantInt, Value>> linearExpressionFirst = firstIncrement.getSecond(); 
		List<Pair<ConstantInt, Value>> linearExpressionSecond = secondIncrement.getSecond();
		if(linearExpressionFirst.size() != linearExpressionSecond.size()){
			return false;
		}

		for(Pair<ConstantInt, Value> pairInFirstExpr : linearExpressionFirst){
			boolean foundMatch = false;
			for(Pair<ConstantInt, Value> pairInSecondExpr : linearExpressionFirst){
				if(pairInFirstExpr.getFirst().equals(pairInSecondExpr.getFirst())
						&& pairInFirstExpr.getSecond() ==  pairInSecondExpr.getSecond()){
					foundMatch = true;
					break;
				}
			}

			if(!foundMatch){
				return false;
			}
		}

		return true;
	}

	public boolean isArg(){
		if(type == LocationType.ARGUMENT){
			return true;
		}
		else if(type == LocationType.SCALED || type == LocationType.LOOP_INCREMENT){
			if(type == LocationType.SCALED){
				ScaledStorageLocation scaledStorageLocation = (ScaledStorageLocation) this;
				if(scaledStorageLocation.getBaseAddressLocation().isArg())
					return true;
				else
					return false;
			}
			else{
				LoopIncrementedStorageLocation loopIncrementedStorageLocation = (LoopIncrementedStorageLocation) this;
				if(loopIncrementedStorageLocation.getBaseAddressLocation().isArg())
					return true;
				else
					return false;
			}
		}
		return false;
	}

	public boolean isAuto(){
		if(type == LocationType.AUTO || type == LocationType.LOOP_INCREMENT){
			return true;
		}
		else if(type == LocationType.SCALED){
			ScaledStorageLocation scaledStorageLocation = (ScaledStorageLocation) this;
			if(scaledStorageLocation.getBaseAddressLocation().isArg())
				return true;
			else
				return false;
		}
		return false;
	}

	public boolean isGlobal(){
		if(type == LocationType.GLOBAL || type == LocationType.LOOP_INCREMENT){
			return true;
		}
		else if(type == LocationType.SCALED){
			ScaledStorageLocation scaledStorageLocation = (ScaledStorageLocation) this;
			if(scaledStorageLocation.getBaseAddressLocation().isArg())
				return true;
			else
				return false;
		}
		return false;
	}

	public boolean isAny(){
		if(type == LocationType.ANY)
			return true;

		return false;
	}


	@Override
	public ContainerTypeID getContainerType() {
		return Container.ContainerTypeID.STORAGE_LOCATION;
	}

	@Override
	public boolean equals(Object obj){
		if(obj == null){
			return false;
		}

		if(!(obj instanceof StorageLocation)){
			return false;
		}

		if(this.alias((StorageLocation) obj) != AliasResult.MUST_ALIAS){
			return false;
		}

		return true;

	}

	@Override
	public int hashCode() {
		return toString().hashCode();
	}

	@Override
	public int compareTo(StorageLocation o) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static void identifyIncrementingPointers(Set<StorageLocation> storageLocations) throws Exception {

		Pair<Set<StorageLocation>, Map<StorageLocCombinedExpression, Set<List<StorageLocation>>>> 
		partitions = StorageLocation.getPartitions(storageLocations);

		Set<StorageLocation> nonAPs = partitions.getFirst();

		Map<StorageLocCombinedExpression, Set<List<StorageLocation>>> diffsAndAPs = partitions.getSecond();

		Set<StorageLocCombinedExpression> diffs = diffsAndAPs.keySet();

		// Get a map of factors and storage location pairs
		Map<Long, List<Pair<StorageLocCombinedExpression, StorageLocCombinedExpression>>> factorsAndPairs
		= getFactorAndExpressionPairs(diffs);

		//		if(factorsAndPairs == null || factorsAndPairs.size() == 0){
		//			return null;
		//		}

		// printFactorsAndPairs(factorsAndPairs);

		// Identify the increments that represent an arithmetic progression 
		Set<StorageLocCombinedExpression> increments = identifyIncrements(factorsAndPairs);

		// Get the first element of each arithmetic progression for each increment
		Map<StorageLocCombinedExpression, Set<Set<StorageLocation>>> incrementAndInitialStorageLocs = 
			getIncrementAndInitialStorageLocations(increments, diffsAndAPs);
		if(incrementAndInitialStorageLocs == null){
			//return null;
		}

		Map<StorageLocation, Set<StorageLocCombinedExpression>> baseAndIncrements = 
			getBaseAndIncrements(incrementAndInitialStorageLocs);

	}

	static Map<StorageLocation, Set<StorageLocCombinedExpression>> getBaseAndIncrements(
			Map<StorageLocCombinedExpression, Set<Set<StorageLocation>>> incrementAndPartitionedInitialElements) {
		
		if(incrementAndPartitionedInitialElements == null){
			return null;
		}

		Map<StorageLocation, Set<StorageLocCombinedExpression>> baseAndIncrements =
			new HashMap<StorageLocation, Set<StorageLocCombinedExpression>>();

		Set<StorageLocCombinedExpression> allIncrements = incrementAndPartitionedInitialElements.keySet();
		Iterator<Entry<StorageLocCombinedExpression, Set<Set<StorageLocation>>>> iterator = incrementAndPartitionedInitialElements.entrySet().iterator();
		
		while(iterator.hasNext()){
			Entry<StorageLocCombinedExpression, Set<Set<StorageLocation>>> entry = iterator.next();
			StorageLocCombinedExpression increment = entry.getKey();
			Set<Set<StorageLocation>> initialStorageLocsOfAPS = entry.getValue();

			int totalCountForOtherIncrements = 0;
			int totalSizeForIncr = 0;
			for(Set<StorageLocation> initialElementsWithCommonBase : initialStorageLocsOfAPS){
				Iterator<StorageLocation> iterator2 = initialElementsWithCommonBase.iterator();
				StorageLocation sl = iterator2.next();
				
				StorageLocation base = sl;
				if(sl.isScaledLocation()){
					base = ((ScaledStorageLocation)sl).getBaseAddressLocation();
				}
				
				int countForOtherIncrementsWithCommonBase = getCountForOtherIncrementsWithCommonBase(base, increment,
						allIncrements, initialElementsWithCommonBase);
				
				totalCountForOtherIncrements += countForOtherIncrementsWithCommonBase;
				totalSizeForIncr += initialElementsWithCommonBase.size();

				// Add the base and the increment
				Set<StorageLocCombinedExpression> incrs = baseAndIncrements.get(base);
				if(incrs == null){
					incrs = new HashSet<StorageLocCombinedExpression>();
					incrs.add(increment);
					baseAndIncrements.put(base, incrs);
				}
				else{
					incrs.add(increment);
				}
			}
			
			if(totalSizeForIncr != totalCountForOtherIncrements + 1){
				return null;
			}
		}
		
		return baseAndIncrements;
	}
	
	private static int getCountForOtherIncrementsWithCommonBase(StorageLocation base, StorageLocCombinedExpression increment,
			Set<StorageLocCombinedExpression> allIncrements,
			Set<StorageLocation> initialElementsWithCommonBase){
		
		Set<StorageLocCombinedExpression> otherIncrements = new HashSet<StorageLocCombinedExpression>();
		Iterator<StorageLocCombinedExpression> iterator = allIncrements.iterator();
		while(iterator.hasNext()){
			StorageLocCombinedExpression expr = iterator.next();
			if(expr.equals(increment)){
				continue;
			}
			otherIncrements.add(expr);
		}
		
		// Look for other increments
		
		int allElementsFromOtherIncrements = 0;
		Iterator<StorageLocCombinedExpression> iteratorForOtherIncrs = otherIncrements.iterator();
		while(iteratorForOtherIncrs.hasNext()){
			StorageLocCombinedExpression otherIncr = iteratorForOtherIncrs.next();
			
			int count = 0;
			while(true){
				ScaledStorageLocation newSL = null;
				if(count == 0){
					newSL = new ScaledStorageLocation(base, 
							otherIncr.getExpression(), otherIncr.getConstValue());
				}
				else{
					StorageLocCombinedExpression scaledExpr =  otherIncr.scaleBy(count + 1);
					newSL = new ScaledStorageLocation(base, scaledExpr.getExpression(), scaledExpr.getConstValue());
				}
				
				if(!initialElementsWithCommonBase.contains(newSL)){
					break;
				}
				
				count++;
			}
			
			allElementsFromOtherIncrements += count;
		}
		
		
		return allElementsFromOtherIncrements;
	}

	static Map<StorageLocCombinedExpression, Set<Set<StorageLocation>>> getIncrementAndInitialStorageLocations(
			Set<StorageLocCombinedExpression> increments,
			Map<StorageLocCombinedExpression, Set<List<StorageLocation>>> diffsAndAPs) {

		Map<StorageLocCombinedExpression, Set<Set<StorageLocation>>> incrementAndInitialStorageLocs = 
					new HashMap<StorageLocCombinedExpression, Set<Set<StorageLocation>>>();

		Iterator<StorageLocCombinedExpression> iterator = increments.iterator();
		while(iterator.hasNext()){
			StorageLocCombinedExpression diff = iterator.next();

			Set<List<StorageLocation>> aps = diffsAndAPs.get(diff);
			Iterator<List<StorageLocation>> iterator2 = aps.iterator();
			Set<Set<StorageLocation>> storageLocsWithCommonBaseLocs = new HashSet<Set<StorageLocation>>(); 

			while(iterator2.hasNext()){
				List<StorageLocation> ap = iterator2.next();
				StorageLocation firstElement = ap.get(0);
				StorageLocation baseOfFirstElement = firstElement;
				if(firstElement.isScaledLocation()){
					baseOfFirstElement = ((ScaledStorageLocation) firstElement).getBaseAddressLocation();
				}

				if(storageLocsWithCommonBaseLocs.isEmpty()){
					Set<StorageLocation> locsWithCommonBase = new HashSet<StorageLocation>();
					locsWithCommonBase.add(firstElement);
					storageLocsWithCommonBaseLocs.add(locsWithCommonBase);
				}
				else{
					Set<StorageLocation> matchingSetOfStorageLocs = null;
					for(Set<StorageLocation> locsWithCommonBase : storageLocsWithCommonBaseLocs){
						Iterator<StorageLocation> iter2 = locsWithCommonBase.iterator();
						// Pick any element and check against its base address
						StorageLocation sl = iter2.next();
						if(sl.isScaledLocation()){
							sl = ((ScaledStorageLocation)sl).getBaseAddressLocation();
						}

						if(sl.equals(baseOfFirstElement)){
							// Found a match
							matchingSetOfStorageLocs = locsWithCommonBase;
							break;
						}
					}
					
					if(matchingSetOfStorageLocs != null){
						matchingSetOfStorageLocs.add(firstElement);
					}
					else{
						Set<StorageLocation> newSet = new HashSet<StorageLocation>();
						newSet.add(firstElement);
						storageLocsWithCommonBaseLocs.add(newSet);
					}
				}
			}
			
			incrementAndInitialStorageLocs.put(diff, storageLocsWithCommonBaseLocs);
		}
		
		return incrementAndInitialStorageLocs;
	}

	public static Pair<Set<StorageLocation>, Map<StorageLocCombinedExpression, Set<List<StorageLocation>>>> 
	getPartitions(Set<StorageLocation> storageLocs) throws Exception {

		// Create a list of storage locations

		List<StorageLocation> storageLocsList = new ArrayList<StorageLocation>(storageLocs);

		// Sort the list
		//Collections.sort(storageLocsList);

		// Get a map of differences and storage location pairs
		Map<StorageLocCombinedExpression, List<Pair<StorageLocation, StorageLocation>>> diffsAndPairs
		= getDiffsAndPairs(storageLocsList);


		// From the map above, get a map of arithmetic progressions and corresponding increments (diffs)
		Map<StorageLocCombinedExpression, Set<List<StorageLocation>>> arithmeticProgessions = getAPs(diffsAndPairs);

		// Partition the original storage locations using the diffs found
		Set<StorageLocation> storageLocsNotInAP = new HashSet<StorageLocation>(storageLocs);

		Iterator<Entry<StorageLocCombinedExpression, Set<List<StorageLocation>>>> iterator = arithmeticProgessions.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<StorageLocCombinedExpression, Set<List<StorageLocation>>> entry = iterator.next();
			Set<List<StorageLocation>> listOfAPs = entry.getValue();
			Iterator<List<StorageLocation>> iter2 = listOfAPs.iterator();
			while(iter2.hasNext()){
				List<StorageLocation> arithmeticProgression = iter2.next();
				storageLocsNotInAP.removeAll(arithmeticProgression);
			}
		}

		return new Pair<Set<StorageLocation>, Map<StorageLocCombinedExpression, Set<List<StorageLocation>>>>
		(storageLocsNotInAP, arithmeticProgessions);
	}

	static Set<StorageLocCombinedExpression> identifyIncrements(
			Map<Long, List<Pair<StorageLocCombinedExpression, StorageLocCombinedExpression>>> factorsAndPairs) {
		Set<Long> factorsSet = factorsAndPairs.keySet();
		List<Long> factors = new ArrayList<Long>(factorsSet);
		Collections.sort(factors);

		Long firstFactor = factors.get(0);

		if(firstFactor != 2){
			return null;
		}

		Long currentFactor = firstFactor;
		Set<StorageLocCombinedExpression> initElementsOfGP =  new HashSet<StorageLocCombinedExpression>();
		for(Long factor : factors){
			if(factor == firstFactor){

				List<Pair<StorageLocCombinedExpression, StorageLocCombinedExpression>> gpsForFirstFactor
				= factorsAndPairs.get(firstFactor);
				for(Pair<StorageLocCombinedExpression, StorageLocCombinedExpression> pair : gpsForFirstFactor){
					initElementsOfGP.add(pair.getFirst());
				}

				initElementsOfGP.add(factorsAndPairs.get(factor).get(0).getFirst());

				continue;
			}

			if(factor != currentFactor + 1){
				return null;
			}

			currentFactor = factor;

			List<Pair<StorageLocCombinedExpression, StorageLocCombinedExpression>> gpsForCurrentFactor
			= factorsAndPairs.get(currentFactor);
			Set<StorageLocCombinedExpression> initElementsOfElementsInCurrentGP =  new HashSet<StorageLocCombinedExpression>();
			for(Pair<StorageLocCombinedExpression, StorageLocCombinedExpression> pair : gpsForCurrentFactor){
				initElementsOfElementsInCurrentGP.add(pair.getFirst());
			}

			if(!(initElementsOfElementsInCurrentGP.size() == initElementsOfGP.size() &&
					initElementsOfElementsInCurrentGP.containsAll(initElementsOfGP))){
				return null;
			}

		}

		return initElementsOfGP;
	}

	private static Map<StorageLocCombinedExpression, List<Pair<StorageLocation, StorageLocation>>> getDiffsAndPairs(
			List<StorageLocation> storageLocsList) throws Exception {
		Map<StorageLocCombinedExpression, List<Pair<StorageLocation, StorageLocation>>> diffsAndPairs = 
			new HashMap<StorageLocCombinedExpression, List<Pair<StorageLocation, StorageLocation>>>();

		int numElements = storageLocsList.size();
		for(int i =0; i < numElements; i++){

			//			if(i == numElements -2){
			//				break;
			//			}

			StorageLocation element = storageLocsList.get(i);

			// for(int j = i +1; j < numElements; j++){
			for(int j = 0; j < numElements; j++){
				StorageLocation next = storageLocsList.get(j);
				StorageLocCombinedExpression diff = getConstantDiffBetweenAddresses(next, element);

				if(diff == null){
					// We cannot determine the difference between these two addresses
					continue;
				}

				if(next.equals(element)){
					// No difference
					continue;
				}

				if(diff.hasNegativeElements()){
					continue;
				}

				List<Pair<StorageLocation, StorageLocation>> pairs = diffsAndPairs.get(diff);
				if(pairs != null){
					int addAfterIndex = -1;
					int count = 0;
					for(Pair<StorageLocation, StorageLocation> pair: pairs){
						if(pair.getSecond().equals(element)){
							addAfterIndex = count + 1;
							break;
						}
						count++;
					}

					if(addAfterIndex != -1){
						pairs.add(addAfterIndex, new Pair<StorageLocation, StorageLocation>(element, next));
					}
					else{
						pairs.add(new Pair<StorageLocation, StorageLocation>(element, next));
					}
				}
				else{
					pairs = new ArrayList<Pair<StorageLocation,StorageLocation>>();
					pairs.add(new Pair<StorageLocation, StorageLocation>(element, next));
					diffsAndPairs.put(diff, pairs);
				}
			}
		}

		return diffsAndPairs;
	}

	private static Map<StorageLocCombinedExpression, Set<List<StorageLocation>>> getAPs(
			Map<StorageLocCombinedExpression, List<Pair<StorageLocation, StorageLocation>>> diffsAndPairs) {

		Map<StorageLocCombinedExpression, Set<List<StorageLocation>>> result =
			new HashMap<StorageLocCombinedExpression, Set<List<StorageLocation>>>();

		Iterator<Entry<StorageLocCombinedExpression, List<Pair<StorageLocation, StorageLocation>>>> iterator 
		= diffsAndPairs.entrySet().iterator();

		while(iterator.hasNext()){
			Entry<StorageLocCombinedExpression, List<Pair<StorageLocation, StorageLocation>>> entry = iterator.next();
			StorageLocCombinedExpression diff = entry.getKey();
			List<Pair<StorageLocation, StorageLocation>> value = entry.getValue();

			Set<List<StorageLocation>> apsForDiff = identifyArithmeticProgressionInPairs(diff, value);

			if(apsForDiff == null || apsForDiff.size() == 0){
				continue;
			}

			result.put(diff, apsForDiff);
		}

		return result;
	}

	private static Set<List<StorageLocation>> identifyArithmeticProgressionInPairs(
			StorageLocCombinedExpression diff,
			List<Pair<StorageLocation, StorageLocation>> pairs) {

		Set<List<StorageLocation>> arithmeticProgressions = new HashSet<List<StorageLocation>>();

		Set<Pair<StorageLocation, StorageLocation>> cloneOfPairs = new HashSet<Pair<StorageLocation,StorageLocation>>();
		cloneOfPairs.addAll(pairs);

		Map<Pair<StorageLocation, StorageLocation>, List<StorageLocation>> pairAndAP = 
			new HashMap<Pair<StorageLocation,StorageLocation>, List<StorageLocation>>();

		Iterator<Pair<StorageLocation,StorageLocation>> iterator = cloneOfPairs.iterator();
		while(iterator.hasNext()){
			Pair<StorageLocation, StorageLocation> seed = iterator.next();

			if(pairAndAP.containsKey(seed)){
				continue;
			}

			List<StorageLocation> arithmeticProgression = findMaximalArithmeticProgressionSequence(seed, cloneOfPairs, pairAndAP);

			if(arithmeticProgression != null && arithmeticProgression.size() > 2){
				arithmeticProgressions.add(arithmeticProgression);
			}

		}


		return arithmeticProgressions;
	}

	private static List<StorageLocation> findMaximalArithmeticProgressionSequence(
			Pair<StorageLocation, StorageLocation> seed,
			Set<Pair<StorageLocation, StorageLocation>> pairs,
			Map<Pair<StorageLocation, StorageLocation>, List<StorageLocation>> pairAndAP) {

		List<StorageLocation> arithmeticProgression = new ArrayList<StorageLocation>();
		arithmeticProgression.add(seed.getFirst());
		arithmeticProgression.add(seed.getSecond());
		pairAndAP.put(seed, arithmeticProgression);

		StorageLocation first = arithmeticProgression.get(0);
		StorageLocation last = arithmeticProgression.get(arithmeticProgression.size() -1);

		Iterator<Pair<StorageLocation, StorageLocation>> iterator = pairs.iterator();

		while(iterator.hasNext()){
			Pair<StorageLocation, StorageLocation> pair = iterator.next();

			if(pair == seed){
				continue;
			}

			if(pairAndAP.get(pair) != null){
				continue;
			}

			StorageLocation pairSecond = pair.getSecond();
			StorageLocation pairFirst = pair.getFirst();
			if(first.equals(pairSecond)){
				arithmeticProgression.add(0, pair.getFirst());

				// Update the first element in the current AP
				first = pair.getFirst();

				pairAndAP.put(pair, arithmeticProgression);

				iterator = pairs.iterator();

			}

			if(last.equals(pairFirst)){
				arithmeticProgression.add(pair.getSecond());

				// Update the last element in the current AP
				last = pair.getSecond();

				pairAndAP.put(pair, arithmeticProgression);

				iterator = pairs.iterator();

			}

		}

		return arithmeticProgression;
	}


	private static Map<Long, Set<List<StorageLocCombinedExpression>>> getGPs(
			Map<Long, List<Pair<StorageLocCombinedExpression, StorageLocCombinedExpression>>> diffsAndPairs) {

		Map<Long, Set<List<StorageLocCombinedExpression>>> result =
			new HashMap<Long, Set<List<StorageLocCombinedExpression>>>();

		Iterator<Entry<Long, List<Pair<StorageLocCombinedExpression, StorageLocCombinedExpression>>>> iterator 
		= diffsAndPairs.entrySet().iterator();

		while(iterator.hasNext()){
			Entry<Long, List<Pair<StorageLocCombinedExpression, StorageLocCombinedExpression>>> entry = iterator.next();
			Long factor = entry.getKey();
			List<Pair<StorageLocCombinedExpression, StorageLocCombinedExpression>> listOfPairs = entry.getValue();

			Set<List<StorageLocCombinedExpression>> gpsForFactor = identifyGeometricProgressionInPairs(factor, listOfPairs);

			if(gpsForFactor == null || gpsForFactor.size() == 0){
				continue;
			}

			result.put(factor, gpsForFactor);
		}

		return result;
	}

	private static Set<List<StorageLocCombinedExpression>> identifyGeometricProgressionInPairs(
			Long factor,
			List<Pair<StorageLocCombinedExpression, StorageLocCombinedExpression>> pairs) {

		Set<List<StorageLocCombinedExpression>> geometricProgressions = new HashSet<List<StorageLocCombinedExpression>>();


		Map<Pair<StorageLocCombinedExpression, StorageLocCombinedExpression>, List<StorageLocCombinedExpression>> pairAndGP = 
			new HashMap<Pair<StorageLocCombinedExpression,StorageLocCombinedExpression>, List<StorageLocCombinedExpression>>();

		Iterator<Pair<StorageLocCombinedExpression,StorageLocCombinedExpression>> iterator = pairs.iterator();
		while(iterator.hasNext()){
			Pair<StorageLocCombinedExpression, StorageLocCombinedExpression> seed = iterator.next();

			if(pairAndGP.containsKey(seed)){
				continue;
			}

			List<StorageLocCombinedExpression> geometricProgression = findMaximalGeometricProgressionSequence(seed, pairs, pairAndGP);

			if(geometricProgression != null && geometricProgression.size() > 2){
				geometricProgressions.add(geometricProgression);
			}

		}


		return geometricProgressions;

	}

	private static List<StorageLocCombinedExpression> findMaximalGeometricProgressionSequence(
			Pair<StorageLocCombinedExpression, StorageLocCombinedExpression> seed,
			List<Pair<StorageLocCombinedExpression, StorageLocCombinedExpression>> pairs,
			Map<Pair<StorageLocCombinedExpression, StorageLocCombinedExpression>, List<StorageLocCombinedExpression>> pairAndGP) {

		List<StorageLocCombinedExpression> geometricProgression = new ArrayList<StorageLocCombinedExpression>();
		geometricProgression.add(seed.getFirst());
		geometricProgression.add(seed.getSecond());
		pairAndGP.put(seed, geometricProgression);

		StorageLocCombinedExpression first = geometricProgression.get(0);
		StorageLocCombinedExpression last = geometricProgression.get(geometricProgression.size() -1);

		Iterator<Pair<StorageLocCombinedExpression, StorageLocCombinedExpression>> iterator = pairs.iterator();

		while(iterator.hasNext()){
			Pair<StorageLocCombinedExpression, StorageLocCombinedExpression> pair = iterator.next();

			if(pair == seed){
				continue;
			}

			if(pairAndGP.get(pair) != null){
				continue;
			}

			StorageLocCombinedExpression pairSecond = pair.getSecond();
			StorageLocCombinedExpression pairFirst = pair.getFirst();
			if(first.equals(pairSecond)){
				geometricProgression.add(0, pair.getFirst());

				// Update the first element in the current AP
				first = pair.getFirst();

				pairAndGP.put(pair, geometricProgression);

				iterator = pairs.iterator();

			}

			if(last.equals(pairFirst)){
				geometricProgression.add(pair.getSecond());

				// Update the last element in the current AP
				last = pair.getSecond();

				pairAndGP.put(pair, geometricProgression);

				iterator = pairs.iterator();

			}

		}

		return geometricProgression;
	}

	static Map<Long, List<Pair<StorageLocCombinedExpression, StorageLocCombinedExpression>>> 
	getFactorAndExpressionPairs(Set<StorageLocCombinedExpression> expressions){

		Map<Long, List<Pair<StorageLocCombinedExpression, StorageLocCombinedExpression>>> factorAndExpressionPairs
		= new HashMap<Long, List<Pair<StorageLocCombinedExpression, StorageLocCombinedExpression>>>();

		Iterator<StorageLocCombinedExpression> iterator = expressions.iterator();
		while(iterator.hasNext()){
			StorageLocCombinedExpression currentExpr = iterator.next();

			Iterator<StorageLocCombinedExpression> iterator2 = expressions.iterator();
			while(iterator2.hasNext()){
				StorageLocCombinedExpression otherExpr = iterator2.next();

				if(otherExpr.equals(currentExpr)){
					continue;
				}

				Long factor = StorageLocCombinedExpression.getFactor(currentExpr, otherExpr);
				if(factor == null){
					continue;
				}

				if(factor <= 0){
					// Only positive factors
					continue;
				}

				StorageLocCombinedExpression firstExpr = currentExpr, secondExpr = otherExpr;
				if(currentExpr.isGreaterThan(otherExpr)){
					firstExpr = otherExpr;
					secondExpr = currentExpr;
				}

				List<Pair<StorageLocCombinedExpression, StorageLocCombinedExpression>> pairs = factorAndExpressionPairs.get(factor);
				if(pairs != null){
					int addAfterIndex = -1;
					int count = 0;
					for(Pair<StorageLocCombinedExpression, StorageLocCombinedExpression> pair: pairs){
						if(pair.getSecond().equals(currentExpr)){
							addAfterIndex = count + 1;
							break;
						}
						count++;
					}

					if(addAfterIndex != -1){
						pairs.add(addAfterIndex, 
								new Pair<StorageLocCombinedExpression, StorageLocCombinedExpression>(firstExpr, secondExpr));
					}
					else{
						pairs.add(new Pair<StorageLocCombinedExpression, StorageLocCombinedExpression>(firstExpr, secondExpr));
					}
				}
				else{
					pairs = new ArrayList<Pair<StorageLocCombinedExpression,StorageLocCombinedExpression>>();
					pairs.add(new Pair<StorageLocCombinedExpression, StorageLocCombinedExpression>(firstExpr, secondExpr));
					factorAndExpressionPairs.put(factor, pairs);
				}
			}
		}

		return factorAndExpressionPairs;
	}
}

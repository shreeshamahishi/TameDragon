package org.tamedragon.common.aliasanalysis;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.tamedragon.common.Pair;
import org.tamedragon.common.llvmir.math.APInt;
import org.tamedragon.common.llvmir.types.CompilationContext;
import org.tamedragon.common.llvmir.types.ConstantInt;
import org.tamedragon.common.llvmir.types.IntegerType;
import org.tamedragon.common.llvmir.types.Type;
import org.tamedragon.common.llvmir.types.Value;
import org.tamedragon.common.llvmir.types.Value.ValueTypeID;
import org.tamedragon.common.utils.LLVMIRUtils;

public class StorageLocationTests {
	private static final String ROOT_PATH = "TestData/AliasAnalysis/FSAnalysisTests";
	
	private static final Logger LOG = LoggerFactory.getLogger(StorageLocationTests.class);

	CompilationContext context = new CompilationContext();
	IntegerType integerType = IntegerType.getInt32Type(context, true);

	@Test
	public void runCombinations1() throws Exception {

		StorageLocation base = new StorageLocation(LocationType.ARGUMENT, 0);

		Value value = new Value(Type.getInt32Type(context, true));
		value.setValueTypeID(ValueTypeID.ARGUMENT);
		value.setName("m");

		Set<StorageLocation> storageLocations = new HashSet<StorageLocation>();
		StorageLocation s1 = createStorageLocation(base, 32, 32, value);
		StorageLocation s2 = createStorageLocation(base, 192, 64, value);
		StorageLocation s3 = createStorageLocation(base, 64, 64, value);
		StorageLocation s4 = createStorageLocation(base, 128, 64, value);
		StorageLocation s5 = createStorageLocation(base, 96, 96, value);
		StorageLocation s6 = createStorageLocation(base, 160, 96, value);
		StorageLocation s7 = createStorageLocation(base, 224, 96, value);
		StorageLocation s8 = createStorageLocation(base, 96, 32, value);
		StorageLocation s9 = createStorageLocation(base, 288, 96, value);

		storageLocations.add(base);
		storageLocations.add(s1); storageLocations.add(s2); storageLocations.add(s3);
		storageLocations.add(s4); storageLocations.add(s5); storageLocations.add(s6);
		storageLocations.add(s7); storageLocations.add(s8); storageLocations.add(s9);

		LOG.debug("INTIAL SET OF STORAGE LOCATIONS:  "  + storageLocations);

		Pair<Set<StorageLocation>, Map<StorageLocCombinedExpression, Set<List<StorageLocation>>>> 
		partitions = StorageLocation.getPartitions(storageLocations);

		Set<StorageLocation> nonAPs = partitions.getFirst();
		Map<StorageLocCombinedExpression, Set<List<StorageLocation>>> diffsAndAPs = partitions.getSecond();

		//assertTrue(nonAPs.size() == 0);


		Iterator<Entry<StorageLocCombinedExpression, Set<List<StorageLocation>>>> iterator1 = 
			diffsAndAPs.entrySet().iterator();

		while(iterator1.hasNext()){
			Entry<StorageLocCombinedExpression, Set<List<StorageLocation>>> entry = iterator1.next();
			LOG.debug("FOR DIFF = " + entry.getKey().toString() + " THE APS FOUND ARE:");

			Set<List<StorageLocation>> apSet = entry.getValue();
			Iterator<List<StorageLocation>> iter2 = apSet.iterator();
			while(iter2.hasNext()){
				LOG.debug("	SET: " + iter2.next());
			}
		}
	}

	@Test
	public void runCombinations2() throws Exception {

		StorageLocation base = new StorageLocation(LocationType.ARGUMENT, 0);

		Value value = new Value(Type.getInt32Type(context, true));
		value.setValueTypeID(ValueTypeID.ARGUMENT);
		value.setName("m");

		Set<StorageLocation> storageLocations = new HashSet<StorageLocation>();
		StorageLocation s1 = createStorageLocation(base, 32, 32, value);
		StorageLocation s2 = createStorageLocation(base, 128, 64, value);
		StorageLocation s3 = createStorageLocation(base, 128, 128, value);
		StorageLocation s4 = createStorageLocation(base, 192, 64, value);
		StorageLocation s5 = createStorageLocation(base, 64, 64, value);
		StorageLocation s6 = createStorageLocation(base, 96, 96, value);
		StorageLocation s7 = createStorageLocation(base, 160, 96, value);
		StorageLocation s8 = createStorageLocation(base, 224, 96, value);
		StorageLocation s9 = createStorageLocation(base, 384, 128, value);
		StorageLocation s10 = createStorageLocation(base, 192, 128, value);
		StorageLocation s11 = createStorageLocation(base, 96, 32, value);
		StorageLocation s12 = createStorageLocation(base, 320, 128, value);
		StorageLocation s13 = createStorageLocation(base, 256, 128, value);
		StorageLocation s14 = createStorageLocation(base, 288, 96, value);


		storageLocations.add(base);
		storageLocations.add(s1); storageLocations.add(s2); storageLocations.add(s3);
		storageLocations.add(s4); storageLocations.add(s5); storageLocations.add(s6);
		storageLocations.add(s7); storageLocations.add(s8); storageLocations.add(s9);
		storageLocations.add(s10); storageLocations.add(s11); storageLocations.add(s12);
		storageLocations.add(s13); storageLocations.add(s14); 

		LOG.debug("INTIAL SET OF STORAGE LOCATIONS:  "  + storageLocations);

		Pair<Set<StorageLocation>, Map<StorageLocCombinedExpression, Set<List<StorageLocation>>>> 
		partitions = StorageLocation.getPartitions(storageLocations);

		Set<StorageLocation> nonAPs = partitions.getFirst();
		Map<StorageLocCombinedExpression, Set<List<StorageLocation>>> diffsAndAPs = partitions.getSecond();

		//assertTrue(nonAPs.size() == 0);


		Iterator<Entry<StorageLocCombinedExpression, Set<List<StorageLocation>>>> iterator1 = 
			diffsAndAPs.entrySet().iterator();

		while(iterator1.hasNext()){
			Entry<StorageLocCombinedExpression, Set<List<StorageLocation>>> entry = iterator1.next();
			LOG.debug("FOR DIFF = " + entry.getKey().toString() + " THE APS FOUND ARE:");

			Set<List<StorageLocation>> apSet = entry.getValue();
			Iterator<List<StorageLocation>> iter2 = apSet.iterator();
			while(iter2.hasNext()){
				LOG.debug("	SET: " + iter2.next());
			}
		}
	}

	@Test
	public void runCombinations3() throws Exception {

		StorageLocation base = new StorageLocation(LocationType.ARGUMENT, 0);

		Value value = new Value(Type.getInt32Type(context, true));
		value.setValueTypeID(ValueTypeID.ARGUMENT);
		value.setName("m"); 

		Set<StorageLocation> storageLocations = new HashSet<StorageLocation>();
		StorageLocation s1 = createStorageLocation(base, 32, 32, value);
		StorageLocation s2 = createStorageLocation(base, 352, 160, value);
		StorageLocation s3 = createStorageLocation(base, 480, 160, value);
		StorageLocation s4 = createStorageLocation(base, 128, 64, value);
		StorageLocation s5 = createStorageLocation(base, 288, 160, value);
		StorageLocation s6 = createStorageLocation(base, 128, 128, value);
		StorageLocation s7 = createStorageLocation(base, 416, 160, value);
		StorageLocation s8 = createStorageLocation(base, 192, 64, value);
		StorageLocation s9 = createStorageLocation(base, 64, 64, value);
		StorageLocation s10 = createStorageLocation(base, 224, 160, value);
		StorageLocation s11 = createStorageLocation(base, 96, 96, value);
		StorageLocation s12 = createStorageLocation(base, 160, 160, value);
		StorageLocation s13 = createStorageLocation(base, 160, 96, value);
		StorageLocation s14 = createStorageLocation(base, 224, 96, value);
		StorageLocation s15 = createStorageLocation(base, 384, 128, value);
		StorageLocation s16 = createStorageLocation(base, 192, 128, value);
		StorageLocation s17 = createStorageLocation(base, 96, 32, value);
		StorageLocation s18 = createStorageLocation(base, 320, 128, value);
		StorageLocation s19 = createStorageLocation(base, 256, 128, value);
		StorageLocation s20 = createStorageLocation(base, 288, 96, value);

		storageLocations.add(base);
		storageLocations.add(s1); storageLocations.add(s2); storageLocations.add(s3);
		storageLocations.add(s4); storageLocations.add(s5); storageLocations.add(s6);
		storageLocations.add(s7); storageLocations.add(s8); storageLocations.add(s9);
		storageLocations.add(s10); storageLocations.add(s11); storageLocations.add(s12);
		storageLocations.add(s13); storageLocations.add(s14); storageLocations.add(s15);
		storageLocations.add(s16); storageLocations.add(s17); storageLocations.add(s18);
		storageLocations.add(s19); storageLocations.add(s20); 

		LOG.debug("INTIAL SET OF STORAGE LOCATIONS:  "  + storageLocations);

		Pair<Set<StorageLocation>, Map<StorageLocCombinedExpression, Set<List<StorageLocation>>>> 
		partitions = StorageLocation.getPartitions(storageLocations);

		Set<StorageLocation> nonAPs = partitions.getFirst();
		Map<StorageLocCombinedExpression, Set<List<StorageLocation>>> diffsAndAPs = partitions.getSecond();

		//assertTrue(nonAPs.size() == 0);


		Iterator<Entry<StorageLocCombinedExpression, Set<List<StorageLocation>>>> iterator1 = 
			diffsAndAPs.entrySet().iterator();

		while(iterator1.hasNext()){
			Entry<StorageLocCombinedExpression, Set<List<StorageLocation>>> entry = iterator1.next();
			LOG.debug("FOR DIFF = " + entry.getKey().toString() + " THE APS FOUND ARE:");

			Set<List<StorageLocation>> apSet = entry.getValue();
			Iterator<List<StorageLocation>> iter2 = apSet.iterator();
			while(iter2.hasNext()){
				LOG.debug("	SET: " + iter2.next());
			}
		}
	}

	@Test
	public void runCombinations4() throws Exception {

		StorageLocation base = new StorageLocation(LocationType.ARGUMENT, 0);

		Value value = new Value(Type.getInt32Type(context, true));
		value.setValueTypeID(ValueTypeID.ARGUMENT);
		value.setName("m"); 

		Set<StorageLocation> storageLocations = new HashSet<StorageLocation>();
		StorageLocation s1 = createStorageLocation(base, 128, 64, value);
		StorageLocation s2 = createStorageLocation(base, 320, 192, value);
		StorageLocation s3 = createStorageLocation(base, 288, 160, value);
		StorageLocation s4 = createStorageLocation(base, 192, 192, value);
		StorageLocation s5 = createStorageLocation(base, 576, 192, value);
		StorageLocation s6 = createStorageLocation(base, 96, 96, value);
		StorageLocation s7 = createStorageLocation(base, 160, 96, value);
		StorageLocation s8 = createStorageLocation(base, 224, 96, value);
		StorageLocation s9 = createStorageLocation(base, 384, 128, value);
		StorageLocation s10 = createStorageLocation(base, 288, 96, value);
		StorageLocation s11 = createStorageLocation(base, 320, 128, value);
		StorageLocation s12 = createStorageLocation(base, 32, 32, value);
		StorageLocation s13 = createStorageLocation(base, 352, 160, value);
		StorageLocation s14 = createStorageLocation(base, 480, 160, value);
		StorageLocation s15 = createStorageLocation(base, 128, 128, value);
		StorageLocation s16 = createStorageLocation(base, 256, 192, value);
		StorageLocation s17 = createStorageLocation(base, 416, 160, value);
		StorageLocation s18 = createStorageLocation(base, 192, 64, value);
		StorageLocation s19 = createStorageLocation(base, 64, 64, value);
		StorageLocation s20 = createStorageLocation(base, 224, 160, value);
		StorageLocation s21 = createStorageLocation(base, 512, 192, value);
		StorageLocation s22 = createStorageLocation(base, 160, 160, value);
		StorageLocation s23 = createStorageLocation(base, 192, 128, value);
		StorageLocation s24 = createStorageLocation(base, 448, 192, value);
		StorageLocation s25 = createStorageLocation(base, 96, 32, value);
		StorageLocation s26 = createStorageLocation(base, 384, 192, value);
		StorageLocation s27 = createStorageLocation(base, 256, 128, value);

		storageLocations.add(base);
		storageLocations.add(s1); storageLocations.add(s2); storageLocations.add(s3);
		storageLocations.add(s4); storageLocations.add(s5); storageLocations.add(s6);
		storageLocations.add(s7); storageLocations.add(s8); storageLocations.add(s9);
		storageLocations.add(s10); storageLocations.add(s11); storageLocations.add(s12);
		storageLocations.add(s13); storageLocations.add(s14); storageLocations.add(s15);
		storageLocations.add(s16); storageLocations.add(s17); storageLocations.add(s18);
		storageLocations.add(s19); storageLocations.add(s20); storageLocations.add(s21);
		storageLocations.add(s22); storageLocations.add(s23); storageLocations.add(s24);
		storageLocations.add(s25); storageLocations.add(s26); storageLocations.add(s27);

		LOG.debug("INTIAL SET OF STORAGE LOCATIONS:  "  + storageLocations);

		Pair<Set<StorageLocation>, Map<StorageLocCombinedExpression, Set<List<StorageLocation>>>> 
		partitions = StorageLocation.getPartitions(storageLocations);

		Set<StorageLocation> nonAPs = partitions.getFirst();
		Map<StorageLocCombinedExpression, Set<List<StorageLocation>>> diffsAndAPs = partitions.getSecond();

		printDiffAndAPs(diffsAndAPs);

		Set<StorageLocCombinedExpression> diffs = diffsAndAPs.keySet();

		// Get a map of factors and storage location pairs
		Map<Long, List<Pair<StorageLocCombinedExpression, StorageLocCombinedExpression>>> factorsAndPairs
		= StorageLocation.getFactorAndExpressionPairs(diffs);

		printFactorsAndPairs(factorsAndPairs);

		// Identify the increments that represent an arithmetic progression 
		Set<StorageLocCombinedExpression> increments = StorageLocation.identifyIncrements(factorsAndPairs);

		// Get the first element of each arithmetic progression for each increment
		Map<StorageLocCombinedExpression, Set<Set<StorageLocation>>> incrementAndInit
		= StorageLocation.getIncrementAndInitialStorageLocations(increments, diffsAndAPs);

		printIncrementAndInitLocs(incrementAndInit);

		Map<StorageLocation, Set<StorageLocCombinedExpression>> baseAndIncrements = 
			StorageLocation.getBaseAndIncrements(incrementAndInit);

		printBaseAndIncrements(baseAndIncrements);
	}

	private ScaledStorageLocation createStorageLocation(StorageLocation base, int constOffset, int factor, Value value) 
	throws InstantiationException {

		ConstantInt constInt = new ConstantInt(integerType, new APInt(integerType.getNumBits(), "" + factor, integerType.isSigned()));
		List<Pair<ConstantInt, Value>> expr1 = new ArrayList<Pair<ConstantInt,Value>>();
		expr1.add(new Pair<ConstantInt, Value>(constInt, value));
		ScaledStorageLocation sl = new ScaledStorageLocation(base, expr1, constOffset);
		return sl;
	}

	// TODO For debugging, remove later

	private void printDiffAndAPs(
			Map<StorageLocCombinedExpression, Set<List<StorageLocation>>> diffsAndAPs) {
		Iterator<Entry<StorageLocCombinedExpression, Set<List<StorageLocation>>>> iterator1 = 
			diffsAndAPs.entrySet().iterator();

		while(iterator1.hasNext()){
			Entry<StorageLocCombinedExpression, Set<List<StorageLocation>>> entry = iterator1.next();
			LOG.debug("FOR DIFF = " + entry.getKey().toString() + " THE APS FOUND ARE:");

			Set<List<StorageLocation>> apSet = entry.getValue();
			Iterator<List<StorageLocation>> iter2 = apSet.iterator();
			while(iter2.hasNext()){
				LOG.debug("	SET: " + iter2.next());
			}
		}
	}	

	private void printFactorsAndPairs(
			Map<Long, List<Pair<StorageLocCombinedExpression, StorageLocCombinedExpression>>> factorsAndPairs) {

		Iterator<Entry<Long, List<Pair<StorageLocCombinedExpression, StorageLocCombinedExpression>>>> iterator2 = 
			factorsAndPairs.entrySet().iterator();

		while(iterator2.hasNext()){
			Entry<Long, List<Pair<StorageLocCombinedExpression, StorageLocCombinedExpression>>> entry = iterator2.next();
			LOG.debug("FOR FACTOR = " + entry.getKey() + " THE GPS FOUND ARE:");

			List<Pair<StorageLocCombinedExpression, StorageLocCombinedExpression>> gpListForFactor = entry.getValue();
			for(Pair<StorageLocCombinedExpression, StorageLocCombinedExpression> pair : gpListForFactor){
				LOG.debug("	GP: (" + pair.getFirst() + ", " + pair.getSecond() + ")");
			}
		}
	}

	private void printIncrementAndInitLocs(
			Map<StorageLocCombinedExpression, Set<Set<StorageLocation>>> incrementAndInit) {

		Iterator<Entry<StorageLocCombinedExpression, Set<Set<StorageLocation>>>> iterator1 = 
			incrementAndInit.entrySet().iterator();

		while(iterator1.hasNext()){
			Entry<StorageLocCombinedExpression, Set<Set<StorageLocation>>> entry = iterator1.next();

			Set<Set<StorageLocation>> listOfSetOfStorageLocs = entry.getValue();

			String str = ("	(");
			for(Set<StorageLocation> setOfStorageLocs : listOfSetOfStorageLocs){
				Iterator<StorageLocation> sls = setOfStorageLocs.iterator();
				
				while(sls.hasNext()){
					str += sls.next() + ", ";
				}
			}
			
			LOG.debug("FOR DIFF = {} THE INIT LOCS FOUND ARE: ", entry.getKey().toString(), str);
		}
	}

	private void printBaseAndIncrements(
			Map<StorageLocation, Set<StorageLocCombinedExpression>> baseAndIncrements) {
		Iterator<Entry<StorageLocation, Set<StorageLocCombinedExpression>>> iterator1 = 
			baseAndIncrements.entrySet().iterator();

		while(iterator1.hasNext()){
			Entry<StorageLocation, Set<StorageLocCombinedExpression>> entry = iterator1.next();
			Set<StorageLocCombinedExpression> listOfExprs = entry.getValue();

			String str = "	(";
			for(StorageLocCombinedExpression expr : listOfExprs){
				str += expr.toString() + ", ";
			}
			str += ")";
			LOG.debug("FOR STORAGE LOCATION = " + entry.getKey().toString() + " THE INCREMENTS ARE:");
		}
	}
}

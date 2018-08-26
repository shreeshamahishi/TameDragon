package org.tamedragon.common.aliasanalysis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import com.compilervision.common.DepthFirstSearch;
//import com.compilervision.common.DiGraph;
//import com.compilervision.common.CFEdge;
//import com.compilervision.common.Node;
import org.tamedragon.common.Pair;
import org.tamedragon.common.controlflowanalysis.IncrementingPointerTuple;
import org.tamedragon.common.controlflowanalysis.LoopIdiomRecognize;
import org.tamedragon.common.controlflowanalysis.LoopInfo;
import org.tamedragon.common.llvmir.instructions.CFG;
import org.tamedragon.common.llvmir.instructions.CastInst;
import org.tamedragon.common.llvmir.instructions.GetElementPtrInst;
import org.tamedragon.common.llvmir.instructions.Instruction;
import org.tamedragon.common.llvmir.instructions.LoadInst;
import org.tamedragon.common.llvmir.instructions.PhiNode;
import org.tamedragon.common.llvmir.instructions.SelectInst;
import org.tamedragon.common.llvmir.instructions.StoreInst;
import org.tamedragon.common.llvmir.instructions.BinaryOperator.BinaryOperatorID;
import org.tamedragon.common.llvmir.instructions.Instruction.InstructionID;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionDetailAccessException;
import org.tamedragon.common.llvmir.types.Argument;
import org.tamedragon.common.llvmir.types.ArrayType;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.CompilationContext;
import org.tamedragon.common.llvmir.types.ConstantInt;
import org.tamedragon.common.llvmir.types.Container;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.GlobalValue;
import org.tamedragon.common.llvmir.types.GlobalVariable;
import org.tamedragon.common.llvmir.types.Module;
import org.tamedragon.common.llvmir.types.PointerType;
import org.tamedragon.common.llvmir.types.StructType;
import org.tamedragon.common.llvmir.types.Type;
import org.tamedragon.common.llvmir.types.User;
import org.tamedragon.common.llvmir.types.Value;
import org.tamedragon.common.llvmir.types.Container.ContainerTypeID;
import org.tamedragon.common.llvmir.utils.LLVMIREmitter;

import com.sun.swing.internal.plaf.basic.resources.basic;

/**
 * This class represents an alias analysis pass that is flow-sensitive. Given a location
 * in the LLVM intermediate representation, it can answer whether two objects may, must
 * 
 */
public class FSAliasAnalysis  //extends AliasAnalysis {
{

	private Map<Instruction, AliasSet> programPointAndAliasSets;

	private long numAutoAllocObjects;

	private Map<Instruction, Map<Container, Set<StorageLocation>>> programPointAndPointerInfo;

	private boolean terminateFixedPointIteration;

	private Set<GlobalVariable> globalVariables;

	private LLVMIREmitter emitter;

	private Module module;

	private Function function;

	private AliasAnalysisDFS traversal;
	
	private static final Logger LOG = LoggerFactory.getLogger(FSAliasAnalysis.class);

//	public FSAliasAnalysis(AliasAnalysis prev) {
//		super(prev);
//
//		numAutoAllocObjects = 0;
//		programPointAndPointerInfo = new HashMap<Instruction, Map<Container, Set<StorageLocation>>>();
//
//		terminateFixedPointIteration = false;
//
//		globalVariables = new HashSet<GlobalVariable>();
//
//		emitter = LLVMIREmitter.getInstance();
//	}
//
//	public void analyze(Module module, Function function){
//
//		this.module = module;
//		this.function = function;
//
//		initializePointersAndStorageLocations(function);
//
//		LOG.debug("INITIAL VALUES:");
//		print(function, "%4");
//
//		CFG graph = function.getCfg();
//
//		traversal = new AliasAnalysisDFS(this, graph);
//
//		int iteration_count = 0;
//
//		do {
//			terminateFixedPointIteration =  (Boolean)
//			traversal.execute(graph, graph.getStartNode(), programPointAndPointerInfo,
//					iteration_count);
//
//			iteration_count++;
//			LOG.debug("\nVALUES AFTER ITERATION " + iteration_count + ": ");
//			print(function, null);
//
//
//			LOG.debug("FOR %13, STORAGE LOCS SIZE AFTER ITERATION COUNT = " + iteration_count +  " is " + 
//					traversal.getStorageLocsForValueAt(2, 0, "%13").size());
//
//			if(iteration_count > 8){
//				break;
//			}
//
//		}while(!canTerminate(traversal, iteration_count));
//
//		LOG.debug("\nFINAL VALUES: ");
//		print(function, null);
//
//		// Get the Java runtime
//		Runtime runtime = Runtime.getRuntime();
//		// Run the garbage collector
//		//runtime.gc();
//		// Calculate the used memory
//		runtime.totalMemory();
//		long memory = runtime.totalMemory() - runtime.freeMemory();
//		LOG.debug("Used memory in bytes: " + memory );
//		LOG.debug("Used memory in megabytes: "
//				+ (memory / (1024L * 1024L)));
//	}
//
//	private boolean canTerminate(AliasAnalysisDFS traversal, int iter_count){
//		if(terminateFixedPointIteration){
//			return true;
//		}
//
//		// Check if all the changes are due to incrementing pointers in a loop
//		try {
//
//			Map<Instruction, Map<Container, Set<List<StorageLocation>>>>   instrsAndIncrementingPointerLocs = 
//				allChangesDueToIncrementingPointersInLoop(traversal.getInstructionAndChangingSLs(), iter_count);
//			if(instrsAndIncrementingPointerLocs != null){
//
//				// TODO Check if these values in the incrementing linear expressions are constants
//				//				boolean allValuesInIncrementingPointersAreConsts = 
//				//					allValuesInIncrementingPointersAreConsts(instrsAndIncrementingPointerLocs);
//
//				updateStorageLocationsForIncrementingPointers(instrsAndIncrementingPointerLocs, iter_count);
//				return true;
//			}
//		} 
//		catch (Exception e) {
//			e.printStackTrace();
//			System.exit(-1);
//		}
//		return false;
//	}
//
//	private void updateStorageLocationsForIncrementingPointers(
//			Map<Instruction, Map<Container, Set<List<StorageLocation>>>>  instrsAndIncrementingPointerLocs,
//			int iter_count) throws Exception {
//
//		if(instrsAndIncrementingPointerLocs == null){
//			// Sanity check
//			// TODO : Log this warning
//			return;
//		}
//
//		Iterator<Map.Entry<Instruction,Map<Container,Set<List<StorageLocation>>>>> iterator = 
//			instrsAndIncrementingPointerLocs.entrySet().iterator();
//		while(iterator.hasNext()){
//			Entry<Instruction, Map<Container, Set<List<StorageLocation>>>> entry = iterator.next();
//			Instruction instr = entry.getKey();
//			Map<Container, Set<List<StorageLocation>>> containerAndSetOfIncrPtrsList = entry.getValue();
//
//			Iterator<Map.Entry<Container, Set<List<StorageLocation>>>> iterator2 = 
//				containerAndSetOfIncrPtrsList.entrySet().iterator();
//
//			while(iterator2.hasNext()){
//				Map.Entry<Container, Set<List<StorageLocation>>> entry2 = iterator2.next();
//				Container ctr = entry2.getKey();
//				Set<List<StorageLocation>> setOfIncrPtrLists = entry2.getValue();
//				updateWithIncrementingPointerAt(instr, ctr, setOfIncrPtrLists);
//			}
//		}
//
//		// Now we have updated the value and storage location maps with the loop increment flags,
//		// run a DFS again so we get the changes are propagated through the entire procedure.
//		//		CFG graph = function.getCfg();
//		//		do {
//		//			terminateFixedPointIteration =  (Boolean)
//		//			traversal.execute(graph, graph.getStartNode(), programPointAndPointerInfo,
//		//					iter_count);
//		//
//		//			iter_count++;
//		//			LOG.debug("\nVALUES AFTER ITERATION " + iter_count + ": ");
//		//			print(function, null);
//		//
//		//			//						if(iter_count > 5){
//		//			//							break;
//		//			//						}
//		//
//		//		}while(!terminateFixedPointIteration);
//	}
//
//	private void updateWithIncrementingPointerAt(Instruction instruction,
//			Container ctr, Set<List<StorageLocation>> setOfIncrPtrLists) throws Exception {
//
//
//		Iterator<List<StorageLocation>> iterator = setOfIncrPtrLists.iterator();
//		while(iterator.hasNext()){
//			List<StorageLocation> incrementingPtrs = iterator.next();
//
//			Set<StorageLocation> currentStorageLocs = programPointAndPointerInfo.get(instruction).get(ctr);
//
//			for(StorageLocation storageLoc : incrementingPtrs){
//				currentStorageLocs.remove(storageLoc);
//			}
//
//			StorageLocCombinedExpression increment = 
//				StorageLocation.getConstantDiffBetweenAddresses(incrementingPtrs.get(1), incrementingPtrs.get(0));
//
//			StorageLocation newSL = new LoopIncrementedStorageLocation(incrementingPtrs.get(0), increment);
//			currentStorageLocs.add(newSL);
//		}
//	}
//
//	private Map<Instruction, Map<Container, Set<List<StorageLocation>>>> allChangesDueToIncrementingPointersInLoop(
//			Map<Container, Map<Instruction, Pair<Set<StorageLocation>, Set<StorageLocation>>>> containerAndChangingStoreLocs,
//			int iter_count) throws Exception {
//
//		LOG.debug("\n******************************CHANGES AFTER ITERATION " + iter_count + " WITH SIZE OF CHANGES = " + containerAndChangingStoreLocs.size() + ":");
//		//		
//
//		printInsAndChangingSls(containerAndChangingStoreLocs, iter_count);
//		//
//		LOG.debug("\n****************************** END CHANGES AFTER ITERATION " + iter_count + ":");
//		//
//
//		Map<Instruction, Map<Container, Set<List<StorageLocation>>>>  instrsAndIncrementingPointerLocs = new
//		HashMap<Instruction, Map<Container, Set<List<StorageLocation>>>>();
//
//		Set<Entry<Container, Map<Instruction, Pair<Set<StorageLocation>, Set<StorageLocation>>>>> 
//		entries1 = containerAndChangingStoreLocs.entrySet();
//		Iterator<Entry<Container, Map<Instruction, Pair<Set<StorageLocation>, Set<StorageLocation>>>>> iter1 = 
//			entries1.iterator();
//
//		while(iter1.hasNext()){
//			Entry<Container, Map<Instruction, Pair<Set<StorageLocation>, Set<StorageLocation>>>> entry = iter1.next();
//			Container ctr = entry.getKey();
//			Map<Instruction, Pair<Set<StorageLocation>, Set<StorageLocation>>> changingLocs = entry.getValue();
//
//			Iterator<Entry<Instruction, Pair<Set<StorageLocation>, Set<StorageLocation>>>> iter2 = 
//				changingLocs.entrySet().iterator();
//			while(iter2.hasNext()){
//				Entry<Instruction, Pair<Set<StorageLocation>, Set<StorageLocation>>> entry2 = iter2.next();
//				Instruction instr =  entry2.getKey();
//
//				Pair<Set<StorageLocation>, Set<StorageLocation>> changes = entry2.getValue();
//
//				Set<StorageLocation> oldSls = changes.getFirst();
//				if(oldSls == null || oldSls.size() == 0){
//					return null;
//				}
//
//				Set<StorageLocation> newSls = changes.getSecond();
//				Set<List<StorageLocation>> arithmeticProgressionOfAddresses  = 
//					getArithmeticProgressionOfAddresses(instr, ctr, oldSls, newSls);
//				if(arithmeticProgressionOfAddresses == null){
//					return null;
//				}
//				else{
//					Map<Container, Set<List<StorageLocation>>> containerAndIncrementingPtrsAtInstr = 
//						instrsAndIncrementingPointerLocs.get(instr);
//					if(containerAndIncrementingPtrsAtInstr == null){
//						containerAndIncrementingPtrsAtInstr = new HashMap<Container, Set<List<StorageLocation>>>();
//						instrsAndIncrementingPointerLocs.put(instr, containerAndIncrementingPtrsAtInstr);
//					}
//
//					containerAndIncrementingPtrsAtInstr.put(ctr, arithmeticProgressionOfAddresses);
//				}
//			}
//		}
//
//		if(iter_count > 2){
//			printChanges(instrsAndIncrementingPointerLocs);
//		}
//
//		return instrsAndIncrementingPointerLocs;
//	}
//
//	// TODO For debugging, remove later
//	private void printChanges(
//			Map<Instruction, Map<Container, Set<List<StorageLocation>>>> instrAndStrLocationsPrgs) {
//
//		Set<Instruction> instrs = instrAndStrLocationsPrgs.keySet();
//		List<String> instrStrs = new ArrayList<String>();
//		Iterator<Instruction> iter = instrs.iterator();
//		Map<String, Instruction> nameAndInstrs = new HashMap<String, Instruction>();
//		while(iter.hasNext()){
//			Instruction instr = iter.next();
//			String instrDesc = instr.toString();
//			instrStrs.add(instrDesc);
//			nameAndInstrs.put(instrDesc, instr);
//		}
//
//		Collections.sort(instrStrs);
//
//		LOG.debug("PROGRESSIVELY INCREASING ADDRESSES with size = " + instrAndStrLocationsPrgs.size() + ": ");
//
//		for(String str : instrStrs){
//			Instruction instr = nameAndInstrs.get(str);
//			LOG.debug("AT INSTRUCTION " + instr.toString() + ": ");
//			LOG.debug("		" + instrAndStrLocationsPrgs.get(instr));
//		}
//	}
//
//	private Set<List<StorageLocation>> getArithmeticProgressionOfAddresses(Instruction instruction, Container ctr, 
//			Set<StorageLocation> oldSls, Set<StorageLocation> newSls) throws Exception {
//		List<StorageLocation> orderedListOfScaledAddresses = getArithmeticProgressionOfAddresses(oldSls, newSls); 
//
//		if(orderedListOfScaledAddresses == null){
//			return null;
//		}
//
//		// TODO Confirm the control flow for this instruction
//		// TODO implement bucketing of lists into a set
//		Set<List<StorageLocation>> setOfScaledAddressesList = new HashSet<List<StorageLocation>>();
//		setOfScaledAddressesList.add(orderedListOfScaledAddresses);
//		return setOfScaledAddressesList;
//
//	}
//
//	private List<StorageLocation> getArithmeticProgressionOfAddresses(
//			Set<StorageLocation> oldSls, Set<StorageLocation> newSls) throws Exception {
//
//		List<StorageLocation> orderedListOfScaledAddresses = new ArrayList<StorageLocation>();
//
//		StorageLocation newSL = null;
//
//		if(!(newSls.size() > 2 && oldSls.size() == newSls.size() - 1)){
//			// The set of new storage location should be at least 3 to figure out that
//			// an increment is occurring; and the size of the older storage location set 
//			// should be one less than that of the new set.
//			return null;
//		}
//
//		Iterator<StorageLocation> newSlsIter = newSls.iterator();
//		boolean addedOneNonScaledAddress = false;
//		while(newSlsIter.hasNext()){
//			StorageLocation sl = newSlsIter.next();
//			if(!sl.isScaledLocation()){
//				// At the most we can have only one non-scaled location
//				if(addedOneNonScaledAddress){
//					return null;
//				}
//				else{
//					addedOneNonScaledAddress = true;
//				}
//			}
//			if(!oldSls.contains(sl)){
//				// At the most we can have only one new location
//				if(newSL != null){
//					return null;
//				}
//				newSL = sl;
//			}
//			if(sl != newSL){
//				if(!addToOrderedListOfScaledAddresses(orderedListOfScaledAddresses, sl)){
//					return null;
//				}
//			}
//		}
//
//		// We now have set possible storage locations for the container at the given location that
//		// are all scaled addresses. Check if they form a progressively incrementing pointer
//
//		StorageLocCombinedExpression increment = StorageLocation.getConstantDiffBetweenAddresses(
//				orderedListOfScaledAddresses.get(1), orderedListOfScaledAddresses.get(0));
//
//		for(int i = 2; i < orderedListOfScaledAddresses.size(); i++){
//			StorageLocCombinedExpression currentIncrement = 
//				StorageLocation.getConstantDiffBetweenAddresses(orderedListOfScaledAddresses.get(i),
//						orderedListOfScaledAddresses.get(i-1));
//			if(!(currentIncrement.equals(increment))){
//				return null;
//			}
//		}
//
//		// OK, we have a list of progressively incrementing addresses. Check if the new storage
//		// location is next in the arithmetic progression.
//		StorageLocCombinedExpression diff= 
//			StorageLocation.getConstantDiffBetweenAddresses(newSL,
//					orderedListOfScaledAddresses.get(orderedListOfScaledAddresses.size() -1));
//		if(!(diff.equals(increment))){
//			return null;
//		}
//
//		orderedListOfScaledAddresses.add(newSL);
//
//		return orderedListOfScaledAddresses;
//	}
//
//	private boolean addToOrderedListOfScaledAddresses(List<StorageLocation> orderedListOfScaledAddresses, 
//			StorageLocation storageLocation){
//		try{
//			int count = 0;
//			boolean inserted = false;
//			Long constValue = null;
//
//			for(StorageLocation existingScaledAddress : orderedListOfScaledAddresses){
//
//				StorageLocCombinedExpression diff = 
//					StorageLocation.getConstantDiffBetweenAddresses(storageLocation, existingScaledAddress);
//				if(diff == null){
//					return false;
//				}
//
//				constValue = diff.getConstValue();
//				if(constValue == null){
//					return false;
//				}
//
//				if(constValue < 0){
//					orderedListOfScaledAddresses.add(count, storageLocation);
//					inserted = true;
//					break;
//				}
//				count++;
//			}
//
//			if(!inserted){
//				orderedListOfScaledAddresses.add(storageLocation);
//			}
//
//			return true;
//		}
//		catch(Exception e){
//			e.printStackTrace();
//			System.exit(-1);
//		}
//
//		return false;
//	}
//
//	private void printInsAndChangingSls(
//			Map<Container, Map<Instruction, Pair<Set<StorageLocation>, Set<StorageLocation>>>> instrsAndChangingSls, 
//			int iter_count) {
//
//		Set<Entry<Container, Map<Instruction, Pair<Set<StorageLocation>, Set<StorageLocation>>>>> 
//		entries1 = instrsAndChangingSls.entrySet();
//		Iterator<Entry<Container, Map<Instruction, Pair<Set<StorageLocation>, Set<StorageLocation>>>>> iter1 = 
//			entries1.iterator();
//
//		while(iter1.hasNext()){
//			Entry<Container, Map<Instruction, Pair<Set<StorageLocation>, Set<StorageLocation>>>> entry = iter1.next();
//			Container ctr = entry.getKey();
//			Map<Instruction, Pair<Set<StorageLocation>, Set<StorageLocation>>> changingLocs = entry.getValue();
//
//			LOG.debug("For container: " + (ctr instanceof Value? emitter.getValidName((Value) ctr) :
//				ctr.toString()) + ": "); 
//
//			Iterator<Entry<Instruction, Pair<Set<StorageLocation>, Set<StorageLocation>>>> iter2 = 
//				changingLocs.entrySet().iterator();
//			while(iter2.hasNext()){
//				Entry<Instruction, Pair<Set<StorageLocation>, Set<StorageLocation>>> entry2 = iter2.next();
//				Instruction instr =  entry2.getKey();
//
//				if(!(ctr instanceof StorageLocation && "AUTO:8".equals(ctr.toString())
//						&& ("%11 = load i32** %ptr, align 8".equals(instr.toString()) ||
//								//"%24 = load i32** %ptr, align 8".equals(instr.toString()) ||
//								//"%34 = load i32* %c, align 4".equals(instr.toString()) ||
//								"store i32* %31, i32** %ptr, align 8".equals(instr.toString())))){
//					//"%13 = load i32** %ptr, align 8".equals(instr.toString())))){
//					continue ;
//				}
//
//				Pair<Set<StorageLocation>, Set<StorageLocation>> changes = entry2.getValue();
//				LOG.debug("	Instruction " + instr.toString() + ":");
//
//				Set<StorageLocation> oldSls = changes.getFirst();
//				Set<StorageLocation> newSls = changes.getSecond();
//
//				String out = "		OLD: ";
//				if(oldSls == null || oldSls.size() == 0){
//					out += "{}";
//				}
//				else{
//					Iterator<StorageLocation> slIter = oldSls.iterator();
//					while(slIter.hasNext()){
//						out += slIter.next().toString() + ", ";
//					}
//				}
//				LOG.debug(out);
//
//				out = "		NEW: ";
//				if(newSls == null || newSls.size() == 0){
//					out += "{}";
//				}
//				else{
//					Iterator<StorageLocation> slIter = newSls.iterator();
//					while(slIter.hasNext()){
//						StorageLocation sl = slIter.next();
//						out += sl.toString() + ", ";
//					}
//					LOG.debug(out);
//
//					if(iter_count >= 5){
//						slIter = newSls.iterator();
//						int index = 1;
//						while(slIter.hasNext()){
//							StorageLocation sl = slIter.next();
//
//							List<String> params = getScaledParams(sl);
//							if(params.size() == 2 ){
//								LOG.debug("StorageLocation s" + index + " = createStorageLocation(base, " + params.get(0) + 
//										", " + params.get(1) + ", value);");
//								index++;
//							}
//
//
//						}
//					}
//
//				}
//
//			}
//
//		}
//	}
//
//	// TODO : For debugging, remove later
//	private List<String> getScaledParams(StorageLocation sl){
//		List<String> vals = new ArrayList<String>();
//
//		if(sl.getType() != LocationType.SCALED){
//			return vals;
//		}
//		
//		ScaledStorageLocation scaledStorageLocation = (ScaledStorageLocation) sl;
//		
//		List<Pair<ConstantInt,Value>> expr = scaledStorageLocation.getLinearExpression();
//		vals.add(scaledStorageLocation.getBaseOffset() + "");
//
//		if(expr != null){
//			for(Pair<ConstantInt, Value> element : expr){
//				vals.add(element.getFirst().getApInt().getVal());
//			}
//		}
//
//		return vals;
//	}
//
//	public List<Value> identifyPointers() {
//		Set<Value> pointers = new HashSet<Value>();
//
//		List<GlobalVariable> globalVars = module.getGlobalVariables();
//		for(GlobalVariable gv : globalVars){
//			pointers.add(gv);
//		}
//
//		List<BasicBlock> basicBlocks = function.getBasicBlocks();
//		for(BasicBlock bb: basicBlocks){
//			Vector<Instruction> instrs = bb.getInstructions();
//			for(Instruction ins : instrs){
//				if(ins.getType() instanceof PointerType){
//					pointers.add(ins);
//				}
//
//				int numOprs = ins.getNumOperands();
//				for(int i = 0; i < numOprs; i++){
//					Value opr = ins.getOperand(i);
//					if(opr.getType() instanceof PointerType){
//						pointers.add(opr);
//					}
//				}
//
//			}
//		}
//
//		List<Value> ptrList = new ArrayList<Value>(pointers);
//
//		return ptrList;
//
//	}
//
//	public Value getPtr(List<Value> ptrs, String name) {
//		for(Value ptr : ptrs){
//			if(ptr.getName() != null){
//				if(ptr.getName().equals(name))
//					return ptr;
//			}
//			else{
//				String nameFromEmitter = emitter.getValidName(ptr);
//				if(name.equals(nameFromEmitter)){
//					return ptr;
//				}
//
//			}
//		}
//
//		return null;
//	}
//
//	public List<BasicBlock> getBasicBlocks(){
//		return function.getBasicBlocks();
//	}
//
//	//TODO This is for debugging, remove this later
//	private void print(Function funcOfInterest, String ptrName) {
//
//		List<BasicBlock> basicBlocks = funcOfInterest.getBasicBlocks();
//
//		for(BasicBlock bb: basicBlocks){
//			Vector<Instruction> instrs = bb.getInstructions();
//
//			for(Instruction ins : instrs){
//
//				if(!(ins.getType() instanceof PointerType)){
//					continue;
//				}
//
//				String insName = emitter.getValidName(ins);
//
//				//				if(ptrName != null && !ptrName.equals(insName)){
//				//					continue;
//				//				}
//
//				String out = insName + ": ";
//
//				Map<Container,Set<StorageLocation>> valueAndStrLocs = programPointAndPointerInfo.get(ins);
//				if(valueAndStrLocs == null){
//					continue;
//				}
//
//				Set<Map.Entry<Container,Set<StorageLocation>>> entries1 = valueAndStrLocs.entrySet();
//				Iterator<Map.Entry<Container,Set<StorageLocation>>> iter1 = entries1.iterator();
//
//				out += "Storage Locs: [";
//
//				while(iter1.hasNext()){
//					Map.Entry<Container,Set<StorageLocation>> entr1 = iter1.next();
//					Container container = entr1.getKey();
//
//					if(container.getContainerType() == ContainerTypeID.VALUE){
//						Value val = (Value) container;
//						out += emitter.getValidName(val) + ": {";
//					}
//					else{
//						StorageLocation address = (StorageLocation) container;
//						out +=  "ADDRESS(" + address + "): {"; 
//					}
//					Set<StorageLocation> strLocs = entr1.getValue();
//					out += "$hc=" + System.identityHashCode(strLocs) + " ";
//					Iterator<StorageLocation> slIter = strLocs.iterator();
//					while(slIter.hasNext()){
//						out += slIter.next().toString() + ", ";
//					}
//
//					out += "}, ";
//				}
//				out += "]";
//				LOG.debug(out);
//			}
//		}
//	}
//
//	private void initializePointersAndStorageLocations(Function funcOfInterest) {
//
//		long globalVarCount = 0;
//		long localVarCount = 0;
//		long argVarCount = 0;
//
//		List<BasicBlock> basicBlocks = funcOfInterest.getBasicBlocks();
//		List<Instruction> visitedInstructions = new ArrayList<Instruction>();
//
//		Instruction previousInstructionVisited = null;
//		Set<Value> valueSet = new HashSet<Value>();
//
//		int count = 0;
//
//		for(BasicBlock bb: basicBlocks){
//			Vector<Instruction> instrs = bb.getInstructions();
//
//			for(Instruction ins : instrs){
//				boolean tagInstruction = false;
//
//				Map<Container, Set<StorageLocation>> valueAndSls = null;
//
//				if(ins.getType() instanceof PointerType){
//					InstructionID instrID = ins.getInstructionID();
//					Set<StorageLocation> locsForLocalVar = new HashSet<StorageLocation>();
//
//
//					if("%b = alloca i32, align 4".equals(ins.toString())){
//						LOG.debug("WAIT HERE, for inst %b def, sls hc = " + locsForLocalVar.hashCode());
//					}
//
//
//					if(instrID == InstructionID.ALLOCA_INST){
//						StorageLocation slForLocalVar = new StorageLocation(LocationType.AUTO, localVarCount++);
//						locsForLocalVar.add(slForLocalVar);
//					}
//
//					valueAndSls = new HashMap<Container, Set<StorageLocation>>();
//
//					valueAndSls.put(ins, locsForLocalVar);
//					updateValueAndSls(programPointAndPointerInfo.get(previousInstructionVisited), valueAndSls);
//
//					programPointAndPointerInfo.put(ins, valueAndSls);
//					updateVisitedInstructionsWith(visitedInstructions, ins, locsForLocalVar);
//					tagInstruction = true;
//				}
//
//				// Check the operands
//				int numOprs = ins.getNumOperands();
//				for(int i = 0; i < numOprs; i++){
//					Value opr = ins.getOperand(i);
//					if(!(opr.getType() instanceof PointerType)){
//						continue;
//					}
//
//					if(valueSet.contains(opr)){
//						continue;
//					}
//
//					if(opr instanceof GlobalValue || opr instanceof Argument){
//						StorageLocation storageLoc = null;
//						if(opr instanceof GlobalValue){
//							storageLoc = new StorageLocation(LocationType.GLOBAL, globalVarCount++);
//							globalVariables.add((GlobalVariable)opr);
//						}
//						else{
//							storageLoc = new StorageLocation(LocationType.ARGUMENT, argVarCount++);
//						}
//						Set<StorageLocation> locsForOperand = new HashSet<StorageLocation>();
//						locsForOperand.add(storageLoc);
//
//						if(valueAndSls == null){
//							valueAndSls = new HashMap<Container, Set<StorageLocation>>();
//						}
//						valueAndSls.put(opr, locsForOperand);
//						updateValueAndSls(programPointAndPointerInfo.get(previousInstructionVisited), valueAndSls);
//
//						programPointAndPointerInfo.put(ins, valueAndSls);
//
//						updateVisitedInstructionsWith(visitedInstructions, opr, locsForOperand);
//						tagInstruction = true;
//
//						valueSet.add(opr);
//
//					}
//				}
//
//				if(!tagInstruction){
//					// Update info with previous instruction
//					if(previousInstructionVisited != null){
//						updateCurrentInsWithPreviousInfo(ins, 
//								programPointAndPointerInfo.get(previousInstructionVisited));
//					}
//				}
//
//				visitedInstructions.add(ins);
//				previousInstructionVisited = ins;
//				count++;
//			}
//		}
//	}
//
//	private void updateCurrentInsWithPreviousInfo( Instruction ins,
//			Map<Container, Set<StorageLocation>> previousInfo) {
//
//		if(previousInfo == null || previousInfo.isEmpty()){
//			return;
//		}
//
//		Map<Container, Set<StorageLocation>> infoCurrentIns = new HashMap<Container, Set<StorageLocation>>();
//
//		Set<Entry<Container, Set<StorageLocation>>> entries = previousInfo.entrySet();
//		Iterator<Entry<Container, Set<StorageLocation>>> iter = entries.iterator();
//		while(iter.hasNext()){
//			Map.Entry<Container, Set<StorageLocation>> entr = iter.next();
//			Container ctr = entr.getKey();
//			Set<StorageLocation> storageLocs = entr.getValue();
//
//			Set<StorageLocation> newStorageLocsSet = new HashSet<StorageLocation>();
//			newStorageLocsSet.addAll(storageLocs);
//			infoCurrentIns.put(ctr, newStorageLocsSet);
//		}
//
//		programPointAndPointerInfo.put(ins, infoCurrentIns);
//
//	}
//
//	private void updateVisitedInstructionsWith(
//			List<Instruction> visitedInstructions,
//			Value value, Set<StorageLocation> storageLocs) {
//
//		for(Instruction visitedIns : visitedInstructions){
//			Map<Container, Set<StorageLocation>> mapForVisitedIns = programPointAndPointerInfo.get(visitedIns);
//			if(mapForVisitedIns == null){
//				mapForVisitedIns = new HashMap<Container, Set<StorageLocation>>();
//				programPointAndPointerInfo.put(visitedIns, mapForVisitedIns);
//			}
//
//			Set<StorageLocation> copy = new HashSet<StorageLocation>();copy.addAll(storageLocs);
//			mapForVisitedIns.put(value, copy);
//		}
//	}
//
//	private void updateValueAndSls(
//			Map<Container, Set<StorageLocation>> currentValueAndSLMap,
//			Map<Container, Set<StorageLocation>> valueAndSls) {
//
//		if(currentValueAndSLMap == null){
//			// Probably the first instruction being analyzed; no change
//			return;
//		}
//
//		Set<Entry<Container, Set<StorageLocation>>> entries = currentValueAndSLMap.entrySet();
//		Iterator<Entry<Container, Set<StorageLocation>>> iter = entries.iterator();
//		while(iter.hasNext()){
//			Map.Entry<Container, Set<StorageLocation>> entr = iter.next();
//			Container container = entr.getKey();
//			Set<StorageLocation> storageLocs = entr.getValue();
//			valueAndSls.put(container, storageLocs);
//		}
//	}
//
//	@Override
//	public AliasResult alias(StorageLocation LocA, StorageLocation LocB) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public boolean pointsToConstantMemory(StorageLocation Loc, boolean OrLocal) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public ModRefBehavior getModRefBehavior(Function F) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void deleteValue(Value V) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void copyValue(Value From, Value To) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void addEscapingUse(User U) {
//		// TODO Auto-generated method stub
//
//	}
//
//	public AliasResult alias(final Instruction programPoint, final Value value1, final Value value2) {
//		if(!(value1.getType().isPointerType() && value2.getType().isPointerType())){
//			// Non-pointers do not alias each other
//			return AliasResult.NO_ALIAS;
//		}
//
//		if(programPoint == null){
//			// Sanity check; return MAY_ALIAS
//			return AliasResult.MAY_ALIAS;
//		}
//
//		Map<Container, Set<StorageLocation>> valueAndSLAtProgramPoint = programPointAndPointerInfo.get(programPoint);		
//		return checkPossibleStorageLocations(valueAndSLAtProgramPoint.get(value1), valueAndSLAtProgramPoint.get(value2),
//				value1, value2);
//	}
//
//	public AliasResult alias(final Instruction programPoint1, final Instruction programPoint2,
//			final Value value) {
//		if(!(value.getType().isPointerType())){
//			// Non-pointers do not point to anything
//			return AliasResult.NO_ALIAS;
//		}
//
//		if(programPoint1 == null || programPoint2 == null){
//			// Sanity check; return MAY_ALIAS
//			return AliasResult.MAY_ALIAS;
//		}
//
//		//		if(programPoint1 == programPoint2){
//		//			return AliasResult.MUST_ALIAS;
//		//		}
//
//		Map<Container, Set<StorageLocation>> valueAndSLAtProgramPoint1 = programPointAndPointerInfo.get(programPoint1);
//		Map<Container, Set<StorageLocation>> valueAndSLAtProgramPoint2 = programPointAndPointerInfo.get(programPoint2);
//
//		return checkPossibleStorageLocations(valueAndSLAtProgramPoint1.get(value), valueAndSLAtProgramPoint2.get(value),
//				value, value);
//	}
//
//	private AliasResult checkPossibleStorageLocations(Set<StorageLocation> possibleStorageLocs1,
//			Set<StorageLocation> possibleStorageLocs2, Value value1, Value value2){
//		if(possibleStorageLocs1.size() == 0 || possibleStorageLocs2.size() == 0){
//			return AliasResult.NO_ALIAS;
//		}
//
//		if(possibleStorageLocs1.size() == 1 && possibleStorageLocs2.size() == 1){
//			StorageLocation sl1 = (StorageLocation)(possibleStorageLocs1.toArray()[0]);
//			StorageLocation sl2 = (StorageLocation)(possibleStorageLocs2.toArray()[0]);
//
//			return alias(sl1, sl2, value1, value2);
//		}
//
//		Iterator<StorageLocation> iter1 = possibleStorageLocs1.iterator();
//		while(iter1.hasNext()){
//			StorageLocation sl1 = iter1.next();
//
//			Iterator<StorageLocation> iter2 = possibleStorageLocs2.iterator();
//			while(iter2.hasNext()){
//				StorageLocation sl2 = iter2.next();
//				if(alias(sl1, sl2, value1, value2) != AliasResult.NO_ALIAS){
//					return AliasResult.MAY_ALIAS;
//				}
//			}
//		}
//		return AliasResult.NO_ALIAS;
//	}
//
//	private AliasResult alias(StorageLocation sl1, StorageLocation sl2,
//			Value value1, Value value2) {
//		AliasResult result = sl1.alias(sl2);
//
//		if(result == AliasResult.MUST_ALIAS || result == AliasResult.NO_ALIAS){
//			return result;
//		}
//
//		// If one of both abstract storage location are typed, use the size information if possible
//		if(sl1.getType() == LocationType.SCALED || sl2.getType() == LocationType.SCALED){
//			long sizeOfValue1 = getTypeStoreSize((((PointerType) value1.getType()).getContainedType()));
//			long sizeOfValue2 = getTypeStoreSize((((PointerType) value2.getType()).getContainedType()));
//
//			if(sl1.getType() == LocationType.SCALED){
//				return aliasBasedOnScaledAddressesAndSize((ScaledStorageLocation)sl1, sl2, sizeOfValue1, sizeOfValue2);
//			}
//			else{
//				return aliasBasedOnScaledAddressesAndSize((ScaledStorageLocation)sl2, sl1, sizeOfValue2, sizeOfValue1);
//			}
//		}
//		else{
//			return result;
//		}
//	}
//
//	private AliasResult aliasBasedOnScaledAddressesAndSize(ScaledStorageLocation scaledSL,
//			StorageLocation otherSL, long size1, long size2) {
//		StorageLocation baseAddress = scaledSL.getBaseAddressLocation();
//		long baseOffset = scaledSL.getBaseOffset();
//		List<Pair<ConstantInt, Value>> linearExpression  = scaledSL.getLinearExpression();
//
//		LocationType otherType = otherSL.getType();
//		if(otherType != LocationType.SCALED){
//			if(!(baseOffset == 0 && linearExpression.size() == 0)){
//				return AliasResult.NO_ALIAS;
//			}
//			else{
//				return baseAddress.alias(otherSL);
//			}
//		}
//		else{
//			// The other storage location is also scaled
//			ScaledStorageLocation otherScaledLoc = (ScaledStorageLocation) otherSL;
//			StorageLocation otherBaseAddressLocation = otherScaledLoc.getBaseAddressLocation();
//			List<Pair<ConstantInt, Value>> otherLinearExpression = otherScaledLoc.getLinearExpression();
//			long otherBaseOffset = otherScaledLoc.getBaseOffset();
//
//			// If the base addresses are not the same, we cant say anything about the addresses
//			if(baseAddress != otherBaseAddressLocation){
//				AliasResult baseAddressAliasResult = baseAddress.alias(otherBaseAddressLocation);
//				if(baseAddressAliasResult != AliasResult.MUST_ALIAS){
//					return baseAddressAliasResult;
//				}
//			}
//
//			// Get the difference from the linear expression (if any)
//			List<Pair<ConstantInt, Value>> differenceInLinearExpression = new ArrayList<Pair<ConstantInt,Value>>();
//			try{
//				differenceInLinearExpression = StorageLocation.getDifference(linearExpression, otherLinearExpression);
//			}
//			catch(Exception e){
//				// TODO Log this later
//				e.printStackTrace();
//				System.exit(-1);
//			}
//
//			if(baseOffset == otherBaseOffset){
//				if(differenceInLinearExpression.size() == 0){
//					// Lexically similar GEPs
//					return AliasResult.MUST_ALIAS;
//				}
//				else{
//					// TODO Can we handle this better?
//					return AliasResult.PARTIAL_ALIAS;
//				}
//			}
//			else{
//				long offsetDifference = baseOffset - otherBaseOffset;
//				if(differenceInLinearExpression.size() == 0){
//					// Differs with a constant offset
//					if(offsetDifference >= 0){
//						if(offsetDifference < size2){
//							return AliasResult.PARTIAL_ALIAS;
//						}
//						else{
//							return AliasResult.NO_ALIAS;
//						}
//					}
//					else{
//						if(Math.abs(offsetDifference) < size1){
//							return AliasResult.PARTIAL_ALIAS;
//						}
//						else{
//							return AliasResult.NO_ALIAS;
//						}
//					}
//				}
//				else{
//					// TODO Can we handle this better?
//					return AliasResult.PARTIAL_ALIAS;
//				}
//			}
//		}
//	}
//
//	public Set<StorageLocation> getStorageLocation(Instruction instruction, Container container) {
//		return programPointAndPointerInfo.get(instruction).get(container);
//	}
}

class AliasAnalysisDFS // extends DepthFirstSearch{
{

//	private HashMap<CFEdge<Node>, Boolean> edgeVsVisitStatus;
//
//	private HashSet<Node> visitedNodes;
//
//	private Map<BasicBlock, Map<Container, Set<StorageLocation>>> pointerInfoOut;
//
//	private Map<Instruction, Map<Container, Set<StorageLocation>>> programPointAndPointerInfo;
//
//	private boolean terminateFixedPointIteration;
//
//	private LLVMIREmitter emitter;
//
//	private Map<Container, Set<StorageLocation>> pointerInfoForStartingNode;
//
//	private AliasAnalysis aliasAnalysis;
//
//	private List<Value> pointers;  // For debugging, remove later
//
//	private int iteration_count;
//
//	private List<LoopInfo> loopInfoList;
//
//	private CFG cfg;
//
//	private LoopIdiomRecognize loopIdiomRecognize;
//
//	private boolean doneIdentifyingIncrementingPointerTuples;
//
//	private Map<Container, Map<Instruction, Pair<Set<StorageLocation>, Set<StorageLocation>>>> containerAndChangingStorageLocations; 
//
//
//	public AliasAnalysisDFS(AliasAnalysis aliasAnalysis, CFG cfg){
//		this.pointerInfoOut = new HashMap<BasicBlock, Map<Container, Set<StorageLocation>>>();
//
//		emitter = LLVMIREmitter.getInstance();
//
//		this.aliasAnalysis = aliasAnalysis;
//
//		pointers = ((FSAliasAnalysis)aliasAnalysis).identifyPointers();
//
//		this.cfg = cfg;
//
//		//loopIdiomRecognize = new LoopIdiomRecognize(cfg);
//
//		doneIdentifyingIncrementingPointerTuples = false;
//
//		containerAndChangingStorageLocations = new 
//		HashMap<Container, Map<Instruction, Pair<Set<StorageLocation>, Set<StorageLocation>>>>();
//	}
//
//	public Object execute(DiGraph<CFEdge<Node>, Node> graph, Node startNode, 
//			Map<Instruction, Map<Container, Set<StorageLocation>>> programPointAndInfo,
//			int iteration_count){
//		super.execute(graph, startNode);
//
//		terminateFixedPointIteration = true;
//
//		// Initialize data structures
//		visitedNodes = new HashSet<Node>();
//		edgeVsVisitStatus = new HashMap<CFEdge<Node>, Boolean> ();
//		int numEdges = graph.getNumEdges();
//		for(int i = 0; i < numEdges; i++)
//		{
//			Boolean visited = new Boolean(false);
//			CFEdge<Node> edge = graph.getEdge(i);
//			edgeVsVisitStatus.put(edge, visited);
//		}
//
//		this.programPointAndPointerInfo = programPointAndInfo;
//
//		this.iteration_count = iteration_count;
//
//		return traverse(startNode);
//	}
//
//	@Override
//	public boolean isVisited(CFEdge<Node> edge) {
//		Boolean visited = (Boolean) edgeVsVisitStatus.get(edge);
//		return visited.booleanValue();
//	}
//
//
//	@Override
//	public void visit(CFEdge<Node> edge){
//		edgeVsVisitStatus.put(edge, new Boolean(true));
//	}
//
//	@Override
//	public void visit(Node node){
//
//		BasicBlock basicBlock = (BasicBlock) node;
//
//
//
//
//		LOG.debug("@@@@@@@@@@@@@@@@@@@ VISITING NODE = " + emitter.getValueName(basicBlock));
//		//
//		//		LOG.debug("$$$$$$$$$$$$$$$$$ %p2 storage locs before mergPredData at BB= "  +
//		//				emitter.getValueName(basicBlock)+ getStorageLocsForValueAt(9, 1, "%39"));
//		//
//		//
//		//		if(iteration_count == 1 && "%7".equals(emitter.getValueName(basicBlock))){
//		//			LOG.debug("WAIT HERE");
//		//		}
//
//		// Merge data from predecessors to get pointer information.
//		Map<Container, Set<StorageLocation>> dataIn = mergePredecessorData(basicBlock);
//
//		// Use the merged data from predecessors to model this basic block. The output
//		// will be data in for successors of this basic block
//		List<Instruction> instructions = basicBlock.getInstructions();
//		for(Instruction instruction : instructions){
//			dataIn = modelInstruction(instruction, dataIn);
//		}
//
//		// Update the pointer info out so successors will use them as data in
//		pointerInfoOut.put(basicBlock, dataIn);
//
//		// Update the visited nodes
//		visitedNodes.add(node);
//	}
//
//	protected Set<StorageLocation> getStorageLocsForValueAt(int bbIndex, int instrIndex, String valueName){
//		BasicBlock offendingBB = ((FSAliasAnalysis) aliasAnalysis).getBasicBlocks().get(bbIndex);
//		Value val = ((FSAliasAnalysis) aliasAnalysis).getPtr(pointers, valueName);
//		Instruction ins = offendingBB.getInstructions().elementAt(instrIndex);
//		return programPointAndPointerInfo.get(ins).get(val);
//	}
//
//	private Map<Container, Set<StorageLocation>> mergePredecessorData(BasicBlock basicBlock){
//
//		Map<Container, Set<StorageLocation>> dataInForSuccessor = new HashMap<Container, Set<StorageLocation>>();
//
//		Vector<Node> predecessors = basicBlock.getPredecessors();
//		if(predecessors == null || predecessors.size() == 0){
//			// Must be the start node; the data will be the same as that of the initial data
//			if(pointerInfoForStartingNode == null){
//
//				Vector<Instruction> instrsInStartNode = basicBlock.getInstructions();
//				for(Instruction instr: instrsInStartNode){
//					Map<Container, Set<StorageLocation>> slsInfo = programPointAndPointerInfo.get(instr);
//					merge(slsInfo, dataInForSuccessor);
//
//					pointerInfoForStartingNode = dataInForSuccessor;
//				}
//			}
//			else{
//				dataInForSuccessor = pointerInfoForStartingNode;
//			}
//
//			return dataInForSuccessor;
//		}
//
//		if(predecessors.size() == 1){
//			// Only one predecessor; data out of that predecessor
//			// should be data in for current node
//			Map<Container, Set<StorageLocation>> dataOutOfPredecessor = pointerInfoOut.get(predecessors.get(0));
//			dataInForSuccessor = dataOutOfPredecessor;
//
//			return dataInForSuccessor;
//		}
//
//		// Merge data coming from predecessors
//		for(Node pred : predecessors){
//			Map<Container, Set<StorageLocation>> dataOutOfPred = pointerInfoOut.get(pred);
//			if(dataOutOfPred == null){
//				// The predecessor has not been visited yet
//				BasicBlock predBB = (BasicBlock) pred;
//				dataOutOfPred = programPointAndPointerInfo.get(predBB.getLastInstruction());
//			}
//
//			Iterator<Map.Entry<Container, Set<StorageLocation>>> iterator = dataOutOfPred.entrySet().iterator();
//			while(iterator.hasNext()){
//				Map.Entry<Container, Set<StorageLocation>> entry = iterator.next();
//				Container container = entry.getKey();
//
//				Set<StorageLocation> slsInPred = entry.getValue();
//				Set<StorageLocation> slsInDataIn = dataInForSuccessor.get(container);
//
//				if(slsInPred != null && slsInDataIn != null && slsInPred.size() == 1 && slsInDataIn.size() == 1){
//					Iterator<StorageLocation> iterPred = slsInPred.iterator();
//					Iterator<StorageLocation> iterDataIn = slsInDataIn.iterator();
//
//					StorageLocation slPrd = iterPred.next();
//					StorageLocation slDtIn = iterDataIn.next();
//
//					if(emitter.getValueName(basicBlock).equals("%32")){
//						LOG.debug("WAIT HERE");
//					}
//
//					if(("LOOP_INCR:AUTO:5[0+(1)]".equals(slPrd.toString()) && "ARGUMENT:0".equals(slDtIn))
//							|| "LOOP_INCR:AUTO:5[0+(1)]".equals(slDtIn.toString()) && "ARGUMENT:0".equals(slPrd.toString())){
//						LOG.debug("WAIT HERE");
//					}
//				}
//
//				if(slsInDataIn == null || slsInDataIn.size() == 0){
//					if(iteration_count > 0){
//						LOG.debug("WAIT HERE");
//					}
//					Set<StorageLocation> setForThisProgramPoint = new HashSet<StorageLocation>();
//					setForThisProgramPoint.addAll(slsInPred);
//					dataInForSuccessor.put(container, setForThisProgramPoint);
//				}
//				else{
//					mergeStorageLocations(slsInDataIn, slsInPred);
//				}
//			}
//		}
//
//		return dataInForSuccessor;
//	}
//
//	private void merge(Map<Container, Set<StorageLocation>> from,
//			Map<Container, Set<StorageLocation>> into) {
//
//		Iterator<Entry<Container, Set<StorageLocation>>> iterator = from.entrySet().iterator();
//		while(iterator.hasNext()){
//			Entry<Container, Set<StorageLocation>> entry = iterator.next();
//			Container container = entry.getKey();
//			Set<StorageLocation> slsFrom = entry.getValue();
//
//			if(into.containsKey(container)){
//				// merge
//				Set<StorageLocation> slsInto = into.get(container);
//				//slsInto.addAll(slsFrom);
//				mergeStorageLocations(slsInto, slsFrom);
//			}
//			else{
//				Set<StorageLocation> slsInto = new HashSet<StorageLocation>();
//				//slsInto.addAll(slsFrom);
//				mergeStorageLocations(slsInto, slsFrom);
//				into.put(container, slsInto);
//			}
//		}
//	}
//
//	private Map<Container, Set<StorageLocation>>  modelInstruction(Instruction instruction, 
//			Map<Container, Set<StorageLocation>> valueAndSLAtPredecessor){
//		InstructionID instrID = instruction.getInstructionID();
//		Map<Container, Set<StorageLocation>> valueAndSLAtPropramPoint = programPointAndPointerInfo.get(instruction);
//
//		if(valueAndSLAtPropramPoint == null){
//			valueAndSLAtPropramPoint = new HashMap<Container, Set<StorageLocation>>();
//			programPointAndPointerInfo.put(instruction, valueAndSLAtPropramPoint);
//		}
//
//		Set<StorageLocation> currentSls = valueAndSLAtPropramPoint.get(instruction);
//		Map<StorageLocation, Set<StorageLocation>> currentSlAndStorageLocs = new HashMap<StorageLocation, Set<StorageLocation>>();
//		if(instruction.getInstructionID() == InstructionID.STORE_INST){
//			// Current storage locations will be null; if we are storing a pointer here,
//			// get the storage location of that pointer
//
//			currentSlAndStorageLocs = getStorageLocationAndContainedStorageLocations((StoreInst) instruction);
//		}
//
//		Map<Container, Pair<Set<StorageLocation>, Set<StorageLocation>>> changesAtInstruction = null;
//
//		if(instrIsAssociatedWithIdentifiedLoopIncrement(instruction)){
//			updateWithPreviousProgPointData(valueAndSLAtPredecessor, valueAndSLAtPropramPoint);
//			return programPointAndPointerInfo.get(instruction);
//		}
//
//		changesAtInstruction = 
//			updateWithPreviousProgPointData(valueAndSLAtPredecessor, valueAndSLAtPropramPoint);
//
//		try{
//			if(instrID == InstructionID.LOAD_INST){
//				LoadInst loadInst = (LoadInst) instruction;
//
//				if(loadInst.getType().isPointerType()){
//					Value addressStoredAt = loadInst.getOperand(0);
//					Set<StorageLocation> strLocs = null;
//
//					strLocs = getStorageLocationsFromLoadAddressFromAddress(instruction, addressStoredAt);
//					changesAtInstruction.put(loadInst, 
//							new Pair<Set<StorageLocation>, Set<StorageLocation>>(currentSls, strLocs));
//
//					valueAndSLAtPropramPoint.put(instruction, strLocs);
//				}
//			}
//			else if(instrID == InstructionID.PHI_NODE_INST){
//				PhiNode phiNode = (PhiNode) instruction;
//
//				if(phiNode.getType().isPointerType()){
//					Set<StorageLocation> mergedSls = new HashSet<StorageLocation>();
//					int numIncomingVals = phiNode.getNumIncomingValues();
//					for(int i = 0; i < numIncomingVals; i++){
//						Value incomingVal = phiNode.getIncomingValue(i);
//						BasicBlock bbOfIncomingVal = phiNode.getIncomingBlock(i);
//						Instruction lastInsOfBasicBlockOfIncomingValue = bbOfIncomingVal.getLastInstruction();
//						Set<StorageLocation> slsForVal = null;
//						slsForVal = programPointAndPointerInfo.get(lastInsOfBasicBlockOfIncomingValue).get(incomingVal);
//
//						mergeStorageLocations(mergedSls, slsForVal);
//					}
//
//					changesAtInstruction.put(phiNode, 
//							new Pair<Set<StorageLocation>, Set<StorageLocation>>( currentSls,  mergedSls));
//
//					valueAndSLAtPropramPoint.put(instruction, mergedSls);
//				}
//			}
//			else if(instrID == InstructionID.GET_ELEMENT_PTR){
//
//				GetElementPtrInst gep = (GetElementPtrInst) instruction;
//				
//				if("%14 = getelementptr inbounds i32* %13, i32 1".equals(gep.toString())){
//					LOG.debug("WAIT HERE");
//				}
//				
//				Set<StorageLocation> newSls = new HashSet<StorageLocation>();
//
//				Value pointerOperand = gep.getPointerOperand();
//				Set<StorageLocation> baseAddressSls = valueAndSLAtPropramPoint.get(pointerOperand);
//
//				Iterator<StorageLocation> baseAddressIterator = baseAddressSls.iterator();
//				while(baseAddressIterator.hasNext()){
//					StorageLocation sl = baseAddressIterator.next();
//					StorageLocation newSL = analyzeGEP(gep, sl);
//					newSls.add(newSL);
//				}
//
//				changesAtInstruction.put(gep, 
//						new Pair<Set<StorageLocation>, Set<StorageLocation>>(currentSls, newSls));
//				valueAndSLAtPropramPoint.put(instruction, newSls);
//			}
//			else if(instrID == InstructionID.BIT_CAST_INST){
//				CastInst bitCast = (CastInst) instruction;
//				Value srcValue = bitCast.getOperand(0);
//				Set<StorageLocation> srcValueAddresses = valueAndSLAtPropramPoint.get(srcValue);
//
//				Set<StorageLocation> newSls = new HashSet<StorageLocation>();
//				Iterator<StorageLocation> slIterator = srcValueAddresses.iterator();
//				while(slIterator.hasNext()){
//					StorageLocation sl = slIterator.next();
//					newSls.add(sl);
//				}
//
//				changesAtInstruction.put(bitCast, 
//						new Pair<Set<StorageLocation>, Set<StorageLocation>>(currentSls, newSls));
//
//				valueAndSLAtPropramPoint.put(instruction, newSls);
//			}
//			else if(instrID == InstructionID.SELECT_INST){
//				SelectInst selectInst = (SelectInst) instruction;
//				Value srcValue1 = selectInst.getOperand(1);
//				Value srcValue2 = selectInst.getOperand(2);
//				Set<StorageLocation> srcValueAddresses1 = valueAndSLAtPropramPoint.get(srcValue1);
//				Set<StorageLocation> srcValueAddresses2 = valueAndSLAtPropramPoint.get(srcValue2);
//
//				Set<StorageLocation> newSls = new HashSet<StorageLocation>();
//				mergeStorageLocations(newSls, srcValueAddresses1);
//				mergeStorageLocations(newSls, srcValueAddresses2);
//
//				changesAtInstruction.put(selectInst, 
//						new Pair<Set<StorageLocation>, Set<StorageLocation>>( currentSls, 
//								newSls));
//				valueAndSLAtPropramPoint.put(instruction, newSls);
//			}
//			else if(instrID == InstructionID.STORE_INST){
//				StoreInst storeInst = (StoreInst) instruction;
//				Value storedValue = storeInst.getOperand(0);
//
//				if("store i32* %29, i32** %ptr, align 8".equals(storeInst.toString())
//						|| "store i32* %21, i32** %ptr, align 8".equals(storeInst.toString())
//						|| "store i32* %12, i32** %ptr, align 8".equals(storeInst.toString())){
//					LOG.debug("WAIT HERE");
//				}
//
//				if(storedValue.getType().isPointerType()){
//					Set<StorageLocation> newStorageLocs = new HashSet<StorageLocation>();
//					Value addressStoredAt = storeInst.getOperand(1);
//					Set<StorageLocation> baseAddrSLs = programPointAndPointerInfo.get(instruction).get(addressStoredAt);
//					Iterator<StorageLocation> baseIter = baseAddrSLs.iterator();
//
//					Set<StorageLocation> storedValueSLs = programPointAndPointerInfo.get(instruction).get(storedValue);
//					newStorageLocs.addAll(storedValueSLs);
//					while(baseIter.hasNext()){
//						StorageLocation baseSl = baseIter.next();
//
//						if(currentSlAndStorageLocs != null){
//							currentSls = currentSlAndStorageLocs.get(baseSl);
//						}
//
//						changesAtInstruction.put(baseSl, 
//								new Pair<Set<StorageLocation>, Set<StorageLocation>>(currentSls, 
//										newStorageLocs));
//
//						programPointAndPointerInfo.get(instruction).put(baseSl, newStorageLocs);
//					}
//				}
//			}
//			else if(instrID == InstructionID.CALL_INST){
//
//			}
//			else if(instrID == InstructionID.INT_TO_PTR_INST){
//
//			}
//
//			// Register all changes that happened as a result of visiting this instruction
//			updateChanges(instruction, changesAtInstruction);
//
//			if(valueAndSLAtPredecessor == null){
//				return valueAndSLAtPropramPoint; 
//			}
//		}
//		catch(InstructionDetailAccessException idae){
//			idae.printStackTrace();
//			System.exit(-1);
//		}
//
//		return valueAndSLAtPropramPoint;
//	}
//
//	private Map<StorageLocation, Set<StorageLocation>> getStorageLocationAndContainedStorageLocations(StoreInst storeInst){
//
//		if(storeInst == null){
//			return null;
//		}
//
//		Value storedValue = storeInst.getOperand(0);
//		if(!storedValue.getType().isPointerType()){
//			return null;
//		}
//
//		Value addressStoredAt = storeInst.getOperand(1);
//		Set<StorageLocation> containedAddresses = programPointAndPointerInfo.get(storeInst).get(addressStoredAt);
//		if(containedAddresses == null){
//			return null;
//		}
//
//		Map<StorageLocation, Set<StorageLocation>> storageLocationsAndContainedStorageLocations = new 
//		HashMap<StorageLocation, Set<StorageLocation>>();
//
//		Iterator<StorageLocation> containedAddressesIter = containedAddresses.iterator();
//		while(containedAddressesIter.hasNext()){
//			StorageLocation containedAddress = containedAddressesIter.next();
//			storageLocationsAndContainedStorageLocations.put(containedAddress,
//					programPointAndPointerInfo.get(storeInst).get(containedAddress));
//		}
//
//		return storageLocationsAndContainedStorageLocations;
//	}
//
//	private void updateChanges(Instruction instruction, Map<Container, Pair<Set<StorageLocation>, Set<StorageLocation>>>
//	changesAtInstruction){
//		Set<Entry<Container, Pair<Set<StorageLocation>, Set<StorageLocation>>>>  entries = changesAtInstruction.entrySet();
//		Iterator<Entry<Container, Pair<Set<StorageLocation>, Set<StorageLocation>>>> iter = entries.iterator();
//
//		while(iter.hasNext()){
//			Entry<Container, Pair<Set<StorageLocation>, Set<StorageLocation>>> entry = iter.next();
//			Container container = entry.getKey();
//			Pair<Set<StorageLocation>, Set<StorageLocation>> changes = entry.getValue();
//
//			updateChanges(instruction, container, changes.getFirst(), changes.getSecond());
//		}
//	}
//
//	private boolean instrIsAssociatedWithIdentifiedLoopIncrement(Instruction programPoint){
//
//		InstructionID instrID = programPoint.getInstructionID();
//		if(!( instrID == InstructionID.LOAD_INST || instrID == InstructionID.PHI_NODE_INST || 
//				instrID == InstructionID.GET_ELEMENT_PTR || instrID == InstructionID.SELECT_INST || 
//				instrID == InstructionID.BIT_CAST_INST)){
//			// This instruction cannot be part of a pointer increment in a loop
//			return false;
//		}
//
//		Value value = (Value)programPoint;
//
//		Set<StorageLocation> storageLocs = programPointAndPointerInfo.get(programPoint).get(value);
//		if(storageLocs == null || storageLocs.size() == 0){
//			return false;
//		}
//
//		if(!storageLocsHasIdentifiedLoopIncrement(storageLocs)){
//			return false;
//		}
//
//		return true;
//	}
//
//	private boolean storageLocsHasIdentifiedLoopIncrement(Set<StorageLocation> storageLocs){
//		if(storageLocs == null || storageLocs.size() == 0){
//			return false;
//		}
//
//		Iterator<StorageLocation> iterator = storageLocs.iterator();
//		while(iterator.hasNext()){
//			StorageLocation storageLoc = iterator.next();
//			if(storageLoc.getType() != LocationType.LOOP_INCREMENT){
//				return false;
//			}
//		}
//		return true;
//	}
//
//	/*private boolean storageLocIsIdentifiedLoopIncrement(Instruction programPoint, Value value){
//		Set<StorageLocation> storageLocs = programPointAndPointerInfo.get(programPoint).get(value);
//		if(storageLocs == null || storageLocs.size() == 0){
//			return false;
//		}
//
//		Iterator<StorageLocation> iterator = storageLocs.iterator();
//		while(iterator.hasNext()){
//			StorageLocation storageLoc = iterator.next();
//			if(storageLoc.getType() != LocationType.LOOP_INCREMENT){
//				return false;
//			}
//		}
//
//		return true;
//
//	}
//	 */
//
//	private void updateChanges(Instruction instruction, Container container, Set<StorageLocation> oldLocations,
//			Set<StorageLocation> newLocations){
//
//		if(iteration_count > 1 && container instanceof Value && "%13".equals(emitter.getValidName((Value)container))){
//			LOG.debug("WAIT HERE");
//		}
//
//		Map<Instruction, Pair<Set<StorageLocation>, Set<StorageLocation>>> changesForContainer 
//		= 	containerAndChangingStorageLocations.get(container);
//
//		if(oldLocations == null || !similarStorageLocationSets(newLocations, oldLocations)){
//			// Something has changed
//			//						LOG.debug("AYYO, CHANGING AGAIN: " + 
//			//								(container instanceof Value? emitter.getValidName((Value)container) : container.toString())
//			//								+ " AT Instruction: " + instruction.toString() +  
//			//								"with old = " + oldLocations  + " AND NEW = " + newLocations) ;
//			terminateFixedPointIteration = false;
//
//			if(changesForContainer == null){
//				changesForContainer = new HashMap<Instruction, Pair<Set<StorageLocation>,Set<StorageLocation>>>();
//				Pair<Set<StorageLocation>,Set<StorageLocation>> changedPair = 
//					new Pair<Set<StorageLocation>, Set<StorageLocation>>(oldLocations, newLocations);
//				changesForContainer.put(instruction, changedPair);
//				containerAndChangingStorageLocations.put(container, changesForContainer);
//			}
//			else{
//				Pair<Set<StorageLocation>,Set<StorageLocation>> changedPair = changesForContainer.get(instruction);
//				if(changedPair == null){
//					changedPair = 
//						new Pair<Set<StorageLocation>, Set<StorageLocation>>(oldLocations, newLocations);
//					changesForContainer.put(instruction, changedPair);
//				}
//				changedPair.setFirst(oldLocations);
//				changedPair.setSecond(newLocations);
//			}
//		}
//		else{
//			// No change, remove from the change map if a change had already been noted at the instruction.
//			if(changesForContainer != null){
//				changesForContainer.remove(instruction);
//				if(changesForContainer.size() == 0){
//					containerAndChangingStorageLocations.remove(container);
//				}
//			}
//			//containerAndChangingStorageLocations.remove(changesForContainer);
//		}
//	}
//
//	//	private boolean nonConvergentFunction(Instruction instruction) {
//	//		if(!doneIdentifyingIncrementingPointerTuples){
//	//			loopIdiomRecognize.identifyingIncrementingPointers();
//	//			doneIdentifyingIncrementingPointerTuples = true;
//	//		}
//	//		if(loopIdiomRecognize.participatesInIncrementingPointer(instruction)){
//	//			return true;
//	//		}
//	//		else{
//	//			return false;
//	//		}
//	//	}
//
//	private Set<StorageLocation> getStorageLocationsFromLoadAddressFromAddress(
//			Instruction instruction, Value addressStoredAt) {
//
//		Set<StorageLocation> baseAddrLocations = programPointAndPointerInfo.get(instruction).get(addressStoredAt);
//
//		Set<StorageLocation> result = new HashSet<StorageLocation>();
//
//		Iterator<StorageLocation> baseIterator = baseAddrLocations.iterator();
//		while(baseIterator.hasNext()){
//			StorageLocation baseSL = baseIterator.next();
//			Set<StorageLocation> containedSLs = programPointAndPointerInfo.get(instruction).get(baseSL);
//			if(containedSLs != null){
//				result.addAll(containedSLs);
//			}
//			else{
//				if(baseSL.isArg() || baseSL.isGlobal() || baseSL.isAny()){
//					// If the containing address is global or an argument, the storage location in it
//					// is unknown
//					result.add(new StorageLocation(LocationType.ANY, -1));
//				}
//			}
//		}
//
//		return result;
//
//	}
//
//	/**
//	 * Adds all abstract storage locations from source into the destination. Before addition, we check for duplicates
//	 * using the alias() method on storage location instances
//	 */
//	private void mergeStorageLocations(Set<StorageLocation> dest, Set<StorageLocation> source) {
//
//		if(dest.size() == 0){
//			dest.addAll(source);
//			return;
//		}
//
//		Iterator<StorageLocation> srcStorages = source.iterator();
//		while(srcStorages.hasNext()){
//			StorageLocation srcSL = srcStorages.next();
//
//			if(!dest.contains(srcSL)){
//				dest.add(srcSL);
//			}
//		}
//	}
//
//	private boolean similarStorageLocationSets(Set<StorageLocation> firstSet, Set<StorageLocation> secondSet){
//
//		if(firstSet.size() != secondSet.size()){
//			return false;
//		}
//
//		if(firstSet.containsAll(secondSet)){
//			return true;
//		}
//
//		// Look for equivalence of each storage location
//		// TODO This in N^2, can we make this better?
//		Iterator<StorageLocation> firstIter = firstSet.iterator();
//		while(firstIter.hasNext()){
//			StorageLocation slInFirst = firstIter.next();
//			Iterator<StorageLocation> secondIter = secondSet.iterator();
//			int numMatchesFound = 0;
//
//			while(secondIter.hasNext()){
//				StorageLocation slInSecond = secondIter.next();
//				if(storageLocationsAreEquivalent(slInFirst, slInSecond)){
//					numMatchesFound++;
//				}
//			}
//			if(numMatchesFound == 0){
//				return false;
//			}
//		}
//		return true;
//	}
//
//	private boolean storageLocationsAreEquivalent(StorageLocation slInFirst,
//			StorageLocation slInSecond) {
//
//		if(slInFirst.getType() != slInSecond.getType()){
//			return false;
//		}
//
//		if(slInFirst.getType() == LocationType.AUTO || slInFirst.getType() == LocationType.ARGUMENT
//				|| slInFirst.getType() == LocationType.GLOBAL){
//			if(slInFirst.getObjectIndex() != slInSecond.getObjectIndex()){
//				return false;
//			}
//			return true;
//		}
//
//		if(slInFirst.getType() == LocationType.SCALED){
//			
//			ScaledStorageLocation scaledFirst = (ScaledStorageLocation) slInFirst;
//			ScaledStorageLocation scaledSecond = (ScaledStorageLocation) slInSecond;
//			
//			if(scaledFirst.getBaseAddressLocation().getType() != scaledSecond.getBaseAddressLocation().getType()){
//				return false;
//			}
//
//			if(scaledFirst.getBaseOffset() != scaledSecond.getBaseOffset()){
//				return false;
//			}
//			try{
//				if(!StorageLocation.getDifference(scaledFirst.getLinearExpression(), scaledSecond.getLinearExpression()).isEmpty()){
//					return false;
//				}
//
//				return true;
//			}
//			catch(Exception e){
//				e.printStackTrace();
//				System.exit(-1);
//			}
//		}
//
//		if(slInFirst.getType() == LocationType.LOOP_INCREMENT){
//			LoopIncrementedStorageLocation loopIncrementedFirst = (LoopIncrementedStorageLocation) slInFirst;
//			LoopIncrementedStorageLocation loopIncrementedSecond = (LoopIncrementedStorageLocation) slInSecond;
//			if(loopIncrementedFirst.getBaseAddressLocation().equals(loopIncrementedSecond.getBaseAddressLocation())){
//				return false;
//			}
//			
//			if(loopIncrementedFirst.getIncrementedBy().equals(loopIncrementedSecond.getIncrementedBy())){
//				return false;
//			}
//		}
//
//		return true;
//	}
//
//	private StorageLocation analyzeGEP(GetElementPtrInst gep, StorageLocation baseAddressStorageLocation){
//
//		if("%31".equals(emitter.getValidName(gep))){
//			LOG.debug("WAIT HERE");
//		}
//
//		Type currentElementType = gep.getPointerOperand().getType();
//
//		// Iterate over the indexes
//		long baseOffset = 0;
//		List<Pair<ConstantInt, Value>> linearExpression = new ArrayList<Pair<ConstantInt,Value>>();
//
//		int numOprs = gep.getNumOperands();
//		for(int i = 1; i < numOprs; i++){
//			Value index = gep.getOperand(i);
//
//			if(currentElementType.isStructType()){
//				StructType structType = (StructType) currentElementType;
//				ConstantInt constInt = (ConstantInt) index;
//				int indexIntoStructElement = Integer.parseInt(constInt.getApInt().getVal());
//				if(indexIntoStructElement != 0){
//					baseOffset += structType.getElementOffset(indexIntoStructElement);
//				}
//
//				currentElementType = structType.getTypeAtIndex(indexIntoStructElement);
//				continue;
//			}
//
//			// Must be an array/pointer
//			if (index.isAConstant()) {
//				currentElementType = currentElementType.getContainedTypes().get(0);
//				ConstantInt constInt = (ConstantInt) index;
//				if(constInt.isZero()){
//					continue;
//				}
//
//				long factor = Long.parseLong(constInt.getApInt().getVal());
//
//				if(currentElementType.isPrimitiveType()){
//					baseOffset += currentElementType.getPrimitiveSizeInBits() * factor;
//				}
//				if(currentElementType.isPointerType()){
//					baseOffset += ((PointerType) currentElementType).getPointerSize() * factor;
//				}
//				else{
//					if(currentElementType.isStructType()){
//						StructType st = (StructType) currentElementType;
//						baseOffset += st.getSize()* factor;
//					}
//				}
//
//				continue;
//			}
//
//			// A variable is used to index into the array or pointer; create a linear 
//			// expression for this in the form of C1 * V + C2
//			CompilationContext context = currentElementType.getCompilationContext();
//			long sizeValue =  -1;
//			if(currentElementType.isPrimitiveType()){
//				sizeValue =  currentElementType.getPrimitiveSizeInBits();
//			}
//			else if(currentElementType.isArrayType()){
//				// This element represents an array, get the size of each element of the array
//				ArrayType arrayType = (ArrayType) currentElementType;
//				Type containedType = arrayType.getActualContainedType();
//				sizeValue = aliasAnalysis.getTypeStoreSize(containedType);
//				currentElementType = containedType;
//			}
//			else{
//				// This element must represent a pointer
//				sizeValue = ((PointerType) currentElementType).getPointerSize();
//			}
//
//			try{
//				ConstantInt constInt  = ConstantInt.create(Type.getInt64Type(context, false),sizeValue, false);
//				// If we already have such a pair, add the constants, else add this pair into the list
//				ConstantInt newConstInt = null;
//				for(Pair<ConstantInt, Value> expr : linearExpression){
//					if(expr.getSecond() == index){
//						newConstInt = (ConstantInt)expr.getFirst().add(constInt);
//					}
//				}
//
//				if(newConstInt == null){
//					// New pair
//					Pair<ConstantInt, Value> expr = new Pair<ConstantInt, Value>(constInt, index);
//					linearExpression.add(expr);
//				}
//
//			}
//			catch(Exception ie){
//				LOG.debug("Instantiation exception : " + ie.getMessage());
//				System.exit(-1);
//			}
//		}
//
//		return new ScaledStorageLocation(baseAddressStorageLocation, linearExpression, baseOffset);
//	}
//
//	private Map<Container, Pair<Set<StorageLocation>, Set<StorageLocation>>> updateWithPreviousProgPointData(
//			Map<Container, Set<StorageLocation>> valueAndSLAtPredecessor,
//			Map<Container, Set<StorageLocation>> valueAndSLAtPropramPoint){
//
//		Map<Container, Pair<Set<StorageLocation>, Set<StorageLocation>>> changes
//		= new HashMap<Container, Pair<Set<StorageLocation>,Set<StorageLocation>>>();
//
//		if(valueAndSLAtPredecessor == null || valueAndSLAtPropramPoint == null){
//			return changes;
//		}
//
//		Set<Entry<Container,Set<StorageLocation>>> entries = valueAndSLAtPredecessor.entrySet();
//		Iterator<Entry<Container, Set<StorageLocation>>> iter = entries.iterator();
//		while(iter.hasNext()){
//			Entry<Container, Set<StorageLocation>> entry = iter.next();
//			Container container = entry.getKey();
//
//			Set<StorageLocation> slsInPredecessor = entry.getValue();
//			Set<StorageLocation> sls = valueAndSLAtPropramPoint.get(container);
//
//			if(storageLocsHasIdentifiedLoopIncrement(sls)){
//				continue;
//			}
//
//			if(sls == null || (!(sls.size() == slsInPredecessor.size() && sls.containsAll(slsInPredecessor)))){
//				// Needs an update for this pointer from information in predecessor
//				if(iteration_count  > 2 && container instanceof Value && "%5".equals(emitter.getValidName((Value)container))){
//					LOG.debug("WAIT HERE");
//				}
//
//				Set<StorageLocation> copy = new HashSet<StorageLocation>(); copy.addAll(slsInPredecessor);
//				valueAndSLAtPropramPoint.put(container, copy);
//			}
//
//			// Make a note of the change
//			Pair<Set<StorageLocation>,Set<StorageLocation>> oldAndNew = 
//				new Pair<Set<StorageLocation>, Set<StorageLocation>>(sls, 
//						slsInPredecessor);
//			changes.put(container, oldAndNew);
//		}
//
//		return changes;
//	}
//
//	public  boolean isDone(){
//		int numVisitedNodes = visitedNodes.size();
//		int numNodes = graph.getNumNodes();
//		if(numVisitedNodes == numNodes){
//			return true;
//		}
//		return false;
//	}
//
//	@Override
//	public  boolean isVisited(Node node){
//		if(visitedNodes.contains(node)) return true;
//		return false;
//	}
//
//	@Override
//	public void traverseDiscovery(Node nextNode, Node node){
//
//	}
//
//	@Override
//	public void traverseBack(CFEdge<Node> edge, Node node){
//	}
//
//	public Object getResult(){
//		return new Boolean(terminateFixedPointIteration);
//	}
//
//	public Map<Container, Map<Instruction, Pair<Set<StorageLocation>, Set<StorageLocation>>>> getInstructionAndChangingSLs() {
//		return containerAndChangingStorageLocations;
//	}
}


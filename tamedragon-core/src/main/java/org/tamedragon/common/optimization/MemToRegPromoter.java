package org.tamedragon.common.optimization;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;
import java.util.Vector;

import org.tamedragon.common.DominatorTree;
import org.tamedragon.common.DominatorTreeNode;
import org.tamedragon.common.GeneralUtilities;
import org.tamedragon.common.TreeNode;
import org.tamedragon.common.controlflowanalysis.DominatorCalculationContext;
import org.tamedragon.common.llvmir.instructions.AllocaInst;
import org.tamedragon.common.llvmir.instructions.CFG;
import org.tamedragon.common.llvmir.instructions.CastInst;
import org.tamedragon.common.llvmir.instructions.GetElementPtrInst;
import org.tamedragon.common.llvmir.instructions.Instruction;
import org.tamedragon.common.llvmir.instructions.LoadInst;
import org.tamedragon.common.llvmir.instructions.PhiNode;
import org.tamedragon.common.llvmir.instructions.StoreInst;
import org.tamedragon.common.llvmir.instructions.Instruction.InstructionID;
import org.tamedragon.common.llvmir.math.APInt;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.CompilationContext;
import org.tamedragon.common.llvmir.types.ConstantInt;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.IntegerType;
import org.tamedragon.common.llvmir.types.PointerType;
import org.tamedragon.common.llvmir.types.Type;
import org.tamedragon.common.llvmir.types.UndefValue;
import org.tamedragon.common.llvmir.types.Value;
import org.tamedragon.common.llvmir.utils.LLVMIREmitter;
import org.tamedragon.common.utils.LLVMIRUtils;

/**
 * This class implements the Mem2Reg optimization pass that promotes values in memory
 * to temporary registers. The optimization can only be effective on allocations 
 * instructions that qualify to be promoted.
 * 
 * Firstly, allocation instructions are qualified to be promoted are identified. Simple
 * cases where the there is only store to that allocation or allocations that are used
 * only in a single block are promoted first. Thereafter, "non-trivial" allocas that 
 * have multiple loads and stores spanning several basic blocks are promoted.
 * 
 * The promotion of non-trivial allocas is performed in two phases. Insertion points for
 * phi nodes are first determined using worklist to traverse iterated dominance frontiers.
 * Phi nodes are then inserted in those nodes.
 * 
 * In the next phase, values retrieved from load instructions are used to replace uses
 * of the load instruction. The algorithm is loosely based on the pseduo-code in Chapter 19
 * of "Modern Compiler Implementation In Java" (Ed. 2000) by Andrew Appel. The algorithm
 * traverses the dominator tree; for each variable, the most recently defined version of the
 * variable is pushed onto a stack for that variable. During traversal, the children of 
 * each node in the dominator tree is processed; thereafter, definition of each variable is
 * popped off the stack for that variable. This ensures that the "most recent definition" 
 * is used to replace the load instructions.
 *
 */
class AllocInfo{
	private Vector<BasicBlock> definingBlocks;
	private Vector<BasicBlock> usingBlocks;

	private StoreInst  onlyStore;
	private BasicBlock onlyBlock;
	private boolean onlyUsedInOneBlock;

	public AllocInfo(){
		definingBlocks = new Vector<BasicBlock>();
		usingBlocks = new Vector<BasicBlock>();
	}

	private void clear() {
		definingBlocks.clear();
		usingBlocks.clear();
		onlyStore = null;
		onlyBlock = null;
		onlyUsedInOneBlock = true;
	}

	/// AnalyzeAlloca - Scan the uses of the specified alloca, filling in our
	/// ivars.
	public void analyzeAlloca(AllocaInst allocaInst, Function function) {
		clear();

		// As we scan the uses of the alloca instruction, keep track of stores,
		// and decide whether all of the loads and stores to the alloca are within
		// the same basic block.
		int numUsesOfAlloca = allocaInst.getNumUses();
		for(int i = 0; i < numUsesOfAlloca; i++){

			Instruction userInst = (Instruction) allocaInst.getUserAt(i);
			InstructionID instID = userInst.getInstructionID();

			if (instID == InstructionID.STORE_INST) {
				// Remember the basic blocks which define new values for the alloca
				StoreInst storeInst = (StoreInst) userInst;
				definingBlocks.add(storeInst.getParent());
				onlyStore = storeInst;
			} 
			else //if(instID == InstructionID.LOAD_INST) {
			{
				//LoadInst loadInst = (LoadInst)userInst;
				// Otherwise it must be a load instruction, keep track of variable
				// reads.
				//usingBlocks.add(loadInst.getParent());
				usingBlocks.add(userInst.getParent());
			}

			if (onlyUsedInOneBlock) {
				if (onlyBlock == null)
					onlyBlock = userInst.getParent();
				else if (onlyBlock != userInst.getParent())
					onlyUsedInOneBlock = false;
			}
		}

	}

	public int numDefiningBlocks(){
		return definingBlocks.size();
	}

	public void clearUsingBlocks(){
		usingBlocks.clear();
	}

	public StoreInst getOnlyStore(){
		return onlyStore;
	}

	public void addUsingBlock(BasicBlock usingBB){
		usingBlocks.add(usingBB);
	}

	public boolean usingBlocksAreEmpty(){
		return usingBlocks.size() == 0;
	}

	public boolean isUsedOnlyInOneBlock(){
		return onlyUsedInOneBlock;
	}

	public Vector<BasicBlock> getDefiningBlocks(){
		return definingBlocks;
	}

	public Vector<BasicBlock> getUsingBlocks() {
		return usingBlocks;
	}

}

/**
 * This class represents an optimization pass that identifies allocation instructions
 * and promotes them if possible. 
 * @author shreesha
 *
 */
public class MemToRegPromoter {

	private int numSingleStore;    // Number of alloca's promoted with a single store
	private int numLocalPromoted;   // Number of alloca's promoted within one block

	private int numDeadAllocas;
	private DominatorTree dominatorTree;

	private LLVMIREmitter emitter;

	public Transformations execute(Function function){
		
		if(function.getNumBasicBlocks() == 0){
			// Must be a declaration
			return null;
		}

		emitter = LLVMIREmitter.getInstance();

		// Get the dominator tree for the given CFG. 
		DominatorCalculationContext dominatorCalculationContext = 
			new DominatorCalculationContext(function);
		dominatorCalculationContext.setLengauerTarjan(null);
		dominatorTree = dominatorCalculationContext.getDominatorTree();

		// Get the entry block of the CFG
		BasicBlock entryBB = function.getStartNode();

		// Identify all allocations that can be promoted


		while(true){
			Vector<AllocaInst> allocasThatCanBePromoted = new Vector<AllocaInst>();
			Iterator<Instruction> instrsInEntryBB = entryBB.getInstructions();
			while(instrsInEntryBB.hasNext()){
				Instruction instr = instrsInEntryBB.next();
				if(instr.getInstructionID() != InstructionID.ALLOCA_INST)
					continue;

				AllocaInst allocInst = (AllocaInst) instr;
				if(canBePromotedToReg(allocInst))
					allocasThatCanBePromoted.add(allocInst);
			}

			if(allocasThatCanBePromoted.isEmpty())
				break;

			promoteAllocas(allocasThatCanBePromoted, function);
		}

		return null;
	}

	private void promoteAllocas(Vector<AllocaInst> allocasThatCanBePromoted, Function function){

		List<AllocaInst> removedAllocas = new ArrayList<AllocaInst>();

		AllocInfo info = new AllocInfo();

		Vector<AllocaInst> nonTrivialAllocasThatCanBePromoted = new  Vector<AllocaInst>();
		HashMap<AllocaInst, Vector<BasicBlock>> nonTrivalAllocasAndDefBlocks = new HashMap<AllocaInst, Vector<BasicBlock>>();
		HashMap<AllocaInst, Vector<BasicBlock>> nonTrivalAllocasAndUseBlocks = new HashMap<AllocaInst, Vector<BasicBlock>>();

		for (AllocaInst allocaInst : allocasThatCanBePromoted) {

			removeLifetimeIntrinsicUsers(allocaInst);

			if (allocaInst.getNumUses() == 0) {
				// If there are no uses of the alloca, just delete it now.
				allocaInst.eraseFromParent();

				// Remove the alloca from the Allocas list, since it has been processed
				++numDeadAllocas;
				continue;
			}

			// Calculate the set of read and write-locations for each alloca.  This is
			// analogous to finding the 'uses' and 'definitions' of each variable.
			info.analyzeAlloca(allocaInst, function);

			// If there is only a single store to this value, replace any loads of
			// it that are directly dominated by the definition with the value stored.
			if (info.numDefiningBlocks() == 1) {

				rewriteSingleStoreAlloca(allocaInst, function, info);

				// Finally, after the scan, check to see if the store is all that is left.
				if (info.usingBlocksAreEmpty()) {

					// Remove the (now dead) store and alloca.
					if(info.getOnlyStore().toString().contains("%40 = getelementptr inbounds %struct.student, %struct.student* %st, i32 0, i32 1")) {
						System.out.println("WAIT HERE");
					}
					info.getOnlyStore().eraseFromParent();
					allocaInst.eraseFromParent();

					// The alloca has been processed, move on.
					removedAllocas.add(allocaInst);
					++numSingleStore;
					continue;
				}
			}

			// If the alloca is only read and written in one basic block, just perform a
			// linear sweep over the block to eliminate it.
			if (info.isUsedOnlyInOneBlock()) {
				promoteSingleBlockAlloca(allocaInst, function, info);

				// Finally, after the scan, check to see if the stores are all that is
				// left.
				if(info.usingBlocksAreEmpty()) {
					// Remove the (now dead) stores and alloca.

					int numUsesOfAlloca = allocaInst.getNumUses();
					List<StoreInst> storesToBeRemoved = new ArrayList<StoreInst>();

					for(int i = 0; i < numUsesOfAlloca; i++){
						Instruction userInst = (Instruction) allocaInst.getUserAt(i);
						InstructionID userInstID = userInst.getInstructionID();
						if(userInstID == InstructionID.STORE_INST){
							StoreInst storeInst = (StoreInst) userInst;
							storesToBeRemoved.add(storeInst);
						}
					}

					for(StoreInst storeInst : storesToBeRemoved){
						storeInst.eraseFromParent();
					}

					allocaInst.eraseFromParent();

					// The alloca has been processed, move on.
					removedAllocas.add(allocaInst);
					++numLocalPromoted;
					continue;
				}
			}

			// This alloca is non-trivial; it has stores and loads across basic
			// blocks. Add these to the list of non-trivial allocas and also
			// add to the their list of defining and using blocks

			nonTrivialAllocasThatCanBePromoted.add(allocaInst);

			Vector<BasicBlock> defBlocks = new Vector<BasicBlock>(info.getDefiningBlocks());
			Vector<BasicBlock> useBlocks = new Vector<BasicBlock>(info.getUsingBlocks());

			nonTrivalAllocasAndDefBlocks.put(allocaInst, defBlocks);
			nonTrivalAllocasAndUseBlocks.put(allocaInst, useBlocks);
		}
		
		printIntermediate("After promoting allocas ", function);


		if (nonTrivialAllocasThatCanBePromoted.size() == 0)
			return; // All of the allocas must have been trivial!

		// Some of the allocas that can be promoted are NOT trivial; 
		// lets remove them and replace those values with phi nodes.
		HashMap<BasicBlock, HashSet<BasicBlock>> dominanceFrontiers = 
			GeneralUtilities.getDominanceFrontiers(function, dominatorTree);

		HashMap<AllocaInst, PriorityQueue<BasicBlock>> allocasAndInsertionPoints
		= determineInsertionPoints(nonTrivialAllocasThatCanBePromoted, dominanceFrontiers,
				nonTrivalAllocasAndDefBlocks, nonTrivalAllocasAndUseBlocks,
				function);
		
		
		insertPhiFunctions(nonTrivialAllocasThatCanBePromoted, allocasAndInsertionPoints, function);
		renameVariables(nonTrivialAllocasThatCanBePromoted, function);
	}

	private void printIntermediate(String msg, Function function) {
		System.out.println(msg);
		LLVMIRUtils llvmirUtils = new LLVMIRUtils();
		LLVMIREmitter emitter = llvmirUtils.getEmitter();
		emitter.reset();
		List<String> instrsAfterOpt = emitter.emitInstructions(function.getParent());
		llvmirUtils.printAsm(instrsAfterOpt);
		
	}

	private void insertPhiFunctions(
			Vector<AllocaInst> allocasThatCanBePromoted,
			HashMap<AllocaInst, PriorityQueue<BasicBlock>> allocasAndInsertionPoints,
			Function function) {

		HashMap<Value, Integer> phiNodeBaseVsCount = new HashMap<Value, Integer>();

		// Using a worklist, for each node containing the definition of a variable, 
		// insert phi functions in dominance frontiers of that node
		int numAllocas = allocasThatCanBePromoted.size();
		for(int i = 0; i < numAllocas ; i++){

			AllocaInst variable = allocasThatCanBePromoted.elementAt(i);

			PriorityQueue<BasicBlock> insertionPoints = allocasAndInsertionPoints.get(variable);

			while(!insertionPoints.isEmpty()) {

				// Add this node to the list of control flow nodes containing SSA phi nodes
				BasicBlock frontierNode = insertionPoints.remove();

				int numPreds = function.getCfg().incomingEdgesOf(frontierNode).size();
				PointerType ptrType = (PointerType)variable.getType();

				String newPhiNodeName = getNewPhiNodeName(phiNodeBaseVsCount, variable);

				PhiNode phiNode = PhiNode.create(ptrType.getContainedType(),
						newPhiNodeName, numPreds, variable, (BasicBlock) frontierNode);	
				phiNode.setParent((BasicBlock)frontierNode);
				
				List<BasicBlock> predecessorsOfFrontierNode = function.getCfg().getPredecessors(frontierNode);
				for(BasicBlock node : predecessorsOfFrontierNode){
					BasicBlock predecessor = (BasicBlock)node;
					
					try{
						Value tempValue = new Value(ptrType.getContainedType());
						phiNode.addIncoming(tempValue, predecessor);
					}
					catch(Exception e){
						e.printStackTrace();
						System.exit(-1);
					}
				}

				// Insert this phi node at the beginning of the basic block
				frontierNode.insertInstructionAt(phiNode, 0);
			}
		}
	}

	private HashMap<AllocaInst, PriorityQueue<BasicBlock>> determineInsertionPoints(
			Vector<AllocaInst> allocasThatCanBePromoted,
			HashMap<BasicBlock, HashSet<BasicBlock>> dominanceFrontiers,
			HashMap<AllocaInst, Vector<BasicBlock>> nonTrivalAllocasAndDefBlocks,
			HashMap<AllocaInst, Vector<BasicBlock>> nonTrivalAllocasAndUseBlocks,
			Function function) {

		HashMap<AllocaInst, PriorityQueue<BasicBlock>> allocasAndInsertionPoints 
		= new HashMap<AllocaInst, PriorityQueue<BasicBlock>>();

		// Using a worklist, for each node containing the definition of a variable, 
		// insert phi functions in dominance frontiers of that node
		int numAllocas = allocasThatCanBePromoted.size();
		for(int i = 0; i < numAllocas; i++){

			AllocaInst variable = allocasThatCanBePromoted.elementAt(i);

			// Get the worklist of nodes in which this variable is defined
			//			PriorityQueue<BasicBlock> insertionQueue =
			//				new PriorityQueue<BasicBlock>(10, new DomLevelComparator(domLevelNodes));

			PriorityQueue<BasicBlock> insertionQueue =
				new PriorityQueue<BasicBlock>(10, new DomLevelComparator(function));

			HashSet<BasicBlock> workList = new HashSet<BasicBlock>();

			int numUsesOfAlloca = variable.getNumUses();
			for(int j = 0; j < numUsesOfAlloca;j++){

				Instruction userInst = (Instruction) variable.getUserAt(j);
				InstructionID instID = userInst.getInstructionID();

				if (instID != InstructionID.STORE_INST) 
					continue;

				// Remember the basic blocks which define new values for the alloca
				BasicBlock parentOfDefiningIns = userInst.getParent();

				workList.add(parentOfDefiningIns);
			}

			Set<BasicBlock> visitedNodes = new HashSet<BasicBlock>();
			while(!workList.isEmpty()) {
				Iterator<BasicBlock> iterator = workList.iterator();
				BasicBlock defNode = iterator.next();
				workList.remove(defNode);
				visitedNodes.add(defNode);

				// Insert phi functions for each node in the dominance frontier of defNode
				HashSet<BasicBlock> dominanceFrontierOfNode = dominanceFrontiers.get(defNode);
				if(dominanceFrontierOfNode == null) 
					continue;

				Iterator<BasicBlock> dfIter = dominanceFrontierOfNode.iterator();
				while(dfIter.hasNext()){
					BasicBlock frontierNode =  dfIter.next();

					Set<BasicBlock> liveInBlocks = new HashSet<BasicBlock>();

					computeLiveInBlocks(variable,
							nonTrivalAllocasAndUseBlocks.get(variable),
							new HashSet<BasicBlock>
					(nonTrivalAllocasAndDefBlocks.get(variable)), 
					liveInBlocks, function);

					// If the frontier node is not in the "live-in" list for this
					// alloca, no need to create a phi node for this.
					if(!liveInBlocks.contains(frontierNode))
						continue;

					// Add the frontier node to the work-list (if it already exists, it will not be added)
					BasicBlock frontierBB = (BasicBlock) frontierNode;
					if(!visitedNodes.contains(frontierBB)){
						workList.add(frontierBB);
					}

					if(!insertionQueue.contains(frontierBB))
						insertionQueue.add(frontierBB);

				}
			}

			allocasAndInsertionPoints.put(variable, insertionQueue);
		}

		return allocasAndInsertionPoints;
	}

	/**
	 * RewriteSingleStoreAlloca - If there is only a single store to this value,
	 * replace any loads of it that are directly dominated by the definition with
	 * the value stored.
	 * @param allocaInst
	 */

	private void rewriteSingleStoreAlloca(AllocaInst allocaInst, Function function, AllocInfo info){
		StoreInst onlyStore = info.getOnlyStore();
		boolean isStoringGlobalVal = !(onlyStore.getOperand(0) instanceof Instruction);
		BasicBlock storeBB = onlyStore.getParent();

		// Clear out UsingBlocks.  We will reconstruct it here if needed.
		info.clearUsingBlocks();

		int numUsesOfAlloca = allocaInst.getNumUses();

		HashMap<LoadInst, Value> removableLoadsAndReplacementVals 
		= new HashMap<LoadInst, Value>();

		for(int i = 0; i < numUsesOfAlloca; i++){
			Instruction userInst = (Instruction) allocaInst.getUserAt(i);
			InstructionID userInstID = userInst.getInstructionID();
			if(userInstID != InstructionID.LOAD_INST){
				if(userInst != onlyStore){
					// TODO this is an error, throw an exception
				}
				continue;
			}

			LoadInst loadInst = (LoadInst)userInst;

			// Okay, if we have a load from the alloca, we want to replace it with the
			// only value stored to the alloca.  We can do this if the value is
			// dominated by the store.  If not, we use the rest of the mem2reg machinery
			// to insert the phi nodes as needed.
			if (!isStoringGlobalVal) {  
				// Non-instructions are always dominated.
				if (loadInst.getParent() == storeBB) {
					// We have a use that is in the same block as the store, compare the
					// indices of the two instructions to see which one came first.  If the
					// load came before the store, we can't handle it.

					int storeIndex = storeBB.getInstructionIndex(onlyStore);
					int loadIndex = storeBB.getInstructionIndex(loadInst);

					if (storeIndex > loadIndex) {
						// Can't handle this load, bail out.
						info.addUsingBlock(storeBB);
						continue;
					}
				}
				else if (loadInst.getParent() != storeBB &&
						!dominatorTree.dominates(storeBB,  loadInst.getParent())) {
					// The load and store are in different blocks, use BB dominance to
					// check their relationships.  If the store doesn't dom the use, bail
					// out.
					info.addUsingBlock(loadInst.getParent());
					continue;
				}
			}

			// Otherwise, we *can* safely rewrite this load.
			Value replaceValue = onlyStore.getOperand(0);
			// If the replacement value is the load, this must occur in unreachable
			// code.
			if (replaceValue == loadInst){
				replaceValue = UndefValue.createOrGet(loadInst.getType());
			}

			removableLoadsAndReplacementVals.put(loadInst, replaceValue);

		}

		// Replace the uses of the removable load instructions with the values
		// determined and remove the load instructions
		Set<LoadInst> removableLoadsSet = 
			removableLoadsAndReplacementVals.keySet();
		Iterator<LoadInst> iterator = removableLoadsSet.iterator();
		while(iterator.hasNext()){
			LoadInst loadInst = iterator.next();
			Value replaceValue = 
				removableLoadsAndReplacementVals.get(loadInst);

			loadInst.replaceAllUsesOfThisWith(replaceValue);
			loadInst.eraseFromParent();
		}
	}

	/**
	 * PromoteSingleBlockAlloca - Many allocas are only used within a single basic
	 * block.  If this is the case, avoid traversing the CFG and inserting a lot of
	 * potentially useless PHI nodes by just performing a single linear pass over
	 * the basic block using the Alloca.
	 * 
	 * If we cannot promote this alloca (because it is read before it is written),
	 * return true.  This is necessary in cases where, due to control flow, the
	 * alloca is potentially undefined on some control flow paths.  e.g. code like
	 * this is potentially correct:
	 *
	 *   for (...) { if (c) { A = undef; undef = B; } }
	 *
	 * ... so long as A is not used before undef is set.
	 */

	private void promoteSingleBlockAlloca(AllocaInst allocaInst, Function function, AllocInfo info) {

		// Get that single block
		BasicBlock singleBlock = allocaInst.getParent();

		// Clear out UsingBlocks.  We will reconstruct it here if needed.
		info.clearUsingBlocks();

		// Gather all the store instructions
		List<StoreInst> storeInstUses = new ArrayList<StoreInst>();
		int numUsesOfAlloca = allocaInst.getNumUses();
		for(int i = 0; i < numUsesOfAlloca; i++){
			Instruction userInst = (Instruction) allocaInst.getUserAt(i);
			InstructionID userInstID = userInst.getInstructionID();
			if(userInstID == InstructionID.STORE_INST){
				storeInstUses.add((StoreInst) userInst); 
			}
		}

		// If there are no stores to the alloca, just replace any loads with undef.
		if (storeInstUses.size() == 0) {
			// Get all uses of this alloca that are loads
			List<LoadInst> loadUses = new ArrayList<LoadInst>();
			for(int i = 0; i < numUsesOfAlloca; i++){
				Instruction userInst = (Instruction) allocaInst.getUserAt(i);
				
				InstructionID userInstID = userInst.getInstructionID();

				if (userInstID == InstructionID.LOAD_INST) {
					LoadInst loadInst = (LoadInst) userInst;
					loadUses.add(loadInst);
				}
			}

			// Remove them
			for(LoadInst loadInst : loadUses){
				loadInst.replaceAllUsesOfThisWith(UndefValue.createOrGet(loadInst.getType()));
				loadInst.eraseFromParent();
			}
			return;
		}

		// Walk all of the loads from this alloca, replacing them with the nearest
		// store above them, if any.

		HashMap<LoadInst, Value> loadInstKilledAndReplaceVals =
			new HashMap<LoadInst, Value>();

		for(int i = 0; i < numUsesOfAlloca; i++){

			Instruction userInst = (Instruction) allocaInst.getUserAt(i);

			InstructionID userInstID = userInst.getInstructionID();
			if (userInstID != InstructionID.LOAD_INST) 
				continue;

			LoadInst loadInst = (LoadInst) userInst;
			int loadInstIndex = singleBlock.getInstructionIndex(loadInst);

			// Find the nearest store that has a lower index than this load. 
			StoreInst closestStoreInst = null;
			int indexOfClosestStore = -1;
			for(StoreInst storeInst : storeInstUses){
				int indexOfStoreInst = singleBlock.getInstructionIndex(storeInst);
				if(indexOfStoreInst > loadInstIndex)
					break;
				if(indexOfStoreInst > indexOfClosestStore){
					closestStoreInst = storeInst;
					indexOfClosestStore = indexOfStoreInst;
				}
			}

			// If there is no store before this load, then we can't promote this load.
			if (indexOfClosestStore == -1) {
				// Can't handle this load, bail out.
				info.addUsingBlock(singleBlock);
				continue;
			}

			// Otherwise, there was a store before this load, the load takes its value.
			//loadInst.replaceAllUsesOfThisWith(closestStoreInst.getOperand(0));
			//loadInst.eraseFromParent();
			loadInstKilledAndReplaceVals.put(loadInst, closestStoreInst.getOperand(0));

		}

		Set<LoadInst> loadInstrsToBeRemoved = loadInstKilledAndReplaceVals.keySet();
		Iterator<LoadInst> iter = loadInstrsToBeRemoved.iterator();
		while(iter.hasNext()){
			LoadInst li = iter.next();
			Value replaceVal = loadInstKilledAndReplaceVals.get(li);
			li.replaceAllUsesOfThisWith(replaceVal);
			li.eraseFromParent();
		}

	}

	private void renameVariables(Vector<AllocaInst> allocasThatCanBePromoted,
			Function function) {

		HashMap<AllocaInst, Stack<Value>> nearestLiveValuesOfVariables = 
			new HashMap<AllocaInst, Stack<Value>>();
		HashMap<Value, Integer> phiNodeBaseVsCount = new HashMap<Value, Integer>();

		DominatorTreeNode root = dominatorTree.getDominatorTreeRoot();
		rename(root, allocasThatCanBePromoted, function, nearestLiveValuesOfVariables,
				phiNodeBaseVsCount);
	}

	private void rename(DominatorTreeNode dominatorTreeNode,
			Vector<AllocaInst> allocasThatCanBePromoted, Function function, HashMap<AllocaInst, 
			Stack<Value>> nearestLiveValuesOfVariables, 
			HashMap<Value, Integer> phiNodeBaseVsCount) {

		BasicBlock cfNode = (BasicBlock)dominatorTreeNode.getGraphNode();

		Vector<Instruction> instrsToBeRemoved = new Vector<Instruction>();

		Iterator<Instruction> instructions = cfNode.getInstructions();
		while(instructions.hasNext()){
			Instruction instruction = instructions.next();
			
			InstructionID instId = instruction.getInstructionID();

			if(instId == InstructionID.ALLOCA_INST){
				if(allocasThatCanBePromoted.contains(instruction))
					instrsToBeRemoved.add(instruction);
			}
			else if(instId == InstructionID.STORE_INST){
				StoreInst storeInst = (StoreInst) instruction;

				Value storeTarget = storeInst.getOperand(1);
				if(allocasThatCanBePromoted.contains(storeTarget)){
					instrsToBeRemoved.add(storeInst);
					AllocaInst allocaForStore = (AllocaInst) storeTarget;
					Value storeValue = storeInst.getOperand(0);
					Stack<Value> nearestValueStackForVariable =
						nearestLiveValuesOfVariables.get(storeTarget);
					if(nearestValueStackForVariable == null)
						nearestValueStackForVariable = new Stack<Value>();

					nearestValueStackForVariable.push(storeValue);
					nearestLiveValuesOfVariables.put(allocaForStore,
							nearestValueStackForVariable);
				}
			}
			else if(instId == InstructionID.LOAD_INST){
				LoadInst loadInst = (LoadInst) instruction;
				Value storeTarget = loadInst.getOperand(0);
				if(allocasThatCanBePromoted.contains(storeTarget)){
					instrsToBeRemoved.add(loadInst);
					AllocaInst allocaForStore = (AllocaInst) storeTarget;
					Stack<Value> nearestValueStack = 
						nearestLiveValuesOfVariables.get(allocaForStore);

					Value val = null;
					if(nearestValueStack == null){
						// A load without a store; lets consider
						// it as an undef
						val = UndefValue.createOrGet(loadInst.getType());
					}
					else{
						val = nearestValueStack.peek();
					}

					loadInst.replaceAllUsesOfThisWith(val);
				}
			}
			else if(instId == InstructionID.PHI_NODE_INST){
				PhiNode phiNode = (PhiNode) instruction;
				Value primaryValue = phiNode.getPrimaryValue();

				if(allocasThatCanBePromoted.contains(primaryValue)){

					AllocaInst ai = (AllocaInst) primaryValue;
					Stack<Value> nearestValueStackForVariable =
						nearestLiveValuesOfVariables.get(ai);
					if(nearestValueStackForVariable == null){
						nearestValueStackForVariable = new Stack<Value>();
						nearestLiveValuesOfVariables.put(ai, nearestValueStackForVariable);
					}

					nearestValueStackForVariable.push(phiNode);
				}
			}
		}

		// If this control flow node has successors, and if some or all successors
		// have phi nodes, update the corresponding parameter of the phi node
		List<BasicBlock> successors = function.getCfg().getSuccessors(cfNode);
		int count = 0;

		for(BasicBlock succNode : successors){
			BasicBlock successor = (BasicBlock) succNode;
			
			Iterator<Instruction> instrsInSuccessor = successor.getInstructions();
			while(instrsInSuccessor.hasNext()){
				Instruction instrInSuccessor = instrsInSuccessor.next();
				
				InstructionID instrID = instrInSuccessor.getInstructionID();
				if(instrID != InstructionID.PHI_NODE_INST)
					break;

				// Update the phi node with the corresponding parameter
				PhiNode phiNode = (PhiNode) instrInSuccessor;

				Value primaryValueForPhiNode = phiNode.getPrimaryValue();
				if(allocasThatCanBePromoted.contains(primaryValueForPhiNode)){
					AllocaInst ai = (AllocaInst)primaryValueForPhiNode;

					Value nearestVal = getNearestValue(nearestLiveValuesOfVariables, ai);

					int predecessorIndex = function.getCfg().getIndexOfPredecessor(cfNode, successor);
					phiNode.setPhiOperandAt(predecessorIndex, nearestVal);
				}
			}

			count++;
		}

		// Rename each child of this dominator tree node

		Vector<TreeNode> children = dominatorTreeNode.getChildren();

		for(TreeNode child: children){
			rename((DominatorTreeNode)child, allocasThatCanBePromoted, function, 
					nearestLiveValuesOfVariables, phiNodeBaseVsCount);
		}

		// Pop off the stack
		instructions = cfNode.getInstructions();
		while(instructions.hasNext()){
			Instruction instr = instructions.next();
			InstructionID instrID = instr.getInstructionID();
			if(instrID != InstructionID.PHI_NODE_INST && instrID != 
				InstructionID.STORE_INST)
				continue;

			AllocaInst allocaInst = null;
			if(instrID == InstructionID.PHI_NODE_INST){
				PhiNode phiNode = (PhiNode) instr;
				Value primaryValue = phiNode.getPrimaryValue();
				if(allocasThatCanBePromoted.contains(primaryValue))
					allocaInst = (AllocaInst) primaryValue;
			}
			else{
				StoreInst storeInst = (StoreInst) instr; 
				Value storeTarget = storeInst.getOperand(1);
				if(allocasThatCanBePromoted.contains(storeTarget))
					allocaInst = (AllocaInst) storeTarget;
			}

			if(allocaInst == null)
				continue;

			Stack<Value> valueStackForVariable = nearestLiveValuesOfVariables.get(allocaInst);
			if(!valueStackForVariable.empty())
				valueStackForVariable.pop();
		}

		// Remove the instructions that are marked to be deleted
		for(Instruction instr : instrsToBeRemoved) {
			if(instr.getInstructionID() == InstructionID.STORE_INST) {
				System.out.println("Removing store instruction: ");
				System.out.println(instr);
			}
			instr.eraseFromParent();
		}
			
	}

	private String getNewPhiNodeName(HashMap<Value, Integer> phiNodeBaseVsCount, 
			Value primaryValue) {
		Integer currentCount = phiNodeBaseVsCount.get(primaryValue);
		int count = 0;
		if(currentCount == null){
			currentCount = new Integer(0);
		}
		else{
			count = currentCount.intValue();
			count++;
			currentCount = new Integer(count);
		}
		phiNodeBaseVsCount.put(primaryValue, currentCount);
		String baseName = primaryValue.getName();
		if(baseName == null)
			baseName = "";

		return baseName + "." + count;
	}

	/*
	 * Returns the nearest value associated with an alloca instruction. If
	 * the alloca that is passed does not have a valid value associated with
	 * it in the map of allocas against the stack of values for each alloca,
	 * it is considered as undefined.
	 */
	private Value getNearestValue( HashMap<AllocaInst, 
			Stack<Value>> nearestLiveValuesOfVariables, AllocaInst ai){

		Stack<Value> nearestLiveValue = nearestLiveValuesOfVariables.get(ai);
		
		if(nearestLiveValue == null || nearestLiveValue.empty()){
			// Not defined - create an undef value and return it.
			PointerType ptrType = (PointerType)ai.getType();
			Type typeOfVal = ptrType.getContainedType();
			return UndefValue.createOrGet(typeOfVal);
		}
		else{
			return nearestLiveValue.peek();
		}

	}

	/**
	 * Knowing that this alloca is promotable, we know that it's safe to kill all
	 * instructions EXCEPT for load and store.
	 * @param allocaInst
	 */
	private void removeLifetimeIntrinsicUsers(AllocaInst allocaInst){

		int numUses = allocaInst.getNumUses();
		for (int i = 0; i < numUses; i++) {
			Instruction useInst = (Instruction) allocaInst.getUserAt(i);

			InstructionID useInstID = useInst.getInstructionID();
			if (useInstID == InstructionID.STORE_INST || useInstID == InstructionID.LOAD_INST)
				continue;

			// Not a load or a store
			Type useInstType = useInst.getType();
			CompilationContext compilationContext = useInstType.getCompilationContext();
			if(useInstType != Type.getVoidType(compilationContext)) 
				continue;

			// The only users of this bitcast/GEP instruction are lifetime intrinsics.
			// Follow the use/def chain to erase them now instead of leaving it for
			// dead code elimination later.

			int numUsesOfUse = useInst.getNumUses();
			for (int j = 0; j < numUsesOfUse; j++) {
				Instruction useOfUseInst = (Instruction)useInst.getUserAt(j);
				useOfUseInst.eraseFromParent();
			}

			useInst.eraseFromParent();
		}
	}

	/**
	 * Return true if this alloca is legal for promotion. This is true if there
	 * are only loads and stores to the alloca.
	 */
	private boolean canBePromotedToReg(AllocaInst allocInst){

		int numUses = allocInst.getNumUses();
		for(int i = 0; i < numUses; i++){
			Instruction userInst = (Instruction)allocInst.getUserAt(i);
			InstructionID instType = userInst.getInstructionID();

			if(instType == InstructionID.LOAD_INST){
				LoadInst loadInst = (LoadInst) userInst;
				if(loadInst.isVolatile())
					return false;
			}
			else if(instType == InstructionID.STORE_INST){
				StoreInst storeInst = (StoreInst) userInst;
				// The alloc instruction must be the second operand of the store inst
				if(storeInst.getOperand(0) == allocInst)
					return false;
				if(storeInst.isVolatile())
					return false;
			}
			//else if(instType == InstructionID.INTRINSIC){
			// TODO Implement this
			//}
			else if(instType == InstructionID.BIT_CAST_INST){
				CastInst castInst = (CastInst) userInst;
				Type castInstType = castInst.getType();
				CompilationContext cc = castInst.getContext();
				if(!(castInstType instanceof PointerType))
					return false;
				PointerType ptrType = (PointerType) castInstType;
				Type containedType = ptrType.getContainedType();
				if(containedType != Type.getInt8Type(cc, true))
					return false;

				// TODO What is onlyUsedByLifetimeMarkers?
				//if (!onlyUsedByLifetimeMarkers(castInst))
					return false;
			}
			else if (instType == InstructionID.GET_ELEMENT_PTR) {
				GetElementPtrInst gepi = (GetElementPtrInst) userInst;
				Type gepiType = gepi.getType();
				CompilationContext cc = gepi.getContext();
				if(!(gepiType instanceof PointerType))
					return false;
				PointerType ptrType = (PointerType) gepiType;
				Type containedType = ptrType.getContainedType();
				if(containedType != Type.getInt8Type(cc, true))
					return false;

				if (!gepi.hasAllZeroIndices())
					return false;
				// TODO What is onlyUsedByLifetimeMarkers?
				//if (!onlyUsedByLifetimeMarkers(gepi))
					return false;
			}
			else {
				return false;
			}

		}

		return true;
	}

	/** ComputeLiveInBlocks - Determine which blocks the value is live in.  These
	 ** are blocks which lead to uses.  Knowing this allows us to avoid inserting
	 ** PHI nodes into blocks which don't lead to uses (thus, the inserted phi nodes
	 ** would be dead).
	 **
	 */

	private void computeLiveInBlocks(AllocaInst allocaInst, 
			Vector<BasicBlock> usingBlocks, 
			Set<BasicBlock> defBlocks,
			Set<BasicBlock> liveInBlocks, Function function) {

		// To determine liveness, we must iterate through the predecessors of blocks
		// where the def is live.  Blocks are added to the worklist if we need to
		// check their predecessors.  Start with all the using blocks.

		Vector<BasicBlock> liveInBlockWorklist = 
			new Vector<BasicBlock>(usingBlocks);

		// If any of the using blocks is also a definition block, check to see if the
		// definition occurs before or after the use.  If it happens before the use,
		// the value isn't really live-in.
		for (int i = 0, e = liveInBlockWorklist.size(); i != e; ++i) {
			BasicBlock bb = liveInBlockWorklist.elementAt(i);

			if (!defBlocks.contains(bb))
				continue;

			// Okay, this is a block that both uses and defines the value.  If the first
			// reference to the alloca is a def (store), then we know it isn't live-in.
			Iterator<Instruction> instructionsInBB = bb.getInstructions();
			while ( instructionsInBB.hasNext()) {
				Instruction ins = instructionsInBB.next();
				InstructionID instrTypeID = ins.getInstructionID();
				if (instrTypeID == InstructionID.STORE_INST) {
					StoreInst storeInst = (StoreInst) ins;
					if (storeInst.getOperand(1) != allocaInst) 
						continue;

					// We found a store to the alloca before a load.  The alloca is not
					// actually live-in here.
					liveInBlockWorklist.remove(bb);
					--i; --e;
					break;
				}

				if (instrTypeID == InstructionID.LOAD_INST) {
					LoadInst loadInst = (LoadInst) ins;

					if (loadInst.getOperand(0) != allocaInst) 
						continue;

					// Okay, we found a load before a store to the alloca.  It is actually
					// live into this block.
					break;
				}
			}
		}

		// Now that we have a set of blocks where the phi is live-in, recursively add
		// their predecessors until we find the full region the value is live.
		while (!liveInBlockWorklist.isEmpty()) {
			BasicBlock bb = liveInBlockWorklist.remove(0);

			// The block really is live in here, insert it into the set.  If already in
			// the set, then it has already been processed.
			if (!liveInBlocks.add(bb))
				continue;

			// Since the value is live into BB, it is either defined in a predecessor or
			// live into it to.  Add the preds to the worklist unless they are a
			// defining block.
			List<BasicBlock> predNodes = function.getCfg().getPredecessors(bb);

			for (BasicBlock predNode : predNodes) {
				BasicBlock pred = (BasicBlock) predNode;

				// The value is not live into a predecessor if it defines the value.
				if (defBlocks.contains(pred))
					continue;

				// Otherwise it is, add to the worklist.
				liveInBlockWorklist.add(pred);
			}
		}
	}
}

/**
 * A comparator class that implements a comparator based on the
 * order of the sequence of nodes in the CFG.
 * 
 */
class DomLevelComparator implements Comparator<BasicBlock>
{
	private Function function;

	public DomLevelComparator(Function function){
		this.function = function;
	}

	@Override
	public int compare(BasicBlock node, BasicBlock otherNode) {

		Map<BasicBlock, Integer> bbNumbering = function.getBBNumbering();

		Integer nodeLevel = bbNumbering.get(node);
		Integer otherNodeLevel = bbNumbering.get(otherNode);

		if (nodeLevel < otherNodeLevel) {
			return -1;
		}
		else if (nodeLevel > otherNodeLevel) {
			return 1;
		}

		return 0;
	}
}
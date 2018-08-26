package org.tamedragon.common.dataflowanalysis;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgraph.graph.DefaultEdge;
import org.jgrapht.traverse.DepthFirstIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tamedragon.common.llvmir.instructions.Instruction;
import org.tamedragon.common.llvmir.instructions.LoadInst;
import org.tamedragon.common.llvmir.instructions.StoreInst;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.Value;
import org.tamedragon.common.llvmir.utils.LLVMIREmitter;

/**
 * This class implements a simple data flow algorithm on loads and stores using the concept
 * of reaching definitions. It answers the questions like:
 * 
 * 1. Whether two load statements that load from the same pointer have an intervening store
 *    to that pointer.
 *    
 * 2. Given a store statement, which load statements are effected by the store.
 *   
 * This is implemented as a bit-vector forward data flow algorithm.
 */

public class LoadStoreAnalysis {

	private Function function;
	private boolean terminateFixedPointIteration;

	private Map<Instruction, BitSet> programPointAndInfoIn;
	private Map<Instruction, BitSet> programPointAndInfoOut;
	private Map<BasicBlock, BitSet> basicBlockAndInfoOut;

	private Map<StoreInst, Integer> storeInstAndIndex;

	Map<Instruction, BitSet> gen = new HashMap<Instruction, BitSet>();
	Map<Instruction, BitSet> kill = new HashMap<Instruction, BitSet>();

	// Addresses and corresponding store instructions
	Map<Value, List<StoreInst>> addressAndStoreInstrs;
	
	private BitSet infoForStartNode;

	LLVMIREmitter emitter = LLVMIREmitter.getInstance();
	private static final Logger LOG = LoggerFactory.getLogger(LoadStoreAnalysis.class);

	public LoadStoreAnalysis(Function function){
		this.function = function;

		programPointAndInfoIn = new HashMap<Instruction, BitSet>();
		programPointAndInfoOut = new HashMap<Instruction, BitSet>();
		basicBlockAndInfoOut = new HashMap<BasicBlock, BitSet>();

		storeInstAndIndex = new HashMap<StoreInst, Integer>();
	}

	public void analyze(){

		initializeGenAndKill();

		int iterationCount = 0;

		do {
			
			terminateFixedPointIteration = true;

			DepthFirstIterator<BasicBlock, DefaultEdge> dfsIterator = 
				new DepthFirstIterator<BasicBlock, DefaultEdge>(function.getCfg(), function.getStartNode()); 
			
			while(dfsIterator.hasNext()){
				visitNode(dfsIterator.next());
			}

			iterationCount++;
			if(LOG.isDebugEnabled()){
				printInfoIn(iterationCount);
			}

		}while(!terminateFixedPointIteration);
	}

	private void visitNode(BasicBlock basicBlock) {
		BitSet oldInfoOut = basicBlockAndInfoOut.get(basicBlock);
		if(oldInfoOut != null){
			oldInfoOut = (BitSet) oldInfoOut.clone();
		}

		// Merge data from predecessors to get pointer information.
		BitSet dataIn = mergePredecessorData(basicBlock);

		// Use the merged data from predecessors to model this basic block. The output
		// will be data in for successors of this basic block
		Iterator<Instruction> instructions = basicBlock.getInstructions();
		while(instructions.hasNext()){
			Instruction instruction = instructions.next();
			dataIn = visitInstruction(instruction, dataIn);
		}

		// Update the store info out so successors will use them as data in
		basicBlockAndInfoOut.put(basicBlock, dataIn);

		if(!dataIn.equals(oldInfoOut)){
			terminateFixedPointIteration = false;
		}
	}

	private void printInfoIn(int iterationCount) {
		
		LOG.debug("\nVALUES AFTER ITERATION {} : [", iterationCount);
		
		Set<StoreInst> stores = storeInstAndIndex.keySet();
		List<StoreInst> storeList = new ArrayList<StoreInst>();
		Iterator<StoreInst> iter = stores.iterator();
		while(iter.hasNext()){
			StoreInst si = iter.next();
			storeList.add(si);
		}

		Iterator<BasicBlock> basicBlockIterator = function.basicBlocksIterator();
		String storeInfoStr = "";
		while(basicBlockIterator.hasNext()) {
			BasicBlock bb = basicBlockIterator.next();
			Iterator<Instruction> instrs = bb.getInstructions();
			while(instrs.hasNext()){
				Instruction instr = instrs.next();
				BitSet info = programPointAndInfoIn.get(instr);

				for(StoreInst si : storeList){
					if(info.get(storeInstAndIndex.get(si))){
						storeInfoStr += si.toString() + ", ";
					}
				}
				storeInfoStr += "]";
			}
		}
		LOG.debug(storeInfoStr);
	}

	private void initializeGenAndKill() {

		int storeInstCount = 0;

		addressAndStoreInstrs = new HashMap<Value, List<StoreInst>>();

		Iterator<BasicBlock> basicBlockIterator = function.basicBlocksIterator();
		while(basicBlockIterator.hasNext()) {
			BasicBlock basicBlock = basicBlockIterator.next();
			Iterator<Instruction> instrs = basicBlock.getInstructions();
			while(instrs.hasNext()){
				Instruction instr = instrs.next();
				if(!(instr instanceof StoreInst)){
					continue;
				}

				StoreInst storeInst = (StoreInst) instr;
				storeInstAndIndex.put(storeInst, storeInstCount);
				Value addressStoredAt = storeInst.getPointerOperand();
				List<StoreInst> storeInstrsForAddress = addressAndStoreInstrs.get(addressStoredAt);
				if(storeInstrsForAddress == null){
					storeInstrsForAddress = new ArrayList<StoreInst>();
				}
				storeInstrsForAddress.add(storeInst);
				addressAndStoreInstrs.put(addressStoredAt, storeInstrsForAddress);

				storeInstCount++;

			}
		}

		Set<Map.Entry<StoreInst,Integer>> entrySet = storeInstAndIndex.entrySet();
		Iterator<Map.Entry<StoreInst,Integer>> iterator = entrySet.iterator();

		while(iterator.hasNext()){
			Map.Entry<StoreInst, Integer> entry = iterator.next();
			StoreInst storeInst = entry.getKey();
			Integer index = entry.getValue();

			BitSet genBitSet = new BitSet(storeInstCount);
			BitSet killBitSet = new BitSet(storeInstCount);
			genBitSet.clear();
			killBitSet.clear();

			genBitSet.set(index);

			Value addressStoredAt = storeInst.getPointerOperand();
			for(StoreInst storeForAddress : addressAndStoreInstrs.get(addressStoredAt)){
				if(storeForAddress == storeInst){
					continue;
				}

				killBitSet.set(storeInstAndIndex.get(storeForAddress));

			}

			gen.put(storeInst, genBitSet);
			kill.put(storeInst, killBitSet);
		}
	}

	public List<StoreInst> getStoresForLoad(LoadInst loadInst){

		List<StoreInst> stores = new ArrayList<StoreInst>();

		Value addressStoredAt = loadInst.getOperand(0);

		BitSet infoAtInst = programPointAndInfoIn.get(loadInst);

		List<StoreInst> storeInstrs = addressAndStoreInstrs.get(addressStoredAt);

		if(storeInstrs != null){
			for(StoreInst si : storeInstrs){
				if(infoAtInst.get(storeInstAndIndex.get(si))){
					stores.add(si);
				}
			}
		}

		return stores;
	}

	private BitSet visitInstruction(Instruction instruction, BitSet dataIn) {
		BitSet currentInfoIn = programPointAndInfoIn.get(instruction);
		if(currentInfoIn == null){
			currentInfoIn = new BitSet();
			programPointAndInfoIn.put(instruction, currentInfoIn);
		}
		BitSet currentInfoOut = programPointAndInfoOut.get(instruction);
		if(currentInfoOut == null){
			currentInfoOut = new BitSet();
			programPointAndInfoOut.put(instruction, currentInfoOut);
		}

		currentInfoIn.or(dataIn);
		if(!(instruction instanceof StoreInst)){
			currentInfoOut.or(dataIn);
			return currentInfoOut;
		}

		BitSet inInfo = (BitSet) currentInfoIn.clone();
		inInfo.andNot(kill.get(instruction));

		BitSet newInfoOut = (BitSet) gen.get(instruction).clone();
		newInfoOut.or(inInfo);
		programPointAndInfoOut.put(instruction, newInfoOut);

		return newInfoOut;
	}

	private BitSet mergePredecessorData(BasicBlock basicBlock){
		BitSet dataInForSuccessor = new BitSet();

		List<BasicBlock> predecessors = function.getCfg().getPredecessors(basicBlock);
		if(predecessors == null || predecessors.size() == 0){
			// Must be the start node; the data will be the same as that of the initial data
			if(infoForStartNode == null){
				initializeInfoForStartNode(basicBlock);
			}
			else{
				dataInForSuccessor = infoForStartNode;
			}

			return dataInForSuccessor;
		}

		if(predecessors.size() == 1){
			// Only one predecessor; data out of that predecessor
			// should be data in for current node
			BitSet dataOutOfPredecessor = basicBlockAndInfoOut.get(predecessors.get(0));
			dataInForSuccessor = (BitSet)dataOutOfPredecessor.clone();
			return dataInForSuccessor;
		}

		// Merge data coming from predecessors
		for(BasicBlock pred : predecessors){
			BitSet dataOutOfPred = basicBlockAndInfoOut.get(pred);
			if(dataOutOfPred == null){
				// The predecessor has not been visited yet
				dataOutOfPred = new BitSet();
			}
			dataInForSuccessor.or(dataOutOfPred);
		}

		return dataInForSuccessor;
	}

	private void initializeInfoForStartNode(BasicBlock startNode){
		Iterator<Instruction> instrsInStartNode = startNode.getInstructions();
		Instruction previousInstruction = null;
		while(instrsInStartNode.hasNext()){
			Instruction instr = instrsInStartNode.next();
			BitSet infoIn = null;
			BitSet infoOut = null;

			// Set the info in first
			if(previousInstruction == null){
				// First instruction
				infoIn = new BitSet(); infoIn.clear();
			}
			else{
				BitSet prevInstrInfoOut = programPointAndInfoOut.get(previousInstruction);
				infoIn = (BitSet)prevInstrInfoOut.clone();
			}
			programPointAndInfoIn.put(instr, infoIn);

			// Set the info out now
			if(instr instanceof StoreInst){
				infoOut = (BitSet) infoIn.clone();
				infoOut.set(storeInstAndIndex.get(instr));
				infoOut.andNot(kill.get(instr));
			}
			else{
				infoOut = (BitSet)infoIn.clone();
			}

			programPointAndInfoOut.put(instr, infoOut);

			infoForStartNode = infoOut;

			previousInstruction = instr; 
		}
	}
}

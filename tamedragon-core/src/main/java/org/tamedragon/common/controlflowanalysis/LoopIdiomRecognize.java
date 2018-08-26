package org.tamedragon.common.controlflowanalysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.tamedragon.common.controlflowanalysis.IncrementingPointerTuple.IncrementingPointerType;
import org.tamedragon.common.dataflowanalysis.LoadStoreAnalysis;
import org.tamedragon.common.llvmir.instructions.CFG;
import org.tamedragon.common.llvmir.instructions.GetElementPtrInst;
import org.tamedragon.common.llvmir.instructions.Instruction;
import org.tamedragon.common.llvmir.instructions.LoadInst;
import org.tamedragon.common.llvmir.instructions.PhiNode;
import org.tamedragon.common.llvmir.instructions.StoreInst;
import org.tamedragon.common.llvmir.instructions.Instruction.InstructionID;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.Value;

/**
 * This class is responsible for identifying common loop idioms like pointer increments. 
 *
 */
public class LoopIdiomRecognize {
	
	private List<LoopInfo> loopInfoList;
	
	private Function function;
	
	private Map<Instruction, IncrementingPointerTuple> instructionAndIncrementingPointers;
	
	private LoadStoreAnalysis loadStoreAnalysis;
	
	public LoopIdiomRecognize(Function function){
		this.function = function;
		
		LoopIdentifier loopIdentifier = new LoopIdentifier(function);
		loopInfoList = loopIdentifier.getLoopInfoList();
		
//		loadStoreAnalysis = new LoadStoreAnalysis(function);
//		loadStoreAnalysis.analyze();
		
		instructionAndIncrementingPointers = new HashMap<Instruction, IncrementingPointerTuple>();
	}
	
	public void identifyingIncrementingPointers(){
		Iterator<BasicBlock> basicBlockIterator = function.basicBlocksIterator();
		while(basicBlockIterator.hasNext()) {
			BasicBlock basicBlock = basicBlockIterator.next();
			Iterator<Instruction> instructions = basicBlock.getInstructions();
			
			while(instructions.hasNext()){
				Instruction instruction  = instructions.next();
				InstructionID instrID = instruction.getInstructionID();
				IncrementingPointerTuple tuple = null;
				if(instrID == InstructionID.STORE_INST){
					tuple = checkForNonConvergence((StoreInst)instruction);
				}
				else if(instrID == InstructionID.PHI_NODE_INST){
					tuple = checkForNonConvergence((PhiNode)instruction);
				}
				
				if(tuple != null){
					markIncrementingPointers(tuple);
				}
			}
		}
	}
	
	private void markIncrementingPointers(IncrementingPointerTuple tuple) {
		if(tuple == null){
			return;
		}
		
		instructionAndIncrementingPointers.put(tuple.getGep(), tuple);
		
		if(tuple.getIncrPtrType() == IncrementingPointerType.PHI_GEP){
			instructionAndIncrementingPointers.put(tuple.getPhiNode(), tuple);
			return;
		}
		
		instructionAndIncrementingPointers.put(tuple.getInitializingStore(), tuple);
		instructionAndIncrementingPointers.put(tuple.getPreIncrementLoad(), tuple);
		instructionAndIncrementingPointers.put(tuple.getPostIncrementStore(), tuple);
	}

	private IncrementingPointerTuple checkForNonConvergence(StoreInst storeInst) {

		Value addressStoredAt = storeInst.getOperand(1);
		int numUsers = addressStoredAt.getNumUses();
		List<LoadInst> loadsForAddress = new ArrayList<LoadInst>();
		for(int i = 0; i < numUsers; i++){
			Value use = addressStoredAt.getUserAt(i);
			if(use instanceof LoadInst){
				loadsForAddress.add((LoadInst)use);
			}
		}

		if(loadsForAddress.size() == 0){
			// No loads on this address
			return null;
		}

		// We have some loads from this address, check if any one of them are 
		// located in loops
		for(LoopInfo loopInfo : loopInfoList){
			List<BasicBlock> nodesInLoop = loopInfo.getNodesInLoop();
			for(BasicBlock node : nodesInLoop){
				BasicBlock basicBlock = (BasicBlock) node;
				for(LoadInst load: loadsForAddress){
					BasicBlock parentOfLoadInst = load.getParent();
					if(basicBlock == parentOfLoadInst){
						
						List<BasicBlock> preHeaders = loopInfo.getPreHeader();
						
						if(preHeaders.contains(storeInst.getParent())){
							GetElementPtrInst gep = getGepFor(load);
							if(gep == null){
								// Does not have a GEP against this address loaded; check for
								// increment by load->add->intotoptr->store
//								IncrementingPointerTuple tuple = getIncrementingPointerThroughInttoPtr(load);
//								if(tuple != null){
//									return tuple;
//								}
								continue;
							}

							if(gep.getNumOperands() > 2){
								// An increment of a pointer cannot have more than two operands
								continue;
							}

							// Check if we are storing this incremented pointer back into the same address
							int numGepUses = gep.getNumUses();
							for(int i = 0; i < numGepUses; i++){
								Value gepUse = gep.getUserAt(i);
								if(!(gepUse instanceof StoreInst)){
									continue;
								}

								StoreInst storeUse = (StoreInst) gepUse;
								if(!nodesInLoop.contains(storeUse.getParent())){
									continue;
								}

								if(storeUse.getPointerOperand() != addressStoredAt){
									// Storing the GEP result at some other address
									continue;
								}

								// Hey, we found a pointer increment!
								IncrementingPointerTuple incrementingPointer = new IncrementingPointerTuple(storeInst, load, gep, storeUse,
										storeInst.getOperand(0), gep.getOperand(1));
								return incrementingPointer;
							}

							break;
						}
					}
				}
			}
		}

		return null;
	}


	/**
	 * Identifies an incrementing pointer in a loop that has the pattern load->load->add->inttoptr->store
	 * @param load
	 * @return
	 */
//	private IncrementingPointerTuple getIncrementingPointerThroughInttoPtr(
//			LoadInst load) {
//		
//		int numUsesOfLoad = load.getNumUses();
//		for(int i = 0; i < numUsesOfLoad; i++){
//			Value useOfLoad = load.getUserAt(i);
//			
//		}
//		
//	}

	private IncrementingPointerTuple checkForNonConvergence(PhiNode phiNode) {

		if(loopInfoList == null){
			// Identify loops lazily
			LoopIdentifier loopIdentifier = new LoopIdentifier(function);
			loopInfoList = loopIdentifier.getLoopInfoList();
		}

		LoopInfo loopInfoForPhiNode = null;
		for(LoopInfo loopInfo : loopInfoList){
			List<BasicBlock> nodesInLoop = loopInfo.getNodesInLoop();
			for(BasicBlock node : nodesInLoop){
				BasicBlock basicBlock = (BasicBlock) node;				
				if(basicBlock.hasInstruction(phiNode)){
					loopInfoForPhiNode = loopInfo;
					break;
				}
			}
		}

		if(loopInfoForPhiNode == null){
			// The phi node definition is not in a loop
			return null;
		}

		List<BasicBlock> nodesInLoop = loopInfoForPhiNode.getNodesInLoop();

		Value valueFromInsideLoop = null;
		Value addressBeingIncremented = null;

		int numOperands = phiNode.getNumOperands();
		if(numOperands != 2){
			// An address being implemented can only have 2 operands in the phi node
			return null;
		}

		try{
			BasicBlock pred1 = phiNode.getIncomingBlock(0);
			Value incomingVal1 = phiNode.getIncomingValue(0);
			BasicBlock pred2 = phiNode.getIncomingBlock(1);
			Value incomingVal2 = phiNode.getIncomingValue(1);
			if(nodesInLoop.contains(pred1)){
				if(!nodesInLoop.contains(pred2)){
					valueFromInsideLoop = incomingVal1;
					addressBeingIncremented = incomingVal2;
				}
				else{
					return null;
				}
			}
			else if(nodesInLoop.contains(pred2)){
				if(!nodesInLoop.contains(pred1)){
					valueFromInsideLoop = incomingVal2;
					addressBeingIncremented = incomingVal1;
				}
				else{
					return null;
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
			System.exit(-1);
		}

		if(!(valueFromInsideLoop instanceof GetElementPtrInst)){
			// Not a GEP
			return null;
		}

		GetElementPtrInst gep = (GetElementPtrInst) valueFromInsideLoop;
		if(gep.getPointerOperand() != phiNode){
			// The address in the GEP is not the phi node under consideration,
			// so this is not in the canonical pointer increment form
			return null;
		}

		Value incrementor = getGepIncrementor(gep);
		if(incrementor == null){
			// The incrementing factor in the GEP is neither a constant nor
			// a loop invariant
			return null;
		}

		// Hey, we found a canonical pointer incrementing function!
		IncrementingPointerTuple incrementingPointer = new IncrementingPointerTuple(phiNode, gep, addressBeingIncremented,
				incrementor);
		return incrementingPointer;
	}
	
	private GetElementPtrInst getGepFor(LoadInst load) {
		// TODO Complete implementation later
		int numUses = load.getNumUses();
		for(int i = 0; i < numUses; i++){
			Value use = load.getUserAt(i);
			if(! (use instanceof GetElementPtrInst)){
				continue;
			}

			GetElementPtrInst gep = (GetElementPtrInst) use;
			if(gep.getPointerOperand() != load){
				continue;
			}

			return gep;
		}

		return null;
	}
	

	private Value getGepIncrementor(GetElementPtrInst gep) {
		if(gep.getNumOperands() == 2){
			Value otherGepOperand = gep.getOperand(1);
			if(otherGepOperand.isAConstant()){
				return otherGepOperand;
			}
			else{
				// TODO Check if this is a loop invariant
				return null;
			}
		}
		else{
			// TODO Implement this
			return null;
		}
	}
	
	public boolean participatesInIncrementingPointer(Instruction instruction){
		if(instructionAndIncrementingPointers.containsKey(instruction)){
			return true;
		}
		
		return false;
	}
	
	public IncrementingPointerTuple getIncrementingPointerTupleForInstruction(Instruction instruction){
		return instructionAndIncrementingPointers.get(instruction);
	}
	
//	public IncrementingPointerTuple getIncrementingPointerTupleForLoadAfterPointerIncrement(LoadInst loadInst){
//		if(instructionAndIncrementingPointers.get(loadInst) != null){
//			return null;
//		}
//		
//		List<StoreInst> storesForLoad = loadStoreAnalysis.getStoresForLoad(loadInst);
//		if(storesForLoad.size() != 1){
//			return null;
//		}
//		
//		StoreInst storeForLoad = storesForLoad.get(0);
//		IncrementingPointerTuple tuple = instructionAndIncrementingPointers.get(storeForLoad);
//		if(tuple == null){
//			return null;
//		}
//		
//		for(LoopInfo loopInfo: loopInfoList){
//			List<BasicBlock> nodesInLoop = loopInfo.getNodesInLoop();
//			if(nodesInLoop.contains(storeForLoad.getParent()) && nodesInLoop.contains(loadInst.getParent())){
//				return tuple;
//			}
//		}
//	
//		return null;
//	}
}

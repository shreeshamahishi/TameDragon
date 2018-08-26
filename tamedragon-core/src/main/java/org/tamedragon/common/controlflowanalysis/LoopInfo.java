package org.tamedragon.common.controlflowanalysis;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Vector;

import org.jgraph.graph.DefaultEdge;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tamedragon.common.llvmir.instructions.CFG;
import org.tamedragon.common.llvmir.instructions.Instruction;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.Value;
import org.tamedragon.common.llvmir.types.Value.ValueTypeID;
import org.tamedragon.common.llvmir.utils.LLVMIREmitter;

public class LoopInfo {

	private DefaultEdge backEdge;
	private List<BasicBlock> nodesInLoop;

	// If this loop contains a header that is shared with
	// headers of other loops, we keep track of back edges
	// of those loops in this list
	private List<DefaultEdge> otherBackEdges;

	private LLVMIREmitter emitter;

	private CFG cfg;
	
	private static final Logger LOG = LoggerFactory.getLogger(LoopInfo.class);

	public LoopInfo(DefaultEdge backEdge, List<BasicBlock> nodesInLoop){
		emitter = LLVMIREmitter.getInstance();
		otherBackEdges = new ArrayList<DefaultEdge>();

		this.backEdge = backEdge;
		this.nodesInLoop = nodesInLoop;

		cfg = nodesInLoop.get(0).getParent().getCfg();
	}

	public BasicBlock getBackEdgeSource(){
		return cfg.getEdgeSource(backEdge);
	}

	public BasicBlock getBackEdgeTarget(){
		return cfg.getEdgeTarget(backEdge);
	}

	public List<BasicBlock> getNodesInLoop() {
		return nodesInLoop;
	}


	public void addToOtherBackEdges(DefaultEdge newBackEdge){
		otherBackEdges.add(newBackEdge);
	}

	public boolean nestsInside(LoopInfo otherLoop){

		if(this == otherLoop)
			return false;   // A loop does not nest within itself


		BasicBlock loopHeader = cfg.getEdgeTarget(backEdge);
		BasicBlock loopHeaderOfOtherLoop = otherLoop.getBackEdgeTarget();

		if(loopHeader != loopHeaderOfOtherLoop){
			// Different headers
			List<BasicBlock> nodesInOtherLoop = otherLoop.getNodesInLoop();
			if(nodesInOtherLoop.containsAll(nodesInLoop)){
				return true;
			}
		}

		return false;
	}

	public List<DefaultEdge> getOtherBackEdges() {
		return otherBackEdges;
	}
	/**
	 * Returns the successor of the loop exit node excluding the node in loop
	 */
	public List<BasicBlock> getPostLoopExit() {

		List<BasicBlock> postLoopExitNodes = new ArrayList<BasicBlock>(); // There could be more than one

		if(nodesInLoop.size() == 0){
			return postLoopExitNodes;
		}

		for(BasicBlock node : nodesInLoop) {
			List<BasicBlock> sucessorOfNode = cfg.getSuccessors(node);

			for(BasicBlock sucessor : sucessorOfNode)
			{
				// if any successor of given node is outside the loop
				//then node is loop exit 
				// and successor is postLoopExit Node
				if(!nodesInLoop.contains(sucessor))
					postLoopExitNodes.add(sucessor);
			}
		}

		return postLoopExitNodes;

	}

	public List<BasicBlock> getPreHeader() {

		BasicBlock header = cfg.getEdgeTarget(backEdge);

		CFG cfg = header.getParent().getCfg();

		List<BasicBlock> predecessorsOfHead = cfg.getPredecessors(header);

		int numPreds = 0;
		if(predecessorsOfHead != null)
			numPreds = predecessorsOfHead.size();

		List<BasicBlock> preheaderNodes = new ArrayList<BasicBlock>(); // There could be more than one
		if(numPreds == 0){
			// The header is the topmost node, so we cannot hoist any
			// loop invariants outside. TODO : Verify this
		}
		else {
			// Identify the predecessors that are not in the loop
			for(BasicBlock pred : predecessorsOfHead){
				if(!nodesInLoop.contains(pred))
					preheaderNodes.add(pred);
			}
		}

		return preheaderNodes;

	}

	/**
	 * Returns all the instructions that are invariant in this loop
	 * @return
	 */
	public LinkedHashSet<Value> getLoopInvariants(){

		LinkedHashSet<Value> loopInvariants = new LinkedHashSet<Value>();

		// Visit the blocks in the loop in the breadth-first order
		// noting any changes in the status of the invariant flags 
		// in the instructions. Repeat until there are no more changes

		List<BasicBlock> nodesInBreadthFirstOrder = getNodesInBreadthFirstOrder(
				cfg.getEdgeTarget(backEdge), nodesInLoop);

		int numLoopInvariantInsFoundBeforeIter = 0,
		numLoopInvariantInsFoundAfterIter = 0;
		do{
			numLoopInvariantInsFoundBeforeIter = 
				loopInvariants.size();
			for(BasicBlock node : nodesInBreadthFirstOrder){
				markBlock(node, loopInvariants);
			}

			numLoopInvariantInsFoundAfterIter = 
				loopInvariants.size();

		}while(numLoopInvariantInsFoundBeforeIter !=
			numLoopInvariantInsFoundAfterIter);

		printLoopInvariantInstrs(loopInvariants);

		return loopInvariants;
	}

	private void printLoopInvariantInstrs(
			Set<Value> loopInvariantInstructions) {
		Iterator<Value> invariantsIter = loopInvariantInstructions.iterator();
		while(invariantsIter.hasNext()){
			LOG.info("Found a loop invariant :" + emitter.getValidName(invariantsIter.next()));
		}
	}

	/**
	 * Return All Instruction in loop that have all users are outside the loop
	 */
	public LinkedHashSet<Instruction> getInstructionHasAllUserOutsideLoop(LinkedHashSet<Value> loopInvariants){
		LinkedHashSet<Instruction> InstrNotusedinLoop = new LinkedHashSet<Instruction>();
		//visit all Instruction in loop 
		// and find all the user of the instruction 
		// if it is not in loop add it to the return list
		List<BasicBlock> nodesInBreadthFirstOrder = getNodesInBreadthFirstOrder(
				cfg.getEdgeTarget(backEdge), nodesInLoop);

		int numLoopInvariantInsFoundBeforeIter = 0,
		numLoopInvariantInsFoundAfterIter = 0;
		do{
			numLoopInvariantInsFoundBeforeIter = 
				InstrNotusedinLoop.size();
			for(BasicBlock node : nodesInBreadthFirstOrder){
				findAllInstructionhasNotUsedinLooop(node, InstrNotusedinLoop, loopInvariants);
			}
		}while(numLoopInvariantInsFoundBeforeIter !=
			numLoopInvariantInsFoundAfterIter);

		return InstrNotusedinLoop;	
	}

	/**
	 * return the all instruction which has all user outside the loop from the given node
	 * @param node
	 * @param instrNotusedinLoop
	 * @param loopInvariants
	 */

	private void findAllInstructionhasNotUsedinLooop(BasicBlock node,
			LinkedHashSet<Instruction> instrNotusedinLoop, LinkedHashSet<Value>loopInvariants) {
		// TODO Auto-generated method stub
		BasicBlock parentBB = (BasicBlock) node;

		Iterator<Instruction> instructions = parentBB.getInstructions();

		// Keep track of values that are not instructions and note them
		// as invariants
		while(instructions.hasNext()){
			Instruction instruction  = instructions.next();
			// If it is already present in loop invariant 
			if(loopInvariants.contains(instruction))
				continue;
			//			if(instruction.isMemoryOperation()){
			//				// TODO Use alias analysis
			//				continue;
			//			}
			// If the instruction cannot be hoisted or sunk, continue
			if(!instruction.canBeHoistedOrSank())
				continue;

			//check the all user of Instruction
			boolean hasAllUserOutside = true;
			int noOfUser = instruction.getNumUses();
			for(int i=0 ; i<noOfUser; i++){
				Instruction userInstr = (Instruction) instruction.getUserAt(i);
				BasicBlock userInstrNode = userInstr.getParent();
				if(nodesInLoop.contains(userInstrNode)){
					hasAllUserOutside = false;
					break;
				}

			}
			if(hasAllUserOutside){
				instrNotusedinLoop.add(instruction);
			}
		}
	}

	private List<BasicBlock> getNodesInBreadthFirstOrder(BasicBlock loopHeader,
			List<BasicBlock> nodesInLoop) {
		
		List<BasicBlock> nodesInBreadthFirstOrder = new ArrayList<BasicBlock>();

		BreadthFirstIterator<BasicBlock, DefaultEdge> iterator = 
			new BreadthFirstIterator<BasicBlock, DefaultEdge>(cfg, cfg.getEdgeTarget(backEdge));

		while(iterator.hasNext()){
			BasicBlock basicBlock = iterator.next();
			if(nodesInLoop.contains(basicBlock)){
				nodesInBreadthFirstOrder.add(basicBlock);
			}
		}
		
		return nodesInBreadthFirstOrder;
	}

	/**
	 * return the loop all loop Invariant from the given node
	 * @param node
	 * @param loopInvariants
	 */
	private void markBlock(BasicBlock node, LinkedHashSet<Value> loopInvariants) {

		BasicBlock parentBB = (BasicBlock) node;

		Iterator<Instruction> instructions = parentBB.getInstructions();

		// Keep track of values that are not instructions and note them
		// as invariants
		Set<Value> nonInstructions = new HashSet<Value>();
		while(instructions.hasNext()){
			Instruction instruction = instructions.next();
			
			int numOperands = instruction.getNumOperands();
			boolean foundVariant = false;
			for(int i = 0; i < numOperands; i++){
				Value operand = instruction.getOperand(i);
				ValueTypeID typeID = operand.getValueTypeID();
				if(typeID != ValueTypeID.INSTRUCTION){
					// All non-instructions are loop-invariant
					nonInstructions.add(operand);
					continue;
				}

				Instruction operandIns = (Instruction) operand;
				BasicBlock definingBlock = operandIns.getParent();
				if(!nodesInLoop.contains(definingBlock)){
					continue;
				}

				// Check if this instruction itself is loop invariant
				if(loopInvariants.contains(operandIns)){
					continue;
				}

				foundVariant = true;
				//break;
			}
			// If the instruction cannot be hoisted or sunk, continue
			if(!instruction.canBeHoistedOrSank())
				continue;

			if(!foundVariant)
				loopInvariants.add(instruction);
		}

		// Add non-instructions that were found
		loopInvariants.addAll(nonInstructions);
	}

	private void visitInBreadthFirstOrder(BasicBlock startNode, 
			HashSet<BasicBlock> visitedNodes, List<BasicBlock> nodesInLoop,
			List<BasicBlock> nodesInBreadthFirstOrder){
		Queue<BasicBlock> queue = new LinkedList<BasicBlock>() ;

		if (startNode == null)
			return;
		queue.clear();
		queue.add(startNode);

		visitedNodes.add(startNode);
		while(!queue.isEmpty()){
			BasicBlock node = queue.remove();
			nodesInBreadthFirstOrder.add(node);
			if(nodesInLoop.size() == nodesInBreadthFirstOrder.size() && nodesInBreadthFirstOrder.containsAll(nodesInLoop))
				return;

			List<BasicBlock> children = cfg.getSuccessors(node);

			Iterator<BasicBlock> iterator = children.iterator();
			while(iterator.hasNext())
			{
				BasicBlock child = iterator.next();
				if(visitedNodes.contains(child) || !nodesInLoop.contains(child))
					continue;
				queue.add(child);
				visitedNodes.add(child);
			}
		}
	}

}

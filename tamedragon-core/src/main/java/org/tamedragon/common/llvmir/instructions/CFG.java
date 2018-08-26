package org.tamedragon.common.llvmir.instructions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.jgraph.graph.DefaultEdge;
import org.jgrapht.graph.ListenableDirectedGraph;
import org.tamedragon.common.DominatorTree;
import org.tamedragon.common.controlflowanalysis.LoopIdentifier;
import org.tamedragon.common.controlflowanalysis.LoopNestingTree;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionDetailAccessException;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionUpdateException;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.Value;

public class CFG extends ListenableDirectedGraph<BasicBlock,DefaultEdge>{
	private BasicBlock startNode;

	//protected Vector<BasicBlock> vertices;          // List of graph nodes
	protected Vector<DefaultEdge> edges;
	protected int nodeCount;

	private boolean inSSAForm;

	public CFG(){
		super(DefaultEdge.class);
		edges = new Vector<DefaultEdge>();
		nodeCount = 0;
		inSSAForm = false;
	}

	public boolean getSSAFormFlag(){
		return inSSAForm;
	}

	public void setSSAFormFlag(boolean flag){
		inSSAForm = flag;
	}

	public BasicBlock getStartNode() {
		return startNode;
	}

	/*
	 * This function returns the control flow node in which a instruction (quadruple) exists. Note
	 * that this function considers object references for comparison
	 *
	 */
	public BasicBlock getCFNodeForQuadruple(Instruction instr){
		Iterator<BasicBlock> nodesIterator = vertexSet().iterator();
		while(nodesIterator.hasNext()){

			BasicBlock nd = (BasicBlock) nodesIterator.next();
			Iterator<Instruction> quadruples = nd.getInstructions();
			while(quadruples.hasNext()){
				Instruction instrInNode = quadruples.next();
				if(instrInNode == instr)
					return nd;
			}
		}
		return null;
	}

	public BasicBlock firstNode() {
		return getStartNode();
	}

	/**
	 * Creates and returns the reverse CFG of this object. A canonical start node
	 * is created that has edges to all the terminal nodes in this node. This node
	 * is the start node for the reversed CFG.
	 */

	public CFG reverse(){

		// The start node of the reversed CFG is not known yet.
		CFG reversedCFG = new CFG();  

		// Get all the terminal nodes
		List<BasicBlock> listOfTerminalNodes = getTerminalNodes();
		

		int numTerminalNodes = listOfTerminalNodes.size();
		boolean hasOnlyOneTerminalNode = numTerminalNodes == 1 ? true : false;
		HashMap<BasicBlock, BasicBlock> nodeAndnodeInReversedCFG = 
			new HashMap<BasicBlock, BasicBlock>();
		for(BasicBlock terminalNode: listOfTerminalNodes){
			updateReverseCFG(terminalNode, reversedCFG,
					hasOnlyOneTerminalNode,nodeAndnodeInReversedCFG);
		}
		return reversedCFG;
	}
	
	private List<BasicBlock> getTerminalNodes(){
		List<BasicBlock> listOfTerminalNodes = new ArrayList<BasicBlock>();

		Set<BasicBlock> vertexSet = vertexSet();
		Iterator<BasicBlock> iterator = vertexSet.iterator();
		while(iterator.hasNext()){
			BasicBlock cfNode = iterator.next();
			if(getSuccessors(cfNode).size() > 0)
				continue;

			listOfTerminalNodes.add(cfNode);
		}
		
		return listOfTerminalNodes;
	}

	private void updateReverseCFG(BasicBlock node, CFG reversedCFG,
			boolean hasOnlyOneTerminalNode,
			HashMap<BasicBlock, BasicBlock> nodeAndnodeInReversedCFG) {

		BasicBlock reversedNode = nodeAndnodeInReversedCFG.get(node);
		if(reversedNode == null){
			reversedNode = node.createClone();
			nodeAndnodeInReversedCFG.put(node, reversedNode);
		}

		if(getSuccessors(node).size() == 0){
			// This is a terminal node in the current CFG. Make this a 
			// successor in the reversed CFG.

			BasicBlock startNodeOfReversedCFG = reversedCFG.getStartNode();
			if(startNodeOfReversedCFG == null){
				// The start node in the reversed CFG is not yet created; 
				// create it now. If this CFG has only one non-terminal, create
				// it from that node. If it has multiple terminal nodes, create
				// a new start node for the reversed CFG and add edges to the corresponding
				// node in the reversed flow graph.
				if(hasOnlyOneTerminalNode){
					// Only one terminal node; make this the start node of the reversed CFG
					startNodeOfReversedCFG = reversedNode;
					reversedCFG.addVertex(startNodeOfReversedCFG);
				}
				else{
					// Not the only terminal node; create a new start node
					startNodeOfReversedCFG = new BasicBlock(node.getContext());
					startNodeOfReversedCFG.setName("Reverse_Start");
					reversedCFG.addVertex(startNodeOfReversedCFG);

					DefaultEdge edge = new DefaultEdge();
					reversedCFG.addEdge(startNodeOfReversedCFG,
							reversedNode, edge);
				}
				reversedCFG.setStartNode(startNodeOfReversedCFG);
			}
			else{
				reversedCFG.addVertex(reversedNode);
				DefaultEdge edge = new DefaultEdge();
				reversedCFG.addEdge(startNodeOfReversedCFG,
						reversedNode, edge);
			}
		}

		// For each predecessor of this node, create a corresponding successor
		// in the reverse CFG

		List<BasicBlock> predecessors = getPredecessors(node);
		int numPreds = predecessors.size();
		for(int i = 0; i < numPreds; i++){
			BasicBlock predNode = predecessors.get(i);

			// If a node with the same name exists in the reverse flow graph, do not
			// add it again
			BasicBlock reversedNodeOfPred = nodeAndnodeInReversedCFG.get(predNode);
			if(reversedNodeOfPred != null){
				DefaultEdge edge = new DefaultEdge();
				reversedCFG.addEdge(reversedNode, reversedNodeOfPred, edge);
				continue;
			}

			reversedNodeOfPred = predNode.createClone();
			reversedCFG.addVertex(reversedNodeOfPred);
			DefaultEdge edge = new DefaultEdge();
			reversedCFG.addEdge(reversedNode, reversedNodeOfPred, edge);

			updateReverseCFG(predNode, reversedCFG, hasOnlyOneTerminalNode, 
					nodeAndnodeInReversedCFG);
		}
	}

	public void setStartNode(BasicBlock startNode) {
		this.startNode = startNode;
	}

	/**
	 * Returns the index of the predecessor that is passed to this function 
	 * for the given node. If the node that is passed is not a predecessor, -1
	 * (invalid) is returned.
	 */
	public int getIndexOfPredecessor(BasicBlock predecessor, BasicBlock node){
		List<BasicBlock> predecessors = getPredecessors(node);
		return predecessors.indexOf(predecessor);
	}

	public List<BasicBlock> getSuccessors(BasicBlock node){
		return getPredecessorsOrSuccessors(node, false);
	}

	public List<BasicBlock> getPredecessors(BasicBlock node){
		return getPredecessorsOrSuccessors(node, true);
	}

	private List<BasicBlock> getPredecessorsOrSuccessors(BasicBlock node, boolean predecessors){
		List<BasicBlock> predecessorsOrSuccessors = new ArrayList<BasicBlock>();

		Set<DefaultEdge> edges = null;
		if(predecessors){
			edges = incomingEdgesOf(node);
		}
		else{
			edges = outgoingEdgesOf(node);
		}

		Iterator<DefaultEdge> iterator = edges.iterator();
		while(iterator.hasNext()){
			BasicBlock other = null;
			if(predecessors){
				other = getEdgeSource(iterator.next());
			}
			else{
				other = getEdgeTarget(iterator.next());
			}
			predecessorsOrSuccessors.add(other);
		}

		return predecessorsOrSuccessors;
	}

	/**
	 * SplitCriticalEdge - If this edge is a critical edge, insert a new node to
	  split the critical edge.  This will update DominatorTree information if it
	  is available, thus calling this pass will not invalidate either of them.
	  This returns the new block if the edge was split, null otherwise.

	  If MergeIdenticalEdges is true (not the default), *all* edges from TI to the
	  specified successor will be merged into the same critical edge block.
	  This is most commonly interesting with switch instructions, which may
	  have many edges to any one destination.  This ensures that all edges to that
	  dest go to one block instead of each going to a different block, but isn't
	  the standard definition of a "critical edge".

	  It is invalid to call this function on a critical edge that starts at an
	  IndirectBrInst.  Splitting these edges will almost always create an invalid
	  program because the address of the new block won't be the one that is jumped
	  to.
	 * @param latchTerm
	 * @param succNum
	 * @return
	 * @throws InstructionDetailAccessException 
	 * @throws InstructionUpdateException 
	 */
	public static BasicBlock splitCriticalEdge(TerminatorInst ti,
			int succNum, boolean MergeIdenticalEdges,boolean DontDeleteUselessPhis) throws InstructionDetailAccessException, InstructionUpdateException {
		if (!isCriticalEdge(ti, succNum, MergeIdenticalEdges)) return null;

		BasicBlock TIBB = ti.getParent();
		BasicBlock destBB = ti.getSuccessor(succNum);

		// Create a new basic block, linking it into the CFG.
		
		Function function = TIBB.getParent();
		BasicBlock newBB = new BasicBlock(function);
		newBB.setName(TIBB.getName() + "." + destBB.getName() + "_crit_edge");

		List<BasicBlock> destinations = new ArrayList<BasicBlock>();
		destinations.add(destBB);
		
		function.insertNewNodeBetween(TIBB, destinations, newBB);
		
		// Insert the block into the function... right after the block TI lives in.
//		List<BasicBlock> basicBlocks = function.getBasicBlocks();
//		int pos = function.getIndexOf(TIBB);
//		basicBlocks.add(pos+1, newBB);

		// If there are any PHI nodes in DestBB, we need to update them so that they
		// merge incoming values from NewBB instead of from TIBB.
		CFG cfg = function.getCfg();
		List<BasicBlock> successors = cfg.getSuccessors(destBB);
		for(BasicBlock successor : successors) {
			// Loop over any phi nodes in the basic block, updating the BB field of
			// incoming values...
			Iterator<Instruction> instrIT = successor.getInstructions();
			
			while(instrIT.hasNext()){
				Instruction instruction = (Instruction)instrIT.next();
				if(instruction instanceof PhiNode){
					PhiNode phiNode = (PhiNode)instruction;
					int index = phiNode.getBasicBlockIndex(destBB);
					while(index !=1){
						Value value = phiNode.getIncomingValue(index);
						phiNode.setIncomingValueAndBasicBlock(index, value, destBB);
						index = phiNode.getBasicBlockIndex(destBB);
					}
				}
			}
		}

		LoopIdentifier loopIdentifier = new LoopIdentifier(function);
		LoopNestingTree loopNestingTree = loopIdentifier.getLoopNestingTree();
		DominatorTree dominatorTree = loopIdentifier.getDominatorTree();

		// If we have nothing to update, just return.
		if (dominatorTree == null && loopNestingTree == null)
			return newBB;

		return newBB;
	}

	@Override
	public boolean addVertex(BasicBlock node){
		if(vertexSet().contains(node)){
			return false;
		}
		return super.addVertex(node);
	}
	
	@Override
	public boolean addEdge(BasicBlock source, BasicBlock target, DefaultEdge edge){
		return super.addEdge(source, target, edge);
	}
	
	private static boolean isCriticalEdge(TerminatorInst ti, int succNum,
			boolean mergeIdenticalEdges) throws InstructionDetailAccessException {
		BasicBlock srcBB = ti.getParent();

		CFG cfg = srcBB.getParent().getCfg();

		int numOfSuccesors = cfg.getSuccessors(srcBB).size(); 
		if(succNum > numOfSuccesors-1)
			return false;

		if(numOfSuccesors == 1)
			return false;

		BasicBlock destBB = ti.getSuccessor(succNum);
		int numOfPredecessors = cfg.getPredecessors(destBB).size();
		if(numOfPredecessors == 1)
			return false;

		if(!mergeIdenticalEdges){
			BasicBlock predBB = (BasicBlock) cfg.getPredecessors(destBB).get(1);
			return predBB != srcBB;
		}
		else{
			for(int i = 0; i < numOfPredecessors; i++){
				BasicBlock predBB = (BasicBlock) cfg.getPredecessors(destBB).get(i);
				if(predBB != srcBB)
					return true;
			}
		}

		return false;
	}
}


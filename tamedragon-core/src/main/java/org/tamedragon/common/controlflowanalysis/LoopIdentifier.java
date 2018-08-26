package org.tamedragon.common.controlflowanalysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.jgraph.graph.DefaultEdge;
import org.tamedragon.common.DominatorTree;
import org.tamedragon.common.SpanningTree;
import org.tamedragon.common.SpanningTreeDFS;
import org.tamedragon.common.llvmir.instructions.CFG;

//import com.compilervision.common.llvmir.utils.LLVMIREmitter;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.Function;

/**
 * Given a flow graph, this class determines loops in it. A DFS
 * is first performed to identify back-edges. A back edge in a 
 * flowgraph is defined one whose head dominates it's tail. For 
 * example, given the back-edge m -> n, n dominates m. The node
 * n is the header of the loop.
 * 
 *  A DFS on the input flowgraph is first performed to determine
 *  the back-edges. Once we have determined the back-edges, we
 *  use the property of "natural loops" : the subgraph consisting
 *  of the set of nodes from which node m can be reached without
 *  going through n.   
 *
 */
public class LoopIdentifier {

	private Function function;
	private CFG cfg;

	private boolean graphIsNonReducible;
	private List<LoopInfo> loopInfoList;
	private LoopNestingTree loopNestingTree;
	private DominatorTree dominatorTree;

	//private LLVMIREmitter emitter;

	public LoopIdentifier(Function function){
		this.function = function;
		this.cfg = function.getCfg();
		loopInfoList = new ArrayList<LoopInfo>();
		graphIsNonReducible = false;
		//emitter = LLVMIREmitter.getInstance();
		analyze();
	}

	public void analyze(){

		// Perform a DFS to get the spanning tree
		SpanningTreeDFS spanningTreeDFS = new SpanningTreeDFS();
		SpanningTree spanningTree = (SpanningTree)spanningTreeDFS.execute(function);
		Vector<DefaultEdge> retreatingEdges = spanningTreeDFS.getRetreatingEdges();

		// Get the dominator tree for this graph
		DominatorCalculationContext dominatorCalculationContext = 
			new DominatorCalculationContext(function);
		dominatorCalculationContext.setLengauerTarjan(spanningTree);
		dominatorTree = dominatorCalculationContext.getDominatorTree();

		Vector<DefaultEdge> backEdges = getBackEdges(retreatingEdges);

		loopInfoList = new ArrayList<LoopInfo>();

		for(DefaultEdge backEdge : backEdges){

			List<BasicBlock> nodesInLoop = getLoopInfo(function.getCfg(), backEdge);

			// Iterate over the current list of loop info objects found
			// and merge the current loopInfo with any loop in the list
			// with the same header.
			LoopInfo loopToMergeWith = null;
			for(LoopInfo existingLoopInfo : loopInfoList){
				BasicBlock header = cfg.getEdgeTarget(backEdge);
				BasicBlock headerInExistingLoop = (BasicBlock)existingLoopInfo.getBackEdgeTarget();
				if(header == headerInExistingLoop){
					loopToMergeWith = existingLoopInfo;
					break;
				}
			}

			if(loopToMergeWith != null){
				// Merge the new loop found with the existing loop that has
				// the same header
				loopToMergeWith.getNodesInLoop().addAll(nodesInLoop);
				loopToMergeWith.addToOtherBackEdges(backEdge);
			}
			else{
				LoopInfo loopInfo =  new LoopInfo(backEdge, nodesInLoop);
				loopInfoList.add(loopInfo);
			}
		}

		// In the list of loops, we will now have information about headers;
		// if a header h1 dominates another header h2, the loop associated
		// with h2 nests inside the loop associated with h1.
		HashMap<LoopInfo, LoopInfo> immediateNestingInfo 
		= new HashMap<LoopInfo, LoopInfo>();
		for(LoopInfo loopInfo : loopInfoList){
			List<BasicBlock> orderedListOfAncestorsInDomTree = 
				getOrderedListOfAncestorsInDomTree(loopInfo.getBackEdgeTarget());
			boolean foundImmediateOuterLoop = false;
			outer : for(BasicBlock parent : orderedListOfAncestorsInDomTree){
				for(LoopInfo otherLoopInfo : loopInfoList){
					if(otherLoopInfo == loopInfo)
						continue;

					if(parent == otherLoopInfo.getBackEdgeTarget()){
						if(otherLoopInfo.getNodesInLoop().containsAll(loopInfo.getNodesInLoop())){
							immediateNestingInfo.put(loopInfo, otherLoopInfo);
							foundImmediateOuterLoop = true;
							break outer;
						}
					}
				}
			}

			if(!foundImmediateOuterLoop){
				// No outer loop for this loop
				immediateNestingInfo.put(loopInfo, null);
			}
		}

		// Now we have the loop nesting information, create the loop nesting tree
		loopNestingTree = LoopNestingTree.create(loopInfoList, immediateNestingInfo);
	}

	private List<BasicBlock> getOrderedListOfAncestorsInDomTree(BasicBlock node) {

		BasicBlock runner = node;
		List<BasicBlock> orderedParentList = new ArrayList<BasicBlock>();
		while(runner != null){
			BasicBlock parent = dominatorTree.getParentInTree(runner);
			if(parent == null)
				break;

			orderedParentList.add(parent);
			runner = parent;
		}

		return orderedParentList;
	}

	/**
	 * Given retreating edges that were found in a DFS traversal,
	 * this method returns back edges. A back edge in a graph is
	 * an edge in which the head of the edge dominates it's tail.
	 * 
	 * If the number of retreating edges do not match the number
	 * of back edges, the graph is non-reducible.
	 * 
	 */
	private Vector<DefaultEdge> getBackEdges(Vector<DefaultEdge> retreatingEdges) {

		Vector<DefaultEdge> backEdges = new Vector<DefaultEdge>();

		for(DefaultEdge retreatingEdge : retreatingEdges){
			BasicBlock head = cfg.getEdgeTarget(retreatingEdge);
			BasicBlock tail = cfg.getEdgeSource(retreatingEdge);

			if(dominatorTree.dominates(head, tail))
				backEdges.add(retreatingEdge);
			else
				graphIsNonReducible = true;
		}

		return backEdges;
	}

	/**
	 * Given a back edge, this method returns a list of nodes that
	 * the back edge denotes 
	 */
	private List<BasicBlock> getLoopInfo(CFG cfg, DefaultEdge backEdge) {
		BasicBlock head = cfg.getEdgeTarget(backEdge);
		BasicBlock tail = cfg.getEdgeSource(backEdge);

		List<BasicBlock> nodesInLoop = new ArrayList<BasicBlock>();
		nodesInLoop.add(head); nodesInLoop.add(tail);

		List<BasicBlock> tempList = new ArrayList<BasicBlock>();
		if(head != tail)
			tempList.add(tail);

		while(!tempList.isEmpty()){
			BasicBlock node = tempList.remove(tempList.size() -1);
			List<BasicBlock> predNodes = cfg.getPredecessors(node);
			for(BasicBlock pred: predNodes){
				if(nodesInLoop.contains(pred))
					continue;

				nodesInLoop.add(pred);
				tempList.add(pred);

			}
		}

		return nodesInLoop;
	}

	public boolean isGraphIsNonReducible() {
		return graphIsNonReducible;
	}

	public List<LoopInfo> getLoopInfoList() {
		return loopInfoList;
	}

	public LoopNestingTree getLoopNestingTree() {
		return loopNestingTree;
	}

	public DominatorTree getDominatorTree() {
		return dominatorTree;
	}
}

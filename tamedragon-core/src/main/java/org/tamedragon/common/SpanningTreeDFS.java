package org.tamedragon.common;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import org.jgraph.graph.DefaultEdge;
import org.tamedragon.common.llvmir.instructions.CFG;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.utils.LLVMIREmitter;

public class SpanningTreeDFS {

	private SpanningTreeNode spanningTreeRoot;
	private HashMap<DefaultEdge, Boolean> edgeVsVisitStatus;
	private HashMap<BasicBlock, SpanningTreeNode> nodeVsSpanningTreeNode;
	private Vector<DefaultEdge> retreatingEdges;
	private Vector<DefaultEdge> forwardEdges;
	private Vector<DefaultEdge> crossEdges;
	private Vector<SpanningTreeNode> visitedNodes;
	private int currentDepthNumber;

	protected CFG graph;
	private Function function;
	private enum TraverseState{ENTER, EXIT};

	private HashMap<BasicBlock, TraverseState> nodeAndTraverseState;

	public Object execute(Function function){
		this.function = function;
		this.graph = function.getCfg();
		nodeAndTraverseState = new HashMap<BasicBlock, TraverseState>();

		currentDepthNumber = 1;

		edgeVsVisitStatus = new HashMap<DefaultEdge, Boolean> ();
		nodeVsSpanningTreeNode = new HashMap<BasicBlock, SpanningTreeNode>();
		retreatingEdges = new Vector<DefaultEdge>();
		forwardEdges = new Vector<DefaultEdge>();
		crossEdges = new Vector<DefaultEdge>();
		visitedNodes = new Vector<SpanningTreeNode>();

		Iterator<BasicBlock> basicBlockIterator = function.basicBlocksIterator();
		while(basicBlockIterator.hasNext()) {
			BasicBlock node = basicBlockIterator.next();
			TreeNode treeNode = new TreeNode(node.getName());
			SpanningTreeNode spanningTreeNode = new SpanningTreeNode(treeNode, node);
			spanningTreeNode.setVisited(false);
			if(node == function.getStartNode())
			{
				spanningTreeNode.setDepthNumber(currentDepthNumber);
				spanningTreeRoot = spanningTreeNode;
				visitedNodes.addElement(spanningTreeNode);
			}
			nodeVsSpanningTreeNode.put(node, spanningTreeNode);
		}

		Set<DefaultEdge> edgeSet = graph.edgeSet();
		Iterator<DefaultEdge> iterator = edgeSet.iterator();

		while(iterator.hasNext()){
			Boolean visited = new Boolean(false);
			edgeVsVisitStatus.put(iterator.next(), visited);
		}

		return traverse(function.getStartNode());
	}

	public boolean isVisited(DefaultEdge edge) {
		Boolean visited = (Boolean) edgeVsVisitStatus.get(edge);
		return visited.booleanValue();
	}

	public boolean isVisited(BasicBlock node){
		SpanningTreeNode spanningTreeNode = (SpanningTreeNode) nodeVsSpanningTreeNode.get(node);
		return spanningTreeNode.isVisited();
	}

	public void visit(DefaultEdge edge){
		edgeVsVisitStatus.put(edge, new Boolean(true));
	}

	public void visit(BasicBlock node){
		SpanningTreeNode spanningTreeNode = (SpanningTreeNode) nodeVsSpanningTreeNode.get(node);
		spanningTreeNode.setVisited(true);	
	}

	public boolean isDone(){
//		int numVisitedNodes = visitedNodes.size();
//		int numNodes = function.getNumBasicBlocks();
//		if(numVisitedNodes == numNodes){
//			return true;
//		}
		return false;
	}

	public void traverseDiscovery(BasicBlock nextNode, BasicBlock node){
		SpanningTreeNode stNode = (SpanningTreeNode)nodeVsSpanningTreeNode.get(node);

		SpanningTreeNode stNodeNext = (SpanningTreeNode)nodeVsSpanningTreeNode.get(nextNode);
		stNodeNext.setDepthNumber(++currentDepthNumber);
		stNodeNext.setName(nextNode.getName());

		stNode.addChild(stNodeNext);

		visitedNodes.addElement(stNodeNext);
	}

	public void traverseBack(DefaultEdge edge, BasicBlock node){

		BasicBlock srcNode = graph.getEdgeSource(edge);
		BasicBlock destNode = graph.getEdgeTarget(edge);

		SpanningTreeNode stSrcNode = (SpanningTreeNode)nodeVsSpanningTreeNode.get(srcNode);
		SpanningTreeNode stDestNode = (SpanningTreeNode)nodeVsSpanningTreeNode.get(destNode);

		long depthFirstNumOfSrc = stSrcNode.getDepthNumber();
		long depthFirstNumOfDest = stDestNode.getDepthNumber();

		if(depthFirstNumOfDest < depthFirstNumOfSrc){
			if(traversalInProgress(destNode)){
				retreatingEdges.addElement(edge);
			}
			else{
				crossEdges.add(edge);
			}
		}
		else{
			if(traversalInProgress(srcNode)){
				forwardEdges.addElement(edge);
			}
			else{
				crossEdges.add(edge);
			}
		}
	}

	/**
	 * This function creates the spanning tree and returns it.
	 */
	public SpanningTree getResult(){
		SpanningTree spanningTree = new SpanningTree();
		spanningTree.setGraphNodeVsSpanningTreeNode(nodeVsSpanningTreeNode);
		spanningTree.setVisitedNodes(visitedNodes);
		spanningTree.setSpanningTreeRoot(spanningTreeRoot);
		return spanningTree;
	}

	public Vector<DefaultEdge> getRetreatingEdges(){
		return retreatingEdges;
	}

	public Vector<DefaultEdge> getForwardEdges() {
		return forwardEdges;
	}

	private SpanningTree traverse(BasicBlock node) {
		
		nodeAndTraverseState.put(node, TraverseState.ENTER);
		visit(node);

		Set<DefaultEdge> outgoingEdges = graph.outgoingEdgesOf(node);
		Iterator<DefaultEdge> iterator = outgoingEdges.iterator();

		while(iterator.hasNext()){
			DefaultEdge nextEdge = iterator.next();
			if(!isVisited(nextEdge)) {
				visit(nextEdge);
				BasicBlock nextNode = graph.getEdgeTarget(nextEdge);
				if(!isVisited(nextNode)) {
					traverseDiscovery(nextNode, node);
					if(!isDone()){
						traverse(nextNode);
					}
				}
				else
					traverseBack(nextEdge, node);
			}
		}

		nodeAndTraverseState.put(node, TraverseState.EXIT);
		return getResult();
	}

	public boolean traversalInProgress(BasicBlock node){
		if(nodeAndTraverseState.get(node) == TraverseState.ENTER)
			return true;

		return false;
	}

}

package org.tamedragon.common;

import java.util.HashMap;
import java.util.ListIterator;
import java.util.Vector;

import org.tamedragon.common.llvmir.types.BasicBlock;

public class SpanningTree {

	private HashMap<BasicBlock, SpanningTreeNode> nodeVsSpanningTreeNode;
	private Vector<SpanningTreeNode> visitedNodes;
	private SpanningTreeNode spanningTreeRoot;
	
	public void setGraphNodeVsSpanningTreeNode(HashMap<BasicBlock, SpanningTreeNode> nodeVsSpanningTreeNode){
		this.nodeVsSpanningTreeNode = nodeVsSpanningTreeNode;
	}
	
	public BasicBlock getParentInTree(BasicBlock graphNode){
		SpanningTreeNode stNode = nodeVsSpanningTreeNode.get(graphNode);
		SpanningTreeNode parentSTNode = (SpanningTreeNode)stNode.getParent();
		return parentSTNode.getGraphNode();
	}
	
	public long getDepthFirstNumber(BasicBlock node){
		SpanningTreeNode stNode = nodeVsSpanningTreeNode.get(node);
		return stNode.getDepthNumber();
	}

	public void setVisitedNodes(Vector<SpanningTreeNode> visitedNodes) {
		this.visitedNodes = visitedNodes;
	}
	
	public BasicBlock getVisitedNode(int index){
		SpanningTreeNode stNode = visitedNodes.elementAt(index);
		return stNode.getGraphNode();
	}

	public void setSpanningTreeRoot(SpanningTreeNode spanningTreeRoot) {
		this.spanningTreeRoot = spanningTreeRoot;
	}
	
	public ListIterator<SpanningTreeNode> visitedNodesIterator(){
		return (ListIterator<SpanningTreeNode>) visitedNodes.iterator();
	}
	
	public ListIterator<SpanningTreeNode> reverseVisitedNodesIterator(){
		return  visitedNodes.listIterator(visitedNodes.size());
	}
	
	public SpanningTreeNode getTreeRoot(){
		return spanningTreeRoot;
	}
}

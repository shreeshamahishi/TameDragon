package org.tamedragon.common;

import org.tamedragon.common.llvmir.types.BasicBlock;

/**
 * This class is a wrapper class around a node. It contains the node object and adds additional functionality
 * to a spanning tree node, namely the depth number and whether the node has been visited. 
 * 
 * It conforms to the Decorator design pattern (also known as Wrapper pattern).
 * @author shreesha123
 *
 */

public class SpanningTreeNode extends TreeNode{
	
	private TreeNode node;        // The contained node
	private BasicBlock graphNode;       // The node in the graph corresponding to this tree node
	private boolean visited;  // Indicates whether this node has been visited
	private long depthNumber;  // The depth number at which this node has been visited
	
	public SpanningTreeNode(TreeNode node, BasicBlock graphNode){
		this.node = node;
		this.graphNode = graphNode;
	}

	public void setVisited(boolean visited){
		this.visited = visited;
	}
	
	public void setDepthNumber(long num){
		this.depthNumber = num;
	}
	
	public long getDepthNumber(){
		return depthNumber;
	}

	public boolean isVisited() {
		return visited;
	}
	
	public BasicBlock getGraphNode(){
		return graphNode;
	}
}

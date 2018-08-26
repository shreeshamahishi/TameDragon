package org.tamedragon.common;

import java.util.Vector;

import org.tamedragon.common.llvmir.types.BasicBlock;

public class DominatorTreeNode extends TreeNode{

	private BasicBlock graphNode;       // The node in the graph corresponding to this tree node

	public DominatorTreeNode(TreeNode node, BasicBlock graphNode){
		this.graphNode = graphNode;
	}

	public BasicBlock getGraphNode(){
		return graphNode;
	}

	@Override
	public void addChild(TreeNode node) {
		super.addChild(node);
	}

	public Vector<DominatorTreeNode> getDTNChildren() {
		Vector<DominatorTreeNode> tempChildren = new Vector<DominatorTreeNode>();
		for (TreeNode node :children)
			tempChildren.add((DominatorTreeNode)(node));

		return tempChildren;
	}


}

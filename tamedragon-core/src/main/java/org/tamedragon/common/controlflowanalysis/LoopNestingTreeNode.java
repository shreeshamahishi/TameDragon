package org.tamedragon.common.controlflowanalysis;

import org.tamedragon.common.TreeNode;

public class LoopNestingTreeNode extends TreeNode{

	private LoopInfo loopInfo;
	
	public LoopNestingTreeNode(LoopInfo loopInfo){
		this.loopInfo = loopInfo;
	}

	public LoopInfo getLoopInfo() {
		return loopInfo;
	}
	
	@Override
	public void addChild(TreeNode node) {
		super.addChild(node);
	}
}

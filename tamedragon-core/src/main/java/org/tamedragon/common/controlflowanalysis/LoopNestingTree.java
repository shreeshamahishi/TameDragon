package org.tamedragon.common.controlflowanalysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.tamedragon.common.TreeNode;
import org.tamedragon.common.llvmir.types.BasicBlock;

public class LoopNestingTree {

	private LoopNestingTreeNode loopNestingTreeRoot;
	private HashMap<LoopInfo, LoopNestingTreeNode> loopInfoVsLoopNestingTreeNode;

	public LoopNestingTree(LoopNestingTreeNode loopNestingTreeRoot,
			HashMap<LoopInfo, LoopNestingTreeNode> loopInfoVsLoopNestingTreeNode) {

		this.loopNestingTreeRoot = loopNestingTreeRoot;
		this.loopInfoVsLoopNestingTreeNode = loopInfoVsLoopNestingTreeNode;
	}

	public List<LoopInfo> getLeafLoops(){
		return getLeafLoops(loopNestingTreeRoot);
	}

	/**
	 * A static utility function that creates a LoopNestingTree object given 
	 * the list of LoopInfo objects and the a map of each LoopInfo objects
	 * against its immediate outer loop. 
	 */

	public static LoopNestingTree create(List<LoopInfo> loopInfoList, 
			HashMap<LoopInfo, LoopInfo> nestingInfo){

		// First create the nesting information tree nodes and a mapping
		// of graph nodes and nesting information nodes

		LoopNestingTreeNode loopNestingTreeRoot = new LoopNestingTreeNode(null);

		HashMap<LoopInfo, LoopNestingTreeNode> loopInfoVsLoopNestingTreeNode = 
			new HashMap<LoopInfo, LoopNestingTreeNode>();
		for(LoopInfo loopInfo : loopInfoList) {
			LoopNestingTreeNode loopNestingTreeNode = 
				new LoopNestingTreeNode(loopInfo);

			loopInfoVsLoopNestingTreeNode.put(loopInfo, loopNestingTreeNode);
		}

		// Populate the tree from the immediateDominators HashMap
		Set<LoopInfo> keys = nestingInfo.keySet();
		Iterator<LoopInfo> keyIterator = keys.iterator();

		while(keyIterator.hasNext()) {
			LoopInfo loopInfo = keyIterator.next();
			LoopInfo outerLoop = nestingInfo.get(loopInfo);

			LoopNestingTreeNode lntNode = loopInfoVsLoopNestingTreeNode.get(loopInfo);
			if(outerLoop == null){
				// The loop is not nested in any outer loop; make it a child of the
				// the root of the loop nesting tree.
				loopNestingTreeRoot.addChild(lntNode);
			}
			else {
				LoopNestingTreeNode outerLntNode = loopInfoVsLoopNestingTreeNode.get(outerLoop);
				outerLntNode.addChild(lntNode);
			}
		}

		// Create a new dominator tree object, populate it and return it
		LoopNestingTree loopNestingTree = new LoopNestingTree(loopNestingTreeRoot,
				loopInfoVsLoopNestingTreeNode);
		return loopNestingTree;
	}

	public LoopNestingTreeNode getLoopNestingTreeRoot() {
		return loopNestingTreeRoot;
	}

	public HashMap<LoopInfo, LoopNestingTreeNode> getLoopInfoVsLoopNestingTreeNode() {
		return loopInfoVsLoopNestingTreeNode;
	}

	/**
	 * Iterates recursively over the tree and returns all the leaf nodes.
	 * Each leaf node represents a loop with no inner loops.
	 * @param treeNode
	 * @return
	 */
	private List<LoopInfo> getLeafLoops(TreeNode treeNode){

		LoopNestingTreeNode loopNestingTreeNode = 
			(LoopNestingTreeNode) treeNode;

		List<LoopInfo> leaves = new ArrayList<LoopInfo>();

		Vector<TreeNode> children = treeNode.getChildren();
		if(children.size() == 0){
			leaves.add(loopNestingTreeNode.getLoopInfo());
			return leaves;
		}
		else{
			for(TreeNode child : children){
				LoopNestingTreeNode childTN = (LoopNestingTreeNode) child;
				List<LoopInfo> leavesFromChild = getLeafLoops(childTN);
				leaves.addAll(leavesFromChild);
			}
		}

		return leaves;
	}
	
	/**
	 * Returns true if the first argument (inner) loop nests within the loop
	 * represented by the second argument (outer)
	 * @param inner
	 * @param outer
	 * @return
	 */
	public boolean nestsInside(LoopInfo inner, LoopInfo outer) {

		LoopNestingTreeNode lntnInner = loopInfoVsLoopNestingTreeNode.get(inner);
		LoopNestingTreeNode lntnOuter = loopInfoVsLoopNestingTreeNode.get(outer);
		
		if(lntnInner == null || lntnOuter == null)
			return false;
		
		if(pathExists(lntnOuter, lntnInner))
			return true;
		
		return false;
		

	}
/**
 * this method return the true value if path between two nodes exist
 * @param dtNode1
 * @param dtNode2
 * @return boolean
 */
	private boolean pathExists(TreeNode dtNode1, TreeNode dtNode2){

		Vector<TreeNode> children = dtNode1.getChildren();
		for(TreeNode child : children){
			if(child == dtNode2)
				return true;

			if(pathExists(child, dtNode2))
				return true;
		}

		return false;
	}

	/**
	 * Returns true if the loop passed as the argument is an outermost
	 * loop (a child of the root).
	 * @param loopInfo
	 * @return
	 */
	public boolean isAnOutermostLoop(LoopInfo loopInfo) {
		LoopNestingTreeNode lntn = loopInfoVsLoopNestingTreeNode.get(loopInfo);
		if(loopNestingTreeRoot.getChildren().contains(lntn))
				return true;
		
		return false;
			
	}

	public LoopInfo getImmediateOuterLoop(LoopInfo innerLoop) {

		LoopNestingTreeNode lntnInnerLoop = loopInfoVsLoopNestingTreeNode.get(innerLoop);
		if(loopNestingTreeRoot.getChildren().contains(lntnInnerLoop))
			// An outermost loop has no other outer loops; return null
			return null;
		
		return ((LoopNestingTreeNode)lntnInnerLoop.getParent()).getLoopInfo();
	}
	/**
	 * This function returns the inner-most loop in which this basic block exists.
	 * @param basicBlock
	 * @return
	 */
	public LoopInfo getLoopInfoForBB(BasicBlock basicBlock){
		HashSet<LoopInfo> visitedLoops = new HashSet<LoopInfo>();
		List<LoopInfo> leafLoops = getLeafLoops();
		for(LoopInfo leafLoop : leafLoops){
			LoopInfo runner = leafLoop;
			while(runner != null){
				if(visitedLoops.contains(leafLoop)){
					runner = getImmediateOuterLoop(runner);
					continue;
				}
				visitedLoops.add(runner);
				if(runner.getNodesInLoop().contains(basicBlock))
					return runner;
				runner = getImmediateOuterLoop(runner);
			}
		}
		return null;
	}
}

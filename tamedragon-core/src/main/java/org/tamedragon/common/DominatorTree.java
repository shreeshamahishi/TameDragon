package org.tamedragon.common;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.utils.LLVMIREmitter;

public class DominatorTree {
	private HashMap<BasicBlock, DominatorTreeNode> nodeVsDominatorTreeNode;
	private DominatorTreeNode dominatorTreeRoot;

	private static final Logger LOG = LoggerFactory.getLogger(DominatorTree.class);
	
	public void setGraphNodeVsDominatorTreeNode(HashMap<BasicBlock, DominatorTreeNode> nodeVsDominatorTreeNode){
		this.nodeVsDominatorTreeNode = nodeVsDominatorTreeNode;
	}

	public BasicBlock getParentInTree(BasicBlock graphNode){
		
		DominatorTreeNode dtNode = (DominatorTreeNode)nodeVsDominatorTreeNode.get(graphNode);
		if(dtNode == dominatorTreeRoot){
			// The root cannot have  parent
			return null;
		}
		
		DominatorTreeNode parentDTNode = (DominatorTreeNode)dtNode.getParent();
		return parentDTNode.getGraphNode();
	}

	public Vector<BasicBlock> getChildNodesForGraphNode(BasicBlock graphNode){
		Vector<BasicBlock> children = new Vector<BasicBlock>();
		DominatorTreeNode dtNode = (DominatorTreeNode)nodeVsDominatorTreeNode.get(graphNode);
		Vector<TreeNode> childrenInDomTree = dtNode.getChildren();
		if(childrenInDomTree == null) return children;   // Has no children in dominator tree

		for(int i = 0; i < childrenInDomTree.size(); i++){
			DominatorTreeNode childDTNode = (DominatorTreeNode) childrenInDomTree.elementAt(i);
			children.addElement(childDTNode.getGraphNode());
		}

		return children;
	}

	public void setDominatorTreeRoot(DominatorTreeNode dominatorTreeRoot) {
		this.dominatorTreeRoot = dominatorTreeRoot;
	}

	public DominatorTreeNode getDominatorTreeRoot(){
		return dominatorTreeRoot;
	}

	public DominatorTreeNode getDominatorTreeNode(BasicBlock graphNode){
		return (DominatorTreeNode)nodeVsDominatorTreeNode.get(graphNode);
	}

	public HashMap<BasicBlock, DominatorTreeNode> getNodeVsDominatorTreeNode() {
		return nodeVsDominatorTreeNode;
	}

	/**
	 * Returns true if node1 dominates node2 in the CFG
	 * @param node1
	 * @param node2
	 * @return
	 */
	public boolean dominates(BasicBlock graphNode1, BasicBlock graphNode2){
		if(graphNode1 == graphNode2)  
			return true;  // Each node dominates itself

		DominatorTreeNode dtNode1 = (DominatorTreeNode)nodeVsDominatorTreeNode.get(graphNode1);
		DominatorTreeNode dtNode2 = (DominatorTreeNode)nodeVsDominatorTreeNode.get(graphNode2);

		// graphNode1 dominates graphNode2 if there is a path in the dominator tree
		// from graphNode1 to graphNode2.

		return pathExists(dtNode1, dtNode2);


	}

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
	 * Returns the nesting levels of each dominator tree node
	 */
	public HashMap<DominatorTreeNode, Integer> getDomLevels(){
		HashMap<DominatorTreeNode, Integer> domLevels = new HashMap<DominatorTreeNode, Integer>();

		Stack<DominatorTreeNode> worklist = new Stack<DominatorTreeNode>();

		domLevels.put(dominatorTreeRoot, new Integer(0));
		worklist.push(dominatorTreeRoot);

		while (!worklist.empty()) {
			DominatorTreeNode dtNode = worklist.pop();
			int childLevel = domLevels.get(dtNode).intValue() + 1;
			Integer childLevelInt = new Integer(childLevel);
			Vector<TreeNode>  childDtNodes = dtNode.getChildren();
			for (TreeNode child : childDtNodes) {
				DominatorTreeNode domChild = (DominatorTreeNode) child;
				domLevels.put(domChild, childLevelInt);
				worklist.push(domChild);
			}
		}

		return domLevels;
	}

	/**
	 * A static utility function that creates a DominatorTree object given the directed graph,
	 * the start node of the directed grap and a map of each node in the graph against its immediate
	 * dominator. 
	 * 
	 * Typically, most algorithms that construct the dominator tree (eg.; Lengauer-Tarjan method)
	 * first determine a map of each node against its immediate dominator. This function provides
	 * a single location to create the dominator tree out of that information.
	 */

	public static DominatorTree create(Function function, 
			HashMap<BasicBlock, BasicBlock> immediateDominators){

		// First create the dominator tree nodes and a mapping
		// of graph nodes and dominator nodes
		HashMap<BasicBlock, DominatorTreeNode> nodeVsDominators = 
					new HashMap<BasicBlock, DominatorTreeNode>();
		DominatorTreeNode dominatorTreeRoot = null;
		
		Iterator<BasicBlock> basicBlockIterator = function.basicBlocksIterator();
		while(basicBlockIterator.hasNext()) {
			BasicBlock node = basicBlockIterator.next();
			TreeNode treeNode = new TreeNode(node.getName());
			DominatorTreeNode dominatorTreeNode = 
				new DominatorTreeNode(treeNode, node);
			if(node == function.getStartNode())
				dominatorTreeRoot = dominatorTreeNode;
			nodeVsDominators.put(node, dominatorTreeNode);
		}

		// Populate the tree from the immediateDominators HashMap
		Set<BasicBlock> keys = immediateDominators.keySet();
		Iterator<BasicBlock> keyIterator = keys.iterator();
	
		while(keyIterator.hasNext()) {
			//Node node = (Node)entry.getKey();
			BasicBlock node = keyIterator.next();
			//Node domNode = (Node)entry.getValue();
			BasicBlock domNode = immediateDominators.get(node);

			DominatorTreeNode dtNode = (DominatorTreeNode) nodeVsDominators.get(node);
			if(domNode == null){
				// node must have had the starting node as it's immediate dominator
				dominatorTreeRoot.addChild(dtNode);
			}
			else {
				DominatorTreeNode dtDomNode = (DominatorTreeNode) nodeVsDominators.get(domNode);
				dtDomNode.addChild(dtNode);
			}
		}

		// Create a new dominator tree object, populate it and return it
		DominatorTree dominatorTree = new DominatorTree();
		dominatorTree.setDominatorTreeRoot(dominatorTreeRoot);
		dominatorTree.setGraphNodeVsDominatorTreeNode(nodeVsDominators);

		return dominatorTree;
	}
	
	public void print(){
		LLVMIREmitter emitter = LLVMIREmitter.getInstance();
		Vector<TreeNode> children = dominatorTreeRoot.getChildren();
		LOG.debug("Root node in dominator tree: {} with children: {}", emitter.getValidName(dominatorTreeRoot.getGraphNode()));
		String childNodes = "";
		for(TreeNode treeNode : children){
			DominatorTreeNode child = (DominatorTreeNode) treeNode;
			childNodes += emitter.getValidName(child.getGraphNode()) + ", ";
		}
		
		for(TreeNode treeNode : children){
			print((DominatorTreeNode) treeNode);
		}
		
	}
	
	public void print(DominatorTreeNode dtNode){
		LLVMIREmitter emitter = LLVMIREmitter.getInstance();
		
		Vector<TreeNode> children = dtNode.getChildren();
		
		
		String treeNodeDesc = "";
		for(TreeNode treeNode : children){
			DominatorTreeNode child = (DominatorTreeNode) treeNode;
			BasicBlock bb = child.getGraphNode();
			
			treeNodeDesc += emitter.getValidName(bb) + ", ";
		}
		
		LOG.debug("Node in tree: {} with children {}", emitter.getValidName(dtNode.getGraphNode()), treeNodeDesc);
		
		for(TreeNode treeNode : children){
			print((DominatorTreeNode) treeNode);
		}
		
	}
}

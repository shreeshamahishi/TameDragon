package org.tamedragon.common.controlflowanalysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.tamedragon.common.DominatorTree;
import org.tamedragon.common.SpanningTree;
import org.tamedragon.common.SpanningTreeDFS;
import org.tamedragon.common.SpanningTreeNode;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.Function;

public class LengauerTarjan extends DominatorTreeStrategy{
	private HashMap<BasicBlock,BasicBlock> semidominators; 
	private HashMap<BasicBlock,BasicBlock> ancestors;
	private HashMap<BasicBlock, BasicBlock> immediateDominators;
	private HashMap<BasicBlock,BasicBlock> sameDominators;
	private SpanningTree spanningTree;
	
	public LengauerTarjan(SpanningTree spanningTree){
		semidominators = new HashMap<BasicBlock,BasicBlock>();
		ancestors = new HashMap<BasicBlock,BasicBlock>();
		immediateDominators = new HashMap<BasicBlock,BasicBlock>();
		sameDominators = new HashMap<BasicBlock,BasicBlock>();
		this.spanningTree = spanningTree;
	}

	@Override
	public DominatorTree getDominatorTree(Function function, boolean onReverseCFG){
		// Get the spanning tree from the input graph		
		if(spanningTree == null){
			SpanningTreeDFS spanningTreeDFS = new SpanningTreeDFS();
			spanningTree = (SpanningTree)spanningTreeDFS.execute(function);
		}

		HashMap<BasicBlock,List<BasicBlock>> semiDomVsNodes = new HashMap<BasicBlock,List<BasicBlock>>();

		// Loop through nodes in the graph starting with the highest depth first number and 
		// going backwards through to the root (excluding the root)
		
		ListIterator<SpanningTreeNode> reverseTreeNodeIterator = spanningTree.reverseVisitedNodesIterator();
		while(reverseTreeNodeIterator.hasPrevious()) {
			SpanningTreeNode stNode = reverseTreeNodeIterator.previous();
			if(stNode == spanningTree.getTreeRoot()){
				break;
			}
			
			BasicBlock graphNode = stNode.getGraphNode();
			BasicBlock parent = spanningTree.getParentInTree(graphNode);
			BasicBlock semiDominator = parent;    // Initialize the semidominator to the parent
			BasicBlock temp = null;

			List<BasicBlock> predecessors = function.getCfg().getPredecessors(graphNode);
			int numPredecessors = predecessors.size();
			long dfNumOfNode = spanningTree.getDepthFirstNumber(graphNode);

			// The following loop calculates the semidominator using the fact that the semidominator is the
			// node with the following properties:
			// 1. If the predecessor is an ancestor of the current node in the spanning tree, it is a candidate
			//    to be the semidominator
			// 2. Else, if it is not an an ancestor, the semidominator of the predecessor with the smallest
			//    depth number
			// 3. The semidominator is the node of the above list with the smallest depth number 

			for(int j = 0; j < numPredecessors; j++){
				BasicBlock prdNode = (BasicBlock)predecessors.get(j);				
				long dfNumOfPred = spanningTree.getDepthFirstNumber(prdNode);
				if(dfNumOfPred <= dfNumOfNode)    // Predecessor is an ancestor
					temp = prdNode;
				else                              // Predecessor is an NOT an ancestor
					temp = (BasicBlock)semidominators.get(ancestorWithLowestSemi(prdNode));

				if(spanningTree.getDepthFirstNumber(temp) <
						spanningTree.getDepthFirstNumber(semiDominator))
					semiDominator = temp;
			}

			// Put the semidominator determined above into the semidominator bucket (HashMap)
			semidominators.put(graphNode, semiDominator);
			addToSemiDominatorBucket(semiDomVsNodes, semiDominator, graphNode);
			link(parent, graphNode);

			// Calculate the immediate dominator
			List<BasicBlock>  childrenOfSemiDominator = semiDomVsNodes.get(parent);
			int numChildren = 0;
			if(childrenOfSemiDominator != null)
				numChildren = childrenOfSemiDominator.size();

			for(int k = 0; k < numChildren; k++){
				BasicBlock child = (BasicBlock) childrenOfSemiDominator.get(k);
				BasicBlock prevNode = ancestorWithLowestSemi(child);
				if(semidominators.get(prevNode) == semidominators.get(child))
					immediateDominators.put(child, parent);
				else
					sameDominators.put(child, prevNode);
			}
		}

		// Deferred dominator calculations
		Iterator<BasicBlock> basicBlockIterator = function.basicBlocksIterator();
		while(basicBlockIterator.hasNext()) {
			BasicBlock node = basicBlockIterator.next();
			BasicBlock sameNode =  sameDominators.get(node);
			if(sameNode != null)
				immediateDominators.put(node, (BasicBlock)immediateDominators.get(sameNode));
		}

		// Create the dominator tree
		return DominatorTree.create(function,immediateDominators);
	}

	private BasicBlock ancestorWithLowestSemi(BasicBlock node){
		BasicBlock temp = node;
		while(ancestors.get(node) != null){
			BasicBlock semiDomOfNode = (BasicBlock) semidominators.get(node);
			BasicBlock semiDomOfTempNode = (BasicBlock) semidominators.get(temp);
			if(spanningTree.getDepthFirstNumber(semiDomOfNode) <
					spanningTree.getDepthFirstNumber(semiDomOfTempNode))
				temp = node;
			node = ancestors.get(node);
		}
		return temp;
	}

	private void link(BasicBlock parent, BasicBlock node){
		ancestors.put(node, parent);
	}

	private void addToSemiDominatorBucket(HashMap<BasicBlock,List<BasicBlock>> semiDomVsNodes, 
			BasicBlock semiDominator, BasicBlock graphNode){
		List<BasicBlock> nodes = (List<BasicBlock>)  semiDomVsNodes.get(semiDominator);
		if(nodes == null) {
			nodes = new ArrayList<BasicBlock>();
		}

		nodes.add(graphNode);
		semiDomVsNodes.put(semiDominator, nodes);
	}
}
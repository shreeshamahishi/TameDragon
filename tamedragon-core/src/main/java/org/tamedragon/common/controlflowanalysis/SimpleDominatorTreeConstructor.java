package org.tamedragon.common.controlflowanalysis;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.jgraph.graph.DefaultEdge;
import org.jgrapht.traverse.DepthFirstIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tamedragon.common.DominatorTree;
import org.tamedragon.common.llvmir.instructions.CFG;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.utils.LLVMIREmitter;

/**
 * This class implements a simple, but sub-optimal algorithm to construct a dominator
 * tree from a directed graph. The algorithm is based on the pseduo-code in Chapter 7 - 
 * Control-Flow Analysis in the book "Advanced Compiler Design & Implementation" by 
 * Steven Muchnick (@1997 edition Morgan Kaufmann Publishers, Inc.)
 * 
 * The dominator tree is constructed in primary two steps: the first step determines 
 * all the dominators of a given node in the graph. The second step uses the dominators
 * found in the first step and constructs a map of each node against its immediate 
 * dominators.
 * 
 * A final minor step uses the immediate dominator map to create the dominator tree.
 * 
 */
public class SimpleDominatorTreeConstructor extends DominatorTreeStrategy{

	private BasicBlock startNode;
	private Function function;
	private CFG cfg;

	HashMap<BasicBlock, Set<BasicBlock>> dominators;
	
	private static final Logger LOG = LoggerFactory.getLogger(SimpleDominatorTreeConstructor.class);
	
	public SimpleDominatorTreeConstructor(){
		dominators = new HashMap<BasicBlock, Set<BasicBlock>>();
	}

	@Override
	public DominatorTree getDominatorTree(Function function, boolean onReverseCFG) {
		this.function = function;
		if(onReverseCFG){
			cfg = function.getCfg().reverse();
			this.startNode = cfg.firstNode();
		}
		else{
			cfg = function.getCfg();
			this.startNode = function.getStartNode();
		}

		// Construct the dominators for each node in the graph
		constructDominators();

		// Construct a map of node against its immediate dominator
		HashMap<BasicBlock, BasicBlock> immediateDominators = constructImmediateDoms();

		// Create the dominator tree from the mapping above and return it
		return DominatorTree.create(function, immediateDominators);
	}

	/**
	 * Populates the dominators for each node in the input graph. The 
	 * dominators are represented as a HashMap with a node in the graph
	 * as the key and the set of dominators of that node as the value.
	 */
	private void constructDominators() {

		// Set the dominator of the entry node as itself
		HashSet<BasicBlock> dominatorsOfNode = new HashSet<BasicBlock>();
		dominatorsOfNode.add(startNode);
		dominators.put(startNode, dominatorsOfNode);
		HashSet<BasicBlock> tempDoms = new HashSet<BasicBlock>();
		Iterator<BasicBlock> basicBlockIterator1 = function.basicBlocksIterator();
		while(basicBlockIterator1.hasNext()) {
			tempDoms.add(basicBlockIterator1.next());
		}

		Iterator<BasicBlock> basicBlockIterator2 = function.basicBlocksIterator();
		while(basicBlockIterator2.hasNext()) {
			BasicBlock node = basicBlockIterator2.next();
			if(node != startNode)
				dominators.put(node, tempDoms);
		}

		if(tempDoms.size() == 1){
			return;
		}
		
		boolean change = false;
		do{
			change = true;

			DepthFirstIterator<BasicBlock, DefaultEdge> dfsIterator = 
				new DepthFirstIterator<BasicBlock, DefaultEdge>(cfg, cfg.getStartNode());

			while(dfsIterator.hasNext()){
				BasicBlock basicBlock = dfsIterator.next();
				if(basicBlock != startNode){
					change = updateDominatorsForNode(basicBlock);
				}
			}
		}
		while(change);

		if(LOG.isDebugEnabled()){
			printDoms();
		}
	}

	private boolean updateDominatorsForNode(BasicBlock node){

		boolean change = false;
		Set<BasicBlock> initialDominatorsForNode = dominators.get(node);
		Set<BasicBlock> tempNodes = initTempNodes();

		List<BasicBlock> predecessors = cfg.getPredecessors(node);

		for(BasicBlock pred : predecessors){
			Set<BasicBlock> dominatorsOfPred = dominators.get(pred);
			tempNodes = getIntersection(tempNodes, dominatorsOfPred);
		}

		tempNodes.add(node);

		if(!(tempNodes.containsAll(initialDominatorsForNode) &&
				tempNodes.size() == initialDominatorsForNode.size()))
		{
			change = true;
			dominators.put(node, tempNodes);
		}
		return change;
	}

	public static Set<BasicBlock> getIntersection(Set<BasicBlock> set1, Set<BasicBlock> set2) {
		boolean set1IsLarger = set1.size() > set2.size();
		Set<BasicBlock> cloneSet = new HashSet<BasicBlock>(set1IsLarger ? set2 : set1);
		cloneSet.retainAll(set1IsLarger ? set1 : set2);
		return cloneSet;
	}

	private Set<BasicBlock> initTempNodes() {
		Set<BasicBlock> tempNodes = new HashSet<BasicBlock>();
		Iterator<BasicBlock> basicBlockIterator = function.basicBlocksIterator();
		while(basicBlockIterator.hasNext()) {
			BasicBlock node = basicBlockIterator.next();
			tempNodes.add(node);
		}

		return tempNodes;
	}

	private void printDoms(){
		LLVMIREmitter emitter = LLVMIREmitter.getInstance();
		Set<BasicBlock> keys = dominators.keySet();
		Iterator<BasicBlock> iter = keys.iterator();
		while(iter.hasNext()){
			BasicBlock key = iter.next();
			Set<BasicBlock> vals = dominators.get(key);
			
			Iterator<BasicBlock> iter2 = vals.iterator();
			String dominators = "";
			while(iter2.hasNext()){
				dominators += emitter.getValidName(iter2.next()) + ",";
			}
			//LOG.debug("For node {} ", emitter.getValidName(key), " the doms are : {}", dominators);
		}
	}

	private HashMap<BasicBlock, BasicBlock> constructImmediateDoms() {
		HashMap<BasicBlock, List<BasicBlock>> tempDominators = constructListRepresentationOfDominators();
		HashMap<BasicBlock, BasicBlock> immediateDominators = new HashMap<BasicBlock, BasicBlock>();

		DepthFirstIterator<BasicBlock, DefaultEdge> dfsIterator = 
			new DepthFirstIterator<BasicBlock, DefaultEdge>(function.getCfg(), function.getStartNode());

		while(dfsIterator.hasNext()){
			BasicBlock basicBlock = dfsIterator.next();
			if(basicBlock == startNode){
				continue;
			}
			
			constructIDomForNode(basicBlock, tempDominators, immediateDominators);
		}

		return immediateDominators;
	}

	private HashMap<BasicBlock, List<BasicBlock>> constructListRepresentationOfDominators() {

		HashMap<BasicBlock, List<BasicBlock>> tempDominators = new HashMap<BasicBlock, List<BasicBlock>>();

		Set<BasicBlock> keys = dominators.keySet();
		Iterator<BasicBlock> keyIter = keys.iterator();
		while(keyIter.hasNext()){
			List<BasicBlock> domList = new ArrayList<BasicBlock>();
			BasicBlock node = keyIter.next();
			Set<BasicBlock> domsOfNode = dominators.get(node);
			Iterator<BasicBlock> domIterator = domsOfNode.iterator();
			while(domIterator.hasNext()){
				BasicBlock dom = domIterator.next();
				if(dom == node)
					continue;
				domList.add(dom);
			}
			tempDominators.put(node, domList);
		}
		return tempDominators;
	}

	private void constructIDomForNode(BasicBlock node, HashMap<BasicBlock, List<BasicBlock>> tempDominators,
			HashMap<BasicBlock, BasicBlock> immediateDominators){

		List<BasicBlock> domsList = tempDominators.get(node);

		BitSet bitSetOfDoms = new BitSet(domsList.size());
		bitSetOfDoms.set(0, domsList.size(), true);

		int outerCount = 0;
		for(BasicBlock domNode : domsList){
			if(!bitSetOfDoms.get(outerCount)){
				outerCount++;
				continue;
			}

			int innerCount = 0;
			for(BasicBlock otherDomNode : domsList){

				if(otherDomNode == domNode){
					innerCount++;
					continue;
				}

				if(dominators.get(domNode).contains(otherDomNode))
					bitSetOfDoms.clear(innerCount);
				innerCount++;
			}
			outerCount++;
		}

		int setBit = bitSetOfDoms.nextSetBit(0);
		BasicBlock idomForNode = domsList.get(setBit);

		immediateDominators.put(node, idomForNode);
	}
}



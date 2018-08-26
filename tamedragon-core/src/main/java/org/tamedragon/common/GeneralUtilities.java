package org.tamedragon.common;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.tamedragon.common.llvmir.instructions.CFG;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.Function;

public class GeneralUtilities {
	/*
	 * Given the control flow graph and it's corresponding dominator tree, this function returns
	 * the dominance frontier for nodes in the control flow graph.
	 */
	public static HashMap<BasicBlock, HashSet<BasicBlock>> getDominanceFrontiers(
			Function function, DominatorTree dominatorTree){
		
		HashMap<BasicBlock, HashSet<BasicBlock>> dominanceFrontiers = new HashMap<BasicBlock, HashSet<BasicBlock>>();
		
		Iterator<BasicBlock> basicBlockIterator = function.basicBlocksIterator();
		while(basicBlockIterator.hasNext()) {
			// Identify nodes with more than predecessor
			BasicBlock node = basicBlockIterator.next();
			List<BasicBlock> predecessors = function.getCfg().getPredecessors(node);
			int numPreds = predecessors.size();

			if(numPreds <= 1)
				continue;

			// This node has more than predecessor
			BasicBlock immediateDominator = dominatorTree.getParentInTree(node);
			for(int j = 0; j < numPreds; j++){
				BasicBlock predecessor = predecessors.get(j);
				BasicBlock runner = predecessor;
				while(runner != immediateDominator){

					HashSet<BasicBlock> members = (HashSet<BasicBlock>)dominanceFrontiers.get(runner);
					if(members == null) 
						members = new HashSet<BasicBlock>();
					members.add(node);
					dominanceFrontiers.put(runner, members);

					runner = dominatorTree.getParentInTree(runner);
				}
			}
		}

		return dominanceFrontiers;
	}

	public static void addToDominanceFrontier(
			HashMap<BasicBlock, HashSet<BasicBlock>> dominanceFrontiers, BasicBlock node, BasicBlock frontierMember){
		HashSet<BasicBlock> members = dominanceFrontiers.get(node);
		if(members == null) members = new HashSet<BasicBlock>();
		members.add(frontierMember);
		dominanceFrontiers.put(node, members);
	}
}

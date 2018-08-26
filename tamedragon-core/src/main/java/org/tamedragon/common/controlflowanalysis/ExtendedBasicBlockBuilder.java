package org.tamedragon.common.controlflowanalysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.tamedragon.common.llvmir.instructions.CFG;
import org.tamedragon.common.llvmir.types.BasicBlock;

public class ExtendedBasicBlockBuilder {

	private CFG cfg;
	
	private Set<BasicBlock> ebbRoots;
	private Map<BasicBlock, List<BasicBlock>> allEbbs;
	
	public ExtendedBasicBlockBuilder(final CFG controlFlowGraph){
		this.cfg = controlFlowGraph;
		
		ebbRoots = new HashSet<BasicBlock>();
		ebbRoots.add(cfg.getStartNode());
		
		allEbbs = new HashMap<BasicBlock, List<BasicBlock>>();
	}
	
	public Map<BasicBlock, List<BasicBlock>> build(){
		
		while(ebbRoots.size() > 0){
			
			// Get an arbitrary element from the set of roots that 
			// have already been determined
			Iterator<BasicBlock> iterator = ebbRoots.iterator();
			BasicBlock rootNode = iterator.next();
			ebbRoots.remove(rootNode);
			
			if(!allEbbs.containsKey(rootNode)){
				allEbbs.put(rootNode, buildEbb(rootNode));
			}
		}
		
		return allEbbs;
		
	}

	private List<BasicBlock> buildEbb(BasicBlock rootNode) {
		List<BasicBlock> ebb = new ArrayList<BasicBlock>();
		addBB(rootNode, ebb);
		return ebb;
	}
	
	private void addBB(BasicBlock rootNode, List<BasicBlock> ebb){
		ebb.add(rootNode);
		
		CFG cfg = rootNode.getParent().getCfg();
		
		List<BasicBlock> successors = cfg.getSuccessors(rootNode);
		for(BasicBlock succNode : successors){
			List<BasicBlock> preds = cfg.getPredecessors(succNode);
			if(preds.size() == 1 && !ebb.contains(succNode))
				addBB(succNode, ebb);
			else{
				ebbRoots.add(succNode);
			}
		}
	}
}

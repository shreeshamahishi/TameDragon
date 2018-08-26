package org.tamedragon.common.optimization;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.Map.Entry;

import org.tamedragon.common.EnvironmentConstants;
import org.tamedragon.common.llvmir.instructions.Instruction;
import org.tamedragon.common.llvmir.types.BasicBlock;

public class Transformations {
	
	public static final int DEAD_CODE_ELIMINATION = 1;
	public static final int REMOVAL_OF_REDUDANT_CONDITIONS_CREATED_BY_TRANSLATION = 2;
	public static final int SIMPLE_OPTIMIZATIONS = 3;
	public static final int SPARSE_CONDITIONAL_CONSTANT_PROPAGATION = 4;
	public static final int LOOP_OPTIMIZATIONS = 5;
	public static final int INSTRUCTION_SCHEDULING = 6;
	public static final int LOOP_INVARIANT_CODE_MOTION = 7;
	public static final int LOOP_STRENGTH_REDUCE = 8;
	public static final int LOOP_UNSWITCHING = 9;
	public static final int TAIL_RECURSIVE_CALL_ELIMINATION = 10;
	public static final int TAIL_MERGING = 11;
	public static int BRANCH_OPTIMIZATIONS = 12;
	private int type;
	
	// The following data structures represent the changes that occurred due to the
	// transformations
	private LinkedHashMap<BasicBlock, HashMap<Instruction, Instruction>> replacementsInNode;
	private HashMap<BasicBlock, Vector<Instruction>> deletionsInNodes;	
	private LinkedHashMap<String, String> stringReplacements;
	private LinkedHashMap<Instruction, Instruction> insReplacements;
	private Vector<Instruction> deletions;
	private Vector<BasicBlock> removedBlocks;
	private LinkedHashMap<BasicBlock, HashMap<String, String>> updatedInstructionsInNode;
	
	
	public int getType(){
		return type;
	}

	public Transformations(int type){
		this.type = type;		
		
		replacementsInNode = new LinkedHashMap<BasicBlock, HashMap<Instruction,Instruction>>();
		deletionsInNodes = new HashMap<BasicBlock, Vector<Instruction>>();
		stringReplacements = new LinkedHashMap<String, String>();
		insReplacements = new LinkedHashMap<Instruction, Instruction>();
		deletions = new Vector<Instruction>();
		removedBlocks = new Vector<BasicBlock>();
		updatedInstructionsInNode = new LinkedHashMap<BasicBlock, HashMap<String,String>>();
		
	}
	
	public void addToDeletions(BasicBlock block, Instruction ins){
		Vector<Instruction> instrs = deletionsInNodes.get(block);
		if(instrs == null){
			instrs = new Vector<Instruction>();
		}
		instrs.addElement(ins);
		deletionsInNodes.put(block, instrs);		
	}
	
	public void addToDeletions(Instruction ins){
		deletions.addElement(ins);
	}
		
	public void addToReplacementsInNode(BasicBlock block, 
			Instruction oldIns, Instruction newIns){
		HashMap<Instruction, Instruction> oldAndNewInstrs = 
			replacementsInNode.get(block);
		if(oldAndNewInstrs == null){
			oldAndNewInstrs = new HashMap<Instruction, Instruction>();
		}
		oldAndNewInstrs.put(oldIns, newIns);
		replacementsInNode.put(block, oldAndNewInstrs);		
	}
	
	public void addToStringReplacements(String oldIns, String newIns){
		stringReplacements.put(oldIns, newIns);		
	}
	
	public void addToInsReplacements(Instruction oldIns, Instruction newIns){
		insReplacements.put(oldIns, newIns);
	}
	
	public void addToRemovedBlocks(BasicBlock cfNode){
		removedBlocks.addElement(cfNode);
	}
	
	public void addToUpdatedInstructionsInNode(BasicBlock cfNode, String oldSSAPhiFunc,
			String newSSAPhiFunc){
		HashMap<String, String> oldAndNewInstrs = 
			updatedInstructionsInNode.get(cfNode);
		if(oldAndNewInstrs == null){
			oldAndNewInstrs = new HashMap<String, String>();
		}
		oldAndNewInstrs.put(oldSSAPhiFunc, newSSAPhiFunc);
		updatedInstructionsInNode.put(cfNode, oldAndNewInstrs);	
	}
	
	public boolean transformationOccurred(){
		if(type == DEAD_CODE_ELIMINATION){
			// For dead code elimination transformation, replacements and deletion
			// information should be available
			if(deletionsInNodes.size() == 0 && replacementsInNode.size() == 0)
				return false;	
			return true;
		}
		else if(type == REMOVAL_OF_REDUDANT_CONDITIONS_CREATED_BY_TRANSLATION){
			// For removal of redundant conditions created by translation transformation, 
			// replacements and removed blocks information should be available
			if(replacementsInNode.size() == 0 && removedBlocks.size() == 0)
				return false;
			return true;
		}
		else if(type == SIMPLE_OPTIMIZATIONS){
			// For simple optimization transformation, instruction replacements, string replacements
			// and deletions should be available										
			if(insReplacements.size() == 0 && deletions.size() == 0 && stringReplacements.size() == 0)
				return false;
			return true;
		}
		else if(type == SPARSE_CONDITIONAL_CONSTANT_PROPAGATION){
			// For SCCP, replacement in nodes, removed blocks and updated instructions in nodes
			// should be available
			if(replacementsInNode.size() == 0 &&  removedBlocks.size() == 0
		          && updatedInstructionsInNode.size() == 0 && deletionsInNodes.size() == 0)
				return false;
			return true;
		}
		else if(type == LOOP_OPTIMIZATIONS){
			//TODO - Implement this
			return false;
		}
		else if(type == INSTRUCTION_SCHEDULING){
			//TODO - Implement this
			return false;
		}
		else
			return false;
		
	}
	
	public String getTransformationDescriptions(){
		String desc = "";
		if(type == DEAD_CODE_ELIMINATION){
			desc = "TRANSFORMATIONS FOR DEAD CODE ELIMINATION:" + EnvironmentConstants.NEWLINE;
			desc +=  getDescForDeadCodeElimination();			
		}
		else if(type == REMOVAL_OF_REDUDANT_CONDITIONS_CREATED_BY_TRANSLATION){
			desc = "TRANSFORMATIONS FOR REMOVAL OF REDUNDANT CONDITIONS CREATED BY TRANSLATION:"
				+ EnvironmentConstants.NEWLINE;
			desc += getDescForRedundantCondsCreatedByTransRemoval();
		}
		else if(type == SIMPLE_OPTIMIZATIONS){
			desc = "TRANSFORMATIONS FOR SIMPLE OPTIMIZATIONS:" + EnvironmentConstants.NEWLINE;
			desc +=  getDescForSimpleOpts();		
		}
		else if(type == SPARSE_CONDITIONAL_CONSTANT_PROPAGATION){
			desc = "TRANSFORMATIONS FOR SPARSE CONDITIONAL CONSTANT PROPAGATION:" + EnvironmentConstants.NEWLINE;
			desc +=  getDescForSCCP();		
		}
		else if(type == LOOP_OPTIMIZATIONS){
			desc = "";
		}
		else if(type == INSTRUCTION_SCHEDULING){
			desc = "";		
		}
		else
			desc = "";
		
		desc += EnvironmentConstants.NEWLINE;
		return desc;
	}
	
	private String getDescForSCCP(){
		if(replacementsInNode.size() == 0 &&  removedBlocks.size() == 0
		          && updatedInstructionsInNode.size() == 0 && deletionsInNodes.size() == 0)
			return "No transformation was applied since no conditions were found for SCCP.";
		
		// First the replacements in the nodes
		String description = "";
		if(replacementsInNode.size() == 0){
			description += "No instruction was modified" + EnvironmentConstants.NEWLINE;
		}
		else{
			description += "The following instructions were modified:" + EnvironmentConstants.NEWLINE;
			description += getDescFromReplacementsInNode();
		}
		
		// Now the removed blocks
		if(removedBlocks.size() == 0){
			description += "No basic block was removed";
		}
		else{
			description += "The following basic blocks were removed:" + EnvironmentConstants.NEWLINE;
			description += getDescFromRemovedBlocks();
		}
		
		// Now the updated instructions in node
		if(updatedInstructionsInNode.size() == 0){
			description += "No instructions were updated" + EnvironmentConstants.NEWLINE;
		}
		else{
			description += "The following instructions were updated:" + EnvironmentConstants.NEWLINE;
			description += getDescFromUpdatedInsInNode();
		}
		
		// Now the deleted instructions in node
		if(deletionsInNodes.size() == 0){
			description += "No instruction was removed" + EnvironmentConstants.NEWLINE;
		}
		else{
			description += "The following instructions were removed:" + EnvironmentConstants.NEWLINE;
			description += getDescFromDeletionsInNode();
		}
		
		
		return description;
	}
	
	public String getDescForRedundantCondsCreatedByTransRemoval(){
		if(replacementsInNode.size() == 0 && removedBlocks.size() == 0){
			return "No transformation was applied since no redundant conditions created by the translation were found.";
		}
		
		// First the replacements in nodes
		String description = "";
		if(replacementsInNode.size() == 0){
			description += "No instruction was modified" + EnvironmentConstants.NEWLINE;
		}
		else{
			description += "The following instructions were modified:" + EnvironmentConstants.NEWLINE;
			description += getDescFromReplacementsInNode();
		}
		
		// Now the removed blocks
		if(removedBlocks.size() == 0){
			description += "No basic block was removed";
		}
		else{
			description += "The following basic blocks were removed:" + EnvironmentConstants.NEWLINE;
			description += getDescFromRemovedBlocks();
		}
		
		return description;
	}
	
	public String getDescForDeadCodeElimination(){		
		if(deletionsInNodes.size() == 0 && replacementsInNode.size() == 0){
			return "No transformation was applied since no dead code was detected.";
		}
		String description = "";
		
		// First the deletions
		if(deletionsInNodes.size() == 0){
			description += "No instruction was removed" + EnvironmentConstants.NEWLINE;
		}
		else{
			description += "The following instructions were removed:" + EnvironmentConstants.NEWLINE;
			description += getDescFromDeletionsInNode();
		}
		
		// Now the replacements
		if(replacementsInNode.size() == 0){
			description += "No instruction was modified" + EnvironmentConstants.NEWLINE;
			return description;
		}
		
		description += "The following instructions were modified:" + EnvironmentConstants.NEWLINE;
		description += getDescFromReplacementsInNode();
					
		return description;
	}
	
	/*
	 * Returns a string describing the transformations that took place during
	 * transformation using simple optimizations
	 */
	public  String getDescForSimpleOpts(){
		String description = "";
		
		if(insReplacements.size() == 0 && deletions.size() == 0 && stringReplacements.size() == 0){
			return "No transformation was applied since there was no scope for copy/constant propagation nor for constant folding.";
		}
		
		// First the instruction replacements		
		if(insReplacements.size() == 0){
			description += "No instruction was replaced with any other" + EnvironmentConstants.NEWLINE;
		}
		else{
			description += "The following instructions were replaced as follows:" + EnvironmentConstants.NEWLINE;
			description += getDescFromInsReplacements();
		}
		
		// Now the "string" replacements
		if(stringReplacements.size() == 0){
			description += "No instruction was modified" + EnvironmentConstants.NEWLINE;
		}
		else{
			description += "The following instructions were modified as follows:" + EnvironmentConstants.NEWLINE;
			description += getDescFromStringReplacements();
		}
				
		// Now the deletions
		if(deletions.size() == 0){
			description += "No instruction was deleted" + EnvironmentConstants.NEWLINE;
		}
		else{
			description += "The following instructions were deleted:" + EnvironmentConstants.NEWLINE;
			description += getDescFromDeletions();
		}
		
		return description;
	}
	
	private String getDescFromDeletionsInNode(){
		String description = "";
		
		Set<Map.Entry<BasicBlock, Vector<Instruction>>> entries = deletionsInNodes.entrySet();
		Iterator<Entry<BasicBlock, Vector<Instruction>>> iter = entries.iterator();
		while(iter.hasNext())
		{
			Entry<BasicBlock, Vector<Instruction>> entry = iter.next();
			BasicBlock cfNode = (BasicBlock) entry.getKey();
			description += "In node " + cfNode.getName() + ":"+ EnvironmentConstants.NEWLINE ;
			Vector<Instruction> deletionsInNode = (Vector<Instruction>)entry.getValue();
			for(int i = 0; i < deletionsInNode.size(); i++){
				Instruction ins = deletionsInNode.elementAt(i);
				description += ins.toString();
			}
		}
		
		description += EnvironmentConstants.NEWLINE;
		return description;
	}
	
	private String getDescFromReplacementsInNode(){
		String description = "";
		
		Set<Entry<BasicBlock, HashMap<Instruction, Instruction>>> entries = replacementsInNode.entrySet();
		Iterator<Entry<BasicBlock, HashMap<Instruction, Instruction>>> iter = entries.iterator();
		
		while(iter.hasNext())
		{
			Entry<BasicBlock, HashMap<Instruction, Instruction>> entry = iter.next();
			BasicBlock cfNode = (BasicBlock) entry.getKey();
			
			description += "In node " + cfNode.getName() + ":" + EnvironmentConstants.NEWLINE;
			HashMap<Instruction, Instruction> replacements  = 
				(HashMap<Instruction, Instruction>)entry.getValue();
			
			Set<Entry<Instruction, Instruction>> repEntries = replacements.entrySet();
			Iterator<Entry<Instruction, Instruction>> repIter = repEntries.iterator();
			while(repIter.hasNext())
			{
				Entry<Instruction, Instruction> repEntry = repIter.next();
				Instruction oldIns = (Instruction) repEntry.getKey();
				Instruction newIns = (Instruction) repEntry.getValue();
				
				String oldStr = oldIns.toString();
				int indexOfNewLine = oldStr.indexOf(EnvironmentConstants.NEWLINE);
				oldStr = oldStr.substring(0, indexOfNewLine);
				
				description += oldStr + " transformed to: " + newIns.toString();
			}
		}
		description += EnvironmentConstants.NEWLINE;
		return description;
	}
	
	private String getDescFromRemovedBlocks(){
		String description = "";
		for(int i = 0; i < removedBlocks.size(); i++){
			BasicBlock node = removedBlocks.elementAt(i);
			description += node.getName() + ", ";
		}
		
		// Remove the last comma
		int indexOfLastComma = description.lastIndexOf(",");
		if(indexOfLastComma > 0){
			description = description.substring(0, indexOfLastComma);
		}
		
		description += EnvironmentConstants.NEWLINE;
		description += EnvironmentConstants.NEWLINE;
		return description;
	}
	
	private String getDescFromInsReplacements(){
		String description = "";
		Set<Entry<Instruction, Instruction>> entries = insReplacements.entrySet();
		Iterator<Entry<Instruction, Instruction>>  iter = entries.iterator();
		while(iter.hasNext())
		{
			Entry<Instruction, Instruction>  entry =  iter.next();
			Instruction oldIns = (Instruction) entry.getKey();
			Instruction newIns = (Instruction) entry.getValue();
			
			String oldStr = oldIns.toString();
			int indexOfNewLine = oldStr.indexOf(EnvironmentConstants.NEWLINE);
			oldStr = oldStr.substring(0, indexOfNewLine);
			
			description += oldStr + " transformed to: " + newIns.toString();
		}
		description += EnvironmentConstants.NEWLINE;
		return description;
	}
	
	private String getDescFromStringReplacements(){
		String description = "";
		Set<Entry<String, String>> entries = stringReplacements.entrySet();
		Iterator<Entry<String, String>> iter = entries.iterator();
		while(iter.hasNext())
		{
			Entry<String, String> entry =  iter.next();
			String oldIns = (String) entry.getKey();
			String newIns = (String) entry.getValue();
			
			int indexOfNewLine = oldIns.indexOf(EnvironmentConstants.NEWLINE);
			if(indexOfNewLine >= 0)			
				oldIns = oldIns.substring(0, indexOfNewLine);
			
			description += oldIns + " transformed to: " + newIns;
		}
		
		description += EnvironmentConstants.NEWLINE;
		return description;
	}
	
	private String getDescFromDeletions(){
		String description = "";
		
		for(int i = 0; i < deletions.size(); i++){
			Instruction node = deletions.elementAt(i);
			description += node.toString();
		}
		
		description += EnvironmentConstants.NEWLINE;
		return description;
	}
	
	private String getDescFromUpdatedInsInNode(){
		String description = "";
		
		Set<Entry<BasicBlock, HashMap<String, String>>> entries = updatedInstructionsInNode.entrySet();
		Iterator<Entry<BasicBlock, HashMap<String, String>>> iter = entries.iterator();
		while(iter.hasNext())
		{
			Entry<BasicBlock, HashMap<String, String>> entry =  iter.next();
			BasicBlock cfNode = (BasicBlock) entry.getKey();
			
			description += "In node " + cfNode.getName() + ":"+ EnvironmentConstants.NEWLINE ;
			HashMap<String, String> updationsInNode = 
				(HashMap<String, String>)entry.getValue();
			
			Set<Entry<String, String>> repEntries = updationsInNode.entrySet();
			Iterator<Entry<String, String>>  repIter = repEntries.iterator();
			while(repIter.hasNext())
			{
				Entry<String, String>  repEntry = repIter.next();
				String oldIns = (String) repEntry.getKey();
				String newIns = (String) repEntry.getValue();
				
				int indexOfNewLine = oldIns.indexOf(EnvironmentConstants.NEWLINE);
				oldIns = oldIns.substring(0, indexOfNewLine);
				
				description += oldIns + " transformed to: " + newIns;
			}			
		}
		
		description += EnvironmentConstants.NEWLINE;
		return description;
	}
}

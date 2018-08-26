package org.tamedragon.common.optimization;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tamedragon.common.controlflowanalysis.DominatorCalculationContext;
import org.tamedragon.common.llvmir.instructions.CFG;
import org.tamedragon.common.llvmir.instructions.Instruction;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.Value;
import org.tamedragon.common.llvmir.types.Value.ValueTypeID;

public class ADCE {	

	private Function function;
	
	private static final Logger LOG = LoggerFactory.getLogger(ADCE.class);

	public Transformations execute(Function function){
		this.function = function;

		Transformations transformations = new Transformations(Transformations.DEAD_CODE_ELIMINATION);

		// First Create the inverse flow graph from the given control flow graph which is in SSA form

		// Now we have the SSA in the reverse form, create the dominator tree from it
		DominatorCalculationContext dominatorCalculationContext = 
			new DominatorCalculationContext(function);
		dominatorCalculationContext.setLengauerTarjan(null);
		dominatorCalculationContext.setOnReverseCFG(true);
		
		/*DominatorTree dominatorTree = dominatorCalculationContext.getDominatorTree();

		// Get the dominance frontiers - this will determine the reverse dominance frontier in the
		// original flow graph
		HashMap<Node, HashSet<Node>> reverseDominanceFrontiers =
			new HashMap<Node, HashSet<Node>>();

		HashMap<Node, HashSet<Node>> dominanceFrontiers =
			GeneralUtilities.getDominanceFrontiers(reverseCFG, dominatorTree);

		Set<Node> entries = dominanceFrontiers.keySet();
		Iterator<Node> iter = entries.iterator();
		while(iter.hasNext()) {
			CFNode cfNode = (CFNode) iter.next();
			HashSet<Node> dfNodes = dominanceFrontiers.get(cfNode);

			CFNode cfNodeOriginalFlowGraph = 
				controlFlowGraph.getNodeWithName(cfNode.getName());

			HashSet<Node> dfOriginal = new HashSet<Node>();
			Iterator<Node> dfIter = dfNodes.iterator();
			while(dfIter.hasNext()){
				CFNode dfMember = (CFNode) dfIter.next();
				CFNode cfNodeOriginalFlowGraphMember = 
					controlFlowGraph.getNodeWithName(dfMember.getName());
				dfOriginal.add(cfNodeOriginalFlowGraphMember);
			}

			reverseDominanceFrontiers.put(cfNodeOriginalFlowGraph, dfOriginal);
		}*/

		// Get all critical operations
		HashSet<Instruction> workList = getCriticalOperations();

		LOG.info("Initial critical operations found = " + workList.size());

		// Using the set of critical operations as a work-list, mark out other operations as critical
		// that are dependent on already marked ones
		HashSet<Instruction> allCriticalInstructions = new HashSet<Instruction>();

		while(workList.size() > 0){
			// Remove the first instruction
			Iterator<Instruction> instructionIterator = workList.iterator();

			Instruction instr = instructionIterator.next();
			workList.remove(instr);
			LOG.info("Adding to critical instructions list: {}" + instr);
			allCriticalInstructions.add(instr);   // Add to final list

			// Get the list of source variables; for each source, add the instruction that defines it.

			int numOperands = instr.getNumOperands();
			for(int i = 0; i < numOperands; i++){
				Value operand = instr.getOperand(i);
				ValueTypeID valueType = operand.getValueTypeID();
				
				if(valueType != ValueTypeID.INSTRUCTION)
					continue;

				// The operand is an instruction
				if(!allCriticalInstructions.contains(operand))
					workList.add((Instruction)operand);
			}

			/*// For the block in which the instruction is located, get the reverse dominance frontiers;
			// For each block in the frontier, get the last statement which could be a branch and mark it
			CFNode nodeOfInstruction = instr.getParent();
			HashSet<Node> reverseDF = reverseDominanceFrontiers.get(nodeOfInstruction);

			if(reverseDF == null){
				continue;
			}

			// The node has a set of reverse dominance frontiers; iterate through them
			// to identify the last statement (branch) in each of them
			Iterator<Node> dfIter = reverseDF.iterator();
			while(dfIter.hasNext()){
				CFNode dfMember = (CFNode) dfIter.next();
				Vector<Instruction> instrsInNode = dfMember.getInstructions();
				Instruction lastInstr = (Instruction)instrsInNode.lastElement();
				if(!allCriticalInstructions.contains(lastInstr))
					workList.add(lastInstr);
				allCriticalInstructions.add(lastInstr);
			}*/

		}

		// Remove the list of instructions from the flow graph now, by iterating through each control flow node.
		// Remove an instruction if it is in the not in the list of all critical operations 
		Iterator<BasicBlock> basicBlockIterator = function.basicBlocksIterator();
		while(basicBlockIterator.hasNext()) {
			BasicBlock cfNode = basicBlockIterator.next();
			BasicBlock parentBB = (BasicBlock) cfNode;
			Iterator<Instruction> instrsInNode = parentBB.getInstructions();
			List<Instruction> instructionsToBeRemoved = new ArrayList<Instruction>();
			
			while(instrsInNode.hasNext()){				
				Instruction instr =  instrsInNode.next();

				if(allCriticalInstructions.contains(instr))
					continue;
				
				instructionsToBeRemoved.add(instr);
				
				//transformations.addToDeletions(cfNode, instr);  // add to the list of deletions
			}
			

			// Remove the non-critical instructions
			parentBB.removeInstructionSubset(instructionsToBeRemoved);

		}
		return transformations;
	}

	private HashSet<Instruction> getCriticalOperations(){

		// Create a work-list of all critical operations - these include storing into memory,
		// function calls, phi-functions and jumps, Input/Output (not handled here)
		HashSet<Instruction> criticalOperations = new HashSet<Instruction> ();

		Iterator<BasicBlock> basicBlockIterator = function.basicBlocksIterator();
		while(basicBlockIterator.hasNext()) {
			BasicBlock node = basicBlockIterator.next();

			Iterator<Instruction> instrsInNode = node.getInstructions();			
			while(instrsInNode.hasNext()){
				Instruction instr = instrsInNode.next();

				if(instr.isCritical()){
					criticalOperations.add(instr);
				}
			}
		}

		return criticalOperations;
	}
}

package org.tamedragon.common.optimization;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tamedragon.common.LatticeValue;
import org.tamedragon.common.llvmir.instructions.CFG;
import org.tamedragon.common.llvmir.instructions.Instruction;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.Constant;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.UndefValue;
import org.tamedragon.common.llvmir.types.Value;
import org.tamedragon.common.llvmir.types.Value.ValueTypeID;

/**
 * This class implements the classic algorithm to propagate constants
 * aggressively: the sparse conditional constant propagation method.
 * 
 * Two worklists, one for the variables and one for the basic blocks 
 * are used. The algorithm assumes that none of the basic blocks except
 * the first is reachable. Variables are associated with a corresponding
 * lattice values. Lattice values for variables can have only three 
 * states : undefined, constant or overdefined. 
 * 
 * The algorithm executes in three phase. In the first phase, the two
 * worklists are traversed and modified as new evidence is found that
 * a variable is either a constant or is not, based on the lattice values.
 * When evidence is found that a variable is constant, the state of it's 
 * lattice value moves from undefined to constant. At a later stage, when 
 * it is known that the variable, is in fact, not a constant, the state of
 * it's lattice value moves from constant to overdefined. Lattice values 
 * can change state only twice during the traversal : from "undefined" to
 * "constant" and from "contant" to "undefined". If the lattice valus is 
 * already known to be "overdefined", it cannot change its state.
 * 
 * When the state of the lattice value of a variable changes, the variable
 * is added to the variable worklist, since it will effect the instructions
 * that USE that variable. Instructions are also symbolically interpreted; in
 * doing so, if it is found that a terminator instruction evaluates to show 
 * that some basic blocks are indeed found to be reachable, those basic blocks
 * are added to the basic block worklist. Instructions in those basic blocks are
 * then evaluated and those basic blocks are removed from the basic block. The
 * iterations over the worklists proceed until both worklists are empty. The 
 * iterations reveal two sets of information: unreachable basic blocks and the
 * variables that are constant through the execution of the program.
 * 
 * In the next phase, all instructions (except for the terminator instruction) are
 * removed from the basic blocks that were found to be unreachable and all 
 * variables found to be constants are propagated through the code.
 *
 */
public class SparseConditionalConstantPropagation {

	private HashSet<BasicBlock> unreachableNodes;
	private HashMap<Value, LatticeValue> tempVsLatticeValue;

	// The worklists
	private Vector<Value> variableWorkList;
	private Vector<BasicBlock> cfgWorkList;
	
	private static final Logger LOG = LoggerFactory.getLogger(SparseConditionalConstantPropagation.class);

	public Transformations execute(Function function){

		// Create the transformations object
		Transformations transformations = 
			new Transformations(Transformations.SPARSE_CONDITIONAL_CONSTANT_PROPAGATION);

		// Initialize the list of unreachable nodes with all nodes in the cfg
		// (assume all nodes are unreachable)
		unreachableNodes = new HashSet<BasicBlock>();

		// Initialize all the names in the SSA to undefined; also mark all the 
		// nodes in the cfg as unreachable
		tempVsLatticeValue = new HashMap<Value, LatticeValue>();
		Vector<BasicBlock> allNodes = new Vector<BasicBlock>();
		Iterator<BasicBlock> basicBlockIterator = function.basicBlocksIterator();
		while(basicBlockIterator.hasNext()) {
			BasicBlock cfNode = basicBlockIterator.next();
			allNodes.addElement(cfNode);
			unreachableNodes.add(cfNode);	

			Iterator<Instruction> instrs = cfNode.getInstructions();
			while(instrs.hasNext()){
				Instruction ins = instrs.next();
			
				if(ins.definesNewValue()){
					tempVsLatticeValue.put(ins, new LatticeValue(LatticeValue.UNDEFINED));
				}

				int numOperands = ins.getNumOperands();
				for(int m = 0; m < numOperands; m++){
					Value operand = ins.getOperand(m);
					ValueTypeID operandValueTypeID = operand.getValueTypeID();
					if(operandValueTypeID == ValueTypeID.ARGUMENT ){
						// Arguments must be initialized to overdefined
						tempVsLatticeValue.put(operand, 
								new LatticeValue(LatticeValue.OVERDEFINED));
					}
					else if(operandValueTypeID == ValueTypeID.INSTRUCTION){
						// Instructions must be initialized to undefined
						tempVsLatticeValue.put(operand, new LatticeValue(LatticeValue.UNDEFINED));
					}
					
					else if(operand.isAConstant()){
						// Constants must be be initialized to constant
						// lattice values
						LatticeValue newLatticeValue = new LatticeValue();
						newLatticeValue.setConstantValue((Constant) operand);
						tempVsLatticeValue.put(operand, newLatticeValue);
					}

				}
			}			
		}

		// Initialize the CFGWorklist with the start node of the graph
		cfgWorkList = new Vector<BasicBlock>();
		cfgWorkList.addElement((BasicBlock) function.getStartNode());

		// Initialize the variable worklist. It will be empty initially
		variableWorkList = new Vector<Value>();

		// Initialization done, now the propagation phase
		while(cfgWorkList.size() != 0 || variableWorkList.size() != 0){
			if(cfgWorkList.size() != 0){
				BasicBlock block = (BasicBlock) cfgWorkList.remove(0);	

				unreachableNodes.remove(block);								
				modelOperationsInReachableBlock(block);
			}

			if(variableWorkList.size() == 0)
				continue;

			Instruction ins = (Instruction)variableWorkList.remove(0);
			
			int numUses = ins.getNumUses();
			for(int i = 0; i < numUses; i++){
				Instruction useInstr = (Instruction)ins.getUserAt(i);
				if(!unreachableNodes.contains(useInstr.getParent())){
					// This instruction is reachable, process it
					modelReachableInstruction( useInstr);
				}					
			}

		}

		// Completed propagation phase - use the information in the analysis 
		// done in the propagation phase to remove instructions in unreachable
		// blocks
		removeInstructionsInUnreachableBlocks(transformations);

		// Constant propagation - Iterate through the lattice values, 
		// checking for constants and replacing / evaluating constant expressions
		basicBlockIterator = function.basicBlocksIterator();
		while(basicBlockIterator.hasNext()) {
			BasicBlock cfNode = basicBlockIterator.next();
			BasicBlock parentBB = (BasicBlock) cfNode;
			Iterator<Instruction> instrs = cfNode.getInstructions();
			Vector<Instruction> instrsToBeDeletedInNode = new Vector<Instruction>();
			while(instrs.hasNext()){
				Instruction ins = instrs.next();

				LatticeValue latticeValue = tempVsLatticeValue.get(ins);
				if(latticeValue == null){
					// Maybe instructions like branches that do not have
					// a lattice value
					continue;
				}

				short latticeValType = latticeValue.getType();

				if(latticeValType == LatticeValue.CONSTANT){
					instrsToBeDeletedInNode.addElement(ins);	
					// Add to transformations
					// transformations.addToDeletions(cfNode, ins); 
				}
				else{
					//processInstructionForCopyPropagation(ins, cfNode, transformations);
				}
			}

			// Delete all instructions in the node that need to be deleted
			for(int m = 0; m < instrsToBeDeletedInNode.size(); m++){
				Instruction instrToBeDeleted = instrsToBeDeletedInNode.elementAt(m);
				// Replace all uses of this with the constant first
				Constant constVal = 
					tempVsLatticeValue.get(instrToBeDeleted).getConstantValue();

				instrToBeDeleted.replaceAllUsesOfThisWith(constVal);

			}

			// Now remove the instructions
			for(Instruction instrToBeDeleted : instrsToBeDeletedInNode){
				parentBB.removeInstruction(instrToBeDeleted);
			}
		}

		return transformations;
	}

	private void modelOperationsInReachableBlock(BasicBlock block){

		Iterator<Instruction> instrs = block.getInstructions();	
		while(instrs.hasNext()){
			Instruction ins = instrs.next();
			modelReachableInstruction(ins);			
		}
	}
	
	private void modelReachableInstruction(Instruction ins){

		System.out.println(" Trying to model Ins = " + ins);

		LatticeValue latticeValueBeforeModelling = tempVsLatticeValue.get(ins);
		LatticeValue latticeValueAfterModelling = 
			ins.visitForSCCP(latticeValueBeforeModelling,
				unreachableNodes, tempVsLatticeValue,
		        variableWorkList, cfgWorkList);

		// If the lattice value for the destination temp has changed, add this 
		// to the ssa work-list (provided its not a physical register)
		if(latticeValueBeforeModelling != null && latticeValueAfterModelling != null){
			if(latticeValueBeforeModelling.getType() != latticeValueAfterModelling.getType()){
				LOG.debug("Lattice value of {} changed from {} to {}" + ins.toString(), latticeValueBeforeModelling,
						latticeValueAfterModelling);
				
				variableWorkList.addElement(ins);
			}	

			tempVsLatticeValue.put(ins, latticeValueAfterModelling);			
		}		
	}
	
	private void removeInstructionsInUnreachableBlocks(Transformations transformations){

		Iterator<BasicBlock> iter = unreachableNodes.iterator();
		
		while(iter.hasNext()){
			BasicBlock unreachableNode = iter.next();
			
			if(unreachableNode.numInstructions() == 1){			
				continue;
			}
			
			// Delete the instructions backwards, as it has a reduced 
			// likelihood of having to update as many def-use and 
			// use-def chains.
			Iterator<Instruction> reverseInstrList = unreachableNode.reverseInstructionList();
			while(reverseInstrList.hasNext()){
				Instruction instr = reverseInstrList.next();

				// Check to see if there are non-terminating instructions to delete.
				if(instr.isTerminatorInstruction())
					continue;

				if (instr.getNumUses() > 0)
					instr.replaceAllUsesOfThisWith(UndefValue.createOrGet(instr.getType()));

				/*if (isa<LandingPadInst>(Inst)) {
					EndInst = Inst;
					continue;
				}*/

				instr.eraseFromParent();
			}
		}		
	}
}

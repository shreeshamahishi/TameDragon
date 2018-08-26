package org.tamedragon.common.optimization;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.tamedragon.common.DominatorTree;
import org.tamedragon.common.controlflowanalysis.LoopIdentifier;
import org.tamedragon.common.controlflowanalysis.LoopInfo;
import org.tamedragon.common.controlflowanalysis.LoopNestingTree;
import org.tamedragon.common.llvmir.instructions.Instruction;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionDetailAccessException;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionUpdateException;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.Constant;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.Value;
import org.tamedragon.common.llvmir.types.Value.ValueTypeID;
import org.tamedragon.common.llvmir.utils.LLVMIREmitter;

/**
 * Loop Invariant Code Motion,execute method of this class will apply LICM optimization 
 * to the given Control Flow Graph 
 */
public class LICM {

	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(LICM.class);
	
	private LLVMIREmitter emitter;
	private Function function;
	
	public LICM() {
		emitter = LLVMIREmitter.getInstance();
	}

	public Transformations execute(Function function) throws InstructionUpdateException{
		this.function = function;
		
		Transformations transformations = new Transformations(Transformations.LOOP_INVARIANT_CODE_MOTION);
		
		// Identify any loops
		LoopIdentifier loopIdentifier = new LoopIdentifier(function);
		LoopNestingTree loopNestingTree = loopIdentifier.getLoopNestingTree();
		DominatorTree dominatorTree = loopIdentifier.getDominatorTree();

		// Iterate over the loop nesting tree, starting with the leaf loops 
		// first; this way, we start with the innermost loops and traverse
		// to outer loops
		List<LoopInfo> leafLoops = loopNestingTree.getLeafLoops();

		HashSet<LoopInfo> visitedLoops = new HashSet<LoopInfo>();

		for(LoopInfo leafLoop : leafLoops){
			analyzeAndPerformCodeMotion(dominatorTree, leafLoop);
			visitedLoops.add(leafLoop);

			// Now that the leaf loop has been visited, work outwards
			if(loopNestingTree.isAnOutermostLoop(leafLoop))
				continue;

			LoopInfo runner = loopNestingTree.getImmediateOuterLoop(leafLoop);
			while(runner != null){
				if(visitedLoops.contains(runner)){
					// Already visited, try next one
					runner = loopNestingTree.getImmediateOuterLoop(runner);
					continue;
				}
				// Not visited, visit it now
				analyzeAndPerformCodeMotion(dominatorTree, runner);

				// Get the next outer loop to analyze
				runner = loopNestingTree.getImmediateOuterLoop(runner);
			}
		}
		
		
		
		return transformations;
	}

	private void analyzeAndPerformCodeMotion(DominatorTree dominatorTree, LoopInfo loop) {
		LinkedHashSet<Value> loopInvariants = loop.getLoopInvariants();
		LinkedHashSet<Instruction> InsructionUseOutsideLoop = loop.getInstructionHasAllUserOutsideLoop(loopInvariants);
		loopInvariants.addAll(InsructionUseOutsideLoop);
		HashSet<Instruction> sinkInstructions = new LinkedHashSet<Instruction>();
		LinkedHashSet<Instruction> hoistInstructions = new LinkedHashSet<Instruction>();

		// Make a note of dead instructions
		List<Instruction> deadInstructions = new ArrayList<Instruction>();
		if(loopInvariants.size() > 0){
			outer : for(Value value : loopInvariants){
				if(value.getValueTypeID() != ValueTypeID.INSTRUCTION)
					continue;

				Instruction instruction = (Instruction) value;

				// Some instructions can be dead if they have no uses. Check if
				// this instruction has any uses; if there are none, it is dead
				// and can be removed
				if(instruction.getNumUses() == 0){
					LOG.debug("Found dead code : " + emitter.getValidName(instruction));
					deadInstructions.add(instruction);
					continue;
				}

				BasicBlock parent = instruction.getParent();
				int numUses = instruction.getNumUses();
				for(int i = 0; i < numUses; i++){
					Instruction useInstr = (Instruction)instruction.getUserAt(i);
					BasicBlock nodeContainingUse = useInstr.getParent();

					if(!dominatorTree.dominates(parent, nodeContainingUse))
						// Not safe to hoist this instruction
						continue outer;
				}

				// If the instruction can be constant folded, do it now
				Constant constVal = instruction.foldIfPossible();
				if(constVal != null){
					instruction.replaceAllUsesOfThisWith(constVal);
					instruction.eraseFromParent();
					continue outer;
				}

				if(canSink(instruction, dominatorTree, loop))
					sinkInstructions.add(instruction);
				else
					hoistInstructions.add(instruction);
			}
		}
		
		try {
			sink(dominatorTree, sinkInstructions, loop);
			hoist(dominatorTree, hoistInstructions, loop);
		} catch (InstructionUpdateException e) {
			// TODO Log error here
			e.printStackTrace();
			System.exit(-1);
		} catch (InstructionDetailAccessException e) {
			// TODO Log error here
			e.printStackTrace();
			System.exit(-1);
		}
		
		// Remove any instructions that were found to be dead before returning
		for(Instruction deadInstr : deadInstructions){
			BasicBlock parentBB = deadInstr.getParent();
			parentBB.removeInstruction(deadInstr);
		}
	}

	/**
	 * Hoist the list of loop invariant instructions outside the loop
	 * that is passed in the second argument. 
	 * @param dominatorTree 
	 * @param loopInvariants
	 * @param loop
	 * @throws InstructionUpdateException 
	 * @throws InstructionDetailAccessException 
	 */
	private void hoist(DominatorTree dominatorTree, LinkedHashSet<Instruction> loopInvariants, LoopInfo loop)
			throws InstructionUpdateException, InstructionDetailAccessException {

		// Get the preheader
		List<BasicBlock> preheaders = loop.getPreHeader();
		BasicBlock preheader = null;
		
		if(preheaders.size() == 1){
			preheader = preheaders.get(0);
		}
		else if(preheaders.size() > 1){
			
			// More than one preheader: create a new pre-header that can be inserted between the current
			// pre-headers and the loop header
			BasicBlock newPreheader = new BasicBlock(function);
			newPreheader.setName("Preheader");

			BasicBlock loopHeader = loop.getBackEdgeTarget();
			function.insertNewNodeBetween(preheaders, loopHeader, newPreheader);
			
			preheader = newPreheader;				
		}
		else{
			// No pre-headers
			// TODO : Implement this
		}
		
		// Hoist as many loop invariant instructions in this loop as possible to the preheader
		for(Instruction instruction : loopInvariants){			
			LOG.debug("Hoisting loop invariant {} into {}", emitter.getValidName(instruction),
					emitter.getValidName(preheader));
			
			// Safe to hoist this instruction outside the loop to the preheader
			Instruction terimatorOfPreHeader = preheader.getLastInstruction();

			instruction.moveInstructionBefore(terimatorOfPreHeader);
		}
	}


	private void sink(DominatorTree dominatorTree, HashSet<Instruction> loopInvariants, LoopInfo loopInfo){
		// get LoopExit
		List<BasicBlock> postLoopExitNodes = loopInfo.getPostLoopExit();
		int addCount = 0;

		// Sink as many loop invariant instructions in this loop as possible 
		for(Instruction instruction : loopInvariants){

			BasicBlock postLoopExit ;
			if(postLoopExitNodes.size() == 0 || postLoopExitNodes.size() > 1){
				// Either has no postloopExit OR has more than one postloopExit
				//TODO : What do we do here? Create a new postloopExit
				return;
			}
			else
				postLoopExit = postLoopExitNodes.get(0);

			// Safe to sink this instruction outside the loop
			instruction.getParent().removeInstruction(instruction);
			
			BasicBlock loopExitBB = (BasicBlock) postLoopExit;
			loopExitBB.insertInstructionAt(instruction, addCount++);			
						
			//instruction.setParent(loopExitBB);
		}

	}

	/**
	 * it check can the given instruction can be sink if the all user of the instruction are outside the loop
	 *  then it safe to sink else hoist it.
	 * @param instruction
	 * @param dominatorTree
	 * @param loopInfo
	 * @return true if instruction can be sink;
	 */

	private boolean canSink(Instruction instruction, DominatorTree dominatorTree, LoopInfo loopInfo)
	{
		List<BasicBlock> nodeInLoop = loopInfo.getNodesInLoop();
		int noOfUser = instruction.getNumUses();
		for(int i=0 ; i<noOfUser; i++){
			Instruction userInstr = (Instruction) instruction.getUserAt(i);
			BasicBlock userInstrNode = userInstr.getParent();
			if(nodeInLoop.contains(userInstrNode))
				return false;
		}
		return true;

	}
}
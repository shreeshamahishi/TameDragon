package org.tamedragon.common.llvmir.types;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.jgraph.graph.DefaultEdge;
import org.tamedragon.common.llvmir.instructions.AllocaInst;
import org.tamedragon.common.llvmir.instructions.AssemblyInstruction;
import org.tamedragon.common.llvmir.instructions.BranchInst;
import org.tamedragon.common.llvmir.instructions.CFG;
import org.tamedragon.common.llvmir.instructions.Instruction;
import org.tamedragon.common.llvmir.instructions.PhiNode;
import org.tamedragon.common.llvmir.instructions.TerminatorInst;
import org.tamedragon.common.llvmir.instructions.Instruction.InstructionID;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionDetailAccessException;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionUpdateException;
import org.tamedragon.common.llvmir.utils.LLVMIREmitter;

public class BasicBlock extends Value{

	protected Vector<Instruction> instructions;      // The list of instructions in this node
	private HashSet<Value> definitions;              // The list of variables that have been defined in this node
	private Function parent;
	private int lastAllocationInstructionIndex;
	private BitSet usesBitVector;                   //	Use and def temporaries in the form of a bit vectors for liveness analysis
	private BitSet defsBitVector;
	private int currentInst = 0;

	public BasicBlock(CompilationContext context){
		super(Type.getLabelType(context));
		initBasicBlock();
	}
	
	public BasicBlock(Function parent){
		super(Type.getLabelType(parent.getContext()));
		this.parent = parent;
		initBasicBlock();
	}
	
	private void initBasicBlock(){
		lastAllocationInstructionIndex = 0;
		instructions = new Vector<Instruction>();
		setValueTypeID(ValueTypeID.BASIC_BLOCK);
	}

	public Iterator<Instruction> getInstructions(){
		return instructions.iterator();
	}
	
	public Iterator<Instruction> getInstructionSubset(int start, int end){		
		return instructions.subList(start, end).iterator();
	}
	
	public Iterator<Instruction> getInstructionSubset(int startIndex){
		if(startIndex < 0 || startIndex > instructions.size() -1){
			// TODO Log warning here
			return null;
		}
		
		return getInstructionSubset(startIndex, instructions.size());			
	}
	
	public Iterator<Instruction> reverseInstructionList(){
		List<Instruction> reverseList = new ArrayList<Instruction>(instructions.size());
		
		for(int i = instructions.size() -1; i > -1; i--){
			reverseList.add(instructions.elementAt(i));
		}
		
		return reverseList.iterator();
	}
	
	
	public boolean addAll(List<Instruction> additionalInstructions){
		return instructions.addAll(additionalInstructions);
	}

	public boolean hasInstruction(Instruction instruction){
		return instructions.contains(instruction);
	}
	
	public void insertInstructionAt(Instruction instruction, int indexToInsertAt) {
		instructions.insertElementAt(instruction, indexToInsertAt);
		instruction.setParent(this);
	}
	
	public Instruction getInstructionAt(int index){
		return instructions.elementAt(index);
	}
	
	public HashSet<Value> getDefinitions() {
		return definitions;
	}

	public void addInstruction(Instruction instr) {

		InstructionID instructionID = instr.getInstructionID();

		if(instructionID == InstructionID.PHI_NODE_INST){
			instructions.add(currentInst++, instr);
			lastAllocationInstructionIndex++;
		}

		else if(instructionID == InstructionID.ALLOCA_INST){
			AllocaInst allocaInst = (AllocaInst)instr;
			boolean isVariableArrayAllocation = allocaInst.isVariableLengthArrayAllocation();
			// If this is an allocation instruction but not a Variable Array Allocation, treat differently
			if(!isVariableArrayAllocation){
				if(instructions.size() == 0){
					instructions.add(instr);
					lastAllocationInstructionIndex = 0;
				}
				else{
					if(lastAllocationInstructionIndex == instructions.size()){
						instructions.add(instr);
					}
					else{
						instructions.insertElementAt(instr, lastAllocationInstructionIndex);
					}
				}

				lastAllocationInstructionIndex++;
			}
			else
				instructions.add(instr);
		}
		else{
			// Not an allocation instruction; add at end
			instructions.add(instr);
		}

		instr.setParent(this);
	}

	//TODO : Remove this later since it refers to the old "AssemblyInstruction"
	// and retain the new removeInstruction below it
	/*
	 * This function removes the instruction that has been passed. This can be called from
	 * optimization routines that discover that an instruction is unnecessary (maybe during dead
	 * code elimination).
	 */

	public void removeInst(AssemblyInstruction instr){
		instructions.remove(instr);
	}

	public void removeInstruction(Instruction instr){
		instructions.remove(instr);

		// Remove from list of uses
		instr.emptyUserList();
	}
	
	public int numInstructions(){
		return instructions.size();
	}
	
	/**
	 * Removes instruction from this basic block, but not permanently.
	 * @param instruction
	 */
	public void removeInstructionFromBasicBlock(Instruction instruction){
		instructions.remove(instruction);
	}
	
	public void removeAllInstructions() {
		int numInstrs = instructions.size();
		for(int i = 0 ; i < numInstrs; i++){
			Instruction instr = instructions.get(0);
			removeInstruction(instr);
		}
		
		instructions = null;
	}

	/**
	 * Removes a portion of instructions in this basic block
	 * @param instructionsToBeRemoved
	 */
	public void removeInstructionSubset(
			List<Instruction> instructionsToBeRemoved) {
		for(Instruction instr : instructionsToBeRemoved){
			removeInstruction(instr);
		}
	}
	
	public BitSet getDefsBitVector() {
		return defsBitVector;
	}

	public void setDefsBitVector(BitSet defsBitVector) {
		this.defsBitVector = defsBitVector;
	}

	public Function getParent() {
		return parent;
	}

	public void setParent(Function parent) {
		this.parent = parent;
	}

	public BitSet getUsesBitVector() {
		return usesBitVector;
	}

	public void setUsesBitVector(BitSet usesBitVector) {
		this.usesBitVector = usesBitVector;
	}

	public String emit(){
		return getName();
	}

	public void setName(String name){
		super.setName(name);
	}

	public Instruction getLongestInstruction(){
		if(instructions.size() == 0)
			return null;

		Instruction longestIns = instructions.elementAt(0);
		int longest = longestIns.emit().length();
		for(Instruction ins : instructions){
			String insDesc = ins.emit();
			int insDescLength = insDesc.length();
			if(longest < insDescLength)
				longestIns = ins;
		}

		return longestIns;
	}

	/**
	 * Returns the index  of the instruction in this basis block. If the instruction
	 * that is passed is not present in this basic block, we return an invalid value (-1)
	 * @param inst
	 * @return
	 */
	public int getInstructionIndex(Instruction inst){
		return instructions.indexOf(inst);
	}

	public List<String> emitInstructions(List<Value> values){
		List<String> instructionStrings = new ArrayList<String>();
		if(values.indexOf(this) != 0){
			// No comment for the first block
			instructionStrings.add("\n");
			instructionStrings.add(createCommentForBasicBlock());
		}

		// Add instructions
		for(Instruction ins : instructions){
			String insDesc = ins.emit();
			instructionStrings.add("  " + insDesc);
		}

		return instructionStrings;
	}

	public void addBasicBlockInBetween(BasicBlock predecessor, BasicBlock sucessor, CFG cfg){

		DefaultEdge edge = cfg.getEdge(predecessor, sucessor);
		if(edge != null){
			cfg.removeEdge(edge);
		}

		cfg.addEdge(predecessor, this, new DefaultEdge());
		cfg.addEdge(this, sucessor,  new DefaultEdge());
	}

	private String createCommentForBasicBlock(){
		String name = LLVMIREmitter.getInstance().getValidName(this);
		CFG cfg = getParent().getCfg();

		List<BasicBlock> preds = cfg.getPredecessors(this);
		int numPreds = preds.size();
		String predsPart = "		; preds = ";
		int predsCount = 0;
		for(BasicBlock pred : preds){
			BasicBlock basicBlock = ((BasicBlock) pred);
			String predName = LLVMIREmitter.getInstance().getValidName(basicBlock);
			predsPart += predsCount < numPreds -1 ? predName + ", " : predName;
			predsCount++;
		}

		// Remove '%' from label name in comments
		if(name.startsWith("%"))
			name = name.replaceFirst("%", "");
		
		String labelName = "; <label>:" + name; 
		if(hasName()){
			labelName = ";" + name + ":";
		}

		Instruction longestIns = getLongestInstruction();
		int longestInsLength = 0;
		if(longestIns != null)
			longestInsLength = longestIns.emit().length();

		int sizeOfCommentsWithoutPadding = labelName.length() + predsPart.length();
		String padding = "";
		if(sizeOfCommentsWithoutPadding < longestInsLength){
			int numPad = longestInsLength - sizeOfCommentsWithoutPadding;

			for(int i = 0; i < numPad; i++) padding += " ";
		}

		String comment = labelName + padding + predsPart;
		return comment;
	}

	/**
	 * splitBasicBlock - This splits a basic block into two at the specified
	     instruction.  Note that all instructions BEFORE the specified iterator stay
	     as part of the original basic block, an unconditional branch is added to
	     the new BB, and the rest of the instructions in the BB are moved to the new
	     BB, including the old terminator.  This invalidates the iterator.

	     Note that this only works on well formed basic blocks (must have a
	     terminator), and 'I' must not be the end of instruction list (which would
	     cause a degenerate basic block to be formed, having a terminator inside of
	     the basic block).
	 * @param I
	 * @param BBName
	 * @return
	 * @throws InstructionDetailAccessException 
	 * @throws InstructionUpdateException 
	 */

	public BasicBlock splitBasicBlock(Instruction I, String BBName) throws InstructionDetailAccessException, InstructionUpdateException {

		CompilationContext compilationContext = I.getType().getCompilationContext();
		Value newBBValue = new Value(Type.getLabelType(compilationContext));
		newBBValue.setName(BBName);

		BasicBlock newBB = new BasicBlock(getParent());
		Function function = this.getParent();
		function.insertNewNodeBetween(this, function.getCfg().getSuccessors(this), newBB);


		// Move all of the specified instructions from the original basic block into
		// the new basic block.
		splice(newBB, I);
		// Add a branch instruction to the newly formed basic block.
		BranchInst newBranchInst = BranchInst.create(newBB, this, compilationContext);
		this.addInstruction(newBranchInst);

		// Now we must loop through all of the successors of the New block (which
		// were the successors of the 'this' block), and update any PHI nodes in
		// successors.  If there were PHI nodes in the successors, then they need to
		// know that incoming branches will be from New, not from Old.
		List<BasicBlock> successors = function.getCfg().getSuccessors(newBB);
		for(BasicBlock successor : successors) {
			// Loop over any phi nodes in the basic block, updating the BB field of
			// incoming values...
			Iterator<Instruction> instrIT = successor.getInstructions();
			while(instrIT.hasNext()){
				Instruction instruction = (Instruction)instrIT.next();
				if(instruction instanceof PhiNode){
					PhiNode phiNode = (PhiNode)instruction;
					int index = phiNode.getBasicBlockIndex(this);
					while(index !=1){
						Value value = phiNode.getIncomingValue(index);
						phiNode.setIncomingValueAndBasicBlock(index, value, newBB);
						index = phiNode.getBasicBlockIndex(this);
					}
				}
			}
		}
		return newBB;
	}

	private void splice(BasicBlock newBB, Instruction instruction) throws InstructionUpdateException{
		int index = getInstructionIndex(instruction);
		List<Instruction> subList = new ArrayList<Instruction>();
		for(int i = index; i< instructions.size(); i++){
			Instruction instr = instructions.elementAt(i);
			subList.add(instr);
		}
		instructions.removeAll(subList);
		newBB.addAll(subList);
	}

	public Instruction getFirstInstruction(){
		return instructions.get(0);
	}
	
	public TerminatorInst getLastInstruction(){
		int numInstructions = instructions.size();
		return (TerminatorInst)instructions.get(numInstructions -1);
	}
	
	@Override
	public String toString() {
		
		Iterator<Instruction> instrs = getInstructions();
		if(instrs == null){
			return "<html></html>";
		}

		String str  = "<html>";
		LLVMIREmitter.getInstance().processFunction(getParent());

		String bBNumber  = LLVMIREmitter.getInstance().getValidName(this);
		str += "<b>Label : " + bBNumber + "<br></b>";
		while(instrs.hasNext()){
			str += instrs.next().emit()+"<br>";
		}

		str += "</html>";
		return str;
	}

	public void updateBrInstrAfterRemoval(BasicBlock basicBlock) throws InstructionDetailAccessException, InstructionUpdateException {
		int numOfUsers = getNumUses();
		for(int i = 0; i < numOfUsers; i++){
			Value user = getUserAt(i);
			if (user instanceof BranchInst) {
				BranchInst brInstr = (BranchInst) user;
				if(brInstr.isConditional()){
					if(brInstr.getSuccessor(1) == this)
						brInstr.setSuccessor(1, basicBlock, true);
					else if(brInstr.getSuccessor(2) == this)
						brInstr.setSuccessor(2, basicBlock, true);
				}
				else if(brInstr.getSuccessor(0) == this)
					brInstr.setSuccessor(0, basicBlock, true);

				BasicBlock parentBBOfBrInstr = brInstr.getParent();
				CFG cfg = parentBBOfBrInstr.getParent().getCfg();
				DefaultEdge edge = cfg.getEdge(parentBBOfBrInstr, basicBlock);
				if(edge == null){
					edge = new DefaultEdge();
					cfg.addEdge(parentBBOfBrInstr, basicBlock, edge);
				}
				brInstr.ifHasSameOperandsMakeConditionalToUnconditional();
			}
		}
	}

	public BasicBlock createClone(){
//		BasicBlock newBB = new BasicBlock(this);
//		return newBB;
		
		return null;
	}

	public boolean allInstructionsAreDead(){
		for(Instruction instruction : instructions){
			if(instruction.mayHaveSideEffects()){
				return false;
			}

			int numOfUsers = instruction.getNumUses();
			for(int j = 0; j < numOfUsers; j++){
				Value user = instruction.getUserAt(j);
				// If any user of this instruction is also a instruction outside this basic block 
				// then this instruction is not dead.
				if(!(user instanceof Instruction)){
					continue;
				}

				Instruction ins = (Instruction) user;
				if(ins.getParent() != instruction.getParent())
					return false;
			}
		}
		
		return true;
	}

	public Instruction getFirstNonPhiInstruction() {
		for(Instruction instruction : instructions){
			if(instruction.getInstructionID() != InstructionID.PHI_NODE_INST){
				return instruction;
			}
		}
		
		return instructions.lastElement();
	}
}

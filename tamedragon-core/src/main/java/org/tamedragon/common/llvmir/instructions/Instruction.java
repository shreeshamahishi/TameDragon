package org.tamedragon.common.llvmir.instructions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;

import org.tamedragon.common.LatticeValue;
import org.tamedragon.common.llvmir.instructions.BinaryOperator.BinaryOperatorID;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.Constant;
import org.tamedragon.common.llvmir.types.Type;
import org.tamedragon.common.llvmir.types.User;
import org.tamedragon.common.llvmir.types.Value;

public abstract class Instruction extends User{
	boolean isTerminatorInstruction = false;
	boolean isCastInstruction = false;
	public enum InstructionID {
		ALLOCA_INST(0) ,              //  0: type with no size
		LOAD_INST(1),   		      //  1: 16-bit floating point type
		STORE_INST(2),                //  1: 32-bit floating point type
		FENCE_INST(3),                //  2: 64-bit floating point type
		ATOMIC_COMPXCHNG_INST(4),     //  3: 80-bit floating point type (X87)
		ATOMIC_RMW_INST(5),           //  4: 128-bit floating point type (112-bit mantissa)
		ICMP_INST(6),                 //  5: 128-bit floating point type (two 64-bits, PowerPC)
		FCMP_INST(7),                 //  6: Labels
		CALL_INST(8),                 //  7: Metadata
		SELECT_INST(9),               //  8: MMX vectors (64 bits, X86 specific)
		VA_ARG_INST(10),      //  9: Arbitrary bit width integers
		EXTRACT_ELEM_INST(11),    //  10: Functions
		INSERT_ELEM_INST(12),      //  11: Structures
		SHUFFLE_VEC_INST(13),       //  12: Arrays
		EXTRACT_VALUE_INST(14),     //  13: Pointers
		INSERT_VALUE_INST(15),      //  14: SIMD 'packed' format, or other vector type
		PHI_NODE_INST(16),      //
		LANDING_PAD_INST(17),      //
		RETURN_INST(18),      //
		BINARY_INST(19),
		SWITCH_INST(20),      //
		INDIRECT_BR_INST(21),     //
		INVOKE_INST(22),
		UNWIND_INST(23),
		RESUME_INST(24),
		UNREACHABLE_INST(25),
		TRUNC_INST(26),
		ZEXT_INST(27),
		SEXT_INST(28),
		FP_TRUNC_INST(29),
		FP_EXT_INST(30),
		UI_TO_FP_INST(31),
		SI_TO_FP_INST(32),
		FP_TO_UI_INST(33),
		FP_TO_SI_INST(34),
		INT_TO_PTR_INST(35),
		PTR_TO_INT_INST(36),
		BIT_CAST_INST(37),
		BRANCH_INST(38),
		GET_ELEMENT_PTR(39),
		TYPE_INSTR(40),
		//FUNC_DEF(41),
		GLOBAL_DECLR(42);
		//FUNC_DECLR(43);

		private int value;

		private InstructionID(int value){
			this.value = value;
		}

		public int getTypeIdValue(){
			return value;
		}
	} 

	public enum AtomicOrdering {
		NotAtomic(""),
		Unordered("unordered"),
		Monotonic("monotonic"),
		// Consume = 3,  // Not specified yet.
		Acquire("acquire") ,
		Release("release") ,
		AcquireRelease("acq_rel"),
		SequentiallyConsistent("seq_cst");

		private final String representation;

		AtomicOrdering(String representation){
			this.representation = representation;
		}

		public String getRepresentation(){
			return representation;
		}
	}

	public enum SynchronizationScope {
		SingleThread("singlethread") ,
		CrossThread("crossthread");

		private final String representation;

		SynchronizationScope(String representation){
			this.representation = representation;
		}

		public String getRepresentation(){
			return representation;
		}
	}

	private InstructionID instructionID;
	private BasicBlock parent;

	public Instruction(InstructionID instructionID, Type type, List<Value> operandList, BasicBlock parent){

		super(type, operandList);
		this.instructionID = instructionID;
		this.parent = parent;
		setValueTypeID(ValueTypeID.INSTRUCTION);

	}

	public InstructionID getInstructionID() {
		return instructionID;
	}

	public BasicBlock getParent() {
		return parent;
	}

	public void setParent(BasicBlock parentBB) {
		this.parent = parentBB;
	}

	public void eraseFromParent(){
		parent.removeInstruction(this);
	}

	public boolean isTerminatorInstruction() {
		return isTerminatorInstruction;
	}

	public void setTerminatorInstruction(boolean isTerminatorInstruction) {
		this.isTerminatorInstruction = isTerminatorInstruction;
	}

	public boolean isCastInstruction() {
		return isCastInstruction;
	}

	public void setCastInstruction(boolean isCastInstruction) {
		this.isCastInstruction = isCastInstruction;
	}

	public abstract boolean definesNewValue();
	public abstract boolean isCritical();
	public abstract String emit();
	public abstract LatticeValue visitForSCCP(LatticeValue latticeValueBeforeModelling,
			HashSet<BasicBlock> unreachableNodes,
			HashMap<Value, LatticeValue> tempVsLatticeValue,
			Vector<Value> ssaWorkList,
			Vector<BasicBlock> cfgWorkList);
	public abstract boolean canBeHoistedOrSank();

	public abstract Constant foldIfPossible();

	/*public boolean isCastInstruction(){
		if(instructionID == InstructionID.TRUNC_INST
				|| instructionID == InstructionID.ZEXT_INST
				|| instructionID == InstructionID.SEXT_INST
				|| instructionID == InstructionID.FP_TRUNC_INST
				|| instructionID == InstructionID.FP_EXT_INST
				|| instructionID == InstructionID.UI_TO_FP_INST
				|| instructionID == InstructionID.SI_TO_FP_INST
				|| instructionID == InstructionID.FP_TO_UI_INST
				|| instructionID == InstructionID.FP_TO_SI_INST
				|| instructionID == InstructionID.INT_TO_PTR_INST
				|| instructionID == InstructionID.PTR_TO_INT_INST
				|| instructionID == InstructionID.BIT_CAST_INST)
			return true;

		return false;
	} */

	/**
	 * Returns true if the instruction defines a new value. For example,
	 * binary instructions define a new value, but branch instructions 
	 * do not. This is useful when we perform renaming.
	 * @return
	 */
	/*public boolean definesNewValue(){
		if(instructionID == InstructionID.RETURN_INST
				|| instructionID == InstructionID.BRANCH_INST
				|| instructionID == InstructionID.SWITCH_INST
				|| instructionID == InstructionID.INDIRECT_BR_INST
				|| instructionID == InstructionID.RESUME_INST
				|| instructionID == InstructionID.UNREACHABLE_INST
				|| instructionID == InstructionID.STORE_INST
				|| instructionID == InstructionID.FENCE_INST
				|| instructionID == InstructionID.FUNC_DECLR
				|| instructionID == InstructionID.FUNC_DEF
		){
			return false;
		}
		else if(instructionID == InstructionID.INVOKE_INST
				|| instructionID == InstructionID.CALL_INST){
			return true;
		}

		return true;
	}*/

	/**
	 * Returns true if the instruction if this instance is a 
	 * terminator instruction (branch, .
	 * @return
	 */
	/*public boolean isTerminatorInstruction(){
		if(instructionID == InstructionID.RETURN_INST
				|| instructionID == InstructionID.BRANCH_INST
				|| instructionID == InstructionID.SWITCH_INST
				|| instructionID == InstructionID.INDIRECT_BR_INST
				|| instructionID == InstructionID.INVOKE_INST
				|| instructionID == InstructionID.RESUME_INST
				|| instructionID == InstructionID.UNREACHABLE_INST
		){
			return true;
		}

		return false;
	}*/

	/** Return true if the instruction is associative:
	 *  Associative operators satisfy:  x op (y op z) === (x op y) op z
	 * In LLVM, the Add, Mul, And, Or, and Xor operators are associative.
	 */
	public static boolean isAssociative(BinaryOperatorID opCode) {
		return opCode == BinaryOperatorID.AND || 
		opCode == BinaryOperatorID.OR ||
		opCode == BinaryOperatorID.XOR ||
		opCode == BinaryOperatorID.AND || 
		opCode == BinaryOperatorID.MUL;
	}

	/** Return true if the instruction is commutative:
	 *  Commutative operators satisfy: (x op y) === (y op x)
	 *  In LLVM, these are the associative operators, plus SetEQ and SetNE, when
	 *  applied to any type.
	 */
	public static boolean isCommutative(BinaryOperatorID opCode) {

		switch (opCode) {
		case ADD:
		case FADD:
		case MUL:
		case FMUL:
		case AND:
		case OR:
		case XOR:
			return true;
		default:
			return false;
		}
	}

	/**
	 * Removes all users in the user list. The users themselves
	 * do not get removed from the system; only the user list is
	 * reset. Should be called when this value is being removed.
	 */
	public void emptyUserList() {
		int numOperands = getNumOperands();
		for(int i = 0; i < numOperands; i++){
			Value operand = getOperand(i);

			int numUsersOfOperand = operand.getNumUses();
			for(int j = 0; j < numUsersOfOperand; j++){
				Value userOfOpr = operand.getUserAt(j);
				if(userOfOpr == this){
					operand.removeUser(this);
				}
			}
		}
	}
	
	public void moveInstructionBefore(Instruction referenceInst) {

		if(this == referenceInst){
			// No change, return
			return;
		}

		BasicBlock parent = getParent();
		parent.removeInstructionFromBasicBlock(this);
		
		BasicBlock parentOfRefInstr = referenceInst.getParent();
	
		int indexToInsertAt = parentOfRefInstr.getInstructionIndex(referenceInst);
		if(indexToInsertAt < 0)
			indexToInsertAt = 0;
		
		parentOfRefInstr.insertInstructionAt(this, indexToInsertAt);		
	}

	/**
	 * Returns true if the value passed in the first parameter represents the instruction
	 * denoted by the instruction ID passed in the second parameter. 
	 * @param value
	 * @return
	 */
	public static boolean isValueInstruction(Value value, InstructionID instructionID){

		ValueTypeID valTypeID = value.getValueTypeID();
		if(valTypeID != ValueTypeID.INSTRUCTION){
			return false;
		}
		else{
			Instruction instr = (Instruction) value;
			InstructionID instrID = instr.getInstructionID();
			if(instructionID == instrID){
				return true;
			}
		}
		return false;
	}

	public boolean mayReadOrWriteMemory() {
		return mayReadFromMemory() || mayWriteToMemory();
	}

	/// mayReadFromMemory - Return true if this instruction may read memory.
	///
	public boolean mayReadFromMemory() {
		switch (instructionID) {
		default: return false;

		case VA_ARG_INST:
		case LOAD_INST:
			// case FENCE: // FIXME: refine definition of mayReadFromMemory
		case ATOMIC_COMPXCHNG_INST:
		case ATOMIC_RMW_INST:
			return true;
		case CALL_INST:
			CallInst callInst = (CallInst) this;
			return !callInst.doesNotAccessMemory();
		case INVOKE_INST:
			// TODO : return Invoke callInst = (InvokeInst) this;
			return true;
		case STORE_INST:
			StoreInst storeInst = (StoreInst) this;
			return !storeInst.isUnordered();
		}
	}

	/// mayWriteToMemory - Return true if this instruction may modify memory.
	///
	public boolean mayWriteToMemory() {
		switch (instructionID) {
		default: return false;

		// TODO case Instruction::Fence: // FIXME: refine definition of mayWriteToMemory
		case STORE_INST:
		case VA_ARG_INST:
		case ATOMIC_COMPXCHNG_INST:
		case ATOMIC_RMW_INST:
			return true;
		case CALL_INST:
			CallInst callInst = (CallInst) this;
			return !callInst.onlyReadsMemory();
		case INVOKE_INST:
			// TODO : return Invoke callInst = (InvokeInst) this;
			return true;
		case LOAD_INST:
			LoadInst loadInst = (LoadInst) this;
			return !loadInst.isUnordered();
		}
	}

	public boolean mayThrow() {
		if(instructionID == InstructionID.CALL_INST){
			CallInst callInst = (CallInst) this;
			return !callInst.doesNotThrow();
		}
		else if(instructionID == InstructionID.RESUME_INST){
			return true;
		}
		return false;
	}

	public boolean mayHaveSideEffects() {
		return mayWriteToMemory() || mayThrow();
	}

	public void removeUsersOfInstr() {
		int nosOfUsersOfInstr = getNumUses();
		if(nosOfUsersOfInstr == 0)
			return;
		for(int k = 0; k < nosOfUsersOfInstr; k++){
			Value userOfCondBrInstr = getUserAt(k);
			if(userOfCondBrInstr instanceof Instruction){
				Instruction instruction = (Instruction)userOfCondBrInstr;
				instruction.getParent().removeInstruction(instruction);
				instruction.removeUsersOfInstr();
			}
		}
	}

	@Override
	public String toString() {
		//this.getParent().toString();
		return emit();
	}
}

package org.tamedragon.common.llvmir.instructions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import org.tamedragon.common.LatticeValue;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionCreationException;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionUpdateException;
import org.tamedragon.common.llvmir.math.APInt;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.CompilationContext;
import org.tamedragon.common.llvmir.types.Constant;
import org.tamedragon.common.llvmir.types.ConstantFP;
import org.tamedragon.common.llvmir.types.ConstantInt;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.IntegerType;
import org.tamedragon.common.llvmir.types.PointerType;
import org.tamedragon.common.llvmir.types.Type;
import org.tamedragon.common.llvmir.types.Value;
import org.tamedragon.common.llvmir.utils.LLVMIREmitter;

public class StoreInst extends Instruction {
	private boolean isVolatile;
	private long align;
	private AtomicOrdering atomicOrdering;
	private SynchronizationScope synchScope;
	
	private Properties properties;

	private StoreInst(Properties properties, CompilationContext compilationContext,
			List<Value> operandList, boolean isVolatile, long align,
			AtomicOrdering atomicOrdering, SynchronizationScope synchScope, BasicBlock parent) {
		super(InstructionID.STORE_INST, Type.getVoidType(compilationContext),
				operandList, parent);

		this.isVolatile = isVolatile;
		this.align = align;
		this.atomicOrdering = atomicOrdering;
		this.synchScope = synchScope;
		
		this.properties = properties;
	}

	public boolean isVolatile() {
		return isVolatile;
	}

	public void setVolatile(boolean isVolatile) {
		this.isVolatile = isVolatile;
	}

	public long getAlign() {
		return align;
	}

	public void setAlign(long align) throws InstructionUpdateException {
		boolean isPowerOf2 = LLVMUtility.checkIfNumberIsPowerOfTwo(align);
		if (!isPowerOf2)
			throw new InstructionUpdateException(
					InstructionUpdateException.ALIGNMENT_IS_NOT_A_POWER_OF_TWO);

		Value val = this.getOperand(0);

		Type type = val.getType();

		this.align = AllocaInst.getAlignmentForType(properties, type);
	}

	public AtomicOrdering getAtomicOrdering() {
		return atomicOrdering;
	}

	private void setAtomicOrdering(AtomicOrdering ord)
			throws InstructionUpdateException {
		if (this.atomicOrdering == null
				|| this.atomicOrdering == AtomicOrdering.NotAtomic) {
			throw new InstructionUpdateException(
					InstructionUpdateException.ATOMIC_ORDERING_CAN_BE_SET_ONLY_FOR_ATOMIC_LOAD_OR_STORE_INSTR);
		}
		// Atomic ordering is present
		else if (ord != null && ord != AtomicOrdering.NotAtomic) {
			if (ord == AtomicOrdering.AcquireRelease
					|| ord == AtomicOrdering.Release)
				throw new InstructionUpdateException(
						InstructionUpdateException.INVALID_ATOMIC_ORDER_FOR_LOAD_OR_STORE_INSTR);
		}

		this.atomicOrdering = ord;
	}

	public SynchronizationScope getSynchScope() {
		return synchScope;
	}

	public void setSynchScope(SynchronizationScope synScope)
			throws InstructionUpdateException {
		// Atomic ordering is not there, Synchronization Scope is not null
		if (atomicOrdering == null
				|| atomicOrdering == AtomicOrdering.NotAtomic
				&& synScope != null) {
			if (synScope == SynchronizationScope.SingleThread)
				throw new InstructionUpdateException(
						InstructionUpdateException.ONLY_ATOMIC_LOAD_OR_STORE_INSTR_CAN_BE_SINGLE_THREADED);
		} else
			this.synchScope = synScope;
	}

	boolean isAtomic() {
		return (getAtomicOrdering() != null && getAtomicOrdering() != AtomicOrdering.NotAtomic);
	}

	public void setAtomic(AtomicOrdering Ordering)
			throws InstructionUpdateException {
		setAtomicOrdering(Ordering);
		setSynchScope(SynchronizationScope.CrossThread);
	}

	boolean isSimple() {
		return !isAtomic() && !isVolatile();
	}

	public boolean isUnordered() {
		return (getAtomicOrdering().ordinal() <= AtomicOrdering.Unordered
				.ordinal())
				&& !isVolatile();
	}

	public Value getValueOperand() {
		return getOperand(0);
	}

	public Value getPointerOperand() {
		return getOperand(1);
	}

	public long getPointerOperandIndex() {
		return 1l;
	}

	int getPointerAddressSpace() {
		PointerType pointerType = (PointerType) getPointerOperand().getType();
		return pointerType.getAddressSpace();
	}

	public static StoreInst create(Properties properties, Value firstArg, Value secondArg, boolean isVolatile,
			AtomicOrdering atomicOrdering, SynchronizationScope synchScope, BasicBlock parent)
					throws InstructionCreationException {

		List<Value> oprList = new ArrayList<Value>();

		if(firstArg == null)
			throw new InstructionCreationException(InstructionCreationException.FIRST_ARG_OF_STORE_INSTR_CANNOT_BE_NULL);
		if(secondArg == null)
			throw new InstructionCreationException(InstructionCreationException.SECOND_ARG_OF_STORE_INSTR_CANNOT_BE_NULL);

		Type val1Type = firstArg.getType();
		if (!val1Type.isFirstClassType()) {
			throw new InstructionCreationException(
					InstructionCreationException.STORE_INSTR_MUST_STORE_VAL_OF_FIRST_CLASS_TYPE);
		}

		boolean isFirstArgConstantZero = false;

		if(firstArg instanceof ConstantInt){
			ConstantInt constantInt = (ConstantInt)firstArg;
			APInt apInt = constantInt.getApInt();
			if(apInt.getVal().equals("0")){
				// If the secondArg is also a pointer to pointer (could be extended to any level...), then make firstAgr as null pointer
				isFirstArgConstantZero = true;
			}
		}

		PointerType pointerType = null;
		IntegerType intType = null;
		int nosOfBits = 0;
		int align = 1;

		try {
			pointerType = (PointerType) secondArg.getType();
		} 
		catch (ClassCastException e) {
			throw new InstructionCreationException(
					InstructionCreationException.THE_2ND_OPERAND_TO_A_STORE_INSTR_SHOULD_BE_POINTER_TYPE);
		}

		Type containedType = pointerType.getContainedType();

		// Convert first argument to null pointer type
		if(containedType.isPointerType() && isFirstArgConstantZero){
			val1Type = containedType;
			firstArg = new Value(containedType);
			firstArg.setName("null");
		}

		if (containedType != val1Type)
			throw new InstructionCreationException(
					InstructionCreationException.STORED_VALUE_AND_POINTER_TYPE_DONOT_MATCH);

		CompilationContext compilationContext = val1Type
				.getCompilationContext();

		if (containedType.isIntegerType()) {
			intType = (IntegerType) containedType;
			nosOfBits = intType.getNumBits();
			boolean isPowerOf2 = LLVMUtility
					.checkIfNumberIsPowerOfTwo(nosOfBits);
			if (!isPowerOf2)
				throw new InstructionCreationException(
						InstructionCreationException.IF_FOR_LOAD_OR_STORE_INSTR_POINTEE_IS_OF_INTEGER_TYPE_THEN_BITS_SHOULD_BE_POW_OF_2);
			align = AllocaInst.getAlignmentForType(properties, intType);
		} else if (containedType.isFloatingPointType()) {
			if (containedType.isFloatType()){
				align = AllocaInst.getAlignmentForType(properties, containedType);
			}
			else if (containedType.isDoubleType())
				align = AllocaInst.getAlignmentForType(properties, containedType);
		} else if (containedType.isPointerType())
			align = AllocaInst.getAlignmentForType(properties, containedType);

		if (atomicOrdering != null
				&& atomicOrdering != AtomicOrdering.NotAtomic) {
			if (atomicOrdering == AtomicOrdering.AcquireRelease
					|| atomicOrdering == AtomicOrdering.Release)
				throw new InstructionCreationException(
						InstructionCreationException.INVALID_ATOMIC_ORDER_FOR_ATOMIC_LOAD_OR_STORE_INSTR);
			// if it is atomic store type of instr, then pointee should be of
			// Integer type
			if (!containedType.isIntegerType()) {
				throw new InstructionCreationException(
						InstructionCreationException.FOR_ATOMIC_LOAD_OR_STORE_INSTR_POINTEE_SHOULD_BE_OF_INTEGER_TYPE);
			}
		} else if (synchScope != null
				&& synchScope == SynchronizationScope.SingleThread)
			throw new InstructionCreationException(
					InstructionCreationException.NON_ATOMIC_STORE_OR_LOAD_CANNOT_BE_SINGLE_THREADED);

		oprList.add(firstArg);
		oprList.add(secondArg);

		StoreInst storeInst = new StoreInst(properties, compilationContext, oprList,
				isVolatile, align, atomicOrdering, synchScope, parent);
		return storeInst;
	}

	@Override
	public String emit() {
		String description = "store";
		if (isAtomic())
			description += " atomic";
		if (isVolatile())
			description += " volatile";
		Value firstOp = getOperand(0);
		String firstOpName = LLVMIREmitter.getInstance().getValidName(firstOp);
		if (firstOp instanceof ConstantInt) {
			ConstantInt constantInt = (ConstantInt) getOperand(0);
			description += " " + constantInt.toString() + ", ";
		} else if (firstOp instanceof ConstantFP) {
			ConstantFP constantFP = (ConstantFP) getOperand(0);
			description += " " + constantFP.toString() + ", ";
		} else if (!(firstOp instanceof Constant)) {
			String valueName = firstOpName;
			description += " " + firstOp.getType().toString() + " "
					+ valueName + ", ";
		}
		else if (firstOp instanceof Function) {
			description += " " + firstOp.getType().toString() + " " + firstOpName + ", ";
		}

		String secondOpName = LLVMIREmitter.getInstance().getValidName(getOperand(1));
		description += getOperand(1).getType().toString() + " "
				+ secondOpName;
		if (getSynchScope() != null
				&& getSynchScope() == SynchronizationScope.SingleThread)
			description += " " + getSynchScope().getRepresentation();
		if (getAtomicOrdering() != null)
			description += " " + getAtomicOrdering().getRepresentation();
		description += ", align " + align;
		return description;
	}

	@Override
	public boolean isCastInstruction() {
		return false;
	}

	@Override
	public boolean definesNewValue() {
		return false;
	}

	@Override
	public boolean isTerminatorInstruction() {
		return false;
	}

	@Override
	public boolean isCritical() {
		// Any instruction that is stores into memory is a critical operation
		return true;
	}

	@Override
	public LatticeValue visitForSCCP(LatticeValue latticeValueBeforeModelling,
			HashSet<BasicBlock> unreachableNodes,
			HashMap<Value, LatticeValue> tempVsLatticeValue,
			Vector<Value> ssaWorkList,
			Vector<BasicBlock> cfgWorkList) {
		// If this store is of a struct, ignore it.
		Value storeValue = getOperand(0);
		if (storeValue.getType().isStructType())
			return latticeValueBeforeModelling;


		// TODO : We implement this once global values or constants are supported
		//		  if (TrackedGlobals.empty() || !isa<GlobalVariable>(SI.getOperand(1)))
		//		    return;
		//
		//		  GlobalVariable *GV = cast<GlobalVariable>(SI.getOperand(1));
		//		  DenseMap<GlobalVariable*, LatticeVal>::iterator I = TrackedGlobals.find(GV);
		//		  if (I == TrackedGlobals.end() || I->second.isOverdefined()) return;
		//
		//		  // Get the value we are storing into the global, then merge it.
		//		  mergeInValue(I->second, GV, getValueState(SI.getOperand(0)));
		//		  if (I->second.isOverdefined())
		//		    TrackedGlobals.erase(I);      // No need to keep tracking this!

		return latticeValueBeforeModelling;

	}

	@Override
	public boolean canBeHoistedOrSank() {
		return false;
	}

	@Override
	public Constant foldIfPossible() {
		// TODO Cannot be folded?
		return null;
	}

}

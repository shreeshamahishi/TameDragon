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
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.Constant;
import org.tamedragon.common.llvmir.types.IntegerType;
import org.tamedragon.common.llvmir.types.PointerType;
import org.tamedragon.common.llvmir.types.Type;
import org.tamedragon.common.llvmir.types.Value;
import org.tamedragon.common.llvmir.utils.LLVMIREmitter;

/**
 * This class represents LLVM's Load Instruction
 * @author ipsg
 *
 */
public class LoadInst extends UnaryInstruction {
	
	private boolean isVolatile;
	private long align;
	private String pointerName;
	private AtomicOrdering order;
	private SynchronizationScope synchScope;
	
	private Properties properties;

	public LoadInst(Properties properties, Type type, List<Value> operandList, String name,
			boolean isVolatile, AtomicOrdering order,
			SynchronizationScope synchScope, long align, String pointerName, BasicBlock parent) {
		super(InstructionID.LOAD_INST, type, operandList, parent);

		setName(name);
		this.order = order;
		this.isVolatile = isVolatile;
		this.order = order;
		this.align = align;
		this.synchScope = synchScope;
		this.pointerName = pointerName;
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
		PointerType pointerType = (PointerType) val.getType();
		Type containedType = pointerType.getContainedType();
		align = AllocaInst.getAlignmentForType(properties, containedType);

		this.align = align;
	}

	public AtomicOrdering getOrder() {
		return order;
	}

	public void setOrder(AtomicOrdering ord)
			throws InstructionUpdateException {
		 if (this.order == null || this.order == AtomicOrdering.NotAtomic) {
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
		 
		this.order = ord;
	}

	public SynchronizationScope getSynchScope() {
		return synchScope;
	}

	public void setSynchScope(SynchronizationScope synScope)
			throws InstructionUpdateException {
		// Atomic ordering is not there, Synchronization Scope is not null
		if (order == null || order == AtomicOrdering.NotAtomic
				&& synScope != null) {
			if (synScope == SynchronizationScope.SingleThread)
				throw new InstructionUpdateException(
						InstructionUpdateException.ONLY_ATOMIC_LOAD_OR_STORE_INSTR_CAN_BE_SINGLE_THREADED);
			else
				this.synchScope = synScope;
		} else
			this.synchScope = synScope;
	}

	boolean isAtomic() {
		return (getOrder() != null && getOrder() != AtomicOrdering.NotAtomic);
	}

	public void setAtomic(AtomicOrdering Ordering)
			throws InstructionUpdateException {
		setOrder(Ordering);
		setSynchScope(SynchronizationScope.CrossThread);
	}

	boolean isSimple() {
		return !isAtomic() && !isVolatile();
	}

	public boolean isUnordered() {
		return (getOrder().ordinal() <= AtomicOrdering.Unordered.ordinal())
				&& !isVolatile();
	}

	Value getPointerOperand() {
		return getOperand(0);
	}

	static long getPointerOperandIndex() {
		return 0l;
	}

	int getPointerAddressSpace() {
		PointerType pointerType = (PointerType) getPointerOperand().getType();
		return pointerType.getAddressSpace();
	}

	public static LoadInst create(Properties properties, Value value, String name,
			boolean isVolatile, AtomicOrdering order,
			SynchronizationScope synchScope, BasicBlock parent)
			throws InstructionCreationException {
		
//		if(name == null){
//			throw new InstructionCreationException(InstructionCreationException.NAME_SHOULD_BE_PROVIDED);
//		}
		
		if(value == null){
			throw new InstructionCreationException(InstructionCreationException.VALUE_CANNOT_BE_NULL);
		}

		
		PointerType type = null;
		IntegerType intType = null;
		int nosOfBits = 0;
		int align = 1;
		
		try {
			type = (PointerType) value.getType();
		} 
		catch (ClassCastException cce) {
			throw new InstructionCreationException(
					InstructionCreationException.LOAD_INSTR_CAN_ONLY_HAVE_POINTER_AS_AN_OPERAND);
		}
		
		Type containedType = type.getContainedType();
		align = AllocaInst.getAlignmentForType(properties, containedType);
		
		if (!containedType.isFirstClassType()) {
			throw new InstructionCreationException(
					InstructionCreationException.LOAD_INSTR_SHOULD_HAVE_POINTER_TO_FIRSTCLASS_TYPE_ONLY);
		}
		else if (containedType.isIntegerType()) {
			intType = (IntegerType) containedType;
			nosOfBits = intType.getNumBits();
			boolean isPowerOf2 = LLVMUtility
					.checkIfNumberIsPowerOfTwo(nosOfBits);
			if (!isPowerOf2)
				throw new InstructionCreationException(
						InstructionCreationException.IF_FOR_LOAD_OR_STORE_INSTR_POINTEE_IS_OF_INTEGER_TYPE_THEN_BITS_SHOULD_BE_POW_OF_2);
		} 


		// Atomic ordering is there
		if (order != null && order != AtomicOrdering.NotAtomic) {
			if (order == AtomicOrdering.AcquireRelease
					|| order == AtomicOrdering.Release)
				throw new InstructionCreationException(
						InstructionCreationException.INVALID_ATOMIC_ORDER_FOR_ATOMIC_LOAD_OR_STORE_INSTR);
			// if it is atomic load type of pointee should be of Integer type
			if (!containedType.isIntegerType()) {
				throw new InstructionCreationException(
						InstructionCreationException.FOR_ATOMIC_LOAD_OR_STORE_INSTR_POINTEE_SHOULD_BE_OF_INTEGER_TYPE);
			}
		}
		else if (synchScope != null
				&& synchScope == SynchronizationScope.SingleThread)
			throw new InstructionCreationException(
					InstructionCreationException.NON_ATOMIC_STORE_OR_LOAD_CANNOT_BE_SINGLE_THREADED);

		List<Value> oprList = new ArrayList<Value>();
		oprList.add(value);
		LoadInst loadInst = new LoadInst(properties, containedType, oprList , name,
				isVolatile, order, synchScope, align, null, parent);

		return loadInst;
	}

	@Override
	public String emit() {
		String name = LLVMIREmitter.getInstance().getValidName(this);
		pointerName = LLVMIREmitter.getInstance().getValidName(getOperand(0));
		String description = name + " = load";
		if (this.isAtomic())
			description += " atomic";
		if (this.isVolatile)
			description += " volatile";
		description += " " + getOperand(0).getType().toString() + " "
				+ pointerName;
		if (this.isAtomic()) {
			if (synchScope == SynchronizationScope.SingleThread)
				description += " " + synchScope.getRepresentation();
			description += " " + order.getRepresentation();
		}
		description += ", align " + align;

		return description;
	}

	@Override
	public boolean isCastInstruction() {
		return false;
	}

	@Override
	public boolean definesNewValue() {
		return true;
	}

	@Override
	public boolean isTerminatorInstruction() {
		return false;
	}

	@Override
	public boolean isCritical() {
		return false;
	}

	@Override
	public LatticeValue visitForSCCP(LatticeValue currentLatticeValue,
			HashSet<BasicBlock> unreachableNodes,
			HashMap<Value, LatticeValue> tempVsLatticeValue,
	        Vector<Value> ssaWorkList,
			Vector<BasicBlock> cfgWorkList) {
		LatticeValue newLatticeValue = new LatticeValue();

		// If this load is of a struct, just mark the result overdefined.
		if (getType().isStructType()){
			newLatticeValue.setType(LatticeValue.OVERDEFINED);
			return newLatticeValue;
		}

		Value addressVal = getOperand(0);
		LatticeValue ptrLatticeValue = tempVsLatticeValue.get(addressVal);
		if (ptrLatticeValue.getType() == LatticeValue.UNDEFINED)
			return currentLatticeValue;   // The pointer is not resolved yet!

		if (currentLatticeValue.getType() == LatticeValue.OVERDEFINED)
			return currentLatticeValue; 

		if ((ptrLatticeValue.getType()  != LatticeValue.CONSTANT) || isVolatile()){
			newLatticeValue.setType(LatticeValue.OVERDEFINED);
			return newLatticeValue;
		}

		// We are loading from a pointer that is constant
		Constant ptr = ptrLatticeValue.getConstantValue();

		// TODO : Check other possibilities of loading from global constants 
		// or otherwise (below)
		//		// load null -> null
		//		if (isa<ConstantPointerNull>(Ptr) && I.getPointerAddressSpace() == 0)
		//			return markConstant(IV, &I, Constant::getNullValue(I.getType()));
		//
		//		// Transform load (constant global) into the value loaded.
		//		if (GlobalVariable *GV = dyn_cast<GlobalVariable>(Ptr)) {
		//			if (!TrackedGlobals.empty()) {
		//				// If we are tracking this global, merge in the known value for it.
		//				DenseMap<GlobalVariable*, LatticeVal>::iterator It =
		//					TrackedGlobals.find(GV);
		//				if (It != TrackedGlobals.end()) {
		//					mergeInValue(IV, &I, It->second);
		//					return;
		//				}
		//			}
		//		}
		//
		//		// Transform load from a constant into a constant if possible.
		//		if (Constant *C = ConstantFoldLoadFromConstPtr(Ptr, TD))
		//			return markConstant(IV, &I, C);

		// Otherwise we cannot say for certain what value this load will produce.
		// Bail out.
		newLatticeValue.setType(LatticeValue.OVERDEFINED);
		return newLatticeValue;
	}

	@Override
	public boolean canBeHoistedOrSank() {
		return false;
	}

	@Override
	public Constant foldIfPossible() {
		//TODO Cannot be constant folded?
		return null;
	}
}

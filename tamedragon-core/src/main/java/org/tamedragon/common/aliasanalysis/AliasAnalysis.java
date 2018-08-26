package org.tamedragon.common.aliasanalysis;

import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tamedragon.common.llvmir.instructions.AllocaInst;
import org.tamedragon.common.llvmir.instructions.CallInst;
import org.tamedragon.common.llvmir.instructions.Instruction;
import org.tamedragon.common.llvmir.instructions.LoadInst;
import org.tamedragon.common.llvmir.instructions.StoreInst;
import org.tamedragon.common.llvmir.instructions.Instruction.InstructionID;
import org.tamedragon.common.llvmir.types.Argument;
import org.tamedragon.common.llvmir.types.ArrayType;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.GlobalValue;
import org.tamedragon.common.llvmir.types.GlobalVariable;
import org.tamedragon.common.llvmir.types.PointerType;
import org.tamedragon.common.llvmir.types.StructType;
import org.tamedragon.common.llvmir.types.Type;
import org.tamedragon.common.llvmir.types.User;
import org.tamedragon.common.llvmir.types.Value;
import org.tamedragon.target.TargetData;

//===- llvm/Analysis/AliasAnalysis.h - Alias Analysis Interface -*- C++ -*-===//
//
//                     The LLVM Compiler Infrastructure
//
// This file is distributed under the University of Illinois Open Source
// License. See LICENSE.TXT for details.
//
//===----------------------------------------------------------------------===//
//
// This file defines the generic AliasAnalysis interface, which is used as the
// common interface used by all clients of alias analysis information, and
// implemented by all alias analysis implementations.  Mod/Ref information is
// also captured by this interface.
//
// Implementations of this interface must implement the various virtual methods,
// which automatically provides functionality for the entire suite of client
// APIs.
//
// This API identifies memory regions with the Location class. The pointer
// component specifies the base memory address of the region. The Size specifies
// the maximum size (in address units) of the memory region, or UnknownSize if
// the size is not known. The TBAA tag identifies the "type" of the memory
// reference; see the TypeBasedAliasAnalysis class for details.
//
// Some non-obvious details include:
//  - Pointers that point to two completely different objects in memory never
//    alias, regardless of the value of the Size component.
//  - NoAlias doesn't imply inequal pointers. The most obvious example of this
//    is two pointers to constant memory. Even if they are equal, constant
//    memory is never stored to, so there will never be any dependencies.
//    In this and other situations, the pointers may be both NoAlias and
//    MustAlias at the same time. The current API can only return one result,
//    though this is rarely a problem in practice.
//
//===----------------------------------------------------------------------===//

public abstract class AliasAnalysis {

	private static final Logger LOG = LoggerFactory.getLogger(AliasAnalysis.class);
	
	// Error messages
	public static final String INSTRUCTION_RANGE_MOD_CHECK_INSTRUCTIONS_NOT_IN_SAME_BLOCK 
					= "Instructions passed as arguments should be in the same basic block.";
	
	protected TargetData targetData;
	
	private AliasAnalysis previousAliasAnalysis;

	/// InitializeAliasAnalysis - Subclasses must call this method to initialize
	/// the AliasAnalysis interface before any other methods are called.  This is
	/// typically called by the run* methods of these subclasses.  This may be
	/// called multiple times.
	///
	public void InitializeAliasAnalysis(){}


	public AliasAnalysis(AliasAnalysis prev){
		previousAliasAnalysis = prev;
	}

	/// UnknownSize - This is a special value which can be used with the
	/// size arguments in alias queries to indicate that the caller does not
	/// know the sizes of the potential memory references.
	//protected static final long UnknownSize = ~UINT64_C(0);
	protected static final long UnknownSize = 1844674407370955161L;


	/// getTypeStoreSize - Return the TargetData store size for the given type,
	/// if known, or a conservative value otherwise.
	///
	public long getTypeStoreSize(Type type) {
		// TODO : Implement after getting TargetData
		//return TD ? TD->getTypeStoreSize(Ty) : UnknownSize;
		//return UnknownSize;
		
		long sizeValue = -1;
		
		if(type.isPrimitiveType()){
			sizeValue = type.getPrimitiveSizeInBits();
		}
		else if(type.isStructType()){
			StructType strType = (StructType) type;
			sizeValue = strType.getSize();
		}
		else if(type.isArrayType()){
			ArrayType at = (ArrayType) type;
			sizeValue = at.getSize();
		}
		else if(type.isPointerType()){
			sizeValue = ((PointerType) type).getPointerSize();
		}
		
		return sizeValue;
	}

	//===--------------------------------------------------------------------===//
	/// Alias Queries...
	///

	/// getLocation - Fill in Loc with information about the memory reference by
	/// the given instruction.
	private StorageLocation getStorageLocation(final LoadInst loadInst){
		return new StorageLocation(loadInst.getOperand(0),
				getTypeStoreSize(loadInst.getType())
				//,LI->getMetadata(LLVMContext::MD_tbaa));
		);
	}

	private StorageLocation getStorageLocation(final StoreInst storeInst){
		return new StorageLocation(storeInst.getOperand(1),
				getTypeStoreSize(storeInst.getType())
				//,LI->getMetadata(LLVMContext::MD_tbaa));
		);
	}

	// TODO : Implement these when we implement the corresponding instructions
	//	public StorageLocation getStorageLocation(final VAArgInst VI){
	//	}
	//
	//	public StorageLocation getStorageLocation(final AtomicCmpXchgInst CXI){
	//
	//	}
	//
	//	public StorageLocation getStorageLocation(final AtomicRMWInst RMWI){
	//
	//	}
	//
	//	public static StorageLocation getLocationForSource(final MemTransferInst MTI){
	//		//TODO Implement this
	//		return null;
	//	}
	//
	//	public static StorageLocation getLocationForDest(final MemIntrinsic MI){
	//		//TODO Implement this
	//		return null;
	//	}


	/// alias - The main low level interface to the alias analysis implementation.
	/// Returns an AliasResult indicating whether the two pointers are aliased to
	/// each other.  This is the interface that must be implemented by specific
	/// alias analysis implementations.
	public abstract AliasResult alias(final StorageLocation LocA, final StorageLocation LocB);

	/// alias - A convenience wrapper.
	public AliasResult alias(final Value V1, long V1Size,
			final Value V2, long V2Size) {
		return alias(new StorageLocation(V1, V1Size), new StorageLocation(V2, V2Size));
	}

	/// alias - A convenience wrapper.
	public AliasResult alias(final Value V1, final Value V2) {
		return alias(V1, UnknownSize, V2, UnknownSize);
	}

	/// isNoAlias - A trivial helper function to check to see if the specified
	/// pointers are no-alias.
	public boolean isNoAlias(final StorageLocation LocA, final StorageLocation LocB) {
		return alias(LocA, LocB) == AliasResult.NO_ALIAS;
	}

	/// isNoAlias - A convenience wrapper.
	public boolean isNoAlias(final Value V1, long V1Size,
			final Value V2, long V2Size) {
		return isNoAlias(new StorageLocation(V1, V1Size), new  StorageLocation(V2, V2Size));
	}

	/// isMustAlias - A convenience wrapper.
	public boolean isMustAlias(final StorageLocation LocA, final StorageLocation LocB) {
		return alias(LocA, LocB) == AliasResult.MUST_ALIAS;
	}

	/// isMustAlias - A convenience wrapper.
	public boolean isMustAlias(final Value V1, final Value V2) {
		return alias(V1, 1, V2, 1) == AliasResult.MUST_ALIAS;
	}

	/// pointsToConstantMemory - If the specified memory location is
	/// known to be constant, return true. If OrLocal is true and the
	/// specified memory location is known to be "local" (derived from
	/// an alloca), return true. Otherwise return false.
	public abstract boolean pointsToConstantMemory(final StorageLocation Loc,
			boolean OrLocal);

	/// pointsToConstantMemory - A convenient wrapper.
	public boolean pointsToConstantMemory(final Value P, boolean OrLocal) {
		return pointsToConstantMemory(new StorageLocation(P, UnknownSize), OrLocal);
	}

	// TODO Implement when we have implemneted ImmutableCallSite
	/// getModRefBehavior - Return the behavior when calling the given call site.
	//public abstract ModRefBehavior getModRefBehavior(ImmutableCallSite CS);

	/// getModRefBehavior - Return the behavior when calling the given function.
	/// For use when the call site is not known.
	public abstract ModRefBehavior getModRefBehavior(final Function F);

	/// doesNotAccessMemory - If the specified call is known to never read or
	/// write memory, return true.  If the call only reads from known-constant
	/// memory, it is also legal to return true.  Calls that unwind the stack
	/// are legal for this predicate.
	///
	/// Many optimizations (such as CSE and LICM) can be performed on such calls
	/// without worrying about aliasing properties, and many calls have this
	/// property (e.g. calls to 'sin' and 'cos').
	///
	/// This property corresponds to the GCC 'const' attribute.
	///
	//TODO : Implement the following once we have ImmutableCallSite implemented
	//	public boolean doesNotAccessMemory(ImmutableCallSite CS) {
	//		return getModRefBehavior(CS) == ModRefAttributes.DoesNotAccessMemory;
	//	}

	/// doesNotAccessMemory - If the specified function is known to never read or
	/// write memory, return true.  For use when the call site is not known.
	///
	//	public boolean doesNotAccessMemory(final Function F) {
	//		return getModRefBehavior(F) == DoesNotAccessMemory;
	//	}

	/// onlyReadsMemory - If the specified call is known to only read from
	/// non-volatile memory (or not access memory at all), return true.  Calls
	/// that unwind the stack are legal for this predicate.
	///
	/// This property allows many common optimizations to be performed in the
	/// absence of interfering store instructions, such as CSE of strlen calls.
	///
	/// This property corresponds to the GCC 'pure' attribute.
	///
	//	public boolean onlyReadsMemory(ImmutableCallSite CS) {
	//		return onlyReadsMemory(getModRefBehavior(CS));
	//	}

	/// onlyReadsMemory - If the specified function is known to only read from
	/// non-volatile memory (or not access memory at all), return true.  For use
	/// when the call site is not known.
	///
	public boolean onlyReadsMemory(final Function F) {
		return onlyReadsMemory(getModRefBehavior(F));
	}

	/// onlyReadsMemory - Return true if functions with the specified behavior are
	/// known to only read from non-volatile memory (or not access memory at all).
	///
	public static boolean onlyReadsMemory(ModRefBehavior MRB) {
		int result = MRB.getModRefBehaviorValue() & ModRefResult.MOD.getModRefValue();
		if(result == 0){
			return true;
		}
		else{
			return false;
		}
	}

	/// onlyAccessesArgPointees - Return true if functions with the specified
	/// behavior are known to read and write at most from objects pointed to by
	/// their pointer-typed arguments (with arbitrary offsets).
	///
	public static boolean onlyAccessesArgPointees(ModRefBehavior MRB) {
		int result = MRB.getModRefBehaviorValue() & ModRefAttributes.ANYWHERE.getModRefAttributesValue()
		& ~ModRefAttributes.ARG_POINTEES.getModRefAttributesValue();
		if(result == 0){
			return true;
		}
		else{
			return false;
		}
	}

	/// doesAccessArgPointees - Return true if functions with the specified
	/// behavior are known to potentially read or write from objects pointed
	/// to be their pointer-typed arguments (with arbitrary offsets).
	///
	public static boolean doesAccessArgPointees(ModRefBehavior MRB) {

		int result1 = MRB.getModRefBehaviorValue() & ModRefResult.MOD_REF.getModRefValue();
		int result2 = 
			MRB.getModRefBehaviorValue() & ModRefAttributes.ARG_POINTEES.getModRefAttributesValue();

		if(result1 > 0 && result1 > 0){
			return true;
		}
		else{
			return false;
		}
	}

	/// getModRefInfo - Return information about whether or not an instruction may
	/// read or write the specified memory location.  An instruction
	/// that doesn't read or write memory may be trivially LICM'd for example.
	public ModRefResult getModRefInfo(final Instruction I,
			final StorageLocation Loc) {

		InstructionID instId = I.getInstructionID();

		switch (instId) {
		case VA_ARG_INST:  
			// TODO return getModRefInfo((VAArgInst)I, Loc);
			return ModRefResult.MOD_REF;
		case LOAD_INST:   
			return getModRefInfo((LoadInst)I,  Loc);
		case STORE_INST:  
			return getModRefInfo((StoreInst)I, Loc);
		case FENCE_INST:  
			// TODO return getModRefInfo((FenceInst)I, Loc);
			return ModRefResult.MOD_REF;
		case ATOMIC_COMPXCHNG_INST:
			// TODO return getModRefInfo((AtomicCmpXchgInst)I, Loc);
			return ModRefResult.MOD_REF;
		case ATOMIC_RMW_INST:
			// TODO return getModRefInfo((AtomicRMWInst)I, Loc);
			return ModRefResult.MOD_REF;
		case CALL_INST:   
			return getModRefInfo((CallInst)I,  Loc);
		case INVOKE_INST:
			//TODO return getModRefInfo((InvokeInst)I,Loc);
			return ModRefResult.MOD_REF;
		default:                  
			return ModRefResult.NO_MOD_REF;
		}
	}

	/// getModRefInfo - A convenience wrapper.
	public ModRefResult getModRefInfo(final Instruction I,
			final Value P, long Size) {
		return getModRefInfo(I, new StorageLocation(P, Size));
	}

	// TODO Implement when we have implemneted ImmutableCallSite
	/// getModRefInfo (for call sites) - Return whether information about whether
	/// a particular call site modifies or reads the specified memory location.
	//	public abstract ModRefResult getModRefInfo(ImmutableCallSite CS,
	//			final StorageLocation Loc);
	//
	//	/// getModRefInfo (for call sites) - A convenience wrapper.
	//	public ModRefResult getModRefInfo(ImmutableCallSite CS,
	//			final Value P, long Size) {
	//		return getModRefInfo(CS, Location(P, Size));
	//	}

	/// getModRefInfo (for calls) - Return whether information about whether
	/// a particular call modifies or reads the specified memory location.
	public ModRefResult getModRefInfo(final CallInst C, final StorageLocation Loc) {
		// TODO return getModRefInfo(ImmutableCallSite(C), Loc);
		return ModRefResult.MOD_REF;
	}

	/// getModRefInfo (for calls) - A convenience wrapper.
	public ModRefResult getModRefInfo(final CallInst C, final Value P, long Size) {
		return getModRefInfo(C, new StorageLocation(P, Size));
	}

	/// getModRefInfo (for invokes) - Return whether information about whether
	/// a particular invoke modifies or reads the specified memory location.
	//TODO Implement when have the InvokeInst
	//	public ModRefResult getModRefInfo(final InvokeInst I,
	//			final StorageLocation Loc) {
	//		return getModRefInfo(ImmutableCallSite(I), Loc);
	//	}
	//
	//	/// getModRefInfo (for invokes) - A convenience wrapper.
	//	public ModRefResult getModRefInfo(final InvokeInst I,
	//			final Value P, long Size) {
	//		return getModRefInfo(I, new StorageLocation(P, Size));
	//	}

	/// getModRefInfo (for loads) - Return whether information about whether
	/// a particular load modifies or reads the specified memory location.
	public ModRefResult getModRefInfo(final LoadInst L, final StorageLocation Loc){
		// Be conservative in the face of volatile/atomic.
		if (!L.isUnordered())
			return ModRefResult.MOD_REF;

		// If the load address doesn't alias the given address, it doesn't read
		// or write the specified memory.
		if (alias(getStorageLocation(L), Loc).ordinal() == AliasResult.NO_ALIAS.ordinal())
			return ModRefResult.NO_MOD_REF;

		// Otherwise, a load just reads.
		return ModRefResult.REF;
	}

	/// getModRefInfo (for loads) - A convenience wrapper.
	public ModRefResult getModRefInfo(final LoadInst L, final Value P, long Size) {
		return getModRefInfo(L, new StorageLocation(P, Size));
	}

	/// getModRefInfo (for stores) - Return whether information about whether
	/// a particular store modifies or reads the specified memory location.
	public ModRefResult getModRefInfo(final StoreInst S, final StorageLocation Loc){
		// Be conservative in the face of volatile/atomic.
		if (!S.isUnordered())
			return ModRefResult.MOD_REF;

		// If the store address cannot alias the pointer in question, then the
		// specified memory cannot be modified by the store.
		if (alias(getStorageLocation(S), Loc).ordinal() == AliasResult.NO_ALIAS.ordinal())
			return ModRefResult.NO_MOD_REF;

		// If the pointer is a pointer to constant memory, then it could not have been
		// modified by this store.
		if (pointsToConstantMemory(Loc, false))
			return ModRefResult.NO_MOD_REF;

		// Otherwise, a store just writes.
		return ModRefResult.MOD;
	}

	/// getModRefInfo (for stores) - A convenience wrapper.
	public ModRefResult getModRefInfo(final StoreInst S, final Value P, long Size){
		return getModRefInfo(S, new StorageLocation(P, Size));
	}

	/// getModRefInfo (for fences) - Return whether information about whether
	/// a particular store modifies or reads the specified memory location.
	//TODO Implement once we have the Fence instruction
	//	public ModRefResult getModRefInfo(final FenceInst S, final StorageLocation Loc) {
	//		// Conservatively correct.  (We could possibly be a bit smarter if
	//		// Loc is a alloca that doesn't escape.)
	//		return ModRefResult.MOD_REF;
	//	}

	//TODO Implement once we have the Fence instruction
	/// getModRefInfo (for fences) - A convenience wrapper.
	//	public ModRefResult getModRefInfo(final FenceInst S, final Value P, long Size){
	//		return getModRefInfo(S, new StorageLocation(P, Size));
	//	}

	/// getModRefInfo (for cmpxchges) - Return whether information about whether
	/// a particular cmpxchg modifies or reads the specified memory location.
	//TODO Implement once we have the AtomicCmpXchgInst instruction
	//	public ModRefResult getModRefInfo(final AtomicCmpXchgInst CX, final StorageLocation Loc){
	//		// Acquire/Release cmpxchg has properties that matter for arbitrary addresses.
	//		  if (CX->getOrdering() > Monotonic)
	//		    return ModRef;
	//
	//		  // If the cmpxchg address does not alias the location, it does not access it.
	//		  if (!alias(getLocation(CX), Loc))
	//		    return NoModRef;
	//
	//		  return ModRef;
	//
	//	}
	/// getModRefInfo (for cmpxchges) - A convenience wrapper.
	//	ModRefResult getModRefInfo(const AtomicCmpXchgInst *CX,
	//			const Value *P, unsigned Size) {
	//		return getModRefInfo(CX, Location(P, Size));
	//	}

	//TODO Implement once we have the AtomicRMWInst instruction
	/// getModRefInfo (for atomicrmws) - Return whether information about whether
	/// a particular atomicrmw modifies or reads the specified memory location.
	//	public ModRefResult getModRefInfo(final AtomicRMWInst RMW, final Location Loc){
	//	}
	//
	//	/// getModRefInfo (for atomicrmws) - A convenience wrapper.
	//	ModRefResult getModRefInfo(const AtomicRMWInst *RMW,
	//			const Value *P, unsigned Size) {
	//		return getModRefInfo(RMW, Location(P, Size));
	//	}

	/// getModRefInfo (for va_args) - Return whether information about whether
	/// a particular va_arg modifies or reads the specified memory location.
	//TODO Implement once we have the VAArgInst instruction
	//	ModRefResult getModRefInfo(const VAArgInst* I, const Location &Loc);
	//
	//	/// getModRefInfo (for va_args) - A convenience wrapper.
	//	ModRefResult getModRefInfo(const VAArgInst* I, const Value* P, uint64_t Size){
	//		return getModRefInfo(I, Location(P, Size));
	//	}

	/// getModRefInfo - Return information about whether two call sites may refer
	/// to the same set of memory locations.  See 
	///   http://llvm.org/docs/AliasAnalysis.html#ModRefInfo
	/// for details.
	// TODO Implement once we have the ImmutableCallSite implemented
	//	public abstract ModRefResult getModRefInfo(ImmutableCallSite CS1,
	//			ImmutableCallSite CS2);

	//===--------------------------------------------------------------------===//
	/// Higher level methods for querying mod/ref information.
	///

	/// canBasicBlockModify - Return true if it is possible for execution of the
	/// specified basic block to modify the value pointed to by Ptr.
	public boolean canBasicBlockModify(final BasicBlock BB, final StorageLocation Loc){
		Instruction firstIns = BB.getFirstInstruction();
		Instruction lastIns = BB.getLastInstruction();
		return canInstructionRangeModify(firstIns, lastIns, Loc);
	}

	/// canBasicBlockModify - A convenience wrapper.
	public boolean canBasicBlockModify(final BasicBlock BB, final Value P, long Size){
		return canBasicBlockModify(BB, new StorageLocation(P, Size));
	}

	/// canInstructionRangeModify - Return true if it is possible for the
	/// execution of the specified instructions to modify the value pointed to by
	/// Ptr.  The instructions to consider are all of the instructions in the
	/// range of [I1,I2] INCLUSIVE.  I1 and I2 must be in the same basic block.
	public boolean canInstructionRangeModify(final Instruction I1, final Instruction I2,
			final StorageLocation Loc) throws IllegalArgumentException {

		BasicBlock parent = I1.getParent();

		if(parent != I2.getParent()){ 
			assert false : INSTRUCTION_RANGE_MOD_CHECK_INSTRUCTIONS_NOT_IN_SAME_BLOCK;;
			LOG.error(INSTRUCTION_RANGE_MOD_CHECK_INSTRUCTIONS_NOT_IN_SAME_BLOCK);
			throw new IllegalArgumentException(INSTRUCTION_RANGE_MOD_CHECK_INSTRUCTIONS_NOT_IN_SAME_BLOCK);
		}

		int startIndex = parent.getInstructionIndex(I1);
		int endIndex = parent.getInstructionIndex(I2);

		Iterator<Instruction> instructionsInRange = parent.getInstructionSubset(startIndex, endIndex);
		while(instructionsInRange.hasNext()){
			if ((getModRefInfo(instructionsInRange.next(), Loc).ordinal() & ModRefResult.MOD.ordinal()) > 0){
				return true;
			}
		}
		
		return false;
	}

	/// canInstructionRangeModify - A convenience wrapper.
	public boolean canInstructionRangeModify(final Instruction I1, final Instruction I2,
			final Value Ptr, long Size) {
		return canInstructionRangeModify(I1, I2, new StorageLocation(Ptr, Size));
	}

	//===--------------------------------------------------------------------===//
	/// Methods that clients should call when they transform the program to allow
	/// alias analyses to update their internal data structures.  Note that these
	/// methods may be called on any instruction, regardless of whether or not
	/// they have pointer-analysis implications.
	///

	/// deleteValue - This method should be called whenever an LLVM Value is
	/// deleted from the program, for example when an instruction is found to be
	/// redundant and is eliminated.
	///
	public abstract void deleteValue(Value V);

	/// copyValue - This method should be used whenever a preexisting value in the
	/// program is copied or cloned, introducing a new value.  Note that analysis
	/// implementations should tolerate clients that use this method to introduce
	/// the same value multiple times: if the analysis already knows about a
	/// value, it should ignore the request.
	///
	public abstract void copyValue(Value From, Value To);

	/// addEscapingUse - This method should be used whenever an escaping use is
	/// added to a pointer value.  Analysis implementations may either return
	/// conservative responses for that value in the future, or may recompute
	/// some or all internal state to continue providing precise responses.
	///
	/// Escaping uses are considered by anything _except_ the following:
	///  - GEPs or bitcasts of the pointer
	///  - Loads through the pointer
	///  - Stores through (but not of) the pointer
	public abstract void addEscapingUse(User U);

	/// replaceWithNewValue - This method is the obvious combination of the two
	/// above, and it provided as a helper to simplify client code.
	///
	public void replaceWithNewValue(Value Old, Value New) {
		copyValue(Old, New);
		deleteValue(Old);
	}

	// Utility functions
	/// isNoAliasCall - Return true if this pointer is returned by a noalias
	/// function.
	public static boolean isNoAliasCall(final Value V){
		//TODO Implement after ImmutableCallSite
		//		if (isa<CallInst>(V) || isa<InvokeInst>(V))
		//		    return ImmutableCallSite(cast<Instruction>(V))
		//		      .paramHasAttr(0, Attribute::NoAlias);
		//		  return false;

		return false;
	}

	/// isIdentifiedObject - Return true if this pointer refers to a distinct and
	/// identifiable object.  This returns true for:
	///    Global Variables and Functions (but not Global Aliases)
	///    Allocas and Mallocs
	///    ByVal and NoAlias Arguments
	///    NoAlias returns
	///
	public boolean isIdentifiedObject(final Value V){
		if (V instanceof AllocaInst)
			return true;
		else if (V instanceof GlobalVariable // TODO After implementing GlobalAlias : && ! (V instanceof GlobalAlias))
		)
			return true;
		else if (isNoAliasCall(V))
			return true;
		else if (V instanceof Argument){
			Argument arg = (Argument) V;
			return arg.hasNoAliasAttr() || arg.hasByValAttr();
		}

		return false;
	}
	/// isKnownNonNull - Return true if this pointer couldn't possibly be null by
	/// its definition.  This returns true for allocas, non-extern-weak globals and
	/// byval arguments.
	public boolean isKnownNonNull(final Value V){
		// Alloca never returns null, malloc might.
		if (V instanceof AllocaInst)
			return true;

		// A byval argument is never null.
		else if (V instanceof Argument){
			Argument arg = (Argument) V;
			return arg.hasByValAttr();
		}

		// Global values are not null unless extern weak.
		else if (V instanceof GlobalValue){
			GlobalValue globalValue = (GlobalValue) V;
			return globalValue.hasWeakLinkage();
		}
		return false;
	}


	public TargetData getTargetData() {
		return targetData;
	}


	public void setTargetData(TargetData targetData) {
		this.targetData = targetData;
	}


	public AliasAnalysis getPreviousAliasAnalysis() {
		return previousAliasAnalysis;
	}
}

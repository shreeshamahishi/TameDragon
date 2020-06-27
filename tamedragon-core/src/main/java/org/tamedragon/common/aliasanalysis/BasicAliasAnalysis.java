package org.tamedragon.common.aliasanalysis;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tamedragon.common.Pair;
import org.tamedragon.common.llvmir.instructions.AllocaInst;
import org.tamedragon.common.llvmir.instructions.CallInst;
import org.tamedragon.common.llvmir.instructions.GetElementPtrInst;
import org.tamedragon.common.llvmir.instructions.Instruction;
import org.tamedragon.common.llvmir.instructions.PhiNode;
import org.tamedragon.common.llvmir.instructions.SelectInst;
import org.tamedragon.common.llvmir.instructions.Instruction.InstructionID;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionDetailAccessException;
import org.tamedragon.common.llvmir.math.APInt;
import org.tamedragon.common.llvmir.math.ULong;
import org.tamedragon.common.llvmir.types.Argument;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.ConstantInt;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.GlobalAlias;
import org.tamedragon.common.llvmir.types.GlobalValue;
import org.tamedragon.common.llvmir.types.IntegerType;
import org.tamedragon.common.llvmir.types.PointerType;
import org.tamedragon.common.llvmir.types.StructType;
import org.tamedragon.common.llvmir.types.Type;
import org.tamedragon.common.llvmir.types.User;
import org.tamedragon.common.llvmir.types.Value;
import org.tamedragon.common.llvmir.types.Type.TypeID;
import org.tamedragon.common.llvmir.types.Value.ValueTypeID;
import org.tamedragon.common.llvmir.types.exceptions.TypeCreationException;
import org.tamedragon.target.TargetData;

enum ExtensionKind{
	EK_NotExtended,
	EK_SignExt,
	EK_ZeroExt
}

class VariableGEPIndex{
	private Value value;
	private ExtensionKind extension;
	private long scale;

	public VariableGEPIndex(Value val, ExtensionKind ext, long scale){
		this.value = val;
		this.extension = ext;
		this.scale = scale;
	}

	public Value getValue() {
		return value;
	}
	public void setValue(Value value) {
		this.value = value;
	}

	public ExtensionKind getExtension() {
		return extension;
	}

	public void setExtension(ExtensionKind extension) {
		this.extension = extension;
	}

	public long getScale() {
		return scale;
	}

	public void setScale(long scale) {
		this.scale = scale;
	}
}

/**
 * A helper class that denotes the value and the base offset associated
 * with a decomposed GEP value
 *
 */
class DecomposedGEPValue{
	private Value value;
	private long offset;

	public DecomposedGEPValue(Value value, long offset){
		this.value = value;
		this.offset = offset;
	}

	public Value getValue() {
		return value;
	}

	public void setValue(Value value) {
		this.value = value;
	}

	public long getOffset() {
		return offset;
	}

	public void setOffset(long offset) {
		this.offset = offset;
	}
}

public class BasicAliasAnalysis extends AliasAnalysis{

	private static final Logger LOG = LoggerFactory.getLogger(BasicAliasAnalysis.class);

	// Error messages
	public static final String NO_SUPPORT_FOR_INTERPROCEDURAL_QUERIES  = "BasicAliasAnalysis does not support interprocedural queries.";
	private short MAX_NUMP_PHI_BBs_VALUE_REACHABILITY_CHECK = 20;

	// Visited - Track instructions visited by pointsToConstantMemory.
	private Set<Value>  Visited;

	private Set<BasicBlock>  visitedPhiBBs;


	public BasicAliasAnalysis(AliasAnalysis prev) {
		super(prev);

		Visited = new HashSet<Value>();

	}

	private static Function getParent(final Value V) {
		ValueTypeID valueTypeID = V.getValueTypeID();
		if (valueTypeID == ValueTypeID.INSTRUCTION){
			Instruction instr = (Instruction) V;
			return instr.getParent().getParent();
		}

		if (valueTypeID == ValueTypeID.ARGUMENT){
			Argument arg = (Argument) V;
			return arg.getParent();
		}

		return null;
	}

	private static DecomposedGEPValue DecomposeGEPExpression(Value V, long BaseOffs,
			List<VariableGEPIndex> VarIndices ,final TargetData targetData){
		// Limit recursion depth to limit compile time in crazy cases.
		int MaxLookup = 6;

		GetElementPtrInst gePtr = null;

		BaseOffs = 0;
		do {
			// See if this is a bitcast or GEP.

			ValueTypeID valueTypeID = V.getValueTypeID();
			if(valueTypeID != ValueTypeID.INSTRUCTION && valueTypeID != ValueTypeID.CONST_EXPR){
				if(valueTypeID == ValueTypeID.GLOBAL_ALIAS){
					GlobalAlias globalAlias = (GlobalAlias)V;

					if(!globalAlias.mayBeOverridden()){
						V = globalAlias.getAliasee();
						continue;
					}
				}

				return new DecomposedGEPValue(V, BaseOffs);
			}

			if(valueTypeID == ValueTypeID.INSTRUCTION){
				Instruction instr = (Instruction) V;
				InstructionID instrID = instr.getInstructionID();
				if(instrID == InstructionID.BIT_CAST_INST){
					V = instr.getOperand(0);
					continue;
				}

				if(instrID != InstructionID.GET_ELEMENT_PTR){

					// If it's not a GEP, hand it off to SimplifyInstruction to see if it
					// can come up with something. This matches what GetUnderlyingObject does.
					// TODO: Implement SimplifyInstruction
					//				        if (const Value *Simplified =
					//				              SimplifyInstruction(const_cast<Instruction *>(I), TD)) {
					//				          V = Simplified;
					//				          continue;
					//				        }

					return new DecomposedGEPValue(V, BaseOffs);
				}

				gePtr = (GetElementPtrInst) instr;


				// Don't attempt to analyze GEPs over unsized objects.

				Type operandType = gePtr.getOperand(0).getType();
				TypeID oprTypeID = operandType.getTypeId();
				if(oprTypeID == TypeID.POINTER_ID){
					PointerType ptrType = (PointerType) operandType;
					if(!ptrType.getContainedType().isSized()){
						return new DecomposedGEPValue(V, BaseOffs);
					}
				}


				// If we are lacking TargetData information, we can't compute the offets of
				// elements computed by GEPs.  However, we can handle bitcast equivalent
				// GEPs.
				if (targetData == null) {
					if (!gePtr.hasAllZeroIndices())
						return new DecomposedGEPValue(V, BaseOffs);
					V = gePtr.getOperand(0);
					continue;
				}

				// Walk the indices of the GEP, accumulating them into BaseOff/VarIndices.

				//gep_type_iterator GTI = gep_type_begin(GEPOp);
				List<Pair<Value, Type>> indexVsType = gePtr.getIndexVsType();
				int numGepOperands = gePtr.getNumOperands();
				for(int i = 1; i < numGepOperands; i++){
					Value Index = gePtr.getOperand(i);

					Type type = indexVsType.get(i).getSecond();
					TypeID typeID = type.getTypeId();


					// Compute the (potentially symbolic) offset in bytes for this index.
					if (typeID == TypeID.STRUCT_ID) {
						StructType STy = (StructType) type;

						// For a struct, add the member offset.
						ConstantInt cstInt = (ConstantInt) Index;

						//TODO Implement zero extension
						//int FieldNo = cstInt.getZeroExt();
						int FieldNo = cstInt.getApInt().getZExtValueNew().intValue();
						if (FieldNo == 0)
							continue;

						try{
							BaseOffs += targetData.getStructLayout(STy).getElementOffset(FieldNo);
						}
						catch(Exception e){
							e.printStackTrace();
							System.exit(-1);
						}

						continue;
					}

					// For an array/pointer, add the element offset, explicitly scaled.
					if (Index instanceof ConstantInt) {
						ConstantInt CIdx = (ConstantInt) Index;
						if (CIdx.isZero()) 
							continue;
						//TODO Implement getSignExtension and use that instead of ZeroExtension below
						//BaseOffs += targetData.getTypeAllocSize(type) * CIdx.getApInt().getSExtValue();
						try{
							BaseOffs += targetData.getTypeAllocSize(type) * CIdx.getApInt().getZExtValueNew().intValue();
						}
						catch(Exception e){
							e.printStackTrace();
							System.exit(-1);
						}

						continue;
					}

					long Scale = 0;
					try{
						Scale = targetData.getTypeAllocSize(type);
					}
					catch(Exception e){
						e.printStackTrace();
						System.exit(-1);
					}
					ExtensionKind Extension = ExtensionKind.EK_NotExtended;

					// If the integer type is smaller than the pointer size, it is implicitly
					// sign extended to pointer size.
					int Width = ((IntegerType)Index.getType()).getNumBits();
					if (targetData.getPointerSizeInBits() > Width){
						Extension = ExtensionKind.EK_SignExt;
					}

					// Use GetLinearExpression to decompose the index into a C1*V+C2 form.
					APInt IndexScale = new APInt(Width, ULong.valueOf(0), false);
					APInt IndexOffset = new APInt(Width,  ULong.valueOf(0), false);

					Index = GetLinearExpression(Index, IndexScale, IndexOffset, Extension,
							targetData, 0);

					// The GEP index scale ("Scale") scales C1*V+C2, yielding (C1*V+C2)*Scale.
					// This gives us an aggregate computation of (C1*Scale)*V + C2*Scale.
					//TODO : Implement getSExtValue and change this below to use it instead of getZextValue
					//					BaseOffs += IndexOffset.getSExtValue()*Scale;
					//					Scale *= IndexScale.getSExtValue();
					BaseOffs += IndexOffset.getZExtValueNew().intValue()*Scale;
					Scale *= IndexScale.getZExtValueNew().intValue();


					// If we already had an occurrence of this index variable, merge this
					// scale into it.  For example, we want to handle:
					//   A[x][x] -> x*16 + x*4 -> x*20
					// This also ensures that 'x' only appears in the index list once.
					List<Integer> delList = new ArrayList<Integer>();
					for (int j = 0, e = VarIndices.size(); j != e; j++) {
						if (VarIndices.get(j).getValue() == Index &&
								VarIndices.get(j).getExtension() == Extension) {
							Scale += VarIndices.get(j).getScale();
							delList.add(j);
							//VarIndices.erase(VarIndices.begin()+i);
							break;
						}
					}
					for(Integer delIndex : delList){
						VarIndices.remove(delIndex);
					}

					// Make sure that we have a scale that makes sense for this target's
					// pointer size.
					int ShiftBits = 64-targetData.getPointerSizeInBits();

					if (ShiftBits > 0) {
						Scale <<= ShiftBits;
						Scale = (long)Scale >> ShiftBits;
					}

					if (Scale > 0) {
						VariableGEPIndex Entry = new VariableGEPIndex(Index, Extension,
								(long)Scale);
						VarIndices.add(Entry);
					}
				}
			}

			// Analyze the base pointer next.
			V = gePtr.getOperand(0);
		} while (--MaxLookup > 0);

		// If the chain of expressions is too deep, just return early.
		return new DecomposedGEPValue(V, BaseOffs);
	}

	private Value getUnderlyingObject(Value value, final TargetData targetData, int maxLookUp)
			throws Exception {
		if (!value.getType().isPointerType()) {
			return value;
		}

		for (int count = 0; maxLookUp == 0 || count < maxLookUp; count++) {
			ValueTypeID valueTypeID = value.getValueTypeID();
			if (valueTypeID == ValueTypeID.GLOBAL_ALIAS) {
				GlobalAlias globalAlias = (GlobalAlias) value;
				// TODO Implement this
				//if (globalAlias.isInterposable()) {
				if (globalAlias.mayBeOverridden()) {
					return value;
				}
				value = globalAlias.getAliasee();
			}
			else{
				Instruction instr = (Instruction)value;
				InstructionID instrID = instr.getInstructionID();
				if(instrID == InstructionID.GET_ELEMENT_PTR){
					GetElementPtrInst gep = (GetElementPtrInst) instr;
					value = gep.getPointerOperand();
				}
				else if(instrID == InstructionID.BIT_CAST_INST
						// TODO : Implement instruction
						//|| instrID == Instruction.AddressSpaceCast){
						) {
					value = instr.getOperand(0);
				}
				else if(instrID == InstructionID.ALLOCA_INST){
					// An alloca can't be further simplified.
				      return value;
				}
				else if(instrID == InstructionID.CALL_INST || instrID == InstructionID.INVOKE_INST) {
					// TODO Implement a common base class for call and invoke instructions and then 
					// implement getArgumentAliasingToReturnedPointer()
					//CallInst callInst = (CallInst) instr;
					//value = getArgumentAliasingToReturnedPointer();
					CallInst callInstr = (CallInst) instr;
					value = callInstr.getArgOperand(0);
				}
				
				else {
					// See if InstructionSimplify knows any relevant tricks.
					// TODO: Acquire a DominatorTree and use it.
					//TODO Implement SimplifyInstruction
					//Value Simplified = SimplifyInstruction(I, TD, 0);
					Value Simplified = null;
					if (Simplified != null) {
						value = Simplified;
						continue;
					}

					return value;
				}

				if(!value.getType().isPointerType()){
					throw new Exception("Unexpected operand type!");
				}
			}
		}

		return value;
	}

	private AliasResult aliasGEP(final GetElementPtrInst GEP1, long V1Size,
			final Value V2, long V2Size,
			// TODO Implement after we define MDNode final MDNode *V2TBAAInfo,
			final Value UnderlyingV1,
			final Value UnderlyingV2) {
		long GEP1BaseOffset = 0;
		List<VariableGEPIndex> GEP1VariableIndices = new  ArrayList<VariableGEPIndex>();

		// If we have two gep instructions with must-alias'ing base pointers, figure
		// out if the indexes to the GEP tell us anything about the derived pointer.
		if (V2 instanceof GetElementPtrInst) {

			GetElementPtrInst gepInstr = (GetElementPtrInst)V2;

			// Do the base pointers alias?
			AliasResult BaseAlias = aliasCheck(UnderlyingV1, UnknownSize, 0,
					UnderlyingV2, UnknownSize, 0);

			// If we get a No or May, then return it immediately, no amount of analysis
			// will improve this situation.
			if (BaseAlias != AliasResult.MUST_ALIAS) return BaseAlias;

			// Otherwise, we have a MustAlias.  Since the base pointers alias each other
			// exactly, see if the computed offset from the common pointer tells us
			// about the relation of the resulting pointer.
			DecomposedGEPValue decomposedGEP1 = DecomposeGEPExpression(GEP1, GEP1BaseOffset, GEP1VariableIndices, targetData);
			Value GEP1BasePtr = decomposedGEP1.getValue();
			GEP1BaseOffset = decomposedGEP1.getOffset();

			long GEP2BaseOffset = 0;
			List<VariableGEPIndex> GEP2VariableIndices = new ArrayList<VariableGEPIndex>();
			DecomposedGEPValue decomposedGEP2 = DecomposeGEPExpression(gepInstr, GEP2BaseOffset, GEP2VariableIndices, targetData);
			Value GEP2BasePtr = decomposedGEP2.getValue();
			GEP2BaseOffset = decomposedGEP2.getOffset();

			// If DecomposeGEPExpression isn't able to look all the way through the
			// addressing operation, we must not have TD and this is too complex for us
			// to handle without it.
			if (GEP1BasePtr != UnderlyingV1 || GEP2BasePtr != UnderlyingV2) {
				if(targetData != null){
					System.err.println("DecomposeGEPExpression and GetUnderlyingObject disagree!");
					System.exit(-1);
				}
				return AliasResult.MAY_ALIAS;
			}

			// Subtract the GEP2 pointer from the GEP1 pointer to find out their
			// symbolic difference.
			GEP1BaseOffset -= GEP2BaseOffset;
			GetIndexDifference(GEP1VariableIndices, GEP2VariableIndices);
		} 
		else {
			// Check to see if these two pointers are related by the getelementptr
			// instruction.  If one pointer is a GEP with a non-zero index of the other
			// pointer, we know they cannot alias.

			// If both accesses are unknown size, we can't do anything useful here.
			if (V1Size == UnknownSize && V2Size == UnknownSize)
				return AliasResult.MAY_ALIAS;

			AliasResult R = aliasCheck(UnderlyingV1, UnknownSize, 0,
					V2, V2Size, null);
			if (R != AliasResult.MUST_ALIAS)
				// If V2 may alias GEP base pointer, conservatively returns MayAlias.
				// If V2 is known not to alias GEP base pointer, then the two values
				// cannot alias per GEP semantics: "A pointer value formed from a
				// getelementptr instruction is associated with the addresses associated
				// with the first operand of the getelementptr".
				return R;

			DecomposedGEPValue decomposedGEP1 = DecomposeGEPExpression(GEP1, GEP1BaseOffset, GEP1VariableIndices, targetData);

			Value GEP1BasePtr = decomposedGEP1.getValue();
			GEP1BaseOffset = decomposedGEP1.getOffset();

			// If DecomposeGEPExpression isn't able to look all the way through the
			// addressing operation, we must not have TD and this is too complex for us
			// to handle without it.
			if (GEP1BasePtr != UnderlyingV1) {
				if(targetData != null){ 
					System.err.println("DecomposeGEPExpression and GetUnderlyingObject disagree!");
					return AliasResult.MAY_ALIAS;
				}
			}
		}

		// In the two GEP Case, if there is no difference in the offsets of the
		// computed pointers, the resultant pointers are a must alias.  This
		// hapens when we have two lexically identical GEP's (for example).
		//
		// In the other case, if we have getelementptr <ptr>, 0, 0, 0, 0, ... and V2
		// must aliases the GEP, the end result is a must alias also.
		if (GEP1BaseOffset == 0 && GEP1VariableIndices.isEmpty())
			return  AliasResult.MUST_ALIAS;

		// If there is a constant difference between the pointers, but the difference
		// is less than the size of the associated memory object, then we know
		// that the objects are partially overlapping.  If the difference is
		// greater, we know they do not overlap.
		if (GEP1BaseOffset != 0 && GEP1VariableIndices.isEmpty()) {
			if (GEP1BaseOffset >= 0) {
				if (V2Size != UnknownSize) {
					if ((long)GEP1BaseOffset < V2Size)
						return AliasResult.PARTIAL_ALIAS;
					return AliasResult.NO_ALIAS;
				}
			} 
			else {
				if (V1Size != UnknownSize) {
					if (-(long)GEP1BaseOffset < V1Size)
						return AliasResult.PARTIAL_ALIAS;
					return AliasResult.NO_ALIAS;
				}
			}
		}

		// Try to distinguish something like &A[i][1] against &A[42][0].
		// Grab the least significant bit set in any of the scales.
		if (!GEP1VariableIndices.isEmpty()) {
			long Modulo = 0;
			for (int i = 0, e = GEP1VariableIndices.size(); i != e; i++)
				Modulo |= (long)GEP1VariableIndices.get(i).getScale();
			Modulo = Modulo ^ (Modulo & (Modulo - 1));

			// We can compute the difference between the two addresses
			// mod Modulo. Check whether that difference guarantees that the
			// two locations do not alias.
			long ModOffset = (long)GEP1BaseOffset & (Modulo - 1);
			if (V1Size != UnknownSize && V2Size != UnknownSize &&
					ModOffset >= V2Size && V1Size <= Modulo - ModOffset)
				return AliasResult.NO_ALIAS;
		}

		// Statically, we can see that the base objects are the same, but the
		// pointers have dynamic offsets which we can't resolve. And none of our
		// little tricks above worked.
		//
		// TODO: Returning PartialAlias instead of MayAlias is a mild hack; the
		// practical effect of this is protecting TBAA in the case of dynamic
		// indices into arrays of unions or malloc'd memory.
		return  AliasResult.PARTIAL_ALIAS;
	}

	private static void GetIndexDifference(
			List<VariableGEPIndex> Dest,
			List<VariableGEPIndex> Src) {
		if (Src.isEmpty())
			return;

		for (int i = 0, e = Src.size(); i != e; i++) {
			Value V = Src.get(i).getValue();
			ExtensionKind Extension = Src.get(i).getExtension();
			long Scale = Src.get(i).getScale();

			// Find V in Dest.  This is N^2, but pointer indices almost never have more
			// than a few variable indexes.

			for (int j = 0, size = Dest.size(); j != size; j++) {
				VariableGEPIndex destVariableGEPIdx = Dest.get(j);
				if (destVariableGEPIdx.getValue() != V || destVariableGEPIdx.getExtension() != Extension)
					continue;

				// If we found it, subtract off Scale V's from the entry in Dest.  If it
				// goes to zero, remove the entry.
				if (destVariableGEPIdx.getScale() != Scale){
					destVariableGEPIdx.setScale(destVariableGEPIdx.getScale() - Scale);
				}
				else{
					Dest.remove(j);
				}
				Scale = 0;

				break;
			}

			// If we didn't consume this entry, add it to the end of the Dest list.
			if (Scale > 0) {
				VariableGEPIndex Entry = new VariableGEPIndex(V, Extension, -1 * Scale);
				Dest.add(Entry);
			}
		}
	}

	private static boolean notDifferentParent(final Value O1, final Value O2) {

		Function F1 = getParent(O1);
		Function F2 = getParent(O2);

		return F1 == null || F2 == null || F1 == F2;
	}

	private static boolean isEscapeSource(final Value V) {

		ValueTypeID valueTypeID = V.getValueTypeID();

		if(valueTypeID == ValueTypeID.ARGUMENT){
			return true;
		}

		if(valueTypeID != ValueTypeID.INSTRUCTION){
			return false;
		}

		Instruction instr = (Instruction) V;
		InstructionID instrID = instr.getInstructionID();

		if (instrID == InstructionID.CALL_INST || instrID == InstructionID.INVOKE_INST)
			return true;

		// The load case works because isNonEscapingLocalObject considers all
		// stores to be escapes (it passes true for the StoreCaptures argument
		// to PointerMayBeCaptured).
		if (instrID == InstructionID.LOAD_INST)
			return true;

		return false;
	}

	/// isNonEscapingLocalObject - Return true if the pointer is to a function-local
	/// object that never escapes from the function.
	private static boolean isNonEscapingLocalObject(final Value V) {
		// If this is a local allocation, check to see if it escapes.

		if (V instanceof AllocaInst || isNoAliasCall(V))
			// Set StoreCaptures to True so that we can assume in our callers that the
			// pointer is not the result of a load instruction. Currently
			// PointerMayBeCaptured doesn't have any special analysis for the
			// StoreCaptures=false case; if it did, our callers could be refined to be
			// more precise.
			try{
				return CaptureTrackingUtil.pointerMayBeCaptured(V, false, /*StoreCaptures=*/true);
			}
		catch(Exception e){
			e.printStackTrace();
			System.exit(-1);
		}

		// If this is an argument that corresponds to a byval or noalias argument,
		// then it has not escaped before entering the function.  Check if it escapes
		// inside the function.
		if(V.getValueTypeID() == ValueTypeID.ARGUMENT){
			Argument arg = (Argument) V;

			//TODO Review this after reviewing hasByValAttr

			if (arg.hasByValAttr() || arg.hasNoAliasAttr()) {
				// Don't bother analyzing arguments already known not to escape.
				if (arg.hasNoCaptureAttr(0))
					return true;
				try{
					return CaptureTrackingUtil.pointerMayBeCaptured(V, false, /*StoreCaptures=*/true);
				}
				catch(Exception e){
					e.printStackTrace();
					System.exit(-1);
				}
			}
		}

		return false;
	}


	@Override
	public AliasResult alias(StorageLocation LocA, StorageLocation LocB) {
		if(!notDifferentParent(LocA.getStartOfLocationPtr(), LocB.getStartOfLocationPtr())){
			LOG.error(NO_SUPPORT_FOR_INTERPROCEDURAL_QUERIES);
			throw new IllegalArgumentException(NO_SUPPORT_FOR_INTERPROCEDURAL_QUERIES);
		}
		return aliasCheck(LocA.getStartOfLocationPtr(), LocA.getSize(), null,
				LocB.getStartOfLocationPtr(), LocB.getSize(), null);
	}

	private AliasResult aliasCheck(Value v1, long V1Size,
			Object object, Value v2, long V2Size, Object object2) {
		// If either of the memory references is empty, it doesn't matter what the
		// pointer values are.
		if (V1Size == 0 || V2Size == 0)
			return AliasResult.NO_ALIAS;

		//TODO Implement this
		// Strip off any casts if they exist.
		//v1 = v1.stripPointerCastsAndInvariantGroups();
		//v2 = v2.stripPointerCastsAndInvariantGroups();

		if (v1.getValueTypeID() == ValueTypeID.UNDEF_VALUE || v2.getValueTypeID() == ValueTypeID.UNDEF_VALUE){
			// Scalars cannot alias each other
			return AliasResult.NO_ALIAS; 
		}

		// Are we checking for alias of the same value?
		if(isValueEqualInPotentialCycles(v1, v2)) {
			return AliasResult.MUST_ALIAS;
		}
		if (v1 == v2){
			return AliasResult.MUST_ALIAS;
		}

		if (!v1.getType().isPointerType() || !v1.getType().isPointerType()){
			// Scalars cannot alias each other
			return AliasResult.NO_ALIAS; 
		}

		// Figure out what objects these things are pointing to if we can.
		Value o1 = null;
		Value o2 = null;

		try{
			o1 = getUnderlyingObject(v1, targetData, 6);
			o2 = getUnderlyingObject(v2, targetData, 6);
		}
		catch(Exception e){
			System.exit(-1); 
		}

		// Null values in the default address space don't point to any object, so they
		// don't alias any other pointer.

		ValueTypeID valueTypeIDO1 = o1.getValueTypeID();
		ValueTypeID valueTypeIDO2 = o2.getValueTypeID();

		if (valueTypeIDO1 == ValueTypeID.CONST_POINTER_NULL){
			PointerType ptrType = (PointerType)o1.getType();
			if (ptrType.getAddressSpace() == 0){
				return AliasResult.NO_ALIAS;
			}
		}

		if (valueTypeIDO2 == ValueTypeID.CONST_POINTER_NULL){
			PointerType ptrType = (PointerType)o2.getType();
			if (ptrType.getAddressSpace() == 0){
				return AliasResult.NO_ALIAS;
			}
		}

		if (o1 != o2) {
			// If V1/V2 point to two different objects we know that we have no alias.
			if (isIdentifiedObject(o1) && isIdentifiedObject(o2)){
				return AliasResult.NO_ALIAS;
			}

			// Constant pointers can't alias with non-const isIdentifiedObject objects.
			if ((o1.isAConstant() && isIdentifiedObject(o2) && !o2.isAConstant()) ||
					(o2.isAConstant() && isIdentifiedObject(o1) && !o1.isAConstant()))
				return AliasResult.NO_ALIAS;

			// Arguments can't alias with local allocations or noalias calls
			// in the same function.

			if (((o1 instanceof Argument) && (isObjectAllocaInst(o2)|| isNoAliasCall(o2))) ||
					(o2 instanceof Argument && (isObjectAllocaInst(o2)|| isNoAliasCall(o2)))){
				return AliasResult.NO_ALIAS;
			}

			// Most objects can't alias null.
			if ((o2.getValueTypeID() == ValueTypeID.CONST_POINTER_NULL) && isKnownNonNull(o1) ||
					(o1.getValueTypeID() == ValueTypeID.CONST_POINTER_NULL) && isKnownNonNull(o2))
				return AliasResult.NO_ALIAS;

			// If one pointer is the result of a call/invoke or load and the other is a
			// non-escaping local object within the same function, then we know the
			// object couldn't escape to a point where the call could return it.
			//
			// Note that if the pointers are in different functions, there are a
			// variety of complications. A call with a nocapture argument may still
			// temporary store the nocapture argument's value in a temporary memory
			// location if that memory location doesn't escape. Or it may pass a
			// nocapture value to other functions as long as they don't capture it.
			if (isEscapeSource(o1) && isNonEscapingLocalObject(o2))
				return AliasResult.NO_ALIAS;
			if (isEscapeSource(o2) && isNonEscapingLocalObject(o1))
				return AliasResult.NO_ALIAS;
		}

		// If the size of one access is larger than the entire object on the other
		// side, then we know such behavior is undefined and can assume no alias.
		if (targetData != null){
			if ((V1Size != UnknownSize && isObjectSmallerThan(o2, V1Size, targetData)) ||
					(V2Size != UnknownSize && isObjectSmallerThan(o1, V2Size, targetData)))
				return AliasResult.NO_ALIAS;
		}

		// Check the cache before climbing up use-def chains. This also terminates
		// otherwise infinitely recursive queries.
		//TODO Implement once we have MD nodes implemented
		//		LocPair Locs(Location(V1, V1Size, V1TBAAInfo),
		//				Location(V2, V2Size, V2TBAAInfo));
		//		if (V1 > V2)
		//			std::swap(Locs.first, Locs.second);
		//		std::pair<AliasCacheTy::iterator, bool> Pair =
		//			AliasCache.insert(std::make_pair(Locs, MayAlias));
		//		if (!Pair.second)
		//			return Pair.first->second;

		// FIXME: This isn't aggressively handling alias(GEP, PHI) for example: if the
		// GEP can't simplify, we don't even look at the PHI cases.
		Value tempVal = null;
		long tempSize = 0L;
		Value tempUnderlyingObject = null;
		if (!Instruction.isValueInstruction(v1, InstructionID.GET_ELEMENT_PTR) && 
				Instruction.isValueInstruction(v2, InstructionID.GET_ELEMENT_PTR)) {
			tempVal = v1; v1 = v2; v2 = tempVal;
			tempSize = V1Size; V1Size = V2Size; V2Size = tempSize;
			tempUnderlyingObject = o1; o1 = o2; o2 = tempUnderlyingObject;
		}

		if (Instruction.isValueInstruction(v1, InstructionID.GET_ELEMENT_PTR)) {
			GetElementPtrInst gepV1 = (GetElementPtrInst) v1;
			AliasResult Result = aliasGEP(gepV1, V1Size, v2, V2Size, o1, o2);
			if (Result != AliasResult.MAY_ALIAS){
				//TODO Implement cache
				//return AliasCache[Locs] = Result;
				return Result;
			}
		}

		if (Instruction.isValueInstruction(v1, InstructionID.PHI_NODE_INST) 
				&& !Instruction.isValueInstruction(v1, InstructionID.PHI_NODE_INST)) {
			tempVal = v1; v1 = v2; v2 = tempVal;
			tempSize = V1Size; V1Size = V2Size; V2Size = tempSize;
		}

		if (Instruction.isValueInstruction(v1, InstructionID.PHI_NODE_INST)) {
			try{
				PhiNode phiNode = (PhiNode)v1;
				AliasResult Result = aliasPHI(phiNode, V1Size, v2, V2Size);
				//TODO Implement cache
				//return AliasCache[Locs] = Result;
				return Result;
			}
			catch(Exception e){
				// TODO Log here
				System.exit(-1);
			}
		}

		if (Instruction.isValueInstruction(v2, InstructionID.SELECT_INST) && 
				!Instruction.isValueInstruction(v1, InstructionID.SELECT_INST)) {
			tempVal = v1; v1 = v2; v2 = tempVal;
			tempSize = V1Size; V1Size = V2Size; V2Size = tempSize;
		}

		if (Instruction.isValueInstruction(v1, InstructionID.SELECT_INST)) {
			SelectInst S1 = (SelectInst) v1;
			AliasResult Result = aliasSelect(S1, V1Size, 
					v2, V2Size);
			if (Result != AliasResult.MAY_ALIAS){
				//TODO Implement cache
				//return AliasCache[Locs] = Result;
			}
		}

		// If both pointers are pointing into the same object and one of them
		// accesses is accessing the entire object, then the accesses must
		// overlap in some way.
		if (targetData != null && o1 == o2){
			if ((V1Size != UnknownSize && isObjectSize(o1, V1Size, targetData)) ||
					(V2Size != UnknownSize && isObjectSize(o2, V2Size, targetData))){
				// TODO Implement alias cache
				//return AliasCache[Locs] = PartialAlias;
				return AliasResult.PARTIAL_ALIAS;
			}
		}

		// Try the next one in the chain if there is one
		AliasResult Result =
				getPreviousAliasAnalysis().alias(new StorageLocation(v1, V1Size),
						new StorageLocation(v2, V2Size));

		// TODO Implement alias cache
		//return AliasCache[Locs] = Result;

		return Result;
	}

	protected boolean isValueEqualInPotentialCycles(Value v1, Value v2) {
		if(v1 != v2) {
			return false;
		}

		if(v1.getValueTypeID() != ValueTypeID.INSTRUCTION) {
			return true;
		}

		if (visitedPhiBBs.isEmpty()) {
			return true;
		}


		if (visitedPhiBBs.size() > MAX_NUMP_PHI_BBs_VALUE_REACHABILITY_CHECK) {
			return false;
		}

		// Make sure that the visited phis cannot reach the Value. This ensures that
		// the Values cannot come from different iterations of a potential cycle the
		// phi nodes could be involved in.
		for (BasicBlock bb : visitedPhiBBs) {
			// TODO Implement this.
			/*if (isPotentiallyReachable(&P->front(), Inst, nullptr, DT, LI)) {
				return false;
			}*/
		}

		return true;

	}

	// aliasPHI - Provide a bunch of ad-hoc rules to disambiguate a PHI instruction
	// against another.
	private AliasResult aliasPHI(final PhiNode phiNode, long PNSize,
			// final MDNode *PNTBAAInfo,
			final Value V2, long V2Size
			// ,const MDNode *V2TBAAInfo
			) throws Exception {
		// If the values are PHIs in the same block, we can do a more precise
		// as well as efficient check: just check for aliases between the values
		// on corresponding edges.
		if (Instruction.isValueInstruction(V2, InstructionID.PHI_NODE_INST)){
			PhiNode PN2 = (PhiNode) V2;
			if (PN2.getParent() == phiNode.getParent()) {

				AliasResult Alias =
						aliasCheck(phiNode.getIncomingValue(0), PNSize, null,
								PN2.getIncomingValueForBlock(phiNode.getIncomingBlock(0)),
								V2Size, null);
				if (Alias == AliasResult.MAY_ALIAS)
					return Alias;

				for (int i = 1, e = phiNode.getNumIncomingValues(); i != e; i++) {
					AliasResult ThisAlias =
							aliasCheck(phiNode.getIncomingValue(i), PNSize, null,
									PN2.getIncomingValueForBlock(phiNode.getIncomingBlock(i)),
									V2Size, null);

					Alias = MergeAliasResults(ThisAlias, Alias);
					if (Alias == AliasResult.MAY_ALIAS)
						return Alias;
				}

				return Alias;
			}
		}

		Set<Value> UniqueSrc = new HashSet<Value>();
		List<Value> V1Srcs = new ArrayList<Value>();
		for (int i = 0, e = phiNode.getNumIncomingValues(); i != e; i++) {
			Value PV1 = phiNode.getIncomingValue(i);
			if (Instruction.isValueInstruction(PV1, InstructionID.PHI_NODE_INST))
				// If any of the source itself is a PHI, return MayAlias conservatively
				// to avoid compile time explosion. The worst possible case is if both
				// sides are PHI nodes. In which case, this is O(m x n) time where 'm'
				// and 'n' are the number of PHI sources.
				return AliasResult.MAY_ALIAS;

			if (UniqueSrc.add(PV1))
				V1Srcs.add(PV1);
		}

		AliasResult Alias = aliasCheck(V2, V2Size, null,
				V1Srcs.get(0), PNSize, null);
		// Early exit if the check of the first PHI source against V2 is MayAlias.
		// Other results are not possible.
		if (Alias == AliasResult.MAY_ALIAS)
			return Alias;

		// If all sources of the PHI node NoAlias or MustAlias V2, then returns
		// NoAlias / MustAlias. Otherwise, returns MayAlias.
		for (int i = 1, e = V1Srcs.size(); i != e; i++) {
			Value V = V1Srcs.get(i);

			AliasResult ThisAlias = aliasCheck(V2, V2Size, null,
					V, PNSize, null);
			Alias = MergeAliasResults(ThisAlias, Alias);
			if (Alias == AliasResult.MAY_ALIAS)
				break;
		}

		return Alias;
	}

	/// aliasSelect - Provide a bunch of ad-hoc rules to disambiguate a Select
	/// instruction against another.
	private AliasResult aliasSelect(final SelectInst SI, long SISize,
			final Value V2, long V2Size) {
		// If the values are Selects with the same condition, we can do a more precise
		// check: just check for aliases between the values on corresponding arms.
		if (Instruction.isValueInstruction(V2, InstructionID.SELECT_INST)){
			SelectInst SI2 = (SelectInst) V2;
			if (SI.getCondition() == SI2.getCondition()) {
				AliasResult Alias =
						aliasCheck(SI.getTrueValue(), SISize, null,
								SI2.getTrueValue(), V2Size, null);

				if (Alias == AliasResult.MAY_ALIAS)
					return AliasResult.MAY_ALIAS;

				AliasResult ThisAlias =
						aliasCheck(SI.getFalseValue(), SISize, null,
								SI2.getFalseValue(), V2Size, null);

				return MergeAliasResults(ThisAlias, Alias);
			}
		}

		// If both arms of the Select node NoAlias or MustAlias V2, then returns
		// NoAlias / MustAlias. Otherwise, returns MayAlias.
		AliasResult Alias =
				aliasCheck(V2, V2Size, null, SI.getTrueValue(), SISize, null);
		if (Alias == AliasResult.MAY_ALIAS)
			return AliasResult.MAY_ALIAS;

		AliasResult ThisAlias =
				aliasCheck(V2, V2Size, null, SI.getFalseValue(), SISize, null);

		return MergeAliasResults(ThisAlias, Alias);
	}


	private boolean isObjectAllocaInst(Value value) {
		ValueTypeID valueTypeID = value.getValueTypeID();
		if(valueTypeID != ValueTypeID.INSTRUCTION)
			return false;
		Instruction instr = (Instruction) value;
		if(instr.getInstructionID() != InstructionID.ALLOCA_INST){
			return false;
		}

		return true;
	}

	@Override
	public boolean pointsToConstantMemory(StorageLocation Loc, boolean OrLocal) {
		if(!Visited.isEmpty()){
			// TODO Log here
			//("Visited must be cleared after use!");
			System.exit(-1);
		}

		int MaxLookup = 8;

		List<Value> Worklist = new ArrayList<Value>();
		Worklist.add(Loc.getStartOfLocationPtr());
		do {
			Value V = null;
			try{
				V = getUnderlyingObject(Worklist.get(0), targetData, 6);
			}
			catch(Exception e){
				// TODO Log here
				e.printStackTrace();
				System.exit(-1);
			}
			if (!Visited.add(V)) {
				Visited.clear();

				return pointsToConstantMemory(Loc, OrLocal);
			}

			// An alloca instruction defines local memory.
			if (OrLocal && Instruction.isValueInstruction(V, InstructionID.ALLOCA_INST))
				continue;

			// A global constant counts as local memory for our purposes.
			if (V.getValueTypeID() == ValueTypeID.GLOBAL_VALUE) {
				GlobalValue GV = (GlobalValue) V;
				// Note: this doesn't require GV to be "ODR" because it isn't legal for a
				// global to be marked constant in some modules and non-constant in
				// others.  GV may even be a declaration, not a definition.
				Visited.clear();
				return pointsToConstantMemory(Loc, OrLocal);
			}

			// If both select values point to local memory, then so does the select.
			if (Instruction.isValueInstruction(V, InstructionID.SELECT_INST)) {
				SelectInst SI = (SelectInst) V;
				Worklist.add(SI.getTrueValue());
				Worklist.add(SI.getFalseValue());
				continue;
			}

			// If all values incoming to a phi node point to local memory, then so does
			// the phi.
			try{
				if (Instruction.isValueInstruction(V, InstructionID.PHI_NODE_INST)) {
					PhiNode PN = (PhiNode)V;
					// Don't bother inspecting phi nodes with many operands.
					if (PN.getNumIncomingValues() > MaxLookup) {
						Visited.clear();
						return pointsToConstantMemory(Loc, OrLocal);
					}

					for (int i = 0, e = PN.getNumIncomingValues(); i != e; i++)
						Worklist.add(PN.getIncomingValue(i));
					continue;
				}
			}
			catch(Exception e){
				// TODO Log here
				System.exit(-1);
			}

			// Otherwise be conservative.
			Visited.clear();
			return pointsToConstantMemory(Loc, OrLocal);

		} while (!Worklist.isEmpty() && --MaxLookup > 0);

		Visited.clear();

		return Worklist.isEmpty();
	}

	@Override
	public ModRefBehavior getModRefBehavior(Function F) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteValue(Value V) {
		// TODO Auto-generated method stub

	}

	@Override
	public void copyValue(Value From, Value To) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addEscapingUse(User U) {
		// TODO Auto-generated method stub

	}

	private static Value GetLinearExpression(Value index, APInt indexScale,
			APInt indexOffset, ExtensionKind extension, TargetData targetData,
			int i) {
		// TODO Auto-generated method stub
		return null;
	}

	/// isObjectSmallerThan - Return true if we can prove that the object specified
	/// by V is smaller than Size.
	private static boolean isObjectSmallerThan(final Value value, long Size,
			final TargetData targetData) {
		// This function needs to use the aligned object size because we allow
		// reads a bit past the end given sufficient alignment.
		long ObjectSize = getObjectSize(value, targetData, /*RoundToAlign*/true);

		return ObjectSize != UnknownSize && ObjectSize < Size;
	}

	private static AliasResult MergeAliasResults(AliasResult A, AliasResult B) {
		// If the results agree, take it.
		if (A == B)
			return A;
		// A mix of PartialAlias and MustAlias is PartialAlias.
		if ((A == AliasResult.PARTIAL_ALIAS && B == AliasResult.MUST_ALIAS) ||
				(B == AliasResult.PARTIAL_ALIAS && A == AliasResult.MUST_ALIAS))
			return AliasResult.PARTIAL_ALIAS;

		// Otherwise, we don't know anything.
		return AliasResult.MAY_ALIAS;
	}

	/// isObjectSize - Return true if we can prove that the object specified
	/// by V has size Size.
	private static boolean isObjectSize(final Value V, long Size,
			final TargetData targetData) {
		long ObjectSize = getObjectSize(V, targetData, false);
		return ObjectSize != UnknownSize && ObjectSize == Size;
	}

	/// getObjectSize - Return the size of the object specified by V, or
	/// UnknownSize if unknown.
	private static long getObjectSize(final Value value, final TargetData targetData,
			boolean RoundToAlign) {
		Type accessType = null;
		long Align = 0L;

		ValueTypeID valueTypeID = value.getValueTypeID();

		if (valueTypeID == ValueTypeID.GLOBAL_VALUE) {
			GlobalValue globalValue = (GlobalValue) value;
			if (globalValue.hasDefinitiveInitializer()){ 
				return UnknownSize;
			}

			accessType = globalValue.getType().getContainedType();
			Align = globalValue.getAlignment();
		} 
		else if (valueTypeID == ValueTypeID.ARGUMENT) {
			Argument arg = (Argument) value;
			// TODO : review hasByValueAttr
			if (arg.hasByValAttr()) {
				PointerType ptrType = (PointerType) arg.getType();
				accessType = ptrType.getContainedType();
				try {
					Align = arg.getParamAlignment();
				} catch (InstructionDetailAccessException e) {
					// TODO Log here
					e.printStackTrace();
					System.exit(-1);
				} catch (TypeCreationException e) {
					// TODO Log here
					e.printStackTrace();
					System.exit(-1);
				}
			} 
			else {
				return UnknownSize;
			}
		} 
		else if(valueTypeID == ValueTypeID.INSTRUCTION){
			Instruction instr = (Instruction)value;
			InstructionID instrID = instr.getInstructionID();
			if (instrID == InstructionID.ALLOCA_INST) {
				AllocaInst allocaInst = (AllocaInst) instr;
				if (!allocaInst.isVariableLengthArrayAllocation())
					accessType = allocaInst.getType().getScalarType();
				else
					return UnknownSize;
				Align = allocaInst.getAlignment();
			} 
			else if (instrID == InstructionID.CALL_INST) {
				CallInst callInst = (CallInst) instr;
				// TODO Implement this when we have memory builtins.
				/*if (!RoundToAlign && !isArrayMalloc(value, targetData))
				// The size is the argument to the malloc call.
				Value argVal = callInst.getArgOperand(0);

				if (const ConstantInt* C = dyn_cast<ConstantInt>(CI->getArgOperand(0)))
					return C->getZExtValue();
				 */
				return UnknownSize;
			}
		}
		else {
			return UnknownSize;
		}

		if (accessType != null && !accessType.isSized())
			return UnknownSize;

		long Size = 0L;

		try{
			Size = targetData.getTypeAllocSize(accessType);
			// If there is an explicitly specified alignment, and we need to
			// take alignment into account, round up the size. (If the alignment
			// is implicit, getTypeAllocSize is sufficient.)
			if (RoundToAlign && Align > 0){
				Size = roundUpToAlignment(Size, Align);
			}
		}
		catch(Exception e){
			//TODO Log here
			e.printStackTrace();
			System.exit(-1);
		}
		return Size;
	}

	/// RoundUpToAlignment - Returns the next integer (mod 2**64) that is
	/// greater than or equal to \arg Value and is a multiple of \arg
	/// Align. Align must be non-zero.
	///
	/// Examples:
	/// roundUpToAlignment(5, 8) = 8
	/// roundUpToAlignment(17, 8) = 24
	/// roundUpToAlignment(~0LL, 8) = 0
	private static long roundUpToAlignment(long Value, long Align) {
		return ((Value + Align - 1) / Align) * Align;
	}
}
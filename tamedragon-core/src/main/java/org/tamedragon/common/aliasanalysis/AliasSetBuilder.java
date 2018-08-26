package org.tamedragon.common.aliasanalysis;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.tamedragon.common.aliasanalysis.AliasSet.AccessType;
import org.tamedragon.common.llvmir.instructions.CFG;
import org.tamedragon.common.llvmir.instructions.Instruction;
import org.tamedragon.common.llvmir.instructions.LoadInst;
import org.tamedragon.common.llvmir.instructions.StoreInst;
import org.tamedragon.common.llvmir.instructions.Instruction.AtomicOrdering;
import org.tamedragon.common.llvmir.instructions.Instruction.InstructionID;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.Value;

public class AliasSetBuilder {

	private List<AliasSet> aliasSetList;

	private AliasAnalysis aliasAnalysis;

	private Function function;

	public AliasSetBuilder(Function function, AliasAnalysis aliasAnalysis){
		aliasSetList = new ArrayList<AliasSet>();
		this.function = function;
		this.aliasAnalysis = aliasAnalysis;
	}

	public void build(){

		Iterator<BasicBlock> basicBlockIterator = function.basicBlocksIterator();
		while(basicBlockIterator.hasNext()) {
			add(basicBlockIterator.next());
		}
	}

	private void add(BasicBlock basicBlock){

		Iterator<Instruction> instructions = basicBlock.getInstructions();
		while(instructions.hasNext()){
			add(instructions.next());
		}
	}

	private boolean add(Instruction instruction) {
		InstructionID instrID = instruction.getInstructionID();
		if(instrID == InstructionID.LOAD_INST){
			LoadInst loadInst = (LoadInst) instruction;
			if (loadInst.getOrder().ordinal() > AtomicOrdering.Monotonic.ordinal()) 
				return addUnknown(instruction);

			AliasSet.AccessType accessType = AliasSet.AccessType.Refs;

			if (!loadInst.isUnordered()) 
				accessType = AliasSet.AccessType.ModRef;

			boolean isNewPtr = false;
			AliasSet aliasSets = addPointer(loadInst.getOperand(0),
					aliasAnalysis.getTypeStoreSize(loadInst.getType()),
					accessType, isNewPtr);
			if (loadInst.isVolatile()) 
				aliasSets.setVolatile(true);
			else
				aliasSets.setVolatile(false);

			return isNewPtr;
		}
		else if(instrID == InstructionID.STORE_INST){
			StoreInst storeInst = (StoreInst) instruction;
			if (storeInst.getAtomicOrdering().ordinal() > AtomicOrdering.Monotonic.ordinal()) 
				return addUnknown(storeInst);

			AliasSet.AccessType accessType = AliasSet.AccessType.Mods;
			if (!storeInst.isUnordered()) 
				accessType = AliasSet.AccessType.ModRef;

			boolean isNewPtr = false;
			Value valueToStore = storeInst.getOperand(0);
			AliasSet aliasSets = addPointer(storeInst.getOperand(1),
					aliasAnalysis.getTypeStoreSize(valueToStore.getType()),
					accessType, isNewPtr);

			if (storeInst.isVolatile()) 
				aliasSets.setVolatile(true);
			else
				aliasSets.setVolatile(false);

			return isNewPtr;
		}
		else if(instrID == InstructionID.VA_ARG_INST){
			// TODO Implement after VariableArgInst is implemented
			return false;
		}

		return addUnknown(instruction);
	}

	private boolean addUnknown(Instruction instruction) {

		if (!instruction.mayReadOrWriteMemory())
			return true; // doesn't alias anything

		AliasSet as = findAliasSetForUnknownInst(instruction);
		if (as != null) {
			as.addUnknownInst(instruction, aliasAnalysis);
			return false;
		}

		as = new AliasSet();
		as.addUnknownInst(instruction, aliasAnalysis);
		return true;
	}

	private AliasSet findAliasSetForUnknownInst(Instruction instruction) {
		// TODO Auto-generated method stub
		return null;
	}

	private AliasSet addPointer(Value pointer, long size,
			AccessType accessType, boolean isNewSet) {
		isNewSet = false;
		AliasSet aliasSet = getAliasSetForPointer(pointer, size, isNewSet);

		// aliasSet.setAccessType(aliasSet.getAccessType().ordinal() | accessType.ordinal());
		return aliasSet;
	}

	private AliasSet getAliasSetForPointer(Value pointer, long size,
			boolean isNewSet) {
		AliasSet entry = getEntryFor(pointer);

		// Check to see if the pointer is already known.
		if (entry != null) {
			entry.updateSize(size);
			return entry;
		}

		/*// This pointer is not known yet, find a set for it (could be new)
		AliasSet aliasSetFound = findAliasSetForPointer(pointer, size);
		if (aliasSetFound != null) {
			// Add it to the alias set it aliases.
			AS->addPointer(*this, Entry, Size, TBAAInfo);
			return *AS;
		}

		if (New) *New = true;
		// Otherwise create a new alias set to hold the loaded pointer.
		AliasSets.push_back(new AliasSet());
		AliasSets.back().addPointer(*this, Entry, Size, TBAAInfo);
		return AliasSets.back();
	*/
		return null;
	}

	
	
	private AliasSet getEntryFor(Value pointer){
		for(AliasSet aliasSet : aliasSetList){
			if(aliasSet.contains(pointer)){
				return aliasSet;
			}
		}

		return null;

	}

}
package org.tamedragon.compilers.mips.translate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.tamedragon.assemblyabstractions.ActivationFrame;
import org.tamedragon.assemblyabstractions.ScalarStackElement;
import org.tamedragon.assemblyabstractions.StackElement;
import org.tamedragon.assemblyabstractions.exceptions.InvalidStackElementAccessException;
import org.tamedragon.common.Label;
import org.tamedragon.common.llvmir.instructions.AssemblyInstruction;
import org.tamedragon.common.llvmir.types.AbstractType;


public class MipsActivationFrameImpl implements ActivationFrame {

	public static final int WORDSIZE = 4;

	private Label label;
	private String sourceName;

	private boolean isLeafProcedure;

	private Vector<StackElement> stackElements;

	// This is a map of call made by this function (the AssemblyInstruction) against a list of 
	// types of arguments that need to be passed on the stack.
	private HashMap<AssemblyInstruction, List<StackElement>>  argTypesOnStackForInvocations;

	// Map of register names against the type of data that are used in the current function
	private HashMap<String, AbstractType> registersUsedVsDataType;

	// Flag to indicate whether or not to align data
	private boolean alignData;

	// Arguments that are on the stack that this function is accepting.
	private List<AbstractType> argsOnStackForAcceptance;
	
	public MipsActivationFrameImpl(){
		stackElements = new Vector<StackElement>();
		argTypesOnStackForInvocations = new HashMap<AssemblyInstruction, List<StackElement>>();
		registersUsedVsDataType = new HashMap<String, AbstractType>();
		argsOnStackForAcceptance = new ArrayList<AbstractType>();
	}

	public MipsActivationFrameImpl(Label nm){
		stackElements = new Vector<StackElement>();
		label = nm;
	}

	public Label getLabel(){
		return label;
	}

	public void setLabel(Label nm){
		label = nm;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getReturnValueRegister(){
		return "v0";
	}


	public Vector<StackElement> getStackElements() {
		return stackElements;
	}

	@Override
	public void addArgumentInStackForInvocation(AssemblyInstruction callInstruction, AbstractType typeOfArgumentOnStack){

		List<StackElement> typesOfArgumentsOnStackForCall = argTypesOnStackForInvocations.get(callInstruction);
		if(typesOfArgumentsOnStackForCall == null){
			typesOfArgumentsOnStackForCall = new ArrayList<StackElement>();
		}

		ScalarStackElement element = new ScalarStackElement();
		element.setAbstractType(typeOfArgumentOnStack);
		element.setType(StackElement.ARGUMENT_IN_STACK);
		typesOfArgumentsOnStackForCall.add(element);

		argTypesOnStackForInvocations.put(callInstruction, typesOfArgumentsOnStackForCall);
	}

	@Override
	public void addArgumentInStackForAccepting(AbstractType abstractType){
		argsOnStackForAcceptance.add(abstractType);
	}
	
	@Override
	public void setIsLeafProcedure(boolean isLeafProcedure){
		this.isLeafProcedure = isLeafProcedure;
	}

	@Override
	public void setAlignData(boolean alignData){
		this.alignData = alignData;
	}

	@Override
	public Vector<StackElement> getElementsOnStack(){
		return stackElements;
	}

	@Override
	public void addStackElement(StackElement stackElement) {
		stackElements.add(stackElement);
	}

	@Override
	public int getOffsetForNextElementFromStackPointerForAcceptingArgument(int savedArgSize){
		int currentOffset = AbstractType.WORD_SIZE;

		if(!alignData){
			// No data alignment required; return the total size
			for(AbstractType abstractType : argsOnStackForAcceptance){
				currentOffset += abstractType.getSize();
			}

			return currentOffset;
		}

		// Need data alignment
		for(AbstractType abstractType : argsOnStackForAcceptance){
			int sizeOfElement = abstractType.getSize();
			int nextOffset = offSetFromStackPointerForNewElement(currentOffset, sizeOfElement);
			currentOffset = nextOffset + sizeOfElement;
		}

		// Determine offset for the next element
		currentOffset = offSetFromStackPointerForNewElement(currentOffset, savedArgSize);
		
		return currentOffset;
	}
	
	
	@Override
	public int getOffsetForNextElementFromFramePointer(int sizeOfElement){

		int currentOffSet = getSizeForAllElementsAtOffsetsFromFP();   // This is the current offset from the frame pointer
		if(!alignData){
			return currentOffSet + sizeOfElement;
		}

		return offSetFromFramePointerForNewElement(currentOffSet, sizeOfElement);
	}

	/**
	 * This function returns the size of all elements that are stored at offsets from the frame pointer.
	 * This will account for the spilled temporaries, escaped variables and local variables (arrays) 
	 * at this current instance.
	 * 
	 * It DOES not take into account the saved registers ($s0, $t0, etc.) nor any arguments to the callees.
	 * 
	 */

	public int getSizeForAllElementsAtOffsetsFromFP(){
		int currentOffset = 0;

		if(!alignData){
			// No data alignment required; return the total size
			for(StackElement stackElement : stackElements){
				currentOffset += stackElement.getSize();
			}

			return currentOffset;
		}

		// Need data alignment
		for(StackElement stackElement : stackElements){
			int sizeOfElement = stackElement.getSize();
			currentOffset = offSetFromFramePointerForNewElement(currentOffset, sizeOfElement);
		}

		return currentOffset;
	}

	/**
	 * Returns a position for a new element to be stored on the stack with an offset BELOW the frame pointer. 
	 * If the data needs to be aligned, we take into account both the current offset AND the size of the new
	 * element; else we just return the current offset PLUS the size of the element. 
	 */

	private int offSetFromFramePointerForNewElement(int currentOffSet, int sizeOfElement){

		if(!alignData)
			return currentOffSet + sizeOfElement;

		// Data needs alignment
		
		/*if(currentOffSet == 0)
			return currentOffSet + sizeOfElement;  // Anything can be aligned at 0
		
		if(currentOffSet <= sizeOfElement){
			return currentOffSet + sizeOfElement;
		}
		else{
			
		}
		*/
		
		
		int tempPosition = currentOffSet + sizeOfElement;
		int remainder = tempPosition % sizeOfElement;
		if(remainder == 0)
			currentOffSet = tempPosition;
		else{
			int paddingRequired = sizeOfElement - remainder;
			currentOffSet = tempPosition + paddingRequired;
		}
		

		return currentOffSet;
	}

	@Override
	public int getOffsetForNextArgumentForInvocationOnStackPointer(AssemblyInstruction callInstruction, int sizeOfElement){

		int startOffSetForArgs = getStartOffsetForStoringArguments();
		int currentOffSet = getSizeOfOutgoingArgs(callInstruction, startOffSetForArgs);
		
		if(currentOffSet == 0)
			currentOffSet = startOffSetForArgs;

		if(!alignData){
			return currentOffSet;
		}

		// Needs alignment
		return offSetFromStackPointerForNewElement(currentOffSet, sizeOfElement);
	}

	public int getSizeOfOutgoingArgs(AssemblyInstruction callInstruction,
			int startingOffsetForArgs){

		List<StackElement> argsOnStackForInvocation = argTypesOnStackForInvocations.get(callInstruction);

		if(argsOnStackForInvocation == null)   // No args on stack for this invocation.
			return 0;

		if(alignData){
			// Need to align data
			int currentOffSet = startingOffsetForArgs;
			for(StackElement stackElement : argsOnStackForInvocation){
				int sizeOfElement = stackElement.getSize();
				currentOffSet = offSetFromStackPointerForNewElement(currentOffSet, sizeOfElement);
				currentOffSet += sizeOfElement;
			}
		}

		// No need to align
		int sizeOfArgsForInvocation = startingOffsetForArgs;
		for(StackElement stackElement : argsOnStackForInvocation){
			// No need for data alignment; just calculate the size
			sizeOfArgsForInvocation += stackElement.getSize();
		}

		return sizeOfArgsForInvocation;
	}

	/**
	 * Returns a position for a new element to be stored on the stack with an offset AFTER the stack pointer. 
	 * If the data needs to be aligned, we take into account both the current offset AND the size of the new
	 * element; else we just return the current offset. 
	 */

	private int offSetFromStackPointerForNewElement(int currentOffSet, int sizeOfElement){

		if(!alignData)
			return currentOffSet;

		// Data needs alignment
		int newOffset = currentOffSet + sizeOfElement;
		int remainder = newOffset % sizeOfElement;
		if(remainder == 0){    // Aligns neatly
			return currentOffSet;   
		}
		else{                  // Requires padding
			int paddingRequired = sizeOfElement - remainder;
			return currentOffSet + paddingRequired;
		}
	}

	@Override
	public int getOffset(short storageItemType){

		int offSetFromSP = 0;

		if(storageItemType == ActivationFrame.OFFSET_FOR_STORING_ARGUMENTS){
			offSetFromSP = getStartOffsetForStoringArguments();
		}
		else if(storageItemType == ActivationFrame.OFFSET_FOR_CALLEE_SAVED_REGISTERS){
			return getStartOffsetForStoringCalleeSavedRegisters();
		}
		else if(storageItemType == ActivationFrame.OFFSET_FOR_CALLER_SAVED_REGISTERS){
			return getStartOffsetForStoringCallerSavedRegisters();
		}
		else if(storageItemType == ActivationFrame.OFFSET_FOR_STORING_RETURN_ADDRESS){
			return getStartOffsetForStoringReturnAddress();
		}
		else if(storageItemType == ActivationFrame.OFFSET_FOR_STORING_FRAME_POINTER){
			return getStartOffsetForStoringFP();
		}

		return offSetFromSP;
	}

	@Override
	public int getStackSize() {

		// Get the end of the elements stored with offsets from the stack pointer
		int offsetForStoringFP = getStartOffsetForStoringFP();
		int endOfFPStore = offsetForStoringFP + WORDSIZE;

		// Get the size of all elements stored with offsets from the frame pointer
		int sizeOfElementsAtOffsetsFromFP = getSizeForAllElementsAtOffsetsFromFP();
		if(sizeOfElementsAtOffsetsFromFP == 0)
			return endOfFPStore;

		// Get the last element stored on the list of elements at offsets from the FP; find the 
		// right offset for it at an index from the stack pointer
		StackElement lastElement = stackElements.lastElement();
		if(!alignData){
			return endOfFPStore + sizeOfElementsAtOffsetsFromFP;
		}
		else{
			int startIndexOfLastElementOfFP = offSetFromStackPointerForNewElement(endOfFPStore, lastElement.getSize());
			int stackSize = startIndexOfLastElementOfFP + sizeOfElementsAtOffsetsFromFP;
			if(stackSize % AbstractType.DOUBLE_WORD != 0){
				// Align against a double-word boundary
				stackSize = offSetFromStackPointerForNewElement(stackSize, AbstractType.DOUBLE_WORD);
				
			}
			return stackSize;
		}
	}

	@Override
	public int getOffsetForNextElementFromStackPointer(short storageItemType,
			int sizeOfElement) {

		// Get the required offset based on the storage item type
		int requiredOffSet = 0;
		int startOffsetForStorageType = 0;
		if(storageItemType == ActivationFrame.OFFSET_FOR_STORING_ARGUMENTS){
			startOffsetForStorageType = getStartOffsetForStoringArguments();
			requiredOffSet = getSizeOfOutgoingArgs(startOffsetForStorageType);
		}
		else if(storageItemType == ActivationFrame.OFFSET_FOR_CALLEE_SAVED_REGISTERS){
			startOffsetForStorageType = getStartOffsetForStoringCalleeSavedRegisters();
			requiredOffSet = getSizeOfStoredRegisters(CALLEE_SAVED_REGISTERS_FLAG, startOffsetForStorageType);
		}
		else if(storageItemType == ActivationFrame.OFFSET_FOR_CALLER_SAVED_REGISTERS){
			startOffsetForStorageType = getStartOffsetForStoringCallerSavedRegisters();
			requiredOffSet = getSizeOfStoredRegisters(CALLER_SAVED_REGISTERS_FLAG, startOffsetForStorageType);
		}
		else if(storageItemType == ActivationFrame.OFFSET_FOR_STORING_RETURN_ADDRESS){
			startOffsetForStorageType = getStartOffsetForStoringReturnAddress();
			requiredOffSet = offSetFromStackPointerForNewElement(startOffsetForStorageType, WORDSIZE);
		}
		else if(storageItemType == ActivationFrame.OFFSET_FOR_STORING_FRAME_POINTER){
			startOffsetForStorageType = getStartOffsetForStoringFP();
			requiredOffSet = offSetFromStackPointerForNewElement(startOffsetForStorageType, WORDSIZE);
		}

		// From this offset, calculate the current size
		return offSetFromStackPointerForNewElement(requiredOffSet, sizeOfElement);

	}

	@Override
	public int getOffsetForSavedRegisterstFromStackPointer(short storageItemType, int index) 
	throws InvalidStackElementAccessException {

		int startOffsetForStorageType = 0;
		List<String> saveRegisters = null;

		if(storageItemType == CALLER_SAVED_REGISTERS_FLAG){
			startOffsetForStorageType = getStartOffsetForStoringCallerSavedRegisters();
			if(isLeafProcedure){
				// If this is a leaf procedure, there are no caller saved registers
				throw new InvalidStackElementAccessException();
			}

			// Not a leaf procedure, lets see how much we need to save
			saveRegisters = getCallerSavedRegistersUsedInFunction();
		}
		else if(storageItemType == CALLEE_SAVED_REGISTERS_FLAG){
			saveRegisters = getCalleeSavedRegistersUsedInFunction();
			startOffsetForStorageType = getStartOffsetForStoringCalleeSavedRegisters();
		}
		else{
			// Invalid storage type on stack.
			throw new InvalidStackElementAccessException();
		}

		if(saveRegisters.size() == 0){
			// If there are no registers of this type, it is an error
			throw new InvalidStackElementAccessException();
		}

		if(index < 0 || index >= saveRegisters.size()){
			// If the index that is passed is not valid, it is an error
			throw new InvalidStackElementAccessException();
		}

		if(index == 0){
			// If the index is 0, it must be the first element
			return startOffsetForStorageType;
		}

		int requiredOffSet = startOffsetForStorageType;

		int registerCount = 0;
		for(String register : saveRegisters){
			AbstractType registerType = getRegisterType(register);
			int savedRegisterSize = registerType.getSize();
			if(savedRegisterSize < AbstractType.WORD_SIZE)
				savedRegisterSize = AbstractType.WORD_SIZE;

			if(alignData){
				requiredOffSet = offSetFromStackPointerForNewElement(requiredOffSet, savedRegisterSize);
				requiredOffSet += savedRegisterSize;
			}
			else{
				requiredOffSet += savedRegisterSize;
			}

			if(registerCount == index -1){
				// Found it; find its offset based on the required offset and the size
				String requiredRegister = saveRegisters.get(index);
				AbstractType typeOfRequiredRegister = getRegisterType(requiredRegister);
				int requiredRegisterSize = typeOfRequiredRegister.getSize();
				return offSetFromStackPointerForNewElement(requiredOffSet, requiredRegisterSize);
			}

			registerCount++;
		}
		
		return -1; // Invalid

	}

	private int getStartOffsetForStoringCalleeSavedRegisters(){
		// 1. Get offset for storing arguments 
		int offSetFromSP = getStartOffsetForStoringArguments();

		// 2. Get end point for storing arguments (= current offset + size of outgoing args)
		int endPointOfArgs = getSizeOfOutgoingArgs(offSetFromSP);   

		// 3. Get the start offset for storing Callee saved registers
		if(!alignData)
			return endPointOfArgs;

		return getStartOffsetForStoringSavedRegisters(CALLEE_SAVED_REGISTERS_FLAG, endPointOfArgs);

	}

	private int getStartOffsetForStoringCallerSavedRegisters(){

		// 1. Get offset for storing callee saved registers
		int startOffsetForCalleeSavedRegs = getStartOffsetForStoringCalleeSavedRegisters();

		// 2. Get the end point for storing callee saved registers
		int endPointOfCalleeSavedRegisterStore = getSizeOfStoredRegisters(CALLEE_SAVED_REGISTERS_FLAG, startOffsetForCalleeSavedRegs);

		// 3. Get the start offset for storing caller saved registers
		if(isLeafProcedure)
			return endPointOfCalleeSavedRegisterStore;   // no need to save any caller saved registers

		if(!alignData)
			return endPointOfCalleeSavedRegisterStore;

		return getStartOffsetForStoringSavedRegisters(CALLER_SAVED_REGISTERS_FLAG, endPointOfCalleeSavedRegisterStore);

	}

	private int getStartOffsetForStoringReturnAddress(){

		// 1. Get offset for storing callee saved registers
		int startOffsetForCallerSavedRegs = getStartOffsetForStoringCallerSavedRegisters();

		// 2. Get the end point for storing caller saved registers
		int endPointOfCallerSavedRegisterStore = getSizeOfStoredRegisters(CALLER_SAVED_REGISTERS_FLAG, startOffsetForCallerSavedRegs);

		if(!alignData)
			return endPointOfCallerSavedRegisterStore;

		// 3. Get the start offset for storing the return address
		return offSetFromStackPointerForNewElement(endPointOfCallerSavedRegisterStore, WORDSIZE);
	}

	private int getStartOffsetForStoringFP(){

		// 1. Get offset for storing callee saved registers
		int startOffsetForStoringReturnAddress = getStartOffsetForStoringReturnAddress();

		// 2. Get the start offset for storing the return address
		int startOffSetForReturnAddress = 
			offSetFromStackPointerForNewElement(startOffsetForStoringReturnAddress, WORDSIZE);

		return startOffSetForReturnAddress + WORDSIZE;

	}

	public int getSizeOfOutgoingArgs(int startOffset){

		// If there are no arguments, there is no increase of offset from the stack pointer
		if(argTypesOnStackForInvocations.size() == 0){
			return startOffset;
		}

		int maxSize = 0;

		int startOffSetForArgs = startOffset; 

		Set<AssemblyInstruction> invocations = argTypesOnStackForInvocations.keySet();
		Iterator<AssemblyInstruction> iterator = invocations.iterator();
		while(iterator.hasNext()){
			AssemblyInstruction invocation = iterator.next();
			int sizeOfArgsForInvocation = getSizeOfOutgoingArgs(invocation,
					startOffSetForArgs);

			// Reset the maximum size
			if(maxSize < sizeOfArgsForInvocation)
				maxSize = sizeOfArgsForInvocation;
		}
		return maxSize;
	}

	@Override
	public List<String> getCallerSavedRegistersUsedInFunction() {
		return getRegistersUsedInFunction(MipsProperties.getAllCallerSavedRegisters());
	}

	@Override
	public AbstractType getRegisterType(String registerName) {
		return registersUsedVsDataType.get(registerName);
	}

	@Override
	public List<String> getCalleeSavedRegistersUsedInFunction() {
		return getRegistersUsedInFunction(MipsProperties.getAllCalleeSavedRegisters());
	}

	@Override
	public void addRegistersUsed(String registerName, AbstractType abstractType){
		registersUsedVsDataType.put(registerName, abstractType);
	}

	private List<String> getRegistersUsedInFunction(Vector<String> registerPool){
		List<String> registersUsedInFunction = new ArrayList<String>();

		Set<String> registerNames = registersUsedVsDataType.keySet();

		Iterator<String> iterator = registerNames.iterator();
		while(iterator.hasNext()){
			String registerName = iterator.next();
			if(registerPool.contains(registerName))
				registersUsedInFunction.add(registerName);
		}

		Collections.sort(registersUsedInFunction);

		return registersUsedInFunction;
	}

	private int getSizeOfStoredRegisters(int saveType, int previousOffset){
		List<String> saveRegisters = null;

		if(saveType == CALLER_SAVED_REGISTERS_FLAG){
			if(isLeafProcedure) // No need to save any caller saved registers
				return previousOffset;

			// Not a leaf procedure, lets see how much we need to save
			saveRegisters = getCallerSavedRegistersUsedInFunction();
		}
		else
			saveRegisters = getCalleeSavedRegistersUsedInFunction();

		if(saveRegisters.size() == 0){
			return previousOffset;
		}

		int size = previousOffset;

		for(String register : saveRegisters){
			AbstractType registerType = getRegisterType(register);
			int savedRegisterSize = registerType.getSize();
			
			if(savedRegisterSize < AbstractType.WORD_SIZE)
				savedRegisterSize = AbstractType.WORD_SIZE; // Must be at least word size to save registers

			if(alignData){
				size = offSetFromStackPointerForNewElement(size, savedRegisterSize);
				size += savedRegisterSize;
			}
			else{
				size += savedRegisterSize;
			}
		}

		return size;
	}

	private int getStartOffsetForStoringArguments(){
		int startOffset = WORDSIZE;    // Minimum for storing the static link

		if(!alignData)
			return startOffset;   

		// Need to align data
		if(argTypesOnStackForInvocations.size() == 0){
			// No arguments to be passed, doesnt matter
			return startOffset;
		}

		// Iterate through first argument for all iterations; if any one of them is a long
		// integer or a double, return DOUBLE_WORD; else return WORD

		Set<AssemblyInstruction> invocations = argTypesOnStackForInvocations.keySet();
		Iterator<AssemblyInstruction> iterator = invocations.iterator();
		while(iterator.hasNext()){
			AssemblyInstruction callInstruction = iterator.next();
			List<StackElement> argElements = argTypesOnStackForInvocations.get(callInstruction);
			if(argElements == null || argElements.size() == 0)
				continue;

			StackElement argOnStack = argElements.get(0);
			int argSize = argOnStack.getSize();
			if(argSize > WORDSIZE){
				return argSize;
			}
		}

		return startOffset;
	}

	private int getStartOffsetForStoringSavedRegisters(int saveType, int previousOffset){

		List<String> savedRegisters = null;
		if(saveType == CALLEE_SAVED_REGISTERS_FLAG)
			savedRegisters = getCalleeSavedRegistersUsedInFunction();
		else
			savedRegisters = getCallerSavedRegistersUsedInFunction();

		if(savedRegisters == null || savedRegisters.size() == 0){
			return previousOffset;
		}

		String firstSavedRegister = savedRegisters.get(0);
		AbstractType registerType = getRegisterType(firstSavedRegister);
		return offSetFromStackPointerForNewElement(previousOffset, registerType.getSize());

	}
	
	public String toString(){
		String newLine = "\n";
		String description = "ACTIVATION FRAME:	" + newLine;
		
		description += "Start offset for outgoing args    = " + getStartOffsetForStoringArguments() + newLine;
		int startOffsetForCalleeSavedRegs = getStartOffsetForStoringCalleeSavedRegisters();
		description += "Start offset for callee saved regs = " + startOffsetForCalleeSavedRegs + newLine;
		List<String> saveRegisters = getCalleeSavedRegistersUsedInFunction();
		int size = startOffsetForCalleeSavedRegs;
		for(String register : saveRegisters){
			AbstractType registerType = getRegisterType(register);
			int savedRegisterSize = registerType.getSize();

			if(savedRegisterSize < AbstractType.WORD_SIZE)
				savedRegisterSize = AbstractType.WORD_SIZE;
			
			if(alignData){
				size = offSetFromStackPointerForNewElement(size, savedRegisterSize);
				description += "	" + register + "(size = " + savedRegisterSize + ") offset: " + size + newLine;
			}
			size += savedRegisterSize;
		}
		
		saveRegisters = getCallerSavedRegistersUsedInFunction();
		int startOffsetForCallerSavedRegs = getStartOffsetForStoringCallerSavedRegisters();
		description += "Start offset for caller saved regs = " + startOffsetForCallerSavedRegs + newLine;
		size = startOffsetForCallerSavedRegs;
		for(String register : saveRegisters){
			AbstractType registerType = getRegisterType(register);
			int savedRegisterSize = registerType.getSize();
			
			if(savedRegisterSize < AbstractType.WORD_SIZE)
				savedRegisterSize = AbstractType.WORD_SIZE;

			if(alignData){
				size = offSetFromStackPointerForNewElement(size, savedRegisterSize);
				description += "	" + register + "(size = " + savedRegisterSize + ") offset: " + size + newLine;
			}
			size += savedRegisterSize;
		}
		description += "Start offset for storing ret addrs = " + getStartOffsetForStoringReturnAddress() + newLine;
		int offsetForStoringFP = getStartOffsetForStoringFP();
		description += "Start offset for storing FP        = " + offsetForStoringFP + newLine;
		int endOfFPStore = offsetForStoringFP + WORDSIZE;
		
		if(stackElements.size() == 0){
			description += "No stack elements need to be stored. " + newLine;
			return description;
		}
			
		StackElement lastElement = stackElements.lastElement();
		int startOfStackElements = 0;
		if(!alignData)
			startOfStackElements = endOfFPStore ;
		
		else
			startOfStackElements = offSetFromStackPointerForNewElement(endOfFPStore, lastElement.getSize());
			
		description += "Start offset of stack elements     = " + startOfStackElements + newLine;
		int currentOffset = 0;

		if(!alignData){
			// No data alignment required; return the total size
			for(StackElement stackElement : stackElements){
				currentOffset += stackElement.getSize();
				description += "	" + "Element (size = " +  stackElement.getSize() + "): "+ currentOffset +  newLine;
			}
		}

		// Need data alignment
		for(StackElement stackElement : stackElements){
			int sizeOfElement = stackElement.getSize();			
			currentOffset = offSetFromFramePointerForNewElement(currentOffset, sizeOfElement);
			description += "	" + "Element (size = " +  sizeOfElement + ") offset from FP: "+ currentOffset +  newLine;
		}

		description += "Stack size                         = " + getStackSize() + newLine;
		
		
		return description;
	}
	
}

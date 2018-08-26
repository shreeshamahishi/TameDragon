package org.tamedragon.compilers.mips.translate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.tamedragon.assemblyabstractions.ActivationFrame;
import org.tamedragon.assemblyabstractions.constructs.AssemType;
import org.tamedragon.common.EnvironmentConstants;
import org.tamedragon.common.Label;
import org.tamedragon.common.TargetDataType;
import org.tamedragon.common.TargetMachine;
import org.tamedragon.common.llvmir.instructions.ArgumentPassIns;
import org.tamedragon.common.llvmir.instructions.AssemblyInstruction;
import org.tamedragon.common.llvmir.instructions.CJump;
import org.tamedragon.common.llvmir.instructions.ConditionalFlagSet;
import org.tamedragon.common.llvmir.instructions.Operation;
import org.tamedragon.common.llvmir.types.AbstractType;
import org.tamedragon.common.llvmir.types.Numeric;
import org.tamedragon.common.llvmir.types.Operand;
import org.tamedragon.common.llvmir.types.Temp;
import org.tamedragon.common.utils.ConversionUtility;
import org.tamedragon.compilers.mips.exceptions.UnsupportedInstructionException;

public class MipsProperties implements TargetMachine{

	private static final String CANNOT_RETURN_MORE_THAN_ONE_VALUE = "The MIPS architecture does not support returning more than one value from a function";

	// Data type descriptions
	public final String BYTE_TYPE = ".byte";
	public final String SHORT_INT_TYPE = ".half";
	public final String WORD_TYPE = ".word";
	public final String DOUBLE_WORD_TYPE = ".dword";
	public final String STRING_TYPE = ".asciiz";

	private AssemblyConstructor assemblyConstructor;

	private static final Vector<String> CALLEE_SAVED_INTEGER_REGISTERS = new Vector<String>();
	private static final Vector<String> CALLER_SAVED_INTEGER_REGISTERS = new Vector<String>();
	private static final Vector<String> CALLER_SAVED_FLOATING_POINT_REGISTERS = new Vector<String>();
	private static final Vector<String> CALLEE_SAVED_FLOATING_POINT_REGISTERS = new Vector<String>();

	private static final Vector<String> INT_ARG_PASSING_REGISTERS = new Vector<String>();
	private static final Vector<String> INT_RETURN_REGISTERS = new Vector<String>();
	private static final Vector<String> FLOATING_POINT_ARG_PASSING_REGISTERS = new Vector<String>();
	private static final Vector<String> FLOATING_POINT_RETURN_REGISTERS = new Vector<String>();

	static{
		// Add callee saved registers
		CALLEE_SAVED_INTEGER_REGISTERS.add("$s0"); CALLEE_SAVED_INTEGER_REGISTERS.add("$s1");
		CALLEE_SAVED_INTEGER_REGISTERS.add("$s2"); CALLEE_SAVED_INTEGER_REGISTERS.add("$s3");
		CALLEE_SAVED_INTEGER_REGISTERS.add("$s4"); CALLEE_SAVED_INTEGER_REGISTERS.add("$s5");
		CALLEE_SAVED_INTEGER_REGISTERS.add("$s6"); CALLEE_SAVED_INTEGER_REGISTERS.add("$s7");

		// Add caller saved registers
		CALLER_SAVED_INTEGER_REGISTERS.add("$t0"); CALLER_SAVED_INTEGER_REGISTERS.add("$t1");
		CALLER_SAVED_INTEGER_REGISTERS.add("$t2"); CALLER_SAVED_INTEGER_REGISTERS.add("$t3");
		CALLER_SAVED_INTEGER_REGISTERS.add("$t4"); CALLER_SAVED_INTEGER_REGISTERS.add("$t5");
		CALLER_SAVED_INTEGER_REGISTERS.add("$t6"); CALLER_SAVED_INTEGER_REGISTERS.add("$t7");
		CALLER_SAVED_INTEGER_REGISTERS.add("$t8"); CALLER_SAVED_INTEGER_REGISTERS.add("$t9");

		// Add caller saved floating point registers
		CALLER_SAVED_FLOATING_POINT_REGISTERS.add("$f2"); CALLER_SAVED_FLOATING_POINT_REGISTERS.add("$f4");
		CALLER_SAVED_FLOATING_POINT_REGISTERS.add("$f6"); CALLER_SAVED_FLOATING_POINT_REGISTERS.add("$f8");
		CALLER_SAVED_FLOATING_POINT_REGISTERS.add("$f10"); CALLER_SAVED_FLOATING_POINT_REGISTERS.add("$f16");
		CALLER_SAVED_FLOATING_POINT_REGISTERS.add("$f18"); 

		// Add callee saved floating point registers
		CALLEE_SAVED_FLOATING_POINT_REGISTERS.add("$f20"); CALLEE_SAVED_FLOATING_POINT_REGISTERS.add("$f22");
		CALLEE_SAVED_FLOATING_POINT_REGISTERS.add("$f24"); CALLEE_SAVED_FLOATING_POINT_REGISTERS.add("$f26");
		CALLEE_SAVED_FLOATING_POINT_REGISTERS.add("$f28"); CALLEE_SAVED_FLOATING_POINT_REGISTERS.add("$f30"); 

		// Add integer argument passing registers
		INT_ARG_PASSING_REGISTERS.add("$a0"); INT_ARG_PASSING_REGISTERS.add("$a1");
		INT_ARG_PASSING_REGISTERS.add("$a2"); INT_ARG_PASSING_REGISTERS.add("$a3");

		// Add integer return registers
		INT_RETURN_REGISTERS.add("$v0"); INT_RETURN_REGISTERS.add("$v1");

		// Add floating point argument passing registers
		FLOATING_POINT_ARG_PASSING_REGISTERS.add("$f12"); FLOATING_POINT_ARG_PASSING_REGISTERS.add("$f14");

		// Add floating point return registers
		FLOATING_POINT_RETURN_REGISTERS.add("$f0"); 

	}

	public MipsProperties(){
		assemblyConstructor = new AssemblyConstructor();
	}

	public Vector<String> getColors(){
		Vector<String> colors = new Vector<String>();

		colors.addAll(CALLEE_SAVED_INTEGER_REGISTERS);
		colors.addAll(CALLER_SAVED_INTEGER_REGISTERS);
		colors.addAll(CALLER_SAVED_FLOATING_POINT_REGISTERS);
		colors.addAll(CALLEE_SAVED_FLOATING_POINT_REGISTERS);

		return colors;

	}

	public Vector<String> createCodeStringForFunction( ActivationFrame activationFrame, 
			Vector<AssemblyInstruction> functionBodyCode, 
			boolean isMainFunction){

		return assemblyConstructor.createCodeForFunction(activationFrame , functionBodyCode, isMainFunction);
	}

	public static Vector<String> getCallerSavedIntegerRegisterNames(){
		return CALLER_SAVED_INTEGER_REGISTERS;
	}

	public static Vector<String> getCallerSavedFloatingPointRegisterNames(){
		return CALLER_SAVED_FLOATING_POINT_REGISTERS;
	}

	public static Vector<String> getCalleeSavedIntegerRegisterNames(){
		return CALLEE_SAVED_INTEGER_REGISTERS;
	}

	public static Vector<String> getCalleeSavedFloatingPointRegisterNames(){
		return CALLEE_SAVED_FLOATING_POINT_REGISTERS;
	}

	public static Vector<String> getIntArgumentPassingRegisterNames(){
		return INT_ARG_PASSING_REGISTERS;
	}

	public static Vector<String> getFloatingPointArgumentPassingRegisterNames(){
		return FLOATING_POINT_ARG_PASSING_REGISTERS;
	}

	public static Vector<String> getAllCallerSavedRegisters(){
		Vector<String> allCallerSavedRegisters = new Vector<String>();

		allCallerSavedRegisters.addAll(getCallerSavedIntegerRegisterNames());
		allCallerSavedRegisters.addAll(getCallerSavedFloatingPointRegisterNames());

		return allCallerSavedRegisters;
	}

	public static Vector<String> getAllCalleeSavedRegisters(){
		Vector<String> allCalleeSavedRegisters = new Vector<String>();

		allCalleeSavedRegisters.addAll(getCalleeSavedIntegerRegisterNames());
		allCalleeSavedRegisters.addAll(getCalleeSavedFloatingPointRegisterNames());

		return allCalleeSavedRegisters;
	}


	public Temp getRegister(int type){
		if(type == Temp.MACHINE_REGISTER_FRAME_POINTER)
			return Temp.getTemp("$fp");
		else if(type == Temp.MACHINE_REGISTER_STACK_POINTER)
			return Temp.getTemp("$sp");
		else if(type == Temp.MACHINE_REGISTER_RETURN_TYPE)
			return Temp.getTemp("$v0");
		else
			return null;

	}

	public int getNumParameterRegisters(){
		return 4;
	}

	public int getWordSize(){
		return 4;
	}

	public boolean isLoadStore(){
		return true;
	}

	public Vector<String>  getGlobalDataDeclarations(HashMap<Label, TargetDataType> labelVsTargetDataType){
		Vector<String> decls = new Vector<String>();

		Set<Label> entries = labelVsTargetDataType.keySet();
		Iterator<Label> iter = entries.iterator();
		while(iter.hasNext()) {
			Label lbl =  iter.next();
			TargetDataType targetDataType = (TargetDataType) labelVsTargetDataType.get(lbl);	
			String dataDesc = getDataSectionDescription(lbl, targetDataType);
			decls.addElement(dataDesc);
		}

		return decls;
	}

	public String getTypeDescription(int size, int type){		
		String typeStr = "";

		if(size == TargetDataType.DATA_TYPE_CHAR_SIZE){
			typeStr = BYTE_TYPE;
		}
		if(size == TargetDataType.DATA_TYPE_HALF_WORD_SIZE){
			typeStr = SHORT_INT_TYPE;
		}
		if(size == TargetDataType.DATA_TYPE_WORD_SIZE){
			typeStr = WORD_TYPE;
		}
		if(size == TargetDataType.DATA_TYPE_DOUBLE_WORD_SIZE){
			typeStr = DOUBLE_WORD_TYPE;
		}

		if(type == TargetDataType.DATA_TYPE_STRING){
			typeStr = STRING_TYPE;
		}

		return typeStr;
	}

	public String getDataSectionDescription(Label label, TargetDataType targetDataType){
		String dataSectionDesc = "";
		String typeDesc = getTypeDescription(targetDataType.getSize(), targetDataType.getType());
		dataSectionDesc =  label + ": " + typeDesc + " \"" + targetDataType.getValue() +  "\""  + EnvironmentConstants.NEWLINE;
		return dataSectionDesc;
	}

	public Vector<AssemblyInstruction> modify (Vector<AssemblyInstruction> instructionList,
			ActivationFrame activationFrame,
			boolean isLeafProcedure) throws Exception{

		if(instructionList == null)
			return null;

		Vector<AssemblyInstruction> modifiedInstructions = new Vector<AssemblyInstruction>();

		HashMap<AssemblyInstruction, Vector<AssemblyInstruction>> instructionVsReplacements 
		= new HashMap<AssemblyInstruction, Vector<AssemblyInstruction>>();

		// Collect any instructions to be replaced with corresponding instructions that replace
		// into a map

		List<ArgumentPassIns> argPassInstructions = null;

		for(AssemblyInstruction instruction : instructionList){
			int instType = instruction.getType();
			if(instType == AssemblyInstruction.ARG_PASS){
				ArgumentPassIns argPassIns = (ArgumentPassIns)instruction;
				if(argPassInstructions == null){
					argPassInstructions = new ArrayList<ArgumentPassIns>();
				}
				argPassInstructions.add(argPassIns);
			}
			else{
				if(argPassInstructions != null){

					Vector<AssemblyInstruction> modifiedArgPassInstructions = 
						getReplacementsForArgumentPassInstructions(argPassInstructions, activationFrame);
					if(modifiedArgPassInstructions != null && modifiedArgPassInstructions.size() > 0){

						// The size of the argument pass instruction list must equal the size of the
						// list of modified instructions
						int argumentIndex = 0;
						for(ArgumentPassIns argPassIns : argPassInstructions){
							Vector<AssemblyInstruction> modifedArgPassInsList = new Vector<AssemblyInstruction>();
							modifedArgPassInsList.add(modifiedArgPassInstructions.get(argumentIndex));
							instructionVsReplacements.put(argPassIns, modifedArgPassInsList);
							argumentIndex++;
						}
					}

					argPassInstructions = null;   // Reset
				}

				if(instType == AssemblyInstruction.CALL){
					// This is a call, lets move parameters into $a0, etc. if the callee accepts arguments
					Vector<AssemblyInstruction> modifiedCallInstructions = 
						getReplacementsForCallInstruction(instruction, activationFrame);

					if(modifiedCallInstructions != null && modifiedCallInstructions.size() > 0){
						instructionVsReplacements.put(instruction, modifiedCallInstructions);
					}
				}
				else if(instType == AssemblyInstruction.RETURN){
					// This is a return instruction, lets assign return values to $v0 or $f0
					Vector<AssemblyInstruction> modifiedReturnInstructions = 
						getReplacementsForReturnInstruction(instruction);

					if(modifiedReturnInstructions != null && modifiedReturnInstructions.size() > 0){
						instructionVsReplacements.put(instruction, modifiedReturnInstructions);
					}
				}
				else if(instType == AssemblyInstruction.INSTRUCTION){
					// If the operand of a operation is a floating point value, and the other is 
					// an immediate value, move the immediate value to a temp first
					Operation operation = (Operation) instruction;
					if(operation.isArithmeticInstruction()){
						Vector<AssemblyInstruction> modifiedIns = 
							getReplacementsForImmdValueInArithmeticInsHavingFloatingPointOperands(operation);

						if(modifiedIns != null && modifiedIns.size() > 0){
							instructionVsReplacements.put(instruction, modifiedIns);
						}

					}
				}
				else if(instType == AssemblyInstruction.CJUMP){
					// If the operand of a operation is a floating point value, and the other is 
					// an immediate value, move the immediate value to a temp first
					CJump conditionalJump = (CJump) instruction;
					Vector<AssemblyInstruction> modifiedIns = 
						getReplacementsForConditionalJumpInsHavingFloatingPointOperands(conditionalJump);

					if(modifiedIns != null && modifiedIns.size() > 0){
						instructionVsReplacements.put(instruction, modifiedIns);
					}
				}
			}
		}

		// Do the replacements
		for(AssemblyInstruction instruction : instructionList){
			Vector<AssemblyInstruction> replacements = instructionVsReplacements.get(instruction);
			if(replacements == null)
				modifiedInstructions.add(instruction);   // No replacements
			else
				modifiedInstructions.addAll(replacements);   // Replace

		}

		return modifiedInstructions;
	}

	/**
	 * Returns instruction for a single argument that is passed. An instruction is created that copies the
	 * operand of the pass instruction into either one of $a0, $a1, $f12, etc. or into a memory location 
	 * with a given offset from the frame pointer
	 * @param argPassIns
	 * @return
	 */
	private Vector<AssemblyInstruction> getReplacementsForArgumentPassInstructions(
			List<ArgumentPassIns> argPassInstructions,
			ActivationFrame activationFrame){

		Vector<AssemblyInstruction> replacementsForArgPassing = new Vector<AssemblyInstruction>();

		int intArgCountIndex = 0, floatPointArgCountIndex = 0; 

		// The following offset refers to the previous stack, i.e., the caller function since 
		// we need the arguments from there.

		for(ArgumentPassIns argPassIns : argPassInstructions){

			Operation argPassOperation = null;
			Temp argPassRegister = (Temp)argPassIns.getSrcList().get(0);  // Only one of these.

			Vector<Temp> destList = new Vector<Temp>();
			destList.addElement(argPassRegister);
			Vector<Operand> srcList = new Vector<Operand>();

			if(argPassRegister.isInteger()){
				// Argument passed is of integer type
				if(intArgCountIndex < INT_ARG_PASSING_REGISTERS.size()){
					Temp tmp = Temp.getTemp("$a" + intArgCountIndex);
					srcList.addElement(tmp);
					argPassOperation = new Operation(destList, srcList, Operation.MOVE_TEMP_TO_TEMP,  
							null, null);
				}
				else{
					int savedArgSize = argPassRegister.getIntegerSize();
					int offsetForArgPass = 
						activationFrame.getOffsetForNextElementFromStackPointerForAcceptingArgument(savedArgSize);

					argPassOperation = 
						getOperationForLoadingFromOffsetFromFP(argPassRegister, offsetForArgPass);

					AbstractType abstractType = ConversionUtility.getAbstractType(argPassRegister);
					activationFrame.addArgumentInStackForAccepting(abstractType);
				}

				intArgCountIndex++;
			}
			else{
				// Argument passed is of floating point type
				if(floatPointArgCountIndex < FLOATING_POINT_ARG_PASSING_REGISTERS.size()){
					Temp tmp = Temp.getTemp("$f" + ((floatPointArgCountIndex *2) + 12));
					srcList.addElement(tmp);
					argPassOperation = new Operation(destList, srcList, Operation.MOVE_TEMP_TO_TEMP,  
							null, null);
				}
				else{
					int savedArgSize = argPassRegister.getFloatPrecision();
					int offsetForArgPass = 
						activationFrame.getOffsetForNextElementFromStackPointerForAcceptingArgument(savedArgSize);

					argPassOperation = 
						getOperationForLoadingFromOffsetFromFP(argPassRegister, offsetForArgPass);

					AbstractType abstractType = ConversionUtility.getAbstractType(argPassRegister);
					activationFrame.addArgumentInStackForAccepting(abstractType);
				}	

				floatPointArgCountIndex++;
			}
			replacementsForArgPassing.add(argPassOperation);
		}

		return replacementsForArgPassing;
	}


	private Operation getOperationForLoadingFromOffsetFromFP(Temp destRegister, int offsetFromFP){

		Vector<Temp> destList = new Vector<Temp>();
		destList.addElement(destRegister);
		Vector<Operand> srcList = new Vector<Operand>();
		srcList.addElement(Temp.getTemp("$fp"));
		Numeric immd = new Numeric(Numeric.INTEGER_TYPE, "" + offsetFromFP);
		Operation operation = new Operation(destList, srcList, Operation.LOAD_FROM_TEMP_OFFSET_EXP_MEM,
				immd, null);

		return operation;
	}

	private Operation getOperationForStoringIntoOffsetFromSP(Temp srcRegister, int offsetFromSP){
		Vector<Operand> srcList = new Vector<Operand>();
		srcList.addElement(srcRegister);
		srcList.addElement(Temp.getTemp("$sp"));
		Numeric immd = new Numeric(Numeric.INTEGER_TYPE, ""+ offsetFromSP);
		Operation operation = new Operation(null, srcList, Operation.STORE_WORD_INTO_ADDRESS_AT_TEMP,
				immd, null);

		return operation;
	}


	/**
	 * Returns a list of MIPS-equivalent instructions (that includes real registers) related to a 
	 * call instruction in the IR. 
	 * @param callInsruction
	 * @return
	 */
	public Vector<AssemblyInstruction> getReplacementsForCallInstruction(AssemblyInstruction callInstruction,
			ActivationFrame activationFrame)
			throws UnsupportedInstructionException {

		Vector<Operation> parameterPassingOperations = 
			getAdditionalInstructionsForParameterPassing(callInstruction, activationFrame);

		Vector<AssemblyInstruction> allReplacements = new Vector<AssemblyInstruction>();
		if(parameterPassingOperations != null && parameterPassingOperations.size() > 0){
			allReplacements.addAll(parameterPassingOperations);
		}

		allReplacements.add(callInstruction);

		// Add any replacements for return operations
		Vector<Temp> defs = callInstruction.getDestList();
		Vector<Operation> functionReturnOperations = getInstructionsForFunctionReturn(defs);

		if(functionReturnOperations != null)
			allReplacements.addAll(functionReturnOperations);

		return allReplacements;
	}

	public Vector<Operation> getAdditionalInstructionsForParameterPassing(AssemblyInstruction callInstruction,
			ActivationFrame activationFrame){
		Vector<Operation> additionalInstructions = new Vector<Operation>();

		Vector<Operand> arguments = callInstruction.getSrcList();
		// Check for any arguments passed

		if(arguments == null || arguments.size() == 0)
			return additionalInstructions;

		int integerArgCount = 0;
		int floatingPointArgCount = 0;

		int argCount = 0;

		for(Operand operand : arguments){

			if(operand.isInteger()){					
				if(integerArgCount < INT_ARG_PASSING_REGISTERS.size()){
					// Create an instruction that moves this argument into $a<n> 
					// or onto the stack if the number of integer arguments exceed 4
					Vector<Temp> destList = new Vector<Temp>();							
					String registerName = "$a" + integerArgCount;
					Temp paramTemp = Temp.getTemp(registerName);

					destList.add(paramTemp);

					Vector<Operand> srcList = new Vector<Operand>();

					Operation instr = null;

					if(operand.getOperandType() == Operand.TEMP_TYPE){
						Temp srcTemp = (Temp) operand;
						updateRegisterWithTypeInfo(paramTemp, srcTemp);
						srcList.add(srcTemp);
						instr = new Operation(destList, srcList, Operation.MOVE_TEMP_TO_TEMP,  
								null, null);
					}
					else{
						Numeric numeric = (Numeric) operand;
						updateRegisterWithTypeInfo(paramTemp, numeric);
						instr = new Operation(destList, null, Operation.LOAD_IMMEDIATE_VALUE, 
								numeric, null);						
					}
					//additionalInstructions.add(saveOperation);
					additionalInstructions.add(instr);
					callInstruction.updateSourceList(argCount, paramTemp);
				}
				else{
					// Needs to go on to the stack
					// Get the abstract type and the size
					AbstractType abstractType = null;
					int elementSize = 0;
					if(operand.getOperandType() == Operand.TEMP_TYPE){
						Temp temp = (Temp) operand;
						elementSize = temp.getIntegerSize();
						abstractType = ConversionUtility.getAbstractType(temp);
					}
					else{
						Numeric numeric = (Numeric) operand;
						elementSize = numeric.getIntegerSize();
						abstractType = ConversionUtility.getAbstractType(numeric);
					}

					Vector<Operation> stackStorageIns = operationsForStackStorageOfParameters( 
							activationFrame, callInstruction, operand, elementSize);
					additionalInstructions.addAll(stackStorageIns);

					// Update the activation frame with this information
					activationFrame.addArgumentInStackForInvocation(callInstruction, abstractType);
				}

				integerArgCount++;
			}
			else{ 
				// Create an instruction that moves this argument into $f12 / $f13 or $f14 / $f15 
				// or onto the stack if the number of floating point arguments exceed 2 (if double precision) 
				// or 4 (if four floats)

				if(floatingPointArgCount < 2){
					String registerName = "$f12";
					String registerPairName = "$f13";

					if(floatingPointArgCount == 1){
						// Second floating point argument
						registerName = "$f14";
						registerPairName = "$f15";						
					}

					Vector<Operand> srcList = new Vector<Operand>();
					Operation instr = null;

					Vector<Temp> destList = new Vector<Temp>();							
					Temp paramTemp = Temp.getTemp(registerName);
					destList.add(paramTemp);

					if(operand.getOperandType() == Operand.TEMP_TYPE){	
						Temp useTemp = (Temp) operand;
						srcList.add((Temp)operand);

						updateRegisterWithTypeInfo(paramTemp, useTemp);

						if(useTemp.getFloatPrecision() == AssemType.FP_DOUBLE){   
							Temp paramTempForRegisterPair = Temp.getTemp(registerPairName);
							destList.add(paramTempForRegisterPair);
						}
						instr = new Operation(destList, srcList, Operation.MOVE_TEMP_TO_TEMP,  
								null, null);
					}
					else{
						Numeric numeric = (Numeric) operand;
						updateRegisterWithTypeInfo(paramTemp, numeric);
						if(numeric.getType() == Numeric.DOUBLE_TYPE){
							Temp paramTempForRegisterPair = Temp.getTemp(registerPairName);
							destList.add(paramTempForRegisterPair);
						}
						instr = new Operation(destList, null, Operation.LOAD_IMMEDIATE_VALUE, 
								numeric, null);						
					}

					//additionalInstructions.add(saveOperation);
					additionalInstructions.add(instr);
					callInstruction.updateSourceList(argCount, paramTemp);			
				}
				else{
					// Needs to go on to the stack
					// Get the abstract type and the size
					AbstractType abstractType = null;
					int elementSize = 0;
					if(operand.getOperandType() == Operand.TEMP_TYPE){
						Temp temp = (Temp) operand;
						elementSize += temp.getFloatPrecision();
						abstractType = ConversionUtility.getAbstractType(temp);
					}
					else{
						Numeric numeric = (Numeric) operand;
						elementSize = numeric.getFloatPrecision();
						abstractType = ConversionUtility.getAbstractType(numeric);
					}

					Vector<Operation> stackStorageIns = operationsForStackStorageOfParameters( 
							activationFrame, callInstruction, operand, elementSize);
					additionalInstructions.addAll(stackStorageIns);

					// Update the activation frame with this information
					activationFrame.addArgumentInStackForInvocation(callInstruction, abstractType);
				}

				floatingPointArgCount++;
			}

			argCount++;
		}

		return additionalInstructions;
	}

	private Vector<Operation> operationsForStackStorageOfParameters(ActivationFrame activationFrame, 
			AssemblyInstruction callInstruction, Operand operand, int elementSize){

		// Get the offset based on the element size and the current information on the stack
		int offset = activationFrame.getOffsetForNextArgumentForInvocationOnStackPointer(
				callInstruction, elementSize);

		Vector<Operation> operations = new Vector<Operation>();

		if(operand.getOperandType() == Operand.TEMP_TYPE){
			Temp srcTemp = (Temp) operand;
			Operation instr = getOperationForStoringIntoOffsetFromSP(srcTemp, offset);

			operations.add(instr);
		}
		else{
			Temp newTemp = new Temp();
			Numeric numeric = (Numeric) operand;
			updateRegisterWithTypeInfo(newTemp, numeric);
			Vector<Temp> destList = new Vector<Temp>();
			destList.add(newTemp);
			Operation loadImmdValueIns = new Operation(destList, null, Operation.LOAD_IMMEDIATE_VALUE, 
					numeric, null);

			operations.add(loadImmdValueIns);

			Operation storeIns = getOperationForStoringIntoOffsetFromSP(newTemp, offset);

			operations.add(storeIns);

		}

		return operations;
	}

	/**
	 * This function copies the values of return temps in the return temp that is passed into $v0 or $f0
	 * @param returnValues
	 * @return
	 * @throws UnsupportedInstructionException
	 */
	public Vector<AssemblyInstruction> getReplacementsForReturnInstruction(AssemblyInstruction instruction)
	throws UnsupportedInstructionException {

		Vector<Operand> srcList = instruction.getSrcList();
		if(srcList == null || srcList.size() == 0)
			return new Vector<AssemblyInstruction>();

		// Before modifying to MIPS, there will be only return value
		if(srcList.size() > 1)
			throw new UnsupportedInstructionException(CANNOT_RETURN_MORE_THAN_ONE_VALUE);

		Vector<AssemblyInstruction> replacementsForReturnValues = new Vector<AssemblyInstruction>();
		Vector<Temp> destList = new Vector<Temp>();

		Operand retValue = srcList.elementAt(0);
		Operation returnOperation = null;
		if(retValue.getOperandType() == Operand.TEMP_TYPE){
			Temp temp = (Temp) retValue;
			Temp retValRegister = null;
			if(temp.isInteger())
				retValRegister = Temp.getTemp("$v0");
			else
				retValRegister = Temp.getTemp("$f0");

			destList.add(retValRegister);

			retValRegister.setInteger(temp.isInteger());
			retValRegister.setIntegerSize(temp.getIntegerSize());
			retValRegister.setSigned(temp.isSigned());
			retValRegister.setFloatPrecision(temp.getFloatPrecision());

			returnOperation = new Operation(destList, srcList, Operation.MOVE_TEMP_TO_TEMP,  
					null, null);
		}
		else{
			Numeric numeric = (Numeric)retValue;
			Temp retValRegister = null;
			if(numeric.isInteger())
				retValRegister = Temp.getTemp("$v0");
			else
				retValRegister = Temp.getTemp("$f0");

			destList.add(retValRegister);

			retValRegister.setInteger(numeric.isInteger());
			retValRegister.setIntegerSize(numeric.getIntegerSize());
			retValRegister.setSigned(true);   // TODO: How do we know signed / unsigned here?
			retValRegister.setFloatPrecision(numeric.getFloatPrecision());


			returnOperation = new Operation(destList, null, Operation.LOAD_IMMEDIATE_VALUE,  
					numeric, null);
		}


		replacementsForReturnValues.add(returnOperation);

		return replacementsForReturnValues;
	}

	public Vector<Operation> getInstructionsForFunctionReturn(Vector<Temp> returnValues) 
	throws UnsupportedInstructionException {

		Vector<Operation> additionsForReturnValues = new Vector<Operation>();

		if(returnValues == null || returnValues.size() == 0)
			return additionsForReturnValues;

		// Before modifying to MIPS, there will be only return value
		if(returnValues.size() > 1)
			throw new UnsupportedInstructionException(CANNOT_RETURN_MORE_THAN_ONE_VALUE);

		Temp returnValue = returnValues.elementAt(0);  
		Vector<Temp> destList = new Vector<Temp>();
		Vector<Operand> srcList = new Vector<Operand>();

		destList.add(returnValue);
		Temp returnRegister = null;

		if(returnValue.isInteger())             // Move from $v0 to this register
			returnRegister = Temp.getTemp("$v0");
		else                                    // Move from $f0 to this register
			returnRegister = Temp.getTemp("$f0");	

		srcList.add(returnRegister);
		returnRegister.setInteger(returnValue.isInteger());
		returnRegister.setIntegerSize(returnValue.getIntegerSize());
		returnRegister.setSigned(returnValue.isSigned());
		returnRegister.setFloatPrecision(returnValue.getFloatPrecision());


		Operation getReturnValue = new Operation(destList, srcList, Operation.MOVE_TEMP_TO_TEMP,  
				null, null);
		additionsForReturnValues.add(getReturnValue);

		return additionsForReturnValues;
	}

	/**
	 * Returns true if the temporary passed in the first parameter can be assigned the register
	 * whose name is passed in the second parameter.
	 */
	public boolean isCompatibleWithRegister(Temp temp, String registerName){

		if(!temp.isInteger()){
			// The temporary does not have an integer; can only use even-numbered floating point registers
			if(CALLER_SAVED_FLOATING_POINT_REGISTERS.contains(registerName) || 
					CALLEE_SAVED_FLOATING_POINT_REGISTERS.contains(registerName))
				return true;
		}
		else{
			// The temporary has an integer register
			if(CALLEE_SAVED_INTEGER_REGISTERS.contains(registerName) 
					|| CALLER_SAVED_INTEGER_REGISTERS.contains(registerName))
				return true;
		}

		return false;
	}

	public boolean isSizeCompatible(Temp temp, String clr){
		if(!temp.isInteger()){
			if(temp.getFloatPrecision() == AbstractType.DOUBLE_PRECISION){
				// Temp has a double precision floating point
				if(!(CALLER_SAVED_FLOATING_POINT_REGISTERS.contains(clr)
						|| CALLEE_SAVED_FLOATING_POINT_REGISTERS.contains(clr)))
					return false;
			}
		}

		return true;
	}

	public Vector<AssemblyInstruction> getReplacementsForImmdValueInArithmeticInsHavingFloatingPointOperands(
			Operation operation){

		Vector<AssemblyInstruction> replacements = new Vector<AssemblyInstruction>();

		Vector<Temp> destList = operation.getDestList();

		boolean isArthmeticInsWithFloatingPointOperands = false;
		if(operation.isArithmeticInstruction()){


			for(Temp destTemp : destList){
				if(!destTemp.isInteger())
					isArthmeticInsWithFloatingPointOperands = true;
				break;
			}		
			if(!isArthmeticInsWithFloatingPointOperands){
				// Check in the source list
				Vector<Operand> srcList = operation.getSrcList();
				for(Operand operand : srcList){
					if(!operand.isInteger())
						isArthmeticInsWithFloatingPointOperands = true;
					break;
				}
			}
		}

		List<Numeric> numericOperands = new ArrayList<Numeric>();
		if(isArthmeticInsWithFloatingPointOperands){
			Vector<Operand> srcList = operation.getSrcList();
			for(Operand operand : srcList){
				if(operand.getOperandType() == Operand.NUMERIC_TYPE){
					numericOperands.add((Numeric) operand);
				}
			}

			// Check for any immediate values
			Numeric immdValueInOpr = operation.getImmediateValue();
			if(immdValueInOpr != null){
				numericOperands.add(immdValueInOpr);
			}
		}

		if(numericOperands.size() > 0){
			// Found some immediate values used with floating point operands

			// Get the type of the destination temp; promote the sources to this type
			Temp destTmp = destList.elementAt(0);

			int count = 0;
			for(Numeric immdVal : numericOperands){
				Temp newTemp = new Temp();
				newTemp.setInteger(destTmp.isInteger());
				newTemp.setIntegerSize(destTmp.getIntegerSize());
				newTemp.setSigned(destTmp.isSigned());
				newTemp.setFloatPrecision(destTmp.getFloatPrecision());

				Vector<Temp> newDestForLoadImmd = new Vector<Temp>();
				newDestForLoadImmd.add(newTemp);

				Operation loadImmdValOpr = new Operation(newDestForLoadImmd, null, Operation.LOAD_IMMEDIATE_VALUE, 
						immdVal, null);

				replacements.add(loadImmdValOpr);

				operation.replaceImmediateValueWithTemp(immdVal, newTemp);
				count++;

			}

			replacements.add(operation);

		}

		return replacements;

	}

	public Vector<AssemblyInstruction>  getReplacementsForConditionalJumpInsHavingFloatingPointOperands(
			CJump conditionalJump){
		Vector<AssemblyInstruction> replacements = new Vector<AssemblyInstruction>() ;

		if(!conditionalJump.hasFloatingPointOperands()){
			// There are no floating point operands in this conditional jump
			// nothing to do
			return replacements;
		}

		// One or more operands is a floating point operand

		// Check if both are in temps
		Temp firstTemp = conditionalJump.getTempOperandAtIndex(0);
		Temp secondTemp = conditionalJump.getTempOperandAtIndex(1);

		if(firstTemp != null && secondTemp != null){
			return getReplacementsForCJumpWithFloatingPointTemps(firstTemp, secondTemp, 
					conditionalJump.getTrueLabel(), conditionalJump.getFalseLabel(),
					conditionalJump.getRelOp());
		}

		if(firstTemp == null){
			// The first operand MUST be a numeric, load it into a register
			Numeric firstNumeric = conditionalJump.getNumericOperandAtIndex(0);
			firstTemp = new Temp();
			Operation loadIns = getInsToLoadFloatingPointValueInRegister(firstTemp, firstNumeric);
			replacements.add(loadIns);
		}

		// The second operand must be either a numeric OR null (in which case check the immediate value
		// of the conditional jump)
		Numeric secondNumeric = conditionalJump.getNumericOperandAtIndex(1);

		if(secondNumeric == null)   // The second operand is null; the numeric value MUST be in the
			// immediate value of the conditional jump
			secondNumeric = conditionalJump.getImmediateValue();

		// Second operand is a numeric; put this in a new temporary
		secondTemp = new Temp();
		Operation loadIns = getInsToLoadFloatingPointValueInRegister(secondTemp, secondNumeric);
		replacements.add(loadIns);
		Vector<AssemblyInstruction> additionalIns = 
			getReplacementsForCJumpWithFloatingPointTemps(firstTemp, secondTemp,
					conditionalJump.getTrueLabel(), conditionalJump.getFalseLabel(), conditionalJump.getRelOp());
		replacements.addAll(additionalIns);
		
		return replacements;
	}

	/**
	 * Returns two instructions:
	 * 
	 * 1. A ConditionalFlagSet instruction based on the conditional flag
	 * 2. A CJump instruction based on the value conditional register flag, the true and false labels and 
	 *    the relational operator passed in the 3rd, 4th and 5th arguments respectively.
	 */
	private Vector<AssemblyInstruction>  getReplacementsForCJumpWithFloatingPointTemps(Temp firstTemp,
			Temp secondTemp, String trueLabel, String falseLabel, int relop){
		
		Vector<AssemblyInstruction> replacements = new Vector<AssemblyInstruction>();

		String conditionFlagValue = "1";
		if(relop == CJump.GE || relop == CJump.GT){
			if(relop == CJump.GE)
				relop = CJump.LT;
			else
				relop = CJump.LE;
			
			/*Temp tempTemp = firstTemp;
			firstTemp = secondTemp;
			secondTemp = tempTemp;
			*/
			conditionFlagValue = "0";
			
		}
		
		// Create the conditional flag set instruction and add to replacements
		Temp conditionalTemp = Temp.getTemp("cond");
		Vector<Operand> srcList = new Vector<Operand>();
		srcList.add(firstTemp);
		srcList.add(secondTemp);

		ConditionalFlagSet conditionalFlagSet = new ConditionalFlagSet(srcList, relop, conditionalTemp);
		replacements.add(conditionalFlagSet);
		
		// Create the new cjump instruction and add to replacements
		Vector<Operand> srcListForNewCJump = new Vector<Operand>();
		srcListForNewCJump.add(conditionalTemp);
		Numeric numeric = new Numeric(Numeric.INTEGER_TYPE, conditionFlagValue);
		srcListForNewCJump.add(numeric);
		
		CJump newCJump = new CJump(srcListForNewCJump, trueLabel, falseLabel, CJump.EQ, null);
		replacements.add(newCJump);
		
		return replacements;
	}
	
	/**
	 * Returns an instruction that loads the immediate value passed in the second argument into the temp
	 * value passed in the second argument.
	 * @param temp
	 * @param numeric
	 * @return
	 */
	private Operation getInsToLoadFloatingPointValueInRegister(Temp temp, Numeric numeric){
		
		if(numeric.isInteger()){
			// Can happen in cases where we are comparing a float with an int (eg. : beq $f2, 1)
			// Convert that numeric to a floating value
			
			int precision = Numeric.FLOAT_TYPE;
			int tempPrecision = temp.getFloatPrecision();
			if(tempPrecision == AbstractType.DOUBLE_PRECISION)
				precision = Numeric.DOUBLE_TYPE;
			
			String value = numeric.toString();
			
			if(value.indexOf(".") < 0)
				value += ".0";
			
			
			numeric = new Numeric(precision, value);
		}
		
		updateRegisterWithTypeInfo(temp, numeric);
		Vector<Temp> destList = new Vector<Temp>();
		destList.addElement(temp);
		Operation instr = new Operation(destList, null,Operation.LOAD_IMMEDIATE_VALUE,
				numeric, null);

		return instr;
	}

	/**
	 * Updates the type information of the value contained in the new temp using the 
	 * type information contained in the reference temp passed in the second parameter. 
	 * @param newTemp
	 * @param referenceTemp
	 */
	private void updateRegisterWithTypeInfo(Temp newTemp, Temp referenceTemp){

		if(newTemp == null || referenceTemp == null)   // Sanity check
			return;

		newTemp.setInteger(referenceTemp.isInteger());
		newTemp.setIntegerSize(referenceTemp.getIntegerSize());
		newTemp.setSigned(referenceTemp.isSigned());
		newTemp.setFloatPrecision(referenceTemp.getFloatPrecision());
	}

	/**
	 * Updates the type information of the value contained in the new temp using the 
	 * type information contained in the reference numeric passed in the second parameter. 
	 * @param newTemp
	 * @param referenceTemp
	 */
	private void updateRegisterWithTypeInfo(Temp newTemp, Numeric numeric){

		if(newTemp == null || numeric == null)   // Sanity check
			return;

		newTemp.setInteger(numeric.isInteger());
		newTemp.setIntegerSize(numeric.getIntegerSize());
		newTemp.setFloatPrecision(numeric.getFloatPrecision());
	}
}

package org.tamedragon.compilers.mips.translate;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.tamedragon.assemblyabstractions.ActivationFrame;
import org.tamedragon.assemblyabstractions.constructs.AssemCJump;
import org.tamedragon.assemblyabstractions.exceptions.InvalidStackElementAccessException;
import org.tamedragon.common.EnvironmentConstants;
import org.tamedragon.common.llvmir.instructions.AssemblyInstruction;
import org.tamedragon.common.llvmir.instructions.CJump;
import org.tamedragon.common.llvmir.instructions.Call;
import org.tamedragon.common.llvmir.instructions.ConditionalFlagSet;
import org.tamedragon.common.llvmir.instructions.Jump;
import org.tamedragon.common.llvmir.instructions.LabelInstr;
import org.tamedragon.common.llvmir.instructions.Operation;
import org.tamedragon.common.llvmir.types.AbstractType;
import org.tamedragon.common.llvmir.types.Numeric;
import org.tamedragon.common.llvmir.types.Operand;
import org.tamedragon.common.llvmir.types.Temp;

public class AssemblyConstructor {	
	public static final String CALL_PRELUDE = "jal ";

	private ActivationFrame activationFrame;

	public Vector<String> createCodeForFunction(ActivationFrame activationFrame, 
			Vector<AssemblyInstruction> functionBodyCode, 
			boolean isMainFunction){

		this.activationFrame = activationFrame;

		Vector<String> functionCode = new Vector<String>();

		// If this function calls other functions, insert the pre and post call instructions;
		// else, retain original body code.
		Vector<String> bodyCodeWithPreAndPostCallInstrs = 
			getCodeWithPreAndPostCallInstrs(functionBodyCode);

		// Add the prologue and epilogue for this function
		functionCode.addAll(bodyCodeWithPreAndPostCallInstrs);
		Vector<String> prologue = getPrologueInstructions();
		functionCode.addAll(1, prologue);
		Vector<String> epilogue = getEpilogueInstructions(isMainFunction);
		functionCode.addAll(epilogue);

		return functionCode;
	}

	public Vector<String> getCodeWithPreAndPostCallInstrs( Vector<AssemblyInstruction> functionBody){
		Vector<String> code = new Vector<String>();

		// Loop through the function body, looking for function calls
		Vector<String> [] preAndPostCallInstrs = null;
		List<String> operationEquivalents = null;
		for(int i = 0; i < functionBody.size(); i++){
			AssemblyInstruction instr = functionBody.elementAt(i);
			int instrType = instr.getType();
			String instrString = null;

			if(instr.getType() == AssemblyInstruction.CALL){

				Call callInstr = (Call) instr;
				instrString = emitInstructionForCall(callInstr);

				preAndPostCallInstrs = getPreAndPostCallInstrs();

			}
			else{				
				if(instrType == AssemblyInstruction.CJUMP){
					CJump cjump = (CJump) instr;
					instrString = emitInstructionForCJump(cjump);
				}
				else if(instrType == AssemblyInstruction.CONDITIONAL_FLAG_SET){
					ConditionalFlagSet conditionalFlagSet = (ConditionalFlagSet) instr;
					instrString = emitInstructionForConditionalFlagSet(conditionalFlagSet);
				}
				else if(instrType == AssemblyInstruction.INSTRUCTION){
					Operation operation = (Operation) instr;
					operationEquivalents = emitInstructionForOperation(operation);
				}
				else if(instrType == AssemblyInstruction.JUMP){
					Jump jump = (Jump) instr;
					instrString = emitInstructionForJump(jump);
				}
				else if(instrType == AssemblyInstruction.LABEL){
					LabelInstr labelInstr = (LabelInstr) instr;
					instrString = emitInstructionForLabel(labelInstr);
				}
				else{
					instrString = "??";
				}
			}
			
			// If there are pre and post-call instructions, add them and 
			// reset back to null;
			if(preAndPostCallInstrs != null){
				code.addAll(preAndPostCallInstrs[0]);  // Add the precall instructions
				code.addElement(instrString);          // Add the call instruction itself
				code.addAll(preAndPostCallInstrs[1]);   // Add the postcall instructions

				preAndPostCallInstrs = null; // Reset it back to null
			}
			else{
				if(instrString == null){
					// Try the operation equivalents
					if(operationEquivalents != null){
						code.addAll(operationEquivalents);
						operationEquivalents = null;
					}
				}
				else
					code.addElement(instrString);
			}
		}

		return code;

	}

	public String emitInstructionForCall(Call call){
		return EnvironmentConstants.TAB + "jal " + call.getFunctionName() + EnvironmentConstants.NEWLINE;
	}

	public Vector<String>[] getPreAndPostCallInstrs(){

		Vector<String>[] preAndPostCallInstrs = new Vector[2];
		Vector<String> preCallInstrs = new Vector<String>();
		Vector<String> postCallInstrs = new Vector<String>();

		preAndPostCallInstrs[0] = preCallInstrs;
		preAndPostCallInstrs[1] = postCallInstrs;
		List<String> callerSavedRegisters = activationFrame.getCallerSavedRegistersUsedInFunction();

		if(callerSavedRegisters == null ||  callerSavedRegisters.size() == 0)
			return preAndPostCallInstrs;  // No need to save, since none of the caller-saved registers are being used.


		// TODO: For now, we save ALL caller-saved registers that are being used, since we do not know which 
		// of those registers the called function can clobber; after inter-procedural optimization, we choose
		// only the ones used in the called function

		int registerCount = 0;
		for(String register : callerSavedRegisters){

			AbstractType registerType = activationFrame.getRegisterType(register);
			int offSetForCallerSave = 0;
			try{
				offSetForCallerSave = 
				activationFrame.getOffsetForSavedRegisterstFromStackPointer(
						ActivationFrame.CALLER_SAVED_REGISTERS_FLAG, registerCount);
			}
			catch(InvalidStackElementAccessException excp){
				// TODO Handle this
			}

			if(registerType.isInteger()){
				
				String saveInstr = EnvironmentConstants.TAB + "sw" + " " + register + ", " + offSetForCallerSave + "($sp)" + EnvironmentConstants.NEWLINE;
				preCallInstrs.addElement(saveInstr);

				String loadInstr = EnvironmentConstants.TAB + "lw" + " " + register + ", " + offSetForCallerSave + "($sp)"  + EnvironmentConstants.NEWLINE;
				postCallInstrs.addElement(loadInstr);

			}
			else{
				String storeOperatorDesc = "s.s";
				String loadOperatorDesc = "l.s";
				int precision = registerType.getFloatPrecision();

				if(precision == AbstractType.DOUBLE_PRECISION){
					storeOperatorDesc = "s.d";
					loadOperatorDesc = "l.d";
				}

				String saveInstr = EnvironmentConstants.TAB + storeOperatorDesc + " " + register + ", " + 
									offSetForCallerSave + "($sp)" + EnvironmentConstants.NEWLINE;
				preCallInstrs.addElement(saveInstr);

				String loadInstr = EnvironmentConstants.TAB + loadOperatorDesc + " " + register + ", " + 
									offSetForCallerSave + "($sp)"  + EnvironmentConstants.NEWLINE;
				postCallInstrs.addElement(loadInstr);

			}
			
			registerCount++;
		}

		// Nothing to save; just display a comment
		//String comment = EnvironmentConstants.TAB + "# PRE-CALL -> NO REGISTER NEEDS TO BE SAVED"  + EnvironmentConstants.NEWLINE;
		//preCallInstrs.add(comment);

		return preAndPostCallInstrs;
	}

	public String emitInstructionForCJump(CJump cjump){
		Numeric immediateValue = cjump.getImmediateValue();
		Vector<Operand> srcList = cjump.getSrcList();
		int relop = cjump.getRelOp();
		String ifLabel = cjump.getTrueLabel();
		if(immediateValue != null){
			// Comparison with a constant value
			Operand opr = (Operand) srcList.elementAt(0);   // Only one temp in the statement			
			return getCJumpString(relop, opr, immediateValue, ifLabel);
		}
		else{
			// Comparison with two temporaries
			Operand lhsOpr = (Operand) srcList.elementAt(0);			
			
			Operand rhsOpr = (Operand) srcList.elementAt(1);
			return getCJumpString(relop, lhsOpr.toString(), rhsOpr.toString(), ifLabel);
		}
	}
	
	public String emitInstructionForConditionalFlagSet(ConditionalFlagSet conditionalFlagSet){
		String desc = "";
		
		Vector<Operand> srcList = conditionalFlagSet.getSrcList();
		int relop = conditionalFlagSet.getRelOp();
		
		Operand opr1 = srcList.get(0);
		Operand opr2 = srcList.get(1);
		String src1 = opr1.toString();
		String src2 = opr2.toString();
		
		String opDesc = "";
		
		if(relop == CJump.EQ) 
			opDesc = "c.eq";
		else if(relop == CJump.NE)
			opDesc = "c.ne";
		else if(relop == CJump.GT)
			opDesc = "c.gt";
		else if(relop == CJump.GE)
			opDesc = "c.ge";
		else if(relop == CJump.LT)
			opDesc = "c.lt";
		else 
			opDesc = "c.le";
		
		String floatingPointPostfixDesc = "";
		String floatingPointPostfixForOpr1 = floatingPointPostfix(opr1);
		String floatingPointPostfixForOpr2 = floatingPointPostfix(opr2);
		if("".equals(floatingPointPostfixForOpr1)){
			floatingPointPostfixDesc = floatingPointPostfixForOpr2;
		}
		else{
			if("s".equals(floatingPointPostfixForOpr1)){
				floatingPointPostfixDesc = "s";
				if("d".equals(floatingPointPostfixForOpr2)){
					floatingPointPostfixDesc = "d";
				}
			}
			else{
				floatingPointPostfixDesc = "d";
			}
		}
		
		floatingPointPostfixDesc = "." + floatingPointPostfixDesc;
		
		desc = EnvironmentConstants.TAB + opDesc + floatingPointPostfixDesc + " " + src1.toString() + ", " 
				+ src2 + EnvironmentConstants.NEWLINE;
		
		return desc;
	}

	private String floatingPointPostfix(Operand operand){
		String postfix = "";
		
		if(operand.getOperandType() == Operand.NUMERIC_TYPE){
			Numeric numeric = (Numeric) operand;
			if(numeric.isInteger())
				return postfix;
			
			if(numeric.getFloatPrecision() == AbstractType.SINGLE_PRECISION)
				return "s";
			else
				return "d";
		}
		else{
			Temp temp = (Temp) operand;
			if(temp.isInteger())
				return postfix;
			
			if(temp.getFloatPrecision() == AbstractType.SINGLE_PRECISION)
				return "s";
			else
				return "d";
		}
	}
	
	private String getCJumpString(int op, Operand src1, Numeric immediateValue,
			String ifLabel){
		String branchOnStr = "";

		int numericType = immediateValue.getType();
		if(numericType == Numeric.INTEGER_TYPE){
			int value = Integer.parseInt(immediateValue.toString());
			if(op == AssemCJump.EQ || op == AssemCJump.NE){
				if(value == 0){
					if(op == AssemCJump.EQ) 
						branchOnStr = EnvironmentConstants.TAB + "beqz " + src1.toString() + ", " + 
						ifLabel + EnvironmentConstants.NEWLINE;
					else 
						branchOnStr = EnvironmentConstants.TAB + "bnez " + src1.toString() + ", " 
						+ ifLabel + EnvironmentConstants.NEWLINE;
				}
				else {
					branchOnStr = getCJumpString(op, src1.toString(), ""+value, ifLabel);
				}
			}
			else{
				if(value == 0){
					if(op == AssemCJump.GT)
						branchOnStr = EnvironmentConstants.TAB + "bgtz " + src1.toString() + ", " + 
						ifLabel.toString() + EnvironmentConstants.NEWLINE;
					else if(op == AssemCJump.GE)
						branchOnStr = EnvironmentConstants.TAB + "bgez " + src1.toString() + ", " + 
						ifLabel.toString() + EnvironmentConstants.NEWLINE;
					else if(op == AssemCJump.LT)
						branchOnStr = EnvironmentConstants.TAB + "bltz " + src1.toString() + ", " + 
						ifLabel.toString() + EnvironmentConstants.NEWLINE;
					else 
						branchOnStr = EnvironmentConstants.TAB + "blez " + src1.toString() + ", " + 
						ifLabel.toString() + EnvironmentConstants.NEWLINE;
				}
				else{
					branchOnStr = getCJumpString(op, src1.toString(), ""+ value, ifLabel);
				}
			}
		}
		else if(numericType == Numeric.FLOAT_TYPE){
			// TODO - Implement this
			float value = Float.parseFloat(immediateValue.toString());	
		}
		else{ // Double type
			// TODO - Implement this
			double value = Double.parseDouble(immediateValue.toString());
		}				

		return branchOnStr;
	}

	private String getCJumpString(int op, String src1, String src2, String ifLabel){
		String branchOnStr = "";
		
		// Handle the special case of the conditional flag set to 1 (eg. : bc1t L24)
		if(src1.equals("cond")){
			if(src2.equals("1")){
				branchOnStr = EnvironmentConstants.TAB + "bc1t " + ifLabel + EnvironmentConstants.NEWLINE;
			}
			else{
				branchOnStr = EnvironmentConstants.TAB + "bc1f " + ifLabel + EnvironmentConstants.NEWLINE;
			}
			
			return branchOnStr;
		}
		
		// Not checking the conditional flag; lets consider other types of CJump
		if(op == AssemCJump.EQ) 
			branchOnStr = EnvironmentConstants.TAB + "beq " + src1.toString() + ", "  
			+ src2 + ", " + ifLabel + EnvironmentConstants.NEWLINE;
		else if(op == AssemCJump.NE)
			branchOnStr = EnvironmentConstants.TAB + "bne " + src1.toString() + ", " 
			+ src2 + ", " +  ifLabel + EnvironmentConstants.NEWLINE;
		else if(op == AssemCJump.GT)
			branchOnStr = EnvironmentConstants.TAB + "bgt " + src1.toString() + ", "  
			+ src2 + ", " + ifLabel + EnvironmentConstants.NEWLINE;
		else if(op == AssemCJump.GE)
			branchOnStr = EnvironmentConstants.TAB + "bge " + src1.toString() + ", "  
			+ src2 + ", " + ifLabel + EnvironmentConstants.NEWLINE;
		else if(op == AssemCJump.LT)
			branchOnStr = EnvironmentConstants.TAB + "blt " + src1.toString() + ", "  
			+ src2 + ", " + ifLabel + EnvironmentConstants.NEWLINE;
		else 
			branchOnStr = EnvironmentConstants.TAB + "ble " + src1.toString() + ", "  
			+ src2 + ", " + ifLabel + EnvironmentConstants.NEWLINE;

		return branchOnStr;
	}

	public String emitInstructionForJump(Jump jump){		
		return EnvironmentConstants.TAB + "j " + jump.getLabel() + EnvironmentConstants.NEWLINE;
	}

	public String emitInstructionForLabel(LabelInstr labelInstr){
		return labelInstr.getLabelStr() + ":" + EnvironmentConstants.NEWLINE;
	}

	public List<String> emitInstructionForOperation(Operation operation){
		String instructionDescription = "";
		
		String extraInsForNonCommutativeOperations = null;

		Vector<Temp> destList = operation.getDestList();
		Vector<Operand> srcList = operation.getSrcList();
		int operationCode = operation.getOperationCode();
		Numeric immediateValue = operation.getImmediateValue();
		Temp destTemp = null; // Only one destination
		if(destList != null){
			destTemp = (Temp) destList.elementAt(0); 
		}

		int numSrcTemps = 0;
		if(srcList != null){
			numSrcTemps = srcList.size();
		}

		if(operationCode == Operation.ADD || operationCode == Operation.MINUS || 
				operationCode == Operation.MUL || operationCode == Operation.DIV 
				|| operationCode == Operation.MODULO || operationCode == Operation.BITWISE_AND
				|| operationCode == Operation.BITWISE_OR || operationCode == Operation.BITWISE_XOR
				|| operationCode == Operation.LEFT_SHIFT || operationCode == Operation.RIGHT_SHIFT
				|| operationCode == Operation.ONES_COMPLEMENT){
			// A mathematical operation; should have one destination and either:
			// two source variables - either two temporaries or a temporary and an immediate value
			// OR 1 source variable that is an operand in a unary operation.

			String lhsOperand = "";
			boolean lhsIsImmediateValue = false;
			if(srcList.elementAt(0).getOperandType() == Operand.TEMP_TYPE){
				lhsOperand = ((Temp)srcList.elementAt(0)).toString();
			}
			else{
				lhsIsImmediateValue = true;
				lhsOperand = ((Numeric)srcList.elementAt(0)).toString();
			}

			String rhsOperand = "";
			if(numSrcTemps == 2){
				if(srcList.elementAt(1).getOperandType() == Operand.TEMP_TYPE)
					rhsOperand = ((Temp)srcList.elementAt(1)).toString();
				else{
					Numeric nm = (Numeric)srcList.elementAt(1);
					rhsOperand = getNumericValueToEmit(nm, operation);
				}
			}
			else{
				if(immediateValue == null){
					// Must be unary operation
					if(srcList.elementAt(0).getOperandType() == Operand.TEMP_TYPE)
						rhsOperand = ((Temp)srcList.elementAt(0)).toString();
					else{
						Numeric nm = (Numeric)srcList.elementAt(0);
						rhsOperand = getNumericValueToEmit(nm, operation);
					}
				}
				else{
					rhsOperand = getNumericValueToEmit(immediateValue, operation);
				}
			}

			if(lhsIsImmediateValue){
				// The LHS cannot be an immediate value in MIPS, if it is, we exchange the lhs and rhs if the
				// operation is commutative; else other manipulations are needed
				
				if(operation.isCommutative()){
					String temp = lhsOperand;
					lhsOperand = rhsOperand;
					rhsOperand = temp;
				}
				else{
					String operationDescForLoad  = getOperatorDescBasedOnType("li", destTemp);
					extraInsForNonCommutativeOperations = EnvironmentConstants.TAB + 
					operationDescForLoad + " " + destTemp.toString() + ", " + lhsOperand
							+ EnvironmentConstants.NEWLINE;
					
					lhsOperand = destTemp.toString();
				}
			}
			
			if(operationCode == Operation.ADD || operationCode == Operation.MINUS || operationCode == Operation.MUL
					|| operationCode == Operation.DIV){
				String oprDesc = null;
				if(operationCode == Operation.ADD)
					oprDesc = getOperatorDescBasedOnType("add", destTemp);
				else if(operationCode == Operation.MINUS)
					oprDesc = getOperatorDescBasedOnType("sub", destTemp);
				else if(operationCode == Operation.MUL)
					oprDesc = getOperatorDescBasedOnType("mul", destTemp);
				else 
					oprDesc = getOperatorDescBasedOnType("div", destTemp);

				instructionDescription = EnvironmentConstants.TAB + oprDesc + " " + destTemp.toString() + ", " + lhsOperand + ", " 
				+ rhsOperand + EnvironmentConstants.NEWLINE;
			}

			else if(operationCode == Operation.MODULO)
				instructionDescription = EnvironmentConstants.TAB + "rem " + destTemp.toString() + ", " + lhsOperand + ", " 
				+ rhsOperand + EnvironmentConstants.NEWLINE;
			else if(operationCode == Operation.BITWISE_AND)
				instructionDescription = EnvironmentConstants.TAB + "and " + destTemp.toString() + ", " + lhsOperand + ", " 
				+ rhsOperand + EnvironmentConstants.NEWLINE;
			else if(operationCode == Operation.BITWISE_OR)
				instructionDescription = EnvironmentConstants.TAB + "or " + destTemp.toString() + ", " + lhsOperand + ", " 
				+ rhsOperand + EnvironmentConstants.NEWLINE;
			else if(operationCode == Operation.BITWISE_XOR)
				instructionDescription = EnvironmentConstants.TAB + "xor " + destTemp.toString() + ", " + lhsOperand + ", " 
				+ rhsOperand + EnvironmentConstants.NEWLINE;
			else if(operationCode == Operation.LEFT_SHIFT)
				instructionDescription = EnvironmentConstants.TAB + "rol " + destTemp.toString() + ", " + lhsOperand + ", " 
				+ rhsOperand + EnvironmentConstants.NEWLINE;
			else if(operationCode == Operation.RIGHT_SHIFT)
				instructionDescription = EnvironmentConstants.TAB + "ror " + destTemp.toString() + ", " + lhsOperand + ", " 
				+ rhsOperand + EnvironmentConstants.NEWLINE;
			else if(operationCode == Operation.ONES_COMPLEMENT)
				instructionDescription = EnvironmentConstants.TAB + "not " + destTemp.toString() + ", " + lhsOperand +
				EnvironmentConstants.NEWLINE;
		}
		else if(operationCode == Operation.LOAD_IMMEDIATE_VALUE){
			String immdValToEmit = getNumericValueToEmit(immediateValue, operation);
			
			if(destTemp.isInteger())
				instructionDescription = EnvironmentConstants.TAB + "li " + destTemp.toString() + ", " + 
				immdValToEmit + EnvironmentConstants.NEWLINE; 
			else{
				if(destTemp.getFloatPrecision() == AbstractType.SINGLE_PRECISION)
					instructionDescription = EnvironmentConstants.TAB + "li.s " + destTemp.toString() + ", " + 
					immdValToEmit + EnvironmentConstants.NEWLINE; 
				else
					instructionDescription = EnvironmentConstants.TAB + "li.d " + destTemp.toString() + ", " + 
					immdValToEmit + EnvironmentConstants.NEWLINE; 
			}
		}
		else if(operationCode == Operation.STORE_WORD_INTO_ADDRESS_AT_TEMP){
			
			// The temp representing the address CANNOT be a Numeric
			Temp srcTempToStore = (Temp) srcList.elementAt(0);
			String srcString= (srcTempToStore).toString();
			
			String storeString = getLoadStoreOperatorDescriptionForIntOperator(
					srcTempToStore.getIntegerSize(), !srcTempToStore.isSigned(), true);
			if(!srcTempToStore.isInteger()){
				if(srcTempToStore.getFloatPrecision() == AbstractType.SINGLE_PRECISION)
					storeString = "s.s";
				else
					storeString = "s.d";
			}

			// The temp representing the address CANNOT be a Numeric
			Temp addrTemp = (Temp) srcList.elementAt(1);

			String offSetStr = immediateValue.toString();
			int offSet = Integer.parseInt(offSetStr);
			String destStr = "";
			if(offSet == 0)
				destStr = "(" + addrTemp.toString() + ")" + EnvironmentConstants.NEWLINE;
			else 
				destStr = offSet + "(" + addrTemp.toString() + ")" + EnvironmentConstants.NEWLINE;
			instructionDescription = EnvironmentConstants.TAB + storeString + " " + srcString + ", " + destStr;
		}
		else if(operationCode == Operation.STORE_WORD_INTO_CONST_ADDRESS){
			String srcString = "";
			if(srcList.elementAt(0).getOperandType() == Operand.TEMP_TYPE)
				srcString = ((Temp) srcList.elementAt(0)).toString();
			else
				srcString = ((Numeric) srcList.elementAt(0)).toString();

			// TODO - Check if the following statement is correct instruction
			instructionDescription = EnvironmentConstants.TAB + "sw " + srcString + ", " + immediateValue.toString() + EnvironmentConstants.NEWLINE;
		}
		else if(operationCode == Operation.MOVE_TEMP_TO_TEMP){
			String srcString = "";
			String moveIns = "";

			Operand srcOperand = (Operand) srcList.elementAt(0);   // Only one source for this operation

			boolean reverseOperandOrder = false;

			if(srcOperand.getOperandType() == Operand.TEMP_TYPE){
				Temp srcTmp = (Temp) srcList.elementAt(0);
				srcString = srcTmp.toString();

				if(!destTemp.isInteger()){
					if(!srcTmp.isInteger()){
						// Destination temp has floating point and source temp also has floating point
						if(srcTmp.getFloatPrecision() == AbstractType.SINGLE_PRECISION)
							moveIns = "mov.s";
						else
							moveIns = "mov.d";
					}
					else{
						// Destination temp has floating point and source temp is has integer
						moveIns = "mtc1";
						reverseOperandOrder = true;
					}
				}
				else{
					if(!srcTmp.isInteger()){
						// Destination temp has integer and source temp is has floating point
						moveIns = "mfc1";
					}
					else{
						// Destination temp has integer and source temp is also integer
						moveIns = "move";
					}
				}
			}
			else{
				// TODO - Check if this can happen
				srcString = ((Numeric) srcList.elementAt(0)).toString();
			}

			if(!reverseOperandOrder)
				instructionDescription = EnvironmentConstants.TAB + moveIns + " " + destTemp.toString() + ", " + srcString 
				+ EnvironmentConstants.NEWLINE;
			else      // Eg. : mftc1 $t0, $f14
				instructionDescription = EnvironmentConstants.TAB + moveIns + " " + srcString + ", " + destTemp.toString() 
				+ EnvironmentConstants.NEWLINE;
		}
		else if(operationCode == Operation.LOAD_ADDRESS){
			if(operation.getLabel() != null)                        // Load address from label
				instructionDescription = EnvironmentConstants.TAB + "la " + destTemp.toString() + ", " 
				+ operation.getLabel() + EnvironmentConstants.NEWLINE;
			else if(immediateValue != null)          // Load address from constant value
				instructionDescription = EnvironmentConstants.TAB + "la " + destTemp.toString() + ", " + immediateValue.toString() + EnvironmentConstants.NEWLINE;
			else{
				// The temp representing the address CANNOT be a Numeric
				Temp srcTemp = (Temp) srcList.elementAt(0);
				instructionDescription = EnvironmentConstants.TAB + "la " + destTemp.toString() + ", " + "(" + srcTemp.toString() + ")" + EnvironmentConstants.NEWLINE;
			}
		}
		else if(operationCode == Operation.LOAD_FROM_TEMP_OFFSET_EXP_MEM){
			
			String offSetStr = immediateValue.toString();
			int offSet = Integer.parseInt(offSetStr);
			String srcString = "";
			if(srcList.elementAt(0).getOperandType() == Operand.TEMP_TYPE)
				srcString = ((Temp) srcList.elementAt(0)).toString();
			else
				srcString = ((Numeric) srcList.elementAt(0)).toString();
			String signStr = "";
			
			String operationDesc = "";
			if(!destTemp.isInteger()){
				if(destTemp.getFloatPrecision() == AbstractType.SINGLE_PRECISION)
					operationDesc = "l.s";
				else
					operationDesc = "l.d";
			}
			else{
				operationDesc = getLoadStoreOperatorDescriptionForIntOperator(destTemp.getIntegerSize(),
						!destTemp.isSigned(), false);
			}
			
			if(offSet < 0) {
				signStr = "-";
				int val = Math.abs(offSet);

				instructionDescription = EnvironmentConstants.TAB + operationDesc + " " + 
										destTemp.toString() + ", " +  signStr + val + "("  +
										srcString+ ")" + EnvironmentConstants.NEWLINE;
			}
			else if(offSet > 0){
				instructionDescription = EnvironmentConstants.TAB + operationDesc + " " + 
										 destTemp.toString() + ", " +  offSet + "("  +
				                         srcString+ ")" + EnvironmentConstants.NEWLINE;
			}
			else
			{
				instructionDescription = EnvironmentConstants.TAB + operationDesc + " " + 
										 destTemp.toString() + ", " + "("  +
										 srcString+ ")" + EnvironmentConstants.NEWLINE;
			}
		}

		List<String> mipsInsForOperation = new ArrayList<String>();
		if(extraInsForNonCommutativeOperations != null)
			mipsInsForOperation.add(extraInsForNonCommutativeOperations);
		
		mipsInsForOperation.add(instructionDescription);
		
		//return instructionDescription;
		return mipsInsForOperation;
	}	

	public String getOperatorDescBasedOnType(String baseDescription, Temp temp){
		String oprDesc = baseDescription;
		if(temp.isInteger()){
			if(!temp.isSigned()){
				if("add".equals(baseDescription)){
					return "addu";
				}
				
				if("sub".equals(baseDescription)){
					return "subu";
				}
			}
			return oprDesc;
		}

		if(!temp.isInteger()){
			if(temp.getFloatPrecision() == AbstractType.SINGLE_PRECISION){
				oprDesc = baseDescription + ".s";    // "add.s" for single precision
			}
			else{
				oprDesc = baseDescription + ".d";    // "add.d" for single precision
			}
		}

		return oprDesc;
	}

	public Vector<String> getPrologueInstructions(){
		int stackSize = activationFrame.getStackSize();

		Vector<String> prologueList = new Vector<String>();

		// Allocate the stack frame
		prologueList.addElement(EnvironmentConstants.TAB + "# FUNCTION PROLOGUE" + EnvironmentConstants.NEWLINE);
		prologueList.addElement(EnvironmentConstants.TAB + "subu $sp, $sp, " + stackSize + EnvironmentConstants.NEWLINE);

		List<String> calleeSavedRegisters = activationFrame.getCalleeSavedRegistersUsedInFunction();

		// Start by storing callee saved registers
		int registerCount = 0;
		for(String registerUsed : calleeSavedRegisters){
			AbstractType registerType = activationFrame.getRegisterType(registerUsed);
			int offsetForCalleeSave = 0;
			try{
				offsetForCalleeSave = 
				activationFrame.getOffsetForSavedRegisterstFromStackPointer(
						ActivationFrame.CALLEE_SAVED_REGISTERS_FLAG, registerCount);
			}
			catch(InvalidStackElementAccessException excp){
				// TODO Handle this
			}

			if(registerType.isInteger()){
				
				String saveInstr = EnvironmentConstants.TAB + "sw" + " " + registerUsed + ", " + offsetForCalleeSave + "($sp)" + EnvironmentConstants.NEWLINE;
				prologueList.addElement(saveInstr);
			}
			else{
				String storeOperatorDesc = "s.s";
				int precision = registerType.getFloatPrecision();

				if(precision == AbstractType.DOUBLE_PRECISION){
					storeOperatorDesc = "s.d";
				}

				String saveInstr = EnvironmentConstants.TAB + storeOperatorDesc + " " + registerUsed + ", " + 
				offsetForCalleeSave + "($sp)" + EnvironmentConstants.NEWLINE;
				prologueList.addElement(saveInstr);
			}
			
			registerCount++;
		}
		
		// Save the return address
		int offsetForReturnAddressStore = activationFrame.getOffset(ActivationFrame.OFFSET_FOR_STORING_RETURN_ADDRESS);
		prologueList.addElement(EnvironmentConstants.TAB + "sw $ra, " +  offsetForReturnAddressStore + "($sp)" + EnvironmentConstants.NEWLINE);

		// Save the frame pointer
		int offsetForFramePointerStore = activationFrame.getOffset(ActivationFrame.OFFSET_FOR_STORING_FRAME_POINTER);
		prologueList.addElement(EnvironmentConstants.TAB + "sw $fp, " +  offsetForFramePointerStore + "($sp)" + EnvironmentConstants.NEWLINE);

		// Update the frame pointer
		prologueList.addElement(EnvironmentConstants.TAB + "addu $fp, $sp, " + stackSize + EnvironmentConstants.NEWLINE);
		
		prologueList.addElement(EnvironmentConstants.TAB + "# END FUNCTION PROLOGUE" + EnvironmentConstants.NEWLINE + EnvironmentConstants.NEWLINE);
		return prologueList;
	}

	public Vector<String> getEpilogueInstructions(boolean isMainFunction){
		Vector<String> epilogueList = new Vector<String>();
		epilogueList.addElement(EnvironmentConstants.TAB + "# FUNCTION EPILOGUE" + EnvironmentConstants.NEWLINE);

		// Restore the frame pointer
		int offsetForFramePointerStore = activationFrame.getOffset(ActivationFrame.OFFSET_FOR_STORING_FRAME_POINTER);
		epilogueList.addElement(EnvironmentConstants.TAB + "lw $fp, " + offsetForFramePointerStore + "($sp)" + EnvironmentConstants.NEWLINE);

		// Restore the return address
		int offsetForReturnAddressStore = activationFrame.getOffset(ActivationFrame.OFFSET_FOR_STORING_RETURN_ADDRESS);
		epilogueList.addElement(EnvironmentConstants.TAB + "lw $ra, " + offsetForReturnAddressStore + "($sp)" + EnvironmentConstants.NEWLINE);

		// Restore all callee saved registers
		List<String> calleeSavedRegisters = activationFrame.getCalleeSavedRegistersUsedInFunction();
		int registerCount = 0;
		for(String registerUsed : calleeSavedRegisters){
			AbstractType registerType = activationFrame.getRegisterType(registerUsed);
			int offsetForCalleeSave = 0;
			try{
				offsetForCalleeSave = 
				activationFrame.getOffsetForSavedRegisterstFromStackPointer(
						ActivationFrame.CALLEE_SAVED_REGISTERS_FLAG, registerCount);
			}
			catch(InvalidStackElementAccessException excp){
				// TODO Handle this
			}
			if(registerType.isInteger()){
				
				epilogueList.addElement(EnvironmentConstants.TAB + "lw" + " " + registerUsed + ", " +  
						offsetForCalleeSave + "($sp)" + EnvironmentConstants.NEWLINE);
			}
			else{
				String storeOperatorDesc = "s.s";
				int precision = registerType.getFloatPrecision();

				if(precision == AbstractType.DOUBLE_PRECISION)
					storeOperatorDesc = "s.d";

				epilogueList.addElement(EnvironmentConstants.TAB + storeOperatorDesc + " " + registerUsed + ", " +  
						offsetForCalleeSave + "($sp)" + EnvironmentConstants.NEWLINE);
				
			}
			
			registerCount++;
		}
		
		// Restore original stack pointer
		int stackSize = activationFrame.getStackSize();
		epilogueList.addElement(EnvironmentConstants.TAB + "addu $sp, $sp, " + stackSize + EnvironmentConstants.NEWLINE);

		if(isMainFunction){
			// Return to host- - add a syscall to exit
			epilogueList.addElement("	li $v0, 10" + EnvironmentConstants.NEWLINE);
			epilogueList.addElement("	syscall" + EnvironmentConstants.NEWLINE);
		}
		else{
			// Return to the caller
			epilogueList.addElement(EnvironmentConstants.TAB + "jr $ra " + EnvironmentConstants.NEWLINE);
		}

		epilogueList.addElement(EnvironmentConstants.TAB + "# END FUNCTION EPILOGUE" + EnvironmentConstants.NEWLINE + EnvironmentConstants.NEWLINE);
		return epilogueList;
	}

	/*
	 * If the numeric is a floating point value, we need to ensure that the immediate value should reflect
	 * a decimal. If the instruction refers to operations on floating points, an instruction like "li.s $f2, 34"
	 * should be modified to "li.s $f2, 34.0". This function effects the change of the numeric value from "34" to "34.0"
	 */
	public String getNumericValueToEmit(Numeric nm, Operation operation){
		if(!operation.instructionHasFloatingPointReferences())
			return nm.toString();


		try{
			String valStr = nm.toString();
			int val = Integer.parseInt(valStr);

			double valDbl = 1.0 * val;
			return "" + valDbl;
		}
		catch(Exception e){
			return nm.toString();
		}
	}

	public String getLoadStoreOperatorDescriptionForIntOperator(int intSize, boolean isUnsigned, boolean isForStore){
		String operatorDescription = "";
		
		if(isForStore){
			if(intSize == AbstractType.BYTE_SIZE)
				operatorDescription = "sb";
			else if(intSize == AbstractType.HALF_WORD)
				operatorDescription = "sh";
			else   // Must be word size
				operatorDescription = "sw";
			
			return operatorDescription;
		}
		
		// For load operation
		if(intSize == AbstractType.BYTE_SIZE)
			operatorDescription = "lb";
		else if(intSize == AbstractType.HALF_WORD)
			operatorDescription = "lh";
		else if(intSize == AbstractType.WORD_SIZE)
			operatorDescription = "lw";
		else     // Must be word size
			operatorDescription = "ld";
		
		if(isUnsigned && (intSize == AbstractType.BYTE_SIZE || intSize == AbstractType.HALF_WORD )){
			if(intSize == AbstractType.HALF_WORD)
				operatorDescription = "lhu";
			else
				operatorDescription = "lbu";
		}
		
		return operatorDescription;
	}

}

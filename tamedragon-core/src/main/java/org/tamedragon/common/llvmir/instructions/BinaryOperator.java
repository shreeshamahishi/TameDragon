package org.tamedragon.common.llvmir.instructions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tamedragon.common.LatticeValue;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionCreationException;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.Constant;
import org.tamedragon.common.llvmir.types.ConstantInt;
import org.tamedragon.common.llvmir.types.OpCodeID;
import org.tamedragon.common.llvmir.types.Type;
import org.tamedragon.common.llvmir.types.Value;
import org.tamedragon.common.llvmir.types.VectorType;
import org.tamedragon.common.llvmir.types.Type.TypeID;
import org.tamedragon.common.llvmir.utils.ConstantFolding;
import org.tamedragon.common.llvmir.utils.LLVMIREmitter;

/**
 * This class represents LLVM's Binary Instruction
 */
public class BinaryOperator extends Instruction{

	private BinaryOperatorID operatorID;
	private boolean noUnsignedWrap;
	private boolean noSignedWrap;
	private boolean exact;
	private Value result;

	private static final Logger LOG = LoggerFactory.getLogger(BinaryOperator.class);

	private static final String CANNOT_FOLD_BIN_INSTRUCTION = "Cannot fold binary operation with operands: ";

	public enum BinaryOperatorID implements OpCodeID{
		ADD(0, "add") ,        //  0:  Add instruction for two integers or two vectors of integer values
		FADD(1, "fadd"),        //  1:  Add instruction for two floating point or two vectors of floating point values 
		SUB(2, "sub") ,        //  2:  Subtract instruction for two integers or two vectors of integer values
		FSUB(3, "fsub") ,       //  3:  Subtract instruction for two floating point or two vectors of floating point values
		MUL(4, "mul") ,        //  4:  Multiply instruction for two integers or two vectors of integer values
		FMUL(5, "fmul"),        //  5:  Multiply instruction for two floating point or two vectors of floating point values
		UDIV(6, "udiv"),        //  6:  Unsigned integer quotient of the two integers or two vectors of integer values
		SDIV(7,"sdiv"),        //  7:  Signed integer quotient of the two integers or two vectors of integer values
		FDIV(8, "fdiv"),        //  8:  Quotient of the two floating point or two vectors of floating point values
		UREM(9, "urem"),        //  9:  Remainder from the unsigned division of two integers or two vectors of integer values 
		SREM(10, "srem"),       //  10: Remainder from the signed division of two integers or two vectors of integer values 
		FREM(11, "frem"),       //  11: Remainder from the division of two floating point or two vectors of floating point values 
		SHL(12, "shl"),        //  12: Left shift where the first operand must be an integer or vector of integer values and the second is an unsigned type 
		LSHR(13, "lshr"),       //  13: Logical right shift where the operands are integers or vector of integer values
		ASHR(14, "ashr"),       //  14: Arithmetic right shift where the operands are integers or vector of integer values
		AND(15, "and"),        //  15: Bitwise logical "and" where the operands are integers or vector of integer values
		OR(16, "or"),         //  16: Bitwise logical inclusive "or" where the operands are integers or vector of integer values
		XOR(17, "xor");        //  17: Bitwise logical exclusive "or" where the operands are integers or vector of integer values

		private int value;		
		private String strRepresentation;

		private BinaryOperatorID(int id, String strRepresentation){
			this.value = id;
			this.strRepresentation = strRepresentation;
		}

		public int getTypeIdValue(){
			return value;
		}

		public String getStrRepresentation() {
			return strRepresentation;
		}

		public boolean isAddition(){
			if(this == ADD || this == FADD)
				return true;
			return false;
		}

		public boolean isSubtraction(){
			if(this == SUB || this == FSUB)
				return true;
			return false;
		}

		public boolean isMultiplication(){
			if(this == MUL || this == FMUL)
				return true;
			return false;
		}
	}

	protected BinaryOperator(BinaryOperatorID operatorID, Type type, String name, List<Value> operandList,
			boolean noSignedWrap, boolean noUnsignedWrap, boolean exact, BasicBlock parent){
		super(InstructionID.BINARY_INST, type, operandList, parent);
		this.operatorID = operatorID;
		this.noSignedWrap = noSignedWrap;
		this.noUnsignedWrap = noUnsignedWrap;
		this.exact = exact;
		setName(name);
	}

	public InstructionID getInstructionID() {
		return InstructionID.BINARY_INST;
	}

	public BinaryOperatorID getOpCode(){
		return operatorID;
	}

	public void setOpCode(BinaryOperatorID operatorID){
		this.operatorID = operatorID;
	}

	/**
	 * This instruction is safe to use on any binary instruction and
	 * does not modify the semantics of the instruction.  If the instruction
	 * cannot be reversed (ie, it's a Div), then return false.
	 */
	public boolean  swapOperands(){
		if(!isCommutative())
			return false; 

		Value temp0 = getOperand(0);
		Value temp1 = getOperand(1);

		setOperand(0, temp1);
		setOperand(1, temp0);

		return true;
	}

	public boolean isCommutative(){
		switch (operatorID) {
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

	public void setHasNoUnsignedWrap(boolean b){
		noUnsignedWrap = b;
	}

	public void setHasNoSignedWrap(boolean b){
		noSignedWrap = b;
	}

	public void  setIsExact(boolean b){
		exact = b;
	}

	public boolean hasNoUnsignedWrap(){
		return noUnsignedWrap;
	}

	public boolean hasNoSignedWrap(){
		return noSignedWrap;
	}

	public boolean isExact(){
		return exact;
	}

	public Value getResult() {
		return result;
	}

	@Override
	public String emit(){
		String operatorDesc = operatorID.getStrRepresentation();
		String otherAttributes = " ";
		if(noUnsignedWrap){
			if(noSignedWrap)
				otherAttributes = " nuw nsw ";
			else
				otherAttributes = " nuw ";
		}
		else{
			if(noSignedWrap)
				otherAttributes = " nsw ";
		}

		// Check for the exact flag if the other two flags do not apply
		if(otherAttributes.trim().isEmpty()){
			if(exact)
				otherAttributes = " exact ";
		}

		String name = LLVMIREmitter.getInstance().getValidName(this);
		String op0Name = LLVMIREmitter.getInstance().getValidName(getOperand(0));
		String op1Name = LLVMIREmitter.getInstance().getValidName(getOperand(1));

		return name + " = " + operatorDesc + otherAttributes + getType() + " " 
		+ op0Name + ", " + op1Name;
	}

	public static BinaryOperator create(BinaryOperatorID binOpId, Type type, String name,
			Value firstOpr, Value secondOpr, boolean noSignedWrap,
			boolean noUnsignedWrap, boolean exact, BasicBlock parent) throws InstructionCreationException {

		BinaryOperator binaryOpr = null;

		// The binary operator ID must be a valid one
		if(binOpId == null){
			throw new InstructionCreationException(InstructionCreationException.BINARY_OPERATOR_ID_IS_NULL);
		}

		if(type == null){
			throw new InstructionCreationException(InstructionCreationException.RESULT_TYPE_CANNOT_BE_NULL);
		}

		if(type != firstOpr.getType()){
			throw new InstructionCreationException(InstructionCreationException.RESULT_VALUE_TYPE_DIFFERS_FROM_OPERANDS_TYPE);
		}

		switch (binOpId) {
		case ADD:
		case SUB:
		case MUL:
		case UDIV:
		case SDIV:
		case UREM:
		case SREM:
		case SHL:
		case LSHR:
		case ASHR:
		case AND:
		case OR:
		case XOR:
			binaryOpr = createBinOprForIntOperands(binOpId, type, name,
					firstOpr, secondOpr, noSignedWrap, noUnsignedWrap, exact,
					parent);
			break;
		case FADD:
		case FSUB:
		case FMUL:
		case FDIV:
		case FREM:
			binaryOpr = createBinOprForFloatingPointOperands(binOpId, type,
					name, firstOpr, secondOpr, noSignedWrap, noUnsignedWrap,
					exact, parent);

		}

		return binaryOpr;
	}

	public BinaryOperatorID getOperatorID() {
		return operatorID;
	}

	private static BinaryOperator createBinOprForIntOperands(BinaryOperatorID binOpId, 
			Type type, String name, Value firstOpr, Value secondOpr, boolean noSignedWrap,
			boolean noUnsignedWrap, boolean exact, BasicBlock parent) throws InstructionCreationException{

		BinaryOperator binaryOpr = null;
		boolean areIntOperands = false;
		Type firstOprType = firstOpr.getType();
		Type secondOprType = secondOpr.getType();

		// Check if both are integers
		if(firstOprType.getTypeId() == TypeID.INTEGER_ID) {
			if(secondOprType.getTypeId() == TypeID.INTEGER_ID){

				if(firstOprType != secondOprType)
					throw new InstructionCreationException(InstructionCreationException.INTEGER_OPERANDS_MUST_BE_SAME_WIDTH);

				areIntOperands = true;
			}
			else
				throw new InstructionCreationException(InstructionCreationException.OPERANDS_MUST_BE_INTEGERS);
		}

		if(!areIntOperands){
			// Not integer operands, check if they are vectors of integer types
			if(firstOprType.getTypeId() == TypeID.VECTOR_ID) {
				if(secondOprType.getTypeId() == TypeID.VECTOR_ID){
					if (firstOprType != secondOprType) {
						throw new InstructionCreationException(InstructionCreationException.VECTOR_OPERANDS_MUST_BE_SIMILAR);
					}
				}
				else {
					throw new InstructionCreationException(InstructionCreationException.OPERANDS_MUST_BE_INTEGERS);
				}
			}
			else{
				// Neither integers nor vector of integer values
				throw new InstructionCreationException(InstructionCreationException.OPERANDS_MUST_BE_INTEGERS);
			}
		}

		// Operands and result value are OK, check if the other flags are OK
		if(exact && (binOpId == BinaryOperatorID.ADD || binOpId == BinaryOperatorID.SUB  
				||binOpId == BinaryOperatorID.MUL || binOpId == BinaryOperatorID.UREM
				|| binOpId == BinaryOperatorID.SREM || binOpId == BinaryOperatorID.SHL
				|| binOpId == BinaryOperatorID.AND || binOpId == BinaryOperatorID.OR
				|| binOpId == BinaryOperatorID.XOR)){
			throw new InstructionCreationException(InstructionCreationException.EXACT_CANNOT_BE_USED_HERE, binOpId.getStrRepresentation());
		}
		if((noSignedWrap || noUnsignedWrap) && (binOpId == BinaryOperatorID.UDIV || binOpId == BinaryOperatorID.SDIV
				|| binOpId == BinaryOperatorID.UREM || binOpId == BinaryOperatorID.SREM
				|| binOpId == BinaryOperatorID.LSHR || binOpId == BinaryOperatorID.ASHR
				|| binOpId == BinaryOperatorID.OR || binOpId == BinaryOperatorID.XOR)){

			throw new InstructionCreationException(InstructionCreationException.WRAP_FLAG_CANNOT_BE_USED_HERE, 
					binOpId.getStrRepresentation());
		}

		// All the data passed is OK, create the BinaryOperator object and return
		List<Value> operandList = new ArrayList<Value>();
		operandList.add(firstOpr);
		operandList.add(secondOpr);
		binaryOpr = new BinaryOperator(binOpId, type, name, operandList, noSignedWrap, noUnsignedWrap, exact, parent);

		return binaryOpr;

	}

	private static BinaryOperator createBinOprForFloatingPointOperands(BinaryOperatorID binOpId, 
			Type type, String name, Value firstOpr, Value secondOpr, boolean noSignedWrap,
			boolean noUnsignedWrap, boolean exact, BasicBlock parent) throws InstructionCreationException{

		BinaryOperator binaryOpr = null;

		Type firstOprType = firstOpr.getType();
		Type secondOprType = secondOpr.getType();

		if(!(firstOprType.isFloatingPointType() && secondOprType.isFloatingPointType())){
			// Not floating types; check if they are vector of floating point values
			if(!(firstOprType.isVectorType() && secondOprType.isVectorType()))
				throw new InstructionCreationException(InstructionCreationException.OPERANDS_MUST_BE_FLOATING_POINT_TYPES);

			Type containedType1 = ((VectorType) firstOprType).getContainedType();
			Type containedType2 = ((VectorType) secondOprType).getContainedType();

			if(!(containedType1.isFloatingPointType() && containedType2.isFloatingPointType()))
				throw new InstructionCreationException(InstructionCreationException.OPERANDS_MUST_BE_FLOATING_POINT_TYPES); 

			// Both are vectors of floating point types
			if(firstOprType != secondOprType)
				throw new InstructionCreationException(InstructionCreationException.VECTOR_OPERANDS_MUST_BE_SIMILAR);
		}
		else{
			// Check if both represent the same floating point type
			if(firstOprType != secondOprType) 
				throw new InstructionCreationException(InstructionCreationException.OPERANDS_MUST_BE_FLOATING_POINT_TYPES);
		}

		// Operands are OK, check if the other flags are OK
		if(exact)
			throw new InstructionCreationException(InstructionCreationException.EXACT_CANNOT_BE_USED_FOR_FLOATING_POINT_OPERANDS);

		if(noSignedWrap || noUnsignedWrap)
			throw new InstructionCreationException(InstructionCreationException.WRAP_FLAG_CANNOT_BE_USED_FOR_FLOATING_POINT_OPERANDS);

		// All the data passed is OK, create the BinaryOperator object and return
		List<Value> operandList = new ArrayList<Value>();
		operandList.add(firstOpr);
		operandList.add(secondOpr);
		binaryOpr = new BinaryOperator(binOpId, type, name, operandList, noSignedWrap, noUnsignedWrap, exact, parent);

		return binaryOpr;
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
		LatticeValue newLatticeValue = null;

		try{
			if(currentLatticeValue.getType() == LatticeValue.OVERDEFINED)
				return currentLatticeValue;

			Value firstOperandValue = getOperand(0);
			Value secondOperandValue = getOperand(1);

			LatticeValue operand1State = tempVsLatticeValue.get(firstOperandValue);
			LatticeValue operand2State = tempVsLatticeValue.get(secondOperandValue);

			if(operand1State.getType() == LatticeValue.CONSTANT &&
					operand2State.getType() == LatticeValue.CONSTANT){
				newLatticeValue = new LatticeValue();
				Constant constantValue = Constant.getConstant(getOperatorID(),
						operand1State.getConstantValue(), operand2State.getConstantValue());
				newLatticeValue.setConstantValue(constantValue);
				return newLatticeValue;
			}

			// If something is undef, wait for it to resolve.
			if (!(operand1State.getType() == LatticeValue.OVERDEFINED) && 
					!(operand2State.getType() == LatticeValue.OVERDEFINED))
				return currentLatticeValue;

			// Otherwise, one of the operands is overdefined.  Try to produce something
			// better than overdefined with some tricks.
			boolean firstOperandIsOverdefined = true;
			LatticeValue nonOverDefinedValue = null;

			if (operand1State.getType() != LatticeValue.OVERDEFINED){
				nonOverDefinedValue = operand1State;
				firstOperandIsOverdefined = false;
			}
			else if (operand2State.getType() != LatticeValue.OVERDEFINED)
				nonOverDefinedValue = operand2State;

			if(nonOverDefinedValue == null){
				// Both are overdefined, nothing much can be done here. Update 
				// the lattice state to overdefined and return.
				newLatticeValue = new LatticeValue();
				newLatticeValue.setType(LatticeValue.OVERDEFINED);
				return newLatticeValue;
			}

			// One of the operands is overdefined.

			BinaryOperatorID opCode = getOpCode();

			// If this is a multiply operation, and the non-overdefined value is zero,
			// the result is zero
			if(opCode == BinaryOperatorID.MUL || opCode == BinaryOperatorID.FMUL){
				// This is a multiply operation; if one of the non-overdefined value is zero,
				// we can return a constant 0.
				if(nonOverDefinedValue.getType() == LatticeValue.CONSTANT){
					Constant constVal = nonOverDefinedValue.getConstantValue();
					if(constVal.isZero()){
						newLatticeValue = new LatticeValue();
						Constant constantValue = Constant.getZero(constVal);
						newLatticeValue.setConstantValue(constantValue);
						return newLatticeValue;

					}
				}
			}

			// If this is a division operation, and the non-overdefined value is zero,
			// and it is also the first operand, the result is zero 
			if(opCode == BinaryOperatorID.UDIV || opCode == BinaryOperatorID.SDIV ||
					opCode == BinaryOperatorID.FDIV || opCode == BinaryOperatorID.UREM ||
					opCode == BinaryOperatorID.SREM){
				if(!firstOperandIsOverdefined && operand1State.getType() == LatticeValue.CONSTANT){
					Constant constVal = operand1State.getConstantValue();
					if(constVal.isZero()){
						newLatticeValue = new LatticeValue();
						newLatticeValue.setConstantValue(constVal);
						return newLatticeValue;

					}
				}

			}

			// If this is an AND or OR with 0 or -1, it doesn't matter that the other
			// operand is overdefined.

			if (opCode == BinaryOperatorID.AND || opCode == BinaryOperatorID.OR) {
				if (nonOverDefinedValue.getType() == LatticeValue.UNDEFINED) {
					// Could annihilate value.
					// TODO Implement this
					//				if (opCode == BinaryOperatorID.AND)
					//					markConstant(IV, &I, Constant::getNullValue(I.getType()));
					//				else if (VectorType *PT = dyn_cast<VectorType>(I.getType()))
					//					markConstant(IV, &I, Constant::getAllOnesValue(PT));
					//				else
					//					markConstant(IV, &I,
					//							Constant::getAllOnesValue(I.getType()));
					//				return;
				}

				if(opCode == BinaryOperatorID.AND) {
					// X and 0 = 0
					if (nonOverDefinedValue.getConstantValue().isZero()){
						newLatticeValue = new LatticeValue();
						newLatticeValue.setConstantValue(nonOverDefinedValue.getConstantValue());
						return newLatticeValue;
					}
				} 
				else {
					// Binary Opcode is "OR"
					Constant constantValue = nonOverDefinedValue.getConstantValue();
					ConstantInt constInt = (ConstantInt) constantValue;
					if (constInt.isAllOnesValue())   {  // X or -1 = -1
						newLatticeValue = new LatticeValue();
						newLatticeValue.setConstantValue(nonOverDefinedValue.getConstantValue());
						return newLatticeValue;
					}
				}
			}


			// Some other combination of operations; we will need mark this as overdefined.
			newLatticeValue = new LatticeValue();
			newLatticeValue.setType(LatticeValue.OVERDEFINED);
			return newLatticeValue;
		}
		catch(Exception e){
			LOG.error("Visit for SCCP failed for this binary operator instruction: " + e.getMessage());
			System.exit(-1);
		}

		return newLatticeValue;
	}

	@Override
	public boolean canBeHoistedOrSank() {
		// Can be moved out of a loop
		return true;
	}

	public static BinaryOperatorID getBinOpIDFromString(String str){
		if(str.equals("add"))
			return BinaryOperatorID.ADD;
		else if(str.equals("fadd"))
			return BinaryOperatorID.FADD;
		else if(str.equals("sub"))
			return BinaryOperatorID.SUB;
		else if(str.equals("fsub"))
			return BinaryOperatorID.FSUB;
		else if(str.equals("mul"))
			return BinaryOperatorID.MUL;
		else if(str.equals("fmul"))
			return BinaryOperatorID.FMUL;
		else if(str.equals("udiv"))
			return BinaryOperatorID.UDIV;
		else if(str.equals("sdiv"))
			return BinaryOperatorID.SDIV;
		else if(str.equals("fdiv"))
			return BinaryOperatorID.FDIV;
		else if(str.equals("urem"))
			return BinaryOperatorID.UREM;
		else if(str.equals("srem"))
			return BinaryOperatorID.SREM;
		else if(str.equals("frem"))
			return BinaryOperatorID.FREM;
		else if(str.equals("shl"))
			return BinaryOperatorID.SHL;
		else if(str.equals("lshr"))
			return BinaryOperatorID.LSHR;
		else if(str.equals("ashr"))
			return BinaryOperatorID.ASHR;
		else if(str.equals("and"))
			return BinaryOperatorID.AND;
		else if(str.equals("or"))
			return BinaryOperatorID.OR;
		else
			return BinaryOperatorID.XOR;
	}

	@Override
	public Constant foldIfPossible() {
		// If both constants are constants, we can fold them
		Value operand1 = getOperand(0); Value operand2 = getOperand(1);
		if(!(operand1.isAConstant() && operand2.isAConstant())){
			return null;
		}

		Constant constOperand1 = (Constant) operand1;
		Constant constOperand2 = (Constant) operand2;
		try{
			return ConstantFolding.constantFoldBinaryInstruction(operatorID, constOperand1, constOperand2);
		}
		catch(Exception e){
			e.printStackTrace();
			LOG.error(CANNOT_FOLD_BIN_INSTRUCTION + constOperand1.toString() + " and " + constOperand2.toString());
			return null;
		}
	}
}
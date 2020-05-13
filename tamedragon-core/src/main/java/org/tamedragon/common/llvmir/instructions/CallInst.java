package org.tamedragon.common.llvmir.instructions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;

import org.tamedragon.common.LatticeValue;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionCreationException;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionUpdateException;
import org.tamedragon.common.llvmir.types.ArrayType;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.Constant;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.FunctionType;
import org.tamedragon.common.llvmir.types.PointerType;
import org.tamedragon.common.llvmir.types.Type;
import org.tamedragon.common.llvmir.types.Value;
import org.tamedragon.common.llvmir.utils.LLVMIREmitter;

/**
 * CallInst - This class represents a function call, abstracting a target
 * machine's calling convention.  This class uses low bit of the SubClassData
 * field to indicate whether or not this is a tail call.  The rest of the bits
 * hold the calling convention of the call.
 *
 */
public class CallInst extends Instruction {

	private boolean tailCall;
	private int funcAttributeList;
	private int retAttributeList;
	private CallingConv callingConvention;
	private boolean isPointerToFunc = false;
	private PointerType pointerToFuncType;

	protected CallInst(Type type, List<Value> operandList, String name, boolean tailCall, BasicBlock parent) {
		super(InstructionID.CALL_INST, type,operandList, parent);
		setName(name);
		this.tailCall = tailCall;
	}

	public static CallInst create(Value funcValue, List<Value> args, String name,
			boolean tailCall, boolean isPointerToFunction, BasicBlock parent)
	throws InstructionCreationException{

		if(funcValue == null)
			throw new InstructionCreationException(InstructionCreationException.FUNC_VALUE_CANNOT_BE_NULL);

		if(args == null)
			throw new InstructionCreationException(InstructionCreationException.ARGS_SHOULD_NOT_BE_NULL);

		// Ensure that the value passed as a function is pointer to a function or the Function itself 
		Type funcValueType = funcValue.getType();
		if(!funcValueType.isPointerType() && !funcValueType.isFunctionType())
			throw new InstructionCreationException(InstructionCreationException.INVALID_TYPE_FOR_CALL_INSTR_SHOULD_BE_EITHER_PTR_TO_FUNC_OR_FUNC_TYPE);

		FunctionType funcType = null;

		if(funcValueType.isPointerType()){
			PointerType ptrType = (PointerType) funcValueType;

			Type containedType = ptrType.getContainedType();
			if(!containedType.isFunctionType())
				throw new InstructionCreationException(InstructionCreationException.IF_TYPE_IS_PTR_TO_FUNC_CONTAINED_TYPE_SHOULD_BE_FUNC_TYPE);

			funcType = (FunctionType) containedType;
		}
		else
			funcType = (FunctionType) funcValueType;

		int numArgs = args.size();
		int numFuncParams = funcType.getNumParams();

		if(funcType.isVarArg()){
			if(numArgs < numFuncParams)
				throw new InstructionCreationException(InstructionCreationException.NOS_OF_ARGS_CANNOT_BE_LESS_THAN_NOS_OF_PARAMS);
		}
		else{
			if(numFuncParams != numArgs)   // "NumOperands not set up?"
				throw new InstructionCreationException(InstructionCreationException.NOS_OF_ARGS_SHOULD_BE_EQUAL_TO_NOS_OF_PARAMS);
		}

		for(int i = 0; i != numArgs; i++){
			if(i < numFuncParams){
				Type funcParamType = funcType.getParamType(i);
				Type argType = args.get(i).getType();
				if(funcParamType != argType){
					boolean isTypeSame = checkTypes(funcParamType, argType);
					if(!isTypeSame)
						throw new InstructionCreationException(InstructionCreationException.FUNC_PARAM_TYPE_CANNOT_BE_DIFFERENT_THAN_ARG_TYPE);
				}
			}
		}

		List<Value> operandList = new ArrayList<Value>();
		operandList.add(funcValue);
		operandList.addAll(args);

		CallInst callInst = new CallInst(funcType.getReturnType(), operandList, name, tailCall, parent);

		if(isPointerToFunction){
			callInst.setPointerToFunc(true);
			callInst.setPointerToFuncType((PointerType) funcValueType);
		}

		return callInst;
	}

	public static CallInst create(Value valueFunc, String name, boolean tailCall, boolean isPointerToFunction, BasicBlock parent) 
	throws InstructionCreationException {

		// Ensure that the value passed as a function is pointer to a function or the Function itself 
		Type funcValueType = valueFunc.getType();
		if(!funcValueType.isPointerType() && !funcValueType.isFunctionType())
			throw new InstructionCreationException(InstructionCreationException.INVALID_TYPE_FOR_CALL_INSTR_SHOULD_BE_EITHER_PTR_TO_FUNC_OR_FUNC_TYPE);

		FunctionType funcType = null;

		if(funcValueType.isPointerType()){
			PointerType ptrType = (PointerType) funcValueType;

			Type containedType = ptrType.getContainedType();
			if(!containedType.isFunctionType())
				throw new InstructionCreationException(InstructionCreationException.IF_TYPE_IS_PTR_TO_FUNC_CONTAINED_TYPE_SHOULD_BE_FUNC_TYPE);

			funcType = (FunctionType) containedType;
		}
		else
			funcType = (FunctionType) funcValueType;

		if(funcType.getNumParams() != 0)  // "Calling a function with bad signature");
			throw new InstructionCreationException(InstructionCreationException.NOS_OF_ARGS_CANNOT_BE_LESS_THAN_NOS_OF_PARAMS);

		List<Value> operandList = new ArrayList<Value>();
		operandList.add(valueFunc);

		CallInst callInst = new CallInst(funcType.getReturnType(), operandList, name, tailCall, parent);

		if(isPointerToFunction){
			callInst.setPointerToFunc(true);
			callInst.setPointerToFuncType((PointerType)funcValueType);
		}

		return callInst;
	}


	private static boolean checkTypes(Type funcParamType, Type argType) {
		boolean isTypeSame = false;
		PointerType pointerType = null;
		ArrayType arrayType = null;

		if(funcParamType.isPointerType() && argType.isArrayType()){
			pointerType = (PointerType)funcParamType;
			arrayType = (ArrayType)argType;
		}
		else if(argType.isPointerType() && funcParamType.isArrayType()){
			pointerType = (PointerType)argType;
			arrayType = (ArrayType)funcParamType;
		}

		if(pointerType != null && arrayType != null){
			Type ptrContainedType = pointerType.getContainedType();
			Type arrContainedType = arrayType.getContainedType();

			int ptrCount = 0;
			int arrCount = 0;

			while(ptrContainedType.isPointerType()){
				ptrCount++;
				ptrContainedType = ((PointerType)ptrContainedType).getContainedType();
			}

			while(arrContainedType.isArrayType()){
				arrCount++;
				arrContainedType = ((ArrayType)arrContainedType).getContainedType();
			}

			if(ptrContainedType == arrContainedType && ptrCount == arrCount)
				isTypeSame = true;
		}

		return isTypeSame;
	}

	@Override
	public String emit(){
		String description = "";
		String returnTypeStr = getType().toString();

		if(!returnTypeStr.equals("void")){
			description = LLVMIREmitter.getInstance().getValidName(this) + " =";
		}

		if(tailCall)
			description += " tail";

		if(!returnTypeStr.equals("void"))
			description += " call";
		else
			description += "call";

		if(getCallingConv() != null)
			description += " " + getCallingConv();
		int attrs = getRet_attrs();

		if(attrs != 0)
			description += " " + LLVMUtility.getAttributeAsString(attrs);

		description += " " + returnTypeStr;

		FunctionType functionType = null;
		PointerType pointerType = (PointerType)getOperand(0).getType();
		functionType = (FunctionType)pointerType.getContainedType();

		boolean hasEllipses = functionType.isVarArg();
		if(hasEllipses){
			// The function has variable arguments
			description += " (";
			int numberOfParams = functionType.getNumParams();
			for(int i = 0; i < numberOfParams; i++){
				Type type = functionType.getParamType(i);
				description += type.toString() + ", ";
			}
			description += "...)*";
		}

		String funcName = LLVMIREmitter.getInstance().getValidName(getOperand(0));
		description += " " + funcName + "(";
		if(getNumOperands() >= 2){
			for(int i = 1; i < getNumOperands(); i++){
				Value val = getOperand(i);
				
				if(val.toString().equals("false")) {
					System.out.println("WAIT HERE");
				}
				
				String name = LLVMIREmitter.getInstance().getValidName(val);

				if(val instanceof Constant){
					if(i < (getNumOperands() - 1))
						description += val.toString() + ", ";
					else
						description += val.toString();
				}
				else{
					if(i < (getNumOperands() - 1))
						description += val.getType() + " " + name + ", ";
					else
						description += val.getType() + " " + name;
				}
			}
		}

		description += ")";
		attrs = new Integer(getFuncAttributes());
		if(attrs > 0){
			List<Integer> attrList = Attributes.getAttributesAsList(attrs);
			for(int i = 0; i < attrList.size(); i++){
				int attr = attrList.get(i).intValue();
				if(i < (attrList.size()-1))
					description += " " + Attributes.getAttributeAsString(attr) + ","; 
				else
					description += " " + Attributes.getAttributeAsString(attr); 
			}
		}
		return description;
	}

	public int getRet_attrs() {
		return retAttributeList;
	}

	public void addReturnAttributes(int retAttrs) throws InstructionUpdateException {		
		validateReturnAttrs(retAttrs);
		retAttributeList |= retAttrs;
	}

	public void removeReturnAttributes(int retAttrs) throws InstructionUpdateException {
		validateReturnAttrs(retAttrs);
		retAttributeList &= ~retAttrs;
	}

	private void validateReturnAttrs(int retAttrs) throws InstructionUpdateException{
		Value funcVal = this.getOperand(0);
		FunctionType functionType = (FunctionType)funcVal.getType();
		Type retType = functionType.getReturnType();
		if(retType.isVoidType())
			throw new InstructionUpdateException(InstructionUpdateException.CANNOT_SET_A_RET_ATTR_TO_FUNC_RET_VOID);

		// The attribute passed can only be zero or a power of 2
		if(!(retAttrs == 0 || LLVMUtility.isPowerOf2_32(retAttrs)))
			throw new InstructionUpdateException(InstructionUpdateException.ATTRIBUTE_IS_NOT_A_POWER_OF_TWO);

		if(!(retAttrs == Attributes.ZExt || retAttrs == Attributes.SExt
				|| retAttrs == Attributes.InReg))
			throw new InstructionUpdateException(InstructionUpdateException.INVALID_ATTR_FOR_FUNCTION_RET_TYPE);		
	}

	public boolean isTailCall() {  
		return tailCall;  
	}

	public void setTailCall(boolean tailCall) { 
		this.tailCall = tailCall; 
	}

	public int getNumArgOperands() { 
		return getNumOperands() - 1; 
	}

	public Value getArgOperand(int i) {
		return getOperand(i); 
	}

	public void setArgOperand(int i, Value value) {
		setOperand(i, value); 
	}

	public CallingConv getCallingConv(){
		return callingConvention;
	}

	public void setCallingConv(CallingConv callingConv) {
		callingConvention = callingConv;
	}

	public int getFuncAttributes(){
		return funcAttributeList; 
	}

	public int getReturnAttributes(){
		return retAttributeList; 
	}

	/**
	 * Adds the attribute to the list of attributes of the function.
	 * */
	public void addFuncAttribute(int attr) throws InstructionUpdateException {
		validateFunctionAttribute(attr);
		funcAttributeList |= attr;
	}

	/**
	 * Removes the attribute from the list of attributes.
	 * */
	public void removeFuncAttribute(int attr) throws InstructionUpdateException{
		validateFunctionAttribute(attr);
		funcAttributeList &= ~attr;
	}

	private void validateFunctionAttribute(int attr) throws InstructionUpdateException{

		// The attribute passed can only be zero or a power of 2
		if(!(attr == 0 || LLVMUtility.isPowerOf2_32(attr)))
			throw new InstructionUpdateException(InstructionUpdateException.ATTRIBUTE_IS_NOT_A_POWER_OF_TWO);

		// Can remove only 'noreturn', 'nounwind', 'readonly' and 'readnone'
		if(!(attr == Attributes.NoReturn || attr == Attributes.NoUnwind
				|| attr == Attributes.ReadOnly || attr == Attributes.ReadNone))
			throw new InstructionUpdateException(InstructionUpdateException.INVALID_ATTR_FOR_FUNCTION_IN_CALL_INSTR);
	}

	/**
	 * Determines whether the call or the callee has the given attribute.
	 * */
	public boolean paramHasAttr(int attr) {

		if(!(attr == 0 || LLVMUtility.isPowerOf2_32(attr)))
			return false;

		if ((funcAttributeList & attr) != 0)
			return true;

		return false;
	}

	public boolean canReturnTwice(){
		return paramHasAttr(Attributes.ReturnsTwice);
	}

	public boolean doesNotAccessMemory(){
		return paramHasAttr(Attributes.ReadNone);
	}

	public boolean onlyReadsMemory(){
		return doesNotAccessMemory() || paramHasAttr(Attributes.ReadOnly);
	}

	/*	
	 public void setIsNoInline(boolean noInlineFlag) {
		try{
			if (noInlineFlag)
				addFuncAttribute(Attributes.NoInline);
			else 
				removeFuncAttribute(Attributes.NoInline);
		}
		catch(InstructionUpdateException iue) {
			// Should not happen
		}
	}
	
	 public void setCanReturnTwice(boolean flag) {
		try{
			if (flag)
				addFuncAttribute(Attributes.ReturnsTwice);
			else 
				removeFuncAttribute(Attributes.ReturnsTwice);
		}
		catch(InstructionUpdateException iue) {
			// Should not happen
		}
	} 
	
	public void setOnlyReadsMemory(boolean onlyReadsMemoryFlag) {

		try{
			if (onlyReadsMemoryFlag)
				addFuncAttribute(Attributes.ReadOnly);
			else 
				removeFuncAttribute(Attributes.ReadOnly | Attributes.ReadNone);
		}
		catch(InstructionUpdateException iue) {
			// Should not happen
		}
	}

	public void setDoesNotReturn(boolean doesNotReturnFlag) {
		try{
			if (doesNotReturnFlag)
				addFuncAttribute(Attributes.NoReturn);
			else 
				removeFuncAttribute(Attributes.NoReturn);
		}
		catch(InstructionUpdateException iue) {
			// Should not happen
		}
	}
	
	public void setDoesNotAccessMemory(boolean doesNotAccessMemoryFlag) {
		try{
			if (doesNotAccessMemoryFlag)
				addFuncAttribute(Attributes.ReadNone);
			else 
				removeFuncAttribute(Attributes.ReadNone);
		}
		catch(InstructionUpdateException iue) {
			// Should not happen
		}
	
		public void setDoesNotThrow(boolean doesNotThrowFlag) {
			try{
				if (doesNotThrowFlag)
					addFuncAttribute(Attributes.NoUnwind);
				else 
					removeFuncAttribute(Attributes.NoUnwind);
			}
			catch(InstructionUpdateException iue) {
			// Should not happen
			iue.printStackTrace();
			}
		}
	}
	*/

	public boolean doesNotReturn(){ 
		return paramHasAttr(Attributes.NoReturn);
	}

	public boolean doesNotThrow(){ 
		return paramHasAttr(Attributes.NoUnwind); 
	}

	public boolean hasStructRetAttr(){
		return paramHasAttr(Attributes.StructRet);
	}

	
	public boolean hasByValArgument(){
		return false;
	}

	public Function getCalledFunction(){
		return (Function) getOperand(0);
	}
	
	public Value getCalledValue(){ return getOperand(0); }

	public void setCalledFunction(Value function) {
		setOperand(1, function);
	}

	private void setPointerToFunc(boolean isPointerToFunc) {
		this.isPointerToFunc = isPointerToFunc;
	}


	private void setPointerToFuncType(PointerType pointerToFunc) {
		this.pointerToFuncType = pointerToFunc;
	}

	private PointerType getPointerToFuncType() {
		return pointerToFuncType;
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
		return true;
	}

	@Override
	public LatticeValue visitForSCCP(LatticeValue latticeValueBeforeModelling,
			HashSet<BasicBlock> unreachableNodes,
			HashMap<Value, LatticeValue> tempVsLatticeValue,
			Vector<Value> ssaWorkList,
			Vector<BasicBlock> cfgWorkList) {
		// TODO Implement this later; for now, change to overdefined.
		LatticeValue latticeValueAfterModelling = new LatticeValue();
		latticeValueAfterModelling.setType(LatticeValue.OVERDEFINED);

		return latticeValueAfterModelling;
	}

	@Override
	public boolean canBeHoistedOrSank() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Constant foldIfPossible() {
		// Cannot be constant folded
		return null;
	}
}

package org.tamedragon.assemblyabstractions.constructs;

import java.util.List;

import org.tamedragon.common.llvmir.instructions.CallingConv;
import org.tamedragon.common.llvmir.types.PointerType;
import org.tamedragon.common.llvmir.types.Type;

/**
 * This class holds the information needed to create LLVM's Call Expressions
 * @author ipsg
 *
 */
public class IRTreeCallExp extends IRTreeExp{
	
	private Type returnType;
	private List<IRTree> paramList;
	private List<Type> formalParamTypes;
	private IRTreeMemory functionMemory;
	private boolean tailCall;
	private CallingConv callingConvention = CallingConv.C;
	private boolean isPointerToFunc;
	private PointerType pointerToFuncType;
	private boolean hasEllipses;
	private boolean isOnlyReadsMemory;
	private int func_attrs;
	private int return_attrs;
	
	public IRTreeCallExp(Type returnType, List<IRTree> paramList, IRTreeMemory functionMemory, List<Type> formalParamTypes, boolean hasEllipses, boolean isOnlyReadsMemory, int return_attrs, int func_attrs){
		this.returnType = returnType;
		this.paramList = paramList;
		this.functionMemory = functionMemory;
		this.formalParamTypes = formalParamTypes;
		this.hasEllipses = hasEllipses;
		this.isOnlyReadsMemory = isOnlyReadsMemory;
		this.func_attrs = func_attrs;
		this.return_attrs = return_attrs;
		this.expType = TreeNodeExpType.CALL_EXP;
	}

	public Type getReturnType() {
		return returnType;
	}

	public List<IRTree> getParamList() {
		return paramList;
	}

	public void setParamList(List<IRTree> paramList) {
		this.paramList = paramList;
	}

	public List<Type> getFormalParamTypes() {
		return formalParamTypes;
	}

	public boolean isTailCall() {
		return tailCall;
	}

	public void setTailCall(boolean tailCall) {
		this.tailCall = tailCall;
	}

	public boolean isOnlyReadsMemory() {
		return isOnlyReadsMemory;
	}

	public void setOnlyReadsMemory(boolean isOnlyReadsMemory) {
		this.isOnlyReadsMemory = isOnlyReadsMemory;
	}

	public CallingConv getCallingConvention() {
		return callingConvention;
	}

	public void setCallingConvention(CallingConv callingConvention) {
		this.callingConvention = callingConvention;
	}

	public IRTreeMemory getFunctionMemory() {
		return functionMemory;
	}

	public int getFunc_attrs() {
		return func_attrs;
	}

	public void setFunc_attrs(int funcAttrs) {
		func_attrs = funcAttrs;
	}

	public int getReturn_attrs() {
		return return_attrs;
	}

	public void setReturn_attrs(int returnAttrs) {
		return_attrs = returnAttrs;
	}

	public boolean isHasEllipses() {
		return hasEllipses;
	}

	public void setHasEllipses(boolean hasEllipses) {
		this.hasEllipses = hasEllipses;
	}

	public boolean isPointerToFunc() {
		return isPointerToFunc;
	}

	public void setPointerToFunc(boolean isPointerToFunc) {
		this.isPointerToFunc = isPointerToFunc;
	}

	public PointerType getPointerToFuncType() {
		return pointerToFuncType;
	}

	public void setPointerToFuncType(PointerType pointerToFuncType) {
		this.pointerToFuncType = pointerToFuncType;
	}
}

package org.tamedragon.assemblyabstractions.constructs;

import java.util.List;

import org.tamedragon.common.llvmir.types.Type;
import org.tamedragon.common.llvmir.types.Value;

/**
 * This class holds information needed to create LLVM's Function
 * @author ipsg
 *
 */
public class IRTreeFunction extends IRTreeStatement{
	
	private String funcName;
	private Type returnType;
	private List<Value> parameters;
	private List<List<Integer>> paramAttrs;
	private List<Integer> funcAttributeList; // parameter attributes for call
	private List<Integer> retAttributeList;
	private boolean isDeclaration;
	private boolean endsWithEllipses;
	private boolean isStatic;
	
	public IRTreeFunction(String funcName, Type returnType, List<Value> parameters, List<List<Integer>> paramAttrs, List<Integer> funcAttributeList, List<Integer> retAttributeList, boolean isIntrinsic, boolean endsWithEllipses, boolean isStatic){
		this.funcName = funcName;
		this.returnType = returnType;
		this.parameters = parameters;
		this.paramAttrs = paramAttrs;
		this.funcAttributeList = funcAttributeList;
		this.retAttributeList = retAttributeList;
		this.isDeclaration = isIntrinsic;
		this.endsWithEllipses = endsWithEllipses;
		this.isStatic = isStatic;
		this.statementType = TreeStatementType.FUNC;
	}

	public String getFuncName() {
		return funcName;
	}
	
	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	public boolean isStatic() {
		return isStatic;
	}

	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}

	public boolean isDeclaration() {
		return isDeclaration;
	}

	public void setDeclaration(boolean isDeclaration) {
		this.isDeclaration = isDeclaration;
	}

	public Type getReturnType() {
		return returnType;
	}
	
	public List<Integer> getFuncAttributeList() {
		return funcAttributeList;
	}
	
	public List<List<Integer>> getParamAttrs() {
		return paramAttrs;
	}

	public List<Integer> getRetAttributeList() {
		return retAttributeList;
	}

	public void setReturnType(Type returnType) {
		this.returnType = returnType;
	}

	public boolean isEndsWithEllipses() {
		return endsWithEllipses;
	}

	public void setEndsWithEllipses(boolean endsWithEllipses) {
		this.endsWithEllipses = endsWithEllipses;
	}

	public List<Value> getParameters() {
		return parameters;
	}

	public void setParameters(List<Value> parameters) {
		this.parameters = parameters;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}
}

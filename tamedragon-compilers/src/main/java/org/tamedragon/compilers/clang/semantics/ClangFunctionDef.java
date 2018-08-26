package org.tamedragon.compilers.clang.semantics;

import java.util.HashMap;
import java.util.Vector;

import org.tamedragon.assemblyabstractions.ActivationFrame;
import org.tamedragon.assemblyabstractions.constructs.IRTree;
import org.tamedragon.common.Label;
import org.tamedragon.common.TargetDataType;

public class ClangFunctionDef implements ClangTransUnit{
	private String name;

	private IRTree functionBody;     // The translated body of the function

	//private Vector<AssemCallExp> invocations;    // A list of function levels that represents the functions that this
								                 // function calls.
	private int numIncomingParams; // The number of parameters this function accepts.
	
	private HashMap<Label, TargetDataType> labelVsTargetDataType;    // A map of the literals type information in the program 
														   			 // versus the labels in the assembly language
	
	private ActivationFrame activationFrame;
	
	private boolean mainFunction;  // A flag to indicate if this is the main function (starting point)

	public ClangFunctionDef(){
		//invocations = new Vector<AssemCallExp>();		
		labelVsTargetDataType = new HashMap<Label, TargetDataType>();	
	}
	
	public ClangFunctionDef(String name) {
		this.name  = name;	
	}
	
	public String getName(){
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
//	public IRTree getExternalDeclTree() {
//		return functionBody;
//	}
//
//	public void setExternalDeclTree(IRTree functionBody) {
//		this.functionBody = functionBody;
//	}

	/*public void addCalledFunction(AssemCallExp calledFunctionTree) {
		invocations.addElement(calledFunctionTree);
	}*/

	public int getNumIncomingParams() {
		return numIncomingParams;
	}

	public ActivationFrame getActivationFrame() {
		return activationFrame;
	}

	public void setActivationFrame(ActivationFrame activationFrame) {
		this.activationFrame = activationFrame;
	}
	
	public HashMap<Label, TargetDataType> getLabelVsString() {
		return labelVsTargetDataType;
	}
	
	public void setStringLabel(TargetDataType targetDataType, Label strLabel) {
		labelVsTargetDataType.put(strLabel, targetDataType);
	}

	public Vector<String> dataDeclarations() {
		return null;
	}

	public boolean isMainFunction() {
		return mainFunction;
	}

	public void setMainFunction(boolean mainFunction) {
		this.mainFunction = mainFunction;
	}
	
	public void setExternalDeclTree(IRTree functionBody) {
		this.functionBody = functionBody;
	}
	@Override
	public IRTree getExternalDecl() {
		// TODO Auto-generated method stub
		return functionBody;
	}
}

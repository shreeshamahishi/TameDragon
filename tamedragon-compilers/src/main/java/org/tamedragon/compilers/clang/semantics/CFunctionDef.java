package org.tamedragon.compilers.clang.semantics;

import java.util.HashMap;
import java.util.Vector;

import org.tamedragon.assemblyabstractions.ActivationFrame;
import org.tamedragon.assemblyabstractions.constructs.AssemType;
import org.tamedragon.common.Label;
import org.tamedragon.common.TargetDataType;

public class CFunctionDef implements CTranslationUnit{
	private String name;

	private AssemType functionBody;     // The translated body of the function

	//private Vector<AssemCallExp> invocations;    // A list of function levels that represents the functions that this
								                 // function calls.
	private int numIncomingParams; // The number of parameters this function accepts.
	
	private HashMap<Label, TargetDataType> labelVsTargetDataType;    // A map of the literals type information in the program 
														   			 // versus the labels in the assembly language
	
	private ActivationFrame activationFrame;
	
	private boolean mainFunction;  // A flag to indicate if this is the main function (starting point)

	public CFunctionDef(){
		//invocations = new Vector<AssemCallExp>();		
		labelVsTargetDataType = new HashMap<Label, TargetDataType>();	
	}
	
	public CFunctionDef(String name) {
		this.name  = name;	
	}
	
	public String getName(){
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public AssemType getExternalDeclTree() {
		return functionBody;
	}

	public void setExternalDeclTree(AssemType functionBody) {
		this.functionBody = functionBody;
	}

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
}

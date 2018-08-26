package org.tamedragon.compilers.clang.semantics;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.tamedragon.assemblyabstractions.Level;
import org.tamedragon.assemblyabstractions.constructs.AssemType;
import org.tamedragon.common.EnvironmentConstants;
import org.tamedragon.common.Label;
import org.tamedragon.compilers.mips.translate.MipsActivationFrameImpl;

public class CLevel implements Level{
	private CLevel parentLevel;
	private MipsActivationFrameImpl activationFrame;
	//private Symbol name;
	private String name;

	private AssemType functionBody;     // The translated body of the function

	private Vector invocations;    // A list of function levels that represents the functions that this
	// function calls.
	private int numIncomingParams; // The number of parameters this function accepts.

	private HashMap labelVsStr;    // A map of the string literals in the program versus the labels in 
	// in the assembly language

	//public Level(Level parent, Symbol levelName)
	public CLevel(CLevel parent, String levelName) {
		this.parentLevel = parent;
		this.name  = levelName;

		// Create a new activation frame
		Label newLabel = new Label(levelName);
		activationFrame = new MipsActivationFrameImpl(newLabel);

		invocations = new Vector();

		labelVsStr = new HashMap();	
	}

	public CLevel getParentLevel(){
		return parentLevel;
	}

	public MipsActivationFrameImpl getActivationFrame(){
		return activationFrame;
	}

	public String getLevelName(){
		return name;
	}

	public AssemType getFunctionBody() {
		return functionBody;
	}

	public void setFunctionBody(AssemType functionBody) {
		this.functionBody = functionBody;
	}

	public void addCalledFunction(CLevel calledFunction) {
		invocations.addElement(calledFunction);
	}

	public int getNumIncomingParams() {
		return numIncomingParams;
	}

	public void setNumIncomingParams(int numIncomingParams) {
		this.numIncomingParams = numIncomingParams;
	}

	public int getMaxOutgoingParams()
	{
		int maxOutgoingParams = 0;
		int numInvocations = invocations.size();
		for(int i = 0; i < numInvocations; i++) {
			CLevel calledFunction = (CLevel) invocations.elementAt(i);
			int incomingParamsForCalledFunction = calledFunction.getNumIncomingParams();
			if(incomingParamsForCalledFunction > maxOutgoingParams)
				maxOutgoingParams = incomingParamsForCalledFunction;
		}

		if(maxOutgoingParams > 4)
			maxOutgoingParams = maxOutgoingParams - 4;
		else
			maxOutgoingParams = 0;

		return maxOutgoingParams;
	}

	/**
	 * Returns the number of functions called by the function represented by this level
	 * @return
	 */
	public int getNumFunctionsCalled()
	{
		return invocations.size();
	}

	public HashMap getLabelVsString() {
		return labelVsStr;
	}

	public void setStringLabel(String str, Label strLabel)
	{
		labelVsStr.put(strLabel, str);
	}

	/**
	 * 
	 */
	public Vector dataDeclarations()
	{
		Vector decls = new Vector();

		Set entries = labelVsStr.entrySet();
		Iterator iter = entries.iterator();
		while(iter.hasNext())
		{
			Map.Entry entry = (Map.Entry) iter.next();
			Label lbl = (Label) entry.getKey();
			String str = (String)  entry.getValue();

			String declaration = lbl + ": " + ".asciiz \"" + str +  "\"" + EnvironmentConstants.NEWLINE;
			decls.addElement(declaration);
		}

		return decls;
	}
}

package org.tamedragon.common;

import java.util.HashMap;
import java.util.Vector;

import org.tamedragon.assemblyabstractions.ActivationFrame;
import org.tamedragon.common.llvmir.instructions.AssemblyInstruction;
import org.tamedragon.common.llvmir.types.Temp;

public interface TargetMachine {
	public Vector<String> getColors();

	public Vector<String> createCodeStringForFunction( ActivationFrame activationFrame, 
			Vector<AssemblyInstruction> functionBodyCode,  boolean isMainFunction);
			  
	public Temp getRegister(int type);              // To support registers like $FP, $SP, $v0, etc.
	public int getNumParameterRegisters();          // Returns the number of registers for passing in as parameters
	public int getWordSize();
	public boolean isLoadStore();
	public Vector<String>  getGlobalDataDeclarations(HashMap<Label, TargetDataType> labelVsTargetDataType);
	public Vector<AssemblyInstruction> modify(Vector<AssemblyInstruction> instructionList,
			ActivationFrame activationFrame,
			boolean isLeafProcedure) throws Exception ;
	public boolean isCompatibleWithRegister(Temp temp, String registerName);
	public boolean isSizeCompatible(Temp temp, String clr);
}

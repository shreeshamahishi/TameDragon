package org.tamedragon.assemblyabstractions;

import java.util.List;
import java.util.Vector;

import org.tamedragon.assemblyabstractions.exceptions.InvalidStackElementAccessException;
import org.tamedragon.common.Label;
import org.tamedragon.common.llvmir.instructions.AssemblyInstruction;
import org.tamedragon.common.llvmir.types.AbstractType;

public interface ActivationFrame {	
	
	public static final short OFFSET_FOR_CALLEE_SAVED_REGISTERS = 0;
	public static final short OFFSET_FOR_CALLER_SAVED_REGISTERS = 1;
	public static final short OFFSET_FOR_STORING_RETURN_ADDRESS = 2;
	public static final short OFFSET_FOR_STORING_FRAME_POINTER =  3;
	public static final short OFFSET_FOR_STORING_ARGUMENTS =  4;

	public static final short CALLER_SAVED_REGISTERS_FLAG = 0;
	public static final short CALLEE_SAVED_REGISTERS_FLAG = 1;
	
	public Label getLabel();
	public void setLabel(Label nm);
	public String getSourceName();
	public void setSourceName(String sourceName);
	public void setAlignData(boolean alignData);
	
	public void addStackElement(StackElement stackElement);
	public void addRegistersUsed(String registerName, AbstractType abstractType);
	public void addArgumentInStackForInvocation(AssemblyInstruction callInstruction, AbstractType typeOfArgumentOnStack);
	public void setIsLeafProcedure(boolean isLeafProcedure);
	public void addArgumentInStackForAccepting(AbstractType abstractType);
	
	public int getStackSize();
	public int getOffsetForNextElementFromFramePointer(int sizeOfElement);
	public int getOffsetForNextElementFromStackPointerForAcceptingArgument(int savedArgSize);
	
	public int getOffset(short storageItemType);
	public int getOffsetForNextElementFromStackPointer(short storageItemType, int sizeOfElement);
	public int getOffsetForNextArgumentForInvocationOnStackPointer(AssemblyInstruction callInstruction, int sizeOfElement);
	public int getOffsetForSavedRegisterstFromStackPointer(short storageItemType, int index) 
			throws InvalidStackElementAccessException;
	
	public List<String> getCallerSavedRegistersUsedInFunction();
	public AbstractType getRegisterType(String registerName);
	public List<String> getCalleeSavedRegistersUsedInFunction();
	public Vector<StackElement> getElementsOnStack();
}

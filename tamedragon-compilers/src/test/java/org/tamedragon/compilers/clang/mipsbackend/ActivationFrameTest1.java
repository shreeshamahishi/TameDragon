package org.tamedragon.compilers.clang.mipsbackend;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import org.tamedragon.assemblyabstractions.ActivationFrame;
import org.tamedragon.assemblyabstractions.ScalarStackElement;
import org.tamedragon.assemblyabstractions.StackElement;
import org.tamedragon.common.Label;
import org.tamedragon.common.llvmir.instructions.Call;
import org.tamedragon.common.llvmir.types.AbstractType;
import org.tamedragon.compilers.clang.semantics.ActivationFrameFactory;

public class ActivationFrameTest1 {
	
	private ActivationFrame fixture;
	
	@Before
	public void setUp(){
		ActivationFrameFactory activationFrameFactory = new ActivationFrameFactory();
		fixture = activationFrameFactory.getActivationFrame("MIPS");
		fixture.setSourceName("Test");
		fixture.setLabel(new Label(""));	
		fixture.setAlignData(true);
	}
	
	/**
	 * Bare minimum stack with only one callee saved regsiter in $s0 with data of size 1 byte
	 */
	@Ignore
	@Test
	public void testLeafProcedureAndNoStackVariablesAndOneCalleeSavedIntByteSize() {     
		AbstractType abstractType = getAbstractType(true, 1, true);
		fixture.addRegistersUsed("$s0", abstractType);
		fixture.setIsLeafProcedure(true);
		
		assertTrue(fixture.getStackSize() == 16);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_STORING_ARGUMENTS) == 4);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_CALLEE_SAVED_REGISTERS) == 4);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_CALLER_SAVED_REGISTERS) == 5);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_STORING_RETURN_ADDRESS) == 8);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_STORING_FRAME_POINTER) == 12);
	}
	
	/**
	 * Bare minimum stack with only one callee saved regsiter in $s0 with data of size 2 bytes
	 */
	@Ignore
	@Test
	public void testLeafProcedureAndNoStackVariablesAndOneCalleeSavedShortIntSize() {     
		
		AbstractType abstractType = getAbstractType(true, 2, true);
		fixture.addRegistersUsed("$s0", abstractType);
		fixture.setIsLeafProcedure(true);
		
		assertTrue(fixture.getStackSize() == 16);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_STORING_ARGUMENTS) == 4);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_CALLEE_SAVED_REGISTERS) == 4);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_CALLER_SAVED_REGISTERS) == 6);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_STORING_RETURN_ADDRESS) == 8);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_STORING_FRAME_POINTER) == 12);
	}
	
	/**
	 * Bare minimum stack with only one callee saved regsiter in $s0 with data of size 4 bytes
	 */
	@Test
	public void testLeafProcedureAndNoStackVariablesAndOneCalleeSavedIntSize() {     
		
		AbstractType abstractType = getAbstractType(true, 4, true);
		fixture.addRegistersUsed("$s0", abstractType);
		fixture.setIsLeafProcedure(true);
		
		assertTrue(fixture.getStackSize() == 16);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_STORING_ARGUMENTS) == 4);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_CALLEE_SAVED_REGISTERS) == 4);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_CALLER_SAVED_REGISTERS) == 8);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_STORING_RETURN_ADDRESS) == 8);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_STORING_FRAME_POINTER) == 12);
	}
	
	/**
	 * Bare minimum stack with only one callee saved regsiter in $f20 with data of floating point size of 
	 * single precision
	 */
	@Test       
	public void testLeafProcedureAndNoStackVariablesAndOneCalleeSavedFloatSinglePrecision() {     
		
		AbstractType abstractType = getAbstractType(false, 4, false);
		fixture.addRegistersUsed("$f20", abstractType);
		fixture.setIsLeafProcedure(true);
		
		assertTrue(fixture.getStackSize() == 16);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_STORING_ARGUMENTS) == 4);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_CALLEE_SAVED_REGISTERS) == 4);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_CALLER_SAVED_REGISTERS) == 8);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_STORING_RETURN_ADDRESS) == 8);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_STORING_FRAME_POINTER) == 12);
	}
	
	/**
	 * Bare minimum stack with only one callee saved regsiter in $s0 with data of floating point size of 
	 * single precision
	 */
	@Test
	public void testLeafProcedureAndNoStackVariablesAndOneCallerSavedFloatSinglePrecision() {     
		
		AbstractType abstractType = getAbstractType(false, 4, false);
		fixture.addRegistersUsed("$f2", abstractType);
		fixture.setIsLeafProcedure(true);
		
		assertTrue(fixture.getStackSize() == 12);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_STORING_ARGUMENTS) == 4);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_CALLEE_SAVED_REGISTERS) == 4);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_CALLER_SAVED_REGISTERS) == 4);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_STORING_RETURN_ADDRESS) == 4);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_STORING_FRAME_POINTER) == 8);
	}
	
	/**
	 * Bare minimum stack with only one caller saved regsiter in $s0 with data of floating point size of 
	 * double precision
	 */
	@Test
	public void testLeafProcedureAndNoStackVariablesAndOneCallerSavedFloatDoublePrecision() {     
		
		AbstractType abstractType = getAbstractType(false, 8, false);
		fixture.addRegistersUsed("$f2", abstractType);
		fixture.setIsLeafProcedure(true);
		
		assertTrue(fixture.getStackSize() == 12);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_CALLEE_SAVED_REGISTERS) == 4);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_CALLER_SAVED_REGISTERS) == 4);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_STORING_ARGUMENTS) == 4);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_STORING_RETURN_ADDRESS) == 4);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_STORING_FRAME_POINTER) == 8);
		
	}
	
	/**
	 * Bare minimum stack with only one callee saved regsiter in $f20 with data of floating point size of 
	 * double precision
	 */
	@Test
	public void testLeafProcedureAndNoStackVariablesAndOneCalleeSavedFloatDoublePrecision() {     
		
		AbstractType abstractType = getAbstractType(false, 8, false);
		fixture.addRegistersUsed("$f20", abstractType);
		fixture.setIsLeafProcedure(true);
		
		assertTrue(fixture.getStackSize() == 24);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_CALLEE_SAVED_REGISTERS) == 8);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_CALLER_SAVED_REGISTERS) == 16);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_STORING_ARGUMENTS) == 4);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_STORING_RETURN_ADDRESS) == 16);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_STORING_FRAME_POINTER) == 20);
		
	}
	
	/**
	 * Non-leaf procedure with no local variables and one callee save and another caller saved
	 * registers
	 */
	@Test
	public void testNonLeafProcedureAndNoStackVariablesAndOneCalleeSavedFloatDoublePrecision() {     
		
		AbstractType abstractType = getAbstractType(false, 8, false);
		fixture.addRegistersUsed("$f20", abstractType);  // Callee saved
		fixture.addRegistersUsed("$f2", abstractType);  // Caller saved
		fixture.setIsLeafProcedure(false);
		
		assertTrue(fixture.getStackSize() == 32);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_CALLEE_SAVED_REGISTERS) == 8);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_CALLER_SAVED_REGISTERS) == 16);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_STORING_ARGUMENTS) == 4);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_STORING_RETURN_ADDRESS) == 24);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_STORING_FRAME_POINTER) == 28);
	}
	
	/**
	 * Non-leaf procedure with no local variables and multiple types of caller and callee saves
	 */
	@Test
	public void testNonLeafProcedureAndNoStackVariablesAndMultipleSaveTypes() {     
		
		fixture.setIsLeafProcedure(false);
		
		AbstractType doubleFp = getAbstractType(false, 8, false);
		fixture.addRegistersUsed("$f20", doubleFp);  // Callee saved
		fixture.addRegistersUsed("$f2", doubleFp);  // Caller saved
		
		AbstractType singleFp = getAbstractType(false, 4, false);
		fixture.addRegistersUsed("$f22", singleFp);  // Callee saved
		
		AbstractType intByte = getAbstractType(true, 4, true);		
		fixture.addRegistersUsed("$s0", intByte);  // Callee saved
		
		AbstractType charByte = getAbstractType(true, 1, true);		
		fixture.addRegistersUsed("$s1", charByte);  // Callee saved
		
		AbstractType shortByte = getAbstractType(true, 2, false);		
		fixture.addRegistersUsed("$t0", shortByte);  // Caller saved
		
		AbstractType longByte = getAbstractType(true, 8, false);		
		fixture.addRegistersUsed("$t1", longByte);  // Caller saved
		
		assertTrue(fixture.getStackSize() == 64);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_CALLEE_SAVED_REGISTERS) == 8);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_CALLER_SAVED_REGISTERS) == 32);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_STORING_ARGUMENTS) == 4);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_STORING_RETURN_ADDRESS) == 56);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_STORING_FRAME_POINTER) == 60);
	}
	
	/**
	 * Non-leaf procedure with no local variables and multiple types of caller and callee saves
	 * and saving outgoing arguments too.
	 */
	@Ignore
	@Test
	public void testNonLeafProcedureAndSomeOutgoingArgsAndNoStackVariablesAndMultipleSaveTypes() {     
		
		fixture.setIsLeafProcedure(false);
		
		// Set up some caller-saved and some callee-saved registers
		AbstractType doubleFp = getAbstractType(false, 8, false);
		fixture.addRegistersUsed("$f20", doubleFp);  // Callee saved
		fixture.addRegistersUsed("$f2", doubleFp);  // Caller saved
		
		AbstractType singleFp = getAbstractType(false, 4, false);
		fixture.addRegistersUsed("$f22", singleFp);  // Callee saved
		
		AbstractType intByte = getAbstractType(true, 4, true);		
		fixture.addRegistersUsed("$s0", intByte);  // Callee saved
		
		AbstractType charByte = getAbstractType(true, 1, true);		
		fixture.addRegistersUsed("$s1", charByte);  // Callee saved
		
		AbstractType shortByte = getAbstractType(true, 2, false);		
		fixture.addRegistersUsed("$t0", shortByte);  // Caller saved
		
		AbstractType longByte = getAbstractType(true, 8, false);		
		fixture.addRegistersUsed("$t1", longByte);  // Caller saved
		
		// Set up some outgoing arguments
		List<AbstractType> argTypesOnStack1 = argsOnStackForInvocation(shortByte, longByte);  // 10
		List<AbstractType> argTypesOnStack2 = argsOnStackForInvocation(doubleFp, longByte);   // 16
		List<AbstractType> argTypesOnStack3 = argsOnStackForInvocation(intByte, charByte, singleFp);  // 9
		List<AbstractType> argTypesOnStack4 = argsOnStackForInvocation(doubleFp, intByte, shortByte);  // 14
		
		Call call1 = new Call("foo", null, null);
		for(AbstractType abstractType : argTypesOnStack1){
			fixture.addArgumentInStackForInvocation(call1, abstractType);
		}
		
		Call call2 = new Call("bar", null, null);
		for(AbstractType abstractType : argTypesOnStack2){
			fixture.addArgumentInStackForInvocation(call2, abstractType);
		}
		
		Call call3 = new Call("foobar", null, null);
		for(AbstractType abstractType : argTypesOnStack3){
			fixture.addArgumentInStackForInvocation(call3, abstractType);
		}
		
		Call call4 = new Call("foo1", null, null);
		for(AbstractType abstractType : argTypesOnStack4){
			fixture.addArgumentInStackForInvocation(call4, abstractType);
		}
		
		assertTrue(fixture.getStackSize() == 80);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_CALLEE_SAVED_REGISTERS) == 24);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_CALLER_SAVED_REGISTERS) == 48);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_STORING_ARGUMENTS) == 8);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_STORING_RETURN_ADDRESS) == 72);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_STORING_FRAME_POINTER) == 76);

		
		// Add one more caller-saved and callee-saved and another smaller list of outgoing args and check
		// the result
		fixture.addRegistersUsed("$s2", shortByte);   // Callee saved
		fixture.addRegistersUsed("$t2", charByte);    // Caller saved
		List<AbstractType> argTypesOnStack5 = argsOnStackForInvocation(intByte, intByte);  // 8, no effect on size of args
		Call call5 = new Call("foo2", null, null);
		for(AbstractType abstractType : argTypesOnStack5){
			fixture.addArgumentInStackForInvocation(call5, abstractType);
		}
		assertTrue(fixture.getStackSize() == 84);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_CALLEE_SAVED_REGISTERS) == 24);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_CALLER_SAVED_REGISTERS) == 48);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_STORING_ARGUMENTS) == 8);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_STORING_RETURN_ADDRESS) == 76);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_STORING_FRAME_POINTER) == 80);
		
		// Add a caller-saved register and a larger list of outgoing args and check the result
		fixture.addRegistersUsed("$s3", intByte);   // Callee saved
		List<AbstractType> argTypesOnStack6 = argsOnStackForInvocation(intByte, intByte, doubleFp, doubleFp);  // 24
		Call call6 = new Call("bar1", null, null);
		for(AbstractType abstractType : argTypesOnStack6){
			fixture.addArgumentInStackForInvocation(call6, abstractType);
		}
		
		
		assertTrue(fixture.getStackSize() == 92);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_CALLEE_SAVED_REGISTERS) == 32);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_CALLER_SAVED_REGISTERS) == 56);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_STORING_ARGUMENTS) == 8);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_STORING_RETURN_ADDRESS) == 84);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_STORING_FRAME_POINTER) == 88);
	}
	
	/**
	 * Non-leaf procedure with multiple types of caller and callee saves , saving outgoing 
	 * argument and some stack variables too.
	 */
	@Ignore
	@Test
	public void testNonLeafProcedureAndSomeOutgoingArgsWithStackVariablesAndMultipleSaveTypes() {     
		
		fixture.setIsLeafProcedure(false);
		
		// Set up some caller-saved and some callee-saved registers
		AbstractType doubleFp = getAbstractType(false, 8, false);
		AbstractType singleFp = getAbstractType(false, 4, false);
		AbstractType charByte = getAbstractType(true, 1, true);
		AbstractType intByte = getAbstractType(true, 4, true);	
		AbstractType shortByte = getAbstractType(true, 2, false);
		AbstractType longByte = getAbstractType(true, 8, false);		
		
		// Callee saved (size = 42)
		fixture.addRegistersUsed("$f22", doubleFp);  // 8
		fixture.addRegistersUsed("$f24", singleFp);  // 4
		fixture.addRegistersUsed("$f26", doubleFp);  // 8
		fixture.addRegistersUsed("$s0", intByte);    // 4
		fixture.addRegistersUsed("$s1", shortByte);  // 2
		fixture.addRegistersUsed("$s2", longByte);   // 8
		fixture.addRegistersUsed("$s3", longByte);   // 8
		// Caller saved (size = 24)
		fixture.addRegistersUsed("$f2", singleFp);   // 4 
		fixture.addRegistersUsed("$f4", singleFp);   // 4
		fixture.addRegistersUsed("$f6", doubleFp);   // 8
		fixture.addRegistersUsed("$t0", intByte);    // 4
		fixture.addRegistersUsed("$t1", shortByte);  // 2
		fixture.addRegistersUsed("$t2", charByte);   // 1
		fixture.addRegistersUsed("$t3", charByte);   // 1
		
		// Set up some outgoing arguments (20)
		List<AbstractType> argTypesOnStack1 = argsOnStackForInvocation(longByte, longByte);  // 16
		List<AbstractType> argTypesOnStack2 = argsOnStackForInvocation(doubleFp, longByte, singleFp);   // 20
		List<AbstractType> argTypesOnStack3 = argsOnStackForInvocation(charByte, charByte, doubleFp);  // 10
		List<AbstractType> argTypesOnStack4 = argsOnStackForInvocation(doubleFp);  // 8
		
		Call call1 = new Call("foo", null, null);
		for(AbstractType abstractType : argTypesOnStack1){
			fixture.addArgumentInStackForInvocation(call1, abstractType);
		}
		
		Call call2 = new Call("bar", null, null);
		for(AbstractType abstractType : argTypesOnStack2){
			fixture.addArgumentInStackForInvocation(call2, abstractType);
		}
		
		Call call3 = new Call("foobar", null, null);
		for(AbstractType abstractType : argTypesOnStack3){
			fixture.addArgumentInStackForInvocation(call3, abstractType);
		}
		
		Call call4 = new Call("foo1", null, null);
		for(AbstractType abstractType : argTypesOnStack4){
			fixture.addArgumentInStackForInvocation(call4, abstractType);
		}
		
		// Set up some local variables (14)
		ScalarStackElement sseDoubleFp = getScalarStackElement(doubleFp);
		ScalarStackElement sseSingleFp = getScalarStackElement(singleFp);
		ScalarStackElement sseShortByte =  getScalarStackElement(shortByte);
		fixture.addStackElement(sseDoubleFp);   // 8
		fixture.addStackElement(sseSingleFp);   // 4
		fixture.addStackElement(sseShortByte);  // 2
		
		assertTrue(fixture.getStackSize() == 126);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_CALLEE_SAVED_REGISTERS) == 32);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_CALLER_SAVED_REGISTERS) == 80);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_STORING_ARGUMENTS) == 8);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_STORING_RETURN_ADDRESS) == 104);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_STORING_FRAME_POINTER) == 108);
	}
	
	@Ignore
	@Test
	public void testLeafProcedureAndWithStackVariablesAndMultipleSaveTypes() {     
		
		fixture.setIsLeafProcedure(true);
		
		// Set up some caller-saved and some callee-saved registers
		AbstractType doubleFp = getAbstractType(false, 8, false);
		AbstractType singleFp = getAbstractType(false, 4, false);
		AbstractType charByte = getAbstractType(true, 1, true);
		AbstractType intByte = getAbstractType(true, 4, true);	
		AbstractType shortByte = getAbstractType(true, 2, false);
		AbstractType longByte = getAbstractType(true, 8, false);		
		
		// Callee saved (size = 20)
		fixture.addRegistersUsed("$f22", singleFp);  // 4
		fixture.addRegistersUsed("$f24", singleFp);  // 4
		fixture.addRegistersUsed("$f26", doubleFp);  // 8
		fixture.addRegistersUsed("$s1", shortByte);  // 2
		fixture.addRegistersUsed("$s2", charByte);   // 1
		fixture.addRegistersUsed("$s3", charByte);   // 1
		// Caller saved (size = 20, but actually zero, since it is a leaf procedure)
		fixture.addRegistersUsed("$f2", doubleFp);   // 8 
		fixture.addRegistersUsed("$f4", doubleFp);   // 8
		fixture.addRegistersUsed("$t0", intByte);    // 4
		
		// Set up some local variables (14)
		ScalarStackElement sseShortByte = getScalarStackElement(shortByte);
		ScalarStackElement sseSingleFp = getScalarStackElement(singleFp);
		ScalarStackElement sseLongByte =  getScalarStackElement(longByte);
		fixture.addStackElement(sseShortByte);   // 2
		fixture.addStackElement(sseSingleFp);    // 4
		fixture.addStackElement(sseLongByte);    // 8
		
		assertTrue(fixture.getStackSize() == 56);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_CALLEE_SAVED_REGISTERS) == 4);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_CALLER_SAVED_REGISTERS) == 28);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_STORING_ARGUMENTS) == 4);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_STORING_RETURN_ADDRESS) == 28);
		assertTrue(fixture.getOffset(ActivationFrame.OFFSET_FOR_STORING_FRAME_POINTER) == 32);
		
	}
	
	private AbstractType getAbstractType(boolean isInt, int byteSize, boolean isSigned){
		AbstractType abstractType = new AbstractType();
		if(isInt){
			abstractType.setInteger(true);
			abstractType.setIntegerSize(byteSize);
			abstractType.setSigned(isSigned);
		}
		else{
			abstractType.setInteger(false);
			abstractType.setFloatPrecision(byteSize);
		}
		return abstractType;
	}
	
	private List<AbstractType> argsOnStackForInvocation(AbstractType...abstractTypes ){
		List<AbstractType> argTypesOnStack = new ArrayList<AbstractType>();
		for(AbstractType at : abstractTypes){
			argTypesOnStack.add(at);
		}
		return argTypesOnStack;
	}
	
	private ScalarStackElement getScalarStackElement(AbstractType abstractType){
		ScalarStackElement sseDoubleFp = new ScalarStackElement();
		sseDoubleFp.setType(StackElement.SCALAR_TYPE);
		sseDoubleFp.setMode(StackElement.ESCAPED);
		sseDoubleFp.setAbstractType(abstractType);
		
		return sseDoubleFp;
	}
}

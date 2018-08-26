package org.tamedragon.common.llvmir.instructions;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.tamedragon.common.llvmir.instructions.CastInst;
import org.tamedragon.common.llvmir.instructions.Instruction.InstructionID;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionCreationException;
import org.tamedragon.common.llvmir.math.APInt;
import org.tamedragon.common.llvmir.types.CompilationContext;
import org.tamedragon.common.llvmir.types.ConstantInt;
import org.tamedragon.common.llvmir.types.Type;
import org.tamedragon.common.llvmir.types.Value;

public class CastInstrsTest {

	private CompilationContext compilationContext;

	@Before
	public void setUp(){
		compilationContext = new CompilationContext();
	}

	@Test
	public void testValidCastInstrsCreation(){

		String errMsg = "";
		CastInst castInst1 = null;
		
		try{
			Value sourceValue = new Value(Type.getInt32Type(compilationContext, true));
			sourceValue.setName("Y");
			castInst1 =  CastInst.create(InstructionID.TRUNC_INST, "X", 
					Type.getInt1Type(compilationContext, false), sourceValue, null);
		}
		catch(InstructionCreationException ice){
			errMsg = ice.getMessage();
		}

		assertNotNull(castInst1);
		String insStr = castInst1.toString();
		assertTrue(insStr.equals("%X = trunc i32 %Y to i1"));
		assertTrue(errMsg.equals(""));
		
		CastInst castInst2 = null;
		try{
			ConstantInt ci = null;
			try {
				ci = new ConstantInt(Type.getInt32Type(compilationContext, true), new APInt(32, "257", true));
			} catch (InstantiationException e) {}
			assertNotNull(ci);
			castInst2 =  CastInst.create(InstructionID.TRUNC_INST, "X", 
					Type.getInt8Type(compilationContext, true), ci, null);
		}
		catch(InstructionCreationException ice){
			errMsg = ice.getMessage();
		}

		assertNotNull(castInst2);
		insStr = castInst2.toString();
		assertTrue(insStr.equals("%X = trunc i32 257 to i8"));
		assertTrue(errMsg.equals(""));
	}
	
	@Test
	public void testInvalidCastInstrsCreation(){

		String errMsg = "";
		CastInst castInst1 = null;
		
		try{
			Value sourceValue = new Value(Type.getInt32Type(compilationContext, true));
			sourceValue.setName("%Y");
			castInst1 =  CastInst.create(InstructionID.TRUNC_INST, "%X", 
					Type.getInt64Type(compilationContext, true), sourceValue, null);
		}
		catch(InstructionCreationException ice){
			errMsg = ice.getMessage();
		}
		
		assertNull(castInst1);
		assertTrue(errMsg.equals("Cannot cast i32 to i64"));
	}
}

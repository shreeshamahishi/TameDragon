package org.tamedragon.common.llvmir.instructions;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.tamedragon.common.Pair;
import org.tamedragon.common.llvmir.instructions.AllocaInst;
import org.tamedragon.common.llvmir.instructions.GetElementPtrInst;
import org.tamedragon.common.llvmir.instructions.LoadInst;
import org.tamedragon.common.llvmir.instructions.StoreInst;
import org.tamedragon.common.llvmir.instructions.Instruction.AtomicOrdering;
import org.tamedragon.common.llvmir.instructions.Instruction.SynchronizationScope;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionCreationException;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionUpdateException;
import org.tamedragon.common.llvmir.math.APInt;
import org.tamedragon.common.llvmir.types.APFloat;
import org.tamedragon.common.llvmir.types.ArrayType;
import org.tamedragon.common.llvmir.types.CompilationContext;
import org.tamedragon.common.llvmir.types.ConstantFP;
import org.tamedragon.common.llvmir.types.ConstantInt;
import org.tamedragon.common.llvmir.types.IntegerType;
import org.tamedragon.common.llvmir.types.PointerType;
import org.tamedragon.common.llvmir.types.StructType;
import org.tamedragon.common.llvmir.types.Type;
import org.tamedragon.common.llvmir.types.Value;
import org.tamedragon.common.llvmir.types.exceptions.TypeCreationException;

public class MemoryInstructionsTest {
	private CompilationContext compilationContext;
	private Properties properties;

	@Before
	public void setUp() {
		compilationContext = new CompilationContext();
		properties = LLVMUtility.getDefaultProperties();
	}

	// *************************************************************************************************************
	// *********************** TESTS FOR ALLOCA INSTRUCTIONS
	// *******************************************************
	// *************************************************************************************************************

	// Confirm that all "normal" alloca instructions are created correctly.
	@Test
	public void testAllocaCreation() {
		// creating alloca instr to only one i32 type
		Type resultType = Type.getInt32Type(compilationContext, true);
		String name = "ptr";
		int arraySize = 1;
		Integer align = 0;

		AllocaInst allocaInst = null;
		try {
			allocaInst = AllocaInst.create(properties, resultType, name, align, null, null);
		} catch (InstructionCreationException ice) { }
		assertNotNull(allocaInst);
		assertTrue(allocaInst.toString().equals("%ptr = alloca i32, align 4"));

		// creating alloca instr to allocate memory for an array of i32 type,
		// whose size is 4
		arraySize = 4;
		ArrayType arrayType = new ArrayType(compilationContext, Type
				.getInt32Type(compilationContext, true), arraySize);
		try {
			allocaInst = AllocaInst.create(properties, arrayType, name, align, null, null);
		} catch (InstructionCreationException ice) { }
		assertNotNull(allocaInst);
		assertTrue(allocaInst.toString().equals("%ptr = alloca [4 x i32], align 16"));

		// creating alloca instr to only one i32 type
		try {
			allocaInst = AllocaInst.create(properties, resultType, name, align, null, null);
		} catch (InstructionCreationException ice) {
		}
		assertNotNull(allocaInst);
		assertTrue(allocaInst.toString().equals("%ptr = alloca i32, align 4"));

		// creating alloca instr to only one i8 type, whose alignment is 1
		resultType = Type.getInt8Type(compilationContext, true);
		try {
			allocaInst = AllocaInst.create(properties, resultType, name, align, null, null);
		} catch (InstructionCreationException ice) {
		}
		assertNotNull(allocaInst);
		assertTrue(allocaInst.toString().equals("%ptr = alloca i8, align 1"));

		// creating a allocation instruction for a int pointer
		try {
			resultType = Type.getPointerType(compilationContext, Type.getInt32Type(compilationContext, true), 0);
		} catch (TypeCreationException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		try {
			allocaInst = AllocaInst.create(properties, resultType, name, align, null, null);
		} catch (InstructionCreationException ice) {
		}
		assertNotNull(allocaInst);
		assertTrue(allocaInst.toString().equals("%ptr = alloca i32*, align 8"));

		// creating a allocation instruction to allocate memory for string array
		// coming from method argument
		PointerType ptrType = null;
		try {
			ptrType = Type.getPointerType(compilationContext, Type.getInt8Type(compilationContext, true), 0);
			resultType = Type.getPointerType(compilationContext, ptrType, 0);
		} catch (TypeCreationException e) {
			e.printStackTrace();
			System.exit(-1);
		}

		try {
			allocaInst = AllocaInst.create(properties, resultType, name, align, null, null);
		} catch (InstructionCreationException ice) {
		}
		assertNotNull(allocaInst);
		assertTrue(allocaInst.toString().equals("%ptr = alloca i8**, align 8"));

		// creating a allocation instruction to allocate memory for structure
		// type
		ArrayType arrType = new ArrayType(compilationContext, Type
				.getInt8Type(compilationContext, true), 0);
		resultType = new StructType(compilationContext, true, "pqr",false,
				Type.getInt32Type(compilationContext, true), Type
				.getFloatType(compilationContext), Type
				.getDoubleType(compilationContext), arrType);
		try {
			allocaInst = AllocaInst.create(properties, resultType, name, align, null, null);
		} catch (InstructionCreationException ice) {
		}
		assertNotNull(allocaInst);
		assertTrue(allocaInst.toString().equals("%ptr = alloca %pqr, align 8"));

		// creating a allocation instruction to allocate memory for float type
		resultType = Type.getFloatType(compilationContext);
		try {
			allocaInst = AllocaInst.create(properties, resultType, name, align, null, null);
		} catch (InstructionCreationException ice) {
		}
		assertNotNull(allocaInst);
		assertTrue(allocaInst.toString().equals("%ptr = alloca float, align 4"));

		// creating a allocation instruction to allocate memory for double type
		resultType = Type.getDoubleType(compilationContext);
		try {
			allocaInst = AllocaInst.create(properties, resultType, name, align, null, null);
		} catch (InstructionCreationException ice) {
		}
		assertNotNull(allocaInst);
		assertTrue(allocaInst.toString()
				.equals("%ptr = alloca double, align 8"));

		// creating a allocation instruction to allocate memory for i16 type
		resultType = Type.getInt16Type(compilationContext, true);
		try {
			allocaInst = AllocaInst.create(properties, resultType, name, align, null, null);
		} catch (InstructionCreationException ice) {
		}
		assertNotNull(allocaInst);
		assertTrue(allocaInst.toString().equals("%ptr = alloca i16, align 2"));

		// creating a allocation instruction to allocate memory for i16 type,
		// and align it to 1024
		resultType = Type.getInt16Type(compilationContext, true);
		align = 1024;
		try {
			allocaInst = AllocaInst.create(properties, resultType, name, align, null, null);
		} catch (InstructionCreationException ice) {
		}
		assertNotNull(allocaInst);
		assertTrue(allocaInst.toString()
				.equals("%ptr = alloca i16, align 1024"));

		// creating a allocation instruction to allocate memory for i16 type,
		// and trying to align it to 1,
		// should not be aligned to 1 but aligned to any convenient boundary
		// compatible with the type i16 i.e. 2
		resultType = Type.getInt16Type(compilationContext, true);
		align = 1;
		try {
			allocaInst = AllocaInst.create(properties, resultType, name, align, null, null);
		} catch (InstructionCreationException ice) {
		}
		assertNotNull(allocaInst);
		assertTrue(allocaInst.toString().equals("%ptr = alloca i16, align 2"));

		// creating alloca instr to only one half type
		resultType = Type.getHalfType(compilationContext);
		arraySize = 1;
		align = 0;
		try {
			allocaInst = AllocaInst.create(properties, resultType, name, align, null, null);
		} catch (InstructionCreationException ice) {
		}
		assertNotNull(allocaInst);
		assertTrue(allocaInst.toString().equals("%ptr = alloca half, align 8"));
	}

	// Confirm that all "invalid" alloca instructions throws proper exceptions.
	@Test
	public void testInvalidAllocaCreation() throws TypeCreationException {
		// creating alloca instr with void type
		Type resultType = Type.getVoidType(compilationContext);
		String name = "%ptr";
		// We need to align because, sometimes we may want to provide our own
		// aligment
		// which should be greater than minimum alignment requirement of that
		// type.
		Integer align = 0;

		AllocaInst allocaInst = null;
		String errMsg = "";
		try {
			allocaInst = AllocaInst.create(properties, resultType, name, align, null, null);
		} catch (InstructionCreationException ice) {
			errMsg = ice.getMessage();
		}
		assertTrue(errMsg
				.equals(InstructionCreationException.INVALID_TYPE_FOR_ALLOCATION));

		// creating alloca instr with metadata type
		resultType = Type.getVoidType(compilationContext);

		errMsg = "";
		try {
			allocaInst = AllocaInst.create(properties, resultType, name, align, null, null);
		} catch (InstructionCreationException ice) {
			errMsg = ice.getMessage();
		}
		assertTrue(errMsg
				.equals(InstructionCreationException.INVALID_TYPE_FOR_ALLOCATION));

		// creating alloca instr with function type
		resultType = Type.getFunctionType(compilationContext, Type
				.getInt32Type(compilationContext, true), false, null);

		errMsg = "";
		try {
			allocaInst = AllocaInst.create(properties, resultType, name, align, null, null);
		} catch (InstructionCreationException ice) {
			errMsg = ice.getMessage();
		}
		assertTrue(errMsg
				.equals(InstructionCreationException.INVALID_TYPE_FOR_ALLOCATION));

		// creating alloca instruction with alignment value which is not a power
		// of two
		// but greater than the minimum alignment for i32 i.e. 4
		resultType = Type.getInt32Type(compilationContext, true);
		errMsg = "";
		align = 7;
		try {
			allocaInst = AllocaInst.create(properties, resultType, name, align, null, null);
		} catch (InstructionCreationException ice) {
			errMsg = ice.getMessage();
		}
		assertTrue(errMsg
				.equals(InstructionCreationException.ALIGNMENT_IS_NOT_A_POWER_OF_TWO));

		// creating alloca instr with value as null
		resultType = null;

		errMsg = "";
		try {
			allocaInst = AllocaInst.create(properties, resultType, name, align, null, null);
		} catch (InstructionCreationException ice) {
			errMsg = ice.getMessage();
		}
		assertTrue(errMsg.equals(InstructionCreationException.RESULT_TYPE_CANNOT_BE_NULL));
	}

	// Confirm that all "valid update" of alloca instructions works properly.
	@Test
	public void testAllocaUpdation() {
		// creating alloca instr to only one i32 type, and updating the align
		// value to 8
		// should update it to 8, because minimum alignment requirement for i32
		// is 4
		Type resultType = Type.getInt32Type(compilationContext, true);
		String name = "ptr";
		Integer align = 0;

		AllocaInst allocaInst = null;
		try {
			allocaInst = AllocaInst.create(properties, resultType, name, align, null, null);
		} catch (InstructionCreationException ice) { }

		assertNotNull(allocaInst);
		assertTrue(allocaInst.toString().equals("%ptr = alloca i32, align 4"));
		try {
			allocaInst.setAlignment(8, Type.getInt32Type(compilationContext, true));
		} catch (InstructionUpdateException e) {}
		assertTrue(allocaInst.toString().equals("%ptr = alloca i32, align 8"));
	}

	// Confirm that all "valid update" of alloca instructions works properly.
	@Test
	public void testAllocaInvalidUpdation() {
		// trying to update the align to a value which is not a power of two.
		Type resultType = Type.getInt32Type(compilationContext, true);
		String name = "ptr";
		Integer align = 0;
		String errMsg = "";
		AllocaInst allocaInst = null;
		try {
			allocaInst = AllocaInst.create(properties, resultType, name, align, null, null);
		} catch (InstructionCreationException ice) {}
		try {
			allocaInst.setAlignment(7, Type.getInt32Type(compilationContext, true));
		} catch (InstructionUpdateException e) {
			errMsg = e.getMessage();
		}
		assertTrue(errMsg.equals(InstructionUpdateException.ALIGNMENT_IS_NOT_A_POWER_OF_TWO));

		// creating alloca instr to only one i32 type, and updating the align
		// value to 2 should *not* update it to 2, because minimum alignment requirement
		// for i32 is 4
		resultType = Type.getInt32Type(compilationContext, true);
		align = 0;

		try {
			allocaInst = AllocaInst.create(properties, resultType, name, align, null, null);
		} catch (InstructionCreationException ice) { }
		assertNotNull(allocaInst);
		assertTrue(allocaInst.toString().equals("%ptr = alloca i32, align 4"));
		try {
			allocaInst.setAlignment(2, Type.getInt32Type(compilationContext, true));
		} catch (InstructionUpdateException e) {
		}
		assertTrue(allocaInst.toString().equals("%ptr = alloca i32, align 4"));

		// creating alloca instr to only one i32 type, and updating the align
		// value to 536870914
		// should *not* update it to 536870914, because it exceeds the max
		// alignment limit in llvm
		resultType = Type.getInt32Type(compilationContext, true);
		align = 0;
		errMsg = "";

		try {
			allocaInst = AllocaInst.create(properties, resultType, name, align, null, null);
		} catch (InstructionCreationException ice) {
		}
		assertNotNull(allocaInst);
		try {
			allocaInst.setAlignment(536870914, Type
					.getInt32Type(compilationContext, true));
		} catch (InstructionUpdateException e) {
			errMsg = e.getMessage();
		}
		assertTrue(errMsg.equals(InstructionUpdateException.ALIGNMENT_CANNOT_BE_MORE_THAN_MAX_ALIGNMENT));
	}

	// Confirm that all "valid update" of alloca instructions works properly.
	//@Ignore
	@Test
	public void testAllocaInstrUtilityMethods() {
		// Check whether is it a Array Allocation
		long arraySize = 4;
		ArrayType arrayType = new ArrayType(compilationContext, Type.getInt32Type(compilationContext, true), arraySize);
		String name = "ptr";
		Integer align = 0;
		AllocaInst allocaInst = null;
		try {
			allocaInst = AllocaInst.create(properties, arrayType, name, align, null, null);
		} catch (InstructionCreationException ice) {
			ice.printStackTrace();
		}
		assertFalse(allocaInst.isVariableLengthArrayAllocation());

		// Create alloca instruction which is not an array type
		Type resultType = Type.getInt32Type(compilationContext, true);
		try {
			allocaInst = AllocaInst.create(properties, resultType, name, align, null, null);
		} catch (InstructionCreationException ice) { }

		assertFalse(allocaInst.isVariableLengthArrayAllocation());
	}

	// *************************************************************************************************************
	// *********************** TESTS FOR LOAD INSTRUCTIONS
	// *******************************************************
	// *************************************************************************************************************

	// Confirm that all "normal" load instructions are created correctly.
	@Test
	public void testLoadCreation() {
		// creating "normal" **non** atomic Load instructions
		Value value = null;
		IntegerType i32PointeeType = Type.getInt32Type(compilationContext, true);
		try {
			value = new Value(Type.getPointerType(compilationContext, i32PointeeType, 0));
		} catch (TypeCreationException e) {
			e.getMessage(); assertTrue(false);
		}
		value.setName("ptr");
		boolean isVolatile = false;
		String name = "val";
		AtomicOrdering order = null;
		SynchronizationScope synchScope = null;

		LoadInst loadInst = null;
		try {
			loadInst = LoadInst.create(properties, value, name, isVolatile, order,
					synchScope, i32PointeeType.toString(), null);
		} catch (InstructionCreationException ice) {
		}
		assertNotNull(loadInst);
		assertTrue(loadInst.toString().equals("%val = load i32, i32* %ptr, align 4"));

		// Create **atomic** Load Instruction with **atomic ordering** as
		// "Monotonic" Load instructions
		try {
			value = new Value(Type.getPointerType(compilationContext, i32PointeeType, 0));
		} catch (TypeCreationException e) {
			e.getMessage(); assertTrue(false);
		}
		value.setName("ptr");
		isVolatile = false;
		name = "val";
		order = AtomicOrdering.Monotonic;
		synchScope = SynchronizationScope.SingleThread;
		loadInst = null;
		try {
			loadInst = LoadInst.create(properties, value, name, isVolatile, order,
					synchScope, i32PointeeType.toString(), null);
		} catch (InstructionCreationException ice) { }
		assertNotNull(loadInst);
		assertTrue(loadInst.toString().equals("%val = load atomic i32, i32* %ptr singlethread monotonic, align 4"));

		// Create **atomic and single threaded** Load Instruction with
		// **atomic ordering** as "Acquire" Load instructions
		order = AtomicOrdering.Acquire;
		loadInst = null;
		try {
			loadInst = LoadInst.create(properties, value, name, isVolatile, order,
					synchScope, i32PointeeType.toString(), null);
		} catch (InstructionCreationException ice) {
		}
		assertNotNull(loadInst);
		assertTrue(loadInst.toString().equals(
				"%val = load atomic i32, i32* %ptr singlethread acquire, align 4"));

		// creating **atomic and single threaded** Load Instruction with
		// **atomic ordering** as "Release" Load instructions
		order = AtomicOrdering.SequentiallyConsistent;
		loadInst = null;
		try {
			loadInst = LoadInst.create(properties, value, name, isVolatile, order,
					synchScope, i32PointeeType.toString(), null);
		} catch (InstructionCreationException ice) {
		}
		assertNotNull(loadInst);
		assertTrue(loadInst.toString().equals(
				"%val = load atomic i32, i32* %ptr singlethread seq_cst, align 4"));

		// creating **atomic and single threaded** Load Instruction with
		// **atomic ordering** as "Unordered" Load instructions
		order = AtomicOrdering.Unordered;
		loadInst = null;
		try {
			loadInst = LoadInst.create(properties, value, name, isVolatile, order,
					synchScope, i32PointeeType.toString(), null);
		} catch (InstructionCreationException ice) {
		}
		assertNotNull(loadInst);
		assertTrue(loadInst.toString().equals(
				"%val = load atomic i32, i32* %ptr singlethread unordered, align 4"));

		// creating **atomic ,volatile and single threaded** Load instructions
		isVolatile = true;
		loadInst = null;
		order = AtomicOrdering.Monotonic;
		try {
			loadInst = LoadInst.create(properties, value, name, isVolatile, order,
					synchScope, i32PointeeType.toString(), null);
		} catch (InstructionCreationException ice) {
		}
		assertNotNull(loadInst);
		assertTrue(loadInst.toString().equals(
				"%val = load atomic volatile i32, i32* %ptr singlethread monotonic, align 4"));

		// creating **atomic ,volatile and cross threaded** Load instructions
		synchScope = SynchronizationScope.CrossThread;
		loadInst = null;
		try {
			loadInst = LoadInst.create(properties, value, name, isVolatile, order,
					synchScope, i32PointeeType.toString(), null);
		} catch (InstructionCreationException ice) {
		}
		assertNotNull(loadInst);
		assertTrue(loadInst.toString().equals(
				"%val = load atomic volatile i32, i32* %ptr monotonic, align 4"));

		// creating "normal volatile" Load instructions
		isVolatile = true;
		order = null;
		synchScope = null;
		loadInst = null;
		try {
			loadInst = LoadInst.create(properties, value, name, isVolatile, order,
					synchScope, i32PointeeType.toString(), null);
		} catch (InstructionCreationException ice) {
		}
		assertNotNull(loadInst);
		assertTrue(loadInst.toString().equals(
				"%val = load volatile i32, i32* %ptr, align 4"));

		// creating "normal" Load instructions, where pointee is of i8 type
		try {
			value = new Value(Type.getPointerType(compilationContext, Type
					.getInt8Type(compilationContext, true), 0));
		} catch (TypeCreationException e) {
			e.getMessage(); assertTrue(false);
		}
		value.setName("ptr");
		isVolatile = false;
		loadInst = null;
		IntegerType i8PointeeType = Type.getInt8Type(compilationContext, true);
		try {
			loadInst = LoadInst.create(properties, value, name, isVolatile, order,
					synchScope, i8PointeeType.toString(), null);
		} catch (InstructionCreationException ice) { }
		assertNotNull(loadInst);
		assertTrue(loadInst.toString().equals("%val = load i8, i8* %ptr, align 1"));

		// creating "normal" Load instructions, where pointee is of i16 type
		IntegerType i16PointeeType = Type.getInt16Type(compilationContext, true);
		try {
			value = new Value(Type.getPointerType(compilationContext, i16PointeeType, 0));
		} catch (TypeCreationException e) {
			e.getMessage(); assertTrue(false);
		}
		value.setName("ptr");
		isVolatile = false;
		loadInst = null;
		try {
			loadInst = LoadInst.create(properties, value, name, isVolatile, order,
					synchScope, i16PointeeType.toString(), null);
		} catch (InstructionCreationException ice) {
		}
		assertNotNull(loadInst);
		assertTrue(loadInst.toString().equals("%val = load i16, i16* %ptr, align 2"));

		// creating "normal" load instructions, where pointee is of float type
		Type fltPointeeType = Type.getFloatType(compilationContext);
		try {
			value = new Value(Type.getPointerType(compilationContext, fltPointeeType, 0));
		} catch (TypeCreationException e) {
			e.getMessage(); assertTrue(false);
		}
		value.setName("ptr");
		isVolatile = false;
		loadInst = null;
		try {
			loadInst = LoadInst.create(properties, value, name, isVolatile, order,
					synchScope, fltPointeeType.toString(), null);
		} catch (InstructionCreationException ice) { }
		assertNotNull(loadInst);
		assertTrue(loadInst.toString().equals("%val = load float, float* %ptr, align 4"));

		// creating "normal" load instructions, where pointee is of double type
		Type doublePointeeType = Type.getDoubleType(compilationContext);
		try {
			value = new Value(Type.getPointerType(compilationContext, doublePointeeType, 0));
		} catch (TypeCreationException e) {
			e.getMessage(); assertTrue(false);
		}
		value.setName("ptr");
		isVolatile = false;
		loadInst = null;
		try {
			loadInst = LoadInst.create(properties, value, name, isVolatile, order,
					synchScope, doublePointeeType.toString(), null);
		} catch (InstructionCreationException ice) {
		}
		assertNotNull(loadInst);
		assertTrue(loadInst.toString().equals("%val = load double, double* %ptr, align 8"));

		// creating "normal" load instructions, where pointee is of pointer type
		Type pointerTypePointee = null;
		try {
			pointerTypePointee = Type.getPointerType(compilationContext, Type.getDoubleType(compilationContext), 0);
			value = new Value(Type.getPointerType(compilationContext, pointerTypePointee, 0));
		} catch (TypeCreationException e) {
			e.getMessage(); assertTrue(false);
		}
		value.setName("ptr");
		isVolatile = false;
		loadInst = null;
		try {
			loadInst = LoadInst.create(properties, value, name, isVolatile, order, synchScope, pointerTypePointee.toString(), null);
		} catch (InstructionCreationException ice) { }
		assertNotNull(loadInst);
		assertTrue(loadInst.toString().equals( "%val = load double*, double** %ptr, align 8"));
	}

	// Confirm that all "invalid" load instructions throws proper exceptions.
	@Test
	public void testInvalidLoadCreation() {
		// Create load instruction with null value
		String name = "%val";
		AtomicOrdering order = null;
		SynchronizationScope synchScope = null;
		boolean isVolatile = false;
		Value value = null;
		String errMsg = "";
		LoadInst loadInst = null;

		try {
			loadInst = LoadInst.create(properties, value, name, isVolatile, order, synchScope, null, null);
		} catch (InstructionCreationException ice) {
			errMsg = ice.getMessage();
		}
		assertTrue(errMsg.equals(InstructionCreationException.VALUE_CANNOT_BE_NULL));

		// load instruction with pointee as a non-first class type
		value = null;
		Type invalidPointeeType = null;
		try {
			invalidPointeeType = Type.getFunctionType(compilationContext, Type.getVoidType(compilationContext), false, null);
			value = new Value(Type.getPointerType(compilationContext, invalidPointeeType, 0));
		} catch (TypeCreationException e) {
			assertTrue(false);
		}
		value.setName("%ptr");
		errMsg = "";
		loadInst = null;

		try {
			loadInst = LoadInst.create(properties, value, name, isVolatile, order, synchScope, invalidPointeeType.toString(), null);
		} catch (InstructionCreationException ice) {
			errMsg = ice.getMessage();
		}
		assertTrue(errMsg.equals(InstructionCreationException.LOAD_INSTR_SHOULD_HAVE_POINTER_TO_FIRSTCLASS_TYPE_ONLY));

		// load Instruction with pointee as a integer type whose bit width is less than 8
		invalidPointeeType = Type.getInt1Type(compilationContext, false);
		try {
			value = new Value(Type.getPointerType(compilationContext, invalidPointeeType, 0));
		} catch (TypeCreationException e) {
			assertTrue(false);
		}
		value.setName("%ptr");
		errMsg = "";

		try {
			loadInst = LoadInst.create(properties, value, name, isVolatile, order, synchScope, invalidPointeeType.toString(), null);
		} catch (InstructionCreationException ice) {
			errMsg = ice.getMessage();
		}

		// TODO What do we test here?
		IntegerType i64PointeeType = Type.getInt64Type(compilationContext, true);
		try {
			value = new Value(Type.getPointerType(compilationContext, i64PointeeType, 0));
		} catch (TypeCreationException e) {
			e.getMessage(); assertTrue(false);
		}
		value.setName("%ptr");
		errMsg = "";

		try {
			loadInst = LoadInst.create(properties, value, name, isVolatile, order,synchScope, i64PointeeType.toString(), null);
		} catch (InstructionCreationException ice) {
			errMsg = ice.getMessage();
		}

		// With AcquireRelease
		IntegerType i32PointeeType = Type.getInt32Type(compilationContext, true);
		try {
			value = new Value(Type.getPointerType(compilationContext, i32PointeeType, 0));
		} catch (TypeCreationException e) {
			e.getMessage(); assertTrue(false);
		}
		value.setName("%ptr");
		order = AtomicOrdering.AcquireRelease;
		errMsg = "";

		try {
			loadInst = LoadInst.create(properties, value, name, isVolatile, order, synchScope,i32PointeeType.toString(), null);
		} catch (InstructionCreationException ice) {
			errMsg = ice.getMessage();
		}
		assertTrue(errMsg.equals(InstructionCreationException.INVALID_ATOMIC_ORDER_FOR_ATOMIC_LOAD_OR_STORE_INSTR));

		// creating **atomic** Load Instruction with atomic ordering as Release
		try {
			value = new Value(Type.getPointerType(compilationContext, i32PointeeType, 0));
		} catch (TypeCreationException e) {
			e.getMessage(); assertTrue(false);
		}
		value.setName("%ptr");
		order = AtomicOrdering.Release;
		errMsg = "";

		try {
			loadInst = LoadInst.create(properties, value, name, isVolatile, order, synchScope, i32PointeeType.toString(), null);
		} catch (InstructionCreationException ice) {
			errMsg = ice.getMessage();
		}
		assertTrue(errMsg.equals(InstructionCreationException.INVALID_ATOMIC_ORDER_FOR_ATOMIC_LOAD_OR_STORE_INSTR));

		// With atomic load instruction with pointee type not an Integer type
		Type doublePointeeType = Type.getDoubleType(compilationContext);
		try {
			value = new Value(Type.getPointerType(compilationContext, doublePointeeType, 0));
		} catch (TypeCreationException e) {
			e.getMessage(); assertTrue(false);
		}

		value.setName("%ptr");
		order = AtomicOrdering.Unordered;
		errMsg = "";

		try {
			loadInst = LoadInst.create(properties, value, name, isVolatile, order, synchScope, doublePointeeType.toString(), null);
		} catch (InstructionCreationException ice) {
			errMsg = ice.getMessage();
		}
		assertTrue(errMsg.equals(InstructionCreationException.FOR_ATOMIC_LOAD_OR_STORE_INSTR_POINTEE_SHOULD_BE_OF_INTEGER_TYPE));

		// With non-atomic load instruction synchronized
		try {
			value = new Value(Type.getPointerType(compilationContext, i32PointeeType, 0));
		} catch (TypeCreationException e) {
			e.getMessage(); assertTrue(false);
		}
		value.setName("%ptr");
		order = AtomicOrdering.NotAtomic;
		synchScope = SynchronizationScope.SingleThread;
		errMsg = "";

		try {
			loadInst = LoadInst.create(properties, value, name, isVolatile, order, synchScope,i32PointeeType.toString(), null);
		} catch (InstructionCreationException ice) {
			errMsg = ice.getMessage();
		}
		assertTrue(errMsg.equals(InstructionCreationException.NON_ATOMIC_STORE_OR_LOAD_CANNOT_BE_SINGLE_THREADED));

		// creating a Load Instruction with a non-pointer type operand
		value = new Value(Type.getDoubleType(compilationContext));
		value.setName("%ptr");
		order = AtomicOrdering.NotAtomic;
		synchScope = SynchronizationScope.CrossThread;
		errMsg = "";

		try {
			loadInst = LoadInst.create(properties, value, name, isVolatile, order, synchScope, doublePointeeType.toString(), null);
		} catch (InstructionCreationException ice) {
			errMsg = ice.getMessage();
		}
		assertTrue(errMsg.equals(InstructionCreationException.LOAD_INSTR_CAN_ONLY_HAVE_POINTER_AS_AN_OPERAND));
	}

	// Confirm that all "valid" load instructions updates works properly.
	@Test
	public void testValidLoadUpdation() {
		// Update volatile to true.
		IntegerType i32PointeeType = Type.getInt32Type(compilationContext, true);
		Value value = null;
		try {
			value = new Value(Type.getPointerType(compilationContext, i32PointeeType, 0));
		} catch (TypeCreationException e) {
			e.getMessage(); assertTrue(false);
		}
		value.setName("ptr");
		boolean isVolatile = false;
		String name = "val";
		AtomicOrdering order = null;
		SynchronizationScope synchScope = null;

		LoadInst loadInst = null;
		try {
			loadInst = LoadInst.create(properties, value, name, isVolatile, order,	synchScope, i32PointeeType.toString(), null);
		} catch (InstructionCreationException ice) { }
		assertNotNull(loadInst);
		loadInst.setVolatile(true);
		assertTrue(loadInst.toString().equals("%val = load volatile i32, i32* %ptr, align 4"));

		// updating volatile to false.
		loadInst.setVolatile(false);
		assertTrue(loadInst.toString().equals("%val = load i32, i32* %ptr, align 4"));

		// Update alignment
		try {
			loadInst.setAlign(8);
		} catch (InstructionUpdateException e) {
			e.getMessage(); assertTrue(false);
		}
		assertTrue(loadInst.toString().equals("%val = load i32, i32* %ptr, align 4"));

		// Update atomic ordering
		order = AtomicOrdering.Monotonic;
		synchScope = SynchronizationScope.CrossThread;
		try {
			loadInst = LoadInst.create(properties, value, name, isVolatile, order, synchScope, i32PointeeType.toString(), null);
		} catch (InstructionCreationException ice) {
		}
		try {
			loadInst.setAtomic(AtomicOrdering.SequentiallyConsistent);
		} catch (InstructionUpdateException e) {
			e.getMessage(); assertTrue(false);
		}
		assertTrue(loadInst.toString().equals("%val = load atomic i32, i32* %ptr seq_cst, align 4"));

		// updating the synchronization scope for atomic load instructions
		synchScope = SynchronizationScope.SingleThread;
		try {
			loadInst.setSynchScope(synchScope);
		} catch (InstructionUpdateException e) {
			e.getMessage(); assertTrue(false);
		}
		assertTrue(loadInst.toString().equals("%val = load atomic i32, i32* %ptr singlethread seq_cst, align 4"));

		// updating the synchronization scope for non-atomic load instructions
		order = null;
		synchScope = SynchronizationScope.CrossThread;
		try {
			loadInst.setAtomic(order);
			loadInst.setSynchScope(synchScope);
		} catch (InstructionUpdateException e) {
			e.getMessage(); assertTrue(false);
		}
		assertTrue(loadInst.toString().equals("%val = load i32, i32* %ptr, align 4"));
	}

	// Confirm that all "invalid" load instructions updates throws expected exceptions.
	@Test
	public void testInvalidLoadUpates() {
		// Update alignment, not a power of two
		Value value = null;
		IntegerType i32PointeeType = Type.getInt32Type(compilationContext, true);
		try {
			value = new Value(Type.getPointerType(compilationContext, i32PointeeType, 0));
		} catch (TypeCreationException e) {
			e.getMessage(); assertTrue(false);
		}
		value.setName("%ptr");
		boolean isVolatile = false;
		String name = "%val";
		AtomicOrdering order = null;
		SynchronizationScope synchScope = null;

		LoadInst loadInst = null;
		String errMsg = "";
		try {
			loadInst = LoadInst.create(properties, value, name, isVolatile, order,	synchScope, i32PointeeType.toString(), null);
		} catch (InstructionCreationException ice) {
		}
		assertNotNull(loadInst);
		try {
			loadInst.setAlign(6);
		} catch (InstructionUpdateException e) {
			errMsg = e.getMessage();
		}
		assertTrue(errMsg
				.equals(InstructionUpdateException.ALIGNMENT_IS_NOT_A_POWER_OF_TWO));

		// updating the atomic ordering, for atomic load instr to Release or
		// AcquiredRelease
		order = AtomicOrdering.Acquire;
		synchScope = SynchronizationScope.CrossThread;
		try {
			loadInst = LoadInst.create(properties, value, name, isVolatile, order, synchScope, i32PointeeType.toString(), null);
		} catch (InstructionCreationException ice) {
		}
		errMsg = "";
		order = AtomicOrdering.AcquireRelease;
		try {
			loadInst.setAtomic(order);
		} catch (InstructionUpdateException e) {
			errMsg = e.getMessage();
		}
		assertTrue(errMsg.equals(InstructionUpdateException.INVALID_ATOMIC_ORDER_FOR_LOAD_OR_STORE_INSTR));

		// Update the synchronization scope for non-atomic load instructions to SingleThreaded
		order = null;
		synchScope = SynchronizationScope.SingleThread;
		errMsg = "";
		try {
			loadInst.setAtomic(order);
			loadInst.setSynchScope(synchScope);
		} catch (InstructionUpdateException e) {
			errMsg = e.getMessage();
		}
		assertTrue(errMsg.equals(InstructionUpdateException.ONLY_ATOMIC_LOAD_OR_STORE_INSTR_CAN_BE_SINGLE_THREADED));
	}

	// *************************************************************************************************************
	// *********************** TESTS FOR STORE INSTRUCTIONS
	// *******************************************************
	// *************************************************************************************************************

	// Confirm that all "normal" store instructions are created correctly.
	@Test
	public void testStoreCreation() {
		// creating "normal" **non** atomic Store instructions
		APInt val = new APInt(32, "3", false);
		ConstantInt firstOp = null;
		try {
			firstOp = new ConstantInt(Type.getInt32Type(compilationContext, true), val);
		} catch (InstantiationException e2) {}
		assertNotNull(firstOp);
		Value secondOp = null;
		try {
			secondOp = new Value(Type.getPointerType(compilationContext, Type
					.getInt32Type(compilationContext, true), 0));
		} catch (TypeCreationException e) {
			e.getMessage(); assertTrue(false);
		}
		secondOp.setName("ptr");
		boolean isVolatile = false;
		AtomicOrdering atomicOrdering = null;
		SynchronizationScope synchScope = null;

		StoreInst storeInst = null;
		try {
			storeInst = StoreInst.create(properties, firstOp, secondOp, isVolatile, atomicOrdering,
					synchScope, null);
		} catch (InstructionCreationException ice) {
		}
		assertNotNull(storeInst);
		assertTrue(storeInst.toString().equals("store i32 3, i32* %ptr, align 4"));

		// creating "normal" **non** atomic Store instructions
		APFloat apFloat = new APFloat(APFloat.IEEEdouble, "10.23");
		ConstantFP firstOp1 = null;
		try {
			firstOp1 = new ConstantFP(Type.getDoubleType(compilationContext),
					apFloat);
		} catch (InstantiationException e1) {
			e1.getMessage(); assertTrue(false);
		}
		secondOp = null;
		try {
			secondOp = new Value(Type.getPointerType(compilationContext, Type
					.getDoubleType(compilationContext), 0));
		} catch (TypeCreationException e) {
			e.getMessage(); assertTrue(false);
		}
		secondOp.setName("ptr");

		storeInst = null;
		try {
			storeInst = StoreInst.create(properties, firstOp1, secondOp, isVolatile, atomicOrdering,
					synchScope, null);
		} catch (InstructionCreationException ice) {
		}
		assertNotNull(storeInst);
		assertTrue(storeInst.toString().equals("store double 1.023000e+01, double* %ptr, align 8"));

		// creating "normal" **non** atomic Store instructions
		Value firstOp2 = null;
		firstOp2 = new Value(Type.getDoubleType(compilationContext));
		firstOp2.setName("val");
		secondOp = null;
		try {
			secondOp = new Value(Type.getPointerType(compilationContext, Type.getDoubleType(compilationContext), 0));
			secondOp.setName("ptr");
		} catch (TypeCreationException e) {
			e.getMessage(); assertTrue(false);
		}

		storeInst = null;
		try {
			storeInst = StoreInst.create(properties, firstOp2, secondOp, isVolatile, atomicOrdering,
					synchScope, null);
		} catch (InstructionCreationException ice) {
			ice.printStackTrace();
		}
		assertNotNull(storeInst);
		assertTrue(storeInst.toString().equals("store double %val, double* %ptr, align 8"));

		// creating "normal" **non** atomic Store instructions
		PointerType pointerType = null;
		try {
			pointerType = Type.getPointerType(compilationContext, Type.getDoubleType(compilationContext), 0);
			firstOp2 = new Value(pointerType);
			firstOp2.setName("val");
			secondOp = new Value(Type.getPointerType(compilationContext, pointerType, 0));
			secondOp.setName("ptr");
		} catch (TypeCreationException e1) {
			e1.getMessage(); assertTrue(false);
		}

		storeInst = null;
		try {
			storeInst = StoreInst.create(properties, firstOp2, secondOp, isVolatile, atomicOrdering,
					synchScope, null);
		} catch (InstructionCreationException ice) {
			ice.printStackTrace();
		}
		assertNotNull(storeInst);
		assertTrue(storeInst.toString().equals("store double* %val, double** %ptr, align 8"));

		// creating "normal" **non** atomic Store instructions
		StructType structType = new StructType(compilationContext, false,
				"pqr", false, Type.getInt32Type(compilationContext, true));
		try {
			pointerType = Type
					.getPointerType(compilationContext, structType, 0);
			firstOp2 = new Value(pointerType);
			firstOp2.setName("val");
			secondOp = new Value(Type.getPointerType(compilationContext, pointerType, 0));
			secondOp.setName("ptr");
		} catch (TypeCreationException e1) {
			e1.getMessage(); assertTrue(false);
		}

		storeInst = null;
		try {
			storeInst = StoreInst.create(properties, firstOp2, secondOp, isVolatile, atomicOrdering,
					synchScope, null);
		} catch (InstructionCreationException ice) {
			ice.printStackTrace();
		}
		assertNotNull(storeInst);
		assertTrue(storeInst.toString().equals("store %pqr* %val, %pqr** %ptr, align 8"));

		firstOp2 = new Value(Type.getInt32Type(compilationContext, true));
		firstOp2.setName("val");
		secondOp = null;
		try {
			secondOp = new Value(Type.getPointerType(compilationContext, Type
					.getInt32Type(compilationContext, true), 0));
		} catch (TypeCreationException e) {
			e.getMessage(); assertTrue(false);
		}
		secondOp.setName("ptr");
		isVolatile = true;
		atomicOrdering = AtomicOrdering.Acquire;
		synchScope = SynchronizationScope.SingleThread;

		storeInst = null;
		try {
			storeInst = StoreInst.create(properties, firstOp2, secondOp, isVolatile, atomicOrdering,
					synchScope, null);
		} catch (InstructionCreationException ice) {
			ice.printStackTrace();
		}
		assertNotNull(storeInst);
		assertTrue(storeInst.toString().equals("store atomic volatile i32 %val, i32* %ptr singlethread acquire, align 4"));
	}

	// Confirm that all "invalid" store instructions creation throws expected
	// exceptions.
	@Test
	public void testInvalidStoreCreation() {
		//creating store instruction with first argument as null
		ConstantInt firstOp = null;
		Value secondOp = null;
		try {
			secondOp = new Value(Type.getPointerType(compilationContext, Type
					.getDoubleType(compilationContext), 0));
		} catch (TypeCreationException e) {
			e.getMessage(); assertTrue(false);
		}
		secondOp.setName("%ptr");
		boolean isVolatile = false;
		AtomicOrdering atomicOrdering = null;
		SynchronizationScope synchScope = null;
		String errMsg = "";

		StoreInst storeInst = null;
		try {
			storeInst = StoreInst.create(properties, firstOp, secondOp, isVolatile, atomicOrdering,
					synchScope, null);
		} catch (InstructionCreationException ice) {
			errMsg = ice.getMessage();
		}
		assertTrue(errMsg
				.equals(InstructionCreationException.FIRST_ARG_OF_STORE_INSTR_CANNOT_BE_NULL));

		//creating store instruction with second argument as null
		APInt val = new APInt(32, "3", false);
		firstOp = null;
		try {
			firstOp = new ConstantInt(Type.getInt32Type(compilationContext, true), val);
		} catch (InstantiationException e2) {}
		assertNotNull(firstOp);
		secondOp = null;
		errMsg = "";

		storeInst = null;
		try {
			storeInst = StoreInst.create(properties, firstOp, secondOp, isVolatile, atomicOrdering,
					synchScope, null);
		} catch (InstructionCreationException ice) {
			errMsg = ice.getMessage();
		}
		assertTrue(errMsg
				.equals(InstructionCreationException.SECOND_ARG_OF_STORE_INSTR_CANNOT_BE_NULL));

		// creating "normal" **non** atomic Store instructions, where pointer
		// type and value type don't match
		val = new APInt(32, "3", false);
		firstOp = null;
		try {
			firstOp = new ConstantInt(Type.getInt32Type(compilationContext, true), val);
		} catch (InstantiationException e2) {}
		assertNotNull(firstOp);
		secondOp = null;
		try {
			secondOp = new Value(Type.getPointerType(compilationContext, Type
					.getDoubleType(compilationContext), 0));
		} catch (TypeCreationException e) {
			e.getMessage(); assertTrue(false);
		}
		secondOp.setName("%ptr");
		errMsg = "";

		storeInst = null;
		try {
			storeInst = StoreInst.create(properties, firstOp, secondOp, isVolatile, atomicOrdering,
					synchScope, null);
		} catch (InstructionCreationException ice) {
			errMsg = ice.getMessage();
		}
		assertTrue(errMsg
				.equals(InstructionCreationException.STORED_VALUE_AND_POINTER_TYPE_DONOT_MATCH));

		// creating "normal" **non** atomic Store instructions, where the second
		// operand of the store instr is not a pointer type
		secondOp = null;
		secondOp = new Value(Type.getDoubleType(compilationContext));
		secondOp.setName("%ptr");
		errMsg = "";

		storeInst = null;
		try {
			storeInst = StoreInst.create(properties, firstOp, secondOp, isVolatile, atomicOrdering,
					synchScope, null);
		} catch (InstructionCreationException ice) {
			errMsg = ice.getMessage();
		}
		assertTrue(errMsg
				.equals(InstructionCreationException.THE_2ND_OPERAND_TO_A_STORE_INSTR_SHOULD_BE_POINTER_TYPE));

		// creating "normal" **non** atomic Store instructions, where stored
		// value is not of first class type
		Value firstOperand = new Value(Type.getVoidType(compilationContext));
		secondOp = null;
		try {
			secondOp = new Value(Type.getPointerType(compilationContext, Type
					.getDoubleType(compilationContext), 0));
		} catch (TypeCreationException e) {
			e.getMessage(); assertTrue(false);
		}
		secondOp.setName("%ptr");
		errMsg = "";

		try {
			storeInst = StoreInst.create(properties, firstOperand, secondOp, isVolatile, atomicOrdering,
					synchScope, null);
		} catch (InstructionCreationException ice) {
			errMsg = ice.getMessage();
		}
		assertTrue(errMsg
				.equals(InstructionCreationException.STORE_INSTR_MUST_STORE_VAL_OF_FIRST_CLASS_TYPE));

		// creating "normal" atomic Store instructions, where atomic ordering is
		// either AcquiredRelease or Release
		val = new APInt(32, "3", false);
		firstOp = null;
		try {
			firstOp = new ConstantInt(Type.getInt32Type(compilationContext, true), val);
		} catch (InstantiationException e2) {}
		assertNotNull(firstOp);
		secondOp = null;
		try {
			secondOp = new Value(Type.getPointerType(compilationContext, Type
					.getInt32Type(compilationContext, true), 0));
		} catch (TypeCreationException e) {
			e.getMessage(); assertTrue(false);
		}
		secondOp.setName("%ptr");
		isVolatile = false;
		atomicOrdering = AtomicOrdering.AcquireRelease;
		synchScope = null;
		errMsg = "";

		storeInst = null;
		try {
			storeInst = StoreInst.create(properties, firstOp, secondOp, isVolatile, atomicOrdering,
					synchScope, null);
		} catch (InstructionCreationException ice) {
			errMsg = ice.getMessage();
		}
		assertTrue(errMsg
				.equals(InstructionCreationException.INVALID_ATOMIC_ORDER_FOR_ATOMIC_LOAD_OR_STORE_INSTR));

		// creating "normal" atomic Store instructions, where stored value is
		// not an Integer Type
		secondOp = null;
		try {
			secondOp = new Value(Type.getPointerType(compilationContext, Type
					.getDoubleType(compilationContext), 0));
		} catch (TypeCreationException e) {
			e.getMessage(); assertTrue(false);
		}
		secondOp.setName("%ptr");
		APFloat apFloat = new APFloat(APFloat.IEEEdouble, "11.2");
		ConstantFP constantFP = null;
		try {
			constantFP = new ConstantFP(Type
					.getDoubleType(compilationContext), apFloat);
		} catch (InstantiationException e1) {
			e1.getMessage(); assertTrue(false);
		}
		isVolatile = false;
		atomicOrdering = AtomicOrdering.Acquire;
		synchScope = null;
		errMsg = "";

		storeInst = null;
		try {
			storeInst = StoreInst.create(properties, constantFP, secondOp, isVolatile, atomicOrdering,
					synchScope, null);
		} catch (InstructionCreationException ice) {
			errMsg = ice.getMessage();
		}
		assertTrue(errMsg
				.equals(InstructionCreationException.FOR_ATOMIC_LOAD_OR_STORE_INSTR_POINTEE_SHOULD_BE_OF_INTEGER_TYPE));

		// creating "normal" **non** atomic Store instructions, where
		// Synchronization Scope is SingleThreaded

		atomicOrdering = AtomicOrdering.NotAtomic;
		synchScope = SynchronizationScope.SingleThread;
		errMsg = "";

		storeInst = null;
		try {
			storeInst = StoreInst.create(properties, constantFP, secondOp, isVolatile, atomicOrdering,
					synchScope, null);
		} catch (InstructionCreationException ice) {
			errMsg = ice.getMessage();
		}
		assertTrue(errMsg
				.equals(InstructionCreationException.NON_ATOMIC_STORE_OR_LOAD_CANNOT_BE_SINGLE_THREADED));

		// creating a atomic store instruction where the stored value is a
		// integer of bit width less than 8

		val = new APInt(1, "0", false);
		firstOp = null;
		try {
			firstOp = new ConstantInt(Type.getInt1Type(compilationContext, false), val);
		} catch (InstantiationException e1) {}
		assertNotNull(firstOp);
		secondOp = null;
		try {
			secondOp = new Value(Type.getPointerType(compilationContext, Type
					.getInt1Type(compilationContext, false), 0));
		} catch (TypeCreationException e) {
			e.getMessage(); assertTrue(false);
		}
		secondOp.setName("%ptr");
		isVolatile = false;
		atomicOrdering = AtomicOrdering.Acquire;
		synchScope = null;
		errMsg = "";

		storeInst = null;
		try {
			storeInst = StoreInst.create(properties, firstOp, secondOp, isVolatile, atomicOrdering,
					synchScope, null);
		} catch (InstructionCreationException ice) {
			errMsg = ice.getMessage();
		}
		//		assertTrue(errMsg
		//				.equals(InstructionCreationException.IF_FOR_LOAD_OR_STORE_INSTR_POINTEE_IS_OF_INTEGER_TYPE_THEN_BITS_SHOULD_BE_BETWEEN_8_AND_TARGET_SPECIFIC_SIZE_LIMIT));

		// creating a atomic store instruction where the stored value is a
		// integer of bit width more than target specific size limit
		val = new APInt(64, "45", false);
		firstOp = null;
		try {
			firstOp = new ConstantInt(Type.getInt64Type(compilationContext, true), val);
		} catch (InstantiationException e1) {}
		assertNotNull(firstOp);
		secondOp = null;
		try {
			secondOp = new Value(Type.getPointerType(compilationContext, Type
					.getInt64Type(compilationContext, true), 0));
		} catch (TypeCreationException e) {
			e.getMessage(); assertTrue(false);
		}
		secondOp.setName("%ptr");
		errMsg = "";

		storeInst = null;
		try {
			storeInst = StoreInst.create(properties, firstOp, secondOp, isVolatile, atomicOrdering,
					synchScope, null);
		} catch (InstructionCreationException ice) {
			errMsg = ice.getMessage();
		}
		//		assertTrue(errMsg
		//				.equals(InstructionCreationException.IF_FOR_LOAD_OR_STORE_INSTR_POINTEE_IS_OF_INTEGER_TYPE_THEN_BITS_SHOULD_BE_BETWEEN_8_AND_TARGET_SPECIFIC_SIZE_LIMIT));
	}

	// Confirm that all "valid" updates works correctly
	@Test
	public void testStoreUpdate() {
		// updating the alignment of a store instruction
		APInt val = new APInt(32, "3", false);
		ConstantInt firstOp = null;
		try {
			firstOp = new ConstantInt(Type.getInt32Type(compilationContext, true), val);
		} catch (InstantiationException e1) {}
		assertNotNull(firstOp);
		Value secondOp = null;
		try {
			secondOp = new Value(Type.getPointerType(compilationContext, Type
					.getInt32Type(compilationContext, true), 0));
		} catch (TypeCreationException e) {
			e.getMessage(); assertTrue(false);
		}
		secondOp.setName("ptr");
		boolean isVolatile = false;
		AtomicOrdering atomicOrdering = null;
		SynchronizationScope synchScope = null;

		StoreInst storeInst = null;
		try {
			storeInst = StoreInst.create(properties, firstOp, secondOp, isVolatile, atomicOrdering,
					synchScope, null);
		} catch (InstructionCreationException ice) {
		}
		assertNotNull(storeInst);
		try {
			storeInst.setAlign(8);
		} catch (InstructionUpdateException e) {
			e.getMessage(); assertTrue(false);
		}
		assertTrue(storeInst.toString().equals("store i32 3, i32* %ptr, align 4"));
	}

	// Confirm that all "invalid" updates throws correct exceptions
	@Test
	public void testStoreInvalidUpdate() {
		// updating the atomic order of a atomic store instruction to
		// AcquiredRelease or Release
		APInt val = new APInt(32, "3", false);
		ConstantInt firstOp = null;
		try {
			firstOp = new ConstantInt(Type.getInt32Type(compilationContext, true), val);
		} catch (InstantiationException e1) {}
		assertNotNull(firstOp);
		Value secondOp = null;
		try {
			secondOp = new Value(Type.getPointerType(compilationContext, Type
					.getInt32Type(compilationContext, true), 0));
		} catch (TypeCreationException e) {
			e.getMessage(); assertTrue(false);
		}
		secondOp.setName("%ptr");
		boolean isVolatile = false;
		AtomicOrdering atomicOrdering = AtomicOrdering.Acquire;
		SynchronizationScope synchScope = null;
		String errMsg = "";

		StoreInst storeInst = null;
		try {
			storeInst = StoreInst.create(properties, firstOp, secondOp, isVolatile, atomicOrdering,
					synchScope, null);
		} catch (InstructionCreationException ice) {
		}
		assertNotNull(storeInst);
		try {
			storeInst.setAtomic(AtomicOrdering.AcquireRelease);
		} catch (InstructionUpdateException e) {
			errMsg = e.getMessage();
		}
		assertTrue(errMsg
				.equals(InstructionUpdateException.INVALID_ATOMIC_ORDER_FOR_LOAD_OR_STORE_INSTR));

		// updating atomic order of a non-atomic store instruction
		atomicOrdering = AtomicOrdering.NotAtomic;
		try {
			storeInst = StoreInst.create(properties, firstOp, secondOp, isVolatile, atomicOrdering,
					synchScope, null);
		} catch (InstructionCreationException ice) {
		}
		assertNotNull(storeInst);
		try {
			storeInst.setAtomic(AtomicOrdering.Acquire);
		} catch (InstructionUpdateException e) {
			errMsg = e.getMessage();
		}
		assertTrue(errMsg
				.equals(InstructionUpdateException.ATOMIC_ORDERING_CAN_BE_SET_ONLY_FOR_ATOMIC_LOAD_OR_STORE_INSTR));

		// updating the Synchronization scope of a non-atomic instruction to
		// SingleThreaded
		errMsg = "";
		try {
			storeInst.setSynchScope(SynchronizationScope.SingleThread);
		} catch (InstructionUpdateException e) {
			errMsg = e.getMessage();
		}
		assertTrue(errMsg
				.equals(InstructionUpdateException.ONLY_ATOMIC_LOAD_OR_STORE_INSTR_CAN_BE_SINGLE_THREADED));
	}

	// *************************************************************************************************************
	// *********************** TESTS FOR GET ELEMENT POINTER INSTRUCTIONS
	// *******************************************************
	// *************************************************************************************************************

	// Confirm that all "normal" GetElementPtr instructions are created
	// correctly.
	@Test
	public void testGetElementPtrCreation() {
		// Create "normal" getElementPtr instructions, which tries to calculate the address of a member of a structure
		StructType structType = new StructType(compilationContext, true, "pqr", false, 
				Type.getInt32Type(compilationContext, true), Type
				.getFloatType(compilationContext));
		List<Pair<Value, Type>> indexVsType = new ArrayList<Pair<Value, Type>>();
		PointerType pointerType = null;
		try {
			pointerType = Type.getPointerType(compilationContext, structType, 0);
		} catch (TypeCreationException e3) {
			e3.printStackTrace();
			System.exit(-1);
		}
		Value value = new Value(pointerType);
		value.setName("s");
		APInt apInt1 = new APInt(32, "0", false);
		ConstantInt constantInt1 = null;
		try {
			constantInt1 = new ConstantInt(Type.getInt32Type(compilationContext, true), apInt1);
			Pair<Value, Type> entry_IndexVsType = new Pair<Value, Type>(constantInt1, pointerType);
			indexVsType.add(entry_IndexVsType);
		} catch (InstantiationException e1) {}
		assertNotNull(constantInt1);
		APInt apInt2 = new APInt(32, "1", false);
		ConstantInt constantInt2 = null;
		try {
			constantInt2 = new ConstantInt(Type.getInt32Type(compilationContext, true), apInt2);
			Pair<Value, Type> entry_IndexVsType = new Pair<Value, Type>(constantInt2, Type.getFloatType(compilationContext));
			indexVsType.add(entry_IndexVsType);
		} catch (InstantiationException e1) {}
		assertNotNull(constantInt2);
		String name = "res";
		GetElementPtrInst getElementPtrInst = null;
		try {
			getElementPtrInst = GetElementPtrInst.create(name, value, indexVsType, structType.toString(), null);
		} catch (InstructionCreationException e) { }

		assertNotNull(getElementPtrInst);
		assertTrue(getElementPtrInst.toString().equals("%res = getelementptr %pqr, %pqr* %s, i32 0, i32 1"));

		// Create "normal" getElementPtr instruction, which tries to calculate the address of an element of an array
		ArrayType arrayType = new ArrayType(compilationContext, Type.getInt32Type(compilationContext, true), 4);
		indexVsType = new ArrayList<Pair<Value, Type>>();
		try {
			pointerType = Type.getPointerType(compilationContext, arrayType, 0);
		} catch (TypeCreationException e2) {
			e2.printStackTrace();
			System.exit(-1);
		}
		value = new Value(pointerType);
		value.setName("s");
		apInt1 = new APInt(32, "0", false);
		constantInt1 = null;
		try {
			constantInt1 = new ConstantInt(Type.getInt32Type(compilationContext, true), apInt1);
			Pair<Value, Type> entry_IndexVsType = new Pair<Value, Type>(constantInt1, pointerType);
			indexVsType.add(entry_IndexVsType);
		} catch (InstantiationException e1) {}
		assertNotNull(constantInt1);
		apInt2 = new APInt(32, "1", false);
		constantInt2 = null;
		try {
			constantInt2 = new ConstantInt(Type.getInt32Type(compilationContext, true), apInt2);
			Pair<Value, Type> entry_IndexVsType = new Pair<Value, Type>(constantInt2, Type.getInt32Type(compilationContext, true));
			indexVsType.add(entry_IndexVsType);
		} catch (InstantiationException e1) {}
		assertNotNull(constantInt2);

		getElementPtrInst = null;
		try {
			getElementPtrInst = GetElementPtrInst.create(name, value, indexVsType, arrayType.toString(), null);
		} catch (InstructionCreationException e) {
			e.printStackTrace();
		}
		assertNotNull(getElementPtrInst);
		assertTrue(getElementPtrInst.toString().equals("%res = getelementptr [4 x i32], [4 x i32]* %s, i32 0, i32 1"));
	}
}

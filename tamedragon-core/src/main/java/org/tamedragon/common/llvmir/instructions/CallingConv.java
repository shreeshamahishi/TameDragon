package org.tamedragon.common.llvmir.instructions;

public enum CallingConv {

	// C - The default llvm calling convention, compatible with C.  This
	// convention is the only calling convention that supports varargs calls.
	// As with typical C calling conventions, the callee/caller have to
	// tolerate certain amounts of prototype mismatch.
	C (0),

	// Generic LLVM calling conventions.  None of these calling conventions
	// support varargs calls, and all assume that the caller and callee
	// prototype exactly match.

	// Fast - This calling convention attempts to make calls as fast as
	// possible (e.g. by passing things in registers).
	Fast(8),

	// Cold - This calling convention attempts to make code in the caller as
	// efficient as possible under the assumption that the call is not commonly
	// executed.  As such, these calls often preserve all registers so that the
	// call does not break any live ranges in the caller side.
	Cold(9),

	// GHC - Calling convention used by the Glasgow Haskell Compiler (GHC).
	GHC(10),

	// Target - This is the start of the target-specific calling conventions,
	// e.g. fastcall and thiscall on X86.
	FirstTargetCC(64),

	// X86_StdCall - stdcall is the calling conventions mostly used by the
	// Win32 API. It is basically the same as the C convention with the
	// difference in that the callee is responsible for popping the arguments
	// from the stack.
	X86_StdCall(64),

	// X86_FastCall - 'fast' analog of X86_StdCall. Passes first two arguments
	// in ECX:EDX registers, others - via stack. Callee is responsible for
	// stack cleaning.
	X86_FastCall(65),

	// ARM_APCS - ARM Procedure Calling Standard calling convention (obsolete,
	// but still used on some targets).
	ARM_APCS(66),

	// ARM_AAPCS - ARM Architecture Procedure Calling Standard calling
	// convention (aka EABI). Soft float variant.
	ARM_AAPCS(67),

	// ARM_AAPCS_VFP - Same as ARM_AAPCS, but uses hard floating point ABI.
	ARM_AAPCS_VFP(68),

	// MSP430_INTR - Calling convention used for MSP430 interrupt routines.
	MSP430_INTR(69),

	// X86_ThisCall - Similar to X86_StdCall. Passes first argument in ECX,
	// others via stack. Callee is responsible for stack cleaning. MSVC uses
	// this by default for methods in its ABI.
	X86_ThisCall(70),

	// PTX_Kernel - Call to a PTX kernel.
	// Passes all arguments in parameter space.
	PTX_Kernel(71),

	// PTX_Device - Call to a PTX device function.
	// Passes all arguments in register or parameter space.
	PTX_Device(72),

	// MBLAZE_INTR - Calling convention used for MBlaze interrupt routines.
	MBLAZE_INTR(73),

	// MBLAZE_INTR - Calling convention used for MBlaze interrupt support
	// routines (i.e. GCC's save_volatiles attribute).
	MBLAZE_SVOL(74);

	private int value;

	private CallingConv(int value){
		this.value = value;
	}

	public int getTypeIdValue(){
		return value;
	}
}

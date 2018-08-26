package org.tamedragon.common.aliasanalysis;

public enum ModRefResult {
	NO_MOD_REF(0) ,              
	REF(1),   		 
	MOD(2),         
	MOD_REF(3);            

	private int value;

	private ModRefResult(int value){
		this.value = value;
	}

	public int getModRefValue(){
		return value;
	}
}

enum ModRefAttributes {
	NOWHERE(0) ,              
	ARG_POINTEES(4),   		 
	ANYWHERE(8 | ARG_POINTEES.getModRefAttributesValue());        

	private int value;

	private ModRefAttributes(int value){
		this.value = value;
	}

	public int getModRefAttributesValue(){
		return value;
	}
}

/// ModRefBehavior - Summary of how a function affects memory in the program.
/// Loads from constant globals are not considered memory accesses for this
/// interface.  Also, functions may freely modify stack space local to their
/// invocation without having to report it through these interfaces.
enum ModRefBehavior {
	/// DoesNotAccessMemory - This function does not perform any non-local loads
	/// or stores to memory.
	///
	/// This property corresponds to the GCC 'const' attribute.
	/// This property corresponds to the LLVM IR 'readnone' attribute.
	/// This property corresponds to the IntrNoMem LLVM intrinsic flag.
	DoesNotAccessMemory(ModRefAttributes.NOWHERE.getModRefAttributesValue() | ModRefResult.NO_MOD_REF.getModRefValue()),

	/// OnlyReadsArgumentPointees - The only memory references in this function
	/// (if it has any) are non-volatile loads from objects pointed to by its
	/// pointer-typed arguments, with arbitrary offsets.
	///
	/// This property corresponds to the IntrReadArgMem LLVM intrinsic flag.
	OnlyReadsArgumentPointees(ModRefAttributes.ARG_POINTEES.getModRefAttributesValue() | ModRefResult.REF.getModRefValue()),

	/// OnlyAccessesArgumentPointees - The only memory references in this
	/// function (if it has any) are non-volatile loads and stores from objects
	/// pointed to by its pointer-typed arguments, with arbitrary offsets.
	///
	/// This property corresponds to the IntrReadWriteArgMem LLVM intrinsic flag.
	OnlyAccessesArgumentPointees(ModRefAttributes.ARG_POINTEES.getModRefAttributesValue() | ModRefResult.MOD_REF.getModRefValue()),

	/// OnlyReadsMemory - This function does not perform any non-local stores or
	/// volatile loads, but may read from any memory location.
	///
	/// This property corresponds to the GCC 'pure' attribute.
	/// This property corresponds to the LLVM IR 'readonly' attribute.
	/// This property corresponds to the IntrReadMem LLVM intrinsic flag.
	OnlyReadsMemory(ModRefAttributes.ANYWHERE.getModRefAttributesValue() | ModRefResult.REF.getModRefValue()),

	/// UnknownModRefBehavior - This indicates that the function could not be
	/// classified into one of the behaviors above.
	UnknownModRefBehavior(ModRefAttributes.ANYWHERE.getModRefAttributesValue() | ModRefResult.MOD_REF.getModRefValue());
	
	private int value;

	private ModRefBehavior(int value){
		this.value = value;
	}

	public int getModRefBehaviorValue(){
		return value;
	}
}

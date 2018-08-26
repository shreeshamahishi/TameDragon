package org.tamedragon.common.llvmir.types;

import java.util.List;

import org.tamedragon.common.llvmir.instructions.Instruction;
import org.tamedragon.common.llvmir.types.Type.TypeID;


public class GlobalVariable extends GlobalValue {
	private boolean isConstantGlobal = true;           // Is this a global constant?
	private boolean isThreadLocalSymbol = true;        // Is this symbol "Thread Local"?

	/**
	 * If a parent module is specified, the global is automatically inserted into the end of the specified modules global list.
	 * @param type
	 * @param isConstant
	 * @param linkage
	 * @param initializer
	 * @param name
	 * @param threadLocal
	 */
	public GlobalVariable(PointerType type, boolean isConstant, LinkageTypes linkage,List<Value> initializer, String name,
			boolean threadLocal){
		super(type, initializer, linkage, name);
		this.isConstantGlobal = isConstant;
		this.isThreadLocalSymbol = threadLocal;
	}

	/**
	 * This creates a global and inserts it before the specified other global.
	 * @param m
	 * @param ty
	 * @param isConstant
	 * @param linkage
	 * @param initializer
	 * @param name
	 * @param insertBefore
	 * @param threadLocal
	 */
	public GlobalVariable(Module m, PointerType ty, boolean isConstant,
			LinkageTypes linkage, List<Value> initializer, String name,
			GlobalVariable insertBefore, boolean threadLocal){
		this(ty, isConstant,linkage,initializer,name,threadLocal);
		this.parent = m;
		if(insertBefore == null){
			parent.getGlobalVariables().add(this);
		}
		else{
			parent.insertBefore(this, insertBefore);
		}
	}

	/**
	 * Unless a global variable isExternal(), it has an initializer.  The initializer 
	 * for the global variable/constant is held by Initializer if an initializer is specified.
	 */
	public boolean hasInitializer() {
		return !isDeclaration(); 
	}

	/**
	 * Whether the global variable has an initializer, and any other instances of 
	 * the global (this can happen due to weak linkage) are guaranteed to have the same initializer.
	 * Note that if you want to transform a global, you must use hasUniqueInitializer() instead, 
	 * because of the *_odr linkage type.
	 * 
	 * Example:
	 * 
	 * @a = global SomeType* null - Initializer is both definitive and unique.
	 * 
	 * @b = global weak SomeType* null - Initializer is neither definitive nor unique.
	 * 
	 * @c = global weak_odr SomeType* null - Initializer is definitive, but not unique.
	 */
	public boolean hasDefinitiveInitializer() {
		return hasInitializer() &&
				// The initializer of a global variable with weak linkage may change at
				// link time.
				!mayBeOverridden();
	}

	/**
	 * Whether the global variable has an initializer, and any changes made 
	 * to the initializer will turn up in the final executable.
	 * @return
	 */
	public boolean hasUniqueInitializer(){
		return hasInitializer() &&
				// It's not safe to modify initializers of global variables with weak
				// linkage, because the linker might choose to discard the initializer and
				// use the initializer from another instance of the global variable
				// instead. It is wrong to modify the initializer of a global variable
				// with *_odr linkage because then different instances of the global may
				// have different initializers, breaking the One Definition Rule.
				!isWeakForLinker();
	}

	/**
	 * Return the initializer for this global variable.  It is illegal to call this
	 *  method if the global is external, because we cannot tell what the value is initialized to!
	 * @return
	 * @throws RuntimeException
	 */
	public Value getInitializer() throws RuntimeException{
		if(!hasInitializer()){
			throw new RuntimeException("GV doesn't have initializer!");
		}
		return getOperand(0);
	}

	/**
	 * Sets the initializer for this global variable, removing any existing initializer
	 * if InitVal==NULL.  If this GV has type T*, the initializer must have type T.
	 * @param initVal
	 * @throws Exception
	 */
	public void setInitializer(Constant initVal) throws Exception{
		if (initVal == null) {
			if (hasInitializer()) {
				setOperand(0, null);
			}
		} else {
			if(initVal.getType() != getType()){
				throw new Exception("Initializer type must match GlobalVariable type");
			}
			if (!hasInitializer())
				setOperand(0, initVal);
		}
	}

	/**
	 * If the value is a global constant, its value is immutable throughout the
	 * runtime execution of the program.  Assigning a value into the constant
	 * leads to undefined behavior.
	 * @return
	 */
	public boolean isConstant() {
		return isConstantGlobal; 
	}

	public void setConstant(boolean val) {
		isConstantGlobal = val; 
	}

	/**
	 * If the value is "Thread Local", its value isn't shared by the threads.
	 * @return
	 */
	public boolean isThreadLocal() {
		return isThreadLocalSymbol; 
	}

	public void setThreadLocal(boolean val) {
		isThreadLocalSymbol = val; 
	}

	/**
	 * copy all additional attributes (those not needed to create a 
	 * GlobalVariable) from the GlobalVariable Src to this one.
	 */
	public void copyAttributesFrom(GlobalValue src) throws RuntimeException{
		if(!(src instanceof GlobalVariable)){
			throw new RuntimeException("Expected a GlobalVariable!");
		}
		copyAttributesFrom(src);
		GlobalVariable srcVar = (GlobalVariable)src;
		setThreadLocal(srcVar.isThreadLocal());
	}

	/**
	 * This method unlinks 'this' from the containing module, but does not delete it.
	 */
	public void removeFromParent(){
		// TODO
	}

	/**
	 * This method unlinks 'this' from the containing module, but does not delete it.
	 */
	public void eraseFromParent(){
		// TODO
	}

	/**
	 * Override Constant's implementation of this method so we can replace constant initializers.
	 * @param from
	 * @param to
	 */
	public void replaceUsesOfWithOnConstant(Value from, Value to){

		// If you call this, then you better know this GVar has a constant
		// initializer worth replacing. Enforce that here.
		if(getNumOperands() != 1){
			throw new RuntimeException("Attempt to replace uses of Constants on a GVar with no initializer");
		}

		// And, since you know it has an initializer, the From value better be
		// the initializer :)
		if(getOperand(0) != from){
			throw new RuntimeException("Attempt to replace wrong constant initializer in GVar");
		}

		// And, you better have a constant for the replacement value
		if(!(to instanceof Constant)){
			throw new RuntimeException("Attempt to replace GVar initializer with non-constant");
		}

		// Okay, preconditions out of the way, replace the constant initializer.
		setOperand(0,to);
	}

	public String emit(){
		String name = getName();
		String description = "@" + name + " = ";
		GlobalValue.LinkageTypes linkageType = getLinkage();
		boolean isConstant = isConstant();
		boolean isUnnamedAddr = hasUnnamedAddr();
		Type type = getType();
		Type containedType = null;
		Value initializer = getInitializer();
		int alignment = getAlignment();

		if(linkageType != null)
			description += linkageType.getStrRepresentation() + " ";

		if(isUnnamedAddr){
			description += "unnamed_addr ";
		}

		if(isConstant){
			description += "constant ";
		}
		else{
			description += "global ";
		}

		if(type.isPointerType()){
			PointerType pointerType = (PointerType)type;
			containedType = pointerType.getContainedType();
		}
		else{
			containedType = type;
		}

		if(initializer == null){
			if(containedType.isAggregateType()){
				String typeStr = getType().toString();
				typeStr = typeStr.substring(0, typeStr.length()-1);
				description += typeStr;
				
//				if(containedType.getTypeId() == TypeID.STRUCT_ID){
//					StructType structType = (StructType) containedType;
//					description += structType.bodyDescription();
//				}
			}
			else
				description += containedType.toString();
		}

		if(initializer == null){
			if(linkageType == GlobalValue.LinkageTypes.CommonLinkage){
				if(containedType.isAggregateType()){
					description += " zeroinitializer";
				}
				else if(containedType.isIntegerType())
					description += " 0";
				else if(containedType.isFloatingPointType())
					description += " 0.0";
				else if(containedType.isPointerType())
					description += " null";
			}
		}
		else{
			if(initializer instanceof Instruction)
				description += ((Instruction)initializer).emit();
			else
				description += initializer.toString();
		}

		if(alignment != 0)
			description += ", align " + alignment;

		return description;
	}

	@Override
	public boolean equals(Object obj) {

		if(!(obj instanceof GlobalVariable))
			return false;

		GlobalVariable globalVariable = (GlobalVariable)obj;
		String s1 = toString();
		String s2 = globalVariable.toString();
		if(s1.equals(s2))
			return true;
		else
			return false;
	}

	@Override
	public int hashCode() {
		return toString().hashCode();
	}
}
package org.tamedragon.common.llvmir.types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Vector;

import org.jgraph.graph.DefaultEdge;
import org.tamedragon.common.llvmir.instructions.AllocaInst;
import org.tamedragon.common.llvmir.instructions.Attributes;
import org.tamedragon.common.llvmir.instructions.BranchInst;
import org.tamedragon.common.llvmir.instructions.CFG;
import org.tamedragon.common.llvmir.instructions.CallingConv;
import org.tamedragon.common.llvmir.instructions.Instruction;
import org.tamedragon.common.llvmir.instructions.LLVMUtility;
import org.tamedragon.common.llvmir.instructions.PhiNode;
import org.tamedragon.common.llvmir.instructions.TerminatorInst;
import org.tamedragon.common.llvmir.instructions.Instruction.InstructionID;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionDetailAccessException;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionUpdateException;
import org.tamedragon.common.llvmir.symboltable.ValueSymbolTable;

public class Function extends GlobalValue {

	private CallingConv callingConv;
	private CFG cfg;
	private List<BasicBlock> basicBlocks = new ArrayList<BasicBlock>();        ///< The basic blocks
	private  List<Argument> argumentList = new ArrayList<Argument>();  ///< The formal arguments
	private List<Integer> attributeList = new ArrayList<Integer>(); ///< 0th index is fix for function Attributes , 1st index is fix for return attributes and rest of the indexes are for Parameter attributes
	private ValueSymbolTable symbolTable;

	// Initialize ValueSymbolTable (IIB-1)
	{
		Map<String, Value> vMap = new LinkedHashMap<String, Value>();
		symbolTable = new ValueSymbolTable(vMap);
	}

	/**
	 * hasLazyArguments/CheckLazyArguments - The argument list of a function is
	 * built on demand, so that the list isn't allocated until the first client
	 * needs it.  The hasLazyArguments predicate returns true if the arg list
	 * hasn't been set up yet.
	 * @return
	 */
	private boolean hasLazyArguments() {
		return true;
	}

	/**
	 * If the (optional) Module argument is specified, the function 
	 * is automatically inserted into the end of the function list for the module.
	 * @param parent
	 * @param ptrToFunctype
	 * @param callingConv
	 * @param linkageType
	 * @param name
	 * @param cfg
	 */
	private Function(Module parent, PointerType ptrToFunctype, CallingConv callingConv, 
			LinkageTypes linkageType, String name, CFG cfg){
		super(parent, ptrToFunctype, null, linkageType, name);
		this.callingConv = callingConv;
		this.cfg = cfg;
		setName(name);
		if(parent != null)
			parent.getFunctions().add(this);
	}

	public static Function create(Module parent, PointerType ptrToFunctype, 
			LinkageTypes Linkage,CallingConv callingConv,
			String name, CFG cfg) {
		return new Function(parent, ptrToFunctype, callingConv ,Linkage, name, cfg);
	}

	public Type getReturnType() {
		return getFunctionType().getReturnType();
	}

	public FunctionType getFunctionType()
	{
		return (FunctionType)(getType().getContainedType());
	}

	public CFG getCfg() {
		return cfg;
	}

	public BasicBlock getStartNode(){
		return basicBlocks.get(0);
	}

	public boolean isVarArg() {
		return getFunctionType().isVarArg();
	}

	/**
	 * This method returns the ID number of the specified 
	 * function, or Intrinsic::not_intrinsic if the function is not an
	 * instrinsic, or if the pointer is null.  This value is always defined to be
	 * zero to allow easy checking for whether a function is intrinsic or not.
	 * @return
	 */
	public int getIntrinsicID() {
		String ValName = this.getName();
		if (ValName == null || ValName.length() == 0 || !isIntrinsic())
			return 0;
		int Len = ValName.length();

		if (Len < 5 || ValName.charAt(4) != '.' || ValName.charAt(0) != 'l' || ValName.charAt(1) != 'l'
			|| ValName.charAt(2) != 'v' || ValName.charAt(3) != 'm')
			return 0;  // All intrinsics start with 'llvm.'
		else return 0;
	}

	public boolean isIntrinsic() {
		return getName().contains("llvm."); 
	}

	/**
	 * These method get and set the calling convention of this function.
	 * The enum values for the known calling conventions are defined in CallingConv.java
	 * @return
	 */
	public CallingConv getCallingConv()  {
		return callingConv;
	}

	public void setCallingConv(CallingConv callingConv) {
		this.callingConv = callingConv;
	}

	/**
	 * Return the attribute list for this Function.
	 * @return
	 */
	List<Integer> getAttributeList() {
		return attributeList; 
	}

	public void setAttributeList(List<Integer> attributeList) {
		this.attributeList = attributeList;
	}

	public ValueSymbolTable getSymbolTable() {
		return symbolTable;
	}

	public void setSymbolTable(ValueSymbolTable symbolTable) {
		this.symbolTable = symbolTable;
	}

	/**
	 * Return true if this function has the given attribute.
	 * @param n
	 * @return
	 */
	boolean hasFnAttr(int n) {
		return paramHasAttr(0,n);
	}

	/**
	 * Extract the alignment for a call or parameter (0=unknown).
	 * @param i
	 * @return
	 */
	public int getParamAlignment(int i) {
		// TODO fix this
		return -1;
		//return attributeList.getParamAlignment(i);
	}

	/**
	 * Add function attributes to this function.
	 * @param attr
	 * @throws InstructionUpdateException
	 */
	public void addFnAttr(int attr) throws InstructionUpdateException { 
		// The attribute passed can only be zero or a power of 2:
		if(!(attr == 0 || LLVMUtility.isPowerOf2_32(attr)))
			throw new InstructionUpdateException(InstructionUpdateException.ATTRIBUTE_IS_NOT_A_POWER_OF_TWO);

		// Can have only noreturn', 'nounwind', 'readonly' and 'readnone'
		if(!(attr == Attributes.AlwaysInline || attr == Attributes.NonLazyBind || attr == Attributes.InlineHint
				|| attr == Attributes.Naked || attr == Attributes.NoImplicitFloat || attr == Attributes.NoInline || attr == Attributes.NoRedZone
				|| attr == Attributes.OptimizeForSize || attr == Attributes.ReturnsTwice || attr == Attributes.UWTable
				|| attr == Attributes.NoReturn || attr == Attributes.NoUnwind || attr == Attributes.ReadOnly || attr == Attributes.ReadNone
				|| attr == Attributes.StackProtect || attr == Attributes.StackProtectReq))
			throw new InstructionUpdateException(InstructionUpdateException.INVALID_ATTR_FOR_FUNCTION);

		if(attributeList.size() < 1){
			attributeList.add(null);
		}
		if(attributeList.get(0) != null)
			attributeList.set(0,attributeList.get(0)| attr) ;
		else
			attributeList.set(0, attr);
	}

	/**
	 * Remove function attributes from this function.
	 * @param attr
	 * @throws InstructionUpdateException
	 */
	public void removeFnAttr(int attr) throws InstructionUpdateException {
		// The attribute passed can only be zero or a power of 2:
		if(!(attr == 0 || LLVMUtility.isPowerOf2_32(attr)))
			throw new InstructionUpdateException(InstructionUpdateException.ATTRIBUTE_IS_NOT_A_POWER_OF_TWO);

		// Can remove only 'noreturn', 'nounwind', 'readonly' and 'readnone'
		if(!(attr == Attributes.AlwaysInline || attr == Attributes.NonLazyBind || attr == Attributes.InlineHint
				|| attr == Attributes.Naked || attr == Attributes.NoImplicitFloat || attr == Attributes.NoInline || attr == Attributes.NoRedZone
				|| attr == Attributes.OptimizeForSize || attr == Attributes.ReturnsTwice || attr == Attributes.UWTable
				|| attr == Attributes.NoReturn || attr == Attributes.NoUnwind || attr == Attributes.ReadOnly || attr == Attributes.ReadNone
				|| attr == Attributes.StackProtect || attr == Attributes.StackProtectReq))
			throw new InstructionUpdateException(InstructionUpdateException.INVALID_ATTR_FOR_FUNCTION);

		if(attributeList.size() > 0 && attributeList.get(0) != null)
			attributeList.set(0, attributeList.get(0) & ~attr) ;
	}

	public void addReturnAttributes(int retAttrs) throws InstructionUpdateException {
		//check if the function has a return type or not
		FunctionType functionType = getFunctionType();
		Type retType = functionType.getReturnType();
		if(retType.isVoidType())
			throw new InstructionUpdateException(InstructionUpdateException.CANNOT_SET_A_RET_ATTR_TO_FUNC_RET_VOID);

		// The attribute passed can only be zero or a power of 2:
		if(!(retAttrs == 0 || LLVMUtility.isPowerOf2_32(retAttrs)))
			throw new InstructionUpdateException(InstructionUpdateException.ATTRIBUTE_IS_NOT_A_POWER_OF_TWO);

		if(!(retAttrs == Attributes.ZExt || retAttrs == Attributes.SExt
				|| retAttrs == Attributes.InReg || retAttrs == Attributes.NoAlias))
			throw new InstructionUpdateException(InstructionUpdateException.INVALID_ATTR_FOR_FUNCTION_RET_TYPE);

		if(attributeList.size() < 2){
			fillNullValues(2);
		}
		if(attributeList.get(1) != null)
			attributeList.set(1,attributeList.get(1)| retAttrs) ;
		else
			attributeList.set(1, retAttrs);
	}

	private void fillNullValues(int i) {
		while(attributeList.size() < i){
			attributeList.add(null);
		}
	}

	public void removeRet_attrs(int retAttrs) throws InstructionUpdateException {
		//check if the function has a return type or not
		FunctionType functionType = getFunctionType();
		Type retType = functionType.getReturnType();
		if(retType.isVoidType())
			throw new InstructionUpdateException(InstructionUpdateException.CANNOT_SET_A_RET_ATTR_TO_FUNC_RET_VOID);

		// The attribute passed can only be zero or a power of 2:
		if(!(retAttrs == 0 || LLVMUtility.isPowerOf2_32(retAttrs)))
			throw new InstructionUpdateException(InstructionUpdateException.ATTRIBUTE_IS_NOT_A_POWER_OF_TWO);

		if(!(retAttrs == Attributes.ZExt || retAttrs == Attributes.SExt
				|| retAttrs == Attributes.InReg))
			throw new InstructionUpdateException(InstructionUpdateException.INVALID_ATTR_FOR_FUNCTION_RET_TYPE);

		if(attributeList.size() > 1 && attributeList.get(1) != null)
			attributeList.set(1, attributeList.get(1) & ~retAttrs) ;
	}

	public boolean hasGC(){
		return false;
	}

	public String getGC(){
		return null;
	}

	public void setGC(String Str){}

	public void clearGC(){}

	/**
	 * Determine whether the function has the given attribute.
	 * @param index
	 * @param attr
	 * @return
	 */
	boolean paramHasAttr(int index, int  attr) {
		// attr should be a power to 2
		boolean flag = LLVMUtility.checkIfNumberIsPowerOfTwo(attr);
		if(!flag)
			return false;

		if(index >= attributeList.size()){
			return false;
		}

		int attributes = attributeList.get(index);
		List<Integer> listOfAttributes = Attributes.getAttributesAsList(attributes);

		if(listOfAttributes.contains(attr))
			return true;
		else
			return false;
	}

	/**
	 * adds the attribute to the list of attributes.
	 * @param index
	 * @param attr
	 * @throws InstructionUpdateException
	 */
	public void addAttribute(int index, int  attr) throws InstructionUpdateException{
		// attr should be a power to 2
		boolean flag = LLVMUtility.checkIfNumberIsPowerOfTwo(attr);
		if(!flag)
			throw new InstructionUpdateException(InstructionUpdateException.ATTRIBUTE_IS_NOT_A_POWER_OF_TWO);

		if(index == 0)
			addFnAttr(attr);
		else if(index == 1)
			addReturnAttributes(attr);
		else{
			if(attributeList.size() < (index+1)){
				fillNullValues(index + 1);
			}
			if(attributeList.get(index) != null)
				attributeList.set(index,attributeList.get(index)| attr);
			else
				attributeList.set(index, attr);
		}
	}

	/**
	 * removes the attribute from the list of attributes.
	 * @param index
	 * @param attr
	 * @throws InstructionUpdateException
	 */
	void removeAttribute(int index, int attr) throws InstructionUpdateException{
		if(index == 0)
			removeFnAttr(attr);
		else if(index == 1)
			removeRet_attrs(attr);
		else{
			if(attributeList != null && attributeList.size() > index)
				attributeList.set(index, attributeList.get(index) & ~attr);
		}
	}

	/// @brief Extract the alignment for a call or parameter (0=unknown).
	//	int getParamAlignment(int i) throws InstructionDetailAccessException {
	//		boolean hasAttribute = paramHasAttr(i);
	//		if(!hasAttribute)
	//			throw new InstructionDetailAccessException(InstructionDetailAccessException.PARAM_DOESNT_HAS_THIS_ATTR);
	//		return i;
	//	}

	public boolean doesNotAccessMemory() {
		return hasFnAttr(Attributes.ReadNone);
	}

	public void setDoesNotAccessMemory(boolean DoesNotAccessMemory) throws InstructionUpdateException {
		if (DoesNotAccessMemory) addFnAttr(Attributes.ReadNone);
		else removeFnAttr(Attributes.ReadNone);
	}

	public boolean onlyReadsMemory() {
		return doesNotAccessMemory() || hasFnAttr(Attributes.ReadOnly);
	}

	void setOnlyReadsMemory(boolean OnlyReadsMemory) throws InstructionUpdateException {
		if (OnlyReadsMemory) addFnAttr(Attributes.ReadOnly);
		else removeFnAttr(Attributes.ReadOnly | Attributes.ReadNone);
	}

	public boolean doesNotReturn()  {
		return hasFnAttr(Attributes.NoReturn);
	}

	void setDoesNotReturn(boolean DoesNotReturn) throws InstructionUpdateException {
		if (DoesNotReturn) addFnAttr(Attributes.NoReturn);
		else removeFnAttr(Attributes.NoReturn);
	}

	public boolean doesNotThrow() {
		return hasFnAttr(Attributes.NoUnwind);
	}

	void setDoesNotThrow(boolean DoesNotThrow) throws InstructionUpdateException {
		if (DoesNotThrow) addFnAttr(Attributes.NoUnwind);
		else removeFnAttr(Attributes.NoUnwind);
	}

	/**
	 * True if the ABI mandates (or the user requested) that this function be in a unwind table.
	 * @return
	 */
	public boolean hasUWTable() {
		return hasFnAttr(Attributes.UWTable);
	}

	void setHasUWTable(boolean HasUWTable) throws InstructionUpdateException {
		if (HasUWTable)
			addFnAttr(Attributes.UWTable);
		else
			removeFnAttr(Attributes.UWTable);
	}

	/**
	 * True if this function needs an unwind table.
	 * @return
	 */
	public boolean needsUnwindTableEntry() {
		return hasUWTable() || !doesNotThrow();
	}

	/**
	 * Determine if the function returns a structure through first pointer argument.
	 * @return
	 */
	public boolean hasStructRetAttr() {
		return paramHasAttr(1,Attributes.StructRet);
	}

	/**
	 * Determine if the parameter does not alias other parameters.
	 * @param index
	 * @return
	 */
	public boolean doesNotAlias(int index) {
		return paramHasAttr(index, Attributes.NoAlias);
	}

	void setDoesNotAlias(int index, boolean DoesNotAlias) throws InstructionUpdateException {
		if (DoesNotAlias) addAttribute(index, Attributes.NoAlias);
		else removeAttribute(0, Attributes.NoAlias);
	}

	/**
	 * Determine if the parameter can be captured.
	 * @param index
	 * @return
	 */
	public boolean doesNotCapture(int index)  {
		return paramHasAttr(index, Attributes.NoCapture);
	}

	void setDoesNotCapture(int index, boolean DoesNotCapture) throws InstructionUpdateException {
		if (DoesNotCapture) addAttribute(index, Attributes.NoCapture);
		else removeAttribute(0, Attributes.NoCapture);
	}

	/**
	 * This method removes BasicBlock from the list of BasicBlocks of this function
	 * , removes it from CFG i.e removes all edges and node from CFG and at last it 
	 * removes all the instructions of this BasicBlock from other value's userlist, which 
	 * has instructions from this basic block as one of its user.
	 * @param basicBlock to be removed
	 * @throws InstructionDetailAccessException 
	 * @throws InstructionUpdateException 
	 */
	public void removeBasicBlockFromFunction(BasicBlock basicBlock) throws InstructionUpdateException, InstructionDetailAccessException{
		getCfg().removeVertex(basicBlock);
		basicBlocks.remove(basicBlock);
		Iterator<Instruction> instructions = basicBlock.getInstructions();
		while(instructions.hasNext()){
			Instruction instruction = instructions.next();
			instruction.emptyUserList();
		}
	}

	/// copyAttributesFrom - copy all additional attributes (those not needed to
	/// create a Function) from the Function Src to this one.
	//void copyAttributesFrom(GlobalValue Src);

	/// deleteBody - This method deletes the body of the function, and converts
	/// the linkage to external.
	///
	//		  void deleteBody() {
	//		    dropAllReferences();
	//		    setLinkage(ExternalLinkage);
	//		  }

	/// removeFromParent - This method unlinks 'this' from the containing module,
	/// but does not delete it.
	///
	// void removeFromParent();

	/// eraseFromParent - This method unlinks 'this' from the containing module
	/// and deletes it.
	///
	// void eraseFromParent();

	/// Get the underlying elements of the Function... the basic block list is
	/// empty for external functions.
	///
	public List<Argument> getArgumentList() {
		//CheckLazyArguments();
		return argumentList;
	}

	public void setBasicBlocks(List<BasicBlock> basicBlocks) {
		this.basicBlocks = basicBlocks;
	}

	List<Argument> getSublistAccess(Argument arg) {
		return argumentList;
	}
	List<BasicBlock> getSublistAccess(BasicBlock blk) {
		return basicBlocks;
	}




	// BasicBlock  getEntryBlock() { return front(); }

	//===--------------------------------------------------------------------===//
	// Symbol Table Accessing functions...

	/// getSymbolTable() - Return the symbol table...
	///
	//		        ValueSymbolTable getValueSymbolTable()       { return SymTab; }
	//		   ValueSymbolTable getValueSymbolTable() { return SymTab; }
	//
	//
	//		  //===--------------------------------------------------------------------===//
	//		  // BasicBlock iterator forwarding functions
	//		  //
	//		  iterator                begin()       { return BasicBlocks.begin(); }
	//		  const_iterator          begin() const { return BasicBlocks.begin(); }
	//		  iterator                end  ()       { return BasicBlocks.end();   }
	//		  const_iterator          end  () const { return BasicBlocks.end();   }
	//
	//		  size_t                   size() const { return BasicBlocks.size();  }
	//		  boolean                    empty() const { return BasicBlocks.empty(); }
	//		  const BasicBlock       &front() const { return BasicBlocks.front(); }
	//		        BasicBlock       &front()       { return BasicBlocks.front(); }
	//		  const BasicBlock        &back() const { return BasicBlocks.back();  }
	//		        BasicBlock        &back()       { return BasicBlocks.back();  }
	//
	//		  //===--------------------------------------------------------------------===//
	//		  // Argument iterator forwarding functions
	//		  //
	//		  arg_iterator arg_begin() {
	//		    CheckLazyArguments();
	//		    return ArgumentList.begin();
	//		  }
	//		  const_arg_iterator arg_begin() const {
	//		    CheckLazyArguments();
	//		    return ArgumentList.begin();
	//		  }
	//		  arg_iterator arg_end() {
	//		    CheckLazyArguments();
	//		    return ArgumentList.end();
	//		  }
	//		  const_arg_iterator arg_end() const {
	//		    CheckLazyArguments();
	//		    return ArgumentList.end();
	//		  }
	//
	//		  size_t arg_size() const;
	//		  boolean arg_empty() const;
	//
	//		  /// viewCFG - This function is meant for use from the debugger.  You can just
	//		  /// say 'call F->viewCFG()' and a ghostview window should pop up from the
	//		  /// program, displaying the CFG of the current function with the code for each
	//		  /// basic block inside.  This depends on there being a 'dot' and 'gv' program
	//		  /// in your path.
	//		  ///
	//		  void viewCFG() const;
	//
	//		  /// viewCFGOnly - This function is meant for use from the debugger.  It works
	//		  /// just like viewCFG, but it does not include the contents of basic blocks
	//		  /// into the nodes, just the label.  If you are only interested in the CFG
	//		  /// this can make the graph smaller.
	//		  ///
	//		  void viewCFGOnly() const;
	//
	//		  /// Methods for support type inquiry through isa, cast, and dyn_cast:
	//		  static inline boolean classof(const Function *) { return true; }
	//		  static inline boolean classof(const Value *V) {
	//		    return V->getValueID() == Value::FunctionVal;
	//		  }
	//
	//		  /// dropAllReferences() - This method causes all the subinstructions to "let
	//		  /// go" of all references that they are maintaining.  This allows one to
	//		  /// 'delete' a whole module at a time, even though there may be circular
	//		  /// references... first all references are dropped, and all use counts go to
	//		  /// zero.  Then everything is deleted for real.  Note that no operations are
	//		  /// valid on an object that has "dropped all references", except operator
	//		  /// delete.
	//		  ///
	//		  /// Since no other object in the module can have references into the body of a
	//		  /// function, dropping all references deletes the entire body of the function,
	//		  /// including any contained basic blocks.
	//		  ///
	//		  void dropAllReferences();
	//
	//		  /// hasAddressTaken - returns true if there are any uses of this function
	//		  /// other than direct calls or invokes to it. Optionally passes back the
	//		  /// offending user for diagnostic purposes.
	//		  ///
	//		  boolean hasAddressTaken(const User** = 0) const;
	//
	//		  /// callsFunctionThatReturnsTwice - Return true if the function has a call to
	//		  /// setjmp or other function that gcc recognizes as "returning twice".
	//		  boolean callsFunctionThatReturnsTwice() const;
	//
	//		private:
	//		  // Shadow Value::setValueSubclassData with a private forwarding method so that
	//		  // subclasses cannot accidentally use it.
	//		  void setValueSubclassData(unsigned short D) {
	//		    Value::setValueSubclassData(D);
	//		  }
	//		  
	//			inline ValueSymbolTable *
	//			ilist_traits<BasicBlock>::getSymTab(Function *F) {
	//			  return F ? &F->getValueSymbolTable() : 0;
	//			}
	//
	//			inline ValueSymbolTable *
	//			ilist_traits<Argument>::getSymTab(Function *F) {
	//			  return F ? &F->getValueSymbolTable() : 0;
	//
	//
	//	}

	public void setCfg(CFG cfg) {
		this.cfg = cfg;
	}

	public String toString(){
		String description = "";

		LinkageTypes linkageTypes = getLinkage();

		if(linkageTypes != null && linkageTypes != LinkageTypes.ExternalLinkage){
			description += linkageTypes.getStrRepresentation() + " ";
		}

		String returnAttributesAsString = null;

		if(attributeList.size() > 1 && attributeList.get(1) != null)
			returnAttributesAsString = LLVMUtility.getAttributeAsString(attributeList.get(1));

		description += (returnAttributesAsString != null && returnAttributesAsString.length() != 0) ? returnAttributesAsString + " " + getReturnType().toString() + " "  : getReturnType().toString() + " ";
		String funcName = getName();
		description += "@" + funcName + "(";
		List<Argument> argumentlist = getArgumentList();

		for(int i = 0; i < argumentlist.size(); i++){
			Argument argument = argumentlist.get(i);
			description += (i < (argumentlist.size() - 1)) ? argument.toString() + ", " : argument.toString();
		}

		boolean hasEllipses = getFunctionType().isVarArg();
		if(hasEllipses)
			description += ", ...) ";
		else
			description += ") ";

		if(attributeList.size() > 0 && attributeList.get(0) != null){
			Integer funcAttributeList = attributeList.get(0);
			String functionAttributeAsString = LLVMUtility.getAttributeAsString(funcAttributeList);
			functionAttributeAsString = functionAttributeAsString.replaceAll(",", " ");
			description += functionAttributeAsString;
		}

		return description;
	}

	/**
	 * Inserts a NEW basic block and makes it the start block of the control flow graph:
	 * 1. Add the new basic block at the beginning of the basic block list
	 * 2. A new edge is added between the new and old first basic blocks
	 * 3. If there are any fixed size allocas in the existing entry block, they are moved to the new first basic block
	 * 
	 */
	public boolean insertNewStartBasicBlock(BasicBlock newBasicBlock){
		
		BasicBlock oldFirstBlock = basicBlocks.get(0);
		
		// Ensure that this is indeed a new block
		if(basicBlocks.contains(newBasicBlock)){
			// Log warning here
			return false;
		}
		
		basicBlocks.add(0, newBasicBlock);
		cfg.addVertex(newBasicBlock);
		
		if(!cfg.addEdge(newBasicBlock, oldFirstBlock, new DefaultEdge())){
			return false;
		}
		
		// Create an unconditional branch from the source to the new basic block
		BranchInst newBranchInst = BranchInst.create(oldFirstBlock, newBasicBlock, getContext());
		newBasicBlock.addInstruction(newBranchInst);
		
		// If there are any fixed-size allocas in the earlier first basic blocks, move them to the new one.
		Iterator<Instruction> instructions = oldFirstBlock.getInstructions();
		while(instructions.hasNext()){
			Instruction instruction = instructions.next();
			if(instruction.getInstructionID() == InstructionID.ALLOCA_INST){
				AllocaInst allocaInst = (AllocaInst) instruction;
				if(allocaInst.getArraySize() instanceof ConstantInt){
					allocaInst.moveInstructionBefore(newBasicBlock.getFirstInstruction());
				}
			}
		}
		return true;
	}
	
	/**
	 * Replaces the target basic block (in the second argument) of the given basic block (in the first argument) 
	 * with the target basic block in the target argument. If the existing target basic block is not a successor
	 * of the source basic block
	 * @param oldFirstBasicBlock
	 * @throws InstructionDetailAccessException 
	 * @throws InstructionUpdateException 
	 */
	public boolean replaceTargetOfBlockWith( BasicBlock sourceBasicBlock, BasicBlock oldTarget, BasicBlock newTarget) 
			throws InstructionUpdateException, InstructionDetailAccessException {
		List<BasicBlock> successors = cfg.getSuccessors(sourceBasicBlock);
		if(successors == null || successors.size() == 0){
			// TODO Log warning here: this block has no successors?
			return false;
		}
		
		DefaultEdge edge = cfg.getEdge(sourceBasicBlock, oldTarget);
		if(edge == null){
			// TODO Log warning here; no edge from 
		}
		
		cfg.removeEdge(edge);
		cfg.addEdge(sourceBasicBlock, newTarget, new DefaultEdge());
		
		// Update the terminator instruction in the source basic block
		TerminatorInst terminatorInst = sourceBasicBlock.getLastInstruction();
		terminatorInst.replaceSuccessorWith(oldTarget, newTarget);
		
		// If there are any phi nodes in the target, inform them of the removal of this incoming value
		Iterator<Instruction> instructionsInOldTarget = oldTarget.getInstructions();
		while(instructionsInOldTarget.hasNext()){
			Instruction instruction = instructionsInOldTarget.next();
			if(instruction.getInstructionID() != InstructionID.PHI_NODE_INST){
				break;
			}
			
			PhiNode phiNode = (PhiNode) instruction;
			phiNode.removeIncomingValue(sourceBasicBlock);
			
		}
		
		return true;
	}
	
	/**
	 * Inserts a NEW node between the given source and destination basic blocks. It is assumed
	 * that the source and destination basic blocks are originally connected by an edge. 
	 * 
	 *  1. Adds the new node right after the source in the basic block list of the function.
	 *  2. Adds the new node into the control flow graph
	 *  3. Inserts the new node between by adjusting the edges appropriately.
	 *
	 */
	public boolean  insertNewNodeBetween(BasicBlock source, List<BasicBlock> destinations,
			BasicBlock newBasicBlock) {

		// Ensure that this is indeed a new block
		if(basicBlocks.contains(newBasicBlock)){
			// Log warning here
			return false;
		}

		int indx = basicBlocks.indexOf(source);
		if(indx < 0){
			// Log warning here
			return false;
		}

		basicBlocks.add(indx+1, newBasicBlock);
		cfg.addVertex(newBasicBlock);

		for(BasicBlock destination : destinations){
			DefaultEdge edgeToBeRemoved = cfg.getEdge(source, destination);
			if(!cfg.removeEdge(edgeToBeRemoved)){
				return false;
			}
			if(!cfg.addEdge(newBasicBlock, destination, new DefaultEdge())){
				return false;
			}
		}

		if(!cfg.addEdge(source, newBasicBlock, new DefaultEdge())){
			return false;
		}

		// After the structural changes, update instructions and data flow.
		// For the terminator instruction in the source, update the corresponding
		// destination to the new basic block
		TerminatorInst ti = source.getLastInstruction();
		ti.setParent(newBasicBlock);
		source.removeInstruction(ti);

		// Create an unconditional branch from the source to the new basic block
		BranchInst newBranchInst = BranchInst.create(newBasicBlock, source, getContext());
		source.addInstruction(newBranchInst);

		return true;
	}

	/**
	 * Inserts a NEW node between the given sources and a destination basic blocks. It is assumed
	 * that all the sources in the list and the destination basic block are originally connected by an edge. 
	 * 
	 *  1. Adds the new node right before the destination in the basic block list of the function.
	 *  2. Adds the new node into the control flow graph
	 *  3. Inserts the new node between by adjusting the edges appropriately.
	 * @throws InstructionDetailAccessException 
	 * @throws InstructionUpdateException 
	 *
	 */
	public boolean insertNewNodeBetween(List<BasicBlock> sources, BasicBlock destination,
			BasicBlock newBasicBlock) throws InstructionUpdateException, InstructionDetailAccessException {

		// Ensure that this is indeed a new block
		if(basicBlocks.contains(newBasicBlock)){
			// TODO Log warning here
			return false;
		}

		int indx = basicBlocks.indexOf(destination);
		if(indx < 1){
			// TODO Log warning here
			return false;
		}

		basicBlocks.add(indx, newBasicBlock);
		cfg.addVertex(newBasicBlock);

		for(BasicBlock source : sources){
			DefaultEdge edgeToBeRemoved = cfg.getEdge(source, destination);
			if(edgeToBeRemoved == null){
				// TODO Log warning here
				return false;
			}
			if(!cfg.removeEdge(edgeToBeRemoved)){
				return false;
			}

			if(!cfg.addEdge(source, newBasicBlock, new DefaultEdge())){
				return false;
			}
		}


		if(!cfg.addEdge(newBasicBlock, destination, new DefaultEdge())){
			return false;
		}

		// After the structural changes, update instructions and data flow.
		// For the terminator instruction in each source, update the corresponding
		// destination to the new basic block
		for(BasicBlock oldPreHeader : sources){
			TerminatorInst terminatorInst = oldPreHeader.getLastInstruction();
			terminatorInst.replaceSuccessorWith(destination, newBasicBlock);
		}

		// Create an unconditional branch from the old destination to the new basic block
		BranchInst branchInst = BranchInst.create(destination, newBasicBlock, getContext());
		newBasicBlock.addInstruction(branchInst);
		
		// If there are any phi instructions in the destination block, move it the new basic block
//		Vector<Instruction> instructionsInDestination = destination.getInstructions();
//		List<Instruction> phiNodes = new ArrayList<Instruction>();
//		while(true){
//			Instruction instruction = instructionsInDestination.get(0);
//			if(instruction.getInstructionID() != InstructionID.PHI_NODE_INST){
//				break;
//			}
//			
//			destination.removeInstruction(instruction);
//			newBasicBlock.addInstruction(instruction);
//		}

	
		return true;
	}

	public boolean removeDeadBasicBlock(BasicBlock basicBlock) 
				throws InstructionDetailAccessException, InstructionUpdateException {
		
		// For each instruction in the basic block, remove the uses if any
		Iterator<Instruction> instructions = basicBlock.getInstructions();
		while(instructions.hasNext()){
			Instruction instruction = instructions.next();
			if(instruction.getNumUses() > 0){
				throw new InstructionUpdateException(InstructionUpdateException.CANNOT_REMOVE_INSTRUCTION_FROM_LIVE_BLOCK);
			}
			instruction.removeUsersOfInstr();
		}
		basicBlock.removeAllInstructions();
		
		if(!removeBasicBlock(basicBlock)){
			return false;
		}
		
		return true;
	}

	/**
	 * Removes the basic block from the control flow graph, this function and updates the terminator instruction of the
	 * predecessors and any phi node entries in the successors.
	 * The instructions in this basic block are left untouched; the client can decide what needs to be done
	 * with them.
	 
	 * @throws InstructionDetailAccessException
	 * @throws InstructionUpdateException
	 */
	public boolean removeBasicBlock(BasicBlock basicBlock) 
				throws InstructionDetailAccessException, InstructionUpdateException{
		if(!cfg.containsVertex(basicBlock)){
			// TODO Log warning here 
			return false;
		}
		
		// Update any phi nodes in the successors that this basic block is going away.
		removeEntryForPhiNodeInSuccessorsOf(basicBlock);
		
		// For each predecessor, update the terminate instruction to notify that this
		// basic block is going away; also update the control flow graph
		List<BasicBlock> predecessors = cfg.getPredecessors(basicBlock);
		for(BasicBlock predecessor : predecessors){
			TerminatorInst terminatorInst = predecessor.getLastInstruction();
			if(!terminatorInst.removeSuccessor(basicBlock)){
				return false;
			}
			
			DefaultEdge edgeToBeRemoved = cfg.getEdge(predecessor, basicBlock);
			if(!cfg.removeEdge(edgeToBeRemoved)){
				return false;
			}
		}
		
		// For each successor, update the control flow graph
		List<BasicBlock> successors = cfg.getSuccessors(basicBlock);
		for(BasicBlock successor : successors){
			DefaultEdge edgeToBeRemoved = cfg.getEdge(basicBlock, successor);
			if(!cfg.removeEdge(edgeToBeRemoved)){
				return false;
			}
		}

		// Remove the basic block from the control flow graph
		if(!cfg.removeVertex(basicBlock)){
			// TODO Log warning here 
			return false;
		}
		
		// Remove the basic block from this function
		if(!basicBlocks.remove(basicBlock)){
			// TODO Log warning here
			return false;
		}
		
		return true;
	}

	private void removeEntryForPhiNodeInSuccessorsOf(BasicBlock basicBlock) 
				throws InstructionUpdateException {

		List<BasicBlock> successors = cfg.getSuccessors(basicBlock);
		
		for(BasicBlock successor : successors){
			Iterator<Instruction> instructions = successor.getInstructions();

			while(instructions.hasNext()){
				Instruction instruction = instructions.next();
				if(instruction.getInstructionID() != InstructionID.PHI_NODE_INST){
					break;
				}

				PhiNode phiNode = (PhiNode) instruction;
				phiNode.removeIncomingValue(basicBlock);
			}
		}
	}

	public boolean removeBasicBlockOnlyWithAnUnconditionalJump(BasicBlock basicBlock) 
					throws InstructionUpdateException, InstructionDetailAccessException {

		if(cfg.getSuccessors(basicBlock).size() == 0){
			// Leaf block in CFG
			return false;
		}

		if(basicBlock.numInstructions() != 1){
			// TODO Log warning here
			return false;
		}

		Instruction terminatorInstr = basicBlock.getLastInstruction();
		BranchInst branchInst = null;
		if(terminatorInstr.getInstructionID() == InstructionID.BRANCH_INST){
			branchInst = (BranchInst) terminatorInstr;
			if(branchInst.isConditional()){
				return false;
			}
		}

		// Structural changes first
		BasicBlock targetBasicBlock = cfg.getSuccessors(basicBlock).get(0);
		List<BasicBlock> predecessors = cfg.getPredecessors(basicBlock);

		for(BasicBlock predecessor : predecessors){
			DefaultEdge edgeToBeRemoved = cfg.getEdge(predecessor, basicBlock);
			if(edgeToBeRemoved == null){
				// TODO Log warning here
				return false;
			}
			if(!cfg.removeEdge(edgeToBeRemoved)){
				return false;
			}

			if(!cfg.addEdge(predecessor, targetBasicBlock, new DefaultEdge())){
				return false;
			}
		}

		// Adjust the instructions in the predecessors terminator instructions
		for(BasicBlock predecessor : predecessors){
			TerminatorInst terminatorInst = predecessor.getLastInstruction();
			terminatorInst.replaceSuccessorWith(basicBlock, targetBasicBlock);
		}

		// Update phi nodes if any in the target block
		Iterator<Instruction> instructions = targetBasicBlock.getInstructions();
		while(instructions.hasNext()){
			Instruction instruction = instructions.next(); 
			if(instruction.getInstructionID() != InstructionID.PHI_NODE_INST){
				continue;
			}

			PhiNode phiNode = (PhiNode) instruction;
			int index = phiNode.getBasicBlockIndex(basicBlock);
			Value value = phiNode.getIncomingValue(index);

			BasicBlock newBB = null;
			if(predecessors.size() == 1){
				newBB = predecessors.get(0);
			}

			phiNode.setIncomingValueAndBasicBlock(index, value, newBB);
		}

		basicBlocks.remove(basicBlock);
		cfg.removeVertex(basicBlock);

		return true;
	}

	public Iterator<BasicBlock> basicBlocksIterator(){
		return basicBlocks.iterator();
	}

	public ListIterator<BasicBlock> reverseBasicBlocksIterator(){
		return basicBlocks.listIterator(basicBlocks.size());
	}

	public Iterator<BasicBlock> modifiableBasicBlocksIterator(){
		List<BasicBlock> tempList = new ArrayList<BasicBlock>(basicBlocks);
		return tempList.iterator();
	}


	public int getNumBasicBlocks(){
		return basicBlocks.size();
	}

	public BasicBlock getBasicBlockAt(int index){
		return basicBlocks.get(index);
	}

	public int getIndexOf(BasicBlock basicBlock){
		return basicBlocks.indexOf(basicBlock);
	}

	/**
	 * Returns a map containing a numbering for each basic block
	 */

	public Map<BasicBlock, Integer> getBBNumbering(){

		Map<BasicBlock, Integer> bbNumbering = new HashMap<BasicBlock, Integer>();
		int numNodes = basicBlocks.size();
		for(int i = 0; i < numNodes; i++){
			BasicBlock nd = (BasicBlock) basicBlocks.get(i);
			bbNumbering.put(nd, new Integer(i));
		}
		return bbNumbering;
	}
}



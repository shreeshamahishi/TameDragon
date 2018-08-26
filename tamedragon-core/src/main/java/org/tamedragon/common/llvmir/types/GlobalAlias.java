package org.tamedragon.common.llvmir.types;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.tamedragon.common.llvmir.instructions.Instruction;
import org.tamedragon.common.llvmir.instructions.Instruction.InstructionID;

public class GlobalAlias extends GlobalValue{

	public void setParent(Module parent){
		this.parent = parent;
	}

	/**
	 * If a parent module is specified, the alias is automatically inserted
	 * into the end of the specified module's alias list.
	 * @param Ty
	 * @param Linkage
	 * @param Name
	 * @param operandList
	 * @param Parent
	 */
	protected GlobalAlias(Type Ty, LinkageTypes Linkage, String Name,
			List<Constant> operandList, Module Parent){
		super(Ty, operandList, Linkage, Name);

		Constant aliasee = operandList.get(0);

		if (Parent != null){
			Parent.getGlobalAliases().add(this);
		}
	}

	public static GlobalAlias create(Type Ty, LinkageTypes Linkage, String Name, Constant Aliasee,
			Module Parent) throws Exception {
		if(Aliasee == null){
			throw new Exception("A GlobalAlias cannot be created without an aliasee specified.");
		}

		if(Aliasee.getType() != Ty){
			throw new Exception("Alias and aliasee types are not the same. They should be similar.");
		}

		if(Name == null){
			Name = "";
		}

		List<Constant> operandList = new ArrayList<Constant>();
		operandList.add(Aliasee);

		return new GlobalAlias(Ty, Linkage, Name, operandList, Parent);
	}

	/**
	 * This method unlinks 'this' from the containing module and deletes it.
	 */
	public void eraseFromParent(){
		getParent().getGlobalAliases().remove(this);
	}

	public void setAliasee(Constant Aliasee) throws Exception {
		if(Aliasee == null){
			throw new Exception("Aliasee cannot be null");
		}
		if(Aliasee.getType() != getType()){
			throw new Exception("Alias and aliasee types should match!");
		}

		setOperand(0, Aliasee);
	}

	public Constant getAliasee() {
		return (Constant) getOperand(0);
	}

	/**
	 * Aliasee can be either global or bitcast of global. This method retrives the global for both aliasee flavours.
	 * @return
	 * @throws Exception
	 */
	public GlobalValue getAliasedGlobal() throws Exception {
		Value C = getAliasee();
		if (C == null) return null;

		if(C instanceof GlobalValue){
			return (GlobalValue) C;
		}

		ValueTypeID valueTypeID = C.getValueTypeID();
		if(valueTypeID != ValueTypeID.INSTRUCTION){
			throw new Exception("Unsupported aliasee");
		}
		Instruction instr = (Instruction) C;
		if(instr.getInstructionID() != InstructionID.BIT_CAST_INST &&
				instr.getInstructionID() != InstructionID.GET_ELEMENT_PTR){
			throw new Exception("Unsupported aliasee");
		}


		return (GlobalValue)(instr.getOperand(0));
	}

	/**
	 * This method tries to ultimately resolve the alias by going through the
	 * aliasing chain and trying to find the very last global. Returns NULL 
	 * if a cycle was found. If stopOnWeak is false, then the whole chain
	 * aliasing chain is traversed, otherwise - only strong aliases.
	 * @param stopOnWeak
	 * @return
	 * @throws Exception
	 */
	public GlobalValue resolveAliasedGlobal(boolean stopOnWeak) throws Exception {
		Set<GlobalValue> visited = new HashSet<GlobalValue>();

		// Check if we need to stop early.
		if (stopOnWeak && mayBeOverridden())
			return this;

		GlobalValue gv = getAliasedGlobal();
		visited.add(gv);

		// Iterate over aliasing chain, stopping on weak alias if necessary.
		GlobalAlias ga = null;
		do{
			ga = (GlobalAlias) gv;
			if (stopOnWeak && ga.mayBeOverridden())
				break;

			gv = ga.getAliasedGlobal();

			if (!visited.add(gv))
				return null;

		}while(ga != null);

		return gv;
	}
}


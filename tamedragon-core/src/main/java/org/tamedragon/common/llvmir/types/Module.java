package org.tamedragon.common.llvmir.types;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.tamedragon.common.llvmir.symboltable.ValueSymbolTable;
import org.tamedragon.common.llvmir.types.GlobalValue.LinkageTypes;
import org.tamedragon.target.TargetData;

/**
 * LLVM programs are composed of Modules, each of which is a translation unit of the input programs. 
 * Each module consists of functions, global variables, and symbol table entries. Modules may be combined 
 * together with the LLVM linker, which merges function (and global variable) definitions, resolves forward 
 * declarations, and merges symbol table entries.
 * 
 * A module contains things like global variables, function 
 * declarations, and implementations.It is the main container class for the LLVM Intermediate 
 * Representation. Modules are the top level container of all other LLVM Intermediate Representation (IR) objects.
 * Each module directly contains a list of globals variables, a list of functions, a list of libraries (or other 
 * modules) this module depends on, a symbol table, and various data about the target's characteristics.
 */

public class Module {
	private List<GlobalVariable> globalVariables;
	private List<GlobalAlias> globalAliases;
	private List<Function> functions;
	private String name;
	private ValueSymbolTable symbolTable;
	private List<NamedType> namedTypes;
	private TargetData targetData;

	// Initialize ValueSymbolTable (IIB-1)
	{
		Map<String, Value> vMap = new LinkedHashMap<String, Value>();
		symbolTable = new ValueSymbolTable(vMap);
		globalVariables = new ArrayList<GlobalVariable>();
		globalAliases = new ArrayList<GlobalAlias>();
		functions = new ArrayList<Function>();
	}

	public Module(String name, CompilationContext compilationContext, List<NamedType> namedTypes){
		this.name = name;
		this.namedTypes = namedTypes;
		compilationContext.addModule(this);
	}

	public GlobalVariable getGlobalVariable(String name){
		Value value = symbolTable.lookUp(name);
		return (GlobalVariable)value;
	}

	/**
	 * getOrInsertGlobal - Look up the specified global in the module symbol table.<br>
	 * 	1. If it does not exist, add a declaration of the global and return it.<br>
	 * 	2. Else, the global exists but has the wrong type: return the function
	 * 	   with a constantexpr cast to the right type.<br>
	 * 	3. Finally, if the existing global is the correct delclaration, return the
	 *     existing global.
	 */
	public GlobalVariable getOrInsertGlobalvariable(String name, PointerType type){
		GlobalVariable globalVariable = (GlobalVariable)symbolTable.lookUp(name);
		if(globalVariable == null){
			globalVariable = new GlobalVariable(this, type, false, LinkageTypes.ExternalLinkage, null, name, null, false);
			if(!globalVariables.contains(globalVariable))
				globalVariables.add(globalVariable);
		}
		return globalVariable;
	}

	public String getName() {
		return name;
	}

	public List<GlobalVariable> getGlobalVariables() {
		return globalVariables;
	}

	public List<GlobalAlias> getGlobalAliases() {
		return globalAliases;
	}

	public List<NamedType> getNamedTypes() {
		return namedTypes;
	}

	public void setNamedTypes(List<NamedType> namedTypes) {
		this.namedTypes = namedTypes;
	}

	public List<Function> getFunctions() {
		return functions;
	}

	public ValueSymbolTable getSymbolTable() {
		return symbolTable;
	}

	public void setTargetData(TargetData targetData) {
		this.targetData = targetData;
	}

	public TargetData getTargetData() {
		return targetData;
	}

	public void insertBefore(GlobalValue globalVariable, GlobalValue insertBefore) {
		if(globalVariable instanceof GlobalVariable){
			int index = this.getGlobalVariables().indexOf(insertBefore);
			if(index != -1)
				this.getGlobalVariables().add(index - 1, (GlobalVariable)globalVariable);
			else
				this.getGlobalVariables().add((GlobalVariable)globalVariable);
		}
		else if(globalVariable instanceof Function){
			int index = this.getFunctions().indexOf(insertBefore);
			if(index != -1)
				this.getFunctions().add(index - 1, (Function)globalVariable);
			else
				this.getFunctions().add((Function) globalVariable);
		}
	}
}

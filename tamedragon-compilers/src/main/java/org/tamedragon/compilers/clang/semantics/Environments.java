package org.tamedragon.compilers.clang.semantics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Environments {

	private Table variableAndFunctionTable;
	private Table typeTable;
	private Table escapeTable;
	
	private static Environments SINGLETON_INSTANCE;
	
	private static final Logger LOG = LoggerFactory.getLogger(Environments.class);
	
	private Environments(){
		init();		
	}
	
	private void init(){

		// Initialize the two symbol tables - one for the variable, the other the type
		variableAndFunctionTable = new Table();
		typeTable = new Table();
		escapeTable = new Table();
		
		//Initialize typeTable with predefined ids (int and string)
		typeTable.put(Symbol.symbol("int"), IntTypeEntry.getInstance());
		typeTable.put(Symbol.symbol("double"), DoubleTypeEntry.getInstance());
		typeTable.put(Symbol.symbol("void"), VoidTypeEntry.getInstance());
		typeTable.put(Symbol.symbol("float"), FloatTypeEntry.getInstance());
		typeTable.put(Symbol.symbol("char"), CharTypeEntry.getInstance());
		typeTable.put(Symbol.symbol("short"), ShortTypeEntry.getInstance());
		typeTable.put(Symbol.symbol("long"), LongTypeEntry.getInstance());
	
		//Initialize variableAndFunction table with predefined functions (print, flush, ord, etc.)
		//createTestStdFunctionPrintf();
	}
	
	public void reset(){
		init();
	}
	
	public Table getInstanceVariableTable() {
		SINGLETON_INSTANCE = getInstance();
		return SINGLETON_INSTANCE.variableAndFunctionTable;
	}
	
	public Table getInstanceTypeTable() {
		SINGLETON_INSTANCE = getInstance();
		return SINGLETON_INSTANCE.typeTable;
	}
	
	public Table getInstanceEscapeTable() {
		SINGLETON_INSTANCE = getInstance();
		return SINGLETON_INSTANCE.escapeTable;
	}	
	
	/*// TODO Remove this function later
	
	private void createTestStdFunctionPrintf(){
		Vector<VariableEntry> formals = new Vector<VariableEntry>();
		
		VariableEntry newVarEntry1 = new VariableEntry();
		TypeEntryWithAttributes teWithAttrs1 = new TypeEntryWithAttributes();
		teWithAttrs1.setBasicType(StringTypeEntry.getInstance());
		newVarEntry1.setType(teWithAttrs1);
		
		VariableEntry newVarEntry2 = new VariableEntry();
		TypeEntryWithAttributes teWithAttrs2 = new TypeEntryWithAttributes();
		teWithAttrs2.setBasicType(IntTypeEntry.getInstance());
		newVarEntry2.setType(teWithAttrs2);
		
		TypeEntryWithAttributes result = new TypeEntryWithAttributes();
		result.setBasicType(IntTypeEntry.getInstance());
		
		formals.add(newVarEntry1);
		formals.add(newVarEntry1);
		FunctionEntry funcEntry = new FunctionEntry(formals, result, null, null);
		funcEntry.setName("printf");
		variableAndFunctionTable.put(Symbol.symbol("printf"), funcEntry);
	}*/
	
	public static Environments getInstance(){
		if(SINGLETON_INSTANCE == null){
			SINGLETON_INSTANCE = new Environments();
		}
		
		return SINGLETON_INSTANCE;
	}
	
	public void debugPrintVariableTable(){
		if(SINGLETON_INSTANCE == null){
			LOG.debug("No variables in variable table.");			
			return;
		}
		
		Table vandFTable = SINGLETON_INSTANCE.variableAndFunctionTable;
		
		vandFTable.debugPrintVariableTable();
		
	}
	
}

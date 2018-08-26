package org.tamedragon.common.llvmir.symboltable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.tamedragon.common.llvmir.types.Value;

/**
 * This class is used to keep track of Value name verses its Value
 * @author ipsg
 *
 */
public class ValueSymbolTable {
	private Map<String,Value> valueMap;
	
	private int lastUnique;

	public ValueSymbolTable(Map<String,Value> vMap){
		this.valueMap = vMap;
	}

	public Value lookUp(String name){
		return valueMap.get(name);
	}

	public boolean isEmpty(){
		return valueMap.isEmpty();
	}

	public int size(){
		return valueMap.size();
	}

	/**
	 * This method adds the provided value to the symbol table.  The Value
	 * must have a name which is used to place the value in the symbol table. 
	 * If the inserted name conflicts, this renames the value.
	 * @brief Add a named value to the symbol table
	 * @param value
	 */
	public void reinsertValue(Value value){
		String name = value.getName();
		if(name == null || name.length() == 0){
			throw new RuntimeException("Cann't enter nameless value to Symbol Table");
		}
		// Try inserting the name, assuming it won't conflict.
		valueMap.put(name, value);
	}

	/**
	 * createValueName - This method attempts to create a value name and insert
	 * it into the symbol table with the specified name.  If it conflicts, it
	 * auto-renames the name and returns that instead.
	 */
	public String createValueName(String name, Value value){
		// In the common case, the name is not already in the symbol table.

		Value existingValue = valueMap.get(name);
		if (existingValue == null) {
			// No naming conflict, make an entry
			valueMap.put(name, value);
			
			return name;
		}

		String newUniqueName = name;
		while (true) {
			newUniqueName += ++lastUnique;

			existingValue = valueMap.get(newUniqueName);
			if (existingValue == null) {
				// Got a unique name, add to symbol table and return
				valueMap.put(newUniqueName, value);
				
				return newUniqueName;
			}
		}
	}

	public void removeValueName(Value value){
		List<String> names = new ArrayList<String>();
		Set<Entry<String, Value>> entries = valueMap.entrySet();
		Iterator<Entry<String, Value>> iterator = entries.iterator();
		while(iterator.hasNext()){
			Entry<String, Value> entry = iterator.next();
			Value value2 = entry.getValue();
			if(value2.equals(value)){
				names.add(entry.getKey());
			}
		}
		Iterator<String> iterator2 = names.iterator();
		while(iterator2.hasNext()){
			String name = iterator2.next();
			valueMap.remove(name);
		}
	}

	public void insertBefore(Value toBeInserted, Value value){
		Set<Entry<String, Value>> entries = valueMap.entrySet();
		List<Entry<String, Value>> listOfEntries = new ArrayList<Entry<String,Value>>(entries);
		Iterator<Entry<String, Value>> iterator = listOfEntries.iterator();
		int index = 0;
		while(iterator.hasNext()){
			Entry<String, Value> entry = iterator.next();
			Value value2 = entry.getValue();
			if(value2.equals(value)){
				break;
			}
			index++;
		}
		Entry<String, Value> entry = getMapEntry(toBeInserted.getName(), toBeInserted);
		listOfEntries.add(index - 1, entry);
		entries = new LinkedHashSet<Entry<String,Value>>(listOfEntries);
		valueMap = new LinkedHashMap<String, Value>();
		for(Entry<String, Value> entry1 : entries){
			valueMap.put(entry1.getKey(), entry1.getValue());
		}
	}

	private Entry<String, Value> getMapEntry(String name, Value value) {
		LinkedHashMap<String, Value> nameVsValue = new LinkedHashMap<String, Value>();
		nameVsValue.put(name, value);
		Set<Map.Entry<String, Value>> set_indxVsTyp = nameVsValue.entrySet();
		List<Map.Entry<String, Value>> list_indxVsTyp = new ArrayList<Map.Entry<String, Value>>(set_indxVsTyp);
		Map.Entry<String, Value> entry_NameVsValue = list_indxVsTyp.get(0);
		return entry_NameVsValue;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return valueMap.toString();
	}
}

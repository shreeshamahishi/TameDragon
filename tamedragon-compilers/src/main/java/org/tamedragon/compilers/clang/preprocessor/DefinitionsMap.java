package org.tamedragon.compilers.clang.preprocessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class DefinitionsMap {
	
	private DefinitionsMap(){
		map = new HashMap<String, String>();
		selfDefinitions = new HashSet<String>();
	}
	
	private static DefinitionsMap singletonInstance;
	
	private HashMap<String, String> map;
	private HashSet<String> selfDefinitions;
	
	public HashMap<String, String> getMap() {
		return map;
	}

	public void setMap(HashMap<String, String> map) {
		this.map = map;
	}

	public static DefinitionsMap getInstance(){
		if(singletonInstance == null){
			singletonInstance = new DefinitionsMap();
		}
		
		return singletonInstance;
	}
	
	public void setDefinition(String key, String value){
		map.put(key, value);
	}
	
	public void removeDefinition(String key){
		map.remove(key);
	}
	
	public String getDefinition(String key){
		return map.get(key);
	}
	
	public void setDefinitionOnInstance(String key, String value){
		singletonInstance.setDefinition(key, value);
	}
	
	public void removeDefinitionFromInstance(String key){
		singletonInstance.removeDefinition(key);
	}
	
	public String getDefinitionFromInstance(String key){
		return map.get(key);
	} 
	
	public void addToSelfDefinitionsOnInstance(String value){
		singletonInstance.selfDefinitions.add(value);
	}
	
	public boolean isSelfDefintionOnInstance(String value){
		return singletonInstance.selfDefinitions.contains(value);
	}

	public Set<String> getKeys(){
		HashMap<String, String> siMap = singletonInstance.getMap();
		return siMap.keySet();
		
	}
	
	public void clearDefinitions(){
		HashMap<String, String> siMap = singletonInstance.getMap();
		Set<String> keys = siMap.keySet();
		Iterator<String> iter = keys.iterator();
		List<String> defStrings = new ArrayList<String>();
		while(iter.hasNext()){
			defStrings.add(iter.next());
		}
		
		for(String def: defStrings){
			siMap.remove(def);
		}
		
	}
}

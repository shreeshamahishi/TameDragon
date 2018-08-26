package org.tamedragon.common.llvmir.types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class ConstantUniqueMap {
	private boolean HasLargeKey;

	/**
	 * Map - This is the main map from the element descriptor to the Constants.
	   This is the primary way we avoid creating two of the same shape
	   constant.
	 */
	private HashMap<Map.Entry<Type, List<Constant>>, Constant> map;

	/**
	 * InverseMap - If "HasLargeKey" is true, this contains an inverse mapping
	   from the constants to their element in Map.  This is important for
	   removal of constants from the array, which would otherwise have to scan
	   through the map with very large keys.
	 */
	HashMap<Constant, HashMap<Map.Entry<Type, List<Constant>>, Constant>> InverseMap;
	
	public ConstantUniqueMap(){
		this.InverseMap = new HashMap<Constant, HashMap<Entry<Type,List<Constant>>,Constant>>();
		this.map = new HashMap<Entry<Type,List<Constant>>, Constant>();
	}

	public Entry<Map.Entry<Type, List<Constant>>, Constant> map_begin() { 
		Set<Entry<Map.Entry<Type, List<Constant>>, Constant>> entrySet = map.entrySet();
		Iterator<Entry<Map.Entry<Type, List<Constant>>, Constant>> iterator = entrySet.iterator();
		return iterator.next();
	}

	public Entry<Map.Entry<Type, List<Constant>>, Constant> map_end() { 
		Set<Entry<Map.Entry<Type, List<Constant>>, Constant>> entrySet = map.entrySet();
		Iterator<Entry<Map.Entry<Type, List<Constant>>, Constant>> iterator = entrySet.iterator();
		while(iterator.hasNext()){
			Entry<Map.Entry<Type, List<Constant>>, Constant> entry = iterator.next();
			if(!iterator.hasNext())
				return entry;
		}
		return null;
	}

	void freeConstants() {
		Set<Entry<Map.Entry<Type, List<Constant>>, Constant>> entrySet = map.entrySet();
		Iterator<Entry<Map.Entry<Type, List<Constant>>, Constant>> iterator = entrySet.iterator();
		while(iterator.hasNext()) {
			Entry<Map.Entry<Type, List<Constant>>, Constant> entry = iterator.next();
			entry.setValue(null);
		}
	}

	/**
	 * InsertOrGetItem - Return an iterator for the specified element.
	     If the element exists in the map, the returned iterator points to the
	     entry and Exists=true.  If not, the iterator points to the newly
	     inserted entry and returns Exists=false.  Newly inserted entries have
	     I->second == 0, and should be filled in.
	 * @param insertVal
	 * @param exists
	 * @return
	 */
	public HashMap<Map.Entry<Type, List<Constant>>, Constant> insertOrGetItem(Entry<Map.Entry<Type, List<Constant>>, Constant> insertVal) {
		Set<Entry<Map.Entry<Type, List<Constant>>, Constant>> entrySet = map.entrySet();
		Iterator<Entry<Map.Entry<Type, List<Constant>>, Constant>> iterator = entrySet.iterator();
		while(iterator.hasNext()) {
			Entry<Map.Entry<Type, List<Constant>>, Constant> entry = iterator.next();

			Map.Entry<Type, List<Constant>> mapKey1 = entry.getKey();
			Constant constantClass1 = entry.getValue();

			Map.Entry<Type, List<Constant>> mapKey2 = insertVal.getKey();
			Constant constantClass2 = insertVal.getValue();

			if(mapKey1 == mapKey2 && constantClass1 == constantClass2){
				return map;
			}
		}
		map.put(insertVal.getKey(), insertVal.getValue());
		return map;
	}

	private Entry<Map.Entry<Type, List<Constant>>, Constant> findExistingElement(Constant CP) throws Exception{
		if (HasLargeKey) {
			HashMap<Map.Entry<Type, List<Constant>>, Constant> mapTy = InverseMap.get(CP);
			if(mapTy == null){
				throw new Exception("InverseMap corrupt!");
			}
			else{
				Set<Entry<Map.Entry<Type, List<Constant>>, Constant>> entries = mapTy.entrySet();
				Iterator<Entry<Map.Entry<Type, List<Constant>>, Constant>> iterator = entries.iterator();
				while(iterator.hasNext()){
					Entry<Map.Entry<Type, List<Constant>>, Constant> entry = iterator.next();
					Constant constantClass = entry.getValue();
					if(constantClass == CP)
						return entry;
				}
			}
		}
		boolean contains = map.containsValue(CP);

		if(contains){
			Set<Entry<Map.Entry<Type, List<Constant>>, Constant>> entries = map.entrySet();
			Iterator<Entry<Map.Entry<Type, List<Constant>>, Constant>> iterator = entries.iterator();
			while(iterator.hasNext()){
				Entry<Map.Entry<Type, List<Constant>>, Constant> entry = iterator.next();
				Constant constantClass = entry.getValue();
				if(constantClass == CP)
					return entry;
			}
		}
		return null;
	}
	
	  private Constant create(Type Ty, List<Constant> V) throws Exception{
		    Constant Result = ConstantCreator.create(Ty, V);
	
		    if(Result.getType() != Ty){
		    	throw new Exception("Type specified is not correct!");
		    }
		    
		    Map.Entry<Type, List<Constant>> mapKey = getMapEntry(Ty, V);
		    map.put(mapKey, Result);
		    
		    if (HasLargeKey)  // Remember the reverse mapping if needed.
		      InverseMap.put(Result, map);
	
		    return Result;
		  }

	private Map.Entry<Type, List<Constant>> getMapEntry(Type ty, List<Constant> v) {
		HashMap<Type, List<Constant>> hashMap = new HashMap<Type, List<Constant>>();
		hashMap.put(ty, v);
		Set<Entry<Type, List<Constant>>> entries = hashMap.entrySet();
		ArrayList<Entry<Type, List<Constant>>> list = new ArrayList<Entry<Type,List<Constant>>>(entries);
		return list.get(0);
	}
	
	  /**
	   *  getOrCreate - Return the specified constant from the map, creating it if
	      necessary.
	   * @param ty
	   * @param v
	   * @return
	   */
	  public Constant getOrCreate(Type ty, List<Constant> v) throws Exception{
	    Constant Result = null;
	    
	    Set<Entry<Map.Entry<Type, List<Constant>>, Constant>> entries = map.entrySet();
		Iterator<Entry<Map.Entry<Type, List<Constant>>, Constant>> iterator = entries.iterator();
		while(iterator.hasNext()){
			Entry<Map.Entry<Type, List<Constant>>, Constant> entry = iterator.next();
			Constant constantClass = entry.getValue();
			Entry<Type, List<Constant>> entry2 = entry.getKey();
			Type type = entry2.getKey();
			List<Constant> values = entry2.getValue();
			Iterator<Constant> iterator2 = values.iterator();
			Iterator<Constant> iterator3 = v.iterator();
			if(values.size() == v.size() && type == ty){
				boolean isSame = true;
				while(iterator2.hasNext()){
					Value value1 = iterator2.next();
					Value value2 = iterator3.next();
					if(!value1.toString().equals(value2.toString())){
						isSame = false;
						break;
					}
				}
				if(isSame)
					return constantClass;
			}
		}
	        
	    if (Result == null) {
	      // If no preexisting value, create one now...
	      Result = create(ty, v);
	    }
	        
	    return Result;
	  }

	//	public:
	//	  typedef std::pair<Type*, List<Value>> Map.Entry<Type, List<Value>>;
	//	  typedef std::map<Map.Entry<Type, List<Value>>, Constant *> HashMap<Map.Entry<Type, List<Value>>, Constant>;
	//	  typedef std::map<Constant *, typename HashMap<Map.Entry<Type, List<Value>>, Constant>::iterator> HashMap<Map.Entry<Type, List<Value>>, Constant>;
	//	private:
	//	 
	//
	//	public:
	//	  
	//	    

	//	    
	//	private:
	
	//	public:
	//	    

	//
	//	  void remove(Constant *CP) {
	//	    typename HashMap<Map.Entry<Type, List<Value>>, Constant>::iterator I = FindExistingElement(CP);
	//	    assert(I != Map.end() && "Constant not found in constant table!");
	//	    assert(I->second == CP && "Didn't find correct element?");
	//
	//	    if (HasLargeKey)  // Remember the reverse mapping if needed.
	//	      InverseMap.erase(CP);
	//
	//	    Map.erase(I);
	//	  }
	//
	//	  /// MoveConstantToNewSlot - If we are about to change C to be the element
	//	  /// specified by I, update our internal data structures to reflect this
	//	  /// fact.
	//	  void MoveConstantToNewSlot(Constant *C, typename HashMap<Map.Entry<Type, List<Value>>, Constant>::iterator I) {
	//	    // First, remove the old location of the specified constant in the map.
	//	    typename HashMap<Map.Entry<Type, List<Value>>, Constant>::iterator OldI = FindExistingElement(C);
	//	    assert(OldI != Map.end() && "Constant not found in constant table!");
	//	    assert(OldI->second == C && "Didn't find correct element?");
	//	      
	//	     // Remove the old entry from the map.
	//	    Map.erase(OldI);
	//	    
	//	    // Update the inverse map so that we know that this constant is now
	//	    // located at descriptor I.
	//	    if (HasLargeKey) {
	//	      assert(I->second == C && "Bad inversemap entry!");
	//	      InverseMap[C] = I;
	//	    }
	//	  }
	//
	//	  void dump() const {
	//	    DEBUG(dbgs() << "Constant.cpp: ConstantUniqueMap\n");
	//	  }

}

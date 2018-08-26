package org.tamedragon.compilers.clang.semantics;

import java.util.Hashtable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Binder 
{
	  private Object value;  // The 
	  Symbol prevtopSymbolKey;
	  Binder tail;
	  
	  public Binder(Object v, Symbol p, Binder t)
	  {
		value = v; 
		prevtopSymbolKey = p; 
		tail = t;
	  }
	  
	  public Object getValue()
	  {
		  return value;
	  } 
}

	/**
	 * The Table class is similar to java.util.Hashtable, except that
	 * each key must be a SymbolKey and there is a scope mechanism.
	 */

	public class Table {

	 private static final Logger LOG = LoggerFactory.getLogger(Table.class);
		
	  private Hashtable hashTable;
	  private Symbol topSymbolKey;
	  private Binder marks;

	  public Table() {
		  hashTable = new Hashtable();
	  }
	  
	  public boolean keyInCurrentScope(Symbol key) {
		  Symbol tempSymbolKey = topSymbolKey;
		  
		  boolean foundKey = false;
		  while (tempSymbolKey != null)  {			  
			  if(key == tempSymbolKey) {
				  foundKey = true;
				  break;
			  }
			  Binder entry = (Binder)hashTable.get(tempSymbolKey);
			  tempSymbolKey = entry.prevtopSymbolKey;
		  }
		  
		  return foundKey;
	  }

	 /**
	  * Gets the object associated with the specified symbol in the Table.
	  */
	  public Object get(Symbol key) {
		  Binder e = (Binder) hashTable.get(key);
			if (e == null) return null;
			else return e.getValue();
	  }	

	 /**
	  * Puts the specified value into the Table, bound to the specified Symbol.
	  */
	  public void put(Symbol key, Object value)  {
		  hashTable.put(key, new Binder(value, topSymbolKey, (Binder)hashTable.get(key)));
		  topSymbolKey = key;
	  }

	 /**
	  * Remembers the current state of the Table by putting a special marker.
	  */
	  public void beginScope()  {
		  marks = new Binder(null, topSymbolKey, marks); 
		  topSymbolKey = null;
	  }

	 /** 
	  * Restores the table to what it was at the most recent beginScope
	  *	that has not already been ended.
	  */
	  public void endScope()   {
		while (topSymbolKey!=null)  {
		   Binder entry = (Binder)hashTable.get(topSymbolKey);
		   if (entry.tail != null) hashTable.put(topSymbolKey, entry.tail); // restore old entry
		   else hashTable.remove(topSymbolKey);
		   topSymbolKey = entry.prevtopSymbolKey;
		}
		
		topSymbolKey = marks.prevtopSymbolKey;
		marks = marks.tail;
	  }
	  
	  /**
	   * Returns an enumeration of the Table's symbols.
	   */
	  public java.util.Enumeration keys() {
		  return hashTable.keys();
	  }
	  
	  public void debugPrintVariableTable() {
		  Symbol tempSymbolKey = topSymbolKey;
		 
		  while (tempSymbolKey != null)  {		
			  
			  LOG.debug("Variable or function entry name in current scope = " + tempSymbolKey);
			  
			  Binder entry = (Binder)hashTable.get(tempSymbolKey);
			  tempSymbolKey = entry.prevtopSymbolKey;
		  }
	  }
	  
}



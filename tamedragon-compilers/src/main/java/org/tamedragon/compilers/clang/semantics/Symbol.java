package org.tamedragon.compilers.clang.semantics;

import java.util.Hashtable;

public class Symbol {
  
	private String name;
	private static Hashtable<String, Symbol> symbolVsString = new Hashtable<String, Symbol>();
	
	private Symbol(String n)  {
		name = n;
	}
	
	public String toString()  {
		return name;
	}

  /** 
   * Make return the unique symbol associated with a string.
   * Repeated calls to <tt>symbol("abc")</tt> will return the same Symbol.
   */
	public static Symbol symbol(String n)  {
		String u = n.intern();
		Symbol s = (Symbol)symbolVsString.get(u);
		if (s == null)  {
			s = new Symbol(u);
			symbolVsString.put(u,s);
		}
		return s;
	}
}


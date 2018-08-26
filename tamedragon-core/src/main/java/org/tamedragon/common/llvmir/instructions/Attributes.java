package org.tamedragon.common.llvmir.instructions;

import java.util.ArrayList;
import java.util.List;


/**
 * Function parameters and results can have attributes to indicate how they
 * should be treated by optimizations and code generation. This enumeration 
 * lists the attributes that can be associated with parameters, function 
 * results or the function itself.
 */

public class Attributes {

	public static final int None      = 0;             // No attributes have been set
	public static final int ZExt      = 1<<0;          // Zero extended before/after call
	public static final int SExt      = 1<<1;          // Sign extended before/after call
	public static final int NoReturn  = 1<<2;          // Mark the function as not returning
	public static final int InReg     = 1<<3;          // Force argument to be passed in register
	public static final int StructRet = 1<<4;          // Hidden pointer to structure to return
	public static final int NoUnwind  = 1<<5;          // Function doesn't unwind stack
	public static final int NoAlias   = 1<<6;          // Considered to not alias after call
	public static final int ByVal     = 1<<7;          // Pass structure by value
	public static final int Nest      = 1<<8;          // Nested function static chain
	public static final int ReadNone  = 1<<9;          // Function does not access memory
	public static final int ReadOnly  = 1<<10;         // Function only reads from memory
	public static final int NoInline        = 1<<11;   // inline=never
	public static final int AlwaysInline    = 1<<12;   // inline=always
	public static final int OptimizeForSize = 1<<13;   // opt_size
	public static final int StackProtect    = 1<<14;   // Stack protection.
	public static final int StackProtectReq = 1<<15;   // Stack protection required.
	public static final int Alignment = 31<<16;        // Alignment of parameter (5 bits) stored as log2 of alignment with +1 bias
	                                                   // 0 means unaligned different from align 1
	public static final int NoCapture = 1<<21;         // Function creates no aliases of pointer
	public static final int NoRedZone = 1<<22;         // disable redzone
	public static final int NoImplicitFloat = 1<<23;   // disable implicit floating point instructions.
	public static final int Naked           = 1<<24;   // Naked function
	public static final int InlineHint      = 1<<25;   // source said inlining was desirable
	public static final int StackAlignment  = 7<<26;   // Alignment of stack for function (3 bits) stored as log2
	                                                   //of alignment with +1 bias 0 means unaligned (different from alignstack(1))
	public static final int ReturnsTwice    = 1<<29;   // Function can return twice
	public static final int UWTable     = 1<<30;       // Function must be in a unwind table
	public static final int NonLazyBind = 1 <<31;      // Function is called early and/or often, so lazy binding isn't worthwhile.

	// @brief Attributes that only apply to function parameters.
	public static final int ParameterOnly = ByVal | Nest | StructRet | NoCapture;

	/**
	 * Attributes that may be applied to the function itself.  These cannot
	 * be used on return values or function parameters.
	 */
	public static final int FunctionOnly = NoReturn | NoUnwind | ReadNone | ReadOnly |
	  NoInline | AlwaysInline | OptimizeForSize | StackProtect | StackProtectReq |
	  NoRedZone | NoImplicitFloat | Naked | InlineHint | StackAlignment |
	  UWTable | NonLazyBind | ReturnsTwice;

	// @brief Parameter attributes that do not apply to vararg call arguments.
	public static final int VarArgsIncompatible = StructRet;

	// @brief Attributes that are mutually incompatible.
	public static final int MutuallyIncompatible[] = {
	  ByVal | InReg | Nest | StructRet,
	  ZExt  | SExt,
	  ReadNone | ReadOnly,
	  NoInline | AlwaysInline };
	
	private int attribute;
	
	public Attributes(int attribute){
		this.attribute = attribute;
	}
	
	public int getAttribute() {
		return attribute;
	}

	public void setAttribute(int attribute) {
		this.attribute = attribute;
	}
	
	public static String getAttributeAsString(int attribute){
		String attr = "";
		switch(attribute){
		case 1<<0 : attr = "zeroext";break;
		case 1<<1 : attr = "signext";break;
		case 1<<2 : attr = "noreturn";break;
		case 1<<3 : attr = "inreg";break;
		case 1<<4 : attr = "sret";break;
		case 1<<5 : attr = "nounwind";break;
		case 1<<6 : attr = "noalias";break;
		case 1<<7 : attr = "byval";break;
		case 1<<8 : attr = "nest";break;
		case 1<<9 : attr = "readnone";break;
		case 1<<10 : attr = "readonly";break;
		case 1<<11 : attr = "noinline";break;
		case 1<<12 : attr = "alwaysinline";break;
		case 1<<13 : attr = "optsize";break;
		case 1<<14 : attr = "ssp";break;
		case 1<<15 : attr = "sspreq";break;
		case 1<<21 : attr = "nocapture";break;
		case 1<<22 : attr = "noredzone";break;
		case 1<<23 : attr = "noimplicitfloat";break;
		case 1<<24 : attr = "naked";break;
		case 1<<25 : attr = "inlinehint";break;
		case 1<<29 : attr = "returns_twice";break;
		case 1<<30 : attr = "uwtable";break;
		case 1<<31 : attr = "nonlazybind";break;
		}
		return attr;
	}
	
	// TODO Make this more elegant
	public static List<Integer> getAttributesAsList(int attrs){
		List<Integer> listOfAttrs = new ArrayList<Integer>();
		String bitStr = Integer.toBinaryString(attrs);
		char chArr[] = bitStr.toCharArray();
		for(int i = 0; i < chArr.length; i++){
			if(chArr[i] == '1'){
				String str = "1";
				for(int j = i+1; j < chArr.length; j++){
					str += "0";
				}
				Integer intVal = LLVMUtility.binaryToDecimal(str);
				listOfAttrs.add(intVal);
			}
		}
		return listOfAttrs;
	}
}

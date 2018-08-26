package org.tamedragon.compilers.clang.semantics;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.tamedragon.assemblyabstractions.constructs.IRTree;
import org.tamedragon.common.llvmir.types.ConstantInt;
import org.tamedragon.common.llvmir.types.Temp;
import org.tamedragon.common.llvmir.types.Value;
import org.tamedragon.compilers.clang.ErrorHandler;
import org.tamedragon.compilers.clang.SourceLocation;

abstract class Entry { }

class BasicType extends Entry{
	
	public BasicType actual() {
		return this;
	}
	
	public boolean equals(Object object){
		if(!(object instanceof BasicType))
			return false;
		
		BasicType basicType = (BasicType)object;		
		
		if(this == IntTypeEntry.getInstance()){
			if(!(basicType == IntTypeEntry.getInstance()))
				return false;				
		}
		else if(this == DoubleTypeEntry.getInstance()){
			if(!(basicType == DoubleTypeEntry.getInstance()))
				return false;	
		}
		else if(this == VoidTypeEntry.getInstance()){
			if(!(basicType == VoidTypeEntry.getInstance()))
				return false;	
		}
		else if(this == CharTypeEntry.getInstance()){
			if(!(basicType == CharTypeEntry.getInstance()))
				return false;	
		}
		else if(this == ShortTypeEntry.getInstance()){
			if(!(basicType == ShortTypeEntry.getInstance()))
				return false;	
		}
		else if(this == FloatTypeEntry.getInstance()){
			if(!(basicType == FloatTypeEntry.getInstance()))
				return false;	
		}
		else if(this == StringTypeEntry.getInstance()){
			if(!(basicType == StringTypeEntry.getInstance()))
				return false;	
		}
		else if(this == LongTypeEntry.getInstance()){
			if(!(basicType == LongTypeEntry.getInstance()))
				return false;	
		}
		else if(this instanceof PointerTypeEntry){
			if(!(basicType instanceof PointerTypeEntry))
				return false;	
			
			// TODO - Verify this equals
			PointerTypeEntry currentPtrTypeEntr = (PointerTypeEntry) this;
			PointerTypeEntry otherPtrTypeEntr = (PointerTypeEntry) basicType;
			
			if(otherPtrTypeEntr == null)
				return false;
			
			TypeEntryWithAttributes currNext = currentPtrTypeEntr.getBaseTypeEntry();
			TypeEntryWithAttributes otherNext = otherPtrTypeEntr.getBaseTypeEntry();
			if(! (currNext.equals(otherNext))){
				return false;
			}

		}
		else if(this instanceof FunctionEntry){
			if(!(basicType instanceof FunctionEntry))
				return false;	
			
			FunctionEntry funcEntry = (FunctionEntry) this;
			FunctionEntry otherFuncEntry = (FunctionEntry) basicType;
			
			if(!funcEntry.getReturnType().equals(otherFuncEntry.getReturnType())){
				return false;
			}
			else{
				Vector<VariableEntry> formals = funcEntry.getFormals();
				Vector<VariableEntry> otherFormals = otherFuncEntry.getFormals();
				if(formals == null || formals.size() == 0){
					if(!(otherFormals == null || otherFormals.size() == 0))
						return false;
					else 
						return true;
				}
				
				if(formals.size() != otherFormals.size())
					return false;
				
				int count = 0;
				for(VariableEntry varEntry : formals){
					TypeEntryWithAttributes teVarEntry = varEntry.getType();
					TypeEntryWithAttributes otherTeVarEntry = otherFormals.elementAt(count).getType();
					if(!teVarEntry.equals(otherTeVarEntry))
						return false;
					count++;
				}
			}
		}
		else if(this instanceof ArrayTypeEntry){
			if(!(basicType instanceof ArrayTypeEntry))
				return false;	
			
			// TODO : Implement later
		}
		else if(this instanceof StructOrUnionTypeEntry){
			if(!(basicType instanceof StructOrUnionTypeEntry))
				return false;	
			
			StructOrUnionTypeEntry strOrUnion = (StructOrUnionTypeEntry) this;
			StructOrUnionTypeEntry otherStrOrUnion = (StructOrUnionTypeEntry) basicType;
			
			return strOrUnion.equals(otherStrOrUnion);
		}
		else if(this instanceof TypeDefNameTypeEntry){
			if(!(basicType instanceof TypeDefNameTypeEntry))
				return false;	
			
			TypeDefNameTypeEntry tdNameEntry = (TypeDefNameTypeEntry) this;
			TypeDefNameTypeEntry otherTdNameEntry = (TypeDefNameTypeEntry) basicType;
			
			return tdNameEntry.equals(otherTdNameEntry);
			
		}
		// TODO - Implement for enum, structs, etc
		else if(this instanceof EnumSpecTypeEntry){
			
		}
		/*else if(this instanceof NameTypeEntry){
			
		}*/
		
		return true;
	}
	
	public String toString(){
		
		if(this == IntTypeEntry.getInstance()){
			return " int";			
		}
		else if(this == DoubleTypeEntry.getInstance()){
			return " double"; 	
		}
		else if(this == VoidTypeEntry.getInstance()){
			return " void";	
		}
		else if(this == CharTypeEntry.getInstance()){
			return " char";	
		}
		else if(this == ShortTypeEntry.getInstance()){
			return " short";
		}
		else if(this == FloatTypeEntry.getInstance()){
			return " float";
		}
		else if(this == StringTypeEntry.getInstance()){
			return " string";
		}
		else if(this == LongTypeEntry.getInstance()){
			return " long";
		}
		else if(this instanceof PointerTypeEntry){
			PointerTypeEntry pte = (PointerTypeEntry)this;			
			return pte.toString();
		}
		else if(this instanceof ArrayTypeEntry){
			ArrayTypeEntry ate = (ArrayTypeEntry)this;			
			return ate.toString();
		}
		else if(this instanceof FunctionEntry){
			FunctionEntry ate = (FunctionEntry)this;			
			return ate.getDescription();
		}
		
		// TODO - Implement for enum, type name, structs, etc
		else if(this instanceof EnumSpecTypeEntry){
			return "todo";
		}
		else if(this instanceof TypeDefNameTypeEntry){
			return "todo";
		}
		else if(this instanceof StructOrUnionTypeEntry){
			StructOrUnionTypeEntry structOrUnionTypeEntry = (StructOrUnionTypeEntry)this;
			return structOrUnionTypeEntry.toString();
		}
		
		return "";  // Should not happen
	}	
}

class TypeEntryWithAttributes {
	
	// Attributes
	private boolean isConst;
	private boolean isVolatile;
	//private boolean isSigned;
	private boolean isUnsigned;
	private boolean isAuto;
	private boolean isExtern;
	private boolean isRegister;
	private boolean isStatic;
	private boolean isTypedef;
	private boolean isStructOrUnionSpec;
	private boolean isEnumSpec;
	private boolean isVoid;
	private boolean isTypedefName;
	private boolean isEnumConstant;
	
	private BasicType basicType;
	
	private String literalValue;  // Will have a value if it is a literal, else null
	private boolean isLValue;     // Flag to indicate if this is an lvalue

	// Setters and getters for attributes
	
	public boolean isConst() { return isConst; }

	public void setConst(boolean isConst) { this.isConst = isConst; }

	public boolean isVolatile() { return isVolatile; }

	public void setVolatile(boolean isVolatile) { this.isVolatile = isVolatile; }

	/*public boolean isSigned() { return isSigned; }

	public void setSigned(boolean isSigned) { 
		this.isSigned = isSigned;
		if(isSigned)
			this.isUnsigned = false;
	}*/

	public boolean isUnsigned(){
		return isUnsigned; 
	}

	public void setUnsigned(boolean isUnsigned) {
		this.isUnsigned = isUnsigned;
	}

	public boolean isAuto() { return isAuto; }

	public void setAuto(boolean isAuto) { this.isAuto = isAuto; }

	public boolean isExtern() { return isExtern; }

	public void setExtern(boolean isExtern) { this.isExtern = isExtern; }

	public boolean isRegister() { return isRegister; }

	public void setRegister(boolean isRegister) { this.isRegister = isRegister; }

	public boolean isStatic() { return isStatic; }

	public void setStatic(boolean isStatic) { this.isStatic = isStatic; }

	public boolean isTypedef() { return isTypedef; }

	public void setTypedef(boolean isTypedef) { this.isTypedef = isTypedef; }

	public boolean isStructOrUnionSpec() { return isStructOrUnionSpec; }

	public void setStructOrUnionSpec(boolean isStructOrUnionSpec) { this.isStructOrUnionSpec = isStructOrUnionSpec; }

	public boolean isEnumSpec() { return isEnumSpec; }

	public void setEnumSpec(boolean isEnumSpec) { this.isEnumSpec = isEnumSpec; }

	public boolean isTypedefName() { return isTypedefName; }

	public void setTypedefName(boolean isTypedefName) { this.isTypedefName = isTypedefName; }

	public boolean isVoid() { return isVoid; }

	public void setVoid(boolean isVoid) { this.isVoid = isVoid; }

	public BasicType getBasicType() { return basicType; }

	public void setBasicType(BasicType basicType) { this.basicType = basicType; }
	
	public String getLiteralValue() { return literalValue; }

	public void setLiteralValue(String literalValue) { this.literalValue = literalValue; }
	
	public void setIsLValue(boolean isLValue){ this.isLValue = isLValue; }
	
	public boolean isLValue() {return isLValue; }
	
	/*
	 * Utility function that accepts two TypeEntryWithAttributes objects, sets the basic of the first one with that
	 * of the second. To identify the basic type of the first object, we iterate until the last type is null
	 */
	public static String updateBasicType(TypeEntryWithAttributes teOfVarEntry, TypeEntryWithAttributes baseTeWithAttrs){
		
		String status = ErrorHandler.OK;
		BasicType bt = teOfVarEntry.getBasicType();
		if(bt == null){
			teOfVarEntry.copy(baseTeWithAttrs, false);			
			
			return status;
		}

		while(true){			
			if(bt instanceof PointerTypeEntry){
				PointerTypeEntry pte = (PointerTypeEntry) bt;
				teOfVarEntry = pte.getBaseTypeEntry();				
				if(teOfVarEntry == null){
					pte.setBaseTypeEntry(baseTeWithAttrs);
					break;
				}				
				bt = teOfVarEntry.getBasicType();				
			}
			else if(bt instanceof ArrayTypeEntry){
				ArrayTypeEntry ate = (ArrayTypeEntry) bt;
				teOfVarEntry = ate.getBaseTypeEntry();
				
				if(teOfVarEntry == null){
					if(baseTeWithAttrs.getBasicType() instanceof FunctionEntry)
						// Cannot have an array of functions
						status = ErrorHandler.E_ARRAY_OF_FUNCTIONS;
					else
						ate.setBaseTypeEntry(baseTeWithAttrs);
					break;
				}
				bt = teOfVarEntry.getBasicType();	
			}
			else if(bt instanceof FunctionEntry){
				FunctionEntry fe = (FunctionEntry) bt;
				teOfVarEntry = fe.getReturnType();				
				if(teOfVarEntry == null){
					if(baseTeWithAttrs.getBasicType() instanceof FunctionEntry)
						// Cannot have a function returning a function
						status = ErrorHandler.E_DECL_FUNC_RETURNING_FUNC;
					else if(baseTeWithAttrs.getBasicType() instanceof ArrayTypeEntry)
						// Cannot have a function returning an array
						status = ErrorHandler.E_DECL_FUNC_RETURNING_ARRAY;
					else
						fe.setReturnType(baseTeWithAttrs);

					break;
				}
				bt = teOfVarEntry.getBasicType();	
			}
		}
		
		return status;
	}

	public String toString(){
		if(literalValue != null)
			return literalValue;
		
		String str = "";
		
		String baseStr = "";
		if(basicType != null)
			baseStr = basicType.toString();
		
		
		if(isAuto) str += " auto";
		if(isConst) str += " const";
		if(isEnumSpec) str += " enum specifier:";
		if(isExtern) str += " extern";
		if(isRegister) str += " register";
		//if(isSigned){
			//if(baseStr.indexOf("signed") < 0){  // Already noted as signed
			//	str += " signed";
			//}
		//}
		if(isStatic) str += " static";
		if(isStructOrUnionSpec) str += " struct or union spec:";
		if(isTypedef) str += " typedef";
		if(isTypedefName) str += " typdef name:";
		if(isUnsigned){
			if(baseStr.indexOf("unsigned") < 0){  // Already noted as unsigned
				str += " unsigned";
			}
		}
		if(isVoid){
			if(baseStr.indexOf("void") < 0){  // Already noted as void
				str += " void";
			}
		}
		if(isVolatile) 
			str += " volatile";

		str = str +  baseStr;
		
		return str;
	}
	
	public void copy(TypeEntryWithAttributes other, boolean dontCopyBasicType){
		if(!dontCopyBasicType)
			setBasicType(other.getBasicType());
		
		setAuto(other.isAuto());
		setConst(other.isConst());
		setEnumSpec(other.isEnumSpec());
		setExtern(other.isExtern());
		setIsLValue(other.isLValue());
		setLiteralValue(other.getLiteralValue());
		setRegister(other.isRegister());
		//ssetSigned(other.isSigned());
		setStatic(other.isStatic());
		setStructOrUnionSpec(other.isStructOrUnionSpec());
		setTypedef(other.isTypedef());
		setTypedefName(other.isTypedefName());
		setUnsigned(other.isUnsigned());
		setVoid(other.isVoid());
		setVolatile(other.isVolatile());
	}
	
	public boolean equals(Object object){
		if(!(object instanceof TypeEntryWithAttributes))
			return false;
				
		TypeEntryWithAttributes other = (TypeEntryWithAttributes)object;
		
		BasicType otherBasicType = other.getBasicType();
		
		TypeEntryWithAttributes thisTeWithAttrs = null;
		TypeEntryWithAttributes otherTeWithAttrs = null;
		if(basicType instanceof TypeDefNameTypeEntry){
			TypeDefNameTypeEntry thisTypeDef = (TypeDefNameTypeEntry)basicType;
			thisTeWithAttrs = thisTypeDef.getActualBasicType();
			basicType = thisTeWithAttrs.getBasicType();
		}
		if(other.getBasicType() instanceof TypeDefNameTypeEntry){
			TypeDefNameTypeEntry otherTypeDef = (TypeDefNameTypeEntry)other.getBasicType();
			otherTeWithAttrs = otherTypeDef.getActualBasicType();
			otherBasicType = otherTeWithAttrs.getBasicType();
		}
		
		// Check the basic types first
		if(!basicType.equals(otherBasicType))
			return false;
		
		//TODO : Check if the following is correct
		
		if(isConst != other.isConst())
			return false;
		if(isAuto != other.isAuto())
			return false;
		if(isEnumSpec != other.isEnumSpec())
			return false;
		if(isExtern != other.isExtern())
			return false;
		if(isRegister != other.isRegister())
			return false;
		//if(isSigned != other.isSigned())
		//	return false;
		//if(isStatic != other.isStatic())
		//	return false;
		if(isStructOrUnionSpec != other.isStructOrUnionSpec())
			return false;
		if(isTypedef != other.isTypedef())
			return false;
		if(isUnsigned != other.isUnsigned())
			return false;
		if(isVolatile() != other.isVolatile())
			return false;
		if(isVoid != other.isVoid())
			return false;
		
		return true;
		
	}
	
	public TypeEntryWithAttributes clone(){
		TypeEntryWithAttributes newTa = new TypeEntryWithAttributes();
		
		newTa.setAuto(isAuto);
		newTa.setBasicType(basicType);
		newTa.setConst(isConst);
		newTa.setEnumSpec(isEnumSpec);
		newTa.setExtern(isExtern);
		newTa.setIsLValue(isLValue);
		newTa.setLiteralValue(literalValue);
		newTa.setRegister(isRegister);
		//newTa.setSigned(isSigned);
		newTa.setStatic(isStatic);
		newTa.setStructOrUnionSpec(isStructOrUnionSpec);
		newTa.setTypedef(isTypedef);
		newTa.setTypedefName(isTypedefName);
		newTa.setUnsigned(isUnsigned);
		newTa.setVoid(isVoid);
		newTa.setVolatile(isVolatile);
		
		return newTa;
	}

	public boolean isEnumConstant() {
		return isEnumConstant;
	}

	public void setEnumConstant(boolean isEnumConstant) {
		this.isEnumConstant = isEnumConstant;
	}
}

/**
 * 
 * Singleton class for the type entry for int
 *
 */

class IntTypeEntry extends BasicType {
	private static IntTypeEntry singleInstance;
	
	private IntTypeEntry(){	}
	
	public static IntTypeEntry getInstance() {
		if(singleInstance == null)
			singleInstance = new IntTypeEntry();
		
		return singleInstance;
	}
}

/**
 * 
 * Singleton class for the type entry for double
 *
 */

class DoubleTypeEntry extends BasicType {
	private static DoubleTypeEntry singleInstance;
	
	private DoubleTypeEntry(){	}
	
	public static DoubleTypeEntry getInstance() {
		if(singleInstance == null)
			singleInstance = new DoubleTypeEntry();
		
		return singleInstance;
	}
}

/**
 * 
 * Singleton class for the type entry for half precision
 *
 */

class HalfTypeEntry extends BasicType {
	private static HalfTypeEntry singleInstance;
	
	private HalfTypeEntry(){	}
	
	public static HalfTypeEntry getInstance() {
		if(singleInstance == null)
			singleInstance = new HalfTypeEntry();
		
		return singleInstance;
	}
}


/**
 * 
 * Singleton class for the type entry for void
 *
 */

class VoidTypeEntry extends BasicType {
	private static VoidTypeEntry singleInstance;
	
	private VoidTypeEntry(){	}
	
	public static VoidTypeEntry getInstance() {
		if(singleInstance == null)
			singleInstance = new VoidTypeEntry();
		
		return singleInstance;
	}
}

/**
 * 
 * Singleton class for the type entry for char
 *
 */

class CharTypeEntry extends BasicType {
	private static CharTypeEntry singleInstance;
	
	private CharTypeEntry(){	}
	
	public static CharTypeEntry getInstance() {
		if(singleInstance == null)
			singleInstance = new CharTypeEntry();
		
		return singleInstance;
	}
}

/**
 * 
 * Singleton class for the type entry for short
 *
 */

class ShortTypeEntry extends BasicType {
	private static ShortTypeEntry singleInstance;
	
	private ShortTypeEntry(){	}
	
	public static ShortTypeEntry getInstance() {
		if(singleInstance == null)
			singleInstance = new ShortTypeEntry();
		
		return singleInstance;
	}
}

/**
 * 
 * Singleton class for the type entry for float
 *
 */

class FloatTypeEntry extends BasicType {
	private static FloatTypeEntry singleInstance;
	
	private FloatTypeEntry(){	}
	
	public static FloatTypeEntry getInstance() {
		if(singleInstance == null)
			singleInstance = new FloatTypeEntry();
		
		return singleInstance;
	}
}

/**
 * 
 * Singleton class for the type entry for string
 *
 */

class StringTypeEntry extends BasicType {
	private static StringTypeEntry singleInstance;
	
	private StringTypeEntry(){	}
	
	public static StringTypeEntry getInstance() {
		if(singleInstance == null)
			singleInstance = new StringTypeEntry();
		
		return singleInstance;
	}
}

/**
 * 
 * Singleton class for the type entry for long
 *
 */

class LongTypeEntry extends BasicType {
	private static LongTypeEntry singleInstance;
	
	private LongTypeEntry(){	}
	
	public static LongTypeEntry getInstance() {
		if(singleInstance == null)
			singleInstance = new LongTypeEntry();
		
		return singleInstance;
	}
}

class EnumSpecTypeEntry extends BasicType {
	
	private String tag;
	private Map<String, ConstantInt> namesVsValue;	
	private SourceLocation sourceLocation;
	
	public EnumSpecTypeEntry(){}
	
	public EnumSpecTypeEntry(String tag, Map<String, ConstantInt> namesVsValue, SourceLocation sourceLocation){
		this.tag = tag;
		this.namesVsValue = namesVsValue;
		this.sourceLocation = sourceLocation;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Map<String, ConstantInt> getNames() {
		return namesVsValue;
	}

	public void setNames(Map<String, ConstantInt> namesVsValue) {
		this.namesVsValue = namesVsValue;
	}

	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}

	public void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
}

class TypeDefNameTypeEntry extends BasicType {
	private String typeName;
	private TypeEntryWithAttributes actualBasicType;  // In a declaration like 'typedef int Length', this will be int (IntTypeEntry)
	
	private String referenceTypeName;   // In declarations like 'typedef int Length; typedef Length Dist'
	                                    // the reference type name for Dist would be Length; for Length it
	                                    // would be null.
	private SourceLocation sourceLocation;
	
	public TypeDefNameTypeEntry(){}
	
	public TypeDefNameTypeEntry(String typeName, TypeEntryWithAttributes base, 
			String referenceName, SourceLocation sourceLocation){
		this.typeName = typeName;
		this.actualBasicType = base;
		this.referenceTypeName = referenceName;
		this.sourceLocation = sourceLocation;
	}
	
	
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public TypeEntryWithAttributes getActualBasicType() {
		return actualBasicType;
	}
	public void setActualBasicType(TypeEntryWithAttributes actualBasicType) {
		this.actualBasicType = actualBasicType;
	}
	public String getReferenceTypeName() {
		return referenceTypeName;
	}
	public void setReferenceTypeName(String referenceTypeName) {
		this.referenceTypeName = referenceTypeName;
	}

	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}

	public void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
	
	public boolean equals(Object object){
		if(object instanceof TypeDefNameTypeEntry) {
			TypeDefNameTypeEntry other = (TypeDefNameTypeEntry) object;
			return actualBasicType.equals(other.getActualBasicType());			
		}
		else{
			return actualBasicType.equals(object);
		}
	}	
}

class StructOrUnionTypeEntry extends BasicType implements TypeEntry {
	private String tag;
	private boolean isStruct;
	private LinkedHashMap<String, TypeEntryWithAttributes> members;
	
	private SourceLocation sourceLocation;
	
	public StructOrUnionTypeEntry(){}
	
	public StructOrUnionTypeEntry(String tag, boolean isStruct, 
			LinkedHashMap<String, TypeEntryWithAttributes> members, SourceLocation sourceLocation){
		this.tag = tag;
		this.isStruct = isStruct;
		this.members = members;
		this.sourceLocation = sourceLocation;
	}
	
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public LinkedHashMap<String, TypeEntryWithAttributes> getMembers() {
		return members;
	}
	public void setMembers(LinkedHashMap<String, TypeEntryWithAttributes> members) {
		this.members = members;
	}
	public boolean isStruct() {
		return isStruct;
	}
	public void setStruct(boolean isStruct) {
		this.isStruct = isStruct;
	}

	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}

	public void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}	
	
	public Vector<String> getMemberNames(){
		Vector<String> keys = new Vector<String>();
		
		if(members == null)
			return keys;
		
		Set<java.util.Map.Entry<String, TypeEntryWithAttributes>> entrySet = members.entrySet();
		Iterator<java.util.Map.Entry<String, TypeEntryWithAttributes>> iterator = entrySet.iterator();
		while(iterator.hasNext()){
			java.util.Map.Entry<String, TypeEntryWithAttributes> entry = iterator.next();
			String key = entry.getKey();
			keys.add(key);			
		}
		
		return keys;
	}
	
	public TypeEntryWithAttributes getMemberType(String name){
		if(members != null)
			return members.get(name);
		else
			return null;
	}
	
	public int getNumMembers(){
		if(members == null)
			return -1;
		else
			return members.size();
	}
	
	public boolean equals(Object obj){
		if(!(obj instanceof StructOrUnionTypeEntry))
			return false;
		
		StructOrUnionTypeEntry otherStrOrUn = (StructOrUnionTypeEntry) obj;
		if(isStruct && otherStrOrUn.isStruct()){  // Both are structs
			if(!tag.equals(otherStrOrUn.getTag()) && !(tag+".0").equals(otherStrOrUn.getTag()))
				return false;
		}
		else if(!isStruct && !otherStrOrUn.isStruct()){  // Both are unions
			if(!tag.equals(otherStrOrUn.getTag()))
				return false;
		}
		else{
			return false;
		}
		
		return true;
	}
	
	public String toString(){
		String desc = "struct{";
		List<String> memNames = getMemberNames();
		int count = 0;
		for(String memName: memNames){
			TypeEntryWithAttributes teMem = getMemberType(memName);
			
			if(count < members.size() -1)
				desc += memName + ":" + teMem.toString() + "; ";
			else
				desc += memName + ":" + teMem.toString();
			count++;
		}
		
		desc += "}";
		
		return desc;
		
	}
	
}

class ArrayTypeEntry extends BasicType implements DeclaratorChainElement {
	private  TypeEntryWithAttributes baseTypeEntry;
	private TypeEntryWithAttributes dimension;
	private IRTree irTree;
	private boolean isVariableLengthArray;
	
	public ArrayTypeEntry() {}
	
	public ArrayTypeEntry(TypeEntryWithAttributes e) {
		baseTypeEntry = e;
	}
	
	public TypeEntryWithAttributes getBaseTypeEntry() {
		return baseTypeEntry;
	}
	
	public void setBaseTypeEntry(TypeEntryWithAttributes bt){
		baseTypeEntry = bt;
	}
	
	public int getDeclaratorChainElementType(){
		return ARRAY;
	}

	public TypeEntryWithAttributes getDimension() {
		return dimension;
	}

	public void setDimension(TypeEntryWithAttributes dimension) {
		this.dimension = dimension;
	}
	
	public IRTree getIrTree() {
		return irTree;
	}

	public void setIrTree(IRTree irTree) {
		this.irTree = irTree;
	}

	public boolean isVariableLengthArray() {
		return isVariableLengthArray;
	}

	public void setVariableLengthArray(boolean isVariableLengthArray) {
		this.isVariableLengthArray = isVariableLengthArray;
	}

	public String setNextElementInDeclaratorChain(DeclaratorChainElement dce){
		if(baseTypeEntry == null){
			if(dce.getDeclaratorChainElementType() == DeclaratorChainElement.DECL_CHAIN_FUNCTION){
				return ErrorHandler.E_ARRAY_OF_FUNCTIONS;
			}
			
			TypeEntryWithAttributes teWithAttrs = new TypeEntryWithAttributes();
			teWithAttrs.setBasicType((BasicType)dce);
			baseTypeEntry = teWithAttrs;
			return ErrorHandler.OK;
		}
		else{
			TypeEntryWithAttributes newTeWithAttrs = new TypeEntryWithAttributes();
			newTeWithAttrs.setBasicType((BasicType)dce);
			return TypeEntryWithAttributes.updateBasicType(baseTypeEntry, newTeWithAttrs);
		}
	}
	
	public String toString(){
		String str = " array";
		if(dimension != null)
			str += "[" +dimension.toString() + "]";
		else
			str += "[]";
		str += " of";
		str += baseTypeEntry.toString();
		return str;
	}	
}

class PointerTypeEntry extends BasicType implements DeclaratorChainElement{
	private  TypeEntryWithAttributes baseTypeEntry;
	private boolean isConstPtr = false;
	private boolean isVolatilePtr = false;
	
	public PointerTypeEntry() { }
	
	public PointerTypeEntry(TypeEntryWithAttributes e) {
		baseTypeEntry = e;
	}
	
	public TypeEntryWithAttributes getBaseTypeEntry() {
		return baseTypeEntry;
	}

	public void setBaseTypeEntry(TypeEntryWithAttributes bt){
		baseTypeEntry = bt;
	}
	
	public int getDeclaratorChainElementType(){
		return POINTER;
	}
	
	public boolean isConstPtr() {
		return isConstPtr;
	}

	public void setConstPtr(boolean isConstPtr) {
		this.isConstPtr = isConstPtr;
	}

	public boolean isVolatilePtr() {
		return isVolatilePtr;
	}

	public void setVolatilePtr(boolean isVolatilePtr) {
		this.isVolatilePtr = isVolatilePtr;
	}
	
	public String setNextElementInDeclaratorChain(DeclaratorChainElement dce){
		if(baseTypeEntry == null){
			TypeEntryWithAttributes teWithAttrs = new TypeEntryWithAttributes();
			teWithAttrs.setBasicType((BasicType)dce);
			baseTypeEntry = teWithAttrs;
			return ErrorHandler.OK;
		}
		else{
			TypeEntryWithAttributes newTeWithAttrs = new TypeEntryWithAttributes();
			newTeWithAttrs.setBasicType((BasicType)dce);
			return TypeEntryWithAttributes.updateBasicType(baseTypeEntry, newTeWithAttrs);
		}
	}
	
	public String toString(){
		String str = "";
		if(isConstPtr) str += " const";
		if(isVolatilePtr) str += " volatile";
		
		str += " pointer to";
		str += baseTypeEntry.toString();
		return str;
		
	}	
}

/*
class NameTypeEntry extends BasicType {
	public Symbol name;
	private BasicType binding;
    
	public NameTypeEntry(Symbol n) {
    	name=n;
    }
	
	public boolean isLoop() 
	{
		BasicType b = binding; 
	    boolean any;
	    binding=null;
	    if (b==null) any=true;
	    else if (b instanceof NameTypeEntry)
	    	any=((NameTypeEntry)b).isLoop();
	    else any=false;
	    
	    binding=b;
	    return any;
	 }
	     
	 public BasicType getBinding() {
		 return binding;
	 }

	 public boolean coerceTo(BasicType t){
		 // return this.actual().coerceTo(t);
		 // TODO - implement correctly
		 return false;
	 }
	 
	 public void bind(BasicType t) {
		 binding = t;
	 }
}*/


class VariableEntry extends BasicType implements VariableOrFunctionEntry {
	private TypeEntryWithAttributes type;
	private int localVariableNum;
	private CFunctionDef varLevel;
	private int paramNum;
	private boolean isParam;
	private boolean inRegister;
	private Temp registerTemp;
	private boolean onHeap;
	private SourceLocation sourceLocation;
	private String name;
	private int offSetFromFP;   // If this variable escapes into memory (registerTemp will be null then)
	private Value value;
	
	public Value getValue() {
		return value;
	}

	public void setValue(Value value) {
		this.value = value;
	}

	public VariableEntry(){}
	
	/** Used for parameters of the function that are stored in positive offsets from $FP 
	 * OR
	 * for local variables
	 * @param t
	 * @param offSet
	 * @param level
	 */
	public VariableEntry(TypeEntryWithAttributes t, SourceLocation location, boolean isParam, int num, CFunctionDef level) {
		type = t;
		varLevel = level;
		this.isParam = isParam;
		if(isParam)
			paramNum = num;
		else
			localVariableNum = num;
		inRegister = false;
		this.onHeap = false;
		this.sourceLocation = location;
	}
	
	public VariableEntry(TypeEntryWithAttributes t,  SourceLocation location, Temp temp, CFunctionDef level) {
		inRegister = true;
		isParam = false;
		type = t;
		varLevel = level;
		registerTemp = temp;
		this.onHeap = false;
		this.sourceLocation = location;
	}
	
	public VariableEntry(TypeEntryWithAttributes t,  SourceLocation location, CFunctionDef level) {
		inRegister = true;
		isParam = false;
		type = t;
		varLevel = level;
		registerTemp = null;
		this.onHeap = true;
		this.sourceLocation = location;
	}
	
	public int getCategory(){return VariableOrFunctionEntry.VARIABLE;}
	
	public TypeEntryWithAttributes getType() { return type; }
	public void setType(TypeEntryWithAttributes teWithAttrs) { type = teWithAttrs; }
	
	public int getLocalVariableNum() { return localVariableNum; }
	
	public CFunctionDef getLevel() { return varLevel; }
	
	public void setParam(boolean isParam) {
		this.isParam = isParam;
	}

	public boolean isParamVar() { return isParam; }
	
	public void setParamNum(int paramNum) {
		this.paramNum = paramNum;
	}

	public int getParamNum() { return paramNum; }

	public boolean isInRegister() { return inRegister; }
	
	public Temp getRegisterTemp() { return registerTemp; }
	
	public void setRegisterTemp(Temp temp)  { registerTemp = temp; }

	public boolean isOnHeap() { return onHeap; }

	public SourceLocation getSourceLocation() { return sourceLocation; }

	public void setSourceLocation(SourceLocation sourceLocation) {this.sourceLocation = sourceLocation; }
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
	
	public String getDescription(){
		return name + " :" + type.toString();
	}

	public int getOffSetFromFP() {
		return offSetFromFP;
	}

	public void setOffSetFromFP(int offSetFromFP) {
		this.offSetFromFP = offSetFromFP;
	}
	
}

class FunctionEntry extends BasicType implements VariableOrFunctionEntry, DeclaratorChainElement {
	private Vector<VariableEntry> formals;          // The formal parameters to the function
	private TypeEntryWithAttributes result;                 // The return type of the function
	private CFunctionDef level;  // The activation frame of the function
	private String name;
	private SourceLocation sourceLocation;
	private boolean endsWithEllipses;
	
	public FunctionEntry(Vector<VariableEntry> formals, TypeEntryWithAttributes result,
			SourceLocation sourceLocation, CFunctionDef level) {
		this.formals = formals;
		this.result = result;
		this.level = level;
		this.sourceLocation = sourceLocation;
	}
	
	public FunctionEntry(){}
	
	public int getCategory(){return VariableOrFunctionEntry.FUNCTION;}
	
	public Vector<VariableEntry> getFormals() { return formals; }
	
	public TypeEntryWithAttributes getReturnType() { return result; }
	
	public CFunctionDef getLevel() { return level; }
	
	public SourceLocation getSourceLocation() { return sourceLocation; }

	public void setSourceLocation(SourceLocation sourceLocation) {this.sourceLocation = sourceLocation; }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setFormals(Vector<VariableEntry> formals){
		this.formals = formals;
	}
	
	public void setReturnType(TypeEntryWithAttributes te){
		this.result = te;
	}
	
	public int getDeclaratorChainElementType(){
		return DECL_CHAIN_FUNCTION;
	}
	
	public String setNextElementInDeclaratorChain(DeclaratorChainElement dce){
		if(result == null){
			int declChainElementType = dce.getDeclaratorChainElementType();
			if(declChainElementType == DeclaratorChainElement.DECL_CHAIN_FUNCTION)
				return ErrorHandler.E_DECL_FUNC_RETURNING_FUNC;
			else if(declChainElementType == DeclaratorChainElement.ARRAY)
				return ErrorHandler.E_DECL_FUNC_RETURNING_ARRAY;
			
			result = new TypeEntryWithAttributes(); 
			result.setBasicType((BasicType)dce);
			return ErrorHandler.OK;
		}
		else{
			TypeEntryWithAttributes newTeWithAttrs = new TypeEntryWithAttributes();
			newTeWithAttrs.setBasicType((BasicType)dce);
			return TypeEntryWithAttributes.updateBasicType(result, newTeWithAttrs);
		}			
	}
	
	public BasicType getNextElementInDeclaratorChain(){ 
	//	return result.getBasicType();
		return this;
	}
	
	public String getDescription(){
		if(name == null)
			return " function returning" + result.toString();
		else
			return name + " :" + " function returning" + result.toString();
	}

	public boolean isEndsWithEllipses() {
		return endsWithEllipses;
	}

	public void setEndsWithEllipses(boolean endsWithEllipses) {
		this.endsWithEllipses = endsWithEllipses;
	}
}
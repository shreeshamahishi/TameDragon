package org.tamedragon.common.llvmir.types;

import java.util.ArrayList;


public class StructType extends CompositeType {

	private boolean isPacked;
	private boolean isUnion;
	private boolean isSelfReferencial;

	// The name of the structure. It will be null if this is a literal structure. 
	private String name;   

	public StructType(CompilationContext compilationContext, boolean isPacked, String name, boolean isUnion, Type ... contained){
		super(compilationContext, TypeID.STRUCT_ID);
		
		if(name != null)
			this.name = name;
		
		this.isPacked = isPacked;
		if(containedTypes == null)
			containedTypes = new ArrayList<Type>();

		if(contained != null)
			for(Type contType : contained)
				containedTypes.add(contType);
		this.isUnion = isUnion;
	}

	public void addContainedType(Type containedType) {
		containedTypes.add(containedType);
	}

	public void addContainedTypeAtIndex(Type containedType, int index) {
		containedTypes.remove(index);
		containedTypes.add(index, containedType);
	}

	public int getElementSize(){
		if(containedTypes == null)
			return 0;

		return containedTypes.size();
	}

	/*public Type getTypeAt(int index){
		if(!isValidIndex(index))
			return null; 

		return containedTypes.get(index);
	}*/

	public boolean getIsPacked(){
		return isPacked;
	}

	public boolean hasBody(){
		if(containedTypes == null || containedTypes.size() == 0)
			return false;

		return true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isLiteral(){
		if(name == null)
			return true;

		return false;
	}

	public boolean isOpaque(){
		if(!hasBody() && name != null)
			return true;

		return false;
	}

	public boolean isUnion() {
		return isUnion;
	}

	/**
	 * Utility function that returns true if the type that is passed
	 * can be the type of an element in a structure. 
	 */
	public static boolean isValidElementType(Type elementType) {
		return !elementType.isVoidType() && !elementType.isLabelType() &&
				!elementType.isMetadataType() && !elementType.isFunctionType();
	}

	public boolean isSelfReferencial() {
		return isSelfReferencial;
	}

	public void setSelfReferencial(boolean isSelfReferencial) {
		this.isSelfReferencial = isSelfReferencial;
	}

	public String toString(){
		String startToken = "{";
		String endToken = "}";
		if(isPacked){
			startToken = "<{";
			endToken = "}>";
		}

		if(name != null){
			if(!hasBody()){
				return "opaque";
			}
			
			return "%" +name;
		}

		String description = startToken;
		if(hasBody()){
			int count = 0;
			for(Type containedType : containedTypes){
				String delimiter = count < containedTypes.size() -1 ? ", " : "";
				description += containedType.toString() + delimiter;
				count++;
			}
		}

		description += endToken;

		return description;
	}
	
	public String bodyDescription(){
		String bodyDescription = "{";
		
		if(!hasBody()){
			return "";
		}
		
		int count = 0;
		for(Type containedType : containedTypes){
			String delimiter = count < containedTypes.size() -1 ? ", " : "";
			bodyDescription += containedType.toString() + delimiter;
			count++;
		}
		
		return bodyDescription;
	}
	
	// TODO : Move this to TargetData ?

	public long getElementOffset(int index) {
		long offset = 0;
		int count = 0;
		if(!isUnion){
			for(Type containedType : containedTypes){
				if(count == index){
					break;
				}
				
				if(containedType.isPrimitiveType()){
				offset += containedType.getPrimitiveSizeInBits();
				}
				else if(containedType.isPointerType()){
					PointerType ptrType = (PointerType) containedType;
					offset += ptrType.getPointerSize();
				}
				else if(containedType.isStructType()){
					StructType strType = (StructType) containedType;
					offset += strType.getSize();
				}
				count++;
			}
		}
		else{
			// TODO: Union
		}
		return offset;
	}
	
	// TODO : Move this to TargetData ?
	public long getSize(){
		long size = 0;
		if(!isUnion){
			for(Type containedType : containedTypes){
				if(containedType.isPrimitiveType()){
				size += containedType.getPrimitiveSizeInBits();
				}
				else if(containedType.isPointerType()){
					PointerType ptrType = (PointerType) containedType;
					size += ptrType.getPointerSize();
				}
				else if(containedType.isStructType()){
					StructType strType = (StructType) containedType;
					size += strType.getSize();
				}
				else if(containedType.isArrayType()){
					ArrayType arrType = (ArrayType) containedType;
					size += arrType.getSize();
				}
			}
		}
		else{
			// TODO: Union
		}
		return size;
	}
}

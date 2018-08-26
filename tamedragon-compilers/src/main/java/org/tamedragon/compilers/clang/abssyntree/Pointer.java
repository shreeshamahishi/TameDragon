package org.tamedragon.compilers.clang.abssyntree;

public class Pointer extends Absyn {

	private TypeQualifierList typeQualifierList;
	private Pointer pointer;
	
	public Pointer(){
		
	}
	
	public Pointer(Pointer p){
		pointer = p;
	}
	
	public Pointer(TypeQualifierList typeQualifierList,Pointer pointer)
	{
		this.typeQualifierList = typeQualifierList;
		this.pointer = pointer;
	}

	public Pointer getPointer() {
		return pointer;
	}

	public void setPointer(Pointer pointer) {
		this.pointer = pointer;
	}

	public TypeQualifierList getTypeQualifierList() {
		return typeQualifierList;
	}

	public void setTypeQualifierList(TypeQualifierList typeQualifierList) {
		this.typeQualifierList = typeQualifierList;
	}
	
	public String toString(){
		String main = " pointer to";
		String str = "";
		if(typeQualifierList != null)
			str += typeQualifierList.toString() + main;
		else
			str += main;
		
		if(pointer != null){
			str += " pointer to";
		}
		
		
		return str;
	}
	
}

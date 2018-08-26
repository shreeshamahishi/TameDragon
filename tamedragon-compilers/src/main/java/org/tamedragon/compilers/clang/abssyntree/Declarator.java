package org.tamedragon.compilers.clang.abssyntree;

public class Declarator extends Absyn {

	private DirectDeclarator directDeclarator;
	private Pointer pointer;
	
	public Declarator(DirectDeclarator directDeclarator,
			Pointer pointer) {
		this.directDeclarator = directDeclarator;
		this.pointer = pointer;
	}

	public Declarator(){}
	
	public DirectDeclarator getDirectDeclarator() {
		return directDeclarator;
	}

	public void setDirectDeclarator(DirectDeclarator directDeclarator) {
		this.directDeclarator = directDeclarator;
	}

	public Pointer getPointer() {
		return pointer;
	}

	public void setPointer(Pointer pointer) {
		this.pointer = pointer;
	}
	
	public String toString(){
		String str = "";
		
		if(directDeclarator != null)
			str += directDeclarator.toString();
		
		if(pointer != null)
			str += pointer.toString();		
		
		return str;
	}
}

package org.tamedragon.compilers.clang.abssyntree;

public class ExplicitCasting extends Absyn {
	private TypeSpecifier typeSpecifier;
	private Pointer pointer;
	
	public TypeSpecifier getTypeSpecifier() {
		return typeSpecifier;
	}
	public void setTypeSpecifier(TypeSpecifier typeSpecifier) {
		this.typeSpecifier = typeSpecifier;
	}
	public Pointer getPointer() {
		return pointer;
	}
	public void setPointer(Pointer pointer) {
		this.pointer = pointer;
	}
	
	@Override
	public String toString() {
		return typeSpecifier.toString() + " " + pointer.toString();
	}
}

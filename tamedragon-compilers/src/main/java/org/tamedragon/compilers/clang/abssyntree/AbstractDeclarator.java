package org.tamedragon.compilers.clang.abssyntree;

public class AbstractDeclarator extends Absyn {

	private Pointer pointer;
	private DirectAbstractDeclarator directAbstractDeclarator;
	
	public AbstractDeclarator(Pointer pointer, DirectAbstractDeclarator directAbstractDeclarator)
	{
		this.pointer = pointer;
		this.directAbstractDeclarator = directAbstractDeclarator;
	}

	public Pointer getPointer() {
		return pointer;
	}

	public void setPointer(Pointer pointer) {
		this.pointer = pointer;
	}

	public DirectAbstractDeclarator getDirectAbstractDeclarator() {
		return directAbstractDeclarator;
	}

	public void setDirectAbstractDeclarator(
			DirectAbstractDeclarator directAbstractDeclarator) {
		this.directAbstractDeclarator = directAbstractDeclarator;
	}
	
}

package org.tamedragon.compilers.clang.abssyntree;

public class StructOrUnionSpecifier extends TypeSpecifier{

	private StructOrUnion structOrUnion;
	private String name;
	private StructDeclarationList structDecList;
	
	public StructOrUnionSpecifier(){}
	
	public StructOrUnionSpecifier(StructOrUnion structOrUnion, String name,
			StructDeclarationList structDecList)
	{
		super(TypeSpecifier.STRUCT_OR_UNION_SPEC);
		
		this.structOrUnion = structOrUnion;
		this.name = name;
		this.structDecList = structDecList;
		
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public StructDeclarationList getStructDecList() {
		return structDecList;
	}

	public void setStructDecList(StructDeclarationList structDecList) {
		this.structDecList = structDecList;
	}

	public StructOrUnion getStructOrUnion() {
		return structOrUnion;
	}

	public void setStructOrUnion(StructOrUnion structOrUnion) {
		this.structOrUnion = structOrUnion;
	}
}

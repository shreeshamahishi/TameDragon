package org.tamedragon.compilers.clang.abssyntree;

public class EnumSpecifier extends TypeSpecifier{

	private String name;
	private EnumList enumList;
	
	public EnumSpecifier(){}
	
	public EnumSpecifier(String name, EnumList enumList)
	{
		super(TypeSpecifier.ENUM_SPEC);
		this.name = name;
		this.enumList = enumList;
	}

	public EnumList getEnumList() {
		return enumList;
	}

	public void setEnumList(EnumList enumList) {
		this.enumList = enumList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

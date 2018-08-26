package org.tamedragon.common.llvmir.types;

public class NamedType {
	private Type type;
	private String name;
	
	public NamedType(Type type, String name){
		this.type = type;
		this.name = name;
	}
	
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof NamedType))
			return false;
		NamedType namedType = (NamedType)obj;
		Type type1 = getType();
		String name1 = getName();
		Type type2 = namedType.getType();
		String name2 = namedType.getName();
		
		if(type1 != type2)
			return false;
		if(!name1.equals(name2))
			return false;
		
		return true;
	}
	
	@Override
	public int hashCode() {
		return getType().hashCode() + getName().hashCode();
	}
}

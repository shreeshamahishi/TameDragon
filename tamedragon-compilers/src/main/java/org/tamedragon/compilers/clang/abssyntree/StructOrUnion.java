package org.tamedragon.compilers.clang.abssyntree;

public class StructOrUnion extends Absyn {
	public static final int STRUCT = 1;
	public static final int UNION = 2;
	
	private int type;
	
	public StructOrUnion(int type)
	{
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}

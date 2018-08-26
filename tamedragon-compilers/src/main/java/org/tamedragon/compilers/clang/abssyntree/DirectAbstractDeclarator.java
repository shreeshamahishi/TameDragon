package org.tamedragon.compilers.clang.abssyntree;

public class DirectAbstractDeclarator extends Absyn {
	private AbstractDeclarator abstractDeclarator;
	private DirectAbstractDeclarator directAbstractDeclarator;
	private ConstExpr arraySizeExpr;
	private ParamTypeList funcArgs;
	
	public static final int ABS_DECL = 1;
	public static final int ARRAY = 2;
	public static final int EMPTY_ARRAY = 3;
	public static final int FUNC = 4;
	public static final int NO_ARGS_FUNC = 5;
	
	private int type;
	
	public DirectAbstractDeclarator(){}
	
	public DirectAbstractDeclarator(AbstractDeclarator abstractDeclarator,
			DirectAbstractDeclarator directAbstractDeclarator,
			ConstExpr arraySizeExpr, ParamTypeList funcArgs, int type)
	{
		
		this.type  = type;
		
		this.abstractDeclarator = abstractDeclarator;
		this.directAbstractDeclarator = directAbstractDeclarator;
		this.arraySizeExpr = arraySizeExpr;
		this.funcArgs = funcArgs;
	}

	public AbstractDeclarator getAbstractDeclarator() {
		return abstractDeclarator;
	}

	public void setAbstractDeclarator(AbstractDeclarator abstractDeclarator) {
		this.abstractDeclarator = abstractDeclarator;
	}

	public ConstExpr getArraySizeExpr() {
		return arraySizeExpr;
	}

	public void setArraySizeExpr(ConstExpr arraySizeExpr) {
		this.arraySizeExpr = arraySizeExpr;
	}

	public DirectAbstractDeclarator getDirectAbstractDeclarator() {
		return directAbstractDeclarator;
	}

	public void setDirectAbstractDeclarator(
			DirectAbstractDeclarator directAbstractDeclarator) {
		this.directAbstractDeclarator = directAbstractDeclarator;
	}

	public ParamTypeList getFuncArgs() {
		return funcArgs;
	}

	public void setFuncArgs(ParamTypeList funcArgs) {
		this.funcArgs = funcArgs;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}

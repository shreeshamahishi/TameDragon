package org.tamedragon.compilers.clang.abssyntree;

public class TypeOf extends TypeSpecifier {
	private TypeName typeName;
	private RootExpr expression;
	
	public TypeOf(){}
	
	public TypeOf(int line, int pos){
		this.type = TYPEOF;
		setLineNum(line);
		setPos(pos);
	}

	public TypeName getTypeName() {
		return typeName;
	}

	public void setTypeName(TypeName typeName) {
		this.typeName = typeName;
	}

	public RootExpr getExpression() {
		return expression;
	}

	public void setExpression(RootExpr expression) {
		this.expression = expression;
	}
}

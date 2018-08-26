package org.tamedragon.compilers.clang.abssyntree;

public class CastExpr extends Absyn implements Expr{
	
	private UnaryExpr unaryExpr;
	private TypeName typeName;
	private CastExpr castExpr;
	
	public CastExpr(){}
	
	public CastExpr(UnaryExpr ue){
		this.unaryExpr = ue;
	}
	
	public CastExpr(TypeName tn, CastExpr castExpr){
		this.typeName = tn;
		this.castExpr = castExpr;
	}
	
	public CastExpr getCastExpr() {
		return castExpr;
	}
	public void setCastExpr(CastExpr castExpr) {
		this.castExpr = castExpr;
	}
	public TypeName getTypeName() {
		return typeName;
	}
	public void setTypeName(TypeName typeName) {
		this.typeName = typeName;
	}
	public UnaryExpr getUnaryExpr() {
		return unaryExpr;
	}
	public void setUnaryExpr(UnaryExpr unaryExpr) {
		this.unaryExpr = unaryExpr;
	}
	
	public int getExprType(){
		return Expr.CAST;
	}
	
	public String toString(){
		String str = "";
		
		if(castExpr != null){
			str +=  "(" + typeName.toString() + ")" + castExpr.toString();
		}
		else{
			return unaryExpr.toString();
		}
		return str;
	}	
	
	
}

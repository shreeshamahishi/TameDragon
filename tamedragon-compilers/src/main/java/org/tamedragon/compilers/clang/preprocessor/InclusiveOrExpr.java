package org.tamedragon.compilers.clang.preprocessor;

public class InclusiveOrExpr extends Absyn {
	
	private ExclusiveOrExpr exclusiveOrExpr;
	private InclusiveOrExpr inclusiveOrExpr;
	
	public InclusiveOrExpr(){}
	
	public InclusiveOrExpr(InclusiveOrExpr ioe, ExclusiveOrExpr eoe){
		this.exclusiveOrExpr = eoe;
		this.inclusiveOrExpr = ioe;
		if(ioe != null){
			setLineNum(ioe.getLineNum());
			setPos(ioe.getPos());
		}
	}
	
	public ExclusiveOrExpr getExclusiveOrExpr() {
		return exclusiveOrExpr;
	}

	public void setExclusiveOrExpr(ExclusiveOrExpr exclusiveOrExpr) {
		this.exclusiveOrExpr = exclusiveOrExpr;
	}

	public InclusiveOrExpr getInclusiveOrExpr() {
		return inclusiveOrExpr;
	}

	public void setInclusiveOrExpr(InclusiveOrExpr inclusiveOrExpr) {
		this.inclusiveOrExpr = inclusiveOrExpr;
	}
	
	public String toString(){
		String str = "";
		if(inclusiveOrExpr != null){
			str += inclusiveOrExpr.toString() + " | " + exclusiveOrExpr.toString();
		}
		else{
			return exclusiveOrExpr.toString();
		}
		return str;
	}
	
	public int evaluate(){
		int val = exclusiveOrExpr.evaluate();
		
		if(inclusiveOrExpr != null){
			int ioeVal = inclusiveOrExpr.evaluate();
			
			return val | ioeVal; 	
		}		
		
		return val;
	}

}

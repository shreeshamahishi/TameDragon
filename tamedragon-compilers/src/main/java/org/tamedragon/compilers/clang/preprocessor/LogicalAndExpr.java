package org.tamedragon.compilers.clang.preprocessor;

public class LogicalAndExpr extends Absyn {
	
	private InclusiveOrExpr inclusiveOrExpr;
	private LogicalAndExpr logicalAndExpr;
	
	public LogicalAndExpr(){}
	
	public LogicalAndExpr(LogicalAndExpr lae, InclusiveOrExpr ioe){
		this.inclusiveOrExpr = ioe;
		this.logicalAndExpr = lae;
		if(lae != null){
			setLineNum(lae.getLineNum());
			setPos(lae.getPos());
		}
	}
	
	public InclusiveOrExpr getInclusiveOrExpr() {
		return inclusiveOrExpr;
	}

	public void setInclusiveOrExpr(InclusiveOrExpr inclusiveOrExpr) {
		this.inclusiveOrExpr = inclusiveOrExpr;
	}

	public LogicalAndExpr getLogicalAndExpr() {
		return logicalAndExpr;
	}

	public void setLogicalAndExpr(LogicalAndExpr logicalAndExpr) {
		this.logicalAndExpr = logicalAndExpr;
	}

	public String toString(){
		String str = "";
		if(logicalAndExpr != null){
			str += logicalAndExpr.toString() + " && " + inclusiveOrExpr.toString();
		}
		else{
			return inclusiveOrExpr.toString();
		}
		return str;
	}
	
	public int evaluate(){
		int val = inclusiveOrExpr.evaluate();
		
		if(logicalAndExpr != null){
			int loeVal = logicalAndExpr.evaluate();
			if(val == 0 || loeVal == 0)
				return 0;
			else{				
				return val > loeVal? val : loeVal;
			}			
		}		
		
		return val;
	}
	
}

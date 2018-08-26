package org.tamedragon.compilers.clang.preprocessor;

public class LogicalOrExpr extends Absyn {
	private LogicalAndExpr logicalAndExpr;
	private LogicalOrExpr logicalOrExpr;

	public LogicalOrExpr(){}
	
	public LogicalOrExpr(LogicalOrExpr loe, LogicalAndExpr lae){
		this.logicalAndExpr = lae;
		this.logicalOrExpr = loe;
		if(loe != null){
			setLineNum(loe.getLineNum());
			setPos(loe.getPos());
		}
	}
	
	public LogicalAndExpr getLogicalAndExpr() {
		return logicalAndExpr;
	}

	public void setLogicalAndExpr(LogicalAndExpr logicalAndExpr) {
		this.logicalAndExpr = logicalAndExpr;
	}

	public LogicalOrExpr getLogicalOrExpr() {
		return logicalOrExpr;
	}

	public void setLogicalOrExpr(LogicalOrExpr logicalOrExpr) {
		this.logicalOrExpr = logicalOrExpr;
	}

	public String toString(){
		String str = "";
		if(logicalOrExpr != null){
			str += logicalOrExpr.toString() + " || " + logicalAndExpr.toString();
		}
		else{
			return logicalAndExpr.toString();
		}
		return str;
	}
	
	public int evaluate(){
		int val = logicalAndExpr.evaluate();
		
		if(logicalOrExpr != null){
			int loeVal = logicalOrExpr.evaluate();
			if(val == 0 && loeVal == 0)
				return 0;
			else{
				if(val == 0)
					return loeVal;
				else if(loeVal == 0)
					return val;
				else
					return val > loeVal? val : loeVal;
			}
			
		}		
		
		return val;
	}	
}

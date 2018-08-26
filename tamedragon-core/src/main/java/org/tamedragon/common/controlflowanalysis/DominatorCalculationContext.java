package org.tamedragon.common.controlflowanalysis;

import org.tamedragon.common.DominatorTree;
import org.tamedragon.common.SpanningTree;
import org.tamedragon.common.llvmir.types.Function;

public class DominatorCalculationContext {
	
	private DominatorTreeStrategy dominatorTreeStrategy;
	private Function function;
	private boolean onReverseCFG;
	
	public DominatorCalculationContext(Function function){
		this.function = function;
		onReverseCFG = false;
		setLengauerTarjan(null);
	}
	
	public void setLengauerTarjan(SpanningTree spanningTree){
		dominatorTreeStrategy = new LengauerTarjan(spanningTree);
	}
	
	public void setIterativeMethod(){
		dominatorTreeStrategy = new SimpleDominatorTreeConstructor();
	}
	
	public DominatorTree getDominatorTree(){
		return (DominatorTree)dominatorTreeStrategy.getDominatorTree(function, onReverseCFG);
	}

	public void setOnReverseCFG(boolean onReverseCFG) {
		this.onReverseCFG = onReverseCFG;
	}
}

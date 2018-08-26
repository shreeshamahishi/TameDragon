package org.tamedragon.common.controlflowanalysis;

import org.tamedragon.common.DominatorTree;
import org.tamedragon.common.llvmir.types.Function;

public abstract class DominatorTreeStrategy {
	
	public abstract DominatorTree getDominatorTree(Function function, boolean onReverseCFG);

}


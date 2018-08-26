package org.tamedragon.compilers.clang.semantics;

import org.tamedragon.common.Label;

public class IterationOrSelectionLabels {
	
	public static final int SWITCH = 0;
	public static final int ITERATION = 1;
	public static final int FOR_ITERATION = 2;
	
	private int type;
	private Label startLabel; 
	private Label endLabel;
	private Label incrLabel;
	
	public IterationOrSelectionLabels(int type, Label start, Label end, Label incrLabel){
		this.type = type;
		this.startLabel = start;
		this.endLabel = end;
		this.incrLabel = incrLabel;
	}

	public Label getEndLabel() {
		return endLabel;
	}

	public Label getStartLabel() {
		return startLabel;
	}

	public Label getIncrLabel() {
		return incrLabel;
	}

	public int getType() {
		return type;
	}	
}

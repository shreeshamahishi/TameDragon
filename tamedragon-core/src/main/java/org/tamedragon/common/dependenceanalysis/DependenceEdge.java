package org.tamedragon.common.dependenceanalysis;

import org.jgraph.graph.DefaultEdge;

public class DependenceEdge extends DefaultEdge{
	private int latencyLabel;
	
	public DependenceEdge(int latencyLabel){
		this.latencyLabel = latencyLabel;
	}

	public int getLatencyLabel() {
		return latencyLabel;
	}

	public void setLatencyLabel(int latencyLabel) {
		this.latencyLabel = latencyLabel;
	}
}

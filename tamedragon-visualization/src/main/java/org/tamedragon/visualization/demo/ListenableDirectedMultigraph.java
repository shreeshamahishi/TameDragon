package org.tamedragon.visualization.demo;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultListenableGraph;
import org.jgrapht.graph.DirectedMultigraph;

import org.tamedragon.common.llvmir.instructions.CFG;
import org.tamedragon.common.llvmir.types.BasicBlock;

public class ListenableDirectedMultigraph<V, E> extends DefaultListenableGraph<V, E>
{
	private static final long serialVersionUID = 1L;

	ListenableDirectedMultigraph(Class<E> edgeClass)
	{
		super(new DirectedMultigraph<V, E>(edgeClass));
	}
}
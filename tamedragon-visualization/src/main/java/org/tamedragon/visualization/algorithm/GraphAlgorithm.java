/*
	File: GraphAlgorithm.java
	5/22/96    Larry Barowski
 */

package org.tamedragon.visualization.algorithm;

import org.tamedragon.visualization.components.DInterferenceGraph;
import org.tamedragon.visualization.components.DomGraph;

/**
 * This interface is used to add algorithms to the graph tool. All that is
 * required is that the algorithm object class has the function compute, and
 * that it implements GraphAlgorithm.
 * </p>
 * Here is the <a href="../algorithm/GraphAlgorithm.java">source</a>.
 *
 * @author Larry Barowski
 */
public interface GraphAlgorithm {

	/**
	 * Apply the algorithm to graph. The return value should be null if
	 * successful, and an error message if unsuccessful.
	 *
	 * @param update
	 *            an object that allows the display to be updated from within
	 *            the algorithm
	 * @see GraphUpdate
	 */
	abstract public String compute(DInterferenceGraph graph, GraphUpdate update);

	abstract public String compute(DomGraph graph, GraphUpdate update);
}

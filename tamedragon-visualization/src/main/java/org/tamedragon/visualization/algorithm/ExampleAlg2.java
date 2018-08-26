/*
 *File: ExampleAlg2.java
 *
 * Date      Author
 * 10/9/96   Larry Barowski
 *
 */

package org.tamedragon.visualization.algorithm;

import java.util.Random;

import org.tamedragon.visualization.components.*;
import org.tamedragon.visualization.util.DPoint;


/**
 * This example randomly moves the nodes. It does this 100 times, and updates
 * the display and waits 5 milliseconds in between.
 * </p>
 * Here is the <a href="../examplealg/ExampleAlg2.java">source</a>.
 *
 * @author Larry Barowski
 */

public class ExampleAlg2 //implements GraphAlgorithm {
{

//	public String compute(DInterferenceGraph graph, GraphUpdate update) {
//		DNode tmpnode;
//		DPoint pos;
//		Random random = new Random();
//		int i;
//		double xshift, yshift;
//		int maxTime = 100 * graph.getNodeCount()/10;
//
//		for (i = 0; i < maxTime; i++) {
//			for (tmpnode = graph.getStartNode(); tmpnode != null; tmpnode = graph
//					.nextNode(tmpnode)) {
//				pos = tmpnode.getPosition();
//
//				xshift = (random.nextDouble() - .5) * 10.0;
//				yshift = (random.nextDouble() - .5) * 10.0;
//
//				tmpnode.setPosition(pos.x + xshift, pos.y + yshift);
//
//			}
//			/*
//			 * -- Netscape does not allow wait(). try { wait(5); }
//			 * catch(Exception e) ;
//			 */
//		}
//		update.update_forInterferenceGraph(false);
//		return null;
//	}

	public String compute(DomGraph graph, GraphUpdate update) {
		return null;
	}
}

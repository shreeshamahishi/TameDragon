/**
 *
 */
package org.tamedragon.visualization.components;

import java.util.HashMap;
import java.util.Vector;

import org.tamedragon.common.*;

/**
 * Component for the Display purpose. It wraps InterferenceGraph object.
 *
 * @author ramarao
 *
 */
public class DInterferenceGraph {

//	private InterferenceGraph ig;
//	private DNode startNode;
//	HashMap<Integer, DNode> dnodesMap;
//	HashMap<Node, DNode> nodeVsDNode;
//	Vector<DNode> dnodes;
//	Vector<CFEdge> edges;
//	Vector<DEdge> dedges;
//
//	public DInterferenceGraph(InterferenceGraph ig) {
//		this.ig = ig;
//
//		dnodesMap = new HashMap<Integer, DNode>();
//		nodeVsDNode = new HashMap<Node, DNode>();
//		Vector<Node> nodes = ig.nodes();
//		Node node;
//		DNode dnode;
//		for (int i = 0; i < nodes.size(); i++) {
//			node = nodes.get(i);
//			//if (ig.getAdjacentNodeList(node) != null) {				
//				dnode = new DNode(node, ig.getTemp(node), nodeVsDNode.size());
//				nodeVsDNode.put(node, dnode);
//				dnodesMap.put(dnodesMap.size(), dnode);
//			//}
//		}
//	}
//
//	// public int getNumTemps() {
//	// return ig.getNumTemps();
//	// }
//
//	public Vector<DNode> nodes() {
//		if (dnodes == null)
//			dnodes = new Vector<DNode>(nodeVsDNode.values());
//		return dnodes;
//	}
//
//	public DNode getStartNode() {
//		if (startNode == null)
//			startNode = dnodesMap.get(new Integer(0));
//		return startNode;
//	}
//
//	public DNode nextNode(DNode v) {
//		return dnodesMap.get(getIndex(v) + 1);
//	}
//
//	protected int getIndex(DNode node) {
//		if (dnodesMap == null)
//			return -1;
//
//		for (int i = 0; i < dnodesMap.size(); i++) {
//			if (dnodesMap.get(i).equals(node))
//				return i;
//		}
//		return -1;
//	}
//
//	public boolean hasChild(DNode v, DNode u) {
//		return ig.hasChild(v.getNode(), u.getNode());
//	}
//
//	public int getNodeCount() {
//		return dnodesMap.size();
//	}
//
//	public DNode getNode(int index) {
//		if (dnodesMap == null)
//			return null;
//
//		return dnodesMap.get(index);
//	}
//
//	public int getNumEdges() {
//		return ig.getNumEdges();
//	}
//
//	public CFEdge getEdge(int num) {
//		return ig.getEdge(num);
//	}
//
//	public DEdge getDEdge(int num) {
//		if (dedges != null)
//			return dedges.get(num);
//		populateEdges();
//		return getDEdge(num);
//	}
//
//	private void populateEdges() {
//		dedges = new Vector<DEdge>();
//		CFEdge edge;
//		for (int i = 0; i < getNumEdges(); i++) {
//			edge = getEdge(i);
//			dedges.add(new DEdge(nodeVsDNode.get(edge.getSrc()), nodeVsDNode
//					.get(edge.getDestination()), edge));
//		}
//	}
}

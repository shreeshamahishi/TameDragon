package org.tamedragon.visualization.components;

import java.util.HashMap;
import java.util.Vector;
public class DomGraph {

	private Vector<DomNode> vertices;          // List of graph nodes
	private Vector<DOMEdge> edges;
	private HashMap<DomNode, Integer> nodesVsOrderNumber;
	private int nodeCount;

	private DomNode startNode;

	public DomNode getStartNode() {
		return startNode;
	}

	public void setStartNode(DomNode startNode) {
		this.startNode = startNode;
		this.startNode.setPosition(10, 10, 10);
	}

	public DomGraph()
	{
		vertices = new Vector<DomNode>();
		edges = new Vector<DOMEdge>();

		nodesVsOrderNumber = new HashMap<DomNode, Integer>();
		nodeCount = 0;
	}

	public void initNodes(int numNodes){
		vertices = new Vector<DomNode>(numNodes);
		for(int i = 0; i < numNodes; i++){
			vertices.add(null);
		}
	}

	public Vector getOutgoingEdges(DomNode node){
		return node.getOutgoingEdges();
	}

	public int getNumNodes(){
		return vertices.size();
	}

	public int getNumEdges(){
		return edges.size();
	}

	public DomNode getNode(int index){
		return (DomNode) vertices.elementAt(index);
	}

	public DomNode nextNode(DomNode currentNode)
	{
		for(int i = 0; i < getNumNodes(); i++)
		{
			if (getNode(i).equals(currentNode))
			{
				if (i < getNumNodes() - 1)
					return getNode(++i);
				return null;
			}
		}
		return null;
	}

	public DOMEdge getEdge(int index){
		return (DOMEdge) edges.elementAt(index);
	}

	public DomNode getOppositeNode(DomNode node, DOMEdge edge){
		return edge.getOppositeDomNode(node);
	}

	public void addNode(DomNode node){
		vertices.addElement(node);
		int nodeNum = vertices.size() - 1;
		node.setPositionInGraph(nodeNum);
	}

	public void addNodeAt(DomNode node, int index){
		vertices.setElementAt(node, index);
		node.setPositionInGraph(index);
	}

	public void addDirectedEdge(DOMEdge edge, DomNode src, DomNode dest) {
		edges.addElement(edge);
		edge.setDirected(true);
		int edgeNum = edges.size() -1;
		edge.setPositionInGraph(edgeNum);
		edge.setDestination(dest);
		edge.setSrc(src);

		src.addSuccessesor(dest);
		// Assign a number to the destination node
		if(nodeCount == 0){
			nodesVsOrderNumber.put(src, new Integer(nodeCount));
		}
		else{
			nodeCount++;
			nodesVsOrderNumber.put(dest, new Integer(nodeCount));
		}

		// Add the predecessor to the destination at the right index
		DomNode predsOfDest = dest.getPredecessor();
		dest.setPredecessor(src);
		src.addOutgoingEdge(edge);
	}

	public void display(){
		for(int i = 0; i < vertices.size(); i++) {
			DomNode node = (DomNode)vertices.elementAt(i);
			if(node == null) continue;
			System.out.print("Node " + node.getName() + " has edges to : ");
			System.out.println("edges----"+node.getName()+"----incoming edges--"+1+"--outgoing edges---"+node.getOutgoingEdges().size()+"--postion in graph--"+node.getPositionInGraph()+"-successors size--"+node.getSuccessors().size());
			Vector succs = node.getSuccessors();
			for(int j = 0; j < succs.size(); j++){
				DomNode suc = (DomNode) succs.elementAt(j);
				System.out.print(suc.getName() + ", ");
			}
			System.out.println("");
		}
	}

	public DomNode getNode(DomNode domNode){
		for(int i = 0; i < vertices.size(); i++) {
			DomNode node = (DomNode)vertices.elementAt(i);
			if(node == null) continue;
			else if(domNode.getName().equals(node.getName())){
				return domNode;
			}
		}
		System.out.println("not finded the node"+domNode.getName());
		return null;
	}

	public Vector<DomNode> getVertices() {
		return vertices;
	}

	public void setVertices(Vector<DomNode> vertices) {
		this.vertices = vertices;
	}
}

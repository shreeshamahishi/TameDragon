package org.tamedragon.visualization.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.Map.Entry;

import org.tamedragon.common.DominatorTree;
import org.tamedragon.common.DominatorTreeNode;
//import org.tamedragon.common.CFEdge;
//import org.tamedragon.common.Node;
import org.tamedragon.common.TreeNode;

public class DDominatorTree {
//	private DominatorTree dominatorTree;
//	//private DNode startNode;
//	//public Vector<DNode> dnodes;
//	public Vector<Node> dominatorNodeChilds;
//	DomNode dominatorTreeDisplayInfo;
//	ArrayList<DomNode> domNodeCollection = new ArrayList<DomNode>();
//	int positionInGraph = 1;
//	DomGraph domGraph ;
//	HashMap<DominatorTreeNode,Vector<DominatorTreeNode>> domTreeNodevsChildsNode =
//		new HashMap< DominatorTreeNode,Vector<DominatorTreeNode>>();
//
//
//	public DDominatorTree(DominatorTree dominatorTree){
//		this.dominatorTree = dominatorTree;
//		createMapOfchildsVsTreeNode();
//		domGraph =  new DomGraph();
//		constructDominatorTreeDisplayInfo();
//	}
//
//	public void constructDominatorTreeDisplayInfo(){
//
//		Set<Entry<DominatorTreeNode, Vector<DominatorTreeNode>>> entries = domTreeNodevsChildsNode.entrySet();
//		Iterator<Map.Entry<DominatorTreeNode, Vector<DominatorTreeNode>>> iter = entries.iterator();
//
//		while(iter.hasNext()){
//			Map.Entry<DominatorTreeNode, Vector<DominatorTreeNode>> entry = iter.next();
//			DomNode domNode = new DomNode(entry.getKey().getName());
//			Vector<DominatorTreeNode> childDomNodes = entry.getValue();
//			System.out.println("child nodes size for ----"+domNode.getName()+"----"+childDomNodes.size());
//			Vector<DOMEdge> children = new Vector<DOMEdge>();
//			Vector<DomNode> successors = new Vector<DomNode>();
//			if(!hasDomNode(domNode, domNodeCollection)){
//				System.out.println("one parent node created"+domNode.getName());
//				domNodeCollection.add(domNode);
//				domGraph.addNode(domNode);
//			}
//			else{
//				System.out.println("is already there-----"+domNode.getName());
//				domNode = getDomNodeFromCollection(domNode);
//			}
//
//			for(DominatorTreeNode dominatorTreeNode1:childDomNodes){
//				DomNode childDomNode = new DomNode(dominatorTreeNode1.getName());
//				if(!(hasDomNode(childDomNode, domNodeCollection))){
//					domNodeCollection.add(childDomNode);
//					childDomNode.setPredecessor(domNode);
//					DOMEdge edge = new DOMEdge(domNode, childDomNode);
//					domGraph.addDirectedEdge(edge, domNode, childDomNode);
//					domGraph.addNode(childDomNode);
//					childDomNode.setInComingEdge(edge);
//					System.out.println("childs created for "+domNode.getName()+"child is "+childDomNode.getName());
//					children.add(edge);
//					successors.add(childDomNode);
//				}
//				else{
//					childDomNode = getDomNodeFromCollection(childDomNode);
//					childDomNode.setPredecessor(domNode);
//					DOMEdge edge = new DOMEdge(domNode, childDomNode);
//					domGraph.addDirectedEdge(edge, domNode, childDomNode);
//					//domGraph.addNode(childDomNode);
//					childDomNode.setInComingEdge(edge);
//					System.out.println("childs created for "+domNode.getName()+"child is "+childDomNode.getName());
//					children.add(edge);
//					successors.add(childDomNode);
//				}
//			}
//			domNode.setOutgoingEdges(children);
//			domNode.setSuccessors(successors);
//		}
//		setStartNodeToGraph();
//	}
//
//	public Vector<TreeNode> createDomTreeNode(DominatorTreeNode dominatorTreeNode){
//
//		DomNode domNode = new DomNode(dominatorTreeNode.getName());
//		domNode.setPositionInGraph(positionInGraph++);
//		domNodeCollection.add(domNode);
//		Vector<TreeNode> childs = dominatorTreeNode.getChildren();
//
//		return childs;
//
//	}
//
//
//
//	public void createMapOfchildsVsTreeNode(){
//
//		Set<Entry<Node, DominatorTreeNode>> entries = dominatorTree.getNodeVsDominatorTreeNode().entrySet();
//		Iterator<Map.Entry<Node, DominatorTreeNode>> iter = entries.iterator();
//
//		while(iter.hasNext()){
//			Map.Entry<Node, DominatorTreeNode> entry = iter.next();
//			DominatorTreeNode parentDomNode = entry.getValue();
//			Vector<DominatorTreeNode> childDomNodes = parentDomNode.getDTNChildren();
//			//System.out.println("parent is ----"+parentDomNode.getName()+"children size is"+childDomNodes.size());
//			domTreeNodevsChildsNode.put(parentDomNode, childDomNodes);
//		}
//	}
//
//	public DomNode getDomNodeFromCollection(DomNode domNode){
//
//		for(DomNode domNodeTemp:domNodeCollection){
//			if(domNodeTemp.equals(domNode)){
//				return domNodeTemp;
//			}
//		}
//
//		return null;
//	}
//
//	public DomGraph getDomGraph() {
//		return domGraph;
//	}
//
//	public void setDomGraph(DomGraph domGraph) {
//		this.domGraph = domGraph;
//	}
//
//	public boolean hasDomNode(DomNode domNode, ArrayList<DomNode> DomNodeList) {
//		int numSuccs = DomNodeList.size();
//		for (int i = 0; i < numSuccs; i++) {
//			DomNode domNodeFound = DomNodeList.get(i);
//			if (domNodeFound.equals(domNode))
//				return true;
//		}
//		return false;
//	}
//
//	public void setStartNodeToGraph(){
//
//		for(DomNode domNode:domNodeCollection){
//			if(domNode.getPredecessor()==null){
//				domGraph.setStartNode(domNode);
//			}
//		}
//
//	}
}

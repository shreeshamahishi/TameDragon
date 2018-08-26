package org.tamedragon.visualization;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

//import org.tamedragon.common.ControlFlowGraph;
//import org.tamedragon.common.ControlFlowNode;
//import org.tamedragon.common.DiGraph;
//import org.tamedragon.common.CFEdge;
//import org.tamedragon.common.Node;
import org.tamedragon.common.llvmir.instructions.AssemblyInstruction;

public class BreadthFirstSearch {
	
//	private Font font;
//	private Graphics2D graphics2D;
//	private Graphics graphics;
//	
//	private final int leftMargin = 10;              // Distance from the left-most node to the left of drawing window
//	private final int topMargin = 10;               // Distance from the top-most node to the top of drawing window
//	private final int horizontalGap = 20;           // Distance between two nodes at the same level
//	private final int verticalGap = 20;             // Distance between levels
//	private final int verticalGapBetweenIns = 4;    // Vertical distance between instructions in a node
//	
//	private Vector<BFSLevel> levels;
//	int indexOfLowestLevelWithMaxNodes = 0;         // Index of the lowest level with the maximum number of nodes
//	int maxNumNodesInLevel = 0;                     // The maximum number of nodes in any level; there could be more than one level with this
//													// many nodes
//	
//	private HashMap<Node, DrawingParameters> nodesVsDrawingParameters;        // Map of each node against it's size
//	private HashMap<BFSLevel, Integer> layersVsHeights;   // Map of each layer against the height of it's TALLEST node
//													      // (The height of a layer is height of it's tallest node)
//	public BreadthFirstSearch(){
//		font = new Font("Dialog", Font.PLAIN, 36);
//	}
//	
//	public void setGraphics(Graphics grps){
//		graphics = grps;
//		graphics2D = (Graphics2D) grps;
//	}
//	
//	public void renderGraph(DiGraph graph, Node startNode){
//		setLevelsAndSizes(graph, startNode);         // First pass, establishes the levels and the node sizes
//		traverseUpwards();                           // First part of the second pass; traverses upwards, determing positions
//		traverseDownwards();                         // Second part of the second pass; traverses upwards, determing positions
//		drawGraph();                                 // Third pass, actually draws the graph
//	}
//	
//	public void setLevelsAndSizes(DiGraph graph, Node startNode){
//		// BFS traversal 
//		levels = new Vector<BFSLevel>();                  // Create a list of levels
//		nodesVsDrawingParameters = new HashMap<Node, DrawingParameters>();    // Create a map of nodes against thier sizes
//		
//		HashSet<Node> visitedNodes = new HashSet<Node>();   // Set of visited nodes
//		
//		// Create the first level and place the start node in it; add the first level to
//		// the list of levels
//		BFSLevel bfsLevel0 = new BFSLevel();
//		bfsLevel0.addNode(startNode);
//		levels.add(bfsLevel0);
//		int levelNum = 0;
//		BFSLevel currLevel = bfsLevel0;
//		
//		// Calculate the size of the start node and store it. Also store the height of the node
//		Dimension sizeOfStartNode = getSize(startNode);
//		int heightofStartNode = sizeOfStartNode.height;
//		layersVsHeights.put(bfsLevel0, new Integer(heightofStartNode));
//		
//		int maxNumNodes = 1;
//		while(!currLevel.isEmpty()){
//			BFSLevel nextLevel = new BFSLevel();  // Create the next level
//			levels.add(nextLevel);                // and add it to the list of levels
//			
//			Vector<Node> currLevelNodes = currLevel.getNodes();
//			int numNodesInCurrLevel = currLevelNodes.size();
//
//			// Traverse through each node at the current level
//			int maxHeightOfNextLayer = 0;
//			for(int i = 0; i <  numNodesInCurrLevel; i++){
//				Node node = currLevelNodes.elementAt(i);
//		
//				Vector outgoingEdges = graph.getOutgoingEdges(node);
//				int numOutgoingEdges = 0;
//				
//				if(outgoingEdges != null) numOutgoingEdges = outgoingEdges.size();
//				if(numOutgoingEdges == 2){
//					CFEdge leftEdge = (CFEdge) outgoingEdges.elementAt(0);
//					CFEdge rightEdge = (CFEdge) outgoingEdges.elementAt(1);
//					
//					Node leftChildNode = leftEdge.getOppositeNode(node);
//					Node rightChildNode = rightEdge.getOppositeNode(node);
//					
//					if(!crossEdge(leftChildNode, rightChildNode)){
//						// The child nodes have no edge between them
//						maxHeightOfNextLayer = addNodeToLevel(nextLevel, leftChildNode, maxHeightOfNextLayer, visitedNodes);
//						maxHeightOfNextLayer = addNodeToLevel(nextLevel, leftChildNode, maxHeightOfNextLayer, visitedNodes);
//					}				
//				}
//				else if(numOutgoingEdges == 1){
//					CFEdge outEdge = (CFEdge) outgoingEdges.elementAt(0);
//					Node childNode = outEdge.getOppositeNode(node);
//				
//					// Check the number of children of the child node; if there are two of them,
//					// identify the other parent and ensure that the this other parent is in the same level
//					// as the current node or in some level preceding it.
//					Vector edgesFromAbove = childNode.getInComingEdges();
//					if(edgesFromAbove.size() == 2){
//						// The child node has two parents; get the other parent of this child node
//						Node otherParent = null;
//						
//						CFEdge leftEdgeFromAbove = (CFEdge) edgesFromAbove.elementAt(0);
//						CFEdge rightEdgeFromAbove = (CFEdge) edgesFromAbove.elementAt(1);
//						
//						if(node == leftEdgeFromAbove.getOppositeNode(childNode))
//							otherParent = rightEdgeFromAbove.getOppositeNode(childNode);
//						else
//							otherParent = leftEdgeFromAbove.getOppositeNode(childNode);
//										
//						// If this other parent belongs to the current level or a level
//						// above it, and the child node has not already been visited, add the child 
//						// node to the next level
//						if(nodeInLevelOrPrecedingLevels(otherParent, levelNum)){
//							// Add the child node to the next level if not already added
//							maxHeightOfNextLayer = addNodeToLevel(nextLevel, childNode, maxHeightOfNextLayer, visitedNodes);
//						}
//					}
//					else{
//						// The current node is the only parent of this child node
//						maxHeightOfNextLayer = addNodeToLevel(nextLevel, childNode, maxHeightOfNextLayer, visitedNodes);
//					}
//				}
//			}
//			levelNum++;
//			currLevel = nextLevel;    // Set the current layer to the next layer
//			layersVsHeights.put(currLevel, new Integer(maxHeightOfNextLayer));
//			
//			// Set the maximum number of nodes
//			Vector<Node> nodesInLevel = currLevel.getNodes();
//			int numNodesInLevel = nodesInLevel.size();
//			if(numNodesInLevel > maxNumNodes) 
//				maxNumNodes = numNodesInLevel;
//		}		
//		
//		setIndexOfLowestLevelWithMaxNodes(maxNumNodes);  // Set the index of the widest layer
//		maxNumNodesInLevel = maxNumNodes;
//	}
//	
//	/**
//	 * Adds a node to the given level only if the node has not already been visited. Sets the size of the node
//	 * in the nodeVsSizes map. Returns the height of the node if it is taller than the height passed to it; else
//	 * returns the height passed to it.
//	 * 
//	 * @param level
//	 * @param node
//	 * @param currMaxHeight
//	 * @param visitedNodes
//	 * @return
//	 */
//	private int addNodeToLevel(BFSLevel level, Node node, int currMaxHeight, HashSet<Node> visitedNodes){
//		if(!visitedNodes.contains(node)){
//			level.addNode(node);
//			visitedNodes.add(node);
//			Dimension size = getSize(node);
//			int heightOfNode = size.height;
//			if(heightOfNode > currMaxHeight){
//				currMaxHeight = heightOfNode;
//			}
//			DrawingParameters drawingParameters = new DrawingParameters();
//			drawingParameters.setSize(size);
//			nodesVsDrawingParameters.put(node, drawingParameters);
//		}
//		return currMaxHeight;
//	}
//	
//	private Dimension getSize(Node node){
//		ControlFlowNode cfNode = (ControlFlowNode) node;
//		Dimension nodeDimension = new Dimension();
//		Vector instructions = cfNode.getInstructions();
//		int maxWidth = 0;
//		int totalHeight = 5;
//		int numIns = instructions.size();
//		for(int i = 0; i < numIns; i++){
//			AssemblyInstruction ins = (AssemblyInstruction) instructions.elementAt(i);
//			String insStr = ins.toString();
//			Dimension strDim = getStringRenderSize(insStr);
//			
//			if(strDim.width > maxWidth) maxWidth = strDim.width;
//			totalHeight += strDim.height + verticalGapBetweenIns;
//		}
//		
//		return nodeDimension;
//	}
//	
//	/**
//	 * Returns the size as a Dimension of a string that is rendered on the screen using the given
//	 * graphics object and the font
//	 */
//	private Dimension getStringRenderSize(String str){
//		Dimension stringDimension = new Dimension();
//		
//		FontRenderContext frc = graphics2D.getFontRenderContext();
//        TextLayout        tl  = new TextLayout(str, font, frc);
//
//        Rectangle2D rect                   = tl.getBounds();
//        stringDimension.width  = (int) rect.getWidth();
//        stringDimension.height = (int) rect.getHeight();
//
//        return stringDimension;
//
//	}
//	
//	/**
//	 * Returns a flag indicating whether or not the two specified node have a direct edge between them.
//	 * @param node
//	 * @param otherNode
//	 * @return
//	 */
//	private boolean crossEdge(Node node, Node otherNode){
//		Vector nodePreds = node.getPredecessors();
//		if(nodePreds.contains(otherNode)) return true;
//		
//		Vector otherNodePreds = otherNode.getPredecessors();
//		if(otherNodePreds.contains(node)) return true;
//		
//		return false;
//	}
//	
//	/**
//	 * This function returns a flag indicating whether the specified node is present in the specified
//	 * level or in one of it's preceding levels 
//	 * @param node
//	 * @param startLevelNum
//	 * @return
//	 */
//	private boolean nodeInLevelOrPrecedingLevels(Node node, int startLevelNum){
//		for(int i = startLevelNum; i >= 0; i--){
//			BFSLevel lvl = levels.elementAt(i);
//			if(lvl.containsNode(node)) return true;
//		}
//		return false;
//	}
//	
//	/**
//	 * This function executes the second pass of actually drawing the graph. It starts with the widest level (level
//	 * containing the maximum number of nodes) and traverses "upwards" until it reaches the start node". Then it traverses
//	 * "downward" until it reaches the end node.
//	 *
//	 */
//	private void traverseUpwards(){		
//		// Start with the lowest level that has the maximum number of nodes and traverse upwards
//	
//		BFSLevel lowestLevelWithMaxNodes = levels.elementAt(indexOfLowestLevelWithMaxNodes);
//		for(int a = indexOfLowestLevelWithMaxNodes; a >= 0; a--){		
//			BFSLevel currLevel = levels.elementAt(a);
//			int yOffSet = getVerticalDistanceForLayer(a);
//			Vector<Node> nodesInLevel = lowestLevelWithMaxNodes.getNodes();
//			int numnNodesInLevel = nodesInLevel.size();
//			
//			// Iterate over the nodes in the current level
//			if(a == 0){
//				// Iterate over nodes in the level that is the lowest and also contains the maximum number of nodes
//				setPositionsForLevelWithMaxNodes(currLevel, yOffSet, 0, nodesInLevel);
//			}
//			else if(numnNodesInLevel == maxNumNodesInLevel){
//				// Iterate over nodes in the level has the maximum number of nodes, but is the lowest level with those number of nodes
//				int startOffSet = getStartXOffSetForInnerLevelWithMaxNodes(nodesInLevel, a);
//				setPositionsForLevelWithMaxNodes(currLevel, yOffSet, startOffSet, nodesInLevel);
//			}
//			else{
//				// Iterate over nodes in the level; nodes in this level depend upon successor nodes
//				sePositionsForNodesInLevel(currLevel, yOffSet, a, nodesInLevel);	
//			}
//		}
//	}
//	
//	private void setPositionsForLevelWithMaxNodes(BFSLevel lowestLevelWithMaxNodes, int yOffSet, int startXOffset,
//													Vector<Node> nodesInLevel){
//		int xOffSet = startXOffset; // Initialize xOffSet
//		int numnNodesInLevel = nodesInLevel.size();
//		
//		for(int i = 0; i < numnNodesInLevel; i++){
//			ControlFlowNode cfNode = (ControlFlowNode)nodesInLevel.elementAt(i);
//			
//			// Set the nodesVsDrawingParameters map
//			DrawingParameters drawingParameters = nodesVsDrawingParameters.get(cfNode);
//			drawingParameters.setYPosition(yOffSet);
//			drawingParameters.setXPosition(xOffSet);
//			nodesVsDrawingParameters.put(cfNode, drawingParameters);
//			
//			// Update the xOffSet for the next node in this level
//			Vector<Node> preds = cfNode.getPredecessors();
//			Vector<Node> sucss = cfNode.getSuccessors();		
//			if(preds.size() == 1){
//				// Get the parent (A node that has a single parent must have the parent in the immediate preceding level)
//				ControlFlowNode cfNodeParent = (ControlFlowNode) preds.elementAt(0);
//	
//				if(isSingleParentRightDiagonal(cfNodeParent, cfNode, indexOfLowestLevelWithMaxNodes)){					
//					xOffSet = getXOffSetForNextNode(cfNode, sucss, xOffSet, cfNodeParent, indexOfLowestLevelWithMaxNodes);
//				}
//				else{// We ignore this case since the current level is the "base" level; increment by horizontal gap for
//					// the next node
//					xOffSet += horizontalGap; 
//					
//				}
//			}
//			else if(preds.size() == 2){
//				ControlFlowNode cfNodeRightParent = (ControlFlowNode) preds.elementAt(1);
//				BFSLevel precedingLevel = levels.elementAt(indexOfLowestLevelWithMaxNodes -1);
//				if(precedingLevel.containsNode(cfNodeRightParent)){
//					// The parent node is to the right on the level immediately preceding the current node
//					xOffSet = getXOffSetForNextNode(cfNode, sucss, xOffSet, cfNodeRightParent, indexOfLowestLevelWithMaxNodes);
//				}
//				else 
//					xOffSet += horizontalGap;
//			}		
//		}
//	}
//
//	/**
//	 * Sets the positions for nodes in a level that has lesser than the maximum number of nodes in any level during
//	 * the upward traversal of the control-flow graph
//	 *
//	 */
//	private void sePositionsForNodesInLevel(BFSLevel level, int yOffset, int currLevelIndex, Vector<Node> nodesInLevel){
//		int numNodesInLevel = nodesInLevel.size();
//		
//		Vector<Integer> indicesOfNodesWithUnknownXPostions = new Vector<Integer>();   // List of indices of nodes whose
//																					  // x offset cannot be determined by successors
//		
//		for(int i = 0; i < numNodesInLevel; i++){
//			Node currNode = nodesInLevel.elementAt(i);
//			DrawingParameters dpNode = nodesVsDrawingParameters.get(currNode);
//			dpNode.setYPosition(yOffset);
//			Vector<Node> sucss = currNode.getSuccessors();
//			
//			int xPosOfNode = 0;  // To be determined
//			
//			int numSucss = sucss.size();
//			if(numSucss == 1){
//				Node succ = sucss.elementAt(0);
//				BFSLevel nextLevel = levels.elementAt(currLevelIndex + 1);
//				if(nextLevel.containsNode(succ)){
//					DrawingParameters dpSucc = nodesVsDrawingParameters.get(succ);
//					int succXPos = dpSucc.getXPosition();		
//					
//					if(isSingleSuccessorRightDiagonal(currNode, succ, currLevelIndex)){		
//						Dimension nodeSize = dpNode.getSize();
//						xPosOfNode = succXPos - nodeSize.width;
//					}
//					else if(isSingleSuccessorLeftDiagonal(currNode, succ, currLevelIndex)){
//						Dimension succSize = dpSucc.getSize();
//						xPosOfNode = succXPos + succSize.width;
//					}
//					dpNode.setXPosition(xPosOfNode);
//				}
//				else{
//					// The position of the current node cannot be determined from the successor's information
//					indicesOfNodesWithUnknownXPostions.addElement(new Integer(i));
//				}
//			}
//			else if(numSucss == 2){
//				Node firstSucc = sucss.elementAt(0);
//				Node secondSucc = sucss.elementAt(1);
//				BFSLevel nextLevel = levels.elementAt(currLevelIndex + 1);
//				if(nextLevel.containsNode(firstSucc)){
//					// Calculate the xPosition based on the successor on the left side
//					DrawingParameters dpFirstSucc = nodesVsDrawingParameters.get(firstSucc);
//					int xPosFirstSucc = dpFirstSucc.getXPosition();
//					Dimension firstSuccSize = dpFirstSucc.getSize();
//					xPosOfNode = xPosFirstSucc + firstSuccSize.width;
//					dpNode.setXPosition(xPosOfNode);
//				}
//				else if(nextLevel.containsNode(firstSucc)){
//					DrawingParameters dpSecondSucc = nodesVsDrawingParameters.get(secondSucc);
//					int xPosSecondSucc = dpSecondSucc.getXPosition();
//					
//					Dimension nodeSize = dpNode.getSize();
//					xPosOfNode = xPosSecondSucc - nodeSize.width;
//					dpNode.setXPosition(xPosOfNode);
//				}
//				else{
//					// The position of the current node cannot be determined from the successor's information
//					indicesOfNodesWithUnknownXPostions.addElement(new Integer(i));
//				}
//			}
//		}
//		
//		// If there were some nodes whose x offsets could not be determined, iterate through them and attempt to establish a 
//		// a position
//		// TBD - To implement later
//		int numUnknowns = indicesOfNodesWithUnknownXPostions.size();
//		if(numUnknowns >0){
//			System.out.println("Found a node with unknown x postion");
//		}
//	}
//	
//	/**
//	 * Returns the x position of the left-most node in a level that has that maximum number of nodes, but is NOT
//	 * the lowest level with the maximum number of nodes  
//	 */
//	
//	private int getStartXOffSetForInnerLevelWithMaxNodes(Vector<Node> nodesInLevel, int currLevel){		
//		int xOffSet = 0;
//		
//		// Establish the xOffSet for the left-most node in the level
//		ControlFlowNode cfNodeLeftMost = (ControlFlowNode)nodesInLevel.elementAt(0);
//		DrawingParameters drawingParametersOfNode = nodesVsDrawingParameters.get(cfNodeLeftMost);
//		Vector<Node> sucssOfLeftMostNode = cfNodeLeftMost.getSuccessors();
//		
//		int numSucss = sucssOfLeftMostNode.size();
//		if(numSucss == 1){                              // This node has only one successor
//			Node singleSuccessor = sucssOfLeftMostNode.elementAt(0);
//			BFSLevel nextLevel = levels.elementAt(currLevel + 1);
//			if(nextLevel.containsNode(singleSuccessor)){   // The successor is in the next level
//				if(isSingleSuccessorRightDiagonal(cfNodeLeftMost, singleSuccessor, currLevel)){  // Successor is to the right of this node
//					DrawingParameters drawingParametersOfSucc = nodesVsDrawingParameters.get(singleSuccessor);
//					int xOffSetOfSucc = drawingParametersOfSucc.getXPosition();
//					
//					
//					xOffSet = xOffSetOfSucc - drawingParametersOfNode.getSize().width;
//				}
//			}
//			else{
//				// Check if the sucessor is in a level between the "base level" and the current level
//				int succLevel = -1;
//				for(int i = indexOfLowestLevelWithMaxNodes; i > currLevel +1; i--){
//					BFSLevel level = levels.elementAt(i);
//					if(level.containsNode(singleSuccessor)){
//						succLevel = i;
//						break;
//					}
//				}
//				if(succLevel > 0){   // The successor node is at a level between the lowest level with maximum number
//					                 // of nodes and the current level; get the minimum position of the left-most nodes
//									 // in all levels between this node and the current node
//					int minXOffSet = 0;
//					for(int i = succLevel; i >= currLevel +1; i--){
//						BFSLevel lvl = levels.elementAt(i);
//						Node leftmostNode = lvl.getNodeAt(0);
//						int XoffSetOfLeftMostNode = nodesVsDrawingParameters.get(leftmostNode).getXPosition();
//						if(XoffSetOfLeftMostNode < minXOffSet)
//							minXOffSet = XoffSetOfLeftMostNode;
//					}			
//					xOffSet = minXOffSet - drawingParametersOfNode.getSize().width;		
//				}
//				else{
//					// The xOffSet of the leftmost node in this level cannot be determined from the successor 
//					// positions; default it to 0
//					xOffSet = 0;
//				}
//			}
//		}
//		return xOffSet;
//	}
//	
//	/**
//	 * Returns the horizontal offset for the next node in the current level. The sizes of the parent to the 
//	 * right and the successor to the right are compared and the wider one returned. 
//	 * 
//	 * @param sucss
//	 * @param currOffSet
//	 * @param cfNodeParent
//	 * @return
//	 */
//	private int getXOffSetForNextNode(Node node, Vector sucss, int currOffSet, Node cfNodeParent,
//									   int currLevel){
//		// If this node also has a successor in the next level, increment the x-offset
//		// to the maximum among the widths of the predecessor and successor
//		int numSuccs = sucss.size();
//		Dimension sizeOfParent = getSize(cfNodeParent);
//		if(numSuccs == 1){
//			ControlFlowNode cfNodeSuccessor = (ControlFlowNode) sucss.elementAt(0);
//			if(isSingleSuccessorRightDiagonal(node, cfNodeSuccessor, currLevel)){
//				Dimension sizeOfSuccessor = getSize(cfNodeSuccessor);
//				if(sizeOfSuccessor.width > sizeOfParent.width)
//					currOffSet += sizeOfSuccessor.width;
//				else
//					currOffSet += sizeOfParent.width;
//			}
//			else
//				currOffSet += sizeOfParent.width;
//				
//		}
//		else if(numSuccs == 2){
//			// This node has two successors; compare the size of the parent 
//			// with the right child
//			ControlFlowNode cfNodeSuccessor = (ControlFlowNode) sucss.elementAt(1);
//			BFSLevel nextLevel = levels.elementAt(currLevel + 1);
//			if(nextLevel.containsNode(cfNodeSuccessor)){
//				Dimension sizeOfSuccessor = getSize(cfNodeSuccessor);
//				if(sizeOfSuccessor.width > sizeOfParent.width)
//					currOffSet += sizeOfSuccessor.width;
//				else
//					currOffSet += sizeOfParent.width;
//			}
//			else
//				currOffSet += sizeOfParent.width;
//		}
//		return currOffSet;
//	}
//	
//	private boolean isSingleParentRightDiagonal(Node cfNodeParent, Node cfNode, int levelNum){
//		if(levelNum == 0)
//			return false;  // First level
//		
//		// Not first level; check 
//		BFSLevel prevLevel = levels.elementAt(levelNum -1);
//		
//		if(prevLevel.containsNode(cfNodeParent)){
//			Vector childNodesOfParent = cfNodeParent.getPredecessors();
//			Node leftChildOfParent = (Node) childNodesOfParent.elementAt(0); 
//			if(leftChildOfParent == cfNode) return true;
//			
//		}
//		return false; // parent is not in the level above cfNode
//	}
//	
//	private boolean isSingleParentLeftDiagonal(Node cfNodeParent, Node cfNode, int levelNum){
//		if(levelNum == 0)
//			return false;  // First level
//		
//		// Not first level; check 
//		BFSLevel prevLevel = levels.elementAt(levelNum -1);
//		
//		if(prevLevel.containsNode(cfNodeParent)){
//			Vector childNodesOfParent = cfNodeParent.getPredecessors();
//			Node rightChildOfParent = (Node) childNodesOfParent.elementAt(1); 
//			if(rightChildOfParent == cfNode) return true;
//			
//		}
//		return false; // parent is not in the level above cfNode
//	}
//	
//	private boolean isSingleSuccessorRightDiagonal(Node node, Node successor, int currLevel){
//		// If the successor does not belong to the next level, return false
//		BFSLevel nextLevel = levels.elementAt(currLevel + 1);
//		if(!nextLevel.containsNode(successor))
//			return false;
//		
//		// Successor is in the next level; if the leftmost parent of the sucessor is the node passed,
//		// return true
//		Vector predsOfSucc = successor.getPredecessors();
//		Node leftParentOfSucc = (Node) predsOfSucc.elementAt(0);
//		if(leftParentOfSucc == node)
//			return true;
//		
//		return false;
//	}
//	
//	private boolean isSingleSuccessorLeftDiagonal(Node node, Node successor, int currLevel){
//		// If the successor does not belong to the next level, return false
//		BFSLevel nextLevel = levels.elementAt(currLevel + 1);
//		if(!nextLevel.containsNode(successor))
//			return false;
//		
//		// Successor is in the next level; if the rightmost parent of the sucessor is the node passed,
//		// return true
//		Vector predsOfSucc = successor.getPredecessors();
//		if(predsOfSucc.size() < 2) return false;
//		
//		Node rightParentOfSucc = (Node) predsOfSucc.elementAt(1);
//		if(rightParentOfSucc == node)
//			return true;
//		
//		return false;
//	}
//	
//	/**
//	 * Returns the index of the "widest" layer. The widest layer is found as follows:
//	 * 1. There can be more than one layer with the same maximum number. If there is only one 
//	 *    such layer, return it's index
//	 * 2. If there exist more than one layer with maximum number of nodes, check the number of children
//	 *    of the first node of each such layer. If there is only one child, that level is one of the widest layers
//	 * 3. Return the index of the first level that satisfies points "1" and "2".  
//	 *
//	 */
//	
//	private void setIndexOfLowestLevelWithMaxNodes(int maxNodes){
//		int numLevels = levels.size();
//		for(int i = numLevels; i >= 0; i--){
//			BFSLevel level = levels.elementAt(i);
//			Vector<Node> nodesInLevel = level.getNodes();
//			if(nodesInLevel.size() == maxNodes){		
//				indexOfLowestLevelWithMaxNodes = i;
//				break;
//			}
//		}
//	}
//	
//	/**
//	 * Returns the vertical distance from the top of the drawing window to the layer at the given index
//	 * @param indexOfLevel
//	 * @return
//	 */
//	
//	private int getVerticalDistanceForLayer(int indexOfLevel){
//		int yOffSet = topMargin;
//		for(int i = 0; i < indexOfLevel; i++){
//			BFSLevel level = levels.elementAt(i);
//			int heightOfLevel = layersVsHeights.get(level).intValue();
//			yOffSet += heightOfLevel + verticalGap;
//		}
//		return yOffSet;
//	}
//	
//	/**
//	 * Draws the control flow node by rendering each instruction in the node as a string and then drawing
//	 * a bounding rectangle around the instructions
//	 */
//	private void drawNode(Node node, int xOffSet, int yOffSet){
//		ControlFlowNode cfNode = (ControlFlowNode) node;
//		Vector<AssemblyInstruction> instrs = cfNode.getInstructions();
//		
//		// Draw the instructions in the node first
//		int numInstrs = instrs.size();
//		for(int i = 0; i < numInstrs; i++){
//			AssemblyInstruction instr = instrs.elementAt(i);
//			String instrStr = instr.toString();
//			graphics.drawString(instrStr, xOffSet, yOffSet);
//			
//			// Update the vertical offset
//			FontRenderContext frc = graphics2D.getFontRenderContext();
//	        TextLayout tl = new TextLayout(instrStr, font, frc);
//	        Rectangle2D rect = tl.getBounds();
//	        yOffSet += (int) rect.getHeight();
//		}
//		
//		// Draw the node (rectangle) now
//		DrawingParameters drawingParameters = nodesVsDrawingParameters.get(node);
//		graphics2D.drawRect(xOffSet, yOffSet, drawingParameters.getSize().width, drawingParameters.getSize().height);
//		/*
//		 g2 = (Graphics2D) g;
//         g.drawString(str, boundingRectangle.x,
//                      boundingRectangle.height / 2 + boundingRectangle.y + (getDefaultSize(g).height) / 2);
//         g2.drawRect(boundingRectangle.x, boundingRectangle.y, boundingRectangle.width, boundingRectangle.height);
//         */
//
//	}
//	
//	/**
//	 * Second part of the second pass; traverses downwards from the level immediately after the lowest level with maximum number of nodes, 
//	 * determing positions based on predecessor information.
//	 */
//	private void traverseDownwards(){
//		for(int i = indexOfLowestLevelWithMaxNodes + 1; i < levels.size(); i++){
//			BFSLevel level = levels.elementAt(i);
//			int yOffSet = getVerticalDistanceForLayer(i);    // y offset for this level
//			
//			Vector<Node> nodesInLevel = level.getNodes();
//			int numNodesInLevel = nodesInLevel.size();
//			for(int j = 0; j < numNodesInLevel; j++){
//				Node currNode = nodesInLevel.elementAt(j);
//				DrawingParameters dpNode = nodesVsDrawingParameters.get(currNode);
//				Vector preds  = currNode.getPredecessors();
//				int numPreds = preds.size();
//				int xOffset = 0; // To be determined
//				if(numPreds == 1){
//					Node predNode = (Node) preds.elementAt(0);
//					DrawingParameters dpParent = nodesVsDrawingParameters.get(predNode);
//					int xOffsetParent = dpParent.getXPosition();
//					if(isSingleParentRightDiagonal(predNode, currNode, i)){			
//						int nodeSize = dpNode.getSize().width;
//						xOffset = xOffsetParent - nodeSize;
//					}
//					else if(isSingleParentLeftDiagonal(predNode, currNode, i)){
//						int parentNodeSize = dpParent.getSize().width;
//						xOffset = xOffsetParent + parentNodeSize;
//					}
//					else{
//						// Same horizontal line as that of the parent
//						xOffset = xOffsetParent;
//					}
//				}
//				else if(numPreds == 2){
//					Node leftParent = (Node) preds.elementAt(0);
//					Node rightParent = (Node) preds.elementAt(1);
//					
//					BFSLevel prevLevel = levels.elementAt(i -1);
//					if(prevLevel.containsNode(leftParent)){
//						DrawingParameters dpParent = nodesVsDrawingParameters.get(leftParent);
//						int xOffsetLeftParent = dpParent.getXPosition();
//						int widthOfLeftParent = dpParent.getSize().width;
//						xOffset = xOffsetLeftParent + widthOfLeftParent;
//					}
//					else if(prevLevel.containsNode(rightParent)){
//						DrawingParameters dpParent = nodesVsDrawingParameters.get(rightParent);
//						int xOffsetLeftParent = dpParent.getXPosition();
//						int widthOfNode = dpNode.getSize().width;
//						xOffset = xOffsetLeftParent - widthOfNode;
//					}
//				}
//				dpNode.setXPosition(xOffset);
//				dpNode.setYPosition(yOffSet);
//			}
//		}
//	}
//	
//	/**
//	 * Actually draws the graph based on the sizes and the positions calculated
//	 *
//	 */
//	private void drawGraph(){
//		// Find the minimum x offset of among all levels
//		int numLevels  = levels.size();
//		int leftMostXOffset = 0;
//		for(int i = 0; i < numLevels; i++){
//			BFSLevel level = levels.elementAt(i);
//			Vector<Node> nodesInLevel = level.getNodes();
//			Node leftMostNodeInLevel = nodesInLevel.elementAt(0);
//			DrawingParameters drawingParams = nodesVsDrawingParameters.get(leftMostNodeInLevel);
//			int xOffsetForLeftMostNode = drawingParams.getXPosition();
//			if(xOffsetForLeftMostNode < leftMostXOffset)
//				leftMostXOffset = xOffsetForLeftMostNode;
//		}
//		
//		if(leftMostXOffset == 0){
//			leftMostXOffset = leftMargin;
//		}
//		else if(leftMostXOffset < 0){
//			leftMostXOffset = 0 - leftMostXOffset + 10;
//		}
//		
//		// Now draw each node
//		for(int i = 0; i < numLevels; i++){
//			BFSLevel level = levels.elementAt(i);
//			Vector<Node> nodesInLevel = level.getNodes();
//			int yOffSet = getVerticalDistanceForLayer(i);    // y offset for this level
//			
//			int numNodes = nodesInLevel.size();
//			for(int j = 0; j < numNodes; j++){
//				Node node = nodesInLevel.elementAt(j);
//				DrawingParameters drawingParams = nodesVsDrawingParameters.get(node);
//				int xOffset = drawingParams.getXPosition() + leftMostXOffset;
//				drawNode(node, xOffset, yOffSet);
//			}
//		}
//	}
}


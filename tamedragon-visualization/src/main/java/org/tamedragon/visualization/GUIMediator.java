package org.tamedragon.visualization;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

//import org.tamedragon.common.ConnectivityTestDFS;
//import org.tamedragon.common.ControlFlowGraph;
//import org.tamedragon.common.ControlFlowNode;
//import org.tamedragon.common.DiGraph;
//import org.tamedragon.common.DominatorTree;
//import org.tamedragon.common.CFEdge;
//import org.tamedragon.common.Node;
import org.tamedragon.common.Utilities;
import org.tamedragon.common.llvmir.instructions.AssemblyInstruction;

public class GUIMediator extends JComponent {

	private static final long serialVersionUID = 1L;
	private boolean recalculate = false;

	//private ControlFlowGraph controlFlowGraph;
//	private DominatorTree dt;
//	private Point location;
//	private final JDialog nodePropertiesdialog = new JDialog();
//	private JTextArea nodeProptextArea = null;
//	private final int DEFAULT_HEIGHT = 200;
//	private final int DEFAULT_WIDTH = 200;
//
//	private HashMap<Node, HashSet<Node>> dominanceFrontiers;
//	private Graphics2D graphics2D;
//	private Graphics graphics;
//
//	private final int leftMargin = 50; // Distance from the left-most node to
//										// the left of drawing window
//	private final int topMargin = 10; // Distance from the top-most node to
//										// the top of drawing window
//	private final int horizontalGap = 20; // Distance between two nodes at the
//											// same level
//	private final int verticalGap = 20; // Distance between levels
//	private final int verticalGapBetweenIns = 5; // Vertical distance between
//													// instructions in a node
//	private final int instrLeftMargin = 5;
//	private final int instrRightMargin = 5;
//	private final int minDistanceOfEdgeFromNode = 5;
//
//	private double maxTriangleHeight = 0.0;
//	private double minTriangleHeight = 4.0;
//	private double maxTriangleHeightForAngledLine = 0.0;
//	private final double triangleHeightFraction = 0.3; // Fraction of the
//														// incident directed
//														// edge that represents
//	// the height of the triangle
//	private final double angledTriangleHeightFraction = 0.4;
//
//	private Vector<BFSLevel> levels;
//	int indexOfLowestLevelWithMaxNodes = 0; // Index of the lowest level with
//											// the maximum number of nodes
//	int maxNumNodesInLevel = 0; // The maximum number of nodes in any level;
//								// there could be more than one level with this many nodes
//	
//	Dimension graphSize; // The size of the drawn graph
//
//	private HashMap<Node, DrawingParameters> nodesVsDrawingParameters; // Map
//																		// of
//																		// each
//																		// node
//																		// against
//																		// it's
//																		// size
//	private DrawingParameters drawingParameters;
//	private HashMap<BFSLevel, Integer> levelsVsHeights; // Map of each layer
//														// against the height of
//														// it's TALLEST node
//	// (The height of a layer is height of it's tallest node)
//
//	private HashMap<Node, TriangleParameters> nodeVsTriangleParams; // Map of
//																	// each node
//																	// against
//																	// the FIRST
//																	// determined
//																	// angle
//	// of the side of the triangle with its perpendicular bisector.
//
//	private Vector<EdgeParameters> edgeParametersList; // List of all edge parameters to be used
//													   // for drawing edges
//
//	private HashMap<Node, BackEdge> nodeVsBackEdge; // A map of a node with a
//													// corresponding back-edge
//
//	private HashMap<Node, Vector<AdjacentEdge>> nodesVsAdjacentEdges;   // A map of a  node
//																		// with a corresponding
//																		// adjacent  edge
//
//	private HashMap<Node, JComponent> nodeDisplayComponent = new HashMap<Node, JComponent>();
//
//	private NodeTextArea nodeTextArea;
//	private JScrollPane pane;
//	private PropertyChangeListener listener;
//
//	public GUIMediator() {
//		setLayout(null);
//	}
//
//	public JScrollPane getComponent() {
//		if (pane == null)
//			pane = new JScrollPane(this);
//		return pane;
//	}
//
//	/**
//	 * for debugging
//	 */
//	private void displayLevelsAndNodes(){
//		for (int i = 0; i < levels.size(); i++) {
//			BFSLevel level = levels.elementAt(i);
//			System.out.println("Level Number " + (i + 1) + ": ");
//			Vector<Node> nds = level.getNodes();
//			for (int j = 0; j < nds.size(); j++) {
//				Node nd = nds.elementAt(j);
//				System.out.print(nd.getName() + ", ");
//			}
//			System.out.println("");
//		}
//	}
//	
//	public Dimension updateDrawParameters(DiGraph graph, Node startNode,
//			Graphics grps) {
//		// Set the graphics first
//		// graphics = grps;
//
//		//System.out.println("**************** IN UPDATE DRAW PARAMETERS, CFG IS **********");
//		//((ControlFlowGraph) graph).displayFlowGraph();
//		
//		graphics2D = (Graphics2D) getGraphics();
//		// font = graphics.getFont();
//
//		// Calculate the drawing parameters
//		controlFlowGraph = (ControlFlowGraph) graph;
//		nodeVsBackEdge = new HashMap<Node, BackEdge>();
//		setLevelsAndSizes(graph, startNode); // First pass, establishes the
//												// levels and the node sizes
//
//		//displayLevelsAndNodes();
//
//		traverseUpwards(); // First part of the second pass; traverses upwards,
//							// determining positions
//		traverseDownwards(); // Second part of the second pass; traverses
//								// downwards, determining positions
//
//		updateXOffSetsOfNodes();
//
//		edgeParametersList = new Vector<EdgeParameters>();
//		nodeVsTriangleParams = new HashMap<Node, TriangleParameters>();
//
//		nodesVsAdjacentEdges = new HashMap<Node, Vector<AdjacentEdge>>();
//
//		maxTriangleHeight = verticalGap * triangleHeightFraction;
//		maxTriangleHeightForAngledLine = verticalGap
//				* angledTriangleHeightFraction;
//
//		setEdgePositions();
//
//		// Return the size of the graph to be drawn
//		int graphHeight = topMargin;
//		int numLevels = levels.size();
//		for (int i = 0; i < numLevels; i++) {
//			BFSLevel level = levels.elementAt(i);
//			Integer heightInt = levelsVsHeights.get(level);
//			graphHeight += heightInt.intValue() + verticalGap;
//		}
//
//		int graphWidth = 0;
//		for (int i = 0; i < numLevels; i++) {
//			BFSLevel level = levels.elementAt(i);
//			Vector nodes = level.getNodes();
//			int numNodesInLevel = nodes.size();
//			Node lastNodeInLevel = (Node) nodes.elementAt(numNodesInLevel - 1);
//			DrawingParameters dpLastNodeInLevel = nodesVsDrawingParameters
//					.get(lastNodeInLevel);
//			int xPosOfLastNode = dpLastNodeInLevel.getXPosition();
//			Dimension sizeOfLastNode = dpLastNodeInLevel.getSize();
//			int endXPosOfLevel = xPosOfLastNode + sizeOfLastNode.width;
//			if (endXPosOfLevel > graphWidth) {
//				graphWidth = endXPosOfLevel;
//			}
//		}
//
//		return new Dimension(graphWidth + 10, graphHeight);
//	}
//
//	public void setLevelsAndSizes(DiGraph graph, Node startNode) {
//		// BFS traversal
//		levels = new Vector<BFSLevel>(); // Create a list of levels
//		nodesVsDrawingParameters = new HashMap<Node, DrawingParameters>(); // Create
//																			// a
//																			// map
//																			// of
//																			// nodes
//																			// against
//																			// thier
//																			// sizes
//		levelsVsHeights = new HashMap<BFSLevel, Integer>();
//
//		HashSet<Node> visitedNodes = new HashSet<Node>(); // Set of visited
//															// nodes
//
//		// Create the first level and place the start node in it; add the first
//		// level to
//		// the list of levels
//		BFSLevel bfsLevel0 = new BFSLevel();
//		bfsLevel0.addNode(startNode);
//		levels.add(bfsLevel0);
//		int levelNum = 0;
//		BFSLevel currLevel = bfsLevel0;
//
//		// Calculate the size of the start node and store it. Also store the
//		// height of the node
//		Dimension sizeOfStartNode = getSize(startNode);
//		int heightofStartNode = sizeOfStartNode.height;
//		levelsVsHeights.put(bfsLevel0, new Integer(heightofStartNode));
//		DrawingParameters dpStartNode = new DrawingParameters();
//		dpStartNode.setSize(sizeOfStartNode);
//		nodesVsDrawingParameters.put(startNode, dpStartNode);
//
//		int maxNumNodes = 1;
//		outer: while (!currLevel.isEmpty()) {
//			BFSLevel nextLevel = new BFSLevel(); // Create the next level
//			// levels.add(nextLevel); // and add it to the list of levels
//
//			Vector<Node> currLevelNodes = currLevel.getNodes();
//			int numNodesInCurrLevel = currLevelNodes.size();
//
//			// Traverse through each node at the current level
//			int maxHeightOfNextLayer = 0;
//			for (int i = 0; i < numNodesInCurrLevel; i++) {
//				Node node = currLevelNodes.elementAt(i);
//				
//				if(node.getName().equals("L12")){
//					int m = 0;
//				}
//
//				Vector outgoingEdges = graph.getOutgoingEdges(node);
//				int numOutgoingEdges = 0;
//
//				if (outgoingEdges != null)
//					numOutgoingEdges = outgoingEdges.size();
//				if (numOutgoingEdges == 2) {
//					CFEdge leftEdge = (CFEdge) outgoingEdges.elementAt(0);
//					CFEdge rightEdge = (CFEdge) outgoingEdges.elementAt(1);
//
//					Node leftChildNode = leftEdge.getOppositeNode(node);
//					Node rightChildNode = rightEdge.getOppositeNode(node);
//
//					if (!crossEdge(leftChildNode, rightChildNode)) {
//						// The child nodes have no edge between them; add them
//						// to the next level if they are
//						// already not in a preceding level
//						BFSLevel destLevelLeft = nodeInLevelOrPrecedingLevels(
//								leftChildNode, levelNum);
//						if (destLevelLeft == null)
//							maxHeightOfNextLayer = addNodeToLevel(nextLevel,
//									leftChildNode, maxHeightOfNextLayer,
//									visitedNodes);
//						else {
//							BackEdge backEdge = new BackEdge(levelNum,
//									leftChildNode, destLevelLeft);
//							System.out.println("Found a back edge from: "
//									+ node.getName() + " to "
//									+ leftChildNode.getName());
//							nodeVsBackEdge.put(node, backEdge);
//						}
//
//						BFSLevel destLevelRight = nodeInLevelOrPrecedingLevels(
//								rightChildNode, levelNum);
//						if (destLevelRight == null)
//							maxHeightOfNextLayer = addNodeToLevel(nextLevel,
//									rightChildNode, maxHeightOfNextLayer,
//									visitedNodes);
//						else {
//							BackEdge backEdge = new BackEdge(levelNum,
//									rightChildNode, destLevelRight);
//							System.out.println("Found a back edge from: "
//									+ node.getName() + " to "
//									+ rightChildNode.getName());
//							nodeVsBackEdge.put(node, backEdge);
//						}
//					}
//				} else if (numOutgoingEdges == 1) {
//					CFEdge outEdge = (CFEdge) outgoingEdges.elementAt(0);
//					Node childNode = outEdge.getOppositeNode(node);
//
//					BFSLevel destLevel = nodeInLevelOrPrecedingLevels(
//							childNode, levelNum);
//					if (destLevel != null) { // Source node is in a preceding
//												// level
//						BackEdge backEdge = new BackEdge(levelNum, childNode,
//								destLevel);
//						System.out.println("Found a back edge from: "
//										+ node.getName() + " to "
//										+ childNode.getName());
//						nodeVsBackEdge.put(node, backEdge);
//					} else {
//						// Check the number of parents of the child node; if
//						// there are two of them,
//						// identify the other parent and ensure that this other
//						// parent is in the same level
//						// as the current node or in some level preceding it.
//						Vector edgesFromAbove = childNode.getInComingEdges();
//						if (edgesFromAbove.size() == 2) {
//							// The child node has two parents; get the other
//							// parent of this child node
//							Node otherParent = null;
//
//							CFEdge leftEdgeFromAbove = (CFEdge) edgesFromAbove.elementAt(0);
//							CFEdge rightEdgeFromAbove = (CFEdge) edgesFromAbove.elementAt(1);
//
//							if (node == leftEdgeFromAbove.getOppositeNode(childNode))
//								otherParent = rightEdgeFromAbove.getOppositeNode(childNode);
//							else
//								otherParent = leftEdgeFromAbove.getOppositeNode(childNode);
//
//							// If this other parent belongs to the current level
//							// or a level above it add the child node to the next level
//							if (nodeInLevelOrPrecedingLevels(otherParent,
//									levelNum) != null) {
//								// Add the child node to the next level
//								maxHeightOfNextLayer = addNodeToLevel(
//										nextLevel, childNode,
//										maxHeightOfNextLayer, visitedNodes);
//							} else {
//								if (pathExists(childNode, otherParent)) {
//									// Must be a cycle
//									maxHeightOfNextLayer = addNodeToLevel(
//											nextLevel, childNode,
//											maxHeightOfNextLayer, visitedNodes);
//								}
//							}
//						} else {
//							// The current node is the only parent of this child
//							// node
//							maxHeightOfNextLayer = addNodeToLevel(nextLevel,
//									childNode, maxHeightOfNextLayer,
//									visitedNodes);
//						}
//
//						// If all nodes have been visited, exit from the outer
//						// loop
//						if (visitedNodes.size() == controlFlowGraph
//								.getNumNodes()) {
//							currLevel = nextLevel; // Set the current layer to
//													// the next layer
//							levelsVsHeights.put(currLevel, new Integer(
//									maxHeightOfNextLayer));
//							break outer;
//						}
//					}
//				}
//			} // for loop (number of nodes in level
//
//			if (nextLevel.getNodes().size() > 0)
//				levels.add(nextLevel);
//
//			levelNum++;
//			currLevel = nextLevel; // Set the current layer to the next layer
//			levelsVsHeights.put(currLevel, new Integer(maxHeightOfNextLayer));
//
//			// Set the maximum number of nodes
//			Vector<Node> nodesInLevel = currLevel.getNodes();
//			int numNodesInLevel = nodesInLevel.size();
//			if (numNodesInLevel > maxNumNodes)
//				maxNumNodes = numNodesInLevel;
//		} // while loop for levels
//
//		// The end node might not be in the last level; if so, remove it from
//		// the level in which it exists
//		// and add it to the last level
//		// BFSLevel endNodeLevel = levels.elementAt(endNodeLevelIndex);
//
//		setIndexOfLowestLevelWithMaxNodes(maxNumNodes); // Set the index of the
//														// widest layer
//		maxNumNodesInLevel = maxNumNodes;
//	}
//
//	/*
//	 * Returns true if the destNode is encountered in any path from srcNode to
//	 * the end node. Else returns false
//	 */
//	private boolean pathExists(Node srcNode, Node destNode) {
//		ConnectivityTestDFS connectivityTestDFS = new ConnectivityTestDFS();
//		connectivityTestDFS.setDestinationNode(destNode);
//		return ((Boolean) connectivityTestDFS
//				.execute(controlFlowGraph, srcNode)).booleanValue();
//	}
//
//	/**
//	 * Adds a node to the given level only if the node has not already been
//	 * visited. Sets the size of the node in the nodeVsSizes map. Returns the
//	 * height of the node if it is taller than the height passed to it; else
//	 * returns the height passed to it.
//	 *
//	 * @param level
//	 * @param node
//	 * @param currMaxHeight
//	 * @param visitedNodes
//	 * @return
//	 */
//	private int addNodeToLevel(BFSLevel level, Node node, int currMaxHeight,
//			HashSet<Node> visitedNodes) {
//		if (!visitedNodes.contains(node)) {
//			level.addNode(node);
//			visitedNodes.add(node);
//			Dimension size = getSize(node);
//			int heightOfNode = size.height;
//			if (heightOfNode > currMaxHeight) {
//				currMaxHeight = heightOfNode;
//			}
//			DrawingParameters drawingParameters = new DrawingParameters();
//			drawingParameters.setSize(size);
//			nodesVsDrawingParameters.put(node, drawingParameters);
//		}
//		return currMaxHeight;
//	}
//
//	private Dimension getSize(Node node) {
//		ControlFlowNode cfNode = (ControlFlowNode) node;
//		Dimension nodeDimension = new Dimension();
//		Vector instructions = cfNode.getInstructions();
//		int maxWidth = 0;
//		int totalHeight = 5;
//		int numIns = instructions.size();
//		for (int i = 0; i < numIns; i++) {
//			AssemblyInstruction ins = (AssemblyInstruction) instructions
//					.elementAt(i);
//			
//			String insStr = ins.toString();
//			System.out.println("INS = " + insStr);
//			insStr = insStr.trim(); // Removes the new line at the end, if
//									// any
//			Dimension strDim = getStringRenderSize(insStr);
//
//			if (strDim.width > maxWidth)
//				maxWidth = strDim.width;
//			totalHeight += strDim.height + verticalGapBetweenIns;			
//		}
//
//		nodeDimension.height = totalHeight - verticalGapBetweenIns + 2;
//		nodeDimension.width = maxWidth + instrLeftMargin + instrRightMargin;
//
//		return nodeDimension;
//	}
//
//	/**
//	 * Returns the size as a Dimension of a string that is rendered on the
//	 * screen using the given graphics object and the font
//	 */
//	private Dimension getStringRenderSize(String str) {
//		Dimension stringDimension = new Dimension();
//
//		FontRenderContext frc = graphics2D.getFontRenderContext();
//		TextLayout tl = new TextLayout(str, getFont(), frc);
//
//		Rectangle2D rect = tl.getBounds();
//		stringDimension.width = (int) rect.getWidth();
//		stringDimension.height = (int) rect.getHeight();
//
//		return stringDimension;
//
//	}
//
//	/**
//	 * Returns a flag indicating whether or not the two specified node have a
//	 * direct edge between them.
//	 *
//	 * @param node
//	 * @param otherNode
//	 * @return
//	 */
//	private boolean crossEdge(Node node, Node otherNode) {
//		Vector nodePreds = node.getPredecessors();
//		if (nodePreds.contains(otherNode))
//			return true;
//
//		Vector otherNodePreds = otherNode.getPredecessors();
//		if (otherNodePreds.contains(node))
//			return true;
//
//		return false;
//	}
//
//	/**
//	 * This function returns the level at which the node exists. It starts the
//	 * search from the level number that has been passed. If the node is not
//	 * found, null is returned
//	 *
//	 * @param node
//	 * @param startLevelNum
//	 * @return
//	 */
//	private BFSLevel nodeInLevelOrPrecedingLevels(Node node, int startLevelNum) {
//		for (int i = startLevelNum; i >= 0; i--) {
//			BFSLevel lvl = levels.elementAt(i);
//			if (lvl.containsNode(node))
//				return lvl;
//		}
//		return null;
//	}
//
//	/**
//	 * This function executes the second pass of actually drawing the graph. It
//	 * starts with the widest level (level containing the maximum number of
//	 * nodes) and traverses "upwards" until it reaches the start node. Then it
//	 * traverses "downward" until it reaches the end node.
//	 *
//	 */
//	private void traverseUpwards() {
//		// Start with the lowest level that has the maximum number of nodes and
//		// traverse upwards
//
//		for (int a = indexOfLowestLevelWithMaxNodes; a >= 0; a--) {
//			BFSLevel currLevel = levels.elementAt(a);
//			int yOffSet = getVerticalDistanceForLayer(a);
//			Vector<Node> nodesInLevel = currLevel.getNodes();
//			int numnNodesInLevel = nodesInLevel.size();
//
//			// Iterate over the nodes in the current level
//			if (a == indexOfLowestLevelWithMaxNodes) {
//				// Iterate over nodes in the level that is the lowest and also
//				// contains the maximum number of nodes
//				setPositionsForLevelWithMaxNodes(currLevel, yOffSet, 0,
//						nodesInLevel, indexOfLowestLevelWithMaxNodes);
//			} else if (numnNodesInLevel == maxNumNodesInLevel) {
//				// Iterate over nodes in the level has the maximum number of
//				// nodes, but is the lowest level with those number of nodes
//				int startOffSet = getStartXOffSetForInnerLevelWithMaxNodes(
//						nodesInLevel, a);
//				setPositionsForLevelWithMaxNodes(currLevel, yOffSet,
//						startOffSet, nodesInLevel, a);
//			} else {
//				// Iterate over nodes in the level; positions of nodes in this
//				// level depend upon
//				// the positions of the successor nodes
//				sePositionsForNodesInLevel(currLevel, yOffSet, a, nodesInLevel);
//			}
//		}
//	}
//
//	private void setPositionsForLevelWithMaxNodes(
//			BFSLevel lowestLevelWithMaxNodes, int yOffSet, int startXOffset,
//			Vector<Node> nodesInLevel, int levelIndex) {
//		int xOffSet = startXOffset; // Initialize xOffSet
//		int numnNodesInLevel = nodesInLevel.size();
//
//		for (int i = 0; i < numnNodesInLevel; i++) {
//			ControlFlowNode cfNode = (ControlFlowNode) nodesInLevel
//					.elementAt(i);
//
//			// Set the nodesVsDrawingParameters map
//			DrawingParameters drawingParameters = nodesVsDrawingParameters
//					.get(cfNode);
//			drawingParameters.setYPosition(yOffSet);
//			drawingParameters.setXPosition(xOffSet);
//			nodesVsDrawingParameters.put(cfNode, drawingParameters);
//
//			// Update the xOffSet for the next node in this level
//			Vector<Node> preds = cfNode.getPredecessors();
//			Vector<Node> sucss = cfNode.getSuccessors();
//			if (preds.size() == 1) {
//				// Get the parent (A node that has a single parent must have the
//				// parent in the immediate preceding level)
//				ControlFlowNode cfNodeParent = (ControlFlowNode) preds
//						.elementAt(0);
//
//				if (isSingleParentRightDiagonal(cfNodeParent, cfNode,
//						levelIndex)) {
//					xOffSet = getXOffSetForNextNode(cfNode, sucss, xOffSet,
//							cfNodeParent, levelIndex);
//				} else {
//					int tempXOffSet = xOffSet
//							+ drawingParameters.getSize().width + horizontalGap; // Temporary
//																					// xOffSet
//
//					if (i + 1 < numnNodesInLevel - 1) {
//						// The next node is not the last node; try to get the xOffSet of this node
//						// from it's successor's information
//						ControlFlowNode cfNodeNext = (ControlFlowNode) nodesInLevel.elementAt(i + 1);
//						Vector<Node> succsOfNextNode = cfNodeNext.getSuccessors();
//
//						int tempXOffSetFromSuccInfo = 0;
//						if (succsOfNextNode.size() == 1) {
//							ControlFlowNode cfChildNodeOfNext = (ControlFlowNode) succsOfNextNode.elementAt(0);
//							DrawingParameters dpCfChildNodeOfNext = nodesVsDrawingParameters.get(cfChildNodeOfNext);
//
//							if (isSingleSuccessorRightDiagonal(cfNodeNext,
//									cfChildNodeOfNext, levelIndex)) {
//								DrawingParameters dpNodeNext = nodesVsDrawingParameters.get(cfNodeNext);
//								tempXOffSetFromSuccInfo = dpCfChildNodeOfNext.getXPosition()
//										- dpNodeNext.getSize().width;
//
//							} else if (isSingleSuccessorLeftDiagonal(
//									cfNodeNext, cfChildNodeOfNext,levelIndex + 1)) {
//								tempXOffSetFromSuccInfo = dpCfChildNodeOfNext.getXPosition()
//										+ dpCfChildNodeOfNext.getSize().width;
//							}
//
//							if (tempXOffSetFromSuccInfo > tempXOffSet) {
//								xOffSet = tempXOffSetFromSuccInfo;
//							} else {
//								xOffSet = tempXOffSet;
//							}
//						} else {
//							// TODO - Get info from successor info if the next node has two children
//						}
//					}
//					else{
//						// Next node is the last node
//						xOffSet = tempXOffSet;
//					}
//				}
//			} else if (preds.size() == 2) {
//				ControlFlowNode cfNodeRightParent = (ControlFlowNode) preds
//						.elementAt(1);
//				BFSLevel precedingLevel = levels.elementAt(levelIndex - 1);
//				if (precedingLevel.containsNode(cfNodeRightParent)) {
//					// The parent node is to the right on the level immediately
//					// preceding the current node
//					xOffSet = getXOffSetForNextNode(cfNode, sucss, xOffSet,
//							cfNodeRightParent, levelIndex);
//				} else
//					xOffSet += horizontalGap;
//			}
//		}
//	}
//
//	/**
//	 * Sets the positions for nodes in a level that has lesser than the maximum
//	 * number of nodes in any level during the upward traversal of the
//	 * control-flow graph
//	 *
//	 */
//	private void sePositionsForNodesInLevel(BFSLevel level, int yOffset,
//			int currLevelIndex, Vector<Node> nodesInLevel) {
//		int numNodesInLevel = nodesInLevel.size();
//
//		for (int i = 0; i < numNodesInLevel; i++) {
//			boolean cannotFindXPosFromSuccInfo = false; // Flag to check if the
//														// xpos of the current
//														// node can be
//			// found from successor information
//			Node currNode = nodesInLevel.elementAt(i);
//			DrawingParameters dpNode = nodesVsDrawingParameters.get(currNode);
//			dpNode.setYPosition(yOffset);
//			Vector<Node> sucss = currNode.getSuccessors();
//
//			int xPosOfNodeFromSuccInfo = 0; // To be determined
//
//			int numSucss = sucss.size();
//			if (numSucss == 1) {
//				Node succ = sucss.elementAt(0);
//				BFSLevel nextLevel = levels.elementAt(currLevelIndex + 1);
//				if (nextLevel.containsNode(succ)) {
//					DrawingParameters dpSucc = nodesVsDrawingParameters
//							.get(succ);
//					int succXPos = dpSucc.getXPosition();
//
//					if (isSingleSuccessorRightDiagonal(currNode, succ,
//							currLevelIndex)) {
//						Dimension nodeSize = dpNode.getSize();
//						xPosOfNodeFromSuccInfo = succXPos - nodeSize.width;
//					} else if (isSingleSuccessorLeftDiagonal(currNode, succ,
//							currLevelIndex)) {
//						Dimension succSize = dpSucc.getSize();
//						xPosOfNodeFromSuccInfo = succXPos + succSize.width;
//					} else { // Node is positioned directly above the
//								// successor
//						xPosOfNodeFromSuccInfo = positionNodeAboveSuccessor(
//								dpNode, dpSucc);
//					}
//				} else {
//					cannotFindXPosFromSuccInfo = true;
//				}
//			} else if (numSucss == 2) {
//				Node firstSucc = sucss.elementAt(0);
//				Node secondSucc = sucss.elementAt(1);
//				DrawingParameters dpFirstSucc = nodesVsDrawingParameters
//						.get(firstSucc);
//				int xPosFirstSucc = dpFirstSucc.getXPosition();
//				int widthOfFirstSucc = dpFirstSucc.getSize().width;
//				BFSLevel nextLevel = levels.elementAt(currLevelIndex + 1);
//				DrawingParameters dpSecondSucc = nodesVsDrawingParameters
//						.get(secondSucc);
//				int xPosSecondSucc = dpSecondSucc.getXPosition();
//
//				if (nextLevel.containsNode(firstSucc)
//						&& nextLevel.containsNode(secondSucc)) {
//					// Both successors are in the next level; center the node
//					// between the two sucessors
//					int distBtnSuccs = xPosSecondSucc
//							- (xPosFirstSucc + widthOfFirstSucc);
//					int nodeWidth = dpNode.getSize().width;
//
//					int midway = xPosFirstSucc + widthOfFirstSucc
//							+ (int) (distBtnSuccs / 2.0);
//					xPosOfNodeFromSuccInfo = midway - (int) (nodeWidth / 2.0);
//				} else {
//					if (nextLevel.containsNode(firstSucc)) {
//						// Calculate the xPosition based on the successor on the
//						// left side
//						Dimension firstSuccSize = dpFirstSucc.getSize();
//						xPosOfNodeFromSuccInfo = xPosFirstSucc
//								+ firstSuccSize.width;
//					} else if (nextLevel.containsNode(secondSucc)) {
//						Dimension nodeSize = dpNode.getSize();
//						xPosOfNodeFromSuccInfo = xPosSecondSucc
//								- nodeSize.width;
//					} else {
//						cannotFindXPosFromSuccInfo = true;
//					}
//				}
//			}
//
//			if (cannotFindXPosFromSuccInfo) {
//				// The position of the current node could not be determined from
//				// the successor's information
//				// Position it immediately after the previous node in the level
//				// or at 0 if it is first node
//				System.out.println("Found a node with unknown x postion");
//				if (i == 0)
//					dpNode.setXPosition(0);
//				else {
//					DrawingParameters dpPrevNode = nodesVsDrawingParameters
//							.get(nodesInLevel.elementAt(i - 1));
//					int xPos = dpPrevNode.getXPosition()
//							+ dpPrevNode.getSize().width + horizontalGap;
//					dpNode.setXPosition(xPos);
//				}
//			} else {
//				if (i == 0)
//					dpNode.setXPosition(xPosOfNodeFromSuccInfo);
//				else {
//					DrawingParameters dpPrevNode = nodesVsDrawingParameters
//							.get(nodesInLevel.elementAt(i - 1));
//					int xPrevNodeAdvance = dpPrevNode.getXPosition()
//							+ dpPrevNode.getSize().width + horizontalGap;
//					if (xPrevNodeAdvance < xPosOfNodeFromSuccInfo)
//						dpNode.setXPosition(xPosOfNodeFromSuccInfo);
//					else
//						dpNode.setXPosition(xPrevNodeAdvance);
//				}
//			}
//		}
//	}
//
//	/**
//	 * Determines the x-offset of the node whose drawing parameters are passed
//	 * in the first argument. The x-offset is calculated based on the successor
//	 * of the node whose drawing parameters are passed in the second argument.
//	 *
//	 * @param dpNode
//	 * @param dpSucc
//	 * @return
//	 */
//	private int positionNodeAboveSuccessor(DrawingParameters dpNode,
//			DrawingParameters dpSucc) {
//		int xPosOfNode = 0;
//
//		int nodeWidth = dpNode.getSize().width;
//		int succNodeWidth = dpSucc.getSize().width;
//		int succXPos = dpSucc.getXPosition();
//
//		if (nodeWidth < succNodeWidth) {
//			int widthDiff = succNodeWidth - nodeWidth;
//			xPosOfNode = succXPos + (int) widthDiff / 2;
//		} else if (nodeWidth > succXPos) {
//			int widthDiff = nodeWidth - succXPos;
//			xPosOfNode = succXPos - (int) widthDiff / 2;
//		} else
//			xPosOfNode = succXPos;
//
//		return xPosOfNode;
//	}
//
//	/**
//	 * Determines the x-offset of the node whose drawing parameters are passed
//	 * in the first argument. The x-offset is calculated based on the parent of
//	 * the node whose drawing parameters are passed in the second argument.
//	 *
//	 * @param dpNode
//	 * @param dpParent
//	 * @return
//	 */
//	private int positionNodeBelowParent(DrawingParameters dpNode,
//			DrawingParameters dpParent) {
//		int xOffset = 0;
//
//		int nodeSize = dpNode.getSize().width;
//		int parentNodeSize = dpParent.getSize().width;
//		int xOffsetParent = dpParent.getXPosition();
//
//		if (nodeSize < parentNodeSize) {
//			int widthDiff = parentNodeSize - nodeSize;
//			xOffset = xOffsetParent + (int) widthDiff / 2;
//		} else if (nodeSize > parentNodeSize) {
//			int widthDiff = nodeSize - parentNodeSize;
//			xOffset = xOffsetParent - (int) widthDiff / 2;
//		} else
//			xOffset = xOffsetParent;
//
//		return xOffset;
//	}
//
//	/**
//	 * Returns the x position of the left-most node in a level that has that
//	 * maximum number of nodes, but is NOT the lowest level with the maximum
//	 * number of nodes
//	 */
//
//	private int getStartXOffSetForInnerLevelWithMaxNodes(
//			Vector<Node> nodesInLevel, int currLevel) {
//		int xOffSet = 0;
//
//		// Establish the xOffSet for the left-most node in the level
//		ControlFlowNode cfNodeLeftMost = (ControlFlowNode) nodesInLevel
//				.elementAt(0);
//		DrawingParameters drawingParametersOfNode = nodesVsDrawingParameters
//				.get(cfNodeLeftMost);
//		Vector<Node> sucssOfLeftMostNode = cfNodeLeftMost.getSuccessors();
//
//		int numSucss = sucssOfLeftMostNode.size();
//		if (numSucss == 1) { // This node has only one successor
//			Node singleSuccessor = sucssOfLeftMostNode.elementAt(0);
//			BFSLevel nextLevel = levels.elementAt(currLevel + 1);
//			if (nextLevel.containsNode(singleSuccessor)) { // The successor is
//															// in the next level
//				DrawingParameters drawingParametersOfSucc = nodesVsDrawingParameters
//						.get(singleSuccessor);
//				int xOffSetOfSucc = drawingParametersOfSucc.getXPosition();
//				if (isSingleSuccessorRightDiagonal(cfNodeLeftMost,
//						singleSuccessor, currLevel)) { // Successor is to the
//														// right of this node
//					xOffSet = xOffSetOfSucc
//							- drawingParametersOfNode.getSize().width;
//				} else { // This node should be directly above the successor
//							// node
//					xOffSet = positionNodeAboveSuccessor(
//							drawingParametersOfNode, drawingParametersOfSucc);
//				}
//			} else {
//				// Check if the sucessor is in a level between the "base level"
//				// and the current level
//				int succLevel = -1;
//				for (int i = indexOfLowestLevelWithMaxNodes; i > currLevel + 1; i--) {
//					BFSLevel level = levels.elementAt(i);
//					if (level.containsNode(singleSuccessor)) {
//						succLevel = i;
//						break;
//					}
//				}
//				if (succLevel > 0) { // The successor node is at a level
//										// between the lowest level with maximum
//										// number
//					// of nodes and the current level; get the minimum position
//					// of the left-most nodes
//					// in all levels between this node and the current node
//					int minXOffSet = 0;
//					for (int i = succLevel; i >= currLevel + 1; i--) {
//						BFSLevel lvl = levels.elementAt(i);
//						Node leftmostNode = lvl.getNodeAt(0);
//						int XoffSetOfLeftMostNode = nodesVsDrawingParameters
//								.get(leftmostNode).getXPosition();
//						if (XoffSetOfLeftMostNode < minXOffSet)
//							minXOffSet = XoffSetOfLeftMostNode;
//					}
//					xOffSet = minXOffSet
//							- drawingParametersOfNode.getSize().width;
//				} else {
//					// The xOffSet of the leftmost node in this level cannot be
//					// determined from the successor
//					// positions; default it to 0
//					xOffSet = 0;
//				}
//			}
//		}
//		if (numSucss == 2) { // This node has two successors
//			Node leftMostSuccessor = sucssOfLeftMostNode.elementAt(0);
//			BFSLevel nextLevel = levels.elementAt(currLevel + 1);
//			if (nextLevel.containsNode(leftMostSuccessor)) { // The successor
//																// is in the
//																// next level
//				DrawingParameters dpLeftMostSucc = nodesVsDrawingParameters
//						.get(leftMostSuccessor);
//				int xOffSetOfLeftMostSucc = dpLeftMostSucc.getXPosition();
//				xOffSet = xOffSetOfLeftMostSucc
//						+ dpLeftMostSucc.getSize().width;
//			}
//
//		}
//		return xOffSet;
//	}
//
//	/**
//	 * Returns the horizontal offset for the next node in the current level. The
//	 * sizes of the parent to the right and the successor to the right are
//	 * compared and the wider one returned.
//	 *
//	 * @param sucss
//	 * @param currOffSet
//	 * @param cfNodeParent
//	 * @return
//	 */
//	private int getXOffSetForNextNode(Node node, Vector sucss, int currOffSet,
//			Node cfNodeParent, int currLevel) {
//		// If this node also has a successor in the next level, increment the
//		// x-offset
//		// to the maximum among the widths of the predecessor and successor
//		int numSuccs = sucss.size();
//		int nodeWidth = getSize(node).width;
//		Dimension sizeOfParent = getSize(cfNodeParent);
//		if (numSuccs == 1) {
//			ControlFlowNode cfNodeSuccessor = (ControlFlowNode) sucss
//					.elementAt(0);
//			if (isSingleSuccessorRightDiagonal(node, cfNodeSuccessor, currLevel)) {
//				Dimension sizeOfSuccessor = getSize(cfNodeSuccessor);
//				if (sizeOfSuccessor.width > sizeOfParent.width)
//					currOffSet += nodeWidth + sizeOfSuccessor.width;
//				else
//					currOffSet += nodeWidth + sizeOfParent.width;
//			} else {
//				if (nodeInLevelOrPrecedingLevels(cfNodeSuccessor, currLevel - 1) != null)
//					// Successor is in a level higher than that of the current
//					// node
//					currOffSet += nodeWidth + sizeOfParent.width;
//				else
//					currOffSet += nodeWidth + horizontalGap;
//			}
//
//		} else if (numSuccs == 2) {
//			// This node has two successors; compare the size of the parent
//			// with the right child
//			ControlFlowNode cfNodeSuccessor = (ControlFlowNode) sucss
//					.elementAt(1);
//			BFSLevel nextLevel = levels.elementAt(currLevel + 1);
//			if (nextLevel.containsNode(cfNodeSuccessor)) {
//				Dimension sizeOfSuccessor = getSize(cfNodeSuccessor);
//				if (sizeOfSuccessor.width > sizeOfParent.width)
//					currOffSet += nodeWidth + sizeOfSuccessor.width;
//				else
//					currOffSet += nodeWidth + sizeOfParent.width;
//			} else
//				currOffSet += sizeOfParent.width + horizontalGap;
//		}
//		return currOffSet;
//	}
//
//	private boolean isSingleParentRightDiagonal(Node cfNodeParent, Node cfNode,
//			int levelNum) {
//		if (levelNum == 0)
//			return false; // First level
//
//		// Not first level; check
//		BFSLevel prevLevel = levels.elementAt(levelNum - 1);
//
//		if (prevLevel.containsNode(cfNodeParent)) {
//			Vector childNodesOfParent = cfNodeParent.getSuccessors();
//			int numChildNodesOfParent = childNodesOfParent.size();
//			if (numChildNodesOfParent == 1)
//				return false; // Only child of the parent
//
//			// Parent has two children; check if the node is the left child of
//			// the parent
//			Node leftChildOfParent = (Node) childNodesOfParent.elementAt(0);
//			if (leftChildOfParent == cfNode)
//				return true;
//
//		}
//		return false; // parent is not in the level above cfNode
//	}
//
//	private boolean isSingleParentLeftDiagonal(Node cfNodeParent, Node cfNode,
//			int levelNum) {
//		if (levelNum == 0)
//			return false; // First level
//
//		// Not first level; check
//		BFSLevel prevLevel = levels.elementAt(levelNum - 1);
//
//		if (prevLevel.containsNode(cfNodeParent)) {
//			Vector childNodesOfParent = cfNodeParent.getSuccessors();
//			int numChildNodesOfParent = childNodesOfParent.size();
//			if (numChildNodesOfParent == 1)
//				return false; // Only child
//
//			// Parent has two children; check if the node is the right child of
//			// the parent
//			Node rightChildOfParent = (Node) childNodesOfParent.elementAt(1);
//			if (rightChildOfParent == cfNode)
//				return true;
//
//		}
//		return false; // parent is not in the level above cfNode
//	}
//
//	private boolean isSingleSuccessorRightDiagonal(Node node, Node successor,
//			int currLevel) {
//		// If the successor does not belong to the next level, return false
//		BFSLevel nextLevel = levels.elementAt(currLevel + 1);
//		if (!nextLevel.containsNode(successor))
//			return false;
//
//		// Successor is in the next level; if the leftmost parent of the
//		// sucessor is the node passed,
//		// return true
//		Vector predsOfSucc = successor.getPredecessors();
//		int numPredsOfSucc = predsOfSucc.size();
//		if (numPredsOfSucc == 1)
//			return false;
//		else if (numPredsOfSucc == 2) {
//			Node leftParentOfSucc = (Node) predsOfSucc.elementAt(0);
//			// Check if there is a back-edge from this parent to the successor
//			BackEdge backEdge = nodeVsBackEdge.get(leftParentOfSucc);
//			if (backEdge != null) {
//				Node target = backEdge.getDestNode();
//				if (target == successor) // this is a back edge
//					return false;
//			} else {
//				if (leftParentOfSucc == node)
//					return true;
//			}
//		}
//
//		return false;
//	}
//
//	private boolean isSingleSuccessorLeftDiagonal(Node node, Node successor,
//			int currLevel) {
//		// If the successor does not belong to the next level, return false
//		BFSLevel nextLevel = levels.elementAt(currLevel + 1);
//		if (!nextLevel.containsNode(successor))
//			return false;
//
//		// Successor is in the next level; if the rightmost parent of the
//		// sucessor is the node passed,
//		// return true
//		Vector predsOfSucc = successor.getPredecessors();
//		int numPredsOfSucc = predsOfSucc.size();
//		if (numPredsOfSucc == 1)
//			return false;
//		else if (numPredsOfSucc == 2) {
//			Node rightParentOfSucc = (Node) predsOfSucc.elementAt(1);
//			Node leftParentOfSucc = (Node) predsOfSucc.elementAt(0);
//			// Check if there is a back-edge from this parent to the successor
//			BackEdge backEdge = nodeVsBackEdge.get(leftParentOfSucc);
//			if (backEdge != null) {
//				Node target = backEdge.getDestNode();
//				if (target == successor) // this is a back edge
//					return false;
//			} else {
//				if (rightParentOfSucc == node)
//					return true;
//			}
//		}
//
//		return false;
//	}
//
//	/**
//	 * Returns the index of the "widest" layer. The widest layer is found as
//	 * follows: 1. There can be more than one layer with the same maximum
//	 * number. If there is only one such layer, return it's index 2. If there
//	 * exist more than one layer with maximum number of nodes, check the number
//	 * of children of the first node of each such layer. If there is only one
//	 * child, that level is one of the widest layers 3. Return the index of the
//	 * first level that satisfies points "1" and "2".
//	 *
//	 */
//
//	private void setIndexOfLowestLevelWithMaxNodes(int maxNodes) {
//		int numLevels = levels.size();
//		for (int i = numLevels - 1; i >= 0; i--) {
//			BFSLevel level = levels.elementAt(i);
//			Vector<Node> nodesInLevel = level.getNodes();
//			if (nodesInLevel.size() == maxNodes) {
//				indexOfLowestLevelWithMaxNodes = i;
//				break;
//			}
//		}
//	}
//
//	/**
//	 * Returns the vertical distance from the top of the drawing window to the
//	 * layer at the given index
//	 *
//	 * @param indexOfLevel
//	 * @return
//	 */
//
//	private int getVerticalDistanceForLayer(int indexOfLevel) {
//		int yOffSet = topMargin;
//		for (int i = 0; i < indexOfLevel; i++) {
//			BFSLevel level = levels.elementAt(i);
//			int heightOfLevel = levelsVsHeights.get(level).intValue();
//			yOffSet += heightOfLevel + verticalGap;
//		}
//		return yOffSet;
//	}
//
//	/**
//	 * Draws the control flow node by rendering each instruction in the node as
//	 * a string and then drawing a bounding rectangle around the instructions
//	 */
//	private void drawNode(Node node, int xOffSet, int yOffSet) {
//		ControlFlowNode cfNode = (ControlFlowNode) node;
//		Vector instrs = cfNode.getInstructions();
//
//		String finalString = "";
//		int numInstrs = instrs.size();
//		int yOffsetForStr = yOffSet;
//		for (int i = 0; i < numInstrs; i++) {
//			AssemblyInstruction instr = (AssemblyInstruction) instrs
//					.elementAt(i);
//			String instrStr = instr.toString();
//			instrStr = instrStr.trim();
//			
//			finalString += " " + instrStr + "\n";	
//		}
//
//		drawingParameters = nodesVsDrawingParameters.get(node);
//		nodeTextArea = (NodeTextArea) nodeDisplayComponent.get(node);
//		if (recalculate) {
//			nodeTextArea.setText(finalString);
//		}
//		nodeTextArea.setBounds(xOffSet, yOffSet,
//				drawingParameters.getSize().width,
//				drawingParameters.getSize().height);
//		nodeTextArea.setMargin(new Insets(5, 5, 5, 5));
//		add(nodeTextArea);
//		// Draw the node (rectangle) now
//		// graphics2D.drawRect(xOffSet, yOffSet,
//		// drawingParameters.getSize().width,
//		// drawingParameters.getSize().height);
//	}
//
//	/**
//	 * Second part of the second pass; traverses downwards from the level
//	 * immediately after the lowest level with maximum number of nodes,
//	 * determing positions based on predecessor information.
//	 */
//	private void traverseDownwards() {
//		for (int i = indexOfLowestLevelWithMaxNodes + 1; i < levels.size(); i++) {
//			BFSLevel level = levels.elementAt(i);
//			int yOffSet = getVerticalDistanceForLayer(i); // y offset for this
//															// level
//
//			Vector<Node> nodesInLevel = level.getNodes();
//			int numNodesInLevel = nodesInLevel.size();
//			int xAdvance = 0; // Horizontal offset of the end of the previous
//								// node (to avoid collisions)
//			for (int j = 0; j < numNodesInLevel; j++) {
//				Node currNode = nodesInLevel.elementAt(j);
//				DrawingParameters dpNode = nodesVsDrawingParameters
//						.get(currNode);
//				int nodeSize = dpNode.getSize().width;
//				Vector preds = currNode.getPredecessors();
//				int numPreds = preds.size();
//				int xOffset = 0; // To be determined
//				if (numPreds == 1) {
//					Node predNode = (Node) preds.elementAt(0);
//					DrawingParameters dpParent = nodesVsDrawingParameters
//							.get(predNode);
//					int xOffsetParent = dpParent.getXPosition();
//					int parentNodeSize = dpParent.getSize().width;
//					if (isSingleParentRightDiagonal(predNode, currNode, i)) {
//						xOffset = xOffsetParent - nodeSize;
//					} else if (isSingleParentLeftDiagonal(predNode, currNode, i)) {
//						xOffset = xOffsetParent + parentNodeSize;
//					} else {// The node is directly below the parent; center it
//							// against the parent
//						xOffset = positionNodeBelowParent(dpNode, dpParent);
//					}
//				} else if (numPreds == 2) {
//					Node leftParent = (Node) preds.elementAt(0);
//					Node rightParent = (Node) preds.elementAt(1);
//
//					BFSLevel prevLevel = levels.elementAt(i - 1);
//					if (prevLevel.containsNode(leftParent)
//							&& prevLevel.containsNode(rightParent)) {
//						int indexOfLeftParent = prevLevel
//								.getIndexOfNode(leftParent);
//						int indexOfRightParent = prevLevel
//								.getIndexOfNode(rightParent);
//						if (indexOfRightParent == indexOfLeftParent + 1) {
//							// The two parents are adjacent to each other;
//							// center the node between the two
//							xOffset = positionSuccBtnParents(dpNode,
//									leftParent, rightParent);
//						} else {
//							// A sub-graph exists between the two parents;
//							// position the node directly
//							// below the left parent
//							xOffset = positionNodeBelowParent(dpNode,
//									nodesVsDrawingParameters.get(leftParent));
//						}
//					} else {
//						if (prevLevel.containsNode(leftParent)) {
//							DrawingParameters dpParent = nodesVsDrawingParameters
//									.get(leftParent);
//							int xOffsetLeftParent = dpParent.getXPosition();
//							int widthOfLeftParent = dpParent.getSize().width;
//							xOffset = xOffsetLeftParent + widthOfLeftParent;
//						} else if (prevLevel.containsNode(rightParent)) {
//							if (nodeInLevelOrPrecedingLevels(leftParent, i) == null) { // Left
//																						// parent
//																						// must
//																						// be a
//																						// cycle
//																						// start
//								xOffset = positionNodeBelowParent(dpNode,
//										nodesVsDrawingParameters
//												.get(rightParent));
//
//							} else {
//								DrawingParameters dpParent = nodesVsDrawingParameters
//										.get(rightParent);
//								int xOffsetLeftParent = dpParent.getXPosition();
//								int widthOfNode = dpNode.getSize().width;
//								xOffset = xOffsetLeftParent - widthOfNode;
//							}
//						}
//					}
//				}
//
//				// Assign the xOffset to the drawing parameter now
//				if (xOffset < xAdvance + horizontalGap)
//					xOffset = xAdvance + horizontalGap;
//
//				dpNode.setXPosition(xOffset);
//				dpNode.setYPosition(yOffSet);
//
//				// Update xAdvance
//				xAdvance = xOffset + dpNode.getSize().width;
//			}
//		}
//	}
//
//	/**
//	 * Positions the successor node whose drawing parameters are passed in the
//	 * first argument between the two parents
//	 *
//	 * @param leftParent
//	 * @param rightParent
//	 * @return
//	 */
//	private int positionSuccBtnParents(DrawingParameters dpSucc,
//			Node leftParent, Node rightParent) {
//		DrawingParameters dpLeftParent = nodesVsDrawingParameters
//				.get(leftParent);
//		DrawingParameters dpRightParent = nodesVsDrawingParameters
//				.get(rightParent);
//		int distanceBtnParents = dpRightParent.getXPosition()
//				- (dpLeftParent.getXPosition() + dpLeftParent.getSize().width);
//		int xOffset = dpLeftParent.getXPosition()
//				+ dpLeftParent.getSize().width
//				+ (int) (distanceBtnParents / 2.0)
//				- (int) (dpSucc.getSize().width / 2.0);
//
//		return xOffset;
//	}
//
//	private void setEdgePositions() {
//		// Start with the start node, traverse each level and establish the
//		// drawing parameters
//		// for the edges
//
//		int numLevels = levels.size();
//		for (int i = 0; i < numLevels; i++) {
//			BFSLevel level = levels.elementAt(i);
//			Vector<Node> nodesInLevel = level.getNodes();
//			int numNodesInLevel = nodesInLevel.size();
//			for (int j = 0; j < numNodesInLevel; j++) {
//				Node node = nodesInLevel.elementAt(j);
//
//				Node targetNode = null;
//				BackEdge backEdge = nodeVsBackEdge.get(node);
//				if (backEdge != null) { // Found a back edge from this node
//					targetNode = backEdge.getDestNode();
//				}
//
//				Vector<Node> succs = node.getSuccessors();
//				int numSuccs = succs.size();
//				for (int k = 0; k < numSuccs; k++) {
//					Node succ = succs.elementAt(k);
//
//					if (targetNode != null && targetNode == succ) // Draw the back edge
//						setBackEdgeParameters(node, backEdge);
//					else
//						setEdgeParameters(node, succ, i, j);
//
//				}
//			}
//		}
//	}
//
//	private void setEdgeParameters(Node srcNode, Node destNode,
//			int srcNodeLevelIndex, int srcNodeIndexInLevel) {
//
//		System.out.println("WAIT HEREE..... size of levels = " + levels.size());
//		System.out.println("WAIT HEREE..... the other = " + (srcNodeLevelIndex + 1));
//		if(levels.size() <= srcNodeIndexInLevel + 1){
//			System.out.println("WAIT HEREE..... WHATS GOING ON??");
//			return;
//		}
//		
//		BFSLevel nextLevel = levels.elementAt(srcNodeLevelIndex + 1); // Get the  next level
//
//		if (nextLevel.containsNode(destNode)) {
//			// The destination node is in the next level of the source node;a directed line
//			// must be drawn from the bottom center of the source node to the top center of the
//			// destination node
//
//			// Get the bottom mid-point of the source node
//			DrawingParameters dpSrcNode = nodesVsDrawingParameters.get(srcNode);
//			int xcpSrc = dpSrcNode.getXPosition()
//					+ (int) ((dpSrcNode.getSize().width) / 2);
//			int ycpSrc = dpSrcNode.getYPosition() + dpSrcNode.getSize().height;
//
//			Point srcPoint = new Point(xcpSrc, ycpSrc);
//
//			// Get the top center-point of the destination node
//			DrawingParameters dpDestNode = nodesVsDrawingParameters
//					.get(destNode);
//			int xcpDest = dpDestNode.getXPosition()
//					+ (int) ((dpDestNode.getSize().width) / 2);
//			int ycpDest = dpDestNode.getYPosition();
//
//			Point destPoint = new Point(xcpDest, ycpDest);
//
//			// If there is an intersection with the edge with the adjacent node, create a 
//			// forward Manhattan edge instead of a straight line
//			boolean edgeIntersectsWithNeighborNode = edgeIntersectsWithAdjacentNode(
//					srcNodeLevelIndex, srcNodeIndexInLevel, srcPoint, destPoint);
//			if (edgeIntersectsWithNeighborNode) {
//				createForwardManhattanEdge(srcNode, destNode, srcNodeLevelIndex);
//			} 
//			else {
//				// Calculate the triangle positions of the directed line
//				Line line = new Line();
//				line.setStartPoint(new Point(srcPoint));
//				line.setEndPoint(new Point(destPoint));
//				Vector<Line> lines = new Vector<Line>();
//				lines.add(line);
//				EdgeParameters edgeParameters = new EdgeParameters();
//				edgeParameters.setLines(lines);
//
//				// Check if the destination node has some incident edge whose
//				// triangle has already
//				// been calculated.
//				TriangleParameters triangleParams = nodeVsTriangleParams
//						.get(destNode);
//				Point[] trianglePositions = calculateTrianglePositions(
//						srcPoint, destPoint, triangleParams, destNode);
//
//				Vector<Point[]> triangles = new Vector<Point[]>();
//				triangles.addElement(trianglePositions);
//				edgeParameters.setTriangles(triangles);
//				edgeParametersList.addElement(edgeParameters);
//			}
//		} 
//		else {
//			// Destination node is not in the next level, must be more then one level below
//			EdgeParameters eps = createForwardManhattanEdge(srcNode, destNode,
//					srcNodeLevelIndex);
//			edgeParametersList.addElement(eps);
//		}
//	}
//
//	private boolean edgeIntersectsWithAdjacentNode(int srcLevelIndex,
//			int srcNodeIndexInLevel, Point srcPoint, Point destPoint) {
//		boolean edgeIntersectsWithNeighborNode = false;
//		BFSLevel srcNodeLevel = levels.get(srcLevelIndex);
//		Vector<Node> nodesInSrcNodeLevel = srcNodeLevel.getNodes();
//		if (srcPoint.x > destPoint.x) {
//			Node nodeLeftOfSrcNode = null;
//			if (srcNodeIndexInLevel > 0) { // Not the first node in this level
//				nodeLeftOfSrcNode = nodesInSrcNodeLevel
//						.elementAt(srcNodeIndexInLevel - 1);
//				DrawingParameters dpNodeLeftOfSrcNode = nodesVsDrawingParameters
//						.get(nodeLeftOfSrcNode);
//				double yDistThreshold = dpNodeLeftOfSrcNode.getYPosition()
//						+ dpNodeLeftOfSrcNode.getSize().height - srcPoint.y;
//				if (yDistThreshold <= 0)
//					return false;
//
//				double xDistThreshold = srcPoint.x
//						- (dpNodeLeftOfSrcNode.getXPosition() + dpNodeLeftOfSrcNode
//								.getSize().width);
//				double thresholdAngle = Math.asin(yDistThreshold
//						/ xDistThreshold);
//
//				double yDist = destPoint.y - srcPoint.y;
//				double xDist = srcPoint.x - destPoint.x;
//
//				double incidentAngle = Math.asin(yDist / xDist);
//				if (incidentAngle < thresholdAngle) {
//					edgeIntersectsWithNeighborNode = true;
//				}
//			}
//		} else if (srcPoint.x < destPoint.x) {
//			Node nodeRightOfSrcNode = null;
//			if (srcNodeIndexInLevel < nodesInSrcNodeLevel.size() - 1) { // Not
//																		// the
//																		// last
//																		// node
//																		// in
//																		// this
//																		// level
//				nodeRightOfSrcNode = nodesInSrcNodeLevel
//						.elementAt(srcNodeIndexInLevel + 1);
//				DrawingParameters dpNodeRightOfSrcNode = nodesVsDrawingParameters
//						.get(nodeRightOfSrcNode);
//				double xDistThreshold = dpNodeRightOfSrcNode.getXPosition()
//						- srcPoint.x;
//				double yDistThreshold = dpNodeRightOfSrcNode.getYPosition()
//						+ dpNodeRightOfSrcNode.getSize().height - srcPoint.y;
//				if (yDistThreshold <= 0)
//					return false;
//
//				double thresholdAngle = Math.asin(yDistThreshold
//						/ xDistThreshold);
//				double yDist = destPoint.y - srcPoint.y;
//				double xDist = destPoint.x - srcPoint.x;
//
//				double incidentAngle = Math.asin(yDist / xDist);
//				if (incidentAngle < thresholdAngle) {
//					edgeIntersectsWithNeighborNode = true;
//				}
//			}
//		}
//		return edgeIntersectsWithNeighborNode;
//	}
//
//	/**
//	 * Creates a Manhattan edge from source node to destination node when the
//	 * source node is above the the destination node
//	 */
//	private EdgeParameters createForwardManhattanEdge(Node srcNode,
//			Node destNode, int srcNodeLevelIndex) {
//		Vector<Line> lines = new Vector<Line>(); // Lines that are part of
//													// the Manhattan edge (to be
//													// determined)
//
//		DrawingParameters dpSrc = nodesVsDrawingParameters.get(srcNode);
//		int yStart = getVerticalDistanceForLayer(srcNodeLevelIndex)
//				+ dpSrc.getSize().height;
//		int xStart = dpSrc.getXPosition() + (int) (dpSrc.getSize().width / 2.0);
//		Line outLine1 = new Line();
//		outLine1.setStartPoint(new Point(xStart, yStart));
//		outLine1.setEndPoint(new Point(xStart, yStart
//				+ minDistanceOfEdgeFromNode));
//		yStart = yStart + minDistanceOfEdgeFromNode;
//		lines.add(outLine1);
//
//		boolean srcNodeIsToRight = true;		
//		DrawingParameters dpDest = nodesVsDrawingParameters.get(destNode);		
//		if (dpSrc.getXPosition() < dpDest.getXPosition()) { // Source node is to
//															// the right of the
//															// destination node
//			srcNodeIsToRight = false;
//		}
//
//		// Iterate through all levels between the source node and the
//		// destination node and find
//		// the rightmost node among those levels to which a path exists to the
//		// destination node (if the source node
//		// is to the right of the destination node) OR the leftmost node among
//		// those levels to which a
//		// path exists to the destination node(if the source node is to the left
//		// of the destination node)
//		BFSLevel frontierLevel = null;
//		Node frontierNode = null;
//		int currentLevelNum = srcNodeLevelIndex + 1;
//
//		while (true) {
//			BFSLevel currentLevel = levels.elementAt(currentLevelNum);
//			if (currentLevel.containsNode(destNode))
//				break;
//
//			// Get the left most (or right most) node in subgraph for this level
//			Vector<Node> nodesInLevel = currentLevel.getNodes();
//			Node frontierNodeInLevel = null;
//			int numNodes = nodesInLevel.size();
//			for (int i = 0; i < numNodes; i++) {
//				Node node = nodesInLevel.elementAt(i);
//				if (pathExists(node, destNode) && (!pathExists(node, srcNode))) {
//					if (frontierNodeInLevel == null) {
//						frontierNodeInLevel = node;
//					} else {
//						DrawingParameters dpNode = nodesVsDrawingParameters
//								.get(node);
//						DrawingParameters dpOfFrontierNode = nodesVsDrawingParameters
//								.get(frontierNodeInLevel);
//						if (srcNodeIsToRight) {
//							if (dpNode.getXPosition() > dpOfFrontierNode
//									.getXPosition()) {
//								frontierNodeInLevel = node;
//							}
//						} else {
//							if (dpNode.getXPosition() < dpOfFrontierNode
//									.getXPosition()) {
//								frontierNodeInLevel = node;
//							}
//						}
//					}
//				}
//			}
//			// Compare the frontier node determined at this level with the
//			// existing frontier node
//			if (frontierNode == null) {
//				frontierNode = frontierNodeInLevel;
//			} else {
//				DrawingParameters dpFrontierNode = nodesVsDrawingParameters.get(frontierNode);
//				DrawingParameters dpFrontierNodeInLevel = nodesVsDrawingParameters.get(frontierNodeInLevel);
//				if (srcNodeIsToRight) {
//					if (dpFrontierNodeInLevel.getXPosition()
//							+ dpFrontierNodeInLevel.getSize().width > dpFrontierNode.getXPosition()
//							+ dpFrontierNode.getSize().width) {
//						frontierNode = frontierNodeInLevel;
//					}
//				} 
//				else {
//					if (dpFrontierNodeInLevel.getXPosition() < dpFrontierNode.getXPosition()) {
//						frontierNode = frontierNodeInLevel;
//					}
//				}
//			}
//
//			currentLevelNum++;
//		}
//
//		if(frontierNode == null){ // Can happen if the destination node is in the next level
//			frontierNode = srcNode; 
//		}
//		
//		// Now that the frontier node is determined, use the xOffset + width of
//		// that node as xPosStart
//		DrawingParameters dpFrontierNode = nodesVsDrawingParameters.get(frontierNode);
//		int xPosStart = 0;
//		Line outLine2 = new Line();
//		outLine2.setStartPoint(new Point(xStart, yStart));
//		if (srcNodeIsToRight) { // Source node is to the right of the
//								// destination node
//			xPosStart = dpFrontierNode.getXPosition()
//					+ dpFrontierNode.getSize().width
//					+ minDistanceOfEdgeFromNode;
//		} else {
//			xPosStart = dpFrontierNode.getXPosition()
//					- minDistanceOfEdgeFromNode;
//		}
//		outLine2.setEndPoint(new Point(xPosStart, yStart));
//		lines.add(outLine2);
//
//		// Iterate through the each of the levels to establish the Manhattan
//		// edge
//		int count = srcNodeLevelIndex + 1;
//		while (true) {
//			BFSLevel nextLevel = levels.elementAt(count);
//			if (nextLevel.containsNode(destNode)) {
//				break;
//			}
//
//			Vector<Node> nodesInLevel = nextLevel.getNodes();
//			int numNodesInLvl = nodesInLevel.size();
//			Node intersectingNode = null;
//			for (int j = 0; j < numNodesInLvl; j++) {
//				Node nextNode = nodesInLevel.elementAt(j);
//				DrawingParameters dpNode = nodesVsDrawingParameters
//						.get(nextNode);
//				int xPosOfNode = dpNode.getXPosition();
//				int xEndOfNode = xPosOfNode + dpNode.getSize().width;
//				if (xPosStart >= xPosOfNode && xPosStart <= xEndOfNode) {
//					// There is an intersection between the vertical line and
//					// this node
//					intersectingNode = nextNode;
//					break;
//				}
//			}
//
//			// Check if an intersecting node was found
//			if (intersectingNode != null) {
//				// An intersecting node was found
//				int yEnd = getVerticalDistanceForLayer(count)
//						- minDistanceOfEdgeFromNode;
//				Line line1 = new Line();
//				line1.setStartPoint(new Point(xPosStart, yStart));
//				line1.setEndPoint(new Point(xPosStart, yEnd));
//				lines.add(line1);
//
//				DrawingParameters dpIntrNode = nodesVsDrawingParameters
//						.get(intersectingNode);
//				int xPosIntrNode = dpIntrNode.getXPosition();
//				// Create edges flanking around this node in the right or left
//				// direction
//				Line line2 = new Line();
//				int xEnd = 0;
//				if (srcNodeIsToRight)
//					xEnd = xPosIntrNode - minDistanceOfEdgeFromNode;
//				else
//					xEnd = xPosIntrNode + dpIntrNode.getSize().width
//							+ minDistanceOfEdgeFromNode;
//
//				line2.setStartPoint(new Point(xPosStart, yEnd));
//				line2.setEndPoint(new Point(xEnd, yEnd));
//				lines.add(line2);
//
//				Line line3 = new Line();
//				int yEnd1 = yEnd + dpIntrNode.getSize().height
//						+ minDistanceOfEdgeFromNode;
//				line3.setStartPoint(new Point(xEnd, yEnd));
//				line3.setEndPoint(new Point(xEnd, yEnd1));
//
//				lines.add(line1);
//				lines.add(line2);
//				lines.add(line3);
//
//				xPosStart = xEnd; // Update xStart for the next level
//				yStart = yEnd1; // Update yStart for the next level
//			} else {
//				int yEnd = getVerticalDistanceForLayer(count)
//						+ levelsVsHeights.get(nextLevel);
//				Line line1 = new Line();
//				line1.setStartPoint(new Point(xPosStart, yStart));
//				line1.setEndPoint(new Point(xPosStart, yEnd));
//				lines.add(line1);
//				yStart = yEnd; // Update yStart for the next level
//			}
//			count++;
//		} // while loop
//
//		// Create the final line(a horizontal line and and a downward arrow if
//		// necessary)
//		Point[] trianglePos1 = null;
//		int xPosofDestNode = dpDest.getXPosition();
//		boolean destNodeIsLeftChild = isLeftChild(srcNode, destNode);
//
//		if (xPosStart >= xPosofDestNode
//				&& xPosStart <= xPosofDestNode + dpDest.getSize().width) {
//			// xPosStart is above the destination node; just draw a vertical
//			// line
//			Line line1 = new Line();
//			line1.setStartPoint(new Point(xPosStart, yStart));
//			int midPointOfDestNode = xPosofDestNode
//					+ (int) ((0.5) * (dpDest.getSize().width));
//
//			if ((destNodeIsLeftChild && xPosStart < midPointOfDestNode)
//					|| (!destNodeIsLeftChild && xPosStart > midPointOfDestNode)) {
//				line1.setEndPoint(new Point(xPosStart,
//						getVerticalDistanceForLayer(count)));
//				lines.add(line1);
//			} else {
//				int targetXPos = 0;
//				if (destNodeIsLeftChild)
//					targetXPos = xPosofDestNode
//							+ (int) ((0.25) * (dpDest.getSize().width));
//				else
//					targetXPos = xPosofDestNode
//							+ (int) ((0.75) * (dpDest.getSize().width));
//				xPosStart = targetXPos;
//				line1.setEndPoint(new Point(xPosStart, yStart));
//
//				Line line2 = new Line();
//				line2.setStartPoint(new Point(xPosStart, yStart));
//				yStart = getVerticalDistanceForLayer(count)
//						- minDistanceOfEdgeFromNode;
//				line2.setEndPoint(new Point(xPosStart, yStart));
//				Line line3 = new Line();
//				line3.setStartPoint(new Point(xPosStart, yStart));
//				yStart = getVerticalDistanceForLayer(count);
//				line3.setEndPoint(new Point(xPosStart, yStart));
//				lines.add(line1);
//				lines.add(line2);
//				lines.add(line3);
//			}
//
//			trianglePos1 = calculateTrianglePositions(new Point(xPosStart,
//					yStart), new Point(xPosStart,
//					getVerticalDistanceForLayer(count)), null, destNode);
//
//		} else {
//			// Source node is to the right (or to the left) of the destination
//			// node and NOT directly above it
//			Line line1 = new Line();
//			int yEnd = getVerticalDistanceForLayer(count)
//					- minDistanceOfEdgeFromNode;
//			line1.setStartPoint(new Point(xPosStart, yStart));
//			line1.setEndPoint(new Point(xPosStart, yEnd));
//
//			int xEnd = 0;
//			if (!destNodeIsLeftChild) {
//				xEnd = xPosofDestNode
//						+ (int) ((0.75) * (dpDest.getSize().width));
//			} else {
//				xEnd = xPosofDestNode
//						+ (int) ((0.25) * (dpDest.getSize().width));
//			}
//
//			Line line2 = new Line();
//			line2.setStartPoint(new Point(xPosStart, yEnd));
//			line2.setEndPoint(new Point(xEnd, yEnd));
//
//			Line line3 = new Line();
//			line3.setStartPoint(new Point(xEnd, yEnd));
//			line3.setEndPoint(new Point(xEnd,
//					getVerticalDistanceForLayer(count)));
//
//			lines.add(line1);
//			lines.add(line2);
//			lines.add(line3);
//
//			trianglePos1 = calculateTrianglePositions(new Point(xEnd, yEnd),
//					new Point(xEnd, getVerticalDistanceForLayer(count + 1)),
//					null, destNode);
//		}
//
//		// Create the EdgeParameters object and return
//		EdgeParameters edgeParams = new EdgeParameters();
//		edgeParams.setLines(lines);
//		edgeParams.setBackEdge(false);
//
//		Vector<Point[]> triangles = new Vector<Point[]>();
//		triangles.add(trianglePos1);
//		edgeParams.setTriangles(triangles);
//
//		return edgeParams;
//	}
//
//	/*
//	 * Returns true if the node passed in the second parameter is the left child
//	 * of the node passed in the first parameter
//	 */
//	private boolean isLeftChild(Node srcNode, Node destNode) {
//		Vector<Node> childNodes = srcNode.getSuccessors();
//		if (destNode == childNodes.elementAt(0))
//			return true;
//		return false;
//	}
//
//	/**
//	 * Sets the parameters for drawing a back-edge; uses Manhattan distance to
//	 * traverse through intervening levels
//	 */
//	private void setBackEdgeParameters(Node srcNode, BackEdge backEdge) {
//
//		HashMap<String, Integer> levelNumberVsMinXPos = new HashMap<String, Integer>();
//		HashMap<String, Integer> levelNumberVsMaxXPos = new HashMap<String, Integer>();
//		HashMap<String, Node> levelNumberVsLeftMostNodeInSubGraph = new HashMap<String, Node>();
//		HashMap<String, Node> levelNumberVsRightMostNodeInSubGraph = new HashMap<String, Node>();
//
//		int srcLevelIndex = backEdge.getSrcLevelIndex();
//		BFSLevel destLevel = backEdge.getDestLevel();
//		Node destNode = backEdge.getDestNode();
//		int levelIndex = getIndexForLevel(destLevel);
//
//		DrawingParameters dpDestNode = nodesVsDrawingParameters.get(destNode);
//		int xPosDestNode = dpDestNode.getXPosition();
//		levelNumberVsMinXPos.put("" + levelIndex, new Integer(xPosDestNode));
//		
//		levelNumberVsLeftMostNodeInSubGraph.put("" + levelIndex, destNode);
//		int maxX = xPosDestNode + dpDestNode.getSize().width;
//		levelNumberVsMaxXPos.put("" + levelIndex, new Integer(maxX));
//		levelNumberVsRightMostNodeInSubGraph.put("" + levelIndex, destNode);
//
//		HashSet<Node> currentSet = new HashSet<Node>();
//		currentSet.add(destNode);
//		levelIndex++;
//
//		Node frontierNodeLeft = null;
//		BFSLevel leftFrontierNodeLevel = null;
//		Node frontierNodeRight = null;
//		BFSLevel rightFrontierNodeLevel = null;
//		outer: while (true) {
//			Iterator<Node> iter = currentSet.iterator();
//			HashSet<Node> childSet = new HashSet<Node>();
//			int minXPosOfSubGraphInLevel = 0;
//			int maxXPosOfSubGraphInLevel = 0;
//			Node nodeWithMinXPosInSubGraphAtLevel = null;
//			Node nodeWithMaxXPosInSubGraphAtLevel = null;
//
//			boolean traversedAllLevels = false;
//			while (iter.hasNext()) {
//				Node node = iter.next();
//				Vector<Node> successors = node.getSuccessors();
//
//				for (int i = 0; i < successors.size(); i++) {
//					Node childNode = successors.elementAt(i);
//					if (childNode == srcNode
//							|| (levels.elementAt(levelIndex).containsNode(
//									childNode) // && pathExists(childNode,// srcNode)
//							&& childSet.add(childNode))) { // Found a node in the subgraph in this level
//
//						DrawingParameters dpChildNode = nodesVsDrawingParameters
//								.get(childNode);
//						int xPos = dpChildNode.getXPosition();
//						if (minXPosOfSubGraphInLevel == 0) {
//							minXPosOfSubGraphInLevel = xPos;
//							maxXPosOfSubGraphInLevel = xPos
//									+ dpChildNode.getSize().width;
//							nodeWithMinXPosInSubGraphAtLevel = childNode;
//							nodeWithMaxXPosInSubGraphAtLevel = childNode;
//						} else {
//							if (xPos < minXPosOfSubGraphInLevel) {
//								minXPosOfSubGraphInLevel = xPos;
//								nodeWithMinXPosInSubGraphAtLevel = childNode;
//							}
//							if (xPos + dpChildNode.getSize().width > maxXPosOfSubGraphInLevel) {
//								maxXPosOfSubGraphInLevel = xPos
//										+ dpChildNode.getSize().width;
//								nodeWithMaxXPosInSubGraphAtLevel = childNode;
//							}
//						}
//					}
//				} // for loop -> children of each successor
//
//				if (successors.contains(srcNode)){
//					traversedAllLevels = true;
//					break;
//				}
//			} // while loop -> all successors processed
//
//			levelNumberVsMinXPos.put("" + levelIndex, new Integer(
//					minXPosOfSubGraphInLevel));
//		
//			levelNumberVsLeftMostNodeInSubGraph.put("" + levelIndex,
//					nodeWithMinXPosInSubGraphAtLevel);
//			levelNumberVsMaxXPos.put("" + levelIndex, new Integer(
//					maxXPosOfSubGraphInLevel));
//			
//			levelNumberVsRightMostNodeInSubGraph.put("" + levelIndex,
//					nodeWithMaxXPosInSubGraphAtLevel);
//
//			// Populate the frontier node and the level in which it is found
//			if (frontierNodeLeft == null) { // First instance
//				frontierNodeLeft = nodeWithMinXPosInSubGraphAtLevel;
//				frontierNodeRight = nodeWithMaxXPosInSubGraphAtLevel;
//				leftFrontierNodeLevel = levels.elementAt(levelIndex);
//				rightFrontierNodeLevel = levels.elementAt(levelIndex);
//			} else {
//				DrawingParameters dpFrontierNodeLeft = nodesVsDrawingParameters.get(frontierNodeLeft);
//				DrawingParameters dpFrontierNodeRight = nodesVsDrawingParameters.get(frontierNodeRight);
//				if (dpFrontierNodeLeft.getXPosition() > minXPosOfSubGraphInLevel)
//					frontierNodeLeft = nodeWithMinXPosInSubGraphAtLevel;
//				if (dpFrontierNodeRight.getXPosition()
//						+ dpFrontierNodeRight.getSize().width < maxXPosOfSubGraphInLevel)
//					frontierNodeRight = nodeWithMaxXPosInSubGraphAtLevel;
//			}
//
//			currentSet = childSet; // Update the current set
//			levelIndex++; // Update the current index
//			
//			if(traversedAllLevels)
//				break;
//		}
//
//		EdgeParameters edgeParameters = createBackwardManhattanEdge(
//				levelNumberVsMinXPos, levelNumberVsMaxXPos,
//				levelNumberVsLeftMostNodeInSubGraph,
//				levelNumberVsRightMostNodeInSubGraph, frontierNodeLeft,
//				frontierNodeRight, srcNode, backEdge);
//
//		edgeParametersList.add(edgeParameters);
//
//	}
//
//	/**
//	 *
//	 */
//
//	private EdgeParameters createBackwardManhattanEdge(
//			HashMap<String, Integer> levelNumberVsMinXPos,
//			HashMap<String, Integer> levelNumberVsMaxXPos,
//			HashMap<String, Node> levelNumberVsLeftMostNodeInSubGraph,
//			HashMap<String, Node> levelNumberVsRightMostNodeInSubGraph,
//			Node frontierNodeLeft, Node frontierNodeRight, Node srcNode,
//			BackEdge backEdge) {
//
//		EdgeParameters edgeParameters = new EdgeParameters();
//		Vector<Line> lines = new Vector<Line>();
//
//		int srcLevelIndex = backEdge.getSrcLevelIndex();
//		BFSLevel srcLevel = levels.elementAt(srcLevelIndex);
//		Node destNode = backEdge.getDestNode();
//
//		// Check if the destination node is the left or right child of the
//		// source node
//		// boolean srcNodeIsToRight = true;
//		boolean flankLeft = false; // Flag to determine if the backward line
//									// should be left or to the right
//
//		DrawingParameters dpSrcNode = nodesVsDrawingParameters.get(srcNode);
//		// DrawingParameters dpDestNode =
//		// nodesVsDrawingParameters.get(destNode);
//		DrawingParameters dpFrontierNodeLeft = nodesVsDrawingParameters.get(frontierNodeLeft);
//		DrawingParameters dpFrontierNodeRight = nodesVsDrawingParameters.get(frontierNodeRight);
//		int srcNodeXPos = dpSrcNode.getXPosition();
//		int frontierNodeLeftXPos = dpFrontierNodeLeft.getXPosition();
//		int frontierNodeRightXPos = dpFrontierNodeRight.getXPosition();
//		if (!(srcNodeXPos > frontierNodeLeftXPos && srcNodeXPos < frontierNodeRightXPos)) { // Source
//																							// node
//																							// is
//																							// not
//			// NOT between frontiers nodes
//			if (srcNodeXPos == frontierNodeLeftXPos) // Source node directly
//														// below left frontier
//														// node
//				flankLeft = true;
//			else if (srcNodeXPos == frontierNodeRightXPos) // Source node
//															// directly below
//															// right frontier
//															// node
//				flankLeft = false;
//			else if (srcNodeXPos < frontierNodeLeftXPos) // Source node to
//															// the left of the
//															// left frontier
//															// node
//				flankLeft = true;
//			else if (srcNodeXPos > frontierNodeRightXPos) { // Source node to
//															// the right of the
//															// right frontier
//															// node
//				flankLeft = false;
//			}
//		} else { // Source node is between the two frontier nodes
//			int distFromLeftFrontierNode = srcNodeXPos - frontierNodeLeftXPos;
//			int distFromRightFrontierNode = frontierNodeRightXPos - srcNodeXPos;
//			if (distFromLeftFrontierNode < distFromRightFrontierNode)
//				flankLeft = true;
//		}
//
//		int[] startPoint = createOutLinesFromSource(srcLevel, srcLevelIndex,
//				srcNode, flankLeft, frontierNodeLeft, frontierNodeRight, lines);
//		int xPosStart = startPoint[0];
//		int yStart = startPoint[1];
//
//		// Iterate through the intervening levels to get the minimum and maximum
//		// x positions of
//		// the sub-graph
//		int count = srcLevelIndex - 1; // Start one level above the source node
//										// level
//		while (true) {
//			BFSLevel level = levels.elementAt(count);
//			Node borderNode = null;
//
//			int xPosStartNew = xPosStart; // Assume no change in x position
//			if (flankLeft) {
//				Node leftMostNode = levelNumberVsLeftMostNodeInSubGraph.get(""
//						+ count);
//				borderNode = leftMostNode;
//				int minXPosAtLevel = levelNumberVsMinXPos.get("" + count)
//						.intValue();
//				if (minXPosAtLevel <= xPosStart) {
//					int leftOffSet = getEdgeOffsetFromNode(leftMostNode,
//							AdjacentEdge.LEFT);
//					xPosStartNew = minXPosAtLevel - leftOffSet;
//				} else {
//					boolean intersectionFound = false;
//					Vector<Node> nodesInLevel = level.getNodes();
//					for (int i = 0; i < nodesInLevel.size(); i++) {
//						DrawingParameters dpNd = nodesVsDrawingParameters
//								.get(nodesInLevel.elementAt(i));
//						int xPosOfNode = dpNd.getXPosition();
//
//						if (xPosOfNode < minXPosAtLevel) {
//							int wd = dpNd.getSize().width;
//							if (xPosStart <= xPosOfNode
//									|| (xPosStart >= xPosOfNode && xPosStart <= xPosOfNode
//											+ wd)) {
//								intersectionFound = true;
//								break;
//							}
//						}
//					}
//					if (intersectionFound) { // An intersection was found,
//												// draw a horizontal line to the
//												// right
//						// xPosStartNew = minXPosAtLevel - 5;
//						int leftOffSet = getEdgeOffsetFromNode(leftMostNode,
//								AdjacentEdge.LEFT);
//						xPosStartNew = minXPosAtLevel - leftOffSet;
//					}
//				}
//			} else {
//				Node rightMostNode = levelNumberVsRightMostNodeInSubGraph
//						.get("" + count);
//				borderNode = rightMostNode;
//				int maxXPosAtLevel = levelNumberVsMaxXPos.get("" + count)
//						.intValue();
//				if (maxXPosAtLevel >= xPosStart) {
//					// xPosStartNew = maxXPosAtLevel + 5;
//					int rightOffSet = getEdgeOffsetFromNode(rightMostNode,
//							AdjacentEdge.RIGHT);
//					xPosStartNew = maxXPosAtLevel + rightOffSet;
//				} else {
//					boolean intersectionFound = false;
//					Vector<Node> nodesInLevel = level.getNodes();
//					for (int i = 0; i < nodesInLevel.size(); i++) {
//						DrawingParameters dpNd = nodesVsDrawingParameters
//								.get(nodesInLevel.elementAt(i));
//						int xPosOfNode = dpNd.getXPosition();
//
//						if (xPosOfNode > maxXPosAtLevel) {
//							if (xPosStart >= xPosOfNode) {
//								intersectionFound = true;
//								break;
//							}
//						}
//					}
//					if (intersectionFound) { // An intersection was found,
//												// draw a horizontal line to the
//												// left
//						// xPosStartNew = maxXPosAtLevel + 5;
//						int rightOffSet = getEdgeOffsetFromNode(rightMostNode,
//								AdjacentEdge.RIGHT);
//						xPosStartNew = maxXPosAtLevel + rightOffSet;
//					}
//				}
//			}
//			// Now set the horizontal line if the x position has changed
//			if (xPosStart != xPosStartNew) {
//				Line horizontalLine = new Line();
//				horizontalLine.setStartPoint(new Point(xPosStart, yStart));
//				horizontalLine.setEndPoint(new Point(xPosStartNew, yStart));
//				lines.add(horizontalLine);
//				xPosStart = xPosStartNew; // Update the new horizontal
//											// position
//			}
//
//			// Set the vertical line
//			int yVertDist = getVerticalDistanceForLayer(count);
//			int verticalOffset = getEdgeOffsetFromNode(borderNode,
//					AdjacentEdge.TOP);
//			int yStartNew = yVertDist - verticalOffset;
//			Line verticalLine = new Line();
//			verticalLine.setStartPoint(new Point(xPosStart, yStart));
//			verticalLine.setEndPoint(new Point(xPosStart, yStartNew));
//			lines.add(verticalLine);
//			yStart = yStartNew; // Update the new vertical position
//
//			count--;
//
//			if (level.containsNode(destNode))
//				break;
//
//		}
//
//		// Draw the final lines reaching the destination node
//		
//		Node leftPred = (Node)destNode.getPredecessors().elementAt(0);
//		boolean isLeftParent = false;
//		if(leftPred == srcNode)
//			isLeftParent = true;
//		
//		DrawingParameters dpDest = nodesVsDrawingParameters.get(destNode);
//		int xEnd = 0;
//		int xPosofDestNode = dpDest.getXPosition();
//		if (!isLeftParent) {
//			xEnd = xPosofDestNode + (int) ((0.75) * (dpDest.getSize().width));
//		} else {
//			xEnd = xPosofDestNode + (int) ((0.25) * (dpDest.getSize().width));
//		}
//
//		Line lastHorizontalLine = new Line();
//		lastHorizontalLine.setStartPoint(new Point(xPosStart, yStart));
//		lastHorizontalLine.setEndPoint(new Point(xEnd, yStart));
//		lines.add(lastHorizontalLine);
//
//		int vertOffset = getEdgeOffsetFromNode(destNode, AdjacentEdge.TOP);
//		int yEnd = yStart + vertOffset;
//		Line lastVerticalLine = new Line();
//		lastVerticalLine.setStartPoint(new Point(xEnd, yStart));
//		lastVerticalLine.setEndPoint(new Point(xEnd, yEnd));
//		lines.add(lastVerticalLine);
//
//		edgeParameters.setLines(lines);
//
//		edgeParameters.setBackEdge(true);
//		Vector<Point[]> triangles = new Vector<Point[]>();
//		Point[] trianglePos1 = calculateTrianglePositions(new Point(xEnd,
//				yStart), new Point(xEnd, yEnd), null, destNode);
//		triangles.add(trianglePos1);
//		edgeParameters.setTriangles(triangles);
//		return edgeParameters;
//	}
//
//	private int[] createOutLinesFromSource(BFSLevel srcLevel,
//			int srcLevelIndex, Node srcNode, boolean flankLeft,
//			Node frontierNodeLeft, Node frontierNodeRight, Vector<Line> lines) {
//		// Get the node to the immediate left of the source node
//
//		int retStartPoint[] = new int[2];
//		Node nodeLeftOfScrNode = null;
//		Node nodeRightOfScrNode = null;
//		int rightEdgeOfLeftNodeOfSrc = 0;
//		int leftEdgeOfRightNodeOfSrc = 0;
//		Vector<Node> nodesInSrcLevel = srcLevel.getNodes();
//		int srcNodeIndexInLevel = 0;
//		for (int i = 0; i < nodesInSrcLevel.size(); i++) {
//			if (nodesInSrcLevel.elementAt(i) == srcNode) {
//				srcNodeIndexInLevel = i;
//				break;
//			}
//		}
//		if (srcNodeIndexInLevel > 0) {
//			nodeLeftOfScrNode = nodesInSrcLevel
//					.elementAt(srcNodeIndexInLevel - 1);
//			DrawingParameters dpNodeLeftOfSrcNode = nodesVsDrawingParameters
//					.get(nodeLeftOfScrNode);
//			rightEdgeOfLeftNodeOfSrc = dpNodeLeftOfSrcNode.getXPosition()
//					+ dpNodeLeftOfSrcNode.getSize().width;
//		}
//
//		if (srcNodeIndexInLevel < nodesInSrcLevel.size() - 1) {
//			nodeRightOfScrNode = nodesInSrcLevel
//					.elementAt(srcNodeIndexInLevel + 1);
//			DrawingParameters dpNodeRightOfSrcNode = nodesVsDrawingParameters
//					.get(nodeRightOfScrNode);
//			leftEdgeOfRightNodeOfSrc = dpNodeRightOfSrcNode.getXPosition();
//		}
//
//		// Set the initial "out-lines"
//		Line outLine1 = new Line();
//
//		int xPosStart = 0;
//		int yStart = 0;
//
//		DrawingParameters dpSrcNode = nodesVsDrawingParameters.get(srcNode);
//		int srcLevelHeight = getVerticalDistanceForLayer(srcLevelIndex);
//		int srcNodeBtm = srcLevelHeight + dpSrcNode.getSize().height;
//		int srcNodeMid = dpSrcNode.getXPosition()
//				+ (int) (dpSrcNode.getSize().width / 2.0);
//		;
//		outLine1.setStartPoint(new Point(srcNodeMid, srcNodeBtm));
//		yStart = srcNodeBtm + minDistanceOfEdgeFromNode;
//		outLine1.setEndPoint(new Point(srcNodeMid, yStart));
//		lines.add(outLine1);
//		xPosStart = srcNodeMid;
//
//		DrawingParameters dpFrontierNodeLeft = nodesVsDrawingParameters
//				.get(frontierNodeLeft);
//		int frontierNodeLeftXPos = dpFrontierNodeLeft.getXPosition();
//		DrawingParameters dpFrontierNodeRight = nodesVsDrawingParameters
//				.get(frontierNodeRight);
//		int frontierNodeRightXPos = dpFrontierNodeRight.getXPosition();
//
//		if (flankLeft) { // The back edge must flank the left of the loop
//			int offsetFromSrcNode = dpSrcNode.getXPosition()
//					- minDistanceOfEdgeFromNode;
//			if (offsetFromSrcNode > frontierNodeLeftXPos
//					&& nodeLeftOfScrNode != null
//					&& rightEdgeOfLeftNodeOfSrc > frontierNodeLeftXPos) {
//				// An intersection exists between the out line and the node to
//				// the left of the source node
//				Line outLine4 = new Line();
//				xPosStart = rightEdgeOfLeftNodeOfSrc
//						+ getEdgeOffsetFromNode(nodeLeftOfScrNode,
//								AdjacentEdge.RIGHT);
//				outLine4.setStartPoint(new Point(srcNodeMid, yStart));
//				outLine4.setEndPoint(new Point(xPosStart, yStart));
//
//				Line outLine5 = new Line();
//				int newYPos = dpSrcNode.getYPosition()
//						- getEdgeOffsetFromNode(nodeLeftOfScrNode,
//								AdjacentEdge.TOP);
//				outLine5.setStartPoint(new Point(xPosStart, yStart));
//				outLine5.setEndPoint(new Point(xPosStart, newYPos));
//				yStart = newYPos;
//				lines.add(outLine4);
//				lines.add(outLine5);
//
//				Line outLine6 = new Line();
//				outLine6.setStartPoint(new Point(xPosStart, yStart));
//				xPosStart = frontierNodeLeftXPos
//						- getEdgeOffsetFromNode(frontierNodeLeft,
//								AdjacentEdge.LEFT);
//				outLine6.setEndPoint(new Point(xPosStart, yStart));
//				lines.add(outLine6);
//
//			} else {
//				// No intersection exists between the out line and the node to
//				// the left of the source node
//				Line outLine2 = new Line();
//				Line outLine3 = new Line();
//				outLine2.setStartPoint(new Point(xPosStart, yStart));
//				int offsetFromFrontierNode = frontierNodeLeftXPos
//						- getEdgeOffsetFromNode(frontierNodeLeft,
//								AdjacentEdge.LEFT);
//				if (offsetFromFrontierNode < offsetFromSrcNode)
//					xPosStart = offsetFromFrontierNode;
//				else
//					xPosStart = dpSrcNode.getXPosition()
//							- minDistanceOfEdgeFromNode;
//				outLine2.setEndPoint(new Point(xPosStart, yStart));
//				outLine3.setStartPoint(new Point(xPosStart, yStart));
//				int verticalOffSet = getEdgeOffsetFromNode(srcNode,
//						AdjacentEdge.TOP);
//				yStart = srcLevelHeight - verticalOffSet;
//				outLine3.setEndPoint(new Point(xPosStart, yStart));
//				lines.add(outLine2);
//				lines.add(outLine3);
//			}
//		} else { // The back edge must flank the right of the loop
//			int offsetFromSrcNode = dpSrcNode.getXPosition()
//					+ dpSrcNode.getSize().width + minDistanceOfEdgeFromNode;
//			if (offsetFromSrcNode < frontierNodeRightXPos
//					&& nodeRightOfScrNode != null
//					&& leftEdgeOfRightNodeOfSrc < frontierNodeRightXPos) {
//
//				// An intersection exists between the out line and the node to
//				// the right of the source node
//				Line outLine4 = new Line();
//				xPosStart = leftEdgeOfRightNodeOfSrc
//						- getEdgeOffsetFromNode(nodeRightOfScrNode,
//								AdjacentEdge.LEFT);
//				outLine4.setStartPoint(new Point(srcNodeMid, yStart));
//				outLine4.setEndPoint(new Point(xPosStart, yStart));
//
//				Line outLine5 = new Line();
//				int newYPos = dpSrcNode.getYPosition()
//						- getEdgeOffsetFromNode(nodeRightOfScrNode,
//								AdjacentEdge.TOP);
//				outLine5.setStartPoint(new Point(xPosStart, yStart));
//				outLine5.setEndPoint(new Point(xPosStart, newYPos));
//				yStart = newYPos;
//				lines.add(outLine4);
//				lines.add(outLine5);
//
//				Line outLine6 = new Line();
//				outLine6.setStartPoint(new Point(xPosStart, yStart));
//				xPosStart = frontierNodeRightXPos
//						+ dpFrontierNodeLeft.getSize().width
//						+ getEdgeOffsetFromNode(frontierNodeRight,
//								AdjacentEdge.RIGHT);
//				outLine6.setEndPoint(new Point(xPosStart, yStart));
//				lines.add(outLine6);
//
//			} else {
//				// No intersection exists between the out line and the node to
//				// the right of the source node
//				Line outLine2 = new Line();
//				Line outLine3 = new Line();
//				outLine2.setStartPoint(new Point(xPosStart, yStart));
//				xPosStart = frontierNodeRightXPos
//						+ dpFrontierNodeRight.getSize().width
//						+ getEdgeOffsetFromNode(frontierNodeRight,
//								AdjacentEdge.RIGHT);
//				outLine2.setEndPoint(new Point(xPosStart, yStart));
//				outLine3.setStartPoint(new Point(xPosStart, yStart));
//				int verticalOffSet = getEdgeOffsetFromNode(srcNode,
//						AdjacentEdge.TOP);
//				yStart = srcLevelHeight - verticalOffSet;
//				outLine3.setEndPoint(new Point(xPosStart, yStart));
//				lines.add(outLine2);
//				lines.add(outLine3);
//			}
//		}
//
//		retStartPoint[0] = xPosStart;
//		retStartPoint[1] = yStart;
//		return retStartPoint;
//	}
//
//	private int getEdgeOffsetFromNode(Node node, short orientation) {
//		int ret = minDistanceOfEdgeFromNode;
//		Vector<AdjacentEdge> adjacentEdges = nodesVsAdjacentEdges.get(node);
//		if (adjacentEdges == null) {
//			adjacentEdges = new Vector<AdjacentEdge>();
//			AdjacentEdge adjEdge = new AdjacentEdge();
//			adjEdge.setOrientation(orientation);
//			if (orientation == AdjacentEdge.LEFT)
//				adjEdge.setLeftDistanceFromNode(minDistanceOfEdgeFromNode);
//			else if (orientation == AdjacentEdge.RIGHT)
//				adjEdge.setRightDistanceFromNode(minDistanceOfEdgeFromNode);
//			else if (orientation == AdjacentEdge.TOP)
//				adjEdge.setTopDistanceFromNode(minDistanceOfEdgeFromNode);
//			else if (orientation == AdjacentEdge.BOTTOM)
//				adjEdge.setBottomDistanceFromNode(minDistanceOfEdgeFromNode);
//
//			adjacentEdges.addElement(adjEdge);
//			nodesVsAdjacentEdges.put(node, adjacentEdges);
//		} else {
//			for (int i = 0; i < adjacentEdges.size(); i++) {
//				AdjacentEdge adjEdge = adjacentEdges.elementAt(i);
//				if (adjEdge.getOrientation() == orientation) {
//					if (orientation == AdjacentEdge.LEFT) {
//						ret = adjEdge.getLeftDistanceFromNode()
//								+ minDistanceOfEdgeFromNode;
//						adjEdge.setLeftDistanceFromNode(ret);
//					} else if (orientation == AdjacentEdge.RIGHT) {
//						ret = adjEdge.getRightDistanceFromNode()
//								+ minDistanceOfEdgeFromNode;
//						adjEdge.setRightDistanceFromNode(ret);
//					} else if (orientation == AdjacentEdge.TOP) {
//						ret = adjEdge.getTopDistanceFromNode()
//								+ minDistanceOfEdgeFromNode;
//						adjEdge.setTopDistanceFromNode(ret);
//					}
//					if (orientation == AdjacentEdge.BOTTOM) {
//						ret = adjEdge.getBottomDistanceFromNode()
//								+ minDistanceOfEdgeFromNode;
//						adjEdge.setBottomDistanceFromNode(ret);
//					}
//				}
//			}
//		}
//
//		return ret;
//	}
//
//	/**
//	 * Determines the vertices of the triangle that points towards the
//	 * destination from the source
//	 */
//	private Point[] calculateTrianglePositions(Point srcPoint, Point destPoint,
//			TriangleParameters triangleParams, Node destNode) {
//		Point[] vertices = new Point[3];
//		if (srcPoint.x == destPoint.x) {
//			// The destination is straight below the source
//			double heightOfTriangle = 0.0;
//
//			int vDist = Math.abs(srcPoint.y - destPoint.y);
//			if (vDist >= 20)
//				heightOfTriangle = maxTriangleHeight;
//			else {
//				if (vDist <= 10)
//					heightOfTriangle = minTriangleHeight;
//				else
//					heightOfTriangle = vDist * triangleHeightFraction;
//			}
//
//			int yVertex = destPoint.y - (int) heightOfTriangle;
//			int xLeftVertex = srcPoint.x - (int) (heightOfTriangle / 2);
//			int xRightVertex = srcPoint.x + (int) (heightOfTriangle / 2);
//
//			vertices[0] = destPoint;
//			vertices[1] = new Point(xLeftVertex, yVertex);
//			vertices[2] = new Point(xRightVertex, yVertex);
//			return vertices;
//
//		}
//
//		// Calculate the distance between the source and destination points
//		double xDist = Math.abs((srcPoint.x - destPoint.x));
//		double yDist = Math.abs((srcPoint.y - destPoint.y));
//		double distance = Math.sqrt(Math.pow(xDist, 2) + Math.pow(yDist, 2));
//		double sideLength = distance * triangleHeightFraction;
//
//		if (distance >= 20)
//			sideLength = maxTriangleHeightForAngledLine;
//		else
//			sideLength = distance * angledTriangleHeightFraction;
//
//		double tangent = yDist / xDist;
//		double incidentAngle = Math.atan(tangent); // calculate now
//
//		double triangleAngle = 0.0;
//		int heightOfBottomVertex;
//		int widthOfBottomVertex;
//
//		double maxAngle;
//		int heightOfTopVertex;
//		int widthOfTopVertex;
//
//		if (triangleParams != null) { // The triangle parameters have already
//										// been calculated
//			triangleAngle = triangleParams.getAngleOfSideWithPerpBisector();
//			if (triangleAngle < incidentAngle) {
//				sideLength = triangleParams.getSideLength();
//				heightOfBottomVertex = (int) (sideLength * Math
//						.sin(incidentAngle - triangleAngle));
//				widthOfBottomVertex = (int) (sideLength * Math
//						.cos(incidentAngle - triangleAngle));
//
//				maxAngle = incidentAngle + triangleAngle;
//				heightOfTopVertex = (int) (sideLength * Math.sin(maxAngle));
//				widthOfTopVertex = (int) (sideLength * Math.cos(maxAngle));
//			} else {
//				triangleAngle = incidentAngle / 2; // Make the triangle angle
//													// half of the incident
//													// angle
//
//				heightOfBottomVertex = (int) (sideLength * Math
//						.sin(triangleAngle));
//				widthOfBottomVertex = (int) (sideLength * Math
//						.cos(triangleAngle));
//
//				maxAngle = 1.5 * incidentAngle;
//				heightOfTopVertex = (int) (sideLength * Math.sin(maxAngle));
//				widthOfTopVertex = (int) (sideLength * Math.cos(maxAngle));
//			}
//		} else {
//			triangleAngle = incidentAngle / 2; // Make the triangle angle half
//												// of the incident angle
//			TriangleParameters tp = new TriangleParameters();
//			tp.setAngleOfSideWithPerpBisector(triangleAngle);
//			tp.setSideLength(sideLength);
//
//			nodeVsTriangleParams.put(destNode, tp);
//
//			heightOfBottomVertex = (int) (sideLength * Math.sin(triangleAngle));
//			widthOfBottomVertex = (int) (sideLength * Math.cos(triangleAngle));
//
//			maxAngle = 1.5 * incidentAngle;
//			heightOfTopVertex = (int) (sideLength * Math.sin(maxAngle));
//			widthOfTopVertex = (int) (sideLength * Math.cos(maxAngle));
//
//		}
//
//		// The top and bottom vertices of the triangle (to be determined)
//		int xBottomVertex = 0;
//		int yBottomVertex = 0;
//		int xTopVertex = 0;
//		int yTopVertex = 0;
//
//		if (destPoint.x > srcPoint.x) {
//
//			// Bottom vertex
//			xBottomVertex = destPoint.x - widthOfBottomVertex;
//			yBottomVertex = destPoint.y - heightOfBottomVertex;
//
//			// Top vertex
//			xTopVertex = destPoint.x - widthOfTopVertex;
//			yTopVertex = destPoint.y - heightOfTopVertex;
//		} else {
//			// Bottom vertex
//			xBottomVertex = destPoint.x + widthOfBottomVertex;
//			yBottomVertex = destPoint.y - heightOfBottomVertex;
//
//			// Top vertex
//			xTopVertex = destPoint.x + widthOfTopVertex;
//			yTopVertex = destPoint.y - heightOfTopVertex;
//		}
//
//		vertices[0] = destPoint;
//		vertices[1] = new Point(xBottomVertex, yBottomVertex);
//		vertices[2] = new Point(xTopVertex, yTopVertex);
//		return vertices;
//	}
//
//	private void updateXOffSetsOfNodes() {
//		// Find the minimum x offset of among all levels
//		int numLevels = levels.size();
//		int leftMostXOffset = 0;
//		for (int i = 0; i < numLevels; i++) {
//			BFSLevel level = levels.elementAt(i);
//			Vector<Node> nodesInLevel = level.getNodes();
//			Node leftMostNodeInLevel = nodesInLevel.elementAt(0);
//			DrawingParameters drawingParams = nodesVsDrawingParameters
//					.get(leftMostNodeInLevel);
//			int xOffsetForLeftMostNode = drawingParams.getXPosition();
//			if (xOffsetForLeftMostNode < leftMostXOffset)
//				leftMostXOffset = xOffsetForLeftMostNode;
//		}
//
//		if (leftMostXOffset == 0) {
//			leftMostXOffset = leftMargin;
//		} else if (leftMostXOffset < 0) {
//			leftMostXOffset = 0 - leftMostXOffset + 10;
//		}
//
//		// Now draw each node
//		for (int i = 0; i < numLevels; i++) {
//			BFSLevel level = levels.elementAt(i);
//			Vector<Node> nodesInLevel = level.getNodes();
//
//			int numNodes = nodesInLevel.size();
//			for (int j = 0; j < numNodes; j++) {
//				Node node = nodesInLevel.elementAt(j);
//				DrawingParameters drawingParams = nodesVsDrawingParameters
//						.get(node);
//				int xOffset = drawingParams.getXPosition() + leftMostXOffset;
//				drawingParams.setXPosition(xOffset); // Update the horizontal
//														// position
//				nodesVsDrawingParameters.put(node, drawingParams);
//			}
//		}
//	}
//
//	/**
//	 * Actually draws the graph based on the sizes and the positions calculated
//	 *
//	 */
//	public void drawGraph(Graphics g) {
//
//		removeAll();
//
//		if (levels == null) {
//			return;
//		}
//		graphics = g;
//		graphics2D = (Graphics2D) g;
//
//		// Find the minimum x offset of among all levels
//		int numLevels = levels.size();
//		int leftMostXOffset = 0;
//		for (int i = 0; i < numLevels; i++) {
//			BFSLevel level = levels.elementAt(i);
//			Vector<Node> nodesInLevel = level.getNodes();
//			Node leftMostNodeInLevel = nodesInLevel.elementAt(0);
//			DrawingParameters drawingParams = nodesVsDrawingParameters
//					.get(leftMostNodeInLevel);
//			int xOffsetForLeftMostNode = drawingParams.getXPosition();
//			if (xOffsetForLeftMostNode < leftMostXOffset)
//				leftMostXOffset = xOffsetForLeftMostNode;
//		}
//
//		if (leftMostXOffset == 0) {
//			leftMostXOffset = leftMargin;
//		} else if (leftMostXOffset < 0) {
//			leftMostXOffset = 0 - leftMostXOffset + 10;
//		}
//
//		// Now draw each node
//		for (int i = 0; i < numLevels; i++) {
//			BFSLevel level = levels.elementAt(i);
//			Vector<Node> nodesInLevel = level.getNodes();
//			int yOffSet = getVerticalDistanceForLayer(i); // y offset for this
//															// level
//
//			int numNodes = nodesInLevel.size();
//			for (int j = 0; j < numNodes; j++) {
//				Node node = nodesInLevel.elementAt(j);
//				DrawingParameters drawingParams = nodesVsDrawingParameters
//						.get(node);
//				int xOffset = drawingParams.getXPosition();
//				drawingParams.setXPosition(xOffset); // Update the horizontal
//														// position
//				drawNode(node, xOffset, yOffSet);
//			}
//		}
//
//		// Draw all edges
//		int numEdges = edgeParametersList.size();
//		for (int i = 0; i < numEdges; i++) {
//			EdgeParameters edgeParameters = edgeParametersList.elementAt(i);
//
//			Color currentColor = getColor();
//			if (edgeParameters.isBackEdge()) {
//				graphics.setColor(Color.MAGENTA);
//			}
//
//			// Draw the lines
//			Vector<Line> lines = edgeParameters.getLines();
//			int numLines = lines.size();
//			for (int a = 0; a < numLines; a++) {
//				Line line = lines.elementAt(a);
//				Point start = line.getStartPoint();
//				Point end = line.getEndPoint();
//				graphics.drawLine(start.x, start.y, end.x, end.y);
//			}
//
//			// Draw the triangles
//			Vector<Point[]> triangles = edgeParameters.getTriangles();
//			if (triangles != null) {
//				for (int j = 0; j < triangles.size(); j++) {
//					Point[] triangle = triangles.elementAt(j);
//
//					int[] xPoints = new int[3];
//					int[] yPoints = new int[3];
//					xPoints[0] = triangle[0].x;
//					xPoints[1] = triangle[1].x;
//					xPoints[2] = triangle[2].x;
//					yPoints[0] = triangle[0].y;
//					yPoints[1] = triangle[1].y;
//					yPoints[2] = triangle[2].y;
//
//					graphics.fillPolygon(xPoints, yPoints, 3);
//				}
//			}
//			// Reset to current color
//			if (edgeParameters.isBackEdge()) {
//				graphics.setColor(currentColor);
//			}
//		}
//	}
//
//	private Color getColor() {
//		return graphics.getColor();
//	}
//
//	private int getIndexForLevel(BFSLevel level) {
//		int ret = -1;
//		for (int i = 0; i < levels.size(); i++) {
//			if (level == levels.elementAt(i)) {
//				ret = i;
//				break;
//			}
//		}
//		return ret;
//	}
//
//	@Override
//	protected void paintComponent(Graphics g) {
//		if (recalculate) {
//			graphSize = updateDrawParameters(controlFlowGraph,
//					controlFlowGraph.getStartNode(), g);
//
//			setPreferredSize(graphSize);
//			createNodeComponent();
//		}
//		drawGraph(g);
//		if (recalculate) {
//			SwingUtilities.invokeLater(new Runnable() {
//				public void run() {
//					pane.getViewport().setViewPosition(new Point(0, 0));
//				}
//			});
//		}
//		recalculate = false;
//	}
//
//	public Dimension getGraphSize(){
//		return graphSize;
//	}
//
//	private void createNodeComponent() {
//		nodeDisplayComponent.clear();
//
//		for (int i = 0; i < levels.size(); i++) {
//			BFSLevel level = levels.elementAt(i);
//			Vector<Node> nodesInLevel = level.getNodes();
//			for (int j = 0; j < nodesInLevel.size(); j++) {
//				Node node = nodesInLevel.elementAt(j);
//				NodeTextArea nodeTextArea = new NodeTextArea();
//				nodeTextArea.setLineWrap(true);
//				nodeTextArea.setEditable(false);
////				nodeTextArea.setFocusable(false);
//				nodeTextArea.setEnabled(true);
//				nodeTextArea.setToolTipText("Node name: " + node.getName());
//				nodeTextArea.setFont(getGraphics().getFont());
//				nodeTextArea.setBorder(BorderFactory
//						.createLineBorder(Color.black));
//				nodeTextArea.setNode(node);
//				nodeDisplayComponent.put(node, nodeTextArea);
//			}
//		}
//	}
//
//	protected void setCFG(ControlFlowGraph cfg) {
//		controlFlowGraph = cfg;
//		recalculate = true;
//	}
//
//	protected void setDT(DominatorTree dt) {
//		this.dt = dt;
//	}
//
//	protected void generateDominatorFrontiers() {
//		if (controlFlowGraph != null && dt != null) {
//			dominanceFrontiers = Utilities.getDominanceFrontiers(
//					controlFlowGraph, dt);
//		}
//
//		// preparing the dialog.
//		nodePropertiesdialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//		nodePropertiesdialog.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
//		nodeProptextArea = new JTextArea();
//		nodeProptextArea.setEditable(false);
//		nodeProptextArea.setLineWrap(true);
//		nodePropertiesdialog.setModal(true);
//		nodePropertiesdialog.setResizable(false);
//		nodePropertiesdialog.getContentPane().add(
//				new JScrollPane(nodeProptextArea));
//		nodePropertiesdialog.setTitle("Dominance Frontiers");
//
//	}
//
//	class NodeTextArea extends JTextArea implements MouseListener, KeyListener {
//
//		private static final long serialVersionUID = 1L;
//		Node node = null;
//		HashSet<Node> df;
//		String text = "";
//		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//
//		public void setNode(Node node) {
//			this.node = node;
//			df = dominanceFrontiers.get(node);
//			addMouseListener(this);
//			addKeyListener(this);
//		}
//
//		public Node getNode() {
//			return node;
//		}
//
//		public void mouseClicked(MouseEvent e) {
//
//		}
//
//		public void mouseEntered(MouseEvent e) {
//			setBackground(Color.GRAY);
//		}
//
//		public void mouseExited(MouseEvent e) {
//			setBackground(Color.WHITE);
//		}
//
//		public void mousePressed(MouseEvent e) {
//		}
//
//		public void mouseReleased(MouseEvent e) {
//			if (e.getButton() != MouseEvent.BUTTON1) {
//				return;
//			}
//
//			if (listener == null)
//				return;
//
//			Vector<String> columnData = new Vector<String>();
//			columnData.add(node.getName());
//			text ="";
//			if (df != null)
//			{
//				Iterator<Node> iter = df.iterator();
//				if (iter.hasNext())
//					text = iter.next().getName();
//				while(iter.hasNext())
//				{
//					text += "," + iter.next().getName();
//				}
//			}
//			columnData.add(text);
//			columnData.add("<Provide Date here>");
//			listener.propertyChange(new PropertyChangeEvent(this, "DISPLAYPROPERTY", "", columnData));
//		}
//
//
//		public void keyPressed(KeyEvent e) {
//		}
//
//		public void keyReleased(KeyEvent e) {
//			if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
//			{
//				if (listener != null)
//				{
//					listener.propertyChange(new PropertyChangeEvent(this, "HIDEPROPERTY", "", "hide"));
//				}
//			}
//		}
//
//		public void keyTyped(KeyEvent e) {
//		}
//	}
//
//	public void addPropertyChangeListener(PropertyChangeListener listener) {
//		this.listener = listener;
//	}
//}
//
///**
// * This class represents the x and y position of a node and also it's size
// *
// * @author shreesha123
// *
// */
//class DrawingParameters {
//	private int xPosition;
//	private int yPosition;
//	private Dimension size;
//
//	public int getYPosition() {
//		return yPosition;
//	}
//
//	public void setYPosition(int yposition) {
//		yPosition = yposition;
//	}
//
//	public Dimension getSize() {
//		return size;
//	}
//
//	public void setSize(Dimension size) {
//		this.size = size;
//	}
//
//	public int getXPosition() {
//		return xPosition;
//	}
//
//	public void setXPosition(int position) {
//		xPosition = position;
//	}
//}
//
///**
// * This class represents a line in an edge. It has a start point and an end
// * point
// *
// * @author shreesha123
// *
// */
//class Line {
//	private Point startPoint;
//	private Point endPoint;
//
//	public Point getEndPoint() {
//		return endPoint;
//	}
//
//	public void setEndPoint(Point endPoint) {
//		this.endPoint = endPoint;
//	}
//
//	public Point getStartPoint() {
//		return startPoint;
//	}
//
//	public void setStartPoint(Point startPoint) {
//		this.startPoint = startPoint;
//	}
//}
//
///**
// * This class represents the line (or lines) and the triangle (pointer) that
// * represent an edge. It can have either one line or multiple lines (might
// * represent Manhattan distance) and a triangle
// *
// * @author shreesha123
// *
// */
//class EdgeParameters {
//	private Vector<Line> lines;
//	private boolean isBackEdge;
//
//	private Vector<Point[]> triangles;
//
//	public Vector<Line> getLines() {
//		return lines;
//	}
//
//	public void setLines(Vector<Line> lines) {
//		this.lines = lines;
//	}
//
//	public Vector<Point[]> getTriangles() {
//		return triangles;
//	}
//
//	public void setTriangles(Vector<Point[]> triangles) {
//		this.triangles = triangles;
//	}
//
//	public boolean isBackEdge() {
//		return isBackEdge;
//	}
//
//	public void setBackEdge(boolean isBackEdge) {
//		this.isBackEdge = isBackEdge;
//	}
//}
//
//class TriangleParameters {
//	private double angleOfSideWithPerpBisector;
//	private double sideLength;
//
//	public double getAngleOfSideWithPerpBisector() {
//		return angleOfSideWithPerpBisector;
//	}
//
//	public void setAngleOfSideWithPerpBisector(
//			double angleOfSideWithPerpBisector) {
//		this.angleOfSideWithPerpBisector = angleOfSideWithPerpBisector;
//	}
//
//	public double getSideLength() {
//		return sideLength;
//	}
//
//	public void setSideLength(double sideLength) {
//		this.sideLength = sideLength;
//	}
//}
//
///**
// * Represents a back edge in the control flow graph. Typically this is a back
// * edge from a loop. Contains the level of the source node, the level of the
// * destination node and the destination node.
// *
// * @author shreesha123
// *
// */
//class BackEdge {
//	private int srcLevelIndex;
//	private Node destNode;
//	private BFSLevel destLevel;
//
//	public BackEdge(int srcLevelIndex, Node destNode, BFSLevel destLevel) {
//		this.srcLevelIndex = srcLevelIndex;
//		this.destNode = destNode;
//		this.destLevel = destLevel;
//	}
//
//	public BFSLevel getDestLevel() {
//		return destLevel;
//	}
//
//	public Node getDestNode() {
//		return destNode;
//	}
//
//	public int getSrcLevelIndex() {
//		return srcLevelIndex;
//	}
//}
//
///**
// * Represents a neighbouring edge adjecent to a node. Has an orientation that
// * indicates whether it is at the top, left, right or center to a node and the
// * distance from the node
// */
//
//class AdjacentEdge {
//	public static final short TOP = 1;
//	public static final short BOTTOM = 1;
//	public static final short LEFT = 1;
//	public static final short RIGHT = 1;
//
//	public static final short DOWNWARD = 1;
//	public static final short UPWARD = 2;
//
//	private short orientation;
//
//	private int leftDistanceFromNode;
//	private int topDistanceFromNode;
//	private int bottomDistanceFromNode;
//	private int rightDistanceFromNode;
//
//	private short direction;
//
//	public short getOrientation() {
//		return orientation;
//	}
//
//	public void setOrientation(short orientation) {
//		this.orientation = orientation;
//	}
//
//	public short getDirection() {
//		return direction;
//	}
//
//	public void setDirection(short direction) {
//		this.direction = direction;
//	}
//
//	public int getBottomDistanceFromNode() {
//		return bottomDistanceFromNode;
//	}
//
//	public void setBottomDistanceFromNode(int bottomDistanceFromNode) {
//		this.bottomDistanceFromNode = bottomDistanceFromNode;
//	}
//
//	public int getLeftDistanceFromNode() {
//		return leftDistanceFromNode;
//	}
//
//	public void setLeftDistanceFromNode(int leftDistanceFromNode) {
//		this.leftDistanceFromNode = leftDistanceFromNode;
//	}
//
//	public int getRightDistanceFromNode() {
//		return rightDistanceFromNode;
//	}
//
//	public void setRightDistanceFromNode(int rightDistanceFromNode) {
//		this.rightDistanceFromNode = rightDistanceFromNode;
//	}
//
//	public int getTopDistanceFromNode() {
//		return topDistanceFromNode;
//	}
//
//	public void setTopDistanceFromNode(int topDistanceFromNode) {
//		this.topDistanceFromNode = topDistanceFromNode;
//	}
}

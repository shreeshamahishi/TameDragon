package org.tamedragon.common;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tamedragon.common.llvmir.instructions.AssemblyInstruction;
import org.tamedragon.common.llvmir.types.Operand;
import org.tamedragon.common.llvmir.types.Temp;

public class RegisterAllocator  {
	
	private static final Logger LOG = LoggerFactory.getLogger(RegisterAllocator.class);
	
//	private InterferenceGraph interferenceGraph;
//	private  Vector<AssemblyInstruction> instructionList;
//	private Vector<AssemblyInstruction> outputInstructionList;
//	private Vector<String> poolOfFreeColors;
//	private int numberOfRegisters;
//	private TargetMachine targetArchitecture;
//	private HashMap<Temp, String> assignment;
//	private HashMap<String, HashSet<Temp>> colorVsTemps;
//
//	public RegisterAllocator(InterferenceGraph interferenceGraph,
//			Vector<AssemblyInstruction> instructionList,  TargetMachine targetArchitecture){
//		this.interferenceGraph = interferenceGraph;
//		this.instructionList = instructionList;
//		this.poolOfFreeColors = targetArchitecture.getColors();
//		this.targetArchitecture = targetArchitecture;
//
//		numberOfRegisters = targetArchitecture.getColors().size();
//
//		LOG.debug("POOL OF FREE COLORS");
//		for(String str: poolOfFreeColors){
//			LOG.debug("Color = "  + str);
//		}
//		
//		allocate();
//	}
//
//	private void allocate(){		
//
//		LOG.debug("INPUT TO REGISTER ALLOCATOR");
//		printInstructionList(instructionList);
//		LOG.debug("END INPUT TO REGISTER ALLOCATOR");
//
//		Stack<Temp> selectStack  = new Stack<Temp>();
//		HashMap<Temp, HashSet<Temp>> selectedTempVsAdjTemps = new HashMap<Temp, HashSet<Temp>>();
//		HashMap<Temp, Temp[]> coalescedVsList = new HashMap<Temp, Temp[]>();   // A map of coalesed temp vs. it's set of corresponding children
//
//		Vector<Node> igNodes = interferenceGraph.nodes();
//		HashSet<Node> spilledNodes = new HashSet<Node>();
//
//		// Analyze the interference graph now
//		int numIter = 1;
//		while(igNodes.size() > 0){		
//
//			Node igNode = (Node) igNodes.elementAt(0);
//			Temp tmp = interferenceGraph.getTemp(igNode);
//			LOG.debug("**************ITERATION " + numIter + " ******************************");
//			LOG.debug("Considering temp = " + tmp);
//
//			// This node has not been considered yet; process it	
//			int degree = interferenceGraph.getDegree(igNode);   // Get the degree of the node
//			if(degree <= numberOfRegisters -1){
//				processLowDegreeNode(igNodes, igNode, selectStack, selectedTempVsAdjTemps, coalescedVsList);
//			}
//			else{
//				// This node is of significant degree; Get the best node from this node to the end of the list
//				// of nodes
//				Node bestNextNode = getBestNextNode(igNodes);
//				LOG.debug(tmp + " is of significant degree, trying with next best node: " + interferenceGraph.getTemp(bestNextNode));
//
//				Temp nextBestTemp = interferenceGraph.getTemp(bestNextNode);
//				LOG.debug("Considering temp = " + nextBestTemp);
//
//				int deg = interferenceGraph.getDegree(bestNextNode);
//				if(deg <= numberOfRegisters -1){
//					processLowDegreeNode(igNodes, bestNextNode, selectStack, selectedTempVsAdjTemps, coalescedVsList);
//				}
//				else{
//					// All nodes from igNode to the end of the list are significant degree nodes; 
//					// select the best next node for spilling; if the spill priority of the next best node
//					// is lesser than the spill priority of the current node being considered, make the
//					// next best node the current node.
//
//					int spForNextBestNode = Integer.parseInt(interferenceGraph.getSpillCost(bestNextNode));
//					int spForCurrNode = Integer.parseInt(interferenceGraph.getSpillCost(igNode));
//
//					if(spForCurrNode > spForNextBestNode) bestNextNode = igNode;
//
//					// Remove any moves associated with this node (freeze the moves)
//					removeMovesForNode(bestNextNode);
//
//					// Select for spilling
//					pushOnToStack(bestNextNode, selectStack,  selectedTempVsAdjTemps);
//					interferenceGraph.removeNode(bestNextNode);  // This will remove all edges of this node
//					igNodes.remove(bestNextNode);
//					spilledNodes.add(bestNextNode);
//				}
//			}
//
//			// Reset the work-list of nodes (populate from nodes in the interference graph)
//			igNodes = interferenceGraph.nodes();
//
//			numIter++;	
//		}
//
//		// Assign the colors now
//		assignment = 
//			assignColors(selectStack, selectedTempVsAdjTemps, spilledNodes, coalescedVsList);
//
//		if(assignment != null){
//			colorVsTemps = getRegistersVsAssignedTemps(assignment);
//			printAssignment(assignment);                   // Print out the assignments to debug
//			HashMap<String, String> tempNamesVsColors = getMapOfTempNamesVsColors(assignment);
//			populateOutputInstructionList(tempNamesVsColors);     // Update the instructions basedo on the assignments
//		}
//		else{
//			// A coloring was not possible; try to spill, rewrite code, construct cfg, ig and then analyze again.
//		}
//	}	
//
//	private void pushOnToStack(Node igNode, Stack<Temp> selectStack, 
//			HashMap<Temp, HashSet<Temp>> selectedTempVsAdjTemps){
//		Temp temp = interferenceGraph.getTemp(igNode);
//		HashSet<Node> adjList = interferenceGraph.getAdjacentNodeList(igNode);
//
//		HashSet<Temp> adjTemps = new HashSet<Temp>();
//		String adjTemStrs = "";
//		if(adjList != null){
//			Iterator<Node> iter = adjList.iterator();
//			while(iter.hasNext()){
//				Node adjNode = (Node) iter.next();
//				Temp adjTemp = interferenceGraph.getTemp(adjNode);
//				adjTemps.add(adjTemp);
//				adjTemStrs += adjTemp.toString() + ",";
//			}
//		}
//
//		LOG.debug("Pushing onto the stack: " + interferenceGraph.getTemp(igNode) + " with adjTemps = [" + adjTemStrs + "]");
//
//		selectedTempVsAdjTemps.put(temp, adjTemps);
//		selectStack.push(temp);
//	}
//
//	private void processLowDegreeNode(Vector<Node> igNodes, Node igNode, Stack<Temp> selectStack,
//			HashMap<Temp, HashSet<Temp>> selectedTempVsAdjTemps,
//			HashMap<Temp, Temp[]>  coalescedVsList){
//		// This node has a degree lesser than that of the physical register of the m/c
//		HashSet<Node> moveRelatedNodes = interferenceGraph.getMoveRelatedNodes(igNode);
//		if(moveRelatedNodes == null || moveRelatedNodes.size() == 0){
//			// Not move related; remove this node from the graph and put it on the 
//			// selection stack
//			LOG.debug("Not move related..., ");
//			pushOnToStack(igNode, selectStack,  selectedTempVsAdjTemps);
//			interferenceGraph.removeNode(igNode);  // This will remove all edges of this node
//			igNodes.remove(igNode);
//		}
//		else{
//			// This node is move related; check if it can be coalesced with each of it's
//			// move related neighbors
//			Iterator<Node> iter = moveRelatedNodes.iterator();
//			boolean coalesced = false;
//			while(iter.hasNext()){
//				// Coalesce with the first move related node, if possible.
//				Node moveNeighbor = (Node) iter.next();
//
//				// The node should not have an interference edge with this neighbor; if it does,
//				// it cannot be coalesced
//				HashSet<Node> igNeighbors = interferenceGraph.getAdjacentNodeList(igNode);
//				if(igNeighbors != null && igNeighbors.contains(moveNeighbor)) continue;
//
//				HashSet<Node> unionOfNeighbors = getUnionOfNeighbors(igNode, moveNeighbor);
//				if(unionOfNeighbors.size() <= numberOfRegisters -1)
//				{
//					// The two nodes can be coalesced; coalesce
//					coalesce(igNode, moveNeighbor, unionOfNeighbors, igNodes, coalescedVsList);
//					coalesced = true;
//					break;
//				}
//			}
//
//			if(!coalesced)
//			{
//				// This node cannot be coalesced with any of it's move related nodes;
//				// freeze it and continue. It involves disabling moves between
//				// the two nodes
//				iter = moveRelatedNodes.iterator();
//				Vector<Node> nodesToBeRemoved = new Vector<Node>();
//				while(iter.hasNext())
//					nodesToBeRemoved.addElement(iter.next());
//				for(int a = 0; a < nodesToBeRemoved.size(); a++)
//					removeMoves(igNode, (Node) nodesToBeRemoved.elementAt(a));
//
//				// Now that the moves have been removed for this node, it can be removed
//				// from the interference graph
//				pushOnToStack(igNode, selectStack,  selectedTempVsAdjTemps);
//				interferenceGraph.removeNode(igNode);
//				igNodes.remove(igNode);
//			}
//		}
//	}
//
//	/**
//	 * This method returns the union of the neigbors of the input nodes. It uses HashSet objects
//	 * so there are no duplicates
//	 * @param igNode
//	 * @param moveNeighbor
//	 * @return
//	 */
//	private HashSet<Node> getUnionOfNeighbors(Node igNode, Node moveNeighbor)
//	{		
//		HashSet<Node> igNodeAdjList = interferenceGraph.getAdjacentNodeList(igNode);
//		HashSet<Node> moveNeighAdjList = interferenceGraph.getAdjacentNodeList(moveNeighbor);
//
//		HashSet<Node> union = new HashSet<Node>();
//
//		if(igNodeAdjList == null){
//			// This node does not have any intereferences; check the move-related node's neighbors
//			if(moveNeighAdjList == null) return union;   // The neighbor does not have any interferences
//
//			Iterator<Node> iter1 = moveNeighAdjList.iterator();
//			while(iter1.hasNext()){
//				Node moveNeighNeigh = (Node) iter1.next();
//				if(moveNeighNeigh != igNode) union.add(moveNeighNeigh); 
//			}
//		}
//		else{
//			Iterator<Node> iter = igNodeAdjList.iterator();
//			while(iter.hasNext())
//				union.add((Node) iter.next()); 
//
//			if(moveNeighAdjList != null){
//				Iterator<Node> iter1 = moveNeighAdjList.iterator();
//				while(iter1.hasNext()){
//					Node moveNeighNeigh = (Node) iter1.next();
//					if(moveNeighNeigh != igNode) union.add(moveNeighNeigh); 
//				}
//			}
//		}
//		return union;
//	}
//
//	/** This method will coalesce the two input nodes and creates a new node in the graph by 
//	 * using the set that is the union of the neighbors of each of the nodes (the third parameter).
//	 * 
//	 * @param node
//	 */
//
//	private void coalesce(Node node, Node moveNeighbor, HashSet<Node> unionOfNeighbors, 
//			Vector<Node> igNodes, HashMap<Temp, Temp[]> coalescedVsList){
//		// Create a new node in the interference graph; add this to the end of the nodes list
//		// of the interference graph
//		Temp temp = new Temp();
//		Node newNode = interferenceGraph.newInterferenceGraphNode(temp);
//
//		// Populate the map of coalesced nodes with the new node vs. it's children
//		Temp[] children = new Temp[2];
//		children[0] = interferenceGraph.getTemp(node);
//		children[1] = interferenceGraph.getTemp(moveNeighbor);
//
//		Temp referenceTemp = children[0];
//		if(children[1].isWiderThan(children[0]))
//			referenceTemp = children[1];
//
//		updateNewTempWithTypeInfo(temp, referenceTemp);
//
//		LOG.debug("Coalescing " + children[0] + " and " + children[1] + " into " + temp);
//
//		coalescedVsList.put(temp, children);
//
//		// Add edges from the new node to each element in the union set
//		Iterator<Node> iter = unionOfNeighbors.iterator();
//		while(iter.hasNext()){
//			Node neighbor = iter.next();
//			interferenceGraph.updateAdjacencyMatrix(newNode, neighbor);
//		}
//
//		// Remove the move between the nodes, since they are being coalesed
//		removeMoves(node, moveNeighbor);
//
//		// Create moves for the new node if any; the moves will be the union of the moves related
//		// to the two nodes
//		HashSet<Node> union = new HashSet<Node>();
//		HashSet<Node> movesForNode = interferenceGraph.getMoveRelatedNodes(node);
//		HashSet<Node> movesForMoveNeigh =  interferenceGraph.getMoveRelatedNodes(moveNeighbor);
//		Iterator<Node> iter1 = movesForNode.iterator();
//		while(iter1.hasNext()){
//			Node nd = iter1.next();			
//			union.add(nd);
//		}
//
//		Iterator<Node> iter2 = movesForMoveNeigh.iterator();
//		while(iter2.hasNext()){
//			Node nd = iter2.next();
//			union.add(nd);
//		}
//
//		Iterator<Node> iter3 = union.iterator();
//		while(iter3.hasNext()){
//			interferenceGraph.addToMoveRelatedNodes(newNode, iter3.next());
//		}
//
//		// The spill cost of the new coalesced node will be the maximum of the spill cost of the two nodes
//		// that are being coalesced
//		int spCostOfNewNode = Integer.parseInt(interferenceGraph.getSpillCost(node));
//		int spCostOfMoveNeigh = Integer.parseInt(interferenceGraph.getSpillCost(moveNeighbor));
//		if(spCostOfNewNode < spCostOfMoveNeigh ) spCostOfNewNode = spCostOfMoveNeigh;
//		interferenceGraph.setSpillCost(newNode, ""+spCostOfNewNode);
//
//		// Remove the two nodes from the interference graph
//		interferenceGraph.removeNode(node);
//		interferenceGraph.removeNode(moveNeighbor); 
//		igNodes.remove(node);
//		igNodes.remove(moveNeighbor);
//		removeMovesForNode(node);
//		removeMovesForNode(moveNeighbor);
//	}
//
//	private void removeMoves(Node node, Node moveNeighbor){
//		HashSet<Node> moveRelatedNodesForInputNode = interferenceGraph.getMoveRelatedNodes(node);
//		HashSet<Node> moveRelatedNodesForMoveNeigh = interferenceGraph.getMoveRelatedNodes(moveNeighbor);
//		moveRelatedNodesForInputNode.remove(moveNeighbor);
//		moveRelatedNodesForMoveNeigh.remove(node);
//	}
//
//	/**
//	 * This method will return the next "best" node to be processed while iterating through the nodes of the interference
//	 * graph. The next "best" node will be either a low-degree node or a high degree node with a high spill cost.
//	 * 
//	 * @return
//	 */
//
//	private Node getBestNextNode(Vector<Node> igNodes){
//		//	Get the next node with insignificant degree; during the sequentional search,
//		// find the nodes of high degree with the highest spill cost - this will
//		// help if there are no nodes with low degree
//		Node nextNodeWithLowerDegree = null;
//		Node highDegNodeWithMaxSpillCost = null;
//		int maxSpillCost = 0;
//		int numNodes = igNodes.size();
//		for(int a = 1; a < numNodes; a++){
//			Node nextIgNode = (Node) igNodes.elementAt(a);  // Get the next node
//
//			int degreeOfNextNode = interferenceGraph.getDegree(nextIgNode);   // Get the degree of this node
//			if(degreeOfNextNode <= numberOfRegisters -1) {
//				nextNodeWithLowerDegree = nextIgNode;
//				break;
//			}
//			else{
//				int spCost = Integer.parseInt(interferenceGraph.getSpillCost(nextIgNode));
//				if(spCost > maxSpillCost) 
//				{
//					maxSpillCost = spCost;
//					highDegNodeWithMaxSpillCost = nextIgNode;
//				}
//			}
//		}
//
//		// Check if a lower degree node was found; if found, return it; else return the high-degree node 
//		// with maximum spill cost
//		if(nextNodeWithLowerDegree != null) return nextNodeWithLowerDegree;
//
//		// No low degree node was found; return the one that was found with max spill cost
//		return highDegNodeWithMaxSpillCost;
//	}
//
//	private void removeMovesForNode(Node node){
//		HashSet<Node> moveRelatedNodes = interferenceGraph.getMoveRelatedNodes(node);
//		if(moveRelatedNodes != null && moveRelatedNodes.size() != 0){
//			Vector<Node> nodesToBeRemoved = new Vector<Node>();
//			Iterator<Node> iter = moveRelatedNodes.iterator();
//			while(iter.hasNext())
//				nodesToBeRemoved.addElement(iter.next());
//
//			for(int a = 0; a < nodesToBeRemoved.size(); a++)
//				removeMoves(node, (Node)nodesToBeRemoved.elementAt(a));
//		}
//	}
//
//	private HashMap<Temp, String>  assignColors(Stack<Temp> selectStack, 
//			HashMap<Temp, HashSet<Temp>> selectedTempVsAdjTemps,
//			HashSet<Node> spilledNodes, 
//			HashMap<Temp, Temp[]> coalescedVsList){
//
//		interferenceGraph.reset();
//		
//		HashSet<String> usedColors = new HashSet<String>();
//
//		HashMap<Temp, Node> tempVsNewNode = new HashMap<Temp, Node>();
//		HashMap<Temp, String> tempVsColor = new HashMap<Temp, String>(); 
//
//		while(!selectStack.isEmpty()){
//			Temp temp = selectStack.pop();
//
//			Node newNode = interferenceGraph.newInterferenceGraphNode(temp);
//			tempVsNewNode.put(temp, newNode);
//
//			HashSet<Temp> adjTemps = selectedTempVsAdjTemps.get(temp);
//
//			// Find out the colors that CANNOT be used for this new node; these will be the 
//			// colors of the adjacent nodes of the new temp
//			HashSet<String> prohibitedColorsForNewNode = getProhibitedColorsForNode(temp, 
//					adjTemps, tempVsColor);
//
//			if(prohibitedColorsForNewNode.size() >= numberOfRegisters){
//				// No color available for new node; have to spill (a potential spill has now
//				// become an actual spill) and re-try
//				LOG.debug("POTENTIAL SPILL HAS BECOME A REAL SPILL!!!");
//				return null;
//			}
//
//			String colorToUse = getColorToUse(temp, usedColors, prohibitedColorsForNewNode);
//			if(colorToUse == null){
//				// No color available for new node; have to spill (a potential spill has now
//				// become an actual spill) and re-try
//				LOG.debug("POTENTIAL SPILL HAS BECOME A REAL SPILL!!!");
//				return null;
//			}
//			
//			if(temp.toString().equals("t139_n0")){
//				LOG.debug("WAIT HERE, putting temp and color to use = " + temp + ", color = " + colorToUse);
//			}
//
//			tempVsColor.put(temp, colorToUse);
//
//			// If temp is a coalesced temporary, add it's children also to the tempVsColor map
//			HashSet<Temp> coalescedTemps = getCoalescedTemps(temp, coalescedVsList);
//			Iterator<Temp> coalTempIter = coalescedTemps.iterator();
//			while(coalTempIter.hasNext())
//				tempVsColor.put((Temp) coalTempIter.next(), colorToUse);
//
//			usedColors.add(colorToUse);
//		}
//		
//		return tempVsColor;
//	}
//
//	private HashSet<String> getProhibitedColorsForNode(Temp temp, HashSet<Temp> adjTemps, 
//			HashMap<Temp, String> tempVsColor){
//
//		HashSet<String> prohibitedColorsForNewNode = new HashSet<String>();
//
//		Iterator<Temp> iter = adjTemps.iterator();		
//		while(iter.hasNext()){
//			Temp adjTemp = (Temp) iter.next();
//			String prohibitedColor = tempVsColor.get(adjTemp);
//			prohibitedColorsForNewNode.add(prohibitedColor);
//		}
//		return prohibitedColorsForNewNode;
//	}
//
//	/*
//	 * 
//	 */
//	private String getColorToUse(Temp temp, HashSet<String> usedColors, 
//			HashSet<String> prohibitedColors ){
//
//		// Must try to use an already used color; iterate through the usedColors set and find 
//		// if there is one such color
//		Iterator<String> iter1 = usedColors.iterator();
//		String colorToUse = null;
//		
//		while(iter1.hasNext()){
//			String usedColor = iter1.next();
//			if(!prohibitedColors.contains(usedColor)){
//				
//				if(targetArchitecture.isCompatibleWithRegister(temp, usedColor)){
//					colorToUse = usedColor;
//					break;
//				}
//				
//			}
//		}
//
//		
//		if(colorToUse != null)
//			return colorToUse;
//
//		// No color from the list of already used colors can be used. Try and get from the pool of free colors (if compatible).
//		// If no compatible ones are found, but incompatible ones are found, we can still use the incompatible one provided
//		// we do not use it in any computations and use it only for storage (only move instructions)
//
//		String possibleColor = null;
//
//		for(String clr : poolOfFreeColors){
//			boolean isCompatible = targetArchitecture.isCompatibleWithRegister(temp, clr);
//			if(isCompatible){
//				colorToUse = clr;
//				break;
//			}
//			else{
//				// Found an incompatible color; check if a possible color has already not been chosen;
//				// if not chosen, check if the size is compatible
//				if(possibleColor == null){
//					if(targetArchitecture.isSizeCompatible(temp, clr))
//						possibleColor = clr;
//				}
//			}
//		}			
//
//		if(colorToUse != null){
//			poolOfFreeColors.remove(colorToUse);
//			return colorToUse;
//		}
//
//		// No compatible color found; check if a possible color has been found.
//		if(possibleColor != null){
//			// A possible color has been found; if its used only in move statements, we are OK
//			if(isInvolvedOnlyInMoves(temp))
//				colorToUse = possibleColor;
//		}
//
//		if(colorToUse != null)
//			poolOfFreeColors.remove(colorToUse);
//
//
//		return colorToUse;
//	}
//
//	private boolean isInvolvedOnlyInMoves(Temp temp){
//
//		int numMoves = 0;
//		for(AssemblyInstruction ins: instructionList){
//
//			Vector<Temp> destList = ins.getDestList();
//			Vector<Operand> srcList = ins.getSrcList();
//
//			if((destList != null && destList.contains(temp)) 
//					|| (srcList != null && srcList.contains(temp))){
//				if(!ins.isMove())
//					return false;
//				else
//					numMoves++;
//			}
//		}
//
//		if(numMoves > 0)
//			return true;
//		else 
//			return false;
//
//	}
//
//	private HashSet<Temp> getCoalescedTemps(Temp temp, HashMap<Temp, Temp[]> coalescedVsList){
//		HashSet<Temp> coalescedTemps = new HashSet<Temp>();
//
//		Temp[] children = (Temp[]) coalescedVsList.get(temp);
//		if(children == null) return coalescedTemps;
//
//		coalescedTemps.add(children[0]);
//		coalescedTemps.add(children[1]);
//		coalescedTemps.addAll(getCoalescedTemps(children[0], coalescedVsList));
//		coalescedTemps.addAll(getCoalescedTemps(children[1], coalescedVsList));
//
//		return coalescedTemps;
//	}
//
//	private void populateOutputInstructionList(HashMap<String, String> tempNamesVsColors){
//		outputInstructionList = new Vector<AssemblyInstruction>();
//
//		// Replace temp variable with the actual physical register names in the code
//		int numIns = instructionList.size();
//		for(int i = 0; i < numIns; i++){
//			AssemblyInstruction ins = (AssemblyInstruction) instructionList.elementAt(i);
//			
//			if(ins.isMove()){
//				Vector<Temp> def = ins.getDestList();
//				Vector<Operand> uses = ins.getSrcList();
//
//				// Get the color assigned to each temp (for the dest and the use temp)
//				String colorDest = tempNamesVsColors.get(((Temp) def.elementAt(0)).toString());  
//				String colorUse =  tempNamesVsColors.get(((Temp) uses.elementAt(0)).toString());
//
//				// If they are the same, ignore them; this will help get rid of unnecessary move instructions
//				if(colorDest == colorUse) continue;
//			}
//
//			// Update the instructions
//			
//			updateInstruction(ins, tempNamesVsColors);
//
//			outputInstructionList.addElement(ins);				
//		}
//	}
//
//	/**
//	 * From the assignment map, this function returns a map of the name of the temporary against the 
//	 * colors assigned to that temporary.
//	 * @param assignment
//	 * @return
//	 */
//	private HashMap<String, String > getMapOfTempNamesVsColors(HashMap<Temp, String> assignment){
//		
//		HashMap<String, String> tempNamesVsColors = new HashMap<String, String>();
//		Set<Temp> tempKeys = assignment.keySet();
//		Iterator<Temp> iterator = tempKeys.iterator();
//		while(iterator.hasNext()){
//			Temp tempKey = iterator.next();
//			if(assignment.get(tempKey) != null)
//				tempNamesVsColors.put(tempKey.toString(), assignment.get(tempKey));
//		}
//		
//		return tempNamesVsColors;
//	}
//	
//	private void updateInstruction(AssemblyInstruction ins, HashMap<String, String> assignment){
//		
//		// Replace the def with the new temps
//		Vector<Temp> defs = ins.getDestList();
//		if(defs != null){
//			Temp defTemp = defs.elementAt(0);
//			
//			if(!defTemp.isPhysicalRegister()){
//				String color = assignment.get(defTemp.toString());	
//				Temp newTemp = new Temp(color);
//				
//				updateNewTempWithTypeInfo(newTemp, defTemp);
//				
//				defs.set(0, newTemp);
//			}
//			
//		}
//
//		// Replace the uses list with the new temps
//		Vector<Operand> uses = ins.getSrcList();
//		if(uses == null)
//			return;
//
//		for(int j = 0; j < uses.size();j++){
//			Operand operand = (Operand)uses.elementAt(j);
//			if(operand.getOperandType() == Operand.TEMP_TYPE){
//				Temp tmp = (Temp) uses.elementAt(j);
//				
//				if(tmp.isPhysicalRegister()) {
//					continue;
//				}
//
//				String color = (String) assignment.get(tmp.toString());	
//				if(color == null){
//					// No color has been assigned, probably its not part of the interference graph or maybe
//					// live in at procedure entry; in that case, there is no change and we use the same temp
//					continue;
//				}
//
//				// A color has been assigned, use it 
//				Temp newTemp = new Temp(color);		
//				updateNewTempWithTypeInfo(newTemp, tmp);
//				uses.set(j, newTemp);
//			}
//		}
//	}
//	
//	/**
//	 * Updates the type information of the value contained in the new temp using the 
//	 * type information contained in the reference temp passed in the second parameter. 
//	 * @param newTemp
//	 * @param referenceTemp
//	 */
//	private void updateNewTempWithTypeInfo(Temp newTemp, Temp referenceTemp){
//		
//		if(newTemp == null || referenceTemp == null)   // Sanity check
//			return;
//		
//		newTemp.setInteger(referenceTemp.isInteger());
//		newTemp.setIntegerSize(referenceTemp.getIntegerSize());
//		newTemp.setSigned(referenceTemp.isSigned());
//		newTemp.setFloatPrecision(referenceTemp.getFloatPrecision());
//	}
//
//	/** 
//	 * Updates the activation frame object with information about the registers used in this function.
//	 * This will be useful later for the activation frame to allocate stack size, etc.
//	 * @param assignment
//	 */
//	
//	private void printAssignment(HashMap<Temp, String> assignment){
//		
//		LOG.debug("COLOR ASSIGNMENT: ");
//
//		Set<String> colors = colorVsTemps.keySet();
//		Iterator<String> colorIterator = colors.iterator();
//		while(colorIterator.hasNext()){
//			
//			String color = colorIterator.next();
//
//			LOG.debug(color + ": ");
//
//			HashSet<Temp> tempList = colorVsTemps.get(color);
//			Iterator<Temp> iter1 = tempList.iterator();
//			String tmpListStr = "";
//			while(iter1.hasNext()){
//				Temp t = iter1.next();
//				String tmpStr = t.toString();
//				
//				if(tmpStr != null)
//					tmpListStr += tmpStr + " ";
//				else
//					tmpListStr += "???" + " ";
//			}
//			LOG.debug(tmpListStr);
//		}
//	}
//
//
//	/**
//	 * Returns a map of assigned colors against the set of temporaries that have been assigned those colors.
//	 * @return
//	 */
//	private HashMap<String, HashSet<Temp>> getRegistersVsAssignedTemps(HashMap<Temp, String> assignment){
//		HashMap<String, HashSet<Temp>> colorVsTemps = new HashMap<String, HashSet<Temp>>();
//
//		Set<Temp> tempsSet = assignment.keySet();
//		Iterator<Temp> iter = tempsSet.iterator();
//		while(iter.hasNext()){
//			Temp temp = iter.next();
//			String color = assignment.get(temp);
//			
//			if(temp.toString().equals("t171_n1")){
//				LOG.debug("WAIT HERE FOR T171");
//			}
//
//			HashSet<Temp> tempList = colorVsTemps.get(color);
//			
//			if(tempList == null) 
//				tempList = new HashSet<Temp>();
//			tempList.add(temp);
//			colorVsTemps.put(color, tempList);
//		}
//		
//		return colorVsTemps;
//	}
//	
//	public Vector<AssemblyInstruction> getOutputInstructionList(){
//		return outputInstructionList;
//	}
//
//	public int getNumRegistersUsed(){
//		return numberOfRegisters - poolOfFreeColors.size();
//	}
//
//
//	public void setTargetArchitecture(TargetMachine targetArchitecture) {
//		this.targetArchitecture = targetArchitecture;
//	}
//
//	private void printInstructionList(Vector<AssemblyInstruction> functionBodyCode){
//		LOG.debug("INSTRUCTION LIST:");
//		for(AssemblyInstruction ai: functionBodyCode){
//			if(ai == null){
//				LOG.debug("Null here");
//			}
//			else{
//				LOG.debug(ai.toString());
//			}
//		}		
//	}
//
//	public HashMap<String, HashSet<Temp>> getColorVsTemps() {
//		return colorVsTemps;
//	}

}

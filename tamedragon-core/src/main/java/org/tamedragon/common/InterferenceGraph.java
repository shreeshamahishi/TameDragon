package org.tamedragon.common;

import java.io.File;
import java.io.FileWriter;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import org.jgrapht.graph.SimpleGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tamedragon.common.llvmir.instructions.AssemblyInstruction;
import org.tamedragon.common.llvmir.types.Operand;
import org.tamedragon.common.llvmir.types.Temp;
import org.tamedragon.common.llvmir.types.Value;

public class InterferenceGraph // extends SimpleGraph<Value, Value>
{
	
	private static final Logger LOG = LoggerFactory.getLogger(InterferenceGraph.class);
	
	public InterferenceGraph(Class edgeClass, Value src, Value dest){
		
	}
//	private ControlFlowGraph cfg;
//	private BitSet liveIn[];
//	private BitSet liveOut[];
//
//	private int numNodes;
//	private int numTemps;
//
//	private HashMap<String, Node> keyVsNodes;
//	private HashMap<Node, Temp> nodeVsTemps;
//	private Vector<Temp> igTemps;
//	private HashMap<Node, HashSet<Node>> adjacencyMatrix;    // Needed by the register allocator
//	private HashMap<Node, HashSet<Node>> moveRelatedNodes;   // Needed by the register allocator
//
//	private HashMap<Temp, Vector<Integer[]>> tempVsLiveRanges;   // Needed by the register allocator;
//																// will hold a map of each temporary against
//															   // a list of it's live ranges
//
//	private HashMap<Temp, Integer[]> tempVsPrevDefUsePair; // A map of each temporary vs. an two-element
//										  // array of Integers; the first elements holds the line number
//										  // the temp has been defined and the second holds the current use.
//
//	private Vector<CFEdge> edges;
//	private HashMap<Node, String> nodesVsSpillCosts;
//	private int maxSpillCostForAnyTemp;
//
//	public InterferenceGraph(ControlFlowGraph cfg)
//	{
//		super(cfg.getVariables().size());
//		this.cfg = cfg;
//
//		numNodes = cfg.getNumNodes();
//		numTemps = cfg.getVariables().size();
//		igTemps = cfg.getVariables();
//		liveIn = new BitSet[numNodes];
//		liveOut = new BitSet[numNodes];
//		for(int i = 0; i < numNodes; i++){
//			liveIn[i] = new BitSet(numTemps);
//			liveIn[i].clear();
//			liveOut[i] = new BitSet(numTemps);
//			liveOut[i].clear();
//		}
//
//		keyVsNodes = new HashMap<String, Node>();
//		nodeVsTemps = new HashMap<Node, Temp>();
//		adjacencyMatrix = new HashMap<Node, HashSet<Node>>();
//		moveRelatedNodes = new HashMap<Node, HashSet<Node>>();
//		tempVsLiveRanges = new HashMap<Temp, Vector<Integer[]>>();
//		tempVsPrevDefUsePair = new HashMap<Temp, Integer[]>();
//		nodesVsSpillCosts = new HashMap<Node, String>();
//		edges = new Vector<CFEdge>();
//		createInterferenceGraph();
//	}
//
//	/*
//	 * This method will reset all the attributes of the interference graph, so the register
//	 * allocator can start all over again. Does not reset live-in and live-out information since this
//	 * is not required
//	 *
//	 */
//
//	public void reset()
//	{
//		numNodes = 0;
//		numTemps = 0;
//		igTemps = new Vector<Temp>();
//
//		keyVsNodes = new HashMap<String, Node>();
//		nodeVsTemps = new HashMap<Node, Temp>();
//		adjacencyMatrix = new HashMap<Node, HashSet<Node>>();
//		moveRelatedNodes = new HashMap<Node, HashSet<Node>>();
//		tempVsLiveRanges = new HashMap<Temp, Vector<Integer[]>>();
//		tempVsPrevDefUsePair = new HashMap<Temp, Integer[]>();
//		nodesVsSpillCosts = new HashMap<Node, String>();
//	}
//
//	private void createInterferenceGraph()
//	{
//		int count = 0;
//
//		BitSet liveInTemp[] = new BitSet[numNodes];
//		BitSet liveOutTemp[] = new BitSet[numNodes];
//
//		do
//		{
//			// Start by iterating backwards
//			for(int i = numNodes -1; i >= 0; i--)
//			{
//				liveInTemp[i] = (BitSet) liveIn[i].clone();
//				liveOutTemp[i] = (BitSet) liveOut[i].clone();
//
//				ControlFlowNode node = (ControlFlowNode) cfg.getNode(i);
//				//ControlFlowNode node = (ControlFlowNode) cfg.getNodeWithName("" +i);
//
//				if(node == null) continue;
//				AssemblyInstruction instr = (AssemblyInstruction) node.getInstructions().elementAt(0);
//
//				liveOut[i] = getLiveOutForNode(node);
//				liveIn[i] = getLiveInForNode(node, i);
//			}
//
//			count++;
//		}while(!isEndOfIterations(liveInTemp, liveOutTemp));
//
//		// Now that live in and live out are calculated for each node, create the
//		// interference graph. This is done by adding edges from the def temporaries to
//		// the live-out temporaries for each node
//		for(int i = 0; i < numNodes; i++)
//		{
//			ControlFlowNode cfgNode = (ControlFlowNode) cfg.getNode(i);
//			if(cfgNode == null) continue;
//			AssemblyInstruction instr = (AssemblyInstruction)
//						(cfgNode.getInstructions().elementAt(0)); // A node for this graph has only one instruction
//
//			Vector<Temp> defList = instr.getDestList();
//			Vector<Operand> useList = instr.getSrcList();
//			populateLiveRangeInfo(i, defList, useList);
//			boolean isMove = instr.isMove();
//
//			Vector<Temp> liveOutTemps = getLiveOutTemps(i);
//
//			if(defList == null)  continue;
//			int numDefs = defList.size();
//			for(int j = 0; j < numDefs; j++) {
//				Temp from = (Temp) defList.elementAt(j);
//				if(from.isPhysicalRegister()) 
//					continue;   // This is a machine register; ignore it
//				Node fromNode = newInterferenceGraphNode(from);
//
//				// If it is a move, add to move-related nodes
//				if(isMove) {
//					for(int a = 0; a < useList.size(); a++) {
//						Temp toTemp = (Temp)useList.elementAt(a);
//						if(toTemp.isPhysicalRegister()) 
//							continue; // This is a machine register; ignore it
//
//						Node toNode = newInterferenceGraphNode(toTemp);
//						addToMoveRelatedNodes(fromNode, toNode);
//					}
//				}
//
//				// Add interference edges for this destination node to each of the live-outs
//				int numLiveOuts = liveOutTemps.size();
//				for(int k = 0; k < numLiveOuts; k++) {
//					Temp to = liveOutTemps.elementAt(k);
//					if(to.isPhysicalRegister()) 
//						continue; // This is a machine register; ignore it
//					if(from == to) continue;
//
//					Node toNode = newInterferenceGraphNode(to);
//
//					// Add an interference edge if it is not a move
//					if(!isMove) {
//						updateAdjacencyMatrix(fromNode, toNode);
//					}
//					else {
//						if(!useList.contains(to)) updateAdjacencyMatrix(fromNode, toNode);
//					}
//				}
//			}
//		}
//		// Now that the interference graph has been created, populate the spill costs
//		populateSpillCosts();
//	}
//
//	private BitSet getLiveInForNode(ControlFlowNode node, int index){
//		BitSet liveInForNode = new BitSet(numTemps);
//		liveInForNode.clear();
//
//		BitSet uses = cfg.getUsesBitVector(node);
//		BitSet defs = cfg.getDefsBitVector(node);
//
//		BitSet liveOutForNode = liveOut[index];
//		BitSet usesClone = (BitSet) uses.clone();
//		BitSet liveOutForNodeClone = (BitSet) liveOutForNode.clone();
//
//		liveOutForNodeClone.andNot(defs);
//		usesClone.or(liveOutForNodeClone);
//		liveInForNode = usesClone;
//
//		return liveInForNode;
//	}
//
//	private BitSet getLiveOutForNode(Node node)
//	{
//		BitSet liveOutForNode = new BitSet(numTemps);
//		liveOutForNode.clear();
//
//		Vector succs = node.getSuccessors();
//		if(succs != null && succs.size() > 0)
//		{
//			for(int j = 0; j < succs.size(); j++)
//			{
//				Node succ = (Node) succs.elementAt(j);
//				int index = getNodeIndexFromNode(succ);
//				BitSet succLiveIn = liveIn[index];
//				liveOutForNode.or(succLiveIn);
//			}
//		}
//		return liveOutForNode;
//	}
//
//	private boolean isEndOfIterations(BitSet liveInTemp[], BitSet liveOutTemp[]){
//		for(int i = 0; i < numNodes; i++){
//			BitSet tempIn = liveInTemp[i];
//			BitSet tempOut = liveOutTemp[i];
//
//			for(int j = 0; j < numTemps; j++){
//				if(tempIn.get(j) != liveIn[i].get(j)) return false;
//				if(tempOut.get(j) != liveOut[i].get(j)) return false;
//			}
//		}
//		return true;
//	}
//
//	private int getNodeIndexFromNode(Node node){
//		for(int i = 0; i < numNodes; i++){
//			Node nd = (Node) cfg.getNode(i);
//			if(nd == node) return i;
//		}
//		return -1;
//	}
//
//	private int getTempIndexFromTemp(Temp temp){
//		int i;
//		for(i = 0; i < numTemps; i++){
//			Temp t = igTemps.elementAt(i);
//			if(t.equals(temp)) return i;
//		}
//		igTemps.addElement(temp);
//
//		numTemps = igTemps.size();
//
//		return i;
//	}
//
//	public void addToMoveRelatedNodes(Node from, Node to){
//		HashSet<Node> destNodesForFrom = moveRelatedNodes.get(from);
//		if(destNodesForFrom == null)  destNodesForFrom = new HashSet<Node>();
//		destNodesForFrom.add(to);
//		moveRelatedNodes.put(from, destNodesForFrom);
//
//		HashSet<Node> destNodesForTo = moveRelatedNodes.get(to);
//		if(destNodesForTo == null)  destNodesForTo = new HashSet<Node>();
//		destNodesForTo.add(from);
//		moveRelatedNodes.put(to, destNodesForTo);
//	}
//
//	private Vector<Temp> getLiveOutTemps(int index){
//		Vector<Temp> liveOutTemps = new Vector<Temp>();
//		BitSet liveOutBitSet = liveOut[index];
//		for(int i = 0; i < numTemps; i++){
//			if(liveOutBitSet.get(i)) liveOutTemps.addElement(cfg.getTemp(i));
//		}
//		return liveOutTemps;
//	}
//
//	public Node newInterferenceGraphNode(Temp temp){
//		int tempIndex = getTempIndexFromTemp(temp);
//		String id = "" + tempIndex;
//
//		Node node = keyVsNodes.get(id);
//
//		if(node != null) return node;
//
//		//	This node does not exist already; create a new one
//		return createNewNode(temp, id);
//	}
//
//	public Node createNewNode(Temp temp, String id){
//		Node node = super.newNode(id);
//
//		keyVsNodes.put(id, node);
//		nodeVsTemps.put(node, temp);
//		addNode(node, -1);
//		return node;
//	}
//
//	public Temp getTemp(Node node){
//		return nodeVsTemps.get(node);
//	}
//
//	public int getNumTemps(){
//		return nodeVsTemps.size();
//	}
//
//	public void updateAdjacencyMatrix(Node fromNode, Node toNode){
//		// Add "toNode" to the adjacent list of "fromNode"
//		HashSet<Node> adjListForFromNode = adjacencyMatrix.get(fromNode);
//		if(adjListForFromNode == null) adjListForFromNode = new HashSet<Node>();
//		adjListForFromNode.add(toNode);
//		adjacencyMatrix.put(fromNode, adjListForFromNode);
//
//		// Add "fromNode" to the adjacent list of "toNode"
//		HashSet<Node> adjListForToNode = adjacencyMatrix.get(toNode);
//		if(adjListForToNode == null) adjListForToNode = new HashSet<Node>();
//		adjListForToNode.add(fromNode);
//		adjacencyMatrix.put(toNode, adjListForToNode);
//
//		CFEdge edge = new CFEdge(fromNode, toNode);
//		edge.setPositionInGraph(edges.size() - 1);
//		edges.add(edge);
//	}
//
//	public Vector<Temp> getIgTemps(){
//		return igTemps;
//	}
//
//	public HashSet<Node> getAdjacentNodeList(Node node){
//		return adjacencyMatrix.get(node);
//	}
//
//	public void removeNode(Node node){
//		// Removes the edges, from the adjacency matrix but not from the node list
//		HashSet adjacentList = adjacencyMatrix.get(node);  // Get the adjacent nodes
//		if(adjacentList != null){
//			// (It could be null if two nodes that coalesce to create this input node do
//			// not have any neighbors)
//			Iterator iter = adjacentList.iterator();
//			while(iter.hasNext()){
//				Node toNode = (Node) iter.next();
//				HashSet adjacentListForTo = adjacencyMatrix.get(toNode);
//				adjacentListForTo.remove(node);
//			}
//		}
//
//		// Remove from the adjacency matrix also
//		adjacencyMatrix.remove(node);
//
//		// Remove from the node Vs. Temps map too
//		nodeVsTemps.remove(node);
//
//	}
//
//	public HashSet<Node> getMoveRelatedNodes(Node nd){
//		return moveRelatedNodes.get(nd);
//	}
//
//	private void populateLiveRangeInfo(int line, Vector defList, Vector useList){
//		// Inspect the defintions
//		if(defList != null){
//			int numDefs = defList.size();
//
//			for(int i = 0; i < numDefs; i++){
//				Temp tempDef = (Temp) defList.elementAt(i);
//				Integer[] prevDefUsePair = tempVsPrevDefUsePair.get(tempDef);
//				if(prevDefUsePair == null){
//					// This is the first time the temp is being defined; create the def-use pair
//					Integer [] defUsePair = new Integer[2];
//					defUsePair[0] = new Integer(line);
//					defUsePair[1] = null;
//					tempVsPrevDefUsePair.put(tempDef, defUsePair);
//				}
//				else{
//					// This temp. has been defined (and probably used earlier). Check if its been used earlier
//					if(prevDefUsePair[1] == null){
//						// This temp is being redefined before getting used; update the def index
//						prevDefUsePair[0] = new Integer(line);
//					}
//					else{
//						// This temp is being redefined after it has been defined and used; create the new live range
//						// and update the new def-use pair
//						addToLiveRanges(tempDef, prevDefUsePair);
//
//						Integer [] defUsePair = new Integer[2];
//						defUsePair[0] = new Integer(line);
//						defUsePair[1] = null;
//						tempVsPrevDefUsePair.put(tempDef, defUsePair);
//					}
//				}
//			}
//		}
//
//		if(useList != null){
//			// Inspect the usages
//			int numUses = useList.size();
//			for(int i = 0; i < numUses; i++){
//				Operand operandUse = (Operand) useList.elementAt(i);
//				if(operandUse.getOperandType() == Operand.NUMERIC_TYPE)
//					continue;
//				
//				Temp tempUse = (Temp) operandUse;
//				Integer[] prevDefUsePair = tempVsPrevDefUsePair.get(tempUse);
//				if(prevDefUsePair == null){
//					// Temp is being used without being defined; ignore the def, could be a physical register
//					Integer [] defUsePair = new Integer[2];
//					defUsePair[0] = null;
//					defUsePair[1] = new Integer(line);
//					tempVsPrevDefUsePair.put(tempUse, defUsePair);
//				}
//				else{
//					// Update the latest usage linenum
//					prevDefUsePair[1] = new Integer(line);
//				}
//				// Update the live ranges map for the temp
//				addToLiveRanges(tempUse, prevDefUsePair);
//			}
//		}
//	}
//
//	public void addToLiveRanges(Temp temp, Integer[] defUsePair){
//		Vector<Integer[]> liveRanges = tempVsLiveRanges.get(temp);
//		if(liveRanges == null) liveRanges = new Vector<Integer[]>();
//		liveRanges.addElement(defUsePair);
//		tempVsLiveRanges.put(temp, liveRanges);
//	}
//
//	public void populateSpillCosts(){
//		Vector<Node> igNodes = nodes();
//		int numIgNodes = igNodes.size();
//		for(int n = 0; n < numIgNodes; n++)
//		{
//			Node igNode = igNodes.elementAt(n);
//			Temp temp = nodeVsTemps.get(igNode);
//
//			// Get the maximum live range for this node
//			int maxLiveRange = 0;
//			Vector liveRanges = tempVsLiveRanges.get(temp);
//			if(liveRanges != null) // Could be null for reserved registers like $FP, $SP
//			{
//				int numLiveRanges = liveRanges.size();
//				for(int i = 0; i < numLiveRanges; i++)
//				{
//					Integer[] defUse = (Integer[])liveRanges.elementAt(i);
//					if(defUse != null)
//					{
//						int defLine = 0, useLine = 0;
//						if(defUse[0] != null) defLine = defUse[0].intValue();
//						if(defUse[1] != null) useLine = defUse[1].intValue();
//
//						int diff = Math.abs(useLine - defLine);
//						if(diff > maxLiveRange) maxLiveRange = diff;
//					}
//				}
//
//				// Get the degree of this temp node
//				int degree = getDegree(igNode);
//				int spillCost = degree * maxLiveRange;
//
//				// Populate the node vs. spill cost and the max spill cost for any temp node
//				nodesVsSpillCosts.put(igNode, "" + spillCost);
//				if(spillCost >= maxSpillCostForAnyTemp) maxSpillCostForAnyTemp = spillCost;
//			}
//		}
//	}
//
//	public String getSpillCost(Node node){
//		String spillCost = nodesVsSpillCosts.get(node);
//		if(spillCost == null) return "-1";
//		return spillCost;
//	}
//
//	public void setSpillCost(Node node, String cost){
//		nodesVsSpillCosts.put(node, cost);
//	}
//
//	public int getMaxSpillCost(){
//		return maxSpillCostForAnyTemp;
//	}
//
//	public int getDegree(Node node){
//		HashSet neighbors = adjacencyMatrix.get(node);
//		if(neighbors != null) return neighbors.size();
//		return 0;
//	}
//
//	public void showMoveRelatedNodes(){
//		LOG.debug("MOVE RELATED NODES: ");
//		String str = "";
//		for(int i = 0; i < numTemps; i++)
//		{
//			Node node = keyVsNodes.get("" + i);
//			if(node == null) continue;
//			Temp temp = nodeVsTemps.get(node);
//			if(temp == null) continue;
//
//			HashSet mvNodes = moveRelatedNodes.get(node);
//			str += temp.toString() + " is move-related to: ";
//			if(mvNodes != null)
//			{
//				Iterator iter = mvNodes.iterator();
//				while(iter.hasNext())
//				{
//					Node nxt = (Node) iter.next();
//					Temp tp = nodeVsTemps.get(nxt);
//					str += tp.toString() + " ";
//				}
//			}
//			 str += "\n";
//		}
//
//		LOG.debug(str);
//	}
//
//	public void showLiveRangeInfo(){
//		LOG.debug("LIVE RANGE INFO: ");
//
//		for(int i = 0; i < nodes().size(); i++)
//		{
//			Node nd = nodes().elementAt(i);
//			Temp tmp = nodeVsTemps.get(nd);
//			LOG.debug(tmp.toString() + ": " );
//
//			Vector liveRanges = tempVsLiveRanges.get(tmp);
//			String rangeStr = "";
//			if(liveRanges != null)
//			{
//				for(int a = 0; a < liveRanges.size();a++)
//				{
//					Integer[] range = (Integer[]) liveRanges.elementAt(a);
//					if(range != null)
//					{
//						String from = "?";
//						String to = "?";
//						if(range[0] != null) from = "" + range[0].intValue();
//						if(range[1] != null) to = "" + range[1].intValue();
//						rangeStr += "[" + from + " - " + to + "] ";
//					}
//				}
//			}
//			rangeStr += " Spill priority = " + nodesVsSpillCosts.get(nd);
//			LOG.debug(rangeStr);
//		}
//	}
//
//	public void showInterferenceGraph(){
//		Vector desc = getDescriptionOfGraph();
//		for(int i = 0; i < desc.size(); i++){
//			LOG.debug((String)desc.elementAt(i));
//		}
//	}
//
//	public void saveInterferenceGraphInFile(File outFile){
//		Vector des = getDescriptionOfGraph();
//		try {
//			FileWriter fileWriter = new FileWriter(outFile);
//			int numDescs = des.size();
//			for (int i = 0; i < numDescs; i++) {
//
//				String str = (String)des.elementAt(i);
//				fileWriter.write(str);
//			}
//			fileWriter.flush();
//			fileWriter.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	private Vector getDescriptionOfGraph(){
//		Vector description = new Vector();
//		String str = "";
//		LOG.debug("Number of nodes  =" + nodeVsTemps.size());
//		LOG.debug("From another angle, Number of nodes  =" + nodes().size());
//
//		Set entries = nodeVsTemps.entrySet();
//		Iterator iter = entries.iterator();
//		while(iter.hasNext())
//		{
//			Map.Entry entry = (Map.Entry) iter.next();
//
//			Node node = (Node) entry.getKey();
//			if(node == null) continue;
//			Temp temp = (Temp) entry.getValue();
//			if(temp == null) continue;
//			str += temp.toString() + " has edges to: ";
//
//			HashSet nbrs = adjacencyMatrix.get(node);
//			if(nbrs == null)
//			{
//				str += "\n";
//				continue;
//			}
//			Iterator iter1 = nbrs.iterator();
//			while(iter1.hasNext())
//			{
//				Node nxt = (Node) iter1.next();
//				Temp tp = nodeVsTemps.get(nxt);
//				str += tp.toString() + " ";
//			}
//
//			 str += "\n";
//		}
//
//		description.addElement(str);
//		return description;
//	}
//
//	public void showLiveInAndLiveOut(){
//		for(int i = 0; i < numNodes; i++)
//		{
//			String displayForNode = i + ": ";
//
//			BitSet li = liveIn[i];
//			BitSet lo = liveOut[i];
//
//			String liveInStr = "Live In -> ";
//			String liveOutStr = "  Live Out -> ";
//			for(int j = 0; j < numTemps; j++)
//			{
//				if(li.get(j))
//				{
//					liveInStr += cfg.getTemp(j).toString() + " ";
//				}
//				if(lo.get(j))
//				{
//					liveOutStr += cfg.getTemp(j).toString() + " ";
//				}
//			}
//
//			displayForNode += liveInStr + liveOutStr;
//
//			LOG.debug(displayForNode);
//		}
//	}
//
//
//	public  Node getStartNode()
//	{
//		return cfg.getStartNode();
//	}
//
//	public int getNumEdges() {
//		return edges.size();
//	}
//
//	public CFEdge getEdge(int num) {
//		return edges.get(num);
//	}
//
//	public boolean hasChild(Node parent, Node child)
//	{
//		HashSet<Node> set = getAdjacentNodeList(parent);
//		if (set == null || set.size() <= 0)
//			return false;
//		return set.contains(child);
//	}
}

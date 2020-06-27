package org.tamedragon.common.optimization;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Vector;

import org.jgraph.graph.DefaultEdge;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tamedragon.common.DominatorTree;
import org.tamedragon.common.controlflowanalysis.DominatorCalculationContext;
import org.tamedragon.common.llvmir.instructions.AllocaInst;
import org.tamedragon.common.llvmir.instructions.BinaryOperator;
import org.tamedragon.common.llvmir.instructions.CFG;
import org.tamedragon.common.llvmir.instructions.CastInst;
import org.tamedragon.common.llvmir.instructions.CmpInst;
import org.tamedragon.common.llvmir.instructions.FCmpInst;
import org.tamedragon.common.llvmir.instructions.GetElementPtrInst;
import org.tamedragon.common.llvmir.instructions.ICmpInst;
import org.tamedragon.common.llvmir.instructions.Instruction;
import org.tamedragon.common.llvmir.instructions.PhiNode;
import org.tamedragon.common.llvmir.instructions.BinaryOperator.BinaryOperatorID;
import org.tamedragon.common.llvmir.instructions.CmpInst.Predicate;
import org.tamedragon.common.llvmir.instructions.Instruction.InstructionID;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionDetailAccessException;
import org.tamedragon.common.llvmir.math.APInt;
import org.tamedragon.common.llvmir.math.ULong;
import org.tamedragon.common.llvmir.types.Argument;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.CompilationContext;
import org.tamedragon.common.llvmir.types.Constant;
import org.tamedragon.common.llvmir.types.ConstantInt;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.IntegerType;
import org.tamedragon.common.llvmir.types.Type;
import org.tamedragon.common.llvmir.types.Value;

public class GVN {

	private DefaultDirectedGraph<Value, ValueGraphEdge> valueGraph;
	private List<Set<Value>> b;
	private  LinkedHashSet<Integer> workList;   
	private CFG cfg;
	private Function function;

	private static final Logger LOG = LoggerFactory.getLogger(GVN.class);
	
	class ValueGraphEdge extends DefaultEdge {

		private int operandPosition ;

		public void setOperandPosition(int operandPosition) {
			this.operandPosition = operandPosition;
		}

		public int getOperandPosition() {
			return operandPosition;
		}
	}
	
	public GVN(){
		b = new ArrayList<Set<Value>>();
		workList = new LinkedHashSet<Integer>();
	}

	/** Optimize the CFG by based on GVN algorithm
	 *@param controlFlowGraph
	 **/
	public int execute(Function function) throws InstructionDetailAccessException {
		
		if(function.getNumBasicBlocks() == 0){
			// Must be a declaration or empty function
			return -1;
		}
		
		this.function = function;
		this.cfg = function.getCfg();
		ValueGraphCreator valueGraphCreator = new ValueGraphCreator();
		Set<Value> s = new LinkedHashSet<Value>();
		valueGraphCreator.createValueGraphFromCFG();
		valueGraph = valueGraphCreator.getValueGraph();
		List<Value> firstNodeOprndList = new ArrayList<Value>();
		List<Value> curentNodeOprndList = new ArrayList<Value>();
		Value x = null;
		Value m = null;
		//Vector<ValueGraphNode> graphNodes = valueGraph.getAllNodes();

		int p = initializePartition();
		int i = 0, previous = -1  ;
		int iterationCount = 0;
		do{

			LOG.info("GVN iteration count = "  + iterationCount);
			
			firstNodeOprndList = new ArrayList<Value>();
			boolean isCommutative = false;
			Iterator<Integer> iterator = workList.iterator();
			if(iterator.hasNext()){
				i = iterator.next();
				if(i == previous && workList.size()>1)
					i = iterator.next();
				previous = i;
			}
			workList.remove(i);

			Set<Value> a = b.get(i);
			Iterator<Value> iterator1 = a.iterator();
			if(iterator1.hasNext())
				m = iterator1.next();

			p++;
			Set<Value> set = new LinkedHashSet<Value>();
			set.add(m);
			b.add(p, set);
			b.get(i).remove(m);

			int arityOfm = getArity(m);	

			for(int j=1; j <= arityOfm; j++){
				Value node = followEdge(m, j);				
				firstNodeOprndList.add(node);
			}

			s = new LinkedHashSet<Value>(b.get(i));
			s.remove(m); 

			while(!s.isEmpty()){
				curentNodeOprndList =  new ArrayList<Value>();
				List<Value> reveseOprandList = new ArrayList<Value>();
				Iterator<Value> itr = s.iterator();
				if(itr.hasNext())
					x = itr.next();
				s.remove(x);

				int arityOfx = getArity(x);					
				for(int j = 1;j <= arityOfx;j++){
					Value node = followEdge(x, j);
					curentNodeOprndList.add(node);
				}

				if(m instanceof BinaryOperator && x instanceof BinaryOperator){
					BinaryOperator binaryOperator = (BinaryOperator) m;
					BinaryOperatorID binaryOperatorID = binaryOperator.getOperatorID();
					boolean isCommutativeOpr = BinaryOperator.isCommutative(binaryOperatorID);
					if(isCommutativeOpr){
						Value firstOprNode = curentNodeOprndList.get(0);
						Value secondOprNode = curentNodeOprndList.get(1);
						reveseOprandList.add(0, secondOprNode);
						reveseOprandList.add(1, firstOprNode);
						isCommutative = isSameOperatorForList(firstNodeOprndList, reveseOprandList);;
					}
				}

				boolean isSameOperator = true;
				isSameOperator = isSameOperatorForList(firstNodeOprndList, curentNodeOprndList);
				if(isSameOperator || isCommutative){
					set.add(x);
					b.get(i).remove(x);
					boolean isArityMoreThanOne = isArityMoreThanOne(firstNodeOprndList);
					if(set.size() > 1 && isArityMoreThanOne){
						boolean isCongrugentOperands;
						if(isCommutative){
							isCongrugentOperands = isCongrugentOperandsforList(firstNodeOprndList, reveseOprandList);
						}
						else{
							isCongrugentOperands = isCongrugentOperandsforList(firstNodeOprndList, curentNodeOprndList);
						}
						if(!isCongrugentOperands && !workList.isEmpty())
							workList.add(p);
						else if(!isCongrugentOperands && workList.isEmpty())// if it is last partition
						{
							set.remove(x); //set.add(x);
							b.get(i).add(x); //	b.get(i).remove(x);
						}
					}
					isCommutative = false;				
				}
			}

			if(b.get(i).isEmpty()){
				b.remove(i);
				int inWorkList = p;
				p--;
				if(workList.contains(inWorkList)){
					workList.remove(inWorkList);
					workList.add(i);
				}
				b.remove(p);
				b.add(i, set);
			}
			else if(b.get(i).size()>1)
			{
				workList.add(i);
			}
			
			iterationCount++;
			
		}while(!workList.isEmpty());

		//		now Replace The congruent node's  
		try {
			removeCongrugentNodes();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
		return p;
	}


	private boolean isCongrugentOperandsforList(List<Value> firstNodeOprndList, List<Value> curentNodeOprndList) {
		if(firstNodeOprndList.size() != curentNodeOprndList.size())
			return false;

		int numOfOprnds = firstNodeOprndList.size();
		for(int i = 0; i < numOfOprnds; i++){
			Value node1 = firstNodeOprndList.get(i);
			Value node2 = curentNodeOprndList.get(i);
			if(!hasCongurant(node1, node2))
				return false;
		}
		return true;
	}

	private boolean isArityMoreThanOne(List<Value> firstNodeOprndList) {
		int numOfOprnds = firstNodeOprndList.size();
		for(int i = 0; i < numOfOprnds; i++){
			Value node1 = firstNodeOprndList.get(i);
			if(getArity(node1) > 1)
				return true;
		}

		return false;
	}

	private boolean isSameOperatorForList(List<Value> firstNodeOprndList, List<Value> curentNodeOprndList) {
		if(firstNodeOprndList.size() != curentNodeOprndList.size())
			return false;

		int numOfOprnds = firstNodeOprndList.size();
		for(int i = 0; i < numOfOprnds; i++){
			Value node1 = firstNodeOprndList.get(i);
			Value node2 = curentNodeOprndList.get(i);
			
			if(!hasSameOperator(node1, node2))
				return false;
		}
		return true;
	}

	/** Check whether the given nodes are congruent or not
	 *@param ValueGraphNode;
	 *@param ValureGraphNode
	 *	 **/
	private boolean hasCongurant(Value m, Value x) {
		List<Set<Value>> purePartions = new ArrayList<Set<Value>>(b);
		List<Set<Value>> allPartions = new ArrayList<Set<Value>>(b);
		LinkedHashSet<Integer> workListtemp = new LinkedHashSet<Integer>(workList);
		Iterator<Integer> iterator = workListtemp.iterator();
		while(iterator.hasNext()){
			Integer i = iterator.next();
			Set<Value> nonCongurentPartition = allPartions.get(i);
			purePartions.remove(nonCongurentPartition);
		}
		Iterator<Set<Value>> outerIterator = purePartions.iterator();
		while (outerIterator.hasNext()) {
			Set<Value> set = (Set<Value>) outerIterator.next();
			if(set.contains(m) && set.contains(x)){
				return true;
			}
		}

		return false;
	}



	/** @return ValueGraphNode of given Node in 
	 *@param ValueGraphNode;
	 *@param int Operand Position
	 *	 **/
	private Value followEdge(Value m, int j) {
		Set<ValueGraphEdge> edges = valueGraph.outgoingEdgesOf(m);
		for(ValueGraphEdge edge : edges){
			if(edge.getOperandPosition() == j){
				return valueGraph.getEdgeTarget(edge);
			}
		}
		return null;
	}

	private int initializePartition() {

		Set<Value> graphNodes = valueGraph.vertexSet();

		//		ValueGraphNode v;
		int  i = -1,k = -1;

		for(Value v : graphNodes){
			if(eliminateIfDeadCode(v))
				continue;
			i = 0;
			while(i<= k)
			{
				Set<Value> nodes = b.get(i);
				Iterator<Value> iterator = nodes.iterator();
				if(iterator.hasNext());
				Value node = iterator.next();
				if(!(v instanceof AllocaInst && node instanceof AllocaInst)){
					if(hasSameOperator(v,node)) {
						nodes.add(v);
						if(getArity(v)>0 && nodes.size()>1){
							workList.add(i);
						}

						i = k+1;
					}}
				i++;
			}
			if(i == k+1){
				k = k+1;
				Set<Value> set = new LinkedHashSet<Value>();
				set.add(v);
				b.add(k, set);
				//				b.set(k, set);
			}
		}
		return k;

	}


	/** Check whether the given nodes has of Same Type;
	 *@param ValueGraphNode;
	 *@param ValureGraphNode
	 *	 **/
	private boolean hasSameOperator(Value v, Value node) {
		if(node instanceof BinaryOperator && v instanceof BinaryOperator){
			BinaryOperator binaryOperator = (BinaryOperator)v;
			BinaryOperatorID binaryOperatorID1 = binaryOperator.getOperatorID();
			binaryOperator = (BinaryOperator)node;
			BinaryOperatorID binaryOperatorID2 = binaryOperator.getOperatorID();

			if(binaryOperatorID1 == binaryOperatorID2){
				return true;
			}

		}else if(node instanceof PhiNode && v instanceof PhiNode){
			PhiNode phiNode = (PhiNode) v;
			PhiNode phiNode1 = (PhiNode)node;
			if(phiNode == phiNode1)
				return true;
		}
		else if(node instanceof Constant && v instanceof Constant){
			Constant constant1 = (Constant) node;
			Constant constant2 = (Constant) v;
			if(constant1.equals(constant2))
				return true;
		}

		else if(node instanceof Argument && v instanceof Argument){
			Argument argument1 = (Argument) node;
			Argument argument2 =  (Argument) v;
			if(argument2.equals(argument1))
				return true;
		}

		else if(node instanceof CastInst && v instanceof CastInst){
			CastInst castInst1 = (CastInst) node;
			CastInst castInst2 =  (CastInst) v;
			if(castInst1.getInstructionID() == castInst2.getInstructionID())
				return true;
		}
		else if(node instanceof GetElementPtrInst && v instanceof GetElementPtrInst){
			GetElementPtrInst ptrInst1 = (GetElementPtrInst) node;
			GetElementPtrInst ptrInst2 = (GetElementPtrInst) v;
			if(ptrInst1.getPointerOperand() == ptrInst2.getPointerOperand()){
				return true;
			}
		}
		else if(node instanceof AllocaInst && v instanceof AllocaInst){
			AllocaInst allocaInst1 = (AllocaInst) node;
			AllocaInst allocaInst2 = (AllocaInst) v;
			if(allocaInst1 == allocaInst2)
				return true;

		}
		else if(node.getValueTypeID()== null && v.getValueTypeID()== null){
			Value value1 = node;
			Value value2 = v;
			if(value1.getType().getTypeId() == value2.getType().getTypeId())
				return true;
		}

		return false;
	}

	private void removeCongrugentNodes() throws InstantiationException{
		Iterator<Set<Value>> bIterator = b.iterator();

		DominatorTree dominatorTree = null;
		DominatorCalculationContext dominatorCalculationContext = new DominatorCalculationContext(function);
		dominatorCalculationContext.setIterativeMethod();
		dominatorTree = dominatorCalculationContext.getDominatorTree();

		while (bIterator.hasNext()) {
			Set<Value> nodes = (Set<Value>) bIterator.next();
			Iterator<Value> iterator = nodes.iterator();
			int count = 0;
			Value firstValue = null;
			while(iterator.hasNext() && nodes.size() > 1){
				if(count == 0){
					firstValue = iterator.next();
				}
				else{
					Value value = iterator.next();
					if((value instanceof AllocaInst && firstValue instanceof AllocaInst)||
							(value instanceof ConstantInt && firstValue instanceof ConstantInt))
						break;

					//If bothe values are Instruction then check it's Basic Block Dominance
					if(firstValue instanceof Instruction && value instanceof Instruction){ 
						Instruction firstInstruction = (Instruction) firstValue;
						Instruction valueInstruction = (Instruction) value;

						boolean isDominates = dominatorTree.dominates(firstInstruction.getParent(),valueInstruction.getParent());
						if(!isDominates)
							continue;
					}

					replaceAllValueUsers(firstValue,value);
				}
				count++;
			}
		}
	}

	private void replaceAllValueUsers(Value firstValue, Value value) throws InstantiationException {
		if(value instanceof Instruction && firstValue instanceof Instruction){
			Instruction instruction = (Instruction)value;
			//			if(instruction.getNumUses() == 0)
			Constant constVal = instruction.foldIfPossible();
			if(constVal != null){
				instruction.replaceAllUsesOfThisWith(constVal);
				instruction.eraseFromParent();
				return;
			}

			instruction.replaceAllUsesOfThisWith(firstValue);
			int numUsers = instruction.getNumUses();
			for(int i=0; i < numUsers ;i++)
			{
				Value userInstruction = instruction.getUserAt(i);

				if(userInstruction instanceof PhiNode){
					PhiNode phiNode = (PhiNode) userInstruction;
					int noOfPredessors = phiNode.getNumPredecessors();
					Value previousPredessor = null;
					boolean hasSamePredessor = false;
					for(int j=0; j<noOfPredessors; j++){
						try {
							if(j == 0){
								previousPredessor = phiNode.getIncomingValue(j);
								continue;
							}
							Value currentPredessor = phiNode.getIncomingValue(j);
							if(previousPredessor != currentPredessor){
								hasSamePredessor = false;
								break;
							}
							else
								hasSamePredessor = true;

						} catch (InstructionDetailAccessException e) {
							e.printStackTrace();
						}
					}
					if(hasSamePredessor){
						phiNode.getParent().removeInstruction(phiNode);
						phiNode.replaceAllUsesOfThisWith(previousPredessor);
					}
				}

				if(userInstruction instanceof CmpInst){
					CmpInst cmpInst = null;
					if(userInstruction instanceof ICmpInst)
						cmpInst = (ICmpInst) userInstruction;

					if(userInstruction instanceof FCmpInst)
						cmpInst = (FCmpInst) userInstruction;

					CompilationContext compilationContext = cmpInst.getType().getCompilationContext();
					Predicate predicate = cmpInst.getPredicate();
					if(cmpInst.getOperand(0)== cmpInst.getOperand(1)){
						switch(predicate){
						case ICMP_EQ  :
						case ICMP_SGE  :
						case ICMP_SLE  :
						case ICMP_UGE :
						case ICMP_ULE :
						case FCMP_OEQ :
						case FCMP_OLE: 
						case FCMP_OGE :

						{APInt val = new APInt(1, ULong.valueOf(1), false);
						IntegerType intType =Type.getInt1Type(compilationContext, false);;
						ConstantInt constantIntTrueValue = new ConstantInt(intType, val);
						userInstruction.replaceAllUsesOfThisWith(constantIntTrueValue);
						cmpInst.getParent().removeInstructionFromBasicBlock(cmpInst);
						break;
						}
						case ICMP_NE :
						case ICMP_SLT:
						case ICMP_SGT:
						case ICMP_ULT:
						case ICMP_UGT:
						case FCMP_ONE :
						case FCMP_FALSE :
						case FCMP_OLT :
						case FCMP_OGT :
						{APInt val = new APInt(1, ULong.valueOf(0), false);
						IntegerType intType =Type.getInt1Type(compilationContext, false);;
						ConstantInt constantIntFalseValue = new ConstantInt(intType, val);
						userInstruction.replaceAllUsesOfThisWith(constantIntFalseValue);
						cmpInst.getParent().removeInstructionFromBasicBlock(cmpInst);
						break;
						}

						}

					}
				}

				//				if(userInstruction instanceof BinaryOperator && firstValue  instanceof Constant) {
				//					BinaryOperator binaryOperator = (BinaryOperator) userInstruction;
				//					 BinaryOperatorID operatorID = binaryOperator.getOperatorID();
				//					 Constant constant = (Constant) firstValue;
				//					  int otherOperandIndex = (binaryOperator.getOperand(0) == constant)? 1 : 0; 
				//					 switch (operatorID) {
				//					case ADD:
				//					case FADD:
				//						if(constant.isZero())
				//							binaryOperator.replaceAllUsesOfThisWith(binaryOperator.getOperand(otherOperandIndex));
				//						binaryOperator.getParent().getInstructions().removeElement(userInstruction);
				//						break;
				//					
				//					case MUL:
				//					break;
				//					default:
				//						break;
				//					}
				//				}
			}
			instruction.getParent().removeInstructionFromBasicBlock(instruction);

		}
	}
	private boolean eliminateIfDeadCode(Value value) {
		if(value instanceof BinaryOperator){
			BinaryOperator binaryOperator = (BinaryOperator) value;
			BinaryOperatorID operatorID = binaryOperator.getOperatorID();
			Value firstOpr = binaryOperator.getOperand(0);
			Value secondOpr = binaryOperator.getOperand(1);
			Constant constant = null;
			int otherOperandIndex;
			if(firstOpr instanceof Constant){
				constant = (Constant) firstOpr;
				otherOperandIndex = 1;
			}

			else if(secondOpr instanceof Constant){
				constant = (Constant) secondOpr;
				otherOperandIndex = 0;
			}

			else
				return false;
			switch (operatorID) {
			case ADD:
			case FADD:
				if(constant.isZero())
				{
					binaryOperator.replaceAllUsesOfThisWith(binaryOperator.getOperand(otherOperandIndex));
					binaryOperator.getParent().removeInstructionFromBasicBlock(binaryOperator);
					return true;
				}
				break;
			case SUB:
			case FSUB:
				if(otherOperandIndex == 0 && constant.isZero()){
					binaryOperator.replaceAllUsesOfThisWith(binaryOperator.getOperand(otherOperandIndex));
					binaryOperator.getParent().removeInstructionFromBasicBlock(binaryOperator);
					return true;
				}
				break;
			case MUL:
				break;
			default:
				break;
			}
		}
		return false;
	}


	private int getArity(Value v) {
		return valueGraph.outgoingEdgesOf(v).size();
	}
	
	class ValueGraphCreator{
		private DefaultDirectedGraph<Value, ValueGraphEdge> valueGraph; 
		private Set<Value>  setOfNodes;

		//Default Constructor to initialize the field
		public ValueGraphCreator() {
			valueGraph = new DefaultDirectedGraph<Value, ValueGraphEdge>(ValueGraphEdge.class);

			setOfNodes = new HashSet<Value>();
		}

		/**
		 * This method creates the value graph from a Control Flow Graph
		 */
		public void createValueGraphFromCFG() throws InstructionDetailAccessException{
			BasicBlock startNode = (BasicBlock) cfg.getStartNode();
			if (startNode == null)
				return;

			// Traverse the given CFG in Breadth first Order(BFS)
			// and add it to the visitedNodeList
			ArrayList<BasicBlock> visitedNodeList = new ArrayList<BasicBlock>();

			Queue<BasicBlock> queue = new LinkedList<BasicBlock>() ; 
			queue.clear();
			queue.add(startNode);
			visitedNodeList.add(startNode);

			while(!queue.isEmpty()){
				BasicBlock parentNode = queue.remove();
				//for every basic block add instruction of basic block to the valueGraph;
				addBBToValueGraph(parentNode);
				List<BasicBlock> succesors = cfg.getSuccessors(parentNode);

				Iterator<BasicBlock> iterator = succesors.iterator();
				while(iterator.hasNext()) {
					BasicBlock childNode = iterator.next();
					if(isVisitedNode(childNode, visitedNodeList))
						continue;
					queue.add(childNode);
					visitedNodeList.add(childNode);
				}
			}
		}

		/**
		 * Iterate over all the instructions of the given basic block and add it into the value graph
		 * @param node
		 * @throws InstructionDetailAccessException
		 */

		private void addBBToValueGraph(BasicBlock node) throws InstructionDetailAccessException {
			//get all instruction 
			Iterator<Instruction> allInstrs = node.getInstructions();
			while(allInstrs.hasNext()){
				Instruction instruction = allInstrs.next();
				InstructionID instructionId = instruction.getInstructionID();	
				//			handle it for only specific instruction skip for all other instruction
				switch (instructionId) {
				case FCMP_INST:
				case ICMP_INST:
				case BINARY_INST:
				case BIT_CAST_INST:
				case UI_TO_FP_INST:
				case SI_TO_FP_INST:
				case FP_TO_UI_INST:
				case FP_TO_SI_INST:
				case INT_TO_PTR_INST:
				case PTR_TO_INT_INST:
				case GET_ELEMENT_PTR :
					handleInstr(instruction);
					break;
					//			case PHI_NODE_INST:
					//				handelPhiInstr((PhiNode)instruction);
					//				break;
				default:
					break;
				}
			}
		}
		/**
		 * This method adds the node and edges in the ValueGraph according to the instruction operand and instruction ID.
		 * Instruction ID will become the ValueGraph Node and Operand of Instruction will become another node having 
		 * the incoming edges from instruction in node.
		 * @param instr
		 */

		private void handleInstr(Instruction instr) {
			// Get new valueGraphNode for instruction
			Value node = createNewNode(instr);
			Value currentOpr ;
			int numOfOprend = instr.getNumOperands();

			// For every operand of the instruction create new node add edge from instruction node to operand node
			for(int i = 0;i < numOfOprend ;i++){
				currentOpr = instr.getOperand(i);
				Value OprNode = createNewNode(currentOpr);
				ValueGraphEdge edge = new ValueGraphEdge();
				edge.setOperandPosition(i+1);

				valueGraph.addEdge(node, OprNode, edge);
			}

			// Cast Instruction has only one operand so far create new 
			// valueGraph node crate value from target type of  casting 
			// and  make it as a second node  of instruction node
			Value secondOpr;
			if(instr instanceof CastInst){
				CastInst castInst = (CastInst) instr;
				Type targetType = castInst.getType();
				secondOpr = new Value(targetType);
				Value secondOprNode = createNewNode(secondOpr);
				ValueGraphEdge edge2 = new ValueGraphEdge();
				edge2.setOperandPosition(2);
				valueGraph.addEdge(node, secondOprNode, edge2);
			}
		}

		/**
		 * @return {@link ValueGraphNode}
		 */
		public DefaultDirectedGraph<Value, ValueGraphEdge> getValueGraph() {
			return valueGraph;
		}

		/**
		 * create New valueGraphNode of given value and add it into valueGraph
		 * @param value
		 * @return ValueGraphNode
		 */
		private Value createNewNode(Value value) {
			// For a constant value, create a new node every time 
			if(value instanceof Constant){
				valueGraph.addVertex(value);
				return value;
			}

			// in in setOfNodes for the same value previously Value is created 
			// if  yes, return the previously created value graph node else create new node
			Iterator<Value> iterator = setOfNodes.iterator();
			while (iterator.hasNext()) {
				Value valueGraphNode =  iterator.next();
				if(valueGraphNode  == value)
					return valueGraphNode;
			}

			// Not found, create a new one 
			setOfNodes.add(value);
			valueGraph.addVertex(value);
			return value;
		}

		/**
		 * Returns true if node is already visited else return false
		 */
		private boolean isVisitedNode(BasicBlock v1, ArrayList<BasicBlock> visitedNodeList) {
			Iterator<BasicBlock> iterator = visitedNodeList.iterator(); 
			while(iterator.hasNext()) {
				BasicBlock node = iterator.next();
				if(node == v1)
					return true;
			}
			return false;
		}
	}
	
	
}

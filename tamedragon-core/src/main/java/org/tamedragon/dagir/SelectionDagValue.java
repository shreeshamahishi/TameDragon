package org.tamedragon.dagir;

import org.tamedragon.dagir.valuetypes.ExtendedValueType;

public class SelectionDagValue {

	private SelectionDAGNode Node;       // The node defining the value we are using.
	private int resultNumber;            // Which return value of the node we are using.

	public SelectionDagValue(){
		Node = null;
		resultNumber = 0;
	}

	public SelectionDagValue(SelectionDAGNode Node, int resNo) {
		this.Node = Node;
		this.resultNumber = resNo;
	}

	/// get the index which selects a specific result in the SDNode
	public int getResNo() { 
		return resultNumber; 
	}

	/// get the SDNode which holds the desired result
	public SelectionDAGNode getNode() {
		return Node; 
	}

	/// set the SDNode
	public void setNode(SelectionDAGNode N) {
		Node = N; 
	}

	@Override
	public boolean equals(Object O) {
		if(!(O instanceof SelectionDagValue))
			return false;

		SelectionDagValue sdValue = (SelectionDagValue) O;
		return Node == sdValue.getNode() && resultNumber == sdValue.getResNo();
	}

	public boolean isLesserThan(SelectionDagValue otherSDValue) {
		return Node.isLesserThan(otherSDValue) || (Node == otherSDValue.getNode() && resultNumber < otherSDValue.getResNo());
	}

	public SelectionDAGNode getValue(int R) {
		return new SelectionDAGNode(Node, R);
	}

	// isOperandOf - Return true if this node is an operand of N.
	public boolean isOperandOf(SelectionDAGNode N){
		int numOperands = N.getNumOperands();
		for (int i = 0; i < numOperands; i++){
			if(this == N.getOperand(i))
				return true;
		}

		return false;
	}

	/// getValueType - Return the ValueType of the referenced return value.
	//
	//TODO Implement this
	//inline EVT getValueType() const;

	/// getValueSizeInBits - Returns the size of the value in bits.
	///
	public int getValueSizeInBits() {
		//TODO Implement this
		return 0;
		//return getValueType().getSizeInBits();
	}

	// Forwarding methods - These forward to the corresponding methods in SDNode.

	public int getOpcode() {
		return Node.getOpcode();
	}

	public int getNumOperands() {
		return Node.getNumOperands();
	}

	public SelectionDagValue getOperand(int i) {
		return Node.getOperand(i);
	}

	public long getConstantOperandVal(int i) {
		return Node.getConstantOperandVal(i);
	}

	public boolean isTargetOpcode() {
		return Node.isTargetOpcode();
	}

	public boolean isTargetMemoryOpcode(){
		return Node.isTargetMemoryOpcode();
	}

	public boolean isMachineOpcode(){
		return Node.isMachineOpcode();
	}

	public int getMachineOpcode(){
		return Node.getMachineOpcode();
	}

	public boolean useIsEmpty(){
		return !Node.hasAnyUseOfValue(resultNumber);
	}

	public boolean hasOneUse(){
		return Node.hasNUsesOfValue(1, resultNumber);
	}

	//TODO Implement after implementing EVT
//	public EVT getValueType()  {
//		return Node.getValueType(resultNumber);
//	}

	/// reachesChainWithoutSideEffects - Return true if this operand (which must
	/// be a chain) reaches the specified operand without crossing any
	/// side-effecting instructions.  In practice, this looks through token
	/// factors and non-volatile loads.  In order to remain efficient, this only
	/// looks a couple of nodes in, it does not do an exhaustive search.
	public boolean reachesChainWithoutSideEffects(SelectionDagValue Dest,
			int Depth ){
		if (this == Dest) 
			return true;

		// Don't search too deeply, we just want to be able to see through
		// TokenFactor's etc.
		if (Depth == 0) 
			return false;

		// If this is a token factor, all inputs to the TF happen in parallel.  If any
		// of the operands of the TF does not reach dest, then we cannot do the xform.
		// TODO : Fix after you implement TokenFactor
		// if (getOpcode() == ISD::TokenFactor) {
		if (getOpcode() == 10) {
			int numOperands = getNumOperands();
			for (int i = 0; i < numOperands; i++){
				if (!getOperand(i).reachesChainWithoutSideEffects(Dest, Depth-1))
					return false;
			}
			return true;
		}

		// Loads don't have side effects, look through them.
		// TODO Implement after LoadSDNode is implemented
		//		  if (LoadSDNode *Ld = dyn_cast<LoadSDNode>(*this)) {
		//		    if (!Ld->isVolatile())
		//		      return Ld->getChain().reachesChainWithoutSideEffects(Dest, Depth-1);
		//		  }
		return false;

	}

	public ExtendedValueType getValueType() {
		return Node.getValueType(resultNumber);
	}
}

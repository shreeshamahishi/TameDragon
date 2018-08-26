package org.tamedragon.dagir;

import org.tamedragon.dagir.valuetypes.ExtendedValueType;

public class SelectionDAGNode {

	public SelectionDAGNode(SelectionDAGNode node, int r) {
		// TODO Auto-generated constructor stub
	}

	public boolean isLesserThan(SelectionDagValue otherSDValue) {
		// TODO Auto-generated method stub
		return false;
	}

	public int getNumOperands() {
		// TODO Auto-generated method stub
		return 0;
	}

	public SelectionDagValue getOperand(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getOpcode() {
		// TODO Auto-generated method stub
		return 0;
	}

	public long getConstantOperandVal(int i) {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean isTargetOpcode() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isTargetMemoryOpcode() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isMachineOpcode() {
		// TODO Auto-generated method stub
		return false;
	}

	public int getMachineOpcode() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean hasAnyUseOfValue(int resultNumber) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean hasNUsesOfValue(int i, int resultNumber) {
		// TODO Auto-generated method stub
		return false;
	}

	public ExtendedValueType getValueType(int resultNumber) {
		// TODO Auto-generated method stub
		return null;
	}
}

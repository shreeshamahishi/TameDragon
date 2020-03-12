package org.tamedragon.common.common.analysis;

import org.tamedragon.common.DominatorTree;
import org.tamedragon.common.llvmir.instructions.Instruction;

public class ValueTrackingQuery {

	//private DataLayout DL;
	//private AssumptionCache AC;
	private Instruction CxtI;
	private DominatorTree DT;
	private InstrInfoQuery IIQ;
	
	public ValueTrackingQuery(Instruction cxtI, DominatorTree dT, InstrInfoQuery iIQ) {
		super();
		CxtI = cxtI;
		DT = dT;
		IIQ = iIQ;
	}

	public Instruction getCxtI() {
		return CxtI;
	}

	public DominatorTree getDT() {
		return DT;
	}

	public InstrInfoQuery getIIQ() {
		return IIQ;
	}
}

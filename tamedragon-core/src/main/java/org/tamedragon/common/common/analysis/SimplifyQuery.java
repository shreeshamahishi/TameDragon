package org.tamedragon.common.common.analysis;

import org.tamedragon.common.DominatorTree;
import org.tamedragon.common.llvmir.instructions.Instruction;
import org.tamedragon.common.llvmir.irdata.DataLayout;

public class SimplifyQuery {
	public DataLayout DL;
	//public TargetLibraryInfo TLI;
	public DominatorTree DT;
	// AssumptionCache *AC = nullptr;
	public Instruction CxtI;

	// Wrapper to query additional information for instructions like metadata or
	// keywords like nsw, which provides conservative results if those cannot
	// be safely used.
	InstrInfoQuery IIQ;

	public SimplifyQuery(DataLayout DL, Instruction CXTI, InstrInfoQuery iiq) {
		this.DL = DL;
		this.CxtI = CXTI;
		this.IIQ = iiq;
	}

	public SimplifyQuery getWithInstruction(Instruction I) {
		SimplifyQuery copy = new SimplifyQuery(this.DL, this.CxtI, this.IIQ);
		copy.CxtI = I;
		return copy;
	}

	// TODO Implement this when we have the TargetLibraryInfo and the AssumptionCache ready
	//public SimplifyQuery(DataLayout DL, TargetLibraryInfo TLI, DominatorTree DT
	//              AssumptionCache AC, Instruction *CXTI, boolean UseInstrInfo) {
	//  : DL(DL), TLI(TLI), DT(DT), AC(AC), CxtI(CXTI), IIQ(UseInstrInfo) {}
	//}
}


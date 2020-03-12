package org.tamedragon.common.common.analysis;

import org.tamedragon.common.llvmir.instructions.BinaryOperator;
import org.tamedragon.common.llvmir.instructions.Instruction;

public class InstrInfoQuery {

	private boolean useInstrInfo = true;

	public InstrInfoQuery() {}

	public InstrInfoQuery(boolean UMD){
		this.useInstrInfo = UMD;
	}

	// TODO Implement Metadata Nodes
	//public MDNode getMetadata(Instruction I, int KindID) {
	//  if (UseInstrInfo)
	//    return I.getMetadata(KindID);
	//  return null;
	// }

	public boolean hasNoUnsignedWrap(Instruction Op)  {
		if (useInstrInfo)
			return Op.hasNoUnsignedWrap();
		return false;
	}

	public boolean hasNoSignedWrap(Instruction Op)  {
		if (useInstrInfo)
			return Op.hasNoSignedWrap();
		return false;
	}

	public boolean isExact(BinaryOperator Op) {
		// TODO Implement PossiblyExactOperator
		//if (UseInstrInfo && isa<PossiblyExactOperator>(Op))
		//	return cast<PossiblyExactOperator>(Op).isExact();
		//	return false;

		if (useInstrInfo)
			return Op.hasNoSignedWrap();
		return false;
	}

}
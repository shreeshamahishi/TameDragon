package org.tamedragon.assemblyabstractions.constructs;

/**
 * This class holds information needed to create LLVM's Store Instruction
 * @author ipsg
 *
 */
public class IRTreeMove  extends IRTreeStatement{
	
	private IRTreeExp destExp;
	private IRTreeExp src;
	
	public IRTreeMove(IRTreeExp destExp, IRTreeExp src){
		this.destExp = destExp;
		this.src = src;
		statementType = TreeStatementType.MOVE;
	}
	
	public IRTreeExp getDest() {
		return destExp;
	}
	
	public void setDest(IRTreeExp dest) {
		this.destExp = dest;
	}
	
	public IRTreeExp getSrc() {
		return src;
	}
	
	public void setSrc(IRTreeExp src) {
		this.src = src;
	}

	@Override
	public String getDescription() {
		return "Move";
	}
}

package org.tamedragon.assemblyabstractions.constructs;

/**
 * This class represents the start of the loop body
 * @author ipsg
 *
 */
public class IRTreeLoopBody extends IRTreeStatement{
	
	public IRTreeLoopBody(){
		this.statementType = TreeStatementType.LOOP_BODY_STMT;
	}
	
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

}

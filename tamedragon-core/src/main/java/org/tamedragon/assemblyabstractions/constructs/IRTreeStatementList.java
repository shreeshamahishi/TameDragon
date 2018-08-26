package org.tamedragon.assemblyabstractions.constructs;

/**
 * This class represents a List of Instructions
 * @author ipsg
 *
 */
public class IRTreeStatementList implements IRTree { 

	private IRTreeStatement statement;
	private IRTreeStatementList statementList;
	
	public IRTreeStatementList(IRTreeStatement stm, IRTreeStatementList list){
		this.statement = stm;
		this.statementList = list;
	}

	public IRTreeStatement getStatement(){
		return statement;
	}
	
	public IRTreeStatementList getStatementList(){
		return statementList;
	}
	
	public void setStatementList(IRTreeStatementList list){
		statementList = list;
	}
	

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

}

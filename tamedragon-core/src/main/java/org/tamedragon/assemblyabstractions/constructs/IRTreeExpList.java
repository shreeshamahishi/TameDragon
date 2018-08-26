package org.tamedragon.assemblyabstractions.constructs;

import java.util.List;

/**
 * This class contains a list of IRTree Expressions
 * @author ipsg
 *
 */
public class IRTreeExpList extends IRTreeExp {
	
	private List<IRTreeExp> listOfExprs;
	
	public IRTreeExpList(List<IRTreeExp> listOfExprs){
		this.listOfExprs = listOfExprs;
		this.expType = TreeNodeExpType.EXPR_LIST;
	}

	public List<IRTreeExp> getListOfExprs() {
		return listOfExprs;
	}

	public void setListOfExprs(List<IRTreeExp> listOfExprs) {
		this.listOfExprs = listOfExprs;
	}
}

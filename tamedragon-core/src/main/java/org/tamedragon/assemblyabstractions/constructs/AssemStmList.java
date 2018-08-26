package org.tamedragon.assemblyabstractions.constructs;

public class AssemStmList 
{
	private AssemStm head;
	private AssemStmList tail;
	
	public AssemStmList(AssemStm h, AssemStmList t) {
		head = h;
		tail = t;
	}
	
	public AssemStm getStm()
	{
		return head;
	}
	
	public AssemStmList getStmList()
	{
		return tail;
	}
	
	public void setStmList(AssemStmList ls)
	{
		tail = ls;
	}
	
	public void setHead(AssemStm stm)
	{
		head = stm;
	}
}

package org.tamedragon.assemblyabstractions.constructs;

public class AssemExpList  {
	
	private AssemExp head;
	private AssemExpList tail;
	
	public AssemExpList(AssemExp h, AssemExpList t) 
	{
		head = h;
		tail = t;
	}
	
	public AssemExp getHead()
	{
		return head;
	}
	
	public AssemExpList getTail()
	{
		return tail;
	}

	public void setHead(AssemExp head) {
		this.head = head;
	}
}

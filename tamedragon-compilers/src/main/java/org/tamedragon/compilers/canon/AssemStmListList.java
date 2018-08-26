package org.tamedragon.compilers.canon;

import org.tamedragon.assemblyabstractions.constructs.*;

public class AssemStmListList 
{
	private AssemStmList head;
	private AssemStmListList tail;
	  
	public AssemStmListList(AssemStmList h, AssemStmListList t)
	{
		head = h;
		tail = t;
	}
	
	public AssemStmList getHead()
	{
		return head;
	}
	
	public AssemStmListList getTail()
	{
		return tail;
	}
	
	public void setTail(AssemStmListList ls)
	{
		tail = ls;
	}
}

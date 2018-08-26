package org.tamedragon.compilers.canon;

import org.tamedragon.assemblyabstractions.constructs.*;

public class MoveCall extends AssemStm 
{
	private AssemTemp dst;
	private AssemCallExp src;
	  
	public MoveCall(AssemTemp d, AssemCallExp s) 
	{
		dst = d;
		src =s;
	}
	
	public AssemExpList children() 
	{
		return src.children();
	}
	  
	public AssemStm build(AssemExpList kids) 
	{
		return new AssemMove(dst, src.build(kids));
	}
	
	public String getDescription()
	{
		return "MoveCall";
	}
	
	public AssemStm canonize()
	{
		return null;
	}
	
	public int getStmType()
	{
		return AssemStm.MOVE_CALL;
	}
}
	  
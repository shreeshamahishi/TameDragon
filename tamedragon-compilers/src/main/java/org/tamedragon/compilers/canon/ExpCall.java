package org.tamedragon.compilers.canon;

import org.tamedragon.assemblyabstractions.constructs.*;

public class ExpCall extends AssemStm 
{
	AssemCallExp call;
	
	public ExpCall(AssemCallExp c) 
	{
		call = c;
	}
	
	public AssemExpList children() 
	{
		return call.children();
	}
	
	public AssemStm build(AssemExpList list)
	{
		return new AssemExpStm(call.build(list));
	}
	
	public String getDescription()
	{
		return "ExpCall";
	}
	
	public AssemStm canonize()
	{
		return null;
	}
	
	public int getStmType()
	{
		return AssemStm.CALL;
	}
}   
	  
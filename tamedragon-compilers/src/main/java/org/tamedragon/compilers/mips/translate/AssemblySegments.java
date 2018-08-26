package org.tamedragon.compilers.mips.translate;

import java.util.Vector;

import org.tamedragon.common.EnvironmentConstants;

public class AssemblySegments 
{
	private Vector<String> preTextDirectives;
	private Vector<String> postTextDirectives;
	private Vector<String> dataDirectives;
	
	public AssemblySegments(){
		preTextDirectives = new Vector<String>();
		postTextDirectives = new Vector<String>();
		dataDirectives = new Vector<String>();
	}
	
	public Vector<String> getPreTextDirectives(){
		preTextDirectives.addElement(EnvironmentConstants.TAB + ".text" + EnvironmentConstants.NEWLINE);
		return preTextDirectives;
	}
	
	public Vector<String> getPostTextDirectives(){
		return postTextDirectives;
	}
	
	public Vector<String> getDataDirectives(){
		dataDirectives.addElement(EnvironmentConstants.TAB + ".data" + EnvironmentConstants.NEWLINE);
		return dataDirectives;
	}
}

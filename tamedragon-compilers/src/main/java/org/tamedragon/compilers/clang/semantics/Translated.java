package org.tamedragon.compilers.clang.semantics;

import org.tamedragon.assemblyabstractions.constructs.IRTree;
import org.tamedragon.compilers.clang.SourceLocation;

public class Translated {
	private IRTree irTree;
	private TypeEntryWithAttributes typeEntry;
	private SourceLocation sourceLocation;
	private String sourceFilePath;
	
	private boolean excessInitializersInArrayInit;
	private boolean excessInitializersInStructnit;
	
	private String variableName;   // If this is a declared variable, the name is stored here
	
	public void setIRTree(IRTree IRTree) { this.irTree = IRTree; }
	
	public void setEntry(TypeEntryWithAttributes typeEntry) { this.typeEntry = typeEntry; }
	
	public IRTree getIRTree() { return irTree; }
	
	public TypeEntryWithAttributes getTypeEntry() { return typeEntry; }

	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}

	public void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}

	public String getSourceFilePath() {
		return sourceFilePath;
	}

	public void setSourceFilePath(String sourceFilePath) {
		this.sourceFilePath = sourceFilePath;
	}

	public void setTypeEntry(TypeEntryWithAttributes typeEntry) {
		this.typeEntry = typeEntry;
	}

	public boolean isExcessInitializersInArrayInit() {
		return excessInitializersInArrayInit;
	}

	public void setExcessInitializersInArrayInit(
			boolean excessInitializersInArrayInit) {
		this.excessInitializersInArrayInit = excessInitializersInArrayInit;
	}

	public boolean isExcessInitializersInStructnit() {
		return excessInitializersInStructnit;
	}

	public void setExcessInitializersInStructnit(
			boolean excessInitializersInStructnit) {
		this.excessInitializersInStructnit = excessInitializersInStructnit;
	}

	public String getVariableName() {
		return variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}
}

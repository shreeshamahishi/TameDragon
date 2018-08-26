package org.tamedragon.compilers.clang.semantics;

import org.tamedragon.assemblyabstractions.constructs.AssemType;
import org.tamedragon.compilers.clang.SourceLocation;

public class TranslatedExpAndType {
	private AssemType assemType;
	private TypeEntryWithAttributes typeEntry;
	private SourceLocation sourceLocation;
	private String sourceFilePath;
	
	private boolean excessInitializersInArrayInit;
	private boolean excessInitializersInStructnit;
	
	private String variableName;   // If this is a declared variable, the name is stored here
	
	public void setAssemType(AssemType assemType) { this.assemType = assemType; }
	
	public void setEntry(TypeEntryWithAttributes typeEntry) { this.typeEntry = typeEntry; }
	
	public AssemType getAssemType() { return assemType; }
	
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

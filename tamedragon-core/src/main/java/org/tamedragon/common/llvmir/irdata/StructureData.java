package org.tamedragon.common.llvmir.irdata;

/**
 * The StructureData class denotes a LLVM structure.
 **/

public class StructureData extends ValueData {

	private String structElements;

	public void setStructElements(String structElements) {
		this.structElements = structElements;
	}

	public String getStructElements() {
		return structElements;
	}
}
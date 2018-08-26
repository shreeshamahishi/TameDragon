package org.tamedragon.common.machineabstractions;

import java.util.List;
import java.util.Set;

import org.tamedragon.common.llvmir.instructions.Instruction;

public interface MachineArchitecture {
	
	public String getName();
	public List<Resource> getResources();
	public int getMaxCycles();
	
	// Returns the set of resources used in the cycle for a given instruction
	public Set<Resource> getResourcesUsed(Instruction ins, int cycle);

}

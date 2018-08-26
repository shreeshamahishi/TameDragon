package org.tamedragon.common.dependenceanalysis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.tamedragon.common.llvmir.instructions.Instruction;
import org.tamedragon.common.llvmir.instructions.Instruction.InstructionID;
import org.tamedragon.common.machineabstractions.MachineArchitecture;
import org.tamedragon.common.machineabstractions.Resource;

class MockMIPS4000 implements MachineArchitecture{

	private static final String NAME = "MIPS4000";
	
	private static final List<String> RESOURCES_NAMES = new ArrayList<String>();
	private static final List<Resource> RESOURCES;
	static{
		// The pipeline
		RESOURCES_NAMES.add("IF");RESOURCES_NAMES.add("IS"); RESOURCES_NAMES.add("RF");
		RESOURCES_NAMES.add("EX"); RESOURCES_NAMES.add("DF"); RESOURCES_NAMES.add("DS");
		RESOURCES_NAMES.add("TC"); RESOURCES_NAMES.add("WB");
		
		// Pipeline for floating point ops
		RESOURCES_NAMES.add("UNPACK"); RESOURCES_NAMES.add("SHIFT"); RESOURCES_NAMES.add("ADD"); 
		RESOURCES_NAMES.add("MULA"); RESOURCES_NAMES.add("MULB");
		RESOURCES_NAMES.add("ROUND"); 
		
		
		RESOURCES = new ArrayList<Resource>();
		for(String resourceName : RESOURCES_NAMES){
			RESOURCES.add(new Resource("MIPS4000" , resourceName));
		}
	}
	
	private static final int MAX_CYCLES = 7;
	
	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public List<Resource> getResources() {
		return RESOURCES;
	}

	@Override
	public int getMaxCycles() {
		return MAX_CYCLES;
	}

	@Override
	public Set<Resource> getResourcesUsed(Instruction ins, int cycle) {
		/*InstructionID instID = ins.getInstructionID();
		
		
		
		if(instID == InstructionID.ALLOCA_INST){
			
		}*/
		return null;
	}
	
}

public class DataDependenceAnalysisTest {
	
}

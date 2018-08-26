package org.tamedragon.common.machineabstractions;

/**
 * Represents a resource or a so-called "functional unit" in a computer
 * architecture. For example, the MIPS R4000 floating point unit has
 * seven resources (mantissa ADD), E (Exception test), M (multiplier
 * first stage and so on.
 * 
 * For now, we represent them as a simple POJO with a String to describe
 * the resource.
 * 
 */
public class Resource {

	public Resource(String targetName, String resourceDescription){
		this.targetName = targetName;
		this.resourceDescription = resourceDescription;
	}
	
	private String targetName;
	private String resourceDescription;

	public String getResourceDescription() {
		return resourceDescription;
	}
	
	public String getTargetName() {
		return targetName;
	}
	
	@Override
	public boolean equals(Object other){
		if(!(other instanceof Resource))
			return false;
		
		Resource otherResource = (Resource) other;
		
		if(targetName.equals(otherResource.getTargetName())){
			return true;
		}
		
		if(resourceDescription.equals(otherResource.getResourceDescription())){
			return true;
		}
		
		return false;
	}
}

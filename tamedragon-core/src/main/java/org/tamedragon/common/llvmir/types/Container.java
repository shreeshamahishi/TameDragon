package org.tamedragon.common.llvmir.types;

/**
 * This interface denotes entities that contain or represent objects. The contained objects can be constants,
 * addresses, variables,etc.
 * 
 * For example, Value can be a Container (it can hold objects that are constants, variables, addresses, etc.)
 *
 */
public interface Container {
	public enum ContainerTypeID {
		VALUE,                  // This is an instance of Value
		STORAGE_LOCATION,       // This is an instance of an address (it can contain objects or other storage locations)
	} 
	
	public ContainerTypeID getContainerType();
	
}

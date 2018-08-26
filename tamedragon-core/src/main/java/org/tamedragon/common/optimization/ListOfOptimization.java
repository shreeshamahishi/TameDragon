package org.tamedragon.common.optimization;

public class ListOfOptimization {

	public enum OptimizationTypeID{
		MemToReg,
		ADCE,		
		LICM,
		SCCP,
		GlobalCSE,
		LocalCSE,
		GVN,
		BranchOptimization,
		TailRecursiveCallElimination,
		TailMerging,
		LoopStrengthReduce,
		LoopUnSwitching,
		SSADeadCodeEliminator,

	}
	protected OptimizationTypeID optimizationTypeID;
	/**
	 * @return the optimizationTypeID
	 */
	public OptimizationTypeID getOptimizationTypeID() {
		return optimizationTypeID;
	}
	/**
	 * @param optimizationTypeID the optimizationTypeID to set
	 */
	public void setOptimizationTypeID(OptimizationTypeID optimizationTypeID) {
		this.optimizationTypeID = optimizationTypeID;
	}
	
}

package org.tamedragon.compilers.clang.semantics;

import org.tamedragon.common.TargetMachine;
import org.tamedragon.compilers.mips.translate.MipsProperties;

public class TargetMachineFactory {
	
	private String target;

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}
	
	public TargetMachine getTargetMachine(String target){
		
		this.target = target;
		
		TargetMachine targetMachine = null;
		
		if(target.equals("mips") || target.equals("MIPS")){
			targetMachine = new MipsProperties();
 		}
		
		if(target.equalsIgnoreCase("arm")){
			// TODO Implement this
			// targetMachine = new ArmProperties();
		}
		
		return targetMachine;
	}
}

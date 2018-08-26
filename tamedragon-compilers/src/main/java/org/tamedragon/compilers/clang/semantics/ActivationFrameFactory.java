package org.tamedragon.compilers.clang.semantics;

import org.tamedragon.assemblyabstractions.ActivationFrame;
import org.tamedragon.compilers.mips.translate.MipsActivationFrameImpl;

public class ActivationFrameFactory {
	
	private String target;

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}
	
	public ActivationFrame getActivationFrame(String target){
		
		this.target = target;
		
		ActivationFrame activationFrame = null;
		
		if(target.equalsIgnoreCase("mips")){
			activationFrame = new MipsActivationFrameImpl();
		}
		if(target.equalsIgnoreCase("arm")){
			// TODO Implement this
			//activationFrame = new ArmActivationFrameImpl();
		}
		
		return activationFrame;
	}
	
	
}

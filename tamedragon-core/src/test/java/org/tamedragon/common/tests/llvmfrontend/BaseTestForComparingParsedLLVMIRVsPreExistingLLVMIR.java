package org.tamedragon.common.tests.llvmfrontend;

import org.junit.Before;
import org.tamedragon.common.llvmir.utils.LLVMIREmitter;


public class BaseTestForComparingParsedLLVMIRVsPreExistingLLVMIR {
	LLVMIREmitter emitter = null;

	@Before
	public void setUp(){
		emitter = LLVMIREmitter.getInstance();
		emitter.reset();
	 
	}
}

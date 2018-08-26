package org.tamedragon.common.tests.llvmfrontend;

import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;
import org.tamedragon.common.llvmir.semanticanalysis.LLVMErrorHandler;
import org.tamedragon.common.utils.LLVMIRUtils;

@Ignore
public class SemanticErrorsTest  {

	private static final String ROOT_PATH = "SemanticErrors";

	@Test
	public void runUndefinedValue() throws Exception {
		String srcFileName =  "UndefinedValue.bc";
		LLVMIRUtils llvmirUtils = new LLVMIRUtils();
		llvmirUtils.getInstructionsList(ROOT_PATH, srcFileName);
		
		LLVMErrorHandler errorHandler = LLVMErrorHandler.getInstance();
		
		int numErrors = errorHandler.getNumErrors();
		assertTrue(numErrors == 1);
		
		errorHandler.displayResult();
		
	}	
	
	@Test
	public void runMismatchedVarOrder() throws Exception {
		String srcFileName =  "MismatchedVarOrder.bc";
		LLVMIRUtils llvmirUtils = new LLVMIRUtils();
		llvmirUtils.getInstructionsList(ROOT_PATH, srcFileName);
		
		LLVMErrorHandler errorHandler = LLVMErrorHandler.getInstance();
		
		int numErrors = errorHandler.getNumErrors();
		assertTrue(numErrors == 1);
		
		errorHandler.displayResult();
		
	}	
}

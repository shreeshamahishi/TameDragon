package org.tamedragon.common.controlflowanalysis;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.tamedragon.common.llvmir.instructions.LLVMUtility;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.Module;
import org.tamedragon.common.optimization.MemToRegPromoter;
import org.tamedragon.compilers.LLVMBaseTest;
import org.tamedragon.compilers.clang.CLangUtils;
import org.tamedragon.compilers.clang.CompilerSettings;

public class LoopIdiomRecogizeTests extends LLVMBaseTest{

	private CompilerSettings compilerSettings;
	private String projectPath = "CSrc/ControlFlowAnalysis/PointerIncrementingTests/";
	private String projectRootPath;
	
	@Before
	public void setUp(){		
		super.setUp();
		properties = LLVMUtility.getDefaultProperties();
		
		CLangUtils.populateSettings();
		compilerSettings = CompilerSettings.getInstance();
		compilerSettings.setProjectPath(projectPath);

		projectRootPath = compilerSettings.getProjectRoot();
	}
	
	@Test
	public void simplePointerIncrement() throws Exception {
		String cSrcfilename =  "SimplePointerIncrement.c";
		
		Function function = getFunction(cSrcfilename);

		LoopIdiomRecognize loopIdiomRecognize = new LoopIdiomRecognize(function);
		loopIdiomRecognize.identifyingIncrementingPointers();

	}
	
	private Function getFunction(String cSrcfilename)  throws Exception{
		getRawLLVRIRInstrs(projectRootPath + projectPath, cSrcfilename);

		Module module = getModule();
		List<Function> functions = module.getFunctions();

		Function function = functions.get(functions.size() -1);
		
		// Mem2reg
		MemToRegPromoter memToRegPromoter = new MemToRegPromoter();
		memToRegPromoter.execute(function);
		System.out.println("AFTER MEM2REG: ");
		emitter.reset();
		List<String> instrsAfterOpt = emitter.emitInstructions(module);
		printAsm(instrsAfterOpt);
		
		return function;
	}
	
}

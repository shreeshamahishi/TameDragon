package org.tamedragon.compilers.clang.tests.llvmIrtranslate;

import java.util.List;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.tamedragon.common.llvmir.instructions.LLVMUtility;
import org.tamedragon.common.utils.LLVMIRComparisionUtils;
import org.tamedragon.compilers.LLVMBaseTest;
import org.tamedragon.compilers.clang.CLangUtils;
import org.tamedragon.compilers.clang.CompilerSettings;

public class IRTreeGenTestBinOps extends LLVMBaseTest {

	private CompilerSettings compilerSettings;
	private String projectPath = "CSrc/TranslateToLLVMIR/BinaryOperators/";
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
	public void testSimpleAddition() {
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectRootPath + projectPath, "SimpleBinOp.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "SimpleBinOpLLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testAdditionWithGlobalVariable() {
		try {
			List<String> listOfDynamicInstrsCreated = getRawLLVRIRInstrs(projectRootPath + projectPath, "SimpleBinOpWithGlobalVar.c");
			assertTrue(LLVMIRComparisionUtils.compare(listOfDynamicInstrsCreated, projectPath, "SimpleBinOpWithGlobalVarLLVMIR.ll"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
}

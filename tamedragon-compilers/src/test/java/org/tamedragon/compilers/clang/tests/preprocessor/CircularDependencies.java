package org.tamedragon.compilers.clang.tests.preprocessor;

import static org.junit.Assert.assertTrue;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.tamedragon.common.llvmir.instructions.LLVMUtility;
import org.tamedragon.common.llvmir.types.CompilationContext;
import org.tamedragon.compilers.clang.CLangUtils;
import org.tamedragon.compilers.clang.CompilerSettings;
import org.tamedragon.compilers.clang.ErrorHandler;
import org.tamedragon.compilers.clang.exceptions.DAGCreationException;
import org.tamedragon.compilers.clang.preprocessor.DefinitionsMap;
import org.tamedragon.compilers.clang.preprocessor.IncludesPreProcessed;
import org.tamedragon.compilers.clang.preprocessor.PreprocessorMain;
import org.tamedragon.compilers.clang.semantics.Environments;

public class CircularDependencies {

	private String sourceFilePath;	
	private CompilerSettings compilerSettings;
	private ErrorHandler errorHandler ;
	DefinitionsMap defsMap;
	Environments environments;

	private Properties properties;

	private String projectPath = "CSrc/Preprocessor/";

	@Before
	public void setUp(){		
		properties = LLVMUtility.getDefaultProperties();

		CLangUtils.populateSettings();
		compilerSettings = CompilerSettings.getInstance();
		String projectRootPath = compilerSettings.getProjectRoot();
		compilerSettings.setProjectPath(projectPath);

		CompilationContext compilationContext = new CompilationContext();
		compilerSettings.setCompilationContext(compilationContext);

		sourceFilePath = projectRootPath + projectPath + "IncludeWithCircularDependency.c"; 

		// Start with a clean slate
		errorHandler = ErrorHandler.getInstance();
		errorHandler.reset();
		defsMap = DefinitionsMap.getInstance();
		defsMap.clearDefinitions();
		environments = Environments.getInstance();
		environments.reset();		
		HashMap<String, HashMap<String, List<InputStream>>> includesPreProcessed = IncludesPreProcessed.getInstance();
		includesPreProcessed.clear();
	}

	@Test
	public void testWithCircularDependency() {   		

		try {
			PreprocessorMain ppMain = new PreprocessorMain(sourceFilePath);			
			InputStream sourceFileInputStream = ppMain.process(true); 
			assertTrue(false);
		}
		catch(DAGCreationException e) {  // Must throw DAG creation exception
			String projectRootPath = compilerSettings.getProjectRoot();
			String includeFile7 = projectRootPath + projectPath + "h7.h";
			String includeFile5 = projectRootPath + projectPath + "h5.h";
			
			assertTrue(includeFile7.equals(e.getSourceFilePath()));
			assertTrue(includeFile5.equals(e.getIncludeFilePath()));
			
			assertTrue(true);
		} catch (Exception e) {  // But no other exception
			assertTrue(false);
		}
	} 
}

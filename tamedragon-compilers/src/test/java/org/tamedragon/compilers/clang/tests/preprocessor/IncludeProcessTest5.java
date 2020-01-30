package org.tamedragon.compilers.clang.tests.preprocessor;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.tamedragon.common.llvmir.instructions.LLVMUtility;
import org.tamedragon.common.llvmir.types.CompilationContext;
import org.tamedragon.compilers.clang.CLangUtils;
import org.tamedragon.compilers.clang.CompilerSettings;
import org.tamedragon.compilers.clang.ErrorHandler;
import org.tamedragon.compilers.clang.ErrorIterator;
import org.tamedragon.compilers.clang.SourceLocation;
import org.tamedragon.compilers.clang.SourceLocationAndMsg;
import org.tamedragon.compilers.clang.abssyntree.TranslationUnit;
import org.tamedragon.compilers.clang.preprocessor.DefinitionsMap;
import org.tamedragon.compilers.clang.preprocessor.IncludesPreProcessed;
import org.tamedragon.compilers.clang.preprocessor.PreprocessorMain;
import org.tamedragon.compilers.clang.preprocessor.PreprocessorSegments;
import org.tamedragon.compilers.clang.preprocessor.PreprocessorUnit;
import org.tamedragon.compilers.clang.semantics.Environments;
import org.tamedragon.compilers.clang.semantics.Semantic;
import org.tamedragon.compilers.clang.tests.TestInitializer;

public class IncludeProcessTest5 extends TestInitializer {

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
		
		sourceFilePath = projectRootPath + projectPath + "IncludeProcessTest5.c"; 
	
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
	public void test1() {   
		
		PreprocessorMain ppMain = new PreprocessorMain(sourceFilePath);
		InputStream is = ppMain.replaceTrigraphSequencesAndSpliceLines(sourceFilePath);
		
		PreprocessorSegments preprocessorSegments = ppMain.getPreprocessorTranslationByLLParsing(is);
		assertNotNull(preprocessorSegments);	
		
		List<PreprocessorUnit> units = preprocessorSegments.getUnits();
		assertNotNull(units);
		assertTrue(units.size() == 7);
		
		StringBuffer sb = preprocessorSegments.process(sourceFilePath, true);
				
		System.out.println("Translated program = " + sb.toString());		
	} 	
	
	@Test
	public void test2() {   		

		// Run the preprocessor on the sourceFile			
		PreprocessorMain ppMain = new PreprocessorMain(sourceFilePath);			
		ppMain.process(true); 
		
		// Check the definitions map
		Set<String> keys = defsMap.getKeys();
		assertTrue(keys.size() == 8);
		assertTrue( defsMap.getDefinition("VAR1").equals("45"));
		assertTrue( defsMap.getDefinition("VAR2").equals("14"));
		assertTrue( defsMap.getDefinition("VAR3").equals("15"));
		assertTrue( defsMap.getDefinition("VAR4").equals("45"));
		assertTrue( defsMap.getDefinition("VAR5").equals("34"));
		assertTrue( defsMap.getDefinition("MAX").equals("30"));
		assertTrue( defsMap.getDefinition("MIN").equals("2"));
		assertTrue( defsMap.getDefinition("_H4_").equals(""));
		
		// Check the inputstreams for the included files
		String projectRootPath = compilerSettings.getProjectRoot();
		String includeFile1 = projectRootPath + projectPath + "h3.h";
		String includeFile2 = projectRootPath + projectPath + "h4.h";
		
		HashMap<String, HashMap<String, List<InputStream>>> includesPreProcessed = IncludesPreProcessed.getInstance();
		keys = includesPreProcessed.keySet();
		assertTrue(keys.size() == 2);
		
		HashMap<String, List<InputStream>> includesMapInFile1 = includesPreProcessed.get(sourceFilePath);
		assertTrue(includesMapInFile1 != null);
		assertTrue(includesMapInFile1.size() == 2);
		String includeStr1 = "# \"h3.h\" #";
		String includeStr2 = "# \"h4.h\" #";
		
		verifyPreprocessedInclude(includesMapInFile1, includeStr1, includeFile1);
		verifyPreprocessedInclude(includesMapInFile1, includeStr2, includeFile2);
				
		HashMap<String, List<InputStream>> includesMapInFile2 = includesPreProcessed.get(includeFile1);
		assertTrue(includesMapInFile2 != null);
		assertTrue(includesMapInFile2.size() == 1);
		String includeStr3 = "# \"h4.h\" #";
		
		verifyPreprocessedInclude(includesMapInFile2, includeStr3, includeFile2);		
	} 
	
	@Test
	public void test3() {   		
				
		PreprocessorMain ppMain = new PreprocessorMain(sourceFilePath);			
		InputStream sourceFileInputStream = ppMain.process(true); 
		
		String projectRootPath = compilerSettings.getProjectRoot();

		String includeFile1 = projectRootPath + projectPath + "h3.h";
		
		// Translate to abstract syntax tree
		TranslationUnit translationUnit = CLangUtils.getTranslationByLLParsing(sourceFileInputStream);

		// Pass through semantic analyzer and translate to assembly tree
		CompilationContext compilationContext = CompilerSettings.getInstance().getInstanceCompilationContext();
		Semantic semanticAnalyzer = new Semantic(properties, sourceFilePath, compilationContext);  	    
		semanticAnalyzer.translateAbstractTree(translationUnit);    	  
		errorHandler.displayResult();
		
		// Check the errors in h3.h
		int count = 1;	
		
		ErrorIterator iter = new ErrorIterator(includeFile1);
		try{
			while(iter.hasNext()){
				SourceLocationAndMsg srcLcAndMsg =  iter.next();
				SourceLocation srcLc = srcLcAndMsg.getSrcLocation();
				String msg = srcLcAndMsg.getMsg();
				
				int line = srcLc.getLineNum();
				
				if(count == 1){
					assertTrue(line == 11); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "dup" + ErrorHandler.E_VARIABLE_ALREADY_INITIALIZED));		
				}
				count++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		assertTrue(count == 2);		

		
	} 
		
	public void verifyPreprocessedInclude(HashMap<String, List<InputStream>> includesMapInFile,
							String includeStr, String includee){
		List<InputStream> inclsFile1Incl1 =  includesMapInFile.get(includeStr);
		assertNotNull(inclsFile1Incl1);
		assertTrue(inclsFile1Incl1.size() == 1);
		InputStream is1 = inclsFile1Incl1.get(0);
		assertTrue(is1 != null);
		StringBuffer sb = CLangUtils.getStringBufferFromInputStream(is1);
		assertTrue(!sb.toString().contains("#include"));
		assertTrue(!sb.toString().contains("#define"));
		int numLinesInCode = getNumLinesInFile(includee);
		int numLinesInProcessedCode = getNumLinesInString(sb.toString());		
		
		assertTrue(numLinesInCode == numLinesInProcessedCode);

	}	
}

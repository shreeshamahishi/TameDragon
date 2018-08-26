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

public class IncludeProcessTest1 extends TestInitializer {

	private String sourceFilePath;	
	private CompilerSettings compilerSettings;
	private ErrorHandler errorHandler ;
	DefinitionsMap defsMap;
	Environments environments;
	
	private Properties properties;
		
	@Before
	public void setUp(){		
		properties = LLVMUtility.getDefaultProperties();
		CLangUtils.populateSettings();
		compilerSettings = CompilerSettings.getInstance();
		compilerSettings.setInstanceIncludePath("resources/include");
		compilerSettings.setInstanceProjectPath("CSrc/Preprocessor");
		CompilationContext compilationContext = new CompilationContext();
		compilerSettings.setCompilationContext(compilationContext);
		
		sourceFilePath ="CSrc/Preprocessor/IncludeProcessTest1.c"; 
		
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
		assertTrue(keys.size() == 7);
		assertTrue( defsMap.getDefinition("VAR1").equals("45"));
		assertTrue( defsMap.getDefinition("VAR2").equals("14"));
		assertTrue( defsMap.getDefinition("VAR3").equals("15"));
		assertTrue( defsMap.getDefinition("VAR4").equals("45"));
		assertTrue( defsMap.getDefinition("VAR5").equals("34"));
		assertTrue( defsMap.getDefinition("MAX").equals("30"));
		assertTrue( defsMap.getDefinition("MIN").equals("2"));
		
		// Check the inputstreams for the included files
		String includeFile1 = compilerSettings.getInstanceProjectPath() + File.separator + "h1.h";
		String includeFile2 = compilerSettings.getInstanceProjectPath() + File.separator + "h2.h";
		
		HashMap<String, HashMap<String, List<InputStream>>> includesPreProcessed = IncludesPreProcessed.getInstance();
		keys = includesPreProcessed.keySet();
		assertTrue(keys.size() == 2);
		
		HashMap<String, List<InputStream>> includesMapInFile1 = includesPreProcessed.get(sourceFilePath);
		assertTrue(includesMapInFile1 != null);
		assertTrue(includesMapInFile1.size() == 2);
		String includeStr1 = "# \"h1.h\" #";
		String includeStr2 = "# \"h2.h\" #";
		
		verifyPreprocessedInclude(includesMapInFile1, includeStr1, includeFile1);
		verifyPreprocessedInclude(includesMapInFile1, includeStr2, includeFile2);
				
		HashMap<String, List<InputStream>> includesMapInFile2 = includesPreProcessed.get(includeFile1);
		assertTrue(includesMapInFile2 != null);
		assertTrue(includesMapInFile2.size() == 1);
		String includeStr3 = "# \"h2.h\" #";
		
		verifyPreprocessedInclude(includesMapInFile2, includeStr3, includeFile2);		
	} 
	
	@Test
	public void test3() {   		
		String targetDesc = compilerSettings.getInstanceTarget();
				
		PreprocessorMain ppMain = new PreprocessorMain(sourceFilePath);			
		InputStream sourceFileInputStream = ppMain.process(true); 
		
		String includeFile1 = compilerSettings.getInstanceProjectPath() + File.separator + "h1.h";
		String includeFile2 = compilerSettings.getInstanceProjectPath() + File.separator + "h2.h";		
		
		// Translate to abstract syntax tree
		TranslationUnit translationUnit = CLangUtils.getTranslationByLLParsing(sourceFileInputStream);

		// Pass through semantic analyzer and translate to assembly tree
		CompilationContext compilationContext = CompilerSettings.getInstance().getInstanceCompilationContext();
		Semantic semanticAnalyzer = new Semantic(properties, sourceFilePath, compilationContext);
//		SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer(sourceFilePath, targetDesc); 	    
		semanticAnalyzer.translateAbstractTree(translationUnit);    	  
		errorHandler.displayResult();
		
		// Check the errors in h1.h
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

		// Check the errors in h2.h
		count = 1;	
		
		iter = new ErrorIterator(includeFile2);
		try{
			while(iter.hasNext()){
				SourceLocationAndMsg srcLcAndMsg =  iter.next();
				SourceLocation srcLc = srcLcAndMsg.getSrcLocation();
				String msg = srcLcAndMsg.getMsg();
				
				int line = srcLc.getLineNum();
				
				if(count == 1){
					assertTrue(line == 4); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "h2def1" + ErrorHandler.E_VARIABLE_ALREADY_INITIALIZED));
				}
				if(count == 2){
					assertTrue(line == 5); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "h2def2" + ErrorHandler.E_VARIABLE_ALREADY_INITIALIZED));
				}
				if(count == 3){
					assertTrue(line == 6); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "dup" + ErrorHandler.E_VARIABLE_ALREADY_INITIALIZED));
				}
				count++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		assertTrue(count == 4);		
		
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

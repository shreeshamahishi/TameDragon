package org.tamedragon.compilers.clang.tests.preprocessor;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

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
import org.tamedragon.compilers.clang.preprocessor.PreprocessorMain;
import org.tamedragon.compilers.clang.preprocessor.PreprocessorSegments;
import org.tamedragon.compilers.clang.preprocessor.PreprocessorUnit;
import org.tamedragon.compilers.clang.semantics.Environments;
import org.tamedragon.compilers.clang.semantics.Semantic;
import org.tamedragon.compilers.clang.tests.TestInitializer;

public class IncludeProcessTest3 extends TestInitializer {

	private String sourceFilePath;
	private PreprocessorSegments preprocessorSegments;
	private Properties properties;
	
	private CompilerSettings compilerSettings;
	private String projectRootPath;
	private ErrorHandler errorHandler ;
	DefinitionsMap defsMap;
	Environments environments;

	private String projectPath = "CSrc/Preprocessor/";
		
	@Before
	public void setUp(){		
		properties = LLVMUtility.getDefaultProperties();
		
		CLangUtils.populateSettings();
		compilerSettings = CompilerSettings.getInstance();
		projectRootPath = compilerSettings.getProjectRoot();
		compilerSettings.setProjectPath(projectPath);
		
		CompilationContext compilationContext = new CompilationContext();
		compilerSettings.setCompilationContext(compilationContext);
		
		sourceFilePath = projectRootPath + projectPath + "IncludeProcessTest3.c"; 
	
		// Start with a clean slate
		errorHandler = ErrorHandler.getInstance();
		errorHandler.reset();
		defsMap = DefinitionsMap.getInstance();
		defsMap.clearDefinitions();
		environments = Environments.getInstance();
		environments.reset();		
		//HashMap<String, HashMap<String, List<InputStream>>> includesPreProcessed = IncludesPreProcessed.getInstance();
		//includesPreProcessed.clear();
		
		PreprocessorMain ppMain = new PreprocessorMain(sourceFilePath);
		InputStream is = ppMain.replaceTrigraphSequencesAndSpliceLines(sourceFilePath);		

		preprocessorSegments = ppMain.getPreprocessorTranslationByLLParsing(is);
		assertNotNull(preprocessorSegments);			
		
	}

	@Test
	public void test1() {     

		Environments environments = Environments.getInstance();
		environments.reset();	
		
		CompilerSettings compilerSettings = CompilerSettings.getInstance();
		String targetDesc = compilerSettings.getInstanceTarget();

		ErrorHandler errorHandler = ErrorHandler.getInstance();
		errorHandler.reset();

		// Clear definitions since we are not calling process() of PreprocessorMain
		DefinitionsMap defsMap = DefinitionsMap.getInstance();
		defsMap.clearDefinitions();
		
		List<PreprocessorUnit> units = preprocessorSegments.getUnits();
		assertNotNull(units);
		assertTrue(units.size() == 4);

		StringBuffer sb = preprocessorSegments.process(sourceFilePath, true);
		String codeText = sb.toString();

		int numLinesInCode = getNumLinesInFile(sourceFilePath);
		int numLinesInProcessedCode = getNumLinesInString(codeText);

		System.out.println(codeText);

		assertTrue(numLinesInCode == numLinesInProcessedCode);

		// Run the parser and the semantic analyzer
		PreprocessorMain ppMain = new PreprocessorMain(sourceFilePath);
		InputStream sourceFileInputStream = ppMain.process(true); 

		// Translate to abstract syntax tree
		TranslationUnit translationUnit = CLangUtils.getTranslationByLLParsing(sourceFileInputStream);

		// Pass through semantic analyzer and translate to assembly tree
		CompilationContext compilationContext = CompilerSettings.getInstance().getInstanceCompilationContext();
		Semantic semanticAnalyzer = new Semantic(properties, sourceFilePath, compilationContext); 	    
		semanticAnalyzer.translateAbstractTree(translationUnit);    	  
		errorHandler.displayResult();
		assertTrue(errorHandler.getNumErrors() == 3);
		
		// Check the errors in IdDir3Header2.h
		//String includeFile2 = compilerSettings.getInstanceProjectPath() + "/" + "IdDir3Header2.h";		
		String includeFile2 = projectRootPath + projectPath + "IdDir3Header2.h";
		
		int count = 1;	
		
		ErrorIterator iter = new ErrorIterator(includeFile2);
		try{
			while(iter.hasNext()){
				SourceLocationAndMsg srcLcAndMsg =  iter.next();
				SourceLocation srcLc = srcLcAndMsg.getSrcLocation();
				String msg = srcLcAndMsg.getMsg();
				
				int line = srcLc.getLineNum();
				
				if(count == 1){
					assertTrue(line == 4); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "m" + ErrorHandler.E_VARIABLE_ALREADY_INITIALIZED));		
				}
				if(count == 2){
					assertTrue(line == 5); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "k" + ErrorHandler.E_VARIABLE_ALREADY_INITIALIZED));		
				}
				count++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		assertTrue(count == 3);		
		
	}
}

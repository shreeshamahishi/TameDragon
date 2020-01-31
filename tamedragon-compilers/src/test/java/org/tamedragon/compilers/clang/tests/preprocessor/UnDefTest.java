package org.tamedragon.compilers.clang.tests.preprocessor;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
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
import org.tamedragon.compilers.clang.preprocessor.PreprocessorMain;
import org.tamedragon.compilers.clang.preprocessor.PreprocessorSegments;
import org.tamedragon.compilers.clang.preprocessor.PreprocessorUnit;
import org.tamedragon.compilers.clang.semantics.Environments;
import org.tamedragon.compilers.clang.semantics.Semantic;
import org.tamedragon.compilers.clang.tests.TestInitializer;

public class UnDefTest extends TestInitializer {

	private String sourceFilePath;
	private PreprocessorSegments preprocessorSegments;
	private Properties properties;
	private PreprocessorMain ppMain;

	@Before
	public void setUp(){		
		properties = LLVMUtility.getDefaultProperties();
		CLangUtils.populateSettings();
		CompilerSettings compilerSettings = CompilerSettings.getInstance();
		compilerSettings.setInstanceProjectPath("CSrc/Preprocessor");
		CompilationContext compilationContext = new CompilationContext();
		compilerSettings.setCompilationContext(compilationContext);

		sourceFilePath ="CSrc/Preprocessor/UnDefTest.c"; 
		
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(sourceFilePath).getFile());
		String absolutePath = file.getAbsolutePath();
		
		ppMain = new PreprocessorMain(absolutePath);
		InputStream is = ppMain.replaceTrigraphSequencesAndSpliceLines(absolutePath);		

		preprocessorSegments = ppMain.getPreprocessorTranslationByLLParsing(is);
		assertNotNull(preprocessorSegments);				
	}

	@Test
	public void test1() throws Exception {     

		Environments environments = Environments.getInstance();
		environments.reset();
		
		CompilerSettings compilerSettings = CompilerSettings.getInstance();
		String targetDesc = compilerSettings.getInstanceTarget();

		ErrorHandler errorHandler = ErrorHandler.getInstance();
		errorHandler.reset();

		List<PreprocessorUnit> units = preprocessorSegments.getUnits();
		assertNotNull(units);
		assertTrue(units.size() == 3);

		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(sourceFilePath).getFile());
		String absolutePath = file.getAbsolutePath();
		
		StringBuffer sb = preprocessorSegments.process(absolutePath, ppMain.getDependenciesDag(), true);
		String codeText = sb.toString();

		int numLinesInCode = getNumLinesInFile(absolutePath);
		int numLinesInProcessedCode = getNumLinesInString(codeText);

		System.out.println(codeText);

		assertTrue(numLinesInCode == numLinesInProcessedCode);

		// Run the parser and the semantic analyzer
		PreprocessorMain ppMain = new PreprocessorMain(absolutePath);
		InputStream sourceFileInputStream = ppMain.process(true); 

		// Translate to abstract syntax tree
		TranslationUnit translationUnit = CLangUtils.getTranslationByLLParsing(sourceFileInputStream);

		// Pass through semantic analyzer and translate to assembly tree
		CompilationContext compilationContext = CompilerSettings.getInstance().getInstanceCompilationContext();
		Semantic semanticAnalyzer = new Semantic(properties, absolutePath, compilationContext);	    
		semanticAnalyzer.translateAbstractTree(translationUnit);    	  
		errorHandler.displayResult();
		assertTrue(errorHandler.getNumErrors() == 2);

		int count = 1;	

		ErrorIterator iter = new ErrorIterator(absolutePath);
		try{
			while(iter.hasNext()){
				SourceLocationAndMsg srcLcAndMsg =  iter.next();
				SourceLocation srcLc = srcLcAndMsg.getSrcLocation();
				String msg = srcLcAndMsg.getMsg();

				int line = srcLc.getLineNum();

				if(count == 1){
					assertTrue(line == 5); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "SOMEMACRO" + ErrorHandler.E_NOT_DEFINED));
				}
				if(count == 2){
					assertTrue(line == 5); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "arr: " + ErrorHandler.E_ARRAY_SIZE_NOT_SPECIFIED.substring(ErrorHandler.ERROR.length(), ErrorHandler.E_ARRAY_SIZE_NOT_SPECIFIED.length())));
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

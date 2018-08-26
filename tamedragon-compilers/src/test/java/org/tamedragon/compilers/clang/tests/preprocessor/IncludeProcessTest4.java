package org.tamedragon.compilers.clang.tests.preprocessor;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.InputStream;
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
import org.tamedragon.compilers.clang.semantics.Environments;
import org.tamedragon.compilers.clang.semantics.Semantic;
import org.tamedragon.compilers.clang.tests.TestInitializer;

public class IncludeProcessTest4 extends TestInitializer {

	private String sourceFilePath;
	private Environments environments;
	private ErrorHandler errorHandler ;
	private String targetDesc;
	private Properties properties;
	@Before
	public void setUp(){		
		properties = LLVMUtility.getDefaultProperties();
		CLangUtils.populateSettings();
		CompilerSettings compilerSettings = CompilerSettings.getInstance();
		compilerSettings.setInstanceProjectPath("CSrc/Preprocessor");
		CompilationContext compilationContext = new CompilationContext();
		compilerSettings.setCompilationContext(compilationContext);

		sourceFilePath ="CSrc/Preprocessor/IncludeProcessTest4.c"; 	
		
		environments = Environments.getInstance();		
		errorHandler = ErrorHandler.getInstance();
		
		targetDesc = compilerSettings.getInstanceTarget();
		
	}

	@Test
	public void testInclude4() {     

		
		sourceFilePath ="CSrc/Preprocessor/IncludeProcessTest4.c"; 
		
		PreprocessorMain ppMain = new PreprocessorMain(sourceFilePath);
		InputStream is = ppMain.replaceTrigraphSequencesAndSpliceLines(sourceFilePath);
		PreprocessorSegments preprocessorSegments = ppMain.getPreprocessorTranslationByLLParsing(is);
		assertNotNull(preprocessorSegments);
		String codeText = preprocessorSegments.process(sourceFilePath, true).toString();
				
		int numLinesInCode = getNumLinesInFile(sourceFilePath);
		int numLinesInProcessedCode = getNumLinesInString(codeText);

		System.out.println(codeText);

		assertTrue(numLinesInCode == numLinesInProcessedCode);

		// Run the parser and the semantic analyzer
//		PreprocessorMain ppMain = new PreprocessorMain(sourceFilePath);
		InputStream sourceFileInputStream = ppMain.process(true); 

		// Translate to abstract syntax tree
		TranslationUnit translationUnit = CLangUtils.getTranslationByLLParsing(sourceFileInputStream);

		// Pass through semantic analyzer and translate to assembly tree
		CompilationContext compilationContext = CompilerSettings.getInstance().getInstanceCompilationContext();
		Semantic semanticAnalyzer = new Semantic(properties, sourceFilePath, compilationContext); 	    
		semanticAnalyzer.translateAbstractTree(translationUnit);    	  
		errorHandler.displayResult();

		int count = 1;	

		ErrorIterator iter = new ErrorIterator(sourceFilePath);
		try{
			while(iter.hasNext()){
				SourceLocationAndMsg srcLcAndMsg =  iter.next();
				SourceLocation srcLc = srcLcAndMsg.getSrcLocation();
				String msg = srcLcAndMsg.getMsg();

				int line = srcLc.getLineNum();

				if(count == 1){
					assertTrue(line == 14); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "xyz" + ErrorHandler.E_NOT_DEFINED));
				}
				count++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}

		assertTrue(count == 1);		
	}
}

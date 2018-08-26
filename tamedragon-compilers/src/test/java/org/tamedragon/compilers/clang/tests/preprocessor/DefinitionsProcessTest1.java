package org.tamedragon.compilers.clang.tests.preprocessor;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;

import org.tamedragon.compilers.clang.preprocessor.PreprocessorMain;
import org.tamedragon.compilers.clang.preprocessor.PreprocessorSegments;
import org.tamedragon.compilers.clang.semantics.Environments;
import org.tamedragon.compilers.clang.tests.TestInitializer;

public class DefinitionsProcessTest1 extends TestInitializer {

	private String sourceFilePath;
	private PreprocessorSegments preprocessorSegments;	
	
	@Before
	public void setUp(){		
		sourceFilePath ="CSrc/Preprocessor/DefinitionsProcessTest1.c"; 
		
		PreprocessorMain ppMain = new PreprocessorMain(sourceFilePath);
		InputStream is = ppMain.replaceTrigraphSequencesAndSpliceLines(sourceFilePath);
		
		preprocessorSegments = ppMain.getPreprocessorTranslationByLLParsing(is);
		assertNotNull(preprocessorSegments);			
	}
	
	@Test
	public void test1() {     
		
		Environments environments = Environments.getInstance();
		environments.reset();	
		
		StringBuffer sb = preprocessorSegments.process(sourceFilePath, true);		
		String codeText = sb.toString();
		assertTrue(!codeText.contains("#define"));                      // should not have any more directives
		assertTrue(codeText.contains("for(x = 0; x < 3*20; x++)"));   // replacement has occurred
		assertTrue(!codeText.contains("INIT"));                         // all macros should be replaced
		assertTrue(!codeText.contains("MAX_COUNT"));                    // all macros should be replaced
		 
		printDefs();
	}	
}

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

public class TrigraphSequenceTest1 extends TestInitializer {

	private String sourceFilePath;
	private PreprocessorSegments preprocessorSegments;
	
	
	@Before
	public void setUp(){		
		sourceFilePath ="CSrc/Preprocessor/TrigraphSequenceTest1.c"; 
		
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
		System.out.println("Code text after trigraph replacement AND processing:");
		System.out.println(codeText);
		assertTrue(!codeText.contains("define"));                       // should not have any more directives
		assertTrue(codeText.contains("int arr1[3] = {1, 2, 3};"));      // replacement has occurred
		assertTrue(codeText.contains("result1 = m ^ n;"));      // replacement has occurred
		assertTrue(codeText.contains("result2 = m | n;"));      // replacement has occurred
		assertTrue(codeText.contains("result3 = m ~ n;"));      // replacement has occurred
		assertTrue(codeText.
				contains("This } or | should not get replaced, but it does"));  // replacement must not have occurred on a string		
	}	
}

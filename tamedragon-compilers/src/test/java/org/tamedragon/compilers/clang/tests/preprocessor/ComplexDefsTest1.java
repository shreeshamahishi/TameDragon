package org.tamedragon.compilers.clang.tests.preprocessor;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;

import org.tamedragon.compilers.clang.preprocessor.PreprocessorMain;
import org.tamedragon.compilers.clang.preprocessor.PreprocessorSegments;
import org.tamedragon.compilers.clang.semantics.Environments;
import org.tamedragon.compilers.clang.tests.TestInitializer;

public class ComplexDefsTest1 extends TestInitializer {

	private String sourceFilePath;
	private PreprocessorSegments preprocessorSegments;	
	
	@Before
	public void setUp(){	
		
		Environments environments = Environments.getInstance();
		environments.reset();	
		
		sourceFilePath ="CSrc/Preprocessor/ComplexDefsTest1.c"; 
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(sourceFilePath).getFile());
		sourceFilePath = file.getAbsolutePath();
		
		PreprocessorMain ppMain = new PreprocessorMain(sourceFilePath);
		InputStream is = ppMain.replaceTrigraphSequencesAndSpliceLines(sourceFilePath);
		
		preprocessorSegments = ppMain.getPreprocessorTranslationByLLParsing(is);
		assertNotNull(preprocessorSegments);			
	}
	
	@Test
	public void test1() {      		
		StringBuffer sb = preprocessorSegments.process(sourceFilePath, true);		
		String codeText = sb.toString();
		assertTrue(!codeText.contains("#define"));                      // should not have any more directives
		
		printDefs();
	}	
}

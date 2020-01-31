package org.tamedragon.compilers.clang.tests.preprocessor;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import org.tamedragon.compilers.clang.preprocessor.PreprocessorMain;
import org.tamedragon.compilers.clang.preprocessor.PreprocessorSegments;
import org.tamedragon.compilers.clang.preprocessor.PreprocessorUnit;
import org.tamedragon.compilers.clang.semantics.Environments;
import org.tamedragon.compilers.clang.tests.TestInitializer;

public class ConditionalTest1 extends TestInitializer {

	private String sourceFilePath;
	private PreprocessorSegments preprocessorSegments;	
	private PreprocessorMain ppMain;
	
	@Before
	public void setUp(){		
		sourceFilePath ="CSrc/Preprocessor/ConditionalTest1.c"; 
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(sourceFilePath).getFile());
		sourceFilePath = file.getAbsolutePath();
		
		ppMain = new PreprocessorMain(sourceFilePath);
		InputStream is = ppMain.replaceTrigraphSequencesAndSpliceLines(sourceFilePath);
		
		preprocessorSegments = ppMain.getPreprocessorTranslationByLLParsing(is);
		assertNotNull(preprocessorSegments);	
		
	}
	
	@Test
	public void test1() throws Exception {      		
		
		Environments environments = Environments.getInstance();
		environments.reset();	
		
		List<PreprocessorUnit> units = preprocessorSegments.getUnits();
		assertTrue(units.size() == 5);
		assertTrue(units.get(0).getPreprocessorUnitType() == PreprocessorUnit.PROGRAM_CODE);
		assertTrue(units.get(1).getPreprocessorUnitType() == PreprocessorUnit.DEFINITION);
		assertTrue(units.get(2).getPreprocessorUnitType() == PreprocessorUnit.PROGRAM_CODE);
		assertTrue(units.get(3).getPreprocessorUnitType() == PreprocessorUnit.CONDITIONAL);
		assertTrue(units.get(4).getPreprocessorUnitType() == PreprocessorUnit.PROGRAM_CODE);
		
		StringBuffer sb = preprocessorSegments.process(sourceFilePath, ppMain.getDependenciesDag(), true);		
		String codeText = sb.toString();
		
		System.out.println("************ FINAL PROCESSED CODE: ");
		System.out.println(codeText);
		System.out.println("************ END FINAL PROCESSED CODE: ");
		
		assertTrue(!codeText.contains("#define"));                      // should not have any more directives
		assertTrue(!codeText.contains("#ifdef"));                       // should not have any more directives
		assertTrue(!codeText.contains("#endif"));                       // should not have any more directives
		
		int numLinesInCode = getNumLinesInFile(sourceFilePath);
		int numLinesInProcessedCode = getNumLinesInString(codeText);
				
		assertTrue(numLinesInCode == numLinesInProcessedCode);
				 		
	}	
}

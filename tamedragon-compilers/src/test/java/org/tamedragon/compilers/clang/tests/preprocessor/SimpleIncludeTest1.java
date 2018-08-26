package org.tamedragon.compilers.clang.tests.preprocessor;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.InputStream;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import org.tamedragon.compilers.clang.preprocessor.PreprocessorMain;
import org.tamedragon.compilers.clang.preprocessor.PreprocessorSegments;
import org.tamedragon.compilers.clang.preprocessor.PreprocessorUnit;
import org.tamedragon.compilers.clang.semantics.Environments;
import org.tamedragon.compilers.clang.tests.TestInitializer;

public class SimpleIncludeTest1 extends TestInitializer {

	private String sourceFilePath;
	private PreprocessorSegments preprocessorSegments;
	
	
	@Before
	public void setUp(){		
		sourceFilePath ="CSrc/Preprocessor/SimpleIncludeTest1.c"; 
		PreprocessorMain ppMain = new PreprocessorMain(sourceFilePath);
		InputStream is = ppMain.replaceTrigraphSequencesAndSpliceLines(sourceFilePath);
		
		preprocessorSegments = ppMain.getPreprocessorTranslationByLLParsing(is);
		assertNotNull(preprocessorSegments);				
	}
	
	@Test
	public void test1() {     
		
		Environments environments = Environments.getInstance();
		environments.reset();	
		
		List<PreprocessorUnit> preprocessorUnits = preprocessorSegments.getUnits();
		assertTrue(preprocessorUnits.size() == 3);
		PreprocessorUnit pu1 = preprocessorUnits.get(0);
		PreprocessorUnit pu2 = preprocessorUnits.get(1);
		PreprocessorUnit pu3 = preprocessorUnits.get(2);
		assertTrue(pu1.getPreprocessorUnitType() == PreprocessorUnit.PROGRAM_CODE);
		assertTrue(pu2.getPreprocessorUnitType() == PreprocessorUnit.INCLUDE_DIRECTIVE);
		assertTrue(pu3.getPreprocessorUnitType() == PreprocessorUnit.PROGRAM_CODE);	
		
		assertTrue("stdio.h".equals(pu2.toString()));		
	}	
}

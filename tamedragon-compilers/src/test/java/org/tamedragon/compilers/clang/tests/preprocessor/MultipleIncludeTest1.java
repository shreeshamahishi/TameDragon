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

public class MultipleIncludeTest1 extends TestInitializer {

	private String sourceFilePath;
	private PreprocessorSegments preprocessorSegments;
	
	
	@Before
	public void setUp(){		
		sourceFilePath ="CSrc/Preprocessor/MultipleIncludeTest1.c"; 
		
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
		assertTrue(preprocessorUnits.size() == 12);
		PreprocessorUnit pu1 = preprocessorUnits.get(0);
		PreprocessorUnit pu2 = preprocessorUnits.get(1);
		PreprocessorUnit pu3 = preprocessorUnits.get(2);
		PreprocessorUnit pu4 = preprocessorUnits.get(3);
		PreprocessorUnit pu5 = preprocessorUnits.get(4);
		PreprocessorUnit pu6 = preprocessorUnits.get(5);
		PreprocessorUnit pu7 = preprocessorUnits.get(6);
		PreprocessorUnit pu8 = preprocessorUnits.get(7);
		PreprocessorUnit pu9 = preprocessorUnits.get(8);
		PreprocessorUnit pu10 = preprocessorUnits.get(9);
		PreprocessorUnit pu11 = preprocessorUnits.get(10);
		PreprocessorUnit pu12 = preprocessorUnits.get(11);
		
		assertTrue(pu1.getPreprocessorUnitType() == PreprocessorUnit.PROGRAM_CODE);
		
		assertTrue(pu2.getPreprocessorUnitType() == PreprocessorUnit.INCLUDE_DIRECTIVE);
		assertTrue(pu3.getPreprocessorUnitType() == PreprocessorUnit.INCLUDE_DIRECTIVE);
		assertTrue(pu4.getPreprocessorUnitType() == PreprocessorUnit.INCLUDE_DIRECTIVE);
		
		assertTrue(pu5.getPreprocessorUnitType() == PreprocessorUnit.PROGRAM_CODE);

		assertTrue(pu6.getPreprocessorUnitType() == PreprocessorUnit.INCLUDE_DIRECTIVE);
		
		assertTrue(pu7.getPreprocessorUnitType() == PreprocessorUnit.PROGRAM_CODE);
		
		assertTrue(pu8.getPreprocessorUnitType() == PreprocessorUnit.INCLUDE_DIRECTIVE);
		assertTrue(pu9.getPreprocessorUnitType() == PreprocessorUnit.INCLUDE_DIRECTIVE);
		
		assertTrue(pu10.getPreprocessorUnitType() == PreprocessorUnit.PROGRAM_CODE);
		
		assertTrue(pu11.getPreprocessorUnitType() == PreprocessorUnit.INCLUDE_DIRECTIVE);
		
		assertTrue(pu12.getPreprocessorUnitType() == PreprocessorUnit.PROGRAM_CODE);
		
		
		assertTrue("stdio.h".equals(pu2.toString()));
		assertTrue("cmath.h".equals(pu3.toString()));
		assertTrue("ctype.h".equals(pu4.toString()));
		assertTrue("conio.h".equals(pu6.toString()));
		assertTrue("sample1.h".equals(pu8.toString()));
		assertTrue("tyranny.h".equals(pu9.toString()));
		assertTrue("turn.h".equals(pu11.toString()));
			
	}	
}
